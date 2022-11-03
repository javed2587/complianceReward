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

    <body class="home_padding secondHeaderBody">
        <jsp:include page="./inc/header2.jsp" />

        <!--Registration Page-->
        <div class="row">
            <div class="wrapper">
                <input id="patientId" type="hidden" value="${patientProfile.id}"/>
                <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
                <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.dob}"/>
                <div class="userBanner clearfix">

                    <div class="clearfix">
                        <ul>
                            <li >${fn:toUpperCase(patientProfile.firstName)}&nbsp; ${fn:toUpperCase(fn:substring(patientProfile.lastName, 0, 1))} &nbsp;(${patientProfile.gender})</li>
                            <li > 
                                DOB: <fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.dob}"/> </li>
                            <li >Age:  ${nowyear-dobyear} YEARS</li>
                                <c:choose>
                                    <c:when test="${fn:length(patientProfile.patientProfileMembersList) > 0}">
                                    <li ><a href="#" onclick="displayModal(${patientProfile.id}, 'dependentsModal', 'viewDependentWs')"> ${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li >${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</li>
                                    </c:otherwise>
                                </c:choose>
                            <li >${count} PROGRAM RX'S</li>
                            <li>
                                <c:if test="${patientProfile.isOldPatient ne true}">
                                    <span class="new_usere"></span>
                                </c:if>
                                Member Since <fmt:formatDate value='${patientProfile.createdOn}' pattern='yyyy-MM-dd'/></li>
                        </ul>
                    </div>
                </div>
                <div class="customTableRow">
                    <div class="col-md-3">
                        <jsp:include page="./inc/patientinfo.jsp" />
                        <!--                            <form action="" class="messageForm">
                                                        <textarea name="message" class="messageBox" >Send Message to Patient</textarea>
                                                        <button type="submit"><i class="fa fa-paper-plane greenText" ></i></button>
                                                    </form>-->
                        <!--                            <a href="#" class="greenText messageHistory">View Message History</a>-->
                    </div>
                    <div class="col-md-9">

                        <div class="tableContainer rxListTable">

                            <table id="level2" class="table-striped">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Medication</th>
                                        <th>Qty</th>
                                        <th>Pharmacy Name</th>
                                        <th>Pharmacy Phone</th>

                                        <th>Rx Number</th>
                                        <th>Quoted</th>
                                        <th>Video/ Image</th>
                                        <th>OrderType</th>
                                        <th>Status </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                    <c:when test="${count > 0}">
                                    <!------------------------------------------------------------------------>
                                    <c:forEach  var="row" items="${TransferRxQueueDTO}">
                                        <tr>

                                            <td>
                                                <fmt:formatDate value='${row.receivedDate}' pattern='yyyy-MM-dd HH:mm:ss' var="date" />
                                                <%--
                                                <c:set var="disable" value="${row.disabled}"/>
                                                <c:choose>
                                              
                                                <c:when test="${1 == disable}">
                                                <a href="#!"    style="color: grey">${date}</a>
                                                </c:when>
                                                <c:otherwise>--%>
                                                <%--  <c:set var="transferDetailId" value="${row.transferDetailId}" /> --%>
                                                <a href="<c:url value="/ConsumerPortal/rxDetail/${row.orderId}/${row.patientId}"/>"  style="color: blue">${date}</a>
                                                <%--</c:otherwise>
                                                </c:choose>--%>
                                            </td>
                                            <td>${row.rxSearched}</td>
                                            <td>${row.quantity}</td>
                                            <td style="float:left;">${row.pharmacyName}</td>
                                            <td>${row.pharmacyPhone}</td>

                                            <td>${row.rxNumber}</td>
                                            <td><fmt:formatNumber type="currency" currencySymbol="$" value="${row.cashPriceQuated}" /></td>
                                            <c:set var="vid" value="${row.ptVideo}"   />
                                            <c:set var="imgs" value="${row.transferImage}"   />
                                            <c:choose>
                                                <c:when test="${ (vid== null || vid =='')&&(imgs==null || imgs=='')}">
                                                    <td><a href="#"><i class="fa fa-play" style="color:grey;"></i></a></td>
                                                        </c:when>
                                                <c:when test="${imgs ne ''}">
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
                                            <td>${row.orderType}</td>
                                            <td class="orangeText">${row.status}</td>
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

                        <div class="back_wrap"><a href="<c:url value="/PharmacyPortal/successLogin/${orderId}?status=${param.status}" />" class="btn back_btn" >Back
                                <!-- <input type="submit" value="Back" /> -->
                            </a></div>

                    </div>
                </div>
            </div>



            <%-- <div id="openModal" class="modalDialog">
                    <div>
                            <a href="#close" title="Close" class="close">X</a>

                            <video id="myVideo" width="320" height="176" style="float:center;" controls>
                  <source src="${pageContext.request.contextPath}/resources/video/mov_bbb.mp4" type="video/mp4">
                    Your browser does not support HTML5 video.
                 </video>
                    </div>
            </div> --%>
            <!-- Modal Status -->

            <!--Registration Page-->


        </div>

    </div>
    <!--/ Modal Status -->
</div>
<!--Registration Page-->


<!--Footer-->
<jsp:include page="./inc/footer2.jsp" />
<!--/Footer-->

</div>
<jsp:include page="modals.jsp" />
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
                <p>Prescription has not been viewed by any user.</p>
            </div>
        </div>

    </div>
    <div id="dialogDependant" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
    <div id="dialogInsurance" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
    <div id="dialog" style="height: auto; overflow: auto; background-color: white;display: none;"></div>
</div>
<!--/ Modal Status -->

<!-- Modal Register -->
<!--/ Modal Register -->

<!-- jQuery -->

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientDependent.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInsurancePharmacy.js"></script>

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
                                            
                                            $('#level2').dataTable({"sPaginationType": "full_numbers" ,
                                                    "aoColumnDefs": [
                                                                     { 'bSortable': false, 'aTargets': [ 2 , 4 , 5, 6, 7 ] }
                                                                  ]
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

