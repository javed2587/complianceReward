package com.ssa.cms.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
//import org.apache.log4j.Logger;
import org.jdom.Element;
import com.ssa.cms.common.InstantDataDictionary;
import com.ssa.cms.decorator.ValidationDecorator;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.InstantRedemptionId;
import java.math.BigDecimal;

public class InstantValidationUtil {

	String fileName = "";
	public InstantValidationUtil() {

	}

	/******************************************************************************************/
	/*******************          Validation of IRF Records       *****************************/
	/******************************************************************************************/

	public ValidationDecorator validateIRFRecord(Element rootElement){

		ValidationDecorator decorator = new ValidationDecorator();
		SimpleDateFormat simpleDateFormat = null;
		InstantRedemption instantRedemptionFile = new InstantRedemption();
                InstantRedemptionId id = new InstantRedemptionId();
		InstantRedemptionValidator irfValidtator = new InstantRedemptionValidator();

                instantRedemptionFile.setId(id);
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <FillDate>2012-05-01</FillDate>
		// Validation Of FillDate (R) 
		/*********************************************************************************************/
		/*********************************************************************************************/

		Element element = rootElement.getChild(InstantDataDictionary.FILL_DATE_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.FILL_DATE_TAG_NAME + " tag missing");
			return decorator;
		}

		String fillDate = element.getValue();

		decorator = irfValidtator.validateFillDate(fillDate);

		if(!decorator.isValid()){
			return decorator;
		}

		try{

			fillDate = fillDate.trim();

			simpleDateFormat = new SimpleDateFormat(InstantDataDictionary.FILL_DATE_FORMAT);
			Date fdate = simpleDateFormat.parse(fillDate);
			instantRedemptionFile.setFillDate(fdate);
		}catch(Exception e){
			//logger.info(LogUtil.getStackTraceAsString(e));
		}

		
		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PharmacyNPI>1790882496</PharmacyNPI>
		// Validation Of PharmacyNPI (R) (A/N,10)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PHARMACY_NPI_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PHARMACY_NPI_TAG_NAME + " tag missing");
			return decorator;
		}
		
		String pharmacyNPI = element.getValue();
		decorator = irfValidtator.validatePharmacyNPI(pharmacyNPI);

		if(!decorator.isValid()){
			return decorator;
		}

		pharmacyNPI = pharmacyNPI.trim();

		instantRedemptionFile.setPharmacyNpi(pharmacyNPI);

		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PrescriptionNumber>409871</PrescriptionNumber>
		// Validation of PrescriptionNumber (R) (N , 12)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PRESCRIPTION_NUMBER_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PRESCRIPTION_NUMBER_TAG_NAME + " tag missing");
			return decorator;
		}

		String prescriptionNumber = element.getValue();
		decorator = irfValidtator.validatePrescriptionNumber(prescriptionNumber);

		if(!decorator.isValid()){
			return decorator;
		}
		prescriptionNumber = prescriptionNumber.trim();
		instantRedemptionFile.setPrescriptionNumber(prescriptionNumber);

		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <ClaimStatus>B1</ClaimStatus>
		// Validation Of ClaimStatus (R)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.CLAIM_STATUS_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.CLAIM_STATUS_TAG_NAME + " tag missing");
			return decorator;
		}

		String claimStatusString = element.getValue();
		decorator = irfValidtator.validateClaimStatus(claimStatusString);

		if(!decorator.isValid()){
			return decorator;
		}

		claimStatusString = claimStatusString.trim();
		
		int claimStatus = 0;

		if(claimStatusString.equals("B2")) {
			claimStatus = 1;
		}

		instantRedemptionFile.getId().setClaimStatus(claimStatus);


		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <TransactionResponseStatus>P</TransactionResponseStatus>
		// Validation Of TransactionResponseStatus (R)
		/*********************************************************************************************/
		/*********************************************************************************************/

		/*
		
		element = rootElement.getChild(InstantDataDictionary.TRANSACTION_RESPONSE_STATUS_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.TRANSACTION_RESPONSE_STATUS_TAG_NAME + " tag missing");
			return decorator;
		}

		String transactionResponseStatusString = element.getValue();
		decorator = irfValidtator.validateTransactionResponseStatus(transactionResponseStatusString);

		if(!decorator.isValid()){
			return decorator;
		}

		instantRedemptionFile.setTransactionResponseStatus(transactionResponseStatusString);
		
		*/
		
		


		/*********************************************************************************************/
		/*********************************************************************************************/
		// <RxGroupNumber>EL76001001</RxGroupNumber>
		// Validation Of RxGroupNumber (R) (AN , 10)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.RXGROUP_NUMBER_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.RXGROUP_NUMBER_TAG_NAME + " tag missing");
			return decorator;
		}

		String rxGroupNumber = element.getValue();

		decorator = irfValidtator.validateRxGroupNumber(rxGroupNumber);

		if(!decorator.isValid()){
			return decorator;
		}
		rxGroupNumber = rxGroupNumber.trim();

		instantRedemptionFile.setRxGroupNumber(rxGroupNumber);

		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <CardholderId>68550548332</CardholderId>
		// Validation Of CardholderId (R) (Numeric, 11)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.CARDHOLDER_ID_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage("CardholderId tag missing");
			return decorator;
		}

		
		String cardholderId = element.getValue();
		decorator = irfValidtator.validateCardholderId(cardholderId);

		if(!decorator.isValid()){
			return decorator;
		}
		cardholderId = cardholderId.trim();
		instantRedemptionFile.setCardholderId(cardholderId);
		

		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <NDCNumber>20482000230</NDCNumber>
		// Validation Of `NDCNumber` (R) (N, 19)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.NDC_NUMBER_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.NDC_NUMBER_TAG_NAME + " tag missing");
			return decorator;
		}

		String ndcNumber = element.getValue();

		decorator = irfValidtator.validateNDCNumber(ndcNumber);
		if(!decorator.isValid()){
			return decorator;
		}

		ndcNumber = ndcNumber.trim();

		instantRedemptionFile.setNdcNumber(ndcNumber);

		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <Quantity>60.000</Quantity>
		// Validation Of Quantity (R)(dddddd.ddd)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.QUANTITY_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.QUANTITY_TAG_NAME + " tag missing");
			return decorator;
		}

		String quantity = element.getValue();
		decorator = irfValidtator.validateQuantity(quantity);

		if(! decorator.isValid()){
			return decorator;
		}
		
		quantity = quantity.trim();

		double longQuantity = Double.parseDouble(quantity);

		if(longQuantity < 0){
			longQuantity = longQuantity * -1;
		}

		instantRedemptionFile.setQuantity(longQuantity);
		
		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <DaysSupply>30</DaysSupply>
		// Validation Of DaysSupply (R) (N )
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.DAYS_SUPPLY_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.DAYS_SUPPLY_TAG_NAME + " tag missing");
			return decorator;
		}
		
		String daysSupply = element.getValue();
		decorator = irfValidtator.validateDaysSupply(daysSupply);

		if(!decorator.isValid()){
			return decorator;
		}
		
		daysSupply = daysSupply.trim();
		int daysSupplyInt = Integer.parseInt(daysSupply);
		
		if(daysSupplyInt < 0){
			daysSupplyInt = daysSupplyInt * -1;
		}
		instantRedemptionFile.setDaysSupply(daysSupplyInt);

		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <RefillsUsed>0</RefillsUsed>
		// Validation of RefillsUsed (O) (N , 2)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.REFILLS_USED_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.REFILLS_USED_TAG_NAME + " tag missing");
			return decorator;
		}

		String refillsUsed = element.getValue();
		decorator = irfValidtator.validateRefillUsed(refillsUsed);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(refillsUsed, decorator);
		}

		if(refillsUsed ==  null || refillsUsed.trim().length() == 0){
			refillsUsed = "0";
		}else{
			refillsUsed = refillsUsed.trim();
		}

		int refillsUsedInt = Integer.parseInt(refillsUsed);
		if(refillsUsedInt < 0){
			refillsUsedInt = refillsUsedInt * -1;
		}
		instantRedemptionFile.setRefillsUsed(refillsUsedInt);

		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <IngredientCostPaid>1767.22</IngredientCostPaid>
		// Validation Of IngredientCostPaid (R) (D,10)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.INGREDIENT_COST_PAID_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.INGREDIENT_COST_PAID_TAG_NAME + " tag missing");
			return decorator;
		}
		
		String ingredientCostPaid = element.getValue();
		decorator = irfValidtator.validateIngredientCostPaid(ingredientCostPaid);

		if(!decorator.isValid()){
			return decorator;
		}

		ingredientCostPaid = ingredientCostPaid.trim();
		double ingredientCostPaidDouble = Double.parseDouble(ingredientCostPaid);

		if(ingredientCostPaidDouble <0){
			ingredientCostPaidDouble = ingredientCostPaidDouble * -1;
		}

		instantRedemptionFile.setIngredientCostPaid(new BigDecimal(ingredientCostPaidDouble));
		
		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <CopayAmount>0.00</CopayAmount>
		// Validation Of CopayAmount (R) (D,10)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.COPAY_AMOUNT_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.COPAY_AMOUNT_TAG_NAME + " tag missing");
			return decorator;
		}
		
		String copayAmount = element.getValue();
		decorator = irfValidtator.validateCopayAmount(copayAmount);

		if(!decorator.isValid()){
			return decorator;
		}

		copayAmount = copayAmount.trim();
		double copayAmountDouble = Double.parseDouble(copayAmount);

		if(copayAmountDouble <0){
			copayAmountDouble = copayAmountDouble * -1;
		}

		//instantRedemptionFile.setIngredientCostPaid(copayAmountDouble);
		instantRedemptionFile.setPtOutOfPocket(new BigDecimal(copayAmountDouble));
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <TransactionNumber>U12122TA8F2600</TransactionNumber>
		// Validation Of TransactionNumber Required (AN,20)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.TRANSACTION_NUMBER_TAG_NAME);

		if( element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.TRANSACTION_NUMBER_TAG_NAME + " tag missing");
			return decorator;
		}

		
		String transactionNumber = element.getValue();
		decorator = irfValidtator.validateTransactionNumber(transactionNumber);

		if(!decorator.isValid()){
			return decorator;
		}
		transactionNumber = transactionNumber.trim();
		instantRedemptionFile.getId().setTransactionNumber(transactionNumber);

		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <ClaimAuthorizationNumber>U12144R9A62200</ClaimAuthorizationNumber>
		// Validation Of TransactionNumber Required (AN,20)
		/*********************************************************************************************/
		/*********************************************************************************************/
		/*
		element = rootElement.getChild(InstantDataDictionary.CLAIM_AUTHORIZATION_NUMBER_TAG_NAME);

		if( element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.CLAIM_AUTHORIZATION_NUMBER_TAG_NAME + " tag missing");
			return decorator;
		}

		
		String claimAuthorizationNumber = element.getValue();
		decorator = irfValidtator.validateClaimAuthorizationNumber(claimAuthorizationNumber);

		if(!decorator.isValid()){
			return decorator;
		}
		claimAuthorizationNumber = claimAuthorizationNumber.trim();
		instantRedemptionFile.setClaimAuthorizationNumber(claimAuthorizationNumber);
		*/
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <ClaimAuthorizationNumber>U12144R9A62200</ClaimAuthorizationNumber>
		// Validation Of TransactionNumber Required (AN,20)
		/*********************************************************************************************/
		/*********************************************************************************************/
		/*
		element = rootElement.getChild(InstantDataDictionary.ORIGNAL_CLAIM_AUTHORIZATION_NUMBER_TAG_NAME);

		if( element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.ORIGNAL_CLAIM_AUTHORIZATION_NUMBER_TAG_NAME + " tag missing");
			return decorator;
		}

		
		String orignalClaimAuthorizationNumber = element.getValue();
		decorator = irfValidtator.validateClaimAuthorizationNumber(orignalClaimAuthorizationNumber);

		if(!decorator.isValid()){
			return decorator;
		}
		orignalClaimAuthorizationNumber = orignalClaimAuthorizationNumber.trim();
		instantRedemptionFile.setOrignalClaimAuthorizationNumber(orignalClaimAuthorizationNumber);
		
		*/
		
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PrescriberNPI>1306844808</PrescriberNPI>
		// Validation Of PrescriberNPI (O) (N,10)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PRESCRIBER_NPI_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage( InstantDataDictionary.PRESCRIBER_NPI_TAG_NAME + " tag missing");
			return decorator;
		}

		String prescriberNPI = element.getValue();
		decorator = irfValidtator.validatePrescriberNPI(prescriberNPI);
		
		if(!decorator.isValid()){
			this.sendDRFWarningAlert(prescriberNPI, decorator);
		}
		
		if(prescriberNPI != null && prescriberNPI.trim().length() > 0){
			prescriberNPI = prescriberNPI.trim();
		}
		
		instantRedemptionFile.setPrescriberNpi(prescriberNPI);


		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PriceSource>13</PriceSource>
		// Validation Of PriceSource (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PRICE_SOURCE_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PRICE_SOURCE_TAG_NAME + " tag missing");
			return decorator;
		}

		String priceSource = element.getValue();
		decorator = irfValidtator.validatePriceSource(priceSource);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(priceSource, decorator);

		}

		if(decorator.isValid()){
			if(priceSource != null && priceSource.trim().length() > 0){
				priceSource = priceSource.trim();
				instantRedemptionFile.setPriceSource(priceSource);
			}
		}


		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId1>000000</OtherPayerId1>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID1_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID1_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId1 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId1(otherPayerId1);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId1, decorator);

		}


		if(otherPayerId1 != null && otherPayerId1.trim().length() > 0){
			otherPayerId1 = otherPayerId1.trim();
			instantRedemptionFile.setOtherPayerId1(otherPayerId1);
		}
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId2>000000</OtherPayerId2>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID2_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID2_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId2 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId2(otherPayerId2);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId2, decorator);

		}


		if(otherPayerId2 != null && otherPayerId2.trim().length() > 0){
			otherPayerId2 = otherPayerId2.trim();
			instantRedemptionFile.setOtherPayerId2(otherPayerId2);
		}
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId3>000000</OtherPayerId3>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID3_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID3_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId3 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId3(otherPayerId3);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId3, decorator);
		}


		if(otherPayerId3 != null && otherPayerId3.trim().length() > 0){
			otherPayerId3 = otherPayerId3.trim();
			instantRedemptionFile.setOtherPayerId3(otherPayerId3);
		}
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId4>000000</OtherPayerId4>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID4_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID4_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId4 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId4(otherPayerId4);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId4, decorator);
		}


		if(otherPayerId4 != null && otherPayerId4.trim().length() > 0){
			otherPayerId4 = otherPayerId4.trim();
			instantRedemptionFile.setOtherPayerId4(otherPayerId4);
		}
		
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId5>000000</OtherPayerId5>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID5_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID5_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId5 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId5(otherPayerId5);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId5, decorator);
		}


		if(otherPayerId5 != null && otherPayerId5.trim().length() > 0){
			otherPayerId5 = otherPayerId5.trim();
			instantRedemptionFile.setOtherPayerId5(otherPayerId5);
		}
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId6>000000</OtherPayerId6>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID6_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID6_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId6 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId6(otherPayerId6);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId6, decorator);
		}


		if(otherPayerId6 != null && otherPayerId6.trim().length() > 0){
			otherPayerId6 = otherPayerId6.trim();
			instantRedemptionFile.setOtherPayerId6(otherPayerId6);
		}
		
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId7>000000</OtherPayerId7>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID7_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID7_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId7 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId7(otherPayerId7);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId7, decorator);
		}


		if(otherPayerId7 != null && otherPayerId7.trim().length() > 0){
			otherPayerId7 = otherPayerId7.trim();
			instantRedemptionFile.setOtherPayerId7(otherPayerId7);
		}
		
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId8>000000</OtherPayerId8>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID8_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID8_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId8 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId8(otherPayerId8);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId8, decorator);
		}


		if(otherPayerId8 != null && otherPayerId8.trim().length() > 0){
			otherPayerId8 = otherPayerId8.trim();
			instantRedemptionFile.setOtherPayerId8(otherPayerId8);
		}
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <OtherPayerId9>000000</OtherPayerId9>
		// Validation Of OtherPayerId1 (O) (AN,3)
		/*********************************************************************************************/
		/*********************************************************************************************/ 
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_ID9_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_ID9_TAG_NAME + " tag missing");
			return decorator;
		}

		String otherPayerId9 = element.getValue();
		
		//need to change Validation

		decorator = irfValidtator.validateOtherPayerId9(otherPayerId9);

		if(!decorator.isValid()){
			this.sendDRFWarningAlert(otherPayerId9, decorator);
		}


		if(otherPayerId9 != null && otherPayerId9.trim().length() > 0){
			otherPayerId9 = otherPayerId9.trim();
			instantRedemptionFile.setOtherPayerId9(otherPayerId9);
		}
		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <RxWrittenDate>2012-05-01</RxWrittenDate>
		// Validation RxWrittenDate (Required) (Date,10)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.RX_WRITTEN_DATE_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.RX_WRITTEN_DATE_TAG_NAME + " tag missing");
			return decorator;
		}

		String rxDateWritten = element.getValue();
		decorator = irfValidtator.validateRxWrittenDate(rxDateWritten);

		if(!decorator.isValid()){
			return decorator;
		}

		try{
			rxDateWritten = rxDateWritten.trim();
			simpleDateFormat = new SimpleDateFormat(InstantDataDictionary.RX_WRITTEN_DATE_FORMAT);
			Date rxWrittenDateDate = simpleDateFormat.parse(rxDateWritten);
			instantRedemptionFile.setRxWrittenDate(rxWrittenDateDate);
		}catch(Exception e){
			//logger.info(LogUtil.getStackTraceAsString(e));
		}

		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PatientFirstName>JANICE</PatientFirstName>
		// Submitted Patient First Name Optional max length 30 (R)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PATIENT_FIRST_NAME_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PATIENT_FIRST_NAME_TAG_NAME + " tag missing");
			return decorator;
		}


		String patientFirstName = element.getValue();
		decorator = irfValidtator.validatePatientFirstName(patientFirstName);
		
		if(!decorator.isValid()){
			this.sendDRFWarningAlert(patientFirstName, decorator);
		}
		
		if(patientFirstName != null && patientFirstName.trim().length() > 0){
			patientFirstName = patientFirstName.trim();
		}
		
		instantRedemptionFile.setCardholderFirstName(patientFirstName);



		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PatientLastName>JANICE</PatientLastName>
		// Submitted Patient Last Name Optional max length 30 (R)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PATIENT_LAST_NAME_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PATIENT_LAST_NAME_TAG_NAME + " tag missing");
			return decorator;
		}

		String patientLastName = element.getValue();
		decorator = irfValidtator.validatePatientLastName(patientLastName);
		
		if(!decorator.isValid()){
			this.sendDRFWarningAlert(patientLastName, decorator);
		}
		
		if(patientLastName != null && patientLastName.trim().length() > 0){
			patientLastName = patientLastName.trim();
		}
		instantRedemptionFile.setCardholderLastName(patientLastName);

		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PatientDOB>1951-11-20</PatientDOB>
		// Validation Of PatientDOB (R) (date,8) // Skipped
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PATIENT_DOB_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PATIENT_DOB_TAG_NAME + " tag missing");
			return decorator;
		}
		
		String patientDOB = element.getValue();
		decorator = irfValidtator.validatePatientDOB(patientDOB);

		if(!decorator.isValid()){
			return decorator;
		}

		try{
			simpleDateFormat = new SimpleDateFormat(InstantDataDictionary.PATIENT_DOB_FORMAT);
			simpleDateFormat.setLenient(false);
			Date dob = simpleDateFormat.parse(patientDOB);
			instantRedemptionFile.setCardholderDob(dob);
		}catch(Exception e){
			//logger.info(LogUtil.getStackTraceAsString(e));
		}

		
		
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PatientGender>2</PatientGender>
		// Validation Of PatientGender (R) (N,1)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PATIENT_GENDER_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage( InstantDataDictionary.PATIENT_GENDER_TAG_NAME + " tag missing");
			return decorator;
		}

		String cardholderGender = element.getValue();
		decorator = irfValidtator.validateCardholderGender(cardholderGender);

		if(!decorator.isValid()){
			return decorator;
		}

		cardholderGender = cardholderGender.trim();
		
		String gender = "M";
		if(cardholderGender.equals("2")){
			gender = "F";		
		} 

		instantRedemptionFile.setCardholderGender(gender.charAt(0));

		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PostingDate>2012-05-07 21:47:48</PostingDate>
		// Validation Of posting date (R)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.POSTING_DATE_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.POSTING_DATE_TAG_NAME + " tag missing");
			return decorator;
		}

		
		String postingDateStr = element.getValue();
		decorator = irfValidtator.validatePostingDate(postingDateStr);

		if(!decorator.isValid()){
			return decorator;
		}

		try{
			simpleDateFormat = new SimpleDateFormat(InstantDataDictionary.POSTING_DATE_FORMAT);
			simpleDateFormat.setLenient(true);
			Date postingDate = simpleDateFormat.parse(postingDateStr);
			instantRedemptionFile.setPostingDate(postingDate);
		}catch(Exception e){
			//logger.info(LogUtil.getStackTraceAsString(e));
		}
		
		
		
		
		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// <PrescriberIdQualifier>01</PrescriberIdQualifier>
		// Validation Of PrescriberIdQualifier (R) (D ,2)
		/*********************************************************************************************/
		/*********************************************************************************************/
		element = rootElement.getChild(InstantDataDictionary.PRESCRIBER_ID_QUALIFIER_TAG_NAME);

		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.PRESCRIBER_ID_QUALIFIER_TAG_NAME + " tag missing");
			return decorator;
		}

		String prescriberIdQualifier = element.getValue();
		decorator = irfValidtator.validatePrescriberIdQualifier(prescriberIdQualifier);
		
		if(!decorator.isValid()) {
			return decorator;
		}
		
		prescriberIdQualifier = prescriberIdQualifier.trim();
		
		//instantRedemptionFile.setPrescriber(prescriberIdQualifier);
		
		// 12 = DEA Number
		// 01 = NPI
		if(prescriberIdQualifier.equalsIgnoreCase("12")){
			
		  prescriberNPI = instantRedemptionFile.getPrescriberNpi();
		  
		  instantRedemptionFile.setPrescriberNpi(null);
		  instantRedemptionFile.setDeaNumber(prescriberNPI);
		  
		}else if(prescriberIdQualifier.equalsIgnoreCase("01")){
			prescriberNPI = instantRedemptionFile.getPrescriberNpi();
			  
			instantRedemptionFile.setPrescriberNpi(prescriberNPI);
			instantRedemptionFile.setDeaNumber(null);
		}
		
		
		
		if(claimStatus == 1){
			instantRedemptionFile.setReversalDate(instantRedemptionFile.getPostingDate());
		}
		

		/*********************************************************************************************/
		/*********************************************************************************************/
		// Calculational field
		// Validation Of Total Drug Cost Paid To Pharmacy (Required) (Decimal) (123456.12)
		/*********************************************************************************************/
		/*********************************************************************************************/
		
		float totalDrugCostPaidToPharmacyDouble = (float) (ingredientCostPaidDouble - copayAmountDouble);
		
		String totalDrugCostPaidToPharmacyStr = totalDrugCostPaidToPharmacyDouble+"";
		
		
		decorator = irfValidtator.validateTotalCostPaidToPharmacy(totalDrugCostPaidToPharmacyStr.trim());
		
		if(!decorator.isValid()){
			return decorator;
		}
		instantRedemptionFile.setTotDrugCostPaidToPharmacy(new BigDecimal(totalDrugCostPaidToPharmacyDouble));

		
		/*********************************************************************************************/
		/*********************************************************************************************/
		// Validation Of SubmittedId (R) (AN,20) 
		// IRF,DRF
		/*********************************************************************************************/
		/*********************************************************************************************/
		
		element = rootElement.getChild(InstantDataDictionary.SUBMITTED_ID);
		
		if(element == null){
			decorator.setValid(false);
			decorator.setMessage("SubmittedId tag missing");
			return decorator;
		}
		
		String submittedId = element.getValue();
		decorator = irfValidtator.validateSubmittedId(submittedId);
		
		if(!decorator.isValid()){
			return decorator;
		}
		submittedId = submittedId.trim();
		
		if(submittedId.length() == 11)
			submittedId = submittedId.substring(1);
		
		instantRedemptionFile.setSubmittedId(submittedId);
		
		element = rootElement.getChild(InstantDataDictionary.OTHER_PAYER_REJECTION_CODE_TAG_NAME);
		
		if(element == null){
			decorator.setValid(false);
			decorator.setMessage(InstantDataDictionary.OTHER_PAYER_REJECTION_CODE_TAG_NAME + " tag missing");
			return decorator;
		}
		
		String otherPayerRejectionCode = element.getValue();		
		instantRedemptionFile.setOtherPayerRejectionCode(otherPayerRejectionCode);
		
		decorator.setResult(instantRedemptionFile);
		decorator.setValid(true);
		return decorator;
	}
	
	
	
	
	/*********************************************************************************************/
	/*********************************************************************************************/
	/*********************************************************************************************/
	
	

	private boolean sendDRFWarningAlert(String fieldValue, ValidationDecorator decorator){

		boolean isSent = false;
		String warnEmailSubject = "Emdeon IRF Warning - " + fileName;
		String message = "Automated warning message for the problem occurred during Emdeon IRF processing <br/> ";
		message = message + "(Record is saved as result of this warning)";

		String error = decorator.getMessage();
		String fieldName = decorator.getFieldName();
		String fieldTrace = "N/A";
		//String mailBody = EmailUtil.createEmailBodyValidationWarning("Emdeon DRF Processor", message, error, fieldName, fieldValue, fieldTrace, fileName);
		//isSent = EmailSender.sendErrorEmail(warnEmailSubject, mailBody); 
		return isSent;
	}


}
