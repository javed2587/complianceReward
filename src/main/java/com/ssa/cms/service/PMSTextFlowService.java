package com.ssa.cms.service;

import com.ssa.cms.common.Constants;
import com.ssa.cms.dao.PMSTextFlowDAO;
import com.ssa.cms.dao.PatientProfileDAO;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.CampaignMessageReqRes;
import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.Event;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.MessagePriority;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.ReminderPOJO;
import com.ssa.cms.model.Response;
import com.ssa.cms.model.Url;
import com.ssa.cms.model.ValidResponse;
import com.ssa.cms.model.WidgetUser;
import com.ssa.cms.util.EncryptionHandlerUtil;
import com.ssa.decorator.MTDecorator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PMSTextFlowService {

    @Autowired
    PMSTextFlowDAO textFlowDAO;
    @Autowired
    PatientProfileDAO patientProfileDAO;
    @Autowired
    private PatientProfileService patientProfileService;

    private final Log logger = LogFactory.getLog(getClass());

    public PMSTextFlowDAO getTextFlowDAO() {
        return textFlowDAO;
    }

    public void setTextFlowDAO(PMSTextFlowDAO textFlowDAO) {
        this.textFlowDAO = textFlowDAO;
    }

    public void save(Object bean) {
        try {
            textFlowDAO.save(bean);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Exception: PMSTextFlowService -> save", ex);
        }
    }

    public void update(Object bean) {
        try {
            textFlowDAO.update(bean);
        } catch (Exception ex) {
            logger.error("Exception: PMSTextFlowService -> update", ex);
        }
    }

    public CampaignTrigger getTriggerByKeyword(String keyword) {
        CampaignTrigger trigger = null;
        try {
            trigger = textFlowDAO.getTriggerByKeyword(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PMSTextFlowService -> getTriggerByKeyword", e);
        }
        return trigger;
    }

    public Campaigns getCampaigns(Integer campaignId) {
        Campaigns campaigns = null;
        try {
            campaigns = textFlowDAO.getCampaigns(campaignId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaigns", e);
        }
        return campaigns;
    }

    public ShortCodes getShortCodeByCode(Integer code) {
        ShortCodes shortCode = null;
        try {
            shortCode = textFlowDAO.getShortCodeByCode(code);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getShortCodeByCode", e);
        }
        return shortCode;
    }

    public Event getEventByStaticValue(String staticValue) {
        Event event = null;
        try {
            event = textFlowDAO.getEventByStaticValue(staticValue);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getEventByStaticValue", e);
        }
        return event;
    }

    public EventHasFolderHasCampaigns getEventHasFolderHasCampaign(int campaignId, int eventId, String communicationPath) {
        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = null;
        try {
            eventHasFolderHasCampaigns = textFlowDAO.getEventHasFolderHasCampaign(campaignId, eventId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getEventHasFolderHasCampaign", e);
        }
        return eventHasFolderHasCampaigns;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId) {
        List<CampaignMessages> list = null;
        try {
            list = textFlowDAO.getCampaignMessages(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignMessages", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessagesByCommunicationType(int campaignId, int folderId, String communicationType) {
        List<CampaignMessages> list = null;
        try {
            list = textFlowDAO.getCampaignMessagesByCommunicationType(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignMessagesByCommunicationType", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId, int messageTypeId) {
        List<CampaignMessages> list = null;
        try {
            list = textFlowDAO.getCampaignMessages(campaignId, folderId, messageTypeId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignMessages", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessagesByCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        List<CampaignMessages> list = null;
        try {
            list = textFlowDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignMessagesByCommunicationPath", e);
        }
        return list;
    }

    public CampaignMessagesResponse getCampaignMessagesResponse(int campaignId, int folderId, int messageTypeId) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = textFlowDAO.getCampaignMessagesResponse(campaignId, folderId, messageTypeId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignMessagesResponse", e);
        }
        return campaignMessagesResponse;
    }

    public List<CampaignMessagesResponse> getResponseByCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        List<CampaignMessagesResponse> list = null;
        try {
            list = textFlowDAO.getResponseByCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getResponseByCommunicationPath", e);
        }
        return list;
    }

    public CampaignMessagesResponse getCampaignMessagesResponsebyCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignMessagesResponsebyCommunicationPath", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseWithResponseTitle(int campaignId, int folderId, int messageTypeId, String responseTitle) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = textFlowDAO.getCampaignMessagesResponseWithResponseTitle(campaignId, folderId, messageTypeId, responseTitle);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignMessagesResponseWithResponseTitle", e);
        }
        return campaignMessagesResponse;
    }

    public ValidResponse getValidResponse(String validWord) {
        ValidResponse validResponse = null;
        try {
            validResponse = textFlowDAO.getValidResponse(validWord);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getValidResponse", e);
        }
        return validResponse;
    }

    public Response getResponseByTitle(String responseTitle) {
        Response response = null;
        try {
            response = textFlowDAO.getResponseByTitle(responseTitle);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getResponseByTitle", e);
        }
        return response;
    }

    public CustomerRequest getCustomerRequestIP(String phoneNumber, int shortCode) {
        CustomerRequest customerRequest = null;
        try {
            customerRequest = textFlowDAO.getCustomerRequestIP(EncryptionHandlerUtil.getEncryptedString(phoneNumber), shortCode);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PMSTextFlowService -> getCustomerRequestIP", e);
        }
        return customerRequest;
    }

    public CustomerRequest getCustomerRequestById(long crSeqNo) {
        CustomerRequest customerRequest = null;
        try {
            customerRequest = textFlowDAO.getCustomerRequestById(crSeqNo);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCustomerRequestById", e);
        }
        return customerRequest;
    }

    public CustomerRequest getCustomerRequestIPorCM(String phoneNumber, int shortCode) {
        CustomerRequest customerRequest = null;
        try {
            customerRequest = textFlowDAO.getCustomerRequestIPorCM(EncryptionHandlerUtil.getEncryptedString(phoneNumber), shortCode);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCustomerRequestIPorCM", e);
        }
        return customerRequest;
    }

    public OptInOut getOptInOut(long crSeqNo) {
        OptInOut optInOut = null;
        try {
            optInOut = textFlowDAO.getOptInOut(crSeqNo);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getOptInOut", e);
        }
        return optInOut;
    }

    public CampaignMessageRequest getLastCampaignMessageRequestByCRSeqNo(long crSeqNo) {
        CampaignMessageRequest campaignMessageRequest = null;
        try {
            campaignMessageRequest = textFlowDAO.getLastCampaignMessageRequestByCRSeqNo(crSeqNo);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getLastCampaignMessageRequestByCRSeqNo", e);
        }
        return campaignMessageRequest;
    }

    public List<ReminderPOJO> getTextFlowReminderRecords(int yCount) {
        List<ReminderPOJO> list = new ArrayList<ReminderPOJO>();
        try {
            list = textFlowDAO.getTextFlowReminderRecords(yCount);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getTextFlowReminderRecords", e);
        }
        return list;
    }

    public List<ReminderPOJO> getAutoTextFlowRecord() {
        List<ReminderPOJO> list = new ArrayList<ReminderPOJO>();
        try {
            list = textFlowDAO.getAutoTextFlowRecord();
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getAutoTextFlowRecord", e);
        }
        return list;
    }

    public List<ReminderPOJO> getCouponRemindeRecords() {
        List<ReminderPOJO> list = new ArrayList<ReminderPOJO>();
        try {
            list = textFlowDAO.getCouponRemindeRecords();
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCouponRemindeRecords", e);
        }
        return list;
    }

    public int sentMessageCountCampaign(String phoneNumber, int campaignId, int folderId, int messageTypeid, String isRepeat) {
        int count = 0;
        try {
            count = textFlowDAO.sentMessageCountCampaign(EncryptionHandlerUtil.getEncryptedString(phoneNumber), campaignId, folderId, messageTypeid, isRepeat);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> sentMessageCountCampaign", e);
        }
        return count;
    }

    public int sentMMSCountCampaign(String phoneNumber, int campaignId, int folderId, int messageTypeid) {
        int count = 0;
        try {
            count = textFlowDAO.sentMMSCountCampaign(EncryptionHandlerUtil.getEncryptedString(phoneNumber), campaignId, folderId, messageTypeid);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> sentMMSCountCampaign", e);
        }
        return count;
    }

    public GatewayInfo getGatewayInfo(int shortCode) {
        GatewayInfo gatewayInfo = null;
        try {
            gatewayInfo = textFlowDAO.getGatewayInfo(shortCode);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getGatewayInfo", e);
        }
        return gatewayInfo;
    }

    public int getIRFRedemptionCountByPhone(String phoneNumber, int campaignId) {
        int count = 0;
        try {
            count = textFlowDAO.getIRFRedemptionCountByPhone(phoneNumber, campaignId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getIRFRedemptionCountByPhone", e);
        }
        return count;
    }

    public long getIRFLastRedemptionSecondsByPhone(String phoneNumber, int campaignId) {
        long seconds = 0;
        try {
            seconds = textFlowDAO.getIRFLastRedemptionSecondsByPhone(phoneNumber, campaignId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getIRFLastRedemptionSecondsByPhone", e);
        }
        return seconds;
    }

    public MessagePriority getMessagePriority(String phoneNumber, int shortCode) {
        MessagePriority messagePriority = null;
        try {
            messagePriority = textFlowDAO.getMessagePriority(EncryptionHandlerUtil.getEncryptedString(phoneNumber), shortCode);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getMessagePriority", e);
        }
        return messagePriority;
    }

    public String getURL(String urlCode) {
        Url url = null;
        String urlString = "";
        try {
            urlString = textFlowDAO.getURL(urlCode);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getURL", e);
        }
        return urlString;
    }

    public boolean isMmsExists(int campaignId, int folderId) {
        boolean flag = false;
        try {
            flag = textFlowDAO.isMmsExists(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> isMmsExists", e);
        }
        return flag;
    }

    public String getEventsDescription(int campaignId, int folderId, String communicationPath) {
        String events = "";
        try {
            events = textFlowDAO.getEventsDescription(campaignId, folderId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getEventsDescription", e);
        }
        return events;
    }

    public WidgetUser getWidgetUser(String userName, String userPassword) {
        WidgetUser widgetUser = null;
        try {
            widgetUser = textFlowDAO.getWidgetUser(userName, userPassword);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getWidgetUser", e);
        }
        return widgetUser;
    }

    public boolean isIPAddressValid(Integer widgetUserId, String ipAddress) {
        boolean isIpValid = false;
        try {
            isIpValid = textFlowDAO.isIPAddressValid(widgetUserId, ipAddress);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> isIPAddressValid", e);
        }
        return isIpValid;
    }

    public boolean isCommunicationIdRegistredWithDifferentMemeberId(String memberId, String phoneNumber) {
        boolean flag = false;
        try {
            flag = textFlowDAO.isCommunicationIdRegistredWithDifferentMemeberId(memberId, phoneNumber);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> isCommunicationIdRegistredWithDifferentMemeberId", e);
        }
        return flag;
    }

    public boolean isMemberExists(String memberId, String firstName, String lastName, String dateOfBirth) {
        boolean flag = false;
        try {
            flag = textFlowDAO.isMemberExists(memberId, firstName, lastName, dateOfBirth);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> isMemberIdExists", e);
        }
        return flag;
    }

    public List<OptInOut> getCampaignOptedOutReminderRecords(Integer campaignId) {
        List<OptInOut> list = new ArrayList<>();
        try {
            list = textFlowDAO.getOptedOutCampaignMemberRecords(campaignId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getCampaignOptedOutReminderRecords", e);
        }
        return list;
    }

    public List<CustomerRequest> getAllCustomerRequestIPOrCMByCampaignId(Integer campaignId) {
        List<CustomerRequest> list = new ArrayList<>();
        try {
            list = textFlowDAO.getAllCustomerRequestIPOrCMByCampaignId(campaignId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> getAllCustomerRequestIPOrCMByCampaignId", e);
        }
        return list;
    }

    public CustomerRequest setCustomerRequest(String phoneNumber,
            int campaignId, String campaignName, String message,
            int shortCodeValue, String source, String widgetName,
            long inputReferenceNumber,
            long ivrId,
            String ivrPath,
            String cardNumber,
            String statusCode
    ) {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setPhoneNumber(phoneNumber);
        customerRequest.setCampaignId(campaignId);
        customerRequest.setCampaignName(campaignName);
        customerRequest.setCommunicationPath("SMS");
        customerRequest.setYcount(0);
        customerRequest.setKeywordCode(message);
        customerRequest.setShortCode(shortCodeValue);
        customerRequest.setEffectiveDate(new Date());
        customerRequest.setLastUpdatedOn(new Date());
        customerRequest.setCommunicationSourceCode(source);
        customerRequest.setWidgetName(widgetName);
        customerRequest.setStatusCode(statusCode);
        customerRequest.setInputReferenceNumber(inputReferenceNumber);

        customerRequest.setCardNumber(cardNumber);
        customerRequest.setIvrId(ivrId);
        customerRequest.setIvrPath(ivrPath);
        return customerRequest;
    }

    public void cancelRequest(String message, String phoneNumber, int sCode) {
        if (message.equalsIgnoreCase("CANCEL")) {
            CustomerRequest customerRequest = getCustomerRequestIP(phoneNumber, sCode);
            cancelRequest(customerRequest);
        }
    }

    public void cancelRequest(CustomerRequest customerRequest) {
        if (customerRequest != null) {
            customerRequest.setStatusCode(StatusEnum.CANCELLED.getValue());
//            customerRequest.setLastUpdatedOn(new Date());
            this.update(customerRequest);

            OptInOut optInOut = this.getOptInOut(customerRequest.getCrSeqNo());
            if (optInOut != null) {
                optInOut.setStatusCode(StatusEnum.CANCELLED.getValue());
                this.update(optInOut);
            }
        }
    }

    public CampaignMessageRequest setCampaignMessageRequest(long crSeqNo, int campaignId, String campaignName, int shortCodeValue, int messageId, int messageTypeId, int folderId, long inputReferenceNumber, int intervalId, String intervalDesc, String eventDesc, String communicationType, String message) {
        CampaignMessageRequest campaignMessageRequest = new CampaignMessageRequest();
        campaignMessageRequest.setCrSeqNo(crSeqNo);
        campaignMessageRequest.setCampaignId(campaignId);
        campaignMessageRequest.setCampaignName(campaignName);
        campaignMessageRequest.setShortCode(shortCodeValue);
        campaignMessageRequest.setMessageId(messageId);
        campaignMessageRequest.setMessageTypeId(messageTypeId);
        campaignMessageRequest.setFolderId(folderId);
        campaignMessageRequest.setMessageTypeCode(message);
        campaignMessageRequest.setInputReferenceNumber(inputReferenceNumber);
        campaignMessageRequest.setIntervalId(intervalId);
        campaignMessageRequest.setIntervalDescription(intervalDesc);
        campaignMessageRequest.setEventDetail(eventDesc);
        campaignMessageRequest.setCommunicationPath(communicationType);
        return campaignMessageRequest;
    }

    public void saveCampaignMessageReqRes(MTDecorator decorator, long cmrSeqNo, Integer shortCodeValue, int campaignId, String campaignName, int messageId, Integer nextMessageTypeId, int folderId, long inputReferenceNumber) {
        CampaignMessageReqRes campaignMessageReqRes = new CampaignMessageReqRes();
        campaignMessageReqRes.setErrorCode(decorator.getErrorCode());
        campaignMessageReqRes.setErrorDescription(decorator.getErrorDescription());
        campaignMessageReqRes.setSmsRequest(decorator.getRequestXML());
        campaignMessageReqRes.setSmsResponse(decorator.getResponseXML());
        campaignMessageReqRes.setMtsId(decorator.getMtsId());
        campaignMessageReqRes.setMessageText(decorator.getMessageText());
        campaignMessageReqRes.setTicketId(decorator.getTicketId());
        campaignMessageReqRes.setCmrSeqNo(cmrSeqNo);
        campaignMessageReqRes.setShortCode(shortCodeValue);
        campaignMessageReqRes.setEffectiveDate(new Date());
        campaignMessageReqRes.setCampaignId(campaignId);
        campaignMessageReqRes.setCampaignName(campaignName);
        campaignMessageReqRes.setMessageId(messageId);
        campaignMessageReqRes.setFolderId(folderId);
        campaignMessageReqRes.setMessageTypeId(nextMessageTypeId);
        campaignMessageReqRes.setInputReferenceNumber(inputReferenceNumber);
        this.save(campaignMessageReqRes);
    }
        public void saveCampaignMessageReqRes(MTDecorator decorator) {
        CampaignMessageReqRes campaignMessageReqRes = new CampaignMessageReqRes();
        campaignMessageReqRes.setErrorCode(decorator.getErrorCode());
        campaignMessageReqRes.setErrorDescription(decorator.getErrorDescription());
        campaignMessageReqRes.setSmsRequest(decorator.getRequestXML());
        campaignMessageReqRes.setSmsResponse(decorator.getResponseXML());
        campaignMessageReqRes.setMtsId(decorator.getMtsId());
        campaignMessageReqRes.setMessageText(decorator.getMessageText());
        campaignMessageReqRes.setTicketId(decorator.getTicketId());
        campaignMessageReqRes.setEffectiveDate(new Date());
        this.save(campaignMessageReqRes);
    }

    public OptInOut setOptinOut(String phoneNumber, int campaignId, String campaignName, int sCode, String statusCode) {
        OptInOut optInOut = new OptInOut();
        optInOut.setPhoneNumber(phoneNumber);
        optInOut.setCampaignId(campaignId);
        optInOut.setCampaignName(campaignName);
        optInOut.setEffectiveDate(new Date());
        optInOut.setStatusCode(statusCode);
        optInOut.setOptInOut("I");
        optInOut.setShortCode(sCode);
        return optInOut;
    }

    public void OptOutPatientProfile(String communicationId) {
        logger.info("Patient Opted Out against this CommunicationId: " + communicationId);
        try {
            textFlowDAO.updatePatientInfo(communicationId);
        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> OptOutPatientProfile", e);
        }
    }

    public void updateEnrollmentIpad(String phone) {
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientInfoByEmailOrMobileNo(phone, "");
            if (patientProfile != null) {
                textFlowDAO.updateEnrollmentIpadStatus(patientProfile.getPatientProfileSeqNo());
            } else {
                logger.info("No enrollment found against this mobile number " + phone);
            }

        } catch (Exception e) {
            logger.error("Exception: PMSTextFlowService -> updateEnrollmentIpad", e);
        }
    }
        public void updateOrderStatusInResponseYes(String phone) {
            try {
                PatientProfile profile = patientProfileDAO.getPatientInfoByEmailOrMobileNo(phone, "");
                if (profile != null) {
                    List<Order> ord = patientProfileDAO.getOrdersListByProfileId(profile.getPatientProfileSeqNo());
                    for (Order ordelist : ord) {
                        OrderStatus stsus = new OrderStatus();
                        Order order = patientProfileService.getOrderById(ordelist.getId());
                        logger.info(""+order);
                        stsus.setId(Constants.ORDER_STATUS.MBR_TO_PAY_ID);
                        ordelist.setOrderStatus(stsus);
                        patientProfileDAO.update(ordelist);
                        patientProfileService.addRewardsPointSAndHistory(order, profile, 8);
                    }
                } else {
                    logger.info("No Order found for patient against this mobile number " + phone);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception: PMSTextFlowService -> updateOrderStatusInResponseYes", e);
            }
    }
}
