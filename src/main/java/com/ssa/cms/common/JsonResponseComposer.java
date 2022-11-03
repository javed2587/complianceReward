/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.common;

import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.model.AuditFields;
import com.ssa.cms.model.BaseModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Haider Ali
 */
public class JsonResponseComposer {

    private static final Log logger = LogFactory.getLog(JsonResponseComposer.class.getName());

    public static String composeSuccessResponse(AuditFields auditFields) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.SUCCESS);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_SUCCESS);
            jsonResponse.setAuditFields(auditFields);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeFailureResponse(String str_errorMessage) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.FAIL);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_FAILLIURE);
            jsonResponse.setErrorMessage(str_errorMessage);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeFailureResponse(String str_errorMessage, BaseModel baseModel) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.FAIL);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_FAILLIURE);
            jsonResponse.setErrorMessage(str_errorMessage);
            jsonResponse.setBaseModel(baseModel);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeFailureResponse(String str_errorMessage, BaseDTO baseDTO) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.FAIL);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_FAILLIURE);
            jsonResponse.setErrorMessage(str_errorMessage);
            jsonResponse.setBaseDTO(baseDTO);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeFailureResponse(String str_errorMessage, BaseDTO baseDTO, Integer errorCode) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.FAIL);
            jsonResponse.setStatuscode(errorCode);
            jsonResponse.setErrorMessage(str_errorMessage);
            jsonResponse.setBaseDTO(baseDTO);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeSuccessResponse(BaseDTO baseDTO) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.SUCCESS);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_SUCCESS);
            jsonResponse.setBaseDTO(baseDTO);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeSuccessResponse(BaseModel baseModel) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.SUCCESS);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_SUCCESS);
            jsonResponse.setBaseModel(baseModel);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeSuccessResponse() {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.SUCCESS);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_SUCCESS);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeSuccessResponse(Object dependants) {

        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(Constants.JSON_STATUS.SUCCESS);
            jsonResponse.setStatuscode(Constants.JSON_STATUS.CODE_SUCCESS);
            jsonResponse.setDependants(dependants);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception ex) {
            logger.error("Exception: JsonResponseComposer -> composeSuccessResponse", ex);
        }
        return str_response;
    }

    public static String composeResponse(String message, String status, int errorCode) {
        String str_response = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus(status);
            jsonResponse.setStatuscode(errorCode);
            jsonResponse.setErrorMessage(message);
            str_response = objectMapper.writeValueAsString(jsonResponse);
        } catch (Exception e) {
            logger.error("Exception:: JsonResponseComposer:: composeResponse", e);
        }
        return str_response;
    }
}
