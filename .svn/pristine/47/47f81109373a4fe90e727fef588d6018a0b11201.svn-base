<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/menu.jsp" />

                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Reward Points Setup 
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Reward Points</h1>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/rewardPoints/load" commandName="rewardPoints" method="post" onsubmit="return validateField()">
                    <div class="page-message">
                        <div class="${not empty message?'message':'errorMessage'} messageheading" id="message">${not empty message?message:errorMessage}</div>
                        <br clear="all">
                    </div>
                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                        <div style="float: right;">
                            <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/rewardPoints/load'"><a href="${pageContext.request.contextPath}/rewardPoints/load" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#rewardPoints').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px;padding-bottom: 20px;padding-top:15px;background:#F7F7F7;">
                            <div class="form-group margin-ive fgPL-zero">    
                                <div class="col-sm-3">
                                    <label>Complete to Enrollment:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="enrollvalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[0].point}"/>
                                    <form:hidden path="rewardPointlist[0].type" value="${not empty rewardPoints.rewardPointlist[0].type?rewardPoints.rewardPointlist[0].type:'Complete to Enrollment'}"/>
                                    <form:hidden path="rewardPointlist[0].id"/>
                                    <form:hidden path="rewardPointlist[0].createdBy"/>
                                    <form:hidden path="rewardPointlist[0].createdOn"/>
                                    <form:input id="enroll" path="rewardPointlist[0].point" cssClass="form-control2" maxlength="5" onkeypress="return IsDigit(event)" value="${enrollvalue}"/>
                                    <div id="enrollReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Share with Friend:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="sharevalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[1].point}"/>
                                    <form:hidden path="rewardPointlist[1].type" value="${not empty rewardPoints.rewardPointlist[1].type?rewardPoints.rewardPointlist[1].type:'Share with Friend'}"/>
                                    <form:hidden path="rewardPointlist[1].id"/>
                                    <form:hidden path="rewardPointlist[1].createdBy"/>
                                    <form:hidden path="rewardPointlist[1].createdOn"/>                                  
                                    <form:input id="share" path="rewardPointlist[1].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${sharevalue}"/>
                                    <div id="shareReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Feedback Survey:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="feedbackvalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[2].point}"/>
                                    <form:hidden path="rewardPointlist[2].type" value="${not empty rewardPoints.rewardPointlist[2].type?rewardPoints.rewardPointlist[2].type:'Feedback Survey'}"/>
                                    <form:hidden path="rewardPointlist[2].id"/>
                                    <form:hidden path="rewardPointlist[2].createdBy"/>
                                    <form:hidden path="rewardPointlist[2].createdOn"/>
                                    <form:input id="feedback" path="rewardPointlist[2].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${feedbackvalue}"/>
                                    <div id="feedbackReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Order Placed:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="ordervalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[3].point}"/>
                                    <form:hidden path="rewardPointlist[3].type" value="${not empty rewardPoints.rewardPointlist[3].type?rewardPoints.rewardPointlist[3].type:'Order Placed'}"/>
                                    <form:hidden path="rewardPointlist[3].id"/>
                                    <form:hidden path="rewardPointlist[3].createdBy"/>
                                    <form:hidden path="rewardPointlist[3].createdOn"/>
                                    <form:input id="order" path="rewardPointlist[3].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${ordervalue}"/>
                                    <div id="orderReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div style="clear:both;"></div>
                            <div class="form-group fgPL-zero" style="position: relative; top: 15px;">
                                <div class="col-sm-3">
                                    <label>Transfer Rx:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="transfervalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[4].point}"/>
                                    <form:hidden path="rewardPointlist[4].type" value="${not empty rewardPoints.rewardPointlist[4].type?rewardPoints.rewardPointlist[4].type:'Transfer Rx'}"/>
                                    <form:hidden path="rewardPointlist[4].id"/>
                                    <form:hidden path="rewardPointlist[4].createdBy"/>
                                    <form:hidden path="rewardPointlist[4].createdOn"/>
                                    <form:input id="transfer" path="rewardPointlist[4].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${transfervalue}"/>
                                    <div id="transferReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Rx Refill:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="rxvalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[5].point}"/>
                                    <form:hidden path="rewardPointlist[5].type" value="${not empty rewardPoints.rewardPointlist[5].type?rewardPoints.rewardPointlist[5].type:'Rx Refill'}"/>
                                    <form:hidden path="rewardPointlist[5].id"/>
                                    <form:hidden path="rewardPointlist[5].createdBy"/>
                                    <form:hidden path="rewardPointlist[5].createdOn"/>
                                    <form:input id="rx" path="rewardPointlist[5].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${rxvalue}"/>
                                    <div id="rxReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Health Education:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="healthvalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[6].point}"/>
                                    <form:hidden path="rewardPointlist[6].type" value="${not empty rewardPoints.rewardPointlist[6].type?rewardPoints.rewardPointlist[6].type:'Health Education'}"/>
                                    <form:hidden path="rewardPointlist[6].id"/>
                                    <form:hidden path="rewardPointlist[6].createdBy"/>
                                    <form:hidden path="rewardPointlist[6].createdOn"/>
                                    <form:input id="health" path="rewardPointlist[6].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${healthvalue}"/>
                                    <div id="healthReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Patient Glucose Reading:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="biometryvalue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[7].point}"/>
                                    <form:hidden path="rewardPointlist[7].type" value="${not empty rewardPoints.rewardPointlist[7].type?rewardPoints.rewardPointlist[7].type:'Patient Glucose Reading'}"/>
                                    <form:hidden path="rewardPointlist[7].id"/>
                                    <form:hidden path="rewardPointlist[7].createdBy"/>
                                    <form:hidden path="rewardPointlist[7].createdOn"/>
                                    <form:input id="biometry" path="rewardPointlist[7].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${biometryvalue}"/>
                                    <div id="biometryReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-lg-3"> 
                                    <label> Express Enrollment:<span style="color:red">*</span></label>
                                    <fmt:formatNumber var="expressEnrollNumValue" type="number" maxIntegerDigits="5" value="${rewardPoints.rewardPointlist[10].point}"/>
                                    <form:hidden path="rewardPointlist[10].type" value="${not empty rewardPoints.rewardPointlist[10].type?rewardpoints.rewardPointlist[10].type:'Express Enrollment'}"/>
                                    <form:hidden path="rewardPointlist[10].id"/>
                                    <form:hidden path="rewardPointlist[10].createdBy"/>
                                    <form:hidden path="rewardPointlist[10].createdOn"/>
                                    <form:input id="expressEnroll" path="rewardPointlist[10].point" cssClass="form-control2"  maxlength="5" onkeypress="return IsDigit(event)" value="${expressEnrollNumValue}"/>
                                    <div id="expressEnrollReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-lg-3">
                                    <label> BMI Reading(%):<span style="color:red">*</span></label>
                                </div>
                            </div>
                            <div style="clear:both;"></div>
                        </div>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px;padding-bottom:20px; margin-top: -30px;background:#f7f7f7;display: none;">  
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">    
                                    <div class="col-lg-3 col-sm-4 reward-padding-left"> 
                                        <div class="form-group fgPL-zero">                    
                                            <label> PT Margin for Rx Order(%):<span style="color:red">*</span></label>
                                            <fmt:formatNumber var="numberType1" type="number" maxIntegerDigits="2" value="${rewardPoints.rewardPointlist[8].point}"/>
                                            <form:hidden path="rewardPointlist[8].type" value="${not empty rewardPoints.rewardPointlist[8].type?rewardpoints.rewardPointlist[8].type:'PT Margin for Rx Order'}"/>
                                            <form:hidden path="rewardPointlist[8].id"/>
                                            <form:hidden path="rewardPointlist[8].createdBy"/>
                                            <form:hidden path="rewardPointlist[8].createdOn"/>
                                            <form:input id="PTMargin" path="rewardPointlist[8].point" cssClass="form-control2"  maxlength="2" onkeypress="return IsDigit(event)" value="${numberType1}"/>
                                            <div id="PTMarginReq" class="errorMessage"></div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-sm-4"> 
                                        <label> PT Margin for Transfer Rx(%):<span style="color:red">*</span></label>
                                        <fmt:formatNumber var="numberType" type="number" maxIntegerDigits="2" value="${rewardPoints.rewardPointlist[9].point}"/>
                                        <form:hidden path="rewardPointlist[9].type" value="${not empty rewardPoints.rewardPointlist[9].type?rewardpoints.rewardPointlist[9].type:'PT Margin for Transfer Rx'}"/>
                                        <form:hidden path="rewardPointlist[9].id"/>
                                        <form:hidden path="rewardPointlist[9].createdBy"/>
                                        <form:hidden path="rewardPointlist[9].createdOn"/>
                                        <form:input id="PT" path="rewardPointlist[9].point" cssClass="form-control2"  maxlength="2" onkeypress="return IsDigit(event)" value="${numberType}"/>
                                        <div id="PTReq" class="errorMessage"></div>
                                    </div>
                                </div>
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-6">
                                        &nbsp;
                                    </div>
                                </div>
                                <div style="clear:both;"></div>
                            </div>
                        </div>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px;padding-bottom:20px; margin-top: -30px;background:#f7f7f7;">  
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">    
                                    <div class="col-sm-3"> 
                                        <div class="col-sm-6" style="padding-left:0px;">
                                            <label>Basic:<span style="color:red">*</span></label>
                                            <form:hidden path="patientRewardLevel[0].type" value="${not empty rewardPoints.patientRewardLevel[0].type?rewardpoints.patientRewardLevel[0].type:'Basic'}"/>
                                            <form:hidden path="patientRewardLevel[0].id"/>
                                            <form:hidden path="patientRewardLevel[0].createdBy"/>
                                            <form:hidden path="patientRewardLevel[0].createdOn"/>
                                            <form:input id="basicLevelFrom" path="patientRewardLevel[0].fromLevel" cssClass="form-control2" onkeypress="return IsDigit(event)" readonly="true" value="0" placeholder="From" maxlength="3"/>
                                            <div id="basicLevelFromReq" class="errorMessage"></div>
                                        </div>
                                        <div class="col-sm-6 rewardlevel basicPoint-padding-left">
                                            <label>&nbsp;</label>
                                            <form:input id="basicLevelTo" path="patientRewardLevel[0].toLevel" cssClass="form-control2" onkeypress="return IsDigit(event)" placeholder="To" maxlength="4" onchange="onChangeValue(this.value,'silverLevelFrom')"/>
                                            <div id="basicLevelToReq" class="errorMessage"></div>
                                        </div>
                                    </div>        
                                </div>
                                <div class="form-group fgPL-zero">    
                                    <div class="col-sm-3"> 
                                        <div class="col-sm-6" style="padding-left:0px;">
                                            <label>Silver:<span style="color:red">*</span></label>

                                            <form:hidden path="patientRewardLevel[1].type" value="${not empty rewardPoints.patientRewardLevel[1].type?rewardpoints.patientRewardLevel[1].type:'Silver'}"/>
                                            <form:hidden path="patientRewardLevel[1].id"/>
                                            <form:hidden path="patientRewardLevel[1].createdBy"/>
                                            <form:hidden path="patientRewardLevel[1].createdOn"/>
                                            <form:input id="silverLevelFrom" path="patientRewardLevel[1].fromLevel" cssClass="form-control2" onkeypress="return IsDigit(event)"  placeholder="From" readonly="true"/>
                                            <div id="silverFrmReq" class="errorMessage"></div>
                                        </div>
                                        <div class="col-sm-6 rewardlevel basicPoint-padding-left">
                                            <label>&nbsp;</label>
                                            <form:input id="silverLevelTo" path="patientRewardLevel[1].toLevel" cssClass="form-control2" onkeypress="return IsDigit(event)" placeholder="To" onchange="onChangeValue(this.value,'goldLevelFrom')" />
                                            <div id="silverLevelToReq" class="errorMessage"></div>
                                        </div>
                                    </div> 
                                </div>
                                <div class="form-group fgPL-zero">    
                                    <div class="col-sm-3"> 
                                        <div class="col-sm-6" style="padding-left:0px;">
                                            <label>Gold:<span style="color:red">*</span></label>
                                            <form:hidden path="patientRewardLevel[2].type" value="${not empty rewardPoints.patientRewardLevel[2].type?rewardpoints.patientRewardLevel[2].type:'Gold'}"/>
                                            <form:hidden path="patientRewardLevel[2].id"/>
                                            <form:hidden path="patientRewardLevel[2].createdBy"/>
                                            <form:hidden path="patientRewardLevel[2].createdOn"/>
                                            <form:input id="goldLevelFrom" path="patientRewardLevel[2].fromLevel" cssClass="form-control2" onkeypress="return IsDigit(event)"  placeholder="From" readonly="true"/>
                                            <div id="goldLevelFromReq" class="errorMessage"></div>
                                        </div>
                                        <div class="col-sm-6 rewardlevel basicPoint-padding-left">
                                            <label>&nbsp;</label>
                                            <form:input id="goldLevelTo" path="patientRewardLevel[2].toLevel" cssClass="form-control2" onkeypress="return IsDigit(event)" placeholder="To" onchange="onChangeValue(this.value,'permiumLevelFrom')"/>
                                            <div id="goldLevelToReq" class="errorMessage"></div>
                                        </div>
                                    </div> 
                                </div>
                                <div class="form-group fgPL-zero">    
                                    <div class="col-sm-3"> 
                                        <div class="col-sm-6" style="padding-left:0px;">
                                            <label>Permium:<span style="color:red">*</span></label>
                                            <form:hidden path="patientRewardLevel[3].type" value="${not empty rewardPoints.patientRewardLevel[3].type?rewardpoints.patientRewardLevel[3].type:'Permium'}"/>
                                            <form:hidden path="patientRewardLevel[3].id"/>
                                            <form:hidden path="patientRewardLevel[3].createdBy"/>
                                            <form:hidden path="patientRewardLevel[3].createdOn"/>
                                            <form:input id="permiumLevelFrom" path="patientRewardLevel[3].fromLevel" cssClass="form-control2" onkeypress="return IsDigit(event)" placeholder="From" readonly="true"/>
                                            <div id="permiumLevelFromReq" class="errorMessage"></div>
                                        </div>
                                        <div class="col-sm-6 rewardlevel basicPoint-padding-left">
                                            <label>&nbsp;</label>
                                            <form:input id="permiumLevelTo" path="patientRewardLevel[3].toLevel" cssClass="form-control2" onkeypress="return IsDigit(event)" placeholder="To"/>
                                            <div id="permiumLevelToReq" class="errorMessage"></div>
                                        </div>
                                    </div> 
                                </div>
                                <div style="clear:both;"></div>
                            </div>
                        </div>
                    </form:form>
                </div> <!-- /content -->
                <jsp:include page="./inc/footer.jsp" />
            </div> <!-- /wrapper -->
            <script type="text/javascript">
                function onChangeValue(val, index) {
                    if (val !== "") {
                        $("#" + index).val(parseInt(val) + 1);
                    }
                }
                function startTimer()
                {
                    window.setInterval("hideMessage()", 5000);
                }
                function hideMessage() {
                    $("#enrollReq").hide();
                    $("#shareReq").hide();
                    $("#feedbackReq").hide();
                    $("#orderReq").hide();
                    $("#transferReq").hide();
                    $("#rxReq").hide();
                    $("#healthReq").hide();
                    $("#biometryReq").hide();
                    $("#PTMarginReq").hide();
                    $("#PTReq").hide();
                    $("#message").hide();
                    $("#basicLevelFromReq").hide();
                    $("#basicLevelToReq").hide();
                    $("#silverFrmReq").hide();
                    $("#silverLevelToReq").hide();
                    $("#goldLevelFromReq").hide();
                    $("#goldLevelToReq").hide();
                    $("#permiumLevelFromReq").hide();
                    $("#permiumLevelToReq").hide();
                    $("#expressEnrollReq").hide();
                    $("#bmiReadingReq").hide();
                }
                function validateField() {
                    var empty = true;
                    if ($("#enroll").val() === "") {
                        $("#enrollReq").text("Required");
                        $("#enrollReq").show();
                        empty = false;
                    }
                    if ($("#share").val() === "") {
                        $("#shareReq").text("Required");
                        $("#shareReq").show();
                        empty = false;
                    }
                    if ($("#feedback").val() === "") {
                        $("#feedbackReq").text("Required");
                        $("#feedbackReq").show();
                        empty = false;
                    }
                    if ($("#order").val() === "") {
                        $("#orderReq").text("Required");
                        $("#orderReq").show();
                        empty = false;
                    }
                    if ($("#transfer").val() === "") {
                        $("#transferReq").text("Required");
                        $("#transferReq").show();
                        empty = false;
                    }
                    if ($("#rx").val() === "") {
                        $("#rxReq").text("Required");
                        $("#rxReq").show();
                        empty = false;
                    }
                    if ($("#health").val() === "") {
                        $("#healthReq").text("Required");
                        $("#healthReq").show();
                        empty = false;
                    }
                    if ($("#biometry").val() === "") {
                        $("#biometryReq").text("Required");
                        $("#biometryReq").show();
                        empty = false;
                    }
                    if ($("#PTMargin").val() === "") {
                        $("#PTMarginReq").text("Required");
                        $("#PTMarginReq").show();
                        empty = false;
                    }
                    if ($("#PT").val() === "") {
                        $("#PTReq").text("Required");
                        $("#PTReq").show();
                        empty = false;
                    }
                    if ($("#expressEnroll").val() === "") {
                        $("#expressEnrollReq").text("Required");
                        $("#expressEnrollReq").show();
                        empty = false;
                    }
                    if ($("#basicLevelFrom").val() === "") {
                        $("#basicLevelFromReq").text("Required");
                        $("#basicLevelFromReq").show();
                        empty = false;
                    }
                    if ($("#bmiReading").val() === "") {
                        $("#bmiReadingReq").text("Required");
                        $("#bmiReadingReq").show();
                        empty = false;
                    }
                    if ($("#basicLevelTo").val() === "") {
                        $("#basicLevelToReq").text("Required");
                        $("#basicLevelToReq").show();
                        empty = false;
                    }
                    if ($("#silverLevelFrom").val() === "") {
                        $("#silverFrmReq").text("Required");
                        $("#silverFrmReq").show();
                        empty = false;
                    }
                    if ($("#silverLevelTo").val() === "") {
                        $("#silverLevelToReq").text("Required");
                        $("#silverLevelToReq").show();
                        empty = false;
                    }

                    if ($("#goldLevelFrom").val() === "") {
                        $("#goldLevelFromReq").text("Required");
                        $("#goldLevelFromReq").show();
                        empty = false;
                    }
                    if ($("#goldLevelTo").val() === "") {
                        $("#goldLevelToReq").text("Required");
                        $("#goldLevelToReq").show();
                        empty = false;
                    }

                    if ($("#permiumLevelFrom").val() === "") {
                        $("#permiumLevelFromReq").text("Required");
                        $("#permiumLevelFromReq").show();
                        empty = false;
                    }
                    if ($("#permiumLevelTo").val() === "") {
                        $("#permiumLevelToReq").text("Required");
                        $("#permiumLevelToReq").show();
                        empty = false;
                    }
                    if ($("#basicLevelTo").val() !== "" && $("#basicLevelFrom").val() !== "") {
                        if ($("#basicLevelTo").val() === $("#basicLevelFrom").val()) {
                            $("#basicLevelToReq").text("Must be greater than previous value");
                            $("#basicLevelToReq").show();
                            empty = false;
                        }
                    }
                    if ($("#silverLevelTo").val() !== "" && $("#silverLevelFrom").val() !== "") {
                        if ($("#silverLevelTo").val() <= $("#silverLevelFrom").val()) {
                            $("#silverLevelToReq").text("Must be greater than previous value");
                            $("#silverLevelToReq").show();
                            empty = false;
                        }
                    }
                    if ($("#goldLevelTo").val() !== "" && $("#goldLevelFrom").val() !== "") {
                        if ($("#goldLevelTo").val() <= $("#goldLevelFrom").val()) {
                            $("#goldLevelToReq").text("Must be greater than previous value");
                            $("#goldLevelToReq").show();
                            empty = false;
                        }
                    }
                    if ($("#permiumLevelTo").val() !== "" && $("#permiumLevelFrom").val() !== "") {
                        if ($("#permiumLevelFrom").val() <= $("#permiumLevelTo").val()) {
                            $("#permiumLevelToReq").text("Must be greater than previous value");
                            $("#permiumLevelToReq").show();
                            empty = false;
                        }
                    }
                    return empty;
                }
            </script>
    </body>
</html>
