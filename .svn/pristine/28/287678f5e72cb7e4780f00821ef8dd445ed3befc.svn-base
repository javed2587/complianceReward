/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.validation;

import com.ssa.cms.common.Constants;
import com.ssa.cms.dto.DeliveryDistanceFeeDTO;
import com.ssa.cms.model.JsonResponse;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;

/**
 *
 * @author Zubair
 */
public class APIValidationUtil {

    private static Pattern pattern;
    private static Matcher matcher;

    public static PatientProfile validateToken(String securityToken, JsonResponse jsonResponse, PatientProfileService patientProfileService, final Log logger) throws IOException {
        logger.info("SecurityToken= " + securityToken);

        PatientProfile patientProfile = null;
        if (CommonUtil.isNullOrEmpty(securityToken)) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage("Security token is required.");
            return patientProfile;
        }

        patientProfile = patientProfileService.getPatientProfileByToken(securityToken);
        if (patientProfile == null || CommonUtil.isNullOrEmpty(patientProfile.getPatientProfileSeqNo())) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_SECURITY_TOKEN);
            return patientProfile;
        }
        jsonResponse.setPatientId(patientProfile.getPatientProfileSeqNo());
        return patientProfile;
    }

    public static boolean isValidZipCode(String zip, PatientProfile profile, JsonResponse jsonResponse, PatientProfileService patientProfileService, final Log logger) throws IOException {
        List<DeliveryDistanceFeeDTO> list = patientProfileService.getZipCodeCalculationsList(zip, profile);
        if (list.isEmpty()) {
            if (!validateZipCode(zip, jsonResponse, profile.getPatientProfileSeqNo(), patientProfileService, logger)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateZipCode(String zip, JsonResponse jsonResponse, Integer profileId, PatientProfileService patientProfileService, final Log logger) throws IOException {
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

    private static void zipCodeLimitError(JsonResponse jsonResponse) {
        jsonResponse.setErrorCode(0);
        jsonResponse.setErrorMessage(Constants.LIMIT_EXCEEDED);
    }

    private static void inValidZipCodeMessage(JsonResponse jsonResponse) {
        jsonResponse.setErrorCode(0);
        jsonResponse.setErrorMessage("InValid zip code");
    }

    public static boolean validateEmail(String email, JsonResponse jsonResponse) throws IOException {
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
                jsonResponse.setErrorMessage("Enter valid Phone#/Email address" + " " + email);
                return false;
            }
        }
        return true;
    }

    public static boolean validateDOB(String dob, PatientProfile profile, JsonResponse jsonResponse, final Log logger) throws ParseException, IOException {
        if (CommonUtil.isNotEmpty(dob)) {
            Date dateOfBirth = DateUtil.stringToDate(dob, Constants.USA_DATE_FORMATE);
            logger.info("After Format Date Of Birth is: " + dateOfBirth);
            System.out.println("After Format Date Of Birth is: " + dateOfBirth);
            profile.setDob(dateOfBirth);
            if (!validateAge(dateOfBirth, jsonResponse)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateAge(Date dateOfBirth, JsonResponse jsonResponse) throws IOException {
        int years = DateUtil.getDiffYears(dateOfBirth, new Date());
        if (years < 18) {
            jsonResponse.setErrorCode(0);
            jsonResponse.setErrorMessage(Constants.INVALID_AGE);
            return false;
        }
        return true;
    }
}
