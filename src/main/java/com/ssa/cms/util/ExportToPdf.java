package com.ssa.cms.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.ssa.cms.common.Constants;
import com.ssa.cms.dto.BasicStatisticsReportDTO;
import com.ssa.cms.dto.NotificationMessagesDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.dto.PatientProfileDTO;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.Order;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

/**
 *
 * @author Zubair
 */
public class ExportToPdf extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Image image = getImage(request);

        Font headingFont = getHeaderFont();

        PdfPCell headingCell = getHeadingCell();

        PdfPCell evenDataCell = new PdfPCell();
        evenDataCell.setBackgroundColor(new BaseColor(237, 237, 240));
        evenDataCell.setPadding(5);
        evenDataCell.setBorderColor(BaseColor.LIGHT_GRAY);

        Font dataFont = getDataFont();
        PdfPCell dataCell = getDataCell();

        if (model.get("key").equals("ViewOrders")) {
            response.addHeader("content-disposition", "inline;filename=Orders.pdf");
            List<Order> ordersList = (List<Order>) model.get("list");
            downloadOrder(ordersList, document, headingFont, headingCell, dataFont, dataCell, evenDataCell, image);
        }
        if (model.get("key").equals("SurveyReports")) {
            response.addHeader("content-disposition", "inline;filename=SurveyReports.pdf");
            List<Campaigns> surveyList = (List<Campaigns>) model.get("list");
            downloadSurveyReports(surveyList, document, headingFont, headingCell, dataFont, dataCell, evenDataCell, image);
        }
        if (model.get("key").equals("YearEndStatement")) {
            response.addHeader("content-disposition", "inline;filename=YearEndStatement.pdf");
            List<OrderDetailDTO> orderDetailDTOList = (List<OrderDetailDTO>) model.get("list");
            populateYearEndStatementDetail(document, request, new Date(), orderDetailDTOList);
        }
        if (model.get("key").equals("inAppNotificationReports")) {
            response.addHeader("content-disposition", "inline;filename=InAppNotificationReports.pdf");
            List<NotificationMessagesDTO> notificationMessagesesList = (List<NotificationMessagesDTO>) model.get("list");
            generateInAppNotificationReport(document, new Date(), notificationMessagesesList, request);
        }
        if (model.get("key").equals("biWeeklyFinancialReport")) {
            response.addHeader("content-disposition", "inline;filename=FinancialReport.pdf");
            //document= new Document(PageSize.A4.rotate()); 
            Map map = (Map) model.get("finDataMap");
            //writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
            generateFinancialnReport(document, (String) map.get("frmDate"), (String) map.get("toDate"), (String) map.get("cycle"),
                    (List) map.get("list"), (double) map.get("total"), request, map, writer);
        }
        if (model.get("key").equals("pharmacyTransactionalReport")) {
            response.addHeader("content-disposition", "inline;filename=PharmacyReport.pdf");
            //document= new Document(PageSize.A4.rotate()); 
            Map map = (Map) model.get("pharmacyTransactionalDataMap");
            //writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
            generatePharmacyTransactionalReport(document, (String) map.get("frmDate"), (String) map.get("toDate"), (String) map.get("pharmacy"),
                    (List) map.get("list"), (double) map.get("total"), request);
//            this.addFooterToDocument(document);
        }
        if (model.get("key").equals("InActivePatientReports")) {
            response.addHeader("content-disposition", "inline;filename=InActivePatientReports.pdf");
            List<PatientProfileDTO> inActivePatientList = (List<PatientProfileDTO>) model.get("list");
            downloadInActivePatientReports(inActivePatientList, document, headingFont, headingCell, dataFont, dataCell, evenDataCell, image);
        }
        if (model.get("key").equals("basicStatisticsReportData")) {
            response.addHeader("content-disposition", "inline;filename=BasicStatisticsReports.pdf");
            BasicStatisticsReportDTO basicStatisticsReportDTO = (BasicStatisticsReportDTO) model.get("list");
            generateBasicStatisticsReports(basicStatisticsReportDTO, document, getDataFont("Segoe UI", BaseColor.WHITE, 14.0f), headingCell, getDataFont(FontFactory.TIMES_ROMAN, BaseColor.BLACK, 12.0f), dataCell, evenDataCell, image);
        }
        if (model.get("key").equals("patientBasicReportData")) {
            response.addHeader("content-disposition", "inline;filename=PatientBasicReports.pdf");
            List<OrderDetailDTO> listofPatientBasicDTO = (List<OrderDetailDTO>) model.get("list");
            generatePatientBasicReports(listofPatientBasicDTO, document, getDataFont("Segoe UI", BaseColor.WHITE, 14.0f), headingCell, getDataFont(FontFactory.TIMES_ROMAN, BaseColor.BLACK, 12.0f), dataCell, evenDataCell, image);
        }
        if (model.get("key").equals("patientEnhancedReportData")) {
            response.addHeader("content-disposition", "inline;filename=PatientEnhancedReports.pdf");
            List<OrderDetailDTO> listofPatientBasicDTO = (List<OrderDetailDTO>) model.get("list");
            generatePatientEnhancedReports(listofPatientBasicDTO, document, getDataFont("Segoe UI", BaseColor.WHITE, 14.0f), headingCell, getDataFont(FontFactory.TIMES_ROMAN, BaseColor.BLACK, 12.0f), dataCell, evenDataCell, image);
        }
    }

    public PdfPCell getDataCell() {
        PdfPCell dataCell = new PdfPCell();
        dataCell.setPadding(5);
        dataCell.setBorderColor(BaseColor.LIGHT_GRAY);
        return dataCell;
    }

    public Font getDataFont() {
        Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        dataFont.setColor(BaseColor.BLACK);
        dataFont.setSize(8.0f);
        return dataFont;
    }

    public Font getDataFont(String font, BaseColor color, float size) {
        Font dataFont = FontFactory.getFont(font);
        dataFont.setColor(color);
        dataFont.setSize(size);
        return dataFont;
    }

    public PdfPCell getHeadingCell() {
        PdfPCell headingCell = new PdfPCell();
        headingCell.setBackgroundColor(new BaseColor(49, 147, 161));
        headingCell.setPadding(5);
        headingCell.setBorderColor(BaseColor.LIGHT_GRAY);
        return headingCell;
    }

    public PdfPCell getHeadingCellTop() {
        PdfPCell headingCell = new PdfPCell();
        headingCell.setBackgroundColor(BaseColor.WHITE);
        headingCell.setPadding(5);
        headingCell.setBorderColor(BaseColor.WHITE);
        return headingCell;
    }

    public Font getParaGraphColors() {
        Font colorFont = new Font();
        colorFont.setColor(BaseColor.BLUE);
        colorFont.setSize(9.5f);
        return colorFont;

    }

    public Font getHeaderFont() {
        Font headingFont = FontFactory.getFont("Segoe UI");
        headingFont.setColor(BaseColor.WHITE);
        headingFont.setSize(10.0f);
        return headingFont;
    }

    public Image getImage(HttpServletRequest request) throws BadElementException, IOException {
        String filename = "/resources/images/compliance-reward-logo.png";
        ServletContext context = request.getServletContext();
        String pathname = context.getRealPath(filename);
        Image image = Image.getInstance(pathname);
        image.scaleAbsolute(239f, 46f);
        return image;
    }

    public Image getRxRedImage(HttpServletRequest request) throws BadElementException, IOException {
        String filename = "/resources/images/redRxlogo.PNG";
        ServletContext context = request.getServletContext();
        String pathname = context.getRealPath(filename);
        Image image = Image.getInstance(pathname);
        image.scaleAbsolute(239f, 46f);
        return image;
    }

    private void downloadOrder(List<Order> ordersList, Document document, Font headingFont, PdfPCell headingCell, Font dataFont, PdfPCell dataCell, PdfPCell evenDataCell, Image image) throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();

        document.add(image);

        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));

        document.add(new Paragraph("View Orders"));
        document.addTitle("View Orders");
        document.add(new Paragraph("Date : " + dateFormat.format(date)));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{2.0f, 2.5f, 2.0f, 3.0f, 2.0f});
        table.setSpacingBefore(10);

        headingCell.setPhrase(new Phrase("Order Date", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Order #", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Rx #", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Patient Name", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Status", headingFont));
        table.addCell(headingCell);

        int number = 0;

        for (Order order : ordersList) {

            int quotient = number / 2;
            if (quotient * 2 == number) {
                dataCell.setPhrase(new Phrase(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getId(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getRxNo(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getPatientName(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getOrderStatus().getName(), dataFont));
                table.addCell(dataCell);
                number++;
            } else {
                dataCell.setPhrase(new Phrase(DateUtil.dateToString(order.getCreatedOn(), "MM/dd/yyyy"), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getId(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getRxNo(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getPatientName(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(order.getOrderStatus().getName(), dataFont));
                table.addCell(dataCell);
                number++;
            }
        }

        document.add(table);

    }

    private void downloadSurveyReports(List<Campaigns> surveyList, Document document, Font headingFont, PdfPCell headingCell, Font dataFont, PdfPCell dataCell, PdfPCell evenDataCell, Image image) throws DocumentException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();

        document.add(image);

        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));

        document.add(new Paragraph("Survey Reports"));
        document.addTitle("Survey Reports");
        document.add(new Paragraph("Date : " + dateFormat.format(date)));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.5f, 2.0f, 3.0f});
        table.setSpacingBefore(10);

        headingCell.setPhrase(new Phrase("Question(s)", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Total Responses", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Response(s)", headingFont));
        table.addCell(headingCell);

        int number = 0;

        for (Campaigns campaigns : surveyList) {

            int quotient = number / 2;
            if (quotient * 2 == number) {
                dataCell.setPhrase(new Phrase(campaigns.getQuestionTitle(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(campaigns.getGrandTotal(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(campaigns.getResponseTitle(), dataFont));
                table.addCell(dataCell);
                number++;
            } else {
                dataCell.setPhrase(new Phrase(campaigns.getQuestionTitle(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(campaigns.getGrandTotal(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(campaigns.getResponseTitle(), dataFont));
                table.addCell(dataCell);
                number++;
            }
        }

        document.add(table);
    }

    public String downlaodYearEndStatement(List<OrderDetailDTO> list, Integer patientProfileId, HttpServletRequest request, Log logger) throws Exception {
        String fileName = null;
        try {
            Date date = new Date();
            String time = DateUtil.dateToString(date, Constants.EFFECTIVE_DATE_FORMAT);
            time = time.replace(" ", "_");
            fileName = "YearEndStatement" + patientProfileId + time + ".pdf";
            File file = new File(PropertiesUtil.getProperty("INSURANCE_CARD_PATH") + fileName);
            file.createNewFile();
            String output = CommonUtil.executeCommand(Constants.COMMAND);
            logger.info("Command Result:: " + output);

            FileOutputStream fileout = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter.getInstance(document, fileout);
            document.open();

            populateYearEndStatementDetail(document, request, date, list);
            document.close();
            return fileName;
        } catch (IOException | DocumentException e) {
            logger.error("Exception:: downlaodYearEndStatement", e);
            e.printStackTrace();
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public void populateYearEndStatementDetail(Document document, HttpServletRequest request, Date date, List<OrderDetailDTO> list) throws DocumentException, Exception, IOException {
        document.add(getRxRedImage(request));

        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));

        document.add(new Paragraph("Year End Statement"));
        document.addTitle("Year End Statement");
        document.add(new Paragraph("Date : " + DateUtil.dateToString(date, Constants.USA_DATE_FORMATE)));

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.5f, 1.7f, 2.0f, 2.0f, 2.3f, 2.0f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        PdfPCell headingCell = getHeadingCell();
        Font headingFont = getHeaderFont();

//        headingCell.setPhrase(new Phrase("Rx#", headingFont));
//        table.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Drug Name", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Out of Pocket", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("3rd Party", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Order Date", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Points Redeemed", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Points Cost", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Final Price", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("HandLing Fee", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Total Payment", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        Font dataFont = getDataFont();
        PdfPCell dataCell = getDataCell();

        list.stream().map((detailDTO) -> {
            dataCell.setPhrase(new Phrase(detailDTO.getDrugName(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            return detailDTO;
        }).map((detailDTO) -> {
            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(detailDTO.getOriginalPtCopayStr(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return detailDTO;
        }).map((detailDTO) -> {
            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(detailDTO.getRxReimbCost(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return detailDTO;
        }).map((detailDTO) -> {
            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(detailDTO.getOrderDate(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(AppUtil.convertNumberToThousandSeparatedFormat(
////                    AppUtil.getSafeInt(detailDTO.getRedeemPoints(), 0)), dataFont));
////            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
////            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(detailDTO.getRedeemPointsCost(), "en", "us"), dataFont));
//            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            return detailDTO;
        }).map((detailDTO) -> {
            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(detailDTO.getFinalPayment(), "en", "us"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return detailDTO;
        }).map((detailDTO) -> {
            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(detailDTO.getHandLingFee(), "en", "us"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return detailDTO;

        }).map((detailDTO) -> {
            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(detailDTO.getPaymentIncludingShipping(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return detailDTO;

        }).forEach((_item) -> {
            table.addCell(dataCell);
        });
        document.add(table);
    }

    ///////////////////////////////////////////////////////////////////////////////////
//    public void populateYearEndStatementDetail(Document document, HttpServletRequest request, Date date, List<OrderDetailDTO> list) throws DocumentException, Exception, IOException {
//        document.add(getImage(request));
//
//        LineSeparator ls = new LineSeparator();
//        document.add(new Chunk(ls));
//
//        document.add(new Paragraph("Year End Statement"));
//        document.addTitle("Year End Statement");
//        document.add(new Paragraph("Date : " + DateUtil.dateToString(date, Constants.USA_DATE_FORMATE)));
//
//        PdfPTable table = new PdfPTable(9);
//        table.setWidthPercentage(100.0f);
//        table.setWidths(new float[]{3.5f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f});
//        table.setSpacingBefore(10);
//
//        PdfPCell headingCell = getHeadingCell();
//        Font headingFont = getHeaderFont();
//        headingCell.setPhrase(new Phrase("Drug Name", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Mfr Cost", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Selling Price", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Order Date", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Drug Price", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("HandLing Fee", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Redeem Points Cost", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Profit Margin", headingFont));
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Final Payment", headingFont));
//        table.addCell(headingCell);
//
//        Font dataFont = getDataFont();
//        PdfPCell dataCell = getDataCell();
//
//        list.stream().map((detailDTO) -> {
//            dataCell.setPhrase(new Phrase(detailDTO.getDrugName(), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(CommonUtil.getDecimalFormat(detailDTO.getMfrCost()), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(CommonUtil.getDecimalFormat(detailDTO.getPriceIncludingMargins()), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(detailDTO.getOrderDate(), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(CommonUtil.getDecimalFormat(detailDTO.getDrugPrice()), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(CommonUtil.getDecimalFormat(detailDTO.getHandLingFee()), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(CommonUtil.getDecimalFormat(detailDTO.getRedeemPointsCost()), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(CommonUtil.getDecimalFormat(detailDTO.getProfitMargin()), dataFont));
//            return detailDTO;
//        }).map((detailDTO) -> {
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(CommonUtil.getDecimalFormat(detailDTO.getFinalPayment()), dataFont));
//            return detailDTO;
//        }).forEach((_item) -> {
//            table.addCell(dataCell);
//        });
//        document.add(table);
//    }
    public String downloadInAppNotificationReport(List<NotificationMessagesDTO> messagesesList, HttpServletRequest request) {
        String fileName = "";
        try {
            Date date = new Date();
            String time = DateUtil.dateToString(date, Constants.EFFECTIVE_DATE_FORMAT);
            time = time.replace(" ", "_");
            time = time.replace(":", "_");
            fileName = "InAppNotificationReport" + time + ".pdf";
            File file = new File("D:\\" + fileName);
            file.createNewFile();
            try (FileOutputStream fileout = new FileOutputStream(file)) {
                Document document = new Document();
                PdfWriter.getInstance(document, fileout);
                document.open();

                //document.add(getImage(request));
                generateInAppNotificationReport(document, date, messagesesList, request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Export To Pdf::downloadInAppNotificationReport:: ", e);
            return null;
        }
        return fileName;
    }

    public void generateInAppNotificationReport(Document document, Date date, List<NotificationMessagesDTO> messagesesList, HttpServletRequest request) throws DocumentException, Exception {
        document.add(getImage(request));
        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));

        document.add(new Paragraph("In App Notification Report"));
        document.addTitle("In App Notification Report");
        document.add(new Paragraph("Date : " + DateUtil.dateToString(date, Constants.USA_DATE_FORMATE)));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{1.5f, 1.5f, 5.5f, 1.5f});
        table.setSpacingBefore(10);

        PdfPCell headingCell = getHeadingCell();
        Font headingFont = getHeaderFont();
        headingCell.setPhrase(new Phrase("Date Time", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Phone", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Message", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Response", headingFont));
        table.addCell(headingCell);

        Font dataFont = getDataFont();
        PdfPCell dataCell = getDataCell();

        for (NotificationMessagesDTO messages : messagesesList) {
            logger.info("NotificationMessages Id:: " + messages.getMessageId());
            String html = "<html><body>" + AppUtil.getSafeStr(messages.getMessageText(), "") + "</body></html>";
            //logger.info("Message text is# " + html);
            if (html.contains("<hr>")) {
                html = html.replace("<hr>", "");
            }
            List<Element> element = HTMLWorker.parseToList(new StringReader(html), null);
            //HTMLWorker hTMLWorker = new HTMLWorker(document);
            //hTMLWorker.parse(new StringReader(html));

            dataCell.setPhrase(new Phrase(DateUtil.dateToString(messages.getCreatedOn(), Constants.USA_DATE_TIME_FORMATE), dataFont));
            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(AppUtil.getSafeStr(messages.getMobileNumber(), ""), dataFont));
            table.addCell(dataCell);
            if (element != null && !element.isEmpty()) {
                element.stream().forEach((e) -> {
                    dataCell.addElement(e);
                });
            }
            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(AppUtil.getSafeStr(messages.getMessageResponses(), ""), dataFont));
            table.addCell(dataCell);
        }
        document.add(table);
        document.close();
    }

    public void generateFinancialnReport(Document document, String frmDate, String toDate,
            String cycle, List<OrderDetailDTO> orderList, double netBalance,
            HttpServletRequest request,
            Map map, PdfWriter writer) throws DocumentException, Exception {
        //document.setPageSize(PageSize.A4.rotate());
        RotatePdf rotation = new RotatePdf();
        writer.setPageEvent(rotation);
//        document.setPageSize(PageSize.LEGAL);
//        document.setPageSize(PageSize.LEGAL.rotate());

        PdfPTable tableHeader = new PdfPTable(3);

        tableHeader.setWidthPercentage(100.0f);
        tableHeader.setWidths(new float[]{2.5f, 4.5f, 3.5f});
        tableHeader.setSpacingBefore(10);

        PdfPCell headingCellHeader = getHeadingCellTop();
        PdfPCell cell1 = new PdfPCell(getImage(request));
        cell1.setBackgroundColor(BaseColor.WHITE);
        cell1.setBorderColor(BaseColor.WHITE);
        tableHeader.addCell(cell1);
        tableHeader.addCell(headingCellHeader);
        headingCellHeader.setPhrase(new Phrase("Financial Report" + "\n\n" + "Pharmacy Name: " + map.get("pharmacy") + "\nAddress: " + map.get("address") + "\nZip: " + map.get("zip") + "\nSite Contact: " + map.get("contact") + "\nCYCLE BEGINNING : " + frmDate + "\n" + "CYCLE ENDING: " + toDate + "\n" + "CYCLE NUMBER: " + cycle + " of 26"));
        tableHeader.addCell(headingCellHeader);
        document.add(tableHeader);

        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));

        Font headingFontForAbreviation = getParaGraphColors();
        PdfPTable tableAbrreviation = new PdfPTable(2);
        tableAbrreviation.setWidthPercentage(100.0f);
        tableAbrreviation.setWidths(new float[]{3.1f, 8.0f});
        tableAbrreviation.setSpacingBefore(10);

        headingCellHeader.setPhrase(new Phrase("Abbreviation for following columns :" + "\n"));
        tableAbrreviation.addCell(headingCellHeader);
        headingCellHeader.setPhrase(new Phrase("Transaction#: Tran# , Acquisition Cost : ACQ , " + "Insurance : Ins , " + "Reimbursement Cost : Reimb , " + "Points Redeemed : Pt R , " + "Final Payment : F P, " + "Pharmacy Profit : Phmcy Profit, " + " Pharmacy Remit : Phmcy Remit", headingFontForAbreviation));
        tableAbrreviation.addCell(headingCellHeader);
        document.add(tableAbrreviation);

        PdfPTable table = new PdfPTable(19);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{6.0f, 4.2f, 4.0f, 4.5f, 6.0f, 3.5f, 5.6f, 4.8f, 5.5f,
            3.6f, 5.5f, 6.0f, 5.5f, 6.0f, 5.0f, 5.5f, 5.0f, 5.5f, 5.5f});
        table.setSpacingBefore(10);

        PdfPCell headingCell = getHeadingCell();
        Font headingFont = getHeaderFont();

//        headingCell.setPhrase(new Phrase("Tran#", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Date", headingFont));
//         headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Rx#", headingFont));
//         headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(headingCell);
//
//        ///////////////////////////////////////////////////////////////
//        headingCell.setPhrase(new Phrase("Drug", headingFont));
//         headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Qty", headingFont));
//         headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("ACQ", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//       table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Svc Fee", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Cost", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Ins", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Reimb", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Copay", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Rx Claim", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Pt R", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Share Cost", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("F P", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("IMV Fee", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Phmcy profit", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//
//        headingCell.setPhrase(new Phrase("Phmcy Remit", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
//        headingCell.setPhrase(new Phrase("Tran#", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Date", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

//        headingCell.setPhrase(new Phrase("Request#", headingFont));
//        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Type", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Rx#", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("GCN", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        ///////////////////////////////////////////////////////////////
        headingCell.setPhrase(new Phrase("Medication", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Qty", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Insured?", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("ACQ", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Ins.", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Svc.", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Svc Fee", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Pt R.", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Share Cost", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Copay", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("F P", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("SellingPrice ", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("IMV Fee", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Phmcy profit", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Phmcy Remit", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        //////////////////////////////////////////////////////////////
        Font dataFont = getDataFont();
        PdfPCell dataCell = getDataCell();

        for (OrderDetailDTO order : orderList) {
            //logger.info("NotificationMessages Id:: " + messages.getMessageId() + " Message text is:: " + messages.getMessageText());

            //HTMLWorker hTMLWorker = new HTMLWorker(document);
            //hTMLWorker.parse(new StringReader(html));
//            dataCell.setPhrase(new Phrase("" + order.getTransactionNumber(), dataFont));
//            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(order.getOrderDate(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(order.getId(), dataFont));
//            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(order.getRequestType(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

            ///////////////////////////////////////////////////////////////
            dataCell.setPhrase(new Phrase(order.getRxNumber(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(order.getGcn(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(order.getDrugName() + " " + order.getDrugType() + " " + order.getStrength(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(order.getDrugType(), dataFont));
//            table.addCell(dataCell);
//            
//            dataCell.setPhrase(new Phrase(order.getStrength(), dataFont));
//            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(order.getQty(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(order.getInsuranceType(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getAcqCost(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getReimbursementCost(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(order.getDeliveryPreferencesName(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getDeliveryFee(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.numberFormat(order.getSharePoints()), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getSharePointsCost(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getAcqCost() + order.getDeliveryFee(), "en", "US"), dataFont));
//            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(dataCell);
//            dataCell.setPhrase(new Phrase(order.getInsuranceType(), dataFont));
//            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.addCell(dataCell);
//            double copay = order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d;
//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(copay, "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getFinalPayment() != null ? order.getFinalPayment() : 0d, "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(copay + order.getReimbursementCost(), "en", "US"), dataFont));
//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getSellingPrice(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getImvFee(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getPharmacyProfit(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getRemitPharmacy(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

            //////////////////////////////////////////////////////////////
        }
        table.setHeaderRows(1);
        document.add(table);
        document.add(new Paragraph("BALANCE TO IMV : " + AppUtil.roundOffNumberToCurrencyFormat(netBalance, "en", "US")));
        document.close();
    }

    ////////////////////////////////////////////////////////////////////////
    public void generatePharmacyTransactionalReport(Document document, String frmDate, String toDate, String pharmacy, List<OrderDetailDTO> orderList, double netBalance, HttpServletRequest request) throws DocumentException, Exception {
        //document.setPageSize(PageSize.A4.rotate());

        PdfPTable tableHeader = new PdfPTable(3);
        tableHeader.setWidthPercentage(100.0f);
        tableHeader.setWidths(new float[]{2.5f, 4.5f, 3.5f});
        tableHeader.setSpacingBefore(10);

        PdfPCell headingCellHeader = getHeadingCellTop();
        PdfPCell cell1 = new PdfPCell(getImage(request));
        cell1.setBackgroundColor(BaseColor.WHITE);
        cell1.setBorderColor(BaseColor.WHITE);
        tableHeader.addCell(cell1);
        tableHeader.addCell(headingCellHeader);
        headingCellHeader.setPhrase(new Phrase("             Pharmacy Transaction Report\n\n             Pharmacy:  " + pharmacy + "\n" + "             From :        " + frmDate + "\n" + "             To:              " + toDate));
        tableHeader.addCell(headingCellHeader);
        document.add(tableHeader);

        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));

//        Font headingFontForAbreviation = getParaGraphColors();
//        PdfPTable tableAbrreviation = new PdfPTable(2);
//        tableAbrreviation.setWidthPercentage(100.0f);
//        tableAbrreviation.setWidths(new float[]{3.1f, 8.0f});
//        tableAbrreviation.setSpacingBefore(10);
//        
//        headingCellHeader.setPhrase(new Phrase("Abbreviation for following columns :"+"\n"));
//        tableAbrreviation.addCell(headingCellHeader);
//        headingCellHeader.setPhrase(new Phrase("Transaction#: Tran# , Acquisition Cost : ACQ , "+"Insurance : Ins , "+"Reimbursement Cost : Reimb , "+"Points Redeemed : Pt R , "+ "Final Payment : F P, " +"Pharmacy Profit : Phmcy Profit, " +" Pharmacy Remit : Phmcy Remit",headingFontForAbreviation));
//        tableAbrreviation.addCell(headingCellHeader);
//        document.add(tableAbrreviation);
        PdfPTable table = new PdfPTable(11);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{4.9f, 5.8f, 4.5f, 9.3f, 3.5f, 4.5f, 4.3f, 6.2f, 5.5f,
            6.0f, 5.5f});
        table.setSpacingBefore(10);

        PdfPCell headingCell = getHeadingCell();
        Font headingFont = getHeaderFont();

        headingCell.setPhrase(new Phrase("Tran#", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Date", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Rx#", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        ///////////////////////////////////////////////////////////////
        headingCell.setPhrase(new Phrase("Drug", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

//        headingCell.setPhrase(new Phrase("TYPE", headingFont));
//        table.addCell(headingCell);
//        
//        headingCell.setPhrase(new Phrase("STRENGTH", headingFont));
//        table.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Qty", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

//        headingCell.setPhrase(new Phrase("ACQ", headingFont));
//        table.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Svc Fee", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Cost", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Ins", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Reimb", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Copay", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Final Payment", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        //////////////////////////////////////////////////////////////
        Font dataFont = getDataFont();
        PdfPCell dataCell = getDataCell();

        for (OrderDetailDTO order : orderList) {
            //logger.info("NotificationMessages Id:: " + messages.getMessageId() + " Message text is:: " + messages.getMessageText());

            //HTMLWorker hTMLWorker = new HTMLWorker(document);
            //hTMLWorker.parse(new StringReader(html));
//            dataCell.setPhrase(new Phrase("" + order.getTransactionNumber(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(order.getOrderDate(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

            ///////////////////////////////////////////////////////////////
            dataCell.setPhrase(new Phrase(order.getRxNumber(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(order.getDrugName() + " " + order.getDrugType() + " " + order.getStrength(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(order.getQty(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getDeliveryFee(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getAcqCost() + order.getDeliveryFee(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(order.getInsuranceType(), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getReimbursementCost(), "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            double copay = order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d;
//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(copay, "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

//            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(copay + order.getReimbursementCost(), "en", "US"), dataFont));
//            table.addCell(dataCell);
            dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(order.getFinalPayment() != null ? order.getFinalPayment() : 0d, "en", "US"), dataFont));
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dataCell);

            //////////////////////////////////////////////////////////////
        }
        table.setHeaderRows(1);
        document.add(table);
        document.add(new Chunk(ls));
//        document.add(new Paragraph("Confidentiality NOTICE --© 2018. Prepared for internal use only. This document contains information proprietary and confidential to  Ingenious Mobility and is not to be disclosed in whole or in part without the express written consent of  IMV."));
        document.close();
    }

    ////////////////////////////////////////////////////////////////////////
    private void addFooterToDocument(Document document) {
        try {
            Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
            ByteArrayOutputStream baos = createTemporaryOutputStream();

            // Apply preferences and build metadata.
            PdfWriter writer = newWriter(document, baos);
            Phrase footer = new Phrase("this is a footer", ffont);
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 10, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////
    private void downloadInActivePatientReports(List<PatientProfileDTO> inActivePatientList, Document document, Font headingFont, PdfPCell headingCell, Font dataFont, PdfPCell dataCell, PdfPCell evenDataCell, Image image) throws DocumentException, Exception {
        document.add(image);
        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));
        document.add(new Paragraph("Enrolled Patient No Order Reports"));
        document.addTitle("Enrolled Patient No Order Reports");
        document.add(new Paragraph("Date : " + DateUtil.dateToString(new Date(), Constants.USA_DATE_FORMATE)));

        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.5f, 2.0f, 2.0f, 2.0f, 3.5f, 3.5f, 2.0f, 2.5f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        headingCell.setPhrase(new Phrase("Patient Name", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("DOB", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Gender", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Mobile", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Email", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Reg. Date", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("INS", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Payment Card", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Allergies", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Zip", headingFont));
        table.addCell(headingCell);
        int number = 0;
        for (PatientProfileDTO ppdto : inActivePatientList) {
            int quotient = number / 2;
            if (quotient % 2 == number) {
                dataCell.setPhrase(new Phrase(ppdto.getPatientName(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getPatientDOB(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getGender(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(CommonUtil.subStringPhone(ppdto.getMobileNumber(), "-"), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getEmailAddress(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getRegistrationDate(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getHasRxCard(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getHasPaymentCard(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getAllergies(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(ppdto.getZip(), dataFont));
                table.addCell(dataCell);
                number++;
            } else {
                evenDataCell.setPhrase(new Phrase(ppdto.getPatientName(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getPatientDOB(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getGender(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(CommonUtil.subStringPhone(ppdto.getMobileNumber(), "-"), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getEmailAddress(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getRegistrationDate(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getHasRxCard(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getHasPaymentCard(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getAllergies(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(ppdto.getZip(), dataFont));
                table.addCell(evenDataCell);
                number++;
            }
        }
        document.add(table);
    }

    private void generateBasicStatisticsReports(BasicStatisticsReportDTO basicStatisticsReportDTO, Document document, Font headingFont, PdfPCell headingCell, Font dataFont, PdfPCell dataCell, PdfPCell evenDataCell, Image image) throws DocumentException, Exception {
        Paragraph paragraph = new Paragraph();
        document.add(image);
        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));
        document.add(new Paragraph("Basic Statistics Reports"));
        document.addTitle("Basic Statistics Reports");
        document.add(new Paragraph("Date : " + DateUtil.dateToString(new Date(), Constants.USA_DATE_FORMATE)));
        PdfPTable mainTable = new PdfPTable(4);
        mainTable.setWidthPercentage(100.0f);
        mainTable.getDefaultCell().setBorder(0);

        //1).Patient Type
        generatePatientTypeTable(headingCell, headingFont, dataCell, dataFont, basicStatisticsReportDTO, mainTable);
        //2).Gender table
        generateGenderTable(headingFont, dataFont, basicStatisticsReportDTO, mainTable);
        //3).Rx Mix
        generateRxMixTable(headingFont, dataFont, basicStatisticsReportDTO, mainTable);
        //4).Insurance PdfTable
        generateInsurancePdfTable(headingFont, dataFont, basicStatisticsReportDTO, mainTable);
        paragraph.add(mainTable);
        mainTable = new PdfPTable(3);
        mainTable.setWidthPercentage(100.0f);
        mainTable.getDefaultCell().setBorder(0);
        //5).Patient Out of Pocket
        generatePatientOutOfPocket(headingFont, dataFont, basicStatisticsReportDTO, mainTable);
        //6).age by Rx recipient table
        generateAgeByRxRecipient(headingFont, dataFont, basicStatisticsReportDTO, mainTable);
        //7).Rx Fulfillment
        generateRxFulfillmentTable(headingFont, dataFont, basicStatisticsReportDTO, mainTable);

        paragraph.add(mainTable);
        document.add(paragraph);
    }

    private void generatePatientTypeTable(PdfPCell headingCell, Font headingFont, PdfPCell dataCell, Font dataFont, BasicStatisticsReportDTO basicStatisticsReportDTO, PdfPTable mainTable) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{5.0f, 3.0f, 2.6f});
        table.setSpacingBefore(10);

        headingCell.setPhrase(new Phrase("Patient Type", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Count", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("%", headingFont));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headingCell);

        dataCell.setPhrase(new Phrase("Account Holder", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getAccountHolderNumber()) ? basicStatisticsReportDTO.getAccountHolderNumber().toString() : "0"), dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getAccountHolderCount()) ? basicStatisticsReportDTO.getAccountHolderCount() : "0", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase("Child Dependant", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getChildDependentNumber()) ? basicStatisticsReportDTO.getChildDependentNumber().toString() : "0"), dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getChildDependantCount()) ? basicStatisticsReportDTO.getChildDependantCount() : "0", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase("Adult Dependant", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getAdultDependentNumber()) ? basicStatisticsReportDTO.getAdultDependentNumber().toString() : "0"), dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getAdultDependantCount()) ? basicStatisticsReportDTO.getAdultDependantCount() : "0", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase("Total", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dataCell.setRowspan(2);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase(!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalPatientTypeNumber()) ? basicStatisticsReportDTO.getTotalPatientTypeNumber().toString() : "0", dataFont));
        dataCell.setRowspan(2);
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dataCell);
        dataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalPatientTypeCount()) ? basicStatisticsReportDTO.getTotalPatientTypeCount() : "0", dataFont));
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        dataCell.setRowspan(2);
        table.addCell(dataCell);

        mainTable.addCell(table);
    }

    private void generateGenderTable(Font headingFont, Font dataFont, BasicStatisticsReportDTO basicStatisticsReportDTO, PdfPTable mainTable) throws DocumentException {
        PdfPTable table2 = new PdfPTable(3);
        table2.setWidthPercentage(100.0f);
        table2.setWidths(new float[]{2.5f, 2.0f, 2.0f});
        table2.setSpacingBefore(10);
        PdfPCell headingCell1 = getHeadingCell();
        headingCell1.setPhrase(new Phrase("Gender", headingFont));
        table2.addCell(headingCell1);
        headingCell1.setPhrase(new Phrase("Count", headingFont));
        headingCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(headingCell1);
        headingCell1.setPhrase(new Phrase("%", headingFont));
        headingCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(headingCell1);
        PdfPCell dataCell_1 = getDataCell();
        dataCell_1.setPhrase(new Phrase("Male", dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalMaleGenderNumber()) ? basicStatisticsReportDTO.getTotalMaleGenderNumber().toString() : "0"), dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalMaleGender()) ? basicStatisticsReportDTO.getTotalMaleGender() : "0", dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase("Female", dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalFemaleGenderNumber()) ? basicStatisticsReportDTO.getTotalFemaleGenderNumber().toString() : "0"), dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalFemaleGender()) ? basicStatisticsReportDTO.getTotalFemaleGender() : "0", dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase("Total", dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_LEFT);
        dataCell_1.setRowspan(3);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalGenderNumber()) ? basicStatisticsReportDTO.getTotalGenderNumber().toString() : "0"), dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        dataCell_1.setRowspan(3);
        table2.addCell(dataCell_1);
        dataCell_1.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalGenderCount()) ? basicStatisticsReportDTO.getTotalGenderCount() : "0", dataFont));
        dataCell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        dataCell_1.setRowspan(3);
        table2.addCell(dataCell_1);

        mainTable.addCell(table2);
    }

    private void generateAgeByRxRecipient(Font headingFont, Font dataFont, BasicStatisticsReportDTO basicStatisticsReportDTO, PdfPTable mainTable) throws DocumentException {
        PdfPTable table3 = new PdfPTable(3);
        table3.setWidthPercentage(100.0f);
        table3.setWidths(new float[]{6.0f, 2.1f, 2.1f});
        table3.setSpacingBefore(10);
        PdfPCell headingCell_2 = getHeadingCell();
        headingCell_2.setPhrase(new Phrase("Age By Rx Recipient", headingFont));
        table3.addCell(headingCell_2);

        headingCell_2.setPhrase(new Phrase("Count", headingFont));
        headingCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(headingCell_2);

        headingCell_2.setPhrase(new Phrase("%", headingFont));
        headingCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(headingCell_2);

        PdfPCell dataCell_2 = getDataCell();
        dataCell_2.setPhrase(new Phrase("0-17 years", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge17Count()) ? basicStatisticsReportDTO.getUnderAge17Count().toString() : "0"), dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge0To17Count()) ? basicStatisticsReportDTO.getUnderAge0To17Count() : "0", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase("18 - 34 years", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge18Count()) ? basicStatisticsReportDTO.getUnderAge18Count().toString() : "0"), dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge18To34Count()) ? basicStatisticsReportDTO.getUnderAge18To34Count() : "0", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);

        dataCell_2.setPhrase(new Phrase("35 - 50 years", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge35Count()) ? basicStatisticsReportDTO.getUnderAge35Count().toString() : "0"), dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge35To50Count()) ? basicStatisticsReportDTO.getUnderAge35To50Count() : "0", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);

        dataCell_2.setPhrase(new Phrase("51 - 64 years", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge51Count()) ? basicStatisticsReportDTO.getUnderAge51Count().toString() : "0"), dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getUnderAge51To64Count()) ? basicStatisticsReportDTO.getUnderAge51To64Count() : "0", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);

        dataCell_2.setPhrase(new Phrase("65+", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getUnderAge65Count()) ? basicStatisticsReportDTO.getUnderAge65Count().toString() : "0"), dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getAge65MoreCount()) ? basicStatisticsReportDTO.getAge65MoreCount() : "0", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);

        dataCell_2.setPhrase(new Phrase("Total", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalAgeByRxRecipientNumber()) ? basicStatisticsReportDTO.getTotalAgeByRxRecipientNumber().toString() : "0"), dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);
        dataCell_2.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalAgeByRxRecipientCount()) ? basicStatisticsReportDTO.getTotalAgeByRxRecipientCount() : "0", dataFont));
        dataCell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.addCell(dataCell_2);

        mainTable.addCell(table3);
    }

    private void generatePatientOutOfPocket(Font headingFont, Font dataFont, BasicStatisticsReportDTO basicStatisticsReportDTO, PdfPTable mainTable) throws DocumentException {
        //Patient Out of Pocket
        PdfPTable ptOutOfPocketTable = new PdfPTable(3);
        ptOutOfPocketTable.setWidthPercentage(100.0f);
        ptOutOfPocketTable.setWidths(new float[]{6.0f, 2.2f, 2.2f});
        ptOutOfPocketTable.setSpacingBefore(10);
        PdfPCell ptOutOfPocketCell = getHeadingCell();
        ptOutOfPocketCell.setPhrase(new Phrase("Patient Out of Pocket", headingFont));
        ptOutOfPocketTable.addCell(ptOutOfPocketCell);

        ptOutOfPocketCell.setPhrase(new Phrase("Count", headingFont));
        ptOutOfPocketCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketCell);

        ptOutOfPocketCell.setPhrase(new Phrase("%", headingFont));
        ptOutOfPocketCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketCell);

        PdfPCell ptOutOfPocketDataCell = getDataCell();
        ptOutOfPocketDataCell.setPhrase(new Phrase("Zero", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocketNumber()) ? basicStatisticsReportDTO.getPtOutofPocketNumber().toString() : "0"), dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocketCount()) ? basicStatisticsReportDTO.getPtOutofPocketCount() : "0", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);

        ptOutOfPocketDataCell.setPhrase(new Phrase("$0.01 -$25", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocket1To25Number()) ? basicStatisticsReportDTO.getPtOutofPocket1To25Number().toString() : "0"), dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocket1To25Count()) ? basicStatisticsReportDTO.getPtOutofPocket1To25Count() : "0", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);

        ptOutOfPocketDataCell.setPhrase(new Phrase("$26 - $75", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocket26To75Number()) ? basicStatisticsReportDTO.getPtOutofPocket26To75Number().toString() : "0"), dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocket26To75Count()) ? basicStatisticsReportDTO.getPtOutofPocket26To75Count() : "0", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);

        ptOutOfPocketDataCell.setPhrase(new Phrase("$76 or more", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPtOutofPocket76GreaterNumber()) ? basicStatisticsReportDTO.getPtOutofPocket76GreaterNumber().toString() : "0"), dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPtOutofPocket76GreaterCount()) ? basicStatisticsReportDTO.getPtOutofPocket76GreaterCount() : "0", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);

        ptOutOfPocketDataCell.setPhrase(new Phrase("Total", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        ptOutOfPocketDataCell.setRowspan(2);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalPtOutofPocketNumber()) ? basicStatisticsReportDTO.getTotalPtOutofPocketNumber().toString() : "0"), dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketDataCell.setRowspan(2);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);
        ptOutOfPocketDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalPtOutOfPocketCount()) ? basicStatisticsReportDTO.getTotalPtOutOfPocketCount() : "0", dataFont));
        ptOutOfPocketDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        ptOutOfPocketDataCell.setRowspan(2);
        ptOutOfPocketTable.addCell(ptOutOfPocketDataCell);

        mainTable.addCell(ptOutOfPocketTable);
    }

    private void generateRxMixTable(Font headingFont, Font dataFont, BasicStatisticsReportDTO basicStatisticsReportDTO, PdfPTable mainTable) throws DocumentException {
        //Rx Mix
        PdfPTable rxMixTable = new PdfPTable(3);
        rxMixTable.setWidthPercentage(100.0f);
        rxMixTable.setWidths(new float[]{2.5f, 2.0f, 2.0f});
        rxMixTable.setSpacingBefore(10);
        PdfPCell rxMixCell = getHeadingCell();
        rxMixCell.setPhrase(new Phrase("Rx Mix", headingFont));
        rxMixTable.addCell(rxMixCell);

        rxMixCell.setPhrase(new Phrase("Count", headingFont));
        rxMixCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixTable.addCell(rxMixCell);

        rxMixCell.setPhrase(new Phrase("%", headingFont));
        rxMixCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixTable.addCell(rxMixCell);

        PdfPCell rxMixDataCell = getDataCell();
        rxMixDataCell.setPhrase(new Phrase("Brand", dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxMixTable.addCell(rxMixDataCell);
        rxMixDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getBrandNumber()) ? basicStatisticsReportDTO.getBrandNumber().toString() : "0"), dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixTable.addCell(rxMixDataCell);
        rxMixDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getBrandCount()) ? basicStatisticsReportDTO.getBrandCount() : "0", dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixTable.addCell(rxMixDataCell);

        rxMixDataCell.setPhrase(new Phrase("Generic", dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxMixTable.addCell(rxMixDataCell);
        rxMixDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getGenericNumber()) ? basicStatisticsReportDTO.getGenericNumber().toString() : "0"), dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixTable.addCell(rxMixDataCell);
        rxMixDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getGenericCount()) ? basicStatisticsReportDTO.getGenericCount() : "0", dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixTable.addCell(rxMixDataCell);

        rxMixDataCell.setPhrase(new Phrase("Total", dataFont));
        rxMixDataCell.setRowspan(3);
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxMixTable.addCell(rxMixDataCell);
        rxMixDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalRxMixNumber()) ? basicStatisticsReportDTO.getTotalRxMixNumber().toString() : "0"), dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixDataCell.setRowspan(3);
        rxMixTable.addCell(rxMixDataCell);
        rxMixDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalRxMixCount()) ? basicStatisticsReportDTO.getTotalRxMixCount() : "0", dataFont));
        rxMixDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxMixDataCell.setRowspan(3);
        rxMixTable.addCell(rxMixDataCell);

        mainTable.addCell(rxMixTable);
    }

    private void generateRxFulfillmentTable(Font headingFont, Font dataFont, BasicStatisticsReportDTO basicStatisticsReportDTO, PdfPTable mainTable) throws DocumentException {
        //Rx Fulfillment
        PdfPTable rxFulfillmentTable = new PdfPTable(3);
        rxFulfillmentTable.setWidthPercentage(100.0f);
        rxFulfillmentTable.setWidths(new float[]{5.0f, 2.0f, 2.0f});
        rxFulfillmentTable.setSpacingBefore(10);

        PdfPCell rxFulfillmentCell = getHeadingCell();

        rxFulfillmentCell.setPhrase(new Phrase("Rx Fulfillment", headingFont));
        rxFulfillmentTable.addCell(rxFulfillmentCell);

        rxFulfillmentCell.setPhrase(new Phrase("Count", headingFont));
        rxFulfillmentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentCell);

        rxFulfillmentCell.setPhrase(new Phrase("%", headingFont));
        rxFulfillmentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentCell);

        PdfPCell rxFulfillmentDataCell = getDataCell();
        rxFulfillmentDataCell.setPhrase(new Phrase("2nd Day", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getDay2ndNumber()) ? basicStatisticsReportDTO.getDay2ndNumber().toString() : "0"), dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getDay2ndCount()) ? basicStatisticsReportDTO.getDay2ndCount() : "0", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);

        rxFulfillmentDataCell.setPhrase(new Phrase("Prem-Next Day", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getNextDayNumber()) ? basicStatisticsReportDTO.getNextDayNumber().toString() : "0"), dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getNextDayCount()) ? basicStatisticsReportDTO.getNextDayCount() : "0", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);

        rxFulfillmentDataCell.setPhrase(new Phrase("Premium-Same Day", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getSameDayNumber()) ? basicStatisticsReportDTO.getSameDayNumber().toString() : "0"), dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getSameDayCount()) ? basicStatisticsReportDTO.getSameDayCount() : "0", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);

        rxFulfillmentDataCell.setPhrase(new Phrase("Pick Up", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPickUpNumber()) ? basicStatisticsReportDTO.getPickUpNumber().toString() : "0"), dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPickUpCount()) ? basicStatisticsReportDTO.getPickUpCount() : "0", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);

        rxFulfillmentDataCell.setPhrase(new Phrase("Total", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        rxFulfillmentDataCell.setRowspan(2);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalRxFulfillmentNumber()) ? basicStatisticsReportDTO.getTotalRxFulfillmentNumber().toString() : "0"), dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentDataCell.setRowspan(2);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);
        rxFulfillmentDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalRxFullfillmentCount()) ? basicStatisticsReportDTO.getTotalRxFullfillmentCount() : "0", dataFont));
        rxFulfillmentDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rxFulfillmentDataCell.setRowspan(2);
        rxFulfillmentTable.addCell(rxFulfillmentDataCell);

        mainTable.addCell(rxFulfillmentTable);
    }

    private void generateInsurancePdfTable(Font headingFont, Font dataFont, BasicStatisticsReportDTO basicStatisticsReportDTO, PdfPTable mainTable) throws DocumentException {
        PdfPTable insTable = new PdfPTable(3);
        insTable.setWidthPercentage(100.0f);
        insTable.setWidths(new float[]{3.0f, 2.0f, 1.5f});
        insTable.setSpacingBefore(10);
        PdfPCell insHeadingCell = getHeadingCell();
        insHeadingCell.setPhrase(new Phrase("Insurance", headingFont));
        insTable.addCell(insHeadingCell);

        insHeadingCell.setPhrase(new Phrase("Count", headingFont));
        insHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insHeadingCell);

        insHeadingCell.setPhrase(new Phrase("%", headingFont));
        insHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insHeadingCell);

        PdfPCell insDataCell = getDataCell();
        insDataCell.setPhrase(new Phrase("Commercial", dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getCommercialInusranceNumber()) ? basicStatisticsReportDTO.getCommercialInusranceNumber().toString() : "0"), dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getCommercialInsuranceCount()) ? basicStatisticsReportDTO.getCommercialInsuranceCount() : "0", dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insDataCell);

        insDataCell.setPhrase(new Phrase("Public", dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getPublicMedicareNumber()) ? basicStatisticsReportDTO.getPublicMedicareNumber().toString() : "0"), dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getPublicMedicareCount()) ? basicStatisticsReportDTO.getPublicMedicareCount() : "0", dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insDataCell);

        insDataCell.setPhrase(new Phrase("Self pay", dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getSelfPayNumber()) ? basicStatisticsReportDTO.getSelfPayNumber().toString() : "0"), dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getSelfPayCount()) ? basicStatisticsReportDTO.getSelfPayCount() : "0", dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insDataCell);

        insDataCell.setPhrase(new Phrase("Total", dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        insDataCell.setRowspan(2);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase((!CommonUtil.isNullOrEmpty(basicStatisticsReportDTO.getTotalInsuranceNumber()) ? basicStatisticsReportDTO.getTotalInsuranceNumber().toString() : "0"), dataFont));
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insDataCell.setRowspan(2);
        insTable.addCell(insDataCell);
        insDataCell.setPhrase(new Phrase(CommonUtil.isNotEmpty(basicStatisticsReportDTO.getTotalInsuranceCount()) ? basicStatisticsReportDTO.getTotalInsuranceCount() : "0", dataFont));
        insDataCell.setRowspan(2);
        insDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        insTable.addCell(insDataCell);

        mainTable.addCell(insTable);
    }

    private void generatePatientBasicReports(List<OrderDetailDTO> listofPatientBasicDTO, Document document, Font headingFont, PdfPCell headingCell, Font dataFont, PdfPCell dataCell, PdfPCell evenDataCell, Image image) throws DocumentException, Exception {
        document.add(image);
        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));
        document.add(new Paragraph("Patient Basic Reports"));
        document.addTitle("Patient Basic Reports");
        document.add(new Paragraph("Date : " + DateUtil.dateToString(new Date(), Constants.USA_DATE_FORMATE)));

        PdfPTable table = new PdfPTable(17);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{5.8f, 5.8f, 5.8f, 5.8f, 5.5f, 5.5f, 8f, 5f, 5f,
            5.8f, 5.8f, 5.8f, 5.8f, 5.5f, 6.1f, 6.1f, 5.5f});
        table.setSpacingBefore(10);

        headingCell.setPhrase(new Phrase("Patient Name", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Registered Status", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Date of Service", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Phone Number", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("RX Number", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Refill Sequence", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Medication", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Qty", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Days Supply", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Insurance Applied", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Member Out Of Pocket", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Sales Tax Collected", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Delivery Method", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Delivery Fee", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Total Charged To Payment Card", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Address Delivered Alias", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Patient Signature", headingFont));
        table.addCell(headingCell);

        int number = 0;
        for (OrderDetailDTO oddto : listofPatientBasicDTO) {
            Image patientSignature = null;
//            if (CommonUtil.isNotEmpty(oddto.getPatientSignature())) {
//                patientSignature = Image.getInstance(oddto.getPatientSignature());
//                patientSignature.scaleAbsolute(30f, 30f);
//            }
            int quotient = number / 2;
            if (quotient % 2 == number) {
                dataCell.setPhrase(new Phrase(oddto.getPatientName(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getRegisteredStatus(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getOrderDate(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getMobileNumber(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getRxNumber(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getRefill() + "", dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getDrugName() + " " + oddto.getDrugType(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getQty(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getDaysSupply() + "", dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getPaymentType(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getFinalPaymentStr(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getSalesTaxStr(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getDeliveryPreferencesName(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getHandlingFeeStr(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getTotalPaymentStr(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getShippingAddress(), dataFont));
                table.addCell(dataCell);
                if (patientSignature != null) {
                    dataCell.setImage(patientSignature);
                    table.addCell(dataCell);
                } else {
//                    dataCell.setPhrase(new Phrase(oddto.getPatientSignature(), dataFont));
                    table.addCell(dataCell);
                }

                number++;
            } else {
                evenDataCell.setPhrase(new Phrase(oddto.getPatientName(), dataFont));
//                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getRegisteredStatus(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getOrderDate(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getMobileNumber(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getRxNumber(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getRefill() + "", dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getDrugName() + " " + oddto.getDrugType(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getQty(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getDaysSupply() + "", dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getPaymentType(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getFinalPaymentStr(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getSalesTaxStr(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getDeliveryPreferencesName(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getHandlingFeeStr(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getTotalPaymentStr(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getShippingAddress(), dataFont));
                table.addCell(evenDataCell);
                if (patientSignature != null) {
                    evenDataCell.setImage(patientSignature);
                    table.addCell(evenDataCell);
                } else {
//                    evenDataCell.setPhrase(new Phrase(oddto.getPatientSignature(), dataFont));
                    table.addCell(evenDataCell);
                }
                number++;
            }
        }
        document.add(table);
    }

    private void generatePatientEnhancedReports(List<OrderDetailDTO> listofPatientBasicDTO, Document document, Font headingFont, PdfPCell headingCell, Font dataFont, PdfPCell dataCell, PdfPCell evenDataCell, Image image) throws DocumentException, Exception {
        document.add(image);
        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));
        document.add(new Paragraph("Patient Enhanced Reports"));
        document.addTitle("Patient Enhanced Reports");
        document.add(new Paragraph("Date : " + DateUtil.dateToString(new Date(), Constants.USA_DATE_FORMATE)));

        PdfPTable table = new PdfPTable(24);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f,
            4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f, 4.1f});
        table.setSpacingBefore(10);

        headingCell.setPhrase(new Phrase("Patient Name", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Registered Status", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Date of Service", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Request Control Number", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Phone Number", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("RX Number", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Refill Sequence", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Medication", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Qty", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("SELF PAY Price Estimate", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Days Supply", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Insurance Applied", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Member Out Of Pocket", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Sales Tax Collected", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("3rd Party Pay", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Compliance Rewards™ Earned", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Earned Savings", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Rx Expires", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Delivery Method", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Delivery Fee", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Address Delivered", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Total Charged To Payment Card", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Patient Signature", headingFont));
        table.addCell(headingCell);

        headingCell.setPhrase(new Phrase("Pharmacy Filled By Initials", headingFont));
        table.addCell(headingCell);

        int number = 0;
        for (OrderDetailDTO oddto : listofPatientBasicDTO) {
            Image patientSignature = null;
//            if (CommonUtil.isNotEmpty(oddto.getPatientSignature())) {
//                patientSignature = Image.getInstance(oddto.getPatientSignature());
//                patientSignature.scaleAbsolute(30f, 30f);
//            }
            int quotient = number / 2;
            if (quotient % 2 == number) {
                dataCell.setPhrase(new Phrase(oddto.getPatientName(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getRegisteredStatus(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getOrderDate(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getId(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getMobileNumber(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getRxNumber(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getRefill() + "", dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getDrugName() + " " + oddto.getDrugType(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getQty(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(oddto.getDrugPrice(), "en", "Us"), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getDaysSupply() + "", dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getPaymentType(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getFinalPaymentStr(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getSalesTaxStr(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(oddto.getRxReimbCost(), "en", "Us"), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getRedeemPoints(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(oddto.getRedeemPointsCost(), "en", "Us"), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getRxExpiredDateStr(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getDeliveryPreferencesName(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getHandlingFeeStr(), dataFont));
                table.addCell(dataCell);
//                dataCell.setPhrase(new Phrase(oddto.getShippingAddress(), dataFont));
                table.addCell(dataCell);
                dataCell.setPhrase(new Phrase(oddto.getTotalPaymentStr(), dataFont));
                table.addCell(dataCell);
                if (patientSignature != null) {
                    dataCell.setImage(patientSignature);
                    table.addCell(dataCell);
                } else {
//                    dataCell.setPhrase(new Phrase(oddto.getPatientSignature(), dataFont));
                    table.addCell(dataCell);
                }
//                dataCell.setPhrase(new Phrase(oddto.getPharmacyName(), dataFont));
                table.addCell(dataCell);
                number++;
            } else {
                evenDataCell.setPhrase(new Phrase(oddto.getPatientName(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getRegisteredStatus(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getOrderDate(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getId(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getMobileNumber(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getRxNumber(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getRefill() + "", dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getDrugName() + " " + oddto.getDrugType(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getQty(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(oddto.getDrugPrice(), "en", "Us"), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getDaysSupply() + "", dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getPaymentType(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getFinalPaymentStr(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getSalesTaxStr(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(oddto.getRxReimbCost(), "en", "Us"), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getRedeemPoints(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(AppUtil.roundOffNumberToCurrencyFormat(oddto.getRedeemPointsCost(), "en", "Us"), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getRxExpiredDateStr(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getDeliveryPreferencesName(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getHandlingFeeStr(), dataFont));
                table.addCell(evenDataCell);
//                evenDataCell.setPhrase(new Phrase(oddto.getShippingAddress(), dataFont));
                table.addCell(evenDataCell);
                evenDataCell.setPhrase(new Phrase(oddto.getTotalPaymentStr(), dataFont));
                table.addCell(evenDataCell);
                if (patientSignature != null) {
                    evenDataCell.setImage(patientSignature);
                    table.addCell(evenDataCell);
                } else {
//                    evenDataCell.setPhrase(new Phrase(oddto.getPatientSignature(), dataFont));
                    table.addCell(evenDataCell);
                }
//                evenDataCell.setPhrase(new Phrase(oddto.getPharmacyName(), dataFont));
                table.addCell(evenDataCell);
                number++;
            }
        }
        document.add(table);

    }
}
