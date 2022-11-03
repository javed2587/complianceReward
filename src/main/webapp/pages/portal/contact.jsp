<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div class="wrapper">
            <div class="container" style="background-color:#efefef;margin-top:10px;margin-bottom: 50px;">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="pageTitle">CONTACT US</h1>
                        <hr class="titleBorder" />
                        <c:forEach var="pc" items="${pageContents}">
                            <c:set var="dcTitle" value="${pc.cMSPages.title}"/>
                            <c:if test="${fn:containsIgnoreCase(dcTitle, 'Contact Us – Content')}">
                                <p class="discription">${pc.content}</p>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                        <form:form action="${pageContext.request.contextPath}/ConsumerPortal/contactUs" commandName="contactUs" role="form" method="Post" id="contactForm">
                            <div class="form-group contactUspadding contactfield">
                                <form:input path="name" cssClass="form-control textboxchangepasswod" placeholder="Name"/>
                                <form:errors path="name" cssClass="errorMessage" cssStyle="float:left;"/>
                            </div>
                            <div class="form-group contactUspadding contactfield">
                                <form:input path="email" cssClass="form-control textboxchangepasswod" placeholder="Email"/>
                                <form:errors path="email" cssClass="errorMessage" cssStyle="float:left;"/>
                            </div>
                            <div class="form-group contactUspadding contactfield">
                                <form:textarea id="body" path="message" rows="5" cssClass="form-control textareaheight" placeholder="Message"/>
                                <form:errors path="message" cssClass="errorMessage" cssStyle="float:left;"/>
                            </div>
                            <div class="form-group contactUspadding contactfield">
                                <div id="errorMessage" class="${not empty errorMessage?'errorMessage':'message'}" style="position: absolute;bottom: 75px;"><c:if test="${not empty errorMessage}">${errorMessage}</c:if><c:if test="${not empty message}">${message}</c:if></div>
                                    <button type="submit" class="btn btn-primary">SUBMIT</button>
                                    <button type="button" class="btn btn-primary" onclick="resetForm();">RESET</button>
                                </div>
                        </form:form>
                    </div>
                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                        <div class="form-group contactUspadding contactfield">
                            <style>
                                #map-canvas {
                                    width: 100%;
                                    height: 175px;
                                }
                            </style>
                            <script src="https://maps.googleapis.com/maps/api/js"></script>
                            <script>
                                        function initialize() {
                                            var myLatlng = new google.maps.LatLng(26.392244, -80.075983);
                                            var mapCanvas = document.getElementById('map-canvas');
                                            var mapOptions = {
                                                center: myLatlng,
                                                zoom: 15,
                                                mapTypeId: google.maps.MapTypeId.TERRAIN
                                            }
                                            var map = new google.maps.Map(mapCanvas, mapOptions)
                                            var marker = new google.maps.Marker({
                                                position: myLatlng,
                                                map: map,
                                                title: '4800 North Federal Highway'
                                            });
                                        }
                                        google.maps.event.addDomListener(window, 'load', initialize);
                            </script>
                            <div id="map-canvas"></div>
                        </div>
                        <div class="form-group contactfieldAdd" style="background-color:#fff;padding-top:6px; padding-left:10px;padding-right:10px;padding-bottom:10px;">
                            <c:forEach var="address" items="${pageContents}">
                                <c:set var="dcTitle" value="${address.cMSPages.title}"/>
                                <c:if test="${fn:containsIgnoreCase(dcTitle, 'Contact Us - Address')}">
                                    <p>${address.content}</p>  
                                </c:if>
                                <c:if test="${fn:containsIgnoreCase(dcTitle, 'Contact Us – Phone')}">
                                    <address style="color:#003468;" class="address-setting"><span class="fa fa-phone" style="margin-right:10px;"></span>${address.content}</address>
                                    </c:if>
                                    <c:if test="${fn:containsIgnoreCase(dcTitle, 'Contact Us – Email')}">
                                    <address style="color: #003468;" class="address-setting"><span class="glyphicon glyphicon-envelope" style="margin-right:10px;"></span>${address.content}</address> 
                                    </c:if>
                                </c:forEach>
                            <a href="https://www.linkedin.com/company/american-pharmaceutical-ingredients-llc" target="_blank"><i class="fa fa-1x fa-linkedin" style="color:#fff;background-color:#007ab9;text-align:center;padding:5px;"></i></a>
                            <a href="https://www.facebook.com/BuyAPI" target="_blank"><i class="fa fa-1x fa-facebook" style="color:#fff;background-color:#3b5998;text-align:center;padding:5px;width: 25px;"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp" />
        <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <script type="text/javascript">
                                        function resetForm() {
                                            $('#contactForm').find("input[type=text], textarea").val("");
                                        }

                                        function startTimer()
                                        {
                                            window.setInterval("hideMessage()", 5000);
                                        }
                                        function hideMessage() {
                                            document.getElementById("errorMessage").style.display = "none";

                                        }
        </script>
    </body>
</html>

