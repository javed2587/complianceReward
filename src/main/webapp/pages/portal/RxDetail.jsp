3+
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
        <!--Registration Page-->
        <div class="row">
            <div class="wrapper">
                <input id="patientId" type="hidden" value="${order.patientId}"/>
                <input id="orderId" type="hidden" value="${order.orderId}"/>
                <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
                <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.dob}"/>
                <input type="hidden" id="orderDateFld" 
                       value="<fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss' />" />
                <div class="userBanner clearfix">
                    <div class="clearfix">
                        <ul>
                            <li >${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(fn:substring(patientProfile.lastName, 0, 1))} &nbsp;(${patientProfile.gender eq 'M'?'MALE':'FEMALE'})</li>
                            <li ><fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.dob}"/> (${nowyear-dobyear} YEARS)</li>
                            <li >MEMBER SINCE <fmt:formatDate pattern="MM/yyyy" value="${patientProfile.createdOn}"/></li>
                                <c:choose>
                                    <c:when test="${fn:length(patientProfile.patientProfileMembersList) > 0}">
                                    <li><a href="#" onclick="displayModal(${order.patientId}, 'dependentsModal', 'viewDependentWs')"> ${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li>${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</li>
                                    </c:otherwise>
                                </c:choose>
                            <li >${count} PROGRAM RX'S</li>
                        </ul>
                        <p><fmt:formatDate pattern="MMMM dd, yyyy hh:mm" value="<%=new java.util.Date()%>"/> CST</p>
                    </div>
                </div>
                <div class="customTableRow">
                    <div class="col-md-3">
                        <div class="heightList clearfix">
                            <div class="primume_box">
                                <p><span>${rewardPoints.availablePoints}</span>Compliance<br />Reward&trade; Points</p>
                                <div class="primiume_icon">
                                    <c:forEach var="row" items="${rewardLevelList}">
                                        <c:if test="${rewardPoints.availablePoints>row.fromLevel && rewardPoints.availablePoints<=row.toLevel}">

                                            <div style="${row.type eq 'Basic'?'text-align: center;padding-right:7px !important;font-size:12px':''}">
                                                ${row.type}
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>

                            <ul>
                                <li><span>Height</span></li>
                                <li><span>Weight</span></li>
                                <li><span>BMI</span></li>
                            </ul>
                            <ul>
                                <li>${not empty patientProfile.patientProfileHealthsList[0].heightFeet?patientProfile.patientProfileHealthsList[0].heightFeet:0.0} ft</li>
                                <li>${not empty patientProfile.patientProfileHealthsList[0].weight?patientProfile.patientProfileHealthsList[0].weight:0.0} lbs</li>
                                <li>${not empty patientProfile.patientProfileHealthsList[0].bmi?patientProfile.patientProfileHealthsList[0].bmi:0}%</li>
                            </ul>

                        </div>
                        <div class="contactContainer">
                            <p><span class="blueText">Phone #.</span> ${fn:substring(patientProfile.mobileNumber, 0, 3)}-${fn:substring(patientProfile.mobileNumber, 3, 6)}-${fn:substring(patientProfile.mobileNumber, 6, 10)}</p>
                            <p><span class="blueText">Backup Phone #.</span> ${fn:substring(patientProfile.mobileNumber, 0, 3)}-${fn:substring(patientProfile.mobileNumber, 3, 6)}-${fn:substring(patientProfile.mobileNumber, 6, 10)}</p>
                            <p><span class="blueText">Email</span> ${not empty patientProfile.emailAddress?patientProfile.emailAddress:'&nbsp;'}</p>
                            <p><span class="blueText">Allergies </span><br />  ${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}</p>
                        </div>
                        <div class="shippingContainer">
                            <ol type="1">
                                <c:forEach var="shippingAddressVar" items="${patientProfile.patientDeliveryAddresses}">
                                    <li>
                                        <span class="blueText">Shipping Address </span><span class="greenText" style="float: right;">&nbsp;${shippingAddressVar.defaultAddress eq 'Yes'?'Default':''}</span>
                                        <br />
                                        <strong>${shippingAddressVar.description}</strong>
                                        <br />
                                        <span>${shippingAddressVar.address}, ${shippingAddressVar.apartment}, ${shippingAddressVar.city},${shippingAddressVar.state.name} ,${shippingAddressVar.zip}</span>
                                    </li>
                                </c:forEach>
                            </ol>
                        </div>
                        <div class="insuranceContainer">
                            <span class="blueText">Insurance Card</span>
                            <c:choose>
                                <c:when test="${not empty patientInsuranceDetails}">
                                    <span><a href="#" class="greenText" data-toggle="modal" data-target="#insuranceDetailModal">View Detail</a></span>
                                </c:when>
                                <c:otherwise>
                                    <span>View Detail</span>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${not empty patientProfile.insuranceFrontCardPath && not empty patientProfile.insuranceBackCardPath}">
                                    <div>
                                        <img src="${patientProfile.insuranceFrontCardPath}" alt="insurance card front" data-toggle="modal" data-target="#cardsModal" class="frontsidecardimg"/>
                                        <img src="${patientProfile.insuranceBackCardPath}" alt="insurance card back" data-toggle="modal" data-target="#cardsModal" class="frontsidecardimg"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div style="height: 50px;">
                                        <span style="color: red;">No Insurance Card Available.</span>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <form action="" class="messageForm">
                            <textarea id="messageId" name="message" class="messageBox" onclick="window.pharmacyNotification.resetMessageFn();" placeholder="Send Message to Patient"></textarea>
                            <button type="button" onclick="window.pharmacyNotification.savePharmacyNotificationFn();"><i class="fa fa-paper-plane greenText"></i></button>
                        </form>
                        <a href="#" class="greenText messageHistory pull-left" onclick="displayModal(${order.orderId}, 'historyModal', 'viewHistoryMessageWs')">View Message History</a>
                        <div id="errorSendMessage" class="pull-right"></div>
                    </div>
                    <c:choose>
                                        <c:when test="${update eq '1'}">
                                            <div style="color:green; text-align:left;">
                                                Order updated successfully.
                                            </div>
                                        </c:when>
                     </c:choose>

                    <div class="col-md-9">
                        <div class="transfer_wrap clearfix">
                            <div class="col-sm-9 set_table">
                                <div class="transfer_top clearfix">
                                    <h3>Order Detail</h3>
                                </div>
                                <table>
                                    <tr>
                                        <th class="col-sm-3">Request On</th>
                                        <th class="col-sm-3">Order #.</th>
                                        <th class="col-sm-3">Type</th>
                                        <th class="col-sm-3">Status </th>
                                    </tr>
                                    <tr>
                                        <td>
                                            <fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                        </td>
                                        <td>${order.orderId}</td>                                        
                                        <td>Rx Order</td>
                                        <td>${order.status}</td>
                                    </tr>
                                </table>


                                <div class="transfer_top clearfix">
                                    <h3>Drug Detail</h3>
                                </div>
                                <table>
                                    <tr>
                                        <th class="col-sm-3">Drug Name</th>
                                        <th class="col-sm-3">Type</th>
                                        <th class="col-sm-3">Qty.</th>
                                        <th class="col-sm-3">Drug Price </th>
                                    </tr>
                                    <tr>
                                        <td>${order.drug}</td>
                                        <td>${order.drugType}</td>
                                        <td>${order.quantity}</td>
                                        <td><fmt:formatNumber type="currency" currencySymbol="$" value="${order.cashPriceQuated}"/></td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${order.status eq 'Pending'}">
                                        <tr>
                                        <td colspan="3" align="left">
                                        <input type="button" 
                                         onclick="window.pharmacyNotification.showEditDrugDiv('editDrugDiv');" id="editDrugLnk" 
                                         class="btn" value="Edit" />

                                        </td>
                                         </tr>
                                        </c:when>
                                    </c:choose>
                                </table>
   <!----------------------------------------------------------------------------------------------------------->
   <form id="formUpdate" 
         action="${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${orderId}/${patientId}?status=${order.status}&update=1" method="post">
                               <div id="editDrugDiv" style="display:none">
                                     <table>                                    
                                        <tr>
                                        <td class="drug">
                                             <input id="drugNameId"  type="text" class="form-control selectpicker show-tick"
                                                    onblur="window.transferRequest.lookUpDrugTypeByBrandName();" 
                                                    oninput="window.transferRequest.lookUpDrugName2();"
                                                    value="${order.drugNameWithoutStrength}"/>
                                        </td>
                                        <td >
                                        <select id= "drugTypeId"  onblur="window.transferRequest.lookUpDrugStrengthByBrandNameAndType();" cssClass="form-control">
                                                <option value="">Select Type</:option>
                                               
                                        </select>
                                        </td>
                                        <td >
                                         <select id="drugStrength" path="strength" cssClass="form-control">
                                                <option value="">Select Strength</option>
                                                
                                          </select>
                                        </td>
                                        <td align="center" class="drug">
                                            <input id="drugQtyFld"  type="text" size="5" 
                                                    value="${order.quantity}"/>
                                        </td>                                        
                                       
                                    </tr>
                                   
                                             

                                     </table>
                                     <div style="text-align: right; position: relative; ">
                                        
                                      
                                        
                                       <input type="button" class="btn back_btn" value="Done" 
                                                onclick="window.transferRequest.updateDrugInfoInOrder(${order.orderId})" 
                                    style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;" />
                                    &nbsp;
                                        <input type="button" class="btn back_btn" value="Cancel" 
                                        onclick="window.pharmacyNotification.hideEditDrugDiv('editDrugDiv');" 
                                    style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;" />
                                    </div>
                                    <a id="drugUpdateLnk" onclick="document.getElementById('formUpdate').submit(); return false;"  style="display:none"></a>
                                </div>
   </form>
   <!------------------------------------------------------------------------------------------------------------>



                                <div class="transfer_top clearfix">
                                    <h3>Rx Detail</h3>
                                </div>
                                <table>
                                    <tr>
                                        <th>Patient Name</th>
                                        <th>Rx #.</th>
                                        <th>Days Supply.</th>
                                        <th>Expired On </th>
                                        <th>Refil Allowed</th>
                                    </tr>
                                    <tr>
                                        <td>
                                            ${order.patientName}
                                        </td>
                                        <td>${not empty order.transferDetail.rxNumber?order.transferDetail.rxNumber:'N/A'}</td>
                                        <td>${not empty order.transferDetail.daysSupply?order.transferDetail.daysSupply:'N/A'}</td>
                                        <td>
                                            <fmt:formatDate var="expiryDate" value="${order.transferDetail.expiryDate}" pattern="MM/dd/yyyy"/>
                                            ${not empty expiryDate?expiryDate:'N/A'}
                                        </td>
                                        <td>${not empty order.transferDetail.refillAllowed?order.transferDetail.refillAllowed:'N/A'}</td>
                                    </tr>
                                    <tr>
                                        <th>Refill Used</th>
                                        <th>Last Fill Date</th>
                                        <th>Prescriber NPI</th>
                                        <th>Pharmacy Name </th>
                                        <th>Phrmacy Phone </th>
                                    </tr>
                                    <tr>
                                        <td>${not empty order.transferDetail.refillUsed?order.transferDetail.refillUsed:'N/A'}</td>
                                        <td>
                                            <fmt:formatDate var="lastFillDate" value="${order.transferDetail.lastFillDate}" pattern="MM/dd/yyyy"/>
                                            ${not empty lastFillDate?lastFillDate:'N/A'}
                                        </td>
                                        <td>${not empty order.transferDetail.npi?order.transferDetail.npi:'N/A'}</td>
                                        <td>${not empty order.transferDetail.pharmacyName?order.transferDetail.pharmacyName:'N/A'}</td>
                                        <td>${not empty order.transferDetail.pharmacyPhone?order.transferDetail.pharmacyPhone:'N/A'}</td>
                                    </tr>
                                </table>
                            </div>

                            <div class="col-sm-3 video_div">
                                <!--                                <a href="#" class="video_button"></a>-->
                                <video id="videoContainer"   width="155" height="230" controls preload="none" >
                                    <source src="${order.ptVideo}" type="video/mp4">
                                </video>
                            </div>

                            <form  action="${pageContext.request.contextPath}/ConsumerPortal/transferRxList/${orderId}/${patientId}?status=${order.status}"
                                   method="post" id="form" name="form">
                                <div class="comments_section">
                                    <div class="col-md-9 comment_area">
                                        <textarea id="processMessage" placeholder="Type Comments" class="form-control" 
                                                  onclick="window.pharmacyNotification.resetMessageFn();">${order.orderHistoryMessage}</textarea>
                                    </div>
                                    <div class="col-sm-3 comment_buttons"><a href="${pageContext.request.contextPath}/PharmacyPortal/successLogin/${orderId}?status=${order.status}">Back to Main Queue</a><br />
                                        <input type="button" name="save" value="Save" class="btn" onclick="window.pharmacyNotification.processOrder();"/>
                                        <%if(request.getParameter("profile")!=null &&
                                            request.getParameter("profile").equals("1")    ){%>
                                            <input type="submit" name="cancel" value="Cancel" class="btn" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/patientHistory/${patientId}'"/>
                                       <%}else{%>
                                        <input type="submit" name="cancel" value="Cancel" class="btn" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/transferRxList/${orderId}/${patientId}'"/>
                                        <%}%>
                                        <a id="backLnk" onclick="document.getElementById('form').submit(); return false;" class="btn btn_one" style="display:none"></a>

                                    </div>
                                </div>
                            </form>

                        </div>



                        <jsp:include page="../patientprofile/inAppNotificationMsgs.jsp" />
                        <div class="medication_wrap clearfix">

                            <div class="col-sm-9 no-paading">
                                <h2>MEDICATION SPECIFIC MESSAGE <span>-- SEND PATIENT MESSAGE (<i>in app</i>)</span></h2>
                                <div class="col-md-4 col-sm-4 medi_col">
                                    <h4>NOT CARRIED/Out of STOCK</h4>
                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           'CII', 'Drug can not fill', 'Pharmacy Notification', 'Cannot Fill');" 
                                           id="canNotFill" class="btn btn_one" value="Drug- Cannot Fill" /> 

                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'Rx No Carried for Now', 'Pharmacy Notification', 'Rx No Carried');" 
                                           id="notCarried" class="btn btn_one" value="Rx Not Carried for Now" /> 
                                </div>

                                <div class="col-md-4 col-sm-4 medi_col">
                                    <h4>CALLING MD</h4>
                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'No Refills; Calling HCP', 'Pharmacy Notification', 'No Refill Remaining');" 
                                           id="canNotFill" class="btn btn_one" value="No Refills; Calling HCP" /> 
                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'Rx Expired; Calling HCP', 'Pharmacy Notification', 'Rx Expired');" 
                                           class="btn btn_one" value="Rx Expired; Calling HCP" />

                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'MD Declined – Cann not fill', 'Pharmacy Notification', 'MD Declined');" 
                                           class="btn btn_one" value="MD Declined – Can't fill"/>



                                </div>

                                <div class="col-md-4 col-sm-4 medi_col">
                                    <h4>INS DECLINED</h4>
                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'Refill too Soon- $ Option', 'Pharmacy Notification', 'Refill too Soon');" 
                                           class="btn btn_one" value="Refill too Soon- $ Option"/>
                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'Ins Declined-  $ Option', 'Pharmacy Notification', 'Ins Declined');" 
                                           class="btn btn_one" value="Ins Declined-  $ Option"/>

                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'Step Edit / PA-  $ Option', 'Pharmacy Notification', 'Step Edit PA');" 
                                           class="btn btn_one" value="Step Edit / PA-  $ Option"/>

                                    <input type="button" 
                                           onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                           '', 'Ins not Accepted-- $', 'Pharmacy Notification', 'Ins Not accpeted');" 
                                           class="btn btn_one" value="Ins not Accepted-- $"/>


                                </div>
                            </div>


                            <div class="col-md-3 col-sm-3 medi_col">
                                <h4>ADMIN  STATUS</h4>
                                <input type="hidden" id="processingID" value="0" />
                                <input type="hidden" id="pending" value="0" />
                                <input type="hidden" id="delayed" value="0" />
                                <input type="hidden" id="courier" value="0" />
                                <input type="hidden" id="shipped" value="0" />
                                <input type="hidden" id="statusBit" value="0" />

                                <c:choose>
                                    <c:when test="${order.status eq 'Pending'}">
                                        <input type="button" onclick="window.pharmacyNotification.checkProcessingStatusBit('medicationModal2',
                                                      '', 'Processing Order', 'RX Order', 'Order Processed');" id="processingLnk" class="btn btn_two" 
                                               value="PROCESSING" />

                                    </c:when>

                                    <c:otherwise>
                                        <input type="button" onclick="window.pharmacyNotification.checkProcessingStatusBit('medicationModal2',
                                                   '','Order Processed','RX Order','Order Processed');" id="processingLnk" class="btn btn_two" 
                                               disabled="true"       value="PROCESSING" />
                                    </c:otherwise>
                                </c:choose>
                               <c:choose>         
                                 <c:when test="${ order.status eq 'Shipped'}">
                                        <input type="button" disabled="true"  id="holdLnk" class="btn btn_two" 
                                               value="On Hold" />

                                </c:when>
                                    <c:otherwise>
                                        <input type="button" id="holdLnk" class="btn btn_two" 
                                         onclick="window.pharmacyNotification.checkOtherStatusBit('medicationModal',
                                                        '', 'Order On Hold', 'RX Order', 'Order On Hold', 7);"
                                         value="On Hold" />
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${order.status eq 'Pending' || order.status eq 'Shipped'}">
                                        <input type="button" disabled="true"  id="pendingLnk" class="btn btn_two" 
                                               value="PENDING PHMCY REPLY" />

                                        <input type="button"disabled="true"  id="delayedLnk" class="btn btn_two" 
                                               value="DELAYED / Rx ON ORDER" />

                                        <input type="button" disabled="true"  id="courierLnk" class="btn btn_two" 
                                               value="COURIER DELIVERY" />

                                        <input type="button" disabled="true"  id="shippedLnk" class="btn btn_two" 
                                               value="Shipped" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" onclick="window.pharmacyNotification.checkOtherStatusBit('medicationModal',
                                                        '', 'Order Processed', 'RX Order', 'Order Processed', 3);" id="pendingLnk" class="btn btn_two" 
                                               value="PENDING PHMCY REPLY" />

                                        <input type="button" onclick="window.pharmacyNotification.checkOtherStatusBit('medicationModal',
                                                        '', 'Order Processed', 'RX Order', 'Order Processed', 4);" id="delayedLnk" class="btn btn_two" 
                                               value="DELAYED / Rx ON ORDER" />

                                        <input type="button" onclick="window.pharmacyNotification.checkOtherStatusBit('medicationModal',
                                                        '', 'Order Processed', 'RX Order', 'Order Processed', 5);" id="courierLnk" class="btn btn_two" 
                                               value="COURIER DELIVERY" />

                                        <input type="button" onclick="window.pharmacyNotification.checkOtherStatusBit('medicationModal',
                                                        '', 'Order Processed', 'RX Order', 'Order Processed', 6);" id="shippedLnk" class="btn btn_two" 
                                               value="Shipped" />
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>

                    </div>
                </div>
            </div>


        </div>
        <!--Registration Page-->

        <!--Footer-->
        <jsp:include page="./inc/footer2.jsp" />
        <!--/Footer-->

    </div>
    <!-- Modal Status -->
    <jsp:include page="modals.jsp" />
    <div id="statusModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">STATUses</h4>
                </div>
                <div class="modal-body">
                    <h6>OPEN BY OTHER USER</h6>
                    <p>Prescription is currently open by another user and cannot be viewed.</p>
                    <h6>Pending</h6>
                    <p>Prescription has not been viewed by any user.</p>
                </div>
            </div>

        </div>
    </div>
    <!--/ Modal Status -->

    <!-- Modal Register -->
    <!--/ Modal Register -->

    <!-- jQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientDependent.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInsurancePharmacy.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
    <script>
                                            $(function () {
                                                var pull = $('#pull');
                                                menu = $('.Menu ul');
                                                menuHeight = menu.height();

                                                $(pull).on('click', function (e) {
                                                    e.preventDefault();
                                                    menu.slideToggle();
                                                });

                                                $(window).resize(function () {
                                                    var w = $(window).width();
                                                    if (w > 320 && menu.is(':hidden')) {
                                                        menu.removeAttr('style');
                                                    }
                                                });
                                            });


                                            $(document).ready(function (e) {
                                                $(".buttonss").click(function () {

                                                    $(".logDrop").slideToggle();
                                                })
                                            });
    </script>
</body>

</html>
