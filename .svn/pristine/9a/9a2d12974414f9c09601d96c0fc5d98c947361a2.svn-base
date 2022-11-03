<%-- 
    Document   : patientinfo
    Created on : Sep 29, 2015, 5:30:49 PM
    Author     : msheraz
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div class="container containerConents">
            <form:form action="${pageContext.request.contextPath}/patient/creditcardinfo" commandName="patientProfile" method="post" onsubmit="return validateField()">
                <form:hidden path="communicationID"/>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <p class="font-class" style="color: rgba(5, 4, 4, 1);">
                            <b class="welcome-font-class" style="color: rgba(21, 101, 172, 1);">Welcome!</b><br/>
                            This is official storefront is created to provide an economical option for commercially insured and cash paying patients to <b style="color: red; font-weight: normal;">obtain genuine API Direct products</b> that have been prescribed by their health care professional.<br/><br style="display: block; margin: 3px 0px;"/>
                            Uninsured and public insurance (e.g. Medicare, Medicaid and Military) are <b style="color: red; font-weight: normal;">eligible to receive API Direct prescriptions</b> for $25 each per 30 day supply. 
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 font-class" style="margin-bottom: 15px;">
                        <a style="text-decoration: underline; color: red;" href="#">HIPPA Statement</a>,
                        <a style="text-decoration: underline; color: red;" href="#">HIPPA Authorization Form</a>
                    </div>
                </div>    
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h5 class="heading-font-class" style="color: rgba(21, 101, 172, 1);">Patient Detail</h5>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="padding-left: 0; padding-right: 0;">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 verticalSpacing">
                            <form:input class="font-class form-control" path="firstName" placeholder="First Name" maxlength="50" onkeypress="return IsAlphaNumeric(event);"/>
                            <div id="firstNamereq" class="errorMessage"></div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 verticalSpacing">
                            <form:input class="font-class form-control" path="lastName" placeholder="Last Name" maxlength="50" onkeypress="return IsAlphaNumeric(event);"/>
                            <div id="lastNamereq" class="errorMessage"></div>
                        </div>    
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="padding-left: 0; padding-right: 0;">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 verticalSpacing">
                            <form:input class="font-class form-control" path="DOB" placeholder="Date of Birth (dd/mm/yy)" id="datetimepicker"/>
                            <div id="datetimepickerreq" class="errorMessage"></div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 verticalSpacing">
                            <div class="col-lg-3 col-md-4 col-sm-3 col-xs-3" style="padding-right: 0;">
                                <form:radiobutton id="maleRadio" path="gender" value="M"/>
                                <label class="font-class" for="maleRadio">Male</label>
                            </div>
                            <div class="col-lg-4 col-md-5 col-sm-3 col-xs-3" style="padding-right: 0;">
                                <form:radiobutton id="femaleRadio" path="gender" value="F"/>
                                <label class="font-class" for="femaleRadio">Female</label>
                            </div>
                        </div>
                    </div>    
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h5 class="heading-font-class" style="color: rgba(21, 101, 172, 1);">Primary / Responsible Party Contact</h5>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 verticalSpacing">
                        <form:input class="font-class form-control" path="primaryContactFirstName" placeholder="First Name" maxlength="50" onkeypress="return IsAlphaNumeric(event);"/>
                        <div id="primaryContactFirstNamereq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 verticalSpacing">
                        <form:input class="font-class form-control" path="primaryContactLastName" placeholder="Last Name" maxlength="50" onkeypress="return IsAlphaNumeric(event);"/>
                        <div id="primaryContactLastNamereq" class="errorMessage"></div>
                    </div>    
                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12" id="primaryEmailDiv">
                        <form:input class="font-class form-control" path="primaryContactEmail" style="display: inline;" placeholder="Email" maxlength="50"/>
                        <div id="primaryContactEmailreq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 hidden-md hidden-sm hidden-xs">

                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h5 class="heading-font-class" style="color: rgba(21, 101, 172, 1);">Shipping Address</h5>
                    </div>
                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12 verticalSpacing">
                        <form:input id="address" class="font-class form-control" path="shippingAddress.address" placeholder="Address" maxlength="150"/>
                        <div id="addressreq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 col-md-5 col-sm-5 col-xs-5 verticalSpacing">
                        <form:input id="city" class="font-class form-control" path="shippingAddress.city" placeholder="City" maxlength="50"/>
                        <div id="cityreq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 verticalSpacing">
                        <form:select id="state" path="shippingAddress.state.id" cssClass="form-control selectpicker show-tick font-class">
                            <form:option value="0" label="State" />
                            <form:options items="${states}" itemLabel="abbr" itemValue="id"/>
                        </form:select>
                        <div id="statereq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-4 verticalSpacing">
                        <form:input id="zip" class="font-class form-control" path="shippingAddress.zip" placeholder="Zip" maxlength="5"/>
                        <div id="zipreq" class="errorMessage"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="">
                        <b class="font-class" style="font-weight: normal; color: red;">
                            Page 1 of 3
                        </b>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="text-align: right;">
                        <button class="btn btn-primary font-class">CONTINUE <i class="fa fa-angle-double-right font-class"></i></button>
                    </div>
                </div>
            </form:form>
        </div>
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            $('#datetimepicker').datetimepicker({timepicker: false,
                format: 'm/d/Y',
                onChangeDateTime: function () {
                    jQuery('#datetimepicker').datetimepicker('hide');
                }

            });

            function validateField() {
                var empty = true;
                var e = document.getElementById("state");
                if ($("#firstName").val().trim() === "") {
                    $("#firstNamereq").text("Required");
                    $("#firstNamereq").show();
                    empty = false;
                }
                if ($("#lastName").val().trim() === "") {
                    $("#lastNamereq").text("Required");
                    $("#lastNamereq").show();
                    empty = false;
                }
                if ($("#datetimepicker").val().trim() === "") {
                    $("#datetimepickerreq").text("Required");
                    $("#datetimepickerreq").show();
                    empty = false;
                } 
                if ($("#primaryContactFirstName").val().trim() === "") {
                    $("#primaryContactFirstNamereq").text("Required");
                    $("#primaryContactFirstNamereq").show();
                    empty = false;
                }
                if ($("#primaryContactLastName").val().trim() === "") {
                    $("#primaryContactLastNamereq").text("Required");
                    $("#primaryContactLastNamereq").show();
                    empty = false;
                }
                if ($("#primaryContactEmail").val().trim() === "") {
                    $("#primaryContactEmailreq").text("Required");
                    $("#primaryContactEmailreq").show();
                    empty = false;
                } else {
                    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
                    if (!filter.test($("#primaryContactEmail").val().trim())) {
                        $("#primaryContactEmailreq").text("Invalid Email");
                        $("#primaryContactEmailreq").show();
                        empty = false;
                    }
                }
                if ($("#address").val().trim() === "") {
                    $("#addressreq").text("Required");
                    $("#addressreq").show();
                    empty = false;
                }
                if ($("#city").val().trim() === "") {
                    $("#cityreq").text("Required");
                    $("#cityreq").show();
                    empty = false;
                }
                if (e.options[e.selectedIndex].value == 0) {
                    $("#statereq").text("Required");
                    $("#statereq").show();
                    empty = false;
                }
                if ($("#zip").val().trim() === "") {
                    $("#zipreq").text("Required");
                    $("#zipreq").show();
                    empty = false;
                }
                if (!empty) {
                    timeOut();// fade out error messages
                }

                return empty;
            }
        </script>
    </body>
</html>
