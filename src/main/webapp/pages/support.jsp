<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <jsp:include page="./inc/head.jsp" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Support</title>
    </head>
    <body onload="startTimer();window.support.populateAppUrl('${pageContext.request.contextPath}');">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />


                <div class="page-message" style="padding-left: 15px; padding-top: 5px;">
                    <div id="errorMessage" class="${not empty message?'message':'errorMessage'}">${not empty message?message:errorMessage}</div>
                    <br clear="all">
                </div>
                <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                    <br>
                    <div class="row grid" style="height: auto;margin-bottom: 40px;padding-bottom: 6px;background:#f7f7f7;">
                        <div class="search-grid">
                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li id="processli" class="active support_Menu"><a href="#tab1" data-toggle="tab" class="support_Menu">Processes</a></li>
                                    <li class="support_Menu" style="display: none;"><a href="#tab2" data-toggle="tab" class="support_Menu">IRF/DRF Processes</a></li>
                                    <li class="support_Menu" style="display: none;"><a href="#tab3" data-toggle="tab" class="support_Menu">Widget</a></li>
                                    <li class="support_Menu"><a href="#tab4" data-toggle="tab" class="support_Menu">Import Files</a></li>
                                    <li class="support_Menu"><a href="#tab5" data-toggle="tab" class="support_Menu">Remove Patient Profile</a></li>
                                    <li class="support_Menu"><a href="#tab6" data-toggle="tab" class="support_Menu">App Msgs</a></li>
                                    <li id="ordli" class="support_Menu"><a href="#tab7" data-toggle="tab" class="support_Menu">Orders</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tab1">
                                        <br>
                                        <form:form action="${pageContext.request.contextPath}/run" id="process" commandName="supportModel">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Select Processes:<span style="color:red">*</span></label>
                                                <div class="col-sm-3">
                                                    <form:select path="id" cssClass="form-control">
                                                        <form:option value="0" label="Please Select" />
                                                        <form:option value="1" label="Refill Reminder" />
                                                        <form:option value="2" label="Lets Get Started Reminder" />
                                                        <form:option value="3" label="End Of The Year Reminder" />
                                                        <form:option value="4" label="Annual Statement Reminder" />
                                                        <form:option value="5" label="Auto Deletion Order"/>
                                                        <form:option value="9" label="POA Expiry Reminder"/>
                                                    </form:select>
                                                    <div class="btn-group">
                                                        <div class="btndrop btn-margin" onclick="$('#process').submit();">
                                                            <a class="btn_Color">Run</a></div>

                                                    </div>
                                                </div>
                                            </div>

                                        </form:form>
                                    </div>
                                    <div class="tab-pane" id="tab2" style="display: none;">
                                        <br>
                                        <form:form action="${pageContext.request.contextPath}/executeRedempiton" id="lakerProcess" commandName="supportModel">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Email/Phone:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="cardHolderId" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Transaction Number:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="EmTransactionNum" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Claim Status:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="claimStatus" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Card Holder First Name:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="firstName" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Card Holder Last Name:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="lastName" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Fill Date:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="fillDate" id="datetimepicker" placeholder="mm/dd/yyyy" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Date of Birth:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="postingDate" id="datetimepicker1" placeholder="mm/dd/yyyy" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Out of Pocket:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:input path="outofpocket" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">File Type:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <form:radiobutton path="fileType" value="IRF"/> IRF <br>
                                                    <form:radiobutton path="fileType" value="DRF"/> DRF
                                                </div>
                                            </div>
                                            <div class="form-group" style="clear: both">
                                                <label class="col-sm-2 control-label">NDC Number:<span style="color:red">*</span></label>
                                                <div class="col-sm-2">
                                                    <c:if test="${empty ndcNumber}" >
                                                        <form:input path="ndcNumber[0]" cssClass="form-control"/>
                                                        <form:hidden path="remove"/>
                                                    </c:if>
                                                    <button class="fa fa-plus-circle" style="position:absolute;top:-2px;right:-15px;"  id="add" type="button" class="fa fa-plus-circle"></button>
                                                    <c:forEach items="${ndcNumber}" varStatus="loop">
                                                        <div id="ndcNumber${loop.index}">
                                                            <form:input path="ndcNumber[${loop.index}]" cssClass="form-control"/>
                                                            <c:if test="${loop.index ne 0}">
                                                                <button type="button" class="remove fa fa-minus-circle" data-index="${loop.index}" style="position:relative;right:-21px; margin-top:-23px;float:right;"></button>  
                                                            </c:if>
                                                        </div>
                                                    </c:forEach>

                                                </div>
                                            </div>
                                            <div class="form-group">

                                                <div class="col-sm-3">

                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-5">
                                                    <div class="btndrop btn-margin" onclick="$('#lakerProcess').submit();" style="margin-top: -23px;">
                                                        <a class="btn_Color">Run</a></div>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="tab-pane" id="tab3" style="display: none;">
                                        <br>
                                        <form:form action="${pageContext.request.contextPath}/runWidget" commandName="supportModel" cssClass="form-horizontal">

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Communication Path:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:select path="communicationMethod">
                                                        <form:option value="" label="Please Select" />
                                                        <form:option value="text" label="Text" />
                                                        <form:option value="email" label="Email" />
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Email/Phone:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:input path="communicationId"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Short Code:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:input path="shortCode"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Card Number:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:input path="cardNumber"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Event Name:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:input path="eventName"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Campaign Trigger:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:input path="ctrigger"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">User Id:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:input path="userId"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Password:<span style="color:red">*</span></label>
                                                <div class="col-sm-10">
                                                    <form:password path="password"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <div class="btndrop btn-margin" onclick="$('#supportModel').submit();">
                                                        <a class="btn_Color">Run</a></div>
                                                </div>
                                            </div>

                                        </form:form>



                                    </div>
                                    <div class="tab-pane" id="tab4">
                                        <form:form id="importFile" action="${pageContext.request.contextPath}/drugBrand/importDrugFile" commandName="supportModel" 
                                                   cssClass="form-horizontal" enctype="multipart/form-data" method="POST">
                                            <div class="form-group">
                                                <div >
                                                    <label >Type:</label>
                                                    <select id="docTypeOpt" name="docTypeOpt">
                                                        <option value="-1">----------------Please Select----------------</option>
                                                        <option value="1">Patient Guide</option>
                                                        <option value="2">Medication Guide</option>
                                                        <option value="3">Image</option>
                                                    </select>

                                                </div>
                                                <label class="col-sm-2 control-label">Enter Folder/Directory Name:<span style="color:red">*</span></label>
                                                <div class="col-sm-3">
                                                    <input type="file" name="files[]" multiple="true">
                                                    <div class="btn-group">
                                                        <div class="btndrop btn-margin" onclick="$('#importFile').submit();">
                                                            <a class="btn_Color">Run</a></div>

                                                    </div>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>

                                    <div class="tab-pane" id="tab5" style="margin-top: 10px;">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Enter Phone Number:<span style="color:red">*</span></label>
                                            <div class="col-sm-3">
                                                <input id="phoneNumberFid" type="text" name="phoneNumber" class="form-control"/>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="btn pull-left" style="background: #2071b6;color: #FFF;padding-top: 3px;" onclick="searchPatientName()">Search</div>
                                                <div id="btnRemove" class="btn pull-right disabled" style="background: #2071b6;color: #FFF;padding-top: 3px;" onclick="validateRemovePatientField();">Remove</div>
                                            </div>
                                            <div class="col-sm-5">&nbsp;</div>
                                            <div class="col-sm-12">
                                                <label class="col-sm-4 control-label" style="padding-left: 3px;">Patient Name: <span id="patientName" style="font-size: 14px;font-weight: bold;color: black;"></span></label>
                                            </div>
                                            <div class="col-sm-12">
                                                <label class="col-sm-2" style="padding-left: 3px;"><input id="chkDelAll" type="checkbox" value="All" name="tblName" onclick="chkAll()"/> Delete All</label>
                                                <label class="col-sm-2"><input id="chkPaymentInfo" type="checkbox" value="PatientPaymentInfo" name="tblName"/> PatientPaymentInfo</label>
                                                <label class="col-sm-2"><input id="chkDeliveryAddress" type="checkbox" value="PatientDeliveryAddress" name="tblName"/> PatientDeliveryAddress</label>
                                                <label class="col-sm-2"><input id="chkPatientInsuranceDetails" type="checkbox" value="PatientInsuranceDetails" name="tblName"/> PatientInsuranceDetails</label>
                                                <label class="col-sm-2"><input id="chkDependents" type="checkbox" value="PatientProfileMembers" name="tblName"/> Dependents</label>
                                                <label class="col-sm-2"><input id="chkDrugSearches" type="checkbox" value="DrugSearches" name="tblName"/> DrugSearches</label>
                                            </div>

                                            <div class="col-sm-12">
                                                <label class="col-sm-2" style="padding-left: 3px;"><input id="chkOrders" type="checkbox" value="Orders" name="tblName"/> Orders</label>
                                                <label class="col-sm-2"><input id="chkMsgs" type="checkbox" value="NotificationMessages" name="tblName"/> Messages</label>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="tab-pane" id="tab6" style="margin-top: 10px;">
                                        <form:form id="sendAppMsgsFrm" action="${pageContext.request.contextPath}/sendMsgs" commandName="supportModel" cssClass="form-horizontal" onsubmit="return validateMsgsField();">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Enter Phone Number:<span style="color:red">*</span></label>
                                                <div class="col-sm-3">
                                                    <form:input id="phoneNoFid" path="phoneNo" cssClass="form-control" maxlength="10"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Select Message:<span style="color:red">*</span></label>
                                                <div class="col-sm-3">
                                                    <form:select path="messageId" cssClass="form-control">
                                                        <form:option value="0" label="Please Select" />
                                                        <c:forEach var="row" items="${campaignMessagesList}">
                                                            <form:option value="${row.messageId}">${row.messageType.messageTypeTitle}</form:option>
                                                        </c:forEach>
                                                    </form:select>
                                                    <div class="btn-group">
                                                        <div class="btndrop btn-margin" onclick="$('#sendAppMsgsFrm').submit();">
                                                            <a class="btn_Color">Run</a>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="tab-pane" id="tab7" style="margin-top: 10px;">
                                        <form:form id="orderDetailsFrm" action="${pageContext.request.contextPath}/orderDetailList" commandName="supportModel" cssClass="form-horizontal" onsubmit="return validateOrderDetailsField();">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Enter Phone Number:<span style="color:red">*</span></label>
                                                <div class="col-sm-3">
                                                    <form:input id="mobileNoFid" path="phoneNo" cssClass="form-control" maxlength="10"/>
                                                    <div class="btn-group">
                                                        <div class="btndrop btn-margin" onclick="$('#orderDetailsFrm').submit();">
                                                            <a class="btn_Color">Search</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group" style="padding-left: 24px;">
                                                <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px;width: 98%;">
                                                    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                                                    <div class="contents">                                   
                                                        <div class="table-box">
                                                            <input id="orderDetailList" type="hidden" value="${fn:length(orderDetailList)}"/>
                                                            <table class="display" id="example" class="display table">
                                                                <thead style="height:36px;">
                                                                    <tr class="row">  
                                                                        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                            Order#
                                                                        </th>
                                                                        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                            Patient Name
                                                                        </th>
                                                                        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                            Mobile#
                                                                        </th>
                                                                        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                            Fill Date
                                                                        </th>
                                                                        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                            Status
                                                                        </th>
                                                                        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2" style="text-align: center;">
                                                                            Action
                                                                        </th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var="row" items="${orderDetailList}">
                                                                        <fmt:formatDate var="fmtDate" value="${row.filledDate}" pattern="MM/dd/yyyy"/>
                                                                        <tr class="row grid-row">
                                                                            <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                                <span id="">${row.id}</span>
                                                                            </td>
                                                                            <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                                <span id="">${row.firstName} ${row.lastName}</span>
                                                                            </td>
                                                                            <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                                <span id="">${row.mobileNumber}</span>
                                                                            </td>
                                                                            <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2">

                                                                                ${fmtDate}

                                                                            </td>
                                                                            <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                                                <span id="">${row.orderStatusName}</span>
                                                                            </td>
                                                                            <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 text-left-ipad padd-left-16-ipad" style="text-align: center;">
                                                                                <span id="btnEdit${row.id}">
                                                                                    <img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20" onclick="window.support.displayBtnFn('${row.id}', '${fmtDate}')" title="Edit"/>
                                                                                </span>
                                                                                <span id="btnRefillMsg${row.id}">
                                                                                    <!--<img src="${pageContext.request.contextPath}/resources/images/SendMSG.png" width="20" onclick="window.support.sendRefillMsgs()" title="Send refill reminder msgs"/>-->
                                                                                    <input type="hidden" id="refillreminderhidden" value="${row.id}"/>
                                                                                </span>
                                                                                <span id="btnUpdate${row.id}" style="display: none;position: relative;top:20px;">
                                                                                    <div id="btnSave${row.id}" class="btn_Color" style="cursor: pointer;height: 18px;" onclick="window.support.updateFillDate('${row.id}')">Save</div>
                                                                                    <div id="btnCancel${row.id}" class="btn_Color" onclick="window.support.cancelFn('${row.id}')" style="cursor: pointer;">Cancel</div>

                                                                                </span>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>


                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>

                        </div>


                    </div>
                </div>
                <!----------------------------Refill Reminder Message Model----------------------------------->
                <div class="modal fade financial_modal" id="refillmodel" role="dialog">
                    <div class="modal-dialog">
                        <input type="hidden" id="orderIdhiden"/>
                        <!-----  Modal content--->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4>Refill Reminder Message</h4>
                            </div>
                            <div class="modal-body clearfix">
                                <div class="col-sm-6"><label style="float: left;">Fill Date:</label>
                                    <input id="nextRefill" name="nextRefillDateFId" class="nextRefillDate" type="text" style="width: 85px; color: black; padding: 3px; " value="${fmtDate}" readonly="true"/>

                               <!--<input id="refill" name="nextRefillDateFId" class="nextRefillDate" type="text" style="width: 85px;" value="${fmtDate}" readonly="true"/>-->
                                </div>


                                <div class="col-sm-12">

                                    <input  id="sendRefillreminder" type="button" value="Send Refill Reminder " name="Submit" class="btn btn-primary" style="background: #2171b6; color: white;line-height: 0px;text-align: center;" onclick="window.support.sendRefillMsgs()"/>
                                    <input id="savenextRefillDate" type="button" value="Save" name="Submit" class="btn btn-primary" style="background: #2171b6; color: white;line-height: 0px;text-align: center;" onclick="window.support.updateFillDate()"/>
                                    <input type="button" value="Close" class="btn btn-primary" style="background: #2171b6; color: white;line-height: 0px;text-align: center;" data-dismiss="modal"/>
                                </div>

                                -------------------------------------------------------------------------------------------------------------

                            </div>

                        </div>

                    </div>
                </div>
                <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
                <!----------------------------------------------------------------->

                <script type="text/javascript">
                    $(document).ready(function () {
                        var totalorders = $("#orderDetailList").val();
                        console.log("totalorders " + totalorders);
                        if (totalorders > 0) {
                            $("#ordli").attr("class", "support_Menu active");
                            $("#tab7").attr("class", "tab-pane active");
                            $("#processli").attr("class", "support_Menu");
                            $("#tab1").attr("class", "tab-pane");
                        }
                    });
                    $('#datetimepicker,#datetimepicker1').datetimepicker({timepicker: false,
                        format: 'm/d/Y',
                        onChangeDateTime: function (dp, $input) {
                            jQuery('#datetimepicker').datetimepicker('hide');
                            jQuery('#datetimepicker1').datetimepicker('hide');
                        }

                    });
                    $('#nextRefill').datetimepicker({timepicker: false,
                        format: 'm/d/Y',
                        maxDate: 0,
                        onChangeDateTime: function (dp, $input) {
                            jQuery('.nextRefillDate').datetimepicker('hide');
                        }

                    });
                    $(function () {
                        $("input[name='tblName']").prop("disabled", true);
                        if (window.location.pathname == "/orderDetailList") {
                            $("#ordli").attr("class", "active support_Menu");
                            $("#tab7").attr("class", "tab-pane active");
                            $("#processli").attr("class", "support_Menu");
                            $("#tab1").attr("class", "tab-pane");
                            $("#phoneNoFid").val("");
                        }
                        var index = ${fn:length(supportModel.ndcNumber)};
                        if (index == 0) {
                            index = 1;
                        }
                        $("#add").off("click").on("click", function () {

                            $(this).before(function () {
                                var html = '<div id="ndcNumber' + index + '" class="">';
                                html += '<input class="form-control" type="text" id="ndcNumber' + index + '" name="ndcNumber[' + index + ']" style="float:left;"/>';

                                html += '<button type="button" class="remove fa fa-minus-circle" data-index="' + index + '" style="position:relative;right:-21px; margin-top:-23px;float:right;"></button> ';
                                html += "</div>";
                                $("#ndcNumber" + index).show();
                                index++;
                                return html;
                            });

                            return false;
                        });
                        $('body').on('click', '.remove', function () {
                            var index2remove = $(this).data("index");
                            $("#ndcNumber" + index2remove).remove();
                            index--;
                            return false;
                        });

                    });
                    function hideMessage() {
                        document.getElementById("errorMessage").style.display = "none";
                    }

                    function startTimer() {
                        window.setInterval("hideMessage()", 8000);  // 5000 milliseconds = 5 seconds
                    }
                    function copyFiles() {
                        var sourcePath = $("#sourcePath").val();
                        if (sourcePath == "") {
                            alert("Required");
                            return false;
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/importFile?sourcePath=" + sourcePath,
                            dataType: 'json',
                            contentType: 'application/json',
                            success: function (data) {
                                alert(data.errorMessage);
                            },
                            error: function (e) {
                                alert('Error while request...' + e);
                            }
                        });
                    }
                    function delPatinetProfile() {
                        var phoneNumber = $("#phoneNumberFid").val();
                        var rdoTblName = document.getElementsByName('tblName');
                        var tblName_value = [];
                        for (var i = 0; i < rdoTblName.length; i++) {
                            if (rdoTblName[i].checked) {
                                tblName_value.push(rdoTblName[i].value);
                            }
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/delPatientProfile?phoneNumber=" + phoneNumber + "&tblName=" + tblName_value,
                            dataType: 'json',
                            type: "POST",
                            contentType: false,
                            success: function (data) {
                                if (data.statuscode == 200) {
                                    $("#errorMessage").text(data.errorMessage);
                                    $("#errorMessage").attr("class", "message");
                                    $("#errorMessage").attr("style", "display:block;");
                                } else {
                                    $("#errorMessage").text(data.errorMessage);
                                    $("#errorMessage").attr("class", "errorMessage");
                                    $("#errorMessage").attr("style", "display:block;");
                                }
                                hideModel();
                            },
                            error: function (e) {
                                $("#errorMessage").text('Error while request...' + e);
                                $("#errorMessage").show();
                                hideModel();
                            }
                        });
                    }
                    function validateMsgsField() {
                        if ($("#phoneNoFid").val().length == 0) {
                            $("#errorMessage").text("Enter patient phone number.");
                            $("#errorMessage").attr("class", "errorMessage");
                            $("#errorMessage").attr("style", "display:block;");
                            return  false;
                        }
                        if ($("#phoneNoFid").val().length < 10) {
                            $("#errorMessage").text("Enter 10 digit phone number.");
                            $("#errorMessage").attr("class", "errorMessage");
                            $("#errorMessage").attr("style", "display:block;");
                            return  false;
                        }
                        if ($("#messageId").val() == 0) {
                            $("#errorMessage").text("Please select message.");
                            $("#errorMessage").attr("class", "errorMessage");
                            $("#errorMessage").attr("style", "display:block;");
                            return  false;
                        }
                        return true;
                    }
                    function validateOrderDetailsField() {
                        return window.support.validateOrderDetailsField();
                    }
                    function searchPatientName() {
                        var phoneNumber = $("#phoneNumberFid").val();
                        if (phoneNumber.length == 0) {
                            $("#errorMessage").text("Enter patient phone number.");
                            $("#errorMessage").show();
                            return;
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/searchPatient?phoneNumber=" + phoneNumber,
                            dataType: 'json',
                            type: "POST",
                            contentType: false,
                            success: function (data) {
                                if (data.statuscode == 200) {
                                    $("#patientName").text(data.errorMessage);
                                    $("#patientName").attr("style", "font-size: 14px;font-weight: bold;color: black;");
                                    $("#btnRemove").attr("class", "btn pull-right");
                                    $("input[name='tblName']").prop("disabled", false);
                                } else {
                                    $("#patientName").text(data.errorMessage);
                                    $("#patientName").attr("style", "font-size: 14px;font-weight: bold;color: red;");
                                    $("#btnRemove").attr("class", "btn pull-right disabled");
                                    $("input[name='tblName']").prop("checked", false);
                                    $("input[name='tblName']").prop("disabled", true);
                                }
                            },
                            error: function (e) {
                                $("#errorMessage").text('Error while request...' + e);
                                $("#errorMessage").show();
                            }
                        });
                    }
                    function isDeletePatient() {
                        var dialog = $("#dialog").dialog({
                            closeOnEscape: false,
                            autoOpen: false,
                            modal: true,
                            height: 170,
                            width: '22%',
                            cache: false,
                            autoResize: true,
                            open: function (event, ui) {
                                //hide titlebar.
                                $(".ui-dialog-titlebar").css("font-size", "14px");
                                $(".ui-dialog-titlebar").css("font-weight", "normal");
                                $(".ui-dialog-titlebar").hide();
                            },
                            close: function ()
                            {
                                $(this).dialog('close');
                                $(this).dialog('destroy');
                            }
                        });
                        var logoutHtml = '<div  style="text-align: center; ">'
                                + '<p style="margin-bottom: 20px; color:#E70000;">You want to delete this patient?</p>'
                                + '<div style="float: left; width: 49%; text-align: right; font-size: 14px;">'
                                + '<button id="btnYes"  class="btn btnisdel"  type="submit" onclick="delPatinetProfile();" style="background: #2071b6;color: #FFF;">Yes</button>'
                                + "</div>"
                                + '<div style="float: right; width: 49%; text-align: left;font-size: 14px;  ">'
                                + '<button  class="btn btnisdel" type="button" onclick="hideModel();">No</button>'
                                + '</div>'
                                + '</div>';
                        $(dialog).html(logoutHtml);
                        $("#dialog").dialog("open");
                    }
                    function validateRemovePatientField() {
                        var valid = true;
                        var phoneNumber = $("#phoneNumberFid").val();
                        if (phoneNumber.length == 0) {
                            $("#errorMessage").text("Enter patient phone number.");
                            $("#errorMessage").show();
                            valid = false;
                        }
                        var rdoTblName = document.getElementsByName('tblName');
                        var tblName_value = [];
                        for (var i = 0; i < rdoTblName.length; i++) {
                            if (rdoTblName[i].checked) {
                                tblName_value.push(rdoTblName[i].value);
                            }
                        }
                        if (tblName_value.length == 0) {
                            $("#errorMessage").text("Select a check box.");
                            $("#errorMessage").show();
                            valid = false;
                        }
                        if (valid) {
                            if (tblName_value.indexOf("Orders") == 0) {
                                isRemoveAssociateOrderMsg();
                            } else {
                                isDeletePatient();
                            }
                        }
                        return valid;
                    }
                    function isRemoveAssociateOrderMsg() {
                        var dialog = $("#dialog").dialog({
                            closeOnEscape: false,
                            autoOpen: false,
                            modal: true,
                            height: 170,
                            width: '35%',
                            cache: false,
                            autoResize: true,
                            open: function (event, ui) {
                                //hide titlebar.
                                $(".ui-dialog-titlebar").css("font-size", "14px");
                                $(".ui-dialog-titlebar").css("font-weight", "normal");
                                $(".ui-dialog-titlebar").hide();
                            },
                            close: function ()
                            {
                                $(this).dialog('close');
                                $(this).dialog('destroy');
                            }
                        });
                        var logoutHtml = '<div  style="text-align: center; ">'
                                + '<p style="margin-bottom: 20px; color:#E70000;">Messages associated with this order will also be deleted. Do you want to contine?</p>'
                                + '<div style="float: left; width: 49%; text-align: right; font-size: 14px;">'
                                + '<button id="btnYes"  class="btn btnisdel"  type="submit" onclick="delPatinetProfile();" style="background: #2071b6;color: #FFF;">Yes</button>'
                                + "</div>"
                                + '<div style="float: right; width: 49%; text-align: left;font-size: 14px;  ">'
                                + '<button  class="btn btnisdel" type="button" onclick="hideModel();">No</button>'
                                + '</div>'
                                + '</div>';
                        $(dialog).html(logoutHtml);
                        $("#dialog").dialog("open");
                    }
                    function chkAll() {
                        if ($("#chkDelAll").is(':checked')) {
                            $("#chkPaymentInfo").prop("disabled", true);
                            $("#chkDeliveryAddress").prop("disabled", true);
                            $("#chkPatientInsuranceDetails").attr("disabled", true);
                            $("#chkDependents").attr("disabled", true);
                            $("#chkDrugSearches").attr("disabled", true);
                            $("#chkOrders").attr("disabled", true);
                            $("#chkMsgs").attr("disabled", true);
                            $("input[name='tblName']").prop("checked", true);
                        } else {
                            $("input[name='tblName']").prop("disabled", false);
                            $("input[name='tblName']").prop("checked", false);
                        }
                    }
                    function openOrdersDialog() {
                        if ($("#chkOrders").is(':checked')) {
                            isRemoveAssociateOrderMsg();
                        }
                    }
                </script>                                   
                <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/support.js"></script>
            </div>

            <jsp:include page="./inc/footer.jsp" />
    </body>
</html>
