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
                    <img src="${pageContext.request.contextPath}/resources/images/API_Logo.jpg">&nbsp;&nbsp;<img class="hidden-xs" src="${pageContext.request.contextPath}/resources/images/bar.png" />&nbsp;&nbsp;</a> <br /> <span style="color:#2071b6;font-family:arial;font-size: 18px;">API CoPay Assistance Program - Summary</span>  
                    <br />
                    <b>Thank you for your order.</b> We will be contacting your prescribing physician to obtain the prescription.  Allow 7-10 days for delivery  maximum.<br />
                    <form:form action="${pageContext.request.contextPath}/order/summary" commandName="order" method="post" role="form" data-toggle="validator">    


                        <div class="errorMessage" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            
                        

                    <form:hidden path="type" value="placeorder" />
                    <form:hidden path="firstName"/>
                    <form:hidden path="lastName"/>
                    <form:hidden path="streetAddress"/>
                    <form:hidden path="addressLine2"/>
                    <form:hidden path="state"/>
                    <form:hidden path="zip"/>
                    <form:hidden path="city"/>
                    <form:hidden path="cardNumber"/>
                    <form:hidden path="cardHoderName"/>
                    <form:hidden path="cardExpiry"/>
                    <form:hidden path="cardCvv"/>
                    <form:hidden path="cardType"/>
                    <form:hidden path="transactionNo"/>
                    <form:hidden path="orderStatus.id" value="1"/>
                    <form:hidden path="orderHistory[0].comments" value="Order Placed!"/>

                    <div class="row grid" style="height: auto;border: 0px;padding-top: 5px;">
                        <div class="search-grid demo-show2 accordionnn">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background" style="display: block;border: 1px solid #c6c6c6;margin-bottom: 20px;margin-top: -4px;padding-right: 8px;padding-bottom: 5px;" id="panel${loop.index}"> 

                                <div class="full-width overflow-auto pull-left"> 
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">  
                                        <div class="form-group" style="margin-top: 10px;margin-bottom: 5px; padding:10px 14px 10px 14px; background: white;border:1px solid #c6c6c6;float: left;width:100%;border-radius:2px;"> 
                                            <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12" style="font-size:12px;"> 
                                                <img src="${pageContext.request.contextPath}/resources/images/pills.jpg" height="58" style="padding-right:10px;" align="left" /> 
                                                <br />
                                                <span style="color:#145994;font-weight: bold;">$ <fmt:formatNumber pattern="0.00" value="${order.copay}"/> of VIP CoPay Assistance Special Offer</span> <br />

                                            </div>

                                        </div>
                                        <i>Please review the information below before submitting.</i>
                                        <br />
                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="margin-top: 5px; padding:10px 14px 10px 14px; background: white;border:1px solid #c6c6c6;float: left;border-radius:2px;font-size:12px;"> 
                                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 orderHeadings padd-left-zero">
                                                Shipping Address
                                            </div>

                                            <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 padd-left-zero">
                                                ${order.firstName} ${order.lastName} <br />
                                                ${order.streetAddress} ${order.addressLine2}, ${order.city}, ${order.state},${order.zip} <br /><br /><br />
                                            </div>
                                        </div>

                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="margin-top: 5px; padding:10px 14px 10px 14px; background: white;border:1px solid #c6c6c6;float: left;border-radius:2px;font-size:11px;"> 

                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 orderHeadings padd-left-zero">
                                                Order Detail
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padd-left-zero">
                                                    Prescription#:      ${prescriptionNumber}
                                                </div>
                                                <div class="col-lg-4 col-md-5 col-sm-6 col-xs-6 padd-left-zero">
                                                    Compound Price
                                                </div>
                                                <div class="col-lg-1 col-md-4 col-sm-6 col-xs-6 padd-left-zero" style="background: #D9D9D9;">
                                                    <b>$ <fmt:formatNumber pattern="0.00" value="${order.outOfPocket}"/></b>
                                                </div>
                                                <br clear="all" />
                                                <div class="col-lg-4 col-md-5 col-sm-6 col-xs-6 padd-left-zero">
                                                    Less CoPay Assistance from API
                                                </div>
                                                <div class="col-lg-1 col-md-4 col-sm-6 col-xs-6 padd-left-zero" style="background: #D9D9D9;color:red;font-weight: bold;">
                                                    -$ <fmt:formatNumber pattern="0.00" value="${order.copay}"/>
                                                </div>
                                                <br clear="all" />
                                                <div class="col-lg-4 col-md-5 col-sm-6 col-xs-6">
                                                    <b>Total Payable</b>
                                                </div>
                                                <div class="col-lg-1 col-md-4 col-sm-6 col-xs-6" style="background: #D9D9D9;">
                                                    $ <fmt:formatNumber pattern="0.00" value="${order.outOfPocket - order.copay}"/>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="form-group" style="margin-top: 10px; margin-bottom: 5px; padding:10px 14px 10px 14px; background: white;border:1px solid #c6c6c6;float: left;width:100%;border-radius:2px;"> 
                                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 padd-left-zero" style="font-size:12px;"> 
                                                <span style="color:#145994;font-weight: bold;">Payment Method</span> <br />
                                                <span>Card -  ${order.cardType}: ${order.cardNumber}-${order.cardCvv}</span> <br />
                                                <span>Expiry: ${order.cardExpiry}</span> <br />
                                            </div>
                                            <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 padd-left-zero" style="font-size:12px;"> 
                                                <c:if test="${empty order.id or  order.id == 0}">
                                                    <button type="submit" class="btndrop pull-left" style="margin-top:1px; background-color: #145994; color: #FFFFFF;">Place Order</button> 
                                                </c:if>
                                                <c:if test="${empty order.id or  order.id == 0}">
                                                    &nbsp;&nbsp;<button type="button" onclick="location.href = '${pageContext.request.contextPath}/order/placeorder/${order.transactionNo}'" class="btndrop" style="margin-top:1px; background-color: #145994; color: #FFFFFF;">Back</button>
                                                </c:if>
                                                <br />
                                                <span >By placing your order, you agree to Stardock's store policies and condition of use</span> <br />
                                            </div>
                                        </div>
                                        <span style="font-size: 12px;"><i>
                                                After pressing the "Place Order" button, your order will be processed and the next page will display your receipt (including your order number). You will also receive and e-mail or message acknowledging receipt of your order. <br />
                                                The form on http://www.stardock.com/support can be used to resend this information to the email address you ordered under.<br />
                                                if you have any questions of problems with your order, please email  <a href="mailto:sales@stardock.com" style="color: blue;">sales@stardock.com</a>.</i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="margin: 0px;font-size: 8px; margin-bottom: 15px;">
                        <img src="${pageContext.request.contextPath}/resources/images/norton.jpg" style="height: 35px;padding-right: 10px;line-height: 8px;" align="left" />You are now connected using a secure (SSL) connection and all information is transmitted in an encrypted form. You should see a small lock (Internet Explorer, Firefox, Chrome, Opera, Safari) indication that your browser is communicating securely with our web store.

                        <br />
                        <div style="color:#92BCDF; font-weight: bold;">
                            <a href="#" style="color:#2679C0">Terms & Conditions</a>  |  <a href="#" style="color:#2679C0">Privacy Policy</a>  |  <a href="#" style="color:#2679C0">Espanol?</a>  |   <a href="#" style="color:#2679C0">Return Policy</a> <br /><br />
                        </div>
                    </div>
                </form:form>
                </div>
                </div>
        </div> <!-- /content -->

    </div> <!-- /wrapper -->
</body>
</html>
