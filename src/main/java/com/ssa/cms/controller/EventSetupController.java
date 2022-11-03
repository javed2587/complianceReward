package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Event;
import com.ssa.cms.model.EventDetail;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/event")
public class EventSetupController implements Serializable {

    @Autowired
    private SetupService setupsDelegate;
    @Autowired
    private MessageSource messageSource;
    public String message;
    public String errorMessage;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/list")
    public ModelAndView getEventlist(HttpServletRequest request) {
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        List<Event> eventList = setupsDelegate.loadAllEventSetupNPData(sessionBean.getUserId());
        ModelAndView modelAndView = new ModelAndView("eventlist");
        modelAndView.addObject("eventlist", eventList);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEvent(HttpServletRequest request, @PathVariable("id") Integer eventId) {

        ModelAndView modelAndView = new ModelAndView("addevent");
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        LinkedHashMap<String, String> irfList = setupsDelegate.irfFieldList();
        LinkedHashMap<String, String> drfList = setupsDelegate.drfFieldList();

        if (eventId == 0) {

            Event event = new Event();
            event.setEventCriteria("Static");

            modelAndView.addObject("event", event);
            modelAndView.addObject("irfList", irfList);
            modelAndView.addObject("drfList", drfList);

        } else { // Edit existing detail

            Event selectedEvent = setupsDelegate.getSelectedEvent(eventId, sessionBean.getUserId() + "");
            modelAndView.addObject("event", selectedEvent);
            modelAndView.addObject("irfList", irfList);
            modelAndView.addObject("drfList", drfList);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEvent(HttpServletRequest request, @PathVariable("id") Integer eventId, RedirectAttributes redirectAttributes) {
        // delete the valid persistant event object with given eventId
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        if (eventId != null && eventId > 0) {
            setupsDelegate.deleteSelectedEvent(eventId, sessionBean.getUserId() + "");
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/event/list");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveUpdateEvent(@ModelAttribute @Valid Event event, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        manageList(sessionBean, event, model);
        if (event.getEventDetails() != null) {
            for (EventDetail eventDetail : event.getEventDetails()) {
                if (eventDetail.getFieldSelection() == null) {
                    event.getEventDetails().remove(eventDetail);
                } else {
                    eventDetail.setFieldSelection(eventDetail.getFieldSelection().replace(",", "").trim());
                }
            }
        }
        if (!validateEvent(result, event, model)) {
            return "/addevent";
        }

        // Check the event name duplication rule
        Boolean isDuplicatedEvent = setupsDelegate.checkDuplicatedEvent(event.getEventTitle(), event.getEventId());
        if (isDuplicatedEvent) {
            // Error Message will be returned
            model.addAttribute("errorMessage", messageSource.getMessage("field.event.duplicate", null, null));
            return "/addevent";
        } else {
            setupsDelegate.saveUpdateEvent(event, sessionBean.getUserId());
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            return "redirect:/event/list";
        }
    }

    private boolean validateEvent(BindingResult result, Event event, Model model) {
        boolean valid = true;
        if (result.hasErrors()) {
            valid = false;
        }
        if (event.getEventTitle() != null && !"".equals(event.getEventTitle().trim()) && event.getEventTitle().trim().length() < 2) {
            model.addAttribute("lengthMsg", "Minimum length 2 character");
            valid = false;
        }
        if ("Static".equals(event.getEventCriteria()) && (event.getStaticValue().trim() == null || "".equals(event.getStaticValue().trim()))) {
            model.addAttribute("message1", "Required");
            valid = false;
        }

        if ("Dynamic".equals(event.getEventCriteria())) {
            if ("".equals(event.getDynamicValue().trim()) || event.getDynamicValue().trim() == null) {
                model.addAttribute("message2", "Required");
                valid = false;
            }
            for (EventDetail eventDetail : event.getEventDetails()) {
                if ("DailyRedemption".equals(eventDetail.getDataSet())) {
                    eventDetail.setFieldSelection(eventDetail.getDrfValue());
                }
                if ((eventDetail.getDataSet().trim() == null || "".equals(eventDetail.getDataSet().trim())) || (eventDetail.getFieldSelection().trim() == null || "".equals(eventDetail.getFieldSelection().trim())) || (eventDetail.getOperation().trim() == null || "".equals(eventDetail.getOperation().trim()))
                        || (eventDetail.getSpecificValue().trim() == null || "".equals(eventDetail.getSpecificValue().trim())) || (eventDetail.getCondition().trim() == null || "".equals(eventDetail.getCondition().trim()))) {
                    model.addAttribute("errorMessage", "All dynamic field(s) required");
                    valid = false;
                }
            }
        }
        return valid;
    }

    private void manageList(SessionBean sessionBean, Event event, Model model) throws NumberFormatException {
        LinkedHashMap<String, String> irfList = setupsDelegate.irfFieldList();
        LinkedHashMap<String, String> drfList = setupsDelegate.drfFieldList();
        model.addAttribute("irfList", irfList);
        model.addAttribute("drfList", drfList);
    }

}
