package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Folder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@RequestMapping(value = "/folder")
public class CommunicationFolderSetupController {

    @Autowired
    private SetupService setupsDelegate;
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
    public ModelAndView getCommunicationFolderlist() {

        List<Folder> list = setupsDelegate.getCommunicationFolderList();
        ModelAndView modelAndView = new ModelAndView("folderlist");
        modelAndView.addObject("folderlist", list);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEdit(@ModelAttribute Folder folder, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("addfolder");
        modelAndView.addObject("folderlist", new Folder());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCommunicationFolder(@ModelAttribute @Valid Folder folder, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String folderName = folder.getFolderName();
        if (!validate(result, folderName, folder, model)) {
            return "/addfolder";
        }
        boolean saved = setupsDelegate.saveCommunicationFolder(folder, sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "/addfolder";
        }
        return "redirect:/folder/list";
    }

    private boolean validate(BindingResult result, String folderName, Folder folder, Model model) throws NoSuchMessageException {

        if (result.hasErrors()) {
            return false;
        }
        if (folderName != null && !"".equals(folderName.trim()) && folderName.trim().length() < 4) {
            model.addAttribute("message1","Minimum length 4 character");
            return false;
        }
        if (folderName != null && !"".equals(folderName)) {
            boolean duplicate = setupsDelegate.getCommunicationFolderByName(folderName, folder.getFolderId());
            if (duplicate) {
                model.addAttribute("errorMessage", messageSource.getMessage("field.folder.duplicate", null, null));
                return false;
            }
        }

        return true;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editCommunicationFolder(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("addfolder");
        Folder folder = setupsDelegate.getFolderById(id);
        modelAndView.addObject("folder", folder);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBrand(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = setupsDelegate.deleteCommunicationFolder(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/folder/list");
        return modelAndView;
    }
}
