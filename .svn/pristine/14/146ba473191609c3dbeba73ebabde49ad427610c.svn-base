package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.OrderService;
import com.ssa.cms.model.Order;
import com.ssa.cms.util.DateUtil;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WelcomeController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) throws Exception {
        SessionBean sessionBean = (SessionBean) request.getSession(false).getAttribute("sessionBean");
        if (sessionBean.getUserNameDB().equalsIgnoreCase("viewOrder")) {
            return this.viewOrder(request);
        } else {
            //return new ModelAndView("redirect:/dashboard");
            return new ModelAndView("redirect:/patient/deletePatientrecorByPhone");
        }
    }

    public ModelAndView viewOrder(HttpServletRequest request) throws Exception {
        Order order = new Order();
        order.setFromDate(DateUtil.dateToString(new Date(), "MM/dd/yyyy"));
        order.setToDate(DateUtil.dateToString(new Date(), "MM/dd/yyyy"));
        SessionBean sessionBean = (SessionBean) request.getSession(false).getAttribute("sessionBean");
        ModelAndView modelAndView = new ModelAndView("orderlist");
        List<Order> list = orderService.getAllPharmacyOrder();
        modelAndView.addObject("orderStatusList", orderService.getOrderStatusList());
        modelAndView.addObject("pharmacyList", orderService.getPharmacyList(sessionBean.getUserNameDB(), sessionBean.getUserId()));
        modelAndView.addObject("pharmacyUser", sessionBean.getUserNameDB());
        modelAndView.addObject("totalRecords", list.size());
        modelAndView.addObject("list", list);
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @RequestMapping(value = "/order/search", method = RequestMethod.GET)
    public ModelAndView OrderSearch(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        return new ModelAndView("redirect:/order/search");
    }
}
