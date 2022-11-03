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
        <jsp:include page="./inc/newRxheader2.jsp" />
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
        <input type="hidden" id="paymentFld" value="${order.paymentMode}"/>
        <input type="hidden" id="profitShareFld" value="${not empty order.profitShareCost?order.profitShareCost:'0.0'}" class="form-control isDecimalValue" onblur="calculateMFRCost(${totalDrugPrice}, this.value,${order.finalPayment},${order.handlingFee})" onkeypress="return  float_validation(event)">
        <input type="hidden" id="mfrCostFld" value="${not empty order.mfrCost?order.mfrCost:'0.0'}" class="form-control isDecimalValue" onblur="calculateMFRCost(${totalDrugPrice}, this.value,${order.finalPayment},${order.handlingFee})" onkeypress="return  float_validation(event)">
        <input type="hidden" id="profitSharePoints" value="0" >
        <input type="hidden" id="profitMarginCol" value="${not empty order.profitMargin?order.profitMargin:'0.0'}"/>

        <!--
         <input type="hidden" id="totalRedeemPointsId" value="${order.profitSharePoint}"/>
         <input type="hidden" id="redeemPointsCostFld" value="${order.actualProfitShare}"/>-->
        <input type="hidden" id="olversion" value="<fmt:formatDate value='${order.olversion}' pattern='yyyy-MM-dd HH:mm:ss'/>"  />

        <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
        <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.birthDate}"/>
        <!--Registration Page-->

        <div class="">
            <div class="col-md-12"> 
                <div class="tableContainer patient_info row">
                    <div class="col-sm-6 patient_name padd_zero">
                        <c:choose>
                            <c:when test="${dependentFlag eq false}">
                                <h3>${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(patientProfile.lastName)} &nbsp;<span><fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.birthDate}"/> <span>(${patientProfile.gender})  ${nowyear-dobyear} yrs</span></span></h3>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate var="dobdependentyear" pattern="yyyy" value="${dependentObject.birthDate}"/>
                                <h3>${fn:toUpperCase(dependentObject.firstName)}&nbsp; ${fn:toUpperCase(dependentObject.lastName)} &nbsp;<span><fmt:formatDate pattern="MM/dd/yyyy" value="${dependentObject.birthDate}"/> <span>(${dependentObject.gender})  ${nowyear-dobdependentyear} yrs

                                            <c:choose>
                                                <c:when test="${adultFlag eq false}">
                                                    <span style="color:blue">&nbsp;MINOR - ${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(patientProfile.lastName)} </span>

                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color:blue">&nbsp;ADULT - ${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(patientProfile.lastName)} </span>
                                                </c:otherwise>
                                            </c:choose>

                                        </span></span></h3>
                                    </c:otherwise>
                                </c:choose>
                    </div>
                    <c:if test="${(sessionBeanPortal.pmap[(79).intValue()].view eq true || sessionBeanPortal.pmap[(79).intValue()].manage eq true)}">
                        <div class="col-sm-1 rx_labe padd_zero">
                            <h5>Allergies</h5>
                        </div>
                        <div class="col-sm-5 rx_allergies padd_zero scroll">
                            ${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}
                        </div>
                    </c:if>
                    <jsp:include page="../inc/pharmacyQueueMenu.jsp" />
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
                        <c:set var="isOrderTypeRefillOrTransfer" value="${order.orderType eq 'Transfer' || order.orderType eq 'Refill' ? '':'disabled' }" />
                        <c:set var="fieldsDisabledFlag2" value="${order.requiresDeletion eq true||(order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)}" />
                        <c:set var="fieldsDisabledFlag" value="${order.requiresDeletion eq true || order.status eq 'DELIVERY' || order.status eq 'Ready to Fill' || order.status eq 'Filled'|| 
                                                                 order.status eq 'Waiting payment authorization'|| order.status eq 'Payment Authorized' ||    order.status eq 'Shipped'
                                                                 || order.status eq 'Pickup At Pharmacy' || order.status eq 'Cancelled'
                                                                 || (order.status eq 'Waiting Pt Response' && order.disabledFld eq 1)  ?'disabled':''}"/>
                        <c:set var="shippedFlag" value="${order.status ne 'Filled' ?'disabled':''}"/>
                        <c:set var="orderStatusPendingDisabledButtons" value="${order.status eq 'Pending' || order.status eq 'Unverified Image' ?'disabled':''}" />
                        <c:set var="shippedBtnFlag" value="${order.status ne 'Filled' ?'disabled':''}"/>
                        <c:set var="filledBtnFlag" value="${order.status eq 'Fill Request Accepted'  ?'':'disabled'}"/>
                        <c:set var="readyFilledBtnFlag" value="${order.status eq 'Pending' || order.status eq 'Ready to Fill' || order.status eq 'Ready to Fill' || order.status eq 'Pending Pharmacy Reply' || order.status eq 'Filled' || order.status eq 'DELIVERY' ||order.status eq 'Shipped'  ||    order.status eq 'Cancelled' ||order.status eq 'Waiting payment authorization' ||order.status eq 'Ready to Fill' ||order.status eq 'Fill Request Accepted'?'disabled':''}"/>
                        <c:set var="processingBtnFlag" value="${order.status eq 'Filled' || order.status eq 'DELIVERY' || order.status eq 'Shipped' || order.status eq 'Cancelled' || order.status eq 'Processing' ||order.status eq 'Waiting payment authorization' ||order.status eq 'Ready to Fill' ||order.status eq 'Fill Request Accepted' ||order.status eq 'Payment Authorized'?'disabled':''}"/>
                        <c:set var="pickUpFromPharmacyFlag" value="${order.status eq 'Pickup At Pharmacy' ?'disabled':''}"/>
                        <c:set var="mandatoryFldsSign" value="${order.drugIncluded eq true && order.status ne 'Pending'?'<span style=\"color:red\"><b>*</b></span>':''}" />



                        <div class="col-md-12"> 
                            <div class="transfer_wrap clearfix">
                                <div class="col-sm-12 col-lg-8 set_table">
                                    <div class="order_details_h clearfix" style="display:none">
                                        <h2> 
                                            Status of order:<span id="orderStatusSpan"> ${order.status}</span>
                                        </h2>
                                        <input type="hidden"  id="dependentId" value="${dependentId}" />
                                        <small>DATE OF POST: <fmt:formatDate value='${order.receivedDate}' pattern='yyyy-MM-dd'/> @ <strong><fmt:formatDate value='${order.receivedDate}' pattern='hh:mm:ss'/></strong></small>
                                    </div>
                                    <div id="full_video_container" class="full_video_container" style="display: none;"> </div>
                                    <fmt:formatNumber var="totalDrugPrice" value="${order.totalDrugPrice}" pattern="0.00"/>
                                    <input id="estimatedDrugPrice" type="hidden" value="${totalDrugPrice}"/>
                                    <div class="rx_detail_table col-sm-12 new_tables">
                                        <table class="dt_tables" style="display:none">
                                            <tbody>
                                                <tr>
                                                    <th>Control&nbsp;#</th>
                                                    <th>REQ&nbsp;RECV�D</th>
                                                    <th>Current&nbsp;MBR&nbsp;Request</th>

                                                </tr>
                                            <input type="hidden" id="orderTypeFld" value="${order.orderType}" >

                                            <tr>
                                                <td><input class="form-control" placeholder="" value="RXD-${order.orderId}" name="" readonly/></td>
                                                <td>
                                                    <fmt:formatDate var="receivedDate" value='${order.receivedDate}' pattern='yyyy-MM-dd @ HH:mm'/>
                                                    <input class="form-control" placeholder="" name="" value="${receivedDate}" readonly/>
                                                </td>
                                                <td class="label_td"><input class="form-control" placeholder="" value="${order.requestType}" name="" disabled />

                                                </td>

                                            </tr>

                                            </tbody>
                                        </table>

                                        <table class="dt_tables">
                                            <tbody>
                                                <tr> 
                                                    <th class="size_length" >Original&nbsp;Date${mandatoryFldsSign}</th>
                                                    <th>RX&nbsp;Name</th>
                                                    <th class="strength_cntrol">Strength</th>
                                                    <th>Dosage&nbsp;Type</th>
                                                    <th class="size_cntrol">QTY${mandatoryFldsSign}</th>
                                                    <th class="size_cntrol">Days<br />Supply${mandatoryFldsSign}</th>
                                                    <th class="size_cntrol">Refills<br />Allowed</th>
                                                    <th class="size_cntrol">Refills<br />Remain</th>
                                                </tr>
                                                <tr> 
                                                    <!--   <td><input type="text" id="patientNameFld" value="Riaz  Ahmad" class="form-control" /></td>-->
                                                    <td class="date_width">
                                                        <input id="datetimepicker" class="form-control calendar_field" placeholder="mm/dd/yyyy" ${fieldsDisabledFlag}
                                                               onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"
                                                               value="<fmt:formatDate value='${order.rxDate}' pattern='MM/dd/yyyy'/>"  ${ManagePermissionUpdateAllRequestFields} />
                                                    </td>
                                                    <td>
                                                        <input id="drugNameIdDetail" type="hidden" value="${order.drugNameWithoutStrength}" ${ManagePermissionUpdateAllRequestFields}/>
                                                        <input type="text" id="referenceBrandDetail" name="" value="${order.drugNameWithoutStrength}" class="form-control strengh_fie" 
                                                               onblur="needToConfirm = false;
                                                                       window.drugestimateprice.populateDrugDosageAndStrengthDetail('referenceBrandDetail',
                                                                               'drugNameIdDetail', 'drugStrengthDetail', 'drugTypeIdDetail');
                                                                       window.pharmacyNotification.validateImageVerifiedFields();" 
                                                               oninput="window.transferRequest.lookUpObject('referenceBrandDetail','${pageContext.request.contextPath}/ConsumerPortal/populateDrugDetailByBrandOrGenericName')" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>

                                                    </td>
                                                    <td>
                                                        <select id="drugStrengthDetail" class="form-control strengh_fi" onblur="window.transferRequest.populateQtyByStrengthAndDrugNameDetail('drugQtyFld', 'referenceBrandDetail', 'drugStrengthDetail', 'drugTypeIdDetail');" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>
                                                            <option value="${order.strength}">${order.strength}<option>
                                                        </select>

                                                    </td>
                                                    <td>
                                                        <select id="drugTypeIdDetail" onchange="checkSelectedValue('drugTypeId')"
                                                                onblur="needToConfirm = false;
                                                                        window.transferRequest.lookUpDrugStrengthByBrandNameAndTypeDetail('referenceBrandDetail',
                                                                                'drugStrengthDetail', 'drugTypeIdDetail');" 
                                                                class="form-control strengh_fi" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>
                                                            <option value="${order.drugType}">${order.drugType}</option>

                                                        </select>
                                                    </td>
                                                    <td class="size_define">
                                                        <input type="text" id="drugQtyFld" value="${order.quantity}" class="form-control qty" onchange="window.pharmacyNotification.validateImageVerifiedFields();"
                                                               onblur="needToConfirm = false;
                                                                       window.transferRequest.lookUpDrugCostDetail('drugNameIdDetail',
                                                                               'referenceBrandDetail',
                                                                               'drugStrengthDetail',
                                                                               'drugTypeIdDetail');
                                                                       window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');" onkeypress="return IsDigit(event)"
                                                               ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="5">
                                                    </td>
                                                    <td class="size_define">
                                                        <input type="text" id="daysSupplyFld" value="${not empty order.daysSupply?order.daysSupply:''}" class="form-control center_fiel center_field" 
                                                               onkeypress="return IsDigit(event)" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="4">

                                                    </td>
                                                    <td class="size_define">
                                                        <input type="text" id="refillAllowedFld" value="${order.refillsAllowed}" class="form-control center_fiel center_field" onkeypress="return IsDigit(event)" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="3">
                                                    </td>
                                                    <td class="size_define">
                                                        <input type="text" id="refillUsedFld" value="${order.refillsRemaining}" class="form-control center_fiel center_field" onkeypress="return IsDigit(event)" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields} maxlength="3">
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table></div>





                                    <div class="col-sm-12  pharmacy_tables new_tables">
                                        <div class="table_col_left col-sm-6">
                                            <h6><span>Original Pharmacy</span></h6>
                                            <table class="dt_tables">
                                                <tbody>
                                                    <tr> 
                                                        <th class="size_fix">Rx&nbsp;#</th>
                                                        <th>Name</th>
                                                        <th>Phone</th>
                                                    </tr> 
                                                    <tr> 
                                                        <td>
                                                            <input type="text" id="pharmacyRxNoFld" value="${order.oldRxNo}" class="form-control center_fiel" ${fieldsDisabledFlag} maxlength="9" onkeypress="return IsAlphaNumericWithHyphen(event)" >
                                                        </td>
                                                        <td><input type="text" id="pharmacyNameFld"   value="${not empty order.pharmacyName?order.pharmacyName:''}" class="form-control center_fiel" ${fieldsDisabledFlag} maxlength="50"></td>
                                                        <td>
                                                            <input type="text" id="pharmacyPhoneFld" value="${not empty order.pharmacyPhone?order.pharmacyPhone:''}" class="form-control center_fiel" 
                                                                   onkeypress="return IsDigitWithHyphen(event)" maxlength="20" ${fieldsDisabledFlag}>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="table_col_right col-sm-6 margin_none">
                                            <h6><span>Prescriber</span></h6>
                                            <table class="dt_tables">
                                                <tbody>
                                                    <tr> 
                                                        <th>Last&nbsp;Name</th>
                                                        <th class="size_fixed">First&nbsp;Name</th>
                                                        <th class="fixed_size">Phone</th>
                                                    </tr>
                                                    <tr> 
                                                        <td>
                                                            <input type="text" id="prescriberLastNameFld" value="${not empty order.prescriberLastName?order.prescriberLastName:''}" class="form-control  center_fiel" 
                                                                   onkeypress="return onlyAlphabets(event)" ${fieldsDisabledFlag}  ${ManagePermissionUpdateAllRequestFields} maxlength="50">
                                                        </td>
                                                        <td>
                                                            <input type="text" id="prescriberNameFld" value="${not empty order.prescriberName?order.prescriberName:''}" class="max_width_new_firstname form-control fld_cntrl center_fiel" ${fieldsDisabledFlag} size="10" maxlength="20" onkeypress="return onlyAlphabets(event)"   ${ManagePermissionUpdateAllRequestFields}>
                                                        </td>

                                                        <td>
                                                            <input type="text" id="prescriberPhoneFld" value="${not empty order.prescriberPhone?order.prescriberPhone:''}" class="form-control center_fiel" 
                                                                   onkeypress="return IsDigitWithHyphen(event)" maxlength="20" ${fieldsDisabledFlag} ${ManagePermissionUpdateAllRequestFields}>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>


                                        <!----------------------------------------------------------------->
                                        <div class="col-sm-2 table_price">
                                            <h3>Insurance<span style="color:red"><b>*</b></span></h3>
                                            <div class="table_col_public ">

                                                <table class="dt_tables new_tables">
                                                    <tbody>
                                                        <tr>
                                                            <th>Private</th>
                                                            <th>Public</th> 
                                                            <th>Self Pay</th>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <div class="checkbox_style">
                                                                    <input type="radio" id="INS" name="selector" 
                                                                           onclick="window.pharmacyNotification.selfPayEvent()" value="Commercial" ${fieldsDisabledFlag} ${order.insuranceCheckCommercial} ${ManagePermissionUpdateAllRequestFields}>
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
                                                                    <input type="radio" id="radio-b" name="selector" value="Cash" ${order.selfPayCheck} ${ManagePermissionUpdateAllRequestFields} ${fieldsDisabledFlag}>
                                                                    <label for="radio-b">Pay</label>
                                                                    <div class="check"></div>
                                                                </div>
                                                            </td>
                                                        </tr>

                                                    </tbody>

                                                </table>
                                            </div>
                                        </div>
                                        <div class="table_slef_pay col-sm-2 padd-zero">
                                            <table class="dt_tables new_table deliver_new">
                                                <tbody>
                                                    <tr>
                                                        <th>Delivery Preference<span style="color:red"><b>*</b></span></th> 
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <select name="pref" id="pref" class="deliver_green" style="color: black;">
                                                                <option value="-1">----Please Select---</option>
                                                                <option value="1">Same Day</option>
                                                                <option value="2" >Next Day</option>
                                                                <option value=3">2nd Day</option>
                                                                <option value=4">Pick Up at Pharmacy</option>    
                                                            </select> 
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="table_col_full table_col_custom table_col_cstm col-sm-7 margin_none pad_new_comp">
                                            <h3>Compliance Rewards&trade;</h3>

                                            <table class="dt_tables table_width back_color">
                                                <tbody>
                                                    <tr> 
                                                        <th class="">Rx&nbsp;Cost $</th>
                                                        <th>MBR&nbsp;Outof PCKT $</th>
                                                        <th>3rd&nbsp;PartyPay</th>

                                                        <th class="lightGreen">Reward
                                                            Points</th>
                                                        <th class="lightGreen">Reward Saved</th>

                                                        <th>Collect </th>

                                                    </tr>

                                                    <tr> 
                                                        <td>
                                                            <input type="text" id="acqCostFld" value="$${order.rxAcqCost}" class="form-control set_inputfield isDecimalValue text_right" onkeypress="return  float_validation(event, this.value);"
                                                                   onblur="window.pharmacyNotification.addCommas('acqCostFld');"     ${fieldsDisabledFlag} ${ManagePermissionUpdateComplainceRewards}>
                                                        </td>
                                                        <td>
                                                            <input type="text" id="ptCopayFld" value="$${order.originalPtCopay}" class="form-control set_inputfield isDecimalValue text_right" 
                                                                   onblur="needToConfirm = false;
                                                                           window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');
                                                                           window.transferRequest.calculatePayment();
                                                                           window.pharmacyNotification.addCommas('ptCopayFld');" 
                                                                   onkeypress="return  float_validation(event, this.value)" ${fieldsDisabledFlag} ${ManagePermissionUpdateComplainceRewards}>
                                                        </td>
                                                        <td>
                                                            <input type="text" id="reimbFld" value="$${order.rxReimbCost}" class="form-control set_inputfield isDecimalValue text_right" 
                                                                   onblur="needToConfirm = false;
                                                                           window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');
                                                                           window.transferRequest.calculatePayment();
                                                                           window.pharmacyNotification.addCommas('reimbFld');" 
                                                                   onkeypress="return  float_validation(event, this.value)" ${fieldsDisabledFlag}  ${ManagePermissionUpdateComplainceRewards}>
                                                        </td>
                                                <input type="hidden" id="sellingPriceFld" value="$${totalDrugPrice}" />

                                                <td><input type="text" id="totalRedeemPointsId"  value="${order.profitSharePoint}" ${fieldsDisabledFlag}
                                                           class="form-control set_reward isDecimalValue text_right" onkeypress="return  float_validation(event, this.value)" > 
                                                </td>
                                                <td>
                                                    <input type="text" id="redeemPointsCostFld"  class="form-control set_field isDecimalValue text_right" ${fieldsDisabledFlag}
                                                           value="$${order.actualProfitShare}" 
                                                           onkeypress="return  float_validation(event)" onblur="window.pharmacyNotification.addCommas('redeemPointsCostFld');">
                                                </td>

                                                <td><input type="text" id="finalPaymentFeeFld" value="$${order.paymentExcludingDelivery}" class="form-control set_collect isDecimalValue text_right" onkeypress="return  float_validation(event, this.value)" onblur="window.pharmacyNotification.addCommas('finalPaymentFeeFld');"  ${ManagePermissionUpdateComplainceRewards} disabled="true"></td>


                                                </tr>
                                                </tbody>
                                            </table>

                                            <input type="hidden" id="ptOverridePriceFld" value="${order.ptOverridePrice eq true ? 1:0}"/>
                                            <input type="hidden" id="rxScanFld" value="${order.rxScan eq true ? 1:0}"/>
                                            <input type="hidden" id="labelScanFld" value="${order.labelScan eq true ? 1:0}"/>
                                        </div>

                                        <div class="col-sm-1 pt_btn ptn_new ne_width_pt_button">
                                            <input type="button" id="ptOverRideBtn" value="" class="btn btn_pt cancelbt" onclick="window.pharmacyNotification.ptOverrideEvent()" ${fieldsDisabledFlag}>
                                        </div>


                                        <input type="hidden" id="paymentExcludingDeliveryHidden" value="${order.paymentExcludingDelivery}" />                    
                                        <!----------------------------------------------------------------->


                                        <div id="statusException"></div>
                                        <div class="col-sm-12 table_col_full">


                                        </div>


                                    </div>




                                    <div class="type_comments custom_buttons col-sm-12 col-lg-3">

    <!-- <textarea id="orderComments" class="form-control" placeholder="Type Comment">${not empty order.comments?order.comments:''}</textarea>

<div class="type_comment col-md-3 col-sm-3">

<textarea id="orderComments" class="form-control" placeholder="Type Comment">${not empty order.comments?order.comments:''}</textarea>


</div>                                   -->





                                        <jsp:include page="../patientprofile/inAppNotificationMsgs.jsp" />



                                        <div class="col-sm-8 top10_padding"></div>
                                        <div class="col-sm-4 top10_padding">

                                        </div>

                                    </div>





                                    <div class="col-sm-12 col-lg-4 rightvideo pull-right">

                                        <!--                                    <li ><a data-toggle="tab" href="#mbrDialogTab">MBR DIALOG LOG</a></li>
                                                                            <ul class="nav nav-tabs">
                                                                                
                                        <c:if test="${order.status eq 'Filled' }">
                                        <li><a data-toggle="tab" href="#shipping">SHIPPING INFO</a></li>
                                        </c:if>
                                </ul>-->
                                        <div class="tables_buttons table_btn pul_right_button">
                                            <form action="${pageContext.request.contextPath}/ConsumerPortal/rxDetail/${orderId}/${patientId}?success=1&profile=1&status=${order.status}" method="post" id="form" name="form">
                                                <a id="backLnk" onclick="document.getElementById('form').submit();
                                                                return false;" class="btn btn_one" style="display:none"></a> 

                                                <c:choose>
                                                    <c:when test="${pageContext.request.getParameter('status') == null || pageContext.request.getParameter('success') != null}">
                                                        <input type="button" id="cancel" name="cancel" value="Cancel" class="btn btn_dtail btn_cstm cancelbt" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest/'">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="button" id="cancel" name="cancel" value="Cancel" class="btn btn_dtail btn_cstm cancelbt" onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/queuePatientDetailPage/${patientId}/${sessionBeanPortal.selectedTab}?fromDate=${pageContext.request.getParameter('fromDate')}&toDate=${pageContext.request.getParameter('toDate')}&status=${pageContext.request.getParameter('status')}'">
                                                    </c:otherwise>
                                                </c:choose>
                                                <input type="button" id="saveBtn" name="save" value="Save" class="btn btn_dtail btn_cstm pull-right" onclick="needToConfirm = false;
                                                        window.pharmacyNotification.newOnlineOrder(0, '');">    

                                            </form>                  
                                        </div>

                                        <div class="tab-content" >


                                            <!---------------------------------------------------------------------------->
                                            <c:if test="${ManagePermissionUpdateClerk == true}">
                                                <div id="shipping" class="tab-pane fade video_div_two">
                                                    <h4>SHIPPING INFO</h4>
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>Delivery Address</th>
                                                                <th>Zip Code</th>
                                                                <th>Delivery Requested</th>
                                                                <th>Fee Collected</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>${patientProfile.defaultAddress}</td>
                                                                <td>76092</td>
                                                                <td class="redText">${order.deliveryService}</td>
                                                                <td>${order.handlingFee}</td>
                                                            </tr>
                                                            <tr>
                                                        </tbody>
                                                    </table>
                                                    <table class="table table-two">
                                                        <thead>
                                                            <tr>
                                                                <th>Multi Rx</th>
                                                                <th>Carrier</th>
                                                                <th>Tracking #</th>
                                                                <th>Clerk</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" id="multiRxC" onclick="window.pharmacyNotification.multiRxOrder();">
                                                                </td>
                                                                <td>   <select id="deliverycarrier" style="min-width:50px;"  ${shippedFlag}>
                                                                        <option value="US MAil" ${order.carrier eq 'US MAil'? 'selected':''}> US MAIL</option>
                                                                        <option value="UPS" ${order.carrier eq 'UPS'? 'selected':''}>UPS</option>
                                                                        <option value="FEDEX" ${order.carrier eq 'FEDEX'? 'selected':''}>FEDEX</option>
                                                                        <option value="Hand Delivery" ${order.carrier eq 'Hand Delivery'? 'selected':''}> Hand Delivery</option>
                                                                    </select></td>
                                                                <td><input id="traclingno" type="text" value="${order.tracking}" class="form-control" onkeypress="return IsAlphaNumericWithHyphen(event)" ${shippedFlag}></td>
                                                                <td><input id="clerk" class="form-control" value="${order.clerk}" ${shippedFlag}></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                    <div id="show_div">
                                                        <table class="table table-three">
                                                            <thead>
                                                                <tr>
                                                                    <th><input type="checkbox" id="thMultiRxCB" onclick="selectAllMutliRx()" ></th>
                                                                    <th>Control No.</th>
                                                                    <th>Rx Processed</th>
                                                                    <th>Service Req.</th>
                                                                    <th>Delivery Fee</th>

                                                                </tr>
                                                            </thead>
                                                            <tbody id="shippingInfoTB">
                                                                <!--      <tr>
                                                                      <td><input type="checkbox" ></td>
                                                                      <td>RXD-201704113</td>
                                                                      <td>TYKERB**BRAND</td>
                                                                      <td>2nd Day*</td>
                                                                      <td>$20:00</td>
                                                                        </tr>
                                                                      <tr>
                                                                      <td><input type="checkbox" ></td>
                                                                      <td>RXD-201704113</td>
                                                                      <td>TYKERB**BRAND</td>
                                                                      <td>2nd Day*</td>
                                                                      <td>$20:00</td>
                                                                        </tr>
                                                                      <tr>
                                                                      <td><input type="checkbox" ></td>
                                                                      <td>RXD-201704113</td>
                                                                      <td>TYKERB**BRAND</td>
                                                                      <td>2nd Day*</td>
                                                                      <td>$20:00</td>
                                                                        </tr>-->
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <input type="button" id="pickFromPharmacyLnk" class="btn btn-blue1" value="Picked from pharmacy"
                                                           onclick="window.pharmacyNotification.checkOtherStatusBitHandler(15, 'Pickup At Pharmacy');" ${statusBtnDisabledFlag} ${deliveryPreferecePharmacyDisabledFlag}>
                                                    <input type="button" id="shippedLnk" class="btn btn-blue1" value="Shipped" 
                                                           onclick="window.pharmacyNotification.checkOtherStatusBitHandler(6, 'Shipped');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag} >
                                                    <input type="button" id="courierLnk" class="btn btn-blue1" value="Delivery" 
                                                           onclick="window.pharmacyNotification.checkOtherStatusBitHandler(5, 'DELIVERY');" ${statusBtnDisabledFlag} ${deliveryPrefereceDeliveryDisabledFlag}>
                                                </div>
                                            </c:if>
                                            <!---------------------------------------------------------------------------->
                                            <!------------------------------------------------------------->
                                            <!--                                        <div id="mbrDialogTab" class="tab-pane fade video_div_one in active" style="min-height: 530px;">
                                                                                    <div class="patient_coment_list" style="min-height: 530px;">
                                                                                        <h2><i class="fa fa-comments" aria-hidden="true"></i> MBR Dialog Log</h2>
                                            
                                            <c:forEach var="question" items="${questions}" >
                                                <p>${question.question}
                                                    <br>(${question.questionTimeStr})
                                                </p>
                                            </c:forEach>

                                            <div class="textBox detail_comment">

                                                <i class="fa fa-paperclip fileUpload" title="upload file" onclick="window.pharmacyNotification.uploadFile()"></i>
                                                <input id="attachFile" type="file" style="display: none;" accept="image/*" onchange="window.pharmacyNotification.validateFileFormat();"/>
                                                <textarea id="messageId" name="pharmacyNote" class="messageBox" style="min-height: 400px;" onclick="window.pharmacyNotification.resetMessageFn();" placeholder="Send Message to Patient"></textarea>
                                                <button type="button" onclick="window.pharmacyNotification.savePharmacyNotificationFn();" ><i class="fa fa-paper-plane greenText"></i></button>
                                                <a href="#" class="greenText messageHistory pull-left" onclick="displayModal(${order.orderId}, 'historyModal', 'viewHistoryMessageWs')">View Message History</a>
                                                <div id="errorSendMessage" class="pull-right"></div>
                                            </div>
                                        </div>
                                        </div>-->
                                            <!------------------------------------------------------------->

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
                <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
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
                                                               $(document).ready(function (e) {
                                                                   window.pharmacyNotification.populateAppUrl('${pageContext.request.contextPath}');
                                                                   window.drugestimateprice.populateAppUrl('${pageContext.request.contextPath}');
                                                                   window.transferRequest.populateAppUrl('${pageContext.request.contextPath}');
                                                                   window.patientInfoDependant.populateAppUrl('${pageContext.request.contextPath}');
                                                                   window.patientInfoInsurance.populateAppUrl('${pageContext.request.contextPath}');
                                                                   
                                                               });
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
                    //                    $(document).ready(function (e) {
                    //                $(".buttonss").click(function () {
                    //
                    //                    $(".logDrop").slideToggle();
                    //                })
                    //            });
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

                </script>
                <script>
//                    $('.modal').modal({keyboard: false,
//                        show: true
//                    });
//                    Jquery
//                    draggable
//                            $('.modal-dialog').draggable({
//                        handle: ".modal-header"
//                    });

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
