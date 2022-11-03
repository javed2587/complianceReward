<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/menu.jsp" />

                <div class="breadcrumbs">
                    <c:if test="${pharmacy.id eq null || pharmacy.id eq 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Pharmacies <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add New
                    </c:if>
                    <c:if test="${pharmacy.id != null &&  pharmacy.id != 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Pharmacy Detail
                    </c:if>
                </div>
                <div class="heading">
                    <c:if test="${pharmacy.id eq null || pharmacy.id eq 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Pharmacy</h1>
                    </c:if>
                    <c:if test="${pharmacy.id != null &&  pharmacy.id != 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Pharmacy Detail</h1>
                    </c:if>
                </div> <!-- /header -->


                <form:form action="${pageContext.request.contextPath}/pharmacy/add" commandName="pharmacy" method="post" prependId="false">
                    <form:hidden path="id" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" align="right" style="padding-right: 0px;top:-5px;">
                                <div class="btndrop btn-margin" style="background:#2071b6;color: white;" onclick="location.href = '${pageContext.request.contextPath}/pharmacy/list'">Back</div>
                        </div>
                        <div class="search-grid">   
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background1" style="display: block;border: 1px solid #c6c6c6; margin-bottom: 10px;margin-top: 1px;padding-right: 8px;" id="panel${2}">         
                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  
                                </div>   
                                <div class="full-width overflow-auto pull-left">  
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                        <div class="form-group" id="orderDatailPanel1">
                                            <div class="col-md-12">
                                                <span style="color:#2071b6;font-weight: bold;">${pharmacy.title}</span>
                                            </div>
                                            <div class="col-md-12">
                                                <span>${pharmacy.address1}, ${pharmacy.city}, ${pharmacy.state.abbr} ${pharmacy.zip}</span>
                                            </div>
                                        </div>
                                    </div> 
                                </div>  
                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  
                                </div> 
                            </div>
                        </div>
                        <div class="search-grid">
                            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 background1" style="display: block;border: 1px solid #c6c6c6; margin-bottom: 10px;margin-right: 10px;float: left;" id="panel${0}">         
                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  

                                </div>   

                                <div class="full-width pull-left" style="height: 140px;overflow: auto;">  
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                        <div style="padding-left: 8px;font-weight: bold;color:#2071b6;">Pharmacist Information (Primary Account)</div>
                                        <div class="form-group" id="orderDatailPanel3" style="padding-left: 0px;">
                                            <c:forEach var="pharmacyUser" items="${pharmacyUserList}" end="0">
                                                <div class="col-lg-3 col-sm-12 col-md-4 col-xs-12">Name <span class="hidden-md hidden-sm hidden-xs" style="float:right;color:#1f4c76;">:</span></div>
                                                <div class="col-lg-9 col-sm-12 col-md-8 col-xs-12"> ${pharmacyUser.firstName} ${pharmacyUser.lastName}</div>
                                                <div class="col-lg-3 col-sm-12 col-md-4 col-xs-12">Email Address <span class="hidden-md hidden-sm hidden-xs" style="float:right;color:#1f4c76;">:</span></div>
                                                <div class="col-lg-9 col-sm-12 col-md-8 col-xs-12">${pharmacyUser.email}</div>
                                                <div class="col-lg-3 col-sm-12 col-md-4 col-xs-12">Notification Phone # <span class="hidden-md hidden-sm hidden-xs" style="float:right;color:#1f4c76;">:</span></div>
                                                <div class="col-lg-9 col-sm-12 col-md-8 col-xs-12">${pharmacyUser.phone}</div>
                                            </c:forEach>

                                        </div>  
                                    </div> 
                                </div>   
                            </div>
                        </div>
                        <div class="search-grid">     
                            <div class="col-lg-5 col-md-12 col-sm-12 col-xs-12 background1 portalwidth" style="display: block;border: 1px solid #c6c6c6; margin-bottom: 10px;padding-right: 8px;" id="panel${1}">         

                                <div style="width:100%;padding-left: 0px;position:relative;padding-top: 7px;" class="pull-left">  

                                </div>   

                                <div class="full-width overflow-auto pull-left" style="margin-bottom: 3px;height: 140px;">  
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                        <div style="font-weight: bold;color:#2071b6;">Facility Operation</div>
                                        <c:forEach var="pharmacyFacilityOperation" items="${pharmacyFacilityOperations}">
                                            <c:if test="${pharmacyFacilityOperation.isSelected}">
                                                <div style="width:100%;">
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
                        </div>
                        <div class="search-grid">   
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background1" style="display: block;border: 1px solid #c6c6c6; margin-bottom: 10px;padding-right: 8px;" id="panel${2}">         
                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  
                                </div>   
                                <div class="full-width overflow-auto pull-left">  
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                        <div class="form-group" id="orderDatailPanel1">
                                            <div class="col-md-12">
                                                <div style="color:#2071b6;font-weight: bold;">Secondary Contact(s)</div>
                                            </div>
                                            <c:choose>
                                                <c:when test="${fn:length(pharmacyUserList) gt 1}">
                                                    <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                        <div class="accountLabel">Name</div>
                                                    </div> 
                                                    <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                        <div class="accountLabel">Email Address</div>
                                                    </div> 
                                                    <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                        <div class="accountLabel">Notification Phone #.</div>
                                                    </div> 
                                                    <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                        <div class="accountLabel">Last Login</div>
                                                    </div>
                                                    <c:forEach var="pharmacyUser" items="${pharmacyUserList}" begin="1">
                                                        <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                            <div class="labelDescription" style="width:100%;"> ${pharmacyUser.firstName} ${pharmacyUser.lastName}</div>
                                                        </div> 
                                                        <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                            <div class="labelDescription" style="width:100%;"> ${pharmacyUser.email}</div>
                                                        </div> 
                                                        <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                            <div class="labelDescription" style="width:100%;"> ${pharmacyUser.phone}</div>
                                                        </div> 
                                                        <div class="col-lg-3 col-sm-6 col-md-3 col-xs-12">
                                                            <div class="labelDescription" style="width:100%;"> 6/8/2015 - 22:30</div>
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
                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  
                                </div> 
                            </div>
                        </div>
                    </div>
                </form:form>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
    </body>
</html>
