<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<meta name="SKYPE_TOOLBAR" content ="SKYPE_TOOLBAR_PARSER_COMPATIBLE"/>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div class="container contentsContainer">
            <h2 class="pageTitle">PATIENT RESPONSE QUEUE</h2>
            <hr class="titleBorder"> 
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
                    <c:forEach var="pc" items="${pageContents}">
                        <c:set var="dcTitle" value="${pc.cMSPages.title}"/>
                        <c:if test="${fn:containsIgnoreCase(dcTitle, 'Prescription Queue – Content')}">
                            <p style="text-align: center;">${pc.content}</p>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="">
                <div class="col-lg-6 col-sm-12 col-md-12 col-xs-12 margin-top-18 left-devs">
                    <input id="cmd" type="hidden" value="${order.dailyRedemption.communicationMethod}">
                    <input id="orderId" type="hidden" value="${order.id}">
                    <c:choose>
                        <c:when test="${not empty order.rxNo}">
                            <div id="orderDetail" class="overviewContainer">
                                <div class="col-lg-6 col-sm-12 col-md-12 col-xs-12 margin-top-10" style="padding:0px 5px;background:white;float:left;">
                                    <span class="innermessage2">Order #:${order.id}</span>
                                    <div id="patientInfo" class="contentBox" style="min-height: 182px;overflow: visible;margin-top: 4px;">
                                        <div class="boxTitle">Patient Information:</div>
                                        <span style="width:100%;float:left;color: #2169b3;font-weight: bold;">${order.firstName} ${order.lastName} <span style="float:right;color: #2169b3;font-weight: normal;">${order.dailyRedemption.cardholderGender eq 'F'.charAt(0)?'Female':'Male'} <fmt:formatDate value="${order.dailyRedemption.cardholderDob}" pattern="MM/dd/YYYY" var="prdob"/>(${prdob})</span></span>
                                        <span style="width:100%;float:left;color: #2169b3;"><i class="fa fa-envelope" style="color:#3f9614;display: ${order.dailyRedemption.communicationMethod eq 'E'?'inline':'none'}"></i><i class="fa fa-phone" style="color:white;background: #3f9614;padding: 1px 2px; display: ${order.dailyRedemption.communicationMethod eq 'T'?'inline':'none'}"></i>
                                            <c:if test="${order.dailyRedemption.communicationMethod eq 'T'}">${fn:substring(order.dailyRedemption.communicationId, 0, 3)}-${fn:substring(order.dailyRedemption.communicationId,3,6)}-${fn:substring(order.dailyRedemption.communicationId,6,10)}</c:if>
                                            <c:if test="${order.dailyRedemption.communicationMethod eq 'E'}">${order.dailyRedemption.communicationId}</c:if>
                                            <span style="width:100%;float:left;color: #2169b3;">${order.streetAddress}, ${order.city}, ${order.state} ${order.zip}</span>
                                            <span style="width:100%;float:left;padding-top: 6px;">Refill Remaining: <fmt:formatNumber value="${order.dailyRedemption.refillsAllowed - order.dailyRedemption.refillsUsed}"/> <span style="float:right;position: relative;bottom: 6px;">Ingredients ${order.itemsOrder} &nbsp;<i style="color:#1f4c76;font-size: 22px;font-weight:bold;cursor: pointer" class="fa fa-eye" onclick="showIngredientsPopup('${order.transactionNo}', 'Ingredients');"></i></span></span>
                                            <span style="width:100%;float:left;color: #db1a1b;font-size: 14px;padding-top: 5px;font-weight: bold;">Prescriber :<span style="color: #db1a1b;padding-top: 5px;">${order.dailyRedemption.prescriberFirstName} ${order.dailyRedemption.prescriberLastName}</span></span>
                                            <span id="prescriberPhone" style="width:100%;float:left;color: #db1a1b;"><i class="fa fa-phone faphone"></i> 
                                                123-123-1234
                                            </span>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-sm-12 col-md-12 col-xs-12 margin-top-10" style="padding:0px 5px;float:left;">
                                    <span class="innermessage2">PTACCEPTEDASSISTANCE & PAID</span>
                                    <div id="ptAccept" class="contentBox" style="overflow: visible;margin-top: 4px;">
                                        <div class="boxTitle">API Drug Rebate Information:</div>
                                        <span style="width:100%;float:left;">Original Claim Submitted  <span style="float:right;">$<fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.pharmacyGrossAmount}"/></span></span>
                                        <span style="width:100%;float:left;">Insurance Reimbursement  <span style="float:right;">$<fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.planPharmacyAmount}"/></span></span>
                                        <span style="width:100%;float:left;">Patient Out of Pocket  <span style="float:right;">$<fmt:formatNumber pattern="0.00" value="${order.dailyRedemption.ptOutOfPocket}"/></span></span>
                                        <span style="width:100%;float:left;color:#1f6ab1;">API Copay Assistance  <span style="float:right;color:#1f6ab1;"><fmt:formatNumber pattern="0.00" value="${order.copay}"/></span></span>
                                        <span style="width:100%;float:left;font-size: 14px;font-weight:bold;">Final Payment *  <span style="float:right;font-size: 14px;font-weight:bold;">$<c:set var="finalPay" value="${order.dailyRedemption.ptOutOfPocket - order.copay}" /><fmt:formatNumber pattern="0.00" value="${finalPay}"/></span></span>
                                        <span style="color:#1f4c76;font-size: 11px;">* Payment Confirmation HGFA858, 5/26/2015 @11:30PM</span>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding:0px 5px;margin-top: 5px;float:left;">
                                    <div class="contentBox" style="height: 80px;overflow: auto;">
                                        <div class="boxTitle">Order Status:</div>
                                        <c:forEach var="oh" items="${order.orderHistory}">
                                            <span style="${oh.orderStatus.id eq 3?'color:#ea940e;':oh.orderStatus.id eq 4?'color:#65af3d;':''} width:100%;float:left;"><fmt:formatDate value="${oh.createdOn}" pattern="MM/dd/YYYY hh:mm a" var="orderplacedate"/>${orderplacedate} ${oh.orderStatus.id eq 1?'Placed':oh.orderStatus.name} <c:if test="${oh.orderStatus.id eq 4}">${order.orderCarriers.name}(${order.orderTrackingNo})</c:if> </span>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding-left: 0px; padding-right: 0px; float:left;">  
                                    <div style="color: #dc1c1b;padding-left: 5px; position: relative;top:4px;font-size: 14px;font-weight: bold;">COMMENTS to PATIENT & SHIPPMENT TRACKING</div>
                                    <form:form action="${pageContext.request.contextPath}/PharmacyPortal/prescription/${order.id}" commandName="order" method="post" onsubmit="return validateField()">
                                        <div class="col-sm-5" style="padding-left: 5px;padding-right: 5px;margin-top: 8px;">
                                            <form:select id="tracking" path="orderStatus.id" cssClass="form-control" style="padding:5px 10px;height:31px;font-size: 13px;" disabled="${order.orderStatus.id eq 4 || order.orderStatus.id == 1 || order.orderStatus.id eq 5}" onchange="disableEnableTrackNo(this.value)">
                                                <form:option value="0" label="Order status" />
                                                <form:options items="${filterOrderStatus}" itemValue="id" itemLabel="name"/>
                                            </form:select>
                                            <input type="hidden" id="statusHidden" value="${selectedOrderstatus ne null?selectedOrderstatus:0}"/>
                                        </div>
                                        <div class="col-sm-7" style="padding-left: 5px;padding-right: 5px;">
                                            <div class="col-sm-6">
                                                <form:input id="trackNo" path="orderTrackingNo" cssClass="form-control" cssStyle="padding:5px 10px;height:31px;margin-top: 8px;font-size: 13px;" placeholder="Tracking #" disabled="${selectedOrderstatus ne 4}" value="${orderTrackingNo}" maxlength="10" />
                                            </div>
                                            <div class="col-sm-6" style="padding-right: 0px;margin-top: 8px;">
                                                <form:select id="orderCarriers" path="orderCarriers.id" cssClass="form-control" style="padding:5px 10px;height:31px;font-size: 13px;" disabled="${selectedOrderstatus ne 4}">
                                                    <form:option value="0" label="Orders Carriers" />
                                                    <form:options items="${orderCarriersList}" itemValue="id" itemLabel="name"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding-left: 5px;padding-right: 5px;margin-top: 8px;float: left;">
                                            <c:choose>
                                                <c:when test="${order.orderStatus.id eq 5}">
                                                    <form:input id="comments" path="orderHistory[${fn:length(order.orderHistory)-1}].comments" cssClass="form-control" disabled="${order.orderStatus.id eq 4 || order.orderStatus.id == 1 || order.orderStatus.id eq 5}" style="font-size: 13px;"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <form:input id="comments" path="orderHistory[2].comments" cssClass="form-control" disabled="${order.orderStatus.id eq 4 || order.orderStatus.id == 1 || order.orderStatus.id eq 5}" style="font-size: 13px;" placeholder="Comments"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                        <div class="col-sm-12 text-center-mobile" style="padding-left: 7px;">
                                            <label id="orderMessage" class="${not empty message?'message':'error'}">${not empty message?message:errorMessage}</label>
                                        </div>
                                        <div class="col-sm-12 text-center-mobile" style="clear: both; text-align: right;padding-left: 5px;padding-right: 5px;" id="prescriptionBtnsDiv">
                                            <button id="btnMsgHistory" type="button" class="btn btn-primary btnmsghistory" style="display: ${order.orderStatus.id == 1 || order.showPtMessage ne true?'none':'inline'};" onclick="showIngredientsPopup('${order.id}', 'MessageHistory')">MSG HISTORY</button>
                                            <button type="button" class="btn btn-primary prebtnpadding" style="height:29px;font-size: 12px; display: ${order.orderStatus.id == 1?'none':'inline'};" onclick="showMessagePopup('${order.dailyRedemption.communicationId}', '${order.dailyRedemption.communicationMethod}')">SEND MSG</button>
                                            <button type="submit" class="btn btn-primary prebtnpadding" style="height:29px;font-size: 12px; display: ${order.orderStatus.id eq 4 || order.orderStatus.id == 1 || order.orderStatus.id eq 5?'none':'inline'};">SAVE</button>
                                            <button type="button" class="btn btn-primary prebtnpadding" style="height:29px;font-size: 12px; display: ${order.orderStatus.id == 1?'inline':'none'};" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/update/${order.id}/2'">ACCEPT ORDER</button>
                                            <button type="button" class="btn btn-primary prebtnpadding" style="height:29px;font-size: 12px; display: ${order.orderStatus.id == 2?'inline':'none'};" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/update/${order.id}/1'">REJECT</button>
                                        </div>
                                    </form:form>
                                </div>

                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 margin-top-10" style="padding:0px 5px; background:white; float:left;">
                                <div class="background1 ${not empty orderList?'prescdivheight':'emptydivheight'}">
                                    <h5 class="innermessage" style="float: left; padding-bottom: 5px;">ORDER DETAIL</h5>
                                    <div class="overviewContainer emptyorderview" style="color: red;">
                                        No data available.
                                    </div>
                                </div>
                            </div>    
                        </c:otherwise>  
                    </c:choose>
                </div>
                <div class="col-lg-6 col-sm-12 col-md-12 col-xs-12 margin-top-18 mobileOverflow presctable div-padding" >
                    <div class="background1 ${not empty orderList?'prescdivheight':'emptydivheight'}">
                        <h5 class="innermessage" style="float: left; padding-bottom: 5px;">ORDERS HISTORY</h5>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 phg" style="padding:1px 0px;">
                            <table id="example" class="recentOrder col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top: 15px; padding-bottom: 15px;width: 100% !important;">
                                <thead>
                                    <tr>
                                        <th class="">Trans. #</th>
                                        <th class="">Rx #</th>
                                        <th class="">Order Date</th>
                                        <th class="">Comments</th>
                                        <th class="">RPh</th>
                                        <th class="">Reimbursed</th>
                                        <th class="">Quality</th>
                                        <th class="">Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${orderList}">
                                        <tr>
                                            <td class="" style="font-size: 11px;">
                                                <div>
                                                    <i class="fa fa-arrow-right" style="float: left; color: red; ${list.id eq selectedOrderNo ? "display: block;":"display: none"}"></i>
                                                    <a href="${pageContext.request.contextPath}/PharmacyPortal/getOrderDetail/${path}/${list.id}" class="blueUnderline">${list.id}</a>
                                                </div>
                                            </td>
                                            <td class="" style="font-size: 11px;">${list.rxNo}</td>
                                            <td class="" style="font-size: 11px;"><fmt:formatDate value="${list.createOn}" pattern="MM/dd/YYYY HH:mm" var="orderdate"/>${orderdate}</td>
                                            <td class="" style="font-size: 11px;"><i style="color:#1f4c76;font-size: 18px;font-weight:bold;cursor: pointer;padding-left:21px;" class="fa fa-eye" onclick="showIngredientsPopup('${list.id}', 'comments')"></i></td>
                                            <td class="" style="font-size: 11px;">${list.rph}</td>
                                            <td class="" style="font-size: 11px;">$<fmt:formatNumber pattern="0.00" value="${list.dailyRedemption.planPharmacyAmount}"/></td>
                                            <td class="" style="padding-left: 19px;font-size: 11px;">${list.quality}</td>

                                            <td class="" style="font-size: 11px;">
                                                <c:if test="${list.orderStatus.id eq 1}">
                                                    <span style="color:rgb(222,102,255);text-align: center;">${list.orderStatus.name}</span>
                                                </c:if>
                                                <c:if test="${list.orderStatus.id eq 2}">
                                                    <span style="color: #2071b6;text-align: center;">${list.orderStatus.name}</span>
                                                </c:if>
                                                <c:if test="${list.orderStatus.id eq 3}">
                                                    <span style="color:rgb(232,108,62);">${list.orderStatus.name}</span>
                                                </c:if>
                                                <c:if test="${list.orderStatus.id eq 4}">
                                                    <span style="color: #067512;">${list.orderStatus.name}</span>
                                                </c:if>
                                                <c:if test="${list.orderStatus.id eq 5}">
                                                    <span style="color: #E70000;">${list.orderStatus.name}</span>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>  
                <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;"></div>      
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables1.10.7.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.min.js"></script>
        <script type="text/javascript">
                                                $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                                                    format: 'm/d/Y',
                                                    onChangeDateTime: function (dp, $input) {
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
                                                $(window).load(function () {
                                                    var scrollX;
                                                    if ($(window).width() === 360 || $(window).width() === 320 || $(window).width() === 640) {
                                                        scrollX = true;
                                                    } else {
                                                        scrollX = "";
                                                    }
                                                    $('#example').dataTable({
                                                        "scrollY": "440px",
                                                        "scrollCollapse": true,
                                                        "paging": false,
                                                        "bSort": false,
                                                        "aaSorting": [],
                                                        "scrollX": true,
                                                        "initComplete": function (settings, json) {
                                                            $(document).find('.dataTables_scrollBody').addClass("scrollbar");
                                                        },
                                                        "language": {
                                                            "emptyTable": "<b style='color: red; font-weight: normal;'>Nothing in queue.</b>"
                                                        }
                                                    });
                                                    $('#example_filter').attr('style', 'display: none;');

                                                    if (document.getElementById('patientInfo').clientHeight > document.getElementById('ptAccept').clientHeight) {
                                                        document.getElementById('ptAccept').style.height = document.getElementById('patientInfo').clientHeight + 'px';
                                                    }
                                                    var v = $("#statusHidden").val();
                                                    if (v !== "") {
                                                        document.getElementById('tracking').selectedIndex = v;
                                                    }
                                                    if (document.getElementById("trackNo").value != "") {
                                                        document.getElementById("trackNo").value = "";
                                                    }
                                                    if (document.getElementById("comments").value != "") {
                                                        document.getElementById("comments").value = "";
                                                    }
                                                    $('select[id="tracking"] option[value="' + $("#statusHidden").val() + '"]').attr("selected", "selected");
                                                    $('tracking').selectpicker('refresh');
                                                });
                                                function disableEnableTrackNo(id) {
                                                    $("#statusHidden").val(id);
                                                    if (id == 4) {
                                                        document.getElementById('trackNo').disabled = false;
                                                        document.getElementById("orderCarriers").disabled = false;
                                                    } else {
                                                        document.getElementById('trackNo').disabled = true;
                                                        document.getElementById("orderCarriers").disabled = true;
                                                    }
                                                }
                                                function startTimer()
                                                {
                                                    window.setInterval("hideMessage()", 5000);
                                                }
                                                function hideMessage() {
                                                    if (document.getElementById("error").value != "") {
                                                        document.getElementById("error").style.display = "none";
                                                    }
                                                    if (document.getElementById("error1").value != "") {
                                                        document.getElementById("error1").style.display = "none";
                                                    }
                                                    if (document.getElementById("error2").value != "") {
                                                        document.getElementById("error2").style.display = "none";
                                                    }
                                                    document.getElementById("orderMessage").style.display = "none";
                                                }
                                                function hideOrderForm() {
                                                    if ($('#orderForm').is(':visible')) {
                                                        $(".orderHeading>i").prop('class', 'fa fa-plus-circle');
                                                        document.getElementById("orderForm").style.display = "none";
                                                    } else {
                                                        $(".orderHeading>i").prop('class', 'fa fa-minus-circle');
                                                        document.getElementById("orderForm").style.display = "block";
                                                    }
                                                }

                                                function showIngredientsPopup(transactionNo, type) {
                                                    var htitle, w;
                                                    if (type == 'comments') {
                                                        htitle = "ORDER COMMENTS";
                                                        if (window.screen.width > 360) {
                                                            w = 360;
                                                        } else {
                                                            w = $(window).width() * 0.9;
                                                        }
                                                    } else if (type == 'MessageHistory') {
                                                        htitle = "PATIENT'S MESSAGE HISTORY";
                                                        if (window.screen.width > 360) {
                                                            w = 360;
                                                        } else {
                                                            w = $(window).width() * 0.9;
                                                        }
                                                    }
                                                    else {
                                                        htitle = "ORDERED INGREDIENTS";
                                                        if (window.screen.width > 360) {
                                                            w = 360;
                                                        } else {
                                                            w = $(window).width() * 0.9;
                                                        }
                                                    }
                                                    var dialog = $("#dialog").dialog({
                                                        closeOnEscape: false,
                                                        autoOpen: false,
                                                        modal: true,
                                                        height: 200,
                                                        width: w,
                                                        title: htitle,
                                                        cache: false,
                                                        open: function (event, ui) {
                                                            //hide titlebar.
                                                            $(".ui-dialog-titlebar").css("font-size", "14px");
                                                            $(".ui-dialog-titlebar").css("font-weight", "normal");
                                                        },
                                                        close: function ()
                                                        {
                                                            $(this).dialog('close');
                                                            $(this).dialog('destroy');
                                                            $(this).html("");
                                                        }
                                                    });
                                                    if (type == 'comments') {
                                                        var url = "${pageContext.request.contextPath}/PharmacyPortal/comments/" + transactionNo;
                                                        $.get(url, function (data) {
                                                            var html = "<div class='col-lg-12 col-sm-12 col-md-12 col-xs-12' style='padding-left:0px;padding-top:5px;padding-bottm:5px;'>";
                                                            $.each(data, function (index, element) {
                                                                html += "<div id='oc" + index + "'>";
                                                                html += "<div style='color: #1f4c76;margin-bottom:-2px;font-size:12px;width:100%;font-weight:bold;'>";
                                                                html += formatDate(new Date(element.createdOn));
                                                                html += "</div>";
                                                                html += "<div style='float:left;font-size:12px;font-weight:bold;'>Status: ";
                                                                html += "<div style='float:right;font-weight:normal;'>";
                                                                html += element.status;
                                                                html += "</div>";
                                                                html += "</div><br>";
                                                                html += "<div style='float:left;font-size:12px;font-weight:bold;position: relative;bottom:10px;'>Comments: ";
                                                                html += "<div style='float:right;font-weight:normal;'>";
                                                                html += element.comments;
                                                                html += "</div>";
                                                                html += "</div><br>";
                                                                html += "</div>";
                                                            });
                                                            html += "</div>";
                                                            $(dialog).html(html);
                                                        });
                                                    } else if (type == 'MessageHistory') {
                                                        var url = "${pageContext.request.contextPath}/PharmacyPortal/ptmessage/" + transactionNo;
                                                        $.get(url, function (data) {

                                                            var html = "<div class='col-lg-12 col-sm-12 col-md-12 col-xs-12' style='padding-left:0px;padding-top:5px;padding-bottm:5px;'>";
                                                            $.each(data, function (index, element) {
                                                                if (index > 0) {
                                                                    html += "<div id='oc" + index + "' style='padding-top:15px;'>";
                                                                } else {
                                                                    html += "<div id='oc" + index + "'>";
                                                                }
                                                                html += "<div style='color: #1f4c76;margin-bottom:-2px;width:100%;font-size:12px;font-weight:bold;'>";
                                                                html += formatDate(new Date(element.createdOn));
                                                                html += "</div>";
                                                                if (element.subject != null) {
                                                                    html += "<div style='float:left;font-weight:bold;font-size:12px;'>Subject: ";
                                                                    html += "<div style='float:right;font-weight:normal;'>";
                                                                    html += element.subject;
                                                                    html += "</div>";
                                                                    html += "</div><br>";
                                                                    html += "<div style='font-size:12px;width:100%;position: relative;bottom:10px;'>";
                                                                    html += element.message;
                                                                    html += "</div>";
                                                                } else {
                                                                    html += "<div style='font-size:12px;width:100%;'>";
                                                                    html += element.message;
                                                                    html += "</div>";
                                                                }
                                                                html += "</div>";
                                                            });
                                                            html += "</div>";
                                                            $(dialog).html(html);
                                                        });
                                                    }
                                                    else {
                                                        var url = "${pageContext.request.contextPath}/PharmacyPortal/orderingredients/" + transactionNo;
                                                        $.get(url, function (data) {
                                                            var html = "<table style='background-color: white; table-layout:fixed; width: 100%;padding-top:5px;padding-bottm:5px;'><tbody>";
                                                            html += "<tr style='font-size: 12px; border-bottom: 1px solid black; color: #1f4c76;'>";
                                                            html += "<td style='width:5%;'></td>";
                                                            html += "<td style='width:30%;'>NDC</td>";
                                                            html += "<td style='width:65%;'>Product Name</td>";
                                                            html += "</tr>";
                                                            $.each(data, function (index, element) {
                                                                if(element.ndcMatch === true){
                                                                    html += "<tr style='font-size: 12px;'>";
                                                                } else {
                                                                    html += "<tr style='font-size: 12px; color: red;'>";
                                                                }
                                                                html += "<td style='width:5%;'>";
                                                                html += index + 1;
                                                                html += ") </td>";
                                                                html += "<td style='width: 30%;'>";
                                                                if (element.ndc != null) {
                                                                    html += element.ndc.substr(0, 5) + "-" + element.ndc.substr(5, 4) + "-" + element.ndc.substr(9, element.ndc.length - 9);
                                                                }
                                                                html += "</td>";
                                                                html += "<td style='width: 65%;'>";
                                                                html += element.name;
                                                                html += "</td>";
                                                                html += "</tr>";
                                                            });
                                                            html += "</tbody></table>";
                                                            $(dialog).html(html);
                                                        });
                                                    }
                                                    $(dialog).dialog("open");
                                                }
                                                function validateField() {
                                                    var e = document.getElementById("tracking");
                                                    if (e.options[e.selectedIndex].value == 0) {
                                                        document.getElementById("orderMessage").style.display = "block";
                                                        $("#orderMessage").prop('class', 'error');
                                                        $("#orderMessage").text("Order status is required.");
                                                        return false;
                                                    }
                                                    if (e.options[e.selectedIndex].value == 4) {
                                                        var oc = document.getElementById("orderCarriers");
                                                        if ($("#trackNo").val() == "") {
                                                            document.getElementById("orderMessage").style.display = "block";
                                                            $("#orderMessage").prop('class', 'error');
                                                            $("#orderMessage").text("Tracking # is required.");
                                                            return false;
                                                        }
                                                        if (oc.options[oc.selectedIndex].value == 0) {
                                                            document.getElementById("orderMessage").style.display = "block";
                                                            $("#orderMessage").prop('class', 'error');
                                                            $("#orderMessage").text("Order carrier is required.");
                                                            return false;
                                                        }
                                                    }
                                                    return true;
                                                }
                                                function showMessagePopup(communicationId, communicationMethod) {
                                                    var w;
                                                    if (window.screen.width > 360) {
                                                        w = 360;
                                                    } else {
                                                        w = $(window).width() * 0.9;
                                                    }
                                                    var dialog = $("#dialog").dialog({
                                                        closeOnEscape: false,
                                                        autoOpen: false,
                                                        modal: true,
                                                        width: w,
                                                        cache: false,
                                                        title: "Send Message to Patient",
                                                        open: function (event, ui) {
                                                            //hide titlebar.
                                                            $(".ui-dialog-titlebar").css("font-size", "14px");
                                                            $(".ui-dialog-titlebar").css("font-weight", "normal");
                                                        },
                                                        close: function ()
                                                        {
                                                            $(this).dialog('close');
                                                            $(this).dialog('destroy');
                                                            $(this).html("");
                                                        }
                                                    });
                                                    var html = "<div class='col-lg-12 col-sm-12 col-md-12 col-xs-12 commentspopup'>";
                                                    html += "<div class='form-group'>";
                                                    html += "<div class = 'input-group'>";
                                                    html += "<div class = 'input-group-addon' >To:</div>"
                                                    if (communicationMethod == 'E') {
                                                        html += "<input type = 'text' class = 'form-control textfontsize' id = 'communicationId' value='" + communicationId + "' placeholder='Enter E-mail' readonly='true'>";
                                                    } else {
                                                        html += "<input type = 'text' class = 'form-control textfontsize' id = 'communicationId' value='" + communicationId + "' placeholder='Enter your mobile phone #' readonly='true'>";
                                                    }
                                                    html += "</div>";
                                                    html += "<div id='error1' style='color: #E70000;font-size: 12px;float:left;'></div>";
                                                    html += "</div>";
                                                    if (communicationMethod == 'E') {
                                                        html += "<div class='form-group' style='padding-top:7px'>";
                                                        html += "<input type = 'text' class = 'form-control textfontsize' id = 'subject' placeholder='Subject' maxlength='255' autofocus>";
                                                        html += "<div id='error2' style='color: #E70000;font-size: 12px;float:left;'></div>";
                                                        html += "</div>"
                                                        html += "<div class='form-group' style='padding-top:7px'>";
                                                        html += "<textarea id='emailBody' class='form-control textfontsize' rows='3' placeholder='Message will be here' maxlength='1000'></textarea>";
                                                        html += "<div id='error' style='color: #E70000;font-size: 12px;float:left;'></div>";
                                                        html += "</div>";
                                                    } else {
                                                        html += "<div class='form-group' style='padding-top:7px'>";
                                                        html += "<textarea id='messageBody' class='form-control textfontsize' rows='3' placeholder='Message will be here' maxlength='160' autofocus></textarea>";
                                                        html += "<div id='error' style='color: #E70000;font-size: 12px;float:left;'></div>";
                                                        html += "<div id='count' class='messagecount'></div>";
                                                        html += "</div>";
                                                    }

                                                    html += "<div style='padding-top:7px'>";
                                                    html += "<button type='button' class='btn btn-primary' style='height:29px;font-size: 12px;' onclick='sendMessage()'>SEND MESSAGE</button>";
                                                    html += "</div>";
                                                    html += "</div>";
                                                    $(dialog).html(html);
                                                    if (communicationMethod == 'T') {
                                                        $("#communicationId").mask("999-999-9999");
                                                        $("#count").text("Characters left: " + 160);
                                                        $("#messageBody").keyup(function () {
                                                            $("#count").text("Characters left: " + (160 - $(this).val().length));
                                                        });
                                                    }
                                                    $(dialog).dialog("open");
                                                }
                                                function sendMessage() {
                                                    var valid = true
                                                    var cmd = $("#cmd").val();
                                                    if ($("#communicationId").val() == "") {
                                                        document.getElementById("error1").style.display = "block";
                                                        $("#error1").text("Required");
                                                        valid = false;
                                                    }
                                                    if (cmd == 'T' && $("#messageBody").val() == "") {
                                                        document.getElementById("error").style.display = "block";
                                                        $("#error").text("Required");
                                                        valid = false;
                                                    }
                                                    if (cmd == 'E' && $("#emailBody").val() == "") {
                                                        document.getElementById("error").style.display = "block";
                                                        $("#error").text("Required");
                                                        valid = false;
                                                    }
                                                    if (cmd == 'E' && $("#subject").val() == "") {
                                                        document.getElementById("error2").style.display = "block";
                                                        $("#error2").text("Required");
                                                        valid = false;
                                                    }
                                                    if (valid) {
                                                        var dataValue = "";
                                                        if (cmd == 'E') {
                                                            dataValue = 'communicationId=' + $("#communicationId").val() + '&subject=' + $("#subject").val() + '&message=' + $("#emailBody").val() + '&communicationMethod=' + cmd + '&orderNo=' + $("#orderId").val();
                                                        } else {
                                                            dataValue = 'communicationId=' + $("#communicationId").val() + '&message=' + $("#messageBody").val() + '&communicationMethod=' + cmd + '&orderNo=' + $("#orderId").val();
                                                        }
                                                        $.ajax({
                                                            url: "${pageContext.request.contextPath}/PharmacyPortal/sendMessage",
                                                            type: "POST",
                                                            data: dataValue,
                                                            success: function (data) {
                                                                if (data == "Success") {
                                                                    $("#dialog").dialog("close");
                                                                    document.getElementById("orderMessage").style.display = "block";
                                                                    $("#orderMessage").text("Message send successfully.");
                                                                    $("#orderMessage").attr('class', 'message');
                                                                    document.getElementById("btnMsgHistory").style.display = "inline";
                                                                } else if (data == "Invalid") {
                                                                    document.getElementById("error1").style.display = "block";
                                                                    $("#error1").text("Invalid phone number.");
                                                                } else {
                                                                    document.getElementById("error").style.display = "block";
                                                                    $("#error").text("There is some problem to send message.");
                                                                }
                                                            },
                                                            error: function (e) {
                                                                alert('Error while request...' + e);
                                                            }
                                                        });
                                                    } else {
                                                        return valid;
                                                    }
                                                }


        </script>
    </body>
</html>



