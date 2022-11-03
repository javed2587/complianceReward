/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import com.ssa.cms.common.Constants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author SSASOFTDHA
 */
public class NotificationUtil {
    public String prepareErrorNotification(int statusCode,long SequenceNumber,String description){
		
		String notificaiton = "";
		
		try{
			
			Document document = new Document();
			Element rootElement = new Element("RequestNotification");
			document.setRootElement(rootElement);
			
			Element sequenceNumber = new Element("SequenceNumber");
			sequenceNumber.setText(SequenceNumber+"");
			rootElement.addContent(sequenceNumber);
			
			Element notificationDate = new Element("NotificationDate");
			notificationDate.setText(new SimpleDateFormat(Constants.EFFECTIVE_DATE_FORMAT).format(new Date()));
			rootElement.addContent(notificationDate);
			
			
			Element notificationStatus = new Element("NotificationStatus");
			notificationStatus.setText(Integer.toString(statusCode));
			rootElement.addContent(notificationStatus);

			
			Element descriptionElement = new Element("Description");
			descriptionElement.setText(description);
			rootElement.addContent(descriptionElement);
			
			
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			
			XMLOutputter outputter = new XMLOutputter();
			outputter.setFormat(Format.getPrettyFormat());
			
			outputter.output(document, printWriter);
			
			notificaiton = stringWriter.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return notificaiton;
	}
}
