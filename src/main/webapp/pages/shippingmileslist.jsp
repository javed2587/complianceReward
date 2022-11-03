<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" />Shipping Miles Setup
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i> Shipping Miles Setup&nbsp;</h1>
                </div> <!-- /header -->
                <div class="page-message" style="padding-left: 15px; padding-top: -5px;">  
                    <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                    <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                    </div>
                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 17px;"> 
                        <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                            <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                        </div>
                        <div style="margin-top: 1px;" class="btndrop addbtn" onclick="location.href = '${pageContext.request.contextPath}/shippingMiles/add'">
                        <a class="btn_Color" href="${pageContext.request.contextPath}/shippingMiles/add">Add New</a>&nbsp;&nbsp;<span style="color: white;"><i class="fa fa-plus"></i></span>
                    </div>
                    <br><br>
                    <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px; padding-top: 5px;">
                        <div class="contents">                                   
                            <div class="table-box">
                                <table class="display" id="example" class="display table">
                                    <thead>
                                        <tr class="row grid-header">  
                                            <th class="width20-ipad" style="padding-left: 9px;">
                                                <span class="tbl_f">Pharmacy&nbsp;</span>
                                            </th>
                                            <th class="hidden-xs pswidth-1200">
                                                <span class="tbl_f">Zip Code</span>
                                            </th>
                                            <th class="hidden-xs pswidth-1200">
                                                <span class="tbl_f">Miles</span>
                                            </th>
                                            <th class="hidden-xs pswidth-1200">
                                                <span class="tbl_f">Fees</span>
                                            </th>
                                            <th class="hidden-xs" style="text-align: center;">
                                                <span class="tbl_f">Action</span>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${list}">
                                            <tr class="row grid-row odd">
                                                <td class="hidden-xs width20-ipad" valign="top">
                                                    <span id="tbl_textalign">${row.pharmacyName}</span>
                                                </td>
                                                <td class="hidden-xs width20-ipad" valign="top">
                                                    <span id="tbl_textalign">${row.pharmacyZip}</span>
                                                </td>
                                                <td class="hidden-xs pswidth-1200" style="position: relative; right: 7px;">
                                                    <c:forEach var="miles" items="${row.deliveryDistanceFeesList}" varStatus="milesLoop">
                                                        ${miles.deliveryDistances.distanceFrom} - ${miles.deliveryDistances.distanceTo} <c:if test="${!milesLoop.last}"></br></c:if>
                                                    </c:forEach>
                                                </td>
                                                <td class="hidden-xs pswidth-1200 width20-ipad" style="position: relative; right: 7px;">
                                                    <c:forEach var="fees" items="${row.deliveryDistanceFeesList}" varStatus="feesLoop">
                                                        ${fees.deliveryFee} <c:if test="${!feesLoop.last}"></br></c:if>
                                                    </c:forEach>
                                                </td>
                                                <td style="text-align: center;position: relative;right: 10px;">
                                                    <a  href="${pageContext.request.contextPath}/shippingMiles/edit/${row.id}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>&nbsp;
                                                   <!--     <a  href="#"  onclick="isDeleteRecord('drugAdditionalMargin/delete/${row.id}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>-->
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
    </body>
</html>

