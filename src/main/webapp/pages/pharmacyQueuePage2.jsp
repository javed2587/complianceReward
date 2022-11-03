<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./portal/inc/head2.jsp" />
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
    <body class="home_padding secondHeaderBody">
        <jsp:include page="./portal/inc/headerPage2.jsp"/>

        <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
        <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.birthDate}"/>
        <c:if test="${order.dependentFlag eq true}" >
            <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientOrderQueuepage2.patientDOB}"/>
        </c:if>
        <!--Registration Page-->;

        <div class="">
            <div class="col-md-12"> 
                
                <div class="tableContainer patient_info margin_top_new_top row manage_topPosition">
                    <div class="col-sm-6 patient_name padd_zero">
                        <c:set var="isAdmin" value="false"></c:set>
                        <c:if test="${sessionBeanPortal.isAdmin}">
                            <c:set var="isAdmin" value="true"></c:set>
                            <h3>${fn:toUpperCase(patientProfile.firstName)} ${fn:toUpperCase(patientProfile.lastName)} &nbsp;<span><fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.birthDate}"/> <span>(${patientProfile.gender})  ${nowyear-dobyear} yrs</span></span></h3>
                        </c:if>

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
                        <div id="allergiesDiv" class="col-sm-4 rx_allergies padd_zero scroll" style="">
                            ${not empty patientProfile.allergies?patientProfile.allergies:''}
                        </div>
                        <div class="col-sm-1 rx_label padd_zero manage-btnSave">
                            <input type="button" class="btn-save" 
                                   onclick="window.pharmacyNotification.updatePatientAllergies()" value="Save" />

                        </div>
                    </c:if>
                    <jsp:include page="inc/pharmacyQueueMenu.jsp" />
                    <!--Registration Page-->

                    <div class="customTableRow clearfix cutome_left">
                        <input type="checkbox" id="navigation" />
                        <label class="cutmobtn ${isAdmin eq false?'disabledEvents':''}" for="navigation">
                        </label>
                        <div class="tque_left">
                            <div class="savePharm">
                                <h4 class="pull-left"> ${PharmacyTitle} </h4><%--<a href="${pageContext.request.contextPath}/PharmacyPortal/profile_edit/${Email}" class="pull-right">Edit</a>--%>
                                <p>  ${Address}</p>
                                <p>  ${Address2}</p><br />
                                <p><span>Name:</span> ${fn:toUpperCase(patientProfile.firstName)} ${fn:toUpperCase(patientProfile.lastName)}</p>
                                <p><span>Email:</span>  ${Email}</p>
                                <p> <span>Ph:</span> ${Phone}</p>
                                <p> <span>Fx:</span> ${Fax}</p>
                            </div>
                            <div class="userProf">
                                <h4>USER profiles</h4>
                                <ul>
                                    <li><span>Pharmacist</span>${Admin} </li>
                                    <li><span>Staff</span>${Staff}</li>
                                    <li><span>Other</span>0</li>
                                </ul>
                            </div>
                            <div class="queueOver">
                                <h4>Queue Overview</h4>
                                <ul>
                                    <li><span>New / Open</span>${Count}</li>
                                    <li><span>Pending</span>0</li>
                                    <li><span>Processed</span>0</li>
                                </ul>
                            </div>

                            <div class="rxqueue">
                                <p class="caretRight">RX QUEUE </p>
                            </div>


                            <ul class="rx_lft_menu">
                                <li><a href="javascript:;">Reports</a> 

                                </li>
                            </ul>
                            <div class="rxqueue" style="background-color:#8aca14 ">
                                <p class="caretRight"> <a href="${pageContext.request.contextPath}/PharmacyPortal/careGiverList">PENDING CAREGIVER </a> </p>
                            </div>
                            <div class="rxqueue">
                                <p class="caretRight">patient Lookup </p>
                            </div>

                        </div>
                        <!-- Saimoon New Table-->
                        <div class="col-md-12 custome_right rx_newtable">

                            <div class="tableContainer manage_container">

                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs margin_top_none" role="tablist">
                                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">${filterLabel}</a></li>

                                </ul>
                                <form:form action="${pageContext.request.contextPath}/ConsumerPortal/queuePatientDetailPage" commandName="baseDTO" role="form" method="Post">
                                    <input id="patientId" type="hidden" value="${patientProfile.id}" name="patientId"/>
                                    <input id="dependentId" type="hidden" value="${baseDTO.dependentId}" name="dependentId"/>
                                    <input id="selectedTab" type="hidden" value="${sessionBeanPortal.selectedTab}" name="selectedTab"/>
                                    <div class="outer-helpingSec">
                                        <div class="col-sm-2 col-xs-12 drp-ipad">
                                            <div class="input-group">
                                                <fmt:formatDate var="fromDate" pattern="MM/dd/yyyy" value="${baseDTO.fromDate}"/>
                                                <span class="input-group-addon" id="basic-addon2">From:<span style="color:red">*</span></span>
                                                <form:input id="datetimepicker" path="fromDate" cssClass="form-control" value="${fromDate}" aria-describedby="basic-addon2" 
                                                            placeholder="MM/dd/yyyy" onchange="enableSearchBtn()"  style=" min-width: 110px;" />
                                            </div>
                                            <span id="fromDateReq" class="errorMessage"></span>
                                        </div>
                                        <div class="col-sm-2 col-xs-12 to_date drp-ipad">
                                            <div class="input-group">
                                                <fmt:formatDate var="toDate" pattern="MM/dd/yyyy" value="${baseDTO.toDate}"/>
                                                <span class="input-group-addon" id="basic-addon3">To:<span style="color:red">*</span></span>
                                                <form:input id="datetimepicker1" path="toDate" cssClass="form-control" value="${toDate}" 
                                                            aria-describedby="basic-addon3" placeholder="MM/dd/yyyy" onchange="enableSearchBtn()"  style=" max-width: 110px;" />
                                            </div>
                                            <span id="toDateReq" class="errorMessage"></span>
                                        </div>
                                        <div class="col-sm-3 col-xs-12 drp-ipad">
                                            <div class="input-group">
                                                <span class="input-group-addon" id="basic-addon3">Status:<span style="color:red">*</span></span>
                                                <form:select path="status" cssClass="form-control" onchange="enableSearchBtn()">
                                                    <form:option value="" label="All" />
                                                    <form:option value="Refill Now" label="Refill Now" />
                                                    <form:option value="Active Orders" label="Active Orders" />
                                                    <form:option value="Expiring Soon" label="Expiring Soon" />
                                                    <form:option value="Pending" label="Unverified Images/Pending" />
                                                    <form:option value="Processing" label="Processing" />
                                                    <form:option value="Pending Review" label="Interpreted Images" />
                                                    <form:option value="Waiting Pt Response" label="Waiting Pt Response" />
                                                    <form:option value="Filled" label="Processed Requests" />
                                                    <form:option value="Cancelled" label="Cancelled Requests" />
                                                    <form:option value="Response Received" label="Patient Response" />
                                                    <form:option value="Shipped" label="Shipping Delivery" />
                                                </form:select>

                                            </div>
                                            <span id="toDateReq" class="errorMessage"></span>
                                        </div>
                                        <div class="col-sm-2 col-xs-12">
                                            <div style="float: left;">
                                                <button id="btnSearch" class="btn btn-primary width_none_new" style="color: white">Search</button>
                                            </div>
                                            <div>
                                                <a href="${pageContext.request.contextPath}/ConsumerPortal/queuePatientDetailPage/${patientProfile.id}/${baseDTO.dependentId}/${sessionBeanPortal.selectedTab}" class="btn btn-primary width_none_new" style="color: white;margin-left: 5px;">Cancel</a>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                                <c:if test="${(sessionBeanPortal.pmap[(82).intValue()].view eq true || sessionBeanPortal.pmap[(82).intValue()].manage eq true)}">
                                    <!--<button type="button" class="btn btn-primary btn_estimate" data-toggle="modal" data-target="#Drug_ind">Estimate Price</button>-->
                                </c:if>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="home">
                                        <table class="table-striped" class="display nowrap" cellspacing="0" width="100%" id="newRequestTable">
                                            <thead>
                                                <tr>

                                                    <th>REQ&nbsp;#</th>
                                                    <th class="fixed_size">LAST&nbsp; STATUS<br/ >POSTED</th>
                                                    <th>MBR&nbsp;SVC<br />REQUEST</th>
                                                    <th class="svc_size txtAlign">SVC</th>
                                                    <th class="status_size">Current<br />RX&nbsp;STATUS</th>
                                                    <th class="medi_size">MEDICATION</th>   
                                                    <th>SELF&nbsp;PAY</th>
                                                    <th class="pt_size txtAlign">PUBLIC <br />INS.</th>

                                                    <th class="pt_size">Final&nbsp; Pt OOP</th>
                                                    <th class="pt_size">Days Until <br />Refill</th>
                                                    <th>Refill<br />Remain</th>
                                                    <th class="redText">Until Rx Expires</th>
                                                    <th class="txtAlign">DATE<br/>DELIVERED</th>
                                                    <th class="medi_size">ADDRESS</th>
                                                    <th>ZIP</th>



                                                </tr>
                                            </thead>
                                            <fmt:formatDate value='${baseDTO.fromDate}' pattern='yyyy-MM-dd' var="fromDateStr" />
                                            <fmt:formatDate value='${baseDTO.toDate}' pattern='yyyy-MM-dd' var="toDateStr" />
                                            <tbody>
                                                <c:forEach var="row" items="${patientOrderQueue}">
                                                    <tr>

                                                        <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd' var="creationDate" />
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='HH:mm:ss' var="creationTime" />


                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${row.deliveryService eq 'Same Day' || row.deliveryService eq 'Next Day*'}">
                                                                    <a href="<c:url 
                                                                           value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}?fromDate=${fromDateStr}&toDate=${toDateStr}&status=${baseDTO.status}"/>" class="redText">${row.requestControlNumber1} <br> ${row.requestControlNumber2} ${row.rxNo}</a>

                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="<c:url 
                                                                           value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}?fromDate=${fromDateStr}&toDate=${toDateStr}&status=${baseDTO.status}"/>" >${row.requestControlNumber1} <br> ${row.requestControlNumber2} ${row.rxNo}</a>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                        </td>
                                                        <fmt:formatDate value='${row.updatedDate}' pattern='yyyy-MM-dd' var="date" />
                                                        <fmt:formatDate value='${row.updatedDate}' pattern='HH:mm' var="time" />
                                                        <c:choose>


                                                            <c:when test="${row.deliveryService eq 'Same Day'}">
                                                                <td><a href="<c:url 
                                                                           value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}?fromDate=${fromDateStr}&toDate=${toDateStr}&status=${baseDTO.status}"/>"
                                                                       style="color: red">${date}<br>@<strong>${time}</strong></a>
                                                                </td>
                                                            </c:when>

                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                                <td><a href="<c:url 
                                                                           value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}?fromDate=${fromDateStr}&toDate=${toDateStr}&status=${baseDTO.status}"/>"
                                                                       style="color: red">${date}<br>@<strong>${time}</strong></a>
                                                                </td>
                                                            </c:when>

                                                            <c:otherwise>
                                                                <td><a href="<c:url 
                                                                           value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}?fromDate=${fromDateStr}&toDate=${toDateStr}&status=${baseDTO.status}"/>"
                                                                       style="color: blue">${date}<br>@<strong>${time}</strong></a>
                                                                </td>

                                                            </c:otherwise>

                                                        </c:choose>

                                                        <c:choose>
                                                            <c:when test="${row.deliveryService eq 'Same Day'}">
                                                                <td class="redText">${row.requestType}</td>
                                                            </c:when>
                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                                <td class="redText">${row.requestType}</td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td style="color:blue">${row.requestType}</td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${row.deliveryService eq 'Same Day'}">
                                                                <td class="txtAlign" style="color:red">${row.deliveryService}<br>
                                                                    <strong><span style="color:red">!</span></strong>
                                                                </td>
                                                            </c:when>

                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                                <td class="txtAlign"><span style="color:blue">${row.deliveryService}</span><br>
                                                                    <strong><span style="color:red">!</span></strong>
                                                                </td>
                                                            </c:when>

                                                            <c:when test="${row.deliveryService eq '2nd Day*'}">
                                                                <td class="txtAlign" style="color:blue">${row.deliveryService}</td>
                                                            </c:when>


                                                            <c:otherwise>
                                                                <td style="color:blue" class="txtAlign"><span >${row.deliveryService}


                                                                    </span></td>
                                                                </c:otherwise>

                                                        </c:choose>
                                                        <td>${row.status}</td>


                                                        <c:choose>
                                                            <c:when test="${row.userInput eq false}">
                                                                <td>${row.drugNameWithoutStrength} 
                                                                    &nbsp;${row.strength} &nbsp;${row.drugType} </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                <td  class="redText">
                                                                    <c:if test="${row.userInputStr ne ''}">
                                                                        <span class="icon_medication"></span>
                                                                    </c:if>
                                                                    ${row.userInputStr}

                                                                </td>

                                                            </c:otherwise>
                                                        </c:choose>

                                                        <c:choose>
                                                            <c:when test="${row.finalPaymentMode eq 'SELF PAY'}">
                                                                <td style="color:blue;" class="txtAlign">${row.finalPaymentMode}</td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td style="color:red;" class="txtAlign">${row.finalPaymentMode}</td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${row.finalPaymentMode eq 'INSURANCE'}">
                                                                <td style="color:red;" class="txtAlign">Y</td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td style="color:blue;" class="txtAlign">N</td>
                                                            </c:otherwise>
                                                        </c:choose>


                                                        <td class="bold_new">${row.originalPtCopayStr}</td>
                                                        <td>${row.daysToRefill}</td>
                                                        <td class="txtAlign">${row.refillsRemaining}</td>
                                                        <td>${row.daysToExpire}</td>

                                                        <td class="txtAlign">${row.deliveryDate}</td>
                                                        <td>${patientProfile.defaultAddress}</td>
                                                        <td>${row.zip}</td>

                                                    </tr>
                                                </c:forEach>


                                            </tbody>
                                        </table>

                                    </div>
                                    <a href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest/${orderId}?status=${order.status}" class="btn back_qq pull-left img_arrow" name="back"></a>


                                </div>

                            </div>

                        </div>
                        <!-- Saimoon New Table-->

                    </div>
                </div>

            </div>


        </div>
        <!--Registration Page-->




        <jsp:include page="./portal/inc/footer2.jsp" />
    </div>
    <!-- Modal Status -->
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
                    <p>Prescription is not currently open by any user.</p>
                </div>
            </div>

        </div>
    </div>
    <!--/ Modal Status -->

    <!-- Modal Register -->
    <!--/ Modal Register -->
    <!-- jQuery -->

    <!--Financial Report-->
    <div class="modal fade financial_modal" id="finanmodal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>Financial Report</h4>
                </div>
                <div class="modal-body clearfix">
                    <div class="col-sm-6"><label>From</label>
                        <input id="frmDate" name="frmDate" class="form-control" placeholder="mm/dd/yyyy" value='${frmDate}'
                               onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"
                               disabled="true"   /> </div>

                    <div class="col-sm-6"><label>To</label>
                        <input   id="toDate" name="toDate" placeholder="mm/dd/yyyy" value='${toDate}'
                                 class="form-control" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" disabled="true"/> </div>
                    <!----------------------------------------------------------------------------------------------------------------->
                    <div class="col-sm-6"><label>Cycle#</label>
                        <input id="cycleNo" name="currCycle" class="form-control" placeholder="Cycle#" value='${currCycle}'
                               maxlength="2" onkeypress="return IsDigit(event)"
                               /> </div>

                    <div class="col-sm-6"><label>Year</label>
                        <input   id="year" name="year" placeholder="Year" value='${currYear}'
                                 class="form-control"  maxlength="4" onkeypress="return IsDigit(event)"/> </div>
                    <div class="col-sm-6"><label>Format</label></div>
                    <div class="col-sm-6">
                        <input type="radio" name="formatBtn" id="radio-a" value="pdfView" checked>Pdf &nbsp;
                        <input type="radio" name="formatBtn" id="radio-b" value="excelView" >Excel
                    </div>
                    <div>
                        <span id="errorDiv" style="color:red; font-weight:bold">

                        </span>      
                    </div>
                    <!----------------------------------------------------------------------------------------------------------------->
                    <div class="col-sm-12 gobtn">
                        <input type="button" value="Submit" name="go" class="btn btn-primary" onclick="exportFinancialReportData()"/>
                    </div>
                </div>

            </div>

        </div>
    </div>
    <!--/Financial Report-->



    <!--Drug Modal Saimoon-->
    <jsp:include page="./portal/drugestimateprice.jsp" />
    <!--/Drug Modal Saimoon-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
    <script>
                            $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                                format: 'm/d/Y',
                                onChangeDateTime: function (dp, $input) {
                                    jQuery('#datetimepicker').datetimepicker('hide');
                                    jQuery('#datetimepicker1').datetimepicker('hide');
                                }

                            });

                            $(document).ready(function ( ) {
                                $('#newRequestTable').dataTable({"sPaginationType": "full_numbers",
                                    "aoColumnDefs": [
                                        {'bSortable': false, 'aTargets': [4, 5, 6]}
                                    ],
                                    "scrollX": true,
                                    "scrollY": "400",
                                    "responsive": true
                                });

                                $('#interpretedIImageTable').dataTable({"sPaginationType": "full_numbers",
                                    "aoColumnDefs": [
                                        {'bSortable': false, 'aTargets': [4, 5, 6]}
                                    ],

                                });

                                $('#interpretedIImageTable').dataTable({"sPaginationType": "full_numbers",
                                    "aoColumnDefs": [
                                        {'bSortable': false, 'aTargets': [2, 4, 5, 6, 7]}
                                    ],

                                });

                                $('#messagesexample').dataTable({"sPaginationType": "full_numbers",
                                    "aoColumnDefs": [
                                        {'bSortable': false, 'aTargets': [2, 4, 5, 6, 7]}
                                    ],

                                });


                                //$("#example_wrapper > .dataTables_filter").appendTo("div.search_tab_f");
                                //$("#example_wrapper > .dataTables_length").appendTo("div.sorting_tab_f"); 
                                enableSearchBtn();
                                window.drugestimateprice.populateAppUrl('${pageContext.request.contextPath}');
                                window.transferRequest.populateAppUrl('${pageContext.request.contextPath}');
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
                                });
                                $(".cutome_left label.cutmobtn").click(function (e) {
                                    $(".cutome_left label.cutmobtn").toggleClass("btnopenedc");
                                });
                                $(".rx_lft_menu li a").click(function (e) {

                                    $(this).next("ul").slideToggle();
                                });

                                $("#cycleNo,#year").bind('blur', function ()
                                {
                                    //alert("A");
                                    var cycle = $("#cycleNo").val();
                                    var year = $("#year").val();

                                    var json = {"cycle": cycle, "year": year};
                                    /////////////////////////////////////////
                                    $.ajax(
                                            {
                                                url: "/PharmacyPortal/loadFinancialCycleInfo",
                                                type: "POST",
                                                dataType: 'text',
                                                contentType: 'application/json',
                                                data: JSON.stringify(json),
                                                success: function (data)
                                                {
                                                    var response = $.parseJSON(data);
                                                    //alert("data "+response);
                                                    $("#frmDate").val(response.frmDate);
                                                    $("#toDate").val(response.toDate);
                                                    //alert(""+data);
                                                    //alert("Drug detail has been saved successfully.")
                                                    //$("#drugDataForm").submit();

                                                },
                                                error: function (xhr, status, error)
                                                {
                                                    alert(xhr.responseText);
                                                }
                                            });
                                    ////////////////////////////////////////
                                });
                            });
                            /////////////////////////////////////////////////////////////
                            $('#frmDate').datetimepicker({timepicker: false, format: 'm/d/Y',
                                onChangeDateTime: function (dp, $input) {
                                    jQuery('#datetimepicker').datetimepicker('hide');//
                                }});
                            $('#toDate').datetimepicker({timepicker: false, format: 'm/d/Y',
                                onChangeDateTime: function (dp, $input) {
                                    jQuery('#datetimepicker').datetimepicker('hide');
                                }});

                            function exportFinancialReportData() {

                                if ($("#year").val() == '')
                                {
                                    $("#errorDiv").html("Please specify year.");
                                    $("#year").focus();
                                    return false;
                                }
                                if ($("#cycleNo").val() == '')
                                {
                                    $("#errorDiv").html("Please specify Cycle#.");
                                    $("#cycleNo").focus();
                                    return false;
                                }
                                $("#errorDiv").html('');
                                var format = 'pdfView';
                                if ($('#radio-b').is(':checked'))
                                {
                                    format = 'excelView';
                                }
                                window.open('${pageContext.request.contextPath}/ConsumerPortal/exportPdfFinancial?fromDate=' + $("#frmDate").val() + '&toDate=' + $("#toDate").val()
                                        + '&pharmacyId=0&cycle=' + $("#cycleNo").val() + '&format=' + format, "_blank");

                            }



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
                            ////////////////////////////////////////////////////////////
                            var currdate = new Date();
                            var time = (currdate.getMonth() + 1) + '/' + currdate.getDate() + '/' + currdate.getFullYear() + '  ' + (currdate.getHours() + ":" + currdate.getMinutes() + ": " + currdate.getSeconds());
                            // var formatted = $.datepicker.formatDate("DD M d, yy", new Date());
                            document.getElementById("dt").innerHTML = time;
                            function validateField() {
                                var valid = true;
//                                    if ($("#datetimepicker").val() == "") {
//                                        $("#fromDateReq").text("Required");
//                                        $("#fromDateReq").show();
//                                        valid = false;
//                                    }
//                                    if ($("#datetimepicker1").val() == "") {
//                                        $("#toDateReq").text("Required");
//                                        $("#toDateReq").show();
//                                        valid = false;
//                                    }
                                if ($("#datetimepicker").val().length > 0 && $("#datetimepicker1").val().length > 0) {
                                    var fromDate = new Date($('#datetimepicker').val());
                                    var toDate = new Date($('#datetimepicker1').val());
                                    if (toDate <= fromDate) {
                                        $("#toDateReq").text("To date must be greater than from date");
                                        $("#toDateReq").show();
                                        valid = false;
                                    }
                                }
                                return valid;
                            }
                            function enableSearchBtn() {
                                if ($("#datetimepicker").val().length > 0 && $("#datetimepicker1").val().length > 0) {
                                    $("#btnSearch").removeAttr("disabled");
                                } else if ($("#status").val() !== "") {
                                    $("#btnSearch").removeAttr("disabled");
                                } else {
                                    $("#btnSearch").attr("disabled");
                                }
                            }

    </script>
</body>
</html>

