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

                <form:form action="${pageContext.request.contextPath}/order/add/${order.id}" commandName="order" method="post" role="form">    
                    <div class="wrp clearfix pOrderHeader" style="padding-left: 15px; padding-right: 15px;color:black;">
                        <img src="${pageContext.request.contextPath}/resources/images/API_Logo.jpg">&nbsp;&nbsp;<img class="hidden-xs" src="${pageContext.request.contextPath}/resources/images/bar.png" />&nbsp;&nbsp;</a> <br /> <span style="color:#2071b6;font-family:arial;font-size: 18px;">API CoPay Assistance Program - Order Status</span>  
                        <br />

                        <div class="row grid" style="height: auto;border: 0px;">
                            <div class="search-grid demo-show2 accordionnn">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background" style="display: block;border: 1px solid #c6c6c6;margin-top: 5px;padding-right: 8px;padding-bottom: 10px;" id="panel${loop.index}"> 

                                    <div class="full-width overflow-auto pull-left"> 
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">  
                                            <div class="form-group" style="margin-top: 10px; padding:10px 6px 5px 14px; background: white;border:1px solid #c6c6c6;float: left;width:100%;border-radius:2px;"> 
                                                <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12" style="font-size:12px;"> 
                                                    <c:choose>
                                                        <c:when test="${order.logo ne null}">
                                                            <img src="${pageContext.request.contextPath}/order/getLogo/${order.id}" height="58" style="padding-right:10px;"  align="left"> <br />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${pageContext.request.contextPath}/resources/images/pills.jpg" height="58" style="padding-right:10px;"  align="left" /> <br />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <b style="color:#2071b6;">VIP CoPay Offer for Rx</b> <br />

                                                    <b>Save $ <fmt:formatNumber pattern="0.00" value="${order.copay}"/> now</b> <br />
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="text-align: center; padding:14px 14px 13px 14px; background: white;border:1px solid #c6c6c6;float: left;border-radius:2px;font-size:12px;min-height: 142px;margin-top: -5px;"> 
                                                <b style="font-size: 14px;">Order # ${order.id} : GenRx</b>
                                                <br /><br />
                                                <c:choose>
                                                    <c:when test="${order.orderStatus.name eq 'Un-Assigned' or order.orderStatus.name eq 'Assigned'}">
                                                        <img src="${pageContext.request.contextPath}/resources/images/01.png">
                                                    </c:when>
                                                    <c:when test="${order.orderStatus.name eq 'Filled'}">
                                                        <img src="${pageContext.request.contextPath}/resources/images/02.png">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="${pageContext.request.contextPath}/resources/images/03.png">
                                                    </c:otherwise>
                                                </c:choose>
                                                <br />
                                                <c:choose>
                                                    <c:when test="${order.orderStatus.name eq 'Un-Assigned'}">
                                                        <b>In-Progress</b>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <b>${order.orderStatus.name}</b>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 shippingAdd" style="padding:10px 2px 10px 3px; background: white;border:1px solid #c6c6c6;float: left;border-radius:2px;font-size:11px; left:5px;width: 49.6%;min-height: 142px; margin-top: -5px;"> 
                                                <div class="row">
                                                    <div class="col-lg-1 col-md-1 col-sm-6 col-xs-6">
                                                        <b>Estimated Delivery:</b>
                                                    </div>
                                                    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
                                                        <b><fmt:formatDate pattern="MM/dd/yyyy" value="${order.createOn}"/></b> - <b><fmt:formatDate pattern="MM/dd/yyyy" value="${order.estimated}"/></b>
                                                    </div>
                                                    <br clear="all" />
                                                    <div class="col-lg-1 col-md-1 col-sm-6 col-xs-6">
                                                        <b>Shipping Address:</b>
                                                    </div>
                                                    <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                                        ${order.firstName}  ${order.lastName}<br /> 
                                                        ${order.streetAddress} ${order.addressLine2},
                                                        ${order.city}, ${order.state},
                                                        ${order.zip}<br/> 
                                                    </div>
                                                    <br clear="all" />
                                                    <div class="col-lg-1 col-md-1 col-sm-6 col-xs-6">
                                                        ${not empty order.orderTrackingNo ? '<b>Tracking:</b>':''}
                                                    </div>
                                                    <div class="col-lg-2 col-md-2 col-sm-6 col-xs-6" style="width: 80%;">
                                                        ${order.orderTrackingNo} <c:if test="${not empty order.orderCarriers.name}">(${order.orderCarriers.name})</c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">  

                                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="margin-top: 10px; padding:23px 14px 22px 14px; background: white;border:1px solid #c6c6c6;float: left;border-radius:2px;font-size:12px;min-height: 125px;height: 125px;overflow: auto;"> 
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 orderHeadings">
                                                    Details
                                                </div>
                                                <div class="row">
                                                    <c:forEach var="orderhistory" items="${order.orderHistory}">
                                                        <div class="col-lg-1 col-md-1 col-sm-6 col-xs-6">
                                                            <b><fmt:formatDate pattern="MM/dd/yyyy" value="${orderhistory.createdOn}"/></b>
                                                        </div>
                                                        <div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
                                                            ${orderhistory.orderStatus.name} <c:if test="${not empty orderhistory.comments}">-</c:if> ${orderhistory.comments}
                                                            </div>
                                                            <br clear="all" />
                                                    </c:forEach>
                                                </div>
                                            </div>

                                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 shippingAdd" style="margin-top: 10px;background: white;border:1px solid #c6c6c6;float: left;border-radius:2px;font-size:11px;padding: 0; left: 5px; width: 49.6%;"> 
                                                <img src="${pageContext.request.contextPath}/resources/images/question.jpg" width="100%" style="min-height: 123px;"><br />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="margin: 0px;font-size: 8px; margin-bottom: 15px; padding-left: 15px;">
                        <img src="${pageContext.request.contextPath}/resources/images/norton.jpg" style="height: 35px;padding-right: 10px;line-height: 8px;" align="left" />You are now connected using a secure (SSL) connection and all information is transmitted in an encrypted form. You should see a small lock (Internet Explorer, Firefox, Chrome, Opera, Safari) indication that your browser is communicating securely with our web store.

                        <br />
                        <div style="color:#92BCDF; font-weight: bold;">
                            <a href="#" style="color:#2679C0">Terms & Conditions</a>  |  <a href="#" style="color:#2679C0">Privacy Policy</a>  |  <a href="#" style="color:#2679C0">Espanol?</a>  |   <a href="#" style="color:#2679C0">Return Policy</a> <br /><br />
                        </div>
                    </div>
                </form:form>
            </div> <!-- /content -->

        </div> <!-- /wrapper -->
    </body>
</html>
