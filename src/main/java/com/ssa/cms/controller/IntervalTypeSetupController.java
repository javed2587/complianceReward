package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.validator.IntervalValidator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/intervalsType")
public class IntervalTypeSetupController {

    @Autowired
    private SetupService setupsDelegate;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private IntervalValidator intervalValidator;
    SessionBean sessionBean;

    @InitBinder
    public void init(HttpServletRequest request) {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/list")
    public ModelAndView intervalTypeList() {
        List<IntervalsType> list = setupsDelegate.getIntervalTypeList();
        ModelAndView modelAndView = new ModelAndView("intervaltypelist");
        modelAndView.addObject("intervaltypelist", list);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addEdit(@ModelAttribute IntervalsType intervalsType, Model model) {
        return create(intervalsType, model, true);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveInterval(@Valid @ModelAttribute IntervalsType intervalsType, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        manageIntervals(intervalsType);
        intervalValidator.validate(intervalsType, bindingResult);
        if (!validateIntervals(bindingResult, intervalsType, model)) {
            return "/addintervaltype";
        }
        boolean saved = setupsDelegate.saveIntervalTypes(intervalsType, sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "redirect:/intervalsType/add";
        }
        return "redirect:/intervalsType/list";
    }

    private boolean validateIntervals(BindingResult bindingResult, IntervalsType intervalsType, Model model) throws NoSuchMessageException {
        if (bindingResult.hasErrors()) {
            return false;
        }
        if (intervalsType.getIntervalsTypeTitle() != null && !"".equals(intervalsType.getIntervalsTypeTitle().trim()) && intervalsType.getIntervalsTypeTitle().trim().length() < 3) {
            model.addAttribute("message1", "Minimum length 3 character");
            return false;
        }
        boolean checkDuplicateTitle = setupsDelegate.getIntervalsByTitle(intervalsType.getIntervalsTypeTitle(), intervalsType.getIntervalsTypeId());
        if (checkDuplicateTitle) {
            model.addAttribute("errorMessage", messageSource.getMessage("field.intervalTitle.duplicate", null, null));
            return false;
        }
        if (intervalsType.getIntervals() != null && !intervalsType.getIntervals().isEmpty()) {

            Set<Integer> checkIntervalValue = findDuplicates(intervalsType.getIntervals());
            if (checkIntervalValue.size() > 0) {
                model.addAttribute("errorMessage", messageSource.getMessage("field.intervalValue.duplicate", null, null));
                return false;
            }
        }
        return true;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editInterval(@PathVariable("id") Integer id, @ModelAttribute IntervalsType intervalsType) {
        intervalsType = setupsDelegate.getIntervalsTypeById(id);
        ModelAndView modelAndView = new ModelAndView("addintervaltype");
        modelAndView.addObject("intervalsType", intervalsType);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteInterval(@PathVariable("id") Integer id, RedirectAttributes model) {
        boolean deleted = setupsDelegate.deleteIntervalsType(id);
        if (deleted) {
            model.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            model.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/intervalsType/list");
        return modelAndView;
    }

    private String create(IntervalsType intervalsType, Model model, boolean init) {
        if (init) {
            intervalsType.setIntervals(new AutoPopulatingList<>(Intervals.class));
        }
        return "/addintervaltype";
    }

    private List<Intervals> manageIntervals(IntervalsType intervalsType) {
        List<Intervals> Intervals2remove = new ArrayList<>();
        if (intervalsType.getIntervals() != null) {
            for (Iterator<Intervals> i = intervalsType.getIntervals().iterator(); i.hasNext();) {
                Intervals intervals = i.next();
                // If the remove flag is true, remove the employee from the list
                if (intervals.getRemove() == 1) {
                    Intervals2remove.add(intervals);
                    i.remove();
                    // Otherwise, perform the links
                } else {
                    intervals.setIntervalsType(intervalsType);
                }
            }
        }
        return Intervals2remove;
    }

    public static Set<Integer> findDuplicates(List<Intervals> list) {

        final Set<Integer> setToReturn = new HashSet<>();
        final Set<Integer> set1 = new HashSet<>();

        for (Intervals intervals : list) {
            if (!set1.add(intervals.getIntervalValue())) {
                setToReturn.add(intervals.getIntervalValue());
            }
        }
        return setToReturn;
    }

    @RequestMapping(value = "/deleteInterval/{id}", produces = "application/json")
    public @ResponseBody
    boolean deleteIntervalHandler(@PathVariable String id,
            HttpServletRequest request) throws Exception {
        return setupsDelegate.deleteIntervalsById(Integer.parseInt(id));
    }
}
