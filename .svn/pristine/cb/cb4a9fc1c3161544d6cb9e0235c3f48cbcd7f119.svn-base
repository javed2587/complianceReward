
package com.ssa.cms.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class MMSRequestHandler1 {

	
	private String mm7Version;
	private String vasId;
	private String vaspId;
	private String senderAddressShortCode;
	private String recipientsNumber;
	private String displayOnly;
	private String deliveryReport;
	private String contentAllowAdaptations;
	private String href;
	private String transactionId;
	private String mustUnderstand;
	private String subject;
	
	private int numElements = 0;
	long totalRecords = 0;
	
	public MMSRequestHandler1() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * parse to xml Open market message  response 
	 * @param response
	 * @param logger
	 * @throws JDOMException
	 * @throws IOException
	 */
	public  void process (String response) throws JDOMException, IOException { 
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new ByteArrayInputStream(response.getBytes()));
		Element root = doc.getRootElement();
		processElement (root);
		System.out.println("Total Number of Elements Processed: "	+numElements);
	}
	
	/**
	 * parse to xml Open market message  response 
	 * @param element
	 * @param logger
	 */
	private  void processElement (Element element) {
		numElements++;

		String elementName = element.getName();
		
		if(elementName.equals("TransactionID")){
			List attributes = element.getAttributes();
			setTransactionId(element.getValue());
				System.out.println("TransactionID : "+ element.getValue());

			for (int j = 0; j < attributes.size(); j++) { 
				Attribute column = (Attribute) attributes.get(j);
				String ver = column.getName();
				if(ver.equals("mustUnderstand")){
					this.setMustUnderstand(column.getValue());
					System.out.println("mustUnderstand : " + column.getValue());
				}
				
			}

		}
		if(elementName.equals("DeliveryReport")){
			this.setDeliveryReport(element.getValue());
			System.out.println("DeliveryReport : "+element.getValue());
		}
		if(elementName.equals("MM7Version")){
			this.setMm7Version(element.getValue());
			System.out.println("MM7Version : "+this.getMm7Version());			
		}

		if(elementName.equals("VASID")){
			this.setVasId(element.getValue());
			System.out.println("VASID : "+element.getValue());
		}
		
		if(elementName.equals("VASPID")){
			
			this.setVaspId(element.getValue());
			System.out.println("VaspId : "+element.getValue());
		}
		if(elementName.equals("ShortCode")){
			
			this.setSenderAddressShortCode(element.getValue());
			System.out.println("ShortCode : "+ element.getValue());
		}
		
		
		if(elementName.equals("Subject")){
			this.setSubject(element.getValue());
			System.out.println("Subject : "+element.getValue());
		}
		if(elementName.equals("Content")){
			List attributes = element.getAttributes();
			for (int j = 0; j < attributes.size(); j++) { 
			Attribute column = (Attribute) attributes.get(j);
			String ver = column.getName();
			if(ver.equals("allowAdaptations")){
				this.setContentAllowAdaptations(column.getValue());
				System.out.println("AllowAdaptations : "+column.getValue());
			}
			if(ver.equals("href")){
				this.setHref(column.getValue());
				System.out.println("href : "+column.getValue());
			}
		 }
	}		
		if(elementName.equals("Number")){
			this.setRecipientsNumber(element.getValue());
			System.out.println("RecipientNumber : "+element.getValue());
			List attributes = element.getAttributes();
			for (int j = 0; j < attributes.size(); j++) { 
			Attribute column = (Attribute) attributes.get(j);
			String ver = column.getName();
			if(ver.equals("displayOnly")){
				this.setDisplayOnly(column.getValue());
				System.out.println("DisplayOnly : "+column.getValue());
			}
		 }
	}	
		
		List childs = element.getChildren();
		Iterator iterator = childs.iterator();
		while (iterator.hasNext()) {

			Element child = (Element) iterator.next();
			processElement (child);
		}
	}

	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the mM7Version
	 */
	public String getMm7Version() {
		return mm7Version;
	}

	/**
	 * @param mM7Version the mM7Version to set
	 */
	public void setMm7Version(String mm7Version) {
		this.mm7Version = mm7Version;
	}

	/**
	 * @return the vasId
	 */
	public String getVasId() {
		return vasId;
	}

	/**
	 * @param vasId the vasId to set
	 */
	public void setVasId(String vasId) {
		this.vasId = vasId;
	}

	/**
	 * @return the vaspId
	 */
	public String getVaspId() {
		return vaspId;
	}

	/**
	 * @param vaspId the vaspId to set
	 */
	public void setVaspId(String vaspId) {
		this.vaspId = vaspId;
	}

	/**
	 * @return the senderAddressShortCode
	 */
	public String getSenderAddressShortCode() {
		return senderAddressShortCode;
	}

	/**
	 * @param senderAddressShortCode the senderAddressShortCode to set
	 */
	public void setSenderAddressShortCode(String senderAddressShortCode) {
		this.senderAddressShortCode = senderAddressShortCode;
	}

	/**
	 * @return the recipientsNumber
	 */
	public String getRecipientsNumber() {
		return recipientsNumber;
	}

	/**
	 * @param recipientsNumber the recipientsNumber to set
	 */
	public void setRecipientsNumber(String recipientsNumber) {
		this.recipientsNumber = recipientsNumber;
	}

	/**
	 * @return the displayOnly
	 */
	public String getDisplayOnly() {
		return displayOnly;
	}

	/**
	 * @param displayOnly the displayOnly to set
	 */
	public void setDisplayOnly(String displayOnly) {
		this.displayOnly = displayOnly;
	}

	/**
	 * @return the deliveryReport
	 */
	public String getDeliveryReport() {
		return deliveryReport;
	}

	/**
	 * @param deliveryReport the deliveryReport to set
	 */
	public void setDeliveryReport(String deliveryReport) {
		this.deliveryReport = deliveryReport;
	}

	/**
	 * @return the contentAllowAdaptations
	 */
	public String getContentAllowAdaptations() {
		return contentAllowAdaptations;
	}

	/**
	 * @param contentAllowAdaptations the contentAllowAdaptations to set
	 */
	public void setContentAllowAdaptations(String contentAllowAdaptations) {
		this.contentAllowAdaptations = contentAllowAdaptations;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
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

	/**
	 * @return the numElements
	 */
	public  int getNumElements() {
		return numElements;
	}

	/**
	 * @param numElements the numElements to set
	 */
	public  void setNumElements(int numElements) {
		this.numElements = numElements;
	}

	/**
	 * @return the totalRecords
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}


	
	

}
