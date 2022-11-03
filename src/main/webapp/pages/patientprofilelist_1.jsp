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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Patient Profiles
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Registered Application Users</h1>
                </div> <!-- /header -->
                <div class="page-message messageheading" style="padding-top: 0px;">
                    <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                    <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                    </div>

                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">
                        <div class="col-lg-10 col-md-9 col-sm-9 col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                            <input autocomplete="off" type="text" class="form-control" id="search" placeholder="Search" name="searchTitle_1" >
                        </div>
                        <br><br>
                        <div class="tab-content" style="height: auto;border-top: 0px;margin-bottom:55px;padding-top: 4px;">  
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                        <div role="tabpanel" class="tab-pane active">                                   
                            <div  class="table-responsive">
                                <table id="patientTb" class="display table" border="1">
                                    <thead>
                                        <tr class="row grid-header">
                                            <th width="16%">
                                                Patient Name
                                            </th>
                                            <th  width="10%">
                                                DOB
                                            </th>
                                            <th width="5%">
                                                Gender
                                            </th>
                                            <th width="5%">
                                                Cardholder Relation
                                            </th>
                                            <th width="5%">
                                                Mobile
                                            </th>
                                            <th width="20%">
                                                Email
                                            </th>
                                            <th width="2%">
                                                OS
                                            </th>
                                            <th width="4%">
                                                State
                                            </th>
                                            <th width="13%">
                                                Reg. Date
                                            </th>
                                            <th width="12%">
                                                Last Updated
                                            </th>

                                            <th width="4%">
                                                Status
                                            </th>
                                            <th  width="4%">
                                                &nbsp;&nbsp;Action 
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${patientlist}">
                                            <tr class="row grid-row" >
                                                <td width="16%">
                                                    <a class="tbl_f" href="#" onclick="location.href = '${pageContext.request.contextPath}/patient/detail/${list.id}'">
                                                        <strong> <u>  ${list.firstName} ${list.lastName}</u></strong></a>
                                                </td>
                                                <td width="10%">
                                                    <span id="tbl_textalign"><fmt:formatDate pattern="MM/dd/yyyy" value="${list.birthDate}"/></span>
                                                </td>
                                                <td width="5%">
                                                    <span id="tbl_textalign">${list.gender eq 'M'?'Male':'Female'}</span>
                                                </td>
                                                <td width="5%">
                                                    <span id="tbl_textalign">
                                                        <c:choose>
                                                            <c:when test="${not empty list.cardHolderRelation}">
                                                                ${list.cardHolderRelation}
                                                            </c:when>
                                                            <c:otherwise>
                                                                NO INS ON FILE    
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </span>
                                                </td>
                                                <td width="5%">                                                   
                                                    <span id="tbl_textalign">${list.mobileNumber}</span>
                                                </td>
                                                <td width="20%">
                                                    <span id="tbl_textalign">${list.emailAddress}</span>
                                                </td>
                                                <td width="2%">
                                                    <span id="tbl_textalign">${list.osType eq 10?'IOS':'Android'}</span>
                                                </td>
                                                <td width="4%">
                                                    <span id="tbl_textalign">${list.state}</span>
                                                </td>
                                                <td width="13%">
                                                    <span id="tbl_textalign"><fmt:formatDate pattern="YYYY-MM-dd" value="${list.createdDate}"/></span>
                                                </td>
                                                <td width="12%">
                                                    <span id="tbl_textalign"><fmt:formatDate pattern="MM-dd-yy @ hh:mm a" value="${list.updatedDate}"/></span>
                                                </td>

                                                <td width="4%">
                                                    <span id="tbl_textalign" style="color:${list.status eq 'Pending'?'rgb(203,96,9)':list.status eq 'Validated'?'#067512':list.status eq 'OptedOut'?'#FF0DFC':'#e70000'};padding-left: 15px;">${list.status}</span>
                                                </td>
                                                <td width="4%"  align="center">

                                                    <img class="${sessionBean.pmap[(57).intValue()].manage eq true?'':'disabled'}" src="${pageContext.request.contextPath}/resources/images/edit.jpg"  style="cursor:pointer; "width="20" onclick="location.href = '${pageContext.request.contextPath}/patient/detail/${list.id}'"/>
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
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInfo.js"></script>
        <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            //alert("hi");
                                                            window.patientInfo.PatientTable();
                                                            $("#patientTb_filter").attr("style","display:none;");
                                                            window.patientInfo.populateAppUrl('${pageContext.request.contextPath}');
                                                            $("#search").keyup(function () {
                                                                var v = $('#patientTb').dataTable().fnFilter(this.value);
                                                                if (typeof v === 'undefined' && this.value !== "") {
                                                                    //$(".dataTables_empty").attr("colspan", 13);
                                                                }
                                                            });
                                                        });

        </script>
    </body>
</html>
