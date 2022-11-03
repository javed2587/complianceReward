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
                    <c:set var="disabledEditFlag" value="${users.userId != null &&  users.userId != 0 ?'true':'false'}"/>
                    <c:if test="${users.userId eq null || users.userId eq 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Users <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add User
                    </c:if>
                    <c:if test="${users.userId != null &&  users.userId != 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Users <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit User
                    </c:if>
                </div>
                <div class="heading">
                    <c:if test="${users.userId eq null || users.userId eq 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add User</h1>
                    </c:if>
                    <c:if test="${users.userId != null &&  users.userId != 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit User</h1>
                    </c:if>
                </div> <!-- /header -->


                <form:form action="${pageContext.request.contextPath}/user/add" commandName="users" method="post" prependId="false" onsubmit="return validateEmail()">
                    <form:hidden path="userId" />
                    <form:hidden path="userPassword" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />
                    <form:hidden path="isAdmin" value="true"/>
                    <c:if test="${disabledEditFlag}">
                        <form:hidden path="pharmacyId" />
                    </c:if>
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/user/list'"><a href="${pageContext.request.contextPath}/user/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#users').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px;padding-bottom: 20px;padding-top:15px;background:#F7F7F7;">


                            <div class="form-group margin-ive fgPL-zero">    
                                <div class="col-sm-3">
                                    <label>First Name: <span style="color:red">*</span></label>
                                    <form:input id="fName" path="firstName" cssClass="form-control"/>
                                    <form:errors path="firstName" cssClass="errorMessageValid" id="fnameerror"/>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Last Name: <span style="color:red">*</span></label>
                                    <form:input id="lname" path="lastName" cssClass="form-control"/> 
                                    <form:errors path="lastName" cssClass="errorMessageValid" id="lnameerror"/>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Tel (Off) #:</label>
                                    <form:input path="telNo" cssClass="form-control" id="telno" maxlength="10"/>
                                    <c:if test="${not empty TelValidmessage}"><div class="errorMessageValid1">${TelValidmessage}</div></c:if>
                                    <form:errors path="telNo" cssClass="errorMessageValid" />
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Cell #:</label>
                                    <form:input path="cellNo" cssClass="form-control" id="phone" maxlength="10" />
                                    <c:if test="${not empty cellValidmessage}"><div class="errorMessageValid1">${cellValidmessage}</div></c:if>
                                    <form:errors path="cellNo" cssClass="errorMessageValid" />
                                </div>
                            </div>
                            <div style="clear:both;"></div>
                            <div class="form-group fgPL-zero" style="position: relative; top: 15px;">
                                <div class="col-sm-3">
                                    <label>Email: <span style="color:red">*</span></label>
                                    <form:input id="email" path="emailAddress" cssClass="form-control" onchange="return validateEmail()"/>
                                    <span id="myspan1" class="errorMessage1" style="display: none;">Invalid Email address</span>
                                    <form:errors path="emailAddress" cssClass="errorMessageValid" id="emailerror"/>
                                </div>
                            </div>

                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>User Start Date: <span style="color:red">*</span></label>
                                    <form:input path="userStartDate" id="datetimepicker" cssClass="form-control" placeholder="mm/dd/yyyy" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"/>
                                    <form:errors path="userStartDate" cssClass="errorMessageValid" id="dateerror"/>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>User Name: <span style="color:red">*</span></label>
                                    <form:input path="userName" cssClass="form-control" onkeypress="return IsAlphaNumeric(event);" id="uname" onchange="validateLength(this)"/>
                                    <span id="myspan" class="errorMessage1"><c:if test="${not empty message2}">${message2}</c:if></span>
                                    <form:errors path="userName" cssClass="errorMessageValid" id="unameerror"/>
                                </div>
                            </div>
                            <div class="form-group fgPL-zero">
                                <div class="col-sm-3">
                                    <label>Assign Role: <span style="color:red">*</span></label>
                                    <form:select multiple="false" path="roleTypes.roleId" class="form-control selectpicker show-tick">  
                                        <form:option value="0">Select One</form:option>  
                                        <form:options items="${roles}" itemValue="roleId" itemLabel="roleTitle" />
                                    </form:select>
                                    <c:if test="${not empty message1}"><div class="errorMessageValid1" id="roleerror">${message1}</div></c:if>
                                    </div>
                                </div>
                                <div style="clear:both;"></div>
                                <div class="form-group fgPL-zero" style="position: relative; top: 10px;">
                                    <div class="col-sm-3">
                                        <label>Status:</label><br>
                                    <form:radiobutton path="isActive" value="Yes"/>&nbsp;Active&nbsp;&nbsp;&nbsp;<form:radiobutton path="isActive" value="No"/>&nbsp;InActive</td>
                                </div>
                            </div>

                            <div class="form-group fgPL-zero" style="display:none;">
                                <div class="col-sm-3">
                                    <label>User Type:</label><br>
                                    <form:radiobutton path="isAdmin" value="true"  disabled="${disabledEditFlag}" />&nbsp;Admin
                                </div>
                            </div>
                            <div class="form-group fgPL-zero" style="display:none;">
                                <div class="col-sm-3" style="display:none;">
                                    <label>Assign Pharmacy:</label><br>
                                    <form:select multiple="false" path="pharmacyId" class="form-control selectpicker show-tick" disabled="${disabledEditFlag}">  
                                        <form:option value="0">Select One</form:option>
                                        <form:options items="${pharmaciesList}" itemValue="id" itemLabel="title" />
                                    </form:select>
                                    <span id="pharmacyErrorMessage" class="errorMessage1"><c:if test="${not empty pharmacyErrorMessage}">${pharmacyErrorMessage}</c:if></span>
                                    </div>
                                </div>
                                <div class="form-group" style="display:none;">
                                    <div class="col-sm-3">       
                                    </div>
                                </div>


                            </div>
                        </div>
                </form:form>
                <script type="text/javascript">
                    $('#datetimepicker').datetimepicker({timepicker: false, format: 'm/d/Y',
                        onChangeDateTime: function (dp, $input) {
                            jQuery('#datetimepicker').datetimepicker('hide');
                        }});
                </script>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

        <script>
            jQuery(document).ready(function ($) {
                $("#phone").mask("999-999-9999");
                $("#telno").mask("999-999-9999");
                // $("#datetimepicker").mask("99/99/9999");
            });
            function validateEmail() {
                var res = true;
                document.getElementById("myspan1").style.display = "none";

                if (document.getElementById("email").value.trim() != "") {
                    var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
                    if (!pattern.test(document.getElementById("email").value)) {
                        document.getElementById("myspan1").style.display = "block";
                        res = false;
                    }
                }
                return res;
            }
            $(function () {
                $('#fName,#lname').keydown(function (e) {
                    if (e.altKey) {
                        e.preventDefault();
                    } else {
                        var key = e.keyCode;
                        if (!((key == 8) || (key == 32) || (key == 9) || (key == 13) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
                            e.preventDefault();
                        }
                    }
                });
            });
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
                document.getElementById("myspan1").style.display = "none";
            }

            function startTimer() {
                window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
            }
            function validateLength(inputtxt) {
                if (inputtxt.value.trim() != "" && inputtxt.value.length < 5) {
                    document.getElementById("myspan").style.display = "block";
                    document.getElementById("myspan").innerHTML = "Minimum length 5 characters";
                    return false;
                } else {
                    document.getElementById("myspan").style.display = "none";
                    return true;
                }
            }
            function addSlashes(input) {
                var v = input.value;
                if (v.match(/^\d{2}$/) !== null) {
                    input.value = v + '/';
                } else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
                    input.value = v + '/';
                }
            }
            function enablePharmacy(value) {
                if (value) {
                    $("#pharmacyId").attr("disabled", "disabled");
                    $("#pharmacyId").selectpicker("refresh");
                } else {
                    $("#pharmacyId").removeAttr("disabled");
                    $("#pharmacyId").selectpicker("refresh");
                }
            }
        </script>
    </body>
</html>
