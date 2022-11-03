<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<html lang="en">
    <jsp:include page="./inc/head2.jsp" />
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" />

    </head>
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
    <body class="home_padding">
        <jsp:include page="./inc/header2.jsp" />

        <!--------------------------------------New Design------------------------------->
        <div class=""> 
            <input id="patientId" type="hidden" value="${patientProfile.id}"/>
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
                                                    <!--<span>SPECIAL INSTRUCTIONS</span>-->
                                                    <input type="text" value="" disabled="true" name="" class="form-control" />
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
                                <li class="dropdown">Allergies <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down" aria-hidden="true"></i></a>
                                
                                <div class="dropdown-menu address_dropdown allergies_drp">
                                    <ul class="address_list"> 
                                            <li> 
                                              ${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}
                                            </li>                                     
                                    </ul>
                                </div>
                                    <!--<input type="text" name="" 
                                           value="${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}" 
                                           disabled="true" class="form-control" />-->
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
                                                    <!--<textarea class="form-control" placeholder="Allergies"></textarea>-->
                                                </li>

                                            </c:forEach>
                                        </ul> 
                                    </div>
                                </li>
                                <li class="dropdown">Insurance & copay cards <span>(${fn:length(lstInsuranceCards)})</span><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down" aria-hidden="true"></i></a>
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
                                                        <h6>Back</h6>
                                                        <img src="${cards.insuranceBackCardPath}" alt="insurance card back" data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Insurance Card (Back) Click on image for full screen')"/> </div>
                                                    </c:forEach>
                                            </li>

                                        </ul>
                                    </div>
                                </li>
                                <li><span>${total}</span> Program Rx’s</li>
                                <li>Member Since <span><fmt:formatDate value='${patientProfile.createdOn}' pattern='yyyy-MM-dd'/></span></li>
                            </ul>
                            <span class="icon_Status"> <fmt:formatNumber value="${patientProfile.totalRewardPoints}" pattern="#,###"  /><br />
                                Basic </span> 
                                <c:if test="${patientProfile.isOldPatient ne true}">
                                <span class="icon_unew"></span> 
                            </c:if>
                        </div>
                        <div class="textBox patient_box" style="display: none;">
                            <form action="" class="messageForm">
                                <i class="fa fa-paperclip fileUpload" title="upload file" onclick="window.pharmacyNotification.uploadFile()"></i>
                                <input id="attachFile" type="file" style="display: none;" accept="image/*" onchange="window.pharmacyNotification.validateFileFormat();"/>
                                <textarea id="messageId" name="pharmacyNote" class="messageBox" onclick="window.pharmacyNotification.resetMessageFn();" placeholder="Send Message to Patient"></textarea>
                                <button type="button" onclick="window.pharmacyNotification.savePharmacyNotificationForPaientFn();"><i class="fa fa-paper-plane greenText"></i></button>
                                <div id="errorSendMessage" class="pull-right"></div>
                                <!--
                                <a href="#" class="greenText messageHistory pull-left" onclick="displayModal(${order.orderId}, 'historyModal', 'viewPatientHistoryMessageWs')">View Message History</a>
                                --> </form>
                        </div>

                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation"> <a href="#home" aria-controls="home" role="tab" data-toggle="tab">Pending & Transfers(${count})</a> </li>
                            <li role="presentation" class="active"> <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Active Rx’s (${activeCount})</a> </li>
                            <li role="presentation"> <a href="#cancel" aria-controls="cancel" role="tab" data-toggle="tab">Cancelled (${cancelActiveCount})</a> </li>
                        </ul>



                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane" id="home">
                                <div class="tableTenRecord tablecnt">
                                    <table id="level2" class="table-striped">
                                        <thead>
                                            <tr>

                                                <th>Original Date</th>
                                                <th>Order#</th>
                                               
                                                <th>Rx Name/Strength</th>
                                                <th>Qty</th>
                                                <th>Days Supply</th>
                                                <th>Refills Remain</th>
                                                <th>INS COST</th>
                                                <th>INS/CASH</th>
                                                <th>Sales Price</th>
                                                <th>PT Amount Suggested</th>
                                                <th>Profit Margin</th>
                                                <th>Points Earned</th>
                                                <th>Delivery Fee</th>
                                                <th>Final PT Price</th>
                                                <th>Image/Video</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>  
                                        <tbody>


                                            <!------------------------------------------------------------------------>
                                            <c:forEach  var="row" items="${patientOrderQueue}">
                                                <tr>


                                                    <td>${row.orderDate}</td>
                                                    <td><a href="<c:url value="/ConsumerPortal/rxDetail/${row.id}/${row.patientId}?profile=1"/>" 
                                                           style="color: blue">${row.id}</a></td>
                                                   
                                                    <td>${row.drugName}</td>
                                                    <td>${row.qty}</td>
                                                    <td>${row.daysSupply}</td>
                                                    <td>${row.refillsRemaining}</td>
                                                    <td><fmt:formatNumber value="${row.originalPtCopay}" pattern="0.00"/></td>
                                                    <td>Cash</td>
                                                    <!------------------------------------>
                                                    <td><fmt:formatNumber value="${row.drugPrice}" pattern="0.00"/></td>
                                                    <td><fmt:formatNumber value="${row.amountWithoutMargin}" pattern="0.00"/></td>
                                                    <td><fmt:formatNumber value="${row.profitMargin}" pattern="0.00"/></td>
                                                    <td>${row.pointsEarned}</td>
                                                    <!------------------------------------>
                                                    <td>$ 
                                                        <c:choose>
                                                            <c:when test="${not empty row.handLingFee}">
                                                                <fmt:formatNumber value="${row.handLingFee}" pattern="0.00"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                0.00
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>$ <fmt:formatNumber value="${row.finalPayment}" pattern="0.00"/></td>
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:set var="imgs" value="${row.transferImage}"   />
                                                    <c:choose>
                                                        <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                            <td> <span style="color:red">.........</span>

                                                            </td>
                                                        </c:when>
                                                        <c:when test="${imgs ne ''}">
                                                            <td> <a href="javascript:window.open('${row.transferImage}')">
                                                                    <i class="fa fa-paperclip" aria-hidden="true" ></i></a>


                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td> <a href="javascript:window.open('${row.ptVideo}')">
                                                                    <i class="fa fa-paperclip" aria-hidden="true" ></i></a>


                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>${row.orderStatusName}</td>

                                                </tr>
                                            </c:forEach>

                                            <!------------------------------------------------------------------------>


                                        </tbody>
                                    </table>

                                </div>

                            </div>
                            <div role="tabpanel" class="tab-pane active" id="profile">
                                <div class="tableTenRecord">
                                    <table id="level1" class="table-striped">


                                        <thead>
                                            <tr>
                                                <th style="padding-right: 35px !important;">Fill&nbsp;Date</th>
                                                <th>Original Date</th>
                                                <th>Rx Number</th>
                                                <th>Order#</th>
                                                <th>Rx Name/Strength</th>
                                                <th>Qty</th>
                                                <th>Days Supply</th>
                                                <th>Refills Remain</th>
                                                <th>INS COST</th>
                                                <th>INS/CASH</th>
                                                <th>Sales Price</th>
                                                <th>PT Amount Suggested</th>
                                                <th>Profit Margin</th>
                                                <th>Points Earned</th>
                                                <th>Delivery Fee</th>
                                                <th>Final PT Price</th>
                                                <th>Next Refill Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>  
                                        <tbody>

                                            <!------------------------------------------------------------------------>
                                            <c:forEach  var="row" items="${patientActiveOrderQueue}">
                                                <tr>
                                                    <td>

                                                        <fmt:formatDate value='${row.statusCreatedOn}' pattern='yyyy-MM-dd' var="date" />
                                                        <fmt:formatDate value='${row.statusCreatedOn}' pattern='HH:mm:ss' var="time" />
                                                        <a href="<c:url value="/ConsumerPortal/rxDetail/${row.id}/${row.patientId}?profile=1"/>" 
                                                           style="color: blue">
                                                            ${date}                                                                        
                                                        </a>
                                                    </td>

                                                    <td>${row.orderDate}</td>
                                                    <td>${row.rxNumber}</td>
                                                    <td>
                                                        <a href="<c:url value="/ConsumerPortal/rxDetail/${row.id}/${row.patientId}?profile=1"/>" 
                                                           style="color: blue">${row.id}</a></td>
                                                    <td>${row.drugName}</td>
                                                    <td>${row.qty}</td>
                                                    <td>${row.daysSupply}</td>
                                                    <td>${row.refillsRemaining}</td>
                                                    <td><fmt:formatNumber value="${row.originalPtCopay}" pattern="0.00"/></td>
                                                    <td>Cash</td>
                                                    <!------------------------------------>
                                                    <td><fmt:formatNumber value="${row.drugPrice}" pattern="0.00"/></td>
                                                    <td><fmt:formatNumber value="${row.amountWithoutMargin}" pattern="0.00"/></td>
                                                    <td><fmt:formatNumber value="${row.profitMargin}" pattern="0.00"/></td>
                                                    <td>${row.pointsEarned}</td>
                                                    <!------------------------------------>
                                                    <td>$ 
                                                        <c:choose>
                                                            <c:when test="${not empty row.handLingFee}">
                                                                <fmt:formatNumber value="${row.handLingFee}" pattern="0.00"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                0.00
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td>$ <fmt:formatNumber value="${row.finalPayment}" pattern="0.00"/></td>
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:set var="imgs" value="${row.transferImage}"   />
                                                    <c:choose>
                                                        <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                            <td> <fmt:formatDate value='${row.nextRefillDate}' pattern='yyyy-MM-dd' var="nextDate" />
                                                                <c:choose>
                                                                    <c:when test="${nextDate==null || nextDate==''}">
                                                                        <span style="color:red">.........</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${nextDate}
                                                                    </c:otherwise>    
                                                                </c:choose>
                                                            </td>
                                                        </c:when>
                                                        <c:when test="${imgs ne ''}">
                                                            <td> <fmt:formatDate value='${row.nextRefillDate}' pattern='yyyy-MM-dd' var="nextDate" />
                                                                <c:choose>
                                                                    <c:when test="${nextDate==null || nextDate==''}">
                                                                        <span style="color:red">.........</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${nextDate}
                                                                    </c:otherwise>    
                                                                </c:choose>
                                                                <a href="javascript:window.open('${row.transferImage}')">
                                                                    <i class="fa fa-paperclip" aria-hidden="true" ></i></a>


                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td> <fmt:formatDate value='${row.nextRefillDate}' pattern='yyyy-MM-dd' var="nextDate" />
                                                                <c:choose>
                                                                    <c:when test="${nextDate==null || nextDate==''}">
                                                                        <span style="color:red">.........</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${nextDate}
                                                                    </c:otherwise>    
                                                                </c:choose>
                                                                <a href="javascript:window.open('${row.ptVideo}')">
                                                                    <i class="fa fa-paperclip" aria-hidden="true" ></i></a>


                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>${row.orderStatusName}</td>

                                                </tr>
                                            </c:forEach>

                                            <!------------------------------------------------------------------------>



                                        </tbody>
                                    </table>

                                </div>

                            </div>
                            <div role="tabpanel" class="tab-pane" id="cancel">
                                <div class="tableTenRecord tablecnt">
                                    <table id="level3" class="table-striped">
                                        <thead>
                                            <tr>

                                                <th>Original Date</th>
                                                <th>Rx Number</th>
                                                <th>Rx Name/Strength</th>
                                                <th>Qty</th>
                                                <th>Days Supply</th>
                                                <th>Refills Remain</th>
                                                <th>INS COST</th>
                                                <th>INS/CASH</th>
                                                <th>Sales Price</th>
                                                <th>PT Amount Suggested</th>
                                                <th>Profit Margin</th>
                                                <th>Points Earned</th>
                                                <th>Delivery Fee</th>
                                                <th>Final PT Price</th>
                                                <th>Image/Video</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>  
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${cancelActiveCount > 0}">
                                                    <!------------------------------------------------------------------------>
                                                    <c:forEach  var="row" items="${patientCancelOrderQueue}">
                                                        <tr>


                                                            <td>${row.orderDate}</td>


                                                            <%--<c:url value="/ConsumerPortal/rxDetail/${row.id}/${row.patientId}?profile=1"/>--%> 
                                                            <td>  ${row.rxNumber}</td>
                                                            <td>${row.drugName}</td>
                                                            <td>${row.qty}</td>
                                                            <td>${row.daysSupply}</td>
                                                            <td>${row.refillsRemaining}</td>
                                                            <td><fmt:formatNumber value="${row.originalPtCopay}" pattern="0.00"/></td>
                                                            <td>Cash</td>
                                                            <!------------------------------------>
                                                            <td><fmt:formatNumber value="${row.drugPrice}" pattern="0.00"/></td>
                                                            <td><fmt:formatNumber value="${row.amountWithoutMargin}" pattern="0.00"/></td>
                                                            <td><fmt:formatNumber value="${row.profitMargin}" pattern="0.00"/></td>
                                                            <td>${row.pointsEarned}</td>
                                                            <!------------------------------------>
                                                            <td>$ 
                                                                <c:choose>
                                                                    <c:when test="${not empty row.handLingFee}">
                                                                        <fmt:formatNumber value="${row.handLingFee}" pattern="0.00"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        0.00
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>$ <fmt:formatNumber value="${row.finalPayment}" pattern="0.00"/></td>
                                                            <c:set var="vid" value="${row.ptVideo}"   />
                                                            <c:set var="imgs" value="${row.transferImage}"   />
                                                            <c:choose>
                                                                <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                                    <td> <span style="color:red">.........</span>

                                                                    </td>
                                                                </c:when>
                                                                <c:when test="${imgs ne ''}">
                                                                    <td> <a href="javascript:window.open('${row.transferImage}')">
                                                                            <i class="fa fa-paperclip" aria-hidden="true" ></i></a>


                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td> <a href="javascript:window.open('${row.ptVideo}')">
                                                                            <i class="fa fa-paperclip" aria-hidden="true" ></i></a>


                                                                    </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${row.orderStatusName}</td>

                                                        </tr>
                                                    </c:forEach>

                                                    <!------------------------------------------------------------------------>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr>

                                                        <td colspan="10" align="center" style="color:red">No record found</td>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>


                                        </tbody>
                                    </table>

                                </div>

                            </div>
                            <!----------------------------------------------------->
                            <a href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest/${orderId}?status=${order.status}" class="btn back_qq pull-left" name="back"></a>
                            
                        </div>
                    </div>
                </div>
            </div>

            <!--Registration Page--> 
            <!--Footer-->
            <jsp:include page="./inc/footer2.jsp" />
            <!--/Footer-->
        </div>
        <!--------------------------------------New Design------------------------------->
        <!--/ Modal Status -->
        <jsp:include page="modals.jsp" />
        <!-- Modal Register -->
        <!--/ Modal Register -->

        <!-- jQuery -->

        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientDependent.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInsurancePharmacy.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
        <script>


                                    $('ul.dropdown-menu').on('click', function (event) {
                                        //The event won't be propagated to the document NODE and 
                                        // therefore events delegated to document won't be fired
                                        event.stopPropagation();
                                    });
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

                                        $('#level2').dataTable({"sPaginationType": "full_numbers",
                                            "aoColumnDefs": [
                                                {'bSortable': false, 'aTargets': [2, 4, 5, 6, 7]}
                                            ]
                                        });
                                        $('#level1').dataTable({
                                            "sPaginationType": "full_numbers",
                                            "aoColumnDefs": [
                                                {'bSortable': false, 'aTargets': [2, 4, 5, 6, 7]}
                                            ],
                                            "oLanguage": {
                                                "sEmptyTable": "No records found."
                                            },
                                            "fnInitComplete": function () {
                                                $(".dataTables_empty").attr("style", "color:red;")

                                            }
                                        });

                                    });

                                    function viewLargeImg(path, title) {
                                        var dialog = $("#dialog").dialog({
                                            closeOnEscape: false,
                                            autoOpen: false,
                                            modal: true,
                                            height: 260,
                                            width: '24%',
                                            cache: false,
                                            autoResize: true,
                                            title: title,
                                            open: function (event, ui) {
                                                //hide titlebar.
                                                $(".ui-dialog-titlebar").css("background-color", "blue");
                                                $(".ui-dialog-titlebar").css("font-size", "14px");
                                                $(".ui-dialog-titlebar").css("font-weight", "normal");
                                            },
                                            close: function ()
                                            {
                                                $(this).dialog('close');
                                                $(this).dialog('destroy');
                                            }
                                        });
                                        var url = "${pageContext.request.contextPath}/patient/" + path;
                                        var html = '<img src="' + url + '" width="340" height="200">';
                                        $(dialog).html(html);
                                        $("#dialog").dialog("open");
                                    }



                                    $('body').on('hidden.bs.modal', '.modal', function () {
                                        $('iframe').trigger('pause');
                                    });

                                    var vid = document.getElementById("myVideo");

                                    function playVid() {
                                        vid.play();
                                    }

                                    function pauseVid() {
                                        vid.pause();
                                    }

        </script>
    </body>

</html>

