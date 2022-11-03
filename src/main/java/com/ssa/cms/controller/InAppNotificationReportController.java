/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.controller;

import com.itextpdf.text.DocumentException;
import com.ssa.cms.common.Constants;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.NotificationMessagesDTO;
import com.ssa.cms.model.NotificationMessages;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping(value = "/inAppReport")
public class InAppNotificationReportController {

    @Autowired
    private PatientProfileService patientProfileService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.USA_DATE_FORMATE);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("inappnotificationreport");
        modelAndView.addObject("baseDTO", new BaseDTO());
        return modelAndView;
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ModelAndView populateRecord(@ModelAttribute BaseDTO baseDTO, HttpServletRequest request) throws DocumentException, Exception {
        ModelAndView modelAndView = new ModelAndView("inappnotificationreport");
        List<NotificationMessagesDTO> list = patientProfileService.getInAppNotificationReport(baseDTO, false);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public ModelAndView exportExcel(@RequestParam(value = "fromDate", required = false) Date fromDate, @RequestParam(value = "toDate", required = false) Date toDate) {
        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setFromDate(DateUtil.endDateFormat(fromDate));
        baseDTO.setToDate(DateUtil.endDateFormat(toDate));
        ModelAndView modelAndView = new ModelAndView("excelView", "list", patientProfileService.getInAppNotificationReport(baseDTO, true));
        modelAndView.addObject("key", "inAppNotificationReports");
        return modelAndView;
    }

    @RequestMapping(value = "/exportPdf", method = RequestMethod.GET)
    public ModelAndView exportPdf(@RequestParam(value = "fromDate", required = false) Date fromDate, @RequestParam(value = "toDate", required = false) Date toDate, @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
        BaseDTO baseDTO = new BaseDTO();
        if (fromDate != null) {
            baseDTO.setFromDate(DateUtil.endDateFormat(fromDate));
        }
        if (toDate != null) {
            baseDTO.setToDate(DateUtil.endDateFormat(toDate));
        }
        baseDTO.setPhoneNumber(phoneNumber);
        ModelAndView modelAndView = new ModelAndView("pdfView", "list", patientProfileService.getInAppNotificationReport(baseDTO, true));
        modelAndView.addObject("key", "inAppNotificationReports");
        return modelAndView;
    }
}
