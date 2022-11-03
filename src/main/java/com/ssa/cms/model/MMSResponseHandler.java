package com.ssa.cms.model;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class MMSResponseHandler {

	private String mm7Version;
	private String statusCode;
	private String statusText;
	private String messageId;
	private String transactionId;
	private String mustUnderstand;
	private  int numElements = 0;
	long totalRecords = 0;
	
	
	public MMSResponseHandler() {
	}
	
	/**
	 * parse to xml Open market message  response 
	 * @param response
	 * @param logger
	 * @throws JDOMException
	 * @throws IOException
	 */
	
	
	protected static Logger logger = Logger.getLogger(MMSResponseHandler.class);
	
	public  void process (String response){ 
		
		try{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new ByteArrayInputStream(response.getBytes()));
			Element root = doc.getRootElement();
			processElement (root, logger);
			logger.info("Total Number of Elements Processed: "	+ numElements);
		}catch(Exception e){
                    e.printStackTrace();
		}
	}
	
	
        
        
	
	private  void processElement (Element element, Logger logger) {
		numElements++;

		String elementName = element.getName();
		
		if(elementName.equals("TransactionID")){
			List attributes = element.getAttributes();
			setTransactionId(element.getValue());
				logger.info("TransactionID : "+ element.getValue());

			for (int j = 0; j < attributes.size(); j++) { 
				Attribute column = (Attribute) attributes.get(j);
				String ver = column.getName();
				if(ver.equals("mustUnderstand")){
					this.setMustUnderstand(column.getValue());
					logger.info("mustUnderstand : " + column.getValue());
				}
				
			}

		}
		
		if(elementName.equals("MM7Version")){
			this.setMm7Version(element.getValue());
			logger.info("MM7Version : "+this.getMm7Version());			
		}

		if(elementName.equals("StatusCode")){
			this.setStatusCode(element.getValue());
			logger.info("StatusCode : "+element.getValue());
		}
		
		if(elementName.equals("StatusText")){
			
			this.setStatusText(element.getValue());
			logger.info("StatusText : "+element.getValue());
		}
		if(elementName.equals("MessageID")){
			
			this.setMessageId(element.getValue());
			logger.info("MessageID : "+ element.getValue());
		}
				
		List childs = element.getChildren();
		Iterator iterator = childs.iterator();
		while (iterator.hasNext()) {

			Element child = (Element) iterator.next();
			processElement (child, logger);
		}
	}

	/**
	 * @return the mm7Version
	 */
	public String getMm7Version() {
		return mm7Version;
	}

	/**
	 * @param mm7Version the mm7Version to set
	 */
	public void setMm7Version(String mm7Version) {
		this.mm7Version = mm7Version;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the mustUnderstand
	 */
	public String getMustUnderstand() {
		return mustUnderstand;
	}

	/**
	 * @param mustUnderstand the mustUnderstand to set
	 */
	public void setMustUnderstand(String mustUnderstand) {
		this.mustUnderstand = mustUnderstand;
	}
	
	

}
