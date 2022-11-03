<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head2.jsp" />

    <body class="home_padding secondHeaderBody" onload="loadInformativeDataFromServer()">
        <jsp:include page="./inc/header2.jsp" />
        <!--Registration Page-->
        <div class="">
            <div class="wrapper rxQueueInfoCont">
                <div class="customRow clearfix">
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
                            <h4> ${PharmacyTitle} </h4><%--<a href="${pageContext.request.contextPath}/PharmacyPortal/profile_edit/${Email}" class="pull-right">Edit</a>--%>
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
                            <!--                            <h4>Queue Overview</h4>
                                                        <ul>
                                                            <li><span>New / Open</span>${Count}</li>
                                                            <li><span>Pending</span>0</li>
                                                            <li><span>Processed</span>0</li>
                                                        </ul>-->
                            <h4>Queue Overview</h4>
                            <ul>
                                <li id="pendingCount"><span>New /Pending</span>(0)</li>
                                <li id="waitingPtResponseCount"><span>Waiting Responses </span> (0)</li>
                                <li id="filledCount"><span>Processed</span>(0)</li>
                                <li id="shippedCount"><span>Shipped  </span>(0)</li>
                                <li id="PtResponseCount"><span>Pt Responses</span>(0)</li>
                            </ul>
                        </div>

                        <div class="rxqueue" style="background-color:#3582b3">
                            <p class="caretRight"> <a style="color:#fff;" href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest/?status=">RX QUEUE </a></p>
                        </div>


                        <ul class="rx_lft_menu">
                            <li><a href="javascript:;">Reports</a> 

                            </li>
                        </ul>
                        <div class="rxqueue" style="background-color:#8aca14">
                            <p class="caretRight"> <a href="${pageContext.request.contextPath}/PharmacyPortal/careGiverList">PENDING CAREGIVER </a> </p>
                        </div>
                        <div class="rxqueue">
                            <p class="caretRight">patient Lookup </p>
                        </div>

                    </div>
                    <!--Drug Modal-->
                    <jsp:include page="drugestimateprice.jsp" />
                    <jsp:include page="multidrugestimateprice.jsp" />
                    <!--/Drug Modal-->
                    <div class="col-md-12 custome_right">

                        <div class="tableContainer">

                            <!-- Nav tabs -->
                            <!-- Tab panes -->
                            <div class="tab-content">




                                <!--                            <div class="rxquetablewerap">-->
                                <table  id="example" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Created On</th>
                                            <th>Care Giver</th>
                                            <th>Dependant</th>
                                            <th>POA Expiration</th>
                                            <th>POA Front Image</th>
                                            <th>POA Back Image</th>
                                            <th style="display: none;">POA Expiration</th>
                                            <th>Rx Count</th>
                                            <th>Comments</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="row" items="${careGiverList}">
                                            <tr>
                                                <td>
                                                    <fmt:formatDate value='${row.createdDate}' pattern='yyyy-MM-dd' var="date"/>
                                                    <fmt:formatDate value='${row.createdDate}' pattern='HH:mm:ss' var="time"/>

                                                    <c:choose>
                                                        <c:when test="${row.approvalStr eq 'Approved'}">
                                                            <a href="<c:url 
                                                                   value="/PharmacyPortal/careGiverDetail/${row.id}"/>"
                                                               style="color: green">${date}@<strong>${time}</strong></a>
                                                        </c:when>
                                                        <c:when test="${row.approvalStr eq 'Rejected'}">
                                                            <a href="<c:url 
                                                                   value="/PharmacyPortal/careGiverDetail/${row.id}"/>"
                                                               style="color: red">${date}@<strong>${time}</strong></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="<c:url 
                                                                   value="/PharmacyPortal/careGiverDetail/${row.id}"/>"
                                                               style="color: blue">${date}@<strong>${time}</strong></a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${row.fullPatientName}</td>
                                                <td>${row.firstName} ${row.lastName}</td>
                                                <%-- <c:choose>
                                                    <c:when test="${row.approvalStr eq 'Approved'}">
                                                        <td><fmt:formatDate pattern = "MM/dd/yyyy" 
                                                                        value = "${row.poaExpirationDate}" /></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                        
                                                      
                                                    </c:otherwise>
                                                </c:choose>--%>
                                                <td>
                                                    <fmt:formatDate pattern = "MM/dd/yyyy" value = "${row.poaExpirationDate}" />
                                                </td>


                                                <!----------------------------------------------------->

                                                <!----------------------------------------------------->
                                                <!--------------------------------------------------------->

                                                <c:set var="imgsFront" value="${row.frontPOAImage}"   />
                                                <c:choose>
                                                    <c:when test="${(imgsFront==null || imgsFront=='')}">
                                                        <td><a href="#"><i class="fa fa-paperclip" style="color:grey;"></i></a></td>
                                                            </c:when>

                                                    <c:when test="${imgsFront!=null && imgsFront ne ''}">
                                                        <td><a href="#myModal${row.patientId}${row.id}" class="fa fa-paperclip" style="color:green;" 
                                                               data-toggle="modal"></a>

                                                            <!-- Modal HTML -->
                                                            <div id="myModal${row.patientId}${row.id}" class="modal fade"  >
                                                                <div class="modal-dialog" style="width: 336px;">
                                                                    <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                        <div class="">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                        </div>
                                                                        <div class="">
                                                                            <img src="${imgsFront}"/>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td> 
                                                    </c:when>

                                                    <c:otherwise>
                                                        <td><a href="#myModal${row.patientId}${row.id}" class="fa fa-paperclip" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                            <!-- Modal HTML -->
                                                            <div id="myModal${row.patientId}${row.id}" class="modal fade"  >
                                                                <div class="modal-dialog" style="width: 336px;">
                                                                    <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                        <div class="">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td> 
                                                    </c:otherwise>
                                                </c:choose>
                                                <!------------------------- Back POA Image  ------------------------------->
                                                <c:set var="imgs" value="${row.backPOAImage}"   />
                                                <c:choose>
                                                    <c:when test="${(imgs==null || imgs=='')}">
                                                        <td><a href="#"><i class="fa fa-paperclip" style="color:grey;"></i></a></td>
                                                            </c:when>

                                                    <c:when test="${imgs!=null && imgs ne ''}">
                                                        <td><a href="#myModal${row.id}${row.patientId}" class="fa fa-paperclip" style="color:green;" 
                                                               data-toggle="modal"></a>

                                                            <!-- Modal HTML -->
                                                            <div id="myModal${row.id}${row.patientId}" class="modal fade"  >
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
                                                        <td><a href="#myModal${row.id}${row.patientId}" class="fa fa-paperclip" style="color:green;" data-toggle="modal"></a><!-- <a href="#"><i class="fa fa-play"></i></a>  --> <!-- <a href="#" data-toggle="modal" data-target="#openModal" onclick="playVid()">Launch video player</a> -->

                                                            <!-- Modal HTML -->
                                                            <div id="myModal${row.id}${row.patientId}" class="modal fade"  >
                                                                <div class="modal-dialog" style="width: 336px;">
                                                                    <div class="modal-content" style="    width: 330px; height: 196px; padding-top: 5px;">
                                                                        <div class="">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td> 
                                                    </c:otherwise>
                                                </c:choose>
                                                <!---------------------------------------------------------->
                                                <td style="display: none;">
                                                    ${row.expiryDate}
                                                </td>

                                                <!-------------------------------------------------------->
                                                <td>${row.rxCount}</td>
                                                <td>${row.comments}</td>

                                                <!-------------------------------------------------------->
                                                <td>${row.approvalStr}</td>

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



                                <!------------------------------------------------------------------------------------------------------>


                            </div>

                        </div>
                    </div>

                </div>


            </div>
            <!--Registration Page-->




            <jsp:include page="./inc/footer2.jsp" />
            <jsp:include page="addNewRxDiv.jsp" />  

        </div>
        <!-- Modal Status -->
        <!--    <div id="statusModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
        
                     Modal content
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
            / Modal Status 
        
             Modal Register 
            / Modal Register 
             jQuery 
        
            Financial Report
            <div class="modal fade financial_modal" id="finanmodal" role="dialog">
                <div class="modal-dialog">
        
                     Modal content
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
                            -------------------------------------------------------------------------------------------------------------
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
                            -------------------------------------------------------------------------------------------------------------
                            <div class="col-sm-12 gobtn">
                                <input type="button" value="Submit" name="go" class="btn btn-primary" onclick="exportFinancialReportData()"/>
                            </div>
                        </div>
        
                    </div>
        
                </div>
            </div>-->
        <!--/Financial Report-->




        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
        <script>

        $(document).ready(function ( ) {
            $('#example').dataTable({"sPaginationType": "full_numbers",
                aaSorting: [[0, 'desc']],
                "aoColumnDefs": [
                    {'bSortable': false, 'aTargets': [4, 5, 6]}
                ]
            });
            window.pharmacyNotification.populateAppUrl('${pageContext.request.contextPath}');
            window.drugestimateprice.populateAppUrl('${pageContext.request.contextPath}');
            window.transferRequest.populateAppUrl('${pageContext.request.contextPath}');

            //$("#example_wrapper > .dataTables_filter").appendTo("div.search_tab_f");
            //$("#example_wrapper > .dataTables_length").appendTo("div.sorting_tab_f"); 

        });
        function loadInformativeDataFromServer()
        {
            ////////////////////////////////////////////////////////////
            var url = "${pageContext.request.contextPath}/PharmacyPortal/firstQueueInfo";

            jQuery.ajax({
                type: 'GET',
                url: url,
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',

                success: function (data) {
//                                                        JSONArray jarray = data.mapStatusCount;
//                                                        console.log("VAL "+JSON.parse(data.mapStatusCount));
                    $.each(data.mapStatusCount, function (key, value) {

                        if (key != 'null')
                        {
//                                                                console.log("Key "+key+" VAL "+value);
                            if ($("#span" + key) != null)
                            {
                                //$("#span" + key).html("(" + value + ")");
                                if (key == 1) {
                                    $("#pendingCount").html("<span>New /Pending</span>(" + value + ")");
                                } else if (key == 16) {
                                    $("#waitingPtResponseCount").html("<span>Waiting Responses </span>(" + value + ")");
                                } else if (key == 8) {
                                    $("#filledCount").html("<span>Processed</span>(" + value + ")");
                                } else if (key == 6) {
                                    $("#shippedCount").html("<span>Shipped </span>(" + value + ")");
                                } else if (key == 19) {
                                    $("#PtResponseCount").html("<span>Pt Responses </span>(" + value + ")");
                                }
                            }
                        }
                    })


                }
            });
            ///////////////////////////////////////////////////////////
        }

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
                            url: "${pageContext.request.contextPath}/PharmacyPortal/loadFinancialCycleInfo",
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

