/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.controller;

import com.ssa.cms.delegate.OrderService;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.util.DateUtil;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mzubair
 */
@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("basicstatisticsreport");
        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setFromDate(DateUtil.getOneMonthBefore());
        baseDTO.setToDate(new Date());
        modelAndView.addObject("baseDTO", baseDTO);
        return modelAndView;
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ModelAndView populateRecord(@ModelAttribute BaseDTO baseDTO, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("basicstatisticsreport");
        modelAndView.addObject("basicStatisticsReportData", orderService.getBasicStatisticsReport(baseDTO));
        return modelAndView;
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView exportExcel(@RequestParam(value = "fromDate", required = false) Date fromDate, @RequestParam(value = "toDate", required = false) Date toDate,
            @RequestParam(value = "format", required = false) String format) {
        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setFromDate(DateUtil.endDateFormat(fromDate));
        baseDTO.setToDate(DateUtil.endDateFormat(toDate));
        ModelAndView modelAndView = new ModelAndView(format, "list", orderService.getBasicStatisticsReport(baseDTO));
        modelAndView.addObject("key", "basicStatisticsReportData");
        return modelAndView;
    }
}
