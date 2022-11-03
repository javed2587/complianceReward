<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head2.jsp" />
    <jsp:include page="../multiRxModal2.jsp" />
    <jsp:include page="../programRxModel.jsp" />
    <jsp:include page="../deliveryPrefModal.jsp" /> 
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
        <jsp:include page="./inc/header3.jsp" />
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

            #drugImageDiv {
                width: 100%;
                height: auto;
                overflow: hidden;
            }
            #drugImageDiv.rotate90,
            #drugImageDiv.rotate270 {
                width: 600px;
                height: 400px
            }
            #drugImg {
                transform-origin: top left;
                /* IE 10+, Firefox, etc. */
                -webkit-transform-origin: top left;
                /* Chrome */
                -ms-transform-origin: top left;
                /* IE 9 */

            }
            #cardImageDiv.rotate90 #drugImg 
            {
                transform: rotate(90deg) translateY(-100%) !important;
                -webkit-transform: rotate(90deg) translateY(-100%) !important;
                -ms-transform: rotate(90deg) translateY(-100%) !important;;
            }

            #drugImageDiv.rotate180,
            #drugImageDiv.rotate360 {
                /*width: 400px;*/
                height: 600px
            }
            #drugImageDiv {
                transform-origin: top left;
                /* IE 10+, Firefox, etc. */
                -webkit-transform-origin: top left;
                /* Chrome */
                -ms-transform-origin: top left;
                /* IE 9 */
            }
            #drugImageDiv.rotate90 #drugImg {
                transform: rotate(90deg) translateY(-100%) !important;
                -webkit-transform: rotate(90deg) translateY(-100%) !important;
                -ms-transform: rotate(90deg) translateY(-100%) !important;
            }
            #drugImageDiv.rotate180 #drugImg {
                transform: rotate(180deg) translate(-100%, -100%) !important;
                -webkit-transform: rotate(180deg) translate(-100%, -100%) !important;
                -ms-transform: rotate(180deg) translateX(-100%, -100%) !important;
            }
            #drugImageDiv.rotate270 #drugImg {
                transform: rotate(270deg) translateX(-100%) !important;
                -webkit-transform: rotate(270deg) translateX(-100%) !important;
                -ms-transform: rotate(270deg) translateX(-100%) !important;
            }
            #button1{ display:none; }.errorNotice{float:left;} .errorNotice + #button1{display:block;}
            #statusExceptionRefillbtn{ display:none; }.errorNotice + #statusExceptionRefillbtn{display:block;}
        </style>

        <input id="patientId" type="hidden" value="${patientProfile.id}"/>
        <input id="orderDependentId" type="hidden" value="${order.dependentId}"/>
        <input id="orderId" type="hidden" value="${order.orderId}"/>
        <input id="paymentAuthorization" type="hidden" value="${order.paymentAuthorization}"/>
        <input  type="hidden" id="filledate" value="${order.filledDate}"/>
        <input type="hidden" id="paymentFld" value="${order.paymentMode}"/>
        <input type="hidden" id="profitShareFld" value="${not empty order.profitShareCost?order.profitShareCost:'0.0'}" class="form-control isDecimalValue" onblur="calculateMFRCost(${totalDrugPrice}, this.value,${order.finalPayment},${order.handlingFee})" onkeypress="return  float_validation(event)">
        <input type="hidden" id="mfrCostFld" value="${not empty order.mfrCost?order.mfrCost:'0.0'}" class="form-control isDecimalValue" onblur="calculateMFRCost(${totalDrugPrice}, this.value,${order.finalPayment},${order.handlingFee})" onkeypress="return  float_validation(event)">
        <input type="hidden" id="profitSharePoints" value="0" >
        <input type="hidden" id="profitMarginCol" value="${order.profitMargin}"/>
        <input type="hidden" id="hiddenRxNumbers" />
        <input id="currentOrdOutOfPocket" type="hidden" value="${order.paymentExcludingDeliveryStr}"/>
        <input id="currentOrdFinalPayment" type="hidden" value="${order.finalPaymentStr}"/>
        <input id="multiRxScreen" type="hidden" value="0"/>

        <!--
         <input type="hidden" id="totalRedeemPointsId" value="${order.profitSharePoint}"/>
         <input type="hidden" id="redeemPointsCostFld" value="${order.actualProfitShare}"/>-->
        <input type="hidden" id="olversion" value="<fmt:formatDate value='${order.olversion}' pattern='yyyy-MM-dd HH:mm:ss'/>"  />

        <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
        <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.birthDate}"/>
        <c:if test="${order.dependentFlag eq true}" >
            <fmt:formatDate var="dobyear" pattern="yyyy" value="${order.patientDOB}"/>
        </c:if>
        <!--Registration Page-->

        <div class="">
            <div class="col-md-12"> 
                <div class="tableContainer patient_info row">
                    <div class="col-sm-6 patient_name padd_zero">
                        <h3>${fn:toUpperCase(order.patientName)}&nbsp; 
                            <c:choose>
                                <c:when test="${order.dependentFlag eq false}">
                                    <span><fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.birthDate}"/> 
                                        <span>(${patientProfile.gender})  ${nowyear-dobyear} yrs</span>
                                    </span></h3>
                                </c:when>
                                <c:otherwise>
                                <span><fmt:formatDate pattern="MM/dd/yyyy" value="${order.patientDOB}"/> 
                                    <span>(${order.gender})  ${nowyear-dobyear} yrs</span>
                                    <c:choose>
                                        <c:when test="${order.adultFlag eq false}">
                                            <span style="color:#2171b6 !important;">&nbsp;MINOR - ${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(patientProfile.lastName)} </span>

                                        </c:when>
                                        <c:otherwise>
                                            <span style="color:#2171b6 !important;">&nbsp;ADULT - ${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(patientProfile.lastName)} </span>
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                                </h3>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${(sessionBeanPortal.pmap[(79).intValue()].view eq true || sessionBeanPortal.pmap[(79).intValue()].manage eq true)}">
                        <div class="col-sm-1 rx_labe padd_zero">
                            <!--                            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#mytestModal">Test Modal</button>-->
                            <h5>Allergies
                                <!--                               just for testing purpose-->
                                <!-- <a href="#" class="btn btn-info btn-lg" data-toggle="modal" data-target="#mynewModal2">Allergies</a>-->
                                <!--                               just for testing purpose-->
                            </h5>
                        </div>
                        <div id="allergiesDiv" class="col-sm-5 rx_allergies padd_zero scroll" >
                            ${not empty patientProfile.allergies?patientProfile.allergies:''}
                        </div>
                        <!--  <div class="col-sm-1 rx_label padd_zero">
                             <input type="button" class="btn-save" 
                                    onclick="window.pharmacyNotification.updatePatientAllergies()" value="Save" />
 
                         </div> -->
                    </c:if>
                    <jsp:include page="../inc/pharmacyQueueMenu.jsp" />
                    <jsp:include page="drugestimateprice.jsp" />
                    <jsp:include page="multidrugestimateprice.jsp" />
                    <!--Top Nav ends-->

                    <div class="customTableRow">
                        <div id="popUpSuccess" class="success-msg" style="display:none"
                             <i class="fa fa-check" aria-hidden="true"></i><span><strong>Order has been updated successfully.</strong></span>
                        </div>
                        <c:if test="${order.status eq 'Filled'}">
                            <div id="shippingMsg" class="success-msg" style="margin-left:10px;margin-right: 10px;padding: 8px;">
                                <strong>${waitingPatientMsg}</strong>
                            </div>
                        </c:if>
                        <div id="popUpException" class="error-msg" style="display:none">
                            <span id="popupErrDiv" >  <i class="fa fa-exclamation-circle" aria-hidden="true"></i><strong>Error while processing order ?still 7 days remaining for next Refill.?</strong></span>
                            <span id="overrideSpan" style="float:right;display:none" 
                                  onclick="window.pharmacyNotification.refillOverride()"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Refill Override</span>
                            <span id="overrideRemainingSpan" style="float:right;display:none" 
                                  onclick="window.pharmacyNotification.refillRemainingOverride()"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Refill Override</span>
                        </div>
                        <%if (request.getParameter("success") != null && request.getParameter("success").equals("1")) {
                                String message = "Order has been updated successfully.";
                                if (request.getParameter("verified") != null
                                        && request.getParameter("verified").equals("1")) {
                                    message = "Image has been verified successfully.";
                                }
                        %>
                        <div id="popUpException1" class="success-msg"
                             <i class="fa fa-check" aria-hidden="true"></i><span><strong><%=message%></strong></span>
                        </div>
                        <%}%> 
                        <%if (request.getParameter("delete") != null && request.getParameter("delete").equals("1")) {%>
                        <div id="popUpException1">
                            <i class="fa fa-check" aria-hidden="true"></i><span><strong>Order has been deleted successfully.</strong></span>
                        </div>
                        <%}%> 
                        <c:choose >
                            <c:when test="${errMsg eq '1'}">
                                <div id="popUpException1" class="error-msg">
                                    <i class="fa fa-exclamation-circle" aria-hidden="true"></i><strong>Error while processing order.</strong><span style="float:right;" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Refill Override</span>
                                </div>
                            </c:when>
                            <c:otherwise>
<!--                                <div id="popUpException" style="min-height:30px; color:red; font-weight:bold">${errMsg}</div>-->
                                <input type="button" id="button1" style="background: #c9c9c9; color: #000000;" class="btn btn_dtail" value="Refill Override" onclick="window.pharmacyNotification.refillRemainingOverride()"></input>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" id="refillOverriden" value="0" >
                        <!--                        <div id="statusException"></div>-->
                        <input type="button" id="statusExceptionRefillbtn" style="background: #c9c9c9; color: #000000; " class="btn btn_dtail" value="Refill Override" onclick="window.pharmacyNotification.refillOverride()"></input>
                        <c:set var="mandatoryFldsSign" value="${order.drugIncluded eq true && order.status ne 'Pending'?'<span style=\"color:red\"><b>*</b></span>':''}" />
                        <c:set var="deliveryPrefereceDeliveryDisabledFlag" value="${order.deliveryService eq 'Pick Up at Pharmacy' ?'disabled':''}" />
                        <c:set var="deliveryPreferecePharmacyDisabledFlag" value="${order.deliveryService ne 'Pick Up at Pharmacy' ?'disabled':''}" />

                        <%-- <c:set var="statusBtnDisabledFlag" value="${order.status eq 'DELIVERY' || 
                                                                     order.status eq 'Pickup At Pharmacy'  || order.status eq 'Shipped' ||    order.status eq 'Cancelled'|| (order.handDelivery eq 1 && order.handDeliveryAccepted eq 0) ?'disabled':''}"/>--%>
                        <c:set var="statusBtnDisabledFlag" value="${order.status eq 'DELIVERY' || 
                                                                    order.status eq 'Pickup At Pharmacy'  ||
                                                                    order.status eq 'Shipped' ||    
                                                                    order.status eq 'Cancelled' ?'disabled':''}"/>
                        <c:set var="isOrderTypeRefillOrTransfer" value="${order.orderType eq 'Transfer' || order.orderType eq 'Refill' ? '':'disabled' }" />
                        <c:set var="fieldsDisabledFlag2" value="${order.requiresDeletion eq true||(order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)}" />
                        <c:set var="fieldsDisabledFlag" value="${order.requiresDeletion eq true || order.status eq 'DELIVERY' || order.status eq 'Ready to Fill' || order.status eq 'Filled'|| 
                                                                 order.status eq 'Waiting payment authorization'|| order.status eq 'Payment Authorized' ||    order.status eq 'Shipped'
                                                                 || order.status eq 'Pickup At Pharmacy' || order.status eq 'Cancelled' || order.status eq 'Ready to Deliver Rx'
                                                                 || (order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)  ?'disabled':''}"/>
                        <c:set var="fieldsDisabledFlag3" value="${order.requiresDeletion eq true || order.status eq 'Ready to Fill' ||  
                                                                  order.status eq 'Waiting payment authorization'|| order.status eq 'Payment Authorized' 
                                                                  || order.status eq 'Pickup At Pharmacy' || order.status eq 'Cancelled'
                                                                  || (order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)  ?'disabled':''}"/>
                        <c:choose>
                            <c:when test="${adminRole eq false}">
                                <c:set var="responseBtnDisabledFlag" value="${order.requiresDeletion eq true || order.status eq 'DELIVERY' || order.status eq 'Ready to Fill' || order.status eq 'Filled'|| 
                                                                              order.status eq 'Waiting payment authorization'|| order.status eq 'Payment Authorized' ||    order.status eq 'Shipped'
                                                                              || order.status eq 'Pickup At Pharmacy' || order.status eq 'Cancelled' || order.status eq 'Ready to Deliver Rx'
                                                                              || (order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)  ?'disabled':''}"/>
                                <c:set var="orderStatusPendingDisabledButtons" value="${order.status eq 'Pending' || order.status eq 'Unverified Image' ?'disabled':''}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="responseBtnDisabledFlag" value='' />
                                <c:set var="orderStatusPendingDisabledButtons" value='' />
                            </c:otherwise>
                        </c:choose>
                        <c:set var="byPassImgVerification" value="${sessionBeanPortal.pmap[(96).intValue()].manage eq false ? 'disabled' : ''}" />
                        <c:set var="imgVerifiedFlag" value="${sessionBeanPortal.pmap[(96).intValue()].manage eq false && order.drugIncluded eq false && order.status eq 'Unverified Image'?'true':'false'}" />
                        <c:set var="shippedFlag" value="${order.status ne 'Filled' ?'disabled':''}"/>

                        <c:set var="shippedBtnFlag" value="${order.status ne 'Filled' ?'disabled':''}"/>
                        <c:set var="filledBtnFlag" value="${order.status eq 'Fill Request Accepted'  ?'':'disabled'}"/>
                        <c:set var="readyFilledBtnFlag" value="${order.status eq 'Pending' || order.status eq 'Ready to Fill' || order.status eq 'Ready to Fill' || order.status eq 'Pending Pharmacy Reply' || order.status eq 'Filled' || order.status eq 'DELIVERY' ||order.status eq 'Shipped'  ||    order.status eq 'Cancelled' ||order.status eq 'Waiting payment authorization' ||order.status eq 'Ready to Fill' ||order.status eq 'Fill Request Accepted'?'disabled':''}"/>
                        <c:set var="processingBtnFlag" value="${order.status eq 'Filled' || order.status eq 'DELIVERY' || order.status eq 'Shipped' || order.status eq 'Cancelled' || order.status eq 'Processing' ||order.status eq 'Waiting payment authorization' ||order.status eq 'Ready to Fill' ||order.status eq 'Fill Request Accepted' ||order.status eq 'Payment Authorized'?'disabled':''}"/>
                        <c:set var="pickUpFromPharmacyFlag" value="${order.status eq 'Pickup At Pharmacy' ?'disabled':''}"/>
                        <c:set var="waitingPtResponseFlag" value="${order.status eq 'Waiting Pt Response'?'disabled':''}" />

                        <c:set var="ManagePermissionMedicationSpecificMessages" value="${sessionBeanPortal.pmap[(83).intValue()].manage eq false ? 'disabled' : ''}" />
                        <c:set var="ViewPermissionMedicationSpecificMessages" value="${sessionBeanPortal.pmap[(83).intValue()].view eq true ? 'disabled' : ''}" />

                        <c:set var="ManagePermissionUpdateClerk" value="${sessionBeanPortal.pmap[(87).intValue()].manage eq false ? false : true}" />
                        <c:set var="ViewPermissionUpdateClerk" value="${sessionBeanPortal.pmap[(87).intValue()].view eq true ? 'disabled' : ''}" />

                        <c:set var="ManagePermissionUpdateAllRequestFields" value="${sessionBeanPortal.pmap[(88).intValue()].manage eq false ? 'disabled' : ''}" />
                        <c:set var="ViewPermissionUpdateAllRequestFields" value="${sessionBeanPortal.pmap[(88).intValue()].view eq true ? 'disabled' : ''}" />

                        <c:set var="ManagePermissionUpdateComplainceRewards" value="${sessionBeanPortal.pmap[(89).intValue()].manage eq false ? 'disabled' : ''}" />
                        <c:set var="ViewPermissionUpdateComplainceRewards" value="${sessionBeanPortal.pmap[(89).intValue()].view eq true ? 'disabled' : ''}" />

                        <c:set var="ManagePermissionSendMessage" value="${sessionBeanPortal.pmap[(90).intValue()].manage eq false ? 'disabled' : ''}" />
                        <c:set var="ViewPermissionSendMessage" value="${sessionBeanPortal.pmap[(90).intValue()].view eq true ? 'disabled' : ''}" />

                        <c:set var="ManagePermissionAccesssDeliveryButton" value="${sessionBeanPortal.pmap[(91).intValue()].manage eq false ? 'disabled' : ''}" />
                        <c:set var="ViewPermissionAccesssDeliveryButton" value="${sessionBeanPortal.pmap[(91).intValue()].view eq true ? 'disabled' : ''}" />

                        <c:set var="ManagePermissionAccesssShippedButton" value="${sessionBeanPortal.pmap[(92).intValue()].manage eq false ? 'disabled' : ''}" />
                        <c:set var="ViewPermissionAccesssShippedButton" value="${sessionBeanPortal.pmap[(92).intValue()].view eq true ? 'disabled' : ''}" />

                        <c:set var="ManagePermissionAccesssCancelOrderButton" value="${sessionBeanPortal.pmap[(95).intValue()].manage eq false ? false : true}" />
                        <c:set var="ViewPermissionAccesssCancelOrderButton" value="${sessionBeanPortal.pmap[(95).intValue()].view eq true ? 'disabled' : ''}" />
                        <c:set var="pharmacyClass" value="${order.labelScan eq true ? 'table_left':'' }" />
                        <c:set var="prescriberClass" value="${order.rxScan eq true ? 'table_right':'' }" />

                        <div class="col-md-12"> 
                            <div class="transfer_wrap clearfix">
                                <div class="col-sm-12 col-lg-7 set_table">
                                    <div class="rx_no bold_new">
                                        <h3>Rx No: <span id="sysRxNoLabel" class="bold_new">${order.assignedOrderId}</span></h3>
                                    </div>
                                    <c:choose>
                                        <c:when test="${order.status=='Unverified Image'}">
                                            <div class="order_details" style="background:#ffc000;">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="order_details" >
                                                </c:otherwise>
                                            </c:choose>
                                            <h2> 
                                                <c:choose>
                                                    <c:when test="${order.status=='Cancelled'}">
                                                        Status of order:<span id="orderStatusSpan"style="color: red"> ${order.status}</span>
                                                    </c:when> 
                                                    <c:when test="${order.questionAuthorization eq true}">
                                                        Status of order:<span id="orderStatusSpan"> <a href="#" onclick="window.pharmacyNotification.showAnswerRxQuestion(${order.orderId}, '${order.question}',${order.patientId},${order.questionId})"> ${order.statusLabel} </span>
                                                    </c:when> 
                                                    <c:otherwise>
                                                        Status of order:<span id="orderStatusSpan"> ${order.statusLabel}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                                <input type="hidden" id="byPassImgVerification" 
                                                       value="${sessionBeanPortal.pmap[(96).intValue()].manage eq false ? 0 : 1}" /> 
                                                <input type="hidden" id="statusHiddenFld" value="${order.status}" />
                                                <!--Order Detail-- ${order.orderId}--></h2>
    <!--                                     <!--<span id="orderStatusSpan">${order.status}</span>-->
                                            <small>DATE OF POST: <fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd'/> @ <strong><fmt:formatDate value='${order.receivedDate}' pattern='hh:mm:ss'/></strong></small>
                                        </div>

                                        <div id="full_video_container" class="full_video_container" style="display: none;"> </div>
                                        <fmt:formatNumber var="totalDrugPrice" value="${order.totalDrugPrice}" pattern="0.00"/>
                                        <input id="estimatedDrugPrice" type="hidden" value="${totalDrugPrice}"/>
                                        <div class="rx_detail_table col-sm-12 new_tables">
                                            <table class="dt_tables">
                                                <tbody>
                                                    <tr>
                                                        <th>Control&nbsp;#</th>
                                                        <th>REQ&nbsp;RECVD</th>
                                                        <th>Current MBR Request</th>
                                                        <th>Rx EXP Date</th>
                                                        <th>Next Refill</th>
                                                    </tr>
                                                <input type="hidden" id="orderTypeFld" value="${order.orderType}" >
                                                <fmt:formatDate var="receivedDate1" value='${order.receivedDate}' pattern='yyyy-MM-dd'/>
                                                <input type="hidden" id="recievedDateFld" value="${receivedDate1}" >
                                                <tr>
                                                    <td><input class="form-control bold_new" placeholder="" value="${order.orderId}" name="" readonly/></td>
                                                    <td>
                                                        <fmt:formatDate var="receivedDate" value='${order.receivedDate}' pattern='yyyy-MM-dd @ HH:mm'/>
                                                        <input class="form-control" placeholder="" name="" value="${receivedDate}" readonly/>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${order.labelScan eq true}">
                                                                <input id="requestTypeFld" class="form-control current_estimate" placeholder="" value="${order.requestType}" style="background:#3a89d7" name="" readonly/>
                                                            </c:when>
                                                            <c:when test="${order.rxScan eq true}">
                                                                <input id="requestTypeFld" class="form-control current_estimate" placeholder="" value="${order.requestType}" style="background: #8aca14;" name="" readonly/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input id="requestTypeFld" class="form-control current_estimate" placeholder="" value="${order.requestType}" name="" readonly style="background:#df1111"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td><input class="form-control bold_new" placeholder=""  placeholder="" value="${order.rxExpiredDateStr}" name="" disabled="true"/></td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${order.preauthorized eq true}" >
                                                                <input id="datetimepicker3" class="form-control calendar_field" placeholder="mm/dd/yyyy"  placeholder=""
                                                                       onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"
                                                                       value="${order.nextRefillDateStr}" name="" ${ManagePermissionUpdateAllRequestFields} />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="form-control bold_new" placeholder=""  placeholder="" value="${order.nextRefillDateStr}" name="" disabled="true" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>

                    <!--   <td class="label_td"><input class="form-control" placeholder="" value="${order.requestType}" name=""  ${ManagePermissionUpdateAllRequestFields} disabled />
                          
                          

                      </td> -->

                                                    <!--                                                <td>
                                                                                                        <div class="checkbox_style">
                                                                                                            <input type="radio" id="INS" name="selector" onclick="window.pharmacyNotification.selfPayEvent()" value="Commercial" ${fieldsDisabledFlag} ${order.insuranceCheckCommercial} ${ManagePermissionUpdateAllRequestFields}>
                                                                                                            <label for="INS">INS</label>
                                                                                                            <div class="check"></div>
                                                                                                        </div>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <div class="checkbox_style public_check">
                                                                                                            <input type="radio" id="radio-a" name="selector" onclick="window.pharmacyNotification.selfPayEvent()" value="Public" ${order.insuranceCheck} ${ManagePermissionUpdateAllRequestFields} ${fieldsDisabledFlag}>
                                                                                                            <label for="radio-a">INS</label>
                                                                                                            <div class="check"></div>
                                                                                                        </div>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <div class="checkbox_style">
                                                                                                            <input type="radio" id="radio-b" name="selector" value="Cash" onclick="window.pharmacyNotification.selfPayEvent()" ${order.selfPayCheck} ${ManagePermissionUpdateAllRequestFields} ${fieldsDisabledFlag}>
                                                                                                            <label for="radio-b">Pay</label>
                                                                                                            <div class="check"></div>
                                                                                                        </div>
                                                                                                    </td>
                                                                                                    <td> <input class="form-control" placeholder="" value="${order.estimatedPrice}" disabled="true" name="" ${ManagePermissionUpdateAllRequestFields} ${fieldsDisabledFlag}  onkeypress="return float_validation(event, this.value)"/></td>-->
                                                </tr>
                                                <!--<tr>
        
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
                                                <!-- </tr>-->
                                                </tbody>
                                            </table>
                                            <input id="loadingTime" type="hidden" value="${loadingTime}" />
                                            <table class="dt_tables">
                                                <tbody>
                                                    <tr> 
                                                        <th class="size_length" >Original&nbsp;Date${mandatoryFldsSign}</th>
                                                        <th>RX&nbsp;Name<span style="color:red"><b>*</b></span>
                                                            <span  id="drugNotFoundSpan" style="color:red;display:none"><b> Drug not found.</b></span>
                                                        </th>
                                                        <th class="strength_cntrol">Strength<span style="color:red"><b>*</b></span></th>
                                                        <th>Dosage&nbsp;Type<span style="color:red"><b>*</b></span></th>
                                                        <th class="size_cntrol">QTY${mandatoryFldsSign}</th>
                                                        <th class="size_cntrol">Days<br />Supply${mandatoryFldsSign}</th>
                                                        <th class="size_cntrol">Refills<br />Allowed</th>
                                                        <th class="size_cntrol">Refills<br />Remain</th>
                                                    </tr>
                                                    <tr> 
                                                        <!--   <td><input type="text" id="patientNameFld" value="Riaz  Ahmad" class="form-control" /></td>-->
                                                        <td class="date_width">
                                                            <input id="datetimepicker" class="form-control calendar_field bold_new" placeholder="mm/dd/yyyy" ${fieldsDisabledFlag}
                                                                   onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"
                                                                   value="<fmt:formatDate value='${order.rxDate}' pattern='MM/dd/yyyy'/>"  ${ManagePermissionUpdateAllRequestFields} />
                                                        </td>
                                                        <td>
                                                            <input id="drugNameIdDetail" type="hidden" value="${order.drugNameWithoutStrength}" ${ManagePermissionUpdateAllRequestFields}/>
                                                            <input type="text" id="referenceBrandDetail" name="" value="${order.drugNameWithoutStrength}" class="form-control strengh_fie bold_new" 
                                                                   onblur="needToConfirm = false;
                                                                           window.drugestimateprice.populateDrugDosageAndStrengthDetail('referenceBrandDetail',
                                                                                   'drugNameIdDetail', 'drugStrengthDetail', 'drugTypeIdDetail');
                                                                           window.pharmacyNotification.validateImageVerifiedFields();" 
                                                                   oninput="window.transferRequest.lookUpObject('referenceBrandDetail','/ConsumerPortal/populateDrugDetailByBrandOrGenericName')" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>

                                                        </td>
                                                        <td>
                                                            <select id="drugStrengthDetail" class="form-control strengh_fi bold_new" onblur="window.transferRequest.populateQtyByStrengthAndDrugNameDetail('drugQtyFld', 'referenceBrandDetail', 'drugStrengthDetail', 'drugTypeIdDetail');" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>
                                                                <option value="${order.strength}">${order.strength}<option>
                                                            </select>

                                                        </td>
                                                        <td>
                                                            <select id="drugTypeIdDetail" onchange="checkSelectedValue('drugTypeIdDetail')"
                                                                    onblur="needToConfirm = false;
                                                                            window.transferRequest.lookUpDrugStrengthByBrandNameAndTypeDetail('referenceBrandDetail',
                                                                                    'drugStrengthDetail', 'drugTypeIdDetail');" 
                                                                    class="form-control strengh_fi bold_new" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>
                                                                <option value="${order.drugType}">${order.drugType}</option>

                                                            </select>
                                                        </td>
                                                        <td class="size_define">
                                                            <input type="text" id="drugQtyFld" value="${order.quantity}" class="form-control qty text_right bold_new" onchange="window.pharmacyNotification.validateImageVerifiedFields();"
                                                                   onblur="needToConfirm = false;
                                                                           window.transferRequest.lookUpDrugCostDetail('drugNameIdDetail',
                                                                                   'referenceBrandDetail',
                                                                                   'drugStrengthDetail',
                                                                                   'drugTypeIdDetail');
                                                                           window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');" onkeypress="return IsDigit(event)"
                                                                   ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="5">
                                                        </td>
                                                        <td class="size_define">
                                                            <input type="text" id="daysSupplyFld" value="${not empty order.daysSupply?order.daysSupply:''}" class="form-control center_fiel center_field text_right bold_new" 
                                                                   onkeypress="return IsDigit(event)" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="4">

                                                        </td>
                                                        <td class="size_define">
                                                            <input type="text" id="refillAllowedFld" value="${order.refillsAllowed}" class="form-control center_fiel center_field text_right bold_new" onkeypress="return IsDigit(event)" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="3">
                                                        </td>
                                                        <td class="size_define">
                                                            <input type="text" id="refillUsedFld" value="${order.refillsRemaining}" class="form-control center_fiel center_field text_right bold_new" onkeypress="return IsDigit(event)" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="3">
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table></div>





                                        <div class="col-sm-12  pharmacy_tables new_tables">
                                            <div class="table_col_left ${pharmacyClass} col-sm-6">
                                                <h6><span>Original Pharmacy</span></h6>
                                                <table class="dt_tables">
                                                    <tbody>
                                                        <tr> 
                                                            <th class="size_fix">Rx&nbsp;#</th>
                                                            <th>Name${mandatoryFldsSign}</th>
                                                            <th class="fixed_size">Phone${mandatoryFldsSign}</th>
                                                            <th class="ex_size">Ext.</th>
                                                        </tr> 
                                                        <tr> 
                                                            <td>
                                                                <input type="text" id="pharmacyRxNoFld" value="${order.oldRxNo}" class="form-control set_fld center_fiel bold_new" ${fieldsDisabledFlag} maxlength="9" onkeypress="return IsAlphaNumericWithHyphen(event)" ${ManagePermissionUpdateAllRequestFields}>
                                                            </td>
                                                            <td><input type="text" id="pharmacyNameFld"   value="${not empty order.pharmacyName?order.pharmacyName:''}" class="form-control center_fiel bold_new" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="50" onkeyup="window.pharmacyNotification.setUpperCaseChar(this.id, 0);"/></td>
                                                            <td>
                                                                <input type="text" id="pharmacyPhoneFld" value="${not empty order.pharmacyPhone?order.pharmacyPhone:''}" class="form-control fld_cntrl center_fiel bold_new" 
                                                                       onkeypress="return IsDigitWithHyphen(event)" maxlength="20" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}></td>
                                                            <td> <input type="text" id="pharmacyPhoneExt" class="form-control center_fiel ex_size" maxlength="5" onkeypress="return IsDigit(event)" 
                                                                        ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}
                                                                        value="${not empty order.pharmacyExtension?order.pharmacyExtension:''}" >
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <div class="table_col_right ${prescriberClass} col-sm-6">
                                                <h6><span>Prescriber</span></h6>
                                                <table class="dt_tables">
                                                    <tbody>
                                                        <tr> 
                                                            <th>Last&nbsp;Name</th>
                                                            <th class="size_fixed">First&nbsp;Name</th>
                                                            <th class="fixed_size">Phone</th>
                                                            <th class="ex_size">Ext.</th>
                                                        </tr>
                                                        <tr> 
                                                            <td>
                                                                <input type="text" id="prescriberLastNameFld" value="${not empty order.prescriberLastName?order.prescriberLastName:''}" class="form-control  center_fiel bold_new" 
                                                                       onkeypress="return onlyAlphabets(event)" ${fieldsDisabledFlag}  ${ManagePermissionUpdateAllRequestFields} maxlength="50" onkeyup="window.pharmacyNotification.setUpperCaseChar(this.id, 0);"/>
                                                            </td>
                                                            <td>
                                                                <input type="text" id="prescriberNameFld" value="${not empty order.prescriberName?order.prescriberName:''}" class="form-control fld_cntrl center_fiel bold_new" ${fieldsDisabledFlag} size="10" maxlength="20" onkeypress="return onlyAlphabets(event)" onkeyup="window.pharmacyNotification.setUpperCaseChar(this.id, 0);"   ${ManagePermissionUpdateAllRequestFields}>
                                                            </td>

                                                            <td>
                                                                <input type="text" id="prescriberPhoneFld" value="${not empty order.prescriberPhone?order.prescriberPhone:''}" class="form-control center_fiel bold_new" 
                                                                       onkeypress="return IsDigitWithHyphen(event)" maxlength="20" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>
                                                            </td>
                                                            <td> <input type="text" id="prescriberPhoneExt" class="form-control center_fiel ex_size" maxlength="5" onkeypress="return IsDigit(event)"
                                                                        ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} value="${order.prescriberExtension}" >
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col-sm-2 table_price">
                                                <h3>Insurance</h3>
                                                <div class="table_col_public ">

                                                    <table class="dt_tables new_tables">
                                                        <tbody>
                                                            <tr>
                                                                <th>Private</th>
                                                                <th>Public</th> 
                                                                <th>Self<br />
                                                                    Pay</th>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <div class="checkbox_style">
                                                                        <input type="radio" id="INS" name="selector" onclick="window.pharmacyNotification.selfPayEvent()" value="Commercial" ${fieldsDisabledFlag} ${order.insuranceCheckCommercial} ${ManagePermissionUpdateAllRequestFields}>
                                                                        <label for="INS">INS</label>
                                                                        <div class="check"></div>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="checkbox_style public_check">
                                                                        <input type="radio" id="radio-a" name="selector" onclick="window.pharmacyNotification.selfPayEvent()" value="Public" ${order.insuranceCheck} ${ManagePermissionUpdateAllRequestFields} ${fieldsDisabledFlag}>
                                                                        <label for="radio-a">INS</label>
                                                                        <div class="check"></div>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="checkbox_style">
                                                                        <input type="radio" id="radio-b" name="selector" value="Cash" ${order.selfPayCheck} ${ManagePermissionUpdateAllRequestFields} ${fieldsDisabledFlag} onclick="window.pharmacyNotification.selfPayEvent()">
                                                                        <label for="radio-b">Pay</label>
                                                                        <div class="check"></div>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                        </tbody>

                                                    </table>
                                                </div>
                                            </div>
                                            <div class="table_slef_pay col-sm-1">
                                                <table class="dt_tables new_table">
                                                    <tbody>
                                                        <tr>
                                                            <th class="textCentImp">Quoted<br />
                                                                Price</th> 
                                                        </tr>
                                                        <tr>
                                                            <td>

                                                                <c:choose>
                                                                    <c:when test="${fn:containsIgnoreCase(order.insuranceCheckCommercial, 'Checked') || fn:containsIgnoreCase(order.insuranceCheck, 'Checked')}">
                                                                        <input type="text" id="estPriceFld" value="$${order.estimatedPrice}" class="form-control set_inputfield " onkeypress="" ${fieldsDisabledFlag} >
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="text" id="estPriceFld" value="$${order.estimatedPrice}" class="form-control set_inputfield " disabled="true" onkeypress="">
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </td>
                                                        </tr>

                                                    </tbody>
                                                </table>
                                            </div>

                                            <div class="table_col_full table_col_custom col-sm-8">
                                                <h3>Compliance Rewards&trade;</h3>

                                                <table class="dt_tables table_width">
                                                    <tbody>
                                                        <tr> 
                                                            <th class="">Rx&nbsp;Cost $</th>
                                                            <th>MBR&nbsp;Outof <br />PCKT $</th>
                                                            <th>3rd&nbsp;PartyPay</th>

                                                            <th class="lightGreen">Reward<br />
                                                                Points</th>
                                                            <th class="lightGreen">Reward<br />Saved</th>

                                                            <th>Collect </th>

                                                        </tr>

                                                        <tr> 
                                                            <td>
                                                                <input type="text" id="acqCostFld" value="$${order.rxAcqCost}" class="form-control set_inputfield isDecimalValue text_cent bold_new" onkeypress="return  float_validation(event, this.value);"
                                                                       onblur="window.pharmacyNotification.addCommas('acqCostFld', 1);"     ${fieldsDisabledFlag} ${ManagePermissionUpdateComplainceRewards}>
                                                            </td>
                                                            <td>
                                                                <input type="text" id="ptCopayFld" value="$${order.originalPtCopay}" class="form-control set_inputfield isDecimalValue text_cent bold_new" 
                                                                       onblur="needToConfirm = false;
                                                                               window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');
                                                                               window.transferRequest.calculatePayment();
                                                                               window.pharmacyNotification.addCommas('ptCopayFld', 1);" 
                                                                       onkeypress="return  float_validation(event, this.value)" ${fieldsDisabledFlag} ${ManagePermissionUpdateComplainceRewards}>
                                                            </td>
                                                            <td>
                                                                <input type="text" id="reimbFld" value="$${order.rxReimbCost}" class="form-control set_inputfield isDecimalValue text_cent bold_new" 
                                                                       onblur="needToConfirm = false;
                                                                               window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');
                                                                               window.transferRequest.calculatePayment();
                                                                               window.pharmacyNotification.addCommas('reimbFld', 1);" 
                                                                       onkeypress="return  float_validation(event, this.value)" ${fieldsDisabledFlag}  ${ManagePermissionUpdateComplainceRewards}>
                                                            </td>
                                                    <input type="hidden" id="sellingPriceFld" value="$${totalDrugPrice}" />

                                                    <td><input type="text" id="totalRedeemPointsId"  value="${order.profitSharePoint}" ${fieldsDisabledFlag}
                                                               class="form-control set_reward isDecimalValue text_cent" onkeypress="return  float_validation(event, this.value)" 
                                                               onblur="window.pharmacyNotification.addCommas('totalRedeemPointsId', 0);"> 
                                                    </td>
                                                    <td>
                                                        <input type="text" id="redeemPointsCostFld"  class="form-control set_field isDecimalValue text_cent" ${fieldsDisabledFlag}
                                                               value="$${order.actualProfitShare}" 
                                                               onkeypress="return  float_validation(event)" onblur="window.pharmacyNotification.addCommas('redeemPointsCostFld', 1);">
                                                    </td>

                                                    <td><input type="text" id="finalPaymentFeeFld" value="$${order.paymentExcludingDelivery}" class="form-control set_collect isDecimalValue text_cent bold_new disabledEvents" onkeypress="return  float_validation(event, this.value)" onblur="window.pharmacyNotification.addCommas('finalPaymentFeeFld', 1);"  ${ManagePermissionUpdateComplainceRewards} readonly="true" onfocus="this.blur();"></td>


                                                    </tr>
                                                    </tbody>
                                                </table>

                                                <input type="hidden" id="ptOverridePriceFld" value="${order.ptOverridePrice eq true ? 1:0}"/>
                                                <input type="hidden" id="rxScanFld" value="${order.rxScan eq true ? 1:0}"/>
                                                <input type="hidden" id="labelScanFld" value="${order.labelScan eq true ? 1:0}"/>
                                            </div>

                                            <div class="col-sm-1 pt_btn">
                                                <input type="button" id="ptOverRideBtn" value="" class="btn btn_pt cancelbt" onclick="window.pharmacyNotification.ptOverrideEvent()" ${fieldsDisabledFlag3}>
                                            </div>


                                            <input type="hidden" id="paymentExcludingDeliveryHidden" value="${order.paymentExcludingDelivery}" />                    
                                            <div class="col-sm-12 table_col_full">
                                                <h3>Pharmacy responses</h3>
                                                <input id="statusBit" type="hidden" value="0"/>
                                                <div class="col-md-3 col-sm-3 medi_col left_zero">
                                                    <c:choose>
                                                        <c:when test="${order.orderType eq 'Refill' || adminRole eq true}">
                                                            <input type="button" class="btn btn_one" value="C-II-Refills" 
                                                                   onclick="window.pharmacyNotification.openDrugCanNitFillnDiv(
                                                                                   'medicationModal', 'CII', 'Drug can not fill', 'Pharmacy Notification', 'Cannot Fill', null, 1);" ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} style="width:171.85px; height:27px"/>   
                                                            <input type="button" class="btn btn_one" value="Rx Carried" 
                                                                   onclick="
                                                                           window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                                   '', 'Rx No Carried for Now', 'Pharmacy Notification', 'Rx No Carried', null, 1);
                                                                   " ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} style="width:171.85px; height:27px"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="button" class="btn btn_one" value="C-II-Refills" 
                                                                   disabled="true" style="width:171.85px; height:27px"/>   
                                                            <input type="button" class="btn btn_one" value="Rx Carried" 
                                                                   disabled="true" style="width:171.85px; height:27px"/>
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <input type="button" class="btn btn_one" value="Phmcy X-FER" 
                                                           onclick="
                                                                   window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                           '', 'Transfer Failure', 'Pharmacy Notification', 'Transfer Failure', null, 1);
                                                           " ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px"/>   
                                                    <input type="button" class="btn btn_one" value="HCP AUTH" 
                                                           onclick="window.pharmacyNotification.setLowerCost(1);
                                                                   window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                           '', 'MD Declined ? Can not fill', 'Pharmacy Notification', 'MD Declined', null, null, null);
                                                           " ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px"/>
                                                    <input type="button" class="btn btn_one" value="Rx Supply"
                                                           onclick="window.pharmacyNotification.setLowerCost(1);
                                                                   window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                           '', 'Rx not in Supply', 'Pharmacy Notification', 'Rx not in Supply', null, null, null);
                                                           " ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px"/>

                                                    <c:choose>
                                                        <c:when test="${(not empty order.transferImage || not empty order.ptVideo)&& (order.status eq 'Unverified Image'|| order.status eq 'Pending' || order.statusId eq 19)}">


                                                            <input type="button" class="btn btn_one" value="UPLOAD PROBLEM" 
                                                                   onclick="
                                                                           window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                                   '', 'UNRECOGNIZABLE IMAGE', 'Pharmacy Notification', 'UNRECOGNIZABLE IMAGE', null, null, 1, null);
                                                                   " ${ManagePermissionMedicationSpecificMessages} style="width:171.85px; height:27px" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="button" class="btn btn_one" value="UPLOAD PROBLEM" disabled="true"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col-md-3 col-sm-3 medi_col">
                                                    <button class="btn btn_two"
                                                            onclick="window.pharmacyNotification.setLowerCost(1);
                                                                    window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                            '', '$ Refill too Soon- $ Option', 'Pharmacy Notification', 'Cash Refill too Soon');
                                                            " ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px">$ Refill too Soon<span style="color:red">wait</span>
                                                    </button>
                                                    <c:choose>
                                                        <c:when test="${order.orderType eq 'Transfer' || order.orderType eq 'Refill' || adminRole eq true}">
                                                            <button class="btn btn_two" onclick="window.pharmacyNotification.setLowerCost(1);
                                                                    window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                            '', 'INS-Refill too Soon- $ Option', 'Pharmacy Notification', 'Refill too Soon');
                                                                    " ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px">
                                                                Ins: Refill Too Soon<span>Pt $ Option</span>
                                                            </button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="btn btn_two" disabled
                                                                    style="width:171.85px; height:27px">
                                                                Ins: Refill Too Soon<span>Pt $ Option</span>
                                                            </button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <button class="btn btn_two" onclick="window.pharmacyNotification.setLowerCost(1);
                                                            window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                    '', 'Ins Declined-  $ Option', 'Pharmacy Notification', 'Ins Declined', null, null, 1);
                                                            " ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px">INS Reject<span>Pt $ Option</span></button>
                                                    <!--
                                                    <button class="btn btn_two" onclick="
                                                            window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                    '', 'Ins not Accepted-- $', 'Pharmacy Notification', 'Ins Not Accepted', null, null, 1);
                                                            " ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px"><i class="btn_block"></i>INS Accepted<span>Pt $ Option</span>
                                                    </button>-->
                                                    <button class="btn btn_two"
                                                            onclick="window.pharmacyNotification.setLowerCost(1);
                                                                    window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                            '', 'HCP CURRENTLY DELAYED', 'Pharmacy Notification', 'HCP CURRENTLY DELAYED', null, null, null);
                                                            " ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="width:171.85px; height:27px">HCP 3 Days<span>Call Other HCP?</span>
                                                    </button>



                                                </div>
                                                <div class="col-md-3 col-sm-3 medi_col right_zero">
                                                    <input id="verifiedImg" type="hidden" value="${order.imageVerificationRequired eq true ?1:0}"/>
                                                    <c:choose>

                                                        <c:when test="${order.status eq 'Unverified Image' || order.statusId eq 19 }">

                                                            <button class="btn verified_btn" onclick="window.pharmacyNotification.checkOtherStatusBitHandler(17, 'Pending Review');" 
                                                                    id="interpretedImageLnk"  style="width:171.85px; height:27px" >IMG Verified </button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="btn verified_btn" disabled style="width:171.85px; height:27px">IMG Verified</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <button class="btn btn_two" ${ManagePermissionMedicationSpecificMessages} onclick="
                                                            window.pharmacyNotification.setLowerCost(1);
                                                            window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                    '', 'No Refills; Calling HCP', 'Pharmacy Notification', 'No Refill Remaining', null, null, null);
                                                            " ${fieldsDisabledFlag} ${isOrderTypeRefillOrTransfer} style="width:171.85px; height:27px">0 Refills - Called HCP
                                                    </button>


                                                    <c:choose>
                                                        <c:when test="${(order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)}">
                                                            <button class="btn medical_icon" ${ManagePermissionMedicationSpecificMessages} 
                                                                    onclick="if (window.pharmacyNotification.validateDrugFields('No Refill Remaining', true)) {
                                                                                window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                                        '', 'Drug Information', 'Pharmacy Notification', 'Drug Information');
                                                                            }"   style="width:171.85px; height:27px">Clinical Advisory <i class="fa fa-paperclip" aria-hidden="true"></i>
                                                            </button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="btn medical_icon" ${ManagePermissionMedicationSpecificMessages} 
                                                                    onclick="if (window.pharmacyNotification.validateDrugFields('No Refill Remaining', true)) {
                                                                                window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                                        '', 'Drug Information', 'Pharmacy Notification', 'Drug Information', 1);
                                                                            }"   style="width:171.85px; height:27px">Clinical Advisory <i class="fa fa-paperclip" aria-hidden="true"></i>
                                                            </button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${(order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)}">
                                                            <button id="paymentLnk" class="btn proceed_btn" ${ManagePermissionMedicationSpecificMessages} onclick="if (window.pharmacyNotification.validateOrderProcessFields() && window.pharmacyNotification.validateFinalPayment()) {
                                                                        window.pharmacyNotification.checkOtherStatusBitHandler(9, 'Waiting Pt Response');
                                                                    }" ${orderStatusPendingDisabledButtons}  style="width:171.85px; height:27px" >$ Rx  <span>></span> Quote - Proceed?
                                                            </button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button id="paymentLnk" class="btn proceed_btn" ${ManagePermissionMedicationSpecificMessages} onclick="if (window.pharmacyNotification.validateOrderProcessFields() && window.pharmacyNotification.validateFinalPayment()) {
                                                                        window.pharmacyNotification.checkOtherStatusBitHandler(9, 'Waiting Pt Response');
                                                                    }" ${fieldsDisabledFlag} ${orderStatusPendingDisabledButtons}  style="width:171.85px; height:27px" ${fieldsDisabledFlag}>$ Rx  <span>></span> Quote - Proceed?
                                                            </button>
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <input type="hidden" id="lowerCost" value="0" />


                                                    <c:choose>
                                                        <c:when test="${order.deliveryService eq 'Same Day'}">
                                                            <input type="button" id="lowerCostBtn" name="lowerCostBtn" class="btn proceed_btn" value="LOWER COST OPTION" style="background:#3a89d7;color:#ffffff; width: 171.85px" disabled> 
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="button" id="lowerCostBtn" name="lowerCostBtn" class="btn proceed_btn" value="LOWER COST OPTION"  
                                                                   onclick=" window.pharmacyNotification.setLowerCost(1);
                                                                           window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                                   '', 'Lower Cost Option', 'Pharmacy Notification', 'Lower Cost Option', null, null, 0);
                                                                   "
                                                                   ${ManagePermissionMedicationSpecificMessages} ${responseBtnDisabledFlag} ${orderStatusPendingDisabledButtons} style="background:#3a89d7;color:#ffffff; width: 171.85px">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="type_comment col-md-3 col-sm-3">

      <!--<textarea id="orderComments" class="form-control" placeholder="Type Comment">${not empty order.comments?order.comments:''}</textarea>-->
                                                    <form action="/ConsumerPortal/rxDetail/${orderId}/${patientId}?delete=1&profile=1&status=${order.status}" 
                                                          method="post" id="delform" name="delform">
                                                        <a id="delLnk" onclick="document.getElementById('delform').submit();
                                                                return false;" class="btn btn_one" style="display:none"></a> 
                                                    </form>
                                                    <form action="/ConsumerPortal/rxDetailRefillHandler/${patientId}" id="refillForm">
                                                        <input type="hidden" id="refillOrder" name="refillOrder" value="" />
                                                        <a id="refillLnk" onclick="document.getElementById('refillForm').submit();
                                                                return false;" class="btn btn_one" style="display:none"></a> 
                                                    </form>

                                                    <div class="tables_buttons">
                                                        <form action="/ConsumerPortal/rxDetail/${orderId}/${patientId}?success=1&profile=1&status=${order.status}" method="post" id="form" name="form">
                                                            <a id="backLnk" onclick="document.getElementById('form').submit();
                                                                    return false;" class="btn btn_one" style="display:none"></a> 



                                                            <%--            <c:choose>
                                                                            <c:when test="${pageContext.request.getParameter('status') == null || pageContext.request.getParameter('success') != null}">
                                                                                <input type="button" id="cancel" name="cancel" value="Cancel" class="btn btn_dtail cancelbt" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/successSignin/'">
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <input type="button" id="cancel" name="cancel" value="Cancel" class="btn btn_dtail cancelbt" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/queuePatientDetailPage/${patientId}/${sessionBeanPortal.selectedTab}?fromDate=${pageContext.request.getParameter('fromDate')}&toDate=${pageContext.request.getParameter('toDate')}&status=${pageContext.request.getParameter('status')}'">
                                                                            </c:otherwise>
                                                                        </c:choose>--%>

                                                            <!--  <c:choose>
                                                                <c:when test="${order.status eq 'Shipped' || order.status eq 'Pickup At Pharmacy' ||  order.status eq 'DELIVERY' || order.status eq 'Waiting payment authorization'  || order.status eq 'Cancelled' || order.status eq 'Ready to Fill' || order.status eq 'Filled'}">
                                                                    <input type="button" id="saveBtn" name="save" value="Update" class="btn btn_dtail pull-right" disabled="true" onclick="needToConfirm = false;
                                                                            window.pharmacyNotification.processOrder(1, '');" ${ManagePermissionMedicationSpecificMessages}>    
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="button" id="saveBtn" name="save" value="Update" class="btn btn_dtail pull-right" onclick="needToConfirm = false;
                                                                            window.pharmacyNotification.processOrder(1, '');" ${ManagePermissionMedicationSpecificMessages} ${fieldsDisabledFlag} ${imgVerifiedFlag}>
                                                                </c:otherwise>
                                                            </c:choose>-->
                                                        </form>
                                                        <form 
                                                            action="/ConsumerPortal/rxDetail/${orderId}/${patientId}?success=1&verified=1&profile=1&status=${order.status}" 
                                                            method="post" id="formVerified" name="form">
                                                            <a id="verifiedLnk" onclick="document.getElementById('formVerified').submit();
                                                                    return false;" class="btn btn_one" style="display:none"></a> 




                                                        </form>
                                                    </div>
                                                    <div class="tables_buttons">
                                                        <c:if test="${order.requiresDeletion eq true || (sessionBeanPortal.pmap[(95).intValue()].view eq true && 
                                                                      sessionBeanPortal.pmap[(95).intValue()].manage eq true)}">
                                                              <div class="cancel_bton">

                                                                  <input type="button" class="btn btn-cancl" value="Delete Order" onclick="window.pharmacyNotification.deleteOrderHandler();" ${statusBtnDisabledFlag}>
                                                              </div>
                                                        </c:if>
                                                        <input type="button" id="cancel" name="cancel" value="RESET FIELDS" class="btn btn_dtail cancelbt" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${orderId}/${patientId}?profile=1&status=${order.status}'">
                                                        <input type="hidden" value="${nextOrder}" id="nextOrder" /> 
                                                    </div>
                                                    <div class="tables_buttons">


                                                        <a href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest" class="back_qq" name="back"></a>
                                                        <c:choose>
                                                            <c:when test="${not empty nextOrderId && nextOrderId ne 0}"> 
                                                                <c:choose>
                                                                    <c:when test="${nextPatientId eq '0'}">
                                                                        <input type="button" id="Next" name="Next" value="" class="next_qq" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${nextOrderId}/${patientId}?status=${status}&profile=1&index=${index}'" ${ManagePermissionMedicationSpecificMessages}>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="button" id="Next" name="Next" value="" class="next_qq" onclick="window.pharmacyNotification.loadOrderForNextPatient()">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="button" id="Next" name="Next" value="" class="next_qq" disabled="true">
                                                            </c:otherwise>
                                                        </c:choose>                       

                                                        <!--                                                    <input type="button"  value="Verify" class="btn btn_dtail btn_Overify" onclick="window.pharmacyNotification.verifyOrderFlds();">-->

                                                    </div>
                                                    <div class="tables_buttons">

                                                        <c:choose>
                                                            <c:when test="${order.status eq 'Shipped' || order.status eq 'Pickup At Pharmacy' ||  order.status eq 'DELIVERY' || order.status eq 'Waiting payment authorization'  || order.status eq 'Cancelled' || order.status eq 'Ready to Fill' || order.status eq 'Filled'}">
                                                                <input type="button" id="saveBtn" name="save" value="UPDATE DATA" class="btn btn_dtail pull-right" disabled="true" onclick="needToConfirm = false;
                                                                        window.pharmacyNotification.processOrder(1, '');" ${ManagePermissionMedicationSpecificMessages}>    
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="button" id="saveBtn" name="save" value="UPDATE DATA" class="btn btn_dtail pull-right" onclick="needToConfirm = false;
                                                                        window.pharmacyNotification.processOrder(1, '');" ${ManagePermissionMedicationSpecificMessages} ${fieldsDisabledFlag} ${imgVerifiedFlag} ${waitingPtResponseFlag}>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <div class="proccessed_div">

                                                        <!--                                                    <label>System Generated Rx#</label>
                                                                                                            <input id="systemGeneratedRxNumberFld" type="text" class="form-control" maxlength="15">-->

                                                        <c:choose>
                                                            <c:when test="${order.statusId eq 8 || order.requestType eq 'Refill'}">
                                                                <input type="button"  data-toggle="modal"  id="filledHanlerLnk" class="btn btn_dtail"   value="FILL/REFILL RX"
                                                                       onclick="window.pharmacyNotification.fillHandler('fillDivArea',${order.statusId})"      ${imgVerifiedFlag}/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="button"  data-toggle="modal"  id="filledHanlerLnk" class="btn btn_dtail"   value="FILL/REFILL RX"
                                                                       onclick="window.pharmacyNotification.fillHandler('fillDivArea',${order.statusId})"      ${imgVerifiedFlag} ${waitingPtResponseFlag}/>
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <!--   <button class="btn btn-refill-rx">REFILL RX</button>-->
                                                    </div>
    <!--                                                <div id="fillDivArea" class="proccessed_div" style="display:none"><input id="systemGeneratedRxNumberFld" placeholder="New Rx."  type="text" class="form-control" maxlength="15" value="${order.systemGeneratedRxNumber}">
                                                        <input id="filledlnk"   type="button" class="form-control btn-blue"  value="DONE" data-target="#myModal"
                                                               onclick="window.pharmacyNotification.refillOrder(${order.orderId},${order.statusId});" />
                                                    </div>-->



                                                    <div class="shipping_btns clearfix" style="display:none">
                                                        <input type="hidden" id="errFlagFld" value="0" />
                                                        <input type="button"  id="shippedLnk" class="btn btn_dtail" 
                                                               value="SHIPPED" onclick="window.pharmacyNotification.checkOtherStatusBitHandler(6, 'Shipped');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag} ${ManagePermissionAccesssShippedButton} />

                                                        <input type="button"   id="courierLnk" class="btn btn_dtail"  
                                                               value="DELIVERY"  onclick="window.pharmacyNotification.checkOtherStatusBitHandler(5, 'DELIVERY');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag} ${ManagePermissionAccesssDeliveryButton} />


                                                        <input type="button" id="pickFromPharmacyLnk" class="btn btn_dtail full_btn"
                                                               onclick="window.pharmacyNotification.checkOtherStatusBitHandler(15, 'Pickup At Pharmacy');" ${statusBtnDisabledFlag} ${deliveryPreferecePharmacyDisabledFlag} value="PICKED FROM PHARMACY" />
                                                    </div>

                                                </div>
                                            </div>


                                        </div>

                                        <jsp:include page="../patientprofile/inAppNotificationMsgs.jsp" />
                                        <jsp:include page="../patientprofile/dependentmodal.jsp" />



                                        <div class="col-sm-8 top10_padding"></div>
                                        <div class="col-sm-4 top10_padding">

                                        </div>

                                    </div>

                                    <div class="col-sm-12 col-lg-5 rightvideo">

                                        <div id="ptInCommingRes" class="medicationModal patient_response confirmation_modal listModal healthModal formModal" style="display:${showPtRespQDiv};padding-bottom: 5px;">

                                            <div class="">
                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <c:forEach var="row" items="${incommingPtResponseQuestion}">
                                                        <input type="hidden" id="hiddenQuestionId" value="${row.id}" />
                                                        <input   type="hidden" id="hiddenQuestionOrderId" value="${order.orderId}" />

                                                        <div class="modal-header modal_header_style">
                                                            <!-- <button type="button" class="close" data-dismiss="modal" >&times;</button> -->
                                                            <h4  class="modal-title disp_in_mod"style="padding-left:5px;"><label>INCOMMING PT RESPONSE</label></h4>
                                                            <h5 class="modal-title left left_align" style="float: right;"><label><strong>Likely Topic</strong> Lower cost option</label></h5>
                                                        </div>
                                                        <div class="modal-body refill_medi">
                                                            <div>
                                                                <div>
                                                                    <div class="col-sm-12 time_inner_modal patientname">                  
                                                                        <div class="col-sm-7">
                                                                            <h6>
                                                                                <strong>${row.patientName} <small><fmt:formatDate pattern="HH:mm:ss" value="${row.questionTime}"/> </small></strong>                                                
                                                                                <span class="rx" >  REQ&nbsp#.</span>
                                                                                <span><a href="#" style="color:red;  padding-left: 0px;">${row.systemGeneratedRxNumber}</a></span>                                                                     

                                                                            </h6>
                                                                            <p class="clearfix question_"><span>Q:<strong>${row.question}</strong></span>
                                                                            </p>
                                                                        </div>

                                                                        <div class="col-sm-5 line_inner_modal direct_link" style="padding-left: 0px;padding-right: 0px; padding-top: 5px;">
                                                                            <h5>
                                                                                direct link: <span>${row.patientEmail}</span>
                                                                            </h5>
                                                                            <!--                                                                    <h4 id="osType">
                                                                            ${row.osType}
                                                                        </h4>-->
                                                                            <h3>
                                                                                Direct link: <span>${row.patientPhoneNumber}</span>
                                                                            </h3>
                                                                        </div>
                                                                        <!--                                                                    <h6 class="col-sm-6">
                                                                                                                                                <div class="col-sm-2">
                                                                                                                                                    <span id="reqFldAnsQuestionLabel">  Rx#:</span>
                                                                                                                                                </div>
                                                                                                                                                <div class="col-sm-9">
                                                                                                                                                    <span ><a href="#" id="reqFldAnsQuestionValue">${row.systemGeneratedRxNumber}</a></span>
                                                                                                                                                </div>
                                                                                                                                            </h6>-->
                                                                    </div>
                                                                    <!--                                                                <div class="col-sm-12 line_inner_modal">
                                                                                                                                        <h5>
                                                                                                                                            direct link: <span id="patientEmail">${row.patientEmail}</span>
                                                                                                                                        </h5>
                                                                                                                                        <h4 id="osType">
                                                                    ${row.osType}
                                                                </h4>
                                                                <h3>
                                                                    Direct link: <span id="mobileNumber">${row.patientPhoneNumber}</span>
                                                                </h3>
                                                            </div>-->
                                                                    <div class="refill_options no_disp question_p">
    <!--                                                                    <p class="col-sm-6 clearfix" style="padding: 0px 0px 0px 5px;margin-bottom: 0px;"><span id="questionSpan">Q:<strong>${row.question}</strong></span>
                                                                        </p>-->

                                                                        <div class="">
                                                                            <div class="col-sm-1 padd-zero answer_padd" style="margin-top: 5px;margin-right: -16px;padding-left: 3px; font-size: 10px;"> <span class="answer" style="font-size: 10px;">A:</span>
                                                                            </div>
                                                                            <div class="col-sm-6 padd-zero  ">
                                                                                <input type="text" class="form-control"  id="answerFld" style="height:20px; margin-left: -14px;" /> </div> 
                                                                        </div>
                                                                        <!--<br />-->
                                                                        <div class=" col-sm-5 align-center" style="padding-right:0px; margin: -7px 0px 0px 0px;">
                                                                            <button id="cancelAnswerBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 65px; vertical-align: middle; background-color: #f60000; font-size:12px; line-height:11px; font-family: 'Roboto Condensed', sans-serif; color: #ffd600; font-weight:bold;" onclick="window.pharmacyNotification.isDisplayPtResponseSection(1)">Cancel</button>
                                                                            <input id="answerSendBtn" type="button" class="btn back_btn" value="Responded" 
                                                                                   onclick="window.pharmacyNotification.saveAnswer();" 
                                                                                   style="width: 85px;vertical-align: middle; font-size:12px; line-height:12px;margin-right: 10px; font-family: 'Roboto Condensed', sans-serif; color: #ffd600; font-weight:bold;">
                                                                        </div>
                                                                    </div>
                                                                </div> 
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>

                                        <ul class="nav nav-tabs" id="detail3Tabs">

                                            <c:choose>
                                                <c:when test="${(order.status eq 'Filled' || order.status eq 'Shipped' || order.status eq 'Pickup At Pharmacy' || order.status eq 'DELIVERY' || order.status eq 'Ready to Deliver Rx' ||  order.handDelivery eq 1) && ManagePermissionUpdateClerk}">
                                                    <li><a data-toggle="tab" href="#image">IMAGE <span style="color: #e9e601;">(${imageCount})</span></a></li>
                                                    <!--                                                    <li id="shippingInfoTab" class="active"><a data-toggle="tab" href="#shipping">SHIPPING INFO</a></li>-->
                                                    <li><a data-toggle="tab" href="#fillHistory">Fill History <span style="color: #e9e601;">(${fn:length(filledOrdersLst)})</span></a></li>
                                                    <li><a data-toggle="tab" href="#mbrDialogTab">MBR DIALOG LOG <span style="color: #e9e601;">(${fn:length(questions)})</span></a></li>
<!--                                                    <li id="multiRxTab" style="display: ${order.status eq 'Filled' && order.deliveryId eq 1?'':'none'}"><a data-toggle="tab" href="#sameDayOrders">Multi Rx SHIPPING <span style="color: #e9e601;">(${fn:length(sameDayOredrsList)})</span></a></li>-->
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="active"><a data-toggle="tab" href="#image">IMAGE <span style="color: #e9e601;">(${imageCount})</span></a></li>
                                                    <li ><a data-toggle="tab" href="#fillHistory">Fill History <span style="color: #e9e601;">(${fn:length(filledOrdersLst)})</span></a></li>
                                                    <li ><a data-toggle="tab" href="#mbrDialogTab">MBR DIALOG LOG <!--<span style="color: #e9e601;">(${fn:length(questions)})</span>--></a></li>
                                                    </c:otherwise>
                                                </c:choose>

                                        </ul>

                                        <div class="tab-content" >
                                            <div id="image" class="${imageDivStyle}">
                                                <c:if test="${not empty order.ptVideo}">
                                                    <video id="videoContainer"    controls preload="none" >
                                                        <source src="${order.ptVideo}" type="video/mp4">
                                                    </video>
                                                </c:if>

                                                <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false">

                                                    <!-- Wrapper for slides -->
                                                    <div class="carousel-inner">
                                                        <div class="item active" id="drugImageDiv">
                                                            <c:if test="${not empty order.transferImage}"> 
                                                                <div id="dvLoading"></div>
                                                                <img id="drugImg"  src="${order.transferImage}"/>

                                                            </c:if>

                                                        </div>

                                                        <div class="item">
                                                            <c:if test="${not empty order.transferImage}">                           
                                                                <img  src="${order.transferImage}" 
                                                                      />
                                                            </c:if>

                                                        </div>

                                                        <div class="item">
                                                            <c:if test="${not empty order.transferImage}">                           
                                                                <img id="drugImage"  src="${order.transferImage}" 
                                                                     />
                                                            </c:if>

                                                        </div>
                                                    </div>

                                                    <!-- Left and right controls -->
                                                    <input type="hidden" id="imageId" value="${order.imageId}" />
                                                    <a class="left carousel-control" href="#" data-slide="prev">
                                                        <span class="fa fa-arrow-left" aria-hidden="true" style="display:none" id="prevImage" onclick="window.pharmacyNotification.loadPrevImage();"></span>
                                                        <span class="sr-only" >Previous</span>
                                                    </a>
                                                    <c:if test="${imageCount > 1}" >
                                                        <a class="right carousel-control"  onclick="window.pharmacyNotification.loadNextImage();"  data-slide="next">
                                                            <span class="fa fa-arrow-right" aria-hidden="true" id="nextImage"></span>
                                                            <span class="sr-only">Next</span>
                                                        </a>
                                                    </c:if>
                                                </div>
                                                <div class="img_buttons" >
<!--                                                    <input type="image" src="${pageContext.request.contextPath}/resources/images/plus.png" onclick="setZoom('1.75', 'image', 1)"  />
                                                    <input type="image" src="${pageContext.request.contextPath}/resources/images/minus.png" onclick="setZoom('0', 'image', 0.26)" />-->
                                                    <input id="zoomOpts" value="0" type="hidden"/>
                                                    <input id="btnZoomIn" type="image" src="${pageContext.request.contextPath}/resources/images/plus.png" onclick="zoomin(1);" />
                                                    <input id="btnZoomOut" type="image" src="${pageContext.request.contextPath}/resources/images/minus.png" onclick="zoomin(0);" />
                                                    <input type="image" src="${pageContext.request.contextPath}/resources/images/rotate.png" id="rotateDrug" />
                                                    <input type="hidden" id="zoomFactor" value="1">
                                                </div>

                                            </div>

                                            <!---------------------------------------------------------------------------->
                                            <!------------------------------------------------------------->
                                            <div id="mbrDialogTab" class="tab-pane fade video_div_two">
                                                <div class="patient_coment_list" >
                                                    <h2><i class="fa fa-comments" aria-hidden="true"></i> MBR Dialog Log</h2>


                                                    <c:forEach var="question" items="${questions}" >
                                                        <p>${question.question}
                                                            <br>(${question.questionTimeStr})
                                                        </p>
                                                    </c:forEach>
                                                    <a href="#" class="greenText messageHistory pull-right" onclick="displayModal(${order.orderId}, 'historyModal', 'viewHistoryMessageWs')">View Message History</a>
                                                    <div class="textBox detail_comment">

                                                        <i class="fa fa-paperclip fileUpload" title="upload file" onclick="window.pharmacyNotification.uploadFile()"></i>
                                                        <input id="attachFile" type="file" style="display: none;" accept="image/*" onchange="window.pharmacyNotification.validateFileFormat();" ${ManagePermissionSendMessage}/>
                                                        <textarea id="messageId" name="pharmacyNote" class="messageBox"  onclick="window.pharmacyNotification.resetMessageFn();" placeholder="Send Message to Patient"></textarea>

                                                    </div>
                                                    <button type="button" class="btn-message" onclick="window.pharmacyNotification.savePharmacyNotificationFn();" ${ManagePermissionSendMessage}><i class="fa fa-paper-plane greenText"></i></button>
                                                </div>


                                                <div id="errorSendMessage" class="pull-right"></div>
                                            </div>
                                            <!------------------------------------------------------------->
                                            <input type="hidden" id="itemIds" name="itemIds" value="" />
                                            <div id="fillHistory" class="tab-pane fade video_div_two" >
                                                <button type="button" class="btn btn_dtail" id="refillBtn" onclick="window.pharmacyNotification.refillSelectedOrders('fillTable', 'refillBtn')" disabled>Add to Refill</button>
                                                <table class="table-striped table table-three" id="fillTable">
                                                    <thead>
                                                        <tr>
                                                            <th>&nbsp;</th>
                                                            <th>Rx No.</th>
                                                            <th>Date<br>Of Svc</th>
                                                            <th>Medication</th>
                                                            <th>Qty</th>
                                                            <th>Days<br>Supply</th>
                                                            <th>Days<br>Until<br>Refill</th>
                                                            <th>Fills<br>Remain</th>
                                                            <th>Final<br>Payment</th>
                                                            <th>Origin</th>
                                                            <th>Self<br>Pay</th>
                                                            <th>Public<br>Ins</th>
                                                            <th>Compliance<br>Rewards</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="row" items="${filledOrdersLst}">   
                                                            <tr id="fillTable_tr_"${row.id}>
                                                                <td><input type="checkbox" id="fillTable_td_1_"${row.id} name="multirxDCB" value="${row.id}"   onclick="window.pharmacyNotification.controllingActionButtonForRefill('fillTable', 'refillBtn')"></td>
                                                                <td class=""><a href="/ConsumerPortal/rxDetail/${row.id}/${patientId}" style="color: blue;float: left;" >${sessionBeanPortal.pharmacyAbbr}-${row.systemGeneratedRxNumber}</a></td>
                                                                <td>
                                                                    <fmt:formatDate value='${row.filledDate}' pattern='yyyy-MM-dd' var="date" />

                                                                    ${date}
                                                                </td>
                                                                <c:choose>
                                                                    <c:when test="${row.genericName eq '*BRAND NAME*'}" >
                                                                        <td>${row.brandName}&nbsp;${row.drugType}&nbsp;${row.strength}
                                                                            <small style="color:red">*BRAND NAME*</small></td>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <td>${row.drugName}&nbsp;${row.drugType}&nbsp;${row.strength}</td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <td>${row.qty}</td>
                                                                <td>${row.daysSupply}</td>
                                                                <c:choose>
                                                                    <c:when test="${row.overdueFlag eq true}" >
                                                                        <th style="color:red;font-weight:bold">${row.daysToRefill}</th>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <th>${row.daysToRefill}</th>
                                                                        </c:otherwise> 
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${row.refillsRemaining eq 0}" >
                                                                        <th style="color:red">${row.refillsRemaining}</th>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <th>${row.refillsRemaining}</th>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${row.selfPayCheck eq 'Y'}">
                                                                        <th style="color:blue; font-weight: bold">${row.finalPaymentStr}</th>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <th style="color:red; font-weight: bold">${row.finalPaymentStr}</th>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                <th class="${row.requestStyle}"> <span class="custom_span">X-FER</span><br>${row.requestStr}</th>
                                                                    <c:choose>
                                                                        <c:when test="${row.selfPayCheck eq 'Y'}">
                                                                        <th>${row.selfPayCheck}</th>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <th style="color:red; font-weight: bold">${row.selfPayCheck}</th>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${row.publicInsCheck eq 'N'}">
                                                                        <th>${row.publicInsCheck}</th>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <th style="color:red; font-weight: bold">${row.publicInsCheck}</th>
                                                                        </c:otherwise>
                                                                    </c:choose>        


                                                                <th>${row.redeemPoints}</th>

                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div id="sameDayOrders" class="tab-pane fade video_div_two">
                                                <button type="button" class="btn btn_dtail" id="btnShippingMultiRx" onclick="window.pharmacyNotification.processSameDayShippingRxOrders('programRxModal', 0);" disabled>Add to Shipping</button>
                                                <table class="table-striped table table-three" id="sameDayOrdersTable">
                                                    <thead>
                                                        <tr>
                                                            <th><input id="chkAllSameDay1" type="checkbox" name="chkAllSameDay" style="display: ${not empty sameDayOredrsList?'':'none'};" class="left" title="SELECT ALL SAME DAY ORDERS" onclick="window.pharmacyNotification.allSameDayMainChkBox();"></th>
                                                            <th>RX#</th>
                                                            <th class="txtAlign">DATE<br/>COMPLETED</th>
                                                            <th id="medication">MEDICATION</th>
                                                            <th>QTY</th>
                                                            <th>INS</th>
                                                            <th class="txtAlign">PT OUT OF<br/>PCKT</th>
                                                            <th>Service</th>
                                                            <th class="txtAlign">DELIVERY<br/>ZIP CODE</th>
                                                            <th class="txtAlign">RX ORIGIN</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="row" items="${sameDayOredrsList}">
                                                            <tr>
                                                                <td>
                                                                    <input id="rxNumber-${row.id}" type='checkbox' value="${row.id}" class='left allProgramRxChk' name='allProgramRxChk' onchange='window.pharmacyNotification.populateAllProgramRxComboValue();'/>
                                                                </td>
                                                                <td class="rxNumber">
                                                                    <c:choose>
                                                                        <c:when test="${not empty row.systemGeneratedRxNumber}">
                                                                            <span class='bold_new'>RXD-<br/></span>${row.systemGeneratedRxNumber}
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            --
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                                <td class="dateCompletedField">
                                                                    <c:url var="rxUrl" value="/ConsumerPortal/rxDetail/${row.id}/${patientProfile.id}"/>
                                                                    <c:if test="${row.careGiver eq 1}">
                                                                        <c:url var="rxUrl" value="/PharmacyPortal/careGiverDetail/${row.dependentId}?pq=1"/>
                                                                    </c:if>
                                                                    <a href="${rxUrl}" style="text-decoration: underline;">${fn:substring(row.orderDate, 0, 2)}-${fn:substring(row.orderDate, 3, 5)}-<span class='redText'>${fn:substring(row.orderDate, 6, 10)}</span></a>
                                                                </td>
                                                                <td>
                                                                    ${not empty row.drugName?row.drugName:''} ${not empty row.strength?row.strength:''} ${not empty row.drugType?row.drugType:''}
                                                                </td>
                                                                <td>
                                                                    ${not empty row.qty?row.qty:'-'}
                                                                </td>
                                                                <td>
                                                                    ${not empty row.paymentMode && row.paymentMode eq 'INSURANCE'?'INS':'<span class="redText">N</span>'}
                                                                </td>
                                                                <td>${row.finalPaymentStr}</td>
                                                                <td>
                                                                    <c:if test="${not empty row.deliveryPreferencesName}">
                                                                        <span style='color:red;'>!*Same<br/>DAY *!</span>
                                                                        </c:if>
                                                                </td>
                                                                <td>
                                                                    ${row.zipCode}
                                                                </td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${row.requestType eq 'X-FER LABEL SCAN'}">
                                                                            <span style="color: blue;">${row.requestType}</span>
                                                                        </c:when>
                                                                        <c:when test="${row.requestType eq 'X-FER RX SCAN'}">
                                                                            <span style="color: green;">${row.requestType}</span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span style="color: red;">${row.requestType}</span>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <c:if test="${ManagePermissionUpdateClerk == true}">
                                                <div id="shipping" class="${shippingDivStyle}">
                                                    <div id="divSameShippingOrders" style="display: none;">
                                                        <input id="btnSameShippingOrders" type="button" class="btn btn-blue1 right_flo" value="SAME DAY ShIPPING ORDERS (0)" onclick="window.pharmacyNotification.populateSameDayShippingRxOrderList(1);" title="CLICK HERE TO VIEW SAME DAY ShIPPING ORDERS"/>
                                                    </div>
                                                    <!--
                                                    <a href="#">Next Rx<i class="fa fa-arrow-right" aria-hidden="true"></i></a>-->
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <!-- <th class="width_twenty">${order.deliveryService}</th> -->
                                                                <th class="width_seventy">Location Alias</th>
                                                                <th>Delivery Address</th>
                                                                <th class="width_seventy">Zip</th>
                                                                <th class="width_twentyeight">Multi Rx</th>

                                                                <th class="width_seventy">SHIPPING <br /> REQUESTED</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <!-- <td>${patientProfile.defaultAddress}</td>
                                                                <td>76092</td>
                                                                <td class="redText">${order.deliveryService}</td>
                                                                <td>${order.handlingFee}</td> -->
                                                                <td>
                                                                    <c:if test="${order.deliveryUrgent eq true}">
                                                                        <span style="color:red"><b>!</b></span>
                                                                    </c:if>&nbsp;           
                                                                </td>

                                                                <td>${patientProfile.defaultAddress}</td>
                                                                <td id="zipCodeFId">${patientProfile.defaultAddresszip}</td>

                                                                <td id="multiRxCount">${filledIndex} of ${filledSize}</td>
                                                                <td>${order.deliveryService}</td>
                                                            </tr>

                                                        </tbody>
                                                    </table>
                                                    <table class="table table1">
                                                        <thead>
                                                            <tr>
                                                                <th>Ship Date</th>
                                                                <th>Carrier</th>

                                                                <!-- <th>Compliance Reward</th>  -->
                                                                <!-- <th>Tax</th> -->
                                                                <!-- <th>Final Co-Pay</th> -->

                                                                <th>Shipping SVCS</th>
                                                                <th class=""><input type="checkbox" id="waiveDeliveryFeeChkbox" ${processingBtnFlag} 
                                                                                    onclick="window.pharmacyNotification.waiveDeliveryFee(${order.handlingFee},${order.finalPayment});window.pharmacyNotification.addCommas('handlingFeeFld', 1)"><span>Waive Delivery Fee</span></th>


                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td> <input id="datetimepicker2" 
                                                                            onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" class="form-control calendar_field" placeholder="mm/dd/yyyy" ${shippedFlag} ${processingBtnFlag} value="<fmt:formatDate value='${order.shippedOn}' pattern='MM/dd/yyyy'/>"></td>
                                                                <td>   <select id="deliverycarrier" style="width:100%; height:24px;"  ${shippedFlag} ${processingBtnFlag} onchange="window.pharmacyNotification.selectDeliveryCarrierAction('deliveryPreferences', 4)">
                                                                        <option value="US MAil" ${order.carrier eq 'US MAil'? 'selected':''}> US POSTAL</option>
                                                                        <option value="IOMNI" ${order.carrier eq 'IOMNI'? 'selected':''}>IOMNI/Hand Delivery</option>
                                                                        <option value="UPS" ${order.carrier eq 'UPS'? 'selected':''}>UPS</option>
                                                                        <option value="FEDEX" ${order.carrier eq 'FEDEX'? 'selected':''}>FEDEX</option>
                                                                        <option value="Pharmacy Pickup" ${order.carrier eq 'Pharmacy Pickup'? 'selected':''}>Pharmacy Pickup</option>
                                                                    </select></td>
                                                                <!--  
                                                                 <td class="redText" style="min-width: 45px;">(-) ${order.actualrofitShareStr}</td>
                                                                 <td>-----</td>
                                                                 <td style="min-width:90px;" ><input id="finalCopayCol" value="${order.paymentExcludingDeliveryStr}" class="input_width form-control rightText" disabled="true"/>                                                               </td> -->

                                                                <td><select id="deliveryPreferences" onchange="window.pharmacyNotification.selectDeliverySvcsAction('deliverycarrier', 'Pharmacy Pickup')" style="width:100%; height:24px;"  ${shippedFlag} ${processingBtnFlag}>
                                                                        <option value="3" ${order.deliveryId eq 3 ? 'selected':''}>2nd Day</option>
                                                                        <option value="1" ${order.deliveryId eq 1 ? 'selected':''}>Same Day</option>
                                                                        <option value="2" ${order.deliveryId eq 2 ? 'selected':''}>Next Day</option>
                                                                        <option value="4" ${order.deliveryId eq 4 ? 'selected':''}>Pick Up at Pharmacy</option>

                                                                    </select></td>
                                                                <td>&nbsp;</td>


                                                            </tr>

                                                        </tbody>
                                                    </table>
                                                    <!--  <div class="delivery_fee">
                                                    <c:if test="${order.multiRx eq false}">
                                                        <input id="netAmountBtn" type="button" class="btn-delivery" value=" Amount to be Collected: ${order.finalPaymentStr}" disabled="true">          
                                                    </c:if>
                                                </div> -->
                                                    <div class="col-sm-12 time_date">
                                                        <!--
                                                        <span>Time: @ 5 Pm to 8 pm</span>
                                                        <span>Date: 6-6-2017</span>-->
                                                        <!--   <div class="hand_pickup">
                                                              <input type="button" class="btn btn-blue1" style="background: red;" value="Pharmacy Pickup"
                                                                     onclick="window.pharmacyNotification.checkStatusBit(15, 'Pickup At Pharmacy');" ${statusBtnDisabledFlag} ${deliveryPreferecePharmacyDisabledFlag} ${waitingPtResponseFlag}>
                                                              <input type="button" class="btn btn-blue1" value="Hand delivery" 
                                                                     onclick="window.pharmacyNotification.checkStatusBit(5, 'DELIVERY');"
                                                        ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag} ${waitingPtResponseFlag}>


                                             </div> -->
                                                    </div>
                                                    <table class="table table-two text_center">
                                                        <thead>
                                                            <tr>
                                                                <!--  <th>Multi Rx</th> -->

                                                                <th>Tracking #</th>
                                                                <th>Clerk</th>
                                                                <th>Shipping Fee</th>
                                                                <th>Orig Rx<br />
                                                                    Out of Pocket</th>
                                                                <th>Amount to be Collected</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <!--   <td>
                                                                <c:choose>
                                                                    <c:when test="${order.multiRx eq true}">
                                                                        <input type="checkbox" id="multiRxC" onclick="window.pharmacyNotification.multiRxOrder();">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="checkbox" id="multiRxC" disabled>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </td> -->

                                                                <td>
                                                                    <input id="traclingno" type="text" value="${order.tracking}" class="form-control" onkeypress="return IsAlphaNumericWithHyphen(event)" ${shippedFlag} ${processingBtnFlag}>
                                                                </td>
                                                                <td>
                                                                    <input id="clerk" class="form-control" value="${order.clerk}" ${shippedFlag} ${processingBtnFlag} onkeyup="window.pharmacyNotification.setUpperCaseChar(this.id, 0);"/>
                                                                </td>
                                                                <td style="min-width:60px;" >
                                                                    <input id="currentOrdShippingFee" type="hidden" value="${order.handlingFeeStr}"/>
                                                                    <input id="handlingFeeFld"  
                                                                           type="text" class="input_width form-control text_cent " value="${order.handlingFeeStr}" 
                                                                           onkeypress="return  float_validation(event, this.value)" ${processingBtnFlag}/>
                                                                </td>
                                                                <td class="text_cent" id="outOfPocketFId">
                                                                    ${order.paymentExcludingDeliveryStr}
                                                                </td>
                                                                <td class="text_cent" id="finalPaymentCol">
                                                                    ${order.finalPaymentStr}
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                    <c:if test="${order.multiRx eq true}">


                                                    </c:if>
                                                    <!-- <div class="delivery_fee"> -->
                                                    <!--
                                                                  <input type="button" class="btn-delivery" value="Delivery Fee: $40"></td>
                                                    -->   
                                                    <!--  <c:if test="${order.multiRx eq true}">
                                                         <input id="netAmountBtn" type="button" class="btn-delivery" value=" Amount to be Collected: ${order.finalPaymentStr}" disabled="true">
                                                    </c:if> -->


                                                    <!-- </div> -->
                                                    <!-- <input type="button" id="pickFromPharmacyLnk" class="btn btn-blue1" value="Picked from pharmacy"
                                                           onclick="window.pharmacyNotification.checkOtherStatusBitHandler(15, 'Pickup At Pharmacy');" ${statusBtnDisabledFlag} ${deliveryPreferecePharmacyDisabledFlag}>
                                                    <input type="button" id="shippedLnk" class="btn btn-blue1" value="Shipped" 
                                                           onclick="window.pharmacyNotification.checkOtherStatusBitHandler(6, 'Shipped');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag} >
                                                    <input type="button" id="courierLnk" class="btn btn-blue1" value="Delivery" 
                                                           onclick="window.pharmacyNotification.checkOtherStatusBitHandler(5, 'DELIVERY');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag}> -->
                                                    <div class="payment-charge">
                                                        <input type="button" style="background: red;"
                                                               onclick="if (window.pharmacyNotification.validateOrderProcessFields() && window.pharmacyNotification.validateFinalPaymentForPaymentFailureMsg()) {
                                                                           window.pharmacyNotification.setLowerCost(1);
                                                                           window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                                                   '', 'Payment Failure', 'Pharmacy Notification', 'Payment Failure')
                                                                       }" 
                                                               id="notCarried"  class="btn btn-blue1" value="PAYMENT FAILED"   ${statusBtnDisabledFlag} ${processingBtnFlag}/> 
                                                        <%--   <input type="button" class="btn btn-blue1" value="Charge payment & ship" onclick="window.transferRequest.postOrderToIOMNI();" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag}> --%>
                                                        <input type="button" class="btn btn-blue1" value="Charge payment & ship" onclick="window.pharmacyNotification.checkStatusBit(6, 'Shipped');" ${statusBtnDisabledFlag} ${waitingPtResponseFlag} ${processingBtnFlag}>
                                                    </div>
                                                </c:if>
                                                <!---------------------------------------------------------------------------->
                                                <div class="video_controlls">


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
                    <jsp:include page="processAllSameDayRxOrderModel.jsp" />
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
                    <!--                    <input type="button" id="popup1" 
                                                <input id="popUpbtn" type="button" class="btn back_btn" value="OK" 
                                                                           onclick="/PharmacyPortal/newMemberRequest" 
                                                                           style="display: none;"> -->

                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/notifier.js"></script>


                    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientDependent.js"></script>
                    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInsurancePharmacy.js"></script>
                    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
                    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
                    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/jquery.toaster.js"></script>
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/jquery.min.js"></script>
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/wheelzoom.js"></script>
                    <script type="text/javascript">

                                                            setTimeout(function ()
                                                            {
                                                                window.pharmacyNotification.setIntervalAndExecute(window.pharmacyNotification.popUp, 300000);
                                                            }, 300000);

//        window.pharmacyNotification.setIntervalAndExecute(window.pharmacyNotification.popUp, 300000);
//                    (function($){
//                                    alert("aaaaaaaaaaaaaaa");
//                          $.notifier({
//                         "type": "error",
//                        "title": "Hello Notification",
//                        "text": "Rx-Direct is pahramcy Apllication",
//                        "positionX": "right",
//                        "delay": 4000,
//                        "number" : 5,
//                        "animationIn" : 'shake',
//                        "animationIn_duration": 'slow',
//                        "animationOut" : 'drop',
//                        "animationOut_duration" : 'slow'
//                         });
//                        })(jQuery);

                                                            $('#datetimepicker').datetimepicker({timepicker: false, format: 'm/d/Y',
                                                                onChangeDateTime: function (dp, $input) {
                                                                    jQuery('#datetimepicker').datetimepicker('hide');
                                                                }});

                                                            $('#datetimepicker2').datetimepicker({timepicker: false, format: 'm/d/Y',
                                                                maxDate: 0,
                                                                onChangeDateTime: function (dp, $input) {
                                                                    jQuery('#datetimepicker2').datetimepicker('hide');
                                                                }});

                                                            $('#datetimepicker3').datetimepicker({timepicker: false, format: 'm/d/Y',
                                                                minDate: 0,
                                                                onChangeDateTime: function (dp, $input) {
                                                                    jQuery('#datetimepicker3').datetimepicker('hide');
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



                        //            $(function () {
                        //                var pull = $('#pull');
                        //                menu = $('.Menu ul');
                        //                menuHeight = menu.height();
                        //                $(pull).on('click', function (e) {
                        //                    e.preventDefault();
                        //                    menu.slideToggle();
                        //                });
                        //                        $(needToConfirm = false; window
                        //                                ).resize(function () {
                        //                var w = $(needToConfirm = false; window
                        //                        ).width();
                        //                        if (w > 320 && menu.is(':hidden')) {
                        //                    menu.removeAttr('style');
                        //                }
                        //            });
                        //            }
                        //            );
                        $(document).ready(function (e) {
                            //$('#drugImg').attr('src');
                            // $('#drugImageDiv').zoom({url:$('#drugImg').attr('src')});
                            var drugImg = document.querySelector('#drugImg');
                            wheelzoom(drugImg);
                            window.pharmacyNotification.appendCurrencySignFromNumricFlds();
                            window.pharmacyNotification.appendFldValue('estPriceFld', 1, '$');
                            allergiesCount();
                            $(window).bind("load", function () {

                                window.pharmacyNotification.selfPayEvent();
                            })
                        });
                    </script>

                    <script type="text/javascript">
                        /////////////////////////////////////

                        /////////////////////////////////////////////////////
                        var angleDrug = 0;
                        var imgDrug = document.getElementById('drugImageDiv');
                        document.getElementById('rotateDrug').onclick = function () {
                            //alert("angle drug "+angleDrug);
                            angleDrug = (angleDrug + 90) % 360;
//                            alert("angle drug "+angleDrug);  
                            imgDrug.className = image.className + " rotate" + angleDrug;
                        }
                        /////////////////////////////////////////////////////    
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
                        var vid = document.getElementById("drugImg");//("videoContainer");



                        function setPlaySpeed() {
                            vid.playbackRate = 3.0;

                        }


                        function setZoom(point1, div, zoomin) {
                            /* predefine rotate */
                            var point = parseFloat($("#zoomFactor").val());
                            if (zoomin == 1)
                            {
                                point = point + .25;
                            } else
                            {
                                if (point > .25)
                                {
                                    point = point - .25;
                                }
                            }
                            $("#zoomFactor").val(point);
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
                            //alert("A "+"#"+div);
                            vid.style[prop] = 'scale(' + point + ') rotate(' + rotate + 'deg)';
                            $("#" + div).attr("style", "overflow:auto;");
                        }
                        function zoomin(ops) {
//                            var myImg = vid;
//                            var currWidth = myImg.clientWidth;
//                            myImg.style.width = (currWidth + 100) + "px";
                            $("#zoomOpts").val(ops);
                            $("#drugImg").click();
                        }
                        function zoomout() {
                            var myImg = vid;
                            var currWidth = myImg.clientWidth;
                            if (currWidth == 100)
                                return false;
                            else {
                                myImg.style.width = (currWidth - 100) + "px";
                            }
                        }
                        function setFullScreen()
                        {


                            var videoElement = document.getElementById('videoContainer');
                            videoElement.webkitRequestFullScreen();
                        }

                        function ZoomIn(frontImage) {
                            // alert("hi "+document.getElementById(frontImage)+" zoom "+document.getElementById(frontImage).style.zoom);
                            var ZoomInValue = parseInt(document.getElementById(frontImage).style.zoom) + 1 + '%'
                            //alert("zoom in value ");
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
                        function isEnableField(fId) {
                            $("#" + fId).removeAttr("disabled");
                        }

                        function selectAllMutliRx() {
                            if ($("#thMultiRxCB").prop("checked") == true) {
                                $('input[name="multirxDCB"]').prop('checked', true);
                            } else {
                                $('input[name="multirxDCB"]').prop('checked', false);
                            }
                        }
                        function allergiesCount() {
                            var allergiesLength = $("#allergiesDiv").text().length;
                            if (allergiesLength > 255) {
                                return false;
                            }
                            if (allergiesLength >= 125) {
                                $("#allergiesDiv").attr("style", "overflow:auto;height:60px;");
                            }

                            return true;
                        }
                    </script>
                    <script>
                        $('.modal').modal({keyboard: false,
                            show: false
                        });
                        //Jquery draggable
                        $('.modal-dialog').draggable({
                            handle: ".modal-header"
                        });

                    </script>
                    <script>
                        // show loading image
                        $('#dvLoading').show();

                        // main image loaded ?
                        $('#drugImg').on('load', function () {
                            // hide/remove the loading image
                            $('#dvLoading').hide();
                        });

                        $(window).bind("load", function () {
                            $('#dvLoading').fadeOut(2000);
                        });
                    </script>

                    <style>
                        #dvLoading {
                            background:url(http://loadinggif.com/images/image-selection/36.gif) no-repeat center center;
                            height: 200px;
                            width: 200px;
                            position: fixed;
                            left: 50%;
                            top: 50%;
                            margin: -25px 0 0 -25px;
                            z-index: 1000;
                        }
                    </style>
                    <!--------------------------------------------------------------------------------------------->
                    <div id="giveAnswerOfQuestiona_dialog" class="medicationModal patient_response confirmation_modal listModal healthModal formModal modal fade" role="dialog" style="display: none;">
                        <input id="loadPage" type="hidden" <a href="#" aria-controls="presponse" role="tab" data-toggle="tab" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${orderId}/${patientId}'" /></a>

                        <input type="hidden" id="hiddenQuestionId" value="" />
                        <input   type="hidden" id="hiddenQuestionOrderId" value="" />

                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" >&times;</button>
                                    <h4  class="modal-title"><label>Question Answer Dialog</label></h4> 

                                </div>
                                <div class="modal-body refill_medi">
                                    <div id="postAnswerMessageDiv">
                                    </div>
                                    <div >
                                        <div class="refill_options">
                                            <p class="clearfix"><span id="questionSpan">Q:</span>

                                            </p>

                                            <div class="">
                                                <div class="col-sm-1 padd-zero" style="max-width: 25px;"> <span class="answer">A:</span>
                                                </div>
                                                <div class="col-sm-11 padd-zero ">
                                                    <input type="text" class="form-control"  id="answerFld" /> </div> 
                                            </div>

                                            <br />
                                            <div class="align-center">
                                                <input id="answerSendBtn" type="button" class="btn back_btn" value="Send" 
                                                       onclick="window.pharmacyNotification.saveAnswer();" 
                                                       style="width: 80px; vertical-align: middle;"> 

                                                <button id="cancelAnswerBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 80px; vertical-align: middle;">Cancel</button>
                                            </div>

                                        </div>
                                    </div> 
                                </div>
                            </div>
                        </div>
                    </div>

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

                                    <div class="padd_right_conform">


                                        <div class="refill_options">
                                            <p class="clearfix"><span>Do you want to delete this order?</span>

                                            </p>
                                            <div>
                                                <input id="confirmBtn" type="button" class="btn back_btn" value="OK" 
                                                       onclick="window.pharmacyNotification.deleteOrder(1);" 
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
                    <div id="addNewRxOrderDlg" class="medicationModal confirmation_modal listModal healthModal formModal modal fade" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" >&times;</button>
                                    <h4  class="modal-title"><label>Patient Information Dialog</label></h4> 

                                </div>
                                <div class="modal-body refill_medi">

                                    <div >


                                        <div class="refill_options">
                                            <p class="clearfix"><span>Do you want to place new order?</span>

                                            </p>
                                            <div> Mobile # <input type="text" id="addNewRxPatientMobileNo" /> </div>

                                            <div id="addNewRxpatientInformation"> 

                                            </div>
                                            <br />
                                            <div>
                                                <input id="confirmOtherStatusBtn" type="button" class="btn back_btn" value="Retrieve" 
                                                       onclick="window.pharmacyNotification.getPatientInformationByPhoneNumber();" 
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
                    <div class="modal fade" id="rxdetail3" role="dialog">
                        <div class="modal-dialog modal-dragable" style="top: 54% !important; right: 3%; position: relative; width: 350px;">
                            <div class="modal-content" >
                                <div class="modal-header" style="padding:0; text-align: center;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title" style="background:#0262ae;font-weight: bold; color: #ffffff; text-align: left;">Rx Number</h4>
                                </div>
                                <div class="modal-body" style=" height: 156x;">
                                    <div id="fillDivArea" class="proccessed_div">
                                        <div class="col-sm-12">
                                            <label id="abbrLabel">${sessionBeanPortal.pharmacyAbbr}-</label>
                                            <input class="custom-input" id="systemGeneratedRxNumberFld"  type="text" maxlength="15" value="${order.systemGeneratedRxNumber}" style="text-align: left;">

                                        </div>
                              <!--                                    <input id="systemGeneratedRxNumberFld" placeholder="New Rx." type="text" class="form-control" maxlength="15" value="${order.systemGeneratedRxNumber}">-->
                                        <input id="filledlnk" type="button" class="form-control btn-blue" value="DONE" onclick="window.pharmacyNotification.refillOrder(${order.orderId}, ${order.statusId});">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>
                        var input = document.getElementById("systemGeneratedRxNumberFld");
                        input.addEventListener("keyup", function (event) {
                            event.preventDefault();
                            if (event.keyCode === 13) {
                                document.getElementById("filledlnk").click();
                            }
                        });

                        $('.modal-dragable').draggable();

                    </script>
                    <!----------------------------------------------------------------------------------------------------------------->
                    <div class="modal fade mynew-modal" id="mytestModal" role="dialog">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">$ Refill too Soon- $ Option</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="col-sm-12 padd-zero">
                                        <h4>Medication: <span class="tab-right">Trandate {LABETALOL HCL} 200 MG TAB</span></h4>
                                        <ul>
                                            <li><a>Quantity : </a></li>
                                            <li><a>30</a></li>
                                        </ul>                  
                                    </div>
                                    <div class="col-sm-12 padd-zero ">                                   
                                        <h3 style="background: #c9c9c9;">CASH PAY-REFILL TOO SOON</h3>
                                        <h4>Subject: </h4>
                                        <ul>
                                            <li><a>Received (10/31/2017 09:19} </a></li>                                           
                                        </ul>
                                    </div>
                                    <div class="col-xs-12 reward_para modal_para padd-zero">
                                        <p>The medication you requested is not in our inventory at <span class="blue-text" style="color: #2368b2;" ><strong>RX-DIRECT HOME DELIVERY.</strong></span><br />
                                            We apologize for this inconvenience, and as act of goodwill offer you <span class="black-text" style="color: #333333;" ></span><strong>50 COMPLIANCE REWARD</strong>
                                            toward another purchase!
                                        </p>
                                    </div>

                                    <h5 style="margin: 0;">Details:</h5>
                                    <div class="details-div">
                                        <h4>Medication: <span class="tab-right"><strong>Labetalol HCL 200MG TAB</strong> Generic for Trandate</span> </h4>
                                        <h4>REFILL REMAIN:<span class="tab-right">0</span> </h4>
                                        <h4>Rx Expiration:</h4>
                                        <h6><label>RX-DIRECT Comments:</label></h6>
                                        <textarea class="form-control" rows="1" id="comment"></textarea>
                                    </div>
                                    <div><h4>YOU HAVE (3) OPTIONS </h4>

                                        <p><strong>1)</strong>You may <strong>cancel your this refill request</strong></p>
                                        <div class="send_rct"><a href="inapp://cancelOrder_201711014" class="ctr_btn btn red_bg">CANCEL REQUEST</a></div>
                                        <p><strong>2) If you have <span class="blue">a new written (paper) Rx or another current  Rx bottle then we can  accept a picture </span>
                                            </strong><a href="inapp://takePOAPicture"><img src="https://rxdirectdev.ssasoft.com/resources/images/take_pic.png" alt=""></a></p><div class="clearfix"></div><p></p>
                                        <p>
                                            <strong>3) Recommended: (default)</strong> Continue to wait until such time your refill is permitted.  This  request will be postponed until the Rx is refillable  in accordance with the wishes of your HCP
                                        </p>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <a href="#" class="ctr_btn btn backhome">Home</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!----------------------------------------------------------------------------------------------------------------->
                    <div class="modal fade mynew-modal" id="mynewModal" role="dialog">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Rx Not Carried for Now</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="col-sm-12 padd-zero">
                                        <h4>Medication: <span class="tab-right">Trandate {LABETALOL HCL} 200 MG TAB</span></h4>
                                        <ul>
                                            <li><a>Quantity : </a></li>
                                            <li><a>30</a></li>
                                        </ul>                  
                                    </div>
                                    <div class="col-sm-12 padd-zero ">                                   
                                        <h3>Medication not Carried</h3>
                                        <h4>Subject: </h4>

                                        <ul>
                                            <li><a>Received (10/31/2017 09:19} </a></li>                                           
                                        </ul>
                                    </div>
                                    <div class="col-xs-9 reward_para modal_para padd-zero">
                                        <p>The medication you requested is not in our inventory at <span class="blue-text" style="color: #2368b2;" ><strong>RX-DIRECT HOME DELIVERY.</strong></span><br />
                                            We apologize for this inconvenience, and as act of goodwill offer you <span class="black-text" style="color: #333333;" ></span><strong>50 COMPLIANCE REWARD</strong>
                                            toward another purchase!
                                        </p>
                                    </div>
                                    <div class="col-xs-3 reward_point modal_point"><img src="https://rxdirectdev.ssasoft.com/resources/images/reward1.png" alt=""><span class="blue">150</span>
                                        <h5>COMPLIANCE<br>
                                            REWARDS</h5>
                                    </div>
                                    <h5 style="margin: 0;">Details:</h5>
                                    <div class="details-div">
                                        <h4>Medication: <span class="tab-right"><strong>Labetalol HCL 200MG TAB</strong> Generic for Trandate</span> </h4>
                                        <h6><label>RX-DIRECT Comments:</label></h6>
                                        <textarea class="form-control" rows="1" id="comment"></textarea>
                                    </div>
                                    <p>In our efforts to provide the lowest prices we primarily furnish <strong>Generic Versions of most Drugs in Pill Forms.</strong></p>
                                    <div class="drug-lookup">
                                        <a href="#" ><img src="https://rxdirectdev.ssasoft.com/resources/images/drugLookup.png" alt=""></a>
                                        <p>Search Our Inventory at any time</p>
                                        <p><strong>If you have a new written (paper) Rx or another current Rx Bottle</strong>-we can accept<br />
                                            a picture from our existing customers and pick up the original.</p>
                                    </div>
                                    <div class="modal-f clearfix">
                                        <a href="#" class="btn btn-home">Home</a>
                                        <img src="https://rxdirectdev.ssasoft.com/resources/images/take_picture_img.png">
                                    </div>
                                </div>
                                <div class="modal-footer">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!--------------------------------------------------------------------------->
                <div class="modal fade mynew-modal" id="mynewModal2" role="dialog">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Rx Not Carried for Now</h4>
                            </div>
                            <div class="modal-body">
                                <div class="col-sm-12 padd-zero">
                                    <h4>Medication: <span class="tab-right">Trandate {LABETALOL HCL} 200 MG TAB</span></h4>
                                    <ul>
                                        <li><a>Quantity : </a></li>
                                        <li><a>30</a></li>
                                    </ul>                  
                                </div>
                                <div class="col-sm-12 padd-zero ">                                   
                                    <h3>Medication not Carried</h3>
                                    <h4>Subject: </h4>

                                    <ul>
                                        <li><a>Received ([date_time]) </a></li>                                           
                                    </ul>
                                </div>
                                <div class="col-xs-9 reward_para modal_para padd-zero">
                                    <p>The medication you requested is not in our inventory at <span class="blue-text" style="color: #2368b2;" ><strong>RX-DIRECT HOME DELIVERY.</strong></span><br />
                                        We apologize for this inconvenience, and as act of goodwill offer you <span class="black-text" style="color: #333333;" ></span><strong>50 COMPLIANCE REWARD</strong>
                                        toward another purchase!
                                    </p>
                                </div>
                                <div class="col-xs-3 reward_point modal_point"><img src="https://rxdirectdev.ssasoft.com/resources/images/reward1.png" alt=""><span class="blue">150</span>
                                    <h5>COMPLIANCE<br>
                                        REWARDS</h5>
                                </div>
                                <h5 style="margin: 0;">Details:</h5>
                                <div class="details-div">
                                    <h4>Medication: <span class="tab-right"><strong>[generic_name] [DRUG_STRENGTH] [DRUG_TYPE]</strong> Generic for [DRUG_NAME]</span> </h4>
                                    <h6><label>RX-DIRECT Comments:</label></h6>
                                    <textarea class="form-control" rows="1" id="commentInAppRx"></textarea>
                                </div>
                                <p>In our efforts to provide the lowest prices we primarily furnish <strong>Generic Versions of most Drugs in Pill Forms.</strong></p>
                                <div class="drug-lookup">
                                    <a href="#" ><img src="https://rxdirectdev.ssasoft.com/resources/images/drugLookup.png" alt=""></a>
                                    <p>Search Our Inventory at any time</p>
                                    <p><strong>If you have a new written (paper) Rx or another current Rx Bottle</strong>-we can accept<br />
                                        a picture from our existing customers and pick up the original.</p>
                                </div>
                                <div class="modal-f clearfix">
                                    <a href="#" class="btn btn-home">Home</a>
                                    <img src="https://rxdirectdev.ssasoft.com/resources/images/take_picture_img.png">
                                </div>
                            </div>
                            <div class="modal-footer">

                            </div>
                        </div>
                    </div>
                </div>
                <!--------------------------------------------------------------------------->

                <form action="/ConsumerPortal/rxDetail/${nextOrderId}/${nextPatientId}?profile=1&status=${order.status}" method="post" id="formNextPatient" name="formNextPatient">
                    <a id="nextPatientLnk" onclick="document.getElementById('formNextPatient').submit();
                            return false;" class="btn btn_one" style="display:none"></a> 
                    <input type="hidden" id="multiProcessingOrders"
                           name="multiProcessingOrders"
                           value="${multiProcessingOrders}" />
                </form>
                </body>
                <script language="JavaScript">
                    populateArrays();
                </script>
                </html>
