<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">

    <jsp:include page="./inc/head.jsp" />

    <body>

        <jsp:include page="./inc/header.jsp" />



        <div class="clearfix">

            <jsp:include page="./inc/newMenu.jsp" />

            <div class="breadcrumbs">

                <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Dashboard

            </div>

            <div class="heading" >

                <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Dashboard</h1>

            </div> 

        </div>



        <div class="container contentsContainer" style="margin-top:10px; margin-bottom: 5px;">

            <div class="row row-saprator">

                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 left-devs">

                    <div id="top10PrescriberDiv" style="background:#f7f7f7; border: 1px solid #cdcdce; padding-left: 5px; padding-right: 5px;">

                        <h4 class="table-heading" style="padding-left: 5px;">Top 10 Compliance Reward Prescribers <b class="table-heading-small">(Past 30 days)</b></h4>

                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">

                            <table id="top10PrescriberTbl" class="recentOrder" style="padding-top: 15px; padding-bottom: 15px; width: 100%;">

                                <thead style="border: none !important;">

                                    <tr>

                                        <th class="col-sm-3">Name</th>

                                        <th class="col-sm-1">NPI</th>

                                        <th class="col-sm-1">Rx Trans.</th>

                                        <th class="col-sm-1">Pt. OptIns</th>

                                        <th class="col-sm-1">AWP Cost</th>

                                        <th class="col-sm-2">Degree</th>

                                        <th class="col-sm-2">Specialty</th>

                                        <th class="col-sm-1">Zip</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    <c:choose>

                                        <c:when test="${not empty topTenPrescriberList}">

                                            <c:forEach var="prescriber" items="${topTenPrescriberList}"> 

                                                <tr>

                                                    <td>${prescriber.prescriberFirstName} ${prescriber.prescriberLastName}</td>

                                                    <td>${prescriber.prescriberNpi}</td>

                                                    <td>${prescriber.totalRxTransactions}</td>

                                                    <td>${prescriber.totalPatientOptins}</td>

                                                    <td>$${prescriber.sumOfAWP}</td>

                                                    <td></td>

                                                    <td>${prescriber.prescriberSpecialty}</td>

                                                    <td>${prescriber.prescriberZipCode}</td>

                                                </tr>

                                            </c:forEach>

                                        </c:when>

                                        <c:otherwise>

                                            <tr>

                                                <td colspan="8">

                                                    <div style="text-align: center;">Nothing in queue.</div>

                                                </td>

                                            </tr>

                                        </c:otherwise>

                                    </c:choose>

                                </tbody>

                            </table>

                        </div>

                    </div>

                </div>

                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12" style="padding-bottom: 0;">

                    <div id="top10PharmaciesDiv" style="background:#f7f7f7; border: 1px solid #cdcdce; padding-left: 5px; padding-right: 5px;">

                        <h4 class="table-heading" style="padding-left: 5px;">Top 10 Compliance Reward Pharmacies <b class="table-heading-small">(Past 30 days)</b></h4>

                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">

                            <table id="top10PharmaciesTbl" class="recentOrder" style="padding-top: 15px; padding-bottom: 15px; width: 100%;">

                                <thead style="border: none !important;">

                                    <tr>

                                        <th class="col-sm-3">Pharmacy</th>

                                        <th class="col-sm-1">Rx Trans.</th>

                                        <th class="col-sm-1">Pt. OptIns</th>

                                        <th class="col-sm-1">Shipped Rx</th>

                                        <th class="col-sm-1">AWP Cost</th>

                                        <th class="col-sm-1">RD Ing.%</th>

                                        <th class="col-sm-2">Zip</th>

                                        <th class="col-sm-2">NumedCare Rep.</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    <c:choose>

                                        <c:when test="${not empty topTenPharmacyList}">

                                            <c:forEach var="pharmacy" items="${topTenPharmacyList}"> 

                                                <tr>

                                                    <td>${pharmacy.title}</td>

                                                    <td>${pharmacy.fillCount + pharmacy.shipCount}</td>

                                                    <td>${pharmacy.fillCount - pharmacy.deninedCount}</td>

                                                    <td>${pharmacy.shipCount}</td>

                                                    <td>$${pharmacy.awp}</td>

                                                    <td><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${pharmacy.apiToNonApiRatio}" /></td>

                                                    <td>${pharmacy.zip}</td>

                                                    <td>${pharmacy.salesRep}</td>

                                                </tr>

                                            </c:forEach>

                                        </c:when>

                                        <c:otherwise>

                                            <tr>

                                                <td colspan="8">

                                                    <div style="text-align: center;">Nothing in queue.</div>

                                                </td>

                                            </tr>

                                        </c:otherwise>

                                    </c:choose>

                                </tbody>

                            </table>

                        </div>    

                    </div>

                </div>

            </div>

            <div class="row row-saprator">

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                    <div id="allProgramDataDiv" style="background:#f7f7f7; min-height: 100px; border: 1px solid #cdcdce; padding-left: 5px;">

                        <h4 class="table-heading" style="padding-left: 5px;">Current All Pharmacies & All Compliance Reward Program Products</h4>

                        <div class="col-lg-12  table-responsive" >

                            <table id="allProgramDataTbl" class="recentOrder " style="padding-top: 15px; padding-bottom: 15px; width: 100%;" >

                                <thead style="border: none !important;">

                                    <tr>

                                        <th >Offers Sent</th>

                                        <th >Pt. Responses</th>

                                        <th >Response%</th>

                                        <th >Rx InQueue</th>

                                        <th >AWP of Rx In Queue</th>

                                        <th >RD Ing.</th>

                                        <th >Rebate Paid</th>

                                        <th >Other Ing.</th>

                                        <th >RD Ing.%</th>

                                        <th >Payment Collected</th>

                                        <th ># Rx Trans.</th>

                                        <th >CC Fee</th>

                                        <th >Phar. Trans. Fee</th>

                                        <th >Net Phar. Payable</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    <c:choose>

                                        <c:when test="${not empty allProgramData}">

                                            <tr>

                                                <%--<td>${allProgramData.totalRxMessages}</td>

                                                <td>${allProgramData.totalPtResponses}</td>

                                                <td><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${((allProgramData.totalPtResponses/allProgramData.totalRxMessages)*100)}" /></td>

                                                <td>${allProgramData.totalRxInQueue}</td>

                                                <td>$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${allProgramData.awpOfRxInQueue}" /></td>

                                                <td>${allProgramData.totalApiIngredients}</td>

                                                <td>$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${allProgramData.totalRebatesPaid}" /></td>

                                                <td>${allProgramData.totalOtherIngredients}</td>    

                                                <td><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${(allProgramData.totalApiIngredients/(allProgramData.totalApiIngredients + allProgramData.totalOtherIngredients))*100}" /></td>

                                                <td>$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${allProgramData.totalPaymentCollected}" /></td>

                                                <td>${allProgramData.totalPtResponses}</td>--%>

                                                <td></td>

                                                <td></td>

                                                <td></td>

                                            </tr>



                                        </c:when>

                                        <c:otherwise>

                                            <tr>

                                                <td colspan="14">

                                                    <div style="text-align: center;">Nothing in queue.</div>

                                                </td>

                                            </tr>

                                        </c:otherwise>

                                    </c:choose>

                                </tbody>

                            </table>

                        </div>

                        <div class="hidden-lg">

                            <div class="row" style="background-color: white; color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    Offers Sent

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    

                                </div>

                            </div>

                            <div class="row" style="color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    Pt. Responses

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--${allProgramData.totalPtResponses}--%>

                                </div>

                            </div>

                            <div class="row" style="background-color: white; color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    Response%

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${((allProgramData.totalPtResponses/allProgramData.totalRxMessages)*100)}" />%--%>

                                </div>

                            </div>

                            <div class="row" style="color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    Rx InQueue

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--${allProgramData.totalRxInQueue}--%>

                                </div>

                            </div>

                            <div class="row" style="background-color: white; color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    AWP of Rx In Queue

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${allProgramData.awpOfRxInQueue}" />--%>

                                </div>

                            </div>

                            <div class="row" style="color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    API Ing.

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--${allProgramData.totalApiIngredients}--%>

                                </div>

                            </div>

                            <div class="row" style="background-color: white; color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    Rebate Paid

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${allProgramData.totalRebatesPaid}" />--%>

                                </div>

                            </div>

                            <div class="row" style="color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    Other Ing.

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                   <%-- ${allProgramData.totalOtherIngredients}--%>

                                </div>

                            </div>

                            <div class="row" style="background-color: white; color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    API Ing.%

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${(allProgramData.totalApiIngredients/(allProgramData.totalApiIngredients + allProgramData.totalOtherIngredients))*100}" />%--%>

                                </div>

                            </div>

                            <div class="row" style="color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    Payment Collected

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${allProgramData.totalPaymentCollected}" />--%>

                                </div>

                            </div>

                            <div class="row" style="background-color: white; color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    # Rx Trans.

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    <%--${allProgramData.totalPtResponses}--%>

                                </div>

                            </div>

                            <div class="row" style="color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                    CC Fee

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    

                                </div>

                            </div>

                            <div class="row" style="background-color: white; color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                   Phar. Trans. Fee

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    

                                </div>

                            </div>

                            <div class="row" style="color: black;">

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: left;">

                                     Net Phar. Payable

                                </div>

                                <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right;">

                                    

                                </div>

                            </div>

                            

                        </div>                

                    </div>

                </div>

            </div>

        </div> 



        <jsp:include page="./inc/footer.jsp" />



    </body>

</html>