<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/newMenu.jsp" />

                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Shipping Pref. Setup
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Shipping Preferences</h1>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/delivery/load" commandName="deliveryPreferences" method="post" onsubmit="return validateField()">
                    <div class="page-message">
                        <div class="${not empty message?'message':'errorMessage'} messageheading" id="message">${not empty message?message:errorMessage}</div>
                        <br clear="all">
                    </div>
                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                        <div style="float: right;">
                            <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/delivery/load'"><a href="${pageContext.request.contextPath}/delivery/load" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#deliveryPreferences').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px;padding-bottom: 20px;padding-top:15px;background:#F7F7F7;">
                            <div class="form-group margin-ive fgPL-zero">    
                                <div class="col-sm-3">
                                    <label>Same Day:<span style="color:red">*</span></label>
                                    <form:hidden path="deliveryPreferencesList[0].name" value="${not empty deliveryPreferences.deliveryPreferencesList[0].name?deliveryPreferences.deliveryPreferencesList[0].name:'Same Day'}"/>
                                    <form:hidden path="deliveryPreferencesList[0].id"/>
                                    <form:hidden path="deliveryPreferencesList[0].createdBy"/>
                                    <form:hidden path="deliveryPreferencesList[0].createdOn"/>
                                    <form:input id="same" path="deliveryPreferencesList[0].cost" cssClass="form-control" maxlength="6" onkeypress="return isFloatNumber(this,event)"/>
                                    <div id="sameReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-6">
                                    <label>Description:<span style="color:red">*</span></label>
                                    <form:input id="samedayDescription" path="deliveryPreferencesList[0].description" cssClass="form-control" maxlength="255"/>
                                    <div id="samedayDescriptionReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    &nbsp;
                                </div>
                            </div>
                            <div style="clear:both;"></div>
                            <div class="form-group margin-ive fgPL-zero">
                                <div class="col-sm-3">
                                    <label>1-2 Days($):<span style="color:red">*</span></label>
                                    <form:hidden path="deliveryPreferencesList[1].name" value="${not empty deliveryPreferences.deliveryPreferencesList[1].name?deliveryPreferences.deliveryPreferencesList[1].name:'1-2 Day'}"/>
                                    <form:hidden path="deliveryPreferencesList[1].id"/>
                                    <form:hidden path="deliveryPreferencesList[1].createdBy"/>
                                    <form:hidden path="deliveryPreferencesList[1].createdOn"/>
                                    <form:input id="1-2" path="deliveryPreferencesList[1].cost" cssClass="form-control" maxlength="6" onkeypress="return isFloatNumber(this,event)"/>
                                    <div id="1-2Req" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-6">
                                    <label>Description:<span style="color:red">*</span></label>
                                    <form:input id="1-2daysDescription" path="deliveryPreferencesList[1].description" cssClass="form-control" maxlength="255"/>
                                    <div id="1-2daysDescriptionReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    &nbsp;
                                </div>
                            </div>
                            <div style="clear:both;"></div>    
                            <div class="form-group margin-ive fgPL-zero">
                                <div class="col-sm-3">
                                    <label>2-3 Days($):<span style="color:red">*</span></label>
                                    <form:hidden path="deliveryPreferencesList[2].name" value="${not empty deliveryPreferences.deliveryPreferencesList[2].name?deliveryPreferences.deliveryPreferencesList[2].name:'2-3 Day'}"/>
                                    <form:hidden path="deliveryPreferencesList[2].id"/>
                                    <form:hidden path="deliveryPreferencesList[2].createdBy"/>
                                    <form:hidden path="deliveryPreferencesList[2].createdOn"/>
                                    <form:input id="2-3" path="deliveryPreferencesList[2].cost" cssClass="form-control" maxlength="6" onkeypress="return isFloatNumber(this,event)"/>
                                    <div id="2-3Req" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-6">
                                    <label>Description:<span style="color:red">*</span></label>
                                    <form:input id="2-3description" path="deliveryPreferencesList[2].description" cssClass="form-control" maxlength="255"/>
                                    <div id="2-3descriptionReq" class="errorMessage"></div>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    &nbsp;
                                </div>
                            </div>
                            <div style="clear:both;"></div>

                        </div> 
                    </div>
                </div>
                <div style="clear: both;"></div>
            </div>
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
        $("#sameReq").hide();
        $("#1-2Req").hide();
        $("#2-3Req").hide();
        $("#message").hide();
        $("#samedayDescriptionReq").hide();
        $("#1-2daysDescriptionReq").hide();
        $("#2-3descriptionReq").hide();
    }
    function validateField() {
        var empty = true;
        if ($("#same").val() === "") {
            $("#sameReq").text("Required");
            $("#sameReq").show();
            empty = false;
        }
        if ($("#1-2").val() === "") {
            $("#1-2Req").text("Required");
            $("#1-2Req").show();
            empty = false;
        }
        if ($("#2-3").val() === "") {
            $("#2-3Req").text("Required");
            $("#2-3Req").show();
            empty = false;
        }
        if ($("#samedayDescription").val().trim() === "") {
            $("#samedayDescriptionReq").text("Required");
            $("#samedayDescriptionReq").show();
            empty = false;
        }
        if ($("#1-2daysDescription").val().trim() === "") {
            $("#1-2daysDescriptionReq").text("Required");
            $("#1-2daysDescriptionReq").show();
            empty = false;
        }
        if ($("#2-3description").val().trim() === "") {
            $("#2-3descriptionReq").text("Required");
            $("#2-3descriptionReq").show();
            empty = false;
        }
        return empty;
    }
</script>
</body>
</html>
