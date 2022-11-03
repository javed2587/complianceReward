package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.WidgetUser;
import com.ssa.cms.model.WidgetUserIpaddresses;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/widgetUser")
public class WidgetUserSetupController {

    @Autowired
    private SetupService setupService;
    @Autowired
    private MessageSource messageSource;

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
    public ModelAndView widgetUserList(@ModelAttribute WidgetUser widgetUser) {
        List<WidgetUser> list = setupService.getWidgetUserList(sessionBean.getUserId()+"");
        ModelAndView modelAndView = new ModelAndView("widgetuserlist");
        modelAndView.addObject("widgetuserlist", list);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addEdit(@ModelAttribute WidgetUser widgetUser, Model model) {
        return create(widgetUser, model, true);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveWidgetUser(@ModelAttribute @Valid WidgetUser widgetUser, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        manageWidgetUserIpaddress(widgetUser);
        if (!validateWidgetUser(bindingResult, widgetUser, model)) {
            return create(widgetUser, model, false);
        }
        boolean saved = setupService.saveWidgetUser(widgetUser, sessionBean.getUserName());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "/addwidgetuser";
        }
        return "redirect:/widgetUser/list";
    }

    private boolean validateWidgetUser(BindingResult bindingResult, WidgetUser widgetUser, Model model) throws NoSuchMessageException {
        if (bindingResult.hasErrors()) {
            return false;
        }
        if (widgetUser.getUserName() != null && !"".equals(widgetUser.getUserName().trim())) {
            boolean checkDuplicateUserName = setupService.isUserNameValid(widgetUser.getUserName(), widgetUser.getWidgetUserId());
            if (checkDuplicateUserName) {
                model.addAttribute("errorMessage", messageSource.getMessage("field.widgetUser.duplicate", null, null));
                return false;
            }
        }

        if (widgetUser.getWidgetUserIpaddresseses() != null && !widgetUser.getWidgetUserIpaddresseses().isEmpty()) {
            for (WidgetUserIpaddresses ipaddresses : widgetUser.getWidgetUserIpaddresseses()) {
                if (widgetUser.getValidateIp() != null && widgetUser.getValidateIp().equalsIgnoreCase("yes") && ipaddresses.getIpAddress() != null && ipaddresses.getIpAddress().trim().isEmpty()) {
                    model.addAttribute("errorMessage", messageSource.getMessage("field.ipaddress.required", null, null));
                    return false;
                }
                if ("Invalid IP address".equals(ipaddresses.getIpAddress())) {
                    model.addAttribute("errorMessage", messageSource.getMessage("field.ipaddress.error", null, null));
                    return false;
                }
            }
            Set<String> checkDuplicateIpAddress = findDuplicates(widgetUser.getWidgetUserIpaddresseses());
            if (checkDuplicateIpAddress.size() > 0) {
                model.addAttribute("errorMessage", messageSource.getMessage("field.widgetUser.widgetUserIpaddresseses.duplicate", null, null));
                return false;
            }
        }
        return true;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editWidgetUser(@PathVariable("id") Integer id) {
        WidgetUser widgetUser = setupService.getWidgetUserById(id);
        List<Campaigns> campaignlist = setupService.getAllCampaigns(sessionBean.getUserId()+"");
        ModelAndView modelAndView = new ModelAndView("addwidgetuser");
        modelAndView.addObject("widgetUser", widgetUser);
        modelAndView.addObject("campaignlist", campaignlist);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteWidgetUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = setupService.deleteWidgetUser(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/widgetUser/list");
        return modelAndView;
    }

    private String create(WidgetUser widgetUser, Model model, boolean init) {
        if (init) {
            widgetUser.setWidgetUserIpaddresseses(new AutoPopulatingList<WidgetUserIpaddresses>(WidgetUserIpaddresses.class));
        }
        List<Campaigns> campaignlist = setupService.getAllCampaigns(sessionBean.getUserId()+"");
        model.addAttribute("campaignlist", campaignlist);
        widgetUser.setIsActive("Yes");
        model.addAttribute("type", "widgetUser");
        return "addwidgetuser";
    }

    private List<WidgetUserIpaddresses> manageWidgetUserIpaddress(WidgetUser widgetUser) {
        List<WidgetUserIpaddresses> widgetUserIpaddresses2remove = new ArrayList<WidgetUserIpaddresses>();
        if (widgetUser.getWidgetUserIpaddresseses() != null) {
            for (Iterator<WidgetUserIpaddresses> i = widgetUser.getWidgetUserIpaddresseses().iterator(); i.hasNext();) {
                WidgetUserIpaddresses widgetUserIpaddresses = i.next();
                // If the remove flag is true, remove the employee from the list
                if (widgetUserIpaddresses.getRemove() == 1) {
                    widgetUserIpaddresses2remove.add(widgetUserIpaddresses);
                    i.remove();
                    // Otherwise, perform the links
                } else {
                    widgetUserIpaddresses.setWidgetUser(widgetUser);
                }
            }
        }
        return widgetUserIpaddresses2remove;
    }

    public static Set<String> findDuplicates(List<WidgetUserIpaddresses> list) {

        final Set<String> setToReturn = new HashSet<String>();
        final Set<String> set1 = new HashSet<String>();

        for (WidgetUserIpaddresses widgetUserIpaddresses : list) {
            if (!"Not a valid IP address".equals(widgetUserIpaddresses.getIpAddress()) && !set1.add(widgetUserIpaddresses.getIpAddress())) {
                setToReturn.add(widgetUserIpaddresses.getIpAddress());
            }
        }
        return setToReturn;
    }
}
