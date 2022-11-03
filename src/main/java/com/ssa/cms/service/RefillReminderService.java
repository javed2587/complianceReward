/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.service;

import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.common.Constants;
import com.ssa.cms.dao.RefillReminderDAO;
import com.ssa.cms.dao.PatientProfileDAO;
import com.ssa.cms.dto.OrdersDTO;
import com.ssa.cms.enums.EventTitleEnum;
import com.ssa.cms.enums.PlaceholderEnum;
import com.ssa.cms.enums.ResponseTitleEnum;
import com.ssa.cms.model.*;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.PropertiesUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RefillReminderService {

    @Autowired
    private RefillReminderDAO refillReminderDAO;

    @Autowired
    private PatientProfileDAO patientProfileDAO;

    @Autowired
    private PatientProfileService patientProfileService;
    private final org.apache.log4j.Logger success = org.apache.log4j.Logger.getLogger("successRefillReminder");
    private final org.apache.log4j.Logger failed = org.apache.log4j.Logger.getLogger("failedRefillReminder");
    private final Log logger = LogFactory.getLog(getClass());

    public RefillReminderDAO getRefillReminderDAO() {
        return refillReminderDAO;
    }

    public void setRefillReminderDAO(RefillReminderDAO refillReminderDAO) {
        this.refillReminderDAO = refillReminderDAO;
    }

    public OptInOut getTextOptInOut(int campaignId, String phoneNumber) {
        OptInOut optInOut = null;
        try {
            optInOut = refillReminderDAO.getTextOptInOut(campaignId, phoneNumber);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getTextOptInOut", e);
        }
        return optInOut;
    }

    public void save(Object bean) {
        try {
            refillReminderDAO.save(bean);
        } catch (Exception ex) {
            logger.error("Exception: RefillReminderService -> save", ex);
        }
    }

    public void update(Object bean) {
        try {
            refillReminderDAO.update(bean);
        } catch (Exception ex) {
            logger.error("Exception: RefillReminderService -> update", ex);
        }
    }

    public void delete(Object bean) {
        try {
            refillReminderDAO.delete(bean);
        } catch (Exception ex) {
            logger.error("Exception: RefillReminderService -> delete", ex);
        }
    }

    public List<Campaigns> getAllRefillCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            list = refillReminderDAO.getAllRefillCandidateActiveCampaign();
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getAllRefillCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<Campaigns> getAllRepeatRefillCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            list = refillReminderDAO.getAllRepeatRefillCandidateActiveCampaign();
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getAllRepeatRefillCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<Campaigns> getAllRefillFailureCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            list = refillReminderDAO.getAllRefillFailureCandidateActiveCampaign();
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getAllRefillFailureCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<Campaigns> getAllRefillOrderReminderCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            list = refillReminderDAO.getAllRefillOrderReminderCandidateActiveCampaign();
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getAllRefillOrderReminderCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<Campaigns> getAllPACandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            list = refillReminderDAO.getAllPACandidateActiveCampaign();
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getAllPACandidateActiveCampaign", e);
        }
        return list;
    }

    public List<DailyRedemption> getDRFRefillReminderRecordsByCampaignId(int campaignId, int intervalValue) {
        return refillReminderDAO.getDRFRefillReminderRecordsByCampaignId(campaignId, intervalValue);
    }

    public List<EventHasFolderHasCampaigns> getEventHasFolderHasCampaign(int campaignId) {
        List<EventHasFolderHasCampaigns> list = new ArrayList<>();
        try {
            list = refillReminderDAO.getEventHasFolderHasCampaign(campaignId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getEventHasFolderHasCampaign", e);
        }
        return list;
    }

    public List<EventHasFolderHasCampaigns> getEventHasFolderHasCampaign(int campaignId, String dynamicValue) {
        List<EventHasFolderHasCampaigns> list = new ArrayList<>();
        try {
            list = refillReminderDAO.getEventHasFolderHasCampaign(campaignId, dynamicValue);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getEventHasFolderHasCampaign", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId) {
        List<CampaignMessages> list = null;
        try {
            list = refillReminderDAO.getCampaignMessages(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessages", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessagesByResponseTitle(int campaignId, int folderId) {
        List<CampaignMessages> list = null;
        try {
            list = refillReminderDAO.getCampaignMessagesByResponseTitle(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessagesByResponseTitle", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId, int messageTypeId) {
        List<CampaignMessages> list = null;
        try {
            list = refillReminderDAO.getCampaignMessages(campaignId, folderId, messageTypeId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessages", e);
        }
        return list;
    }

    public CustomerRequest getCustomerRequestIPorCM(String phoneNumber) {
        CustomerRequest customerRequest = null;
        try {
            customerRequest = refillReminderDAO.getCustomerRequestIPorCM(phoneNumber);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCustomerRequestIPorCM", e);
        }
        return customerRequest;
    }

    public OptInOut checkOptInOut(String phoneNumber, int campaignId, int shortCode) {
        OptInOut optInOut = null;
        try {
            optInOut = refillReminderDAO.checkOptInOut(phoneNumber, campaignId, shortCode);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> checkOptInOut", e);
        }
        return optInOut;
    }

    public CampaignMessagesResponse getCampaignMessagesResponse(int campaignId, int folderId, int messageTypeId) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponse(campaignId, folderId, messageTypeId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessagesResponse", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessagesResponse getCampaignMessagesResponse(int campaignId, int folderId, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponse(campaignId, folderId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessagesResponse", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessagesResponse getCampaignMessagesResponsebyCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            if (communicationPath.equalsIgnoreCase("T")) {
                communicationPath = Constants.SMS;
            }
            campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessagesResponsebyCommunicationPath", e);
        }
        return campaignMessagesResponse;
    }

    public OptInOut getOptInOut(long crSeqNo) {
        OptInOut optInOut = null;
        try {
            optInOut = refillReminderDAO.getOptInOut(crSeqNo);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getOptInOut", e);
        }
        return optInOut;
    }

    public boolean redemptionExistsInIRF(DailyRedemption drf, int daysBack, int campaignId) {
        boolean redemptionExists = false;
        try {
            redemptionExists = refillReminderDAO.redemptionExistsInIRF(drf, daysBack, campaignId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> redemptionExistsInIRF", e);
        }
        return redemptionExists;
    }

    public List<RefillReminderPOJO> getTextRepeatRefillRecords(int campId) {
        List<RefillReminderPOJO> list = new ArrayList<>();
        try {
            list = refillReminderDAO.getTextRepeatRefillRecords(campId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getTextRepeatRefillRecords", e);
        }
        return list;
    }

    public List<RefillReminderPOJO> getTextRefill2Records(int campId) {
        List<RefillReminderPOJO> list = new ArrayList<>();
        try {
            list = refillReminderDAO.getTextRefill2Records(campId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getTextRefill2Records", e);
        }
        return list;
    }

    public List<RefillReminderPOJO> getEmailRepeatRefillRecords(int campId) {
        List<RefillReminderPOJO> list = new ArrayList<>();
        try {
            list = refillReminderDAO.getEmailRepeatRefillRecords(campId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getEmailRepeatRefillRecords", e);
        }
        return list;
    }

    public DailyRedemption getDailyRedemptionDetailByRedemptionId(int redemptionId) {
        DailyRedemption drf = null;
        try {
            drf = refillReminderDAO.getDailyRedemptionDetailByRedemptionId(redemptionId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getDailyRedemptionDetailByRedemptionId", e);
        }
        return drf;
    }

    public InstantRedemption getInstantRedemptionDetailByRedemptionId(int redemptionId) {
        InstantRedemption irf = null;
        try {
            irf = refillReminderDAO.getInstantRedemptionDetailByRedemptionId(redemptionId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getInstantRedemptionDetailByRedemptionId", e);
        }
        return irf;
    }

    public Campaigns getCampaignsById(Integer campaignsId) {
        Campaigns campaigns = null;
        try {
            campaigns = refillReminderDAO.getCampaignsById(campaignsId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignsById", e);
        }
        return campaigns;
    }

    public boolean isPatientEligibleForReminder(int messageRequestId, long secondsDelay) {
        boolean isPatientEligibleForReminder = false;
        try {
            isPatientEligibleForReminder = refillReminderDAO.isPatientEligibleForReminder(messageRequestId, secondsDelay);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> isPatientEligibleForReminder", e);
        }
        return isPatientEligibleForReminder;
    }

    public GatewayInfo getGatewayInfo(int shortCode) {
        GatewayInfo gatewayInfo = null;
        try {
            gatewayInfo = refillReminderDAO.getGatewayInfo(shortCode);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getGatewayInfo", e);
        }
        return gatewayInfo;
    }

    public CustomerRequest getCustomerRequestIP(String phoneNumber) {
        CustomerRequest customerRequest = null;
        try {
            customerRequest = refillReminderDAO.getCustomerRequestIP(phoneNumber);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCustomerRequestIP", e);
        }
        return customerRequest;
    }

    public ValidResponse getValidResponse(String validWord) {
        ValidResponse validResponse = null;
        try {
            validResponse = refillReminderDAO.getValidResponse(validWord);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getValidResponse", e);
        }
        return validResponse;
    }

    public AggregatorMessageRequest getAggregatorMessageRequestById(int messageReqNo) {
        AggregatorMessageRequest aggregatorMessageRequest = null;
        try {
            aggregatorMessageRequest = refillReminderDAO.getAggregatorMessageRequestById(messageReqNo);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getAggregatorMessageRequestById", e);
        }
        return aggregatorMessageRequest;
    }

    public AggregatorMessageRequest getRefillReminderAggregatorMessageRequestByRedemptionId(int redemptionId) {
        AggregatorMessageRequest aggregatorMessageRequest = null;
        try {
            aggregatorMessageRequest = refillReminderDAO.getRefillReminderAggregatorMessageRequestByRedemptionId(redemptionId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getRefillReminderAggregatorMessageRequestByRedemptionId", e);
        }
        return aggregatorMessageRequest;
    }

    public RxMMSRedemptionReqRes getRxMMSRedemptionReqResById(int messageReqNo) {
        RxMMSRedemptionReqRes rxMMSRedemptionReqRes = null;
        try {
            rxMMSRedemptionReqRes = refillReminderDAO.getRxMMSRedemptionReqResById(messageReqNo);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getRxMMSRedemptionReqResById", e);
        }
        return rxMMSRedemptionReqRes;
    }

    public void populateNpiValues(InstantRedemption irf) {
        try {
            refillReminderDAO.populateNpiValues(irf);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> populateNpiValues", e);
        }
    }

    public Drug getProductByNdc(String ndcNo) {
        Drug drug = null;
        try {
            drug = refillReminderDAO.getProductByNdc(ndcNo);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getProductByNdc", e);
        }
        return drug;
    }

    public int retrieveMessageCountByTypeForRedemption(AggregatorMessageRequest aggregatorMessageRequest) {
        int count = 0;
        try {
            count = refillReminderDAO.retrieveMessageCountByTypeForRedemption(aggregatorMessageRequest);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> retrieveMessageCountByTypeForRedemption", e);
        }
        return count;
    }

    public String getURL(String urlCode) {
        String urlString = "";
        try {
            urlString = refillReminderDAO.getURL(urlCode);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getURL", e);
        }
        return urlString;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByResponseName(int campaignId, int folderId, String responseTitle) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponseByResponseName(campaignId, folderId, responseTitle);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessagesResponseByResponseName", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignTrigger getTriggerByCampaignId(int campaignId) {
        CampaignTrigger trigger = null;
        try {
            trigger = refillReminderDAO.getTriggerByCampaignId(campaignId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getTriggerByCampaignId", e);
        }
        return trigger;
    }

    public Intervals getIntervalById(int intervalId) {
        Intervals interval = null;
        try {
            interval = refillReminderDAO.getIntervalById(intervalId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getIntervalById", e);
        }
        return interval;
    }

    public int markAggregatorMessageRequestEndDate(int messageReqNo) {
        int count = 0;
        try {
            count = refillReminderDAO.markAggregatorMessageRequestEndDate(messageReqNo);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> markAggregatorMessageRequestEndDate", e);
        }
        return count;
    }

    public int markRARYESReceivedEndDate(int redemptionId) {
        int count = 0;
        try {
            count = refillReminderDAO.markRARYESReceivedEndDate(redemptionId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> markRARYESReceivedEndDate", e);
        }
        return count;
    }

    public int sentEmailCountRefill(String email, int campaignId, int folderId, int messageTypeid) {
        int count = 0;
        try {
            count = refillReminderDAO.sentEmailCountRefill(email, campaignId, folderId, messageTypeid);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> sentEmailCountRefill", e);
        }
        return count;
    }

    public int sentEmailCountPAPending(String email, int campaignId, int folderId, int messageTypeid) {
        int count = 0;
        try {
            count = refillReminderDAO.sentEmailCountPAPending(email, campaignId, folderId, messageTypeid);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> sentEmailCountPAPending", e);
        }
        return count;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByResComm(int campaignId, int folderId, String responseTitle, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponseByResComm(campaignId, folderId, responseTitle, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessagesResponseByResComm", e);
        }
        return campaignMessagesResponse;
    }

    public List<CampaignMessages> getCampaignMessagesByCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        List<CampaignMessages> list = null;
        try {
            if (communicationPath.equalsIgnoreCase("T")) {
                communicationPath = Constants.SMS;
            }
            list = refillReminderDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getCampaignMessagesByCommunicationPath", e);
        }
        return list;
    }

    public String getEventsDescription(int campaignId, int folderId) {
        String events = "";
        try {
            events = refillReminderDAO.getEventsDescription(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getEventsDescription", e);
        }
        return events;
    }

    public int sentMMSCountCampaign(String phoneNumber, int campaignId, int folderId, int messageTypeid) {
        int count = 0;
        try {
            count = refillReminderDAO.sentMMSCountCampaign(phoneNumber, campaignId, folderId, messageTypeid);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> sentMMSCountCampaign", e);
        }
        return count;
    }

    public Object findById(Class clazz, Integer id) {
        Object o = null;
        try {
            o = refillReminderDAO.findById(clazz, id);
        } catch (Exception ex) {
            logger.error("Exception: RefillReminderService -> findById", ex);
        }
        return o;
    }

    public List<EventDetail> getEventDetail(String eventsIds, String dataSet) {
        List<EventDetail> list = null;
        try {
            list = refillReminderDAO.getEventDetail(eventsIds, dataSet);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getEventDetail", e);
        }
        return list;
    }

    public Order getOrderDetailByTransactionNumber(String cardHolderFirstName, String cardHolderLastname, Integer campaignId, Date cardHolderDOB) {
        Order order = null;
        try {
            logger.info("CardHolderFirstName : " + cardHolderFirstName + " CardHolderLastname: " + cardHolderLastname + " CampaignId: " + campaignId + " CardHolderDOB: " + cardHolderDOB);
            String transactionNumber = refillReminderDAO.getDailyRedemptions(cardHolderFirstName, cardHolderLastname, campaignId, cardHolderDOB);
            logger.info("Travsction number is :" + transactionNumber);
            List<Order> list = refillReminderDAO.getOrderByTransactionNumber(transactionNumber);
            if (!list.isEmpty()) {
                order = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getOrderDetailByTransactionNumber", e);
        }
        return order;
    }

    public boolean isEventDetailVerifiedForDailyRedemption(List<EventDetail> details, DailyRedemptionId drfId) {
        boolean flag = false;
        flag = refillReminderDAO.isEventDetailVerifiedForDailyRedemption(details, drfId);
        return flag;
    }

    public boolean isMmsExists(int campaignId, int folderId) {
        boolean flag = false;
        try {
            flag = refillReminderDAO.isMmsExists(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> isMmsExists", e);
        }
        return flag;
    }

    public MessagePriority getMessagePriority(String phoneNumber, int shortCode) {
        MessagePriority messagePriority = null;
        try {
            messagePriority = refillReminderDAO.getMessagePriority(phoneNumber, shortCode);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getMessagePriority", e);
        }

        return messagePriority;
    }

    public List<InstantRedemption> getDRFInLast30Mints(int campaignId) {
        List<InstantRedemption> list = null;
        try {
            list = refillReminderDAO.getDRFInLast30Mints(campaignId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getDRFInLast30Mints", e);
        }

        return list;
    }

    public int getPreviousRedemptionId(InstantRedemption instantRedemption) {
        int redemptionId = 0;
        try {
            redemptionId = refillReminderDAO.getPreviousRedemptionId(instantRedemption);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getPreviousRedemptionId", e);
        }

        return redemptionId;
    }

    public AggregatorMessageRequest getLastestAggregatorMessageRequest(int redemptionId) {
        AggregatorMessageRequest aggregatorMessageRequest = null;
        try {
            aggregatorMessageRequest = refillReminderDAO.getLastestAggregatorMessageRequest(redemptionId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getLastestAggregatorMessageRequest", e);
        }

        return aggregatorMessageRequest;
    }

    public AggregatorMessageRequest getRefillSuccessfullMessage(int redemptionId) {
        AggregatorMessageRequest aggregatorMessageRequest = null;
        try {
            aggregatorMessageRequest = refillReminderDAO.getRefillSuccessfullMessage(redemptionId);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getRefillSuccessfullMessage", e);
        }

        return aggregatorMessageRequest;
    }

    public String getDrugName(String ndc) {
        String drugName = null;
        try {
            drugName = refillReminderDAO.getDrugName(ndc);
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getDrugName", e);
        }

        return drugName;
    }

    public Order getOrderByTransactionNumber(String transactionNumber) {
        Order order = null;
        try {
            List<Order> list = refillReminderDAO.getOrderByTransactionNumber(transactionNumber);
            if (!list.isEmpty()) {
                order = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: RefillReminderService -> getOrderByTransactionNumber", e);
        }
        return order;
    }

    public boolean sendNextRefillReminder(Date date, Logger log, Logger successed, Logger failed) {
        boolean nextRefillflag = false;

        try {
            List<String> orderStatusList = new ArrayList<>();
//            orderStatusList.add(Constants.ORDER_STATUS.DELIVERY);
//            orderStatusList.add(Constants.ORDER_STATUS.PICKUP_AT_PHARMACY);
//            orderStatusList.add(Constants.ORDER_STATUS.SHIPPED);
            orderStatusList.add(Constants.ORDER_STATUS.FILLED);

            //get Reider list for 2 days.
            List<Order> orders = patientProfileDAO.getRefillReminderOrdersList(date, orderStatusList);
            log.info("Total Orders " + orders.size());
            if (CommonUtil.isNullOrEmpty(orders)) {
                log.info("There are no orders available which need next refill reminder (System will return).");
                return nextRefillflag;
            }

            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(Constants.MSG_CONTEXT_REFILL, Constants.COMPLIANCE_REWARD);
            if (campign == null || CommonUtil.isNullOrEmpty(campign.getMessageId())) {
                log.info("CampaignMessages is null (System will return).");
                return nextRefillflag;
            }

            String orderId = "";
            for (Order order : orders) {
                boolean successReminder = true;
                try {
                    log.info("OrderId= "+order.getId());
                    if (order.getId().equals(orderId)) {
                        log.info(Constants.MSG_CONTEXT_REFILL + " already send against this order " + order.getId());
                        continue;
                    }
                    orderId = order.getId();
                    List<BusinessObject> buisnessObjectLst = new ArrayList<>();
                    BusinessObject bo = new BusinessObject("orders.id", order.getId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);
                    bo = new BusinessObject("messageType.id.messageTypeId", campign.getMessageType().getId().getMessageTypeId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);

                    List<NotificationMessages> isNotificationMessagesesExist = patientProfileDAO.findByNestedProperty(new NotificationMessages(), buisnessObjectLst, null, 0);
                    if (!CommonUtil.isNullOrEmpty(isNotificationMessagesesExist)) {
                        ///here reminder will be add.
                        log.info(Constants.MSG_CONTEXT_REFILL + " already send against this order " + order.getId());
                        continue;
                    }
                    Practices dbPractise = patientProfileDAO.getPractiseNameById(order.getPatientProfile().getPracticeId());
                    String pharmacyComments = "", genericName = "";
                    if (order.getDrugDetail2() != null && order.getDrugDetail2().getDrugId() != null) {
                        genericName = order.getDrugDetail2().getGenericName();
                    }
                    campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
                    if (CommonUtil.isNotEmpty(campign.getSmstext())) {
                        //Update order
                        order.setNextRefillFlag("1");
                        order.setLastReminderDate(new Date());
                        order.setRefillReinderCount(1);
                        patientProfileDAO.saveOrUpdate(order);
                        NotificationMessages nf = new NotificationMessages();
                        nf.setCreatedBy("Auto");
                        nf.setUpdatedOn(new Date());
                        patientProfileDAO.saveOrUpdate(nf);
                        //Send refill reminder message
                        RewardPoints rewardPoints = (RewardPoints) patientProfileDAO.findByPropertyUnique(new RewardPoints(), "type", "Rx Refill", Constants.HIBERNATE_EQ_OPERATOR);
                        CampaignMessages campaignMessages = replaceRefillMsgPlaceHolders(campign, order, genericName, rewardPoints, pharmacyComments);
                        String subject = patientProfileService.getMessageSubjectWithprocessedPlaceHolders(campaignMessages.getSubject(), order.getId(), dbPractise);
                        campaignMessages.setSubject(subject);
                        String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
                        campaignMessages.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(pushNot, order.getId(), dbPractise));
                        patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, order.getPatientProfile().getPatientProfileSeqNo(), order,
                                campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
                        nextRefillflag = true;

                    } else {
                        log.info("Msg test is empty.");
                        nextRefillflag = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    failed.error("order Id =" + order.getId() + " refill reminder is failed due to " + e);
                    log.error("Exception: Next refill reminder -> ", e);
                    successReminder = false;
                }
                if (successReminder) {
                    successed.info("Successfully send next refill reminder to Order Id=" + order.getId());
                    log.info("Successfully send next refill reminder to Order Id=" + order.getId());
                }
            }
            return nextRefillflag;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception: Next refill reminder -> ", e);
            return nextRefillflag;
        }
    }

    public CampaignMessages replaceRefillMsgPlaceHolders(CampaignMessages campign, Order order, String genericName, RewardPoints rewardPoints, String pharmacyComments) throws Exception {
        CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(campign);
        Practices dbPractise = patientProfileDAO.getPractiseNameById(order.getPracticeId());
        String brandRefrance = "", rxLabelName = "", genricOrBrand = "", strength = "", orderStatus = "", daysTillRefill = "", imgAssistAuth = "";
//            long daysTillRefill;
        if (order.getOrderStatus() != null) {
            orderStatus = order.getOrderStatus().getName();
        }
        String filledDate = "", expiryDate = "", assistAuth = "", titleAssistAuth = "";
        if (order.getRxProcessedAt() == null) {
            filledDate = DateUtil.dateToString(order.getUpdatedAt(), Constants.USA_DATE_FORMATE);
        } else {
            filledDate = DateUtil.dateToString(order.getRxProcessedAt(), Constants.USA_DATE_FORMATE);
        }
        Date nextfilleDte = order.getNextRefillDate();
        if (filledDate != null) {
//                Date remaingDays = DateUtil.addDays(filleDte, order.getDaysSupply());
            Date today = new Date();
            long dayDiff = DateUtil.dateDiffInDays(today, nextfilleDte);
            System.out.println("DAYS DIFF " + dayDiff + " ID " + order.getId());
            if (dayDiff >= 0) {
                daysTillRefill = Long.toString(dayDiff);
            } else {
                daysTillRefill = Long.toString(dayDiff);
            }
        }
        if (order.getRxExpiredDate() != null) {
            expiryDate = DateUtil.dateToString(order.getRxExpiredDate(), Constants.USA_DATE_FORMATE);
        }
        if (order.getRxPatientOutOfPocket() != 0d) {
             assistAuth = CommonUtil.getDecimalFormat(order.getRxPatientOutOfPocket());
//               String val = String.valueOf(order.getRxPatientOutOfPocket()) ; 
//               assistAuth  = String.format("%.2f", val);
            titleAssistAuth = "STATEMENT CREDIT AUTHORIZED : ";
            imgAssistAuth = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "vw.png";
        }
        if (order.getDrugDetail2() != null) {
            strength = CommonUtil.isNullOrEmpty(order.getStrength()) || order.getStrength().equals(order.getDrugDetail2().getDrugId().toString()) ? order.getDrugDetail2().getStrength() : order.getStrength();
            logger.info("strength :" + strength);
            brandRefrance = order.getDrugDetail2().getBrandReference();
            rxLabelName = order.getDrugDetail2().getRxLabelName();
            if ("G".equalsIgnoreCase(order.getDrugDetail2().getGenericOrBrand())) {
                if (!CommonUtil.isNullOrEmpty(order.getDrugDetail2().getBrandReference())) {
                    genricOrBrand = order.getDrugDetail2().getBrandReference();
                } else {
                    genricOrBrand = "Not Applicable";
                }
            } else {
                genricOrBrand = "Brand Name Only";
            }
            logger.info("\"G\".equalsIgnoreCase(order.getDrugDetail2().getGenericOrBrand()->genricOrBrand :" + genricOrBrand);
        }
        campaignMessages.setSmstext(campign.getSmstext()
                .replace(PlaceholderEnum.DATE.getValue(), DateUtil.dateToString(new Date(), Constants.DD_MM_YYYY))
                .replace(PlaceholderEnum.TIME.getValue(), DateUtil.dateToString(new Date(), Constants.TIME_HH_MM))
                .replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"))
//                .replace(PlaceholderEnum.LAST_FILLED.getValue(), filledDate != null ? filledDate : "null")
//                .replace(PlaceholderEnum.RX_EXPIRY_DATE.getValue(), expiryDate != null ? expiryDate : "null")
//                .replace(PlaceholderEnum.PRACTISE_NAME.getValue(), dbPractise.getPracticename())
//                .replace(PlaceholderEnum.DRUG_NAME.getValue(), rxLabelName)
////                .replace(PlaceholderEnum.DRUG_STRENGTH.getValue(), order.getStrength())
//                .replace(PlaceholderEnum.DRUG_TYPE.getValue(), order.getDrugType())
//                .replace(PlaceholderEnum.DRUG_QTY.getValue(), order.getQty())
//                .replace(PlaceholderEnum.GeNERIC_ORBRAND_NAME.getValue(), genricOrBrand)
//                .replace(PlaceholderEnum.BRAND_REFRANCE.getValue(), brandRefrance)
//                .replace(PlaceholderEnum.DAYS_TO_REFILL.getValue(), daysTillRefill)
//                .replace(PlaceholderEnum.ORDER_STATUS.getValue(), CommonUtil.isNullOrEmpty(orderStatus) ? " " : orderStatus)
//                .replace(PlaceholderEnum.GeNERIC_NAME.getValue(), genericName)
//                .replace(PlaceholderEnum.RX_PATIENT_OUT_OF_POCKET.getValue(), AppUtil.getSafeStr(order.getRxPatientOutOfPocket().toString(), "0"))
//                .replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "MM/dd/YYYY hh:mm a"))
//                .replace(PlaceholderEnum.REQUEST_NO.getValue(), order.getId())
//                .replace(PlaceholderEnum.REFILL_POINTS.getValue(), rewardPoints != null && rewardPoints.getId() != null ? rewardPoints.getPoint().intValueExact() + "" : "0")
//                .replace(PlaceholderEnum.PHARMACY_COMMENTS.getValue(), CommonUtil.isNotEmpty(pharmacyComments) ? pharmacyComments : "")
//                .replace(PlaceholderEnum.RX_NUMBER.getValue(), AppUtil.getSafeStr(order.getRxNumber(), ""))
//                .replace(PlaceholderEnum.DAYS_SUPPLY.getValue(), order.getDaysSupply() != null ? order.getDaysSupply().toString() : "0")
//                .replace(PlaceholderEnum.REFILL_REMAINING.getValue(), order.getRefillsRemaining() != null ? order.getRefillsRemaining().toString() : "0")
//                .replace(PlaceholderEnum.TITLE_ASSIST_AUTH.getValue(), !"0.00".equals(titleAssistAuth) ? titleAssistAuth : "")
//                .replace(PlaceholderEnum.VAL_ASSIST_AUTH.getValue(), !"0.00".equals(assistAuth) ? assistAuth : "")
//                .replace(PlaceholderEnum.IMG_ASSIST_AUTH.getValue(), !"0.00".equals(imgAssistAuth) ? imgAssistAuth : "")
//                .replace(PlaceholderEnum.PATIENT_OUT_OF_POCKET.getValue(), AppUtil.getSafeStr(order.getRxOutOfPocket().toString(), "0"))
        );
        return campaignMessages;
    }

    public boolean sendEndOfTheYearJob() {
        boolean nextRefillflag = false;
        try {

            List<PatientProfile> patientProfilesList = patientProfileDAO.findByNestedProperty(new PatientProfile(), "Status", Constants.COMPLETED, Constants.HIBERNATE_EQ_OPERATOR, null, 0);
            if (CommonUtil.isNullOrEmpty(patientProfilesList)) {
                logger.info("There are no orders availabe which need next refill reminder.");
                return nextRefillflag;
            }
            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(Constants.END_OF_THE_YEAR, Constants.Pharmacy_Notification);
            for (PatientProfile patientProfile : patientProfilesList) {
                List<PatientInsuranceDetails> patientInsuranceDetailsList = patientProfileDAO.findByNestedProperty(new PatientInsuranceDetails(), "patientProfile.id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR, null, 0);

                if (!CommonUtil.isNullOrEmpty(patientInsuranceDetailsList)) {

                    if (campign != null && campign.getMessageId() != null) {
                        if (CommonUtil.isNotEmpty(campign.getSmstext())) {
                            patientProfileService.saveNotificationMessages(campign, Constants.NO, patientProfile.getPatientProfileSeqNo());
                            nextRefillflag = true;
                        } else {
                            logger.info("Msg test is empty.");
                            nextRefillflag = false;
                        }
                        return nextRefillflag;
                    }
                } else {
                    logger.info("Patient Insurance detail is null.");
                    nextRefillflag = false;
                }
            }
            return nextRefillflag;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: Next refill reminder -> ", e);
            return nextRefillflag;
        }
    }

    public boolean sendAnnualStatements() {
        boolean nextAnnualflag = false;
        try {
            String currentYear = DateUtil.dateToString(new Date(), "yyyy");
            logger.info("Current Year is:: " + currentYear);
            List<String> orderStatusList = new ArrayList<>();
            orderStatusList.add(Constants.ORDER_STATUS.DELIVERY);
            orderStatusList.add(Constants.ORDER_STATUS.SHIPPED);
            List<Order> ordersList = patientProfileDAO.getOrdersForAnnualStatement(Integer.parseInt(currentYear), orderStatusList);
            if (CommonUtil.isNullOrEmpty(ordersList)) {
                logger.info("Order list is empty.");
                return nextAnnualflag;
            }
            CampaignMessages campignMessages = this.patientProfileService.getNotificationMsgs(Constants.ANNUAL_STATEMENT, Constants.RX_ORDER);

            for (Order order : ordersList) {
                if (order.getPatientProfile() == null || order.getPatientProfile().getPatientProfileSeqNo() == null) {
                    logger.info("Patient Profile info is null.");
                    continue;
                }
                Long totalOrder = patientProfileDAO.getTotalOrders(orderStatusList, order.getPatientProfile().getPatientProfileSeqNo());
                logger.info("Total Orders:: " + totalOrder);
                if (campignMessages != null && campignMessages.getMessageId() != null) {
                    List<BusinessObject> bosList = new ArrayList<>();

                    BusinessObject bo = new BusinessObject("patientProfile.id", order.getPatientProfile().getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR);
                    bosList.add(bo);
                    bo = new BusinessObject("messageType.id.messageTypeId", campignMessages.getMessageType().getId().getMessageTypeId(), Constants.HIBERNATE_EQ_OPERATOR);
                    bosList.add(bo);
                    bo = new BusinessObject("subject", campignMessages.getSubject(), Constants.HIBERNATE_EQ_OPERATOR);
                    bosList.add(bo);

                    List<NotificationMessages> isNotificationMessagesesExist = patientProfileDAO.findByNestedProperty(new NotificationMessages(), bosList, null, 0);
                    if (!CommonUtil.isNullOrEmpty(isNotificationMessagesesExist)) {
                        logger.info(Constants.ANNUAL_STATEMENT + " already send against this order " + order.getId());
                        continue;
                    }

                    String message = campignMessages.getSmstext();
                    if (CommonUtil.isNotEmpty(message)) {
                        CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(campignMessages);
                        campaignMessages.setSmstext(message.replace("[date_time]", DateUtil.dateToString(new Date(), "MM/dd/YYYY hh:mm a"))
                                .replace("[YEAR]", currentYear)
                                .replace("[TOTAL_ORDER]", (totalOrder != null ? totalOrder.toString() : ""))
                                .replace("[patient_Id]", order.getPatientProfile().getPatientProfileSeqNo().toString()));
                        patientProfileService.saveNotificationMessages(campaignMessages, order.getPatientProfile().getPatientProfileSeqNo(), order.getId());
                        nextAnnualflag = true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception:: sendAnnualStatements ", e);
        }
        return nextAnnualflag;
    }

    public boolean sendRepeatRefillReminder(Logger log, Logger successed, Logger failed) {
//          public boolean sendRepeatRefillReminder() {
        boolean nextRefillflag = false;

        try {
            List<Object[]> ordList;
            ordList = patientProfileDAO.getRefillAbleOrderListForSecondReminder();
            if (!CommonUtil.isNullOrEmpty(ordList)) {
             nextRefillflag  = sendNotification(log, successed, failed, ordList, "Reapeat Reminder", "Reapeat Reminder");
//                  sendNotification(ordList, "Reapeat Reminder", "Reapeat Reminder");
                   log.info("..End sending 2 days Refil Reminder Msg.." + nextRefillflag);
            }
            ordList = patientProfileDAO.getRefillAbleOrderListForThirdReminder();
            if (!CommonUtil.isNullOrEmpty(ordList)) {
               nextRefillflag = sendNotification(log, successed, failed, ordList, "Reapeat Reminder after 5", "Reapeat Reminder");
//               sendNotification(ordList, "Reapeat Reminder after 5", "Reapeat Reminder");
                log.info("..End sending 5 days Refil Reminder Msg.." + nextRefillflag);
            }
            ordList = patientProfileDAO.getRefillAbleOrderListForFourtReminder();
            if (!CommonUtil.isNullOrEmpty(ordList)) {
              nextRefillflag =  sendNotification(log, successed, failed, ordList, "Reapeat Reminder after 10", "Reapeat Reminder");
//                sendNotification(ordList,  "Reapeat Reminder after 10", "Reapeat Reminder");
                 log.info("..End sending 10 days Refil Reminder Msg.." + nextRefillflag);
            }
        } catch (Exception e) {
            e.printStackTrace();
            nextRefillflag = false;
        }
        return nextRefillflag;
    }

    public boolean sendNotification(Logger log, Logger successed, Logger failed, List<Object[]> ordList, String validResponse, String event) throws Exception {
//         public boolean sendNotification(List<Object[]> ordList, String validResponse, String event) {
        boolean nextRefillflag = false;
        try {
            log.info("Total Orders " + ordList.size());
            System.out.println("Total Orders " + ordList.size());
            if (CommonUtil.isNullOrEmpty(ordList)) {
                log.info("There are no orders availabe which need next Second refill reminder (System will return).");
                return nextRefillflag;
            }

            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(validResponse, event);
            if (campign == null || CommonUtil.isNullOrEmpty(campign.getMessageId())) {
                log.info("CampaignMessages is null (System will return).");
                return nextRefillflag;
            }
            Order ord;// = new Order();
            String orderId = "";
            for (Object[] order : ordList) {
                boolean successReminder = true;
                try {

                    if (order[0].toString().equals(orderId)) {
                        logger.info(Constants.MSG_CONTEXT_REFILL + " already send against this order " + order[0].toString());
                        continue;
                    }
                    orderId = order[0].toString();
                    //////////////////////////
                    List<BusinessObject> buisnessObjectLst = new ArrayList<>();
                    BusinessObject bo = new BusinessObject("orders.id", orderId, Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);
                    bo = new BusinessObject("messageType.id.messageTypeId", campign.getMessageType().getId().getMessageTypeId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);

                    List<NotificationMessages> isNotificationMessagesesExist = patientProfileDAO.findByNestedProperty(new NotificationMessages(), buisnessObjectLst, null, 0);
                    if (!CommonUtil.isNullOrEmpty(isNotificationMessagesesExist)) {
                        logger.info(Constants.MSG_CONTEXT_REFILL + " already send against this order " + orderId);
                        continue;
                    }
                    ord = patientProfileService.getOrderById(orderId);
                    Practices dbPractise = patientProfileDAO.getPractiseNameById(ord.getPatientProfile().getPracticeId());
                    String pharmacyComments = "", genericName = "";
                    if (ord.getDrugDetail2() != null && ord.getDrugDetail2().getDrugId() != null) {
                        genericName = ord.getDrugDetail2().getGenericName();
                    }
                    campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
                    if (CommonUtil.isNotEmpty(campign.getSmstext())) {
                        ord.setNextRefillFlag("1");
                        ord.setRefillReinderCount(ord.getRefillReinderCount() + 1);
                        patientProfileDAO.saveOrUpdate(ord);
                        CampaignMessages campaignMessages = replaceRefillMsgPlaceHolders(campign, ord, genericName, null, pharmacyComments);
                        String subject = patientProfileService.getMessageSubjectWithprocessedPlaceHolders(campaignMessages.getSubject(), ord.getId(), dbPractise);
                        campaignMessages.setSubject(subject);
                        String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
                        campaignMessages.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(pushNot, ord.getId(), dbPractise));
                        patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, ord.getPatientProfile().getPatientProfileSeqNo(), ord,
                                campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
                        nextRefillflag = true;
                    } else {
                        log.info("Msg test is empty.");
                        nextRefillflag = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    failed.error("order Id =" + order[0].toString() + " refill reminder is failed due to " + e);
                    log.error("Exception: Next refill reminder -> ", e);
                    successReminder = false;
                }
                if (successReminder) {
                    successed.info("Successfully send next refill reminder to Order Id=" + order[0].toString());
                }
            }
            return nextRefillflag;
        } catch (Exception e) {
            log.error("Exception: Next refill reminder -> ", e);
            return nextRefillflag;
        }

    }

    public boolean sendRepeatRefillReminderForMBR(Logger log) {
        boolean nextRefillflag = false;

        try {
            int mbrFolderId = AppUtil.getSafeInt(PropertiesUtil.getProperty("MBR_Folder_Id"), 0);
            int mbrMsgTypeId = AppUtil.getSafeInt(PropertiesUtil.getProperty("MBR_Message_Type_Id"), 0);
            log.info("..Start sending First MBR Msg..");
            List<Object[]> mbrIstReminderList = patientProfileDAO.getMBROrderReminderList(Constants.ORDER_STATUS.MBR_TO_PAY_ID, 24, 48, mbrFolderId, mbrMsgTypeId);
            nextRefillflag = sendNotificationForMBR(mbrIstReminderList, ResponseTitleEnum.FIRST_MBR_REMINDER.getValue(), EventTitleEnum.Rx_Advisory.getValue(), log);
            log.info("..End sending First MBR Msg.." + nextRefillflag);

            log.info("..Start sending 2nd MBR Msg..");
            List<Object[]> mbr2ndReminderList = patientProfileDAO.getMBROrderReminderList(Constants.ORDER_STATUS.MBR_TO_PAY_ID, 48, 72, mbrFolderId, mbrMsgTypeId);
            nextRefillflag = sendNotificationForMBR(mbr2ndReminderList, ResponseTitleEnum.SECOND_MBR_REMINDER.getValue(), EventTitleEnum.Rx_Advisory.getValue(), log);
            log.info("..End sending 2nd MBR Msg.." + nextRefillflag);

            log.info("..Start sending 3rd MBR Msg..");
            List<Object[]> mbr3rdReminderList = patientProfileDAO.getMBROrderReminderList(Constants.ORDER_STATUS.MBR_TO_PAY_ID, 72, 96, mbrFolderId, mbrMsgTypeId);
            nextRefillflag = sendNotificationForMBR(mbr3rdReminderList, ResponseTitleEnum.THIRD_MBR_REMINDER.getValue(), EventTitleEnum.Rx_Advisory.getValue(), log);
            log.info("..End sending 3rd MBR Msg.." + nextRefillflag);
            
            log.info("..Start Process Auto Cancel Mbr Order  after 3rd reminder...");
            List<Object[]> mbrAfter3rdReminderList = patientProfileDAO.getMBROrderReminderList(Constants.ORDER_STATUS.MBR_TO_PAY_ID, 96, 120, mbrFolderId, mbrMsgTypeId);
            for (Object[] orderList : mbrAfter3rdReminderList) {
                NotificationMessages dbNotificationMessge = patientProfileDAO.getNotificationMessageByOrdrIdAndMsgTypeId(orderList[0].toString());
                if (dbNotificationMessge!=null) {
                    Order ord = patientProfileService.getOrderById(dbNotificationMessge.getOrders().getId());
                    if (ord.getOrderStatus().getId() == Constants.ORDER_STATUS.MBR_TO_PAY_ID) {
                        OrderStatus status = patientProfileDAO.getOrderStausById(Constants.ORDER_STATUS.CANCEL_ORDER_ID);
                        ord.setOrderStatus(status);
                        ord.setUpdatedOn(new Date());
                        patientProfileDAO.update(ord);
                        log.info("..Send Order cancel In-app Notification to user on Mbr Order  after 3rd reminder...");
                        patientProfileService.sendPushNotificationOnPlaceOrder(ord.getPatientProfile(), ord, ResponseTitleEnum.Order_Cancelled.getValue(), EventTitleEnum.Order_Cancel.getValue());
                    }
                }
            }
            log.info("..End Process Auto Cancel Mbr Order  after 3rd reminder...");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception# sendRepeatRefillReminderForMBR# ", e);
            nextRefillflag = false;
        }
        return nextRefillflag;
    }

    public boolean sendNotificationForMBR(List<Object[]> ordList, String validResponse, String eventName, Logger log) throws Exception {
        boolean nextRefillflag = false;
        try {
            log.info("Total " + validResponse + " Orders " + ordList.size());
            if (CommonUtil.isNullOrEmpty(ordList)) {
                log.info("There are no orders availabe for " + validResponse + " reminder (System will return).");
                return nextRefillflag;
            }

            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(validResponse, eventName);
            if (campign == null || CommonUtil.isNullOrEmpty(campign.getMessageId())) {
                log.info("CampaignMessages is null (System will return).");
                return nextRefillflag;
            }
            Order ord;// = new Order();
            String orderId = "";
            for (Object[] order : ordList) {
                try {

                    if (order[0].toString().equals(orderId)) {
                        log.info(validResponse + " already send against this order " + order[0].toString());
                        continue;
                    }
                    orderId = order[0].toString();
                    //////////////////////////
                    List<BusinessObject> buisnessObjectLst = new ArrayList<>();
                    BusinessObject bo = new BusinessObject("orders.id", orderId, Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);
                    bo = new BusinessObject("messageType.id.messageTypeId", campign.getMessageType().getId().getMessageTypeId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);

                    List<NotificationMessages> isNotificationMessagesesExist = patientProfileDAO.findByNestedProperty(new NotificationMessages(), buisnessObjectLst, null, 0);
                    if (!CommonUtil.isNullOrEmpty(isNotificationMessagesesExist)) {
                        log.info(validResponse + " already send against this order " + orderId);
                        continue;
                    }
                    ord = patientProfileService.getOrderById(orderId);
                    Practices dbPractise = patientProfileDAO.getPractiseNameById(ord.getPatientProfile().getPracticeId());
                    String pharmacyComments = "", genericName = "";
                    if (ord.getDrugDetail2() != null && ord.getDrugDetail2().getDrugId() != null) {
                        genericName = ord.getDrugDetail2().getGenericName();
                    }
                    campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
                    if (CommonUtil.isNotEmpty(campign.getSmstext())) {
                        ord.setMbrRemindercount(ord.getMbrRemindercount() + 1);
                        patientProfileDAO.saveOrUpdate(ord);
                        CampaignMessages campaignMessages = replaceRefillMsgPlaceHolders(campign, ord, genericName, null, pharmacyComments);
                        String subject = patientProfileService.getMessageSubjectWithprocessedPlaceHolders(campaignMessages.getSubject(), ord.getId(), dbPractise);
                        campaignMessages.setSubject(subject);
                        String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
                        campaignMessages.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(pushNot, ord.getId(), dbPractise));
                        patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, ord.getPatientProfile().getPatientProfileSeqNo(), ord,
                                campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
                        nextRefillflag = true;
                    } else {
                        log.info("Msg test is empty.");
                        nextRefillflag = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("Exception: MBR Reminder# ", e);
                    nextRefillflag = false;
                }
            }
            return nextRefillflag;
        } catch (Exception e) {
            log.error("Exception: MBR Reminder# ", e);
            return nextRefillflag;
        }

    }

    public boolean sendSurveyReminders(Logger log) {
        boolean isReminderSend = false;
        try {
            log.info("Start sending First survey reminders..");
            List<Object[]> IstReminderList = refillReminderDAO.getSurveyRemindersList(24, 72);
            isReminderSend = sendSurveyRemindersMsg(ResponseTitleEnum.FIRST_SURVEY_REMINDER.getValue(), EventTitleEnum.RX_REPORTER.getValue(), log, IstReminderList);
            log.info("End sending First survey reminders: " + isReminderSend);

            log.info("Start sending First survey reminders..");
            List<Object[]> secondReminderList = refillReminderDAO.getSurveyRemindersList(72, 96);
            isReminderSend = sendSurveyRemindersMsg(ResponseTitleEnum.SECOND_SURVEY_REMINDER.getValue(), EventTitleEnum.RX_REPORTER.getValue(), log, secondReminderList);
            log.info("End sending First survey reminders: " + isReminderSend);
        } catch (Exception e) {
            isReminderSend = false;
            log.error("Exception# sendSurveyReminders# ", e);
        }
        return isReminderSend;
    }

    private boolean sendSurveyRemindersMsg(String validResponse, String eventName, Logger log, List<Object[]> IstReminderList) {
        boolean isMsgSend = false;
        try {
            if (CommonUtil.isNullOrEmpty(IstReminderList)) {
                log.info("There are no survey availabe for " + validResponse + " (System will return).");
                return isMsgSend;
            }

            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(validResponse, eventName);
            if (campign == null || CommonUtil.isNullOrEmpty(campign.getMessageId())) {
                log.info("CampaignMessages is null (System will return).");
                return isMsgSend;
            }

            for (Object[] surveyLogs : IstReminderList) {
                  Long surveylogId = AppUtil.getSafeLong(surveyLogs[0].toString(), 0l);
                Long surveyId = AppUtil.getSafeLong(surveyLogs[1].toString(), 0l);
                int patientId = AppUtil.getSafeInt(surveyLogs[2].toString(), 0);
                log.info("PatientId is= " + patientId);
                Survey2 survey = (Survey2) refillReminderDAO.findRecordById(new Survey2(), surveyId);
                if (survey == null) {
                    log.info("No survey info exist against this Id: " + surveyId + " (System will return).");
                    continue;
                }
                  AssignedSurvey surveylog= patientProfileDAO.getSurveyLogsBySurveyLogId(patientId, surveylogId);
//                  AssignedSurvey surveylog = (AssignedSurvey) refillReminderDAO.findRecordById(new AssignedSurvey(), survey.getId());
                 if (surveylog == null) {
                    log.info("No surveylog info exist against this Id: " + survey.getId() + " (System will return).");
                    continue;
                }      
                List<BusinessObject> buisnessObjectLst = new ArrayList<>();
                BusinessObject bo = new BusinessObject("patientProfile.patientProfileSeqNo", patientId, Constants.HIBERNATE_EQ_OPERATOR);
                buisnessObjectLst.add(bo);
                bo = new BusinessObject("messageType.id.messageTypeId", campign.getMessageType().getId().getMessageTypeId(), Constants.HIBERNATE_EQ_OPERATOR);
                buisnessObjectLst.add(bo);
//                bo = new BusinessObject("patientProfile.patientProfileSeqNo", patientId, Constants.HIBERNATE_EQ_OPERATOR);
//                buisnessObjectLst.add(bo);
                
                List<NotificationMessages> isNotificationMessagesesExist = patientProfileDAO.findByNestedProperty(new NotificationMessages(), buisnessObjectLst, null, 0);
                if (!CommonUtil.isNullOrEmpty(isNotificationMessagesesExist)) {
                    log.info(validResponse + " already send against this survey " + surveyId);
                    continue;
                }
// campaignMessages.setSmstext(surveyIdd);
//            String push = AppUtil.getSafeStr(dbcampaignMessages.getPushNotification(), "");
//            campaignMessages.setPushNotification(push);
                if (CommonUtil.isNotEmpty(campign.getSmstext())) {
                    CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(campign);
//                    campaignMessages.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
//                   campaignMessages.setSmstext(surveylog.getId().toString());
                    String subject = campaignMessages.getSubject().replace(PlaceholderEnum.Survey_Title.getValue(), survey.getTitle());
                    campaignMessages.setSubject(subject);
                    patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, patientId, surveylog.getId(),
                    campaignMessages.getSmstext(), campaignMessages.getSubject());
                    isMsgSend = true;
                } else {
                    log.info("Msg test is empty.");
                    isMsgSend = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception# sendSurveyRemindersMsg# ", e);
            isMsgSend = false;
        }

        return isMsgSend;
    }
     public boolean sendRefillReminderByPharmacy(OrdersDTO body) {
        boolean nextRefillflag = false;

        try {
            List<String> orderStatusList = new ArrayList<>();
            orderStatusList.add(Constants.ORDER_STATUS.FILLED);
//            for(String ord : body.getOrderId()) {
                 for (String ord : body.listOfOrders) {               
//                   Order orders = patientProfileDAO.getRefillReminderOrders(new Date(), orderStatusList,ord);
//                   if (orders == null) {
//                      logger.info("There are no orders available "+ord+" which need next refill reminder (System will return).");
//                      return nextRefillflag;
//                 }
          Order orders = patientProfileDAO.getOrderDetailById(ord);
          CampaignMessages campign = this.patientProfileService.getNotificationMsgs(Constants.MSG_CONTEXT_REFILL, Constants.COMPLIANCE_REWARD);
            if (campign == null || CommonUtil.isNullOrEmpty(campign.getMessageId())) {
                logger.info("CampaignMessages is null (System will return).");
                return nextRefillflag;
                }
                String orderId = "";
                boolean successReminder = true;
                try {
                    logger.info("OrderId= "+orders.getId());
//                    if (orders.getId().equals(orderId)) {
//                        logger.info(Constants.MSG_CONTEXT_REFILL + " already send against this order " + orders.getId());
//                         return nextRefillflag;
//                    }
                    orderId = orders.getId();
                    List<BusinessObject> buisnessObjectLst = new ArrayList<>();
                    BusinessObject bo = new BusinessObject("orders.id", orders.getId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);
                    bo = new BusinessObject("messageType.id.messageTypeId", campign.getMessageType().getId().getMessageTypeId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);

                    List<NotificationMessages> isNotificationMessagesesExist = patientProfileDAO.findByNestedProperty(new NotificationMessages(), buisnessObjectLst, null, 0);
                    if (!CommonUtil.isNullOrEmpty(isNotificationMessagesesExist)) {
                        ///here reminder will be add.
                        logger.info(Constants.MSG_CONTEXT_REFILL + " already send against this order " + orders.getId());
                        continue;
                    }
                    Practices dbPractise = patientProfileDAO.getPractiseNameById(orders.getPatientProfile().getPracticeId());
                    String pharmacyComments = "", genericName = "";
                    if (orders.getDrugDetail2() != null && orders.getDrugDetail2().getDrugId() != null) {
                        genericName = orders.getDrugDetail2().getGenericName();
                    }
                    campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
                    if (CommonUtil.isNotEmpty(campign.getSmstext())) {
                        //Update order
//                        orders.setNextRefillFlag("1");
//                        orders.setLastReminderDate(new Date());
//                        orders.setRefillReinderCount(1);
//                        patientProfileDAO.saveOrUpdate(orders);
                        //Send refill reminder message
//                        RewardPoints rewardPoints = (RewardPoints) patientProfileDAO.findByPropertyUnique(new RewardPoints(), "type", "Rx Refill", Constants.HIBERNATE_EQ_OPERATOR);
                        CampaignMessages campaignMessages = replaceRefillMsgPlaceHolders(campign, orders, genericName, null, pharmacyComments);
                        String subject = patientProfileService.getMessageSubjectWithprocessedPlaceHolders(campaignMessages.getSubject(), orders.getId(), dbPractise);
                        campaignMessages.setSubject(subject);
                        String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
                        campaignMessages.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(pushNot, orders.getId(), dbPractise));
                          boolean isSend= patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, orders.getPatientProfile().getPatientProfileSeqNo(), orders,
                                campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
                       if(isSend==true){
                          orders.setNextRefillFlag("1");
                        orders.setLastReminderDate(new Date());
                        orders.setRefillReinderCount(1);
                        patientProfileDAO.saveOrUpdate(orders);
                          nextRefillflag = true;
                       }
                    } else {
                        logger.info("Msg test is empty.");
                        nextRefillflag = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    failed.error("order Id =" + orders.getId() + " refill reminder is failed due to " + e);
                    logger.error("Exception: Next refill reminder by sending Pharmacy-> ", e);
                    successReminder = false;
                }
                if (successReminder) {
                    success.info("Successfully send next refill reminder to Order Id=" + orders.getId()+ " By Pharmacy...");
                    logger.info("Successfully send next refill reminder to Order Id=" + orders.getId()+ " By Pharmacy...");
                }
             
            }
            return nextRefillflag;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: Next refill reminder by sending Pharmacy-> ", e);
            return nextRefillflag;
        }
    }
   public boolean sendRenewalRefillReminder(Date date, Logger log, Logger successed, Logger failed) {
        boolean nextRefillflag = false;
        try {           
            List<Order> orders = patientProfileDAO.getOrdersDetialForRenewal(date);
            log.info("Total Orders " + orders.size());
            if (CommonUtil.isNullOrEmpty(orders)) {
                log.info("There are no orders available which need Renewal refill reminder (System will return).");
                return nextRefillflag;
            }

            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(Constants.MSG_CONTEXT_RENEWAL, Constants.MSG_CONTEXT_RENEWAL_REMINDER);
            if (campign == null || CommonUtil.isNullOrEmpty(campign.getMessageId())) {
                log.info("CampaignMessages is null (System will return).");
                return nextRefillflag;
            }

            String orderId = "";
            for (Order order : orders) {
                boolean successReminder = true;
                try {
                    log.info("OrderId= "+order.getId());
                    if (order.getId().equals(orderId)) {
                        log.info(Constants.MSG_CONTEXT_RENEWAL + " already send against this order " + order.getId());
                        continue;
                    }
                    orderId = order.getId();
                    List<BusinessObject> buisnessObjectLst = new ArrayList<>();
                    BusinessObject bo = new BusinessObject("orders.id", order.getId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);
                    bo = new BusinessObject("messageType.id.messageTypeId", campign.getMessageType().getId().getMessageTypeId(), Constants.HIBERNATE_EQ_OPERATOR);
                    buisnessObjectLst.add(bo);

                    List<NotificationMessages> isNotificationMessagesesExist = patientProfileDAO.findByNestedProperty(new NotificationMessages(), buisnessObjectLst, null, 0);
                    if (!CommonUtil.isNullOrEmpty(isNotificationMessagesesExist)) {
                        ///here reminder will be add.
                        log.info(Constants.MSG_CONTEXT_RENEWAL + " already send against this order " + order.getId());
                        continue;
                    }
                    Practices dbPractise = patientProfileDAO.getPractiseNameById(order.getPatientProfile().getPracticeId());
//                    String pharmacyComments = "", genericName = "";
//                    if (order.getDrugDetail2() != null && order.getDrugDetail2().getDrugId() != null) {
//                        genericName = order.getDrugDetail2().getGenericName();
//                    }
                    CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(campign);
                    campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
                        //Send refill reminder message
//                        RewardPoints rewardPoints = (RewardPoints) patientProfileDAO.findByPropertyUnique(new RewardPoints(), "type", "Rx Refill", Constants.HIBERNATE_EQ_OPERATOR);
//                        CampaignMessages campaignMessages = replaceRefillMsgPlaceHolders(campign, order, genericName, rewardPoints, pharmacyComments);
                        String subject = patientProfileService.getMessageSubjectWithprocessedPlaceHolders(campaignMessages.getSubject(), order.getId(), dbPractise);
                        campaignMessages.setSubject(subject);
                        String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
                        campaignMessages.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(pushNot, order.getId(), dbPractise));
                        boolean isSend = patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, order.getPatientProfile().getPatientProfileSeqNo(), order,
                                campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
                        if (isSend) {
                            //Update order
                            order.setNextRefillFlag("1");
                            order.setLastReminderDate(new Date());
                            order.setRefillReinderCount(1);
                            patientProfileDAO.saveOrUpdate(order);
                            NotificationMessages nf = new NotificationMessages();
                            nf.setCreatedBy("Auto");
                            nf.setUpdatedOn(new Date());
                            patientProfileDAO.saveOrUpdate(nf);
                        }

                        nextRefillflag = true;

                } catch (Exception e) {
                    e.printStackTrace();
                    failed.error("order Id =" + order.getId() + " refill reminder is failed due to " + e);
                    log.error("Exception: Next refill reminder -> ", e);
                    successReminder = false;
                }
                if (successReminder) {
                    successed.info("Successfully send next refill reminder to Order Id=" + order.getId());
                    log.info("Successfully send next refill reminder to Order Id=" + order.getId());
                }
            }
            return nextRefillflag;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception: Next refill reminder -> ", e);
            return nextRefillflag;
        }
    }

}
