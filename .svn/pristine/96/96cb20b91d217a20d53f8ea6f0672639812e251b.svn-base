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

            <form:form action="${pageContext.request.contextPath}/patient/uploadcard" commandName="patientProfile" method="post" onsubmit="return validateField();">
                <form:hidden id="expiryDate" path="paymentInfo.expiryDate"/>
                <form:hidden path="communicationID"/>
                <div id="errorMessage" class="row" style="display: ${not empty errorMessage ? 'block;' : 'none;'}">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 errorMessage" style="margin-bottom: 0;">
                        ${errorMessage}
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 fontcolor heading-font-class">
                        <b>Payment Information</b> 
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 font-class">
                        <p>
                            We Accept: <img src="${pageContext.request.contextPath}/resources/images/cards.jpg"/>
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0; padding-right: 0;">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 verticalSpacing">
                            <form:select id="cardType" cssClass="form-control" path="paymentInfo.cardType">
                                <form:option value="0" label="Card Type" />
                                <form:option value="AMEX">AMEX</form:option> 
                                <form:option value="DISCOVER">DISCOVER</form:option>
                                <form:option value="MASTERCARD">MASTERCARD</form:option> 
                                <form:option value="VISA">VISA</form:option> 
                            </form:select>
                            <div id="cardTypereq" class="errorMessage"></div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 verticalSpacing">
                            <form:input id="cardNumber" path="paymentInfo.cardNumber" cssClass="font-class form-control" placeholder="Credit Card Number" maxlength="19" onkeypress="return IsDigit(event)"/>
                            <div id="cardNumberreq" class="errorMessage"></div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 verticalSpacing">
                            <form:input id="cardHolderName" path="paymentInfo.cardHolderName" cssClass="font-class form-control" placeholder="Cardholder's Name" maxlength="26" onkeypress="return IsAlphaNumeric(event)"/>
                            <div id="cardHolderNamereq" class="errorMessage"></div>
                        </div>    
                    </div>    
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0; padding-right: 0;">
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 verticalSpacing">
                            <form:select id="expiryMonth" path="paymentInfo.expiryMonth" cssClass="font-class form-control selectpicker show-tick">
                                <form:option value="0" label="Expiry Month" />
                                <form:option value="01" label="Jan" />
                                <form:option value="02" label="Feb" />
                                <form:option value="03" label="Mar" />
                                <form:option value="04" label="Apr" />
                                <form:option value="05" label="May" />
                                <form:option value="06" label="Jun" />
                                <form:option value="07" label="Jul" />
                                <form:option value="08" label="Aug" />
                                <form:option value="09" label="Sep" />
                                <form:option value="10" label="Oct" />
                                <form:option value="11" label="Nov" />
                                <form:option value="12" label="Dec" />
                            </form:select>
                            <div id="expiryMonthreq" class="errorMessage"></div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 verticalSpacing">
                            <form:select id="expiryYear" path="paymentInfo.expiryYear" cssClass="font-class form-control" onchange="concatExpiryDate(this.value)">
                                <form:option value="0" label="Expiry Year" />
                            </form:select>
                            <div id="expiryYearreq" class="errorMessage"></div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 verticalSpacing">
                            <form:input id="ccvNumber" path="paymentInfo.ccvNumber" cssClass="font-class form-control" placeholder="CVV Number" maxlength="4" onkeypress="return IsDigit(event)"/>
                            <div id="ccvNumberreq" class="errorMessage"></div>
                        </div> 
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 fontcolor heading-font-class">
                        <b>Billing Information</b>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left:0">
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 font-class">
                            <form:checkbox id="sameBillingInfoCkb" path="paymentInfo.sameAddress" value="Yes" style="margin-right: 5px; color: rgba(5, 4, 4, 1);" checked="checked"/>
                            Same as Shipping Address
                            <div id="sameBillingInfoCkbreq" class="errorMessage"></div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 font-class verticalSpacing">
                            <a id="addBillingAddressControl" onclick="showBillingAddress();" href="#" style="text-decoration: none; color: rgba(5, 4, 4, 1);"><i class="fa fa-plus"></i> Add Billing Address</a>
                        </div>
                    </div>
                </div>
                <div class="row" style="display: none;" id="billingAddressDiv">
                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12 verticalSpacing">
                        <form:input id="address" class="font-class form-control" path="billingAddress.address" placeholder="Address" maxlength="150"/>
                        <div id="addressreq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 col-md-5 col-sm-5 col-xs-5 verticalSpacing">
                        <form:input id="city" class="font-class form-control" path="billingAddress.city" placeholder="City" maxlength="50"/>
                        <div id="cityreq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 verticalSpacing">
                        <form:select id="state" path="billingAddress.state.id" cssClass="form-control selectpicker show-tick font-class">
                            <form:option value="0" label="State" />
                            <form:options items="${states}" itemLabel="abbr" itemValue="id"/>
                        </form:select>
                        <div id="statereq" class="errorMessage"></div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-4 verticalSpacing">
                        <form:input id="zip" class="font-class form-control" path="billingAddress.zip" placeholder="Zip" maxlength="5" onkeypress="return IsDigit(event,this);"/>
                        <div id="zipreq" class="errorMessage"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pull-fontsize">
                        <img src="${pageContext.request.contextPath}/resources/images/norton.jpg" align="left" style="float: left;"/>
                        <span class="disclaimer-font-class disclaimer-position">You are now connected using a secure (SSL) connection and all information is transmitted in an encrypted form.
                            You should see a small lock (Internet Explorer, Firefox, Chrome, Opera, Safari) indication that your browser is communicating securely with our web store.</span>
                    </div>
                </div>
                <div class="row pull-top" style="clear: both;">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 15px;">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0;">
                            <input id="disclaimerCkb" type="checkbox" aria-label="..." style="float: left; margin-right: 5px;">
                            <span class="fontcolor1">Disclaimer & Permission to appeal coverage decisions</span>
                        </div>
                        <div id="disclaimerReq" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 errorMessage" style="padding-left: 0; padding-bottom: 15px;">

                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 disclaimer-font-class" style="padding-left: 15px; clear: both;">
                        By clicking here - I understand that the GenRx fulfillment offer of $0 per 30 days supply applies only to those patients who have valid, commercial prescription drug insurance. All other forms of prescription insurance (e.g. provided by public or military) or uninsured cash payers is provided via mail order at a $25 per Rx per month. Further I acknowledge and permit CmpdRx to use elements of either my prescription drug claim or demographic information for the purpose of appealing or petitioning any primary insurer on the patients behalf to secure reimbursement for the prescribed medication as part of the GenRx access program, in compliance with the current policies of the patients insurer.
                    </div>
                </div>
                <div class="row pull-top">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="">
                        <b class="font-class" style="font-weight: normal; color: red;">
                            Page 2 of 3
                        </b>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="text-align: right;">
                        <a class="font-class" style="text-decoration: underline; color: red;" href="${pageContext.request.contextPath}/patient/registration/${patientProfile.communicationID}"><i class="fa fa-angle-double-left" style="margin-right: 2px;"></i>BACK</a>
                        <button class="btn btn-primary">CONTINUE <i class="fa fa-angle-double-right font-class"></i></button>
                    </div>
                </div>
            </form:form>
        </div> 
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            var expiryDate = $("#expiryDate").val();
            if (expiryDate != null && expiryDate.length > 0) {
                expiryDate = expiryDate.split("/")[1];
            }

            var start = new Date().getFullYear();
            var end = new Date().getFullYear() + 5;
            var options = "";
            options += "<option value='0'>Expiry Year</option>";
            for (var year = start; year <= end; year++) {
                if (expiryDate == year) {
                    options += "<option value=" + year + " selected>" + year + "</option>";
                } else {
                    options += "<option value=" + year + ">" + year + "</option>";
                }
            }
            document.getElementById("expiryYear").innerHTML = options;

            function showBillingAddress() {
                $("#billingAddressDiv").show();
                $("#sameBillingInfoCkb").attr("checked", false);
            }
            $("#sameBillingInfoCkb").on("change", function() {
                if (this.checked) {
                    $("#addBillingAddressControl").unbind('click')
                    $("#billingAddressDiv").hide();
                    $("#billingAddressDiv input").val("");
                    $('#billingAddressDiv select').prop('selectedIndex',0);
                } else {
                    $("#billingAddressDiv").show();
                    $("#addBillingAddressControl").bind('click')
                }
            });
            function validateField() {
                var empty = true;
                var em = document.getElementById("expiryMonth");
                var ey = document.getElementById("expiryYear");
                var es = document.getElementById("state");
                var cType = document.getElementById("cardType");

                if (cType.options[cType.selectedIndex].value == 0) {
                    $("#cardTypereq").text("Required");
                    $("#cardTypereq").show();
                    empty = false;
                }
                if ($("#cardNumber").val().trim() === "") {
                    $("#cardNumberreq").text("Required");
                    $("#cardNumberreq").show();
                    empty = false;
                }
                if ($("#cardHolderName").val().trim() === "") {
                    $("#cardHolderNamereq").text("Required");
                    $("#cardHolderNamereq").show();
                    empty = false;
                }
                if (em.options[em.selectedIndex].value == 0) {
                    $("#expiryMonthreq").text("Required");
                    $("#expiryMonthreq").show();
                    empty = false;
                }
                if (ey.options[ey.selectedIndex].value == 0) {
                    $("#expiryYearreq").text("Required");
                    $("#expiryYearreq").show();
                    empty = false;
                }
                if ($("#ccvNumber").val().trim() === "") {
                    $("#ccvNumberreq").text("Required");
                    $("#ccvNumberreq").show();
                    empty = false;
                }

                if ($("#billingAddressDiv").is(':visible')) {
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
                    if (es.options[es.selectedIndex].value == 0) {
                        $("#statereq").text("Required");
                        $("#statereq").show();
                        empty = false;
                    }
                    if ($("#zip").val().trim() === "") {
                        $("#zipreq").text("Required");
                        $("#zipreq").show();
                        empty = false;
                    }
                }
                if (!$("#disclaimerCkb").is(":checked")) {
                    $("#disclaimerReq").text("Required");
                    $("#disclaimerReq").show();
                    empty = false;
                }

                if (!empty) {
                    timeOut();
                }
                return empty;
            }
            function concatExpiryDate(expiryYear) {
                var em = document.getElementById("expiryMonth");
                if (expiryYear != 0 && em.options[em.selectedIndex].value != 0) {
                    var appendValue = em.options[em.selectedIndex].value + "/" + expiryYear;
                    $("#expiryDate").val(appendValue);
                }
            }
            $(window).load(function(){
                timeOut();
            });
        </script>
    </body>
</html>
