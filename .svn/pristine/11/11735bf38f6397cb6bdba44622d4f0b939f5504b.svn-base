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
        <!--Registration Page-->
        <div class="row">
            <div class="wrapper rxQueueInfoCont">
                <div	 class="customRow clearfix">
                    <div class="col-sm-4 ">
                        <div class="numberDiv">
                            <span>0h 41m</span>
                        </div>
                        <div class="textDetail">
                            <span>THE AVERAGE TIME IT TAKES YOUR
                                PHARMACY TO PROCESS AN RX</span>
                        </div>
                    </div>
                    <div class="col-sm-3 ">
                        <div class="numberDiv lessWidthNumber">
                            <span>37</span>
                        </div>
                        <div class="textDetail">
                            <span>THE AVERAGE NUMBER OF PRESCRIPTIONS PROCESSED EACH DAY</span>
                        </div>
                    </div>
                    <div class="col-sm-3 ">
                        <div class="numberDiv ">
                            <span>42%</span>
                        </div>
                        <div class="textDetail">
                            <span>THE % OF NEW PRESRIPTIONS
                                PROCESSED BY YOU THIS WEEK</span>
                        </div>
                    </div>
                    <div class="col-sm-2 ">
                        <!--<div class="dateInfoDiv" id="dt"> wrapping time inside span so id "dt" is allocated to inner span-->
                        <div class="dateInfoDiv">
                            <span id="dt">

                            </span>
                        </div>
                    </div>
                </div>
                <div class="customTableRow clearfix cutome_left">
                    <input type="checkbox" id="navigation" />
                    <label class="cutmobtn" for="navigation">
                    </label>
                    <div class="tque_left">
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
                            <p class="caretRight"><a href="/PharmacyPortal/newMemberRequest/">RX QUEUE </a></p>
                        </div>


                        <ul class="rx_lft_menu">
                            <li><a href="javascript:;">Reports</a> 

                            </li>
                        </ul>
                                <div class="rxqueue" style="background-color:#3582b3; color: #fff !important; ">
                            <p class="caretRight"> <a style="color: #fff;" href="/PharmacyPortal/careGiverList">PENDING CAREGIVER </a> </p>
                        </div>
                        <div class="rxqueue">
                            <p class="caretRight">patient Lookup </p>
                        </div>

                    </div>
                    <div class="col-md-12 custome_right">

                        <div class="tableContainer">

                            <!-- Nav tabs -->
                            <div class="table_headh clearfix">
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" ${tab1}><a href="#home" aria-controls="home" role="tab" data-toggle="tab">NEW & TRANSFER REQUESTS (${Count})</a></li>
                                    <li role="presentation" ${tab2}><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">In Process Rx (${ProcessingCount})</a></li>
                                    <li role="presentation" ${tab3}><a href="#filled" aria-controls="profile" role="tab" data-toggle="tab">Filled/Completed Rx (${filledCount})</a></li>
                                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Shipped Rx (${ProcessedCount})</a></li>
                                    <li role="presentation"><a href="#cancel" aria-controls="cancel" role="tab" data-toggle="tab">Cancelled (${CancelledCount})</a></li>

                                    <!--
                                    <li role="presentation" ${tab4}><a href="#onhold" aria-controls="profile" role="tab" data-toggle="tab">On Hold Rx (${holdCount})</a></li>
                                    -->

                                </ul>
                                <button type="button" class="btn btn-primary btn_estimate" data-toggle="modal" data-target="#Drug_ind">Estimate Price</button>
                                <div class="search_tab_f"></div>
                                <div class="sorting_tab_f"></div>
                            </div>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane ${recordDiv1}" id="home">



                                    <!--                            <div class="rxquetablewerap">-->
                                    <table  id="example" class="display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Received</th>
                                                <th>Order#</th>
                                                <th>Same Day</th>
                                                <th>Patient&nbsp;Name</th>
                                                <th style="padding-right:49px !important;">DOB</th>
                                                <th>Gender</th>
                                                <th>Allergies<br>Noted</th>
                                                <th>INS CARD ON FILE</th>
                                                <th>CASH PAY</th>
                                                <th>DRUG NAME LOOKED UP</th>
                                                <th>Video/ Image Uploaded</th>
                                                <th>Qty</th>
                                                <th>PRICE QUOTED</th>
                                                <th>Delivery<br>Zip<br>Code</th>
                                                <th>Multi Rx</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="row" items="${listRxTransfer}">
                                                <tr>
                                                    <td>
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd' var="date" />
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='HH:mm:ss' var="time" />
                                                        <c:set var="disable" value="${row.disabled}"/>
                                                        <%--  <c:choose>
                                                    
                                                        <c:when test="${1 == disable}">
                                                            <a href="javascript:alert('This record is locked since it is already opened by someone else.');"    style="color: grey">${date}@<strong>${time}</strong></a>
                                                        </c:when>
                                                        <c:otherwise>--%>
                                                        <%--  <form action="${pageContext.request.contextPath}/ConsumerPortal/transferRxList/${row.transferDetailId}/${row.patientId}" method="post" id="form" name="form">
                                                              <a href=""  onclick="document.getElementById('form').submit(); return false;"  style="color: blue">${date}</a>
                                                             </form> --%>
                                                        <%--   <c:set var="transferDetailId" value="${row.transferDetailId}" /> --%>
                                                        <c:set var="orderId" value="${row.orderId}" />
                                                        <%--  <a href="<c:url value="/ConsumerPortal/transferRxList/${transferDetailId==0||transferDetailId==null ?0:transferDetailId}/${row.rxId}/${orderId==0||orderId==null ?0:orderId}/${row.patientId}"/>"  style="color: blue">${date}</a> --%>
                                                        <a href="<c:url 
                                                               value="/ConsumerPortal/patientHistory/${row.patientId}"/>"
                                                           style="color: blue">${date}@<strong>${time}</strong></a>
                                                        <%-- </c:otherwise>
                                                       </c:choose>--%>

                                                    </td>
                                                    <td> <a href="<c:url 
                                                                value="/ConsumerPortal/patientHistory/${row.patientId}"/>"
                                                            style="color: blue">${orderId}</a></td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${row.sameDayDelivery==true}">
                                                                <span style="color:red; font-size:30px"><strong>!</strong></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                &nbsp;
                                                            </c:otherwise>
                                                        </c:choose>    
                                                    </td>
                                                    <td><a href="<c:url 
                                                               value="/ConsumerPortal/patientHistory/${row.patientId}"/>"
                                                           style="color: blue">${row.patientName}</a></td>
                                                    <td>${row.patientDOB}</td> 
                                                    <td>${row.gender}</td>
                                                    <!----------------------------------------------------->
                                                    <c:choose>
                                                        <c:when test="${row.allergies==''}">
                                                            <td style="color:red;">NO</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:gray;">YES</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!----------------------------------------------------->
                                                    <c:set var="hasRxCard" value="${row.hasRxCard}"/>
                                                    <c:choose>
                                                        <c:when test="${'yes' == hasRxCard}">
                                                            <td style="color:gray;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:red;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row.payCash==false}">
                                                            <td style="padding-right:133px;color:gray;">
                                                                NO
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:red;">YES</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>
                                                        ${row.rxSearched}
                                                    </td>


                                                    <!--------------------------------------------------------->
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:set var="imgs" value="${row.transferImage}"   />
                                                    <c:choose>
                                                        <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                            <td><a href="#"><i class="fa fa-play" style="color:grey;"></i></a></td>
                                                                </c:when>

                                                        <c:when test="${imgs!=null && imgs ne ''}">
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" 
                                                                   data-toggle="modal"></a>

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <img src="${imgs}"/>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:when>

                                                        <c:otherwise>
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <video id="videoContainer"   width="320" height="176" controls preload="none" >
                                                                                    <source src="${row.ptVideo}" type="video/mp4">
                                                                                </video>
                                                                                <%-- <c:out value="${row.ptVideo}"></c:out> --%>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-------------------------------------------------------->
                                                    <td >
                                                        ${row.quantity}
                                                    </td>
                                                    <td style="padding-left:19px">
                                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${row.cashPriceQuated}"/>
                                                    </td>
                                                    <!-------------------------------------------------------->
                                                    <td>${row.zip}</td>
                                                    <c:choose>
                                                        <c:when test="${row.multiRx==true}">
                                                            <td style="color:red;">YES</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:gray;">NO</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-------------------------------------------------------->


                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <!--                            </div>-->



                                    <!-- <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <li>
                                                <a href="#" aria-label="Previous">
                                                    <span aria-hidden="true"><i class="fa fa-caret-left"></i></span>
                                                </a>
                                            </li>
                                            <li class="active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#">5</a></li>
                                            <li>
                                                <a href="#" aria-label="Next">
                                                    <span aria-hidden="true"><i class="fa fa-caret-right"></i></span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav> -->
                                </div>
                                <div role="tabpanel" class="tab-pane ${recordDiv3}" id="profile">
                                    <table class="display" id="profileexample">
                                        <thead>
                                            <tr>
                                                <th>Received</th>
                                                <th>Patient Name</th>
                                                <th>DOB</th>
                                                <th>Has Rx Ins Cards?</th>
                                                <th>Rx Searched</th>
                                                <th>Pt Video</th>
                                                <th>Qty</th>
                                                <th>Cash Pr. Quoted</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="row" items="${listRxProcessed}">
                                                <tr>
                                                    <td >
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss' var="date" />
                                                        <c:set var="disable" value="${row.disabled}"/>


                                                        <c:set var="orderId" value="${row.orderId}" />
                                                        <%--  <a href="<c:url value="/ConsumerPortal/transferRxList/${transferDetailId==0||transferDetailId==null ?0:transferDetailId}/${row.rxId}/${orderId==0||orderId==null ?0:orderId}/${row.patientId}"/>"  style="color: blue">${date}</a> --%>
                                                        <a href="<c:url 
                                                               value="/ConsumerPortal/patientHistory/${row.patientId}"/>"
                                                           style="color: blue">${date}@<strong>${time} </strong></a>


                                                    </td>
                                                    <td>
                                                        ${row.patientName}
                                                    </td>

                                                    <td>
                                                        ${row.patientDOB}
                                                    </td>
                                                    <c:set var="hasRxCard" value="${row.hasRxCard}"/>
                                                    <c:choose>
                                                        <c:when test="${'yes' == hasRxCard}">
                                                            <td style="padding-right:133px;color:green;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:red;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>
                                                        ${row.rxSearched}
                                                    </td>
                                                    <!--------------------------------------------------------->
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:set var="imgs" value="${row.transferImage}"   />
                                                    <c:choose>
                                                        <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                            <td><a href="#"><i class="fa fa-play" style="color:grey;"></i></a></td>
                                                                </c:when>

                                                        <c:when test="${imgs!=null && imgs ne ''}">
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" 
                                                                   data-toggle="modal"></a>

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <img src="${imgs}"/>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <video id="videoContainer"   width="320" height="176" controls preload="none" >
                                                                                    <source src="${row.ptVideo}" type="video/mp4">
                                                                                </video>
                                                                                <%-- <c:out value="${row.ptVideo}"></c:out> --%>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-------------------------------------------------------->
                                                    <td >
                                                        ${row.quantity}
                                                    </td>
                                                    <td style="padding-left:19px">
                                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${row.cashPriceQuated}"/>
                                                    </td>


                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>  
                                </div>
                                <div role="tabpanel" class="tab-pane ${recordDiv2}" id="messages">
                                    <table class="table-striped" id="messagesexample">
                                        <thead>
                                            <tr>
                                                <th>Received</th>
                                                <th>Patient Name</th>
                                                <th>DOB</th>
                                                <th>Has Rx Ins Cards?</th>
                                                <th>Rx Searched</th>
                                                <th>Pt Video</th>
                                                <th>Qty</th>
                                                <th>Cash Pr. Quoted</th>
                                                <!--
                                                 <th sort=false>View Status <a href="#" data-toggle="modal" data-target="#statusModal"><img src="${pageContext.request.contextPath}/resources/images/question_11.png" alt="status" /></a></th>
                                                --> </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="row" items="${listRxProcessing}">
                                                <tr>
                                                    <td >
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd' var="date" />
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='HH:mm:ss' var="time" />
                                                        <c:set var="disable" value="${row.disabled}"/>

                                                        <c:set var="orderId" value="${row.orderId}" />

                                                        <a href="<c:url 
                                                               value="/ConsumerPortal/patientHistory/${row.patientId}"/>"
                                                           style="color: blue">${date}@<strong>${time}</a>


                                                    </td>
                                                    <td>
                                                        ${row.patientName}
                                                    </td>

                                                    <td>
                                                        ${row.patientDOB}
                                                    </td>
                                                    <c:set var="hasRxCard" value="${row.hasRxCard}"/>
                                                    <c:choose>
                                                        <c:when test="${'yes' == hasRxCard}">
                                                            <td style="padding-right:133px;color:green;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:red;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>
                                                        ${row.rxSearched}
                                                    </td>
                                                    <!--------------------------------------------------------->
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:set var="imgs" value="${row.transferImage}"   />
                                                    <c:choose>
                                                        <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                            <td><a href="#"><i class="fa fa-play" style="color:grey;"></i></a></td>
                                                                </c:when>

                                                        <c:when test="${imgs!=null && imgs ne ''}">
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" 
                                                                   data-toggle="modal"></a>

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <img src="${imgs}"/>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <video id="videoContainer"   width="320" height="176" controls preload="none" >
                                                                                    <source src="${row.ptVideo}" type="video/mp4">
                                                                                </video>
                                                                                <%-- <c:out value="${row.ptVideo}"></c:out> --%>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-------------------------------------------------------->
                                                    <td >
                                                        ${row.quantity}
                                                    </td>
                                                    <td style="padding-left:19px">
                                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${row.cashPriceQuated}"/>
                                                    </td>


                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table> 
                                </div>
                                <!---------------------------------------------------------------------------------------->
                                <div role="tabpanel" class="tab-pane" id="filled">
                                    <table class="table-striped" id="messagesexample">
                                        <thead>
                                            <tr>
                                                <th>Received</th>
                                                <th>Patient Name</th>
                                                <th>DOB</th>
                                                <th>Has Rx Ins Cards?</th>
                                                <th>Rx Searched</th>
                                                <th>Pt Video</th>
                                                <th>Qty</th>
                                                <th>Cash Pr. Quoted</th>
                                                <!--
                                                 <th sort=false>View Status <a href="#" data-toggle="modal" data-target="#statusModal"><img src="${pageContext.request.contextPath}/resources/images/question_11.png" alt="status" /></a></th>
                                                --> </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="row" items="${listRxFilled}">
                                                <tr>
                                                    <td >
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss' var="date" />
                                                        <c:set var="disable" value="${row.disabled}"/>

                                                        <c:set var="orderId" value="${row.orderId}" />

                                                        <a href="<c:url 
                                                               value="/ConsumerPortal/patientHistory/${row.patientId}"/>"
                                                           style="color: blue">${date}@<strong>${time}</strong></a>


                                                    </td>
                                                    <td>
                                                        ${row.patientName}
                                                    </td>

                                                    <td>
                                                        ${row.patientDOB}
                                                    </td>
                                                    <c:set var="hasRxCard" value="${row.hasRxCard}"/>
                                                    <c:choose>
                                                        <c:when test="${'yes' == hasRxCard}">
                                                            <td style="padding-right:133px;color:green;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:red;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>
                                                        ${row.rxSearched}
                                                    </td>
                                                    <!--------------------------------------------------------->
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:set var="imgs" value="${row.transferImage}"   />
                                                    <c:choose>
                                                        <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                            <td><a href="#"><i class="fa fa-play" style="color:grey;"></i></a></td>
                                                                </c:when>

                                                        <c:when test="${imgs!=null && imgs ne ''}">
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" 
                                                                   data-toggle="modal"></a>

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <img src="${imgs}"/>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <video id="videoContainer"   width="320" height="176" controls preload="none" >
                                                                                    <source src="${row.ptVideo}" type="video/mp4">
                                                                                </video>
                                                                                <%-- <c:out value="${row.ptVideo}"></c:out> --%>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-------------------------------------------------------->
                                                    <td >
                                                        ${row.quantity}
                                                    </td>
                                                    <td style="padding-left:19px">
                                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${row.cashPriceQuated}"/>
                                                    </td>


                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table> 
                                </div>
                                <!------------------------------------------------------------------------------------------------------>
                                <div role="tabpanel" class="tab-pane ${recordDiv4}" id="onhold">
                                    <table class="display" id="profileexample">
                                        <thead>
                                            <tr>
                                                <th>Received</th>
                                                <th>Patient Name</th>
                                                <th>DOB</th>
                                                <th>Has Rx Ins Cards?</th>
                                                <th>Rx Searched</th>
                                                <th>Pt Video</th>
                                                <th>Qty</th>
                                                <th>Cash Pr. Quoted</th>
                                                <th sort=false>View Status <a href="#" data-toggle="modal" data-target="#statusModal"><img src="${pageContext.request.contextPath}/resources/images/question_11.png" alt="status" /></a></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="row" items="${listHold}">
                                                <tr>
                                                    <td >
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss' var="date" />
                                                        <c:set var="disable" value="${row.disabled}"/>
                                                        <c:choose>

                                                            <c:when test="${1 == disable}">
                                                                <a href="#!"    style="color: grey">${date}</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <%--  <form action="${pageContext.request.contextPath}/ConsumerPortal/transferRxList/${row.transferDetailId}/${row.patientId}" method="post" id="form" name="form">
                                                                      <a href=""  onclick="document.getElementById('form').submit(); return false;"  style="color: blue">${date}</a>
                                                                     </form> --%>
                                                                <%--   <c:set var="transferDetailId" value="${row.transferDetailId}" /> --%>
                                                                <c:set var="orderId" value="${row.orderId}" />
                                                                <%--  <a href="<c:url value="/ConsumerPortal/transferRxList/${transferDetailId==0||transferDetailId==null ?0:transferDetailId}/${row.rxId}/${orderId==0||orderId==null ?0:orderId}/${row.patientId}"/>"  style="color: blue">${date}</a> --%>
                                                                <a href="<c:url value="/ConsumerPortal/transferRxList/${orderId==0||orderId==null ?0:orderId}/${row.patientId}?status=On Hold"/>"  style="color: blue">${date}</a>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td>
                                                        ${row.patientName}
                                                    </td>

                                                    <td>
                                                        ${row.patientDOB}
                                                    </td>
                                                    <c:set var="hasRxCard" value="${row.hasRxCard}"/>
                                                    <c:choose>
                                                        <c:when test="${'yes' == hasRxCard}">
                                                            <td style="padding-right:133px;color:green;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:red;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>
                                                        ${row.rxSearched}
                                                    </td>
                                                    <!--------------------------------------------------------->
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:choose>
                                                        <c:when test="${ vid== null || vid ==''}">
                                                            <td><a href="#"><i class="fa fa-play" style="color:grey;"></i></a></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <video id="videoContainer"   width="320" height="176" controls preload="none" >
                                                                                    <source src="${row.ptVideo}" type="video/mp4">
                                                                                </video>
                                                                                <%-- <c:out value="${row.ptVideo}"></c:out> --%>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-------------------------------------------------------->
                                                    <td >
                                                        ${row.quantity}
                                                    </td>
                                                    <td style="padding-left:19px">
                                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${row.cashPriceQuated}"/>
                                                    </td>
                                                    <td style="color:orange;">
                                                        ${row.status}
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>  
                                </div>

                                <div role="tabpanel" class="tab-pane" id="cancel">
                                    <table class="display" id="cancelexample">
                                        <thead>
                                            <tr>
                                                <th>Received</th>
                                                <th>Patient Name</th>
                                                <th>DOB</th>
                                                <th>Has Rx Ins Cards?</th>
                                                <th>Rx Searched</th>
                                                <th>Pt Video</th>
                                                <th>Qty</th>
                                                <th>Cash Pr. Quoted</th>
                                               <!--<th sort=false>View Status <a href="#" data-toggle="modal" data-target="#statusModal"><img src="${pageContext.request.contextPath}/resources/images/question_11.png" alt="status" /></a></th>-->
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="row" items="${listCancelled}">
                                                <tr>
                                                    <td >
                                                        <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss' var="date" />
                                                        <c:set var="disable" value="${row.disabled}"/>
                                                        <c:choose>

                                                            <c:when test="${1 == disable}">
                                                                <a href="#!"    style="color: grey">${date}</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <%--  <form action="${pageContext.request.contextPath}/ConsumerPortal/transferRxList/${row.transferDetailId}/${row.patientId}" method="post" id="form" name="form">
                                                                      <a href=""  onclick="document.getElementById('form').submit(); return false;"  style="color: blue">${date}</a>
                                                                     </form> --%>
                                                                <%--   <c:set var="transferDetailId" value="${row.transferDetailId}" /> --%>
                                                                <c:set var="orderId" value="${row.orderId}" />
                                                                <%--  <a href="<c:url value="/ConsumerPortal/transferRxList/${transferDetailId==0||transferDetailId==null ?0:transferDetailId}/${row.rxId}/${orderId==0||orderId==null ?0:orderId}/${row.patientId}"/>"  style="color: blue">${date}</a> --%>
                                                                <a href="<c:url 
                                                                       value="/ConsumerPortal/patientHistory/${row.patientId}"/>"
                                                                   style="color: blue">${date}@<strong>${time}</a>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td>
                                                        ${row.patientName}
                                                    </td>

                                                    <td>
                                                        ${row.patientDOB}
                                                    </td>
                                                    <c:set var="hasRxCard" value="${row.hasRxCard}"/>
                                                    <c:choose>
                                                        <c:when test="${'yes' == hasRxCard}">
                                                            <td style="padding-right:133px;color:green;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color:red;">
                                                                ${row.hasRxCard}
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>
                                                        ${row.rxSearched}
                                                    </td>
                                                    <!--------------------------------------------------------->
                                                    <c:set var="vid" value="${row.ptVideo}"   />
                                                    <c:choose>
                                                        <c:when test="${ vid== null || vid ==''}">
                                                            <td><a href="#"><i class="fa fa-play" style="color:grey;"></i></a></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                            <td><a href="#myModal${row.orderId}" class="fa fa-play" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                                <!-- Modal HTML -->
                                                                <div id="myModal${row.orderId}" class="modal fade"  >
                                                                    <div class="modal-dialog" style="width: 336px;">
                                                                        <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                            <div class="">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="">
                                                                                <video id="videoContainer"   width="320" height="176" controls preload="none" >
                                                                                    <source src="${row.ptVideo}" type="video/mp4">
                                                                                </video>
                                                                                <%-- <c:out value="${row.ptVideo}"></c:out> --%>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td> 
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-------------------------------------------------------->
                                                    <td >
                                                        ${row.quantity}
                                                    </td>
                                                    <td style="padding-left:19px">
                                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${row.cashPriceQuated}"/>
                                                    </td>
                                                    <!--                                                    <td style="color:orange;">
                                                    ${row.status}
                                                </td>-->

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>  
                                    <!---------------------------------------------------------------------------->
                                </div>
                                <!------------------------------------------------------------------------------------------------------>
                            </div>

                        </div>

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

                            $(document).ready(function ( ) {
                                $('#example').dataTable({"sPaginationType": "full_numbers",
                                    "aoColumnDefs": [
                                        {'bSortable': false, 'aTargets': [4, 5, 6]}
                                    ]
                                });

                                $('#profileexample').dataTable({"sPaginationType": "full_numbers",
                                    "aoColumnDefs": [
                                        {'bSortable': false, 'aTargets': [2, 4, 5, 6, 7]}
                                    ]
                                });

                                $('#messagesexample').dataTable({"sPaginationType": "full_numbers",
                                    "aoColumnDefs": [
                                        {'bSortable': false, 'aTargets': [2, 4, 5, 6, 7]}
                                    ]
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

    </script>
</body>
</html>

