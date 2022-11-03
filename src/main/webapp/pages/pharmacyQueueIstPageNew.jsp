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
    <style>
        #loadingg {
            width: 100%;
            height: 100%;
            top: 0px;
            left: 0px;
            position: fixed;
            display: block;
            opacity: 0.7;
            background-color: #000;
            z-index: 1500;
            text-align: center;
        }

        #loading-image {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 500;
            margin: -32px 0 0 -32px;
        }
    </style>
    <div id="loadingg">
        <img id="loading-image" src="http://cdn.nirmaltv.com/images/generatorphp-thumb.gif" alt="Loading..." />
    </div>
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
                    <h4>Queue Overview</h4>
                    <ul>
                        <li id="pendingCount"><span>New /Pending</span>(${Count})</li>
                        <li id="waitingPtResponseCount"><span>Waiting Responses </span> (${WaitingPtResponseCount})</li>
                        <li id="filledCount"><span>Processed</span>(${filledCount})</li>
                        <li id="shippedCount"><span>Shipped  </span>(${ProcessedCount})</li>
                        <li id="PtResponseCount"><span>Pt Responses</span>(${PtResponseCount})</li>
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
                    <p class="caretRight"> <a href="${pageContext.request.contextPath}/PharmacyPortal/careGiverList" style="color:#fff;"  >PENDING CAREGIVER </a> </p>
                </div>
                
                 <div class="rxqueue" >
                    <p class="caretRight"> <a href="${pageContext.request.contextPath}/PharmacyPortal/patientActivities" style="color:#fff;"  >PATIENT ACTIVITIES </a> </p>
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
                        <li id="newRequestTab" role="presentation" ><a style="color:#1c64be !important;" id="home" href="#home" aria-controls="home" role="tab" data-toggle="tab" >New Member<br/>Requests <span id="span1">(0)</span></a></li>
                        <li id="incommingPtRes" role="presentation"><a id="responseLnk" href="#presponse" aria-controls="presponse" role="tab" data-toggle="tab" class="blue">Incomming Pt<br/>Responses <span id="span19">(0)</a></li>
                        <li id="awaitingPtRes" role="presentation"><a class="red" id="waitingLnk" href="#messages" aria-controls="messages" role="tab" data-toggle="tab" >AWaiting PT<br/>Response <span id="span16"> (0)</a></li>
                        <li id="interpretedImageTab" role="presentation"><a id="interpreted" href="#profile" aria-controls="profile" role="tab" data-toggle="tab" >INTERPRETED<br/>Images <span id="span17"> (0)</a></li>
                        <li id="" role="presentation"><a id="processedLnk" href="#prequest" aria-controls="prequest" role="tab" data-toggle="tab" >Processed Pt<br/>Requests <span id="span8"> (0)</a></li>
                        <li id="" role="presentation"><a id="shippedLnk" href="#shipdelivery" aria-controls="shipdelivery" role="tab" data-toggle="tab" >Shipping &<br/>Delivery <span id="span6">(0)</a></li>
                        <li id="" role="presentation"><a class="red_last cancelPtResponse" id="cancelledLnk" href="#crequest" aria-controls="crequest" role="tab" data-toggle="tab" >Cancelled Pt<br/>Requests <span id="span11">(0)</a></li>
                    </ul>

                    <div id="searchBox" class="input-group add-on search_container" style="">
                        <div class="dataTables_length" id="pResponseTable_length">
                            <label>
                                <select name="pResponseTable_length" aria-controls="pResponseTable" class="">
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select> 
                            </label>
                        </div>
                        <div class="col-sm-10" style="margin:0 0 0 50px;">
                            <div class="col-sm-3 col-xs-12 drp-ipad">
                                <div class="input-group">
                                    <!--                                    <span class="input-group-addon pad_labe_new  padd_none_new_page_top" id="basic-addon1" style="width: 38%;">Req Control#:</span>
                                                                        <input id="tableId" type="hidden">
                                                                        <input class="form-control" placeholder="Req Control #/Rx#" name="searchTitle_1" id="searchTitle_1" type="text" onkeyup="enableSearchBtn()" style="width:66%;">-->
                                    <span class="" id="basic-addon1">
                                        <select id="searchByddl" class="form-control dropdown-select" onchange="searchBy(this.value)" style="width: 100%;">
                                            <option value="Rx">Prescriber#</option>
                                            <option value="ptName">PT Name</option>
                                            <option value="mobileNo">Phone#</option>
                                        </select>
                                    </span>
                                    <input id="tableId" type="hidden">
                                    <input class="form-control searchBy" placeholder="Enter Prescriber#" name="searchBy" id="searchBy" type="text" onkeyup="enableSearchBtn()" style="width:51%;" onchange="enableSearchBtn()"/>
                                    <input class="form-control" placeholder="Enter Rx#" name="searchTitle_1" id="searchTitle_1" type="text" onkeyup="enableSearchBtn()" style="width:51%;display: none;"/>
                                </div>
                            </div>
                            <span id="fromDateReq" class="errorMessage"></span>
                            <!-- <div class="col-sm-2 padd_none_new_page">
                                    <input id="tableId" type="hidden">
                                        <input class="form-control" placeholder="Search" name="searchTitle_1" id="searchTitle_1" type="text" onkeyup="searchRecord(this.id)">
                                    </div> -->
                            <div class="col-sm-3 col-xs-12 drp-ipad">
                                <div class="input-group">
                                    <span class="input-group-addon pad_labe_new" id="basic-addon1">MBR SVC REQ:</span>
                                    <select id="searchByMbrSvcReq" class="form-control" style="width:70%;" onchange="enableSearchBtn()">
                                        <option value="">Select</option>
                                        <option value="X-FER LABEL SCAN">
                                            X-FER LABEL SCAN
                                        </option>
                                        <option value="X-FER RX SCAN">
                                            X-FER RX SCAN
                                        </option>
                                        <option value="Refill">Refill</option>
                                    </select>
                                </div>
                                <span id="fromDateReq" class="errorMessage"></span>
                            </div>
                            <div class="col-sm-3 col-xs-12 drp-ipad padd_none_new_page_two">
                                <div class="input-group">
                                    <span class="input-group-addon pad_labe_new" id="basic-addon1">Rx Status:</span>
                                    <select id="searchByOrderStatus" class="form-control" style="width:55%;" onchange="enableSearchBtn()">
                                        <option value="">Select</option>
                                        <option id="opt1" value="UNVERIFIED" style="display: none;">UNVERIFIED</option>
                                        <option id="opt2" value="Pending" style="display: none;">Pending</option>
                                        <option id="opt3" value="Pending Review" style="display: none;">Pending Review</option>
                                        <option id="opt4" value="Waiting Pt Response" style="display: none;">Waiting Pt Response</option>
                                        <option id="opt5" value="Filled" style="display: none;">Filled</option>
                                        <option id="opt6" value="Shipped" style="display: none;">Shipped</option>
                                        <option id="opt7" value="Cancelled" style="display: none;">Cancelled</option>
                                        <option id="opt8" value="Response Received">Response Received</option>
                                    </select>
                                </div>
                                <span id="fromDateReq" class="errorMessage"></span>
                            </div>
                            <div class="col-sm-3 col-xs-12 drp-ipad padd_none_new_page">
                                <div class="input-group">
                                    <span class="input-group-addon pad_labe_new" id="basic-addon1">Delivery SVC:</span>
                                    <select id="searchByDeliverySvc" class="form-control" style="width:61%;" onchange="enableSearchBtn()">
                                        <option value="">Select</option>
                                        <option value="Same Day">Same Day</option>
                                        <option value="Next Day*">Next Day*</option>
                                        <option value="2nd Day*">2nd Day*</option>
                                        <option value="Pick Up at Pharmacy">Pick Up at Pharmacy</option>
                                    </select>
                                </div>
                                <span id="fromDateReq" class="errorMessage"></span>
                            </div>                            
                        </div>
                        <div class="col-sm-2" style="z-index:999;">
                            <div class="col-sm-12 col-xs-12 padd_none_new_page">
                                <div style="float: left;">
                                    <button id="btnSearch" class="btn btn-primary width_none_new" style="color: white" onclick="searchRecord(this.id)" disabled>Search</button>
                                </div>
                                <div>
                                    <!--                                    <a href="/ConsumerPortal/queuePatientDetailPage/24/89/tab1" class="btn btn-primary width_none_new" style="color: white;margin-left: 5px;">Cancel</a>-->
                                    <button id="btnCancel" class="btn btn-primary width_none_new" style="color: white;margin-left: 5px;" onclick="resetSearchField();searchRecord(this.id)">Cancel</button>
                                </div>
                            </div>
                        </div>

                    </div>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home">
                            <table class="table-striped" id="newRequestTable">
                                <thead>
                                    <tr>
                                        <th class="size_enter"></th>
                                        <th class="size_enter"></th>
                                        <th class="size_enter"></th>
                                        <th class="size_enter"></th>
                                        <th class="size_onefourty"></th>
                                        <th class="mediaction_size"></th>
                                        <th class="size_enter"></th>
                                        <th class="size_thirty"></th>

                                        <th class="size_twenty"></th>
                                        <th class="size_thirty"></th>
                                        <!--                                                <th>ZIP</th>-->

                                    </tr>
                                </thead>
                                <tbody>



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
                                </tbody>
                            </table>
                        </div>


                        <input id="loadingTime" type="hidden" value="${loadingTime}" />
                        <div role="tabpanel" class="tab-pane" id="messages">

                            <div>


                                <input type="button" class="btn-delete" id="delBtn" value="Delete" disabled
                                       onClick="window.pharmacyNotification.deleteSelectedOrderHandler();"
                                       style="display:none" >

                                <input type="button" class="btn-process" id="actionBtn" value="Process" disabled
                                       onClick="window.pharmacyNotification.openSelectedOrders();"
                                       style="display:none" >
                            </div>
                            <table id="waitingPtResponseTbl" class="table-striped">
                                <thead>
                                    <tr>
                                        <th class="ten_width">&nbsp;</th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th class="redText"></th>
                                        <th></th>
                                        <th></th>
                                        <th class="eighty_width"></th>
                                        <th></th>

                                    </tr>
                                </thead>
                                <tbody>

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

                                </tbody>
                            </table>

                        </div>



                        <div role="tabpanel" class="tab-pane" id="shipdelivery">

                            <table id="shippingDeliveryTbl" class="table-striped" >
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

                                </tbody>
                            </table>

                        </div>
                        <div role="tabpanel" class="tab-pane" id="crequest">
                            <table id="cancelledRequestTbl" class="table-striped">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>

                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>

                        </div>
                        <div role="tabpanel" class="tab-pane" id="presponse">

                            <table id="pResponseTable" class="table-striped table-presponse">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>

                                    </tr>
                                </thead>
                                <tbody>
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

<form action="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest" method="get" id="form" name="form">
    <a id="backLnk" onclick="document.getElementById('form').submit();
            return false;" class="btn btn_one" style="display:none"></a>  

</form>   
<form action="${pageContext.request.contextPath}/ConsumerPortal/rxDetailHandler" method="get" id="form2" name="form2">
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
<input type="hidden" id="filterFld" value="Response Received" />
<input type="hidden" id="tabFld" value="${tab}" />
<input type="hidden" id="selectedTabFld" value="${sessionBeanPortal.selectedTab}" />


<!--Drug Modal Saimoon-->
<jsp:include page="./portal/drugestimateprice.jsp" />
<jsp:include page="./portal/multidrugestimateprice.jsp" />
<jsp:include page="./portal/addNewRxDiv.jsp" />   
<!--/Drug Modal Saimoon-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>-->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugestimateprice.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyNotification.js"></script>
<script>
                                       function handleClick(event) {
                                           if ($("#searchTitle_1").val().length > 0) {
                                               $("#searchTitle_1").val("");
                                           }
										   
                                           resetSearchField();
//            alert('Clicked on the ' + event.data.text);
                                           $("#tabFld").val(event.data.text);
                                           var selectedTabF = event.data.text;
                                           $("#selectedTabFld").val(event.data.text);
//            alert("filter "+event.data.filter   )
                                           $("#filterFld").val(event.data.filter);
                                           managingTablesVisibility();
                                           $("#loadingg").fadeIn();
                                           page1Data();
                                           for (var indx = 1; indx <= 8; indx++) {
                                               if (selectedTabF == "tab1") {
                                                   if (indx == 1 || indx == 2) {
                                                       $("#opt" + indx).removeAttr("style");
                                                       continue;
                                                   }
                                                   $("#opt" + indx).attr("style", "display:none;");
                                               } else if (selectedTabF == "tab7") {
                                                   if (indx == 8) {
                                                       $("#opt" + indx).removeAttr("style");
                                                       continue;
                                                   }
                                                   $("#opt" + indx).attr("style", "display:none;");
                                               } else if (selectedTabF == "tab3") {
                                                   if (indx == 4) {
                                                       $("#opt" + indx).removeAttr("style");
                                                       continue;
                                                   }
                                                   $("#opt" + indx).attr("style", "display:none;");
                                               } else if (selectedTabF == "tab2") {
                                                   if (indx == 3) {
                                                       $("#opt" + indx).removeAttr("style");
                                                       continue;
                                                   }
                                                   $("#opt" + indx).attr("style", "display:none;");
                                               } else if (selectedTabF == "tab4") {
                                                   if (indx == 5) {
                                                       $("#opt" + indx).removeAttr("style");
                                                       continue;
                                                   }
                                                   $("#opt" + indx).attr("style", "display:none;");
                                               } else if (selectedTabF == "tab5") {
                                                   if (indx == 6) {
                                                       $("#opt" + indx).removeAttr("style");
                                                       continue;
                                                   }
                                                   $("#opt" + indx).attr("style", "display:none;");
                                               } else if (selectedTabF == "tab6") {
                                                   if (indx == 7) {
                                                       $("#opt" + indx).removeAttr("style");
                                                       continue;
                                                   }
                                                   $("#opt" + indx).attr("style", "display:none;");
                                               }
                                           }
                                       }

                                       function managingTablesVisibility()
                                       {
                                           $("#newRequestTable").attr("style", "display:none;");
                                           $("#interpretedIImageTable").attr("style", "display:none;");
                                           $("#waitingPtResponseTbl").attr("style", "display:none;");
                                           $("#processRequestTbl").attr("style", "display:none;");
                                           $("#shippingDeliveryTbl").attr("style", "display:none;");
                                           $("#cancelledRequestTbl").attr("style", "display:none;");
                                           $("#pResponseTable").attr("style", "display:none;");

                                           $("#newRequestTable").dataTable({searching: false, paging: false, "bDestroy": true, "bInfo": false});
                                           $("#interpretedIImageTable").dataTable({searching: false, paging: false, "bDestroy": true, "bInfo": false});
                                           $("#waitingPtResponseTbl").dataTable({searching: false, paging: false, "bDestroy": true, "bInfo": false});
                                           $("#processRequestTbl").dataTable({searching: false, paging: false, "bDestroy": true, "bInfo": false});
                                           $("#shippingDeliveryTbl").dataTable({searching: false, paging: false, "bDestroy": true, "bInfo": false});
                                           $("#cancelledRequestTbl").dataTable({searching: false, paging: false, "bDestroy": true, "bInfo": false});
                                           $("#pResponseTable").dataTable({searching: false, paging: false, "bDestroy": true, "bInfo": false});
//             table.destroy();
                                       }

                                       function hideAllTables()
                                       {
                                           $("#newRequestTable").attr("style", "display:none;");
                                           $("#interpretedIImageTable").attr("style", "display:none;");
                                           $("#waitingPtResponseTbl").attr("style", "display:none;");
                                           $("#processRequestTbl").attr("style", "display:none;");
                                           $("#shippingDeliveryTbl").attr("style", "display:none;");
                                           $("#cancelledRequestTbl").attr("style", "display:none;");
                                           $("#pResponseTable").attr("style", "display:none;");
                                       }

                                       function loadInformativeDataFromServer()
                                       {
                                           ////////////////////////////////////////////////////////////
                                           var url = "${pageContext.request.contextPath}/PharmacyPortal/firstQueueInfo?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val();

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
                                                               $("#span" + key).html("(" + value + ")");
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

                                       function page1Data()
                                       {
                                           /////////////////////////////////////////////////
                                           hideAllTables();
                                           //////////////////////////////////////////////////
                                           var tab = $("#tabFld").val();
                                           var selectTab = $("#selectedTabFld").val();//'${sessionBeanPortal.selectedTab}';
                                           selectedTab(selectTab);
                                           var tableId, orderStatus;
                                           var jsonArr = [];
										  // alert(tab);
                                           if (tab == 'tab1') {
//                                               $("#pResponseTable").attr("style", "display:none;");
                                               $("#newRequestTable").show();//.attr("style", "display:block;");
                                               $(".tab-content > .tab-pane").attr("style", "display:block;");
//                                               alert("here ")
                                               tableId = "newRequestTable";
                                               //jsonArr.push({'bSortable': false, 'aTargets': [2, 3, 5, 6, 7, 8, 9]});
                                               jsonArr.push({'bSortable': false, 'aTargets': [8, 9]});
//                                               $("#searchBox").attr("style", "top:5.5%;right: 10px;position: absolute;");

                                           } else if (tab == 'tab2') {
											   

//                                               $("#newRequestTable").attr("style", "display:none;");
                                               $("#interpretedIImageTable").show();
                                               tableId = "interpretedIImageTable";
                                               jsonArr.push({'bSortable': false, 'aTargets': [2, 3, 4, 6, 7, 8]});
//                                               $("#searchBox").attr("style", "top:5.2%;right: 10.9px;position: absolute;");

                                           } else if (tab == 'tab3') {
//                                               $("#newRequestTable").attr("style", "display:none;");
                                               $("#waitingPtResponseTbl").show();
                                               tableId = "waitingPtResponseTbl";
                                               jsonArr.push({'bSortable': false, 'aTargets': [0, 3, 4, 7, 8, 9, 10, 11, 12, 13, 14]});
//                                               $("#searchBox").attr("style", "top:10.9%;right: 34px;position: absolute;");

                                           } else if (tab == 'tab4') {
//                                               $("#newRequestTable").attr("style", "display:none;");
                                               $("#processRequestTbl").show();
                                               tableId = "processRequestTbl";
                                               jsonArr.push({'bSortable': false, 'aTargets': [3, 7, 8, 9, 10, 11, 12, 13]});
//                                               $("#searchBox").attr("style", "top:6.7%;right: 34px;position: absolute;");
                                           } else if (tab == 'tab5') {
//                                               $("#newRequestTable").attr("style", "display:none;");
                                               $("#shippingDeliveryTbl").show();
                                               tableId = "shippingDeliveryTbl";
                                               jsonArr.push({'bSortable': false, 'aTargets': [2, 3, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]});
//                                               $("#searchBox").attr("style", "top:33px;right: 34px;position: absolute;");
                                           } else if (tab == 'tab6') {
//                                               $("#newRequestTable").attr("style", "display:none;");
                                               $("#cancelledRequestTbl").show();
                                               tableId = "cancelledRequestTbl";
                                               jsonArr.push({'bSortable': false, 'aTargets': [2, 4, 5, 6, 7, 8, 9, 10, 11, 12]});
//                                               $("#searchBox").attr("style", "top:5.9%;right: 34px;position: absolute;");
                                           } else if (tab == 'tab7') {
											  // $("#home").attr("style", "display:none;");
//                                               $("#newRequestTable").attr("style", "display:none;");
                                               $("#pResponseTable").show();
                                               tableId = "pResponseTable";
                                               // jsonArr.push({'bSortable': false, 'aTargets': [2, 5, 6, 7]});
                                               jsonArr.push({'bSortable': false, 'aTargets': [2, 6]});
//                                               $("#searchBox").attr("style", "top:5.9%;right: 34px;position: absolute;");
                                           }
                                           loadInformativeDataFromServer();
                                           $("#loadingg").fadeOut();
                                           $("#tableId").val(tableId);
//                                           $("#searchTitle_1").val('${search}');

                                           if (tab == 'tab2') {
                                               $('#' + tableId).dataTable({
                                                   "dom": 'T<"clear">lrtip',
                                                   "bProcesing": true,
                                                   "bServerSide": true,
                                                   "bAutoWidth": false,
                                                   "autoWidth": false,
                                                   "iDisplayLength": 10,
                                                   "responsive": true,
                                                   "bSortable": false,
                                                   "bInfo": true,
                                                   "bFilter": true,
                                                   "bLengthChange": true,
                                                   "bDestroy": true,
                                                   "sAjaxSource": "${pageContext.request.contextPath}/PharmacyPortal/listing?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val() + "&mbrReq=" + $("#searchByMbrSvcReq").val() + "&deliveryPref=" + $("#searchByDeliverySvc").val() + "&rxStatus=" + $("#searchByOrderStatus").val() + "&searchField=" + $("#searchByddl").val() + "&searchValue=" + $(".searchBy").val(),
                                                   "aoColumns": [
    ${colMap['tab2']}
                                                   ],
                                                   "fnServerData": function (sSource, aoData, fnCallback) {
                                                       $.ajax({
                                                           "dataType": 'json',
                                                           "type": "GET",
                                                           "url": sSource,
                                                           "data": aoData,
                                                           "success": fnCallback
                                                       });
                                                   },
                                                   "sPaginationType": "full_numbers",
                                                   "oLanguage": {
                                                       "sLengthMenu": "_MENU_ ",
                                                       "sEmptyTable": "No records found."
                                                   },
                                                   "aaSorting": [[1, "desc"]],
                                                   "aoColumnDefs": jsonArr,
                                                   "scrollX": true,
                                                   "scrollY": "400",
                                                   "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                                                       orderStatus = aData.orderStatus;
                                                   },
                                                   "fnDrawCallback": function (oSettings) {
                                                       invokeTabLink(orderStatus);
                                                   }
                                               });
                                           } else if (tab == 'tab3') {
                                               $('#' + tableId).dataTable({
                                                   "dom": 'T<"clear">lrtip',
                                                   "bProcesing": true,
                                                   "bServerSide": true,
                                                   "bAutoWidth": false,
                                                   "autoWidth": false,
                                                   "iDisplayLength": 10,
                                                   "responsive": true,
                                                   "bSortable": false,
                                                   "bInfo": true,
                                                   "bFilter": true,
                                                   "bLengthChange": true,
                                                   "bDestroy": true,
                                                   "sAjaxSource": "${pageContext.request.contextPath}/PharmacyPortal/listing?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val() + "&mbrReq=" + $("#searchByMbrSvcReq").val() + "&deliveryPref=" + $("#searchByDeliverySvc").val() + "&rxStatus=" + $("#searchByOrderStatus").val() + "&searchField=" + $("#searchByddl").val() + "&searchValue=" + $(".searchBy").val(),
                                                   "aoColumns": [
                                                       {
                                                           "sTitle": "",
                                                           "sDefaultContent": "",
                                                           "bSortable": false,
                                                           "bSearchable": false,
                                                           "mRender": function (data, type, oObj) {
                                                               return '';//<input id="waitingPtResponseTbl_td_1_' + oObj.orderId + '" name="multirxDCB" type="checkbox" value="' + oObj.orderId + '" onclick=\'window.pharmacyNotification.controllingActionButtonForPage1(\"waitingPtResponseTbl\",\"delBtn\")\'></input>';

                                                           }
                                                       },
    ${colMap['tab3']}
                                                   ],
                                                   "fnServerData": function (sSource, aoData, fnCallback) {
                                                       $.ajax({
                                                           "dataType": 'json',
                                                           "type": "GET",
                                                           "url": sSource,
                                                           "data": aoData,
                                                           "success": fnCallback
                                                       });
                                                   },
                                                   "sPaginationType": "full_numbers",
                                                   "oLanguage": {
                                                       "sLengthMenu": "_MENU_ ",
                                                       "sEmptyTable": "No records found."
                                                   },
                                                   "aaSorting": [[1, "asc"]],
                                                   "aoColumnDefs": jsonArr,
                                                   "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                                                       orderStatus = aData.orderStatus;
                                                   },
                                                   "fnDrawCallback": function (oSettings) {
                                                       invokeTabLink(orderStatus);
                                                   }
                                               });
                                           }
                                           ////////////////////////////////////////////////////////
                                           else if (tab == 'tab1') {

//                                           alert("HERE");

                                               var oTable = $('#' + tableId).dataTable({
                                                   "dom": 'T<"clear">lrtip',
                                                   "bProcesing": true,
                                                   "bServerSide": true,
                                                   "bAutoWidth": false,
                                                   "autoWidth": false,
                                                   "iDisplayLength": 10,
                                                   "responsive": true,
                                                   "bSortable": false,
                                                   "bInfo": true,
                                                   "bFilter": true,
                                                   "bLengthChange": true,
                                                   "bDestroy": true,
//                                                   "aaData" : aData,
                                                   "sAjaxSource": "${pageContext.request.contextPath}/PharmacyPortal/listing?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val() + "&mbrReq=" + $("#searchByMbrSvcReq").val() + "&deliveryPref=" + $("#searchByDeliverySvc").val() + "&rxStatus=" + $("#searchByOrderStatus").val() + "&searchField=" + $("#searchByddl").val() + "&searchValue=" + $(".searchBy").val(),
//                                                   "sAjaxDataProp":"dataTableDataStr",
                                                   "aoColumns": [
    ${colMap['tab1']}
                                                   ],
                                                   "fnServerData": function (sSource, aoData, fnCallback) {

                                                       $.ajax({
                                                           "dataType": 'json',
                                                           "type": "GET",
                                                           "url": sSource,
                                                           "data": aoData,
                                                           "success": fnCallback
                                                       });
                                                   },
                                                   "sPaginationType": "full_numbers",
                                                   "oLanguage": {
                                                       "sLengthMenu": "_MENU_ ",
                                                       "sEmptyTable": "No records found."
                                                   },
                                                   "aaSorting": [[1, "desc"]],
                                                   "aoColumnDefs": jsonArr,
                                                   "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                                                       orderStatus = aData.orderStatus;
                                                   },
                                                   "fnDrawCallback": function (oSettings) {
                                                       invokeTabLink(orderStatus);
                                                   }
                                               });
                                           }
                                           ///////////////////////////////////////////////////////
                                           else if (tab == 'tab4') {

//                                           alert("HERE");

                                               var oTable = $('#' + tableId).dataTable({
                                                   "dom": 'T<"clear">lrtip',
                                                   "bProcesing": true,
                                                   "bServerSide": true,
                                                   "bAutoWidth": false,
                                                   "autoWidth": false,
                                                   "iDisplayLength": 10,
                                                   "responsive": true,
                                                   "bSortable": false,
                                                   "bInfo": true,
                                                   "bFilter": true,
                                                   "bLengthChange": true,
                                                   "bDestroy": true,
//                                                   "aaData" : aData,
                                                   "sAjaxSource": "${pageContext.request.contextPath}/PharmacyPortal/listing?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val() + "&mbrReq=" + $("#searchByMbrSvcReq").val() + "&deliveryPref=" + $("#searchByDeliverySvc").val() + "&rxStatus=" + $("#searchByOrderStatus").val() + "&searchField=" + $("#searchByddl").val() + "&searchValue=" + $(".searchBy").val(),
//                                                   "sAjaxDataProp":"dataTableDataStr",
                                                   "aoColumns": [
    ${colMap['tab4']}
                                                   ],
                                                   "fnServerData": function (sSource, aoData, fnCallback) {

                                                       $.ajax({
                                                           "dataType": 'json',
                                                           "type": "GET",
                                                           "url": sSource,
                                                           "data": aoData,
                                                           "success": fnCallback
                                                       });
                                                   },
                                                   "sPaginationType": "full_numbers",
                                                   "oLanguage": {
                                                       "sLengthMenu": "_MENU_ ",
                                                       "sEmptyTable": "No records found."
                                                   },
                                                   "aaSorting": [[1, "desc"]],
                                                   "aoColumnDefs": jsonArr,
                                                   "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                                                       orderStatus = aData.orderStatus;
                                                   },
                                                   "fnDrawCallback": function (oSettings) {
                                                       invokeTabLink(orderStatus);
                                                   }
                                               });
                                           } else if (tab == 'tab5') {

//                                           alert("HERE");

                                               var oTable = $('#' + tableId).dataTable({
                                                   "dom": 'T<"clear">lrtip',
                                                   "bProcesing": true,
                                                   "bServerSide": true,
                                                   "bAutoWidth": false,
                                                   "autoWidth": false,
                                                   "iDisplayLength": 10,
                                                   "responsive": true,
                                                   "bSortable": false,
                                                   "bInfo": true,
                                                   "bFilter": true,
                                                   "bLengthChange": true,
                                                   "bDestroy": true,
//                                                   "aaData" : aData,
                                                   "sAjaxSource": "${pageContext.request.contextPath}/PharmacyPortal/listing?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val() + "&mbrReq=" + $("#searchByMbrSvcReq").val() + "&deliveryPref=" + $("#searchByDeliverySvc").val() + "&rxStatus=" + $("#searchByOrderStatus").val()+ "&searchField=" + $("#searchByddl").val() + "&searchValue=" + $(".searchBy").val(),
//                                                   "sAjaxDataProp":"dataTableDataStr",
                                                   "aoColumns": [
    ${colMap['tab5']}
                                                   ],
                                                   "fnServerData": function (sSource, aoData, fnCallback) {

                                                       $.ajax({
                                                           "dataType": 'json',
                                                           "type": "GET",
                                                           "url": sSource,
                                                           "data": aoData,
                                                           "success": fnCallback
                                                       });
                                                   },
                                                   "sPaginationType": "full_numbers",
                                                   "oLanguage": {
                                                       "sLengthMenu": "_MENU_ ",
                                                       "sEmptyTable": "No records found."
                                                   },
                                                   "aaSorting": [[1, "desc"]],
                                                   "aoColumnDefs": jsonArr,
                                                   "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                                                       orderStatus = aData.orderStatus;
                                                   },
                                                   "fnDrawCallback": function (oSettings) {
                                                       invokeTabLink(orderStatus);
                                                   }
                                               });
                                           } else if (tab == 'tab6') {

//                                           alert("HERE");

                                               var oTable = $('#' + tableId).dataTable({
                                                   "dom": 'T<"clear">lrtip',
                                                   "bProcesing": true,
                                                   "bServerSide": true,
                                                   "bAutoWidth": false,
                                                   "autoWidth": false,
                                                   "iDisplayLength": 10,
                                                   "responsive": true,
                                                   "bSortable": false,
                                                   "bInfo": true,
                                                   "bFilter": true,
                                                   "bLengthChange": true,
                                                   "bDestroy": true,
//                                                   "aaData" : aData,
                                                   "sAjaxSource": "${pageContext.request.contextPath}/PharmacyPortal/listing?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val() + "&mbrReq=" + $("#searchByMbrSvcReq").val() + "&deliveryPref=" + $("#searchByDeliverySvc").val() + "&rxStatus=" + $("#searchByOrderStatus").val()+ "&searchField=" + $("#searchByddl").val() + "&searchValue=" + $(".searchBy").val(),
//                                                   "sAjaxDataProp":"dataTableDataStr",
                                                   "aoColumns": [
    ${colMap['tab6']}
                                                   ],
                                                   "fnServerData": function (sSource, aoData, fnCallback) {

                                                       $.ajax({
                                                           "dataType": 'json',
                                                           "type": "GET",
                                                           "url": sSource,
                                                           "data": aoData,
                                                           "success": fnCallback
                                                       });
                                                   },
                                                   "sPaginationType": "full_numbers",
                                                   "oLanguage": {
                                                       "sLengthMenu": "_MENU_ ",
                                                       "sEmptyTable": "No records found."
                                                   },
                                                   "aaSorting": [[1, "desc"]],
                                                   "aoColumnDefs": jsonArr,
                                                   "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                                                       orderStatus = aData.orderStatus;
                                                   },
                                                   "fnDrawCallback": function (oSettings) {
                                                       invokeTabLink(orderStatus);
                                                   }
                                               });
                                           }
                                           ///////////////////////////////////////////////////////
                                           else {

//                                           alert("HERE");

                                               var oTable = $('#' + tableId).dataTable({
                                                   "dom": 'T<"clear">lrtip',
                                                   "bProcesing": true,
                                                   "bServerSide": true,
                                                   "bAutoWidth": false,
                                                   "autoWidth": false,
                                                   "iDisplayLength": 10,
                                                   "responsive": true,
                                                   "bSortable": false,
                                                   "bInfo": true,
                                                   "bFilter": true,
                                                   "bLengthChange": true,
                                                   "bDestroy": true,
//                                                   "aaData" : aData,
                                                   "sAjaxSource": "${pageContext.request.contextPath}/PharmacyPortal/listing?filter=" + $("#filterFld").val() + "&search=" + $("#searchTitle_1").val() + "&mbrReq=" + $("#searchByMbrSvcReq").val() + "&deliveryPref=" + $("#searchByDeliverySvc").val() + "&rxStatus=" + $("#searchByOrderStatus").val()+ "&searchField=" + $("#searchByddl").val() + "&searchValue=" + $(".searchBy").val(),
//                                                   "sAjaxDataProp":"dataTableDataStr",
                                                   "aoColumns": [
    ${colMap['tab7']}
                                                   ],
                                                   "fnServerData": function (sSource, aoData, fnCallback) {

                                                       $.ajax({
                                                           "dataType": 'json',
                                                           "type": "GET",
                                                           "url": sSource,
                                                           "data": aoData,
                                                           "success": fnCallback
                                                       });
                                                   },
                                                   "sPaginationType": "full_numbers",
                                                   "oLanguage": {
                                                       "sLengthMenu": "_MENU_ ",
                                                       "sEmptyTable": "No records found."
                                                   },
                                                   "aaSorting": [[1, "desc"]],
                                                   "aoColumnDefs": jsonArr,
                                                   "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                                                       orderStatus = aData.orderStatus;
                                                   },
                                                   "fnDrawCallback": function (oSettings) {
                                                       invokeTabLink(orderStatus);
                                                   }
                                               });
                                           }
                                       }
                                       $(document).ready(function ( ) {
                                           page1Data();
                                           window.pharmacyNotification.populateAppUrl('${pageContext.request.contextPath}');
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

                                       function searchRecord(id) {
                                           page1Data();
                                           //Clear previous filters on table
                                           //fnResetAllFilters();
                                           //Create new filter for table
//                                           var colIndex = 1;
//                                           if ($("#" + id).val().length == 0 || $("#" + id).val().length == 3 || $("#" + id).val().length == 5 || $("#" + id).val().length >= 9) {
////                                               $('#' + $("#tableId").val()).dataTable().fnFilter($("#" + id).val(), colIndex);
//                                               page1Data();
//                                           }
//                                           else if ($("#" + id).val().length == 0) {
//                                               var tab = '${tab}';
//                                               if (tab == 'tab1') {
//                                                   location.href = '${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest1';
//                                               } else if (tab == 'tab2') {
//                                                   location.href = '${pageContext.request.contextPath}/PharmacyPortal/interpretedImages';
//                                               } else if (tab == 'tab3') {
//                                                   location.href = '${pageContext.request.contextPath}/PharmacyPortal/waitingPtResponse';
//                                               } else if (tab == 'tab4') {
//                                                   location.href = '${pageContext.request.contextPath}/PharmacyPortal/processRequest';
//                                               } else if (tab == 'tab5') {
//                                                   location.href = '${pageContext.request.contextPath}/PharmacyPortal/shippingDelivery';
//                                               } else if (tab == 'tab6') {
//                                                   location.href = '${pageContext.request.contextPath}/PharmacyPortal/cancelledRequest';
//                                               }
//                                               else if (tab == 'tab7') {
//                                                   location.href = '${pageContext.request.contextPath}/PharmacyPortal/responseReceived';
//                                               }
//                                           }
//        $(this).val('');
                                       }
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

                                           // selectedTab(selectTab);
                                       });
                                       /////////////////////////////////////////////////////////////
                                       $('#frmDate').datetimepicker({timepicker: false, format: 'm/d/Y',
                                           onChangeDateTime: function (dp, $input) {
                                               jQuery('#datetimepicker').datetimepicker('hide'); //
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
                                       function selectedTab(selectedTab) {
                                           $("li").removeAttr("class");
                                           $("#newRequestTab").attr("class", "active");
                                           if (selectedTab == 'tab1') {
//                                               $('#home').attr("class", "tab-pane");
                                               $('#rowTab > :nth-child(1)').tab('show');
                                               $('#home').attr("class", "tab-pane active");
                                               $('#rowTab  li:nth-child(1)').attr("class", "activeUserTab");
                                           } else if (selectedTab == 'tab2') {
                                               $('#home').attr("class", "tab-pane");
                                               $('#rowTab > :nth-child(4)').tab('show');
                                               $('#profile').attr("class", "tab-pane active");
                                               $('#rowTab  li:nth-child(4)').attr("class", "activeUserTab");
                                           } else if (selectedTab == 'tab3') {
                                               $('#home').attr("class", "tab-pane");
                                               $('#rowTab > :nth-child(3)').tab('show');
                                               $('#messages').attr("class", "tab-pane active");
                                               $('#rowTab  li:nth-child(3)').attr("class", "activeUserTab");
                                           } else if (selectedTab == 'tab4') {
                                               $('#home').attr("class", "tab-pane");
                                               $('#rowTab > :nth-child(5)').tab('show');
                                               $('#prequest').attr("class", "tab-pane active");
                                               $('#rowTab  li:nth-child(5)').attr("class", "activeUserTab");
                                           } else if (selectedTab == 'tab5') {
                                               $('#home').attr("class", "tab-pane");
                                               $('#rowTab > :nth-child(6)').tab('show');
                                               $('#shipdelivery').attr("class", "tab-pane active");
                                               $('#rowTab  li:nth-child(6)').attr("class", "activeUserTab");
                                           } else if (selectedTab == 'tab6') {
                                               $('#home').attr("class", "tab-pane");
                                               $('#rowTab > :nth-child(7)').tab('show');
                                               $('#crequest').attr("class", "tab-pane active");
                                               $('#rowTab  li:nth-child(7)').attr("class", "activeUserTab");
                                           } else if (selectedTab == 'tab7')
                                           {
                                               $('#home').attr("class", "tab-pane");
                                               $('#rowTab > :nth-child(2)').tab('show');
                                               $('#presponse').attr("class", "tab-pane active");

                                               $('#rowTab  li:nth-child(2)').attr("class", "activeUserTab");
                                           }
                                       }
                                       function invokeTabLink(orderStatus) {
                                           return;
//                                           alert("orderstatus "+orderStatus)
//                                           if (orderStatus == '${filter}') {
//                                               return;
//                                           }
//                                           if (orderStatus == 'Pending') {
//                                               location.href = '${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest1?search=' + $("#searchTitle_1").val();
//                                           } else if (orderStatus == 'Pending Review') {
//                                               location.href = '${pageContext.request.contextPath}/PharmacyPortal/interpretedImages?search=' + $("#searchTitle_1").val();
//                                           } else if (orderStatus == 'Waiting Pt Response') {
//                                               location.href = '${pageContext.request.contextPath}/PharmacyPortal/waitingPtResponse?search=' + $("#searchTitle_1").val();
//                                           } else if (orderStatus == 'Filled') {
//                                               location.href = '${pageContext.request.contextPath}/PharmacyPortal/processRequest?search=' + $("#searchTitle_1").val();
//                                           } else if (orderStatus == 'Shipped') {
//                                               location.href = '${pageContext.request.contextPath}/PharmacyPortal/shippingDelivery?search=' + $("#searchTitle_1").val();
//                                           } else if (orderStatus == 'Cancelled') {
//                                               location.href = '${pageContext.request.contextPath}/PharmacyPortal/cancelledRequest?search=' + $("#searchTitle_1").val();
//                                           }
//                                           else if (orderStatus == 'Response Received') {
//                                               location.href = '${pageContext.request.contextPath}/PharmacyPortal/responseReceived?search=' + $("#searchTitle_1").val();
//                                           }
                                       }
                                       function enableSearchBtn() {
                                           if ($("#searchTitle_1").val().length > 0 || $("#searchByMbrSvcReq").val() !== "" ||
                                                   $("#searchByDeliverySvc").val() !== "" || $("#searchByOrderStatus").val() !== "" || $(".searchBy").val().length > 0) {
                                               $("#btnSearch").removeAttr("disabled");
                                           } else {
                                               $("#btnSearch").attr("disabled", "disabled");
                                           }
                                       }
                                       function resetSearchField() {
                                           $("#searchTitle_1").val("");
                                           $("#searchByMbrSvcReq").val("");
                                           $("#searchByDeliverySvc").val("");
                                           $("#searchByOrderStatus").val("");
                                           $(".searchBy").val("");
                                           $("#searchByddl").val("Rx");
                                           $("#searchTitle_1").attr("style", "width:51%;display:none;");
                                           $(".searchBy").attr("style", "width:51%;");
                                           $(".searchBy").attr("placeholder", "Enter Prescriber#");
                                           $(".searchBy").attr("id", "searchBy");
                                          //$("#searchByddl").attr("style", "width:188%;");
                                           enableSearchBtn();
                                       }
                                       function searchBy(v) {
                                           $(".searchBy").val("");
                                           $("#searchTitle_1").val("");
                                           if (v == "") {
                                               $("#searchTitle_1").attr("style", "width:51%;display:none;");
                                               $(".searchBy").attr("style", "width:51%;");
                                               $(".searchBy").attr("placeholder", "Enter Prescriber#");
                                               $(".searchBy").attr("id", "searchBy");
//                                               $("#searchByddl").attr("style", "width:188%;");
                                               return;
                                           }
                                           if (v == "ptName") {
                                               console.log(v);
                                               $(".searchBy").attr("style", "width:51%;");
                                               $(".searchBy").attr("placeholder", "Enter PT Name");
                                               $(".searchBy").attr("id", v);
                                               $("#searchByddl").removeAttr("style");
                                           } else if (v == "mobileNo") {
                                               console.log(v);
                                               $(".searchBy").attr("style", "width:51%;");
                                               $(".searchBy").attr("placeholder", "Enter Phone#");
                                               $(".searchBy").attr("id", v);
                                               $("#searchByddl").removeAttr("style");
                                           } else if (v == "dob") {
                                               console.log(v);
                                               $("#searchByddl").removeAttr("style");
                                               $(".searchBy").attr("style", "width:51%;display:block;");
                                               $(".searchBy").attr("placeholder", "Enter Date of Birth");
                                               $(".searchBy").attr("id", v);
                                               $('#' + v).datetimepicker({timepicker: false, format: 'm/d/Y',
                                                   onChangeDateTime: function (dp, $input) {
                                                       $('#' + v).datetimepicker('hide');

                                                   }});
                                           } else if (v == "Rx") {
                                               console.log(v);
                                               $("#searchByddl").removeAttr("style");
                                               $("#searchTitle_1").attr("style", "width:51%;display:none;");
                                               $(".searchBy").attr("placeholder", "Enter Prescriber#");
//                                               $(".searchBy").attr("style", "width:51%;display:none;");
                                               return;
                                           }
                                       }

</script>
<script type="text/javascript">
    $(window).load(function () {
// alert("load");
        $("#loadingg").fadeOut();
    });

    $('#home').click({text: 'tab1', filter: "Pending"}, handleClick);
    $('#interpreted').click({text: 'tab2', filter: "Pending Review"}, handleClick);
    $('#waitingLnk').click({text: 'tab3', filter: "Waiting Pt Response"}, handleClick);
    $('#processedLnk').click({text: 'tab4', filter: "Filled"}, handleClick);
    $('#shippedLnk').click({text: 'tab5', filter: "Shipped"}, handleClick);
    $('#cancelledLnk').click({text: 'tab6', filter: "Cancelled"}, handleClick);
    $('#responseLnk').click({text: 'tab7', filter: "Response Received"}, handleClick);
    $("thead th").click(function ()
    {
        loadInformativeDataFromServer();
    });
</script>
</body>
</html>

