package com.ssa.cms.validation;

import java.text.SimpleDateFormat;

import com.ssa.cms.common.DailyDataDictionary;
import com.ssa.cms.decorator.ValidationDecorator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DailyRedemptionValidator {

    /**
     * validate Fill Date
     *
     * @param fillDate
     * @param logger
     * @return
     */
    public ValidationDecorator validateTransactionNumber(String transactionNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Transaction_Number");
        decorator.setFieldValue(transactionNumber);

        System.out.print("Validation of Transaction_Number  " + transactionNumber);
        if (transactionNumber == null || transactionNumber.trim().length() == 0) {
            decorator.setMessage("CS_Claim_Authorization not found");
            decorator.setValid(false);
            return decorator;
        }

        if (transactionNumber.trim().length() > DailyDataDictionary.CS_CLAIM_AUTHORIZATION_LEN) {
            decorator.setMessage("CS_Claim_Authorization length exceeds");
            decorator.setValid(false);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateCsDateProcessed(String csDateProcessed) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_Date_Processed");
        decorator.setFieldValue(csDateProcessed);

        System.out.print("Validate CS_Date_Processed (Required) " + csDateProcessed);

        if (csDateProcessed == null || csDateProcessed.trim().length() == 0) {
            decorator.setMessage("CS_Date_Processed missing");
            decorator.setValid(false);
            return decorator;
        }

        csDateProcessed = csDateProcessed.trim();

        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
        Matcher matcher = pattern.matcher(csDateProcessed);

        if (!matcher.matches()) {
            decorator.setMessage("CS_Date_Processed must be informat yyyy-MM-dd HH:mm:ss");
            decorator.setValid(false);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateClaimStatus(String claimStatus) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Claim_Status");
        decorator.setFieldValue(claimStatus);

        System.out.print("Validate Claim_Status (0,1)" + claimStatus);

        if (claimStatus == null || claimStatus.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Claim_Status value is missing");
            return decorator;
        }

        claimStatus = claimStatus.trim();

        Pattern pattern = Pattern.compile("^[0,1]$");
        Matcher matcher = pattern.matcher(claimStatus);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("Claim_Status has invalid value. Other than 0,1 Value = " + claimStatus);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateCsDuplicateFlag(String csDuplicateFlag) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_DUPLICATE_FLAG");
        decorator.setFieldValue(csDuplicateFlag);

        System.out.print("Validate CS_DUPLICATE_FLAG (optional) (0,1)" + csDuplicateFlag);

        if (csDuplicateFlag == null || csDuplicateFlag.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_DUPLICATE_FLAG missing");
            return decorator;
        }

        if (csDuplicateFlag.trim().length() > DailyDataDictionary.CS_DUPLICATE_FLAG_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CS_DUPLICATE_FLAG exceeds length");
            return decorator;
        }

        if (!(csDuplicateFlag.trim().equals("0") || csDuplicateFlag.trim().equals("1"))) {
            decorator.setValid(false);
            decorator.setMessage("CS_DUPLICATE_FLAG has invalid value. Other than 0 or 1 ");
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateCsReversalDate(String csReversalFlag) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_REVERSAL_FLAG");
        decorator.setFieldValue(csReversalFlag);

        System.out.print("Validate CS_REVERSAL_FLAG (optional) (0,1)" + csReversalFlag);

        if (csReversalFlag == null || csReversalFlag.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_REVERSAL_FLAG missing");
            return decorator;
        }

        if (csReversalFlag.trim().length() > DailyDataDictionary.CS_REVERSAL_FLAG_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CS_REVERSAL_FLAG exceeds length");
            return decorator;
        }

        if (!(csReversalFlag.trim().equals("0") || csReversalFlag.trim().equals("1"))) {
            decorator.setValid(false);
            decorator.setMessage("CS_REVERSAL_FLAG has invalid value. Other than 0 or 1 ");
            return decorator;
        }

        return decorator;
    }

    /**
     *
     * @param csPharmNCPDP
     * @param logger
     * @return
     */
    public ValidationDecorator validatePharmacyNCPDP(String pharmNCPDP) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Pharmacy_NCPDP");
        decorator.setFieldValue(pharmNCPDP);

        System.out.print("Validate Pharmacy_NCPDP " + pharmNCPDP);

        if (pharmNCPDP == null || pharmNCPDP.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Pharmacy_NCPDP missing");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.PHARMACY_NCPDP_REGEX);
        Matcher matcher = pattern.matcher(pharmNCPDP);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("Pharmacy_NCPDP is not valid digit of 15 digits value =  " + pharmNCPDP);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePharmacyName(String pharmacyName) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Pharmacy_Name");
        decorator.setFieldValue(pharmacyName);

        System.out.print("Validate Pharmacy_Name " + pharmacyName);

        if (pharmacyName == null || pharmacyName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Pharmacy_Name missing");
            return decorator;
        }

        pharmacyName = pharmacyName.trim();

        if (pharmacyName.length() > DailyDataDictionary.PHARMACY_NAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("Pharmacy_Name exceeds length max length 35 value = " + pharmacyName);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateRxGroupNumber(String rxGroupNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Group_Number");
        decorator.setFieldValue(rxGroupNumber);

        System.out.print("Validate Group_Number " + rxGroupNumber);

        if (rxGroupNumber == null || rxGroupNumber.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Group_Number missing");
            return decorator;
        }

        if (rxGroupNumber.trim().length() > DailyDataDictionary.RX_GROUP_NUMBER_LEN) {
            decorator.setValid(false);
            decorator.setMessage("Group_Number exceeds the max lenth of 15 value = " + rxGroupNumber);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateCardholderID(String cardholderId) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Cardholder_Id");
        decorator.setFieldValue(cardholderId);

        System.out.print("Validate Cardholder_Id " + cardholderId);

        if (cardholderId == null || cardholderId.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Cardholder_Id missing");
            return decorator;
        }

        if (cardholderId.trim().length() > DailyDataDictionary.CARDHOLDER_ID_LEN) {
            decorator.setValid(false);
            decorator.setMessage("Cardholder_Id exceeds max length 20 value = " + cardholderId);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateFillDate(String fillDate) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Fill_Date");
        decorator.setFieldValue(fillDate);

        System.out.print("Validate Fill_Date (Required) " + fillDate);

        if (fillDate == null || fillDate.trim().length() == 0) {
            decorator.setMessage("Fill_Date missing");
            decorator.setValid(false);
            return decorator;
        }

        fillDate = fillDate.trim();

        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");;
        Matcher matcher = pattern.matcher(fillDate);

        if (!matcher.matches()) {
            decorator.setMessage("Fill_Date is not in format yyyy-MM-dd value : " + fillDate);
            decorator.setValid(false);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateCsDateWritten(String csDateWritten) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_DATE_WRITTEN");
        decorator.setFieldValue(csDateWritten);

        System.out.print("Validate CS_DATE_WRITTEN (Required) " + csDateWritten);

        if (csDateWritten == null || csDateWritten.trim().length() == 0) {
            decorator.setMessage("CS_DATE_WRITTEN missing");
            decorator.setValid(false);
            return decorator;
        }

        csDateWritten = csDateWritten.trim();

        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");;
        Matcher matcher = pattern.matcher(csDateWritten);

        if (!matcher.matches()) {
            decorator.setMessage("CS_DATE_Written is not in format yyyy-MM-dd " + csDateWritten);
            decorator.setValid(false);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePrescriptionNumber(String prescriptionNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Prescription_Number");
        decorator.setFieldValue(prescriptionNumber);

        System.out.print("Validate Prescription_Number " + prescriptionNumber);

        if (prescriptionNumber == null || prescriptionNumber.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Prescription_Number missing");
            return decorator;
        }

        prescriptionNumber = prescriptionNumber.trim();

        if (prescriptionNumber.length() > DailyDataDictionary.PRESCRIPTION_NUMBER_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CS_RX_NUMBER exceeds max length " + DailyDataDictionary.PRESCRIPTION_NUMBER_LEN + " VALUE = " + prescriptionNumber);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateFillNumber(String csFillNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_FILL_NUMBER");
        decorator.setFieldValue(csFillNumber);

        System.out.print("Validate CS_FILL_NUMBER : " + csFillNumber);

        if (csFillNumber == null || csFillNumber.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_FILL_NUMBER is missing");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.FILL_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(csFillNumber);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("CS_FILL_NUMBER conatains invalid value " + csFillNumber);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateRefillAuthorized(String refillAuthorized) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_REFILLS_AUTHORIZED");
        decorator.setFieldValue(refillAuthorized);
        System.out.print("Validate CS_REFILLS_AUTHORIZED) : " + refillAuthorized);

        if (refillAuthorized != null && refillAuthorized.trim().length() > 0) {

            refillAuthorized = refillAuthorized.trim();

            Pattern pattern = Pattern.compile(DailyDataDictionary.REFILLS_AUTHORIZED_REGEX);
            Matcher matcher = pattern.matcher(refillAuthorized);

            if (!matcher.matches()) {
                decorator.setValid(false);
                decorator.setMessage("CS_REFILLS_AUTHORIZED contains alphabet(s) " + refillAuthorized);
                return decorator;
            }
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateNDCNumber(String ndcNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_DRUG_NDC");
        decorator.setFieldValue(ndcNumber);

        System.out.print("Validate CS_DRUG_NDC  : " + ndcNumber);

        if (ndcNumber == null || ndcNumber.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_DRUG_NDC is missing");
            return decorator;
        }

        ndcNumber = ndcNumber.trim();

        Pattern pattern = Pattern.compile(DailyDataDictionary.NDC_NUMBER_REGX);
        Matcher matcher = pattern.matcher(ndcNumber);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("CS_DRUG_NDC contains alphabet(s) " + ndcNumber);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateDrugName(String drugName) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_DRUG_NAME");
        decorator.setFieldValue(drugName);

        if (drugName == null || drugName.trim().length() == 0) {
            decorator.setMessage("CS_DRUG_NAME missing");
            decorator.setValid(false);
            return decorator;
        }

        drugName = drugName.trim();

        if (drugName.length() > DailyDataDictionary.DRUG_NAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CS_DRUG_NAME exceeds max length of 35 value : " + drugName);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateDrugStrength(String drugStrength) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_DRUG_STRENGTH");
        decorator.setFieldValue(drugStrength);

        if (drugStrength != null && drugStrength.trim().length() > 0) {
            drugStrength = drugStrength.trim();

            decorator.setValid(false);
            decorator.setMessage("CS_DRUG_STRENGTH exceeds the max length of 10 value = " + drugStrength);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateDrugDosageDescription(String drugDosageDescription) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_DRUG_DOSAGE_DESCRIPTION");
        decorator.setFieldValue(drugDosageDescription);

        if (drugDosageDescription != null && drugDosageDescription.trim().length() > 0) {
            drugDosageDescription = drugDosageDescription.trim();

            if (drugDosageDescription.trim().length() > DailyDataDictionary.DRUG_DOSAGE_DESCRIPTION_LEN) {
                decorator.setValid(false);
                decorator.setMessage("CS_DRUG_DOSAGE_DESCRIPTION exceeds the max length of value = " + drugDosageDescription);
                return decorator;
            }
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateQuantityDispensed(String quantityDispensed) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_QUANTITY_DISPANSED");
        decorator.setFieldValue(quantityDispensed);

        System.out.print("Validate CS_QUANTITY_DISPANSED : " + quantityDispensed);

        if (quantityDispensed == null || quantityDispensed.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_QUANTITY_DISPANSED missing");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.QUANTITY_REGEX);
        Matcher matcher = pattern.matcher(quantityDispensed);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("CS_QUANTITY_DISPANSED is not in the format dddddd.dd value = " + quantityDispensed);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateDaysSupply(String daySupply) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_DAYS_SUPPLY");
        decorator.setFieldValue(daySupply);

        if (daySupply == null || daySupply.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_DAYS_SUPPLY missing");
            return decorator;
        }

        daySupply = daySupply.trim();

        Pattern pattern = Pattern.compile(DailyDataDictionary.DAYS_SUPPLY_REGEX);
        Matcher matcher = pattern.matcher(daySupply);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("CS_DAYS_SUPPLY contains alphabet(s) value = " + daySupply);
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateIngredientCostPaid(String ingredientCostPaid) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_PLAN_INGREDIENT_COST");
        decorator.setFieldValue(ingredientCostPaid);

        if (ingredientCostPaid == null || ingredientCostPaid.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_PLAN_INGREDIENT_COST value missing");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.INGREDIENT_COST_PAID_REGX);
        Matcher matcher = pattern.matcher(ingredientCostPaid);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("CS_PLAN_INGREDIENT_COST is not in the format of dddddd.dd value = " + ingredientCostPaid);
            return decorator;
        }

        return decorator;
    }

    /**
     * validate Total Cost Paid To Pharmacy
     *
     * @param toatlCostPaidToPharmacy
     * @param logger
     * @return
     */
    public ValidationDecorator validateDispanseFeePaid(String dispanseFeePaid) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_PLAN_DISPENSING_FEE");
        decorator.setFieldValue(dispanseFeePaid);

        if (dispanseFeePaid == null || dispanseFeePaid.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_PLAN_DISPENSING_FEE value missing");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.DISPENSING_FEE_REGEX);
        Matcher matcher = pattern.matcher(dispanseFeePaid);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("CS_PLAN_DISPENSING_FEE contains alphabet(s) ");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateSalesTax(String planSalesTax) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_PLAN_SALES_TAX");
        decorator.setFieldValue(planSalesTax);
        decorator.setValid(true);

        if (planSalesTax != null && planSalesTax.trim().length() > 0) {

            Pattern pattern = Pattern.compile(DailyDataDictionary.SALES_TAX_REGEX);
            Matcher matcher = pattern.matcher(planSalesTax);

            if (!matcher.matches()) {
                decorator.setValid(false);
                decorator.setMessage("CS_PLAN_SALES_TAX is not in the format dddddd.dd " + planSalesTax);
                return decorator;
            }
        }
        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateIncentiveFee(String incentiveFee) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_INCENTIVE_FEE");
        decorator.setFieldValue(incentiveFee);

        if (incentiveFee != null && incentiveFee.trim().length() > 0) {

            incentiveFee = incentiveFee.trim();

            Pattern pattern = Pattern.compile(DailyDataDictionary.INCENTIVE_FEE_REGEX);
            Matcher matcher = pattern.matcher(incentiveFee);

            if (!matcher.matches()) {
                decorator.setValid(false);
                decorator.setMessage("CS_INCENTIVE_FEE is not in the format of dddddd.dd value = " + incentiveFee);
                return decorator;
            }
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePatientPayAmount(String totalPatientAmountPaid) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_TOTAL_PATIENT_PAY_AMOUNT");
        decorator.setFieldValue(totalPatientAmountPaid);

        if (totalPatientAmountPaid == null || totalPatientAmountPaid.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_TOTAL_PATIENT_PAY_AMOUNT missing");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.TOTAL_PATIENT_AMOUNT_REGX);
        Matcher matcher = pattern.matcher(totalPatientAmountPaid);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("CS_TOTAL_PATIENT_PAY_AMOUNT is not in the format of dddddd.dd value = " + totalPatientAmountPaid);
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validatePriceSource(String priceSource) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Price_Source");
        decorator.setFieldValue(priceSource);

        if (priceSource != null && priceSource.trim().length() > 0) {
            if (priceSource.trim().length() > DailyDataDictionary.PRICE_SOURCE_LEN) {
                decorator.setValid(false);
                decorator.setMessage("Price_Source exceeds the max length of 3 value = " + priceSource);
                return decorator;
            }
        }
        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePharmacyNPI(String pharmacyNPI) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Pharmacy_NPI");
        decorator.setFieldValue(pharmacyNPI);

        if (pharmacyNPI == null || pharmacyNPI.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Pharmacy_NPI missing");
            return decorator;
        }

        pharmacyNPI = pharmacyNPI.trim();

        if (pharmacyNPI.trim().length() > DailyDataDictionary.PHARMACY_NPI_LEN) {
            decorator.setValid(false);
            decorator.setMessage("Pharmacy_NPI exceeds max length of 15 Value = " + pharmacyNPI);
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateCiClaimSource(String ciClaimSource) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CI_Claim_Source");
        decorator.setFieldValue(ciClaimSource);

        if (ciClaimSource == null || ciClaimSource.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CI_Claim_Source missing");
            return decorator;
        }

        if (ciClaimSource.trim().length() > DailyDataDictionary.CI_CLAIM_SOURCE_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CI_Claim_Source exceeds length");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateCiDirectReimburseFlag(String ciDirectReimburseFlag) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CI_DIRECT_REIMBURSEMENT_FLAG");
        decorator.setFieldValue(ciDirectReimburseFlag);

        if (ciDirectReimburseFlag == null || ciDirectReimburseFlag.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CI_DIRECT_REIMBURSEMENT_FLAG missing");
            return decorator;
        }

        ciDirectReimburseFlag = ciDirectReimburseFlag.trim();

        if (ciDirectReimburseFlag.trim().length() > DailyDataDictionary.CI_DIRECT_REIMBURSEMENT_FLAG_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CI_DIRECT_REIMBURSEMENT_FLAG exceeds length");
            return decorator;
        }

        if (!(ciDirectReimburseFlag.trim().equals("0") || ciDirectReimburseFlag.trim().equals("1"))) {
            decorator.setValid(false);
            decorator.setMessage("CI_DIRECT_REIMBURSEMENT_FLAG value found other than 0,1");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateCiTestClaimFlag(String ciTestClaimFlag) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CI_TEST_CLAIM_FLAG");
        decorator.setFieldValue(ciTestClaimFlag);

        if (ciTestClaimFlag == null || ciTestClaimFlag.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CI_TEST_CLAIM_FLAG missing");
            return decorator;
        }

        if (ciTestClaimFlag.trim().length() > DailyDataDictionary.CI_TEST_CLAIM_FLAG_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CI_TEST_CLAIM_FLAG exceeds length");
            return decorator;
        }

        if (!(ciTestClaimFlag.trim().equals("0") || ciTestClaimFlag.trim().equals("1"))) {
            decorator.setValid(false);
            decorator.setMessage("CI_TEST_CLAIM_FLAG value found other than 0,1");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateCiBatchProcessedFlag(String ciBatchProcessedFlag) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CI_BATCH_PROCESSED_FLAG");
        decorator.setFieldValue(ciBatchProcessedFlag);

        if (ciBatchProcessedFlag == null || ciBatchProcessedFlag.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CI_BATCH_PROCESSED_FLAG missing");
            return decorator;
        }

        ciBatchProcessedFlag = ciBatchProcessedFlag.trim();

        if (ciBatchProcessedFlag.trim().length() > DailyDataDictionary.CI_BATCH_PROCESSED_FLAG_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CI_BATCH_PROCESSED_FLAG exceeds length");
            return decorator;
        }

        if (!(ciBatchProcessedFlag.trim().equals("0") || ciBatchProcessedFlag.trim().equals("1"))) {
            decorator.setValid(false);
            decorator.setMessage("CI_BATCH_PROCESSED_FLAG value found other than 0,1");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateCiOtherProcessorFlag(String ciOtherProcessorFlag) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CI_OTHER_PROCESSOR_FLAG");
        decorator.setFieldValue(ciOtherProcessorFlag);

        if (ciOtherProcessorFlag == null || ciOtherProcessorFlag.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CI_OTHER_PROCESSOR_FLAG missing");
            return decorator;
        }

        if (ciOtherProcessorFlag.trim().length() > DailyDataDictionary.CI_BATCH_PROCESSED_FLAG_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CI_OTHER_PROCESSOR_FLAG exceeds length");
            return decorator;
        }

        if (!(ciOtherProcessorFlag.trim().equals("0") || ciOtherProcessorFlag.trim().equals("1"))) {
            decorator.setValid(false);
            decorator.setMessage("CI_OTHER_PROCESSOR_FLAG value found other than 0,1");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateSubmittedPatientFirstName(String submittedPatientFirstName) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setValid(true);
        decorator.setFieldName("Submitted_Patient_First_Name");
        decorator.setFieldValue(submittedPatientFirstName);

        if (submittedPatientFirstName != null && submittedPatientFirstName.trim().length() > 0) {
            submittedPatientFirstName = submittedPatientFirstName.trim();
            if (submittedPatientFirstName.length() > DailyDataDictionary.SUBMITTED_PATIENT_FIRSTNAME_LEN) {
                decorator.setValid(false);
                decorator.setMessage("Submitted_Patient_First_Name exceeds max length 30 value = " + submittedPatientFirstName);
                return decorator;
            }
        }
        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateSubmittedPatientLastName(String submittedPatientLastName) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setValid(true);
        decorator.setFieldName("Submitted_Patient_Last_Name");
        decorator.setFieldValue(submittedPatientLastName);

        if (submittedPatientLastName != null && submittedPatientLastName.trim().length() > 0) {
            submittedPatientLastName = submittedPatientLastName.trim();
            if (submittedPatientLastName.length() > DailyDataDictionary.SUBMITTED_PATIENT_LASTNAME_LEN) {
                decorator.setValid(false);
                decorator.setMessage("Submitted_Patient_Last_Name exceeds max length 30 value = " + submittedPatientLastName);
                return decorator;
            }
        }
        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateCardHolderFirstName(String cardHolderFirstName) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Cholder_First_Name");
        decorator.setFieldValue(cardHolderFirstName);

        if (cardHolderFirstName == null || cardHolderFirstName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Cholder_First_Name not found");
            return decorator;
        }

        if (cardHolderFirstName.trim().length() > DailyDataDictionary.CS_PATIENT_FIRST_NAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CS_PATIENT_FIRST_NAME exceeds max length of 30 value = " + cardHolderFirstName);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateCardHolderLastName(String cardHolderLastName) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Cardholder_Last_Name");
        decorator.setFieldValue(cardHolderLastName);

        if (cardHolderLastName == null || cardHolderLastName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Cardholder_Last_Name not found");
            return decorator;
        }

        if (cardHolderLastName.trim().length() > DailyDataDictionary.CARDHOLDER_FIRST_NAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("Cardholder_Last_Name length exceeds max length of 30 value = " + cardHolderLastName);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePtCholderFirstName(String ptCholderFirstName) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PT_CARDHOLDER_FIRST_NAME");
        decorator.setFieldValue(ptCholderFirstName);

        if (ptCholderFirstName == null || ptCholderFirstName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PT_CARDHOLDER_FIRST_NAME not found");
            return decorator;
        }

        if (ptCholderFirstName.trim().length() > DailyDataDictionary.CARDHOLDER_FIRST_NAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PT_CARDHOLDER_FIRST_NAME length exceeds");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validatePtDOB(String ptDOB) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PT_DATE_OF_BIRTH");
        decorator.setFieldValue(ptDOB);

        if (ptDOB == null || ptDOB.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PT_DATE_OF_BIRTH not found");
            return decorator;
        }
        ptDOB = ptDOB.trim();

        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = pattern.matcher(ptDOB);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("PT_DATE_OF_BIRTH must be in format yyyy-MM-dd");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateCardholderGender(String gender) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PT_GENDER_CODE");
        decorator.setFieldValue(gender);

        if (gender == null || gender.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PT_GENDER_CODE missing");
            return decorator;
        }

        gender = gender.toUpperCase();

        Pattern pattern = Pattern.compile("^[M,F,U]$");
        Matcher matcher = pattern.matcher(gender);
        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("PT_GENDER_CODE value found other than M,F,U value = " + gender);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePaSubmittedNumber(String paSubmittedNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PA_SUBMITTED_NO");
        decorator.setFieldValue(paSubmittedNumber);

        if (paSubmittedNumber == null || paSubmittedNumber.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PA_SUBMITTED_NO missing");
            return decorator;
        }

        if (paSubmittedNumber.trim().length() > DailyDataDictionary.PA_SUBMITTED_NO_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PA_SUBMITTED_NO exceeds length");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.PA_SUBMITTED_NO_REGEX);
        Matcher matcher = pattern.matcher(paSubmittedNumber);

        if (matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("PA_SUBMITTED_NO contains alphabet(s) ");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateDcGCNCode(String dcGCNCode) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("DC_GCN_CODE");
        decorator.setFieldValue(dcGCNCode);

        System.out.print("Validate DC_GCN_CODE : " + dcGCNCode);

        if (dcGCNCode == null || dcGCNCode.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("DC_GCN_CODE missing");
            return decorator;
        }

        if (dcGCNCode.trim().length() > DailyDataDictionary.DC_GCN_CODE_LEN) {
            decorator.setValid(false);
            decorator.setMessage("DC_GCN_CODE exceeds length");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.DC_GCN_CODE_EXPRESSION);
        Matcher matcher = pattern.matcher(dcGCNCode.trim());

        if (matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("DC_GCN_CODE contains alphabet(s)");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validateDcStandardTherapeuticClass(String dcStandardTherapeuticClass) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("DC_STANDARD_THERAPEUTIC_CLASS");
        decorator.setFieldValue(dcStandardTherapeuticClass);

        System.out.print("Validate DC_STANDARD_THERAPEUTIC_CLASS : " + dcStandardTherapeuticClass);

        if (dcStandardTherapeuticClass == null || dcStandardTherapeuticClass.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("DC_STANDARD_THERAPEUTIC_CLASS missing");
            return decorator;
        }

        if (dcStandardTherapeuticClass.trim().length() > DailyDataDictionary.DC_STANDARD_THERAPEUTIC_CLASS_LEN) {
            decorator.setValid(false);
            decorator.setMessage("DC_STANDARD_THERAPEUTIC_CLASS exceeds length");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.DC_STANDARD_THERAPEUTIC_CLASS_REGEX);
        Matcher matcher = pattern.matcher(dcStandardTherapeuticClass.trim());

        if (matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("DC_GCN_CODE contains alphabet(s)");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validatePhAddress1(String phAddress1) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PH_PHYSICAL_ADDRESS_LINE1");
        decorator.setFieldValue(phAddress1);

        System.out.print("Validate PH_PHYSICAL_ADDRESS_LINE1 : " + phAddress1);

        if (phAddress1 == null || phAddress1.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_ADDRESS_LINE1 missing");
            return decorator;
        }

        if (phAddress1.trim().length() > DailyDataDictionary.PH_PHYSICAL_ADDRESS_LINE1_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_ADDRESS_LINE1 exceeds length");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validatePhAddress2(String phAddress2) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PH_PHYSICAL_ADDRESS_LINE2");
        decorator.setFieldValue(phAddress2);

        System.out.print("Validate PH_PHYSICAL_ADDRESS_LINE2 : " + phAddress2);

        if (phAddress2 == null || phAddress2.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_ADDRESS_LINE2 missing");
            return decorator;
        }

        if (phAddress2.trim().length() > DailyDataDictionary.PH_PHYSICAL_ADDRESS_LINE2_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_ADDRESS_LINE2 exceeds length");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validatePhPharmacyCity(String phPharmacyCity) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PH_PHYSICAL_CITY");
        decorator.setFieldValue(phPharmacyCity);

        System.out.print("Validate PH_PHYSICAL_CITY : " + phPharmacyCity);

        if (phPharmacyCity == null || phPharmacyCity.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_CITY missing");
            return decorator;
        }

        if (phPharmacyCity.trim().length() > DailyDataDictionary.PH_PHYSICAL_CITY_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_CITY exceeds length");
            return decorator;
        }

        return decorator;
    }

    public ValidationDecorator validatePhPharmacyState(String phPharmacyState) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PH_PHYSICAL_STATE");
        decorator.setFieldValue(phPharmacyState);

        System.out.print("Validate PH_PHYSICAL_STATE : " + phPharmacyState);

        if (phPharmacyState == null || phPharmacyState.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_STATE missing");
            return decorator;
        }

        if (phPharmacyState.trim().length() > DailyDataDictionary.PH_PHYSICAL_STATE_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHYSICAL_STATE exceeds length");
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePharmacyZipCode(String pharmacyZipCode) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setValid(true);
        decorator.setFieldName("Prescriber_Zip_Code");
        decorator.setFieldValue(pharmacyZipCode);

        System.out.print("Validate Prescriber_Zip_Code : " + pharmacyZipCode);

        if (pharmacyZipCode != null && pharmacyZipCode.trim().length() > 0) {
            pharmacyZipCode = pharmacyZipCode.trim();

            Pattern pattern = Pattern.compile(DailyDataDictionary.PHARMACY_ZIP_CODE_REGX);
            Matcher matcher = pattern.matcher(pharmacyZipCode.trim());

            if (!matcher.matches()) {
                decorator.setValid(false);
                decorator.setMessage("Prescriber_Zip_Code must be in format ddddd-dddd");
                return decorator;
            }
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePhPharmacyPhoneNumber(String phPharmacyPhoneNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PH_PHONE_NUMBER");
        decorator.setFieldValue(phPharmacyPhoneNumber);

        System.out.print("Validate PH_PHONE_NUMBER : " + phPharmacyPhoneNumber);

        if (phPharmacyPhoneNumber == null || phPharmacyPhoneNumber.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHONE_NUMBER missing");
            return decorator;
        }

        if (phPharmacyPhoneNumber.trim().length() > DailyDataDictionary.PH_PHONE_NUMBER_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHONE_NUMBER exceeds length");
            return decorator;
        }

        Pattern pattern = Pattern.compile(DailyDataDictionary.PH_PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phPharmacyPhoneNumber.trim());

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("PH_PHONE_NUMBER is not numeric(10)");
            return decorator;
        }

        return decorator;
    }

    /**
     *
     * @param PR_Prescriber_FirstName
     * @param logger
     * @return
     */
    public ValidationDecorator validateEpPrescriberFirstName(String epPrescriberName) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PR_Prescriber_FirstName");
        decorator.setFieldValue(epPrescriberName);

        System.out.print("Validate PR_Prescriber_FirstName : " + epPrescriberName);

        if (epPrescriberName == null || epPrescriberName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PR_Prescriber_FirstName missing");
            return decorator;
        }

        if (epPrescriberName.trim().length() > DailyDataDictionary.EP_PRESCRIBER_FIRST_NAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PR_Prescriber_FirstName exceeds length");
            return decorator;
        }

        return decorator;
    }

    /**
     *
     * @param PR_Prescriber_LastName
     * @param logger
     * @return
     */
    public ValidationDecorator validateEpPrescriberLastName(String epPrescriberName) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PR_Prescriber_LastName");
        decorator.setFieldValue(epPrescriberName);

        System.out.print("Validate PR_Prescriber_LastName : " + epPrescriberName);

        if (epPrescriberName == null || epPrescriberName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PR_Prescriber_LastName missing");
            return decorator;
        }

        if (epPrescriberName.trim().length() > DailyDataDictionary.EP_PRESCRIBER_LAST_NAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PR_Prescriber_LastName exceeds length");
            return decorator;
        }

        return decorator;
    }

    /**
     *
     * @param prAddressLine1
     * @param logger
     * @return
     */
    public ValidationDecorator validatePrAddressLine1(String prAddressLine1) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PR_ADDRESS_LINE1");
        decorator.setFieldValue(prAddressLine1);

        System.out.print("Validation Of PR_ADDRESS_LINE1" + prAddressLine1);

        if (prAddressLine1 == null || prAddressLine1.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PR_ADDRESS_LINE1 exceeds length");
            return decorator;
        }

        if (prAddressLine1.trim().length() > DailyDataDictionary.PR_ADDRESS_LINE1_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PR_ADDRESS_LINE1 exceeds length");
            return decorator;
        }

        return decorator;
    }

    /**
     *
     * @param prAddressLine2
     * @param logger
     * @return
     */
    public ValidationDecorator validatePrAddressLine2(String prAddressLine2) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PR_ADDRESS_LINE2");
        decorator.setFieldValue(prAddressLine2);

        System.out.print("Validation Of PR_ADDRESS_LINE2" + prAddressLine2);

        if (prAddressLine2 == null || prAddressLine2.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PR_ADDRESS_LINE2 exceeds length");
            return decorator;
        }

        if (prAddressLine2.trim().length() > DailyDataDictionary.PR_ADDRESS_LINE2_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PR_ADDRESS_LINE2 exceeds length");
            return decorator;
        }

        return decorator;
    }

    /**
     * validate Price Source
     *
     * @param priceSource
     * @param logger
     * @return
     */
    public ValidationDecorator validatePrescriberCity(String prCity) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PR_CITY");
        decorator.setFieldValue(prCity);

        if (prCity == null || prCity.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PR_CITY Value  Missing ");
            return decorator;
        }

        prCity = prCity.trim();

        if (prCity.length() > DailyDataDictionary.PR_CITY_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PR_CITY length exceeds 15");
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePrState(String prState) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PR_STATE");
        decorator.setFieldValue(prState);

        if (prState == null || prState.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("PR_STATE Value  Missing ");
            return decorator;
        }

        if (prState.trim().length() > DailyDataDictionary.PR_STATE_LEN) {
            decorator.setValid(false);
            decorator.setMessage("PR_STATE length exceeds");
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePrescriberZipCode(String prescriberZipCode) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setValid(true);
        decorator.setFieldName("Prescriber_Zip_Code");
        decorator.setFieldValue(prescriberZipCode);

        System.out.print("Validate Prescriber_Zip_Code : " + prescriberZipCode);

        if (prescriberZipCode != null && prescriberZipCode.trim().length() > 0) {
            prescriberZipCode = prescriberZipCode.trim();

            Pattern pattern = Pattern.compile(DailyDataDictionary.PRESCRIBER_ZIP_CODE_REGX);
            Matcher matcher = pattern.matcher(prescriberZipCode.trim());

            if (!matcher.matches()) {
                decorator.setValid(false);
                decorator.setMessage("Prescriber_Zip_Code must be in format ddddd-dddd");
                return decorator;
            }
        }

        return decorator;
    }

    /**
     *
     * @param prZipCode
     * @param logger
     * @return
     */
    public ValidationDecorator validateDEANumber(String deaNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("PR_DEA");
        decorator.setFieldValue(deaNumber);

        if (deaNumber != null && deaNumber.trim().length() == 0) {
            deaNumber = deaNumber.trim();
            if (deaNumber.trim().length() > DailyDataDictionary.DEA_NUMBER_LENGTH) {
                decorator.setValid(false);
                decorator.setMessage("PR_DEA exceeds max length of 9 value = " + deaNumber);
                return decorator;
            }
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validatePrescriberNPI(String prescriberNPI) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Prescriber_NPI");
        decorator.setFieldValue(prescriberNPI);

        if (prescriberNPI != null && prescriberNPI.trim().length() == 0) {
            prescriberNPI = prescriberNPI.trim();
            if (prescriberNPI.length() > DailyDataDictionary.PR_NPI_LEN) {
                decorator.setValid(false);
                decorator.setMessage("Prescriber_NPI exceeds max length of 20 value = " + prescriberNPI);
                return decorator;
            }
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateEpSpecialty1(String epSpecialty1) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("EP_SPECIALTY1");
        decorator.setFieldValue(epSpecialty1);

        if (epSpecialty1 == null || epSpecialty1.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("EP_SPECIALTY1 Value  Missing ");
            return decorator;
        }

        if (epSpecialty1.trim().length() > DailyDataDictionary.EP_SPECIALTY1_LEN) {
            decorator.setValid(false);
            decorator.setMessage("EP_SPECIALTY1 length exceeds ");
            return decorator;
        }

        return decorator;
    }

    /**
     *
     * @param smSubmittedCardholderLastName
     * @param logger
     * @return
     */
//	public ValidationDecorator validateSubmittedCardHolderFirstName(String smSubmittedCardholderLastName){
//		
//		ValidationDecorator decorator = new ValidationDecorator();
//		decorator.setFieldName("SM_SUBMITTED_CARDHOLDER_LASTNAME");
//		decorator.setFieldValue(smSubmittedCardholderLastName);
//		
//		if(smSubmittedCardholderLastName == null || smSubmittedCardholderLastName.trim().length() == 0){
//			decorator.setValid(false);
//			decorator.setMessage("SM_SUBMITTED_CARDHOLDER_LASTNAME Value  Missing ");
//			return decorator;
//		}
//		
//		if(smSubmittedCardholderLastName.trim().length() > DailyDataDictionary.SM_SUBMITTED_CARDHOLDER_LASTNAME_LEN){
//			decorator.setValid(false);
//			decorator.setMessage("SM_SUBMITTED_CARDHOLDER_LASTNAME length exceeds ");
//			return decorator;
//		}
//		
//		return decorator;
//	}

    public ValidationDecorator validateCsPatientLastName(String csPatientLastName) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_PATIENT_LASTNAME");
        decorator.setFieldValue(csPatientLastName);

        if (csPatientLastName == null || csPatientLastName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_PATIENT_LASTNAME Value  Missing ");
            return decorator;
        }

        if (csPatientLastName.trim().length() > DailyDataDictionary.CS_PATIENT_LASTNAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CS_PATIENT_LASTNAME length exceeds ");
            return decorator;
        }

        return decorator;
    }

//	public ValidationDecorator validateSubmittedCardHolderLastName(String smSubmittedPatientFirstName){
//		
//		ValidationDecorator decorator = new ValidationDecorator();
//		decorator.setFieldName("SM_SUBMITTED_PATIENT_FIRSTNAME");
//		decorator.setFieldValue(smSubmittedPatientFirstName);
//		
//		if(smSubmittedPatientFirstName == null || smSubmittedPatientFirstName.trim().length() == 0){
//			decorator.setValid(false);
//			decorator.setMessage("SM_SUBMITTED_PATIENT_FIRSTNAME Value  Missing ");
//			return decorator;
//		}
//		
//		if(smSubmittedPatientFirstName.trim().length() > DailyDataDictionary.SM_SUBMITTED_PATIENT_FIRSTNAME_LEN){
//			decorator.setValid(false);
//			decorator.setMessage("SM_SUBMITTED_PATIENT_FIRSTNAME length exceeds ");
//			return decorator;
//		}
//		
//		return decorator;
//	}
    public ValidationDecorator validateCsPatientFirstName(String csPatientFirstName) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("CS_PATIENT_FIRSTNAME");
        decorator.setFieldValue(csPatientFirstName);

        if (csPatientFirstName == null || csPatientFirstName.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("CS_PATIENT_FIRSTNAME Value  Missing ");
            return decorator;
        }

        if (csPatientFirstName.trim().length() > DailyDataDictionary.CS_PATIENT_FIRSTNAME_LEN) {
            decorator.setValid(false);
            decorator.setMessage("CS_PATIENT_FIRSTNAME length exceeds ");
            return decorator;
        }

        return decorator;
    }

    /**
     * validate Submitted CardHolder Gender
     *
     * @param submittedCardHolderGender
     * @param logger
     * @return
     */
    public ValidationDecorator validateSmSubmittedGender(String smSubmittedGender) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("SM_SUBMITTED_GENDER");
        decorator.setFieldValue(smSubmittedGender);

        if (smSubmittedGender == null || smSubmittedGender.trim().length() == 0) {
            return decorator;
        }

        System.out.print("SM_SUBMITTED_GENDER value:" + smSubmittedGender);

        if (smSubmittedGender.trim().length() > DailyDataDictionary.SM_SUBMITTED_GENDER_LEN) {
            decorator.setValid(false);
            decorator.setMessage("SM_SUBMITTED_GENDER Exceeds length 1");
            return decorator;
        }

        return decorator;
    }

    /**
     * validate Submitted CardHolder DOB
     *
     * @param submittedCardHolderDOB
     * @param logger
     * @return
     */
    public ValidationDecorator validateSmSubmittedPatientDOB(String smSubmittedPatientDOB) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("SM_SUBMITTED_PATIENT_DATEOFBIRTH");
        decorator.setFieldValue(smSubmittedPatientDOB);

        if (smSubmittedPatientDOB == null || smSubmittedPatientDOB.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("SM_SUBMITTED_PATIENT_DATEOFBIRTH Value  Missing ");
            return decorator;
        }

        if (smSubmittedPatientDOB.trim().length() > DailyDataDictionary.SM_SUBMITTED_PATIENT_DATEOFBIRTH_LEN) {
            decorator.setMessage("SM_SUBMITTED_PATIENT_DATEOFBIRTH length exceeds");
            decorator.setValid(false);
            return decorator;
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DailyDataDictionary.SM_SUBMITTED_PATIENT_DATEOFBIRTH_FILE_FORMAT);
            simpleDateFormat.setLenient(false);

            if (smSubmittedPatientDOB.trim().length() == 7) {
                smSubmittedPatientDOB = "0" + smSubmittedPatientDOB;
            }

            System.out.print("SM_SUBMITTED_PATIENT_DATEOFBIRTH final value : " + smSubmittedPatientDOB);

            if (smSubmittedPatientDOB.length() != simpleDateFormat.toPattern().length()) {
                throw new Exception();
            }

            simpleDateFormat.parse(smSubmittedPatientDOB);

        } catch (Exception e) {
            decorator.setMessage("SM_SUBMITTED_PATIENT_DATEOFBIRTH invalidate format");
            decorator.setValid(false);
            e.printStackTrace();
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateSubmittedId(String submittedId) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Submitted_Id");
        decorator.setFieldValue(submittedId);

        if (submittedId == null || submittedId.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Submitted_Id value not found");
            return decorator;
        }

        System.out.print("Submitted_Id value:" + submittedId);

        if (submittedId.trim().length() > DailyDataDictionary.SV_SUBMITTED_CARDHOLDERID_LEN) {
            decorator.setValid(false);
            decorator.setMessage("Submitted_Id Exceeds length 20 value : " + submittedId);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateUsualCustomaryAmount(String usualCustomaryAmount) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Usual_Customary_Amount");
        decorator.setFieldValue(usualCustomaryAmount);

        if (usualCustomaryAmount == null || usualCustomaryAmount.trim().length() == 0) {
            decorator.setValid(false);
            decorator.setMessage("Usual_Customary_Amount value not found");
            return decorator;
        }

        usualCustomaryAmount = usualCustomaryAmount.trim();

        Pattern pattern = Pattern.compile("^(\\+|-)?\\d{1,6}+(.\\d{1,2})?$");
        Matcher matcher = pattern.matcher(usualCustomaryAmount);

        if (!matcher.matches()) {
            decorator.setValid(false);
            decorator.setMessage("SP_SUBMITTEDUANDC is not valid decimal value " + usualCustomaryAmount);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateTotalDrugCostPaidToPharmacy(String totalDrugCostPaidToPharmacy) {
        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Total_Drug_Cost_Paid_To_Pharmacy");

        if (totalDrugCostPaidToPharmacy == null || totalDrugCostPaidToPharmacy.trim().length() == 0) {
            decorator.setMessage("Total Drug Cost To Pharmacy value missing");
            decorator.setValid(false);
            return decorator;
        }

        totalDrugCostPaidToPharmacy = totalDrugCostPaidToPharmacy.trim();

        Pattern pattern = Pattern.compile(DailyDataDictionary.TOTAL_DRUG_COXT_PAID_TO_PHARMACY_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(totalDrugCostPaidToPharmacy);

        if (!matcher.matches()) {
            decorator.setMessage("Total Drug Cost To Pharmacy is not in the format dddddd.dd");
            decorator.setValid(false);
            return decorator;
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateClaimAuthorizationNumber(String claimAuthorizationNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Claim_Authorization_Number");
        decorator.setFieldValue(claimAuthorizationNumber);

        System.out.print("Validation of Claim_Authorization_Number  " + claimAuthorizationNumber);

        if (claimAuthorizationNumber != null && claimAuthorizationNumber.trim().length() > 0) {

            if (claimAuthorizationNumber.trim().length() > DailyDataDictionary.CS_CLAIM_AUTHORIZATION_LEN) {
                decorator.setMessage("Claim_Authorization_Number exceeds the max lenth of 20 : " + claimAuthorizationNumber);
                decorator.setValid(false);
                return decorator;
            }
        }

        return decorator;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    public ValidationDecorator validateOrignalClaimAuthorizationNumber(String orignalClaimAuthorizationNumber) {

        ValidationDecorator decorator = new ValidationDecorator();
        decorator.setFieldName("Orignal_Claim_Authorization_Number");
        decorator.setFieldValue(orignalClaimAuthorizationNumber);

        System.out.print("Validation of Orignal_Claim_Authorization_Number  " + orignalClaimAuthorizationNumber);

        if (orignalClaimAuthorizationNumber != null && orignalClaimAuthorizationNumber.trim().length() > 0) {
            orignalClaimAuthorizationNumber = orignalClaimAuthorizationNumber.trim();

            if (orignalClaimAuthorizationNumber.trim().length() > DailyDataDictionary.CS_CLAIM_AUTHORIZATION_LEN) {
                decorator.setMessage("Orignal_Claim_Authorization_Number exceeds the max lenth of 20 : " + orignalClaimAuthorizationNumber);
                decorator.setValid(false);
                return decorator;
            }
        }

        return decorator;
    }

}
