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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Widget Setup

                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Widgets</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="widgetUser">
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">
                             <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                        <c:if test="${sessionBean.pmap[(27).intValue()].manage eq true}">
                            <div style="margin-top: 1px;" class="btndrop addbtn" onclick="location.href = '${pageContext.request.contextPath}/widgetUser/add'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/widgetUser/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                            </div>
                        </c:if>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
                                                <th class="col-lg-3 col-md-3 col-sm-3 col-xs-10">
                                                    User Name
                                                </th>
                                                <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs">
                                                    Password
                                                </th>
                                                <th class="col-lg-2 col-md-2 col-sm-4 col-xs-2 hidden-xs">
                                                    Campaign Title
                                                </th>
                                                <th class="col-lg-4 col-md-4 col-sm-4 col-xs-4 hidden-sm hidden-xs">
                                                    IP Address
                                                </th>

                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-2 text-right">
                                                    <span style="margin-right: 25px;">Action</span>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${widgetuserlist}">
                                                <tr class="row grid-row">
                                                    <td class="col-lg-3 col-md-3 col-sm-3 col-xs-10" valign="middle">
                                                        <span id="tbl_textalign">${list.userName}</span>
                                                    </td>
                                                    <td class="hidden-sm hidden-xs" valign="middle">
                                                        <span id="tbl_textalign">${list.password}</span>
                                                    </td>
                                                    <td class="col-sm-4 hidden-xs" valign="middle">
                                                        <span id="tbl_textalign">${list.campaignName}</span>
                                                    </td>
                                                    <td class="hidden-sm hidden-xs" valign="middle">    
                                                        <c:forEach var="widgetIp" items="${list.widgetUserIpaddresseses}" varStatus="widgetLoop">
                                                            ${widgetIp.ipAddress}<c:if test="${!widgetLoop.last}">,</c:if>
                                                        </c:forEach>

                                                    </td>
                                                    <!--                                                    <td valign="middle">
                                                    <c:if test="${list.isActive eq 'Yes'}">Active</c:if><c:if test="${list.isActive ne 'Yes'}">InActive</c:if>
                                                    </td>-->
                                                        <td class="text-right" class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
                                                           <a class="${sessionBean.pmap[(27).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/widgetUser/edit/${list.widgetUserId}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>
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
        <script type="text/javascript">
                                $(function() {
                                    // setTimeout() function will be fired after page is loaded
                                    // it will wait for 5 sec. and then will fire
                                    // $("#successMessage").hide() function
                                    setTimeout(function() {
                                        $("#errorMessage").hide('blind', {}, 500)
                                        $("#message").hide('blind', {}, 500)
                                    }, 5000);
                                });
        </script>
    </body>
</html>



