<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />  
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Email Template Setup

                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Email Templates</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="surveyEmailTemplate">
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>

                        </div>   
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                        <c:if test="${sessionBean.pmap[(32).intValue()].manage eq true}">
                            <div class="btndrop" style="float: right;margin-top: 1px;" onclick="location.href = '${pageContext.request.contextPath}/surveyEmailTemplate/add'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/surveyEmailTemplate/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                            </div>
                        </c:if>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead style="height:38px;">
                                            <tr class="row">  
                                                <th class="col-lg-11 col-md-11 col-sm-11 col-xs-11">
                                                    Template Title
                                                </th>

                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-left-ipad" style="text-align: center;">
                                                    &nbsp;  Action
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${surveyEmailTemplateList}">
                                                <tr class="row grid-row">
                                                    <td class="col-lg-11 col-md-11 col-sm-11 col-xs-11">
                                                        <span id="">${list.title}</span>
                                                    </td>
                                                    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1 padd-left-16-ipad text-left-ipad" style="text-align: right;">
                                                        <span id="tbl_textalign">
                                                            <div class="btn-group">
                                                                <a class="btn btn-primary dropdown-toggle ${sessionBean.pmap[(32).intValue()].manage eq true?'':'disabled'}" data-toggle="dropdown" href="#"><i class="fa fa-eye">&nbsp;&nbsp;<span class="caret"></span></i></a>
                                                                <ul class="dropdown-menu">
                                                                    <li><a href="${pageContext.request.contextPath}/surveyEmailTemplate/edit/${list.id}">Edit</a></li> 
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
