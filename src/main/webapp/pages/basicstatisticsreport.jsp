<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<style type="text/css">
    .input-group{
        width: 100%;
    }
</style>
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home  <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Basic Statistics Report
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Basic Statistics Report</h1>
                </div>
                <form:form action="${pageContext.request.contextPath}/report/view" commandName="baseDTO" role="form" method="Post" onsubmit="return validateField()">
                    <div class="col-sm-12 surveyreports-ipad" style="padding-top: 15px;">
                        <div class="form-group margin-ive">
                            <div class="col-lg-8  drp-ipad" style="padding-left: 0px;padding-right: 0;">
                                <span class="search-head">Search Criteria:</span>
                                <div class="col-lg-2  drp-ipad" style="padding-left: 0px;padding-right: 0; margin-right: 15px;">

                                    <div class="input-group" style="display: inline-block;">

                                        <fmt:formatDate var="fromDate" pattern="MM/dd/yyyy" value="${baseDTO.fromDate}"/>
                                        <!--                                    <span class="input-group-addon" id="basic-addon2">From:<span style="color:red">*</span></span>-->
                                        <form:input id="datetimepicker" path="fromDate" cssClass="form-control" value="${fromDate}" aria-describedby="basic-addon2" placeholder="Start Date"/>
                                    </div>
                                    <span id="fromDateReq" class="errorMessage"></span>
                                </div>

                                <!--                            <div class="col-sm-2 col-xs-12 drp-ipad">
                                
                                                                
                                                            </div>-->
                                <div class="col-lg-2 col-xs-12 drp-ipad" style="padding-left: 0px;padding-right: 15px; margin-right: 15px;">
                                    <div class="input-group">
                                        <fmt:formatDate var="toDate" pattern="MM/dd/yyyy" value="${baseDTO.toDate}"/>
                                        <!--                                    <span class="input-group-addon" id="basic-addon3">To:<span style="color:red">*</span></span>-->
                                        <form:input id="datetimepicker1" path="toDate" cssClass="form-control" value="${toDate}" aria-describedby="basic-addon3" placeholder="End Date"/>
                                    </div>
                                    <span id="toDateReq" class="errorMessage"></span>

                                </div>
                                <div class="btn-group btn-dropright-ipad">
                                    <button class="btndrop" style="color: white; margin-top: 0; background-color: #2071b6;">Search</button>
                                </div>
                                <div class="btn-group btn-drop-ipad" >
                                    <a href="${pageContext.request.contextPath}/report/view" class="btndrop" style="color: white;background-color: #2071b6;">Cancel</a>
                                </div>
                                <!--                                   <button class="btndrop" style="color: white; margin-top: 0; background-color: #2071b6;">Search</button>  -->
                            </div>
                            <!--                            <div class="col-sm-4 col-xs-12" style="margin-top: -9px;">
                                                            
                                                            
                                                        </div>-->
                        </div>
                        <br clear="all">
                    </div>
                </form:form>
                <div class="wrp clearfix" style="padding-left: 15px;padding-right: 45px;">
                    <div class="pull-right btndrop" style="color: white;margin-bottom: 10px;" onclick="exportReportData('excelView')">Export Excel</div>
                    <div class="pull-right btndrop" style="color: white" onclick="exportReportData('pdfView')">Export Pdf</div>
                </div>

                <div class="clearfix" style="padding-left: 15px;padding-right: 15px;">
                    <div class="row" style="height: auto;border-top: 0px;margin-bottom:45px; padding-top: 5px;">
                        <div id="table-custom" class="contents">                                   
                            <div class="table-box">
                                
                                                 
                                
                                <table class ="table borderless table-hover">
                                    <tr>
                                        <th> Rx Mix </th>
                                        <th>Count</th>
                                        <th> % </th>
                                        <th></th>
                                    </tr>
                                    <tr>
                                        <td>Brand</td>
                                        <td>${basicStatisticsReportData.brandNumber !=null?basicStatisticsReportData.brandNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.brandCount !=null?basicStatisticsReportData.brandCount:'0'}</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Generic</td>
                                        <td>${basicStatisticsReportData.genericNumber !=null?basicStatisticsReportData.genericNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.genericCount !=null?basicStatisticsReportData.genericCount:'0'}</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>${basicStatisticsReportData.totalRxMixNumber!=null?basicStatisticsReportData.totalRxMixNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.totalRxMixCount!=null?basicStatisticsReportData.totalRxMixCount:'0'}</td>
                                        <td></td>
                                    </tr>

                                </table>
                                    
                                    <table class ="table borderless table-hover" >
                                    <tr>
                                        <th>Gender </th>
                                        <th>Count</th>
                                        <th> % </th>
                                        <th></th>
                                    </tr>
                                    <tr>
                                        <td> Male </td>
                                        <td>${basicStatisticsReportData.totalMaleGenderNumber !=null?basicStatisticsReportData.totalMaleGenderNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.totalMaleGender !=null?basicStatisticsReportData.totalMaleGender:'0'} </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td> Female</td>
                                        <td>${basicStatisticsReportData.totalFemaleGenderNumber !=null?basicStatisticsReportData.totalFemaleGenderNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.totalFemaleGender !=null?basicStatisticsReportData.totalFemaleGender:'0'} </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>${basicStatisticsReportData.totalGenderNumber!=null?basicStatisticsReportData.totalGenderNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.totalGenderCount!=null?basicStatisticsReportData.totalGenderCount:'0'} </td>
                                        <td></td>
                                    </tr>

                                </table>
                                <table class ="table borderless table-hover">
                                    <tr>
                                        <th> Insurance </th>
                                        <th>Count</th>
                                        <th> % </th>
                                        
                                    </tr>
                                    <tr>
                                        <td>Commercial</td>
                                        <td>${basicStatisticsReportData.commercialInusranceNumber !=null?basicStatisticsReportData.commercialInusranceNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.commercialInsuranceCount !=null?basicStatisticsReportData.commercialInsuranceCount:'0'}</td>
                                        
                                    </tr>
                                    <tr>
                                        <td>Public</td>
                                        <td>${basicStatisticsReportData.publicMedicareNumber !=null?basicStatisticsReportData.publicMedicareNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.publicMedicareCount !=null?basicStatisticsReportData.publicMedicareCount:'0'}</td>
                                        
                                    </tr>
                                    <tr>
                                        <td>Self Pay</td>
                                        <td>${basicStatisticsReportData.selfPayNumber !=null?basicStatisticsReportData.selfPayNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.selfPayCount !=null?basicStatisticsReportData.selfPayCount:'0'}</td>
                                        
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>${basicStatisticsReportData.totalInsuranceNumber!=null?basicStatisticsReportData.totalInsuranceNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.totalInsuranceCount!=null?basicStatisticsReportData.totalInsuranceCount:'0'}</td>
                                        
                                    </tr>

                                </table>
                                    <table class ="table borderless table-hover">
                                    <tr>
                                        <th> Patient Type </th>
                                        <th>Count</th>
                                        <th> % </th>
                                    </tr>
                                    <tr>
                                        <td> a) Account holder </td>
                                        <td>${basicStatisticsReportData.accountHolderNumber !=null?basicStatisticsReportData.accountHolderNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.accountHolderCount !=null?basicStatisticsReportData.accountHolderCount:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> b) Child dependant</td>
                                        <td>${basicStatisticsReportData.childDependentNumber !=null?basicStatisticsReportData.childDependentNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.childDependantCount !=null?basicStatisticsReportData.childDependantCount:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> c) Adult dependant</td>
                                        <td>${basicStatisticsReportData.adultDependentNumber!=null?basicStatisticsReportData.adultDependentNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.adultDependantCount!=null?basicStatisticsReportData.adultDependantCount:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> Total</td>
                                        <td>${basicStatisticsReportData.totalPatientTypeNumber!=null?basicStatisticsReportData.totalPatientTypeNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.totalPatientTypeCount!=null?basicStatisticsReportData.totalPatientTypeCount:'0'} </td>
                                    </tr>
                                </table>
                                    <table class ="table borderless table-hover">
                                    <tr>
                                        <th> Rx Fulfillment </th>
                                        <th>Count</th>
                                        <th> % </th>
                                    </tr>
                                    <tr>
                                        <td>2nd Day</td>
                                        <td>${basicStatisticsReportData.day2ndNumber !=null?basicStatisticsReportData.day2ndNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.day2ndCount !=null?basicStatisticsReportData.day2ndCount:'0'}</td>
                                    </tr>
                                    <tr>
                                        <td>Prem-Next Day</td>
                                        <td>${basicStatisticsReportData.nextDayNumber !=null?basicStatisticsReportData.nextDayNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.nextDayCount !=null?basicStatisticsReportData.nextDayCount:'0'}</td>
                                    </tr>
                                    <tr>
                                        <td>Premium-Same Day</td>
                                        <td>${basicStatisticsReportData.sameDayNumber !=null?basicStatisticsReportData.sameDayNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.sameDayCount !=null?basicStatisticsReportData.sameDayCount:'0'}</td>
                                    </tr>
                                    <tr>
                                        <td>Pick Up</td>
                                        <td>${basicStatisticsReportData.pickUpNumber !=null?basicStatisticsReportData.pickUpNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.pickUpCount !=null?basicStatisticsReportData.pickUpCount:'0'}</td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>${basicStatisticsReportData.totalRxFulfillmentNumber !=null?basicStatisticsReportData.totalRxFulfillmentNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.totalRxFullfillmentCount !=null?basicStatisticsReportData.totalRxFullfillmentCount:'0'}</td>
                                    </tr>

                                </table>
                                
                                <table class ="table borderless table-hover">
                                    <tr>
                                        <th> Patient Out of Pocket </th>
                                        <th>Count</th>
                                        <th> % </th>
                                    </tr>
                                    <tr>
                                        <td> Zero </td>
                                        <td>${basicStatisticsReportData.ptOutofPocketNumber !=null?basicStatisticsReportData.ptOutofPocketNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.ptOutofPocketCount !=null?basicStatisticsReportData.ptOutofPocketCount:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> $0.01 -$25 </td>
                                        <td>${basicStatisticsReportData.ptOutofPocket1To25Number !=null?basicStatisticsReportData.ptOutofPocket1To25Number:'0'}</td>
                                        <td> ${basicStatisticsReportData.ptOutofPocket1To25Count !=null?basicStatisticsReportData.ptOutofPocket1To25Count:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> $26 - $75 </td>
                                        <td>${basicStatisticsReportData.ptOutofPocket26To75Number !=null?basicStatisticsReportData.ptOutofPocket26To75Number:'0'}</td>
                                        <td> ${basicStatisticsReportData.ptOutofPocket26To75Count !=null?basicStatisticsReportData.ptOutofPocket26To75Count:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td>$76 or more</td>
                                        <td>${basicStatisticsReportData.ptOutofPocket76GreaterNumber !=null?basicStatisticsReportData.ptOutofPocket76GreaterNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.ptOutofPocket76GreaterCount !=null?basicStatisticsReportData.ptOutofPocket76GreaterCount:'0'}</td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>${basicStatisticsReportData.totalPtOutofPocketNumber!=null?basicStatisticsReportData.totalPtOutofPocketNumber:'0'}</td>
                                        <td>${basicStatisticsReportData.totalPtOutOfPocketCount!=null?basicStatisticsReportData.totalPtOutOfPocketCount:'0'}</td>
                                    </tr>
                                </table>
                                    <table class ="table borderless table-hover">
                                    <tr>
                                        <th> Age By Rx Recipient </th>
                                        <th>Count</th>
                                        <th> % </th>
                                    </tr>
                                    <tr>
                                        <td> 0-17 years </td>
                                        <td>${basicStatisticsReportData.underAge17Count !=null?basicStatisticsReportData.underAge17Count:'0'}</td>
                                        <td> ${basicStatisticsReportData.underAge0To17Count !=null?basicStatisticsReportData.underAge0To17Count:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> 18 - 34 years </td>
                                        <td>${basicStatisticsReportData.underAge18Count !=null?basicStatisticsReportData.underAge18Count:'0'}</td>
                                        <td> ${basicStatisticsReportData.underAge18To34Count !=null?basicStatisticsReportData.underAge18To34Count:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> 35 - 50 years </td>
                                        <td>${basicStatisticsReportData.underAge35Count !=null?basicStatisticsReportData.underAge35Count:'0'}</td>
                                        <td> ${basicStatisticsReportData.underAge35To50Count !=null?basicStatisticsReportData.underAge35To50Count:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> 51 - 64 years </td>
                                        <td>${basicStatisticsReportData.underAge51Count !=null?basicStatisticsReportData.underAge51Count:'0'}</td>
                                        <td> ${basicStatisticsReportData.underAge51To64Count !=null?basicStatisticsReportData.underAge51To64Count:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td> 65+ </td>
                                        <td>${basicStatisticsReportData.underAge65Count !=null?basicStatisticsReportData.underAge65Count:'0'}</td>
                                        <td> ${basicStatisticsReportData.age65MoreCount !=null?basicStatisticsReportData.age65MoreCount:'0'} </td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>${basicStatisticsReportData.totalAgeByRxRecipientNumber!=null?basicStatisticsReportData.totalAgeByRxRecipientNumber:'0'}</td>
                                        <td> ${basicStatisticsReportData.totalAgeByRxRecipientCount!=null?basicStatisticsReportData.totalAgeByRxRecipientCount:'0'} </td>
                                    </tr>
                                </table>
                               
                                
                                
                                <!--                                <table class="col-sm-12">
                                                                    <tr>
                                                                        <td valign="top">
                                                                            <table class="display" class="display table">
                                                                                <thead>
                                                                                    <tr class="row grid-header">
                                                                                        <th style="padding-left: 10px;">Patient Type</th>
                                                                                        <th>%</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Account Holder</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.accountHolderCount !=null?basicStatisticsReportData.accountHolderCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Child Dependant</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.childDependantCount !=null?basicStatisticsReportData.childDependantCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Adult Dependant</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.adultDependantCount!=null?basicStatisticsReportData.adultDependantCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Total</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalPatientTypeCount!=null?basicStatisticsReportData.totalPatientTypeCount:'0'}</td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <table class="display" class="display table">
                                                                                <thead>
                                                                                    <tr class="row grid-header">
                                                                                        <th>Gender</th>
                                                                                        <th>%</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Male</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalMaleGender !=null?basicStatisticsReportData.totalMaleGender:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Female</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalFemaleGender !=null?basicStatisticsReportData.totalFemaleGender:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Total</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalGenderCount!=null?basicStatisticsReportData.totalGenderCount:'0'}</td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <table class="display" class="display table">
                                                                                <thead>
                                                                                    <tr class="row grid-header">
                                                                                        <th>Age By Rx Recipient</th>
                                                                                        <th>%</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="row grid-row">
                                                                                        <td>0-17 years</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.underAge0To17Count !=null?basicStatisticsReportData.underAge0To17Count:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>18 - 34 years</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.underAge18To34Count !=null?basicStatisticsReportData.underAge18To34Count:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>35 - 50 years</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.underAge35To50Count !=null?basicStatisticsReportData.underAge35To50Count:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>51 - 64 years</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.underAge51To64Count !=null?basicStatisticsReportData.underAge51To64Count:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>65+</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.age65MoreCount !=null?basicStatisticsReportData.age65MoreCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Total</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalAgeByRxRecipientCount!=null?basicStatisticsReportData.totalAgeByRxRecipientCount:'0'}</td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <table class="display" class="display table">
                                                                                <thead>
                                                                                    <tr class="row grid-header">
                                                                                        <th>Patient Out of Pocket</th>
                                                                                        <th>%</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Zero</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.ptOutofPocketCount !=null?basicStatisticsReportData.ptOutofPocketCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>$0.01 -$25</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.ptOutofPocket1To25Count !=null?basicStatisticsReportData.ptOutofPocket1To25Count:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>$26 - $75</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.ptOutofPocket26To75Count !=null?basicStatisticsReportData.ptOutofPocket26To75Count:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>$76 or more</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.ptOutofPocket76GreaterCount !=null?basicStatisticsReportData.ptOutofPocket76GreaterCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Total</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalPtOutOfPocketCount!=null?basicStatisticsReportData.totalPtOutOfPocketCount:'0'}</td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <table class="display" class="display table">
                                                                                <thead>
                                                                                    <tr class="row grid-header">
                                                                                        <th>Rx Mix</th>
                                                                                        <th>%</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Brand</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.brandCount !=null?basicStatisticsReportData.brandCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Generic</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.genericCount !=null?basicStatisticsReportData.genericCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Total</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalRxMixCount!=null?basicStatisticsReportData.totalRxMixCount:'0'}</td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <table class="display" class="display table">
                                                                                <thead>
                                                                                    <tr class="row grid-header">
                                                                                        <th>Rx Fulfillment</th>
                                                                                        <th>%</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="row grid-row">
                                                                                        <td>2nd Day</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.day2ndCount !=null?basicStatisticsReportData.day2ndCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Prem-Next Day</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.nextDayCount !=null?basicStatisticsReportData.nextDayCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Premium-Same Day</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.sameDayCount !=null?basicStatisticsReportData.sameDayCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Pick Up</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.pickUpCount !=null?basicStatisticsReportData.pickUpCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Total</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalRxFullfillmentCount !=null?basicStatisticsReportData.totalRxFullfillmentCount:'0'}</td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                        <td valign="top">
                                                                            <table class="display" class="display table">
                                                                                <thead>
                                                                                    <tr class="row grid-header">
                                                                                        <th>Insurance</th>
                                                                                        <th>%</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Commercial</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.commercialInsuranceCount !=null?basicStatisticsReportData.commercialInsuranceCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Public</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.publicMedicareCount !=null?basicStatisticsReportData.publicMedicareCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Self pay</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.selfPayCount!=null?basicStatisticsReportData.selfPayCount:'0'}</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr class="row grid-row">
                                                                                        <td>Total</td>
                                                                                        <td style="padding-left: 0px;">${basicStatisticsReportData.totalInsuranceCount!=null?basicStatisticsReportData.totalInsuranceCount:'0'}</td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                
                                                                </table>-->
                            </div>
                        </div>
                    </div>
                </div> <!-- /wrp -->
            </div> <!-- /content -->
        </div> <!-- /wrapper -->
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            $('#datetimepicker').datetimepicker({timepicker: false,
                format: 'm/d/Y',
                maxDate: 0,
                onChangeDateTime: function (dp, $input) {
                    jQuery('#datetimepicker').datetimepicker('hide');
                }

            });
            $('#datetimepicker1').datetimepicker({timepicker: false,
                format: 'm/d/Y',
                onChangeDateTime: function (dp, $input) {
                    jQuery('#datetimepicker1').datetimepicker('hide');
                }

            });

            function exportReportData(format) {
                if (!validateField()) {
                    return;
                }
                window.open('${pageContext.request.contextPath}/report/export?fromDate=' + $("#datetimepicker").val() + '&toDate=' + $("#datetimepicker1").val() + '&format=' + format, "_blank");
            }
            function validateField() {
                var valid = true;

                if ($("#datetimepicker").val() == "") {
                    $("#fromDateReq").text("Required");
                    $("#fromDateReq").show();
                    valid = false;
                }
                if ($("#datetimepicker1").val() == "") {
                    $("#toDateReq").text("Required");
                    $("#toDateReq").show();
                    valid = false;
                }

                return valid;
            }
        </script>
    </body>
</html>
