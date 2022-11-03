<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <div><i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Patient Details.</div>
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Patient Details.</h1><div style="text-align: right; padding-top: 8px; margin-right: 15px;"> <img id="previousUserProfile" src="${pageContext.request.contextPath}/resources/images/Left.png" width="25" style="cursor: pointer;" onclick="previousUser()"/><img id="nextUserProfile" src="${pageContext.request.contextPath}/resources/images/Right.png" width="25" style="cursor: pointer;" onclick="nextUser()" /></div>
                </div> <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/patient/update" commandName="patientProfile" method="post" role="form" autocomplete="off" onsubmit="return validateField()">    
                    <form:hidden id="patientId" path="id"/>
                    <div class="page-message messageheading">
                        <div class="${not empty message?'message':'errorMessage'}" id="message">${not empty message?message:errorMessage}</div>
                        <br clear="all">
                    </div>
                    <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 
                    <div class="wrp clearfix" style="padding-left: 15px; padding-right: 15px;margin-bottom: 50px; ">
                        <div>    
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background_patientprofile" id="panel${0}" style="height: 270px">          
                                <div class="col-lg-12" style="width: 100%;padding-top: 5px;color: white;font-size: 15px;font-weight: bold; background-color: #2071b6 ">
                                    <div style="width: 14%;float: left;">
                                        ${fn:toUpperCase(patientProfile.firstName)} ${fn:substring(patientProfile.lastName, 0, 1)}
                                    </div>
                                    <div style="width: 10%;float: left;text-align: center;">
                                        ${patientProfile.gender eq 'M'?'MALE':'FEMALE'}
                                    </div>
                                    <div style="width: 15%;float: left;">
                                        <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.dob}"/> 
                                        <fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.dob}"/> 
                                        (${nowyear-dobyear} YEARS)
                                    </div>
                                    <div style="width: 15%;float: left;">
                                        MEMBER SINCE <fmt:formatDate pattern="MM/yyyy" value="${patientProfile.createdOn}"/>
                                    </div>
                                    <div style="width: 10%;float: left;">
                                        ${fn:length(patientProfile.patientProfileMembersList)}DEPENDENTS
                                    </div>
                                    <div style="width: 11%;float: left;">
                                        0 PROGRAM Rx's
                                    </div>
                                    <div style="width: 25%;float: left;text-align: right;">
                                        <fmt:formatDate pattern="EEEE, MMMM dd, yyyy hh:mm:ss" value="${patientProfile.createdOn}"/> CST
                                    </div>
                                </div>
                                <div class="col-sm-3  " style="margin-top: 8px;font-size: 12px; padding-left: 2px;" > 
                                    <div style="width: 100%;padding-top: 5px;padding-left: 12px;" class="background1 patientdetailpanel1">
                                        <div style="width: 50%;float: left;">
                                            <label style="font-weight: bold;">Phone #.</label>
                                        </div>
                                        <div style="width: 50%;float:right;">
                                            <label style="font-weight: bold; padding-top: 3px; ">Email Address</label>
                                        </div>
                                        <div style="width: 50%;float: left;color: black;">
                                            ${fn:substring(patientProfile.mobileNumber, 0, 3)}-${fn:substring(patientProfile.mobileNumber, 3, 6)}-${fn:substring(patientProfile.mobileNumber, 6, 10)}
                                        </div>
                                        <div style="color: black;width: 50%;float: right;">
                                            ${not empty patientProfile.emailAddress?patientProfile.emailAddress:'&nbsp;'} 
                                        </div>
                                        <div style="width: 100%;">
                                            <div class="form-horizontal form-inline">
                                                <label style="font-weight: bold; padding-top: 3px;">Allergies </label><img src="${pageContext.request.contextPath}/resources/images/edit.png" width="5%" style="margin-left:7px; cursor: pointer" onclick="window.patientInfo.ShowDialogBoxAllergy('${patientProfile.allergies}')"/>
                                            </div>
                                        </div>
                                        <div id="allergiesRec" style="color:${not empty patientProfile.allergies?'black;':'red'};width: 100%;">
                                            ${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'} 
                                        </div>
                                        <div style="width: 100%;">
                                            <label style="font-weight: bold;padding-top: 3px; ">Shipping Preference</label>
                                        </div>
                                        <div style="width: 100%;color:${not empty patientProfile.deliveryPreferenceId.name?'black;':'red'};">
                                            <c:choose>
                                                <c:when test="${not empty patientProfile.deliveryPreferenceId.name}">
                                                    ${patientProfile.deliveryPreferenceId.name} ($<fmt:formatNumber value="${patientProfile.deliveryPreferenceId.cost}" pattern="0.00"/>)
                                                </c:when>
                                                <c:otherwise>
                                                    No data available.
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3 background1 patientdetailpanel2 " style="margin-top: 8px;font-size: 12px;overflow: auto;" > 
                                    <div  style="padding-top:5px;">
                                        <div style="width: 100%;">
                                            <label style="font-weight: bold;padding-top: 3px; ">Shipping Address</label>
                                        </div>

                                        <input id="patientAddressId" type="hidden"/>
                                        <!-- haider shipping address -->
                                        <c:forEach var="shippingAddressVar" items="${patientProfile.patientDeliveryAddresses}">
                                            <div style="width: 100%;">
                                                <label style="font-weight: bold;padding-top: 3px; color: black;">${shippingAddressVar.description}</label><img src="${pageContext.request.contextPath}/resources/images/edit.png" width="5%" style="margin-left:7px; cursor: pointer" onclick="window.ssaSoftDeliveryAddress.LookupDeliveryAddressById(${shippingAddressVar.id})"/>
                                            </div>
                                            <div id="shippingAddress" style="width: 100%;color: black;">
                                                ${shippingAddressVar.address}, ${shippingAddressVar.apartment}, ${shippingAddressVar.city},${shippingAddressVar.state.name} ,${shippingAddressVar.zip}
                                            </div>
                                        </c:forEach>


                                    </div>
                                </div>
                                <div class="col-sm-3" style="margin-top: 8px;font-size: 12px;"> 
                                    <div style="width: 100%;padding-top: 5px;" class="background1 druginsurancecardpanel1">
                                        <div class=""><span style="font-weight: bold; padding-top: 3px; padding-left: 7px;color: #2071b6">Insurance Card</span><img src="${pageContext.request.contextPath}/resources/images/edit.png" width="5%" style="margin-left:7px; cursor: pointer" onclick="window.patientInfoInsurance.ShowDialogBoxInsurance();"/>
                                            <div class="col-sm-12">
                                                <c:choose>
                                                    <c:when test="${not empty patientProfile.insuranceFrontCardPath && not empty patientProfile.insuranceBackCardPath}">
                                                        <input id="insuranceDetailId" type="hidden" value="${patientProfile.insuranceDetailId}"/>
                                                        <div class="col-sm-5">
                                                            <img src="${patientProfile.insuranceFrontCardPath}" onclick="viewLargeImg('${patientProfile.insuranceFrontCardPath}', 'Front Side')" class="frontsidecardimg">
                                                        </div> 
                                                        <div class="col-sm-7">
                                                            <img src="${patientProfile.insuranceBackCardPath}" onclick="viewLargeImg('${patientProfile.insuranceBackCardPath}', 'Back Side')" style="float: right; position: relative; width: 140px; height: 96px; margin-top: 5px; left: 8px;">
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="insuranceDetailId" type="hidden" value="0"/>
                                                        <div style="color: red;" align="center">No insurance card.</div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3 background1 druginsurancecardpanel1 " style="margin-top: 8px;font-size: 12px;">
                                    <div style="width: 100%;padding-top: 9px;margin-left: 12px;">
                                        <div style="background:rgb(138,202,20);float: left;height: 55px;width: 280px;" >

                                            <div style="float: left; font-weight: bold; color: black; font-size: 20px; padding-left: 10px; padding-top: 15px;">
                                                ${patientProfile.totalRewardPoints}
                                            </div>
                                            <div style="font-weight: bold; text-align: center; margin-left: 2px; padding-top: 8px; width: 188px; color: white;">
                                                Compilance<br> Reward<sup>TM</sup> Points
                                            </div>
                                            <div class="ribbon">
                                                <c:forEach var="row" items="${rewardLevelList}">
                                                    <c:if test="${patientProfile.totalRewardPoints>row.fromLevel && patientProfile.totalRewardPoints<=row.toLevel}">
                                                        ${row.type}
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="clear: both;"></div>
                                    <div class="col-sm-3" style="padding-left: 0px; width: 16%;color: #2071b6 ;margin-left: 12px; margin-top: 8px;">
                                        Height(Ft/
                                        <div class="input-group">
                                            <form:hidden id="profileHealthsId" path="patientProfileHealthsList[0].id"/>
                                            <form:input id="heightFeet" path="patientProfileHealthsList[0].heightFeet" cssClass="form-control" readonly="true" value="${not empty patientProfile.patientProfileHealthsList[0].heightFeet?patientProfile.patientProfileHealthsList[0].heightFeet:0}"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2" style="padding-left: 5px;color: #2071b6;margin-top: 8px;" >
                                        Inch)
                                        <div class="input-group">
                                            <form:input id="Inch" path="patientProfileHealthsList[0].heightInch" cssClass="form-control" readonly="true" value="${not empty patientProfile.patientProfileHealthsList[0].heightInch?patientProfile.patientProfileHealthsList[0].heightInch:0}"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3" style="padding-left:15px;color: #2071b6;margin-top: 8px;">
                                        Weight(Lbs)
                                        <div class="input-group">
                                            <form:input id="Weight" path="patientProfileHealthsList[0].weight" cssClass="form-control" readonly="true" value="${not empty patientProfile.patientProfileHealthsList[0].weight?patientProfile.patientProfileHealthsList[0].weight:0}"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3" style="padding-left:17px;color: #2071b6;margin-top: 8px;">
                                        BMI(%)
                                        <div class="input-group">
                                            <form:input id="BMI" path="patientProfileHealthsList[0].bmi" cssClass="form-control" readonly="true" value="${not empty patientProfile.patientProfileHealthsList[0].bmi?patientProfile.patientProfileHealthsList[0].bmi:'0.00'}"/>
                                        </div>
                                    </div>
                                    <div style="text-align: center; margin-top: 13px;padding-left: 5px;">
                                        <img src="${pageContext.request.contextPath}/resources/images/edit.png" width="5%" style="cursor: pointer" onclick="uploadRecord(${patientProfile.id}, 'get');"/>
                                    </div>
                                </div>
                                <div class="col-sm-6 " style="font-size: 12px;padding-left: 2px;">
                                    <div style="width: 100%;padding-top: 10px;overflow-y: auto" class="dependent">
                                        <c:choose>
                                            <c:when test="${fn:length(patientProfile.patientProfileMembersList) gt 0}">
                                                <c:set var="count" value="1"></c:set>
                                                <c:forEach var="row" items="${patientProfile.patientProfileMembersList}">
                                                    <div class="col-sm-12">
                                                        <div class="col-sm-3" style="padding-left:5px">
                                                            <label>Dependent ${count}</label>
                                                        </div>
                                                        <div class="col-sm-3" style="padding-left:5px;">
                                                            <label style="color:black">${fn:toUpperCase(row.firstName)} ${fn:toUpperCase(fn:substring(row.lastName, 0, 1))}&nbsp;(${row.gender eq 'M'?'MALE':'FEMALE'})</label>
                                                        </div>
                                                        <div class="col-sm-3" style="padding-left:5px;">
                                                            <fmt:formatDate var="memberdobyear" pattern="yyyy" value="${row.dob}"/>
                                                            <label style="color:black"><fmt:formatDate pattern="MM/dd/yyyy" value="${row.dob}"/> (${nowyear-memberdobyear} Years old)</label>
                                                        </div>
                                                        <div class="col-sm-3" style="padding-left:5px;">
                                                            <label style="color:black;">${not empty row.allergies?row.allergies:'&nbsp;'}</label>
                                                        </div>
                                                    </div>
                                                    <c:set var="count" value="${count+1}"></c:set>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-sm-12" style="color: red;" align="center">No data available.</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>

                                <div id="patientPharmacyNotes" style="padding-left: 4px;width: 300px;height: 73px;color: black;" class="col-sm-3 pharmacynotess">

                                    <div id="displayNotes" style="width: 300px;height: 44px; overflow: auto">
                                        <c:forEach var="row" items="${patientProfileNotesList}">
                                            ${row.notes}
                                            <br>
                                        </c:forEach>
                                    </div>

                                    <textarea id="pharmacyNotes" placeholder="Pharmacy Notes" style="width: 300px;height: 30px;"></textarea>

                                </div>


                                <div class="col-sm-3 sendmessage" style="margin-left: 21px;" >
                                    <div  style="padding-top: 5px;padding-left: 8px;">
                                        Send Patient Message
                                    </div>
                                    <div style="text-align:right; padding-bottom: 3px; margin-top: 15px;padding-right: 1px;"><img src="${pageContext.request.contextPath}/resources/images/SendMSG.png" width="7%" style="cursor: pointer" /></div>
                                </div>
                            </div>
                            <div style="clear: both;"></div>

                            <!--  tab start  -->
                            <div class="col-sm-12">
                                <ul id="myTab" class="nav nav-pills">
                                    <li class="mytabli" onclick="displayTab(1)"><a id="madicat" href="#" class="activetab">Rx HISTORY</a></li>
                                    <li class="mytabli" onclick="displayTab(2)"><a id="pointsB" href="#" class="inactivetab">UN FILLED / ON HOLD</a></li>
                                    <li class="mytabli" onclick="displayTab(3)"><a id="pa" href="#" class="inactivetab">PROGRAM ACTIVITIES</a></li>
                                </ul>
                            </div>

                            <!-- table start  -->

                            <div id="medicationsTable" class="col-sm-12">
                                <table class="display" id="example" class="display table" width="100%">
                                    <thead>
                                        <tr class="row grid-header" style="background-color: #e9ecf1;">
                                            <th style="font-size: 10px; color:#2071b6;padding-left: 10px;"><span class="tbl_f">DATE FILLED&nbsp;</span></th>
                                            <th style="font-size: 10px; color:#2071b6"> <span class="tbl_f">ACTIVE Rx</th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">RX.#</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f"> DRUG</span></th>
                                            <th style="font-size: 10px; color:#2071b6"> <span class="tbl_f">DISP QTY</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">REF REM</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f"> LAST ACTION</span></th>                                               
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">INS BILLED</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">INGRD COST</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">ORIG COPAY</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">HANDLING FEE</span></th>

                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">PT AMT COLLECTED</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">TOTAL SALE</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">PROFIT MARGIN</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">POINTS EARNED</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">NEXT REFILL</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">VIDEO ATTACH</span></th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="patientOrderRx" items="${patientProfile.orders}">
                                            <tr class="row grid-row odd">
                                                <td><span id="tbl_textalign"><fmt:formatDate pattern="MM/dd/yyyy" value="${patientOrderRx.createdOn}"/></span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">${patientOrderRx.id}</span></td>
                                                <td><span id="tbl_textalign">${patientOrderRx.drugName}</span></td>
                                                <td><span id="tbl_textalign">${patientOrderRx.qty}</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">${patientOrderRx.getOrderStatus().getName()}</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">$&nbsp;${patientOrderRx.drugPrice}</span></td>
                                                <td><span id="tbl_textalign">$&nbsp;${patientOrderRx.handLingFee}</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div id="pointsBalanceTable" class="col-sm-12">
                                <table class="display" id="pointsBalanceTb" class="display table" width="100%">
                                    <thead>
                                        <tr class="row grid-header" style="background-color: #e9ecf1;">
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">DATE&nbsp;</span></th>
                                            <th style="font-size: 10px; color:#2071b6"> <span class="tbl_f">MEDICATION</th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">ACTIVITY</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">PHARMACY ACTION</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">PT RESPONSE</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">EARNED COMPLIANCE REWARDS</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="patientOrderRx" items="${patientProfile.orders}">
                                            <tr class="row grid-row odd">
                                                <td><span id="tbl_textalign"><fmt:formatDate pattern="MM/dd/yyyy" value="${patientOrderRx.createdOn}"/></span></td>
                                                <td><span id="tbl_textalign">${patientOrderRx.drugName}</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div id="programActivitiesTable" class="col-sm-12">
                                <table class="display" id="programActivityTb" class="display table" width="100%">
                                    <thead>
                                        <tr class="row grid-header" style="background-color: #e9ecf1;">
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">DATE&nbsp;</span></th>
                                            <th style="font-size: 10px; color:#2071b6"> <span class="tbl_f">MEDICATION</th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">ACTIVITY</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">RESPONSE</span></th>
                                            <th style="font-size: 10px; color:#2071b6"><span class="tbl_f">POINTS</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="patientOrderRx" items="${patientProfile.orders}">
                                            <tr class="row grid-row odd">
                                                <td><span id="tbl_textalign"><fmt:formatDate pattern="MM/dd/yyyy" value="${patientOrderRx.createdOn}"/></span></td>
                                                <td><span id="tbl_textalign">${patientOrderRx.drugName}</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">N/A</span></td>
                                                <td><span id="tbl_textalign">+${patientOrderRx.redeemPoints}</span></td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="dialog" style="height: auto; overflow: auto; background-color: white;display: none;"></div>
            <div id="dialogDeliveryAddress" style="height: 300px; overflow: auto; background-color: white;display: none;padding-left: 0px;padding-top: 25px !important;"></div>
            <div id="dialogAllergies" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
            <div id="dialogInsurance" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
        </form:form>
    </div> <!-- /content -->
    <jsp:include page="./inc/footer.jsp" />
</div> <!-- /wrapper -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/ssasoftDeliveryAddress.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInfo.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInfoInsurance.js"></script>
<script type="text/javascript">
                                        $('#datetimepicker').datetimepicker({timepicker: false, format: 'm/d/Y',
                                            onChangeDateTime: function (dp, $input) {
                                                jQuery('#datetimepicker').datetimepicker('hide');
                                            }});
</script>
<script type="text/javascript">
    function viewLargeImg(url, title) {
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
                $(".ui-dialog-titlebar").css("font-size", "14px");
                $(".ui-dialog-titlebar").css("font-weight", "normal");
            },
            close: function ()
            {
                $(this).dialog('close');
                $(this).dialog('destroy');
            }
        });
        var html = '<img src="' + url + '" width="340" height="200">';
        $(dialog).html(html);
        $("#dialog").dialog("open");
    }
    function getPatientHealthRecord(patientProfileId, heightFeet, heightInch, weight, bmi) {
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
            title: "Patient Health",
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
        var html = "<div style='margin-left:40px;'>";
        html += "<div class='col-sm-2'>";
        html += "<label >Height(Ft/</label>";
        html += "<div class='input-group'>";
        html += "<input id='feet' type ='text' class='form-control' value='" + heightFeet + "' onkeypress='return IsDigit(event)'>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col-sm-2' style='margin-left:7px;'>";
        html += "<label>Inch)</label>";
        html += "<div class='input-group'>";
        html += "<input id='inch' type ='text' class='form-control' value='" + heightInch + "' onkeypress='return IsDigit(event)'>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col-sm-3' >";
        html += "<label>Weight(Lbs)</label>";
        html += "<div class='input-group'>";
        html += "<input id='weight' type ='text' class='form-control' value='" + weight + "' onkeypress='return IsDigit(event)'>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col-sm-3' style='margin-left:7px;'>";
        html += "<label >BMI(%)</label>";
        html += "<div class='input-group'>";
        html += "<input id='bmi' type ='text' class='form-control' value='" + bmi + "' onkeypress='return isFloatNumber(this,event)' maxlength='4'>";
        html += "</div>";
        html += "</div>";
        html += "</div>";
        html += "<div style=padding-top:5px;>";
        html += "<div style='float: left; padding-top:10px;margin-left:87px;'>";
        html += "<button  class = 'btndrop btn-margin' type='button' class = 'btn_Color' style='color:#FFFFFF; onclick='hideModel();' >Cancel</button></a>";
        html += "</div >";
        html += "<div style='float:right;margin-top:10px;margin-right:88px;'>";
        html += "<button  class = 'btndrop btn-margin' type='button' style='color:#FFFFFF;' onclick='savePatientHealth(" + patientProfileId + ")' class = 'btn_Color'> Save</button></a>";
        html += "</div>";
        html += "</div>";
        $(dialog).html(html);
        $(dialog).dialog("open");
    }
    function uploadRecord(patientProfileId, msg) {
        if (patientProfileId > 0) {
            $.ajax({
                url: "${pageContext.request.contextPath}/patient/getPatientProfileHealth/" + patientProfileId,
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        $("#profileHealthsId").val(data.id);
                        if (msg == "Success") {
                            setPatientHealthRecord(data);
                        } else {
                            getPatientHealthRecord(patientProfileId, data.heightFeet, data.heightInch, data.weight, data.bmi);
                        }
                    } else {
                        getPatientHealthRecord(patientProfileId, 0, 0, 0, '0.00');
                    }
                }
            });
        }
    }
    function savePatientHealth(patientProfileId) {
        var id;
        if ($("#profileHealthsId").val() != "") {
            id = $("#profileHealthsId").val();
        } else {
            id = null;
        }

        //var json = {"patientProfileId": patientProfileId,"height": $("#feet").val(), "inch": $("#inch").val(), "weigth": $("#weight").val(), "BMI": $("#bmi").val()};
        var json = {"patientHealthId": id, "patientProfileId": patientProfileId, "height": $("#feet").val(), "inch": $("#inch").val(), "weigth": $("#weight").val(), "bmi": $("#bmi").val()};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/savePatientProfileHealth",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    window.location.href = "/patient/detail/" + $("#patientId").val();
                }

            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }
    function setPatientHealthRecord(data) {
        if (data.heightFeet != null) {
            $("#heightFeet").val(data.heightFeet);
        } else {
            $("#heightFeet").val(0);
        }
        if (data.heightInch != null) {
            $("#Inch").val(data.heightInch);
        } else {
            $("#Inch").val(0);
        }
        if (data.weight != null) {
            $("#Weight").val(data.weight);
        } else {
            $("#Weight").val(0);
        }
        if (data.bmi != null) {
            $("#BMI").val(data.bmi);
        } else {
            $("#BMI").val('0.00');
        }
    }
    function showPatientRecordDetail(patientId, type) {
        var json = {"id": patientId};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/getPatientShippingAddress",
            type: "POST",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (type == "saved") {
                    $("#patientAddressId").val(data.id);
                    var shipAddress = data.address + " " + data.apartment + " " + data.city + " " + data.zip;
                    //$("#shippingAddress").text(shipAddress);
                } else {
                    showDialog(data, type, patientId);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }

    function showDialog(data, type, patientId) {
        var dialog = $("#dialog").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "30%",
            cache: false,
            title: type,
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
        var html, address = '', city = '', stateId = 0, zip = '', apartment = '';
        if (data != null) {
            if (data.id != null) {
                $("#patientAddressId").val(data.id);
            }
            if (data.address != null) {
                address = data.address;
            }
            if (data.city != null) {
                city = data.city;
            }
            if (data.zip != null) {
                zip = data.zip;
            }
            if (data.stateId != null) {
                stateId = data.stateId;
            }
            if (data.apartment != null) {
                apartment = data.apartment;
            }
        }
        html = "<div class='col-sm-2'>";
        html += "<label>Address:</label>";
        html += "<input id='address' type='text' class='form-control' value='" + address + "'/>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<label>Apartment:</label>";
        html += "<input id='apartment' type='text' class='form-control' value='" + apartment + "'/>";
        html += "</div>";
        html += "<div class='col-sm-2'>";
        html += "<label>City:</label>";
        html += "<input id='city' type='text' class='form-control' value='" + city + "'/>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<label>State:</label>";
        html += "<select id='state' class='form-control selectpicker show-tick' style='padding-top:3px;'>";
        html += "<option value='0'>Select One</option>";
        html += "</select>";
        html += "</div>";
        html += "<div class='col-sm-2'>";
        html += "<label>Zip:</label>";
        html += "<input id='zip' type='text' class='form-control' value='" + zip + "'/>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='hideModel();' style='color:#2071b6; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='updatePatientRecord(" + patientId + ");' style='color:#2071b6; padding-top: 1px;'>Save</button>";
        html += "</div>";
        getStatesWs(stateId);
        $(dialog).html(html);
        $(dialog).dialog("open");
    }
    function updatePatientRecord(patientId) {
        var addressId = null;
        if ($("#patientAddressId").val() != "") {
            addressId = $("#patientAddressId").val();
        }
        var json = {"id": addressId, "address": $("#address").val(), "city": $("#city").val(), "apartment": $("#apartment").val(), "stateId": $("#state").val(), "zip": $("#zip").val(), "profileId": patientId};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/savePatientShippingAddress",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            success: function (data) {
                if (data == true) {
                    showPatientRecordDetail(patientId, "saved");
                    hideModel();
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }
    function getStatesWs(stateId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/getStatesWs",
            type: "POST",
            dataType: 'json',
            success: function (data) {
                $('#state')
                        .find('option')
                        .remove()
                        .end()
                        .append('<option value="0">Select One</option>')
                        .val('Select One');
                $.each(data.data, function (index, element) {
                    $('#state').append($('<option>', {value: element.id, text: element.name}));
                });

                if (stateId > 0) {
                    $('#state').val(stateId).attr("selected", "selected");
                    $("#state").trigger("change");
                }
//                $('#state').selectpicker('refresh');
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }
    $(document).ready(function () {
        window.patientInfo.ProgramActivityTable();
        window.patientInfo.PointsBalanceTable();
        $("#pointsBalanceTable").hide();
        $("#programActivitiesTable").hide();
        window.patientInfo.populateAppUrl('${pageContext.request.contextPath}');
        window.patientInfoInsurance.populateAppUrl('${pageContext.request.contextPath}');
        window.ssaSoftDeliveryAddress.populateAppUrl('${pageContext.request.contextPath}');
    });
    function displayTab(id) {
        if (id == 1) {
            $("#madicat").attr("class", "activetab");
            $("#pointsB").attr("class", "inactivetab");
            $("#pa").attr("class", "inactivetab");
            $("#medicationsTable").show();
            $("#pointsBalanceTable").hide();
            $("#programActivitiesTable").hide();
        } else if (id == 2) {
            $("#pointsB").attr("class", "activetab");
            $("#madicat").attr("class", "inactivetab");
            $("#pa").attr("class", "inactivetab");

            $("#pointsBalanceTable").show();
            $("#programActivitiesTable").hide();
            $("#medicationsTable").hide();

        } else if (id == 3) {
            $("#pa").attr("class", "activetab");
            $("#pointsB").attr("class", "inactivetab");
            $("#madicat").attr("class", "inactivetab");

            $("#programActivitiesTable").show();
            $("#pointsBalanceTable").hide();
            $("#medicationsTable").hide();
        }
    }



    $("#pharmacyNotes").keypress(function (e) {

        if (e.keyCode == 13 && !e.shiftKey)
        {
//            console.log("Taking the string away");
            var patientId = "${patientProfile.id}";
            var textval = $("#pharmacyNotes").val();
            var dispval = document.getElementById("displayNotes").innerHTML;
            document.getElementById("displayNotes").innerHTML = dispval + '<br>' + textval;

            if (textval == "") {
                return;
            }
            var json = {"ptProfileId": patientId, "notes": textval};

            e.preventDefault();
            //now call the code to submit your form
            $.ajax({
                url: "${pageContext.request.contextPath}/patient/savePatientNotes",
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(json),
                success: function (data) {
                    var response = eval(data);
                    $("#pharmacyNotes").val("");
                },
                error: function (e) {
                    alert('Error while request...' + e);
                }
            });
        }
    });

    function previousUser() {
        var newUser = ${patientProfile.id} - 1;
        var json = {"id": newUser};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/checkPatientIdExists",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data) {
                    window.location = window.location.href = "/patient/detail/" + newUser;
                }
                var response = eval(data);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }

    function nextUser() {
        var newUser = ${patientProfile.id} + 1;
        var json = {"id": newUser};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/checkPatientIdExists",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data) {
                    window.location = window.location.href = "/patient/detail/" + newUser;
                }
                var response = eval(data);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }

</script>
</body>
</html>
