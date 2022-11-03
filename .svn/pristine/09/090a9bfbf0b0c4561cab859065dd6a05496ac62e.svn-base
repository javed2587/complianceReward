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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Rx Transfer Request
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Rx Transfer</h1>
                </div> <!-- /header -->
                <div class="page-message messageheading" style="padding-top: 0px;">
                    <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                    <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                    </div>

                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">
                        <div class="col-lg-10 col-md-9 col-sm-9 col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                            <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                        </div>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px;padding-top: 4px;">  
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                        <div class="contents">                                   
                            <div class="table-box">
                                <table class="display" id="example" class="display table">
                                    <thead>
                                        <tr class="row grid-header">
                                            <th class="" style="padding-left:10px;">
                                                <span class="tbl_f">Patient Name</span>
                                            </th>
                                            <th class="hidden-xs" style="padding-left:10px;">
                                                <span class="tbl_f">Primary Phone</span>
                                            </th>
                                            <th class="hidden-xs" style="padding-left:10px;">
                                                <span class="tbl_f">Pharmacy</span>
                                            </th>
                                            <th class="hidden-xs" style="padding-left:10px;">
                                                <span class="tbl_f">Pharmacy Phone</span>
                                            </th>
                                            <th class="hidden-xs" style="padding-left:10px;">
                                                <span class="tbl_f">Rx #</span>
                                            </th>
                                            <th class="hidden-xs" style="padding-left:10px;">
                                                <span class="tbl_f">Drug</span>
                                            </th>
                                            <th class="">
                                                <span class="tbl_f">Qty Desired</span>
                                            </th>
                                            <th style="padding-left:10px;">
                                                <span class="tbl_f">Requested On</span>
                                            </th>
                                            <th style="padding-left:8px;">
                                                <span class="tbl_f">Status</span>
                                            </th>
                                            <th style="padding-left:8px;">
                                                <span class="tbl_f">Type</span>
                                            </th>
                                            <th class="text-center text-left-ipad">
                                                <span class="">&nbsp;&nbsp;Action</span> 
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${transferRequestList}">
                                            <tr class="row grid-row">
                                                <td class="">
                                                    <span id="tbl_textalign">${row.patientName}</span>
                                                </td>
                                                <td class="hidden-xs" >
                                                    <span id="tbl_textalign">${row.pharmacyPhone}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign">${row.pharmacyName}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign">${row.pharmacyPhone}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign">${row.rxNumber}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign">${row.drug}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span>${row.quantity}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign"><fmt:formatDate var="requestOn" pattern="MM/dd/yyyy HH:mm" value="${row.requestedOn}"/>${requestOn}</span>
                                                </td>
                                                <td>
                                                    <c:if test="${row.transferDetail eq null || row.transferDetail.status eq 'Pending' || row.transferDetail.status eq 'Transfered'}">
                                                        <span id="tbl_textalign">${row.transferDetail.status eq 'Transfered'?row.transferDetail.status:'Pending'}</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${row.isOrder eq null || row.isOrder eq '0'}">
                                                            <span>Transfer</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span>Order</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    
                                                </td>
                                                <td class="tbl_f" align="center" style="cursor: pointer">
                                                    <img class="${sessionBean.pmap[(57).intValue()].manage eq true?'':'disabled'}" src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20" onclick="location.href = '${pageContext.request.contextPath}/rxTransfer/view/${row.id}'"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

    </body>
</html>

