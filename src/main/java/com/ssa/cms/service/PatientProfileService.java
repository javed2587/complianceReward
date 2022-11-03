package com.ssa.cms.service;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.dao.OrderDAO;
import com.ssa.cms.dao.PMSTextFlowDAO;
import com.ssa.cms.dao.PatientProfileDAO;
import com.ssa.cms.dto.ActivityHistoryDTO;
import com.ssa.cms.dto.AnswerDetailDTO;
import com.ssa.cms.dto.AppointmentRequestDTO;
import com.ssa.cms.dto.AssignedSurveyInfoDTO;
import com.ssa.cms.dto.AssignedSurveyLogsDTO;
import com.ssa.cms.dto.BMIWeeklyDetailDTO;
import com.ssa.cms.dto.BMIWeeklyDetailRapperDTO;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.BloodPressureDTO;
import com.ssa.cms.dto.CapsuleDTO;
import com.ssa.cms.dto.ComplianceRewardPointDTO;
import com.ssa.cms.dto.DeliveryDistanceFeeDTO;
import com.ssa.cms.dto.DeliveryPreferencesDTO;
import com.ssa.cms.dto.DiseaseDTO;
import com.ssa.cms.dto.DrugBrandDTO;
import com.ssa.cms.dto.DrugCategoryListDTO;
import com.ssa.cms.dto.DrugDTO;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.dto.DrugSearchesDTO;
import com.ssa.cms.dto.EnrollemntIpadDTO;
import com.ssa.cms.dto.ErrorDto;
import com.ssa.cms.dto.Faq_DTO;
import com.ssa.cms.dto.GenralDTO;
import com.ssa.cms.dto.InsuranceCardDataDTO;
import com.ssa.cms.dto.LoginDTO;
import com.ssa.cms.dto.LowerCostDrugDTO;
import com.ssa.cms.dto.MessageThreadDTO;
import com.ssa.cms.dto.NotificationMessagesDTO;
import com.ssa.cms.dto.OrderCountDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.dto.OrderStatusDTO;
import com.ssa.cms.dto.OrderTransferImagesDTO;
import com.ssa.cms.dto.PatientActivity;
import com.ssa.cms.dto.PatientActivityDTO;
import com.ssa.cms.dto.PatientAppointmentDTO;
import com.ssa.cms.dto.PatientBodyMassResultDTO;
import com.ssa.cms.dto.PatientDeliveryAddressDTO;
import com.ssa.cms.dto.PatientDeliveryAddressWithStatesDTO;
import com.ssa.cms.dto.PatientDependentDTO;
import com.ssa.cms.dto.PatientGlucoseDTO;
import com.ssa.cms.dto.PatientHeartPulseDTO;
import com.ssa.cms.dto.PatientInsuranceDetailsDTO;
import com.ssa.cms.dto.PatientProfileDTO;
import com.ssa.cms.dto.PatientProfileUpdateRequestResponseDTO;
import com.ssa.cms.dto.PatientRecentHistoryDTO;
import com.ssa.cms.dto.PharmacyPatientMessageDTO;
import com.ssa.cms.dto.PracticesDTO;
import com.ssa.cms.dto.QuestionAndOptionDTO;
//import com.ssa.cms.dto.QuestionAnserDTO;
import com.ssa.cms.dto.QuestionAnswerDTO;
import com.ssa.cms.dto.QuestionOptionDTO;
import com.ssa.cms.dto.QuestionUpdtaeDTO;
import com.ssa.cms.dto.ReadyToDeliveryRxDTO;
import com.ssa.cms.dto.RefillOrderDTO;
import com.ssa.cms.dto.ReporterMessagesDTO;
import com.ssa.cms.dto.RewardHistoryDTO;
import com.ssa.cms.dto.RewardsPointDTO;
import com.ssa.cms.dto.RxReporterSurveyResponseDTO;
import com.ssa.cms.dto.SruveyResponseDetialDTO;
import com.ssa.cms.dto.Survey2DTO;
import com.ssa.cms.dto.SurveyAnswerDTO;
import com.ssa.cms.dto.SurveyBridgeDTO;
import com.ssa.cms.dto.SurveyDto;
import com.ssa.cms.dto.SurveyQuesDTO;
import com.ssa.cms.dto.SurveyResponseAnswerDTO;
import com.ssa.cms.dto.SurveyResponseDTO;
import com.ssa.cms.dto.SurveyResponseDetailDTO;
import com.ssa.cms.dto.SurveyThemeDTO;
import com.ssa.cms.dto.SurveyTypeDTO;
import com.ssa.cms.dto.TabletDTO;
import com.ssa.cms.dto.TransferRxQueueDTO;
import com.ssa.cms.dto.surveyQuestionAnswerDTO;
import com.ssa.cms.enums.ActivitiesEnum;
import com.ssa.cms.enums.PlaceholderEnum;
import com.ssa.cms.model.ActivitesHistory;
import com.ssa.cms.model.AppointmentRequest;
import com.ssa.cms.model.AssignedSurvey;
import com.ssa.cms.model.BloodPressure;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.ComplianceRewardPoint;
import com.ssa.cms.model.DeliveryDistanceFee;
import com.ssa.cms.model.DeliveryPreferences;
import com.ssa.cms.model.DiseaseDetail;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.DrugBasic;
import com.ssa.cms.model.DrugBrand;
//import com.ssa.cms.model.DrugCategory;//not exist class and db
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.DrugGeneric;
import com.ssa.cms.model.DrugGenericTypes;
import com.ssa.cms.model.DrugDetail2;
import com.ssa.cms.model.DrugSearches;
//import com.ssa.cms.model.DrugTherapyClass;
import com.ssa.cms.model.EnrollementIpad;
import com.ssa.cms.model.Event;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.Faq;
import com.ssa.cms.model.FeeSettings;
import com.ssa.cms.model.JsonResponse;
import com.ssa.cms.model.MessageResponses;
import com.ssa.cms.model.MessageThreads;
import com.ssa.cms.model.MessageType;
import com.ssa.cms.model.MessageTypeId;
import com.ssa.cms.model.NotificationMessages;
import com.ssa.cms.model.NotificationStatus;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderBatch;
import com.ssa.cms.model.OrderCustomDocumments;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderPlaceEmail;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.OrderStatusLogs;
import com.ssa.cms.model.PatientActivitiesDetail;
import com.ssa.cms.model.PatientAllergies;
import com.ssa.cms.model.PatientAppointment;
import com.ssa.cms.model.PatientBodyMassResult;
import com.ssa.cms.model.PatientDeliveryAddress;
import com.ssa.cms.model.PatientDependent;
import com.ssa.cms.model.PatientGlucoseResults;
import com.ssa.cms.model.PatientHeartPulseResult;
import com.ssa.cms.model.PatientInsuranceDetails;
import com.ssa.cms.model.PatientPoints;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.PatientProfileNotes;
import com.ssa.cms.model.PatientProfilePreferences;
import com.ssa.cms.model.PatientRewardLevel;
import com.ssa.cms.model.PaymentsRecived;
import com.ssa.cms.model.PharmacyPatientMessage;
import com.ssa.cms.model.PharmacyZipCodes;
import com.ssa.cms.model.Practices;
import com.ssa.cms.model.PreferencesSetting;
import com.ssa.cms.model.QuestionAnswer;
import com.ssa.cms.model.ReadyToDeliverRxOrders;
import com.ssa.cms.model.RewardHistory;
import com.ssa.cms.model.RewardPoints;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.State;
import com.ssa.cms.model.StrengthJsonList;
import com.ssa.cms.model.TransferDetail;
import com.ssa.cms.model.TransferRequest;
import com.ssa.cms.model.ValidResponse;
import com.ssa.cms.model.YearEndStatementInfo;
import com.ssa.cms.model.ZipCodeCalculation;
import com.ssa.cms.model.Question;
import com.ssa.cms.model.QuestionOption;
import com.ssa.cms.model.ReporterChatSession;
import com.ssa.cms.model.ReporterMessages;
import com.ssa.cms.model.ReporterProfile;
import com.ssa.cms.model.RewardActivity;
import com.ssa.cms.model.RxRenewal;
import com.ssa.cms.model.RxReporterResponse;
import com.ssa.cms.model.RxReporterUsers;
//import com.ssa.cms.model.RxRepQusDetail;
//import com.ssa.cms.model.RxReporterOption;
//import com.ssa.cms.model.RxReporterQuestion;
import com.ssa.cms.model.Survey;
import com.ssa.cms.model.Survey2;
import com.ssa.cms.model.SurveyAnswer;
import com.ssa.cms.model.SurveyBridge;
//import com.ssa.cms.model.SurveyLogs;
import com.ssa.cms.model.SurveyQues;
import com.ssa.cms.model.SurveyQuestion;
import com.ssa.cms.model.SurveyRespDetail;
import com.ssa.cms.model.SurveyResponse;
import com.ssa.cms.model.SurveyResponseAnswerDetial;
import com.ssa.cms.model.SurveyTheme;
import com.ssa.cms.model.SurveyType2;
import com.ssa.cms.model.VegaWalletRewardPoint;
import com.ssa.cms.remainder.NextRefillReminderJob;
import com.ssa.cms.response.BMIMonthFilterWsDetails;
import com.ssa.cms.response.BMIMonthFilterWsMainResponse;
import com.ssa.cms.response.BMIMonthFilterWsResponse;
import com.ssa.cms.response.BMIYearFilterWsDetails;
import com.ssa.cms.response.BMIYearFilterWsMainResponse;
import com.ssa.cms.response.BMIYearFilterWsResponse;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DBUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import com.ssa.cms.util.FileUtil;
import com.ssa.cms.util.PropertiesUtil;
import com.ssa.cms.util.RedemptionUtil;
import com.ssa.cms.util.SMSUtil;
import com.ssa.decorator.MTDecorator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.persistence.tools.profiler.Profile;
import org.hibernate.Hibernate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

/**
 *
 * @author Saber Hussain
 */
@Service
@Transactional
public class PatientProfileService {

    @Autowired
    private PatientProfileDAO patientProfileDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    PMSTextFlowDAO textFlowDAO;
    @Autowired
    ServletContext servletContext;
     @Autowired
    private RefillReminderService refillReminderService;
    private static final Log logger = LogFactory.getLog(PatientProfileService.class.getName());

    public boolean update(Object object) {
        boolean isUpdate = false;
        try {
            patientProfileDAO.update(object);
            isUpdate = true;
        } catch (Exception e) {
            isUpdate = false;
            logger.error("Exception: PatientProfileService -> update", e);
            e.printStackTrace();
        }
        return isUpdate;
    }

    public boolean merge(Object object) {
        boolean isUpdate = false;
        try {
            patientProfileDAO.merge(object);
            isUpdate = true;
        } catch (Exception e) {
            isUpdate = false;
            logger.error("Exception: PatientProfileService -> merge", e);
        }
        return isUpdate;
    }

    public List<LoginDTO> getPatientProfileList() {
        List<LoginDTO> list = new ArrayList<>();
        try {
            List<Object[]> lstObj = patientProfileDAO.getPatientProfilesListWithDefaultAddress();//.getPatientProfilesList();
            for (Object[] arr : lstObj) {
                PatientProfile profile = (PatientProfile) arr[0];
                LoginDTO loginDTO = CommonUtil.populateProfileUserData(profile);
                if (arr[1] != null) {
                    PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[1];
                    loginDTO.setState(addr.getState() != null ? addr.getState().getAbbr() : "");
                } else {
                    loginDTO.setState("");
                }
                list.add(loginDTO);

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService -> getPatientProfileList", e);
        }
        return list;
    }

    public List<State> getStates() {
        List<State> list = new ArrayList<>();
        try {
            List<State> dblist = patientProfileDAO.getAllRecords(new State());
            for (State state : dblist) {
                State newState = new State();
                newState.setId(state.getId());
                newState.setName(state.getName());
                newState.setAbbr(state.getAbbr());
                list.add(newState);
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getStates", e);
        }
        return list;
    }

    ////////////////////////////////////////////////
    public boolean clearDeviceTokenFromProfile(PatientProfile profile) throws Exception {
        if (profile != null && profile.getPatientProfileSeqNo() != null) {
            profile.setDeviceToken(null);
            profile.setSecurityToken(null);
            String securityToken = RedemptionUtil.getRandomToken();
            logger.info("Temporary Token for Pharmecy uses: " + securityToken);
            profile.setSecurityToken(securityToken);
            this.patientProfileDAO.saveOrUpdate(profile);
            return true;
        }
        return false;

    }
    ///////////////////////////////////////////////

    public boolean savePatientInfo(PatientProfile patientProfile) {
        boolean isSaved;
        try {
            boolean isNew = patientProfile.getPatientProfileSeqNo() == null;
            if (patientProfile.getSecurityToken() != null) {
                patientProfile.setStatus(Constants.COMPLETED);
            } else {
                patientProfile.setStatus("Pending");
            }
            setPatientChild(patientProfile);

            patientProfile.setCreatedBy(0);
            patientProfile.setCreatedOn(new Date());
            patientProfile.setUpdatedBy(0);
            patientProfile.setUpdatedOn(new Date());
            patientProfileDAO.saveOrUpdate(patientProfile);
            if (isNew) {
                System.out.println("going to save preferences FOR:");
                System.out.println("Patient id " + patientProfile.getPatientProfileSeqNo());
                this.saveAllPreference(patientProfile.getPatientProfileSeqNo());
            }

            isSaved = true;
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> savePatientInfo", e);
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;
    }

    public void setPatientChild(PatientProfile patientProfile) {
        patientProfile.setNotificationMessagesList(null);
        patientProfile.setOrders(null);
        patientProfile.setPatientGlucoseResultsList(null);
        patientProfile.setYearEndStatementInfoList(null);
    }

    public boolean save(Object obj) {
        boolean isSaved;
        try {
            patientProfileDAO.save(obj);
            isSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService -> savePatientInfo", e);
            isSaved = false;
        }
        return isSaved;
    }

    public String getURL(String urlCode) {
        String urlString = "";
        try {
            urlString = patientProfileDAO.getURL(urlCode);
        } catch (Exception e) {
            logger.error("Exception", e);
        }
        return urlString;
    }

    public Object getObjectById(Object obj, int id) {
        return patientProfileDAO.getObjectById(obj, id);
    }

    public Object getObjectById(Object obj, String id) {
        return patientProfileDAO.findRecordById(obj, id);
    }

    public Order getOrderById(Object obj, String id) throws Exception {
        Order order = (Order) patientProfileDAO.findRecordById(obj, id);
        if (order != null) {
            Hibernate.initialize(order.getDrugDetail());
            if (order.getRxExpiredDate() != null) {
                order.setRxExpiredDateStr(DateUtil.dateToString(order.getRxExpiredDate(), "MM/dd/yyyy"));
            } else {
                order.setRxExpiredDateStr("N/A");
            }

            if (order.getLastRefillDate() != null) {
                order.setLastFilledDate(DateUtil.dateToString(order.getLastRefillDate(), "MM/dd/yyyy"));
            }
        }
        return order;//patientProfileDAO.findRecordById(obj, id);
    }

    public List<RewardHistory> getPatientRewardHistory(Integer id) {
        try {
            List<RewardHistory> list = this.patientProfileDAO.getRewardHistoryByProfileId(id);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
            // Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List getPatientInsuranceCards(Integer patientId) {
        List<InsuranceCardDataDTO> list = new ArrayList<>();
        List<BusinessObject> lstObj = new ArrayList();
        BusinessObject obj = new BusinessObject("patientProfile.id", patientId, Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        obj = new BusinessObject("isArchived", 0, Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        List<PatientInsuranceDetails> lst = this.patientProfileDAO.findByNestedProperty(new PatientInsuranceDetails(), lstObj, "isPrimary", Constants.HIBERNATE_DESC_ORDER);
        for (PatientInsuranceDetails patientInsuranceDetail : lst) {
            InsuranceCardDataDTO icddto = populatePatientInsuranceCardDetail(patientInsuranceDetail);
            list.add(icddto);
        }
        return list;
    }

    private InsuranceCardDataDTO populatePatientInsuranceCardDetail(PatientInsuranceDetails patientInsuranceDetail) {
        InsuranceCardDataDTO icddto = new InsuranceCardDataDTO();
        icddto.setId(patientInsuranceDetail.getId());
        if (patientInsuranceDetail.getIsPrimary() == null) {
            icddto.setIsPrimary(0);
        }
        icddto.setCardHolderRelationship(patientInsuranceDetail.getCardHolderRelationship());
        icddto.setInsuranceFrontCardPath(patientInsuranceDetail.getInsuranceFrontCardPath());
        icddto.setInsuranceBackCardPath(patientInsuranceDetail.getInsuranceBackCardPath());
        return icddto;
    }

    public PatientProfile getPatientProfileById(Integer id) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfile(id);
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {

                Long totalRewardPoint = patientProfileDAO.getTotalRewardHistoryPoints(id);
                if (totalRewardPoint != null) {
                    patientProfile.setTotalRewardPoints(totalRewardPoint);
                } else {
                    patientProfile.setTotalRewardPoints(0L);
                }
                List<PatientDeliveryAddress> deliveryAddressList = patientProfile.getPatientDeliveryAddresses();
                if (deliveryAddressList != null && deliveryAddressList.size() > 0) {
                    PatientDeliveryAddress address = deliveryAddressList.get(0);
                    patientProfile.setDefaultAddress(AppUtil.getSafeStr(address.getDescription(), "") + " " + AppUtil.getSafeStr(address.getAddress(), "") + ":"
                            + AppUtil.getSafeStr(address.getZip(), ""));
                    patientProfile.setDefaultAddresszip(AppUtil.getSafeStr(address.getZip(), ""));
                }
                List<Order> ordersList = patientProfileDAO.getOrdersListByProfileId(id);
                if (ordersList.size() > 0) {
                    patientProfile.setOrders(ordersList);
                } else {
                    patientProfile.setOrders(ordersList);
                }
            } else {
                logger.info("PatientProfile id is null: " + id);
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getPatientProfileById", e);
        }
        return patientProfile;
    }

    public boolean updatePatientProfile(PatientProfile patientProfile) {
        boolean isUpdate = false;
        try {
            if (patientProfile.getStatus() == null) {
                patientProfile.setStatus("Pending");
            }
            isUpdate = patientProfileDAO.updatePatientInfo(patientProfile);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> updatePatientProfile", e);
        }
        return isUpdate;
    }

    public String isCommunicationIdExist(String communicationId) {
        String statusCode = "";
        try {
            statusCode = patientProfileDAO.isCommunicationIdExist(communicationId);
            logger.info("StatusCode value is: " + statusCode);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> isCommunicationIdExist", e);
        }
        return statusCode;
    }

    public SmtpServerInfo getSmtpServerInfo(String campaignId) {
        SmtpServerInfo smtpServerInfo = null;
        try {
            smtpServerInfo = patientProfileDAO.getSmtpServerInfo(campaignId);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getSmtpServerInfo", e);
        }
        return smtpServerInfo;
    }

    public boolean sendVerificationCode(String mobileNumber, String message) throws Exception {
        boolean phoneValidity = false;
        try {
            logger.info("Mobile Number: " + mobileNumber);
            if (!isPatientPhoneNumberExist(mobileNumber)) {
                logger.info("This phone# is not registered.");
                throw new Exception("This phone# is not registered.");
            }
            String phonevalidationUrl = Constants.PHONE_VALIDATION_URL; //patientProfileDAO.getURL(Constants.PVURL);
            PhoneValidationService phoneValidationService = new PhoneValidationService(phonevalidationUrl);
            phoneValidity = phoneValidationService.checkPhoneValidity(mobileNumber, "", "GenRx");
            if (phoneValidity) {
                //send verification code
                MTDecorator decorator = SMSUtil.sendSmsText(mobileNumber, message);
                NotificationStatus notificationStatus = new NotificationStatus();
                notificationStatus.setEffectiveDate(new Date());
                notificationStatus.setEndDate(new Date());
                if (decorator.getErrorCode() != null) {
                    logger.info("Error code is: " + decorator.getErrorCode());
                    notificationStatus.setStatusCode(decorator.getErrorCode());
                }
                notificationStatus.setNarrative(decorator.getErrorDescription());
                notificationStatus.setPhoneNumber(EncryptionHandlerUtil.getEncryptedString(mobileNumber));
                patientProfileDAO.save(notificationStatus);
                logger.info("Messsage Sent: " + phoneValidity);
            }
        } catch (Exception ex) {
            phoneValidity = false;
            logger.error("Exception", ex);
            ex.printStackTrace();
        }
        return phoneValidity;
    }

    public List<DeliveryPreferences> getDeliveryPreferences() {
        List<DeliveryPreferences> list = new ArrayList<>();
        try {
            List<DeliveryPreferences> dbList = patientProfileDAO.getAllRecords(new DeliveryPreferences());
            for (DeliveryPreferences preference : dbList) {
                DeliveryPreferences deliveryPreferences = new DeliveryPreferences();
                deliveryPreferences.setId(preference.getId());
                deliveryPreferences.setName(preference.getName());
                deliveryPreferences.setDescription(preference.getDescription());
                list.add(deliveryPreferences);
            }
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }
        return list;
    }

    public boolean isVerificationCodeExist(String phoneNumber, Integer code) {
        boolean isExist = false;
        try {
            isExist = patientProfileDAO.isVerificationCodeExist(EncryptionHandlerUtil.getEncryptedString(phoneNumber), code);
            logger.info("isVerificationCodeExist: " + isExist);
        } catch (Exception e) {
            logger.error("Exception -> isVerificationCodeExist", e);
        }
        return isExist;
    }

    public boolean isPatientPhoneNumberExist(String phoneNumber) {
        boolean isExist = false;
        try {
            isExist = patientProfileDAO.isPatientPhoneNumberExist(EncryptionHandlerUtil.getEncryptedString(phoneNumber));
            logger.info("isPatientPhoneNumberExist: " + isExist);
        } catch (Exception e) {
            logger.error("Exception -> isPatientPhoneNumberExist", e);
        }
        return isExist;
    }

    public boolean isVerificationCodeExist(String phoneNumber, Integer code, String firebaseToken) {
        boolean isExist = false;
        try {
            //isExist = patientProfileDAO.isVerificationCodeExist(phoneNumber, code);
            List lst = this.patientProfileDAO.retrieveatientByPhoneAndCode(EncryptionHandlerUtil.getEncryptedString(phoneNumber), code);
            if (lst != null && lst.size() > 0) {
                PatientProfile profile = (PatientProfile) lst.get(0);
                profile.setDeviceToken(firebaseToken);
                this.createDeviceTokenForPatient(profile, firebaseToken);
                isExist = true;
            }
            logger.info("isVerificationCodeExist: " + isExist);
        } catch (Exception e) {
            logger.error("Exception -> isVerificationCodeExist", e);
        }
        return isExist;
    }

    public boolean isVerificationCodeExist(String phoneNumber, Integer code, String firebaseToken, String osType) {
        boolean isExist = false;
        try {
            //isExist = patientProfileDAO.isVerificationCodeExist(phoneNumber, code);
            List lst = this.patientProfileDAO.retrieveatientByPhoneAndCode(EncryptionHandlerUtil.getEncryptedString(phoneNumber), code);
            if (lst != null && lst.size() > 0) {
                PatientProfile profile = (PatientProfile) lst.get(0);
                profile.setDeviceToken(firebaseToken);
                profile.setOsType(AppUtil.getSafeStr(osType, "20"));
                this.createDeviceTokenForPatient(profile, firebaseToken);
                isExist = true;
            }
            logger.info("phoneNumber# " + phoneNumber + "isVerificationCodeExist: " + isExist);
        } catch (Exception e) {
            logger.error("Exception -> isVerificationCodeExist", e);
            e.printStackTrace();
        }
        return isExist;
    }

    public boolean savePatientInsuranceCardInfo(PatientProfile patientProfile) {
        boolean isSaved = false;
        try {
            PatientProfile oldPatientProfile = patientProfileDAO.getPatientProfile(patientProfile.getPatientProfileSeqNo());
            if (oldPatientProfile != null) {
                oldPatientProfile.setUpdatedBy(0);
                oldPatientProfile.setUpdatedOn(new Date());
                oldPatientProfile.setCardHolderRelation(patientProfile.getCardHolderRelation());
                //  oldPatientProfile.setInsuranceCardFront(patientProfile.getInsuranceCardFront());
                //oldPatientProfile.setInsuranceCardBack(patientProfile.getInsuranceCardBack());
                oldPatientProfile.setInsuranceBackCardPath(patientProfile.getInsuranceBackCardPath());
                oldPatientProfile.setInsuranceFrontCardPath(patientProfile.getInsuranceFrontCardPath());
                oldPatientProfile.setSecurityToken(patientProfile.getSecurityToken());
                oldPatientProfile.setStatus(Constants.COMPLETED);
                patientProfileDAO.merge(oldPatientProfile);
                isSaved = true;
            }
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception -> savePatientInsuranceCardInfo", e);
        }
        return isSaved;
    }

    public RewardPoints getRewardPoints(Integer id) {
        RewardPoints rewardPoints = new RewardPoints();
        try {
            rewardPoints = patientProfileDAO.getRewardPoints(id);
        } catch (Exception e) {
            logger.error("Exception -> getRewardPoints", e);
        }
        return rewardPoints;
    }

    public boolean saveRewardHistory(String description, Integer point, Integer patientId, String type) {
        boolean isSaved = false;
        try {
            logger.info("Reward Points: " + point);
            RewardHistory rewardHistory = new RewardHistory();
            rewardHistory.setPatientId(patientId);
//            rewardHistory.setDescription(description);
//            rewardHistory.setType(type);
//            rewardHistory.setPoint(point);
            rewardHistory.setCreatedDate(new Date());
            patientProfileDAO.save(rewardHistory);
            isSaved = true;
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception -> saveRewardHistory", e);
        }
        return isSaved;
    }

    public boolean saveRewardHistory(String description, Integer point, Integer patientId, String type, Order order) {
        boolean isSaved = false;
        try {
            logger.info("Reward Points: " + point);
            RewardHistory rewardHistory = new RewardHistory();
            rewardHistory.setPatientId(patientId);
//            rewardHistory.setDescription(description);
//            rewardHistory.setType(type);
//            rewardHistory.setPoint(point);
            rewardHistory.setCreatedDate(new Date());
            rewardHistory.setOrder(order);
            patientProfileDAO.save(rewardHistory);
            isSaved = true;
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception -> saveRewardHistory", e);
        }
        return isSaved;
    }

    public boolean updateVerificationCode(Integer verificationCode, String mobileNumber) {
        boolean isUpdate = false;
        try {
            isUpdate = patientProfileDAO.updateVerificationCode(verificationCode, EncryptionHandlerUtil.getEncryptedString(mobileNumber));
        } catch (Exception e) {
            e.printStackTrace();
            isUpdate = false;
            logger.error("Exception -> saveRewardHistory", e);
        }
        return isUpdate;
    }

    public PatientProfile getPatientProfileByMobileNumber(String mobileNumber) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileByMobileNumber(EncryptionHandlerUtil.getEncryptedString(mobileNumber));
        } catch (Exception e) {
            logger.error("Exception -> getPatientProfileByMobileNumber", e);
            e.printStackTrace();
        }
        return patientProfile;
    }

    public boolean deleteRecord(Integer id) {
        boolean isDeleted = false;
        try {
            List<PatientProfile> patientProfilesList = patientProfileDAO.findByNestedProperty(new PatientProfile(), "id", id, Constants.HIBERNATE_EQ_OPERATOR, null, 0);
            if (CommonUtil.isNullOrEmpty(patientProfilesList)) {
                return isDeleted;
            }
            for (PatientProfile patientProfile : patientProfilesList) {
                CommonUtil.delPatientProfileData(patientProfile, patientProfileDAO);
                isDeleted = true;
            }
        } catch (Exception e) {
            isDeleted = false;
            logger.error("Exception:: deleteRecord", e);
        }
        return isDeleted;
    }

    public boolean updateAllergies(String token, String allergies) {
        boolean isUpdate = false;
        PatientAllergies patientAllergies = new PatientAllergies();
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientProfileByToken(token);
            if (patientProfile != null) {
                patientProfile.setAllergies(allergies);
                patientProfileDAO.merge(patientProfile);

                patientAllergies.setAllergies(allergies);
                patientAllergies.setPatientProfile(patientProfile);
                patientProfileDAO.save(patientAllergies);
                isUpdate = true;
                if (isUpdate == true) {
                    saveActivitesHistory(ActivitiesEnum.Add_ALLERGY.getValue(), patientProfile, "", allergies, "","");
                }
            } else {
                logger.info("In valid token: " + token);
                isUpdate = false;
            }
        } catch (Exception e) {
            isUpdate = false;
            e.printStackTrace();
            logger.error("Exception -> updateAllergies", e);
        }
        return isUpdate;
    }

    public PatientProfile getPatientProfileByToken(String token) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileByToken(token);
            if (patientProfile != null) {
                this.populateDependentAndIsuranceCountsForPatient(patientProfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getPatientProfileByToken", e);
        }
        return patientProfile;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public PatientProfile getPatientProfileByToken(String token, Integer dependentId) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileByToken(token);
            if (patientProfile != null) {
                this.populateDependentAndIsuranceCountsForPatient(patientProfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getPatientProfileByToken", e);
        }
        return patientProfile;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public void populateDependentAndIsuranceCountsForPatient(PatientProfile profile) throws Exception {
        if (profile != null) {
            //  Long count = this.patientProfileDAO.populateDependentsCount(profile.getPatientProfileSeqNo());
            //  profile.setDependentCount(count != null ? count : 0L);
            Long insCount = this.patientProfileDAO.populateInsCardCount(profile.getPatientProfileSeqNo());
            profile.setInsCardCount(insCount != null ? insCount : 0L);

        }
    }
    //////////////////////////////////////////////////////////////////////////////////////

    public PatientProfile createDeviceTokenForPatient(String securityToken, String deviceToken) {

        try {
            PatientProfile patientProfile = this.getPatientProfileBySecurityToken(securityToken);
            if (patientProfile != null) {
                patientProfile.setDeviceToken(deviceToken);
                this.patientProfileDAO.saveOrUpdate(patientProfile);
            }
            return patientProfile;
        } catch (Exception e) {
            logger.error("Exception -> getPatientProfileByToken", e);
            return null;
        }

    }

    public PatientProfile createDeviceTokenForPatient(PatientProfile patientProfile, String deviceToken) {

        try {

            if (patientProfile != null) {
                patientProfile.setDeviceToken(deviceToken);
                this.patientProfileDAO.saveOrUpdate(patientProfile);
            }
            return patientProfile;
        } catch (Exception e) {
            logger.error("Exception -> getPatientProfileByToken", e);
            return null;
        }

    }

    public boolean updateInsuranceCardWs(String token, String cardHolderRelation, byte[] insuranceCardFront, byte[] insuranceCardBack, String imgFcPath, String imgBcPath) {
        boolean isUpdate = false;
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientProfileByToken(token);
            if (patientProfile != null) {
                patientProfile.setCardHolderRelation(cardHolderRelation);
                //  patientProfile.setInsuranceCardFront(insuranceCardFront);
                //patientProfile.setInsuranceCardBack(insuranceCardBack);
                patientProfile.setInsuranceFrontCardPath(imgFcPath);
                patientProfile.setInsuranceBackCardPath(imgBcPath);
                patientProfileDAO.merge(patientProfile);
                isUpdate = true;
            } else {
                logger.info("In valid token: " + token);
                isUpdate = false;
            }
        } catch (Exception e) {
            logger.error("Exception:: updateInsuranceCardWs", e);
        }
        return isUpdate;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //patientProfile, cardHolderRelation, 
//                     decodedFrontCard, decodedBackCard, imgFcPath, imgBcPath, memberId, planId, 
//                    effectiveDate, expiryDate, createdOn, updatedOn, insuranceProvider, 
//                    groupNumber, providerPhone, providerAddress, isPramiry
    public boolean addInsuranceCard(PatientProfile patientProfile, String cardHolderRelation,
            byte[] insuranceCardFront, byte[] insuranceCardBack, String imgFcPath, String imgBcPath,
            String memberId, Long cardId, String planId, String effectiveDate, String expiryDate, String createdOn,
            String updatedOn, String insuranceProvider, String groupNumber, String providerPhone,
            String providerAddress, int isPramiry, Integer dependentId, JsonResponse jsonResponse) throws Exception {
        boolean isAdded = false;
        String cardType = "Secondary";
        PatientInsuranceDetails insuranceCard = new PatientInsuranceDetails();
        if (cardId != 0) {
            // cardId=0 means existing card
            try {
                insuranceCard = (PatientInsuranceDetails) patientProfileDAO.findRecordById(new PatientInsuranceDetails(), cardId);
            } catch (Exception e) {
                e.getStackTrace();
                jsonResponse.setErrorMessage("There is problem to update record." + e.getMessage());
                logger.error("Exception -> getPatientProfileByToken", e);
                return false;
            }
            if (patientProfile != null) {
                List<BusinessObject> lstObj = new ArrayList();
                List<PatientInsuranceDetails> patientInsuranceDetailsList = new ArrayList<PatientInsuranceDetails>();

                if (isPramiry == 1) {
                    lstObj.add(new BusinessObject("patientProfile.id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR));
                    lstObj.add(new BusinessObject("isPrimary", 1, Constants.HIBERNATE_EQ_OPERATOR));
                    try {
                        patientInsuranceDetailsList = patientProfileDAO.findByProperty(new PatientInsuranceDetails(), lstObj, "", Constants.HIBERNATE_ASC_ORDER);
                    } catch (Exception e) {
                        e.getStackTrace();
                        jsonResponse.setErrorMessage("There is problem to update record." + e.getMessage());
                        logger.error("Exception -> getPatientProfileByToken", e);
                        return false;
                    }
                    if (!CommonUtil.isNullOrEmpty(patientInsuranceDetailsList)) {
                        patientInsuranceDetailsList.get(0).setIsPrimary(0);
                        try {
                            patientProfileDAO.update(patientInsuranceDetailsList.get(0));
                        } catch (Exception e) {
                            e.getStackTrace();
                            jsonResponse.setErrorMessage("There is problem to update record." + e.getMessage());
                            logger.error("Exception -> getPatientProfileByToken", e);
                            return false;
                        }
                    }
                    cardType = "Primary";
                }

                insuranceCard.setPatientProfile(patientProfile);
                insuranceCard.setMemberID(AppUtil.getSafeStr(memberId, ""));
                insuranceCard.setInsuranceProvider(AppUtil.getSafeStr(insuranceProvider, ""));
                insuranceCard.setGroupNumber(AppUtil.getSafeStr(groupNumber, ""));
                insuranceCard.setPlanId(AppUtil.getSafeStr(planId, ""));
                insuranceCard.setProviderPhone(AppUtil.getSafeStr(providerPhone, ""));
                insuranceCard.setProviderAddress(AppUtil.getSafeStr(providerAddress, ""));

                if (expiryDate != null) {
                    insuranceCard.setExpiryDate(DateUtil.stringToDate(expiryDate, Constants.DATE_FORMATE_SHORT));
                }
                if (createdOn != null) {
                    insuranceCard.setCreatedOn(DateUtil.stringToDate(createdOn, Constants.DATE_FORMATE_SHORT));
                }
                if (updatedOn != null) {
                    insuranceCard.setCreatedOn(DateUtil.stringToDate(updatedOn, Constants.DATE_FORMATE_SHORT));
                }
                if (cardHolderRelation != null) {
                    insuranceCard.setCardHolderRelationship(cardHolderRelation);
                }
                try {
                    if (effectiveDate != "") {
                        insuranceCard.setEffectiveDate(DateUtil.stringToDate(effectiveDate, Constants.DATE_FORMATE_SHORT));
                    }

                    insuranceCard.setInsuranceFrontCardPath(imgFcPath);
                    insuranceCard.setInsuranceBackCardPath(imgBcPath);
                    insuranceCard.setIsPrimary(isPramiry);
                    insuranceCard.setIsArchived(0);
                    patientProfileDAO.saveOrUpdate(insuranceCard);
                    isAdded = true;
                    saveActivitesHistory(ActivitiesEnum.EDIT_INSURANCE_CARD.getValue(), patientProfile, "", cardType, "","");
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("Your insurance card image has been successfully updated");
                    return isAdded;
                } catch (Exception e) {
                    e.getStackTrace();
                    jsonResponse.setErrorMessage("There is problem to update record." + e.getMessage());
                    logger.error("Exception -> getPatientProfileByToken", e);
                    return false;
                }
            }
        }

        if (patientProfile != null) {
            List<BusinessObject> lstObj = new ArrayList();
            List<PatientInsuranceDetails> patientInsuranceDetailsList = new ArrayList<PatientInsuranceDetails>();

            if (isPramiry == 1) {
                lstObj.add(new BusinessObject("patientProfile.id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR));
                lstObj.add(new BusinessObject("isPrimary", 1, Constants.HIBERNATE_EQ_OPERATOR));

                patientInsuranceDetailsList = patientProfileDAO.findByProperty(new PatientInsuranceDetails(), lstObj, "", Constants.HIBERNATE_ASC_ORDER);
                if (!CommonUtil.isNullOrEmpty(patientInsuranceDetailsList)) {
                    patientInsuranceDetailsList.get(0).setIsPrimary(0);
                    patientProfileDAO.update(patientInsuranceDetailsList.get(0));
                }
                cardType = "Primary";
            }

            insuranceCard.setPatientProfile(patientProfile);
            insuranceCard.setMemberID(AppUtil.getSafeStr(memberId, ""));
            insuranceCard.setInsuranceProvider(AppUtil.getSafeStr(insuranceProvider, ""));
            insuranceCard.setGroupNumber(AppUtil.getSafeStr(groupNumber, ""));
            insuranceCard.setPlanId(AppUtil.getSafeStr(planId, ""));
            insuranceCard.setProviderPhone(AppUtil.getSafeStr(providerPhone, ""));
            insuranceCard.setProviderAddress(AppUtil.getSafeStr(providerAddress, ""));

            if (expiryDate != null) {
                insuranceCard.setExpiryDate(DateUtil.stringToDate(expiryDate, Constants.DATE_FORMATE_SHORT));
            }
            if (createdOn != null) {
                insuranceCard.setCreatedOn(DateUtil.stringToDate(createdOn, Constants.DATE_FORMATE_SHORT));
            }
            if (updatedOn != null) {
                insuranceCard.setCreatedOn(DateUtil.stringToDate(updatedOn, Constants.DATE_FORMATE_SHORT));
            }
            if (cardHolderRelation != null) {
                insuranceCard.setCardHolderRelationship(cardHolderRelation);
            }
            if (effectiveDate != "") {
                insuranceCard.setEffectiveDate(DateUtil.stringToDate(effectiveDate, Constants.DATE_FORMATE_SHORT));
            }
            insuranceCard.setInsuranceFrontCardPath(imgFcPath);
            insuranceCard.setInsuranceBackCardPath(imgBcPath);
            insuranceCard.setIsPrimary(isPramiry);
            insuranceCard.setIsArchived(0);

            patientProfileDAO.saveOrUpdate(insuranceCard);
            isAdded = true;
            saveActivitesHistory(ActivitiesEnum.ADD_INSURANCE_CARD.getValue(), patientProfile, "", cardType, "","");
        }
        jsonResponse.setErrorCode(1);
        jsonResponse.setErrorMessage("Insurance card has been added successfully.");
        return isAdded;
    }

    public JsonResponse archiveInsuranceCard(PatientProfile patientProfile, Long cardId, JsonResponse jsonResponse) throws Exception {
        PatientInsuranceDetails insurance = new PatientInsuranceDetails();
        try {
            if (patientProfile != null) {
                insurance = (PatientInsuranceDetails) patientProfileDAO.getPatientInsuranceDetailByPatientIdAndCardNo(cardId, patientProfile.getPatientProfileSeqNo());
//            insurance = (PatientInsuranceDetails) patientProfileDAO.findRecordById(new PatientInsuranceDetails(),cardId);
                if (insurance != null && insurance.getIsArchived().toString().equals("0")) {
                    int ppId = insurance.getPatientProfile().getPatientProfileSeqNo();
                    if (ppId == patientProfile.getPatientProfileSeqNo()) {
                        insurance.setIsArchived(1);
                        patientProfileDAO.update(insurance);
                        jsonResponse.setErrorCode(1);
                        String cardType;
                        if (insurance.getIsPrimary() == 1) {
                            cardType = "Primary";
                        } else {
                            cardType = "Secondary";
                        }
                        jsonResponse.setErrorMessage("Card successfully deleted");
                        saveActivitesHistory(ActivitiesEnum.DELETE_INSURANCE_CARD.getValue(), patientProfile, "", cardType, "","");
                    } else {
                        jsonResponse.setErrorCode(0);
                        jsonResponse.setErrorMessage("Card does belong to this patient.");
                    }
                } else {
                    logger.info("In valid Card!");
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Card does not exists.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

    public List<PatientInsuranceDetailsDTO> getInsuranceCard(PatientProfile patientProfile, Integer dependentId) throws Exception {
        List<PatientInsuranceDetailsDTO> patientInsuranceDetailsDTOList = new ArrayList<>();
        List<PatientInsuranceDetails> patientInsuranceDetailslist = new ArrayList<>();

        if (patientProfile != null) {
            patientInsuranceDetailslist = patientProfileDAO.populateInsCardList(patientProfile.getPatientProfileSeqNo());
            if (CommonUtil.isNullOrEmpty(patientInsuranceDetailslist)) {
                logger.info("No Insurance Card Avalible");
                throw new Exception("No Insurance Card Avalible.Please add new.");
            }
        }

        patientInsuranceDetailsDTOList = populatePatientInsuranceDetails(patientInsuranceDetailslist);
        return patientInsuranceDetailsDTOList;
    }

    public List<PatientInsuranceDetailsDTO> populatePatientInsuranceDetails(List<PatientInsuranceDetails> patientInsuranceDetailslist) throws Exception {
        List<PatientInsuranceDetailsDTO> patientInsuranceDetailsDTOList = new ArrayList<>();
        for (PatientInsuranceDetails patientInsuranceDetails : patientInsuranceDetailslist) {
            PatientInsuranceDetailsDTO patientInsuranceDetailsDTO = new PatientInsuranceDetailsDTO();
            patientInsuranceDetailsDTO.setId(patientInsuranceDetails.getId());
            patientInsuranceDetailsDTO.setGroupNumber(AppUtil.getSafeStr(patientInsuranceDetails.getGroupNumber(), ""));
            patientInsuranceDetailsDTO.setPatientProfileId(AppUtil.getSafeInt(patientInsuranceDetails.getPatientProfile().getPatientProfileSeqNo() + "", 0));
            patientInsuranceDetailsDTO.setInsuranceProvider(AppUtil.getSafeStr(patientInsuranceDetails.getInsuranceProvider(), ""));
            patientInsuranceDetailsDTO.setMemberID(AppUtil.getSafeStr(patientInsuranceDetails.getMemberID(), ""));
            patientInsuranceDetailsDTO.setPlanId(AppUtil.getSafeStr(patientInsuranceDetails.getPlanId(), ""));
            patientInsuranceDetailsDTO.setProviderAddress(AppUtil.getSafeStr(patientInsuranceDetails.getProviderAddress(), ""));
            patientInsuranceDetailsDTO.setProviderPhone(AppUtil.getSafeStr(patientInsuranceDetails.getProviderPhone(), ""));
            patientInsuranceDetailsDTO.setCardHolderRelationship(AppUtil.getSafeStr(patientInsuranceDetails.getCardHolderRelationship(), ""));
            patientInsuranceDetailsDTO.setInsuranceFrontCardPath(AppUtil.getSafeStr(patientInsuranceDetails.getInsuranceFrontCardPath(), ""));
            patientInsuranceDetailsDTO.setInsuranceBackCardPath(AppUtil.getSafeStr(patientInsuranceDetails.getInsuranceBackCardPath(), ""));
            patientInsuranceDetailsDTO.setIsPramiry(AppUtil.getSafeInt(patientInsuranceDetails.getIsPrimary() + "", 0));
            patientInsuranceDetailsDTO.setIsArchived(AppUtil.getSafeInt(patientInsuranceDetails.getIsArchived() + "", 0));
            if (patientInsuranceDetails.getEffectiveDate() == null) {
                patientInsuranceDetailsDTO.setEffectiveDate("");
            } else {
                patientInsuranceDetailsDTO.setEffectiveDate(DateUtil.dateToString(patientInsuranceDetails.getEffectiveDate(), Constants.DATE_FORMATE_SHORT));
            }
            patientInsuranceDetailsDTO.setPatientId(AppUtil.getSafeInt(patientInsuranceDetails.getPatientProfile().getPatientProfileSeqNo() + "", 0));
            patientInsuranceDetailsDTOList.add(patientInsuranceDetailsDTO);
        }
        return patientInsuranceDetailsDTOList;
    }

    public List<OrderDetailDTO> getYearEndStatment(PatientProfile patientProfile, Date startDate, Date endDate) throws Exception {
        List<Order> orderList = new ArrayList<>();
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            orderList = patientProfileDAO.getYearEndStatment(patientProfile.getPatientProfileSeqNo(), startDate, endDate);
        }
        return populateYearEndStatment(orderList);
    }

    public List<OrderDetailDTO> populateYearEndStatment(List<Order> orderList) throws Exception {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            ///////////////////////////////////////////
            String drug = AppUtil.getSafeStr(order.getDrugName(), "");
            String[] arr = AppUtil.getBrandAndGenericFromDrugName(drug);
            if (arr != null) {
                if (arr.length == 2) {
                    drug = arr[0] + "(" + arr[1] + ")";
//                    genericName = arr[1];
                } else if (AppUtil.getSafeStr(drug, "").indexOf(Constants.BRAND_NAME_ONLY) >= 0) {
                    drug = arr[0] + "(" + Constants.BRAND_NAME_ONLY + ")";
                }
//                else if (AppUtil.getSafeStr(drug, "").length() > 0) {
//                    genericName = drug;
//                    drug = "User Input";
//                }
            }
            ///////////////////////////////////////////
            orderDetailDTO.setDrugName(drug);
            orderDetailDTO.setRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "-"));
            orderDetailDTO.setId(order.getId());
//            orderDetailDTO.setOriginalPtCopayStr(AppUtil.roundOffNumberToCurrencyFormat(order.getOriginalPtCopay(), "en", "US"));
//            orderDetailDTO.setRxReimbCost(order.getRxReimbCost() != null && order.getRxReimbCost() > 0d ? order.getRxReimbCost() : 0d);
//            orderDetailDTO.setRedeemPoints(order.getProfitSharePoint() != null ? AppUtil.convertNumberToThousandSeparatedFormat(order.getProfitSharePoint()) : "0");
//            orderDetailDTO.setRedeemPointsCost(order.getActualProfitShare() != null ? order.getActualProfitShare() : 0d);
            if (order.getMfrCost() != null) {
//                orderDetailDTO.setMfrCost(order.getMfrCost());
            } else {
//                orderDetailDTO.setMfrCost(0.00);
            }
            if (order.getPriceIncludingMargins() != null) {
//                orderDetailDTO.setPriceIncludingMargins(order.getPriceIncludingMargins());
            } else {
//                orderDetailDTO.setPriceIncludingMargins(0.00);
            }
            if (order.getCreatedOn() != null) {
                orderDetailDTO.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), Constants.USA_DATE_TIME_FORMATE));
            } else {
                orderDetailDTO.setOrderDate("01/01/1900");
            }
            if (order.getDrugPrice() != null) {
                orderDetailDTO.setDrugPrice(order.getDrugPrice());
            } else {
                orderDetailDTO.setDrugPrice(0.00);
            }
            if (order.getHandLingFee() != null) {
//                orderDetailDTO.setHandLingFee(order.getHandLingFee());
            } else {
//                orderDetailDTO.setHandLingFee(0.00);
            }
            if (order.getRedeemPointsCost() != null) {
//                orderDetailDTO.setRedeemPointsCost(order.getRedeemPointsCost());
            } else {
//                orderDetailDTO.setRedeemPointsCost(0.00);
            }
            if (order.getProfitMargin() != null) {
//                orderDetailDTO.setProfitMargin(order.getProfitMargin());
            } else {
//                orderDetailDTO.setProfitMargin(0.00);
            }
            if (order.getFinalPayment() != null) {
                orderDetailDTO.setFinalPayment(order.getFinalPayment());
            } else {
                orderDetailDTO.setFinalPayment(0.00);
            }
//            orderDetailDTO.setPaymentIncludingShipping(
//                    AppUtil.roundOffNumberToCurrencyFormat(orderDetailDTO.getFinalPayment()
//                            + orderDetailDTO.getHandLingFee(), "en", "us"));
//            orderDetailDTOList.add(orderDetailDTO);
        }
        return orderDetailDTOList;
    }

    public boolean updatePrimaryInsuranceCard(PatientProfile patientProfile, Integer cardId) throws Exception {
        boolean isUpdated = false;
        List<BusinessObject> lstObj = new ArrayList();
        List<PatientInsuranceDetails> patientInsuranceDetailsList = new ArrayList();

        if (patientProfile != null) {

            PatientInsuranceDetails makePrimary = (PatientInsuranceDetails) patientProfileDAO.findRecordById(new PatientInsuranceDetails(), cardId);
            if (makePrimary != null) {
                lstObj.add(new BusinessObject("PatientProfile.Id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR));
                lstObj.add(new BusinessObject("PatientInsuranceDetails.IS_PRIMARY", 1, Constants.HIBERNATE_EQ_OPERATOR));
                patientInsuranceDetailsList = patientProfileDAO.findByProperty(new PatientInsuranceDetails(), lstObj, "patientProfile.id", Constants.HIBERNATE_ASC_ORDER);
                if (CommonUtil.isNullOrEmpty(patientInsuranceDetailsList)) {
                    patientInsuranceDetailsList.get(0).setIsPrimary(0);
                    patientProfileDAO.update(patientInsuranceDetailsList.get(0));
                }
                makePrimary.setIsPrimary(1);
                patientProfileDAO.saveOrUpdate(makePrimary);
                isUpdated = true;
            } else {
                logger.info("In valid Card!");
                isUpdated = false;
                throw new Exception("Card is not existed!");
            }
        }
        return isUpdated;
    }

    public List<QuestionAnswerDTO> getQuestionAnswerList(String orderId) {
        List<QuestionAnswerDTO> questionDTOLst = new ArrayList();
        try {
            List<QuestionAnswer> questionsList = this.patientProfileDAO.findByNestedProperty(new QuestionAnswer(), "order.id", orderId, Constants.HIBERNATE_EQ_OPERATOR, "id", Constants.HIBERNATE_DESC_ORDER);

            if (questionsList != null) {
                for (QuestionAnswer answer : questionsList) {
                    QuestionAnswerDTO dto = new QuestionAnswerDTO();
//                    dto.setId(answer.getId());
                       dto.setQuestionId(answer.getId());
                    dto.setQuestion(answer.getQuestion());
                    dto.setQuestionTimeStr(answer.getQuestionTime() != null
                            ? DateUtil.dateToString(answer.getQuestionTime(), "MM/dd/yyyy hh:mm a") : "");
                    questionDTOLst.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionDTOLst;
    }

    public DrugDTO getDrugInfo(Integer basicDrugId) {

        DrugBasic drugBasic = (DrugBasic) patientProfileDAO.findRecordById(new DrugBasic(), basicDrugId);
        DrugDTO drugDTO = new DrugDTO();
        if (drugBasic != null) {

            drugDTO.setDrugId(drugBasic.getDrugBasicId());
            drugDTO.setBrandName(drugBasic.getBrandName());
            drugDTO.setGenaricName(drugBasic.getDrugGeneric().getGenericName());
            drugDTO.setDrugNDC(Long.MAX_VALUE);
            drugDTO.setStrength("");
        }

        return drugDTO;

    }

    /*//////////////////////////////////////////////////////////////////////////////////////////////////////
     // Refill Reminder End                                                                                //
     //////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public boolean updateDeliveryAddressWs(PatientProfile patientProfile, Integer id, String address, String appt, String city, String stateId, String zip, String description, String addressType, String defaultAddress, Integer dprefId) {
        boolean isUpdate = false;
        try {
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                logger.info("patientProfile id is: " + patientProfile.getPatientProfileSeqNo());
                updatePreviousDefaultAddress(patientProfile.getPatientProfileSeqNo(), defaultAddress);
                logger.info("Update Ptient DeliveryAddress: " + id);
                PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getPatientDeliveryAddressById(patientProfile.getPatientProfileSeqNo(), id);
                setDeliveryAddress(patientDeliveryAddress, patientProfile.getPatientProfileSeqNo(), address, appt, description, city, Integer.parseInt(stateId), zip, addressType, defaultAddress, dprefId);
                patientDeliveryAddress.setUpdatedOn(new Date());
                patientProfileDAO.update(patientDeliveryAddress);
                isUpdate = true;
            }
        } catch (Exception e) {
            logger.error("Exception -> updateDeliveryAddressWs", e);
        }
        return isUpdate;
    }

    public PatientProfile getPatientProfileByPhoneNumber(String mobileNumber) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileByPhoneNumber(EncryptionHandlerUtil.getEncryptedString(mobileNumber));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getPatientProfileByPhoneNumber", e);
        }
        return patientProfile;
    }

    public List<PatientRewardLevel> getPatientRewardLevelList() {
        List<PatientRewardLevel> list = new ArrayList<>();
        try {
            list = patientProfileDAO.getAllRecords(new PatientRewardLevel());
        } catch (Exception e) {
            logger.error("Exception -> getPatientRewardLevelList", e);
        }
        return list;
    }

    public Set<DrugBrandDTO> getDrugBrandsList(String name) {

        SortedSet<DrugBrandDTO> list = new TreeSet<>(
                Comparator.comparing(DrugBrandDTO::getDrugBrandName));
        try {

            List<DrugBasic> drugGenericList = patientProfileDAO.retrieveDrugWithGeneric(name);
            List<DrugBasic> drugBrandList = patientProfileDAO.retrieveDrugWithoutGeneric(name);

            if ((drugGenericList == null && drugBrandList == null) || (drugGenericList.size() == 0 && drugBrandList.size() == 0)) {
                DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
                drugBrandDTO.setId(0);
                drugBrandDTO.setDrugBrandName(Constants.EMPTY_MESSAGE);
                list.add(drugBrandDTO);
                return list;
            }

            if (drugGenericList.size() > 0) {
                for (DrugBasic drugBrand : drugGenericList) {
                    DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
                    drugBrandDTO.setId(drugBrand.getDrugBasicId());
//                    drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "") + "("
//                            + AppUtil.getSafeStr(drugBrand.getBrandName(), "") + ")");
                    if (AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "").equalsIgnoreCase(Constants.BRAND_NAME_ONLY_WITH_STARIC)) {
                        // if (AppUtil.getSafeStr(d.getArchived(), "").equalsIgnoreCase("N")) 
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + " "
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), ""));
                    } else {
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + "("
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "").toLowerCase() + ")");

                    }

                    list.add(drugBrandDTO);
                }
            }

            if (drugBrandList.size() > 0) {
                logger.info("Drug Brand list size: " + drugBrandList.size());
                for (DrugBasic drugBrand : drugBrandList) {
                    DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
                    drugBrandDTO.setId(drugBrand.getDrugBasicId());
                    //drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), ""));
                    if (AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "").equalsIgnoreCase(Constants.BRAND_NAME_ONLY_WITH_STARIC)) {
                        // if (AppUtil.getSafeStr(d.getArchived(), "").equalsIgnoreCase("N")) 
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + " "
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), ""));
                    } else {
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + "("
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "").toLowerCase() + ")");

                    }

                    list.add(drugBrandDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugBrandsList", e);
        }
        return list;
    }

//    public Set<DrugBrandDTO> getDrugBrandsList(String name) {
//        Set<DrugBrandDTO> list = new HashSet<>();
//        try {
//            List<DrugBrand> drugBrandList = patientProfileDAO.getDrugBrandsList(name);
//            if (drugBrandList.size() > 0) {
//                logger.info("Drug Brand list size: " + drugBrandList.size());
//                for (DrugBrand drugBrand : drugBrandList) {
//                    DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
//                    drugBrandDTO.setId(drugBrand.getId());
//                    if (drugBrand.getDrugGenericTypes() != null && drugBrand.getDrugGenericTypes().getId() != null) {
//                        drugBrandDTO.setDrugBrandName(drugBrand.getDrugBrandName() + "(" + drugBrand.getDrugGenericTypes().getDrugGenericName() + ")");
//                    } else {
//                        logger.info("Drug Generic Name");
//                        drugBrandDTO.setDrugBrandName(drugBrand.getDrugBrandName());
//                    }
//                    list.add(drugBrandDTO);
//                }
//            } else {
//                DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
//                drugBrandDTO.setId(0);
//                drugBrandDTO.setDrugBrandName(Constants.EMPTY_MESSAGE);
//                list.add(drugBrandDTO);
//            }
//        } catch (Exception e) {
//            logger.error("Exception -> getDrugBrandsList", e);
//        }
//        return list;
//    }
    /////////////////////////////////////////////////////////////////
//    public Drug getDrugList(Integer drugBrandId) {
//        Drug drug = new Drug();
//        try {
//            List<StrengthJsonList> strengthList = new ArrayList<>();
//            Set<TabletDTO> tabletDTOs = new LinkedHashSet<>();
//            List<CapsuleDTO> capsuleList = new ArrayList<>();
//            Set<String> drugTypeDTOs = new LinkedHashSet<>();
//            List<BusinessObject> lstBusiness = new ArrayList();
//            
//            DrugBasic drugBasic = (DrugBasic) patientProfileDAO.findRecordById(new DrugBasic(), drugBrandId);
//            if (drugBasic == null) {
//                return null;
//            }
//            Set<DrugDetail> list = drugBasic.getDrugDetailSet();
////            BusinessObject bizz=new BusinessObject("drugBasic", drugBrandId, Constants.HIBERNATE_EQ_OPERATOR);
////            lstBusiness.add(bizz);
////            bizz=new BusinessObject("archived","N",Constants.HIBERNATE_EQ_OPERATOR);
////            lstBusiness.add(bizz);
////            List<Drug> list = patientProfileDAO.findByProperty(new DrugDetail(), lstBusiness, "", 0);
//            StrengthJsonList strengthJsonList = new StrengthJsonList();
//            if (list != null && list.size() > 0) {
//                for (DrugDetail d : list) {
//                    //String type=AppUtil.getSafeStr(d.getDrugForm().getFormDescr(), "");
//                    if (AppUtil.getSafeStr(d.getArchived(), "").equalsIgnoreCase("N")) {
//                        TabletDTO tabletDTO = new TabletDTO();
//                        CapsuleDTO capsuleDTO = new CapsuleDTO();
//                        //drug.setDrugNdc(d.getDrugNDC());
//                        drug.setDrugGpi("" + d.getDrugGPI());
//                        //drug.setRouteDescription(d.getRouteDescription());
//                        drug.setDrugGcn("" + d.getDrugGCN());
//                        //drug.setDrugNdc(d.getDrugDetailId());
//                        //drug.setdType(d.getFormPrefix());
//                        //drug.setDrugType(d.getDrugForm().getFormDescr());
//                        //drug.setDefQty(d.getDefQty());
//                        makeDrugTypeList(d, tabletDTO, tabletDTOs, strengthJsonList, capsuleDTO, capsuleList);
//                        drug.setdType(tabletDTO.getFormPrefix());
//                        drugTypeDTOs.add(AppUtil.getSafeStr(tabletDTO.getFormPrefix(), "").length() > 0
//                                ? AppUtil.getSafeStr(tabletDTO.getFormPrefix(), "")
//                                : AppUtil.getSafeStr(capsuleDTO.getFormPrefix(), ""));//.replaceAll("\\s", ""));
//                    }
//                }
//                strengthList.add(strengthJsonList);
//                drug.setStrengthList(strengthList);
//                drug.setdType(drugTypeDTOs);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("Exception -> getDrugList", e);
//        }
//        return drug;
//    }
    ////////////////////////////////////////////////////////////////
    public Drug getDrugList(Integer drugBrandId) {
        Drug drug = new Drug();
        try {
            List<StrengthJsonList> strengthList = new ArrayList<>();
            Set<DrugDTO> tabletDTOs = new LinkedHashSet<>();
            Set<String> drugTypeDTOs = new LinkedHashSet<>();
            List<BusinessObject> lstBusiness = new ArrayList();

            DrugBasic drugBasic = (DrugBasic) patientProfileDAO.findRecordById(new DrugBasic(), drugBrandId);
            if (drugBasic == null) {
                return null;
            }
            Set<DrugDetail> list = drugBasic.getDrugDetailSet();

            StrengthJsonList strengthJsonList = new StrengthJsonList();
            Map map = new HashMap();
            if (list != null && list.size() > 0) {
                for (DrugDetail d : list) {
                    //String type=AppUtil.getSafeStr(d.getDrugForm().getFormDescr(), "");
                    if (AppUtil.getSafeStr(d.getArchived(), "").equalsIgnoreCase("N")) {
                        DrugDTO tabletDTO = new DrugDTO();
                        drug.setDrugGpi("" + d.getDrugGPI());
                        //drug.setRouteDescription(d.getRouteDescription());
                        drug.setDrugGcn("" + d.getDrugGCN());
                        //drug.setDrugNdc(d.getDrugDetailId());
                        //drug.setdType(d.getFormPrefix());
                        //drug.setDrugType(d.getDrugForm().getFormDescr());
                        //drug.setDefQty(d.getDefQty());
                        tabletDTO = makeDrugTypeList(d, tabletDTO, tabletDTOs, strengthJsonList);
                        drug.setdType(tabletDTO.getFormPrefix());
                        drugTypeDTOs.add(AppUtil.getSafeStr(tabletDTO.getFormPrefix(), ""));//.replaceAll("\\s", ""));
                        if (map.get(tabletDTO.getFormPrefix()) != null) {
                            List lst = (List) map.get(tabletDTO.getFormPrefix());
                            lst.add(tabletDTO);
                        } else {
                            List lst = new ArrayList();
                            lst.add(tabletDTO);
                            map.put(tabletDTO.getFormPrefix(), lst);
                        }
                        //map.put(tabletDTO.getFormPrefix(), tabletDTO);

                    }
                }
//                strengthList.add(strengthJsonList);
//                drug.setStrengthList(strengthList);
                drug.setDrugMap(map);
                drug.setdType(drugTypeDTOs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugList", e);
        }
        return drug;
    }

    ////////////////////////////////////////////////////////////////
//    public Drug getDrugList(Integer drugBrandId) {
//        Drug drug = new Drug();
//        try {
//            List<StrengthJsonList> strengthList = new ArrayList<>();
//            Set<TabletDTO> tabletDTOs = new LinkedHashSet<>();
//            List<CapsuleDTO> capsuleList = new ArrayList<>();
//            Set<String> drugTypeDTOs = new LinkedHashSet<>();
//            List<Drug> list = patientProfileDAO.getAllDrug(drugBrandId);
//            StrengthJsonList strengthJsonList = new StrengthJsonList();
//            if (list.size() > 0) {
//                for (Drug d : list) {
//
//                    TabletDTO tabletDTO = new TabletDTO();
//                    CapsuleDTO capsuleDTO = new CapsuleDTO();
//                    drug.setDrugId(d.getDrugId());
//                    drug.setDrugGpi(d.getDrugGpi());
//                    drug.setRouteDescription(d.getRouteDescription());
//                    drug.setDrugGcn(d.getDrugGcn());
//                    drug.setdType(d.getDrugType());
//                    makeDrugTypeList(d, tabletDTO, tabletDTOs, strengthJsonList, capsuleDTO, capsuleList);
//                    drugTypeDTOs.add(d.getDrugType().replaceAll("\\s", ""));
//                }
//                strengthList.add(strengthJsonList);
//                drug.setStrengthList(strengthList);
//                drug.setdType(drugTypeDTOs);
//            }
//        } catch (Exception e) {
//            logger.error("Exception -> getDrugList", e);
//        }
//        return drug;
//    }
//    private void makeDrugTypeList(Drug d, TabletDTO tabletDTO, Set<TabletDTO> tabletDTOs, StrengthJsonList strengthJsonList, CapsuleDTO capsuleDTO, List<CapsuleDTO> capsuleList) {
//        if (d.getDrugType().replaceAll("\\s", "").equalsIgnoreCase(Constants.TABLET)) {
//            tabletDTO.setDrugId(d.getDrugId());
//            tabletDTO.setStrength(d.getStrength() + "" + d.getDrugUnits().getName());
//            tabletDTOs.add(tabletDTO);
//            strengthJsonList.setTablet(tabletDTOs);
//        } else {
//            capsuleDTO.setDrugId(d.getDrugId());
//            capsuleDTO.setStrength(d.getStrength() + "" + d.getDrugUnits().getName());
//            capsuleList.add(capsuleDTO);
//            Set<CapsuleDTO> os = new LinkedHashSet<>();
//            for (CapsuleDTO cdto : capsuleList) {
//                os.add(cdto);
//            }
//            strengthJsonList.setCapsule(os);
//        }
//    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private DrugDTO makeDrugTypeList(DrugDetail d, DrugDTO tabletDTO, Set<DrugDTO> tabletDTOs,
            StrengthJsonList strengthJsonList) {
        String type = AppUtil.getSafeStr(d.getDrugForm().getFormDescr(), "");
        tabletDTO.setType(type);
        tabletDTO.setFormPrefix(type);
        tabletDTO.setDrugNDC(d.getDrugDetailId());//d.getDrugNDC());
        tabletDTO.setStrength(d.getStrength());
        tabletDTO.setDefQty(d.getDefQty());
        String qtyStr = AppUtil.getSafeStr(d.getPackageSizeValues(), "");//.split(",");
        String[] qtyStrArr = qtyStr.split(",");
        int[] qtyInt = new int[qtyStrArr != null ? qtyStrArr.length : 0];
        try {

            for (int i = 0; qtyStrArr != null && i < qtyStrArr.length; i++) {
                qtyStrArr[i] = AppUtil.getSafeStr(qtyStrArr[i], "").length() <= 4 ? AppUtil.getSafeStr(qtyStrArr[i], "")
                        : AppUtil.getSafeStr(qtyStrArr[i], "").substring(0, 3);
                qtyInt[i] = AppUtil.getSafeInt(qtyStrArr[i], 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
            qtyStr = "30";
            qtyInt = Arrays.stream(qtyStr.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        }
//        int[] qtyInt = Arrays.stream(qtyStr.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = new ArrayList<Integer>();

        for (int index = 0; index < qtyInt.length; index++) {
            list.add(qtyInt[index]);
        }
        Collections.sort(list);//, Collections.reverseOrder());
//            Collections.sort(list, Collections.reverseOrder());
        String[] qtyValues = new String[list.size()];
        //Object[] aryArr = list.toArray();
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            qtyValues[i] = "" + n;//.toString();
        }
        tabletDTO.setQtyValues(qtyValues);
        tabletDTOs.add(tabletDTO);
        strengthJsonList.setDrug(tabletDTOs);
        return tabletDTO;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    private void makeDrugTypeList(DrugDetail d, TabletDTO tabletDTO, Set<TabletDTO> tabletDTOs,
            StrengthJsonList strengthJsonList, CapsuleDTO capsuleDTO, List<CapsuleDTO> capsuleList) {
        String type = AppUtil.getSafeStr(d.getDrugForm().getFormDescr(), "");
        if (d.getDrugForm().getFormDescr().toUpperCase().indexOf(Constants.TABLET) >= 0) //.replaceAll("\\s", "").equalsIgnoreCase(Constants.TABLET)) 
        {
            tabletDTO.setType(type);
            tabletDTO.setFormPrefix(Constants.TABLET);
            tabletDTO.setDrugNDC(d.getDrugDetailId());//d.getDrugNDC());
            tabletDTO.setStrength(d.getStrength());
            tabletDTO.setDefQty(d.getDefQty());
            String qtyStr = d.getPackageSizeValues();//.split(",");
            int[] qtyInt = Arrays.stream(qtyStr.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            List<Integer> list = new ArrayList<Integer>();

            for (int index = 0; index < qtyInt.length; index++) {
                list.add(qtyInt[index]);
            }
            Collections.sort(list);//, Collections.reverseOrder());
//            Collections.sort(list, Collections.reverseOrder());
            String[] qtyValues = new String[list.size()];
            //Object[] aryArr = list.toArray();
            for (int i = 0; i < list.size(); i++) {
                int n = list.get(i);
                qtyValues[i] = "" + n;//.toString();
            }
//            String[] arr = d.getPackageSizeValues().split(",");
//            if (arr.length > 0) {
//                String[] qtyValues = new String[arr.length];
//                
//                for (int i = 0; i < qtyValues.length; i++) {
//                    qtyValues[i] = "120";
//                }
//                qtyValues[0] = "" + d.getDefQty();
//                for (int i = 1; i < arr.length; i++) {
//                    if (!arr[i].equals(qtyValues[0])) {
//                        qtyValues[i] = arr[i];
//                    }
//                }
//                Arrays.sort(qtyValues);
//                List<String> list = Arrays.asList(qtyValues);
//                Collections.reverse(list);
//                qtyValues = (String []) list.toArray();
            tabletDTO.setQtyValues(qtyValues);
//                
//            } 
//            else {
//                String[] qtyValues = new String[1];
//                for (int i = 0; i < qtyValues.length; i++) {
//                    qtyValues[i] = "120";
//                }
//                Arrays.sort(qtyValues);
//                List<String> list = Arrays.asList(qtyValues);
//                Collections.reverse(list);
//                qtyValues = (String []) list.toArray();
//                qtyValues[0] = "" + d.getDefQty();
//                tabletDTO.setQtyValues(qtyValues);
//            }
            //tabletDTO.setQtyValues(d.getPackageSizeValues().split(","));
            tabletDTOs.add(tabletDTO);
            strengthJsonList.setTablet(tabletDTOs);
        } else {
            capsuleDTO.setType(type);
            capsuleDTO.setFormPrefix(Constants.CAPSULE);
            capsuleDTO.setDrugNDC(d.getDrugDetailId());//.getDrugNDC());
            capsuleDTO.setStrength(d.getStrength());
            capsuleDTO.setDefQty(d.getDefQty());
            String qtyStr = d.getPackageSizeValues();//.split(",");
            int[] qtyInt = Arrays.stream(qtyStr.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            List<Integer> list = new ArrayList<Integer>();

            for (int index = 0; index < qtyInt.length; index++) {
                list.add(qtyInt[index]);
            }
            Collections.sort(list, Collections.reverseOrder());
//            Collections.sort(list, Collections.reverseOrder());
            String[] qtyValues = new String[list.size()];
            //Object[] aryArr = list.toArray();
            for (int i = 0; i < list.size(); i++) {
                int n = list.get(i);
                qtyValues[i] = "" + n;//.toString();
            }

//            String[] arr = d.getPackageSizeValues().split(",");
//            if (arr.length > 0) {
//                String[] qtyValues = new String[arr.length];
//                for (int i = 0; i < qtyValues.length; i++) {
//                    qtyValues[i] = "120";
//                }
//                qtyValues[0] = "" + d.getDefQty();
//                for (int i = 1; i < arr.length; i++) {
//                    if (!arr[i].equals(qtyValues[0])) {
//                        qtyValues[i] = arr[i];
//                    }
//                }
//                capsuleDTO.setQtyValues(qtyValues);
//                
//            } else {
//                String[] qtyValues = new String[1];
//                for (int i = 0; i < qtyValues.length; i++) {
//                    qtyValues[i] = "120";
//                }
//                qtyValues[0] = "" + d.getDefQty();
            capsuleDTO.setQtyValues(qtyValues);
//            }
            //capsuleDTO.setQtyValues(d.getPackageSizeValues().split(","));
            capsuleList.add(capsuleDTO);
            Set<CapsuleDTO> os = new LinkedHashSet<>();
            for (CapsuleDTO cdto : capsuleList) {
                os.add(cdto);
            }
            strengthJsonList.setCapsule(os);
        }
    }

    ///////////////////////////////////////////////////////
    public DrugDetail getDrugDetailInfo(Long drugId, Integer qty, PatientProfile patientProfile) {
        DrugDetail drug = new DrugDetail();
        try {
            DrugDetail newDrug = (DrugDetail) patientProfileDAO.findRecordById(new DrugDetail(), drugId);
            if (newDrug != null && newDrug.getDrugNDC() != null) {
                float drugProfitPercent = newDrug.getMarginPercent();
                float additionalFee = newDrug.getAdditionalFee();
                float basePrice = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(newDrug.getBasePrice()), 0f);
                newDrug.setBasePrice(basePrice);
                float drugCost = newDrug.getBasePrice() * qty;
                drug.setDrugCost(drugCost);
                float drugProfit = drugCost * (drugProfitPercent / 100);
                float totalFee = drugCost + drugProfit + additionalFee;
                drug.setTotalPrice(totalFee);
                //drug.setDrugId(drugI);
                //setDrugAdditionalMarginPricesField(newDrug, qty, drug);
                // setDeliveryPreferences(patientProfile, drug);
                getDrugsLookUpCalculation(patientProfile, drug);
                drug.setDrugNDC(newDrug.getDrugDetailId());//(newDrug.getDrugNDC());
                drug.setBrandName(AppUtil.getSafeStr(newDrug.getDrugBasic().getBrandName(), ""));
                drug.setGenericName(AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), ""));
                drug.setFormDesc(AppUtil.getSafeStr(newDrug.getDrugForm().getFormDescr(), "") + "(" + qty + ")");
                drug.setAdditionalMargin(additionalFee);
                drug.setProfitValue(drugProfit);
                drug.setImgUrl(AppUtil.getSafeStr(newDrug.getImgUrl(), ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugList", e);
        }
        return drug;
    }

    /////////////////////////////////////////////////////
    public DrugDetail populateOrderInfoInDrug(DrugDetail drug, String orderId) {
        if (AppUtil.getSafeStr(orderId, "").length() > 0) {
            Order order = (Order) this.patientProfileDAO.findRecordById(new Order(), orderId);
            if (order != null) {
                drug.setRxNo(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), ""));
                drug.setPymentType(AppUtil.getSafeStr(order.getFinalPaymentMode(), "")
                        .equalsIgnoreCase("SELF PAY") ? "SELF PAY" : "Insurance");
                drug.setRefillsRemaining(order.getRefillsRemaining() != null ? order.getRefillsRemaining() : 0);
                drug.setDaysSupply(order.getDaysSupply() != null ? order.getDaysSupply() : 0);
            }
        }
        return drug;

    }

    /////////////////////////////////////////////////////
    public DrugDetail getGenericBasedDrugDetailInfoHandler(Long drugId, Integer qty, PatientProfile patientProfile,
            String orderId) {
        DrugDetail detail = this.getGenericBasedDrugDetailInfo(drugId, qty, patientProfile);
        return this.populateOrderInfoInDrug(detail, orderId);
    }

    /////////////////////////////////////////////////////
    public DrugDetail getGenericBasedDrugDetailInfo(Long drugId, Integer qty, PatientProfile patientProfile) {
        DrugDetail drug = new DrugDetail();
        try {
            DrugDetail newDrug = (DrugDetail) patientProfileDAO.findRecordById(new DrugDetail(), drugId);
            if (newDrug != null && newDrug.getDrugNDC() != null) {
                float drugProfitPercent = newDrug.getMarginPercent();
                float additionalFee = newDrug.getAdditionalFee();
                float basePrice = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(newDrug.getBasePrice()), 0f);
                logger.info("basePrice# " + basePrice + " Qty# " + qty);
                float drugCost = basePrice * qty; //newDrug.getBasePrice() * qty;
                drug.setRxAcqCost(drugCost);
                drug.setBasePrice(basePrice);
                float drugProfit = drugCost * (drugProfitPercent / 100);
                float mktSurcharge = newDrug.getMktSurcharge() != null ? newDrug.getMktSurcharge() : 0.0f;
                float totalFee = drugCost + drugProfit; //drugCost + drugProfit + additionalFee + newDrug.getMktSurcharge();
                drug.setTotalPrice(totalFee);
                float profitValue = totalFee - drugCost;
                float profitShare = profitValue * Constants.PROFIT_SHARE_PERCENT / 100;
                drug.setProfitValue(profitValue);
                drug.setProfitShare(profitShare);
                drug.setDrugCost(drugCost);
                //drug.setDrugId(drugI);
                //setDrugAdditionalMarginPricesField(newDrug, qty, drug);
                // setDeliveryPreferences(patientProfile, drug);
                getDrugsLookUpCalculation(patientProfile, drug);
                drug.setDrugNDC(newDrug.getDrugDetailId());//(newDrug.getDrugNDC());
                drug.setBrandName(AppUtil.getSafeStr(newDrug.getDrugBasic().getBrandName(), ""));
                //drug.setGenericName(AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), ""));
                //////////////////////////////////////////////
                if (AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), "").equalsIgnoreCase("* BRAND NAME ONLY *")) {
                    String tmp = "";
                    drug.setGenericName(tmp);
                } else {
                    drug.setGenericName(AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), ""));
                }
                //////////////////////////////////////////////
                drug.setFormDesc(AppUtil.getSafeStr(newDrug.getDrugForm().getFormDescr(), "") + "(" + qty + ")");
                drug.setAdditionalMargin(additionalFee);
                //drug.setProfitValue(drugProfit);
                drug.setImgUrl(AppUtil.getSafeStr(newDrug.getImgUrl(), ""));
                if (drug.getFinalPrice() != null && drug.getFinalPrice() < 0) {
                    drug.setFinalPrice(0.0f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugList", e);
        }
        return drug;
    }

    //////////////////////////////////////////////////////
    public DrugDetail getGenericBasedDrugDetailInfo(Long drugId, Integer qty, PatientProfile patientProfile, String orderId) {
        DrugDetail drug = new DrugDetail();
        try {
            DrugDetail newDrug = (DrugDetail) patientProfileDAO.findRecordById(new DrugDetail(), drugId);
            if (newDrug != null && newDrug.getDrugNDC() != null) {
                float drugProfitPercent = newDrug.getMarginPercent();
                float additionalFee = newDrug.getAdditionalFee();
                float basePrice = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(newDrug.getBasePrice()), 0f);
                float drugCost = basePrice * qty; //newDrug.getBasePrice() * qty;
                drug.setDrugCost(drugCost);
                drug.setBasePrice(basePrice);
                float drugProfit = drugCost * (drugProfitPercent / 100);
                float mktSurcharge = newDrug.getMktSurcharge() != null ? newDrug.getMktSurcharge() : 0.0f;
                float totalFee = drugCost + drugProfit + (additionalFee * qty) + newDrug.getMktSurcharge();
                drug.setTotalPrice(totalFee);
                float profitValue = totalFee - drugCost;
                profitValue = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(profitValue), 0f);
                float profitShare = profitValue * Constants.PROFIT_SHARE_PERCENT / 100;
                profitShare = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(profitShare), 0f);
                drug.setProfitValue(profitValue);
                drug.setProfitShare(profitShare);
                //drug.setDrugId(drugI);
                //setDrugAdditionalMarginPricesField(newDrug, qty, drug);
                // setDeliveryPreferences(patientProfile, drug);
                int profitSharePoint = calculatePointsFromProfitShare(drug);
                drug.setPointsFromShare(profitSharePoint);
                float costFromPoints = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(calculateCostFromPoints(profitSharePoint)), 0f);
                drug.setCostFromPoints(costFromPoints);

                //////////////////////////////////SAVING Profit Share Points in Reward History/////////////////////////////////////////////
//                if (profitSharePoint > 0) {
//                    Order order = (Order) this.patientProfileDAO.findRecordById(new Order(), orderId);
//                    RewardHistory history = new RewardHistory();
//                    history.setDescription(Constants.PROFIT_SHARE_DESCRIPTION);
//                    history.setOrder(order);
//                    history.setPatientId(patientProfile.getId());
//                    history.setCreatedDate(new Date());
//                    this.patientProfileDAO.save(history);
//                }
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                getDrugsLookUpCalculation(patientProfile, drug);
                drug.setDrugNDC(newDrug.getDrugDetailId());//(newDrug.getDrugNDC());
                drug.setBrandName(AppUtil.getSafeStr(newDrug.getDrugBasic().getBrandName(), ""));
                //drug.setGenericName(AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), ""));
                //////////////////////////////////////////////
                if (AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), "").equalsIgnoreCase("* BRAND NAME ONLY *")) {
                    String tmp = "";
                    drug.setGenericName(tmp);
                } else {
                    drug.setGenericName(AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), ""));
                }
                //////////////////////////////////////////////
                drug.setFormDesc(AppUtil.getSafeStr(newDrug.getDrugForm().getFormDescr(), "") + "(" + qty + ")");
                drug.setAdditionalMargin(additionalFee);
                //drug.setProfitValue(drugProfit);
                drug.setImgUrl(AppUtil.getSafeStr(newDrug.getImgUrl(), ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugList", e);
        }
        return drug;
    }

    /////////////////////////////////////////////////////
    public DrugDetail getGenericBasedDrugDetailInfo(float profitValue) {
        DrugDetail drug = new DrugDetail();
        try {

            profitValue = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(profitValue), 0f);
            float profitShare = profitValue * Constants.PROFIT_SHARE_PERCENT / 100;
            profitShare = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(profitShare), 0f);
            drug.setProfitValue(profitValue);
            drug.setProfitShare(profitShare);
            //drug.setDrugId(drugI);
            //setDrugAdditionalMarginPricesField(newDrug, qty, drug);
            // setDeliveryPreferences(patientProfile, drug);
            int profitSharePoint = calculatePointsFromProfitShare(drug);
            drug.setPointsFromShare(profitSharePoint);
            float costFromPoints = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(calculateCostFromPoints(profitSharePoint)), 0f);
            drug.setCostFromPoints(costFromPoints);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugList", e);
        }
        return drug;
    }

    //////////////////////////////////////////////////////
    public DrugDetail getDrugDetailInfoTemp(Long drugId, Integer qty, PatientProfile patientProfile) {
        DrugDetail drug = new DrugDetail();
        try {
            DrugDetail newDrug = (DrugDetail) patientProfileDAO.findRecordById(new DrugDetail(), drugId);
            if (newDrug != null && newDrug.getDrugNDC() != null) {
                float drugProfitPercent = newDrug.getMarginPercent();
                float additionalFee = newDrug.getAdditionalFee();
                float basePrice = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(newDrug.getBasePrice()), 0f);
                newDrug.setBasePrice(basePrice);
                float drugCost = newDrug.getBasePrice() * qty;
                drug.setDrugCost(drugCost);
                float drugProfit = drugCost * (drugProfitPercent / 100);
                float totalFee = drugCost + drugProfit + additionalFee;
                drug.setTotalPrice(totalFee);
                //drug.setDrugId(drugI);
                //setDrugAdditionalMarginPricesField(newDrug, qty, drug);
                // setDeliveryPreferences(patientProfile, drug);
                getDrugsLookUpCalculation(patientProfile, drug);
                drug.setDrugDetailId(newDrug.getDrugDetailId());
                drug.setDrugNDC(newDrug.getDrugNDC());//(newDrug.getDrugNDC());
                drug.setBrandName(AppUtil.getSafeStr(newDrug.getDrugBasic().getBrandName(), ""));
                drug.setGenericName(AppUtil.getSafeStr(newDrug.getDrugBasic().getDrugGeneric().getGenericName(), ""));
                drug.setFormDesc(AppUtil.getSafeStr(newDrug.getDrugForm().getFormDescr(), "") + "(" + qty + ")");
                drug.setAdditionalMargin(additionalFee);
                drug.setProfitValue(drugProfit);
                drug.setRequiresHandDelivery(newDrug.getRequiresHandDelivery());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugList", e);
        }
        return drug;
    }

    //////////////////////////////////////////////////////
    public Drug getDrugDetails(Integer drugId, Integer qty, PatientProfile patientProfile) {
        Drug drug = new Drug();
        try {
            Drug newDrug = patientProfileDAO.getDrugById(drugId);
            if (newDrug != null && newDrug.getDrugId() != null) {
                drug.setDrugId(drugId);
//                setDrugAdditionalMarginPricesField(newDrug, qty, drug);//not exist class and db
                // setDeliveryPreferences(patientProfile, drug);
                getDrugsLookUpCalculation(patientProfile, drug);
                drug.setDrugName(newDrug.getDrugBrand().getDrugBrandName());
                drug.setGenericName(newDrug.getDrugBrand().getDrugGenericTypes().getDrugGenericName());
            }
        } catch (Exception e) {
            logger.error("Exception -> getDrugList", e);
        }
        return drug;
    }

    /////////////////////////////////////////////////////////////////////////
    public int calculatePointsFromProfitShare(DrugDetail drug) {
        float profitShare = drug.getProfitShare();
        float pointsCostwithoutPrcessingCharges = 0;
        int pointsFromShare = 0;
        FeeSettings settings = (FeeSettings) this.patientProfileDAO.findRecordById(new FeeSettings(), 4);
        if (settings != null) {
            pointsCostwithoutPrcessingCharges = settings.getFee().floatValue();
            if (pointsCostwithoutPrcessingCharges > 0) {
                Float f = (profitShare / pointsCostwithoutPrcessingCharges);
                pointsFromShare = f.intValue();
            }
        }
        return pointsFromShare;
    }

    /////////////////////////////////////////////////////////////////////////
    public float calculateCostFromPoints(int points) {

        float cost = 0f;
        FeeSettings settings = (FeeSettings) this.patientProfileDAO.findRecordById(new FeeSettings(), 2);
        if (settings != null) {
            float pointsCostwithoutPrcessingCharges = settings.getFee().floatValue();
            if (pointsCostwithoutPrcessingCharges > 0) {
                cost = points * pointsCostwithoutPrcessingCharges;
            }
        }
        return cost;
    }

    /////////////////////////////////////////////////////////////////////////
    private void getDrugsLookUpCalculation(PatientProfile patientProfile, DrugDetail drug) throws Exception {
        try {

            //calculatePointsFromProfitShare(drug);
            RewardPoints rewardPoints = getMyRewardsPoints(patientProfile.getPatientProfileSeqNo());
            OrderDetailDTO detailDTO = getRedeemPointsWs(rewardPoints.getAvailablePoints().toString());
            //Double dp = drug.getTotalPrice() * 0.9;
            Double dp = drug.getDrugCost().doubleValue(); //Todo drug.getTotalPrice().doubleValue();
            logger.info("Drug Price:: " + dp);
//            if (detailDTO <= dp) {
//                logger.info("Drug total Price:: " + drug.getTotalPrice() + " Total RedeemPointsCost:: " + detailDTO.getRedeemPointsCost());
//                getDrugLookUpCalculation(drug, detailDTO, rewardPoints, rewardPoints.getAvailablePoints(), dp);
//            } else {
//                FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getRecordByType(new FeeSettings(), Constants.PERPOINTVALUE);
//                Double p = dp / feeSettings.getFee().doubleValue();
//                Long redemainPoints = Math.round(p);
//                logger.info("Round value:: " + redemainPoints);
//                detailDTO = getRedeemPointsWs(redemainPoints.toString());
//                getDrugLookUpCalculation(drug, detailDTO, rewardPoints, redemainPoints, dp);
//            }
        } catch (Exception e) {
            logger.error("Exception:: getDrugsLookUpCalculation", e);
        }

    }

    private Long getDrugsLookUpCalculation(DrugDetail drug,
            Long lifeTimePoints, Long availablePoints) throws Exception {
        try {

            //calculatePointsFromProfitShare(drug);
//            RewardPoints rewardPoints = getMyRewardsPoints(patientProfile.getId());
            OrderDetailDTO detailDTO = getRedeemPointsWs(availablePoints.toString());
            //Double dp = drug.getTotalPrice() * 0.9;
            Double dp = drug.getDrugCost().doubleValue(); //Todo drug.getTotalPrice().doubleValue();
            //logger.info("90% of Drug Price:: " + dp);
//            if (detailDTO.getRedeemPointsCost() <= dp) {
//                logger.info("Drug total Price:: " + drug.getTotalPrice() + " Total RedeemPointsCost:: " + detailDTO.getRedeemPointsCost());
//                getDrugLookUpCalculation(drug, detailDTO, lifeTimePoints, availablePoints, dp);
//                return 0L;
//            } else {
//                FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getRecordByType(new FeeSettings(), Constants.PERPOINTVALUE);
//                Double p = dp / feeSettings.getFee().doubleValue();
//                Long redemainPoints = Math.round(p);
//                logger.info("Round value:: " + redemainPoints);
//                detailDTO = getRedeemPointsWs(redemainPoints.toString());
//                getDrugLookUpCalculation(drug, detailDTO, lifeTimePoints, redemainPoints, dp);
//                return Math.abs(availablePoints - redemainPoints);
//            }
        } catch (Exception e) {
            logger.error("Exception:: getDrugsLookUpCalculation", e);
            return 0L;
        }
        return availablePoints;

    }

    /////////////////////////////////////////////////////////////////////////
    private void getDrugLookUpCalculation(DrugDetail drug, OrderDetailDTO detailDTO, Long lifeTimePoints,
            Long redemainPoints, Double dp) {
        try {
//            Double finalPrice = dp - detailDTO.getRedeemPointsCost();
//            logger.info("Final Price:: " + finalPrice);
            if (drug.getDrugCost() != null) {
//                Double finalDrugCost = drug.getDrugCost() - detailDTO.getRedeemPointsCost();
//                drug.setFinalDrugCost(AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(finalDrugCost), 0d));
            }

            //Double finalTotalPrice = (drug.getTotalPrice() * 0.1) + finalPrice;
            //logger.info("After Calculate :: " + finalTotalPrice);
            drug.setTotalPrice(AppUtil.getSafeFloat(CommonUtil.getDecimalFormat(drug.getTotalPrice().doubleValue()), 0f));
//            drug.setFinalPrice(AppUtil.getSafeFloat(CommonUtil.getDecimalFormat(finalPrice.doubleValue()), 0f));
            drug.setRedeemedPoints(redemainPoints);
//            drug.setRedeemedPointsPrice(detailDTO.getRedeemPointsCost().floatValue());
            drug.setLifeTimePoints(lifeTimePoints);
            //saveRewardHistory("Drug Look Up", availablePoints.intValue(), patientProfile.getId(), Constants.MINUS);
        } catch (Exception e) {
            logger.error("Exception:: getDrugLookUpCalculation", e);
        }

    }

    /////////////////////////////////////////////////////////////////////////
    private void getDrugsLookUpCalculation(PatientProfile patientProfile, Drug drug) throws Exception {
        try {
            RewardPoints rewardPoints = getMyRewardsPoints(patientProfile.getPatientProfileSeqNo());
            OrderDetailDTO detailDTO = getRedeemPointsWs(rewardPoints.getAvailablePoints().toString());
            //Double dp = drug.getTotalPrice() * 0.9;
            Double dp = drug.getDrugCostWithoutMargin(); //Todo drug.getTotalPrice();
//            logger.info("90% of Drug Price:: " + dp);
//            if (detailDTO.getRedeemPointsCost() <= dp) {
//                logger.info("Drug total Price:: " + drug.getTotalPrice() + " Total RedeemPointsCost:: " + detailDTO.getRedeemPointsCost());
//                getDrugLookUpCalculation(drug, detailDTO, rewardPoints, rewardPoints.getAvailablePoints(), dp);
//            } else {
//                FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getRecordByType(new FeeSettings(), Constants.PERPOINTVALUE);
//                Double p = dp / feeSettings.getFee().doubleValue();
//                Long redemainPoints = Math.round(p);
//                logger.info("Round value:: " + redemainPoints);
//                detailDTO = getRedeemPointsWs(redemainPoints.toString());
//                getDrugLookUpCalculation(drug, detailDTO, rewardPoints, redemainPoints, dp);
//            }
        } catch (Exception e) {
            logger.error("Exception:: getDrugsLookUpCalculation", e);
        }

    }

    private void getDrugLookUpCalculation(Drug drug, OrderDetailDTO detailDTO, RewardPoints rewardPoints, Long redemainPoints, Double dp) {
        try {
//            Double finalPrice = dp - detailDTO.getRedeemPointsCost();
//            logger.info("Final Price:: " + finalPrice);
//            drug.setFinalDrugCost(AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(finalPrice), 0d));
            //Double finalTotalPrice = (drug.getTotalPrice() * 0.1) + finalPrice;
            //logger.info("After Calculate :: " + finalTotalPrice);
            drug.setTotalPrice(Double.parseDouble(CommonUtil.getDecimalFormat(drug.getTotalPrice())));
//            drug.setFinalPrice(Double.parseDouble(CommonUtil.getDecimalFormat(finalPrice)));
            drug.setRedeemedPoints(redemainPoints);
//            drug.setRedeemedPointsPrice(detailDTO.getRedeemPointsCost().toString());
            drug.setLifeTimePoints(rewardPoints.getLifeTimePoints());
            //saveRewardHistory("Drug Look Up", availablePoints.intValue(), patientProfile.getId(), Constants.MINUS);
        } catch (Exception e) {
            logger.error("Exception:: getDrugLookUpCalculation", e);
        }

    }

    /////////////////////////////////////////////////////////////////////////
    private void getDrugLookUpCalculation(DrugDetail drug, OrderDetailDTO detailDTO, RewardPoints rewardPoints,
            Long redemainPoints, Double dp) {
        try {
//            Double finalPrice = dp - detailDTO.getRedeemPointsCost();
//            logger.info("Final Price:: " + finalPrice);
//            Double finalDrugCost = drug.getDrugCost() - detailDTO.getRedeemPointsCost();
//            logger.info("Final Drug Cost# " + finalDrugCost);
//            drug.setFinalDrugCost(AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(finalDrugCost), 0d));
            //Double finalTotalPrice = (drug.getTotalPrice() * 0.1) + finalPrice;
            //logger.info("After Calculate :: " + finalTotalPrice);
            drug.setTotalPrice(AppUtil.getSafeFloat(CommonUtil.getDecimalFormat(drug.getTotalPrice().doubleValue()), 0f));
//            drug.setFinalPrice(AppUtil.getSafeFloat(CommonUtil.getDecimalFormat(finalPrice), 0f));
            drug.setRedeemedPoints(redemainPoints);
//            drug.setRedeemedPointsPrice(detailDTO.getRedeemPointsCost().floatValue());
            drug.setLifeTimePoints(rewardPoints.getLifeTimePoints());
            //saveRewardHistory("Drug Look Up", availablePoints.intValue(), patientProfile.getId(), Constants.MINUS);
        } catch (Exception e) {
            logger.error("Exception:: getDrugLookUpCalculation", e);
        }

    }

    //////////////////////////////////////not exist class and db////////////////////////////////////
//    private void setDrugAdditionalMarginPricesField(Drug newDrug, Integer qty, Drug drug) throws Exception {
////        List<DrugAdditionalMarginPrices> list = patientProfileDAO.getDrugAdditionalMarginPrices(newDrug.getDrugBrand().getDrugGenericTypes().getDrugTherapyClass().getDrugCategory().getId());//not exist class and db
//        if (list.size() > 0) {
//            for (DrugAdditionalMarginPrices damp : list) {
//                if (damp != null && damp.getId() != null) {
//                    if (qty >= damp.getDrugQtyFrom() && qty <= damp.getDrugQtyTo()) {
//                        logger.info("Drug Mac Price: " + newDrug.getDrugMacPrice() + " Drug Margin Value: " + damp.getDrugMarginValue());
//                        Double cost = newDrug.getDrugMacPrice() * qty;
//                        logger.info("Drug Mac Price * qty: " + cost);
//                        BigDecimal totalCost = damp.getDrugMarginValue().add(BigDecimal.valueOf(cost));
//                        logger.info("Total Cost is: " + totalCost);
//                        DecimalFormat df = new DecimalFormat("#.00");
//                        String drugCost = df.format(totalCost);
//                        logger.info("Drug Cost: " + drugCost);
//                        drug.setDrugCostWithoutMargin(cost);
//                        drug.setCost(totalCost.doubleValue());
//                        drug.setTotalPrice(totalCost.doubleValue());
//                        drug.setAdditionalMargin(damp.getDrugMarginValue());
//                        
//                        break;
//                    } else {
//                        logger.info("Qty not exist: DrugQtyFrom:: " + damp.getDrugQtyFrom() + " DrugQtyTo is: " + damp.getDrugQtyTo());
//                    }
//                } else {
//                    logger.info("DrugAdditionalMarginPrices is null");
//                }
//            }
//        } else {
//            logger.info("DrugAdditionalMarginPrices list is empty:");
//        }
//    }
    private void setDeliveryPreferences(PatientProfile patientProfile, Drug drug) throws Exception {
        PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getDefaultPatientDeliveryAddress(patientProfile.getPatientProfileSeqNo());
        if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getZip() != null && patientDeliveryAddress.getDeliveryPreferences() != null && patientDeliveryAddress.getDeliveryPreferences().getId() != null) {
            DeliveryPreferences deliveryPreferences = patientDeliveryAddress.getDeliveryPreferences();
            if (deliveryPreferences != null && deliveryPreferences.getId() != null) {
                if (patientDeliveryAddress.getId() != null && patientDeliveryAddress.getZip() != null && patientDeliveryAddress.getDeliveryPreferences() != null && patientDeliveryAddress.getDeliveryPreferences().getId() != null) {
                    logger.info("Patient zip code is: " + patientDeliveryAddress.getZip() + " deliveryPreferences Id is: " + deliveryPreferences.getId());
                    ZipCodeCalculation zipCodeCalculations = patientProfileDAO.getZipCodeCalculationByProfileId(patientProfile.getPatientProfileSeqNo(), patientDeliveryAddress.getDeliveryPreferences().getId(), patientDeliveryAddress.getZip());
                    if (zipCodeCalculations == null) {
                        getDistanceFeeDTO(patientDeliveryAddress.getZip(), patientProfile.getPatientProfileSeqNo());
                    }
                    zipCodeCalculations = patientProfileDAO.getZipCodeCalculationByProfileId(patientProfile.getPatientProfileSeqNo(), patientDeliveryAddress.getDeliveryPreferences().getId(), patientDeliveryAddress.getZip());
                    if (zipCodeCalculations != null && zipCodeCalculations.getId() != null) {
                        logger.info("Miles is: " + zipCodeCalculations.getMiles());
                        PharmacyZipCodes pharmacyZipCodes = this.getPharmacyZipCodes();
                        for (DeliveryDistanceFee deliveryDistanceFee : pharmacyZipCodes.getDeliveryDistanceFeesList()) {
                            if (deliveryDistanceFee != null && deliveryDistanceFee.getId() != null) {
                                if (zipCodeCalculations.getMiles() >= deliveryDistanceFee.getDeliveryDistances().getDistanceFrom() && zipCodeCalculations.getMiles() <= deliveryDistanceFee.getDeliveryDistances().getDistanceTo()) {
                                    logger.info("Distance Delivery Preferences id: " + deliveryDistanceFee.getDeliveryPreferenceses().getId() + " deliveryPreferences Id: " + deliveryPreferences.getId());
                                    if (deliveryDistanceFee.getDeliveryPreferenceses().getId().equals(patientDeliveryAddress.getDeliveryPreferences().getId())) {
                                        DeliveryPreferencesDTO deliveryPreferencesDTO = new DeliveryPreferencesDTO();
                                        deliveryPreferencesDTO.setId(deliveryPreferences.getId());
                                        deliveryPreferencesDTO.setName(deliveryPreferences.getName());
                                        if (deliveryDistanceFee.getDeliveryFee() != null) {
                                            logger.info("Delivery fee is: " + deliveryDistanceFee.getDeliveryFee());
                                            deliveryPreferencesDTO.setCost(deliveryDistanceFee.getDeliveryFee());
                                            if (drug.getCost() != null) {
                                                BigDecimal totalPrice = deliveryDistanceFee.getDeliveryFee().add(new BigDecimal(drug.getCost()));
                                                logger.info("finalPrice is: " + totalPrice);
                                                drug.setTotalPrice(totalPrice.doubleValue());
                                            }
                                        }
                                        drug.setShippingFee(deliveryPreferencesDTO);
                                    } else {
                                        logger.info("DeliveryPreferenceses id doest not match");
                                    }
                                }
                            }
                        }
                    } else {
                        logger.info("Zip Code Calculations is null");
                    }
                } else {
                    drug.setShippingFee(new String[0]);
                    logger.info("Select No Defualt Address against this profile id: " + patientProfile.getPatientProfileSeqNo());
                }
            } else {
                drug.setShippingFee(new String[0]);
                logger.info("deliveryPreferences null against profile id");
            }
        } else {
            drug.setShippingFee(new String[0]);
            logger.info("Cannot associate shipping fee with profile");
        }
    }

    public String loadDrugGeneric(String drugBrandName) {
        String msg = "";
        try {

            DrugBasic drug = (DrugBasic) this.patientProfileDAO.findByPropertyUnique(new DrugBasic(), "brandName", drugBrandName, Constants.HIBERNATE_EQ_OPERATOR, "brandName", Constants.HIBERNATE_DESC_ORDER);
            if (drug != null) {
                msg = drug.getDrugGeneric().getGenericName();
            }
            return msg;
        } catch (Exception e) {
            return msg;
        }
    }

    public CampaignMessages getNotificationMsgs(String message, String eventName) {
        CampaignMessages campaignMessages = new CampaignMessages();
        try {
            //proccessing Y,YES,YEP ect.
            ValidResponse validResponse = textFlowDAO.getValidResponse(message);
            if (validResponse != null && validResponse.getVresponseId() != null) {
                Event event = textFlowDAO.getEventByStaticValue(eventName.trim());
                if (event == null) {
                    logger.info("No such event defined (System will return)");
                    return null;
                }
                EventHasFolderHasCampaigns eventHasFolderHasCampaigns = textFlowDAO.getEventHasFolderHasCampaign(Integer.parseInt(Constants.campaignId), event.getEventId(), Constants.SMS);
                if (eventHasFolderHasCampaigns == null) {
                    logger.info("No folder associated to this campaign (System will return)");
                    return null;
                }
                int folderId = eventHasFolderHasCampaigns.getFolderId();
                logger.info("Folder Id : " + folderId);
                List<CampaignMessages> campaignMessagesList = textFlowDAO.getCampaignMessagesByCommunicationType(Integer.parseInt(Constants.campaignId), folderId);
                if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                    logger.info("No messages found for (System will return)");
                    return null;
                }

                CampaignMessagesResponse campaignMessagesResponse = textFlowDAO.getCampaignMessagesResponseByResComm(Integer.parseInt(Constants.campaignId), folderId, validResponse.getResponse().getResponseTitle());
                if (campaignMessagesResponse == null || campaignMessagesResponse.getCampaignMessagesResponseId() == null) {
                    logger.info("No Campaign Messages Response.");
                    return null;
                }
                campaignMessages = campaignMessagesResponse.getCampaignMessages();
            }
        } catch (Exception e) {
            logger.error("Exception -> getNotificationMsgs", e);
        }
        return campaignMessages;
    }

    ////////////////////////////////////////////////////////////////////////////
    public void reminderPOAExpiry(org.apache.log4j.Logger successed, org.apache.log4j.Logger failed, CampaignMessages campaignMessages) {
        try {
            Date date = new Date();
            Date aboutToExpiry = DateUtil.addDaysToDate(date, Constants.POA_EXPIRY_DAYS);
        } catch (Exception e) {
            e.printStackTrace();
            failed.error("Exception# reminderPOAExpiry# " + e);
        }

    }
    ///////////////////////////////////////////////////////////////////////////

    public boolean saveNotificationMessages(CampaignMessages campaignMessages, String isTestMsg, Integer profileId) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(this.getMessageSubjectWithprocessedPlaceHolders(
                        campaignMessages.getSubject(), "0", null));
               /**check will be able after discussion with ios team**/
         if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(campaignMessages.getSmstext());
                } 
               
                notificationMessages.setPushSubject((this.getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), "0", null)));
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsTestMsg(isTestMsg);
            notificationMessages.setPushSubject(AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""));
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && notificationMessages != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                // logger.info("Push Notification " + notificationMessages.getPushSubject());
                if (AppUtil.getSafeStr(profile.getOsType(), "20").equals("20")) {

                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            e.printStackTrace();
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    public boolean saveNotificationMessages(CampaignMessages campaignMessages, String isTestMsg, Integer profileId, Long surveyId, Practices dbPractise, String messge) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(this.getMessageSubjectWithprocessedPlaceHolders(
                campaignMessages.getSubject(), "0", dbPractise));
                if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(messge);
                }           
                notificationMessages.setPushSubject((this.getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), "0", dbPractise)));
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsTestMsg(isTestMsg);
            notificationMessages.setSurveyLogsId(surveyId);
            logger.info("SurveyLog Id :"+surveyId);
            notificationMessages.setPushSubject(AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""));
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            notificationMessages.setAssignSurveyId(surveyId);//SurveyId(surveyId);
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && notificationMessages != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                // logger.info("Push Notification " + notificationMessages.getPushSubject());
                if (AppUtil.getSafeStr(profile.getOsType(), "20").equals("20")) {

                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            e.printStackTrace();
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    public boolean saveNotificationMessages(CampaignMessages campaignMessages, String isTestMsg, Integer profileId, QuestionAnswer dbQuestionAnswer, Practices dbPractise) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(this.getMessageSubjectWithprocessedPlaceHolders(
                        campaignMessages.getSubject(), "0", dbPractise));
                         if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(campaignMessages.getSmstext());
                } 
//                notificationMessages.setMessageText(campaignMessages.getSmstext());
                notificationMessages.setPushSubject((this.getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), "0", dbPractise)));
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId)); 
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE); 
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsTestMsg(isTestMsg);
            notificationMessages.setQuestionId(dbQuestionAnswer.getId());
            notificationMessages.setPushSubject(AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""));
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            dbQuestionAnswer.setNotificationMessages(notificationMessages);
            update(dbQuestionAnswer);
//             notificationMessages.setQuestionId(dbQuestionAnswer.getId());
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && notificationMessages != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                // logger.info("Push Notification " + notificationMessages.getPushSubject());
                if (AppUtil.getSafeStr(profile.getOsType(), "20").equals("20")) {

                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            e.printStackTrace();
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    public boolean saveNotificationMessages(CampaignMessages campaignMessages, String isTestMsg, Integer profileId, String message, String subject) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(this.getMessageSubjectWithprocessedPlaceHolders(
                        subject, "0", null));
                         if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(message);
                } 
//                notificationMessages.setMessageText(message);
                notificationMessages.setPushSubject((this.getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), "0", null)));
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsTestMsg(isTestMsg);
            notificationMessages.setPushSubject(AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""));
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && notificationMessages != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                // logger.info("Push Notification " + notificationMessages.getPushSubject());
                if (AppUtil.getSafeStr(profile.getOsType(), "20").equals("20")) {

                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                }
            }
        } catch (Exception e) {
            isSaved = false;
            e.printStackTrace();
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    public boolean saveNotificationMessages(CampaignMessages campaignMessages, String isTestMsg,
            Integer profileId, String orderId) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campaignMessages.getSubject(), ""), orderId, null));// notificationMessages.setSubject(campaignMessages.getSubject());
                        if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(campaignMessages.getSmstext());
                } 
//                notificationMessages.setMessageText(campaignMessages.getSmstext());
                notificationMessages.setPushSubject(campaignMessages.getPushNotification());
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsTestMsg(isTestMsg);
            notificationMessages.setOrderPrefix("RXD");
            Order order = this.findOrderById(orderId);
            if (order != null) {
                order.setLastReminderDate(new Date());
                notificationMessages.setOrders(order);
                patientProfileDAO.saveOrUpdate(order);
            }
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                // logger.info("Push Notification " + notificationMessages.getPushSubject());
                //  System.out.println("Profile for sending push " + profile.getFirstName());
                String osType = AppUtil.getSafeStr(profile.getOsType(), "20");
                if (osType.equals("20"))//android
                {
                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), order, notificationMessages,
                            "RXD", notificationMessages.getPushSubject(), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), order, notificationMessages,
                            "RXD", notificationMessages.getPushSubject(), profile);
                }
            }

        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean saveNotificationMessages(CampaignMessages campaignMessages, String isTestMsg,
            Integer profileId, Order order, String message, String subject, Practices dbPractise) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(subject, ""), order.getId(), dbPractise));// notificationMessages.setSubject(campaignMessages.getSubject());
                       if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(message);
                } 
//                notificationMessages.setMessageText(message);
                notificationMessages.setPushSubject(campaignMessages.getPushNotification());
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsTestMsg(isTestMsg);
            notificationMessages.setOrderPrefix("RXD");
            Order order2 = this.findOrderById(order.getId());
            if (order2 != null) {
                //order.setLastReminderDate(new Date());
                notificationMessages.setOrders(order2);
                //patientProfileDAO.saveOrUpdate(order);
            }
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            if (notificationMessages.getMessageType() != null && notificationMessages.getMessageType().getId() != null) {
                notificationMessages.setMessageTypeId(notificationMessages.getMessageType().getId().getMessageTypeId());
                if (notificationMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID) {
                    QuestionAnswer questionAnswer = patientProfileDAO.getQuestionAnswerByNotificationId(notificationMessages.getId());
                    if (questionAnswer != null) {
                        notificationMessages.setQuestionId(questionAnswer.getId());
                        notificationMessages.setQuestionText(questionAnswer.getQuestion());
                        notificationMessages.setQuestionAnserImg(questionAnswer.getQuestionImge());
                        notificationMessages.setQuesAnswerText(questionAnswer.getAnswer());
                    }
                }
            }

//               PharmacyPatientMessage pharmacyPatientMessage = patientProfileDAO.getPharmacyPatientMessageByOrderId(order.getId());
//                    if (pharmacyPatientMessage != null) {
//                        notificationMessages.setPatientOrdMessge(pharmacyPatientMessage.getMessage());
//                        notificationMessages.setPatientOrdMsgSubject(pharmacyPatientMessage.getSubject());
//                       
//                    }
            MessageThreads dbMessageThreads = patientProfileDAO.getMessageThreadByOrderId(order.getId());
            if (dbMessageThreads != null) {
                notificationMessages.setPatientOrdMessge(dbMessageThreads.getMessge());
                notificationMessages.setPatientOrdMsgSubject("Pharmacy Direct Message");
            }
            OrderCustomDocumments orderCustomDocumments = patientProfileDAO.getOrderCustomDocumments(order.getId());
            if (orderCustomDocumments != null) {
                String url = PropertiesUtil.getProperty("PHP_WEB_PDF") + orderCustomDocumments.getCustomDocuments();
                notificationMessages.setOrderPdfDocument(url);
                notificationMessages.setOrderDocumentMessgeSub(orderCustomDocumments.getMessage());
            }
            if (order.getRefillsRemaining() != null
                    && order.getRefillsRemaining() > 0 && order.getRefillDone() == 0
                    && order.getNextRefillDate() != null) {
                Date nextRefillD = order.getNextRefillDate();
                Date today = new Date();
                long dayDiff = DateUtil.dateDiffInDays(today, nextRefillD);
                int diff = (int) dayDiff;
                if (diff >= 0) {
                    notificationMessages.setRefillRemainingDaysCount(diff);
                } else {
                    notificationMessages.setRefillRemainingDaysCount(diff);
                }
            }
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                //  logger.info("Push Notification " + notificationMessages.getPushSubject());
                //System.out.println("Profile for sending push " + profile.getFirstName());
                String osType = AppUtil.getSafeStr(profile.getOsType(), "20");
                if (osType.equals("20"))//android
                {
                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), order2, notificationMessages,
                            "RXD", notificationMessages.getPushSubject(), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), order2, notificationMessages,
                            "RXD", notificationMessages.getPushSubject(), profile);
                }
            }

        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean saveNotificationMessages(CampaignMessages campaignMessages, Integer profileId, String orderId) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campaignMessages.getSubject(), ""), orderId, null));
                        if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(campaignMessages.getSmstext());
                } 
//                notificationMessages.setMessageText(campaignMessages.getSmstext());
                notificationMessages.setPushSubject(campaignMessages.getPushNotification());
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            //////////////////////////////////
            Order order = (Order) orderDAO.findRecordById(new Order(), orderId);
//            order.setId(orderId);
            notificationMessages.setOrders(order);
            /////////////////////////////////
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsEventFire(false);
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                // logger.info("Push Notification " + notificationMessages.getPushSubject());
                String osType = AppUtil.getSafeStr(profile.getOsType(), "20");
                if (osType.equals("20"))//android
                {
                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), order, notificationMessages,
                            "RXD", AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), order, notificationMessages,
                            "RXD", AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), profile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    public List<NotificationMessages> getNotificationMessagesByPatientId(Integer profileId) {
        List<NotificationMessages> list = new ArrayList<>();
        try {
            for (NotificationMessages messages : patientProfileDAO.getNonArchivedNotificationMessagesByProfileId(profileId)) {
//                NotificationMessages notificationMessages = getNotificationMessages(messages);//old 
                  NotificationMessages notificationMessages = getNotificationMessagesDetialByPatient(messages,""); //new order Object
                list.add(notificationMessages);
            }
            Collections.sort(list, CommonUtil.byDate);
        } catch (Exception e) {
            logger.error("Exception -> getNotificationMessagesByProfileId", e);
            e.printStackTrace();
        }
        return list;
    }

    private NotificationMessages getNotificationMessages(NotificationMessages messages) {
        NotificationMessages notificationMessages = new NotificationMessages();
        try {
            notificationMessages.setId(messages.getId());
            notificationMessages.setNotificationMsgId(messages.getId());
            if (messages.getOrders() != null && messages.getOrders().getId() != null) {
                Order ord = messages.getOrders();
                notificationMessages.setOrderId(ord.getId());
                notificationMessages.setRxNo(AppUtil.getSafeStr(ord.getRxNumber(), ""));
                notificationMessages.setPatientOutOfPocket(ord.getRxPatientOutOfPocket());
                notificationMessages.setQty(ord.getQty());
                notificationMessages.setDrugType(ord.getDrugType());
                notificationMessages.setDaysSupply(ord.getDaysSupply());
                notificationMessages.setOrderStatus(ord.getOrderStatus().getName() != null ? ord.getOrderStatus().getName() : "null");
                notificationMessages.setRxExpiredDate(ord.getRxExpiredDate() != null ? ord.getRxExpiredDate() : null);
                notificationMessages.setRefillsRemaining(ord.getRefillsRemaining());
                notificationMessages.setViewStatus(ord.getViewStatus());
                notificationMessages.setAssistanceAuth(ord.getRxPatientOutOfPocket());
                try {
//                    PharmacyPatientMessage pharmacyPatientMessage = patientProfileDAO.getPharmacyPatientMessageByOrderId(ord.getId());
//                    if (pharmacyPatientMessage != null) {
//                        notificationMessages.setPatientOrdMessge(pharmacyPatientMessage.getMessage());
//                        notificationMessages.setPatientOrdMsgSubject(pharmacyPatientMessage.getSubject());
//                        notificationMessages.setPatientOrdMsgId(pharmacyPatientMessage.getId());
//                    }
                    MessageThreads dbMessageThreads = patientProfileDAO.getMessageThreadByOrderId(ord.getId());
                    if (dbMessageThreads != null) {
                        notificationMessages.setPatientOrdMessge(dbMessageThreads.getMessge());
                        notificationMessages.setPatientOrdMsgSubject("Pharmacy Direct Message");
                    }
                    OrderCustomDocumments orderCustomDocumments = patientProfileDAO.getOrderCustomDocumments(ord.getId());
                    if (orderCustomDocumments != null) {
                        String url = PropertiesUtil.getProperty("PHP_WEB_PDF") + orderCustomDocumments.getCustomDocuments();
                        notificationMessages.setOrderPdfDocument(url);
                        notificationMessages.setOrderDocumentMessgeSub(orderCustomDocumments.getMessage());
//                   9     url = "http://compliancerewards.ssasoft.com/compliancereward/public/"+orderCustomDocumments.getCustomDocuments();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("Exception:PatientProfileService: getNotificationMessages-> pharmacyPatientMessage", e);
                }
                String filledDate = "";
                if (ord.getRxProcessedAt() == null) {
                    filledDate = DateUtil.dateToString(ord.getUpdatedAt(), Constants.USA_DATE_FORMATE);
                } else {
                    filledDate = DateUtil.dateToString(ord.getRxProcessedAt(), Constants.USA_DATE_FORMATE);
                }
                notificationMessages.setLastFilledDate(filledDate != null ? filledDate : "");
                if (ord.getRefillsRemaining() != null
                        && ord.getRefillsRemaining() > 0 && ord.getRefillDone() == 0
                        && ord.getNextRefillDate() != null) {
                    Date nextRefillD = ord.getNextRefillDate();
                    Date today = new Date();
                    long dayDiff = DateUtil.dateDiffInDays(today, nextRefillD);
                    int diff = (int) dayDiff;
                    if (diff >= 0) {
                        notificationMessages.setRefillRemainingDaysCount(diff);
                    } else {
                        notificationMessages.setRefillRemainingDaysCount(diff);
                    }
                }
                if (ord.getDrugDetail2() != null) {
                    DrugDetail2 drugdetial =ord.getDrugDetail2(); 
                    notificationMessages.setBrandRefrance(drugdetial.getBrandReference());
                    notificationMessages.setDrugName(drugdetial.getRxLabelName());
                    notificationMessages.setGenericOrBrand(drugdetial.getGenericOrBrand());
                    String strength = CommonUtil.isNullOrEmpty(ord.getStrength()) || ord.getStrength().equals(drugdetial.getDrugId().toString()) ? drugdetial.getStrength() : ord.getStrength();
                    notificationMessages.setStrength(strength);
                }

                if (ord.getComplianceRewardPoint() != null) {
                    notificationMessages.setEarnedRewardPoint(ord.getComplianceRewardPoint().getCurrentEarnReward());
                    notificationMessages.setRxOutOfPocket(ord.getComplianceRewardPoint().getRxPatientOutOfPocket());
                }

                if (ord.getOrderStatus() != null && CommonUtil.isNotEmpty(ord.getOrderStatus().getName())) {
                    notificationMessages.setOrderStatus(ord.getOrderStatus().getName());
                }
            }
                   notificationMessages.setIsRead(messages.getIsRead());
                   notificationMessages.setIsArchive(messages.getIsArchive());
            if (messages.getMessageType() != null && messages.getMessageType().getId() != null) {
                notificationMessages.setMessageTypeId(messages.getMessageType().getId().getMessageTypeId());
                if (messages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID) {
                    QuestionAnswer questionAnswer = patientProfileDAO.getQuestionAnswerByNotificationId(messages.getId());
                    if (questionAnswer != null) {
                        notificationMessages.setQuestionId(questionAnswer.getId());
                        notificationMessages.setQuestionText(questionAnswer.getQuestion());
                        notificationMessages.setQuestionAnserImg(questionAnswer.getQuestionImge());
                        notificationMessages.setQuesAnswerText(questionAnswer.getAnswer());
                    }
                }
                if (messages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_Survey ||
                        messages.getMessageType().getId().getMessageTypeId() == Constants.SURVEY_REMINDER ||
                        messages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_CBD_SURVY ||
                        messages.getMessageType().getId().getMessageTypeId() == Constants.SECOND_SURVEY_REIMDER) {
                    //After deleting textMessge old data may be crewtaing isssue for mobile 
                    if(messages.getMessageText() != null){
                          notificationMessages.setAssignSurveyId(Long.parseLong(EncryptionHandlerUtil.getDecryptedString(messages.getMessageText())));
                          notificationMessages.setAssignsurvyId(EncryptionHandlerUtil.getDecryptedString(messages.getMessageText()));
                    }else {
                     notificationMessages.setAssignSurveyId(messages.getSurveyLogsId());  
                     notificationMessages.setAssignsurvyId(messages.getSurveyLogsId() != null ? messages.getSurveyLogsId().toString(): "");
                    }


                    AssignedSurvey dbAssignsurvey = patientProfileDAO.getAssignedSurveysById(messages.getPatientProfile().getPatientProfileSeqNo(), messages.getSurveyLogsId());
                    if (dbAssignsurvey != null) {
                        notificationMessages.setAssignStatus(dbAssignsurvey.getStatus());
                    }
                }

            }
            if (messages.getPatientProfile() != null && messages.getPatientProfile().getPatientProfileSeqNo() != null) {
                notificationMessages.setProfileId(messages.getPatientProfile().getPatientProfileSeqNo());
                notificationMessages.setMobileNumber(EncryptionHandlerUtil.getDecryptedString(messages.getPatientProfile().getMobileNumber()));
            }

            notificationMessages.setSubject(EncryptionHandlerUtil.getDecryptedString(messages.getSubject()));
//        notificationMessages.setMessageText(CommonUtil.replaceNotificationMessagesPlaceHolder(EncryptionHandlerUtil.getDecryptedString(messages.getMessageText()), messages.getCreatedOn()));
            Practices practiseDb = patientProfileDAO.getPractiseNameById(messages.getPatientProfile().getPracticeId());
            
            String msg = EncryptionHandlerUtil.getDecryptedString(messages.getMessageText());
            if(msg != null){
                     notificationMessages.setMessageText(msg.replace("[date_]", DateUtil.dateToString(new Date(), Constants.USA_DATE_FORMATE))
                    .replace("[Time_]", DateUtil.dateToString(new Date(), Constants.TIME_HH_MM))
                    .replace("[PRACTICE_NAME]", practiseDb.getPracticename()));
            }
            notificationMessages.setCreatedOn(DateUtil.formatDate(messages.getCreatedOn(), "MM/dd/yyyy"));
            notificationMessages.setCreatedonStringFormat(DateUtil.dateToString(messages.getCreatedOn(), "E MM-dd-yyyy"));

//           Date cd = DateUtil.formatDate(messages.getCreatedOn(), Constants.USA_DATE_FORMATE);
//            SimpleDateFormat frmt = new SimpleDateFormat("MM/dd/yyyy");
//            notificationMessages.setCreatedOn(DateUtil.formatDate(messages.getCreatedOn(), Constants.USA_DATE_FORMATE));
//            notificationMessages.setCreatedOn(frmt.format(messages.getCreatedOn()));
            notificationMessages.setTimeAgo(DateUtil.getDateDiffInSecondsFromCurrentDate(messages.getCreatedOn()));
//            notificationMessages.setIsRead(messages.getIsRead());
//            notificationMessages.setIsArchive(messages.getIsArchive());
            notificationMessages.setIsCritical(messages.getIsCritical());
            notificationMessages.setMessageCategory(Constants.ORDER_NOTIFICATION);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notificationMessages;
    }

    ////////////////////////////////////////////////////////////////////
    public TransferRequest saveRxTransferRequest(Integer profileId, String patientName, String pharmacyName, String pharmacyPhone, String drug, Integer quantity, String videoPath, String rxNumber, String transferId, String drugImg) {
        TransferRequest transferRequest = new TransferRequest();
        try {
            if (CommonUtil.isNotEmpty(transferId)) {
                transferRequest = (TransferRequest) patientProfileDAO.getObjectById(new TransferRequest(), Integer.parseInt(transferId));
            }
            transferRequest.setPatientId(profileId);
            transferRequest.setPatientName(patientName);
            transferRequest.setPharmacyName(pharmacyName);
            transferRequest.setPharmacyPhone(pharmacyPhone);
            transferRequest.setDrug(drug);
            transferRequest.setQuantity(quantity);
            transferRequest.setVideo(videoPath);
            transferRequest.setDrugImg(drugImg);
            transferRequest.setRxNumber(rxNumber);
            transferRequest.setRequestedOn(new Date());
            patientProfileDAO.saveOrUpdate(transferRequest);
        } catch (Exception e) {
            logger.error("Exception -> saveRxTransferRequest", e);
        }
        return transferRequest;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public TransferRequest saveRxTransferRequest(Integer profileId, String patientName, String pharmacyName, String pharmacyPhone,
            String drug, Integer quantity, String videoPath, String rxNumber, String transferId, String drugImg,
            String prescriberName, String prescriberPhone, String orderType) {
        TransferRequest transferRequest = new TransferRequest();
        try {
            if (CommonUtil.isNotEmpty(transferId)) {
                transferRequest = (TransferRequest) patientProfileDAO.getObjectById(new TransferRequest(), Integer.parseInt(transferId));
            }
            transferRequest.setPatientId(profileId);
            transferRequest.setPatientName(patientName);
            transferRequest.setPharmacyName(pharmacyName);
            transferRequest.setPharmacyPhone(pharmacyPhone);
            transferRequest.setDrug(drug);
            transferRequest.setQuantity(quantity);
            transferRequest.setVideo(videoPath);
            transferRequest.setDrugImg(drugImg);
            transferRequest.setRxNumber(rxNumber);
            transferRequest.setRequestedOn(new Date());
            transferRequest.setPrescriberName(prescriberName);
            transferRequest.setPrescribeerPhone(prescriberPhone);
            transferRequest.setIsOrder(orderType);
            patientProfileDAO.saveOrUpdate(transferRequest);
        } catch (Exception e) {
            logger.error("Exception:: saveRxTransferRequest", e);
        }
        return transferRequest;
    }
    ///////////////////////////////////////////////////////////////////////////////////

    public List<NotificationMessages> getNotificationMessagesListById(Integer id) {
        List<NotificationMessages> list = new ArrayList<>();
        try {
            for (NotificationMessages messages : patientProfileDAO.getNotificationMessagesListById(id)) {
                NotificationMessages notificationMessages = getNotificationMessages(messages);
                list.add(notificationMessages);
            }
        } catch (Exception e) {
            logger.error("Exception -> getNotificationMessagesListById", e);
        }
        return list;
    }

    public NotificationMessages updateNotificationMessages(int id, int archiveValue, NotificationMessages notificationMessages, int isRead) {
        try {

            if (notificationMessages != null && notificationMessages.getId() != null) {
                if (CommonUtil.isNullOrEmpty(archiveValue)) {
                    notificationMessages.setIsArchive(Boolean.FALSE);
                } else {
                    notificationMessages.setIsArchive(Boolean.TRUE);
                }
                if (!CommonUtil.isNullOrEmpty(isRead)) {
                    notificationMessages.setIsRead(Boolean.TRUE);
                }
                patientProfileDAO.update(notificationMessages);
                notificationMessages = getNotificationMessages(notificationMessages);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> updateNotificationMessages", e);
        }
        return notificationMessages;
    }

    public NotificationMessages getIsReadNotificationMessagesCount(Integer profileId) {
        NotificationMessages notificationMessages = new NotificationMessages();
        try {
            Long readMessages = patientProfileDAO.getTotalReadNotificationMessages(profileId);
            if (readMessages != null) {
                notificationMessages.setReadMesages(readMessages);
            }
            Long unReadMessages = patientProfileDAO.getTotalUnReadNotificationMessages(profileId);
            if (unReadMessages != null) {
                notificationMessages.setUnReadMessages(unReadMessages);
            }
        } catch (Exception e) {
            logger.error("Exception -> getIsReadNotificationMessagesCount", e);
        }
        return notificationMessages;
    }

    public RewardPoints getRxTransferPoints() {
        RewardPoints newRewardPoints = new RewardPoints();
        try {
            newRewardPoints = (RewardPoints) patientProfileDAO.getRecordByType(new RewardPoints(), Constants.TRANSFERRX);
            if (newRewardPoints != null && newRewardPoints.getId() != null) {
                FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getRecordByType(new FeeSettings(), Constants.PERPOINTVALUE);
                if (feeSettings != null && feeSettings.getId() != null) {
                    BigDecimal totalCost = newRewardPoints.getPoint().multiply(feeSettings.getFee());
                    logger.info("Total cost of rewardpoint * fee value: " + totalCost);
                    Integer point = newRewardPoints.getPoint().intValueExact();
                    logger.info("Points are: " + point);
                    newRewardPoints.setCost(totalCost);
                    newRewardPoints.setPoints(point);
                }
            }
        } catch (Exception e) {
            logger.error("Exception -> getRxTransferPoints", e);
        }
        return newRewardPoints;
    }

    public RewardPoints getMyRewardsPoints(Integer profileId) {
        RewardPoints rewardPoints = new RewardPoints();
        try {
            Long lifeTimePoints = 0l;// = patientProfileDAO.getTotalRewardHistoryPointByType("PLUS", profileId);
            logger.info("life Time Points: " + lifeTimePoints);
            if (lifeTimePoints != null) {
                rewardPoints.setLifeTimePoints(lifeTimePoints);
            } else {
                rewardPoints.setLifeTimePoints(0L);
            }
            Long totalMinusPoints = 0l;// = patientProfileDAO.getTotalRewardHistoryPointByType("MINUS", profileId);
            rewardPoints.setAvailedRewardPoints(totalMinusPoints);
            logger.info("total Minus Points: " + totalMinusPoints);
            if (totalMinusPoints != null && lifeTimePoints != null) {
                Long availablePoints = lifeTimePoints - totalMinusPoints;
                logger.info("availablePoints is: " + availablePoints);
                if (availablePoints > 0) {
                    rewardPoints.setAvailablePoints(availablePoints);
                } else {
                    rewardPoints.setAvailablePoints(0L);
                }
            } else {
                rewardPoints.setAvailablePoints(rewardPoints.getLifeTimePoints());
            }
            List<RewardPoints> rewardPointsList = new ArrayList<>();
            List<RewardPoints> dbRewardPointses = patientProfileDAO.getAllRecords(new RewardPoints());
            for (RewardPoints rp : dbRewardPointses) {
                Integer points = rp.getPoint().intValueExact();
                logger.info("Points are: " + points);
                rp.setPoints(points);
                rewardPointsList.add(rp);
            }
            rewardPoints.setBonusPoints(rewardPointsList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getMyRewardsPoints", e);
        }
        return rewardPoints;
    }

    public void saveImageOrVideo(byte[] decodedFrontCard, File file, String imageFormat, String command) throws IOException {
        try {
            logger.info("Image format is: " + imageFormat);
            BufferedImage imag = ImageIO.read(new ByteArrayInputStream(decodedFrontCard));
            ImageIO.write(imag, imageFormat, file);
            String output = CommonUtil.executeCommand(command);
            logger.info("Command Result:: " + output);
        } catch (Exception e) {
            logger.error("Exception -> saveImageOrVideo", e);
        }
    }

    /**
     * @param offset
     * @return
     * @Author Haider Ali
     */
    public List getDrugCategory(Integer offset) {
        logger.info("Getting Drug Category: ");
        try {
            List lst_drugCategory = patientProfileDAO.getDrugCategoryListAll(offset, Constants.PAGING_CONSTANT.RECORDS_PER_PAGE);
            logger.info("Total Drug Category size : " + lst_drugCategory.size());
            return lst_drugCategory;
        } catch (Exception ex) {
            logger.error("Exception -> getDrugCategory", ex);
        }
        return null;

    }
    //        ////////////////////////////not exist class and db./////////////////////////
//    public Long getTotalDrugCategory() {
//        
//        logger.debug("Getting Drug Category: ");
//        try {
//            
//            Long l_totalRecords = patientProfileDAO.getTotalRecords(DrugCategory.class);
//            logger.debug("Total Drug Category size : " + l_totalRecords);
//            return l_totalRecords;
//        } catch (Exception ex) {
//            logger.error("Exception -> getDrugCategory", ex);
//        }
//        return null;
//        
//    }
    //        ////////////////////////////not exist class and db./////////////////////////
//    public List getDrugsByParameter(String a_searchParameter) {
//        logger.info("Getting Drug Category: ");
//        try {
//            List lst_drugCategory = patientProfileDAO.getDrugSearch(DrugCategory.class, a_searchParameter);
//            logger.info("Total Drug Category size : " + lst_drugCategory.size());
//            return lst_drugCategory;
//        } catch (Exception ex) {
//            logger.error("Exception -> getDrugCategory", ex); 
//        }
//        return null;
//        
//    }

    public CampaignMessages getNextMessages(CampaignMessages campaignMessages) {
        CampaignMessages messages = new CampaignMessages();
        try {
            logger.info("Next Message Type id: " + campaignMessages.getMessageType().getId().getMessageTypeId());
            if (campaignMessages.getMessageType().getId().getMessageTypeId() != 0) {
                CampaignMessagesResponse campaignMessagesResponse = patientProfileDAO.getCampaignMessagesResponseByCampaignMessageId(campaignMessages.getMessageType().getId().getMessageTypeId());
                if (campaignMessagesResponse != null && campaignMessagesResponse.getCampaignMessagesResponseId() != null) {
                    if (campaignMessagesResponse.getNextMessage() != null) {
                        messages = textFlowDAO.getCampaignMessagesByMessageTypeId(campaignMessagesResponse.getNextMessage(), campaignMessagesResponse.getFolderId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception -> sendNextMessages", e);
        }
        return messages;
    }

    public void sendNextMessages(CampaignMessages campaignMessages, Integer profileId) {
        try {
            CampaignMessages nextCampaignMessages = this.getNextMessages(campaignMessages);
            if (nextCampaignMessages != null && nextCampaignMessages.getMessageId() != null) {
                logger.info("Send Next Notification messages.");
                logger.info("Next Msg subject are: " + nextCampaignMessages.getSubject() + " Next Message Text are:" + nextCampaignMessages.getSmstext());
                System.out.println("Next Msg subject are: " + nextCampaignMessages.getSubject() + " Next Message Text are:" + nextCampaignMessages.getSmstext());
                nextCampaignMessages.setSmstext(AppUtil.getSafeStr(nextCampaignMessages.getSmstext(), "").
                        replace("[date_time]", DateUtil.dateToString(new Date(), "MM/dd/YYYY hh:mm a")));
                if (this.saveNotificationMessages(nextCampaignMessages, Constants.NO, profileId)) {
                    logger.info("next Notification messages sent and saved.");
                }
            } else {
                logger.info("There is no Next Notification messages.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDetailDTO getPlaceRxOrderDetailsWs(PatientProfile patientProfile, long drugId, Integer qty, String drugType, String strength) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        try {
            DrugDetail drugDetail = this.getDrugDetailInfo(drugId, qty, patientProfile);
            orderDetailDTO.setDrugDetail(drugDetail);
            RewardPoints rewardPoints = getMyRewardsPoints(patientProfile.getPatientProfileSeqNo());
            if (rewardPoints != null && rewardPoints.getAvailablePoints() != null) {
//                orderDetailDTO.setAvailablePoints(rewardPoints.getAvailablePoints());
            }
            orderDetailDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getDefaultPatientDeliveryAddress(patientProfile.getPatientProfileSeqNo());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                State state = (State) patientProfileDAO.getObjectById(new State(), patientDeliveryAddress.getState().getId());
                if (patientDeliveryAddress.getApartment() != null && !"".equals(patientDeliveryAddress.getApartment())) {
//                    orderDetailDTO.setShippingAddress(patientDeliveryAddress.getAddress() + " " + patientDeliveryAddress.getApartment() + " " + patientDeliveryAddress.getCity() + ", " + state.getName() + " " + patientDeliveryAddress.getZip());
                } else {
//                    orderDetailDTO.setShippingAddress(patientDeliveryAddress.getAddress() + " " + patientDeliveryAddress.getCity() + ", " + state.getName() + " " + patientDeliveryAddress.getZip());
                }
            } else {
//                orderDetailDTO.setShippingAddress("");
            }

            logger.info("Payment info is null against this profile id: " + patientProfile.getPatientProfileSeqNo());
//            orderDetailDTO.setCardHolderName("");
//            orderDetailDTO.setCardInfo("");

        } catch (Exception e) {
            logger.error("Exception -> getPlaceRxOrderDetailsWs", e);
        }
        return orderDetailDTO;
    }

    public Order saveRxOrder(PatientProfile patientProfile, Integer drugId, String finalPayment, String redeemPoints, String redeemPointsCost, String handLingFee, String strength, String drugName, String drugType, String qty, String drugPrice) {
        Order order = new Order();
        try {
            order.setFirstName(patientProfile.getFirstName());
            order.setLastName(patientProfile.getLastName());
            order.setPatientProfile(new PatientProfile(patientProfile.getPatientProfileSeqNo()));
            PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getDefaultPatientDeliveryAddress(patientProfile.getPatientProfileSeqNo());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                logger.info("Set shipping address info. ");
                order.setStreetAddress(patientDeliveryAddress.getAddress());
                order.setCity(patientDeliveryAddress.getCity());
                order.setZip(patientDeliveryAddress.getZip());
                order.setAddressLine2(patientDeliveryAddress.getDescription());
                order.setApartment(patientDeliveryAddress.getApartment());
                if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                    order.setState(patientDeliveryAddress.getState().getName());
                }
            }

            order.setRedeemPoints(redeemPoints);
            if (finalPayment != null && !finalPayment.equalsIgnoreCase("undefined")) {
                order.setPayment(Double.parseDouble(finalPayment));
            }
            if (redeemPointsCost != null && !redeemPointsCost.equalsIgnoreCase("undefined")) {
                order.setRedeemPointsCost(Double.parseDouble(redeemPointsCost));
            }
            OrderStatus orderStatus = (OrderStatus) patientProfileDAO.getObjectById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
            if (orderStatus != null && orderStatus.getId() != null) {
                order.setOrderStatus(orderStatus);
            }
            if (handLingFee != null && !handLingFee.equalsIgnoreCase("undefined")) {
                order.setHandLingFee(Double.parseDouble(handLingFee));
            }
            order.setStrength(strength);
            order.setDrugName(drugName);
            order.setDrugType(drugType);
            order.setQty(qty);
            if (drugPrice != null) {
                logger.info("Drug Price is: " + drugPrice);
                order.setDrugPrice(Double.parseDouble(drugPrice));
            }
            order.setCreatedOn(new Date());
            order.setUpdatedOn(new Date());
            order.setOrderHistory(null);
            order.setOrderType(Constants.ORDERTYPE_RXORDER);
            patientProfileDAO.save(order);
            order.setAwardedPoints(saveRewardOrderHistory(redeemPoints, patientProfile, order));
        } catch (Exception e) {
            logger.error("Exception -> saveRxOrder", e);
        }
        return order;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public Order saveRxOrder(PatientProfile patientProfile, Integer drugId, String finalPayment, String redeemPoints,
            String redeemPointsCost, String handLingFee, String strength, String drugName, String drugType, String qty,
            String drugPrice, String addressId) {
        Order order = new Order();
        try {
            order.setFirstName(patientProfile.getFirstName());
            order.setLastName(patientProfile.getLastName());
            order.setPatientProfile(new PatientProfile(patientProfile.getPatientProfileSeqNo()));
            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(new PatientDeliveryAddress(), AppUtil.getSafeInt(addressId, 0));//getDefaultPatientDeliveryAddress(patientProfile.getId());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                logger.info("Set shipping address info. ");
                order.setStreetAddress(patientDeliveryAddress.getAddress());
                order.setCity(patientDeliveryAddress.getCity());
                order.setZip(patientDeliveryAddress.getZip());
                order.setAddressLine2(patientDeliveryAddress.getDescription());
                order.setApartment(patientDeliveryAddress.getApartment());
                if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                    order.setState(patientDeliveryAddress.getState().getName());
                }
            }

            order.setRedeemPoints(redeemPoints);
            if (finalPayment != null && !finalPayment.equalsIgnoreCase("undefined")) {
                order.setPayment(Double.parseDouble(finalPayment));
            }
            if (redeemPointsCost != null && !redeemPointsCost.equalsIgnoreCase("undefined")) {
                order.setRedeemPointsCost(Double.parseDouble(redeemPointsCost));
            }
            OrderStatus orderStatus = (OrderStatus) patientProfileDAO.getObjectById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
            if (orderStatus != null && orderStatus.getId() != null) {
                order.setOrderStatus(orderStatus);
            }
            if (handLingFee != null && !handLingFee.equalsIgnoreCase("undefined")) {
                order.setHandLingFee(Double.parseDouble(handLingFee));
            }
            order.setStrength(strength);
            order.setDrugName(drugName);
            order.setDrugType(drugType);
            order.setQty(qty);
            if (drugPrice != null) {
                logger.info("Drug Price is: " + drugPrice);
                order.setDrugPrice(Double.parseDouble(drugPrice));
            }
            order.setCreatedOn(new Date());
            order.setUpdatedOn(new Date());
            order.setOrderHistory(null);
            order.setOrderType(Constants.ORDERTYPE_RXORDER);
            patientProfileDAO.save(order);
            //saveRewardOrderHistory(redeemPoints, patientProfile);
            order.setAwardedPoints(saveRewardOrderHistory(redeemPoints, patientProfile, order));
        } catch (Exception e) {
            logger.error("Exception -> saveRxOrder", e);
        }
        return order;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public Order saveRxOrder(PatientProfile patientProfile, Integer drugId, String finalPayment, String redeemPoints,
            String redeemPointsCost, String handLingFee, String strength, String drugName, String drugType, String qty,
            String drugPrice, String additionalMargin, String addressId, String requestId) {
        Order order = new Order();
        try {
            TransferRequest transferRequest = new TransferRequest();
            transferRequest = (TransferRequest) this.patientProfileDAO.getObjectById(new TransferRequest(), AppUtil.getSafeInt(requestId, 0));
            if (transferRequest != null) {
                String patientName = AppUtil.getSafeStr(transferRequest.getPatientName(), "");
                if (patientName.length() > 50) {
                    patientName = patientName.substring(0, 49);
                }
                order.setFirstName(patientName);
                order.setLastName("");
            } else {
                order.setFirstName(patientProfile.getFirstName());
                order.setLastName(patientProfile.getLastName());
            }
            order.setPatientProfile(new PatientProfile(patientProfile.getPatientProfileSeqNo()));
            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(new PatientDeliveryAddress(), AppUtil.getSafeInt(addressId, 0));//getDefaultPatientDeliveryAddress(patientProfile.getId());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                logger.info("Set shipping address info. ");
                order.setStreetAddress(patientDeliveryAddress.getAddress());
                order.setCity(patientDeliveryAddress.getCity());
                order.setZip(patientDeliveryAddress.getZip());
                order.setAddressLine2(patientDeliveryAddress.getDescription());
                order.setApartment(patientDeliveryAddress.getApartment());
                if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                    order.setState(patientDeliveryAddress.getState().getName());
                }
            }

            order.setRedeemPoints(redeemPoints);
            if (finalPayment != null && !finalPayment.equalsIgnoreCase("undefined")) {
                order.setPayment(Double.parseDouble(finalPayment));
            }
            if (redeemPointsCost != null && !redeemPointsCost.equalsIgnoreCase("undefined")) {
                order.setRedeemPointsCost(Double.parseDouble(redeemPointsCost));
            }
            OrderStatus orderStatus = (OrderStatus) patientProfileDAO.getObjectById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
            if (orderStatus != null && orderStatus.getId() != null) {
                order.setOrderStatus(orderStatus);
            }
            if (handLingFee != null && !handLingFee.equalsIgnoreCase("undefined")) {
                order.setHandLingFee(Double.parseDouble(handLingFee));
            }
            order.setStrength(strength);
            order.setDrugName(drugName);
            order.setDrugType(drugType);
            order.setQty(qty);
            if (drugPrice != null) {
                logger.info("Drug Price is: " + drugPrice);
                order.setDrugPrice(Double.parseDouble(drugPrice));
            }
            order.setAdditionalMargin(AppUtil.getSafeDouble(additionalMargin, 0d));
            order.setCreatedOn(new Date());
            order.setUpdatedOn(new Date());
            order.setOrderHistory(null);
            order.setOrderType(Constants.ORDERTYPE_RXORDER);
            patientProfileDAO.save(order);
            //saveRewardOrderHistory(redeemPoints, patientProfile);
            order.setAwardedPoints(saveRewardOrderHistory(redeemPoints, patientProfile, order));
        } catch (Exception e) {
            logger.error("Exception -> saveRxOrder", e);
        }
        return order;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public Order saveRxOrder(PatientProfile patientProfile, Long drugId, String finalPayment, String redeemPoints,
            String redeemPointsCost, String handLingFee, String strength, String drugName, String drugType, String qty,
            String drugPrice, String additionalMargin, String addressId, String requestId,
            String video, String img, String comments, Boolean isRefill, Boolean isPrescriptionHardCopy, Boolean addCopyCard) {
        Order order = new Order();
        try {

            TransferRequest transferRequest = new TransferRequest();
            transferRequest = (TransferRequest) this.patientProfileDAO.getObjectById(new TransferRequest(), AppUtil.getSafeInt(requestId, 0));
            if (transferRequest != null) {
                String patientName = AppUtil.getSafeStr(transferRequest.getPatientName(), "");
                if (patientName.length() > 50) {
                    patientName = patientName.substring(0, 49);
                }
                order.setFirstName(patientName);
                order.setLastName("");
            } else {
                order.setFirstName(patientProfile.getFirstName());
                order.setLastName(patientProfile.getLastName());
            }
            //order.setDrug(new Drug(drugId));
            DrugDetail detail = new DrugDetail();
            detail.setDrugNDC(drugId);
            order.setDrugDetail(detail);
            order.setPatientProfile(new PatientProfile(patientProfile.getPatientProfileSeqNo()));
            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(new PatientDeliveryAddress(), AppUtil.getSafeInt(addressId, 0));//getDefaultPatientDeliveryAddress(patientProfile.getId());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                logger.info("Set shipping address info. ");
                order.setStreetAddress(patientDeliveryAddress.getAddress());
                order.setCity(patientDeliveryAddress.getCity());
                order.setZip(patientDeliveryAddress.getZip());
                order.setAddressLine2(patientDeliveryAddress.getDescription());
                order.setApartment(patientDeliveryAddress.getApartment());
                if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                    order.setState(patientDeliveryAddress.getState().getName());
                }
            }

            order.setRedeemPoints(redeemPoints);
            if (finalPayment != null && !finalPayment.equalsIgnoreCase("undefined")) {
                order.setPayment(Double.parseDouble(finalPayment));
            }
            if (redeemPointsCost != null && !redeemPointsCost.equalsIgnoreCase("undefined")) {
                order.setRedeemPointsCost(Double.parseDouble(redeemPointsCost));
            }
            OrderStatus orderStatus = (OrderStatus) patientProfileDAO.getObjectById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
            if (orderStatus != null && orderStatus.getId() != null) {
                order.setOrderStatus(orderStatus);
            }
            if (handLingFee != null && !handLingFee.equalsIgnoreCase("undefined")) {
                order.setHandLingFee(Double.parseDouble(handLingFee));
            }

            DrugDetail tmp = (DrugDetail) this.patientProfileDAO.findRecordById(new DrugDetail(), drugId);
            if (tmp != null) {
                order.setStrength(tmp.getStrength());
                order.setDrugName(tmp.getDrugBasic().getBrandName());
                order.setDrugType(tmp.getDrugForm().getFormDescr());
//                order.setStrength(strength);
//                order.setDrugName(drugName);
//                order.setDrugType(drugType);
            }
            order.setQty(qty);
            if (drugPrice != null) {
                logger.info("Drug Price is: " + drugPrice);
                order.setDrugPrice(Double.parseDouble(drugPrice));
            }
            order.setAdditionalMargin(AppUtil.getSafeDouble(additionalMargin, 0d));
            order.setCreatedOn(new Date());
            order.setUpdatedOn(new Date());
            order.setOrderHistory(null);
            if (AppUtil.getSafeStr(order.getOrderType(), "").equalsIgnoreCase("Transfer")) {
                order.setOrderType(Constants.ORDERTYPE_RXORDER);
            }
            order.setVideo(video);
            order.setImagePath(img);
            order.setPatientComments(comments);
            order.setRxAcqCost(AppUtil.getSafeDouble(drugPrice, 0d));
            order.setIsPrescriptionHardCopy(isPrescriptionHardCopy);
            order.setAddCopyCard(addCopyCard);
            patientProfileDAO.save(order);
            //saveRewardOrderHistory(redeemPoints, patientProfile);
            order.setAwardedPoints(saveRewardOrderHistory(redeemPoints, patientProfile, order));
        } catch (Exception e) {
            logger.error("Exception -> saveRxOrder", e);
        }
        return order;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public Order saveRxOrder(PatientProfile patientProfile, Long drugId, String finalPayment, String priceWithMargin, String redeemPoints,
            String redeemPointsCost, String handLingFee, String strength, String drugName, String drugType, String qty,
            String drugPrice, String additionalMargin, String addressId, String requestId,
            String video, String img, String comments, Boolean isRefill, Boolean isPrescriptionHardCopy, Boolean addCopyCard,
            String paymentType, DeliveryPreferences deliveryPreference, String cardId, String copayIds, String miles, String rxAcqCost) {
        Order order = new Order();
        try {

            TransferRequest transferRequest = new TransferRequest();
            transferRequest = (TransferRequest) this.patientProfileDAO.getObjectById(new TransferRequest(), AppUtil.getSafeInt(requestId, 0));
//            if (transferRequest != null) {
//                String patientName = AppUtil.getSafeStr(transferRequest.getPatientName(), "");
//                if (patientName.length() > 50) {
//                    patientName = patientName.substring(0, 49);
//                }
//                order.setFirstName(patientName);
//                order.setLastName("");
//            } else {
//                order.setFirstName(patientProfile.getFirstName());
//                order.setLastName(patientProfile.getLastName());
//            }
            //order.setDrug(new Drug(drugId));
            DrugDetail detail = new DrugDetail();
            //detail.setDrugNDC(drugId);
            detail.setDrugDetailId(drugId);
            order.setDrugDetail(detail);
            order.setPatientProfile(new PatientProfile(patientProfile.getPatientProfileSeqNo()));
            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(new PatientDeliveryAddress(), AppUtil.getSafeInt(addressId, 0));//getDefaultPatientDeliveryAddress(patientProfile.getId());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                logger.info("Set shipping address info. ");
                order.setStreetAddress(patientDeliveryAddress.getAddress());
                order.setCity(patientDeliveryAddress.getCity());
                order.setZip(patientDeliveryAddress.getZip());
                order.setAddressLine2(patientDeliveryAddress.getDescription());
                order.setApartment(patientDeliveryAddress.getApartment());
                if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                    order.setState(patientDeliveryAddress.getState().getName());
                }
            }

            order.setRedeemPoints(redeemPoints);
            if (finalPayment != null && !finalPayment.equalsIgnoreCase("undefined")) {
                //order.setPayment(Double.parseDouble(finalPayment));
                order.setFinalPayment(Double.parseDouble(finalPayment));
                order.setEstimatedPrice(AppUtil.getSafeDouble(finalPayment, 0d));
            }
            if (drugPrice != null) {
                order.setPriceIncludingMargins(AppUtil.getSafeDouble(drugPrice, 0d));
//                order.setEstimatedPrice(AppUtil.getSafeDouble(drugPrice, 0d));
            }
            if (redeemPointsCost != null && !redeemPointsCost.equalsIgnoreCase("undefined")) {
                order.setRedeemPointsCost(Double.parseDouble(redeemPointsCost));
            }
            OrderStatus orderStatus = (OrderStatus) patientProfileDAO.getObjectById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
            order.setNextRefillFlag("0");
            if (orderStatus != null && orderStatus.getId() != null) {
                order.setOrderStatus(orderStatus);
            }
            if (handLingFee != null && !handLingFee.equalsIgnoreCase("undefined")) {
                order.setHandLingFee(Double.parseDouble(handLingFee));
            }

            DrugDetail tmp = (DrugDetail) this.patientProfileDAO.findRecordById(new DrugDetail(), drugId);
            if (tmp != null) {
                order.setStrength(tmp.getStrength());
                String brandName = tmp.getDrugBasic() != null ? tmp.getDrugBasic().getBrandName() : "";
                String genericName = tmp.getDrugBasic() != null && tmp.getDrugBasic().getDrugGeneric() != null
                        ? tmp.getDrugBasic().getDrugGeneric().getGenericName() : "";
                order.setDrugName(brandName + "{" + genericName + "}");
                order.setDrugType(tmp.getDrugForm().getFormDescr());
                order.setRxExpiry(tmp.getMonthRxExpiration());
//                order.setStrength(strength);
//                order.setDrugName(drugName);
//                order.setDrugType(drugType);
            }
            order.setQty(qty);
            if (drugPrice != null) {
                logger.info("Drug Price is: " + drugPrice);
                order.setDrugPrice(Double.parseDouble(drugPrice));
            }
            order.setAdditionalMargin(AppUtil.getSafeDouble(additionalMargin, 0d));
            order.setCreatedOn(new Date());
            order.setUpdatedOn(new Date());
            order.setOrderHistory(null);
            if (AppUtil.getSafeStr(order.getOrderType(), "").equalsIgnoreCase("Transfer")) {
                order.setOrderType(Constants.ORDERTYPE_RXORDER);
            }
            order.setVideo(video);
            order.setImagePath(img);
            order.setPatientComments(comments);
            //To do with ios team
            if (CommonUtil.isNotEmpty(rxAcqCost)) {
                order.setRxAcqCost(AppUtil.getSafeDouble(rxAcqCost, 0d));
            } else {
                order.setRxAcqCost(AppUtil.getSafeDouble(drugPrice, 0d));
            }

            order.setIsPrescriptionHardCopy(isPrescriptionHardCopy);
            order.setAddCopyCard(addCopyCard);
            order.setPaymentType(paymentType);
            order.setFinalPaymentMode(paymentType);
            if (deliveryPreference != null) {
                order.setDeliveryPreference(deliveryPreference);
            }
            order.setPaymentId(AppUtil.getSafeInt(cardId, 0));
            order.setMiles(miles);
            patientProfileDAO.save(order);
            //saveRewardOrderHistory(redeemPoints, patientProfile);
            order.setAwardedPoints(saveRewardOrderHistory(redeemPoints, patientProfile, order));
            logger.info("copayIds:: " + copayIds);

//            if (addCopyCard && coPayCardDetailsList!=null) {
//                CoPayCardDetails coPayCardDetails = new CoPayCardDetails();
//                for (CoPayCardDetails CoPayCardDetails : coPayCardDetailsList) {
////                    coPayCardDetails = (CoPayCardDetails) patientProfileDAO.findRecordById(new CoPayCardDetails(), coPayCardId);
//                    coPayCardDetails.setOrder(order);
//                    patientProfileDAO.update(coPayCardDetails);
//                }
//            }
//          Hibernate.initialize(order.getDrugDetail());
//          Hibernate.initialize(order.getDrugDetail().getDrugBasic());
//          Hibernate.initialize(order.getDrugDetail().getDrugBasic().getDrugGeneric());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> saveRxOrder", e);
        }

        return order;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public Order saveRxOrder(PatientProfile patientProfile, Integer drugId, String finalPayment, String redeemPoints,
            String redeemPointsCost, String handLingFee, String strength, String drugName, String drugType, String qty,
            String drugPrice, String additionalMargin, String addressId, String requestId,
            String video) {
        Order order = new Order();
        try {
            TransferRequest transferRequest = new TransferRequest();
            transferRequest = (TransferRequest) this.patientProfileDAO.getObjectById(new TransferRequest(), AppUtil.getSafeInt(requestId, 0));
            if (transferRequest != null) {
                String patientName = AppUtil.getSafeStr(transferRequest.getPatientName(), "");
                if (patientName.length() > 50) {
                    patientName = patientName.substring(0, 49);
                }
                order.setFirstName(patientName);
                order.setLastName("");
            } else {
                order.setFirstName(patientProfile.getFirstName());
                order.setLastName(patientProfile.getLastName());
            }
            order.setPatientProfile(new PatientProfile(patientProfile.getPatientProfileSeqNo()));
            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(new PatientDeliveryAddress(), AppUtil.getSafeInt(addressId, 0));//getDefaultPatientDeliveryAddress(patientProfile.getId());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                logger.info("Set shipping address info. ");
                order.setStreetAddress(patientDeliveryAddress.getAddress());
                order.setCity(patientDeliveryAddress.getCity());
                order.setZip(patientDeliveryAddress.getZip());
                order.setAddressLine2(patientDeliveryAddress.getDescription());
                order.setApartment(patientDeliveryAddress.getApartment());
                if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                    order.setState(patientDeliveryAddress.getState().getName());
                }
            }

            order.setRedeemPoints(redeemPoints);
            if (finalPayment != null && !finalPayment.equalsIgnoreCase("undefined")) {
                order.setPayment(Double.parseDouble(finalPayment));
            }
            if (redeemPointsCost != null && !redeemPointsCost.equalsIgnoreCase("undefined")) {
                order.setRedeemPointsCost(Double.parseDouble(redeemPointsCost));
            }
            OrderStatus orderStatus = (OrderStatus) patientProfileDAO.getObjectById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
            if (orderStatus != null && orderStatus.getId() != null) {
                order.setOrderStatus(orderStatus);
            }
            if (handLingFee != null && !handLingFee.equalsIgnoreCase("undefined")) {
                order.setHandLingFee(Double.parseDouble(handLingFee));
            }
            order.setStrength(strength);
            order.setDrugName(drugName);
            order.setDrugType(drugType);
            order.setQty(qty);
            if (drugPrice != null) {
                logger.info("Drug Price is: " + drugPrice);
                order.setDrugPrice(Double.parseDouble(drugPrice));
            }
            order.setAdditionalMargin(AppUtil.getSafeDouble(additionalMargin, 0d));
            order.setCreatedOn(new Date());
            order.setUpdatedOn(new Date());
            order.setOrderHistory(null);
            order.setOrderType(Constants.ORDERTYPE_RXORDER);
            order.setVideo(video);
            patientProfileDAO.save(order);
            //saveRewardOrderHistory(redeemPoints, patientProfile);
            order.setAwardedPoints(saveRewardOrderHistory(redeemPoints, patientProfile, order));
        } catch (Exception e) {
            logger.error("Exception -> saveRxOrder", e);
        }
        return order;
    }
    //////////////////////////////////////////////////////////////////////////////////////

    public OrderHistory saveOrderHistory(OrderHistory history) throws Exception {
        history.setCreatedOn(new Date());
        history.setUpdatedOn(new Date());
        history.setUpdatedBy(0);
        history.setCreatedBy(0);
        patientProfileDAO.save(history);
        return history;
    }

    private BigDecimal saveRewardOrderHistory(String redeemPoints, PatientProfile patientProfile, Order order) throws NumberFormatException, Exception {
        logger.info("Redeem Points are: " + redeemPoints);
        RewardHistory rewardHistory = new RewardHistory();
//        rewardHistory.setDescription("Place Order");
        rewardHistory.setPatientId(patientProfile.getPatientProfileSeqNo());
//        rewardHistory.setType(Constants.MINUS);
        if (CommonUtil.isNotEmpty(redeemPoints)) {
//            rewardHistory.setPoint(AppUtil.getSafeFloat(redeemPoints, 0f).intValue());
        }
        rewardHistory.setCreatedDate(new Date());
        patientProfileDAO.save(rewardHistory);
        RewardPoints rewardPoints = this.getRewardPoints(Constants.REWARD_ORDER_PLACE_POINT);
        if (rewardPoints != null && rewardPoints.getId() != null) {
            logger.info("Redeem Points are: " + rewardPoints.getPoint());
            rewardHistory = new RewardHistory();
//            rewardHistory.setDescription("Place Order");
            rewardHistory.setPatientId(patientProfile.getPatientProfileSeqNo());
//            rewardHistory.setType(Constants.PLUS);
            if (rewardPoints.getPoint() != null) {
//                rewardHistory.setPoint(rewardPoints.getPoint().intValueExact());
            }
            rewardHistory.setCreatedDate(new Date());
            rewardHistory.setOrder(order);
            patientProfileDAO.save(rewardHistory);
        }
        return rewardPoints != null ? rewardPoints.getPoint() : BigDecimal.ZERO;
    }

    private BigDecimal saveRewardOrderHistory(String redeemPoints, PatientProfile patientProfile, Order order, String rewardKey, int rewardId) throws NumberFormatException, Exception {
        logger.info("Redeem Points are: " + redeemPoints);
        RewardHistory rewardHistory = new RewardHistory();
//        rewardHistory.setDescription(rewardKey);
        rewardHistory.setPatientId(patientProfile.getPatientProfileSeqNo());
//        rewardHistory.setType(Constants.MINUS);
        if (CommonUtil.isNotEmpty(redeemPoints)) {
//            rewardHistory.setPoint(new Integer(redeemPoints));
        }
        rewardHistory.setCreatedDate(new Date());
        patientProfileDAO.save(rewardHistory);
        RewardPoints rewardPoints = this.getRewardPoints(rewardId);
        if (rewardPoints != null && rewardPoints.getId() != null) {
            logger.info("Redeem Points are: " + rewardPoints.getPoint());
            rewardHistory = new RewardHistory();
//            rewardHistory.setDescription(rewardKey);
            rewardHistory.setPatientId(patientProfile.getPatientProfileSeqNo());
//            rewardHistory.setType(Constants.PLUS);
            if (rewardPoints.getPoint() != null) {
//                rewardHistory.setPoint(rewardPoints.getPoint().intValueExact());
            }
            rewardHistory.setCreatedDate(new Date());
            rewardHistory.setOrder(order);
            patientProfileDAO.save(rewardHistory);
        }
        return rewardPoints != null ? rewardPoints.getPoint() : BigDecimal.ZERO;
    }

    public OrderDetailDTO getRedeemPointsWs(String redeemPoints) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        try {
            if (CommonUtil.isNotEmpty(redeemPoints)) {
                logger.info("Redeem Points Points: " + redeemPoints);
                FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getRecordByType(new FeeSettings(), Constants.PERPOINTVALUE);
                if (feeSettings != null && feeSettings.getId() != null) {
                    logger.info("FeeSettings value: " + feeSettings.getFee().doubleValue());
                    DecimalFormat df = new DecimalFormat("#.##");
                    Double totalRedeemPoints = feeSettings.getFee().doubleValue() * Double.parseDouble(redeemPoints);
                    logger.info("Before format Total Redeem Points is " + totalRedeemPoints);
                    totalRedeemPoints = Double.valueOf(df.format(totalRedeemPoints));
                    logger.info("After Format Total Redeem Points is " + totalRedeemPoints);
//                    orderDetailDTO.setRedeemPointsCost(totalRedeemPoints);
//                    orderDetailDTO.setRedeemPoints(redeemPoints);
                }
            }
        } catch (Exception e) {
            logger.error("Exception -> getHandlingFee", e);
        }
        return orderDetailDTO;
    }

    public OrderDetailDTO getQuickStatsWs(Integer patientProfileSeqNo) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        Date date = new Date();
        try {
            Long totalDeliveryAddress = patientProfileDAO.getTotalDeliveryAddressByProfileId(patientProfileSeqNo);
            if (totalDeliveryAddress != null) {
                orderDetailDTO.setTotalDeliveryAddress(totalDeliveryAddress);
            } else {
                orderDetailDTO.setTotalDeliveryAddress(0L);
            }
            orderDetailDTO.setRefillAbleCount(patientProfileDAO.getRefillableOrdersListByProfileId(patientProfileSeqNo).size());
            orderDetailDTO.setInsuranceCardsCount(patientProfileDAO.getInsuranceCardsCount(patientProfileSeqNo));
            orderDetailDTO.setActiveRxCount(patientProfileDAO.getActiveOrderCount(patientProfileSeqNo));
//            orderDetailDTO.setTotalWaitingPtResponseCount(patientProfileDAO.TotalWatingPtResponseCount(patientProfileSeqNo, date).intValue());

//            orderDetailDTO.setCopayCardsCount(patientProfileDAO.getCopayCardsCount(patientId));
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("patientProfile.id", patientProfileSeqNo, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            obj = new BusinessObject("orderStatus.id", Constants.ORDER_STATUS.FILLED_ID, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
//            orderDetailDTO.setTotalReadyToDeliverRxOrders(patientProfileDAO.getTotalRecordsByNestedProperty(new Order(), lstObj, "Count(*)"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getQuickStatsWs", e);
        }
        return orderDetailDTO;
    }

    public Set<OrderDetailDTO> getFilledRxHistory(Integer patientProfileSeqNo) {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        try {
            List<Order> dbOrders = patientProfileDAO.getActiveOrdersListByProfileId(patientProfileSeqNo);
            list = setOrderList2(dbOrders, Constants.FILLED_RX_HISTORY, "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getFilledRxHistory", e);
        }
        return list;
    }

    ///////////////////////////////////////////////////////////////////
    public Set<OrderDetailDTO> getRefillRx(Integer profileId, String orderId) {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        try {
            List<Order> dbOrders = patientProfileDAO.getRefillableOrdersListByProfileId(profileId);
            list = setOrderList2(dbOrders, Constants.FILLED_RX_HISTORY, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getFilledRxHistory", e);
        }
        return list;
    }
    //////////////////////////////////////////////////////////////////

    public Set<OrderDetailDTO> viewOrderReceiptWs(Integer patientId, String orderId) {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        try {
            List<Order> dbOrders = patientProfileDAO.viewOrderReceiptList(patientId, orderId);
            list = this.populateOrderList(dbOrders, null);
            logger.info("New Order list size: " + list.size());
        } catch (Exception e) {
            logger.error("Exception -> viewOrderReceiptWs", e);
        }
        return list;
    }

    private Set<OrderDetailDTO> populateOrderList(List<Order> dbOrders, String type) throws Exception {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        for (Order order : dbOrders) {
            OrderDetailDTO newOrder = new OrderDetailDTO();
            newOrder.setId(order.getId());
//            newOrder.setFirstName("");//order.getFirstName());//Commented on Adil/Rafay(IOS team) Request to fix the problems during confirmpaymentws & updaterxorderws
//            newOrder.setLastName("");//order.getLastName());//Commented on Adil/Rafay(IOS team) Request to fix the problems during confirmpaymentws & updaterxorderws
            CommonUtil.populateDecryptedOrderData(newOrder, order);
            newOrder.setPatientName(AppUtil.getSafeStr(
                    AppUtil.getSafeStr(order.getPatientProfile().getFirstName(), "") + " "
                    + AppUtil.getSafeStr(order.getPatientProfile().getLastName(), ""), ""));
            if (order.getCreatedOn() != null) {
                newOrder.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
                newOrder.setOrderTime(DateUtil.dateToString(order.getCreatedOn(), "hh:mm:ss"));
            } else if (order.getCreatedAt() != null) {
                newOrder.setOrderDate(DateUtil.dateToString(order.getCreatedAt(), "MM/dd/yyyy"));
                newOrder.setOrderTime(DateUtil.dateToString(order.getCreatedAt(), "hh:mm:ss"));
            }
            if (order.getRxProcessedAt() != null) {
                newOrder.setFilledDateStr(DateUtil.dateToString(order.getRxProcessedAt(), "MM/dd/yyyy"));
//                newOrder.setFilledTime(DateUtil.dateToString(order.getRxProcessedAt(), "hh:mm:ss"));
//                newOrder.setRxProcessedDate(newOrder.getFilledDateStr().concat(" ").concat(newOrder.getFilledTime()));
            }
//            if (order.getOrderStatus() != null && order.getOrderStatus().getId() != null) {
//                newOrder.setOrderStatusName(order.getOrderStatus().getName());
//                if (order.getOrderStatus().getId() == 8) {
//                    newOrder.setOrderType(1);
//                } else {
//                    newOrder.setOrderType(0);
//                }
//            }

//            PatientProfileMembers dependent = order.getPatientProfileMembers();
//            if (dependent == null) {
//                newOrder.setCareGiver(0);
//            } else {
//                Boolean isAdult = dependent.getIsAdult();
//
//                if (isAdult == null || isAdult == false) {
//                    newOrder.setCareGiver(0);
//                } else {
//                    newOrder.setCareGiver(1);
//                }
//            }
            newOrder.setOrderType(AppUtil.getSafeStr(order.getOrderType(), "").equalsIgnoreCase("Transfer") ? 1 : 0);
            newOrder.setStrength(order.getStrength());
            newOrder.setDrugName(order.getDrugName());
            if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.TABLET)) {
                newOrder.setDrugType("TAB");
            } else if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.CAPSULE)) {
                newOrder.setDrugType("CAP");
            } else {
                newOrder.setDrugType(AppUtil.getSafeStr(order.getDrugType(), "-"));
            }
            newOrder.setQty(order.getQty());
            newOrder.setDrugPrice(order.getDrugPrice());
//            newOrder.setRedeemPoints(order.getRedeemPoints());
//            newOrder.setRedeemPointsCost(order.getRedeemPointsCost() != null ? order.getRedeemPointsCost() : 0d);
//            newOrder.setHandLingFee(order.getHandLingFee() != null ? order.getHandLingFee() : 0d);
            double payment = order.getPayment() != null ? order.getPayment() : 0d;
//            newOrder.setPaymentIncludingRedmeenCost(AppUtil.roundOffNumberToCurrencyFormat(payment + newOrder.getRedeemPointsCost(), "en", "US"));
//            newOrder.setPaymentIncludingShipping(AppUtil.roundOffNumberToCurrencyFormat(payment + newOrder.getHandLingFee(), "en", "US"));
            newOrder.setCardType(order.getCardType());
//            newOrder.setRxNumber(order.getSystemGeneratedRxNumber());
            String cardNumber = order.getCardNumber();
            logger.info("Card Number is: " + cardNumber);
            if (CommonUtil.isNotEmpty(cardNumber) && cardNumber.length() > 4) {
                logger.info("card number is: " + cardNumber.substring(order.getCardNumber().length() - 4));
                newOrder.setCardNumber(cardNumber.substring(order.getCardNumber().length() - 4));
            } else {
                logger.info("Card Number length is less than 4: " + cardNumber);
                newOrder.setCardNumber(cardNumber);
            }
            if (order.getPayment() != null) {
                DecimalFormat decimalFormat = new DecimalFormat("#.###");
                String formatPayment = decimalFormat.format(order.getPayment());
                logger.info("formatPayment" + formatPayment);
                newOrder.setPayment(Double.parseDouble(formatPayment));
            }

            //ToDo
//            if (order.getApartment() != null && !"".equals(order.getApartment())) {
//                logger.info("Appartmnet is: " + order.getApartment());
//                newOrder.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                        + AppUtil.getSafeStr(order.getApartment(), "") + " " + AppUtil.getSafeStr(order.getCity(), "")
//                        + ", " + AppUtil.getSafeStr(order.getState(), "") + " "
//                        + AppUtil.getSafeStr(order.getZip(), ""));
//            } else {
//                newOrder.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                        + AppUtil.getSafeStr(order.getCity(), "") + ", " + AppUtil.getSafeStr(order.getState(), "")
//                        + " " + AppUtil.getSafeStr(order.getZip(), ""));
//            }
            //PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getDefaultPatientDeliveryAddress(order.getPatientProfile().getId());
            //Todo
//            if (order.getDeliveryPreference() != null
//                    && order.getDeliveryPreference().getId() != null) {
//                newOrder.setDeliveryPreferencesName(order.getDeliveryPreference().getName());
//                String pref = newOrder.getDeliveryPreferencesName();
//                String name = AppUtil.getSafeStr(pref, "").toLowerCase();
//                if (name.contains("2nd day")) {
//                    newOrder.setHandLingFee(0.0d);
//                    newOrder.setIncludedStr("Included");
//                } else {
//                    newOrder.setHandLingFee(order.getHandLingFee());
//                    newOrder.setIncludedStr("");
//                }
//            }
            //this.populateDrugDetail(order, newOrder);
            this.populateNewDrugDetail(order, newOrder);
//            newOrder.setComments(AppUtil.getSafeStr(order.getPatientComments(), ""));
//            newOrder.setPaymentType(AppUtil.getSafeStr(order.getPaymentType(), ""));
            String source = "";
            if (AppUtil.getSafeStr(order.getVideo(), "").length() > 0) {
                source = "Video";
            } else if (AppUtil.getSafeStr(order.getImagePath(), "").length() > 0) {
                source = "Image";
            }
//            newOrder.setSource(source);
//            newOrder.setIsCopayCard(order.getAddCopyCard());
            //newOrder.setOrderStatusName(order.getOrderStatus() != null ? AppUtil.getSafeStr(order.getOrderStatus().getName(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING) ? "Pharmacy Review Pending" : AppUtil.getSafeStr(order.getOrderStatus().getName(), "") : "");
            newOrder.setOrderStatusName(order.getOrderStatus() != null ? AppUtil.getSafeStr(order.getOrderStatus().getName(), "") : "");
            getOrderRewardDetail(order.getPatientProfile().getPatientProfileSeqNo(), newOrder);
            if (order.getRefillsAllowed() != null && order.getRefillsRemaining() != null) {
                String status = order.getOrderStatus() != null ? AppUtil.getSafeStr(order.getOrderStatus().getName(), "") : "";
                Date nextRefillDate = order.getNextRefillDate();
                Date today = new Date();
                int refillDone = order.getRefillDone() != null ? order.getRefillDone() : 0;
                newOrder.setRefill(order.getRefillsRemaining() != null && order.getRefillsRemaining() > 0 //&& !nextRefillDate.before(today)
                        && refillDone == 0
                        && (status.equalsIgnoreCase("Shipped") || status.equalsIgnoreCase("DELIVERY")) ? 1 : 0);
            } else {
                newOrder.setRefill(0);
            }
            newOrder.setDaysSupply(order.getDaysSupply() != null ? order.getDaysSupply() : 0);

            if (order.getRefillsRemaining() == null || order.getRefillsRemaining() == 0) {
                newOrder.setDaysToRefill("No Refills remaining.");
            } else if ((AppUtil.getSafeStr(newOrder.getOrderStatusName(), "").equalsIgnoreCase("Pending")
                    || AppUtil.getSafeStr(newOrder.getOrderStatusName(), "").equalsIgnoreCase("Processing"))
                    && AppUtil.getSafeStr(order.getOrderType(), "").equalsIgnoreCase("Refill")) {
                newOrder.setDaysToRefill("Pending Pharmacy Review");
            } else if (AppUtil.getSafeStr(newOrder.getOrderStatusName(), "").equalsIgnoreCase("DELIVERY")
                    || AppUtil.getSafeStr(newOrder.getOrderStatusName(), "").equalsIgnoreCase("Shipped")) {
                if (order.getNextRefillDate() != null) {
                    if (order.getRefillDone() != null && order.getRefillDone() == 1) {
                        newOrder.setDaysToRefill("Already filled");
                    } else {
                        long daysCount = DateUtil.dateDiffInDays(new Date(), order.getNextRefillDate());
                        newOrder.setRefillRemainingDaysCount(daysCount);
                        if (daysCount == 0) {
                            newOrder.setDaysToRefill("NEXT REFILL SAME DAY");
                        } else if (daysCount > 0) {
                            String dayStr = daysCount > 1 ? "days" : "day";
                            newOrder.setDaysToRefill("NEXT REFILL in " + daysCount + " " + dayStr);
                        } else {
                            String dayStr = daysCount == -1 ? "day" : "days";
                            newOrder.setDaysToRefill("REFILL OVER DUE " + daysCount + " " + dayStr);
                        }

                    }
                } else {
                    newOrder.setDaysToRefill("Not Refillable Yet");
                }
            } else {
                newOrder.setDaysToRefill("Not Refillable Yet");
            }

            if (AppUtil.getSafeStr(order.getOrderType(), "").equalsIgnoreCase("Refill")) {
                newOrder.setRequestType("Refill Requested");
            }
//            else {
//                String requestType = "";
//                if (order.getIsBottleAvailable() != null && order.getIsBottleAvailable()) {
//                    requestType = "X-FER LABEL SCAN";
//                    newOrder.setRequestType(requestType);
//                } else {
//                    requestType = "X-FER RX SCAN";
//                    newOrder.setRequestType(requestType);
//                }
//            }

            Integer refillRemaining = order.getRefillsRemaining() != null ? order.getRefillsRemaining() : 0;
            newOrder.setRefillsRemaining(refillRemaining + "");
            newOrder.setPrescriptionNumber(order.getId());
            if (order.getLastRefillDate() != null) {
                newOrder.setLastFilledDate(DateUtil.dateToString(order.getLastRefillDate(), "MM/dd/yyyy"));
            }
            newOrder.setRefillsRemaining((order.getRefillsRemaining() != null ? order.getRefillsRemaining() : 0) + "");

            newOrder.setPatientId(order.getPatientProfile().getPatientProfileSeqNo().toString());
            //newOrder.setDependentId(order.getPatientProfileMembers() != null ? order.getPatientProfileMembers().getId().toString() : "0");
            //Integer paymentId = order.getPaymentId();
            try {
//                PatientPaymentInfo patientPaymentInfo = this.patientProfileDAO.getPatientPaymentInfoById(paymentId,
//                        order.getPatientProfile() != null ? order.getPatientProfile().getPatientProfileSeqNo() : 0);
//                if (patientPaymentInfo != null) {
//                    newOrder.setCardType(patientPaymentInfo.getCardType());
//                    newOrder.setCardNumber(patientPaymentInfo.getCardNumber());
//                } else {
//                    newOrder.setCardType("");
//                    newOrder.setCardNumber("");
//                }
//                newOrder.setIsUpdateMfr(CommonUtil.isEditableOrder(AppUtil.getSafeStr(newOrder.getOrderStatusName(), "")));
                //newOrder.setIsBottleAvailable(order.getIsBottleAvailable() == null ? Boolean.FALSE : order.getIsBottleAvailable());
            } catch (Exception e) {
                logger.error("Exception:: setOrderList", e);
            }
//            newOrder.setPharmacyName(AppUtil.getSafeStr(order.getPharmacyName(), ""));
//            newOrder.setPharmacyPhone(AppUtil.getSafeStr(order.getPharmacyPhone(), ""));
            newOrder.setPrescriberName(AppUtil.getSafeStr(order.getPrescriberLastName(), ""));
            newOrder.setPrescriberPhone(AppUtil.getSafeStr(order.getPrescriberPhone(), ""));
//            if (order.getRxNumber() != null) {
//                newOrder.setOrigRx(AppUtil.getSafeStr(order.getRxNumber(), ""));
//            } else {
            newOrder.setOrigRx(AppUtil.getSafeStr(order.getRxNumber(), ""));
//            System.out.println("Pharmacy phone " + AppUtil.getSafeStr(newOrder.getPharmacyPhone(), ""));
            System.out.println("ORIG RX " + AppUtil.getSafeStr(newOrder.getOrigRx(), ""));
//            }
            newOrder.setRxNumber(AppUtil.getSafeStr(order.getRxNumber(), ""));

            //Todo
            //List<OrderTransferImagesDTO> orderTransferImagesDTOs = populateOrderTransferImages(order);
            //newOrder.setOrderTransferImages(orderTransferImagesDTOs);
            this.populateOrderCost(order, newOrder);
            list.add(newOrder);
        }
        return list;
    }

    private List<OrderTransferImagesDTO> populateOrderTransferImages(Order order) {
        List<OrderTransferImagesDTO> orderTransferImagesDTOs = new ArrayList<>();
        if (!CommonUtil.isNullOrEmpty(order.getOrderTransferImages())) {
            order.getOrderTransferImages().stream().map((orderTransferImages) -> {
                OrderTransferImagesDTO orderTransferImagesDTO = new OrderTransferImagesDTO();
                orderTransferImagesDTO.setDrugImg(EncryptionHandlerUtil.getDecryptedString(orderTransferImages.getDrugImg()));
                return orderTransferImagesDTO;
            }).forEach((orderTransferImagesDTO) -> {
                orderTransferImagesDTOs.add(orderTransferImagesDTO);
            });
        }
        return orderTransferImagesDTOs;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    private Set<OrderDetailDTO> setOrderList2(List<Order> dbOrders, String type, String primaryOrderId) throws Exception {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        for (Order order : dbOrders) {
            OrderDetailDTO newOrder = populateRefillRxData(order, primaryOrderId);

            list.add(newOrder);
        }
        return list;
    }

    private OrderDetailDTO populateRefillRxData(Order order, String primaryOrderId) {
        OrderDetailDTO newOrder = new OrderDetailDTO();
        try {
            List<RxRenewal> rxRenewalList = patientProfileDAO.getRxRenewalRequestByOrderIdd(order.getId());
            if (rxRenewalList != null) {
                for (RxRenewal renew : rxRenewalList) {
                    if ("HcpReq_Created".equals(renew.getViewStatus())) {
                        newOrder.setRenViewStatus(renew.getViewStatus());
                    } else {
                        newOrder.setRenViewStatus(renew.getViewStatus());
                    }
                }
            }
            newOrder.setId(order.getId());
            newOrder.setPatientName(AppUtil.getSafeStr(
                    AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(order.getPatientProfile().getFirstName()), "") + " "
                    + AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(order.getPatientProfile().getLastName()), ""), ""));
            if (order.getCreatedOn() != null) {
                newOrder.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
                newOrder.setOrderTime(DateUtil.dateToString(order.getCreatedOn(), "hh:mm:ss"));
            } else if (order.getCreatedAt() != null) {
                newOrder.setOrderDate(DateUtil.dateToString(order.getCreatedAt(), "MM/dd/yyyy"));
                newOrder.setOrderTime(DateUtil.dateToString(order.getCreatedAt(), "hh:mm:ss"));
            }
            if (order.getRxProcessedAt() != null) {
                newOrder.setFilledDateStr(DateUtil.dateToString(order.getRxProcessedAt(), "MM/dd/yyyy"));
            } else if ("Filled".equals(order.getOrderStatus().getName())) {
                newOrder.setFilledDateStr(DateUtil.dateToString(order.getUpdatedAt(), "MM/dd/yyyy"));
            } else {
                newOrder.setFilledDateStr("");
            }
            newOrder.setPatientOutOfPocket(order.getRxPatientOutOfPocket());// assistAuth
            newOrder.setOrgPatientOutOfPocket(order.getRxOutOfPocket()); //orignal patientOut of pocket 
            newOrder.setIsPrescriberRegister(Boolean.FALSE);
            if (order.getPrescriberId() != null) {
                Practices practice = patientProfileDAO.getRegisterdPracticeNames(order.getPrescriberId());
                if (practice != null) {
                    newOrder.setIsPrescriberRegister(Boolean.TRUE);
                    newOrder.setPrescriberId(practice.getId());
                    newOrder.setPrescriberName(practice.getPracticename());

                }
            }
              Practices practices = patientProfileDAO.getPracticesById(order.getPracticeId());
                    if (practices != null) {
                        newOrder.setPracticeLogo(practices.getPracticeLogo() != null ? PropertiesUtil.getProperty("PHP_WEB_PDF") + practices.getPracticeLogo() : "");
                    }
            if (order.getComplianceRewardPoint() != null) {
                newOrder.setCurrentEarnReward(order.getComplianceRewardPoint().getCurrentEarnReward());
                if (order.getComplianceRewardPoint().getCreatedOn() != null) {
                    if (order.getComplianceRewardPoint().getUpdateOn() != null) {  
                        newOrder.setRewardPointDate(DateUtil.dateToString(DateUtil.formatDate(order.getComplianceRewardPoint().getUpdateOn(), Constants.USA_DATE_TIME_SECOND_FORMATE_12H), Constants.USA_DATE_TIME_SECOND_FORMATE_12H));
                    } else {
                        newOrder.setRewardPointDate(DateUtil.dateToString(DateUtil.formatDate(order.getComplianceRewardPoint().getCreatedOn(), Constants.USA_DATE_TIME_SECOND_FORMATE_12H), Constants.USA_DATE_TIME_SECOND_FORMATE_12H));
                    }
                }
            } else {
//                newOrder.setAvailedRewardPoints(0d);
            }
             double rxPatientOutOfPocket = order.getRxPatientOutOfPocket() != null ? order.getRxPatientOutOfPocket() : 0d;
            newOrder.setRxPatientOutOfPocketStr(AppUtil.roundOffNumberToCurrencyFormat(rxPatientOutOfPocket, "en", "US"));
            this.populateNewDrugDetail(order, newOrder);
            newOrder.setOrderStatusName(order.getOrderStatus() != null ? AppUtil.getSafeStr(order.getOrderStatus().getName(), "") : "");
            newOrder.setViewStatus(order.getViewStatus()!= null ? order.getViewStatus(): "");
            int refillDone = order.getRefillDone() != null ? order.getRefillDone() : 0;
            String status = order.getOrderStatus() != null ? AppUtil.getSafeStr(order.getOrderStatus().getName(), "") : "";
            int daysDiff = 0;
            int refillValid = 0;
            newOrder.setDaysSupply(order.getDaysSupply() != null ? order.getDaysSupply() : 0);
            if (order.getRefillsRemaining() != null
                    && order.getRefillsRemaining() > 0 && refillDone == 0
                    && order.getNextRefillDate() != null) {
                Date nextRefillD = order.getNextRefillDate();
                Date today = DateUtil.formatDate(new Date(), Constants.DATE_FORMATE_SHORT);
                long dayDiff = DateUtil.dateDiffInDays(today, nextRefillD);
                System.out.println("DAYS DIFF " + dayDiff + " ID " + order.getId());
                if (dayDiff >= 0) {
                    refillValid = 1;
                    newOrder.setDaysToRefill("" + dayDiff);
                    newOrder.setRefillRemainingDaysCount(dayDiff);
//                    newOrder.setRefillDone(0);
                } else {
                    newOrder.setRefillRemainingDaysCount(dayDiff);
//                    newOrder.setRefillDone(1);
                }
            } else {
                logger.info("refill done will be 1");
//                newOrder.setRefillDone(1);
            }
                    newOrder.setRefillDone(order.getRefillDone());
            newOrder.setRefillRemainingCount(order.getRefillsRemaining() != null ? order.getRefillsRemaining() : 0);
            newOrder.setStatusId(order.getOrderStatus() != null ? order.getOrderStatus().getId() : 0);
            Integer refillRemaining = order.getRefillsRemaining() != null ? order.getRefillsRemaining() : 0;
            newOrder.setRefillsRemaining(refillRemaining + "");
            if (order.getLastRefillDate() != null) {
                newOrder.setLastFilledDate(DateUtil.dateToString(order.getLastRefillDate(), "MM/dd/yyyy"));
            } 
            //Comments these line for short time 15 july 2020
            else if ("Filled".equals(order.getOrderStatus().getName())) {
                newOrder.setLastFilledDate(DateUtil.dateToString(order.getUpdatedAt(), "MM/dd/yyyy"));
            }
            else {
                newOrder.setLastFilledDate("");
            }
            newOrder.setRefillCount(order.getRefillCount());
            newOrder.setRefillsRemaining((order.getRefillsRemaining() != null ? order.getRefillsRemaining() : 0) + "");

            newOrder.setPatientId(order.getPatientProfile().getPatientProfileSeqNo().toString());
            newOrder.setRxNumber(order.getRxNumber()+"-"+"0"+order.getRefillCount());
            newOrder.setOrigRx(order.getRxNumber());
            newOrder.setNextRefillDate(order.getNextRefillDate());
//            newOrder.setNextRefillDate(DateUtil.dateToString(order.getNextRefillDate(), Constants.DATE_FORMATE_SHORT));
            newOrder.setRxRxpiredDate(order.getRxExpiredDate());
            newOrder.setReporterPrescription(order.getReporterPrescription()!= null ? order.getReporterPrescription(): 0);
            if (AppUtil.getSafeStr(primaryOrderId, "").length() > 0) {
                if (order.getId().equals(primaryOrderId)) {
                    newOrder.setPrimaryOrder(1);
                } else {
                    newOrder.setPrimaryOrder(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# populateRefillRxData# ", e);
        }
        return newOrder;
    }

    private void populateOrderCost(Order order, OrderDetailDTO newOrder) {
        double rxIngredientPrice = order.getRxIngredientPrice() != null ? order.getRxIngredientPrice() : 0d;
//        newOrder.setRxIngredientPriceStr(AppUtil.roundOffNumberToCurrencyFormat(rxIngredientPrice, "en", "US"));

        double rxProfitability = order.getRxProfitability() != null ? order.getRxProfitability() : 0d;
//        newOrder.setRxProfitabilityStr(AppUtil.roundOffNumberToCurrencyFormat(rxProfitability, "en", "US"));

        double rxActivityMultiplier = order.getRxActivityMultiplier() != null ? order.getRxActivityMultiplier() : 0d;
//        newOrder.setRxActivityMultiplierStr(AppUtil.roundOffNumberToCurrencyFormat(rxActivityMultiplier, "en", "US"));

        double rxThirdPartyPay = order.getRxThirdPartyPay() != null ? order.getRxThirdPartyPay() : 0d;
//        newOrder.setRxThirdPartyPayStr(AppUtil.roundOffNumberToCurrencyFormat(rxThirdPartyPay, "en", "US"));

        double rxPatientOutOfPocket = order.getRxPatientOutOfPocket() != null ? order.getRxPatientOutOfPocket() : 0d;
        newOrder.setRxPatientOutOfPocketStr(AppUtil.roundOffNumberToCurrencyFormat(rxPatientOutOfPocket, "en", "US"));

        double rxFinalCollect = order.getRxFinalCollect() != null ? order.getRxFinalCollect() : 0d;
//        newOrder.setRxFinalCollectStr(AppUtil.roundOffNumberToCurrencyFormat(rxFinalCollect, "en", "US"));
    }

    private void populateDrugDetail(Order order, OrderDetailDTO newOrder) throws Exception {
        DrugDetail detail = order.getDrugDetail();
        if (detail != null && detail.getDrugDetailId() != null) {
            detail = patientProfileDAO.getDrugDetailById(detail.getDrugDetailId());
            newOrder.setDrugId(detail.getDrugDetailId());
            if (detail.getDrugBasic() != null) {
                newOrder.setBrandName(detail.getDrugBasic().getBrandName());
                if (detail.getDrugBasic().getDrugGeneric() != null) {
                    newOrder.setGenericName(detail.getDrugBasic().getDrugGeneric().getGenericName());
                }
            }
//            newOrder.setStrength(AppUtil.getSafeStr(detail.getStrength(), ""));
//            newOrder.setImageURL(AppUtil.getSafeStr(detail.getImgUrl(), ""));
//            newOrder.setDrugDocURL(AppUtil.getSafeStr(detail.getDrugDocUrl(), ""));
//            newOrder.setPatientDocURL(AppUtil.getSafeStr(detail.getPdfDocUrl(), ""));
            if (AppUtil.getSafeStr(newOrder.getGenericName(), "").equalsIgnoreCase("* BRAND NAME ONLY *")) {
                newOrder.setDrugName(AppUtil.getSafeStr(newOrder.getBrandName(), "") + " "
                        + AppUtil.getSafeStr(newOrder.getGenericName(), ""));
            } else {
                newOrder.setDrugName(AppUtil.getSafeStr(newOrder.getBrandName(), "") + "{"
                        + AppUtil.getSafeStr(newOrder.getGenericName(), "") + "}");

            }
        }
    }

    private  void populateNewDrugDetail(Order order, OrderDetailDTO newOrder) throws Exception {
        try {
            DrugDetail2 drugDetail = order.getDrugDetail2();
            if (drugDetail != null && !CommonUtil.isNullOrEmpty(drugDetail.getDrugId())) {
                drugDetail = (DrugDetail2) patientProfileDAO.findRecordById(new DrugDetail2(), drugDetail.getDrugId());
                if (drugDetail != null) {
                    newOrder.setDrugId(drugDetail.getDrugId());
                    newOrder.setBrandName(drugDetail.getBrandReference());
                    newOrder.setGenericOrBrand(order.getDrugDetail2().getGenericOrBrand());
                    newOrder.setGenericName(drugDetail.getGenericName());
//                    newOrder.setStrength(AppUtil.getSafeStr(drugDetail.getStrength(), ""));
                    newOrder.setGenericOrBrand(AppUtil.getSafeStr(drugDetail.getGenericOrBrand(), ""));

                    newOrder.setDrugName(drugDetail.getRxLabelName());
                      newOrder.setStrength(order.getStrength() != null ? order.getStrength() : order.getDrugDetail2().getStrength() != null ? order.getDrugDetail2().getStrength().trim() : "");
                    if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.TABLET)) {
                        newOrder.setDrugType("TAB");
                    } else if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.CAPSULE)) {
                        newOrder.setDrugType("CAP");
                    } else {
                        newOrder.setDrugType(AppUtil.getSafeStr(order.getDrugType(), "-"));
                    }
                    newOrder.setQty(order.getQty());
                      
//            newOrder.setDrugName(EncryptionHandlerUtil.getDecryptedString(order.getDrugName()));
//                    if ("B".equalsIgnoreCase(drugDetail.getGenericOrBrand())) {
//                        newOrder.setDrugName(AppUtil.getSafeStr(drugDetail.getRxLabelName(), "") + " * BRAND NAME ONLY *");
//                    } else {
//                        newOrder.setDrugName(AppUtil.getSafeStr(drugDetail.getRxLabelName(), "") + "{"
//                                + AppUtil.getSafeStr(drugDetail.getGenericName(), "") + "}");
//                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception#populateNewDrugDetail# ", e);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void getOrderRewardDetail(Integer profileId, OrderDetailDTO newOrder) throws Exception {
        OrderDetailDTO quickStats = getQuickStatsWs(profileId);
//        newOrder.setOpenOrdersCuont(quickStats.getTotalOpenOrders());
//        newOrder.setRxShippedCount(quickStats.getTotalRxShipped());
//        newOrder.setProcessingOrdersCount(quickStats.getProcessingOrdersCount());
//        newOrder.setShippingOrdersCount(quickStats.getShippingOrdersCount());
//        if (quickStats.getSavingsToDate() != null) {
//            newOrder.setTotalSavings(quickStats.getSavingsToDate());
//        } else {
//            newOrder.setTotalSavings(0.00);
//        }
//        RewardPoints rewardPoints = getMyRewardsPoints(profileId);
//        newOrder.setLifetimeRewardPoints(rewardPoints.getLifeTimePoints());
//        Long totalMinusPoints = patientProfileDAO.getTotalRewardHistoryPointByType("MINUS", profileId);
//        if (totalMinusPoints != null) {
//            newOrder.setAvailedRewardPoints(rewardPoints.getAvailablePoints());
//        } else {
//            newOrder.setAvailedRewardPoints(0L);
//        }
//        newOrder.setAvailableRewardPoints(rewardPoints.getAvailablePoints());
    }

    public OrderDetailDTO getNoInsurancesWs(String memberId, Integer profileId) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        try {
            String disValue = "";
            FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getObjectById(new FeeSettings(), Constants.FEE_SETTING_ID);
            if (feeSettings != null && feeSettings.getId() != null) {
//                orderDetailDTO.setLocalDisCount(feeSettings.getFee().intValueExact());
                Integer discountValue = feeSettings.getFee().intValueExact();
                disValue = discountValue.toString();
            }
            logger.info("Discount value is: " + disValue);
            logger.info("Member Id is: " + memberId);
//            orderDetailDTO.setMemberId(memberId);

            String webappRoot = servletContext.getRealPath("/");
            String relativeFolder = File.separator + "resources" + File.separator
                    + "noinsurancecard" + File.separator;
            String filename = Constants.INSURANCE_CARD_PATH + "NoInsuranceCard" + profileId + ".png";
            String noInsuranceCardPath = Constants.INSURANCE_CARD_URL + "NoInsuranceCard" + profileId + ".png";
            setDiscountValueOnCard(disValue, filename);
            setMemberIdOnCard(filename, memberId);
//            orderDetailDTO.setNoInsuranceCard(noInsuranceCardPath);
        } catch (Exception e) {
            logger.error("Exception -> getNoInsurancesWs", e);
        }
        return orderDetailDTO;
    }

    private void setDiscountValueOnCard(String disValue, String noInsuranceCardPath) throws IOException {
        final BufferedImage image = ImageIO.read(new File(Constants.NO_INSURANCE_CARD_PATH + "/" + "Card_01.png"));
        Graphics g = image.getGraphics();
        g.setFont(new Font("Serif", Font.BOLD, 118));
        g.drawString(disValue.trim(), 200, 117);
        g.dispose();
        ImageIO.write(image, "png", new File(noInsuranceCardPath));
        String output = CommonUtil.executeCommand(Constants.COMMAND);
        logger.info("Command Result:: " + output);
    }

    private void setMemberIdOnCard(String noInsuranceCardPath, String memberId) throws IOException {
        final BufferedImage image1 = ImageIO.read(new File(noInsuranceCardPath));
        Graphics2D g1 = image1.createGraphics();
        g1.setPaint(Color.BLACK);
        g1.setFont(g1.getFont().deriveFont(18f));
        g1.drawString(memberId, 587, 350);
        g1.dispose();
        ImageIO.write(image1, "png", new File(noInsuranceCardPath));
        String output = CommonUtil.executeCommand(Constants.COMMAND);
        logger.info("Command Result:: " + output);
    }

    public boolean dependentHasOrders(Integer membeId) throws Exception {
        BusinessObject obj = new BusinessObject("patientProfileMembers.id", membeId, Constants.HIBERNATE_EQ_OPERATOR);
        List objLst = new ArrayList();
        objLst.add(obj);
        List lst = this.patientProfileDAO.findByNestedProperty(new Order(), objLst, "", 0);
        return lst != null && lst.size() > 0;
    }

    public PatientDeliveryAddress savePatientDeliveryAddress(Integer profileId, String address, String apartment, String description, String city, Integer stateId, String zip, String addressType, String defaultAddress, Integer dprefId) {
        PatientDeliveryAddress patientDeliveryAddress = new PatientDeliveryAddress();
        try {
            updatePreviousDefaultAddress(profileId, defaultAddress);
            setDeliveryAddress(patientDeliveryAddress, profileId, address, apartment, description, city, stateId, zip, addressType, defaultAddress, dprefId);
            patientDeliveryAddress.setCreatedOn(new Date());
            patientDeliveryAddress.setUpdatedOn(new Date());
            patientProfileDAO.save(patientDeliveryAddress);
        } catch (Exception e) {
            logger.error("Exception -> savePatientDeliveryAddress", e);
        }
        return patientDeliveryAddress;
    }

    private void updatePreviousDefaultAddress(Integer profileId, String defaultAddress) throws Exception {
        logger.info("updatePreviousDefaultAddress: " + profileId);
        if (defaultAddress.equalsIgnoreCase(Constants.YES)) {
            patientProfileDAO.updatePreviousDefaultAddress(profileId, Constants.NO);
        }
    }

    private void setDeliveryAddress(PatientDeliveryAddress patientDeliveryAddress, Integer profileId, String address, String apartment, String description, String city, Integer stateId, String zip, String addressType, String defaultAddress, Integer dprefId) {
        patientDeliveryAddress.setPatientProfile(new PatientProfile(profileId));
        patientDeliveryAddress.setAddress(address);
        patientDeliveryAddress.setApartment(apartment);
        patientDeliveryAddress.setDescription(description);
        patientDeliveryAddress.setCity(city);
        patientDeliveryAddress.setState(new State(stateId));
        patientDeliveryAddress.setZip(zip);
        patientDeliveryAddress.setAddressType(addressType);
        if (defaultAddress == null || defaultAddress.isEmpty()) {
            defaultAddress = Constants.NO;
        }
        patientDeliveryAddress.setDefaultAddress(defaultAddress);
        if (dprefId != null) {
            patientDeliveryAddress.setDeliveryPreferences(new DeliveryPreferences(dprefId));
        }
    }

    public List<PatientDeliveryAddressDTO> getPatientDeliveryAddressesByProfileId(Integer profileId) {
        List<PatientDeliveryAddressDTO> list = new ArrayList<>();
        try {
            for (PatientDeliveryAddress patientDeliveryAddress : patientProfileDAO.getPatientDeliveryAddressesByProfileId(profileId)) {
                PatientDeliveryAddressDTO patientDeliveryAddressDTO = populatePatientDeliveryAddress(patientDeliveryAddress);
                patientDeliveryAddressDTO.setTotalAddress(patientProfileDAO.getPatientDeliveryAddressesByProfileId(profileId).size());
                list.add(patientDeliveryAddressDTO);
            }
        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryAddressesByProfileId", e);
        }
        return list;
    }

    public List<PatientDeliveryAddressDTO> getPatientDeliveryAddressesByProfileId(Integer profileId, String addressType) {
        List<PatientDeliveryAddressDTO> list = new ArrayList<>();
        try {
            List<PatientDeliveryAddress> listOfDeliveryAddresses = patientProfileDAO.getPatientDeliveryAddressesByProfileIdAndAddressType(profileId, addressType);
            for (PatientDeliveryAddress patientDeliveryAddress : listOfDeliveryAddresses) {
                PatientDeliveryAddressDTO patientDeliveryAddressDTO = populatePatientDeliveryAddress(patientDeliveryAddress);
                patientDeliveryAddressDTO.setTotalAddress(listOfDeliveryAddresses.size());
                list.add(patientDeliveryAddressDTO);
            }
        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryAddressesByProfileId", e);
        }
        return list;
    }

    public PatientDeliveryAddressDTO getPatientDeliveryAddressByProfileId(Integer profileId, String addressType) {
        PatientDeliveryAddressDTO patientDeliveryAddressDTO = new PatientDeliveryAddressDTO();
        try {
            PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getPatientDeliveryAddressByProfileIdAndAddressType(profileId, addressType);
            patientDeliveryAddressDTO = populatePatientDeliveryAddress(patientDeliveryAddress);
        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryAddressByProfileId", e);
        }
        return patientDeliveryAddressDTO;
    }

    public PatientDeliveryAddressDTO getPatientDeliveryAddressById(Integer profileId, Integer id) {
        PatientDeliveryAddressDTO patientDeliveryAddressDTO = new PatientDeliveryAddressDTO();
        try {
            PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getPatientDeliveryAddressById(profileId, id);
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null) {
                patientDeliveryAddressDTO = this.populatePatientDeliveryAddress(patientDeliveryAddress);
            }
        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryAddressById", e);
        }
        return patientDeliveryAddressDTO;
    }

    private PatientDeliveryAddressDTO populatePatientDeliveryAddress(PatientDeliveryAddress patientDeliveryAddress) {
        PatientDeliveryAddressDTO patientDeliveryAddressDTO = new PatientDeliveryAddressDTO();
        patientDeliveryAddressDTO.setAddressId(patientDeliveryAddress.getId());
        if (patientDeliveryAddress.getPatientProfile() != null && patientDeliveryAddress.getPatientProfile().getPatientProfileSeqNo() != null) {
            patientDeliveryAddressDTO.setProfileId(patientDeliveryAddress.getPatientProfile().getPatientProfileSeqNo());
        }
        if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null && patientDeliveryAddress.getState().getId() != 0) {
            patientDeliveryAddressDTO.setStateId(patientDeliveryAddress.getState().getId());
            patientDeliveryAddressDTO.setStateName(patientDeliveryAddress.getState().getName());
        }
        patientDeliveryAddressDTO.setAddress(patientDeliveryAddress.getAddress());
        patientDeliveryAddressDTO.setApartment(patientDeliveryAddress.getApartment());
        patientDeliveryAddressDTO.setAddressType(patientDeliveryAddress.getAddressType());
        patientDeliveryAddressDTO.setCity(patientDeliveryAddress.getCity());
        patientDeliveryAddressDTO.setZip(patientDeliveryAddress.getZip() != null ? patientDeliveryAddress.getZip() : "null");
        patientDeliveryAddressDTO.setDescription(patientDeliveryAddress.getDescription());
        patientDeliveryAddressDTO.setDefaultAddress(patientDeliveryAddress.getDefaultAddress());
        if (patientDeliveryAddress.getDeliveryPreferences() != null && patientDeliveryAddress.getDeliveryPreferences().getId() != null) {
            patientDeliveryAddressDTO.setDprefaId(patientDeliveryAddress.getDeliveryPreferences().getId());
        }
        patientDeliveryAddressDTO.setAddressDescription(patientDeliveryAddress.getDescription());
        patientDeliveryAddressDTO.setCommenseDate(patientDeliveryAddress.getCommenceDate());
        patientDeliveryAddressDTO.setCeaseDate(patientDeliveryAddress.getCeaseDate());
        return patientDeliveryAddressDTO;
    }

    public PatientDeliveryAddress getPatientDeliveryDefaultAddress(Integer profileId) {
        PatientDeliveryAddress patientDeliveryAddress = new PatientDeliveryAddress();
        try {
            patientDeliveryAddress = patientProfileDAO.getDefaultPatientDeliveryAddress(profileId);
        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryDefaultAddress", e);
        }
        return patientDeliveryAddress;
    }

    public PatientDeliveryAddress getPatientDeliveryAddressByPatientId(Integer profileId) {
        PatientDeliveryAddress patientDeliveryAddress = new PatientDeliveryAddress();
        try {
            patientDeliveryAddress = patientProfileDAO.getPatientDeliveryAddressByPatientId(profileId);
        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryAddressByPatientId", e);
        }
        return patientDeliveryAddress;
    }

    public Order getOrderById(String orderId) {
        Order orderInfo = new Order();
        try {
            orderInfo = (Order) patientProfileDAO.findRecordById(new Order(), orderId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getOrderById ", e);
        }
        return orderInfo;
    }

    public String getOrderStatusName(String orderId) {
        Order order = getOrderById(orderId);
        String name = "";
        try {
            OrderStatus orderStatus = order.getOrderStatus();
            if (orderStatus != null && !CommonUtil.isNullOrEmpty(orderStatus.getId())) {
                name = orderStatus.getName();
            }

        } catch (Exception e) {
            logger.error("Exception -> getOrderStatusName ", e);
        }
        return name;
    }

    public OrderStatusDTO getOrderStatusDTO(String orderId) {
        Order order = getOrderById(orderId);
        OrderStatusDTO dto = new OrderStatusDTO();

        try {
            OrderStatus orderStatus = order.getOrderStatus();
            if (orderStatus != null && !CommonUtil.isNullOrEmpty(orderStatus.getId())) {
                dto.setId(orderStatus.getId());
                dto.setName(orderStatus.getName());
            }

        } catch (Exception e) {
            logger.error("Exception -> getOrderStatusDTO ", e);
        }
        return dto;
    }

    public List<DeliveryDistanceFeeDTO> getDistanceFeeDTO(String zipCode, Integer profileId) {
        List<DeliveryDistanceFeeDTO> dDFTlist = new ArrayList<>();
        try {
            List<PharmacyZipCodes> list = patientProfileDAO.getPharmacyZipCodesList();
            if (list.size() > 0) {
                PharmacyZipCodes pharmacyZipCodes = list.get(0);
                if (pharmacyZipCodes != null && pharmacyZipCodes.getId() != null) {
                    DeliveryDistanceFeeDTO deliveryDistanceFeeDTO = new DeliveryDistanceFeeDTO();
                    logger.info("PharmacyZipCodes: " + pharmacyZipCodes.getPharmacyZip() + " Patient Zip Code: " + zipCode);
                    String sURL = Constants.ZIP_CODE_API_URL + pharmacyZipCodes.getPharmacyZip() + "/" + zipCode + "/mile";
                    logger.info("URL: " + sURL);
                    URL url = new URL(sURL);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    if (http != null) {
                        if (http.getResponseCode() == 400 || http.getResponseCode() == 429) {
                            logger.info("Response Code is: " + http.getResponseCode() + " Message: " + http.getResponseMessage());
                            deliveryDistanceFeeDTO.setErrorMessage("Invalid zip code or Usage limit exceeded.");
                            deliveryDistanceFeeDTO.setError_code(http.getResponseCode());
                            dDFTlist.add(deliveryDistanceFeeDTO);
                            return dDFTlist;
                        } else if (http.getResponseCode() == 404) {
                            logger.info("Response Code is: " + http.getResponseCode() + " Message: " + http.getResponseMessage());
                            deliveryDistanceFeeDTO.setErrorMessage("Zip code " + zipCode + " not found.");
                            deliveryDistanceFeeDTO.setError_code(http.getResponseCode());
                            dDFTlist.add(deliveryDistanceFeeDTO);
                            return dDFTlist;
                        }
                    }
                    String genreJson = IOUtils.toString(url.openStream());
                    deliveryDistanceFeeDTO = new Gson().fromJson(genreJson, DeliveryDistanceFeeDTO.class);
                    setDeliveryDistanceFee(deliveryDistanceFeeDTO, pharmacyZipCodes, zipCode, dDFTlist);

                    //save calculation in a table
                    saveZipCodeCalculation(dDFTlist, profileId);
                    ////////////////////////////////////////////////
                    Comparator<DeliveryDistanceFeeDTO> byName
                            = (DeliveryDistanceFeeDTO o1, DeliveryDistanceFeeDTO o2) -> Integer.compare(o1.getSeqNo(), o2.getSeqNo());

                    Collections.sort(dDFTlist, byName);
                    ////////////////////////////////////////////////
                }
            }
        } catch (Exception e) {
            logger.error("Exception -> getDistanceFeeDTO ", e);
        }
        return dDFTlist;
    }

    /////////////////////////////////////////////////////////////////////////////////
    public DeliveryDistanceFeeDTO validateDistanceFeeDTO(String zipCode, Integer profileId) {

        DeliveryDistanceFeeDTO deliveryDistanceFeeDTO = new DeliveryDistanceFeeDTO();
        try {
            List<PharmacyZipCodes> list = patientProfileDAO.getPharmacyZipCodesList();

            ////////////////////////////////////////////////////////////////////////////////
            if (list.size() > 0) {
                PharmacyZipCodes pharmacyZipCodes = list.get(0);
                if (pharmacyZipCodes != null && pharmacyZipCodes.getId() != null) {

                    logger.info("PharmacyZipCodes: " + pharmacyZipCodes.getPharmacyZip() + " Patient Zip Code: " + zipCode);
                    String sURL = Constants.ZIP_CODE_API_URL + pharmacyZipCodes.getPharmacyZip() + "/" + zipCode + "/mile";
                    logger.info("URL: " + sURL);
                    URL url = new URL(sURL);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    if (http != null) {
                        System.out.println("Response Code is: " + http.getResponseCode() + " Message: " + http.getResponseMessage());
                        if (http.getResponseCode() == 400 || http.getResponseCode() == 429) {
                            logger.info("Response Code is: " + http.getResponseCode() + " Message: " + http.getResponseMessage());
                            deliveryDistanceFeeDTO.setErrorMessage("Invalid zip code or Usage limit exceeded.");
                            deliveryDistanceFeeDTO.setError_code(http.getResponseCode());
                            return deliveryDistanceFeeDTO;
                        } else if (http.getResponseCode() == 404) {
                            logger.info("Response Code is: " + http.getResponseCode() + " Message: " + http.getResponseMessage());
                            deliveryDistanceFeeDTO.setErrorMessage("Zip code " + zipCode + " not found.");
                            deliveryDistanceFeeDTO.setError_code(http.getResponseCode());
                            return deliveryDistanceFeeDTO;
                        }
                    }
                    String genreJson = IOUtils.toString(url.openStream());
                    deliveryDistanceFeeDTO = new Gson().fromJson(genreJson, DeliveryDistanceFeeDTO.class);
//                    setDeliveryDistanceFee(deliveryDistanceFeeDTO, pharmacyZipCodes, zipCode, dDFTlist);
                    return deliveryDistanceFeeDTO;
                }
            }

            ////////////////////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            logger.error("Exception -> getDistanceFeeDTO ", e);
            e.printStackTrace();

        }
        return deliveryDistanceFeeDTO;
    }

    ////////////////////////////////////////////////////////////////////////////////
    private void setDeliveryDistanceFee(DeliveryDistanceFeeDTO deliveryDistanceFeeDTO, PharmacyZipCodes pharmacyZipCodes, String zipCode, List<DeliveryDistanceFeeDTO> dDFTlist) {
        if (deliveryDistanceFeeDTO != null && deliveryDistanceFeeDTO.getDistance() != null) {
            Long distance = Math.round(deliveryDistanceFeeDTO.getDistance());
            logger.info("Distance from api: " + deliveryDistanceFeeDTO.getDistance() + " After round zipcode distance value: " + distance);
            for (DeliveryDistanceFee deliveryDistanceFee : pharmacyZipCodes.getDeliveryDistanceFeesList()) {
                DeliveryDistanceFeeDTO dTO = new DeliveryDistanceFeeDTO();
                if (deliveryDistanceFee.getDeliveryDistances() != null && deliveryDistanceFee.getDeliveryDistances().getId() != null) {

                    if (distance >= deliveryDistanceFee.getDeliveryDistances().getDistanceFrom() && distance <= deliveryDistanceFee.getDeliveryDistances().getDistanceTo()) {
                        dTO.setMiles(distance);
                        dTO.setDeliveryFee(deliveryDistanceFee.getDeliveryFee());
                        if (deliveryDistanceFee.getDeliveryPreferenceses() != null) {
                            dTO.setDprefaId(deliveryDistanceFee.getDeliveryPreferenceses().getId());
                            dTO.setName(deliveryDistanceFee.getDeliveryPreferenceses().getName());
                            if (AppUtil.getSafeStr(dTO.getName(), "").contains("2nd Day")) {
                                dTO.setDeliveryFee(BigDecimal.ZERO);
                            }
                            dTO.setDescription(deliveryDistanceFee.getDeliveryPreferenceses().getDescription());
                            dTO.setSeqNo(deliveryDistanceFee.getDeliveryPreferenceses().getSeqNo());
                            logger.info("Name: " + deliveryDistanceFee.getDeliveryPreferenceses().getName());
                        }
                        dTO.setZip(zipCode);
                        if (!checkDuplicateRecordInList(dDFTlist, deliveryDistanceFee.getDeliveryPreferenceses().getId())) {
                            dDFTlist.add(dTO);
                        }
                    } else {
                        logger.info("Distance From: " + deliveryDistanceFee.getDeliveryDistances().getDistanceFrom() + " Distance To: " + deliveryDistanceFee.getDeliveryDistances().getDistanceTo());
                        logger.info("Delivery distance does not match with zip code distance");
                    }
                } else {
                    logger.info("DeliveryDistanceFee is null");
                }
            }
        } else {
            logger.info("Error Msg is: ");
            dDFTlist.add(deliveryDistanceFeeDTO);
        }
    }

    public void saveZipCodeCalculation(List<DeliveryDistanceFeeDTO> list, Integer patientId) {
        try {
            for (DeliveryDistanceFeeDTO dTO : list) {
                ZipCodeCalculation zipCodeCalculation = new ZipCodeCalculation();
                zipCodeCalculation.setPatientId(patientId);
                zipCodeCalculation.setMiles(dTO.getMiles());
                zipCodeCalculation.setDeliveryFee(dTO.getDeliveryFee());
                zipCodeCalculation.setDeliveryPreferencesId(dTO.getDprefaId());
                zipCodeCalculation.setCreatedOn(new Date());
                zipCodeCalculation.setZip(dTO.getZip());
                patientProfileDAO.save(zipCodeCalculation);
            }

        } catch (Exception e) {
            logger.error("Exception -> saveZipCodeCalculation ", e);
        }

    }

    public void updateDeliveryPreferencesByProfileId(Integer profileId, Integer dprefId, String status, String deliveryFee, String distance) {
        try {
            logger.info("Update Delivery Preferences By ProfileId " + profileId + " Delivery Preferences id: " + dprefId + " status: " + status);
            patientProfileDAO.updateDeliveryPreferencesByProfileId(profileId, dprefId, status, deliveryFee, distance);
        } catch (Exception e) {
            logger.error("Exception -> updateDeliveryPreferencesByProfileId ", e);
        }
    }

    public boolean updateTransferRequest(Integer profileId, Integer transferRxId, Integer devliveryAddressId, Integer paymentId, Integer dprefId,
            String zip, String miles, String deliveryFee, Long orderChainId) {
        boolean update = false;
        try {
            update = patientProfileDAO.updateTransferRequest(profileId, transferRxId, devliveryAddressId, paymentId, dprefId, zip, miles, deliveryFee);
            RewardPoints rewardPoints = patientProfileDAO.getRewardPoints(Constants.RX_TRANSFER_Id);
            if (rewardPoints != null && rewardPoints.getId() != null) {
                logger.info("Save Rx Transfer Reward Point.");

            }
            //saveRxTransferOrder(transferRxId, profileId, devliveryAddressId, paymentId);
        } catch (Exception e) {
            logger.error("Exception -> updateTransferRequest ", e);
        }
        return update;
    }

    ////////////////////////////////////////////////////////
    public Order updateTransferOrder(Integer profileId, Integer transferRxId, Integer devliveryAddressId, Integer paymentId, Integer dprefId,
            String zip, String miles, String deliveryFee, Long orderChainId, String comments, String paymentType, Boolean addCopyCard, Long[] coPayCardIdList,
            Integer dPrefId, String copayIds, Boolean isBottleAvailable) {
        boolean update = false;
        Order order = new Order();
        try {
            if (!CommonUtil.isNullOrEmpty(paymentId)) {
                update = patientProfileDAO.updateTransferRequest(profileId, transferRxId, devliveryAddressId, paymentId, dprefId, zip, miles, deliveryFee);
            }

            RewardPoints rewardPoints = patientProfileDAO.getRewardPoints(Constants.RX_TRANSFER_Id);
            if (rewardPoints != null && rewardPoints.getId() != null) {
                logger.info("Save Rx Transfer Reward Point.");
                PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(new PatientDeliveryAddress(), devliveryAddressId);

            }
            //saveRxTransferOrder(transferRxId, profileId, devliveryAdd`ressId, paymentId);
        } catch (Exception e) {
            logger.error("Exception -> updateTransferRequest ", e);
        }
        return order;
    }
    ///////////////////////////////////////////////////////

    public boolean updateTransferRequest(Integer profileId, Integer transferRxId, Integer devliveryAddressId, Integer paymentId, Integer dprefId,
            String zip, String miles, String deliveryFee, Long orderChainId, String comments, String paymentType, Order order) {
        boolean update = false;
        try {
            update = patientProfileDAO.updateTransferRequest(profileId, transferRxId, devliveryAddressId, paymentId, dprefId, zip, miles, deliveryFee);
            RewardPoints rewardPoints = patientProfileDAO.getRewardPoints(Constants.RX_TRANSFER_Id);
            if (rewardPoints != null && rewardPoints.getId() != null) {
                logger.info("Save Rx Transfer Reward Point.");

            }
            //saveRxTransferOrder(transferRxId, profileId, devliveryAddressId, paymentId);
        } catch (Exception e) {
            logger.error("Exception -> updateTransferRequest ", e);
        }
        return update;
    }

    private void saveRxTransferOrder(Integer transferRxId, Integer profileId, Integer devliveryAddressId, Integer paymentId) throws Exception {
        logger.info("Save RxTransfer Order ");
        Order order = new Order();
        TransferRequest transferRequest = (TransferRequest) patientProfileDAO.getObjectById(new TransferRequest(), transferRxId);
        TransferDetail transferDetail = (TransferDetail) patientProfileDAO.getTransferDetailByTranferRequestId(transferRequest.getId());
        if (transferRequest != null && transferRequest.getId() != null) {
            if (transferRequest.getVideo() == null) {
                order.setFirstName(transferRequest.getPatientName());
                order.setLastName(transferRequest.getPatientName());
                order.setPatientProfile(new PatientProfile(profileId));
                order.setOrderType(Constants.ORDERTYPE_TRANSFER);
                order.setViewStatus(Constants.VIEW_STATUS_CLOSED);
                if (transferDetail != null) {
                    order.setTransferDetail(transferDetail);
                } else {
                    order.setTransferDetail(null);
                }
                PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getPatientDeliveryAddressById(profileId, devliveryAddressId);
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

                order.setDrugName(transferRequest.getDrug());
                if (transferRequest.getQuantity() != null) {
                    order.setQty(transferRequest.getQuantity().toString());
                }
                if (transferRequest.getDeliveryFee() != null) {
                    order.setPayment(transferRequest.getDeliveryFee().doubleValue());
                }

                OrderStatus orderStatus = (OrderStatus) patientProfileDAO.getObjectById(new OrderStatus(), Constants.TRANSFER_RX_ORDER_STATUS_Id);
                if (orderStatus != null && orderStatus.getId() != null) {
                    order.setOrderStatus(orderStatus);
                }
                order.setOrderHistory(null);
                order.setCreatedOn(new Date());
                order.setUpdatedOn(new Date());
                patientProfileDAO.save(order);
            }
        }
    }

    /**
     *
     * @param deliveryAddressId
     * @return
     */
    public String getPatientDeliveryAddressById(Integer deliveryAddressId) {

        String patientDeliveryAddressJson = "";
        try {
            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(PatientDeliveryAddress.class, deliveryAddressId);
            List<State> list_states = getStates();

            PatientDeliveryAddressWithStatesDTO patientDeliveryAddressWithStatesDTO = entityToDtoPatientDeliveryAddress(patientDeliveryAddress, list_states);

            ObjectMapper objectMapper = new ObjectMapper();
            patientDeliveryAddressJson = objectMapper.writeValueAsString(patientDeliveryAddressWithStatesDTO);
        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryAddressbyId", e);
        }
        return patientDeliveryAddressJson;
    }

    private PatientDeliveryAddressWithStatesDTO entityToDtoPatientDeliveryAddress(PatientDeliveryAddress patientDeliveryAddress, List<State> list) {

        PatientDeliveryAddressWithStatesDTO patientDeliveryAddressWithStatesDTO = new PatientDeliveryAddressWithStatesDTO();
        patientDeliveryAddressWithStatesDTO.setAddressId(patientDeliveryAddress.getId());
        patientDeliveryAddressWithStatesDTO.setApartment(patientDeliveryAddress.getApartment());
        patientDeliveryAddressWithStatesDTO.setZip(patientDeliveryAddress.getZip());
        patientDeliveryAddressWithStatesDTO.setCity(patientDeliveryAddress.getCity());
        patientDeliveryAddressWithStatesDTO.setAddress(patientDeliveryAddress.getAddress());
        patientDeliveryAddressWithStatesDTO.setDescription(patientDeliveryAddress.getDescription());
        patientDeliveryAddressWithStatesDTO.setDefaultAddress(patientDeliveryAddress.getDefaultAddress());
        patientDeliveryAddressWithStatesDTO.setAddressType(patientDeliveryAddress.getAddressType());
        patientDeliveryAddressWithStatesDTO.setStateId(patientDeliveryAddress.getState().getId());
        patientDeliveryAddressWithStatesDTO.setStateName(patientDeliveryAddress.getState().getName());

        patientDeliveryAddressWithStatesDTO.setStates(list);

        return patientDeliveryAddressWithStatesDTO;

    }

    /**
     * save delivery Address
     *
     * @param patientDeliveryAddressDTO
     * @return
     */
    public String updatePatientDeliveryAddress(PatientDeliveryAddressDTO patientDeliveryAddressDTO) {

        String patientDeliveryAddressJson = "";
        try {
            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(PatientDeliveryAddress.class, patientDeliveryAddressDTO.getAddressId());
            patientDeliveryAddress.setAddress(patientDeliveryAddressDTO.getAddress());
            patientDeliveryAddress.setApartment(patientDeliveryAddressDTO.getApartment());
            patientDeliveryAddress.setCity(patientDeliveryAddressDTO.getCity());
            State state = new State();
            state.setId(patientDeliveryAddressDTO.getStateId());
            patientDeliveryAddress.setState(state);
            patientDeliveryAddress.setZip(patientDeliveryAddressDTO.getZip());
            patientDeliveryAddress.setDescription(patientDeliveryAddressDTO.getDescription());
            patientDeliveryAddress.setAddressType(patientDeliveryAddressDTO.getAddressType());
            patientDeliveryAddress.setDefaultAddress(patientDeliveryAddressDTO.getDefaultAddress());
            patientProfileDAO.saveOrUpdate(patientDeliveryAddress);

            /**
             * send back save object
             */
            List<State> list_states = getStates();
            PatientDeliveryAddressWithStatesDTO patientDeliveryAddressWithStatesDTO = entityToDtoPatientDeliveryAddress(patientDeliveryAddress, list_states);

            ObjectMapper objectMapper = new ObjectMapper();
            patientDeliveryAddressJson = objectMapper.writeValueAsString(patientDeliveryAddressWithStatesDTO);

        } catch (Exception e) {
            logger.error("Exception -> getPatientDeliveryAddressbyId", e);
            return "";
        }
        return patientDeliveryAddressJson;
    }

    /**
     *
     * @param patientProfileUpdateRequestResponseDTO
     * @return
     */
    public String updatePatientProfileAllergies(PatientProfileUpdateRequestResponseDTO patientProfileUpdateRequestResponseDTO) {

        String patientProfileUpdateJson = "";
        try {
            PatientProfile patientProfile = new PatientProfile();
            patientProfile.setPatientProfileSeqNo(patientProfileUpdateRequestResponseDTO.getPatientProfileId());
            patientProfile.setAllergies(patientProfileUpdateRequestResponseDTO.getAllergies());
            patientProfileDAO.updatePatientInfoAllergies(patientProfile);

            patientProfileUpdateRequestResponseDTO.setStatus(Constants.JSON_STATUS.SUCCESS);
            patientProfileUpdateRequestResponseDTO.setStatuscode(Constants.JSON_STATUS.CODE_SUCCESS);

            ObjectMapper objectMapper = new ObjectMapper();
            patientProfileUpdateJson = objectMapper.writeValueAsString(patientProfileUpdateRequestResponseDTO);
            return patientProfileUpdateJson;

        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> updatePatientProfile", e);
            patientProfileUpdateRequestResponseDTO.setStatus(Constants.JSON_STATUS.FAIL);
            patientProfileUpdateRequestResponseDTO.setErrorMessage(e.getMessage());

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                patientProfileUpdateJson = objectMapper.writeValueAsString(patientProfileUpdateRequestResponseDTO);
            } catch (Exception ex) {
                logger.error("Exception: PatientProfileService -> updatePatientProfile -> unable to parse response", ex);
            }
            return patientProfileUpdateJson;
        }

    }
//        ////////////////////////////not exist class and db./////////////////////////

    /**
     * search by Drug Cetory
     */
//    public String searchDrugCategoryByParameter(String searchParameter) {
//        String responseDrugCategoryJson = "";
//        try {
//            List<DrugCategory> lst_drugCategory = patientProfileDAO.searchDrugCategoryListByParameter(searchParameter);
//            List<DrugCategoryListDTO> drugCategoryListDTO = new ArrayList<>();
//            convertDrugCategoryModelToDto(lst_drugCategory, drugCategoryListDTO);
//            
//            ObjectMapper objectMapper = new ObjectMapper();
//            responseDrugCategoryJson = objectMapper.writeValueAsString(drugCategoryListDTO);
//            return responseDrugCategoryJson;
//            
//        } catch (Exception ex) {
//            logger.error("Exception: PatientProfileService -> search Drug Category -> unable to search", ex);
//        }
//        return responseDrugCategoryJson;
//    }
    ////////////////////////////not exist class and db./////////////////////////
//    private void convertDrugCategoryModelToDto(List<DrugCategory> lst_drugCetegoryModel, List<DrugCategoryListDTO> lst_drugCategoryListDTO) {
//        
//        for (DrugCategory drugCategory : lst_drugCetegoryModel) {
//            
//            DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
//            drugCategoryListDTO.setCategoryId("" + drugCategory.getId());
//            drugCategoryListDTO.setCategoryName(drugCategory.getDrugCategoryName());
//            
//            lst_drugCategoryListDTO.add(drugCategoryListDTO);
//        }
//    }
    /**
     * Therapy Classes
     *
     * @param searchParameter
     * @return
     */
//    public String searchTherapyClassesByParameter(Integer drugCatId, String drugTherapyClassname) {
//        
//        String responseDrugCategoryJson = "";
//        try {
//            
//            List<DrugTherapyClass> lst_drugTherapyClass = patientProfileDAO.searchDrugTherapyClassListByParameter(drugCatId, drugTherapyClassname);
//            List<DrugCategoryListDTO> drugCategoryListDTO = new ArrayList<>();
//            convertTherapyClassModelToDto(lst_drugTherapyClass, drugCategoryListDTO);
//            
//            ObjectMapper objectMapper = new ObjectMapper();
//            responseDrugCategoryJson = objectMapper.writeValueAsString(drugCategoryListDTO);
//            return responseDrugCategoryJson;
//            
//        } catch (Exception ex) {
//            logger.error("Exception: PatientProfileService -> search Drug Category -> unable to search", ex);
//        }
//        return responseDrugCategoryJson;
//    }
//    private void convertTherapyClassModelToDto(List<DrugTherapyClass> lst_drugTherapyClassModel, List<DrugCategoryListDTO> lst_drugCategoryListDTO) {
//        
//        for (DrugTherapyClass drugTherapyClass : lst_drugTherapyClassModel) {
//            
//            DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
////            drugCategoryListDTO.setCategoryId("" + drugTherapyClass.getDrugCategory().getId()); //not exist class and db
////            drugCategoryListDTO.setCategoryName(drugTherapyClass.getDrugCategory().getDrugCategoryName());//not exist class and db
//            drugCategoryListDTO.setTherapyClassId("" + drugTherapyClass.getId());
//            drugCategoryListDTO.setTherapyClassName("" + drugTherapyClass.getDrugTherapyClassName());
//            
//            lst_drugCategoryListDTO.add(drugCategoryListDTO);
//        }
//    }
    /**
     * Generic Name
     *
     * @param searchParameter
     * @return
     */
    public String searchGenericNameByParameter(Integer drugTherapyClassId, String genericNamePrefix) {

        String responseDrugCategoryJson = "";
        try {

            List<DrugGenericTypes> lst_drugTherapyClass = patientProfileDAO.searchDrugGenericTypesListByParameter(drugTherapyClassId, genericNamePrefix);
            List<DrugCategoryListDTO> drugCategoryListDTO = new ArrayList<>();
            convertGenericTypeModelToDto(lst_drugTherapyClass, drugCategoryListDTO);

            ObjectMapper objectMapper = new ObjectMapper();
            responseDrugCategoryJson = objectMapper.writeValueAsString(drugCategoryListDTO);
            return responseDrugCategoryJson;

        } catch (Exception ex) {
            logger.error("Exception: PatientProfileService -> search Drug Category -> unable to search", ex);
        }
        return responseDrugCategoryJson;
    }

    private void convertGenericTypeModelToDto(List<DrugGenericTypes> lst_drugTherapyClass, List<DrugCategoryListDTO> lst_drugCategoryListDTO) {

        for (DrugGenericTypes drugGenericTypes : lst_drugTherapyClass) {

            DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
            drugCategoryListDTO.setGenericNameId("" + drugGenericTypes.getId());
            drugCategoryListDTO.setGenericName(drugGenericTypes.getDrugGenericName());
//            drugCategoryListDTO.setTherapyClassId("" + drugGenericTypes.getDrugTherapyClass().getId());

            lst_drugCategoryListDTO.add(drugCategoryListDTO);
        }
    }

    /**
     * Brand Name
     */
    public String searchBrandNameByParameter(Integer drugGenericNameId, String brandNamePrefix) {

        String responseDrugCategoryJson = "";
        try {

            List<DrugBrand> lst_brandName = patientProfileDAO.searchDrugBrandNameListByParameter(drugGenericNameId, brandNamePrefix);
            List<DrugCategoryListDTO> drugCategoryListDTO = new ArrayList<>();
            convertDrugBrandModelToDto(lst_brandName, drugCategoryListDTO);

            ObjectMapper objectMapper = new ObjectMapper();
            responseDrugCategoryJson = objectMapper.writeValueAsString(drugCategoryListDTO);
            return responseDrugCategoryJson;

        } catch (Exception ex) {
            logger.error("Exception: PatientProfileService -> search Drug Category -> unable to search", ex);
        }
        return responseDrugCategoryJson;
    }

    private void convertDrugBrandModelToDto(List<DrugBrand> lst_drugBrand, List<DrugCategoryListDTO> lst_drugCategoryListDTO) {

        for (DrugBrand drugBrand : lst_drugBrand) {

            DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();

            drugCategoryListDTO.setBrandNameId("" + drugBrand.getId());
            drugCategoryListDTO.setBrandName(drugBrand.getDrugBrandName());
            drugCategoryListDTO.setGenericNameId("" + drugBrand.getDrugGenericTypes().getId());

            lst_drugCategoryListDTO.add(drugCategoryListDTO);
        }
    }

    public boolean saveDrugSearchesWs(Integer patientId, Integer drugId, String drugName, String drugType, String qty, String drugPrice, String strength, String genericName) {
        boolean isSaved = false;
        try {
            DrugSearches drugSearches = new DrugSearches();
            drugSearches.setPatientProfile(new PatientProfile(patientId));
            drugSearches.setDrug(new Drug(drugId));
            drugSearches.setGenericName(genericName);
            drugSearches.setDrugBrandName(drugName);
            drugSearches.setDrugType(drugType);
            drugSearches.setQty(qty);
            if (drugPrice != null) {
                drugSearches.setDrugPrice(Double.parseDouble(drugPrice));
            }
            drugSearches.setStrength(strength);
            drugSearches.setCreatedOn(new Date());
            patientProfileDAO.save(drugSearches);
            isSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            logger.error("Exception: PatientProfileService -> saveDrugSearchesWs", e);
        }
        return isSaved;
    }

    /////////////////////////////////////////////////////////////////
    public boolean saveDrugSearchesWs(Integer patientId, Long drugId, String drugName, String drugType, String qty, String drugPrice, String strength, String genericName) {
        boolean isSaved = false;
        try {
            DrugSearches drugSearches = new DrugSearches();
            drugSearches.setPatientProfile(new PatientProfile(patientId));
            DrugDetail detail = new DrugDetail();
            detail.setDrugDetailId(drugId);//.setDrugNDC(drugId);
            drugSearches.setDrugDetail(detail);
            drugSearches.setGenericName(genericName);
            drugSearches.setDrugBrandName(drugName);
            drugSearches.setDrugType(drugType);
            drugSearches.setQty(qty);
            if (drugPrice != null) {
                drugSearches.setDrugPrice(Double.parseDouble(drugPrice));
            }
            drugSearches.setStrength(strength);
            drugSearches.setCreatedOn(new Date());
            patientProfileDAO.save(drugSearches);
            isSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            logger.error("Exception: PatientProfileService -> saveDrugSearchesWs", e);
        }
        return isSaved;
    }

    ////////////////////////////////////////////////////////////////
    public List<DrugSearchesDTO> getSearchesRecordList(Integer patientId) {
        List<DrugSearchesDTO> list = new ArrayList<>();
        try {
            List<DrugSearches> drugSearcheses = patientProfileDAO.getDrugSearchesList(patientId);
            logger.info("Total Record: " + drugSearcheses.size());
            for (DrugSearches ds : drugSearcheses) {
                DrugSearchesDTO drugSearchesDTO = new DrugSearchesDTO();
                drugSearchesDTO.setDrugSearchId(ds.getId());
                if (ds.getPatientProfile() != null && ds.getPatientProfile().getPatientProfileSeqNo() != null) {
                    drugSearchesDTO.setId(ds.getPatientProfile().getPatientProfileSeqNo());
                    drugSearchesDTO.setPatientId(ds.getPatientProfile().getPatientProfileSeqNo());
                }
//                if (ds.getDrug() != null && ds.getDrug().getDrugId() != null) {
//                    drugSearchesDTO.setDrugId(ds.getDrug().getDrugId());
//                }
                if (ds.getDrugDetail() != null && ds.getDrugDetail().getDrugDetailId() != null) {//ds.getDrugDetail().getDrugNDC() != null) {
                    // drugSearchesDTO.setDrugId(ds.getDrug().getDrugId());
                    drugSearchesDTO.setDrugNDC(ds.getDrugDetail().getDrugDetailId().toString());//.getDrugNDC().toString());
                }
                drugSearchesDTO.setGenericName(ds.getGenericName());
                drugSearchesDTO.setDrugName(ds.getDrugBrandName());
                drugSearchesDTO.setDrugType(ds.getDrugType());
                drugSearchesDTO.setStrength(ds.getStrength());
                drugSearchesDTO.setDrugQty(ds.getQty());
                drugSearchesDTO.setDrugPrice(ds.getDrugPrice());
                String date = DateUtil.dateToString(ds.getCreatedOn(), "MMM dd, yyyy");
                String time = DateUtil.dateToString(ds.getCreatedOn(), "HH:mm a");
                drugSearchesDTO.setCreatedOn(date + " at " + time);
                drugSearchesDTO.setTotalRecord(drugSearcheses.size());
                list.add(drugSearchesDTO);
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getSearchesRecordList", e);
        }
        return list;
    }

    public boolean deleteDrugSearchesById(Integer id) {
        boolean isDelete = false;
        try {
            isDelete = patientProfileDAO.deleteDrugSearchesRecordById(id);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> deleteDrugSearchesById", e);
        }
        return isDelete;
    }

    public boolean deleteAllDrugSearchesRecordByProfileId(Integer profileId) {
        boolean isDelete = false;
        try {
            isDelete = patientProfileDAO.deleteAllDrugSearchesRecordByProfileId(profileId);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> deleteAllDrugSearchesRecordByProfileId", e);
        }
        return isDelete;
    }

    public boolean saveBloodGlucoseResult(Integer profileId, String glucoseLevel, String readingTime, String type) {
        boolean isSaved = false;
        try {
            PatientGlucoseResults patientGlucoseResults = new PatientGlucoseResults();
            patientGlucoseResults.setPatientProfile(new PatientProfile(profileId));
            patientGlucoseResults.setGlucoseLevel(glucoseLevel);
            patientGlucoseResults.setReadingTime(readingTime);
            patientGlucoseResults.setType(type);
            patientGlucoseResults.setCreatedOn(new Date());
            patientProfileDAO.save(patientGlucoseResults);
            isSaved = true;
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception: PatientProfileService -> saveBloodGlucoseResultsaveBloodGlucoseResult", e);
        }
        return isSaved;
    }

    /**
     *
     * @param insuranceDetailsId
     * @return
     */
    public PatientInsuranceDetailsDTO getPatientInsuranceDetailsById(Long insuranceDetailsId) {
        PatientInsuranceDetailsDTO patientInsuranceDetailsDTO = null;
        try {
            if (insuranceDetailsId != null && insuranceDetailsId > 0) {

                PatientInsuranceDetails patientInsuranceDetails = (PatientInsuranceDetails) patientProfileDAO.findByPropertyUnique(new PatientInsuranceDetails(), "id", insuranceDetailsId, Constants.HIBERNATE_EQ_OPERATOR);//getPatientInsuranceDetailById(insuranceDetailsId);
                patientInsuranceDetailsDTO = new PatientInsuranceDetailsDTO();
                modelToDtoPatientInsuranceDetails(patientInsuranceDetailsDTO, patientInsuranceDetails);
                logger.info("Getting Patient Insurance : " + patientInsuranceDetails.getId());
            }
        } catch (Exception e) {
            logger.error("Exception -> getPatientInsurance Detail ", e);
        }
        return patientInsuranceDetailsDTO;
    }

    /**
     *
     * @param patientInsuranceDetailsDTO
     * @return
     */
    public boolean saveUpdatePatientInsuranceDetails(PatientInsuranceDetailsDTO patientInsuranceDetailsDTO) {
        boolean isUpdate = false;

        /**
         * check record save or update
         */
        boolean b_recordUpdate = updatePatientInsuranceDetail(patientInsuranceDetailsDTO);
        if (b_recordUpdate) {
            return true;
        }
        try {
            if (patientInsuranceDetailsDTO != null) {

                PatientProfile patientProfile = new PatientProfile();
                patientProfile.setPatientProfileSeqNo(patientInsuranceDetailsDTO.getPatientProfileId());

                PatientInsuranceDetails patientInsuranceDetails = new PatientInsuranceDetails();
                patientInsuranceDetails.setPatientProfile(patientProfile);
                dtoToModelPatientInsuranceDetails(patientInsuranceDetailsDTO, patientInsuranceDetails);

                patientProfileDAO.saveOrUpdate(patientInsuranceDetails);
                logger.info("Save Ptient Insurance : " + patientInsuranceDetails.getId());
                isUpdate = true;
            }
        } catch (Exception e) {
            logger.error("Exception -> updateDeliveryAddressWs", e);
        }
        return isUpdate;
    }

    /**
     *
     * @param patientInsuranceDetailsDTO
     * @return
     */
    private boolean updatePatientInsuranceDetail(PatientInsuranceDetailsDTO patientInsuranceDetailsDTO) {

        boolean isUpdate = false;
        try {
            PatientInsuranceDetails patientInsuranceDetails = patientProfileDAO.getPatientInsuranceDetailByPatientId(patientInsuranceDetailsDTO.getPatientProfileId());
            if (patientInsuranceDetails != null && patientInsuranceDetails.getId() != 0) {
                dtoToModelPatientInsuranceDetails(patientInsuranceDetailsDTO, patientInsuranceDetails);
                patientProfileDAO.merge(patientInsuranceDetails);
                return true;
            }
            logger.info("Update Ptient Insurance : " + patientInsuranceDetails.getId());
        } catch (Exception ex) {
            logger.info("Patient Insurnace Card Detail not exist");
        }
        return isUpdate;

    }

    private void dtoToModelPatientInsuranceDetails(PatientInsuranceDetailsDTO patientInsuranceDetailsDTO, PatientInsuranceDetails patientInsuranceDetails) throws Exception {

        Date fromdate = DateUtil.stringToDate(patientInsuranceDetailsDTO.getExpiryDate(), "MM/dd/yyyy");
        patientInsuranceDetails.setExpiryDate(fromdate);
        patientInsuranceDetails.setGroupNumber(patientInsuranceDetailsDTO.getGroupNumber());
        patientInsuranceDetails.setInsuranceProvider(patientInsuranceDetailsDTO.getInsuranceProvider());
        patientInsuranceDetails.setMemberID(patientInsuranceDetailsDTO.getMemberID());
        patientInsuranceDetails.setPlanId(patientInsuranceDetailsDTO.getPlanId());
        patientInsuranceDetails.setProviderAddress(patientInsuranceDetailsDTO.getProviderAddress());
        patientInsuranceDetails.setProviderPhone(patientInsuranceDetailsDTO.getProviderPhone());
        Date currentDate = DateUtil.formatDate(new Date(), "MM/dd/yyyy");
        patientInsuranceDetails.setCreatedOn(currentDate);
        patientInsuranceDetails.setUpdatedOn(currentDate);

    }

    private void modelToDtoPatientInsuranceDetails(PatientInsuranceDetailsDTO patientInsuranceDetailsDTO, PatientInsuranceDetails patientInsuranceDetails) throws Exception {

        patientInsuranceDetailsDTO.setId(patientInsuranceDetails.getId());
        patientInsuranceDetailsDTO.setGroupNumber(patientInsuranceDetails.getGroupNumber());
        patientInsuranceDetailsDTO.setInsuranceProvider(patientInsuranceDetails.getInsuranceProvider());
        patientInsuranceDetailsDTO.setMemberID(patientInsuranceDetails.getMemberID());
        patientInsuranceDetailsDTO.setPlanId(patientInsuranceDetails.getPlanId());
        patientInsuranceDetailsDTO.setProviderAddress(patientInsuranceDetails.getProviderAddress());
        patientInsuranceDetailsDTO.setProviderPhone(patientInsuranceDetails.getProviderPhone());
        String str_expireDate = DateUtil.dateToString(patientInsuranceDetails.getExpiryDate(), "MM/dd/yyyy");
        patientInsuranceDetailsDTO.setExpiryDate(str_expireDate);

    }

    public boolean isDefaultPatientDeliveryAddress(Integer profileId) {
        boolean isExist = false;
        try {
            isExist = patientProfileDAO.isDefaultPatientDeliveryAddress(profileId);
            logger.info("isDefaultPatientDeliveryAddress: " + isExist);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> isDefaultPatientDeliveryAddress", e);
        }
        return isExist;
    }

    public String getZipCodeCalculations(String zip, Integer profileId, Integer prefId) {
        ZipCodeCalculation object = null;
        try {
            object = this.patientProfileDAO.getZipCodeCalculations(zip, profileId, prefId);
        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (object != null) {
            return object.getDeliveryFee().toString();
        }
        return "0";
    }

    public List<DeliveryDistanceFeeDTO> getZipCodeCalculationsList(String zip, PatientProfile patientProfile) {
        List<DeliveryDistanceFeeDTO> deliveryDistanceFeeDTOs = new ArrayList<>();
        try {
            List<ZipCodeCalculation> zipCodeCalculationsList = patientProfileDAO.getZipCodeCalculationsList(zip, patientProfile.getPatientProfileSeqNo());
            if (!zipCodeCalculationsList.isEmpty() && zipCodeCalculationsList.size() > 0) {
                ZipCodeCalculation calculation = zipCodeCalculationsList.get(0);
                PharmacyZipCodes pharmacyZipCodes = this.getPharmacyZipCodes();
                for (DeliveryDistanceFee deliveryDistanceFee : pharmacyZipCodes.getDeliveryDistanceFeesList()) {
                    DeliveryDistanceFeeDTO distanceFeeDTO = new DeliveryDistanceFeeDTO();
                    if (deliveryDistanceFee != null && deliveryDistanceFee.getId() != null) {
                        if (calculation.getMiles() >= deliveryDistanceFee.getDeliveryDistances().getDistanceFrom() && calculation.getMiles() <= deliveryDistanceFee.getDeliveryDistances().getDistanceTo()) {
                            if (calculation.getDeliveryFee() != null) {
                                logger.info("Delivery fee is: " + deliveryDistanceFee.getDeliveryFee());
                                distanceFeeDTO.setDeliveryFee(deliveryDistanceFee.getDeliveryFee());
                            }
                            distanceFeeDTO.setMiles(calculation.getMiles());
                            if (deliveryDistanceFee.getDeliveryPreferenceses() != null && deliveryDistanceFee.getDeliveryPreferenceses().getId() != null) {
                                logger.info("DeliveryPreferenceId is:: " + deliveryDistanceFee.getDeliveryPreferenceses().getId());
                                distanceFeeDTO.setDprefaId(deliveryDistanceFee.getDeliveryPreferenceses().getId());
                                distanceFeeDTO.setName(deliveryDistanceFee.getDeliveryPreferenceses().getName());
                                String name = AppUtil.getSafeStr(deliveryDistanceFee.getDeliveryPreferenceses().getName(), "").toLowerCase();
                                if (name.contains("2nd day")) {
                                    distanceFeeDTO.setDeliveryFee(BigDecimal.valueOf(0));
                                    distanceFeeDTO.setIncludedStr("Included");
                                } else {
                                    distanceFeeDTO.setIncludedStr("");
                                }
                                distanceFeeDTO.setDescription(deliveryDistanceFee.getDeliveryPreferenceses().getDescription());
                                distanceFeeDTO.setPickedFromPharmacy(deliveryDistanceFee.getDeliveryPreferenceses().isPickedFromPharmacy());
                            }
                            if (!checkDuplicateRecordInList(deliveryDistanceFeeDTOs, distanceFeeDTO.getDprefaId())) {
                                deliveryDistanceFeeDTOs.add(distanceFeeDTO);
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getZipCodeCalculationsList", e);
        }
        return deliveryDistanceFeeDTOs;
    }

    public boolean checkDuplicateRecordInList(List<DeliveryDistanceFeeDTO> list, Integer prefId) {
        boolean isExist = false;
        try {
            for (DeliveryDistanceFeeDTO dTO : list) {
                if (dTO.getDprefaId() != null && prefId != null) {
                    if (dTO.getDprefaId().equals(prefId)) {
                        isExist = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> checkDuplicateRecordInList", e);
        }
        return isExist;
    }

    public PharmacyZipCodes getPharmacyZipCodes() {
        PharmacyZipCodes pharmacyZipCodes = new PharmacyZipCodes();
        try {
            List<PharmacyZipCodes> list = patientProfileDAO.getPharmacyZipCodesList();
            if (!list.isEmpty() && list.size() > 0) {
                pharmacyZipCodes = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getPharmacyZipCodes", e);
        }
        return pharmacyZipCodes;
    }

    public List<OrderDetailDTO> getTransferRxList(Integer profileId, Integer transFerId) {
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            List<TransferRequest> transferRequestList = patientProfileDAO.geTransferRequestsList(profileId, transFerId);
            setRxTransFerList(transferRequestList, list, profileId);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getTransferRxList", e);
        }
        return list;
    }

    public List<OrderDetailDTO> getTransferRxList(Integer profileId, Integer transFerId, String comments, String paymentType) {
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            List<TransferRequest> transferRequestList = patientProfileDAO.geTransferRequestsList(profileId, transFerId);
            setRxTransFerList(transferRequestList, list, profileId, comments, paymentType);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getTransferRxList", e);
        }
        return list;
    }

    public List<OrderDetailDTO> getTransferRxList(Integer profileId, Integer transFerId, String comments, String paymentType, String source, Boolean addCopyCard) {
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            List<TransferRequest> transferRequestList = patientProfileDAO.geTransferRequestsList(profileId, transFerId);
            setRxTransFerList(transferRequestList, list, profileId, comments, paymentType, source, addCopyCard);
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getTransferRxList", e);
        }
        return list;
    }

    private void setRxTransFerList(List<TransferRequest> transferRequestList, List<OrderDetailDTO> list, Integer profileId) throws Exception {
        if (!transferRequestList.isEmpty() && transferRequestList.size() > 0) {
            for (TransferRequest transferRequest : transferRequestList) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                if (transferRequest.getDeliveryPreferences() != null && transferRequest.getDeliveryPreferences().getId() != null) {
                    DeliveryPreferences deliveryPreferences = this.getDeliveryPreferenceById(transferRequest.getDeliveryPreferences().getId());
//                    String fee = this.getZipCodeCalculations(transferRequest.getZip(), profileId, transferRequest.getDeliveryPreferences().getId());
//                    orderDetailDTO.setName(deliveryPreferences.getName());
//                    orderDetailDTO.setDescription(deliveryPreferences.getDescription());
//                    orderDetailDTO.setFee(CommonUtil.getStrToBigDecimal(fee));
                }
//                orderDetailDTO.setTransferRxId(transferRequest.getId());
                orderDetailDTO.setPatientName(transferRequest.getPatientName());
                orderDetailDTO.setDrugName(transferRequest.getDrug());
                if (transferRequest.getQuantity() != null) {
                    orderDetailDTO.setQty(transferRequest.getQuantity().toString());
                }
                if (transferRequest.getRequestedOn() != null) {
                    orderDetailDTO.setOrderDate(DateUtil.dateToString(transferRequest.getRequestedOn(), Constants.USA_DATE_TIME_FORMATE));
                }
                if (transferRequest.getPatientDeliveryAddress() != null && transferRequest.getPatientDeliveryAddress().getId() != null) {
                    State state = transferRequest.getPatientDeliveryAddress().getState();
                    if (transferRequest.getPatientDeliveryAddress().getApartment() != null && !"".equals(transferRequest.getPatientDeliveryAddress().getApartment())) {
                        logger.info("Appartmnet is: " + transferRequest.getPatientDeliveryAddress().getApartment());
//                        orderDetailDTO.setShippingAddress(transferRequest.getPatientDeliveryAddress().getAddress() + " " + transferRequest.getPatientDeliveryAddress().getApartment() + " " + transferRequest.getPatientDeliveryAddress().getCity() + ", " + state.getName() + " " + transferRequest.getPatientDeliveryAddress().getZip());
                    } else {
//                        orderDetailDTO.setShippingAddress(transferRequest.getPatientDeliveryAddress().getAddress() + " " + transferRequest.getPatientDeliveryAddress().getCity() + ", " + state.getName() + " " + transferRequest.getPatientDeliveryAddress().getZip());
                    }
                }
                orderDetailDTO.setPayment(transferRequest.getDeliveryFee().doubleValue());
                orderDetailDTO.setOrderType(1);
                orderDetailDTO.setOrderStatusName(Constants.PENDING);
                getOrderRewardDetail(profileId, orderDetailDTO);
                list.add(orderDetailDTO);
            }
        }
    }

    private void setRxTransFerList(List<TransferRequest> transferRequestList, List<OrderDetailDTO> list, Integer profileId, String comments, String paymentType) throws Exception {
        if (!transferRequestList.isEmpty() && transferRequestList.size() > 0) {
            for (TransferRequest transferRequest : transferRequestList) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                if (transferRequest.getDeliveryPreferences() != null && transferRequest.getDeliveryPreferences().getId() != null) {
                    DeliveryPreferences deliveryPreferences = this.getDeliveryPreferenceById(transferRequest.getDeliveryPreferences().getId());
                    String fee = this.getZipCodeCalculations(transferRequest.getZip(), profileId, transferRequest.getDeliveryPreferences().getId());
//                    orderDetailDTO.setName(deliveryPreferences.getName());
//                    orderDetailDTO.setDescription(deliveryPreferences.getDescription());
//                    orderDetailDTO.setFee(CommonUtil.getStrToBigDecimal(fee));
                }
//                orderDetailDTO.setTransferRxId(transferRequest.getId());
//                orderDetailDTO.setPatientName(transferRequest.getPatientName());
//                orderDetailDTO.setDrugName(transferRequest.getDrug());
//                orderDetailDTO.setComments(AppUtil.getSafeStr(comments, ""));//transferRequest.getComments());
//                orderDetailDTO.setPaymentType(AppUtil.getSafeStr(paymentType, ""));//transferRequest.getPaymentType());
                if (transferRequest.getQuantity() != null) {
                    orderDetailDTO.setQty(transferRequest.getQuantity().toString());
                }
                if (transferRequest.getRequestedOn() != null) {
                    orderDetailDTO.setOrderDate(DateUtil.dateToString(transferRequest.getRequestedOn(), Constants.USA_DATE_TIME_FORMATE));
                }

                if (transferRequest.getPatientDeliveryAddress() != null && transferRequest.getPatientDeliveryAddress().getId() != null) {
                    State state = transferRequest.getPatientDeliveryAddress().getState();
//                    if (transferRequest.getPatientDeliveryAddress().getApartment() != null && !"".equals(transferRequest.getPatientDeliveryAddress().getApartment())) {
//                        logger.info("Appartmnet is: " + transferRequest.getPatientDeliveryAddress().getApartment());
//                        orderDetailDTO.setShippingAddress(transferRequest.getPatientDeliveryAddress().getAddress() + " " + transferRequest.getPatientDeliveryAddress().getApartment() + " " + transferRequest.getPatientDeliveryAddress().getCity() + ", " + state.getName() + " " + transferRequest.getPatientDeliveryAddress().getZip());
//                    } else {
//                        orderDetailDTO.setShippingAddress(transferRequest.getPatientDeliveryAddress().getAddress() + " " + transferRequest.getPatientDeliveryAddress().getCity() + ", " + state.getName() + " " + transferRequest.getPatientDeliveryAddress().getZip());
//                    }
                }
                orderDetailDTO.setPayment(transferRequest.getDeliveryFee().doubleValue());
                orderDetailDTO.setOrderType(1);
                orderDetailDTO.setOrderStatusName(Constants.PENDING);
                getOrderRewardDetail(profileId, orderDetailDTO);
                list.add(orderDetailDTO);
            }
        }
    }

    private void setRxTransFerList(List<TransferRequest> transferRequestList, List<OrderDetailDTO> list, Integer profileId, String comments, String paymentType, String source, Boolean addCopyCard) throws Exception {
        if (!transferRequestList.isEmpty() && transferRequestList.size() > 0) {
            for (TransferRequest transferRequest : transferRequestList) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//                orderDetailDTO.setIsCopayCard(addCopyCard);
//                if (transferRequest.getDeliveryPreferences() != null && transferRequest.getDeliveryPreferences().getId() != null) {
//                    DeliveryPreferences deliveryPreferences = this.getDeliveryPreferenceById(transferRequest.getDeliveryPreferences().getId());
//                    String fee = this.getZipCodeCalculations(transferRequest.getZip(), profileId, transferRequest.getDeliveryPreferences().getId());
//                    orderDetailDTO.setName(deliveryPreferences.getName());
//                    orderDetailDTO.setDescription(deliveryPreferences.getDescription());
//                    orderDetailDTO.setFee(CommonUtil.getStrToBigDecimal(fee));
//                }
//                orderDetailDTO.setTransferRxId(transferRequest.getId());
//                orderDetailDTO.setPatientName(transferRequest.getPatientName());
//                orderDetailDTO.setDrugName(transferRequest.getDrug());
//                orderDetailDTO.setComments(AppUtil.getSafeStr(comments, ""));//transferRequest.getComments());
//                orderDetailDTO.setPaymentType(AppUtil.getSafeStr(paymentType, ""));//transferRequest.getPaymentType());
                if (transferRequest.getQuantity() != null) {
                    orderDetailDTO.setQty(transferRequest.getQuantity().toString());
                }
                if (transferRequest.getRequestedOn() != null) {
                    orderDetailDTO.setOrderDate(DateUtil.dateToString(transferRequest.getRequestedOn(), Constants.USA_DATE_TIME_FORMATE));
                }

                if (transferRequest.getPatientDeliveryAddress() != null && transferRequest.getPatientDeliveryAddress().getId() != null) {
                    State state = transferRequest.getPatientDeliveryAddress().getState();
//                    if (transferRequest.getPatientDeliveryAddress().getApartment() != null && !"".equals(transferRequest.getPatientDeliveryAddress().getApartment())) {
//                        logger.info("Appartmnet is: " + transferRequest.getPatientDeliveryAddress().getApartment());
////                        orderDetailDTO.setShippingAddress(transferRequest.getPatientDeliveryAddress().getAddress() + " " + transferRequest.getPatientDeliveryAddress().getApartment() + " " + transferRequest.getPatientDeliveryAddress().getCity() + ", " + state.getName() + " " + transferRequest.getPatientDeliveryAddress().getZip());
//                    } else {
//                        orderDetailDTO.setShippingAddress(transferRequest.getPatientDeliveryAddress().getAddress() + " " + transferRequest.getPatientDeliveryAddress().getCity() + ", " + state.getName() + " " + transferRequest.getPatientDeliveryAddress().getZip());
//                    }
                }
//                orderDetailDTO.setSource(source);
                orderDetailDTO.setPayment(transferRequest.getDeliveryFee().doubleValue());
                orderDetailDTO.setOrderType(1);
                orderDetailDTO.setOrderStatusName(Constants.PENDING);
                getOrderRewardDetail(profileId, orderDetailDTO);
                list.add(orderDetailDTO);
            }
        }
    }

    public PatientProfile getPatientProfileBySecurityToken(String securityToken) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileBySecurityToken(securityToken);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService -> getPatientProfileBySecurityToken", e);
        }
        return patientProfile;
    }

    public boolean savePatientProfileNotes(PatientProfileNotes patientProfileNotes) {
        boolean success;
        try {
            patientProfileNotes.setCreatedOn(new Date());
            patientProfileDAO.save(patientProfileNotes);
            success = true;
        } catch (Exception e) {
            logger.error("Exception::", e);
            success = false;
        }
        return success;
    }

    public List<PatientProfileNotes> getPatientProfileNotesList(Integer profileId) {
        List<PatientProfileNotes> list = new ArrayList<>();
        try {
            list = patientProfileDAO.getPatientProfileNotesListByPtProfileId(profileId);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public boolean isDefaultDeliveryAddress(Integer profileId) {
        boolean isExist = false;
        try {
            logger.info("ProfileId is:: " + profileId);
            isExist = patientProfileDAO.isDefaultDeliveryAddress(profileId);
            logger.info("isDefaultDeliveryAddress:: " + isExist);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return isExist;
    }

    public boolean isDefaultPaymentInfo(Integer profileId) {
        boolean isExist = false;
        try {
            logger.info("ProfileId is:: " + profileId);
            isExist = patientProfileDAO.isDefaultPaymentInfo(profileId);
            logger.info("isDefaultDeliveryAddress:: " + isExist);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return isExist;
    }

    public TransferDetail getTransferDetailByTranferRequestId(int requestId) {

        try {
            TransferDetail trnsfer = patientProfileDAO.getTransferDetailByTranferRequestId(requestId);
            return trnsfer;
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return null;
    }

    public boolean deleteRxTransferRecord(Integer transferId, Integer profileId) {
        boolean isDelete = false;
        try {
            isDelete = patientProfileDAO.deleteRxTransferRecord(transferId, profileId);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return isDelete;
    }

    public PatientInsuranceDetails getPatientInsuranceDetailsByProfileId(Integer profileId) {
        PatientInsuranceDetails patientInsuranceDetails = new PatientInsuranceDetails();
        try {
            patientInsuranceDetails = patientProfileDAO.getPatientInsuranceDetailByPatientId(profileId);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return patientInsuranceDetails;
    }

    //////////////////////////////////////////////////////////////////////////////
    public Set<OrderDetailDTO> viewOrderReceiptWs(Integer patientId, String orderId, String handlingFee, String pref,
            String cardNumber, String cardType, String awardedPoints, String clearNameFlds) {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        try {
            List<Order> dbOrders = patientProfileDAO.viewOrderReceiptList(patientId, orderId);
            list = setOrderList(dbOrders, null, handlingFee, pref, cardNumber, cardType, awardedPoints, clearNameFlds);
            logger.info("New Order list size: " + list.size());
        } catch (Exception e) {
            logger.error("Exception -> viewOrderReceiptWs", e);
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////////////////
    public Set<OrderDetailDTO> viewOrderReceiptWs(Integer patientId, String orderId, String handlingFee, String pref,
            String cardNumber, String cardType, String awardedPoints, String clearNameFlds, String paymentType, String comments) {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        try {
            List<Order> dbOrders = patientProfileDAO.viewOrderReceiptList(patientId, orderId);
            list = setOrderList(dbOrders, null, handlingFee, pref, cardNumber, cardType, awardedPoints, clearNameFlds, paymentType, comments);
            logger.info("New Order list size: " + list.size());
        } catch (Exception e) {
            logger.error("Exception -> viewOrderReceiptWs", e);
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////////////////
    public Set<OrderDetailDTO> viewOrderReceiptWs(Integer patientId, String orderId, String handlingFee, String pref,
            String cardNumber, String cardType, String awardedPoints, String clearNameFlds, String paymentType, String comments, String source) {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        try {
            List<Order> dbOrders = patientProfileDAO.viewOrderReceiptList(patientId, orderId);
            if (!CommonUtil.isNullOrEmpty(dbOrders)) {
                list = setOrderList(dbOrders, null, handlingFee, pref, cardNumber, cardType, awardedPoints, clearNameFlds, paymentType, comments, source);
            }
            logger.info("New Order list size: " + list.size());
        } catch (Exception e) {
            logger.error("Exception -> viewOrderReceiptWs", e);
        }
        return list;
    }

    private Set<OrderDetailDTO> setOrderList(List<Order> dbOrders, String type, String handlingFee, String pref,
            String cardNumber, String cardType, String awardedPoints, String clearNameFlds) throws Exception {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        for (Order order : dbOrders) {
            OrderDetailDTO newOrder = new OrderDetailDTO();
            newOrder.setId(order.getId());
            if (AppUtil.getSafeStr(clearNameFlds, "").equals("1")) {
//                newOrder.setFirstName("");
//                newOrder.setLastName("");
            } else {
                CommonUtil.populateDecryptedOrderData(newOrder, order);
            }
            if (order.getCreatedOn() != null) {
                newOrder.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
                newOrder.setOrderTime(DateUtil.dateToString(order.getCreatedOn(), "hh:mm:ss"));
            }
            if (order.getOrderStatus() != null && order.getOrderStatus().getId() != null) {
                newOrder.setOrderStatusName(order.getOrderStatus().getName());
                if (order.getOrderStatus().getId() == 8) {
                    newOrder.setOrderType(1);
                } else {
                    newOrder.setOrderType(0);
                }
            }
//            newOrder.setStateTax(0f);
            newOrder.setRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "").length() > 0
                    ? AppUtil.getSafeStr(order.getRxPrefix(), "") + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "") : "");
            newOrder.setStrength(order.getStrength());
            if (CommonUtil.isNotEmpty(order.getDrugType()) && order.getDrugType().equalsIgnoreCase(Constants.TABLET)) {
                newOrder.setDrugType("TAB");
            } else if (order.getDrugType().equalsIgnoreCase(Constants.CAPSULE)) {
                newOrder.setDrugType("CAP");
            } else {
                newOrder.setDrugType(order.getDrugType());
            }
            newOrder.setQty(order.getQty());
            newOrder.setDrugPrice(order.getDrugPrice());
//            newOrder.setRedeemPoints(order.getRedeemPoints());
//            newOrder.setRedeemPointsCost(order.getRedeemPointsCost());
//            newOrder.setHandLingFee(order.getHandLingFee());
            //String 
            cardNumber = order.getCardNumber();
            logger.info("Card Number is: " + cardNumber);
            if (cardNumber != null && !"".equals(cardNumber) && cardNumber.length() > 4) {
                logger.info("card number is: " + cardNumber.substring(order.getCardNumber().length() - 4));
                newOrder.setCardNumber(cardNumber.substring(order.getCardNumber().length() - 4));
            } else {
                logger.info("Card Number length is less than 4: " + cardNumber);
                newOrder.setCardNumber(cardNumber);
            }
            if (order.getPayment() != null) {
                if (order.getPayment() > 0d) {
                    DecimalFormat decimalFormat = new DecimalFormat("#.###");
                    String formatPayment = decimalFormat.format(order.getPayment());
                    logger.info("formatPayment" + formatPayment);
                    newOrder.setPayment(Double.parseDouble(formatPayment));
                } else {
                    newOrder.setPayment(0.00d);
                }
            }

//            if (order.getApartment() != null && !"".equals(order.getApartment())) {
//                logger.info("Appartmnet is: " + order.getApartment());
//                newOrder.setShippingAddress(order.getStreetAddress() + " " + order.getApartment() + " " + order.getCity() + ", " + order.getState() + " " + order.getZip());
//            } else {
//                newOrder.setShippingAddress(order.getStreetAddress() + " " + order.getCity() + ", " + order.getState() + " " + order.getZip());
//            }
//
//            newOrder.setDeliveryPreferencesName(pref);
//            newOrder.setAwardedPoints(awardedPoints);

            getOrderRewardDetail(order.getPatientProfile().getPatientProfileSeqNo(), newOrder);
            list.add(newOrder);
        }
        return list;
    }

    private Set<OrderDetailDTO> setOrderList(List<Order> dbOrders, String type, String handlingFee, String pref,
            String cardNumber, String cardType, String awardedPoints, String clearNameFlds, String paymentType, String comments) throws Exception {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        for (Order order : dbOrders) {
            OrderDetailDTO newOrder = new OrderDetailDTO();
            newOrder.setId(order.getId());
//            if (AppUtil.getSafeStr(clearNameFlds, "").equals("1")) {
////                newOrder.setFirstName("");
////                newOrder.setLastName("");
//            } else {
//                newOrder.setFirstName(order.getFirstName());
//                newOrder.setLastName(order.getLastName());
//            }
            CommonUtil.populateDecryptedOrderData(newOrder, order);
            if (order.getCreatedOn() != null) {
                newOrder.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
                newOrder.setOrderTime(DateUtil.dateToString(order.getCreatedOn(), "hh:mm:ss"));
            }
            if (order.getOrderStatus() != null && order.getOrderStatus().getId() != null) {
                newOrder.setOrderStatusName(order.getOrderStatus().getName());
                if (order.getOrderStatus().getId() == 8) {
                    newOrder.setOrderType(1);
                } else {
                    newOrder.setOrderType(0);
                }
            }
//            newOrder.setStateTax(0f);
            newOrder.setRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "").length() > 0
                    ? AppUtil.getSafeStr(order.getRxPrefix(), "") + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "") : "");
            newOrder.setStrength(order.getStrength());
            newOrder.setDrugName(order.getDrugName());
            if (order.getDrugType().equalsIgnoreCase(Constants.TABLET)) {
                newOrder.setDrugType("TAB");
            } else if (order.getDrugType().equalsIgnoreCase(Constants.CAPSULE)) {
                newOrder.setDrugType("CAP");
            } else {
                newOrder.setDrugType(order.getDrugType());
            }
            newOrder.setQty(order.getQty());
            newOrder.setDrugPrice(order.getDrugPrice());
//            newOrder.setRedeemPoints(order.getRedeemPoints());
//            newOrder.setRedeemPointsCost(order.getRedeemPointsCost());
//            newOrder.setHandLingFee(order.getHandLingFee());
//            newOrder.setCardType(order.getCardType());
//            newOrder.setPaymentType(AppUtil.getSafeStr(paymentType, ""));
//            newOrder.setComments(AppUtil.getSafeStr(comments, ""));
            //String 
            cardNumber = order.getCardNumber();
            logger.info("Card Number is: " + cardNumber);
            if (cardNumber != null && !"".equals(cardNumber) && cardNumber.length() > 4) {
                logger.info("card number is: " + cardNumber.substring(order.getCardNumber().length() - 4));
                newOrder.setCardNumber(cardNumber.substring(order.getCardNumber().length() - 4));
            } else {
                logger.info("Card Number length is less than 4: " + cardNumber);
                newOrder.setCardNumber(cardNumber);
            }
            if (order.getPayment() != null) {
                if (order.getPayment() > 0d) {
                    DecimalFormat decimalFormat = new DecimalFormat("#.###");
                    String formatPayment = decimalFormat.format(order.getPayment());
                    logger.info("formatPayment" + formatPayment);
                    newOrder.setPayment(Double.parseDouble(formatPayment));
                } else {
                    newOrder.setPayment(0.00d);
                }
            }

            if (order.getApartment() != null && !"".equals(order.getApartment())) {
                logger.info("Appartmnet is: " + order.getApartment());
//                newOrder.setShippingAddress(order.getStreetAddress() + " " + order.getApartment() + " " + order.getCity() + ", " + order.getState() + " " + order.getZip());
            } else {
//                newOrder.setShippingAddress(order.getStreetAddress() + " " + order.getCity() + ", " + order.getState() + " " + order.getZip());
            }

//            newOrder.setDeliveryPreferencesName(pref);
//            newOrder.setAwardedPoints(awardedPoints);

            getOrderRewardDetail(order.getPatientProfile().getPatientProfileSeqNo(), newOrder);
            list.add(newOrder);
        }
        return list;
    }

    private Set<OrderDetailDTO> setOrderList(List<Order> dbOrders, String type, String handlingFee, String pref,
            String cardNumber, String cardType, String awardedPoints, String clearNameFlds, String paymentType, String comments, String source) throws Exception {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
//        try {
            logger.info("clearNameFlds: " + clearNameFlds + " pref:: " + pref + " cardNumber:: " + cardNumber + " awardedPoints:: " + awardedPoints + " paymentType:: " + paymentType + " comments:: " + comments + " source: " + source);
            for (Order order : dbOrders) {
                OrderDetailDTO newOrder = new OrderDetailDTO();
                newOrder.setId(order.getId());
//                newOrder.setIsCopayCard(order.getAddCopyCard());
//                if (AppUtil.getSafeStr(clearNameFlds, "").equals("1")) {
//                    newOrder.setFirstName("");
//                    newOrder.setLastName("");
//                } else if (order.getPatientProfile() != null) {
//                    newOrder.setFirstName(AppUtil.getSafeStr(order.getPatientProfile().getFirstName(), ""));
//                    newOrder.setLastName(AppUtil.getSafeStr(order.getPatientProfile().getLastName(), ""));
//                } else {
//                    newOrder.setFirstName("");
//                    newOrder.setLastName("");
//                }
                CommonUtil.populateDecryptedOrderData(newOrder, order);
                if (order.getCreatedOn() != null) {
                    newOrder.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
                    newOrder.setOrderTime(DateUtil.dateToString(order.getCreatedOn(), "hh:mm:ss"));
                }
                if (order.getOrderStatus() != null && order.getOrderStatus().getId() != null) {
                    newOrder.setOrderStatusName(order.getOrderStatus().getName().equalsIgnoreCase(Constants.ORDER_STATUS.PENDING) ? "Pharmacy Review Pending" : order.getOrderStatus().getName());
                    if (order.getOrderStatus().getId() == 8) {
                        newOrder.setOrderType(1);
                    } else {
                        newOrder.setOrderType(0);
                    }
                }
//                newOrder.setStateTax(0f);
                newOrder.setRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "").length() > 0
                        ? AppUtil.getSafeStr(order.getRxPrefix(), "") + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "") : "");
                newOrder.setStrength(order.getStrength());
                newOrder.setDrugName(order.getDrugName());
                if (CommonUtil.isNotEmpty(order.getDrugType())) {
                    if (order.getDrugType().equalsIgnoreCase(Constants.TABLET)) {
                        newOrder.setDrugType("TAB");
                    } else if (order.getDrugType().equalsIgnoreCase(Constants.CAPSULE)) {
                        newOrder.setDrugType("CAP");
                    }
                } else {
                    newOrder.setDrugType(order.getDrugType());
                }
                newOrder.setQty(order.getQty());
                newOrder.setDrugPrice(order.getDrugPrice());
//                newOrder.setRedeemPoints(order.getRedeemPoints());
                String redeemCostStr = "0.0";
                if (order.getRedeemPointsCost() != null) {
                    redeemCostStr = AppUtil.roundOffNumberToTwoDecimalPlaces(order.getRedeemPointsCost());
                }
//                newOrder.setRedeemPointsCost(AppUtil.getSafeDouble(redeemCostStr, 0d));//order.getRedeemPointsCost());
//                newOrder.setHandLingFee(order.getHandLingFee());
                newOrder.setCardType(order.getCardType());
//                newOrder.setPaymentType(AppUtil.getSafeStr(paymentType, ""));
//                newOrder.setComments(AppUtil.getSafeStr(comments, ""));
//                newOrder.setSource(AppUtil.getSafeStr(source, ""));
                //String 
                cardNumber = order.getCardNumber();
                logger.info("Card Number is: " + cardNumber);
                if (cardNumber != null && !"".equals(cardNumber) && cardNumber.length() > 4) {
                    logger.info("card number is: " + cardNumber.substring(order.getCardNumber().length() - 4));
                    newOrder.setCardNumber(cardNumber.substring(order.getCardNumber().length() - 4));
                } else {
                    logger.info("Card Number length is less than 4: " + cardNumber);
                    newOrder.setCardNumber(cardNumber);
                }
                if (order.getPayment() != null) {
                    if (order.getPayment() > 0d) {
                        DecimalFormat decimalFormat = new DecimalFormat("#.###");
                        String formatPayment = decimalFormat.format(order.getPayment());
                        logger.info("formatPayment" + formatPayment);
                        newOrder.setPayment(Double.parseDouble(formatPayment));
                    } else {
                        newOrder.setPayment(0.00d);
                    }
                }

                if (order.getApartment() != null && !"".equals(order.getApartment())) {
                    logger.info("Appartmnet is: " + order.getApartment());
//                    newOrder.setShippingAddress(order.getStreetAddress() + " " + order.getApartment() + " " + order.getCity() + ", " + order.getState() + " " + order.getZip());
                } else {
//                    newOrder.setShippingAddress(order.getStreetAddress() + " " + order.getCity() + ", " + order.getState() + " " + order.getZip());
                }
                if (CommonUtil.isNotEmpty(pref)) {
                    String name = AppUtil.getSafeStr(pref, "").toLowerCase();
//                    if (name.contains("2nd day")) {
//                        newOrder.setHandLingFee(0.0d);
//                        newOrder.setIncludedStr("Included");
//                    } else {
//                        newOrder.setIncludedStr("");
                    }
//                }

//                newOrder.setDeliveryPreferencesName(pref);
//                if (CommonUtil.isNotEmpty(awardedPoints)) {
//                    newOrder.setAwardedPoints(awardedPoints);
//                }
//
//                DrugDetail drugDetail = order.getDrugDetail();
//                if (drugDetail != null && drugDetail.getDrugDetailId() != null && drugDetail.getDrugDetailId() > 0) {
//                    DrugBasic drugBasic = drugDetail.getDrugBasic();
//                    DrugGeneric drugGeneric = drugBasic.getDrugGeneric();
//                    if (drugGeneric != null && drugGeneric.getDrugGenericID() != null) {
//                        newOrder.setBrandName(drugBasic.getBrandName());
//                    }
//                    if (drugGeneric != null && drugGeneric.getDrugGenericID() != null) {
//                        newOrder.setGenericName(drugGeneric.getGenericName());
//                    }
//                }
//                newOrder.setDaysToRefill("No Refillable yet.");
//                if (order.getPatientProfile() != null && order.getPatientProfile().getPatientProfileSeqNo() != null) {
//                    getOrderRewardDetail(order.getPatientProfile().getPatientProfileSeqNo(), newOrder);
//                }
//                newOrder.setMiles(order.getMiles());
//                newOrder.setPharmacyName(AppUtil.getSafeStr(order.getPharmacyName(), ""));
//                newOrder.setPharmacyPhone(AppUtil.getSafeStr(order.getPharmacyPhone(), ""));
//                newOrder.setPrescriberName(AppUtil.getSafeStr(order.getPrescriberLastName(), ""));
//                newOrder.setPrescriberPhone(AppUtil.getSafeStr(order.getPrescriberPhone(), ""));
//                List<OrderTransferImagesDTO> orderTransferImagesDTOs = populateOrderTransferImages(order);
//                newOrder.setOrderTransferImages(orderTransferImagesDTOs);
//                newOrder.setOrigRx(AppUtil.getSafeStr(order.getOldRxNumber(), ""));
//                newOrder.setIsBottleAvailable(order.getIsBottleAvailable() == null ? Boolean.FALSE : order.getIsBottleAvailable());
//                System.out.println("Pharmacy phone " + AppUtil.getSafeStr(newOrder.getPharmacyPhone(), ""));
//                System.out.println("ORIG RX " + AppUtil.getSafeStr(newOrder.getOrigRx(), ""));
//                DrugDetail detail = order.getDrugDetail();
//                if (detail != null) {
//                    newOrder.setImageURL(AppUtil.getSafeStr(detail.getImgUrl(), ""));
//                    newOrder.setDrugDocURL(AppUtil.getSafeStr(detail.getDrugDocUrl(), ""));
//                    newOrder.setPatientDocURL(AppUtil.getSafeStr(detail.getPdfDocUrl(), ""));
//                    System.out.println("IMAGE " + detail.getImgUrl() + " drug doc " + detail.getDrugDocUrl() + " patient doc" + detail.getPdfDocUrl());
//                }
                list.add(newOrder);
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("Exception::setOrderList:: ", e);
//        }
        return list;
    }

    public PatientDeliveryAddress getPatientDeliveryAddressById(int addressId) {
        PatientDeliveryAddress address = null;
        try {
            address = this.patientProfileDAO.getPatientDeliveryAddressById(addressId);
        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

    public DeliveryPreferences getDeliveryPreferenceById(int prefId) {
        DeliveryPreferences pref = null;
        try {
            pref = this.patientProfileDAO.getDeliveryPreferenceById(prefId);
        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pref;
    }

    public PatientDeliveryAddress getDefaultDeliveryPreference(int patientId) {
        PatientDeliveryAddress address = null;
        try {
            address = this.patientProfileDAO.getDefaultPatientDeliveryAddress(patientId);
        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

    /////////////////////////////////////////////////////////////////////////////
    private void populateOrderDetail(String patientId, List lst, List<OrderDetailDTO> orderDetailLst) throws Exception {
        if (orderDetailLst == null) {
            orderDetailLst = new ArrayList();
        }
        for (Object obj : lst) {
            OrderDetailDTO dto = new OrderDetailDTO();
            Order order = (Order) obj;

            String type = AppUtil.getSafeStr(order.getDrugType(), "");
            if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.TABLET)) {
                type = "TAB";
            } else if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.CAPSULE)) {
                type = "CAP";
            }
            dto.setId(order.getId());
            dto.setPatientId(patientId);
            dto.setDrugPrice(order.getDrugPrice() != null ? order.getDrugPrice() : 0d);
            dto.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
            dto.setStatusCreatedOn(order.getUpdatedOn());//.getStatusCreatedOn());
            Hibernate.initialize(order.getTransferDetail());
            dto.setRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "").length() > 0
                    ? AppUtil.getSafeStr(order.getRxPrefix(), "") + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "") : "");
//            dto.setRedeemPoints(order.getRedeemPoints());
//            dto.setRedeemPointsCost(order.getRedeemPointsCost());
//            dto.setDrugName(AppUtil.getSafeStr(order.getDrugName(), "") + " " + AppUtil.getSafeStr(order.getStrength(), ""));
//            dto.setQty(order.getQty());
//            dto.setHandLingFee(order.getHandLingFee());
//            dto.setPayment(order.getPayment());
//            dto.setFinalPayment(order.getFinalPayment() != null ? order.getFinalPayment() : 0d);
////            dto.setPointsEarned(order.getRewardHistorySet() != null && order.getRewardHistorySet().size() > 0 && order.getRewardHistorySet().get(0) != null && order.getRewardHistorySet().get(0).getPoint() != null ? order.getRewardHistorySet().get(0).getPoint() : 0);
//            dto.setRxAcqCost(order.getRxAcqCost() != null ? order.getRxAcqCost() : 0d);
//            dto.setRxReimbCost(order.getRxReimbCost() != null ? order.getRxReimbCost() : 0d);
//            dto.setOriginalPtCopay(order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d);
//            dto.setAdditionalMargin(order.getAdditionalMargin() != null ? order.getAdditionalMargin() : 0d);
////            dto.setProfitMargin(order.getProfitMargin() != null ? order.getProfitMargin() : 0d);
//            if (dto.getDrugPrice() != null && dto.getAdditionalMargin() != null) {
////                dto.setAmountWithoutMargin(dto.getDrugPrice() - dto.getAdditionalMargin());
//            } else {
////                dto.setAmountWithoutMargin(0d);
//            }
//            if (order.getFilledDate() != null && order.getRefillsRemaining() != null && order.getRefillsRemaining() > 0) {
//                dto.setNextRefillDate(DateUtil.addDaysToDate(order.getFilledDate(), dto.getDaysSupply()));
//            }
            if (order.getNextRefillDate() != null) {
                dto.setNextRefillDate(order.getNextRefillDate());//DateUtil.addDaysToDate(order.getFilledDate(), dto.getDaysSupply()));
            }
            dto.setFilledDate(order.getFilledDate());
            dto.setOrderStatusName(order.getOrderStatus().getName());
            //////////////////////////////////////////////
            try {
                String videoStr = AppUtil.getSafeStr(order.getVideo(), "").replace("localhost", Constants.APP_PATH_NON_SECURE);//"rxdirectdev.ssasoft.com");
                if (videoStr.length() > 0 && !videoStr.contains("http://") && !videoStr.contains("https://")) {
                    videoStr = "http://" + videoStr;
                }
//                dto.setPtVideo(videoStr);
                String imgStr = AppUtil.getSafeStr(order.getImagePath(), "").replace("localhost", Constants.APP_PATH_NON_SECURE);// "rxdirectdev.ssasoft.com");
                if (imgStr.length() > 0 && !imgStr.contains("http://") && !imgStr.contains("https://")) {
                    imgStr = "http://" + imgStr;
                }
//                dto.setTransferImage(imgStr);

                ////////////////////////////////////////////////////////
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception:: populateOrderDetail:: ", e);
            }
            /////////////////////////////////////////////

            orderDetailLst.add(dto);
            //dto.setAwardedPoints(order.getAwardedPoints());

        }
    }

    public List getPatientProfilesHistoryOtherThanPending(int patientId) throws Exception {
        List lst = this.patientProfileDAO.getPatientProfilesHistoryOtherThanPending(patientId);
        List<OrderDetailDTO> orderDetailLst = new ArrayList();
        populateOrderDetail("" + patientId, lst, orderDetailLst);
        return orderDetailLst;
    }

    public String getRequestVideo(String id) {
        TransferRequest req = (TransferRequest) this.patientProfileDAO.getObjectById(new TransferRequest(), AppUtil.getSafeInt(id, 0));
        return (req != null ? req.getVideo() : "");

    }

    public TransferRxQueueDTO getRequestVideoAndImage(String id) {
        TransferRequest req = (TransferRequest) this.patientProfileDAO.getObjectById(new TransferRequest(), AppUtil.getSafeInt(id, 0));
        TransferRxQueueDTO transfer = null;
        if (req != null) {
            transfer = new TransferRxQueueDTO();
            transfer.setTransferImage(AppUtil.getSafeStr(req.getDrugImg(), ""));
            transfer.setPtVideo(AppUtil.getSafeStr(req.getVideo(), ""));
        }
        return transfer;

    }

    ////////////////////////////////////////////////////////////////////////////////////
    public List<NotificationMessages> getNotificationForWaitingResponsesWs(Integer profileId) throws Exception {
        List<NotificationMessages> list = new ArrayList<>();
        List<NotificationMessages> listFromDb = patientProfileDAO.
                getNotificationMessagesListForWaitingResponses(profileId);
        for (NotificationMessages messages : listFromDb) {
            NotificationMessages notificationMessages = getNotificationMessages(messages);
//            notificationMessages.setMessageText(null);
            notificationMessages.setIsCritical(messages.getIsCritical());
            list.add(notificationMessages);
        }
        return list;
    }

    public List<NotificationMessages> getAllNotificationMessges(Integer profileId,  int isArchive) {
        List<NotificationMessages> list = new ArrayList<>();
        try {
            List<NotificationMessages> lstDb;
                 if(isArchive == 1){
                   lstDb = patientProfileDAO.getArchivedNotificationMessagesByProfileId(profileId);
                 }else {
                   lstDb = patientProfileDAO.getNonArchivedNotificationMessagesByProfileId(profileId);
                 }
            logger.info("Strt...Going to iterate List for (NotificationMessages messages : lstDb)"+new Date());
            for (NotificationMessages messages : lstDb) {
                NotificationMessages notificationMessages = getNotificationMessagesDetialByPatient(messages,"");

                notificationMessages.setMessageText(null);
                notificationMessages.setIsCritical(messages.getIsCritical());
                list.add(notificationMessages);
            }
             logger.info("End...Going to iterate List for (NotificationMessages messages : lstDb)"+new Date());
//            Collections.sort(list, CommonUtil.byDate);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getNotificationWs", e);
            e.printStackTrace();
        }
        return list;
    }

      public List<NotificationMessages> getNotificationPaginationWs(Integer profileId, Integer skip, int limit) {
        List<NotificationMessages> list = new ArrayList<>();
        try {

            List<NotificationMessages> lstDb = patientProfileDAO.getNotificationMessagesByProfileIdforPagination(profileId, skip, limit);
//             Collection<NotificationMessages> pagesList =  PatientProfileService.getPage(lstDb, size, pageNumber);
             logger.info("Strt...Going to iterate List for (NotificationMessages messages : lstDb)"+new Date());
            for (NotificationMessages messages : lstDb) {
                NotificationMessages notificationMessages = getNotificationMessagesDetialByPatient(messages,"");

                notificationMessages.setMessageText(null);
                notificationMessages.setIsCritical(messages.getIsCritical());
                list.add(notificationMessages);
            }
             logger.info("End...Going to iterate List for (NotificationMessages messages : lstDb)"+new Date());
//            Collections.sort(list, CommonUtil.byDate);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getNotificationWs", e);
            e.printStackTrace();
        }
        return list;
    }
//      public static Collection<NotificationMessages> getPage(Collection<NotificationMessages> c, Integer pageSize, int pageNumber) {
////    public public List<NotificationMessages> getPage(List<NotificationMessages> c, Integer pageSize) {
//        if (c == null) {
//            return Collections.emptyList();
//        }
//        List<NotificationMessages> list = new ArrayList<>(c);
//        if (pageSize == null || pageSize <= 0 || pageSize > list.size()) {
//            pageSize = list.size();
//        }
//        int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
//        List<NotificationMessages> pages = new ArrayList<>(numPages);
//        for (int pageNum = 0; pageNum < numPages;) {
////            pages.subList(0, 0);
//            pages.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size()));
////            list.add(pages);
//        }
//        return pages;
//    }
            public static Collection<NotificationMessages> getPage(Collection<NotificationMessages> c, Integer pageSize, int pageNumber) {
//    public public List<NotificationMessages> getPage(List<NotificationMessages> c, Integer pageSize) {
        if (c == null) {
            return Collections.emptyList();
        }
        List<NotificationMessages> list = new ArrayList<>(c);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size()) {
            pageSize = list.size();
        }
        List<NotificationMessages> pages = new ArrayList<>(pageNumber);
        for (int pageNum = 0; pageNum < pageNumber;) {
//            pages.subList(0, 0);
            pages.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size()));
//            list.add(pages);
        }
        return pages;
    }
    public static <NotificationMessages> List<List<NotificationMessages>> getPages(Collection<NotificationMessages> c, Integer pageSize) {
        if (c == null) {
            return Collections.emptyList();
        }
        List<NotificationMessages> list = new ArrayList<NotificationMessages>(c);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size()) {
            pageSize = list.size();
        }
        int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
        List<List<NotificationMessages>> pages = new ArrayList<List<NotificationMessages>>(numPages);
        for (int pageNum = 0; pageNum < numPages;) {
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        }
        return pages;
    }
    public JSONObject getNotificationMessagesTextById(Integer id) {
        JSONObject messagesJSON = new JSONObject();
        try {
            if (id != null) {
                List<NotificationMessages> dbList = patientProfileDAO.getNotificationMessagesListById(id);
                RewardPoints cRewardPoints = (RewardPoints) this.patientProfileDAO.findByPropertyUnique(new RewardPoints(), "type", "Read Critical Message", Constants.HIBERNATE_EQ_OPERATOR, "", 0);
                RewardPoints sRewardPoints = (RewardPoints) this.patientProfileDAO.findByPropertyUnique(new RewardPoints(), "type", "Read Standard Message", Constants.HIBERNATE_EQ_OPERATOR, "", 0);
                if (dbList != null && dbList.size() > 0) {

                    for (NotificationMessages messages : dbList) {
//                        RewardHistory rewardHistory = new RewardHistory();
//                        if (!messages.getIsRead()) {
//                            if (messages.getIsCritical() != null && messages.getIsCritical() == 1) {
//                                messages.setPointsAwarded(cRewardPoints.getPoint().intValue());
//                                rewardHistory.setPatientId(messages.getPatientProfile().getPatientProfileSeqNo());
//                                rewardHistory.setDescription(cRewardPoints.getType());
//                                rewardHistory.setType("PLUS");
//                                rewardHistory.setPoint(cRewardPoints.getPoint().intValue());
//                                rewardHistory.setOrder(ord);
//                            } else {
//                                messages.setPointsAwarded(sRewardPoints.getPoint().intValue());
//                                rewardHistory.setPatientId(messages.getPatientProfile().getPatientProfileSeqNo());
//                                rewardHistory.setDescription(sRewardPoints.getType());
//                                rewardHistory.setType("PLUS");
//                                rewardHistory.setPoint(sRewardPoints.getPoint().intValue());
//                                rewardHistory.setOrder(ord);
//                            }
//                        }
                        messages.setIsRead(Boolean.TRUE);
//                        this.patientProfileDAO.save(rewardHistory);
                        this.patientProfileDAO.update(messages);
                        NotificationMessages notificationMessages = getNotificationMessages(messages);
                        messagesJSON = getMessageJsonObject(notificationMessages);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getNotificationMessagesTextById", e);
        }
        return messagesJSON;
    }

    private JSONObject getMessageJsonObject(NotificationMessages notificationMessages) {
        JSONObject messagesJSON = new JSONObject();
        messagesJSON.put("id", notificationMessages.getId());
        messagesJSON.put("messageCategory", notificationMessages.getMessageCategory());
        messagesJSON.put("subject", EncryptionHandlerUtil.getDecryptedString(notificationMessages.getSubject()));
        messagesJSON.put("messageText", EncryptionHandlerUtil.getDecryptedString(notificationMessages.getMessageText()));
        messagesJSON.put("isRead", notificationMessages.getIsRead());
        messagesJSON.put("messageTypeId", notificationMessages.getMessageTypeId());
        messagesJSON.put("profileId", notificationMessages.getProfileId());
        messagesJSON.put("orderId", notificationMessages.getOrderId());
        messagesJSON.put("createdOn", notificationMessages.getCreatedOn());
        messagesJSON.put("rxNumber", notificationMessages.getRxNo());
        return messagesJSON;
    }

    public String getCalculateFinalPtPay(String redeemPoints, Integer patinetId, String orderId, String copay) {
        String json = "";
        try {
            Order order = (Order) getObjectById(new Order(), orderId);
            if (CommonUtil.isNotEmpty(redeemPoints)) {
                saveRewardHistory("Redeemed Compliance Rewards", Integer.parseInt(redeemPoints), patinetId, Constants.PLUS, order);
            }
            DrugDetail drugDetail = new DrugDetail();
            PatientProfile patientProfile = getPatientProfileById(patinetId);
            RewardPoints rewardPoints = getMyRewardsPoints(patientProfile.getPatientProfileSeqNo());

            FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getRecordByType(new FeeSettings(), Constants.PERPOINTVALUE);
            Double totalCopayPoints = (1 / feeSettings.getFee().doubleValue()) * AppUtil.getSafeDouble(copay, 0.0d);
            logger.info("totalCopayPoints:: " + totalCopayPoints);
            if (rewardPoints.getAvailablePoints() >= totalCopayPoints) {
                saveRewardHistory("Redeemed Compliance Rewards", totalCopayPoints.intValue(), patinetId, Constants.MINUS, order);
                drugDetail.setRedeemedPoints(totalCopayPoints.longValue());
                totalCopayPoints = feeSettings.getFee().doubleValue() * totalCopayPoints;
                drugDetail.setRedeemedPointsPrice(Float.parseFloat(CommonUtil.getDecimalFormat(totalCopayPoints)));
            } else {
                saveRewardHistory("Redeemed Compliance Rewards", rewardPoints.getAvailablePoints().intValue(), patinetId, Constants.MINUS, order);
                drugDetail.setRedeemedPoints(rewardPoints.getAvailablePoints());
                Double rewardPointCost = feeSettings.getFee().doubleValue() * rewardPoints.getAvailablePoints();
                drugDetail.setRedeemedPointsPrice(Float.parseFloat(CommonUtil.getDecimalFormat(rewardPointCost)));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(drugDetail);
        } catch (Exception e) {
            logger.error("Exception:: getCalculateFinalPtPay", e);
        }
        return json;
    }

    public Long getTotalRewardHistoryPoint(String type, String orderId, Integer patientId) {
        Long totalPoints = 0L;
        try {
            if (patientId != null) {
                totalPoints = patientProfileDAO.getTotalRewardHistoryPointByType(type, patientId);
            } else if (CommonUtil.isNotEmpty(orderId)) {
                totalPoints = patientProfileDAO.getTotalRewardHistoryPointByOrderId(type, orderId);
            }
        } catch (Exception e) {
            logger.error("Exception:: getTotalRewardHistoryPoint", e);
        }
        return totalPoints;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean saveNotificationMessages(CampaignMessages campaignMessages, Integer profileId, String orderId, Integer isCritical) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(AppUtil.getSafeStr(campaignMessages.getSubject(), ""));
                         if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(campaignMessages.getSmstext());
                } 
//                notificationMessages.setMessageText(campaignMessages.getSmstext());

                if (isCritical > 0) {
                    isCritical = 1;
                }
                notificationMessages.setIsCritical(isCritical);
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            //////////////////////////////////
            Order order = (Order) this.getObjectById(new Order(), orderId);
            notificationMessages.setOrders(order);
            /////////////////////////////////
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setPushSubject(AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""));
            notificationMessages.setIsEventFire(false);
            patientProfileDAO.save(notificationMessages);
            this.sendPushNotifications(profileId, order, notificationMessages,
                    AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), "RXD");
            isSaved = true;
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    private void sendPushNotifications(Integer profileId, Order order, NotificationMessages notificationMessages, String subject, String prefix) {
        try {
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null) {
                System.out.println("Profile for sending push " + profile.getFirstName());
                String osType = AppUtil.getSafeStr(profile.getOsType(), "20");
                if (osType.equals("20"))//android
                {
                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), order, notificationMessages, prefix, subject, profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), order, notificationMessages, prefix, subject, profile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public Set<OrderDetailDTO> saveRefillModule(Long orderChainId) throws Exception {
        Set<OrderDetailDTO> detailDTOs = new LinkedHashSet<>();

        RewardPoints rewardPoints = (RewardPoints) this.patientProfileDAO.findByPropertyUnique(new RewardPoints(), "type", "Rx Refill", Constants.HIBERNATE_EQ_OPERATOR, "", 0);

        List<Order> orderLst = this.orderDAO.getDeliveredOrderList(orderChainId);
        if (CommonUtil.isNullOrEmpty(orderLst)) {
            throw new Exception("No  orders has been DELIVERED/SHIPPED for this prescription.");
        }
        Order dbOrder = orderLst.get(0);//(Order) patientProfileDAO.findByPropertyUnique(new Order(), "orderChain.id", orderChainId, Constants.HIBERNATE_EQ_OPERATOR, "createdOn", Constants.HIBERNATE_DESC_ORDER);
        if (dbOrder.getRefillsAllowed() == null || dbOrder.getRefillsAllowed() == 0) {
            throw new Exception("Refill is not allowed.");
        }
        if (dbOrder.getRefillsRemaining() == null || dbOrder.getRefillsRemaining() == 0) {
            throw new Exception("No Refills remaining.");
        }
        if (dbOrder != null && dbOrder.getId() != null) {
            //dbOrder.setOrderType(Constants.REFILL);
            Order ord = new Order();
            BeanUtils.copyProperties(dbOrder, ord);
            OrderStatus status = new OrderStatus();
            status.setId(1);
            ord.setOrderStatus(status);
//            ord.setOrderHistory(null);
//            ord.setOrdersPtMessagesList(null);
//            ord.setRewardHistorySet(null);
            //                if (ord.getRefillsAllowed() != null && ord.getRefillsAllowed() > 0) {
//                    int refillRemining = ord.getRefillsAllowed() - 1;
//                    ord.setRefillsRemaining(refillRemining);
//                }
//            ord.setCoPayCardDetails(null);
            ord.setOrderType(Constants.REFILL);
            ord.setCreatedOn(new Date());
            ord.setRefillDone(1);
            patientProfileDAO.save(ord);

            OrderHistory orderHistory = new OrderHistory();
            OrderHistory dbOrderHistory = (OrderHistory) patientProfileDAO.findByPropertyUnique(new OrderHistory(), "order.id", dbOrder.getId(), Constants.HIBERNATE_EQ_OPERATOR);
            if (dbOrderHistory != null && dbOrderHistory.getId() != null) {
                BeanUtils.copyProperties(dbOrderHistory, orderHistory);
                orderHistory.setOrder(ord);
                patientProfileDAO.save(orderHistory);
            }
            List<Order> orderlist = new ArrayList<>();
            orderlist.add(ord);
            detailDTOs = populateOrderList(orderlist, null);
        }

        return detailDTOs;
    }

    public String saveRefillModuleFromWeb(String commaSeparatedOrderIds, int createdBy) throws Exception {
        String[] arr = commaSeparatedOrderIds.split(",");
        boolean flag = false;
        String id = "0";
        for (int i = 0; arr != null && i < arr.length; i++) {
            Order dbOrder = (Order) this.patientProfileDAO.findRecordById(new Order(), arr[i]);
//            Set<OrderDetailDTO> dtos = this.saveRefillModule(dbOrder, false, createdBy);
//            if (!flag) {
//                id = dtos.stream().findFirst().get().getId();
//                flag = true;
//            }
        }
        return id;
    }

    public Set<OrderDetailDTO> saveRefillModule(List<OrderDetailDTO> lstOrder, Integer patientId) throws Exception {
        Set orderSet = new HashSet();
        OrderBatch orderBatch = new OrderBatch();
        orderBatch.setCreatedOn(new Date());
        PatientProfile profile = new PatientProfile(patientId);
        orderBatch.setPatientProfile(profile);
        this.patientProfileDAO.saveOrUpdate(orderBatch);
        for (OrderDetailDTO dto : lstOrder) {
//            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(
//                    new PatientDeliveryAddress(), dto.getDeliveryAddressId());
            Order order = (Order) this.patientProfileDAO.findRecordById(new Order(), dto.getId());
            if (order != null) {
//                order.setQty(dto.getQty());
//                order.setOrderBatch(orderBatch);
//                if (dto.getDeliveryPreferenceId() != null && dto.getDeliveryPreferenceId() > 0) {
//                    order.setDeliveryPreference(new DeliveryPreferences(dto.getDeliveryPreferenceId()));
//                }
//                if (dto.getPaymentId() > 0) {
//                    order.setPaymentId(dto.getPaymentId());
//                }
//                /////////////////////////////////////////////////////////////
//                if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null
//                        && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
//                    logger.info("Set shipping address info. ");
//                    order.setStreetAddress(patientDeliveryAddress.getAddress());
//                    order.setCity(patientDeliveryAddress.getCity());
//                    order.setZip(patientDeliveryAddress.getZip());
//                    order.setAddressLine2(patientDeliveryAddress.getDescription());
//                    order.setApartment(patientDeliveryAddress.getApartment());
//                    if (patientDeliveryAddress.getState() != null
//                            && patientDeliveryAddress.getState().getId() != null) {
//                        order.setState(patientDeliveryAddress.getState().getName());
//                    }
//                }
                ////////////////////////////////////////////////////////////
                Set<OrderDetailDTO> set = this.saveRefillModule(order, dto, patientId, orderBatch);
                if (set != null && set.size() > 0) {
                    orderSet.add(set.iterator().next());
                }

//                order.setPaymentId(dto.);
                //this
            }
        }
        return orderSet;

    }

    public Set<OrderDetailDTO> saveRefillModule(Order dbOrder, OrderDetailDTO dto, Integer patientId,
            OrderBatch orderBatch) throws Exception {

        //RewardPoints rewardPoints =(RewardPoints)this.patientProfileDAO.findByPropertyUnique(new RewardPoints(),"type","Rx Refill",Constants.HIBERNATE_EQ_OPERATOR,"",0);
        //List<Order> orderLst=this.orderDAO.getDeliveredOrderList(orderChainId);
        if (dbOrder == null || (!dbOrder.getOrderStatus().getName().equalsIgnoreCase("DELIVERY")
                && !dbOrder.getOrderStatus().getName().equalsIgnoreCase("Shipped")
                && !dbOrder.getOrderStatus().getName().equalsIgnoreCase("Pickup At Pharmacy")
                && !dbOrder.getOrderStatus().getName().equalsIgnoreCase("Filled"))) {
            throw new Exception("Only delivered/shipped orders can be Refilled.");
        }

        if (dbOrder.getRefillsAllowed() == null || dbOrder.getRefillsAllowed() == 0) {
            throw new Exception("Refill is not allowed.");
        }
        if (dbOrder.getRefillsRemaining() == null || dbOrder.getRefillsRemaining() == 0) {
            throw new Exception("No Refills remaining.");
        }
        return this.saveRefillModule(dbOrder, dto, true, 0, patientId, orderBatch);

    }

    public Set<OrderDetailDTO> saveRefillModule(Long orderChainId, String orderId) throws Exception {

        //RewardPoints rewardPoints =(RewardPoints)this.patientProfileDAO.findByPropertyUnique(new RewardPoints(),"type","Rx Refill",Constants.HIBERNATE_EQ_OPERATOR,"",0);
        //List<Order> orderLst=this.orderDAO.getDeliveredOrderList(orderChainId);
        Order dbOrder = (Order) this.patientProfileDAO.findRecordById(new Order(), orderId);

        if (dbOrder == null || (!dbOrder.getOrderStatus().getName().equalsIgnoreCase("DELIVERY")
                && !dbOrder.getOrderStatus().getName().equalsIgnoreCase("Shipped") && !dbOrder.getOrderStatus().getName().equalsIgnoreCase(Constants.ORDER_STATUS.FILLED))) {
            throw new Exception("Only delivered/shipped orders can be Refilled.");
        }

        if (dbOrder.getRefillsAllowed() == null || dbOrder.getRefillsAllowed() == 0) {
            throw new Exception("Refill is not allowed.");
        }
        if (dbOrder.getRefillsRemaining() == null || dbOrder.getRefillsRemaining() == 0) {
            throw new Exception("No Refills remaining.");
        }
        //return this.saveRefillModule(dbOrder, true, 0);
        return null;
    }

    /**
     *
     * @param dbOrder order for which refill has to be carried out
     * @param refillViaApp true in case of placing order from app, false
     * otherwise.
     * @param createdBy user going to create refill, it will be 0 when refill
     * request is made from app
     * @return
     * @throws Exception
     */
    public Order saveRefillModule(Order dbOrder, boolean refillViaApp, int createdBy, JsonResponse jsonResponse) throws Exception {
        Order ord = new Order();
        if (dbOrder != null && dbOrder.getId() != null) {
            dbOrder.setLastRefillDate(new Date());
            dbOrder.setRefillDone(1);
            dbOrder.setViewStatus("Refill_Created");
            dbOrder.setUpdatedAt(new Date());
            this.patientProfileDAO.saveOrUpdate(dbOrder);
            this.updateOrderHistory(dbOrder.getId(), dbOrder.getOrderStatus().getId());
            //Mobile app wants to Archive Messge for clint after Refill Request of Notification Message (Time to Refill)
            NotificationMessages notificationMessage = getNotificationByOrderIdForRefillRequestFromIpad(dbOrder.getId());
           if (notificationMessage != null) {
                notificationMessage.setIsArchive(Boolean.TRUE);
                notificationMessage.setIsRead(Boolean.TRUE);
                this.update(notificationMessage);
            }
            if (dbOrder.getRefillsRemaining()== 0) { 
                 logger.info("...Start Renewal-Prescription create on Zero Refill...");
                ord =  createRefillOrder(dbOrder , ord, createdBy, "Allow");
               
            }else {
                 logger.info("...Refill created...");
                 ord = createRefillOrder(dbOrder , ord, createdBy, "");
                 
            }
            /* ===================Add Reward point and Reward History on Create Refill Order========================= */
            jsonResponse.setData(addRewardsPointSAndHistory(ord, ord.getPatientProfile(), 9));
            /* ================Update(e.g outOfPocket/balalnce)Reward point and Reward History on Create Refill Order======== */
            try {
                Date refillDueDate = DateUtil.addDaysToDate(DateUtil.formatDate(dbOrder.getCreatedAt(), Constants.DD_MM_YYYY), dbOrder.getDaysSupply());
                Date currentDate = DateUtil.formatDate(new Date(), Constants.DD_MM_YYYY);
                if (currentDate.compareTo(refillDueDate) <= 0) {
                    jsonResponse.setData(this.updateComplianceRewardPointAndRewardHistoryOnRefill(ord.getId(), ord.getPatientProfile(), Constants.REWARD_ACTIVTIES.REFILL_ON_TIME));
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("getRewardpoint ->PatientHeartPulseResult ", e);
            }
            /* ===============Update Reward point and Reward History as a Create new Order(refill also like a new order)============= */

        }

        return ord;
    }
      public Order createRefillOrder(Order dbOrder ,Order ord,int createdBy, String refillRemian)throws Exception{
    
            BeanUtils.copyProperties(dbOrder, ord);
            ord.setDrugDetail2(dbOrder.getDrugDetail2());
            OrderStatus status = new OrderStatus();
            status.setId(Constants.ORDER_STATUS.REFILL_ID);
            ord.setParentOrderId(dbOrder.getId());
            ord.setOrderStatus(status);
            ord.setRewardHistorySet(null);
            ord.setCreatedBy(createdBy);
            ord.setOrderType(Constants.REFILL);
            ord.setCreatedOn(new Date());
            ord.setCreatedAt(new Date());
            ord.setUpdatedAt(new Date());
            ord.setRxExpiredDate(dbOrder.getRxExpiredDate());
            ord.setShippedOn(null);
            ord.setShippedBy(null);
            ord.setTraclingno("");
            ord.setClerk("");
            ord.setDeliverycarrier("");
            ord.setRxProfitability(dbOrder.getRxProfitability());
            ord.setRxActivityMultiplier(dbOrder.getRxActivityMultiplier());
            ord.setRxPatientOutOfPocket(dbOrder.getRxPatientOutOfPocket());// its asistantAuth value 
            ord.setRxOutOfPocket(dbOrder.getRxOutOfPocket());// actuall RxPatientOutOfPocket value
            ord.setRxFinalCollect(dbOrder.getRxFinalCollect());
            ord.setRxSelling(dbOrder.getRxSelling());
            ord.setDaysSupply(dbOrder.getDaysSupply());
            ord.setPrescriberId(dbOrder.getPrescriberId());
            ord.setRxProfitability(dbOrder.getRxProfitability());
            ord.setPaymentMode(dbOrder.getPaymentMode());
            ord.setPrescriberName(dbOrder.getPrescriberName());
            ord.setPrescriberLastName(dbOrder.getPrescriberLastName());
            ord.setPrescriberPhone(dbOrder.getPrescriberPhone());
            ord.setRxActivityMultiplier(dbOrder.getRxActivityMultiplier());
            ord.setRxThirdPartyPay(dbOrder.getRxThirdPartyPay());
            ord.setPrescriberNPI(dbOrder.getPrescriberNPI());
            ord.setFinalPayment(dbOrder.getFinalPayment());
            ord.setRxFinalCollect(dbOrder.getRxFinalCollect());
            ord.setInsuranceType(dbOrder.getInsuranceType());
            ord.setQty(dbOrder.getQty());
            ord.setDrugType(dbOrder.getDrugType());
            ord.setStrength(dbOrder.getStrength());
            ord.setDrugName(dbOrder.getDrugName());
            ord.setBrandReference(dbOrder.getBrandReference());
            ord.setRefillDone(0);
            ord.setViewStatus(null);
            ord.setNextRefillFlag("0");
            
            if("Allow".equals(refillRemian)) { 
                /*Statement will always enter this boday if Refill Remianing is qill be zero so we will add renewal_presctopin as 0 as temporary value.*/
                ord.setRenewalPrescription(dbOrder.getRenewalPrescription()+1);
                ord.setRefillsRemaining(0); 
                 ord.setViewStatus("Rx_Renewal");
            }else {
              ord.setRefillsRemaining(dbOrder.getRefillsRemaining() > 0 ? dbOrder.getRefillsRemaining() - 1 : 0);
              ord.setRenewalPrescription(dbOrder.getRenewalPrescription());
            } 
            ord.setRefillCount(dbOrder.getRefillCount() + 1);
            ord.setLastRefillDate(new Date());
            ord.setLastReminderDate(null);
            ord.setRxOrigDate(new Date());
            ord.setServiceDate(new Date());
//            ord.setNextRefillDateOfPreviousOrder(dbOrder.getNextRefillDate());
            ord.setRxNumber(dbOrder.getRxNumber());
            ord.setBrandReference(dbOrder.getBrandReference());
            ord.setRxIngredientPrice(dbOrder.getRxIngredientPrice());
            float nextRefillDateDays = (dbOrder.getDaysSupply() * 83) / 100f;
            int nextRefillDateDay = Math.round(nextRefillDateDays);
            ord.setNextRefillDate(DateUtil.addDays(new Date(), nextRefillDateDay));
            ord.setPrescriberId(dbOrder.getPrescriberId());
//            ord.setOrderBatch(orderBatch);
            this.patientProfileDAO.save(ord);
            return ord;
}
    //////////////////////////////////////////////////////////////////////////////////////////////
    public Set<OrderDetailDTO> saveRefillModule(Order dbOrder, OrderDetailDTO dto, boolean refillViaApp,
            int createdBy, Integer patientId, OrderBatch orderBatch) throws Exception {
        Set<OrderDetailDTO> detailDTOs = new LinkedHashSet<>();
        DrugDetail detail = null;
//        OrderBatch orderBatch = new OrderBatch();
//        orderBatch.setCreatedOn(new Date());
//        PatientProfile profile = new PatientProfile(patientId);
//        orderBatch.setPatientProfile(profile);
//        this.patientProfileDAO.saveOrUpdate(orderBatch);
        if (dbOrder != null && dbOrder.getId() != null) {
            //dbOrder.setOrderType(Constants.REFILL);
            dbOrder.setLastRefillDate(new Date());
            dbOrder.setRefillDone(1);
            this.patientProfileDAO.saveOrUpdate(dbOrder);//.update(dbOrder);
            this.saveActivitesHistory(ActivitiesEnum.REFILL_REQUEST.getValue(), dbOrder.getPatientProfile(), dbOrder.getRxNumber(), "", "",dbOrder.getId());
//            dbOrder.setMessageResponseses(null);
//            dbOrder.setOrderTransferImages(null);
//            dbOrder.setNotificationMessageses(null);
//            dbOrder.setOrderCustomDocumentses(null);
            Order ord = new Order();
            BeanUtils.copyProperties(dbOrder, ord);
            if (CommonUtil.isNotEmpty(dto.getQty())) {
                ord.setQty(dto.getQty());
            } else {
                dto.setQty(dbOrder.getQty());
            }
            ord.setDrugDetail2(dbOrder.getDrugDetail2());
            if (dbOrder.getDrugDetail() != null) {
                detail = getGenericBasedDrugDetailInfo(dbOrder.getDrugDetail().getDrugDetailId(), AppUtil.getSafeInt(dto.getQty(), 0), dbOrder.getPatientProfile());
                if (detail != null) {
                    ord.setDrugDetail(dbOrder.getDrugDetail());
//                    ord.setRxAcqCost(detail.getRxAcqCost() != null ? detail.getRxAcqCost().doubleValue() : 0f);
//                    ord.setDrugPrice(1d * detail.getDrugCost());//detail.getBasePrice() * AppUtil.getSafeInt(ord.getQty(),0));
//                    ord.setPriceIncludingMargins(1d * detail.getTotalPrice());
                    if (!CommonUtil.isNullOrEmpty(detail.getRedeemedPoints())) {
                        ord.setRedeemPoints(detail.getRedeemedPoints().toString());
                    }
                    if (detail.getRedeemedPointsPrice() != null) {
                        ord.setRedeemPointsCost(1d * detail.getRedeemedPointsPrice());
                    }
//                    ord.setPayment(detail.getFinalDrugCost());
//                    ord.setFinalPayment(detail.getFinalDrugCost());
//                    ord.setPaymentExcludingDelivery(detail.getFinalDrugCost());
//                    ord.setEstimatedPrice(detail.getFinalDrugCost());
                }
            }
            //////////////////////////////////////////////////////////////
            ord.setRefillsRemaining(dbOrder.getRefillsRemaining() != null
                    && dbOrder.getRefillsRemaining() > 0 ? dbOrder.getRefillsRemaining() - 1 : 0);
            ord.setOrderBatch(orderBatch);
//            if (dto.getDeliveryPreferenceId() != null && dto.getDeliveryPreferenceId() > 0) {
//                ord.setDeliveryPreference(new DeliveryPreferences(dto.getDeliveryPreferenceId()));
//            }
//            if (dto.getPaymentId() > 0) {
//                ord.setPaymentId(dto.getPaymentId());
//            }
            //ord.setHandLingFee(dto.getHandLingFee() != null ? dto.getHandLingFee() : 0.0d);
            /////////////////////////////////////////////////////////////
            //Todo
//            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(
//                    new PatientDeliveryAddress(), dto.getDeliveryAddressId());
//            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null) {
////                        && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
//                logger.info("Set shipping address info. ");
//                ord.setStreetAddress(patientDeliveryAddress.getAddress());
//                ord.setCity(patientDeliveryAddress.getCity());
//                ord.setZip(patientDeliveryAddress.getZip());
//                ord.setAddressLine2(patientDeliveryAddress.getDescription());
//                ord.setApartment(patientDeliveryAddress.getApartment());
//                if (patientDeliveryAddress.getState() != null
//                        && patientDeliveryAddress.getState().getId() != null) {
//                    ord.setState(patientDeliveryAddress.getState().getName());
//                }
//            }
            /////////////////////////////////////////////////////////////
            OrderStatus status = new OrderStatus();
            status.setId(Constants.ORDER_STATUS.PENDING_ID);
            ord.setOrderStatus(status);
//          ord.setMessageResponseses(null);
            ord.setOrderTransferImages(null);
//            ord.setNotificationMessageses(null);
//            ord.setOrderCustomDocumentses(null);
            ord.setRewardHistorySet(null);
            ord.setCreatedBy(createdBy);
//                if (ord.getRefillsAllowed() != null && ord.getRefillsAllowed() > 0) {
//                    int refillRemining = ord.getRefillsAllowed() - 1;
//                    ord.setRefillsRemaining(refillRemining);
//                }
            //ord.setCoPayCardDetails(null);
            ord.setOrderType(Constants.REFILL);
            ord.setCreatedOn(new Date());
            ord.setCreatedAt(new Date());
            ord.setUpdatedAt(new Date());
            ord.setShippedOn(null);
            ord.setShippedBy(null);
            ord.setTraclingno("");
            ord.setClerk("");
            ord.setDeliverycarrier("");
            ord.setRefillDone(0);
            ord.setNextRefillFlag("0");
            ord.setLastRefillDate(null);
            ord.setLastReminderDate(null);

            //Todo
//            OrderChain orderChain = ord.getOrderChain();
//            if (orderChain != null) {
////                orderChain.setRefillAllow(ord.getRefillsAllowed());
//                orderChain.setRefillRemaing(orderChain.getRefillRemaing() > 0 ? orderChain.getRefillRemaing() - 1 : 0);
//            }
            List<OrderHistory> orderHistorys = populateOrderHistory(dbOrder, ord, status);
            if (!CommonUtil.isNullOrEmpty(orderHistorys)) {
                ord.setOrderHistory(orderHistorys);
            } else {
                ord.setOrderHistory(null);
            }
            patientProfileDAO.save(ord);
            //patientProfileDAO.saveOrUpdate(orderChain);
            /////////////////////////////////
//            if (refillViaApp) {
//                ord.setAwardedPoints(saveRewardOrderHistory(ord.getRedeemPoints(), ord.getPatientProfile(), ord, "Rx Refill", 6));
//            }
            /////////////////////////////////
            List<Order> orderlist = new ArrayList<>();
            orderlist.add(ord);
            detailDTOs = setOrderList2(orderlist, null, "");
        }

        return detailDTOs;
    }

    private List<OrderHistory> populateOrderHistory(Order dbOrder, Order ord, OrderStatus status) {
        List<OrderHistory> orderHistorys = new ArrayList<>();
        OrderHistory dbOrderHistory = (OrderHistory) patientProfileDAO.findByPropertyUnique(new OrderHistory(), "order.id", dbOrder.getId(), Constants.HIBERNATE_EQ_OPERATOR);
        if (dbOrderHistory != null && dbOrderHistory.getId() != null) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrder(ord);
            orderHistory.setComments(dbOrderHistory.getComments());
            orderHistory.setOrderStatus(status);
            orderHistory.setPatientResponseLog(dbOrderHistory.getPatientResponseLog());
            orderHistory.setResponseRequiredLog(dbOrderHistory.getResponseRequiredLog());
            orderHistory.setCreatedOn(new Date());
            orderHistory.setUpdatedOn(new Date());
            orderHistorys.add(orderHistory);
        }
        return orderHistorys;
    }

    public boolean saveQuestion(String orderId, String question, PatientProfile patientProfile, Integer notificationMsgId) {
        boolean isSaved = false;

        try {
            Order order = (Order) this.patientProfileDAO.findRecordById(new Order(), orderId);
            if (order != null) {
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setOrder(order);
                questionAnswer.setQuestion(question);
                questionAnswer.setQuestionTime(new Date());
                questionAnswer.setPatientProfile(patientProfile);

                if (!CommonUtil.isNullOrEmpty(notificationMsgId)) {
                    NotificationMessages notificationMessages = new NotificationMessages();
                    notificationMessages.setId(notificationMsgId);
                    questionAnswer.setNotificationMessages(notificationMessages);
                }

                patientProfileDAO.save(questionAnswer);
                resetAutoDeletionValues(order, order.getOrderStatus().getId());
                order.setPatientResponse(Constants.PATIENT_RESPONSES.PLACE_QUESTION);
                order.setResponseFullFilled("0");
                OrderStatus status = new OrderStatus();
                //status.setId(19);
                //As per client requirment change order status
                //status.setId(Constants.ORDER_STATUS.PENDING_ID);
                status.setId(Constants.ORDER_STATUS.PENDING_QUESTION_ID);
                order.setOrderStatus(status);
                patientProfileDAO.saveOrUpdate(order);
                isSaved = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            logger.error("Exception -> NotificationMessages", e);
        }
        return isSaved;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public boolean saveAnswer(String orderId, int questionId, String answer, int userId) {
        boolean isSaved = false;

        try {
            Order order = (Order) this.patientProfileDAO.findRecordById(new Order(), orderId);
            if (order != null) {
                QuestionAnswer questionAnswer = (QuestionAnswer) this.patientProfileDAO.findRecordById(new QuestionAnswer(), questionId);
                if (questionAnswer != null) {
//                    questionAnswer.setOrder(order);
                    questionAnswer.setAnswer(answer);
                    questionAnswer.setAnswerTime(new Date());
                    patientProfileDAO.save(questionAnswer);
                    resetAutoDeletionValues(order, order.getOrderStatus().getId());
//                    order.setPatientResponse(Constants.PATIENT_RESPONSES.PLACE_QUESTION);
                    order.setResponseFullFilled("1");
                    OrderStatus status = new OrderStatus();
                    status.setId(1);
                    order.setOrderStatus(status);
                    patientProfileDAO.saveOrUpdate(order);
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception -> NotificationMessages", e);
        }
        return isSaved;
    }
    ////////////////////////////////////////////////////////////////////////////////////

    public PatientProfileDTO getProfileListWs(String mobileNumber, Integer verificationCode) {
        PatientProfileDTO patientProfileDTO = new PatientProfileDTO();
        try {
            List<PatientProfile> patientProfilesList = patientProfileDAO.getPatientProfilesList(EncryptionHandlerUtil.getEncryptedString(mobileNumber), verificationCode);
            if (!CommonUtil.isNullOrEmpty(patientProfilesList)) {
                PatientProfile patientProfile = patientProfilesList.get(0);

                LoginDTO profile = CommonUtil.populateProfileUserData(patientProfile);
                patientProfileDTO.setUser(profile);
                List<PatientInsuranceDetails> patientInsuranceDetailses = patientProfileDAO.findByNestedProperty(new PatientInsuranceDetails(), "patientProfile.id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR, "", Constants.HIBERNATE_ASC_ORDER);
                patientProfileDTO.setInsurancecards(populatePatientInsuranceDetails(patientInsuranceDetailses));

                patientProfileDTO.setSaveSearches(getSearchesRecordList(patientProfile.getPatientProfileSeqNo()));

                InsuranceCardDataDTO insuranceCardDataDTO = populateInsuranceCardData(patientProfile);
                patientProfileDTO.setInsuranceCardData(insuranceCardDataDTO);

                patientProfileDTO.setAddress(getPatientDeliveryAddressesByProfileId(patientProfile.getPatientProfileSeqNo()));

                RewardPoints rewardPoints = getMyRewardsPoints(patientProfile.getPatientProfileSeqNo());
                patientProfileDTO.setAvailableRewardPoints(rewardPoints.getAvailablePoints());
                patientProfileDTO.setLifetimeRewardPoints(rewardPoints.getLifeTimePoints());
                patientProfileDTO.setAvailedRewardPoints(rewardPoints.getAvailedRewardPoints());
                patientProfileDTO.setTotalSavings(patientProfileDAO.getTotalRecords(new Order(), "patientProfile.id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_SUM_FUNCTION, "redeemPointsCost"));
            }
        } catch (Exception e) {
            logger.error("Exception:: getProfileListWs", e);
            e.printStackTrace();
        }
        return patientProfileDTO;
    }

    public PatientProfileDTO getProfileListWs(String mobileNumber) {
        PatientProfileDTO patientProfileDTO = new PatientProfileDTO();
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientProfileByMobileNumber(EncryptionHandlerUtil.getEncryptedString(mobileNumber));
            if (patientProfile != null) {     
                LoginDTO profile = this.papulateProfileUserData(patientProfile);

                patientProfileDTO.setUser(profile);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: getProfileListWs", e);
        }
        return patientProfileDTO;
    }

    public LoginDTO getProfileListWs(String mobileNumber, String status, String fcmId) {
        LoginDTO profile = new LoginDTO();
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientProfileByPhoneAndStatus(EncryptionHandlerUtil.getEncryptedString(mobileNumber), status);
            if (patientProfile != null && !CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                profile = CommonUtil.populateProfileUserData(patientProfile);
            }
        } catch (Exception e) {
            logger.error("Exception:: getProfileListWs", e);
        }
        return profile;
    }

    public InsuranceCardDataDTO populateInsuranceCardData(PatientProfile patientProfile) {
        InsuranceCardDataDTO insuranceCardDataDTO = new InsuranceCardDataDTO();
        insuranceCardDataDTO.setCardHolderRelationship(patientProfile.getCardHolderRelation());
        insuranceCardDataDTO.setInsuranceFrontCardPath(patientProfile.getInsuranceFrontCardPath());
        insuranceCardDataDTO.setInsuranceBackCardPath(patientProfile.getInsuranceBackCardPath());
        return insuranceCardDataDTO;
    }

    public OrderCountDTO getOrderCount(Integer patientProfileSeqNo) {
        OrderCountDTO orderCountDTO = new OrderCountDTO();
        try {
            orderCountDTO.setNewMsgCount(patientProfileDAO.getTotalUnReadNotificationMessages(patientProfileSeqNo));
            orderCountDTO.setUserNotificationCount(patientProfileDAO.getTotalUserNotificationCouunt(patientProfileSeqNo));
            orderCountDTO.setOldMsgCount(patientProfileDAO.getTotalReadNotificationMessages(patientProfileSeqNo));
            List<Order> orderList = patientProfileDAO.getOrderListByStatus(patientProfileSeqNo, Constants.ORDER_STATUS.MBR_TO_PAY_ID);
            if (!CommonUtil.isNullOrEmpty(orderList)) {
                orderCountDTO.setTotalWaitingPtResponseCount(orderList.size());
            }
            List<BusinessObject> bos = new ArrayList();
            BusinessObject bo = new BusinessObject("patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_EQ_OPERATOR);
            bos.add(bo);
            bo = new BusinessObject("isArchived", 0, Constants.HIBERNATE_EQ_OPERATOR);
            bos.add(bo);
//            orderCountDTO.setTotalSavedSearchesCount(patientProfileDAO.getTotalRecords(new DrugSearches(), "patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_COUNT_FUNCTION, "*"));
            orderCountDTO.setInsuranceCardCount(patientProfileDAO.getInsuranceCardsCount(patientProfileSeqNo));
            orderCountDTO.setAllergiesCount(patientProfileDAO.getTotalAllergiesCount(patientProfileSeqNo));
            orderCountDTO.setTotalDeliveryAddress(patientProfileDAO.getTotalDeliveryAddressByProfileId(patientProfileSeqNo));
            orderCountDTO.setTotalLifeTimeRewards(patientProfileDAO.getTotalLifeTimeRewardPoints(patientProfileSeqNo));
            orderCountDTO.setTotalAvailableRewardPoint(patientProfileDAO.getTotalAvailableRewardPoints(patientProfileSeqNo));
            //orderCountDTO.setTotalRefillAvailable(patientProfileDAO.getTotalRecords(new OrderChain(), "patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_SUM_FUNCTION, "refillAllow"));
            orderCountDTO.setTotalOrderCount(patientProfileDAO.getTotalRecords(new Order(), "patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_COUNT_FUNCTION, "*"));
            orderCountDTO.setTotalActivityCount(patientProfileDAO.TotalActivityCountbyPatientId(patientProfileSeqNo));
            orderCountDTO.setTotalSentOrderMessgeCount(patientProfileDAO.getCountSentMessageForOrder(patientProfileSeqNo));
            orderCountDTO.setTotalNewGenralQuestionCount(patientProfileDAO.getCountReadGenralQuestion(patientProfileSeqNo));
            orderCountDTO.setTotalNewOrderQuestionCount(patientProfileDAO.getCountReadMessageForOrder(patientProfileSeqNo));
            orderCountDTO.setTotalNewGenralQuestionReleventChatCount(patientProfileDAO.getGenralQuestionReleventChatCount(patientProfileSeqNo));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: getOrderCount", e);
        }
        return orderCountDTO;
    }

    public int updateDeleiveryPreferance(String orderId, String fee, String addressId, String deliveryPrefId) {
        int isUpdate = 0;
        try {
            Order order = this.getOrderById("" + orderId);
            DeliveryPreferences deliveryPreference = this.getDeliveryPreferenceById(AppUtil.getSafeInt(deliveryPrefId, 0));
            order.setHandLingFee(Double.parseDouble(fee));
            order.setDeliveryPreference(deliveryPreference);

            PatientDeliveryAddress patientDeliveryAddress = (PatientDeliveryAddress) patientProfileDAO.getObjectById(new PatientDeliveryAddress(), AppUtil.getSafeInt(addressId, 0));//getDefaultPatientDeliveryAddress(patientProfile.getId());
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null && patientDeliveryAddress.getDefaultAddress().equalsIgnoreCase("Yes")) {
                logger.info("Set shipping address info. ");
                order.setStreetAddress(patientDeliveryAddress.getAddress());
                order.setCity(patientDeliveryAddress.getCity());
                order.setZip(patientDeliveryAddress.getZip());
                order.setAddressLine2(patientDeliveryAddress.getDescription());
                order.setApartment(patientDeliveryAddress.getApartment());
                if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                    order.setState(patientDeliveryAddress.getState().getName());
                    isUpdate = 1;
                }
                patientProfileDAO.update(order);
            }
        } catch (Exception e) {
            logger.error("Exception:: updateDeleiveryPreferance", e);
        }
        return isUpdate;
    }

    public int updateDateTime(String orderId, Integer dPrefId, String date, String deliveryTo, String deliveryFrom) throws ParseException, Exception {

        Order order = this.getOrderById("" + orderId);

        Date toTime = DateUtil.stringToDate(deliveryTo, "HH:mm");
        Date fromTime = DateUtil.stringToDate(deliveryFrom, "HH:mm");

        order.setDeliveryTo(toTime);
        order.setDeliveryFrom(fromTime);
        order.setCustomerShippingDate(DateUtil.stringToDate(date, "mm-dd-yyyy"));
        if (dPrefId != null) {
            DeliveryPreferences pref = (DeliveryPreferences) this.patientProfileDAO.findRecordById(
                    new DeliveryPreferences(), dPrefId);
            if (pref != null) {
                order.setDeliveryPreference(pref);
            }
        }
        order.setPatientResponse("Preferred delivery time is between " + deliveryFrom + " and " + deliveryTo);
        patientProfileDAO.update(order);
//        this.saveQuestion(order, "Preferred delivery time is between " + deliveryFrom + " and " + deliveryTo + ".", order.getPatientProfile());
        return 1;
    }

    public int fillAsCash(int Id, int createdBy, int statusVal, String comments, String paymentMode) throws Exception {
        Order order = (Order) this.patientProfileDAO.findRecordById(new Order(), "" + Id);
        if (order != null) {
//            order.setPatientResponse(Constants.PATIENT_RESPONSES.FILL_AS_CASH);
            order.setUpdatedOn(new Date());
            order.setFinalPaymentMode("SELF PAY");
            order.setPatientResponse(Constants.PATIENT_RESPONSES.FILL_AS_CASH);
            order.setResponseFullFilled("0");
            OrderStatus status = new OrderStatus();
            status.setId(19);
            order.setOrderStatus(status);
//            this.saveQuestion(order, "Customer requested to fill as cash.", order.getPatientProfile());
            OrderHistory history = new OrderHistory();
            history.setOrder(order);
            history.setOrderStatus(order.getOrderStatus());
            history.setCreatedBy(createdBy);
            history.setUpdatedBy(createdBy);
            history.setCreatedOn(new Date());
            history.setUpdatedOn(new Date());
            history.setPatientResponseLog(Constants.PATIENT_RESPONSES.FILL_AS_CASH);
            patientProfileDAO.saveOrUpdate(history);
            order = resetAutoDeletionValues(order, statusVal);
            if (order != null) {
                patientProfileDAO.saveOrUpdate(order);
            }
        }
        return 1;
    }

    public Order resetAutoDeletionValues(Order order, Integer status) throws Exception {
//        Order order = this.findOrderById(orderId);

        if (order != null) {
//            OrderStatus orderStatus = new OrderStatus();
//            orderStatus = (OrderStatus) patientProfileDAO.findRecordById(new OrderStatus(), status);
//            order.setOrderStatus(orderStatus);
            order.setAutoDeletionDate(null);
            order.setAutoDeletionFlag(0);
//            patientProfileDAO.saveOrUpdate(order);

        }
        return order;
    }

    public int updatestopReminder(String orderId, Integer optOutRefillReminder) throws ParseException, Exception {
        //OrderChain orderChain = (OrderChain) patientProfileDAO.findRecordById(new OrderChain(),orderChainId );     
        Order order = this.getOrderById("" + orderId);
        if (order != null) {
            //OrderChain orderChain = order.getOrderChain();
            //orderChain.setOptOutRefillReminder(optOutRefillReminder);
            order.setOptOutRefillReminder(optOutRefillReminder);
            order.setUpdatedOn(new Date());
            patientProfileDAO.update(order);
            return 1;

        } else {

            return 0;
        }

    }

    public void saveYearEndStatementInfo(Integer patientProfileId, String fileName) {
        try {
            YearEndStatementInfo yearEndStatementInfo = new YearEndStatementInfo();
            yearEndStatementInfo.setPatientProfile(new PatientProfile(patientProfileId));
            yearEndStatementInfo.setFileName(fileName);
            yearEndStatementInfo.setYearOn(DateUtil.dateToString(new Date(), "yyyy"));
            yearEndStatementInfo.setCreatedOn(new Date());
            patientProfileDAO.save(yearEndStatementInfo);
        } catch (Exception e) {
            logger.error("Exception:: saveYearEndStatementInfo", e);
        }
    }

    public YearEndStatementInfo getYearEndStatementInfo(Integer patientProfileId) {
        YearEndStatementInfo yearEndStatementInfo = new YearEndStatementInfo();
        try {
            List<YearEndStatementInfo> endStatementInfos = patientProfileDAO.findByNestedProperty(new YearEndStatementInfo(), "patientProfile.id", patientProfileId, Constants.HIBERNATE_EQ_OPERATOR, null, 0);
            if (!CommonUtil.isNullOrEmpty(endStatementInfos)) {
                yearEndStatementInfo = endStatementInfos.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception:: getYearEndStatementInfo", e);
        }
        return yearEndStatementInfo;
    }

    public Set<DrugBrandDTO> getDrugBrandsOnlyList(String name) {

        Set<DrugBrandDTO> list = new HashSet<>();
        try {

            List<DrugBasic> drugBrandList = patientProfileDAO.retrieveDrugWithoutGeneric(name);

            for (DrugBasic drugBrand : drugBrandList) {
                DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
                drugBrandDTO.setId(drugBrand.getDrugBasicId());

                drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), ""));

                list.add(drugBrandDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getDrugBrandsList", e);
        }
        return list;
    }

    public String getDrugDetailByBrandName(String brandName, String genericName, Long drugGcn) {
        String response = "";
        try {
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("drugBasic.brandName", brandName, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            obj = new BusinessObject("drugBasic.drugGeneric.genericName", genericName, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            if (!CommonUtil.isNullOrEmpty(drugGcn)) {
                obj = new BusinessObject("drugGCN", drugGcn, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
            }

            List<DrugDetail> drugDetails = patientProfileDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
            List<DrugDetailDTO> drugDetailList = new ArrayList<>();
            for (DrugDetail dd : drugDetails) {
                DrugDetailDTO drugDetail = new DrugDetailDTO();
                drugDetail.setDrugDetailId(dd.getDrugDetailId());
                drugDetail.setDrugGCN(dd.getDrugGCN());
                drugDetail.setStrength(dd.getStrength());
                drugDetail.setFormDesc(dd.getDrugForm().getFormDescr());
                drugDetail.setDefQty(dd.getDefQty());
                drugDetailList.add(drugDetail);
            }
            response = new ObjectMapper().writeValueAsString(drugDetailList);
        } catch (Exception e) {
            logger.error("Exception:: getDrugDetailByBrandName", e);
        }
        return response;
    }

    public Set<DrugBrandDTO> getDrugBrandsListByBrandOrGenericName(String name) {

        Set<DrugBrandDTO> list = new HashSet<>();
        try {

            List<DrugBasic> drugGenericList = patientProfileDAO.retrieveDrugWithGeneric(name);
            List<DrugBasic> drugBrandList = patientProfileDAO.retrieveDrugWithoutGeneric(name);

            if ((drugGenericList == null && drugBrandList == null) || (CommonUtil.isNullOrEmpty(drugGenericList) && CommonUtil.isNullOrEmpty(drugBrandList))) {
                DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
                drugBrandDTO.setId(0);
                drugBrandDTO.setDrugBrandName(Constants.EMPTY_MESSAGE);
                list.add(drugBrandDTO);
                return list;
            }

            if (drugGenericList.size() > 0) {
                drugGenericList.stream().map((drugBrand) -> {
                    DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
                    drugBrandDTO.setId(drugBrand.getDrugBasicId());
                    //                    drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "") + "("
//                            + AppUtil.getSafeStr(drugBrand.getBrandName(), "") + ")");
                    if (AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "").equalsIgnoreCase("* BRAND NAME ONLY *")) {
                        // if (AppUtil.getSafeStr(d.getArchived(), "").equalsIgnoreCase("N"))
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + " "
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), ""));
                    } else {
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + "{"
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "") + "}");

                    }
                    return drugBrandDTO;
                }).forEach((drugBrandDTO) -> {
                    list.add(drugBrandDTO);
                });
            }

            if (drugBrandList.size() > 0) {
                logger.info("Drug Brand list size: " + drugBrandList.size());
                drugBrandList.stream().map((drugBrand) -> {
                    DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
                    drugBrandDTO.setId(drugBrand.getDrugBasicId());
                    //drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), ""));
                    if (AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "").equalsIgnoreCase("* BRAND NAME ONLY *")) {
                        // if (AppUtil.getSafeStr(d.getArchived(), "").equalsIgnoreCase("N")) 
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + " "
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), ""));
                    } else {
                        drugBrandDTO.setDrugBrandName(AppUtil.getSafeStr(drugBrand.getBrandName(), "") + "{"
                                + AppUtil.getSafeStr(drugBrand.getDrugGeneric().getGenericName(), "") + "}");

                    }
                    return drugBrandDTO;
                }).forEach((drugBrandDTO) -> {
                    list.add(drugBrandDTO);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception :: getDrugBrandsListByBrandOrGenericName", e);
        }
        return list;
    }

    public List<NotificationMessagesDTO> getInAppNotificationReport(BaseDTO baseDTO, boolean isPdf) {
        List<NotificationMessagesDTO> list = new ArrayList<>();
        try {
            String phoneNumber = baseDTO.getPhoneNumber();
            baseDTO.setPhoneNumber(EncryptionHandlerUtil.getEncryptedString(phoneNumber));
            List<NotificationMessages> notificationMessageses = patientProfileDAO.getInAppNotificationReport(baseDTO);
            if (!CommonUtil.isNullOrEmpty(notificationMessageses)) {
                notificationMessageses.stream().map((messages) -> getNotificationMessagesDTO(messages, isPdf)).forEach((notificationMessages) -> {
                    list.add(notificationMessages);
                });
            }
            baseDTO.setPhoneNumber(phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception :: getInAppNotificationReport", e);
        }
        return list;
    }

    public boolean savePatientPreference(Integer patientId, Integer preferenceSettingId, boolean preferenceValue) {
        boolean retVal = false;
        try {
            PatientProfilePreferences preference = null;
            List<BusinessObject> businessObjects = new ArrayList<>();
            businessObjects.add(new BusinessObject("patient.id", patientId, Constants.HIBERNATE_EQ_OPERATOR));
            businessObjects.add(new BusinessObject("preferenceSetting.id", preferenceSettingId, Constants.HIBERNATE_EQ_OPERATOR));
            List<PatientProfilePreferences> preferences = patientProfileDAO.findByProperty(new PatientProfilePreferences(), businessObjects, "", 0);

            if (preferences != null && preferences.size() > 0) {
                preference = preferences.get(0);
            } else {
                preference = new PatientProfilePreferences(new PatientProfile(patientId), new PreferencesSetting(preferenceSettingId));
            }
            preference.setPreferenceValue(preferenceValue);
            patientProfileDAO.savePatientPreference(preference);
            retVal = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception :: savePatientPreference", e);
        }
        return retVal;
    }

    public boolean addResponseToNotificationMessage(String responseDescription, Integer profileId, Integer messageId, String orderId) {
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            notificationMessages.setId(messageId);
            Order order = new Order();
            order.setId(orderId);
            MessageResponses messageResponses = new MessageResponses(responseDescription, new PatientProfile(profileId), notificationMessages, order);
            patientProfileDAO.save(messageResponses);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception :: addResponseToNotificationMessage", e);
        }
        return false;
    }

    private NotificationMessagesDTO getNotificationMessagesDTO(NotificationMessages messages, boolean isPdf) {
        NotificationMessagesDTO nm = new NotificationMessagesDTO();
        nm.setMessageId(messages.getId());
        if (messages.getOrders() != null && messages.getOrders().getId() != null) {
            nm.setOrderId(messages.getOrders().getId());
        }
        if (messages.getMessageType() != null && messages.getMessageType().getId() != null) {
            nm.setMessageTypeId(messages.getMessageType().getId().getMessageTypeId());
        }
        if (messages.getPatientProfile() != null && messages.getPatientProfile().getPatientProfileSeqNo() != null) {
            nm.setProfileId(messages.getPatientProfile().getPatientProfileSeqNo());
            nm.setMobileNumber(messages.getPatientProfile().getMobileNumber());
        }
        if (messages.getMessageResponses() != null && messages.getMessageResponses().size() > 0) {
            StringBuilder sb = new StringBuilder("");
            messages.getMessageResponses().forEach((mr) -> {
                if (!isPdf) {
                    sb.append(AppUtil.getSafeStr(mr.getResponseDescription(), "")).append("<br>");
                } else {
                    sb.append(AppUtil.getSafeStr(mr.getResponseDescription(), "")).append("\n");
                }
            });
            nm.setMessageResponses(sb.toString());
        }

        nm.setSubject(messages.getSubject());
        nm.setMessageText(CommonUtil.replaceNotificationMessagesPlaceHolder(messages.getMessageText(), messages.getCreatedOn()));
        nm.setCreatedOn(messages.getCreatedOn());
        nm.setTimeAgo(DateUtil.getDateDiffInSecondsFromCurrentDate(messages.getCreatedOn()));
        nm.setIsRead(messages.getIsRead());
        nm.setIsCritical(messages.getIsCritical() != null ? messages.getIsCritical() : 0);
        nm.setMessageCategory(Constants.ORDER_NOTIFICATION);
        return nm;
    }

    public List<DeliveryDistanceFeeDTO> getZipCodeCalculationsList(String zip, PatientProfile patientProfile, boolean pickedFromPharmacy) {
        List<DeliveryDistanceFeeDTO> deliveryDistanceFeeDTOs = new ArrayList<>();
        try {
            List<ZipCodeCalculation> zipCodeCalculationsList = patientProfileDAO.getZipCodeCalculationsList(zip, patientProfile.getPatientProfileSeqNo());
            if (!zipCodeCalculationsList.isEmpty() && zipCodeCalculationsList.size() > 0) {
                ZipCodeCalculation calculation = zipCodeCalculationsList.get(0);
                PharmacyZipCodes pharmacyZipCodes = this.getPharmacyZipCodes(pickedFromPharmacy);
                for (DeliveryDistanceFee deliveryDistanceFee : pharmacyZipCodes.getDeliveryDistanceFeesList()) {
                    DeliveryDistanceFeeDTO distanceFeeDTO = new DeliveryDistanceFeeDTO();
                    if (deliveryDistanceFee != null && deliveryDistanceFee.getId() != null) {
                        if (calculation.getMiles() >= deliveryDistanceFee.getDeliveryDistances().getDistanceFrom() && calculation.getMiles() <= deliveryDistanceFee.getDeliveryDistances().getDistanceTo()) {
                            if (calculation.getDeliveryFee() != null) {
                                logger.info("Delivery fee is: " + deliveryDistanceFee.getDeliveryFee());
                                distanceFeeDTO.setDeliveryFee(deliveryDistanceFee.getDeliveryFee());
                            }
                            if (deliveryDistanceFee.getDeliveryPreferenceses() != null && deliveryDistanceFee.getDeliveryPreferenceses().getId() != null) {
                                logger.info("DeliveryPreferenceId is:: " + deliveryDistanceFee.getDeliveryPreferenceses().getId());
                                distanceFeeDTO.setDprefaId(deliveryDistanceFee.getDeliveryPreferenceses().getId());
                                distanceFeeDTO.setName(deliveryDistanceFee.getDeliveryPreferenceses().getName());
                                String name = AppUtil.getSafeStr(deliveryDistanceFee.getDeliveryPreferenceses().getName(), "").toLowerCase();
                                if (name.contains("2nd day")) {
                                    distanceFeeDTO.setDeliveryFee(BigDecimal.valueOf(0));
                                    distanceFeeDTO.setIncludedStr("Included");
                                } else {
                                    distanceFeeDTO.setIncludedStr("");
                                }
                                distanceFeeDTO.setDescription(deliveryDistanceFee.getDeliveryPreferenceses().getDescription());
                                distanceFeeDTO.setPickedFromPharmacy(deliveryDistanceFee.getDeliveryPreferenceses().isPickedFromPharmacy());
                                distanceFeeDTO.setSeqNo(deliveryDistanceFee.getDeliveryPreferenceses().getSeqNo());
                            }

                            distanceFeeDTO.setMiles(calculation.getMiles());

                            if (!checkDuplicateRecordInList(deliveryDistanceFeeDTOs, distanceFeeDTO.getDprefaId())) {
                                deliveryDistanceFeeDTOs.add(distanceFeeDTO);
                            }

                        }
                    }
                }
            }

            Comparator<DeliveryDistanceFeeDTO> byName
                    = (DeliveryDistanceFeeDTO o1, DeliveryDistanceFeeDTO o2) -> Integer.compare(o1.getSeqNo(), o2.getSeqNo());

            Collections.sort(deliveryDistanceFeeDTOs, byName);
            //////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService -> getZipCodeCalculationsList", e);
        }
        return deliveryDistanceFeeDTOs;
    }

    public PharmacyZipCodes getPharmacyZipCodes(boolean pickedFromPharmacy) {
        PharmacyZipCodes pharmacyZipCodes = new PharmacyZipCodes();
        try {
            List<PharmacyZipCodes> list = patientProfileDAO.getPharmacyZipCodesList(pickedFromPharmacy);
            if (!list.isEmpty() && list.size() > 0) {
                pharmacyZipCodes = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getPharmacyZipCodes", e);
        }
        return pharmacyZipCodes;
    }

    public boolean saveMessageResponses(PatientProfile patientProfile, String orderId, Integer notificationMsgId, String msgResponse) {
        boolean isSaved = false;
        try {
            MessageResponses messageResponses = new MessageResponses();
            messageResponses.setPatientProfile(patientProfile);
            if (CommonUtil.isNotEmpty(orderId)) {
                Order order = getOrderById(orderId);
                messageResponses.setOrder(order);
                if (CommonUtil.isNotEmpty(msgResponse) && msgResponse.equalsIgnoreCase("Requested to cancel request")) {
                    OrderHistory history = new OrderHistory();
                    history.setOrder(order);
                    history.setComments(msgResponse);
                    OrderStatus orderStatus = (OrderStatus) patientProfileDAO.findByPropertyUnique(new OrderStatus(), "name", Constants.ORDER_STATUS.CANCELLED, Constants.HIBERNATE_EQ_OPERATOR);
                    history.setOrderStatus(orderStatus);
                    saveOrderHistory(history);
                }
            }
            if (!CommonUtil.isNullOrEmpty(notificationMsgId)) {
                NotificationMessages notificationMessages = (NotificationMessages) getObjectById(new NotificationMessages(), notificationMsgId);
                messageResponses.setNotificationMessages(notificationMessages);
            }
            messageResponses.setResponseDescription(msgResponse);
            messageResponses.setCreatedOn(new Date());
            patientProfileDAO.save(messageResponses);
            isSaved = true;
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception:: PatientProfileService:: saveMessageResponses", e);
        }
        return isSaved;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getMessageSubjectWithprocessedPlaceHolders(String messageSubject, String orderId, Practices dbPractise) {

        Order order = this.findOrderById(orderId);
        return CommonUtil.getMessageSubjectWithprocessedPlaceHolders(messageSubject, order, dbPractise);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public Order findOrderById(String id) {
        Order order = (Order) this.patientProfileDAO.findRecordById(new Order(), id);
        if (order != null) {
            Hibernate.initialize(order.getOrderStatus());
            Hibernate.initialize(order.getPatientProfile());
        }
        return order;
    }

    public Object findRecordById(Object obj, Integer id) {
        return this.patientProfileDAO.findRecordById(obj, id);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean saveNotificationMessages(CampaignMessages campaignMessages, Integer profileId, String orderId,
            Integer isCritical, Integer patientProfileMembersId) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(AppUtil.getSafeStr(campaignMessages.getSubject(), ""));
                        if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(campaignMessages.getSmstext());
                } 
//                notificationMessages.setMessageText(campaignMessages.getSmstext());

                if (isCritical > 0) {
                    isCritical = 1;
                }
                notificationMessages.setIsCritical(isCritical);
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            //////////////////////////////////
//            Order order = (Order) this.getObjectById(new Order(), orderId);
//            notificationMessages.setOrders(order);
            /////////////////////////////////
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setPushSubject(AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""));
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && notificationMessages != null) {
                if (AppUtil.getSafeStr(profile.getOsType(), "20").equals("20")) {

                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSaved = false;
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    public CampaignMessages getNotificationMsgsForDisapprove(String message, String eventName) {
        CampaignMessages campaignMessages = new CampaignMessages();
        try {
            //proccessing Y,YES,YEP ect.
            ValidResponse validResponse = textFlowDAO.getValidResponse(message);
            if (validResponse != null && validResponse.getVresponseId() != null) {
                Event event = textFlowDAO.getEventByStaticValue(eventName.trim());
                if (event == null) {
                    logger.info("No such event defined (System will return)");
                    return null;
                }
                EventHasFolderHasCampaigns eventHasFolderHasCampaigns = textFlowDAO.getEventHasFolderHasCampaign(Integer.parseInt(Constants.campaignId), event.getEventId(), Constants.SMS);
                if (eventHasFolderHasCampaigns == null) {
                    logger.info("No folder associated to this campaign (System will return)");
                    return null;
                }
                int folderId = eventHasFolderHasCampaigns.getFolderId();
                logger.info("Folder Id : " + folderId);
                List<CampaignMessages> campaignMessagesList = textFlowDAO.getCampaignMessagesByCommunicationType(Integer.parseInt(Constants.campaignId), folderId);
                if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                    logger.info("No messages found for (System will return)");
                    return null;
                }

                CampaignMessagesResponse campaignMessagesResponse = textFlowDAO.getCampaignMessagesResponseByResComm(Integer.parseInt(Constants.campaignId), folderId, validResponse.getResponse().getResponseTitle());
                if (campaignMessagesResponse == null) {
                    logger.info("No Campaign Messages Response.");
                    return null;
                }
                campaignMessages = campaignMessagesResponse.getCampaignMessages();
            }
        } catch (Exception e) {
            logger.error("Exception -> getNotificationMsgs", e);
        }
        return campaignMessages;
    }

    public String updatePatientAllergies(int pid, String allergies) throws Exception {
        PatientProfile patientProfile = (PatientProfile) this.patientProfileDAO.findRecordById(new PatientProfile(), pid);
        if (patientProfile != null) {
            if (AppUtil.getSafeStr(allergies, "").length() > Constants.ALLERGIES_MAX_LENGTH) {
                allergies = AppUtil.getSafeStr(allergies, "").substring(0, Constants.ALLERGIES_MAX_LENGTH);
            }
            patientProfile.setAllergies(allergies);
            this.patientProfileDAO.saveOrUpdate(patientProfile);
            return "Patient allergies have been updated successfully.";
        }
        return "Some error occurred while saving the record.";
    }

    public String saveRefill(String orderId, Double rxAcqCost, Double originalPtCopay, Double rxReimbCost,
            Integer profitSharePoint, Double actualProfitShare, Double paymentExcludingDelivery,
            int createdBy, int orderStatusId, Integer refillOverriden) throws Exception {
        String id = "0";
//        int refilloverriden = 0;
//        try {
        if (CommonUtil.isNotEmpty(orderId)) {
            Order dbOrder = (Order) this.patientProfileDAO.findRecordById(new Order(), orderId);

            if (dbOrder != null && dbOrder.getId() != null) {
                if (dbOrder.getOrderStatus() != null && dbOrder.getOrderStatus().getId() != null && (dbOrder.getOrderStatus().getId() == 5 || dbOrder.getOrderStatus().getId() == 6 || dbOrder.getOrderStatus().getId() == 8)) {
                    Set<OrderDetailDTO> dtos = this.saveRefillModule(dbOrder, rxAcqCost, originalPtCopay, rxReimbCost, profitSharePoint, actualProfitShare, paymentExcludingDelivery, false, createdBy, 1);
                    List<OrderDetailDTO> orderBatchList = orderDAO.getOrderBatchsList(dbOrder.getPatientProfile().getPatientProfileSeqNo(), orderId);
                    if (!CommonUtil.isNullOrEmpty(orderBatchList)) {
                        id = orderBatchList.stream().findFirst().get().getId();
                    } else {
                        id = dtos.stream().findFirst().get().getId();
                    }
                }
            }
        }

//        } catch (Exception e) {
//            logger.error("Exception:: saveFillRx:: ", e);
//        }
        return id;
    }

    private void populateOrderData(Order order, Double rxAcqCost, Double originalPtCopay, Double rxReimbCost, Integer profitSharePoint, Double actualProfitShare, Double paymentExcludingDelivery) {
        if (rxAcqCost != null) {
            order.setRxAcqCost(rxAcqCost);
        }
        if (originalPtCopay != null) {
            order.setOriginalPtCopay(originalPtCopay);
        }
        if (rxReimbCost != null) {
            order.setRxReimbCost(rxReimbCost);
        }
        if (profitSharePoint != null) {
            order.setProfitSharePoint(profitSharePoint);
        }
        if (actualProfitShare != null) {
            order.setActualProfitShare(actualProfitShare.floatValue());
        }
        if (paymentExcludingDelivery != null) {
            order.setPaymentExcludingDelivery(paymentExcludingDelivery);
        }
    }

    private Set<OrderDetailDTO> saveRefillModule(Order dbOrder, Double rxAcqCost, Double originalPtCopay, Double rxReimbCost, Integer profitSharePoint, Double actualProfitShare, Double paymentExcludingDelivery, boolean refillViaApp, int createdBy, int orderStatusId) throws Exception {
        Set<OrderDetailDTO> detailDTOs = new LinkedHashSet<>();
        DrugDetail detail = null;
        if (dbOrder != null && dbOrder.getId() != null) {
            //dbOrder.setOrderType(Constants.REFILL);
            dbOrder.setRefillDone(1);
            this.patientProfileDAO.update(dbOrder);
            Order ord = new Order();
            BeanUtils.copyProperties(dbOrder, ord);
            if (dbOrder.getDrugDetail() != null) {
                detail = getGenericBasedDrugDetailInfo(dbOrder.getDrugDetail().getDrugDetailId(), AppUtil.getSafeInt(dbOrder.getQty(), 0), dbOrder.getPatientProfile());
                if (detail != null) {
                    ord.setDrugPrice(1d * detail.getDrugCost());//detail.getBasePrice() * AppUtil.getSafeInt(ord.getQty(),0));
                    ord.setPriceIncludingMargins(1d * detail.getTotalPrice());
                    ord.setRedeemPoints(detail.getRedeemedPoints().toString());
                    ord.setRedeemPointsCost(1d * detail.getRedeemedPointsPrice());
                }
            }
            populateOrderData(ord, rxAcqCost, originalPtCopay, rxReimbCost, profitSharePoint, actualProfitShare, paymentExcludingDelivery);
            OrderStatus status = new OrderStatus();
            status.setId(orderStatusId);
            ord.setOrderStatus(status);
            ord.setRewardHistorySet(null);
            ord.setOrderTransferImages(null);
            ord.setCreatedBy(createdBy);
            if (ord.getRefillsAllowed() != null && ord.getRefillsAllowed() > 0) {
                int refillRemining = ord.getRefillsAllowed() - 1;
                ord.setRefillsRemaining(refillRemining);
            }
            ord.setOrderType(Constants.REFILL);
            ord.setCreatedOn(new Date());
            ord.setRefillDone(0);

            //OrderHistory orderHistory = new OrderHistory();
//            OrderHistory dbOrderHistory = (OrderHistory) patientProfileDAO.findByPropertyUnique(new OrderHistory(), "order.id", dbOrder.getId(), Constants.HIBERNATE_EQ_OPERATOR);
//            if (dbOrderHistory != null && dbOrderHistory.getId() != null) {
//                BeanUtils.copyProperties(dbOrderHistory, orderHistory);
//                orderHistory.setId(null);
            //orderHistory.setOrder(ord);
            //OrderStatus statusHistory = new OrderStatus();
            //statusHistory.setId(1);
            //orderHistory.setOrderStatus(statusHistory);
            //orderHistory.setCreatedBy(0);
            //orderHistory.setCreatedOn(new Date());
            //orderHistory.setUpdatedBy(0);
            //orderHistory.setUpdatedOn(new Date());
            //patientProfileDAO.save(orderHistory);
//            }
            List<OrderHistory> orderHistorys = populateOrderHistory(dbOrder, ord, status);
            if (!CommonUtil.isNullOrEmpty(orderHistorys)) {
                ord.setOrderHistory(orderHistorys);
            } else {
                ord.setOrderHistory(null);
            }
            patientProfileDAO.save(ord);
            /////////////////////////////////
            if (refillViaApp) {
                ord.setAwardedPoints(saveRewardOrderHistory(ord.getRedeemPoints(), ord.getPatientProfile(), ord, "Rx Refill", 6));
            }
            /////////////////////////////////
            List<Order> orderlist = new ArrayList<>();
            orderlist.add(ord);
            detailDTOs = populateOrderList(orderlist, null);
        }

        return detailDTOs;
    }

    public int savePrimeryOrders(String orderId, Integer paymentId, Integer deliveryPreferenceId) throws Exception {
//        Order order = new Order();
        Order order = this.orderDAO.getOrdersById("" + orderId);
        DeliveryPreferences deliveryPreference = new DeliveryPreferences();
        deliveryPreference = (DeliveryPreferences) patientProfileDAO.findRecordById(new DeliveryPreferences(), deliveryPreferenceId);
        try {
            order.setPaymentId(paymentId);
            order.setDeliveryPreference(deliveryPreference);
            patientProfileDAO.saveOrUpdate(order);
        } catch (Exception e) {
            logger.error("Exception -> saveRxOrderChainForTransfer", e);
        }
        return 1;
    }

    public long getOrdersCount(int orderStatus, Integer pharmacyId) throws Exception {

        return this.orderDAO.getCountAllOrders(orderStatus, pharmacyId);

    }

    public boolean deleteDrugImagesByOrderId(String orderId) {
        boolean isDelete = false;
        try {
            isDelete = patientProfileDAO.deleteDrugImages(orderId);
        } catch (Exception e) {

            logger.error("Exception: PatientProfileService -> deleteDrugImagesSearchesByOrderId", e);
        }
        return isDelete;
    }

//     public int Reminder(int seconds) throws Exception {
////        Order order = new Order();
//
//        try {
//
////            Timer timer = new Timer();
////            timer.schedule(new RemindTask(), seconds * 1000);
////            timer.cancel(); //Terminate the timer thread
//          Timer timer = new Timer();
//          timer.schedule(new RemindTask(), seconds*1000);
//	}
//
//         public void run() {
//            System.out.println("Time's up!");
//            timer.cancel(); //Terminate the timer thread
//        }
//    }
//
//     }
//       catch (Exception e) {
//            logger.error(e);
//        }
//        return 1;
//    }
    public boolean saveAllPreference(Integer patientId) throws Exception {
        boolean retVal = false;
        PatientProfilePreferences preference = null;
        PatientProfile patientProfile = (PatientProfile) patientProfileDAO.findRecordById(new PatientProfile(), patientId);
        List<PreferencesSetting> preferen = patientProfileDAO.getPreferenceSettingId();

        if (preferen != null && preferen.size() > 0) {
            for (PreferencesSetting ps : preferen) {
                preference = new PatientProfilePreferences();
                preference.setPatient(patientProfile);
                preference.setPreferenceValue(true);
                preference.setPreferenceSetting(ps);
                patientProfileDAO.save(preference);
                retVal = true;
            }
        }

        return retVal;
    }

    private DrugDetail populateDrugDetailCalculation(DrugDetail newDrug, int qty) {
        DrugDetail drug = new DrugDetail();
        if (newDrug != null && newDrug.getDrugDetailId() != null) {
            float drugProfitPercent = newDrug.getMarginPercent();
            float additionalFee = newDrug.getAdditionalFee();
            float basePrice = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(newDrug.getBasePrice()), 0f);
            float drugCost = basePrice * qty; //newDrug.getBasePrice() * qty;
            float drugProfit = drugCost * (drugProfitPercent / 100);
            float mktSurcharge = newDrug.getMktSurcharge() != null ? newDrug.getMktSurcharge() : 0.0f;
            float totalFee = drugCost + drugProfit + additionalFee + newDrug.getMktSurcharge();
//                drug.setTotalPrice(totalFee);
            float profitValue = totalFee - drugCost;
            float profitShare = profitValue * Constants.PROFIT_SHARE_PERCENT / 100;
            drug.setAdditionalFee(additionalFee);
            drug.setProfitValue(profitValue);
            drug.setProfitShare(profitShare);
            drug.setTotalPrice(totalFee);
            drug.setMktSurcharge(mktSurcharge);
            drug.setBasePrice(basePrice);
            drug.setDrugCost(drugCost);
            drug.setDrugProfit(drugProfit);
        }

        return drug;
    }

    public RefillOrderDTO getRefillRx(Integer profileId, String orderId, Integer dependentId) {
        RefillOrderDTO refillOrderDTO = new RefillOrderDTO();
        try {
            List<Integer> listOfOrderStatusIds = new ArrayList<>();
            listOfOrderStatusIds.add(Constants.ORDER_STATUS.FILLED_ID);
//            listOfOrderStatusIds.add(Constants.ORDER_STATUS.SHIPPED_ID);
              RewardPoints rewardPoints = null;
              Long availablePoints = 0l;
            //1). populate refillAble Orders

            PatientProfile dbpatientProfile = (PatientProfile) patientProfileDAO.findRecordById(new PatientProfile(), profileId);
            List<SurveyBridge> listsurvey = patientProfileDAO.getSurveyListByMobileNumber(dbpatientProfile.getMobileNumber());
            refillOrderDTO.setPendingActivities(this.getSurveyIDList(listsurvey));

            List<Order> refillAbleOrders = patientProfileDAO.getRefillableOrdersListByProfileId(profileId, dependentId, listOfOrderStatusIds);
            refillOrderDTO.setRefillAbleOrdersCount(!CommonUtil.isNullOrEmpty(refillAbleOrders) ? refillAbleOrders.size() : 0);
            if (!CommonUtil.isNullOrEmpty(refillAbleOrders)) {
                rewardPoints = getMyRewardsPoints(profileId);
                availablePoints = rewardPoints.getAvailablePoints();
                Set<OrderDetailDTO> refillAbleOrdersList = populateRefillAbleOrders(refillAbleOrders, orderId, availablePoints, rewardPoints);
                refillOrderDTO.setRefillAbleOrdersList(refillAbleOrdersList);
            }
            //2). populate Zero refill Orders
            List<Order> zeroRefillOrders = patientProfileDAO.getZeroRefillOrdersListByProfileId(profileId, dependentId, listOfOrderStatusIds);
            refillOrderDTO.setRxWithZeroRefillsCount(!CommonUtil.isNullOrEmpty(zeroRefillOrders) ? zeroRefillOrders.size() : 0);
            if (!CommonUtil.isNullOrEmpty(zeroRefillOrders)) {
                if (rewardPoints == null) {
                    rewardPoints = getMyRewardsPoints(profileId);
                    availablePoints = rewardPoints.getAvailablePoints();
                }
                Set<OrderDetailDTO> zeroRefillOrdersList = populateRefillAbleOrders(zeroRefillOrders, orderId, availablePoints, rewardPoints);
                refillOrderDTO.setRxWithZeroRefillsList(zeroRefillOrdersList);
            }

            //3). populate rx Expiring Soon
            List<Order> dbRxOrderExpireSoonList = patientProfileDAO.getRxExpireSoonListByPatientId(profileId, dependentId, listOfOrderStatusIds);
            refillOrderDTO.setRxExpiringSoonCount(!CommonUtil.isNullOrEmpty(dbRxOrderExpireSoonList) ? dbRxOrderExpireSoonList.size() : 0);
            if (!CommonUtil.isNullOrEmpty(dbRxOrderExpireSoonList)) {
                Set<OrderDetailDTO> rxOrderExpireSoonList = populateRxOrderExpireSoonList(dbRxOrderExpireSoonList, orderId);
                refillOrderDTO.setRxExpiringSoonList(rxOrderExpireSoonList);
            }
            //3). populate Total Order List Set<OrderDetailDTO> getFilledRxHistory
             List<Order> dbOrdersList = patientProfileDAO.getActiveOrdersListByProfileId(profileId);
            //Comparator<Order> cm1=Comparator.nullsLast(Comparator.comparing(Order::getUpdatedAt));  
//            Collections.sort(dbOrdersList);
            refillOrderDTO.setActiveOrderList(!CommonUtil.isNullOrEmpty(dbOrdersList) ? dbOrdersList.size() : 0);
            if (!CommonUtil.isNullOrEmpty(dbOrdersList)) {
                Set<OrderDetailDTO> list = setOrderList2(dbOrdersList, Constants.FILLED_RX_HISTORY, "");
                refillOrderDTO.setRxActiveOrdersList(list);
            }
//        Collections.sort(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getRefillRx", e);
        }
        return refillOrderDTO;
    }

    private Set<OrderDetailDTO> populateRxOrderExpireSoonList(List<Order> rxOrderExpireSoon, String orderId) {
        //      TODO          if (rewardPoints == null) {
//                    rewardPoints = getMyRewardsPoints(profileId);
//                    availablePoints = rewardPoints.getAvailablePoints();
//                }
        Set<OrderDetailDTO> rxOrderExpireSoonList = new LinkedHashSet<>();
        try {
            for (Order order : rxOrderExpireSoon) {

                Date currentDate = DateUtil.formatDate(new Date(), Constants.DATE_FORMATE_SHORT);
                long diffDays = DateUtil.dateDiffInDays(currentDate, order.getRxExpiredDate());
                logger.info("diffDays= " + diffDays);
                if (diffDays > 0 && diffDays <= 45) {
                    //                OrderDetailDTO newOrder=populateRefillRxDataWithCalculations(order, orderId,true);
                    OrderDetailDTO newOrder = populateRefillRxData(order, orderId);
                    //Todo
                    //drugCalculationAndRewardPoints(order, newOrder, availablePoints, rewardPoints);
                    rxOrderExpireSoonList.add(newOrder);
                }

            }
        } catch (Exception e) {
            logger.error("Exception# populateRxOrderExpireSoonList", e);
        }

        return rxOrderExpireSoonList;
    }

    public List<SurveyDto> getSurveyIDList(List<SurveyBridge> listsurvey) throws Exception {
        List<SurveyDto> list = new ArrayList<>();
        for (SurveyBridge lst : listsurvey) {
            SurveyDto SurveyDto = new SurveyDto();
            SurveyDto.setSurveyBridgeId(lst.getUniqueKey());
            list.add(SurveyDto);
        }
        return list;
    }

    private Set<OrderDetailDTO> populateRefillAbleOrders(List<Order> refillAbleOrders, String orderId, Long availablePoints, RewardPoints rewardPoints) throws Exception {
        Set<OrderDetailDTO> list = new LinkedHashSet<>();
        try {
            for (Order order : refillAbleOrders) {
                OrderDetailDTO newOrder = populateRefillRxData(order, orderId);
                newOrder.setRefillAbleCount(refillAbleOrders.size());
//                RxRenewal rxRenewal = patientProfileDAO.getRxRenewalRequestByOrderIdd(orderId);
//                if(rxRenewal!= null) {
//                    newOrder.setRenViewStatus(rxRenewal.getViewStatus());
//                }
                list.add(newOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void drugCalculationAndRewardPoints(Order order, OrderDetailDTO newOrder, Long availablePoints, RewardPoints rewardPoints) throws Exception {
        if (order.getDrugDetail() != null) {
            newOrder.setDrugId(order.getDrugDetail().getDrugDetailId() != null
                    ? order.getDrugDetail().getDrugDetailId() : 0L);

            DrugDetail drug = populateDrugDetailCalculation(order.getDrugDetail(), AppUtil.getSafeInt(order.getQty(), 0));
            availablePoints = getDrugsLookUpCalculation(drug, rewardPoints.getLifeTimePoints(), availablePoints);

//            newOrder.setAvailablePoints(availablePoints);
//            newOrder.setLifeTimePoints(rewardPoints.getLifeTimePoints());
//            newOrder.setFinalPayment(drug.getFinalPrice() != null ? drug.getFinalPrice().doubleValue() : 0d);
//            newOrder.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(newOrder.getFinalPayment(), "en", "us"));
//            newOrder.setTotalPayment(drug.getTotalPrice() != null ? drug.getTotalPrice().doubleValue() : 0d);
//            newOrder.setTotalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(newOrder.getTotalPayment(), "en", "us"));
//            newOrder.setRedeemPoints(drug.getRedeemedPoints() != null ? drug.getRedeemedPoints().toString() : "0");
//            newOrder.setRedeemPointsCost(drug.getRedeemedPointsPrice() != null ? drug.getRedeemedPointsPrice().doubleValue() : 0d);
//            newOrder.setBasePrice(drug.getBasePrice());
//            newOrder.setAdditionalMargin(AppUtil.getSafeDouble("" + drug.getAdditionalFee(), 0d));
//            newOrder.setDrugProfit(drug.getDrugProfit());
//            newOrder.setMktSurcharge(drug.getMktSurcharge() != null ? drug.getMktSurcharge() : 0d);
//            newOrder.setAcqCost(drug.getDrugCost());
//            double handlingFee = order.getHandLingFee() != null ? order.getHandLingFee() : 0d;
//            double finalPayment = newOrder.getFinalPayment() != null ? newOrder.getFinalPayment() : 0d;
//            double paymentIncludingShipping = finalPayment + handlingFee;
//            newOrder.setPaymentIncludingShipping(AppUtil.roundOffNumberToCurrencyFormat(paymentIncludingShipping,
//                    "en", "Us"));
//            newOrder.setPaymentIncludingRedmeenCost(AppUtil.roundOffNumberToCurrencyFormat(finalPayment + newOrder.getRedeemPointsCost(),
//                    "en", "Us"));
//            double drugCost = drug.getDrugCost() - newOrder.getRedeemPointsCost();
//            newOrder.setFinalDrugCost(AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(drugCost + handlingFee), 0d));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getDrugsLookUpCalculation(PatientProfile patientProfile, DrugDetail drug, RewardPoints rewardPoints) throws Exception {
        try {

            //calculatePointsFromProfitShare(drug);
            OrderDetailDTO detailDTO = getRedeemPointsWs(rewardPoints.getAvailablePoints().toString());
            //Double dp = drug.getTotalPrice() * 0.9;
            Double dp = drug.getTotalPrice().doubleValue();
            //logger.info("90% of Drug Price:: " + dp);
//            if (detailDTO.getRedeemPointsCost() <= dp) {
//                logger.info("Drug total Price:: " + drug.getTotalPrice() + " Total RedeemPointsCost:: " + detailDTO.getRedeemPointsCost());
//                getDrugLookUpCalculation(drug, detailDTO, rewardPoints, rewardPoints.getAvailablePoints(), dp);
//            } else {
//                FeeSettings feeSettings = (FeeSettings) patientProfileDAO.getRecordByType(new FeeSettings(), Constants.PERPOINTVALUE);
//                Double p = dp / feeSettings.getFee().doubleValue();
//                Long redemainPoints = Math.round(p);
//                logger.info("Round value:: " + redemainPoints);
//                detailDTO = getRedeemPointsWs(redemainPoints.toString());
//                getDrugLookUpCalculation(drug, detailDTO, rewardPoints, redemainPoints, dp);
//            }
        } catch (Exception e) {
            logger.error("Exception:: getDrugsLookUpCalculation", e);
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public List<OrderPlaceEmail> getOrderPlaceEmails() {
        List<OrderPlaceEmail> orderPlaceEmails = new ArrayList<>();
        try {
            orderPlaceEmails = patientProfileDAO.findByNestedProperty(new OrderPlaceEmail(), "isActive", Constants.YES, Constants.HIBERNATE_EQ_OPERATOR, "", 0);
        } catch (Exception e) {
            logger.error("Exception# getOrderPlaceEmails", e);
        }
        return orderPlaceEmails;
    }

    public String isPOAExpire(String orderId) {
        logger.info("Start method isPOAExpire# orderId " + orderId);
        String response = JsonResponseComposer.composeSuccessResponse();
        try {
            Order order = (Order) patientProfileDAO.findRecordById(new Order(), orderId);
            if (order == null || CommonUtil.isNullOrEmpty(order.getId())) {
                logger.info("There is no order found.");
                return response;
            }

            CampaignMessages campaignMessages = this.getNotificationMsgs("Update Caretaker POA", Constants.PHARMACY_NOTIFICATION);
            if (campaignMessages == null || CommonUtil.isNullOrEmpty(campaignMessages.getMessageId())) {
                logger.info("There are no caretaker msg exist.");
                return response;
            }
        } catch (Exception e) {
            response = JsonResponseComposer.composeFailureResponse(e.getMessage());
            logger.error("Exception# isPOAExpire# ", e);
            e.printStackTrace();
        }
        return response;
    }

    public void updateNotificationMessages(Integer notificationMsgId, Boolean isEventFire) {
        try {
            NotificationMessages notificationMessages = (NotificationMessages) patientProfileDAO.getObjectById(new NotificationMessages(), notificationMsgId);
            if (notificationMessages != null && notificationMessages.getId() != null) {
                notificationMessages.setIsEventFire(isEventFire);
                patientProfileDAO.update(notificationMessages);
            }
        } catch (Exception e) {
            logger.error("Exception# updateNotificationMessages# ", e);
        }
    }

    public String getQuestionAnswerById(Integer id) {
        String json = "";
        try {
            List<QuestionAnswerDTO> questionDTOLst = populateQuestionAnswerDetail(id);
            json = JsonResponseComposer.composeSuccessResponse(questionDTOLst);
        } catch (Exception e) {
            logger.error("Exception#", e);
        }
        return json;
    }

    public List<QuestionAnswerDTO> populateQuestionAnswerDetail(Integer questionId) {
        List<QuestionAnswerDTO> questionDTOLst = new ArrayList<>();
        try {
            List<QuestionAnswer> questionsList = this.patientProfileDAO.findByNestedProperty(new QuestionAnswer(), "id", questionId, Constants.HIBERNATE_EQ_OPERATOR, "id", Constants.HIBERNATE_DESC_ORDER);
            for (QuestionAnswer answer : questionsList) {
                QuestionAnswerDTO dto = new QuestionAnswerDTO();
//                dto.setId(answer.getId());
                  dto.setQuestionId(answer.getId());
                dto.setQuestion(answer.getQuestion());
                if (answer.getPatientProfile() != null && !CommonUtil.isNullOrEmpty(answer.getPatientProfile().getPatientProfileSeqNo())) {
                    dto.setPatientName((answer.getPatientProfile().getFirstName() + " " + answer.getPatientProfile().getLastName()).toUpperCase());
                    String queuePatientDetailPage = "<a href=\"/ConsumerPortal/queuePatientDetailPage/" + answer.getPatientProfile().getPatientProfileSeqNo() + "/0/tab1\" target='_blank'>" + dto.getPatientName() + "</a>";
                    dto.setPatientName(queuePatientDetailPage);
                    dto.setPatientPhoneNumber(answer.getPatientProfile().getMobileNumber());
                    dto.setPatientEmail(answer.getPatientProfile().getEmailAddress());

                    if (Integer.toString(Constants.OS_TYPE.IOS).equals(answer.getPatientProfile().getOsType())) {
                        dto.setOsType(Constants.APP_TYPE.IOS);
                    }
                    if (Integer.toString(Constants.OS_TYPE.ANDROID).equals(answer.getPatientProfile().getOsType())) {
                        dto.setOsType(Constants.APP_TYPE.ANDROID);
                    }

                }
                if (answer.getNotificationMessages() != null && !CommonUtil.isNullOrEmpty(answer.getNotificationMessages().getId())) {
                    String subject = answer.getNotificationMessages().getSubject();
                    logger.info("Subject# " + subject);
                    if (CommonUtil.isNotEmpty(subject) && !subject.contains("<<") && !subject.contains("div")) {
                        dto.setMsgTitle(subject);
                    }

                }

                if (CommonUtil.isNullOrEmpty(dto.getMsgTitle())) {
                    if (answer.getOrder() != null && CommonUtil.isNotEmpty(answer.getOrder().getId())) {
                        dto.setMsgTitle(answer.getOrder().getOrderType());
                    }
                }
                dto.setQuestionTime(answer.getQuestionTime());
                String hourMint = answer.getQuestionTime() != null ? DateUtil.dateToString(answer.getQuestionTime(), "HH:mm") : "";
                String sec = answer.getQuestionTime() != null ? DateUtil.dateToString(answer.getQuestionTime(), "ss") : "";
                if (CommonUtil.isNotEmpty(hourMint) && CommonUtil.isNotEmpty(sec)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(hourMint);
                    sb.append(":");
                    sb.append("<small>");
                    sb.append(sec);
                    sb.append("</small>");
                    sb.append("<a href=\"#\">");
                    sb.append(dto.getPatientName());
                    sb.append("</a>");
                    dto.setQuestionTimeStr(sb.toString());
                }
                if (answer.getOrder() != null) {
                    dto.setSystemGeneratedRxNumber(AppUtil.getSafeStr(answer.getOrder().getSystemGeneratedRxNumber(), "").length() > 0 ? AppUtil.getSafeStr(answer.getOrder().getSystemGeneratedRxNumber(), "") : "" + answer.getOrder().getId());
                    dto.setSystemRxNumberLabel(AppUtil.getSafeStr(answer.getOrder().getSystemGeneratedRxNumber(), "").length() > 0 ? "Rx#" : "Req#");
                }
                questionDTOLst.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# populateQuestionAnswerDetail", e);
        }
        return questionDTOLst;
    }

    public LoginDTO getPatientProfileDetailByToken(String token) {
        LoginDTO patientProfileDTO = new LoginDTO();
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientProfileByToken(token);
            if (patientProfile != null && !CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                patientProfileDTO = CommonUtil.populateProfileUserData(patientProfile);
                this.populateDependentAndIsuranceCountsForPatient(patientProfile.getPatientProfileSeqNo(), patientProfileDTO, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getPatientProfileDetailByToken", e);
        }
        return patientProfileDTO;
    }

    public LoginDTO getPatientProfileDetailByToken(String token, Integer dependentId) {
        LoginDTO patientProfileDTO = new LoginDTO();
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientProfileByToken(token);
            if (patientProfile != null && !CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                //populate PatientProfile User Data
                patientProfileDTO = CommonUtil.populateProfileUserData(patientProfile);
                patientProfileDTO.setFirstName(patientProfileDTO.getFirstName() + " " + patientProfileDTO.getLastName());
                //populate Dependent and Isurance Counts For Patient
                this.populateDependentAndIsuranceCountsForPatient(patientProfile.getPatientProfileSeqNo(), patientProfileDTO, dependentId);
                //populate DeliveryPreference data
                //populate patient insurance card details
                patientProfileDTO.setInsuranceBackCardPath(patientProfile.getInsuranceBackCardPath());
                patientProfileDTO.setInsuranceFrontCardPath(patientProfile.getInsuranceFrontCardPath());
                patientProfileDTO.setCardHolderRelation(patientProfile.getCardHolderRelation());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception # getPatientProfileDetailByToken(String token, Integer dependentId) ", e);
        }
        return patientProfileDTO;
    }

    private void populateDependentAndIsuranceCountsForPatient(Integer patientProfileId, LoginDTO patientProfileDTO, Integer dependentId) throws Exception {
        patientProfileDTO.setDependentCount(0L);
        if (!CommonUtil.isNullOrEmpty(dependentId)) {
            patientProfileDTO.setDependentInsCardCount(0L);
        } else {
            Long insCount = this.patientProfileDAO.populateInsCardCount(patientProfileId);
            patientProfileDTO.setInsCardCount(insCount != null ? insCount : 0L);
        }
    }

    public LoginDTO getPatientProfileDataById(Integer id) {
        LoginDTO profile = new LoginDTO();
        try {
            PatientProfile patientProfile = patientProfileDAO.getPatientProfile(id);
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                List<PatientInsuranceDetails> listOfInsuranceDetails = patientProfileDAO.populateInsCardList(profile.getId());
                if (!CommonUtil.isNullOrEmpty(listOfInsuranceDetails)) {
                    for (PatientInsuranceDetails insuranceDetails : listOfInsuranceDetails) {
                        if (insuranceDetails.getIsPrimary() == 1) {
                            profile.setInsuranceDetailId(insuranceDetails.getId());
                            profile.setInsuranceFrontCardPath(insuranceDetails.getInsuranceFrontCardPath());
                            profile.setInsuranceBackCardPath(insuranceDetails.getInsuranceBackCardPath());
                            break;
                        }
                    }
                }
                profile = CommonUtil.populateProfileUserData(patientProfile);

                Long totalRewardPoint = patientProfileDAO.getTotalRewardHistoryPoints(id);
                if (totalRewardPoint != null) {
                    profile.setTotalRewardPoints(totalRewardPoint);
                } else {
                    profile.setTotalRewardPoints(0L);
                }

                List<PatientDeliveryAddress> deliveryAddressList = patientProfile.getPatientDeliveryAddresses();
                profile.setPatientDeliveryAddresses(deliveryAddressList);
                if (deliveryAddressList != null && deliveryAddressList.size() > 0) {
                    PatientDeliveryAddress address = deliveryAddressList.get(0);
                    profile.setDefaultAddress(AppUtil.getSafeStr(address.getDescription(), "") + " " + AppUtil.getSafeStr(address.getAddress(), "") + ":"
                            + AppUtil.getSafeStr(address.getZip(), ""));
                    profile.setDefaultAddresszip(AppUtil.getSafeStr(address.getZip(), ""));
                }
                List<Order> ordersList = patientProfileDAO.getOrdersListByProfileId(id);
                if (ordersList.size() > 0) {
                    profile.setOrders(ordersList);
                } else {
                    profile.setOrders(ordersList);
                }
            } else {
                logger.info("PatientProfile id is null: " + id);
            }
        } catch (Exception e) {
            logger.error("Exception: PatientProfileService -> getPatientProfileDataById", e);
        }
        return profile;
    }

    public String processSameDayShippingRxOrders(String orderIds) {
        String json = "";
        try {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            List<String> lst = Arrays.asList(orderIds.split(","));
            Double handLingFee = 0.0d, finalPayment = 0.0d, totalAmount = 0.0d;
            List<Order> listOfOrders = patientProfileDAO.getOrderListByIds(lst);
            for (Order order : listOfOrders) {
                //(Order) patientProfileDAO.findRecordById(new Order(), id);
                Double handlingFee = order.getHandLingFee() != null ? order.getHandLingFee() : 0d;
                handLingFee = handLingFee + handlingFee;
//                orderDetailDTO.setHandLingFee(handLingFee);

//                orderDetailDTO.setHandlingFeeStr(AppUtil.roundOffNumberToCurrencyFormat(handlingFee, "en", "Us"));

                Double finalPyment = order.getFinalPayment() != null ? order.getFinalPayment() : 0d;
                finalPayment = finalPayment + finalPyment;
//                orderDetailDTO.setPaymentExcludingDeliveryStr(AppUtil.roundOffNumberToCurrencyFormat(finalPayment, "en", "Us"));

                totalAmount = finalPyment + totalAmount; //finalPyment + handLingFee + totalAmount;
                orderDetailDTO.setFinalPayment(totalAmount);
                orderDetailDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(totalAmount, "en", "Us"));
            }
//            orderDetailDTO.setTotalRxShipped((long) lst.size());
            json = JsonResponseComposer.composeSuccessResponse(orderDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: processSameDayShippingRxOrders", e);
        }
        return json;
    }

    public List<Order> getProcessedOrdersByPatientId(Integer patientId) {
        List<Order> listOfOrderDetail = new ArrayList<>();
        try {
            List<Integer> lstStatus = CommonUtil.generateStatusIdsList();
            listOfOrderDetail = patientProfileDAO.getRxProgramOrdersByPatientId(patientId, lstStatus);
        } catch (Exception e) {
            logger.error("Exception:: getProcessedOrdersByPatientId", e);
        }
        return listOfOrderDetail;
    }

    public boolean isConfirmReadyToDeliveryRxOrders(List<ReadyToDeliveryRxDTO> lstReadyToDeliveryRx, MultipartFile signature) {
        logger.info("Start ReadyToDeliveryRxOrders process " + lstReadyToDeliveryRx.size());
        boolean isSaved = false;
        try {
            for (ReadyToDeliveryRxDTO readyToDeliveryRx : lstReadyToDeliveryRx) {
                logger.info("Order Id# " + readyToDeliveryRx.getOrderId() + " readyToDeliveryRx.getIsDelivered()# " + readyToDeliveryRx.getIsDelivered());
                Order order = (Order) patientProfileDAO.findByPropertyUnique(new Order(), "id", readyToDeliveryRx.getOrderId(), Constants.HIBERNATE_EQ_OPERATOR);
                order.setReadyToDeliverRxDate(new Date());
                if (CommonUtil.isNotEmpty(readyToDeliveryRx.getHandlingFee())) {
                    logger.info("Handling Fee# " + readyToDeliveryRx.getHandlingFee());
                    order.setHandLingFee(AppUtil.getSafeDouble(readyToDeliveryRx.getHandlingFee(), 0d));
                }
                if (CommonUtil.isNotEmpty(readyToDeliveryRx.getFinalPayment())) {
                    logger.info("Prescription Out Of Pocket# " + readyToDeliveryRx.getFinalPayment());
                    double finalPayment = AppUtil.getSafeDouble(readyToDeliveryRx.getFinalPayment(), 0d);
                    order.setPaymentExcludingDelivery(finalPayment);
                    //finalPayment = finalPayment + AppUtil.getSafeDouble(readyToDeliveryRx.getHandlingFee(), 0d);
                    logger.info("Final Payment# " + finalPayment);
                    order.setFinalPayment(finalPayment);
                }
                OrderStatus os = new OrderStatus();
                if (readyToDeliveryRx.getIsDelivered()) {
                    os.setId(Constants.ORDER_STATUS.READY_TO_DELIVER_ID);
                } else {
                    //os.setId(Constants.ORDER_STATUS.CANCEL_ORDER_ID);
                }
                logger.info("OrderStatus is# " + os.getId());
                order.setOrderStatus(os);
                PatientDeliveryAddress patientDeliveryAddress = null;
                if (!CommonUtil.isNullOrEmpty(readyToDeliveryRx.getDelievryAddressId())) {
                    patientDeliveryAddress = patientProfileDAO.getPatientDeliveryAddressById(readyToDeliveryRx.getDelievryAddressId());
                    if (patientDeliveryAddress != null && !CommonUtil.isNullOrEmpty(patientDeliveryAddress.getId())) {
                        order.setStreetAddress(patientDeliveryAddress.getAddress());
                        order.setCity(patientDeliveryAddress.getCity());
                        order.setZip(patientDeliveryAddress.getZip());
                        order.setAddressLine2(patientDeliveryAddress.getDescription());
                        order.setApartment(patientDeliveryAddress.getApartment());
                        if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                            order.setState(patientDeliveryAddress.getState().getName());
                        }
                    }
                }
                if (!CommonUtil.isNullOrEmpty(readyToDeliveryRx.getDeliveryPreferenceId())) {
                    DeliveryPreferences deliveryPreferences = patientProfileDAO.getDeliveryPreferenceById(readyToDeliveryRx.getDeliveryPreferenceId());
                    if (deliveryPreferences != null && !CommonUtil.isNullOrEmpty(readyToDeliveryRx.getDeliveryPreferenceId())) {
                        order.setDeliveryPreference(deliveryPreferences);
                    }
                }

                order.setReadyToDeliverRxOrders(this.saveReadyToDeliverRxOrders(order, patientDeliveryAddress, readyToDeliveryRx));
                patientProfileDAO.saveOrUpdate(order);
                if (signature != null && (!signature.isEmpty())) {
                    Integer patientId = 0;
                    PatientProfile patientProfile = order.getPatientProfile();
                    if (Objects.equals(patientProfile.getPatientProfileSeqNo(), patientId)) {
                        logger.info("Patient Signature already saved against this " + patientId);
                        break;
                    }
                    patientId = patientProfile.getPatientProfileSeqNo();
                    String dateStr = DateUtil.dateToString(new Date(), "yy-MM-dd hh:mm:ss");
                    dateStr = dateStr.replace(":", "-");
                    dateStr = dateStr.replace(" ", "-");
                    String ext = FileUtil.determineImageFormat(signature.getBytes());
                    String completeName = "Sign_" + patientId + "_" + dateStr + "." + ext;
                    String signatureImagPath = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "orderimages/" + completeName;
                    logger.info("Complete signature Image Path: " + signatureImagPath);

                    FileCopyUtils.copy(signature.getBytes(),
                            new File(PropertiesUtil.getProperty("ORDER_IMAGES_CARD_PATH") + completeName));
                    CommonUtil.executeCommand(PropertiesUtil.getProperty("COMMAND"));
                    patientProfile.setSignature(signatureImagPath);

                    patientProfileDAO.saveOrUpdate(patientProfile);
                }
                isSaved = true;
            }
        } catch (Exception e) {
            isSaved = false;
            e.printStackTrace();
            logger.error("Exception:: isConfirmReadyToDeliveryRxOrders", e);
        }
        return isSaved;
    }

    private List<ReadyToDeliverRxOrders> listOfReadyToDeliverRxOrders(Order order, ReadyToDeliveryRxDTO readyToDeliveryRx) {
        List<BusinessObject> lstObj = new ArrayList();
        BusinessObject obj = new BusinessObject("patientProfile.id", order.getPatientProfile().getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        obj = new BusinessObject("deliveryPreferences.id", order.getDeliveryPreference().getId(), Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        if (!CommonUtil.isNullOrEmpty(readyToDeliveryRx.getDelievryAddressId())) {
            obj = new BusinessObject("patientDeliveryAddress.id", readyToDeliveryRx.getDelievryAddressId(), Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
        }
        obj = new BusinessObject("isShipped", false, Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        List<ReadyToDeliverRxOrders> listOfReadyToDeliverRxOrders = patientProfileDAO.findByNestedProperty(new ReadyToDeliverRxOrders(), lstObj, "", 0);
        return listOfReadyToDeliverRxOrders;
    }

    private ReadyToDeliverRxOrders saveReadyToDeliverRxOrders(Order order, PatientDeliveryAddress patientDeliveryAddress, ReadyToDeliveryRxDTO readyToDeliveryRx) {
        logger.info("Start saving saveReadyToDeliverRxOrders");
        ReadyToDeliverRxOrders readyToDeliverRxOrders = new ReadyToDeliverRxOrders();
        try {
            readyToDeliverRxOrders.setPatientProfile(order.getPatientProfile());
            readyToDeliverRxOrders.setDeliveryPreferences(order.getDeliveryPreference());
            if (patientDeliveryAddress != null && !CommonUtil.isNullOrEmpty(patientDeliveryAddress.getId())) {
                readyToDeliverRxOrders.setPatientDeliveryAddress(patientDeliveryAddress);
            }
            readyToDeliverRxOrders.setIsShipped(false);
            readyToDeliverRxOrders.setPostedDate(new Date());
            List<ReadyToDeliverRxOrders> listOfReadyToDeliverRxOrders = this.listOfReadyToDeliverRxOrders(order, readyToDeliveryRx);
            if (CommonUtil.isNullOrEmpty(listOfReadyToDeliverRxOrders)) {
                patientProfileDAO.save(readyToDeliverRxOrders);
            } else {
                readyToDeliverRxOrders = listOfReadyToDeliverRxOrders.get(0);
            }
            //order.setReadyToDeliverRxOrders(readyToDeliverRxOrders);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: saveReadyToDeliverRxOrders", e);
        }
        logger.info("End saving saveReadyToDeliverRxOrders " + readyToDeliverRxOrders.getId());
        return readyToDeliverRxOrders;
    }

    public List<OrderDetailDTO> generatePatientBasicReport(BaseDTO baseDTO) {
        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        try {
            String phoneNumber = baseDTO.getPhoneNumber();
            if (CommonUtil.isNotEmpty(phoneNumber)) {
                baseDTO.setPhoneNumber(EncryptionHandlerUtil.getEncryptedString(phoneNumber));
            }
            List<Order> list = patientProfileDAO.generatePatientBasicReport(baseDTO);
//            if (CommonUtil.isNotEmpty(baseDTO.getPatientName())) {
//                list = list.stream().filter(ord -> ord.getPatientProfile().getFirstName().equalsIgnoreCase(baseDTO.getPatientName())
//                        || ord.getPatientProfile().getLastName().equalsIgnoreCase(baseDTO.getPatientName()) || baseDTO.getPatientName().equalsIgnoreCase(ord.getPatientProfile().getFirstName() + " " + ord.getPatientProfile().getLastName())).collect(Collectors.toList());
//
//                if (CommonUtil.isNullOrEmpty(list)) {
//                    list = list.stream().filter(ord -> ord.getPatientProfileMembers().getFirstName().equalsIgnoreCase(baseDTO.getPatientName())
//                            || ord.getPatientProfileMembers().getLastName().equalsIgnoreCase(baseDTO.getPatientName()) || baseDTO.getPatientName().equalsIgnoreCase(ord.getPatientProfileMembers().getFirstName() + " " + ord.getPatientProfileMembers().getLastName())).collect(Collectors.toList());
//                }
//                if (CommonUtil.isNullOrEmpty(list)) {
//                    list = list.stream().filter(ord -> ord.getFirstName().equalsIgnoreCase(baseDTO.getPatientName()) || ord.getLastName().equalsIgnoreCase(baseDTO.getPatientName())).collect(Collectors.toList());
//                }
//            }
//            if (CommonUtil.isNotEmpty(baseDTO.getEmail())) {
//                list = list.stream().filter(ord -> ord.getPatientProfile().getEmailAddress().equalsIgnoreCase(baseDTO.getEmail())
//                        || ord.getPatientProfile().getEmailAddress().equalsIgnoreCase(baseDTO.getEmail())).collect(Collectors.toList());
//
//                if (CommonUtil.isNullOrEmpty(list)) {
//                    list = list.stream().filter(ord -> ord.getPatientProfileMembers().getEmail().equalsIgnoreCase(baseDTO.getEmail())
//                            || ord.getPatientProfileMembers().getEmail().equalsIgnoreCase(baseDTO.getEmail())).collect(Collectors.toList());
//                }
//            }
            for (Order order : list) {
                OrderDetailDTO dto = new OrderDetailDTO();
//                dto.setRegisteredStatus("Primary Member");
                String patientName = "";
                if (order.getPatientProfile() != null && !CommonUtil.isNullOrEmpty(order.getPatientProfile().getPatientProfileSeqNo())) {
                    patientName = EncryptionHandlerUtil.getDecryptedString(order.getPatientProfile().getFirstName()) + " " + EncryptionHandlerUtil.getDecryptedString(order.getPatientProfile().getLastName());
                }
                dto.setPatientName(patientName);
                dto.setMobileNumber(order.getPatientProfile().getMobileNumber());
//                dto.setPatientSignature(AppUtil.getSafeStr(order.getPatientProfile().getSignature(), ""));
                dto.setId(order.getId());
                dto.setDrugPrice(order.getDrugPrice() != null ? order.getDrugPrice() : 0d);
                dto.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
                dto.setCreatedOn(order.getCreatedOn());
                dto.setStatusCreatedOn(order.getUpdatedOn());//.getStatusCreatedOn());
                dto.setRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "").length() > 0
                        ? AppUtil.getSafeStr(order.getRxPrefix(), "") + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "") : "");
//                dto.setRedeemPoints(order.getRedeemPoints());
//                dto.setRedeemPointsCost(order.getRedeemPointsCost() != null ? order.getRedeemPointsCost() : 0d);
//                dto.setRxReimbCost(order.getRxReimbCost() != null ? order.getRxReimbCost() : 0d);
//                dto.setDrugName(AppUtil.getSafeStr(order.getDrugName(), "") + " " + AppUtil.getSafeStr(order.getStrength(), ""));
                dto.setDrugType(order.getDrugType());
                dto.setRefill(order.getRefillsAllowed());
                //dto.setRefillsRemaining(order.getOrderChain() != null ? order.getOrderChain().getRefillRemaing() + "" : "0");
                //dto.setDaysSupply(order.getOrderChain() != null && order.getOrderChain().getDaysSupply() != null ? order.getOrderChain().getDaysSupply() : 0);//order.getDaysSupply() != null && order.getDaysSupply() > 0 ? order.getDaysSupply() : 0);
                dto.setDaysSupply(order.getDaysSupply() != null ? order.getDaysSupply() : 0);
                dto.setQty(order.getQty());
//                dto.setHandLingFee(order.getHandLingFee());
                dto.setPayment(order.getPayment());
                dto.setFinalPayment(order.getFinalPayment() != null ? order.getFinalPayment() : 0d);
                dto.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(dto.getFinalPayment(), "en", "Us"));
                if (order.getDeliveryPreference() != null) {
                    if (order.getDeliveryPreference().getName().contains("2nd Day")) {
//                        dto.setHandLingFee(0d);
                    }
//                    dto.setDeliveryPreferencesName(order.getDeliveryPreference().getName());
                }
//                double totalPayment = dto.getFinalPayment() + dto.getHandLingFee();
//                dto.setTotalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(totalPayment, "en", "Us"));
//                dto.setHandlingFeeStr(AppUtil.roundOffNumberToCurrencyFormat(dto.getHandLingFee(), "en", "Us"));
//                if (order.getApartment() != null && !"".equals(order.getApartment())) {
//                    //  logger.info("Appartmnet is: " + order.getApartment());
////                    dto.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                            + AppUtil.getSafeStr(order.getApartment(), "") + " " + AppUtil.getSafeStr(order.getCity(), "")
//                            + ", " + AppUtil.getSafeStr(order.getState(), "") + " "
//                            + AppUtil.getSafeStr(order.getZip(), ""));
//                } else {
////                    dto.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                            + AppUtil.getSafeStr(order.getCity(), "") + ", " + AppUtil.getSafeStr(order.getState(), "")
//                            + " " + AppUtil.getSafeStr(order.getZip(), ""));
//                }
                if (AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("Public") || AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("Commercial")) {
//                    dto.setPaymentType(AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("Commercial") ? "PRIVATE INS" : "PUBLIC INS");
                }
                if (order.getRxExpiredDate() != null) {
                    dto.setRxExpiredDateStr(DateUtil.dateToString(order.getRxExpiredDate(), "MM/dd/yyyy"));
                }
//                dto.setSalesTaxStr(AppUtil.roundOffNumberToCurrencyFormat(0d, "en", "Us"));
//                dto.setPharmacyName(AppUtil.getSafeStr(order.getPharmacyName(), ""));
                orderDetailDTOs.add(dto);
            }
        } catch (Exception e) {
            logger.error("Exception:: generatePatientBasicReport", e);
        }
        return orderDetailDTOs;
    }

    public PatientProfile savePatientProfile(PatientProfile patientProfile) {
        try {
            if (patientProfile.getSecurityToken() != null) {
                patientProfile.setStatus(Constants.COMPLETED);
            } else {
                patientProfile.setStatus("Pending");
            }

            if (CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                patientProfile.setCreatedOn(new Date());
            }
            patientProfile.setUpdatedOn(new Date());
            patientProfileDAO.saveOrUpdate(patientProfile);
        } catch (Exception e) {
            logger.error("Exception:: savePatientProfile", e);
        }
        return patientProfile;
    }

    public boolean isPatientEmailExist(String email, Integer patientId) {
        boolean isEmailExist = false;
        try {
            isEmailExist = patientProfileDAO.isPatientEmailExist(EncryptionHandlerUtil.getEncryptedString(email), patientId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# isPatientEmailExist", e);
        }
        return isEmailExist;
    }

    public boolean isPatientMobileExist(String mobileNumber, Integer patientId) {
        boolean isMobileExist = false;
        try {
            isMobileExist = patientProfileDAO.isPatientMobileExist(EncryptionHandlerUtil.getEncryptedString(mobileNumber), patientId);
        } catch (Exception e) {
            logger.error("Exception# isPatientMobileExist", e);
        }
        return isMobileExist;
    }

    public PatientProfile updatePatientProfileInfo(PatientProfile patientProfile) {
        try {
            if (patientProfile.getSecurityToken() != null) {
                patientProfile.setStatus(Constants.COMPLETED);
            } else {
                patientProfile.setStatus("Pending");
            }
            this.populateOsType(patientProfile.getOsType(), patientProfile);
            //Get PatientProfile
            PatientProfile dbPatientProfile = patientProfileDAO.getPatientProfile(patientProfile.getPatientProfileSeqNo());
            if (dbPatientProfile != null) {
                patientProfile.setCreatedOn(dbPatientProfile.getCreatedOn());

                if (CommonUtil.isNullOrEmpty(patientProfile.getEnrollmentPath())) {
                    patientProfile.setEnrollmentPath(dbPatientProfile.getEnrollmentPath());
                }
                if (CommonUtil.isNullOrEmpty(patientProfile.getOsType())) {
                    patientProfile.setOsType(dbPatientProfile.getOsType());
                }

            }
            patientProfile.setUpdatedOn(new Date());
            patientProfileDAO.saveOrUpdate(patientProfile);
        } catch (Exception e) {
            logger.error("Exception:: updatePatientProfileInfo: ", e);
        }
        return patientProfile;
    }

    public LoginDTO updateProfileWs(LoginDTO loginDTO, String psw) {
        try {
            //Get PatientProfile
            PatientProfile dbPatientProfile = patientProfileDAO.getPatientProfile(loginDTO.getPatientProfileSeqNo());
            if (dbPatientProfile != null) {
                loginDTO.setIsAlreadEnrolled(Boolean.TRUE);
                if (CommonUtil.isNullOrEmpty(dbPatientProfile.getPassword())) {
                    dbPatientProfile.setPassword(CommonUtil.bCryptPasswordEncoder(psw));
                    loginDTO.setIsAlreadEnrolled(Boolean.FALSE);
                }
                dbPatientProfile.setFirstName(loginDTO.getFirstName());
                dbPatientProfile.setLastName(loginDTO.getLastName());
                dbPatientProfile.setNickName(loginDTO.getNickName());
                dbPatientProfile.setCity(loginDTO.getCity());
                dbPatientProfile.setZipcode(loginDTO.getZip());
                if (loginDTO.getStateId() != null) {
                    State state = (State) patientProfileDAO.findRecordById(new State(), loginDTO.getStateId());
                    if (!CommonUtil.isNullOrEmpty(state.getId())) {
                        dbPatientProfile.setStatee(state);
                    }
                }

                dbPatientProfile.setDeviceToken(loginDTO.getDeviceToken());
                this.populateOsType(loginDTO.getOsType(), dbPatientProfile);
                dbPatientProfile.setUpdatedOn(new Date());
                patientProfileDAO.saveOrUpdate(dbPatientProfile);
                saveActivitesHistory(ActivitiesEnum.PROFILE_EDIT.getValue(), dbPatientProfile, "", "", "","");
            }

        } catch (Exception e) {
            e.printStackTrace();
            loginDTO = null;
            logger.error("Exception# updateProfileWs: ", e);
        }
        return loginDTO;
    }

    public LoginDTO savePatientEnrollmentWs(PatientProfile patientProfile, LoginDTO loginDTO) {
        try {
            patientProfile.setMobileNumber(loginDTO.getMobileNumber());
            patientProfile.setEmailAddress(loginDTO.getEmailAddress());
            patientProfile.setFirstName(loginDTO.getFirstName());
            patientProfile.setLastName(loginDTO.getLastName());
            patientProfile.setNickName(loginDTO.getNickName());
            patientProfile.setDeviceToken(loginDTO.getDeviceToken());
            patientProfile.setGender(loginDTO.getGender());
            patientProfile.setStatus(Constants.PENDING);
            patientProfile.setPracticeId(loginDTO.getPractiseId());
            this.populateOsType(loginDTO.getOsType(), patientProfile);

            patientProfile.setCreatedOn(new Date());
            patientProfile.setUpdatedOn(new Date());
            patientProfileDAO.save(patientProfile);
            loginDTO.setPatientProfileSeqNo(patientProfile.getPatientProfileSeqNo());
        } catch (Exception e) {
            loginDTO = null;
            logger.error("Exception# savePatientEnrollmentWs: ", e);
        }
        return loginDTO;
    }

    private void populateOsType(String osType, PatientProfile patientProfile) {
        if (CommonUtil.isNotEmpty(osType)) {
            if (Integer.toString(Constants.OS_TYPE.IOS).equals(osType)) {
                patientProfile.setOsType("" + Constants.OS_TYPE.IOS);
                patientProfile.setEnrollmentPath(Constants.APP_TYPE.IOS);
            } else if (Integer.toString(Constants.OS_TYPE.ANDROID).equals(osType)) {
                patientProfile.setOsType("" + Constants.OS_TYPE.ANDROID);
                patientProfile.setEnrollmentPath(Constants.APP_TYPE.ANDROID);
            } else {
                patientProfile.setOsType("" + Constants.OS_TYPE.WEB);
                patientProfile.setEnrollmentPath(Constants.APP_TYPE.WEB);
            }
        }
    }

    public PatientProfile getPatientInfoByEmailOrMobileNo(String mobileNumber, String email) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientInfoByEmailOrMobileNo(mobileNumber, email);
            if (patientProfile != null && patientProfile.getStatee() != null && !CommonUtil.isNullOrEmpty(patientProfile.getStatee().getId())) {
                State state = (State) patientProfileDAO.findRecordById(new State(), patientProfile.getStatee().getId());
                patientProfile.setStatee(state);
                patientProfile.setState(state.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# getPatientInfoByEmailOrMobileNo: ", e);
        }
        return patientProfile;
    }

    public PatientGlucoseDTO saveGlucoseLevel(PatientGlucoseDTO patientGlucoseDTO, PatientProfile patientProfile) {
        PatientGlucoseResults patientGlucoseResults = new PatientGlucoseResults();
        try {
               String practiceCode = "";
                Practices dbpractice = patientProfileDAO.getPracticesById(patientProfile.getPracticeId());
                if(dbpractice!=null){
                practiceCode = dbpractice.getPracticeCode();
                }
            if (!CommonUtil.isNullOrEmpty(patientGlucoseDTO.getId())) {

                patientGlucoseResults = patientProfileDAO.getGlucoseById(patientProfile.getPatientProfileSeqNo(), patientGlucoseDTO.getId());
                patientGlucoseResults.setGlucoseLevel(patientGlucoseDTO.getGlucoseLevel());
                patientGlucoseResults.setReadingTime(patientGlucoseDTO.getReadingTime());
                patientGlucoseResults.setIsFasting(patientGlucoseDTO.getIsFasting());
                patientGlucoseResults.setUpdatedOn(new Date());
                patientProfileDAO.update(patientGlucoseResults);
                saveActivitesHistory(ActivitiesEnum.EDIT_GLUCOSE_ACTIVITY.getValue(), patientProfile, "", patientGlucoseDTO.getGlucoseLevel() + "mg/dl fasting:" + patientGlucoseDTO.getIsFasting(), patientGlucoseDTO.getReadingTime(),"");

            } else {

                patientGlucoseResults.setPatientProfile(patientProfile);
                patientGlucoseResults.setGlucoseLevel(patientGlucoseDTO.getGlucoseLevel());
                patientGlucoseResults.setReadingTime(patientGlucoseDTO.getReadingTime());
                patientGlucoseResults.setIsFasting(patientGlucoseDTO.getIsFasting());
                patientGlucoseResults.setCreatedOn(new Date());
                patientProfileDAO.save(patientGlucoseResults);
                patientGlucoseDTO.setId(patientGlucoseResults.getId());
                if (patientGlucoseDTO.getRxNumber() != null) {
                    patientGlucoseDTO.setRewardDTO(this.updateComplianceRewardPointAndRewardHistoryWithRxNumber(practiceCode+patientGlucoseDTO.getRxNumber(), patientProfile, Constants.REWARD_ACTIVTIES.GLUCOSE, patientGlucoseDTO.getGlucoseLevel() + "mg/dl fasting:" + patientGlucoseDTO.getIsFasting()));
                    //save Activites History
                    saveActivitesHistory(ActivitiesEnum.ADD_GLUCOSE_ACTIVITY.getValue(), patientProfile, practiceCode+patientGlucoseDTO.getRxNumber(), patientGlucoseDTO.getGlucoseLevel() + "mg/dl fasting:" + patientGlucoseDTO.getIsFasting(), patientGlucoseDTO.getReadingTime(),"");
                    return patientGlucoseDTO;
                }
                patientGlucoseDTO.setRewardDTO(this.updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(patientProfile, Constants.REWARD_ACTIVTIES.GLUCOSE, "PatientGlucoseResults", patientGlucoseDTO.getGlucoseLevel() + "mg/dl fasting:" + patientGlucoseDTO.getIsFasting(), patientGlucoseDTO.getReadingTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# patientProfileService->  saveGlucoseLevel: ", e);
        }
        return patientGlucoseDTO;
    }

    public List<PatientGlucoseDTO> getPatientGlucoseList(Integer patientProfileSeqNo) {
        List<PatientGlucoseDTO> list = new ArrayList<>();
        try {
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            List<PatientGlucoseResults> patientGlucoseResultsList = patientProfileDAO.findByNestedProperty(new PatientGlucoseResults(), lstObj, "", 0);
            for (PatientGlucoseResults dbpatientGlucoseResults : patientGlucoseResultsList) {
                PatientGlucoseDTO patientGlucoseDTO = new PatientGlucoseDTO();
                patientGlucoseDTO.setId(dbpatientGlucoseResults.getId());
                patientGlucoseDTO.setGlucoseLevel(dbpatientGlucoseResults.getGlucoseLevel());
                patientGlucoseDTO.setReadingTime(dbpatientGlucoseResults.getReadingTime());
                patientGlucoseDTO.setIsFasting(dbpatientGlucoseResults.getIsFasting());
                patientGlucoseDTO.setPatientProfileSeqNo(dbpatientGlucoseResults.getPatientProfile().getPatientProfileSeqNo());
                list.add(patientGlucoseDTO);
            }
        } catch (Exception e) {
            logger.error("Exception# PatientProfileService-> getPatientGlucoseList", e);
        }
        return list;
    }

    public BloodPressureDTO saveBloodPressureLevel(BloodPressureDTO bloodPressureDTO, PatientProfile patientProfile) {
        BloodPressure dbBloodPressure = new BloodPressure();
        try {
                String practiceCode = "";
                Practices dbpractice = patientProfileDAO.getPracticesById(patientProfile.getPracticeId());
                if(dbpractice!=null){
                practiceCode = dbpractice.getPracticeCode();
                }
            if (!CommonUtil.isNullOrEmpty(bloodPressureDTO.getBloodPresureSeqNo())) {

                dbBloodPressure = patientProfileDAO.getBloodPresureById(patientProfile.getPatientProfileSeqNo(), bloodPressureDTO.getBloodPresureSeqNo());
                dbBloodPressure.setDistolicBloodPressure(bloodPressureDTO.getDistolicBloodPressure());
                dbBloodPressure.setSystolicBloodPressure(bloodPressureDTO.getSystolicBloodPressure());
                dbBloodPressure.setPulse(bloodPressureDTO.getPulse());
                dbBloodPressure.setReadingTime(bloodPressureDTO.getReadingTime());
                dbBloodPressure.setUpdatedOn(new Date());
                patientProfileDAO.update(dbBloodPressure);
                saveActivitesHistory(ActivitiesEnum.EDIT_BLOOD_PRESSURE_ACTIVITY.getValue() + ": ", patientProfile, "", " SYS/DIA " + bloodPressureDTO.getSystolicBloodPressure() + "/" + bloodPressureDTO.getDistolicBloodPressure() + " PRV " + bloodPressureDTO.getPulse(), bloodPressureDTO.getReadingTime(),"");
//                saveActivitesHistory(ActivitiesEnum.EDIT_BLOOD_PRESSURE_ACTIVITY.getValue() + ": ", patientProfile, "", bloodPressureDTO.getDistolicBloodPressure() + " DIA " + bloodPressureDTO.getSystolicBloodPressure() + " SYS " + bloodPressureDTO.getPulse() + " PRV", bloodPressureDTO.getReadingTime());
            } else {

                dbBloodPressure.setPatientProfile(patientProfile);
                dbBloodPressure.setDistolicBloodPressure(bloodPressureDTO.getDistolicBloodPressure());
                dbBloodPressure.setSystolicBloodPressure(bloodPressureDTO.getSystolicBloodPressure());
                dbBloodPressure.setPulse(bloodPressureDTO.getPulse());
                dbBloodPressure.setReadingTime(bloodPressureDTO.getReadingTime());
                dbBloodPressure.setCreatedOn(new Date());
                patientProfileDAO.save(dbBloodPressure);
                bloodPressureDTO.setBloodPresureSeqNo(patientProfile.getPatientProfileSeqNo());
                if (bloodPressureDTO.getRxNumber() != null) {
                    bloodPressureDTO.setRewardDTO(this.updateComplianceRewardPointAndRewardHistoryWithRxNumber(practiceCode+bloodPressureDTO.getRxNumber(), patientProfile, Constants.REWARD_ACTIVTIES.BLOOD_PRESSURE, " SYS/DIA " + bloodPressureDTO.getSystolicBloodPressure() + "/" + bloodPressureDTO.getDistolicBloodPressure() + " PRV " + bloodPressureDTO.getPulse()));
                    saveActivitesHistory(ActivitiesEnum.ADD_BLOOD_PRESSURE_ACTIVITY.getValue(), patientProfile, practiceCode+bloodPressureDTO.getRxNumber(), " SYS/DIA " + bloodPressureDTO.getSystolicBloodPressure() + "/" + bloodPressureDTO.getDistolicBloodPressure() + " PRV " + bloodPressureDTO.getPulse(), bloodPressureDTO.getReadingTime(),"");
                    return bloodPressureDTO;
                }
                bloodPressureDTO.setRewardDTO(this.updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(patientProfile, Constants.REWARD_ACTIVTIES.BLOOD_PRESSURE, "PatientBloodPresureResult", " SYS/DIA " + bloodPressureDTO.getSystolicBloodPressure() + "/" + bloodPressureDTO.getDistolicBloodPressure(), bloodPressureDTO.getReadingTime()));
            }
        } catch (Exception e) {
            logger.error("Exception# patientProfileService->  saveGlucoseLevel: ", e);
        }
        return bloodPressureDTO;
    }

    public List<PatientActivityDTO> getPatientActitvityCount(Integer patientProfileSeqNo) {
        List<PatientActivityDTO> list = new ArrayList<>();

        try {
            PatientActivityDTO patientActivityDTO = new PatientActivityDTO();
            patientActivityDTO.setBloodGlucoseCount(patientProfileDAO.getTotalRecords(new PatientGlucoseResults(), "patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_COUNT_FUNCTION, "*"));
            patientActivityDTO.setBloodPressureCount(patientProfileDAO.getTotalRecords(new BloodPressure(), "patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_COUNT_FUNCTION, "*"));
            patientActivityDTO.setPulseRateCount(patientProfileDAO.getTotalRecords(new PatientHeartPulseResult(), "patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_COUNT_FUNCTION, "*"));
            patientActivityDTO.setTotalBodyMassCount(patientProfileDAO.getTotalRecords(new PatientBodyMassResult(), "patientProfile.patientProfileSeqNo", patientProfileSeqNo, Constants.HIBERNATE_COUNT_FUNCTION, "*"));
            patientActivityDTO.setSurvyCount(patientProfileDAO.getTotalCountSurvey(patientProfileSeqNo));
//            patientActivityDTO.setSurvyCount(patientProfileDAO.getTotalCountSurveylogs(patientProfileSeqNo));
            patientActivityDTO.setTottalPresentServeys(patientProfileDAO.getTotalPresentSurvey());
            PatientGlucoseResults glucose = patientProfileDAO.getPatientGlucoseResultsByPatientId(patientProfileSeqNo);
            if (glucose != null && glucose.getCreatedOn() != null) {
                patientActivityDTO.setLastBloodGlucoseReadingTime(DateUtil.dateToString(glucose.getCreatedOn(), Constants.DATE_TIME_FORMAT));
            }
            BloodPressure bloodPressure = patientProfileDAO.getBbloodPressureByPatientId(patientProfileSeqNo);
            if (bloodPressure != null && bloodPressure.getCreatedOn() != null) {
                patientActivityDTO.setLastBloodPressureReadingTime(DateUtil.dateToString(bloodPressure.getCreatedOn(), Constants.DATE_TIME_FORMAT));
            }
            PatientHeartPulseResult heartPulse = patientProfileDAO.getPatientHeartPulseResultByPatientId(patientProfileSeqNo);
            if (heartPulse != null && heartPulse.getCreatedOn() != null) {
                patientActivityDTO.setLastPulseRateReadingTime(DateUtil.dateToString(heartPulse.getCreatedOn(), Constants.DATE_TIME_FORMAT));
            }
            PatientBodyMassResult patientBodyMassResult = patientProfileDAO.getBodyMassResutByPatientId(patientProfileSeqNo);
            if (patientBodyMassResult != null && patientBodyMassResult.getCreatedOn() != null) {
                patientActivityDTO.setLastBodyMassReadingTime(DateUtil.dateToString(patientBodyMassResult.getCreatedOn(), Constants.DATE_TIME_FORMAT));
            }
            patientActivityDTO.setServerCurrentTime(new Date());
            list.add(patientActivityDTO);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: getPatientActitvityCount", e);
        }
        return list;
    }

    public PatientGlucoseResults getPatientGlucoseResultsByPatientId(Integer patientProfileSeqNo) {
        PatientGlucoseResults patientGlucoseResults = new PatientGlucoseResults();
        try {
            patientGlucoseResults = patientProfileDAO.getPatientGlucoseResultsByPatientId(patientProfileSeqNo);
        } catch (Exception e) {
        }
        return patientGlucoseResults;
    }

    public List<BloodPressureDTO> getBloodPressureHstoryList(Integer patientProfileSeqNo) throws Exception {
        List<BloodPressureDTO> list = new ArrayList<>();
        try {
            List<BloodPressure> bloodPressureList = patientProfileDAO.getBloodPressureResultListByPatientProfileId(patientProfileSeqNo);
            for (BloodPressure bloodPressure : bloodPressureList) {
                BloodPressureDTO bloodPressureDTO = new BloodPressureDTO();
                bloodPressureDTO.setBloodPresureSeqNo(bloodPressure.getBloodPresureSeqNo());
                bloodPressureDTO.setDistolicBloodPressure(bloodPressure.getDistolicBloodPressure());
                bloodPressureDTO.setPulse(bloodPressure.getPulse());
                bloodPressureDTO.setReadingTime(bloodPressure.getReadingTime());
                bloodPressureDTO.setSystolicBloodPressure(bloodPressure.getSystolicBloodPressure());
                bloodPressureDTO.setPatientProfileSeqNo(bloodPressure.getPatientProfile().getPatientProfileSeqNo());
                list.add(bloodPressureDTO);
            }
        } catch (Exception e) {
            logger.error("Exception# ->patientProfileService ->getBloodPressureHstoryList ", e);
        }
        return list;
    }

    public PatientBodyMassResultDTO saveBodyMassResult(PatientBodyMassResultDTO patientBodyMassResultDTO, PatientProfile profile) throws Exception {
        PatientBodyMassResult dbPatientBodyMassResult = new PatientBodyMassResult();
        try {
              String practiceCode = "";
                Practices dbpractice = patientProfileDAO.getPracticesById(profile.getPracticeId());
                if(dbpractice!=null){
                practiceCode = dbpractice.getPracticeCode();
                }
            if (!CommonUtil.isNullOrEmpty(patientBodyMassResultDTO.getBodyMassResultSeqNo())) {

                dbPatientBodyMassResult = patientProfileDAO.getBodyMassResutById(patientBodyMassResultDTO.getBodyMassResultSeqNo(), profile.getPatientProfileSeqNo());
                dbPatientBodyMassResult.setHeight(patientBodyMassResultDTO.getHeight());
                dbPatientBodyMassResult.setWeight(patientBodyMassResultDTO.getWeight());
                dbPatientBodyMassResult.setPulse(patientBodyMassResultDTO.getPulse());
                dbPatientBodyMassResult.setBMI(patientBodyMassResultDTO.getBMI());
                dbPatientBodyMassResult.setresultDate(patientBodyMassResultDTO.getResultDate());
                dbPatientBodyMassResult.setresultDate(patientBodyMassResultDTO.getResultDate());
                dbPatientBodyMassResult.setUpdatedOn(new Date());
                patientProfileDAO.update(dbPatientBodyMassResult);
                saveActivitesHistory(ActivitiesEnum.EDIT_BODY_MASS_ACTIVITY.getValue(), profile, "", patientBodyMassResultDTO.getHeight() + "ft " + patientBodyMassResultDTO.getWeight() + "LBS" + patientBodyMassResultDTO.getBMI(), patientBodyMassResultDTO.getResultDate(),"");
            } else {
                dbPatientBodyMassResult.setPatientProfile(profile);
                dbPatientBodyMassResult.setHeight(patientBodyMassResultDTO.getHeight());
                dbPatientBodyMassResult.setWeight(patientBodyMassResultDTO.getWeight());
                dbPatientBodyMassResult.setPulse(patientBodyMassResultDTO.getPulse());
                dbPatientBodyMassResult.setBMI(patientBodyMassResultDTO.getBMI());
                dbPatientBodyMassResult.setresultDate(patientBodyMassResultDTO.getResultDate());
                dbPatientBodyMassResult.setCreatedOn(new Date());
                patientProfileDAO.save(dbPatientBodyMassResult);
                patientBodyMassResultDTO.setPatientProfileSeqNo(profile.getPatientProfileSeqNo());
                if (patientBodyMassResultDTO.getRxNumber() != null) {
                    patientBodyMassResultDTO.setRewardPointDTO(this.updateComplianceRewardPointAndRewardHistoryWithRxNumber(practiceCode+patientBodyMassResultDTO.getRxNumber(), profile, Constants.REWARD_ACTIVTIES.BODY_MASS, patientBodyMassResultDTO.getHeight() + "ft " + patientBodyMassResultDTO.getWeight() + "LBS " + patientBodyMassResultDTO.getBMI()));
                    saveActivitesHistory(ActivitiesEnum.ADD_BODY_MASS_ACTIVITY.getValue(), profile, practiceCode+patientBodyMassResultDTO.getRxNumber(), patientBodyMassResultDTO.getHeight() + "ft " + patientBodyMassResultDTO.getWeight() + "LBS " + patientBodyMassResultDTO.getBMI(), patientBodyMassResultDTO.getResultDate(),"");
                    return patientBodyMassResultDTO;
                }
                patientBodyMassResultDTO.setRewardPointDTO(this.updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(profile, Constants.REWARD_ACTIVTIES.BODY_MASS, "PatientBodyMass", patientBodyMassResultDTO.getHeight() + "ft " + patientBodyMassResultDTO.getWeight() + "LBS " + patientBodyMassResultDTO.getBMI(), patientBodyMassResultDTO.getResultDate()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# patientProfileService-> saveBodyMassResult", e);
        }
        return patientBodyMassResultDTO;
    }

    public void saveActivitesHistory(String activityName, PatientProfile profile, String rxNumber, String activityDetail, String readingTime ,String orderId) {
        try {
            ActivitesHistory activitesHistory = new ActivitesHistory();
            activitesHistory.setOrderId(orderId != null ? orderId: "");
            activitesHistory.setPatientProfile(profile);
            activitesHistory.setActivityName(activityName);
            activitesHistory.setRxNumber(rxNumber);
            activitesHistory.setCreatedOn(new Date());
            activitesHistory.setActivityDetail(activityDetail);
            activitesHistory.setReadingTime(readingTime);
            patientProfileDAO.save(activitesHistory);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# patientProfileService# saveActivitesHistory ", e);
        }

    }

    public List<PatientBodyMassResultDTO> getBodyMassHistoryList(Integer patientProfileSeqNo) throws Exception {
        List<PatientBodyMassResultDTO> list = new ArrayList<>();
        try {
            List<PatientBodyMassResult> BodyMassResultList = patientProfileDAO.getBodyMassResultListByPatientProfileId(patientProfileSeqNo);
            for (PatientBodyMassResult patientMassResult : BodyMassResultList) {
                PatientBodyMassResultDTO patientBodyMassResultDTO = new PatientBodyMassResultDTO();
                patientBodyMassResultDTO.setBodyMassResultSeqNo(patientMassResult.getBodyMassResultSeqNo());
                patientBodyMassResultDTO.setHeight(patientMassResult.getHeight());
                patientBodyMassResultDTO.setWeight(patientMassResult.getWeight());
                patientBodyMassResultDTO.setPulse(patientMassResult.getPulse());
                patientBodyMassResultDTO.setBMI(patientMassResult.getBMI());
                patientBodyMassResultDTO.setResultDate(patientMassResult.getresultDate());
                patientBodyMassResultDTO.setPatientProfileSeqNo(patientMassResult.getPatientProfile().getPatientProfileSeqNo());
                list.add(patientBodyMassResultDTO);
            }
        } catch (Exception e) {
            logger.error("Exception# patientProfileService-> getBodyMassHistoryList", e);
        }
        return list;
    }

    public PatientProfile getPatientProfileByEmailOrPhone(String identify) throws Exception {

        try {
            return patientProfileDAO.getPatientProfileByEmailIdOrPhoneNumber(identify);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# PatientProfileService -> getPatientProfileByEmailOrPhone ", e);
        }
        return null;
    }

    public PatientHeartPulseDTO saveHeartPulseResult(PatientHeartPulseDTO patientHeartPulseDTO, PatientProfile profile) throws Exception {
        PatientHeartPulseResult dbpatientHeartPulse = new PatientHeartPulseResult();
        try {
                          String practiceCode = "";
                Practices dbpractice = patientProfileDAO.getPracticesById(profile.getPracticeId());
                if(dbpractice!=null){
                practiceCode = dbpractice.getPracticeCode();
                }
            if (!CommonUtil.isNullOrEmpty(patientHeartPulseDTO.getHeartPulseSeqNo())) {

                dbpatientHeartPulse = patientProfileDAO.getHeartPulseById(patientHeartPulseDTO.getHeartPulseSeqNo(), profile.getPatientProfileSeqNo());
                dbpatientHeartPulse.setHeartPulse(patientHeartPulseDTO.getHeartPulse());
                dbpatientHeartPulse.setHeartPulseDate(patientHeartPulseDTO.getHeartPulseDate());
                dbpatientHeartPulse.setUpdatedOn(new Date());
                patientProfileDAO.update(dbpatientHeartPulse);
                saveActivitesHistory(ActivitiesEnum.EDIT_HEART_PULS_ACTIVITY.getValue(), profile, "", patientHeartPulseDTO.getHeartPulse() + "BPM", patientHeartPulseDTO.getHeartPulseDate(),"");
            } else {
                dbpatientHeartPulse.setPatientProfile(profile);
                dbpatientHeartPulse.setHeartPulse(patientHeartPulseDTO.getHeartPulse());
                dbpatientHeartPulse.setHeartPulseDate(patientHeartPulseDTO.getHeartPulseDate());
                dbpatientHeartPulse.setCreatedOn(new Date());
                patientProfileDAO.save(dbpatientHeartPulse);
                patientHeartPulseDTO.setPatinetProfileSeqNo(profile.getPatientProfileSeqNo());
                RewardsPointDTO rewardDTO = new RewardsPointDTO();
                //save Activites History
                if (patientHeartPulseDTO.getRxNumber() != null) {
                    rewardDTO.setRxNumber(patientHeartPulseDTO.getRxNumber());
                    patientHeartPulseDTO.setRewardDTO(this.updateComplianceRewardPointAndRewardHistoryWithRxNumber(practiceCode+patientHeartPulseDTO.getRxNumber(), profile, Constants.REWARD_ACTIVTIES.HEART_PULSE, patientHeartPulseDTO.getHeartPulse() + "BPM"));
                    saveActivitesHistory(ActivitiesEnum.ADD_HEART_PULS_ACTIVITY.getValue(), profile, practiceCode+patientHeartPulseDTO.getRxNumber(), patientHeartPulseDTO.getHeartPulse() + "BPM", patientHeartPulseDTO.getHeartPulseDate(),"");
                    return patientHeartPulseDTO;
                }
                patientHeartPulseDTO.setRewardDTO(this.updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(profile, Constants.REWARD_ACTIVTIES.HEART_PULSE, "PatientHeartPulseResult", patientHeartPulseDTO.getHeartPulse() + "BPM", patientHeartPulseDTO.getHeartPulseDate()));
            }
        } catch (Exception e) {
            logger.error("Exception# patientProfileService-> saveBodyMassResult", e);
        }
        return patientHeartPulseDTO;
    }

    public List<PatientHeartPulseDTO> getHeartPulseHistoryList(Integer patientProfileSeqNo) throws Exception {
        List<PatientHeartPulseDTO> list = new ArrayList<>();
        try {
            List<PatientHeartPulseResult> heartPulseResultList = patientProfileDAO.getHeartPulseListByPatientProfileId(patientProfileSeqNo);
            for (PatientHeartPulseResult patientHeartPulseResult : heartPulseResultList) {
                PatientHeartPulseDTO patientHeartPulseDTO = new PatientHeartPulseDTO();
                patientHeartPulseDTO.setHeartPulseSeqNo(patientHeartPulseResult.getHeartPulseSeqNo());
                patientHeartPulseDTO.setHeartPulse(patientHeartPulseResult.getHeartPulse());
                patientHeartPulseDTO.setHeartPulseDate(patientHeartPulseResult.getHeartPulseDate());
                patientHeartPulseDTO.setPatinetProfileSeqNo(patientProfileSeqNo);
                list.add(patientHeartPulseDTO);
            }
        } catch (Exception e) {
            logger.error("Exception# patientProfileService-> getBodyMassHistoryList", e);
        }
        return list;
    }

    public List<PatientActivityDTO> getPatientActivitiesList2(Integer patientProileSeqNo) throws Exception {
        List<PatientActivityDTO> list = new ArrayList<>();
        PatientActivityDTO patientActivityDTO = new PatientActivityDTO();
        try {
            List<PatientProfile> profileList = patientProfileDAO.getPatientActivitesDetial();

            PatientProfile dbprofile = this.getPatientProfileById(patientProileSeqNo);
            List<PatientHeartPulseDTO> hertList = this.getHeartPulseHistoryList(patientProileSeqNo);
            List<PatientBodyMassResultDTO> massList = this.getBodyMassHistoryList(patientProileSeqNo);
            List<BloodPressureDTO> bPressureList = this.getBloodPressureHstoryList(patientProileSeqNo);
            List<PatientGlucoseDTO> glucoseList = this.getPatientGlucoseList(patientProileSeqNo);
            patientActivityDTO.setHeartPulseList(hertList);
            patientActivityDTO.setBodyMassList(massList);
            patientActivityDTO.setBloodPressureList(bPressureList);
            patientActivityDTO.setBloodGlucoseList(glucoseList);
            String fname = dbprofile.getFirstName();
            String lname = dbprofile.getLastName();
            patientActivityDTO.setFirstName(EncryptionHandlerUtil.getDecryptedString(fname + " " + lname));
            Date birthDate = DateUtil.stringToDate(EncryptionHandlerUtil.getDecryptedString(dbprofile.getBirthDate()), "yyyy");
            Date todayDate = new Date();
            int age = DateUtil.getDiffYears(birthDate, todayDate);
            String currentAge = Integer.toString(age);
            patientActivityDTO.setBirth(currentAge + "yrs");

            list.add(patientActivityDTO);

        } catch (Exception e) {
            logger.error("Excption# patientProfileService-> getPatientActivitiesList");
        }
        return list;
    }

    public List<PatientActivityDTO> getPatientActivitiesList() throws Exception {
        List<PatientActivityDTO> list = new ArrayList<>();
        try {
            List<Object[]> profileList = patientProfileDAO.getPatientActivitesDetial2();
//           System.out.println("list class :: " + profileList.get(0));
//           System.out.println("object class :: " + profileList.get(0).getClass());
//            
            for (Object[] patientActivities : profileList) {

                PatientActivity obj = new PatientActivity();
                obj.setFirstName(patientActivities[0].toString());
                PatientActivityDTO patientActivityDTO = new PatientActivityDTO();

                patientActivityDTO.setPatientId(Integer.parseInt(patientActivities[0].toString()));
                String fname = EncryptionHandlerUtil.getDecryptedString(patientActivities[1] != null ? patientActivities[1].toString() : "");
                String lname = EncryptionHandlerUtil.getDecryptedString(patientActivities[2] != null ? patientActivities[2].toString() : "");
                patientActivityDTO.setFirstName(fname + " " + lname);
                String birthDate = EncryptionHandlerUtil.getDecryptedString(patientActivities[3] != null ? patientActivities[3].toString() : "");
                if (!birthDate.equals("")) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    //String birth = format.parse(birthDate).toString();
                    Date birth = DateUtil.stringToDate(birthDate, "MM/dd/yyyy");
                    String birthYear = format.format(birth);
                    Date todayDate = new Date();
                    String today = format.format(todayDate);
                    //int age = DateUtil.getDiffYears(birth, todayDate);
                    int age = Integer.parseInt(today) - Integer.parseInt(birthYear);
                    String currentAge = Integer.toString(age);
                    patientActivityDTO.setBirth(currentAge + " yrs");
                } else {
                    patientActivityDTO.setBirth("N/A");
                }
                patientActivityDTO.setGlucoseLevel(patientActivities[10] != null ? patientActivities[10].toString() : "N/A");
                patientActivityDTO.setHeartPulse(patientActivities[4] != null ? patientActivities[4].toString() : "N/A");
                patientActivityDTO.setBodyMass(patientActivities[6] != null && patientActivities[7] != null ? "H: " + patientActivities[6].toString() + " W: " + patientActivities[7].toString() : "N/A");
                patientActivityDTO.setBloodPressure(patientActivities[8] != null && patientActivities[9] != null ? patientActivities[8].toString() + "/" + patientActivities[9].toString() : "N/A");
                String dateTime = patientActivities[11] != null ? patientActivities[11].toString() : "N/A";
                if (!dateTime.equals("N/A")) {
                    patientActivityDTO.setDateTime(DateUtil.changeDateFormat(dateTime, "yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy hh:mm aaa"));
                } else {
                    patientActivityDTO.setDateTime("N/A");
                }

                list.add(patientActivityDTO);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Excption# patientProfileService-> getPatientActivitiesList");
        }

        return list;
    }

    public List<BloodPressure> getBloodPressureList(int patientId) {
        List<BloodPressure> bloodPressureList = new ArrayList<>();
        try {
            bloodPressureList = patientProfileDAO.getBloodPressureList(patientId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Excption# patientProfileService-> getBloodPressureList");
        }
        return bloodPressureList;
    }

    public BMIYearFilterWsMainResponse getBMIYearFilterWs(int patientId, String startDate, String endDate) throws Exception {
        BMIYearFilterWsMainResponse bMIYearFilterWsMainResponse = new BMIYearFilterWsMainResponse();
        BMIYearFilterWsDetails bMIYearFilterWsDetails = new BMIYearFilterWsDetails();
        BMIYearFilterWsResponse bMIYearFilterWsResponse = new BMIYearFilterWsResponse();

        ArrayList<BMIYearFilterWsResponse> searchResult = new ArrayList<>();
        ArrayList<BMIYearFilterWsDetails> records = new ArrayList<>();

        List<Object[]> bmiList = patientProfileDAO.getgetBMIYearFilterWs(patientId, startDate, endDate);
//        Date previousDate = null;
//        Map<String, List<PatientBodyMassResult>> groupedByDate = bmiList.stream().collect(Collectors.groupingBy(PatientBodyMassResult::getresultDate));
//        System.out.println(groupedByDate);
//        for(Map.Entry<String, List<PatientBodyMassResult>> m : groupedByDate.entrySet()){
//            System.out.println("Key : " + m.getKey());
//        }
        String previousYear = "";
        boolean firstObj = true;
        for (Object[] bmi : bmiList) {
            bMIYearFilterWsDetails.setHeight(bmi[2].toString());
            bMIYearFilterWsDetails.setMonth(DateUtil.changeDateFormat(bmi[4].toString(), "yyyy-MM-dd mm:HH:ss", "MM"));
            bMIYearFilterWsDetails.setPulse(bmi[5].toString());
            bMIYearFilterWsDetails.setWeight(bmi[3].toString());
            bMIYearFilterWsDetails.setBmi(CommonUtil.calculateBMI(bmi[3].toString(), bmi[2].toString()));

            if (previousYear.equalsIgnoreCase(DateUtil.changeDateFormat(bmi[4].toString(), "yyyy-MM-dd mm:HH:ss", "yyyy"))) {
                records.add(bMIYearFilterWsDetails);
            } else {
                if (!firstObj) {
                    bMIYearFilterWsResponse.setRecords(records);
                    searchResult.add(bMIYearFilterWsResponse);
                    records = new ArrayList<>();
                }
                bMIYearFilterWsResponse = new BMIYearFilterWsResponse();
                String year = DateUtil.changeDateFormat(bmi[4].toString(), "yyyy-MM-dd mm:HH:ss", "yyyy");
                records.add(bMIYearFilterWsDetails);
                bMIYearFilterWsResponse.setYear(year);
                bMIYearFilterWsResponse.setRecords(records);
                previousYear = year;
            }
            bMIYearFilterWsDetails = new BMIYearFilterWsDetails();
            firstObj = false;
        }

        searchResult.add(bMIYearFilterWsResponse);
        bMIYearFilterWsMainResponse.setSearchResult(searchResult);
        return bMIYearFilterWsMainResponse;
    }

    public BMIMonthFilterWsMainResponse getBMIMonthFilterWs(int patientId, String startDate, String endDate) throws Exception {
        BMIMonthFilterWsMainResponse bMIMonthFilterWsMainResponse = new BMIMonthFilterWsMainResponse();
        BMIMonthFilterWsDetails bMIMonthFilterWsDetails = new BMIMonthFilterWsDetails();
        BMIMonthFilterWsResponse bMIMonthFilterWsResponse = new BMIMonthFilterWsResponse();

        ArrayList<BMIMonthFilterWsResponse> searchResult = new ArrayList<>();
        ArrayList<BMIMonthFilterWsDetails> records = new ArrayList<>();

        List<Object[]> bmiList = patientProfileDAO.getgetBMIMonthFilterWs(patientId, startDate, endDate);

        String previousWeek = "";
        boolean firstObj = true;
        for (Object[] bmi : bmiList) {
            bMIMonthFilterWsDetails.setHeight(bmi[0].toString());
            bMIMonthFilterWsDetails.setPulse(bmi[3].toString());
            bMIMonthFilterWsDetails.setWeight(bmi[1].toString());
            bMIMonthFilterWsDetails.setBmi(CommonUtil.calculateBMI(bmi[1].toString(), bmi[0].toString()));

            if (previousWeek.equalsIgnoreCase(bmi[2].toString())) {
                records.add(bMIMonthFilterWsDetails);
            } else {
                if (!firstObj) {
                    bMIMonthFilterWsResponse.setRecords(records);
                    searchResult.add(bMIMonthFilterWsResponse);
                    records = new ArrayList<>();
                }
                bMIMonthFilterWsResponse = new BMIMonthFilterWsResponse();
                String week = bmi[2].toString();
                records.add(bMIMonthFilterWsDetails);
                bMIMonthFilterWsResponse.setWeek(week);
                bMIMonthFilterWsResponse.setRecords(records);
                previousWeek = week;
            }
            bMIMonthFilterWsDetails = new BMIMonthFilterWsDetails();
            firstObj = false;
        }

        searchResult.add(bMIMonthFilterWsResponse);
        bMIMonthFilterWsMainResponse.setSearchResult(searchResult);
        return bMIMonthFilterWsMainResponse;
    }

    public List<BMIWeeklyDetailRapperDTO> getWeeeklyBodyMassResult(Integer patientProileSeqNo, PatientBodyMassResultDTO patientBodyMassResultDTO) throws Exception {
        ArrayList<BMIWeeklyDetailRapperDTO> searchResult = new ArrayList<>();
        ArrayList<BMIWeeklyDetailDTO> records = new ArrayList<>();

        BMIWeeklyDetailRapperDTO patientBodyMassResultMainBody = new BMIWeeklyDetailRapperDTO();
        BMIWeeklyDetailDTO bMIWeeklyDetailDTO = new BMIWeeklyDetailDTO();
        try {
            List<Object[]> patientBodyMassResultList = patientProfileDAO.getAllWeekAverageResultPatientBodyMass(patientProileSeqNo, patientBodyMassResultDTO.getStartDate(), patientBodyMassResultDTO.getEndDate());
            String previousDay = "";
            boolean firstObj = true;
            for (Object[] massResult : patientBodyMassResultList) {
                bMIWeeklyDetailDTO.setHeight(massResult[2].toString());
                bMIWeeklyDetailDTO.setWeight(massResult[3].toString());
                bMIWeeklyDetailDTO.setPulse(massResult[5].toString());
                bMIWeeklyDetailDTO.setTime(DateUtil.changeDateFormat(massResult[4].toString(), "yyyy-MM-dd HH:mm:ss", "HH:mm:ss"));
                bMIWeeklyDetailDTO.setBmi(CommonUtil.calculateBMI(massResult[3].toString(), massResult[2].toString()));
//            records.add(bMIWeeklyDetailDTO);
                if (previousDay.equalsIgnoreCase(DateUtil.changeDateFormat(massResult[4].toString(), "yyyy-MM-dd HH:mm:ss", "dd"))) {
                    records.add(bMIWeeklyDetailDTO);
                } else {
                    if (!firstObj) {
                        patientBodyMassResultMainBody.setRecords(records);
                        searchResult.add(patientBodyMassResultMainBody);
                        records = new ArrayList<>();
                    }

                    patientBodyMassResultMainBody = new BMIWeeklyDetailRapperDTO();
                    String day = DateUtil.changeDateFormat(massResult[4].toString(), "yyyy-MM-dd HH:mm:ss", "dd");
                    records.add(bMIWeeklyDetailDTO);
                    patientBodyMassResultMainBody.setDay(day);
                    patientBodyMassResultMainBody.setRecords(records);
                    previousDay = day;
                }
                bMIWeeklyDetailDTO = new BMIWeeklyDetailDTO();
                firstObj = false;
            }
            searchResult.add(patientBodyMassResultMainBody);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# patientProfileService-> getWeeeklyBodyMassResult");
        }

        return searchResult;
    }

    public List<BMIWeeklyDetailRapperDTO> getDetailWeeeklyBodyMassResult(Integer patientProileSeqNo, PatientBodyMassResultDTO patientBodyMassResultDTO) throws Exception {
        ArrayList<BMIWeeklyDetailRapperDTO> searchResult = new ArrayList<>();
        ArrayList<BMIWeeklyDetailDTO> records = new ArrayList<>();

        BMIWeeklyDetailRapperDTO patientBodyMassResultMainBody = new BMIWeeklyDetailRapperDTO();
        BMIWeeklyDetailDTO bMIWeeklyDetailDTO = new BMIWeeklyDetailDTO();
        try {
            List<Object[]> patientBodyMassResultList = patientProfileDAO.getAllWeekDetailResultPatientBodyMass(patientProileSeqNo, patientBodyMassResultDTO.getStartDate(), patientBodyMassResultDTO.getEndDate());
            String previousDay = "";
            boolean firstObj = true;
            for (Object[] massResult : patientBodyMassResultList) {
                bMIWeeklyDetailDTO.setHeight(massResult[2].toString());
                bMIWeeklyDetailDTO.setWeight(massResult[3].toString());
                bMIWeeklyDetailDTO.setPulse(massResult[5].toString());
                bMIWeeklyDetailDTO.setTime(DateUtil.changeDateFormat(massResult[4].toString(), "yyyy-MM-dd HH:mm:ss", "HH:mm:ss"));
                bMIWeeklyDetailDTO.setBmi(CommonUtil.calculateBMI(massResult[3].toString(), massResult[2].toString()));
//            records.add(bMIWeeklyDetailDTO);
                if (previousDay.equalsIgnoreCase(DateUtil.changeDateFormat(massResult[4].toString(), "yyyy-MM-dd HH:mm:ss", "dd"))) {
                    records.add(bMIWeeklyDetailDTO);
                } else {
                    if (!firstObj) {
                        patientBodyMassResultMainBody.setRecords(records);
                        searchResult.add(patientBodyMassResultMainBody);
                        records = new ArrayList<>();
                    }

                    patientBodyMassResultMainBody = new BMIWeeklyDetailRapperDTO();
                    String day = DateUtil.changeDateFormat(massResult[4].toString(), "yyyy-MM-dd HH:mm:ss", "dd");
                    records.add(bMIWeeklyDetailDTO);
                    patientBodyMassResultMainBody.setDay(day);
                    patientBodyMassResultMainBody.setRecords(records);
                    previousDay = day;
                }
                bMIWeeklyDetailDTO = new BMIWeeklyDetailDTO();
                firstObj = false;
            }
            searchResult.add(patientBodyMassResultMainBody);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# patientProfileService-> getWeeeklyBodyMassResult");
        }

        return searchResult;
    }

    public List<Question> getQuestionsList(String surveyId, int practiceId, String patientPhoneNumber) throws Exception {
        List<Object[]> list = patientProfileDAO.getQuestionList(surveyId, practiceId, EncryptionHandlerUtil.getDecryptedString(patientPhoneNumber));
        List<Question> listTemp = new ArrayList<>();
        Question question = new Question();
        for (Object[] obj : list) {
            question.setQuestionId(Integer.parseInt(obj[0].toString()));
            question.setNarrative(obj[1].toString());
            question.setEndDate(obj[3] == null ? null : obj[3].toString());
            question.setEffectiveDate(obj[2].toString());
            question.setOrgUnitId(Integer.parseInt(obj[4].toString()));
            listTemp.add(question);
            question = new Question();
        }
        return listTemp;
    }

    public List<SurveyBridgeDTO> getSurveyList(String mobileNumber) throws Exception {
        List<SurveyBridgeDTO> list = new ArrayList<>();
        try {
            List<SurveyBridge> survyList = patientProfileDAO.getSurveyListByMobileNumber(mobileNumber);
            for (SurveyBridge srvey : survyList) {
                SurveyBridgeDTO surveyBridgeDTO = new SurveyBridgeDTO();
                if (!"CM".equalsIgnoreCase(srvey.getSurveyStatusCode())) {
                    surveyBridgeDTO.setUniqueKey(srvey.getUniqueKey());
                    surveyBridgeDTO.setOrgUnitId(srvey.getOrgUnitId());
                    surveyBridgeDTO.setPatientPhonNumber(srvey.getPatientPhonNumber());
                    surveyBridgeDTO.setSource(srvey.getSource());
                    surveyBridgeDTO.setSurveyStatusCode(srvey.getSurveyStatusCode());
                    surveyBridgeDTO.setPracticeId(srvey.getPracticeId());
                    surveyBridgeDTO.setEffectiveDate(srvey.getEffectiveDate());
                    surveyBridgeDTO.setEndDate(srvey.getEndDate());
                }
                list.add(surveyBridgeDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getSurveyList", e);
        }
        return list;

    }

    public Boolean getSurvyPushNotification(long surveyId, PatientProfile profile, String message, String eventName) {
        try {
            CampaignMessages dbcampaignMessages = this.getNotificationMsgs(message, eventName);
            if (dbcampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            String text = AppUtil.getSafeStr(dbcampaignMessages.getSmstext(), "");
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbcampaignMessages);
            Practices dbPractise = patientProfileDAO.getPractiseNameById(profile.getPracticeId());
            String surveyIdd = "";
            Long assignSurveyId = 0L;
            AssignedSurvey dbSurveyLogs = patientProfileDAO.getSurveyLogsBySurveyIdd(profile.getPatientProfileSeqNo(), surveyId);
            if (dbSurveyLogs != null) {
                surveyIdd = dbSurveyLogs.getId() != null ? dbSurveyLogs.getId().toString() : "";
//                  surveyIdd = dbSurveyLogs.getId()!= null? dbSurveyLogs.getSurvey().getId().toString() : "";
                assignSurveyId = dbSurveyLogs.getId() != null ? dbSurveyLogs.getId() : 0L;

            }
//            campaignMessages.setSmstext(surveyIdd);
            String push = AppUtil.getSafeStr(dbcampaignMessages.getPushNotification(), "");
            campaignMessages.setPushNotification(push);
            saveNotificationMessages(campaignMessages, Constants.NO, profile.getPatientProfileSeqNo(), assignSurveyId, dbPractise, campaignMessages.getSmstext());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getSurvyPushNotification -> notification not sent.", e);
            return false;
        }
        return true;
    }

    public Boolean sendPushNotificationOnPlaceOrder(PatientProfile patientProfile, Order order, String message, String eventName) {
        try {
            // Order order = (Order) patientProfileDAO.findRecordById(new Order(), OrderId);
            if (order == null) {
                logger.error("No order exist against this order# " + order.getId());
                return false;
            }
            CampaignMessages dbCampaignMessages = this.getNotificationMsgs(message, eventName);
            if (dbCampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages);
            Practices dbPractise = patientProfileDAO.getPractiseNameById(patientProfile.getPracticeId());
            logger.info("patientProfile.getPracticeId() :"+patientProfile.getPracticeId());

            logger.info("DateUtil.dateToString(new Date(), \"E, MMMM dd yyyy\")" + DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"));
            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, order.getId(), dbPractise));

            saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order, campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
        } catch (Exception e) {
            logger.error("PatientProfileService# sendPushNotificationOnPlaceOrder# aginst :" + message + ": ", e);
            return false;
        }
        return true;
    }
     public Boolean sendHCPGenralPushNotification(PatientProfile patientProfile, String message, String eventName) {
        try {
            
            CampaignMessages dbCampaignMessages = this.getNotificationMsgs(message, eventName);
            if (dbCampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages);
            Practices dbPractise = patientProfileDAO.getPractiseNameById(patientProfile.getPracticeId());
            logger.info("patientProfile.getPracticeId() :"+patientProfile.getPracticeId());

            logger.info("DateUtil.dateToString(new Date(), \"E, MMMM dd yyyy\")" + DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"));
            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(pushNot);
            saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo());
//            saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order, campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
        } catch (Exception e) {
            logger.error("PatientProfileService# sendPushNotificationOnPlaceOrder# aginst :" + message + ": ", e);
            return false;
        }
        return true;
    }

    public Boolean sendNotification(Integer profileId, String messge) {
        try {
            CampaignMessages dbCampaignMessages = this.getNotificationMsgs(messge, Constants.EVENTNAME);
            if (dbCampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages);
            ////////////////////////////////////////////////////////////////

            String message = AppUtil.getSafeStr(dbCampaignMessages.getSmstext(), "");
//            String message = AppUtil.getSafeStr(campaignMessages.getSmstext(), "");
            campaignMessages.setSmstext(message.replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "E, MMMM dd yyyy")));

            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, "", null));
            this.saveNotificationMessages(campaignMessages, Constants.NO, profileId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getSurvyPushNotification -> notification not sent.");
            return false;
        }
        return true;
    }

    public List<SurveyBridge> getSurveyBridgeRecord() throws Exception {
        List<SurveyBridge> list = new ArrayList<>();
        List<SurveyBridge> listHours = new ArrayList<>();
        List<SurveyBridge> listFortyHours = new ArrayList<>();
        List<SurveyBridge> listSeventyTwoHours = new ArrayList<>();
        try {
            listHours = patientProfileDAO.getSurveyBridgerecordOldOneDay();
            listFortyHours = patientProfileDAO.getSurveyBridgeRecordOldTwoDays();
            listSeventyTwoHours = patientProfileDAO.getSurveyBridgeRecordOld3rdDays();
            list.addAll(listHours);
            list.addAll(listFortyHours);
            list.addAll(listSeventyTwoHours);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PatientAllergies> getAllergyList(int patientId) throws Exception {
        List<PatientAllergies> list = new ArrayList<>();
        try {
            List<PatientAllergies> allergieslist = patientProfileDAO.getAllergyList(patientId);
            for (PatientAllergies patientAllergies : allergieslist) {
                PatientAllergies patientAllergiesdto = new PatientAllergies();
                patientAllergiesdto.setId(patientAllergies.getId());
                patientAllergiesdto.setAllergies(patientAllergies.getAllergies());
                list.add(patientAllergiesdto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getAllergyList", e);
        }
        return list;

    }

    public List<Object> getSurveQuestionList(String phoneNumber, String surveyId) {
        List<Object> list = new ArrayList<>();

        try {
//            int yessId=0;
//            int noId=0;
            List<Object[]> listsurvey = patientProfileDAO.getQuestionListByPhneNumber(phoneNumber, surveyId);
            for (Object[] listQuestion : listsurvey) {
                QuestionAnswerDTO questionAnserDTO = new QuestionAnswerDTO();
                questionAnserDTO.setQuestionId(Long.parseLong(listQuestion[0].toString()));// Integer.parseInt());
                questionAnserDTO.setQuestion(listQuestion[1].toString());
                ArrayList<AnswerDetailDTO> anserRecord = new ArrayList<>();
                AnswerDetailDTO answerDetailDTO = new AnswerDetailDTO();
                answerDetailDTO.setId(1);
                answerDetailDTO.setLablel("yes");
                anserRecord.add(answerDetailDTO);

                AnswerDetailDTO answerDetailDTO1 = new AnswerDetailDTO();
                answerDetailDTO1.setId(2);
                answerDetailDTO1.setLablel("no");
                anserRecord.add(answerDetailDTO1);

                questionAnserDTO.setAnswers(anserRecord);
                list.add(questionAnserDTO);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateQuestionReply(String mobile, String surveyId, List<surveyQuestionAnswerDTO> qRDTO) {
        try {
            this.patientProfileDAO.updatePractiseQuestionAnswerReply(mobile, surveyId, qRDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveAndUpdateAddresses(PatientProfile patientProfile, PatientDeliveryAddressDTO addressDto) {
        boolean isSaved = false;
        PatientDeliveryAddress dbpatientAddress = new PatientDeliveryAddress();

        try {
            State state = (State) patientProfileDAO.findRecordById(new State(), addressDto.getStateId());
            if (!CommonUtil.isNullOrEmpty(addressDto.getAddressId())) {
                dbpatientAddress = (PatientDeliveryAddress) patientProfileDAO.findRecordById(new PatientDeliveryAddress(), addressDto.getAddressId());
                dbpatientAddress.setUpdatedOn(new Date());
            } else {
                dbpatientAddress.setUpdatedOn(new Date());
                dbpatientAddress.setCreatedOn(new Date());
            }

            if (!"PermanentAddress".equalsIgnoreCase(addressDto.getAddressType())) {
//                dbpatientAddress.setDefaultAddress(Constants.NO);
//            } else {
//                dbpatientAddress.setDefaultAddress(Constants.NO);
                dbpatientAddress.setCommenceDate(addressDto.getCommenseDate());
                dbpatientAddress.setCeaseDate(addressDto.getCeaseDate());
            }
            populatePatientDeliveryAddress(dbpatientAddress, addressDto, state, patientProfile);
            int profileId = dbpatientAddress.getPatientProfile().getPatientProfileSeqNo();
            if (!CommonUtil.isNullOrEmpty(addressDto.getAddressId())) {
                patientProfileDAO.update(dbpatientAddress);
            } else {
                patientProfileDAO.save(dbpatientAddress);
            }
//            patientProfileDAO.saveOrUpdate(dbpatientAddress);
            isSaved = true;
            saveActivitesHistory(ActivitiesEnum.ADDRESS_CHANGE.getValue(), patientProfile, "", addressDto.getAddress(), "","");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> saveAndUpdateAddresses", e);
            isSaved = false;
        }
        return isSaved;
    }

    private void populatePatientDeliveryAddress(PatientDeliveryAddress dbpatientAddress, PatientDeliveryAddressDTO addressDto, State state, PatientProfile patientProfile) {
        dbpatientAddress.setAddress(addressDto.getAddress());
        dbpatientAddress.setCity(addressDto.getCity());
        dbpatientAddress.setState(state);
        dbpatientAddress.setZip(addressDto.getZip());
        dbpatientAddress.setAddressType(addressDto.getAddressType());
        dbpatientAddress.setPatientProfile(patientProfile);
    }

    public boolean markCurrentAddressAsDefault(int addressId, int patientProfileSeqNo) throws Exception {
        boolean isUpdate = false;
        try {
            PatientDeliveryAddress patientDeliveryAddress = patientProfileDAO.getPatientDeliveryAddressById(patientProfileSeqNo, addressId);
            if (patientDeliveryAddress != null && !CommonUtil.isNullOrEmpty(addressId)) {
                updatePreviousDefaultAddress(patientProfileSeqNo, Constants.YES);
                patientDeliveryAddress.setDefaultAddress(Constants.YES);
                patientDeliveryAddress.setUpdatedOn(new Date());
                patientProfileDAO.update(patientDeliveryAddress);
                isUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> markCurrentAddressAsDefault", e);
            return false;
        }
        return isUpdate;
    }

    public ErrorDto deleteAndUpdateAllergies(int profileId, int allergyId, String allergyName, String action) throws Exception {
        ErrorDto errorDto = new ErrorDto();
        boolean isUpdate = false;
        PatientProfile patientProfile = new PatientProfile(profileId);
        String allergyNa = patientProfileDAO.getAllergyNameByAllergyId(profileId, allergyId);
        try {
            if ("Delete".equalsIgnoreCase(action)) {
                isUpdate = patientProfileDAO.deleteAllergies(profileId, allergyId);
                if (isUpdate = true) {
                    errorDto.setValue(1);
                }
                saveActivitesHistory(ActivitiesEnum.DELETE_ALLERGY.getValue(), patientProfile, "", allergyNa, "","");
            } else {
                PatientAllergies allergy2 = patientProfileDAO.getAllergy(profileId, allergyName);
                String name = "";
                if (allergy2 != null) {
                    name = allergy2.getAllergies();
                }
                PatientAllergies allergy = patientProfileDAO.AllergyeExist(profileId, allergyId);
                if (!allergy.getAllergies().equals(allergyName) && !allergyName.equalsIgnoreCase(name)) {
                    isUpdate = patientProfileDAO.UpdateAllergies(profileId, allergyName, allergyId);
                    if (isUpdate = true) {
                        errorDto.setValue(2);
                    }
                    saveActivitesHistory(ActivitiesEnum.EDIT_ALLERGY.getValue(), patientProfile, "", allergyName, "","");
                } else {
                    errorDto.setValue(3);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> DeleteAndUpdateAllergies", e);
//            isUpdate = false;
            errorDto.setValue(4);
        }
        return errorDto;
    }

    public RefillOrderDTO getPaymentDueOrderList(int profileId) throws Exception {
        RefillOrderDTO refillOrderDTO = new RefillOrderDTO();
//        List<RefillOrderDTO> oderList = new ArrayList<>();
        try {
            List<Order> orderList = patientProfileDAO.getOrderListByStatus(profileId, Constants.ORDER_STATUS.MBR_TO_PAY_ID);
            refillOrderDTO.setPaymentDueOrderCount(!CommonUtil.isNullOrEmpty(orderList) ? orderList.size() : 0);
            if (!CommonUtil.isNullOrEmpty(orderList)) {
                Set<OrderDetailDTO> listorder = setOrderList2(orderList, "", "");
                refillOrderDTO.setPaymentDueOrderList(listorder);
            }
//            for(Order ord : orderList){
//               refillOrderDTO.setRxOutOfPocket(ord.getRxPatientOutOfPocket());
//               
//            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> getPaymentDueOrderList", e);
        }
        return refillOrderDTO;
    }

    public EnrollemntIpadDTO enrollmentData(int profileId) throws Exception {
        EnrollemntIpadDTO enrollemntIpadDTO = new EnrollemntIpadDTO();
        EnrollementIpad dbenrollementIpad = new EnrollementIpad();
        try {

            dbenrollementIpad = patientProfileDAO.getEnrollemtRecord(profileId);
            enrollemntIpadDTO.setEnrollemtStatus(dbenrollementIpad.getEnrollemtStatus());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> enrollmentData", e);

        }
        return enrollemntIpadDTO;
    }

    public EnrollementIpad getEnrollementByPAtientId(int profileId) throws Exception {
        EnrollementIpad enrollementIpad = new EnrollementIpad();
        try {
            enrollementIpad = patientProfileDAO.getEnrollemtRecord(profileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enrollementIpad;
    }

    public PatientAllergies getAllergyIfExist(int patientId, String alergyName) throws Exception {
        PatientAllergies allergieslist = new PatientAllergies();
        try {
            allergieslist = patientProfileDAO.getAllergy(patientId, alergyName);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getAllergy", e);
        }
        return allergieslist;
    }

    public boolean getDeletePatientProfile(String phone) throws Exception {
        boolean isUpdate = false;
        try {

            isUpdate = patientProfileDAO.DeletePatientProfileRecordFromAllOrphens(phone);
            if (isUpdate = true) {
                isUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getAllergy", e);
            isUpdate = false;
        }
        return isUpdate;
    }

    public List<ActivityHistoryDTO> getActivityHistory() {
        List<ActivityHistoryDTO> list = new ArrayList<>();
        try {
            List<ActivitesHistory> historyList = patientProfileDAO.getActivityHistory();
            for (ActivitesHistory listHistory : historyList) {
                ActivityHistoryDTO ActivityHistoryDTO = new ActivityHistoryDTO();
                ActivityHistoryDTO.setActivityNumber(listHistory.getId());
                ActivityHistoryDTO.setActivityName(listHistory.getActivityName());
                ActivityHistoryDTO.setRxNumber(Integer.parseInt(listHistory.getRxNumber()));
                ActivityHistoryDTO.setDateTime(listHistory.getCreatedOn());
                ActivityHistoryDTO.setActivityDetails(listHistory.getActivityDetail());
                ActivityHistoryDTO.setReadingTime(listHistory.getReadingTime());
                list.add(ActivityHistoryDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getActivityHistory", e);
        }
        return list;
    }

    public LoginDTO papulateProfileUserData(PatientProfile patientProfile) {
        LoginDTO loginDTO = new LoginDTO();
        try {
            Practices practiseDb;
            loginDTO = CommonUtil.populateProfileUserData(patientProfile);
            if (patientProfile.getPracticeId() != null) {
                practiseDb = patientProfileDAO.getPractiseNameById(patientProfile.getPracticeId());
            } else {
                practiseDb = patientProfileDAO.getPractiseNameById(patientProfile.getPhysicianPracticeId());
            }
            if (practiseDb != null) {
                loginDTO.setPracticeName(practiseDb.getPracticename());
                loginDTO.setPracticeCode(practiseDb.getPracticeCode());
                String url = practiseDb.getPracticeLogo()!= null ? PropertiesUtil.getProperty("PHP_WEB_PDF") +practiseDb.getPracticeLogo():"";
                loginDTO.setPracticeLogo(UriUtils.encodePath(url, Charsets.UTF_8.toString()));
                System.out.println(UriUtils.encodePath(url, Charsets.UTF_8.toString()));
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# papulateProfileUserDate", e);
        }
        return loginDTO;
    }

    public boolean updateStatusById(int profileId, String orderId) throws Exception {
        boolean isUpdate = false;
        try {
//
//              List<Order> orderListee = patientProfileDAO.getOrdersListByProfileId(profileId);
//              for(Order lst2 : orderListee){
//              if(lst2.getId().contains(s)){
//              isUpdate = false;
//              }
//              }
            isUpdate = patientProfileDAO.updateOrderStatusById(profileId, orderId);
//            for (OrdersDTO lst : orderList) {
//                isUpdate = patientProfileDAO.updateOrderStatusById(profileId, orderId);
////                signatureImg(signatureImage, lst.getOrderId());
//            }           
            if (isUpdate == true) {
                isUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getAllergy", e);
            isUpdate = false;
        }
        return isUpdate;
    }

    public boolean signatureImg(MultipartFile signature, String orderId) throws Exception {
        boolean isUpdate = false;
        try {
            if (signature != null && (!signature.isEmpty())) {
                Integer patientId = 0;
                Order order = (Order) this.findOrderById(orderId);
                PatientProfile patientProfile = order.getPatientProfile();
                if (Objects.equals(patientProfile.getPatientProfileSeqNo(), patientId)) {
                    logger.info("Patient Signature already saved against this " + patientId);
                    isUpdate = false;
                }
                patientId = patientProfile.getPatientProfileSeqNo();
                String dateStr = DateUtil.dateToString(new Date(), "yy-MM-dd hh:mm:ss");
                dateStr = dateStr.replace(":", "-");
                dateStr = dateStr.replace(" ", "-");
                String ext = FileUtil.determineImageFormat(signature.getBytes());
                String completeName = "Sign_" + patientId + "_" + dateStr + "." + ext;
                String signatureImagPath = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "orderimages/" + completeName;
                logger.info("Complete signature Image Path: " + signatureImagPath);

                FileCopyUtils.copy(signature.getBytes(),
                        new File(PropertiesUtil.getProperty("ORDER_IMAGES_CARD_PATH") + completeName));
                CommonUtil.executeCommand(PropertiesUtil.getProperty("COMMAND"));
                patientProfile.setSignature(signatureImagPath);

                patientProfileDAO.saveOrUpdate(patientProfile);
                isUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    public boolean deleteCurrentAddress(int patientId, int addressId) {
        boolean isDelete = false;
        try {
            isDelete = patientProfileDAO.deleteCurrentAddress(addressId, patientId);
            if (isDelete == true) {
                isDelete = true;
            } else {
                isDelete = false;
            }

        } catch (Exception e) {
            logger.error("PatientProfileService# deleteCurrentAddress", e);
            isDelete = false;
        }
        return isDelete;
    }

    public PatientProfile getPatientProfileByProfileId(int profileId) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileByPatientIdr(profileId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getPatientProfileByProfileId", e);
        }
        return patientProfile;
    }

    public PatientProfile getPatientProfileByProfileIdWithOutStatus(int profileId) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileByPatientIdwithOutStatusBoundery(profileId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getPatientProfileByProfileId", e);
        }
        return patientProfile;
    }

    public GenralDTO savedOrderQuestion(PatientProfile profile, String question, String img, String orderId, Long questionId) {
//        boolean isSaved = false;
           GenralDTO genralDTO = new GenralDTO();
        try {           
             if ((!(orderId.equals("0") || CommonUtil.isNullOrEmpty(orderId)) && CommonUtil.isNullOrEmpty(questionId)) || 
                    (CommonUtil.isNullOrEmpty(orderId) && (!CommonUtil.isNullOrEmpty(questionId))) ) {
                Order order = (Order) this.findOrderById(orderId);
                MessageThreads msgThread = new MessageThreads();
                Practices dbPractice = patientProfileDAO.getPractiseNameById(profile.getPracticeId());
                if (dbPractice != null) {
                    msgThread.setPractice(dbPractice);
                }
                 if (!CommonUtil.isNullOrEmpty(questionId)) {
                     msgThread.setQuestionId(questionId);                
                 }
                 if (order != null) {
                      msgThread.setOrder(order);
                 }
                msgThread.setProfile(profile);                
                msgThread.setAttachment(img);
                msgThread.setMessge(question);
                msgThread.setMesssgeType("Pharmacy");
                msgThread.setMessgeStaus("received");
                msgThread.setMarkAsRead(Boolean.FALSE);
                msgThread.setCreatedAt(new Date());
                this.save(msgThread);
                genralDTO.setServerDate(msgThread.getCreatedAt().getTime());
                if(msgThread.getOrder()!=null) {
                Practices practice = patientProfileDAO.getPracticesById(msgThread.getOrder().getPracticeId());
                genralDTO.setPresciberName(practice != null ? practice.getPracticename() : "");
                genralDTO.setPracticeLogo(practice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +practice.getPracticeLogo():"");
                //                 newOrder.setPracticeLogo(practice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +practice.getPracticeLogo():"");
                }
//                isSaved = true;
//                 if (isSaved == true) {
                     if (order != null) {
                         saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", msgThread.getMessge() + " type: Order Message", "",orderId);
                     } else {
                         saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", msgThread.getMessge() + " type: Genral chat", "",orderId);
                     }
//                 }
            } else if (CommonUtil.isNullOrEmpty(orderId) && questionId == 0l ) {
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setQuestionType("General_Question");
                questionAnswer.setPatientProfile(profile);
                questionAnswer.setQuestion(question);
                questionAnswer.setQuestionImge(img);
                questionAnswer.setIsRead(Boolean.FALSE);
                questionAnswer.setQuestionTime(new Date()); 
                patientProfileDAO.save(questionAnswer);
//                isSaved = true;
//                if (isSaved == true) {
                    saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", questionAnswer.getQuestion() + " type:" + questionAnswer.getQuestionType(), "","");
//                }
                //////////////////////
//                Order order = (Order) this.findOrderById(orderId);
                MessageThreads msgThread = new MessageThreads();
                Practices dbPractice = patientProfileDAO.getPractiseNameById(profile.getPracticeId());
                if (dbPractice != null) {
                    msgThread.setPractice(dbPractice);
                }
                msgThread.setQuestionId(questionAnswer.getId()); 
                msgThread.setProfile(profile);                
                msgThread.setAttachment(img);
                msgThread.setMessge(question);
                 msgThread.setMesssgeType("Pharmacy");
                msgThread.setMessgeStaus("received");
                msgThread.setMarkAsRead(Boolean.FALSE);
                msgThread.setCreatedAt(new Date()); 
                this.save(msgThread);
                genralDTO.setServerDate(msgThread.getCreatedAt().getTime());
                if(msgThread.getOrder()!=null) {
                Practices practice = patientProfileDAO.getPracticesById(msgThread.getOrder().getPrescriberId());
                genralDTO.setPresciberName(practice != null ? practice.getPracticename() : "");
                 Practices practices = patientProfileDAO.getPracticesById(msgThread.getOrder().getPrescriberId());
                 if(practices != null){
                     genralDTO.setPracticeLogo(practices.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +practices.getPracticeLogo():"");
                 }
         
//                practice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +practice.getPracticeLogo():"");
                
                }
                ///////////////////////
            } else {
//               isSaved = false;
            }
            
            
            /*old work flow before 27-Agust-2020*/
//            if (!(orderId.equals("0") || CommonUtil.isNullOrEmpty(orderId))) {
//                Order order = (Order) this.findOrderById(orderId);
////                questionAnswer.setQuestionType("Order_Question");
////                questionAnswer.setOrder(order);
//
//                MessageThreads msgThread = new MessageThreads();
//                Practices dbPractice = patientProfileDAO.getPractiseNameById(profile.getPracticeId());
//                if (dbPractice != null) {
//                    msgThread.setPractice(dbPractice);
//                }
//                msgThread.setOrder(order);
//                msgThread.setProfile(profile);
//                msgThread.setAttachment(img);
//                msgThread.setMessge(question);
//                msgThread.setMessgeStaus("received");
//                msgThread.setMarkAsRead(Boolean.FALSE);
//                msgThread.setCreatedAt(new Date());
//                this.save(msgThread);
//                isSaved = true;
//                if (isSaved == true) {
//                    saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", msgThread.getMessge() + " type: Order Message", "");
//                }
//            } else {
//                QuestionAnswer questionAnswer = new QuestionAnswer();
//                questionAnswer.setQuestionType("General_Question");
//                questionAnswer.setPatientProfile(profile);
//                questionAnswer.setQuestion(question);
//                questionAnswer.setQuestionImge(img);
//                questionAnswer.setIsRead(Boolean.FALSE);
//                questionAnswer.setQuestionTime(new Date());
//                if (!CommonUtil.isNullOrEmpty(notificationMsgId)) {
//                    NotificationMessages messages = (NotificationMessages) patientProfileDAO.findRecordById(new NotificationMessages(), notificationMsgId);
//                    questionAnswer.setNotificationMessages(messages);
//                }
//                patientProfileDAO.save(questionAnswer);
//                isSaved = true;
//                if (isSaved == true) {
//                    saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", questionAnswer.getQuestion() + " type:" + questionAnswer.getQuestionType(), "");
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# savedOrderQuestion", e);
        }
        return genralDTO;
    }
    public boolean unsubscribePatient(int patientId) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        boolean isUnsubscribe = false;
        try {
            EnrollementIpad dbEnrollementIpad = (EnrollementIpad) this.findRecordById(new EnrollementIpad(), patientId);
            dbEnrollementIpad.setEnrollemtStatus("Unsubsucribe");
            dbEnrollementIpad.setUpdatedAt(new Date());
            patientProfileDAO.update(dbEnrollementIpad);
            isUnsubscribe = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# savedOrderQuestion", e);
        }
        return isUnsubscribe;
    }
//////////////////////////////////Changes By Jandal /////////////////////

    public List<Object> getFAQS() {
        List<Object> list = new ArrayList<>();

        try {
            List<Faq> QAlist = patientProfileDAO.getFAQuestions();
            for (Faq listQA : QAlist) {
                Faq_DTO FAQuestion = new Faq_DTO();
                FAQuestion.setId(listQA.getId());
                FAQuestion.setQuestion(listQA.getQuestion());
                FAQuestion.setAnswer(listQA.getAnswer());
                list.add(FAQuestion);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<QuestionAnswerDTO> getPatientGenralQuestionList(int profileId, String request) throws ParseException, Exception {
        List<QuestionAnswerDTO> list = new ArrayList<>();
         List<Object[]> questionList;
        try {
            if("HCP".equals(request)){
               questionList = patientProfileDAO.getPatientHcpGenralQuestionById(profileId);
            } else {
               questionList = patientProfileDAO.getPatientQuestionById(profileId);
            }
            if (!CommonUtil.isNullOrEmpty(questionList)) {
                for (Object[] listQuestion : questionList) {
                    QuestionAnswerDTO questionlist = new QuestionAnswerDTO();
                    if ("HCP".equals(request)) {
                        if (listQuestion[5] != null) {
                            Practices preactice = patientProfileDAO.getPracticesById(Integer.parseInt(listQuestion[5].toString()));
                            questionlist.setPrescriberName(preactice != null ? preactice.getPracticename() : "");
                            questionlist.setPracticeLogo(preactice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +preactice.getPracticeLogo():"");
                            questionlist.setGenralQuestionReleventChatCount(patientProfileDAO.getGenralQuestionReleventHCPChatCount(Long.parseLong(listQuestion[0].toString())));
                        }
                    } else {
                        questionlist.setGenralQuestionReleventChatCount(patientProfileDAO.getGenralQuestionReleventChatCount(Long.parseLong(listQuestion[0].toString())));
                    }
                    questionlist.setQuestionId(Long.parseLong(listQuestion[0].toString()));// Integer.parseInt(listQuestion[0].toString()));
                    questionlist.setOrderId(listQuestion[1] != null ? listQuestion[1].toString() : "");
                    questionlist.setQuestion(listQuestion[2].toString());
//                    questionlist.setQuestiondate(DateUtil.changeDateFormat(listQuestion[3].toString(), "yyyy-MM-dd hh:mm:ss", "MM/dd/yyyy hh:mm:ss"));
                    Date dd = patientProfileDAO.getMessageThreadDateOfLastMessage(Long.parseLong(listQuestion[0].toString()));
                    if (dd != null) {
                        questionlist.setQuestiondate(DateUtil.dateToString(dd, Constants.USA_DATE_TIME_SECOND_FORMATE_12H));
                    }
                    //same above param Questiondate mobile team need messge thread table date like bewlo im giving them. but same pattern like above 
                    questionlist.setIsRead(Boolean.parseBoolean(listQuestion[4].toString()));
//                    questionlist.setGenralQuestionReleventChatCount(patientProfileDAO.getGenralQuestionReleventHCPChatCount(Long.parseLong(listQuestion[0].toString())));
                    questionlist.setLastchatmessageDate(patientProfileDAO.getMessageThreadDateOfLastMessage(Long.parseLong(listQuestion[0].toString())));
                    list.add(questionlist);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<QuestionAnswerDTO> getPatientQuestionListByDate(int profileId, QuestionAnswerDTO qadto) throws ParseException, Exception {
        List<QuestionAnswerDTO> list = new ArrayList<>();
        qadto.setStartDate(DateUtil.changeDateFormat(qadto.getStartDate(), "MM/dd/yyyy", "yyyy-MM-dd"));
        qadto.setEndDate(DateUtil.changeDateFormat(qadto.getEndDate(), "MM/dd/yyyy", "yyyy-MM-dd"));
        try {
            List<Object[]> Questionlist = patientProfileDAO.getPatientQuestionByDate(profileId, DateUtil.stringToDate(qadto.getStartDate(), "yyyy-MM-dd"), DateUtil.stringToDate(qadto.getEndDate(), "yyyy-MM-dd"));
//            List<MessageThreads> messgesList = patientProfileDAO.getPatientMessagesByDate(profileId, DateUtil.stringToDate(qadto.getStartDate(), "yyyy-MM-dd"), DateUtil.stringToDate(qadto.getEndDate(), "yyyy-MM-dd"));
            for (Object[] listQuestion : Questionlist) {
                QuestionAnswerDTO questionlist = new QuestionAnswerDTO();
                questionlist.setQuestionId(Long.parseLong(listQuestion[0].toString()));// Integer.parseInt(listQuestion[0].toString()));
//                questionlist.setOrrderId(listQuestion[1].toString());
                questionlist.setOrderId(listQuestion[1] != null ? listQuestion[1].toString() : "");
                questionlist.setQuestion(listQuestion[2].toString());
                questionlist.setQuestiondate(DateUtil.changeDateFormat(listQuestion[3].toString(), "yyyy-MM-dd hh:mm:ss", "MM/dd/yyyy"));
                questionlist.setIsRead(Boolean.parseBoolean(listQuestion[4].toString()));
                list.add(questionlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

 public List<QuestionAnswerDTO> getQuestionAnswerImageByID(int profileId, QuestionAnswerDTO questionAnswerDTO, String msgType) throws ParseException, Exception {
        List<QuestionAnswerDTO> list = new ArrayList<>();
        try {
            if (!CommonUtil.isNullOrEmpty(questionAnswerDTO.getOrderId())) {
                List<MessageThreads> messageThreadsList = patientProfileDAO.getMessageThreadByOrderId(profileId, questionAnswerDTO.getOrderId(), msgType);
                for (MessageThreads dbmsgist : messageThreadsList) {
                    dbmsgist.setMarkAsRead(Boolean.TRUE);
                    dbmsgist.setUpdateAt(new Date());
                    this.update(dbmsgist);
                    QuestionAnswerDTO questionanswerDTO = new QuestionAnswerDTO();
                    questionanswerDTO.setId(dbmsgist.getId());
                    questionAnswerDTO.setMessageStatus(dbmsgist.getMessgeStaus());
                    questionanswerDTO.setName(dbmsgist.getMessgeStaus());
                    questionanswerDTO.setQuestionImge(dbmsgist.getAttachment()!= null ? dbmsgist.getAttachment() : "");
                   // questionanswerDTO.setQuestionImge(dbmsgist.getAttachment()!=null ? PropertiesUtil.getProperty("PHP_WEB_PDF") +dbmsgist.getAttachment() : "");
                    questionanswerDTO.setAnswer(dbmsgist.getMessge());
                    questionanswerDTO.setQuestion(dbmsgist.getMessge());
                    questionanswerDTO.setIsRead(dbmsgist.getMarkAsRead());
                    questionanswerDTO.setQuestionTimeStr(DateUtil.changeDateFormat(DateUtil.dateToString(dbmsgist.getCreatedAt(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm:ss", "MM/dd/yyyy"));
                    questionanswerDTO.setQuesTime(DateUtil.changeDateFormat(DateUtil.dateToString(dbmsgist.getCreatedAt(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm:ss", Constants.TIME_HH_MM));
                    questionanswerDTO.setServerDate(dbmsgist.getCreatedAt().getTime());
                      if(!CommonUtil.isNullOrEmpty(dbmsgist.getPhysicianId())) {
                     Object obj = patientProfileDAO.getPhysicianNameById(dbmsgist.getPhysicianId()); 
                     questionanswerDTO.setPhysicianName(obj != null ? obj.toString() : ""); 
                }
                    if(dbmsgist.getOrder().getPrescriberId()!=null) {
                    Practices practice = patientProfileDAO.getPracticesById(dbmsgist.getOrder().getPracticeId());
                    questionanswerDTO.setPrescriberName(practice != null ? practice.getPracticename(): "");
                   // questionanswerDTO.setPhysicianName(dbmsgist.getPhysicianName()!= null ? dbmsgist.getPhysicianName() : "");
                    questionanswerDTO.setPracticeLogo(practice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +practice.getPracticeLogo():"");
//                    questionlist.setPracticeLogo(preactice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +preactice.getPracticeLogo():"");
                    }
                    list.add(questionanswerDTO);
                }
            } else {
                List<MessageThreads> messageThreadsList = patientProfileDAO.getMessageThreadByQuestionId(new Long(questionAnswerDTO.getId()));
                for (MessageThreads dbmsgist : messageThreadsList) {
                    dbmsgist.setMarkAsRead(Boolean.TRUE);
                    dbmsgist.setUpdateAt(new Date());
                    this.update(dbmsgist);
                    QuestionAnswerDTO questionanswerDTO = new QuestionAnswerDTO();
                    questionanswerDTO.setId(dbmsgist.getId());
                    questionanswerDTO.setQuestionId(dbmsgist.getQuestionId());
                    questionAnswerDTO.setMessageStatus(dbmsgist.getMessgeStaus());
                    questionanswerDTO.setName(dbmsgist.getMessgeStaus());
//                    questionanswerDTO.setQuestionImge(dbmsgist.getAttachment());
                    questionanswerDTO.setQuestionImge(dbmsgist.getAttachment()!=null ? dbmsgist.getAttachment() : "");
                   // questionanswerDTO.setQuestionImge(dbmsgist.getAttachment()!=null ? PropertiesUtil.getProperty("PHP_WEB_PDF") +dbmsgist.getAttachment() : "");
                    questionanswerDTO.setAnswer(dbmsgist.getMessge());
                    questionanswerDTO.setQuestion(dbmsgist.getMessge());
                    questionanswerDTO.setIsRead(dbmsgist.getMarkAsRead());
                    TimeZone.getTimeZone(DateUtil.dateToString(dbmsgist.getCreatedAt(), ""));
                          if(!CommonUtil.isNullOrEmpty(dbmsgist.getPhysicianId())) {
                     Object obj = patientProfileDAO.getPhysicianNameById(dbmsgist.getPhysicianId()); 
                     questionanswerDTO.setPhysicianName(obj != null ? obj.toString() : ""); 
                }
//                    questionanswerDTO.setQuestionTimeStr(DateUtil.changeDateFormat(DateUtil.dateToString(dbmsgist.getCreatedAt(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm:ss", "MM/dd/yyyy"));
                    questionanswerDTO.setQuestionTimeStr(DateUtil.dateToString(dbmsgist.getCreatedAt(),Constants.USA_DATE_TIME_SECOND_FORMATE_12H));
                    questionanswerDTO.setQuesTime(DateUtil.changeDateFormat(DateUtil.dateToString(dbmsgist.getCreatedAt(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm:ss", Constants.TIME_HH_MM));
                    questionanswerDTO.setServerDate(dbmsgist.getCreatedAt().getTime());
                    QuestionAnswer dbQuestionAns = patientProfileDAO.getQuestionAnswerByQuestionId(new Long(questionAnswerDTO.getId()));
                   //  questionanswerDTO.setPhysicianName(dbmsgist.getPhysicianName()!= null ? dbmsgist.getPhysicianName() : "");
                    if (dbQuestionAns.getPrescriberId() != null) {
                        Practices practice = patientProfileDAO.getPracticesById(dbQuestionAns.getPrescriberId().intValue());
                        questionanswerDTO.setPrescriberName(practice != null ? practice.getPracticename() : "");
                        questionanswerDTO.setPracticeLogo(practice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +practice.getPracticeLogo():"");
                    }
                    list.add(questionanswerDTO);

                }
                
                
//                List<QuestionAnswer> Questionlist = patientProfileDAO.getQuestionAnswerById(profileId, questionAnswerDTO.getId());
//                for (QuestionAnswer listQuestion : Questionlist) {
//                        listQuestion.setIsRead(Boolean.TRUE);
//                        listQuestion.setUpdatedAt(new Date());
//                    this.update(listQuestion);
//                    QuestionAnswerDTO questionanswerDTO = new QuestionAnswerDTO();
//                    questionanswerDTO.setId(listQuestion.getId());
//                    questionanswerDTO.setQuestion(listQuestion.getQuestion());
//                    questionanswerDTO.setAnswer(listQuestion.getAnswer());
//                    questionanswerDTO.setQuestionImge(listQuestion.getQuestionImge());
//                    questionanswerDTO.setIsRead(listQuestion.getIsRead());
//                    questionanswerDTO.setQuestionTimeStr(DateUtil.changeDateFormat(DateUtil.dateToString(listQuestion.getQuestionTime(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm:ss", "MM/dd/yyyy"));
//                    list.add(questionanswerDTO);
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean PatientPointsEnrollment(PatientProfile profile) throws Exception {
        PatientPoints patientpoints = new PatientPoints();
        List<Order> orderlist = patientProfileDAO.getOrdersListByProfileId(profile.getPatientProfileSeqNo());
        for (Order order : orderlist) {
            if (patientProfileDAO.isNotEnrollforPoints(profile.getPatientProfileSeqNo(), order.getId())) {
                if (patientpoints.EnrollmentCriteria(order)) {
                    patientpoints.setPatientProfile(profile);
                    patientpoints.setOrders(order);
                    patientpoints.setEnrollmentPoints(1);
                    patientpoints.setCreatedOn(new Date());
                    patientProfileDAO.save(patientpoints);
                    return true;
                    ////// add code to store paintpointobject
                }
            }
        }
        return false;
    }

    public String PatientPointsUpdate(int patientId, String orderId, String PointToUpdate) throws Exception {
        PatientPoints patientpoint = patientProfileDAO.updatePatientPointsObject(patientId, orderId);
        if (PointToUpdate.equalsIgnoreCase("Refill")) {
            patientpoint.setRefillPoints(patientpoint.AddPoint(patientpoint.getRefillPoints()));
            patientpoint.setUpdateOn(new Date());
            patientProfileDAO.update(patientpoint);
            return "Refill points updated";
        } else if (PointToUpdate.equalsIgnoreCase("QuestionSubmittion")) {
            patientpoint.setQuestionSubmittionPoints(patientpoint.AddPoint(patientpoint.getQuestionSubmittionPoints()));
            patientpoint.setUpdateOn(new Date());
            patientProfileDAO.update(patientpoint);
            return "QuestionSubmittion points updated";
        }

        return "Encounter Error";
    }

    public Object ResetPassword(PatientProfile patientProfile, String password) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        patientProfile.setPassword(passwordEncoder.encode(password));
        patientProfile.setUpdatePasswordDate(new Date());
        if (patientProfileDAO.saveOrUpdate(patientProfile) != null) {
            return false;
        }
        saveActivitesHistory(ActivitiesEnum.CHANGE_PASSWORD.getValue(), patientProfile, "", "ResetPassword", "","");
        return true;
    }

    public Boolean getPatientHealthHistoryByDate(PatientProfile profile, PatientRecentHistoryDTO patientHealthHistory) throws Exception {
        List<BloodPressureDTO> bloodPressureListDTO = new ArrayList();
        List<PatientBodyMassResultDTO> bodyMassResultListDTO = new ArrayList();
        List<PatientGlucoseDTO> glucoseResultListDTO = new ArrayList();
        List<PatientHeartPulseDTO> heartPulseListDTO = new ArrayList();
        try {
            patientHealthHistory.setStaringDate(DateUtil.changeDateFormat(patientHealthHistory.getStaringDate(), "MM/dd/yyyy", "yyyy-MM-dd"));
            patientHealthHistory.setEndingDate(DateUtil.changeDateFormat(patientHealthHistory.getEndingDate(), "MM/dd/yyyy", "yyyy-MM-dd"));
            patientHealthHistory.setId(profile.getPatientProfileSeqNo());
            // patientHealthHistory.setPatientprofile(profile);
            List<BloodPressure> bloodPressureList = patientProfileDAO.getBloodPressureResultListByDate(profile.getPatientProfileSeqNo(), DateUtil.stringToDate(patientHealthHistory.getStaringDate(), "yyyy-MM-dd"), DateUtil.stringToDate(patientHealthHistory.getEndingDate(), "yyyy-MM-dd"));
            for (BloodPressure bloodPressure : bloodPressureList) {
                BloodPressureDTO bloodPressureDTO = new BloodPressureDTO();
                bloodPressureDTO.setBloodPresureSeqNo(bloodPressure.getBloodPresureSeqNo());
                bloodPressureDTO.setPulse(bloodPressure.getPulse());
                bloodPressureDTO.setDistolicBloodPressure(bloodPressure.getDistolicBloodPressure());
                bloodPressureDTO.setSystolicBloodPressure(bloodPressure.getSystolicBloodPressure());
                bloodPressureDTO.setReadingTime(bloodPressure.getReadingTime());
                bloodPressureListDTO.add(bloodPressureDTO);
            }
            patientHealthHistory.setBloodPressure(bloodPressureListDTO);

            List<PatientBodyMassResult> PatientBodyMassResultList = patientProfileDAO.getBodyMassResultListByDate(profile.getPatientProfileSeqNo(), DateUtil.stringToDate(patientHealthHistory.getStaringDate(), "yyyy-MM-dd"), DateUtil.stringToDate(patientHealthHistory.getEndingDate(), "yyyy-MM-dd"));
            for (PatientBodyMassResult patientBodyMassResult : PatientBodyMassResultList) {
                PatientBodyMassResultDTO patientBodyMassResultDTO = new PatientBodyMassResultDTO();
                patientBodyMassResultDTO.setBodyMassResultSeqNo(patientBodyMassResult.getBodyMassResultSeqNo());
                patientBodyMassResultDTO.setHeight(patientBodyMassResult.getHeight());
                patientBodyMassResultDTO.setWeight(patientBodyMassResult.getWeight());
                patientBodyMassResultDTO.setBMI(patientBodyMassResult.getBMI());
                patientBodyMassResultDTO.setPulse(patientBodyMassResult.getPulse());
                patientBodyMassResultDTO.setResultDate(patientBodyMassResult.getresultDate());
                bodyMassResultListDTO.add(patientBodyMassResultDTO);
            }
            patientHealthHistory.setPatientBodyMassResult(bodyMassResultListDTO);

            List<PatientGlucoseResults> patientGlucoseList = patientProfileDAO.getGlucoseResultHistoryByDate(profile.getPatientProfileSeqNo(), DateUtil.stringToDate(patientHealthHistory.getStaringDate(), "yyyy-MM-dd"), DateUtil.stringToDate(patientHealthHistory.getEndingDate(), "yyyy-MM-dd"));
            for (PatientGlucoseResults glucoseResultList : patientGlucoseList) {
                PatientGlucoseDTO patientGlucoseDTO = new PatientGlucoseDTO();
                patientGlucoseDTO.setId(glucoseResultList.getId());
                patientGlucoseDTO.setIsFasting(glucoseResultList.getIsFasting());
                patientGlucoseDTO.setGlucoseLevel(glucoseResultList.getGlucoseLevel());
                patientGlucoseDTO.setReadingTime(glucoseResultList.getReadingTime());
                glucoseResultListDTO.add(patientGlucoseDTO);
            }
            patientHealthHistory.setPatientGlucose(glucoseResultListDTO);

            List<PatientHeartPulseResult> patientHeartPulseList = patientProfileDAO.getHeartPulseListByDate(profile.getPatientProfileSeqNo(), DateUtil.stringToDate(patientHealthHistory.getStaringDate(), "yyyy-MM-dd"), DateUtil.stringToDate(patientHealthHistory.getEndingDate(), "yyyy-MM-dd"));
            for (PatientHeartPulseResult heartPulse : patientHeartPulseList) {
                PatientHeartPulseDTO heartPulseDTO = new PatientHeartPulseDTO();
                heartPulseDTO.setHeartPulseSeqNo(heartPulse.getHeartPulseSeqNo());
                heartPulseDTO.setHeartPulse(heartPulse.getHeartPulse());
                heartPulseDTO.setHeartPulseDate(heartPulse.getHeartPulseDate());
                heartPulseListDTO.add(heartPulseDTO);
            }
            patientHealthHistory.setPatientHeartPulse(heartPulseListDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    public Order getOrderByOrderId(String orderId) {
        Order orderInfo = new Order();
        try {
            orderInfo = patientProfileDAO.getOrderDetailById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getOrderById ", e);
        }
        return orderInfo;
    }

    public List<OrderDetailDTO> getMedicationHistory(int patientId, String RxNo, String DrugId) {
        List<OrderDetailDTO> orderHistory = new ArrayList();
        try {
            if (RxNo == null && DrugId == null) {
                List<Order> orderList = patientProfileDAO.getMedicationListByProfileId(patientId);
                for (Order ord : orderList) {
                    OrderDetailDTO orderDetail = new OrderDetailDTO();
                    DrugDetail2 dbdrugdetail = ord.getDrugDetail2();
                    orderDetail.setRxNumber(ord.getRxNumber());
                    orderDetail.setDrugName(dbdrugdetail.getRxLabelName());
                    orderDetail.setDrugType(dbdrugdetail.getDosageForm());
                    orderDetail.setOrderDate(DateUtil.changeDateFormat(DateUtil.dateToString(ord.getCreatedAt(), "yyyy-MM-dd"), "yyyy-MM-dd", "MM/dd/yyyy"));
                    orderDetail.setOrderStatusName(ord.getOrderStatus().getName());
                    orderDetail.setRefillsRemaining(String.valueOf(ord.getRefillsRemaining()));
                    orderDetail.setRefillAllowed(dbdrugdetail.getRefillsAllowed());
                    orderHistory.add(orderDetail);
                }
                return orderHistory;
            } else if (RxNo != null && DrugId != null) {
                List<Order> orderList = patientProfileDAO.getOrdersListBy_RxNo_DrugId(patientId, RxNo, Integer.parseInt(DrugId));
                for (Order ord : orderList) {
                    OrderDetailDTO orderDetail = new OrderDetailDTO();
                      DrugDetail2 dbdrugdetail = ord.getDrugDetail2();
                    orderDetail.setRxNumber(ord.getRxNumber());
                    orderDetail.setDrugName(dbdrugdetail.getRxLabelName());
                    orderDetail.setDrugType(dbdrugdetail.getDosageForm());
                    orderDetail.setOrderDate(DateUtil.changeDateFormat(DateUtil.dateToString(ord.getCreatedAt(), "yyyy-MM-dd"), "yyyy-MM-dd", "MM/dd/yyyy"));
                    orderDetail.setOrderStatusName(ord.getOrderStatus().getName());
                    orderDetail.setRefillsRemaining(String.valueOf(ord.getRefillsRemaining()));
                    orderDetail.setRefillAllowed(dbdrugdetail.getRefillsAllowed());
                    orderHistory.add(orderDetail);
                }
                return orderHistory;
            } else if (RxNo != null) {
                List<Order> orderList = patientProfileDAO.getOrdersListByRxNo(patientId, RxNo);
                for (Order ord : orderList) {
                    OrderDetailDTO orderDetail = new OrderDetailDTO();
                    DrugDetail2 dbdrugdetail = ord.getDrugDetail2();
                    orderDetail.setRxNumber(ord.getRxNumber());
                    orderDetail.setDrugName(dbdrugdetail.getRxLabelName());
                    orderDetail.setDrugType(dbdrugdetail.getDosageForm());
                    orderDetail.setOrderDate(DateUtil.changeDateFormat(DateUtil.dateToString(ord.getCreatedAt(), "yyyy-MM-dd"), "yyyy-MM-dd", "MM/dd/yyyy"));
                    orderDetail.setOrderStatusName(ord.getOrderStatus().getName());
                    orderDetail.setRefillsRemaining(String.valueOf(ord.getRefillsRemaining()));
                    orderDetail.setRefillAllowed(dbdrugdetail.getRefillsAllowed());
                    orderHistory.add(orderDetail);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    public Boolean sendPushNotification(QuestionAnswer dbQuestionAnswer, PatientProfile patientProfile, String message, String eventName) {
        try {
            CampaignMessages dbCampaignMessages = this.getNotificationMsgs(message, eventName);
            if (dbCampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            Practices dbPractise = patientProfileDAO.getPractiseNameById(patientProfile.getPracticeId());
            String messge = dbCampaignMessages.getSmstext();
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages);
            campaignMessages.setSmstext(messge.replace(PlaceholderEnum.QuestionID.getValue(), dbQuestionAnswer.getId() + "")
            );
            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, "", dbPractise));
            saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), dbQuestionAnswer, dbPractise);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# sendPushNotification# ", e);
            return false;
        }
        return true;
    }

    public List<ActivityHistoryDTO> getActivityHistoryByDate(int PatientId, String starting, String ending) {
        List<ActivityHistoryDTO> list = new ArrayList<>();
        try {
            List<ActivitesHistory> historyList = patientProfileDAO.getPatientActivityHistory(PatientId, DateUtil.stringToDate(starting, "yyyy-MM-dd"), DateUtil.stringToDateTime1(ending, "yyyy-MM-dd"));
            for (ActivitesHistory listHistory : historyList) {
                ActivityHistoryDTO ActivityHistoryDTO = new ActivityHistoryDTO();
                ActivityHistoryDTO.setActivityNumber(listHistory.getId());
                ActivityHistoryDTO.setActivityName(listHistory.getActivityName());
                ActivityHistoryDTO.setRxNoSTR(listHistory.getRxNumber());
                ActivityHistoryDTO.setDateTime(listHistory.getCreatedOn());
                ActivityHistoryDTO.setActivityDetails(listHistory.getActivityDetail());
                ActivityHistoryDTO.setReadingTime(listHistory.getReadingTime());
                ActivityHistoryDTO.setDateTimeSTR(DateUtil.changeDateFormat(DateUtil.dateToString(listHistory.getCreatedOn(), "yyyy-MM-dd hh:mm:ss a"), "yyyy-MM-dd hh:mm:ss a", "MM/dd/yyyy hh:mm:ss a"));
                list.add(ActivityHistoryDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getActivityHistory", e);
        }
        return list;
    }

    public boolean delRecord(PatientProfile profile) {
        try {

//            EnrollementIpad enrollment = patientProfileDAO.getEnrollemtRecord(profile.getPatientProfileSeqNo());
//            if (enrollment != null) {
//                enrollment.setDeletedAt(new Date());
//                enrollment.setUpdatedAt(new Date());
//                patientProfileDAO.update(enrollment);
//            }
            CommonUtil.delPatientProfileDataByMobileNumberOrEmail(profile, patientProfileDAO);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Practices getPracticebyIdentity(String Identity) throws Exception {
        Practices practices = new Practices();
        try {

            practices = patientProfileDAO.getPracticesByIdentity(Identity);
            return practices;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> getPracticebyPhoneNo", e);
        }
        return null;
    }
        public Practices getPracticebyId(int id) throws Exception {
        Practices practices = new Practices();
        try {

            practices = patientProfileDAO.getPracticesById(id);
            return practices;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> getPracticebyPhoneNo", e);
        }
        return null;
    }

    public Practices getPracticebyPhone(String Phone) throws Exception {
        Practices practices = new Practices();
        try {

            practices = patientProfileDAO.getPracticesByPhone(Phone);
            return practices;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> getPracticebyPhoneNo", e);
        }
        return null;
    }

    public Practices getPracticebyLicense(String License) throws Exception {
        Practices practices = new Practices();
        try {

            practices = patientProfileDAO.getPracticesByLicense(License);
            return practices;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> getPracticebyPhoneNo", e);
        }
        return null;
    }

    public boolean deleteUsersbyPracticeId(Practices practice) {
        try {
            CommonUtil.delUsersbyPracticeId(practice, patientProfileDAO);
        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean deletePatientProfilebyPracticeId(Practices practice) {
        try {
            List<PatientProfile> patientProfileList = patientProfileDAO.getPatientProfileByPracticeId(practice.getId());
            for (PatientProfile profile : patientProfileList) {
                this.delRecord(profile);
            }
//            patientProfileDAO.deleteProfile(practice);
            patientProfileDAO.delete("practices", "id", practice.getId());
        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public PatientProfile getPatientProfileByPhone(String mobileNumber) {
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileDAO.getPatientProfileByPhone(EncryptionHandlerUtil.getEncryptedString(mobileNumber));
        } catch (Exception e) {
            logger.error("Exception -> getPatientProfileByPhone", e);
            e.printStackTrace();
        }
        return patientProfile;
    }

    public List<SurveyAnswerDTO> getSurveyAnswerList(int questionId) {
        List<SurveyAnswerDTO> list = new ArrayList<>();
        try {
            List<SurveyAnswer> answerList = patientProfileDAO.getSurveyAnswerListByQuestionId(questionId);
            for (SurveyAnswer survyList : answerList) {
                SurveyAnswerDTO surveyAnswerDTO = new SurveyAnswerDTO();
                surveyAnswerDTO.setAnswerId(survyList.getSurveyId());
                surveyAnswerDTO.setAnswerLabel(survyList.getAnswerLabel());
                list.add(surveyAnswerDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> getSurveyAnswerList", e);
        }
        return list;
    }

    public SurveyDto getSurvey(int surveyId) {

        List<surveyQuestionAnswerDTO> survyQuestionList = new ArrayList<>();
        SurveyDto surveyDTO = new SurveyDto();

        try {
            Survey dbsurvey = patientProfileDAO.getSurveyById(surveyId);
            List<SurveyQuestion> surveyQuestion = patientProfileDAO.getSurveyQuestionBySurveyId(surveyId);
            for (SurveyQuestion questin : surveyQuestion) {
                surveyQuestionAnswerDTO surveyquestionAnswerDTO = new surveyQuestionAnswerDTO();
                surveyquestionAnswerDTO.setQuestionId(questin.getId());
                surveyquestionAnswerDTO.setQuestionTitle(questin.getTitle());
                List<SurveyAnswer> answerList = patientProfileDAO.getSurveyAnswerListByQuestionId(questin.getId());
                ArrayList<SurveyAnswerDTO> surveyAnserList = new ArrayList<>();
                for (SurveyAnswer survyList : answerList) {
                    SurveyAnswerDTO surveyAnswerDTO = new SurveyAnswerDTO();
                    surveyAnswerDTO.setAnswerId(survyList.getSurveyId());
                    surveyAnswerDTO.setAnswerLabel(survyList.getAnswerLabel());
                    surveyAnserList.add(surveyAnswerDTO);

                }
                surveyquestionAnswerDTO.setAnswer(surveyAnserList);
                survyQuestionList.add(surveyquestionAnswerDTO);

            }
            surveyDTO.setSurveyId(dbsurvey.getId());
            surveyDTO.setSurveyLabel(dbsurvey.getTitle());
            surveyDTO.setSurveyDetail(dbsurvey.getSurveyDetail());
            surveyDTO.setSurveyQuestion(survyQuestionList);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception # PatientProfileService->getSurvey ", e);
        }
        return surveyDTO;
    }

    public PatientProfileDTO getPatientProfileDTOByEmailOrPhone(String identify) throws Exception {
        PatientProfileDTO patient = new PatientProfileDTO();
        try {
            PatientProfile profile = patientProfileDAO.getPatientProfileByEmailIdOrPhoneNumber(identify);
            patient.setFirstName(EncryptionHandlerUtil.getDecryptedString(profile.getFirstName()));
            patient.setLastName(EncryptionHandlerUtil.getDecryptedString(profile.getLastName()));
            patient.setGender(EncryptionHandlerUtil.getDecryptedString(profile.getGender()));
            patient.setAddress(profile.getCity());
            String mobileNumber = EncryptionHandlerUtil.getDecryptedString(profile.getMobileNumber());
            mobileNumber = "(" + mobileNumber.substring(0, 3) + ") " + mobileNumber.substring(3, 6) + "-" + mobileNumber.substring(6, 10);
            patient.setMobileNumber(mobileNumber);
            patient.setEmailAddress(EncryptionHandlerUtil.getDecryptedString(profile.getEmailAddress()));
            patient.setZip(profile.getZipcode());
            patient.setPatientDOB(DateUtil.changeDateFormat(EncryptionHandlerUtil.getDecryptedString(profile.getBirthDate()), "yyyy-MM-dd", "MM-dd-yyyy"));
            return patient;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# PatientProfileService -> getPatientProfileByEmailOrPhone ", e);
        }
        return null;
    }

//    public RewardsPointDTO rewardsPointUpdate(RewardsPointDTO rewardsPointDTO, PatientProfile profile, Order order) {
//        
//        /* ==========================in case rxnumber is provide to by as input============================  */
//        if (!CommonUtil.isNullOrEmpty(rewardsPointDTO.getRxNumber())) {
//            ///================ each case of swith handles activity according to its Name===========///
//            switch (rewardsPointDTO.getActivityDetail()) {
//                case "PatientBodyMass": {
//                    try {
//                        ///===================Creating and Saving PatientBodyMassResult==============///
//                        PatientBodyMassResult patientBodyMassResult = new PatientBodyMassResult();
//                        patientBodyMassResult.setPatientProfile(profile);
//                        patientBodyMassResult.setHeight(rewardsPointDTO.getHeight());
//                        patientBodyMassResult.setWeight(rewardsPointDTO.getWeight());
//                        patientBodyMassResult.setPulse(rewardsPointDTO.getPulse());
//                        patientBodyMassResult.setresultDate(rewardsPointDTO.getResultDate());
//                        patientBodyMassResult.setCreatedOn(new Date());
//                        patientProfileDAO.save(patientBodyMassResult);
//                        ///================Saving Acitvity History=================///
//                        saveActivitesHistory(ActivitiesEnum.ADD_BODY_MASS_ACTIVITY.getValue(), profile, rewardsPointDTO.getRxNumber(), patientBodyMassResult.getHeight() + "ft " + patientBodyMassResult.getWeight() + "LBS " + patientBodyMassResult.getPulse() + "PRV");
//                        ///===============Main Business logic of Reward Point resides in below Function===========///
//                        updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 1);
//                        return rewardsPointDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientBodyMass ",e);
//                    }
//                }
//                break;
//                case "PatientHeartPulseResult": {
//                    try {
//                        PatientHeartPulseResult patientHeartPulseResult = new PatientHeartPulseResult();
//                        patientHeartPulseResult.setPatientProfile(profile);
//                        patientHeartPulseResult.setHeartPulse(rewardsPointDTO.getPulse());
//                        patientHeartPulseResult.setHeartPulseDate(rewardsPointDTO.getResultDate());
//                        patientHeartPulseResult.setCreatedOn(new Date());
//                        patientProfileDAO.save(patientHeartPulseResult);
//                        saveActivitesHistory(ActivitiesEnum.ADD_HEART_PULS_ACTIVITY.getValue(), profile, rewardsPointDTO.getRxNumber(), patientHeartPulseResult.getHeartPulse() + "BPM");
//                        updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 2);
//                        return rewardsPointDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientHeartPulseResult ",e);
//                    }
//                }
//                break;
//                case "PatientGlucoseResults": {
//                    try {
//                        PatientGlucoseResults patientGlucoseResults = new PatientGlucoseResults();
//                        patientGlucoseResults.setPatientProfile(profile);
//                        patientGlucoseResults.setGlucoseLevel(rewardsPointDTO.getPulse());
//                        patientGlucoseResults.setIsFasting(rewardsPointDTO.getIsFasting());
//                        patientGlucoseResults.setReadingTime(rewardsPointDTO.getResultDate());
//                        patientGlucoseResults.setCreatedOn(new Date());
//                        patientProfileDAO.save(patientGlucoseResults);
//                        saveActivitesHistory(ActivitiesEnum.ADD_GLUCOSE_ACTIVITY.getValue(), profile, rewardsPointDTO.getRxNumber(), patientGlucoseResults.getGlucoseLevel() + "mg/dl fasting:" + patientGlucoseResults.getIsFasting());
//                        updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 3);
//                        return rewardsPointDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientGlucoseResults ",e);
//                    }
//                }
//                break;
//                case "PatientBloodPresureResult": {
//                    try {
//                        BloodPressure bloodPressure = new BloodPressure();
//                        bloodPressure.setPatientProfile(profile);
//                        bloodPressure.setDistolicBloodPressure(rewardsPointDTO.getDistolicBloodPressure());
//                        bloodPressure.setSystolicBloodPressure(rewardsPointDTO.getSystolicBloodPressure());
//                        bloodPressure.setPulse(AppUtil.getSafeInt(rewardsPointDTO.getPulse(), 0));
//                        bloodPressure.setReadingTime(rewardsPointDTO.getResultDate());
//                        bloodPressure.setCreatedOn(new Date());
//                        patientProfileDAO.save(bloodPressure);
//                        saveActivitesHistory(ActivitiesEnum.ADD_BLOOD_PRESSURE_ACTIVITY.getValue(), profile,rewardsPointDTO.getRxNumber(),bloodPressure.getDistolicBloodPressure() + " DIA " + bloodPressure.getSystolicBloodPressure() + " SYS " + bloodPressure.getPulse() + " PRV");
//                        updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 4);
//                        return rewardsPointDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientBloodPresuureResult ",e);
//
//                    }
//                }
//                break;
//                case "Refill on Time": {
//                    try {
//                        Date refillDueDate = DateUtil.addDaysToDate(DateUtil.formatDate(order.getRxOrigDate(), Constants.DD_MM_YYYY), order.getDaysSupply());
//                        Date currentDate = DateUtil.formatDate(new Date(), Constants.DD_MM_YYYY);
//                        if (currentDate.equals(refillDueDate)) {
//                            updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 5);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientHeartPulseResult ", e);
//                    }
//                }
//                break;
//                case "Prescription on time": {
//                    try {
//                        //requirment Pending.
//
//                        updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 6);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientHeartPulseResult ",e);
//
//                    }
//                }
//                break;
//                       case "ORDER CREATION": {
//                    try {
//             
//
//                        updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 6);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientHeartPulseResult ",e);
//
//                    }
//                }
//                break;
//                case "Survey On time": {
//                    try {
//                        //under Process.
//                            updateComplianceRewardPointAndRewardHistoryWithRxNumber(rewardsPointDTO, profile, 7);
//                    } catch (Exception e) {
//                        logger.error("getRewardpoint ->PatientHeartPulseResult ",e);
//                    }
//                }
//                default:
//                    logger.error("Invalid activity name;");
//            }
//            /* ==================================in case rxnumber is not provide as input============================================*/
//        } else {
//            switch (rewardsPointDTO.getActivityDetail()) {
//                case "PatientBodyMass": {
//                    try {
//                        PatientBodyMassResult patientBodyMassResult = new PatientBodyMassResult();
//                        patientBodyMassResult.setPatientProfile(profile);
//                        patientBodyMassResult.setHeight(rewardsPointDTO.getHeight());
//                        patientBodyMassResult.setWeight(rewardsPointDTO.getWeight());
//                        patientBodyMassResult.setPulse(rewardsPointDTO.getPulse());
//                        patientBodyMassResult.setresultDate(rewardsPointDTO.getResultDate());
//                        patientBodyMassResult.setCreatedOn(new Date());
//                        patientProfileDAO.save(patientBodyMassResult);
//                        
//                          updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(rewardsPointDTO, profile, 1,"PatientBodyMass",patientBodyMassResult.getHeight() + "ft " + patientBodyMassResult.getWeight() + "LBS " + patientBodyMassResult.getPulse() + "PRV");
//                          return rewardsPointDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientBodyMass(where rxNumber not given) ",e);
//                    }
//                }
//                break;
//                case "PatientHeartPulseResult": {
//                    try {
//                        PatientHeartPulseResult patientHeartPulseResult = new PatientHeartPulseResult();
//                        patientHeartPulseResult.setPatientProfile(profile);
//                        patientHeartPulseResult.setHeartPulse(rewardsPointDTO.getPulse());
//                        patientHeartPulseResult.setHeartPulseDate(rewardsPointDTO.getResultDate());
//                        patientHeartPulseResult.setCreatedOn(new Date());
//                        patientProfileDAO.save(patientHeartPulseResult);
//
//                          updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(rewardsPointDTO, profile, 2,"PatientHeartPulseResult", patientHeartPulseResult.getHeartPulse() + "BPM");
//                          return rewardsPointDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientHeartPulseResult(where rxNumber not given) ",e);
//
//                    }
//                }
//                break;
//                case "PatientGlucoseResults": {
//                    try {
//                        PatientGlucoseResults patientGlucoseResults = new PatientGlucoseResults();
//                        patientGlucoseResults.setPatientProfile(profile);
//                        patientGlucoseResults.setGlucoseLevel(rewardsPointDTO.getGlucoseLevel());
//                        patientGlucoseResults.setIsFasting(rewardsPointDTO.getIsFasting());
//                        patientGlucoseResults.setReadingTime(rewardsPointDTO.getResultDate());
//                        patientGlucoseResults.setCreatedOn(new Date());
//                        patientProfileDAO.save(patientGlucoseResults);
//
//                          updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(rewardsPointDTO, profile, 3,"PatientGlucoseResults",patientGlucoseResults.getGlucoseLevel() + "mg/dl fasting:" + patientGlucoseResults.getIsFasting());
//                          return rewardsPointDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientGlucoseResults(where rxNumber not given) ",e);
//
//                    }
//                }
//                break;
//                case "PatientBloodPresureResult": {
//                    try {
//                        BloodPressure bloodPressure = new BloodPressure();
//                        bloodPressure.setPatientProfile(profile);
//                        bloodPressure.setDistolicBloodPressure(rewardsPointDTO.getDistolicBloodPressure());
//                        bloodPressure.setSystolicBloodPressure(rewardsPointDTO.getSystolicBloodPressure());
//                        bloodPressure.setPulse(AppUtil.getSafeInt(rewardsPointDTO.getPulse(), 0));
//                        bloodPressure.setReadingTime(rewardsPointDTO.getResultDate());
//                        bloodPressure.setCreatedOn(new Date());
//                        patientProfileDAO.save(bloodPressure);
//                        updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(rewardsPointDTO, profile, 4,"PatientBloodPresureResult",bloodPressure.getDistolicBloodPressure() + " DIA " + bloodPressure.getSystolicBloodPressure() + " SYS " + bloodPressure.getPulse() + " PRV");
//                    return rewardsPointDTO;} catch (Exception e) {
//                        e.printStackTrace();
//                        logger.error("getRewardpoint ->PatientBloodPresuureResult(where rxNumber not given) ",e);
//
//                    }
//                }
//                default:
//                    logger.error("Invalid activity name;");
//                    System.out.println("Invalid activity name;");
//            }
//        }
//        return rewardsPointDTO;
//    }
    public ComplianceRewardPointDTO updateComplianceRewardPointAndRewardHistoryWithRxNumber(String rxNumber, PatientProfile profile, int activityId, String activityDetail) {
        ComplianceRewardPoint complianceRewardPoint;
        try {
            /* fetching order list by patientId and rxnumber where order status is processing, activity count less then 4 order by order date desc. */
            Order order = patientProfileDAO.getOrdersByRxNumberAndStatus(rxNumber, profile.getPatientProfileSeqNo());
            if (order != null) {
                ///=============Searching for data in complianceRewardPoint table by PatientId, OrderId and RxNumber================///  
                complianceRewardPoint = order.getComplianceRewardPoint(); //patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo(), ord.getId());
                if (complianceRewardPoint != null) {
                    /////////=========Calculating and Updating Reward Points==============/// 
                    DecimalFormat df = new DecimalFormat("##.00");
                    double dboutofPoket = complianceRewardPoint.getRxPatientOutOfPocket();
                    double dbRemianBalance = complianceRewardPoint.getCurrentRemainBalance();
                    double dbEarnedPoint = complianceRewardPoint.getCurrentEarnReward();
                    double value = dboutofPoket / 4;
                    dbEarnedPoint = dbEarnedPoint + value;
                    dbRemianBalance = dbRemianBalance - value;
                    complianceRewardPoint.setCurrentEarnReward(Double.parseDouble(df.format(dbEarnedPoint)));
                    complianceRewardPoint.setCurrentRemainBalance(Double.parseDouble(df.format(dbRemianBalance)));
                    complianceRewardPoint.setActivityCount(complianceRewardPoint.getActivityCount() + 1);
                    complianceRewardPoint.setUpdateOn(new Date());
                    patientProfileDAO.update(complianceRewardPoint);
                    ////==================================================================/////
                    ///==============logging Reward History ============///
                    RewardHistory dbrewardHistory = new RewardHistory();
                    dbrewardHistory.setOrder(order);
                    dbrewardHistory.setRxNumber(order.getRxNumber());
                    /////rewardActivity object////////
                    RewardActivity rewardActivity = new RewardActivity();
                    rewardActivity.setActivityId(activityId);
                    //////////////////////////////////
                    dbrewardHistory.setActivityId(rewardActivity);
                    dbrewardHistory.setActivityNumber(complianceRewardPoint.getActivityCount() + 1);
                    dbrewardHistory.setPatientId(profile.getPatientProfileSeqNo());
                    dbrewardHistory.setRewardPointId(complianceRewardPoint.getRewardId());
                    dbrewardHistory.setEarnedReward(complianceRewardPoint.getCurrentEarnReward());
                    dbrewardHistory.setActivityDetail(activityDetail);
                    dbrewardHistory.setCreatedDate(new Date());
                    patientProfileDAO.save(dbrewardHistory);
                    /////=====================================================///
                    ///==================RewardPoint Object for Mobile Team===========///
                    ComplianceRewardPointDTO complianceRewardPointDTO = new ComplianceRewardPointDTO();
                    complianceRewardPointDTO.setRxNo(order.getRxNumber());
                    complianceRewardPointDTO.setRxPatientOutOfPocket("$" + complianceRewardPoint.getRxPatientOutOfPocket());
                    complianceRewardPointDTO.setCurrentEarnReward("$" + complianceRewardPoint.getCurrentEarnReward());
                    complianceRewardPointDTO.setCurrentRemainBalance("$" + complianceRewardPoint.getCurrentRemainBalance());
                    complianceRewardPointDTO.setActivityCount(complianceRewardPoint.getActivityCount());
                    complianceRewardPointDTO.setDrugName(order.getDrugDetail2().getRxLabelName());
                    complianceRewardPointDTO.setStrength(order.getStrength());
                    complianceRewardPointDTO.setDrugType(order.getDrugType());
                    complianceRewardPointDTO.setGenericOrBrand(order.getDrugDetail2().getGenericOrBrand());
                    return complianceRewardPointDTO;
                    //////=========================================================///

                }
                logger.error("order have null value in Complinace Reward filed." + order.getComplianceRewardPoint());
                //No recod found in compliance reward table. may be not save by php or java side creation order time. we can find out in java side in //saveRefillNodule or Enrollement time or discuss with ipad team.
                return null;
            }

            Order order2 = patientProfileDAO.getOrdersByRxNumberAndStatusWithOutActivityCount(rxNumber, profile.getPatientProfileSeqNo());
            if (order2 != null) {
                complianceRewardPoint = order2.getComplianceRewardPoint();//patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo(), ord.getId());

                if (complianceRewardPoint != null) {
                    complianceRewardPoint.setActivityCount(complianceRewardPoint.getActivityCount() + 1);
                    if (complianceRewardPoint.getActivityCount() < 5 && complianceRewardPoint.getWegaWalletPoint() < 6) {
                        complianceRewardPoint.setWegaWalletPoint(complianceRewardPoint.getWegaWalletPoint() + 1);
                    }
                    complianceRewardPoint.setUpdateOn(new Date());
                    patientProfileDAO.update(complianceRewardPoint);

                    RewardHistory dbrewardHistory = new RewardHistory();
                    dbrewardHistory.setOrder(order2);
                    dbrewardHistory.setRxNumber(order2.getRxNumber());
                    //////////////rewardActivityObject//////////////
                    RewardActivity rewardActivity = new RewardActivity();
                    rewardActivity.setActivityId(activityId);
                    /////////////////////////////////////////////////    
                    dbrewardHistory.setActivityId(rewardActivity);
                    dbrewardHistory.setActivityNumber(complianceRewardPoint.getActivityCount());
                    dbrewardHistory.setPatientId(profile.getPatientProfileSeqNo());
                    dbrewardHistory.setRewardPointId(complianceRewardPoint.getRewardId());
                    dbrewardHistory.setEarnedReward(complianceRewardPoint.getCurrentEarnReward());
                    dbrewardHistory.setActivityDetail(activityDetail);
                    dbrewardHistory.setCreatedDate(new Date());
                    patientProfileDAO.save(dbrewardHistory);
                    ComplianceRewardPointDTO complianceRewardPointDTO = new ComplianceRewardPointDTO();
//                    complianceRewardPointDTO.setRxNo(order2.getRxNumber());
                    complianceRewardPointDTO.setRxPatientOutOfPocket("$" + complianceRewardPoint.getRxPatientOutOfPocket());
                    complianceRewardPointDTO.setCurrentEarnReward("$" + complianceRewardPoint.getCurrentEarnReward());
                    complianceRewardPointDTO.setCurrentRemainBalance("$" + complianceRewardPoint.getCurrentRemainBalance());
                     complianceRewardPointDTO.setEarnedWegaWalletBounusPoint(complianceRewardPoint.getWegaWalletPoint()); 
                    complianceRewardPointDTO.setTotalwwPointYouCanEarn(5);
                    complianceRewardPointDTO.setRemianingwwPoint(5 - complianceRewardPoint.getWegaWalletPoint());
                    complianceRewardPointDTO.setActivityCount(complianceRewardPoint.getActivityCount());
                    complianceRewardPointDTO.setDrugName(order2.getDrugDetail2().getRxLabelName());
                    complianceRewardPointDTO.setStrength(order2.getStrength());
                    complianceRewardPointDTO.setDrugType(order2.getDrugType());
                    complianceRewardPointDTO.setGenericOrBrand(order2.getDrugDetail2().getGenericOrBrand());
                    return complianceRewardPointDTO;
                }
            } else {
                       complianceRewardPoint = patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo());
                       if(complianceRewardPoint != null) {
                              complianceRewardPoint.setActivityCount(complianceRewardPoint.getActivityCount() + 1);
                    if (complianceRewardPoint.getActivityCount() < 5  && complianceRewardPoint.getWegaWalletPoint() < 6) {
                        complianceRewardPoint.setWegaWalletPoint(complianceRewardPoint.getWegaWalletPoint() + 1);
                    }
                    complianceRewardPoint.setUpdateOn(new Date());
                    patientProfileDAO.update(complianceRewardPoint);

                    RewardHistory dbrewardHistory = new RewardHistory();
                    //////////////rewardActivityObject//////////////
                    RewardActivity rewardActivity = new RewardActivity();
                    rewardActivity.setActivityId(activityId);
                    /////////////////////////////////////////////////    
                    dbrewardHistory.setActivityId(rewardActivity);
                    dbrewardHistory.setActivityNumber(complianceRewardPoint.getActivityCount());
                    dbrewardHistory.setPatientId(profile.getPatientProfileSeqNo());
                    dbrewardHistory.setRewardPointId(complianceRewardPoint.getRewardId());
                    dbrewardHistory.setEarnedReward(complianceRewardPoint.getCurrentEarnReward());
                    dbrewardHistory.setActivityDetail(activityDetail);
                    dbrewardHistory.setCreatedDate(new Date());
                    patientProfileDAO.save(dbrewardHistory);
                    ComplianceRewardPointDTO complianceRewardPointDTO = new ComplianceRewardPointDTO();
//                    complianceRewardPointDTO.setRxPatientOutOfPocket("$" + complianceRewardPoint.getRxPatientOutOfPocket());
//                    complianceRewardPointDTO.setCurrentEarnReward("$" + complianceRewardPoint.getCurrentEarnReward());
//                    complianceRewardPointDTO.setCurrentRemainBalance("$" + complianceRewardPoint.getCurrentRemainBalance());
                    complianceRewardPointDTO.setEarnedWegaWalletBounusPoint(complianceRewardPoint.getWegaWalletPoint()); 
                    complianceRewardPointDTO.setTotalwwPointYouCanEarn(5);
                     complianceRewardPointDTO.setRemianingwwPoint(5 - complianceRewardPoint.getWegaWalletPoint());
//                    complianceRewardPointDTO.setActivityCount(complianceRewardPoint.getActivityCount());
                    return complianceRewardPointDTO;
                       }                              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ComplianceRewardPointDTO updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(PatientProfile profile, int activityId, String activityName, String activityDetails, String readingTime) {
        ComplianceRewardPoint complianceRewardPoint;
         VegaWalletRewardPoint vegaWalletRewardPoint = new VegaWalletRewardPoint();
        try {
            logger.info("fetching order list by patientId where order status is processing, activity count less then 4");
            Order order = patientProfileDAO.getOrdersByStatus(profile.getPatientProfileSeqNo());
            String practiceCode = "";
            Practices dbpractice = patientProfileDAO.getPracticesById(profile.getPracticeId());
            if (dbpractice != null) {
                practiceCode = dbpractice.getPracticeCode();
            }
            if (order != null) {
                logger.info("Activity History Logging...");
                logsActivitiesHistory(profile, activityId, activityName, activityDetails, readingTime, dbpractice.getPracticeCode(), order);
                complianceRewardPoint = order.getComplianceRewardPoint();
                if (complianceRewardPoint != null) {
                    logger.info("Calculating and updating reward Point on First Four Activities");
                    updateComplianceRewardPoint(complianceRewardPoint);
                    logger.info("Saving Reward History...");
                    saveRewardHistory(null,complianceRewardPoint, profile, order, dbpractice.getPracticeCode(), activityId, activityDetails, readingTime);
                    logger.info("Fetching RewardDto..");
                    return getRewardsPointDetialForMobileApp(null,complianceRewardPoint, profile, order, dbpractice.getPracticeCode());
                }
                return null;
            }
            logger.info("fetching order list by patientId where order status is processing, without activity count  in case no reward available agaisnt activity.bcz already perform his 4 activity");
            Order ord2 = patientProfileDAO.getOrdersByStatusWithOutActivityCount(profile.getPatientProfileSeqNo());
            if (ord2 != null) {
                logsActivitiesHistory(profile, activityId, activityName, activityDetails, readingTime, dbpractice.getPracticeCode(), order);
                complianceRewardPoint = ord2.getComplianceRewardPoint();
                logger.info("order have this value in Complinace Reward filed." + ord2.getComplianceRewardPoint());
                if (complianceRewardPoint != null) {
                    logger.info("adding wegaWallet point after complete first 4 acitivites..");
                    vegaWalletRewardPoint  = saveOrUpdateWegaWalletPoint(vegaWalletRewardPoint,profile);
                    saveRewardHistory(vegaWalletRewardPoint,complianceRewardPoint, profile, ord2, dbpractice.getPracticeCode(), activityId, activityDetails, readingTime);
                    logger.info("Saving Reward History...");
                    return getRewardsPointDetialForMobileApp(vegaWalletRewardPoint,complianceRewardPoint, profile, ord2, dbpractice.getPracticeCode());
                }
                logger.error("order have null value in Complinace Reward filed." + ord2.getComplianceRewardPoint());
            } else {
//                   VegaWalletRewardPoint vegaWalletRewardPoint = patientProfileDAO.getVegaWalletRewardPoint(profile.getPatientProfileSeqNo());
                    logger.info("adding wegaWallet if patinet have no order and pperforming any activity");
                     vegaWalletRewardPoint  = saveOrUpdateWegaWalletPoint(vegaWalletRewardPoint,profile);
                    logger.info("Saving Reward History...");
                    saveRewardHistory(vegaWalletRewardPoint,null, profile, ord2, dbpractice.getPracticeCode(), activityId, activityDetails, readingTime);
                    return getRewardsPointDetialForMobileApp(vegaWalletRewardPoint,null, profile, ord2, dbpractice.getPracticeCode());
//                complianceRewardPoint = patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo());
//                if (complianceRewardPoint != null) {
//                    logger.info("adding wegaWallet if patinet have no order and pperforming any activity");
//                   complianceRewardPoint = saveOrUpdateWegaWalletPoint(complianceRewardPoint, profile);
//                } else {
//                   complianceRewardPoint = saveOrUpdateWegaWalletPoint(complianceRewardPoint, profile);
//                }
//                    logger.info("Saving Reward History...");
//                    saveRewardHistory(complianceRewardPoint, profile, ord2, dbpractice.getPracticeCode(), activityId, activityDetails, readingTime);
//                    return getRewardsPointDetialForMobileApp(complianceRewardPoint, profile, ord2, dbpractice.getPracticeCode());
            }         
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService->updateComplianceRewardwithoutRxnumber", e);
        }
        return null;
    }

    public List<RewardHistoryDTO> getRewardHistoryListByPatientId(int profileId, String StartDate, String EndDate) {
        List<RewardHistoryDTO> rewardHistoryDTOList = new ArrayList<>();
        try {
            List<RewardHistory> rewardHistoryList = patientProfileDAO.getRewardHistoryListbyPatientId(profileId, DateUtil.stringToDate(StartDate, "yyyy-MM-dd"), DateUtil.stringToDateTime1(EndDate, "yyyy-MM-dd"));
            for (RewardHistory rewardHistory : rewardHistoryList) {
                RewardHistoryDTO rewardHistoryDTO = new RewardHistoryDTO();
                rewardHistoryDTO.setRxNumber(rewardHistory.getRxNumber());
                rewardHistoryDTO.setActivityTitle(rewardHistory.getActivityId().getActivityName());
                rewardHistoryDTO.setActivityName(rewardHistory.getActivityId().getActivity());
                rewardHistoryDTO.setCreatedDate(DateUtil.changeDateFormat(DateUtil.dateToString(rewardHistory.getCreatedDate(), "yyyy-MM-dd"), "yyyy-MM-dd", "MM/dd/yyyy"));
                rewardHistoryDTO.setActivityNumber(rewardHistory.getActivityNumber());
                rewardHistoryDTO.setEarnedReward(rewardHistory.getEarnedReward());
                rewardHistoryDTO.setActivityDetail(rewardHistory.getActivityDetail());
                rewardHistoryDTO.setReadingTime(rewardHistory.getReadingTime());
                rewardHistoryDTO.setVegaWalletActivityCount(rewardHistory.getVegaWalletActivityCount());
                rewardHistoryDTO.setVeagaWalletPoint(rewardHistory.getVeagaWalletPoint());
                rewardHistoryDTOList.add(rewardHistoryDTO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rewardHistoryDTOList;
    }

    public ComplianceRewardPointDTO addRewardsPointSAndHistory(Order ord, PatientProfile profile, int activity) {
        ComplianceRewardPoint complianceRewardPoint = new ComplianceRewardPoint();
        try {
                String practiceCode = "";
                Practices dbpractice = patientProfileDAO.getPracticesById(profile.getPracticeId());
                if(dbpractice!=null){
                practiceCode = dbpractice.getPracticeCode();
                }
            logger.info("addRewardsPointSAndHistory " + ord.getId());
            RewardActivity rewardActivity = patientProfileDAO.getRewardActivityById(activity);

            complianceRewardPoint.setProfile(profile);
            complianceRewardPoint.setRxNo(practiceCode+"-"+ord.getRxNumber());
            if (ord != null) {
//            Order ordId = new Order(ord.getId());
                complianceRewardPoint.setOrders(ord);
            }
            if (rewardActivity != null) {
                complianceRewardPoint.setRewardactivities(rewardActivity);
            }
            complianceRewardPoint.setRxPatientOutOfPocket(ord.getRxPatientOutOfPocket());//assistant Auth
            DecimalFormat df = new DecimalFormat("##.00");
            double value = ord.getRxPatientOutOfPocket() / 4;
            double earnPoint = 0 + value;
            complianceRewardPoint.setCurrentEarnReward(Double.parseDouble(df.format(earnPoint)));
            complianceRewardPoint.setCurrentRemainBalance(ord.getRxPatientOutOfPocket() - value);
            complianceRewardPoint.setActivityCount(1);
            complianceRewardPoint.setCreatedOn(new Date());
            complianceRewardPoint.setUpdateOn(new Date());
            patientProfileDAO.save(complianceRewardPoint);
            ord.setComplianceRewardPoint(complianceRewardPoint); //Optional just add for now remian discussion with php
            patientProfileDAO.update(ord);

            RewardHistory dbrewardHistory = new RewardHistory();
            dbrewardHistory.setOrder(ord);
            dbrewardHistory.setRxNumber(practiceCode+"-"+ord.getRxNumber());
            dbrewardHistory.setEarnedReward(complianceRewardPoint.getCurrentEarnReward());
            dbrewardHistory.setActivityDetail(ord.getDrugDetail2().getRxLabelName());
            if (rewardActivity != null) {
                dbrewardHistory.setActivityId(rewardActivity);
            }
            dbrewardHistory.setActivityNumber(1);
            dbrewardHistory.setPatientId(profile.getPatientProfileSeqNo());
//            complianceRewardPoint = patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo(), ord.getOrderNo(), ord.getRxNumber());
            if (complianceRewardPoint != null) {
                dbrewardHistory.setRewardPointId(complianceRewardPoint.getRewardId());
            }
            dbrewardHistory.setCreatedDate(new Date());
            patientProfileDAO.save(dbrewardHistory);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("patientProfileService-> addRewardsPointSAndHistory", e);
        }
        ComplianceRewardPointDTO complianceRewardPointDTO = new ComplianceRewardPointDTO();
        complianceRewardPointDTO.setRxNo(complianceRewardPoint.getRxNo());
        complianceRewardPointDTO.setRxPatientOutOfPocket("$" + complianceRewardPoint.getRxPatientOutOfPocket());
        complianceRewardPointDTO.setCurrentEarnReward("$" + complianceRewardPoint.getCurrentEarnReward());
        complianceRewardPointDTO.setCurrentRemainBalance("$" + complianceRewardPoint.getCurrentRemainBalance());
        complianceRewardPointDTO.setActivityCount(complianceRewardPoint.getActivityCount());

        return complianceRewardPointDTO;
    }

    public ComplianceRewardPointDTO updateComplianceRewardPointAndRewardHistoryOnRefill(String orderId, PatientProfile profile, int activityId) {
        ComplianceRewardPoint complianceRewardPoint;
        try {
                String practiceCode = "";
                Practices dbpractice = patientProfileDAO.getPracticesById(profile.getPracticeId());
                if(dbpractice!=null){
                practiceCode = dbpractice.getPracticeCode();
                }
            /* fetching order list by patientId and rxnumber where order status is processing, activity count less then 4 order by order date desc. */
            Order order = patientProfileDAO.getOrderDetailById(orderId);
            if (order != null) {
                ///=============Searching for data in complianceRewardPoint table by PatientId, OrderId and RxNumber================///  
                complianceRewardPoint = order.getComplianceRewardPoint();//patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo(), order.getId());
                if (complianceRewardPoint != null) {
                    /////////=========Calculating and Updating Reward Points==============/// 
                    DecimalFormat df = new DecimalFormat("##.00");
                    double dboutofPoket = complianceRewardPoint.getRxPatientOutOfPocket();
                    double dbRemianBalance = complianceRewardPoint.getCurrentRemainBalance();
                    double dbEarnedPoint = complianceRewardPoint.getCurrentEarnReward();
                    double value = dboutofPoket / 4;
                    dbEarnedPoint = dbEarnedPoint + value;
                    dbRemianBalance = dbRemianBalance - value;
                    complianceRewardPoint.setCurrentEarnReward(Double.parseDouble(df.format(dbEarnedPoint)));
                    complianceRewardPoint.setCurrentRemainBalance(Double.parseDouble(df.format(dbRemianBalance)));
                    complianceRewardPoint.setActivityCount(complianceRewardPoint.getActivityCount() + 1);
                    complianceRewardPoint.setUpdateOn(new Date());
                    patientProfileDAO.update(complianceRewardPoint);
                    ////==================================================================/////
                    ///==============logging Reward History ============///
                    RewardHistory dbrewardHistory = new RewardHistory();
                    dbrewardHistory.setOrder(order);
                    dbrewardHistory.setRxNumber(practiceCode+"-"+order.getRxNumber());
                    /////rewardActivity object////////
                    RewardActivity rewardActivity = new RewardActivity();
                    rewardActivity.setActivityId(activityId);
                    //////////////////////////////////
                    dbrewardHistory.setActivityId(rewardActivity);
                    dbrewardHistory.setActivityNumber(complianceRewardPoint.getActivityCount());
                    dbrewardHistory.setPatientId(profile.getPatientProfileSeqNo());
                    dbrewardHistory.setRewardPointId(complianceRewardPoint.getRewardId());
                    dbrewardHistory.setEarnedReward(complianceRewardPoint.getCurrentEarnReward());
                    dbrewardHistory.setActivityDetail(order.getDrugDetail2().getRxLabelName());
                    dbrewardHistory.setCreatedDate(new Date());
                    patientProfileDAO.save(dbrewardHistory);
                    /////=====================================================///
                    ///==================RewardPoint Object for Mobile Team===========///
                    ComplianceRewardPointDTO complianceRewardPointDTO = new ComplianceRewardPointDTO();
                    complianceRewardPointDTO.setRxNo(order.getRxNumber());
                    complianceRewardPointDTO.setRxPatientOutOfPocket("$" + complianceRewardPoint.getRxPatientOutOfPocket());
                    complianceRewardPointDTO.setCurrentEarnReward("$" + complianceRewardPoint.getCurrentEarnReward());
                    complianceRewardPointDTO.setCurrentRemainBalance("$" + complianceRewardPoint.getCurrentRemainBalance());
                    complianceRewardPointDTO.setActivityCount(complianceRewardPoint.getActivityCount());
                    return complianceRewardPointDTO;
                    //////=========================================================/// 
                }
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BloodPressure getPatientBloodPressureByPatientId(Integer patientProfileSeqNo) {
        BloodPressure bloodPressure = new BloodPressure();
        try {
            bloodPressure = patientProfileDAO.getBbloodPressureByPatientId(patientProfileSeqNo);
        } catch (Exception e) {
        }
        return bloodPressure;
    }

    public PatientHeartPulseResult getPatientPatientHeartPulseResultByPatientId(Integer patientProfileSeqNo) {
        PatientHeartPulseResult patientHeartPulseResult = new PatientHeartPulseResult();
        try {
            patientHeartPulseResult = patientProfileDAO.getPatientHeartPulseResultByPatientId(patientProfileSeqNo);
        } catch (Exception e) {
        }
        return patientHeartPulseResult;
    }

    public PatientBodyMassResult getPatientPatientBodyMassResultByPatientId(Integer patientProfileSeqNo) {
        PatientBodyMassResult patientBodyMassResult = new PatientBodyMassResult();
        try {
            patientBodyMassResult = patientProfileDAO.getBodyMassResutByPatientId(patientProfileSeqNo);
        } catch (Exception e) {
        }
        return patientBodyMassResult;
    }

    public String deleteNotificationMessageByMessageId(PatientProfile patient, int notificationMessageId) {
        try {
            NotificationMessages notificationMessage = patientProfileDAO.getNotificationMessagesByMessageId(notificationMessageId);
            if (notificationMessage != null) {
                notificationMessage.setIsDelete("Yes");
                notificationMessage.setUpdatedOn(new Date());
                patientProfileDAO.update(notificationMessage);
                return "Your notification profile is deleted.";
            }
        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            logger.error("PatientProfileService->deleteNotificationMessageByMessageId", ex);
        }

        return "Message against given id not found";
    }

    public NotificationMessages getNotificationByOrderId(String orderId) {
        NotificationMessages notificationMessages;
        try {
            notificationMessages = patientProfileDAO.getNotificationMessageByOrdrId(orderId);
            return notificationMessages;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("patientProfileService->getNotificationByOrderId" + e);
        }
        return null;
    }

    public NotificationMessages getNotificationByOrderIdForRefillRequestFromIpad(String orderId) {
        NotificationMessages notificationMessages;
        try {
            notificationMessages = patientProfileDAO.getNotificationMessageByOrdrIdforRefill(orderId);
            return notificationMessages;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("patientProfileService->getNotificationByOrderIdForRefillRequestFromIpad" + e);
        }
        return null;
    }

    public NotificationMessages getNotificationMessagesByMessageId(int messageId) {
        NotificationMessages notificationMessages;// = new 
        try {
            notificationMessages = patientProfileDAO.getNotificationMessagesByMessageId(messageId);
            return notificationMessages;
        } catch (Exception e) {
            logger.error("Exception -> getgetNotificationMessagesByMessageId", e);
            e.printStackTrace();
        }
        return null;
    }

    public Boolean enrollPatientByTermAndConditionValue(PatientProfile patient, String value) {
        try {
            EnrollementIpad enrollment = patientProfileDAO.getEnrollemtRecord(patient.getPatientProfileSeqNo());
            if (!value.isEmpty() && value.equalsIgnoreCase("yes")) {
                enrollment.setEnrollemtStatus("Enrolled");
                patientProfileDAO.update(enrollment);
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(PatientProfileService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            logger.error("PatientProfileService->deleteNotificationMessageByMessageId", ex);
        }

        return false;
    }

    public PatientProfile getUnEnrolledProfileBySecurityToken(String securityToken) {
        PatientProfile patientProfile;
        try {
            return patientProfile = patientProfileDAO.getProfileBySecurityToken(securityToken);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService -> getPatientProfileBySecurityToken", e);
        }
        return null;
    }

    public AssignedSurveyInfoDTO getSurveyDetailByAssignedSurveyId(int patientId, Long id) {
        try {
            AssignedSurvey surveyData = patientProfileDAO.getAssignedSurveysById(patientId, id);
            if (surveyData != null) {
                AssignedSurveyInfoDTO servy = new AssignedSurveyInfoDTO();
                servy.setAssignedSurveyId(surveyData.getId());
                servy.setPatientId(patientId);
                servy.setStatus(surveyData.getStatus());
                servy.setSurveyCode(surveyData.getSurveyCode());
                servy.setCreatedAt(DateUtil.changeDateFormat(DateUtil.dateToString(surveyData.getCreatedAt(), "yyyy-dd-MM"), "yyyy-dd-MM", "MM/dd/yyyy"));
                servy.setSurveyId(setSurveyObject(surveyData.getSurvey(), surveyData.getId()));
                return servy;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService->getSurveyListByPatientId", e);
        }
        return null;
    }

    private Survey2DTO setSurveyObject(Survey2 servy, long assignSurveyId) {
        if (servy != null) {
            Survey2DTO survey = new Survey2DTO();
            survey.setId(servy.getId());
            survey.setTitle(servy.getTitle());
            survey.setDetails(servy.getDetails());
            survey.setLanguage(servy.getLanguage());
            survey.setStatus(servy.getStatus());
            //survey.setUser(servy.getUser());
            survey.setSurveyType(setSurvyType(servy.getSurveyType()));
            survey.setTheme(setSurvyTheme(servy.getTheme()));
            survey.setQuestionList(setSurveyQuestionsList(servy.getQuestionList(), assignSurveyId));
            return survey;
        }
        return null;
    }

    private SurveyTypeDTO setSurvyType(SurveyType2 servyType) {
        if (servyType != null) {
            SurveyTypeDTO seryType = new SurveyTypeDTO();
            seryType.setTitle(servyType.getTitle());
            return seryType;
        }
        return null;
    }

    private SurveyThemeDTO setSurvyTheme(SurveyTheme theme) {
        if (theme != null) {
            SurveyThemeDTO seryTheme = new SurveyThemeDTO();
            seryTheme.setTheme_Title(theme.getTheme_Title());
            seryTheme.setTheme_Logo(PropertiesUtil.getProperty("PHP_WEB_PDF") + "images/" + theme.getTheme_Logo());
            seryTheme.setHeader_Image(PropertiesUtil.getProperty("PHP_WEB_PDF") + "images/" + theme.getHeader_Image());
            seryTheme.setHeader_Text(theme.getHeader_Text());
            seryTheme.setFooter_Image(PropertiesUtil.getProperty("PHP_WEB_PDF") + "images/" + theme.getFooter_Image());
            seryTheme.setFooter_Text(theme.getFooter_Text());
            return seryTheme;
        }
        return null;
    }

    private List<SurveyQuesDTO> setSurveyQuestionsList(List<SurveyQues> sQuesttionList, long assignSurveyId) {
        List<SurveyQuesDTO> questionList = new ArrayList<>();
        try {
            for (SurveyQues question : sQuesttionList) {
                SurveyQuesDTO ques = new SurveyQuesDTO();
                ques.setsurveyQuestionId(question.getId());
                ques.setInputType(question.getInputType());
                ques.setDescription(question.getDescription());
                ques.setIsrequired(question.getIsrequired());
                ques.setQuestionTitle(question.getQuestionTitle());
                ques.setQuestionCode(question.getQuestionCode());
                ques.setOptionList(setOptionList(question.getOptionList()));
                 SurveyResponse dbSurveyResponse = patientProfileDAO.getSurveyResponseByQuestionId(assignSurveyId, question.getId());
//                List<SurveyResponseAnswerDetial> anslist = patientProfileDAO.getResponseAnswer(question.getId());
                if (dbSurveyResponse != null) {
                    ques.setSurveyResponseList(setResponseAnswer(dbSurveyResponse));
                }
                questionList.add(ques);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    private List<QuestionOptionDTO> setOptionList(List<QuestionOption> optionList) {
        List<QuestionOptionDTO> optList = new ArrayList<>();
        for (QuestionOption option : optionList) {
            QuestionOptionDTO quesOption = new QuestionOptionDTO();
            quesOption.setquestionOptionId(option.getId());
            quesOption.setOptionName(option.getOptionName());
            quesOption.setOptionOrder(option.getOptionOrder());
            optList.add(quesOption);
        }
        return optList;
    }
    private SurveyResponseAnswerDTO setResponseAnswer(SurveyResponse surveyResponse) {
//          List<SurveyResponseAnswerDTO> ansList = new ArrayList<>();
            SurveyResponseAnswerDTO response = new SurveyResponseAnswerDTO();
        try {
//            for (SurveyResponse surveyResponse : surveyResponseList) {
                
//                SurveyResponseAnswerDTO response = new SurveyResponseAnswerDTO();
                  response.setQuestionId(surveyResponse.getSurveyQuestion().getId());
                   response.setInputType(surveyResponse.getInputType());
                   response.setQuestionType(surveyResponse.getQuestionType());
                   response.setSurveyRespponseDetialList(setSurveyResponseDetial(surveyResponse));
//                ansList.add(response);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientPRofileService-> setResponseAnswer", e);
        }
          return response;
    }
          private List<SruveyResponseDetialDTO> setSurveyResponseDetial(SurveyResponse surveyResponse) {
          List<SruveyResponseDetialDTO> ansList = new ArrayList<>();
          try {
                 List<SurveyResponseAnswerDetial> surveyRes = patientProfileDAO.getSurveyResponseAnswer(surveyResponse.getId());
            for(SurveyResponseAnswerDetial answer : surveyRes){
             SruveyResponseDetialDTO response = new SruveyResponseDetialDTO();
             response.setQuestionOptionId(answer.getQuestionOptionId());
             response.setAnswer(answer.getAnswer());

             ansList.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientPRofileService-> setResponseAnswer", e);
        }
          return ansList;
    }
//      private List<SruveyResponseDetialDTO> setSurveyResponseDetial(List<SurveyResponseAnswerDetial> answerdetial) {
//          List<SruveyResponseDetialDTO> ansList = new ArrayList<>();
//          try {
//            for(SurveyResponseAnswerDetial answer : answerdetial){
//             SruveyResponseDetialDTO response = new SruveyResponseDetialDTO();
//             response.setQuestionOptionId(answer.getQuestionOptionId());
//             response.setAnswer(answer.getAnswer());
//
//             ansList.add(response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("Exception: PatientPRofileService-> setResponseAnswer", e);
//        }
//          return ansList;
//    } 
    public List<AssignedSurveyInfoDTO> getAssignedSurveyTitleList(int patientId) {
        List<AssignedSurveyInfoDTO> surveyListDTO = new ArrayList<>();
        try {
            List<AssignedSurvey> SurveyList = patientProfileDAO.getAssignedSurveysBypatientId(patientId);
            if (SurveyList.size() > 0) {
                for (AssignedSurvey survey : SurveyList) {
                    AssignedSurveyInfoDTO surveyDTO = new AssignedSurveyInfoDTO();
                    surveyDTO.setAssignedSurveyId(survey.getId());
                    surveyDTO.setPatientId(patientId);
                    surveyDTO.setStatus(survey.getStatus());
                    surveyDTO.setCreatedAt(DateUtil.dateToString(survey.getCreatedAt(), Constants.DATE_FORMATE));
//                    String dateInString = DateUtil.getCounterVlaue(DateUtil.dateToString(survey.getCreatedAt(), Constants.DD_MM_YYYY_HH_MM_SS), Constants.DD_MM_YYYY_HH_MM_SS);
                    long dateInString = DateUtil.getCounterVlaue(DateUtil.dateToString(survey.getCreatedAt(), Constants.EFFECTIVE_DATE_FORMAT), Constants.DATE_FORMATE);
                    surveyDTO.setSurveysubmitDate(dateInString);
//                    surveyDTO.setSurveysubmitDate(Long.parseLong(dateInString));
                    surveyDTO.setSurveyId(this.setSurveyTitleObject(survey.getSurvey()));
                    surveyListDTO.add(surveyDTO);
                }
                return surveyListDTO;
            }
        } catch (Exception e) {
            logger.error("Exception : patientProfileService-> getAssignedSurveyTitleList", e);
        }

        return null;
    }

    private Survey2DTO setSurveyTitleObject(Survey2 servy) {
        try {
            if (servy != null) {
                Survey2DTO survey = new Survey2DTO();
                survey.setId(servy.getId());
                survey.setTitle(servy.getTitle());
                survey.setStatus(servy.getStatus());
                survey.setSurveyType(setSurvyType(servy.getSurveyType()));
                survey.setTheme(setSurvyTheme(servy.getTheme()));
                survey.setCretaedAt(DateUtil.dateToString(servy.getCreated_At(), Constants.DATE_FORMATE));
                return survey;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Boolean sendNotificationForSurvey(Integer profileId, String messge, String event) {
        try {
            CampaignMessages campaignMessages = this.getNotificationMsgs(messge, event);
            if (campaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            String message = AppUtil.getSafeStr(campaignMessages.getSmstext(), "");
            campaignMessages.setSmstext(message.replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "E, MMMM dd yyyy")));

            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(pushNot);
            this.saveNotificationMessages(campaignMessages, Constants.NO, profileId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getNotificationForSurvey -> notification not sent.");
            return false;
        }
        return true;
    }
//    public ComplianceRewardPointDTO setAssignedSurveyDeatilss(PatientProfile profile, AssignedSurveyLogsDTO sssignedSurveyLogsDTO) {
//        ComplianceRewardPointDTO complianceRewardPointDTO = null;
//        AssignedSurvey sssignedSurvey = patientProfileDAO.getAssignedSurveysById(profile.getPatientProfileSeqNo(), sssignedSurveyLogsDTO.getId());
////        if (sssignedSurvey.getStatus().equals("Completed")) {
////            sssignedSurvey = addRepeatedSurveyResponseLogs(profile, sssignedSurvey);
////            complianceRewardPointDTO = setAssignedSurveyDeatils(profile, sssignedSurveyLogsDTO, sssignedSurvey, "duplicate");
////        } else {
////          complianceRewardPointDTO =  setAssignedSurveyDeatils(profile, sssignedSurveyLogsDTO, sssignedSurvey, sssignedSurvey.getStatus());
////        }
//            complianceRewardPointDTO =  setAssignedSurveyDeatils(profile, sssignedSurveyLogsDTO, sssignedSurvey, sssignedSurvey.getStatus());
//            sssignedSurvey.setStatus("Completed");
//            sssignedSurvey.setUpdatedAt(new Date());
//            this.update(sssignedSurvey);
//            
//            sssignedSurvey = addRepeatedSurveyResponseLogs(profile, sssignedSurvey);
//            complianceRewardPointDTO = setAssignedSurveyDeatils(profile, sssignedSurveyLogsDTO, sssignedSurvey, "duplicate");
//        return complianceRewardPointDTO;
//    }
//    public ComplianceRewardPointDTO setAssignedSurveyDeatils(PatientProfile profile, AssignedSurveyLogsDTO sssignedSurveyLogsDTO, AssignedSurvey sssignedSurvey, String version) {
////         AssignedSurvey newCopyAssSurvy = null;
////        AssignedSurvey sssignedSurvey = patientProfileDAO.getAssignedSurveysById(profile.getPatientProfileSeqNo(), sssignedSurveyLogsDTO.getId());
////        if(sssignedSurvey.getStatus().equals("Completed")){
////             newCopyAssSurvy = addRepeatedSurveyResponseLogs(profile, sssignedSurvey);
////        }
//        ArrayList<SurveyResponseDTO> surveyResponseList = sssignedSurveyLogsDTO.getSurveyResponseList();
//        try {
//            for (SurveyResponseDTO surveyResponseDTOList : surveyResponseList) {
//                SurveyResponse dbsurveyResponse = new SurveyResponse();
//                dbsurveyResponse.setQuestionTitle(surveyResponseDTOList.getQuestionTitle());
//                dbsurveyResponse.setInputType(surveyResponseDTOList.getInputType());
//                dbsurveyResponse.setQuestionType(surveyResponseDTOList.getQuestionType());
//                SurveyQues surveyQues = new SurveyQues(surveyResponseDTOList.getQuestionId());
//                dbsurveyResponse.setSurveyQuestion(surveyQues);
//                dbsurveyResponse.setSurveyId(sssignedSurveyLogsDTO.getSurveyId());
//                // AssignedSurvey assignedSurvey = new AssignedSurvey(sssignedSurvey.getId());
////                dbsurveyResponse.setAssignSurvey(sssignedSurvey.getStatus().equals("Completed") ? newCopyAssSurvy : sssignedSurvey);
//                dbsurveyResponse.setAssignSurvey(sssignedSurvey);
//                dbsurveyResponse.setCreatedAt(new Date());
//                ArrayList<SurveyResponseDetailDTO> surveyResponseDetailList = surveyResponseDTOList.getSurveyResponseDetailList();
//                this.save(dbsurveyResponse);
//                for (SurveyResponseDetailDTO surveyResponseDetailDTOList : surveyResponseDetailList) {
//                    SurveyRespDetail dbSurveyResDetail = new SurveyRespDetail();
//                    QuestionOption questionOption = new QuestionOption(surveyResponseDetailDTOList.getQuestionOptionId());
//                    dbSurveyResDetail.setQuestionOption(questionOption);
//                    dbSurveyResDetail.setSurveyQustionId(surveyResponseDTOList.getQuestionId());
//                    dbSurveyResDetail.setQuestionAnswer(surveyResponseDetailDTOList.getAnswer());
//                    dbSurveyResDetail.setSurveyResponse(dbsurveyResponse);
//
//                    dbSurveyResDetail.setCreatedAt(new Date());
//                    this.save(dbSurveyResDetail);
//                }
//            }
//             saveSurveeyResponse(sssignedSurvey, sssignedSurveyLogsDTO);
//            sssignedSurvey.setStatus("Completed");
//            sssignedSurvey.setUpdatedAt(new Date());
//            this.update(sssignedSurvey);
////            }
//            if ("duplicate".equals(version)) {
//                sssignedSurvey.setStatus("Duplicate");
//                sssignedSurvey.setUpdatedAt(new Date());
//                this.update(sssignedSurvey);
//            }
////            logger.info("...Creating duplicate survey copy in survey logs....");
////            addRepeatedSurveyResponseLogs(profile, sssignedSurvey);
//            ComplianceRewardPoint complianceRPoint = new ComplianceRewardPoint();
//
//            if (sssignedSurveyLogsDTO.getSurveyId() == 48) {
//
//               ComplianceRewardPoint dbComplianceRPoint = patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo());
//                if (dbComplianceRPoint != null && dbComplianceRPoint.getWegaWalletPoint() <5 ) {                 
//                    dbComplianceRPoint.setWegaWalletPoint(dbComplianceRPoint.getWegaWalletPoint()+1);
//                    dbComplianceRPoint.setUpdateOn(new Date());
//                    update(dbComplianceRPoint);
//                } else {
//                    complianceRPoint.setProfile(profile);
//                    complianceRPoint.setWegaWalletPoint(1);
//                    complianceRPoint.setCreatedOn(new Date());
//                    save(complianceRPoint);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ComplianceRewardPointDTO complianceRewardPointDTO;
//        complianceRewardPointDTO = this.updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(profile, Constants.REWARD_ACTIVTIES.SURVEY_SUBMITTION, "Survey", sssignedSurvey.getSurvey().getTitle(), sssignedSurveyLogsDTO.getReadingTime());
//        if (complianceRewardPointDTO == null) {
//            saveActivitesHistory(ActivitiesEnum.SURVEY_POST.getValue(), profile, "", sssignedSurvey.getSurvey().getTitle(), "","");
//        }
//        return complianceRewardPointDTO;
//    }
        public ComplianceRewardPointDTO setAssignedSurveyDeatils(PatientProfile profile, AssignedSurveyLogsDTO sssignedSurveyLogsDTO) {            ComplianceRewardPointDTO complianceRewardPointDTO = null;
            AssignedSurvey sssignedSurvey = patientProfileDAO.getAssignedSurveysById(profile.getPatientProfileSeqNo(), sssignedSurveyLogsDTO.getId());
        try {
            logger.info("...Saving survey Response....");
            saveSurveeyResponse(sssignedSurvey, sssignedSurveyLogsDTO);
            sssignedSurvey.setStatus("Completed");
            sssignedSurvey.setUpdatedAt(new Date());
            this.update(sssignedSurvey);
            logger.info("...Creating duplicate survey copy in survey logs....");
            if(sssignedSurveyLogsDTO.getSurveyId() != 48) {
              sssignedSurvey = addRepeatedSurveyResponseLogs(profile, sssignedSurvey);
            }
//            logger.info("...Saving Duplicate survey Response....");
//            saveSurveeyResponse(sssignedSurvey, sssignedSurveyLogsDTO);
            if (sssignedSurveyLogsDTO.getSurveyId() == 48) {
                logger.info("...Adding WegaVallet Point for CBD survey Response....");
                addWegaValletPointForCBDSurvey(profile);
            }
            complianceRewardPointDTO = this.updateComplianceRewardPointAndRewardHistoryWithOutRxNumber(profile, Constants.REWARD_ACTIVTIES.SURVEY_SUBMITTION, "Survey", sssignedSurvey.getSurvey().getTitle(), sssignedSurveyLogsDTO.getReadingTime());
            if (complianceRewardPointDTO == null) {
                saveActivitesHistory(ActivitiesEnum.SURVEY_POST.getValue(), profile, "", sssignedSurvey.getSurvey().getTitle(), "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complianceRewardPointDTO;
    }
    private void saveSurveeyResponse(AssignedSurvey sssignedSurvey, AssignedSurveyLogsDTO sssignedSurveyLogsDTO) {
        ArrayList<SurveyResponseDTO> surveyResponseList = sssignedSurveyLogsDTO.getSurveyResponseList();
        try {
            surveyResponseList.forEach((surveyResponseDTOList) -> {
                SurveyResponse dbsurveyResponse = new SurveyResponse();
                dbsurveyResponse.setQuestionTitle(surveyResponseDTOList.getQuestionTitle());
                dbsurveyResponse.setInputType(surveyResponseDTOList.getInputType());
                dbsurveyResponse.setQuestionType(surveyResponseDTOList.getQuestionType());
                SurveyQues surveyQues = new SurveyQues(surveyResponseDTOList.getQuestionId());
                dbsurveyResponse.setSurveyQuestion(surveyQues);
                dbsurveyResponse.setSurveyId(sssignedSurveyLogsDTO.getSurveyId());
                dbsurveyResponse.setAssignSurvey(sssignedSurvey);
                dbsurveyResponse.setCreatedAt(new Date());
                ArrayList<SurveyResponseDetailDTO> surveyResponseDetailList = surveyResponseDTOList.getSurveyResponseDetailList();
                this.save(dbsurveyResponse);
                for (SurveyResponseDetailDTO surveyResponseDetailDTOList : surveyResponseDetailList) {
                    SurveyRespDetail dbSurveyResDetail = new SurveyRespDetail();
                    QuestionOption questionOption = new QuestionOption(surveyResponseDetailDTOList.getQuestionOptionId());
                    dbSurveyResDetail.setQuestionOption(questionOption);
                    dbSurveyResDetail.setSurveyQustionId(surveyResponseDTOList.getQuestionId());
                    dbSurveyResDetail.setQuestionAnswer(surveyResponseDetailDTOList.getAnswer());
                    dbSurveyResDetail.setSurveyResponse(dbsurveyResponse);
                    dbSurveyResDetail.setPatientId(sssignedSurvey.getPatientProfile()!= null ? sssignedSurvey.getPatientProfile().getPatientProfileSeqNo(): 0);
                    dbSurveyResDetail.setCreatedAt(new Date());
                    this.save(dbSurveyResDetail); 
                }
            });
        } catch (Exception e) {
        }
    }
    private void addWegaValletPointForCBDSurvey(PatientProfile profile) {
        ComplianceRewardPoint complianceRPoint = new ComplianceRewardPoint();
        try {
            ComplianceRewardPoint dbComplianceRPoint = patientProfileDAO.getComplianceRewardPoint(profile.getPatientProfileSeqNo());
            if (dbComplianceRPoint != null && dbComplianceRPoint.getWegaWalletPoint() < 5) {
                dbComplianceRPoint.setWegaWalletPoint(dbComplianceRPoint.getWegaWalletPoint() + 1);
                dbComplianceRPoint.setUpdateOn(new Date());
                update(dbComplianceRPoint);
            } else {
                complianceRPoint.setProfile(profile);
                complianceRPoint.setWegaWalletPoint(1);
                complianceRPoint.setCreatedOn(new Date());
                save(complianceRPoint);
            }
        } catch (Exception e) {
        }

    }
    private AssignedSurvey addRepeatedSurveyResponseLogs(PatientProfile profile, AssignedSurvey sssignedSurvey) {
         AssignedSurvey assSurvey = new AssignedSurvey();
         try {
                if(sssignedSurvey != null) {
                      BeanUtils.copyProperties(sssignedSurvey, assSurvey);
                      assSurvey.setPatientProfile(profile);
                      assSurvey.setStatus("Duplicate");
                      assSurvey.setSurvey(sssignedSurvey.getSurvey());
                      assSurvey.setSurveyCode(null);
                      assSurvey.setVersion(sssignedSurvey.getVersion()+1);
                      assSurvey.setCreatedAt(new Date());
                      patientProfileDAO.save(assSurvey);
                }
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("PatientProfileService-> addRepeatedSurveyResponseLogs", e);
        }
         return assSurvey;
    }
    public AssignedSurveyLogsDTO jsonMappingToObjects(String json, Gson gson) throws org.json.simple.parser.ParseException {
        AssignedSurveyLogsDTO assigSurvey = new AssignedSurveyLogsDTO();
        try {
            ArrayList<SurveyResponseDTO> surveyResponseList;
            ArrayList<SurveyResponseDetailDTO> surveyResponseDetailList;
            JSONObject rootJSON = (JSONObject) new JSONParser().parse(json);
            JSONArray dataList = (JSONArray) rootJSON.get("data");
            for (Object assignedSurveyList : dataList.toArray()) {
                JSONObject assignedSurvey = (JSONObject) assignedSurveyList;
                assigSurvey = gson.fromJson(assignedSurvey.toString(), AssignedSurveyLogsDTO.class);
                JSONArray survyResponseList = (JSONArray) assignedSurvey.get("survyResponseList");
                surveyResponseList = new ArrayList<>();
                for (Object surveyQuestions : survyResponseList.toArray()) {
                    JSONObject question = (JSONObject) surveyQuestions;
                    //do something with the issue
                    SurveyResponseDTO Question = gson.fromJson(question.toString(), SurveyResponseDTO.class);
//                saveSurveyResponse(Question);
                    //here we return response in dto only
                    surveyResponseList.add(Question);

                    JSONArray questionList = (JSONArray) question.get("SurveyResponseDetailList");
                    surveyResponseDetailList = new ArrayList<>();
                    for (Object SurveyResponseDetailList : questionList.toArray()) {
                        JSONObject option = (JSONObject) SurveyResponseDetailList;
                        //do something with the issue
                        SurveyResponseDetailDTO questionOption = gson.fromJson(option.toString(), SurveyResponseDetailDTO.class);
                        surveyResponseDetailList.add(questionOption);
                    }
                    Question.setSurveyResponseDetailList(surveyResponseDetailList);
                }
                assigSurvey.setSurveyResponseList(surveyResponseList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService-> jsonMappingToObjects: ", e);
        }

        return assigSurvey;
    }

    //Accept term and condition by mobile
    public void updateOrderStatusOnEnrollementByMobile(PatientProfile profile, EnrollementIpad enrollements) throws Exception {

        enrollements.setEnrollemtStatus("Enrolled");
        this.update(enrollements);
        List<Order> ord = patientProfileDAO.getOrdersListByProfileId(profile.getPatientProfileSeqNo());
        for (Order ordelist : ord) {
            OrderStatus stsus = new OrderStatus();
            logger.info("" + ordelist);
            stsus.setId(2);
            ordelist.setOrderStatus(stsus);
            patientProfileDAO.update(ordelist);
            this.addRewardsPointSAndHistory(ordelist, profile, 8);
        }
    }
//    public Boolean sendPushNotificationfromIpadByEventName(PatientProfile patientProfile, Order order, String message, String eventName) {
//        try {
//            if (order == null) {
//                logger.error("No order exist against this order# " + order.getId());
//                return false;
//            }
//            CampaignMessages dbCampaignMessages = this.getNotificationMsgs(message, eventName);
//            if (dbCampaignMessages == null) {
//                logger.error("campaignMessages is null ");
//                return false;
//            }
//            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages);
//            ////////////////////////////////////////////////////////////////
//
//            String messge = AppUtil.getSafeStr(dbCampaignMessages.getSmstext(), "");
//            Practices dbPractise = patientProfileDAO.getPractiseNameById(patientProfile.getPracticeId());
//            String brandRefrance = "", rxLabelName = "", genricOrBrand = "", strength = "";
//            
//            if (order.getDrugDetail2() != null) {
//                 strength = CommonUtil.isNullOrEmpty(order.getStrength()) || order.getStrength().equals(order.getDrugDetail2().getDrugId().toString())? order.getDrugDetail2().getStrength() : order.getStrength();
//                 logger.info("strength :"+strength); 
//                 brandRefrance = order.getDrugDetail2().getBrandReference();
//                rxLabelName = order.getDrugDetail2().getRxLabelName();
//                if ("G".equalsIgnoreCase(order.getDrugDetail2().getGenericOrBrand())) {
//                    if (!CommonUtil.isNullOrEmpty(order.getDrugDetail2().getBrandReference())) {
//                        genricOrBrand = order.getDrugDetail2().getBrandReference();
//                    } else {
//                        genricOrBrand = "Not Applicable";
//                    }
//                } else {
//                    genricOrBrand = "Brand Name Only";
//                }
//                logger.info("\"G\".equalsIgnoreCase(order.getDrugDetail2().getGenericOrBrand()->genricOrBrand :" + genricOrBrand);
//            }
//            campaignMessages.setSmstext(messge.replace(PlaceholderEnum.DATE.getValue(), DateUtil.dateToString(new Date(), Constants.DD_MM_YYYY))
//                    .replace(PlaceholderEnum.TIME.getValue(), DateUtil.dateToString(new Date(), Constants.TIME_HH_MM))
//                    .replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"))
//                    .replace(PlaceholderEnum.RX_NUMBER.getValue(), AppUtil.getSafeStr(order.getRxNumber(), ""))
//                    .replace(PlaceholderEnum.DRUG_NAME.getValue(), rxLabelName)
//                    .replace(PlaceholderEnum.DRUG_STRENGTH.getValue(), AppUtil.getSafeStr(strength, ""))
//                    .replace(PlaceholderEnum.DRUG_TYPE.getValue(), AppUtil.getSafeStr(order.getDrugType(), ""))
//                    .replace(PlaceholderEnum.DRUG_QTY.getValue(), AppUtil.getSafeStr(order.getQty(), ""))
//                    .replace(PlaceholderEnum.BRAND_REFRANCE.getValue(), brandRefrance)
//                    .replace(PlaceholderEnum.PRACTISE_NAME.getValue(), dbPractise.getPracticename())
//                    .replace(PlaceholderEnum.RX_PATIENT_OUT_OF_POCKET.getValue(), AppUtil.getSafeStr(order.getRxPatientOutOfPocket().toString(), ""))
//                    .replace(PlaceholderEnum.GeNERIC_ORBRAND_NAME.getValue(), genricOrBrand)
//            );
//            logger.info("DateUtil.dateToString(new Date(), \"E, MMMM dd yyyy\")" + DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"));
//            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
//            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
//            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, order.getId(),dbPractise));
//            
//            saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order, campaignMessages.getSmstext(), campaignMessages.getSubject());
//        } catch (Exception e) {
//            logger.error("PatientProfileService# sendPushNotificationOnPlaceOrder# ", e);
//            return false;
//        }
//        return true;
//    }

    public void paymentRecivedDetail(Order dbOrder, Long authNumber) {
        PaymentsRecived dbPaymentsRecived = new PaymentsRecived();
        try {
            Order ord = getOrderById(dbOrder.getId());
            dbPaymentsRecived.setOrder(ord);
            if (ord.getComplianceRewardPoint() != null) {
                dbPaymentsRecived.setPatientCopyCard(ord.getComplianceRewardPoint().getCurrentEarnReward());
            }
            dbPaymentsRecived.setOtherPaymentRecived(ord.getRxPatientOutOfPocket());//or wega wallt payment.
            dbPaymentsRecived.setRxThirdPartyPaid(ord.getRxThirdPartyPay());
            dbPaymentsRecived.setTaxPaid(0d);
            dbPaymentsRecived.setHomeDeliveryFeePaid(0d);
            double total = ord.getRxPatientOutOfPocket() + 0d + 0d + ord.getRxThirdPartyPay() + ord.getComplianceRewardPoint().getCurrentEarnReward();
            dbPaymentsRecived.setTotalBillPaid(total);
            dbPaymentsRecived.setAuthNumber(authNumber);
            dbPaymentsRecived.setCreatedAt(new Date());
            this.save(dbPaymentsRecived);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# paymentRecivedDetail# ", e);
        }
    }

    public Boolean savepharmacyPatientMessageReply(PharmacyPatientMessageDTO pharmacyPatientMessageDTO) {

        try {
            PharmacyPatientMessage dbpharmacyPatientMessage = patientProfileDAO.getPharmacyPatientMessageById(pharmacyPatientMessageDTO.getMessgaeId());
            if (dbpharmacyPatientMessage != null) {
                dbpharmacyPatientMessage.setReply(pharmacyPatientMessageDTO.getReply());
                dbpharmacyPatientMessage.setUpdatedAt(new Date());
                save(dbpharmacyPatientMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public QuestionAnswer validateQuestionAnswerByQuestionId(int patientid, Long qid) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        try {
            questionAnswer = patientProfileDAO.getQuestionAnswerByQuestionId(patientid, qid);

        } catch (Exception e) {
            e.printStackTrace();
//         isValid = false;
        }
        return questionAnswer;
    }

    public boolean updateSurveyLogs(PatientProfile dbprofile, long surveyId) {
        try {
            AssignedSurvey assignedSurvey = new AssignedSurvey();
            assignedSurvey.setPatientProfile(dbprofile);
            Survey2 survey = patientProfileDAO.getSurvyById(surveyId);
            assignedSurvey.setSurvey(survey);
            assignedSurvey.setStatus("Pending");
            assignedSurvey.setCreatedAt(new Date());
            this.save(assignedSurvey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception: PatientProfileService-> updateSurveyLogs", e);
            return false;
        }
        return true;
    }

    public boolean sendAppLinks(String mobile) throws Exception {
        boolean phoneValidity = false;
        try {
            String mobileNumber = EncryptionHandlerUtil.getDecryptedString(mobile);
            logger.info("Mobile Number: " + mobileNumber);
            PhoneValidationService phoneValidationService = new PhoneValidationService(Constants.PHONE_VALIDATION_URL);
            phoneValidity = phoneValidationService.checkPhoneValidity(mobileNumber, "", "ComplianceRewards");
            if (!phoneValidity) {
                logger.info("Mobile Number is: " + phoneValidity);
                return phoneValidity;
            }
            if (!isPatientPhoneNumberExist(mobileNumber)) {
                logger.info("This phone# is not registered.");
                throw new Exception("This phone# is not registered.");
            }
            String appLinkMessage = "Download the Compliance Reward app from following links \n" + "IOS " + Constants.IOS_APP_URL_LINK + "\n" + "ANDROID " + Constants.ANDROID_APP_URL_LINK;
            MTDecorator decorator = SMSUtil.sendSmsText(mobileNumber, appLinkMessage);
            NotificationStatus notificationStatus = new NotificationStatus();
            notificationStatus.setEffectiveDate(new Date());
            notificationStatus.setEndDate(new Date());
            if (decorator.getErrorCode() != null) {
                logger.info("Error code is: " + decorator.getErrorCode());
                notificationStatus.setStatusCode(decorator.getErrorCode());
            }
            notificationStatus.setNarrative(decorator.getErrorDescription());
            notificationStatus.setPhoneNumber(EncryptionHandlerUtil.getEncryptedString(mobileNumber));
            patientProfileDAO.save(notificationStatus);
            logger.info("Messsage Sent: " + decorator.getStatusDescription());
            phoneValidity = true;
        } catch (Exception ex) {
            phoneValidity = false;
            logger.error("Exception", ex);
            ex.printStackTrace();
        }
        return phoneValidity;
    }

    public PatientDependentDTO addAppointmentPatientDetail(PatientProfile profile, PatientDependentDTO patientDependentDTO) {

        try {
            AppointmentRequest appointmentRequest = new AppointmentRequest();
            appointmentRequest.setProfile(profile);
            appointmentRequest.setStatusOfAppointment("new");
            appointmentRequest.setReasonForAppointment("Medical Test");
            appointmentRequest.setCreatedAt(new Date());
            this.save(appointmentRequest);

            PatientDependent patientDependent = new PatientDependent();
            if (!"Self".equalsIgnoreCase(patientDependentDTO.getRelationShip())) {
                patientDependent.setFirstName(patientDependentDTO.getFirstName());
                patientDependent.setLastName(patientDependentDTO.getLastName());
                patientDependent.setGender(patientDependentDTO.getGender());
                patientDependent.setDateOfBirth(patientDependentDTO.getDateOfBirth());
                patientDependent.setRelationShip(patientDependentDTO.getRelationShip());
            }
            patientDependent.setProfile(profile);
            patientDependent.setRequest(appointmentRequest);
            patientDependent.setCreatedAt(new Date());
            this.save(patientDependent);
            patientDependentDTO.setAppointmentId(appointmentRequest.getId());
            patientDependentDTO.setProfile(profile.getPatientProfileSeqNo());
            patientDependentDTO.setRequest(appointmentRequest.getId());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception : PatientProfileService->addAppointmentPatientDetail", e);
        }
        return patientDependentDTO;
    }

    public boolean addWayOfCommunication(PatientProfile profile, AppointmentRequestDTO appointmentRequestDTO) {

        try {
            AppointmentRequest dbappointmentRequest = patientProfileDAO.getAppointmentRequestById(appointmentRequestDTO.getAppointmentId(), profile.getPatientProfileSeqNo());
            if (dbappointmentRequest != null) {
                dbappointmentRequest.setInsuranceUsageFlage(appointmentRequestDTO.getInsuranceUsageFlage());
                dbappointmentRequest.setUpdatedAt(new Date());
                this.update(dbappointmentRequest);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception : PatientProfileService->addAppointmentPatientDetail", e);
            return false;
        }
        return false;
    }

    public List<DiseaseDTO> getDisease() {
        List<DiseaseDTO> list = new ArrayList<>();
        try {
            List<DiseaseDetail> diseslist = patientProfileDAO.getDiseaseList();
            diseslist.stream().map((dbList) -> {
                DiseaseDTO diseaseDetail = new DiseaseDTO();
                diseaseDetail.setId(dbList.getId());
                diseaseDetail.setTitle(dbList.getTitle());
                return diseaseDetail;
            }).forEach((diseaseDetail) -> {
                //                  diseaseDetail.setCreatedAt(dbList.getCreatedAt());
                list.add(diseaseDetail);
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception : PatientProfileService->getDisease", e);
        }
        return list;
    }

    public List<PracticesDTO> getDoctorsList(int patientId) {
        ArrayList<PracticesDTO> list = new ArrayList<>();
        try {
            List<RxReporterUsers> listOfDoctors = patientProfileDAO.getPracticesList();
            for (RxReporterUsers RxReporterUsers : listOfDoctors) {
                PracticesDTO practicesDTO = new PracticesDTO();

                practicesDTO.setUserId(RxReporterUsers.getId());
                ReporterChatSession session = patientProfileDAO.getsessionStatus(patientId, RxReporterUsers.getId().intValue());
//                       for(ReporterChatSession reporterChatSession : session){
                practicesDTO.setSessionStatus(session != null ? session.getSessionStatus() : null);
//                       }

                practicesDTO.setPracticename(RxReporterUsers.getName());
                if (RxReporterUsers.getIsOnline() == 1) {
                    practicesDTO.setPracticeAvialable("On-line");
                } else {
                    practicesDTO.setPracticeAvialable("Off-line");
                }

                if (RxReporterUsers.getPracticeId() != null) {
                    practicesDTO.setPracticeId(RxReporterUsers.getPracticeId().getId());
                    practicesDTO.setPracticephonenumber(RxReporterUsers.getPracticeId().getPracticephonenumber());
                    practicesDTO.setPracticecity(RxReporterUsers.getPracticeId().getPracticecity());
                    practicesDTO.setPracticelicensenumber(RxReporterUsers.getPracticeId().getPracticelicensenumber());

                }
                list.add(practicesDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception :PatientProfileService->getDoctorsList ", e);
        }
        return list;
    }

    public PatientAppointmentDTO addAppointment(PatientProfile profile, PatientAppointmentDTO patientAppointmentDTO) {

        try {
            PatientAppointment patientAppointment = new PatientAppointment();
            patientAppointment.setProfile(profile);
            Practices dbPractice = patientProfileDAO.getPracticesById(patientAppointmentDTO.getPhysicianId());

            if (dbPractice != null) {
                patientAppointment.setPhysician(dbPractice);
            }
            AppointmentRequest request = patientProfileDAO.getAppointmentRequestById(patientAppointmentDTO.getRequestId(), profile.getPatientProfileSeqNo());
            if (request != null) {
                patientAppointment.setRequest(request);
            }
            patientAppointment.setAppointmentDateTime(patientAppointmentDTO.getAppointmentDateTime());
            patientAppointment.setCratedAt(new Date());
            this.save(patientAppointment);
            ReporterMessages reporterMessages = new ReporterMessages();
//             RxReporterUsers dbrxReporterUsers = patientProfileDAO.getRxReporterUsersById(patientAppointmentDTO.getPhysicianId());
            RxReporterUsers dbrxReporterUsers = patientProfileDAO.getRxReporterUsersByUserId(patientAppointmentDTO.getUserId());
            if (dbrxReporterUsers != null) {
                reporterMessages.setUserTo(dbrxReporterUsers.getId().intValue());// .setToUser(dbrxReporterUsers.getId().intValue());
            }
            reporterMessages.setIsRead(false);
            reporterMessages.setUserFrom(profile.getPatientProfileSeqNo());
            reporterMessages.setFromType("Patient");
            reporterMessages.setUserText(patientAppointmentDTO.getReasonToVist());
            reporterMessages.setBookingTime(DateUtil.stringToDateTime(patientAppointmentDTO.getAppointmentDateTime(), Constants.EFFECTIVE_DATE_FORMAT));
            reporterMessages.setCrateAt(new Date());
            this.save(reporterMessages);

            patientAppointmentDTO.setAppointmentNumber(reporterMessages.getId());
            patientAppointmentDTO.setPhysicianName(dbrxReporterUsers.getName());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception : PatientProfileService->addAppointment", e);
        }
        return patientAppointmentDTO;
    }

    public void testThread() {
        System.out.println("helo");
    }

    public boolean cancelAppointment(PatientProfile profile, AppointmentRequestDTO appointmentRequestDTO) {

        try {
            AppointmentRequest dbappointmentRequest = patientProfileDAO.getAppointmentRequestById(appointmentRequestDTO.getAppointmentId(), profile.getPatientProfileSeqNo());
            if (dbappointmentRequest != null) {
                dbappointmentRequest.setStatusOfAppointment("Cancel");
                dbappointmentRequest.setUpdatedAt(new Date());
                this.update(dbappointmentRequest);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception : PatientProfileService->addAppointmentPatientDetail", e);
            return false;
        }
        return false;
    }

    public Boolean sendPushNotificationforRxReporter(PatientProfile patientProfile, Order order, String message, String eventName) {
        try {
            // Order order = (Order) patientProfileDAO.findRecordById(new Order(), OrderId);
            if (order == null) {
                logger.error("No order exist against this order# " + order.getId());
                return false;
            }
            CampaignMessages dbCampaignMessages = this.getNotificationMsgs(message, eventName);
            if (dbCampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages);
            ////////////////////////////////////////////////////////////////

            String messge = AppUtil.getSafeStr(dbCampaignMessages.getSmstext(), "");
            Practices dbPractise;
            if (patientProfile.getPracticeId() != null) {
                dbPractise = patientProfileDAO.getPractiseNameById(patientProfile.getPhysicianPracticeId());
            } else {
                dbPractise = patientProfileDAO.getPractiseNameById(patientProfile.getPracticeId());
            }
            String brandRefrance = "", rxLabelName = "", genricOrBrand = "", strength = "", orderStatus = "", daysTillRefill = "";
            if (order.getOrderStatus().getId() != null) {
                OrderStatus dbstaus = patientProfileDAO.getOrderStausById(order.getOrderStatus().getId());
                if (dbstaus != null) {
                    if ("Cancel".equals(dbstaus.getName())) {
                        orderStatus = "Cancelled";
                    } else {
                        orderStatus = dbstaus.getName();
                    }
                }
            }
            String filledDate = "", expiryDate = "", messageSubject = "", patientMessage = "";
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
            MessageThreads dbMessageThreads = patientProfileDAO.getMessageThreadByOrderId(order.getId());
//             PharmacyPatientMessage pharmacyPatientMessage =   patientProfileDAO.getPharmacyPatientMessageByOrderId(order.getId());
            if (dbMessageThreads != null) {
                patientMessage = dbMessageThreads.getMessge();
                messageSubject = "Pharmacy Direct Message";
            }
            String url = "";
            OrderCustomDocumments orderCustomDocumments = patientProfileDAO.getOrderCustomDocumments(order.getId());
            if (orderCustomDocumments != null) {
                url = "http://compliancerewards.ssasoft.com/compliancereward/public/" + orderCustomDocumments;
            }

            if (order.getDrugDetail2() != null) {
//                strength = order.getDrugDetail2().getStrength();
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

            campaignMessages.setSmstext(messge.replace(PlaceholderEnum.DATE.getValue(), DateUtil.dateToString(new Date(), Constants.DD_MM_YYYY))
                    .replace(PlaceholderEnum.TIME.getValue(), DateUtil.dateToString(new Date(), Constants.TIME_HH_MM))
                    .replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"))
                    .replace(PlaceholderEnum.LAST_FILLED.getValue(), filledDate != null ? filledDate : "null")
                    .replace(PlaceholderEnum.RX_EXPIRY_DATE.getValue(), expiryDate != null ? expiryDate : "null")
                    .replace(PlaceholderEnum.RX_NUMBER.getValue(), AppUtil.getSafeStr(dbPractise.getPracticeCode() + order.getRxNumber(), ""))
                    .replace(PlaceholderEnum.DRUG_NAME.getValue(), rxLabelName)
                    .replace(PlaceholderEnum.DAYS_TO_REFILL.getValue(), daysTillRefill)
                    .replace(PlaceholderEnum.DRUG_STRENGTH.getValue(), AppUtil.getSafeStr(strength, ""))
                    .replace(PlaceholderEnum.DRUG_TYPE.getValue(), AppUtil.getSafeStr(order.getDrugType(), ""))
                    .replace(PlaceholderEnum.DRUG_QTY.getValue(), AppUtil.getSafeStr(order.getQty(), ""))
                    .replace(PlaceholderEnum.BRAND_REFRANCE.getValue(), brandRefrance)
                    .replace(PlaceholderEnum.URL_ADVISORY.getValue(), url)
                    .replace(PlaceholderEnum.PT_MSG_SUBJECT.getValue(), messageSubject != null ? messageSubject : "null")
                    .replace(PlaceholderEnum.PT_MESSAGE.getValue(), patientMessage != null ? patientMessage : "null")
                    .replace(PlaceholderEnum.ORDER_STATUS.getValue(), CommonUtil.isNullOrEmpty(orderStatus) ? " " : orderStatus)
                    .replace(PlaceholderEnum.GeNERIC_ORBRAND_NAME.getValue(), genricOrBrand)
                    .replace(PlaceholderEnum.RX_PATIENT_OUT_OF_POCKET.getValue(), AppUtil.getSafeStr(order.getRxPatientOutOfPocket().toString(), "0"))
                    .replace(PlaceholderEnum.REFILL_REMAINING.getValue(), AppUtil.getSafeStr(order.getRefillsRemaining().toString(), "0"))
                    .replace(PlaceholderEnum.PRACTISE_NAME.getValue(), dbPractise.getPracticename())
                    .replace(PlaceholderEnum.DAYS_SUPPLY.getValue(), order.getDaysSupply() > 0 ? "" + order.getDaysSupply() : "Not mentioned")
                    .replace(PlaceholderEnum.FIRST_QUESTION.getValue(), Constants.RX_REPORTER_QUESTION.FIRST_QUESTION)
                    .replace(PlaceholderEnum.SECOND_QUESTION.getValue(), Constants.RX_REPORTER_QUESTION.SECOND_QUESTION)
                    .replace(PlaceholderEnum.THIRD_QUESTION.getValue(), Constants.RX_REPORTER_QUESTION.THIRD_QUESTION)
                    .replace(PlaceholderEnum.OPTION_ONE.getValue(), Constants.RX_REPORTER_QUESTION.OPTION_ONE)
                    .replace(PlaceholderEnum.OPTION_TWO.getValue(), Constants.RX_REPORTER_QUESTION.OPTION_TWO)
                    .replace(PlaceholderEnum.OPTION_THREE.getValue(), Constants.RX_REPORTER_QUESTION.OPTION_THREE)
                    .replace(PlaceholderEnum.OPTION_FOUR.getValue(), Constants.RX_REPORTER_QUESTION.OPTION_FOUR)
                    .replace(PlaceholderEnum.YES_OPTION.getValue(), Constants.YES)
                    .replace(PlaceholderEnum.NO_OPTION.getValue(), Constants.NO)
            );
            logger.info("DateUtil.dateToString(new Date(), \"E, MMMM dd yyyy\")" + DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"));
            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, order.getId(), dbPractise));

            /////////////////////////////////////////
            saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order, campaignMessages.getSmstext(), campaignMessages.getSubject(), dbPractise);
        } catch (Exception e) {
            logger.error("PatientProfileService# sendPushNotificationOnPlaceOrder# aginst :" + message + ": ", e);
            return false;
        }
        return true;
    }

    public Order getOrderByIdAndPatientId(String orderId, int patientId) {
        Order orderInfo = new Order();
        try {
            orderInfo = patientProfileDAO.getOrderDetailById(orderId, patientId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getOrderById ", e);
        }
        return orderInfo;
    }

    public Boolean sendWelcomeNotification(PatientProfile profile, String messge) {
        try {
            CampaignMessages dbcampaignMessages = this.getNotificationMsgs(messge, Constants.WELCOME_ENROLLMENT);
            if (dbcampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbcampaignMessages);
            ////////////////////////////////////////////////////////////////

            String message = AppUtil.getSafeStr(dbcampaignMessages.getSmstext(), "");
            String doctorName = "";
            if (profile.getPhysicianPracticeId() != null) {
                RxReporterUsers doctor = patientProfileDAO.getRxReporterUsersByUserId(profile.getPhysicianPracticeId().longValue());
                if (doctor != null) {
                    doctorName = doctor.getName();
                }
            } else {
                Practices prectice = patientProfileDAO.getPracticesById(profile.getPracticeId());
                if (prectice != null) {
                    doctorName = prectice.getPracticename();
                }
            }
//            String message = AppUtil.getSafeStr(campaignMessages.getSmstext(), "");
            campaignMessages.setSmstext(message
                    .replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"))
                    .replace(PlaceholderEnum.DOCTOR_NAME.getValue(), doctorName)
            );

            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, "", null));
            this.saveNotificationMessages(campaignMessages, Constants.NO, profile.getPatientProfileSeqNo());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getSurvyPushNotification -> notification not sent.");
            return false;
        }
        return true;
    }

    public Order getOrderByPatientId(int patientId) {
        Order orderInfo = new Order();
        try {
            orderInfo = patientProfileDAO.getOrderDetailByPatientId(patientId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getOrderById ", e);
        }
        return orderInfo;
    }

    public List<ReporterMessagesDTO> getReporterCommunication(int userFrom, int userTo) {
        List<ReporterMessagesDTO> list = new ArrayList<>();
        try {
            List<ReporterMessages> listByUserForm = patientProfileDAO.getReportMsgsList(userFrom, userTo);
            List<ReporterMessages> listByUserTo = patientProfileDAO.getReportMsgsList(userTo, userFrom);
            // merging two list 
            List<ReporterMessages> merged = new ArrayList(listByUserForm);
            merged.addAll(listByUserTo);
            merged.sort(Comparator.comparing(ReporterMessages::getCrateAt));//ascending
            for (ReporterMessages reporterMessagesList : merged) {
                ReporterMessagesDTO reporterMessagesDTO = new ReporterMessagesDTO();
                reporterMessagesDTO.setId(reporterMessagesList.getId());
                reporterMessagesDTO.setUserText(reporterMessagesList.getUserText());
                reporterMessagesDTO.setFromType(reporterMessagesList.getFromType());
                reporterMessagesDTO.setCretedAt(DateUtil.dateToString(reporterMessagesList.getCrateAt(), Constants.EFFECTIVE_DATE_FORMAT));
                list.add(reporterMessagesDTO);
            }

//         Collections.sort(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("patientProfileService->getReporterCommunication ", e);
        }
//        Collections.sort(list1, (list1, list2) -> {
//	return list2.getId() - list1.getId();
//});
        return list;
    }

    public boolean saveNotificationMessages(CampaignMessages campaignMessages, String isTestMsg, Integer profileId, Long surveyId, String message, String subject) {
        logger.info("Start->Save NotificationMessages");
        boolean isSaved;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            MessageType messageType = new MessageType();
            MessageTypeId messageTypeId = new MessageTypeId();
            if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                if (campaignMessages.getMessageType() != null) {
                    messageTypeId.setFolderId(campaignMessages.getMessageType().getId().getFolderId());
                    messageTypeId.setMessageTypeId(campaignMessages.getMessageType().getId().getMessageTypeId());
                } else {
                    logger.info("MessageType is null");
                }
                messageType.setId(messageTypeId);
                notificationMessages.setMessageType(messageType);
                notificationMessages.setSubject(this.getMessageSubjectWithprocessedPlaceHolders(
                        subject, "0", null));
                         if (campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_WELCOME
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_NON_COPY_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_HCP_REGISTRATION
                        || campaignMessages.getMessageType().getId().getMessageTypeId() == Constants.MSG_TYPE_ID_REGISITRATION) {
                    notificationMessages.setMessageText(message);
                } 
//                notificationMessages.setMessageText(message);
                notificationMessages.setPushSubject((this.getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), "0", null)));
            } else {
                logger.info("CampaignMessages is null.");
            }
            notificationMessages.setPatientProfile(new PatientProfile(profileId));
            notificationMessages.setSurveyLogsId(surveyId);
            notificationMessages.setStatus("Success");
            notificationMessages.setIsRead(Boolean.FALSE);
            notificationMessages.setIsArchive(Boolean.FALSE);
            notificationMessages.setIsDelete("No");
            notificationMessages.setCreatedOn(new Date());
            notificationMessages.setIsTestMsg(isTestMsg);
            notificationMessages.setPushSubject(AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""));
            patientProfileDAO.save(notificationMessages);
            isSaved = true;
            PatientProfile profile = (PatientProfile) this.findRecordById(new PatientProfile(), profileId);
            if (profile != null && notificationMessages != null && CommonUtil.isNotEmpty(notificationMessages.getPushSubject())) {
                // logger.info("Push Notification " + notificationMessages.getPushSubject());
                if (AppUtil.getSafeStr(profile.getOsType(), "20").equals("20")) {

                    CommonUtil.pushFCMNotification(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                } else {
                    CommonUtil.pushFCMNotificationIOS(profile.getDeviceToken(), null, notificationMessages, "",
                            AppUtil.getSafeStr(notificationMessages.getPushSubject(), ""), profile);
                }
            }
        } catch (Exception e) {
            isSaved = false;
            e.printStackTrace();
            logger.error("Exception -> NotificationMessages", e);
        }
        logger.info("End->Save NotificationMessages: " + isSaved);
        return isSaved;
    }

    public Boolean saveResponseRxReporterSurvey(int patientId, RxReporterSurveyResponseDTO rxReporterSurveyResponseDTO) {
        boolean isSaved = false;
        try {

            for (QuestionAndOptionDTO questionAndOptionDTOList : rxReporterSurveyResponseDTO.getResponsListDetial()) {
                RxReporterResponse rxReporterResponse = new RxReporterResponse();
                rxReporterResponse.setPatientId(patientId);
                rxReporterResponse.setQuestionId(questionAndOptionDTOList.getQuestionId());
                rxReporterResponse.setOptionTitle(questionAndOptionDTOList.getOptionTitle());
                rxReporterResponse.setCreatedAt(new Date());
                this.save(rxReporterResponse);
            }
            isSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService-> saveResponseRxReporterSurvey", e);
            isSaved = false;
        }
        return isSaved;
    }

    public RxReporterSurveyResponseDTO jsonMappingToObjectsForRxReporterSurvey(String json, Gson gson) throws org.json.simple.parser.ParseException {
        RxReporterSurveyResponseDTO assigSurvey = new RxReporterSurveyResponseDTO();
        try {

            ArrayList<QuestionAndOptionDTO> surveyResponseDetailList;
            JSONObject rootJSON = (JSONObject) new JSONParser().parse(json);
            JSONArray questionList = (JSONArray) rootJSON.get("responsListDetial");
            surveyResponseDetailList = new ArrayList<>();
            for (Object SurveyResponseDetailList : questionList.toArray()) {
                JSONObject option = (JSONObject) SurveyResponseDetailList;
                QuestionAndOptionDTO questionOption = gson.fromJson(option.toString(), QuestionAndOptionDTO.class);
                surveyResponseDetailList.add(questionOption);
            }
            assigSurvey.setResponsListDetial(surveyResponseDetailList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService-> jsonMappingToObjects: ", e);
        }

        return assigSurvey;
    }

    public Boolean checkNotificationStatus(PatientProfile profile) {
        boolean isFirstOrder = false;
        try {
            List<Order> ordlist = patientProfileDAO.getOrderDetailListByPatientIdd(profile.getPatientProfileSeqNo());
            if (ordlist.size() == 1) {
                isFirstOrder = true;
            } else {
                isFirstOrder = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PatientProfileService-> checkNotificationStatus: ", e);
            isFirstOrder = false;
        }
        return isFirstOrder;
    }

    public Boolean sendRxReporterNotificationforAdvisory(PatientProfile profile, String messge, String event) {
        try {
            CampaignMessages dbcampaignMessages = this.getNotificationMsgs(messge, event);
            if (dbcampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbcampaignMessages);
            ////////////////////////////////////////////////////////////////

            String message = AppUtil.getSafeStr(dbcampaignMessages.getSmstext(), "");
            String doctorName = "";
            if (profile.getPhysicianPracticeId() != null) {
                RxReporterUsers doctor = patientProfileDAO.getRxReporterUsersByUserId(profile.getPhysicianPracticeId().longValue());
                if (doctor != null) {
                    doctorName = doctor.getName();
                }
            } else {
                Practices prectice = patientProfileDAO.getPracticesById(profile.getPracticeId());
                if (prectice != null) {
                    doctorName = prectice.getPracticename();
                }
            }
//            String message = AppUtil.getSafeStr(campaignMessages.getSmstext(), "");
            campaignMessages.setSmstext(message
                    .replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), "E, MMMM dd yyyy"))
                    .replace(PlaceholderEnum.DOCTOR_NAME.getValue(), doctorName)
            );

            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, "", null));
            this.saveNotificationMessages(campaignMessages, Constants.NO, profile.getPatientProfileSeqNo());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getSurvyPushNotification -> notification not sent.");
            return false;
        }
        return true;
    }

    public boolean isOrderCancel(String orderId, int patientId) {
        try {
            Order dbOrd = getOrderById(orderId);
            OrderStatus status = patientProfileDAO.getOrderStausById(Constants.ORDER_STATUS.CANCEL_ORDER_ID);
            if (dbOrd != null) {
                dbOrd.setOrderStatus(status);
                dbOrd.setUpdatedAt(new Date());
                update(dbOrd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService->isOrderCancel: ", e);
            return false;
        }
        return true;
    }

    public List<MessageThreadDTO> getUserMessageByID(int profileId, boolean flag, String msgType) throws ParseException, Exception {
        List<MessageThreadDTO> list = new ArrayList<>();
        try {
               String genricOrBrand = "", filledDate = "", daysTillRefill = "";
               List<MessageThreads>  messageThreadsList = patientProfileDAO.getMessageThreadByOrderId(profileId, msgType);          
            for (MessageThreads dbmsgist : messageThreadsList) {
                MessageThreadDTO messageThreadDTO = new MessageThreadDTO();
                messageThreadDTO.setMessgeId(dbmsgist.getId());
                messageThreadDTO.setMessage(dbmsgist.getMessge());
                messageThreadDTO.setIsRead(dbmsgist.getMarkAsRead());
                messageThreadDTO.setMessgeStaus(dbmsgist.getMessgeStaus());
                messageThreadDTO.setCratedAt(DateUtil.dateToString(dbmsgist.getCreatedAt(), Constants.USA_DATE_TIME_SECOND_FORMATE_12H));
                messageThreadDTO.setCreateAtinDateFormat(dbmsgist.getCreatedAt());
                messageThreadDTO.setPhysicianName(dbmsgist.getPhysicianName());
                if(!CommonUtil.isNullOrEmpty(dbmsgist.getPhysicianId())) {
                    Object obj = patientProfileDAO.getPhysicianNameById(dbmsgist.getPhysicianId()); 
                    messageThreadDTO.setPhysicianName(obj != null ? obj.toString() : ""); 
                }
                if (dbmsgist.getOrder() != null) {
                    messageThreadDTO.setOrderId(dbmsgist.getOrder().getId());
                    messageThreadDTO.setRxNumber(dbmsgist.getOrder().getRxNumber() + "-" + "0" + dbmsgist.getOrder().getRefillCount());
                }
                if (dbmsgist.getOrder() != null) {
                    Order ord = dbmsgist.getOrder();
                    OrderDetailDTO order = new OrderDetailDTO();
                    order.setQty(ord.getQty());
                    order.setRefillsRemaining(ord.getRefillsRemaining().toString());
                    if(ord.getRxExpiredDate()!= null)
                    {order.setRxExpiredDateStr(DateUtil.dateToString(ord.getRxExpiredDate(), "MM/dd/yyyy"));}
                    order.setRxRxpiredDate(ord.getRxExpiredDate());
                    order.setOrderStatusName(ord.getOrderStatus().getName());
                    order.setDrugType(AppUtil.getSafeStr(ord.getDrugType(), ""));
                    order.setRxPatientOutOfPocketStr(ord.getRxPatientOutOfPocket().toString());//as assisteent Auth
                    order.setOrgPatientOutOfPocket(ord.getRxOutOfPocket()); //as Pataitn out of Pocket
                    order.setDaysSupply(ord.getDaysSupply());
                    if(flag == true && ord.getPrescriberId() != null){
                    order.setPrescriberId(ord.getPrescriberId());
                    Practices practice = patientProfileDAO.getPracticesById(ord.getPrescriberId());
                    order.setPrescriberName(practice!= null ? practice.getPracticename() : "");
                    order.setPracticeLogo(practice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +practice.getPracticeLogo():"");
                    
                    }
                    
                    if (ord.getRxProcessedAt() == null) {
                        filledDate = DateUtil.dateToString(ord.getUpdatedAt(), Constants.USA_DATE_FORMATE);
                    } else {
                        filledDate = DateUtil.dateToString(ord.getRxProcessedAt(), Constants.USA_DATE_FORMATE);
                    }
                    if (filledDate != null) {
                        Date today = new Date();
                        long dayDiff = DateUtil.dateDiffInDays(today, ord.getNextRefillDate());
                        System.out.println("DAYS DIFF " + dayDiff + " ID " + order.getId());
                        if (dayDiff >= 0) {
                            daysTillRefill = Long.toString(dayDiff);
                        } else {
                            daysTillRefill = Long.toString(dayDiff);
                        }
                    }
//                     newOrder.setStrength(order.getStrength() != null ? order.getStrength() : order.getDrugDetail2().getStrength() != null ? order.getDrugDetail2().getStrength().trim() : "");
                    order.setLastFilledDate(filledDate);
                    order.setDaysToRefill(daysTillRefill);                  
                    if (ord.getDrugDetail2() != null) {
                        DrugDetail2 dbDrug = ord.getDrugDetail2();
                        order.setDrugName(dbDrug.getRxLabelName());
                        order.setBrandRefrance(dbDrug.getBrandReference());
                        String strength = CommonUtil.isNullOrEmpty(dbDrug.getStrength()) || dbDrug.getStrength().equals(dbDrug.getDrugId().toString()) ? dbDrug.getStrength() : dbDrug.getStrength();
                        order.setStrength(ord.getStrength()!=null ? ord.getStrength() : strength != null ? strength : "");
                        order.setGenericOrBrand(AppUtil.getSafeStr(dbDrug.getGenericOrBrand(), ""));
                    }
                    messageThreadDTO.setOrder(order);
                }
                list.add(messageThreadDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception : patientProfileService-> getUserMessageByID", e);
        }
        return list;
    }
//    
//    public List<MessageThreadDTO> getMessgesDetialChatByPAtientId (int profileId) {
//        List<MessageThreadDTO> list = new ArrayList<>();
//        try {
//               String filledDate = "", daysTillRefill = "";
//            List<MessageThreads> messageThreadsList = patientProfileDAO.getMessageThreadByOrderId(profileId); 
//            for (MessageThreads dbmsgist : messageThreadsList) {
//                MessageThreadDTO messageThreadDTO = new MessageThreadDTO();
//                messageThreadDTO.setMessgeId(dbmsgist.getId());
//                messageThreadDTO.setMessage(dbmsgist.getMessge());
//                messageThreadDTO.setIsRead(dbmsgist.getMarkAsRead());
//                messageThreadDTO.setCratedAt(DateUtil.changeDateFormat(DateUtil.dateToString(dbmsgist.getCreatedAt(), "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm:ss", "MM/dd/yyyy hh:mm:ss"));
//               if(dbmsgist.getOrder() != null){
//                messageThreadDTO.setOrderId(dbmsgist.getOrder().getId());
//                messageThreadDTO.setRxNumber(dbmsgist.getOrder().getRxNumber()+"0"+dbmsgist.getOrder().getRefillCount());
//               }
//                if (dbmsgist.getOrder() != null) {
//                    OrderDetailDTO order = new OrderDetailDTO();
//                    order.setQty(dbmsgist.getOrder().getQty());
//                    order.setRefillsRemaining(dbmsgist.getOrder().getRefillsRemaining().toString());
//                    if(dbmsgist.getOrder().getRxExpiredDate()!= null)
//                    {order.setRxExpiredDateStr(DateUtil.dateToString(dbmsgist.getOrder().getRxExpiredDate(), "MM/dd/yyyy"));}
//                    order.setRxRxpiredDate(dbmsgist.getOrder().getRxExpiredDate());
//                    order.setOrderStatusName(dbmsgist.getOrder().getOrderStatus().getName());
//                    order.setDrugType(AppUtil.getSafeStr(dbmsgist.getOrder().getDrugType(), ""));
//                    order.setRxPatientOutOfPocketStr(dbmsgist.getOrder().getRxPatientOutOfPocket().toString());
//                    order.setDaysSupply(dbmsgist.getOrder().getDaysSupply());
//                    if (dbmsgist.getOrder().getRxProcessedAt() == null) {
//                        filledDate = DateUtil.dateToString(dbmsgist.getOrder().getUpdatedAt(), Constants.USA_DATE_FORMATE);
//                    } else {
//                        filledDate = DateUtil.dateToString(dbmsgist.getOrder().getRxProcessedAt(), Constants.USA_DATE_FORMATE);
//                    }
//                    if (filledDate != null) {
//                        Date today = new Date();
//                        long dayDiff = DateUtil.dateDiffInDays(today, dbmsgist.getOrder().getNextRefillDate());
//                        System.out.println("DAYS DIFF " + dayDiff + " ID " + order.getId());
//                        if (dayDiff >= 0) {
//                            daysTillRefill = Long.toString(dayDiff);
//                        } else {
//                            daysTillRefill = Long.toString(dayDiff);
//                        }
//                    }
//                    order.setLastFilledDate(filledDate);
//                    order.setDaysToRefill(daysTillRefill);                  
//                    if (dbmsgist.getOrder().getDrugDetail2() != null) {
//                        order.setDrugName(dbmsgist.getOrder().getDrugDetail2().getRxLabelName());
//                        order.setBrandRefrance(dbmsgist.getOrder().getDrugDetail2().getBrandReference());
//                        order.setStrength(CommonUtil.isNullOrEmpty(dbmsgist.getOrder().getStrength()) || dbmsgist.getOrder().getStrength().equals(dbmsgist.getOrder().getDrugDetail2().getDrugId().toString()) ? dbmsgist.getOrder().getDrugDetail2().getStrength() : dbmsgist.getOrder().getStrength());
//                        order.setGenericOrBrand(AppUtil.getSafeStr(dbmsgist.getOrder().getDrugDetail2().getGenericOrBrand(), ""));
//                    }
//                    messageThreadDTO.setOrder(order);
//                }
//                list.add(messageThreadDTO);
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
    
    public boolean isMarkAsReadOrderAndGneralQuestions(QuestionUpdtaeDTO  questionUpdtaeDTO, int patientId) {
            boolean isMarked = false;
            try {
                if(!questionUpdtaeDTO.getOrderId().equals("0")){
                    MessageThreads dbMessges = patientProfileDAO.getMessageThreadByOrderIdAndMsgId(questionUpdtaeDTO.getOrderId(), questionUpdtaeDTO.getMsgOrQuestionId());
                    if(dbMessges!=null){
                    dbMessges.setMarkAsRead(Boolean.TRUE);
                    dbMessges.setUpdateAt(new Date());
                    this.update(dbMessges);
                    }
                }else {
                    QuestionAnswer dbQuestionAnser = patientProfileDAO.getQuestionAnswerByQuestionId(patientId, new Long(questionUpdtaeDTO.getMsgOrQuestionId())); 
                     if(dbQuestionAnser!=null){
                     dbQuestionAnser.setIsRead(Boolean.TRUE);
                     dbQuestionAnser.setUpdatedAt(new Date());
                     this.update(dbQuestionAnser);
                     } 
                }
                 isMarked = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService->isMarkAsReadOrderAndGneralQuestions: ", e);
            isMarked = false;
        }
            return isMarked;
    }
    public List<PracticesDTO> getReporterSessionDetialList(PatientProfile profile) {
        ArrayList<PracticesDTO> list = new ArrayList<>();
        try {
            //List<ReporterMessages> userTo =  patientProfileDAO.getReportMsgsByPatient(profile.getPatientProfileSeqNo(),profile.getPhysicianPracticeId());
//            List<ReporterMessages> userForm =  patientProfileDAO.getReportMsgsByReporter(patientId);
            //if(userTo!=null || userForm != null){                            
            List<RxReporterUsers> listOfDoctors = patientProfileDAO.getPracticesList();
            for (RxReporterUsers RxReporterUsers : listOfDoctors) {
                PracticesDTO practicesDTO = new PracticesDTO();

                practicesDTO.setUserId(RxReporterUsers.getId());
//                ReporterChatSession session = patientProfileDAO.getsessionStatus(patientId, RxReporterUsers.getId().intValue());
//                if(session != null){
//                   practicesDTO.setSessionStatus(session != null ? session.getSessionStatus() : null);
////                   practicesDTO.setSessionId(session.getId());
////                   practicesDTO.setCreatedAt(DateUtil.dateToString(session.getCreatedAt(), Constants.USA_DATE_FORMATE));
//                }
                practicesDTO.setPracticename(RxReporterUsers.getName());
                if (RxReporterUsers.getIsOnline() == 1) {
                    practicesDTO.setPracticeAvialable("On-line");
                } else {
                    practicesDTO.setPracticeAvialable("Off-line");
                }
                if (RxReporterUsers.getPracticeId() != null) {
                    practicesDTO.setPracticeId(RxReporterUsers.getPracticeId().getId());
                    practicesDTO.setPracticephonenumber(RxReporterUsers.getPracticeId().getPracticephonenumber());
                    practicesDTO.setPracticecity(RxReporterUsers.getPracticeId().getPracticecity());
                    practicesDTO.setPracticelicensenumber(RxReporterUsers.getPracticeId().getPracticelicensenumber());
                }
                
                if(patientProfileDAO.getReportMsgsByPatient(profile.getPatientProfileSeqNo(), RxReporterUsers.getId().intValue()).size()>0||
                patientProfileDAO.getReportMsgsByPatient(RxReporterUsers.getId().intValue(),profile.getPatientProfileSeqNo()).size()>0)
                list.add(practicesDTO);
                
            }
            //}
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception :PatientProfileService->getReporterSessionDetialList ", e);
        }
        return list;
    }
    public List<ReporterMessagesDTO> getReporterDetial(int chatSessionId){
        List<ReporterMessagesDTO> list = new ArrayList<>();
        try {
           List<ReporterMessages> reporterMessge = patientProfileDAO.getReportMsgsListBySessionId(chatSessionId);
           for(ReporterMessages reporterList : reporterMessge){
             ReporterMessagesDTO reporterMessagesDTO = new ReporterMessagesDTO();
             reporterMessagesDTO.setId(chatSessionId);
             reporterMessagesDTO.setFromType(reporterList.getFromType());
             reporterMessagesDTO.setIsRead(reporterList.getIsRead());
             reporterMessagesDTO.setUserText(reporterList.getUserText());
             reporterMessagesDTO.setUserTo(reporterList.getUserTo());
             reporterMessagesDTO.setUserFrom(reporterList.getUserFrom());
             reporterMessagesDTO.setBookingTime(reporterList.getBookingTime());
             reporterMessagesDTO.setCratedAt(DateUtil.dateToString(reporterList.getCrateAt(), Constants.USA_DATE_FORMATE));
             list.add(reporterMessagesDTO);
           }
        } catch (Exception e) {
        }
        return list;
    }
       public boolean optOutRefillReminder(PatientProfile patientProfile, String rxNumber) throws Exception {
        boolean isAdded = false;
           try {
                 List<Order> ord = patientProfileDAO.getOrderListByRxnumber(patientProfile.getPatientProfileSeqNo(), rxNumber);
                for(Order dbOrderlist : ord){
                   dbOrderlist.setOptOutRefillReminder(1);
                   dbOrderlist.setUpdatedOn(new Date());
                   patientProfileDAO.update(dbOrderlist);
                }
                isAdded = true;
           } catch (Exception e) {
                isAdded = false;
               e.printStackTrace();
              logger.info("Exception :PatientProfileService->optOutRefillReminder ", e);
           }
              
        return isAdded;
    }
      public Boolean sendCBDSurvey(Integer profileId, String messge, String evenet) {
        try {
            AssignedSurvey surveylog = new AssignedSurvey();
            CampaignMessages dbCampaignMessages = this.getNotificationMsgs(messge, evenet);
            if (dbCampaignMessages == null) {
                logger.error("campaignMessages is null ");
                return false;
            }
//            Survey2 survey = (Survey2) patientProfileDAO.findRecordById(new Survey2(), 48);
            Survey2 survey = patientProfileDAO.getSurvyById(Constants.CBD_SURVY_ID);
            if (survey != null) {
                surveylog = patientProfileDAO.getSurveyLogsBySurveyIdd(profileId, survey.getId());
            }
            CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages); 
//            String message = AppUtil.getSafeStr(dbCampaignMessages.getSmstext(), "");
//            campaignMessages.setSmstext(surveylog.getId() != null ? surveylog.getId().toString() : "");
            String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
            System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
            campaignMessages.setPushNotification(getMessageSubjectWithprocessedPlaceHolders(pushNot, "", null));
//            this.saveNotificationMessages(campaignMessages, Constants.NO, profileId);
            this.saveNotificationMessages(campaignMessages, Constants.NO, profileId, surveylog.getId(), campaignMessages.getSmstext(), campaignMessages.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getSurvyPushNotification -> notification not sent.");
            return false;
        }
        return true;
    }
    private NotificationMessages getNotificationMessagesDetialByPatient(NotificationMessages messages, String del) {
        NotificationMessages notificationMessages = new NotificationMessages();
//        OrderDetailDTO ordeDto = new OrderDetailDTO();
        try {
            notificationMessages.setId(messages.getId());
            notificationMessages.setNotificationMsgId(messages.getId());
            PatientProfile profile = messages.getPatientProfile();
            logger.info("Start..Going to get data from Order Object" + new Date());
            if (messages.getOrders() != null && messages.getOrders().getId() != null) {
                notificationMessages.setOrderDto(populateRefillRxData(messages.getOrders(), ""));
                Order ord = messages.getOrders();
//                MessageThreads dbMessageThreads = patientProfileDAO.getMessageThreadByOrderId(ord.getId());
//                if (dbMessageThreads != null) {
//                    notificationMessages.setPatientOrdMessge(dbMessageThreads.getMessge());
//                    notificationMessages.setPatientOrdMsgSubject("Pharmacy Direct Message");
//                }
                OrderCustomDocumments orderCustomDocumments = patientProfileDAO.getOrderCustomDocumments(ord.getId());
                if (orderCustomDocumments != null) {
                    notificationMessages.setOrderPdfDocument(PropertiesUtil.getProperty("PHP_WEB_PDF") + orderCustomDocumments.getCustomDocuments());
                    notificationMessages.setOrderDocumentMessgeSub(orderCustomDocumments.getMessage());
                }
            }
            logger.info("End..fetching data from Order Object" + new Date());
            notificationMessages.setIsRead(messages.getIsRead());
            notificationMessages.setIsArchive(messages.getIsArchive());
            if (messages.getMessageType() != null && messages.getMessageType().getId() != null) {
                MessageType messgeType = messages.getMessageType();
                Integer messgeTypeId = messgeType.getId().getMessageTypeId();
                notificationMessages.setMessageTypeId(messgeTypeId);
                logger.info("Strt..fetching data from messgeType.getId().getMessageTypeId() :" + Constants.MSG_TYPE_ID + " : " + new Date());
//                if (messgeTypeId == Constants.MSG_TYPE_ID) {
//                    QuestionAnswer questionAnswer = patientProfileDAO.getQuestionAnswerByQuestionId(messages.getQuestionId());
//                    if (questionAnswer != null) {
//                        notificationMessages.setQuestionId(questionAnswer.getId());
//                        notificationMessages.setQuestionText(questionAnswer.getQuestion());
//                        notificationMessages.setQuestionAnserImg(questionAnswer.getQuestionImge());
//                        notificationMessages.setQuesAnswerText(questionAnswer.getAnswer());
//                    }
//                }
            }
            if (profile != null && profile.getPatientProfileSeqNo() != null) {
                notificationMessages.setProfileId(profile.getPatientProfileSeqNo());
                notificationMessages.setMobileNumber(EncryptionHandlerUtil.getDecryptedString(profile.getMobileNumber()));
            }
            AssignedSurvey dbAssignsurvey;
            if(!CommonUtil.isNullOrEmpty(del)) {
             dbAssignsurvey = patientProfileDAO.getAssignedSurveysByIdd(profile.getPatientProfileSeqNo(), messages.getSurveyLogsId());
            }else{
             dbAssignsurvey = patientProfileDAO.getAssignedSurveysById(profile.getPatientProfileSeqNo(), messages.getSurveyLogsId());
            }
             if (dbAssignsurvey != null) {
                 notificationMessages.setAssignStatus(dbAssignsurvey.getStatus());
              }
            
            notificationMessages.setAssignSurveyId(messages.getSurveyLogsId());
            notificationMessages.setAssignsurvyId(messages.getSurveyLogsId() != null ? messages.getSurveyLogsId().toString() : "");
            
            notificationMessages.setSubject(EncryptionHandlerUtil.getDecryptedString(messages.getSubject()));
            notificationMessages.setCreatedOn(DateUtil.formatDate(messages.getCreatedOn(), "MM/dd/yyyy"));
            notificationMessages.setCreatedonStringFormat(DateUtil.dateToString(messages.getCreatedOn(), "E MM-dd-yyyy"));
            notificationMessages.setTimeAgo(DateUtil.getDateDiffInSecondsFromCurrentDate(messages.getCreatedOn()));
            notificationMessages.setIsCritical(messages.getIsCritical());
            notificationMessages.setMessageCategory(Constants.ORDER_NOTIFICATION);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notificationMessages;
    }
     public Boolean isOrderExist(String rxNumber) {
         boolean isExist = false;
         try {
              Order ord = patientProfileDAO.getOrderDetialByRxNumber(rxNumber);
              if(ord != null) {
                  isExist = true;
              } else {
                  isExist = false;                         
              }
         } catch (Exception e) {
             logger.error("patientProfileService-> isOrderExist : ",e);
         }
         return isExist;
     }
     public List<OrderDetailDTO> getAllRefillDoneOrdersList(String rxNumber, int profileId) {
          ArrayList<OrderDetailDTO> list = new ArrayList<>();
         try {
               List<Order> dblist = patientProfileDAO.getOrderListByRxnumber(rxNumber, profileId);
               for(Order ordlist : dblist) {
                OrderDetailDTO newOrder = populateRefillRxData(ordlist, "");
                list.add(newOrder);           
               }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return list;
     }
          public List<OrderDetailDTO> getAllRefillAbleOrdersList( int profileId) {
          ArrayList<OrderDetailDTO> list = new ArrayList<>();
         try {
               List<Order> dblist = patientProfileDAO.getRefillAbleOrderListByPatientId(profileId);
               for(Order ordlist : dblist) {
                OrderDetailDTO newOrder = populateRefillRxData(ordlist, "");
                list.add(newOrder);           
               }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return list;
     }
        public void logsActivitiesHistory(PatientProfile profile, int activityId, String activityName, String activityDetails, String readingTime, String practiceCode, Order order) {
           try {
                 if (activityName.equalsIgnoreCase("PatientBodyMass")) {
                    saveActivitesHistory(ActivitiesEnum.ADD_BODY_MASS_ACTIVITY.getValue(), profile, practiceCode+order.getRxNumber(), activityDetails, readingTime,order.getId());
                }
                if (activityName.equalsIgnoreCase("PatientHeartPulseResult")) {
                    saveActivitesHistory(ActivitiesEnum.ADD_HEART_PULS_ACTIVITY.getValue(), profile, practiceCode+order.getRxNumber(), activityDetails, readingTime, order.getId());
                }
                if (activityName.equalsIgnoreCase("PatientGlucoseResults")) {
                    saveActivitesHistory(ActivitiesEnum.ADD_GLUCOSE_ACTIVITY.getValue(), profile, practiceCode+order.getRxNumber(), activityDetails, readingTime, order.getId());
                }
                if (activityName.equalsIgnoreCase("PatientBloodPresureResult")) {
                    saveActivitesHistory(ActivitiesEnum.ADD_BLOOD_PRESSURE_ACTIVITY.getValue(), profile, practiceCode+order.getRxNumber(), activityDetails, readingTime, order.getId());
                }
                if (activityName.equalsIgnoreCase("Survey")) {
                    String rxnumber;
                    if(order!=null){
                     rxnumber = practiceCode+order.getRxNumber();
                    }else {
                     rxnumber = "null";
                    }
                    saveActivitesHistory(ActivitiesEnum.SURVEY_POST.getValue(), profile, rxnumber, activityDetails, "","");
                }
           } catch (Exception e) {
               logger.error("Exception : patientProfileService->logsActivitiesHistory",e);
           }
         
       }
    public void updateComplianceRewardPoint(ComplianceRewardPoint complianceRewardPoint) {
        try {
            DecimalFormat df = new DecimalFormat("##.00");
            double dboutofPoket = complianceRewardPoint.getRxPatientOutOfPocket();
            double dbRemianBalance = complianceRewardPoint.getCurrentRemainBalance();
            double dbEarnedPoint = complianceRewardPoint.getCurrentEarnReward();
            double value = dboutofPoket / 4;
            dbEarnedPoint = dbEarnedPoint + value;
            dbRemianBalance = dbRemianBalance - value; 
            complianceRewardPoint.setCurrentEarnReward(Double.parseDouble(df.format(dbEarnedPoint)));
            complianceRewardPoint.setCurrentRemainBalance(Double.parseDouble(df.format(dbRemianBalance)));
            complianceRewardPoint.setActivityCount(complianceRewardPoint.getActivityCount() + 1);
            complianceRewardPoint.setUpdateOn(new Date());
            patientProfileDAO.update(complianceRewardPoint);
        } catch (Exception e) {
            logger.error("Exception : patientProfileService->updateComplianceRewardPoint",e);
        }
    }
    public void saveRewardHistory(VegaWalletRewardPoint vegaWalletRewardPoint,ComplianceRewardPoint complianceRewardPoint,PatientProfile profile, Order order, String practiceCode, int activityId, String activityDetails, String readingTime) {
        try {
                    RewardHistory dbrewardHistory = new RewardHistory();
                    if(order != null){
                     dbrewardHistory.setOrder(order);
                     dbrewardHistory.setRxNumber(practiceCode+"-"+order.getRxNumber());
                    }
                    //////////////rewardActivityObject//////////////
                    RewardActivity rewardActivity = new RewardActivity();
                    rewardActivity.setActivityId(activityId);
                    /////////////////////////////////////////
                    dbrewardHistory.setActivityId(rewardActivity);
                     dbrewardHistory.setPatientId(profile.getPatientProfileSeqNo());
                     if(complianceRewardPoint!=null){
                        dbrewardHistory.setActivityNumber(complianceRewardPoint.getActivityCount());
                        dbrewardHistory.setRewardPointId(complianceRewardPoint.getRewardId());
                        dbrewardHistory.setEarnedReward(complianceRewardPoint.getCurrentEarnReward());
                     }
                     if(vegaWalletRewardPoint!=null) {
                        dbrewardHistory.setVeagaWalletPoint(vegaWalletRewardPoint.getVegaWalletPoint());
                        dbrewardHistory.setVegaWalletActivityCount(vegaWalletRewardPoint.getActivityCount());
                     }
                    dbrewardHistory.setActivityDetail(activityDetails);
                    dbrewardHistory.setCreatedDate(new Date());
                    dbrewardHistory.setReadingTime(readingTime);
                    save(dbrewardHistory);
        } catch (Exception e) {
             logger.error("Exception : patientProfileService->saveRewardHistory",e);
        }
    }
    public ComplianceRewardPointDTO getRewardsPointDetialForMobileApp(VegaWalletRewardPoint vegaWalletRewardPoint,ComplianceRewardPoint complianceRewardPoint, PatientProfile profile, Order order, String practiceCode) {
        try {

            ComplianceRewardPointDTO complianceRewardPointDTO = new ComplianceRewardPointDTO();
            if (order != null && order.getRxPatientOutOfPocket() != null) {
                complianceRewardPointDTO.setRxNo(practiceCode + "-" + order.getRxNumber());
                complianceRewardPointDTO.setDrugName(order.getDrugDetail2().getRxLabelName());
                complianceRewardPointDTO.setStrength(order.getStrength());
                complianceRewardPointDTO.setDrugType(order.getDrugType());
                complianceRewardPointDTO.setGenericOrBrand(order.getDrugDetail2().getGenericOrBrand());
                complianceRewardPointDTO.setRxPatientOutOfPocket("$" + complianceRewardPoint.getRxPatientOutOfPocket());
                complianceRewardPointDTO.setCurrentEarnReward("$" + complianceRewardPoint.getCurrentEarnReward());
                complianceRewardPointDTO.setCurrentRemainBalance("$" + complianceRewardPoint.getCurrentRemainBalance());
                complianceRewardPointDTO.setActivityCount(complianceRewardPoint.getActivityCount());
            }
            if (vegaWalletRewardPoint != null) {
                if (vegaWalletRewardPoint.getVegaWalletPoint() >= 5) {
                    complianceRewardPointDTO.setWegaWalletPointOverDue("Exceed Limit of 5 point.");
                    complianceRewardPointDTO.setEarnedWegaWalletBounusPoint(vegaWalletRewardPoint.getVegaWalletPoint());
                } else {
                    complianceRewardPointDTO.setEarnedWegaWalletBounusPoint(vegaWalletRewardPoint.getVegaWalletPoint());
                }
                complianceRewardPointDTO.setActivityCount(complianceRewardPoint.getActivityCount());
                complianceRewardPointDTO.setVegaWalletActivityCount(vegaWalletRewardPoint.getActivityCount());
                complianceRewardPointDTO.setTotalwwPointYouCanEarn(5);
                complianceRewardPointDTO.setRemianingwwPoint(5 - vegaWalletRewardPoint.getVegaWalletPoint());
            }

                 return complianceRewardPointDTO;
        } catch (Exception e) {
            logger.error("Exception : patientProfileService->getRewardsPointDetialForMobileApp", e);
        }
        return null;
    }
    public VegaWalletRewardPoint saveOrUpdateWegaWalletPoint(VegaWalletRewardPoint vegaWalletRewardPoint,PatientProfile profile) {
//         VegaWalletRewardPoint vegaWalletRewardPoint;
        try {
                     vegaWalletRewardPoint = patientProfileDAO.getVegaWalletRewardPoint(profile.getPatientProfileSeqNo());
                  if(vegaWalletRewardPoint != null) {
                    vegaWalletRewardPoint.setActivityCount(vegaWalletRewardPoint.getActivityCount() + 1);
                    if (vegaWalletRewardPoint.getVegaWalletPoint() < 5) {
                        vegaWalletRewardPoint.setVegaWalletPoint(vegaWalletRewardPoint.getVegaWalletPoint() + 0.5f);
                    }
                    vegaWalletRewardPoint.setUpdatedAt(new Date());
                    patientProfileDAO.update(vegaWalletRewardPoint);
                } else {
                    vegaWalletRewardPoint = new VegaWalletRewardPoint();
                    vegaWalletRewardPoint.setActivityCount(1);
                    vegaWalletRewardPoint.setVegaWalletPoint(0.5f);
                    vegaWalletRewardPoint.setPatientId(profile.getPatientProfileSeqNo());
                    vegaWalletRewardPoint.setCreatedAt(new Date());
                    patientProfileDAO.save(vegaWalletRewardPoint);           
                }     
        } catch (Exception e) {
            logger.error("Exception : patientProfileService->updateWegaWalletPoint", e);
        }
        return vegaWalletRewardPoint;
    }
    /** Replica APi Methods
     * 
     * 
     */
    
    public List<NotificationMessages> getNotificationWs(Integer profileId) {
        List<NotificationMessages> list = new ArrayList<>();
        try {
              logger.info("Strt...Going to Query"+new Date());
            List<NotificationMessages> lstDb = patientProfileDAO.getNotificationMessagesByProfileId(profileId);           
            logger.info("End...Going to End Quey)"+new Date());
            logger.info("Strt...Going to iterate List for (NotificationMessages messages)"+new Date());
            for (NotificationMessages messages : lstDb) {
                NotificationMessages notificationMessages = getNotificationMessagesByPatient(messages);

                notificationMessages.setMessageText(null);
                notificationMessages.setIsCritical(messages.getIsCritical());
                list.add(notificationMessages);
            }
             logger.info("End...Going to iterate List for (NotificationMessages messages : lstDb)"+new Date());
//            Collections.sort(list, CommonUtil.byDate);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getNotificationWs", e);
            e.printStackTrace();
        }
        return list;
    }
    
     private NotificationMessages getNotificationMessagesByPatient(NotificationMessages messages) {
        NotificationMessages notificationMessages = new NotificationMessages();
        try {
            notificationMessages.setId(messages.getId());
            notificationMessages.setNotificationMsgId(messages.getId());
            PatientProfile profile = messages.getPatientProfile();
            if (messages.getOrders() != null && messages.getOrders().getId() != null) {
//                 logger.info("Start..Going to get data from Database for fetch Order object"+new Date()); will run query to fetch order object from db
                 Order ord = messages.getOrders();
                notificationMessages.setOrderId(ord.getId());
                notificationMessages.setRxNo(AppUtil.getSafeStr(ord.getRxNumber(), ""));
                notificationMessages.setPatientOutOfPocket(ord.getRxPatientOutOfPocket()); //AssistAuth
                notificationMessages.setOrgPatientOutOfPocket(ord.getRxOutOfPocket());//RxPatientOutOfPocket
                notificationMessages.setQty(ord.getQty());
                notificationMessages.setDrugType(ord.getDrugType());
                notificationMessages.setDaysSupply(ord.getDaysSupply());///will run query to fetch orderstatus object from db
                notificationMessages.setOrderStatus(ord.getOrderStatus().getName() != null ? ord.getOrderStatus().getName() : "null"); /// 
                notificationMessages.setRxExpiredDate(ord.getRxExpiredDate() != null ? ord.getRxExpiredDate() : null);
                notificationMessages.setRefillsRemaining(ord.getRefillsRemaining());
                notificationMessages.setViewStatus(ord.getViewStatus());
                notificationMessages.setAssistanceAuth(ord.getRxPatientOutOfPocket());
                try {
                    MessageThreads dbMessageThreads = patientProfileDAO.getMessageThreadByOrderId(ord.getId());
//                    logger.info("end..Going to get data from Database for getMessageThreadByOrderId"+new Date());
                    if (dbMessageThreads != null) {
                        notificationMessages.setPatientOrdMessge(dbMessageThreads.getMessge());
                        notificationMessages.setPatientOrdMsgSubject("Pharmacy Direct Message");
                    }
//                    logger.info("Start..Going to get data from Database for getOrderCustomDocumments"+new Date());
                    OrderCustomDocumments orderCustomDocumments = patientProfileDAO.getOrderCustomDocumments(ord.getId());
//                    logger.info("end..Going to get data from Database for getOrderCustomDocumments"+new Date());
                    if (orderCustomDocumments != null) {
                        String url = PropertiesUtil.getProperty("PHP_WEB_PDF") + orderCustomDocumments.getCustomDocuments();
                        notificationMessages.setOrderPdfDocument(url);
                        notificationMessages.setOrderDocumentMessgeSub(orderCustomDocumments.getMessage());
//                   9     url = "http://compliancerewards.ssasoft.com/compliancereward/public/"+orderCustomDocumments.getCustomDocuments();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
//                    logger.error("Exception:PatientProfileService: getNotificationMessages-> pharmacyPatientMessage", e);
                }
                String filledDate = "";
                if (ord.getRxProcessedAt() == null) {
                    filledDate = DateUtil.dateToString(ord.getUpdatedAt(), Constants.USA_DATE_FORMATE);
                } else {
                    filledDate = DateUtil.dateToString(ord.getRxProcessedAt(), Constants.USA_DATE_FORMATE);
                }
                notificationMessages.setLastFilledDate(filledDate != null ? filledDate : "");
                if (ord.getRefillsRemaining() != null
                        && ord.getRefillsRemaining() > 0 && ord.getRefillDone() == 0
                        && ord.getNextRefillDate() != null) {
                    Date nextRefillD = ord.getNextRefillDate();
                    Date today = new Date();
                    long dayDiff = DateUtil.dateDiffInDays(today, nextRefillD);
                    int diff = (int) dayDiff;
                    if (diff >= 0) {
                        notificationMessages.setRefillRemainingDaysCount(diff);
                    } else {
                        notificationMessages.setRefillRemainingDaysCount(diff);
                    }
                }
                if (ord.getDrugDetail2() != null) {
                    DrugDetail2 drugdetial = ord.getDrugDetail2(); // will run query to fetch drug detail object from db
                    notificationMessages.setBrandRefrance(drugdetial.getBrandReference());
                    notificationMessages.setDrugName(drugdetial.getRxLabelName());
                    notificationMessages.setGenericOrBrand(drugdetial.getGenericOrBrand());
                    String strength = CommonUtil.isNullOrEmpty(ord.getStrength()) || ord.getStrength().equals(drugdetial.getDrugId().toString()) ? drugdetial.getStrength() : ord.getStrength();
                    notificationMessages.setStrength(strength != null ? strength : "");                                   
                }

                if (ord.getComplianceRewardPoint() != null) { // will run query to fetch compliance object from db
                    notificationMessages.setEarnedRewardPoint(ord.getComplianceRewardPoint().getCurrentEarnReward());
                    notificationMessages.setRxOutOfPocket(ord.getComplianceRewardPoint().getRxPatientOutOfPocket());
                }

                if (ord.getOrderStatus() != null && CommonUtil.isNotEmpty(ord.getOrderStatus().getName())) {
                    notificationMessages.setOrderStatus(ord.getOrderStatus().getName());
                }
            }
                   notificationMessages.setIsRead(messages.getIsRead());
                   notificationMessages.setIsArchive(messages.getIsArchive());
            if (messages.getMessageType() != null && messages.getMessageType().getId() != null) {
                MessageType messgeType = messages.getMessageType();
                notificationMessages.setMessageTypeId(messgeType.getId().getMessageTypeId());
                if (messgeType.getId().getMessageTypeId() == Constants.MSG_TYPE_ID) {
//                    logger.info("strt..Going to get data from Database for getQuestionAnswerByNotificationId"+new Date());
//                    QuestionAnswer questionAnswer = patientProfileDAO.getQuestionAnswerByNotificationId(messages.getId());
                      QuestionAnswer questionAnswer = patientProfileDAO.getQuestionAnswerByQuestionId(messages.getQuestionId());
//                    logger.info("end..Going to get data from Databasefor getQuestionAnswerByNotificationId"+new Date());getQuestionAnswerByQuestionId
                    if (questionAnswer != null) {
                        notificationMessages.setQuestionId(questionAnswer.getId());
                        notificationMessages.setQuestionText(questionAnswer.getQuestion());
                        notificationMessages.setQuestionAnserImg(questionAnswer.getQuestionImge());
                        notificationMessages.setQuesAnswerText(questionAnswer.getAnswer());
                    }
                }
                if (messgeType.getId().getMessageTypeId() == Constants.MSG_TYPE_ID_Survey ||
                        messgeType.getId().getMessageTypeId() == Constants.SURVEY_REMINDER ||
                        messgeType.getId().getMessageTypeId() == Constants.MSG_TYPE_ID_CBD_SURVY ||
                        messgeType.getId().getMessageTypeId() == Constants.SECOND_SURVEY_REIMDER) {
                    //After deleting textMessge old data may be crewtaing isssue for mobile 
                    if(messages.getMessageText() != null){
                          notificationMessages.setAssignSurveyId(Long.parseLong(EncryptionHandlerUtil.getDecryptedString(messages.getMessageText())));
                          notificationMessages.setAssignsurvyId(EncryptionHandlerUtil.getDecryptedString(messages.getMessageText()));
                    }else {
                     notificationMessages.setAssignSurveyId(messages.getSurveyLogsId());  
                     notificationMessages.setAssignsurvyId(messages.getSurveyLogsId() != null ? messages.getSurveyLogsId().toString(): "");
                    }
//                    logger.info("strt..Going to get data from Database for getAssignedSurveysById"+new Date());
                     AssignedSurvey dbAssignsurvey = patientProfileDAO.getAssignedSurveysById(profile.getPatientProfileSeqNo(), messages.getSurveyLogsId());
//                      logger.info("End..Going to get data from Database for getAssignedSurveysById"+new Date());
                     if (dbAssignsurvey != null) {
                        notificationMessages.setAssignStatus(dbAssignsurvey.getStatus());
                    }
                }

            }
            if (profile != null && profile.getPatientProfileSeqNo() != null) {
                notificationMessages.setProfileId(profile.getPatientProfileSeqNo());
                notificationMessages.setMobileNumber(EncryptionHandlerUtil.getDecryptedString(profile.getMobileNumber()));
            }

            notificationMessages.setSubject(EncryptionHandlerUtil.getDecryptedString(messages.getSubject()));
            notificationMessages.setCreatedOn(DateUtil.formatDate(messages.getCreatedOn(), "MM/dd/yyyy"));
            notificationMessages.setCreatedonStringFormat(DateUtil.dateToString(messages.getCreatedOn(), "E MM-dd-yyyy"));
            notificationMessages.setTimeAgo(DateUtil.getDateDiffInSecondsFromCurrentDate(messages.getCreatedOn()));
            notificationMessages.setIsCritical(messages.getIsCritical());
            notificationMessages.setMessageCategory(Constants.ORDER_NOTIFICATION);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notificationMessages;
    }
        public List<NotificationMessages> getNotificationMessagesByProfileId(Integer profileId) {
        List<NotificationMessages> list = new ArrayList<>();
        try {
            for (NotificationMessages messages : patientProfileDAO.getNonArchivedNotificationMessagesByProfileId(profileId)) {
//                NotificationMessages notificationMessages = getNotificationMessages(messages);//getNotificationMessagesDetialByPatient
                 NotificationMessages notificationMessages = getNotificationMessagesDetialByPatient(messages, "del");//getNotificationMessagesDetialByPatient
                list.add(notificationMessages);
            }
            Collections.sort(list, CommonUtil.byDate);
        } catch (Exception e) {
            logger.error("Exception -> getNotificationMessagesByProfileId", e);
            e.printStackTrace();
        }
        return list;
    }
        private NotificationMessages getNotificationMessagesByPatientDetial(NotificationMessages messages, int profileId) {
        NotificationMessages notificationMessages = new NotificationMessages();
        
        try {
            notificationMessages.setId(messages.getId());
            notificationMessages.setNotificationMsgId(messages.getId());
//            PatientProfile profile = messages.getPatientProfile();
            if (messages.getOrders() != null && messages.getOrders().getId() != null) {
//                 logger.info("Start..Going to get data from Database for fetch Order object"+new Date()); will run query to fetch order object from db
                 Order ord = messages.getOrders();
                notificationMessages.setOrderId(ord.getId());
                notificationMessages.setRxNo(AppUtil.getSafeStr(ord.getRxNumber(), ""));
                notificationMessages.setPatientOutOfPocket(ord.getRxPatientOutOfPocket()); //AssistAuth
                notificationMessages.setOrgPatientOutOfPocket(ord.getRxOutOfPocket());//RxPatientOutOfPocket
                notificationMessages.setQty(ord.getQty());
                notificationMessages.setDrugType(ord.getDrugType());
                notificationMessages.setDaysSupply(ord.getDaysSupply());///will run query to fetch orderstatus object from db
                notificationMessages.setOrderStatus(ord.getOrderStatus().getName() != null ? ord.getOrderStatus().getName() : "null"); /// 
                notificationMessages.setRxExpiredDate(ord.getRxExpiredDate() != null ? ord.getRxExpiredDate() : null);
                notificationMessages.setRefillsRemaining(ord.getRefillsRemaining());
                notificationMessages.setViewStatus(ord.getViewStatus());
                notificationMessages.setAssistanceAuth(ord.getRxPatientOutOfPocket());
                try {
//                    MessageThreads dbMessageThreads = patientProfileDAO.getMessageThreadByOrderId(ord.getId());
////                    logger.info("end..Going to get data from Database for getMessageThreadByOrderId"+new Date());
//                    if (dbMessageThreads != null) {
//                        notificationMessages.setPatientOrdMessge(dbMessageThreads.getMessge());
//                        notificationMessages.setPatientOrdMsgSubject("Pharmacy Direct Message");
//                    }
//                    logger.info("Start..Going to get data from Database for getOrderCustomDocumments"+new Date());
                     if(messages.getMessageType().getId().getMessageTypeId() == Constants.MEDICAL_ADVISORY){
                    OrderCustomDocumments orderCustomDocumments = patientProfileDAO.getOrderCustomDocumments(ord.getId());
//                    logger.info("end..Going to get data from Database for getOrderCustomDocumments"+new Date());
                    if (orderCustomDocumments != null) {
                        String url = PropertiesUtil.getProperty("PHP_WEB_PDF") + orderCustomDocumments.getCustomDocuments();
                        notificationMessages.setOrderPdfDocument(url);
                        notificationMessages.setOrderDocumentMessgeSub(orderCustomDocumments.getMessage());
//                   9     url = "http://compliancerewards.ssasoft.com/compliancereward/public/"+orderCustomDocumments.getCustomDocuments();
                    }
                     }
                } catch (Exception e) {
                    e.printStackTrace();
//                    logger.error("Exception:PatientProfileService: getNotificationMessages-> pharmacyPatientMessage", e);
                }
                String filledDate = "";
                if (ord.getRxProcessedAt() == null) {
                    filledDate = DateUtil.dateToString(ord.getUpdatedAt(), Constants.USA_DATE_FORMATE);
                } else {
                    filledDate = DateUtil.dateToString(ord.getRxProcessedAt(), Constants.USA_DATE_FORMATE);
                }
                notificationMessages.setLastFilledDate(filledDate != null ? filledDate : "");
                if (ord.getRefillsRemaining() != null
                        && ord.getRefillsRemaining() > 0 && ord.getRefillDone() == 0
                        && ord.getNextRefillDate() != null) {
                    Date nextRefillD = ord.getNextRefillDate();
                    Date today = new Date();
                    long dayDiff = DateUtil.dateDiffInDays(today, nextRefillD);
                    int diff = (int) dayDiff;
                    if (diff >= 0) {
                        notificationMessages.setRefillRemainingDaysCount(diff);
                    } else {
                        notificationMessages.setRefillRemainingDaysCount(diff);
                    }
                }
                if (ord.getDrugDetail2() != null) {
                    DrugDetail2 drugdetial = ord.getDrugDetail2(); // will run query to fetch drug detail object from db
                    notificationMessages.setBrandRefrance(drugdetial.getBrandReference());
                    notificationMessages.setDrugName(drugdetial.getRxLabelName());
                    notificationMessages.setGenericOrBrand(drugdetial.getGenericOrBrand());
                    String strength = CommonUtil.isNullOrEmpty(ord.getStrength()) || ord.getStrength().equals(drugdetial.getDrugId().toString()) ? drugdetial.getStrength() : ord.getStrength();
                    notificationMessages.setStrength(strength != null ? strength : "");                                   
                }

//                if (ord.getComplianceRewardPoint() != null) { // will run query to fetch compliance object from db
//                    notificationMessages.setEarnedRewardPoint(ord.getComplianceRewardPoint().getCurrentEarnReward());
//                    notificationMessages.setRxOutOfPocket(ord.getComplianceRewardPoint().getRxPatientOutOfPocket());
//                }

                if (ord.getOrderStatus() != null && CommonUtil.isNotEmpty(ord.getOrderStatus().getName())) {
                    notificationMessages.setOrderStatus(ord.getOrderStatus().getName());
                }
            }
                   notificationMessages.setIsRead(messages.getIsRead());
                   notificationMessages.setIsArchive(messages.getIsArchive());
            if (messages.getMessageType() != null && messages.getMessageType().getId() != null) {
//                MessageType messgeType = messages.getMessageType();
                notificationMessages.setMessageTypeId(messages.getMessageType().getId().getMessageTypeId());
                     notificationMessages.setAssignSurveyId(messages.getSurveyLogsId());  
                     notificationMessages.setAssignsurvyId(messages.getSurveyLogsId() != null ? messages.getSurveyLogsId().toString(): "");
                    if(messages.getSurveyLogsId() != null){
                                         AssignedSurvey dbAssignsurvey = patientProfileDAO.getAssignedSurveysById(profileId, messages.getSurveyLogsId());
                     if (dbAssignsurvey != null) {
                        notificationMessages.setAssignStatus(dbAssignsurvey.getStatus());
                    }
                    }

            }
//            if (profile != null && profile.getPatientProfileSeqNo() != null) {
//                notificationMessages.setProfileId(profile.getPatientProfileSeqNo());
//               / notificationMessages.setMobileNumber(EncryptionHandlerUtil.getDecryptedString(profile.getMobileNumber()));
//            }

            notificationMessages.setSubject(EncryptionHandlerUtil.getDecryptedString(messages.getSubject()));
            notificationMessages.setCreatedOn(DateUtil.formatDate(messages.getCreatedOn(), "MM/dd/yyyy"));
            notificationMessages.setCreatedonStringFormat(DateUtil.dateToString(messages.getCreatedOn(), "E MM-dd-yyyy"));
            notificationMessages.setTimeAgo(DateUtil.getDateDiffInSecondsFromCurrentDate(messages.getCreatedOn()));
            notificationMessages.setIsCritical(messages.getIsCritical());
            notificationMessages.setMessageCategory(Constants.ORDER_NOTIFICATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        return notificationMessages;
    }   
      public List<NotificationMessages> getNotificationDetialWs(Integer profileId) {
        List<NotificationMessages> list = new ArrayList<>();
        try {
              logger.info("Strt...Going to Query"+new Date());
            List<NotificationMessages> lstDb = patientProfileDAO.getNotificationMessagesByProfileId(profileId);           
            logger.info("End...Going to End Quey)"+new Date());
            logger.info("Strt...Going to iterate List for (NotificationMessages messages)"+new Date());
            for (NotificationMessages messages : lstDb) {
                NotificationMessages notificationMessages = getNotificationMessagesByPatientDetial(messages,profileId);

                notificationMessages.setMessageText(null);
                notificationMessages.setIsCritical(messages.getIsCritical());
                list.add(notificationMessages);
            }
             logger.info("End...Going to iterate List for (NotificationMessages messages : lstDb)"+new Date());
//            Collections.sort(list, CommonUtil.byDate);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getNotificationWs", e);
            e.printStackTrace();
        }
        return list;
    }   
      public boolean saveHcpMessages(PatientProfile profile, String question, String img, String orderId, Long questionId){
          try {
            
                Order order = (Order) this.findOrderById(orderId);
                MessageThreads msgThread = new MessageThreads();
                Practices dbPractice = patientProfileDAO.getPractiseNameById(profile.getPracticeId());
                if (dbPractice != null) {
                    msgThread.setPractice(dbPractice);
                }
                 if (!CommonUtil.isNullOrEmpty(questionId)) {
                     msgThread.setQuestionId(questionId);                
                 }
                 if (order != null) {
                      msgThread.setOrder(order);
                 }
                msgThread.setProfile(profile);                
                msgThread.setAttachment(img);
                msgThread.setMessge(question);
                msgThread.setMessgeStaus("received");
                msgThread.setMarkAsRead(Boolean.FALSE);
                msgThread.setCreatedAt(new Date());
                this.save(msgThread);
          } catch (Exception e) {
          }
          return true;
      }
    public GenralDTO savedHcpQuestion(PatientProfile profile, String question, String img, String orderId, Long questionId, Long prescriberId,int physicianId) {
//        boolean isSaved = false;
        GenralDTO genralDTO = new GenralDTO();
        try {
            if ((!(orderId.equals("0") || CommonUtil.isNullOrEmpty(orderId)) && CommonUtil.isNullOrEmpty(questionId))
                    || (CommonUtil.isNullOrEmpty(orderId) && (!CommonUtil.isNullOrEmpty(questionId)))) {
                Order order = (Order) this.findOrderById(orderId);
                MessageThreads msgThread = new MessageThreads();
                Practices dbPractice = patientProfileDAO.getPractiseNameById(profile.getPracticeId());
                if (dbPractice != null) {
                    msgThread.setPractice(dbPractice);
                }
                if (!CommonUtil.isNullOrEmpty(questionId)) {
                    msgThread.setQuestionId(questionId);
                }
                if (order != null) {
                    msgThread.setOrder(order);
                }
                msgThread.setProfile(profile);
                msgThread.setAttachment(img);
                msgThread.setMessge(question);
                msgThread.setMessgeStaus("received");
                msgThread.setMarkAsRead(Boolean.FALSE);
                msgThread.setMesssgeType("HCP");
                msgThread.setPhysicianId(physicianId);
                msgThread.setCreatedAt(new Date());
                this.save(msgThread);
                if (msgThread.getOrder() != null) {
                    genralDTO.setServerDate(msgThread.getCreatedAt().getTime());
                    if (msgThread.getOrder() != null) {
                        Practices practice = patientProfileDAO.getPracticesById(msgThread.getOrder().getPrescriberId());
                        genralDTO.setPresciberName(practice != null ? practice.getPracticename() : "");
                        genralDTO.setPracticeLogo(practice.getPracticeLogo() != null ? PropertiesUtil.getProperty("PHP_WEB_PDF") + practice.getPracticeLogo() : "");
                    }
                }
//                genralDTO.setPhysicianName(msgThread.getPhysicianName() != null ? msgThread.getPhysicianName() : "");
//                isSaved = true;
//                 if (isSaved == true) {
                if (order != null) {
                    saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", msgThread.getMessge() + " type: HCP Order Message", "", "");
                } else {
                    saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", msgThread.getMessge() + " type: HCP Genral chat", "", "");
                }
//                 }
            } else if (CommonUtil.isNullOrEmpty(orderId) && questionId == 0l && !CommonUtil.isNullOrEmpty(prescriberId)) {
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setQuestionType("General_Question");
                questionAnswer.setPatientProfile(profile);
                questionAnswer.setQuestion(question);
                questionAnswer.setQuestionImge(img);
                questionAnswer.setPrescriberId(prescriberId);
                questionAnswer.setIsRead(Boolean.FALSE);
                questionAnswer.setQuestionTime(new Date());
                patientProfileDAO.save(questionAnswer);
//                isSaved = true;
//                if (isSaved == true) {
                saveActivitesHistory(ActivitiesEnum.QUESTION_POST.getValue(), profile, "", questionAnswer.getQuestion() + " type:" + questionAnswer.getQuestionType(), "", "");
//                }
                MessageThreads msgThread = new MessageThreads();
                Practices dbPractice = patientProfileDAO.getPractiseNameById(profile.getPracticeId());
                if (dbPractice != null) {
                    msgThread.setPractice(dbPractice);
                }
                msgThread.setQuestionId(questionAnswer.getId());
                msgThread.setProfile(profile);
                msgThread.setAttachment(img);
                msgThread.setMessge(question);
                msgThread.setMesssgeType("HCP");
                msgThread.setMessgeStaus("received");
                msgThread.setMarkAsRead(Boolean.FALSE);
                msgThread.setPhysicianId(physicianId);
                msgThread.setCreatedAt(new Date());
                this.save(msgThread);
                genralDTO.setServerDate(msgThread.getCreatedAt().getTime());
                if (msgThread.getOrder() != null) {
                    Practices practice = patientProfileDAO.getPracticesById(msgThread.getOrder().getPrescriberId());
                    genralDTO.setPresciberName(practice != null ? practice.getPracticename() : "");
                    genralDTO.setPracticeLogo(practice.getPracticeLogo() != null ? PropertiesUtil.getProperty("PHP_WEB_PDF") + practice.getPracticeLogo() : "");
                }
//                genralDTO.setPhysicianName(msgThread.getPhysicianName() != null ? msgThread.getPhysicianName() : "");
            } else {
                logger.error("PatientProfileService# savedHcpQuestion false ");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# savedHcpQuestion", e);
        }
        return genralDTO;
    }
    public List<PracticesDTO> getPracticeDetails(PatientProfile profile) {
        List<PracticesDTO> list = new ArrayList<>();
        try {
            List<Order> orderlist = patientProfileDAO.getOrderDetailListByPatientId(profile.getPatientProfileSeqNo());
            List<Order> listWithoutDuplicates = orderlist.stream().distinct().collect(Collectors.toList());
            for (Order orderr : listWithoutDuplicates) {
                Practices dbPractice = patientProfileDAO.getRegisterdPracticeById(orderr.getPracticeId());
                PracticesDTO practicesDTO = new PracticesDTO();
                if (dbPractice != null) {
                    practicesDTO.setId(dbPractice.getId());
                    practicesDTO.setPracticename(dbPractice.getPracticename());
                    practicesDTO.setPracticeLogo(dbPractice.getPracticeLogo()!= null? PropertiesUtil.getProperty("PHP_WEB_PDF") +dbPractice.getPracticeLogo():"");
                    practicesDTO.setPracticeaddress(dbPractice.getPracticeaddress());
                    practicesDTO.setPracticecity(dbPractice.getPracticecity());
                    practicesDTO.setPracticestate(dbPractice.getPracticestate());
                    ReporterProfile dbreporterProfile = patientProfileDAO.getPhysicianProfileDetialById(orderr.getPracticeId());
                    if (dbreporterProfile != null) {
                        practicesDTO.setPhysicainImage(PropertiesUtil.getProperty("PHP_RX_REPORTER") + dbreporterProfile.getPhysicianImage());
                        List<String> majorSpecialization = new ArrayList<>();
                        List<String> subSpecialization = new ArrayList<>();
                        List<String> languages = new ArrayList<>();
                        majorSpecialization.add(dbreporterProfile.getMajorSpecializationOne() != null ? dbreporterProfile.getMajorSpecializationOne() : "");
                        majorSpecialization.add(dbreporterProfile.getMajorSpecializationTwo() != null ? dbreporterProfile.getMajorSpecializationTwo() : "");
                        practicesDTO.setMajorSpecialization(majorSpecialization);
                        subSpecialization.add(dbreporterProfile.getSubSpecializationOne() != null ? dbreporterProfile.getSubSpecializationOne() : "");
                        subSpecialization.add(dbreporterProfile.getSubSpecializationTwo() != null ? dbreporterProfile.getSubSpecializationTwo() : "");
                        subSpecialization.add(dbreporterProfile.getSubSpecializationThree() != null ? dbreporterProfile.getSubSpecializationThree() : "");
                        practicesDTO.setSubSpecialization(subSpecialization);
                        languages.add(dbreporterProfile.getLanguageOne() != null ? dbreporterProfile.getLanguageOne() : "");
                        languages.add(dbreporterProfile.getLanguageTwo() != null ? dbreporterProfile.getLanguageTwo() : "");
                        languages.add(dbreporterProfile.getLanguageThree() != null ? dbreporterProfile.getLanguageThree() : "");
                        practicesDTO.setPhysicainLanguages(languages);
                    }

                    list.add(practicesDTO);
                }
                }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService# getPracticeDetails", e);
        }
        return list;
    }
//      public List<PracticesDTO> getPracticeDetails () {
//          List<PracticesDTO> list = new ArrayList<>();
//          try {
//              List<Practices> practiceList = patientProfileDAO.getRegisterdPractice();
//              for(Practices dbPractice :practiceList) {
//               PracticesDTO PracticesDTO = new PracticesDTO();
//               PracticesDTO.setId(dbPractice.getId());
//               PracticesDTO.setPracticename(dbPractice.getPracticename());
//               PracticesDTO.setPracticeaddress(dbPractice.getPracticeaddress());
//               PracticesDTO.setPracticecity(dbPractice.getPracticecity());
//               PracticesDTO.setPracticestate(dbPractice.getPracticestate());
//               list.add(PracticesDTO);
//              }
//          } catch (Exception e) {
//              logger.error("PatientProfileService# getPracticeDetails", e);
//          }
//          return list;
//      } 
      public GenralDTO getInfo(String orderId) {
          GenralDTO genralDTO = new  GenralDTO();
          try {
              Order ord = getOrderById(orderId);             
              genralDTO.setServerDate(new Date().getTime());
              Practices practice =  patientProfileDAO.getPracticesById(ord.getPrescriberId());
              if(practice!=null){
                 genralDTO.setPresciberName(practice.getPracticename());
              }else {
                genralDTO.setPresciberName("No Exist");
              }          
          } catch (Exception e) {
              logger.error("PatientProfileService# getInfo", e);
          }
          return genralDTO; 
      }
      public void updateOrderHistory (String orderId,int statusId) {
          try {
              OrderStatusLogs OrderStatusLogs = new  OrderStatusLogs();
              OrderStatusLogs.setOrderId(Long.parseLong(orderId));
              OrderStatusLogs.setStatusId(statusId);
              OrderStatusLogs.setCreatedAt(new Date());
              OrderStatusLogs.setCreatedBy(2);
              patientProfileDAO.save(OrderStatusLogs);
          } catch (Exception e) {
              e.printStackTrace();
                  logger.error("PatientProfileService# updateOrderHistory", e);
          }
      }
          public QuestionAnswer getQuestionAnswerByQuestionId(Long id) {
              QuestionAnswer questionAnswer = new QuestionAnswer();
        try {
               questionAnswer =  patientProfileDAO.getQuestionAnswerByQuestionId(id);
        } catch (Exception e) {
            logger.error("Exception# PatientProfileService -> getQuestionAnswerByQuestionId", e);
        }
        return questionAnswer;
    }
          public Boolean isDeleteSurvey(int patinetId, long surveyId) {
              try {
                   AssignedSurvey survey =  patientProfileDAO.getSurveyLogsById(patinetId, surveyId);
                   if(survey != null) {
                      survey.setDeletedAt(new Date());
                      survey.setUpdatedAt(new Date());
                      patientProfileDAO.update(survey);
                   }
              } catch (Exception e) { 
                    logger.error("Exception# PatientProfileService -> isDeleteSurvey", e); 
              }
              return true;
          }
        public RxRenewal getRxRenewalByOrderId(String orderId) {
         RxRenewal rxRenewal= null;
         try {
             rxRenewal = patientProfileDAO.getRxRenewalByOrderId(orderId);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("patientProfileService-> getRxRenewalByOrderId",e);
                
         }
         return rxRenewal;
     }
    public boolean isRequestCanceled(String orderId) {
        try {
            RxRenewal rxRenewal = patientProfileDAO.getRxRenewalByOrderId(orderId);
            if (rxRenewal != null) {
                if (rxRenewal.getRenewalOrderId() != null) {
                    Order ordRenewl = getOrderById(rxRenewal.getRenewalOrderId());
                    if (ordRenewl != null) {
                        OrderStatus status = patientProfileDAO.getOrderStausById(Constants.ORDER_STATUS.CANCEL_ORDER_ID);
                        ordRenewl.setOrderStatus(status);
                        ordRenewl.setUpdatedAt(new Date());
                        patientProfileDAO.update(ordRenewl);
                        Order orgOrder = getOrderById(rxRenewal.getOrdrId());
                        if (orgOrder != null) {
                            orgOrder.setViewStatus(null);
                            orgOrder.setRefillDone(0);
                            orgOrder.setUpdatedAt(new Date());
                            patientProfileDAO.update(orgOrder);
                        }
                    }
                }
                rxRenewal.setViewStatus("RxRenewal_Canceled");
                rxRenewal.setUpdatedOn(new Date());
                patientProfileDAO.update(rxRenewal);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientProfileService->isOrderCancel: ", e);
            return false;
        }
        return true;
    }
            public AssignedSurveyLogsDTO getLatestSurveyLogByAssignId(int patientId, long assignSurveyId) {
          AssignedSurveyLogsDTO assignedSurveyLogsDTO = new AssignedSurveyLogsDTO();
          try {
//              AssignedSurvey sssignedSurvey = null;
//                Survey2 survey = patientProfileDAO.getSurvyById(assignSurveyId);
               AssignedSurvey  sssignedSurveyy =  patientProfileDAO.getAssignedSurveysById(patientId, assignSurveyId);
              AssignedSurvey   sssignedSurvey = patientProfileDAO.getduplicateAssignId(patientId, sssignedSurveyy.getSurvey().getId());
                assignedSurveyLogsDTO.setId(sssignedSurvey.getId());
          } catch (Exception e) {
          }
          return assignedSurveyLogsDTO;
      }
      public RxRenewal getRxRenewalRequestByOrderId(String orderId) {
         RxRenewal rxRenewal= null;
         try {
             rxRenewal = patientProfileDAO.getRxRenewalRequestByOrderId(orderId);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("patientProfileService-> getRxRenewalByOrderId",e);
                
         }
         return rxRenewal;
     }
    public LowerCostDrugDTO getLowCostDrugDetial(Order ord) {
        LowerCostDrugDTO lowerCostDrugDTO = new LowerCostDrugDTO();

        try {
            DrugDetail2 drugDetail = ord.getDrugDetail2();
            if (drugDetail != null && !CommonUtil.isNullOrEmpty(drugDetail.getDrugId())) {
                 drugDetail = patientProfileDAO.fetchLowCostDrug(drugDetail.getGcnSeq(), drugDetail.getRxLabelName(), drugDetail.getSponsoredProduct(), drugDetail.getDrugId());
//                drugDetail = (DrugDetail2) patientProfileDAO.findRecordById(new DrugDetail2(), drugDetail.getDrugId());
                lowerCostDrugDTO.setOldDrugDetial(fetchLowCostDrugDetial(ord, drugDetail));
                drugDetail = patientProfileDAO.fetchLowCostDrug(drugDetail.getGcnSeq(), drugDetail.getRxLabelName(), drugDetail.getSponsoredProduct());
                if (drugDetail != null) {
                    lowerCostDrugDTO.setLowCostDrugDetial(fetchLowCostDrugDetial(ord, drugDetail));
                } else {
                    lowerCostDrugDTO.setLowCostDrugDetial("Not available any low cost drug");
                }
            }
        } catch (Exception e) {
            logger.error("patientProfileService-> getLowCostDrugDetial", e);
        }
        return lowerCostDrugDTO;
    }
    private OrderDetailDTO fetchLowCostDrugDetial(Order order, DrugDetail2 drugDetail) {
                           OrderDetailDTO newOrder = new OrderDetailDTO();
        try {
                if (drugDetail != null) {
                    newOrder.setDrugId(drugDetail.getDrugId());
                    newOrder.setBrandName(drugDetail.getBrandReference());
                    newOrder.setGenericOrBrand(order.getDrugDetail2().getGenericOrBrand());
                    newOrder.setGenericName(drugDetail.getGenericName());
                    newOrder.setUnitPrice(drugDetail.getUnitPrice());
                    newOrder.setGenericOrBrand(AppUtil.getSafeStr(drugDetail.getGenericOrBrand(), ""));

                    newOrder.setDrugName(drugDetail.getRxLabelName());
                    newOrder.setStrength(order.getStrength() != null ? order.getStrength() : order.getDrugDetail2().getStrength() != null ? order.getDrugDetail2().getStrength().trim() : "");
                    if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.TABLET)) {
                        newOrder.setDrugType("TAB");
                    } else if (AppUtil.getSafeStr(order.getDrugType(), "").equalsIgnoreCase(Constants.CAPSULE)) {
                        newOrder.setDrugType("CAP");
                    } else {
                        newOrder.setDrugType(AppUtil.getSafeStr(order.getDrugType(), "-"));
                    }
                    newOrder.setQty(order.getQty());
                }
        } catch (Exception e) {
            logger.error("Exception#fetchLowCostDrugDetial# ", e);
        }
        return newOrder;
    }
    public List<PracticesDTO> getPhysicianList(int practiceId) {
        List<PracticesDTO> list = new ArrayList<>();
        try {
            List<Object[]> physicanDetialList = patientProfileDAO.getPhysicianList(practiceId);
            if (physicanDetialList != null) {
                for (Object[] phlist : physicanDetialList) {
                    PracticesDTO practicesDTO = new PracticesDTO();
                    practicesDTO.setId(Integer.parseInt(phlist[0].toString()));
                    practicesDTO.setPracticename(phlist[1] != null ? phlist[1].toString(): "");
                    practicesDTO.setPracticeLogo(phlist[2] != null ? PropertiesUtil.getProperty("PHP_WEB_PDF") + phlist[2].toString() : "");
                    practicesDTO.setPhysicianName(phlist[3] != null ? phlist[3].toString(): "");
                    practicesDTO.setPhysicainImage(phlist[4] != null ? PropertiesUtil.getProperty("PHP_RX_REPORTER") + phlist[4].toString() : "");
                    List<String> languages = new ArrayList<>();
                    languages.add(phlist[5] != null ? phlist[5].toString() : "ENHLISH");
                    languages.add(phlist[6] != null ? phlist[6].toString() : "SPAINSH");
                 //   languages.add(phlist[7] != null ? phlist[7].toString() : "ENHLISH");
                    practicesDTO.setPhysicainLanguages(languages);
                    List<String> majorSpecialization = new ArrayList<>();
                    majorSpecialization.add(phlist[8] != null ? phlist[8].toString() : "Dermatology");
                    majorSpecialization.add(phlist[9] != null ? phlist[9].toString() : "Acene");
                    practicesDTO.setMajorSpecialization(majorSpecialization);
                    List<String> subSpecialization = new ArrayList<>();
                    subSpecialization.add(phlist[10] != null ? phlist[10].toString() : "Rosecea");
                  //  subSpecialization.add(phlist[11] != null ? phlist[11].toString() : "Rosecea");
                    practicesDTO.setSubSpecialization(subSpecialization);
                    practicesDTO.setPhysicianId(Integer.parseInt(phlist[12].toString()));
                    list.add(practicesDTO);
                }
            }
        } catch (Exception e) {
            logger.info("", e);
        }
        return list;
    }
        }
