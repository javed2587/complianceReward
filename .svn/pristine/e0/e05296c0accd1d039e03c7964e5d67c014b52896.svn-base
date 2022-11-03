package com.ssa.cms.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.dao.ConsumerRegistrationDAO;
import com.ssa.cms.dao.DrugDAO;
import com.ssa.cms.dao.OrderDAO;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.DataTablesTO;
import com.ssa.cms.dto.DrugCategoryListDTO;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.dto.SearchDTO;
import com.ssa.cms.dto.TransferRxQueueDTO;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.DeliveryPreferences;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.DrugBasic;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.DrugForm;
import com.ssa.cms.model.DrugGeneric;
import com.ssa.cms.model.MessageType;
import com.ssa.cms.model.MultiRx;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderCustomDocuments;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.OrderTransferImages;
import com.ssa.cms.model.PatientDeliveryAddress;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyFacilityOperation;
import com.ssa.cms.model.PharmacyLookup;
import com.ssa.cms.model.PharmacyType;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.model.QuestionAnswer;
import com.ssa.cms.model.ReadyToDeliverRxOrders;
import com.ssa.cms.model.RedemptionIngredient;
import com.ssa.cms.model.RewardHistory;
import com.ssa.cms.model.RoleTypes;
import com.ssa.cms.model.State;
import com.ssa.cms.model.StateZipCode;
import com.ssa.cms.model.TransferDetail;
import com.ssa.cms.model.TransferRequest;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import static com.ssa.cms.util.DateUtil.addDays;
import com.ssa.cms.util.EmailSenderUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import com.ssa.cms.util.PropertiesUtil;
import com.ssa.cms.util.RandomString;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msheraz
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ConsumerRegistrationService {

    private static final Log logger = LogFactory.getLog(ConsumerRegistrationService.class.getName());

    @Autowired
    private ConsumerRegistrationDAO consumerRegistrationDAO;
    @Autowired
    private OrderDAO orderDao;
    @Autowired
    private PMSTextFlowService textFlowService;
    @Autowired
    private DrugDAO drugDAO;

    @Autowired
    private PatientProfileService patientProfileService;

    public List<PharmacyType> getAllPharmacyTypes() {
        List<PharmacyType> pharmacytypeList = new ArrayList<>();
        try {
            pharmacytypeList = consumerRegistrationDAO.getAllPharmacyTypes();
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getAllPharmacyTypes", e);
        }

        return pharmacytypeList;
    }

    public int getUserCountByPharmacyIdAndRole(int id, String role) {
        int i = 0;
        try {
            i = consumerRegistrationDAO.getPharmacyUserByPharmacyId(id, role);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getUserCountByPharmacyIdAndRole", e);
        }
        return i;
    }

    public PharmacyLookup lookupPharmacy(String npi, String sitePassNumber, int typeId) {
        PharmacyLookup pharmacyLookup = new PharmacyLookup();
        try {
            pharmacyLookup = consumerRegistrationDAO.lookupPharmacy(npi, sitePassNumber, typeId);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> lookupPharmacy", e);
        }

        return pharmacyLookup;
    }

    public PharmacyLookup lookupPharmacyObj(int pharmacyId) {

        PharmacyLookup pharmacyLookup = new PharmacyLookup();
        try {
            pharmacyLookup = consumerRegistrationDAO.lookupPharmacyObj(pharmacyId);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> lookupPharmacy", e);
        }

        return pharmacyLookup;
    }

    public PharmacyLookup PharmacylookupbyId(int id) {

        PharmacyLookup pharmacyLookup = new PharmacyLookup();
        try {
            pharmacyLookup = consumerRegistrationDAO.PharmacylookupbyId(id);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> lookupPharmacy", e);
        }

        return pharmacyLookup;
    }

    public boolean isEmailAddressUnique(String email) {
        Boolean emailExist = Boolean.TRUE;
        try {
            emailExist = consumerRegistrationDAO.isEmailAddressUnique(email);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> isEmailAddressUnique", e);
        }

        return emailExist;
    }

    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileTransferRequestTransferDetails() {
        List<TransferRxQueueDTO> trq = new ArrayList<>();
        try {

            trq = consumerRegistrationDAO.getTranferRxQueueByPatientProfileTransferRequestTransferDetails();
            return trq;
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileTransferRequestTransferDetails", e);
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(int id) {
        //List<TransferRxQueueDTO> trq = new ArrayList<TransferRxQueueDTO>();
        try {

            List<Object[]> list = consumerRegistrationDAO.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2();
            return populateTransferDTOFromTransferRxQueue(list, id);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileTransferRequestTransferDetails", e);
            e.printStackTrace();
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(int id, String orderStatus, Integer pharmacyId) {
        //List<TransferRxQueueDTO> trq = new ArrayList<TransferRxQueueDTO>();
        try {

            List<Object[]> list = consumerRegistrationDAO.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(orderStatus, pharmacyId);
            return populateTransferDTOFromTransferRxQueue(list, id);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileTransferRequestTransferDetails", e);
            e.printStackTrace();
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////
    public Map populateCountForPage1(Integer pharmacyId) {
        Map<String, Integer> map = new HashMap();
        try {

            List<Object[]> list = consumerRegistrationDAO.getAllOrderTypeCount(pharmacyId);

            ///////////////////////////////////////////////////////////////////////////////////
            String key = "";
            for (Object[] record : list) {
                key = "" + record[1];
                if (map.get(key) == null) {
                    map.put(key, AppUtil.getSafeInt("" + record[0], 0));// 1);
                }
//                else if (record[0] != null) {
//                    Integer count = map.get(key);
//                    System.out.println("Order Status id is:: " + key + " Count is:: " + count);
//                    count = count + 1;
//                    map.put(key, count);
//                }
            }
            ///////////////////////////////////////////////////////////////////////////////////

//            }
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileTransferRequestTransferDetails", e);
            e.printStackTrace();
        }
        return map;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getTranferRxQueueForPage1(int id, String orderStatus) {
        //List<TransferRxQueueDTO> trq = new ArrayList<TransferRxQueueDTO>();
        try {

            List<Object[]> list = consumerRegistrationDAO.getStatuswiseOrders(orderStatus);
//            if (orderStatus.equalsIgnoreCase("Pending") || orderStatus.equalsIgnoreCase("Pending Review")
//                    || orderStatus.equalsIgnoreCase("Waiting Pt Response")) {
//                return populateTransferDTOForPage1ForInProcessOrders(list, id);
//            } else {
            return populateTransferDTOForPage1(list, id);

//            }
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileTransferRequestTransferDetails", e);
            e.printStackTrace();
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getTranferRxQueueForPage2(int id, int patientId, int dependentId, String orderStatus) {
        //List<TransferRxQueueDTO> trq = new ArrayList<TransferRxQueueDTO>();
        try {
            List<Object[]> list = consumerRegistrationDAO.getStatuswiseOrders(orderStatus, patientId, dependentId);
            return populateTransferDTOForPage2(list, id);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileTransferRequestTransferDetails", e);
            e.printStackTrace();
        }
        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<TransferRxQueueDTO> populateTransferDTOForPage1ForInProcessOrders(List<Object[]> list, int id) throws Exception {
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        Map patientMap = new HashMap();
        for (Object[] arr : list) {
            PatientProfile patientProfile = (PatientProfile) arr[1];
            if (!patientMap.containsKey(patientProfile.getPatientProfileSeqNo())) {
                patientMap.put(patientProfile.getPatientProfileSeqNo(), new Integer(1));
            } else {
                Integer count = (Integer) patientMap.get(patientProfile.getPatientProfileSeqNo());
                count = count + 1;
                patientMap.put(patientProfile.getPatientProfileSeqNo(), count);
            }
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            Order orders = (Order) arr[0];
            PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[2];
            DeliveryPreferences pref = (DeliveryPreferences) arr[3];
            OrderStatus orderStatus = (OrderStatus) arr[4];
            DrugDetail detail = (DrugDetail) arr[5];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
            transferRxQueueDTO.setUpdatedDate(orders.getUpdatedOn());
            transferRxQueueDTO.setUpdatedBy(consumerRegistrationDAO.getPharmacyUserById(orders.getUpdatedBy()) != null ? consumerRegistrationDAO.getPharmacyUserById(orders.getUpdatedBy()).getFullName() : "N/A");
            if (orders.getQty() != null && !orders.getQty().trim().equalsIgnoreCase("")) {
                transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
            }
            transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
            transferRxQueueDTO.setOrderId(orders.getId());
            transferRxQueueDTO.setRxNumber(orders.getRxNo());
            transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
            //////////////////////////////////////////////
            try {
                String videoStr = AppUtil.getSafeStr(orders.getVideo(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                if (videoStr.length() > 0 && videoStr.indexOf("http://") < 0) {
                    videoStr = "http://" + videoStr;
                }
                transferRxQueueDTO.setPtVideo(videoStr);
                String imgStr = AppUtil.getSafeStr(orders.getImagePath(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                if (imgStr.length() > 0 && imgStr.indexOf("http://") < 0) {
                    imgStr = "http://" + imgStr;
                }
                transferRxQueueDTO.setTransferImage(imgStr);

            } catch (Exception e) {
            }

            /////////////////////////////////////////////
            transferRxQueueDTO.setAllergies(AppUtil.getSafeStr(patientProfile.getAllergies(), ""));
            transferRxQueueDTO.setGender(patientProfile.getGender());
            transferRxQueueDTO.setPayCash(true);
            transferRxQueueDTO.setMultiRx(false);
            transferRxQueueDTO.setMultiRxCount(1);
            transferRxQueueDTO.setMultiRxLabel("N-1");
            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
            transferRxQueueDTO.setStatus((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "OPEN (LOCKED)" : "Pending");
            transferRxQueueDTO.setDisabled((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "1" : "0");
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            if (addr != null) {
                transferRxQueueDTO.setZip(addr.getZip());
                transferRxQueueDTO.setDeliveryAddress(AppUtil.getSafeStr(addr.getAddress(), "") + "<br/>" + addr.getState().getName() + " " + addr.getApartment() + " " + addr.getZip());
            }
            transferRxQueueDTO.setSellingPrice(orders.getPriceIncludingMargins() != null ? orders.getPriceIncludingMargins() : 0d);
            transferRxQueueDTO.setOriginalPtCopay(orders.getOriginalPtCopay() != null ? orders.getOriginalPtCopay() : 0d);
            String currentStatus = AppUtil.getSafeStr(orders.getPatientResponse(), "");
            transferRxQueueDTO.setPatientResponse(currentStatus);
            String responseRequired = AppUtil.getSafeStr(orders.getResponseRequired(), "");
            transferRxQueueDTO.setMedicationSpecMsg(responseRequired);
            String deliverycarrier = AppUtil.getSafeStr(orders.getDeliverycarrier(), "");
            transferRxQueueDTO.setDeliverycarrier(deliverycarrier);
            transferRxQueueDTO.setMiles(orders.getMiles());

            transferRxQueueDTO.setDeliveryFrom(orders.getDeliveryFrom());
            transferRxQueueDTO.setDeliveryTo(orders.getDeliveryTo());
            transferRxQueueDTO.setUnderQuotedPrice(orders.getEstimatedPrice() != null ? orders.getEstimatedPrice() : 0d);

            if (detail != null) {
//                    String drugName = AppUtil.getOneStringFromBrandAndGeneric(detail.getDrugBasic().getBrandName(),
//                            detail.getDrugBasic().getDrugGeneric().getGenericName(),
//                            detail.getDrugBasic().getDrugGeneric().getBrandNameOnly());
                String drugName = AppUtil.getSafeStr(orders.getDrugName(), "");
                try {
                    if (detail != null) {
                        if (detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() != null
                                && detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() == 1) {
                            drugName = detail.getDrugBasic().getBrandName();
                        } else {
                            drugName = detail.getDrugBasic().getDrugGeneric().getGenericName();
                        }
                    }
                } catch (Exception e) {

                }
                transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                transferRxQueueDTO.setStrength(detail.getStrength());
                transferRxQueueDTO.setDrugType(detail.getDrugForm().getFormDescr());
                transferRxQueueDTO.setUserInput(false);
                transferRxQueueDTO.setUserInputStr("");
                transferRxQueueDTO.setStatus(orderStatus.getName());

            } else {
                transferRxQueueDTO.setOldRxNo(AppUtil.getSafeStr(orders.getOldRxNumber(), ""));
                transferRxQueueDTO.setPharmacyPhone(AppUtil.getSafeStr(orders.getPharmacyPhone(), ""));
                transferRxQueueDTO.setDrugNameWithoutStrength(AppUtil.getSafeStr(orders.getDrugName(), ""));
                transferRxQueueDTO.setStrength("");
                transferRxQueueDTO.setDrugType("");
                transferRxQueueDTO.setUserInput(true);
                if (AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "").length() > 0
                        || transferRxQueueDTO.getPharmacyPhone().length() > 0
                        || transferRxQueueDTO.getDrugNameWithoutStrength().length() > 0) {
                    transferRxQueueDTO.setUserInputStr(AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "") + "<br>"
                            + transferRxQueueDTO.getPharmacyPhone() + "<br>"
                            + transferRxQueueDTO.getDrugNameWithoutStrength());
                } else {
                    transferRxQueueDTO.setUserInputStr("");
                }
                transferRxQueueDTO.setStatus("UNVERIFIED IMG");

            }
            transferRxQueueDTO.setQuantity(AppUtil.getSafeInt(orders.getQty(), 0));
            transferRxQueueDTO.setTracking(AppUtil.getSafeStr(orders.getTraclingno(), ""));
            String paymentMode = AppUtil.getSafeStr(orders.getFinalPaymentMode(), "");
            transferRxQueueDTO.setFinalPaymentMode(paymentMode.equalsIgnoreCase("SELF PAY") ? "SELF PAY" : "INS");
            transferRxQueueDTO.setDeliveryService(pref != null ? AppUtil.getSafeStr(pref.getName(), "-") : "-");
            transferRxQueueDTO.setDeliveryUrgent(transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                    || transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*"));

            transferRxQueueDTO.setHandlingFee(orders.getHandLingFee() != null ? orders.getHandLingFee() : 0d);
            transferRxQueueDTO.setRequestControlNumber(orders.getId());
            transferRxQueueDTO.setRequestControlNumber1(transferRxQueueDTO.getRequestControlNumber().substring(0, 8));
            transferRxQueueDTO.setRequestControlNumber2(transferRxQueueDTO.getRequestControlNumber().substring(8));
            String requestType = AppUtil.getSafeStr(orders.getOrderType(), "");
            if (!requestType.equalsIgnoreCase("Refill")) {
                if (orders.getOnlineOrder() != null && orders.getOnlineOrder()) {
                    requestType = "Online Order";
                    transferRxQueueDTO.setRequestTypeBgColor("#33FFDF");
                } else if (orders.getCareGiverRequest() != null && orders.getCareGiverRequest() == 1) {
                    requestType = "Request CareGiver";
                } else if (orders.getIsBottleAvailable() != null && orders.getIsBottleAvailable()) {
                    requestType = "X-FER LABEL SCAN";
                    transferRxQueueDTO.setRequestTypeBgColor("#FFF233");
                } else {
                    requestType = "X-FER RX SCAN";
                    transferRxQueueDTO.setRequestTypeBgColor("#FFA833");
                }
            }
            transferRxQueueDTO.setRequestType(requestType);
            transferRxQueueList.add(transferRxQueueDTO);
//                patientMap.put(patientProfile.getId(), transferRxQueueDTO);

            Map patientIndexMap = new HashMap();

            for (TransferRxQueueDTO dto : transferRxQueueList) {
                Integer count = (Integer) patientMap.get(dto.getPatientId());
                if (count > 1) {
                    dto.setMultiRx(true);
                    dto.setMultiRxCount(count);
                    Integer index = 1;
                    if (patientIndexMap.containsKey(dto.getPatientId())) {
                        index = (Integer) patientIndexMap.get(dto.getPatientId());
                        dto.setMultiRxLabel(index + 1 + " of  Y-" + count);
                        index = index + 1;
                        patientIndexMap.put(dto.getPatientId(), index);
                        //index=1;
                    } else {
                        dto.setMultiRxLabel("1 of  Y-" + count);
                        patientIndexMap.put(dto.getPatientId(), 1);
                    }

                } else {
                    dto.setMultiRx(false);
                    dto.setMultiRxCount(count);
                    dto.setMultiRxLabel("1 of 1");
                }
            }

        }
        return transferRxQueueList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<TransferRxQueueDTO> populateTransferDTOForPage1(List<Object[]> list, int id) throws Exception {
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        Map patientMap = new HashMap();
        Map orderMap = new HashMap();
        String name = "";
        for (Object[] arr : list) {
            PatientProfile patientProfile = (PatientProfile) arr[1];
            Integer dependentId = 0;
            Order orders = (Order) arr[0];
            name = EncryptionHandlerUtil.getDecryptedString(patientProfile.getFirstName()) + " " + EncryptionHandlerUtil.getDecryptedString(patientProfile.getLastName());//patientProfile.getFirstName() + " " + patientProfile.getLastName();
            
            String key = patientProfile.getPatientProfileSeqNo() + "_" + dependentId;
            if (!orderMap.containsKey(orders.getId())) {
                if (!patientMap.containsKey(key)) {
                    String hasRxCard = "";
                    TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
                    PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[2];
                    DeliveryPreferences pref = (DeliveryPreferences) arr[3];
                    OrderStatus orderStatus = (OrderStatus) arr[4];
                    DrugDetail detail = (DrugDetail) arr[5];
//                    if (patientProfile.getInsuranceFrontCardPath() != null) {
//                        hasRxCard = "yes";
//                    } else {
//                        hasRxCard = "no";
//                    }
                    if (AppUtil.getSafeStr(orders.getPaymentType(), "").toUpperCase().indexOf("INSURANCE") >= 0) {
                        hasRxCard = "yes";
                    } else {
                        hasRxCard = "no";
                    }
                    transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
                    transferRxQueueDTO.setDependentId(dependentId);
                    //                if(orders.getPatientProfileMembers()!=null && 
                    //                   orders.getPatientProfileMembers().getIsAdult()!=null &&
                    //                   orders.getPatientProfileMembers().getIsAdult())
                    //                {
                    //                    transferRxQueueDTO.setDependentId(orders.getPatientProfileMembers().getId());
                    //                    transferRxQueueDTO.setCareGiverRequest(1);
                    //                }
                    //                else
                    //                {
                    //                    transferRxQueueDTO.setCareGiverRequest(0);
                    //                }
                    transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
                    transferRxQueueDTO.setUpdatedDate(orders.getUpdatedOn());
                    transferRxQueueDTO.setUpdatedBy(this.getPharmacyUserById(orders.getUpdatedBy()) != null ? this.getPharmacyUserById(orders.getUpdatedBy()).getFullName() : "N/A");
                    if (orders.getQty() != null && !orders.getQty().trim().equalsIgnoreCase("")) {
                        transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
                    }
                    transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
                    transferRxQueueDTO.setOrderId(orders.getId());
                    transferRxQueueDTO.setRxNumber(AppUtil.getSafeStr(orders.getRxPrefix(), "") + orders.getOldRxNumber());
                    transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
                    //////////////////////////////////////////////
                    try {
                        String videoStr = AppUtil.getSafeStr(orders.getVideo(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                        if (videoStr.length() > 0 && videoStr.indexOf("http://") < 0) {
                            videoStr = "http://" + videoStr;
                        }
                        transferRxQueueDTO.setPtVideo(videoStr);
                        String imgStr = AppUtil.getSafeStr(orders.getImagePath(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                        if (imgStr.length() > 0 && imgStr.indexOf("http://") < 0) {
                            imgStr = "http://" + imgStr;
                        }
                        transferRxQueueDTO.setTransferImage(imgStr);

                    } catch (Exception e) {
                    }

                    /////////////////////////////////////////////
                    transferRxQueueDTO.setAllergies(AppUtil.getSafeStr(patientProfile.getAllergies(), ""));
                    transferRxQueueDTO.setGender(patientProfile.getGender());
                    transferRxQueueDTO.setPayCash(true);
                    transferRxQueueDTO.setMultiRx(false);
                    transferRxQueueDTO.setMultiRxCount(1);
                    transferRxQueueDTO.setMultiRxLabel("N-1");
                    transferRxQueueDTO.setPatientName(name);
                    transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
                    ////////////////////////////////////////////////////////////
                    

                    ///////////////////////////////////////////////////////////
                    transferRxQueueDTO.setStatus((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "OPEN (LOCKED)" : "Pending");
                    transferRxQueueDTO.setDisabled((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "1" : "0");
                    transferRxQueueDTO.setHasRxCard(hasRxCard);
                    if (addr != null) {
                        transferRxQueueDTO.setZip(addr.getZip());

                        transferRxQueueDTO.setDeliveryAddress(AppUtil.getSafeStr(addr.getAddress(), "") + "<br/>" + addr.getState().getName() + " " + addr.getApartment() + " " + addr.getZip());
                    }
                    transferRxQueueDTO.setSellingPrice(orders.getPriceIncludingMargins() != null ? orders.getPriceIncludingMargins() : 0d);
                    transferRxQueueDTO.setSellingPriceStr(
                            AppUtil.roundOffNumberToCurrencyFormat(
                                    transferRxQueueDTO.getSellingPrice(), "en", "US"));
                    transferRxQueueDTO.setOriginalPtCopay(orders.getOriginalPtCopay() != null ? orders.getOriginalPtCopay() : 0d);
                    transferRxQueueDTO.setOriginalPtCopayStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getOriginalPtCopay(), "en", "US"));
                    String currentStatus = AppUtil.getSafeStr(orders.getPatientResponse(), "");
                    transferRxQueueDTO.setPatientResponse(currentStatus);
                    String responseRequired = AppUtil.getSafeStr(orders.getResponseRequired(), "");
                    transferRxQueueDTO.setMedicationSpecMsg(responseRequired);
                    String deliverycarrier = AppUtil.getSafeStr(orders.getDeliverycarrier(), "");
                    transferRxQueueDTO.setDeliverycarrier(deliverycarrier);
                    transferRxQueueDTO.setMiles(orders.getMiles());

                    transferRxQueueDTO.setDeliveryFrom(orders.getDeliveryFrom());
                    transferRxQueueDTO.setDeliveryTo(orders.getDeliveryTo());
                    transferRxQueueDTO.setUnderQuotedPrice(orders.getEstimatedPrice() != null ? orders.getEstimatedPrice() : 0d);
                    transferRxQueueDTO.setUnderQuotedPriceStr(AppUtil.roundOffNumberToCurrencyFormat(transferRxQueueDTO.getUnderQuotedPrice(), "en", "US"));
                    transferRxQueueDTO.setPatientResponse(AppUtil.getSafeStr(orders.getPatientResponse(), ""));
                    transferRxQueueDTO.setShippingStatus(orderStatus.getName());
                    String drugName = AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(orders.getDrugName()), "");
                    if (detail != null) {
                        //                    String drugName = AppUtil.getOneStringFromBrandAndGeneric(detail.getDrugBasic().getBrandName(),
                        //                            detail.getDrugBasic().getDrugGeneric().getGenericName(),
                        //                            detail.getDrugBasic().getDrugGeneric().getBrandNameOnly());

                        try {
                            if (detail != null) {
                                if (detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() != null
                                        && detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() == 1) {
                                    drugName = detail.getDrugBasic().getBrandName();
                                    transferRxQueueDTO.setBrandOnly(true);
                                } else {
                                    drugName = detail.getDrugBasic().getDrugGeneric().getGenericName();
                                }
                            }
                        } catch (Exception e) {

                        }
                        transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                        transferRxQueueDTO.setStrength(detail.getStrength());
                        transferRxQueueDTO.setDrugType(detail.getDrugForm().getFormDescr());
                        transferRxQueueDTO.setUserInput(false);
                        transferRxQueueDTO.setUserInputStr("");
                        transferRxQueueDTO.setStatus(orderStatus.getName());
                        transferRxQueueDTO.setOrderStatus(orderStatus.getName());

                    } else {
                        transferRxQueueDTO.setOldRxNo(AppUtil.getSafeStr(orders.getOldRxNumber(), ""));
                        transferRxQueueDTO.setPharmacyPhone(AppUtil.getSafeStr(orders.getPharmacyPhone(), ""));
                        transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                        transferRxQueueDTO.setStrength("");
                        transferRxQueueDTO.setDrugType("");
                        transferRxQueueDTO.setUserInput(true);
                        if (AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "").length() > 0
                                || transferRxQueueDTO.getPharmacyPhone().length() > 0
                                || transferRxQueueDTO.getDrugNameWithoutStrength().length() > 0) {
                            transferRxQueueDTO.setUserInputStr(AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "") + "<br>"
                                    + transferRxQueueDTO.getPharmacyPhone() + "<br>"
                                    + transferRxQueueDTO.getDrugNameWithoutStrength());
                        } else {
                            transferRxQueueDTO.setUserInputStr("");
                        }
                        if (AppUtil.getSafeStr(orders.getImagePath(), "").length() > 0
                                || AppUtil.getSafeStr(orders.getVideo(), "").length() > 0) {
                            transferRxQueueDTO.setStatus("UNVERIFIED");//"UNVERIFIED IMG");
                            transferRxQueueDTO.setSortByStatus(1);
                        } else {
                            transferRxQueueDTO.setStatus("Pending");
                        }

                    }
                    transferRxQueueDTO.setQuantity(AppUtil.getSafeInt(orders.getQty(), 0));
                   
                    transferRxQueueDTO.setRxAcqCost(orders.getRxAcqCost() != null ? orders.getRxAcqCost() : 0d);
                    transferRxQueueDTO.setRxAcqCostStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getRxAcqCost(), "en", "US"));
                    transferRxQueueDTO.setFinalCopay(orders.getFinalPayment() != null ? orders.getFinalPayment() : 0d);
                    transferRxQueueDTO.setFinalCopayStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getFinalCopay(), "en", "US"));
                   
                    transferRxQueueDTO.setTracking(AppUtil.getSafeStr(orders.getTraclingno(), ""));
                    String paymentMode = AppUtil.getSafeStr(orders.getFinalPaymentMode(), "");
                    transferRxQueueDTO.setFinalPaymentMode(paymentMode.equalsIgnoreCase("SELF PAY") ? "SELF PAY" : "INS");
                    transferRxQueueDTO.setDeliveryService(pref != null ? AppUtil.getSafeStr(pref.getName(), "-") : "-");
                    transferRxQueueDTO.setDeliveryUrgent(transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                            || transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*"));

                    transferRxQueueDTO.setHandlingFee(orders.getHandLingFee() != null ? orders.getHandLingFee() : 0d);
                    Double totalSellingPrice = transferRxQueueDTO.getFinalCopay() + transferRxQueueDTO.getHandlingFee();
                    transferRxQueueDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(
                            totalSellingPrice, "en", "US"));
                    transferRxQueueDTO.setRequestControlNumber(orders.getId());
                    transferRxQueueDTO.setRequestControlNumber1(transferRxQueueDTO.getRequestControlNumber().substring(0, 8));
                    transferRxQueueDTO.setRequestControlNumber2(transferRxQueueDTO.getRequestControlNumber().substring(8));
                    String requestType = AppUtil.getSafeStr(orders.getOrderType(), "");
                    if (!requestType.equalsIgnoreCase("Refill")) {
                        transferRxQueueDTO.setTextColor("black");
                        if (orders.getOnlineOrder() != null && orders.getOnlineOrder()) {
                            requestType = "Online Order";
                            transferRxQueueDTO.setRequestTypeBgColor("#FFF233");

                        }  else if (orders.getIsBottleAvailable() != null && orders.getIsBottleAvailable()) {
                            requestType = "X-FER LABEL SCAN";
                            transferRxQueueDTO.setRequestTypeBgColor("#3a89d7");
                            transferRxQueueDTO.setTextColor("white");
                            transferRxQueueDTO.setSortByMbrReq(1);
                        } else {
                            requestType = "X-FER RX SCAN";
                            transferRxQueueDTO.setRequestTypeBgColor("#8aca14");
                            transferRxQueueDTO.setTextColor("white");
                            transferRxQueueDTO.setSortByMbrReq(2);
                        }
                    }
                    transferRxQueueDTO.setRequestType(requestType);
                    transferRxQueueDTO.setSystemGeneratedRxNumber(AppUtil.getSafeStr(orders.getSystemGeneratedRxNumber(), "-"));
                    transferRxQueueDTO.setPatientResponse(AppUtil.getSafeStr(orders.getPatientResponse(), ""));
                    transferRxQueueList.add(transferRxQueueDTO);
                    patientMap.put(key, transferRxQueueDTO);
                } else {

                    TransferRxQueueDTO transferRxQueueDTO2 = (TransferRxQueueDTO) patientMap.get(key);
                    Order ordersTmp = (Order) arr[0];

                    if (!transferRxQueueDTO2.getOrderId().equals(ordersTmp.getId())) {
                        transferRxQueueDTO2.setMultiRx(true);
                        transferRxQueueDTO2.setMultiRxCount(transferRxQueueDTO2.getMultiRxCount() + 1);
                        transferRxQueueDTO2.setMultiRxLabel("Y-" + transferRxQueueDTO2.getMultiRxCount());
                    }

                }
                orderMap.put(orders.getId(), orders.getId());
            }

        }
        return transferRxQueueList;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
//    private retireveListForDuplication(transferRxQueueList)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<TransferRxQueueDTO> populateTransferDTOForPage2(List<Object[]> list, int id) throws Exception {
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        Map map = new HashMap();
        for (Object[] arr : list) {
            PatientProfile patientProfile = (PatientProfile) arr[1];

            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            Order orders = (Order) arr[0];
            String orderId = orders.getId();
            if (!map.containsKey(orderId)) {
                PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[2];
                DeliveryPreferences pref = (DeliveryPreferences) arr[3];
                OrderStatus orderStatus = (OrderStatus) arr[4];
                DrugDetail detail = (DrugDetail) arr[5];
                if (patientProfile.getInsuranceFrontCardPath() != null) {
                    hasRxCard = "yes";
                } else {
                    hasRxCard = "no";
                }
                transferRxQueueDTO.setSystemGeneratedRxNumber(AppUtil.getSafeStr(orders.getSystemGeneratedRxNumber(), ""));
                transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
                transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
                transferRxQueueDTO.setUpdatedDate(orders.getUpdatedOn() != null ? orders.getUpdatedOn() : orders.getCreatedOn());
                if (orders.getQty() != null && !orders.getQty().trim().equalsIgnoreCase("")) {
                    transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
                }
                transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
                transferRxQueueDTO.setOrderId(orders.getId());
                transferRxQueueDTO.setRxNumber(orders.getRxNo());
                transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
                //////////////////////////////////////////////
                try {
                    String videoStr = AppUtil.getSafeStr(orders.getVideo(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                    if (videoStr.length() > 0 && videoStr.indexOf("http://") < 0) {
                        videoStr = "http://" + videoStr;
                    }
                    transferRxQueueDTO.setPtVideo(videoStr);
                    String imgStr = AppUtil.getSafeStr(orders.getImagePath(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                    if (imgStr.length() > 0 && imgStr.indexOf("http://") < 0) {
                        imgStr = "http://" + imgStr;
                    }
                    transferRxQueueDTO.setTransferImage(imgStr);

                } catch (Exception e) {
                }

                /////////////////////////////////////////////
                transferRxQueueDTO.setRequiredResponse(AppUtil.getSafeStr(orders.getResponseRequired(), ""));
                transferRxQueueDTO.setOriginalPtCopay(orders.getOriginalPtCopay() != null ? orders.getOriginalPtCopay() : 0d);
                transferRxQueueDTO.setOriginalPtCopayStr(AppUtil.roundOffNumberToCurrencyFormat(transferRxQueueDTO.getOriginalPtCopay(),
                        "en", "US"));
                
                transferRxQueueDTO.setNextRefillDate(orders.getNextRefillDate());//DateUtil.addDaysToDate(new Date(),
                //transferRxQueueDTO.getDaysSupply()));
                transferRxQueueDTO.setAllergies(AppUtil.getSafeStr(patientProfile.getAllergies(), ""));
                transferRxQueueDTO.setGender(patientProfile.getGender());
                transferRxQueueDTO.setPayCash(true);
                transferRxQueueDTO.setMultiRx(false);
                transferRxQueueDTO.setMultiRxCount(1);
                transferRxQueueDTO.setMultiRxLabel("N-1");
                transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
                transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
                transferRxQueueDTO.setStatus((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "OPEN (LOCKED)" : "Pending");
                transferRxQueueDTO.setDisabled((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "1" : "0");
                transferRxQueueDTO.setHasRxCard(hasRxCard);
                if (addr != null) {
                    transferRxQueueDTO.setZip(addr.getZip());
                    transferRxQueueDTO.setDeliveryAddress(AppUtil.getSafeStr(addr.getAddress(), "") + "<br/>" + addr.getState().getName() + " " + addr.getApartment() + " " + addr.getZip());
                }
                if (detail != null) {
                    String drugName = AppUtil.getOneStringFromBrandAndGeneric(detail.getDrugBasic().getBrandName(),
                            detail.getDrugBasic().getDrugGeneric().getGenericName(),
                            detail.getDrugBasic().getDrugGeneric().getBrandNameOnly());
                    transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                    transferRxQueueDTO.setStrength(detail.getStrength());
                    transferRxQueueDTO.setDrugType(detail.getDrugForm().getFormDescr());
                    transferRxQueueDTO.setUserInput(false);
                    transferRxQueueDTO.setUserInputStr("");
                    transferRxQueueDTO.setStatus(orderStatus.getName());

                } else {
                    transferRxQueueDTO.setOldRxNo(AppUtil.getSafeStr(orders.getOldRxNumber(), ""));
                    transferRxQueueDTO.setPharmacyPhone(AppUtil.getSafeStr(orders.getPharmacyPhone(), ""));
                    transferRxQueueDTO.setDrugNameWithoutStrength(AppUtil.getSafeStr(orders.getDrugName(), ""));
                    transferRxQueueDTO.setStrength("");
                    transferRxQueueDTO.setDrugType("");
                    transferRxQueueDTO.setUserInput(true);
                    if (AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "").length() > 0
                            || transferRxQueueDTO.getPharmacyPhone().length() > 0
                            || transferRxQueueDTO.getDrugNameWithoutStrength().length() > 0) {
                        transferRxQueueDTO.setUserInputStr(AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "") + "<br>"
                                + transferRxQueueDTO.getPharmacyPhone() + "<br>"
                                + transferRxQueueDTO.getDrugNameWithoutStrength());
                    } else {
                        transferRxQueueDTO.setUserInputStr("");
                    }
                    if (AppUtil.getSafeStr(orderStatus.getName(), "").equalsIgnoreCase("Pending")) {
                        transferRxQueueDTO.setStatus("UNVERIFIED IMG");
                    } else {
                        transferRxQueueDTO.setStatus(orderStatus.getName());
                    }

                }
                transferRxQueueDTO.setQuantity(AppUtil.getSafeInt(orders.getQty(), 0));
               
                transferRxQueueDTO.setRxAcqCost(orders.getRxAcqCost() != null ? orders.getRxAcqCost() : 0d);
                
                if (transferRxQueueDTO.getRxExpiredDate() != null) {
                    Date d = new Date();
                    long daysToExpire = DateUtil.dateDiffInDays(d, transferRxQueueDTO.getRxExpiredDate());
                    if (transferRxQueueDTO.getRxExpiredDate().after(d)) {

                        transferRxQueueDTO.setDaysToExpire(daysToExpire + " days");
                    } else {
                        transferRxQueueDTO.setDaysToExpire("Expired " + daysToExpire + " ago ");
                    }
                } else {
                    transferRxQueueDTO.setDaysToExpire("N/A");
                }
                if (transferRxQueueDTO.getNextRefillDate() != null) {
                    Date d = new Date();
                    long daysToRefill = DateUtil.dateDiffInDays(transferRxQueueDTO.getNextRefillDate(), d);
                    if (daysToRefill == 0) {
                        transferRxQueueDTO.setDaysToRefill("NOW");
                    } else if (transferRxQueueDTO.getNextRefillDate().after(d)) {
                        transferRxQueueDTO.setDaysToRefill(Math.abs(daysToRefill) + " days");
                    } else {
                        transferRxQueueDTO.setDaysToRefill(daysToRefill + " Overdue");
                    }
                } else {
                    transferRxQueueDTO.setDaysToRefill("Not refillable yet");
                }
                transferRxQueueDTO.setTracking(AppUtil.getSafeStr(orders.getTraclingno(), ""));
                String paymentMode = AppUtil.getSafeStr(orders.getFinalPaymentMode(), "");
                transferRxQueueDTO.setFinalPaymentMode(paymentMode.equalsIgnoreCase("SELF PAY") ? "SELF PAY" : "INS");
                transferRxQueueDTO.setDeliveryService(pref != null ? AppUtil.getSafeStr(pref.getName(), "-") : "-");
                transferRxQueueDTO.setDeliveryUrgent(transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                        || transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*"));

                transferRxQueueDTO.setHandlingFee(orders.getHandLingFee() != null ? orders.getHandLingFee() : 0d);
                transferRxQueueDTO.setRequestControlNumber(orders.getId());
                transferRxQueueDTO.setRequestControlNumber1(transferRxQueueDTO.getRequestControlNumber().substring(0, 8));
                transferRxQueueDTO.setRequestControlNumber2(transferRxQueueDTO.getRequestControlNumber().substring(8));
                String requestType = AppUtil.getSafeStr(orders.getOrderType(), "");
                if (!requestType.equalsIgnoreCase("Refill")) {
                    if (orders.getIsBottleAvailable() != null && orders.getIsBottleAvailable()) {
                        requestType = "X-FER LABEL SCAN";
                    } else {
                        requestType = "X-FER RX SCAN";
                    }
                }
                transferRxQueueDTO.setRequestType(requestType);
                if (orders.getDeliveryDate() != null) {
                    transferRxQueueDTO.setDeliveryDate(DateUtil.dateToString(orders.getDeliveryDate(), "MM-dd-yyyy"));
                } else {
                    transferRxQueueDTO.setDeliveryDate("");
                }
                transferRxQueueDTO.setRxNo(this.getLastThreeRxNumber(transferRxQueueDTO));
                String responseRequired = AppUtil.getSafeStr(orders.getResponseRequired(), "");
                transferRxQueueDTO.setMedicationSpecMsg(responseRequired);
                transferRxQueueList.add(transferRxQueueDTO);
            }
            map.put(orderId, orderId);

        }
        return transferRxQueueList;
    }

    /////////////////////////////////////////////////////////////////////////
    private List<TransferRxQueueDTO> populateTransferDTOFromTransferRxQueue(List<Object[]> list, int id) {
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        Map patientMap = new HashMap();
        for (Object[] arr : list) {
            PatientProfile patientProfile = (PatientProfile) arr[0];
            if (!patientMap.containsKey(patientProfile.getPatientProfileSeqNo())) {
                String hasRxCard = "";
                TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
                Order orders = (Order) arr[1];
                PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[2];
                if (patientProfile.getInsuranceFrontCardPath() != null) {
                    hasRxCard = "Y";
                } else {
                    hasRxCard = "N";
                }
                transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
                transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
                if (orders.getQty() != null && !orders.getQty().trim().equalsIgnoreCase("")) {
                    transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
                }
                transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
                transferRxQueueDTO.setOrderId(orders.getId());
                transferRxQueueDTO.setRxNumber(orders.getRxNo());
                transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
                //////////////////////////////////////////////
                try {
                    String videoStr = AppUtil.getSafeStr(orders.getVideo(), "").replace("localhost", "rxdirectdev.ssasoft.com");
                    if (videoStr.length() > 0 && videoStr.indexOf("http://") < 0) {
                        videoStr = "http://" + videoStr;
                    }
                    transferRxQueueDTO.setPtVideo(videoStr);
                    String imgStr = AppUtil.getSafeStr(orders.getImagePath(), "").replace("localhost", "rxdirectdev.ssasoft.com");
                    if (imgStr.length() > 0 && imgStr.indexOf("http://") < 0) {
                        imgStr = "http://" + imgStr;
                    }
                    transferRxQueueDTO.setTransferImage(imgStr);

                } catch (Exception e) {
                }

                /////////////////////////////////////////////
                transferRxQueueDTO.setAllergies(AppUtil.getSafeStr(patientProfile.getAllergies(), ""));
                transferRxQueueDTO.setGender(patientProfile.getGender());
                transferRxQueueDTO.setPayCash(true);
                transferRxQueueDTO.setMultiRx(false);
                transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
                transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
                transferRxQueueDTO.setStatus((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "OPEN (LOCKED)" : "Pending");
                transferRxQueueDTO.setDisabled((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "1" : "0");
                transferRxQueueDTO.setHasRxCard(hasRxCard);
                if (addr != null) {
                    transferRxQueueDTO.setZip(addr.getZip());
                }
                transferRxQueueList.add(transferRxQueueDTO);
                patientMap.put(patientProfile.getPatientProfileSeqNo(), transferRxQueueDTO);
            } else {
                TransferRxQueueDTO transferRxQueueDTO2 = (TransferRxQueueDTO) patientMap.get(patientProfile.getPatientProfileSeqNo());
                transferRxQueueDTO2.setMultiRx(true);
            }

        }
        return transferRxQueueList;
    }

    /////////////////////////////////////////////////////////////////////////
    public int getCountTransferDetails() throws Exception {

        int i = 0;
        try {
            i = consumerRegistrationDAO.getCountTransferDetails();
            return i;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> getCountTransferDetails", e);
        }
        return 0;
    }

    public void setTransferDetailPharmacyStatus(String status, int id) {

        try {
            consumerRegistrationDAO.setTransferDetailPharmacyStatus(status, id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> setTransferDetailPharmacyStatus", e);
        }

    }

    public void setOrderStatusWithId(String status, String id) throws Exception {
        try {
            consumerRegistrationDAO.setOrderStatusWithId(status, id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> setOrderStatusWithId", e);
        }

    }

    /////////////////////////////////////////////////////////////////
    public void updateOrderStatusWithIdAndUser(String status, String id, int userId) throws Exception {
        try {
            consumerRegistrationDAO.updateOrderViewStatusWithIdAndUser(status, id, userId, 1);//updateOrderStatusWithIdAndUser(status, id, userId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("Exception: ConsumerRegistrationService -> setOrderStatusWithId", e);
        }

    }

    /////////////////////////////////////////////////////////////////
    public void saveConsumerDetail(Pharmacy pharmacy) throws Exception {
//        try {
        // Set pharmacy facility operations

        for (PharmacyFacilityOperation pharmacyFacilityOperation : pharmacy.getPharmacyFacilityOperationList()) {
            pharmacyFacilityOperation.setCreatedOn(new Date());
            pharmacyFacilityOperation.setPharmacy(pharmacy);

        }
        String psw = RandomString.generatePassword();
        int counter = 0;
        for (PharmacyUser pharmacyUser : pharmacy.getPharmacyUserList()) {
            if (counter > 0) {
                /* String name = pharmacyUser.getFullName();*/
                String name = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();
                if (name != null && name.indexOf(' ') == -1) {
                    pharmacyUser.setFirstName(name);
                    pharmacyUser.setLastName("");
                } else {
                    pharmacyUser.setFirstName(name.substring(0, name.indexOf(' ')));
                    pharmacyUser.setLastName(name.substring(name.indexOf(' ') + 1));
                }
            }
            if (pharmacyUser.getSmsNotify() == null) {
                pharmacyUser.setSmsNotify("No");
            } else {
                pharmacyUser.setSmsNotify("Yes");
            }
            if (pharmacyUser.getEmailNotify() == null) {

                pharmacyUser.setEmailNotify("Yes");
            } else {
                pharmacyUser.setEmailNotify("No");
            }
            pharmacyUser.setCreatedOn(new Date());
            pharmacyUser.setPasswordUpdatedOn(new Date());
            //pharmacyUser.setPassword(RandomString.generatePassword());
            pharmacyUser.setPassword(CommonUtil.bCryptPasswordEncoder(psw));
            pharmacyUser.setStatus("Active");
            pharmacyUser.setPharmacy(pharmacy);
            RoleTypes roleTypes = (RoleTypes) this.consumerRegistrationDAO.findRecordById(new RoleTypes(), Constants.ROLE_TYPES.ADMIN_ROLE);
            if (roleTypes != null) {
                pharmacyUser.setRoleTypes(roleTypes);
            }
            counter++;
        }

        pharmacy.setTitle(pharmacy.getPharmacyLookup().getTitle());
        String npi = pharmacy.getPharmacyLookup().getNpi();
        if (npi != null && StringUtils.isNumeric(npi)) {
            pharmacy.setNpi(Long.parseLong(npi));
        }
        pharmacy.setAddress1(pharmacy.getPharmacyLookup().getAddress());
        pharmacy.setCity(pharmacy.getPharmacyLookup().getCity());
        pharmacy.setState(pharmacy.getPharmacyLookup().getState());
        pharmacy.setZip(pharmacy.getPharmacyLookup().getZip().longValue());
        pharmacy.setPhone(pharmacy.getPharmacyUserList().get(0).getPhone());
        pharmacy.setUserName(pharmacy.getPharmacyUserList().get(0).getEmail());
        pharmacy.setEmail(pharmacy.getPharmacyUserList().get(0).getEmail());
        pharmacy.setPassword(pharmacy.getPharmacyUserList().get(0).getPassword());
        pharmacy.setStatus("Active");
        pharmacy.setContactPerson(pharmacy.getPharmacyUserList().get(0).getFirstName() + " " + pharmacy.getPharmacyUserList().get(0).getLastName());
        pharmacy.setSalesRep(pharmacy.getPharmacyLookup().getSalesRep());
        pharmacy.setCreatedOn(new Date());
        if (pharmacy.getAcceptedTerms() == null) {
            pharmacy.setAcceptedTerms("No");
        } else {
            pharmacy.setAcceptedTerms("Yes");
        }
        if (pharmacy.getAcceptedPolicy() == null) {
            pharmacy.setAcceptedPolicy("No");
        } else {
            pharmacy.setAcceptedPolicy("Yes");
        }
        consumerRegistrationDAO.saveOrUpdate(pharmacy);

        // Send email
        for (PharmacyUser pharmacyUser : pharmacy.getPharmacyUserList()) {
            try {
                logger.info("PharmacyUser list size " + pharmacy.getPharmacyUserList().size());
                logger.info("PharmacyUser email: " + pharmacyUser.getEmail());
                logger.info("PharmacyUser password: " + pharmacyUser.getPassword());
                this.sendUsernameEmail(pharmacyUser.getEmail(), psw,Constants.ACCOUNT_CREATED_USERNAME);
                //Send psw to the user
//                this.sendPsw(pharmacyUser.getEmail(), psw,Constants.ACCOUNT_PASSWORD);

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception: ConsumerRegistrationService -> saveConsumerDetail --> sending email", e);
            }
        }

//        } catch (Exception e) {
//            logger.error("Exception: ConsumerRegistrationService -> saveConsumerDetail", e);
//            e.printStackTrace();
//        }
    }

    public void sendPsw(String userName, String email, String psw,String emailType) {
        /////////////////////////////////////////////////////////////
        CMSEmailContent emailContent = getCMSEmailContent(emailType);
        String emailBody = CommonUtil.relpacePlaceHolderForCredentials(
                emailContent.getEmailBody(),userName, psw, null, email);//this.relpacePlaceHolder(emailContent.getEmailBody(), pharmacyUser.getEmail(), pharmacyUser.getPassword(), null, null);
        EmailSenderUtil.sendEmail(email, emailContent.getSubject(), emailBody);
        ////////////////////////////////////////////////////////////
    }

    public void sendUsernameEmail(String email, String psw,String emailType) {
        // Send username email
        CMSEmailContent emailContent = getCMSEmailContent(emailType);
        String emailBody = CommonUtil.relpacePlaceHolderForCredentials(
                emailContent.getEmailBody(),email, psw, null, null);//this.relpacePlaceHolder(emailContent.getEmailBody(), pharmacyUser.getEmail(), pharmacyUser.getPassword(), null, null);
        EmailSenderUtil.sendEmail(email, emailContent.getSubject(), emailBody);
    }
      public void sendExceptiontoDevloperEmail(String email, Exception e,String emailType) {
        // Send username email
        CMSEmailContent emailContent = getCMSEmailContent(emailType);
        String serverMode = PropertiesUtil.getProperty("SERVER_MODE");
        String emailBody = CommonUtil.relpacePlaceHolderForCredentialsForExceptionPrint(emailContent.getEmailBody(), e, serverMode, email);
        EmailSenderUtil.sendEmail(email, emailContent.getSubject(), emailBody);
    }

    public void updateConsumerDetail(Pharmacy pharmacy) {
        try {
            Pharmacy updatedPharmacy = getPharmacyById(pharmacy.getId());
            // Set pharmacy facility operations
            updatedPharmacy.setPharmacyFacilityOperationList(pharmacy.getPharmacyFacilityOperationList());
            for (PharmacyFacilityOperation pharmacyFacilityOperation : updatedPharmacy.getPharmacyFacilityOperationList()) {
                pharmacyFacilityOperation.setPharmacy(updatedPharmacy);
            }
            updatedPharmacy.setPharmacyUserList(pharmacy.getPharmacyUserList());
            List<PharmacyUser> newlyCreatedUserList = new ArrayList<>();
            int counter = 0;
            for (PharmacyUser pharmacyUser : updatedPharmacy.getPharmacyUserList()) {
                if (counter > 0) {
                    if (pharmacyUser.getId() == null) {
                        String name = pharmacyUser.getFullName();
                        if (name.indexOf(' ') == -1) {
                            pharmacyUser.setFirstName(name);
                            pharmacyUser.setLastName("");
                        } else {
                            pharmacyUser.setFirstName(name.substring(0, name.indexOf(' ')));
                            pharmacyUser.setLastName(name.substring(name.indexOf(' ') + 1));
                        }
                        if (pharmacyUser.getRole().isEmpty()) {
                            pharmacyUser.setRole("user");
                        }
                        if (pharmacyUser.getSmsNotify() == null) {
                            pharmacyUser.setSmsNotify("No");
                        }
                        if (pharmacyUser.getEmailNotify() == null) {
                            pharmacyUser.setEmailNotify("Yes");
                        }
                        pharmacyUser.setPassword(RandomString.generatePassword());
                        pharmacyUser.setLastUpdatedOn(new Date());
                        pharmacyUser.setPasswordUpdatedOn(new Date());
                        pharmacyUser.setCreatedOn(new Date());
                        pharmacyUser.setStatus("Active");
                        newlyCreatedUserList.add(pharmacyUser);

                    } else {
                        if (pharmacyUser.getPasswordUpdatedOn() == null) {
                            pharmacyUser.setPasswordUpdatedOn(new Date());
                        }
                        if (pharmacyUser.getSmsNotify() == null) {
                            pharmacyUser.setSmsNotify("No");
                        }
                        if (pharmacyUser.getEmailNotify() == null) {
                            pharmacyUser.setEmailNotify("No");
                        }
                        pharmacyUser.setLastUpdatedOn(new Date());
                        pharmacyUser.setStatus("Active");
                    }
                } else {
                    if (pharmacyUser.getPasswordUpdatedOn() == null) {
                        pharmacyUser.setPasswordUpdatedOn(new Date());
                    }
                    if (pharmacyUser.getSmsNotify() == null) {
                        pharmacyUser.setSmsNotify("No");
                    }
                    if (pharmacyUser.getEmailNotify() == null) {
                        pharmacyUser.setEmailNotify("No");
                    }

                    pharmacyUser.setLastUpdatedOn(new Date());
                    pharmacyUser.setStatus("Active");
                }
                pharmacyUser.setPharmacy(updatedPharmacy);
                counter++;
            }

            //Find records to be deleted
            List<PharmacyUser> pharmacyUsersToBeDeleted = new ArrayList<>();

            if (updatedPharmacy.getPharmacyUserList().size() >= pharmacy.getPharmacyUserList().size()) {
                for (PharmacyUser pharmacyUser : updatedPharmacy.getPharmacyUserList()) {
                    boolean isDeleted = true;
                    for (PharmacyUser pharmacyUser1 : pharmacy.getPharmacyUserList()) {
                        if (pharmacyUser.getId().equals(pharmacyUser1.getId())) {
                            isDeleted = false;
                            if (pharmacyUser1.getFirstName() != null) {
                                pharmacyUser.setFirstName(pharmacyUser1.getFirstName());
                            }

                            if (pharmacyUser1.getLastName() != null) {
                                pharmacyUser.setLastName(pharmacyUser1.getLastName());
                            }

                            if (pharmacyUser1.getFullName() != null) {
                                String name = pharmacyUser1.getFullName();
                                pharmacyUser.setFirstName(name.substring(0, name.indexOf(' ')));
                                pharmacyUser.setLastName(name.substring(name.indexOf(' ') + 1));
                            }

                            pharmacyUser.setPhone(pharmacyUser1.getPhone());
                            pharmacyUser.setPharmacy(updatedPharmacy);
                            break;
                        }
                    }
                    if (isDeleted) {
                        pharmacyUsersToBeDeleted.add(pharmacyUser);
                    }
                }
            }

            updatedPharmacy.getPharmacyUserList().addAll(newlyCreatedUserList);
            updatedPharmacy.getPharmacyUserList().removeAll(pharmacyUsersToBeDeleted);

            consumerRegistrationDAO.deletePharmacyUsers(pharmacyUsersToBeDeleted);
            consumerRegistrationDAO.merge(updatedPharmacy);

            // Send email
            for (PharmacyUser pharmacyUser : newlyCreatedUserList) {
                try {
                    // Send username email
                    CMSEmailContent emailContent = getCMSEmailContent(Constants.ACCOUNT_CREATED_USERNAME);
                    String emailBody = this.relpacePlaceHolder(emailContent.getEmailBody(), pharmacyUser.getEmail(), pharmacyUser.getPassword(), null, null);
                    EmailSenderUtil.send(pharmacyUser.getEmail(), emailContent.getSubject(), emailBody);
                } catch (Exception e) {
                    logger.error("Exception: ConsumerRegistrationService -> saveConsumerDetail --> sending email", e);
                }
            }

            // Send email deleted user
            for (PharmacyUser pharmacyUser : pharmacyUsersToBeDeleted) {
                try {
                    // Send username email
                    CMSEmailContent emailContent = getCMSEmailContent(Constants.SECONDARY_ACCOUNT_DELETED);
                    String emailBody = this.relpacePlaceHolder(emailContent.getEmailBody(), pharmacyUser.getEmail(), null, pharmacy.getTitle(), pharmacyUser.getEmail());
                    EmailSenderUtil.send(pharmacyUser.getEmail(), emailContent.getSubject(), emailBody);
                } catch (Exception e) {
                    logger.error("Exception: ConsumerRegistrationService -> saveConsumerDetail --> sending email", e);
                }
            }

        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> saveConsumerDetail", e);
        }
    }

    public PharmacyUser getPharmacyUserByEmail(String email) {
        PharmacyUser pharmacyUser = new PharmacyUser();
        try {
            pharmacyUser = consumerRegistrationDAO.getPharmacyUserByEmail(email);
            Hibernate.initialize(pharmacyUser.getRoleTypes());
            Hibernate.initialize(pharmacyUser.getPharmacy());
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyUserByEmail", e);
        }

        return pharmacyUser;
    }

    public Pharmacy getPharmacyByEmail(String email) {
        Pharmacy pharmacy = new Pharmacy();
        try {
            pharmacy = consumerRegistrationDAO.getPharmacyByEmail(email);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyByEmail", e);
        }

        return pharmacy;
    }

    public PharmacyUser forgotPassword(PharmacyUser pharmacyUser) {
        try {
            String psw = RandomString.generatePassword();
            pharmacyUser.setPassword(CommonUtil.bCryptPasswordEncoder(psw));
            consumerRegistrationDAO.update(pharmacyUser);

            if (pharmacyUser.getRole().equalsIgnoreCase("admin")) {
                Pharmacy pharmacy = getPharmacyByEmail(pharmacyUser.getEmail());
                pharmacy.setPassword(pharmacyUser.getPassword());
                consumerRegistrationDAO.update(pharmacy);
            }

            CMSEmailContent emailContent = getCMSEmailContent(Constants.FORGOT_PASSWORD);
            String emailBody = this.relpacePlaceHolder(emailContent.getEmailBody(), null, psw, null, null);
            EmailSenderUtil.send(pharmacyUser.getEmail(), emailContent.getSubject(), emailBody);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> forgotPassword", e);
        }

        return pharmacyUser;
    }

    public void changePharmacyPassword(String email, String newPassword) {
        try {
            // Update password and send email
            Pharmacy pharmacy = getPharmacyByEmail(email);
            PharmacyUser pharmacyUser = getPharmacyUserByEmail(email);

            String cryptPsw = CommonUtil.bCryptPasswordEncoder(newPassword);
            if (pharmacy != null) { // in case of admin user
                pharmacy.setPassword(cryptPsw);
                pharmacyUser.setPassword(cryptPsw);
                pharmacyUser.setPasswordUpdatedOn(new Date());
                consumerRegistrationDAO.update(pharmacy);
            } else { // in case of secondary user
                pharmacyUser.setPasswordUpdatedOn(new Date());
                pharmacyUser.setPassword(cryptPsw);
                consumerRegistrationDAO.update(pharmacyUser);
            }
            CMSEmailContent emailContent = getCMSEmailContent(Constants.CHANGE_PASSWORD);
            String emailBody = this.relpacePlaceHolder(emailContent.getEmailBody(), null, newPassword, null, null);
            EmailSenderUtil.send(pharmacyUser.getEmail(), emailContent.getSubject(), emailBody);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> changePharmacyPassword", e);
        }

    }

    public Set getPharmacyUserList(int pharmacyId) {
        Set<PharmacyUser> pharmacyUserList = new LinkedHashSet<>();
        try {
            pharmacyUserList = consumerRegistrationDAO.getPharmacyUserList(pharmacyId);
            for (PharmacyUser pharmacyUser : pharmacyUserList) {
                pharmacyUser.setFullName(pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName());
            }
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyUserList", e);
        }
        return pharmacyUserList;
    }

    public Set getPharmacyFacilityOperations(int pharmacyId) {
        Set<PharmacyFacilityOperation> pharmacyFacilityOperations = new LinkedHashSet<>();
        try {
            pharmacyFacilityOperations = consumerRegistrationDAO.getPharmacyFacilityOperations(pharmacyId);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyFacilityOperations", e);
        }
        return pharmacyFacilityOperations;
    }

    public Pharmacy getPharmacyById(int pharmacyId) {
        Pharmacy pharmacy = new Pharmacy();
        try {
            pharmacy = consumerRegistrationDAO.getPharmacyById(pharmacyId);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyById", e);
        }
        return pharmacy;
    }

    public List<Pharmacy> getPharmacyByNpi(String npi, Integer pharmacyLookupId) {
        List<Pharmacy> list = new ArrayList<>();
        try {
            list = consumerRegistrationDAO.getPharmacyByNpi(Long.parseLong(npi), pharmacyLookupId);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyByNpi", e);
        }
        return list;
    }

    public Order getAccountOverView(int pharmacyId) {
        Order order = new Order();
        try {
            int assignedCount = consumerRegistrationDAO.getOrderCount(pharmacyId, 2);
            int filledCount = consumerRegistrationDAO.getOrderCount(pharmacyId, 3);
            int shippedCount = consumerRegistrationDAO.getOrderCount(pharmacyId, 4);
            int deniedCount = consumerRegistrationDAO.getOrderCount(pharmacyId, 5);

            double totalRevenue = consumerRegistrationDAO.getOrderRevenueByPharmacy(pharmacyId);
            double totalIngredientCost = consumerRegistrationDAO.getIngredientCostByPharmacy(pharmacyId);
            double netProfit = totalRevenue - totalIngredientCost;

            order.setTotalOrder(assignedCount + filledCount + shippedCount + deniedCount);
            order.setOpenOrder(assignedCount);
            order.setTotalfillOrder(filledCount);
            order.setCompleteOrder(shippedCount);
            order.setTotalDeniedOrders(deniedCount);
            order.setTotalRevenue(totalRevenue);
            order.setTotalIngredientCost(totalIngredientCost);
            order.setNetProfit(netProfit);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getAccountOverView", e);
        }
        return order;
    }

    public String getIntgredients(String transactionNo) throws Exception {
        List<RedemptionIngredient> redemptionIngredientList = new ArrayList<>();
        String json = "";
        try {
            List<RedemptionIngredient> tempList = orderDao.getRedemptionIngredients(transactionNo);
            for (RedemptionIngredient redemptionIngredient : tempList) {
                Drug drug = consumerRegistrationDAO.getDrugByNdc(redemptionIngredient.getNdc());
                RedemptionIngredient newRedemptionIngredient = new RedemptionIngredient();
                if (drug != null) {
                    newRedemptionIngredient.setId(redemptionIngredient.getId());
                    newRedemptionIngredient.setName(drug.getDrugBrand().getDrugBrandName());
                    //newRedemptionIngredient.setName(drug.getDrugBrand().getGenericName());
                    newRedemptionIngredient.setNdc(redemptionIngredient.getNdc());
                    newRedemptionIngredient.setNdcMatch(Boolean.TRUE);
                } else {
                    newRedemptionIngredient.setId(redemptionIngredient.getId());
                    newRedemptionIngredient.setName("Non-API");
                    newRedemptionIngredient.setNdc(redemptionIngredient.getNdc());
                    newRedemptionIngredient.setNdcMatch(Boolean.FALSE);
                }
                redemptionIngredientList.add(newRedemptionIngredient);
            }

            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(redemptionIngredientList);

        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getIntgredients", e);
            throw e;
        }
        return json;
    }

    public CMSEmailContent getCMSEmailContent(String emailType) {
        CMSEmailContent emailContent = new CMSEmailContent();
        try {
            emailContent = consumerRegistrationDAO.getCMSEmailContent(emailType);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getCMSEmailContent", e);
        }
        return emailContent;
    }

    public String relpacePlaceHolder(String emailBody, String userName, String password, String pharmacyName, String userEmail) {
        if (userName != null) {
            emailBody = emailBody.replace("_username_", userName);
        }
        if (password != null) {
            emailBody = emailBody.replace("_password_", password);
        }
        if (pharmacyName != null) {
            emailBody = emailBody.replace("PHARMACY_NAME", pharmacyName);
        }
        if (userEmail != null) {
            emailBody = emailBody.replace("_EMAIL_", userEmail);
        }
        return emailBody;
    }

    public List<PharmacyUser> getPharmacyUsersList() {
        List<PharmacyUser> list = new ArrayList<>();
        try {
            logger.info("Reminder date is :" + DateUtil.getDateVariation(Constants.DAYS, new Date()));
            list = consumerRegistrationDAO.getPharmacyUsersList(DateUtil.getDateVariation(Constants.DAYS, new Date()));
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyUsersList", e);
        }
        return list;
    }

    public void updatePharmacyUsersPasswordDate(String email) {
        try {
            consumerRegistrationDAO.updatePharmacyUsersPasswordDate(email, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> updatePharmacyUsersPasswordDate", e);
        }
    }

    public List<PharmacyUser> getPharmacyUsersList(int hour, String day) {
        List<PharmacyUser> list = new ArrayList<>();
        try {
            int diffHour = hour - 4;
            logger.info("Current Hour is: " + hour);
            logger.info("Current Day is: " + day);
            logger.info("2nd notify Hour: " + diffHour);
            list = consumerRegistrationDAO.getPharmacyUsersList(hour, day, diffHour);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getPharmacyUsersList", e);
        }
        return list;
    }

    public void setPharmacyUserLastLoggedInTime(Integer currentUserId) {
        try {
            consumerRegistrationDAO.setPharmacyUserLastLoggedInTime(currentUserId);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> setPharmacyUserLastLoggedInTime", e);
        }
    }

    public CampaignMessages getMessageType(String title) {
        CampaignMessages campaignMessages = new CampaignMessages();
        try {
            MessageType messageType = consumerRegistrationDAO.getMessageType(title);
            List<CampaignMessages> messageses = new ArrayList<>(messageType.getCampaignMessageses());
            campaignMessages = messageses.get(0);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getMessageType", e);
        }
        return campaignMessages;
    }

    public boolean validatePhoneNo(String phoneNo) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNo);
        if (!matcher.matches()) {
            logger.info("valid 10 digit phone number required: " + phoneNo);
            return false;
        }
        String PVURL = textFlowService.getURL(Constants.PVURL);
        PhoneValidationService phoneValidationService = new PhoneValidationService(PVURL);
        boolean phoneValidity = phoneValidationService.checkPhoneValidity(phoneNo, "0000", "PMS");
        if (!phoneValidity) {
            logger.info("Invalid phone: " + phoneNo);
            return false;
        }
        return true;
    }

    public List<Order> getOrdersList() {
        List<Order> list = new ArrayList<>();
        try {
            list = orderDao.getRecentOrderByPharmacyId();
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getOrdersList", e);
        }
        return list;
    }

    public List<TransferRxQueueDTO> getTranferRxQueue() throws Exception {
        List<TransferRxQueueDTO> list = consumerRegistrationDAO.getTranferRxQueue();
        return list;
    }

    public List<TransferRequest> getSelectedTransferRx(Integer id) throws Exception {
        List<TransferRequest> list = consumerRegistrationDAO.getSelectTransferRx(id);
        return list;
    }

    public List<TransferDetail> getSelectTransferRxListByRequestId(int id) {
        try {
            List<TransferDetail> transferDetailList = consumerRegistrationDAO.getSelectTransferRxListByRequestId(id);
            return transferDetailList;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> getSelectTransferRxListByRequestId", e);
        }
        return null;
    }

    public List<TransferRxQueueDTO> getTranferDetailListByRequestId(int requestId) throws Exception {

        try {
            List<TransferRxQueueDTO> list = consumerRegistrationDAO.getTranferDetailListByRequestId(requestId);
            return list;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> getTranferDetailListByRequestId", e);
        }
        return null;
    }

    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileOrder(int patientId, String orderId) throws Exception {
        try {
            List<TransferRxQueueDTO> list = consumerRegistrationDAO.getTranferRxQueueByPatientProfileOrder(patientId, orderId);
            return list;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileOrder", e);
        }
        return null;
    }

    public List<TransferRxQueueDTO> getTranferDetailListById(int tId, int requestId) throws Exception {
        try {
            List<TransferRxQueueDTO> list = consumerRegistrationDAO.getTranferDetailListById(tId, requestId);
            return list;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> getTranferDetailListByRequestId", e);
        }
        return null;
    }

    public TransferRequest getSelectTransferRxById(int id) {

        TransferRequest tr = new TransferRequest();
        try {
            tr = consumerRegistrationDAO.getSelectTransferRxById(id);
            return tr;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception occured", e);
        }
        return null;
    }

    public TransferRxQueueDTO getTranferDetailListByTransferDetailId(int transferDetailId) throws Exception {
        try {
            TransferRxQueueDTO transferRxQueue = consumerRegistrationDAO.getTranferDetailListByTransferDetailId(transferDetailId);
            return transferRxQueue;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception: ConsumerRegistrationService -> getTranferDetailListByRequestId", e);
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    private int getCurrentOrderIndex(String orderId, List lst) {
        int index = 0;
        for (Object obj : lst) {
            Order o = (Order) obj;
            index++;
            if (o.getId().equals(orderId)) {
                break;
            }
        }
        return index;
    }
    ///////////////////////////////////////////////////////////////////////////////////////

    public TransferRxQueueDTO getOrderDetailListById(String orderId, int pharmacyId) throws Exception {
        try {
            TransferRxQueueDTO transferRxQueue = consumerRegistrationDAO.getOrderDetailListById(orderId);
            ////////////////////////////////////////////////////////////////////////////////////////////
            if (AppUtil.getSafeStr(transferRxQueue.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                List lstQuestions = this.consumerRegistrationDAO.findByNestedProperty(new QuestionAnswer(),
                        "order.id", transferRxQueue.getOrderId(), Constants.HIBERNATE_EQ_OPERATOR, "id", Constants.HIBERNATE_DESC_ORDER);
                if (lstQuestions != null && lstQuestions.size() > 0) {
                    QuestionAnswer answer = (QuestionAnswer) lstQuestions.get(0);
//                    transferRxQueue.setQuestionId(answer.getId());
                    transferRxQueue.setQuestion(answer.getQuestion());
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////
            List lst = consumerRegistrationDAO.findByProperty(new OrderHistory(), "order.id", orderId,
                    Constants.HIBERNATE_EQ_OPERATOR, "id", Constants.HIBERNATE_DESC_ORDER);
            if (lst != null && lst.size() > 0) {
                OrderHistory history = (OrderHistory) lst.get(0);
                transferRxQueue.setOrderHistoryMessage(AppUtil.getSafeStr(history.getComments(), ""));
            }
//            List<BusinessObject> lstObj = new ArrayList();
//            BusinessObject obj = new BusinessObject("patientProfile.id", transferRxQueue.getPatientId(),
//                    Constants.HIBERNATE_EQ_OPERATOR);
//            lstObj.add(obj);
//            obj = new BusinessObject("orderStatus.id", transferRxQueue.getStatusId(), Constants.HIBERNATE_EQ_OPERATOR);
//            lstObj.add(obj);
//            List lstOrdersWithSameStatus = this.consumerRegistrationDAO.findByNestedProperty(new Order(),
//                    lstObj, "createdOn", Constants.HIBERNATE_DESC_ORDER);//.getMultiRxOrdersByPatientId(
            if (transferRxQueue.getStatusId() == 6 || transferRxQueue.getStatusId() == Constants.ORDER_STATUS.READY_TO_DELIVER_ID) {
                List<BusinessObject> lstObj = new ArrayList();
                BusinessObject obj = new BusinessObject("patientProfile.id", transferRxQueue.getPatientId(), Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("readyToDeliverRxOrders.id", transferRxQueue.getReadyToDeliverId(), Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("deliveryPreference.id", transferRxQueue.getDeliveryId(), Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("id", orderId, Constants.HIBERNATE_NE_OPERATOR);
                lstObj.add(obj);

                Long multiRxCount = (Long) consumerRegistrationDAO.getTotalRecordsByNestedProperty(new Order(), lstObj, "Count(*)");
                if (multiRxCount > 0) {
                    transferRxQueue.setMultiRx(true);
                    transferRxQueue.setMultiRxCount(multiRxCount.intValue());
                    if (transferRxQueue.getMultiRxCount() <= 9) {
                        transferRxQueue.setMultiRxLabel("Y-0" + transferRxQueue.getMultiRxCount());
                    } else {
                        transferRxQueue.setMultiRxLabel("Y-" + transferRxQueue.getMultiRxCount());
                    }
                } else {
                    transferRxQueue.setMultiRx(false);
                    transferRxQueue.setMultiRxCount(1);
                    transferRxQueue.setMultiRxLabel("N-" + (CommonUtil.isNullOrEmpty(multiRxCount) ? 0 : multiRxCount.intValue()));
                }
            } else {
                List lstOrdersWithSameStatus = this.consumerRegistrationDAO.getStatuswiseOrders(
                        transferRxQueue.getStatusId() == 5
                        || transferRxQueue.getStatusId() == 6
                        || transferRxQueue.getStatusId() == 15
                        ? 8 : transferRxQueue.getStatusId(),
                        transferRxQueue.getPatientId(), transferRxQueue.getDependentId(), pharmacyId);
                //AppUtil.getSafeInt(orderId, 0), transferRxQueue.getPatientId());
                if (lstOrdersWithSameStatus != null && lstOrdersWithSameStatus.size() > 1) {
                    int index = lstOrdersWithSameStatus != null ? lstOrdersWithSameStatus.size() : 0;//this.getCurrentOrderIndex(orderId, lstOrdersWithSameStatus);
                    transferRxQueue.setMultiRxCount(index);
                    if (index <= 9) {
                        transferRxQueue.setMultiRxLabel("Y-0" + index);
                    } else {
                        transferRxQueue.setMultiRxLabel("Y-" + index);
                    }

                } else {
                    transferRxQueue.setMultiRxCount(1);
                    transferRxQueue.setMultiRxLabel("N-" + (lstOrdersWithSameStatus != null ? lstOrdersWithSameStatus.size() : 0));
                }
                transferRxQueue.setMultiRx(lstOrdersWithSameStatus != null && lstOrdersWithSameStatus.size() > 1);
            }
            long processedOrdersCount = this.getProcessedOrdersCount(orderId, pharmacyId);
            transferRxQueue.setProcessedOrderCount(processedOrdersCount);
//            if (transferRxQueue.getTransferDetail() != null) {
//                TransferRequest transferRequest = (TransferRequest) baseDAO.findRecordById(new TransferRequest(), transferRxQueue.getTransferDetail().getRequestId());
//                if (CommonUtil.isNotEmpty(transferRequest.getVideo())) {
//                    transferRxQueue.setPtVideo(transferRequest.getVideo());
//                }
//            }
            List<OrderTransferImages> lstOrderTransferImageses = consumerRegistrationDAO.findByProperty(new OrderTransferImages(), "order.id", orderId,
                    Constants.HIBERNATE_EQ_OPERATOR, "id", Constants.HIBERNATE_ASC_ORDER);
            transferRxQueue.setOrderTransferImages(lstOrderTransferImageses != null ? lstOrderTransferImageses : new ArrayList<>());
            transferRxQueue.setMultipleImages(lstOrderTransferImageses != null && lstOrderTransferImageses.size() > 1);
            transferRxQueue.setImageId(lstOrderTransferImageses != null
                    && lstOrderTransferImageses.size() > 0 ? lstOrderTransferImageses.get(0).getId() : 0);
            if (lstOrderTransferImageses != null && lstOrderTransferImageses.size() > 0) {
                transferRxQueue.setTransferImage(lstOrderTransferImageses.get(0).getDrugImg());
            }
            return transferRxQueue;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("Exception: ConsumerRegistrationService -> getOrderDetailListById", e);
        }
        return null;

    }

    /**
     *
     * @param pharmacyUser
     * @return
     */
    public PharmacyUser forgotUserNamePassword(PharmacyUser pharmacyUser) {
        try {
            pharmacyUser.setPassword(RandomString.generatePassword());
            consumerRegistrationDAO.updatePharmacyUserPsw(pharmacyUser.getId(), CommonUtil.bCryptPasswordEncoder(pharmacyUser.getPassword()));

            if (pharmacyUser.getRole().equalsIgnoreCase("admin")) {
                Pharmacy pharmacy = getPharmacyByEmail(pharmacyUser.getEmail());
                if (pharmacy != null) {
                    pharmacy.setPassword(pharmacyUser.getPassword());
                    consumerRegistrationDAO.updatePharmacyPswById(pharmacy.getId(), CommonUtil.bCryptPasswordEncoder(pharmacy.getPassword()));
                }
            }

            CMSEmailContent emailContent = getCMSEmailContent(Constants.FORGOT_PASSWORD);
            String emailBody = CommonUtil.relpacePlaceHolderForCredentials(emailContent.getEmailBody(), pharmacyUser.getEmail(), pharmacyUser.getPassword(), null, null);
            EmailSenderUtil.send(pharmacyUser.getEmail(), emailContent.getSubject(), emailBody);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: ConsumerRegistrationService -> forgotPassword", e);
        }

        return pharmacyUser;
    }

    public Object saveOrUpdateObject(Object transientInstance) {
        Object obj = null;
        obj = this.consumerRegistrationDAO.saveOrUpdate(transientInstance);
        return obj;

    }

    public int getTransferRListCount(int patientId, String orderId) throws Exception {
        int i = 0;
        try {
            i = this.consumerRegistrationDAO.getTransferRListCount(patientId, orderId);
            return i;
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getTransferRListCount", e);
        }
        return 0;
    }

    /**
     *
     *
     * @param id order id
     * @param createdBy user updating order, in case update is called from app,
     * then send 0 in this field
     * @param statusVal status id
     * @return
     * @throws Exception
     */
    public int updateOrderStatusNew(int id, int statusVal) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            status.setId(statusVal);
            order.setOrderStatus(status);
            orderDao.saveOrUpdate(order);

        }

        return 1;
    }

    public int updateOrderStatus(int id, int createdBy, int statusVal) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            if (statusVal == Constants.ORDER_STATUS.PAYMENT_AUTHORIZED_ID) {
                status.setId(Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID);
                order.setDisabledFlds(0);
                order.setPatientResponse("Accepted to proceed.");
            } else {
                status.setId(statusVal);
            }
            order.setOrderStatus(status);
            if (statusVal == 2) {
                order.setViewStatus("Pending");
            }
            if (createdBy <= 0) {
                order.setPatientResponse(getOrderStatusPatientResponse(statusVal));
            }
            if (statusVal == 8) {
                order.setDisabledFlds(0);
                order.setPatientResponse("Accepted fill request.");
               
                Date fillDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
                order.setFilledDate(fillDate);
               
                /////////////////////////////////////////////////////////
                
                ///////////////////////////////////////////////////////
            }
            order.setUpdatedBy(createdBy);
            order.setUpdatedOn(new Date());
            order.setStatusCreatedOn(new Date());
            orderDao.saveOrUpdate(order);
            OrderHistory history = new OrderHistory();
            history.setOrder(order);
            history.setOrderStatus(status);
            history.setCreatedBy(createdBy);
            history.setUpdatedBy(createdBy);
            history.setCreatedOn(new Date());
            history.setUpdatedOn(new Date());
            history.setPatientResponseLog(getOrderStatusPatientResponse(statusVal));
            orderDao.saveOrUpdate(history);
        }
        return 1;
    }

    public int updateOrderStatus(int id, int pharmacyId, int createdBy, int statusVal) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            status.setId(statusVal);
            order.setOrderStatus(status);
            if (statusVal == 2) {
                order.setViewStatus("Pending");
            }
            order.setPatientResponse(getOrderStatusPatientResponse(statusVal));
            if (statusVal == 8) {
               
                Date fillDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
                order.setFilledDate(fillDate);
                
              
                ///////////////////////////////////////////////////////
            }
            populatePharmacy(pharmacyId, order);
            order.setUpdatedBy(createdBy);
            order.setUpdatedOn(new Date());
            order.setStatusCreatedOn(new Date());
            orderDao.saveOrUpdate(order);
            OrderHistory history = new OrderHistory();
            history.setOrder(order);
            history.setOrderStatus(status);
            history.setCreatedBy(createdBy);
            history.setUpdatedBy(createdBy);
            history.setCreatedOn(new Date());
            history.setUpdatedOn(new Date());
            history.setPatientResponseLog(getOrderStatusPatientResponse(statusVal));
            orderDao.saveOrUpdate(history);
        }
        return 1;
    }

    private void populatePharmacy(int pharmacyId, Order order) {
        if (!CommonUtil.isNullOrEmpty(pharmacyId)) {
            order.setPharmacy(new Pharmacy(pharmacyId));
        }
    }

    //////////////////////////////////////////////////////////////////
    public int updateOrderStatusByPatient(int id, int statusVal) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
//            OrderStatus status = new OrderStatus();
            if (statusVal == Constants.ORDER_STATUS.PAYMENT_AUTHORIZED_ID) {
//                status.setId(Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID);
                order.setDisabledFlds(0);
                this.setPatientResponseAttributesForOrder(order, Constants.PATIENT_RESPONSES.PAYMENT_AUTHORIZATION);
//                order.setPatientResponse(Constants.PATIENT_RESPONSES.PAYMENT_AUTHORIZATION);
//                order.setResponseFullFilled("0");
            } else if (statusVal == Constants.ORDER_STATUS.CANCEL_ORDER_ID) {
//                status.setId(Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID);
                order.setDisabledFlds(1);
                order.setRequiresDeletion(1);
                this.setPatientResponseAttributesForOrder(order, Constants.PATIENT_RESPONSES.DELETE_ACTION);
//                order.setPatientResponse(Constants.PATIENT_RESPONSES.DELETE_ACTION);
//                order.setResponseFullFilled("0");
            } else {
                OrderStatus status = new OrderStatus();
                status.setId(statusVal);
                order.setOrderStatus(status);
            }
//            order.setOrderStatus(status);
            if (statusVal == 2) {
                order.setViewStatus("Pending");
            }
//            order.setPatientResponse(getOrderStatusPatientResponse(statusVal));

            if (statusVal == 8) {
                order.setDisabledFlds(0);
                order.setPatientResponse(Constants.PATIENT_RESPONSES.FILL_ACCEPTED_MESSAGE);
               
                Date fillDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
                order.setFilledDate(fillDate);
               
               
                ///////////////////////////////////////////////////////
            }

            order.setUpdatedOn(new Date());
            order.setStatusCreatedOn(new Date());
            
            orderDao.saveOrUpdate(order);
//            OrderHistory history = new OrderHistory();
//            history.setOrder(order);
//            history.setOrderStatus(order.getOrderStatus());
//            history.setCreatedBy(0);
//            history.setUpdatedBy(0);
//            history.setCreatedOn(new Date());
//            history.setUpdatedOn(new Date());
//            history.setPatientResponseLog(getOrderStatusPatientResponse(statusVal));
//            orderDao.saveOrUpdate(history);
        }
        return 1;
    }
    //////////////////////////////////////////////////////////////////

//     public int updateOrderStatusByUserId(int id, int userId) throws Exception {
//        Order order = this.orderDao.getOrdersById("" + id);
//        if (order != null) {
//          
//            order.setUpdatedBy(userId);
//            order.setUpdatedOn(new Date());
//            orderDao.update(order);
//        }
//        return 1;
//    }
    private String getOrderStatusPatientResponse(int statusId) {
        String response;
        switch (statusId) {
            case 1:
                response = "Pending";
                break;
            case 2:
                response = "Processing";
                break;
            case 3:
                response = "Pending Pharmacy Reply";
                break;
            case 4:
                response = "Delayed / Rx On Order";
                break;
            case 5:
                response = "DELIVERY";
                break;
            case 6:
                response = "Shipped";
                break;
            case 7:
                response = "On Hold";
                break;
            case 8:
                response = "Filled";
                break;
            case 9:
                response = "Waiting payment authorization";
                break;
            case 10:
                response = "Payment Authorized";
                break;
            case 11:
                response = "Requested to cancel order.";
                break;
            case 12:
                response = "Fill as Cash";
                break;
            case 13:
                response = "Ready to Fill";
                break;
            case 14:
                response = "Fill Request Accepted";
                break;
            case 15:
                response = "Pickup At Pharmacy";
                break;
            case 16:
                response = "Waiting Pt Response";
                break;
            case 17:
                response = "Interpreted Image";
                break;
            default:
                response = "Invalid Response";
                break;
        }
        return response;
    }

    /**
     *
     *
     * @param id order id
     * @param createdBy user updating order, in case update is called from app,
     * then send 0 in this field
     * @param statusVal status id
     * @param authorizePayment Payment Authorization id
     * @return
     * @throws Exception
     */
    public int updateOrderStatusAndAuthorization(int id, int createdBy, int statusVal, String authorizePayment) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            status.setId(statusVal);
            order.setOrderStatus(status);
            order.setPaymentAuthorization(authorizePayment);
            if (statusVal == 2) {
                order.setViewStatus("Pending");
            }
            orderDao.saveOrUpdate(order);
            OrderHistory history = new OrderHistory();
            history.setOrder(order);
            history.setOrderStatus(status);
            history.setCreatedBy(createdBy);
            history.setUpdatedBy(createdBy);
            history.setCreatedOn(new Date());
            history.setUpdatedOn(new Date());
            orderDao.saveOrUpdate(history);
        }
        return 1;
    }

    /////////////////////////////////////////////////////////////////////
    public int updateOrderStatusAndAuthorization(int id, int createdBy, int statusVal, String authorizePayment, int disabledFld, String patientResponse) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            status.setId(19);
            order.setOrderStatus(status);
            order.setPaymentAuthorization(authorizePayment);
            if (statusVal == 2) {
                order.setViewStatus("Pending");
            }
            order.setDisabledFlds(disabledFld);
            order.setPatientResponse(patientResponse);
            order.setResponseFullFilled("0");
            setPatientResponseAttributesForOrder(order, patientResponse);
            orderDao.saveOrUpdate(order);
            OrderHistory history = new OrderHistory();
            history.setOrder(order);
            history.setOrderStatus(status);
            history.setCreatedBy(createdBy);
            history.setUpdatedBy(createdBy);
            history.setCreatedOn(new Date());
            history.setUpdatedOn(new Date());
            orderDao.saveOrUpdate(history);
        }
        return 1;
    }

    ////////////////////////////////////////////////////////////////////
    public int updateOrderStatus(int id, int createdBy, int statusVal, String comments) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            if (statusVal > 0) {
                status.setId(statusVal);
            } else {
                status = order.getOrderStatus();
            }
            order.setOrderStatus(status);
            if (statusVal > 1) {
                order.setViewStatus("Pending");
            }
            orderDao.saveOrUpdate(order);
            saveOrderHistory(order, status, createdBy, comments);
        }
        return 1;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public int updateOrderStatus(int id, int createdBy, int statusVal, String comments, String rxNumber, String brandName,
            String type, String strength, String qty, String daysSupply, String refillsAllowed,
            String refillUsed, String paymentMode, String patientName, String pharmacyName,
            String pharmacyPhone, String prescriberName, String prescriberPhone,
            String prescriberNPI, String acqCost, String reimb, String ptCopay) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            if (statusVal > 0) {
                status.setId(statusVal);
            } else {
                status = order.getOrderStatus();
            }
            order.setOrderStatus(status);
            if (statusVal > 1) {
                order.setViewStatus("Pending");
            }
            ///////////////////////////////////////////////////////////////////
            order.setRxNumber(rxNumber);
            order.setDaysSupply(AppUtil.getSafeInt(daysSupply, 0));
            order.setRefillsAllowed(AppUtil.getSafeInt(refillsAllowed, 0));
            order.setRefillsRemaining(AppUtil.getSafeInt(refillUsed, 0));
            order.setPaymentMode(paymentMode);
            //order.setPatientName(patientName);
            order.setPharmacyName(pharmacyName);
            order.setPharmacyPhone(pharmacyPhone);
            order.setPrescriberName(prescriberName);
            order.setPrescriberPhone(prescriberPhone);
            order.setPrescriberNPI(prescriberNPI);
            order.setRxAcqCost(AppUtil.getSafeDouble(acqCost, 0d));
            order.setRxReimbCost(AppUtil.getSafeDouble(reimb, 0d));
            order.setProfitMargin(AppUtil.getSafeDouble(reimb, 0d) - AppUtil.getSafeDouble(acqCost, 0d));
            order.setOriginalPtCopay(AppUtil.getSafeDouble(ptCopay, 0d));

            getDrugDetail(brandName, type, strength, order, qty);
            //////////////////////////////////////////////////////////////////
            orderDao.saveOrUpdate(order);
            saveOrderHistory(order, status, createdBy, comments);
        }
        return 1;
    }

    public void getDrugDetail(String brandName, String type, String strength, Order order, String qty) throws Exception {
        DrugDetail tmp = this.searchDrugByDrugTypeAndStrength(brandName, type, strength, order);
        if (tmp != null && order.getPatientProfile() != null) {
            DrugDetail drugDetail = this.patientProfileService.getDrugDetailInfoTemp(tmp.getDrugDetailId(),
                    AppUtil.getSafeInt(qty, 0), order.getPatientProfile());

            order.setDrugDetail(drugDetail);
            order.setDrugPrice(drugDetail.getDrugCost().doubleValue());
            order.setAdditionalMargin(drugDetail.getAdditionalMargin().doubleValue());
            order.setRedeemPoints("" + drugDetail.getRedeemedPoints());
            order.setRedeemPointsCost(drugDetail.getRedeemedPointsPrice().doubleValue());
            order.setRequiresHandDelivery(drugDetail.getRequiresHandDelivery());
            order.setRxExpiry(drugDetail.getMonthRxExpiration());
            List<RewardHistory> rewardHistoryList = this.drugDAO.findByProperty(new RewardHistory(), "order.id", order.getId(),
                    Constants.HIBERNATE_EQ_OPERATOR, "createdDate", Constants.HIBERNATE_DESC_ORDER);
            for (RewardHistory reward : rewardHistoryList) {
                if (AppUtil.getSafeStr(reward.getType(), "").equalsIgnoreCase("MINUS")) {
                    reward.setPoint(drugDetail.getRedeemedPoints().intValue());
                    orderDao.saveOrUpdate(reward);
                    break;
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////
    public Order updateOrder(int id, PharmacyUser user, int statusVal, String comments, String rxNumber, String brandName,
            String type, String strength, String qty, String daysSupply, String refillsAllowed,
            String refillUsed, String paymentMode, String patientName, String pharmacyName,
            String pharmacyPhone, String prescriberName, String prescriberPhone,
            String prescriberNPI, String acqCost, String reimb, String ptCopay, String finalPayment, String deliverycarrier, String shipDate,
            String traclingno, String clerk, Order order, String mfrCost, String insuranceType, String priceIncludingMargins,
            String expiredDateStr, Double profitMargin, int profitSharePoint, float actualProfitShare) throws Exception {
        int createdBy = 0;
        if (statusVal == 5 || statusVal == 6 || statusVal == 8 || statusVal == 9 || statusVal == 13 || statusVal == 15) {
            if (AppUtil.getSafeInt(daysSupply, 0) <= 0) {
                throw new Exception("Days supply must be greater than 0.");
            }
            if (order.getRxDate() == null) {
                throw new Exception("Please specify original Rx Date.");
            }
            if (statusVal == 8) {
                //////////////////////////////////////////////////////////////////////
                List<BusinessObject> lstObj = new ArrayList();
                BusinessObject obj = new BusinessObject("systemGeneratedRxNumber", order.getSystemGeneratedRxNumber(),
                        Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("id", order.getId(), Constants.HIBERNATE_NE_OPERATOR);
                lstObj.add(obj);

                List lst = drugDAO.findByNestedProperty(new Order(), lstObj, "", 0);

                /////////////////////////////////////////////////////////////////////
//                List lst= this.consumerRegistrationDAO.findByNestedProperty(new Order(), 
//                        "systemGeneratedRxNumber", order.getSystemGeneratedRxNumber(), 
//                        Constants.HIBERNATE_EQ_OPERATOR, "", 0);
                if (lst != null && lst.size() > 0) {
                    throw new Exception("Processed Rx# must be unique.");
                }
            }
        }
//        Date rxDate = order.getRxDate();
//        String oldRxNumber = order.getOldRxNumber();
//        Double handlingFee = order.getHandLingFee()!=null?order.getHandLingFee():0d;
//        String requiredPatientResponse = order.getResponseRequired();
//        int disabledFld = order.getDisabledFlds() != null ? order.getDisabledFlds() : 0;
        //order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), id + "");
        if (user != null) {

            if (user.getPharmacy() != null) {
//                if (order.getPharmacy() != null
//                        && order.getPharmacy().getId().intValue() != user.getPharmacy().getId().intValue()) {
//                    throw new Exception("This order belongs to some other pharmacy so you can't process it.");
//                }
                order.setPharmacy(user.getPharmacy());
            } else {
                throw new Exception("This user doesn't belong to any pharmacy, so can't proceed with order.");
            }
            createdBy = user.getId();
            order.setUpdatedBy(user.getId());
        }
//        order.setOldRxNumber(oldRxNumber);
//        order.setHandLingFee(handlingFee);
//        order.setResponseRequired(requiredPatientResponse);
//        order.setDisabledFlds(disabledFld);
//        order.setRxDate(rxDate);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            if (statusVal > 0) {
                if (statusVal == 1) {
                    status.setId(17);
                } else if (statusVal == Constants.ORDER_STATUS.HAND_DELIVERED_ID) {
                    status.setId(Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID);
                    order.setDisabledFlds(1);
                    order.setHandDelivery(1);
                    order.setHandDeliveryAccepted(0);
                } else {
                    status.setId(statusVal);
                }
            } else {
                status = order.getOrderStatus();
                if (status.getId() == 1) {
                    status = (OrderStatus) this.consumerRegistrationDAO.findRecordById(new OrderStatus(), 17);
                }
            }
            updateOrderDeliveryDate(status, order);
            order.setOrderStatus(status);
            if (statusVal > 1) {
                order.setViewStatus("Pending");
            }
            if (statusVal == 5 || statusVal == 6 || statusVal == 15) {
                populateOrderDeliveryPreferenceUsed(order);
            }

            getDrugDetail(brandName, type, strength, order, qty);
            if (order.getDrugDetail() == null && statusVal != 11 && statusVal != 16 && statusVal != 19)//status is not cancelled, waiting response or response received
            {
                throw new Exception("Application is unable to get proper data for RX Name,strength & dosage type, please try again.");
            }
           
            order.setRxNumber(rxNumber);
            order.setDaysSupply(AppUtil.getSafeInt(daysSupply, 0));
            order.setRefillsAllowed(AppUtil.getSafeInt(refillsAllowed, 0));
            order.setRefillsRemaining(AppUtil.getSafeInt(refillUsed, 0));
            order.setPaymentMode(AppUtil.getSafeStr(paymentMode, "").equalsIgnoreCase("SELF PAY") ? "Cash" : "INS");
            //order.setPatientName(patientName);
            order.setPharmacyName(pharmacyName);
            order.setPharmacyPhone(pharmacyPhone);
            order.setPrescriberName(prescriberName);
            order.setPrescriberPhone(prescriberPhone);
            order.setPrescriberNPI(prescriberNPI);
            order.setRxAcqCost(AppUtil.getSafeDouble(acqCost, 0d));
            order.setRxReimbCost(AppUtil.getSafeDouble(reimb, 0d));
            order.setProfitMargin(profitMargin);//AppUtil.getSafeDouble(reimb, 0d) - AppUtil.getSafeDouble(acqCost, 0d));
            order.setProfitSharePoint(profitSharePoint);
            order.setActualProfitShare(actualProfitShare);
            order.setOriginalPtCopay(AppUtil.getSafeDouble(ptCopay, 0d));
            order.setQty(qty);
//            getDrugDetail(brandName, type, strength, order, qty);
            order.setFinalPayment(AppUtil.getSafeDouble(finalPayment, 0d));
            double handlingFee = order.getHandLingFee() != null ? order.getHandLingFee() : 0d;
            order.setPaymentExcludingDelivery(AppUtil.getSafeDouble(finalPayment, 0d) - handlingFee);
//            if (statusVal == 6
//                    && AppUtil.getSafeStr(order.getRequiresHandDelivery(), "").equalsIgnoreCase("Y"))//for shipping, an exception will be thrown when drug hand deliver flag is y
//            {
//                throw new Exception("This drug requires hand delivery so instead of Shipping, please click Delivery for this drug.");
//            }
            order.setDeliverycarrier(deliverycarrier);
            if (statusVal == 6 || statusVal == 5 || statusVal == 15) {
                order.setShippedOn(DateUtil.stringToDate(shipDate, Constants.USA_DATE_FORMATE));
            }
            order.setTraclingno(traclingno);
            order.setClerk(clerk);
            order.setInsuranceType(insuranceType);
            if (AppUtil.getSafeStr(insuranceType, "").equalsIgnoreCase("Public")
                    || AppUtil.getSafeStr(insuranceType, "").equalsIgnoreCase("Commercial")) {
                order.setFinalPaymentMode(AppUtil.getSafeStr(insuranceType, ""));
            } else {
                order.setFinalPaymentMode("SELF PAY");
            }
//            if (CommonUtil.isNotEmpty(profitShare)) {
//                order.setProfitShareCost(Double.parseDouble(profitShare));
//            }
            order.setProfitShareCost(0d);
            if (CommonUtil.isNotEmpty(mfrCost)) {
                order.setMfrCost(Double.parseDouble(mfrCost));
            }
            if (CommonUtil.isNotEmpty(priceIncludingMargins)) {
                order.setPriceIncludingMargins(Double.parseDouble(priceIncludingMargins));
            }

            //////////////////////////////////////////////////////////////////
            order.setUpdatedOn(new Date());
            order.setStatusCreatedOn(new Date());
            

            // Apply this check to resolve Optimistic lock exception.
//            if (CommonUtil.isNotEmpty(order.getId())) {
//                Order orderDb = (Order) orderDao.findRecordById(new Order(), order.getId());
//                order.setOlversion(orderDb.getOlversion());
//            }
            orderDao.saveOrUpdate(order);

            saveOrderHistory(order, status, createdBy, comments);
            if (statusVal == 6 && AppUtil.getSafeStr(deliverycarrier, "").toUpperCase().equalsIgnoreCase("IOMNI")) {
//                postOrderToIOMNI(order.getId(), ""+handlingFee);
            }
        }
        return order;
    }

    private void populateOrderDeliveryPreferenceUsed(Order order) {
        if (order.getDeliveryId() > 0) {
            DeliveryPreferences delPref = new DeliveryPreferences();
            delPref.setId(order.getDeliveryId());
//                    order.setDeliveryPreference(delPref);
            order.setDeliveryPreferenceUsed(delPref);
        }
    }

    public void updateOrderDeliveryDate(OrderStatus status, Order order) {
        if (status.getId() == 5 || status.getId() == 6 || status.getId() == 15) {
            order.setDeliveryDate(new Date());
        }
    }

    ///////////////////////////////////////////////////////////////////////
    public int updateOrderStatus(int id, int createdBy, int statusVal, String comments, String rxNumber, String brandName,
            String type, String strength, String qty, String daysSupply, String refillsAllowed,
            String refillUsed, String paymentMode, String patientName, String pharmacyName,
            String pharmacyPhone, String prescriberName, String prescriberPhone,
            String prescriberNPI, String acqCost, String reimb, String ptCopay, String finalPayment, String deliverycarrier,
            String traclingno, String clerk, Order order, String profitShare, String mfrCost, String insuranceType, String priceIncludingMargins) throws Exception {
        order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), id + "");
        if (order != null) {
            OrderStatus status = new OrderStatus();
            if (statusVal > 0) {
                status.setId(statusVal);
            } else {
                status = order.getOrderStatus();
            }
            order.setOrderStatus(status);
            if (statusVal > 1) {
                order.setViewStatus("Pending");
            }
            updateOrderDeliveryDate(status, order);
           
            
           
            order.setRxNumber(rxNumber);
            order.setDaysSupply(AppUtil.getSafeInt(daysSupply, 0));
            order.setRefillsAllowed(AppUtil.getSafeInt(refillsAllowed, 0));
            order.setRefillsRemaining(AppUtil.getSafeInt(refillUsed, 0));
            order.setPaymentMode(paymentMode);
            //order.setPatientName(patientName);
            order.setPharmacyName(pharmacyName);
            order.setPharmacyPhone(pharmacyPhone);
            order.setPrescriberName(prescriberName);
            order.setPrescriberPhone(prescriberPhone);
            order.setPrescriberNPI(prescriberNPI);
            order.setRxAcqCost(AppUtil.getSafeDouble(acqCost, 0d));
            order.setRxReimbCost(AppUtil.getSafeDouble(reimb, 0d));
            order.setProfitMargin(AppUtil.getSafeDouble(reimb, 0d) - AppUtil.getSafeDouble(acqCost, 0d));
            order.setOriginalPtCopay(AppUtil.getSafeDouble(ptCopay, 0d));
            order.setQty(qty);
            getDrugDetail(brandName, type, strength, order, qty);
            order.setFinalPayment(AppUtil.getSafeDouble(finalPayment, 0d));
            if (statusVal == 6
                    && AppUtil.getSafeStr(order.getRequiresHandDelivery(), "").equalsIgnoreCase("Y"))//for shipping, an exception will be thrown when drug hand deliver flag is y
            {
                throw new Exception("This drug requires hand delivery so instead of Shipping, please click Delivery for this drug.");
            }
            order.setDeliverycarrier(deliverycarrier);
            order.setTraclingno(traclingno);
            order.setClerk(clerk);
            order.setInsuranceType(insuranceType);
            if (CommonUtil.isNotEmpty(profitShare)) {
                order.setProfitShareCost(Double.parseDouble(profitShare));
            }
            if (CommonUtil.isNotEmpty(mfrCost)) {
                order.setMfrCost(Double.parseDouble(mfrCost));
            }
            if (CommonUtil.isNotEmpty(priceIncludingMargins)) {
                order.setPriceIncludingMargins(Double.parseDouble(priceIncludingMargins));
            }
            //////////////////////////////////////////////////////////////////
            orderDao.saveOrUpdate(order);
            saveOrderHistory(order, status, createdBy, comments);
        }
        return 1;
    }

    public int updateOrderStatusAndHisotry(String id, int createdBy, int statusVal, String requiredResponse, String patientResponse, Integer pharmacyId) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        if (order != null) {
            OrderStatus status = order.getOrderStatus();
            if (statusVal > 0) {
                status = new OrderStatus();
                status.setId(statusVal);
                order.setOrderStatus(status);
                if (statusVal == 2) {
                    order.setViewStatus(Constants.ORDER_STATUS.PENDING);
                }
                updateOrderDeliveryDate(status, order);
                if (statusVal == 8) {
                    
                   
                    ///////////////////////////////////////////////////////
                }
            }
            populatePharmacy(pharmacyId, order);
            order.setUpdatedBy(createdBy);
            order.setUpdatedOn(new Date());
            order.setStatusCreatedOn(new Date());
            order.setResponseRequired(requiredResponse);
            order.setPatientResponse(patientResponse);
            orderDao.saveOrUpdate(order);
            OrderHistory history = new OrderHistory();
            history.setOrder(order);
            history.setResponseRequiredLog(requiredResponse);
            history.setPatientResponseLog(patientResponse);
            history.setOrderStatus(status);
            history.setCreatedBy(createdBy);
            history.setUpdatedBy(createdBy);
            history.setCreatedOn(new Date());
            history.setUpdatedOn(new Date());
            orderDao.saveOrUpdate(history);
        }
        return 1;
    }

    ///////////////////////////////////////////////////////////////////////
    public void saveOrderHistory(Order order, OrderStatus status, int createdBy, String comments) throws Exception {
        OrderHistory history = new OrderHistory();
        history.setOrder(order);
        history.setResponseRequiredLog(order.getResponseRequired());
        history.setOrderStatus(status);
        history.setCreatedBy(createdBy);
        history.setUpdatedBy(createdBy);
        history.setComments(comments);
        history.setCreatedOn(new Date());
        history.setUpdatedOn(new Date());
        orderDao.saveOrUpdate(history);
    }

    private DrugDetail searchDrugByDrugTypeAndStrength(String drugName, String a_drugType, String drugStrength, Order o) {

        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
//                drugStrength=drugStrength.replace(String.valueOf((char) 160), " ");
//                drugStrength=drugStrength.replace(String.valueOf((char) 65533), " ");
            String[] arr = AppUtil.getBrandAndGenericFromDrugName(drugName);
            String brand = drugName;
            String generic = "";
            if (arr != null) {
                if (arr.length == 1) {
                    brand = arr[0];
                } else {
                    brand = arr[0];
                    generic = arr[1];
                }
            }
            String strengthWithoutSpace = drugStrength;
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("drugBasic.brandName", brand, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            if (AppUtil.getSafeStr(generic, "").length() > 0) {
                obj = new BusinessObject("drugBasic.drugGeneric.genericName", generic, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
            } else {
                obj = new BusinessObject("drugBasic.brandIndicator", "BRAND", Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
            }
            ////////////////////////////////////////////////////////////////////////////
            obj = new BusinessObject("strength", drugStrength, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            obj = new BusinessObject("drugForm.formDescr", a_drugType, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);

            /////////////////////////////////////////////////////////////////////////////
//            List<DrugDetail> drugDetails = patientProfileDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
//            List<DrugDetail> lst_drugBrand = drugDAO.searchDrugBasicPrice(drugName, a_drugType, drugStrength);
            List<DrugDetail> lst_drugBrand = drugDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
            if (lst_drugBrand != null && lst_drugBrand.size() > 0) {
                DrugDetail drugDetail = lst_drugBrand.get(0);
                o.setDrugName(drugName);
                o.setStrength(strengthWithoutSpace);
                o.setDrugType(a_drugType);
                return drugDetail;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    //////////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileOrder(int patientId, String orderId, String orderStatus) throws Exception {
        try {
            List list = consumerRegistrationDAO.getTranferRxQueueByPatientProfileOrder2(patientId, orderId, orderStatus);
            List<TransferRxQueueDTO> transferRxQueueList = this.populateTransferDTOFromTransferRxLevel2Queue(list, patientId);
            return transferRxQueueList;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();;
            logger.error("Exception: ConsumerRegistrationService -> getTranferRxQueueByPatientProfileOrder", e);
        }
        return null;
    }

    //////////////////////////////////////////////////
    private List<TransferRxQueueDTO> populateTransferDTOFromTransferRxLevel2Queue(List<Object[]> list, int id) {
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();

        for (Object[] arr : list) {
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            PatientProfile patientProfile = (PatientProfile) arr[0];
            Order orders = (Order) arr[1];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
            if (orders.getQty() != null && !orders.getQty().trim().equals("")) {
                transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
            }
            transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
            transferRxQueueDTO.setQouted(orders.getDrugPrice() + "");
            transferRxQueueDTO.setOrderId(orders.getId());
            /////////////////////////////////////////////////////
            transferRxQueueDTO.setRxNumber(AppUtil.getSafeStr(orders.getRxNumber(), "").length() > 0
                    ? AppUtil.getSafeStr(orders.getRxNumber(), "") : orders.getId());
            transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
            transferRxQueueDTO.setPharmacyName(orders.getPharmacyName());
            transferRxQueueDTO.setPharmacyPhone(orders.getPharmacyPhone());
            ///////////////////////////////////////////////////

//            if (orders.getTransferDetail() != null) {
//                transferRxQueueDTO.setRxNumber(orders.getTransferDetail().getRxNumber());
//                transferRxQueueDTO.setRxSearched(
//                        orders.getTransferDetail().getDrugName() + " " + orders.getTransferDetail().getStrength());
//                transferRxQueueDTO.setPharmacyName(orders.getTransferDetail().getPharmacyName());
//                transferRxQueueDTO.setPharmacyPhone(orders.getTransferDetail().getPharmacyPhone());
//            } else {
//                transferRxQueueDTO.setRxNumber(orders.getRxNo());
//                transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
//            }
            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());

            transferRxQueueDTO.setStatus(orders.getOrderStatus() != null ? orders.getOrderStatus().getName() : "Pending");//("Pending");
            transferRxQueueDTO.setDisabled(transferRxQueueDTO.getStatus().equals("Pending") ? "0" : "1");
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setOrderType(orders.getOrderType() == null || orders.getOrderType().trim().equals("") ? "" : orders.getOrderType());
            //transferRxQueueDTO.setPtVideo(AppUtil.getSafeStr(orders.getVideo(),""));
            try {
                String videoStr = AppUtil.getSafeStr(orders.getVideo(), "").replace("localhost", "rxdirectdev.ssasoft.com");
                if (videoStr.length() > 0 && videoStr.indexOf("http://") < 0) {
                    videoStr = "http://" + videoStr;
                }
                transferRxQueueDTO.setPtVideo(videoStr);
                ////////////////////////////////////////////////////////////
                String imgStr = AppUtil.getSafeStr(orders.getImagePath(), "").replace("localhost", "rxdirectdev.ssasoft.com");
                if (imgStr.length() > 0 && imgStr.indexOf("http://") < 0) {
                    imgStr = "http://" + imgStr;
                }
                transferRxQueueDTO.setTransferImage(imgStr);
                ////////////////////////////////////////////////////////////
            } catch (Exception e) {
            }

//            if (orders.getTransferDetail() != null && orders.getTransferDetail().getId() != null) {
//                TransferRequest transferRequest = this.consumerRegistrationDAO.getPTVideoFromTransferRequest(orders);
//                if (transferRequest != null) {
//                    transferRxQueueDTO.setPtVideo((transferRequest.getVideo() == null || transferRequest.getVideo().trim().equals("")) ? "" : transferRequest.getVideo());
//                } else {
//                    transferRxQueueDTO.setPtVideo("");
//                }
//
//            }
            transferRxQueueList.add(transferRxQueueDTO);

        }
        return transferRxQueueList;
    }

    ////////////////////////////////////////////////
    public String geNextOrderByPatientProfileOrder(int patientId, String orderId, Date createdDate,
            String orderStatus, int index, int pharmacyId) throws Exception {
//        List<OrderDetailDTO> list = orderDao.getOrderBatchsList(patientId, orderId);
//        if (!CommonUtil.isNullOrEmpty(list)) {
//            return list.stream().findFirst().get().getId();
//        } else {
        List<Order> lst = this.consumerRegistrationDAO.geNextOrderByPatientProfileOrder(patientId, orderId,
                createdDate, orderStatus, pharmacyId);
        if (lst != null && lst.size() > 0) {
            if (index < lst.size()) {
                return lst.get(index).getId();
            }
        }
//        }
        return "0";
    }

    /////////////////////////////////////////////////////////////////////////
    public OrderDetailDTO geNextPatientProfile(int patientId, String orderId, Date createdDate,
            String orderStatus, int index, int pharmacyId) throws Exception {
        List<Order> lst = this.consumerRegistrationDAO.geNexPatientProfile(patientId, orderId,
                createdDate, orderStatus, pharmacyId);
        OrderDetailDTO dto = new OrderDetailDTO();
        if (lst != null && lst.size() > 0) {
            if (index < lst.size()) {

                dto.setPatientId("" + lst.get(index).getPatientProfile().getPatientProfileSeqNo());
                dto.setId(lst.get(index).getId());
//                return lst.get(index).getPatientProfile().getId().toString();
            }
//            return lst.get(0).getId();

        }
        return dto;
    }

    ////////////////////////////////////////////////
    public String getPrevOrderByPatientProfileOrder(int patientId, String orderId, Date createdDate,
            String orderStatus, int index) throws Exception {
        List<Order> lst = this.consumerRegistrationDAO.gePrevOrderByPatientProfileOrder(patientId, orderId,
                createdDate, orderStatus);
        if (lst != null && lst.size() > 0) {
            if (index < lst.size()) {
                return lst.get(index).getId();
            }
            return lst.get(0).getId();

        }
        return "0";
    }

    public int updatePaymentCard(String paymentId, String orderId, String orderStatus, String patientResponse) throws Exception {
        Order order = this.orderDao.getOrdersById("" + orderId);
        if (order != null) {
            order.setPaymentId(AppUtil.getSafeInt(paymentId, 0));
            setPatientResponseAttributesForOrder(order, patientResponse);
            orderDao.saveOrUpdate(order);
        }
        return 1;
    }

    ////////////////////////////////////////////////
    public PharmacyUser getPharmacyUserById(Integer id) {
        PharmacyUser pharmacyUser = new PharmacyUser();
        try {
            pharmacyUser = consumerRegistrationDAO.getPharmacyUserById(id);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService:: getPharmacyUserById", e);
        }
        return pharmacyUser;
    }

    public int updatePrescriber(String orderId, String prescriberName, String prescriberPhone, String prescriberNPI) throws Exception {
        Order order = this.orderDao.getOrdersById("" + orderId);
        if (order != null) {
            //  order.setPaymentId(AppUtil.getSafeInt(prescriberName,0));
            order.setPrescriberName("");
            order.setPrescriberLastName(prescriberName);
            order.setPrescriberPhone(prescriberPhone);
            order.setPrescriberNPI(prescriberNPI);
            order.setUpdatedOn(new Date());
//            order.setPatientResponse(Constants.PATIENT_RESPONSES.UPDATE_PRESCRIBER);
//            order.setResponseFullFilled("0");
            setPatientResponseAttributesForOrder(order, Constants.PATIENT_RESPONSES.UPDATE_PRESCRIBER);
            orderDao.saveOrUpdate(order);
//          Below Line is method call from same class in this method i restAutoDeletionValues 
            resetAutoDeletionValues(orderId, 1);
        }
        return 1;
    }

    ////////////////////////////////////////////////////////
    public int savePatientResponseAttributesForOrder(String orderId, String patientResponse) throws Exception {
        Order order = this.orderDao.getOrdersById(orderId);
        if (order != null) {
            setPatientResponseAttributesForOrder(order, patientResponse);
            orderDao.saveOrUpdate(order);
            return 1;
        }
        return 0;
    }

    public void setPatientResponseAttributesForOrder(Order order, String patientResponse) {
        if (order != null) {
            order.setPatientResponse(patientResponse);
            order.setResponseFullFilled("0");
            OrderStatus status = new OrderStatus();
            status.setId(11);
            order.setOrderStatus(status);
        }
//        return order;
    }
    ////////////////////////////////////////////////////////

//    This method Re-set values in order table (Purpose => If User update/reply to pharmacy with prescription then we will reset value 
//    othervise his order will be Delete after 2 days from queue) we are sending call this method in updatePrescriber method 
    public int resetAutoDeletionValues(String orderId, Integer status) throws Exception {
        Order order = this.orderDao.getOrdersById("" + orderId);

        if (order != null) {
            OrderStatus orderStatus = new OrderStatus();
            orderStatus = (OrderStatus) orderDao.findRecordById(orderStatus, status);
            order.setOrderStatus(orderStatus);
            order.setAutoDeletionDate(null);
            order.setAutoDeletionFlag(0);

            orderDao.saveOrUpdate(order);
        }
        return 1;

    }

    public int fillAsCash(int Id, int createdBy, int statusVal, String comments, String paymentMode) throws Exception {
        Order order = this.orderDao.getOrdersById("" + Id);
        if (order != null) {
            OrderStatus status = new OrderStatus();
            if (statusVal > 0) {
                status.setId(statusVal);
            } else {
                status = order.getOrderStatus();
            }
            if (statusVal > 1) {
                order.setOrderStatus(status);
                order.setPaymentMode("Cash");
            }

//         status = order.getOrderStatus();
//          order.setOrderStatus(status);
//          
            orderDao.saveOrUpdate(order);

        }
        return 1;
    }

    public String populateDeliveryPreferenceAgainstSpecificOrder(String orderId) {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
        return order.getDeliveryPreference() != null ? order.getDeliveryPreference().getName() : "N/A";
    }

    public String[] populateDeliveryPreferenceArrAgainstSpecificOrder(String orderId) {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
        String[] arr = new String[2];
        arr[0] = order.getDeliveryPreference() != null ? order.getDeliveryPreference().getName() : "N/A";
        arr[1] = order.getDeliveryPreferenceUsed() != null ? order.getDeliveryPreferenceUsed().getName() : "N/A";
        return arr;//order.getDeliveryPreference() != null ? order.getDeliveryPreference().getName() : "N/A";
    }

    public boolean updateDrugDetail(DrugDetailDTO drugDetailDTO, Integer currentUserId) {
        boolean isUpdate = false;
        try {
            DrugDetail drugDetail = (DrugDetail) consumerRegistrationDAO.findByPropertyUnique(new DrugDetail(), "drugDetailId", drugDetailDTO.getDrugDetailId(), Constants.HIBERNATE_EQ_OPERATOR);
            if (drugDetail != null && drugDetail.getDrugDetailId() != null) {
                populateDrugDetail(drugDetail, drugDetailDTO);

                DrugBasic drugBasic = addUpdateDrugBasic(drugDetailDTO, currentUserId);
//                DrugBasic drugBasic = drugDetail.getDrugBasic();
//                if (drugBasic != null && drugBasic.getDrugBasicId() != null) {
//                    drugBasic.setBrandName(drugDetailDTO.getBrandName());
//                    DrugGeneric drugGeneric = drugBasic.getDrugGeneric();
//                    drugGeneric.setGenericName(drugDetailDTO.getGenericName());
//                    drugGeneric.setTherapeuticClass(drugDetailDTO.getTherapy());
//                    drugBasic.setDrugGeneric(drugGeneric);
//                    drugDetail.setDrugBasic(drugBasic);
//                }
                drugDetail.setDrugBasic(drugBasic);
                addUpdateDrugForm(drugDetailDTO, currentUserId, drugDetail);
//                DrugForm drugForm = drugDetail.getDrugForm();
//                if (drugForm != null && drugForm.getDrugFormId() != null) {
//                    drugForm.setFormDescr(drugDetailDTO.getFormDesc());
//                    drugDetail.setDrugForm(drugForm);
//                }
                consumerRegistrationDAO.update(drugDetail);
                isUpdate = Boolean.TRUE;
            }
        } catch (Exception e) {
            isUpdate = false;
            logger.error("Exception:: updateDrugDetail", e);
        }
        return isUpdate;
    }

    public void populateDrugDetail(DrugDetail drugDetail, DrugDetailDTO drugDetailDTO) {
        drugDetail.setDrugGCN(drugDetailDTO.getDrugGCN());
        drugDetail.setDrugGPI(drugDetailDTO.getDrugGPI());
        drugDetail.setDemandBrand(drugDetailDTO.getDemandBrand());
        drugDetail.setInStock(drugDetailDTO.getInStock());
        drugDetail.setGnn(drugDetailDTO.getGnn());
        drugDetail.setStrength(drugDetailDTO.getStrength());
        drugDetail.setPackingDesc(drugDetailDTO.getPackingDesc());
        drugDetail.setDefQty(drugDetailDTO.getDefQty());
        drugDetail.setPackageSizeValues(drugDetailDTO.getPackageSizeValues());
        populateDrugDetailAttachment(drugDetailDTO, drugDetail);
        drugDetail.setRequiresHandDelivery(drugDetailDTO.getRequiresHandDelivery());
        drugDetail.setMonthRxExpiration(drugDetailDTO.getRxExpire());
        drugDetail.setMarginPercent(drugDetailDTO.getMarginPercent());
        drugDetail.setBasePrice(drugDetailDTO.getBasePrice());
        drugDetail.setAdditionalFee(drugDetail.getAdditionalFee());
        drugDetail.setMktSurcharge(drugDetailDTO.getMktSurcharge());
    }

    public void populateDrugDetailAttachment(DrugDetailDTO drugDetailDTO, DrugDetail drugDetail) {
        if (CommonUtil.isNotEmpty(drugDetailDTO.getDrugDocUrl())) {
            drugDetail.setDrugDocUpdatedOn(new Date());
            drugDetail.setDrugDoc("1");
            drugDetail.setDrugDocUrl(drugDetailDTO.getDrugDocUrl());
        }
        if (CommonUtil.isNotEmpty(drugDetailDTO.getPdfDocUrl())) {
            drugDetail.setPdfDocUpdatedOn(new Date());
            drugDetail.setPdf("1");
            drugDetail.setPdfDocUrl(drugDetailDTO.getPdfDocUrl());
        }
        if (CommonUtil.isNotEmpty(drugDetailDTO.getImage())) {
            drugDetail.setImgUpdatedOn(new Date());
            drugDetail.setImage("1");
            drugDetail.setImgUrl(drugDetailDTO.getImage());
        }
    }

    public boolean addDrugDetail(DrugDetailDTO drugDetailDTO, Integer currentUserId) {
        boolean isSaved = false;
        try {
            DrugDetail drugDetail = new DrugDetail();
            populateDrugDetail(drugDetail, drugDetailDTO);
            DrugDetail detail = (DrugDetail) drugDAO.findByPropertyUnique(new DrugDetail(), "drugGCN", drugDetailDTO.getDrugGCN(), Constants.HIBERNATE_EQ_OPERATOR);
            if (detail != null && detail.getDrugDetailId() != null) {
                if (CommonUtil.isNotEmpty(detail.getDrugDocUrl())) {
                    drugDetail.setDrugDocUpdatedOn(new Date());
                    drugDetail.setDrugDoc("1");
                    drugDetail.setDrugDocUrl(detail.getDrugDocUrl());
                }
                if (CommonUtil.isNotEmpty(detail.getPdfDocUrl())) {
                    drugDetail.setPdfDocUpdatedOn(new Date());
                    drugDetail.setPdf("1");
                    drugDetail.setPdfDocUrl(detail.getPdfDocUrl());
                }
                if (CommonUtil.isNotEmpty(detail.getImage())) {
                    drugDetail.setImgUpdatedOn(new Date());
                    drugDetail.setImage("1");
                    drugDetail.setImgUrl(detail.getImage());
                }
            } else if (!CommonUtil.isNullOrEmpty(drugDetailDTO.getDrugDetailId())) {
                String fileName = "", url = "";
                detail = (DrugDetail) consumerRegistrationDAO.findByPropertyUnique(new DrugDetail(), "drugDetailId", drugDetailDTO.getDrugDetailId(), Constants.HIBERNATE_EQ_OPERATOR);
                if (CommonUtil.isNotEmpty(detail.getPdfDocUrl()) && CommonUtil.isNullOrEmpty(drugDetailDTO.getPdfDocUrl())) {
                    url = CommonUtil.getDocumentDestinationPath("1") + detail.getDrugGCN() + Constants.FILE_EXTENSION.PDF;
                    fileName = transferFileToServer(url, drugDetailDTO, "1", Constants.FILE_EXTENSION.PDF);
                    drugDetail.setPdfDocUpdatedOn(new Date());
                    drugDetail.setPdf("1");
                    drugDetail.setPdfDocUrl(fileName);
                }
                if (CommonUtil.isNotEmpty(detail.getDrugDocUrl()) && CommonUtil.isNullOrEmpty(drugDetailDTO.getDrugDocUrl())) {
                    url = CommonUtil.getDocumentDestinationPath("2") + detail.getDrugGCN() + Constants.FILE_EXTENSION.PDF;
                    fileName = transferFileToServer(url, drugDetailDTO, "2", Constants.FILE_EXTENSION.PDF);
                    drugDetail.setDrugDocUpdatedOn(new Date());
                    drugDetail.setDrugDoc("1");
                    drugDetail.setDrugDocUrl(fileName);
                }
                if (CommonUtil.isNotEmpty(detail.getImgUrl()) && CommonUtil.isNullOrEmpty(drugDetailDTO.getImage())) {
                    url = CommonUtil.getDocumentDestinationPath("3") + detail.getDrugGCN() + Constants.FILE_EXTENSION.PNG;
                    fileName = transferFileToServer(url, drugDetailDTO, "3", Constants.FILE_EXTENSION.PNG);
                    drugDetail.setImgUpdatedOn(new Date());
                    drugDetail.setImage("1");
                    drugDetail.setImgUrl(fileName);
                }
            }
            drugDetail.setDrugNDC(0L);
            addUpdateDrugForm(drugDetailDTO, currentUserId, drugDetail);
            DrugBasic drugBasic = addUpdateDrugBasic(drugDetailDTO, currentUserId);
            drugDetail.setDrugBasic(drugBasic);
            drugDetail.setArchived("N");
            drugDetail.setCreatedOn(new Date());
            drugDetail.setUpdatedOn(new Date());
            drugDAO.save(drugDetail);
            isSaved = true;
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception:: addDrugDetail", e);
        }
        return isSaved;
    }

    public void addUpdateDrugForm(DrugDetailDTO drugDetailDTO, Integer currentUserId, DrugDetail drugDetail) throws Exception {
        DrugForm drugForm = (DrugForm) drugDAO.findByPropertyUnique(new DrugForm(), "formDescr", drugDetailDTO.getFormDesc(), Constants.HIBERNATE_EQ_OPERATOR);
        if (drugForm == null) {
            drugForm = new DrugForm();
            drugForm.setFormDescr(drugDetailDTO.getFormDesc());
            drugForm.setCreatedBy(currentUserId);
            drugForm.setCreatedOn(new Date());
            drugForm.setUpdatedBy(currentUserId);
            drugForm.setUpdatedOn(new Date());
            drugDAO.save(drugForm);
            drugDetail.setDrugForm(drugForm);
        } else {
            drugForm.setUpdatedBy(currentUserId);
            drugForm.setUpdatedOn(new Date());
            drugDetail.setDrugForm(drugForm);
        }
    }

    public DrugBasic addUpdateDrugBasic(DrugDetailDTO drugDetailDTO, Integer currentUserId) throws Exception {
        DrugBasic drugBasic = (DrugBasic) drugDAO.findByPropertyUnique(new DrugBasic(), "brandName", AppUtil.getSafeStr(drugDetailDTO.getBrandName(), ""), Constants.HIBERNATE_EQ_OPERATOR);
        if (drugBasic == null) {
            drugBasic = new DrugBasic();
            drugBasic.setBrandName(drugDetailDTO.getBrandName());
            drugBasic.setBrandIndicator(drugDetailDTO.getDemandBrand());
            drugBasic.setTherapeuticCategory(drugDetailDTO.getTherapy());
            drugBasic.setCreatedOn(new Date());
            drugBasic.setUpdatedOn(new Date());
            drugBasic.setCreatedBy(currentUserId);
            drugBasic.setUpdatedBy(currentUserId);
        } else {
            drugBasic.setUpdatedBy(currentUserId);
            drugBasic.setUpdatedOn(new Date());
        }
        DrugGeneric drugGeneric = (DrugGeneric) drugDAO.findByPropertyUnique(new DrugGeneric(), "genericName", AppUtil.getSafeStr(drugDetailDTO.getGenericName(), ""), Constants.HIBERNATE_EQ_OPERATOR);
        if (drugGeneric == null) {
            drugGeneric = new DrugGeneric();
            if (drugDetailDTO.getGenericName().contains("*BRAND NAME ONLY*")) {
                drugGeneric.setBrandNameOnly(1);
            } else {
                drugGeneric.setBrandNameOnly(0);
            }
            drugGeneric.setGenericName(drugDetailDTO.getGenericName());
            //drugGeneric.setTherapeuticClass(drugDetailDTO.getTherapy());
            drugGeneric.setCreatedBy(currentUserId);
            drugGeneric.setCreatedOn(new Date());
            drugGeneric.setUpdatedBy(currentUserId);
            drugGeneric.setUpdatedOn(new Date());
            drugDAO.save(drugGeneric);
            drugBasic.setDrugGeneric(drugGeneric);
        } else {
            drugGeneric.setUpdatedBy(currentUserId);
            drugGeneric.setUpdatedOn(new Date());
            drugBasic.setDrugGeneric(drugGeneric);
        }
        drugDAO.saveOrUpdate(drugBasic);
        return drugBasic;
    }

    public String transferFileToServer(String url, DrugDetailDTO drugDetailDTO, String destPath, String ext) throws IOException {
        File source = new File(url);
        File dest = new File(CommonUtil.getDocumentDestinationPath(destPath) + drugDetailDTO.getDrugGCN() + ext);
        FileUtils.copyFile(source, dest);
        String path = CommonUtil.getDocumentPath(destPath);
        CommonUtil.executeCommand(Constants.COMMAND + path);
        return Constants.INSURANCE_CARD_URL + path + drugDetailDTO.getDrugGCN() + ext;
    }

    public boolean isDrugDetailExist(DrugDetailDTO drugDetailDTO) {
        boolean isExist = false;
        try {
            List<BusinessObject> list = new ArrayList<>();
            list.add(new BusinessObject("drugGCN", drugDetailDTO.getDrugGCN(), Constants.HIBERNATE_EQ_OPERATOR));
            list.add(new BusinessObject("demandBrand", drugDetailDTO.getDemandBrand(), Constants.HIBERNATE_EQ_OPERATOR));
            list.add(new BusinessObject("drugBasic.drugGeneric.genericName", drugDetailDTO.getGenericName(), Constants.HIBERNATE_EQ_OPERATOR));
            list.add(new BusinessObject("drugBasic.brandName", drugDetailDTO.getBrandName(), Constants.HIBERNATE_EQ_OPERATOR));
            List<DrugDetail> drugDetailsList = drugDAO.findByNestedProperty(new DrugDetail(), list, "", 0);
            if (!CommonUtil.isNullOrEmpty(drugDetailsList)) {
                isExist = true;
            }
        } catch (Exception e) {
            isExist = true;
            logger.error("Exception:: isDrugDetailExist", e);
        }
        return isExist;
    }

    public Map populateOrderListForFinancialReport(String frmDateStr, String toDateStr,
            int pharmacyId) throws ParseException, Exception {
        Map map = new HashMap();
        Pharmacy pharmacy = (Pharmacy) this.consumerRegistrationDAO.findRecordById(new Pharmacy(), pharmacyId);
        Date frmDate = DateUtil.stringToDate(frmDateStr, "MM/dd/yyyy");
        Date toDate = DateUtil.stringToDate(toDateStr, "MM/dd/yyyy");
        List<Order> lst = this.orderDao.populateOrderListForFinancialReport(frmDate, toDate, "5,6,8,15", pharmacyId);
        List dtoLst = new ArrayList();
        double totalRemit = prepareFinancialReportData(lst, dtoLst, pharmacy);
        map.put("list", dtoLst);
        map.put("total", totalRemit);
        map.put("address", pharmacy != null ? AppUtil.getSafeStr(pharmacy.getAddress1(), "-") : "");
        map.put("zip", pharmacy != null && pharmacy.getZip() != null ? pharmacy.getZip() : 0L);
        map.put("contact", pharmacy.getContactPerson());
        return map;
    }

    public double prepareFinancialReportData(List<Order> lst, List dtoLst, Pharmacy pharmacy) throws Exception {

        String abbr = pharmacy != null ? AppUtil.getSafeStr(pharmacy.getAbbr(), "") + "-" : "";
        if (dtoLst == null) {
            dtoLst = new ArrayList();
        }
        double totalRemit = 0d;
        int count = 1;
        for (Order order : lst) {
            OrderDetailDTO dto = new OrderDetailDTO();
//            dto.setTransactionNumber(count);
            dto.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
            dto.setFilledDate(order.getFilledDate());
            dto.setId(order.getId());
//            dto.setRequestType(order.getOrderType());
            /////////////////////////////////////////////////////
            String requestType = AppUtil.getSafeStr(order.getOrderType(), "");
            if (!requestType.equalsIgnoreCase("Refill")) {
                if (order.getIsBottleAvailable() != null && order.getIsBottleAvailable()) {
                    requestType = "X-FER LABEL SCAN";

                } else {
                    requestType = "X-FER RX SCAN";
                }

            }
            dto.setRequestType(requestType);
            //////////////////////////////////////////////////////
            dto.setRxNumber(abbr + order.getSystemGeneratedRxNumber());
//            dto.setGcn(order.getDrugDetail().getDrugGCN() != null ? order.getDrugDetail().getDrugGCN().toString() : "");
            dto.setDrugName(AppUtil.getOneStringFromBrandAndGeneric(
                    order.getDrugDetail().getDrugBasic().getBrandName(),
                    order.getDrugDetail().getDrugBasic().getDrugGeneric().getGenericName(),
                    order.getDrugDetail().getDrugBasic().getDrugGeneric().getBrandNameOnly()));
            dto.setDrugType(order.getDrugDetail().getDrugForm().getFormDescr());
            dto.setStrength(order.getDrugDetail().getStrength());
            dto.setQty(order.getQty());
//            dto.setInsuranceType(AppUtil.getSafeStr(order.getInsuranceType(), "").equalsIgnoreCase("cash") ? "Self Pay" : AppUtil.getSafeStr(order.getInsuranceType(), ""));
//            dto.setInsuranceType(AppUtil.getInsuranceTypeText(order.getFinalPaymentMode()));
//            dto.setAcqCost(order.getRxAcqCost() != null ? order.getRxAcqCost() : 0d);
            //dto.setReimbursementCost(order.getRxReimbCost()!=null?order.getRxReimbCost():0d);
//            dto.setDeliveryPreferencesName(order.getDeliveryPreference() != null
//                    ? order.getDeliveryPreference().getName() : "");
//            dto.setDeliveryFee(order.getHandLingFee() != null ? order.getHandLingFee() : 0d);
//            dto.setSharePoints(order.getProfitSharePoint() != null ? order.getProfitSharePoint() : 0);
//            dto.setSharePointsCost(order.getActualProfitShare() != null
//                    ? AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(order.getActualProfitShare()), 0d) : 0d);
//            dto.setOriginalPtCopay(order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d);
            dto.setFinalPayment(order.getFinalPayment());
//            dto.setSellingPrice(order.getPriceIncludingMargins() != null ? order.getPriceIncludingMargins() : 0d);
//            dto.setInsuranceType(AppUtil.getSafeStr(order.getInsuranceType(),"").equalsIgnoreCase("cash")?"Self Pay":AppUtil.getSafeStr(order.getInsuranceType(),""));
            double imbCost = order.getRxReimbCost() != null ? order.getRxReimbCost() : 0d; // dto.getSellingPrice() >= dto.getOriginalPtCopay()
            //? dto.getSellingPrice() - dto.getOriginalPtCopay() : 0d;
//            dto.setReimbursementCost(AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(imbCost), 0d));
//            dto.setSellingPrice(imbCost + dto.getOriginalPtCopay());
            double finalPayment = dto.getFinalPayment() != null ? dto.getFinalPayment() : 0d;
            Double imvFee = finalPayment * Constants.IMV_FEE_PERCENT / 100;
            imvFee = AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(imvFee), 0d);
//            dto.setImvFee(imvFee.intValue() > Constants.MIN_IMV_VALUE ? imvFee : Constants.MIN_IMV_VALUE);
//            double pharmacyPrfoit = (dto.getDeliveryFee() + dto.getSellingPrice()) - (dto.getAcqCost() + dto.getImvFee());
//            dto.setPharmacyProfit(AppUtil.getSafeDouble(
//                    AppUtil.roundOffNumberToTwoDecimalPlaces(pharmacyPrfoit), 0d));
//           / double remitPharmacy = dto.getFinalPayment() - dto.getImvFee();
//            dto.setRemitPharmacy(AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(remitPharmacy), 0d));
//            totalRemit = totalRemit + dto.getRemitPharmacy();
            dtoLst.add(dto);
            count++;
        }
        return totalRemit;
    }
   

    public List<TransferRxQueueDTO> getTranferRxQueueForPage2(int id, int patientId, List<String> orderStatusList) {
        //List<TransferRxQueueDTO> trq = new ArrayList<TransferRxQueueDTO>();
        try {

            List<Object[]> list = consumerRegistrationDAO.getStatuswiseOrders(orderStatusList, patientId);
            return populateTransferDTOForPage2(list, id);
        } catch (Exception e) {
            logger.error("Exception:: ConsumerRegistrationService:: getTranferRxQueueForPage2", e);
            e.printStackTrace();
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getOrdersListByDate(int id, BaseDTO baseDTO) {
        //List<TransferRxQueueDTO> trq = new ArrayList<TransferRxQueueDTO>();
        try {

            Date date = new Date();
            Date expiringSoon = DateUtil.addDaysToDate(date, AppUtil.getSafeInt(
                    PropertiesUtil.getProperty("EXPIRING_SOON_DAYS"), 45));
            if (baseDTO != null) {
                baseDTO.setExpiringSoonDate(expiringSoon);
            }
            List<Object[]> list = consumerRegistrationDAO.getOrdersListByDate(baseDTO);
            return populateTransferDTOForPage2(list, id);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationService -> getOrdersListByDate", e);
            e.printStackTrace();
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public List<OrderDetailDTO> getMultiRxOrdersStatusWiseByPatientId(String orderId, Integer pharmacyId) throws Exception {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
        if (order != null) {
            List<Order> orders = null;
            if (order.getOrderStatus().getId() == Constants.ORDER_STATUS.SHIPPED_ID || order.getOrderStatus().getId() == Constants.ORDER_STATUS.READY_TO_DELIVER_ID) {
                orders = orderDao.getMultiRxOrders(order.getPatientProfile().getPatientProfileSeqNo(),
                        order.getOrderStatus().getId(), order.getReadyToDeliverRxOrders().getId(), order.getDeliveryPreference().getId(), pharmacyId, order.getId());
                return populateOrderDetailDTO(orders, 0);
            }
            
        }
        return null;
    }

    /////////////////////////////////////////////////////////////////
    public List<OrderDetailDTO> getOrdersWithSamePref(String orderId, Integer pharmacyId, int[] arr)
            throws Exception {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
//        List<Integer> lst1=new ArrayList<>();
//        lst1=CommonUtil.extractIdsListFromCommaSeparatedString2(Constants.processedOrderIds, lst1);
        List<Integer> lstStatus = CommonUtil.extractStatusIdsFrmArray(arr);
        DeliveryPreferences pref = order.getDeliveryPreference();
        int prefId = pref != null && pref.getId() != null ? pref.getId() : 0;

        
        return null;
    }

    /////////////////////////////////////////////////////////////////
    public List<BusinessObject> getStatesByZip(String zip, String stateName) throws Exception {
        State state = (State) this.consumerRegistrationDAO.findByPropertyUnique(new State(), "name", stateName, Constants.HIBERNATE_EQ_OPERATOR);
        if (state == null) {
            return null;
        }
        ///////////////////////////////////////////
        List<BusinessObject> lstObj = new ArrayList();
        BusinessObject obj = new BusinessObject("state.id", state.getId(), Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        obj = new BusinessObject("zip", zip, Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        List<StateZipCode> lst = this.consumerRegistrationDAO.findByNestedProperty(
                new StateZipCode(), lstObj, "", 0);
        List<BusinessObject> lstObj2 = new ArrayList();
        if (lst != null) {
            for (StateZipCode zipCode : lst) {
                BusinessObject obj2 = new BusinessObject(zipCode.getId().toString(), zip, 0);
                lstObj2.add(obj2);
            }
        }
        //////////////////////////////////////////
        return lstObj2;
    }

    ////////////////////////////////////////////////////////////////
    public List<OrderDetailDTO> getProcessedOrdersByPatientId(String orderId, Integer pharmacyId) throws Exception {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
//        List<Integer> lst1=new ArrayList<>();
//        lst1=CommonUtil.extractIdsListFromCommaSeparatedString2(Constants.processedOrderIds, lst1);
        List<Integer> lstStatus = CommonUtil.generateStatusIdsList();//.extractIdsListFromCommaSeparatedString(Constants.processedOrderIds);

   
        return null;
    }

    public Long getProcessedOrdersCount(String orderId, Integer pharmacyId) throws Exception {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
//        List<Integer> lst1=new ArrayList<>();
//        lst1=CommonUtil.extractIdsListFromCommaSeparatedString2(Constants.processedOrderIds, lst1);
        List<Integer> lstStatus = CommonUtil.generateStatusIdsList();//.extractIdsListFromCommaSeparatedString(Constants.processedOrderIds);

        
        return null;
    }

    /////////////////////////////////////////////////////////////////
    public List<OrderDetailDTO> getShippingQueueByPatientId(String orderId, Integer pharmacyId) throws Exception {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
        if (order != null) {
            List<Order> orders = orderDao.getMultiRxOrdersStatusWiseByPatientId(order.getPatientProfile().getPatientProfileSeqNo(),
                        8, pharmacyId);//populating Processed/Filled orders
                return populateOrderDetailDTO(orders, 0);
            
        }
        return null;
    }

    /////////////////////////////////////////////////////////////////
    public List<OrderDetailDTO> getMultiRxOrdersByPatientId(Integer orderId, Integer patientId, int waiveDeliveryFee, Integer pharmacyId) {
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            List<Order> orders = orderDao.getMultiRxOrdersByPatientId(orderId, patientId, pharmacyId);
            list = populateOrderDetailDTO(orders, waiveDeliveryFee);
        } catch (Exception e) {
            logger.error("Exception# getMultiRxOrdersByPatientId# ", e);
        }
        return list;
    }

    ///////////////////////////////////////////////////////////////////
    public List<OrderDetailDTO> getMultiRxOrdersByDependentId(Integer orderId, Integer dependentId, int waiveDeliveryFee, Integer pharmacyId) throws Exception {
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            List<Order> orders = orderDao.getMultiRxOrdersByDependentId(orderId, dependentId, pharmacyId);
            list = populateOrderDetailDTO(orders, waiveDeliveryFee);
        } catch (Exception e) {
            logger.error("Exception# getMultiRxOrdersByDependentId# ", e);
        }
        return list;
    }

    ///////////////////////////////////////////////////////////////////
    private List<OrderDetailDTO> populateOrderDetailDTO(List<Order> orders, int waiveDeliveryFee) throws Exception {
        List<OrderDetailDTO> odt = new ArrayList<>();

        for (Order order : orders) {
            OrderDetailDTO on = populateOrderDetails(order, waiveDeliveryFee);
            //////////////////////////////////////////
            odt.add(on);
        }
        return odt;
    }
    //////////////////////////////////////////////////////////////////

    private OrderDetailDTO populateOrderDetails(Order order, int waiveDeliveryFee) throws Exception {
        OrderDetailDTO on = new OrderDetailDTO();
        CommonUtil.populateDecryptedOrderData(on, order);
        on.setId(order.getId());
        String dateValue = order.getId();
        String yearPart = "", monthpart = "", dayspart = "", extraorderValue = "";
        if (dateValue.length() >= 4) {
            yearPart = dateValue.substring(0, 4);
        }
//        on.setYear(yearPart);
        if (dateValue.length() >= 6) {
            monthpart = dateValue.substring(4, 6);
        }
//        on.setMonth(monthpart);
        if (dateValue.length() >= 8) {
            dayspart = dateValue.substring(6, 8);
        }
        on.setDays(dayspart);
        if (dateValue.length() > 8) {
            extraorderValue = dateValue.substring(8);
        }
//        on.setExtraordervalue(extraorderValue);
        on.setSystemGeneratedRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), ""));
        populateOrderDrugDetail(order, on);
        on.setQty(AppUtil.getSafeStr(order.getQty(), ""));
        on.setDaysSupply(order.getDaysSupply() != null ? order.getDaysSupply() : 0);
        on.setRefillsRemaining(order.getRefillsRemaining() != null ? order.getRefillsRemaining() + "" : "0");
        on.setNextRefillDate(order.getNextRefillDate());
        String requestType = AppUtil.getSafeStr(order.getOrderType(), "");
        String requestStr = "", requestStyle = "";
        if (!requestType.equalsIgnoreCase("Refill")) {
            if (order.getIsBottleAvailable() != null && order.getIsBottleAvailable()) {
                requestType = "X-FER LABEL SCAN";
                requestStr = "RX  LABEL";
                requestStyle = "blueText";
                //transferRxQueueDTO.setLabelScan(true);
            } else {
                requestType = "X-FER RX SCAN";
                requestStr = "PAPER RX";
                requestStyle = "greenText";
                // transferRxQueueDTO.setRxScan(true);
            }
        }
        on.setRequestType(requestType);
//        on.setRequestStr(requestStr);
//        on.setRequestStyle(requestStyle);
        on.setFinalPayment(order.getFinalPayment() != null ? order.getFinalPayment() : 0.0d);
        on.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(on.getFinalPayment(), "en", "us"));
//        on.setRedeemPointsCost(order.getRedeemPointsCost() != null ? order.getRedeemPointsCost() : 0d);
//        on.setRedeemPointsCostStr(AppUtil.roundOffNumberToCurrencyFormat(on.getRedeemPointsCost(), "en", "US"));
        int redeemPoints = AppUtil.getSafeInt(AppUtil.getSafeStr(order.getRedeemPoints(), ""), 0);
        String redeemPointsStr = AppUtil.roundOffNumberToCurrencyFormat(redeemPoints, "en", "US").replace("$", "");
        String[] arr = redeemPointsStr.split("\\.");
        if (arr.length > 0) {
            redeemPointsStr = arr[0];
        }
//        on.setRedeemPoints(redeemPointsStr);
        /////////////////////////////
        if (on.getNextRefillDate() != null) {
            Date d = new Date();
            long daysToRefill = DateUtil.dateDiffInDays(on.getNextRefillDate(), d);
            if (daysToRefill == 0) {
                on.setDaysToRefill("NOW");
            } else if (on.getNextRefillDate().after(d)) {
                if (daysToRefill < 0) {
                    daysToRefill = Math.abs(daysToRefill);
                }
                on.setDaysToRefill(daysToRefill + "");
            } else {
                on.setDaysToRefill(daysToRefill + " Overdue");
//              /  on.setOverdueFlag(true);
            }
        } else {
            on.setDaysToRefill("Not refillable yet");
        }
        /////////////////////////////
        if (order.getDeliveryPreference() != null) {
//            on.setDeliveryPreferenceId(order.getDeliveryPreference().getId());
//            on.setDeliveryPreferencesName(order.getDeliveryPreference().getName());
        } else {
//         /   on.setDeliveryPreferenceId(0);
//            on.setDeliveryPreferencesName("-");
        }
//        on.setTrackingNo(AppUtil.getSafeStr(order.getTraclingno(), "-"));
//        on.setClerk(AppUtil.getSafeStr(order.getClerk(), "-"));
        double handlingFee = order.getHandLingFee() != null ? order.getHandLingFee() : 0d;
//        on.setHandLingFee(waiveDeliveryFee == 0 ? handlingFee : 0d);
//        on.setHandlingFeeStr(AppUtil.roundOffNumberToCurrencyFormat(on.getHandLingFee(), "en", "US"));
        double finalPayment = order.getFinalPayment() != null ? order.getFinalPayment() : 0d;
        double paymentExcludingDelivery = finalPayment > handlingFee ? finalPayment - handlingFee : 0d;
        on.setFinalPayment(waiveDeliveryFee == 0 ? finalPayment : paymentExcludingDelivery);
        on.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(on.getFinalPayment(), "en", "US"));
        on.setDrugType(order.getDrugType());
        on.setStrength(order.getStrength());
        on.setQty(order.getQty());
        if (order.getFilledDate() != null) {
            on.setFilledDate(order.getFilledDate());
            on.setFilledDateStr(DateUtil.dateToString(order.getFilledDate(), "YYYY-MM-dd"));
        }
        on.setRxNumber(AppUtil.getSafeStr(order.getRxNumber(), ""));
        ///////////////////////////////////////////
//        on.setSelfPayCheck(AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("SELF PAY") ? "Y" : "INS");
//        on.setPublicInsCheck(AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("Public") ? "Y" : "N");
        on.setStatusId(order.getOrderStatus().getId());
        if (order.getOrderStatus().getId() == 1) {
            if (AppUtil.getSafeStr(order.getImagePath(), "").length() > 0
                    || AppUtil.getSafeStr(order.getVideo(), "").length() > 0) {
                on.setOrderStatusName("UNVERIFIED");//"UNVERIFIED IMG");
            } else {
                on.setOrderStatusName("Pending");
            }
        } else if (order.getOrderStatus().getId() == 17) {
            on.setOrderStatusName("Interpreted Images");
        } else {
            on.setOrderStatusName(order.getOrderStatus().getName());
        }
        on.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"));
       
//            on.setDependentId("0");
//            on.setCareGiver(0);
      
        on.setZipCode(AppUtil.getSafeStr(order.getZip(), "--"));
        String year = DateUtil.dateToString(order.getCreatedOn(), "yyyy");
        String month = DateUtil.dateToString(order.getCreatedOn(), "MM");
        String d = DateUtil.dateToString(order.getCreatedOn(), "dd");
        String hm = DateUtil.dateToString(order.getCreatedOn(), "HH:mm");
        on.setStatusPostedDate(year + "-" + month + "-<br/><span class='redText'>" + d + "</span>@" + hm);
//        on.setPaymentMode(AppUtil.getSafeStr(order.getFinalPaymentMode(), ""));
        if (CommonUtil.isNotEmpty(order.getApartment())) {
            logger.info("Appartmnet is: " + order.getApartment());
//            on.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                    + AppUtil.getSafeStr(order.getApartment(), "") + " " + AppUtil.getSafeStr(order.getCity(), "")
//                    + " " + AppUtil.getSafeStr(order.getState(), ""));
        } else {
//            on.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                    + AppUtil.getSafeStr(order.getCity(), "") + " " + AppUtil.getSafeStr(order.getState(), ""));
        }
        PatientDeliveryAddress patientDeliveryAddress = populatePatientDeliveryAddressData(order);
        if (patientDeliveryAddress != null && !CommonUtil.isNullOrEmpty(patientDeliveryAddress.getId())) {
//            on.setDeliveryAddressId(patientDeliveryAddress.getId());
        }
        return on;
    }

    private void populateOrderDrugDetail(Order order, OrderDetailDTO on) {
        String brandName = "", genericName = "", drugName = "";
        boolean userInput = false, brandedDrug = false;
        if (order.getDrugDetail() != null && order.getDrugDetail().getDrugBasic() != null) {
            brandName = order.getDrugDetail().getDrugBasic().getBrandName();
            if (order.getDrugDetail().getDrugBasic().getBrandIndicator().equals("GENERIC")) {
                genericName = order.getDrugDetail().getDrugBasic().getDrugGeneric() != null
                        ? order.getDrugDetail().getDrugBasic().getDrugGeneric().getGenericName() : "*BRAND NAME*";
                drugName = genericName;
                brandedDrug = false;
            } else {
                genericName = "*BRAND NAME*";
                drugName = brandName + " " + genericName;
                brandedDrug = true;
            }
        } else {
            drugName = AppUtil.getSafeStr(order.getDrugName(), "");
        }
        on.setDrugName(drugName);
        on.setGenericName(genericName);
        on.setBrandName(brandName);
        on.setUserInput(userInput);
//        on.setBranded(brandedDrug);
    }

    private PatientDeliveryAddress populatePatientDeliveryAddressData(Order order) {
        PatientDeliveryAddress patientDeliveryAddress = null;
        if (order.getPatientProfile() != null && !CommonUtil.isNullOrEmpty(order.getPatientProfile().getPatientProfileSeqNo())) {
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("patientProfile.id", order.getPatientProfile().getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            obj = new BusinessObject("address", AppUtil.getSafeStr(order.getStreetAddress(), ""), Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            obj = new BusinessObject("city", AppUtil.getSafeStr(order.getCity(), ""), Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            obj = new BusinessObject("zip", AppUtil.getSafeStr(order.getZip(), ""), Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            if (CommonUtil.isNotEmpty(AppUtil.getSafeStr(order.getState(), ""))) {
                State state = (State) consumerRegistrationDAO.findByPropertyUnique(new State(), "name", AppUtil.getSafeStr(order.getState(), ""), Constants.HIBERNATE_EQ_OPERATOR);
                obj = new BusinessObject("state.id", state.getId(), Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
            }
            patientDeliveryAddress = (PatientDeliveryAddress) consumerRegistrationDAO.findUniqueObjectByProperty(new PatientDeliveryAddress(), lstObj, "", 0);
            if (patientDeliveryAddress == null || CommonUtil.isNullOrEmpty(patientDeliveryAddress.getId())) {
                patientDeliveryAddress = patientProfileService.getPatientDeliveryDefaultAddress(order.getPatientProfile().getPatientProfileSeqNo());
            }
        }
        return patientDeliveryAddress;
    }

    private List<OrderDetailDTO> populateOrderDetailDTO(List<Order> orders) {
        List<OrderDetailDTO> odt = new ArrayList<>();

        for (Order o : orders) {
            OrderDetailDTO on = new OrderDetailDTO();
            on.setId(o.getId());
            on.setDrugName(o.getDrugName());
//            on.setDeliveryPreferenceId(o.getDeliveryPreference().getId());
//            on.setDeliveryPreferencesName(o.getDeliveryPreference().getName());
//            on.setDeliveryFee(o.getHandLingFee());
//            on.setDrugType(o.getDrugType());
            on.setStrength(o.getStrength());
            on.setQty(o.getQty());
            odt.add(on);
        }
        return odt;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public int updateHandDeliveryFlag(String orderId) throws Exception {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
        if (order.getHandDeliveryAccepted() != null && order.getHandDeliveryAccepted() == 1) {
            throw new Exception("This order is already accepted for shipment.");
        }
        order.setHandDeliveryAccepted(1);
        order.setDisabledFlds(0);
        this.consumerRegistrationDAO.saveOrUpdate(order);
        return 1;
    }
    /////////////////////////////////////////////////////////////////////////////////

    public PatientProfile getPatientProfile(String mobileNumber) throws Exception {
        PatientProfile pf = orderDao.getPatientProfile(EncryptionHandlerUtil.getEncryptedString(mobileNumber));
        PatientProfile pp = null;
        if (pf != null) {
            pp = new PatientProfile();
            pp.setPatientProfileSeqNo(pf.getPatientProfileSeqNo());
            pp.setFirstName(pf.getFirstName());
            pp.setLastName(pf.getLastName());
            pp.setMobileNumber(pf.getMobileNumber());
        }
        return pp;
    }

    public TransferRxQueueDTO getNextOrderImages(String orderId, Integer id) {
        List<OrderTransferImages> list = this.consumerRegistrationDAO.getNextOrderImagesList(orderId, id);
        if (list != null && list.size() > 0) {
            return populateDTOFrmImageData(list.get(0), list.size() > 1, true);
        }
        return null;
    }

    public TransferRxQueueDTO getPrevOrderImages(String orderId, Integer id) {
        List<OrderTransferImages> list = this.consumerRegistrationDAO.getPrevOrderImagesList(orderId, id);
        if (list != null && list.size() > 0) {
            return populateDTOFrmImageData(list.get(0), true, list.size() > 1);
        }
        return null;
    }

    private TransferRxQueueDTO populateDTOFrmImageData(OrderTransferImages image, boolean lastImage, boolean istImage) {
        TransferRxQueueDTO dto = new TransferRxQueueDTO();
        dto.setImageId(image.getId());
        dto.setTransferImage(image.getDrugImg());
        dto.setNextImage(lastImage);
        dto.setPrevImage(istImage);
        return dto;
    }

    public int createOrderCustomDocument(String docURL, int createdBy, String orderId) throws Exception {
        Order order = (Order) this.consumerRegistrationDAO.findRecordById(new Order(), orderId);
        if (order != null) {
            OrderCustomDocuments docs = new OrderCustomDocuments();
            docs.setCreatedOn(new Date());
            docs.setOrder(order);
            docs.setCustomDocument(docURL);
            this.consumerRegistrationDAO.saveOrUpdate(docs);

        }
        return 1;

    }

    public int updateOrderDeletionAttributes(int id, int updatedBy, int statusVal) throws Exception {
        Order order = this.orderDao.getOrdersById("" + id);
        Date date = new Date();
        String addday = PropertiesUtil.getProperty("AUTO_DELETION_DATE");
        int additionInDate = AppUtil.getSafeInt(addday, 2);
        Date newDate = addDays(date, additionInDate);
        if (order != null) {
            OrderStatus status = new OrderStatus();

            status.setId(statusVal);
            order.setOrderStatus(status);
            order.setAutoDeletionFlag(1);
            order.setAutoDeletionDate(newDate);
            orderDao.saveOrUpdate(order);
        }
        return 1;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public String getFilteredNewMemberRequest(int iDisplayStart, int iDisplayLength, String search, String colName, String sdir, String filter, int sEcho, int userId, Integer pharmacyId, SearchDTO searchDTO, SessionBean sessionBean) {
        String jsonResponse = "";
        try {
            Map<String, String> rxMap = new HashMap();
            List<TransferRxQueueDTO> dbList = null;
            if (CommonUtil.isNotEmpty(search) || CommonUtil.isNotEmpty(searchDTO.getOrderStatus())
                    || CommonUtil.isNotEmpty(searchDTO.getMbrReqType()) || CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())
                    || CommonUtil.isNotEmpty(searchDTO.getMbrReqType()) || CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
                List<Object[]> list = consumerRegistrationDAO.getAllFilteredRecords(0, 0, search, colName, sdir, filter, pharmacyId, searchDTO);
                dbList = this.populateAllOrderStatusData(list, userId);
            } else {
                List<Object[]> list = consumerRegistrationDAO.getFilteredRecordByStatusWise(0, 0, search, colName, sdir, filter, pharmacyId, searchDTO);
                dbList = this.populateTransferDTOForPage1(list, userId);
            }
            // List<TransferRxQueueDTO> dbList = populateTransferDTOForPage1(list, userId);
            List<TransferRxQueueDTO> newlist = new ArrayList<>();
            int rxCount = 0;
            for (TransferRxQueueDTO rxQueueDTO : dbList) {
                TransferRxQueueDTO queueDTO = new TransferRxQueueDTO();
                queueDTO.setPatientResponse(rxQueueDTO.getPatientResponse());
                queueDTO.setSortByMbrReq(rxQueueDTO.getSortByMbrReq());
                queueDTO.setSortByStatus(rxQueueDTO.getSortByStatus());
                queueDTO.setPatientId(rxQueueDTO.getPatientId());
                queueDTO.setOrderId(rxQueueDTO.getOrderId());
                queueDTO.setOrderStatus(rxQueueDTO.getOrderStatus());
                rxQueueDTO.setRxCount(rxCount);
                String rxNumber = this.getLastThreeRxNumber(rxQueueDTO);
                rxQueueDTO.setRxControlNumber(rxQueueDTO.getOrderId() + "-" + rxNumber);
                rxCount = rxQueueDTO.getRxCount();
                rxMap.put(rxQueueDTO.getOrderId(), rxNumber);
                String applicationUrl = PropertiesUtil.getProperty("APP_PATH");
                String careGiverDetailUrl = applicationUrl + "/PharmacyPortal/careGiverDetail/";
                queueDTO.setCareGiverDetailUrl(careGiverDetailUrl);
                String rxDetailUrl = applicationUrl + "/ConsumerPortal/rxDetail/";
                queueDTO.setRxDetailUrl(rxDetailUrl);
                this.populateQuestionAnswerId(rxQueueDTO, queueDTO);
                if (rxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                        || rxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*")) {
                    if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1 && rxQueueDTO.getCaregiverRequestApproved() == 0) {

                        queueDTO.setRequestControlNumber1("<a href='" + careGiverDetailUrl
                                + rxQueueDTO.getDependentId() + "?pq=1' style='color: red'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                    } else {

                        if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase("Pending")) && AppUtil.getSafeStr(
                                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                            //queueDTO.setRequestControlNumber1("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.showAnswerRxQuestion(" + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientResponse() + "'," + rxQueueDTO.getPatientId() + ")\" style=\"color:red\">" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + rxQueueDTO.getQuestionId() + "' style='color: red'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        } else {
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color: blue'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        }

                    }
                } else {
                    if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1 && rxQueueDTO.getCaregiverRequestApproved() == 0) {
                        queueDTO.setRequestControlNumber1("<a href='" + careGiverDetailUrl
                                + rxQueueDTO.getDependentId() + "?pq=1' style='color: blue'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + rxNumber + "</a>");
                    } else {
                        if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase("Pending")) && AppUtil.getSafeStr(
                                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                            //queueDTO.setRequestControlNumber1("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.showAnswerRxQuestion(" + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientResponse() + "'," + rxQueueDTO.getPatientId() + ")\" >" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + rxQueueDTO.getQuestionId() + "' style='color: red'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        } else {
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color: blue'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        }
                    }

                }
                if (filter.equalsIgnoreCase("Pending")) {
                    populateNewRequestMemberData(rxQueueDTO, queueDTO);
                } else if (filter.equalsIgnoreCase("Pending Review")) {
                    populatePendingReviewData(rxQueueDTO, queueDTO, filter);
                } else if (filter.equalsIgnoreCase("Waiting Pt Response")) {
                    populateWaitingPtResponseData(rxQueueDTO, queueDTO);
                } else if (filter.equalsIgnoreCase("Shipped")) {
                    populateProcessedRequestData(rxQueueDTO, queueDTO);
                } else if (filter.equalsIgnoreCase("Filled")) {
                    populateShippingDeliveryData(queueDTO, rxQueueDTO);
                } else if (filter.equalsIgnoreCase("Cancelled")) {
                    populateCancelledRequestData(queueDTO, rxQueueDTO);
                } else if (filter.equalsIgnoreCase("Response Received")) {
                    populateResponseReceivedData(rxQueueDTO, queueDTO);
                }
                sessionBean.setRxNumberMap(rxMap);
                newlist.add(queueDTO);
            }
            //Filter Record
            newlist = this.filterRecords(searchDTO, newlist);

            ///////////////////////////////////////////////////////////////
            DataTablesTO<TransferRxQueueDTO> dataTableTo = new DataTablesTO<>();
            Integer totalRecords = newlist.size() < dbList.size() ? newlist.size() : dbList.size();
            iDisplayLength = iDisplayLength + iDisplayStart;
            List<TransferRxQueueDTO> newRecordList = newlist.size() < dbList.size() ? newlist : newlist.subList(Math.max(0, iDisplayStart), Math.min(totalRecords, iDisplayLength));

            //Sort Record
            if (filter.equalsIgnoreCase("Pending") && CommonUtil.isNotEmpty(colName)) {
                if (colName.contains("requestType") || colName.contains("deliveryService") || colName.contains("status")) {
                    newRecordList = newRecordList.stream().sorted(Comparator.comparing(ord -> ord.getSortByMbrReq() == 1 && ord.getSortByStatus() == 1 && ord.getSortByDeliveryPref() == 1)).collect(Collectors.toList());
                }
            }
            dataTableTo.setiTotalDisplayRecords(totalRecords.longValue());
            dataTableTo.setAaData(newRecordList);
            dataTableTo.setiTotalRecords(totalRecords);
            dataTableTo.setsEcho(sEcho);
            jsonResponse = CommonUtil.toJson(dataTableTo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getFilteredNewMemberRequest:: ", e);
        }
        return jsonResponse;
    }

    private List<TransferRxQueueDTO> populateAllOrderStatusData(List<Object[]> list, int id) throws Exception {
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        Map patientMap = new HashMap();
        Map orderMap = new HashMap();
        Map orderStatusMap = new HashMap();
        Map patientMap1 = new HashMap();
        String name = "";
        for (Object[] arr : list) {
            PatientProfile patientProfile = (PatientProfile) arr[1];
            Integer dependentId = 0;
            Order orders = (Order) arr[0];
            OrderStatus orderStatus = (OrderStatus) arr[4];
            name = EncryptionHandlerUtil.getDecryptedString(patientProfile.getFirstName()) + " " + EncryptionHandlerUtil.getDecryptedString(patientProfile.getLastName());//patientProfile.getFirstName() + " " + patientProfile.getLastName();
            
            String key = patientProfile.getPatientProfileSeqNo() + "_" + dependentId;
            patientMap1.put(key, key);
            if (!orderMap.containsKey(orders.getId())) {
                if (patientMap1.containsKey(key) && !orderStatusMap.containsKey(orderStatus.getId())) {
                    String hasRxCard = "";
                    TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
                    PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[2];
                    DeliveryPreferences pref = (DeliveryPreferences) arr[3];
                    //OrderStatus orderStatus = (OrderStatus) arr[4];
                    DrugDetail detail = (DrugDetail) arr[5];
//                    if (patientProfile.getInsuranceFrontCardPath() != null) {
//                        hasRxCard = "yes";
//                    } else {
//                        hasRxCard = "no";
//                    }
                    if (AppUtil.getSafeStr(orders.getPaymentType(), "").toUpperCase().indexOf("INSURANCE") >= 0) {
                        hasRxCard = "yes";
                    } else {
                        hasRxCard = "no";
                    }
                    transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
                    transferRxQueueDTO.setDependentId(dependentId);
                    //                if(orders.getPatientProfileMembers()!=null && 
                    //                   orders.getPatientProfileMembers().getIsAdult()!=null &&
                    //                   orders.getPatientProfileMembers().getIsAdult())
                    //                {
                    //                    transferRxQueueDTO.setDependentId(orders.getPatientProfileMembers().getId());
                    //                    transferRxQueueDTO.setCareGiverRequest(1);
                    //                }
                    //                else
                    //                {
                    //                    transferRxQueueDTO.setCareGiverRequest(0);
                    //                }
                    transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
                    transferRxQueueDTO.setUpdatedDate(orders.getUpdatedOn());
                    transferRxQueueDTO.setUpdatedBy(this.getPharmacyUserById(orders.getUpdatedBy()) != null ? this.getPharmacyUserById(orders.getUpdatedBy()).getFullName() : "N/A");
                    if (orders.getQty() != null && !orders.getQty().trim().equalsIgnoreCase("")) {
                        transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
                    }
                    transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
                    transferRxQueueDTO.setOrderId(orders.getId());
                    transferRxQueueDTO.setRxNumber(AppUtil.getSafeStr(orders.getRxPrefix(), "") + orders.getOldRxNumber());
                    transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
                    //////////////////////////////////////////////
                    try {
                        String videoStr = AppUtil.getSafeStr(orders.getVideo(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                        if (videoStr.length() > 0 && videoStr.indexOf("http://") < 0) {
                            videoStr = "http://" + videoStr;
                        }
                        transferRxQueueDTO.setPtVideo(videoStr);
                        String imgStr = AppUtil.getSafeStr(orders.getImagePath(), "").replace("localhost", PropertiesUtil.getProperty("APP_PATH"));
                        if (imgStr.length() > 0 && imgStr.indexOf("http://") < 0) {
                            imgStr = "http://" + imgStr;
                        }
                        transferRxQueueDTO.setTransferImage(imgStr);

                    } catch (Exception e) {
                    }

                    /////////////////////////////////////////////
                    transferRxQueueDTO.setAllergies(AppUtil.getSafeStr(patientProfile.getAllergies(), ""));
                    transferRxQueueDTO.setGender(patientProfile.getGender());
                    transferRxQueueDTO.setPayCash(true);
                    //transferRxQueueDTO.setMultiRx(false);
                    List<BusinessObject> buisnessObjectLst = new ArrayList<>();
                    buisnessObjectLst.add(new BusinessObject("patientProfile.id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR));
                    buisnessObjectLst.add(new BusinessObject("orderStatus.id", orderStatus.getId(), Constants.HIBERNATE_EQ_OPERATOR));
                    Long multiRxCount = (Long) consumerRegistrationDAO.getTotalRecordsByNestedProperty(new Order(), buisnessObjectLst, "Count(*)");
                    if (!CommonUtil.isNullOrEmpty(multiRxCount) && multiRxCount > 0) {
                        transferRxQueueDTO.setMultiRx(true);
                        transferRxQueueDTO.setMultiRxCount(multiRxCount.intValue());
                        if (transferRxQueueDTO.getMultiRxCount() <= 9) {
                            transferRxQueueDTO.setMultiRxLabel("Y-0" + transferRxQueueDTO.getMultiRxCount());
                        } else {
                            transferRxQueueDTO.setMultiRxLabel("Y-" + transferRxQueueDTO.getMultiRxCount());
                        }
                    } else {
                        transferRxQueueDTO.setMultiRx(false);
                        transferRxQueueDTO.setMultiRxCount(1);
                        transferRxQueueDTO.setMultiRxLabel("N-" + (CommonUtil.isNullOrEmpty(multiRxCount) ? 0 : multiRxCount.intValue()));
                    }
                    //transferRxQueueDTO.setMultiRxCount(1);
                    //transferRxQueueDTO.setMultiRxLabel("N-1");
                    transferRxQueueDTO.setPatientName(name);
                    transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
                   

                    ///////////////////////////////////////////////////////////
                    transferRxQueueDTO.setStatus((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "OPEN (LOCKED)" : "Pending");
                    transferRxQueueDTO.setDisabled((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "1" : "0");
                    transferRxQueueDTO.setHasRxCard(hasRxCard);
                    if (addr != null) {
                        transferRxQueueDTO.setZip(addr.getZip());

                        transferRxQueueDTO.setDeliveryAddress(AppUtil.getSafeStr(addr.getAddress(), "") + "<br/>" + addr.getState().getName() + " " + addr.getApartment() + " " + addr.getZip());
                    }
                    transferRxQueueDTO.setSellingPrice(orders.getPriceIncludingMargins() != null ? orders.getPriceIncludingMargins() : 0d);
                    transferRxQueueDTO.setSellingPriceStr(
                            AppUtil.roundOffNumberToCurrencyFormat(
                                    transferRxQueueDTO.getSellingPrice(), "en", "US"));
                    transferRxQueueDTO.setOriginalPtCopay(orders.getOriginalPtCopay() != null ? orders.getOriginalPtCopay() : 0d);
                    transferRxQueueDTO.setOriginalPtCopayStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getOriginalPtCopay(), "en", "US"));
                    String currentStatus = AppUtil.getSafeStr(orders.getPatientResponse(), "");
                    transferRxQueueDTO.setPatientResponse(currentStatus);
                    String responseRequired = AppUtil.getSafeStr(orders.getResponseRequired(), "");
                    transferRxQueueDTO.setMedicationSpecMsg(responseRequired);
                    String deliverycarrier = AppUtil.getSafeStr(orders.getDeliverycarrier(), "");
                    transferRxQueueDTO.setDeliverycarrier(deliverycarrier);
                    transferRxQueueDTO.setMiles(orders.getMiles());

                    transferRxQueueDTO.setDeliveryFrom(orders.getDeliveryFrom());
                    transferRxQueueDTO.setDeliveryTo(orders.getDeliveryTo());
                    transferRxQueueDTO.setUnderQuotedPrice(orders.getEstimatedPrice() != null ? orders.getEstimatedPrice() : 0d);
                    transferRxQueueDTO.setUnderQuotedPriceStr(AppUtil.roundOffNumberToCurrencyFormat(transferRxQueueDTO.getUnderQuotedPrice(), "en", "US"));
                    transferRxQueueDTO.setPatientResponse(AppUtil.getSafeStr(orders.getPatientResponse(), key));
                    transferRxQueueDTO.setShippingStatus(orderStatus.getName());
                    String drugName = AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(orders.getDrugName()), "");
                    if (detail != null) {
                        //                    String drugName = AppUtil.getOneStringFromBrandAndGeneric(detail.getDrugBasic().getBrandName(),
                        //                            detail.getDrugBasic().getDrugGeneric().getGenericName(),
                        //                            detail.getDrugBasic().getDrugGeneric().getBrandNameOnly());

                        try {
                            if (detail != null) {
                                if (detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() != null
                                        && detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() == 1) {
                                    drugName = detail.getDrugBasic().getBrandName();
                                    transferRxQueueDTO.setBrandOnly(true);
                                } else {
                                    drugName = detail.getDrugBasic().getDrugGeneric().getGenericName();
                                }
                            }
                        } catch (Exception e) {

                        }
                        transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                        transferRxQueueDTO.setStrength(detail.getStrength());
                        transferRxQueueDTO.setDrugType(detail.getDrugForm().getFormDescr());
                        transferRxQueueDTO.setUserInput(false);
                        transferRxQueueDTO.setUserInputStr("");
                        transferRxQueueDTO.setStatus(orderStatus.getName());
                        transferRxQueueDTO.setOrderStatus(orderStatus.getName());

                    } else {
                        transferRxQueueDTO.setOldRxNo(AppUtil.getSafeStr(orders.getOldRxNumber(), ""));
                        transferRxQueueDTO.setPharmacyPhone(AppUtil.getSafeStr(orders.getPharmacyPhone(), ""));
                        transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                        transferRxQueueDTO.setStrength("");
                        transferRxQueueDTO.setDrugType("");
                        transferRxQueueDTO.setUserInput(true);
                        if (AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "").length() > 0
                                || transferRxQueueDTO.getPharmacyPhone().length() > 0
                                || transferRxQueueDTO.getDrugNameWithoutStrength().length() > 0) {
                            transferRxQueueDTO.setUserInputStr(AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "") + "<br>"
                                    + transferRxQueueDTO.getPharmacyPhone() + "<br>"
                                    + transferRxQueueDTO.getDrugNameWithoutStrength());
                        } else {
                            transferRxQueueDTO.setUserInputStr("");
                        }
                        if (AppUtil.getSafeStr(orders.getImagePath(), "").length() > 0
                                || AppUtil.getSafeStr(orders.getVideo(), "").length() > 0) {
                            transferRxQueueDTO.setStatus("UNVERIFIED");//"UNVERIFIED IMG");
                            transferRxQueueDTO.setSortByStatus(1);
                        } else {
                            transferRxQueueDTO.setStatus("Pending");
                        }

                    }
                    transferRxQueueDTO.setQuantity(AppUtil.getSafeInt(orders.getQty(), 0));
                    
                   
                    transferRxQueueDTO.setRxAcqCost(orders.getRxAcqCost() != null ? orders.getRxAcqCost() : 0d);
                    transferRxQueueDTO.setRxAcqCostStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getRxAcqCost(), "en", "US"));
                    transferRxQueueDTO.setFinalCopay(orders.getFinalPayment() != null ? orders.getFinalPayment() : 0d);
                    transferRxQueueDTO.setFinalCopayStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getFinalCopay(), "en", "US"));
                    
                    transferRxQueueDTO.setTracking(AppUtil.getSafeStr(orders.getTraclingno(), ""));
                    String paymentMode = AppUtil.getSafeStr(orders.getFinalPaymentMode(), "");
                    transferRxQueueDTO.setFinalPaymentMode(paymentMode.equalsIgnoreCase("SELF PAY") ? "SELF PAY" : "INS");
                    transferRxQueueDTO.setDeliveryService(pref != null ? AppUtil.getSafeStr(pref.getName(), "-") : "-");
                    transferRxQueueDTO.setDeliveryUrgent(transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                            || transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*"));

                    transferRxQueueDTO.setHandlingFee(orders.getHandLingFee() != null ? orders.getHandLingFee() : 0d);
                    Double totalSellingPrice = transferRxQueueDTO.getFinalCopay() + transferRxQueueDTO.getHandlingFee();
                    transferRxQueueDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(
                            totalSellingPrice, "en", "US"));
                    transferRxQueueDTO.setRequestControlNumber(orders.getId());
                    transferRxQueueDTO.setRequestControlNumber1(transferRxQueueDTO.getRequestControlNumber().substring(0, 8));
                    transferRxQueueDTO.setRequestControlNumber2(transferRxQueueDTO.getRequestControlNumber().substring(8));
                    String requestType = AppUtil.getSafeStr(orders.getOrderType(), "");
                    if (!requestType.equalsIgnoreCase("Refill")) {
                        transferRxQueueDTO.setTextColor("black");
                        if (orders.getOnlineOrder() != null && orders.getOnlineOrder()) {
                            requestType = "Online Order";
                            transferRxQueueDTO.setRequestTypeBgColor("#FFF233");

                        } else if (orders.getIsBottleAvailable() != null && orders.getIsBottleAvailable()) {
                            requestType = "X-FER LABEL SCAN";
                            transferRxQueueDTO.setRequestTypeBgColor("#3a89d7");
                            transferRxQueueDTO.setTextColor("white");
                            transferRxQueueDTO.setSortByMbrReq(1);
                        } else {
                            requestType = "X-FER RX SCAN";
                            transferRxQueueDTO.setRequestTypeBgColor("#8aca14");
                            transferRxQueueDTO.setTextColor("white");
                            transferRxQueueDTO.setSortByMbrReq(2);
                        }
                    }
                    transferRxQueueDTO.setRequestType(requestType);
                    transferRxQueueDTO.setSystemGeneratedRxNumber(AppUtil.getSafeStr(orders.getSystemGeneratedRxNumber(), "-"));
                    transferRxQueueDTO.setPatientResponse(AppUtil.getSafeStr(orders.getPatientResponse(), ""));
                    transferRxQueueList.add(transferRxQueueDTO);
                    patientMap.put(key, transferRxQueueDTO);
                } else {
//TODO
//                    TransferRxQueueDTO transferRxQueueDTO2 = (TransferRxQueueDTO) patientMap.get(key);
//                    if (transferRxQueueDTO2 != null) {
//                        Order ordersTmp = (Order) arr[0];
//
//                        if (!transferRxQueueDTO2.getOrderId().equals(ordersTmp.getId())) {
//                            transferRxQueueDTO2.setMultiRx(true);
//                            transferRxQueueDTO2.setMultiRxCount(transferRxQueueDTO2.getMultiRxCount() + 1);
//                            transferRxQueueDTO2.setMultiRxLabel("Y-" + transferRxQueueDTO2.getMultiRxCount());
//                        }
//                    }
                }
                orderStatusMap.put(orderStatus.getId(), orderStatus.getId());
                orderMap.put(orders.getId(), orders.getId());
            }

        }
        return transferRxQueueList;
    }

    private List<TransferRxQueueDTO> filterRecords(SearchDTO searchDTO, List<TransferRxQueueDTO> newlist) {
        if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER LABEL SCAN") && AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Same Day")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByMbrReq() == 1 && ord.getSortByDeliveryPref() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER RX SCAN") && AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Same Day")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByMbrReq() == 2 && ord.getSortByDeliveryPref() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER LABEL SCAN")
                && (AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Next Day*") || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("2nd Day*") || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Pick Up at Pharmacy"))) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByMbrReq() == 1 && ord.getSortByDeliveryPref() == 0).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER RX SCAN")
                && (AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Next Day*") || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("2nd Day*") || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Pick Up at Pharmacy"))) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByMbrReq() == 2 && ord.getSortByDeliveryPref() == 0).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER LABEL SCAN")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByMbrReq() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER RX SCAN")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByMbrReq() == 2).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Same Day")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByDeliveryPref() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && (AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("2nd Day*")
                || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Pick Up at Pharmacy") || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Next Day*"))) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1 && ord.getSortByDeliveryPref() == 0).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER LABEL SCAN") && AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Same Day")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByDeliveryPref() == 1 && ord.getSortByMbrReq() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER LABEL SCAN") && (AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PICKUP_AT_PHARMACY)
                || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("2nd Day*") || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Next Day*"))) {
            newlist = newlist.stream().filter(ord -> ord.getSortByDeliveryPref() == 0 && ord.getSortByMbrReq() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER RX SCAN") && AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Same Day")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByDeliveryPref() == 1 && ord.getSortByMbrReq() == 2).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER RX SCAN") && (AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PICKUP_AT_PHARMACY)
                || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("2nd Day*") || AppUtil.getSafeStr(searchDTO.getDeliveryPref(), "").equalsIgnoreCase("Next Day*"))) {
            newlist = newlist.stream().filter(ord -> ord.getSortByDeliveryPref() == 0 && ord.getSortByMbrReq() == 2).collect(Collectors.toList());
        } else if (CommonUtil.isNotEmpty(searchDTO.getOrderStatus()) && !AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER LABEL SCAN")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByMbrReq() == 1 && ord.getSortByStatus() == 0).collect(Collectors.toList());
        } else if (CommonUtil.isNotEmpty(searchDTO.getOrderStatus()) && !AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED") && AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER RX SCAN")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByMbrReq() == 2 && ord.getSortByStatus() == 0).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER LABEL SCAN")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByMbrReq() == 1).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getMbrReqType(), "").equalsIgnoreCase("X-FER RX SCAN")) {
            newlist = newlist.stream().filter(ord -> ord.getSortByMbrReq() == 2).collect(Collectors.toList());
        } else if (AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) {
            newlist = newlist.stream().filter(ord -> ord.getSortByStatus() == 0).collect(Collectors.toList());
        }
        return newlist;
    }

    private String getLastThreeRxNumber(TransferRxQueueDTO rxQueueDTO) {
        String rxNumber = "";
        if (CommonUtil.isNotEmpty(rxQueueDTO.getSystemGeneratedRxNumber()) && !rxQueueDTO.getSystemGeneratedRxNumber().contains("-")) {
            if (rxQueueDTO.getSystemGeneratedRxNumber().length() > 3) {
                rxNumber = rxQueueDTO.getSystemGeneratedRxNumber().substring(rxQueueDTO.getSystemGeneratedRxNumber().length() - 3);
            } else {
                rxNumber = rxQueueDTO.getSystemGeneratedRxNumber();
            }
        }
        if (CommonUtil.isNullOrEmpty(rxNumber)) {
            if (AppUtil.getSafeStr(rxQueueDTO.getOldRxNo(), "").length() > 3) {
                rxNumber = rxQueueDTO.getOldRxNo().substring(rxQueueDTO.getOldRxNo().length() - 3);
            } else {
                rxNumber = AppUtil.getSafeStr(rxQueueDTO.getOldRxNo(), "");
            }
        }
        switch (AppUtil.getSafeStr(rxNumber, "").length()) {
            case 0:
                rxQueueDTO.setRxCount(rxQueueDTO.getRxCount() + 1);
                rxNumber = "00" + rxQueueDTO.getRxCount();
                break;
            case 1:
                rxNumber = "00" + rxNumber;
                break;
            case 2:
                rxNumber = "0" + rxNumber;
                break;
            default:
                break;
        }
        logger.info("rxNumber is# " + rxNumber);
        return rxNumber;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    private List<TransferRxQueueDTO> populateReadyToShipForPage1(List<Object[]> list, int id) throws Exception {
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        Map patientMap = new HashMap();
        Map orderMap = new HashMap();
        Map deliveryPrefMap = new HashMap();
        String name = "";
        int count = 0, deliveryOrdersCount = 0;
        for (Object[] arr : list) {
            count++;
            PatientProfile patientProfile = (PatientProfile) arr[1];
            Integer dependentId = 0;
            Order orders = (Order) arr[0];
            ReadyToDeliverRxOrders deliveryOrders = (ReadyToDeliverRxOrders) arr[10];
            if (deliveryOrders == null || CommonUtil.isNullOrEmpty(deliveryOrders.getId())) {
                logger.info("ReadyToDeliverRxOrders is null " + count);
                continue;
            }

            logger.info("Count is " + count);
            name = EncryptionHandlerUtil.getDecryptedString(patientProfile.getFirstName()) + " " + EncryptionHandlerUtil.getDecryptedString(patientProfile.getLastName());//patientProfile.getFirstName() + " " + patientProfile.getLastName();
            DeliveryPreferences pref = (DeliveryPreferences) arr[3];
            Integer prefId = pref == null || CommonUtil.isNullOrEmpty(pref.getId()) ? deliveryOrders.getDeliveryPreferences().getId() : pref.getId();
            String key = "" + patientProfile.getPatientProfileSeqNo();// + "_" + dependentId;
            if (!orderMap.containsKey(deliveryOrders.getId())) {
                if (!deliveryPrefMap.containsValue(deliveryOrders.getId())) {
                    String hasRxCard = "";
                    TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
                    PatientDeliveryAddress addr = (PatientDeliveryAddress) arr[2];
                    OrderStatus orderStatus = (OrderStatus) arr[4];
                    DrugDetail detail = (DrugDetail) arr[5];

                    if (AppUtil.getSafeStr(orders.getPaymentType(), "").toUpperCase().indexOf("INSURANCE") >= 0) {
                        hasRxCard = "yes";
                    } else {
                        hasRxCard = "no";
                    }
                    transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
                    transferRxQueueDTO.setDependentId(dependentId);

                    transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
                    transferRxQueueDTO.setUpdatedDate(orders.getUpdatedOn());
                    transferRxQueueDTO.setUpdatedBy(this.getPharmacyUserById(orders.getUpdatedBy()) != null ? this.getPharmacyUserById(orders.getUpdatedBy()).getFullName() : "N/A");
                    if (orders.getQty() != null && !orders.getQty().trim().equalsIgnoreCase("")) {
                        transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
                    }
                    transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
                    transferRxQueueDTO.setOrderId(orders.getId());
                    transferRxQueueDTO.setRxNumber(AppUtil.getSafeStr(orders.getRxPrefix(), "") + orders.getSystemGeneratedRxNumber());
                    transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
                    //////////////////////////////////////////////
                    try {
                        String videoStr = AppUtil.getSafeStr(orders.getVideo(), "").replace("localhost", "rxdirectdev.ssasoft.com");
                        if (videoStr.length() > 0 && videoStr.indexOf("http://") < 0) {
                            videoStr = "http://" + videoStr;
                        }
                        transferRxQueueDTO.setPtVideo(videoStr);
                        String imgStr = AppUtil.getSafeStr(orders.getImagePath(), "").replace("localhost", "rxdirectdev.ssasoft.com");
                        if (imgStr.length() > 0 && imgStr.indexOf("http://") < 0) {
                            imgStr = "http://" + imgStr;
                        }
                        transferRxQueueDTO.setTransferImage(imgStr);

                    } catch (Exception e) {
                    }

                    /////////////////////////////////////////////
                    transferRxQueueDTO.setAllergies(AppUtil.getSafeStr(patientProfile.getAllergies(), ""));
                    transferRxQueueDTO.setGender(patientProfile.getGender());
                    transferRxQueueDTO.setPayCash(true);
                    this.getTotalMultiRxCount(patientProfile, deliveryOrders, prefId, transferRxQueueDTO);
                    transferRxQueueDTO.setPatientName(name);
                    transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
                    ////////////////////////////////////////////////////////////
                    

                    ///////////////////////////////////////////////////////////
                    transferRxQueueDTO.setStatus((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "OPEN (LOCKED)" : "Pending");
                    transferRxQueueDTO.setDisabled((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) && orders.getUpdatedBy() != id ? "1" : "0");
                    transferRxQueueDTO.setHasRxCard(hasRxCard);

                    PatientDeliveryAddress deliveryAddress = deliveryOrders.getPatientDeliveryAddress();
                    if (deliveryAddress != null && deliveryAddress.getId() != null) {
                        transferRxQueueDTO.setZip(deliveryAddress.getZip());

                        transferRxQueueDTO.setDeliveryAddress(AppUtil.getSafeStr(deliveryAddress.getAddress(), "") + "<br/>" + deliveryAddress.getState().getName() + " " + deliveryAddress.getApartment() + " " + deliveryAddress.getZip());
                    } else if (addr != null) {
                        transferRxQueueDTO.setZip(addr.getZip());

                        transferRxQueueDTO.setDeliveryAddress(AppUtil.getSafeStr(addr.getAddress(), "") + "<br/>" + addr.getState().getName() + " " + addr.getApartment() + " " + addr.getZip());
                    }
                    transferRxQueueDTO.setSellingPrice(orders.getPriceIncludingMargins() != null ? orders.getPriceIncludingMargins() : 0d);
                    transferRxQueueDTO.setSellingPriceStr(
                            AppUtil.roundOffNumberToCurrencyFormat(
                                    transferRxQueueDTO.getSellingPrice(), "en", "US"));
                    transferRxQueueDTO.setOriginalPtCopay(orders.getOriginalPtCopay() != null ? orders.getOriginalPtCopay() : 0d);
                    transferRxQueueDTO.setOriginalPtCopayStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getOriginalPtCopay(), "en", "US"));
                    String currentStatus = AppUtil.getSafeStr(orders.getPatientResponse(), "");
                    transferRxQueueDTO.setPatientResponse(currentStatus);
                    String responseRequired = AppUtil.getSafeStr(orders.getResponseRequired(), "");
                    transferRxQueueDTO.setMedicationSpecMsg(responseRequired);
                    String deliverycarrier = AppUtil.getSafeStr(orders.getDeliverycarrier(), "");
                    transferRxQueueDTO.setDeliverycarrier(deliverycarrier);
                    transferRxQueueDTO.setMiles(orders.getMiles());

                    transferRxQueueDTO.setDeliveryFrom(orders.getDeliveryFrom());
                    transferRxQueueDTO.setDeliveryTo(orders.getDeliveryTo());
                    transferRxQueueDTO.setUnderQuotedPrice(orders.getEstimatedPrice() != null ? orders.getEstimatedPrice() : 0d);
                    transferRxQueueDTO.setUnderQuotedPriceStr(AppUtil.roundOffNumberToCurrencyFormat(transferRxQueueDTO.getUnderQuotedPrice(), "en", "US"));
                    transferRxQueueDTO.setPatientResponse(AppUtil.getSafeStr(orders.getPatientResponse(), key));
                    transferRxQueueDTO.setShippingStatus(orderStatus.getName());
                    String drugName = AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(orders.getDrugName()), "");
                    if (detail != null) {
                        //                    String drugName = AppUtil.getOneStringFromBrandAndGeneric(detail.getDrugBasic().getBrandName(),
                        //                            detail.getDrugBasic().getDrugGeneric().getGenericName(),
                        //                            detail.getDrugBasic().getDrugGeneric().getBrandNameOnly());

                        try {
                            if (detail != null) {
                                if (detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() != null
                                        && detail.getDrugBasic().getDrugGeneric().getBrandNameOnly() == 1) {
                                    drugName = detail.getDrugBasic().getBrandName();
                                    transferRxQueueDTO.setBrandOnly(true);
                                } else {
                                    drugName = detail.getDrugBasic().getDrugGeneric().getGenericName();
                                }
                            }
                        } catch (Exception e) {

                        }
                        transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                        transferRxQueueDTO.setStrength(detail.getStrength());
                        transferRxQueueDTO.setDrugType(detail.getDrugForm().getFormDescr());
                        transferRxQueueDTO.setUserInput(false);
                        transferRxQueueDTO.setUserInputStr("");
                        transferRxQueueDTO.setStatus(orderStatus.getName());
                        transferRxQueueDTO.setOrderStatus(orderStatus.getName());

                    } else {
                        transferRxQueueDTO.setOldRxNo(AppUtil.getSafeStr(orders.getOldRxNumber(), ""));
                        transferRxQueueDTO.setPharmacyPhone(AppUtil.getSafeStr(orders.getPharmacyPhone(), ""));
                        transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                        transferRxQueueDTO.setStrength("");
                        transferRxQueueDTO.setDrugType("");
                        transferRxQueueDTO.setUserInput(true);
                        if (AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "").length() > 0
                                || transferRxQueueDTO.getPharmacyPhone().length() > 0
                                || transferRxQueueDTO.getDrugNameWithoutStrength().length() > 0) {
                            transferRxQueueDTO.setUserInputStr(AppUtil.getSafeStr(transferRxQueueDTO.getOldRxNo(), "") + "<br>"
                                    + transferRxQueueDTO.getPharmacyPhone() + "<br>"
                                    + transferRxQueueDTO.getDrugNameWithoutStrength());
                        } else {
                            transferRxQueueDTO.setUserInputStr("");
                        }
                        if (AppUtil.getSafeStr(orders.getImagePath(), "").length() > 0
                                || AppUtil.getSafeStr(orders.getVideo(), "").length() > 0) {
                            transferRxQueueDTO.setStatus("UNVERIFIED");//"UNVERIFIED IMG");
                            transferRxQueueDTO.setSortByStatus(1);
                        } else {
                            transferRxQueueDTO.setStatus("Pending");
                        }

                    }
                    transferRxQueueDTO.setQuantity(AppUtil.getSafeInt(orders.getQty(), 0));
                    
                    
                    transferRxQueueDTO.setRxAcqCost(orders.getRxAcqCost() != null ? orders.getRxAcqCost() : 0d);
                    transferRxQueueDTO.setRxAcqCostStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getRxAcqCost(), "en", "US"));
                    transferRxQueueDTO.setFinalCopay(orders.getFinalPayment() != null ? orders.getFinalPayment() : 0d);
                    transferRxQueueDTO.setFinalCopayStr(AppUtil.roundOffNumberToCurrencyFormat(
                            transferRxQueueDTO.getFinalCopay(), "en", "US"));
                   
                    transferRxQueueDTO.setTracking(AppUtil.getSafeStr(orders.getTraclingno(), ""));
                    String paymentMode = AppUtil.getSafeStr(orders.getFinalPaymentMode(), "");
                    transferRxQueueDTO.setFinalPaymentMode(paymentMode.equalsIgnoreCase("SELF PAY") ? "SELF PAY" : "INS");
                    transferRxQueueDTO.setDeliveryService(pref != null ? AppUtil.getSafeStr(pref.getName(), "-") : "-");
                    transferRxQueueDTO.setDeliveryUrgent(transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                            || transferRxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*"));

                    transferRxQueueDTO.setHandlingFee(orders.getHandLingFee() != null ? orders.getHandLingFee() : 0d);
                    Double totalSellingPrice = transferRxQueueDTO.getFinalCopay() + transferRxQueueDTO.getHandlingFee();
                    transferRxQueueDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(
                            totalSellingPrice, "en", "US"));
                    transferRxQueueDTO.setRequestControlNumber(orders.getId());
                    transferRxQueueDTO.setRequestControlNumber1(transferRxQueueDTO.getRequestControlNumber().substring(0, 8));
                    transferRxQueueDTO.setRequestControlNumber2(transferRxQueueDTO.getRequestControlNumber().substring(8));
                    String requestType = AppUtil.getSafeStr(orders.getOrderType(), "");
                    if (!requestType.equalsIgnoreCase("Refill")) {
                        transferRxQueueDTO.setTextColor("black");
                        if (orders.getOnlineOrder() != null && orders.getOnlineOrder()) {
                            requestType = "Online Order";
                            transferRxQueueDTO.setRequestTypeBgColor("#FFF233");

                        } else if (orders.getIsBottleAvailable() != null && orders.getIsBottleAvailable()) {
                            requestType = "X-FER LABEL SCAN";
                            transferRxQueueDTO.setRequestTypeBgColor("#3a89d7");
                            transferRxQueueDTO.setTextColor("white");
                            transferRxQueueDTO.setSortByMbrReq(1);
                        } else {
                            requestType = "X-FER RX SCAN";
                            transferRxQueueDTO.setRequestTypeBgColor("#8aca14");
                            transferRxQueueDTO.setTextColor("white");
                            transferRxQueueDTO.setSortByMbrReq(2);
                        }
                    }
                    transferRxQueueDTO.setRequestType(requestType);
                    transferRxQueueDTO.setSystemGeneratedRxNumber(AppUtil.getSafeStr(orders.getSystemGeneratedRxNumber(), "-"));
                    transferRxQueueDTO.setPatientResponse(AppUtil.getSafeStr(orders.getPatientResponse(), ""));
                    transferRxQueueList.add(transferRxQueueDTO);
                    patientMap.put(key, transferRxQueueDTO);
                    deliveryPrefMap.put(prefId, deliveryOrders.getId());
                    logger.info("DeliveryOrdersCount# " + deliveryOrdersCount);
                    deliveryOrdersCount++;
                } else {

                    TransferRxQueueDTO transferRxQueueDTO2 = (TransferRxQueueDTO) patientMap.get(key);
                    ReadyToDeliverRxOrders ordersTmp = (ReadyToDeliverRxOrders) arr[10];

                    if (transferRxQueueDTO2 != null && transferRxQueueDTO2.getReadyToDeliverId() != ordersTmp.getId()) {
                        transferRxQueueDTO2.setMultiRx(true);
                        transferRxQueueDTO2.setMultiRxCount(transferRxQueueDTO2.getMultiRxCount() + 1);
                        transferRxQueueDTO2.setMultiRxLabel("Y-" + transferRxQueueDTO2.getMultiRxCount());
                    }

                }
                orderMap.put(deliveryOrders.getId(), deliveryOrders.getId());
            }

        }
        return transferRxQueueList;
    }

    private void getTotalMultiRxCount(PatientProfile patientProfile, ReadyToDeliverRxOrders deliveryOrders, Integer prefId, TransferRxQueueDTO transferRxQueueDTO) {
        List<BusinessObject> lstObj = new ArrayList();
        BusinessObject obj = new BusinessObject("patientProfile.id", patientProfile.getPatientProfileSeqNo(), Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        obj = new BusinessObject("readyToDeliverRxOrders.id", deliveryOrders.getId(), Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        obj = new BusinessObject("deliveryPreference.id", prefId, Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        obj = new BusinessObject("id", transferRxQueueDTO.getOrderId(), Constants.HIBERNATE_NE_OPERATOR);
        lstObj.add(obj);

        Long multiRxCount = (Long) consumerRegistrationDAO.getTotalRecordsByNestedProperty(new Order(), lstObj, "Count(*)");
        if (multiRxCount > 0) {
            transferRxQueueDTO.setMultiRx(true);
            transferRxQueueDTO.setMultiRxCount(multiRxCount.intValue());
            transferRxQueueDTO.setMultiRxLabel("Y-" + transferRxQueueDTO.getMultiRxCount());
        } else {
            transferRxQueueDTO.setMultiRx(false);
            transferRxQueueDTO.setMultiRxCount(1);
            transferRxQueueDTO.setMultiRxLabel("N-1");
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    public String getFilteredReadyToShipRequest(int iDisplayStart, int iDisplayLength, String search, String colName, String sdir, String filter, int sEcho, int userId, Integer pharmacyId, SearchDTO searchDTO, SessionBean sessionBean) {
        String jsonResponse = "";
        try {
            Map<String, String> rxMap = new HashMap<>();
            List<TransferRxQueueDTO> dbList = null;
            if (CommonUtil.isNotEmpty(search) || CommonUtil.isNotEmpty(searchDTO.getOrderStatus())
                    || CommonUtil.isNotEmpty(searchDTO.getMbrReqType()) || CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())
                    || CommonUtil.isNotEmpty(searchDTO.getMbrReqType()) || CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
                List<Object[]> list = consumerRegistrationDAO.getAllFilteredRecords(0, 0, search, colName, sdir, filter, pharmacyId, searchDTO);
                dbList = this.populateAllOrderStatusData(list, userId);
            } else {
                List<Object[]> list = consumerRegistrationDAO.getReadyToShipRecords(0, 0, search, colName, sdir, filter, pharmacyId, searchDTO);
                dbList = populateReadyToShipForPage1(list, userId);
            }
            int rxCount = 0;
            //List<TransferRxQueueDTO> dbList = populateReadyToShipForPage1(list, userId);
            List<TransferRxQueueDTO> newlist = new ArrayList<>();
            for (TransferRxQueueDTO rxQueueDTO : dbList) {
                TransferRxQueueDTO queueDTO = new TransferRxQueueDTO();
                queueDTO.setSortByMbrReq(rxQueueDTO.getSortByMbrReq());
                queueDTO.setPatientResponse(rxQueueDTO.getPatientResponse());
                queueDTO.setSortByStatus(rxQueueDTO.getSortByStatus());
                queueDTO.setPatientId(rxQueueDTO.getPatientId());
                queueDTO.setOrderId(rxQueueDTO.getOrderId());
                queueDTO.setOrderStatus(rxQueueDTO.getOrderStatus());
                rxQueueDTO.setRxCount(rxCount);
                String rxNumber = this.getLastThreeRxNumber(rxQueueDTO);
                rxQueueDTO.setRxControlNumber(rxQueueDTO.getOrderId() + "-" + rxNumber);
                rxCount = rxQueueDTO.getRxCount();
                rxMap.put(rxQueueDTO.getOrderId(), rxNumber);

                String applicationUrl = PropertiesUtil.getProperty("APP_PATH");
                String careGiverDetailUrl = applicationUrl + "/PharmacyPortal/careGiverDetail/";
                queueDTO.setCareGiverDetailUrl(careGiverDetailUrl);
                String rxDetailUrl = applicationUrl + "/ConsumerPortal/rxDetail/";
                queueDTO.setRxDetailUrl(rxDetailUrl);
                this.populateQuestionAnswerId(rxQueueDTO, queueDTO);
                if (rxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                        || rxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*")) {
                    if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1 && rxQueueDTO.getCaregiverRequestApproved() == 0) {
                        queueDTO.setRequestControlNumber1("<a href='" + careGiverDetailUrl
                                + rxQueueDTO.getDependentId() + "?pq=1' style='color: red'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                    } else {

                        if (AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING) && AppUtil.getSafeStr(
                                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                            //queueDTO.setRequestControlNumber1("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.showAnswerRxQuestion(" + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientResponse() + "'," + rxQueueDTO.getPatientId() + ")\" style=\"color:red\">" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + rxQueueDTO.getQuestionId() + "' style='color: red'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        } else {
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color: blue'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        }

                    }
                } else {
                    if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1 && rxQueueDTO.getCaregiverRequestApproved() == 0) {
                        queueDTO.setRequestControlNumber1("<a href='" + careGiverDetailUrl
                                + rxQueueDTO.getDependentId() + "?pq=1' style='color: blue'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + rxNumber + "</a>");
                    } else {
                        if (AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING) && AppUtil.getSafeStr(
                                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                            //queueDTO.setRequestControlNumber1("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.showAnswerRxQuestion(" + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientResponse() + "'," + rxQueueDTO.getPatientId() + ")\" >" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + rxQueueDTO.getQuestionId() + "' style='color: red'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        } else {
                            queueDTO.setRequestControlNumber1("<a href='" + rxDetailUrl + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color: blue'>" + rxQueueDTO.getRequestControlNumber1() + rxQueueDTO.getRequestControlNumber2() + "-" + rxNumber + "</a>");
                        }
                    }

                }
                if (filter.equalsIgnoreCase("Pending")) {
                    populateNewRequestMemberData(rxQueueDTO, queueDTO);
                } else if (filter.equalsIgnoreCase("Pending Review")) {
                    populatePendingReviewData(rxQueueDTO, queueDTO, filter);
                } else if (filter.equalsIgnoreCase("Waiting Pt Response")) {
                    populateWaitingPtResponseData(rxQueueDTO, queueDTO);
                } else if (filter.equalsIgnoreCase("Shipped")) {
                    populateProcessedRequestData(rxQueueDTO, queueDTO);
                } else if (filter.equalsIgnoreCase("Filled")) {
                    populateShippingDeliveryData(queueDTO, rxQueueDTO);
                } else if (filter.equalsIgnoreCase("Cancelled")) {
                    populateCancelledRequestData(queueDTO, rxQueueDTO);
                } else if (filter.equalsIgnoreCase("Response Received")) {
                    populateResponseReceivedData(rxQueueDTO, queueDTO);
                }
                sessionBean.setRxNumberMap(rxMap);
                newlist.add(queueDTO);
            }
            newlist = this.filterRecords(searchDTO, newlist);
            ///////////////////////////////////////////////////////////////
            DataTablesTO<TransferRxQueueDTO> dataTableTo = new DataTablesTO<>();
            Integer totalRecords = newlist.size() < dbList.size() ? newlist.size() : dbList.size();
            iDisplayLength = iDisplayLength + iDisplayStart;
            List<TransferRxQueueDTO> newRecordList = newlist.size() < dbList.size() ? newlist : newlist.subList(Math.max(0, iDisplayStart), Math.min(totalRecords, iDisplayLength));
            //Sort Record
            if (filter.equalsIgnoreCase("Pending") && CommonUtil.isNotEmpty(colName)) {
                if (colName.contains("requestType") || colName.contains("deliveryService") || colName.contains("status")) {
                    newRecordList = newRecordList.stream().sorted(Comparator.comparing(ord -> ord.getSortByMbrReq() == 1 && ord.getSortByStatus() == 1 && ord.getSortByDeliveryPref() == 1)).collect(Collectors.toList());
                }
            }
            dataTableTo.setiTotalDisplayRecords(totalRecords.longValue());
            dataTableTo.setAaData(newRecordList);
            dataTableTo.setiTotalRecords(totalRecords);
            dataTableTo.setsEcho(sEcho);
            jsonResponse = CommonUtil.toJson(dataTableTo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getFilteredNewMemberRequest:: ", e);
        }
        return jsonResponse;
    }

    private void populateWaitingPtResponseData(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) throws Exception {
        populateDeliverySvcReq(rxQueueDTO, queueDTO);
        populateDeliveryService(rxQueueDTO, queueDTO);
        populateMultiRxData(rxQueueDTO, queueDTO);
        String queuePatientDetailPageUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/queuePatientDetailPage/";
        queueDTO.setPatientName("<span class='blueText'><a href=\"" + queuePatientDetailPageUrl + rxQueueDTO.getPatientId() + "/" + rxQueueDTO.getDependentId() + "/tab3\">" + rxQueueDTO.getPatientName() + "</span>");
        queueDTO.setDrugNameWithoutStrength(rxQueueDTO.getDrugNameWithoutStrength());
        queueDTO.setStrength(rxQueueDTO.getStrength());
        queueDTO.setDrugType(rxQueueDTO.getDrugType());
        queueDTO.setQuantity(rxQueueDTO.getQuantity());
        queueDTO.setRxAcqCostStr("<span class=\"text_right\">" + rxQueueDTO.getRxAcqCostStr() + "</span>");
        queueDTO.setFinalCopayStr("<span class=\"text_right\">" + rxQueueDTO.getFinalCopayStr() + "</span>");
        queueDTO.setUnderQuotedPriceStr("<span class=\"text_right\">" + rxQueueDTO.getUnderQuotedPriceStr() + "</span>");
        queueDTO.setMedicationSpecMsg("<span class=\"medication_spec\">" + rxQueueDTO.getMedicationSpecMsg() + "</span>");
        queueDTO.setPatientResponse(rxQueueDTO.getPatientResponse());
    }

    private void populatePendingReviewData(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO, String filter) throws Exception {
        populateRequestType(rxQueueDTO, queueDTO);
        populateDeliverySvcReq(rxQueueDTO, queueDTO);
        queueDTO.setUpdatedBy(rxQueueDTO.getUpdatedBy());
//        queueDTO.setRequestType(rxQueueDTO.getRequestType());
        queueDTO.setStatus(filter);
        populateDeliveryService(rxQueueDTO, queueDTO);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"full_table\">");
        stringBuilder.append("<tr>");

        String queuePatientDetailPageUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/queuePatientDetailPage/";
        stringBuilder.append("<td class=\"blueText width-cnt\">");
        stringBuilder.append("<a href=\"").append(queuePatientDetailPageUrl).append(rxQueueDTO.getPatientId()).append("/").append(rxQueueDTO.getDependentId()).append("/tab2\">");
        stringBuilder.append(rxQueueDTO.getPatientName());
        stringBuilder.append("</a>");
        stringBuilder.append("</td>");

        stringBuilder.append("<td class=\"width-cnt\">");
        stringBuilder.append(rxQueueDTO.getDrugNameWithoutStrength());
        stringBuilder.append("</td>");

        stringBuilder.append("<td class=\"width-cnt\">");
        stringBuilder.append(rxQueueDTO.getStrength());
        stringBuilder.append("</td>");

        stringBuilder.append("<td class=\"width-cnt\">");
        stringBuilder.append(rxQueueDTO.getDrugType());
        stringBuilder.append("</td>");

        stringBuilder.append("<td>");
        stringBuilder.append(rxQueueDTO.getQuantity());
        stringBuilder.append("</td>");

        stringBuilder.append("<td>");
        stringBuilder.append(rxQueueDTO.getDaysSupply());
        stringBuilder.append("</td>");

        stringBuilder.append("<td>");
        stringBuilder.append(rxQueueDTO.getRxAcqCostStr());
        stringBuilder.append("</td>");

        stringBuilder.append("<td>");
        stringBuilder.append(rxQueueDTO.getRefillsRemaining());
        stringBuilder.append("</td>");

        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        queueDTO.setSubInterpretedIImageTable(stringBuilder.toString());
        if (rxQueueDTO.getRxDate() != null) {
            queueDTO.setRxDateStr(DateUtil.dateToString(rxQueueDTO.getRxDate(), "yyyy-MM-dd"));
        }
        populateMultiRxData(rxQueueDTO, queueDTO);
        populatePaymentModeData(rxQueueDTO, queueDTO);
    }

    private void populateCancelledRequestData(TransferRxQueueDTO queueDTO, TransferRxQueueDTO rxQueueDTO) throws Exception {
        String queuePatientDetailPageUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/queuePatientDetailPage/";
        queueDTO.setPatientName("<span class='blueText'><a href=\"" + queuePatientDetailPageUrl + rxQueueDTO.getPatientId() + "/" + rxQueueDTO.getDependentId() + "/tab6\">" + rxQueueDTO.getPatientName() + "</span>");
        populateDeliverySvcReq(rxQueueDTO, queueDTO);
        populateDeliveryService(rxQueueDTO, queueDTO);
        queueDTO.setMemberDependant("MEMBER");
        queueDTO.setPharmacyResponse("REQUEST<br />PROCESSED");
        queueDTO.setStatus("REQUEST<br />CANCELLED");
        // queueDTO.setDrugNameWithoutStrength("<span class=\"redText\">VIBRA TAB<br />(doxycycline Hycl)</span>");
        queueDTO.setDrugNameWithoutStrength("<span class=\"redText\">" + rxQueueDTO.getDrugNameWithoutStrength() + " " + rxQueueDTO.getDrugType() + "</span>");
        queueDTO.setQuantityStr("<span class=\"redText\">" + rxQueueDTO.getQuantity() + "</span>");
        populatePaymentModeData(rxQueueDTO, queueDTO);
        queueDTO.setInsuranceCheck("N");
    }

    private void populatePaymentModeData(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) {
        if (rxQueueDTO.getFinalPaymentMode().equalsIgnoreCase("SELF PAY")) {
            queueDTO.setFinalPaymentMode("<span style=\"color:red\">" + rxQueueDTO.getFinalPaymentMode() + "</span>");
        } else {
            queueDTO.setFinalPaymentMode("<span style=\"color:blue\">" + rxQueueDTO.getFinalPaymentMode() + "</span>");
        }
    }

    private void populateShippingDeliveryData(TransferRxQueueDTO queueDTO, TransferRxQueueDTO rxQueueDTO) throws Exception {
        populateProcessedRequestData(rxQueueDTO, queueDTO);
        String queuePatientDetailPageUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/queuePatientDetailPage/";
        queueDTO.setPatientName("<span class='blueText'><a href=\"" + queuePatientDetailPageUrl + rxQueueDTO.getPatientId() + "/" + rxQueueDTO.getDependentId() + "/tab5\">" + rxQueueDTO.getPatientName() + "</span>");
        queueDTO.setRxAcqCostStr("<span class=\"text_right\">" + rxQueueDTO.getRxAcqCostStr() + "</span>");
        queueDTO.setSellingPriceStr("<span class=\"text_right\">" + rxQueueDTO.getSellingPriceStr() + "</span>");
        queueDTO.setOriginalPtCopayStr("<span class=\"text_right\">" + rxQueueDTO.getOriginalPtCopayStr() + "</span>");
        queueDTO.setTracking("<span style='text-align: center;'>" + rxQueueDTO.getTracking() + "</span>");
        queueDTO.setShippingStatus(rxQueueDTO.getShippingStatus());
    }

    private void populateProcessedRequestData(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) throws Exception {
        populateDeliverySvcReq(rxQueueDTO, queueDTO);
        populateDeliveryService(rxQueueDTO, queueDTO);
        populateMultiRxData(rxQueueDTO, queueDTO);
        queueDTO.setSystemGeneratedRxNumber(rxQueueDTO.getSystemGeneratedRxNumber());
        String queuePatientDetailPageUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/queuePatientDetailPage/";
        if (rxQueueDTO.getStatus().equalsIgnoreCase(Constants.ORDER_STATUS.PENDING) && AppUtil.getSafeStr(
                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
            queueDTO.setPatientName("<span class='redText'><a href=\"" + queuePatientDetailPageUrl + rxQueueDTO.getPatientId() + "/" + rxQueueDTO.getDependentId() + "/tab4\">" + rxQueueDTO.getPatientName() + "</span>");
        } else {
            queueDTO.setPatientName("<span class='blueText'><a href=\"" + queuePatientDetailPageUrl + rxQueueDTO.getPatientId() + "/" + rxQueueDTO.getDependentId() + "/tab4\">" + rxQueueDTO.getPatientName() + "</span>");
        }

        queueDTO.setDrugNameWithoutStrength(rxQueueDTO.getDrugNameWithoutStrength());
        queueDTO.setDeliveryAddress(rxQueueDTO.getDeliveryAddress());
        queueDTO.setZip(rxQueueDTO.getZip());
        queueDTO.setMiles("<span class='redText'>" + AppUtil.getSafeStr(rxQueueDTO.getMiles(), "-") + "</span>");
        queueDTO.setHandlingFeeStr("<span class='text_right'>" + CommonUtil.getDecimalFormat(rxQueueDTO.getHandlingFee()) + "</span>");
        queueDTO.setPickUpPaperRx("-");
        if (CommonUtil.isNotEmpty(rxQueueDTO.getDeliverycarrier())) {
            queueDTO.setDeliverycarrier("<span style=\"color:red\">" + rxQueueDTO.getDeliverycarrier() + "</span>");
        } else if (rxQueueDTO.getDeliveryFrom() != null && rxQueueDTO.getDeliveryTo() != null) {
            queueDTO.setDeliverycarrier("<span style=\"color:red\">" + rxQueueDTO.getDeliveryFrom() + "-" + rxQueueDTO.getDeliveryTo() + "</span>");
        } else {
            queueDTO.setDeliverycarrier("-");
        }
        queueDTO.setInsuranceCheck("&nbsp;");
        queueDTO.setShippingStatus(rxQueueDTO.getShippingStatus());
        queueDTO.setFinalCopayStr(rxQueueDTO.getFinalCopayStr());
        queueDTO.setRxAcqCostStr("<span style=\"color:red\">" + rxQueueDTO.getRxAcqCostStr() + "</span>");
        queueDTO.setSellingPriceStr(rxQueueDTO.getFinalPaymentStr());
    }

    private void populateMultiRxData(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) {
        StringBuilder builder;
        if (!rxQueueDTO.getMultiRx()) {
            queueDTO.setMultiRxLabel(rxQueueDTO.getMultiRxLabel());
        } else {
            builder = new StringBuilder();
            builder.append("<span style=\"color:red\">");
            builder.append("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.openMultiRxModel('multiRxModal'," + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientName() + "'," + rxQueueDTO.getPatientId() + ")\" style=\"color:red\">");
            builder.append(rxQueueDTO.getMultiRxLabel());
            builder.append("</a>");
            builder.append("</span>");
            queueDTO.setMultiRxLabel(builder.toString());
        }
    }

    private void populateDeliverySvcReq(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) throws Exception {
        String date = DateUtil.dateToString(rxQueueDTO.getUpdatedDate(), "yyyy-MM-");
        String time = DateUtil.dateToString(rxQueueDTO.getUpdatedDate(), "HH:mm");
        String day = DateUtil.dateToString(rxQueueDTO.getUpdatedDate(), "dd");
        if (rxQueueDTO.getStatus().equalsIgnoreCase(Constants.ORDER_STATUS.PENDING) && AppUtil.getSafeStr(
                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
            queueDTO.setReqPosted("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + rxQueueDTO.getQuestionId() + "'>" + date + "<br><span style=\"color:red\"><strong>" + day + "</strong></span>@" + time + "</a>");
        } else {
            queueDTO.setReqPosted("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "'>" + date + "<br><span style=\"color:red\"><strong>" + day + "</strong></span>@" + time + "</a>");
        }
//        if (rxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day") || rxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*")) {
//            queueDTO.setReqPosted("<a href='/ConsumerPortal/rxDetail/" + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color: red'>" + date + "@<strong>" + time + "</strong></a>");
//        } else {
//            queueDTO.setReqPosted("<a href='/ConsumerPortal/rxDetail/" + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color: blue'>" + date + "@<strong>" + time + "</strong></a>");
//        }
    }

    private void populateNewRequestMemberData(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) throws Exception {
        String date = DateUtil.dateToString(rxQueueDTO.getReceivedDate(), "yyyy-MM-");
        String day = DateUtil.dateToString(rxQueueDTO.getReceivedDate(), "dd");
        String time = DateUtil.dateToString(rxQueueDTO.getReceivedDate(), "HH:mm");
        if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1 && rxQueueDTO.getCaregiverRequestApproved() == 0) {
            queueDTO.setReqPosted("<a href='" + queueDTO.getCareGiverDetailUrl()
                    + rxQueueDTO.getDependentId() + "?pq=1'>" + date + "<br><span style=\"color:red\"><strong>" + day + "</span></strong>@" + time + "</a>");
        } else if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
            queueDTO.setReqPosted("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + rxQueueDTO.getQuestionId() + "'>" + date + "<br><span style=\"color:red\"><strong>" + day + "</span></strong>@" + time + "</a>");
        } else {
            queueDTO.setReqPosted("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "'>" + date + "<br><span style=\"color:red\"><strong>" + day + "</span></strong>@" + time + "</a>");
        }

        if (rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED")) {
            queueDTO.setStatus("<span style='color:red;'>" + rxQueueDTO.getStatus() + "</span>");
        } else {
            queueDTO.setStatus(rxQueueDTO.getStatus());
        }

        if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1
                && rxQueueDTO.getCaregiverRequestApproved() == 0) {
            String url = "<span  style='background:" + rxQueueDTO.getRequestTypeBgColor() + "; color:" + rxQueueDTO.getTextColor() + "'><a href='/PharmacyPortal/careGiverDetail/" + rxQueueDTO.getDependentId() + "?pq=1' style='color:" + rxQueueDTO.getTextColor() + "'>" + rxQueueDTO.getRequestType() + "</a></span>";
            queueDTO.setRequestType(url);
        } else if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
            String reqType = rxQueueDTO.getQuestionId() > 0 ? "MBR ASKS A QUESTION" : rxQueueDTO.getRequestType();
            String url = "<span  style='color:red;'>"
                    + "<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + rxQueueDTO.getQuestionId() + "' style='color:red'>" + reqType + "</a></span>";
            queueDTO.setRequestType(url);
            queueDTO.setStatus("<span style='color:red;'>" + rxQueueDTO.getStatus() + "</span>");
        } else {
            String url = "<span  style='background:" + rxQueueDTO.getRequestTypeBgColor() + "; color:" + rxQueueDTO.getTextColor() + "'>"
                    + "<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color:" + rxQueueDTO.getTextColor() + "'>" + rxQueueDTO.getRequestType() + "</a></span>";
            queueDTO.setRequestType(url);
        }

        ///////////////////////////////////////////////////////////////////////////////////
        String cls = "blueText", style = "";
        if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
            cls = "redText";
            style = "color:red;text-decoration:none;";
        }
        if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1
                && rxQueueDTO.getCaregiverRequestApproved() == 0) {
            queueDTO.setPatientName("<span class='" + cls + "'><a href=\"" + queueDTO.getCareGiverDetailUrl() + rxQueueDTO.getDependentId() + "?pq=1\" style='" + style + "'>" + rxQueueDTO.getPatientName() + "</span>");
        } //////////////////////////////////////////////////////////////////////////////////
        else {
            String queuePatientDetailPageUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/queuePatientDetailPage/";
            queueDTO.setPatientName("<span class='" + cls + "'><a href=\"" + queuePatientDetailPageUrl + rxQueueDTO.getPatientId() + "/" + rxQueueDTO.getDependentId() + "/tab1\" style='" + style + "'>" + rxQueueDTO.getPatientName() + "</span>");
        }
        StringBuilder builder = null;
        if (!rxQueueDTO.isUserInput()) {
            builder = new StringBuilder();
            if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                    queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                builder.append("<strong style=\"color:red\"> " + rxQueueDTO.getDrugNameWithoutStrength() + "&nbsp;" + rxQueueDTO.getStrength() + "&nbsp;" + rxQueueDTO.getDrugType() + "</strong>");
            } else {
                builder.append("<strong> " + rxQueueDTO.getDrugNameWithoutStrength() + "&nbsp;" + rxQueueDTO.getStrength() + "&nbsp;" + rxQueueDTO.getDrugType() + "</strong>");
            }
            if (rxQueueDTO.isBrandOnly()) {
                builder.append("<small style=\"color:red\">*BRAND NAME*</small>");
            }
            queueDTO.setDrugNameWithoutStrength(builder.toString());
        } else {
            builder = new StringBuilder();
            builder.append("<span class='redText'>");
            if (CommonUtil.isNotEmpty(rxQueueDTO.getUserInputStr())) {
                builder.append("<span class=\"icon_medication\"></span>");
            }
            builder.append(rxQueueDTO.getUserInputStr());
            builder.append("</span>");
            queueDTO.setDrugNameWithoutStrength(builder.toString());
        }
        populateDeliveryService(rxQueueDTO, queueDTO);
        if (!rxQueueDTO.getMultiRx()) {
            queueDTO.setMultiRxLabel(rxQueueDTO.getMultiRxLabel());
        } else {
            builder = new StringBuilder();
            builder.append("<span style=\"color:red\">");
            builder.append("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.openMultiRxModel('multiRxModal'," + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientName() + "'," + rxQueueDTO.getPatientId() + ")\" style=\"color:red\">");
            builder.append(rxQueueDTO.getMultiRxLabel());
            builder.append("</a>");
            builder.append("</span>");
            queueDTO.setMultiRxLabel(builder.toString());
        }
        if (rxQueueDTO.getFinalPaymentMode().equalsIgnoreCase("SELF PAY")) {
            builder = new StringBuilder();
            if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                    queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                builder.append("<span style=\"color:red\">");
            } else {
                builder.append("<span style=\"color:blue\">");
            }
            builder.append("N");
            builder.append("</span>");
            queueDTO.setFinalPaymentMode(builder.toString());
        } else {
            builder = new StringBuilder();
            builder.append("<span style=\"color:red\">");
            builder.append("Y");
            builder.append("</span>");
            queueDTO.setFinalPaymentMode(builder.toString());
        }
        if (CommonUtil.isNotEmpty(rxQueueDTO.getAllergies())) {
            builder = new StringBuilder();
            if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                    queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                builder.append("<span style=\"color:red\">");
            } else {
                builder.append("<span style=\"color:black\">");
            }
            builder.append("Y");
            builder.append("</span>");
            queueDTO.setAllergies(builder.toString());
        } else {
            builder = new StringBuilder();
            builder.append("<span style=\"color:red\">");
            builder.append("N");
            builder.append("</span>");
            queueDTO.setAllergies(builder.toString());
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void populateResponseReceivedData(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) throws Exception {
        String date = DateUtil.dateToString(rxQueueDTO.getReceivedDate(), "yyyy-MM-");
        String day = DateUtil.dateToString(rxQueueDTO.getReceivedDate(), "dd");
        String time = DateUtil.dateToString(rxQueueDTO.getReceivedDate(), "HH:mm");
        if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1 && rxQueueDTO.getCaregiverRequestApproved() == 0) {
            queueDTO.setReqPosted("<a href='" + queueDTO.getCareGiverDetailUrl()
                    + rxQueueDTO.getDependentId() + "?pq=1'>" + date + "<br><span style=\"color:red\"><strong>" + day + "</span></strong>@" + time + "</a>");
        } else {
            queueDTO.setReqPosted("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "'>" + date + "<br><span style=\"color:red\"><strong>" + day + "</span></strong>@" + time + "</a>");
        }

        populateDeliveryService(rxQueueDTO, queueDTO);
        //this.populateQuestionAnswerId(rxQueueDTO, queueDTO);
        queueDTO.setPatientResponse("<span>" + rxQueueDTO.getPatientResponse() + "</span>");
        ///////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////
        if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1
                && rxQueueDTO.getCaregiverRequestApproved() == 0) {
            queueDTO.setPatientName("<span class='blueText'><a href=\"" + queueDTO.getCareGiverDetailUrl() + rxQueueDTO.getDependentId() + "?pq=1\">" + rxQueueDTO.getPatientName() + "</span>");
        } //////////////////////////////////////////////////////////////////////////////////
        else {
            String queuePatientDetailPageUrl = PropertiesUtil.getProperty("APP_PATH") + "/ConsumerPortal/queuePatientDetailPage/";
            queueDTO.setPatientName("<span class='blueText'><a href=\"" + queuePatientDetailPageUrl + rxQueueDTO.getPatientId() + "/" + rxQueueDTO.getDependentId() + "/tab1\">" + rxQueueDTO.getPatientName() + "</span>");
        }
        //////////////////////////////////////////////////////////////////
        StringBuilder builder = null;
        if (!rxQueueDTO.getMultiRx()) {
            queueDTO.setMultiRxLabel(rxQueueDTO.getMultiRxLabel());
        } else {
            builder = new StringBuilder();
            builder.append("<span style=\"color:red\">");
            builder.append("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.openMultiRxModel('multiRxModal'," + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientName() + "'," + rxQueueDTO.getPatientId() + ")\" style=\"color:red\">");
            builder.append(rxQueueDTO.getMultiRxLabel());
            builder.append("</a>");
            builder.append("</span>");
            queueDTO.setMultiRxLabel(builder.toString());
        }
        ///////////////////////////////////////////////////////////////////

        if (!rxQueueDTO.isUserInput()) {
            builder = new StringBuilder();
            builder.append("<strong> " + rxQueueDTO.getDrugNameWithoutStrength() + "&nbsp;" + rxQueueDTO.getStrength() + "&nbsp;" + rxQueueDTO.getDrugType() + "</strong>");
            if (rxQueueDTO.isBrandOnly()) {
                builder.append("<small style=\"color:red\">*BRAND NAME*</small>");
            }
            queueDTO.setDrugNameWithoutStrength(builder.toString());
        } else {
            builder = new StringBuilder();
            builder.append("<span class='redText'>");
            if (CommonUtil.isNotEmpty(rxQueueDTO.getUserInputStr())) {
                builder.append("<span class=\"icon_medication\"></span>");
            }
            builder.append(rxQueueDTO.getUserInputStr());
            builder.append("</span>");
            queueDTO.setDrugNameWithoutStrength(builder.toString());
        }

        if (rxQueueDTO.getFinalPaymentMode().equalsIgnoreCase("SELF PAY")) {
            builder = new StringBuilder();
            builder.append("<span style=\"color:blue\">");
            builder.append("SELF");
            builder.append("</span>");
            queueDTO.setFinalPaymentMode(builder.toString());
        } else {
            builder = new StringBuilder();
            builder.append("<span style=\"color:red\">");
            builder.append("INS");
            builder.append("</span>");
            queueDTO.setFinalPaymentMode(builder.toString());
        }

    }

    private void populateQuestionAnswerId(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) {
        // String rxNumber = this.getLastThreeRxNumber(rxQueueDTO);
        if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || rxQueueDTO.getStatus().equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(rxQueueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
            //queueDTO.setRequestControlNumber1("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color: red'>" + rxQueueDTO.getRequestControlNumber1() + "<br>" + rxQueueDTO.getRequestControlNumber2() + rxNumber + "</a>");
            List lstQuestions = this.consumerRegistrationDAO.findByNestedProperty(new QuestionAnswer(),
                    "order.id", rxQueueDTO.getOrderId(), Constants.HIBERNATE_EQ_OPERATOR, "id", Constants.HIBERNATE_DESC_ORDER);
            if (lstQuestions != null && lstQuestions.size() > 0) {
                QuestionAnswer question = (QuestionAnswer) lstQuestions.get(0);
                if (CommonUtil.isNullOrEmpty(question.getAnswer()) && question.getAnswerTime() == null) {
//                    rxQueueDTO.setQuestionId(question.getId());
                    rxQueueDTO.setPatientResponse(AppUtil.getSafeStr(question.getQuestion(), ""));
                    //////////////////////////////////////////////////////////////////////////////////
                    if (rxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day")
                            || rxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*")) {

                        // queueDTO.setRequestControlNumber1("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.showAnswerRxQuestion(" + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientResponse() + "'," + rxQueueDTO.getPatientId() + "," + question.getId() + ")\" style=\"color:red\">" + rxQueueDTO.getRequestControlNumber1() + "<br>" + rxQueueDTO.getRequestControlNumber2() + "</a>");
                        queueDTO.setRequestControlNumber1("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + question.getId() + "' style='color: red'>" + rxQueueDTO.getRxControlNumber() + "</a>");
                    } else {
                        //queueDTO.setRequestControlNumber1("<a href=\"#\" onclick=\"javascript:window.pharmacyNotification.showAnswerRxQuestion(" + rxQueueDTO.getOrderId() + ",'" + rxQueueDTO.getPatientResponse() + "'," + rxQueueDTO.getPatientId() + "," + question.getId() + ")\" >" + rxQueueDTO.getRequestControlNumber1() + "<br>" + rxQueueDTO.getRequestControlNumber2() + "</a>");
                        queueDTO.setRequestControlNumber1("<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "?questionId=" + question.getId() + "' style='color: red'>" + rxQueueDTO.getRxControlNumber() + "</a>");
                    }
                }
                /////////////////////////////////////////////////////////////////////////////////
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    private void populateDeliveryService(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) {
        StringBuilder builder;
        if (rxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day") || rxQueueDTO.getDeliveryService().equalsIgnoreCase("Next Day*")) {
            builder = new StringBuilder();
            builder.append("<span style=\"color:red\">");
            builder.append(rxQueueDTO.getDeliveryService());
            builder.append("</span>");
            builder.append("</br>");
            builder.append("<span style=\"color:red\"><i class=\"fa fa-exclamation\" aria-hidden=\"true\"></i></span>");
            queueDTO.setDeliveryService(builder.toString());
            queueDTO.setSortByDeliveryPref(rxQueueDTO.getDeliveryService().equalsIgnoreCase("Same Day") ? 1 : 0);

        } else if (rxQueueDTO.getDeliveryService().equalsIgnoreCase("Pick Up at Pharmacy")) {
            builder = new StringBuilder();
            if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                    queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                builder.append("<span style=\"color:red\">");
            } else {
                builder.append("<span style=\"color:blue\">");
            }
            builder.append(rxQueueDTO.getDeliveryService());
            builder.append("</span>");
            queueDTO.setDeliveryService(builder.toString());
        } else {
            builder = new StringBuilder();
            if ((rxQueueDTO.getStatus().equalsIgnoreCase("UNVERIFIED") || AppUtil.getSafeStr(rxQueueDTO.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) && AppUtil.getSafeStr(
                    queueDTO.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION)) {
                builder.append("<span style=\"color:red\">");
            } else {
                builder.append("<span style=\"color:black\">");
            }
            builder.append(rxQueueDTO.getDeliveryService());
            builder.append("</span>");
            queueDTO.setDeliveryService(builder.toString());
        }

    }

    private void populateRequestType(TransferRxQueueDTO rxQueueDTO, TransferRxQueueDTO queueDTO) {
        try {
            if (rxQueueDTO.getCareGiverRequest() != null && rxQueueDTO.getCareGiverRequest() == 1
                    && rxQueueDTO.getCaregiverRequestApproved() == 0) {
                String url = "<span  style='background:" + rxQueueDTO.getRequestTypeBgColor() + "; color:" + rxQueueDTO.getTextColor() + "'><a href='/PharmacyPortal/careGiverDetail/" + rxQueueDTO.getDependentId() + "?pq=1' style='color:" + rxQueueDTO.getTextColor() + "'>" + rxQueueDTO.getRequestType() + "</a></span>";
                queueDTO.setRequestType(url);
            } else {
                String url = "<span  style='background:" + rxQueueDTO.getRequestTypeBgColor() + "; color:" + rxQueueDTO.getTextColor() + "'>"
                        + "<a href='" + queueDTO.getRxDetailUrl() + rxQueueDTO.getOrderId() + "/" + rxQueueDTO.getPatientId() + "' style='color:" + rxQueueDTO.getTextColor() + "'>" + rxQueueDTO.getRequestType() + "</a></span>";
                queueDTO.setRequestType(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////
    public void postOrderToIOMNI(String orderId, String totalPayment) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("rxdemo", "WaSgzjvD7yEHk7w");
        provider.setCredentials(AuthScope.ANY, credentials);
        HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build(); //Use this instead 

        try {

            HttpPost request = new HttpPost("https://www.iomni-llc.com/iOmniAPI/Order/Order");
            /*StringEntity params = new StringEntity("{\"clientCompanyID\": \"4767\",\"businessLineID\": \"1000\","
             + "\"transmissionTime\": \"2017-12-19T06:32:31.4141038-05:00\","
             + " \"deliveries\": [null,{"
             + " \"orderID\": -1, \"customerOrderID\": \"\",\"firstName\": \"\",\n"
             + "      \"lastName\": \"\",\n"
             + "      \"street1\": \"\",\n"
             + "      \"street2\": \"\",\n"
             + "      \"city\": \"\",\n"
             + "      \"state\": \"\",\n"
             + "      \"postalCode\": \"\",\n"
             + "      \"phone\": \"\",\n"
             + "      \"deliveryID\": -1,\n"
             + "      \"customerDeliveryID\": null,\n"
             + "      \"clientCustomerID\": null,\n"
             + "      \"smsEnabled\": false,\n"
             + "      \"serviceDate\": null,\n"
             + "      \"serviceCompleted\": null,\n"
             + "      \"sweepID\": null,\n"
             + "      \"deliveryWindowID\": null,\n"
             + "      \"packages\": 0,\n"
             + "      \"items\": 0,\n"
             + "      \"pieceType\": 0,\n"
             + "      \"serviceLevelID\": null,\n"
             + "      \"cases\": null,\n"
             + "      \"weight\": 0.0,\n"
             + "      \"length\": null,\n"
             + "      \"width\": null,\n"
             + "      \"height\": null,\n"
             + "      \"status\": null,\n"
             + "      \"codExpected\": null,\n"
             + "      \"locationCode\": null,\n"
             + "      \"notes\": null,\n"
             + "      \"signature\": null,\n"
             + "      \"serviceTotalCost\": null,\n"
             + "      \"miles\": null,\n"
             + "      \"refrigerated\": null\n"
             + "    }\n"
             + "  ]}");*/

            request.addHeader("content-type", "application/xml");// "application/x-www-form-urlencoded");
//            request.addHeader("Authorization","Basic WaSgzjvD7yEHk7w");
//            request.addHeader("Key","WaSgzjvD7yEHk7w");
            StringEntity entity = populateJsonDataForIomni(orderId, totalPayment);
            System.out.println("iOmni request data:: " + IOUtils.toString(entity.getContent()));
            request.setEntity(entity);
            HttpResponse response = httpClient.execute(request);
            System.out.println("Alright here is response " + response.getStatusLine());
            //handle response here...

        } catch (Exception ex) {
            ex.printStackTrace();
            //handle exception here

        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown(); 
        }

    }

    /////////////////////////////////////////////
    //////////////////////////////////////////////
    public void postOrderToIOMNI() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("", "WaSgzjvD7yEHk7w");
        provider.setCredentials(AuthScope.ANY, credentials);
        HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build(); //Use this instead 

        try {

            HttpPost request = new HttpPost("https://www.iomni-llc.com/iOmniAPI/Order/Order");
            StringEntity params = new StringEntity("{\"clientCompanyID\": \"4767\",\"businessLineID\": \"1000\","
                    + "\"transmissionTime\": \"2017-12-19T06:32:31.4141038-05:00\","
                    + " \"deliveries\": [null,{"
                    + " \"orderID\": -1, \"customerOrderID\": \"\",\"firstName\": \"\",\n"
                    + "      \"lastName\": \"\",\n"
                    + "      \"street1\": \"\",\n"
                    + "      \"street2\": \"\",\n"
                    + "      \"city\": \"\",\n"
                    + "      \"state\": \"\",\n"
                    + "      \"postalCode\": \"\",\n"
                    + "      \"phone\": \"\",\n"
                    + "      \"deliveryID\": -1,\n"
                    + "      \"customerDeliveryID\": null,\n"
                    + "      \"clientCustomerID\": null,\n"
                    + "      \"smsEnabled\": false,\n"
                    + "      \"serviceDate\": null,\n"
                    + "      \"serviceCompleted\": null,\n"
                    + "      \"sweepID\": null,\n"
                    + "      \"deliveryWindowID\": null,\n"
                    + "      \"packages\": 0,\n"
                    + "      \"items\": 0,\n"
                    + "      \"pieceType\": 0,\n"
                    + "      \"serviceLevelID\": null,\n"
                    + "      \"cases\": null,\n"
                    + "      \"weight\": 0.0,\n"
                    + "      \"length\": null,\n"
                    + "      \"width\": null,\n"
                    + "      \"height\": null,\n"
                    + "      \"status\": null,\n"
                    + "      \"codExpected\": null,\n"
                    + "      \"locationCode\": null,\n"
                    + "      \"notes\": null,\n"
                    + "      \"signature\": null,\n"
                    + "      \"serviceTotalCost\": null,\n"
                    + "      \"miles\": null,\n"
                    + "      \"refrigerated\": null\n"
                    + "    }\n"
                    + "  ]}");
            request.addHeader("content-type", "application/xml");// "application/x-www-form-urlencoded");
//            request.addHeader("Authorization","Basic WaSgzjvD7yEHk7w");
//            request.addHeader("Key","WaSgzjvD7yEHk7w");

            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println("Alright here is response " + response.getStatusLine());
            //handle response here...

        } catch (Exception ex) {
            ex.printStackTrace();
            //handle exception here

        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown(); 
        }

    }
    /////////////////////////////////////////////

    private StringEntity populateJsonDataForIomni(String orderId, String totalPayment) throws UnsupportedEncodingException, Exception {
        StringEntity entity = null;
        try {
            Order order = (Order) orderDao.findByPropertyUnique(new Order(), "id", orderId, Constants.HIBERNATE_EQ_OPERATOR);
            String str = null;
            // create the albums object
            JsonObject albums = new JsonObject();
            // add a property calle title to the albums object
            albums.addProperty("clientCompanyID", "1");
            albums.addProperty("businessLineID", "1");
            albums.addProperty("transmissionTime", DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
            // create an array called datasets
            JsonArray datasets = new JsonArray();
            // create a dataset
            JsonObject dataset = new JsonObject();
            dataset.addProperty("orderID", -1);
            dataset.addProperty("customerOrderID", orderId);
            dataset.addProperty("firstName", order.getFirstName());
            dataset.addProperty("lastName", order.getLastName());
            dataset.addProperty("street1", order.getStreetAddress());
            dataset.addProperty("street2", "");
            dataset.addProperty("city", order.getCity());
            dataset.addProperty("state", order.getState());
            dataset.addProperty("postalCode", "");
            dataset.addProperty("phone", order.getPatientProfile().getMobileNumber());
            dataset.addProperty("deliveryID", -1);
            dataset.addProperty("customerDeliveryID", str);
            dataset.addProperty("clientCustomerID", str);
            dataset.addProperty("smsEnabled", false);
            dataset.addProperty("serviceDate", str);
            dataset.addProperty("serviceCompleted", str);
            dataset.addProperty("sweepID", str);
            dataset.addProperty("deliveryWindowID", str);
            dataset.addProperty("packages", 0);
            dataset.addProperty("items", 0);
            dataset.addProperty("pieceType", 0);
            dataset.addProperty("serviceLevelID", str);
            dataset.addProperty("cases", str);
            dataset.addProperty("weight", 0.0);
            dataset.addProperty("length", str);
            dataset.addProperty("width", str);
            dataset.addProperty("height", str);
            dataset.addProperty("status", str);
            dataset.addProperty("codExpected", str);
            dataset.addProperty("locationCode", str);
            dataset.addProperty("notes", str);
            dataset.addProperty("signature", str);
            dataset.addProperty("serviceTotalCost", str);
            dataset.addProperty("miles", str);
            dataset.addProperty("refrigerated", str);
            //datasets.add(null);
            datasets.add(dataset);
            albums.add("deliveries", datasets);
            // create the gson using the GsonBuilder. Set pretty printing on. Allow
            // serializing null and set all fields to the Upper Camel Case
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            entity = new StringEntity(gson.toJson(albums));
        } catch (Exception e) {
            logger.error("Exception#", e);
        }

        return entity;
    }

    public void getpasswordByPharmacyUserList() {
        PharmacyUser phUser = new PharmacyUser();
        try {
            List<PharmacyUser> pharmacyuserList = consumerRegistrationDAO.getPharmacyUsersList();

            for (PharmacyUser user : pharmacyuserList) {

                user.setPassword(CommonUtil.bCryptPasswordEncoder(user.getPassword()));
                if (user.getUserStartDate() == null) {
                    user.setUserStartDate(new Date());
                }
                consumerRegistrationDAO.updatePharmacyUserPsw(user.getId(), user.getPassword());
//                    consumerRegistrationDAO.saveOrUpdate(user);
//                    consumerRegistrationDAO.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: ConsumerRegistrationService -> getpasswordByPharmacyUserList", e);
        }
//        return psw;

    }

    public String populateSameDayShippingRxOrders(String orderIds) {
        String json = "";
        try {
            List<String> lst = Arrays.asList(orderIds.split(","));
            List<OrderDetailDTO> list = new ArrayList<>();

            List<Order> listOfOrders = consumerRegistrationDAO.getOrderListByIds(lst);
            for (Order order : listOfOrders) {
                OrderDetailDTO orderDetailDTO = this.populateOrderDetails(order, 0);
                Double handlingFee = order.getHandLingFee() != null ? order.getHandLingFee() : 0d;
//                orderDetailDTO.setHandlingFeeStr(AppUtil.roundOffNumberToCurrencyFormat(handlingFee, "en", "Us"));

                Double finalPyment = order.getFinalPayment() != null ? order.getFinalPayment() : 0d;
//                orderDetailDTO.setPaymentExcludingDeliveryStr(AppUtil.roundOffNumberToCurrencyFormat(finalPyment, "en", "Us"));

                // finalPyment = finalPyment + handlingFee;
                orderDetailDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(finalPyment, "en", "Us"));

                list.add(orderDetailDTO);
            }
            json = JsonResponseComposer.composeSuccessResponse(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: populateSameDayShippingRxOrders ", e);
        }
        return json;
    }

    public List<OrderDetailDTO> getSameDayAndFilledStatusOrdersByPatientId(Integer patientId) {
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            List<Order> dbOrdersList = consumerRegistrationDAO.getFilledStatusOrdersByPatientId(patientId);
            for (Order order : dbOrdersList) {
                OrderDetailDTO orderDetailDTO = this.populateOrderDetails(order, 0);
                orderDetailDTO.setOrderDate(DateUtil.dateToString(order.getCreatedOn(), Constants.USA_DATE_TIME_SECOND_FORMATE));
                list.add(orderDetailDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: getSameDayAndFilledStatusOrdersByPatientId ", e);
        }
        return list;
    }

    public OrderDetailDTO getReadyToDeliverOrdersByPatientId(Integer patientId, Integer readyToDeliverRxOrdersId, Integer deliveryPrefId) {
        OrderDetailDTO detail = new OrderDetailDTO();
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            Double rxCost = 0d, finalPayment = 0d, shippingCost = 0d, totalRxCost = 0d, totalShippingCost = 0d, grandTotal = 0d;
            List<Order> listOfOrders = consumerRegistrationDAO.getReadyToDeliverOrdersByIdAndPatientIdAndPrefId(patientId, readyToDeliverRxOrdersId, deliveryPrefId, null);
            int count = 0;

            for (Order order : listOfOrders) {
                OrderDetailDTO detailDTO = new OrderDetailDTO();
                detailDTO.setId(order.getId());
                String date = DateUtil.dateToString(order.getCreatedOn(), Constants.DATE_FORMATE_SHORT);
                String time = DateUtil.dateToString(order.getCreatedOn(), "HH:mm:ss");
                detail.setOrderDate(date + " @ " + time);
                detailDTO.setOrderDate(date + "<br/> @ " + time);
                detailDTO.setCreatedOn(order.getCreatedOn());
                this.populateOrderDrugDetail(order, detailDTO);
                detailDTO.setDrugType(order.getDrugType());
                detailDTO.setStrength(order.getStrength());
                detailDTO.setQty(AppUtil.getSafeStr(order.getQty(), ""));
                detailDTO.setDaysSupply(order.getDaysSupply() != null ? order.getDaysSupply() : 0);
                if (order.getDeliveryPreference() != null) {
//                    detailDTO.setDeliveryPreferenceId(order.getDeliveryPreference().getId());
//                    detailDTO.setDeliveryPreferencesName(order.getDeliveryPreference().getName());
                } else {
//                    detailDTO.setDeliveryPreferenceId(0);
//                    detailDTO.setDeliveryPreferencesName("-");
                }
                rxCost = order.getPaymentExcludingDelivery() != null ? order.getPaymentExcludingDelivery() : 0.0d;
                detailDTO.setFinalPayment(rxCost);
                detailDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(rxCost, "en", "us"));
                shippingCost = order.getHandLingFee() != null ? order.getHandLingFee() : 0.0d;
                if (count > 0) {
                    shippingCost = 0d;
                }
//                detailDTO.setHandlingFeeStr(AppUtil.roundOffNumberToCurrencyFormat(shippingCost, "en", "us"));
                finalPayment = rxCost + shippingCost;
                detailDTO.setTotalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(finalPayment, "en", "us"));

                totalRxCost = totalRxCost + rxCost;
//                detailDTO.setTotalRxCost(AppUtil.roundOffNumberToCurrencyFormat(totalRxCost, "en", "us"));
                totalShippingCost = totalShippingCost + shippingCost;
//                detailDTO.setTotalShippingCost(AppUtil.roundOffNumberToCurrencyFormat(totalShippingCost, "en", "us"));
                grandTotal = grandTotal + finalPayment;
//                detailDTO.setGrandPayment(AppUtil.roundOffNumberToCurrencyFormat(grandTotal, "en", "us"));

                detail.setZipCode(order.getZip());
                if (CommonUtil.isNotEmpty(order.getApartment())) {
                    logger.info("Appartmnet is: " + order.getApartment());
//                    detail.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                            + AppUtil.getSafeStr(order.getApartment(), "") + " " + AppUtil.getSafeStr(order.getCity(), "")
//                            + " " + AppUtil.getSafeStr(order.getState(), ""));
                } else {
//                    detail.setShippingAddress(AppUtil.getSafeStr(order.getStreetAddress(), "") + " "
//                            + AppUtil.getSafeStr(order.getCity(), "") + " " + AppUtil.getSafeStr(order.getState(), ""));
                }
                list.add(detailDTO);
                count++;
            }

//            detail.setReadyToDeliverOrders(list);
//          /  detail.setTotalRxCost(AppUtil.roundOffNumberToCurrencyFormat(totalRxCost, "en", "us"));
//            detail.setTotalShippingCost(AppUtil.roundOffNumberToCurrencyFormat(totalShippingCost, "en", "us"));
//            detail.setGrandPayment(AppUtil.roundOffNumberToCurrencyFormat(grandTotal, "en", "us"));
//            detail.setTotalRxReadyToDeliverOrders(list.size());
        } catch (Exception e) {
            logger.error("Exception:: getReadyToDeliverOrdersByPatientId ", e);
        }
        return detail;
    }

    public Order updateOrderStatus(Order order, Integer patientId, String deliverycarrier, String traclingno, String clerk,
            String shipDate, String finalPaymentFld, String outOfPocketFId, String handlingFeeFld, Integer statusVal,
            PharmacyUser user) {
        try {
            if (user != null) {
                if (user.getPharmacy() != null) {
                    order.setPharmacy(user.getPharmacy());
                } else {
                    throw new Exception("This user doesn't belong to any pharmacy, so can't proceed with order.");
                }
                order.setUpdatedBy(user.getId());
            }
            order.setDeliverycarrier(deliverycarrier);
            order.setTraclingno(traclingno);
            order.setClerk(clerk);
            if (CommonUtil.isNotEmpty(shipDate)) {
                order.setShippedOn(DateUtil.stringToDate(shipDate, Constants.USA_DATE_FORMATE));
            }
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(statusVal);
            order.setOrderStatus(orderStatus);
            //OrderChain orderChain = populateOrderChain(order, order.getDaysSupply(),order.getRefillsAllowed(),order.getRefillsRemaining());
            populateOrderDeliveryPreferenceUsed(order);
            //order.setFinalPayment(AppUtil.getSafeDouble(finalPaymentFld, 0d));
            double handlingFee = AppUtil.getSafeDouble(handlingFeeFld, 0d);
            order.setHandLingFee(handlingFee);
            //order.setPaymentExcludingDelivery(AppUtil.getSafeDouble(outOfPocketFId, 0d));
            order.setUpdatedOn(new Date());
            order.setStatusCreatedOn(new Date());

            //List<Order> listOfOrders = consumerRegistrationDAO.getReadyToDeliverOrdersByPatientId(patientId);
            List<Order> listOfOrders = consumerRegistrationDAO.getReadyToDeliverOrdersByIdAndPatientIdAndPrefId(patientId, order.getReadyToDeliverRxOrders().getId(), order.getDeliveryPreference().getId(), false);
            for (Order ord : listOfOrders) {
                ord.setDeliverycarrier(deliverycarrier);
                ord.setTraclingno(traclingno);
                ord.setClerk(clerk);
                if (CommonUtil.isNotEmpty(shipDate)) {
                    ord.setShippedOn(DateUtil.stringToDate(shipDate, Constants.USA_DATE_FORMATE));
                }
                ord.setOrderStatus(orderStatus);
                populateOrderDeliveryPreferenceUsed(ord);
                // ord.setFinalPayment(AppUtil.getSafeDouble(finalPaymentFld, 0d));
                ord.setHandLingFee(handlingFee);
                //ord.setPaymentExcludingDelivery(AppUtil.getSafeDouble(outOfPocketFId, 0d));

                ReadyToDeliverRxOrders deliverRxOrders = ord.getReadyToDeliverRxOrders();
                if (deliverRxOrders != null) {
                    deliverRxOrders.setIsShipped(Boolean.TRUE);
                    deliverRxOrders.setShippedDate(new Date());
                    consumerRegistrationDAO.saveOrUpdate(deliverRxOrders);
                }

                ord.setUpdatedBy(order.getUpdatedBy());
                ord.setUpdatedOn(order.getUpdatedOn());
                ord.setStatusCreatedOn(order.getStatusCreatedOn());

                if (!order.getId().equals(ord.getId())) {
                    MultiRx m = new MultiRx();
                    m.setMainOrder(order);
                    m.setOrder(ord);
                    m.setShippedDate(new Date());
                    consumerRegistrationDAO.save(m);
                }
                consumerRegistrationDAO.saveOrUpdate(ord);
            }
            consumerRegistrationDAO.saveOrUpdate(order);
        } catch (Exception e) {
            logger.error("Exception:: getReadyToDeliverOrdersByPatientId ", e);
        }
        return order;
    }

    public BigInteger populateShippedAndReadyToDeliveryCountForPage1(Integer pharmacyId) {
        BigInteger count = BigInteger.ZERO;
        try {
            count = consumerRegistrationDAO.populateShippedAndReadyToDeliveryCountForPage1(pharmacyId);
        } catch (Exception e) {
            logger.error("Exception:: populateShippedAndReadyToDeliveryCountForPage1 ", e);
        }
        return count;
    }
}

//for (TransferRxQueueDTO rxQueueDTO : dbList) {
//                TransferRxQueueDTO queueDTO = new TransferRxQueueDTO();
//                queueDTO.setPatientId(rxQueueDTO.getPatientId());
