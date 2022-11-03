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
                    <i class="fa fa-home"></i> Home  <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Survey Report
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Survey Report</h1>
                </div>
                <form:form action="${pageContext.request.contextPath}/campaign/surveyReports" commandName="campaigns" role="form" method="Post">
                    <div class="col-sm-12 surveyreports-ipad" style="padding-top: 15px;">
                        <div class="form-group margin-ive">
                            <input type="hidden" id="campId" name="campId" value="13"/>
                            <label  style="color: #444444; display: none;" class="tbl_f col-sm-2 surveyreports">Campaign Title:<span style="color:red">*</span></label>
                            <div class="col-sm-3 width20-ipad" style="display: none;">
                                <form:select id="campId" path="campId" cssClass="form-control selectpicker show-tick" onchange="cascadingDropDown(this.value,'survey')">
                                    <form:option value="0" label="Please Select" />
                                    <form:options items="${campaignslist}" itemValue="campaignId" itemLabel="campaignName"/>
                                </form:select>
                                <span class="errorMessage">${emptyCampaign}</span>
                            </div>
                            <label class="tbl_f col-sm-2 surveyreports lbl-sm">Survey Title:<span style="color:red">*</span></label>
                            <div class="col-sm-3 drp-ipad">
                                <form:select id="survey" path="survey.id" cssClass="form-control selectpicker show-tick">
                                    <form:option value="0" label="Please Select" />
                                    <form:options items="${surveyList}" itemValue="id" itemLabel="title"/>
                                </form:select>
                                <span class="errorMessage">${emptySurvey}</span>
                            </div>

                            <div class="col-sm-2" style="float: right;">
                                <div class="btn-group btn-dropright-ipad">
                                    <button class="btndrop" style="color: white">Generate Report</button>
                                </div>
                                <div class="btn-group btn-drop-ipad">
                                    <button class="btndrop" style="color: white"> Stop</button>
                                </div>
                            </div>
                        </div>
                        <br clear="all">
                    </div>
                </form:form>
                <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">

                </div>

                <div class="clearfix" style="padding-left: 15px;padding-right: 15px;">
                    <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:58px;">

                        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                        <div class="contents pharmacyportal_panel panel-default" style="max-height:auto; height: 150px;"> 
                            <ul class="nav nav-tabs" style="background: #eceaef; height: 29px;">
                                <li role="presentation" class="active"><a href="#" style="color: #444444; height: 29px; margin-right: 0px; padding-top: 4px;">Survey Report</a></li>
                            </ul>
                            <c:choose>
                                <c:when test="${not empty list}">
                                    <div class="table-box" style="margin-top: 3px; margin-bottom: 2px;padding-left: 5px; padding-right: 5px;">
                                        <span class="tbl_f">Total Questions: ${totalRecords}</span>
                                        <span style="float: right;">
                                            <img src="${pageContext.request.contextPath}/resources/images/excel.png" onclick="exportReportData('exportExcel')" />
                                            <img src="${pageContext.request.contextPath}/resources/images/pdf.png" onclick="exportReportData('surveyReports')" />
                                            <span class="tbl_f">
                                                <%
                                                    Date date = new Date();
                                                    SimpleDateFormat sdf = new SimpleDateFormat("MMM,dd,yyyy HH:mm a");
                                                    String today = sdf.format(date);
                                                %>
                                                <%=today%> 
                                            </span>
                                        </span>

                                        <div id="exampletable">
                                            <table class="display table" id="example" class="display">                                         
                                                <thead style="border-top:1px solid #cdcdce;height:35px;">
                                                    <tr class="row grid-header">  
                                                        <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5 sorting">Question(s)</th>
                                                        <th style="width:145px !important;" class="col-lg-0.5 col-md-0.5 col-sm-0.5 col-xs-0.5 hidden-xs sorting">Total Responses</th>  
                                                        <th class="col-lg-6.5 col-md-6.5 col-sm-6.5 col-xs-6.5 hidden-sm hidden-xs " >Response(s)</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="row" items="${list}">
                                                        <tr class="row grid-row">
                                                            <td class="col-lg-5 col-md-5 col-sm-5 col-xs-5">${row.questionTitle}</td>
                                                            <td style="width:145px !important;" class="col-lg-0.5 col-md-0.5 col-sm-0.5 col-xs-0.5 hidden-xs">${row.grandTotal}</td>
                                                            <td class="col-lg-6.5 col-md-6.5 col-sm-6.5 col-xs-6.5 hidden-sm hidden-xs">
                                                                ${row.responseTitle}
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div style="text-align: center; color: #E70000;font-size: 12px;">No record found.</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div> <!-- /wrp -->
            </div> <!-- /content -->
        </div> <!-- /wrapper -->
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
                                                $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                                                    format: 'm/d/Y',
                                                    onChangeDateTime: function(dp, $input) {
                                                        jQuery('#datetimepicker').datetimepicker('hide');
                                                        jQuery('#datetimepicker1').datetimepicker('hide');
                                                    }

                                                });
                                                function exportReportData(source) {
                                                    var table = $('#example tbody tr').children().length - 1;
                                                    var campaign = $("#campId").val();
                                                    var survey = $("#survey").val();
                                                    if (source === 'surveyReports' && table !== 0) {
                                                        window.open('${pageContext.request.contextPath}/campaign/pdfDownload.pdf?campaign=' + campaign + '&survey=' + survey, "_blank");
                                                    } else if (source === 'exportExcel' && table !== 0) {
                                                        window.open('${pageContext.request.contextPath}/campaign/excelDownload.xls?campaign=' + campaign + '&survey=' + survey, "_blank");
                                                    }
                                                    else {
                                                        alert("Table empty");
                                                    }
                                                }
                                                function cascadingDropDown(parentVal, child) {
                                                    if (parentVal > 0) {
                                                        $.ajax({
                                                            url: "${pageContext.request.contextPath}/campaign/surveyList/" + parentVal,
                                                            dataType: "json",
                                                            success: function(data) {
                                                                $('[id="' + child + '"]')
                                                                        .find('option')
                                                                        .remove()
                                                                        .end()
                                                                        .append('<option value="0">Please Select</option>')
                                                                        .val('Select One');
                                                                $.each(data, function(index, element) {
                                                                    $('[id="' + child + '"]').append($('<option>', {value: element.id, text: element.title}));
                                                                });
                                                                $('[id="' + child + '"]').selectpicker('refresh');
                                                            }
                                                        });
                                                    }
                                                    else {
                                                        $('[id="' + child + '"]')
                                                                .find('option')
                                                                .remove()
                                                                .end()
                                                                .append('<option value="0">Please Select</option>')
                                                                .val('Select One');
                                                        $('[id="' + child + '"]').selectpicker('refresh');

                                                    }
                                                }
        </script>
    </body>
</html>
