package com.ssa.cms.common;

public class DailyDataDictionary {
	
	public static final String NUMERIC_REGULAR_EXPRESSION = "^[0-9]*$";
	
	
	public static final String FILL_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final int CS_CLAIM_AUTHORIZATION_INDEX = 0;
	public static final int CS_CLAIM_AUTHORIZATION_LEN = 20;
	
	
	public static final int CS_DATE_PROCESSED_INDEX = 1;
	public static final int CS_DATE_PROCESSED_LEN = 8;
	public static final String CS_DATE_PROCESSED_FILE_FORMAT = "yyyyMMdd HH:mm:ss";
	
	
	// At array index [3];
	public static final int CS_REJECTION_FLAG_INDEX = 3;
	public static final int CS_REJECTION_FLAG_LEN = 1;
	
	// At array index [4];
	public static final int CS_DUPLICATE_FLAG_INDEX = 4;
	public static final int CS_DUPLICATE_FLAG_LEN = 1;
	
	// At array index [5]
	public static final int CS_REVERSAL_FLAG_INDEX = 5;
	public static final int CS_REVERSAL_FLAG_LEN = 1;
	
	
	// At array index [6]
	public static final int CS_PHARMACY_NCPDP_INDEX = 6;
	public static final int PHARMACY_NCPDP_LEN = 15;
	public static final String PHARMACY_NCPDP_REGEX = "^\\d{1,15}$";
	
	
	// At array index [7]
	public static final int PHARMACY_NAME_LEN = 35;
	
	
	// At array index[8]
	public static final int CS_GROUP_NUMBER_INDEX = 8;
	public static final int RX_GROUP_NUMBER_LEN = 15 ;
	
	// At array index[9]
	public static final int CARDHOLDER_ID_LEN = 20;
	 
	// At array index [10]
	// At array index[11]
	public static final int CS_DATE_WRITTEN_INDEX = 11;
	public static final int CS_DATE_WRITTEN_LEN = 8;
	public static final String CS_DATE_WRITTEN_FORMAT = "yyyy-MM-dd";
	
	// At array index [13]
	public static final int CS_RX_NUMBER_INDEX = 12;
	public static final int PRESCRIPTION_NUMBER_LEN = 12;
	
	// At array index [14];
	public static final String FILL_NUMBER_REGEX = "^\\d{1,2}$";
	
	// At array index [15];
	public static final int CS_REFILLS_AUTHORIZED_INDEX = 14;
	public static final String REFILLS_AUTHORIZED_REGEX = "^\\d{1,2}$";
	
	// At array index[16]
	public static final String NDC_NUMBER_REGX = "^\\d{9,19}$";
	
	// At array index[17]
	public static final int DRUG_NAME_LEN = 35;
	
	// At array index [18];
	public static final int DRUG_STRENGTH_LEN = 10;
	
	// At array index [19];
	public static final int DRUG_DOSAGE_DESCRIPTION_LEN = 10;
	
	// At array index [20]
	public static final int CS_QUANTITY_DISPANSED_INDEX = 19;
	public static final int CS_QUANTITY_DISPANSED_LEN = 12;
	public static final String QUANTITY_REGEX = "^(\\+|-)?\\d{1,6}+(.\\d{1,2})?$";
	
	// At array index [21];
	public static final String DAYS_SUPPLY_REGEX = "^(\\+|-)?\\d{1,2}$";
	
	// At array index [22];
	public static final String INGREDIENT_COST_PAID_REGX = "^(\\+|-)?\\d{1,6}(.\\d{1,2})?$";
	
	// At array index [23];
	public static final String DISPENSING_FEE_REGEX = "^(\\+|-)?\\d{1,6}+(.\\d{1,2})?$";
	
	// At array index[24]
	public static final String SALES_TAX_REGEX = "(\\+|-)?\\d{1,6}+(.\\d{1,2})?$";
	
	// At array index[25]
	public static final String INCENTIVE_FEE_REGEX = "^(\\+|-)?\\d{1,6}(.\\d{1,2})?$";
	
	// At array index[26]
	public static final String TOTAL_PATIENT_AMOUNT_REGX = "^(\\+|-)?\\d{1,6}(.\\d{1,2})?$";
	
	
	// At array index[27]
	public static final int PRICE_SOURCE_LEN = 3;
	
	// At array index[28]
	public static final int CS_PHARMACY_NPI_INDEX = 27;
	public static final int PHARMACY_NPI_LEN = 15;
	
	
	public static final int CS_PATIENT_FIRSTNAME_INDEX = 56;
	public static final int CS_PATIENT_FIRSTNAME_LEN = 12;
	
	public static final int CS_PATIENT_LASTNAME_INDEX = 55;
	public static final int CS_PATIENT_LASTNAME_LEN = 15;
	
	// At array index[29]
	public static final int CI_PAPER_CLAIM_FLAG_INDEX = 28;
	public static final int CI_CLAIM_SOURCE_LEN = 20;
	
	// At array index[30]
	public static final int CI_DIRECT_REIMBURSEMENT_FLAG_INDEX = 29;
	public static final int CI_DIRECT_REIMBURSEMENT_FLAG_LEN = 1;
	
	// At array index[31]
	public static final int CI_TEST_CLAIM_FLAG_INDEX = 30;
	public static final int CI_TEST_CLAIM_FLAG_LEN = 1;
	
	// At array index[32]
	public static final int CI_BATCH_PROCESSED_FLAG_INDEX = 31;
	public static final int CI_BATCH_PROCESSED_FLAG_LEN = 1;
	
	// At array index[33]
	public static final int CI_OTHER_PROCESSOR_FLAG_INDEX = 32;
	public static final int CI_OTHER_PROCESSOR_FLAG_LEN = 1;
	
	// At array index[34]
	public static final int CARDHOLDER_LAST_NAME_INDEX = 33;
	public static final int CARDHOLDER_LAST_NAME_LEN = 30;
	
	// At array index[35]
	public static final int CARDHOLDER_FIRST_NAME_INDEX = 34;
	public static final int CARDHOLDER_FIRST_NAME_LEN = 30;
	
	// At array index[36]
	public static final int PT_DATE_OF_BIRTH_INDEX = 35;
	public static final int PT_DATE_OF_BIRTH_LEN = 8;
	public static final String PT_DATE_OF_BIRTH_FILE_FORMAT = "yyyy-MM-dd";
	public static final String PT_DATE_OF_BIRTH_DB_FORMAT = "yyyy-MM-dd";
	
	// At array index[37]
	public static final int PT_GENDER_CODE_INDEX = 36;
	
	// At array index[38]
	public static final int PA_SUBMITTED_NO_INDEX = 37;
	public static final int PA_SUBMITTED_NO_LEN = 11;
	public static final String PA_SUBMITTED_NO_REGEX = "[a-zA-Z]+";
	
	// At array index[39]
	public static final int DC_GCN_CODE_INDEX = 38;
	public static final int DC_GCN_CODE_LEN = 5;
	public static final String DC_GCN_CODE_EXPRESSION = "[a-zA-Z]+";
	
	// At array index[40]
	public static final int DC_STANDARD_THERAPEUTIC_CLASS_INDEX = 39;
	public static final int DC_STANDARD_THERAPEUTIC_CLASS_LEN = 2;
	public static final String DC_STANDARD_THERAPEUTIC_CLASS_REGEX = "[a-zA-Z]+";
	
	// At array index[41]
	public static final int PH_PHYSICAL_ADDRESS_LINE1_INDEX = 40;
	public static final int PH_PHYSICAL_ADDRESS_LINE1_LEN = 35;
	
	// At array index[42]
	public static final int PH_PHYSICAL_ADDRESS_LINE2_INDEX = 41;
	public static final int PH_PHYSICAL_ADDRESS_LINE2_LEN = 35;
	
	// At array index[43]
	public static final int PH_PHYSICAL_CITY_INDEX = 42;
	public static final int PH_PHYSICAL_CITY_LEN = 18;
	
	// At array index[44]
	public static final int PH_PHYSICAL_STATE_INDEX = 43;
	public static final int PH_PHYSICAL_STATE_LEN = 2;
	
	
	// At array index[45]
	public static final int PH_PHYSICAL_ZIPCODE_INDEX = 44;
	
	public static final String PHARMACY_ZIP_CODE_REGX = "^\\d{5}+(-\\d{4})?$";
	public static final String PRESCRIBER_ZIP_CODE_REGX = "^\\d{5}+(-\\d{4})?$";
	
	// At array index[46]
	public static final int PH_PHONE_NUMBER_INDEX = 45;
	public static final int PH_PHONE_NUMBER_LEN = 10;
	public static final String PH_PHONE_NUMBER_REGEX = "\\d{10}";
	
	
	// At array index[47]
	public static final int EP_PRESCRIBER_NAME_INDEX = 46;
	public static final int EP_PRESCRIBER_FIRST_NAME_LEN = 20;
	public static final int EP_PRESCRIBER_LAST_NAME_LEN = 20;
	
	// At array index[48]
	public static final int PR_ADDRESS_LINE1_INDEX = 47;
	public static final int PR_ADDRESS_LINE1_LEN = 40;
	
	// At array index [49]
	public static final int PR_ADDRESS_LINE2_INDEX = 48;
	public static final int PR_ADDRESS_LINE2_LEN = 40;
	
	// At array index[50]
	public static final int PR_CITY_INDEX = 49;
	public static final int PR_CITY_LEN = 24;
	
	// At array index[51]
	public static final int PR_STATE_INDEX = 50;
	public static final int PR_STATE_LEN = 2;
	
	// At array index[52]
	public static final int PR_ZIPCODE_INDEX = 51;
	public static final int PR_ZIPCODE_LEN = 5;
	public static final String PR_ZIPCODE_REGEX = "^\\d{5}$";
	
	// At array index[53]
	public static final int PR_DEA_INDEX = 52;
	public static final int DEA_NUMBER_LENGTH = 9;
	
	// At array index[54]
	public static final int PR_NPI_INDEX = 53;
	public static final int PR_NPI_LEN = 12;
	public static final String PR_NPI_REGEX = "[a-zA-a]+";
	
	// At array index[55]
	public static final int EP_SPECIALTY1_INDEX = 54;
	public static final int EP_SPECIALTY1_LEN = 40;
	
	public static final int SUBMITTED_PATIENT_FIRSTNAME_LEN = 30;
	public static final int SUBMITTED_PATIENT_LASTNAME_LEN = 30;
	
	
	
	// At array index[58]
	public static final int SM_SUBMITTED_PATIENT_DATEOFBIRTH_INDEX = 57;
	public static final int SM_SUBMITTED_PATIENT_DATEOFBIRTH_LEN = 8;
	public static final String SM_SUBMITTED_PATIENT_DATEOFBIRTH_FILE_FORMAT = "yyyyMMdd";
	public static final String SM_SUBMITTED_PATIENT_DATEOFBIRTH_DB_FORMAT = "yyyyMMdd";
	
	// At array index[59]
	public static final int SM_SUBMITTED_GENDER_INDEX = 58;
	public static final int SM_SUBMITTED_GENDER_LEN = 1;

	// At array index[60]
	public static final int SV_SUBMITTED_CARDHOLDERID_INDEX = 59;
	public static final int SV_SUBMITTED_CARDHOLDERID_LEN = 20;
	// At array index[61]
	public static final int SP_SUBMITTEDUANDC_INDEX   = 60;
	public static final int SP_SUBMITTEDUANDC_LEN = 10;
	public static final String SP_SUBMITTEDUANDC_REGEX = "[a-zA-Z]+";
	public static final int CS_CLAIM_STATUS_LEN = 25;
	
	public static final int CS_PATIENT_LAST_NAME_LEN = 15;
	
	public static final int CS_PATIENT_FIRST_NAME_LEN = 30;
	public static final String TOTAL_DRUG_COXT_PAID_TO_PHARMACY_REGULAR_EXPRESSION = "^(\\+|-)?\\d{1,6}+(.\\d{1,2})?$";


}
