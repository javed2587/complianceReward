package com.ssa.cms.service;

import com.ssa.cms.dao.RedemptionDAO;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CouponOffered;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.DailyRedemptionId;
import com.ssa.cms.model.Drfprocessed;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.Enrollment;
import com.ssa.cms.model.EventDetail;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRequest;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.InstantRedemptionId;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.RedemptionIngredient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RedemptionService {

    @Autowired
    private RedemptionDAO redemptionDAO;

    private final Log logger = LogFactory.getLog(getClass());

    public RedemptionDAO getRedemptionDAO() {
        return redemptionDAO;
    }

    public void setRedemptionDAO(RedemptionDAO redemptionDAO) {
        this.redemptionDAO = redemptionDAO;
    }

    public boolean save(Object object) {
        boolean isSaved = false;
        try {
            redemptionDAO.save(object);
            isSaved = true;
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> save", e);
        }
        return isSaved;
    }

    public boolean saveOrUpdate(Object object) {
        boolean isSaved = false;
        try {
            redemptionDAO.saveOrUpdate(object);
            isSaved = true;
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> saveOrUpdate", e);
        }
        return isSaved;
    }

    public boolean update(Object object) {
        boolean isUpdate = false;
        try {
            redemptionDAO.update(object);
            isUpdate = true;
        } catch (Exception ex) {
            isUpdate = false;
            Logger.getLogger(RedemptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }

    public Object getById(Class clazz, Serializable id) {
        Object object = redemptionDAO.getById(clazz, id);
        return object;
    }

    public InstantRedemption getIRFById(InstantRedemptionId id) {
        InstantRedemption irf = new InstantRedemption();
        try {
            irf = redemptionDAO.getIRFById(id);
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> getIRFById", e);
        }
        return irf;
    }

    public int getIRFCountById(InstantRedemptionId id) {
        int count = 0;
        try {
            count = redemptionDAO.getIRFCountById(id);
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> getIRFCountById", e);
        }
        return count;
    }

    public DailyRedemption getDRFById(DailyRedemptionId id) {
        DailyRedemption drf = new DailyRedemption();
        try {
            drf = redemptionDAO.getDRFById(id);
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> getDRFById", e);
        }
        return drf;
    }

    public int getDRFCountById(DailyRedemptionId id) {
        int count = 0;
        try {
            count = redemptionDAO.getDRFCountById(id);
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> getDRFCountById", e);
        }
        return count;
    }

    public Campaigns getCampaignByNDCNumber(String ndcNumber) {
        Campaigns campaigns = new Campaigns();
        try {
            campaigns = redemptionDAO.getCampaignByNDCNumber(ndcNumber);
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> getCampaignByNDCNumber", e);
        }
        return campaigns;
    }

    public List<RedemptionIngredient> getRedemptionIngredientByTransactionNumber(String redemptionNumber) {
        List<RedemptionIngredient> list = new ArrayList<>();
        try {
            list = redemptionDAO.getRedemptionIngredientByTransactionNumber(redemptionNumber);
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> getRedemptionIngredientByTransactionNumber", e);
        }
        return list;
    }

    public Campaigns getCampaignById(String campaignId) {
        Campaigns campaigns = new Campaigns();
        try {
            campaigns = redemptionDAO.getCampaignById(campaignId);
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> getCampaignById", e);
        }
        return campaigns;
    }

    public int sentMMSCountCampaign(String phoneNumber, int campaignId, int folderId, int messageTypeid) {
        int count = 0;
        try {
            count = redemptionDAO.sentMMSCountCampaign(phoneNumber, campaignId, folderId, messageTypeid);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> sentMMSCountCampaign", e);
        }
        return count;
    }

    public InstantRequest getInstantRequestByMemberId(String phoneNo, int campaignId) {
        InstantRequest instantRequest = null;
        try {
            instantRequest = redemptionDAO.getInstantRequestByMemberId(phoneNo, campaignId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getInstantRequestByMemberId", e);
        }
        return instantRequest;
    }

    public List<Integer> getEventByRedemptionDynamicValue() {
        List<Integer> list = null;
        try {
            list = redemptionDAO.getEventByRedemptionDynamicValue();
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getEventByRedemptionDynamicValue", e);
        }
        return list;
    }

    public List<Integer> getFolderByEventIds(List<Integer> eventList, int campaignId) {
        List<Integer> folders = null;
        try {
            folders = redemptionDAO.getFolderByEventIds(eventList, campaignId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getFolderByEventIds", e);
        }
        return folders;
    }

    public EventHasFolderHasCampaigns getEventHasFolderHasCampaign(int campaignId, int eventId) {
        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = null;
        try {
            eventHasFolderHasCampaigns = redemptionDAO.getEventHasFolderHasCampaign(campaignId, eventId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getEventHasFolderHasCampaign", e);
        }
        return eventHasFolderHasCampaigns;
    }

    public List<EventHasFolderHasCampaigns> getEventHasFolderHasCampaign(int campaignId, String dynamicValue, String communicationPath) {
        List<EventHasFolderHasCampaigns> list = null;
        try {
            list = redemptionDAO.getEventHasFolderHasCampaign(campaignId, dynamicValue, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getEventHasFolderHasCampaign", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, List<Integer> folderIds) {
        List<CampaignMessages> list = null;
        try {
            list = redemptionDAO.getCampaignMessages(campaignId, folderIds);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessages", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId) {
        List<CampaignMessages> list = null;
        try {
            list = redemptionDAO.getCampaignMessages(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessages", e);
        }
        return list;
    }

    public List<EventDetail> getEventDetail(String eventsIds, String dataSet) {
        List<EventDetail> list = null;
        try {
            list = redemptionDAO.getEventDetail(eventsIds, dataSet);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getEventDetail", e);
        }
        return list;
    }

    public List<Object> get(Class objClass, Object object) {
        return redemptionDAO.get(objClass, object);
    }

    public Enrollment getTextEnrollment(String phoneNumber, int campaignId) {
        Enrollment enrollment = null;
        try {
            enrollment = redemptionDAO.getTextEnrollment(phoneNumber, campaignId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getTextEnrollment", e);
        }
        return enrollment;
    }

    public Enrollment getEmailEnrollment(String email, int campaignId) {
        Enrollment enrollment = null;
        try {

        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getEmailEnrollment", e);
        }
        return enrollment;
    }

    public CouponOffered getCouponOfferedByCrno(long crNo, int campaignId) {
        CouponOffered couponOffered = null;
        try {
            couponOffered = redemptionDAO.getCouponOfferedByCrno(crNo, campaignId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCouponOfferedByCrno", e);
        }
        return couponOffered;
    }

    public InstantRequest getInstantRequestByPhone(String phone, int campaignId) {
        InstantRequest instantRequest = null;
        try {
            instantRequest = redemptionDAO.getInstantRequestByPhone(phone, campaignId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getInstantRequestByPhone", e);
        }
        return instantRequest;
    }

    public int retrieveMessageCountByTypeForRedemption(String phoneNumber, String rxGroupNumber, Date cardholderDob, Date fillDate, String ndcNumber,
            int claimStatus, int folderId, int messageTypeId) {
        int count = 0;
        try {
            count = redemptionDAO.retrieveMessageCountByTypeForRedemption(phoneNumber, rxGroupNumber, cardholderDob, fillDate, ndcNumber,
                    claimStatus, folderId, messageTypeId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> retrieveMessageCountByTypeForRedemption", e);
        }
        return count;
    }

    public List<Object[]> getMessageCode(String ndc) {
        List<Object[]> messageList = null;
        try {
            messageList = redemptionDAO.getMessageCode(ndc);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getMessageCode", e);
        }
        return messageList;
    }

    public List<Object[]> getCurrentMonthMessageQueueList() {
        List<Object[]> messageList = null;
        try {
            messageList = redemptionDAO.getCurrentMonthMessageQueueList();
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCurrentMonthMessageQueueList", e);
        }
        return messageList;
    }

    public boolean deleteClinicalMessage(String queueId) {
        try {
            return redemptionDAO.deleteMessageFromQueue(queueId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCurrentMonthMessageQueueList", e);
        }
        return false;
    }

    public int getInstantRedemptionCount() {
        return redemptionDAO.getInstantRedemptionCount();
    }

    public boolean isEventDetailVerifiedForDailyRedemption(List<EventDetail> details, DailyRedemptionId drfId, int redemptionId) {
        return redemptionDAO.isEventDetailVerifiedForDailyRedemption(details, drfId, redemptionId);
    }

    public boolean isEventDetailVerifiedForInstantRedemption(List<EventDetail> details, InstantRedemptionId irfId) {
        return redemptionDAO.isEventDetailVerifiedForInstantRedemption(details, irfId);
    }

    public boolean isEventDetailVerifiedForInstantRedemption(List<EventDetail> details, InstantRedemptionId irfId, int redemptionId) {
        return redemptionDAO.isEventDetailVerifiedForInstantRedemption(details, irfId, redemptionId);
    }

    public Object findById(Class clazz, Integer id) {
        Object o = null;
        try {
            o = redemptionDAO.findById(clazz, id);
        } catch (Exception ex) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> findById", ex);
        }
        return o;
    }

    public Campaigns getCampaignsById(Integer campaignsId) throws Exception {
        return redemptionDAO.getCampaignsById(campaignsId);
    }

    public CampaignMessagesResponse getCampaignMessagesResponse(int campaignId, int folderId, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = redemptionDAO.getCampaignMessagesResponse(campaignId, folderId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessagesResponse", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseBycommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            if (communicationPath.equalsIgnoreCase("T")) {
                communicationPath = "SMS";
            }
            campaignMessagesResponse = redemptionDAO.getCampaignMessagesResponseBycommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessagesResponseBycommunicationPath", e);
        }
        return campaignMessagesResponse;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId, int messageTypeId) {
        List<CampaignMessages> list = null;
        try {
            list = redemptionDAO.getCampaignMessages(campaignId, folderId, messageTypeId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessages", e);
        }
        return list;
    }

    public GatewayInfo getGatewayInfo(int shortCode) {
        GatewayInfo gatewayInfo = null;
        try {
            gatewayInfo = redemptionDAO.getGatewayInfo(shortCode);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getGatewayInfo", e);
        }
        return gatewayInfo;
    }

    public void populateNpiValues(InstantRedemption irf) {
        try {
            redemptionDAO.populateNpiValues(irf);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> populateNpiValues", e);
        }
    }

    public CampaignTrigger getTriggerByCampaignId(int campaignId) {
        CampaignTrigger trigger = null;
        try {
            trigger = redemptionDAO.getTriggerByCampaignId(campaignId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getTriggerByCampaignId", e);
        }
        return trigger;
    }

    public OptInOut getTextOptInOut(int campaignId, String phoneNumber) {
        OptInOut optInOut = null;
        try {
            optInOut = redemptionDAO.getTextOptInOut(campaignId, phoneNumber);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getTextOptInOut", e);
        }
        return optInOut;
    }

    public String getURL(String urlCode) {
        String urlString = "";
        try {
            urlString = redemptionDAO.getURL(urlCode);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getURL", e);
        }
        return urlString;
    }

    public BigDecimal getTotalBenefitAmountIRF(InstantRedemption instantRedemption) {
        BigDecimal amount = null;
        try {
            amount = redemptionDAO.getTotalBenefitAmountIRF(instantRedemption);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getTotalBenefitAmountIRF", e);
        }
        return amount;
    }

    public BigDecimal getTotalBenefitAmountDRF(DailyRedemption dailyRedemption) {
        BigDecimal amount = null;
        try {
            amount = redemptionDAO.getTotalBenefitAmountDRF(dailyRedemption);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getTotalBenefitAmountDRF", e);
        }
        return amount;
    }

    public int getTotalRedemptionCountIRF(InstantRedemption instantRedemption) {
        int count = 0;
        try {
            count = redemptionDAO.getTotalRedemptionCountIRF(instantRedemption);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getTotalRedemptionCountIRF", e);
        }
        return count;
    }

    public int getTotalRedemptionCountDRF(DailyRedemption dailyRedemption) {
        int count = 0;
        try {
            count = redemptionDAO.getTotalRedemptionCountDRF(dailyRedemption);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getTotalRedemptionCountDRF", e);
        }
        return count;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByResponseName(int campaignId, int folderId, String responseTitle) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = redemptionDAO.getCampaignMessagesResponseByResponseName(campaignId, folderId, responseTitle);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessagesResponseByResponseName", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByResComm(int campaignId, int folderId, String responseTitle, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = redemptionDAO.getCampaignMessagesResponseByResComm(campaignId, folderId, responseTitle, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessagesResponseByResComm", e);
        }
        return campaignMessagesResponse;
    }

    public List<CampaignMessages> getCampaignMessagesByCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        List<CampaignMessages> list = null;
        try {
            if (communicationPath.equalsIgnoreCase("T")) {
                communicationPath = "SMS";
            }
            list = redemptionDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessagesByCommunicationPath", e);
        }
        return list;
    }

    public int getPerPrescriptionCountDRF(DailyRedemption dailyRedemption) {
        int amount = 0;
        try {
            amount = redemptionDAO.getPerPrescriptionCountDRF(dailyRedemption);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getPerPrescriptionCountDRF", e);
        }
        return amount;
    }

    public List<DailyRedemption> getPaidOnlyDRFRecords(int daysBack) {
        List<DailyRedemption> dailyRedemptionList = new ArrayList<>();
        try {
            dailyRedemptionList = redemptionDAO.getPaidOnlyDRFRecords(daysBack);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getPaidOnlyDRFRecords", e);
        }
        return dailyRedemptionList;
    }

    public int getDRFProcessedRedmeptionCount(DailyRedemption dailyRedemption) {
        int count = 0;
        try {
            count = redemptionDAO.getDRFProcessedRedmeptionCount(dailyRedemption);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getDRFProcessedRedmeptionCount", e);
        }
        return count;
    }

    public int getDRFProcessedCountById(int claimStatus, String transactionNumber) {
        int count = 0;
        try {
            count = redemptionDAO.getDRFProcessedCountById(claimStatus, transactionNumber);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getDRFProcessedCountById", e);
        }
        return count;
    }

    public List<DailyRedemption> getCancelledDRFRecords(int daysBack) {
        List<DailyRedemption> dailyRedemptionList = new ArrayList<>();
        try {
            dailyRedemptionList = redemptionDAO.getCancelledDRFRecords(daysBack);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCancelledDRFRecords", e);
        }
        return dailyRedemptionList;
    }

    public boolean deleteDRFProcessed(Drfprocessed drfprocessed) {
        boolean flag;
        try {
            redemptionDAO.deleteDRFProcessed(drfprocessed);
            flag = true;
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> deleteDRFProcessed", e);
            flag = false;
        }
        return flag;
    }

    public List<Drfprocessed> getDRFProcessedRecordForPatient(Drfprocessed drfprocessed) {
        List<Drfprocessed> list = null;
        try {
            list = redemptionDAO.getDRFProcessedRecordForPatient(drfprocessed);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getDRFProcessedRecordForPatient", e);
        }
        return list;
    }

    public Drfprocessed getDRFProcessedById(int claimStatus, String transactionNumber) {
        Drfprocessed drfprocessed = null;
        try {
            drfprocessed = redemptionDAO.getDRFProcessedById(claimStatus, transactionNumber);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getDRFProcessedById", e);
        }
        return drfprocessed;
    }

    public String getEventsDescription(int campaignId, int folderId, String communicationPath) {
        String events = "";
        try {
            events = redemptionDAO.getEventsDescription(campaignId, folderId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getEventsDescription", e);
        }
        return events;
    }

    public InstantRedemption getInstantRedemptionDetailByRedemptionId(int redemptionId) {
        InstantRedemption irf = null;
        try {
            irf = redemptionDAO.getInstantRedemptionDetailByRedemptionId(redemptionId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getInstantRedemptionDetailByRedemptionId", e);
        }
        return irf;
    }

    public boolean isMmsExists(int campaignId, int folderId) {
        boolean flag = false;
        try {
            flag = redemptionDAO.isMmsExists(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> isMmsExists", e);
        }
        return flag;
    }

    public long getIRFLastRedemptionSecondsByPhone(String phoneNumber, int campaignId) {
        long sec = 0;
        try {
            sec = redemptionDAO.getIRFLastRedemptionSecondsByPhone(phoneNumber, campaignId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getIRFLastRedemptionSecondsByPhone", e);
        }

        return sec;
    }

    public List<CampaignMessages> getCampaignMessagesByMsgId(int messageId) {
        List<CampaignMessages> list = null;
        try {
            list = redemptionDAO.getCampaignMessagesByMsgId(messageId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessagesByMsgId", e);
        }
        return list;
    }

    public String getCommunictionIdByMemberId(String memberId, String dob) {

        try {
            return redemptionDAO.getCommunictionIdByMemberId(memberId, dob);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCampaignMessagesByMsgId", e);
        }
        return "";
    }

    public int getCurrentMonthMessageCount(int campaignId, String communicationId) {

        try {
            return redemptionDAO.getCurrentMonthMessageCount(campaignId, communicationId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCurrentMonthMessageCount", e);
        }
        return 0;
    }

    public int getNdcMessageQueueCount(int campaignId, String communicationId, String ndc) {

        try {
            return redemptionDAO.getNdcMessageQueueCount(campaignId, communicationId, ndc);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getCurrentMonthMessageCount", e);
        }
        return 0;
    }

    public int getMessageQueueCount(int campaignId, String communicationId) {

        try {
            return redemptionDAO.getMessageQueueCount(campaignId, communicationId);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getMessageQueueCount", e);
        }
        return 0;
    }

    public String getNotificationDescription(int statusCode) {
        try {
            return redemptionDAO.getNotificationDescription(statusCode);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public Boolean getPharmacyByNpiNo(String npi) {
        boolean result = false;
        try {
            result = redemptionDAO.getPharmacyByNpiNo(Long.parseLong(npi));
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getPharmacyByNpiNo", e);
        }
        return result;
    }

    public Drug getMaxOfferByNdc(String ndcNo) {
        Drug drug = new Drug();
        try {
            drug = redemptionDAO.getMaxOfferByNdcNo(ndcNo);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getMaxOfferByNdc", e);
        }
        return drug;
    }

    public EventHasFolderHasCampaigns getEventHasFolderHasCampaigns(int campaignId, String dynamicValue, String communicationPath) {
        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = new EventHasFolderHasCampaigns();
        try {
            eventHasFolderHasCampaigns = redemptionDAO.getEhfhCampaigns(campaignId, dynamicValue, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getEventHasFolderHasCampaigns", e);
        }
        return eventHasFolderHasCampaigns;
    }

    public Order getOrderByTransactionsNo(String transactionNo) {
        Order order = new Order();
        try {
            order = redemptionDAO.getOrdersByTransactionNo(transactionNo).get(0);

            logger.info("RedemptionService -> getOrderByTranctionsNo: " + order);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> getOrderByTranctionsNo", e);
        }
        return order;
    }

    public boolean saveAutoOrderPlace(InstantRedemption irf, Order ord) {
        boolean cardConnectRes = false;
        try {
            OrderStatus status = new OrderStatus();
            status.setId(2);
            Double copay = 0.0;
            List<RedemptionIngredient> ingredients = getRedemptionIngredientByTransactionNumber(irf.getId().getTransactionNumber());
            if (ingredients != null && !ingredients.isEmpty()) {
                for (RedemptionIngredient ingredient : ingredients) {
                    Drug drug = getMaxOfferByNdc(ingredient.getNdc());
//                    if (drug != null) {
//                        copay += drug.getMaxOffer();
//                    }
                }
            }
            logger.info("Copay: " + copay);
            logger.info("PtOutOfPocket: " + irf.getPtOutOfPocket());
            Double paymentAmount = irf.getPtOutOfPocket().doubleValue() - copay;
            logger.info("PaymentAmount: " + paymentAmount);
            //ord.setCopay(copay);
            ord.setOutOfPocket(irf.getPtOutOfPocket());
            ord.setTransactionNo(irf.getId().getTransactionNumber());
            ord.setPayment(paymentAmount);
            ord.setCreatedOn(new Date());
            ord.setUpdatedOn(new Date());
            ord.setOrderStatus(status);
            ord.setOrderTrackingNo(null);
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setComments("Order Placed!");
            orderHistory.setOrder(ord);
            orderHistory.setOrderStatus(status);
            orderHistory.setCreatedOn(new Date());
            orderHistory.setUpdatedOn(new Date());
            List<OrderHistory> orderHistorys = new ArrayList<>();
            orderHistorys.add(orderHistory);
            ord.setOrderHistory(orderHistorys);
            redemptionDAO.save(ord);
            cardConnectRes = true;
        } catch (Exception e) {
            logger.error("Exception: RedemptionService -> saveAutoPlaceOrder", e);
        }

        return cardConnectRes;
    }

    public void populatePrescriberNpiValues(DailyRedemption dailyRedemption) {
        try {
            redemptionDAO.populateNpiValues(dailyRedemption);
        } catch (Exception e) {
            logger.error("Exception: " + this.getClass().getSimpleName() + " -> populateNpiValues", e);
        }
    }
}
