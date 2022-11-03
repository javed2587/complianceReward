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
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Patient Profiles
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Patient Profiles</h1>
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
                            <div class="table-box table-responsive">
                                <table class="display" id="example" class="display table">
                                    <thead>
                                        <tr class="row grid-header">
                                            <th class="" style="padding-left: 10px;">
                                                <span class="tbl_f">Patient Name</span>
                                            </th>
                                            <th class="hidden-xs" style="padding-left: 10px;">
                                                <span class="tbl_f">DOB</span>
                                            </th>
                                            <th class="hidden-xs">
                                                <span class="tbl_f">Gender</span>
                                            </th>
                                            <th class="hidden-xs">
                                                <span class="tbl_f">Cardholder Relation</span>
                                            </th>
                                            <th class="hidden-xs">
                                                <span class="tbl_f">Mobile Phone</span>
                                            </th>
                                            <th class="hidden-xs">
                                                <span class="tbl_f">Updated On</span>
                                            </th>
                                            <th style="padding-left: 20px;">
                                                <span class="tbl_f">Status</span>
                                            </th>
                                            <th class="text-center text-left-ipad">
                                                <span class="">&nbsp;&nbsp;Action</span> 
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${patientlist}">
                                            <tr class="row grid-row">
                                                <td class="">
                                                    <span id="tbl_textalign">${list.firstName} ${list.lastName}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign"><fmt:formatDate pattern="MM/dd/yyyy" value="${list.dob}"/></span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign">${list.gender eq 'M'?'Male':'Female'}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign">${list.cardHolderRelation}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign">${list.mobileNumber}</span>
                                                </td>
                                                <td class="hidden-xs">
                                                    <span id="tbl_textalign"><fmt:formatDate pattern="s" value="${list.updatedOn}"/> ${list.updatedOn ne null?'Seconds':''}</span>
                                                </td>
                                                <td>
                                                    <span id="tbl_textalign" style="color:${list.status eq 'Pending'?'rgb(203,96,9)':list.status eq 'Validated'?'#067512':list.status eq 'OptedOut'?'#FF0DFC':'#e70000'};padding-left: 15px;">${list.status}</span>
                                                </td>
                                                <td class="" align="center">

                                                    <img class="${sessionBean.pmap[(57).intValue()].manage eq true?'':'disabled'}" src="${pageContext.request.contextPath}/resources/images/edit.jpg"  style="cursor:pointer;" width="20" onclick="location.href = '${pageContext.request.contextPath}/patient/view/${list.id}'"/>
                                                    <a class="${sessionBean.pmap[(3).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeleteRecord('patient/delete/${list.id}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                </div>
                <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

    </body>
</html>
