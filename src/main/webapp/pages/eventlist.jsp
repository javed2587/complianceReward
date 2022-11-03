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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Event Setup

                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Events</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="brand">
                    <div class="page-message messageheading" style="padding-top: 3px;">
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px">
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                        <c:if test="${sessionBean.pmap[(11).intValue()].manage eq true}">
                            <div class="btndrop" style="float: right;margin-top: 1px;" onclick="location.href = '${pageContext.request.contextPath}/event/edit/0'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/event/edit/0">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                            </div>  
                        </c:if>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px; padding-top: 5px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box table-responsive">
                                    <table id="example" class="display" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
                                                <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                                    <span class="tbl_f">Event Title&nbsp;</span>
                                                </th>
                                                <th class="col-lg-3 col-md-3 col-sm-3 col-xs-3 hidden-xs">
                                                    <span class="tbl_f">Criteria&nbsp;</span> 
                                                </th>
                                                <th class="col-lg-3 col-md-3 col-sm-3 col-xs-3 hidden-xs">
                                                    <span class="tbl_f">Value</span> 
                                                </th>
                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center text-left-ipad" style="padding-right:20px;">
                                                    <span class="tbl_f">&nbsp;&nbsp;Action</span> 
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="event" items="${eventlist}">
                                                <tr class="row grid-row">
                                                    <td class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                                        <span id="tbl_textalign">${event.eventTitle}
                                                    </td>

                                                    <td class="col-lg-3 col-md-3 col-sm-3 col-xs-3 hidden-xs">    

                                                        ${event.eventCriteria}
                                                    </td>
                                                    <td class="col-lg-3 col-md-3 col-sm-3 col-xs-3 hidden-xs" >
                                                        <c:if test="${event.staticValue != NULL}">
                                                            <span id="tbl_textalign">${event.staticValue}</span>
                                                        </c:if>    
                                                        <c:if test="${event.dynamicValue != NULL}">
                                                            <span id="tbl_textalign">${event.dynamicValue}</span>   
                                                        </c:if>    </td>
                                                    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-2 text-right text-left-ipad padd-left-16-ipad">
                                                        <span id="tbl_textalign">
                                                            <a class="${sessionBean.pmap[(11).intValue()].manage eq true?'':'disabled'} " href="${pageContext.request.contextPath}/event/edit/${event.eventId}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>&nbsp;
                                                            <a class="${sessionBean.pmap[(11).intValue()].manage eq true?'':'disabled'} "href="#" onclick="isDeleteRecord('event/delete/${event.eventId}')" style="text-align:right;"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>

                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>


                                </div>
                            </div>
                        </div>
                             <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none; padding-top: 25px !important;"></div>
                    </form:form>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

    </body>
</html>
