package com.ssa.cms.validation;

import com.ssa.cms.common.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ssa.cms.common.DailyDataDictionary;
import com.ssa.cms.decorator.ValidationDecorator;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.DailyRedemptionId;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

public class DailyValidationUtil {

    private String fileName;
    public static final Logger log = Logger.getLogger(DailyValidationUtil.class);

    public DailyValidationUtil() {
    }

    public static DailyRedemption getDailyRedemption(String[] segments) {
        DailyRedemption dailyRedemption = new DailyRedemption();
        dailyRedemption.setFileTypeCode(Constants.DRF);
        dailyRedemption.setEffectiveDate(new Date());
        try {

            String csSegment = segments[1];

            /* 2 */
            String segmentIdentifier = csSegment.substring(0, 2);
            csSegment = csSegment.substring(2);

            /* 3 */
            String csClaimAuthorization = csSegment.substring(0, 20);
            csSegment = csSegment.substring(20);

            /* 4 */
            String csOrignalClaimAuthorization = csSegment.substring(0, 20);
            csSegment = csSegment.substring(20);

            /* 5 */
            String csReversalClaimAuthorization = csSegment.substring(0, 20);
            csSegment = csSegment.substring(20);

            /* 6 */
            String csDateProcessed = csSegment.substring(0, 8);
            csSegment = csSegment.substring(8);

            /* 7 */
            String csTimeProcessed = csSegment.substring(0, 8);
            csSegment = csSegment.substring(8);

            /* 8 */
            String csRejectionFlag = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 9 */
            String csDuplicateFlag = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 10 */
            String csReversalFlag = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 11 */
            String csPharmacyNCPDP = csSegment.substring(0, 15);
            csSegment = csSegment.substring(15);

            /* 12 */
            String csPharmacyName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 13 */
            String pharmacyChain = csSegment.substring(0, 4);
            csSegment = csSegment.substring(4);

            /* 14 */
            String pharmacyChainName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 15 */
            String pharmacyType = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 16 */
            String carrierCode = csSegment.substring(0, 3);
            csSegment = csSegment.substring(3);

            /* 17 */
            String submittedCarrierCode = csSegment.substring(0, 3);
            csSegment = csSegment.substring(3);

            /* 18 */
            String tpaBrokerCode = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 19 */
            String tpaBrokerName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 20 */
            String sponsorCode = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 21 */
            String sponsorName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 22 */
            String csGroupNumber = csSegment.substring(0, 15);
            csSegment = csSegment.substring(15);

            /* 23 */
            String csGroupName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 24 */
            String csCardholderId = csSegment.substring(0, 20);
            csSegment = csSegment.substring(20);

            /* 25 */
            String patientPersonCode = csSegment.substring(0, 3);
            csSegment = csSegment.substring(3);

            /* 26 */
            String csPatientLastName = csSegment.substring(0, 15);
            csSegment = csSegment.substring(15);

            /* 27 */
            String csPatientFirstName = csSegment.substring(0, 12);
            csSegment = csSegment.substring(12);

            /* 28 */
            String prescriberId = csSegment.substring(0, 15);
            csSegment = csSegment.substring(15);

            /* 29 */
            String prescriberName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 30 */
            String csDateFilled = csSegment.substring(0, 8);
            csSegment = csSegment.substring(8);

            /* 31 */
            String csDateWritten = csSegment.substring(0, 8);
            csSegment = csSegment.substring(8);

            /* 32 */
            String csRxNumber = csSegment.substring(0, 12);
            csSegment = csSegment.substring(12);

            /* 33 */
            String csFillNumber = csSegment.substring(0, 2);
            csSegment = csSegment.substring(2);

            /* 34 */
            String csRefillsAuthorized = csSegment.substring(0, 2);
            csSegment = csSegment.substring(2);

            /* 35 */
            String csDrugNDC = csSegment.substring(0, 19);
            csSegment = csSegment.substring(19);

            /* 36 */
            String csDrugName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 37 */
            String csDrugStrength = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 38 */
            String csDrugDosageDescription = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 39 */
            String submittedCompoundCode = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 40 */
            String submittedRouteAdmin = csSegment.substring(0, 11);
            csSegment = csSegment.substring(11);

            /* 41 */
            String drugProcessedSIGCode = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 42 */
            String drugMONYCode = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 43 */
            String csQuantityDispansed = csSegment.substring(0, 12);
            csSegment = csSegment.substring(12);

            /* 44 */
            String csDaysSupply = csSegment.substring(0, 4);
            csSegment = csSegment.substring(4);

            /*45  */
            String csDawCode = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 46 */
            String csPlanIngredientCost = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 47 */
            String csPlanDispensingFee = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 48 */
            String csPlanSalesTax = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 49 */
            String csIncentiveFee = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 50 */
            String csProcessorFee = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 51 */
            String csPlanGrossAmount = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 52 */
            String csOtherPayerAmount = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 53 */
            String csTotalPatientPayAmount = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 54 */
            String csPlanPharmacyAmount = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 55 */
            String csPlanBasisCost = csSegment.substring(0, 3);
            csSegment = csSegment.substring(3);

            /* 56 */
            String csPlanBasisReimbursement = csSegment.substring(0, 3);
            csSegment = csSegment.substring(3);

            /* 57 */
            String csCalculatedAWP = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 58 */
            String csSubmissionClarificationCode = csSegment.substring(0, 2);
            csSegment = csSegment.substring(2);

            /* 59 */
            String csOtherCoverageCode = csSegment.substring(0, 2);
            csSegment = csSegment.substring(2);

            /* 60 */
            String csMedicaidCode = csSegment.substring(0, 4);
            csSegment = csSegment.substring(4);

            /* 61 */
            String csMedicaidName = csSegment.substring(0, 35);
            csSegment = csSegment.substring(35);

            /* 62 */
            String csCouponNumber = csSegment.substring(0, 15);
            csSegment = csSegment.substring(15);

            /* 63 */
            String csPharmacyNPI = csSegment.substring(0, 15);
            csSegment = csSegment.substring(15);

            /* 64 */
            String csPharmacyIDQualifierSubmitted = csSegment.substring(0, 2);
            csSegment = csSegment.substring(2);

            /* 65 */
            String csCalculatedFEDMAC = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);

            /* 66 */
            String csCalculatedAWPSource = csSegment.substring(0, 1);
            csSegment = csSegment.substring(1);

            /* 67 */
            String csCalculatedWAC = csSegment.substring(0, 10);
            csSegment = csSegment.substring(10);
            //189 for future use

            /**
             * ***************Start - CLAIM INDICATOR SEGMENT
             * (CI)************************
             */
            /*  */
            String claimIndicatorSegment = segments[2];

            int ciLength = claimIndicatorSegment.length();

            /* 2 */
            String ciSegmentIndicatior = claimIndicatorSegment.substring(0, 2);
            claimIndicatorSegment = claimIndicatorSegment.substring(2);

            /* 3 */
            String ciPaperClaimFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 4 */
            String ciDirectReimbursementFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 5 */
            String ciTestClaimFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /*6  */
            String ciBatchProcessedFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 7 */
            String ciOtherProcessorFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 8 */
            String ciFormularyDrugFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 9 */
            String ciNetworkPharmacyFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 10 */
            String ciNetworkPhysicianFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 11 */
            String ciShoeboxClaimFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 12 */
            String ciProductQTYClaimFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 13 */
            String ciStarterDoseFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 14 */
            String ciPriorAuthFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 15 */
            String ciDURFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 16 */
            String ciDUROverrideFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 17 */
            String ciIGCopayFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 18 */
            String ciMultiIngredCompoundFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 19 */
            String ciPartialFillDispensingStatus = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 20 */
            String ciMedicaidFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 21 */
            String ciForceUCFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 22 */
            String ciNDCRemapped = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 23 */
            String ciForceDollarZeroPharmacyDue = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 24 */
            String ciAdditionalLowerOfStateRate = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);

            /* 25 */
            String ciPOSMedicaidFlag = claimIndicatorSegment.substring(0, 1);
            claimIndicatorSegment = claimIndicatorSegment.substring(1);
            //34 for future use

            /**
             * ***************Start - Additional Patient SEGMENT
             * (PT)************************
             */
            /*  */
            String ptSegment = segments[3];

            int ptlength = ptSegment.length();

            /* 2 */
            String ptSegmentIndicator = ptSegment.substring(0, 2);
            ptSegment = ptSegment.substring(2);

            /* 3 */
            String ptCardholderLastName = ptSegment.substring(0, 15);
            ptSegment = ptSegment.substring(15);

            /* 4 */
            String ptCardholderFirstName = ptSegment.substring(0, 12);
            ptSegment = ptSegment.substring(12);

            /* 5 */
            String ptPatientMiddleInitial = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 6 */
            String ptPatientNameSuffix = ptSegment.substring(0, 3);
            ptSegment = ptSegment.substring(3);

            /* 7 */
            String ptAddressLine1 = ptSegment.substring(0, 35);
            ptSegment = ptSegment.substring(35);

            /* 8 */
            String ptAddressLine2 = ptSegment.substring(0, 35);
            ptSegment = ptSegment.substring(35);

            /* 9 */
            String ptCity = ptSegment.substring(0, 18);
            ptSegment = ptSegment.substring(18);

            /* 10 */
            String ptState = ptSegment.substring(0, 2);
            ptSegment = ptSegment.substring(2);

            /* 11 */
            String ptZipCode4 = ptSegment.substring(0, 9);
            ptSegment = ptSegment.substring(9);

            /* 12 */
            String ptPhoneNumber = ptSegment.substring(0, 10);
            if (ptPhoneNumber.contains("@")) {
                ptPhoneNumber = ptSegment.substring(0, 19);
                ptSegment = ptSegment.substring(19);
            } else {
                ptSegment = ptSegment.substring(10);
            }

            /* 13 */
            String ptEffectiveDate = ptSegment.substring(0, 8);
            ptSegment = ptSegment.substring(8);

            /* 14 */
            String ptTerminationDate = ptSegment.substring(0, 8);
            ptSegment = ptSegment.substring(8);

            /* 15 */
            String ptDateOfBirth = ptSegment.substring(0, 8);
            ptSegment = ptSegment.substring(8);

            /* 16 */
            String ptGenderCode = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 17 */
            String ptRelationshipCode = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 18 */
            String ptCoverageCode = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 19 */
            String ptReservedforFutureUse1 = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 20 */
            String ptStudentFlag = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 21 */
            String ptReservedforFutureUse2 = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 22 */
            String ptLocationCode = ptSegment.substring(0, 20);
            ptSegment = ptSegment.substring(20);

            /* 23 */
            String ptLocationName = ptSegment.substring(0, 35);
            ptSegment = ptSegment.substring(35);

            /* 24 */
            String ptSecondaryCoverage = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 25 */
            String ptPharmacyLockIn = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 26 */
            String ptPhysicianLockIn = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 27 */
            String ptTestMemberFlag = ptSegment.substring(0, 1);
            ptSegment = ptSegment.substring(1);

            /* 28 */
            String ptEligibilityCreatedByUserID = ptSegment.substring(0, 10);
            ptSegment = ptSegment.substring(10);

            /* 29 */
            String ptEligibilityCreatedDate = ptSegment.substring(0, 8);
            ptSegment = ptSegment.substring(8);

            /* 30 */
            String ptEligibilityModifiedByUserID = ptSegment.substring(0, 10);
            ptSegment = ptSegment.substring(10);

            /* 31 */
            String ptEligibilityModifiedDate = ptSegment.substring(0, 8);
            ptSegment = ptSegment.substring(8);
            //15 for future use

            /**
             * ***************Start - Rejected Code SEGMENT
             * (RJ)************************
             */
            /*  */
            String rjSegment = segments[4];

            int rjLength = rjSegment.length();

            /* 2 */
            String rjSegmentIndicator = rjSegment.substring(0, 2);
            rjSegment = rjSegment.substring(2);

            /* 3 */
            String rjRejectCode1 = rjSegment.substring(0, 3);
            rjSegment = rjSegment.substring(3);

            /* 4 */
            String rjRejectCode2 = rjSegment.substring(0, 3);
            rjSegment = rjSegment.substring(3);

            /* 5 */
            String rjRejectCode3 = rjSegment.substring(0, 3);
            rjSegment = rjSegment.substring(3);

            /* 6 */
            String rjRejectCode4 = rjSegment.substring(0, 3);
            rjSegment = rjSegment.substring(3);

            /* 7 */
            String rjRejectCode5 = rjSegment.substring(0, 3);
            rjSegment = rjSegment.substring(3);
            //15 for future use

            /**
             * ***************Start -Prior Authorization SEGMENT
             * (PA)************************
             */
            /*  */
            String paSegment = segments[5];

            int paLength = paSegment.length();

            /* 2 */
            String paSegmentIndicator = paSegment.substring(0, 2);
            paSegment = paSegment.substring(2);

            /* 3 */
            String paTypeSubmitted = paSegment.substring(0, 2);
            paSegment = paSegment.substring(2);

            /* 4 */
            String paSubmittedNo = paSegment.substring(0, 11);
            paSegment = paSegment.substring(11);

            /* 5 */
            String paActualNoUsed = paSegment.substring(0, 11);
            paSegment = paSegment.substring(11);

            /* 6 */
            String paType = paSegment.substring(0, 1);
            paSegment = paSegment.substring(1);

            /* 7 */
            String paEffectiveDate = paSegment.substring(0, 8);
            paSegment = paSegment.substring(8);

            /* 8 */
            String paTerminationDate = paSegment.substring(0, 8);
            paSegment = paSegment.substring(8);

            /* 9 */
            String paDescription = paSegment.substring(0, 35);
            paSegment = paSegment.substring(35);
            //30 for future use

            /**
             * ***************Start - DUR SEGMENT (DU)************************
             */
            /*  */
            String duSegment = segments[6];

            int duLength = duSegment.length();

            /* 2 */
            String duSegmentIndicator = duSegment.substring(0, 2);
            duSegment = duSegment.substring(2);

            /* 3 */
            String du1ConflictCode = duSegment.substring(0, 2);
            duSegment = duSegment.substring(2);

            /* 4 */
            String du1SeverityIndex = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 5 */
            String du1HitDisposition = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 6 */
            String du1ConflictingAuthorizationNumber = duSegment.substring(0, 20);
            duSegment = duSegment.substring(20);

            /* 7 */
            String du2ConflictCode = duSegment.substring(0, 2);
            duSegment = duSegment.substring(2);

            /* 8 */
            String du2SeverityIndex = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 9 */
            String du2HitDisposition = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 10 */
            String du2ConflictingAuthorizationNumber = duSegment.substring(0, 20);
            duSegment = duSegment.substring(20);

            /* 11 */
            String du3ConflictCode = duSegment.substring(0, 2);
            duSegment = duSegment.substring(2);

            /* 12 */
            String du3SeverityIndex = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 13 */
            String du3HitDisposition = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 14 */
            String du3ConflictingAuthorizationNumber = duSegment.substring(0, 20);
            duSegment = duSegment.substring(20);

            /* 15 */
            String du4ConflictCode = duSegment.substring(0, 2);
            duSegment = duSegment.substring(2);

            /* 16 */
            String du4SeverityIndex = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 17 */
            String du4HitDisposition = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 18 */
            String du4ConflictingAuthorizationNumber = duSegment.substring(0, 20);
            duSegment = duSegment.substring(20);

            /* 19 */
            String du5ConflictCode = duSegment.substring(0, 2);
            duSegment = duSegment.substring(2);

            /* 20 */
            String du5SeverityIndex = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 21 */
            String du5HitDisposition = duSegment.substring(0, 1);
            duSegment = duSegment.substring(1);

            /* 22 */
            String du5ConflictingAuthorizationNumber = duSegment.substring(0, 20);

            /**
             * ***************Start - DUR Override SEGMENT
             * (DO)************************
             */
            /*  */
            String doSegment = segments[7];

            int doLength = doSegment.length();

            /* 2 */
            String doSegmentIndicator = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 3 */
            String doServiceConflict1 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 4 */
            String doProfessionalServiceCode1 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 5 */
            String doServiceCode1 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 6 */
            String doServiceConflict2 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 7 */
            String doProfessionalServiceCode2 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 8 */
            String doServiceCode2 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 9 */
            String doServiceConflict3 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 10 */
            String doProfessionalServiceCode3 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 11 */
            String doServiceCode3 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 12 */
            String doServiceConflict4 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 13 */
            String doProfessionalServiceCode4 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 14 */
            String doServiceCode4 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 15 */
            String doServiceConflict5 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 16 */
            String doProfessionalServiceCode5 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /* 17 */
            String doServiceCode5 = doSegment.substring(0, 2);
            doSegment = doSegment.substring(2);

            /**
             * ***************Start - Drug Classification SEGMENT
             * (DC)************************
             */
            /*  */
            String dcSegment = segments[8];

            int dcLength = dcSegment.length();

            /* 2 */
            String dcSegmentIndicator = dcSegment.substring(0, 2);
            dcSegment = dcSegment.substring(2);

            /* 3 */
            String dcCategoryCode = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 4 */
            String dcClassCode = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 5 */
            String dcGC1Code = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 6 */
            String dcGC2Code = dcSegment.substring(0, 2);
            dcSegment = dcSegment.substring(2);

            /* 7 */
            String dcGC3Code = dcSegment.substring(0, 3);
            dcSegment = dcSegment.substring(3);

            /* 8 */
            String dcGC4Code = dcSegment.substring(0, 4);
            dcSegment = dcSegment.substring(4);

            /* 9 */
            String dcSpecificTherapeuticClass = dcSegment.substring(0, 3);
            dcSegment = dcSegment.substring(3);

            /* 10 */
            String dcGCNCode = dcSegment.substring(0, 5);
            dcSegment = dcSegment.substring(5);

            /* 11 */
            String dcGCNSequenceNumber = dcSegment.substring(0, 6);
            dcSegment = dcSegment.substring(6);

            /* 12 */
            String dcStandardTherapeuticClass = dcSegment.substring(0, 2);
            dcSegment = dcSegment.substring(2);

            /* 13 */
            String dcGenericTherapeuticClass = dcSegment.substring(0, 2);
            dcSegment = dcSegment.substring(2);

            /* 14 */
            String dcAHFSTherapeuticClass = dcSegment.substring(0, 6);
            dcSegment = dcSegment.substring(6);

            /* 15 */
            String dcOrangeBookCode = dcSegment.substring(0, 3);
            dcSegment = dcSegment.substring(3);

            /* 16 */
            String dcRouteAdministrationCode = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 17 */
            String dcDrugFormCode = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 18 */
            String dcDEACode = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 19 */
            String dcMaintenanceDrugIndicator = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 20 */
            String dcUnitIndicator = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 21 */
            String dcRepackageIndicator = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 22 */
            String dcUnitDoseIndicator = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 23 */
            String dcDESIDrugIndicator = dcSegment.substring(0, 1);
            dcSegment = dcSegment.substring(1);

            /* 24 */
            String dcDrugObsoleteDate = dcSegment.substring(0, 8);
            dcSegment = dcSegment.substring(8);
            //25 for future use

            /**
             * ***************Start - Pharmacy SEGMENT
             * (PH)************************
             */
            /*  */
            String phSegment = segments[9];

            int phLength = phSegment.length();

            /* 2 */
            String phSegmentIndicator = phSegment.substring(0, 2);
            phSegment = phSegment.substring(2);

            /* 3 */
            String phPhysicalAddressLine1 = phSegment.substring(0, 35);
            phSegment = phSegment.substring(35);

            /* 4 */
            String phPhysicalAddressLine2 = phSegment.substring(0, 35);
            phSegment = phSegment.substring(35);

            /* 5 */
            String phPhysicalCity = phSegment.substring(0, 18);
            phSegment = phSegment.substring(18);

            /* 6 */
            String phPhysicalState = phSegment.substring(0, 2);
            phSegment = phSegment.substring(2);

            /* 7 */
            String phPhysicalZipCode = phSegment.substring(0, 9);
            phSegment = phSegment.substring(9);

            /* 8 */
            String phPhoneNumber = phSegment.substring(0, 10);
            phSegment = phSegment.substring(10);

            /* 9 */
            String phReservedforFutureUse = phSegment.substring(0, 10);
            phSegment = phSegment.substring(10);

            /* 10 */
            String phFaxNumber = phSegment.substring(0, 10);
            phSegment = phSegment.substring(10);

            /* 11 */
            String phFederalLicenseNumber = phSegment.substring(0, 12);
            phSegment = phSegment.substring(12);

            /* 12 */
            String phFederalTaxIDNumber = phSegment.substring(0, 15);
            phSegment = phSegment.substring(15);

            /* 13 */
            String phStateLicenseNumber = phSegment.substring(0, 20);
            phSegment = phSegment.substring(20);

            /* 14 */
            String phStateTaxIDNumber = phSegment.substring(0, 15);
            phSegment = phSegment.substring(15);

            /* 15 */
            String phStateMedicaidNumber = phSegment.substring(0, 20);
            phSegment = phSegment.substring(20);

            /* 16 */
            String phPharmacyStoreNumber = phSegment.substring(0, 10);
            phSegment = phSegment.substring(10);

            /* 17 */
            String phTestPharmacyFlag = phSegment.substring(0, 1);
            phSegment = phSegment.substring(1);

            /* 18 */
            String phPharmacistIDQualifier = phSegment.substring(0, 2);
            phSegment = phSegment.substring(2);

            /* 19 */
            String phPharmacistID = phSegment.substring(0, 15);
            phSegment = phSegment.substring(15);

            /* 20 */
            String phPrimaryPharmacyDispenserType = phSegment.substring(0, 2);
            phSegment = phSegment.substring(2);
            //6 for future use

            /**
             * ***************Start - Prescriber SEGMENT
             * (PR)************************
             */
            /*  */
            String prSegment = segments[10];

            int prLength = prSegment.length();

            /*  */
            String prSegmentIndicator = prSegment.substring(0, 2);
            prSegment = prSegment.substring(2);

            /* 3 */
            String prBusinessActivityCode = prSegment.substring(0, 1);
            prSegment = prSegment.substring(1);

            /* 4 */
            String prDrugSchedules = prSegment.substring(0, 12);
            prSegment = prSegment.substring(12);

            /* 5 */
            String prAddressLine1 = prSegment.substring(0, 40);
            prSegment = prSegment.substring(40);

            /* 6 */
            String prAddressLine2 = prSegment.substring(0, 40);
            prSegment = prSegment.substring(40);

            /* 7 */
            String prAddressLine3 = prSegment.substring(0, 40);
            prSegment = prSegment.substring(40);

            /* 8 */
            String prAddressLine4 = prSegment.substring(0, 33);
            prSegment = prSegment.substring(33);

            /* 9 */
            String prCity = prSegment.substring(0, 24);
            prSegment = prSegment.substring(24);

            /* 10 */
            String prState = prSegment.substring(0, 2);
            prSegment = prSegment.substring(2);

            /* 11 */
            String prZipCode = prSegment.substring(0, 5);
            prSegment = prSegment.substring(5);

            /* 12 */
            String prDEA = prSegment.substring(0, 9);
            prSegment = prSegment.substring(9);

            /* 13 */
            String prNPI = prSegment.substring(0, 10);
            prSegment = prSegment.substring(10);

            /* 14 */
            String prPrescriberIDQualifier = prSegment.substring(0, 2);
            prSegment = prSegment.substring(2);
            //4 for future use

            /**
             * ***************Start - Enhanced Prescriber SEGMENT
             * (EP)************************
             */
            /*  */
            String epSegment = segments[11];

            int epLength = epSegment.length();

            /*  */
            String epSegmentIndicator = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 3 */
            String epPrescriberName = epSegment.substring(0, 35);
            epSegment = epSegment.substring(35);

            /* 4 */
            String epDEA = epSegment.substring(0, 9);
            epSegment = epSegment.substring(9);

            /* 5 */
            String epNPI = epSegment.substring(0, 10);
            epSegment = epSegment.substring(10);

            /* 6 */
            String epBusinessActivityCode = epSegment.substring(0, 1);
            epSegment = epSegment.substring(1);

            /* 7 */
            String epBusinessActivitySubCode = epSegment.substring(0, 1);
            epSegment = epSegment.substring(1);

            /* 8 */
            String epDrugSchedules = epSegment.substring(0, 12);
            epSegment = epSegment.substring(12);

            /* 9 */
            String epAddressLine1 = epSegment.substring(0, 40);
            epSegment = epSegment.substring(40);

            /* 10 */
            String epAddressLine2 = epSegment.substring(0, 40);
            epSegment = epSegment.substring(40);

            /* 11 */
            String epCity = epSegment.substring(0, 24);
            epSegment = epSegment.substring(24);

            /* 12 */
            String epState = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 13 */
            String epZipCode = epSegment.substring(0, 9);
            epSegment = epSegment.substring(9);

            /* 14 */
            String epPhone = epSegment.substring(0, 10);
            epSegment = epSegment.substring(10);

            /* 15 */
            String epFax = epSegment.substring(0, 10);
            epSegment = epSegment.substring(10);

            /* 16 */
            String epCredentials = epSegment.substring(0, 10);
            epSegment = epSegment.substring(10);

            /* 17 */
            String epPractitionerType = epSegment.substring(0, 25);
            epSegment = epSegment.substring(25);

            /* 18 */
            String epSpecialty1 = epSegment.substring(0, 40);
            epSegment = epSegment.substring(04);

            /* 19 */
            String epSpecialty2 = epSegment.substring(0, 40);
            epSegment = epSegment.substring(40);

            /* 20 */
            String epStateLicense1 = epSegment.substring(0, 15);
            epSegment = epSegment.substring(15);

            /* 21 */
            String epStateLicense1State = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 22 */
            String epStateLicense2 = epSegment.substring(0, 15);
            epSegment = epSegment.substring(15);

            /* 23 */
            String epStateLicense2State = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 24 */
            String epStateLicense3 = epSegment.substring(0, 15);
            epSegment = epSegment.substring(15);

            /* 25 */
            String epStateLicense3State = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 26 */
            String epMedicaidID1 = epSegment.substring(0, 15);
            epSegment = epSegment.substring(15);

            /* 27 */
            String epMedicaidID1State = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 28 */
            String epMedicaidID2 = epSegment.substring(0, 15);
            epSegment = epSegment.substring(15);

            /* 29 */
            String epMedicaidID2State = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 30 */
            String epMedicaidID3 = epSegment.substring(0, 15);
            epSegment = epSegment.substring(15);

            /* 31 */
            String epMedicaidID3State = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);

            /* 32 */
            String epTaxID = epSegment.substring(0, 9);
            epSegment = epSegment.substring(9);

            /* 33 */
            String epExpirationDate = epSegment.substring(0, 8);
            epSegment = epSegment.substring(8);

            /* 34 */
            String epPrescriberIDQualifier = epSegment.substring(0, 2);
            epSegment = epSegment.substring(2);
            //99 for future use

            /**
             * ***************Start - Worker's Compensation SEGMENT
             * (WC)************************
             */
            /*  */
            String wcSegment = segments[12];

            int wcLength = wcSegment.length();

            /*  */
            String wcSegmentIndicator = wcSegment.substring(0, 2);
            wcSegment = wcSegment.substring(2);

            /* 3 */
            String wcDateOfInjury = wcSegment.substring(0, 8);
            wcSegment = wcSegment.substring(8);

            /* 4 */
            String wcClaimNumber = wcSegment.substring(0, 14);
            wcSegment = wcSegment.substring(14);

            /* 5 */
            String wcRecordClaimNumber = wcSegment.substring(0, 14);
            wcSegment = wcSegment.substring(14);

            /* 6 */
            String wcRecordAdjustorCode = wcSegment.substring(0, 10);
            wcSegment = wcSegment.substring(10);

            /* 7 */
            String wcEmployerName = wcSegment.substring(0, 35);
            wcSegment = wcSegment.substring(35);

            /* 8 */
            String wcEmployerAddress1 = wcSegment.substring(0, 35);
            wcSegment = wcSegment.substring(35);

            /* 9 */
            String wcEmployerAddress2 = wcSegment.substring(0, 35);
            wcSegment = wcSegment.substring(35);

            /* 10 */
            String wcEmployerCity = wcSegment.substring(0, 18);
            wcSegment = wcSegment.substring(18);

            /* 11 */
            String wcEmployerState = wcSegment.substring(0, 2);
            wcSegment = wcSegment.substring(2);

            /* 12 */
            String wcEmployerZipCode = wcSegment.substring(0, 9);
            wcSegment = wcSegment.substring(9);

            /* 13 */
            String wcEmployerPhoneNumber = wcSegment.substring(0, 10);
            wcSegment = wcSegment.substring(10);

            /* 14 */
            String wcRecordICDCode1 = wcSegment.substring(0, 6);
            wcSegment = wcSegment.substring(6);

            /* 15 */
            String wcRecordICDCode2 = wcSegment.substring(0, 6);
            wcSegment = wcSegment.substring(6);

            /* 16 */
            String wcRecordICDCode3 = wcSegment.substring(0, 6);
            wcSegment = wcSegment.substring(6);

            /* 17 */
            String wcRecordICDCode4 = wcSegment.substring(0, 6);
            wcSegment = wcSegment.substring(6);

            /* 18 */
            String wcRecordICDCode5 = wcSegment.substring(0, 6);
            wcSegment = wcSegment.substring(6);

            /* 19 */
            String wcRecordTerminationDate = wcSegment.substring(0, 8);
            wcSegment = wcSegment.substring(8);
            //290 for future use

            /**
             * ***************Start - Claim Balance SEGMENT
             * (CB)************************
             */
            /*  */
            String cbSegment = segments[13];

            int cbLength = cbSegment.length();

            /*  */
            String cbSegmentIndicator = cbSegment.substring(0, 2);
            cbSegment = cbSegment.substring(2);

            /* 3 */
            String cbIndividualDeductibleAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 4 */
            String cbIndividualMemberAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 5 */
            String cbIndividualSponsorAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 6 */
            String cbIndividualStartingDeductibleAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 7 */
            String cbIndividualEndingDeductibleAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 8 */
            String cbIndividualRemainingDeductibleAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 9 */
            String cbIndividualStartingMemberAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 10 */
            String cbIndividualEndingMemberAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 11 */
            String cbIndividualStartingSponsorAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 12 */
            String cbIndividualEndingSponsorAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 13 */
            String cbIndividualStartingTierLevel = cbSegment.substring(0, 1);
            cbSegment = cbSegment.substring(1);

            /* 14 */
            String cbIndividualEndingTierLevel = cbSegment.substring(0, 1);
            cbSegment = cbSegment.substring(1);

            /* 15 */
            String cbFamilyDeductibleAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /*16  */
            String cbFamilyMemberAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 17 */
            String cbFamilySponsorAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 18 */
            String cbFamilyStartingDeductibleAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 19 */
            String cbFamilyEndingDeductibleAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 20 */
            String cbFamilyRemainingDeductibleAmount = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 21 */
            String cbFamilyStartingMemberAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 22 */
            String cbFamilyEndingMemberAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 23 */
            String cbFamilyStartingSponsorAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 24 */
            String cbFamilyEndingSponsorAccumulation = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);

            /* 25 */
            String cbFamilyStartingTierLevel = cbSegment.substring(0, 1);
            cbSegment = cbSegment.substring(1);

            /* 26 */
            String cbFamilyEndingTierLevel = cbSegment.substring(0, 1);
            cbSegment = cbSegment.substring(1);

            /* 27 */
            String cbCardValue = cbSegment.substring(0, 10);
            cbSegment = cbSegment.substring(10);
            //80 for future use

            /**
             * ***************Start - Paper Claim Info Segment
             * (PC)************************
             */
            /*  */
            String pcSegment = segments[14];

            int pcLength = pcSegment.length();

            /*  */
            String pcSegmentIndicator = pcSegment.substring(0, 2);
            pcSegment = pcSegment.substring(2);

            /* 3 */
            String pcUserID = pcSegment.substring(0, 10);
            pcSegment = pcSegment.substring(10);

            /* 4 */
            String pcTerminalID = pcSegment.substring(0, 4);
            pcSegment = pcSegment.substring(4);

            /* 5 */
            String pcReceivedDate1 = pcSegment.substring(0, 8);
            pcSegment = pcSegment.substring(8);

            /* 6 */
            String pcReceivedDate2 = pcSegment.substring(0, 8);
            pcSegment = pcSegment.substring(8);

            /* 7 */
            String pcReferenceCode = pcSegment.substring(0, 20);
            pcSegment = pcSegment.substring(20);
            //29 for future use

            /**
             * ***************Start - Plan Code Use Segment
             * (PL)************************
             */
            /*  */
            String plSegment = segments[15];

            int plLength = plSegment.length();

            /*  */
            String plSegmentIndicator = plSegment.substring(0, 2);
            plSegment = plSegment.substring(2);

            /* 3 */
            String plLineOfBusinessCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /*  */
            String plPlanNetworkCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /*  */
            String plMarginNetworkCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /*  */
            String plCommonFormularyCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /*  */
            String plPlanFormulaCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /*  */
            String plPharmacyFormulaCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /*  */
            String plCommonMACCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /*  */
            String plCopayCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);

            /* 11 */
            String plLevel2MessagingCode = plSegment.substring(0, 10);
            plSegment = plSegment.substring(10);
            //90 for future use

            /**
             * ***************Start - Transaction Messaging Segment
             * (TS)************************
             */
            /*  */
            String tsSegment = segments[16];

            int tsLength = tsSegment.length();

            /*  */
            String tsSegmentIndicator = tsSegment.substring(0, 2);
            tsSegment = tsSegment.substring(2);

            /*  */
            String tsTransactionMessage = tsSegment.substring(0, 200);

            /**
             * ***************Start - Submitted Member Segment
             * (SM)************************
             */
            /*  */
            String smSegment = segments[17];

            int smLength = smSegment.length();

            /*  */
            String smSegmentIndicator = smSegment.substring(0, 2);
            smSegment = smSegment.substring(2);

            /* 3 */
            String smSubmittedCardholderLastName = smSegment.substring(0, 15);
            smSegment = smSegment.substring(15);

            /*  */
            String smSubmittedPatientFirstName = smSegment.substring(0, 15);
            smSegment = smSegment.substring(15);

            /*  */
            String smSubmittedPatientDateofBirth = smSegment.substring(0, 8);
            smSegment = smSegment.substring(8);

            /*  */
            String smSubmittedGender = smSegment.substring(0, 1);
            smSegment = smSegment.substring(1);

            /* 7 */
            String smSubmittedPatientEmailAddress = smSegment.substring(0, 80);
            smSegment = smSegment.substring(80);
            //43 for future use 

            /**
             * ***************Start - Additional Submitted Values Segment
             * (SV)************************
             */
            /*  */
            String svSegment = segments[19];

            int svLength = svSegment.length();

            /*  */
            String svSegmentIndicator = svSegment.substring(0, 2);
            svSegment = svSegment.substring(2);

            /*  */
            String svSubmittedNDCNumber = svSegment.substring(0, 19);
            svSegment = svSegment.substring(19);

            /*  */
            String svSubmittedCardholderID = svSegment.substring(0, 20);
            svSegment = svSegment.substring(20);

            /*  */
            String svPrescriptionOriginCode = svSegment.substring(0, 1);
            svSegment = svSegment.substring(1);

            /*  */
            String svPrimaryOtherPayerIDQualifier = svSegment.substring(0, 2);
            svSegment = svSegment.substring(2);

            /* 7 */
            String svPrimaryOtherPayerID = svSegment.substring(0, 10);
            //145 reserved for future

            /**
             * ***************Start - Patient Pay Segment
             * (PP)************************
             */
            /*  */
            String ppSegment = segments[20];

            int ppLength = ppSegment.length();

            /*  */
            String ppSegmentIndicator = ppSegment.substring(0, 2);
            ppSegment = ppSegment.substring(2);

            /*  */
            String ppAttributedToProcessorFee = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /*  */
            String ppAmountOfCoinsurance = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /*  */
            String ppReservedForFutureDefinedField = ppSegment.substring(0, 2);
            ppSegment = ppSegment.substring(2);

            /*  */
            String ppReservedForFuturePricingField1 = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /*  */
            String ppReservedForFuturePricingField2 = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /*  */
            String ppProductSelectionForBrandDrug = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /*  */
            String ppProductOrNonPreferredFormularySelection = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /*  */
            String ppProductOrBrandNonPreferredFormularySelection = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /*  */
            String ppReservedforfuturePricingField3 = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);

            /* 12 */
            String ppReservedforfuturePricingField4 = ppSegment.substring(0, 10);
            ppSegment = ppSegment.substring(10);
            //50 for future use

            String scSegment = segments[21]; // A new segmentSubmitted COB Claim Segment

            /**
             * ***************Start - Pharmacy Pricing Segment
             * (PS)************************
             */
            String psSegment = segments[22];

            int psLength = psSegment.length();

            /*  */
            String psSegmentIndicator = psSegment.substring(0, 2);
            psSegment = psSegment.substring(2);

            /*  */
            String psPharmacyIngredientCost = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);

            /*  */
            String psPharmacyDispensingFee = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);

            /*  */
            String psPharmacySalesTax = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);

            /*  */
            String psPharmacyGrossAmount = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);

            /*  */
            String psPharmacyCopayAmount = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);

            /*  */
            String psPharmacyDueAmount = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);

            /*  */
            String psPharmacyBasisOfCost = psSegment.substring(0, 3);
            psSegment = psSegment.substring(3);

            /*  */
            String psPharmacyBasisofReimbursement = psSegment.substring(0, 3);
            psSegment = psSegment.substring(3);

            /*  */
            String psPharmacyCalculatedAmount = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);

            /* 12 */
            String psProcessorFee = psSegment.substring(0, 10);
            psSegment = psSegment.substring(10);
            //3 for future use

            /**
             * ***************Start - Submitted Pharmacy Pricing Segment
             * (SP)************************
             */
            /*  */
            String spSegment = segments[23];

            int spLength = spSegment.length();

            /*  */
            String spSegmentIndicator = spSegment.substring(0, 2);
            spSegment = spSegment.substring(2);

            /*  */
            String spSubmittedUAndC = spSegment.substring(0, 10);
            spSegment = spSegment.substring(10);

            /*  */
            String spSubmittedIngredientCost = spSegment.substring(0, 10);
            spSegment = spSegment.substring(10);

            /*  */
            String spSubmittedDispensingFee = spSegment.substring(0, 10);
            spSegment = spSegment.substring(10);

            /*  */
            String spSubmittedSalesTax = spSegment.substring(0, 10);
            spSegment = spSegment.substring(10);

            /*  */
            String spSubmittedGrossAmount = spSegment.substring(0, 10);
            spSegment = spSegment.substring(10);

            /* 8 */
            String spSubmittedCopayAmount = spSegment.substring(0, 10);
            spSegment = spSegment.substring(10);
            //24 for future use

            //Required Fields
            dailyRedemption.setClaimAuthorizationNumber(csClaimAuthorization.trim());
            dailyRedemption.setOrignalClaimAuthorizationNumber(csOrignalClaimAuthorization.trim());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DailyDataDictionary.CS_DATE_PROCESSED_FILE_FORMAT);
            if (csTimeProcessed.trim().length() == 7) {
                csTimeProcessed = "0" + csTimeProcessed.trim();
                csDateProcessed += " " + csTimeProcessed;
            } else if (csTimeProcessed.trim().length() != 8) {
                csDateProcessed += " 00:00:00";
            } else {
                csDateProcessed += " " + csTimeProcessed.trim();
            }
            try {
                Date fdate = simpleDateFormat.parse(csDateProcessed);
                dailyRedemption.setPostingDate(fdate);
            } catch (Exception ex) {

            }

            if (csPharmacyNCPDP.trim().length() > 0) {
                dailyRedemption.setPharmacyNcpdp(Long.parseLong(csPharmacyNCPDP.trim()));
            }
            dailyRedemption.setPharmacyName(csPharmacyName.trim());
            dailyRedemption.setRxGroupNumber(csGroupNumber.trim());
            dailyRedemption.setCardholderId(csCardholderId.trim());

            simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            try {
                Date fdate = simpleDateFormat.parse(csDateFilled.trim());
                dailyRedemption.setFillDate(fdate);
            } catch (Exception ex) {

            }

            dailyRedemption.setRxWrittenDate(csDateWritten);
            dailyRedemption.setPrescriptionNumber(csRxNumber);
            if (csRefillsAuthorized.length() > 0) {
                dailyRedemption.setRefillsAllowed(Integer.parseInt(csRefillsAuthorized.trim()));
            }
            dailyRedemption.setNdcNumber(csDrugNDC.trim());
            dailyRedemption.setDrugStrength(csDrugStrength.trim());
            dailyRedemption.setDrugDosageForm(csDrugDosageDescription.trim());
            if (csQuantityDispansed != null) {
                dailyRedemption.setQuantity(Double.parseDouble(csQuantityDispansed.trim()));
            }
            if (csDaysSupply != null) {
                dailyRedemption.setDaysSupply(Integer.parseInt(csDaysSupply.trim()));
            }
            if (csPlanIngredientCost != null) {
                dailyRedemption.setIngredientCostPaid(new BigDecimal(csPlanIngredientCost.trim()));
            }
            if (csPlanIngredientCost != null) {
                dailyRedemption.setDispenseFeePaid(Double.parseDouble(csPlanDispensingFee.trim()));
            }
            if (csPlanSalesTax != null) {
                dailyRedemption.setFlatSalesTaxAmountPaid(Double.parseDouble(csPlanSalesTax.trim()));
            }
            if (csIncentiveFee != null) {
                dailyRedemption.setProfessionalServiceFee(Double.parseDouble(csIncentiveFee.trim()));
            }

            if (psPharmacyGrossAmount != null) {
                dailyRedemption.setPharmacyGrossAmount(Double.parseDouble(psPharmacyGrossAmount.trim()));
            }

            if (csPlanPharmacyAmount != null) {
                dailyRedemption.setPlanPharmacyAmount(Double.parseDouble(csPlanPharmacyAmount.trim()));
            }

            DailyRedemptionId id = new DailyRedemptionId();
            dailyRedemption.setId(id);
            dailyRedemption.getId().setTransactionNumber(csClaimAuthorization.trim());
            if (csRejectionFlag == null || !csRejectionFlag.equals("1")) {
                if (csReversalFlag != null && csReversalFlag.equals("1") || csDuplicateFlag != null && csDuplicateFlag.equals("1")) {
                    dailyRedemption.getId().setClaimStatus(1);
                } else {
                    dailyRedemption.getId().setClaimStatus(0);
                }
            }
            if (dailyRedemption.getId().getClaimStatus() != 0) {
                dailyRedemption.setReversalDate(dailyRedemption.getPostingDate());
            }

            if (csIncentiveFee != null) {
                dailyRedemption.setProfessionalServiceFee(Double.parseDouble(csIncentiveFee.trim()));
            }

            if (csTotalPatientPayAmount != null) {
                dailyRedemption.setPtOutOfPocket(new BigDecimal(csTotalPatientPayAmount.trim()));
            }

            double csTotCostPaidToPharmacy = 0.0;
            double cost = Double.parseDouble(csPlanIngredientCost.trim());
            double patientPayAmount = Double.parseDouble(csTotalPatientPayAmount.trim());
            if (cost > 0 && cost > patientPayAmount) {
                csTotCostPaidToPharmacy = cost - patientPayAmount;
            }
            dailyRedemption.setTotDrugCostPaidToPharmacy(BigDecimal.valueOf(csTotCostPaidToPharmacy));

            dailyRedemption.setPharmacyNpi(csPharmacyNPI.trim());
            dailyRedemption.setCardholderLastName(csPatientLastName.trim());
            dailyRedemption.setCardholderFirstName(csPatientFirstName.trim());
            dailyRedemption.setOtherCoverageCode(csOtherCoverageCode.trim());

            if (ciPaperClaimFlag != null) {
                dailyRedemption.setCiPaperClaimFlag(Integer.parseInt(ciPaperClaimFlag.trim()));
            }

            if (ciDirectReimbursementFlag != null) {
                dailyRedemption.setCiDirectReimbursementFlag(Integer.parseInt(ciDirectReimbursementFlag.trim()));
            }

            if (ciTestClaimFlag != null) {
                dailyRedemption.setCiTestClaimFlag(Integer.parseInt(ciTestClaimFlag.trim()));
            }

            if (ciBatchProcessedFlag != null) {
                dailyRedemption.setCiBatchProcessedFlag(Integer.parseInt(ciBatchProcessedFlag.trim()));
            }

            if (ciOtherProcessorFlag != null) {
                dailyRedemption.setCiOtherProcessorFlag(Integer.parseInt(ciOtherProcessorFlag.trim()));
            }

            dailyRedemption.setSubmittedCardholderFirstName(ptCardholderFirstName.trim());
            dailyRedemption.setSubmittedCardholderLastName(ptCardholderLastName.trim());
            if (ptPhoneNumber != null) {
                dailyRedemption.setCommunicationId(ptPhoneNumber);
            }
            try {
                Date fdate = simpleDateFormat.parse(ptDateOfBirth.trim());
                dailyRedemption.setSubmittedCardholderDob(fdate);
                dailyRedemption.setCardholderDob(fdate);
            } catch (Exception ex) {

            }
            dailyRedemption.setSubmittedCardholderGender(ptGenderCode.trim());
            dailyRedemption.setCardholderGender(ptGenderCode.trim().charAt(0));
            if (dcGCNCode != null) {
                dailyRedemption.setFdbGcn(Long.parseLong(dcGCNCode.trim()));
            }
            dailyRedemption.setTherapeuticClassDesc(dcStandardTherapeuticClass.trim());

            dailyRedemption.setPharmacyAddress1(phPhysicalAddressLine1.trim());
            dailyRedemption.setPharmacyAddress2(phPhysicalAddressLine2.trim());
            dailyRedemption.setPharmacyCity(phPhysicalCity.trim());
            dailyRedemption.setPharmacyState(phPhysicalState.trim());
            dailyRedemption.setPharmacyZipCode(phPhysicalZipCode.trim());
            dailyRedemption.setPharmacyPhone(phPhoneNumber.trim());
            dailyRedemption.setPharmacyPhone(phPhoneNumber.trim());
            dailyRedemption.setPrescriberAddress1(prAddressLine1.trim());
            dailyRedemption.setPrescriberAddress2(prAddressLine2.trim());
            dailyRedemption.setPrescriberCity(prCity.trim());
            dailyRedemption.setPrescriberState(prState.trim());
            dailyRedemption.setPrescriberZipCode(epZipCode.trim());
            dailyRedemption.setDeaNumber(prDEA.trim());
            dailyRedemption.setPrescriberNpi(prNPI.trim());

            if (svSubmittedCardholderID != null && svSubmittedCardholderID.trim().length() == 11) {
                svSubmittedCardholderID = svSubmittedCardholderID.substring(1);
            }
            dailyRedemption.setSubmittedId(svSubmittedCardholderID.trim());

        } catch (Exception e) {
            log.error("Exception: DailyValidationUtil -> getDailyRedemption", e);
        }

        return dailyRedemption;
    }

    private boolean sendDRFWarningAlert(String fieldValue, ValidationDecorator decorator) {

        boolean isSent = false;
        String warnEmailSubject = "Emdeon DRF Warning - " + fileName;
        String message = "Automated warning message for the problem occurred during Emdeon DRF processing <br/> ";
        message = message + "(Record is saved as result of this warning)";

        String error = decorator.getMessage();
        String fieldName = decorator.getFieldName();
        String fieldTrace = "N/A";
        //String mailBody = EmailUtil.createEmailBodyValidationWarning("Emdeon DRF Processor", message, error, fieldName, fieldValue, fieldTrace, fileName);
        //isSent = EmailSender.sendErrorEmail(warnEmailSubject, mailBody); 
        return isSent;
    }

}
