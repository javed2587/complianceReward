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
        var ids = new Array('rxNumberFld', 'drugTypeId', 'drugStrength', 'drugQtyFld', 'daysSupplyFld', 'refillAllowedFld', 'refillUsedFld', 'paymentFld',
                'pharmacyNameFld', 'pharmacyPhoneFld', 'prescriberNameFld', 'prescriberPhoneFld', 'prescriberNPIFld', 'acqCostFld', 'reimbFld',
                'ptCopayFld');


        var values = new Array('', '', '', '', '', '', '', '',
                '', '', '', '', '', '', '', '');

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

        needToConfirm = false;
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
    <body >
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
        <input id="patientId" type="hidden" value="${patientProfile.id}"/>
        <input id="orderId" type="hidden" value="${order.orderId}"/>
        <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
        <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.dob}"/>
        <!--Registration Page-->

        <div class="">
            <div class="col-md-12">
                <div class="tableContainer patient_info row">
                    <div class="patient_name">
                        <h3>${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(patientProfile.lastName)} &nbsp;<span><fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.dob}"/> <span>(${patientProfile.gender})  ${nowyear-dobyear} yrs</span></span></h3>
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
                                            <fmt:formatDate var="memberdobyear" pattern="yyyy" value="${members.dob}"/>
                                            <li> <i>${theCount.count}</i>
                                                <h3>${members.firstName}&nbsp;${members.lastName}
                                                    <span><fmt:formatDate pattern="MM/dd/yyyy" value="${members.dob}"/>   (${members.gender})  

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
                            <li>Member Since <span><fmt:formatDate value='${patientProfile.createdOn}' pattern='yyyy-MM-dd'/></span></li>
                        </ul>
                        <span class="icon_Status"> ${patientProfile.totalRewardPoints}<br />
                            Basic </span> 
                            <c:if test="${patientProfile.isOldPatient ne true}">
                            <span class="icon_unew"></span> 
                        </c:if>
                    </div>
                    <!--Top Nav ends-->

                    <div class="customTableRow">

                        <%if (request.getParameter("success") != null
                                    && request.getParameter("success").equals("1")) {%>
                        <div id="popUpException">
                            <span style="color:green"><strong>Order has been updated successfully.</strong></span>
                        </div>
                        <%}%> 
                        <div id="popUpException"></div>

                        <c:set var="deliveryPrefereceDeliveryDisabledFlag" value="${order.deliveryService eq 'Pick Up at Pharmacy' ?'disabled':''}" />
                        <c:set var="deliveryPreferecePharmacyDisabledFlag" value="${order.deliveryService ne 'Pick Up at Pharmacy' ?'disabled':''}" />

                        <c:set var="statusBtnDisabledFlag" value="${order.status eq 'DELIVERY' || 
                                                                    order.status eq 'Pickup At Pharmacy'  || order.status eq 'Shipped' ||    order.status eq 'Cancelled' ?'disabled':''}"/>

                        <c:set var="fieldsDisabledFlag" value="${order.status eq 'DELIVERY' || order.status eq 'Ready to Fill' || order.status eq 'Filled'|| 
                                                                 order.status eq 'Waiting payment authorization'|| order.status eq 'Payment Authorized' ||    order.status eq 'Shipped'
                                                                 || order.status eq 'Pickup At Pharmacy' || order.status eq 'Cancelled' ?'disabled':''}"/>
                        <c:set var="shippedFlag" value="${order.status ne 'Filled' ?'disabled':''}"/>
                        <c:set var="shippedBtnFlag" value="${order.status ne 'Filled' ?'disabled':''}"/>
                        <c:set var="filledBtnFlag" value="${order.status eq 'Fill Request Accepted'  ?'':'disabled'}"/>
                        <c:set var="readyFilledBtnFlag" value="${order.status eq 'Pending' || order.status eq 'Ready to Fill' || order.status eq 'Ready to Fill' || order.status eq 'Pending Pharmacy Reply' || order.status eq 'Filled' || order.status eq 'DELIVERY' ||order.status eq 'Shipped'  ||    order.status eq 'Cancelled' ||order.status eq 'Waiting payment authorization' ||order.status eq 'Ready to Fill' ||order.status eq 'Fill Request Accepted'?'disabled':''}"/>
                        <c:set var="processingBtnFlag" value="${order.status eq 'Filled' || order.status eq 'DELIVERY' || order.status eq 'Shipped' || order.status eq 'Cancelled' || order.status eq 'Processing' ||order.status eq 'Waiting payment authorization' ||order.status eq 'Ready to Fill' ||order.status eq 'Fill Request Accepted' ||order.status eq 'Payment Authorized'?'disabled':''}"/>
                        <c:set var="pickUpFromPharmacyFlag" value="${order.status eq 'Pickup At Pharmacy' ?'disabled':''}"/>
                        <div class="col-md-12"> 
                            <div class="transfer_wrap clearfix">
                                <div class="col-sm-12 col-lg-7 set_table">
                                    <div class="order_details_h clearfix">
                                        <h2>Order Detail-- ${order.orderId}</h2>

                                        <span id="orderStatusSpan">${order.status}</span>
                                        <small><fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd'/> @ <strong><fmt:formatDate value='${order.receivedDate}' pattern='hh:mm:ss'/></strong></small>
                                    </div>
                                    <div id="full_video_container" class="full_video_container" style="display: none;"> </div>

                                    <div class="rx_detail_table col-sm-10"><table class="dt_tables">
                                            <tbody>
                                                <tr>

                                                    <th>Request<br />
                                                        Type</th>
                                                    <th style="min-width: 66px;">Original<br />
                                                        Date</th>
                                                    <th>RX&nbsp;Number</th>
                                                    <th class="text-center">RX Name /<br />Strength </th>
                                                    <th>QTY</th>
                                                    <th>Days<br />Supply</th>
                                                    <th>Refills<br />Allowed</th>
                                                    <th>Refills<br />Remain</th>
                                                    <!--
                                                    <th><span class="exp_caledar"><i class="fa fa-calendar" aria-hidden="true"></i></span>EXP<br />Date</th>
                                                    -->
                                                    <th>EXP<br />Date </th>

                                                </tr>
                                            <input type="hidden" id="orderTypeFld" value="${order.orderType}" >
                                            <tr>

                                                <td><strong>${order.orderType}</strong></td>
                                                <td class="rxd_dte">
                                                    <input id="datetimepicker" class="form-control" placeholder="mm/dd/yyyy" 
                                                           onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"
                                                           value="<fmt:formatDate value='${order.rxDate}' pattern='MM/dd/yyyy'/>" ${fieldsDisabledFlag} />
                                                </td>
                                                <td class="name_number" style="width:70px;" >
                                                    <input type="text" id="rxNumberFld" value="${order.rxNumber}" style="min-width:66px; margin-bottom:5px;" class="form-control"  maxlength="20"${fieldsDisabledFlag} maxlength="20" onkeypress="return IsAlphaNumericWithHyphen(event)"></td>
                                                <td class="name_strenght_one"> 
                                                    <input type="text" id="drugNameId" name="" style="min-width:50px;"  value="${order.drugNameWithoutStrength}" class="form-control" 
                                                           onblur="needToConfirm = false;
                                                                   window.transferRequest.lookUpDrugTypeByBrandName();" 
                                                           oninput="window.transferRequest.lookUpDrugName2();" ${fieldsDisabledFlag}>
                                                    <select id="drugTypeId" onchange="checkSelectedValue('drugTypeId')" 
                                                            onblur="needToConfirm = false;
                                                                    window.transferRequest.lookUpDrugStrengthByBrandNameAndType();" class="form-control" ${fieldsDisabledFlag}>
                                                        <option value="${order.drugType}">${order.drugType}</option>

                                                    </select>
                                                    <select id="drugStrength" class="form-control" ${fieldsDisabledFlag}>
                                                        <option value="${order.strength}">${order.strength}<option>
                                                    </select>

                                                </td>
                                                <td>
                                                    <input type="text" id="drugQtyFld" value="${order.quantity}" class="form-control small_field1" 
                                                           onblur="needToConfirm = false;
                                                                   window.transferRequest.lookUpDrugCost();" onkeypress="return IsDigit(event)"
                                                           ${fieldsDisabledFlag}>
                                                </td>
                                                <td>
                                                    <input type="text" id="daysSupplyFld" value="${not empty order.daysSupply?order.daysSupply:''}" class="form-control small_field2" 
                                                           onkeypress="return IsDigit(event)" ${fieldsDisabledFlag}>
                                                </td>
                                                <td>
                                                    <input type="text" id="refillAllowedFld" value="${order.refillsAllowed}" class="form-control small_field3" onkeypress="return IsDigit(event)" ${fieldsDisabledFlag}>
                                                </td>
                                                <td>
                                                    <input type="text" id="refillUsedFld" value="${order.refillsRemaining}" class="form-control small_field3" onkeypress="return IsDigit(event)" ${fieldsDisabledFlag}>

                                                </td>
                                                <td class="exp_redate"><fmt:formatDate value='${order.rxExpiredDate}' pattern='yyyy-MM-dd'/>

                                                </td> 

                                                <!-- <input type="hidden" id="paymentFld" value="Cash"/>-->
                                            </tr>
                                            </tbody>
                                        </table>
                                        <table class="dt_tables">
                                            <tbody>
                                                <tr> 
                                                    <th class="col-sm-1">Ins /<br />
                                                        Cash</th>
                                                    <!-- <th class="col-sm-3">Patient</th>-->
                                                    <th class="col-sm-3">Pharmacy Name</th>
                                                    <th class="col-sm-2">Pharmacy Phone</th>
                                                    <th class="col-sm-3">Prescriber Name</th>
                                                    <th class="col-sm-2">Prescriber Phone</th>
                                                    <th class="col-sm-2">Prescriber Npi</th>
                                                </tr>
                                                <tr> 
                                                    <!--   <td><input type="text" id="patientNameFld" value="Riaz  Ahmad" class="form-control" /></td>-->
                                                    <td>
                                                        <input type="text" id="paymentFld" class="form-control" style="width:55px;" ${fieldsDisabledFlag}
                                                               value="${order.paymentMode}" disabled="true">


                                                    </td>
                                                    <td>
                                                        <input type="text" id="pharmacyNameFld" value="${not empty order.pharmacyName?order.pharmacyName:''}" class="form-control" ${fieldsDisabledFlag}>

                                                    </td>
                                                    <td>
                                                        <input type="text" id="pharmacyPhoneFld" value="${not empty order.pharmacyPhone?order.pharmacyPhone:''}" class="form-control" 
                                                               onkeypress="return IsDigitWithHyphen(event)" maxlength="12" ${fieldsDisabledFlag}>

                                                    </td>
                                                    <td>
                                                        <input type="text" id="prescriberNameFld" value="${not empty order.prescriberName?order.prescriberName:''}" class="form-control" 
                                                               onkeypress="return onlyAlphabets(event)" ${fieldsDisabledFlag}>

                                                    </td>
                                                    <td>
                                                        <input type="text" id="prescriberPhoneFld" value="${not empty order.prescriberPhone?order.prescriberPhone:''}" class="form-control" 
                                                               onkeypress="return IsDigitWithHyphen(event)" maxlength="12" ${fieldsDisabledFlag}>

                                                    </td>
                                                    <td>
                                                        <input type="text" id="prescriberNPIFld" value="${not empty order.prescriberNPI?order.prescriberNPI:''}" class="form-control" 
                                                               onkeypress="return IsDigit(event)" maxlength="10" ${fieldsDisabledFlag}>

                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table></div>
                                    <div class="type_comment col-sm-2">
                                        <div class="patient_coment_list">
                                            <c:forEach var="question" items="${questions}" >
                                                <p>${question.question}
                                                    <br>(${question.questionTimeStr})
                                                </p>
                                            </c:forEach>
                                        </div>
                                            <!--<textarea id="orderComments" class="form-control" placeholder="Type Comment">${not empty order.comments?order.comments:''}</textarea>-->
                                    </div>
                                    <div class="col-md-12 del_det_ful">
                                        <div class="rx_detail_table det_tab_ne">
                                            <!--<div class="order_details_h clearfix">
                                            <fmt:formatNumber var="totalDrugPrice" value="${order.totalDrugPrice}" pattern="#.00"/>
                                            <input id="estimatedDrugPrice" type="hidden" value="${totalDrugPrice}"/>
                                            <h2 id="estimatedDrugPriceH">Estimated Drug Price $ ${totalDrugPrice}</h2>

                                        </div>-->
                                            <input type="hidden" id="estimatedDrugPrice" value="${totalDrugPrice}"/>
                                            <input id="paymentType" type="hidden" value="${order.paymentType}"/>
                                            <input id="insuranceType" type="hidden" value="${order.insuranceType}"/>
                                            <table class="dt_tables">
                                                <tbody>
                                                    <tr>
                                                        <th>Public<br />Ins</th>
                                                        <th class="redeem_span">Cash<br />Pay</th>
                                                        <th>Ingredient<br />Cost</th>
                                                        <th>3<sup>rd</sup>&nbsp;Party<br />Reimbursement</th>
                                                        <th>PT OOP<br />/Copay</th>
                                                        <th>Selling<br />Price</th>
                                                        <th>Calculated<br />Profit</th>
                                                        <!--
                                                        <th>Profit<br />Share</th>
                                                        <th>MFR<br />Value</th>
                                                        <th>Compliance <br />Reward</th>-->
                                                        <th>Profit Share Point</th>
                                                        <th>Profit Share Cost</th>
                                                        <th>Service</th>
                                                        <th><span class="earned_span">Delivery</span><br />Fee</th>
                                                        <th>Final<br />Pt&nbsp;Pay</th>
                                                    </tr>
                                                    <tr>

                                                        <%/*
                                                        <c:choose>
                                                            <c:when test="${order.finalPaymentMode eq 'INSURANCE'}">
                                                                <td style="text-align:center;">
                                                                    <label><input type="radio" name="q" id="radio-a" value="Public" ${not empty order.insuranceType && order.insuranceType eq 'Public'?'"checked=checked"':''}></label>
                                 
                                                                </td>
                                                                <td style="text-align:center;">
                                                                    <label><input type="radio" name="q" id="radio-b" value="Cash" ></label>
                                                                </td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td style="text-align:center;">
                                                                    <label><input type="radio" name="q" id="radio-a" value="Public" ></label>
                                                                </td>
                                                                <td style="text-align:center;">
                                                                    <label>
                                                                        <input type="radio" name="q" id="radio-b" value="Cash" checked="checked">
                                                                    </label>
                                                                </td>
                                                            </c:otherwise>
                                                        </c:choose>*/%>
                                                        <td style="text-align:center;">
                                                            <label><input type="radio" name="q" id="radio-a" value="Public" ${order.insuranceCheck} ${fieldsDisabledFlag}></label>

                                                        </td>
                                                        <td style="text-align:center;">
                                                            <label><input type="radio" name="q" id="radio-b" value="Cash" ${order.selfPayCheck} ${fieldsDisabledFlag}></label>
                                                        </td>
                                                        <td>                                     
                                                            <input type="text" id="acqCostFld" value="${order.rxAcqCost}" class="form-control isDecimalValue" onkeypress="return  float_validation(event)"
                                                                   ${fieldsDisabledFlag}>
                                                        </td>
                                                        <td>
                                                            <input type="text" id="reimbFld" value="${order.rxReimbCost}" class="form-control isDecimalValue" 

                                                                   onkeypress="return  float_validation(event)" ${fieldsDisabledFlag} disabled>


                                                        </td>
                                                        <td>
<!--                                                            <input type="text" id="ptCopayFld" value="${order.originalPtCopay}" class="form-control isDecimalValue" onblur="needToConfirm = false;window.pharmacyNotification.calculateFinalPtPay(
                                                                            'ptCopayFld', 'redeemPointsCostFld', 'handlingFeeFld', 'finalPaymentFeeFld', '${order.redeemPoints}')" 
                                                                   onkeypress="return  float_validation(event)" ${fieldsDisabledFlag}>-->
                                                            <input type="text" id="ptCopayFld" value="${order.originalPtCopay}" class="form-control isDecimalValue" 
                                                                   onblur="needToConfirm = false;
                                                                           window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');
                                                                           window.transferRequest.calculatePayment();" 
                                                                   onkeypress="return  float_validation(event)" ${fieldsDisabledFlag} >

                                                        </td>
                                                        <td><input type="text" id="sellingPriceFld" value="${totalDrugPrice}" class="form-control" onkeypress="return  float_validation(event)"
                                                                   onblur="window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');" 
                                                                   ${fieldsDisabledFlag}></td>
                                                        <td><input type="text" id="profitMarginCol" value="${order.profitMargin}" class="form-control 
                                                                   isDecimalValue" 
                                                                   onblur="needToConfirm = false;
                                                                            window.transferRequest.lookUpProfitCalculation();" 
                                                                   onkeypress="return  float_validation(event)" ${fieldsDisabledFlag}></td>
                                                <input type="hidden" id="profitShareFld" value="${not empty order.profitShareCost?order.profitShareCost:'0.0'}" class="form-control isDecimalValue" onblur="calculateMFRCost(${totalDrugPrice}, this.value,${order.finalPayment},${order.handlingFee})" onkeypress="return  float_validation(event)">
                                                <input type="hidden" id="mfrCostFld" value="${not empty order.mfrCost?order.mfrCost:'0.0'}" class="form-control isDecimalValue" onblur="calculateMFRCost(${totalDrugPrice}, this.value,${order.finalPayment},${order.handlingFee})" onkeypress="return  float_validation(event)">
                                                <input type="hidden" id="profitSharePoints" value="0" >
                                                <input type="hidden" id="olversion" value="<fmt:formatDate value='${order.olversion}' pattern='yyyy-MM-dd HH:mm:ss'/>"  />
                                                <!-- <td>${order.awardedPoints}</td>-->
                                                <td id="totalRedeemPointsId">${order.profitSharePoint}</td>
                                                <td id="redeemPointsCostFld">${order.actualProfitShare}</td>
                                                <td><span class="red_txt">${order.deliveryService}</span></td>
                                                <td id="handlingFeeFld">${order.handlingFee}</td>
<!--                                                        <td id="finalPaymentFeeFld">${order.finalPayment}</td>-->
                                                <td><input type="text" id="finalPaymentFeeFld" value="${order.finalPayment}" class="form-control isDecimalValue" onkeypress="return  float_validation(event)" ${fieldsDisabledFlag}></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" style="text-align:center; padding-top:0">
                                                        <c:choose>
                                                            <c:when test="${order.status eq 'Shipped'  || 
                                                                            order.status eq 'DELIVERY' || 
                                                                            order.status eq 'Waiting payment authorization'  ||
                                                                            order.status eq 'Cancelled' ||
                                                                            order.status eq 'Ready to Fill' || 
                                                                            order.status eq 'Filled' }">
                                                                    &nbsp;
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="javascript:;" id="clear-button" >Reset</a>

                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td> 
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                                </tbody>
                                            </table>


                                        </div>
                                    </div>
                                    <!--
                                    <div class="col-md-12 del_det_ful">
                                      <div class="rx_detail_table"><table class="dt_tables">
                                        <tbody>
                                          <tr>
                                            <th class="col-sm-2">RX Acq Cost</th>
                                            <th class="col-sm-1">RX Reimb</th>
                                            
                                            <th class="col-sm-1">Orig Pt Copay</th>
                                            <th>RX Profit</th>
                                            <th><span class="earned_span">Earned</span> <br>
                                              Compliance<br>
                                              Rewards</th>
                                            <th><span class="redeem_span">Redeemed</span> <br>
                                              Compliance <br>
                                              Rewards</th>
                                            <th><span class="redeem_span">Redeemed</span> <br>
                                              Rewards<br>
                                              Cost</th>
                                            <th>Delivery Fee</th>
                                            <th>Final Pt Pay</th>
                                          </tr>
                                          <tr>
                                            <td>
                                                
                                                                                     
                                                <input type="text" id="acqCostFld" value="${order.rxAcqCost}" class="form-control isDecimalValue" onkeypress="return  float_validation(event)"
                                    ${fieldsDisabledFlag}>
          
                      
                             
                         </td>
                         <td>
                            <input type="text" id="reimbFld" value="${order.rxReimbCost}" class="form-control isDecimalValue" 
                                   onblur="needToConfirm = false;window.pharmacyNotification.calculateProfit('reimbFld', 'acqCostFld')" 
                                   onkeypress="return  float_validation(event)" ${fieldsDisabledFlag}>
   
                                          
                         </td>
                         
                         
                         <td>
                             <input type="text" id="ptCopayFld" value="${order.originalPtCopay}" class="form-control isDecimalValue" onblur="needToConfirm = false;window.pharmacyNotification.calculateFinalPtPay(
                                                                          'ptCopayFld', 'redeemPointsCostFld', 'handlingFeeFld', 'finalPaymentFeeFld','${order.redeemPoints}')" 
                                                                            onkeypress="return  float_validation(event)" ${fieldsDisabledFlag}>
   
                                         
                          
                             
                         </td>
                        
                         <td>${order.awardedPoints}</td>
                         <td id="totalRedeemPointsId">${order.redeemPoints}</td>
                         <td id="redeemPointsCostFld">${order.redeemPointsCost}</td>
                         <td id="handlingFeeFld">${order.handlingFee}</td>
                         <td id="finalPaymentFeeFld">${order.finalPayment}</td>
                       </tr>
                     </tbody>
                   </table></div>
                 </div>-->
                                    <jsp:include page="../patientprofile/inAppNotificationMsgs.jsp" />
                                    <div class="medication_wrap clearfix">
                                        <div class="col-sm-12 no-paading">
                                            <!--<h2>MEDICATION SPECIFIC MESSAGE <span>-- SEND PATIENT MESSAGE (<i>in app</i>)</span></h2>-->
                                            <div class="clearfix">
                                                <div class="rx_nav_btns"><form action="/ConsumerPortal/rxDetail/${orderId}/${patientId}?success=1&profile=1&status=${order.status}" method="post" id="form" name="form">
                                                        <a id="backLnk" onclick="document.getElementById('form').submit(); return false;" class="btn btn_one" style="display:none"></a> 

                                                        <div class="col-sm-12 clearfix details_workig">
                                                            <c:if test="${not empty nextOrderId}"> 
                                                                <input type="button" id="Next" name="Next" value="" class="btn next_qq pull-right" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${nextOrderId}/${patientId}?status=${status}&profile=1&index=${index}'">
                                                            </c:if>
                                                            <c:choose>
                                                                <c:when test="${order.status eq 'Shipped' || order.status eq 'Pickup At Pharmacy' ||  order.status eq 'DELIVERY' || order.status eq 'Waiting payment authorization'  || order.status eq 'Cancelled' || order.status eq 'Ready to Fill' || order.status eq 'Filled'}">
                                                                    <input type="button" id="saveBtn" name="save" value="Save" class="btn btn_dtail pull-right" disabled="true" onclick="needToConfirm = false;
                                                                window.pharmacyNotification.processOrder(1, '');">    
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="button" id="saveBtn" name="save" value="Save" class="btn btn_dtail pull-right" onclick="needToConfirm = false;
                                                                window.pharmacyNotification.processOrder(1, '');">
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <input type="button" id="cancel" name="cancel" value="Cancel" class="btn btn_dtail pull-right" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/patientHistory/${patientId}'">
                                                            <a href="${pageContext.request.contextPath}/PharmacyPortal/successSignin/${orderId}?status=${order.status}" class="btn back_qq pull-right" name="back"></a> </div>

                                                    </form></div>
                                                <h2>MEDICATION SPECIFIC MESSAGE - in app msgs</h2>

                                            </div>
                                            <div id="statusException"></div>
                                            <div class="col-md-3 col-sm-3 medi_col">
                                                <h4>NOT CARRIED/Out of STOCK</h4>
                                                <input type="button" 
                                                       onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       'CII', 'Drug can not fill', 'Pharmacy Notification', 'Cannot Fill');" 
                                                       id="canNotFill" class="btn btn_one" value="CII-CANNOT FILL MEDICATION" ${statusBtnDisabledFlag}/> 
                                                <input type="button" 
                                                       onclick="needToConfirm = false;
                                                               window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       '', 'Rx No Carried for Now', 'Pharmacy Notification', 'Rx No Carried');" 
                                                       id="notCarried" class="btn btn_one" value="MEDICATION NOT CARRIED" ${statusBtnDisabledFlag}/>
                                                <input type="button" 
                                                       onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       '', 'Transfer Failure', 'Pharmacy Notification', 'Transfer Failure');" 
                                                       id="notCarried" class="btn btn_one" value="TRANSFER REQUEST FAILURE" ${statusBtnDisabledFlag}/> 
                                                <input type="button" 
                                                       onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       '', 'Payment Failure', 'Pharmacy Notification', 'Payment Failure');" 
                                                       id="notCarried" class="btn btn_one" value="PAYMENT CARD FAILED" ${statusBtnDisabledFlag}/> 
                                            </div>
                                            <div class="col-md-3 col-sm-3 medi_col">
                                                <h4 class="greentitle">CALLING MD</h4>
                                                <input type="button" 
                                                       onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       '', 'No Refills; Calling HCP', 'Pharmacy Notification', 'No Refill Remaining');" 
                                                       id="canNotFill" class="btn btn_one greenbtn" value="NO REFILLS-CALLING HCP" ${statusBtnDisabledFlag} /> 
                                                <input type="button" 
                                                       onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       '', 'Rx Expired; Calling HCP', 'Pharmacy Notification', 'Rx Expired');" 
                                                       class="btn btn_one greenbtn" value="CALLING HCP TO RENEW YOUR" ${statusBtnDisabledFlag} />
                                                <input type="button" 
                                                       onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       '', 'HCP CURRENTLY DELAYED', 'Pharmacy Notification', 'HCP CURRENTLY DELAYED');" 
                                                       class="btn btn_one greenbtn" value="HCP HAS NOT REPLIED YOUR" ${statusBtnDisabledFlag} />
                                                <input type="button" 
                                                       onclick="window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                       '', 'MD Declined – Can not fill', 'Pharmacy Notification', 'MD Declined');" 
                                                       class="btn btn_one greenbtn" value="HCP DECLINED AUTHORIZATION" ${statusBtnDisabledFlag}/>
                                            </div>
                                            <div class="col-md-3 col-sm-3 medi_col">
                                                <h4 class="pinktitle">INS DECLINED</h4>
                                                <input type="button" onclick="needToConfirm = false;window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                '', 'Refill too Soon- $ Option', 'Pharmacy Notification', 'Refill too Soon');" class="btn btn_one pinkbtn" value="REFILL TOO SOON" ${statusBtnDisabledFlag}>
                                                <input type="button" onclick="needToConfirm = false;
                                                        window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                '', 'Ins Declined-  $ Option', 'Pharmacy Notification', 'Ins Declined');" class="btn btn_one pinkbtn" value="INSURANCE CARD FAILED" ${statusBtnDisabledFlag}> 
                                                <input type="button" onclick="needToConfirm = false;
                                                        window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                '', 'Step Edit / PA-  $ Option', 'Pharmacy Notification', 'Step Edit PA');" class="btn btn_one pinkbtn" value="STEP EDIT/PA-$ OPTION" ${statusBtnDisabledFlag}>
                                                <input type="button" onclick="needToConfirm = false;
                                                        window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                '', 'Ins not Accepted-- $', 'Pharmacy Notification', 'Ins Not Accepted');" class="btn btn_one pinkbtn" value="INS NOT ACCEPTED" ${statusBtnDisabledFlag}>
                                            </div>
                                            <div class="col-md-3 col-sm-3 medi_col">
                                                <h4 class="redtitle">ADMIN  STATUS</h4>
                                                <input type="hidden" id="WaitingpaymentauthorizationID" value="0" />
                                                <input type="hidden" id="processingID" value="0" />
                                                <input type="hidden" id="pending" value="0" />
                                                <input type="hidden" id="delayed" value="0" />
                                                <input type="hidden" id="courier" value="0" />
                                                <input type="hidden" id="shipped" value="0" />
                                                <input type="hidden" id="statusBit" value="0" />
                                                <input type="hidden" id="nextStatus" value="0" />

                                                <c:if test="${not empty order.ptVideo || not empty order.transferImage}">
                                                    <input type="hidden" onclick="window.pharmacyNotification.checkOtherStatusBitHandler(17, 'Interpreted Image');" id="interpretedImageLnk" class="btn btn_one redbtn" 
                                                           value="Interpreted Image" ${statusBtnDisabledFlag} />
                                                </c:if>
                                                
                                                <input type="button" onclick="window.pharmacyNotification.checkProcessingStatusBitHandler();" id="processingLnk" class="btn btn_one redbtn" 
                                                       value="NOW PROCESSING" ${statusBtnDisabledFlag} />

                                                <input type="button"   id="pendingLnk" class="btn btn_one redbtn" 
                                                       onclick="window.pharmacyNotification.checkOtherStatusBitHandler(3, 'Pending Pharmacy Reply');"
                                                       value="PENDING PHRMCY REPLY" ${statusBtnDisabledFlag}/>



                                                <input type="button" onclick="window.pharmacyNotification.checkOtherStatusBitHandler(9, 'Waiting payment authorization');" id="paymentLnk" class="btn btn_one redbtn" 
                                                       value="PAYMENT AUTHORIZATION" ${statusBtnDisabledFlag}/>


                                                <!--
                                                                                                 <input type="button"   id="readyFilledlnk" class="btn btn_one redbtn"  
                                                                                               value="READY TO FILL"  onclick="window.pharmacyNotification.checkOtherStatusBit('medicationModal',
                                                                                                                   '', 'Order Processed', 'RX Order', 'Order Processed', 13);"  />
                                                                                                
                                                -->                                              
                                                <input type="button"   id="filledlnk" class="btn btn_one redbtn"  
                                                       value="Rx DISPENDED"  onclick="window.pharmacyNotification.checkOtherStatusBitHandler(13, 'Ready to Fill');"  ${statusBtnDisabledFlag}/>



                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12 cutome_tb">
                                        <div class="rx_detail_table"><table class="dt_tables">
                                                <tbody>
                                                    <tr>
                                                        <th class="col-sm-3">Delivery&nbsp;Address</th>
                                                        <th>Delivery<br />Alias</th>
                                                        <th class="col-sm-1">Zip<br />Code</th>
                                                        <th style="min-width:60px;">Multi<br />Rx</th>
                                                        <th class="col-sm-1">Delivery<br />Requested</th>
                                                        <th class="col-sm-1">Fee<br />Collected</th>
                                                        <th>Waive<br />Fee</th>
                                                        <th class="col-sm-2">Carrier</th>
                                                        <th class="col-sm-2">Tracking&nbsp;#</th>
                                                        <th class="col-sm-2">Clerk</th>
                                                    </tr>
                                                    <tr> 
                                                        <td class="address-td">${patientProfile.defaultAddress}</td>
                                                        <td>&nbsp;</td>
                                                        <td>76092</td>
                                                        <td>&nbsp;</td>
                                                        <td>Next Day</td>
                                                        <td>$3.75</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${order.deliveryFeeWaived eq '1'}" >
                                                                    <a href="#" class="wave_yes"><i class="fa fa-check-circle" aria-hidden="true"></i></a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                    <a href="#" class="wave_cross"><i class="fa fa-times-circle-o" aria-hidden="true"></i></a>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                        </td>
                                                        <td>
                                                            <select id="deliverycarrier" style="min-width:50px;" class="form-control" ${shippedFlag}>
                                                                <option value="US MAil" ${order.carrier eq 'US MAil'? 'selected':''}> US MAIL</option>
                                                                <option value="UPS" ${order.carrier eq 'UPS'? 'selected':''}>UPS</option>
                                                                <option value="FEDEX" ${order.carrier eq 'FEDEX'? 'selected':''}>FEDEX</option>
                                                                <option value="Hand Delivery" ${order.carrier eq 'Hand Delivery'? 'selected':''}> Hand Delivery</option>
                                                            </select>



                                                        </td>
                                                        <td>
                                                            <input id="traclingno" type="text" value="${order.tracking}" class="form-control" onkeypress="return IsAlphaNumeric2(event)" ${shippedFlag}>

                                                        </td>
                                                        <td>
                                                            <input id="clerk" class="form-control" value="${order.clerk}" ${shippedFlag}>

                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table></div>
                                    </div>


                                    <div class="col-sm-8 top10_padding"><div class="textBox detail_comment">
                                            <form action="" class="messageForm">
                                                <i class="fa fa-paperclip fileUpload" title="upload file" onclick="window.pharmacyNotification.uploadFile()"></i>
                                                <input id="attachFile" type="file" style="display: none;" accept="image/*" onchange="window.pharmacyNotification.validateFileFormat();"/>
                                                <textarea id="messageId" name="pharmacyNote" class="messageBox" onclick="window.pharmacyNotification.resetMessageFn();" placeholder="Send Message to Patient"></textarea>
                                                <button type="button" onclick="window.pharmacyNotification.savePharmacyNotificationFn();"><i class="fa fa-paper-plane greenText"></i></button>
                                                <div id="errorSendMessage" class="pull-right"></div>
                                                <input type="checkbox" id="isCriticalPT" /> <label>Critical</label><br />
                                                <a href="#" class="greenText messageHistory pull-left" onclick="displayModal(${order.orderId}, 'historyModal', 'viewHistoryMessageWs')">View Message History</a>



                                            </form>

                                        </div></div>
                                    <div class="col-sm-4 top10_padding">
                                        <div class="shipping_btns clearfix">
                                            <input type="hidden" id="errFlagFld" value="0" />


                                            <input type="button"   id="courierLnk" class="btn btn_dtail"  
                                                   value="DELIVERY"  onclick="window.pharmacyNotification.checkOtherStatusBitHandler(5, 'DELIVERY');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag} />

                                            <input type="button"  id="shippedLnk" class="btn btn_dtail" 
                                                   value="SHIPPED" onclick="window.pharmacyNotification.checkOtherStatusBitHandler(6, 'Shipped');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag} />
                                            <input type="button" id="pickFromPharmacyLnk" class="btn btn_dtail"
                                                   onclick="window.pharmacyNotification.checkOtherStatusBitHandler(15, 'Pickup At Pharmacy');" ${statusBtnDisabledFlag} ${deliveryPreferecePharmacyDisabledFlag} value="PICKED FROM PHARMACY" />
                                        </div>
                                    </div>

                                </div>





                                <div class="col-sm-12 col-lg-5 rightvideo">
                                    <div class="video_div">
                                        <c:if test="${not empty order.ptVideo}">
                                            <video id="videoContainer"    controls preload="none" >
                                                <source src="${order.ptVideo}" type="video/mp4">
                                            </video>
                                        </c:if>

                                        <c:if test="${not empty order.transferImage}">                           
                                            <img  src="${order.transferImage}" 
                                                  />
                                        </c:if>


                                        <div class="video_controlls">


                                        </div>
                                    </div>



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
        <!---------------------------------NEW DESIGN-------------------------------------------------->
        <!--/ Modal Status -->

        <!-- Modal Register -->
        <!--/ Modal Register -->

        <!-- jQuery -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientDependent.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInsurancePharmacy.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
        <script type="text/javascript">
                                                  $('#datetimepicker').datetimepicker({timepicker: false, format: 'm/d/Y',
                                                      onChangeDateTime: function (dp, $input) {
                                                          jQuery('#datetimepicker').datetimepicker('hide');
                                                      }});

                                                  function addSlashes(input)
                                                  {
                                                      var v = input.value;
                                                      if (v.match(/^\d{2}$/) !== null)
                                                      {
                                                          input.value = v + '/';
                                                      } else if (v.match(/^\d{2}\/\d{2}$/) !== null)
                                                      {
                                                          input.value = v + '/';
                                                      }
                                                  }
        </script>
        <script>



            $(function () {
            var pull = $('#pull');
                    menu = $('.Menu ul');
                    menuHeight = menu.height();
                    $(pull).on('click', function (e) {
            e.preventDefault();
                    menu.slideToggle();
            });
                    $(needToConfirm = false; window).resize(function () {
            var w = $(needToConfirm = false; window
                    ).width();
                    if (w > 320 && menu.is(':hidden')) {
            menu.removeAttr('style');
            }
            });
            }
            );
            $(document).ready(function (e) {
                $(".buttonss").click(function () {

                    $(".logDrop").slideToggle();
                })
            });
        </script>

        <script type="text/javascript">

            function checkSelectedValue(elem)
            {
                var val = document.getElementById(elem).value;
                if (val == null || val == '')
                {
                    alert("You have selected an invalid value");
                    document.getElementById(elem).selectedIndex = 0;
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
                var w = needToConfirm = false;
                window.innerWidth;
                var h = needToConfirm = false;
                window.innerHeight;

                if (w / 16 >= h / 9) {
                    vid.setAttribute('width', w);
                    vid.setAttribute('height', 'auto');
                } else {
                    vid.setAttribute('width', 'auto');
                    vid.setAttribute('height', h);
                }
            }



            document.getElementById('clear-button').addEventListener('click', function () {
                ["radio-a", "radio-b"].forEach(function (id) {
                    document.getElementById(id).checked = false;
                });
                return false;
            });

            function calculateMFRCost(totalDrugCost, mfrCost, totalFinalCost, handlingFee) {
                var calculatedProfitFld = $("#profitMarginCol").val();
                var profitShareFld = $("#profitShareFld").val();
                if (parseFloat(profitShareFld) > parseFloat(calculatedProfitFld)) {
                    alert("Profit share must be less than calculated profit");
                    return;
                }
                if (mfrCost == "") {
                    mfrCost = 0;
                }
                if (mfrCost !== "" && mfrCost >= 0 && totalDrugCost >= 0 && handlingFee >= 0) {
                    var totalCost = totalDrugCost - parseFloat(mfrCost) - parseFloat(profitShareFld);
                    if (totalCost < 0) {
                        totalCost = 0;
                    }
                    $("#finalPaymentFeeFld").html((totalCost + handlingFee).toFixed(2));
                } else {
                    $("#finalPaymentFeeFld").html(totalFinalCost);
                }
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

                        <div >


                            <div class="refill_options">
                                <p class="clearfix"><span>Do you want to update this order?</span>

                                </p>
                                <div>
                                    <input id="confirmBtn" type="button" class="btn back_btn" value="OK" 
                                           onclick="window.pharmacyNotification.checkProcessingStatusBit('medicationModal2',
                                     '', 'Processing Order', 'RX Order', 'Order Processed');" 
                                           style="width: 80px; vertical-align: middle;"> 

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

        <!----------------------------------------------------------------------------------------------------------------->
        <div id="confirmOtherStatusDiv" class="medicationModal confirmation_modal listModal healthModal formModal modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" >&times;</button>
                        <h4  class="modal-title"><label>Confirm Dialog</label></h4> 

                    </div>
                    <div class="modal-body refill_medi">

                        <div >


                            <div class="refill_options">
                                <p class="clearfix"><span>Do you want to update this order?</span>

                                </p>
                                <div>
                                    <input id="confirmOtherStatusBtn" type="button" class="btn back_btn" value="OK" 
                                           onclick="window.pharmacyNotification.checkOtherStatusBit('medicationModal2',
                                     '', 'Processing Order', 'RX Order', 'Order Processed');" 
                                           style="width: 80px; vertical-align: middle;"> 

                                    <button id="cancelOtherStatusConfirmBoxBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 80px; vertical-align: middle;">Cancel</button>
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

    </body>
    <script language="JavaScript">
        populateArrays();
    </script>
</html>
