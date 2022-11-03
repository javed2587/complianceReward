<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body onload="startTimer()">
        <div style="float: top;">  
            <img src="${pageContext.request.contextPath}/resources/images/QRHeader.jpg" class="bg" style="left: 0;right: 0;">
        </div> 
        <div>
            <div>

                <form:form action="${pageContext.request.contextPath}/QRCode" commandName="supportModel" cssClass="frm" method="Post">
                    <form:hidden path="shortCode" value="88202" />
                    <form:hidden path="eventName" value="Web" />
                    <form:hidden path="userId" value="GenRx" />
                    <form:hidden path="password" value="password@123" />
                    <form:hidden path="ctrigger"/>
                    <form:hidden path="communicationMethod" id="communicationMethod"/>
                    <form:hidden path="communicationId"/>
                    <div style="padding-top: 8px;">
                        <div class="col-sm-12 webPortal-marginleft" style="padding-bottom: 15px;">
                            <p class="wedportal-p"><span style="color:#2071b6;font-weight: bold;">Enroll Now! </span><span style="color:#2071b6;">GenRx Savings Program</span> Choose a method (below) to notified of savings</p>
                        </div>
                        <div class="${not empty message?'message webportal-paddleft':'errorMessage webportal-paddleft'}" id="message">${not empty message?message:errorMessage}</div>
                    </div>
                    <div>
                        <div>
                            <div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 widPortal-top">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 webPortal-textalign widPortal-top"><i class="fa fa-comments wedportal-icon"></i></div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-9">
                                        <form:input id="phoneNo" path="phoneNo" cssClass="form-control" placeholder="Mobile Phone" maxlength="10" onkeypress="return allowOnlyNumber(event)"/>
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" style="left: -15px;">
                                        <form:radiobutton id="chkPhoneNo" path="options" onclick="enableTextbox('phoneNo','email','ivr','chkEmail','chkIvr')" value="T"/>
                                    </div>
                                </div>  
                                <div class="col-lg-12 col-md-12 col-xs-12 webPortal-paddingtop widPortal-top">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 webPortal-textalign widPortal-top"><i class="fa fa-envelope wedportal-icon"></i></div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-9">
                                        <form:input id="email" path="email" cssClass="form-control" placeholder="E-mail" disabled="true"/>
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" style="left: -15px;">
                                        <form:radiobutton id="chkEmail" path="options" onclick="enableTextbox('email','ivr','phoneNo','chkPhoneNo','chkIvr')" value="E"/>
                                    </div>
                                </div> 
                                <div class="col-lg-12 col-md-12 col-xs-12 webPortal-paddingtop widPortal-top">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 webPortal-textalign widPortal-top"><i class="fa fa-phone wedportal-icon"></i></div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-9">
                                        <form:input id="ivr" path="ivr" cssClass="form-control" placeholder="Voice" maxlength="10" onkeypress="return allowOnlyNumber(event)" disabled="true"/>

                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" style="left: -15px;">
                                        <form:radiobutton id="chkPhoneNo" path="options" onclick="enableTextbox('ivr','email','phoneNo','chkPhoneNo','chkEmail')" value="I"/>
                                    </div>
                                </div> 
                                <div class="col-lg-12 col-md-12 col-xs-12 webPortal-paddingtop">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2">&nbsp;</div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-10">
                                        <form:checkbox path="tc" value="Yes"/>  <span style="font-family:arial;font-size: 14px; color: #626262;">Accept</span> <a href="http://api-direct.com/terms" style="font-family:arial;font-size: 14px;color:#2071b6;" target="_blank">Terms and Conditions</a>
                                    </div>
                                </div> 
                                <div class="col-lg-12 col-md-12 col-xs-12 webPortal-paddingtop">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2">&nbsp;</div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-10">
                                        <div style="background: #2071b6;" class="btndrop" onclick="$('#supportModel').submit();"><a class="btn_Color" style="color: white;">Submit</a></div>

                                    </div>
                                </div> 
                                <div class="col-sm-12 webPortal-paddingtop">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-2">&nbsp;</div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-10">
                                        <p style="font-family:arial;font-size: 12px; color: #626262; font-style: italic;">Applicable message & data rates may apply.</p>

                                    </div>
                                </div> 
                            </div>  
                        </div>
                    </div>
                </div>
            </form:form>
        </div> <!-- /content -->
        <div class="qrFooter">
            <img src="${pageContext.request.contextPath}/resources/images/Footer2_QR.jpg" class="bg">
        </div>
        <script type="text/javascript">
            function enableTextbox(id, emailId, ivrId, chk1, chk2) {
                $("#" + id).prop("disabled", false);
                $("#" + emailId).prop("disabled", true);
                $("#" + ivrId).prop("disabled", true);
                $('#' + chk1).attr('checked', false);
                $('#' + chk2).attr('checked', false);
                $('#' + emailId).val('');
                $('#' + ivrId).val('');
            }
            function startTimer()
            {
                window.setInterval("hideMessage()", 5000);
            }
            function hideMessage() {
                document.getElementById("message").style.display = "none";

            }
            $(document).ready(function() {
                var cm = $("#communicationMethod").val();
                if (cm === "T" || cm === "text") {
                    $("#email").prop("disabled", true);
                    $("#ivr").prop("disabled", true);
                    $('#chkPhoneNo').attr('checked', true);
                } else if (cm === "E" || cm === "email") {
                    $("#email").prop("disabled", false);
                    $("#phoneNo").prop("disabled", true);
                    $("#ivr").prop("disabled", true);
                } else if (cm === "I") {
                    $("#ivr").prop("disabled", false);
                    $("#phoneNo").prop("disabled", true);
                    $("#email").prop("disabled", true);
                } else {
                    $('#chkPhoneNo').attr('checked', true);
                }
            });
            function allowOnlyNumber(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }
        </script>
    </body>
</html>
