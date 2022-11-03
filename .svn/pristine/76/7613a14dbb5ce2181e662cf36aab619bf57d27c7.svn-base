package com.ssa.cms.util;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.Order;
import java.util.Date;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author msheraz
 */
public class CardConnectUtil {

    /**
     * Authorize Transaction With Capture REST Example
     *
     * @param order
     * @return response
     * @throws Exception
     */
    private static final Logger log = Logger.getLogger(CardConnectUtil.class);

    public static JSONObject authTransactionWithCapture(Order order) throws Exception {
        log.info("\nAuthorization With Capture Request");

        // Create Authorization Transaction request
        JSONObject request = new JSONObject();
        // Merchant ID
        request.put("merchid", Constants.CARDCONNECT_MARCHANTID);
        // Card Type
        request.put("accttype", order.getCardType());
        // Card Number
        request.put("account", order.getCardNumber());
        // Card Expiry
        Date expiry = DateUtil.stringToDateTime(order.getCardExpiry(), "MM/yyyy");
        request.put("expiry", DateUtil.dateToString(expiry, "MMyy")); //0917
        // Card CCV2
        request.put("cvv2", order.getCardCvv());
        // Transaction amount
        request.put("amount", order.getPayment());
        // Transaction currency
        request.put("currency", "USD");
        // Cardholder Name
        request.put("name", order.getCardHolderName());
        // Cardholder Address
        request.put("Street", order.getStreetAddress());
        // Cardholder City
        request.put("city", order.getCity());
        // Cardholder State
        request.put("region", order.getState());
        // Cardholder Country
        request.put("country", "US");
        // Cardholder Zip-Code
        request.put("postal", order.getZip());
        // Return a token for this card number
        request.put("tokenize", "Y");
        // Capture auth
        request.put("capture", "Y");

        // Create the REST client
        CardConnectRestClient client = new CardConnectRestClient(Constants.CARDCONNECT_ENDPOINT, Constants.CARDCONNECT_USERNAME, Constants.CARDCONNECT_PASSWORD);

        // Send an AuthTransaction request
        JSONObject response = client.authorizeTransaction(request);

        return response;
    }

    /**
     * Void Transaction REST Example
     *
     * @param order
     * @return response
     */
    public static JSONObject refundTransaction(Order order) {
        log.info("\nRefund Transaction Request");

        // Create Update Transaction request
        JSONObject request = new JSONObject();
        // Merchant ID
        request.put("merchid", Constants.CARDCONNECT_MARCHANTID);
        // Transaction amount
        request.put("amount", "0");
        // Transaction currency
        request.put("currency", "USD");
        // Return Reference code from authorization request
        request.put("retref", order.getDoDirectTransactionId());

        // Create the CardConnect REST client
        CardConnectRestClient client = new CardConnectRestClient(Constants.CARDCONNECT_ENDPOINT, Constants.CARDCONNECT_USERNAME, Constants.CARDCONNECT_PASSWORD);

        // Send a refundTransaction request
        JSONObject response = client.voidTransaction(request);

        return response;
    }

}
