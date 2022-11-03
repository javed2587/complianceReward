<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./portal/inc/head2.jsp" />

    <body class="home_padding secondHeaderBody">
        <jsp:include page="./portal/inc/header2.jsp" />
        <a href="pharmacyQueuePage1.jsp"></a>
        <jsp:include page="multiRxModal2.jsp" />
        <!--Registration Page-->
        <div class="wrapper rxQueueInfoCont">
            <div	 class="customRow clearfix">
            </div>
            <div class="customTableRow clearfix cutome_left"> 
                <input type="checkbox" id="navigation" />
                <label class="cutmobtn" for="navigation">
                </label>
                <div class="tque_left">

                    <div class="left_ticket clearfix">
                        <div class="date_ticket">Mon<br />
                            03-10-17</div>
                        <div class="time_ticker">System Time<br /> 
                            <span>09:02:</span><small>24</small></div>
                    </div>
                    <div class="savePharm">
                        <h4 class="pull-left"> ${PharmacyTitle} </h4><%--<a href="${pageContext.request.contextPath}/PharmacyPortal/profile_edit/${Email}" class="pull-right">Edit</a>--%>
                        <p>  ${Address}</p>
                        <p>  ${Address2}</p><br />
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
                    <div class="rxqueue" style="background-color:#8aca14 ">
                        <p class="caretRight">RX QUEUE </p>
                    </div>
                    <ul class="rx_lft_menu">
                        <li><a href="javascript:;">Reports</a> 

                        </li>
                    </ul>
                    <div class="rxqueue" >
                        <p class="caretRight"> <a href="/PharmacyPortal/careGiverList" style="color:#fff;"  >PENDING CAREGIVER </a> </p>
                    </div>
                    <div class="rxqueue">
                        <p class="caretRight">patient Lookup </p>
                    </div>
                </div>
                <!-- Saimoon New Table-->
                <div class="col-md-12 custome_right rx_newtable">

                    <div class="tableContainer">
                        <div id="popUpException" style="display:none">
                            <span style="color:green"><strong>&nbsp;&nbsp;Record(s) deleted successfully.</strong></span>
                        </div>
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist" id="rowTab">
                            <li id="newRequestTab" role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">New Member Request (${Count})</a></li>
                            <li id="interpretedImageTab" role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">INTERPRETED Images (${ProcessingCount})</a></li>
                            <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Waiting PT Response (${WaitingPtResponseCount})</a></li>
                            <li role="presentation"><a href="#prequest" aria-controls="prequest" role="tab" data-toggle="tab">Processed Requests (${filledCount})</a></li>
                            <li role="presentation"><a href="#shipdelivery" aria-controls="shipdelivery" role="tab" data-toggle="tab">Shipping Delivery (${ProcessedCount})</a></li>
                            <li role="presentation"><a href="#crequest" aria-controls="crequest" role="tab" data-toggle="tab">Cancelled Requests (${CancelledCount})</a></li>
                        </ul>
                        <div class="input-group add-on search_container" style="display: none;">
                            <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                            </div>
                        </div>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="home">
                                <table class="table-striped" id="newRequestTable">
                                    <thead>
                                        <tr>
                                            <th class="size_enter">REQ<br />CONTROL&nbsp;#</th>
                                            <th class="size_enter">REQ<br />POSTED</th>
                                            <th class="size_enter">MBR&nbsp;SVC<br />REQUEST</th>
                                            <th class="size_enter">Current<br />RX&nbsp;STATUS</th>
                                            <th class="size_oneeighty">PATIENT</th>
                                            <th class="mediaction_size size_enter">MEDICATION</th>
                                            <th class="size_enter">DELIVERY<br />SVC</th>
                                            <th class="size_enter">MULTI-RX</th>
                                            
                                            <th class="size_enter">INS</th>
                                            <th class="size_enter">ALLERGY<br />NOTE</th>
                                            <!--                                                <th>ZIP</th>-->
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${listRxTransfer}">
                                            <tr>
                                                <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-' var="date" />
                                                <fmt:formatDate value='${row.receivedDate}' pattern='dd' var="day" />
                                                <fmt:formatDate value='${row.receivedDate}' pattern='HH:mm' var="time" />

                                                <!-- Request Control Number Column -->        
                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: blue" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                <!-- Request Posted Column -->
                                                
                                                
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               >${date}<br><span style="color:red"><strong>${day}</span></strong>@${time}</a>
                                                        </td>
    
                                                <!-- MBR SVC Request  -->    
                                                <c:choose>
                                                    <c:when test="${row.careGiverRequest eq 1 &&
                                                                    row.caregiverRequestApproved eq 0 }">
                                                            <td bgcolor="${row.requestTypeBgColor}" style="color: ${row.textColor}" >
                                                                <a href="<c:url 
                                                                       value="/PharmacyPortal/careGiverDetail/${row.dependentId}?pq=1" />" style="color: ${row.textColor}">
                                                                   ${row.requestType}</a></td>
                                                                </c:when>        
                                                                <c:otherwise>
                                                            <td bgcolor="${row.requestTypeBgColor}" style="color: ${row.textColor}" >${row.requestType}</td>    
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row.status eq 'UNVERIFIED'}">           
                                                        <td style="color: red">${row.status}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>${row.status}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td class="blueText"><a href="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab1">${row.patientName}</a> </td>

                                                <c:choose>
                                                    <c:when test="${row.userInput eq false}">
                                                        <td> <strong>${row.drugNameWithoutStrength}
                                                            
                                                           &nbsp;${row.strength} &nbsp;${row.drugType} </strong>
                                                        <c:if test="${row.brandOnly eq true}" >
                                                                <small style="color:red">*BRAND NAME*</small>
                                                            </c:if>
                                                        </td>
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
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td style="color:red">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>
                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td style="color:blue">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>
                                                    <c:when test="${row.deliveryService eq 'Pick Up at Pharmacy'}">
                                                        <td style="color:blue">${row.deliveryService}<br>

                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq '2nd Day*'}">
                                                        <td style="color:black">${row.deliveryService}</td>
                                                    </c:when>


                                                    <c:otherwise>
                                                        <td style="color:black"><span >${row.deliveryService}


                                                            </span></td>
                                                        </c:otherwise>

                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row.multiRx ne true}">
                                                        <td>${row.multiRxLabel}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red">
                                                            <a href="#" 
                                                            onclick="javascript:window.pharmacyNotification.openMultiRxModel('multiRxModal',
                                                                                ${row.orderId},'${row.patientName}',${row.patientId})"
                                                               style="color:red">
                                                                ${row.multiRxLabel}</a></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                
                                              
                                                
                                               

                                                <c:choose>
                                                    <c:when test="${row.finalPaymentMode eq 'SELF PAY'}">
                                                        <td style="color:blue;" align="center">N</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red;" align="center">Y</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                 <c:choose>
                                                    <c:when test="${row.allergies==''}">
                                                        <td style="color:red;" align="center">N</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:black;" align="center">Y</td>
                                                    </c:otherwise>
                                                </c:choose>
                                            
<!--                                                    <td>${row.zip}</td>-->
                                                
                                            </tr>
                                        </c:forEach>


                                    </tbody>
                                </table>

                            </div>
                            <div role="tabpanel" class="tab-pane" id="profile">
                                <table class="table-striped" id="interpretedIImageTable">
                                    <thead>
                                        <tr>
                                            <th>REQUEST<br />CONTROL&nbsp;NO.</th>
                                            <th>LAST UPDATE&nbsp;POSTED</th>
                                            <th>USER MAKING<br />UPDATE</th>
                                            <th>ORIGINAL REQUEST TYPE</th>
                                            <th>CURRENT PHARMACY&nbsp;STATUS</th>
                                            <th>Svc</th>  
                                            <th class="parent_th">
                                                <table class="full_table" id="subInterpretedIImageTable">
                                                    <tr><th colspan="8" class="interpreted_th">INTERPRETED ENTRY</th></tr>
                                                    <tr>
                                                        <th>PATIENT<br />NAME</th>
                                                        <th>RX&nbsp;NAME</th>
                                                        <th class="">STRENGTH</th>
                                                        <th>DOSAGE<br />TYPE</th>
                                                        <th>QTY.</th>
                                                        <th>DAYS<br />SUPPLY</th>
                                                        <th style="color: red;">RX&nbsp;INGR<br />COST&nbsp;($)</th>
                                                        <th>REFILLS<br />REMAIN</th>
                                                    </tr>
                                                </table>
                                            </th>
                                            <th>RX&nbsp;ORIG<br />DATE</th>
                                            <th>MULTI<br />-RX</th>
                                            <th>REQ&nbsp;SELF<br />
                                                PAY ?</th>

                                        </tr>

                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${listInterpreted}">
                                            <tr>
                                                <fmt:formatDate value='${row.updatedDate}' pattern='yyyy-MM-dd' var="date" />
                                                <fmt:formatDate value='${row.updatedDate}' pattern='HH:mm:ss' var="time" />

                                                <!-- Request Control Number Column -->  

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: blue" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:otherwise>
                                                        </c:choose>

  <!--<td class="blueText">${row.requestControlNumber}</td>-->

                                                <!--                                                    <td><a href="<c:url 
                                                    value="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab2"/>"
                                                style="color: blue">${date}@<strong>${time}</strong></a>
                                         </td>-->

                                                <!-- Last Posted Column -->
                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td class="width-custom"><a href="<c:url 
                                                                                        value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                                                    style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: blue">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:otherwise>

                                                </c:choose>

                                                <td><a href="#" class="blueText">${row.updatedBy}</a></td>

                                                <td>${row.requestType}</td>
                                                <td >
                                                    PENDING REVIEW </td>

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td style="color:red">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>
                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td style="color:blue">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>
                                                    <c:when test="${row.deliveryService eq 'Pick Up at Pharmacy'}">
                                                        <td style="color:blue">${row.deliveryService}<br>

                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq '2nd Day*'}">
                                                        <td style="color:black">${row.deliveryService}</td>
                                                    </c:when>


                                                    <c:otherwise>
                                                        <td style="color:black"><span >${row.deliveryService}


                                                            </span></td>
                                                        </c:otherwise>

                                                </c:choose>        
                                                <td>
                                                    <table class="full_table">
                                                        <tr>
                                                             <!--<td><a href="#" class="blueText">${row.patientName}</a></td>-->

                                                            <!--Patient Name Column-->

                                                            <td class="blueText width-cnt"><a href="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab2">${row.patientName}</a> </td>
                                                            <td class="width-cnt">${row.drugNameWithoutStrength} </td>
                                                            <td class="width-cnt">${row.strength}</td>
                                                            <td class="width-cnt">${row.drugType}</td>
                                                            <td>${row.quantity}</td>
                                                            <td>${row.daysSupply}</td>
                                                            <td class="text_right">${row.rxAcqCostStr}</td>
                                                            <td>${row.refillsRemaining}</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <fmt:formatDate value='${row.rxDate}' pattern='yyyy-MM-dd' var="date" />
                                                <td>${row.rxDate}</td>
                                                <c:choose>
                                                    <c:when test="${row.multiRx ne true}">
                                                        <td>${row.multiRxLabel}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red">${row.multiRxLabel}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row.finalPaymentMode eq 'SELF PAY'}">
                                                        <td style="color:red">${row.finalPaymentMode}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:blue">${row.finalPaymentMode}</td>
                                                    </c:otherwise>
                                                </c:choose>


                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>


                            <input id="loadingTime" type="hidden" value="${loadingTime}" />
                            <div role="tabpanel" class="tab-pane" id="messages">

                                <div>

                                    
                                    <input type="button" class="btn-delete" id="delBtn" value="Delete" disabled
                                           onClick="window.pharmacyNotification.deleteSelectedOrderHandler();"
                                           >
                                    
                                    <input type="button" class="btn-process" id="actionBtn" value="Process" disabled
                                           onClick="window.pharmacyNotification.openSelectedOrders();"
                                           >
                                </div>
                                <table id="waitingPtResponseTbl" class="table-striped">
                                    <thead>
                                        <tr>
                                            <th class="ten_width">&nbsp;</th>
                                            <th>REQUEST<br />CONTROL&nbsp;NO.</th>
                                            <th>LAST<br />UPDATE<br />POSTED</th>
                                            <th>SERVICE<br />REQ.</th>
                                            <th>MULTI<br />-RX</th>
                                            <th>PATIENT&nbsp;NAME</th>
                                            <th>RX&nbsp;NAME</th>
                                            <th>STRENGTH</th>
                                            <th>DOSAGE<br />TYPE</th>
                                            <th>QTY.</th>
                                            <th class="redText">RX&nbsp;INGR<br />COST&nbsp;($)</th>
                                            <th>PATIENT<br />OOP<br />REQ.</th>
                                            <th>UNDER<br />QUOTED<br />PRICE&nbsp;($)</th>
                                            <th class="eighty_width">MEDICATION<br />SPEC&nbsp;MSG</th>
                                            <th>CURRENT<br />STATUS</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${listWaitingPtResponse}">
                                            <tr id="waitingPtResponseTbl_tr_"${row.orderId}>
                                                <td><input id="waitingPtResponseTbl_td_1_"${row.orderId} name="multirxDCB" 
                                                           type="checkbox" value="${row.orderId}" 
                                                           onclick="window.pharmacyNotification.controllingActionButtonForPage1('waitingPtResponseTbl','delBtn')" ></td>
                                                    <fmt:formatDate value='${row.updatedDate}' pattern='yyyy-MM-dd' var="date" />
                                                    <fmt:formatDate value='${row.updatedDate}' pattern='HH:mm:ss' var="time" />

                                                <!-- Request Control Number Column -->  

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: blue" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:otherwise>
                                                        </c:choose>


                                                <!--                                                    <td><a href="<c:url 
                                                    value="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab3"/>"
                                                style="color: blue">${date}@<strong>${time}</strong></a>
                                         </td>-->
                                         <!--<td class="blueText">${row.requestControlNumber}</td>-->

                                                <!-- Last Posted Column -->
                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: blue">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:otherwise>

                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td style="color:red">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td style="color:blue">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq '2nd Day*'}">
                                                        <td style="color:black">${row.deliveryService}</td>
                                                    </c:when>


                                                    <c:otherwise>
                                                        <td style="color:black"><span >${row.deliveryService}


                                                            </span></td>
                                                        </c:otherwise>

                                                </c:choose>


                                                <c:choose>
                                                    <c:when test="${row.multiRx ne true}">
                                                        <td>${row.multiRxLabel}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red">${row.multiRxLabel}</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <!------Patient Name-------->

                                                <td class="blueText"><a href="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab3">${row.patientName}</a> </td>

<!--                                                    <td><a href="#" class="blueText">${row.patientName}</a></td>-->
                                                <td>${row.drugNameWithoutStrength} </td>
                                                <td>${row.strength}</td>
                                                <td>${row.drugType}</td>
                                                <td>${row.quantity}</td>
                                                <td class="text_right">${row.rxAcqCostStr}</td>
                                                <td class="text_right">${row.finalCopayStr}</td>
                                                <td class="text_right">${row.underQuotedPriceStr}</td>
                                                <td  class="medication_spec">${row.medicationSpecMsg}</td>
                                                <td>${row.patientResponse}</td>


                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                                </table>

                            </div>


                            <div role="tabpanel" class="tab-pane" id="prequest">
                                <table id="processRequestTbl" class="table-striped">
                                    <thead>
                                        <tr>
                                            <th>REQUEST<br />CONTROL&nbsp;NO.</th>
                                            <th>STATUS<br />POSTED</th>
                                            <th>SERVICE<br />REQ.</th>
                                            <th>MULTI<br />-RX</th>
                                            <th>RX#</th>
                                            <th>PATIENT&nbsp;NAME</th>
                                            <th>RX&nbsp;PROCESSED</th>
                                            <th>DELIVERY<br />ADDRESS</th>
                                            <th>ZIP</th>
                                            <th class="premium_th">PREMIUM<br />DELIVERY<br />MILES</th>
                                            <th class="premium_th">PREMIUM<br />DELIVERY<br />FEE&nbsp;($)</th>
                                            <th class="premium_th">PICK&nbsp;UP<br />PAPER<br />RX&nbsp;?</th>
                                            <th class="premium_th">PT&nbsp;REQSTD<br />TIME<br />WINDOW</th>
                                            <th class="premium_th">PATIENT<br />SPECIAL<br />INST.</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${listRxFilled}">
                                            <tr>

                                                <fmt:formatDate value='${row.updatedDate}' pattern='yyyy-MM-dd' var="date" />
                                                <fmt:formatDate value='${row.updatedDate}' pattern='HH:mm:ss' var="time" />

                                                <!-- Request Control Number Column -->  

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: blue" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:otherwise>
                                                        </c:choose>

                                                <!--                                                    <td><a href="<c:url 
                                                    value="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab4"/>"
                                                style="color: blue">${date}@<strong>${time}</strong></a>
                                         </td>-->
                                         <!--<td class="blueText">${row.requestControlNumber}</td>-->

                                                <!-- Last Posted Column -->
                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: blue">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: blue">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:otherwise>

                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td style="color:red">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td style="color:blue">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq '2nd Day*'}">
                                                        <td style="color:black">${row.deliveryService}</td>
                                                    </c:when>


                                                    <c:otherwise>
                                                        <td style="color:black"><span >${row.deliveryService}


                                                            </span></td>
                                                        </c:otherwise>

                                                </c:choose>


                                                <c:choose>
                                                    <c:when test="${row.multiRx ne true}">
                                                        <td>${row.multiRxLabel}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red">${row.multiRxLabel}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>${row.systemGeneratedRxNumber}</td>        
                                                <!------Patient Name-------->

                                                <td class="blueText"><a href="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab4">${row.patientName}</a> </td>
                                                <!--<td><a href="#" class="blueText">${row.patientName}</a></td>-->
                                                <td>${row.drugNameWithoutStrength} </td>
                                                <td>${row.deliveryAddress}</td>
                                                <td>${row.zip}</td>
                                                <td class="redText">${row.miles}</td>
                                                <td class="text_right">$<fmt:formatNumber value="${row.handlingFee}" pattern="0.00"/></td>
                                                <td>-</td>

                                                <c:choose>
                                                    <c:when test="${row.deliverycarrier ne ''}">
                                                        <td style="color:red">${row.deliverycarrier}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red">${row.deliveryFrom}-${row.deliveryTo}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td class="blueText">&nbsp;</td>

                                            </tr>



                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>



                            <div role="tabpanel" class="tab-pane" id="shipdelivery">
                                <table id="shippingDeliveryTbl" class="table-striped">
                                    <thead>
                                        <tr>
                                            <th>REQUEST<br />CONTROL&nbsp;NO.</th>
                                            <th>STATUS<br />POSTED</th>
                                            <th>SERVICE<br />REQ.</th>
                                            <th>MULTI<br />-RX</th>
                                            <th>PATIENT&nbsp;NAME</th>
                                            <th class="blueText">RX&nbsp;PROCESSED</th>
                                            <th class="redText">RX&nbsp;INGR<br />COST&nbsp;($)</th>
                                            <th>TOTAL<br />Rx&nbsp;SELLING<br />PRICE&nbsp;($)</th>
                                            <th>INS&nbsp;/<br />SELF<br />PAY</th>
                                            <th>PATIENT<br />OOP<br />FINAL&nbsp;($)</th>
                                            <th>DELIVERY<br />METHOD</th>
                                            <th>TRACKING<br />NUMBER</th>
                                            <th>ZIP</th>
                                            <th class="premium_th">PREMIUM<br />DELIVERY<br /><span class="redText">MILES</span></th>
                                            <th class="premium_th">PREMIUM<br />DELIVERY<br />FEE&nbsp;($)</th>
                                            <th class="premium_th">PICK&nbsp;UP<br />PAPER<br />RX&nbsp;?</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${listRxProcessed}">
                                            <tr>
                                                <fmt:formatDate value='${row.updatedDate}' pattern='yyyy-MM-dd' var="date" />
                                                <fmt:formatDate value='${row.updatedDate}' pattern='HH:mm:ss' var="time" />

                                                <!-- Request Control Number Column -->  

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: blue" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:otherwise>
                                                        </c:choose>

                                                <!--                                                    <td><a href="<c:url 
                                                    value="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab5"/>"
                                                style="color: blue">${date}@<strong>${time}</strong></a>
                                         </td>-->
                                         <!--<td class="blueText">${row.requestControlNumber}</td>-->
                                                <!-- Last Posted Column -->
                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: blue">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:otherwise>

                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td style="color:red">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td style="color:blue">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq '2nd Day*'}">
                                                        <td style="color:black">${row.deliveryService}</td>
                                                    </c:when>


                                                    <c:otherwise>
                                                        <td style="color:black"><span >${row.deliveryService}


                                                            </span></td>
                                                        </c:otherwise>

                                                </c:choose>


                                                <c:choose>
                                                    <c:when test="${row.multiRx ne true}">
                                                        <td>${row.multiRxLabel}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red">${row.multiRxLabel}</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <!------Patient Name-------->

                                                <td class="blueText"><a href="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab5">${row.patientName}</a> </td>

<!--<td><a href="#" class="blueText">${row.patientName}</a></td>-->
                                                <td>${row.drugNameWithoutStrength} </td>
                                                <td class="text_right">${row.rxAcqCostStr}</td>
                                                <td class="text_right">${row.sellingPriceStr}</td>
                                                <c:choose>
                                                    <c:when test="${row.finalPaymentMode eq 'SELF PAY'}">
                                                        <td style="color:red">${row.finalPaymentMode}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:blue">${row.finalPaymentMode}</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <td class="text_right">${row.originalPtCopayStr}</td>
                                                <c:choose>
                                                    <c:when test="${row.deliverycarrier ne ''}">
                                                        <td style="color:red">${row.deliverycarrier}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:red">${row.deliveryFrom}-${row.deliveryTo}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td align="center">${row.tracking}</td>
                                                <td>${row.zip}</td>
                                                <td align="center">${row.miles}</td>
                                                <td class="text_right">$<fmt:formatNumber value="${row.handlingFee}" pattern="0.00"/></td>
                                                <td>-</td>

                                            </tr>


                                        </c:forEach> 

                                    </tbody>
                                </table>

                            </div>






                            <div role="tabpanel" class="tab-pane" id="crequest">
                                <table id="cancelledRequestTbl" class="table-striped">
                                    <thead>
                                        <tr>
                                            <th>REQUEST<br />CONTROL&nbsp;NO.</th>
                                            <th>STATUS<br />POSTED</th>
                                            <th>SERVICE<br />REQ.</th>
                                            <th>PATIENT&nbsp;NAME</th>
                                            <th>MEMBER /<br />DEPENDANT</th>
                                            <th>PHARMACY<br />RESPONSE</th>
                                            <th>RX&nbsp;STATUS</th>
                                            <th>MEDICATION</th>
                                            <th>STRENGTH</th>
                                            <th>QTY.</th>
                                            <th>INS/<br />SELF&nbsp;PAY</th>
                                            <th>PUBLIC<br />INS</th>
                                            <th>DELIVERY<br />SVC REQ.</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${listCancelled}">
                                            <tr>
                                                <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd' var="date" />
                                                <fmt:formatDate value='${row.receivedDate}' pattern='HH:mm:ss' var="time" />

                                                <!-- Request Control Number Column -->  

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: red" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                        <td><a href="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}" style="color: blue" >${row.requestControlNumber1} <br> ${row.requestControlNumber2}</a></td>
                                                            </c:otherwise>
                                                        </c:choose>


                                                <!--                                                    <td><a href="<c:url 
                                                    value="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab6"/>"
                                                style="color: blue">${date}@<strong>${time}</strong></a>
                                         </td>-->
                                                <!-- Last Posted Column -->
                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <td><a href="<c:url 
                                                                   value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"
                                                               style="color: blue">${date}@<strong>${time}</strong></a>
                                                        </td>
                                                    </c:otherwise>

                                                </c:choose>
                                                <!--<td><a href="#" class="redText">${row.requestControlNumber}</a></td>-->
                                                <td class="redText">CANCEL<br />FILL REQUEST</td>
                                                <!--<td><a href="#" class="blueText">${row.patientName}</a></td>-->

                                                <!------Patient Name-------->

                                                <td class="blueText"><a href="/ConsumerPortal/queuePatientDetailPage/${row.patientId}/tab6">${row.patientName}</a> </td>
                                                <td>MEMBER</td>
                                                <td>REQUEST<br />PROCESSED</td>
                                                <td>REQUEST<br />CANCELLED</td>
                                                <td class="redText">VIBRA TAB<br />(doxycycline Hycl)</td>
                                                <td class="redText">${row.strength}</td>

                                                <td class="redText">${row.quantity}</td>
                                                <c:choose>
                                                    <c:when test="${row.finalPaymentMode eq 'SELF PAY'}">
                                                        <td style="color:red">${row.finalPaymentMode}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color:blue">${row.finalPaymentMode}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>N</td>

                                                <c:choose>
                                                    <c:when test="${row.deliveryService eq 'Same Day'}">
                                                        <td style="color:red">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq 'Next Day*'}">
                                                        <td style="color:blue">${row.deliveryService}<br>
                                                            <span style="color:red"><i class="fa fa-exclamation" aria-hidden="true"></i></span>
                                                        </td>
                                                    </c:when>

                                                    <c:when test="${row.deliveryService eq '2nd Day*'}">
                                                        <td style="color:black">${row.deliveryService}</td>
                                                    </c:when>


                                                    <c:otherwise>
                                                        <td style="color:black"><span >${row.deliveryService}


                                                            </span></td>
                                                        </c:otherwise>

                                                </c:choose>

                                            </tr>

                                        </c:forEach>  
                                    </tbody>
                                </table>

                            </div>



                        </div>

                    </div>

                </div>
                <!-- Saimoon New Table-->

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

<form action="/PharmacyPortal/successSignin" method="get" id="form" name="form">
    <a id="backLnk" onclick="document.getElementById('form').submit();
            return false;" class="btn btn_one" style="display:none"></a>  

</form>   
<form action="/ConsumerPortal/rxDetailHandler" method="get" id="form2" name="form2">
    <a id="fwdLnk" onclick="document.getElementById('form2').submit();
            return false;" ></a>  

    <input type="hidden" id="itemIds" name="itemIds" value="" />
    <input id="orderId" name="orderId" type="hidden" value=""/>  
</form>   

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
                                    <p class="clearfix"><span>Do you want to delete this order?</span>

                                    </p>
                                    <div>
                                        <input id="confirmBtn" type="button" class="btn back_btn" value="OK" 
                                               onclick="window.pharmacyNotification.deleteSelectedOrder();" 
                                               style="width: 80px; vertical-align: middle;"> 

                                        <button id="cancelConfirmBoxBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 80px; vertical-align: middle;">Cancel</button>
                                    </div>

                                </div>
                            </div>
                        </div>           
                    </div>
                </div>
</div>

<!--/Financial Report-->



<!--Drug Modal Saimoon-->
<jsp:include page="./portal/drugestimateprice.jsp" />
<jsp:include page="./portal/addNewRxDiv.jsp" />   
<!--/Drug Modal Saimoon-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
<script>

        $(document).ready(function ( ) {
            $('#newRequestTable').dataTable({"sPaginationType": "full_numbers",
                "aoColumnDefs": [
                    {'bSortable': false, 'aTargets': [6]}
                ],
                "aaSorting": [[4, "asc"]],
//                                "scrollX": true,
//                                "scrollY": "400"
            });

            $('#interpretedIImageTable').dataTable({"sPaginationType": "full_numbers",
                "aoColumnDefs": [
                    {'bSortable': false, 'aTargets': [4, 5, 6]}
                ],
                "scrollX": true,
                "scrollY": "400"
            });

            $('#waitingPtResponseTbl').dataTable({"sPaginationType": "full_numbers",
                "aoColumnDefs": [
                    {'bSortable': false, 'aTargets': [0]}
                ],
                "aaSorting": [[4, "asc"]],
            });
            $('#processRequestTbl').dataTable({"sPaginationType": "full_numbers",
//                                "aoColumnDefs": [
//                                    {'bSortable': false, 'aTargets': [5, 6]}
//                                ],
                "aaSorting": [[4, "asc"]],
            });

            $('#shippingDeliveryTbl').dataTable({"sPaginationType": "full_numbers",
                "aoColumnDefs": [
                    {'bSortable': false, 'aTargets': [5, 6]}
                ],
                "aaSorting": [[4, "asc"]],
            });

            $('#cancelledRequestTbl').dataTable({"sPaginationType": "full_numbers",
                "aoColumnDefs": [
                    {'bSortable': false, 'aTargets': [5, 6]}
                ],
                "aaSorting": [[3, "asc"]],
            });
            //$("#example_wrapper > .dataTables_filter").appendTo("div.search_tab_f");
            //$("#example_wrapper > .dataTables_length").appendTo("div.sorting_tab_f"); 

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
            selectedTab();
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

        function selectedTab() {
            var selectedTab = '${sessionBeanPortal.selectedTab}';
            if (selectedTab == 'tab2') {
                $('#home').attr("class", "tab-pane");
                $('#rowTab > :nth-child(2)').tab('show');
                $('#profile').attr("class", "tab-pane active");
            } else if (selectedTab == 'tab3') {
                $('#home').attr("class", "tab-pane");
                $('#rowTab > :nth-child(3)').tab('show');
                $('#messages').attr("class", "tab-pane active");
            } else if (selectedTab == 'tab4') {
                $('#home').attr("class", "tab-pane");
                $('#rowTab > :nth-child(4)').tab('show');
                $('#prequest').attr("class", "tab-pane active");
            } else if (selectedTab == 'tab5') {
                $('#home').attr("class", "tab-pane");
                $('#rowTab > :nth-child(5)').tab('show');
                $('#shipdelivery').attr("class", "tab-pane active");
            } else if (selectedTab == 'tab6') {
                $('#home').attr("class", "tab-pane");
                $('#rowTab > :nth-child(6)').tab('show');
                $('#crequest').attr("class", "tab-pane active");
            }
        }

</script>
</body>
</html>

