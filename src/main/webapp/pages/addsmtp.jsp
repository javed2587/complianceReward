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
                    <c:if test="${smtpServerInfo.smtpId == null || smtpServerInfo.smtpId == ''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage SMTP <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add SMTP
                    </c:if>
                    <c:if test="${smtpServerInfo.smtpId != null &&  smtpServerInfo.smtpId != ''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage SMTP <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit SMTP
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${smtpServerInfo.smtpId == null || smtpServerInfo.smtpId == ''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add SMTP</h1>
                    </c:if>
                    <c:if test="${smtpServerInfo.smtpId != null &&  smtpServerInfo.smtpId != ''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit SMTP</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/smtp/add" commandName="smtpServerInfo" onsubmit="return validatePassword()" autocomplete="off">
                    <form:hidden path="smtpId"/>
                    <form:hidden path="isActive"/>
                    <form:hidden path="isDelete"/>
                    <form:hidden path="createdBy"/>
                    <form:hidden path="createdOn"/>

                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/smtp/list'"><a href="${pageContext.request.contextPath}/smtp/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#smtpServerInfo').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid addFormBackground" style="height: auto; padding-bottom: 10px;padding-top:10px;background:#f7f7f7;margin-bottom:40px;">
                            
                            <div class="col-sm-12 col-xs-12 col-lg-12 col-md-12" style="background:#f7f7f7;overflow:hidden;padding-left:0px; padding-right:0px;">
                                 
                                <div class="form-group">
                                    <div class="col-sm-3 pad-left-320" style="padding-right: 0px;">
                                        <label> Email:<span style="color:red">*</span></label>
                                        <form:input id="fromEmail" path="fromEmail" cssClass="form-control2" onchange="return validatePassword()" />
                                        <span id="myspan2" class="errorMessage1 smtpline" style="display: none;">Invalid Email address</span>
                                        <form:errors path="fromEmail" style="line-height:15px;" cssClass="errorMessageValid" />
                                    </div>
                                </div>
                                <div class="form-group" style="position: relative;top:-15px;">
                                    <div class="col-sm-3" style="padding-right: 0px;padding-left:0px;">
                                        <label> From Name:<span style="color:red">*</span></label>
                                        <form:input id="fName" path="fromName" cssClass="form-control2" onkeypress="return IsAlphabetTab(event)" /> 
                                        <form:errors path="fromName" cssClass="errorMessageValid" />
                                    </div>
                                </div>
                                <div class="form-group" style="position: relative;top:-15px;">
                                    <div class="col-sm-3" style="padding-right: 0px;padding-left:0px;">
                                        <label> SMTP Server:<span style="color:red">*</span></label> 
                                        <form:input path="outGoingSmtp" cssClass="form-control2" onkeyup="validateAlpha()"/>
                                        <form:errors path="outGoingSmtp" cssClass="errorMessageValid" />
                                    </div>  
                                </div>
                                <div class="form-group" style="position: relative;top:-15px;">
                                    <div class="col-sm-3" style="padding-left:0px;padding-right:0px;">
                                        <label>SMTP Port:<span style="color:red">*</span></label>
                                        <form:input path="smtpPort" cssClass="form-control2" maxlength="10" onkeypress="return isNumber(event);" title="This must be a +iv number" />
                                        <form:errors path="smtpPort" cssClass="errorMessageValid" />
                                    </div>
                                </div>
                           




                            <div class="form-group"> 
                                <div class="col-sm-3 pad-left-320" style="padding-right: 0px;">
                                    <label>User Name:<span style="color:red">*</span></label>
                                    <form:input id="smtpUserName" path="smtpUserName" cssClass="form-control2" onkeypress="return IsAlphaNumeric(event);" onchange="return validatePassword()" />
                                    <span id="myspan1" class="errorMessage1" style="display: none;">Minimum length 5 characters</span>
                                    <form:errors path="smtpUserName" cssClass="errorMessageValid" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-3"  style="padding-right: 0px;padding-left:0px;">
                                    <label>  Password:<span style="color:red">*</span></label>
                                    <form:input id="password" path="smtpPassword" cssClass="form-control2" onchange="return validatePassword()" maxlength="15"/>
                                    <span id="myspan" class="errorMessage1" style="display: none;">Password cannot less than 8 characters</span>
                                    <form:errors path="smtpPassword" cssClass="errorMessageValid" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-3">
                                </div>
                            </div>  
                            <div class="form-group">
                                <div class="col-sm-3">
                                </div>
                            </div>
                        </div>
                                 </div>
                    </div>
                </form:form>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

        <script type="text/javascript">
            function isNumber(evt) {
                evt = (evt) ? evt : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }
            function validatePassword()
            {
                var res = true;
                document.getElementById("myspan1").style.display = "none";
                document.getElementById("myspan").style.display = "none";
                document.getElementById("myspan2").style.display = "none";
                var passw1 = /^(?=.*[0-9])(?=.*[!@#$%^._&*])[a-zA-Z0-9!@#$%^&._*]{8,25}$/;
                if (document.getElementById("fromEmail").value.trim() != "") {
                    var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
                    if (!pattern.test(document.getElementById("fromEmail").value)) {
                        document.getElementById("myspan2").style.display = "block";
                        res = false;
                    }
                }
                if (document.getElementById("password").value.trim() != "" && document.getElementById("password").value.trim().length < 8)
                {
                    document.getElementById("myspan").style.display = "block";
                    if (document.getElementById("myspan").innerHTML = "Password should contain at least one digit and one special character") {
                        document.getElementById("myspan").innerHTML = "Password cannot less than 8 characters";
                    }

                    res = false;
                }
                if (document.getElementById("smtpUserName").value.trim() != "" && document.getElementById("smtpUserName").value.length < 5) {
                    document.getElementById("myspan1").style.display = "block";
                    res = false;
                }
                if (document.getElementById("password").value.trim() != null && document.getElementById("password").value.trim() != "" && document.getElementById("password").value.length >= 8) {
                    var result2 = document.getElementById("password").value.match(passw1);
                    if (result2 == null) {
                        document.getElementById("myspan").style.display = "block";
                        document.getElementById("myspan").innerHTML = "Password should contain at least one digit and one special character";
                        res = false;
                    }
                }
                return res;
            }
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
                document.getElementById("myspan1").style.display = "none";
                document.getElementById("myspan2").style.display = "none";
            }

            function startTimer() {
                window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
            }
            var specialKeys = new Array();
            specialKeys.push(36); //Home
            specialKeys.push(35); //End
            specialKeys.push(37); //Left
            specialKeys.push(39); //Right
            function IsAlphabetTab(e) {
                var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                var ret = ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                        || keyCode == 32 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

                return ret;
            }
            $('Input').bind("paste", function(e) {
                e.preventDefault();
            });
            function doKeyUpValidation(text) {
                return text.value = text.value.replace(/[^\w.-]+/g, "");
            }
            function validateAlpha() {
                var textInput = document.getElementById("outGoingSmtp").value;
                textInput = textInput.replace(/[^A-Za-z.-]/g, "");
                document.getElementById("outGoingSmtp").value = textInput;
            }
        </script>
    </body>
</html>

