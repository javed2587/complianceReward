package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Response;
import com.ssa.cms.model.ValidResponse;
import java.io.Serializable;
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
@RequestMapping(value = "/response")
public class ResponseSetupController implements Serializable {

    @Autowired
    private SetupService setupsDelegate;
    @Autowired
    private MessageSource messageSource;
    private String message;
    public String errorMessage;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/list")
    public ModelAndView responseList() {
        List<Response> list = setupsDelegate.getResponseList();
        ModelAndView modelAndView = new ModelAndView("resposelist");
        modelAndView.addObject("responselist", list);
        return modelAndView;
    }

    private List<ValidResponse> manageValidResponse(Response response) {
        List<ValidResponse> response2remove = new ArrayList<>();
        if (response.getValidResponses() != null) {
            for (Iterator<ValidResponse> i = response.getValidResponses().iterator(); i.hasNext();) {
                ValidResponse validResponse = i.next();
                if (validResponse.getRemove() == 1) {
                    response2remove.add(validResponse);
                    i.remove();
                } else if (validResponse.getValidWord() == null) {
                    response2remove.add(validResponse);
                    i.remove();
                } else {
                    validResponse.setResponse(response);
                }
            }
        }
        return response2remove;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addEdit(@ModelAttribute Response response, Model model) {
        return create(response, model, true);
    }

    private String create(Response response, Model model, boolean init) {
        if (init) {
            response.setValidResponses(new AutoPopulatingList<>(ValidResponse.class));
        }
        model.addAttribute("type", "create");
        return "addresponse";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveResponse(@Valid @ModelAttribute Response response, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        manageValidResponse(response);
        if (bindingResult.hasErrors()) {
            // Should not re-init the AutoPopulatingList
            return create(response, model, false);
        }
        if (response.getResponseTitle() != null && !"".equals(response.getResponseTitle().trim())) {
            Response duplicate = setupsDelegate.getResponseByName(response.getResponseTitle(), response.getResponseId());
            if (duplicate != null) {
                model.addAttribute("errorMessage", messageSource.getMessage("field.responseTitle.duplicate", null, null));
                return "/addresponse";
            }
        }
        if (response.getValidResponses() != null && response.getValidResponses().size() > 0) {
            Set<String> checkValidResponse = findDuplicates(response.getValidResponses());
            if (checkValidResponse.size() > 0) {
                model.addAttribute("errorMessage", messageSource.getMessage("field.validResponse.duplicate", null, null));
                return "/addresponse";
            }
        }
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean saved = setupsDelegate.saveResponse(response, sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "/addresponse";
        }
        return "redirect:/response/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editResponse(@PathVariable("id") Integer id) {
        Response response = setupsDelegate.getResponseById(id);
        ModelAndView modelAndView = new ModelAndView("addresponse");
        modelAndView.addObject("response", response);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteResponse(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if (setupsDelegate.isResponseAssociated(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        } else {
            setupsDelegate.deleteResponse(id);
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        }
        return new ModelAndView("redirect:/response/list");
    }

    public static Set<String> findDuplicates(List<ValidResponse> list) {
        final Set<String> setToReturn = new HashSet<>();
        final Set<String> set1 = new HashSet<>();
        for (ValidResponse validResponse : list) {
            if (!set1.add(validResponse.getValidWord())) {
                setToReturn.add(validResponse.getValidWord());
            }
        }
        return setToReturn;
    }

    @RequestMapping(value = "/deleteValidResponse/{id}", produces = "application/json")
    public @ResponseBody
    boolean deleteValidResponseHandler(@PathVariable String id,
            HttpServletRequest request) throws Exception {
        return setupsDelegate.deleteValidResponseById(Integer.parseInt(id));
    }
}
