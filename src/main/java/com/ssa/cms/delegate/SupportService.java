package com.ssa.cms.delegate;

import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.dao.OrderDAO;
import com.ssa.cms.dao.PatientProfileDAO;
import com.ssa.cms.dao.RedemptionDAO;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.RedemptionIngredient;
import com.ssa.cms.model.RewardPoints;
import com.ssa.cms.model.SupportModel;
import com.ssa.cms.remainder.AnnualStatementJob;
import com.ssa.cms.remainder.AutoDeletionOrderJob;
import com.ssa.cms.remainder.AutoReminderForPOAExpiry;
import com.ssa.cms.remainder.EndOfTheYearJob;
//import com.ssa.cms.remainder.LetsGetStartedJob;
import com.ssa.cms.remainder.NextRefillReminderJob;
import com.ssa.cms.remainder.refill.PMSAutoRefillFailure;
import com.ssa.cms.remainder.refill.PMSRefillOrderReminder;
//import com.ssa.cms.service.MessageService;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
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
public class SupportService {

    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private RefillReminderService refillReminderDAO;

    @Autowired
    private RedemptionDAO redemptionDAO;
    @Autowired
    private PatientProfileDAO patientProfileDAO;
    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private AutoDeletionOrderJob autoDeletionOrderJob;
    @Autowired
    private OrderDAO orderDao;
//    @Autowired
//    private MessageService messgaeService;
    @Autowired
    private OrderService orderService;

    public boolean executeProcess(int id) {
        boolean flag = false;

        try {

            //Next Refill Reminder
            if (id == 1) {
                NextRefillReminderJob nextRefillReminderJob = new NextRefillReminderJob();
                nextRefillReminderJob.sendNextRefillReminderJob(refillReminderDAO);
                flag = true;
                return flag;
            }

            //Lets Get Started Reminder
//            if (id == 2) {
//                LetsGetStartedJob letsGetStartedMessage = new LetsGetStartedJob();
//                letsGetStartedMessage.processLetsGetStartedMessage(messgaeService);
//                flag = true;
//                return flag;
//            }

            // End Of The Year Reminder
            if (id == 3) {
                EndOfTheYearJob endOfTheYearJob = new EndOfTheYearJob();
                endOfTheYearJob.processEndOfTheYearJob(refillReminderDAO);
                flag = true;
                return flag;
            }

            //Annual Statement Reminder
            if (id == 4) {
                AnnualStatementJob annualStatementJob = new AnnualStatementJob();
                annualStatementJob.processAnnualStatements(refillReminderDAO);
                flag = true;
                return flag;
            }

            //Fill Confirmation Reminder
//            if (id == 5) {
//                campaignSurveyReminder.sendSurveyAlerts();
//                flag = true;
//                return flag;
//            }
            //Auto Deletion Order  Job
            if (id == 5) {

                autoDeletionOrderJob.autoDeleteOrder(orderService);
                flag = true;
                return flag;
            }

            // Refill Failure
            if (id == 6) {
                PMSAutoRefillFailure autoRefillFailure = new PMSAutoRefillFailure();
                autoRefillFailure.sendRefillFailure(refillReminderDAO);
                flag = true;
                return flag;
            }

            //Refill Order Reminder
            if (id == 7) {
                PMSRefillOrderReminder orderReminder = new PMSRefillOrderReminder();
                orderReminder.sendRefillOrderReminder(refillReminderDAO);
                flag = true;
                return flag;
            }
            if (id == 8) {
                flag = true;
                return flag;
            }
            if (id == 9) {
                AutoReminderForPOAExpiry aExpiry = new AutoReminderForPOAExpiry();
                aExpiry.autoReminder(patientProfileService);
//                aExpiry.autoReminder(orderService);
                return true;
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
            e.printStackTrace();
        }

        return flag;
    }

    public boolean saveRedemptionIngredient(SupportModel supportModel) {
        Boolean saved = false;
        try {
            int i = 0;
            if (supportModel.getNdcNumber().size() > 0) {
                for (String ndc : supportModel.getNdcNumber()) {
                    RedemptionIngredient redemptionIngredient = new RedemptionIngredient();
                    redemptionIngredient.setNdc(ndc);
                    redemptionIngredient.setTransactionNumber(supportModel.getEmTransactionNum());
                    redemptionIngredient.setName("TestDrug" + i);
                    redemptionIngredient.setType("TABLET");
                    redemptionIngredient.setPharmacyCost(null);
                    redemptionIngredient.setStrength(null);
                    redemptionIngredient.setCovered(Boolean.FALSE);
                    redemptionIngredient.setFormulary(Boolean.FALSE);
                    redemptionIngredient.setPlanCost(null);
                    redemptionIngredient.setProcessedSig(null);
                    redemptionIngredient.setQuantity(null);
                    refillReminderDAO.save(redemptionIngredient);
                    i++;
                }
                saved = true;
            }
        } catch (Exception e) {
            saved = false;
            logger.error("Exception:: saveRedemptionIngredient", e);
        }
        return saved;
    }

    public String getCampaignByNDCNumber(List<String> ndcNumbers) {
        String ndcNumber = "";
        try {
            ndcNumber = redemptionDAO.getCampaignByNDCS(ndcNumbers);
        } catch (Exception e) {
            logger.error("Exception: getCampaignByNDCNumber", e);
        }
        return ndcNumber;
    }

    public String delPatientProfileByPhoneNumber(String phoneNumber, String tblName) {
        String response = null;
        try {
            phoneNumber = EncryptionHandlerUtil.getEncryptedString(phoneNumber);
            List<PatientProfile> patientProfilesList = redemptionDAO.findByNestedProperty(new PatientProfile(), "mobileNumber", phoneNumber, Constants.HIBERNATE_EQ_OPERATOR, null, 0);
            if (CommonUtil.isNullOrEmpty(patientProfilesList)) {
                return JsonResponseComposer.composeResponse("No record found against this phone number" + phoneNumber, Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
            }
            for (PatientProfile patientProfile : patientProfilesList) {
                CommonUtil.delPatientProfileData(patientProfile, patientProfileDAO, tblName);
                response = JsonResponseComposer.composeResponse("Record has been deleted successfully.", Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("Exception:: delPatientProfileByPhoneNumber", e);
            response = JsonResponseComposer.composeResponse("There is some problem to delete record.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
        }
        return response;
    }

    public List<CampaignMessages> getCampaignMessagesList() {
        List<CampaignMessages> list = new ArrayList<>();
        try {
            list = patientProfileDAO.getCampaignMessagesList();
        } catch (Exception e) {
            logger.error("Exception:: getCampaignMessagesList", e);
        }
        return list;
    }

    public List<Order> getOrdersListByStatus(String mobileNumber) {
        List<Order> list = new ArrayList<>();
        try {
            List<Integer> orderStatusList = new ArrayList<>();
            orderStatusList.add(Constants.ORDER_STATUS.HAND_DELIVERED_ID);
            orderStatusList.add(Constants.ORDER_STATUS.SHIPPED_ID);
            orderStatusList.add(Constants.ORDER_STATUS.PICKUP_AT_PHARMACY_ID);
            mobileNumber = EncryptionHandlerUtil.getDecryptedString(mobileNumber);
            list = patientProfileDAO.getOrdersListByStatus(mobileNumber, orderStatusList);
        } catch (Exception e) {
            logger.error("Exception:: getOrdersListByStatus", e);
        }
        return list;
    }

    public Boolean isUpdateOrderFillDate(String orderId, String filledDateStr, Integer currentUserId) {
        boolean isUpdate = false;
        try {
            Order order = (Order) patientProfileDAO.findByPropertyUnique(new Order(), "id", orderId, Constants.HIBERNATE_EQ_OPERATOR);

            
            /////////////////////////////////////////////////////////
            

            if (order != null) {
                order.setAutoRefillDate(order.getNextRefillDate());
                order.setUpdatedBy(currentUserId);
                order.setUpdatedOn(new Date());
                order.setShippedOn(DateUtil.stringToDate(filledDateStr, Constants.USA_DATE_FORMATE));
                order.setFilledDate(DateUtil.stringToDate(filledDateStr, Constants.USA_DATE_FORMATE));
                patientProfileDAO.update(order);
                isUpdate = true;
            }
        } catch (Exception e) {
            isUpdate = false;
            logger.error("Exception:: isUpdateOrderFillDate", e);
        }
        return isUpdate;
    }

    public Boolean isRefillReminderMsgSend(String orderId) {
        boolean isMsgSend = false;
        try {
            Order order = orderDao.getOrdersById(orderId);
            if (order != null) {
                CampaignMessages campignMessages = this.patientProfileService.getNotificationMsgs(Constants.MSG_CONTEXT_REFILL, Constants.COMPLIANCE_REWARD);
                if (campignMessages == null || campignMessages.getMessageId() == null) {
                    logger.info("There is no refill msg exist.");
                    return isMsgSend;
                }
                String pharmacyComments = "", genericName = "";
               
//                pharmacyComments = AppUtil.truncateStringToSpecificLength(pharmacyComments, 100);
                if (order.getDrugDetail() != null && order.getDrugDetail().getDrugDetailId() != null) {
                    genericName = order.getDrugDetail().getDrugBasic().getDrugGeneric().getGenericName();
                }
                campignMessages.setSmstext(AppUtil.getSafeStr(campignMessages.getSmstext(), ""));
                if (CommonUtil.isNullOrEmpty(campignMessages.getSmstext())) {
                    logger.info("Msg test is empty.");
                    return isMsgSend;
                }
//                RewardPoints rewardPoints = (RewardPoints) patientProfileDAO.findByPropertyUnique(new RewardPoints(), "type", "Rx Refill", Constants.HIBERNATE_EQ_OPERATOR);
                CampaignMessages campaignMessages = refillReminderDAO.replaceRefillMsgPlaceHolders(campignMessages, order, genericName, null, pharmacyComments);
                String subject = patientProfileService.getMessageSubjectWithprocessedPlaceHolders(campignMessages.getSubject(), orderId, null);
                campaignMessages.setSubject(subject);
                patientProfileService.saveNotificationMessages(campaignMessages, Constants.YES, order.getPatientProfile().getPatientProfileSeqNo(), order.getId());
                isMsgSend = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isMsgSend = false;
            logger.error("Exception:: isRefillReminderMsgSend", e);
        }
        return isMsgSend;
    }

    public String searchPatientProfileByPhoneNumber(String phoneNumber) {
        String response = null;
        try {
            phoneNumber = EncryptionHandlerUtil.getEncryptedString(phoneNumber);
            List<PatientProfile> patientProfilesList = redemptionDAO.findByNestedProperty(new PatientProfile(), "mobileNumber", phoneNumber, Constants.HIBERNATE_EQ_OPERATOR, null, 0);
            if (CommonUtil.isNullOrEmpty(patientProfilesList)) {
                return JsonResponseComposer.composeResponse("No patient found", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
            }
            for (PatientProfile patientProfile : patientProfilesList) {
                response = JsonResponseComposer.composeResponse(patientProfile.getFirstName() + " " + patientProfile.getLastName(), Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("Exception:: delPatientProfileByPhoneNumber", e);
            response = JsonResponseComposer.composeResponse("There is some problem to delete record.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
        }
        return response;
    }
}
