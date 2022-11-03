package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.PermissionService;
import com.ssa.cms.model.RoleTypes;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private PermissionService permissionDelegate;
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listRole(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("rolelist");
        modelAndView.addObject("roles", permissionDelegate.getUserRoles());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("role") @Valid RoleTypes roleTypes, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        int userId = sessionBean.getUserId();
        if (!validateRole(result, roleTypes, userId + "", model)) {
            return "/addrole";
        }

        boolean saved = permissionDelegate.saveRole(roleTypes, userId);
        if (!saved) {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "/addrole";
        } else {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        }

        return "redirect:/role/list";
    }

    private boolean validateRole(BindingResult result, RoleTypes roleTypes, String userId, Model model) throws NumberFormatException, NoSuchMessageException {
        if (result.hasErrors()) {
            return false;
        }
        if (permissionDelegate.roleExists(roleTypes.getRoleTitle(), roleTypes.getRoleId(),
                Integer.parseInt(userId))) {
            model.addAttribute("errorMessage", messageSource.getMessage("field.roleTitle.duplicate", null, null));
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addRole(@ModelAttribute("role") RoleTypes roleType, Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("addrole");
        roleType.setIsActive("Yes");
        modelAndView.addObject("role", roleType);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editRole(@PathVariable Integer id, Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("addrole");
        RoleTypes role = permissionDelegate.getRoleById(id);
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRole(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = permissionDelegate.deleteRole(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/role/list");
        return modelAndView;
    }

}
