<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<div class="patient_bar clearfix">
    <ul>
        <c:set var="isAdmin" value="false"></c:set>
        <c:if test="${sessionBeanPortal.isAdmin}">
            <c:set var="isAdmin" value="true"></c:set>
        </c:if>

        <c:if test="${(sessionBeanPortal.pmap[(77).intValue()].view eq true || sessionBeanPortal.pmap[(77).intValue()].manage eq true)}">
            <li class="dropdown ${isAdmin eq false?'disabledEvents':''}" style="text-decoration: underline;">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">Email & Phone  <i class="fa fa-caret-down" aria-hidden="true"></i></a>
                <ul class="dropdown-menu">
                    <li><span>Patient Name</span> ${fn:toUpperCase(patientProfile.firstName)} ${fn:toUpperCase(patientProfile.lastName)}</li>
                    <li> <span>MOBILE</span> 
                        ${fn:substring(patientProfile.mobileNumber, 0, 3)}.
                        ${fn:substring(patientProfile.mobileNumber, 3, 6)}.
                        ${fn:substring(patientProfile.mobileNumber, 6, 10)} <small>(BACK UP PHONE)</small> n/a </li>
                    <li> <span>Email</span> ${not empty patientProfile.emailAddress?patientProfile.emailAddress:'&nbsp;'} </li>
                </ul>
            </li>
        </c:if>
        <c:if test="${(sessionBeanPortal.pmap[(78).intValue()].view eq true || sessionBeanPortal.pmap[(78).intValue()].manage eq true)}">    
            <li class="dropdown ${isAdmin eq false?'disabledEvents':''}" style="text-decoration: underline;">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">ADDRESSES ON FILE <span>(${fn:length(patientProfile.patientDeliveryAddresses)})</span>  <i class="fa fa-caret-down" aria-hidden="true"></i></a>
                <div class="dropdown-menu address_dropdown">
                    <ul class="address_list">
                        <c:forEach var="shippingAddressVar" items="${patientProfile.patientDeliveryAddresses}" varStatus="theCount">
                            <li class="default"> <i>${theCount.count}</i>
                                <h3><span>${shippingAddressVar.description}</span>${shippingAddressVar.address}, ${shippingAddressVar.apartment}, ${shippingAddressVar.state.name} <br />
                                    ${shippingAddressVar.city},${shippingAddressVar.zip} </h3>
                                <!--<span>SPECIAL INSTRUCTIONS</span>-->
                                <input type="text" value="" disabled="true" name="" class="form-control" />
                            </li>

                        </c:forEach>
                    </ul>
                    <c:if test="${(sessionBeanPortal.pmap[(94).intValue()].view eq true || sessionBeanPortal.pmap[(94).intValue()].manage eq true)}">    
                        <div class="payment_cardinfo">
                            <h3>PAYMENT CARD ON FILE <span>${fn:length(paymentInfoList)}</span></h3>
                            <ul>
                                <c:forEach var="payment" items="${paymentInfoList}" varStatus="theCount">
                                    <li> <i>${theCount.count}</i>
                                        <h4>${payment.cardNumber}</h4>
                                        <div class="col-sm-5 paymentino_col">
                                            <h6>CARD HOLDER</h6>
                                            <p>${payment.cardHolderName}</p>
                                        </div>
                                        <div class="col-sm-7 paymentino_col">
                                            <h6>BILLING ADDRESS</h6>
                                            <p>${payment.address}</p>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
            </li>
        </c:if>

        <c:if test="${order.dependentFlag eq false && (sessionBeanPortal.pmap[(80).intValue()].view eq true || sessionBeanPortal.pmap[(80).intValue()].manage eq true)}">
            <li class="dropdown ${isAdmin eq false?'disabledEvents':''}" style="text-decoration: underline;"> <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">Dependents<span>(${fn:length(patientProfile.patientProfileMembersList)})</span>  <i class="fa fa-caret-down" aria-hidden="true"></i></a>
                <div class="dropdown-menu address_dropdown">
                    <ul class="address_list dependent_list">
                        <c:forEach var="members" items="${patientProfile.patientProfileMembersList}" varStatus="theCount">
                            <fmt:formatDate var="memberdobyear" pattern="yyyy" value="${members.birthDate}"/>
                            <li> <i>${theCount.count}</i>
                                <h3><a href="#" onclick="javascript:window.pharmacyNotification.openDependentModel('dependentModal',${members.id}, '${members.firstName}', '${members.lastName}', '<fmt:formatDate pattern="MM/dd/yyyy" value="${members.birthDate}" />', '${members.gender}')"  data-toggle="modal" >${members.firstName}&nbsp;${members.lastName}</a>
                                    <span><fmt:formatDate pattern="MM/dd/yyyy" value="${members.birthDate}" />   (${members.gender})  

                                    </span></h3>
                                <!--<textarea class="form-control" placeholder="Allergies"></textarea>-->
                            </li>

                        </c:forEach>
                    </ul> 
                </div>
            </li>
        </c:if>
        <c:if test="${(sessionBeanPortal.pmap[(81).intValue()].view eq true || sessionBeanPortal.pmap[(81).intValue()].manage eq true)}">
            <li class="dropdown ${isAdmin eq false?'disabledEvents':''}" style="text-decoration: underline;">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">Insurance & copay cards <span>(${fn:length(lstInsuranceCards)})</span>  <i class="fa fa-caret-down" aria-hidden="true"></i></a>
                <div class="dropdown-menu address_dropdown ins_cardlist">
                    <ul class="address_list">
                        <li class="clearfix"> 
                            <c:forEach var="cards" items="${lstInsuranceCards}" varStatus="countCards">
                                <c:if test="${cards.isPrimary eq 1}">
                                    <span>Primary</span>
                                </c:if>
                                <div class="col-sm-6 card_colum">
                                    <h6>Front</h6>
                                    <img id="frontImage"
                                         src="${cards.insuranceFrontCardPath}" alt="insurance card front" 
                                         data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Insurance Card (Front) Click on image for full screen')"/></div>
                                <div class="col-sm-6 card_colum">
                                    <h6>Back</h6>
                                    <img src="${cards.insuranceBackCardPath}" alt="insurance card back" data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Insurance Card (Back) Click on image for full screen')"/> </div>
                                </c:forEach>
                        </li>

                        <li class="clearfix">
                            <h3>COPAY CARDS WITH THIS ORDER <span>${fn:length(copayCards)}</span></h3>
                            <c:forEach var="copay" items="${copayCards}" varStatus="theCount">
                                <div class="col-sm-6 card_colum">
                                    <span>DRUG:${copay.drugBrand} </span>
                                    <h6>Front</h6>
                                    <c:if test="${not empty copay.copayFrontCardPath}">
                                        <img id="frontImage"
                                             src="${not empty copay.copayFrontCardPath?copay.copayFrontCardPath:copay.copayFrontCardPath}" alt="insurance card front" 
                                             data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Copay Card (Front) Click on image for full screen')"/>
                                    </c:if>

                                </div>
                                <div class="col-sm-6 card_colum">
                                    <h6>BAck</h6>
                                    <c:if test="${not empty copay.copayBackCardPath}">
                                        <img src="${not empty copay.copayBackCardPath?copay.copayBackCardPath:copay.copayBackCardPath}" alt="insurance card back" data-toggle="modal"  class="frontsidecardimg" onclick="frontSide(this.src, 'Copay Card (Back) Click on image for full screen')"/>
                                    </c:if>
                                </div>

                            </c:forEach>
                        </li>

                    </ul>
                </div>
            </li>
        </c:if>
        <li  style="text-decoration: underline;" class="${isAdmin eq false?'disabledEvents':''}"> <a href="javascript:window.pharmacyNotification.openProgramRxModel('programRxModal',${order.orderId},'${fn:toUpperCase(order.patientName)}',${patientId})"  > Program Rx’s <span>${processedCount}</span><i aria-hidden="true"></i></a></li>
        <li>Member Since <span><fmt:formatDate value='${patientProfile.createdDate}' pattern='yyyy-MM-dd'/></span></li>
        <li style="display: ${showPtRespQDiv};text-decoration: underline;"><a href="javascript:window.pharmacyNotification.isDisplayPtResponseSection(0)">INCOMMING PT RESPONSE</a></li>
    </ul>
    
    <div class="box-compliance"><span class="compliance_txt">Compliance <br /> Rewards </span><span class="compliance_nmbr"><fmt:formatNumber value="${patientProfile.totalRewardPoints}" pattern="#,###"  /></span></div>
    
    <span class="icon_Status"> <%-- <fmt:formatNumber value="${patientProfile.totalRewardPoints}" pattern="#,###"  />--%> <fmt:formatNumber value="${rewardPoints.availablePoints}" pattern="#,###"  /><br />
        Basic </span> 
        <c:if test="${patientProfile.isOldPatient ne true}">
        <span class="icon_unew"></span> 
    </c:if>
</div>