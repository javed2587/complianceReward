package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.CMSService;
import com.ssa.cms.model.CMSPageContent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
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

/**
 *
 * @author Zubair
 */
@Controller
@RequestMapping(value = "/CmsPageContent")
public class CMSContentController {

    @Autowired
    private CMSService cMSService;
    @Autowired
    private MessageSource messageSource;
    SessionBean sessionBean;

    /**
     * 
     * @param binder
     * @param request
     * @throws Exception 
     */
    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("addcmscontent");
        modelAndView.addObject("cmsPagelist", cMSService.getCMSPageses());
        modelAndView.addObject("cMSPageContent", new CMSPageContent());
        return modelAndView;
    }

    /**
     * 
     * @param cMSPageContent
     * @param result
     * @param redirectAttributes
     * @param request
     * @return 
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute @Valid CMSPageContent cMSPageContent, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("addcmscontent");
        modelAndView.addObject("cmsPagelist", cMSService.getCMSPageses());
        if (result.hasErrors()) {
            modelAndView.addObject("cMSPageContent", cMSPageContent);
            return modelAndView;
        }
        if (cMSPageContent.getcMSPages().getId() == 0) {
            modelAndView.addObject("cMSPageContent", cMSPageContent);
            modelAndView.addObject("message1", "Required");
            return modelAndView;
        }
        boolean isSave = cMSService.saveCMSContent(cMSPageContent, sessionBean.getUserId());
        if (isSave) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            modelAndView.addObject("cMSPageContent", cMSPageContent);
            return modelAndView;
        }
        return new ModelAndView("redirect:/CmsPageContent/add");
    }

    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/getRecord/{id}", produces = "application/json")
    public @ResponseBody
    String getRecord(@PathVariable("id") Integer id) throws Exception {
        return cMSService.getCMSContentById(id);
    }

}
