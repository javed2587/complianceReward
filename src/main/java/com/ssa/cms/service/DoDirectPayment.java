package com.ssa.cms.service;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import urn.ebay.api.PayPalAPI.DoDirectPaymentReq;
import urn.ebay.api.PayPalAPI.DoDirectPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CreditCardDetailsType;
import urn.ebay.apis.eBLBaseComponents.CreditCardTypeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoDirectPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;
import urn.ebay.apis.eBLBaseComponents.PayerInfoType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;

//# DoDirectPayment API
// The DoDirectPayment API Operation enables you to process a credit card
// payment.
// This sample code uses Merchant Java SDK to make API call. You can
// download the SDKs
// [here](https://github.com/paypal/sdk-packages/tree/gh-pages/merchant-sdk/java)
public class DoDirectPayment {

    public DoDirectPaymentResponseType doDirectPayment(String cardType, String ccNumber, String expiry,
            String buyerName, String amount, String cvv) {

        Logger logger = Logger.getLogger(this.getClass().toString());

        // ## DoDirectPaymentReq
        DoDirectPaymentReq doDirectPaymentReq = new DoDirectPaymentReq();
        DoDirectPaymentRequestDetailsType doDirectPaymentRequestDetails = new DoDirectPaymentRequestDetailsType();

        // Information about the credit card to be charged.
        CreditCardDetailsType creditCard = new CreditCardDetailsType();
        logger.info("CardType:: " + cardType);
        if (null != cardType) // Type of credit card. For UK, only Maestro, MasterCard, Discover, and
        // Visa are allowable. For Canada, only MasterCard and Visa are
        // allowable and Interac debit cards are not supported. It is one of the
        // following values:
        //
        // * Visa
        // * MasterCard
        // * Discover
        // * Amex
        // * Solo
        // * Switch
        // * Maestro: See note.
        // `Note:
        // If the credit card type is Maestro, you must set currencyId to GBP.
        // In addition, you must specify either StartMonth and StartYear or
        // IssueNumber.`
        {
            getCardType(cardType, creditCard);
        }

        // Credit Card number
        //"4770461107194023"
        creditCard.setCreditCardNumber(ccNumber);

        String[] monthYear = expiry.split("/");
        // ExpiryMonth of credit card
        creditCard.setExpMonth(Integer.parseInt(monthYear[0]));

        // Expiry Year of credit card
        creditCard.setExpYear(Integer.parseInt(monthYear[1]));

        // Details about the owner of the credit card.
        PayerInfoType cardOwner = new PayerInfoType();

        // Email address of buyer.
        cardOwner.setPayerName(null);
        cardOwner.setPayer(buyerName);
        //creditCard.setCardOwner(cardOwner);

        creditCard.setCVV2(cvv);

        doDirectPaymentRequestDetails.setCreditCard(creditCard);

        // How you want to obtain payment. When implementing parallel payments,
        // this field is required and must be set to `Order`. When implementing
        // digital goods, this field is required and must be set to `Sale`. If the
        // transaction does not include a one-time purchase, this field is
        // ignored. It is one of the following values:
        //  
        // * `Sale` - This is a final sale for which you are requesting payment
        // (default).
        // * `Authorization` - This payment is a basic authorization subject to
        // settlement with PayPal Authorization and Capture.
        // * `Order` - This payment is an order authorization subject to
        // settlement with PayPal Authorization and Capture.
        // `Note:
        // You cannot set this field to Sale in SetExpressCheckout request and
        // then change the value to Authorization or Order in the
        // DoExpressCheckoutPayment request. If you set the field to
        // Authorization or Order in SetExpressCheckout, you may set the field
        // to Sale.` 
        doDirectPaymentRequestDetails.setPaymentAction(PaymentActionCodeType.SALE);

        // Information about the payment
        PaymentDetailsType paymentDetails = new PaymentDetailsType();

        // Total cost of the transaction to the buyer. If shipping cost and tax
        // charges are known, include them in this value. If not, this value
        // should be the current sub-total of the order.
        //
        // If the transaction includes one or more one-time purchases, this
        // field must be equal to
        // the sum of the purchases. Set this field to 0 if the transaction does
        // not include a one-time purchase such as when you set up a billing
        // agreement for a recurring payment that is not immediately charged.
        // When the field is set to 0, purchase-specific fields are ignored.
        //
        // * `Currency Code` - You must set the currencyID attribute to one of
        // the
        // 3-character currency codes for any of the supported PayPal
        // currencies.
        // * `Amount`
        BasicAmountType orderTotal = new BasicAmountType(CurrencyCodeType.USD, amount);
        paymentDetails.setOrderTotal(orderTotal);

        // Your URL for receiving Instant Payment Notification (IPN) about this
        // transaction. If you do not specify this value in the request, the
        // notification URL from your Merchant Profile is used, if one exists.
        paymentDetails.setNotifyURL("http://localhost/ipn");

        doDirectPaymentRequestDetails.setPaymentDetails(paymentDetails);

        // IP address of the buyer's browser.
        // `Note:
        // PayPal records this IP addresses as a means to detect possible
        // fraud.`
        doDirectPaymentRequestDetails.setIPAddress("127.0.0.1");

        DoDirectPaymentRequestType doDirectPaymentRequest = new DoDirectPaymentRequestType(doDirectPaymentRequestDetails);
        doDirectPaymentReq.setDoDirectPaymentRequest(doDirectPaymentRequest);

        // ## Creating service wrapper object
        // Creating service wrapper object to make API call and loading
        // configuration file for your credentials and endpoint
        PayPalAPIInterfaceServiceService service = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream("sdk_config.properties");
            service = new PayPalAPIInterfaceServiceService(inputStream);
        } catch (IOException e) {
            logger.error("Error Message : " + e.getMessage());
        }

        DoDirectPaymentResponseType doDirectPaymentResponse = null;
        try {
            // ## Making API call
            // Invoke the appropriate method corresponding to API in service
            // wrapper object
            doDirectPaymentResponse = service.doDirectPayment(doDirectPaymentReq);
        } catch (SSLConfigurationException | InvalidCredentialException | IOException | HttpErrorException | InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException | InterruptedException | OAuthException | ParserConfigurationException | SAXException e) {
            logger.error("Error Message : " + e.getMessage());
        }

        // ## Accessing response parameters
        // You can access the response parameters using getter methods in
        // response object as shown below
        // ### Success values
        if (doDirectPaymentResponse.getAck().getValue().equalsIgnoreCase("success")) {

            // Unique identifier of the transaction
            logger.info("DoDirectPayment Transaction ID :" + doDirectPaymentResponse.getTransactionID());
        } // ### Error Values
        // Access error values from error list using getter methods
        else {
            List<ErrorType> errorList = doDirectPaymentResponse.getErrors();
            logger.error("API Error Message : " + errorList.get(0).getLongMessage());
        }
        return doDirectPaymentResponse;
    }

    public DoDirectPaymentResponseType authorizationRequest(String cardType, String ccNumber, String expiry,
            String buyerName, String cvv) {

        Logger logger = Logger.getLogger(this.getClass().toString());

        // ## DoDirectPaymentReq
        DoDirectPaymentReq doDirectPaymentReq = new DoDirectPaymentReq();
        DoDirectPaymentRequestDetailsType doDirectPaymentRequestDetails = new DoDirectPaymentRequestDetailsType();

        // Information about the credit card to be charged.
        CreditCardDetailsType creditCard = new CreditCardDetailsType();

        getCardType(cardType, creditCard);

        // Credit Card number
        //"4770461107194023"
        logger.info("Credit Card number: " + ccNumber);
        creditCard.setCreditCardNumber(ccNumber);

        String[] monthYear = expiry.split("/");
        logger.info("ExpiryMonth of credit card: " + monthYear[0]);
        // ExpiryMonth of credit card
        creditCard.setExpMonth(Integer.parseInt(monthYear[0]));

        // Expiry Year of credit card
        logger.info("Expiry Year of credit card: " + monthYear[1]);
        creditCard.setExpYear(Integer.parseInt(monthYear[1]));

        // Details about the owner of the credit card.
        PayerInfoType cardOwner = new PayerInfoType();

        // Email address of buyer.
        cardOwner.setPayerName(null);
        logger.info("Card holder name: " + buyerName);
        cardOwner.setPayer(buyerName);
        //creditCard.setCardOwner(cardOwner);

        creditCard.setCVV2(cvv);

        doDirectPaymentRequestDetails.setCreditCard(creditCard);

        // How you want to obtain payment. When implementing parallel payments,
        // this field is required and must be set to `Order`. When implementing
        // digital goods, this field is required and must be set to `Sale`. If the
        // transaction does not include a one-time purchase, this field is
        // ignored. It is one of the following values:
        //  
        // * `Sale` - This is a final sale for which you are requesting payment
        // (default).
        // * `Authorization` - This payment is a basic authorization subject to
        // settlement with PayPal Authorization and Capture.
        // * `Order` - This payment is an order authorization subject to
        // settlement with PayPal Authorization and Capture.
        // `Note:
        // You cannot set this field to Sale in SetExpressCheckout request and
        // then change the value to Authorization or Order in the
        // DoExpressCheckoutPayment request. If you set the field to
        // Authorization or Order in SetExpressCheckout, you may set the field
        // to Sale.` 
        doDirectPaymentRequestDetails.setPaymentAction(PaymentActionCodeType.AUTHORIZATION);

        // IP address of the buyer's browser.
        // `Note:
        // PayPal records this IP addresses as a means to detect possible
        // fraud.`
        doDirectPaymentRequestDetails.setIPAddress("127.0.0.1");

        DoDirectPaymentRequestType doDirectPaymentRequest = new DoDirectPaymentRequestType(doDirectPaymentRequestDetails);
        doDirectPaymentReq.setDoDirectPaymentRequest(doDirectPaymentRequest);

        // ## Creating service wrapper object
        // Creating service wrapper object to make API call and loading
        // configuration file for your credentials and endpoint
        PayPalAPIInterfaceServiceService service = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream("sdk_config.properties");
            service = new PayPalAPIInterfaceServiceService(inputStream);
        } catch (IOException e) {
            logger.error("Error Message : " + e.getMessage());
        }

        DoDirectPaymentResponseType doDirectPaymentResponse = null;
        try {
            // ## Making API call
            // Invoke the appropriate method corresponding to API in service
            // wrapper object
            doDirectPaymentResponse = service.doDirectPayment(doDirectPaymentReq);
        } catch (SSLConfigurationException | InvalidCredentialException | IOException | HttpErrorException | InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException | InterruptedException | OAuthException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            logger.error("Error Message : " + e.getMessage());
            return doDirectPaymentResponse;
        }

        // ## Accessing response parameters
        // You can access the response parameters using getter methods in
        // response object as shown below
        // ### Success values
        if (doDirectPaymentResponse!=null && doDirectPaymentResponse.getAck().getValue().equalsIgnoreCase("success")) {

            // Unique identifier of the transaction
            logger.info("DoDirectPayment Transaction ID :" + doDirectPaymentResponse.getTransactionID());
        } // ### Error Values
        // Access error values from error list using getter methods
        else {
            if(doDirectPaymentResponse!=null && doDirectPaymentResponse.getErrors()!=null &&
                    doDirectPaymentResponse.getErrors().size()>0)
            {
                List<ErrorType> errorList = doDirectPaymentResponse.getErrors();
                logger.error("API Error Message : " + errorList.get(0).getLongMessage());
                System.out.println("ERROR CODE "+ errorList.get(0).getErrorCode()+" ERROR DESCR "+errorList.get(0).getLongMessage());
                //doDirectPaymentResponse.setAVSCode(errorList.get(0).getErrorCode());
                //doDirectPaymentResponse.set
                doDirectPaymentResponse.setErrors(errorList);
            }
            else
            {
                return null;
//                doDirectPaymentResponse.setErrors(null);
            }
        }
        return doDirectPaymentResponse;
    }

    private void getCardType(String cardType, CreditCardDetailsType creditCard) {
        // Type of credit card. For UK, only Maestro, MasterCard, Discover, and
        // Visa are allowable. For Canada, only MasterCard and Visa are
        // allowable and Interac debit cards are not supported. It is one of the
        // following values:
        //
        // * Visa
        // * MasterCard
        // * Discover
        // * Amex
        // * Solo
        // * Switch
        // * Maestro: See note.
        // `Note:
        // If the credit card type is Maestro, you must set currencyId to GBP.
        // In addition, you must specify either StartMonth and StartYear or
        // IssueNumber.`
        if ("VISA".equalsIgnoreCase(cardType)) {
            creditCard.setCreditCardType(CreditCardTypeType.VISA);
        } else if ("AMEX".equalsIgnoreCase(cardType)) {
            creditCard.setCreditCardType(CreditCardTypeType.AMEX);
        } else if ("DISCOVER".equalsIgnoreCase(cardType)) {
            creditCard.setCreditCardType(CreditCardTypeType.DISCOVER);
        } else if ("MASTERCARD".equalsIgnoreCase(cardType)) {
            creditCard.setCreditCardType(CreditCardTypeType.MASTERCARD);
        }
    }
}
