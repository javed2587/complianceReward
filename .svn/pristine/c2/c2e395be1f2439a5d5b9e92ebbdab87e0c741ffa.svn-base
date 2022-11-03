<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>



        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <div class="wrp clearfix pOrderHeader" style="padding-left: 15px; padding-right: 15px; color: black; font-size: 12px;">
                    <img src="${pageContext.request.contextPath}/resources/images/API_Logo.jpg">&nbsp;&nbsp;<img class="hidden-xs" src="${pageContext.request.contextPath}/resources/images/bar.png" />&nbsp;&nbsp;</a> <br /> <span style="color:#2071b6;font-family:arial;font-size: 18px;"><span class="hidden-xs">Welcome to API</span> CoPay Assistance Program</span>  
                    <br />
                    <div style="padding-bottom:7px;">
                        The official storefront is created to provide an economical option for patients whose prescribed compounds contain genuine American Pharmaceutical Ingredients product. <br />
                    </div>
                    <form:form action="${pageContext.request.contextPath}/order/summary" commandName="order" method="post" role="form" data-toggle="validator">    
                        <form:hidden path="type" value="orderreview" />
                        <form:hidden path="transactionNo" value="${transactionNumber}"/>
                        <form:hidden path="orderStatus.id" value="1"/>
                        <form:hidden path="orderHistory[0].comments" value="Order Placed!"/>
                        <div class="row grid" style="height: auto;border: 0px;margin-bottom:0px;">
                            <div class="search-grid demo-show2 accordionnn">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background" style="display: block;border-left: 1px solid #c6c6c6; border-right: 1px solid #c6c6c6;border-top: 1px solid #c6c6c6;border-bottom: 1px solid #c6c6c6;margin-bottom: 5px;margin-top: -4px;padding-right: 8px;" id="panel${loop.index}"> 

                                    <div class="full-width overflow-auto pull-left"> 
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">  
                                            <div class="form-group" style="margin-top: 10px; padding:10px 14px 5px 14px; background: white;border:1px solid #c6c6c6;float: left;width:100%;border-radius:2px;"> 
                                                <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12" style="font-size:12px;"> 
                                                    <img src="${pageContext.request.contextPath}/resources/images/pills.jpg" height="58" style="padding-right:10px;" align="left" /> 
                                                    <b style="color:#2071b6;">VIP CoPay Offer for Rx</b> <br />

                                                    <b>Save $ <fmt:formatNumber pattern="0.00" value="${order.copay}"/> now</b> <br />
                                                </div>
                                            </div>

                                            <div class="form-group" style="padding:10px 0px; background: white;border:1px solid #c6c6c6;float: left;width:100%;border-radius:2px;font-size:12px;"> 
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 orderHeadings">
                                                    Shipping Address
                                                </div>

                                                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                                                    <form:input maxlength="50" path="firstName" cssClass="form-control" placeholder="First Name" required="true"/> 
                                                    <form:errors path="firstName" />
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-6 col-xs-12">
                                                    <form:input maxlength="50" path="lastName" cssClass="form-control" placeholder="Last Name" required="true"/> 
                                                    <form:errors path="lastName" />
                                                </div>

                                                <br /> <br /> <br />

                                                <div class="row shipAddress">
                                                    <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input maxlength="150" path="streetAddress" cssClass="form-control" placeholder="Adress line 1" required="true"/> 
                                                    </div>
                                                    <div class="col-lg-1 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input maxlength="150" path="addressLine2" cssClass="form-control" placeholder="Adress line 2" /> 
                                                    </div>
                                                    <div class="col-lg-1 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input maxlength="50" path="city" cssClass="form-control" placeholder="City" required="true"/> 
                                                    </div>
                                                    <div class="col-lg-1 col-md-2 col-sm-6 col-xs-12">
                                                        <form:select cssClass="form-control" path="state">  
                                                            <form:option value="AL">AL</form:option> 
                                                            <form:option value="AK">AK</form:option> 
                                                            <form:option value="AZ">AZ</form:option> 
                                                            <form:option value="AR">AR</form:option> 
                                                            <form:option value="CA">CA</form:option> 
                                                            <form:option value="CO">CO</form:option> 
                                                            <form:option value="CT">CT</form:option> 
                                                            <form:option value="DE">DE</form:option> 
                                                            <form:option value="DC">DC</form:option> 
                                                            <form:option value="FL">FL</form:option> 
                                                            <form:option value="GA">GA</form:option> 
                                                            <form:option value="HI">HI</form:option> 
                                                            <form:option value="ID">ID</form:option> 
                                                            <form:option value="IL">IL</form:option> 
                                                            <form:option value="IN">IN</form:option> 
                                                            <form:option value="IA">IA</form:option> 
                                                            <form:option value="KS">KS</form:option> 
                                                            <form:option value="KY">KY</form:option> 
                                                            <form:option value="LA">LA</form:option> 
                                                            <form:option value="ME">ME</form:option> 
                                                            <form:option value="MD">MD</form:option> 
                                                            <form:option value="MA">MA</form:option> 
                                                            <form:option value="MI">MI</form:option> 
                                                            <form:option value="MN">MN</form:option> 
                                                            <form:option value="MS">MS</form:option> 
                                                            <form:option value="MO">MO</form:option> 
                                                            <form:option value="MT">MT</form:option> 
                                                            <form:option value="NE">NE</form:option> 
                                                            <form:option value="NV">NV</form:option> 
                                                            <form:option value="NH">NH</form:option> 
                                                            <form:option value="NJ">NJ</form:option> 
                                                            <form:option value="NM">NM</form:option> 
                                                            <form:option value="NY">NY</form:option> 
                                                            <form:option value="NC">NC</form:option> 
                                                            <form:option value="ND">ND</form:option> 
                                                            <form:option value="OH">OH</form:option> 
                                                            <form:option value="OK">OK</form:option> 
                                                            <form:option value="OR">OR</form:option> 
                                                            <form:option value="PA">PA</form:option> 
                                                            <form:option value="RI">RI</form:option> 
                                                            <form:option value="SC">SC</form:option> 
                                                            <form:option value="SD">SD</form:option> 
                                                            <form:option value="TN">TN</form:option> 
                                                            <form:option value="TX">TX</form:option> 
                                                            <form:option value="UT">UT</form:option> 
                                                            <form:option value="VT">VT</form:option> 
                                                            <form:option value="VA">VA</form:option> 
                                                            <form:option value="WA">WA</form:option> 
                                                            <form:option value="WV">WV</form:option> 
                                                            <form:option value="WI">WI</form:option> 
                                                            <form:option value="WY">WY</form:option> 
                                                        </form:select>  
                                                    </div>
                                                    <div class="col-lg-1 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input path="zip" cssClass="form-control" placeholder="Zip" required="true" maxlength="5" onkeypress="return IsDigit(event,this);"/> 
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="form-group" style="margin-bottom:0px;padding:10px 0px; background: white;border:1px solid #c6c6c6;float: left;width:100%;border-radius:2px;"> 

                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 orderHeadings">
                                                    Payment Information
                                                </div>
                                                <div class="row payAddress">
                                                    <div class="col-lg-.5 col-md-2 col-sm-6 col-xs-12">
                                                        <form:select cssClass="form-control" path="cardType">  
                                                            <form:option value="AMEX">AMEX</form:option> 
                                                            <form:option value="DISCOVER">DISCOVER</form:option>
                                                            <form:option value="MASTERCARD">MASTERCARD</form:option> 
                                                            <form:option value="VISA">VISA</form:option> 
                                                        </form:select>  
                                                    </div>
                                                    <div class="col-lg-.5 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input maxlength="19" path="cardNumber" cssClass="form-control" placeholder="Card Number" required="true" onkeypress="return IsDigit(event)"/>
                                                    </div>
                                                    <div class="col-lg-.5 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input maxlength="26" path="cardHoderName" cssClass="form-control" placeholder="Card Holder Name"  required="true" onkeypress="return IsAlphaNumeric(event)"/>
                                                    </div>
                                                    <div class="col-lg-.5 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input maxlength="7" id="datetimepicker" path="cardExpiry" cssClass="form-control" placeholder="Card Expiry" required="true"/>
                                                    </div>
                                                    <div class="col-lg-.5 col-md-2 col-sm-6 col-xs-12">
                                                        <form:input maxlength="4" path="cardCvv" min="100" cssClass="form-control" placeholder="CVV Number" required="true" onkeypress="return IsDigit(event)"/>
                                                    </div>

                                                    <div class="col-lg-.5 col-md-2 col-sm-6 col-xs-6 orderpbtn" style="font-size:12px;float:right;">
                                                        <button type="submit" class="btndrop pull-right" style="margin-top:1px; background-color: #145994; color: #FFFFFF;">Continue</button> 
                                                    </div>
                                                   
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-6 margin-top-5">
                                                        <!--<b>We Accept:</b>--> <img src="${pageContext.request.contextPath}/resources/images/cards.jpg" style="padding-right:10px;" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style="margin: 0px;font-size: 8px; margin-bottom: 15px;">
                            <img src="${pageContext.request.contextPath}/resources/images/norton.jpg" style="height: 35px;padding-right: 10px;line-height: 8px;" align="left" />You are now connected using a secure (SSL) connection and all information is transmitted in an encrypted form. You should see a small lock (Internet Explorer, Firefox, Chrome, Opera, Safari) indication that your browser is communicating securely with our web store.

                            <br />
                            <div style="color:#92BCDF; font-weight: bold;">
                                <a href="http://api-direct.com/terms" style="color:#2679C0" target="_blank">Terms & Conditions</a>  |  <a href="http://api-direct.com/privacy" style="color:#2679C0" target="_blank">Privacy Policy</a>  |  <a href="http://api-direct.com/esp" style="color:#2679C0" target="_blank">Espanol?</a>  |   <a href="http://api-direct.com/return" style="color:#2679C0" target="_blank">Return Policy</a> <br /><br />
                            </div>
                        </div>
                    </form:form>


                </div>
            </div> <!-- /content -->

        </div> <!-- /wrapper -->
        <script type="text/javascript">
            $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                format: 'm/Y',
                onChangeDateTime: function(dp, $input) {
                    jQuery('#datetimepicker').datetimepicker('hide');
                    jQuery('#datetimepicker1').datetimepicker('hide');
                }

            });

            var specialKeys = new Array();
            //specialKeys.push(8); //Backspace
            //specialKeys.push(9); //Tab
            specialKeys.push(46); //Delete
            specialKeys.push(36); //Home
            specialKeys.push(35); //End
            specialKeys.push(37); //Left
            specialKeys.push(39); //Right
            function IsAlphaNumeric(e) {
                var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                        || keyCode == 32 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

                return ret;
            }
            function IsDigit(e) {
                var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                        || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

                return ret;
            }

            function onlyAlphabets(e, t) {
                try {
                    if (window.event) {
                        var charCode = window.event.keyCode;
                    }
                    else if (e) {
                        var charCode = e.which;
                    }
                    else {
                        return true;
                    }
                    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode === 32 || charCode === 8)
                        return true;
                    else
                        return false;
                }
                catch (err) {
                    alert(err.Description);
                }
            }
            $('Input').bind("paste", function(e) {
                e.preventDefault();
            });
        </script>
    </body>
</html>
