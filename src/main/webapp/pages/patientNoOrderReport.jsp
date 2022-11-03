<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Reports <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Enrolled Patient No Order Report
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Enrolled Patient No Order Report</h1>
                </div> <!-- /header -->
                <div class="page-message messageheading" style="padding-top: 0px;">
                    <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                    <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                    </div>

                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">
                        <div class="pull-right btndrop" style="color: white;margin-bottom: 5px;margin-left: 5px;" onclick="exportReportData('excelView')">Export Excel</div>
                        <div class="pull-right btndrop" style="color: white" onclick="exportReportData('pdfView')">Export Pdf</div>
                        <br><br><br>
                        <div class="tab-content" style="height: auto;border-top: 0px;margin-bottom:55px;padding-top: 4px;">  
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                        <div role="tabpanel" class="tab-pane active">                                   
                            <div  class="table-responsive">
                                <table id="patientTb" class="display table" border="1">
                                    <thead>
                                        <tr >
                                            <th class="col-sm-3" style="min-width: 140px;">
                                                Patient Name
                                            </th>
                                            <th  class="col-sm-3" style="min-width: 115px;">
                                                DOB
                                            </th>
                                            <th class="col-sm-1">
                                                Gender
                                            </th>
                                            <th class="col-sm-2" style="min-width: 120px;">
                                                Mobile
                                            </th>
                                            <th class="col-sm-2">
                                                Email
                                            </th>
                                            <th class="col-sm-3" style="min-width: 165px;">
                                                Reg.&nbsp;Date
                                            </th>
                                            <th class="col-sm-1">
                                                INS
                                            </th>


                                            <th class="col-sm-3 " width="115px !important;">
                                                Payment Card
                                            </th>

                                            <th class="col-sm-1">
                                                Allergies
                                            </th>

                                            <th class="col-sm-3">
                                                Zip
                                            </th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${patientlist}">
                                            <tr  >
                                                <td >

                                                    <strong>   ${list.patientName} </strong>
                                                </td>
                                                <td >
                                                    <span id="tbl_textalign">${list.patientDOB}</span>
                                                </td>
                                                <td >
                                                    <span id="tbl_textalign">${list.gender}</span>
                                                </td>
                                                <td>
                                                    <span id="tbl_textalign">${fn:substring(list.mobileNumber, 0, 3)}-${fn:substring(list.mobileNumber, 3, 6)}-${fn:substring(list.mobileNumber, 6, 10)}</span>
                                                </td>
                                                <td >
                                                    <span id="tbl_textalign" >
                                                        <a href="mailto:${list.email}" style="color:blue">
                                                            ${list.email}</a></span>
                                                </td>
                                                <td >
                                                    <span id="tbl_textalign">${list.registrationDate}</span>
                                                </td>
                                                <td >
                                                    <span id="tbl_textalign">${list.hasRxCard}</span>
                                                </td>
                                                <td >
                                                    <span id="tbl_textalign">${list.hasPaymentCard}</span>
                                                </td>
                                                <td >
                                                    <span id="tbl_textalign">${list.allergies}</span>
                                                </td>
                                                <td>
                                                    <span id="tbl_textalign">${list.zip}</span>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                </div>
                <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInfo.js"></script>
        <script type="text/javascript">
                            $(document).ready(function () {
                                //alert("hi");
                                window.patientInfo.PatientTable();
                                window.patientInfo.populateAppUrl('${pageContext.request.contextPath}');

                            });
                            function exportReportData(type) {
                                var table = $('#patientTb tbody tr').children().length - 1;

                                if (table !== 0) {
                                    window.open('${pageContext.request.contextPath}/campaign/exportInActivePatientReports?type=' + type, "_blank");
                                }
                            }

        </script>
    </body>
</html>
