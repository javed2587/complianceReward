<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home  <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> In App Notification Report
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;In App Notification Report</h1>
                </div>
                <form:form action="${pageContext.request.contextPath}/inAppReport/view" commandName="baseDTO" role="form" method="Post" onsubmit="return validateField()">
                    <div class="col-sm-12 surveyreports-ipad" style="padding-top: 15px;">
                        <div class="form-group margin-ive">
                            <div class="col-lg-3 col-md-6 drp-ipad" ">
                                <div class="input-group">
                                    <fmt:formatDate var="fromDate" pattern="MM/dd/yyyy" value="${baseDTO.fromDate}"/>
                                    <span class="input-group-addon" id="basic-addon2">From:<span style="color:red">*</span></span>
                                    <form:input id="datetimepicker" path="fromDate" cssClass="form-control" value="${fromDate}" aria-describedby="basic-addon2" placeholder="MM/dd/yyyy"/>
                                </div>
                                <span id="fromDateReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-3 col-md-6 drp-ipad">
                                <div class="input-group">
                                    <fmt:formatDate var="toDate" pattern="MM/dd/yyyy" value="${baseDTO.toDate}"/>
                                    <span class="input-group-addon" id="basic-addon3">To:<span style="color:red">*</span></span>
                                    <form:input id="datetimepicker1" path="toDate" cssClass="form-control" value="${toDate}" aria-describedby="basic-addon3" placeholder="MM/dd/yyyy"/>
                                </div>
                                <span id="toDateReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-3 col-md-6 drp-ipad">
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon4">Phone #:<span style="color:red">*</span></span>
                                    <form:input id="phoneNumber" path="phoneNumber" cssClass="form-control" aria-describedby="basic-addon4" placeholder="Enter Phone Number" maxlength="10"/>
                                </div>
                                <span id="phoneNumberReq" class="errorMessage"></span>
                            </div>
                            <div class="col-lg-2 col-md-6" style="margin-top: -9px;">
                                <div class="btn-group btn-dropright-ipad">
                                    <button class="btndrop" style="color: white">Generate Report</button>
                                </div>
                                <div class="btn-group btn-drop-ipad" style="padding-top: 5px;">
                                    <a href="${pageContext.request.contextPath}/inAppReport/view" class="btndrop" style="color: white">Stop</a>
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
                            <div class="table-box table-responsive">
                                <table class="display" id="example" class="display table">
                                    <thead>
                                        <tr class="row grid-header">
                                            <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Date Time</th>
                                            <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Phone</th>
                                            <th class="col-lg-10 col-md-10 col-sm-10 col-xs-10">Message</th>
                                            <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Response</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${list}">
                                            <tr class="row grid-row">
                                                <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1" valign="top"><span id="tbl_textalign"><fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${row.createdOn}"/></span></td>
                                                <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1" valign="top">${row.mobileNumber}</td>
                                                <td class="col-lg-10 col-md-10 col-sm-10 col-xs-10">${row.messageText}</td>
                                                <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1" valign="top">${row.messageResponses}</td>
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
                    window.open('${pageContext.request.contextPath}/inAppReport/exportPdf?fromDate=' + $("#datetimepicker").val() + '&toDate=' + $("#datetimepicker1").val() + '&phoneNumber=' + $("#phoneNumber").val(), "_blank");
                }
            }
            function validateField() {
                var valid = true;
                if ($("#datetimepicker").val() == "" && $("#datetimepicker1").val() == "" && $("#phoneNumber").val() == "") {
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
        </script>
    </body>
</html>
