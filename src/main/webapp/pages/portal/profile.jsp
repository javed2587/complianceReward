<%@page import="com.ssa.cms.bean.SessionBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <form:form action="${pageContext.request.contextPath}/PharmacyPortal/profile" commandName="pharmacy" role="form" method="POST">
            <div class="container contentsContainer">
                <div class="row">
                    <h2 class="pageTitle">Pharmacy Profile</h2>
                    <hr class="titleBorder"> 
                    <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
                            <c:forEach var="pc" items="${pageContents}">
                                <c:set var="dcTitle" value="${pc.cMSPages.title}"/>
                                <c:if test="${fn:containsIgnoreCase(dcTitle, 'Pharmacy Profile – Content')}">
                                    <p style="text-align: center;">${pc.content}</p>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="row sperator">
                    <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding-left: 0px; padding-right: 0px;">
                        <div class="serchResult" style="margin: 0px;">
                            <!--                <div class="dotCircle col-xs-3 col-lg-1 col-sm-1 col-md-1"><i style="" class="fa fa-dot-circle-o fa-2x"></i></div>-->

                            <h3 class="blueHeading">${pharmacy.title}</h3> 
                            <p class="descriptionText col-lg-12 col-sm-8 col-xs-12 col-md-6" style="padding-left: 0px;">${pharmacy.address1}, ${pharmacy.city}, ${pharmacy.state.abbr} ${pharmacy.zip}</p>
                        </div>
                    </div>
                </div>
                <div class="row sperator">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 left-devs" style="background:#f1f0f0;">
                        <div class="whiteBackground" id="leftDiv">
                            <div class="accountHeading">Pharmacist Information (Primary Account)</div>
                            <input type="hidden" id="iddd" value="${sessionScope.sessionBeanPortal.userId}"/>
                            <c:forEach var="pharmacyUser" items="${pharmacyUserList}" end="0">
                                <c:if test="${sessionScope.sessionBeanPortal.userId eq pharmacyUser.id}" var="isAdminUser"/>

                                <div class="accountLabel col-lg-3 col-sm-5 col-md-4 col-xs-12">Name <span class="hidden-md hidden-sm hidden-xs" style="float:right;color:#1f4c76;">:</span></div>
                                <div class="labelDescription col-lg-9 col-sm-7 col-md-8 col-xs-12"> ${pharmacyUser.firstName} ${pharmacyUser.lastName}</div>
                                <div class="accountLabel col-lg-3 col-sm-5 col-md-4 col-xs-12">Email Address <span class="hidden-md hidden-sm hidden-xs" style="float:right;color:#1f4c76;">:</span></div>
                                <div class="labelDescription col-lg-9 col-sm-7 col-md-8 col-xs-12">${pharmacyUser.email}</div>
                                <div class="accountLabel col-lg-3 col-sm-5 col-md-4 col-xs-12">Notification Phone # <span class="hidden-md hidden-sm hidden-xs" style="float:right;color:#1f4c76;">:</span></div>
                                <div class="labelDescription col-lg-9 col-sm-7 col-md-8 col-xs-12">${pharmacyUser.phone}</div>
                                <div class="accountLabel col-lg-3 col-sm-5 col-md-4 col-xs-12">Notification <span class="hidden-md hidden-sm hidden-xs" style="float:right;color:#1f4c76;">:</span></div>
                                <div class="labelDescription col-lg-9 col-sm-7 col-md-8 col-xs-12">${pharmacyUser.smsNotify!=null && pharmacyUser.smsNotify eq 'Yes'?'SMS':''}<c:if test="${pharmacyUser.emailNotify !=null && pharmacyUser.emailNotify eq 'Yes'}">,${pharmacyUser.emailNotify eq 'Yes'?'Email':''}</c:if></div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 right-dev" style="background:#f1f0f0;" id="facilityOpertaionDiv">
                        <div class="whiteBackground" style="min-height: 145px;" id="rightDiv">
                            <div class="accountHeading">Facility Operation</div>
                            <c:forEach var="pharmacyFacilityOperation" items="${pharmacyFacilityOperations}">
                                <c:if test="${pharmacyFacilityOperation.isSelected}">
                                    <div class="accountLabel" style="width:100%;">
                                        <abbr style="min-width: 84px;float:left;">${pharmacyFacilityOperation.day}<span class="colon"> : </span> </abbr>
                                        <abbr style="color:#333333;min-width: 85px;">&nbsp;&nbsp;${pharmacyFacilityOperation.phoneHoursFrom} (am) </abbr> 
                                        <abbr style="color:#1f4c76;min-width: 40px;"> &nbsp;&nbsp;to&nbsp;&nbsp;</abbr> 
                                        <abbr style="color:#333333;min-width: 83px;">${pharmacyFacilityOperation.phoneHoursTo} (pm)</abbr>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>    
                <div class="row sperator">            
                    <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding-left: 0px; padding-right: 0px;">
                        <div class="secondaryContacts">
                            <div class="accountHeading">Secondary Contact(s) <abbr style="font-size:16px;font-weight:normal;color:#666666;">(max 5)</abbr></div>
                            <c:choose>
                                <c:when test="${fn:length(pharmacyUserList) gt 1}">
                                    <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12" style="padding-left: 0px;">
                                        <div class="accountLabel">Name</div>
                                    </div> 
                                    <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12">
                                        <div class="accountLabel">Email Address</div>
                                    </div> 
                                    <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12">
                                        <div class="accountLabel">Notification Phone #.</div>
                                    </div>
                                    <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12">
                                        <div class="accountLabel">Notification</div>
                                    </div> 
                                    <c:forEach var="pharmacyUser" items="${pharmacyUserList}" begin="1">
                                        <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12" style="padding-left: 0px;">
                                            <div class="labelDescription" style="width:100%;"> ${pharmacyUser.firstName} ${pharmacyUser.lastName}</div>
                                        </div> 
                                        <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12">
                                            <div class="labelDescription" style="width:100%;"> ${pharmacyUser.email}</div>
                                        </div> 
                                        <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12">
                                            <div class="labelDescription" style="width:100%;"> ${pharmacyUser.phone}</div>
                                        </div> 
                                        <div class="col-lg-3 col-sm-3 col-md-3 col-xs-12">
                                            <div class="labelDescription" style="width:100%;">${pharmacyUser.smsNotify!=null && pharmacyUser.smsNotify eq 'Yes'?'SMS':''}<c:if test="${pharmacyUser.emailNotify !=null && pharmacyUser.emailNotify eq 'Yes'}">,${pharmacyUser.emailNotify eq 'Yes'?'Email':''}</c:if></div>
                                            </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="text-align: center; font-size: 13px;">
                                        No secondary contact created.
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>  
                    </div>
                </div>            
                <div class="row sperator">            
                    <span class="pull-left col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding-left: 0px; padding-right: 0px; padding-top: 10px;">
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding-left: 0px; padding-right: 0px;">
                            <button type="button" class="btn btn-primary large" style="margin-left: 0px;" ${isAdminUser ? '' : 'disabled'} onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/profile_edit'">UPDATE</button>
                            <button type="button" class="btn btn-primary large" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/changepassword'">CHANGE PASSWORD</button>
                            <div style="margin-top: 10px;" class="message" ${not empty message ? 'tabindex="0"' : ''}><c:if test="${not empty message}">${message}</c:if></div>
                            </div>
                        </span>
                    </div>

                </div>
        </form:form>            
        <jsp:include page="./inc/footer.jsp" />
        <script>
            $(document).ready(function() {
                $("#leftDiv").css("min-height", $("#rightDiv").innerHeight());
            });
        </script>
    </body>
</html>


