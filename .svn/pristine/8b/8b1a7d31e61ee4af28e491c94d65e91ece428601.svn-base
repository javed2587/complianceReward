package com.ssa.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author msheraz
 */
@Controller
@RequestMapping(value = "")
public class TermsController {
    @RequestMapping(value = {"/terms", "/help", "/privacy", "/policy", "esp"}, method = RequestMethod.GET)
    public ModelAndView viewPageUnderConstruction() {
        ModelAndView modelAndView = new ModelAndView("pageUnderConstruction");
        return modelAndView;
    }
}
