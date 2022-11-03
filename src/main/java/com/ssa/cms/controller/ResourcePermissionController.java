package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.PermissionService;
import com.ssa.cms.model.AppResource;
import com.ssa.cms.model.AppResourceObjectPermissions;
import com.ssa.cms.model.RoleTypes;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/permission")
public class ResourcePermissionController {

    @Autowired
    private PermissionService permissionService;

    private final Log logger = LogFactory.getLog(getClass());
    SessionBean sessionBean;
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    void initBinder(HttpServletRequest request) throws Exception {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ModelAndView load(@ModelAttribute AppResourceObjectPermissions objectPermissions, HttpServletRequest request) {
        List<RoleTypes> roles = permissionService.getRoleTypeList();
        ModelAndView modelAndView = new ModelAndView("permission");
        modelAndView.addObject("objectPermissions", new AppResourceObjectPermissions());
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public ModelAndView load(@ModelAttribute AppResourceObjectPermissions objectPermissions, RedirectAttributes redirectAttributes) {
        List<RoleTypes> roles = permissionService.getRoleTypeList();
        if (objectPermissions.getId().getRoleId() == 0) {
            ModelAndView modelAndView = new ModelAndView("redirect:/permission/load");
            return modelAndView;
        }
        List<AppResource> resources = permissionService.getAppResources();
        List<AppResourceObjectPermissions> permissions = permissionService.getAppResourcePermissionByRoleId(objectPermissions.getId().getRoleId());
        if (permissions != null && permissions.size() > 0) {
            for (AppResourceObjectPermissions perm : permissions) {
                for (AppResource resource : resources) {
                    if (perm.getId().getResourceId() == resource.getResourceId()) {
                        if (perm.isAllowManage() != null && perm.isAllowManage()) {
                            resource.setManageAllow(true);
                        } else {
                            resource.setManageAllow(false);
                        }

                        if (perm.isAllowView() != null && perm.isAllowView()) {
                            resource.setViewAllow(true);
                        } else {
                            resource.setViewAllow(false);
                        }
                        break;
                    }
                }
            }
        }
        objectPermissions.setResources(resources);
        ModelAndView modelAndView = new ModelAndView("permission");
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("resources", resources);
        modelAndView.addObject("objectPermissions", objectPermissions);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveResourcePermission(@ModelAttribute AppResourceObjectPermissions objectPermissions, Model model, RedirectAttributes redirectAttributes) {
        boolean saved = permissionService.saveAppResourcePermission(objectPermissions.getResources(), objectPermissions.getId().getRoleId(), sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/permission/load");
        return modelAndView;
    }
}
