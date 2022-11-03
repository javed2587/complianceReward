<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="${sessionBean.pharmacy eq null?'breadcrumbs':'pharmacybreadcrumbs'}">
                    <div style="display: ${sessionBean.pharmacy eq null?'block':'none'}"><i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> View Orders</div>
                    <div class="col-xs-12" style="display: ${sessionBean.pharmacy ne null?'block':'none'}">Rx Alert Contact: <span style="font-weight: normal;">${sessionBean.pharmacy.contactPerson}, ${sessionBean.pharmacy.personEmail}, ${sessionBean.pharmacy.personMobileNo}, ${sessionBean.pharmacy.personOfficePhoneNo}</span></div>
                </div>
                <form:form action="${pageContext.request.contextPath}/order/list" commandName="order" role="form" method="Post">
                    <div class="col-sm-12" style="padding-left: 0px;padding-top: 15px;">
                        <div class="form-group margin-ive" style="display: ${sessionBean.pharmacy eq null?'block':'none'}">    
                            <div class="col-sm-3">
                                <label style="color: #444444" class="tbl_f">Pharmacy:<span style="color:red"></span></label>
                                    <form:select id="pharmacyId" path="pharmacyId" cssClass="form-control selectpicker show-tick">
                                        <c:if test="${sessionBean.getUserNameDB() ne 'viewOrder'}">
                                            <form:option value="0" label="Please Select" />
                                        </c:if>
                                        <form:options items="${pharmacyList}" itemValue="id" itemLabel="title"/>
                                    </form:select>
                                <span class="errorMessage">${emptyPharmacy}</span>
                            </div>
                        </div>
                        <div class="${sessionBean.pharmacy eq null?'form-group':'form-group margin-ive'}">    
                            <div class="${sessionBean.pharmacy eq null?'col-sm-3':'col-sm-4'}">
                                <label style="color: #444444" class="tbl_f">Order Status:</label>
                                <form:select id="orderStatus" path="orderStatusId" cssClass="form-control selectpicker show-tick">
                                    <form:options items="${orderStatusList}" itemValue="id" itemLabel="name"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">    
                            <div class="${sessionBean.pharmacy eq null?'col-sm-2':'col-sm-3'}" style="margin-bottom: 0px;">
                                <label style="color: #444444" class="tbl_f">From Date:</label>
                                <form:input id="datetimepicker" path="fromDate" cssClass="form-control" placeholder="MM/DD/YYYY" onkeyup="addSlashes(this)" onkeypress="return IsDigit(event)"/>
                                <span class="errorMessage">${fromToDate}</span>
                            </div>
                        </div>
                        <div class="form-group">    
                            <div class="${sessionBean.pharmacy eq null?'col-sm-2':'col-sm-3'}">
                                <label style="color: #444444" class="tbl_f">To Date:</label>
                                <form:input id="datetimepicker1" path="toDate" cssClass="form-control" placeholder="MM/DD/YYYY" onkeyup="addSlashes(this)" onkeypress="return IsDigit(event)"/>
                                <span class="errorMessage">${errorToDate}</span>
                            </div>
                        </div>
                        <div class="form-group">    
                            <div class="${sessionBean.pharmacy eq null?'col-sm-2':'col-sm-2'}" id="searchDiv">
                                <div class="margin-top-5" style="margin-top: 10px;float: right;padding-right: 6px;">
                                    <button class="btndrop" style="background:#2071b6; color: white;"><i class="fa fa-search"></i> Search</button>
                                </div>
                            </div>
                        </div>
                        <br clear="all">
                    </div>
                </form:form>
                <div class="wrp clearfix padd-top-zero" style="padding-left: 15px;padding-right: 15px;">
                    <div id="message" class="message" style="${message ne null?'padding-bottom: 6px;':'0px;'}"><c:if test="${not empty message}">${message}</c:if></div>

                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px;margin-top: 20px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                        <div class="contents">                                   
                            <div class="table-box">
                                <table class="display table" id="example" class="display">                                         
                                    <thead style="border-top:1px solid #cdcdce;height:35px;">
                                        <tr class="row grid-header">
                                            <th class="col-lg-2 col-md-2 col-sm-4 col-xs-6 sorting" >Pharmacy</th>
                                            <th class="col-lg-1 col-md-2 col-sm-4 col-xs-6 sorting" >Order #</th>
                                            <th class="col-lg-2 col-md-2 hidden-sm hidden-xs" >Rx #</th>
                                            <th class="col-lg-2 col-md-3 hidden-sm hidden-xs " >PT. Name</th>
                                            <th class="col-lg-2 col-md-2 col-sm-4 hidden-xs sorting">Order Date</th>   
                                            <th class="col-lg-2 col-md-2 col-sm-3 col-xs-5">Status Date</th>
                                            <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${list}">
                                            <tr class="row grid-row">
                                                <td>${row.pharmacy.title}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/order/detail/${row.id}" class="blueUnderline">${row.id}</a>
                                                </td>
                                                <td>${row.rxNo}</td>
                                                <td>
                                                    ${row.patientName} 
                                                </td>
                                                <td><fmt:formatDate pattern="MM/dd/yyyy HH:mm a" value="${row.createdOn}"/></td>
                                                <td>
                                                    <c:forEach var="history" items="${row.orderHistory}" varStatus="loop">
                                                        <c:if test="${loop.last && history.orderStatus.id eq row.orderStatus.id}">
                                                            <fmt:formatDate pattern="MM/dd/yyyy" value="${history.createdOn}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <c:if test="${row.orderStatus.id eq 1}">
                                                        <span class="unassignedstatus"> ${row.orderStatus.name}</span>
                                                    </c:if>
                                                    <c:if test="${row.orderStatus.id eq 2}">
                                                        <span class="assignstatus"> ${row.orderStatus.name}</span>
                                                    </c:if>
                                                    <c:if test="${row.orderStatus.id eq 3}">
                                                        <span class="fillstatus"> ${row.orderStatus.name}</span>
                                                    </c:if>
                                                    <c:if test="${row.orderStatus.id eq 4}">
                                                        <span class="shipstatus"> ${row.orderStatus.name}</span>
                                                    </c:if>
                                                    <c:if test="${row.orderStatus.id eq 5}">
                                                        <span class="deniedstatus">${row.orderStatus.name}</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- /content -->
        </div> <!-- /wrapper -->
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                format: 'm/d/Y',
                onChangeDateTime: function(dp, $input) {
                    jQuery('#datetimepicker').datetimepicker('hide');
                    jQuery('#datetimepicker1').datetimepicker('hide');
                }

            });
            function addSlashes(input) {
                var v = input.value;
                if (v.match(/^\d{2}$/) !== null) {
                    input.value = v + '/';
                } else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
                    input.value = v + '/';
                }
            }

        </script>
    </body>
</html>
