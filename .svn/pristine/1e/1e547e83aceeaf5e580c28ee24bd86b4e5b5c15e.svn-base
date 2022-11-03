package com.ssa.cms.controller;

import com.ssa.cms.service.DashboardService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author msheraz
 */
@Controller
public class DashboardController implements Serializable{
    
    @Autowired
    private DashboardService dashboardService;
    
    @RequestMapping(value = "/dashboard")
    public ModelAndView loadPageHandler() throws Exception {
        ModelAndView modelAndView = new ModelAndView("index");
        
        modelAndView.addObject("topTenPrescriberList", "");
        
        modelAndView.addObject("topTenPharmacyList", "");
        
        modelAndView.addObject("allProgramData", "");
        
        return modelAndView;
    }
}
