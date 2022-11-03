package com.ssa.cms.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ssa.cms.common.Constants;
import com.ssa.cms.delegate.CMSService;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.delegate.SupportService;
import com.ssa.cms.dto.ActivityHistoryDTO;
import com.ssa.cms.dto.AllergyDto;
import com.ssa.cms.dto.BMIWeeklyDetailRapperDTO;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.BloodPressureDTO;
import com.ssa.cms.dto.PatientGlucoseDTO;
import com.ssa.cms.dto.DeliveryDistanceFeeDTO;
import com.ssa.cms.dto.DrugBrandDTO;
import com.ssa.cms.dto.*;
import com.ssa.cms.enums.ActivitiesEnum;
import com.ssa.cms.enums.EventTitleEnum;
import com.ssa.cms.enums.PlaceholderEnum;
import com.ssa.cms.enums.ResponseTitleEnum;

import com.ssa.cms.model.*;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.CMSPageContent;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.DeliveryPreferences;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.JsonResponse;
import com.ssa.cms.model.NotificationMessages;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderPlaceEmail;
import com.ssa.cms.model.OrderTransferImages;
import com.ssa.cms.model.PatientAllergies;
import com.ssa.cms.model.PatientDeliveryAddress;
import com.ssa.cms.model.PatientInsuranceDetails;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.Question;
import com.ssa.cms.model.RewardPoints;
import com.ssa.cms.model.State;
import com.ssa.cms.model.TransferRequest;
import com.ssa.cms.response.BMIMonthFilterWsMainResponse;
import com.ssa.cms.response.BMIYearFilterWsMainResponse;
import com.ssa.cms.service.*;
import com.ssa.cms.thread.EmialThread;
import com.ssa.cms.thread.SMSAndEmailSendThread;
import com.ssa.cms.util.*;
import com.ssa.cms.validation.APIValidationUtil;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;

/**
 * This web service is used to handle all profile related functionalities i.e.
 * create profile, send verification code ... etc.
 *
 * @author Saber
 */
@RestController
public class PatientWsControllers {

    private static final Log logger = LogFactory.getLog(PatientWsControllers.class.getName());

    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private SetupService setupService;
    @Autowired
    private CMSService cMSService;
    private Pattern pattern;
    private Matcher matcher;
    String[] str = new String[0];
    @Autowired
    ServletContext servletContext;
    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private RefillReminderService refillReminderService;
    @Autowired
    private SupportService supportDelegate;
//    @Autowired
//    private RefillReminderService refillReminderService;

    /**
     * This function is used to validate the phone number, send verification
     * code and create basic profile
     *
     * @param json
     * @return Its return JSON of profile
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/updateProfileWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateProfile(@RequestBody String json) throws IOException, ParseException, Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDTO profile = objectMapper.readValue(json, LoginDTO.class);
        if (profile != null && profile.getPatientProfileSeqNo() != null) {
            String psw = RandomString.generatePassword();
            if (patientProfileService.updateProfileWs(profile, psw) != null) {
                jsonResponse.setErrorCode(1);
//               jsonResponse.setData(patientProfileService.getProfileListWs(profile.getMobileNumber()));
                jsonResponse.setErrorMessage("Profile Updated successfully");
                if (CommonUtil.isNullOrEmpty(profile.getNickName())) {
                    jsonResponse.setErrorMessage("Your nickname has been updated");
                }
                logger.info("Syszzzztem Psw# " + psw + " IsAlreadEnrolled# " + profile.getIsAlreadEnrolled());
                String identity;
                PatientProfile dbProfile = new PatientProfile();
                if (!CommonUtil.isNullOrEmpty(profile.getMobileNumber())) {
                    identity = profile.getMobileNumber();
                    dbProfile = patientProfileService.getPatientProfileByEmailOrPhone(identity);
                } else if (!CommonUtil.isNullOrEmpty(profile.getEmailAddress())) {
                    identity = profile.getEmailAddress();
                    dbProfile = patientProfileService.getPatientProfileByEmailOrPhone(identity);
                }
                if (dbProfile != null) {
                    EnrollementIpad enrollements = patientProfileService.getEnrollementByPAtientId(dbProfile.getPatientProfileSeqNo());
                    if (enrollements.getEnrollemtStatus().equalsIgnoreCase("New")) {
                        patientProfileService.updateOrderStatusOnEnrollementByMobile(dbProfile, enrollements);
                    }
                    if (!Constants.COMPLETED.equalsIgnoreCase(dbProfile.getStatus())) {
                        EmialThread em = new EmialThread(profile.getFirstName(), profile.getEmailAddress(), psw, consumerRegistrationService, logger);
                        Thread t1 = new Thread(em);
                        t1.start();
//                        Thread t1 = new Thread() {
//                            @Override
//                            public void run() {
//                                super.run();
//                                consumerRegistrationService.sendUsernameEmail(profile.getEmailAddress(), profile.getEmailAddress(), Constants.APP_ACCOUNT_CREATED_USERNAME);
//                                consumerRegistrationService.sendPsw(profile.getFirstName(), profile.getEmailAddress(), psw, Constants.APP_ACCOUNT_CREATED_PSW);
//                            }
//                        };
                       

//                    consumerRegistrationService.sendUsernameEmail(profile.getEmailAddress(), profile.getEmailAddress(), Constants.APP_ACCOUNT_CREATED_USERNAME);
//                    consumerRegistrationService.sendPsw(profile.getFirstName(), profile.getEmailAddress(), psw, Constants.APP_ACCOUNT_CREATED_PSW);
                        boolean isSend = patientProfileService.sendNotification(profile.getPatientProfileSeqNo(), Constants.WELCOME);
                        if (isSend == false) {
                            jsonResponse.setErrorCode(0);
                            jsonResponse.setErrorMessage("Welcome Messge Sending Issue..");
                            return objectMapper(objectMapper, jsonResponse);
                        }

                        String secureToken = RedemptionUtil.getRandomToken();
                        Long cardNumber = RedemptionUtil.generateRandomCardNumber(52);
                        logger.info("Secure Token is: " + secureToken);
                        dbProfile.setSecurityToken(secureToken);
                        dbProfile.setStatus(Constants.COMPLETED);
                        dbProfile.setDeviceToken(profile.getFcmToken());
                        if (CommonUtil.isNullOrEmpty(dbProfile.getCardNumber())) {
                            dbProfile.setCardNumber(cardNumber);
                        }

                        dbProfile.setCity(profile.getCity());
                        dbProfile.setZipcode(profile.getZip());
                        State state = new State(profile.getStateId());// (State) patientProfileDAO.findRecordById(new State(), profile.getStateId());
                        if (!CommonUtil.isNullOrEmpty(state.getId())) {
                            dbProfile.setStatee(state);
                        }
                        dbProfile.setProviderAddress(profile.getAddress());
                        PatientDeliveryAddress patientDeliveryAddress = patientProfileService.getPatientDeliveryAddressByPatientId(dbProfile.getPatientProfileSeqNo());
                        patientDeliveryAddress.setCity(profile.getCity());
                        patientDeliveryAddress.setState(state);
                        patientDeliveryAddress.setZip(profile.getZip());
                        patientDeliveryAddress.setAddress(profile.getAddress());
                        patientProfileService.update(patientDeliveryAddress);
                        if (patientProfileService.update(dbProfile)) {
                            jsonResponse.setData(patientProfileService.getProfileListWs(dbProfile.getMobileNumber()));
                        }

                    }
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(messageSource.getMessage("field.saved.error", null, null));
                return objectMapper(objectMapper, jsonResponse);
            }

        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);

    }

    /**
     * This function is used to store questions asked by patients code and
     * create basic profile
     *
     * @param securityToken
     * @param json
     * @return Its return JSON of profile
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/askQuestionWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object questionPlace(
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws IOException, ParseException {
        //public Object getObjectById(Object obj, String id)

        try {
            PatientProfile profile = new PatientProfile();
            JsonResponse jsonResponse = new JsonResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            //validate SecurityToken
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            //Read request param
            BaseDTO baseDTO = objectMapper.readValue(json, BaseDTO.class);
            boolean isSaved = patientProfileService.saveQuestion(baseDTO.getOrderId(), baseDTO.getQuestion(), patientProfile, baseDTO.getNotificationMsgId());

            if (isSaved) {
                isPatientMsgResponse(baseDTO.getNotificationMsgId());
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Question  has been saved successfully.");
                profile.setSuccessOrFailure(Constants.SUCCESS);
                return objectMapper(objectMapper, jsonResponse);

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
                return objectMapper(objectMapper, jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    ////////////////////////////////////////////////////////////////////////////

    /**
     * This function is used to cancel an order if its status is not filled,
     * shipped or delivered code and create basic profile
     *
     * @param securityToken
     * @param orderId
     * @param notificationMsgId
     * @return Its return JSON of profile
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/orderCancelledWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object cancelOrder(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException, ParseException {
        //public Object getObjectById(Object obj, String id)
        try {
            PatientProfile profile = new PatientProfile();
            JsonResponse jsonResponse = new JsonResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
            Order order = (Order) patientProfileService.findOrderById(orderId);

            int isSaved = 0;
            int oderIdSafeInteger = AppUtil.getSafeInt(order.getId(), 0);
            String orderStatusName = order.getOrderStatus().getName();//this.patientProfileService.getOrderStatusName(orderId);
            if (patientProfile.getPatientProfileSeqNo() != null && order.getId() != null && Objects.equals((order.getPatientProfile()).getPatientProfileSeqNo(), patientProfile.getPatientProfileSeqNo())) {
                if (orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.SHIPPED) || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.PICKUP_AT_PHARMACY) || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.DELIVERY) || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.PAYMENT_AUTHORIZED)) {
                    jsonResponse.setErrorMessage("Your Order can't be cancelled now since its status is " + orderStatusName);
                    return objectMapper(objectMapper, jsonResponse);
                }
                if (orderStatusName.equals(Constants.ORDER_STATUS.CANCELLED)) {
                    jsonResponse.setErrorMessage("Your order already has been cancelled. ");
                    return objectMapper(objectMapper, jsonResponse);
                }
//                if (orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.WAITING_PT_RESPONSE)
//                        && order.getRequiresDeletion() == 1) {
//                    jsonResponse.setErrorMessage("Your cancel request has already been submitted. ");
//                    return objectMapper(objectMapper, jsonResponse);
//                }
                // isSaved = consumerRegistrationService.updateOrderStatus(oderIdSafeInteger, 0, 11, "");
                isSaved = consumerRegistrationService.updateOrderStatusByPatient(oderIdSafeInteger, 11);
            }
            if (isSaved == 1) {
                isPatientMsgResponse(notificationMsgId);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Cancel request has been submitted successfully.");
                profile.setSuccessOrFailure(Constants.SUCCESS);
                return objectMapper(objectMapper, jsonResponse);

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
                return objectMapper(objectMapper, jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * This function is used to create patient profile code and create basic
     * profile
     *
     * @param firstName
     * @param lastName
     * @param mobileNumber
     * @param email
     * @param dob
     * @param gender
     * @param osType
     * @param deviceToken
     * @return Its return JSON of profile
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/createProfileWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object createProfile(@RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "mobileNumber", required = false) String mobileNumber,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "dob", required = false) String dob,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "osType", required = false) String osType,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "deviceToken", required = false) String deviceToken) throws IOException, ParseException {
        System.out.println("Mobile Number: " + mobileNumber);
        logger.info("MobileNumber: " + mobileNumber);
        PatientProfile profile = new PatientProfile();
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!validateCreateProfileField(firstName, jsonResponse, lastName, mobileNumber, email, dob, gender)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            mobileNumber = CommonUtil.replaceAllStr(mobileNumber, "-", "");
            PatientProfile patientProfile = patientProfileService.getPatientProfileByPhoneNumber(mobileNumber);
            profile.setFirstName(firstName);
            profile.setLastName(lastName);
            profile.setMobileNumber(mobileNumber);
            profile.setEmailAddress(email);
            profile.setGender(gender);
            profile.setCreatedOn(new Date());
            profile.setUpdatedOn(new Date());
            profile.setOsType(osType);
            profile.setDeviceToken(deviceToken);
            Integer verificationCode = RedemptionUtil.getRandomNumber();
            logger.info("VerificationCode is: " + verificationCode);
            System.out.println("VerificationCode is: " + verificationCode);
            profile.setVerificationCode(verificationCode);
            //validate DOB
            if (!APIValidationUtil.validateDOB(dob, profile, jsonResponse, logger)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            String message = getWelcomeMessage(verificationCode);
            boolean isSaved = saveorupdateprofile(patientProfile, profile);
            //success
            if (isSaved) {
                if (patientProfileService.sendVerificationCode(mobileNumber, message)) {
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(Constants.VERIFICATION_CODE_SENT);
                    profile.setSuccessOrFailure(Constants.SUCCESS);
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage(Constants.INVALID_PHONE_MESSAGE);
                    return objectMapper(objectMapper, jsonResponse);
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
                return objectMapper(objectMapper, jsonResponse);
            }
            jsonResponse.setData(patientProfileService.getProfileListWs(mobileNumber, profile.getVerificationCode()));
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private String getWelcomeMessage(Integer verificationCode) {
        String message = "Your Compliance Reward verification code: " + verificationCode + " to complete your registration.";
        logger.info("Message:: " + message);
        System.out.println("Message:: " + message);
        return message;
    }

    private boolean saveorupdateprofile(PatientProfile patientProfile, PatientProfile profile) {
        boolean isSaved;
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            logger.info("Update Pending Profile: " + patientProfile.getPatientProfileSeqNo());
            System.out.println("Update Pending Profile: " + patientProfile.getPatientProfileSeqNo());
            profile.setPatientProfileSeqNo(patientProfile.getPatientProfileSeqNo());
            patientProfile.setMobileNumber(profile.getMobileNumber());
            patientProfile.setFirstName(profile.getFirstName());
            patientProfile.setLastName(profile.getLastName());
            patientProfile.setVerificationCode(profile.getVerificationCode());
            patientProfile.setEmailAddress(profile.getEmailAddress());
            patientProfile.setDob(profile.getDob());
            patientProfile.setGender(profile.getGender());
            patientProfile.setUpdatedOn(new Date());
            isSaved = patientProfileService.update(patientProfile);
        } else {
            logger.info("save if mobile number not exist: ");
            System.out.println("save if mobile number not exist: ");
            isSaved = patientProfileService.savePatientInfo(profile);
        }
        return isSaved;
    }

    private boolean validateCreateProfileField(String firstName, JsonResponse jsonResponse, String lastName, String mobileNumber, String email, String dob, String gender) {
        if (CommonUtil.isNullOrEmpty(firstName)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_FIRST_NAME);
            return false;
        }
        if (CommonUtil.isNullOrEmpty(lastName)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_LAST_NAME);
            return false;
        }
        if (CommonUtil.isNullOrEmpty(mobileNumber)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_MOBILE_NO);
            return false;
        }
        if (CommonUtil.isNotEmpty(mobileNumber)) {
            mobileNumber = CommonUtil.replaceAllStr(mobileNumber, "-", "");
            if (patientProfileService.isPatientPhoneNumberExist(mobileNumber)) {
                jsonResponse.setData(mobileNumber);
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.PHONE_NUMBER_EXIST);
                return false;
            }
        }
        if (CommonUtil.isNullOrEmpty(email)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.EMAIL_REQ);
            return false;
        }
        if (CommonUtil.isNotEmpty(email)) {
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.INVALID_EMAIL);
                return false;
            }
        }
        if (CommonUtil.isNullOrEmpty(dob)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.DOB_REQ);
            return false;
        }
        if (CommonUtil.isNullOrEmpty(gender)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.GENDER_TYPE_REQ);
            return false;
        }
        return true;
    }

    /**
     * This function is used to verify account
     *
     * @param mobileNumber
     * @param code
     * @param firebaseToken
     * @return Its return JSON of profile
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/accountVerificationWs", produces = "application/json")
    public @ResponseBody
    Object accountVerification(
            @RequestParam(value = "mobileNumber", required = false) String mobileNumber,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "firebaseToken", required = false) String firebaseToken) throws IOException {
        PatientProfileDTO profile = new PatientProfileDTO();
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Verification Code is: " + code + " Phone number is: " + mobileNumber);
        if (CommonUtil.isNullOrEmpty(code)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.VERIFICATION_CODE_REQ);
            return objectMapper(objectMapper, jsonResponse);
        }
        profile.setVerificationCode(Integer.parseInt(code));
        profile.setMobileNumber(mobileNumber);
        if (patientProfileService.isVerificationCodeExist(mobileNumber, AppUtil.getSafeInt(code, 0), firebaseToken)) {
            logger.info("valid code: " + code);
            jsonResponse.setData(profile);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("valid code");
        } else {
            logger.info("Invalid verification code: " + code);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_CODE);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is to used updated profile email on the base of give
     * profile id
     *
     * @param profileId
     * @param email
     * @return Its return JSON of profile
     * @throws java.io.IOException
     *
     * This API is not used
     */
    @RequestMapping(value = "/emailVerificationWs", produces = "application/json")
    public @ResponseBody
    Object emailVerification(
            @RequestParam(value = "profileId", required = false) Integer profileId,
            @RequestParam(value = "email", required = false) String email) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info("Emial Address is: " + email + " Profile Id is: " + profileId);
            if (!validateEmailField(profileId, jsonResponse, email)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            PatientProfile profile = patientProfileService.getPatientProfileById(profileId);
            if (profile != null && profile.getPatientProfileSeqNo() != null) {
                profile.setEmailAddress(email);
                patientProfileService.savePatientInfo(profile);
                profile.setDescription(Constants.UPDATE_MESSAGE);
                profile.setSuccessOrFailure(Constants.SUCCESS);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.UPDATE_MESSAGE);
                setPatientChild(profile);
                jsonResponse.setData(profile);
            } else {
                logger.info("Profile data is null.");
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.NO_RECORD_AGAINST_EMAIL);
                setEmptyData(jsonResponse);
                return objectMapper(objectMapper, jsonResponse);
            }
        } catch (Exception ex) {
            jsonResponse.setData(null);
            logger.error("Exception", ex);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_UPDATE_RECORD);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private void setPatientChild(PatientProfile profile) {
        profile.setNotificationMessagesList(null);
        profile.setOrders(null);
        profile.setPatientDeliveryAddresses(null);
        profile.setPatientGlucoseResultsList(null);
        profile.setYearEndStatementInfoList(null);
        profile.setOrderBatchs(null);
        profile.setMessageResponseses(null);
    }

    private boolean validateEmailField(Integer profileId, JsonResponse jsonResponse, String email) {
        if (CommonUtil.isNullOrEmpty(profileId)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.PROFILE_ID_REQ);
            return false;
        }
        if (CommonUtil.isNullOrEmpty(email)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.EMAIL_REQ);
            return false;
        }
        if (!email.trim().isEmpty()) {
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.INVALID_EMAIL);
                return false;
            }
        }
        return true;
    }

    /**
     * This function is used to verify 18+ age and updated profile if age is
     * validated. It will return success when age updated successfully in
     * profile otherwise failure.
     *
     * @param profileId
     * @param dob
     * @param gender
     * @param allergies
     * @return Its return JSON of profile
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/ageVerificationWs", produces = "application/json")
    public @ResponseBody
    Object ageVerification(
            @RequestParam(value = "profileId", required = false) Integer profileId,
            @RequestParam(value = "dob", required = false) String dob,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "allergies", required = false) String allergies) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info("Date of Birth is : " + dob + " Profile id is :" + profileId + " Gender type is: " + gender + " Allergies: " + allergies);
            if (!validateAgeVerification(profileId, jsonResponse, dob, gender)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            PatientProfile patientProfile = patientProfileService.getPatientProfileById(profileId);
            if (!isProfileInfoEmpty(patientProfile, jsonResponse)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            patientProfile.setGender(gender);
            if (allergies != null && Constants.UNDEFINED.equals(allergies)) {
                allergies = null;
            }
            patientProfile.setAllergies(allergies);
            //validate DOB
            if (!APIValidationUtil.validateDOB(dob, patientProfile, jsonResponse, logger)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            PatientProfile profile = patientProfile;
            patientProfileService.savePatientInfo(patientProfile);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.UPDATE_MESSAGE);
            setPatientChild(profile);
            jsonResponse.setData(profile);
        } catch (IOException | ParseException ex) {
            logger.error("Exception", ex);
            jsonResponse.setData(null);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_UPDATE_RECORD);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private boolean isProfileInfoEmpty(PatientProfile profile, JsonResponse jsonResponse) throws IOException {
        if (profile == null || profile.getPatientProfileSeqNo() == null) {
            logger.info("Profile info is null.");
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.PROFILE_ID_REQ);
            setEmptyData(jsonResponse);
            return false;
        }
        return true;
    }

    private boolean validateAgeVerification(Integer profileId, JsonResponse jsonResponse, String dob, String gender) {
        boolean valid = true;
        if (CommonUtil.isNullOrEmpty(profileId)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.PROFILE_ID_REQ);
            valid = false;
        }
        if (CommonUtil.isNullOrEmpty(dob)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.DOB_REQ);
            valid = false;
        }
        if (CommonUtil.isNullOrEmpty(gender)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.GENDER_TYPE_REQ);
            valid = false;
        }
        return valid;
    }

    private boolean validateBillingAddressField(String addressLine, JsonResponse jsonResponse, String appt, String city, String stateId, String zip) {
        if (CommonUtil.isNullOrEmpty(addressLine)) {
            jsonResponse.setErrorMessage("Address is required");
            jsonResponse.setErrorCode(0);
            return false;
        }

//        if (CommonUtil.isNullOrEmpty(city)) {
//            jsonResponse.setErrorMessage("City is required");
//            jsonResponse.setErrorCode(0);
//            return false;
//        }
        if (CommonUtil.isNullOrEmpty(stateId)) {
            jsonResponse.setErrorMessage("State is required");
            jsonResponse.setErrorCode(0);
            return false;
        }
        if (CommonUtil.isNullOrEmpty(zip)) {
            jsonResponse.setErrorMessage("Zip code is required");
            jsonResponse.setErrorCode(0);
            return false;
        }
        return true;
    }

    private boolean validateDoDirectPayment(String cardType, String cardNumber, String expiry, String cardHolderName, String cvv, JsonResponse jsonResponse) throws IOException {
        if (!validCardTypeLength(cardType, cvv, jsonResponse)) {
            return false;
        }
//            //validating...
        DoDirectPayment doDirectPayment = new DoDirectPayment();
        DoDirectPaymentResponseType response = doDirectPayment.authorizationRequest(cardType, cardNumber, expiry, cardHolderName, cvv);
        if (response == null) {
            System.out.println("RESPONSE IS NULL");
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Connection timeout while authorizing payment info");
            return false;
        }
        logger.info("DoDirectPaymentResponseType: " + response);
        System.out.println("DoDirectPaymentResponseType: " + response + " STRING VLAUE " + response.getAck().getValue());
        if (!(response.getAck().getValue().equalsIgnoreCase(Constants.SUCCESS) || response.getAck().getValue().equalsIgnoreCase(Constants.SUCCESS_WITH_WARNING))) {
            if (response.getErrors() != null) {
                List<ErrorType> errorList = response.getErrors();
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(errorList.get(0).getLongMessage());
                logger.info("DoDirectPaymentResponseType error: " + errorList.get(0).getLongMessage());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Error while authorizing payment info.");
                logger.info("Error while authorizing payment info.");
            }
            return false;
        }
        return true;
    }

    private boolean validCardTypeLength(String cardType, String cvv, JsonResponse jsonResponse) {
        if (cardType != null && cvv != null) {
            logger.info("Cvv number length is: " + cvv.length());
            if ((cardType.equalsIgnoreCase(Constants.VISA) || cardType.equalsIgnoreCase(Constants.MASTERCARD) || cardType.equalsIgnoreCase(Constants.DISCOVER))) {
                if (cvv.length() < 3 || cvv.length() > 3) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage(Constants.INVALID_CVVNO_VMD);
                    logger.info("Please enter valid 3 digit CVV code against this card " + cardType);
                    return false;
                }
            } else if (cardType.equalsIgnoreCase(Constants.AMEX) && (cvv.length() < 4 || cvv.length() > 4)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.INVALID_CVVNO_AMEX);
                logger.info("Please enter valid 4 digit CVV code against this card " + cardType);
                return false;
            }
        }
        return true;
    }

    private boolean validatePaymentInfo(Integer profileId, JsonResponse jsonResponse, String cardHolderName, String cardNumber, String cvv, String expiry, String cardType, String token) {
        if (CommonUtil.isNullOrEmpty(cardHolderName)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("CardHolderName is required.");
            return false;
        }
        if (CommonUtil.isNullOrEmpty(cardNumber)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("CardNumber is required.");
            return false;
        }
        if (CommonUtil.isNullOrEmpty(cvv)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("CVV is required.");
            return false;
        }
        if (CommonUtil.isNullOrEmpty(expiry)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Expiry date is required.");
            return false;
        }
        if (CommonUtil.isNullOrEmpty(cardType)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("CardType is required.");
            return false;
        }
        return true;
    }

    /**
     * This function is used to get list of all delivery preferences.
     *
     * @return list of delivery preferences in JSON format
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/getDeliveryPrefrencesWs", produces = "application/json")
    public @ResponseBody
    Object getDeliveryPrefrences() throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        if (patientProfileService.getDeliveryPreferences().size() > 0) {
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            jsonResponse.setData(patientProfileService.getDeliveryPreferences());
        } else {
            jsonResponse.setData(null);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is no record in DeliveryPrefrences table.");
            logger.info("Empty DeliveryPrefrences list::getDeliveryPrefrences()");
        }
        return objectMapper(mapper, jsonResponse);
    }

    /**
     * This function is used to save insuranceCardInfo
     *
     * @param profileId
     * @param cardHolderRelation
     * @param insuranceCardFront
     * @param insuranceCardBack
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/insuranceCardInfoWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object insuranceCardInfo(@RequestParam(value = "profileId", required = false) Integer profileId,
            @RequestParam(value = "cardHolderRelation", required = false) String cardHolderRelation,
            @RequestParam(value = "insuranceFrontCardPath", required = false) String insuranceCardFront,
            @RequestParam(value = "insuranceBackCardPath", required = false) String insuranceCardBack, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PatientProfile patientProfile = new PatientProfile();
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("ProfileId: " + profileId + " CardHolderRelation: " + cardHolderRelation + " insuranceCardFront: " + insuranceCardFront.length() + " insuranceCardBack: " + insuranceCardBack.length());
            if (!validateCreditCardInfo(cardHolderRelation, jsonResponse, insuranceCardFront, insuranceCardBack)) {
                return jsonResponse;
            }
            byte[] decodedFrontCard = org.apache.commons.codec.binary.Base64.decodeBase64(insuranceCardFront);
            String imgFcPath = saveInsuranceCard(decodedFrontCard, profileId, "InsuranceFrontCard");
            logger.info("insuranceCardFront Path: " + imgFcPath);
            if (!CommonUtil.urlAuthorization(imgFcPath)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Error in saving card please try again.");
                return objectMapper(mapper, jsonResponse);
            }
            String imgBcPath = "";
            if (CommonUtil.isNotEmpty(insuranceCardBack)) {
                byte[] decodedBackCard = org.apache.commons.codec.binary.Base64.decodeBase64(insuranceCardBack);
                imgBcPath = saveInsuranceCard(decodedBackCard, profileId, "InsuranceBackCard");
                logger.info("decodedBackCard: " + imgBcPath);
            }
            patientProfile.setPatientProfileSeqNo(profileId);
            patientProfile.setCardHolderRelation(cardHolderRelation);
            //patientProfile.setInsuranceCardFront(decodedFrontCard);
            //patientProfile.setInsuranceCardBack(decodedBackCard);
            String securityToken = RedemptionUtil.getRandomToken();
            logger.info("Token is: " + securityToken);
            patientProfile.setSecurityToken(securityToken);
            patientProfile.setInsuranceFrontCardPath(imgFcPath);
            patientProfile.setInsuranceBackCardPath(imgBcPath);
            if (patientProfileService.savePatientInsuranceCardInfo(patientProfile)) {
                RewardPoints rewardPoints = patientProfileService.getRewardPoints(Constants.Reward_Points_Id);
                if (rewardPoints != null) {
                    //setRewardPoints(rewardPoints, profileId);
                    RewardPoints rp = patientProfileService.getMyRewardsPoints(profileId);
                    logger.info("RewardPoints: " + rewardPoints.getPoint() + " save reward history." + " Points are: " + rewardPoints.getPoint());
                    Integer points = rewardPoints.getPoint().intValueExact();
                    logger.info("Points with out decimal is: " + points);
                    rewardPoints.setPoints(rp.getLifeTimePoints().intValue());
                    rewardPoints.setSecurityToken(securityToken);
                    rewardPoints.setInsuranceFrontCardPath(imgFcPath);
                    rewardPoints.setInsuranceBackCardPath(imgBcPath);
                    jsonResponse.setData(rewardPoints);
                }
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            logger.error("Exception -> insuranceCardInfo", e);
        }
        return jsonResponse;
    }

    private void setRewardPoints(RewardPoints rewardPoints, Integer profileId) {
        patientProfileService.saveRewardHistory(rewardPoints.getType(), rewardPoints.getPoint().intValueExact(), profileId, Constants.PLUS);
        CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs(Constants.WELCOME, Constants.EVENTNAME);
        if (campaignMessages != null) {
            String messageText = TextFlowUtil.setMessagePlaceHolder(campaignMessages.getSmstext(), rewardPoints.getPoint().intValueExact());
            logger.info("Message Text is: " + messageText);
            campaignMessages.setSmstext(messageText);
            if (patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, profileId)) {
                logger.info("Notification messages sent.");
                patientProfileService.sendNextMessages(campaignMessages, profileId);
            } else {
                logger.info("Notification messages cannot sent.");
            }
        } else {
            logger.info("CampaignMessages cannot found.");
        }
    }

    private String saveInsuranceCard(byte[] decodedBackCard, Integer profileId, String cardName) throws IOException {
        String imageFormat = FileUtil.determineImageFormat(decodedBackCard);
        logger.info("imageFormat: " + imageFormat + " Card Name is: " + cardName);
        File file;
        file = setFilePath(profileId, imageFormat, cardName);
        logger.info("Complete Image Path: " + file.getPath());
        patientProfileService.saveImageOrVideo(decodedBackCard, file, imageFormat, Constants.COMMAND);
        String imageUrl = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + cardName + profileId + "." + imageFormat;
        logger.info("Image Url:: " + imageUrl);
        try {
            Date date = new Date();
            String dateStr = DateUtil.dateToString(date, Constants.DATE_FORMATE);
            dateStr = dateStr.replace(":", "-");
            dateStr = dateStr.replace(" ", "-");
            dateStr = dateStr.replace("_", ":");
            imageUrl = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + cardName + profileId + "-" + dateStr + "." + imageFormat;
            logger.info("-------------- IMAGE URL2 -------------- " + imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: saveInsuranceCard", e);
        }
        return imageUrl;
    }

    private boolean validateCreditCardInfo(String cardHolderRelation, JsonResponse jsonResponse, String insuranceCardFront, String insuranceCardBack) throws IOException {
        if (CommonUtil.isNullOrEmpty(cardHolderRelation)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Cardholder relation required.");
            return false;
        }
        if (CommonUtil.isNullOrEmpty(insuranceCardFront)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("insurance card front side required.");
            return false;
        }
//        if (insuranceCardBack == null || insuranceCardBack.isEmpty() || Constants.UNDEFINED.equals(insuranceCardBack)) {
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage("insurance card back side required.");
//            return false;
//        }
        return true;
    }

    /**
     * This function is used to get list of all states of US.
     *
     * @return list of states in JSON format
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/getStatesWs", produces = "application/json")
    public @ResponseBody
    Object getStates() throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        List<State> list = patientProfileService.getStates();
        ObjectMapper mapper = new ObjectMapper();
        if (list.size() > 0) {
            jsonResponse.setData(list);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Empty state list.");
        }
        return objectMapper(mapper, jsonResponse);
    }

    /**
     * This function is used to get page contents
     *
     * @param type
     * @return page contents
     * @throws IOException
     */
    @RequestMapping(value = "/getContentsWs", produces = "application/json")
    public @ResponseBody
    Object getPageContents(@RequestParam(value = "type", required = false) String type) throws IOException {
        CMSPageContent pageContent = new CMSPageContent();
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Page Type is: " + type);
        if (type == null || type.trim().isEmpty()) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Page type required.");
            return objectMapper(mapper, jsonResponse);
        }
        if (type.equalsIgnoreCase("TermsOfUse")) {
            pageContent = cMSService.getCMSPageContentById(1);
        }
        if (type.equalsIgnoreCase("PrivacyPolicy")) {
            pageContent = cMSService.getCMSPageContentById(2);
        }
        if (type.equalsIgnoreCase("WhyAllergyDetailsRequired")) {
            pageContent = cMSService.getCMSPageContentById(3);
            // String btnLink = "<div style='text-align: center; padding-top: 10px;'><a href='inapp://capture'><button style='background: rgb(35, 104, 178) none repeat scroll 0px 0px; border: 0px none; height: 33px; width: 100px; color: white;'>Click me</button></a></div>";
            // pageContent.setContent(pageContent.getContent().replace("[URL]", btnLink));
        }
        if (type.equalsIgnoreCase("LearnAboutTransferRx")) {
            pageContent = cMSService.getCMSPageContentById(4);
        }
        if (type.equalsIgnoreCase("HowEarnPoints")) {
            pageContent = cMSService.getCMSPageContentById(5);
        }
        if (type.equalsIgnoreCase("HowSpendPoints")) {
            pageContent = cMSService.getCMSPageContentById(6);
        }
        if (type.equalsIgnoreCase("ContactUs")) {
            pageContent = cMSService.getCMSPageContentById(7);
        }
        jsonResponse.setData(pageContent);
        jsonResponse.setErrorCode(1);
        jsonResponse.setErrorMessage(Constants.SUCCESS);
        return objectMapper(mapper, jsonResponse);
    }

    /**
     * This function is used to resend verification code to patient
     *
     * @param mobileNumber
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "reSendVerificationCodeWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object resendVerificationCode(@RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws IOException {
        logger.info("Phone number is: " + mobileNumber);
        PatientProfile patientProfile = new PatientProfile();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            if (mobileNumber == null || mobileNumber.trim().isEmpty() || "undefined".equals(mobileNumber)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Mobile number required.");
                return objectMapper(objectMapper, jsonResponse);
            }
            mobileNumber = CommonUtil.replaceAllStr(mobileNumber, "-", "");
            int verificationCode = RedemptionUtil.getRandomNumber();
            logger.info("VerificationCode is: " + verificationCode);
            patientProfile.setVerificationCode(verificationCode);
            String message = getWelcomeMessage(verificationCode);
            if (patientProfileService.sendVerificationCode(mobileNumber, message)) {
                if (patientProfileService.updateVerificationCode(verificationCode, mobileNumber)) {
                    patientProfile.setDescription(Constants.RESEND_VERIFICATIONCODE_MESSAGE);
                    jsonResponse.setData(patientProfile);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(Constants.VERIFICATION_CODE_SENT);
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("This phone# is not registered.");
                    return objectMapper(objectMapper, jsonResponse);
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("This phone# is not registered.");
                return objectMapper(objectMapper, jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            return objectMapper(objectMapper, jsonResponse);

        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to send verification code.
     *
     * @param mobileNumber
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/verifyMobileNoWs", produces = "application/json")
    public @ResponseBody
    Object sendVerificationCode(@RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws IOException {
        logger.info("mobileNumber is #" + mobileNumber);
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        PatientProfile patientProfile = new PatientProfile();
        if (mobileNumber == null || mobileNumber.trim().isEmpty() || Constants.UNDEFINED.equals(mobileNumber)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_MOBILE_NO);
            return objectMapper(mapper, jsonResponse);
        }
        mobileNumber = CommonUtil.replaceAllStr(mobileNumber, "-", "");
        if (patientProfileService.isPatientPhoneNumberExist(mobileNumber)) {
            int verificationCode = RedemptionUtil.getRandomNumber();
            logger.info("verificationCode: " + verificationCode);
            String message = getWelcomeMessage(verificationCode);
            try {
                if (patientProfileService.sendVerificationCode(mobileNumber, message)) {
                    if (patientProfileService.updateVerificationCode(verificationCode, mobileNumber)) {
                        patientProfile.setMobileNumber(mobileNumber);
                        patientProfile.setVerificationCode(verificationCode);
                        jsonResponse.setData(patientProfile);
                        jsonResponse.setErrorCode(1);
                        jsonResponse.setErrorMessage(Constants.VERIFICATION_CODE_SENT);
                    } else {
                        jsonResponse.setErrorCode(0);
                        jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
                        return objectMapper(mapper, jsonResponse);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(PatientWsControllers.class.getName()).log(Level.SEVERE, null, ex);
                jsonResponse.setErrorCode(2);
                jsonResponse.setErrorMessage("Phone # is not registered.");
            }
        } else {
            jsonResponse.setErrorCode(2);
            jsonResponse.setErrorMessage("Phone # is not registered.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    /**
     * This function is used to login to the system from mobile device
     *
     * @param jsonRequest
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/loginWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object login(@RequestBody String jsonRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode userInputNode = rootNode.path("UserIdentity");
            String userInput = userInputNode.asText();
            JsonNode fcmNode = rootNode.path("fcmId");
            String fcmId = fcmNode.asText();
            logger.info("userInput#" + userInput);
            if (CommonUtil.isNullOrEmpty(userInput)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Enter Phone#/Email address");
                return objectMapper(objectMapper, jsonResponse);
            }
            PatientProfile dbProfile = null;
            if (CommonUtil.isPhoneValid(userInput)) {
                dbProfile = patientProfileService.getPatientInfoByEmailOrMobileNo(userInput, "");
            } else {
                if (!validateEmail(userInput, jsonResponse)) {
                    return objectMapper(objectMapper, jsonResponse);
                }
                dbProfile = patientProfileService.getPatientInfoByEmailOrMobileNo("", userInput);
            }

            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(5);
                jsonResponse.setErrorMessage(messageSource.getMessage("error.record.found", null, null));
                return objectMapper(objectMapper, jsonResponse);
            }
            String secureToken = RedemptionUtil.getRandomToken();
            logger.info("Secure Token is: " + secureToken);
            dbProfile.setSecurityToken(secureToken);
            dbProfile.setStatus(Constants.COMPLETED);
            dbProfile.setDeviceToken(fcmId);
            if (patientProfileService.update(dbProfile)) {
                jsonResponse.setData(patientProfileService.getProfileListWs(dbProfile.getMobileNumber()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("valid code");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Phone# or Email address");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("ERROR: " + e.getMessage());
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    ////////////////////////////////////////////////////////////////////////////////
    /**
     * This function is used to login to the system from mobile device
     *
     * @param securityToken
     * @param mobileNumber
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/loginTokenBasedWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object loginTokenBased(@RequestHeader(value = "securityToken") String securityToken,
            @RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        PatientProfile patientProfile = new PatientProfile();
        if (!validateLoginField(mobileNumber, jsonResponse, mapper, 0)) {
            return objectMapper(mapper, jsonResponse);
        }
//        String secureToken = RedemptionUtil.getRandomToken();
        System.out.println("Parameter Token is: " + securityToken);
//        if (patientProfileService.isVerificationCodeExist(mobileNumber, verificationCode,firebaseToken,osType)) {
        PatientProfile dbProfile = patientProfileService.getPatientProfileByMobileNumber(mobileNumber);
        System.out.println("DB TOKEN " + AppUtil.getSafeStr(dbProfile.getSecurityToken(), ""));
        if (!AppUtil.getSafeStr(dbProfile.getSecurityToken(), "").equals(securityToken)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_CODE);
        } else {
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(patientProfileService.getProfileListWs(mobileNumber));
            jsonResponse.setErrorMessage("valid code");
        }
//            dbProfile.setSecurityToken(securityToken);
//            if (patientProfileService.update(dbProfile)) {
//                patientProfile.setId(dbProfile.getId());
//                patientProfile.setSecurityToken(securityToken);
//                patientProfile.setMobileNumber(mobileNumber);
//                jsonResponse.setData(patientProfileService.getProfileListWs(mobileNumber, verificationCode));
//                jsonResponse.setErrorCode(1);
//                jsonResponse.setErrorMessage("valid code");
//            } else {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage(Constants.ERROR_UPDATE_RECORD);
//            }

//        } else {
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage(Constants.INVALID_CODE);
//        }
        return objectMapper(mapper, jsonResponse);
    }

    ////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/logoutWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object logout(@RequestHeader(value = "securityToken") String securityToken,
            @RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        PatientProfile patientProfile = new PatientProfile();
        try {
            patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                boolean isSaved = this.patientProfileService.clearDeviceTokenFromProfile(patientProfile);
                if (isSaved) {
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("Loggedout successfully.");

                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Error occurred while logging out.");
                    return objectMapper(objectMapper, jsonResponse);
                }
            } else {
                CommonUtil.inValidSecurityToken(jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(null);
            logger.error("Exception", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
        }
        return objectMapper(objectMapper, jsonResponse);
    }
    ////////////////////////////////////////////////////////////////////////////////

    private boolean validateLoginField(String mobileNumber, JsonResponse jsonResponse, ObjectMapper mapper, Integer verificationCode) throws IOException {
        if (mobileNumber == null || mobileNumber.trim().isEmpty() || "undefined".equals(mobileNumber)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Mobile number is required.");
            return false;
        }

        if (verificationCode == null) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Verification code is required.");
            return false;
        }
        if (!patientProfileService.isPatientPhoneNumberExist(mobileNumber)) {
            logger.info("Phone # is not exist. " + mobileNumber);
            jsonResponse.setErrorCode(2);
            jsonResponse.setErrorMessage("Phone # does not exist.");
            return false;
        }
        return true;
    }

    /**
     * This function is used to get patient profile
     *
     * @param profileId
     * @return patient profile
     * @throws IOException
     */
    @RequestMapping(value = "/getProfileWs", produces = "application/json")
    public @ResponseBody
    Object getPatientProfile(@RequestParam(value = "profileId", required = false) Integer profileId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        jsonResponse.setData(patientProfileService.getPatientProfileDataById(profileId));
        jsonResponse.setErrorCode(1);
        objectMapper.setVisibilityChecker(objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to update Allergies (add, update)
     *
     * @param securityToken
     * @param allergies
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateAllergiesWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateAllergies(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "allergies", required = false) String allergies) throws IOException {
        logger.info("SecurityToken is : " + securityToken + " Allergies is: " + allergies);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        //validate SecurityToken
        boolean isUpdate = false;
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            if (allergies != null && Constants.UNDEFINED.equals(allergies)) {
                allergies = "";
            }
            PatientAllergies allergy = patientProfileService.getAllergyIfExist(patientProfile.getPatientProfileSeqNo(), allergies);
            if (allergy == null) {
                isUpdate = patientProfileService.updateAllergies(securityToken, allergies);
            } else {
                jsonResponse.setErrorCode(0);
                //jsonResponse.setErrorMessage("Allergy already exists.");
                jsonResponse.setErrorMessage("Duplicate allergy entry detected; only one will be saved");
                return objectMapper(objectMapper, jsonResponse);
            }
            if (isUpdate == true) {
                List<PatientAllergies> allergylist = patientProfileService.getAllergyList(patientProfile.getPatientProfileSeqNo());
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(allergylist);
                jsonResponse.setTotalRecords(allergylist.size());
                jsonResponse.setErrorMessage("Your allergy profile is updated");
            } else {
                jsonResponse.setErrorCode(0);
                //jsonResponse.setErrorMessage("There is problem to update record.");
                jsonResponse.setErrorMessage("Allergy entry is not recognized. Entry will not be saved");
                return objectMapper(objectMapper, jsonResponse);
            }

        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
            return objectMapper(objectMapper, jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to update delivery address
     *
     * @param securityToken
     * @param id
     * @param address
     * @param apt
     * @param city
     * @param stateId
     * @param zip
     * @param description
     * @param addressType
     * @param defaultAddress
     * @param dprefId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateDeliveryAddressWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateDeliveryAddressWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "apartment", required = false) String apt,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "stateId", required = false) String stateId,
            @RequestParam(value = "zip", required = false) String zip,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "addressType", required = false) String addressType,
            @RequestParam(value = "defaultAddress", required = false) String defaultAddress,
            @RequestParam(value = "dprefaId", required = false) Integer dprefId,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("SecurityToken is :" + securityToken + " Delivery Address id is: " + id + " Default Address as: " + defaultAddress + " DeliveryPreferenceId is: " + dprefId);
        //validate SecurityToken
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (!isValidZipCode(zip, patientProfile, jsonResponse)) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (defaultAddress == null || defaultAddress.isEmpty() || defaultAddress.equalsIgnoreCase("No")) {
            boolean isExist = patientProfileService.isDefaultPatientDeliveryAddress(patientProfile.getPatientProfileSeqNo());
            PatientDeliveryAddressDTO deliveryAddressDTO = patientProfileService.getPatientDeliveryAddressById(patientProfile.getPatientProfileSeqNo(), id);
            if ((deliveryAddressDTO != null && deliveryAddressDTO.getDefaultAddress().equalsIgnoreCase("Yes")) || (!isExist)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("At least one record should set as default.");
                return objectMapper(objectMapper, jsonResponse);
            }
        }
        if (patientProfileService.updateDeliveryAddressWs(patientProfile, id, address, apt, city, stateId, zip, description, addressType, defaultAddress, dprefId)) {
            isPatientMsgResponse(notificationMsgId);
            jsonResponse.setData(patientProfileService.getPatientDeliveryAddressById(patientProfile.getPatientProfileSeqNo(), id));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("Record has been updated successfully.");
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to update insurance card.
     *
     * @param securityToken
     * @param cardHolderRelation
     * @param insuranceCardFront
     * @param insuranceCardBack
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateInsuranceCardWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateInsuranceCardWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "cardHolderRelation", required = false) String cardHolderRelation,
            @RequestParam(value = "insuranceFrontCardPath", required = false) String insuranceCardFront,
            @RequestParam(value = "insuranceBackCardPath", required = false) String insuranceCardBack) throws IOException {
        logger.info("SecurityToken: " + securityToken + " CardHolderRelation: " + cardHolderRelation + " insuranceCardFront: " + insuranceCardFront.length() + " insuranceCardBack: " + insuranceCardBack.length());
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile profile = new PatientProfile();
        //validate SecurityToken
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }

        byte[] decodedFrontCard = null, decodedBackCard = null;
        String imgFcPath = null, imgBcPath = null;
        if (!insuranceCardFront.contains(Constants.APP_PATH)) {
            decodedFrontCard = org.apache.commons.codec.binary.Base64.decodeBase64(insuranceCardFront.getBytes());
            imgFcPath = saveInsuranceCard(decodedFrontCard, patientProfile.getPatientProfileSeqNo(), "InsuranceFrontCard");
            logger.info("decodedFrontCard Path: " + imgFcPath);
        } else {
            logger.info("update exist decodedFrontCard Path: " + imgFcPath);
            imgFcPath = insuranceCardFront;
            //decodedFrontCard = patientProfile.getInsuranceCardFront();
        }
        if (!CommonUtil.urlAuthorization(imgFcPath)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Error in saving card please try again.");
            return jsonResponse;
        }
        if (CommonUtil.isNotEmpty(insuranceCardBack)) {
            if (!insuranceCardBack.contains(Constants.APP_PATH)) {
                decodedBackCard = org.apache.commons.codec.binary.Base64.decodeBase64(insuranceCardBack.getBytes());
                imgBcPath = saveInsuranceCard(decodedBackCard, patientProfile.getPatientProfileSeqNo(), "InsuranceBackCard");
                logger.info("decodedBackCard path: " + imgBcPath);
            } else {
                logger.info("update exist decodedBackCard path: " + imgBcPath);
                imgBcPath = insuranceCardBack;
                //  decodedBackCard = patientProfile.getInsuranceCardBack();
            }
        }
        if (patientProfileService.updateInsuranceCardWs(securityToken, cardHolderRelation, decodedFrontCard, decodedBackCard, imgFcPath, imgBcPath)) {
            logger.info("Record has been updated successfully.");
            profile.setCardHolderRelation(cardHolderRelation);
            profile.setInsuranceFrontCardPath(imgFcPath);
            profile.setInsuranceBackCardPath(imgBcPath);
            jsonResponse.setData(profile);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("Record has been updated successfully.");
        } else {
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
        }
        return jsonResponse;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This function is used to add new Insurance card
     *
     * @param securityToken
     * @param cardHolderRelation
     * @param memberId
     * @param cardId
     * @param insuranceProvider
     * @param groupNumber
     * @param planId
     * @param providerPhone
     * @param providerAddress
     * @param expiryDate
     * @param createdOn
     * @param updatedOn
     * @param isPramiry
     * @param effectiveDate
     * @param insuranceCardFront
     * @param insuranceCardBack
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addInsuranceCardWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addInsuranceCardWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "cardHolderRelation", required = false) String cardHolderRelation,
            @RequestParam(value = "memberId", required = false) String memberId,
            @RequestParam(value = "cardId", required = false) Long cardId,
            @RequestParam(value = "insuranceProvider", required = false) String insuranceProvider,
            @RequestParam(value = "groupNumber", required = false) String groupNumber,
            @RequestParam(value = "planId", required = false) String planId,
            @RequestParam(value = "providerPhone", required = false) String providerPhone,
            @RequestParam(value = "providerAddress", required = false) String providerAddress,
            @RequestParam(value = "expiryDate", required = false) String expiryDate,
            @RequestParam(value = "createdOn", required = false) String createdOn,
            @RequestParam(value = "updatedOn", required = false) String updatedOn,
            @RequestParam(value = "isPramiry", required = false) Integer isPramiry,
            @RequestParam(value = "effectiveDate", required = false) String effectiveDate,
            @RequestParam(value = "insuranceFrontCardPath", required = false) String insuranceCardFront,
            @RequestParam(value = "insuranceBackCardPath", required = false) String insuranceCardBack) throws IOException {
        logger.info("SecurityToken: " + securityToken + " CardHolderRelation: " + cardHolderRelation + " insuranceCardFront: " + insuranceCardFront.length() + " insuranceCardBack: " + insuranceCardBack.length());
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientInsuranceDetails insuranceDetails = new PatientInsuranceDetails();

        try {
            //validate SecurityToken
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            String[] eDate = effectiveDate.split("/");
            effectiveDate = eDate[1] + "-" + eDate[0] + "-01";
            //Long longCardId = Long.parseLong(cardId);
            byte[] decodedFrontCard = null, decodedBackCard = null;
            String imgFcPath = null, imgBcPath = null;
            if (!insuranceCardFront.contains(PropertiesUtil.getProperty("INSURANCE_CARD_URL"))) {
                decodedFrontCard = org.apache.commons.codec.binary.Base64.decodeBase64(insuranceCardFront.getBytes());
                imgFcPath = saveInsuranceCard(decodedFrontCard, patientProfile.getPatientProfileSeqNo(), "InsuranceFrontCard");
                logger.info("decodedFrontCard Path: " + imgFcPath);
            } else {
                logger.info("update exist decodedFrontCard Path: " + imgFcPath);
                imgFcPath = insuranceCardFront;
            }
            if (!CommonUtil.urlAuthorization(imgFcPath)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Error in saving card please try again.");
                return jsonResponse;
            }
            if (CommonUtil.isNotEmpty(insuranceCardBack)) {
                if (!insuranceCardBack.contains(PropertiesUtil.getProperty("INSURANCE_CARD_URL"))) {
                    decodedBackCard = org.apache.commons.codec.binary.Base64.decodeBase64(insuranceCardBack.getBytes());
                    imgBcPath = saveInsuranceCard(decodedBackCard, patientProfile.getPatientProfileSeqNo(), "InsuranceBackCard");
                    logger.info("decodedBackCard path: " + imgBcPath);
                } else {
                    logger.info("update exist decodedBackCard path: " + imgBcPath);
                    imgBcPath = insuranceCardBack;
                }
            }
            if (patientProfileService.addInsuranceCard(patientProfile, cardHolderRelation,
                    decodedFrontCard, decodedBackCard, imgFcPath, imgBcPath, memberId, cardId, planId,
                    effectiveDate, expiryDate, createdOn, updatedOn, insuranceProvider,
                    groupNumber, providerPhone, providerAddress, isPramiry, 0, jsonResponse)) {
                logger.info("Record has been added successfully.");
                insuranceDetails.setCardHolderRelationship(cardHolderRelation);
                insuranceDetails.setInsuranceFrontCardPath(imgFcPath);
                insuranceDetails.setInsuranceBackCardPath(imgBcPath);
                jsonResponse.setData(insuranceDetails);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been added successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
        } catch (Exception e) {
            logger.error("Exception -> getPatientProfileByToken", e);
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
        }

        return jsonResponse;
    }

    /**
     * This function is used to delete insurance card
     *
     * @param securityToken
     * @param cardId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/archiveInsuranceCardWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object archiveInsuranceCardWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "cardId", required = false) Long cardId) throws IOException {
        logger.info("SecurityToken: " + securityToken);
        JsonResponse jsonResponse = new JsonResponse();
        try {
            //validate SecurityToken
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            patientProfileService.archiveInsuranceCard(patientProfile, cardId, jsonResponse);
//            if () {
//                logger.info("Record has been deleted successfully.");
//                jsonResponse.setErrorCode(1);
//                jsonResponse.setErrorMessage("Record has been deleted successfully.");
//            } else {
//                jsonResponse.setData(0);
//                jsonResponse.setErrorMessage("There is problem to delete record.");
//            }
        } catch (Exception e) {
            logger.error("Exception -> getPatientProfileByToken", e);
        }

        return jsonResponse;
    }

    /**
     * This function is use to update Primary insurance Card
     *
     * @param securityToken
     * @param cardId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updatPrimaryInsuranceCardWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updatPrimaryInsuranceCardWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "cardId", required = false) Integer cardId) throws IOException {
        logger.info("SecurityToken: " + securityToken);
        JsonResponse jsonResponse = new JsonResponse();
        PatientProfile profile = new PatientProfile();

        try {
            //validate SecurityToken
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            if (patientProfileService.updatePrimaryInsuranceCard(patientProfile, cardId)) {
                logger.info("Record has been updated successfully.");
                jsonResponse.setData(profile);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
        } catch (Exception e) {
            logger.error("Exception -> getPatientProfileByToken", e);
        }

        return jsonResponse;
    }

    /**
     * This function is used to get all the insurance card
     *
     * @param securityToken
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getInsuranceCardsWs", produces = "application/json")
    public @ResponseBody
    Object getInsuranceCardsWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "dependentId", required = false) Integer dependentId) throws IOException {
        logger.info("SecurityToken: " + securityToken);

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //validate SecurityToken
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                List<PatientInsuranceDetailsDTO> patientInsuranceDetailsDTOList = patientProfileService.getInsuranceCard(patientProfile, dependentId);
                if (!CommonUtil.isNullOrEmpty(patientInsuranceDetailsDTOList)) {
                    jsonResponse.setData(patientInsuranceDetailsDTOList);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(Constants.SUCCESS);
                    logger.info("Success");
                    return objectMapper(objectMapper, jsonResponse);
                } else {
                    setEmptyData(jsonResponse);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("No Insurance Card Avalible.Please add new.");
                    logger.info("Empty list");
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
                logger.info(Constants.INVALID_SECURITY_TOKEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(e.getMessage());
            logger.error("Exception -> getPatientProfileByToken", e);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to get the brand information of a drug
     *
     * @param drugBasicId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getDrugInfoWs", produces = "application/json")
    public @ResponseBody
    Object getDrugLookUpList(@RequestParam(value = "drugBasicId", required = false) Integer drugBasicId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Drug id is: " + drugBasicId);
        if (drugBasicId == 0) {
            logger.info("Drug Name is empty: " + drugBasicId);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Drug Name is empty.");
            return jsonResponse;
        }
        DrugDTO drugDTO = (DrugDTO) patientProfileService.getDrugInfo(drugBasicId);
        if (drugDTO != null) {
            jsonResponse.setData(drugDTO);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Success");
        } else {
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.EMPTY_MESSAGE);
            logger.info("Empty list");
        }
        logger.info("return json");
        return jsonResponse;
    }

    // Year End Statment Start                                                                                   //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    /**
     * This function is used to get the detail of orders placed in a period of
     * time.
     *
     * @param securityToken
     * @param json
     * @param startDate
     * @param endDate
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/yearEndStatmentWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object yearEndStatmentWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json, HttpServletRequest request) throws IOException {
//            @RequestParam(value = "startDate", required = false) Date startDate,
//            @RequestParam(value = "endDate", required = false) Date endDate,

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();

        try {
            //validate SecurityToken
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            OrderDetailDTO ordrDto = mapper.readValue(json, OrderDetailDTO.class);
            logger.info("SecurityToken: " + securityToken + " sDate: " + ordrDto.getsDate() + " eDate: " + ordrDto.geteDate());
            List<OrderDetailDTO> orderDetailDTOList = patientProfileService.getYearEndStatment(patientProfile, ordrDto.getsDate(), ordrDto.geteDate());
//           List<OrderDetailDTO> orderDetailDTOList = patientProfileService.getYearEndStatment(patientProfile, ordrDto.getRxNumber());
            if (!CommonUtil.isNullOrEmpty(orderDetailDTOList)) {
                logger.info("Record for the Patient Id " + patientProfile.getPatientProfileSeqNo() + "Orders count is " + orderDetailDTOList.size());
                jsonResponse.setData(orderDetailDTOList);
                //ExportToPdf file for year end statement
                ExportToPdf exportToPdf = new ExportToPdf();

                String yearEndStatementPdfFile = exportToPdf.downlaodYearEndStatement(orderDetailDTOList, patientProfile.getPatientProfileSeqNo(), request, logger);
                System.out.println("yearEndStatementPdfFile::::" + yearEndStatementPdfFile);
                patientProfileService.saveYearEndStatementInfo(patientProfile.getPatientProfileSeqNo(), yearEndStatementPdfFile);
                jsonResponse.setYearEndStatementPdfFile(yearEndStatementPdfFile);
                jsonResponse.setYearEndStatementLink(PropertiesUtil.getProperty("INSURANCE_CARD_URL") + yearEndStatementPdfFile);
//                }
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been added successfully.");
            } else {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(orderDetailDTOList);
                jsonResponse.setErrorMessage("There is no record found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: yearEndStatmentWs", e);
        }

        return jsonResponse;
    }

    /**
     * this function is used to get Profile details
     *
     * @param securityToken
     * @param type
     * @param dependentId
     * @param notificationMsgId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getProfileDetailsWs", produces = "application/json")
    public @ResponseBody
    Object getProfileDetails(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "dependentId", required = false) Integer dependentId,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        //validate SecurityToken
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        getProfileDetails(securityToken, type, 0, jsonResponse);
        if (!CommonUtil.isNullOrEmpty(jsonResponse.getErrorCode()) && jsonResponse.getErrorCode() == 1) {
            isPatientMsgResponse(0);
//            isPatientMsgResponse(1);
        }
        return jsonResponse;
    }

    private void getProfileDetails(String securityToken, String type, Integer dependentId, JsonResponse jsonResponse) throws IOException {
        PatientProfile patientProfile = null;
        LoginDTO profile = patientProfileService.getPatientProfileDetailByToken(securityToken, 0);
//        LoginDTO profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
        if (profile != null && profile.getPatientProfileSeqNo() != null) {
            if (type.equalsIgnoreCase("PersonalDetails")) {
//                patientProfile.setId(profile.getId());
//                patientProfile.setFirstName(profile.getFirstName() + " " + profile.getLastName());
//                patientProfile.setMobileNumber(profile.getMobileNumber());
//                patientProfile.setEmailAddress(profile.getEmailAddress());
//                patientProfile.setDob(profile.getDob());
//                patientProfile.setAlternatePhoneNumber(profile.getAlternatePhoneNumber());
//                if (profile.getDeliveryPreferenceId() != null && profile.getDeliveryPreferenceId().getId() != null) {
//                    patientProfile.setDprefaId(profile.getDeliveryPreferenceId().getId());
//                    patientProfile.setMiles(profile.getMiles());
//                    patientProfile.setDeliveryFee(profile.getDeliveryFee());
//                }
                setJsonResponse(jsonResponse, type, profile);
            } else if (type.equalsIgnoreCase("Allergies")) {
                patientProfile = new PatientProfile();
                patientProfile.setAllergies(profile.getAllergies());
                setJsonResponse(jsonResponse, type, patientProfile);
                jsonResponse.setData(profile);
            } else if (type.equalsIgnoreCase("CurrentAddress")) { //currentAddress 

                List<PatientDeliveryAddressDTO> list = patientProfileService.getPatientDeliveryAddressesByProfileId(profile.getPatientProfileSeqNo(), "CurrentAddress");
                if (list != null && list.size() > 0) {
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(type);
                    jsonResponse.setData(list);
                } else {
                    setEmptyData(jsonResponse);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("CurrentAddress is null.");
                }
            } else if (type.equalsIgnoreCase("PermanentAddress")) { //parmanetAddress 

                PatientDeliveryAddressDTO addressDto = patientProfileService.getPatientDeliveryAddressByProfileId(profile.getPatientProfileSeqNo(), "PermanentAddress");
                if (addressDto != null) {
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(type);
                    jsonResponse.setData(addressDto);
                } else {
                    setEmptyData(jsonResponse);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("PermanentAddress is null.");
                }
            } else if (type.equalsIgnoreCase("InsuranceCard")) {
                patientProfile = new PatientProfile();
                patientProfile.setInsuranceFrontCardPath(profile.getInsuranceFrontCardPath());
                patientProfile.setInsuranceBackCardPath(profile.getInsuranceBackCardPath());
//                if (profile.getInsuranceCardFront() != null) {
//                    //String insuranceFrontCard = saveInsuranceCard(profile.getInsuranceCardFront(), profile.getId(), "InsuranceFrontCard");
//                    logger.info("Insurance Front card path: " + profile.getInsuranceFrontCardPath());
//                    
//                } else {
//                    patientProfile.setInsuranceFrontCardPath(profile.getInsuranceFrontCardPath());
//                }
//                if (profile.getInsuranceCardBack() != null) {
//                    //String insuranceBackCardPath = saveInsuranceCard(profile.getInsuranceCardBack(), profile.getId(), "InsuranceBackCard");
//                    logger.info("Insurance Back card path: " + profile.getInsuranceBackCardPath());
//                    patientProfile.setInsuranceBackCardPath(profile.getInsuranceBackCardPath());
//                } else {
//                    
//                }
                patientProfile.setCardHolderRelation(profile.getCardHolderRelation());
                setJsonResponse(jsonResponse, type, patientProfile);
            } else if (type.equalsIgnoreCase(Constants.DRUG_SEARCHES)) {
                jsonResponse.setData(patientProfileService.getSearchesRecordList(profile.getId()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(type);
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is no record found against completed status.");
        }
    }

    private void setEmptyData(JsonResponse jsonResponse) {
        jsonResponse.setData(str);
    }

    /**
     * This function is used to update Personal Details
     *
     * @param securityToken
     * @param alternatePhoneNumber
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updatePersonalDetailsWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updatePersonalDetails(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "alternatePhoneNumber", required = false) String alternatePhoneNumber) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " Alternate PhoneNumber is: " + alternatePhoneNumber);

        PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }

        profile.setAlternatePhoneNumber(alternatePhoneNumber);
        profile.setUpdatedOn(new Date());
        if (patientProfileService.update(profile)) {
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("Record has been updated successfully.");
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
        }

        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to get list of drugs
     *
     * @param drugName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getDrugLookUpWs", produces = "application/json")
    public @ResponseBody
    Object getDrugLookUpList(@RequestParam(value = "drugBrandName", required = false) String drugName) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Drug Name is: " + drugName);
        if (drugName == null || drugName.isEmpty()) {
            logger.info("Drug Name is empty: " + drugName);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Drug Name is empty.");
            return jsonResponse;
        }
        Set<DrugBrandDTO> list = patientProfileService.getDrugBrandsList(drugName);
        if (list.size() > 0) {
            jsonResponse.setData(list);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Success");
        } else {
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.EMPTY_MESSAGE);
            logger.info("Empty list");
        }
        logger.info("return json");
        return jsonResponse;
    }

    /**
     * This function is used to get specific drugs of same brand
     *
     * @param securityToken
     * @param drugBrandId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getDrugRecordWs", produces = "application/json")
    public Object getDrugRecord(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "drugBrandId", required = false) Integer drugBrandId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is; " + securityToken + " DrugBrandId: " + drugBrandId);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            Drug drug = patientProfileService.getDrugList(drugBrandId);
            if (drug != null) {//&& drug.getDrugNdc() != null) {
                jsonResponse.setData(drug);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS);
                logger.info("Success");
            } else {
                setEmptyData(jsonResponse);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.EMPTY_MESSAGE);
                logger.info("Empty list");
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to get all the details of a drug
     *
     * @param securityToken
     * @param drugId
     * @param drugType
     * @param strength
     * @param qty
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getDrugRecordDetailWs", produces = "application/json")
    public Object getDrugRecordDetail(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "drugId", required = false) Long drugId,
            @RequestParam(value = "dType", required = false) String drugType,
            @RequestParam(value = "strength", required = false) String strength,
            @RequestParam(value = "qty", required = false) String qty,
            @RequestParam(value = "orderId", required = false) String orderId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("SecurityToken is: " + securityToken + " Drug Id is: " + drugId + " drug Type: " + drugType + " Strength is: " + strength + " Qty is: " + qty);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {

            DrugDetail drug = patientProfileService.getGenericBasedDrugDetailInfoHandler(drugId, AppUtil.getSafeInt(qty, 0), patientProfile, orderId);//.getDrugDetailInfo(drugId, AppUtil.getSafeInt(qty,0), patientProfile);
            //drug.setStrength(strength);
            drug.setFormDesc(drugType);
            //drug.setType(drugType);
            drug.setDrugQty(AppUtil.getSafeInt(qty, 0));
            drug.setStrength(strength);
            jsonResponse.setData(drug);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorCodeType(0);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Sucess");
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private void inValidZipCodeMessage(JsonResponse jsonResponse) {
        jsonResponse.setErrorCode(0);
        jsonResponse.setErrorMessage("InValid zip code");
    }

    /**
     * This function is used to get all the notification messages
     *
     * @param securityToken
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getNotificationMessagesWs", produces = "application/json")
    public Object getAllNotificationMessages(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            logger.info("Profile Id is: " + patientProfile.getPatientProfileSeqNo());
            jsonResponse.setData(patientProfileService.getNotificationMessagesByProfileId(patientProfile.getPatientProfileSeqNo()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Sucess");
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to save new transfer Rx
     *
     * @param securityToken
     * @param patientName
     * @param rxNumber
     * @param firstName
     * @param lastName
     * @param pharmacyName
     * @param pharmacyPhone
     * @param drug
     * @param quantity
     * @param transferId
     * @param drugImg
     * @param drugImgList
     * @param video
     * @param prescriberName
     * @param prescriberPhone
     * @param orderType
     * @param req
     * @param dependentId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/saveRxTransferVideoWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object saveRxTransferVideoWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "patientName", required = false) String patientName,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "rxNumber", required = false) String rxNumber,
            @RequestParam(value = "drug", required = false) String drug,
            @RequestParam(value = "video", required = false) MultipartFile video,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "orderType", required = false) String orderType,
            @RequestParam(value = "transferId", required = false) String transferId,
            @RequestParam(value = "drugImg", required = false) MultipartFile drugImg,
            @RequestParam(value = "drugImgs", required = false) List<MultipartFile> drugImgList,
            @RequestParam(value = "pharmacyName", required = false) String pharmacyName,
            @RequestParam(value = "pharmacyPhone", required = false) String pharmacyPhone,
            @RequestParam(value = "prescriberName", required = false) String prescriberName,
            @RequestParam(value = "prescriberPhone", required = false) String prescriberPhone,
            @RequestParam(value = "dependentId", required = false) String dependentId,
            @RequestParam(value = "strength", required = false) String strength,
            HttpServletRequest req) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " PatientName is: " + patientName + " pharmacyName: " + pharmacyName + " pharmacyPhone: " + pharmacyPhone + " Drug is: " + drug + " Quantity is: " + quantity + " video: " + video + " TransferId:: " + transferId);
        logger.info("DependentId:: " + dependentId + " drugImgList Size is:: " + drugImgList.size());
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        String dateStr = "";
        String completeName = "";
        try {
            Date date = new Date();
            dateStr = DateUtil.dateToString(date, "yy-MM-dd hh:mm:ss");
            dateStr = dateStr.replace(":", "-");
            dateStr = dateStr.replace(" ", "-");
        } catch (Exception e) {

        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            String videoPath = null, drugImagPath = null;
//            String sRootPath = new File("").getAbsolutePath();
            String contextPath = req.getServerName();
            int port = req.getServerPort();
            String webappRoot = servletContext.getRealPath("/");
            String relativeFolder = File.separator + "resources" + File.separator
                    + "noinsurancecard" + File.separator;
            List<OrderTransferImages> orderTransferImagesList = new ArrayList<>();
            if (video != null && !video.isEmpty()) {
                if (video.getBytes() != null) {
                    logger.info("video Format: " + video.getContentType());
//                    String webappRoot = servletContext.getRealPath("/");
//                    String relativeFolder = File.separator + "resources" + File.separator
//                            + "noinsurancecard" + File.separator;
                    String contentType = video.getContentType();
                    String[] contentArr = contentType.split("/");
                    String ext = "mp4"; //FileUtil.determineImageFormat(video.getBytes());
                    if (contentArr != null && contentArr.length > 1) {
                        ext = contentArr[1];
                    }
                    completeName = "Vid_" + patientProfile.getPatientProfileSeqNo() + "_" + dateStr + "." + ext;
                    File file = new File(webappRoot + relativeFolder + completeName);
                    videoPath = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "orderimages/" + completeName;
//                    videoPath = Constants.INSURANCE_CARD_PATH + video.getOriginalFilename() + patientProfile.getId() + "." + video.getContentType();
                    logger.info("Complete video Path: " + videoPath);
//                    FileCopyUtils.copy(video.getBytes(), new File(Constants.INSURANCE_CARD_PATH + video.getOriginalFilename()));
                    FileCopyUtils.copy(video.getBytes(),
                            new File(PropertiesUtil.getProperty("ORDER_IMAGES_CARD_PATH") + completeName));
                    CommonUtil.executeCommand(Constants.COMMAND);
                    completeName = videoPath;
                }
            } else if (!CommonUtil.isNullOrEmpty(drugImgList)) {
                completeName = uploadOrderTransferImages(drugImgList, completeName, patientProfile, dateStr, orderTransferImagesList);
            } else if (drugImg != null && drugImg.getBytes() != null) {
                logger.info("Drug Image Format: " + drugImg.getContentType());

                String ext = FileUtil.determineImageFormat(drugImg.getBytes());
                completeName = "Img_" + patientProfile.getPatientProfileSeqNo() + "_" + dateStr + "." + ext;
                drugImagPath = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "orderimages/" + completeName;
                logger.info("Complete Drug Image Path: " + drugImagPath);
                //FileCopyUtils.copy(drugImg.getBytes(), new File(Constants.INSURANCE_CARD_PATH + drugImg.getOriginalFilename()));
//                    FileCopyUtils.copy(drugImg.getBytes(),
//                            new File(Constants.INSURANCE_CARD_PATH + drugImg.getOriginalFilename()));
                FileCopyUtils.copy(drugImg.getBytes(),
                        new File(PropertiesUtil.getProperty("ORDER_IMAGES_CARD_PATH") + completeName));
                CommonUtil.executeCommand(PropertiesUtil.getProperty("COMMAND"));
                completeName = drugImagPath;
            }
//            saveRxTransfer(patientProfile, patientName, pharmacyName, pharmacyPhone, drug, quantity, videoPath, jsonResponse,
//                    rxNumber, transferId, drugImagPath, AppUtil.getSafeStr(prescriberName, ""),
//                    AppUtil.getSafeStr(prescriberPhone, ""), AppUtil.getSafeStr(orderType, ""));
//            drug=drug+" "+AppUtil.getSafeStr(strength, "");
            logger.info("DRUG INFO FOR TRANSFER-----------> DRUG:: " + drug + " Strength:: " + strength);
            if (video != null) {
                saveRxTransfer(patientProfile, patientName, firstName, lastName, pharmacyName, pharmacyPhone, drug, strength, quantity,
                        completeName, jsonResponse,
                        rxNumber, transferId,
                        "", AppUtil.getSafeStr(prescriberName, ""),
                        AppUtil.getSafeStr(prescriberPhone, ""), AppUtil.getSafeStr(orderType, ""), dependentId, orderTransferImagesList);
            } else {
                saveRxTransfer(patientProfile, patientName, firstName, lastName, pharmacyName, pharmacyPhone, drug, strength, quantity,
                        "", jsonResponse,
                        rxNumber, transferId,
                        completeName, AppUtil.getSafeStr(prescriberName, ""),
                        AppUtil.getSafeStr(prescriberPhone, ""), AppUtil.getSafeStr(orderType, ""), dependentId, orderTransferImagesList
                );
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(PropertiesUtil.getProperty("INVALID_SECURITY_TOKEN"));
            logger.info(PropertiesUtil.getProperty("INVALID_SECURITY_TOKEN"));
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private String uploadOrderTransferImages(List<MultipartFile> drugImgList, String completeName, PatientProfile patientProfile, String dateStr, List<OrderTransferImages> orderTransferImagesList) throws IOException {
        String drugImagPath;
        int count = 1;
        for (MultipartFile drugImg : drugImgList) {
            OrderTransferImages transferImages = new OrderTransferImages();
            logger.info("Drug Image Format: " + drugImg.getContentType());
            String ext = FileUtil.determineImageFormat(drugImg.getBytes());
            completeName = "Img_" + patientProfile.getPatientProfileSeqNo() + "_" + dateStr + "_" + count + "." + ext;
            drugImagPath = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "orderimages/" + completeName;
            logger.info("Complete Drug Image Path: " + drugImagPath);
            FileCopyUtils.copy(drugImg.getBytes(),
                    new File(PropertiesUtil.getProperty("ORDER_IMAGES_CARD_PATH") + completeName));
            CommonUtil.executeCommand(PropertiesUtil.getProperty("COMMAND"));
            completeName = drugImagPath;
            transferImages.setDrugImg(drugImagPath);
            orderTransferImagesList.add(transferImages);
            count++;
        }
        return completeName;
    }

    private void saveRxTransfer(PatientProfile patientProfile, String patientName, String pharmacyName, String pharmacyPhone,
            String drug, Integer quantity, String videoPath, JsonResponse jsonResponse, String rxNumber, String transferId,
            String drugImg, String prescriberName, String prescriberPhone, String orderType) {

        TransferRequest transferRequest = patientProfileService.saveRxTransferRequest(patientProfile.getPatientProfileSeqNo(), patientName,
                pharmacyName, pharmacyPhone, drug, quantity, videoPath, rxNumber, transferId, drugImg,
                prescriberName, prescriberPhone, orderType);
        if (transferRequest != null) {
            logger.info("Transfer Id is:: " + transferRequest.getId());
            RewardPoints rewardPoints = patientProfileService.getRxTransferPoints();
            if (rewardPoints != null && rewardPoints.getId() != null) {
                jsonResponse.setErrorCode(1);
                rewardPoints.setTransferId(transferRequest.getId());
                jsonResponse.setData(rewardPoints);
                jsonResponse.setErrorMessage("Rx Transfer successful");
            } else {
                jsonResponse.setErrorCode(1);
                transferRequest.setTransferId(transferRequest.getId());
                jsonResponse.setData(transferRequest);
                jsonResponse.setErrorMessage("Rx Transfer successful");
                logger.info("Rx Transfer successful: " + transferRequest.getId());
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            logger.info(Constants.ERROR_SAVE_RECORD);
        }
    }

    private void saveRxTransfer(PatientProfile patientProfile, String patientName, String firstName, String lastName, String pharmacyName, String pharmacyPhone,
            String drug, String strength, Integer quantity, String videoPath, JsonResponse jsonResponse, String rxNumber, String transferId,
            String drugImg, String prescriberName, String prescriberPhone, String orderType, String dependentId, List<OrderTransferImages> orderTransferImageses) {

        TransferRequest transferRequest = patientProfileService.saveRxTransferRequest(patientProfile.getPatientProfileSeqNo(), patientName,
                pharmacyName, pharmacyPhone, drug, quantity, videoPath, rxNumber, transferId, drugImg,
                prescriberName, prescriberPhone, orderType);
        if (transferRequest != null) {
            logger.info("Transfer Id is:: " + transferRequest.getId());
            RewardPoints rewardPoints = new RewardPoints(); //patientProfileService.getRxTransferPoints();
//            if (rewardPoints != null && rewardPoints.getId() != null) {
            jsonResponse.setErrorCode(1);
            rewardPoints.setTransferId(transferRequest.getId());
            jsonResponse.setData(rewardPoints);
            jsonResponse.setErrorMessage("Rx Transfer successful");
//            } else {
//                jsonResponse.setErrorCode(1);
//                transferRequest.setTransferId(transferRequest.getId());
//                jsonResponse.setData(transferRequest);
//                jsonResponse.setErrorMessage("Rx Transfer successful");
//                logger.info("Rx Transfer successful: " + transferRequest.getId());
//            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            logger.info(Constants.ERROR_SAVE_RECORD);
        }
    }

    /**
     * This function is used to get notification detail view
     *
     * @param securityToken
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getNotificationDeatilViewWs", produces = "application/json")
    public @ResponseBody
    Object getNotificationDeatilViewWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "id", required = false) Integer id) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " id is: " + id);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            System.out.println("securityToken not validated");
            return jsonResponse;
        }
        if (patientProfileService.getNotificationMessagesByProfileId(patientProfile.getPatientProfileSeqNo()).size() > 0) {
            jsonResponse.setData(patientProfileService.getNotificationMessagesListById(id));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("Sucess");
        } else {
            logger.info("NotificationMessagesList cannot found against this profile id or invalid token");
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Empty List");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to update notification messages
     *
     * @param securityToken
     * @param messageId
     * @param isRead
     * @param archiveValue
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateNotificationMessagesStatusWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateNotificationMessagesStatus(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "messageId", required = false) Integer messageId,
            @RequestParam(value = "isRead", required = false) int isRead,
            @RequestParam(value = "archiveValue", required = false) int archiveValue) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " messageId is: " + messageId + " archiveValue:: " + archiveValue);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        NotificationMessages notificationMessages;
        notificationMessages = patientProfileService.getNotificationMessagesByMessageId(messageId);
        if (notificationMessages != null) {
            notificationMessages = patientProfileService.updateNotificationMessages(messageId, archiveValue, notificationMessages, isRead);
            if (notificationMessages != null) {
                jsonResponse.setData(patientProfileService.getNotificationMessagesByPatientId(patientProfile.getPatientProfileSeqNo()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Success");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("There is some problem to save record.");
                logger.info("There is some problem to update record.");
            }
        } else {
            logger.info("NotificationMessage cannot found against this  id ");
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("NotificationMessage cannot found against this  id");
        }
//        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to get the message read count for in-app messages
     *
     * @param securityToken
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getIsReadNotificationMessagesCountWs", produces = "application/json")
    public @ResponseBody
    Object getIsReadNotificationMessagesCountWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (patientProfileService.getNotificationMessagesByProfileId(patientProfile.getPatientProfileSeqNo()).size() > 0) {
            NotificationMessages notificationMessages = patientProfileService.getIsReadNotificationMessagesCount(patientProfile.getPatientProfileSeqNo());
            if (notificationMessages != null) {
                jsonResponse.setData(notificationMessages);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Success");
            } else {
                setEmptyData(jsonResponse);
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Failure");
            }
        } else {
            logger.info("NotificationMessagesList cannot found against this profile id or invalid token");
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Empty List");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to save transfer rx points
     *
     * @param securityToken
     * @param points
     * @param description
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/saveTransferRxPointsWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object saveTransferRxPoints(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "points", required = false) String points,
            @RequestParam(value = "type", required = false) String description) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            if (patientProfileService.saveRewardHistory(description, Integer.parseInt(points), patientProfile.getPatientProfileSeqNo(), Constants.PLUS)) {
                //sendRxTransferNotification(patientProfile, jsonResponse);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("There is some problem to save record.");
                logger.info("There is some problem to save record.");
            }
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private void sendRxTransferNotification(PatientProfile patientProfile, Order order, TransferRequest transferRequest, JsonResponse jsonResponse) throws Exception {
        //send notification
        CampaignMessages campaignMessages = null;
        if (order != null && order.getIsBottleAvailable() != null && order.getIsBottleAvailable()) {
            campaignMessages = patientProfileService.getNotificationMsgs(Constants.RX_TRANSFER_RESPONSE, Constants.RX_TRANSFER);
        } else {
            campaignMessages = patientProfileService.getNotificationMsgs("Rx Scan Request", Constants.RX_TRANSFER);
        }
        if (campaignMessages != null) {
            if (CommonUtil.isNotEmpty(transferRequest.getPharmacyPhone())) {
                transferRequest.setPharmacyPhone(CommonUtil.replaceAllStr(transferRequest.getPharmacyPhone(), "-", ""));
            }
            String messageSubject = campaignMessages.getSubject();
            String messageText = campaignMessages.getSmstext();
            String userInputStr = "<h5> <small>Generic For</small> <span class=\"blue\"> User Inputted " + "</span></h5>";
            campaignMessages.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(
                    AppUtil.getSafeStr(campaignMessages.getPushNotification(), ""), order != null ? order.getId() : "0", null));
            campaignMessages.setSubject(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(messageSubject, order != null ? order.getId() : "0", null));
            campaignMessages.setSmstext(messageText.replace("[date_time]", DateUtil.dateToString(new Date(), Constants.USA_DATE_TIME_FORMATE))
                    .replace("[SERVICE_REQUESTED]", "X-FER RX via PHARMACY LABEL")
                    .replace("[request_no]", AppUtil.getSafeStr(transferRequest.getOrderId(), ""))
                    .replace("[REQUEST_MADE]", DateUtil.dateToString(new Date(), Constants.USA_DATE_TIME_FORMATE))
                    .replace("[DRUG_NAME]", order != null ? AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(order.getDrugName()), "") + userInputStr : "")
                    .replace("[DRUG_STRENGTH]", order != null ? AppUtil.getSafeStr(order.getStrength(), "") : "")
                    .replace("[DRUG_QTY]", order != null ? AppUtil.getSafeStr(order.getQty(), "") : "")
                    .replace("[DRUG_IMAGE]", (CommonUtil.isNotEmpty(transferRequest.getDrugImg()) && CommonUtil.urlAuthorization(transferRequest.getDrugImg()) ? "<img id='drugImg' src=\'" + transferRequest.getDrugImg() + "\' width='30' height='30'/>" : ""))
                    .replace("[RX_NUMBER]", AppUtil.getSafeStr(transferRequest.getRxNumber(), ""))
                    .replace("[PAYMENT_TYPE]", AppUtil.getSafeStr(transferRequest.getPaymentType(), ""))
                    .replace("[DELIVERY_DAYS]", AppUtil.getSafeStr(transferRequest.getDeliveryDay(), ""))
                    .replace("[PHARMACY_PHONE]", (CommonUtil.isNotEmpty(transferRequest.getPharmacyPhone()) ? CommonUtil.subStringPhone(transferRequest.getPharmacyPhone(), " ") : "")));
            if (patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order.getId())) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Rx Transfer Notification send.");
                logger.info("Rx Transfer Notification send.");
            } else {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Rx Transfer Notification send.");
                logger.info("Rx Transfer Notification send.");
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Rx Transfer Notification cannot send.");
            logger.info("Rx Transfer Notification cannot send.");
        }
    }

    /**
     * This function is used to get the reward points of a patient
     *
     * @param securityToken
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getMyRewardsPointWs", produces = "application/json")
    public @ResponseBody
    Object getMyRewardsPointWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            jsonResponse.setData(patientProfileService.getMyRewardsPoints(patientProfile.getPatientProfileSeqNo()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("Success");
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private File setFilePath(Integer profileId, String imageFormat, String name) {
        logger.info("Get file path");
        String webappRoot = servletContext.getRealPath("/");
        String relativeFolder = File.separator + "resources" + File.separator
                + "noinsurancecard" + File.separator;
        String filename = PropertiesUtil.getProperty("INSURANCE_CARD_PATH") + name + profileId + "." + imageFormat;
        try {
            Date date = new Date();
            String dateStr = DateUtil.dateToString(date, Constants.DATE_FORMATE); //DateUtil.dateToString(date, "yy-mm-dd hh:mm:ss");
            dateStr = dateStr.replace(":", "-");
            dateStr = dateStr.replace(" ", "-");
            dateStr = dateStr.replace("_", ":");
            filename = PropertiesUtil.getProperty("INSURANCE_CARD_PATH") + name + profileId + "-" + dateStr + "." + imageFormat;
            logger.info("-------------- IMAGE URL1 -------------- " + filename);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: setFilePath", e);
        }
        File file = new File(filename);
        return file;
    }

    private void setJsonResponse(JsonResponse jsonResponse, String type, Object patientProfile) {
        jsonResponse.setErrorCode(1);
        jsonResponse.setErrorMessage(type);
        jsonResponse.setData(patientProfile);
    }

    private Object objectMapper(ObjectMapper mapper, JsonResponse jsonResponse) throws IOException {
        logger.info("make json response");
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        logger.info("return json..");
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    }

    /**
     * This function is to get drugs
     *
     * @param securityToken
     * @param offSet
     * @return Its return JSON of profile
     * @throws java.io.IOException
     */
//    @RequestMapping(value = "/drugCategoryWs", produces = "application/json")
//    public @ResponseBody
//    Object drugCategoryWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
//            @RequestParam(value = "offSet", required = false) Integer offSet) throws IOException {
//        logger.info("Json response: " + offSet);
//        JsonResponse jsonResponse = new JsonResponse();
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
//            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
//                return objectMapper(objectMapper, jsonResponse);
//            }
////            DrugPagingDTO drugPagingDTO = objectMapper.readValue(json, DrugPagingDTO.class);
//            DrugPagingDTO drugPagingDTO = new DrugPagingDTO();
//            drugPagingDTO.setOffSet(offSet);
//            Long l_total = patientProfileService.getTotalDrugCategory();
//
//            List drugCategories = patientProfileService.getDrugCategory(drugPagingDTO.getOffSet());
//            int offsetResponse = drugPagingDTO.getOffSet() + Constants.PAGING_CONSTANT.RECORDS_PER_PAGE;
//            if (drugCategories != null && drugCategories.size() > 0) {
//                logger.info("List size is: " + drugCategories.size());
//                DrugModelDtoConversion drugModelDtoConversion = new DrugModelDtoConversion();
//                List<DrugCategoryDTO> lst_DrugCategoryDTO = drugModelDtoConversion.modelToDtoDrugCategory(drugCategories);
//                jsonResponse.setErrorCode(1);
//                jsonResponse.setErrorMessage("Getting drug category");
//                jsonResponse.setData(lst_DrugCategoryDTO);
//                jsonResponse.setTotalRecords(l_total);
//                jsonResponse.setOffset(offsetResponse);
//            } else {
//                logger.info("Drug category is null."); 
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("There is no category.");
//                setEmptyData(jsonResponse);
//                return objectMapper(objectMapper, jsonResponse);
//            }
//        } catch (Exception ex) {
//            jsonResponse.setData(null);
//            logger.error("Exception", ex);
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage("There is problem with getting category.");
//        }
//        return objectMapper(objectMapper, jsonResponse);
//    }
    /**
     * This function is used to get the categories of the drugs
     *
     * @param securityToken
     * @param offSet
     * @param searchParameter
     * @return @throws IOException
     */
//    @RequestMapping(value = "/drugCategorySearchWs", produces = "application/json")
//    public @ResponseBody
//    Object drugCategorySearchWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
//            @RequestParam(value = "offSet", required = false) Integer offSet,
//            @RequestParam(value = "searchParameter", required = false) String searchParameter) throws IOException {
//        logger.info("Json response: " + securityToken);
//        JsonResponse jsonResponse = new JsonResponse();
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
////            DrugPagingDTO drugPagingDTO = objectMapper.readValue(json, DrugPagingDTO.class);
//
//            Long l_total = patientProfileService.getTotalDrugCategory();
//            List drugCategories = patientProfileService.getDrugsByParameter(searchParameter);
//            int offsetResponse = offSet + Constants.PAGING_CONSTANT.RECORDS_PER_PAGE;
//            if (drugCategories != null && drugCategories.size() > 0) {
//
//                DrugModelDtoConversion drugModelDtoConversion = new DrugModelDtoConversion();
//                List<DrugCategoryDTO> lst_DrugCategoryDTO = drugModelDtoConversion.modelToDtoDrugCategorySearch(drugCategories);
//                jsonResponse.setErrorCode(1);
//                jsonResponse.setErrorMessage("Getting drug category");
//                jsonResponse.setData(lst_DrugCategoryDTO);
//                jsonResponse.setTotalRecords(l_total);
//                jsonResponse.setOffset(offsetResponse);
//            } else {
//                logger.info("Drug category is null.");
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("No drug found.");
//                setEmptyData(jsonResponse);
//                return objectMapper(objectMapper, jsonResponse);
//            }
//        } catch (Exception ex) {
//            jsonResponse.setData(null);
//            logger.error("Exception", ex);
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage("There is problem with getting category.");
//        }
//        return objectMapper(objectMapper, jsonResponse);
//    }
    /**
     * This function is used to get all the details of the drugs ordered by a
     * particular patient
     *
     * @param securityToken
     * @param drugId
     * @param qty
     * @param drugType
     * @param strength
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getPlaceRxOrderDetailsWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object getPlaceRxOrderDetailsWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "drugId", required = false) Integer drugId,
            @RequestParam(value = "qty", required = false) Integer qty,
            @RequestParam(value = "dType", required = false) String drugType,
            @RequestParam(value = "strength", required = false) String strength) throws IOException {
        logger.info("Json response: " + securityToken);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            OrderDetailDTO orderDetailDTO = patientProfileService.getPlaceRxOrderDetailsWs(patientProfile, drugId, qty, drugType, strength);
            if (orderDetailDTO != null) {
                jsonResponse.setData(orderDetailDTO);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Sucess");
            } else {
                setEmptyData(jsonResponse);
                logger.info("Empty list.");
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.EMPTY_MESSAGE);
            }
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private void payed(JsonResponse jsonResponse) {
        logger.info("Payment has already processed!");
        jsonResponse.setErrorCode(0);
        jsonResponse.setErrorMessage("Payment has already processed!");
    }

    /**
     * This function is used to compete the order by processing the payment
     * required to get the drug
     *
     * @param securityToken
     * @param drugId
     * @param finalPayment
     * @param redeemPoints
     * @param redeemPointsCost
     * @param handLingFee
     * @param strength
     * @param drugName
     * @param drugType
     * @param qty
     * @param drugPrice
     * @param cardId
     * @param addressId
     * @param orderId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/placeRxOrderDetailsWs", produces = "application/json")
    public @ResponseBody
    Object placeRxOrderDetailsWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "drugId", required = false) Integer drugId,
            @RequestParam(value = "payment", required = false) String finalPayment,
            @RequestParam(value = "redeemPoints", required = false) String redeemPoints,
            @RequestParam(value = "redeemPointsCost", required = false) String redeemPointsCost,
            @RequestParam(value = "handLingFee", required = false) String handLingFee,
            @RequestParam(value = "strength", required = false) String strength,
            @RequestParam(value = "drugName", required = false) String drugName,
            @RequestParam(value = "drugType", required = false) String drugType,
            @RequestParam(value = "qty", required = false) String qty,
            @RequestParam(value = "drugPrice", required = false) String drugPrice,
            @RequestParam(value = "cardId", required = false) String cardId,
            @RequestParam(value = "addressId", required = false) String addressId,
            @RequestParam(value = "orderId", required = false) String orderId) throws IOException, Exception {
        logger.info("Json response: " + securityToken + " Drug Id: " + drugId + " Final Payment is: " + finalPayment + " Redeem Points: " + redeemPoints + " Redeem Points Cost: " + redeemPointsCost);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            logger.info("Profile id is: " + patientProfile.getPatientProfileSeqNo());

            Order order = patientProfileService.saveRxOrder(patientProfile, drugId, finalPayment, redeemPoints, redeemPointsCost,
                    handLingFee, strength, drugName, drugType, qty, drugPrice, addressId);
            OrderHistory history = new OrderHistory();
            history.setOrder(order);
            history.setOrderStatus(order.getOrderStatus());
            history = patientProfileService.saveOrderHistory(history);
            if (order != null && order.getId() != null) {
                CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs("Order Placed", "Rx Order");
                if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                    order.setServiceRequested("WRITTEN RX via APP");
                    String message = CommonUtil.replaceRxOrderPlaceHolder(campaignMessages.getSmstext(), order);
                    campaignMessages.setSmstext(message);
                    logger.info("Notification send");
                    patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order.getId());
                    logger.info("Order id is: " + order.getId() + " Patient Profile id is: " + patientProfile.getPatientProfileSeqNo());
                    jsonResponse.setData(patientProfileService.viewOrderReceiptWs(patientProfile.getPatientProfileSeqNo(), order.getId()));
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(Constants.SUCCESS);
                    logger.info(Constants.SUCCESS);
                    //start sending place order email and sms
                    //startSMSAndEmailSendThread(order.getId(), drugName, order.getStrength(), order.getQty(), order.getDrugType(), "Order Placed");
                } else {
                    logger.info("Notification send fail or campaign messages is not defined.");
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Notification send fail or campaign messages is not defined.");
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.FAILURE);
            }
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return jsonResponse;
    }

    private void startSMSAndEmailSendThread(String orderId, String drugName, String drugStrength, String drugQty, String drugType, String requestMode) {
        logger.info("DrugName is# " + drugName);
        try {
            List<OrderPlaceEmail> orderPlaceEmails = patientProfileService.getOrderPlaceEmails();
            if (CommonUtil.isNullOrEmpty(orderPlaceEmails)) {
                logger.info("No email address exist for sending place order msg.");
                return;
            }

            CMSEmailContent cMSEmailContent = cMSService.getCMSEmailByPageId(Integer.parseInt(PropertiesUtil.getProperty("PLACE_ORDER_CMS_EMAIL_CONTENT_ID")));
            String emailBody = cMSEmailContent.getEmailBody();
            SMSAndEmailSendThread sMSAndEmailSendThread = new SMSAndEmailSendThread(logger);
            sMSAndEmailSendThread.setPatientProfileService(patientProfileService);
            sMSAndEmailSendThread.setOrderPlaceEmails(orderPlaceEmails);
            sMSAndEmailSendThread.setSubject(cMSEmailContent.getSubject());
            sMSAndEmailSendThread.setEmailBody(emailBody.replace("[request_no]", orderId)
                    .replace("[DRUG_NAME]", AppUtil.getProperDrugName(drugName, drugType, drugStrength))
                    .replace("[date]", DateUtil.dateToString(new Date(), Constants.USA_DATE_FORMATE))
                    .replace("[time]", DateUtil.dateToString(new Date(), "HH:mm"))
                    .replace("[PHARMACY_URL]", PropertiesUtil.getProperty("APP_PATH") + "/pharmacyqueue/login"));
            Thread thread = new Thread(sMSAndEmailSendThread);
            thread.start();
        } catch (Exception e) {
            logger.error("startSMSAndEmailSendThread# Exception# ", e);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This function is used to update details of rx order.
     *
     * @param securityToken
     * @param drugId
     * @param finalPayment
     * @param redeemPoints
     * @param redeemPointsCost
     * @param priceWithMargin
     * @param handLingFee
     * @param strength
     * @param drugName
     * @param drugType
     * @param qty
     * @param drugPrice
     * @param cardId
     * @param addressId
     * @param orderId
     * @param copayCardDic
     * @param deliveryPrefId
     * @param clearNameFlds
     * @param additionalDrugMargin
     * @param comments
     * @param orderChainId
     * @param isPrescriptionHardCopy
     * @param addCopyCard
     * @param coPayCardIdList
     * @param paymentType
     * @param rxAcqCost
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateRxOrderDetailsWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object placeRxOrderDetailsWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "drugId", required = false) Long drugId,
            @RequestParam(value = "payment", required = false) String finalPayment,
            @RequestParam(value = "priceWithMargin", required = false) String priceWithMargin,
            @RequestParam(value = "redeemPoints", required = false) String redeemPoints,
            @RequestParam(value = "redeemPointsCost", required = false) String redeemPointsCost,
            @RequestParam(value = "handLingFee", required = false) String handLingFee,
            @RequestParam(value = "strength", required = false) String strength,
            @RequestParam(value = "drugName", required = false) String drugName,
            @RequestParam(value = "drugType", required = false) String drugType,
            @RequestParam(value = "qty", required = false) String qty,
            @RequestParam(value = "drugPrice", required = false) String drugPrice,
            @RequestParam(value = "cardId", required = false) String cardId,
            @RequestParam(value = "copayCardDictionary", required = false) String copayCardDic,
            @RequestParam(value = "addressId", required = false) String addressId,
            @RequestParam(value = "deliveryPrefId", required = false) String deliveryPrefId,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "clearNameFlds", required = false) String clearNameFlds,
            @RequestParam(value = "additionalMargin", required = false) String additionalDrugMargin,
            @RequestParam(value = "comments", required = false) String comments,
            @RequestParam(value = "orderChainId", required = false) String orderChainId,
            @RequestParam(value = "isPrescriptionHardCopy", required = false) Boolean isPrescriptionHardCopy,
            @RequestParam(value = "addCopyCard", required = false) Boolean addCopyCard,
            @RequestParam(value = "coPayCardIdList", required = false) Long[] coPayCardIdList,
            @RequestParam(value = "paymentType", required = false) String paymentType,
            @RequestParam(value = "miles", required = false) String miles,
            @RequestParam(value = "rxAcqCost", required = false) String rxAcqCost) throws IOException, Exception {

        JsonResponse jsonResponse = new JsonResponse();
        try {
            logger.info("Json response: " + securityToken + " Drug Id: " + drugId + " Final Payment is: " + finalPayment
                    + " Redeem Points: " + redeemPoints + " Redeem Points Cost: " + redeemPointsCost + " rxAcqCost: " + rxAcqCost);

            ObjectMapper objectMapper = new ObjectMapper();
            PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                logger.info("Profile id is: " + patientProfile.getPatientProfileSeqNo());
                ///////////////////////////////////////////////////////////////////
                PatientDeliveryAddress address = patientProfileService.getPatientDeliveryAddressById(AppUtil.getSafeInt(addressId, 0));
                String fee = "0.0";
                BigDecimal feeNumeric = BigDecimal.ZERO;
                String prefName = "";
                DeliveryPreferences prefDetails = this.patientProfileService.getDeliveryPreferenceById(AppUtil.getSafeInt(deliveryPrefId, 0));
                if (prefDetails != null) {
                    prefName = prefDetails.getName();
                }
                if (AppUtil.getSafeStr(prefName, "").contains("2nd Day")) {
                    fee = "0.0";
                } else if (address != null) {
                    fee = patientProfileService.getZipCodeCalculations(address.getZip(), patientProfile.getPatientProfileSeqNo(), AppUtil.getSafeInt(deliveryPrefId, 0));
                    feeNumeric = CommonUtil.getStrToBigDecimal(fee);
                }

//                List<CoPayCardDetails> coPayCardDetailsList =null;
//                if(copayCardDic!=null)
//                {
//                 //coPayCardDetailsList = objectMapper.readValue(copayCardDic, TypeFactory.collectionType(List.class, CoPayCardDetails.class));
//                }
                //CoPayCardDetails coPayCardDetails = objectMapper.readValue(copayCardDic, CoPayCardDetails.class);
                //////////////////////////////////////////////////////////////////
                //getPatientPaymentInfoDefaultCardByProfileId(patientProfile.getId(), "Yes");
                //            if (patientPaymentInfo != null && patientPaymentInfo.getId() != null && patientPaymentInfo.getDefaultCard().equalsIgnoreCase("Yes")) {
                //                logger.info("is Default Card: " + patientPaymentInfo.getDefaultCard());
                //                logger.info(" Card NO: " + patientPaymentInfo.getCardNumber());
                //                if (!validCardTypeLength(patientPaymentInfo.getCardType(), patientPaymentInfo.getCvvNumber(), jsonResponse)) {
                //                    return objectMapper(objectMapper, jsonResponse);
                //                }
                //                if (CommonUtil.isNotEmpty(finalPayment)
                //                        && CommonUtil.getStrToBigDecimal(finalPayment).compareTo(BigDecimal.ZERO) > 0) {
                //                    BigDecimal finalPaymentPlusHandlingFee = CommonUtil.getStrToBigDecimal(finalPayment).add(feeNumeric);
                //                    DoDirectPayment payment = new DoDirectPayment();
                //                    DoDirectPaymentResponseType response = payment.doDirectPayment(patientPaymentInfo.getCardType(),
                //                            patientPaymentInfo.getCardNumber(), patientPaymentInfo.getExpiryDate(),
                //                            patientPaymentInfo.getCardHolderName(), "" + finalPaymentPlusHandlingFee, patientPaymentInfo.getCvvNumber());
                //                    logger.info("DoDirectPaymentResponseType: " + response);
                //                    if (!response.getAck().getValue().equalsIgnoreCase("success")) {
                //                        List<ErrorType> errorList = response.getErrors();
                //                        jsonResponse.setErrorCode(0);
                //                        jsonResponse.setErrorMessage(errorList.get(0).getLongMessage());
                //                        return objectMapper(objectMapper, jsonResponse);
                //                    }
                //                }
                //            } else {
                //                logger.info("Payment info is null");
                //            }
                //////////////////////////////////////////////////////////////
                //            saveRxTransfer(patientProfile, patientName, pharmacyName, pharmacyPhone, drug, quantity, null, jsonResponse, rxNumber, transferId, null,prescriberName,  prescriberPhone);
                /////////////////////////////////////////////////////////////
                String video = "";
                String imagePath = "";
                String source = "";
                if (AppUtil.getSafeStr(orderId, "").length() > 0) {
                    TransferRxQueueDTO transfer = this.patientProfileService.getRequestVideoAndImage(orderId);
                    if (transfer != null) {
                        video = AppUtil.getSafeStr(transfer.getPtVideo(), "");
                        imagePath = AppUtil.getSafeStr(transfer.getTransferImage(), "");
                        if (video.length() > 0) {
                            source = "Video";
                        } else if (imagePath.length() > 0) {
                            source = "Image";
                        }
                    }

                }

                Order order = patientProfileService.saveRxOrder(patientProfile, drugId, finalPayment, priceWithMargin, redeemPoints, redeemPointsCost,
                        fee, strength, drugName, drugType, qty, drugPrice, additionalDrugMargin, addressId,
                        orderChainId, video, imagePath, comments, false, isPrescriptionHardCopy, addCopyCard, paymentType, prefDetails, cardId, copayCardDic, miles, rxAcqCost);
                OrderHistory history = new OrderHistory();
                history.setOrder(order);
                history.setOrderStatus(order.getOrderStatus());
                history = patientProfileService.saveOrderHistory(history);
                if (order != null && order.getId() != null) {
                    CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs("Order Placed", "Rx Order");
                    if (campaignMessages != null && campaignMessages.getMessageId() != null) {
                        order.setServiceRequested("WRITTEN RX via APP");
                        String messageSubject = campaignMessages.getSubject();
                        String message = campaignMessages.getSmstext();
                        String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
                        System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
                        String brandName = "";
                        String genericName = "";
                        campaignMessages.setSubject(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(messageSubject, order != null ? order.getId() : "0", null));
                        campaignMessages.setPushNotification(
                                patientProfileService.getMessageSubjectWithprocessedPlaceHolders(pushNot, order != null ? order.getId() : "0", null));
                        //////////////////////////////////////////
//                        String[] arr = AppUtil.getBrandAndGenericFromDrugName(order.getDrugName());
//                        System.out.println("DRUG NAME -----------> " + order.getDrugName());
//                        if (arr != null) {
//                            if (arr.length == 2) {
//                                brandName = arr[0];
//                                genericName = arr[1];
//                            } else {
//                                brandName = arr[0] + "(" + Constants.BRAND_NAME_ONLY + ")";
//                            }
//                        }
                        String drugQualifiedName = AppUtil.getProperDrugName(order.getDrugName(),
                                AppUtil.getSafeStr(drugType, ""), AppUtil.getSafeStr(strength, ""));
                        System.out.println("BRAND NAME -----------> " + brandName + " GENERIC NAME ----> " + genericName);
                        logger.info("Drug replace name# " + drugQualifiedName);
                        /////////////////////////////////////////
                        campaignMessages.setSmstext(message.replace("[date_time]", DateUtil.dateToString(new Date(), Constants.USA_DATE_TIME_FORMATE))
                                .replace("[request_no]", AppUtil.getSafeStr(order.getId(), ""))
                                .replace("[DRUG_NAME]", AppUtil.getSafeStr(drugQualifiedName, ""))
                                .replace("[generic_name]", AppUtil.getSafeStr(genericName, ""))
                                .replace("[DRUG_TYPE]", AppUtil.getSafeStr(drugType, ""))
                                .replace("[DRUG_STRENGTH]", AppUtil.getSafeStr(strength, ""))
                                .replace("[DRUG_QTY]", AppUtil.getSafeStr(qty, ""))
                                .replace("[PAYMENT_TYPE]", AppUtil.getSafeStr(paymentType, ""))
                                .replace("[DELIVERY_DAYS]", AppUtil.getSafeStr(prefName, ""))
                                .replace("[SERVICE_REQUESTED]", "WRITTEN RX via APP")
                                .replace("[INS_TYPE]", AppUtil.getSafeStr(paymentType, "").equalsIgnoreCase("INSURANCE") ? "Y" : "N")
                                .replace("[RX_ESTIMATE]", order.getEstimatedPrice() != null
                                        && order.getEstimatedPrice() > 0d
                                                ? AppUtil.roundOffNumberToCurrencyFormat(order.getEstimatedPrice(), "en", "US")
                                                : AppUtil.roundOffNumberToCurrencyFormat(0d, "en", "US"))
                                .replace("[REQUEST_MADE]", DateUtil.dateToString(new Date(), Constants.USA_DATE_TIME_FORMATE)));
                        logger.info("Notification sending.....");
                        /////////////////////////////////////////////
                        /**
                         * if (AppUtil.getSafeStr(genericName, "").length() == 0
                         * && campaignMessages != null &&
                         * AppUtil.getSafeStr(campaignMessages.getSmstext(),
                         * "").length() > 0) {
                         * campaignMessages.setSmstext(campaignMessages.getSmstext().replace("Generic
                         * For", "")); }*
                         */
                        /////////////////////////////////////////////
                        patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order.getId());
                        logger.info("Order id is: " + order.getId() + " Patient Profile id is: " + patientProfile.getPatientProfileSeqNo());
                        jsonResponse.setData(patientProfileService.viewOrderReceiptWs(patientProfile.getPatientProfileSeqNo(), order.getId(), fee, prefName, "", "", order.getAwardedPoints() != null ? "" + order.getAwardedPoints() : "0", clearNameFlds, paymentType, comments, source));
                        jsonResponse.setErrorCode(1);
                        jsonResponse.setErrorMessage(Constants.SUCCESS);
                        logger.info(Constants.SUCCESS);
                        startSMSAndEmailSendThread(order.getId(), order.getDrugName(), order.getStrength(), order.getQty(), order.getDrugType(), "Order Placed");
                    } else {
                        logger.info("Notification send fail or campaign messages is not defined.");
                        jsonResponse.setErrorCode(0);
                        jsonResponse.setErrorMessage("Notification send fail or campaign messages is not defined.");
                    }
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage(Constants.FAILURE);
                }
            } else {
                CommonUtil.inValidSecurityToken(jsonResponse);
            }
            return jsonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            return jsonResponse;
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This function is used to get redeem points
     *
     * @param securityToken
     * @param availablePoints
     * @param redeemPoints
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getRedeemPointsWs", produces = "application/json")
    public @ResponseBody
    Object getRedeemPointsWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "availablePoints", required = false) String availablePoints,
            @RequestParam(value = "redeemPoints", required = false) String redeemPoints) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken + " Available Points: " + availablePoints + " Redeem Points: " + redeemPoints);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            OrderDetailDTO orderDetailDTO = patientProfileService.getRedeemPointsWs(redeemPoints);
            if (orderDetailDTO != null) {
                jsonResponse.setData(orderDetailDTO);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS);
            } else {
                setEmptyData(jsonResponse);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.EMPTY_MESSAGE);
            }
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to get Quick Order Statistics
     *
     * @param securityToken
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getQuickStatsWs", produces = "application/json")
    public @ResponseBody
    Object getQuickStatsWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            jsonResponse.setData(patientProfileService.getQuickStatsWs(patientProfile.getPatientProfileSeqNo()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to get filled rx history
     *
     * @param securityToken
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getFilledRxHistoryWs", produces = "application/json")
    public @ResponseBody
    Object getFilledRxHistory(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            jsonResponse.setData(patientProfileService.getFilledRxHistory(patientProfile.getPatientProfileSeqNo()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    //////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/GetRefillRxListWs", produces = "application/json")
    public @ResponseBody
    Object getRefillRxList(@RequestHeader(value = "securityToken") String securityToken, @RequestBody String json) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        BaseDTO baseDTO = objectMapper.readValue(json, BaseDTO.class);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        } else {
            jsonResponse.setData(patientProfileService.getRefillRx(patientProfile.getPatientProfileSeqNo(), baseDTO.getOrderId(), baseDTO.getDependentId()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        }
        return objectMapper(objectMapper, jsonResponse);
    }
    //////////////////////////////////////////////////////////////////////////

    /**
     * This function is used to view order receipt
     *
     * @param securityToken
     * @param json
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/viewOrderReceiptWs", produces = "application/json")
    public @ResponseBody
    Object viewOrderReceiptWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        BaseDTO baseDTO = objectMapper.readValue(json, BaseDTO.class);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            jsonResponse.setData(patientProfileService.viewOrderReceiptWs(patientProfile.getPatientProfileSeqNo(), baseDTO.getOrderId()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to send campaign messages who have no insurance
     *
     * @param profileId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getNoInsurancesWs", produces = "application/json")
    public @ResponseBody
    Object getNoInsurancesWs(@RequestParam(value = "profileId", required = false) Integer profileId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("profile id is: " + profileId);
        PatientProfile patientProfile = patientProfileService.getPatientProfileById(profileId);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs(Constants.NO_INSURANCE, Constants.EVENTNAME);
            saveNoInsuranceMsgs(campaignMessages, patientProfile, profileId, jsonResponse);
        } else {
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("Profile id does not exist.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private void saveNoInsuranceMsgs(CampaignMessages campaignMessages, PatientProfile patientProfile, Integer profileId, JsonResponse jsonResponse) {
        if (campaignMessages != null && campaignMessages.getMessageId() != null) {
            OrderDetailDTO orderDetailDTO = patientProfileService.getNoInsurancesWs(patientProfile.getMobileNumber(), patientProfile.getPatientProfileSeqNo());
            RewardPoints rewardPoints = patientProfileService.getRewardPoints(Constants.Reward_Points_Id);
            String securityToken = RedemptionUtil.getRandomToken();
            logger.info("Token is: " + securityToken);
            if (rewardPoints != null) {
                logger.info("Rewards Points: " + rewardPoints.getPoint().intValueExact());
                setRewardPoints(rewardPoints, profileId);
//                orderDetailDTO.setPoints(rewardPoints.getPoint().intValueExact());
                orderDetailDTO.setSecurityToken(securityToken);
            }
            orderDetailDTO.setSubject(campaignMessages.getSubject());
            if (campaignMessages.getSmstext().contains("cid:No_Insurance")) {
                logger.info("Set image path");
                String mess = campaignMessages.getSmstext();
//                mess = mess.replaceAll("cid:No_Insurance", orderDetailDTO.getNoInsuranceCard());
//                orderDetailDTO.setNoInsuranceCard(mess);
                campaignMessages.setSmstext(mess);
            }
//            patientProfile.setDiscountPercentage(orderDetailDTO.getLocalDisCount());
            patientProfile.setSecurityToken(securityToken);
            patientProfile.setStatus(Constants.COMPLETED);
            patientProfileService.update(patientProfile);
            patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo());
            jsonResponse.setData(orderDetailDTO);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } else {
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Empty Message");
        }
    }

    /**
     * This function is used to updated shipping address
     *
     * @param securityToken
     * @param profileId
     * @param dprefId
     * @param distance
     * @param deliveryFee
     * @param addressLine
     * @param appt
     * @param description
     * @param city
     * @param stateId
     * @param zip
     * @param addressType
     * @param defaultAddress
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/shippingAddressWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object deliveryAddressesWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "profileId", required = false) Integer profileId,
            @RequestParam(value = "dprefaId", required = false) Integer dprefId,
            @RequestParam(value = "miles", required = false) String distance,
            @RequestParam(value = "deliveryFee", required = false) String deliveryFee,
            @RequestParam(value = "address", required = false) String addressLine,
            @RequestParam(value = "apartment", required = false) String appt,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "stateId", required = false) Integer stateId,
            @RequestParam(value = "zip", required = false) String zip,
            @RequestParam(value = "addressType", required = false) String addressType,
            @RequestParam(value = "defaultAddress", required = false) String defaultAddress) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("ProfileId is: " + profileId + " DeliveryPreferences id: " + dprefId + " Address is: " + addressLine + " Appt: " + appt + " Description is: " + description + " City: " + city + " State id: " + stateId + " Zip Code: " + zip + " AddressType is: " + addressType + " Default address is: " + defaultAddress);
        PatientProfile profile;
        if (securityToken != null) {
            profile = patientProfileService.getPatientProfileByToken(securityToken);
        } else {
            profile = patientProfileService.getPatientProfileById(profileId);
        }
        if (!isValidZipCode(zip, profile, jsonResponse)) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (profile != null && profile.getPatientProfileSeqNo() != null) {
            if (dprefId != null) {
                String status = Constants.PENDING;
                if (securityToken != null || CommonUtil.isNotEmpty(profile.getSecurityToken())) {
                    status = Constants.COMPLETED;
                }
                patientProfileService.updateDeliveryPreferencesByProfileId(profile.getPatientProfileSeqNo(), dprefId, status, deliveryFee, distance);
            }
            if (defaultAddress.equalsIgnoreCase(Constants.NO)) {
                if (!patientProfileService.isDefaultDeliveryAddress(profile.getPatientProfileSeqNo())) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Please Make it your default shipping address.");
                    return objectMapper(objectMapper, jsonResponse);
                }
            }
            PatientDeliveryAddress patientDeliveryAddress = patientProfileService.savePatientDeliveryAddress(profile.getPatientProfileSeqNo(), addressLine, appt, description, city, stateId, zip, addressType, defaultAddress, dprefId);
            if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null) {
                jsonResponse.setData(patientProfileService.getPatientDeliveryAddressById(profile.getPatientProfileSeqNo(), patientDeliveryAddress.getId()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS_MESSEGE);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            }
        } else {
            logger.info("Profile info is: ");
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.PROFILE_ID_REQ);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private boolean isValidZipCode(String zip, PatientProfile profile, JsonResponse jsonResponse) throws IOException {
        List<DeliveryDistanceFeeDTO> list = patientProfileService.getZipCodeCalculationsList(zip, profile);
        if (list.isEmpty()) {
            if (!validateZipCode(zip, jsonResponse, profile.getPatientProfileSeqNo())) {
                return false;
            }
        }
        return true;
    }

    private boolean validateZipCode(String zip, JsonResponse jsonResponse, Integer profileId) throws IOException {
        logger.info("Validate zip code -> zip code is: " + zip);
        DeliveryDistanceFeeDTO deliveryDistanceFeeDTO = patientProfileService.validateDistanceFeeDTO(zip, profileId);
        if (deliveryDistanceFeeDTO != null && deliveryDistanceFeeDTO.getError_code() != null) {
            if (deliveryDistanceFeeDTO.getError_code() == 400) {
                inValidZipCodeMessage(jsonResponse);
                return false;
            }
            if (deliveryDistanceFeeDTO.getError_code() == 429) {
                zipCodeLimitError(jsonResponse);
                return false;
            }
            if (deliveryDistanceFeeDTO.getError_code() == 404) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(deliveryDistanceFeeDTO.getErrorMessage());
                return false;
            }
        }
        return true;
    }

    private void zipCodeLimitError(JsonResponse jsonResponse) {
        jsonResponse.setErrorCode(0);
        jsonResponse.setErrorMessage(Constants.LIMIT_EXCEEDED);
    }

    /**
     * This function is used to get delivery address
     *
     * @param securityToken
     * @param json
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/GetPtAddressWs", produces = "application/json")
    public @ResponseBody
    Object getDeliveryAddressWs(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("SecurityToken: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        PatientDeliveryAddressDTO patientDeliveryAddressDTO = objectMapper.readValue(json, PatientDeliveryAddressDTO.class);
        patientDeliveryAddressDTO = patientProfileService.getPatientDeliveryAddressById(patientProfile.getPatientProfileSeqNo(), patientDeliveryAddressDTO.getAddressId());
        if (patientDeliveryAddressDTO != null && patientDeliveryAddressDTO.getAddressId() != null) {
            jsonResponse.setData(patientDeliveryAddressDTO);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } else {
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("DeliveryAddress is null.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to save rx transfer for future order
     *
     * @param securityToken
     * @param patientName
     * @param rxNumber
     * @param pharmacyName
     * @param pharmacyPhone
     * @param drug
     * @param quantity
     * @param transferId
     * @param prescriberName
     * @param prescriberPhone
     * @param orderType
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/saveRxTransferWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object saveRxTransferRequest(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "patientName", required = false) String patientName,
            @RequestParam(value = "rxNumber", required = false) String rxNumber,
            @RequestParam(value = "pharmacyName", required = false) String pharmacyName,
            @RequestParam(value = "pharmacyPhone", required = false) String pharmacyPhone,
            @RequestParam(value = "drug", required = false) String drug,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "transferId", required = false) String transferId,
            @RequestParam(value = "prescriberName", required = false) String prescriberName,
            @RequestParam(value = "prescriberPhone", required = false) String prescriberPhone,
            @RequestParam(value = "orderType", required = false) String orderType) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " PatientName is: " + patientName + " pharmacyName: " + pharmacyName + " pharmacyPhone: " + pharmacyPhone + " Drug is: " + drug + " Quantity is: " + quantity + " TransferId:: " + transferId);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            saveRxTransfer(patientProfile, patientName, pharmacyName, pharmacyPhone, drug, quantity, null, jsonResponse, rxNumber, transferId, null, prescriberName, prescriberPhone, orderType);
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to calculate distance between different geographic
     * location by zip codes
     *
     * @param securityToken
     * @param profileId
     * @param zip
     * @param pickedFromPharmacy
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/calculateZipCodeDistanceWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object calculateZipCodeDistance(@RequestHeader(value = "profileId", required = false) Integer profileId,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "zip", required = false) String zip, @RequestParam(value = "pickedFromPharmacy", required = false) boolean pickedFromPharmacy) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " Zip Code: " + zip + " ProfileId: " + profileId + " pickedFromPharmacy: " + pickedFromPharmacy);
        PatientProfile patientProfile;
        if (profileId != null) {
            patientProfile = patientProfileService.getPatientProfileById(profileId);
        } else {
            patientProfile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            List<DeliveryDistanceFeeDTO> list = patientProfileService.getZipCodeCalculationsList(zip, patientProfile, pickedFromPharmacy);
            if (list.isEmpty()) {
                if (!validateZipCode(zip, jsonResponse, patientProfile.getPatientProfileSeqNo())) {
                    return objectMapper(objectMapper, jsonResponse);
                }
                list = patientProfileService.getDistanceFeeDTO(zip, patientProfile.getPatientProfileSeqNo());
            }
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(list);
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Invalid security or profileId");
            logger.info("Invalid security token.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to confirm payment from patient in case the payment
     * is more than the quoted price
     *
     * @param securityToken
     * @param transferId
     * @param paymentId
     * @param devliveryAddressId
     * @param dprefId
     * @param zip
     * @param miles
     * @param deliveryFee
     * @param comments
     * @param addCopyCard
     * @param paymentType
     * @param orderChainId
     * @param coPayCardIdList
     * @param copayCardDic
     * @param isBottleAvailable
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/confirmPaymentWs", produces = "application/json", method = RequestMethod.POST)
    public Object confirmRxTransferPayment(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "transferId", required = false) Integer transferId,
            @RequestParam(value = "paymentId", required = false) Integer paymentId,
            @RequestParam(value = "devliveryAddressId", required = false) Integer devliveryAddressId,
            @RequestParam(value = "dprefId", required = false) Integer dprefId,
            @RequestParam(value = "zip", required = false) String zip,
            @RequestParam(value = "miles", required = false) String miles,
            @RequestParam(value = "deliveryFee", required = false) String deliveryFee,
            @RequestParam(value = "comments", required = false) String comments,
            @RequestParam(value = "addCopyCard", required = false) Boolean addCopyCard,
            @RequestParam(value = "paymentType", required = false) String paymentType,
            @RequestParam(value = "orderChainId", required = false) String orderChainId,
            @RequestParam(value = "coPayCardIdList", required = false) Long[] coPayCardIdList,
            @RequestParam(value = "copayCardDictionary", required = false) String copayCardDic,
            @RequestParam(value = "isBottleAvailable", required = false) Boolean isBottleAvailable) throws IOException, Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " transferId: " + transferId + " paymentId: " + paymentId + " devliveryAddressId: " + devliveryAddressId + " dprefId: " + dprefId + " zip: " + zip + " miles: " + miles + " deliveryFee: " + deliveryFee);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            TransferRequest transferRequest = (TransferRequest) patientProfileService.getObjectById(new TransferRequest(), transferId);
            if (transferRequest != null && transferRequest.getId() != null) {
                Order order = patientProfileService.updateTransferOrder(patientProfile.getPatientProfileSeqNo(), transferId, devliveryAddressId,
                        paymentId, dprefId, zip, miles, deliveryFee, AppUtil.getSafeLong(orderChainId, 0L), comments, paymentType,
                        addCopyCard, coPayCardIdList, dprefId, copayCardDic, isBottleAvailable);
                if (order != null) {
                    String source = "";
                    String video = AppUtil.getSafeStr(order.getVideo(), "");
                    String image = AppUtil.getSafeStr(order.getImagePath(), "");
                    if (video.length() > 0) {
                        source = "Video";
                    } else if (image.length() > 0) {
                        source = "Image";
                    }
                    order.setServiceRequested("X-FER RX via PHARMACY LABEL");
                    order.setRxNumber(transferRequest.getRxNumber());
                    order.setPharmacyPhone(transferRequest.getPharmacyPhone());
                    logger.info("Order Number:: " + order.getId());
                    transferRequest.setOrderId(order.getId());
                    transferRequest.setPaymentType(paymentType);
                    if (order.getDeliveryPreference() != null && order.getDeliveryPreference().getId() != null) {
                        String deliveryDay = order.getDeliveryPreference().getName();
                        if (CommonUtil.isNotEmpty(deliveryDay)) {
                            transferRequest.setDeliveryDay(deliveryDay.replace("*", " ") + "Delivery");
                        } else {
                            transferRequest.setDeliveryDay("");
                        }

                    }
                    sendRxTransferNotification(patientProfile, order, transferRequest, jsonResponse);
                    jsonResponse.setData(patientProfileService.viewOrderReceiptWs(patientProfile.getPatientProfileSeqNo(), order.getId()));
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("Rx Transfer successful");
                    startSMSAndEmailSendThread(order.getId(), order.getDrugName(), order.getStrength(), order.getQty(), order.getDrugType(), Constants.RX_TRANSFER);
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("TransferRequest is null against this id." + transferId);
                logger.info("TransferRequest is null against this id." + transferId);
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info("Invalid security token.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to save searches made by customer
     *
     * @param securityToken
     * @param profileId
     * @param drugId
     * @param drugName
     * @param drugType
     * @param qty
     * @param drugPrice
     * @param strength
     * @param genericName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/saveDrugSearchesWs", produces = "application/json", method = RequestMethod.POST)
    public Object saveDrugSearchesWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "profileId", required = false) String profileId,
            @RequestParam(value = "drugId", required = false) String drugId,
            @RequestParam(value = "drugName", required = false) String drugName,
            @RequestParam(value = "drugType", required = false) String drugType,
            @RequestParam(value = "drugQty", required = false) String qty,
            @RequestParam(value = "drugPrice", required = false) String drugPrice,
            @RequestParam(value = "strength", required = false) String strength,
            @RequestParam(value = "genericName", required = false) String genericName) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        if (CommonUtil.isNullOrEmpty(profileId)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.PROFILE_ID_REQ);
            return objectMapper(objectMapper, jsonResponse);
        }
        if (CommonUtil.isNullOrEmpty(drugId)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.DRUG_ID_REQ);
            return objectMapper(objectMapper, jsonResponse);
        }
        logger.info("Security Token is: " + securityToken + " drugName is: " + drugName + " drugType is: " + drugType + " qty is: " + qty + " drugPrice is: " + drugPrice + " strength is: " + strength + " genericName is: " + genericName);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            if (patientProfileService.saveDrugSearchesWs(Integer.parseInt(profileId),
                    AppUtil.getSafeLong(drugId, 0L), drugName, drugType, qty, drugPrice, strength, genericName)) {
                jsonResponse.setData(patientProfileService.getSearchesRecordList(patientProfile.getPatientProfileSeqNo()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS_MESSEGE);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info("Invalid security token.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to delete saved searches made by patient
     *
     * @param securityToken
     * @param drugSearchId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/clearDrugSearchesWs", produces = "application/json", method = RequestMethod.POST)
    public Object deleteDrugSearchesById(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestParam(value = "drugSearchId", required = false) Integer drugSearchId) throws IOException {
        logger.info("Security Token is: " + securityToken + " DrugSearchId is: " + drugSearchId);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            if (patientProfileService.deleteDrugSearchesById(drugSearchId)) {
                jsonResponse.setData(patientProfileService.getSearchesRecordList(patientProfile.getPatientProfileSeqNo()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Saved search deleted successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_DELETE_MEGGAGE);
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to delete all the saved searches
     *
     * @param securityToken
     * @param profileId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/clearAllDrugSearchesWs", produces = "application/json", method = RequestMethod.POST)
    public Object clearAllDrugSearchesWs(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestParam(value = "id", required = false) Integer profileId) throws IOException {
        logger.info("Security Token is: " + securityToken + " ProfileId is: " + profileId);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            if (patientProfileService.deleteAllDrugSearchesRecordByProfileId(profileId)) {
                jsonResponse.setData(patientProfileService.getSearchesRecordList(profileId));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Saved search deleted successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_DELETE_MEGGAGE);
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info("Invalid security token.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to record blood glucose level of patient
     *
     * @param securityToken
     * @param glucoseLevel
     * @param readingTime
     * @param type
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addBloodGlucoseResultWs", produces = "application/json", method = RequestMethod.POST)
    public Object saveBloodGlucoseResultWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "glucoseLevel", required = false) String glucoseLevel,
            @RequestParam(value = "readingTime", required = false) String readingTime,
            @RequestParam(value = "type", required = false) String type) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " glucoseLevel is: " + glucoseLevel + " glucoseTime is: " + readingTime + " Type is: " + type);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            if (patientProfileService.saveBloodGlucoseResult(patientProfile.getPatientProfileSeqNo(), glucoseLevel, readingTime, type)) {
                RewardPoints rewardPoints = patientProfileService.getRewardPoints(Constants.BLOOD_GLUCOSE_ID);
                if (rewardPoints != null && rewardPoints.getId() != null && rewardPoints.getPoint() != null) {
                    logger.info("Reward Points is: " + rewardPoints.getPoint());
                    rewardPoints.setPoints(rewardPoints.getPoint().intValueExact());
                    jsonResponse.setData(rewardPoints);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(Constants.SUCCESS_MESSEGE);
                } else {
                    logger.info("Blood Glucose point not available against this id: " + Constants.BLOOD_GLUCOSE_ID);
                    jsonResponse.setData(rewardPoints);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage(Constants.SUCCESS_MESSEGE);
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_DELETE_MEGGAGE);
            }
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     *
     * @param profileId
     * @param mobileNumber
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/noThanksWs", produces = "application/json")
    public Object noThanksWs(@RequestParam(value = "profileId", required = false) Integer profileId,
            @RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Profile Id is: " + profileId + " mobileNumber is: " + mobileNumber);

        PatientProfile patientProfile = patientProfileService.getPatientProfileById(profileId);
        if (!isProfileInfoEmpty(patientProfile, jsonResponse)) {
            return objectMapper(objectMapper, jsonResponse);
        }
        logger.info("Mobile# " + patientProfile.getMobileNumber());
        if (!patientProfile.getMobileNumber().equals(EncryptionHandlerUtil.getEncryptedString(mobileNumber))) {
            logger.info("Mobile number does not match");
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.MOBILE_NUMBER_NOT_EXIST);
            return objectMapper(objectMapper, jsonResponse);
        }
        patientProfile.setSecurityToken(RedemptionUtil.getRandomToken());
        patientProfile.setStatus(Constants.COMPLETED);
        if (patientProfileService.update(patientProfile)) {
            RewardPoints rewardPoints = patientProfileService.getRewardPoints(Constants.Reward_Points_Id);
            setRewardPoints(rewardPoints, profileId);
            PatientProfileDTO profileDTO = getPatientProfileDTO(patientProfile);
            profileDTO.setPoints(rewardPoints.getPoint().intValueExact());
            jsonResponse.setData(profileDTO);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_UPDATE_RECORD);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private PatientProfileDTO getPatientProfileDTO(PatientProfile patientProfile) {
        PatientProfileDTO profileDTO = new PatientProfileDTO();
        profileDTO.setProfileId(patientProfile.getPatientProfileSeqNo());
        profileDTO.setMobileNumber(EncryptionHandlerUtil.getDecryptedString(patientProfile.getMobileNumber()));
        profileDTO.setSecurityToken(patientProfile.getSecurityToken());
        return profileDTO;
    }

    /**
     * This function is used to delete rx transfer record
     *
     * @param securityToken
     * @param transferId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteRxTransferRecordWs", produces = "application/json", method = RequestMethod.POST)
    public Object deleteRxTransferRecordWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "transferId", required = false) String transferId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("SecurityToken is:: " + securityToken + " TransferId is:: " + transferId);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile == null || patientProfile.getPatientProfileSeqNo() == null) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
            return objectMapper(objectMapper, jsonResponse);
        }
        if (CommonUtil.isNullOrEmpty(transferId)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("TransferId is null");
            logger.info("TransferId is null");
            return objectMapper(objectMapper, jsonResponse);
        }
        logger.info("Profile Id is:: " + patientProfile.getPatientProfileSeqNo());
        if (patientProfileService.deleteRxTransferRecord(Integer.parseInt(transferId), patientProfile.getPatientProfileSeqNo())) {
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info(Constants.SUCCESS);
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.FAILURE);
            logger.info(Constants.FAILURE);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to set reward points for a shared message with some
     * one.
     *
     * @param securityToken
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/shareWs", produces = "application/json", method = RequestMethod.POST)
    public Object share(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException, Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("SecurityToken is:: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            RewardPoints rp = patientProfileService.getRewardPoints(Constants.SHARE_WITH_FRIEND_RP_ID);
            if (patientProfileService.saveRewardHistory(rp.getType(), rp.getPoint().intValueExact(), patientProfile.getPatientProfileSeqNo(), Constants.PLUS)) {
                jsonResponse.setErrorCode(1);
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//                orderDetailDTO.setSharePoints(rp.getPoint().intValueExact());
                patientProfileService.getOrderRewardDetail(patientProfile.getPatientProfileSeqNo(), orderDetailDTO);
                jsonResponse.setData(orderDetailDTO);
                jsonResponse.setErrorMessage(Constants.SUCCESS);
                logger.info(Constants.SUCCESS);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.FAILURE);
                logger.info(Constants.FAILURE);
            }
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to confirm rx order
     *
     * @param transferno
     * @param securityToken
     * @return
     */
    @RequestMapping(value = "/confirmOrderWs/{transferno}", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object confirmOrderWs(@PathVariable("transferno") String transferno,
            @RequestHeader(value = "securityToken", required = false) String securityToken) {
        JsonResponse jsonResponse = new JsonResponse();
        //ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            Order order = this.setupService.saveOrderByTransferDetail(transferno, patientProfile);
            if (order != null) {
                jsonResponse.setData("Order ID " + order.getId());
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS);
            }

        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return jsonResponse;
    }

    /**
     * This function is used to get Notifications
     *
     * @param securityToken
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getNonArchivedNotificationWs", produces = "application/json")
    public @ResponseBody
    Object getNonArchivedNotification(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            logger.info("Profile Id is: " + patientProfile.getPatientProfileSeqNo());
             logger.info("Start..Going to get data from Database for Controller->getNotificationWs"+new Date());
            jsonResponse.setData(patientProfileService.getAllNotificationMessges(patientProfile.getPatientProfileSeqNo(),0));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Sucess"+new Date());
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return jsonResponse;
    }
    
        @RequestMapping(value = "/getArchivedNotificationWs", produces = "application/json")
    public @ResponseBody
    Object getArchivedNotification(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            logger.info("Profile Id is: " + patientProfile.getPatientProfileSeqNo());
             logger.info("Start..Going to get data from Database for Controller->getNotificationWs"+new Date());
            jsonResponse.setData(patientProfileService.getAllNotificationMessges(patientProfile.getPatientProfileSeqNo(),1));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Sucess"+new Date());
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return jsonResponse;
    }
    
        @RequestMapping(value = "/getNotificationWithPaginationWs",params = { "skip", "limit" }, produces = "application/json")
    public @ResponseBody
    Object getNotificationWithPagination(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam( "skip" ) int skip, @RequestParam( "limit" ) int limit,
            UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            logger.info("Profile Id is: " + patientProfile.getPatientProfileSeqNo());
             logger.info("Start..Going to get data from Database for Controller->getNotificationWs"+new Date());
            jsonResponse.setData(patientProfileService.getNotificationPaginationWs(patientProfile.getPatientProfileSeqNo(), skip , limit));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Sucess"+new Date());
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return jsonResponse;
    }

    ////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/getWaitingResponseNotificationWs", produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getWaitingResponseNotificationWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            logger.info("Security Token is: " + securityToken);
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                logger.info("Profile Id is: " + patientProfile.getPatientProfileSeqNo());
                jsonResponse.setData(patientProfileService.getNotificationForWaitingResponsesWs(patientProfile.getPatientProfileSeqNo()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS);
                logger.info("Sucess");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
                logger.info(Constants.INVALID_SECURITY_TOKEN);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResponse;
    }
    ///////////////////////////////////////////////////////////////////

    /**
     * This is function is used to get specific message
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getNotificationMessagesTextByIdWs", produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getNotificationMessagesTextByIdWs(@RequestParam(value = "id", required = false) Integer id) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("Id: " + id);
        jsonResponse.setData(patientProfileService.getNotificationMessagesTextById(id));
        jsonResponse.setErrorCode(1);
        jsonResponse.setErrorMessage(Constants.SUCCESS);
        logger.info("Sucess");
        return jsonResponse;
    }

    @RequestMapping(value = "/getAtDoctorTextWs", produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getAtDoctorTextWs() throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        //  jsonResponse.setData(Constants.AT_DOCTOR_TEXT);
        Calendar now = Calendar.getInstance();
        int cruntYear = now.get(Calendar.YEAR);
        String yearInString = "" + cruntYear;
        String drText = (Constants.AT_DOCTOR_TEXT);
        String replaceString = drText.replace("[year]", yearInString);
        jsonResponse.setData(replaceString);
        jsonResponse.setErrorCode(1);
        jsonResponse.setErrorMessage(Constants.SUCCESS);
        logger.info("Sucess");

        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * String s1="my name is khan my name is java"; String
     * replaceString=s1.replace("is","was");//replaces all occurrences of "is"
     * to "was" System.out.println(replaceString); Calendar now =
     * Calendar.getInstance(); int year = now.get(Calendar.YEAR); String
     * yearInString = String.valueOf(year); This function is used to send email
     * to patient by request
     *
     * @param id
     * @param email
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/sendEmailWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object sendEmailWs(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "email", required = false) String email) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        if (!APIValidationUtil.validateEmail(email, jsonResponse)) {
            return objectMapper(objectMapper, jsonResponse);
        }
        List<NotificationMessages> messagesesList = patientProfileService.getNotificationMessagesListById(id);
        if (CommonUtil.isNullOrEmpty(messagesesList)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is not record found.");
            return objectMapper(objectMapper, jsonResponse);
        }
        NotificationMessages notificationMessages = messagesesList.get(0);
        if (AppUtil.getSafeStr(notificationMessages.getSubject(), "").equalsIgnoreCase(Constants.MSG_ORDER_RECEIPT)) {
            String message = "Please <a href='Order_Receipt_Url'>Click here</a> to view your order receipt.";
            String orderReceiptUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/orderReceipt/" + id;
            message = message.replaceAll("Order_Receipt_Url", orderReceiptUrl);
            notificationMessages.setMessageText(message);
            notificationMessages.setSubject("Order Recevied From Order No " + notificationMessages.getOrderId());
        }
        if (EmailSenderUtil.send(email, notificationMessages.getSubject(), notificationMessages.getMessageText())) {
            isPatientMsgResponse(id);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.EMAIL_SEND_SUCCESS_MESSEGE);
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.EMAIL_SEND_FAILURE_MESSEGE);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private boolean validateEmail(String email, JsonResponse jsonResponse) throws IOException {
        if (CommonUtil.isNotEmpty(email)) {
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Enter valid Phone#/Email address" + " " + email);
                return false;
            }
        }
        return true;
    }

    /**
     * This function is used to process refill for a drug
     *
     * @param securityToken
     * @param json
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/refillModuleWs", produces = "application/json")
    public @ResponseBody
    Object saveRefillModule(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws IOException {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            logger.info("Security Token is: " + securityToken);
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                logger.info(Constants.INVALID_SECURITY_TOKEN);
                return objectMapper(objectMapper, jsonResponse);
            }
            BaseDTO baseDTO = objectMapper.readValue(json, BaseDTO.class);
            if (CommonUtil.isNullOrEmpty(baseDTO.getOrderId())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order id is null");
                logger.info("Order ChainId is null" + baseDTO.getOrderId());
                return objectMapper(objectMapper, jsonResponse);
            }
            jsonResponse.setData(patientProfileService.saveRefillModule(0l, baseDTO.getOrderId()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            return objectMapper(objectMapper, jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/refillRequestWs", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    Object saveBatchRefillModule(@RequestHeader(value = "securityToken") String securityToken,
            @RequestBody String lstOrder) throws IOException {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info("Security Token is: " + securityToken);
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                logger.info(Constants.INVALID_SECURITY_TOKEN);
                return objectMapper(objectMapper, jsonResponse);
            }
            if (lstOrder == null) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order list is null");
                logger.info("Order list is null");
                return objectMapper(objectMapper, jsonResponse);
            }
            /////////////////////////////////////////////////////////////////////
            JSONObject jsonObj = new JSONObject(lstOrder);

            JSONArray jsonArray = jsonObj.getJSONArray("lstOrder");

            //then get the type for list and parse using gson as
            Type listType = new TypeToken<List<OrderDetailDTO>>() {
            }.getType();
            List<OrderDetailDTO> lstOrder2 = new Gson().fromJson(jsonArray.toString(), listType);
            ////////////////////////////////////////////////////////////////////

            Set<OrderDetailDTO> batchOrders = patientProfileService.saveRefillModule(lstOrder2, patientProfile.getPatientProfileSeqNo());
            for (OrderDetailDTO order : batchOrders) {
                String pharmacyComments = "", brandName = "", genericName = "";
                CampaignMessages dbCampaignMessages = patientProfileService.getNotificationMsgs(Constants.MSG_CONTEXT_REFILL_REQUEST_RECEIVED, Constants.RX_ORDER);
                if (dbCampaignMessages != null && dbCampaignMessages.getMessageId() != null) {
                    logger.info("Order Drug Name is:: " + order.getDrugName());
                    CampaignMessages campaignMessages = CommonUtil.populateCampaignMessages(dbCampaignMessages);
//                    String[] arr = AppUtil.getBrandAndGenericFromDrugName(order.getDrugName());
//                    if (arr != null) {
//                        if (arr.length == 2) {
//                            brandName = arr[0];
//                            genericName = arr[1];
//                        } else {
//                            brandName = arr[0] + "(" + Constants.BRAND_NAME_ONLY + ")";
//                        }
//                    }
                    String drugName = AppUtil.getProperDrugName(order.getDrugName(), AppUtil.getSafeStr(
                            order.getDrugType(), ""), AppUtil.getSafeStr(order.getStrength(), ""));
                    String subject = campaignMessages.getSubject();
                    campaignMessages.setSubject(subject);
                    String message = dbCampaignMessages.getSmstext();
                    campaignMessages.setSmstext(message.replace(PlaceholderEnum.DATE_TIME.getValue(), DateUtil.dateToString(new Date(), Constants.USA_DATE_TIME_FORMATE))
                            .replace(PlaceholderEnum.REQUEST_NO.getValue(), AppUtil.getSafeStr(order.getId(), ""))
                            .replace(PlaceholderEnum.GeNERIC_NAME.getValue(), genericName)
                            .replace(PlaceholderEnum.DRUG_NAME.getValue(), drugName).replace(PlaceholderEnum.DRUG_STRENGTH.getValue(), order.getStrength())
                            .replace(PlaceholderEnum.DRUG_TYPE.getValue(), order.getDrugType()).replace(PlaceholderEnum.DRUG_QTY.getValue(), order.getQty())
                            .replace(PlaceholderEnum.DAYS_SUPPLY.getValue(), order.getDaysSupply() > 0 ? "" + order.getDaysSupply() : "Not mentioned")
                            .replace(PlaceholderEnum.REFILL_REMAINING.getValue(), order.getRefillsRemaining() != null ? order.getRefillsRemaining() : "Not mentioned")
                            .replace(PlaceholderEnum.PHARMACY_COMMENTS.getValue(), pharmacyComments));
                    String pushNot = AppUtil.getSafeStr(campaignMessages.getPushNotification(), "");
                    System.out.println("GOING TO CALL getMessageSubjectWithprocessedPlaceHolders PUSH NOT is " + pushNot);
                    campaignMessages.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(pushNot, order.getId(), null));
                    patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo(), order.getId());
                }
                //startSMSAndEmailSendThread(order.getId(), order.getDrugName(), order.getStrength(), order.getQty(), order.getDrugType(), "");
            }
            jsonResponse.setData(batchOrders);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            return objectMapper(objectMapper, jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * This function is used to get total order count
     *
     * @param securityToken
     * @param patientId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getTotalCountWs", produces = "application/json")
    public @ResponseBody
    Object getActiveOrderWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }

        jsonResponse.setData(patientProfileService.getOrderCount(patientProfile.getPatientProfileSeqNo()));
        jsonResponse.setErrorCode(1);
        jsonResponse.setErrorMessage(Constants.SUCCESS);
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to update prescriber
     *
     * @param orderId
     * @param prescriberName
     * @param prescriberPhone
     * @param prescriberNPI
     * @param securityToken
     * @param notificationMsgId
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws Exception
     */
    @RequestMapping(value = "/updatePrescriberWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updatePrescriber(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "prescriberName", required = false) String prescriberName,
            @RequestParam(value = "prescriberPhone", required = false) String prescriberPhone,
            @RequestParam(value = "prescriberNPI", required = false) String prescriberNPI,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException, ParseException, Exception {

        try {

            JsonResponse jsonResponse = new JsonResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            int isSaved = 0;
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }

            String orderStatusName = this.patientProfileService.getOrderStatusName(orderId);
            if (orderStatusName.equalsIgnoreCase("Shipped") || orderStatusName.equalsIgnoreCase("Filled") || orderStatusName.equalsIgnoreCase("DELIVERY")
                    || orderStatusName.equalsIgnoreCase("Cancelled")) {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Prescriber can't be updated now since its status is " + orderStatusName);
                return objectMapper(objectMapper, jsonResponse);
            }
            isSaved = consumerRegistrationService.updatePrescriber(orderId, prescriberName, prescriberPhone, prescriberNPI);

            if (isSaved == 1) {
                isPatientMsgResponse(notificationMsgId);
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/acceptLowerCostWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object acceptLowerCost(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException, ParseException, Exception {
        logger.info("orderId# " + orderId + " securityToken# " + securityToken + " notificationMsgId# " + notificationMsgId);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            int isSaved = 0;
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            String orderStatusName = this.patientProfileService.getOrderStatusName(orderId);
            if (orderStatusName.equalsIgnoreCase("Shipped") || orderStatusName.equalsIgnoreCase("Pickup At Pharmacy") || orderStatusName.equalsIgnoreCase("Filled") || orderStatusName.equalsIgnoreCase("DELIVERY")
                    || orderStatusName.equalsIgnoreCase("Cancelled")) {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Information can't be updated now since its status is " + orderStatusName);
                return objectMapper(objectMapper, jsonResponse);
            }
            isSaved = consumerRegistrationService.savePatientResponseAttributesForOrder(orderId,
                    Constants.PATIENT_RESPONSES.ACCEPTED_LOWER_COST);

            if (isSaved == 1) {
                isPatientMsgResponse(notificationMsgId);
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("Problem Occurred " + e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }

//        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/acceptLowerCostLaterWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object acceptLowerCostLater(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException, ParseException, Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            int isSaved = 0;
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            String orderStatusName = this.patientProfileService.getOrderStatusName(orderId);
            if (orderStatusName.equalsIgnoreCase("Shipped") || orderStatusName.equalsIgnoreCase("Pickup At Pharmacy") || orderStatusName.equalsIgnoreCase("Filled") || orderStatusName.equalsIgnoreCase("DELIVERY")
                    || orderStatusName.equalsIgnoreCase("Cancelled")) {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Information can't be updated now since its status is " + orderStatusName);
                return objectMapper(objectMapper, jsonResponse);
            }
            isSaved = consumerRegistrationService.savePatientResponseAttributesForOrder(orderId,
                    Constants.PATIENT_RESPONSES.ACCEPT_LOWER_COST_LATER);

            if (isSaved == 1) {
                isPatientMsgResponse(notificationMsgId);
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("Problem Occurred " + e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }

//        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/preAuthorizeRefillWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object preAuthorizeRefill(
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json
    ) throws IOException, ParseException, Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            int isSaved = 0;
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            BaseDTO baseDTO = objectMapper.readValue(json, BaseDTO.class);
            String orderStatusName = this.patientProfileService.getOrderStatusName(baseDTO.getOrderId());
            if (orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.SHIPPED) || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.PICKUP_AT_PHARMACY)
                    || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING) || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.DELIVERY)
                    || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.CANCELLED) || orderStatusName.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING_QUESTION)) {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Information can't be updated now since its status is " + orderStatusName);
                return objectMapper(objectMapper, jsonResponse);
            }
            isSaved = consumerRegistrationService.savePatientResponseAttributesForOrder(baseDTO.getOrderId(),
                    Constants.PATIENT_RESPONSES.PRE_AUTHORIZE_REFILL);

            if (isSaved == 1) {
                isPatientMsgResponse(baseDTO.getNotificationMsgId());
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("Problem Occurred " + e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }

//        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/distcontinueFutureMessagesWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object distcontinueFutureMessages(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException, ParseException, Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            int isSaved = 0;
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            String orderStatusName = this.patientProfileService.getOrderStatusName(orderId);
            if (orderStatusName.equalsIgnoreCase("Shipped") || orderStatusName.equalsIgnoreCase("Pickup At Pharmacy") || orderStatusName.equalsIgnoreCase("Filled") || orderStatusName.equalsIgnoreCase("DELIVERY")
                    || orderStatusName.equalsIgnoreCase("Cancelled")) {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Information can't be updated now since its status is " + orderStatusName);
                return objectMapper(objectMapper, jsonResponse);
            }
            isSaved = consumerRegistrationService.savePatientResponseAttributesForOrder(orderId,
                    Constants.PATIENT_RESPONSES.DISCONTINUE_FUTURE_MESSAGES);

            if (isSaved == 1) {
                isPatientMsgResponse(notificationMsgId);
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("Problem Occurred " + e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }

//        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/continueWaitWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object continueWait(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException, ParseException, Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            int isSaved = 0;
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            String orderStatusName = this.patientProfileService.getOrderStatusName(orderId);
            if (orderStatusName.equalsIgnoreCase("Shipped") || orderStatusName.equalsIgnoreCase("Pickup At Pharmacy") || orderStatusName.equalsIgnoreCase("Filled") || orderStatusName.equalsIgnoreCase("DELIVERY")
                    || orderStatusName.equalsIgnoreCase("Cancelled")) {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Information can't be updated now since its status is " + orderStatusName);
                return objectMapper(objectMapper, jsonResponse);
            }
            isSaved = consumerRegistrationService.savePatientResponseAttributesForOrder(orderId,
                    Constants.PATIENT_RESPONSES.CONTINUE_WAIT);

            if (isSaved == 1) {
                isPatientMsgResponse(notificationMsgId);
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("Problem Occurred " + e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }

//        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/changeOrderStatusWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object changeOrderStatus(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "orderStatusId", required = false) Integer orderStatusId,
            @RequestParam(value = "comments", required = false) String comments,
            @RequestParam(value = "paymentMode", required = false) String paymentMode,
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException, ParseException, Exception {

        try {

            JsonResponse jsonResponse = new JsonResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            int isSaved = 0;

            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
            if (CommonUtil.isNullOrEmpty(orderId)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order id is null");
                return objectMapper(objectMapper, jsonResponse);
            }
            int oderIdSafeInteger = AppUtil.getSafeInt(orderId, 0);
            String commentsSafeString = AppUtil.getSafeStr(comments, "");
            OrderStatusDTO dto = this.patientProfileService.getOrderStatusDTO(orderId);
            String orderStatusName = dto != null ? AppUtil.getSafeStr(dto.getName(), "") : "";
            if (dto != null) {
                if (Objects.equals(dto.getId(), orderStatusId)) {
                    jsonResponse.setErrorMessage("Status is alreay " + dto.getName());
                    return objectMapper(objectMapper, jsonResponse);
                }
            }
            boolean IsEditableOrder = CommonUtil.isEditableOrder(orderStatusName);
            if (!IsEditableOrder) {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Status can't be update now since its status is " + orderStatusName);
                return objectMapper(objectMapper, jsonResponse);
            }
//            if (orderStatusName.equalsIgnoreCase("Fill As Cash")) {
//                jsonResponse.setErrorMessage("Status already " + orderStatusName);
//                return objectMapper(objectMapper, jsonResponse);
//            }
            if (orderStatusId == 12) {
                isSaved = patientProfileService.fillAsCash(oderIdSafeInteger, 0, orderStatusId, commentsSafeString, paymentMode);
            } else {
                isSaved = consumerRegistrationService.updateOrderStatusByPatient(oderIdSafeInteger, orderStatusId);
            }
            if (isSaved == 1) {
                isPatientMsgResponse(notificationMsgId);
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //////////////////////////////////////////////
    @RequestMapping(value = "/updateMFRWithOrderWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateMFRWithOrder(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestParam(value = "orderId", required = false) String orderId, @RequestParam(value = "copayCardDictionary", required = false) String copayCardDictionary) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("securityToken is:: " + securityToken + " orderId is :: " + orderId + " copayCardDictionary is :: " + copayCardDictionary);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (CommonUtil.isNullOrEmpty(orderId)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("OrderId is null or empty.");
            return objectMapper(objectMapper, jsonResponse);
        }

        logger.info("PatientProfile id is:: " + patientProfile.getPatientProfileSeqNo());
        return objectMapper(objectMapper, jsonResponse);
    }

    @RequestMapping(value = "/updateDeliveryPerformanceWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateDeliveryPerformance(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "addressId", required = false) String addressId,
            @RequestParam(value = "preferenceId", required = false) String deliveryPrefId,
            @RequestHeader(value = "securityToken", required = false) String securityToken, String paymentMode) throws IOException, ParseException, Exception {
        try {

            JsonResponse jsonResponse = new JsonResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            int isSaved = 0;
            PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                logger.info("Profile id is: " + patientProfile.getPatientProfileSeqNo());
                ///////////////////////////////////////////////////////////////////
                PatientDeliveryAddress address = patientProfileService.getPatientDeliveryAddressById(AppUtil.getSafeInt(addressId, 0));
                String fee = "0.0";
                BigDecimal feeNumeric = BigDecimal.ZERO;
                String prefName = "";
                DeliveryPreferences prefDetails = this.patientProfileService.getDeliveryPreferenceById(AppUtil.getSafeInt(deliveryPrefId, 0));
                if (prefDetails != null) {
                    prefName = prefDetails.getName();
                }
                if (AppUtil.getSafeStr(prefName, "").contains("2nd Day")) {
                    fee = "0.0";
                } else if (address != null) {
                    fee = patientProfileService.getZipCodeCalculations(address.getZip(), patientProfile.getPatientProfileSeqNo(), AppUtil.getSafeInt(deliveryPrefId, 0));
                    feeNumeric = CommonUtil.getStrToBigDecimal(fee);
                }

                String orderStatusName = this.patientProfileService.getOrderStatusName(orderId);
                boolean IsEditableOrder = CommonUtil.isEditableOrder(orderStatusName);
                if (IsEditableOrder) {
                    jsonResponse.setErrorMessage("Status can't be update now since its status is " + orderStatusName);
                    return objectMapper(objectMapper, jsonResponse);
                }

                isSaved = patientProfileService.updateDeleiveryPreferance(orderId, fee, addressId, deliveryPrefId);

                if (isSaved == 1) {
                    jsonResponse.setData(1);//patientPaymentInfo);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("Record has been updated successfully.");
                } else {
                    jsonResponse.setData(0);
                    jsonResponse.setErrorMessage("There is problem to update record.");
                }
                return objectMapper(objectMapper, jsonResponse);
            }
        } catch (Exception e) {
            logger.error("Exception:: updateDeliveryPerformance:: ", e);
        }
        return null;

    }

    @RequestMapping(value = "/updateOrderDeliveryTimeWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateDeliveryTime(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "dprefId", required = false) Integer dprefId,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "toTime", required = false) String deliveryTo,
            @RequestParam(value = "fromTime", required = false) String deliveryFrom,
            @RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException, ParseException, Exception {
        try {

            JsonResponse jsonResponse = new JsonResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            int isSaved = 0;

            PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                logger.info("Profile id is: " + patientProfile.getPatientProfileSeqNo());
                ///////////////////////////////////////////////////////////////////

                String orderStatusName = this.patientProfileService.getOrderStatusName(orderId);

                if (!orderStatusName.equalsIgnoreCase("Ready to Fill") && !orderStatusName.equalsIgnoreCase("Waiting Pt Response")) {
                    jsonResponse.setErrorMessage("Status can't be update now since its status is " + orderStatusName);
                    return objectMapper(objectMapper, jsonResponse);
                }

                isSaved = patientProfileService.updateDateTime(orderId, dprefId, date, deliveryTo, deliveryFrom);

                if (isSaved == 1) {
                    jsonResponse.setData(1);//patientPaymentInfo);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("Record has been updated successfully.");
                } else {
                    jsonResponse.setData(0);
                    jsonResponse.setErrorMessage("There is problem to update record.");
                }
                return objectMapper(objectMapper, jsonResponse);
            }
        } catch (Exception e) {
            logger.error("Exception::updateDeliveryTime ", e);
        }
        return null;

    }

    @RequestMapping(value = "/stopRefillReminderWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object stopRefillReminder(
            @RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws IOException, ParseException, Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //validate securityToken
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }

            BaseDTO baseDTO = objectMapper.readValue(json, BaseDTO.class);
            logger.info("Profile id is: " + patientProfile.getPatientProfileSeqNo());
            ///////////////////////////////////////////////////////////////////

            int isSaved = patientProfileService.updatestopReminder(baseDTO.getOrderId(), 1);

            if (isSaved == 1) {
                isPatientMsgResponse(baseDTO.getNotificationMsgId());
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record has been updated successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }

        } catch (Exception e) {
            logger.error("Exception::stopRefillReminder ", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
        }
        return objectMapper(objectMapper, jsonResponse);

    }

    @RequestMapping(value = "/downloadYearEndStatementWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object downloadYearEndStatement(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "yearEndStatementPdfFile", required = false) String yearEndStatementPdfFile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("securityToken:: " + securityToken + " yearEndStatementPdfFile:: " + yearEndStatementPdfFile);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (CommonUtil.isNullOrEmpty(yearEndStatementPdfFile)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("File name is null.");
            return objectMapper(objectMapper, jsonResponse);
        }
        if (!CommonUtil.isFileExist(PropertiesUtil.getProperty("INSURANCE_CARD_PATH") + yearEndStatementPdfFile)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("File doest not exist.");
            return objectMapper(objectMapper, jsonResponse);
        }
        try (OutputStream out = response.getOutputStream()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + yearEndStatementPdfFile + "\"");
            CommonUtil.downloadFile(PropertiesUtil.getProperty("INSURANCE_CARD_PATH") + yearEndStatementPdfFile, out);
            out.flush();
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * this function is used to send email to patient
     *
     * @param securityToken
     * @param subject
     * @param message
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/sendEmailsWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object sendEmailsWs(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestParam(value = "subject", required = false) String subject, @RequestParam(value = "message", required = false) String message) throws IOException {
        logger.info("securityToken:: " + securityToken + " subject:: " + subject + " message:: " + message);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        if (CommonUtil.isNotEmpty(patientProfile.getEmailAddress())) {
            String email = EncryptionHandlerUtil.getDecryptedString(patientProfile.getEmailAddress());
            logger.info("Email Address:: " + email);
            if (!validateEmail(email, jsonResponse)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            boolean isEmailSend = EmailSenderUtil.send(email, subject, message);
            logger.info("isEmailSend# " + isEmailSend);
            if (isEmailSend) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.EMAIL_SEND_SUCCESS_MESSEGE);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.EMAIL_SEND_FAILURE_MESSEGE);
            }

        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Patient email address is empty.");
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    /**
     * This function is used to get drug information by drugBrandName
     *
     * @param drugName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getCopyDrugLookUpWs", produces = "application/json")
    public @ResponseBody
    Object getCopyDrugLookUpList(@RequestParam(value = "drugBrandName", required = false) String drugName) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("Drug Name is: " + drugName);
        if (CommonUtil.isNullOrEmpty(drugName)) {
            logger.info("Drug Name is empty: " + drugName);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Drug Name is empty.");
            return jsonResponse;
        }
        Set<DrugBrandDTO> list = patientProfileService.getDrugBrandsOnlyList(drugName);
        jsonResponse.setData(list);
        jsonResponse.setErrorCode(1);
        jsonResponse.setErrorMessage(Constants.SUCCESS);
        logger.info("Success");
        return jsonResponse;
    }

    /**
     * This function is used to add Insurance Card information
     *
     * @param securityToken
     * @param cardHolderRelation
     * @param memberId
     * @param cardId
     * @param insuranceProvider
     * @param groupNumber
     * @param planId
     * @param providerPhone
     * @param providerAddress
     * @param expiryDate
     * @param createdOn
     * @param updatedOn
     * @param isPramiry
     * @param effectiveDate
     * @param insuranceCardFront
     * @param insuranceCardBack
     * @param notificationMsgId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addInsuranceCard1Ws", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addInsuranceCard1Ws(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "dependentId", required = false) Integer dependentId,
            @RequestParam(value = "cardHolderRelation", required = false) String cardHolderRelation,
            @RequestParam(value = "memberId", required = false) String memberId,
            @RequestParam(value = "cardId", required = false) Long cardId,
            @RequestParam(value = "insuranceProvider", required = false) String insuranceProvider,
            @RequestParam(value = "groupNumber", required = false) String groupNumber,
            @RequestParam(value = "planId", required = false) String planId,
            @RequestParam(value = "providerPhone", required = false) String providerPhone,
            @RequestParam(value = "providerAddress", required = false) String providerAddress,
            @RequestParam(value = "expiryDate", required = false) String expiryDate,
            @RequestParam(value = "createdOn", required = false) String createdOn,
            @RequestParam(value = "updatedOn", required = false) String updatedOn,
            @RequestParam(value = "isPramiry", required = false) Integer isPramiry,
            @RequestParam(value = "effectiveDate", required = false) String effectiveDate,
            @RequestParam(value = "insuranceFrontCardPath", required = false) MultipartFile insuranceCardFront,
            @RequestParam(value = "insuranceBackCardPath", required = false) MultipartFile insuranceCardBack,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId) throws IOException {
        logger.info("SecurityToken: " + securityToken + " CardHolderRelation: Integer" + cardHolderRelation + " insuranceCardFront: " + insuranceCardFront + " insuranceCardBack: " + insuranceCardBack);
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientInsuranceDetails insuranceDetails = new PatientInsuranceDetails();

        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(objectMapper, jsonResponse);
            }
//            String[] eDate = effectiveDate.split("-");
//            effectiveDate = eDate[1] + "-" + eDate[0] + "-01";
            //Long longCardId = Long.parseLong(cardId);
            byte[] decodedFrontCard = null, decodedBackCard = null;
            String imgFcPath = null, imgBcPath = null;
            if (insuranceCardFront != null && (!insuranceCardFront.isEmpty())) {
                //decodedFrontCard = org.apache.commons.codec.binary.Base64.decodeBase64(insuranceCardFront.getBytes());
                imgFcPath = CommonUtil.saveInsuranceCard(insuranceCardFront, patientProfile.getPatientProfileSeqNo(), "InsuranceFrontCard");
                logger.info("decodedFrontCard Path: " + imgFcPath);
                if (!CommonUtil.urlAuthorization(imgFcPath)) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Error in saving card please try again.");
                    return jsonResponse;
                }
            }

            if (insuranceCardBack != null && (!insuranceCardBack.isEmpty())) {
                imgBcPath = CommonUtil.saveInsuranceCard(insuranceCardBack, patientProfile.getPatientProfileSeqNo(), "InsuranceBackCard");
                logger.info("decodedBackCard path: " + imgBcPath);
                if (!CommonUtil.urlAuthorization(imgFcPath)) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Error in saving card please try again.");
                    return jsonResponse;
                }
            }
            if (patientProfileService.addInsuranceCard(patientProfile, cardHolderRelation,
                    decodedFrontCard, decodedBackCard, imgFcPath, imgBcPath, memberId, cardId, planId,
                    effectiveDate, expiryDate, createdOn, updatedOn, insuranceProvider,
                    groupNumber, providerPhone, providerAddress, isPramiry, dependentId, jsonResponse)) {
                logger.info("Record has been added successfully.");
                isPatientMsgResponse(notificationMsgId);
                insuranceDetails.setCardHolderRelationship(cardHolderRelation);
                insuranceDetails.setInsuranceFrontCardPath(imgFcPath);
                insuranceDetails.setInsuranceBackCardPath(imgBcPath);
                jsonResponse.setData(insuranceDetails);

            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update record.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception -> getPatientProfileByToken", e);
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("There is problem to update record." + e.getMessage());
        }

        return jsonResponse;
    }

    /**
     * @author arsalan.ahmad
     * @param securityToken
     * @param heading
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/loadPatientPreferencesWs", produces = "application/json")
    public @ResponseBody
    Object loadPatientPreferences(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "heading", required = false) String heading) throws IOException {
        logger.info("SecurityToken: " + securityToken + " heading: " + heading);
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<PatientPreferencesDTO> preferences = null;
            if (preferences != null && preferences.size() > 0) {
                jsonResponse.setData(preferences);
                jsonResponse.setErrorCode(1);
                jsonResponse.setTotalRecords(preferences.size());
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to find preferences.");
            }

        } catch (Exception e) {
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("There is problem to fetch preferences.");
            logger.error("Exception -> loadPatientPreferences", e);
        }
        return jsonResponse;
    }

    /**
     * @author arsalan.ahmad
     * @param securityToken
     * @param preferenceSettingId
     * @param preferenceValue
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/savePatientPreferenceWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object savePatientPreference(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "preferenceSettingId", required = false) Integer preferenceSettingId, @RequestParam(value = "preferenceValue", required = false) boolean preferenceValue) throws IOException {
        logger.info("SecurityToken: " + securityToken + " PreferenceSettingId: " + preferenceSettingId + " PreferenceValue: " + preferenceValue);
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            boolean response = patientProfileService.savePatientPreference(patientProfileService.getPatientProfileByToken(securityToken).getPatientProfileSeqNo(), preferenceSettingId, preferenceValue);
            if (response) {
                jsonResponse.setData(response);
                jsonResponse.setErrorCode(1);
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to save preference.");
            }

        } catch (Exception e) {
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("There is problem to save preferences.");
            logger.error("Exception -> savePatientPreference", e);
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/addResponseToNotificationMessageWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addResponseToNotificationMessage(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "responseText", required = false) String responseText, @RequestParam(value = "messageId", required = false) Integer messageId, @RequestParam(value = "orderId", required = false) String orderId) {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            boolean result = patientProfileService.addResponseToNotificationMessage(responseText, patientProfile.getPatientProfileSeqNo(), messageId, orderId);

            if (result) {
                jsonResponse.setData(result);
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("Response to Notification Messages cannot be added.");
            }
        } catch (Exception e) {
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("Response to Notification Messages cannot be added.");
            logger.error("Exception -> addResponseToNotificationMessage", e);
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/updatePOAWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updatePOAWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "dependentId", required = false) Integer dependentId,
            @RequestParam(value = "frontPOAImage", required = false) MultipartFile frontPOAImage,
            @RequestParam(value = "backPOAImage", required = false) MultipartFile backPOAImage,
            @RequestParam(value = "expiryDate", required = false) String expiryDate) {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("securityToken is: " + securityToken);
        System.out.println("securityToken is: " + securityToken);
        String dateStr = "";
        String frontPOAImageUrl = "";
        String backPOAImageUrl = "";
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            Date date = new Date();
            dateStr = DateUtil.dateToString(date, "yy-MM-dd hh:mm:ss");
            dateStr = dateStr.replace(":", "-");
            dateStr = dateStr.replace(" ", "-");

            String ext = FileUtil.determineImageFormat(frontPOAImage.getBytes());
            frontPOAImageUrl = "Img_" + patientProfile.getPatientProfileSeqNo() + "_" + dateStr + "." + ext;

            String extt = FileUtil.determineImageFormat(frontPOAImage.getBytes());
            backPOAImageUrl = "Img_" + patientProfile.getPatientProfileSeqNo() + "_" + dateStr + "." + extt;

            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {

                jsonResponse.setData(1);
                jsonResponse.setErrorMessage("Data has been updated.");
            }
        } catch (Exception e) {
            jsonResponse.setData(0);
            jsonResponse.setErrorMessage("There is problem to update data.");
            logger.error("Exception -> updatePOAWs", e);
        }
        return jsonResponse;

    }

    @RequestMapping(value = "/addCareTakerWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addCareTakerWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "adultFirstName", required = false) String adultFirstName,
            @RequestParam(value = "adultLastName", required = false) String adultLastName,
            @RequestParam(value = "expiryDate", required = false) String expiryDate,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "isApproved", required = false) Boolean isApproved,
            @RequestParam(value = "frontPOAImage", required = false) MultipartFile frontPOAImage,
            @RequestParam(value = "backPOAImage", required = false) MultipartFile backPOAImage,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "dob", required = false) String dob,
            @RequestParam(value = "allergies", required = false) String allergies,
            @RequestParam(value = "emailAddr", required = false) String emailAddr) {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("securityToken is: " + securityToken + " Gender is:  " + gender);
        System.out.println("securityToken is: " + securityToken);
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            if (CommonUtil.isNullOrEmpty(gender)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.GENDER_TYPE_REQ);
                return jsonResponse;
            }
            Date dateOfBirth = DateUtil.stringToDate(dob, "MM/dd/yyyy");
            logger.info("After Format Date Of Birth is: " + dateOfBirth);
            int years = DateUtil.getDiffYears(dateOfBirth, new Date());
            if (years < 18) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.INVALID_AGE);
                return jsonResponse;
            }
            String frontPOAUrl = null, backPOAUrl = null;

            if (frontPOAImage != null && (!frontPOAImage.isEmpty())) {
                frontPOAUrl = CommonUtil.saveInsuranceCard(frontPOAImage, patientProfile.getPatientProfileSeqNo(), "FrontPOAImage");
                logger.info("decodedFrontCard Path: " + frontPOAUrl);
                if (!CommonUtil.urlAuthorization(frontPOAUrl)) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Error in saving page 1 power of attorney.please try again.");
                    return jsonResponse;
                }
            }
            if (backPOAImage != null && (!backPOAImage.isEmpty())) {
                backPOAUrl = CommonUtil.saveInsuranceCard(backPOAImage, patientProfile.getPatientProfileSeqNo(), "BackPOAImage");
                logger.info("decodedFrontCard Path: " + backPOAUrl);
                if (!CommonUtil.urlAuthorization(backPOAUrl)) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Error in saving page 2 power of attorney/signature.please try again.");
                    return jsonResponse;
                }
            }

            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to add record.");

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to save record.");
            logger.error("Exception:: addCareTakerWs", e);
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/getPatientProfileWs", produces = "application/json")
    public @ResponseBody
    Object getPatientProfileWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        logger.info("securityToken is: " + securityToken);

        LoginDTO patientProfile = patientProfileService.getPatientProfileDetailByToken(securityToken);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Patient info is null.");
            return objectMapper(mapper, jsonResponse);
        }
        jsonResponse.setErrorCode(1);
        jsonResponse.setData(patientProfile);

        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/getPatientProfile2Ws", produces = "application/json")
    public @ResponseBody
    Object getPatientProfile2Ws(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        logger.info("securityToken is: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(mapper, jsonResponse);
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            PatientProfile profile = new PatientProfile();
            profile.setPatientProfileSeqNo(patientProfile.getPatientProfileSeqNo());
            profile.setFirstName(patientProfile.getFirstName());
            profile.setLastName(patientProfile.getLastName());
            profile.setMobileNumber(patientProfile.getMobileNumber());
            profile.setEmailAddress(patientProfile.getEmailAddress());
            profile.setSecurityToken(securityToken);
            profile.setDependentCount(patientProfile.getDependentCount());
            profile.setInsCardCount(patientProfile.getInsCardCount());
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(profile);
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Patient info is null.");
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/msgResponseWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object msgResponseWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "response", required = false) String response,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId,
            @RequestParam(value = "orderId", required = false) String orderId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        logger.info("securityToken is: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            patientProfileService.saveMessageResponses(patientProfile, orderId, notificationMsgId, response);
            jsonResponse.setErrorCode(1);
            //jsonResponse.setErrorMessage("Record has been added successfully.");
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Patient info is null.");
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/updateHandDeliveryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateHandDelivery(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException, ParseException, Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            int isSaved = 0;
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                System.out.println("securityToken not validated");
                return jsonResponse;
            }
            if (CommonUtil.isNullOrEmpty(orderId)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order id is null");
                return objectMapper(objectMapper, jsonResponse);
            }

//            if (orderStatusName.equalsIgnoreCase("Fill As Cash")) {
//                jsonResponse.setErrorMessage("Status already " + orderStatusName);
//                return objectMapper(objectMapper, jsonResponse);
//            }
            isSaved = this.consumerRegistrationService.updateHandDeliveryFlag(orderId);
            if (isSaved == 1) {
                jsonResponse.setData(1);//patientPaymentInfo);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Your request for order shipment is accepted.");
            } else {
                jsonResponse.setData(0);
                jsonResponse.setErrorMessage("There is problem to update the record.");
            }
            return objectMapper(objectMapper, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(0);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }

    }

    @RequestMapping(value = "/updateOrderPrimeryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updatePrimeryOrder(
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "quantity", required = false) String quantity,
            @RequestParam(value = "paymentId", required = false) Integer paymentId,
            @RequestParam(value = "deliveryPrefernceId", required = false) Integer deliveryPrefernceId,
            @RequestParam(value = "insuranceCardId", required = false) String insuranceCardId,
            @RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException, ParseException, Exception {
        //public Object getObjectById(Object obj, String id)
        PatientProfile profile = new PatientProfile();
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        int isSaved = 0;
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            isSaved = patientProfileService.savePrimeryOrders(orderId, paymentId, deliveryPrefernceId);

            if (isSaved == 1) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Order Updated successfully");
                profile.setSuccessOrFailure(Constants.SUCCESS);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
                return objectMapper(objectMapper, jsonResponse);
            }

        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);

    }

    ////////////////////////////////////////////////////////////
    @RequestMapping(value = "/generateDeviceToken", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object generateDeviceToken(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "deviceToken", required = false) String deviceToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        PatientProfile patientProfile = patientProfileService.createDeviceTokenForPatient(securityToken, deviceToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            jsonResponse.setData(patientProfileService.getFilledRxHistory(patientProfile.getPatientProfileSeqNo()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    @RequestMapping(value = "/getServerTimeWs", produces = "application/json")
    public @ResponseBody
    Object getServerTime(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            logger.info("Security Token: " + securityToken);
            PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                jsonResponse.setData(DateUtil.dateToString(new Date(), "MM/dd/yyyy hh:mm:ss"));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS);
            } else {
                CommonUtil.inValidSecurityToken(jsonResponse);
            }
            return objectMapper(objectMapper, jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setData(e.getMessage());
            return objectMapper(objectMapper, jsonResponse);
        }
    }

    ////////////////////////////////////////////////////////////
    @RequestMapping(value = "/updateOrderImagesWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateOrderImagesWs(@RequestHeader(value = "securityToken") String securityToken,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "video", required = false) MultipartFile video,
            @RequestParam(value = "drugImg", required = false) MultipartFile drugImg,
            @RequestParam(value = "drugImgs", required = false) List<MultipartFile> drugImgList,
            @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId,
            HttpServletRequest req) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("OrderId:: " + orderId + "Video" + video + " drugImgList Size is:: " + drugImgList.size());
        System.out.println("OrderId:: " + orderId + "Video" + video + " drugImgList Size is:: " + drugImgList.size());
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        String dateStr = "";
        String completeName = "";
        int isSaved = 0;
        try {
            Date date = new Date();
            dateStr = DateUtil.dateToString(date, "yy-MM-dd hh:mm:ss");
            dateStr = dateStr.replace(":", "-");
            dateStr = dateStr.replace(" ", "-");

            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                /////////////////////////////////////////////////
                String orderStatusName = AppUtil.getSafeStr(this.patientProfileService.getOrderStatusName(orderId), "");
                if (orderStatusName.equalsIgnoreCase("Shipped") || orderStatusName.equalsIgnoreCase("Pickup At Pharmacy") || orderStatusName.equalsIgnoreCase("Filled") || orderStatusName.equalsIgnoreCase("DELIVERY")
                        || orderStatusName.equalsIgnoreCase("Cancelled")) {
                    jsonResponse.setData(0);
                    jsonResponse.setErrorMessage("Information can't be updated now since its status is " + orderStatusName);
                    return objectMapper(objectMapper, jsonResponse);
                }
                /////////////////////////////////////////////////
                patientProfileService.deleteDrugImagesByOrderId(orderId);

                String videoPath = null, drugImagPath = null;
                String webappRoot = servletContext.getRealPath("/");
                String relativeFolder = File.separator + "resources" + File.separator
                        + "noinsurancecard" + File.separator;
                List<OrderTransferImages> orderTransferImagesList = new ArrayList<>();
                if (video != null && !video.isEmpty()) {
                    if (video.getBytes() != null) {
                        logger.info("video Format: " + video.getContentType());
                        String contentType = video.getContentType();
                        String[] contentArr = contentType.split("/");
                        String ext = "mp4"; //FileUtil.determineImageFormat(video.getBytes());
                        if (contentArr != null && contentArr.length > 1) {
                            ext = contentArr[1];
                        }
                        completeName = "Vid_" + patientProfile.getPatientProfileSeqNo() + "_" + dateStr + "." + ext;
                        File file = new File(webappRoot + relativeFolder + completeName);
                        videoPath = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "orderimages/" + completeName;
                        //                    videoPath = Constants.INSURANCE_CARD_PATH + video.getOriginalFilename() + patientProfile.getId() + "." + video.getContentType();
                        logger.info("Complete video Path: " + videoPath);
                        //                    FileCopyUtils.copy(video.getBytes(), new File(Constants.INSURANCE_CARD_PATH + video.getOriginalFilename()));
                        FileCopyUtils.copy(video.getBytes(),
                                new File(PropertiesUtil.getProperty("ORDER_IMAGES_CARD_PATH") + completeName));
                        CommonUtil.executeCommand(Constants.COMMAND);
                        completeName = videoPath;
                    }
                } else if (!CommonUtil.isNullOrEmpty(drugImgList)) {
                    completeName = uploadOrderTransferImages(drugImgList, completeName, patientProfile, dateStr, orderTransferImagesList);
                }
                if (drugImg != null && drugImg.getBytes() != null) {
                    logger.info("Drug Image Format: " + drugImg.getContentType());

                    String ext = FileUtil.determineImageFormat(drugImg.getBytes());
                    completeName = "Img_" + patientProfile.getPatientProfileSeqNo() + "_" + dateStr + "." + ext;
                    drugImagPath = PropertiesUtil.getProperty("INSURANCE_CARD_URL") + "orderimages/" + completeName;
                    logger.info("Complete Drug Image Path: " + drugImagPath);

                    FileCopyUtils.copy(drugImg.getBytes(),
                            new File(PropertiesUtil.getProperty("ORDER_IMAGES_CARD_PATH") + completeName));
                    CommonUtil.executeCommand(PropertiesUtil.getProperty("COMMAND"));
                    completeName = drugImagPath;
                }

                if (video != null) {
                    saveRxTransfer(orderId, completeName, jsonResponse, "", orderTransferImagesList);
                } else {
                    saveRxTransfer(orderId, "", jsonResponse, completeName, orderTransferImagesList);
                }

                isSaved = consumerRegistrationService.savePatientResponseAttributesForOrder(orderId,
                        Constants.PATIENT_RESPONSES.UPDATE_IMAGE_VIDEO);

                if (isSaved == 1) {
                    isPatientMsgResponse(notificationMsgId);
                    jsonResponse.setData(1);//patientPaymentInfo);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("Record has been updated successfully.");
                } else {
                    jsonResponse.setData(0);
                    jsonResponse.setErrorMessage("There is problem to update record.");
                }

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(PropertiesUtil.getProperty("INVALID_SECURITY_TOKEN"));
                logger.info(PropertiesUtil.getProperty("INVALID_SECURITY_TOKEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
            return jsonResponse;
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    private void isPatientMsgResponse(Integer notificationMsgId) {
        if (!CommonUtil.isNullOrEmpty(notificationMsgId)) {
            patientProfileService.updateNotificationMessages(notificationMsgId, Boolean.TRUE);
        }
    }

    private void saveRxTransfer(String orderId, String videoPath, JsonResponse jsonResponse, String drugImg, List<OrderTransferImages> orderTransferImageses) {

        jsonResponse.setErrorCode(0);
        jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
        logger.info(Constants.ERROR_SAVE_RECORD);

    }

    @RequestMapping(value = "/updatePasswordWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updatePassword() throws IOException {
//       PharmacyUser phUser = new PharmacyUser();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            consumerRegistrationService.getpasswordByPharmacyUserList();
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("fail");
            jsonResponse.setErrorMessage(e.getMessage());
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/updateCareTakerImageWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateCareTakerImge(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "careTakerId", required = false) Integer careTakerId,
            @RequestParam(value = "frontPOAImage", required = false) MultipartFile frontPOAImage,
            @RequestParam(value = "backPOAImage", required = false) MultipartFile backPOAImage) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("SecurityToken is: " + securityToken + " CareTakerId is: " + careTakerId);
        try {

            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                System.out.println("securityToken not validated");
                return jsonResponse;
            }
            if (CommonUtil.isNullOrEmpty(careTakerId)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Care Taker ID Required");
                return jsonResponse;
            }
            PatientDependantDTO dependantDTO = null;
            if (dependantDTO == null || CommonUtil.isNullOrEmpty(dependantDTO.getId())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("No care taker record found against this id " + careTakerId);
                return jsonResponse;
            }

            logger.info("patientDependantDTO.getArchived()# " + dependantDTO.getArchived());
            if (dependantDTO.getArchived() != null && dependantDTO.getArchived() == 1) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("This POA request has already been cancelled.");
                return jsonResponse;
            }

            String frontPOAImg = null, backPOAimg = null;
            if (frontPOAImage != null && (!frontPOAImage.isEmpty())) {
                frontPOAImg = CommonUtil.saveInsuranceCard(frontPOAImage, patientProfile.getPatientProfileSeqNo(), "FrontPOAImage");
                logger.info("FrontPOAImage Path: " + frontPOAImg);
                if (!CommonUtil.urlAuthorization(frontPOAImg)) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Error in saving page 1 power of attorney.please try again.");
                    return jsonResponse;
                }
            }
            if (backPOAImage != null && (!backPOAImage.isEmpty())) {
                backPOAimg = CommonUtil.saveInsuranceCard(backPOAImage, patientProfile.getPatientProfileSeqNo(), "BackPOAImage");
                logger.info("BackPOAimg Path: " + backPOAimg);
                if (CommonUtil.isNotEmpty(backPOAimg) && !CommonUtil.urlAuthorization(frontPOAImg)) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Error in saving page 2 power of attorney.please try again.");
                    return jsonResponse;
                }
            }

            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("updtae record Successfully");

        } catch (IOException e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
            logger.error("Exception:: updateCareTakerImageWs", e);

        }
        return jsonResponse;

    }

    /////////////////////////////////////////////////////////////
    @RequestMapping(value = "/updateCareTakerExpiryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateCareTakerExpiryWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "expiryDate", required = false) String expiryDate,
            @RequestParam(value = "careTakerId", required = false) Integer careTakerId) {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("securityToken is: " + securityToken + " careTakerId " + careTakerId);
//        System.out.println("securityToken is: " + securityToken);
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                System.out.println("securityToken not validated");
                return jsonResponse;
            }

            //PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
            PatientDependantDTO dependantDTO = null;
            if (dependantDTO == null || CommonUtil.isNullOrEmpty(dependantDTO.getId())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("No care taker record found against this id " + careTakerId);
                return jsonResponse;
            }
            if (dependantDTO.getArchived() != null
                    && dependantDTO.getArchived() == 1) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("This POA request has already been cancelled.");
                return jsonResponse;
            }

            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("updtae record Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to update record.");
            logger.error("Exception:: updateCareTakerImageWs", e);

        }
        return jsonResponse;
    }

    ////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateReadyToDeliveryRxWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object populateReadyToDeliveryRxWs(@RequestHeader(value = "securityToken", required = false) String securityToken) {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("securityToken is: " + securityToken);
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                System.out.println("securityToken not validated");
                return jsonResponse;
            }
            List<OrderDetailDTO> listOfReadyToDeliveryOrders = consumerRegistrationService.getSameDayAndFilledStatusOrdersByPatientId(patientProfile.getPatientProfileSeqNo());
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(listOfReadyToDeliveryOrders);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem....");
            logger.error("Exception:: populateReadyToDeliveryRxWs", e);
        }

        return jsonResponse;
    }

    @RequestMapping(value = "/confirmReadyToDeliveryRxWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object confirmReadyToDeliveryRxWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "data", required = false) String lstOrder, @RequestParam(value = "signature", required = false) MultipartFile signature) {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("securityToken is: " + securityToken + " lstOrder# " + lstOrder);
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                System.out.println("securityToken not validated");
                return jsonResponse;
            }
            if (CommonUtil.isNullOrEmpty(lstOrder)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order list is empty");
                return jsonResponse;
            }
            JSONObject jsonObj = new JSONObject(lstOrder);

            JSONArray jsonArray = jsonObj.getJSONArray("lstOrder");

            //then get the type for list and parse using gson as
            Type listType = new TypeToken<List<ReadyToDeliveryRxDTO>>() {
            }.getType();
            List<ReadyToDeliveryRxDTO> lstReadyToDeliveryRx = new Gson().fromJson(jsonArray.toString(), listType);
            if (CommonUtil.isNullOrEmpty(lstReadyToDeliveryRx)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order list is null");
                logger.info("Order list is null");
                return jsonResponse;
            }
            boolean isSaved = patientProfileService.isConfirmReadyToDeliveryRxOrders(lstReadyToDeliveryRx, signature);
            if (isSaved) {
                jsonResponse.setData(1);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS_MESSEGE);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("There is problem to saved record.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is problem to saved record..");
            logger.error("Exception:: confirmReadyToDeliveryRxWs", e);
        }
        return jsonResponse;
    }

    //////////////////////////////GetPatientInfoWs also working as Login//////////////////////////////////
    @RequestMapping(value = "/GetPatientInfoWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object populatePatientDataWs(@RequestBody String jsonRequest) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode userInputNode = rootNode.path("UserIdentity");
            String userInput = userInputNode.asText();

            JsonNode fcmId = rootNode.path("fcmToken");
            String fcmToken = fcmId.asText();

            JsonNode inputOsTyp = rootNode.path("osType");
            String osType = inputOsTyp.asText();

            logger.info("userInput# " + userInput);
            logger.info("fcmToken# " + fcmToken);
            logger.info("osType# " + osType);
            if (CommonUtil.isNullOrEmpty(userInput)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Enter Phone#/Email address");
                return objectMapper(objectMapper, jsonResponse);
            }
            PatientProfile dbProfile = patientProfileService.getPatientProfileByEmailOrPhone(userInput);
            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                //jsonResponse.setErrorMessage("There is no patient found..");
                jsonResponse.setSuccessMessage("Non Matched Patient Identifier");
                jsonResponse.setErrorMessage("New members & prescriptions are enrolled exclusively by participating pharmacy at this time");
                return objectMapper(objectMapper, jsonResponse);
            }
            String secureToken = RedemptionUtil.getRandomToken();
            logger.info("Secure Token is: " + secureToken);
            if("Completed".equals(dbProfile.getStatus())){
             dbProfile.setSecurityToken(secureToken);
            }          
            dbProfile.setOsType(osType);
            dbProfile.setDeviceToken(fcmToken);
            patientProfileService.update(dbProfile);
            PatientProfileDTO profile = new PatientProfileDTO();
            if (CommonUtil.isPhoneValid(userInput)) {
                profile.setMobileNumber(userInput);
            } else {
                profile.setEmailAddress(userInput);
            }

            if (CommonUtil.isNotEmpty(profile.getMobileNumber()) && patientProfileService.isPatientMobileExist(profile.getMobileNumber(), null)) {
                EnrollemntIpadDTO enrollemntIpadDTO = patientProfileService.enrollmentData(dbProfile.getPatientProfileSeqNo());
                if (CommonUtil.isNullOrEmpty(enrollemntIpadDTO.getEnrollemtStatus())) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Please enroll from paractise first");
                    return objectMapper(objectMapper, jsonResponse);
                } //                else if ("New".equals(enrollemntIpadDTO.getEnrollemtStatus())) {
                //                    jsonResponse.setErrorCode(0);
                //                    jsonResponse.setSuccessMessage("COMPLIANCE REWARD is offered by invitation only");
                //                    jsonResponse.setErrorMessage("check your text messages for invitation; \n reply with YES to accept conditions ");
                //                    return objectMapper(objectMapper, jsonResponse);
                //                }
                else {
                    jsonResponse.setErrorCode(1);
                    //jsonResponse.setData(patientProfileService.papulateProfileUserData(patientProfileService.getPatientInfoByEmailOrMobileNo(profile.getMobileNumber(), "")));
                    jsonResponse.setData(patientProfileService.papulateProfileUserData(dbProfile));
                    return objectMapper(objectMapper, jsonResponse);
                }

            } else if (CommonUtil.isNotEmpty(profile.getEmailAddress()) && patientProfileService.isPatientEmailExist(profile.getEmailAddress(), null)) {
                EnrollemntIpadDTO enrollemntIpadDTO = patientProfileService.enrollmentData(dbProfile.getPatientProfileSeqNo());
                if (CommonUtil.isNullOrEmpty(enrollemntIpadDTO.getEnrollemtStatus())) {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Please enroll from paractise first");
                    return objectMapper(objectMapper, jsonResponse);
                } else if ("New".equals(enrollemntIpadDTO.getEnrollemtStatus())) {
                    jsonResponse.setErrorCode(0);
                    //jsonResponse.setErrorMessage("Please accept subscription first, A message was sent on email/phone");
                    jsonResponse.setSuccessMessage("COMPLIANCE REWARD is offered by invitation only");
                    jsonResponse.setErrorMessage("check your text messages for invitation; \n reply with YES to accept conditions ");
                    return objectMapper(objectMapper, jsonResponse);
                } else {
                    jsonResponse.setErrorCode(1);
                    // jsonResponse.setData(patientProfileService.papulateProfileUserData(patientProfileService.getPatientInfoByEmailOrMobileNo("", profile.getEmailAddress())));
                    jsonResponse.setData(patientProfileService.papulateProfileUserData(dbProfile));
                    return objectMapper(objectMapper, jsonResponse);
                }

            }

//            if (dbProfile != null) {
//                jsonResponse.setErrorCode(1);
//                patientProfile = patientProfileService.savePatientProfile(dbProfile);
//                jsonResponse.setData(patientProfileService.papulateProfileUserData(dbProfile));
//            } else {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("There is no patient found..");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("There is no patient found..");
            logger.error("Exception:: populatePatientDataWs", e);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    ////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/patientEnrollmentWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object patientEnrollmentWs(@RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LoginDTO loginDTO = objectMapper.readValue(jsonRequest, LoginDTO.class);
            if (CommonUtil.isNullOrEmpty(loginDTO.getMobileNumber())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_MOBILE_NO);
                return objectMapper(objectMapper, jsonResponse);
            }
            if (!APIValidationUtil.validateEmail(loginDTO.getEmailAddress(), jsonResponse)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            if (CommonUtil.isNotEmpty(loginDTO.getMobileNumber())) {
                loginDTO.setMobileNumber(CommonUtil.replaceStr(loginDTO.getMobileNumber(), "-", ""));
                if (patientProfileService.isPatientPhoneNumberExist(loginDTO.getMobileNumber())) {
                    loginDTO.setIsAlreadEnrolled(Boolean.TRUE);
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage(Constants.PHONE_NUMBER_EXIST);
//                    jsonResponse.setData(loginDTO);
                    return objectMapper(objectMapper, jsonResponse);
                }
            }

            if (patientProfileService.isPatientEmailExist(loginDTO.getEmailAddress(), loginDTO.getPatientProfileSeqNo())) {
                loginDTO.setIsAlreadEnrolled(Boolean.TRUE);
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_MSG_EMAIL_EXIST);
//                jsonResponse.setData(loginDTO);
                return objectMapper(objectMapper, jsonResponse);
            }
            PatientProfile patientProfile = new PatientProfile();
            //Check Dob >18
            if (!APIValidationUtil.validateDOB(loginDTO.getDob(), patientProfile, jsonResponse, logger)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            //Save patient Info
            loginDTO = patientProfileService.savePatientEnrollmentWs(patientProfile, loginDTO);
            if (loginDTO != null) {
                String psw = RandomString.generatePassword();
                patientProfile.setPassword(CommonUtil.bCryptPasswordEncoder(psw));
                patientProfile.setUpdatePasswordDate(new Date());
                if (patientProfileService.update(patientProfile)) {
                    //send psw email
                    consumerRegistrationService.sendPsw(loginDTO.getFirstName(), loginDTO.getEmailAddress(), psw, Constants.APP_ACCOUNT_CREATED_PSW);
                    boolean isSend = patientProfileService.sendNotification(loginDTO.getPatientProfileSeqNo(), Constants.WELCOME);
                    if (isSend == false) {
                        jsonResponse.setErrorCode(0);
                        jsonResponse.setErrorMessage("Welcome Messge Sending Issue..");
                        return objectMapper(objectMapper, jsonResponse);
                    }
                    boolean isSandd = patientProfileService.sendNotification(loginDTO.getPatientProfileSeqNo(), Constants.REGISTRATION_SUCCESS);
                    if (isSandd == false) {
                        jsonResponse.setErrorCode(0);
                        jsonResponse.setErrorMessage("Registration  Messge Sending Issue..");
                        return objectMapper(objectMapper, jsonResponse);
                    }
//                CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs(Constants.WELCOME, Constants.EVENTNAME);
//                if (campaignMessages != null) {
//            String messageText = TextFlowUtil.setMessagePlaceHolder(campaignMessages.getSmstext(), rewardPoints.getPoint().intValueExact());
//            logger.info("Message Text is: " + messageText);
//            campaignMessages.setSmstext(messageText);
//            if (patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, profileId)) {
//                logger.info("Notification messages sent.");
                }

                loginDTO.setIsAlreadEnrolled(Boolean.FALSE);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.SUCCESS_MESSEGE);
                jsonResponse.setData(loginDTO);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
            }

        } catch (IOException | ParseException e) {
            logger.error("Exception# patientEnrollmentWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    ////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/forgotPasswordWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object forgotPasswordWs(@RequestBody String jsonRequest) throws IOException, Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode userInputNode = rootNode.path("emailAddress");
            String emailAddress = userInputNode.asText();
            logger.info("EmailAddress# " + emailAddress);

            if (!APIValidationUtil.validateEmail(emailAddress, jsonResponse)) {
                return objectMapper(objectMapper, jsonResponse);
            }
            PatientProfile patientProfile = patientProfileService.getPatientInfoByEmailOrMobileNo("", emailAddress);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(messageSource.getMessage("consumer.email.not.found", null, null));
                return objectMapper(objectMapper, jsonResponse);
            }
            String psw = RandomString.generatePassword();
            patientProfile.setPassword(CommonUtil.bCryptPasswordEncoder(psw));
            patientProfile.setUpdatePasswordDate(new Date());
            if (patientProfileService.update(patientProfile)) {
                //send psw email

                patientProfileService.saveActivitesHistory(ActivitiesEnum.CHANGE_PASSWORD.getValue(), patientProfile, "", "", "","");
                consumerRegistrationService.sendPsw(patientProfile.getFirstName(), emailAddress, psw, Constants.MOBILE_APP_ACCOUNT_FORGOT_PSW);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Password sent at given email address.");
            }
        } catch (IOException | NoSuchMessageException e) {
            logger.error("Exception# ForgotPasswordWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(e.getMessage());
        }
        return objectMapper(objectMapper, jsonResponse);
    }

    @RequestMapping(value = "/addBloodGlucoseWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addBloodGlucoseWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }

            PatientGlucoseDTO patientGlucoseDTO = mapper.readValue(json, PatientGlucoseDTO.class);
            PatientGlucoseResults glucose = patientProfileService.getPatientGlucoseResultsByPatientId(patientProfile.getPatientProfileSeqNo());

            if(glucose != null) {
                
                Date serverDateaddeddFifteenDays = DateUtil.addMinutesTodate(glucose.getCreatedOn(), 15);
                String remainingMinutes = DateUtil.getDateDiffInMinutesFromCurrentDate(serverDateaddeddFifteenDays);
                if(!(new Date().compareTo(serverDateaddeddFifteenDays)>0 || new Date().compareTo(serverDateaddeddFifteenDays)==0)){
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("You can complete this activity again in "+remainingMinutes+ "minutes");
                return objectMapper(mapper, jsonResponse);
                }
            }
            patientGlucoseDTO = patientProfileService.saveGlucoseLevel(patientGlucoseDTO, patientProfile);
            if (patientGlucoseDTO != null) {
                jsonResponse.setData(patientGlucoseDTO);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(messageSource.getMessage("field.value.successfully", null, null));
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(messageSource.getMessage("field.saved.error", null, null));
                return objectMapper(mapper, jsonResponse);
            }

        } catch (IOException | NoSuchMessageException e) {
            logger.error("addBloodGlucoseWs# ", e);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
            return objectMapper(mapper, jsonResponse);
        }

        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/glucoseHistoryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object glucoseHistory(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }
            List<PatientGlucoseDTO> glucoseList = patientProfileService.getPatientGlucoseList(patientProfile.getPatientProfileSeqNo());
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(glucoseList);
            jsonResponse.setTotalRecords(glucoseList.size());
//            jsonResponse.setErrorMessage(Constants.SUCCESS_MESSEGE);
        } catch (Exception e) {
            logger.error("Exception# glucoseHistoryWs# ", e);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);

    }

    @RequestMapping(value = "/patientActivitesCountWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object patientActivitesCountWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        //validate SecurityToken
        PatientProfile patientProfile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        List<PatientActivityDTO> patientActivityDTO = patientProfileService.getPatientActitvityCount(patientProfile.getPatientProfileSeqNo());
        jsonResponse.setData(patientActivityDTO);
        jsonResponse.setErrorCode(1);
        jsonResponse.setTotalRecords(patientActivityDTO.size());
        jsonResponse.setErrorMessage(Constants.SUCCESS);
        return objectMapper(objectMapper, jsonResponse);
    }

    @RequestMapping(value = "/addBloodPressureWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addBloodPressureWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            BloodPressureDTO bloodPressureDTO = mapper.readValue(json, BloodPressureDTO.class);
            BloodPressure bp = patientProfileService.getPatientBloodPressureByPatientId(patientProfile.getPatientProfileSeqNo());
            
            if(bp != null){
                Date serverDateaddeddFifteenDays = DateUtil.addMinutesTodate(bp.getCreatedOn(), 15);
                 String remainingMinutes = DateUtil.getDateDiffInMinutesFromCurrentDate(serverDateaddeddFifteenDays);
                if(!(new Date().compareTo(serverDateaddeddFifteenDays)>0 || new Date().compareTo(serverDateaddeddFifteenDays)==0)){
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("You can complete this activity again in "+remainingMinutes+ "minutes");
                return objectMapper(mapper, jsonResponse);
                }
            }            
            bloodPressureDTO = patientProfileService.saveBloodPressureLevel(bloodPressureDTO, patientProfile);
            if (bloodPressureDTO != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(bloodPressureDTO);
                jsonResponse.setErrorMessage(messageSource.getMessage("field.value.successfully", null, null));
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
                return objectMapper(mapper, jsonResponse);
            }
        } catch (IOException | NoSuchMessageException e) {
            logger.error("Exception# ->addbloodPressureWS ", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
            return objectMapper(mapper, jsonResponse);
        }

        return objectMapper(mapper, jsonResponse);
    }

    /*
     @RequestMapping(value = "/glucoseHistoryWs", produces = "application/json", method = RequestMethod.POST)
     public @ResponseBody
     Object glucoseHistory(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
     */
    @RequestMapping(value = "/bloodPressureHistoryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object bloodPressureHistoryWs(@RequestHeader(value = "SecurityToken", required = false) String securityToken) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile patientprofile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientprofile == null || CommonUtil.isNullOrEmpty(patientprofile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<BloodPressureDTO> bloodPressureDTO = patientProfileService.getBloodPressureHstoryList(patientprofile.getPatientProfileSeqNo());
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(bloodPressureDTO);
            jsonResponse.setTotalRecords(bloodPressureDTO.size());
            jsonResponse.setErrorMessage(messageSource.getMessage("field.value.successfully", null, null));
        } catch (Exception e) {
            logger.error("Exception# patientWsController -> bloodPressureHistory ", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/addPatientBodyMassWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object patientBodyMass(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            PatientBodyMassResultDTO patientBodyMassResultDTO = mapper.readValue(json, PatientBodyMassResultDTO.class);
              PatientBodyMassResult bm = patientProfileService.getPatientPatientBodyMassResultByPatientId(profile.getPatientProfileSeqNo());
            if(bm != null){
                Date serverDateaddedAfterHours = DateUtil.addHoursTodate(bm.getCreatedOn(), 24);
                String remainingHours = DateUtil.getDateDiffInHoursFromCurrentDate(serverDateaddedAfterHours);
                logger.info("remainingHours"+ remainingHours);
                if(!(new Date().compareTo(serverDateaddedAfterHours)>0 || new Date().compareTo(serverDateaddedAfterHours)==0)){
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("You can complete this activity again in "+remainingHours+ "minutes");
                return objectMapper(mapper, jsonResponse);
                }
            }            

            patientBodyMassResultDTO = patientProfileService.saveBodyMassResult(patientBodyMassResultDTO, profile);
            jsonResponse.setData(patientBodyMassResultDTO);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(messageSource.getMessage("field.value.successfully", null, null));

        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> addPatientBodyMassWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/bodyMassResultHistoryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object bodyMassResultHistory(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<PatientBodyMassResultDTO> patientBodyMassResultDTO = patientProfileService.getBodyMassHistoryList(profile.getPatientProfileSeqNo());
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(patientBodyMassResultDTO);
            jsonResponse.setTotalRecords(patientBodyMassResultDTO.size());
            jsonResponse.setErrorMessage("Recoed fetched successfuly.");
        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> bodyMassResultHistoryWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/addPatientHeartPulseWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addPatientHeartPulseWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            PatientHeartPulseDTO patientHeartPulseDTO = mapper.readValue(json, PatientHeartPulseDTO.class);
            PatientHeartPulseResult hp = patientProfileService.getPatientPatientHeartPulseResultByPatientId(profile.getPatientProfileSeqNo());
            
            if(hp != null){
                Date serverDateaddeddFifteenDays = DateUtil.addMinutesTodate(hp.getCreatedOn(), 15);
                 String remainingMin = DateUtil.getDateDiffInMinutesFromCurrentDate(serverDateaddeddFifteenDays);
                 logger.info("remainingMin :"+remainingMin);
                if(!(new Date().compareTo(serverDateaddeddFifteenDays)>0 || new Date().compareTo(serverDateaddeddFifteenDays)==0)){
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("You can complete this activity again in "+remainingMin+ "minutes");
                return objectMapper(mapper, jsonResponse);
                }
            } 

            patientHeartPulseDTO = patientProfileService.saveHeartPulseResult(patientHeartPulseDTO, profile);
            jsonResponse.setData(patientHeartPulseDTO);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(messageSource.getMessage("field.value.successfully", null, null));

        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> addPatientHeartPulseWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/heartPulseResultHistoryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object heartPulseResultHistoryWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<PatientHeartPulseDTO> patientHeartPulseDTO = patientProfileService.getHeartPulseHistoryList(profile.getPatientProfileSeqNo());
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(patientHeartPulseDTO);
            jsonResponse.setTotalRecords(patientHeartPulseDTO.size());
            jsonResponse.setErrorMessage("Recoed fetched successfuly.");
        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> heartPulseResultHistoryWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }
//    @RequestMapping(value = "/BMIWeeklyFilterWs", produces = "application/json", method = RequestMethod.POST)
//    @ResponseBody
//    Object BMIWeeklyFilterWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {
//        JsonResponse jsonResponse = new JsonResponse();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            if (CommonUtil.isNullOrEmpty(SecurityToken)) {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("Security Token is Null ");
//                return objectMapper(mapper, jsonResponse);
//            }
//            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(SecurityToken);
//            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
//                return objectMapper(mapper, jsonResponse);
//            }
//
//            PatientBodyMassResultDTO patientBodyMassResultDTO = mapper.readValue(json, PatientBodyMassResultDTO.class);
//            List<PatientBodyMassResultDTO> patientBodyMassResultDTOList = patientProfileService.getWeeeklyBodyMassResult(profile.getPatientProfileSeqNo(), patientBodyMassResultDTO);
//            jsonResponse.setData(patientBodyMassResultDTOList);
//            jsonResponse.setErrorCode(1);
//            jsonResponse.setTotalRecords(patientBodyMassResultDTOList.size());
//           
//
//        } catch (Exception e) {
//            logger.error("Excepton# patientWsController -> BMIWeeklyFilterWs", e);
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage("Record not fetch.");
//            return objectMapper(mapper, jsonResponse);
//        }
//        return objectMapper(mapper, jsonResponse);
//    }
    /////////////////////////////

    @RequestMapping(value = "/BMIWeeklyFilterAvgWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    Object BMIWeeklyFilterWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            PatientBodyMassResultDTO patientBodyMassResultDTO = mapper.readValue(json, PatientBodyMassResultDTO.class);
            List<BMIWeeklyDetailRapperDTO> bMIWeeklyDetailRapperDTOList = patientProfileService.getWeeeklyBodyMassResult(profile.getPatientProfileSeqNo(), patientBodyMassResultDTO);
            jsonResponse.setData(bMIWeeklyDetailRapperDTOList);
            jsonResponse.setErrorCode(1);
            jsonResponse.setTotalRecords(bMIWeeklyDetailRapperDTOList.size());

        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> BMIWeeklyFilterWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    /////////////////////////////////
    @RequestMapping(value = "/BMIYearFilterWs", method = RequestMethod.GET)
    public @ResponseBody
    JsonResponse BMIYearFilterWs(
            @RequestHeader(value = "SecurityToken", required = false) String SecurityToken,
            @RequestParam(value = "Id", required = true) int patientId,
            @RequestParam(value = "StartDate", required = true) String StartDate,
            @RequestParam(value = "EndDate", required = true) String EndDate) {

        BMIYearFilterWsMainResponse bMIYearFilterWsMainResponse = new BMIYearFilterWsMainResponse();
        JsonResponse jsonResponse = new JsonResponse();
        try {

            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            bMIYearFilterWsMainResponse = patientProfileService.getBMIYearFilterWs(patientId, StartDate, EndDate);
            if (bMIYearFilterWsMainResponse != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record fetch.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not fetch.");
            }
            //jsonResponse = new ObjectMapper().writeValueAsString(bMIYearFilterWsResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonResponse.setData(bMIYearFilterWsMainResponse);
        return jsonResponse;
    }

    @RequestMapping(value = "/BMIMonthlyFilterWs", method = RequestMethod.GET)
    public @ResponseBody
    JsonResponse BMIMonthlyFilterWs(
            @RequestHeader(value = "SecurityToken", required = false) String SecurityToken,
            @RequestParam(value = "Id", required = true) int patientId,
            @RequestParam(value = "StartDate", required = true) String StartDate,
            @RequestParam(value = "EndDate", required = true) String EndDate) {

        BMIMonthFilterWsMainResponse bMIMonthFilterWsMainResponse = new BMIMonthFilterWsMainResponse();
        JsonResponse jsonResponse = new JsonResponse();
        try {

            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            bMIMonthFilterWsMainResponse = patientProfileService.getBMIMonthFilterWs(patientId, StartDate, EndDate);
            if (bMIMonthFilterWsMainResponse != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record fetch.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not fetch.");
            }
            //jsonResponse = new ObjectMapper().writeValueAsString(bMIYearFilterWsResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonResponse.setData(bMIMonthFilterWsMainResponse);
        return jsonResponse;
    }

    @RequestMapping(value = "/BMIWeeklyFilterDetailWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    JsonResponse BMIWeeklyFilterDetailWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            PatientBodyMassResultDTO patientBodyMassResultDTO = mapper.readValue(json, PatientBodyMassResultDTO.class);
            List<BMIWeeklyDetailRapperDTO> bMIWeeklyDetailRapperDTOList = patientProfileService.getDetailWeeeklyBodyMassResult(profile.getPatientProfileSeqNo(), patientBodyMassResultDTO);
            jsonResponse.setData(bMIWeeklyDetailRapperDTOList);
            jsonResponse.setErrorCode(1);
            jsonResponse.setTotalRecords(bMIWeeklyDetailRapperDTOList.size());

        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> BMIWeeklyFilterWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return jsonResponse;
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/getQuestionsWs", method = RequestMethod.GET)
    public @ResponseBody
    JsonResponse getQuestionsWs(
            @RequestHeader(value = "SecurityToken", required = false) String SecurityToken,
            @RequestParam(value = "surveyId", required = false) String surveyId,
            @RequestParam(value = "practiceId", required = false) int practiceId
    ) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            List<Question> questions = patientProfileService.getQuestionsList(surveyId, practiceId, profile.getMobileNumber());
            jsonResponse.setData(questions);
            jsonResponse.setErrorCode(1);
            jsonResponse.setTotalRecords(questions.size());
            jsonResponse.setPatientId(profile.getPatientProfileSeqNo());

        } catch (Exception e) {
            e.printStackTrace();

            logger.error("Excepton# patientWsController -> getQuestionsOfSurvey", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return jsonResponse;
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/SurveyListWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    JsonResponse SurveyListWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<SurveyBridgeDTO> surveyBridgeDTOList = patientProfileService.getSurveyList(profile.getMobileNumber());
            jsonResponse.setData(surveyBridgeDTOList);
            jsonResponse.setErrorCode(1);
            jsonResponse.setTotalRecords(surveyBridgeDTOList.size());

        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> SurveyListWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return jsonResponse;
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/surveyNotificationWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object surveyNotificationWs(@RequestBody String data) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            SurveyNotificationRequestPacketDto body = mapper.readValue(data, SurveyNotificationRequestPacketDto.class);
            if (CommonUtil.isNullOrEmpty(body.surveyId)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("surveyId is null.");
                return objectMapper(mapper, jsonResponse);
            }

            for (int patientId : body.listPatient) {
                PatientProfile dbProfile = patientProfileService.getPatientProfileByProfileIdWithOutStatus(patientId);
                if (dbProfile != null) {
                    patientProfileService.updateSurveyLogs(dbProfile, body.surveyId);
                    boolean isSend = patientProfileService.getSurvyPushNotification(body.surveyId, dbProfile, "Survey", "Compliance Survey");
                    if (isSend) {
//                       boolean isSvaed =  patientProfileService.updateSurveyLogs(dbProfile, body.surveyId);
//                        if(isSvaed == false){
//                           jsonResponse.setErrorCode(0);
//                        jsonResponse.setErrorMessage("Notificaation not sent.");
//                        return objectMapper(mapper, jsonResponse);
//                       }
                        jsonResponse.setErrorCode(1);
                        jsonResponse.setErrorMessage("Notifcations has been sent successfully.");
                    } else {
                        jsonResponse.setErrorCode(0);
                        jsonResponse.setErrorMessage("Notificaation not sent.");
                        return objectMapper(mapper, jsonResponse);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Notificaation not sent.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/allergyListWs", method = RequestMethod.GET)
    @ResponseBody
    Object allergyListWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) {
        JsonResponse jsonResponse = new JsonResponse();
        try {

            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }

            List<PatientAllergies> list = patientProfileService.getAllergyList(profile.getPatientProfileSeqNo());
            if (CommonUtil.isNullOrEmpty(list)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setTotalRecords(list.size());
                jsonResponse.setErrorMessage(messageSource.getMessage("error.record.found", null, null));
            } else {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record found");
                jsonResponse.setData(list);
                jsonResponse.setTotalRecords(list.size());
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Excepton# patientWsController -> allergyListWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(messageSource.getMessage("field.db.error", null, null));
            return jsonResponse;
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/getQuestionAnsListWs", method = RequestMethod.GET)
    @ResponseBody
    Object getQuestionAnsListWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken,
            @RequestParam(value = "SurveyId", required = false) String surveyId) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            //validate SecurityToken
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            //Get Survey Question List
            List<Object> list = patientProfileService.getSurveQuestionList(profile.getMobileNumber(), surveyId);
            if (CommonUtil.isNullOrEmpty(list)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(messageSource.getMessage("error.record.found", null, null));
            } else {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record found");
                jsonResponse.setData(list);
                jsonResponse.setTotalRecords(list.size());
            }

        } catch (IOException | NoSuchMessageException e) {
            e.printStackTrace();
            logger.error("Excepton# patientWsController -> getQuestionAnsListWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(messageSource.getMessage("field.db.error", null, null));
            return jsonResponse;
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/QuestionReplyWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    Object getQuestionReplyWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            QuestionAnswerReplyDetailDTO qRDTO = mapper.readValue(json, QuestionAnswerReplyDetailDTO.class);
            JSONTokener tokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(tokener);
            JSONArray jsonArray = jsonObject.getJSONArray("questionReply");
            Type listQuestionReplyType = new TypeToken<List<surveyQuestionAnswerDTO>>() {
            }.getType();

            List<surveyQuestionAnswerDTO> listQuestionAnswer = new Gson().fromJson(jsonArray.toString(), listQuestionReplyType);
            boolean isSaved = patientProfileService.updateQuestionReply(profile.getMobileNumber(), qRDTO.getSurveyId(), listQuestionAnswer);
            if (!isSaved) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(messageSource.getMessage("field.saved.error", null, null));
            } else {
                jsonResponse.setErrorCode(1);
                jsonResponse.setPatientId(profile.getPatientProfileSeqNo());
                jsonResponse.setSurveyId(qRDTO.getSurveyId());
                jsonResponse.setErrorMessage(messageSource.getMessage("field.value.successfully", null, null));
            }

        } catch (IOException | JSONException | JsonSyntaxException e) {
            logger.error("Excepton# patientWsController -> QuestionReplyWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(messageSource.getMessage("field.saved.error", null, null));
            return jsonResponse;
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/addUpdateAddressWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    Object addAddressWs(@RequestHeader(value = "SecurityToken", required = false) String securityToken, @RequestBody String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            PatientDeliveryAddressDTO addressDto = mapper.readValue(json, PatientDeliveryAddressDTO.class);
            boolean isSaved = patientProfileService.saveAndUpdateAddresses(profile, addressDto);
            if (isSaved) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Address Saved/Updated succesfully. ");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Address Not Saved/Updated. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Address Not Saved/Updated. ");
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/markCurrentAddressAsDefaultWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    Object markCurrentAddressAsDefault(@RequestHeader(value = "SecurityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }
            PatientDeliveryAddressDTO addressDto = mapper.readValue(json, PatientDeliveryAddressDTO.class);
            boolean isUpdate = patientProfileService.markCurrentAddressAsDefault(addressDto.getAddressId(), profile.getPatientProfileSeqNo());
            if (isUpdate == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("CurrentAddress Updated as Default.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record Not Updated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record Not Updated.");
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/deleteAndUpdateAllergyWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    Object deleteAndUpdateAllergyWs(@RequestHeader(value = "SecurityToken", required = false) String securityToken, @RequestBody String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
//        Boolean isSaved = false;
        try {
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
            AllergyDto allergyDto = mapper.readValue(json, AllergyDto.class);

            ErrorDto errorDto = patientProfileService.deleteAndUpdateAllergies(profile.getPatientProfileSeqNo(), allergyDto.getAllergyId(), allergyDto.getAllregyName(), allergyDto.getAction());
            switch (errorDto.getValue()) {
                case 1: {
                    List<PatientAllergies> allergylist = patientProfileService.getAllergyList(profile.getPatientProfileSeqNo());
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setData(allergylist);
                    jsonResponse.setTotalRecords(allergylist.size());
                    //jsonResponse.setErrorMessage("Allergy has deleted successfully.");
                    jsonResponse.setErrorMessage("Your allergy profile is updated");
                    break;
                }
                case 2: {
                    List<PatientAllergies> allergylist = patientProfileService.getAllergyList(profile.getPatientProfileSeqNo());
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setData(allergylist);
                    jsonResponse.setTotalRecords(allergylist.size());
                    //jsonResponse.setErrorMessage("Allergy has updated successfully.");
                    jsonResponse.setErrorMessage("Your allergy profile is updated");
                    break;
                }
                case 3:
                    jsonResponse.setErrorCode(0);
//                    jsonResponse.setErrorMessage("There is no allergy available aginst which can be updated.");
//                    jsonResponse.setData(errorDto);
                    jsonResponse.setErrorMessage("Duplicate allergy entry detected; only one will be saved ");
                    break;
                case 4:
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Encounter Exception in Service.");
                    break;
                default:
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Allergy Not Deleted. ");
                    break;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Encounter Exception.");
        }
        return objectMapper(mapper, jsonResponse);
    }
//      @RequestMapping(value = "/SurveyListWs", produces = "application/json", method = RequestMethod.POST)
//    @ResponseBody
//    JsonResponse SurveyListWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
//        JsonResponse jsonResponse = new JsonResponse();
//        try {
//            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
//            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
//                return jsonResponse;
//            }
//            List<SurveyBridgeDTO> surveyBridgeDTOList = patientProfileService.getSurveyList(profile.getMobileNumber());
//            jsonResponse.setData(surveyBridgeDTOList);
//            jsonResponse.setErrorCode(1);
//            jsonResponse.setTotalRecords(surveyBridgeDTOList.size());
//
//        } catch (Exception e) {
//            logger.error("Excepton# patientWsController -> SurveyListWs", e);
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage("Record not fetch.");
//            return jsonResponse;
//        }
//        return jsonResponse;
//    }

    @RequestMapping(value = "/duePaymentOrderListWs", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Object duePaymentOrderList(@RequestHeader(value = "SecurityToken", required = false) String securityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }
            RefillOrderDTO refillOrderDTO = patientProfileService.getPaymentDueOrderList(profile.getPatientProfileSeqNo());
            if (refillOrderDTO != null) {
                jsonResponse.setData(refillOrderDTO);
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record fetch successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not fetch.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/deletePatientWs", method = RequestMethod.GET)
    @ResponseBody
    public Object deletePatientWs(@RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isDelete = false;
        try {
            PatientProfile profile = patientProfileService.getPatientProfileByEmailOrPhone(mobileNumber);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getMobileNumber())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not found.");
                return objectMapper(mapper, jsonResponse);
            }
            isDelete = patientProfileService.getDeletePatientProfile(mobileNumber);
            if (isDelete == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record Deleted successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not found.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Problem to Delete Rcord.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/ActivitiesHistoryWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object ActivitiesHistoryWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<ActivityHistoryDTO> activityHistoryDTO = patientProfileService.getActivityHistory();
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(activityHistoryDTO);
            jsonResponse.setTotalRecords(activityHistoryDTO.size());
            jsonResponse.setErrorMessage("Recoed fetched successfuly.");
        } catch (Exception e) {
            logger.error("Excepton# patientWsController -> ActivitiesHistoryWs", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetch.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    /**
     * This function is used to validate the phone number, send verification
     * code and create basic profile
     *
     * @param json
     * @return Its return JSON of profile
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/saveNewDrugWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object saveNewDrug(@RequestBody String json) throws IOException, ParseException, Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        DrugDetail2 drugNewDto = objectMapper.readValue(json, DrugDetail2.class);
        DrugService drugService = new DrugService();
        if (drugNewDto != null) {

            drugService.saveNewDrug(drugNewDto);

        } else {
            CommonUtil.inValidSecurityToken(jsonResponse);
        }
        return objectMapper(objectMapper, jsonResponse);

    }
//this Api will call on following functionailty 
//        1.create (Refill Order) as copy of existing order if orderStatus will be Filled 
//        2.order payment will be done by this 
//        3.send payment done notification messge to user.
    @RequestMapping(value = "/updateOrderStatusWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateOrderStatusWs(@RequestHeader(value = "SecurityToken", required = false) String securityToken,
            @RequestParam(value = "listOrder", required = false) String listOrder,
            @RequestParam(value = "signatureImage", required = false) MultipartFile signatureImage) throws IOException, ParseException, Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isSaved = false;
//         String signatureImgPath = null;
        try {
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            if (listOrder == null) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order list is null");
                logger.info("Order list is null");
                return objectMapper(mapper, jsonResponse);
            }
            JSONObject jsonObj = new JSONObject(listOrder);
            JSONArray jsonArray = jsonObj.getJSONArray("listOrder");
            int statusId = Constants.ORDER_STATUS.PAYMENT_AUTHORIZED_ID;
            //then get the type for list and parse using gson as
            Type listType = new TypeToken<List<OrdersDTO>>() {
            }.getType();
            List<OrdersDTO> lstOrder2 = new Gson().fromJson(jsonArray.toString(), listType);

//            String refilOrderId = "";
            for (OrdersDTO ord : lstOrder2) {
                Order dbOrder = patientProfileService.getOrderByOrderId(ord.getOrderId());
                if (dbOrder == null) {
                    logger.info("No order exist against this ord# " + ord.getOrderId());
                    continue;
                }
                String practiceCode = "";
                Practices dbpractice = patientProfileService.getPracticebyId(profile.getPracticeId());
                if (dbpractice != null) {
                    practiceCode = dbpractice.getPracticeCode();
                }
                //Refill Request
                if (dbOrder.getOrderStatus().getId() == Constants.ORDER_STATUS.FILLED_ID && !"Refill_Created".equals(dbOrder.getViewStatus())) {
                    statusId = dbOrder.getOrderStatus().getId();
                    patientProfileService.saveActivitesHistory(ActivitiesEnum.REFILL_REQUEST.getValue(), profile, practiceCode + dbOrder.getRxNumber(), dbOrder.getDrugDetail2().getRxLabelName(), "", dbOrder.getId());
                    dbOrder = patientProfileService.saveRefillModule(dbOrder, true, 0, jsonResponse);
                    patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, Constants.MSG_CONTEXT_REFILL_REQUEST_RECEIVED, Constants.ORDER_CATEGORIES.REFILLABLE_NOW);
                    RefillDTO refillDTO = new RefillDTO();
                    refillDTO.setOrderId(ord.getOrderId());
                    refillDTO.setRefillOrderId(dbOrder.getId());
                    jsonResponse.setData(refillDTO);
                    String url = PropertiesUtil.getProperty("PHP_WEB_PDF") + "get-order-status/" + dbOrder.getPracticeId() + "/" + Constants.ORDER_STATUS.ORDER_REFILL;
                    String apiResponse = ServiceConsumerUtil.getServiceResponse(url, "", "GET");
                    logger.info("PHP_WEB_PDF/get-order-status api response: " + apiResponse);
                    isSaved = true;
                } else if (dbOrder.getOrderStatus().getId() == Constants.ORDER_STATUS.MBR_TO_PAY_ID) {
                    OrderStatus orderStatus = new OrderStatus();
                    orderStatus.setId(Constants.ORDER_STATUS.PAYMENT_AUTHORIZED_ID);
                    orderStatus.setName(Constants.ORDER_STATUS.PAID);
                    dbOrder.setOrderStatus(orderStatus);
                    dbOrder.setUpdatedAt(new Date());
                    patientProfileService.update(dbOrder);
                    patientProfileService.updateOrderHistory(dbOrder.getId(), orderStatus.getId());
                    //Mobile app wants to Archive Messge (pay now with Compliance Reward) for clint.
                    NotificationMessages notificationMessage = patientProfileService.getNotificationByOrderId(dbOrder.getId());
                    if (notificationMessage != null) {
                        notificationMessage.setIsArchive(Boolean.TRUE);
                        notificationMessage.setIsRead(Boolean.TRUE);
                        patientProfileService.update(notificationMessage);
                    }
                    patientProfileService.saveActivitesHistory(ActivitiesEnum.PAYMENT_DONE.getValue(), profile, practiceCode + dbOrder.getRxNumber(), dbOrder.getDrugDetail2().getRxLabelName(), "", dbOrder.getId());
                    Long authNumber = RedemptionUtil.generateRandomAuthNumber();
                    patientProfileService.paymentRecivedDetail(dbOrder, authNumber);
                    patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, Constants.PAYMENT_AUTHORIZATION, "Payment Done");
                    //here we will call api for Order_paid event 
                    String url = PropertiesUtil.getProperty("PHP_WEB_PDF") + "get-order-status/" + dbOrder.getPracticeId() + "/" + Constants.ORDER_STATUS.ORDER_PAID;
                    String apiResponse = ServiceConsumerUtil.getServiceResponse(url, "", "GET");
                    logger.info("PHP_WEB_PDF/get-order-status api response: " + apiResponse);
                    isSaved = true;
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Already requested.");
                    return objectMapper(mapper, jsonResponse);
                }
            }

            if (isSaved) {
                if (signatureImage != null && (!signatureImage.isEmpty())) {
                    if (statusId == Constants.ORDER_STATUS.FILLED_ID) {
                        String imgFcPath = CommonUtil.saveInsuranceCard(signatureImage, profile.getPatientProfileSeqNo(), "RefillRequestSignature");
                        logger.info("RefillRequestSignature Path: " + imgFcPath);
                        profile.setRefillRequestSign(imgFcPath);
                    } else {
                        String imgFcPath = CommonUtil.saveInsuranceCard(signatureImage, profile.getPatientProfileSeqNo(), "signatureImage");
                        logger.info("Payment Authorization Path: " + imgFcPath);
                        profile.setSignature(imgFcPath);
                    }
                    profile.setUpdatedOn(new Date());
                    patientProfileService.update(profile);
                }

                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record updated Successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not updated.");
                return objectMapper(mapper, jsonResponse);
            }

        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Problem to Update Record.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);

    }

    @RequestMapping(value = "/deleteCurrentAddressWs", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteCurrentAddress(@RequestHeader(value = "SecurityToken", required = false) String securityToken,
            @RequestParam(value = "addressId", required = false) int addressId) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isDelete = false;
        try {
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            isDelete = patientProfileService.deleteCurrentAddress(profile.getPatientProfileSeqNo(), addressId);
            if (isDelete == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record Deleted successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not found.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Problem to Delete Rcord.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/orderPlaceNotificationWs", method = RequestMethod.GET)
    public @ResponseBody
    Object orderPlaceNotificationWs(@RequestParam(value = "PatientId", required = false) int profileId,
            @RequestParam(value = "OrderId", required = false) String orderId) throws Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isSend = false;
        try {
            if (CommonUtil.isNullOrEmpty(orderId)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("orderId is Null ");
                return jsonResponse;
            }
            PatientProfile profile = patientProfileService.getPatientProfileByProfileIdWithOutStatus(profileId);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Patient Id is null.");
                return jsonResponse;
            }
            Order dbOrder = patientProfileService.getOrderByOrderId(orderId);
            if (dbOrder == null) {
                logger.info("No order exist against this ord# " + orderId);
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("No order exist against this ord# " + orderId);
                return jsonResponse;
            }
//            Registration Notifications 
                boolean isFirstOrder = patientProfileService.checkNotificationStatus(profile);
                if (isFirstOrder == true) {
                    Order ord = patientProfileService.getOrderByPatientId(profile.getPatientProfileSeqNo());
                    if (profile.getPhysicianPracticeId() != null) {
                        if (ord != null) {
                            if (dbOrder.getRxPatientOutOfPocket() == 0d) {
                                logger.info("...Sending Registration Successfully messge for Rx-Reporter ...");
                                boolean isSandd = patientProfileService.sendWelcomeNotification(profile, Constants.REGISTRATIOn_RX_REPORTER);
                                if (isSandd == false) {
                                    jsonResponse.setErrorCode(0);
                                    jsonResponse.setErrorMessage("Registration  Message Sending Issue..");
                                    return objectMapper(mapper, jsonResponse);
                                }
                            } else {
                                 logger.info("...Sending Registration Successfully messge...");
                                boolean isSandd = patientProfileService.sendNotification(profile.getPatientProfileSeqNo(), Constants.REGISTRATION_SUCCESS);
                                if (isSandd == false) {
                                    jsonResponse.setErrorCode(0);
                                    jsonResponse.setErrorMessage("Registration  Message Sending Issue..");
                                    return objectMapper(mapper, jsonResponse);
                                }
                            }
                        }
                        //For Practice Enroolment Sending Registration successfully Messges
                    } else if (dbOrder != null) {
                        if (dbOrder.getRxPatientOutOfPocket() == 0d) {
                            logger.info("...Sending Registration Successfully messge for Pharmacy Non-Copy ...");
                            boolean isSandd = patientProfileService.sendNotification(profile.getPatientProfileSeqNo(), Constants.REGISTRATIOn_PHARMACY_NON_COPY);
                            if (isSandd == false) {
                                jsonResponse.setErrorCode(0);
                                jsonResponse.setErrorMessage("Registration  Message Sending Issue..");
                                return objectMapper(mapper, jsonResponse);
                            }
                        } else {
                            boolean isSandd = patientProfileService.sendNotification(profile.getPatientProfileSeqNo(), Constants.REGISTRATION_SUCCESS);
                            if (isSandd == false) {
                                jsonResponse.setErrorCode(0);
                                jsonResponse.setErrorMessage("Registration  Message Sending Issue..");
                                return objectMapper(mapper, jsonResponse);
                            }
                        }
                    }
//                    logger.info("...Start sending CBD Initial Survey ...");
//                     patientProfileService.updateSurveyLogs(profile, Constants.CBD_SURVY_ID);
//                     boolean isSandd = patientProfileService.sendCBDSurvey(profile.getPatientProfileSeqNo(), Constants.CBD_INTIAL_SURVEY, Constants.CBD);
//                            if (isSandd == false) {
//                                jsonResponse.setErrorCode(0);
//                                jsonResponse.setErrorMessage("CBD Survey Sending Issue..");
//                                return objectMapper(mapper, jsonResponse);
//                            }
//                     logger.info("...End sending CBD Initial Survey ...");    
                }
            /////////////////////////////////
             logger.info("...Start send PushNotification On PlaceOrder ...");
            isSend = patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, "Rx Order", "Order Placed");
            if (isSend == true) {
                dbOrder.setMbrFlag(1);
                patientProfileService.update(dbOrder);
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage("Notifcations has been sent successfully.");

                String url = PropertiesUtil.getProperty("PHP_WEB_PDF") + "get-order-status/" + dbOrder.getPracticeId() + "/" + Constants.ORDER_STATUS.ORDER_MESSAGE;
                String apiResponse = ServiceConsumerUtil.getServiceResponse(url, "", "GET");
                logger.info("PHP_WEB_PDF/get-order-status api response: " + apiResponse);
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Notificaation not sent.");
                return objectMapper(mapper, jsonResponse);
            }

        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("processed faild.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/orderQuestionInfoWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object orderQuestionInfoWs(@RequestHeader(value = "SecurityToken", required = false) String securityToken,
            @RequestParam(value = "Question", required = false) String question,
            @RequestParam(value = "questionImage", required = false) MultipartFile questionImage,
            @RequestParam(value = "orderId", required = false) String orderId,
//          @RequestParam(value = "notificationMsgId", required = false) Integer notificationMsgId)
            @RequestParam(value = "questionId", required = false) Long questionId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
//        boolean isSaved = false;
          logger.info("orderQuestionInfoWs: " +"question:"+ question + "orderId :"+orderId+"questionId: "+questionId);
        try {
                PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }

//            byte[] decodedFrontCard = null, decodedBackCard = null;
            String imgFcPath = null;
            if (questionImage != null && (!questionImage.isEmpty())) {
                imgFcPath = CommonUtil.saveImage(questionImage, profile.getPatientProfileSeqNo(), "questionImage");
                logger.info("decodedFrontCard Path: " + imgFcPath);
            }
                if(orderId == null || "0".equals(orderId)) {
               orderId = "";
            }
            if(questionId == null) {
                questionId = 0l;
            }
                
             GenralDTO genralDTO = patientProfileService.savedOrderQuestion(profile, question, imgFcPath, orderId, questionId);
//            if (isSaved == true) {
                jsonResponse.setErrorCode(1);
                  jsonResponse.setData(genralDTO);
                jsonResponse.setSuccessMessage("Your question has been sent to the dispensing pharmacy");
                jsonResponse.setErrorMessage(Constants.SUCCESS);
//            } else {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
//                return objectMapper(mapper, jsonResponse);
//            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            logger.error("Exception -> orderQuestionInfoWs", e);
        }
        return jsonResponse;
    }
//     @RequestMapping(value = "/patientQuestionWs", produces = "application/json", method = RequestMethod.POST)
//    public @ResponseBody
//    Object patientQuestionWs(@RequestHeader(value = "SecurityToken", required = false) String  securityToken,
//                             @RequestBody String json) throws IOException {
//        JsonResponse jsonResponse = new JsonResponse();
//        ObjectMapper mapper = new ObjectMapper();
//        boolean isSaved = false;
//        try {
//
//            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
//            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
//                return jsonResponse;
//            }
//             QuestionAnswerDTO questionAnsDTO = mapper.readValue(json, QuestionAnswerDTO.class);
//                    isSaved = patientProfileService.savedPatientQuestion(profile, questionAnsDTO);
//            if (isSaved == true) {
//                jsonResponse.setErrorCode(1);
//                jsonResponse.setErrorMessage(Constants.SUCCESS);
//                } else {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
//                return objectMapper(mapper, jsonResponse);
//            }
//        } catch (Exception e) {
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
//            logger.error("Exception -> orderQuestionInfoWs", e);
//        }
//        return jsonResponse;
//    }

    @RequestMapping(value = "/unSubscribeWs", method = RequestMethod.GET)
    public @ResponseBody
    Object unSubscribeWs(@RequestParam(value = "PatientId", required = false) int patientId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isUnsubscribe = false;
        try {

            isUnsubscribe = patientProfileService.unsubscribePatient(patientId);
            if (isUnsubscribe == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("UnSubscribe successfully");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("not unsubscribe.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            logger.error("Exception -> unSubscribeWs", e);
        }
        return jsonResponse;
    }
////////////////////////////////Chnages by Jandal //////////////////////

    @RequestMapping(value = "/faQuestionsWs", produces = "application/jason")
    public @ResponseBody
    Object getFAQuestionsWS(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jr = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        PatientProfile patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            if (patientProfileService.getFAQS() == null) {
                jr.setErrorCode(0);
                jr.setErrorMessage("Record not Found!!!!");
            } else {
                jr.setData(patientProfileService.getFAQS());
                jr.setErrorCode(1);
                jr.setSuccessMessage(Constants.SUCCESS);
            }

        } else {
            CommonUtil.inValidSecurityToken(jr);
        }
        return objectMapper(mapper, jr);
    }

    @RequestMapping(value = "/questionsListWs", method = RequestMethod.POST)
    public @ResponseBody
    Object orderQuestionsWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody(required = false) String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            //validate Token
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            if (!CommonUtil.isNullOrEmpty(json)) {
                QuestionAnswerDTO qadto = mapper.readValue(json, QuestionAnswerDTO.class);
                List<QuestionAnswerDTO> da = patientProfileService.getPatientQuestionListByDate(profile.getPatientProfileSeqNo(), qadto);
             
                if (da != null) { 
                    jsonResponse.setTotalRecords(da.size());
                    jsonResponse.setData(da);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setSuccessMessage(Constants.SUCCESS);
                    return objectMapper(mapper, jsonResponse);
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setSuccessMessage("No record Found!!");
                    return jsonResponse;
                }
            } else {
                List<QuestionAnswerDTO> da = patientProfileService.getPatientGenralQuestionList(profile.getPatientProfileSeqNo(),"");               
                if (da != null) {
                     jsonResponse.setTotalRecords(da.size());
                    jsonResponse.setData(da);
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setSuccessMessage(Constants.SUCCESS);
                    return objectMapper(mapper, jsonResponse);
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setSuccessMessage("No record Found!!");
                    return jsonResponse;
                }
            }
        } catch (Exception e) {
            logger.error("Exception#questionsListWs# ", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setSuccessMessage("No record Found!!");
        }
        return jsonResponse;
    }

 @RequestMapping(value = "/questionsAnswerListWs", method = RequestMethod.POST)
    public @ResponseBody
    Object questionsAnswerWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            //validate Token
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            QuestionAnswerDTO questionAnswerDTO = mapper.readValue(json, QuestionAnswerDTO.class);
            List<QuestionAnswerDTO> questionAnswerDto = patientProfileService.getQuestionAnswerImageByID(profile.getPatientProfileSeqNo(), questionAnswerDTO, "Pharmacy");

            if (questionAnswerDto != null) {
                jsonResponse.setData(questionAnswerDto);
                jsonResponse.setTotalRecords(questionAnswerDto.size());
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage(Constants.SUCCESS);

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setSuccessMessage("No record Found!!");
                return jsonResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsController ->questionsAnswerListWs", e);
        }

        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/PatientRecentActivityWs", method = RequestMethod.POST)
    public @ResponseBody
    Object PatientRecentActivity(@RequestHeader(value = "securityToken", required = false) String securityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            boolean patientpointenrollment = patientProfileService.PatientPointsEnrollment(profile);

            if (patientpointenrollment) {
                jsonResponse.setData("Enrollment for Points Sucessfull");
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage(Constants.SUCCESS);

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Enrollment for Points Failed");
                return jsonResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsController ->PatientRecentActivityWs", e);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/PatientPointsUpdateWs", method = RequestMethod.POST)
    public @ResponseBody
    Object PatientRefillPoint(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "Orderid", required = false) String orderId,
            @RequestParam(value = "type", required = false) String pointType) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        String message = patientProfileService.PatientPointsUpdate(profile.getPatientProfileSeqNo(), orderId, pointType);
        if (!message.equalsIgnoreCase("Encounter Error")) {
            jsonResponse.setData(message);
            jsonResponse.setErrorCode(1);
            jsonResponse.setSuccessMessage(Constants.SUCCESS);
            return jsonResponse;
        }
        jsonResponse.setData(message);
        jsonResponse.setErrorCode(0);
        jsonResponse.setErrorMessage("Encounter Error");
        return jsonResponse;
    }

    @RequestMapping(value = "/ResetPasswordWs", method = RequestMethod.POST)
    public @ResponseBody
    Object ResetPassword(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "Password", required = false) String password) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        //validate Token
        PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        jsonResponse.setData(patientProfileService.ResetPassword(profile, password));
        jsonResponse.setErrorCode(1);
        jsonResponse.setSuccessMessage(Constants.SUCCESS);
        return jsonResponse;
    }

    @RequestMapping(value = "/getPatientHealthHistoryWs", method = RequestMethod.POST)
    public @ResponseBody
    Object PatientHealthHistory(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientRecentHistoryDTO patientHealthHistory = mapper.readValue(json, PatientRecentHistoryDTO.class);
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
            patientProfileService.getPatientHealthHistoryByDate(profile, patientHealthHistory);
            jsonResponse.setData(patientHealthHistory);
            jsonResponse.setErrorCode(1);
            jsonResponse.setSuccessMessage(Constants.SUCCESS);
        } catch (Exception e) {
            jsonResponse.setData(e.getLocalizedMessage());
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("faild Response");
            return jsonResponse;

        }

        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/getPatientMedicationHistoryWs", method = RequestMethod.POST)
    public @ResponseBody
    Object PatientMedicationHistory(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "RxNo", required = false) String RxNo,
            @RequestParam(value = "DrugId", required = false) String DrugId) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
            jsonResponse.setData(patientProfileService.getMedicationHistory(profile.getPatientProfileSeqNo(), RxNo, DrugId));
            jsonResponse.setErrorCode(1);
            jsonResponse.setSuccessMessage(Constants.SUCCESS);
        } catch (Exception e) {
            jsonResponse.setData(e.getLocalizedMessage());
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("faild Response");
            return jsonResponse;

        }

        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/answerAlertNotificationWs", method = RequestMethod.GET)
    public @ResponseBody
    Object answerAlertNotificationWs(@RequestParam(value = "PatientId", required = false) int profileId,
            @RequestParam(value = "QuestionID", required = false) Integer questionId) throws Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isSend = false;
        try {
            if (CommonUtil.isNullOrEmpty(questionId)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("questionId is Null ");
                return jsonResponse;
            }
            PatientProfile profile = patientProfileService.getPatientProfileByProfileId(profileId);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Patient Id is null.");
                return jsonResponse;
            }
            QuestionAnswer dbQuestionAnswer = patientProfileService.validateQuestionAnswerByQuestionId(profileId, new Long(questionId));
            if (dbQuestionAnswer != null) {

                isSend = patientProfileService.sendPushNotification(dbQuestionAnswer, profile, "Answer Alert", "Answer Alert");
                if (isSend == true) {
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setSuccessMessage("Notifcations has been sent successfully.");
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Notification not sent.");
                    return objectMapper(mapper, jsonResponse);
                }
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("no record found.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Notification not sent.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/getPatientActivityHistoryByDateWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object PatientActivityHistoryByDate(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(json);

            JsonNode startDateNode = rootNode.path("startDate");
            String startDate = startDateNode.asText();

            JsonNode endDateNode = rootNode.path("endDate");
            String endDate = endDateNode.asText();

            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
            List<ActivityHistoryDTO> Activites = patientProfileService.getActivityHistoryByDate(profile.getPatientProfileSeqNo(), DateUtil.changeDateFormat(startDate, "MM/dd/yyyy", "yyyy-MM-dd"), DateUtil.changeDateFormat(endDate, "MM/dd/yyyy", "yyyy-MM-dd"));
            jsonResponse.setData(Activites);
            jsonResponse.setErrorCode(1);
            jsonResponse.setSuccessMessage(Constants.SUCCESS);
        } catch (Exception e) {
            jsonResponse.setData(e.getLocalizedMessage());
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("faild Response");
            return jsonResponse;

        }

        return objectMapper(mapper, jsonResponse);
    }

//    @RequestMapping(value = "/DeletePatientByMobileOrEmailWs", method = RequestMethod.GET)
//    public @ResponseBody
//    Object DeletePatientByMobileNumberWs(@RequestParam(value = "identity", required = false) String Identity) throws Exception {
//
//        JsonResponse jsonResponse = new JsonResponse();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            if (CommonUtil.isNullOrEmpty(Identity)) {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("Invalid Input");
//                return jsonResponse;
//            }
//            PatientProfile profile = patientProfileService.getPatientProfileByEmailOrPhone(Identity);
//
//            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("Record Not Found");
//                return jsonResponse;
//            }
//           patientProfileService.delRecord(profile);
//           jsonResponse.setErrorCode(1);
//           jsonResponse.setSuccessMessage("Record Sucessfully Deleted");
//        } catch (Exception e) {
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage("Record Not Deleted.");
//            return objectMapper(mapper, jsonResponse);
//        }
//        return objectMapper(mapper, jsonResponse);
//    }
//
    @RequestMapping(value = "/getSurveyWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object getSurveyWs(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile dbProfile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token");
                return objectMapper(mapper, jsonResponse);
            }
            SurveyDto surveyUserInput = mapper.readValue(json, SurveyDto.class);
            SurveyDto SurveyDto = patientProfileService.getSurvey(surveyUserInput.getSurveyId());
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(SurveyDto);
            jsonResponse.setSuccessMessage("Record Fetched succefully.");

        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Record not fetched.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

//    @RequestMapping(value = "/DeletePracticeByPhoneWs", method = RequestMethod.POST)
//    public @ResponseBody
//    Object DeletePracticeByPhone(@RequestParam(value = "identity", required = false) String Identity) throws Exception {
//
//        JsonResponse jsonResponse = new JsonResponse();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            if (CommonUtil.isNullOrEmpty(Identity)) {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("mobileNo is Null ");
//                return jsonResponse;
//            }
//            Practices practice=patientProfileService.getPracticebyIdentity(Identity);
//            if (practice == null || CommonUtil.isNullOrEmpty(practice.getId())) {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage("Record Not Found");
//                return jsonResponse;
//            }
//            patientProfileService.deleteUsersbyPracticeId(practice);
//            
//           patientProfileService.deletePatientProfilebyPracticeId(practice);
//           jsonResponse.setErrorCode(1);
//           jsonResponse.setSuccessMessage("Record Sucessfully Deleted");
//        } catch (Exception e) {
//            jsonResponse.setErrorCode(0);
//            jsonResponse.setErrorMessage("Record Not Deleted.");
//            return objectMapper(mapper, jsonResponse);
//        }
//        return objectMapper(mapper, jsonResponse);
//    }
//    @RequestMapping(value = "/rewardsPointWs", produces = "application/json", method = RequestMethod.POST)
//    public @ResponseBody
//       Object rewardsPointWs(@RequestHeader(value = "securityToken", required = false)String securityToken, @RequestBody String json)throws Exception{
//           JsonResponse jsonResponse = new JsonResponse();
//           ObjectMapper mapper = new ObjectMapper();
//           try {
//              ///===============Validation of PatientProfile by Security Token=================/// 
//             PatientProfile dbProfile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
//             if(dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())){
//                 jsonResponse.setErrorCode(0);
//                 jsonResponse.setErrorMessage("Invalid Security Token");
//                 return objectMapper(mapper, jsonResponse);
//             }
//             ///===================Storing/mapping json string data to RewardsPointDTO object===========///
//             RewardsPointDTO rewardsPointDTO = mapper.readValue(json, RewardsPointDTO.class);
//             ///================Main funtion to store and update Data===================/////
//             rewardsPointDTO = patientProfileService.rewardsPointUpdate(rewardsPointDTO, dbProfile, null);
//                 jsonResponse.setErrorCode(1);
//                 jsonResponse.setData(rewardsPointDTO);
//                 jsonResponse.setSuccessMessage("Record added successfully.");
//             
//        } catch (Exception e) {
//                 jsonResponse.setErrorCode(0);
//                 jsonResponse.setErrorMessage("Failed to add record.");
//                 return objectMapper(mapper, jsonResponse);
//    }
//      return objectMapper(mapper, jsonResponse);
//  }
    @RequestMapping(value = "/rewardsHistoryListWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object rewardsHistoryListWs(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile dbProfile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token");
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);

            JsonNode startDateNode = rootNode.path("startDate");
            String startDate = startDateNode.asText();

            JsonNode endDateNode = rootNode.path("endDate");
            String endDate = endDateNode.asText();

            List<RewardHistoryDTO> rewardHistory = patientProfileService.getRewardHistoryListByPatientId(dbProfile.getPatientProfileSeqNo(), DateUtil.changeDateFormat(startDate, "MM/dd/yyyy", "yyyy-MM-dd"), DateUtil.changeDateFormat(endDate, "MM/dd/yyyy", "yyyy-MM-dd"));
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(rewardHistory);
            jsonResponse.setTotalRecords(rewardHistory.size());
            jsonResponse.setPatientId(dbProfile.getPatientProfileSeqNo());
            jsonResponse.setSuccessMessage(Constants.SUCCESS);

        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.FAILURE);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/deleteNotificationMessageWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object deleteNotificationMessage(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile dbProfile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token");
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);

            JsonNode messageId = rootNode.path("messageId");
            int notificationMessageId = messageId.asInt();
            String result = patientProfileService.deleteNotificationMessageByMessageId(dbProfile, notificationMessageId);
            jsonResponse.setErrorMessage(result);
            jsonResponse.setData(patientProfileService.getNotificationMessagesByProfileId(dbProfile.getPatientProfileSeqNo()));
            jsonResponse.setErrorCode(1);
            // jsonResponse.setTotalRecords(rewardHistory.size());
            jsonResponse.setPatientId(dbProfile.getPatientProfileSeqNo());
            jsonResponse.setSuccessMessage(Constants.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsControllers->deleteNotificationMessageByMessageId", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.FAILURE);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/acceptTermsAndConditionWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object acceptTermsAndCondition(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile dbProfile = patientProfileService.getUnEnrolledProfileBySecurityToken(securityToken);
            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token");
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);

            JsonNode status = rootNode.path("Status");
            String value = status.asText();
            jsonResponse.setData(patientProfileService.enrollPatientByTermAndConditionValue(dbProfile, value));
            jsonResponse.setErrorCode(1);
            // jsonResponse.setTotalRecords(rewardHistory.size());
            jsonResponse.setPatientId(dbProfile.getPatientProfileSeqNo());
            jsonResponse.setSuccessMessage(Constants.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsControllers->deleteNotificationMessageByMessageId", e);
//            jsonResponse.setData(e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.FAILURE);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/surveyPlaceNotificationWs", method = RequestMethod.GET)
    public @ResponseBody
    Object orderPlaceNotificationWs(@RequestParam(value = "PatientId", required = false) int profileId
    //            ,
    //            @RequestParam(value = "OrderId", required = false) String orderId
    ) throws Exception {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isSend = false;
        try {
            PatientProfile profile = patientProfileService.getPatientProfileByProfileIdWithOutStatus(profileId);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Patient Id is null.");
                return jsonResponse;
            }

            isSend = patientProfileService.sendNotificationForSurvey(profile.getPatientProfileSeqNo(), "Survey", "Compliance Survey");
            if (isSend == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage("Notifcations has been sent successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Notificaation not sent.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Notificaation not sent.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/getSurveyDetailByIdWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object getSurveyListByPatientSecurityToken(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile dbProfile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token");
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);
            JsonNode assignedSurveyId = rootNode.path("assignedSurveyId");
            Long value = assignedSurveyId.asLong();
            AssignedSurveyInfoDTO survyList = patientProfileService.getSurveyDetailByAssignedSurveyId(dbProfile.getPatientProfileSeqNo(), value);
            jsonResponse.setData(survyList);
            System.out.print(survyList);
            jsonResponse.setErrorCode(1);
            // jsonResponse.setTotalRecords(rewardHistory.size());
            jsonResponse.setPatientId(dbProfile.getPatientProfileSeqNo());
            jsonResponse.setSuccessMessage(Constants.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsControllers->getSurveyDetailByIdWs", e);
//            jsonResponse.setData(e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.FAILURE);
            return objectMapper(mapper, jsonResponse);
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/getSurveyListByPatientIdWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object getSurveyListByPatientIdWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile dbProfile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (dbProfile == null || CommonUtil.isNullOrEmpty(dbProfile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token");
                return objectMapper(mapper, jsonResponse);
            }
            List<AssignedSurveyInfoDTO> survyList = patientProfileService.getAssignedSurveyTitleList(dbProfile.getPatientProfileSeqNo());
            jsonResponse.setData(survyList);
            jsonResponse.setErrorCode(1);
            // jsonResponse.setTotalRecords(rewardHistory.size());
            jsonResponse.setPatientId(dbProfile.getPatientProfileSeqNo());
            jsonResponse.setSuccessMessage(Constants.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsControllers->getSurveyListByPatientIdWs", e);
//            jsonResponse.setData(e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.FAILURE);
            return objectMapper(mapper, jsonResponse);
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/submittSurveyResponseWs", method = RequestMethod.POST) 
    public @ResponseBody
    Object submittSurveyResponse(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
            Gson gson = new Gson();
            AssignedSurveyLogsDTO assignedSurveyLogsDto = patientProfileService.jsonMappingToObjects(json, gson);
            ComplianceRewardPointDTO cassignedSurveyLogsDto = patientProfileService.setAssignedSurveyDeatils(profile, assignedSurveyLogsDto);
//            if (cassignedSurveyLogsDto != null) {
            boolean isSend = patientProfileService.getSurvyPushNotification(assignedSurveyLogsDto.getSurveyId(), profile, "Submitt Survey", "Compliance Survey");
            if (isSend == false) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Notification not Sent.");
                return jsonResponse;
            }
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(cassignedSurveyLogsDto);
            jsonResponse.setSuccessMessage("Survey Successfully Submitted. ");
//            }         
        } catch (Exception e) {
//            jsonResponse.setData(e.getLocalizedMessage());
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("faild to saved.");
            return jsonResponse;

        }

        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/sendNotificationByPharmacyWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object sendRxAdvisory(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return jsonResponse;
            }
            OrderDetailDTO ordDto = mapper.readValue(json, OrderDetailDTO.class);

            if (CommonUtil.isNullOrEmpty(ordDto.getId())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("order is null");
                return jsonResponse;
            }
            logger.info("ordDto.getId() is :"+ordDto.getId());
            Order dbOrder = patientProfileService.getOrderByOrderId(ordDto.getId());
            if (dbOrder == null) {
                logger.info("No order exist against this ord# " + ordDto.getId());
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("No order exist against this ord# " + ordDto.getId());
                return jsonResponse;
            }
            boolean isSend;//= false;
            String name = "";
            switch (ordDto.getOrderStatusId()) {
                case Constants.ORDER_STATUS.CANCEL_ORDER_ID:
                  isSend =  patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Order_Cancelled.getValue(), EventTitleEnum.Order_Cancel.getValue());
                    name = ResponseTitleEnum.Order_Cancelled.getValue();
//                    isSend = true;
                    break;
                case Constants.ORDER_STATUS.UNDER_REVIEW_ID:
                  isSend =  patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Under_Review.getValue(), EventTitleEnum.Order_Under_Review.getValue());
                    name = EventTitleEnum.Order_Under_Review.getValue();
//                    isSend = true;
                    break;
                case Constants.ORDER_STATUS.ASSISTANCE_DC_ID:
                  isSend =  patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Assistanc_DC.getValue(), EventTitleEnum.Assistance_Dc.getValue());
                    name = ResponseTitleEnum.Assistanc_DC.getValue();
//                    isSend = true;
                    break;

                case Constants.ORDER_STATUS.MEDICAL_ADVISORY_ID:
                   isSend = patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Medical_Advisory.getValue(), EventTitleEnum.Rx_Advisory.getValue());
                    name = ResponseTitleEnum.Medical_Advisory.getValue();
//                    isSend = true;
                    break;
                case Constants.ORDER_STATUS.PATIENT_MESSGES_ID:
                   isSend = patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Ph_Direct_Messgae.getValue(), EventTitleEnum.Pharmacy_Direct_Messgae.getValue());
                    name = EventTitleEnum.Pharmacy_Direct_Messgae.getValue();
//                    isSend = true;
                    break;
                case Constants.ORDER_STATUS.DC_PT_DISCOUNTINUE:
                  isSend =  patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.DC_PT_Discontinue.getValue(), EventTitleEnum.DC_PT.getValue());
                    name = EventTitleEnum.DC_PT.getValue();
//                    isSend = true;
                    break;
                    case Constants.ORDER_STATUS.FILLED_ID:
                  isSend =  patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Filled.getValue(), EventTitleEnum.Filled_Prescription.getValue());
                    name = EventTitleEnum.Filled_Prescription.getValue();
//                    isSend = true;
//                    boolean isFirstOrder = patientProfileService.checkNotificationStatus(profile);
                    if (profile.getCbdStatus() == 0) {
                        logger.info("...Start sending CBD Initial Survey ...");
                        patientProfileService.updateSurveyLogs(profile, Constants.CBD_SURVY_ID);
                        boolean surveySend = patientProfileService.sendCBDSurvey(profile.getPatientProfileSeqNo(), Constants.CBD_INTIAL_SURVEY, Constants.CBD);                        
                        if(surveySend) {
                         profile.setCbdStatus(1);
                         profile.setUpdatedOn(new Date());
                         patientProfileService.update(profile);
                         isSend = true;
                        }
                        logger.info("...End sending CBD Initial Survey ...");
                    }
                    break;
                case Constants.ORDER_STATUS.MBR_TO_PAY_ID:
                   isSend = patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Rx_Order.getValue(), EventTitleEnum.Order_Placed.getValue());
                    name = "MBR " + EventTitleEnum.Order_Placed.getValue();
//                    isSend = true;
                    break;
                case Constants.ORDER_STATUS.REFILL_ID:
                  isSend =  patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.Rx_Refill_Initiated.getValue(), EventTitleEnum.Pharmacy_Refill.getValue());
                    name = ResponseTitleEnum.Rx_Refill_Initiated.getValue();
//                    isSend = true;
                    break;
                 case Constants.ORDER_STATUS.HCP_DIRECT_MSG:
                  isSend =  patientProfileService.sendPushNotificationOnPlaceOrder(profile, dbOrder, ResponseTitleEnum.HCP_DIRECT_MESSAGE.getValue(), EventTitleEnum.HCP_DIRECT_MSG.getValue());
                    name = ResponseTitleEnum.HCP_DIRECT_MESSAGE.getValue();
//                    isSend = true;
                    break;
                default:
                    isSend = false;
            }

            if (isSend == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage(name + ": Notifcations has been sent successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Notificaation not sent.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Notificaation not sent.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/nextRefillReminderManuallyWs", method = RequestMethod.GET)
    public @ResponseBody
    Object sendRefillReminderMsg(@RequestParam(value = "orderId", required = false) String orderId) throws Exception {
        String response2 = "";
        JsonResponse response = new JsonResponse();
        try {
//               if(CommonUtil.isNullOrEmpty(orderId)){
//                response.setErrorCode(0);
//                response.setErrorMessage("Order Id is null");
//                return response;
//               }
            if (CommonUtil.isNullOrEmpty(orderId)) {
                refillReminderService.sendNextRefillReminder(new Date(), null, null, null);
                response.setErrorCode(1);
                response.setSuccessMessage("Msg send successfully.");
            }

            boolean isMsgSend = supportDelegate.isRefillReminderMsgSend(orderId);
            if (isMsgSend) {
                response.setErrorCode(1);
                response.setSuccessMessage("Msg send successfully.");
            } else {
                response.setErrorCode(0);
                response.setErrorMessage("process.unsuccessful");
                return response;
            }

        } catch (Exception e) {
            logger.error("Exception# sendRefillMsg# ", e);
            response.setErrorCode(0);
            response.setErrorMessage("Exception process.unsuccessful");
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/patientMessgeReplyWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object patientMessgeReplyWs(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            PharmacyPatientMessageDTO pharmacyPatientMessageDTO = mapper.readValue(json, PharmacyPatientMessageDTO.class);
            boolean isSaved;
            isSaved = patientProfileService.savepharmacyPatientMessageReply(pharmacyPatientMessageDTO);
            if (isSaved == true) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Reply Successfully Submitted.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Exception not Submitted Reply");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            logger.error("Exception# sendRefillMsg# ", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Exception process.unsuccessful");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/repaeatRefillReminderManuallyWs", method = RequestMethod.GET)
    public @ResponseBody
    Object repaeatRefillReminderManuallyWs() throws Exception {
        String response2 = "";
        JsonResponse response = new JsonResponse();
        try {
            boolean isMsgSend = false;
            //MBR To pay
//            isMsgSend = refillReminderService.sendRepeatRefillReminderForMBR();
            //Repeat Refill
//            isMsgSend = refillReminderService.sendRepeatRefillReminder();
            if (isMsgSend) {
                response.setErrorCode(1);
                response.setSuccessMessage("Msg send successfully.");
            } else {
                response.setErrorCode(0);
                response.setErrorMessage("process.unsuccessful");
                return response;
            }

        } catch (Exception e) {
            logger.error("Exception# sendRefillMsg# ", e);
            response.setErrorCode(0);
            response.setErrorMessage("Exception process.unsuccessful");
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/sendAppUrlLinkSMSWs", method = RequestMethod.GET)
    public @ResponseBody
//             Object sendAppUrlLinkSMSWs(@RequestHeader(value = "securityToken", required = true) String securityToken)throws Exception {
    Object sendAppUrlLinkSMSWs(@RequestParam(value = "mobileNumber", required = true) String mobileNumber) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile profile = patientProfileService.getPatientProfileByPhone(mobileNumber);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("This phone# is not registered.");
                return objectMapper(mapper, jsonResponse);
            }
            boolean isMsgSend = patientProfileService.sendAppLinks(profile.getMobileNumber());
            if (isMsgSend == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage("Messge sent Succefully.");//Message("Exception process.unsuccessful");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Exception process.unsuccessful");
                return objectMapper(mapper, jsonResponse);
            }

        } catch (Exception e) {
            logger.error("Exception# sendRefillMsg# ", e);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Exception process.unsuccessful");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/addvistorInfoWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addVistorInfo(@RequestHeader(value = "securityToken", required = true) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            PatientDependentDTO patientDependentDTO = mapper.readValue(json, PatientDependentDTO.class);
            patientDependentDTO = patientProfileService.addAppointmentPatientDetail(profile, patientDependentDTO);
            if (patientDependentDTO != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(patientDependentDTO);
                jsonResponse.setSuccessMessage("Appointment Successfully Submitted.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Appointment Successfully Submitted.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/wayOfCommunicationWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object wayOfCommunication(@RequestHeader(value = "securityToken", required = true) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            AppointmentRequestDTO appointmentRequestDTO = mapper.readValue(json, AppointmentRequestDTO.class);
            boolean isSaved = patientProfileService.addWayOfCommunication(profile, appointmentRequestDTO);
            if (isSaved == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage("Updated Successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not updated.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/getDoctorsListWs", method = RequestMethod.GET)
    public @ResponseBody
    Object getDoctorsList(@RequestHeader(value = "securityToken", required = true) String securityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
 
             PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            List<PracticesDTO> lst = patientProfileService.getDoctorsList(profile.getPatientProfileSeqNo());
            if (lst != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(lst);
                jsonResponse.setTotalRecords(lst.size());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not fetched.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/getDiseaseListWs", method = RequestMethod.GET)
    public @ResponseBody
    Object getDiseaseList() throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            List<DiseaseDTO> lst = patientProfileService.getDisease();
            if (lst != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(lst);
                jsonResponse.setTotalRecords(lst.size());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not fetched.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/addAppointmentWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addAppointment(@RequestHeader(value = "securityToken", required = true) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            PatientAppointmentDTO patientAppointmentDTO;
            patientAppointmentDTO = mapper.readValue(json, PatientAppointmentDTO.class);
            patientAppointmentDTO = patientProfileService.addAppointment(profile, patientAppointmentDTO);
            if (patientAppointmentDTO != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(patientAppointmentDTO);
                jsonResponse.setSuccessMessage("Updated Successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not updated.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/cancelAppointmentWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object cancelAppointment(@RequestHeader(value = "securityToken", required = true) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            AppointmentRequestDTO appointmentRequestDTO = mapper.readValue(json, AppointmentRequestDTO.class);
            boolean isSaved = patientProfileService.cancelAppointment(profile, appointmentRequestDTO);
            if (isSaved == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage("Your Appointment has been cancelled.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not cancelled.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }

    @RequestMapping(value = "/rxReporterNotificationWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object rxReporterNotification(@RequestBody String data) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            RxReporterJsonDTO body = mapper.readValue(data, RxReporterJsonDTO.class);

            for (int patientId : body.listPatient) {
                PatientProfile dbProfile = patientProfileService.getPatientProfileByProfileIdWithOutStatus(patientId);
                if (dbProfile != null) {
                    for (String ord : body.getListOrders()) {
                        Order ordr = patientProfileService.getOrderByIdAndPatientId(ord, dbProfile.getPatientProfileSeqNo());
                        if (ordr != null) {
                            boolean isSend = patientProfileService.sendPushNotificationforRxReporter(dbProfile, ordr, ResponseTitleEnum.RX_REPORTER.getValue(), EventTitleEnum.RX_REPORTER.getValue());
                            if (isSend) {
                                jsonResponse.setErrorCode(1);
                                jsonResponse.setErrorMessage("Notifcations has been sent successfully.");
                            } else {
                                jsonResponse.setErrorCode(0);
                                jsonResponse.setErrorMessage("Notificaation not sent.");
                                return objectMapper(mapper, jsonResponse);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }
     @RequestMapping(value = "/getReporterCommunicationWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object getReporterCommunicationWs(@RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            ReporterMessagesDTO reporterMessagesDTO = mapper.readValue(json, ReporterMessagesDTO.class);
            List<ReporterMessagesDTO> lstreporterMessagesDTO = patientProfileService.getReporterCommunication(reporterMessagesDTO.getUserFrom(), reporterMessagesDTO.getUserTo());
            if (lstreporterMessagesDTO != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(lstreporterMessagesDTO);
                jsonResponse.setTotalRecords(lstreporterMessagesDTO.size());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not fetched.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }
   @RequestMapping(value = "/submittRxReporterSurveyResponseWs", method = RequestMethod.POST) 
    public @ResponseBody
    Object submittRxReporterSurveyResponse(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
            Gson gson = new Gson();
            RxReporterSurveyResponseDTO assignedSurveyLogsDto = patientProfileService.jsonMappingToObjectsForRxReporterSurvey(json, gson);
            Boolean isSaved = patientProfileService.saveResponseRxReporterSurvey(profile.getPatientProfileSeqNo(), assignedSurveyLogsDto);
          if(isSaved == true){
                      jsonResponse.setErrorCode(1);
            jsonResponse.setSuccessMessage("Survey Successfully Submitted. ");
          }else {
             jsonResponse.setErrorCode(0);
            jsonResponse.setSuccessMessage("faild to saved. ");
          }       
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }
     @RequestMapping(value = "/sendRxReporterClinicalAdvisoryNotificationWs", method = RequestMethod.POST) 
    public @ResponseBody
    Object sendRxReporterClinicalAdvisoryNotification(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode userInputNode = rootNode.path("prescriptionId");
            Integer prescriptionId = userInputNode.asInt();
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
             
            boolean isSaved = patientProfileService.sendRxReporterNotificationforAdvisory(profile, ResponseTitleEnum.RX_REPORTER_ADVISORY.getValue(), EventTitleEnum.REPORTER_MEDICAL_ADVISORY.getValue());

          if(isSaved == true){
            jsonResponse.setSuccessCode(1);
//            jsonResponse.setSuccessMessage("Notifcations has been sent successfully. ");
          }else {
            jsonResponse.setErrorCode(0);
//            jsonResponse.setSuccessMessage("faild to saved. ");
          }       
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(objectMapper, jsonResponse);
    }
  @RequestMapping(value = "/cancelOrderWs",produces = "application/json", method = RequestMethod.POST) 
    public @ResponseBody
    Object cancelOrderWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode userInputNode = rootNode.path("orderId");
            String orderId = userInputNode.asText();
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
           if (CommonUtil.isNullOrEmpty(orderId)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Enter valid Order Id.");
                return jsonResponse;
            }
            Order ord = patientProfileService.getOrderById(orderId);
            if(ord.getOrderStatus().getId()== Constants.ORDER_STATUS.CANCEL_ORDER_ID){
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Your order already has been cancelled.");
                return jsonResponse;
            }
            if(ord.getOrderStatus().getId() != Constants.ORDER_STATUS.MBR_TO_PAY_ID){
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Your Order can't be cancelled now since its status is " + ord.getOrderStatus());
                return jsonResponse;
            }
            boolean isCanceled = patientProfileService.isOrderCancel(orderId, profile.getPatientProfileSeqNo());

          if(isCanceled == true){
              patientProfileService.updateOrderHistory(orderId, 27);
            jsonResponse.setSuccessCode(1);
            jsonResponse.setSuccessMessage("Your order has been cancelled. ");
          }else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setSuccessMessage("faild to Cancelled. ");
          }       
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(objectMapper, jsonResponse);
    }
     @RequestMapping(value = "/getUserMessgeWs", method = RequestMethod.POST)
    public @ResponseBody
    Object getUserMessge(@RequestHeader(value = "securityToken", required = false) String securityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            //validate Token
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<MessageThreadDTO> messageThreadsDto = patientProfileService.getUserMessageByID(profile.getPatientProfileSeqNo(), false, "Pharmacy");

            if (messageThreadsDto != null) {
                jsonResponse.setData(messageThreadsDto);
                jsonResponse.setTotalRecords(messageThreadsDto.size());
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage(Constants.SUCCESS);

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setSuccessMessage("No record Found!!");
                return jsonResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsController ->getUserMessgeWs", e);
        }

        return objectMapper(mapper, jsonResponse);
    }
     @RequestMapping(value = "/updateOrderAndGenralQuestionsWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateOrderAndGenralQuestions(@RequestHeader(value = "securityToken", required = false) String securityToken,
          @RequestBody String json) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
       
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }

        QuestionUpdtaeDTO  questionUpdtaeDTO = objectMapper.readValue(json, QuestionUpdtaeDTO.class);
         boolean isUpdate =   patientProfileService.isMarkAsReadOrderAndGneralQuestions(questionUpdtaeDTO, patientProfile.getPatientProfileSeqNo());
         if(isUpdate == true){
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage("Record Updated successfully");

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setSuccessMessage("Record Not updated!");
                return jsonResponse;
            }
         
        return objectMapper(objectMapper, jsonResponse);
    }
      @RequestMapping(value = "/getReporterSessionListWs", method = RequestMethod.GET)
    public @ResponseBody
    Object getReporterSessionList(@RequestHeader(value = "securityToken", required = true) String securityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
 
             PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            List<PracticesDTO> lst = patientProfileService.getReporterSessionDetialList(profile);
            if (lst != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(lst);
                jsonResponse.setTotalRecords(lst.size());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not fetched.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }
//    This Api will fetch "reporter_message" table data against session Id
     @RequestMapping(value = "/getReporterMessageListWs", method = RequestMethod.GET)
    public @ResponseBody
    Object getReporterMessageLis(@RequestParam(value = "sessionId", required = true) Integer sessionId) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {

            List<ReporterMessagesDTO> lst = patientProfileService.getReporterDetial(sessionId);
            if (lst != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(lst);
                jsonResponse.setTotalRecords(lst.size());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not fetched.");
                return jsonResponse;
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(mapper, jsonResponse);
    }
       @RequestMapping(value = "/optOutRefillReminderWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object optOutRefillReminder(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode userInputNode = rootNode.path("rxNumber");
            String rxNumber = userInputNode.asText();
            PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid Security Token.");
                return jsonResponse;
            }
             
            boolean isUpdate = patientProfileService.optOutRefillReminder(profile, rxNumber);

          if(isUpdate == true){
            jsonResponse.setSuccessCode(1);
            jsonResponse.setSuccessMessage("This RxNumber opted out. ");
          }else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setSuccessMessage("faild to update. ");
          }       
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return jsonResponse;
        }
        return objectMapper(objectMapper, jsonResponse);
    }
    @RequestMapping(value = "/sendRefillReminderManuallyWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object sendRefillReminderManually(@RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            OrdersDTO body = mapper.readValue(json, OrdersDTO.class);// new Gson().fromJson(jsonArray.toString(), listType);

            if (CommonUtil.isNullOrEmpty(body.listOfOrders)) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Order list is null");
                logger.info("Order list is null");
                return objectMapper(mapper, jsonResponse);
            }             
                boolean isSend = refillReminderService.sendRefillReminderByPharmacy(body);
                if (isSend == true) {
                    NotificationMessages notificationMsg = new NotificationMessages();
                    notificationMsg.setCreatedBy("Pharmacy");
                    notificationMsg.setUpdatedOn(new Date());
                    patientProfileService.update(notificationMsg);
                    
                    jsonResponse.setErrorCode(1);
                    jsonResponse.setErrorMessage("Refill Reminder sent.");
                } else {
                    jsonResponse.setErrorCode(0);
                    jsonResponse.setErrorMessage("Refill Reminder not sent.");
                    return objectMapper(mapper, jsonResponse);
                }
      
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("process.unsuccessful");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }
    @RequestMapping(value = "/getRefillDoneOrdersListWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object getRefillDoneOrders(@RequestHeader(value = "securityToken", required = true)String securityToken,
            @RequestBody String json) throws Exception {
     JsonResponse jsonResponse = new JsonResponse();
     ObjectMapper mapper = new ObjectMapper();
        try {
                 PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
             JsonNode rootNode = mapper.readTree(json);
            JsonNode userInputNode = rootNode.path("rxNumber");
            String rxNumber = userInputNode.asText();
            boolean ord = patientProfileService.isOrderExist(rxNumber);
            if (ord != true) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("No order Exisit against this RxNumber.");
                return objectMapper(mapper, jsonResponse);
            }
            List<OrderDetailDTO> lst = patientProfileService.getAllRefillDoneOrdersList(rxNumber, profile.getPatientProfileSeqNo());
            if (lst != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(lst);
                jsonResponse.setTotalRecords(lst.size());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not fetched.");
                return jsonResponse;
            }
        } catch (Exception e) {
        }
         return objectMapper(mapper, jsonResponse);
    }
     @RequestMapping(value = "/getRefillDueOrdersListWs", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    Object getRefillDueOrders(@RequestHeader(value = "securityToken", required = true)String securityToken) throws Exception {
     JsonResponse jsonResponse = new JsonResponse();
     ObjectMapper mapper = new ObjectMapper();
        try {
                 PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }

            List<OrderDetailDTO> lst = patientProfileService.getAllRefillAbleOrdersList(profile.getPatientProfileSeqNo());
            if (lst != null) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(lst);
                jsonResponse.setTotalRecords(lst.size());
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Not fetched.");
                return jsonResponse;
            }
        } catch (Exception e) {
        }
         return objectMapper(mapper, jsonResponse);
    }
    /*--------------------------Replica API's------------------------ */
     /*  /getNotificationWs    with zero (for All non-Archived msgs)
       /getArchivedNotificationWs    with 1 (for All Archived Msgs)
       /updateNotificationMessagesWs (change is doen according new order object)*/
    
    
         @RequestMapping(value = "/getNotificationWs", produces = "application/json")
    public @ResponseBody
    Object getNotificationWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        logger.info("Security Token is: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return jsonResponse;
        }
        if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
            logger.info("Profile Id is: " + patientProfile.getPatientProfileSeqNo());
             logger.info("Start..Going to get data from Database for Controller->getNotificationWs"+new Date());
            jsonResponse.setData(patientProfileService.getNotificationWs(patientProfile.getPatientProfileSeqNo()));
//            jsonResponse.setData(patientProfileService.getNotificationDetialWs(patientProfile.getPatientProfileSeqNo()));
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
            logger.info("Sucess"+new Date());
        } else {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            logger.info(Constants.INVALID_SECURITY_TOKEN);
        }
        return jsonResponse;
    }  
    
      @RequestMapping(value = "/updateNotificationMessagesWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object updateNotificationMessagesWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestParam(value = "messageId", required = false) Integer messageId,
            @RequestParam(value = "isRead", required = false) int isRead,
            @RequestParam(value = "archiveValue", required = false) int archiveValue) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token is: " + securityToken + " messageId is: " + messageId + " archiveValue:: " + archiveValue);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        }
        NotificationMessages notificationMessages;
        notificationMessages = patientProfileService.getNotificationMessagesByMessageId(messageId);
        if (notificationMessages != null) {
            notificationMessages = patientProfileService.updateNotificationMessages(messageId, archiveValue, notificationMessages, isRead);
            if (notificationMessages != null) {
                jsonResponse.setData(patientProfileService.getNotificationMessagesByProfileId(patientProfile.getPatientProfileSeqNo()));
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Success");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("There is some problem to save record.");
                logger.info("There is some problem to update record.");
            }
        } else {
            logger.info("NotificationMessage cannot found against this  id ");
            setEmptyData(jsonResponse);
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("NotificationMessage cannot found against this  id");
        }
//        }
        return objectMapper(objectMapper, jsonResponse);
    }
     @RequestMapping(value = "/addMessgeInfoForHcpWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object addMessgeInfoForHcpWs(@RequestHeader(value = "SecurityToken", required = false) String securityToken,
            @RequestParam(value = "message", required = false) String question,
            @RequestParam(value = "messageImage", required = false) MultipartFile questionImage,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "prescriberId", required = false) Long prescriberId,
            @RequestParam(value = "physicianId", required = false) Integer physicianId,
            @RequestParam(value = "questionId", required = false) Long questionId) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
//        boolean isSaved = false;
          logger.info("addMessgeInfoForHcpWs: " +"message:"+ question + "orderId :"+orderId+"questionId: "+questionId+"prescriberId"+prescriberId);
        try {
                PatientProfile profile = patientProfileService.getPatientProfileBySecurityToken(securityToken);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Invalid security Token");
                return objectMapper(mapper, jsonResponse);
            }
            String imgFcPath = null;
            if (questionImage != null && (!questionImage.isEmpty())) {
                imgFcPath = CommonUtil.saveImage(questionImage, profile.getPatientProfileSeqNo(), "questionImage");
                logger.info("decodedFrontCard Path: " + imgFcPath);
            }
                if(orderId == null || "0".equals(orderId)){
               orderId = "";
            }
            if(questionId == null) {
                questionId = 0l;
            }
            if(prescriberId == null){
                prescriberId= 0l;
            }
                
             GenralDTO genralDTO = patientProfileService.savedHcpQuestion(profile, question, imgFcPath, orderId, questionId, prescriberId, physicianId);            
//             if (isSaved == true) {
                jsonResponse.setData(genralDTO);
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage("Your question has been sent to your HCP.");
                jsonResponse.setErrorMessage(Constants.SUCCESS);
//            } else {
//                jsonResponse.setErrorCode(0);
//                jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
//                return objectMapper(mapper, jsonResponse);
//            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_RECORD);
            logger.error("Exception -> addMessgeInfoForHcpWs", e);
        }
        return jsonResponse;
    }
        @RequestMapping(value = "/getHcpUserMessgeWs", method = RequestMethod.POST)
    public @ResponseBody
    Object getHcpUserMessgeWs(@RequestHeader(value = "securityToken", required = false) String securityToken) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            //validate Token
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            List<MessageThreadDTO> messageThreadsDto = patientProfileService.getUserMessageByID(profile.getPatientProfileSeqNo(), true, "HCP");
            List<QuestionAnswerDTO> questionList = patientProfileService.getPatientGenralQuestionList(profile.getPatientProfileSeqNo(),"HCP");
            GenralDTO dto = new GenralDTO();
            dto.setOrderDetial(messageThreadsDto);
            dto.setGenralQuestionDetial(questionList);
            if (messageThreadsDto != null) {
                jsonResponse.setData(dto);
                jsonResponse.setTotalRecords(messageThreadsDto.size());
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage(Constants.SUCCESS);

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setSuccessMessage("No record Found!!");
                return jsonResponse; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsController ->getHcpUserMessgeWs", e);
        }

        return objectMapper(mapper, jsonResponse);
    }   
     @RequestMapping(value = "/hcpQuestionsAnswerListWs", method = RequestMethod.POST)
    public @ResponseBody
    Object hcpQuestionsAnswerListWs(@RequestHeader(value = "securityToken", required = false) String securityToken,
            @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            //validate Token
            PatientProfile profile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (profile == null || CommonUtil.isNullOrEmpty(profile.getPatientProfileSeqNo())) {
                return jsonResponse;
            }
            QuestionAnswerDTO questionAnswerDTO = mapper.readValue(json, QuestionAnswerDTO.class);
            List<QuestionAnswerDTO> questionAnswerDto = patientProfileService.getQuestionAnswerImageByID(profile.getPatientProfileSeqNo(), questionAnswerDTO,"HCP");

            if (questionAnswerDto != null) {
                jsonResponse.setData(questionAnswerDto);
                jsonResponse.setTotalRecords(questionAnswerDto.size());
                jsonResponse.setErrorCode(1);
                jsonResponse.setSuccessMessage(Constants.SUCCESS);

            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setSuccessMessage("No record Found!!");
                return jsonResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatientWsController ->questionsAnswerListWs", e);
        }

        return objectMapper(mapper, jsonResponse);
    }
        @RequestMapping(value = "/getPhysicianListWs", produces = "application/json")
    public @ResponseBody
    Object getPhysicianListWs(@RequestHeader(value = "securityToken") String securityToken) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        } else {
           List<PracticesDTO> dto = patientProfileService.getPracticeDetails(patientProfile);
            jsonResponse.setData(dto);
            jsonResponse.setTotalRecords(dto.size());
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        }
        return objectMapper(objectMapper, jsonResponse);
    }
    @RequestMapping(value = "/sendHCPGenralMessageNotificationWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object sendHCPGenralMessageWs(@RequestHeader(value = "securityToken") String securityToken, @RequestBody String json) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);
            JsonNode userInputNote = rootNode.path("questionId");
            Long questionId = userInputNote.asLong();
            QuestionAnswer dbQuestionAnswer = patientProfileService.getQuestionAnswerByQuestionId(questionId);
            boolean isSend = patientProfileService.sendPushNotification(dbQuestionAnswer, patientProfile, ResponseTitleEnum.HCP_GENRAL_MESSAGE.getValue(), EventTitleEnum.HCP_GEN_MSG.getValue());
            if (isSend == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage(Constants.HCP + ": Notifcations has been sent successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage(Constants.FAILURE);
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.CATCH_BODY_EXEPTION);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }
      @RequestMapping(value = "/deleteSurveyWs",  produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteSurveyWs(@RequestHeader(value = "securityToken", required = false) String securityToken, @RequestBody String json) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper mapper = new ObjectMapper();
        boolean isDelete = false;
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);
            JsonNode userInputNote = rootNode.path("surveyId");
            Long surveyId = userInputNote.asLong();
            isDelete = patientProfileService.isDeleteSurvey(patientProfile.getPatientProfileSeqNo(),surveyId);
            if (isDelete == true) {
                jsonResponse.setErrorCode(1);
                jsonResponse.setErrorMessage("Record Deleted successfully.");
            } else {
                jsonResponse.setErrorCode(0);
                jsonResponse.setErrorMessage("Record not found.");
                return objectMapper(mapper, jsonResponse);
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Problem to Delete Rcord.");
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }
        @RequestMapping(value = "/getDuplicateSurveyDetialWs", produces = "application/json", method = RequestMethod.POST)
     public Object getDeuplicateSurveyDetial(@RequestHeader(value = "securityToken") String securityToken, @RequestBody String json) throws Exception{
        
        JsonResponse jsonResponse = new JsonResponse(); 
        ObjectMapper mapper = new ObjectMapper();
        try {
                    PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);
            JsonNode userInputNote = rootNode.path("assignSurveyId");
            Long surveyId= userInputNote.asLong();
            AssignedSurveyLogsDTO assignedSurvey = patientProfileService.getLatestSurveyLogByAssignId(patientProfile.getPatientProfileSeqNo(), surveyId);
            if(assignedSurvey == null){
             assignedSurvey.setId(surveyId);
             jsonResponse.setData(assignedSurvey);
             jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage("Duplicate Does not exist.");
            return objectMapper(mapper, jsonResponse);
            }else {
            jsonResponse.setErrorCode(1);
            jsonResponse.setData(assignedSurvey);
            return objectMapper(mapper, jsonResponse); 
            }
        } catch (Exception e) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Problem to fetch record");
            return objectMapper(mapper, jsonResponse);
        }
    }
    @RequestMapping(value = "/fethDrugDetialWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object fethDrugDetialWs(@RequestHeader(value = "SecurityToken", required = false) String SecurityToken, @RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonResponse jsonResponse = new JsonResponse();
        try {
            PatientProfile patientProfile = APIValidationUtil.validateToken(SecurityToken, jsonResponse, patientProfileService, logger);
            if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
                return objectMapper(mapper, jsonResponse);
            }
            JsonNode rootNode = mapper.readTree(json);
            JsonNode userInputNote = rootNode.path("orderId");
            String orderId = userInputNote.asText();
            Order order = patientProfileService.getOrderByIdAndPatientId(orderId, patientProfile.getPatientProfileSeqNo());
            if (order != null) {
                LowerCostDrugDTO newOrder = patientProfileService.getLowCostDrugDetial(order);
                jsonResponse.setErrorCode(1);
                jsonResponse.setData(newOrder);
            }
        } catch (Exception e) {
            logger.error("Exception# fethDrugDetialWs# ", e);
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.ERROR_SAVE_MESSAGE);
            return objectMapper(mapper, jsonResponse);
        }
        return objectMapper(mapper, jsonResponse);
    }
         @RequestMapping(value = "/getPhysicianListWithDetialWs", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Object getPhysicianListWithDetialWs(@RequestHeader(value = "securityToken") String securityToken, @RequestBody String json) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Security Token: " + securityToken);
        PatientProfile patientProfile = APIValidationUtil.validateToken(securityToken, jsonResponse, patientProfileService, logger);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            return objectMapper(objectMapper, jsonResponse);
        } else {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode userInputNote = rootNode.path("practiceId");
            Integer practiceId = userInputNote.asInt();
            List<PracticesDTO> dto = patientProfileService.getPhysicianList(practiceId);
            jsonResponse.setData(dto);
            jsonResponse.setTotalRecords(dto.size());
            jsonResponse.setErrorCode(1);
            jsonResponse.setErrorMessage(Constants.SUCCESS);
        }
        return objectMapper(objectMapper, jsonResponse);
    }
}
