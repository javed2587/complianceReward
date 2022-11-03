package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.CMSService;
import com.ssa.cms.model.CMSDocuments;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zubair
 */
@Controller
@RequestMapping(value = "/cMSDocuments")
public class CMSDocumentController {
    
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private CMSService cMSService;
    SessionBean sessionBean;
    
    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }
    
    @RequestMapping(value = "/list")
    public ModelAndView pharmaciesList() {
        ModelAndView modelAndView = new ModelAndView("cmsdocumentslist");
        modelAndView.addObject("documentslist", cMSService.getCMSDocumentList());
        return modelAndView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("addcmsdocuments");
        modelAndView.addObject("cMSDocuments", new CMSDocuments());
        return modelAndView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView saveOrUpdate(@ModelAttribute @Valid CMSDocuments cMSDocuments, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request, @RequestParam("attachment") MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView("addcmsdocuments");
        if (!validateField(result, modelAndView, cMSDocuments)) {
            return modelAndView;
        }
        boolean isTitleExist = cMSService.isCMSDocumentTitleExist(cMSDocuments.getTitle(), cMSDocuments.getId());
        if (isTitleExist) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.cmsdocument.duplicate", null, null));
            modelAndView.addObject("cMSDocuments", cMSDocuments);
            return modelAndView;
        }
        cMSDocuments.setAttachmentName(file.getOriginalFilename());
        cMSDocuments.setContentType(file.getContentType());
        cMSDocuments.setAttachment(file.getBytes());
        boolean isSaved = cMSService.saveDocuments(cMSDocuments, sessionBean.getUserId());
        if (isSaved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            modelAndView.addObject("documents", cMSDocuments);
            return modelAndView;
        }
        return new ModelAndView("redirect:/cMSDocuments/list");
    }
    
    private boolean validateField(BindingResult result, ModelAndView modelAndView, CMSDocuments cMSDocuments) {
        boolean valid = true;
        if (result.hasErrors()) {
            modelAndView.addObject("cMSDocuments", cMSDocuments);
            valid = false;
        }
        if (cMSDocuments.getTitle() != null && cMSDocuments.getTitle().isEmpty()) {
            modelAndView.addObject("cMSDocuments", cMSDocuments);
            modelAndView.addObject("message1", "Required");
            valid = false;
        }
        if (cMSDocuments.getdType().isEmpty()) {
            modelAndView.addObject("cMSDocuments", cMSDocuments);
            modelAndView.addObject("message2", "Required");
            valid = false;
        }
        if (cMSDocuments.getAttachment().length == 0) {
            modelAndView.addObject("cMSDocuments", cMSDocuments);
            modelAndView.addObject("message3", "Required");
            valid = false;
        }
        return valid;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getRecord(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("addcmsdocuments");
        modelAndView.addObject("cMSDocuments", cMSService.getCMSDocuments(id));
        return modelAndView;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRecord(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean delete = cMSService.deleteRecord(id);
        if (delete) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        return new ModelAndView("redirect:/cMSDocuments/list");
    }
}
