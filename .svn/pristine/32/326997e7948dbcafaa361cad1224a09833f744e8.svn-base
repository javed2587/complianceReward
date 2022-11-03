package com.ssa.cms.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.log4j.Logger;
import com.ssa.cms.common.InstantDataDictionary;
import com.ssa.cms.decorator.ValidationDecorator;

public class InstantRedemptionValidator {
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	public ValidationDecorator validateFillDate(String fillDate) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.FILL_DATE_TAG_NAME);
		decorator.setFieldValue(fillDate);
		
		////logger.info("Validate FillDate (Required) " + fillDate);
		
		if (fillDate == null || fillDate.trim().length() == 0){
			decorator.setMessage("FillDate missing");
			decorator.setValid(false);
			return decorator;
		}

		fillDate = fillDate.trim();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.FILL_DATE_TAG_PATTERN);;
		Matcher matcher = pattern.matcher(fillDate);
		
		if(!matcher.matches()){
			decorator.setMessage("Fill_Date is not in format yyyy-MM-dd value : " + fillDate);
			decorator.setValid(false);
			return decorator;
		}

		return decorator;
	}
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validatePharmacyNPI(String pharmacyNPI) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.PHARMACY_NPI_TAG_NAME);
		decorator.setFieldValue(pharmacyNPI);

		if (pharmacyNPI == null || pharmacyNPI.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("PharmacyNPI value missing");
			return decorator;
		}

		pharmacyNPI = pharmacyNPI.trim();
		Pattern pattern = Pattern.compile(InstantDataDictionary.PHARMACY_NPI_PATTERN);
		Matcher matcher = pattern.matcher(pharmacyNPI);
		
		if(!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("PharmacyNPI not validate value  = " + pharmacyNPI);
			return decorator;
		}
	
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validatePrescriptionNumber(String prescriptionNumber) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.PRESCRIPTION_NUMBER_TAG_NAME);
		decorator.setFieldValue(prescriptionNumber);
		
		////logger.info("Validate Prescription Number " + prescriptionNumber);

		if (prescriptionNumber == null || prescriptionNumber.trim().length() == 0){
				decorator.setValid(false);
				decorator.setMessage("Prescription Number missing");
				return decorator;
		}

		prescriptionNumber = prescriptionNumber.trim();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.PRESCRIPTION_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(prescriptionNumber);
		
		if (!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("Prescription Number not validated (digit 1,12) value = " + prescriptionNumber);
			return decorator;
		}


		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/

	public ValidationDecorator validateClaimStatus(String claimStatus) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.CLAIM_STATUS_TAG_NAME);
		decorator.setFieldValue(claimStatus);
		
		////logger.info("Validate ClaimStatus (B1,B2)" + claimStatus);

		if (claimStatus == null || claimStatus.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("ClaimStatus value is missing");
			return decorator;
		}

		claimStatus = claimStatus.trim();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.CLAIM_STATUS_PATTERN);
		Matcher matcher = pattern.matcher(claimStatus);
		
		if (!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("ClaimStatus has invalid value. Other than B1,B2 Value = " + claimStatus);
			return decorator;
		}

		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateRxGroupNumber(String rxGroupNumber) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.RXGROUP_NUMBER_TAG_NAME);
		decorator.setFieldValue(rxGroupNumber);
		
		////logger.info("Validate RxGroupNumber " + rxGroupNumber);

		if (rxGroupNumber == null || rxGroupNumber.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("RxGroupNumber missing");
			return decorator;
		}
		

		if (rxGroupNumber.trim().length() > InstantDataDictionary.RXGROUP_NUMBER_LENGTH){
			decorator.setValid(false);
			decorator.setMessage("RxGroupNumber exceeds the max lenth of 15 value = " + rxGroupNumber);
			return decorator;
		}


		return decorator;
	}
	
	
	
	
	
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	public ValidationDecorator validateCardholderId(String cardholderId) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.CARDHOLDER_ID_TAG_NAME);
		decorator.setFieldValue(cardholderId);
		
		////logger.info("Validate CardholderId " + cardholderId);

		if (cardholderId == null || cardholderId.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("CardholderId missing");
			return decorator;
		}

		Pattern pattern = Pattern.compile(InstantDataDictionary.CARDHOLDER_ID_PATTERN);
		Matcher matcher = pattern.matcher(cardholderId);
		
		if (!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("CardholderId is not digit value b/w 11 and 20 value = " + cardholderId);
			return decorator;
		}


		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	public ValidationDecorator validateNDCNumber(String ndcNumber) {
	
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.NDC_NUMBER_TAG_NAME);
		decorator.setFieldValue(ndcNumber);
		
		////logger.info("Validate NDCNumber  : " + ndcNumber);

		if (ndcNumber == null || ndcNumber.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("NDCNumber is missing");
			return decorator;
		}

		ndcNumber = ndcNumber.trim();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.NDC_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(ndcNumber);
		
		if(!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("NDCNumber is not digit value b/w 9 and 12 value = " + ndcNumber);
			return decorator;	
		}
		

		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/

	public ValidationDecorator validateQuantity(String quantity) {
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.QUANTITY_TAG_NAME);
		decorator.setFieldValue(quantity);
		
		////logger.info("Validate Quantity : " + quantity);

		if (quantity == null || quantity.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("Quantity missing");
			return decorator;
		}
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.QUANTITY_PATTERN);
		Matcher matcher = pattern.matcher(quantity);
		
		if(!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("Quantity is not in the format dddddd.ddd value = " + quantity);
			return decorator;	
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	

	public ValidationDecorator validateDaysSupply(String daySupply) {
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.DAYS_SUPPLY_TAG_NAME);
		decorator.setFieldValue(daySupply);

		if (daySupply == null || daySupply.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("DaysSupply missing");
			return decorator;
		}
		
		daySupply = daySupply.trim();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.DAYS_SUPPLY_PATTERN);
		Matcher matcher = pattern.matcher(daySupply);
		
		if(!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("DaysSupply is not digit value with max length 2 value = " + daySupply);
			return decorator;	
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateRefillUsed(String refillUsed) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.REFILLS_USED_TAG_NAME);
		decorator.setFieldValue(refillUsed);
		
		////logger.info("Validate RefillsUsed) : " + refillUsed);
				
		if(refillUsed != null && refillUsed.trim().length() > 0){
			
			refillUsed = refillUsed.trim();
			
			Pattern pattern = Pattern.compile(InstantDataDictionary.REFILLS_USED_PATTERN);
			Matcher matcher = pattern.matcher(refillUsed);
		
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage("RefillsUsed is not digit value of max length 2 value = " + refillUsed);
				return decorator;	
			}
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateIngredientCostPaid(String ingredientCostPaid) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.INGREDIENT_COST_PAID_TAG_NAME);
		decorator.setFieldValue(ingredientCostPaid);

		if (ingredientCostPaid == null || ingredientCostPaid.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("IngredientCostPaid value missing");
			return decorator;
		}
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.INGREDIENT_COST_PAID_PATTERN);
		Matcher matcher = pattern.matcher(ingredientCostPaid);
		
		if(!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("IngredientCostPaid is not in the format of dddddd.dd value = " + ingredientCostPaid);
			return decorator;	
		}
		 
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateCopayAmount(String copayAmount) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.COPAY_AMOUNT_TAG_NAME);
		decorator.setFieldValue(copayAmount);

		if (copayAmount == null || copayAmount.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("CopayAmount value missing");
			return decorator;
		}
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.COPAY_AMOUNT_PATTERN);
		Matcher matcher = pattern.matcher(copayAmount);
		
		if(!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("CopayAmount is not in the format of dddddd.dd value = " + copayAmount);
			return decorator;	
		}
		 
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateTransactionNumber(String transactionNumber) {

		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.TRANSACTION_NUMBER_TAG_NAME);
		decorator.setFieldValue(transactionNumber);

		////logger.info("Validation of TransactionNumber  " + transactionNumber );
		
		if (transactionNumber == null || transactionNumber.trim().length() == 0) {
			decorator.setMessage("TransactionNumber not found");
			decorator.setValid(false);
			return decorator;
		}

		transactionNumber = transactionNumber.trim();
		
		if (transactionNumber.trim().length() > InstantDataDictionary.TRANSACTION_NUMBER_LENGTH) {
			decorator.setMessage("TransactionNumber exceeds the max length of 20 value = " + transactionNumber);
			decorator.setValid(false);
			return decorator;
		}


		return decorator;
	}
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateClaimAuthorizationNumber(String claimAuthorizationNumber) {

		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.CLAIM_AUTHORIZATION_NUMBER_TAG_NAME);
		decorator.setFieldValue(claimAuthorizationNumber);

		////logger.info("Validation of ClaimAuthorizationNumber  " + claimAuthorizationNumber );
		
		if (claimAuthorizationNumber == null || claimAuthorizationNumber.trim().length() == 0) {
			decorator.setMessage("ClaimAuthorizationNumber not found");
			decorator.setValid(false);
			return decorator;
		}

		claimAuthorizationNumber = claimAuthorizationNumber.trim();
		
		if (claimAuthorizationNumber.length() > InstantDataDictionary.CLAIM_AUTHORIZATION_NUMBER_LENGTH) {
			decorator.setMessage("Claim Authorization Number exceeds the max length of 20 value = " + claimAuthorizationNumber);
			decorator.setValid(false);
			return decorator;
		}


		return decorator;
	}
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOrignalClaimAuthorizationNumber(String orignalClaimAuthorizationNumber) {

		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.ORIGNAL_CLAIM_AUTHORIZATION_NUMBER_TAG_NAME);
		decorator.setFieldValue(orignalClaimAuthorizationNumber);

		////logger.info("Validation of Orignal ClaimAuthorizationNumber  " + orignalClaimAuthorizationNumber );
		
		if (orignalClaimAuthorizationNumber == null || orignalClaimAuthorizationNumber.trim().length() == 0) {
			decorator.setMessage("OrignalClaimAuthorizationNumber not found");
			decorator.setValid(false);
			return decorator;
		}

		orignalClaimAuthorizationNumber = orignalClaimAuthorizationNumber.trim();
		
		if (orignalClaimAuthorizationNumber.length() > InstantDataDictionary.ORIGNAL_CLAIM_AUTHORIZATION_NUMBER_LENGTH) {
			decorator.setMessage("Orignal Claim Authorization Number exceeds the max length of 20 value = " + orignalClaimAuthorizationNumber);
			decorator.setValid(false);
			return decorator;
		}


		return decorator;
	}
	
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validatePrescriberNPI(String prescriberNPI){
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName("PrescriberNPI");
		decorator.setFieldValue(prescriberNPI);
		
		if(prescriberNPI == null || prescriberNPI.trim().length() == 0){
			decorator.setValid(true);
		}
		
		
		prescriberNPI = prescriberNPI.trim();
		
		if(prescriberNPI.length() > InstantDataDictionary.PRESCRIBER_NPI_LENGTH){
			decorator.setValid(false);
			decorator.setMessage("Prescriber_NPI exceeds max length of 12 value = " + prescriberNPI);
			return decorator;
		}
		
		
		return decorator;
	}
	
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	public ValidationDecorator validatePriceSource(String priceSource) {

		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.PRICE_SOURCE_TAG_NAME);
		decorator.setFieldValue(priceSource);

		if (priceSource != null && priceSource.trim().length() > 0) {
			if (priceSource.trim().length() > InstantDataDictionary.PRICE_SOURCE_LENGTH) {
				decorator.setValid(false);
				decorator.setMessage("PriceSource exceeds the max length of 3 value = " + priceSource);
				return decorator;
			}
		}
		return decorator;
	}
	
	
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId1(String otherPayerId1) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID1_TAG_NAME);
		decorator.setFieldValue(otherPayerId1);

		if (otherPayerId1 != null && otherPayerId1.trim().length() > 0){
			otherPayerId1 = otherPayerId1.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId1);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID1_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId1);
				return decorator;
			}
			
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId2(String otherPayerId2) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID2_TAG_NAME);
		decorator.setFieldValue(otherPayerId2);

		if (otherPayerId2 != null && otherPayerId2.trim().length() > 0){
			otherPayerId2 = otherPayerId2.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId2);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID2_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId2);
				return decorator;
			}
			
		}
		
		return decorator;
	}
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId3(String otherPayerId3) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID3_TAG_NAME);
		decorator.setFieldValue(otherPayerId3);

		if (otherPayerId3 != null && otherPayerId3.trim().length() > 0){
			otherPayerId3 = otherPayerId3.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId3);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID3_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId3);
				return decorator;
			}			
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId4(String otherPayerId4) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID4_TAG_NAME);
		decorator.setFieldValue(otherPayerId4);

		if (otherPayerId4 != null && otherPayerId4.trim().length() > 0){
			otherPayerId4 = otherPayerId4.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId4);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID4_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId4);
				return decorator;
			}			
		}
	
		
		return decorator;
	}
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId5(String otherPayerId5) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID5_TAG_NAME);
		decorator.setFieldValue(otherPayerId5);

		if (otherPayerId5 != null && otherPayerId5.trim().length() > 0){
			otherPayerId5 = otherPayerId5.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId5);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID5_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId5);
				return decorator;
			}			
		}
	
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId6(String otherPayerId6) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID6_TAG_NAME);
		decorator.setFieldValue(otherPayerId6);

		if (otherPayerId6 != null && otherPayerId6.trim().length() > 0){
			otherPayerId6 = otherPayerId6.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId6);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID6_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId6);
				return decorator;
			}			
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId7(String otherPayerId7) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID7_TAG_NAME);
		decorator.setFieldValue(otherPayerId7);

		if (otherPayerId7 != null && otherPayerId7.trim().length() > 0){
			otherPayerId7 = otherPayerId7.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId7);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID6_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId7);
				return decorator;
			}			
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId8(String otherPayerId8) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID8_TAG_NAME);
		decorator.setFieldValue(otherPayerId8);

		if (otherPayerId8 != null && otherPayerId8.trim().length() > 0){
			otherPayerId8 = otherPayerId8.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId8);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID8_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId8);
				return decorator;
			}			
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateOtherPayerId9(String otherPayerId9) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.OTHER_PAYER_ID8_TAG_NAME);
		decorator.setFieldValue(otherPayerId9);

		if (otherPayerId9 != null && otherPayerId9.trim().length() > 0){
			otherPayerId9 = otherPayerId9.trim();
			Pattern pattern = Pattern.compile("^\\d{10,15}$");
			Matcher matcher = pattern.matcher(otherPayerId9);
			
			if(!matcher.matches()){
				decorator.setValid(false);
				decorator.setMessage( InstantDataDictionary.OTHER_PAYER_ID9_TAG_NAME + " exceeds max length of 15 value = " + otherPayerId9);
				return decorator;
			}			
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	public ValidationDecorator validateRxWrittenDate(String rxWrittenDate) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.RX_WRITTEN_DATE_TAG_NAME);
		decorator.setFieldValue(rxWrittenDate);
		
		//logger.info("Validate RxWrittenDate (Required) " + rxWrittenDate);
		
		if (rxWrittenDate == null || rxWrittenDate.trim().length() == 0){
			decorator.setMessage("RxWrittenDate missing");
			decorator.setValid(false);
			return decorator;
		}

		rxWrittenDate = rxWrittenDate.trim();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.RX_WRITTEN_DATE_PATTERN);;
		Matcher matcher = pattern.matcher(rxWrittenDate);
		
		if(!matcher.matches()){
			decorator.setMessage("RxWrittenDate is not in format yyyy-MM-dd " + rxWrittenDate);
			decorator.setValid(false);
			return decorator;
		}

		return decorator;
	}
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	
	
	public ValidationDecorator validatePatientFirstName(String patientFirstName) {
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setValid(true);
		decorator.setFieldName(InstantDataDictionary.PATIENT_FIRST_NAME_TAG_NAME);
		decorator.setFieldValue(patientFirstName);

		
		if (patientFirstName == null || patientFirstName.trim().length() == 0) {
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PATIENT_FIRST_NAME_TAG_NAME + " name is missing");
			return decorator;
		}
		
		patientFirstName = patientFirstName.trim();
		
		if (patientFirstName.length() > InstantDataDictionary.PATIENT_FIRST_NAME_LENGTH) {
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PATIENT_FIRST_NAME_TAG_NAME + " exceeds max length 30 value = " + patientFirstName);
			return decorator;
		}
		
		
		return decorator;
	}
	
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	public ValidationDecorator validatePatientLastName(String patientLastName) {

		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setValid(true);
		decorator.setFieldName(InstantDataDictionary.PATIENT_LAST_NAME_TAG_NAME);
		decorator.setFieldValue(patientLastName);

		if (patientLastName == null || patientLastName.trim().length() == 0) {
			decorator.setValid(false);
			decorator.setMessage("PatientLastName first name is missing");
			return decorator;
		}

		patientLastName = patientLastName.trim();

		if (patientLastName.length() > InstantDataDictionary.PATIENT_LAST_NAME_LENGTH) {
			decorator.setValid(false);
			decorator.setMessage("PatientLastName exceeds max length 30 value = " + patientLastName);
			return decorator;
		}

		return decorator;
	}
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/

	public ValidationDecorator validatePatientDOB(String patientDOB) {

		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.PATIENT_DOB_TAG_NAME);
		decorator.setFieldValue(patientDOB);

		if (patientDOB == null || patientDOB.trim().length() == 0) {
			decorator.setValid(false);
			decorator.setMessage("PatientDOB not found");
			return decorator;
		}

		patientDOB = patientDOB.trim();

		Pattern pattern = Pattern.compile(InstantDataDictionary.PATIENT_DOB_PATTERN);
		Matcher matcher = pattern.matcher(patientDOB);

		if (!matcher.matches()) {
			decorator.setValid(false);
			decorator.setMessage("PatientDOB must be in format yyyy-MM-dd value = " + patientDOB);
			return decorator;
		}

		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	public ValidationDecorator validateCardholderGender(String gender) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.PATIENT_GENDER_TAG_NAME);
		decorator.setFieldValue(gender);

		if (gender == null || gender.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("PatientGender missing");
			return decorator;
		}
		
		gender = gender.toUpperCase();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.PATIENT_GENDER_PATTERN);
		Matcher matcher = pattern.matcher(gender);
		
		if(! matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("PatientGender value found other than 1,2 value = " + gender);
			return decorator;
		}
	
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/

	public ValidationDecorator validatePostingDate(String postingDate) {
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.POSTING_DATE_TAG_NAME);
		decorator.setFieldValue(postingDate);
		
		//logger.info("Validate PostingDate (Required) " + postingDate);
		
		if (postingDate == null || postingDate.trim().length() == 0){
			decorator.setMessage("PostingDate missing");
			decorator.setValid(false);
			return decorator;
		}

		postingDate = postingDate.trim();
		
		Pattern pattern = Pattern.compile(InstantDataDictionary.POSTING_DATE_PATTERN);
		Matcher matcher = pattern.matcher(postingDate);
		
		if(!matcher.matches()){
			decorator.setMessage("PostingDate must be informat yyyy-MM-dd HH:mm:ss value = " + postingDate);
			decorator.setValid(false);
			return decorator;
		}

		return decorator;
	}
	
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/

	public ValidationDecorator validatePrescriberIdQualifier(String prescriberIdQualifier) {
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.PRESCRIBER_ID_QUALIFIER_TAG_NAME);
		decorator.setFieldValue(prescriberIdQualifier);
		
		//logger.info("Validate PrescriberIdQualifier (R) " + prescriberIdQualifier);
		
		if (prescriberIdQualifier == null || prescriberIdQualifier.trim().length() == 0){
			decorator.setMessage("PrescriberIdQualifier missing");
			decorator.setValid(false);
			return decorator;
		}

		prescriberIdQualifier = prescriberIdQualifier.trim();
		
		Pattern pattern = Pattern.compile("^\\d{2}$");
		Matcher matcher = pattern.matcher(prescriberIdQualifier);
		
		if(!matcher.matches()){
			decorator.setMessage("PrescriberIdQualifier must be length of 2");
			decorator.setValid(false);
			return decorator;
		}

		return decorator;
	}
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	public ValidationDecorator validateTotalCostPaidToPharmacy(String totalDrugCostPaidToPharmacy) {
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName("TotDrugCostPaidToPharmacy");
		decorator.setFieldValue(totalDrugCostPaidToPharmacy);

		if (totalDrugCostPaidToPharmacy == null	|| totalDrugCostPaidToPharmacy.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage("Total drug cost paid to pharmacy missing");
			return decorator;
		}
		
		Pattern pattern = Pattern.compile("^(\\+|-)?\\d{1,6}(.\\d{1,3})?$");
		Matcher matcher = pattern.matcher(totalDrugCostPaidToPharmacy);
		
		if(!matcher.matches()){
			decorator.setValid(false);
			decorator.setMessage("Total drug cost paid to pharmacy exceeds length OR not valid number value = " + totalDrugCostPaidToPharmacy);
			return decorator;	
		}
		
		return decorator;
	}
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	
	
	public ValidationDecorator validateSubmittedId(String submittedId) {
		
		ValidationDecorator decorator = new ValidationDecorator();
		decorator.setFieldName(InstantDataDictionary.SUBMITTED_ID);
		decorator.setFieldValue(submittedId);
		
		if(submittedId == null || submittedId.trim().length() == 0){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.SUBMITTED_ID + " value not found");
			return decorator;
		}
		
		//logger.info("SubmittedId value:" + submittedId);
		
		if(submittedId.trim().length() > InstantDataDictionary.SUBMITTED_ID_LEN){
			decorator.setValid(false);
			decorator.setMessage("Submitted_Id Exceeds length 20 value : " + submittedId);
			return decorator;
		}
		
		return decorator;
	}

}
