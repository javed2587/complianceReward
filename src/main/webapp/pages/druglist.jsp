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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Compound Setup

                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Compounds</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="drugBrand">
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                        <c:if test="${sessionBean.pmap[(8).intValue()].manage eq true}">
                            <div style="margin-top: 1px;" class="btndrop addbtn" onclick="location.href = '${pageContext.request.contextPath}/drugBrand/add'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/drugBrand/add">Add New</a>&nbsp;&nbsp;<span style="color: white;"><i class="fa fa-plus"></i></span>
                            </div>
                        </c:if>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px; padding-top: 5px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">  
                                                <th class="col-lg-2 col-md-4 col-sm-4 col-xs-1 hidden-xs width20-ipad">
                                                    <span class="tbl_f">Compound Title&nbsp;</span>
                                                </th>
                                                <th class="col-lg-1 col-md-2 col-sm-2 col-xs-1 hidden-xs pswidth-1200">
                                                    <span class="tbl_f">Packing Size</span>
                                                </th>
                                                <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-xs pswidth-1200 width20-ipad">
                                                    <span class="tbl_f">NDC Number</span>
                                                </th>
                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden-sm hidden-xs wacPkgWidth-1200 hidden-md">
                                                    <span class="tbl_f">WAC Price($)</span>
                                                </th>
                                                <th class="col-lg-1 col-md-2 col-sm-2 col-xs-1 hidden-xs pswidth-1200">
                                                    <span class="tbl_f">AWP Price($)</span>
                                                </th>
                                                <th class="col-lg-4 col-md-3 col-sm-3 col-xs-3 hidden-xs width20-ipad">
                                                    <span class="tbl_f">Max Offer($)</span>
                                                </th>
                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-2 text-center text-left-ipad" style="padding-right: 20px;">
                                                    <span class="tbl_f">&nbsp;&nbsp;Action</span> 
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${druglist}">
                                                <tr class="row grid-row odd">
                                                    <td class="col-lg-2 col-md-4 col-sm-4 col-xs-11 hidden-xs width20-ipad" valign="top">
                                                        <span id="tbl_textalign"></span>
                                                    </td>

                                                    <td class="col-lg-1 col-md-2 col-sm-2 col-xs-1 hidden-xs pswidth-1200">
                                                       
                                                    </td>
                                                    <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-xs pswidth-1200 width20-ipad">
                                                      
                                                    </td>
                                                    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden-sm hidden-xs wacPkgWidth-1200 hidden-md">
                                                        
                                                    </td>
                                                    <td class="col-lg-1 col-md-2 col-sm-2 col-xs-1 hidden-xs pswidth-1200">
                                                       
                                                    </td>
                                                    <td class="col-lg-4 col-md-3 col-sm-3 col-xs-3 hidden-xs width20-ipad">
                                                        
                                                    </td>
                                                    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-2 text-right text-left-ipad padd-left-16-ipad" valign="top">
                                                        <span id="tbl_textalign">
                                                            <a class="${sessionBean.pmap[(8).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/drugBrand/edit/${list.id}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>&nbsp;
                                                            <a class="${sessionBean.pmap[(8).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeleteRecord('drugBrand/delete/${list.id}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>

                                                        </span>
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

