<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="heightList clearfix">
    <div class="primume_box">
        <p><span>${rewardPoints.availablePoints}</span>Compliance<br />Reward&trade; Points</p>
        <div class="primiume_icon">
            <c:forEach var="row" items="${rewardLevelList}">
                <c:if test="${rewardPoints.availablePoints>row.fromLevel && rewardPoints.availablePoints<=row.toLevel}">

                    <div style="${row.type eq 'Basic'?'text-align: center;padding-right:7px !important;font-size:12px':''}">
                        ${row.type}
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>

    <ul>
        <li><span>Height</span></li>
        <li><span>Weight</span></li>
        <li><span>BMI</span></li>
    </ul>
    <ul>
        <li>${not empty patientProfile.patientProfileHealthsList[0].heightFeet?patientProfile.patientProfileHealthsList[0].heightFeet:0.0} ft</li>
        <li>${not empty patientProfile.patientProfileHealthsList[0].weight?patientProfile.patientProfileHealthsList[0].weight:0.0} lbs</li>
        <li>${not empty patientProfile.patientProfileHealthsList[0].bmi?patientProfile.patientProfileHealthsList[0].bmi:0}%</li>
    </ul>

</div>
<div class="contactContainer">
    <p><span class="blueText">Phone #.</span> ${fn:substring(patientProfile.mobileNumber, 0, 3)}-${fn:substring(patientProfile.mobileNumber, 3, 6)}-${fn:substring(patientProfile.mobileNumber, 6, 10)}</p>
    <p><span class="blueText">Backup Phone #.</span> ${fn:substring(patientProfile.mobileNumber, 0, 3)}-${fn:substring(patientProfile.mobileNumber, 3, 6)}-${fn:substring(patientProfile.mobileNumber, 6, 10)}</p>
    <p><span class="blueText">Email</span> ${not empty patientProfile.emailAddress?patientProfile.emailAddress:'&nbsp;'}</p>
    <p><span class="blueText">Allergies </span><br />  ${not empty patientProfile.allergies?patientProfile.allergies:'No data available.'}</p>
</div>
<div class="shippingContainer">
    <ol type="1">
        <c:forEach var="shippingAddressVar" items="${patientProfile.patientDeliveryAddresses}">
            <li>
                <span class="blueText">Shipping Address </span><span class="greenText" style="float: right;">&nbsp;${shippingAddressVar.defaultAddress eq 'Yes'?'Default':''}</span>
                <br />
                <strong>${shippingAddressVar.description}</strong>
                <br />
                <span>${shippingAddressVar.address}, ${shippingAddressVar.apartment}, ${shippingAddressVar.city},${shippingAddressVar.state.name} ,${shippingAddressVar.zip}</span>
            </li>
        </c:forEach>
    </ol>
</div>
<div class="insuranceContainer">
    <span class="blueText">Insurance Card</span>
    <c:choose>
        <c:when test="${not empty patientInsuranceDetails}">
            <span><a href="#" class="greenText" data-toggle="modal" data-target="#insuranceDetailModal">View Detail</a></span>
        </c:when>
        <c:otherwise>
            <span>View Detail</span>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${not empty patientProfile.insuranceFrontCardPath && not empty patientProfile.insuranceBackCardPath}">
            <div>
                <img src="${patientProfile.insuranceFrontCardPath}" alt="insurance card front" data-toggle="modal" data-target="#cardsModal" class="frontsidecardimg"/>
                <img src="${patientProfile.insuranceBackCardPath}" alt="insurance card back" data-toggle="modal" data-target="#cardsModal" class="frontsidecardimg"/>
            </div>
        </c:when>
        <c:otherwise>
            <div style="height: 50px;">
                <span style="color: red;">No Insurance Card Available.</span>
            </div>
        </c:otherwise>
    </c:choose>
</div>
