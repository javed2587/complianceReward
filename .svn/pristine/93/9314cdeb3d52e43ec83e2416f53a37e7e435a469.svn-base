<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <div><i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Rx Transfer Request</div>
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Rx Transfer Request</h1>
                </div> <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/rxTransfer/view" commandName="transferDetail" method="Post">
                    <form:hidden path="id"/>
                    <form:hidden path="requestId" value="${transferRequest.id}"/>
                    <div class="page-message messageheading">
                        <div class="${not empty message?'message':'errorMessage'}" id="message">${not empty message?message:errorMessage}</div>
                        <br clear="all">
                    </div>
                    <div class="wrp clearfix" style="padding-left: 15px; padding-right: 15px;margin-bottom: 50px;">
                        <div style="float: right;">
                            <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/rxTransfer/list'"><a href="${pageContext.request.contextPath}/rxTransfer/list" class="btn_Color">Cancel</a></div>
                            <button class="btndrop btn-margin" style="color:white;">Save</button>
                        </div>
                        <br><br>
                        <div class="search-grid">  
                            <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12 background1 transferRequestpanel" id="panel${0}">          
                                <div class="col-sm-12 col-xs-12" style="padding-top:5px;"><span style="color:#3AB781;padding-top: 1px;font-weight: bold;">User Detail</span></div>
                                <div class="col-sm-12 col-xs-12">   
                                    <div class="col-sm-6 col-xs-6" style="padding-left:0px">
                                        <label>User Name</label>
                                        <div class="patientprofilefont">
                                            ${transferRequest.patientProfile.firstName} ${transferRequest.patientProfile.lastName}
<!--                                             ken Brukett-->
                                        </div>
                                    </div>

                                    <div class="col-sm-6 col-xs-6" style="padding-left:0px">
                                        <label>Transfer Request On</label>
                                        <div class="patientprofilefont">
                                            <fmt:formatDate var="requestOn" pattern="MM/dd/yyyy HH:mm" value="${transferRequest.requestedOn}"/>${requestOn}
                                        </div>
                                    </div>
                                    <div style="clear:both;"></div>

                                    <div class="col-sm-6 col-xs-6 padd-left-1920" style="padding-left:0px">
                                        <label>Primary Phone</label>
                                        <div class="patientprofilefont">
                                            ${fn:substring(transferRequest.patientProfile.mobileNumber, 0, 3)}-${fn:substring(transferRequest.patientProfile.mobileNumber, 3, 6)}-${fn:substring(transferRequest.patientProfile.mobileNumber, 6, 10)}
                                        </div>
                                    </div>

                                    <div class="col-sm-6 col-xs-6 padd-left-1920" style="padding-left:0px">
                                        <label>Primary Email</label>
                                        <div class="patientprofilefont">
                                            ${transferRequest.patientProfile.emailAddress}
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12 background1 transferRequestpanel1" id="panel1">          
                                <div style="width: 100%;padding-top: 5px;">
                                    <div class="col-lg-6 col-sm-12 col-xs-12">
                                        <div style="color: #3AB781;padding-top: 1px;font-weight: bold;">Rx Detail</div>
                                        <div style="width: 100%;">
                                            <div class="col-lg-4 col-sm-4 col-xs-6" style="padding-left:0px">
                                                <label>Patient Name</label>
                                                <div class="patientprofilefont">
                                                    ${transferRequest.patientName}
                                                </div>
                                            </div>
                                            <div class="col-sm-4 col-xs-6" style="padding-left:0px">
                                                <label>Pharmacy Name</label>
                                                <div class="patientprofilefont">
                                                    ${transferRequest.pharmacyName}
                                                </div>
                                            </div>
                                            <div class="col-sm-4 col-xs-6" style="padding-left:0px">
                                                <label>Drug Name</label>
                                                <div class="patientprofilefont">${transferRequest.drug}</div>
                                            </div>
                                            <div style="clear:both;" class="hidden-xs"></div>
                                            <div class="col-sm-4 col-xs-6 padd-left-1920">
                                                <label>Rx Number</label>
                                                <div class="patientprofilefont">
                                                    ${transferRequest.rxNumber}
                                                </div>
                                            </div>
                                            <div class="col-sm-4 col-xs-6 padd-left-1920">
                                                <label>Pharmacy Phone</label>
                                                <div class="patientprofilefont">
                                                    ${fn:substring(transferRequest.pharmacyPhone, 0, 3)}-${fn:substring(transferRequest.pharmacyPhone, 3, 6)}-${fn:substring(transferRequest.pharmacyPhone, 6, 10)}
                                                </div>
                                            </div>
                                            <div class="col-sm-4 col-xs-6 padd-left-1920">
                                                <label>Qty Desired</label>
                                                <div class="patientprofilefont">
                                                    ${transferRequest.quantity}
                                                </div>
                                            </div>
                                            <div style="clear:both;"></div>
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-sm-12 col-xs-12 video-margin">
                                        <c:choose>
                                            <c:when test="${not empty transferRequest.video}">
                                                <div class="col-lg-8 col-sm-12 col-xs-12" style="text-align: center;padding-top: 30px;">
                                                    <input id="playVideo" type="button" class="btndrop btn-margin" style="color: white; width: 60%; font-size: 20px; padding-top: 7px; padding-bottom: 38px;" value="Play Video">
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-lg-8 col-sm-12 col-xs-12" style="text-align: center;padding-top: 30px;">
                                                    <input id="playVideo" type="button" class="btndrop btn-margin" 
                                                           style="color: white; width: 60%; font-size: 20px; padding-top: 7px; 
                                                           padding-bottom: 38px;" value="No video" disabled="true">
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        <!-----------
                                        <div class="col-lg-2 col-sm-12 col-xs-12 video-align" style="padding-top: 12px;">
                                            <video id="media-video-image" width="165">
                                                <source src="${transferRequest.video}"/>
                                            </video>
                                        </div>--------------->
                                        <!---------------------------------------------------------------->
                                        <c:choose>
                                            <c:when test="${not empty transferRequest.drugImg}">
                                                <div style="height:20px;">
                                                    <img src="${transferRequest.drugImg}" alt="Associated Image" class="frontsidecardimg"/>

                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div style="height: 50px;">
                                                    <span style="color: red;">No Image associated.</span>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        <!---------------------------------------------------------------->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 50px;padding-bottom:20px;background:#f7f7f7;float: left;">

                            <div class="search-grid">
                                <div class="col-sm-12" style="padding-top:5px;"><span style="color:#3AB781;padding-top: 1px;font-weight: bold;">Transfer Detail</span></div>
                                <div class="col-sm-8 col-xs-12 insurancefield">
                                    <div class="form-group fgPL-zero" style="position: relative; top: 14px;padding-left: 0px;">
                                        <div class="col-sm-3">
                                            <label>Patient First Name:<span style="color:red">*</span></label>
                                            <form:input path="firstName" cssClass="form-control" maxlength="50"/>
                                            <form:errors path="firstName" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Patient Last Name:<span style="color:red">*</span></label>
                                            <form:input path="lastName" cssClass="form-control" maxlength="50"/>
                                            <form:errors path="lastName" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Prescriber NPI:<span style="color:red">*</span></label>
                                            <form:input path="npi" cssClass="form-control" maxlength="10"/>
                                            <form:errors path="npi" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>New Rx #:<span style="color:red">*</span></label>
                                            <form:input path="rxNumber" cssClass="form-control" maxlength="12"/>
                                            <form:errors path="rxNumber" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div style="clear:both;"></div>
                                    <div class="form-group fgPL-zero" style="position: relative; top: 14px;padding-left: 0px;">
                                        <div class="col-sm-3">
                                            <label>Drug Name:<span style="color:red">*</span></label>
                                            <form:hidden id="searchDrugNameId" path="tempDrugName" autocomplete="off" value=""/>
                                            <input id="drugNameDbId" type="hidden" value="${drugDetail.drugBasic.drugBasicId}"/>
                                            <form:input id="drugNameId" path="drugName"	 type="text" class="form-control selectpicker show-tick" oninput="window.transferRequest.lookUpDrugName();" onblur="window.transferRequest.lookUpDrugTypeByBrandName();" value="${transferDetail.drugName}"/>

                                            <form:errors path="drugName" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Drug Type:<span style="color:red">*</span></label>
                                            <form:select id= "drugTypeId" path="drugType" cssClass="form-control" onchange="window.transferRequest.lookUpDrugStrength();">
                                                <form:option value="">Select Drug Type</form:option>
                                                <form:options items="${lst_drugTypes}" itemLabel="type" itemValue="type"/>
                                            </form:select>
                                            <form:errors path="drugType" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <div class="col-sm-6 col-xs-12 padding-right" style="padding-left: 0px;">
                                                <label>Drug Strength:<span style="color:red">*</span></label>
                                                <form:select id="drugStrength" path="strength" cssClass="form-control" onchange="window.transferRequest.defaultQtyByStrength(this.value);">
                                                    <form:option value="">Select Drug Strength</form:option>
                                                    <form:options items="${lst_drugStrength}" itemLabel="strength" itemValue="strength"/>
                                                </form:select>
                                                <form:errors path="strength" cssClass="errorMessage"/>
                                            </div>
                                            <div class="col-sm-6 col-xs-12 padding">
                                                <label>Default Qty:</label>
                                                <form:select id="defaultQty" path="defaultQty" cssClass="form-control">
                                                    <form:option value="0">Select Default Qty</form:option>
                                                    <form:options items="${lst_drugDefaultQty}" itemLabel="defQty" itemValue="defQty"/>
                                                </form:select>
                                                <form:hidden path="ndc"/>
                                                <form:errors path="defaultQty" cssClass="errorMessage"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <div class="col-sm-6 col-xs-12 padding-right" style="padding-left: 0px;">
                                                <label>Desired Qty:<span style="color:red">*</span></label>
                                                <form:input path="quantity" cssClass="form-control" onkeypress="return IsDigit(event)"/>
                                                <form:errors path="quantity" cssClass="errorMessage"/>
                                            </div>
                                            <div class="col-sm-6 col-xs-12 padding">
                                                <label>Day Supply:<span style="color:red">*</span></label>
                                                <form:input path="daysSupply" cssClass="form-control" onkeypress="return IsDigit(event)"/>
                                                <form:errors path="daysSupply" cssClass="errorMessage"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="clear:both;"></div>
                                    <div class="form-group fgPL-zero" style="position: relative; top: 14px;padding-left: 0px;">
                                        <div class="col-sm-3">
                                            <div class="col-sm-6 padding-right" style="padding-left: 0px;">
                                                <label>Refill Allowed:<span style="color:red">*</span></label>
                                                <form:input path="refillAllowed" cssClass="form-control" onkeypress="return IsDigit(event)"/>
                                                <form:errors path="refillAllowed" cssClass="errorMessage"/>
                                            </div>
                                            <div class="col-sm-6 padding">
                                                <label>R Remaining:<span style="color:red">*</span></label>
                                                <form:input path="refillUsed" cssClass="form-control" onkeypress="return IsDigit(event)"/>
                                                <form:errors path="refillUsed" cssClass="errorMessage"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero" style="padding-left: 0px;">
                                        <div class="col-sm-3">
                                            <label>Last Fill Date:<span style="color:red">*</span></label>
                                            <form:input id="lastFillDate" path="lastFillDate" cssClass="form-control default-cursor" placeholder="MM/DD/YYYY" readonly="true"/>
                                            <form:errors path="lastFillDate" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero" style="padding-left: 0px;">
                                        <div class="col-sm-3">
                                            <label>Rx Expiry Date:<span style="color:red">*</span></label>
                                            <form:input id="expiryDate" path="expiryDate" cssClass="form-control default-cursor" placeholder="MM/DD/YYYY" readonly="true"/>
                                            <form:errors path="expiryDate" cssClass="errorMessage"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Status:</label><br>
                                            <form:radiobutton path="status" value="Pending"/> <span class="optionstatus">Pending &nbsp;</span>
                                            <form:radiobutton path="status" value="Transfered"/> <span class="optionstatus">Transfered</span>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-4">
                                            <label>Pharmacy Name:<span style="color:red">*</span></label>
                                            <form:input path="pharmacyName" cssClass="form-control"/>
                                            <form:errors path="pharmacyName" cssClass="errorMessage"/>

                                        </div>
                                        <div class="col-sm-2">
                                            <label>Pharmacy Phone:<span style="color:red">*</span></label>
                                            <form:input path="pharmacyPhone" cssClass="form-control" onkeypress="return IsDigit(event)" maxlength="10"/>
                                            <form:errors path="pharmacyPhone" cssClass="errorMessage"/>
                                        </div>
                                    </div>    


                                    <div style="clear:both;"></div>
                                    <div class="form-group fgPL-zero rmarks-margin-left" style="position: relative; top: 14px;padding-left: 4px;">
                                        <div class="col-sm-12 col-xs-12">
                                            <label>Comments:</label><br>
                                            <form:textarea path="remarks" cssClass="form-control" cssStyle="height: 80px;"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4 col-xs-12 instantnotification padding-left">
                                    <div class="form-group fgPL-zero padding-left-765">
                                        <div class="col-sm-12 col-xs-12">
                                            <label style="font-size: 14px;font-weight: normal;">Instant Notification:</label><br>
                                            <div style="background-color: white;height: 155px;overflow-y: auto; border: 1px solid #bebcc1;width: 99%;">
                                                <div id="currentDateTime" style="color: #2071b6;">01/30/2016 13:45 - </div>
                                                <div id="messageText">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been</div>
                                            </div>
                                        </div>
                                        <div class="col-sm-12 col-xs-12">
                                            <textarea id="notificationId" class="form-control" placeholder="Notification text" style="width: 99%;height: 100px;"></textarea>
                                        </div>
                                        <div class="col-sm-12 col-xs-12">
                                            <input type="button" class="btndrop btn-margin" style="color: white; width: 99%; font-size: 20px; padding-top: 7px; padding-bottom: 38px;" value="Send" onclick="sendNotification(${transferRequest.patientId})">
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>

            <!--  Play video dialog -->
            <div id="dialogPlayVideo" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;">
                <input id="zoomCurrentValue" type="hidden" value="500"/>
                <button onclick="playPause()">Play/Pause</button><button onclick="zoomOut();">Zoom Out</button><button onclick="zoomIn();">Zoom In</button>
                <video id="media-video" width="500">
                    <source src="${transferRequest.video}" type="video/mp4"/>
                </video>
            </div>    

            <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>
            <script type="text/javascript">
                    $(document).ready(function () {
                        var st = "${transferDetail.status}";
                        if (st === null || st === "") {
                            $("#status1").attr("checked", "checked");
                        }

                        /**
                         * 
                         * brandName search called
                         */
                        window.transferRequest.initTransferRequest();
                        if ($("#drugStrength").val() !== "0") {
                            window.transferRequest.defaultQtyByStrength($("#drugStrength").val());
                        }

                    });
                    $('#lastFillDate, #expiryDate').datetimepicker({timepicker: false,
                        format: 'm/d/Y',
                        onChangeDateTime: function (dp, $input) {
                            jQuery('#lastFillDate').datetimepicker('hide');
                            jQuery('#expiryDate').datetimepicker('hide');
                        }

                    });
                    function hideMessage() {
                        $(".errorMessage").hide();
                    }

                    function startTimer() {
                        window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
                    }
                    function populatedDrugStrength(parentVal, child) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rxTransfer/populateDrugStrength/" + parentVal,
                            dataType: "json",
                            success: function (data) {
                                $('[id="' + child + '"]')
                                        .find('option')
                                        .remove()
                                        .end()
                                        .append('<option value="">Select Drug Strength</option>')
                                        .val('Select Drug Strength');
                                $.each(data, function (index, element) {
                                    $('[id="' + child + '"]').append($('<option>', {value: element.strength, text: element.strength}));
                                });
                                $('[id="' + child + '"]').selectpicker('refresh');
                            },
                            error: function (e) {
                                alert('Error while request...' + e);
                            }
                        });
                    }
                    var dialog = null;
                    $('#playVideo').click(function () {
//                    var t=document.createElement("video").canPlayType("video/mp4"); // "maybe"
//                    document.createElement("video").canPlayType("video/m4v"); // ""
//                    document.createElement("video").canPlayType("video/x-m4v");

                        dialog = $("#dialogPlayVideo").dialog({
                            closeOnEscape: false,
                            autoOpen: false,
                            modal: true,
                            height: 'auto',
                            width: '525',
                            title: 'Drug Movie',
                            cache: false,
                            autoResize: true,
                            open: function (event, ui) {
                                //hide titlebar.
                                $(".ui-dialog-titlebar").css("font-size", "14px");
                                $(".ui-dialog-titlebar").css("font-weight", "normal");
                            },
                            close: function ()
                            {
                                $(this).dialog('close');
                                $(this).dialog('destroy');
                            }
                        });

                        $(dialog).dialog("open");
                        if ($("#media-video").get(0).paused) {
                            $("#media-video").get(0).play();
                        } else {
                            $("#media-video").get(0).pause();
                        }
                    });

                    function playPause() {
                        if ($("#media-video").get(0).paused)
                            $("#media-video").get(0).play();
                        else
                            $("#media-video").get(0).pause();
                    }
                    function zoomOut() {
                        var currentVal = $('#zoomCurrentValue').val();
                        var i_currentVal = parseInt(currentVal)
                        i_currentVal = i_currentVal - 20;
                        $("#dialog").dialog({width: i_currentVal});
                        $("#media-video").get(0).width = i_currentVal;
                        $('#zoomCurrentValue').val(i_currentVal);
                    }
                    function zoomIn() {
                        var currentVal = $('#zoomCurrentValue').val();
                        var i_currentVal = parseInt(currentVal);
                        i_currentVal = i_currentVal + 20;
                        $("#media-video").get(0).width = i_currentVal;
                        $('#zoomCurrentValue').val(i_currentVal);
                    }
                    function sendNotification(patientId) {
                        var data = new FormData();
                        if ($("#notificationId").val().trim() === "") {
                            return false;
                        }
                        data.append("patientId", patientId);
                        data.append("messageText", $("#notificationId").val());
                        var uploadURL = "${pageContext.request.contextPath}/rxTransfer/saveInstantNotification";
                        $.ajax({
                            url: uploadURL,
                            type: "POST",
                            contentType: false,
                            processData: false,
                            cache: false,
                            data: data,
                            success: function (response) {
                                alert(response);
                            }, error: function (e) {
                                alert('Error while request...' + e);
                            }
                        });

                    }
            </script>
        </div> <!-- /content -->
        <jsp:include page="./inc/footer.jsp" />
    </div> <!-- /wrapper -->
</body>
</html>
