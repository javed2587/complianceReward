package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Folder;
import com.ssa.cms.model.MessageType;
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
@RequestMapping(value = "/messageType")
public class MessageTypeSetupController {

    @Autowired
    private SetupService setupsDelegate;
    @Autowired
    private MessageSource messageSource;

    SessionBean sessionBean;

    @RequestMapping(value = "/list")
    public ModelAndView messageTypeList(@ModelAttribute MessageType messageType) {
        List<MessageType> list = setupsDelegate.getMessageTypeList(sessionBean.getUserId());
        ModelAndView modelAndView = new ModelAndView("messagetypelist");
        modelAndView.addObject("messagetypelist", list);
        return modelAndView;
    }

    @InitBinder
    public void init(HttpServletRequest request) {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEdit(@ModelAttribute MessageType messageType, BindingResult result, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("addmessagetype");
        List<Folder> folderList = setupsDelegate.getCommunicationFolderList();
        modelAndView.addObject("messagetypelist", new MessageType());
        modelAndView.addObject("folderList", folderList);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("addmessagetype");
        MessageType messageType = setupsDelegate.getMessageTypeById(id);
        List<Folder> folderList = setupsDelegate.getCommunicationFolderList();
        modelAndView.addObject("messageType", messageType);
        modelAndView.addObject("folderList", folderList);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveMessageType(@ModelAttribute @Valid MessageType messageType, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        List<Folder> folderList = setupsDelegate.getCommunicationFolderList();
        model.addAttribute("folderList", folderList);

        if (!validateMessageType(result, messageType, model)) {
            return "/addmessagetype";
        }

        boolean saved = setupsDelegate.saveMessageTypes(messageType, sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "/addmessagetype";
        }
        return "redirect:/messageType/list?r=" + Math.random();
    }

    private boolean validateMessageType(BindingResult result, MessageType messageType, Model model) throws NoSuchMessageException {
        boolean valid = true;
        if (result.hasErrors()) {
            valid = false;
        }
        if (messageType.getId().getFolderId() == 0) {
            model.addAttribute("message1", "Required");
            valid = false;
        }
        if (messageType.getMessageTypeTitle().trim() != null && !"".equals(messageType.getMessageTypeTitle().trim()) && messageType.getMessageTypeTitle().trim().length() < 4) {
            model.addAttribute("message2", "Minimum length 4 character");
            valid = false;
        }
        boolean duplicate = setupsDelegate.isMessageTypeExit(messageType.getId().getMessageTypeId(), messageType.getId().getFolderId(), messageType.getMessageTypeTitle(), messageType.getType());
        if (duplicate) {
            model.addAttribute("errorMessage", messageSource.getMessage("field.messageType.duplicate", null, null));
            valid = false;
        }
        return valid;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBrand(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = setupsDelegate.deleteMessageType(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/messageType/list");
        return modelAndView;
    }
}
