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
                <jsp:include page="./inc/newMenu.jsp" />

                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Fee Setup 
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Program Fees</h1>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/feeSettings/load" commandName="feeSettings" method="post" onsubmit="return validateField()">
                    <div class="page-message">
                        <div class="${not empty message?'message':'errorMessage'} messageheading" id="message">${not empty message?message:errorMessage}</div>
                        <br clear="all">
                    </div>
                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                        <div style="float: right;">
                            <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/feeSettings/load'"><a href="${pageContext.request.contextPath}/feeSettings/load" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#feeSettings').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px;padding-bottom: 20px;padding-top:15px;background:#F7F7F7;">
                            <div class="form-group margin-ive fgPL-zero">    
                                <div class="col-sm-3">
                                    <label>Handling Fee($):<span style="color:red">*</span></label>
                                    <form:hidden path="feeSettingslist[0].type" value="${not empty feeSettings.feeSettingslist[0].type?feeSettings.feeSettingslist[0].type:'Handling Fee($)'}"/>
                                    <form:hidden path="feeSettingslist[0].id"/>
                                    <form:hidden path="feeSettingslist[0].createdBy"/>
                                    <form:hidden path="feeSettingslist[0].createdOn"/>
                                    <form:input id="handling" path="feeSettingslist[0].fee" cssClass="form-control" maxlength="6" onkeypress="return isFloatNumber(this,event)"/>
                                    <div id="handlingReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Per Point Value($):<span style="color:red">*</span></label><br>
                                    <form:hidden path="feeSettingslist[1].type" value="${not empty feeSettings.feeSettingslist[1].type?feeSettings.feeSettingslist[1].type:'Per Point Value($)'}"/>
                                    <form:hidden path="feeSettingslist[1].id"/>
                                    <form:hidden path="feeSettingslist[1].createdBy"/>
                                    <form:hidden path="feeSettingslist[1].createdOn"/>
                                    <form:input id="perpoint" path="feeSettingslist[1].fee" cssClass="form-control" maxlength="6" onkeypress="return isFloatNumber(this,event)"/>
                                    <div id="perpointReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Local Discount(%):<span style="color:red">*</span></label><br>
                                    <fmt:formatNumber var="localvalue" type="number" maxIntegerDigits="2" value="${feeSettings.feeSettingslist[2].fee}"/>
                                    <form:hidden path="feeSettingslist[2].type" value="${not empty feeSettings.feeSettingslist[2].type?feeSettings.feeSettingslist[2].type:'Local Discount(%)'}"/>
                                    <form:hidden path="feeSettingslist[2].id"/>
                                    <form:hidden path="feeSettingslist[2].createdBy"/>
                                    <form:hidden path="feeSettingslist[2].createdOn"/>
                                    <form:input id="local" path="feeSettingslist[2].fee" cssClass="form-control" maxlength="2" onkeypress="return IsDigit(event)" value="${localvalue}"/>
                                    <div id="localReq" class="errorMessage"></div>
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
            function startTimer()
            {
                window.setInterval("hideMessage()", 5000);
            }
            function hideMessage() {
                $("#handlingReq").hide();
                $("#perpointReq").hide();
                $("#localReq").hide();
                $("#message").hide();
            }
            function validateField() {
                var empty = true;
                if ($("#handling").val() === "") {
                    $("#handlingReq").text("Required");
                    $("#handlingReq").show();
                    empty = false;
                }
                if ($("#perpoint").val() === "") {
                    $("#perpointReq").text("Required");
                    $("#perpointReq").show();
                    empty = false;
                }
                if ($("#local").val() === "") {
                    $("#localReq").text("Required");
                    $("#localReq").show();
                    empty = false;
                }
                if ($("#handling").val() === "0") {
                    $("#handlingReq").text("Value should be greater than zero");
                    $("#handlingReq").show();
                    empty = false;
                }
                if ($("#perpoint").val() === "0") {
                    $("#perpointReq").text("Value should be greater than zero");
                    $("#perpointReq").show();
                    empty = false;
                }
                if ($("#local").val() === "0" || parseInt($("#local").val()) > 100) {
                    $("#localReq").text("value should be greater than 0 and less than 100");
                    $("#localReq").show();
                    empty = false;
                }
                return empty;
            }
            $(document).ready(function () {
                
            });
        </script>
    </body>
</html>
