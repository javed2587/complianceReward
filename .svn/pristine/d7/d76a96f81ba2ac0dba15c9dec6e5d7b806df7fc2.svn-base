package com.ssa.cms.delegate;

import com.ssa.cms.common.Constants;
import com.ssa.cms.dao.SetupsDAO;
import com.ssa.cms.dto.DeliveryDistanceFeeDTO;
import com.ssa.cms.dto.PatientProfileDTO;
import com.ssa.cms.model.*;
import com.ssa.cms.service.DrugService;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SetupService {

    @Autowired
    private DrugService drugService;
    @Autowired
    private SetupsDAO setupsDAO;
    private static final Log logger = LogFactory.getLog(SetupService.class.getName());

    public List<Industry> getIndustries() {
        logger.info("Begin: SetupService -> getIndustries");
        List<Industry> industryList = new ArrayList<>();
        try {
            industryList = setupsDAO.findAllIndustries();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getIndustries", e);
        }
        logger.info("End: SetupService -> getIndustries");
        return industryList;
    }

    public Industry getIndustryById(Integer id) {
        Industry industry = new Industry();
        try {
            industry = setupsDAO.getIndustryById(id);
        } catch (Exception e) {
            logger.error(Level.SEVERE, e);
        }
        return industry;
    }

    public boolean getBrandByName(String brandTitle, Integer id) {
        boolean found = false;
        try {
            found = setupsDAO.findBrandByTitle(brandTitle, id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getBrandByName", e);
        }
        return found;
    }

    @Transactional(readOnly = false)
    public boolean saveIndustry(Industry industry, Integer currentUserId) {
        logger.info("Begin: SetupService -> saveIndustry");
        boolean saved = false;
        try {
            if (industry.getIndustryId() == null) {
                industry.setCreatedOn(new Date());
                industry.setCreatedBy(currentUserId);
                industry.setIsActive("Yes");
                industry.setIsDelete("No");
            }
            industry.setUpdatedBy(currentUserId);
            industry.setUpdatedOn(new Date());
            setupsDAO.saveOrUpdate(industry);
            saved = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveIndustry", e);
        }
        logger.info("End: SetupService -> saveIndustry");
        return saved;
    }

    public boolean deleteIndustry(Integer id) {
        boolean delete = false;
        try {
            Industry industry = new Industry();
            industry.setIndustryId(id);
            setupsDAO.delete(industry);
            delete = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteIndustry", e);
        }
        return delete;
    }

    public boolean getIndustryByName(String industryTitle, Integer id) {
        boolean found = false;
        try {
            found = setupsDAO.findIndustryByTitle(industryTitle, id);

        } catch (Exception e) {
            logger.error("Exception: SetupService -> getIndustryByName", e);
        }
        return found;
    }

    public List<Folder> getCommunicationFolderList() {
        List<Folder> list = new ArrayList<>();
        try {
            list = setupsDAO.findAllFolders();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveIndustres", e);
        }
        return list;
    }

    public boolean saveCommunicationFolder(Folder folder, Integer currentUserId) {
        boolean save = false;
        try {
            if (folder.getFolderId() == null) {
                folder.setCreatedOn(new Date());
                folder.setCreatedBy(currentUserId);
                folder.setIsActive("Yes");
                folder.setIsDelete("No");
            }

            folder.setUpdatedBy(currentUserId);
            folder.setUpdatedOn(new Date());
            setupsDAO.saveOrUpdate(folder);
            save = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveCommunicationFolder", e);
        }
        return save;
    }

    public Folder getFolderById(Integer id) {
        Folder folder = new Folder();
        try {
            folder = setupsDAO.getFolderById(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getFolderById", e);
        }
        return folder;
    }

    public boolean getCommunicationFolderByName(String folderName, Integer id) {
        boolean found = false;
        try {
            found = setupsDAO.findCommunciationFolderByName(folderName, id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getCommunicationFolderByName", e);
        }
        return found;
    }

    public boolean deleteCommunicationFolder(Integer id) {
        boolean delete = false;
        try {
            Folder folder = new Folder();
            folder.setFolderId(id);
            setupsDAO.delete(folder);
            delete = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteCommunicationFolder", e);
        }
        return delete;
    }

    public String getFolderNamesById(Integer folderId) {

        String name = null;
        try {
            Folder folderName = setupsDAO.getFolderById(folderId);
            name = folderName.getFolderName();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getFolderNamesById", e);
        }
        return name;
    }

    public List<MessageType> getMessageTypeList(Integer userId) {
        List<MessageType> finalList = new ArrayList<>();
        try {
            List<MessageType> messageTypeList = setupsDAO.findAllMessageTypes();
            //iterate ids 
            for (MessageType message : messageTypeList) {
                Integer folderId = message.getId().getFolderId();
                if (folderId != 0) {
                    message.setFolderNames(this.getFolderNamesById(folderId));
                }
                finalList.add(message);
            }

        } catch (Exception e) {
            logger.error("Exception: SetupService -> getMessageTypeList", e);
        }
        return finalList;
    }

    public boolean saveMessageTypes(MessageType messageType, Integer currentUserId) {
        boolean saved = false;
        try {
            if (messageType.getId().getMessageTypeId() == 0) {
                messageType.setCreatedBy(currentUserId);
                messageType.setCreatedOn(new Date());
                messageType.setIsActive("Yes");
                messageType.setIsDelete("No");
            }

            messageType.setUpdatedBy(currentUserId);
            messageType.setUpdatedOn(new Date());
            if (messageType.getId() != null && messageType.getId().getMessageTypeId() != 0 && !messageType.getAssociatedType()) {
                setupsDAO.deleteMessageType(messageType.getId().getMessageTypeId());
                messageType.getId().setMessageTypeId(0);
            }
            setupsDAO.saveOrUpdate(messageType);
            saved = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveMessageTypes", e);
        }
        return saved;
    }

    public MessageType getMessageTypeById(Integer id) {
        MessageType messageType = new MessageType();
        try {
            messageType = setupsDAO.getMessageTypeById(id);
            if (messageType.getCampaignMessageses().size() > 0) {
                messageType.setAssociatedType(Boolean.TRUE);
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getMessageTypeById", e);
        }
        return messageType;
    }

    public boolean deleteMessageType(Integer id) {
        boolean delete = false;
        try {

            delete = setupsDAO.deleteMessageType(id);

        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteMessageType", e);
        }
        return delete;
    }

    public boolean isMessageTypeExit(int id, int folderId, String title, String type) {
        boolean found = false;
        try {
            found = setupsDAO.isMessTypeExit(id, folderId, title, type);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> isMessageTypeExit", e);
        }
        return found;
    }

    public List<IntervalsType> getIntervalTypeList() {
        List<IntervalsType> list = new ArrayList<>();
        try {
            list = setupsDAO.findAllIntervalsType();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getIntervalTypeList", e);
        }
        return list;
    }

    public boolean saveIntervalTypes(IntervalsType intervalsType, Integer currentUserId) {
        boolean saved = false;
        try {

            if (intervalsType.getIntervalsTypeId() == null) {
                intervalsType.setCreatedOn(new Date());
                intervalsType.setCreatedBy(currentUserId);
            }
            intervalsType.setUpdatedBy(currentUserId);
            intervalsType.setUpdatedOn(new Date());

            if (intervalsType.getIntervals() != null && intervalsType.getIntervals().size() > 0) {
                for (Intervals intervals : intervalsType.getIntervals()) {
                    intervals.setIntervalsType(intervalsType);
                    intervals.setCreatedOn(new Date());
                    intervals.setCreatedBy(currentUserId);
                    intervals.setIsActive("Yes");
                    intervals.setIsDelete("No");
                    intervals.setUpdatedBy(currentUserId);
                    intervals.setUpdatedOn(new Date());
                }
            }
            setupsDAO.saveOrUpdate(intervalsType);
            saved = true;

        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveIntervalTypes", e);
        }
        return saved;
    }

    public IntervalsType getIntervalsTypeById(Integer id) {
        IntervalsType intervalType = new IntervalsType();
        try {
            intervalType = setupsDAO.getIntervalsTypeById(id);
            intervalType.getIntervals();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getIntervalsTypeById", e);
        }
        return intervalType;
    }

    public boolean deleteIntervalsType(Integer id) {
        boolean delete = false;
        IntervalsType intervalType;
        try {
            intervalType = setupsDAO.getIntervalsTypeById(id);
            intervalType.getIntervals();
            setupsDAO.delete(intervalType);
            delete = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteIntervalsType", e);
        }
        return delete;
    }

    public boolean getIntervalsByTitle(String name, Integer id) {
        boolean found = false;
        try {
            found = setupsDAO.findIntervalByTitle(name, id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getIntervalsByTitle", e);
        }
        return found;
    }

    public boolean getIntervalValue(List<String> intervalValue, Integer id) {
        List<Intervals> intervals;
        boolean found = false;
        try {
            intervals = setupsDAO.findIntervalValue(intervalValue, id);
            if (intervals != null && !intervals.isEmpty()) {
                found = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getIntervalValue", e);
        }
        return found;
    }

    public List<DrugBrand> getDrugList() {
        List<DrugBrand> finalList = new ArrayList<>();
        try {
//            finalList = setupsDAO.findAllDrugSetup();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugList", e);
        }
        return finalList;
    }

    public boolean saveDrug(DrugBrand drugBrand, Integer currentUserId) {
        boolean saved = false;
        try {
            if (drugBrand.getId() == null) {
                drugBrand.setCreatedBy(currentUserId);
                drugBrand.setCreatedOn(new Date());
            }
            drugBrand.setUpdatedOn(new Date());
            drugBrand.setUpdatedBy(currentUserId);

            if (drugBrand.getDrugs() != null && drugBrand.getDrugs().size() > 0) {
                for (Drug drug : drugBrand.getDrugs()) {
                    drug.setDrugBrand(drugBrand);
                    drug.setCreatedOn(new Date());
                    drug.setCreatedBy(currentUserId);
                    drug.setUpdatedOn(new Date());
                    drug.setUpdatedBy(currentUserId);
                }
            }
            setupsDAO.saveOrUpdate(drugBrand);
            saved = true;
        } catch (Exception e) {

            logger.error("Exception: SetupService -> saveDrug", e);
        }
        return saved;
    }

    public DrugBrand getDrugById(Integer id) {
        DrugBrand drugBrand = new DrugBrand();
        try {
            drugBrand = setupsDAO.getDrugBrandById(id);
            //drugBrand.getDrugs();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugById", e);
        }
        return drugBrand;
    }

    public boolean deleteDrug(Integer id) {
        boolean delete = false;
        DrugBrand drugBrand;
        try {
            drugBrand = setupsDAO.getDrugBrandById(id);
            //drugBrand.getDrugs();
            setupsDAO.delete(drugBrand);
            delete = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteDrug", e);
        }
        return delete;
    }

    public boolean getNdcNumber(List<String> ndcNumber, Integer id) {
        List<Drug> drug;
        boolean found = false;
        try {
            drug = setupsDAO.findNdcNumber(ndcNumber, id);
            if (drug != null && !drug.isEmpty()) {
                found = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getNdcNumber", e);
        }
        return found;
    }

    public boolean getGenericName(String name, Integer id) {
        boolean found = false;
        try {
            found = setupsDAO.findGenericName(name, id);

        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugBrandByName", e);
        }
        return found;
    }

    public List<Event> loadAllEventSetupNPData(Integer userId) {
        List<Event> eventList = new ArrayList<>();

        try {
            eventList = setupsDAO.fetchAllEventSetup();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> loadAllEventSetupNPData", e);
        }
        return eventList;
    }

    public Event getSelectedEvent(Integer eventId, String userId) {
        Event event;
        try {
            event = setupsDAO.getEventById(eventId);
            for (EventDetail eventDetail : event.getEventDetails()) {
                if (eventDetail.getFieldSelection() != null) {
                    eventDetail.setDrfValue(eventDetail.getFieldSelection());
                }
            }
        } catch (Exception e) {
            event = new Event();
            logger.error("Exception: SetupService -> getSelectedEvent", e);
        }
        return event;
    }

    public List<EventDetail> getAllEventDetail() {
        List<EventDetail> eventDetail;
        try {
            eventDetail = setupsDAO.getAllEventDetail();
        } catch (Exception e) {
            eventDetail = new ArrayList<>();
            logger.error("Exception: SetupService -> getAllEventDetail", e);

        }
        return eventDetail;
    }

    public void deleteSelectedEvent(Integer eventId, String userId) {
        try {
            Event eventTobeDeleted = getSelectedEvent(eventId, userId);
            if (eventTobeDeleted != null) {
                if (eventTobeDeleted.getEventCriteria().equalsIgnoreCase("Static")) {
                    // only delete the event because it has no details                    
                    setupsDAO.deleteEvent(eventId);
                } else {
                    // delete all related details and event
                    setupsDAO.deleteEventDetail(eventId);
                    setupsDAO.deleteEvent(eventId);
                }
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteSelectedEvent", e);
        }
    }

    public void saveUpdateEvent(Event event, Integer currentUserId) {

        try {
            event.setCreatedBy(currentUserId);
            event.setCreatedOn(new Date());
            event.setUpdatedBy(currentUserId);
            event.setUpdatedOn(new Date());
            //Going to saveOrUpdate new static event
            if (event.getEventCriteria().equalsIgnoreCase("Static")) {
                // Conversion from dynamic to static
                if (event.getEventDetails() != null) {
                    event.setEventDetails(null);
                    event.setDynamicValue(null);
                    setupsDAO.deleteBulkEventDetails(event.getEventId());
                    saveUpdateEvent(event);
                } else {
                    // Save or Update static event without changing criteria
                    saveUpdateEvent(event);
                }
            } else {
                // Remove the extra detail and detach event from event detail
                removeExtraDetails(event);
                List<EventDetail> list = event.getEventDetails();
                event.setEventDetails(null);
                event.setStaticValue(null);
                if (event.getEventId() != null) {
                    //Going to saveOrUpdate existing dynamic event
                    setupsDAO.deleteBulkEventDetails(event.getEventId());
                    setupsDAO.saveBulkEventDetails(list, event);
                    saveUpdateEvent(event);
                } else {
                    // Save New dynamic event
                    saveUpdateEvent(event);
                    setupsDAO.saveBulkEventDetails(list, event);
                }
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteSelectedEventDetail", e);
        }
    }

    private void removeExtraDetails(Event event) {
        List<EventDetail> validDetailList = new ArrayList<>();
        for (EventDetail detail : event.getEventDetails()) {
            if (detail.getDataSet() != null) {
                validDetailList.add(detail);
            }
        }
        event.getEventDetails().clear();
        event.setEventDetails(validDetailList);
    }

    public Boolean checkDuplicatedEvent(String eventName, Integer id) {
        Boolean isDuplicatedEvent = false;
        try {
            List<Event> eventList = setupsDAO.getEventByName(eventName, id);
            if (eventList != null && eventList.size() > 0) {
                isDuplicatedEvent = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getUserBrand", e);
        }
        return isDuplicatedEvent;
    }

    public void saveUpdateEvent(Event event) {
        try {
            setupsDAO.saveOrUpdate(event);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveUpdateEvent", e);
        }
    }

    public List<Response> getResponseList() {
        List<Response> list = new ArrayList<>();
        try {
            list = setupsDAO.findAllResponseSetup();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveIndustres", e);
        }
        return list;
    }

    public boolean saveResponse(Response response, Integer currentUserId) {
        boolean saved = false;
        try {
            if (response.getResponseId() == null) {
                response.setCreatedOn(new Date());
                response.setCreatedBy(currentUserId);
                response.setIsActive("Yes");
                response.setIsDelete("No");
            }
            if (response.getValidResponses() != null && response.getValidResponses().size() > 0) {
                for (ValidResponse validResponse : response.getValidResponses()) {
                    validResponse.setResponse(response);
                    validResponse.setCreatedOn(new Date());
                    validResponse.setCreatedBy(currentUserId);
                    validResponse.setUpdatedBy(currentUserId);
                    validResponse.setUpdatedOn(new Date());
                    validResponse.setIsActive("Yes");
                    validResponse.setIsDelete("No");
                }
            }
            response.setUpdatedBy(currentUserId);
            response.setUpdatedOn(new Date());
            setupsDAO.saveOrUpdate(response);
            saved = true;

        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveResponse", e);
        }
        return saved;
    }

    public Response getResponseById(Integer id) {
        Response response = new Response();
        try {
            response = setupsDAO.getResponseTitleById(id);
            response.getValidResponses();

        } catch (Exception e) {
            logger.error("Exception: SetupService -> getResponseById", e);
        }
        return response;
    }

    public boolean deleteResponse(Integer id) {
        boolean delete = false;
        Response response;
        try {
            response = setupsDAO.getResponseTitleById(id);
            response.getValidResponses();
            setupsDAO.delete(response);
            delete = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteResponse", e);
        }
        return delete;
    }

    public Response getResponseByName(String responseTitle, Integer id) {
        Response response = new Response();
        try {
            response = setupsDAO.getResponseTitleByName(responseTitle, id);
        } catch (Exception e) {
        }
        return response;
    }

    public List<SmtpServerInfo> getSmtpList() {
        List<SmtpServerInfo> list = new ArrayList<>();
        try {
            list = setupsDAO.findAllSmtpSetup();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getSmtpList", e);
        }
        return list;
    }

    public boolean saveSmtpSetup(SmtpServerInfo smtpServerInfo, Integer currentUserId) {
        boolean saved = false;
        try {
            setMultipleBrandId(smtpServerInfo);
            if (smtpServerInfo.getSmtpId() == null) {
                smtpServerInfo.setCreatedBy(currentUserId);
                smtpServerInfo.setCreatedOn(new Date());
                smtpServerInfo.setIsActive("Yes");
                smtpServerInfo.setIsDelete("No");
            }

            smtpServerInfo.setUpdatedOn(new Date());
            smtpServerInfo.setUpdatedBy(currentUserId);
            setupsDAO.saveOrUpdate(smtpServerInfo);

            saved = true;

        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveSmtpSetup", e);
        }
        return saved;
    }

    private void setMultipleBrandId(SmtpServerInfo smtpServerInfo) {
        if (smtpServerInfo.getSelectedBrands() != null) {
            String brands = "";
            for (Integer brand : smtpServerInfo.getSelectedBrands()) {
                brands += brand + ",";
            }
            smtpServerInfo.setBrandIds(brands);
        }
    }

    public SmtpServerInfo getSmtpById(Integer id) {
        SmtpServerInfo smtpServerInfo = new SmtpServerInfo();
        try {

            smtpServerInfo = setupsDAO.getSmtpServerById(id);

        } catch (Exception e) {

            logger.error("Exception: SetupService -> editSmtpSetup", e);
        }
        return smtpServerInfo;
    }

    public boolean deleteSmtp(Integer id) {
        boolean delete = false;
        try {
            List<Campaigns> campaignses=setupsDAO.findByNestedProperty(new Campaigns(),"smtpServerInfo.smtpId", id,Constants.HIBERNATE_EQ_OPERATOR,"", 0);
            if(CommonUtil.isNullOrEmpty(campaignses)){
                setupsDAO.delete("SmtpServerInfo", "smtpId", id);
                 delete = true;
            }
//            SmtpServerInfo smtpServerInfo = new SmtpServerInfo();
//            smtpServerInfo.setSmtpId(id);
            //setupsDAO.delete(smtpServerInfo);
           
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteSmtp", e);
        }
        return delete;
    }

    public boolean getSMTPByEmail(String email, Integer id) {
        boolean found = false;
        try {
            found = setupsDAO.findSMTPByEmail(email, id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getSMTPByEmail", e);
        }
        return found;
    }

    public List<ShortCodes> getShortCodeList() {
        List<ShortCodes> list = new ArrayList<>();

        try {
            list = setupsDAO.findAllShortCodeSetup();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getShortCodeList   ", e);
        }
        return list;
    }

    public List<WidgetUser> getWidgetUserList(String userId) {
        List<WidgetUser> list = new ArrayList<>();
        try {
            list = setupsDAO.findAllWidgetUser(userId);
            for (WidgetUser widgetUser : list) {
                Integer campaignId = widgetUser.getCampaignId();
                if (campaignId != null) {
                    widgetUser.setCampaignName(this.getCampaignNamesById(campaignId));
                }
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getWidgetUserList", e);
        }
        return list;
    }

    public boolean saveWidgetUser(WidgetUser widgetUser, String userName) {
        boolean saved = false;
        try {

            if (widgetUser.getWidgetUserId() == null) {
                widgetUser.setCreatedOn(new Date());
                widgetUser.setCreatedBy(userName);
                widgetUser.setIsActive("Yes");
            }
            if (!"Yes".equals(widgetUser.getValidateIp())) {
                widgetUser.setValidateIp("No");
            }
            if (widgetUser.getWidgetUserId() != null) {
                setupsDAO.deleteWidgetUserIpById(widgetUser.getWidgetUserId());
            }
            if (widgetUser.getWidgetUserIpaddresseses() != null && widgetUser.getWidgetUserIpaddresseses().size() > 0) {
                List<WidgetUserIpaddresses> ips = new ArrayList<>();
                for (WidgetUserIpaddresses widgetUserIpaddresses : widgetUser.getWidgetUserIpaddresseses()) {
                    if (widgetUserIpaddresses.getIpAddress().equals("Not a valid IP address")) {
                        widgetUserIpaddresses.setIpAddress(widgetUserIpaddresses.getIpAddress().replace("Not a valid IP address", ""));
                    }
                    if (widgetUserIpaddresses.getIpAddress() != null && !widgetUserIpaddresses.getIpAddress().equals("")) {
                        widgetUserIpaddresses.setWidgetUser(widgetUser);
                        ips.add(widgetUserIpaddresses);
                    }
                }
                if (!ips.isEmpty()) {
                    widgetUser.setWidgetUserIpaddresseses(ips);
                } else {
                    widgetUser.setWidgetUserIpaddresseses(null);
                }
            }
            widgetUser.setLastModifiedBy(userName);
            widgetUser.setLastModifiedOn(new Date());
            setupsDAO.saveOrUpdate(widgetUser);
            saved = true;
        } catch (Exception e) {
            logger.error("Exception: SetupsService -> saveWidgetUser", e);
        }
        return saved;
    }

    public WidgetUser getWidgetUserById(Integer id) {
        WidgetUser widgetUser = new WidgetUser();
        try {
            widgetUser = setupsDAO.getWidgetUserById(id);
            widgetUser.getWidgetUserIpaddresseses();

        } catch (Exception e) {
            logger.error("Exception: SetupService -> getWidgetUserById", e);
        }
        return widgetUser;
    }

    public boolean deleteWidgetUser(Integer id) {
        boolean delete = false;
        WidgetUser widgetUser;
        try {
            widgetUser = setupsDAO.getWidgetUserById(id);
            widgetUser.getWidgetUserIpaddresseses();
            setupsDAO.delete(widgetUser);
            delete = true;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteWidgetUser", e);
        }
        return delete;
    }

    public boolean isUserNameValid(String userName, Integer userId) {
        boolean flag = false;
        try {
            List<WidgetUser> widgetUser = setupsDAO.isUserNameValid(userName, userId);
            if (widgetUser != null && widgetUser.size() > 0) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> isUserNameValid", e);
        }
        return flag;
    }

    public List<Campaigns> getAllCampaigns(String userId) {
        List<Campaigns> campaignList = new ArrayList<>();
        try {
            campaignList = setupsDAO.findAllCampaigns(userId);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getAllCampaigns", e);
        }
        return campaignList;
    }

    public String getCampaignNamesById(Integer campaignId) {

        String name = null;
        try {
            Campaigns campaigns = setupsDAO.getCampaignById(campaignId);
            name = campaigns.getCampaignName();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getFolderNamesById", e);
        }
        return name;
    }

    public LinkedHashMap<String, String> irfFieldList() {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        try {
            list = setupsDAO.irfFieldList();

        } catch (Exception e) {
            logger.error("Exception: SetupService -> irfList", e);
        }
        return list;
    }

    public LinkedHashMap<String, String> drfFieldList() {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        try {
            list = setupsDAO.drfFieldList();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> drfFieldList", e);
        }
        return list;
    }

    public List<DrugBrand> getDrugBrandsList() {
        List<DrugBrand> list = new ArrayList<>();
        try {
            list = setupsDAO.getDrugBrands();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugBrandsList", e);
        }
        return list;
    }

    public String getDrugListByDrugBrandId(Integer drugId) {
        List<Drug> list = new ArrayList<>();
        String json = "";
        try {
            List<Drug> drugList = setupsDAO.findAllDrug(drugId);
            for (Drug drug : drugList) {
                Drug newdrug = new Drug();
                newdrug.setDrugId(drug.getDrugId());
                newdrug.setNdcstrengthvalue(drug.getStrength() + "(" + drug.getDrugGpi() + ")");
                list.add(newdrug);
            }
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugListByDrugBrandId", e);
        }
        return json;
    }

    public List<Drug> getDrugCompoundList() {
        List<Drug> list = new ArrayList<>();
        try {
            list = setupsDAO.getDrugIngredientList();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugIngredientList", e);
        }
        return list;
    }

    public Drug getDrugCompoundById(Integer id) {
        Drug drug = new Drug();
        try {
            drug = setupsDAO.getDrugCompoundById(id);
            drug.setSelectedDrugId(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugIngredientById", e);
        }
        return drug;
    }

    public List<Drug> getDrugsByDrugBrandId(Integer drugBrandId) {
        List<Drug> list = new ArrayList<>();
        try {
            List<Drug> dblist = setupsDAO.findAllDrug(drugBrandId);
            for (Drug drug : dblist) {
                Drug newdrug = new Drug();
                newdrug.setDrugId(drug.getDrugId());
                newdrug.setNdcstrengthvalue(drug.getStrength() + "(" + drug.getDrugGpi() + ")");
                list.add(newdrug);
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugsByDrugBrandId", e);
        }
        return list;
    }

    public boolean checkIngredientsDuplicate(String name, Integer id) {
        boolean duplicate = false;
        try {
            duplicate = setupsDAO.checkDuplicateIngredients(name, id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> checkIngredientsDuplicate", e);
        }
        return duplicate;
    }

    public boolean deleteDrugById(Integer id) {
        boolean deleted = false;
        try {
            deleted = setupsDAO.deleteDrugs(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteDrugById", e);
        }
        return deleted;
    }

    public String getSurveyByCampaignId(Integer campaignId) {
        List<Survey> list = new ArrayList<>();
        String json = "";
        try {
            List<Survey> surveys = setupsDAO.getSurveyListByCampaignId(campaignId);
            for (Survey survey : surveys) {
                Survey newSurvey = new Survey();
                newSurvey.setId(survey.getId());
                newSurvey.setTitle(survey.getTitle());
                list.add(newSurvey);
            }
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getSurveyByCampaignId", e);
        }
        return json;
    }

    public List<Survey> getSurveyListByCampaignId(Integer campaignId) {
        List<Survey> list = new ArrayList<>();
        try {
            list = setupsDAO.getSurveyListByCampaignId(campaignId);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getSurveyListByCampaignId", e);
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////////
    public List<PatientProfileDTO> getPatientWithoutOrder() throws Exception {
        List<Object[]> list = this.setupsDAO.getPatientWithoutOrder();
        List<PatientProfileDTO> transferRxQueueList = new ArrayList<>();
        HashMap map = new HashMap();
        for (Object[] arr : list) {
            PatientProfile patientProfile = (PatientProfile) arr[0];
            if (!map.containsKey(patientProfile.getPatientProfileSeqNo())) {
                map.put(patientProfile.getPatientProfileSeqNo(), patientProfile);
                String hasRxCard = "";
                PatientProfileDTO transferRxQueueDTO = new PatientProfileDTO();
                PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[1];
                if (patientProfile.getInsuranceFrontCardPath() != null) {
                    hasRxCard = "YES";
                } else {
                    hasRxCard = "NO";
                }
                transferRxQueueDTO.setProfileId(patientProfile.getPatientProfileSeqNo());
                transferRxQueueDTO.setRegistrationDate(DateUtil.dateToString(patientProfile.getCreatedOn(), "yyyy-MM-dd hh:mm a"));
                transferRxQueueDTO.setAllergies(AppUtil.getSafeStr(patientProfile.getAllergies(), "").length() > 0 ? "YES" : "NO");
                transferRxQueueDTO.setGender(patientProfile.getGender());
                transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
                transferRxQueueDTO.setPatientDOB(DateUtil.dateToString(patientProfile.getDob(), "yyyy-MM-dd"));
                transferRxQueueDTO.setHasRxCard(hasRxCard);
                transferRxQueueDTO.setZip(addr != null ? addr.getZip() : "");
                transferRxQueueDTO.setMobileNumber(patientProfile.getMobileNumber());
                transferRxQueueDTO.setEmailAddress(AppUtil.getSafeStr(patientProfile.getEmailAddress(), ""));
                transferRxQueueList.add(transferRxQueueDTO);
            }

        }
        return transferRxQueueList;
    }

    public boolean isSortOrderExist(Integer messageTypeId, Integer folderId, Integer sortOrder) {
        boolean duplicate = false;
        try {
            duplicate = setupsDAO.checkDuplicateSortOrder(messageTypeId, folderId, sortOrder);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> isSortOrderExist", e);
        }
        return duplicate;
    }

    public boolean updateMessageType(MessageType messageType) {
        boolean update = false;
        try {
            update = setupsDAO.updateMessageType(messageType);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> updateMessageType", e);
        }
        return update;
    }

    public boolean deleteIntervalsById(Integer id) {
        boolean deleted = false;
        try {
            deleted = setupsDAO.deleteIntervalsById(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteIntervalsById", e);
        }
        return deleted;
    }

    public boolean deleteValidResponseById(Integer id) {
        boolean deleted = false;
        try {
            deleted = setupsDAO.deleteValidResponseById(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteValidResponseById", e);
        }
        return deleted;
    }

//    public List<DrugUnits> getDrugUnitsList() {
//        List<DrugUnits> list = new ArrayList<>();
//        try {
//            list = setupsDAO.getDrugUnitsList();
//        } catch (Exception e) {
//            logger.error("Exception: SetupService -> getDrugUnitsList", e);
//        }
//        return list;
//    }

    public boolean saveRewardPoints(RewardPoints rewardPoints, Integer currentUserId) {
        boolean isSaved = false;
        try {
            if (rewardPoints.getRewardPointlist().size() > 0) {
                for (RewardPoints rp : rewardPoints.getRewardPointlist()) {
                    if (rp.getId() == null) {
                        rp.setCreatedBy(currentUserId);
                        rp.setCreatedOn(new Date());
                    }
                    rp.setUpdatedBy(currentUserId);
                    rp.setUpdatedOn(new Date());
                    setupsDAO.merge(rp);
                    isSaved = true;
                }
            }
            if (rewardPoints.getPatientRewardLevel().size() > 0) {
                for (PatientRewardLevel rewardLevel : rewardPoints.getPatientRewardLevel()) {
                    if (rewardLevel.getId() == null) {
                        rewardLevel.setCreatedBy(currentUserId);
                        rewardLevel.setCreatedOn(new Date());
                    }
                    rewardLevel.setUpdatedBy(currentUserId);
                    rewardLevel.setUpdatedOn(new Date());
                    setupsDAO.merge(rewardLevel);
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception: SetupService -> saveRewardPoints", e);
        }
        return isSaved;
    }

    public RewardPoints getRewardPoints() {
        RewardPoints rewardPoints = new RewardPoints();
        try {
            if (setupsDAO.getRewardPointsList().size() > 0) {
                rewardPoints.setRewardPointlist(setupsDAO.getRewardPointsList());
                rewardPoints.setPatientRewardLevel(setupsDAO.getRecordList(new PatientRewardLevel()));
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getRewardPoints", e);
        }
        return rewardPoints;
    }

    public boolean saveFeeSettings(FeeSettings feeSettings, Integer currentUserId) {
        boolean isSaved = false;
        try {
            if (feeSettings.getFeeSettingslist().size() > 0) {
                for (FeeSettings fs : feeSettings.getFeeSettingslist()) {
                    if (fs.getId() == null) {
                        fs.setCreatedBy(currentUserId);
                        fs.setCreatedOn(new Date());
                    }
                    fs.setUpdatedBy(currentUserId);
                    fs.setUpdatedOn(new Date());
                    setupsDAO.merge(fs);
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception: SetupService -> savefeeSettings", e);
        }
        return isSaved;
    }

    public FeeSettings getFeeSettings() {
        FeeSettings feeSettings = new FeeSettings();
        try {
            if (setupsDAO.getFeeSettingsList().size() > 0) {
                feeSettings.setFeeSettingslist(setupsDAO.getFeeSettingsList());
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getFeeSettings", e);
        }
        return feeSettings;
    }

    public boolean saveDeliveryPreferences(DeliveryPreferences deliveryPreferences, Integer currentUserId) {
        boolean isSaved = false;
        try {
            if (deliveryPreferences.getDeliveryPreferencesList().size() > 0) {
                for (DeliveryPreferences dp : deliveryPreferences.getDeliveryPreferencesList()) {
                    if (dp.getId() == null) {
                        dp.setCreatedBy(currentUserId);
                        dp.setCreatedOn(new Date());
                    }
                    dp.setUpdatedBy(currentUserId);
                    dp.setUpdatedOn(new Date());
                    setupsDAO.merge(dp);
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception: SetupService -> savedeliveryPreferences", e);
        }
        return isSaved;
    }

    public DeliveryPreferences getDeliveryPreferences() {
        DeliveryPreferences deliveryPreferences = new DeliveryPreferences();
        try {
            if (setupsDAO.getDeliveryPreferencesList().size() > 0) {
                deliveryPreferences.setDeliveryPreferencesList(setupsDAO.getDeliveryPreferencesList());
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDeliveryPreferences", e);
        }
        return deliveryPreferences;
    }

    public boolean isResponseAssociated(Integer id) {
        boolean isAssociated = false;
        try {
            Response response = setupsDAO.getResponseTitleById(id);
            if (response.getCampaignMessagesResponses().size() > 0) {
                isAssociated = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> isResponseAssociated", e);
        }
        return isAssociated;
    }

    public boolean saveRxTransfer(TransferDetail transferDetail, Integer currentUserId) throws Exception {
        boolean isSaved = false;
//        try {
        if (transferDetail.getId() == null) {
            transferDetail.setCreatedBy(currentUserId);
            transferDetail.setCreatedOn(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        } else {
            TransferDetail oldTransferDetail = setupsDAO.getTransferDetailById(transferDetail.getId());
            transferDetail.setCreatedBy(oldTransferDetail.getCreatedBy());
            transferDetail.setCreatedOn(oldTransferDetail.getCreatedOn());
        }
        transferDetail.setUpdatedBy(currentUserId);
        transferDetail.setUpdatedOn(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //setupsDAO.merge(transferDetail);
        setupsDAO.saveOrUpdate(transferDetail);
        TransferRequest transferRequest = (TransferRequest) this.setupsDAO.getRecordById(new TransferRequest(),
                transferDetail.getRequestId());
        PatientProfile patientProfile = (PatientProfile) this.setupsDAO.getRecordById(new PatientProfile(),
                transferRequest.getPatientId());
        ///////////////////////////////////////////////
        if (patientProfile != null) {
            Order order = saveOrderByTransferDetail(transferDetail, patientProfile);
            if (order != null) {
                String drugName = AppUtil.getSafeStr(transferDetail.getDrugName(), "");
//                    if(drugName.startsWith(","))
//                    {
//                        drugName=transferDetail.getDrugName().substring(1);
//
//                    }
//                    drugName=drugName.split(",")[0];
                //transferDetail.setDrugName(drugName);
                int i = drugService.updateDrugInfoInOrder(patientProfile, transferDetail.getDrugName(), transferDetail.getDrugType(), transferDetail.getStrength(),
                        "" + transferDetail.getQuantity(), order.getId(), "Transfer Rx", true);
                if (transferDetail.getStatus() != null && transferDetail.getStatus().trim().equalsIgnoreCase("Transfered")) {
                    saveTransferNotificationMessages(transferDetail);
                    List lst = this.setupsDAO.findByProperty(new RewardPoints(),
                            "type", "Transfer Rx", Constants.HIBERNATE_EQ_OPERATOR, "", 0);
                    if (lst != null && lst.size() > 0) {
                        RewardPoints rewardPoints = (RewardPoints) lst.get(0);
                        RewardHistory history = new RewardHistory();
                        history.setPatientId(patientProfile.getPatientProfileSeqNo());
                        history.setDescription("Transfer Rx");
                        history.setType("PLUS");
                        history.setPoint(rewardPoints.getPoint().intValueExact());
                        history.setCreatedDate(new Date());
                        history.setOrder(order);
                        this.setupsDAO.save(history);
                    }
                }
                isSaved = true;
            }
        } else {
            isSaved = false;
            throw new Exception("No patient with given id " + transferRequest.getPatientId() + " exists.");
        }

        //////////////////////////////////////////////
//            isSaved = true;
//        } catch (Exception e) {
//            isSaved = false;
//            logger.error("Exception: SetupService -> saveRxTransfer", e);
//        }
        return isSaved;
    }

    public Order saveOrderByTransferDetail(String rxNo, PatientProfile patientProfile) {
        try {
            TransferDetail transferDetail = new TransferDetail();
            List lst = this.setupsDAO.findByProperty(transferDetail, "rxNumber", "rxNo", 0, "", 0);
            if (lst != null & lst.size() > 0) {
                transferDetail = (TransferDetail) lst.get(0);
            }
            Order order = saveOrderByTransferDetail(transferDetail, patientProfile);
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
            //Logger.getLogger(SetupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void saveTransferNotificationMessages(TransferDetail transfer) throws Exception {
        TransferRequest transferRequest = (TransferRequest) setupsDAO.getRecordById(new TransferRequest(), transfer.getRequestId());//.findTransferRequestById(transferDetail.getRequestId());
        PatientProfile patientprofileInfo = (PatientProfile) setupsDAO.getRecordById(new PatientProfile(), transferRequest.getPatientId());//.findPatientProfileById(transferRequest.getPatientId());
        if (patientprofileInfo != null) {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageTypeId msgTypeId = new MessageTypeId();
            msgTypeId.setMessageTypeId(2);
            msgTypeId.setFolderId(2);
            MessageType msgType = new MessageType();
            msgType.setId(msgTypeId);
            notificationMessages.setMessageType(msgType);
            notificationMessages.setSubject(Constants.TRANSFER_RECIEVED_SUBJECT);
            String msg = Constants.TRANSFER_RECIEVED_MSG;
            notificationMessages.setMessageText(msg.replace("[_transferid_]", "" + transfer.getRxNumber())
                    .replace("[_drugname_]", transfer.getDrugName().replace(",", "") + " " + transfer.getStrength())
                    .replace("[_type_]", transfer.getDrugType()).replace("[_qty_]", "" + transfer.getQuantity()));
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setPatientProfile(patientprofileInfo);
            notificationMessages.setTransferDetail(transfer);
            setupsDAO.save(notificationMessages);
        }
    }

    private Order saveOrderByTransferDetail(TransferDetail transferDetail, PatientProfile patientprofileInfo) throws Exception {
        logger.info("Save RxTransfer Order ");
        Order order = new Order();
        if (transferDetail != null) {
            TransferRequest transferRequest = (TransferRequest) setupsDAO.getRecordById(new TransferRequest(), transferDetail.getRequestId());//.findTransferRequestById(transferDetail.getRequestId());

            if (transferDetail.getStatus() != null && transferDetail.getStatus().trim().equalsIgnoreCase("Transfered")
                    && patientprofileInfo != null) {
                if (transferRequest != null && transferRequest.getId() != null) {
                    order.setFirstName(transferDetail.getFirstName());//patientprofileInfo.getFirstName());
                    order.setLastName(transferDetail.getLastName());//patientprofileInfo.getLastName());
                    order.setPatientProfile(new PatientProfile(transferRequest.getPatientId()));
                    order.setOrderType(Constants.ORDERTYPE_TRANSFER);
                    order.setViewStatus(Constants.VIEW_STATUS_PENDING);//.VIEW_STATUS_CLOSED);
                    if (transferDetail != null) {
                        order.setTransferDetail(transferDetail);
                    } else {
                        order.setTransferDetail(null);
                    }
                    //PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) setupsDAO.findPatientDeliveryAddressById(patientprofileInfo.getId());
                    PatientDeliveryAddress patientDeliveryAddress = setupsDAO.findPatientDeliveryAddressById(patientprofileInfo.getPatientProfileSeqNo());
                    if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null) {
                        order.setAddressLine2(patientDeliveryAddress.getDescription());
                        order.setShippingAddress(patientDeliveryAddress.getAddress());
                        order.setCity(patientDeliveryAddress.getCity());
                        order.setZip(patientDeliveryAddress.getZip());
                        order.setApartment(patientDeliveryAddress.getApartment());
                        if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                            order.setState(patientDeliveryAddress.getState().getName());
                        }
                    }
                    order.setDrugName(transferDetail.getDrugName());
                    if (transferRequest.getQuantity() != null) {
                        order.setQty(transferRequest.getQuantity().toString());
                    }
                    if (transferRequest.getDeliveryFee() != null) {
                        order.setPayment(transferRequest.getDeliveryFee().doubleValue());
                    }
                    
                    OrderStatus orderStatus = (OrderStatus) setupsDAO.getRecordById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
                    if (orderStatus != null && orderStatus.getId() != null) {
                        order.setOrderStatus(orderStatus);
                    }
                    order.setOrderHistory(null);
                    order.setCreatedOn(new Date());
                    order.setUpdatedOn(new Date());
                    setupsDAO.save(order);
                }
            }
        }
        return order;
    }

    public List<TransferRequest> getTransferRequestList() {
        List<TransferRequest> list = new ArrayList<>();
        try {
            List<TransferRequest> dbList = setupsDAO.getRecordList(new TransferRequest());
            if (dbList.size() > 0) {
                for (TransferRequest request : dbList) {
                    request.setTransferDetail(setupsDAO.getTransferDetailByRequestId(request.getId()));
                    request.setPatientProfile(setupsDAO.getPatientProfileById(request.getPatientId()));
                    list.add(request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: SetupService -> getTransferRequestList", e);
        }
        return list;
    }

    public List<TransferRequest> getPendingTransferRequestList() {
        List<TransferRequest> list = new ArrayList<>();
        try {
            List<TransferRequest> dbList = setupsDAO.getRecordList(new TransferRequest());
            if (dbList.size() > 0) {
                for (TransferRequest request : dbList) {
                    request.setTransferDetail(setupsDAO.getTransferDetailByRequestId(request.getId()));
                    request.setPatientProfile(setupsDAO.getPatientProfileById(request.getPatientId()));
                    if (request.getTransferDetail() == null || AppUtil.getSafeStr(
                            request.getTransferDetail().getStatus(), "").equalsIgnoreCase("Pending")) {
                        list.add(request);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: SetupService -> getTransferRequestList", e);
        }
        return list;
    }

    public TransferRequest getTransferRequestById(Integer id) {
        TransferRequest transferRequest = new TransferRequest();
        try {
            transferRequest = setupsDAO.getTransferRequestById(id);
            if (transferRequest != null) {
                if (transferRequest.getPatientId() != null) {
                    PatientProfile patientProfile = setupsDAO.getPatientProfileById(transferRequest.getPatientId());
                    transferRequest.setPatientProfile(CommonUtil.populateProfileUserData(patientProfile));
                }
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getTransferRequestById", e);
        }
        return transferRequest;
    }

    public TransferDetail getTransferDetailById(Integer id) {
        TransferDetail transferDetail = new TransferDetail();
        try {
            transferDetail = setupsDAO.getTransferDetailByRequestId(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getTransferDetailById", e);
        }
        return transferDetail;
    }

    public boolean isRxNumberExist(String rxNumber, Integer id) {
        boolean isDuplicate = false;
        try {
            isDuplicate = setupsDAO.isRxNumberExist(rxNumber, id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> isRxNumberExist", e);
        }
        return isDuplicate;
    }

    public String getDrugsByDrugBrandName(String drugName) {
        List<Drug> drugList = new ArrayList<>();
        String json = "";
        try {
            List<Drug> list = setupsDAO.getDrugsByDrugBrandName(drugName);
            for (Drug drug : list) {
                Drug newDrug = new Drug();
                newDrug.setStrength(drug.getStrength());
                drugList.add(newDrug);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(drugList);
        } catch (Exception e) {
            json = null;
            logger.error("Exception: SetupService -> getDrugsByDrugBrandName", e);
        }
        return json;
    }

    public List<Drug> getDrugsByDrugName(String drugName) {
        List<Drug> list = new ArrayList<>();
        try {
            list = setupsDAO.getDrugsByDrugBrandName(drugName);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugsByDrugName", e);
        }
        return list;
    }

    public List<DrugAdditionalMargin> getDrugAdditionalMarginList() {
        logger.info("Begin: SetupService -> getDrugAdditionalMarginList");
        List<DrugAdditionalMargin> list = new ArrayList<>();
        try {
            list = setupsDAO.getDrugAdditionalMarginList();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugAdditionalMarginList", e);
        }
        logger.info("End: SetupService -> getDrugAdditionalMarginList");
        return list;
    }

    public boolean saveDrugAdditionalMargin(DrugAdditionalMargin drugAdditionalMargin, Integer currentUserId) {
        boolean isSaved;
        try {
            if (drugAdditionalMargin.getId() == null) {
                drugAdditionalMargin.setCreatedBy(currentUserId);
                drugAdditionalMargin.setCreatedOn(new Date());
            } else {
                DrugAdditionalMargin oldAdditionalMargin = (DrugAdditionalMargin) setupsDAO.getRecordById(new DrugAdditionalMargin(), drugAdditionalMargin.getId());
                drugAdditionalMargin.setCreatedBy(oldAdditionalMargin.getCreatedBy());
                drugAdditionalMargin.setCreatedOn(oldAdditionalMargin.getCreatedOn());
            }
            drugAdditionalMargin.setUpdatedBy(currentUserId);
            drugAdditionalMargin.setUpdatedOn(new Date());

            for (DrugAdditionalMarginPrices additionalMarginPrices : drugAdditionalMargin.getDrugAdditionalMarginPricesList()) {
                additionalMarginPrices.setDrugAdditionalMargin(drugAdditionalMargin);
                if (additionalMarginPrices.getId() == null) {
                    additionalMarginPrices.setCreatedBy(currentUserId);
                    additionalMarginPrices.setCreatedOn(new Date());
                } else {
                    DrugAdditionalMarginPrices oldAdditionalMarginPrices = (DrugAdditionalMarginPrices) setupsDAO.getRecordById(new DrugAdditionalMarginPrices(), additionalMarginPrices.getId());
                    additionalMarginPrices.setCreatedBy(oldAdditionalMarginPrices.getCreatedBy());
                    additionalMarginPrices.setCreatedOn(oldAdditionalMarginPrices.getCreatedOn());
                }
                additionalMarginPrices.setUpdatedBy(currentUserId);
                additionalMarginPrices.setUpdatedOn(new Date());
            }
            setupsDAO.merge(drugAdditionalMargin);
            isSaved = true;
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception: SetupService -> saveDrugAdditionalMargin", e);
        }
        return isSaved;
    }

    public DrugAdditionalMargin getDrugAdditionalMarginById(Integer id) {
        DrugAdditionalMargin drugAdditionalMargin = new DrugAdditionalMargin();
        try {
            drugAdditionalMargin = setupsDAO.getDrugAdditionalMarginById(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugAdditionalMarginById", e);
        }
        return drugAdditionalMargin;
    }

    public boolean deleteDrugAdditionalMargin(Integer id) {
        boolean delete;
        DrugAdditionalMargin drugAdditionalMargin;
        try {
            drugAdditionalMargin = setupsDAO.getDrugAdditionalMarginById(id);
            setupsDAO.delete(drugAdditionalMargin);
            delete = true;
        } catch (Exception e) {
            delete = false;
            logger.error("Exception: SetupService -> deleteDrugAdditionalMargin", e);
        }
        return delete;
    }

    public String getDrugAdditionalMarginPricesByDrugBrandId(Integer id) {
        List<DrugAdditionalMarginPrices> list = new ArrayList<>();
        String json = "";
        try {
            DrugAdditionalMargin drugAdditionalMargin = new DrugAdditionalMargin();
            for (DrugAdditionalMarginPrices damp : setupsDAO.getDrugAdditionalMarginPriceses(id)) {
                DrugAdditionalMarginPrices newMarginPrices = new DrugAdditionalMarginPrices();

                newMarginPrices.setId(damp.getId());
                newMarginPrices.setDrugQtyFrom(damp.getDrugQtyFrom());
                newMarginPrices.setDrugQtyTo(damp.getDrugQtyTo());
                newMarginPrices.setDrugMarginValue(damp.getDrugMarginValue());
                newMarginPrices.setCreatedBy(damp.getCreatedBy());
                newMarginPrices.setCreatedOn(damp.getCreatedOn());
                if (damp.getDrugAdditionalMargin() != null && damp.getDrugAdditionalMargin().getId() != null) {
                    drugAdditionalMargin.setDrugAdditionalMarginPricesList(null);
                    //drugAdditionalMargin.setDrugCategory(null);
                    drugAdditionalMargin.setId(damp.getDrugAdditionalMargin().getId());
                    drugAdditionalMargin.setCreatedBy(damp.getDrugAdditionalMargin().getCreatedBy());
                    drugAdditionalMargin.setCreatedOn(damp.getDrugAdditionalMargin().getCreatedOn());
                }
                newMarginPrices.setDrugAdditionalMargin(drugAdditionalMargin);
                list.add(newMarginPrices);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            json = objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugAdditionalMarginById", e);
        }
        return json;
    }

//    public List<DrugCategory> getDrugCategorysList() {
//        List<DrugCategory> list = new ArrayList<>();
//        try {
//            list = setupsDAO.getDrugCategoryList();
//        } catch (Exception e) {
//            logger.error("Exception: SetupService -> getDrugCategorysList", e);
//        }
//        return list;
//    }

    public List<PharmacyZipCodes> getPharmacyZipCodesList() {
        List<PharmacyZipCodes> list = new ArrayList<>();
        try {
            list = setupsDAO.getPharmacyZipCodesList();
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getPharmacyZipCodesList", e);
        }
        return list;
    }

    public boolean savePharmacyZipCodes(PharmacyZipCodes pharmacyZipCodes, Integer currentUserId) {
        boolean isSaved = false;
        try {
            if (pharmacyZipCodes.getId() == null) {
                pharmacyZipCodes.setCreatedBy(currentUserId);
                pharmacyZipCodes.setCreatedOn(new Date());
            } else {
                PharmacyZipCodes oldPharmacyZipCodes = (PharmacyZipCodes) setupsDAO.getRecordById(new PharmacyZipCodes(), pharmacyZipCodes.getId());
                pharmacyZipCodes.setCreatedBy(oldPharmacyZipCodes.getCreatedBy());
                pharmacyZipCodes.setCreatedOn(oldPharmacyZipCodes.getCreatedOn());
                pharmacyZipCodes.setPharmacyName(oldPharmacyZipCodes.getPharmacyName());
                pharmacyZipCodes.setPharmacyZip(oldPharmacyZipCodes.getPharmacyZip());
                setupsDAO.deleteDeliveryDistanceFeeByPharmacyId(pharmacyZipCodes.getId());
            }
            pharmacyZipCodes.setUpdatedOn(new Date());
            pharmacyZipCodes.setUpdatedBy(currentUserId);

            Integer preferencesId = null;
            for (DeliveryDistanceFee ddf : pharmacyZipCodes.getDeliveryDistanceFeesList()) {
                ddf.setPharmacyZipCodes(pharmacyZipCodes);
                ddf.setDeliveryFee(ddf.getFee());
                if (ddf.getId() == null) {
                    ddf.setCreatedBy(currentUserId);
                    ddf.setCreatedOn(new Date());
                }
                ddf.setUpdatedBy(currentUserId);
                ddf.setUpdatedOn(new Date());
                if (ddf.getDeliveryPreferenceses() != null && ddf.getDeliveryPreferenceses().getId() != null) {
                    preferencesId = ddf.getDeliveryPreferenceses().getId();
                    setupsDAO.updateDeliveryPreferences(ddf.getDeliveryPreferenceses().getId(), ddf.getDescription(), currentUserId);
                } else if (ddf.getDeliveryPreferenceses() == null && preferencesId != null) {
                    ddf.setDeliveryPreferenceses(new DeliveryPreferences(preferencesId));
                }
            }
            isSaved = true;
            setupsDAO.merge(pharmacyZipCodes);
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception: SetupService -> savePharmacyZipCodes", e);
        }
        return isSaved;
    }

    public PharmacyZipCodes getPharmacyZipCodesById(Integer id) {
        PharmacyZipCodes pharmacyZipCodes = new PharmacyZipCodes();
        try {
            pharmacyZipCodes = setupsDAO.getPharmacyZipCodesById(id);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getPharmacyZipCodesById", e);
        }
        return pharmacyZipCodes;
    }

    public String getDeliveryPreferencesDistanceById(Integer id) {
        String json = "";
        List<DeliveryPreferencesDistance> newList = new ArrayList<>();
        try {
            PharmacyZipCodes pharmacyZipCodes = new PharmacyZipCodes();
            for (DeliveryPreferencesDistance deliveryPreferencesDistance : setupsDAO.getDeliveryPreferencesDistanceById(id)) {
                DeliveryPreferencesDistance dpd = new DeliveryPreferencesDistance();
                dpd.setId(deliveryPreferencesDistance.getId());
                dpd.setFee(deliveryPreferencesDistance.getFee());
                dpd.setMilesFrom(deliveryPreferencesDistance.getMilesFrom());
                dpd.setMilesTo(deliveryPreferencesDistance.getMilesTo());

                if (deliveryPreferencesDistance.getPharmacyZipCodes() != null && deliveryPreferencesDistance.getPharmacyZipCodes().getId() != null) {
                    pharmacyZipCodes.setId(deliveryPreferencesDistance.getPharmacyZipCodes().getId());
                    pharmacyZipCodes.setPharmacyName(deliveryPreferencesDistance.getPharmacyZipCodes().getPharmacyName());
                    pharmacyZipCodes.setPharmacyZip(deliveryPreferencesDistance.getPharmacyZipCodes().getPharmacyZip());
                    dpd.setPharmacyZipCodes(pharmacyZipCodes);
                }
                newList.add(dpd);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            json = objectMapper.writeValueAsString(newList);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDeliveryPreferencesDistanceById", e);
        }
        return json;
    }

//    public List<DrugCategory> getDrugCategoryList() {
//        List<DrugCategory> list = new ArrayList<>();
//        try {
//            list = setupsDAO.getRecordList(new DrugCategory());
//        } catch (Exception e) {
//            logger.error("Exception: SetupService -> getDrugCategoryList", e);
//        }
//        return list;
//    }

//    public List<DrugTherapyClass> getDrugTherapyClassList() {
//        List<DrugTherapyClass> list = new ArrayList<>();
//        try {
//            list = setupsDAO.getRecordList(new DrugTherapyClass());
//        } catch (Exception e) {
//            logger.error("Exception: SetupService -> getDrugTherapyClassList", e);
//        }
//        return list;
//    }

    public List<DrugGenericTypes> getDrugGenericNameList() {
        List<DrugGenericTypes> list = new ArrayList<>();
        try {
            list = setupsDAO.getRecordList(new DrugGenericTypes());
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugGenericTypesList", e);
        }
        return list;
    }

    public List<DrugBrand> getDrugBrandList() {
        List<DrugBrand> list = new ArrayList<>();
        try {
            list = setupsDAO.getRecordList(new DrugBrand());
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDrugBrandList", e);
        }
        return list;
    }

    public boolean saveDeliveryDsitances(DeliveryDistances deliveryDsitances, Integer currentUserId) {
        boolean isSaved = false;
        try {
            for (DeliveryDistances dd : deliveryDsitances.getDeliveryDsitanceses()) {
                if (dd.getId() == null) {
                    dd.setCreatedBy(currentUserId);
                    dd.setCreatedOn(new Date());
                }
                dd.setUpdatedBy(currentUserId);
                dd.setUpdatedOn(new Date());
                setupsDAO.saveOrUpdate(dd);
                isSaved = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SetupService -> saveDeliveryDsitances", e);
        }
        return isSaved;
    }

    public DeliveryDistances getDeliveryDsitances() {
        DeliveryDistances deliveryDsitances = new DeliveryDistances();
        try {
            List<DeliveryDistances> list = setupsDAO.getRecordList(new DeliveryDistances());
            deliveryDsitances.setDeliveryDsitanceses(list);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDeliveryDsitances", e);
        }
        return deliveryDsitances;
    }

    public boolean deleteDeliveryDistances(Integer id) {
        boolean isDeleted = false;
        try {
            DeliveryDistances deliveryDistances = (DeliveryDistances) setupsDAO.getRecordById(new DeliveryDistances(), id);
            if (deliveryDistances.getDeliveryDistanceFeeList().isEmpty()) {
                isDeleted = setupsDAO.deleteDeliveryDistances(id);
            }

        } catch (Exception e) {
            logger.error("Exception: SetupService -> deleteDeliveryDistances", e);
        }
        return isDeleted;
    }

    public List<DeliveryDistances> getDeliveryDistancesList() {
        List<DeliveryDistances> list = new ArrayList<>();
        try {
            list = setupsDAO.getRecordList(new DeliveryDistances());
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDeliveryDistancesList", e);
        }
        return list;
    }

    public List<DeliveryPreferences> getDeliveryPreferencesList() {
        List<DeliveryPreferences> list = new ArrayList<>();
        try {
            list = setupsDAO.getRecordList(new DeliveryPreferences());
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDeliveryPreferencesList", e);
        }
        return list;
    }

    public String getDeliveryDistances(Integer id, Integer dprefId) {
        List<DeliveryDistanceFeeDTO> list = new ArrayList<>();
        String json = "";
        try {
            for (DeliveryDistanceFee ddf : setupsDAO.getDeliveryDistanceFeesByDistanceId(id, dprefId)) {
                DeliveryDistanceFeeDTO dTO = new DeliveryDistanceFeeDTO();
                if (ddf.getDeliveryDistances() != null && ddf.getDeliveryDistances().getId() != null) {
                    dTO.setId(ddf.getDeliveryDistances().getId());
                }
                if (ddf.getDeliveryPreferenceses() != null && ddf.getDeliveryPreferenceses().getId() != null) {
                    dTO.setDprefId(ddf.getDeliveryPreferenceses().getId());
                }
                dTO.setDeliveryFee(ddf.getDeliveryFee());
                dTO.setFee(ddf.getDeliveryFee().toString());
                list.add(dTO);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            json = objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception: SetupService -> getDeliveryDistances", e);
        }
        return json;
    }

    public TransferRequest findTransferRequestById(int requestId) throws Exception {
        try {
            TransferRequest transferRequest = this.setupsDAO.findTransferRequestById(requestId);
            return transferRequest;
        } catch (Exception e) {
            logger.error("Exception: SetupService -> findTransferRequestById", e);
        }
        return null;
    }
}
