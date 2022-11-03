<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home  <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Patient Basic Report
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Patient Basic Report</h1>
                </div>
                <form:form action="${pageContext.request.contextPath}/patient/patientBasicReport" commandName="baseDTO" role="form" method="Post">
                    <div class="col-sm-12 surveyreports-ipad" style="padding-top: 15px;">
                        <div class="form-group margin-ive">
                            <div class="col-lg-2 col-xs-12 drp-ipad" style="padding-left: 0px;">
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon2">Pt Name:<span style="color:red">*</span></span>
                                    <form:input id="txtPtName" path="patientName" cssClass="form-control" aria-describedby="basic-addon2" placeholder="Enter Pt Name" onkeyup="enableSearchBtn(this.value)" onchange="enableSearchBtn(this.value)"/>
                                </div>
                                <span id="txtPtNameReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-2 col-xs-12 drp-ipad" style="padding-left: 0px;">
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon2">Email:<span style="color:red">*</span></span>
                                    <form:input id="txtEmail" path="email" cssClass="form-control" aria-describedby="basic-addon2" placeholder="Enter Email" onkeyup="enableSearchBtn(this.value)" onchange="enableSearchBtn(this.value)"/>
                                </div>
                                <span id="emailReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-2 col-xs-12 drp-ipad" style="padding-left: 0px;">
                                <div class="input-group">
                                    <fmt:formatDate var="fromDate" pattern="MM/dd/yyyy" value="${baseDTO.fromDate}"/>
                                    <span class="input-group-addon" id="basic-addon2">From:<span style="color:red">*</span></span>
                                    <form:input id="datetimepicker" path="fromDate" cssClass="form-control" value="${fromDate}" aria-describedby="basic-addon2" placeholder="MM/dd/yyyy" onkeyup="enableSearchBtn(this.value)" onchange="enableSearchBtn(this.value)"/>
                                </div>
                                <span id="fromDateReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-2 col-xs-12 drp-ipad">
                                <div class="input-group">
                                    <fmt:formatDate var="toDate" pattern="MM/dd/yyyy" value="${baseDTO.toDate}"/>
                                    <span class="input-group-addon" id="basic-addon3">To:<span style="color:red">*</span></span>
                                    <form:input id="datetimepicker1" path="toDate" cssClass="form-control" value="${toDate}" aria-describedby="basic-addon3" placeholder="MM/dd/yyyy" onkeyup="enableSearchBtn(this.value)" onchange="enableSearchBtn(this.value)"/>
                                </div>
                                <span id="toDateReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-2 col-xs-12 drp-ipad">
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon4">Phone #:<span style="color:red">*</span></span>
                                    <form:input id="phoneNumber" path="phoneNumber" cssClass="form-control" aria-describedby="basic-addon4" placeholder="Enter Phone Number" maxlength="10" onkeyup="enableSearchBtn(this.value)" onchange="enableSearchBtn(this.value)"/>
                                </div>
                                <span id="phoneNumberReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-2 col-md-12" style="margin-top: -9px;">
                                <div class="btn-group btn-dropright-ipad">
                                    <button id="btnSearch" class="btndrop" style="color: white" ${btnSearchDisable}>Generate Report</button>
                                </div>
                                <div class="btn-group btn-drop-ipad" style="padding-top: 5px;">
                                    <a href="${pageContext.request.contextPath}/patient/patientBasicReport" class="btndrop" style="color: white">Stop</a>
                                </div>
                            </div>
                        </div>
                        <br clear="all">
                    </div>
                </form:form>
                <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                    <div class="pull-right btndrop" style="color: white" onclick="exportReportData()">Export Pdf</div>
                </div>

                <div class="clearfix" style="padding-left: 15px;padding-right: 15px;">
                    <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:45px; padding-top: 5px;">
                        <div class="contents">                                   
                            <div class="table-box patient-tablebox">
                                <table class="display" id="example" class="display table">
                                    <thead>
                                        <tr class="row grid-header">
                                            <th class="">Patient Name</th>
                                            <th class="">Registered Status</th>
                                            <th class="">Date of Service</th>
                                            <th class="">Phone Number</th>
                                            <th class="">RX Number</th>
                                            <th class="">Refill Sequence</th>
                                            <th class="">Medication</th>
                                            <th class="">Qty</th>
                                            <th class="">Days Supply</th>
                                            <th class="">Insurance Applied</th>
                                            <th class="">Member Out Of Pocket</th>
                                            <th class="">Sales Tax Collected</th>
                                            <th class="">Delivery Method</th>
                                            <th class="">Delivery FEE</th>
                                            <th class="">Total Charged to Payment Card</th>
                                            <th>Address Delivered Alias</th>
                                            <th>Patient Signature</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${list}">
                                            <tr class="row grid-row">
                                                <td class="" valign="top">${row.patientName}</td>
                                                <td class="" valign="top">${row.registeredStatus}</td>
                                                <td class="" valign="top"><span id="tbl_textalign"><fmt:formatDate pattern="MM/dd/yyyy" value="${row.createdOn}"/></span></td>
                                                <td class="" valign="top">${row.mobileNumber}</td>
                                                <td class="" valign="top">${row.rxNumber}</td>
                                                <td class="" valign="top">${row.refill}</td>
                                                <td class="" valign="top">${row.drugName} ${row.drugType}</td>
                                                <td class="" valign="top">${row.qty}</td>
                                                <td class="" valign="top">${row.daysSupply}</td>
                                                <td class="" valign="top">${row.paymentType}</td>
                                                <td class="" valign="top">${row.finalPaymentStr}</td>
                                                <td class="" valign="top">${row.salesTaxStr}</td>
                                                <td class="" valign="top" style="${row.deliveryPreferencesName eq 'Same Day' || row.deliveryPreferencesName eq 'Next Day*'?'color:red':''}">
                                                    ${row.deliveryPreferencesName}
                                                </td>
                                                <td class="" valign="top" style="${row.deliveryPreferencesName eq 'Same Day' || row.deliveryPreferencesName eq 'Next Day*'?'color:red':''}">
                                                    ${row.handlingFeeStr}
                                                </td>
                                                <td class="" valign="top">${row.totalPaymentStr}</td>
                                                <td valign="top">${row.shippingAddress}</td>
                                                <td class="" valign="top">
                                                    <c:if test="${not empty row.patientSignature}">
                                                        <img src="${row.patientSignature}" height="40" width="40"/>
                                                    </c:if>
                                                </td>
                                            </tr> 
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div> <!-- /wrp -->
            </div> <!-- /content -->
        </div> <!-- /wrapper -->
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                format: 'm/d/Y',
                onChangeDateTime: function (dp, $input) {
                    jQuery('#datetimepicker').datetimepicker('hide');
                    jQuery('#datetimepicker1').datetimepicker('hide');
                }

            });
            function exportReportData() {
                var table = $('#example tbody tr').children().length - 1;
                if (!validateField()) {
                    return;
                }
                if (table !== 0) {
                    window.open('${pageContext.request.contextPath}/patient/exportPdf?reportName=patientBasicReportData&fromDate=' + $("#datetimepicker").val() + '&toDate=' + $("#datetimepicker1").val() + '&phoneNumber=' + $("#phoneNumber").val() + '&ptName=' + $("#txtPtName").val() + '&email=' + $("#txtEmail").val(), "_blank");
                }
            }
            function validateField() {
                var valid = true;
                if ($("#datetimepicker").val() == "" && $("#datetimepicker1").val() == ""
                        && $("#phoneNumber").val() == "") {
                    if ($("#datetimepicker").val() == "") {
                        $("#fromDateReq").text("Required");
                        $("#fromDateReq").show();
                        valid = false;
                    }
                    if ($("#datetimepicker1").val() == "") {
                        $("#toDateReq").text("Required");
                        $("#toDateReq").show();
                        valid = false;
                    }
                    if ($("#phoneNumber").val() == "") {
                        $("#phoneNumberReq").text("Required");
                        $("#phoneNumberReq").show();
                        valid = false;
                    }
                }
                return valid;
            }
            function enableSearchBtn(v) {
                if (v.length > 0) {
                    $("#btnSearch").removeAttr("disabled");
                } else {
                    $("#btnSearch").attr("disabled", "disabled");
                }
            }
        </script>
    </body>
</html>

























