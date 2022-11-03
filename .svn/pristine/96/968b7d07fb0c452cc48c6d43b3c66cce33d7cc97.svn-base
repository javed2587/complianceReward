<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/patientDetail.css" />
    <jsp:include page="./inc/header.jsp" />
    <div id="wrapper">
        <div id="content" class="clearfix" style="z-index:0">
            <jsp:include page="./inc/newMenu.jsp" />
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


                <div class="wrapper p_detail_wrp">
                    <div class="userBanner clearfix">
                        <div class="clearfix">
                            <ul>
                                <li>${fn:toUpperCase(patientProfile.firstName)} ${fn:substring(patientProfile.lastName, 0, 1)}. (${patientProfile.gender})</li>
                                <li>
                                    <fmt:formatDate var="dobyear" pattern="yyyy" value="${patientProfile.birthDate}"/> 
                                    <fmt:formatDate pattern="MM/dd/yyyy" value="${patientProfile.birthDate}"/> (${nowyear-dobyear} Years)
                                </li>
                                <li>Member Since <fmt:formatDate pattern="MM/yyyy" value="${patientProfile.createdDate}"/></li>
                                    <c:choose>
                                        <c:when test="${fn:length(patientProfile.patientProfileMembersList) > 0}">
                                        <li><a href="#" onclick="displayModal(${patientProfile.id}, 'dependentsModal', 'viewDependentWs')"> ${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li>${fn:length(patientProfile.patientProfileMembersList)} DEPENDENTS</li>
                                        </c:otherwise>
                                    </c:choose>
                                <li>
                                    <c:choose>
                                        <c:when test="${totalRxProgram > 0}">
                                            <a href="#" onclick="displayModal(${patientProfile.id}, 'programRxModal', 'viewProcessedOrdersByPatientIdWs')">${totalRxProgram} Program Rx&acute;s</a>
                                        </c:when>
                                        <c:otherwise>
                                            0 Program Rx&acute;s
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </ul>
                            <p><fmt:formatDate pattern="MMMM dd, yyyy hh:mm" value="<%=new java.util.Date()%>"/> CST</p>
                        </div>
                    </div>
                    <div class="customTableRow patientTableRow clearfix">
                        <div class="col-md-3">
                            <div class="heightList clearfix">
                                <div class="primume_box">
                                    <p><span>${patientProfile.totalRewardPoints}</span>Compliance
                                        <br />Reward&trade; Points</p>
                                    <div class="primiume_icon">
                                        <c:forEach var="row" items="${rewardLevelList}">
                                            <c:if test="${patientProfile.totalRewardPoints>row.fromLevel && patientProfile.totalRewardPoints<=row.toLevel}">
                                                ${row.type}
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <ul>
                                    <li><span>Height</span></li>
                                    <li><span>Weight</span></li>
                                    <li><span>BMI</span><a href="#" data-toggle="modal" data-target="#healthModal"><i class="fa fa-pencil"></i></a></li>
                                </ul>
                                <ul>
                                    <li>${not empty patientProfile.patientProfileHealthsList[0].heightFeet?patientProfile.patientProfileHealthsList[0].heightFeet:0} ft</li>
                                    <li>${not empty patientProfile.patientProfileHealthsList[0].weight?patientProfile.patientProfileHealthsList[0].weight:0} lbs</li>
                                    <li>${not empty patientProfile.patientProfileHealthsList[0].bmi?patientProfile.patientProfileHealthsList[0].bmi:'0.00'}%</li>
                                </ul>

                            </div>
                            <div class="contactContainer">
                                <p><span class="blueText">Phone #.</span> ${fn:substring(patientProfile.mobileNumber, 0, 3)}-${fn:substring(patientProfile.mobileNumber, 3, 6)}-${fn:substring(patientProfile.mobileNumber, 6, 10)}</p>
                                <p><span class="blueText">Backup Phone #.</span> ${fn:substring(patientProfile.mobileNumber, 0, 3)}-${fn:substring(patientProfile.mobileNumber, 3, 6)}-${fn:substring(patientProfile.mobileNumber, 6, 10)}</p>
                                <p><span class="blueText">Email</span> ${not empty patientProfile.emailAddress?patientProfile.emailAddress:'&nbsp;'}</p>
                                <p><span class="blueText">Allergies <a href="#" data-toggle="modal" data-target="#allergyModal" ><i class="fa fa-pencil"></i></a> </span>
                                    <br />${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}</p>
                            </div>
                            <div class="shippingContainer">
                                <ol type="1">
                                    <c:forEach var="shippingAddressVar" items="${patientProfile.patientDeliveryAddresses}">
                                        <li>
                                            <span class="blueText">Shipping Address</span>
                                            <a href="#" data-toggle="modal"  onclick="window.ssaSoftDeliveryAddress.LookupDeliveryAddressById(${shippingAddressVar.id})"><i class="fa fa-pencil"></i></a>
                                            <br />
                                            <strong>${shippingAddressVar.description}</strong>
                                            <br />
                                            <span>${shippingAddressVar.address}, ${shippingAddressVar.apartment}, ${shippingAddressVar.city},${shippingAddressVar.state.name} ,${shippingAddressVar.zip}</span>
                                        </li>
                                    </c:forEach>

                                </ol>
                            </div>
                            <div class="insuranceContainer" style="${fn:length(listOfPtInsurances) > 0?'height:137px;overflow:auto;':''}">
                                <c:choose>
                                    <c:when test="${fn:length(listOfPtInsurances) > 0}">
                                        <c:forEach var="insuranceCard" items="${listOfPtInsurances}" varStatus="loop">
                                            <span class="blueText">${loop.index+1}. Insurance Card</span>
                                            <i class="fa fa-pencil" onclick="window.patientInfoInsurance.getInsurnaceDetails();"></i>
                                            <div>
                                                <input id="insuranceDetailId" type="hidden" value="${insuranceCard.id}"/>
<!--                                                <img id="frontImage" src="#" alt="insurance card front" data-toggle="modal" data-target="#cardsModal" height="100" width="128"/>-->
                                                <img id="frontImage" src="${not empty insuranceCard.insuranceFrontCardPath?insuranceCard.insuranceFrontCardPath:'#'}" alt="insurance card front" height="100" width="128"/>
                                                <img id="backImg" src="${not empty insuranceCard.insuranceBackCardPath?insuranceCard.insuranceBackCardPath:'#'}" alt="insurance card back" width="128" height="100"/>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="height: 50px;">
                                            <span class="blueText">Insurance Card</span>
                                            <input id="insuranceDetailId" type="hidden" value="0"/>
                                            <span style="color: red;">No Insurance Card Available.</span>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <form action="" class="messageForm">
                                <textarea id="messageText" name="message" class="messageBox" placeholder="Send Message to Patient"></textarea>
                                <i class="fa fa-paper-plane greenText" onclick="window.patientInfo.SendPatientNotification(${patientProfile.id},${patientProfile.mobileNumber});"></i>
                            </form>
                            <a href="#" class="greenText messageHistory pull-left" onclick="displayModal(${patientProfile.id}, 'historyModal', 'viewPatientHistoryMessage')">View Message History</a>
                            <div id="errorSendMessage" class="pull-right"></div>

                        </div>
                        <div class="col-md-9 rightPatientDiv">

                            <div class="tableContainer">

                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" onclick="displayTab(1)">Active Medications</a></li>
                                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" onclick="displayTab(2)">Points Balance</a></li>
                                    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab" onclick="displayTab(3)">Program Activities</a></li>
                                </ul>
                                <div class="rightTableTop searchRecordsContainer">
                                    <form action="">
                                        <select id="dtPagelen" name="numberOfRecords">
                                            <option value="10" selected="selected">10</option>
                                            <option value="25">25</option>
                                            <option value="50">50</option>
                                            <option value="100">100</option>
                                        </select>
                                        <div class="searchBoxField">
                                            <input id="tabInfo" type="hidden" value="1"/>
                                            <input id="search" type="text" style="color: black;" placeholder="Search">
                                            <button type="submit">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="home">
                                        <div class="">
                                            <table id="activeMedicationTb" class="display table">
                                                <thead>
                                                    <tr class="row grid-header">
                                                        <th  align="left" width="10%">Date</th>
                                                        <th  width="30%">Medication</th>
                                                        <th  width="2%">QTY</th>
                                                        <th  width="6%">Ref Rem</th>
                                                        <th  width="6%">Last Action</th>
                                                        <th  width="6%">Ins Code</th>
                                                        <th  width="6%">Orig Copy</th>
                                                        <th  width="6%">Handling Fee</th>
                                                        <th  width="6%">Redeemed</th>
                                                        <th  width="6%">Total State</th>
                                                        <th  width="6%">Amt Collected</th>
                                                        <th  width="10%">Next Refill</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="patientOrderRx" items="${patientProfile.orders}">
                                                        <tr class="row grid-row">
                                                            <td width="10%"><fmt:formatDate pattern="MM/dd/yyyy" value="${patientOrderRx.createdOn}"/></td>
                                                            <td width="30%">${patientOrderRx.drugName} ${patientOrderRx.strength}
                                                                <c:if test="${not empty patientOrderRx.drugType}">
                                                                    (${patientOrderRx.drugType})
                                                                </c:if>
                                                            </td>
                                                            <td width="2%" align="center">${patientOrderRx.qty}</td>
                                                            <td width="6%" align="center">1</td>
                                                            <td width="6%">${patientOrderRx.orderStatus.name}</td>
                                                            <td width="6%">COM</span></td>
                                                            <td width="6%">N/A</td>
                                                            <td width="6%">${patientOrderRx.handLingFee}</td>
                                                            <td width="6%">${patientOrderRx.redeemPoints}</td>
                                                            <td width="6%">N/A</td>
                                                            <td width="6%">${patientOrderRx.payment}</td>
                                                            <td width="10%">N/A</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!--                                        <nav aria-label="Page navigation">
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
                                                                                </nav>-->

                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="profile">

                                        <div class="">
                                            <table id="pointsBalanceTb" class="display table">
                                                <thead>
                                                    <tr class="row grid-header">

                                                        <th  width="40%">Description</th>
                                                        <th  width="15%" >Redeemed Points</th>
                                                        <th  width="15%" >Awarded Points</th>
                                                        <th  width="30%" >Date</th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach var="reward" items="${reward}" varStatus="counter">

                                                        <tr class="row grid-row">

                                                            <td width="40%">${reward.description}</td>
                                                            <td width="15%" >${reward.redeemedPoints}</td>  
                                                            <td width="15%" >${reward.awardedPoints}</td>  
                                                            <td width="30%" >${reward.createdDate}</td>  
                                                        </tr>
                                                        <!--
                                                        
                                                                 <tr class="row grid-row">
                                                                    <td width="50%" >Points Balance</td>
                                                                    <td  >&nbsp;</td> 
                                                                    <td  >${balance}</td>    
                                                                </tr>
                                                        -->
                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                        <!--                                        <nav aria-label="Page navigation">
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
                                                                                </nav>-->
                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="messages">

                                        <div >
                                            <table id="programActivityTb" class="display table">
                                                <thead>
                                                    <tr class="row grid-header">
                                                        <th  align="left" width="10%">Date</th>
                                                        <th  width="30%">Medication</th>
                                                        <th  width="2%">QTY</th>
                                                        <th  width="6%">Ref Rem</th>
                                                        <th  width="6%">Last Action</th>
                                                        <th  width="6%">Ins Code</th>
                                                        <th  width="6%">Orig Copy</th>
                                                        <th  width="6%">Handling Fee</th>
                                                        <th  width="6%">Redeemed</th>
                                                        <th  width="6%">Total State</th>
                                                        <th  width="6%">Amt Collected</th>
                                                        <th  width="10%">Next Refill</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="patientOrderRx" items="${patientProfile.orders}">
                                                        <tr class="row grid-row">
                                                            <td width="10%"><fmt:formatDate pattern="MM/dd/yyyy" value="${patientOrderRx.createdOn}"/></td>
                                                            <td width="30%">${patientOrderRx.drugName} ${patientOrderRx.strength} 
                                                                <c:if test="${not empty patientOrderRx.drugType}">
                                                                    (${patientOrderRx.drugType})
                                                                </c:if>

                                                            </td>
                                                            <td width="2%" align="center">${patientOrderRx.qty}</td>
                                                            <td width="6%" align="center">1</td>
                                                            <td width="6%">${patientOrderRx.orderStatus.name}</td>
                                                            <td width="6%">COM</span></td>
                                                            <td width="6%">N/A</td>
                                                            <td width="6%">${patientOrderRx.handLingFee}</td>
                                                            <td width="6%">${patientOrderRx.redeemPoints}</td>
                                                            <td width="6%">N/A</td>
                                                            <td width="6%">${patientOrderRx.payment}</td>
                                                            <td width="10%">N/A</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!--                                        <nav aria-label="Page navigation">
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
                                                                                </nav>-->
                                    </div>
                                </div>

                            </div>
                            <div class="row textBox">
                                <div class="col-sm-12 messageForm">

                                    <textarea id="pharmacyNotes" name="pharmacyNote" class="messageBox" placeholder="Pharmacy Notes"></textarea>
                                    <i class="fa fa-paper-plane greenText" onclick="savePharmacyNotes(${patientProfile.id})" title="Click Here For Saving Pharmacy Notes"></i>

                                    <a href="${pageContext.request.contextPath}/patient/detail" class="greenText messageHistory">Back</a>
                                    <div id="pharmacyNotesReq" class="errorMessage pull-right"></div>
                                </div>

                            </div>

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

<!-- Modal Insurance Cards-->

<!--/ Modal Insurance Cards -->
<!-- Modal Dependents -->
<jsp:include page="portal/modals.jsp" />
<!--/ Modal Dependents -->

<!-- Modal History -->

<!--/ Modal History -->

<!-- Modal Health Details -->
<div id="healthModal" class="listModal healthModal formModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Health Details</h4>
            </div>
            <div class="modal-body">
                <div class="healthForm">
                    <input id="profileHealthsId" value="${patientProfile.patientProfileHealthsList[0].id}" type="hidden"/>
                    <div class="form-group">
                        <label for="heightFeet">Height:</label>
                        <input id="heightFeet" class="form-control" type="text" name="heightFeet" placeholder="Feet" value="${not empty patientProfile.patientProfileHealthsList[0].heightFeet?patientProfile.patientProfileHealthsList[0].heightFeet:0}" onkeypress="return IsDigit(event)"/>
                    </div>
                    <div class="form-group">
                        <label for="heightInches"></label>
                        <input id="Inch" type="text" class="form-control" name="heightInches" placeholder="Inch" value="${not empty patientProfile.patientProfileHealthsList[0].heightInch?patientProfile.patientProfileHealthsList[0].heightInch:0}" onkeypress="return IsDigit(event)"/>
                    </div>
                    <div class="form-group">
                        <label for="weight">Weight (lbs):</label>
                        <input id="Weight" class="form-control" type="text" name="weight" placeholder="lbs" value="${not empty patientProfile.patientProfileHealthsList[0].weight?patientProfile.patientProfileHealthsList[0].weight:0}" onkeypress="return IsDigit(event)"/>
                    </div>
                    <div class="form-group">
                        <label for="bmi">BMI (%):</label>
                        <input id="BMI" class="form-control" type="text" name="bmi" placeholder="%" value="${not empty patientProfile.patientProfileHealthsList[0].bmi?patientProfile.patientProfileHealthsList[0].bmi:'0.00'}" onkeypress="return isFloatNumber(this, event)"/>
                    </div>
                    <input class="blueSubmitButton" type="submit" value="Save" onclick="savePatientHealth('${patientProfile.id}')"/>
                </div>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Health Details -->

<!-- Modal Allergy -->
<div id="allergyModal"  tabindex='-1' class="listModal allergyModal formModal modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Allergies</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="allergies">Allergies:</label>
                    <input id="allergyId" class="form-control" type="text" name="allergies" placeholder="Comma Seperated (Penicillin, Peanuts, Mango)" value="${patientProfile.allergies}"/>
                </div>
                <input class="blueSubmitButton" type="submit" value="Save" onclick="window.patientInfo.updateAllergies();"/>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Allergy -->

<!-- Modal Shipping Details -->
<div id="shippingModal" class="listModal shippingModal formModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Shipping Address</h4>
            </div>
            <div class="modal-body">
                <div class="shippingForm">
                    <input id='deliveryAddressId' type='hidden'/>
                    <div class="form-group">
                        <label for="addressOne">Address Line 1:</label>
                        <input id="addressLine1" class="form-control" type="text" name="addressOne" />
                    </div>
                    <div class="form-group">
                        <label for="addresstwo">Address Line 2:</label>
                        <input type="text" class="form-control" name="addressTwo" />
                    </div>
                    <div class="form-group">
                        <label for="apt">APT:</label>
                        <input id="apartment" class="form-control" type="text" name="apt" />
                    </div>
                    <div class="form-group">
                        <label for="city">City:</label>
                        <input id="city" class="form-control" type="text" name="city" />
                    </div>
                    <div class="form-group">
                        <label for="state">State:</label>
                        <select name="state" id="state" class="form-control">
                            <option value='0'>Select One</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="zip">Zip:</label>
                        <input id="zip" class="form-control" type="text" name="zip" />
                    </div>
                    <div class="form-group">
                        <label for="addressDescription">Address Description:</label>
                        <input id="addressDescription" class="form-control" type="text" name="addressDescription" />
                    </div>
                    <div class="form-group">
                        <label for="addressType">Address Type:</label>
                        <div>
                            <input id="rdHome" type="radio" name="addressType" /> <span>Home</span>

                            <input id="rdWork" type="radio" name="addressType" /> <span>Work</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isDefault"></label>
                        <input id="isDefault" type="checkbox" name="isDefault" /> <span>Is Default</span>
                    </div>
                    <input class="blueSubmitButton" type="submit" value="Save" onclick="window.ssaSoftDeliveryAddress.updateDeliveryAddress();"/>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/ Modal Shipping Details -->

<!-- Modal Insurance edit -->
<div id="insEditModal" class="listModal shippingModal formModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Insurance Details</h4>
            </div>
            <div class="modal-body">
                <div class="insEditForm">
                    <input id='patInsId' type='hidden'>
                    <div class="clearfix">
                        <div class="form-group">
                            <label for="memId">Member ID:</label>
                            <input id="memberIDId" class="form-control" type="text" name="memId" />
                        </div>
                        <div class="form-group">
                            <label for="groupNum">Group Number:</label>
                            <input id="groupNumberId" type="text" class="form-control" name="groupNum" />
                        </div>
                        <div class="form-group">
                            <label for="planId">Plan ID: </label>
                            <input id="planIdId" class="form-control" type="text" name="planId" />
                        </div>
                        <div class="form-group">
                            <label for="insProv">Insurance Provider:</label>
                            <input id="insuranceProviderId" class="form-control" type="text" name="insProv" />
                        </div>
                        <div class="form-group">
                            <label for="provAddr">Provider Address:</label>
                            <input id="providerAddressId" class="form-control" type="text" name="provAddr" />
                        </div>
                        <div class="form-group">
                            <label for="providerPhone">Provider Phone:</label>
                            <input id="providerPhoneId" class="form-control" type="text" name="providerPhone" />
                        </div>
                        <div class="form-group">
                            <label for="expiryDate">Expiry Date:</label>
                            <input id="datetimepicker" class="form-control" type="date" name="expiryDate" />
                        </div>
                    </div>
                    <input class="blueSubmitButton" type="submit" value="Save" onclick="window.patientInfoInsurance.saveUpdateInsurnaceDetails();"/>
                </div>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Insurance edit -->
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
        var json = {"patientHealthId": id, "patientProfileId": patientProfileId, "height": $("#heightFeet").val(), "inch": $("#Inch").val(), "weigth": $("#Weight").val(), "bmi": $("#BMI").val()};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/savePatientProfileHealth",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    window.location.href = "${pageContext.request.contextPath}/patient/detail/" + $("#patientId").val();
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
        window.patientInfo.ActivityMedicationTable();
        window.patientInfo.ProgramActivityTable();
        window.patientInfo.PointsBalanceTable();
        
        $("#activeMedicationTb_filter").hide();
        $("#pointsBalanceTb_filter").hide();
        $("#programActivityTb_filter").hide();
        
        $("#activeMedicationTb_length").hide();
        $("#pointsBalanceTb_length").hide();
        $("#programActivityTb_length").hide();
        
        window.patientInfo.populateAppUrl('${pageContext.request.contextPath}');
        window.patientInfoInsurance.populateAppUrl('${pageContext.request.contextPath}');
        window.ssaSoftDeliveryAddress.populateAppUrl('${pageContext.request.contextPath}');

        $("#pointsBalanceTable").hide();
        $("#programActivitiesTable").hide();
        $("#search").keyup(function () {
            if (this.value.trim().length == 0) {
                $("input[type=text]").val("");
                return false;
            }
            var v = "";
            if ($("#tabInfo").val() == 1) {
                v = $('#activeMedicationTb').dataTable().fnFilter(this.value);
            } else if ($("#tabInfo").val() == 2) {
                v = $('#pointsBalanceTb').dataTable().fnFilter(this.value);
            } else if ($("#tabInfo").val() == 3) {
                v = $('#programActivityTb').dataTable().fnFilter(this.value);
            }
            if (typeof v === 'undefined' && this.value !== "") {
                //$(".dataTables_empty").attr("colspan", 13);
            }
        });

        $('#dtPagelen').on('change', function () {
            pageLen(this.value);
        });

    });
    function displayTab(id) {
        $("input[type=text]").val("");
        $('#dtPagelen').val(10).trigger('change');
        if (id == 1) {
            $("#tabInfo").val(1);
//            $("#madicat").attr("class", "activetab");
//            $("#pointsB").attr("class", "inactivetab");
//            $("#pa").attr("class", "inactivetab");
//            $("#medicationsTable").show();
//            $("#pointsBalanceTable").hide();
//            $("#programActivitiesTable").hide();
        } else if (id == 2) {
            $("#tabInfo").val(2);
//            $("#pointsB").attr("class", "activetab");
//            $("#madicat").attr("class", "inactivetab");
//            $("#pa").attr("class", "inactivetab");
//
//            $("#pointsBalanceTable").show();
//            $("#programActivitiesTable").hide();
//            $("#medicationsTable").hide();

        } else if (id == 3) {
            $("#tabInfo").val(3);
//            $("#pa").attr("class", "activetab");
//            $("#pointsB").attr("class", "inactivetab");
//            $("#madicat").attr("class", "inactivetab");
//
//            $("#programActivitiesTable").show();
//            $("#pointsBalanceTable").hide();
//            $("#medicationsTable").hide();
        }
    }
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
                    window.location = window.location.href = "${pageContext.request.contextPath}/patient/detail/" + newUser;
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
                    window.location = window.location.href = "${pageContext.request.contextPath}/patient/detail/" + newUser;
                }
                var response = eval(data);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }
    function savePharmacyNotes(patientId) {
        var textval = $("#pharmacyNotes").val();
        if (textval == "") {
            $("#pharmacyNotesReq").text("Required");
            $("#pharmacyNotesReq").attr("style", "display:block;");
            return false;
        }
        var json = {"ptProfileId": patientId, "notes": textval};

        //e.preventDefault();
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
    function pageLen(v) {
        if ($("#tabInfo").val() == 1) {
            $('select[name="activeMedicationTb_length"]').val(v).trigger('change');
        } else if ($("#tabInfo").val() == 2) {
            //$('#pointsBalanceTb').dataTable().page.len(v).draw();
            $('select[name="pointsBalanceTb_length"]').val(v).trigger('change');
        } else if ($("#tabInfo").val() == 3) {
            //$('#programActivityTb').dataTable().page.len(v).draw();
            $('select[name="programActivityTb_length"]').val(v).trigger('change');
        }
    }
</script>
</body>
</html>
