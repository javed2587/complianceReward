<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./portal/inc/head.jsp" />

    <body>
        <jsp:include page="./portal/inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                
<!--                <div class="${sessionBean.pharmacy eq null?'breadcrumbs':'pharmacybreadcrumbs'}">
                    <div style="display: ${sessionBean.pharmacy eq null?'block':'none'}"><i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> View Orders</div>
                    <div class="col-xs-12" style="display: ${sessionBean.pharmacy ne null?'block':'none'}">Rx Alert Contact: <span style="font-weight: normal;">${sessionBean.pharmacy.contactPerson}, ${sessionBean.pharmacy.personEmail}, ${sessionBean.pharmacy.personMobileNo}, ${sessionBean.pharmacy.personOfficePhoneNo}</span></div>
                </div>-->
             <h3>Selected TransferRx</h3>
             <div class="wrp clearfix padd-top-zero" style="padding-left: 15px;padding-right: 15px;">
                    <div id="message" class="message" style="${message ne null?'padding-bottom: 6px;':'0px;'}"><c:if test="${not empty message}">${message}</c:if></div>

                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px;margin-top: 20px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                        <div class="contents">                                   
                            <div class="table-box">
                 <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">  
                                                <th class="col-lg-2 col-md-2 col-sm-4 col-xs-6 sorting">
                                                    Received Date
                                                </th>
                                                <th class="col-lg-1 col-md-2 col-sm-4 col-xs-6 sorting">
                                                   Medication Name
                                                </th>
                                                 <th class="col-lg-2 col-md-2 col-sm-4 col-xs-6 sorting">
                                                   Quantity
                                                </th>
                                                <th class="col-lg-1 col-md-2 col-sm-4 col-xs-6 sorting">
                                                  Pharmacy Name
                                                </th>
                                                 <th class="col-lg-2 col-md-2 col-sm-4 col-xs-6 sorting">
                                                    Pharmacy Phone
                                                </th>
                                                
                                                <th class="col-lg-2 col-md-2 col-sm-4 col-xs-6 sorting">
                                                    Rx Number
                                                </th>
                                                 <th class="col-lg-2 col-md-2 col-sm-4 col-xs-6 sorting">
                                                    Cash Price Quoted
                                                </th>
                                                <th class="col-lg-1 col-md-2 col-sm-4 col-xs-6 sorting">
                                                   Pic Video
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="row" items="${selectedTransferRxList}">
                                               <tr class="row grid-row">
                                                    <td class="">
                                                       ${row.requestedOn}
                                                    </td>
                                                    <td class="">
                                                        ${row.drug}
                                                    </td>
                                                    
                                                    <td class="">
                                                       ${row.quantity}
                                                    </td>
                                                    <td class="">
                                                        ${row.pharmacyName}
                                                    </td>
                                                    <td class="">
                                                       ${row.pharmacyPhone}
                                                    </td>
                                                    <td class="">
                                                        ${row.rxNumber}
                                                    </td>
                                                    <td class="">
                                                     
                                                    </td>
                                                    <td class="">
                                                        ${row.video}
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
        </div> <!-- /content -->
            <jsp:include page="./portal/inc/footer.jsp" />
        </div> <!-- /wrapper -->

    </body>
</html>

