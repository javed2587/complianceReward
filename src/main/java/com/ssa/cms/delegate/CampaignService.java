package com.ssa.cms.delegate;

import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.dao.CampaignDAO;
import com.ssa.cms.dao.PatientProfileDAO;
import com.ssa.cms.model.*;
import com.ssa.cms.service.PhoneValidationService;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.SMSUtil;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CampaignService {

    @Autowired
    private CampaignDAO campaignDAO;
    @Autowired
    private PatientProfileDAO patientProfileDAO;

    private final Log logger = LogFactory.getLog(getClass());

    public boolean saveCampignBasic(Campaigns campaign, Integer userId) {
        Campaigns dbCampaign;

        List<String> sOptions = campaign.getSelectedOptions();

        campaign.setIsMaxBenefit("No");
        campaign.setIsClinicalMsgs("No");
        if (sOptions != null) {
            for (String option : sOptions) {
                if ("refill".equalsIgnoreCase(option)) {
                    campaign.setIsRefill("Yes");
                } else if ("repeat_refill".equalsIgnoreCase(option)) {
                    campaign.setIsRepeatRefill("Yes");
                } else if ("refill_order_reminder".equalsIgnoreCase(option)) {
                    campaign.setIsRefillOrderReminder("Yes");
                } else if ("validate_member_id".equalsIgnoreCase(option)) {
                    campaign.setIsValidateMemberId("Yes");
                } else if ("max_benefit".equalsIgnoreCase(option)) {
                    campaign.setIsMaxBenefit("Yes");
                } else if ("clinical_messages".equalsIgnoreCase(option)) {
                    campaign.setIsClinicalMsgs("Yes");
                }
            }
        }

        try {
            Date currentDate = DateUtil.formatDate(new Date(), "MM/dd/yyyy");
            if (campaign.getCampaignId() != null) {
                Integer campaignId = campaign.getCampaignId();
                dbCampaign = campaignDAO.getCampaignsById(campaignId);
                dbCampaign.setUpdatedOn(new Date());
                dbCampaign.setUpdatedBy(userId);

                boolean deleted = campaignDAO.deleteCampaignBasicChildren(campaignId);
                if (deleted) {
                    dbCampaign.setBrandId(campaign.getBrandId());
                    dbCampaign.setCampaignName(campaign.getCampaignName());

                    dbCampaign.setIndustry(campaign.getIndustry());
                    logger.info("Check Termination date: " + campaign.getTerminationDateTime() + "Current date is :" + currentDate);
                    if (campaign.getTerminationDateTime().compareTo(currentDate) < 0) {
                        logger.info("Set ISActive No");
                        dbCampaign.setIsActive("No");
                    } else {
                        dbCampaign.setIsActive(campaign.getIsActive());
                    }
                    dbCampaign.setIsMaxBenefit(campaign.getIsMaxBenefit());
                    dbCampaign.setIsRefill(campaign.getIsRefill());
                    dbCampaign.setIsRefillOrderReminder(campaign.getIsRefillOrderReminder());
                    dbCampaign.setIsValidateMemberId(campaign.getIsValidateMemberId());
                    dbCampaign.setMaxBenefitAmount(campaign.getMaxBenefitAmount());
                    dbCampaign.setTerminationDateTime(campaign.getTerminationDateTime());
                    dbCampaign.setShortCodes(campaign.getShortCodes());
                    dbCampaign.setRedemptionLimit(campaign.getRedemptionLimit());
                    dbCampaign.setSmtpServerInfo(campaign.getSmtpServerInfo());
                    dbCampaign.setLanchDateTime(campaign.getLanchDateTime());
                    dbCampaign.setTriggers(campaign.getTriggers());
                    dbCampaign.setRefillProcessTiming(campaign.getRefillProcessTiming());
                    dbCampaign.setDrugBrands(campaign.getDrugBrands());
                    dbCampaign.setClinicalMsgsCount(campaign.getClinicalMsgsCount());
                    dbCampaign.setClinicalMsgsTime(campaign.getClinicalMsgsTime());
                    dbCampaign.setIsClinicalMsgs(campaign.getIsClinicalMsgs());
                    dbCampaign.setSurvey(campaign.getSurvey());
                }

            } else {
                dbCampaign = campaign;
                dbCampaign.setIsDelete("No");
                dbCampaign.setCreatedOn(new Date());
                dbCampaign.setCreatedBy(userId);
                //TO DO for time beign no RedemptionChannel
//                RedemptionChannel channel = new RedemptionChannel();
//                channel.setRchannelId(2);
//                dbCampaign.setRedemptionChannel(channel);
                dbCampaign.setCampaignType("demo");
                if (dbCampaign.getIsActive() == null) {
                    dbCampaign.setIsActive("No");
                }
                if (campaign.getTerminationDateTime().before(currentDate)) {
                    dbCampaign.setIsActive("No");
                }
                dbCampaign.setUpdatedOn(new Date());
                dbCampaign.setUpdatedBy(userId);
            }

            campaignDAO.save(dbCampaign);
            campaign = dbCampaign;

        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate -> saveCampignBasic", e);
        }
        return true;
    }

    public boolean saveCampignAdvance(Campaigns campaign, String communicationPath, Integer userId) {
        Campaigns dbCampaign;
        try {
            Integer campaignId = campaign.getCampaignId();
            if (campaignId != null) {
                dbCampaign = campaignDAO.getCampaignsById(campaignId);
                dbCampaign.setUpdatedOn(new Date());
                dbCampaign.setUpdatedBy(userId);
                campaignDAO.save(dbCampaign);

                //delete child
                //GroupHasFolderHasCampaign
                //CampaignMessagesResponse
                //CampaignMessages
                boolean deleted = campaignDAO.deleteCampaignAdvanceChildren(campaignId, communicationPath);
                if (deleted) {
                    for (FolderHasCampaigns folder : campaign.getFolderHasCampaignses()) {

                        if (folder.getFolder() != null && folder.getFolder().getFolderId() != null) { //folder selected
                            int folderId = folder.getFolder().getFolderId();

                            //save folder
                            saveCampaignFolder(folderId, campaignId, communicationPath, 0);

                            //save events
                            saveCampaignEvents(folder, campaignId, folderId, communicationPath);

                            for (CampaignMessages messages : campaign.getCampaignMessageses()) {
                                if (messages.getMessageTypeId() != null && messages.getMessageType().getFolder() != null && messages.getMessageType().getFolder().getFolderId() == folderId) {
                                    saveCampaignMessage(messages, communicationPath, dbCampaign, userId, folderId);
                                }
                            }
                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: CampaignDelegate -> saveCampignBasic", e);
        }
        return true;
    }

    private void saveCampaignMessage(CampaignMessages messages, String communicationPath, Campaigns dbCampaign, Integer userId, int folderId) throws Exception {
        messages.setCampaigns(dbCampaign);
        messages.setCreatedOn(new Date());
        messages.setCreatedBy(userId);
        messages.setUpdatedBy(userId);
        messages.setUpdatedOn(new Date());
        messages.setIsActive("Yes");
        messages.setIsDelete("No");
        Integer _messageTypeId = messages.getMessageTypeId();
        MessageType messageType = new MessageType();
        MessageTypeId messageTypeId = new MessageTypeId();
        messageTypeId.setFolderId(folderId);
        messageTypeId.setMessageTypeId(_messageTypeId);
        messageType.setId(messageTypeId);
        messages.setMessageType(messageType);
        if (messages.getIsCritical() == null) {
            messages.setIsCritical(0);
        }
        try {

            for (CampaignMessagesResponse response : messages.getCampaignMessagesResponses()) {
                Intervals interval = (Intervals) campaignDAO.getIntervalById(response.getIntervals().getIntervalId());
                response.setIntervalId(interval.getIntervalId());
                response.setIntervalsTypeId(interval.getIntervalsType().getIntervalsTypeId());
                response.setMessageTypeId(_messageTypeId);
                response.setIntervals(interval);
                response.setCampaignMessages(messages);
                response.setComunicationPath(communicationPath);
                response.setCampaignId(dbCampaign.getCampaignId());
                response.setFolderId(folderId);
                if (response.getRepeatIntervalId() != null) {
                    Intervals rInterval = (Intervals) campaignDAO.getIntervalById(response.getRepeatIntervalId());
                    if (rInterval != null) {
                        response.setRepeatIntervalTypeId(rInterval.getIntervalsType().getIntervalsTypeId());
                    }
                }

                response.setCreatedBy(userId);
                response.setUpdatedBy(userId);
                response.setUpdatedOn(new Date());
                response.setCreatedOn(new Date());

            }
            //campaignDAO.merge(messages);
            campaignDAO.saveOrUpdate(messages);

        } catch (Exception exp) {
            logger.error("Exception: CampaignDelegate -> saveCampaignMessage", exp);
        }
    }

    private void saveCampaignEvents(FolderHasCampaigns folder, Integer campaignId, int folderId, String communicationPath) throws Exception {
        //save event
        if (folder.getSelectedEvents() != null && folder.getSelectedEvents().length > 0) {
            EventHasFolderHasCampaigns folderEvent;
            for (Integer eventId : folder.getSelectedEvents()) {
                List<BusinessObject> obj = new ArrayList<>();
                obj.add(new BusinessObject("folderId", folderId, Constants.HIBERNATE_EQ_OPERATOR));
                obj.add(new BusinessObject("campaignId", campaignId, Constants.HIBERNATE_EQ_OPERATOR));
                obj.add(new BusinessObject("eventId", eventId, Constants.HIBERNATE_EQ_OPERATOR));
                obj.add(new BusinessObject("communicationPath", communicationPath, Constants.HIBERNATE_EQ_OPERATOR));
                folderEvent = (EventHasFolderHasCampaigns) campaignDAO.findUniqueObjectByProperty(new EventHasFolderHasCampaigns(), obj, "", 0);
                if (folderEvent != null && !CommonUtil.isNullOrEmpty(eventId)) {
                    campaignDAO.saveOrUpdate(folderEvent);
                } else {
                    folderEvent = new EventHasFolderHasCampaigns();
                    folderEvent.setCampaignId(campaignId);
                    folderEvent.setEventId(eventId);
                    folderEvent.setFolderId(folderId);
                    folderEvent.setCommunicationPath(communicationPath);
                    campaignDAO.save(folderEvent);
                }
            }
        }
    }

    private void saveCampaignFolder(int folderId, Integer campaignId, String communicationPath, int fhCid) throws Exception {
        FolderHasCampaigns folderHasCampaigns = new FolderHasCampaigns();
        FolderHasCampaignsId folderHasCampaignsId = new FolderHasCampaignsId();
        if (fhCid > 0) {
            folderHasCampaignsId.setFhCid(fhCid);
        }
        folderHasCampaignsId.setFolderId(folderId);
        folderHasCampaignsId.setCampaignId(campaignId);
        folderHasCampaigns.setId(folderHasCampaignsId);
        folderHasCampaigns.setCommunicationPath(communicationPath);
        campaignDAO.saveOrUpdate(folderHasCampaigns);
    }

    public boolean isCampaignExists(String campaignName, Integer campaignId) {
        boolean flag = false;
        try {
            List<Campaigns> campaignses = campaignDAO.isCampaignExists(campaignName, campaignId);
            if (campaignses != null && campaignses.size() > 0) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate -> isCampaignExists", e);
        }
        return flag;
    }

    public boolean isTriggerValid(String keyword, Integer campaignId) {
        boolean flag = true;
        try {
            List<CampaignTrigger> triggers = campaignDAO.isTriggerValid(keyword, campaignId);
            if (triggers != null && triggers.size() > 0) {
                flag = false;
            }
        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate -> isTriggerValid", e);
        }
        return flag;
    }

    public List<Intervals> getIntervalList() {
        List<Intervals> list = new ArrayList<>();
        try {
            list = campaignDAO.getIntervalList();

        } catch (Exception e) {
            logger.error("Exception: getIntervalList:", e);
        }
        return list;

    }

    public List<Response> getResponseList() {
        List<Response> list = new ArrayList<>();
        try {
            list = campaignDAO.getResponseList();
        } catch (Exception e) {
            logger.error("Exception: getIntervalList:", e);
        }
        return list;
    }

    public List<Folder> getFolderList(String userId, String communicationPath) {
        List<Folder> list = new ArrayList<>();
        try {
            list = campaignDAO.getAllFolders(communicationPath);
        } catch (Exception e) {
            logger.error("Exception: getFolderList:", e);
        }
        return list;
    }

    public List<ShortCodes> getShortCodeList() {
        List<ShortCodes> list = new ArrayList<>();
        try {
            list = campaignDAO.getShortCodeList();
        } catch (Exception e) {
            logger.error("Exception >> getShortCodeList", e);
        }
        return list;
    }

    public List<Event> getEventList(Integer campaignId) {
        List<Event> list = new ArrayList<>();
        try {
            list = campaignDAO.getAllEvent(campaignId);
        } catch (Exception e) {
            logger.error("Exception >> getAllEvents", e);
        }
        return list;
    }

    public Integer getBrandIdByDrugId(String drugBrandId) {
        Integer brandId = 0;
        try {
            brandId = campaignDAO.getBrandId(drugBrandId);
        } catch (Exception ex) {
            logger.error("Exception >> getBrandIdByDrugId", ex);
        }
        return brandId;
    }

    public List<Campaigns> getCampaignListByType(String campaignType, SessionBean sessionBean) {
        List<Campaigns> list = null;
        try {
            list = campaignDAO.findAllCampaigns(campaignType);
        } catch (Exception e) {
            logger.error("Exception: PermissionDAO -> getUserByName", e);
        }
        return list;
    }

    public Campaigns getCampaignById(Integer campaignId) {
        Campaigns campaigns = new Campaigns();
        try {
            campaigns = campaignDAO.getCampaignsById(campaignId);
            for (CampaignMessages msg : campaigns.getCampaignMessageses()) {
                for (CampaignMessagesResponse response : msg.getCampaignMessagesResponses()) {
                    if (response.getResponse() != null && response.getResponse().getResponseId() != 0) {
                        response.setResponseTitle(response.getResponse().getResponseTitle());
                    } else {
                        response.setResponseTitle("");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CampaignService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campaigns;
    }

    public List<EventHasFolderHasCampaigns> getFolderEvents(Integer folderId, Integer campignId) {
        return campaignDAO.getFolderEvents(campignId, folderId);
    }

    public List<DrugBrand> getAvailableDrugBrands(String userId, Integer campaignId) {
        List<DrugBrand> list = new ArrayList<>();
        try {
            list = campaignDAO.getAllDrugBrands(userId, campaignId);
        } catch (Exception e) {
            logger.error("Exception: getAvailableDrugBrands >", e);
        }

        return list;
    }

    public List<Industry> getIndustryList() {
        List<Industry> list = new ArrayList<>();
        try {
            list = campaignDAO.getIndustryList();

        } catch (Exception e) {
            logger.error("Exception: getIndustryList >", e);
        }

        return list;
    }

    public List<SmtpServerInfo> getSmtpList() {
        List<SmtpServerInfo> list = new ArrayList<>();
        try {
            list = campaignDAO.getSmtpList();

        } catch (Exception e) {
            logger.error("Exception: getSmtpList >", e);
        }
        return list;
    }

    public boolean sendTestSMS(String mobileNumber, String message) {
        boolean phoneValidity = false;
        try {
            String phonevalidationUrl = patientProfileDAO.getURL(Constants.PVURL);
            PhoneValidationService phoneValidationService = new PhoneValidationService(phonevalidationUrl);
            phoneValidity = phoneValidationService.checkPhoneValidity(mobileNumber, "", "GenRx");
            if (phoneValidity) {
                //send message
                SMSUtil.sendSmsMessage(mobileNumber, message);
            }

        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate -> sendTestSMS", e);
        }
        return phoneValidity;
    }

    public CampaignDAO getCampaignDAO() {
        return campaignDAO;
    }

    public void setCampaignDAO(CampaignDAO campaignDAO) {
        this.campaignDAO = campaignDAO;
    }

    public Response getResponseNameById(int responseId) {
        Response response = null;
        try {
            response = campaignDAO.getResponseNameById(responseId);
        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate -> getResponseNameById", e);
        }

        return response;
    }

    public boolean brandFound(String brandIds, List<Integer> userBrandList) {
        boolean brandFound = false;
        if (brandIds != null && userBrandList != null) {
            for (String brand : brandIds.split(",")) {
                if (userBrandList.contains(new Integer(brand))) {
                    brandFound = true;
                    break;
                }
            }
        }
        return brandFound;
    }

    public boolean brandFound_(String brandIds, List<UserBrand> userBrandList) {
        boolean brandFound = false;
        if (brandIds != null && userBrandList != null) {
            for (String brand : brandIds.split(",")) {
                for (UserBrand userBrand : userBrandList) {
                    if (userBrand.getBrandId() == Integer.parseInt(brand)) {
                        brandFound = true;
                        break;
                    }
                }
            }
        }
        return brandFound;
    }

    public boolean brandFoundEvent(String brandIds, String eventBrandIds) {
        boolean brandFound = false;
        for (String brand : brandIds.split(",")) {
            if (eventBrandIds != null) {
                for (String brnd : eventBrandIds.split(",")) {
                    if (Integer.parseInt(brnd) == Integer.parseInt(brand)) {
                        brandFound = true;
                        break;
                    }
                }
            }
        }
        return brandFound;
    }

    public List<Survey> getSurveyList() {
        List<Survey> list = new ArrayList<>();
        try {
            list = campaignDAO.getSurveysList();
        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate -> getSurveyList", e);
        }
        return list;
    }

    public List<Campaigns> getSurveyReports(Integer campaignId, Integer surveyId) {
        List<Campaigns> list = new ArrayList<>();
        List<Campaigns> newlist = new ArrayList<>();
        try {
            Map<String, String> map = new HashMap<>();
            if (campaignId > 0 || surveyId > 0) {
                Integer count = 1;
                List<Object[]> reportsList = campaignDAO.getSurveyReportList(campaignId, surveyId);
                if (reportsList != null) {
                    for (Object[] row : reportsList) {
                        Campaigns campaigns = new Campaigns();
                        campaigns.setSurveyId(row[0].toString());
                        campaigns.setQuestionTitle(row[1].toString());
                        campaigns.setResponseTitle(row[2].toString());
                        campaigns.setTotalResponse(row[3].toString());
                        campaigns.setGrandTotal(row[4].toString());
                        map.put(row[0].toString(), row[1].toString());
                        list.add(campaigns);
                    }

                    for (String entry : map.keySet()) {
                        Campaigns newcampaigns = new Campaigns();
                        newcampaigns.setSrno(count);
                        String respone = "";
                        for (Campaigns campaigns : list) {
                            if (campaigns.getSurveyId().equals(entry)) {
                                newcampaigns.setQuestionTitle(campaigns.getQuestionTitle());
                                newcampaigns.setGrandTotal(campaigns.getGrandTotal());
                                respone += campaigns.getResponseTitle() + " " + campaigns.getTotalResponse() + ", ";
                            }
                        }
                        if (respone.length() > 2) {
                            respone = respone.substring(0, respone.length() - 2);
                        }
                        newcampaigns.setResponseTitle(respone);
                        count++;
                        newlist.add(newcampaigns);
                    }

                }
            }
        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate -> getSurveyReports", e);
        }
        return newlist;
    }

    public boolean saveOrUpdateCampignAdvance(Campaigns campaign, String communicationPath, int userId, Integer fhCid) {
        try {
            for (FolderHasCampaigns folder : campaign.getFolderHasCampaignses()) {

                if (folder.getFolder() != null && folder.getFolder().getFolderId() != null) { //folder selected
                    int folderId = folder.getFolder().getFolderId();

                    //save folder
                    saveCampaignFolder(folderId, campaign.getCampaignId(), communicationPath, fhCid);

                    //save events
                    saveCampaignEvents(folder, campaign.getCampaignId(), folderId, communicationPath);

                    for (CampaignMessages messages : campaign.getCampaignMessageses()) {
                        if (messages.getMessageTypeId() != null && messages.getMessageType().getFolder() != null && messages.getMessageType().getFolder().getFolderId() == folderId) {
                            saveCampaignMessage(messages, communicationPath, campaign, userId, folderId);
                        }
                    }
                }

            }
        } catch (Exception e) {
            logger.error("Exception: CampaignDelegate# saveOrUpdateCampignAdvance", e);
            return false;
        }
        return true;
    }
}
