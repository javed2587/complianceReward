<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Survey Setup
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Surveys</h1>
                </div> <!-- /header -->
                <form:form action="add" commandName="survey">
                    <div class="page-message messageheading" style="padding-top:3px;">  
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>
                    <c:if test="${sessionBean.pmap[(33).intValue()].manage eq true}">
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;"> 
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px; padding-top: 2px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                            <div style="margin-top: 1px;" class="btndrop addbtn" onclick="location.href = '${pageContext.request.contextPath}/survey/add'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/survey/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                            </div>
                        </c:if>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px; padding-top: 5px;"> 
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
                                                <th class="">
                                                    Survey Title
                                                </th>
                                                <th class=" " style="text-align:center">
                                                    No. of Questions
                                                </th>
                                                <th class="" style="text-align:center">
                                                    Status
                                                </th>

                                                <th class="" style="text-align:center; padding-left: 50px;">
                                                    Action 
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${surveyList}">
                                                <tr class="row grid-row">
                                                    <td class="">
                                                        <span id="tbl_textalign">${list.title}</span>
                                                    </td>
                                                    <td class="" style="text-align:center">
                                                        <span id="tbl_textalign">${list.noOfQuestions}</span>   
                                                    </td>
                                                    <td class=""  style="text-align:center">
                                                        <span id="tbl_textalign" style="color: ${list.status eq 'Active'? 'green' : 'red'}">${list.status}</span>   
                                                    </td>

                                                    <td class="" text-right padd-left-7-ipad">
                                                        <span id="tbl_textalign" style="padding-left: 50px;">
                                                            <a class="${sessionBean.pmap[(33).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/survey/edit/${list.id}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>
                                                            <a class="${sessionBean.pmap[(33).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeleteRecord('survey/delete/${list.id}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>

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
