<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head2.jsp" />
    <script language="JavaScript">
        var ids=new Array('rxNumberFld','drugTypeId','drugStrength','drugQtyFld','daysSupplyFld','refillAllowedFld','refillUsedFld','paymentFld',
	'pharmacyNameFld','pharmacyPhoneFld','prescriberNameFld','prescriberPhoneFld','prescriberNPIFld','acqCostFld','reimbFld',
'ptCopayFld');


var values =new Array('','','','','','','','',
	'','','','','','','','');

 function populateArrays()
  {
    // assign the default values to the items in the values array
    for (var i = 0; i < ids.length; i++)
    {
      var elem = document.getElementById(ids[i]);
      if (elem)
        if (elem.type == 'checkbox' || elem.type == 'radio')
          values[i] = elem.checked;
        else
          values[i] = elem.value;
    }      
  }



  var needToConfirm = true;
  
  window.onbeforeunload = confirmExit;
  function confirmExit()
  {
    
    if (needToConfirm)
    {
      // check to see if any changes to the data entry fields have been made
      for (var i = 0; i < values.length; i++)
      {
        var elem = document.getElementById(ids[i]);
        if (elem)
          if ((elem.type == 'checkbox' || elem.type == 'radio')
                  && values[i] != elem.checked)
            return "You have attempted to leave this page.  If you have made any changes to the fields without clicking the Save button, your changes will be lost.  Are you sure you want to exit this page?";
          else if (!(elem.type == 'checkbox' || elem.type == 'radio') &&
                  elem.value != values[i])
            return "You have attempted to leave this page.  If you have made any changes to the fields without clicking the Save button, your changes will be lost.  Are you sure you want to exit this page?";
      }

      // no changes - return nothing      
    }
  }

  </script>
    <body>
        <jsp:include page="./inc/header2.jsp" />
        <!--Registration Page-->
        <div class="row">
            <div class="wrapper">
                <input id="saveOption" type="hidden" value="0" />
                <input id="patientId" type="hidden" value="${order.patientId}" />
                <input id="orderId" type="hidden" value="${order.orderId}"/>
                <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
                <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.dob}"/>
                <input type="hidden" id="orderDateFld" 
                       value="<fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss' />" />
                <div class="userBanner clearfix">
                    <div class="clearfix">
                        <ul>
                            <li>${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(fn:substring(patientProfile.lastName, 0, 1))} &nbsp;(${patientProfile.gender})</li>
                            <li >DOB: <fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.dob}"/> </li>
                            <li >Age: ${nowyear-dobyear} YEARS</li>
                                <c:choose>
                                    <c:when test="${fn:length(patientProfile.patientProfileMembersList) > 0}">
                                    <li><a href="#" onclick="displayModal(${order.patientId}, 'dependentsModal', 'viewDependentWs')"> ${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li>${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</li> 
                                    </c:otherwise>
                                </c:choose>
                            <li >${count} PROGRAM RX'S</li>
                            <li>
                                <c:if test="${order.isOldPatient ne true}">
                                    <span class="new_usere"></span>
                                </c:if>
                                Member Since <fmt:formatDate value='${order.patientEnrollmentDate}' pattern='yyyy-MM-dd'/></li>
                        </ul>

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
                            <span style="color: red;">${fn:length(patientProfile.patientDeliveryAddresses)}</span> <span style="color: black;">ADDRESSES ON FILE </span>
                            <ol type="1">
                                <c:forEach var="shippingAddressVar" items="${patientProfile.patientDeliveryAddresses}">
                                    <li>
                                        <span>ADDRESS</span>
                                        <span class="greenText" style="float: right;">&nbsp;${shippingAddressVar.defaultAddress eq 'Yes'?'Default':''}</span>
                                        <br />
                                        <strong>${shippingAddressVar.description} ${shippingAddressVar.address}, ${shippingAddressVar.apartment}, ${shippingAddressVar.city},${shippingAddressVar.state.name} ,${shippingAddressVar.zip}</strong>
                                        <br />
                                        <span></span>
                                    </li>
                                </c:forEach>
                            </ol>
                        </div>
                        <div class="shippingContainer">
                            <span style="color: red;">${fn:length(paymentInfoList)}</span> <span style="color: black;">PAYMENT CARD ON FILE </span>
                            <c:forEach var="row" items="${paymentInfoList}">
                                <span class="blueText" style="font-weight: bold;">${row.cardNumber} EXP:${row.expiryDate} CVV ${row.cvvNumber}</span><br/>
                                <span style="font-weight: bold;color: black;">CARDHOLDER</span>
                                <span style="font-weight: bold;color: black;float: right;">BILLING ADDRESS</span>
                                <span style="color: black;">${row.cardHolderName}</span>
                                <span style="color: black;float: right;">${row.address} ${row.apartment} ${row.city} </span>
                                <span style="color: black;">&n&nbsp;</span>
                                <span style="color: black;float: right;">${row.state} ${row.zip}</span>
                            </c:forEach>

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

                                        <img id="frontImage"
                                             src="${patientProfile.insuranceFrontCardPath}" alt="insurance card front" 
                                             data-toggle="modal" data-target="#cardsModal" class="frontsidecardimg"/>
                                        <img src="${patientProfile.insuranceBackCardPath}" alt="insurance card back" data-toggle="modal" data-target="#cardsModal" class="frontsidecardimg"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div style="height: 50px;">
                                        <span style="color: red;">No Insurance Card Available.</span>
<!--                                        <img src="${pageContext.request.contextPath}/resources/images/card.png" alt="front" />-->

                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>



                    </div>
                    <c:choose>
                        <c:when test="${update eq '1'}">
                            <div style="color:green; text-align:left;">
                                Order updated successfully.
                            </div>
                        </c:when>
                    </c:choose>

                    <div class="col-md-9">
                        <%if (request.getParameter("success") != null
                                    && request.getParameter("success").equals("1")) {%>
                        <div>
                            <span style="color:green"><strong>Order has been updated successfully.</strong></span>
                        </div>
                        <%}%>
                        <div class="transfer_wrap clearfix">
                            <div class="col-sm-9 set_table">
                                <div class="order_details_h clearfix">
                                    <h2>Order Detail-- ${order.orderId}</h2>

                                    <span id="orderStatusSpan">${order.status}</span>
                                    <small><fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd'/> @ <strong><fmt:formatDate value='${order.receivedDate}' pattern='hh:mm:ss'/></strong></small>
                                </div>

                                <div id="full_video_container" class="full_video_container" style="display: none;">
                                </div>
                                <div id="full_width_img" class="full_width_img" style="display: none;">
                                    <a href="#" class="close_full_img" onclick="hideFullScreen('full_width_img')" title="Close">X</a>
                                    <div id="full_width_img" class="img_div">
                                        <img src="${order.transferImage}" alt="">
                                    </div>
                                </div>

                                <table class="dt_tables">
                                    <tr>
                                        <th class="col-sm-1">Request Type</th>
                                        <th class="col-sm-1">Original Date</th>
                                        <th class="col-sm-1">RX Number</th>
                                        <th class="col-sm-4">RX Name / Strength </th>
                                        <th class="col-sm-1">QTY</th>
                                        <th class="col-sm-1">Days Supply</th>
                                        <th class="col-sm-1">Refills Allowed</th>
                                        <th class="col-sm-1">Refills Remain</th>
                                        <th class="col-sm-1">Ins / Cash</th>
                                    </tr>
                                    <tr>
                                        <td><strong>${order.orderType}</strong></td>
                                        <td> <fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd'/></td>
                                        <td><input type="text" id="rxNumberFld" value="${order.rxNumber}" class="form-control" /></td>
                                        <td class="name_strenght_one"><input type="text" id="drugNameId" name="" value="${order.drugNameWithoutStrength}" class="form-control" 
                                                                             onblur="window.transferRequest.lookUpDrugTypeByBrandName();" 
                                                                             oninput="window.transferRequest.lookUpDrugName2();"/>

                                            <select id= "drugTypeId" onchange="checkSelectedValue('drugTypeId')"
                                                    onblur="window.transferRequest.lookUpDrugStrengthByBrandNameAndType();" 
                                                    class="form-control">
                                                <option value="${order.drugType}">${order.drugType}<option>

                                            </select>
                                            <select id= "drugStrength"   
                                                    class="form-control">
                                                <option value="${order.strength}">${order.strength}<option>

                                            </select>

                                        </td>
                                        <td><input type="text" id="drugQtyFld" value="${order.quantity}" class="form-control" onblur="window.transferRequest.lookUpDrugDetailByBrandNameAndType();" onkeypress="return IsDigit(event)"/></td>
                                        <td><input type="text" id="daysSupplyFld" value="${not empty order.daysSupply?order.daysSupply:''}"class="form-control" onkeypress="return IsDigit(event)"/></td>
                                        <td><input type="text" id="refillAllowedFld" value="${order.refillsAllowed}" class="form-control" onkeypress="return IsDigit(event)"/></td>
                                        <td><input type="text" id="refillUsedFld" value="${order.refillsRemaining}" class="form-control" onkeypress="return IsDigit(event)"/></td>
                                        <td><select id="paymentFld" class="form-control">
                                                <option value="Cash">Cash</option>
                                                <option value="INS">INS</option>
                                            </select></td>
                                    </tr>

                                </table>



                                <table class="dt_tables">
                                    <tr>
                                        <!-- <th class="col-sm-3">Patient</th>-->
                                        <th class="col-sm-3">Pharmacy Name</th>
                                        <th class="col-sm-2">Pharmacy Phone</th>
                                        <th class="col-sm-3">Prescriber Name</th>
                                        <th class="col-sm-2">Prescriber Phone</th>
                                        <th class="col-sm-2">Prescriber Npi</th>
                                    </tr>
                                    <tr>
                                     <!--   <td><input type="text" id="patientNameFld" value="${order.patientName}" class="form-control" /></td>-->
                                        <td><input type="text" id="pharmacyNameFld" value="${not empty order.pharmacyName?order.pharmacyName:''}" class="form-control" /></td>
                                        <td><input type="text" id="pharmacyPhoneFld" value="${not empty order.pharmacyPhone?order.pharmacyPhone:''}" class="form-control" onkeypress="return IsDigit(event)" maxlength="10"/></td>
                                        <td><input type="text" id="prescriberNameFld" value="${not empty order.prescriberName?order.prescriberName:''}" class="form-control" onkeypress="return onlyAlphabets(event)"  /></td>
                                        <td><input type="text" id="prescriberPhoneFld"  value="${not empty order.prescriberPhone?order.prescriberPhone:''}" class="form-control" onkeypress="return IsDigit(event)" maxlength="10"/></td>
                                        <td><input type="text" id="prescriberNPIFld" value="${not empty order.prescriberNPI?order.prescriberNPI:''}" class="form-control" onkeypress="return IsDigit(event)" maxlength="10"/></td>
                                    </tr>

                                </table>

                                <div class="type_comment"><textarea class="form-control" placeholder="Type Comment"></textarea></div>

                                <div class="col-md-2 del_det_logo"><img src="${pageContext.request.contextPath}/resources/images/Rx-logs.png" alt="" /></div>
                                <div class="col-md-10 del_det_ful">
                                    <table class="dt_tables">
                                        <tr>
                                            <th class="col-sm-1">RX Acq Cost</th>
                                            <th class="col-sm-1">RX Reimb</th>
                                            <th class="col-sm-1">RX Profit</th>
                                            <th class="col-sm-3"><span class="earned_span">Earned</span> <br>Compliance<br> Rewards</th>
                                            <th class="col-sm-1">Orig Pt Copay</th>
                                            <th class="col-sm-3"><span class="redeem_span">Redeemed</span> <br>Compliance <br>Rewards</th>
                                            <th class="col-sm-3"><span class="redeem_span">Redeemed</span> <br>Rewards<br>Cost</th>
                                            <th class="col-sm-1">Delivery Fee</th>
                                            <th class="col-sm-1">Final Pt Pay</th>
                                        </tr>
                                        <tr>
                                            <c:set var="rxAcqCost" value="${order.rxAcqCost * order.quantity}"/>
                                            <td><input type="text" id="acqCostFld" value="${order.rxAcqCost}" class="form-control isDecimalValue" onkeypress="return  float_validation(event)"/></td>
                                            <td><input type="text" id="reimbFld" value="${order.rxReimbCost}" class="form-control isDecimalValue" onblur="window.pharmacyNotification.calculateProfit('reimbFld', 'acqCostFld')" onkeypress="return  float_validation(event)"/></td>
                                            <td id="profitMarginCol">${order.profitMargin}</td>
                                            <td>${order.awardedPoints}</td>
                                            <td><input type="text" id="ptCopayFld" value="${order.originalPtCopay}" 
                                                       class="form-control isDecimalValue" onblur="window.pharmacyNotification.calculateFinalPay(
                                                                       'ptCopayFld', 'redeemPointsCostFld', 'handlingFeeFld', 'FeeFld')" onkeypress="return  float_validation(event)"/></td>
                                            <td >${order.redeemPoints}</td>
                                            <td id="redeemPointsCostFld">${order.redeemPointsCost}</td>
                                            <td id="handlingFeeFld">${order.handlingFee}</td>
                                            <td id="finalPaymentFeeFld">${order.finalPayment}</td>
                                        </tr>

                                    </table>
                                </div>
                                <%if (request.getParameter("profile") != null
                                            && request.getParameter("profile").equals("1")) {%>
                                <form  action="${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${orderId}/${patientId}?success=1&profile=1"
                                       method="post" id="form" name="form">  
                                    <%} else {%>
                                    <form  action="${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${orderId}/${patientId}?status=${status}&success=1"
                                           method="post" id="form" name="form">   
                                        <%}%>  
                                        <a id="backLnk" onclick="document.getElementById('form').submit(); return false;" class="btn btn_one" style="display:none"></a>
                                        <!-----------------------------------Saimoon--------------------------------->
                                        <div class="col-sm-12 clearfix details_workig">
                 
                                        <c:if test="${nextOrder eq 1}">
                                                <%if (request.getParameter("profile") != null
                                                            && request.getParameter("profile").equals("1")) {%>
                                                <input type="button" name="Next" value="" class="btn next_qq pull-right"
                                                       onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${nextOrderId}/${patientId}?status=${status}&profile=1&index=${index}'">
                                                <%} else {%>
                                                <input type="button" name="Next" value="" class="btn next_qq pull-right"
                                                       onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${nextOrderId}/${patientId}?status=${status}&index=${index}'">
                                                <%}%>
                                            </c:if> 
                                            <!------------------------------------------------------------------->
                                            
                                            <!------------------------------------------------------------------->

                                            <input type="button" name="save" value="Save" class="btn btn_dtail pull-right" onclick="needToConfirm = false;window.pharmacyNotification.processOrder(1,'');">

                                            <!--
                                            <input type="button" name="back" value="Clear" class="btn btn_dtail clear_btn_dt pull-right">
                                            -->
                                            <%if (request.getParameter("profile") != null
                                                        && request.getParameter("profile").equals("1")) {%>
                                            <input type="button" name="cancel" value="Cancel" class="btn btn_dtail pull-right" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/patientHistory/${patientId}'"/>
                                            <%} else {%>
                                            <input type="button" name="cancel" value="Cancel" class="btn btn_dtail pull-right" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/transferRxList/${orderId}/${patientId}?status=${status}'"/>
                                            <%}%>

                                            
                                            <a href="${pageContext.request.contextPath}/PharmacyPortal/successLogin/${orderId}?status=${order.status}" class="btn back_qq pull-right" name="back"></a>
                                        </div> 
                                        <!--------------------------------------------------------------------------->
                                    </form>         









                            </div>

                            <div class="col-sm-3 no_pp">
                                <c:if test="${not empty order.ptVideo}">
                                    
                                        <div id="videoDiv" class="video_div">
                                            <video id="videoContainer"   width="155" height="230" controls preload="none" >
                                                <source src="${order.ptVideo}" type="video/mp4">
                                            </video>
                                        </div>
                                        <div id="video_controls">
                                            <ul>
                                                <li><button class="btn btn_dtail clear_btn_dt" onclick="setPlaySpeed()" type="button" >Speed</button></li>

                                                <li><button class="btn btn_dtail clear_btn_dt" onclick="setZoom('1.25')">&frac14;</button></li>
                                                <li><button class="btn btn_dtail clear_btn_dt" onclick="setZoom('1.5')">&frac12;</button></li>
                                                <li><button class="btn btn_dtail clear_btn_dt" onclick="setZoom('1.75')">&frac34;</button></li>
                                                <li><button class="btn btn_dtail clear_btn_dt" id="play" onclick="vidplay()">||</button></li>
                                                <li><button class="btn btn_dtail clear_btn_dt"  onclick="setZoom('1')">Reset</button></li>
                                                <li><button class="btn btn_dtail clear_btn_dt"  onclick="displayFullScreen('1')">Full Screen</button></li>
                                            </ul>
                                        </div>
                                    </c:if>
                               

                                
                 
                                    <c:if test="${not empty order.transferImage}">
                                           
                                        <div class="video_div">
                                       
                                            <img src="${order.transferImage}" alt="" title="Click here to view full screen" onclick="displayFullScreen('2')"/>
                                        </div>
                                    </c:if>
                              
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
                                         
                                          <input type="button" 
                                               onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                               '', 'Transfer Failure', 'Pharmacy Notification', 'Transfer Failure');" 
                                               id="notCarried" class="btn btn_one" value="Transfer Failure" /> 
                                          
                                           <input type="button" 
                                               onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                               '', 'Payment Failure', 'Pharmacy Notification', 'Payment Failure');" 
                                               id="notCarried" class="btn btn_one" value="Payment Failure" /> 
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
                                               onclick="window.pharmacyNotification.openDrugRefillTooSoonDiv('medicationModalRefill',
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
                                                            '', 'Order Processed', 'RX Order', 'Order Processed');" id="processingLnk" class="btn btn_two" 
                                                   disabled="true"       value="PROCESSING" />
                                        </c:otherwise>
                                    </c:choose>

                                    <!---------------------------------------------------------------->
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
                                    <!---------------------------------------------------------------->

                                    <!--   <a id="processingLnk" href="#" class="btn btn_two" onclick="window.pharmacyNotification.checkProcessingStatusBit();">PROCESSING</a>-->

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


                            <div class="col-md-4 patient_custom">
                                <h4>Send Patient Custom Mesage</h4>
                                <form action="" class="messageForm">
                                    <i class="fa fa-paperclip fileUpload" title="upload file" onclick="window.pharmacyNotification.uploadFile()"></i>
                                    <input id="attachFile" type="file" style="display: none;" accept="image/*" onchange="window.pharmacyNotification.validateFileFormat();"/>
                                    <textarea id="messageId" name="message" class="messageBox" onclick="window.pharmacyNotification.resetMessageFn();" placeholder="Send Message to Patient"></textarea>
                                    <button type="button" onclick="window.pharmacyNotification.savePharmacyNotificationFn();"><i class="fa fa-paper-plane greenText"></i></button>
                                </form>
                                <a href="#" class="greenText messageHistory pull-left" onclick="displayModal(${order.orderId}, 'historyModal', 'viewHistoryMessageWs')">View Message History</a>
                                <div id="errorSendMessage" class="pull-right"></div>
                            </div>
                            <div class="col-md-8 cutome_tb">
                                <table class="dt_tables">
                                    <tr>
                                        <th class="col-sm-4">Delivery<br />Address</th>
                                        <th class="col-sm-1">Delivery Zip&nbsp;Code</th>
                                        <th class="col-sm-1">Delivery Requested</th>
                                        <th class="col-sm-2">Delivery Fee&nbsp;Collected</th>
                                        <th class="col-sm-1">Delivery Carrier</th>
                                        <th class="col-sm-2">Tracking&nbsp;#</th>
                                        <th class="col-sm-1">Clerk</th>
                                    </tr>
                                    <tr>
                                        <td><strong>Address 1:</strong> 3201 Fannin LN</td>
                                        <td>76092</td>
                                        <td>Next Day</td>
                                        <td>$3.75</td>
                                        <td><select id="deliverycarrier" class="form-control">
                                                <option value="US MAil"> US MAIL</option>
                                                <option value="UPS">UPS</option>
                                                <option value="FEDEX" >FEDEX</option>
                                            </select></td>
                                            <td><input id="traclingno" type="text" value="" class="form-control" onkeypress="return IsAlphaNumeric2(event)"/></td>
                                            <td><select id="clerk" class="form-control">
                                                    <option value="aaaa">aaaa</option>
                                                    <option value="bbbbb">bbbbb</option>
                                                    <option value="cccc">cccc</option>
                                            </select></td>
                                    </tr>

                                </table>
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

        <script type="text/javascript">
                    
            function checkSelectedValue(elem)
            {
                var val=document.getElementById(elem).value;
                if(val==null || val=='')
                {
                    alert("You have selected an invalid value");
                    document.getElementById(elem).selectedIndex=0;
                }
                //alert("val "+val);
            }
                    
            function vidplay() {
                var video = document.getElementById("videoContainer");
                var button = document.getElementById("play");
                if (video.paused) {
                    video.play();
                    button.textContent = "||";
                } else {
                    video.pause();

                }


            }
            var vid = document.getElementById("videoContainer");



            function setPlaySpeed() {
                vid.playbackRate = 3.0;

            }
            function setZoom(point) {
                /* predefine rotate */
                var rotate = 0;
                var properties = ['transform', 'WebkitTransform', 'MozTransform',
                    'msTransform', 'OTransform'],
                        prop = properties[0];
                /* Iterators and stuff */
                var i, j;

                /* Find out which CSS transform the browser supports */
                for (i = 0, j = properties.length; i < j; i++) {
                    if (typeof vid.style[properties[i]] !== 'undefined') {
                        prop = properties[i];
                        break;
                    }
                }
                vid.style[prop] = 'scale(' + point + ') rotate(' + rotate + 'deg)';
                $("#videoDiv").attr("style", "overflow:auto;");
            }
            function setFullScreen()
            {


                var videoElement = document.getElementById('videoContainer');
                videoElement.webkitRequestFullScreen();
            }

            function ZoomIn(frontImage) {
                // alert("hi");
                var ZoomInValue = parseInt(document.getElementById(frontImage).style.zoom) + 1 + '%'
                document.getElementById(frontImage).style.zoom = ZoomInValue;
                return false;
            }

            function ZoomOut() {
                var ZoomOutValue = parseInt(document.getElementById("stuff").style.zoom) - 1 + '%'
                document.getElementById("stuff").style.zoom = ZoomOutValue;
                return false;
            }
            function hideFullScreen(id) {
                $("#" + id).attr("style", "display:none");
            }
            function displayFullScreen(param) {
                var html = "";
                if (param == 1) {
                    html = '<a id="btnClose" href="#" class="close_full_video" onclick="hideFullScreen(\'full_video_container\')" title="Close">X</a> <div id="full_width_videoDiv" class="video_div">';
                    html += '<video id="full_width_videoContainer"   width="155" height="230" controls preload="none" >';
                    html += '<source src="${order.ptVideo}" type="video/mp4">';
                    html += '</video>'
                    html += '</div>';
                } else {
                   // alert("A");
                    html = '<a id="btnClose" href="#" class="close_full_video" onclick="hideFullScreen(\'full_video_container\')" title="Close">X</a> <div id="full_width_videoDiv" class="video_div">';
                    html += '<img src="${order.transferImage}" alt="">';
                    html += '</div>';
                }
               //alert("B");
                $("#full_video_container").html(html);
                //alert("C");
                $("#full_video_container").removeAttr("style");
                scaleVideo();
            }
            function scaleVideo() {
                var vid = document.getElementsByTagName('video')[0];
                var w = window.innerWidth;
                var h = window.innerHeight;

                if (w / 16 >= h / 9) {
                    vid.setAttribute('width', w);
                    vid.setAttribute('height', 'auto');
                } else {
                    vid.setAttribute('width', 'auto');
                    vid.setAttribute('height', h);
                }
            }
        </script>

    </body>
    <script language="JavaScript">
    populateArrays();
  </script>
</html>
