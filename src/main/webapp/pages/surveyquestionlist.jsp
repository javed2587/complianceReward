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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Question Setup

                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Questions</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="surveyQuestion">
                    <div class="page-message messageheading" style="padding-top:3px;">  
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;"> 
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                        <c:if test="${sessionBean.pmap[(31).intValue()].manage eq true}">
                            <div style="margin-top: 1px;" class="btndrop addbtn" onclick="location.href = '${pageContext.request.contextPath}/surveyQuestion/add'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/surveyQuestion/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                            </div> 
                        </c:if> 
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px; padding-top: 3px;"> 
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
                                                <th class="">
                                                    <span class="tbl_f">Question Title</span>
                                                </th>
                                                <th class="">
                                                    <span class="tbl_f">Response Type</span>
                                                </th>
                                                <th class="text-center text-left-ipad" style="padding-right: 20px;">
                                                    <span class="tbl_f">&nbsp;&nbsp;Action</span> 
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${surveyQuestionList}">
                                                <tr class="row grid-row"> 
                                                    <td class="">
                                                        <span id="tbl_textalign">${list.title}</span>
                                                    </td>
                                                    <td class="">
                                                        <span id="tbl_textalign">${list.surveyResponseType.title}</span>   
                                                    </td>
                                                    <td class=" text-right padd-left-16-ipad text-left-ipad" style="text-align: center;">
                                                        <span id="tbl_textalign">
                                                             <a class="${sessionBean.pmap[(31).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/surveyQuestion/edit/${list.id}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>
                                                             <a class="${sessionBean.pmap[(31).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeleteRecord('surveyQuestion/delete/${list.id}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>

                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>


                                </div>
                            </div>
                        </div>
                            <div id="dialog" style="height: 500px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
                    </form:form>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

    </body>
</html>
