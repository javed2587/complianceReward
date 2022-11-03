<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <div style="float: top;">  
            <img src="${pageContext.request.contextPath}/resources/images/QRHeader.jpg" class="bg" style="left: 0;right: 0;">
        </div> 
        <div style="padding-top: 8px;">
            <div>
                <div class="font col-sm-12 webPortal-marginleft">
                    <p style="font-family:arial;font-size: 22px; color: #626262" class="font"><span style="color:#2071b6; font-weight: bold;">Join now </span>for custom prepared Rx's in these categories:</p>
                </div>
                <div class="col-sm-12" style="padding-left: 8%;padding-top: 15px;">
                    <ul class="col-sm-6">

                        <li style="color: #2071b6;font-size: 22px;"><span style="font-family:arial;font-size: 22px; color: #626262">Fertility</span></li>
                        <li style="color: #2071b6;font-size: 22px;"><span style="font-family:arial;font-size: 22px; color: #626262;">Topical Pain Cream</span></li>
                        <li style="color: #2071b6;font-size: 22px;"><span style="font-family:arial;font-size: 22px; color: #626262;">Scar & Wound Cream</span></li>
                        <li style="color: #2071b6;font-size: 22px;"><span style="font-family:arial;font-size: 22px; color: #626262;">Migraine</span></li>
                    </ul>
                    <ul class="col-sm-6">
                        <li style="color: #2071b6;font-size: 22px;"><span style="font-family:arial;font-size: 22px; color: #626262;">Allergy</span></li>
                        <li style="color: #2071b6;font-size: 22px;"><span style="font-family:arial;font-size: 22px; color: #626262;">HCG & Testosterone</span></li>
                        <li style="color: #2071b6;font-size: 22px;"><span style="font-family:arial;font-size: 22px; color: #626262;">Anti-Aging & Sun Damage</span></li>
                    </ul>
                </div>
                <div class="font col-sm-12 webPortal-marginleft" style="padding-top: 30px;">
                    <p style="font-family:arial;font-size: 22px; color: #626262" class="font">Get <span style="color:#2071b6;">reminders, order status, </span>and <span style="color:#2071b6;">copay savings</span>
                        information on your web-connected device.<br><br> Get information about <span style="color:#2071b6;">insurance coverage,</span>
                        possible sources of <span style="color:#2071b6;">financial assistance,</span> and lowest cost of fulfillment.
                    </p>
                </div>
            </div>
        </div> <!-- /content -->
        <div class="qrFooter">
            <c:set var="path" value="${appPath}"/>
            <c:choose>
                <c:when test="${fn:contains(path, 'dev')}">
                    <img src="${pageContext.request.contextPath}/resources/images/Footer_QR_Dev.jpg" class="bg">
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.request.contextPath}/resources/images/Footer_QR_Production.jpg" class="bg">
                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>