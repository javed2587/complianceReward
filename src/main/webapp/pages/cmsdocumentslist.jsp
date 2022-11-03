<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Documents Setup

                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Documents</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="cMSDocuments">
                    <div class="page-message messageheading">  
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;"> 
                            <div style="margin-top: 1px;" class="btndrop addbtn" onclick="location.href = '${pageContext.request.contextPath}/cMSDocuments/add'">
                            <a class="btn_Color" href="${pageContext.request.contextPath}/cMSDocuments/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                        </div>  
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px;"> 
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
                                                <th class="col-lg-6 col-md-6 col-sm-6 col-xs-10">
                                                    <span class="tbl_f">Title</span>
                                                </th>
                                                <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5 hidden-xs">
                                                    <span class="tbl_f">Type</span>
                                                </th>
                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-2 text-center text-left-ipad">
                                                    <span class="tbl_f">&nbsp;&nbsp;Action</span> 
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${documentslist}">
                                                <tr class="row grid-row">
                                                    <td class="col-lg-6 col-md-6 col-sm-6 col-xs-10">
                                                        <span id="tbl_textalign">${list.title}</span>
                                                    </td>
                                                    <td class="col-lg-5 col-md-5 col-sm-5 col-xs-5 hidden-xs">

                                                        <c:if test="${list.dType eq 'word'}">
                                                            ${list.dType} &nbsp;<i class="fa fa-file-word-o"></i>
                                                        </c:if>
                                                        <c:if test="${list.dType eq 'excel'}">
                                                            ${list.dType} &nbsp;<i class="fa fa-file-excel-o"></i>
                                                        </c:if>
                                                        <c:if test="${list.dType eq 'pdf'}">
                                                            ${list.dType} &nbsp;
                                                        </c:if>
                                                        <c:if test="${list.dType eq 'ppt'}">
                                                            ${list.dType} &nbsp;<i class="fa fa-file-powerpoint-o"></i>
                                                        </c:if>
                                                        <c:if test="${list.dType eq 'csv'}">
                                                            ${list.dType} &nbsp;<i class="fa fa-file-image-o"></i>
                                                        </c:if>
                                                    </td>
                                                    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-2 text-right text-left-ipad padd-left-16-ipad">
                                                        <span id="tbl_textalign">
                                                            <div class="btn-group">
                                                                <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-eye">&nbsp;&nbsp;<span class="caret"></span></i></a>
                                                                <ul class="dropdown-menu">
                                                                    <li><a href="${pageContext.request.contextPath}/cMSDocuments/edit/${list.id}">Edit</a></li> 
                                                                    <li><a href="${pageContext.request.contextPath}/cMSDocuments/delete/${list.id}" onclick="return confirm('Are you sure, you want to delete this record?')">Delete</a></li>
                                                                </ul>
                                                            </div>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>


                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

    </body>
</html>

