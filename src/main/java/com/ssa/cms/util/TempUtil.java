package com.ssa.cms.util;
import java.util.ArrayList;
import java.util.List;

public class TempUtil {

    public static List<String> expectedTablesToRemove=new ArrayList<>();

    public TempUtil(){

        expectedTablesToRemove.add("DeliveryPreferences");
        expectedTablesToRemove.add("DeliveryDistances");
        expectedTablesToRemove.add("DeliveryDistanceFee");
        expectedTablesToRemove.add("DeliveryPreferencesDistance");
        expectedTablesToRemove.add("FeeSettings");
        expectedTablesToRemove.add("Invitation");
        expectedTablesToRemove.add("MultiRx");
        expectedTablesToRemove.add("MMSCampaignMessageReqRes");
        expectedTablesToRemove.add("MMSCampaignMessageResponseParsedData");
        expectedTablesToRemove.add("Model");
        expectedTablesToRemove.add("NotifyAdmin");
        expectedTablesToRemove.add("OmCode");
        expectedTablesToRemove.add("OrderChain");
        expectedTablesToRemove.add("OrderCustomDocuments");
        expectedTablesToRemove.add("OrderPlaceEmail");
        expectedTablesToRemove.add("Orders_bkup");
        expectedTablesToRemove.add("OrdersCarriers");
        expectedTablesToRemove.add("OrderTransferImages");
        expectedTablesToRemove.add("OrganizationUnit");
        expectedTablesToRemove.add("PatientNotification");
        expectedTablesToRemove.add("PatientProfileHealth");
        expectedTablesToRemove.add("PatientProfileMembers");
        expectedTablesToRemove.add("PatientProfileNotes");
        expectedTablesToRemove.add("PatientProfilePaymentInfo");
        expectedTablesToRemove.add("PatientService");
        expectedTablesToRemove.add("PatientServiceTransmit");
        expectedTablesToRemove.add("PharmacyFacilityOperation");
        expectedTablesToRemove.add("PharmacyLookup");
        expectedTablesToRemove.add("Pharmacy_PtNotifications");
        expectedTablesToRemove.add("Pharmacy_PtNotifications_bkup");
        expectedTablesToRemove.add("PharmacyType");
        expectedTablesToRemove.add("Practice");
        expectedTablesToRemove.add("PracticeAutoProcess");
        expectedTablesToRemove.add("PracticeBp");
        expectedTablesToRemove.add("PracticeModel");
        expectedTablesToRemove.add("PracticeReportInfo");
        expectedTablesToRemove.add("PracticeStaff");
        expectedTablesToRemove.add("PracticeUser");
        expectedTablesToRemove.add("Promotion");
        expectedTablesToRemove.add("PromotionModel");
        expectedTablesToRemove.add("PUActivationCode");
        expectedTablesToRemove.add("Rebatee");
        expectedTablesToRemove.add("RedemptionChannel");
        expectedTablesToRemove.add("RepLogOn");
        expectedTablesToRemove.add("SessionID");
        expectedTablesToRemove.add("TransferDetail");
        expectedTablesToRemove.add("TransferRequest");
        expectedTablesToRemove.add("UserDefinition");
        expectedTablesToRemove.add("WidgetUser");
        expectedTablesToRemove.add("WidgetUseripAddresses");
        //--
        expectedTablesToRemove.add("Folder");
        expectedTablesToRemove.add("FolderHasCampaigns");
        expectedTablesToRemove.add("GroupHasFolderHasCampaign");
        expectedTablesToRemove.add("MessageQueue");
        expectedTablesToRemove.add("migrations");
        expectedTablesToRemove.add("model_has_permissions");
        expectedTablesToRemove.add("model_has_roles");
        expectedTablesToRemove.add("PatientFollowUP");
        expectedTablesToRemove.add("PatientRewardLevel");
        expectedTablesToRemove.add("patients");
        expectedTablesToRemove.add("RewardHistory");
        expectedTablesToRemove.add("RewardPoints");
        expectedTablesToRemove.add("rxgroupmembers");
        expectedTablesToRemove.add("StateRewardTaxes");




    }

}
