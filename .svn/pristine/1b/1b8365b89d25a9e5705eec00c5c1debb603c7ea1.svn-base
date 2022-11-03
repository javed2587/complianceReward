<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head2.jsp" />
    <body>
        <jsp:include page="./inc/header2.jsp" />
        <!---------------------------------NEW DESIGN-------------------------------------------------->
        <style>
            .wrapper{
                padding-left:15px;
                padding-right:15px;
                width:100%;
                max-width:100%;
            }
            .home_padding .head_top .wrapper {
                width: 100%;
            }
        </style>

        <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
        <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.birthDate}"/>
        <!--Registration Page-->

        <div class="">
            <div class="col-md-12">
                <div class="tableContainer patient_info row">
                    <div class="patient_name">
                        <h3>${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(patientProfile.lastName)} &nbsp;<span><fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.birthDate}"/> <span>(${patientProfile.gender})  ${nowyear-dobyear} yrs</span></span></h3>
                    </div>
                    <div class="patient_bar clearfix">
                        <ul>
                            <li class="dropdown">Email & Phone  <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down" aria-hidden="true"></i></a>
                                <ul class="dropdown-menu">
                                    <li> <span>MOBILE</span>
                                        ${fn:substring(patientProfile.mobileNumber, 0, 3)}.
                                        ${fn:substring(patientProfile.mobileNumber, 3, 6)}.
                                        ${fn:substring(patientProfile.mobileNumber, 6, 10)} <small>(BACK UP PHONE)</small> n/a </li>
                                    <li> <span>Email</span> ${not empty patientProfile.emailAddress?patientProfile.emailAddress:'&nbsp;'} </li>
                                </ul>
                            </li>
                            <li class="dropdown">ADDRESSES ON FILE <span>${fn:length(patientProfile.patientDeliveryAddresses)}</span> <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down" aria-hidden="true"></i></a>
                                <div class="dropdown-menu address_dropdown">
                                    <ul class="address_list">
                                        <c:forEach var="shippingAddressVar" items="${patientProfile.patientDeliveryAddresses}" varStatus="theCount">
                                            <li class="default"> <i>${theCount.count}</i>
                                                <h3><span>${shippingAddressVar.description}</span>${shippingAddressVar.address}, ${shippingAddressVar.apartment}, ${shippingAddressVar.state.name} <br />
                                                    ${shippingAddressVar.city},${shippingAddressVar.zip} </h3>
                                                <span>SPECIAL INSTRUCTIONS</span>
                                                <input type="text" value="" name="" class="form-control" />
                                            </li>

                                        </c:forEach>
                                    </ul>
                                    <div class="payment_cardinfo">
                                        <h3>PAYMENT CARD ON FILE <span>${fn:length(paymentInfoList)}</span></h3>
                                        <ul>
                                            <c:forEach var="payment" items="${paymentInfoList}" varStatus="theCount">
                                                <li> <i>${theCount.count}</i>
                                                    <h4>${payment.cardNumber}</h4>
                                                    <div class="col-sm-5 paymentino_col">
                                                        <h6>CARD HOLDER</h6>
                                                        <p>${payment.cardHolderName}</p>
                                                    </div>
                                                    <div class="col-sm-7 paymentino_col">
                                                        <h6>BILLING ADDRESS</h6>
                                                        <p>${payment.address}</p>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li class="dropdown">Allergies
                                <!--<input type="text" name="" 
                                       value="${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}" 
                                       disabled="true" class="form-control" />-->
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down" aria-hidden="true"></i></a>

                                <div class="dropdown-menu address_dropdown allergies_drp">
                                    <ul class="address_list"> 


                                        <li> 
                                            ${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}
                                        </li>



                                    </ul>
                                </div>

                            </li>
                            <li class="dropdown"><span>Dependents(${fn:length(patientProfile.patientProfileMembersList)})</span> <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down" aria-hidden="true"></i></a>
                                <div class="dropdown-menu address_dropdown">
                                    <ul class="address_list dependent_list">
                                        <c:forEach var="members" items="${patientProfile.patientProfileMembersList}" varStatus="theCount">
                                            <fmt:formatDate var="memberdobyear" pattern="yyyy" value="${members.birthDate}"/>
                                            <li> <i>${theCount.count}</i>
                                                <h3>${members.firstName}&nbsp;${members.lastName}
                                                    <span><fmt:formatDate pattern="MM/dd/yyyy" value="${members.birthDate}"/>   (${members.gender})  

                                                        ${nowyear-memberdobyear} yr </span></h3>
                                                <textarea class="form-control" placeholder="Allergies"></textarea>
                                            </li>

                                        </c:forEach>
                                    </ul>
                                </div>
                            </li>
                            <li class="dropdown">Insurance & Copay cards <span>(${lstInsuranceCardsSize}/${copayCardsSize})</span><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down" aria-hidden="true"></i></a>
                                <div class="dropdown-menu address_dropdown ins_cardlist">
                                    <ul class="address_list">
                                        <li class="clearfix"> 
                                            <c:forEach var="cards" items="${lstInsuranceCards}" varStatus="countCards">
                                                <c:if test="${cards.isPrimary eq 1}">
                                                    <span>Primary</span>
                                                </c:if>
                                                <div class="col-sm-6 card_colum">
                                                    <h6>Front</h6>

                                                    <img id="frontImage"
                                                         src="${cards.insuranceFrontCardPath}" alt="insurance card front" 
                                                         data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Insurance Card (Front) Click on image for full screen')"/></div>
                                                <div class="col-sm-6 card_colum">
                                                    <h6>BAck</h6> 
                                                    <img id="backImage" src="${cards.insuranceBackCardPath}" alt="insurance card back" data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Insurance Card (Back) Click on image for full screen')"/></div>
                                                </c:forEach>
                                        </li>
                                        <li class="clearfix">
                                            <h3>COPAY CARDS WITH THIS ORDER <span>${fn:length(copayCards)}</span></h3>
                                            <c:forEach var="copay" items="${copayCards}" varStatus="theCount">
                                                <div class="col-sm-6 card_colum">
                                                    <span>DRUG:${copay.drugBrand} </span>
                                                    <h6>Front</h6>

                                                    <img id="frontImage"
                                                         src="${copay.copayFrontCardPath}" alt="insurance card front" 
                                                         data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Copay Card (Front) Click on image for full screen')"/></div>
                                                <div class="col-sm-6 card_colum">
                                                    <h6>BAck</h6>

                                                    <img src="${copay.copayBackCardPath}" alt="insurance card back" data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Copay Card (Back) Click on image for full screen')"/> </div>

                                            </c:forEach>
                                        </li>
                                    </ul>

                                </div>
                            </li>
                            <li><span>${total}</span> Program Rx’s</li>
                            <li>Member Since <span><fmt:formatDate value='${patientProfile.createdDate}' pattern='yyyy-MM-dd'/></span></li>
                        </ul>
                        <span class="icon_Status"><fmt:formatNumber value="${rewardPoints.availablePoints}" pattern="#,###"  /><br />
                            Basic </span> 
                            <c:if test="${patientProfile.isOldPatient ne true}">
                            <span class="icon_unew"></span> 
                        </c:if>
                    </div>
                    <!--Top Nav ends-->
                    <!--Drug Modal-->
                    <jsp:include page="drugestimateprice.jsp" />
                    <jsp:include page="multidrugestimateprice.jsp" />
                    <!--/Drug Modal-->
                    <div class="dependent_detail_p">

                        <div class="order_details_h clearfix"><h2>Dependent Detail</h2></div>

                        <div class="dependant_table">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Received Date</th>
                                        <th>Dependent Name</th>
                                        <th>POA Expiry</th>
                                        <th>Rx Count</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="row" items="${careGiverList}">
                                        <tr>
                                            <td><span><fmt:formatDate var="receiveDate" pattern="yyyy-MM-dd" value="${row.createdDate}"/>${receiveDate}@<fmt:formatDate var="receiveTime" pattern="HH:mm:ss" value="${row.createdDate}"/>${receiveTime}</span></td>
                                            <td>${row.firstName} ${row.lastName}</td>
                                            <td>${row.expiryDate}</td>
                                            <td>${row.rxCount}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="col-sm-6 left_zero">
                            <h4>POA Front</h4>
                            <div class="dependant_image">
                                <img id="pic" src="${patientProfileMembers.frontPOAImage}" alt="" /><br>
                            </div>
                            <div class="clearfix col-sm-6" style="margin-left:12%;">
                                <input type="button" value ="-" onclick="zoompic(0.9)"style="width:50px"/>
                                <input type="button" value ="+" onclick="zoompic(1.1)"style="width:50px"/>
                            </div>
                        </div>
                        <div class="col-sm-6 right_zero">
                            <h4>POA Back</h4>
                            <div class="dependant_image">
                                <img id="pic2" src="${patientProfileMembers.backPOAImage}" alt=""  />
                            </div>
                            <div class="clearfix col-sm-6" style="margin-left:12%;">
                                <input type="button" value ="-" onclick="zoom(0.9)"style="width:50px"/>
                                <input type="button" value ="+" onclick="zoom(1.1)"style="width:50px"/>
                            </div>
                        </div>                       

                        <!--                        <div class="col-sm-6 left_zero">
                                                    <h4>POA Front</h4>
                                                    <div class="dependant_image">
                                                        <img id="pic" src="${patientProfileMembers.frontPOAImage}" alt=""  /><br>
                                                    </div>
                                                </div>
                                              <div class="col-sm-6 right_zero">
                                                    <h4>POA Back</h4>
                                                    <div class="dependant_image">
                                                        <img id="pic2" src="${patientProfileMembers.backPOAImage}" alt="" />
                                                    </div>
                                                </div>
                                                    <div>
                                                    <div class="clearfix col-sm-6" style="margin-left:10%;">
                                                        <input type="button" value ="-" onclick="zoompic(0.9)" style="width:50px"/>
                                                        <input type="button" value ="+" onclick="zoompic(1.1) " style="width:50px"/>
                                                    </div>  
                                                    <div class="clearfix col-sm-6" style="margin-left:64%;">
                                                        <input type="button" value ="-" onclick="zoom(0.9)" style="width:50px"/>
                                                        <input type="button" value ="+" onclick="zoom(1.1)" style="width:50px"/>
                                                    </div> 
                                                    </div>  -->
                        <form:form action="${pageContext.request.contextPath}/PharmacyPortal/poaApproval" commandName="patientProfileMembers" method="Post">
                            <form:hidden path="id" />
                            <form:hidden path="isApproved" value="1" />
                            <form:hidden path="disapprove" value="0" />
                            <form:hidden path="pharmacyQueueParam" value="${pharmacyQueueParam}" />
                            <input id="patientId" type="hidden" value="${patientProfile.id}" name="patientId"/>
                            <div class="col-sm-12 dependant_btn">
                                <strong><span id="reqComments"></span></strong><br>

                                <c:forEach var="row" items="${careGiverList}">
                                    <c:choose>
                                        <c:when test="${row.disabledStr eq '0'}">
                                            POA Expiration Date:
                                            <input id="datetimepicker" style="display:inline-block;width: auto;"  class="form-control calendar_field" placeholder="mm/dd/yyyy" 
                                                   onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"   
                                                   value="<fmt:formatDate value='${row.poaExpirationDate}' pattern='MM/dd/yyyy'/>" name="poaExpirationDate" /> 
                                            <br>
                                        </c:when>
                                        <c:otherwise>
                                            POA Expiration Date:
                                            <input id="datetimepicker" style="display:inline-block;width: auto;"  class="form-control calendar_field" placeholder="mm/dd/yyyy" 
                                                   onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"   
                                                   value="<fmt:formatDate value='${row.poaExpirationDate}' pattern='MM/dd/yyyy'/>" name="poaExpirationDate" disabled="true"/> <br>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${row.disabledStr eq '0'}">
                                            <form:textarea  rows="4" cols="50" placeholder=" Comments Here.." path="comments"  /><br>
                                            <a  class="btn btn-primary" style="background:red;width: 88px;text-align: center;" onclick="validateField()">DisApprove</a>

                                            <%if (request.getParameter("pq") != null && request.getParameter("pq").equals("1")) {%>
                                            <a href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest" class="btn btn-primary" style="width: 88px;text-align: center;">Cancel</a>
                                            <%
                                            } else {%>
                                            <a href="${pageContext.request.contextPath}/PharmacyPortal/careGiverList" class="btn btn-primary" style="width: 88px;text-align: center;">Cancel</a>
                                            <%}%>

                                            <a href="#" class="btn btn-primary" style="background:green;width: 88px;text-align: center;" onclick="validateFieldForDate()">Approve</a>

                                            <!--                                       <a  class="btn btn-primary" style="background:green" onclick="validateFieldForDate()">Approve</a>-->
                                        </c:when>
                                        <c:otherwise>
                                            <form:textarea  rows="2" cols="50" placeholder=" Comments Here.." path="comments" disabled="true" /><br>

                                            <a href="${pageContext.request.contextPath}/PharmacyPortal/careGiverList" class="btn btn-primary" style="width: 88px;text-align: center;">Cancel</a>

                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>         
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <!--Registration Page--> 

            <!--Footer-->
            <jsp:include page="./inc/footer2.jsp" />

            <!--/Footer-->

        </div>
        <!-- jQuery -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientDependent.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInsurancePharmacy.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
        <script type="text/javascript" src="date.js"></script>
        <script type="text/javascript">
                                                $(document).ready(function (e) {
                                                    window.pharmacyNotification.populateAppUrl('${pageContext.request.contextPath}');
                                                    window.patientInfoDependant.populateAppUrl('${pageContext.request.contextPath}');
                                                    window.patientInfoInsurance.populateAppUrl('${pageContext.request.contextPath}');
                                                    window.transferRequest.populateAppUrl('${pageContext.request.contextPath}');
                                                    window.drugestimateprice.populateAppUrl('${pageContext.request.contextPath}');
                                                });
                                                function submitDetailsForm(type) {
                                                    if (type == "Approved") {
                                                        $("#isApproved").val(1);
                                                        $("#disapprove").val(0);
                                                    } else {

                                                        $("#isApproved").val(0);
                                                        $("#disapprove").val(1);
                                                    }
                                                    $("#patientProfileMembers").submit();
                                                }
                                                function validateField() {
                                                    if ($("#comments").val().length == 0) {
                                                        $("#reqComments").text("Comments Required");
                                                        $("#reqComments").attr("style", "color:red;font-size:14px;");
                                                        $("#comments").focus();
                                                        return false;
                                                    }
                                                    if ($("#comments").val().length > 200) {
                                                        $("#reqComments").text("Comments can not be of more than 200 chracters.");
                                                        $("#reqComments").attr("style", "color:red;font-size:14px;");
                                                        $("#comments").focus();
                                                        return false;

                                                    }
                                                    $("#confirmDiv2").modal('show');
                                                    return true;
                                                }

                                                var zoomLevelOne = 100;
                                                var maxZoomLevelOne = 108;
                                                var minZoomLevelOne = 95;

                                                function zoompic(zm) {
                                                    var img = document.getElementById("pic");
                                                    if (zm > 1) {
                                                        if (zoomLevelOne < maxZoomLevelOne) {
                                                            zoomLevelOne++;
                                                        } else {
                                                            return;
                                                        }
                                                    } else if (zm < 1) {
                                                        if (zoomLevelOne > minZoomLevelOne) {
                                                            zoomLevelOne--;
                                                        } else {
                                                            return;
                                                        }
                                                    }
                                                    wid = img.width;
                                                    ht = img.height;
                                                    img.style.width = (wid * zm) + "px";
                                                    img.style.height = (ht * zm) + "px";
                                                    //    img.style.marginLeft = -(img.width/2) + "px";
                                                    //    img.style.marginTop = -(img.height/2) + "px";
                                                }

                                                var zoomLevel = 100;
                                                var maxZoomLevel = 108;
                                                var minZoomLevel = 95;

                                                function zoom(zm) {
                                                    var img = document.getElementById("pic2");
                                                    if (zm > 1) {
                                                        if (zoomLevel < maxZoomLevel) {
                                                            zoomLevel++;
                                                        } else {
                                                            return;
                                                        }
                                                    } else if (zm < 1) {
                                                        if (zoomLevel > minZoomLevel) {
                                                            zoomLevel--;
                                                        } else {
                                                            return;
                                                        }
                                                    }
                                                    wid = img.width;
                                                    ht = img.height;
                                                    img.style.width = (wid * zm) + "px";
                                                    img.style.height = (ht * zm) + "px";
                                                    //    img.style.marginLeft = -(img.width/2) + "px";
                                                    //    img.style.marginTop = -(img.height/2) + "px";
                                                }

                                                function validateFieldForDate() {
                                                    if ($("#comments").val().length > 200) {
                                                        $("#reqComments").text("Comments can not be of more than 200 chracters.");
                                                        $("#reqComments").attr("style", "color:red;font-size:14px;");
                                                        $("#comments").focus();
                                                        return false;

                                                    }

                                                    var rxDate = $("#datetimepicker").val();
                                                    if (rxDate == "") {
                                                        $("#reqComments").text("POA Expiration Date Required");
                                                        $("#reqComments").attr("style", "color:red;font-size:14px;");
                                                        $("#datetimepicker").focus();
                                                        return false;
                                                    } else {
//                                            console.log(new Date());
                                                        console.log(convertDate(new Date()))
                                                        console.log($("#datetimepicker").val());
//                                         
                                                        var newDate = new Date().getTime();
                                                        var prescDate = new Date(rxDate).getTime();
                                                        if (prescDate > newDate) {
                                                            $("#reqComments").text("");
                                                            $("#reqComments").attr("style", "color:red;font-size:14px;");
                                                            $("#confirmDiv").modal('show');
                                                            return true
                                                        } else {

                                                            $("#reqComments").text("Enter Future Date Only");
                                                            $("#reqComments").attr("style", "color:red;font-size:14px;");
                                                            $("#datetimepicker").focus();
                                                            return false;
                                                        }
                                                    }
                                                }
                                                $('#datetimepicker').datetimepicker({timepicker: false, format: 'm/d/Y',
                                                    onChangeDateTime: function (dp, $input) {
                                                        jQuery('#datetimepicker').datetimepicker('hide');

                                                    }});
                                                function convertDate(inputFormat) {
                                                    function pad(s) {
                                                        return (s < 10) ? '0' + s : s;
                                                    }
                                                    var d = new Date(inputFormat);
                                                    return [pad(d.getMonth() + 1), pad(d.getDate()), d.getFullYear()].join('/');
                                                }




        </script>
        <!----------------------------------------------------------------------------------------------------------------->
        <div id="confirmDiv" class="medicationModal confirmation_modal listModal healthModal formModal modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" >&times;</button>
                        <h4  class="modal-title"><label>Confirm Dialog</label></h4> 

                    </div>
                    <div class="modal-body refill_medi">

                        <div>


                            <div class="refill_options">
                                <p class="clearfix"><span>Do you want to approve this?</span>

                                </p>
                                <div>
                                    <input id="confirmBtn" type="button" class="btn back_btn" value="OK" 
                                           onclick="submitDetailsForm('Approved')"> 

                                    <button id="cancelConfirmBoxBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 80px; vertical-align: middle;">Cancel</button>
                                </div>

                            </div>
                        </div>
                        <!-------------------------------------------------------------------->

                        <div style="text-align: center; position: relative; bottom: 10px;">
                            <!--
                              <input id="cancel_btnRefill" type="submit" class="btn back_btn" value="Cancel
                                    style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;">-->
                            &nbsp;

                        </div>

                    </div>
                </div>

            </div>
        </div>

        <div id="confirmDiv2" class="medicationModal confirmation_modal listModal healthModal formModal modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" >&times;</button>
                        <h4  class="modal-title"><label>Confirm Dialog</label></h4> 

                    </div>
                    <div class="modal-body refill_medi">

                        <div>


                            <div class="refill_options">
                                <p class="clearfix"><span>Do you want to Disapprove this?</span>

                                </p>
                                <div>
                                    <input id="confirmBtn" type="button" class="btn back_btn" value="OK" 
                                           onclick="submitDetailsForm('DisApproved')"> 

                                    <button id="cancelConfirmBoxBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 80px; vertical-align: middle;">Cancel</button>
                                </div>

                            </div>
                        </div>
                        <!-------------------------------------------------------------------->

                        <div style="text-align: center; position: relative; bottom: 10px;">
                            <!--
                              <input id="cancel_btnRefill" type="submit" class="btn back_btn" value="Cancel
                                    style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;">-->
                            &nbsp;

                        </div>

                    </div>
                </div>

            </div>
        </div>
        <!----------------------------------------------------------------------------------------------------------------->
        <jsp:include page="addNewRxDiv.jsp" />
    </body>
</html>
