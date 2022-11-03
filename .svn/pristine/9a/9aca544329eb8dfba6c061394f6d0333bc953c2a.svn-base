/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.service;

import com.ssa.cms.common.Constants;
import com.ssa.cms.dao.NotificationPharmacyDAO;
import com.ssa.cms.dao.OrderDAO;
import com.ssa.cms.dto.DataTablesTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.dto.PatientNotificationDTO;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.PatientNotification;
import com.ssa.cms.model.Users;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Haider Ali
 */
@Service
@Transactional
public class NotificationPharmacyService {

    @Autowired
    private NotificationPharmacyDAO notificationPharmacyDAO;
    @Autowired
    private OrderDAO orderDAO;

    private static final Log logger = LogFactory.getLog(NotificationPharmacyService.class.getName());

    public String getFilteredDependentRecordListing(int iDisplayStart, int iDisplayLength, Integer patientId, int sEcho) {
        String json = "";
        try {
           
        } catch (Exception e) {
            logger.error("Exception: getFilteredDependentRecordListing:: ", e);
        }
        return json;
    }

    

    public String getFilteredPatientMessageHistory(int iDisplayStart, int iDisplayLength, Integer patientId, int sEcho) {
        String json = "";
        List<PatientNotificationDTO> newPatientProfileNotes = new ArrayList<>();
        try {
            List<PatientNotification> dbList = notificationPharmacyDAO.getPatientNotificationListByPatientId(iDisplayStart, iDisplayLength, patientId);
            for (PatientNotification patientNotification : dbList) {
                PatientNotificationDTO notification = new PatientNotificationDTO();
                if (patientNotification.getDateSent() != null) {
                    notification.setDateSent(DateUtil.dateToString(patientNotification.getDateSent(), Constants.USA_DATE_TIME_FORMATE));
                }
                if (patientNotification.getCreatedOn() != null) {
                    notification.setSentOn(DateUtil.dateToString(patientNotification.getCreatedOn(), Constants.USA_DATE_TIME_FORMATE));
                }
                notification.setMessage(EncryptionHandlerUtil.getDecryptedString(patientNotification.getMessage()));
                if (patientNotification.getCreatedBy() != null) {
                    Users users = (Users) notificationPharmacyDAO.getObjectById(new Users(), patientNotification.getCreatedBy());
                    notification.setSentBy(users.getFirstName() + " " + users.getLastName());
                }
                newPatientProfileNotes.add(notification);
            }
            DataTablesTO<PatientNotificationDTO> dataTableTo = new DataTablesTO<>();
            Integer totalRecords = newPatientProfileNotes.size();
            if (dbList.size() > 0) {
                dataTableTo.setiTotalDisplayRecords((Long) notificationPharmacyDAO.getTotalRecords(new PatientNotification(), "PatientProfileInfoId", patientId, Constants.HIBERNATE_COUNT_FUNCTION, "*"));
            } else {
                dataTableTo.setiTotalDisplayRecords(totalRecords.longValue());
            }
            dataTableTo.setAaData(newPatientProfileNotes);
            dataTableTo.setsEcho(sEcho);
            json = CommonUtil.toJson(dataTableTo);
        } catch (Exception e) {
            logger.error("Exception: getFilteredPatientMessageHistory:: ", e);
        }
        return json;
    }

    public String getProcessedOrdersByPatientId(int iDisplayStart, int iDisplayLength, Integer filter, int sEcho, String sdir) {
        String json = "";
        try {
            List<OrderDetailDTO> listOfOrderDetail = new ArrayList<>();
            List<Integer> lstStatus = CommonUtil.generateStatusIdsList();
            List<Order> orders = notificationPharmacyDAO.getRxProgramOrdersByPatientId(filter, lstStatus);
            //populating Processed/Filled orders
            for (Order order : orders) {
                OrderDetailDTO detailDTO = new OrderDetailDTO();
                CommonUtil.populateDecryptedOrderData(detailDTO, order);
                detailDTO.setId(order.getId());
                detailDTO.setSystemGeneratedRxNumber("<span class='bold_new'>" + order.getRxPrefix() + "<br/></span>" + order.getSystemGeneratedRxNumber());
                String year = DateUtil.dateToString(order.getCreatedOn(), "yyyy");
                String month = DateUtil.dateToString(order.getCreatedOn(), "MM");
                String d = DateUtil.dateToString(order.getCreatedOn(), "dd");
                detailDTO.setOrderDate(year + "-" + month + "-<span class='redText'>" + d + "</span>");
//                detailDTO.setSelfPayCheck(AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("SELF PAY") ? "<span class='redText'>N</span>" : "INS");
                detailDTO.setQty(order.getQty());
                String drugName = "", strength = "", drugType = "", deliveryPreferencesName = "";
                if (CommonUtil.isNotEmpty(order.getDrugName())) {
                    drugName = order.getDrugName();
                }
                if (CommonUtil.isNotEmpty(order.getStrength())) {
                    strength = order.getStrength();
                }
                if (CommonUtil.isNotEmpty(order.getDrugType())) {
                    drugType = order.getDrugType();
                }
                detailDTO.setDrugName(drugName + ' ' + drugType + ' ' + strength);
                double handlingFee = order.getHandLingFee() != null ? order.getHandLingFee() : 0d;
//                detailDTO.setHandLingFee(handlingFee);
//                detailDTO.setHandlingFeeStr(AppUtil.roundOffNumberToCurrencyFormat(detailDTO.getHandLingFee(), "en", "US"));
//                double finalPayment = order.getFinalPayment() != null ? order.getFinalPayment() : 0d;
//                double paymentExcludingDelivery = finalPayment > handlingFee ? finalPayment - handlingFee : 0d;
//                detailDTO.setFinalPayment(finalPayment);
//                detailDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(detailDTO.getFinalPayment(), "en", "US"));
//                if (order.getDeliveryPreference() != null) {
//                    detailDTO.setDeliveryPreferenceId(order.getDeliveryPreference().getId());
//                    deliveryPreferencesName = order.getDeliveryPreference().getName();
//                    detailDTO.setDeliveryPreferencesName(deliveryPreferencesName);
//                    if (detailDTO.getDeliveryPreferencesName().equalsIgnoreCase("Same Day")) {
//                        deliveryPreferencesName = "<span style='color:red;'>!*Same<br/>DAY *!</span>";
//
//                    } else if (detailDTO.getDeliveryPreferencesName().equalsIgnoreCase("Next Day*")) {
//                        deliveryPreferencesName = "<span class='redText'>! *</span>";
//                        deliveryPreferencesName += "<span style='color:blue;'>NEXT<br/> DAY</span>";
//                        deliveryPreferencesName += "<span class='redText'>*!</span>";
//                    } else if (detailDTO.getDeliveryPreferencesName().equalsIgnoreCase("Pick Up at Pharmacy")) {
//                        deliveryPreferencesName = "Pick Up<br/>at Pharmacy";
//                    }
//                    detailDTO.setDeliveryPreferencesName(deliveryPreferencesName);
//                } else {
//                    detailDTO.setDeliveryPreferenceId(0);
//                    detailDTO.setDeliveryPreferencesName("-");
//                }
                detailDTO.setZipCode(order.getZip());
                String requestType = AppUtil.getSafeStr(order.getOrderType(), "");
                String requestStr = "", requestStyle = "style=color:red;";
                if (!requestType.equalsIgnoreCase("Refill")) {
                    if (order.getIsBottleAvailable() != null && order.getIsBottleAvailable()) {
                        requestType = "X-FER LABEL SCAN";
                        requestStr = "RX  LABEL";
                        requestStyle = "style=color:blue;";
                    } else {
                        requestType = "X-FER RX SCAN";
                        requestStr = "PAPER RX";
                        requestStyle = "style=color:green;";
                    }
                }
                detailDTO.setRequestType("<span " + requestStyle + ">" + requestType + "</span>");
//                detailDTO.setRequestStr(requestStr);
                listOfOrderDetail.add(detailDTO);
            }
            DataTablesTO<OrderDetailDTO> dataTableTo = new DataTablesTO<>();
            Integer totalDisplayCount = listOfOrderDetail.size();
            dataTableTo.setiTotalDisplayRecords(totalDisplayCount.longValue());
            if (!CommonUtil.isNullOrEmpty(listOfOrderDetail)) {
                listOfOrderDetail = listOfOrderDetail.subList(iDisplayStart, Math.min(listOfOrderDetail.size(), iDisplayStart + 10));
            }
            dataTableTo.setAaData(listOfOrderDetail);
            dataTableTo.setsEcho(sEcho);
            json = CommonUtil.toJson(dataTableTo);
        } catch (Exception e) {
            logger.error("Exception: getProcessedOrdersByPatientId:: ", e);
        }
        return json;
    }
}
