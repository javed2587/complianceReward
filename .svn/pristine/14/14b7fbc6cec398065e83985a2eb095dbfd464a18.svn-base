package com.ssa.cms.util;

import com.ssa.cms.dto.BasicStatisticsReportDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.dto.PatientProfileDTO;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.NotificationMessages;
import com.ssa.cms.model.Order;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Zubair
 */
public class ExportToExcel extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
//TODO javed Excel library update
        //style.setFillForegroundColor(HSSFColor.BLUE.index);

//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//
       // font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        if (map.get("key").equals("ViewOrders")) {
            response.addHeader("content-disposition", "inline;filename=Orders.xls");
            List<Order> ordersList = (List<Order>) map.get("list");
            downloadOrder(ordersList, workbook, style, dateStyle);
        }
        if (map.get("key").equals("SurveyReports")) {
            response.addHeader("content-disposition", "inline;filename=SurveyReports.xls");
            List<Campaigns> surveyList = (List<Campaigns>) map.get("list");
            downloadSurveyReports(surveyList, workbook, style, dateStyle);
        }
        if (map.get("key").equals("inAppNotificationReports")) {
            response.addHeader("content-disposition", "inline;filename=In App Notification Reports.xls");
            List<NotificationMessages> notificationMessageses = (List<NotificationMessages>) map.get("list");
            downloadInAppNotificationReports(notificationMessageses, workbook, style, dateStyle);
        }
        if (map.get("key").equals("biWeeklyFinancialReport")) {
            response.addHeader("content-disposition", "inline;filename=FinancialReport.xls");
            //document= new Document(PageSize.A4.rotate()); 
            Map financialMap = (Map) map.get("finDataMap");
            //writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
            generateFinancialnReport(workbook, dateStyle, (String) financialMap.get("frmDate"), (String) financialMap.get("toDate"),
                    (String) financialMap.get("cycle"),
                    (List) financialMap.get("list"), (double) financialMap.get("total"), request);
        }
        if (map.get("key").equals("InActivePatientReports")) {
            response.addHeader("content-disposition", "inline;filename=InActivePatientReports.xls");
            List<PatientProfileDTO> inActivePatientList = (List<PatientProfileDTO>) map.get("list");
            downloadInActivePatientReports(inActivePatientList, workbook, style, dateStyle);
        }
        if (map.get("key").equals("basicStatisticsReportData")) {
            response.addHeader("content-disposition", "inline;filename=BasicStatisticsReports.xls");
            BasicStatisticsReportDTO basicStatisticsReportDTO = (BasicStatisticsReportDTO) map.get("list");
            generateBasicStatisticsReports(basicStatisticsReportDTO, workbook, style, dateStyle);
        }
        
         if (map.get("key").equals("pharmacyTransactionalReport")) {
            response.addHeader("content-disposition", "inline;filename=Pharmacy Transaction Report.xls");
            //document= new Document(PageSize.A4.rotate()); 
            Map transMap = (Map) map.get("pharmacyTransactionalDataMap");
            
            generatePharmacyTransactionalReport(workbook, style,(String) transMap.get("frmDate"), (String) transMap.get("toDate"), 
                    (String) transMap.get("pharmacy"),(List) transMap.get("list"), (double) transMap.get("total"), request); 
            //writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);

        }

    }

    /////////////////////////////////////////////////////////////////////////////////////
    public byte[] getImageByte(HttpServletRequest request) throws IOException {
        String filename = "/resources/images/compliance-reward-logo.png";
        ServletContext context = request.getServletContext();
        String pathName = context.getRealPath(filename);
        InputStream inputStream = new FileInputStream(pathName);
        //Get the contents of an InputStream as a byte[].
        byte[] bytes = IOUtils.toByteArray(inputStream);
        inputStream.close();
        return bytes;
    }

    public void generateFinancialnReport(HSSFWorkbook document, CellStyle style, String frmDate, String toDate, String cycle, List<OrderDetailDTO> orderList, double netBalance, HttpServletRequest request) throws Exception {
        //document.setPageSize(PageSize.A4.rotate());
        HSSFSheet sheet = document.createSheet("14-Day Financial Report");

        sheet.setDefaultColumnWidth(50);
        // create header row
        byte[] bytes = getImageByte(request);
        //////////////////////////////////////////////////////////////
        int pictureIdx = document.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        //close the input stream

        //Returns an object that handles instantiating concrete classes
        CreationHelper helper = document.getCreationHelper();
        //Creates the top-level drawing patriarch.
        Drawing drawing = sheet.createDrawingPatriarch();

        //Create an anchor that is attached to the worksheet
        ClientAnchor anchor = helper.createClientAnchor();

        //create an anchor with upper left cell _and_ bottom right cell
        anchor.setCol1(0);
        anchor.setCol2(3);
        anchor.setRow1(0);
        anchor.setRow2(1);

        Picture pict = drawing.createPicture(anchor, pictureIdx);

        //Reset the image to the original size
        pict.resize(); //don't do that. Let the anchor resize the image!

        //Create the Cell B3
//        HSSFCell cell = sheet.createRow(0).createCell(1);
        /////////////////////////////////////////////////////////////
        HSSFRow row2 = sheet.createRow(2);
        HSSFRow row3 = sheet.createRow(3);
        HSSFRow row4 = sheet.createRow(4);
        HSSFRow row5 = sheet.createRow(5);
        row5.createCell(0).setCellValue("CYCLE BEGINNING");
        row5.createCell(1).setCellValue(frmDate);

        HSSFRow row6 = sheet.createRow(6);
        row6.createCell(0).setCellValue("CYCLE ENDING");
        row6.createCell(1).setCellValue(toDate);

        HSSFRow row7 = sheet.createRow(7);
        row7.createCell(0).setCellValue("CYCLE NUMBER :");
        row7.createCell(1).setCellValue(cycle + " of 26");

        HSSFRow header = sheet.createRow(8);

//        document.add(new Paragraph("Financial Report"));
//        document.addTitle("Financial Report");
//        document.add(new Paragraph("CYCLE NUMBER : " + cycle+" of 26"));
//        document.add(new Paragraph("CYCLE BEGINNING : " + frmDate));
//        document.add(new Paragraph("CYCLE ENDING : " + toDate));
//        PdfPTable table = new PdfPTable(19);
//        table.setWidthPercentage(100.0f);
//        table.setWidths(new float[]{4.5f, 5.5f,4.5f,4.5f,4.5f,9.5f,3.5f,4.5f,4.5f,5.5f,
//                        6.0f,7.5f,8.0f,4.5f,4.5f,5.5f,4.5f,5.5f,5.5f});
//        table.setSpacingBefore(10);
        header.createCell(0).setCellValue("Trans.#");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Date");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Rx#");
        header.getCell(2).setCellStyle(style);

        ///////////////////////////////////////////////////////////////
        header.createCell(3).setCellValue("Drug");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Qty");
        header.getCell(4).setCellStyle(style);

        header.createCell(5).setCellValue("Acq");
        header.getCell(5).setCellStyle(style);

        header.createCell(6).setCellValue("Handling Fee");
        header.getCell(6).setCellStyle(style);

        header.createCell(7).setCellValue("Cost");
        header.getCell(7).setCellStyle(style);

        header.createCell(8).setCellValue("Ins");
        header.getCell(8).setCellStyle(style);

        header.createCell(9).setCellValue("Reimb");
        header.getCell(9).setCellStyle(style);

        header.createCell(10).setCellValue("Copay");
        header.getCell(10).setCellStyle(style);

        header.createCell(11).setCellValue("Total Rx Claim");
        header.getCell(11).setCellStyle(style);

        header.createCell(12).setCellValue("Points Redeemed");
        header.getCell(12).setCellStyle(style);

        header.createCell(13).setCellValue("Share Cost");
        header.getCell(13).setCellStyle(style);

        header.createCell(14).setCellValue("Final Payment");
        header.getCell(14).setCellStyle(style);

        header.createCell(15).setCellValue("IMV Fee");
        header.getCell(15).setCellStyle(style);

        header.createCell(16).setCellValue("Pharmacy Profit");
        header.getCell(16).setCellStyle(style);

        header.createCell(17).setCellValue("Pharmacy Remit");
        header.getCell(17).setCellStyle(style);

        //////////////////////////////////////////////////////////////
        int rowCount = 9;

        for (OrderDetailDTO order : orderList) {
//            HSSFRow aRow = sheet.createRow(rowCount++);
//            addStringCell(aRow, 0, order.getTransactionNumber() + "");
//            addStringCell(aRow, 1, order.getOrderDate());
//            addStringCell(aRow, 2, order.getRxNumber());
////            addStringCell(aRow, 2, order.getRequestType());
//
////            addStringCell(aRow, 4, order.getGcn());
//            addStringCell(aRow, 3, order.getDrugName() + " " + order.getDrugType() + " " + order.getStrength());
//            addStringCell(aRow, 4, order.getQty());
//            addStringCell(aRow, 5, AppUtil.roundOffNumberToCurrencyFormat(order.getAcqCost(), "en", "US"));
//            addStringCell(aRow, 6, AppUtil.roundOffNumberToCurrencyFormat(order.getDeliveryFee(), "en", "US"));
//            addStringCell(aRow, 7, AppUtil.roundOffNumberToCurrencyFormat(order.getAcqCost() + order.getDeliveryFee(), "en", "US"));
//            addStringCell(aRow, 8, order.getInsuranceType());
//
//            addStringCell(aRow, 9, AppUtil.roundOffNumberToCurrencyFormat(order.getReimbursementCost(), "en", "US"));
//            double copay = order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d;
//            addStringCell(aRow, 10, AppUtil.roundOffNumberToCurrencyFormat(copay, "en", "US"));
//            addStringCell(aRow, 11, AppUtil.roundOffNumberToCurrencyFormat(copay + order.getReimbursementCost(), "en", "US"));
//            addStringCell(aRow, 12, AppUtil.numberFormat(order.getSharePoints()));
//            addStringCell(aRow, 13, AppUtil.roundOffNumberToCurrencyFormat(order.getSharePointsCost(), "en", "US"));
//
//            addStringCell(aRow, 14, AppUtil.roundOffNumberToCurrencyFormat(order.getFinalPayment(), "en", "US"));
//            addStringCell(aRow, 15, AppUtil.roundOffNumberToCurrencyFormat(order.getImvFee(), "en", "US"));
//            addStringCell(aRow, 16, AppUtil.roundOffNumberToCurrencyFormat(order.getPharmacyProfit(), "en", "US"));
//            addStringCell(aRow, 17, AppUtil.roundOffNumberToCurrencyFormat(order.getRemitPharmacy(), "en", "US"));

            //////////////////////////////////////////////////////////////
        }
        HSSFRow aRow = sheet.createRow(rowCount++);
        addStringCell(aRow, 16, "BALANCE TO IMV ");
        addStringCell(aRow, 17, AppUtil.roundOffNumberToCurrencyFormat(netBalance, "en", "US"));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    private HSSFCell addDateCell(HSSFRow row, int index, Date date,
            HSSFCellStyle dateStyle) {
        HSSFCell cell = row.createCell(index);
        cell.setCellValue(date);
        cell.setCellStyle(dateStyle);
        return cell;
    }

    private HSSFCell addStringCell(HSSFRow row, int index, String value) {
        HSSFCell cell = row.createCell(index);
        cell.setCellValue(new HSSFRichTextString(value));
        return cell;
    }

    private void downloadOrder(List<Order> ordersList, HSSFWorkbook workbook, CellStyle style, HSSFCellStyle dateStyle) {
        HSSFSheet sheet = workbook.createSheet("Orders");
        sheet.setDefaultColumnWidth(30);
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Order Date");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Order #");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Rx #");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Patient Name");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Status");
        header.getCell(4).setCellStyle(style);
// create data rows
        int rowCount = 1;

        for (Order order : ordersList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            addDateCell(aRow, 0, order.getCreatedOn(), dateStyle);
            addStringCell(aRow, 1, order.getId());
            addStringCell(aRow, 2, order.getRxNo());
            addStringCell(aRow, 3, order.getPatientName());
            addStringCell(aRow, 4, order.getOrderStatus().getName());
        }

    }

    private void downloadSurveyReports(List<Campaigns> surveyList, HSSFWorkbook workbook, CellStyle style, HSSFCellStyle dateStyle) {
        HSSFSheet sheet = workbook.createSheet("Survey Reports");
        sheet.setDefaultColumnWidth(50);
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Question(s)");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Total Responses");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Response(s)");
        header.getCell(2).setCellStyle(style);

        // create data rows
        int rowCount = 1;

        for (Campaigns campaigns : surveyList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            addStringCell(aRow, 0, campaigns.getQuestionTitle());
            addStringCell(aRow, 1, campaigns.getGrandTotal());
            addStringCell(aRow, 2, campaigns.getResponseTitle());
        }
    }

    private void downloadInAppNotificationReports(List<NotificationMessages> notificationMessageses, HSSFWorkbook workbook, CellStyle style, HSSFCellStyle dateStyle) throws Exception {
        HSSFSheet sheet = workbook.createSheet("In App notification report");
        sheet.setDefaultColumnWidth(50);
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("DateTime");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Phone#");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Message");
        header.getCell(2).setCellStyle(style);
        // create data rows
        int rowCount = 1;
        for (NotificationMessages messages : notificationMessageses) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            addStringCell(aRow, 0, DateUtil.dateToString(messages.getCreatedOn(), "MM/dd/yyyy HH:mm:ss"));
            addStringCell(aRow, 1, messages.getMobileNumber());
            addStringCell(aRow, 2, messages.getMessageText());
        }
    }

    private void downloadInActivePatientReports(List<PatientProfileDTO> inActivePatientList, HSSFWorkbook workbook, CellStyle style, HSSFCellStyle dateStyle) {
        HSSFSheet sheet = workbook.createSheet("Enrolled Patient No Order Reports");
        sheet.setDefaultColumnWidth(50);
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Patient Name");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("DOB");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Gender");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Mobile");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Email");
        header.getCell(4).setCellStyle(style);

        header.createCell(5).setCellValue("Reg. Date");
        header.getCell(5).setCellStyle(style);

        header.createCell(6).setCellValue("INS");
        header.getCell(6).setCellStyle(style);

        header.createCell(7).setCellValue("Payment Card");
        header.getCell(7).setCellStyle(style);

        header.createCell(8).setCellValue("Allergies");
        header.getCell(8).setCellStyle(style);

        header.createCell(9).setCellValue("Zip");
        header.getCell(9).setCellStyle(style);
        // create data rows
        int rowCount = 1;
        for (PatientProfileDTO ppdto : inActivePatientList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            addStringCell(aRow, 0, ppdto.getPatientName());
            addStringCell(aRow, 1, ppdto.getPatientDOB());
            addStringCell(aRow, 2, ppdto.getGender());
            addStringCell(aRow, 3, CommonUtil.subStringPhone(ppdto.getMobileNumber(), "-"));
            addStringCell(aRow, 4, ppdto.getEmailAddress());
            addStringCell(aRow, 5, ppdto.getRegistrationDate());
            addStringCell(aRow, 6, ppdto.getHasRxCard());
            addStringCell(aRow, 7, ppdto.getHasPaymentCard());
            addStringCell(aRow, 8, ppdto.getAllergies());
            addStringCell(aRow, 9, ppdto.getZip());
        }
    }

    private void generateBasicStatisticsReports(BasicStatisticsReportDTO basicStatisticsReportDTO, HSSFWorkbook workbook, CellStyle style, HSSFCellStyle dateStyle) {
        style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");

        //TODO java excel library update
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        
        generatePatientTypeSheet(workbook, style, basicStatisticsReportDTO);
        //Gender sheet
        generateGenderSheet(workbook, style, basicStatisticsReportDTO);
        //Age By Rx Recipient sheet
        generateAgeByRxRecipientSheet(workbook, style, basicStatisticsReportDTO);
        //Insurance sheet
        generateInsuranceSheet(workbook, style, basicStatisticsReportDTO);
        //Patient Out of Pocket
        generatePatientOutOfPocketSheet(workbook, style, basicStatisticsReportDTO);
        //Rx Mix Sheet
        generateRxMixSheet(workbook, style, basicStatisticsReportDTO);
        //Rx Fulfillment sheet
        generateRxFulfillmentSheet(workbook, style, basicStatisticsReportDTO);
    }

    private void generatePatientTypeSheet(HSSFWorkbook workbook, CellStyle style, BasicStatisticsReportDTO basicStatisticsReportDTO) {
        HSSFSheet sheet = workbook.createSheet("Patient Type");
        sheet.setDefaultColumnWidth(30);
        // create header row
        HSSFRow hSSFRow = sheet.createRow(0);
        hSSFRow.createCell(0).setCellValue("Patient Type");
        hSSFRow.getCell(0).setCellStyle(style);

        hSSFRow.createCell(1).setCellValue("Count");
        hSSFRow.getCell(1).setCellStyle(style);

        HSSFRow header = sheet.createRow(1);
        header.createCell(0).setCellValue("Account Holder");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getAccountHolderNumber()) ? basicStatisticsReportDTO.getAccountHolderNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getAccountHolderCount()) ? basicStatisticsReportDTO.getAccountHolderCount() + "%" : "0%");

        header = sheet.createRow(2);
        header.createCell(0).setCellValue("Child Dependant");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getChildDependentNumber()) ? basicStatisticsReportDTO.getChildDependentNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getChildDependantCount()) ? basicStatisticsReportDTO.getChildDependantCount() + "%" : "0%");

        header = sheet.createRow(3);
        header.createCell(0).setCellValue("Adult Dependant");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getAdultDependentNumber()) ? basicStatisticsReportDTO.getAdultDependentNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getAdultDependantCount()) ? basicStatisticsReportDTO.getAdultDependantCount() + "%" : "0%");

        header = sheet.createRow(4);
        header.createCell(0).setCellValue("Total");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalPatientTypeNumber()) ? basicStatisticsReportDTO.getTotalPatientTypeNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalPatientTypeCount()) ? basicStatisticsReportDTO.getTotalPatientTypeCount() + "%" : "0%");
    }

    private void generateGenderSheet(HSSFWorkbook workbook, CellStyle style, BasicStatisticsReportDTO basicStatisticsReportDTO) {
        HSSFSheet sheet;
        HSSFRow header;
        //Gender sheet
        sheet = workbook.createSheet("Gender");
        sheet.setDefaultColumnWidth(30);
        HSSFRow hSSFRow1 = sheet.createRow(0);

        hSSFRow1.createCell(0).setCellValue("Gender");
        hSSFRow1.getCell(0).setCellStyle(style);

        hSSFRow1.createCell(1).setCellValue("Count");
        hSSFRow1.getCell(1).setCellStyle(style);

        header = sheet.createRow(1);
        header.createCell(0).setCellValue("Male");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalMaleGenderNumber()) ? basicStatisticsReportDTO.getTotalMaleGenderNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalMaleGender()) ? basicStatisticsReportDTO.getTotalMaleGender() + "%" : "0%");

        header = sheet.createRow(2);
        header.createCell(0).setCellValue("Female");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalFemaleGenderNumber()) ? basicStatisticsReportDTO.getTotalFemaleGenderNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalFemaleGender()) ? basicStatisticsReportDTO.getTotalFemaleGender() + "%" : "0%");

        header = sheet.createRow(3);
        header.createCell(0).setCellValue("Total");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalGenderNumber()) ? basicStatisticsReportDTO.getTotalGenderNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalGenderCount()) ? basicStatisticsReportDTO.getTotalGenderCount() + "%" : "0%");
    }

    private void generateAgeByRxRecipientSheet(HSSFWorkbook workbook, CellStyle style, BasicStatisticsReportDTO basicStatisticsReportDTO) {
        HSSFSheet sheet;
        HSSFRow header;
        //Age By Rx Recipient sheet
        sheet = workbook.createSheet("Age By Rx Recipient");
        sheet.setDefaultColumnWidth(30);

        HSSFRow hSSFRow2 = sheet.createRow(0);
        hSSFRow2.createCell(0).setCellValue("Age By Rx Recipient");
        hSSFRow2.getCell(0).setCellStyle(style);

        hSSFRow2.createCell(1).setCellValue("Count");
        hSSFRow2.getCell(1).setCellStyle(style);

        header = sheet.createRow(1);
        header.createCell(0).setCellValue("0-17 Years");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge17Count()) ? basicStatisticsReportDTO.getUnderAge17Count().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge0To17Count()) ? basicStatisticsReportDTO.getUnderAge0To17Count() + "%" : "0%");

        header = sheet.createRow(2);
        header.createCell(0).setCellValue("18 - 34 Years");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge18Count()) ? basicStatisticsReportDTO.getUnderAge18Count().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge18To34Count()) ? basicStatisticsReportDTO.getUnderAge18To34Count() + "%" : "0%");

        header = sheet.createRow(3);
        header.createCell(0).setCellValue("35 - 50 Years");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge35Count()) ? basicStatisticsReportDTO.getUnderAge35Count().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge35To50Count()) ? basicStatisticsReportDTO.getUnderAge35To50Count() + "%" : "0%");

        header = sheet.createRow(4);
        header.createCell(0).setCellValue("51 - 64 Years");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge51Count()) ? basicStatisticsReportDTO.getUnderAge51Count().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge51To64Count()) ? basicStatisticsReportDTO.getUnderAge51To64Count() + "%" : "0%");

        header = sheet.createRow(5);
        header.createCell(0).setCellValue("65+");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge65Count()) ? basicStatisticsReportDTO.getUnderAge65Count().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getAge65MoreCount()) ? basicStatisticsReportDTO.getAge65MoreCount() + "%" : "0%");

        header = sheet.createRow(6);
        header.createCell(0).setCellValue("Total");
        //sheader.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalAgeByRxRecipientNumber()) ? basicStatisticsReportDTO.getTotalAgeByRxRecipientNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalAgeByRxRecipientCount()) ? basicStatisticsReportDTO.getTotalAgeByRxRecipientCount() + "%" : "0%");
    }

    private void generateInsuranceSheet(HSSFWorkbook workbook, CellStyle style, BasicStatisticsReportDTO basicStatisticsReportDTO) {
        HSSFSheet sheet;
        HSSFRow header;
        //Insurance sheet
        sheet = workbook.createSheet("Insurance");
        sheet.setDefaultColumnWidth(30);
        HSSFRow hSSFRow3 = sheet.createRow(0);
        hSSFRow3.createCell(0).setCellValue("Insurance");
        hSSFRow3.getCell(0).setCellStyle(style);

        hSSFRow3.createCell(1).setCellValue("Count");
        hSSFRow3.getCell(1).setCellStyle(style);

        header = sheet.createRow(1);
        header.createCell(0).setCellValue("Commercial Insurance");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getCommercialInusranceNumber()) ? basicStatisticsReportDTO.getCommercialInusranceNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getCommercialInsuranceCount()) ? basicStatisticsReportDTO.getCommercialInsuranceCount() + "%" : "0%");

        header = sheet.createRow(2);
        header.createCell(0).setCellValue("Public");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPublicMedicareNumber()) ? basicStatisticsReportDTO.getPublicMedicareNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPublicMedicareCount()) ? basicStatisticsReportDTO.getPublicMedicareCount() + "%" : "0%");

        header = sheet.createRow(3);
        header.createCell(0).setCellValue("Self pay");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getSelfPayNumber()) ? basicStatisticsReportDTO.getSelfPayNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getSelfPayCount()) ? basicStatisticsReportDTO.getSelfPayCount() + "%" : "0%");

        header = sheet.createRow(4);
        header.createCell(0).setCellValue("Total");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalInsuranceNumber()) ? basicStatisticsReportDTO.getTotalInsuranceNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalInsuranceCount()) ? basicStatisticsReportDTO.getTotalInsuranceCount() + "%" : "0%");
    }

    private void generatePatientOutOfPocketSheet(HSSFWorkbook workbook, CellStyle style, BasicStatisticsReportDTO basicStatisticsReportDTO) {
        HSSFSheet sheet;
        HSSFRow header;
        //Patient Out of Pocket
        sheet = workbook.createSheet("Patient Out of Pocket");
        sheet.setDefaultColumnWidth(30);
        HSSFRow hSSFRow4 = sheet.createRow(0);
        hSSFRow4.createCell(0).setCellValue("Patient Out of Pocket");
        hSSFRow4.getCell(0).setCellStyle(style);

        hSSFRow4.createCell(1).setCellValue("Count");
        hSSFRow4.getCell(1).setCellStyle(style);

        header = sheet.createRow(1);
        header.createCell(0).setCellValue("Zero");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocketNumber()) ? basicStatisticsReportDTO.getPtOutofPocketNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocketCount()) ? basicStatisticsReportDTO.getPtOutofPocketCount() + "%" : "0%");

        header = sheet.createRow(2);
        header.createCell(0).setCellValue("$0.01 -$25");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocket1To25Number()) ? basicStatisticsReportDTO.getPtOutofPocket1To25Number().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocket1To25Count()) ? basicStatisticsReportDTO.getPtOutofPocket1To25Count() + "%" : "0%");

        header = sheet.createRow(3);
        header.createCell(0).setCellValue("$26 - $75");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocket26To75Number()) ? basicStatisticsReportDTO.getPtOutofPocket26To75Number().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocket26To75Count()) ? basicStatisticsReportDTO.getPtOutofPocket26To75Count() + "%" : "0%");

        header = sheet.createRow(4);
        header.createCell(0).setCellValue("$76 or more");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocket76GreaterNumber()) ? basicStatisticsReportDTO.getPtOutofPocket76GreaterNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocket76GreaterCount()) ? basicStatisticsReportDTO.getPtOutofPocket76GreaterCount() + "%" : "0%");

        header = sheet.createRow(5);
        header.createCell(0).setCellValue("Total");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalPtOutofPocketNumber()) ? basicStatisticsReportDTO.getTotalPtOutofPocketNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalPtOutOfPocketCount()) ? basicStatisticsReportDTO.getTotalPtOutOfPocketCount() + "%" : "0%");

    }

    private void generateRxMixSheet(HSSFWorkbook workbook, CellStyle style, BasicStatisticsReportDTO basicStatisticsReportDTO) {
        HSSFSheet sheet;
        HSSFRow header;

        sheet = workbook.createSheet("Rx Mix");
        sheet.setDefaultColumnWidth(30);
        HSSFRow hSSFRow5 = sheet.createRow(0);
        hSSFRow5.createCell(0).setCellValue("Rx Mix");
        hSSFRow5.getCell(0).setCellStyle(style);

        hSSFRow5.createCell(1).setCellValue("Count");
        hSSFRow5.getCell(1).setCellStyle(style);

        header = sheet.createRow(1);
        header.createCell(0).setCellValue("Brand");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getBrandNumber()) ? basicStatisticsReportDTO.getBrandNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getBrandCount()) ? basicStatisticsReportDTO.getBrandCount() + "%" : "0%");

        header = sheet.createRow(2);
        header.createCell(0).setCellValue("Generic");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getGenericNumber()) ? basicStatisticsReportDTO.getGenericNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getGenericCount()) ? basicStatisticsReportDTO.getGenericCount() + "%" : "0%");

        header = sheet.createRow(3);
        header.createCell(0).setCellValue("Total");
        //header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalRxMixNumber()) ? basicStatisticsReportDTO.getTotalRxMixNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalRxMixCount()) ? basicStatisticsReportDTO.getTotalRxMixCount() + "%" : "0%");
    }

    private void generateRxFulfillmentSheet(HSSFWorkbook workbook, CellStyle style, BasicStatisticsReportDTO basicStatisticsReportDTO) {
        HSSFSheet sheet;
        HSSFRow header;

        sheet = workbook.createSheet("Rx Fulfillment");
        sheet.setDefaultColumnWidth(30);
        HSSFRow hSSFRow6 = sheet.createRow(0);
        hSSFRow6.createCell(0).setCellValue("Rx Fulfillment");
        hSSFRow6.getCell(0).setCellStyle(style);

        hSSFRow6.createCell(1).setCellValue("Count");
        hSSFRow6.getCell(1).setCellStyle(style);

        header = sheet.createRow(1);
        header.createCell(0).setCellValue("2nd Day");
       // header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getDay2ndNumber()) ? basicStatisticsReportDTO.getDay2ndNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getDay2ndCount()) ? basicStatisticsReportDTO.getDay2ndCount() + "%" : "0%");

        header = sheet.createRow(2);
        header.createCell(0).setCellValue("Prem-Next Day");
       // header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getNextDayNumber()) ? basicStatisticsReportDTO.getNextDayNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getNextDayCount()) ? basicStatisticsReportDTO.getNextDayCount() + "%" : "0%");

        header = sheet.createRow(3);
        header.createCell(0).setCellValue("Premium-Same Day");
       // header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getSameDayNumber()) ? basicStatisticsReportDTO.getSameDayNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getSameDayCount()) ? basicStatisticsReportDTO.getSameDayCount() + "%" : "0%");

        header = sheet.createRow(4);
        header.createCell(0).setCellValue("Pick Up");
      //  header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPickUpNumber()) ? basicStatisticsReportDTO.getPickUpNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPickUpCount()) ? basicStatisticsReportDTO.getPickUpCount() + "%" : "0%");

        header = sheet.createRow(5);
        header.createCell(0).setCellValue("Total");
       // header.getCell(0).setCellStyle(style);
        addStringCell(header, 1, !CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalRxFulfillmentNumber()) ? basicStatisticsReportDTO.getTotalRxFulfillmentNumber().toString() : "0");
        addStringCell(header, 2, CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalRxFullfillmentCount()) ? basicStatisticsReportDTO.getTotalRxFullfillmentCount() + "%" : "0%");
    }
    
     ////////////////////////////////////////////////////////////////////////
    private void generatePharmacyTransactionalReport(HSSFWorkbook workbook, CellStyle style, String frmDate, String toDate, String pharmacy, List<OrderDetailDTO> orderList, double netBalance, HttpServletRequest request) throws Exception {
        //document.setPageSize(PageSize.A4.rotate());
        HSSFSheet sheet;
        HSSFRow header;

        sheet = workbook.createSheet("Pharmacy Transaction Report");
        sheet.setDefaultColumnWidth(30);
        HSSFRow hSSFRow6 = sheet.createRow(0);
        hSSFRow6.createCell(0).setCellValue("Pharmacy Transaction Report");
        hSSFRow6.getCell(0).setCellStyle(style);
        hSSFRow6.createCell(1).setCellValue("Pharmacy: "+pharmacy);
        hSSFRow6.getCell(1).setCellStyle(style);
        hSSFRow6.createCell(2).setCellValue("From: "+frmDate);
        hSSFRow6.getCell(2).setCellStyle(style);
        hSSFRow6.createCell(3).setCellValue("To: "+toDate);
        hSSFRow6.getCell(3).setCellStyle(style);
        
        header=sheet.createRow(1);
        
        header.createCell(0).setCellValue("Tran#");
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue("Date");
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue("Rx#");
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue("Drug");
        header.getCell(3).setCellStyle(style);
        
        header.createCell(4).setCellValue("Qty");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("Svc Fee");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("Cost");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("Ins");
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue("Reimb");
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue("Copay");
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue("Final Payment");
        header.getCell(10).setCellStyle(style);
        
        int rowCount=2;

        for (OrderDetailDTO order : orderList) 
        {
            
          HSSFRow aRow = sheet.createRow(rowCount);
//          addStringCell(aRow, 0, order.getTransactionNumber() + "");
          addStringCell(aRow, 1, order.getOrderDate());
          addStringCell(aRow, 2, order.getRxNumber());
          addStringCell(aRow, 3, order.getDrugName() + " " + order.getDrugType() + " " + order.getStrength());
//          addStringCell(aRow, 4, order.getQty());
//          addStringCell(aRow, 5, AppUtil.roundOffNumberToCurrencyFormat(order.getDeliveryFee(), "en", "US"));
          
//          addStringCell(aRow, 6, AppUtil.roundOffNumberToCurrencyFormat(order.getAcqCost() + order.getDeliveryFee(), "en", "US"));
//          addStringCell(aRow, 7, order.getInsuranceType());
//          double copay = order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d;
//          addStringCell(aRow, 8,AppUtil.roundOffNumberToCurrencyFormat(copay, "en", "US"));
         
          
//          addStringCell(aRow, 9, AppUtil.roundOffNumberToCurrencyFormat(copay + order.getReimbursementCost(), "en", "US"));
          addStringCell(aRow, 10, AppUtil.roundOffNumberToCurrencyFormat(order.getFinalPayment() != null ? order.getFinalPayment() : 0d, "en", "US"));
          
          rowCount++;
        ///////////////////////////////////////////////////////////////
        }
     
    }
    ////////////////////////////////////////////////////////////////////////
}
