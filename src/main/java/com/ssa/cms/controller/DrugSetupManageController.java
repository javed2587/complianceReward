/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.controller;

import com.ssa.cms.model.Users;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Haider Ali
 */
@Controller
public class DrugSetupManageController implements Serializable{
    
    private final Log logger = LogFactory.getLog(getClass());
    
    @RequestMapping(value = "/drug/setup/manage", method = RequestMethod.GET)
    public ModelAndView manageDrug() {
        logger.info("Begin: Drug manage controller  @@@@@@@@@@@@@@@@@@@@@@ ##############");
        ModelAndView modelAndView = new ModelAndView("drugsetupmanage");
        modelAndView.addObject("users", new Users());
        return modelAndView;
    }
    
}
