<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <head>

    </head>
    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Additional Margin Setup

                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Drug Additional Margin Setup</h1>

                </div> <!-- /header -->
                <form:form action="add" commandName="drugadditionalmargin">
                    <div class="page-message" style="padding-left: 15px; padding-top: -5px;">  
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 17px;"> 
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                            <div style="margin-top: 1px;" class="btndrop addbtn" onclick="location.href = '${pageContext.request.contextPath}/drugAdditionalMargin/add'">
                            <a class="btn_Color" href="${pageContext.request.contextPath}/drugAdditionalMargin/add">Add New</a>&nbsp;&nbsp;<span style="color: white;"><i class="fa fa-plus"></i></span>
                        </div>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px; padding-top: 5px;">
                            <div class="contents">                                   
                                <div class="table-box table-responsive">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">  
                                                <th class="width20-ipad" style="padding-left: 9px;">
                                                    <span class="tbl_f">Drug Category&nbsp;</span>
                                                </th>
                                                <th class="hidden-xs pswidth-1200">
                                                    <span class="tbl_f">Qty 1-30</span>
                                                </th>
                                                <th class="hidden-xs pswidth-1200 width20-ipad">
                                                    <span class="tbl_f">Qty 31-60</span>
                                                </th>
                                                <th class="hidden-sm hidden-xs wacPkgWidth-1200 hidden-md">
                                                    <span class="tbl_f">Qty 61-100</span>
                                                </th>
                                                <th class=" hidden-xs pswidth-1200">
                                                    <span class="tbl_f">Qty 101-180</span>
                                                </th>
                                                <th class="hidden-xs width20-ipad">
                                                    <span class="tbl_f">Qty 181+</span>
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
                                                        <span id="tbl_textalign">${row.drugCategory.drugCategoryName}</span>
                                                    </td>

                                                    <td class="hidden-xs pswidth-1200" style="position: relative; right: 7px;">
                                                        <c:forEach var="qty130" items="${row.drugAdditionalMarginPricesList}">
                                                            <span id="tbl_textalign">
                                                                <c:if test="${qty130.drugQtyFrom eq 1 && qty130.drugQtyTo eq 30}">
                                                                    $<fmt:formatNumber value="${qty130.drugMarginValue}" pattern="0.0000"/>
                                                                </c:if>
                                                            </span>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="hidden-xs pswidth-1200 width20-ipad" style="position: relative; right: 7px;">
                                                        <c:forEach var="qty31to60" items="${row.drugAdditionalMarginPricesList}">
                                                            <span id="tbl_textalign">
                                                                <c:if test="${qty31to60.drugQtyFrom eq 31 && qty31to60.drugQtyTo eq 60}">
                                                                    $<fmt:formatNumber value="${qty31to60.drugMarginValue}" pattern="0.0000"/>
                                                                </c:if>
                                                            </span>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="hidden-sm hidden-xs wacPkgWidth-1200 hidden-md" style="position: relative; right: 7px;">
                                                        <c:forEach var="qty61to100" items="${row.drugAdditionalMarginPricesList}">
                                                            <span id="tbl_textalign">
                                                                <c:if test="${qty61to100.drugQtyFrom eq 61 && qty61to100.drugQtyTo eq 100}">
                                                                    $<fmt:formatNumber value="${qty61to100.drugMarginValue}" pattern="0.0000"/>
                                                                </c:if>
                                                            </span>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="hidden-xs pswidth-1200" style="position: relative; right: 7px;">
                                                        <c:forEach var="qty101to180" items="${row.drugAdditionalMarginPricesList}">
                                                            <span id="tbl_textalign">
                                                                <c:if test="${qty101to180.drugQtyFrom eq 101 && qty101to180.drugQtyTo eq 180}">
                                                                    $<fmt:formatNumber value="${qty101to180.drugMarginValue}" pattern="0.0000"/>
                                                                </c:if>
                                                            </span>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="hidden-xs width20-ipad" style="position: relative; right: 7px;">
                                                        <c:forEach var="qty181" items="${row.drugAdditionalMarginPricesList}">
                                                            <span id="tbl_textalign">
                                                                <c:if test="${qty181.drugQtyFrom ge 181}">
                                                                    $<fmt:formatNumber value="${qty181.drugMarginValue}" pattern="0.0000"/>
                                                                </c:if>
                                                            </span>
                                                        </c:forEach>
                                                    </td>
                                                    <td style="text-align: center;position: relative;right: 10px;">
                                                        <a  href="${pageContext.request.contextPath}/drugAdditionalMargin/edit/${row.id}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>&nbsp;
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
                    </form:form>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

    </body>
</html>

