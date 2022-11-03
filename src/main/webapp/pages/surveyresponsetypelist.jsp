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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Response Type Setup 
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Response Types</h1>
                </div> <!-- /header -->
                <form:form action="add" commandName="surveyResponseType">
                    <div class="page-message messageheading" style="padding-top:3px;">
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>   
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                        <c:if test="${sessionBean.pmap[(29).intValue()].manage eq true}">
                            <div class="btndrop" style="float: right;margin-top: 1px;" onclick="location.href = '${pageContext.request.contextPath}/surveyResponseType/add'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/surveyResponseType/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                            </div>
                        </c:if>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px; padding-top: 5px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead style="height:36px;">
                                            <tr class="row">  
                                                <th class="col-lg-11 col-md-11 col-sm-11 col-xs-11">
                                                    Response Type(s)
                                                </th>
                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-left-ipad" style="text-align: center; padding-right: 20px;">
                                                    &nbsp;  Action
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${surveyResponseTypeList}">
                                                <tr class="row grid-row">
                                                    <td class="col-lg-11 col-md-11 col-sm-11 col-xs-11">
                                                        <span id="">${list.title}</span>
                                                    </td>
                                                    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-left-ipad padd-left-16-ipad" style="text-align: center;">
                                                        <span id="tbl_textalign">
                                                            <a class="${sessionBean.pmap[(29).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/surveyResponseType/edit/${list.id}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>
                                                            <a class="${sessionBean.pmap[(29).intValue()].manage eq true?'':'disabled'}" href="#"onclick="isDeleteRecord('surveyResponseType/delete/${list.id}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>

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

