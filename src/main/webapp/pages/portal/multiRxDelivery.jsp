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
                transform: rotate(90deg) translateY(-100%);
                -webkit-transform: rotate(90deg) translateY(-100%);
                -ms-transform: rotate(90deg) translateY(-100%);
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
                transform: rotate(90deg) translateY(-100%);
                -webkit-transform: rotate(90deg) translateY(-100%);
                -ms-transform: rotate(90deg) translateY(-100%);
            }
            #drugImageDiv.rotate180 #drugImg {
                transform: rotate(180deg) translate(-100%, -100%);
                -webkit-transform: rotate(180deg) translate(-100%, -100%);
                -ms-transform: rotate(180deg) translateX(-100%, -100%);
            }
            #drugImageDiv.rotate270 #drugImg {
                transform: rotate(270deg) translateX(-100%);
                -webkit-transform: rotate(270deg) translateX(-100%);
                -ms-transform: rotate(270deg) translateX(-100%);
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
        <input id="statusBit" type="hidden" value="0"/>
        <input id="multiRxScreen" type="hidden" value="1"/>
        <input id="numberOfRecordFld" type="hidden" value="${readyToDeliverOrders.totalRxReadyToDeliverOrders}"/>
        <input type="hidden" id="lowerCost" value="0" />
        <c:set var="statusBtnDisabledFlag" value="${order.status eq 'DELIVERY' || 
                                                    order.status eq 'Pickup At Pharmacy'  ||
                                                    order.status eq 'Shipped' ||    
                                                    order.status eq 'Cancelled' ?'disabled':''}"/>

        <input type="hidden" id="olversion" value="<fmt:formatDate value='${order.olversion}' pattern='yyyy-MM-dd HH:mm:ss'/>"  />

        <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
        <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.birthDate}"/>
        <c:if test="${order.dependentFlag eq true}" >
            <fmt:formatDate var="dobyear" pattern="yyyy" value="${order.patientDOB}"/>
        </c:if>
        <!--Registration Page-->
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
                        <h5>Allergies

                        </h5>
                    </div>
                    <div id="allergiesDiv" class="col-sm-5 rx_allergies padd_zero scroll" >
                        ${not empty patientProfile.allergies?patientProfile.allergies:''}
                    </div>
                </c:if>
                <jsp:include page="../inc/pharmacyQueueMenu.jsp" />
                <jsp:include page="drugestimateprice.jsp" />
                <jsp:include page="multidrugestimateprice.jsp" />
                <jsp:include page="../patientprofile/inAppNotificationMsgs.jsp" />
                <jsp:include page="../patientprofile/dependentmodal.jsp" />

                <div class="col-sm-5" style="background:#8aca11;color: white;">
                    ${order.status eq 'Shipped'?'Shipped':'Ready To Deliver'} (${readyToDeliverOrders.totalRxReadyToDeliverOrders})
                </div>
                <div class="col-sm-7" style="background:#8aca11;color: white;">
                    Date of Post: ${readyToDeliverOrders.orderDate}
                </div>
                <div id="popUpSuccess" class="success-msg" style="display:none"
                     <i class="fa fa-check" aria-hidden="true"></i><span><strong>Order(s) has been shipped successfully.</strong></span>
                </div>
                <div id="popUpException" class="error-msg" style="display:none;margin-top: 22px;">

                </div>
                <link href="${pageContext.request.contextPath}/resources/css/portal/multirx.css" rel="stylesheet" type="text/css" property="stylesheet" />
                <section>
                    <div class="bottom">
                        <div class="">
                            <div class="col-sm-12 col-md-6 col-lg-6 padd_none">
                                <h2> ORDER TO SHIP</h2>
                                <div class="min_heigt_custom">
                                    <table  class="border-box ">
                                        <thead class=" table table-bordered background-blue ">
                                            <tr>
                                                <th>Rx#.</th>
                                                <th>Date of Post</th>
                                                <th>Medications</th>
                                                <th>Qty</th>
                                                <th>Days Supply</th>
                                                <th>Delivery SVC</th>
                                                <th>Rx Cost</th>
                                                <th>Shipping Cost</th>
                                                <th>Final Payment</th> 
                                            </tr>
                                        </thead>
                                        <tbody class="table color-box">
                                            <c:forEach var="row" items="${listOfReadyToDeliverOrders}">
                                                <tr>
                                                    <td>${row.id}</td>
                                                    <td>${row.orderDate}</td>
                                                    <th class="black-box">
                                                        ${row.drugName}<br/>${row.drugType} ${row.strength}
                                                    </th>
                                                    <td>${row.qty}</td>
                                                    <td>${row.daysSupply}</td>
                                                    <td>${row.deliveryPreferencesName}</td>
                                                    <td>${row.finalPaymentStr}</td>
                                                    <td>${row.handlingFeeStr}</td>
                                                    <td>${row.totalPaymentStr}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tbody class="table">
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <th class="grand-total" style="text-transform:none">Grand Total</th>
                                                <td><span>${readyToDeliverOrders.totalRxCost} </span></td>
                                                <td><span>${readyToDeliverOrders.totalShippingCost}</span></td>
                                                <td><span>${readyToDeliverOrders.grandPayment}</span></td>
                                            </tr>
                                        </tbody>             
                                    </table>
                                </div>
                            </div>
                            <%-- SHIPPING INFO--%>
                            <div class="col-sm-12 col-md-6 col-lg-6 padding_right_none">
                                <h2> SHIPPING INFO</h2>
                                <table class="border-box text-size">
                                    <thead class="table-bordered background-blue">
                                        <tr>
                                            <th>Location</th>
                                            <td colspan="6">Address</td>
                                            <td>Zip</td>
                                            <td colspan="2">Multi Rx</td>
                                        </tr>
                                    </thead>
                                    <tbody class=" table border-box ">
                                        <tr>
                                            <td>
                                                <c:if test="${order.deliveryUrgent eq true}">
                                                    <span style="color:red"><b>!</b></span>
                                                </c:if>&nbsp; 
                                            </td>
                                            <td  colspan="6">${readyToDeliverOrders.shippingAddress}</td>
                                            <td id="zipCodeFId">${readyToDeliverOrders.zipCode}</td>
                                            <td id="multiRxCount" colspan="2">${readyToDeliverOrders.totalRxReadyToDeliverOrders}</td>
                                        </tr>
                                    </tbody>
                                    <tr class="table-bordered box-blue">
                                        <td colspan="2">Ship Date</td>
                                        <td colspan="2">Carrier</td>
                                        <td colspan="2">Tracking #.</td>
                                        <td colspan="1">Clerk</td>
                                        <td>Rx Cost</td>
                                        <td colspan="2">Shipping Cost</td>
                                    </tr>
                                    <tr class="table border-box" style="background:white !important;">
                                        <td colspan="2">
                                            <input id="datetimepicker2" ${statusBtnDisabledFlag} 
                                                   onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" class="form-control calendar_field" placeholder="mm/dd/yyyy" ${shippedFlag} value="<fmt:formatDate value='${order.shippedOn}' pattern='MM/dd/yyyy'/>">
                                        </td>
                                        <td colspan="2">
                                            <select id="deliverycarrier" style="width:100%; height:32px; border-radius:5px; border:1px solid #ccc;"  ${statusBtnDisabledFlag} onchange="window.pharmacyNotification.selectDeliveryCarrierAction('deliveryPreferences', 4)">
                                                <option value="US MAil" ${order.carrier eq 'US MAil'? 'selected':''}> US POSTAL</option>
                                                <option value="IOMNI" ${order.carrier eq 'IOMNI'? 'selected':''}>IOMNI/Hand Delivery</option>
                                                <option value="UPS" ${order.carrier eq 'UPS'? 'selected':''}>UPS</option>
                                                <option value="FEDEX" ${order.carrier eq 'FEDEX'? 'selected':''}>FEDEX</option>
                                                <option value="Pharmacy Pickup" ${order.carrier eq 'Pharmacy Pickup'? 'selected':''}>Pharmacy Pickup</option>
                                            </select>
                                        </td>
                                        <td colspan="2">  <input id="traclingno" type="text" value="${order.tracking}" class="form-control" onkeypress="return IsAlphaNumericWithHyphen(event)" ${statusBtnDisabledFlag} ></td>
                                        <td colspan="1">  <input id="clerk" class="form-control" value="${order.clerk}" ${statusBtnDisabledFlag} onkeyup="window.pharmacyNotification.setUpperCaseChar(this.id, 0);"/></td>
                                        <td id="outOfPocketFId">${readyToDeliverOrders.totalRxCost}</td>
                                        <td colspan="2" id="handlingFeeFld">${readyToDeliverOrders.totalShippingCost}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="7"></td>
                                        <th  colspan="2" style="text-transform:none;">Grand Total</th>
                                        <td colspan="1"><span id="finalPaymentCol">${readyToDeliverOrders.grandPayment} </span></td>
                                    </tr>
                                </table>
                                <div class="border-box padd_new">
                                    <input type="button" style="background: red;"
                                           onclick="if (window.pharmacyNotification.validateFinalPaymentForPaymentFailureMsg()) {
                                                       window.pharmacyNotification.setLowerCost(1);
                                                       window.pharmacyNotification.openDrugCanNitFillnDiv('medicationModal',
                                                               '', 'Payment Failure', 'Pharmacy Notification', 'Payment Failure')
                                                   }" 
                                           id="notCarried"  class="btn btn-danger button_color width_shrink_red" value="PAYMENT FAILED" ${statusBtnDisabledFlag}/>

                                    <input id="btnOrderShip" type="button" class="btn btn-info width_shrink" value="Charge payment & Ship" onclick="window.pharmacyNotification.checkStatusBit(6, 'Shipped');" ${statusBtnDisabledFlag} ${waitingPtResponseFlag}>
                                    <a href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest" class="back_qq" name="back"></a>
                                </div>
                            </div>
                            <%--END of Shipping Info--%>
                        </div>
                    </div>
                </section>

            </div>
        </div>
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/notifier.js"></script>-->


        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientDependent.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInsurancePharmacy.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/jquery.toaster.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/jquery.min.js"></script>
        <script type="text/javascript">
                                        setTimeout(function ()
                                        {
                                            window.pharmacyNotification.setIntervalAndExecute(window.pharmacyNotification.popUp, 300000);
                                        }, 300000);
                                        $('#datetimepicker2').datetimepicker({timepicker: false, format: 'm/d/Y',
                                            maxDate: 0,
                                            onChangeDateTime: function (dp, $input) {
                                                jQuery('#datetimepicker2').datetimepicker('hide');
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

                                            window.pharmacyNotification.appendCurrencySignFromNumricFlds();
                                            window.pharmacyNotification.appendFldValue('estPriceFld', 1, '$');
                                            allergiesCount();
                                            $(window).bind("load", function () {

                                                window.pharmacyNotification.selfPayEvent();
                                            })
                                        });
        </script>
        <!--Footer-->
        <jsp:include page="./inc/footer2.jsp" />
        <!--/Footer-->
        <!-- Modal Status -->
        <jsp:include page="modals.jsp" />
    </body>        
</html>
