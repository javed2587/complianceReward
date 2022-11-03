<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/menu.jsp" />
                <c:if var="isSAdmin" test="${fn:containsIgnoreCase(sessionBean.userName, 'Super Admin') || fn:containsIgnoreCase(sessionBean.userName, 'SuperAdmin')}"></c:if>
                <div class="${sessionBean.pharmacy eq null?'breadcrumbs':'pharmacybreadcrumbs'}">
                    <div style="display: ${sessionBean.pharmacy eq null?'block':'none'}"><i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> View Orders</div>
                    <div style="display: ${sessionBean.pharmacy ne null?'block':'none'}">Rx Alert Contact: <span style="font-weight: normal;">${sessionBean.pharmacy.contactPerson}, ${sessionBean.pharmacy.personEmail}, ${sessionBean.pharmacy.personMobileNo}, ${sessionBean.pharmacy.personOfficePhoneNo}</span></div>
                </div>

                <form:form action="${pageContext.request.contextPath}/order/add/${order.id}" commandName="order" method="post" role="form" enctype="multipart/form-data">    
                    <form:hidden path="id"/>
                    <form:hidden path="createdBy"/>
                    <form:hidden path="type"/>
                    <div class="wrp clearfix" style="padding-left: 15px; padding-right: 15px;padding-top: 15px;">
                        <div class="search-grid">   
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background1" style="display: block;border: 1px solid #c6c6c6; margin-bottom: 10px;margin-top: -4px;padding-right: 8px;" id="panel${2}">         
                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  
                                </div>   
                                <div class="full-width overflow-auto pull-left">  
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                        <div class="form-group" id="orderDatailPanel1">
                                            <div class="col-md-3">
                                                <span style="color:#2071b6;font-weight: bold;">Order Date:</span> <span style="color:black;"><fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${order.createOn}"/></span>
                                            </div>
                                            <div class="col-md-2">
                                                <span style="color:#2071b6;font-weight: bold;">Order #:</span> <span style="color:black;">${order.id}</span>
                                            </div>
                                            <div class="col-md-4">
                                                <span style="color:#2071b6;font-weight: bold;">Pt Name:</span> <span style="color:black;">${order.dailyRedemption.cardholderFirstName} ${order.dailyRedemption.cardholderLastName}</span>
                                            </div>
                                            <div class="col-md-3">
                                                <span style="color:#2071b6;font-weight: bold;">DOB:</span> <span style="color:black;"><fmt:formatDate value="${order.dailyRedemption.cardholderDob}" pattern="MM/dd/YYYY" var="cardholderDob"/>${cardholderDob}</span>
                                            </div>
                                            <div class="col-sm-12 col-xs-12">
                                                <span style="color:#2071b6;font-weight: bold;">Address:</span> <span style="color:black;">${order.streetAddress},${order.city}, ${order.state},${order.zip}</span>
                                            </div>
                                        </div>
                                    </div> 
                                </div>  
                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  
                                </div> 
                            </div>
                        </div>

                        <div class="search-grid">     
                            <form:hidden path="showFolder"/>


                            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 background1" style="display: block;border: 1px solid #c6c6c6; margin-bottom: 10px;margin-right: 10px;float: left;" id="panel${0}">         

                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  

                                </div>   

                                <div class="full-width pull-left" style="${sessionBean.pharmacy eq null?'height: 215px;overflow: auto;':'height: 291px;overflow: auto;'}">  
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                        <div style="padding-left: 8px;font-weight: bold;">API Rebate Eligible</div>
                                        <div class="form-group" id="orderDatailPanel3">
                                            <c:forEach var="row" items="${redemptionIngredient}">
                                                <div class="col-sm-12 page-field" style="color: ${row.ndcMatch eq true?'#067512':'initial'}">
                                                    ${row.srno}. ${row.name} [${row.ndc}] ${row.strength}
                                                </div>
                                            </c:forEach>
                                        </div>  
                                    </div> 
                                </div>   
                            </div>
                        </div>
                        <div class="search-grid">     
                            <form:hidden path="showFolder"/>


                            <div class="col-lg-5 col-md-12 col-sm-12 col-xs-12 background1 portalwidth" style="display: block;border: 1px solid #c6c6c6; margin-bottom: 10px;padding-right: 8px;" id="panel${1}">         

                                <div style="width:100%;padding-left: 0px;position:relative;padding-top: 7px;" class="pull-left">  

                                </div>   

                                <div class="full-width overflow-auto pull-left">  
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 portal320right" style="padding-left: 0px;"><div style="color: #2071b6;background: white;padding-left: 10px;">Original Claim Submitted: <span class="hidden-lg hidden-md hidden-sm pull-right" style="color: black;background: white;">$ <fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.pharmacyGrossAmount}"/></span></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 hidden-xs pharmacy-portaltop" style="padding-left: 0px; padding-right: 0px;"><div style="color: black;background: white;" align="center">$ <fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.pharmacyGrossAmount}"/></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 portal320right" style="padding-top: 5px;padding-left: 0px;"><div style="color: #2071b6;background: white;padding-left: 10px;">Primary Insurance Reimbursed:<span class="hidden-lg hidden-md hidden-sm pull-right" style="color: black;background: white;">$ <fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.planPharmacyAmount}"/></span></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 hidden-xs" style="padding-top: 5px;padding-left: 0px; padding-right: 0px;"><div style="color: black;background: white;" align="center">$ <fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.planPharmacyAmount}"/></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 portal320right" style="padding-top: 5px;padding-left: 0px;"><div style="color: #2071b6;background: white;padding-left: 10px;">Patient Out Of Pocket: <span class="hidden-lg hidden-md hidden-sm pull-right" style="color: black;background: white;">$ <fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.ptOutOfPocket}"/></span></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 hidden-xs" style="padding-top: 5px;padding-left: 0px;padding-right: 0px;"><div style="color: black;background: white;" align="center">$ <fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.ptOutOfPocket}"/></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 portal320right" style="padding-top: 5px;padding-left: 0px;"><div style="color: #2071b6;background: white;padding-left: 10px;">API Copay Assistance: <span class="hidden-lg hidden-md hidden-sm pull-right" style="color: #e70000;background: white;">$ <fmt:formatNumber pattern="0.00" value="${order.copay}"/></span></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 hidden-xs" style="padding-top: 5px;padding-left: 0px;padding-right: 0px;"><div style="color: #E70000;background: white;" align="center">$ <fmt:formatNumber pattern="0.00" value="${order.copay}"/></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 portal320right" style="padding-top: 5px;padding-left: 0px;"><div style="color: #2071b6;background: white;padding-left: 10px;">Final Payment: <span class="hidden-lg hidden-md hidden-sm pull-right" style="color: black;background: white;">$ <c:set var="finalPay" value="${order.dailyRedemption.ptOutOfPocket - order.copay}" /><fmt:formatNumber pattern="0.00" value="${finalPay}"/></span></div></div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 hidden-xs" style="padding-top: 5px;padding-left: 0px;padding-right: 0px;padding-bottom:7px;"><div style="color: black;background: white;font-weight: bold" align="center">$ <c:set var="finalPay" value="${order.dailyRedemption.ptOutOfPocket - order.copay}" /><fmt:formatNumber pattern="0.00" value="${finalPay}"/></div></div>
                                    </div> 
                                </div>
                            </div>
                        </div>
                        <div class="search-grid">     
                            <form:hidden path="showFolder"/>

                            <div class="col-lg-5 col-md-12 col-sm-12 col-xs-12 background1 portalwidth" style="display: block;border: 1px solid #c6c6c6;margin-bottom: 20px;padding-right: 8px;">         
                                <div class="full-width overflow-auto pull-left" style="${sessionBean.pharmacy eq null?'padding-top: 10px;padding-bottom: 10px;':'padding-top: 10px;'}">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">
                                        <div class="${sessionBean.pharmacy eq null?'col-sm-12':'col-sm-6'}" style="color: #2071b6;font-weight: bold;">
                                            Status History
                                        </div>
                                        <div class="col-sm-6" style="display: ${sessionBean.pharmacy eq null?'none':'block'}">
                                            <form:select path="orderStatus.id" id="orderStatusId" cssClass="form-control selectpicker show-tick" onchange="disableEnableTrackNo(this.value)" disabled="${order.orderStatus.id eq 4 || order.orderStatus.id == 1 || order.orderStatus.id eq 5}">
                                                <form:option value="0" label="Order Status" />
                                                <form:options items="${orderStatusList}" itemValue="id" itemLabel="name"/>
                                            </form:select>
                                            <input type="hidden" id="statusHidden" value="${order.selectedStatus}"/>
                                        </div>
                                        <div class="${sessionBean.pharmacy eq null?'col-sm-12':'col-sm-6 portal-height'}"><span style="color: green">${order.orderStatus.name}</span> <span style="color: black;"><fmt:formatDate pattern="MM/dd/yyyy hh a" value="${order.orderHistory[fn:length(order.orderHistory)-1].createdOn}"/></span> <br>${order.orderHistory[fn:length(order.orderHistory)-1].comments}</div>
                                        <div class="col-sm-6 portalcomt" style="display: ${sessionBean.pharmacy eq null?'none':'block'}">
                                            <c:choose>
                                                <c:when test="${order.orderStatus.id eq 5}">
                                                    <form:textarea id="comments" path="orderHistory[${fn:length(order.orderHistory)-1}].comments" cssClass="form-control" disabled="${order.orderStatus.id eq 4 || order.orderStatus.id == 1 || order.orderStatus.id eq 5}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <form:textarea id="comments" path="orderHistory[2].comments" cssClass="form-control" disabled="${order.orderStatus.id eq 4 || order.orderStatus.id == 1 || order.orderStatus.id eq 5}" placeholder="Comments"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="col-sm-6 potalimage portal-top" style="display: ${sessionBean.pharmacy eq null?'none':'block'}">
                                            <c:if test="${sessionBean.pharmacy !=null}">
                                                <div class="fileinput fileinput-new " data-provides="fileinput" style="${order.orderStatus.id != 1?"display:block;":"display:none;"}">
                                                    <span class="btndrop btn-primary btn-file" style="background: #2071b6; color: white;"><span>Compound Image</span><form:input path="logo" type="file" readonly="${order.orderStatus.id eq 4 || order.orderStatus.id eq 5}"/></span>
                                                    <span class="fileinput-filename"></span><span class="fileinput-new">No Image</span>
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="col-sm-6 portaltrack portal-top portal-bottom" style="display: ${sessionBean.pharmacy eq null?'none':'block'}">
                                            <form:input cssClass="form-control" path="orderTrackingNo" readonly="${order.orderStatus.id == 1}" onkeypress="return IsAlphaNumeric(event)" disabled="true" maxlength="10" placeholder="Tracking #."/>
                                        </div>
                                    </div>
                                </div>

                            </div>


                        </div>

                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" align="right" style="top: -15px;">
                            <div class="col-lg-6 hidden-sm hidden-xs hidden-md">&nbsp;</div>
                            <div class="col-lg-3 col-sm-6 col-md-6 col-xs-12 pharmacy-portalmsg">
                                <div id="message" style="color:${not empty message?'#067512;':'#E70000'};font-size: 14px;" class="portalmsg"><c:if test="${not empty message}">${message}</c:if><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                                </div>


                                <div class="col-lg-3 col-sm-12 col-md-12 col-xs-12" style="left:12px;margin-bottom: 40px;" id="actionBtnPanel">
                                    <div class="btndrop btn-margin" style="display:inline;background:#2071b6;" onclick="location.href = '${pageContext.request.contextPath}/order/list'"><a href="${pageContext.request.contextPath}/order/list" style="color: white;">Back</a></div>
                                    <c:if test="${sessionBean.pharmacy !=null}">
                                        <div class="btn-group" style="display: ${order.orderStatus.id eq 2 ? 'inline':'none'};padding-bottom: 3px;">
                                            <div id="btnRejected" class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/order/update/${order.id}/1'" style="background:#2071b6;"><a href="${pageContext.request.contextPath}/order/update/${order.id}/1" class="btn_Color" style="color: white;">Rejected</a></div>
                                        </div>


                                        <div class="btn-group" style="display: ${order.orderStatus.id eq 1?'inline':'none'};padding-bottom: 3px;">
                                            <div id="btnAccepted" class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/order/update/${order.id}/2'" style="background:#2071b6;"><a href="${pageContext.request.contextPath}/order/update/${order.id}/2" class="btn_Color" style="color: white;">Accepted</a></div>
                                        </div>

                                        <div class="btn-group" style="display: ${order.orderStatus.id eq 1 || order.orderStatus.id eq 4 || order.orderStatus.id eq 5?'none':'inline'};padding-bottom: 3px;">

                                            <div class="btndrop btn-margin" style="background:#2071b6;"><a class="btn_Color" style="color: white;" onclick="$('#order').submit();">Save</a></div>
                                        </div>
                                    </c:if>
                                </div>

                            </div>
                        </div>
                    </div>
            </form:form>

            <script type="text/javascript">
                
            </script>
        </div> <!-- /content -->
        <jsp:include page="./inc/footer.jsp" />
    </div> <!-- /wrapper -->
</body>
</html>
