<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

    String url = request.getRequestURL().toString();
    String role = "";
    String setup = "";
    String democampaign = "";
    String reportOne = "";
    String reportTwo = "";
    String home = "";
    String survey = "";
    String order = "";
    String reports = "";
    String portal = "";
    String reportThree = "";
    String patient = "";
    String patientNoOrderReport = "";
    if (url.contains("index")) {
        home = "class='active'";
    }
    if (!url.contains("widget") && (url.contains("role") || url.contains("user") || url.contains("permission") || url.contains("changepassword"))) {
        role = "class='active'";
    }
    if (url.contains("druglist") || url.contains("adddrug") || url.contains("widget") || url.contains("feesetup.jsp") || url.contains("deliverypreferences.jsp")
            || url.contains("smtp") || url.contains("drug") || url.contains("ingredient") || url.contains("rewardsetup.jsp") || url.contains("shippingmileslist.jsp") || url.contains("addshippingmiles.jsp") || url.contains("adddeliverydsitances.jsp")) {
        setup = "class='active'";
    }

    if (url.contains("campaign") || url.contains("event") || url.contains("resposelist") || url.contains("folder") || url.contains("intervaltypelist") || url.contains("addresponse") || url.contains("addmessagetype") || url.contains("addintervaltype") || url.contains("messagetypelist")) {
        democampaign = "class='active'";
    }
    if (url.contains("survey") || url.contains("surveyResponse") || url.contains("surveyQuestion") || url.contains("surveyEmailTemplate")) {
        survey = "class='active'";
    }
    if (url.contains("order") && url.contains("placeorder") || url.contains("ordersummary")) {
        order = "class='active'";
    }
    if (url.contains("orderlist") || url.contains("orderdetail") || url.contains("cmsdocumentslist") || url.contains("addcmscontent")
            || url.contains("addcmsdocuments") || url.contains("cmsemailsetup") || url.contains("pharmacy") || url.contains("managepharmacieslist")) {
        setup = "class='active'";
    }

    if (url.contains("report.jsp") && !url.contains("surveyreports")) {
        reportOne = "class='active'";
    } else if (url.contains("reports.jsp") && !url.contains("surveyreports") && !url.contains("apiprogramreports.jsp")) {
        reportTwo = "class='active'";
    } else if (url.contains("apiprogramreports.jsp") && !url.contains("surveyreports")) {
        reportThree = "class='active'";
    }
    if (url.contains("patient") || url.contains("rxtransferrequestlist.jsp") || url.contains("rxtransferrequest.jsp")) {
        patient = "class='active'";
    }
    if (url.contains("patientNoOrderReport") || url.contains("surveyreports") || url.contains("inappnotificationreport")) {
        patient = "";
        survey = "";
        patientNoOrderReport = "class='active'";
    }
%>  
<header  class="  navbar navbar-static-top bs-docs-nav header-margin-top320">

    <div class="menu_header">

        <div class="navbar-header">
            <button data-target=".bs-navbar-collapse" data-toggle="collapse" type="button" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>

            </button>
            <a style="color: white;
               font-size: 18px;
               font-weight: bold;
               line-height: 55px;
               margin-right: 12px;" class="hidden-lg hidden-md hidden-sm pull-right" href="${pageContext.request.contextPath}/logout"><i class="fa fa-power-off"></i> Logout</a>
        </div>
        <nav role="navigation" class="collapse navbar-collapse bs-navbar-collapse">
            <ul class="nav navbar-nav">
                <li <%=home%>>
                    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                </li>
                <c:if test="${(sessionBean.pmap[(6).intValue()].view eq true || sessionBean.pmap[(6).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(7).intValue()].view eq true || sessionBean.pmap[(7).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(12).intValue()].view eq true || sessionBean.pmap[(12).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(13).intValue()].view eq true || sessionBean.pmap[(13).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(11).intValue()].view eq true || sessionBean.pmap[(11).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(9).intValue()].view eq true || sessionBean.pmap[(9).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(10).intValue()].view eq true || sessionBean.pmap[(10).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(8).intValue()].view eq true || sessionBean.pmap[(8).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(14).intValue()].view eq true || sessionBean.pmap[(14).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(27).intValue()].view eq true || sessionBean.pmap[(27).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(52).intValue()].view eq true || sessionBean.pmap[(52).intValue()].manage eq true)}">
                      <li <%=setup%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Program Setup &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <li>
                                  <a href="${pageContext.request.contextPath}/feeSettings/load">Fee Setup &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}/rewardPoints/load">Points Setup &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}/deliveryDsitances/add">Shipping Distance &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}/shippingMiles/list">Shipping Miles &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}/drugAdditionalMargin/list">Drug Margin Setup &nbsp;</a>
                              </li>
                              <c:if test="${(sessionBean.pmap[(8).intValue()].view eq true || sessionBean.pmap[(8).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li style="display: none;"><a href="${pageContext.request.contextPath}/drugBrand/list">Compound Setup</a></li>

                              </c:if>

                              <c:if test="${(sessionBean.pmap[(14).intValue()].view eq true || sessionBean.pmap[(14).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/smtp/list">SMTP Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(52).intValue()].view eq true || sessionBean.pmap[(52).intValue()].manage eq true)}">
                                  <li>
                                      <a href="${pageContext.request.contextPath}/CmsPageContent/add">Contents Setup &nbsp;</a>
                                  </li>
                                  <li>
                                      <a href="${pageContext.request.contextPath}/email/add">Emails Setup &nbsp;</a>
                                  </li>
                              </c:if>
                          </ul>
                      </li>
                </c:if>

                <!-- Haider Ali  Drug Setup -->      
                <c:if test="${(sessionBean.pmap[(6).intValue()].view eq true || sessionBean.pmap[(6).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(7).intValue()].view eq true || sessionBean.pmap[(7).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(12).intValue()].view eq true || sessionBean.pmap[(12).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(13).intValue()].view eq true || sessionBean.pmap[(13).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(11).intValue()].view eq true || sessionBean.pmap[(11).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(9).intValue()].view eq true || sessionBean.pmap[(9).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(10).intValue()].view eq true || sessionBean.pmap[(10).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(8).intValue()].view eq true || sessionBean.pmap[(8).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(14).intValue()].view eq true || sessionBean.pmap[(14).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(27).intValue()].view eq true || sessionBean.pmap[(27).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(52).intValue()].view eq true || sessionBean.pmap[(52).intValue()].manage eq true)}">
                      <li <%=setup%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Drug Setup &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <li>
                                  <!--
                                  <a href="${pageContext.request.contextPath}/drugBrand/list/category">Drug Setup &nbsp;</a>-->
                                  <a href="${pageContext.request.contextPath}/drugBrand/list/drugdata">Drug Setup &nbsp;</a>
                              </li>

                          </ul>
                      </li>
                </c:if>

                <c:if test="${(sessionBean.pmap[(53).intValue()].view eq true || sessionBean.pmap[(53).intValue()].manage eq true) || (sessionBean.pmap[(54).intValue()].view eq true || sessionBean.pmap[(54).intValue()].manage eq true) || (sessionBean.pmap[(55).intValue()].view eq true
                              || sessionBean.pmap[(55).intValue()].manage eq true) || (sessionBean.pmap[(56).intValue()].view eq true || sessionBean.pmap[(56).intValue()].manage eq true)}">
                      <li <%=democampaign%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Msging Setup &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(12).intValue()].view eq true || sessionBean.pmap[(12).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/response/list">Response Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(13).intValue()].view eq true || sessionBean.pmap[(13).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/intervalsType/list">Interval Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(11).intValue()].view eq true || sessionBean.pmap[(11).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/event/list">Event Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(9).intValue()].view eq true || sessionBean.pmap[(9).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/folder/list">Folder Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(10).intValue()].view eq true || sessionBean.pmap[(10).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/messageType/list">Msg Type Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(53).intValue()].view eq true || sessionBean.pmap[(53).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/campaign/SMS/13">Msgs Setup</a></li>
                                  </c:if>

                          </ul>
                      </li>
                </c:if>
                <li <%=patient%>>
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">Patients &nbsp;<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${(sessionBean.pmap[(57).intValue()].view eq true || sessionBean.pmap[(57).intValue()].manage eq true)}">
                            <li><a href="${pageContext.request.contextPath}/patient/detail">Patient Profile</a></li>
                            </c:if>
                       <!-- <li><a href="${pageContext.request.contextPath}/rxTransfer/list">Rx Transfer Requests</a></li>-->

                    </ul>
                </li>
                <c:if test="${(sessionBean.pmap[(29).intValue()].view eq true || sessionBean.pmap[(29).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(30).intValue()].view eq true || sessionBean.pmap[(30).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(31).intValue()].view eq true || sessionBean.pmap[(31).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(32).intValue()].view eq true || sessionBean.pmap[(32).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(33).intValue()].view eq true || sessionBean.pmap[(33).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(58).intValue()].view eq true || sessionBean.pmap[(58).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">

                      <li <%=survey%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Survey &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(29).intValue()].view eq true || sessionBean.pmap[(29).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/surveyResponseType/list">Response Type Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(30).intValue()].view eq true || sessionBean.pmap[(30).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/surveyResponse/list">Response Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(31).intValue()].view eq true || sessionBean.pmap[(31).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/surveyQuestion/list">Question Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(33).intValue()].view eq true || sessionBean.pmap[(33).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/survey/list">Survey Setup</a></li>
                                  </c:if>
                          </ul>
                      </li>

                      <li <%=patientNoOrderReport%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Reports &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(58).intValue()].view eq true || sessionBean.pmap[(58).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li>
                                      <a href="${pageContext.request.contextPath}/campaign/surveyReports">Survey Report</a>
                                  </li>
                              </c:if>
                              <c:if test="${(sessionBean.pmap[(60).intValue()].view eq true || sessionBean.pmap[(60).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}/inAppReport/view">In App Notification Report</a></li>
                                  </c:if>
                              <li><a href="${pageContext.request.contextPath}/campaign/patientNoOrderReport" id="rptLnkInactivePatients">Enrolled Patient No Order Report</a></li>
                              <li><a href="#" id="rptLnkFinancial">Financial Report</a></li>

                          </ul>
                      </li>



                </c:if>
                <c:if test="${(sessionBean.pmap[(3).intValue()].view eq true || sessionBean.pmap[(3).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(4).intValue()].view eq true || sessionBean.pmap[(4).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(5).intValue()].view eq true || sessionBean.pmap[(5).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                      <li <%=role%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">User Administration &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(3).intValue()].view eq true || sessionBean.pmap[(3).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li class="link"> <a href="${pageContext.request.contextPath}/role/list">Manage Roles</a> </li>
                                  </c:if>

                              <c:if test="${(sessionBean.pmap[(4).intValue()].view eq true || sessionBean.pmap[(4).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li class="" ><a href="${pageContext.request.contextPath}/permission/load">Manage Role Permissions</a></li>
                                  </c:if>

                              <c:if test="${(sessionBean.pmap[(5).intValue()].view eq true || sessionBean.pmap[(5).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li class="link"> <a href="${pageContext.request.contextPath}/user/list">Manage Users</a>  </li>
                                  </c:if>
                          </ul>
                      </li>
                </c:if>
                <c:if test="${(sessionBean.pmap[(37).intValue()].view eq true || sessionBean.pmap[(37).intValue()].manage eq true)
                              || (sessionBean.pmap[(20).intValue()].view eq true || sessionBean.pmap[(20).intValue()].manage eq true)
                              || (sessionBean.pmap[(21).intValue()].view eq true || sessionBean.pmap[(21).intValue()].manage eq true)
                              || (sessionBean.pmap[(24).intValue()].view eq true || sessionBean.pmap[(24).intValue()].manage eq true)
                              || (sessionBean.pmap[(19).intValue()].view eq true || sessionBean.pmap[(19).intValue()].manage eq true)
                              || (sessionBean.pmap[(25).intValue()].view eq true || sessionBean.pmap[(25).intValue()].manage eq true)
                              || (sessionBean.pmap[(16).intValue()].view eq true || sessionBean.pmap[(16).intValue()].manage eq true)
                              || (sessionBean.pmap[(36).intValue()].view eq true || sessionBean.pmap[(36).intValue()].manage eq true)
                              || (sessionBean.pmap[(17).intValue()].view eq true || sessionBean.pmap[(17).intValue()].manage eq true)}">
                      <li <%=reportOne%> style="display: none;">
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle"> Rx-Direct Reports&nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(37).intValue()].view eq true || sessionBean.pmap[(37).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=rebate&title=NDC Rebate Offers&tab=1">NDC Rebate Offers</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(20).intValue()].view eq true || sessionBean.pmap[(20).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=optin&title=Opt-In&tab=1">Opt-In Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(21).intValue()].view eq true || sessionBean.pmap[(21).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=optout&title=Opt-Out&tab=1">Opt-Out Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(24).intValue()].view eq true || sessionBean.pmap[(24).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=activitylog&title=Activity Log&tab=1">Activity Log Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(19).intValue()].view eq true || sessionBean.pmap[(19).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=ivr&title=IVR&tab=1">IVR Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(25).intValue()].view eq true || sessionBean.pmap[(25).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=momt&title=MO/MT&tab=1">MO/MT Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(16).intValue()].view eq true || sessionBean.pmap[(16).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=redemption&title=Daily Redemption&tab=1">DailyRedemption Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(36).intValue()].view eq true || sessionBean.pmap[(36).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=instantredemption&title=Instant Redemption&tab=1">InstantRedemption Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(17).intValue()].view eq true || sessionBean.pmap[(17).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/report.jsp?name=message&title=Message&tab=1">Message Report</a></li>
                                  </c:if> 
                          </ul>
                      </li>
                </c:if>
                <c:if test="${(sessionBean.pmap[(38).intValue()].view eq true || sessionBean.pmap[(38).intValue()].manage eq true)
                              || (sessionBean.pmap[(15).intValue()].view eq true || sessionBean.pmap[(15).intValue()].manage eq true)
                              || (sessionBean.pmap[(39).intValue()].view eq true || sessionBean.pmap[(39).intValue()].manage eq true)
                              || (sessionBean.pmap[(40).intValue()].view eq true || sessionBean.pmap[(40).intValue()].manage eq true)
                              || (sessionBean.pmap[(41).intValue()].view eq true || sessionBean.pmap[(41).intValue()].manage eq true)
                              || (sessionBean.pmap[(35).intValue()].view eq true || sessionBean.pmap[(35).intValue()].manage eq true)
                              || (sessionBean.pmap[(51).intValue()].view eq true || sessionBean.pmap[(51).intValue()].manage eq true)
                              || (sessionBean.pmap[(42).intValue()].view eq true || sessionBean.pmap[(42).intValue()].manage eq true)
                              || (sessionBean.pmap[(43).intValue()].view eq true || sessionBean.pmap[(43).intValue()].manage eq true)
                              || (sessionBean.pmap[(44).intValue()].view eq true || sessionBean.pmap[(44).intValue()].manage eq true)
                              || (sessionBean.pmap[(45).intValue()].view eq true || sessionBean.pmap[(45).intValue()].manage eq true)
                              || (sessionBean.pmap[(46).intValue()].view eq true || sessionBean.pmap[(46).intValue()].manage eq true)
                              || (sessionBean.pmap[(47).intValue()].view eq true || sessionBean.pmap[(47).intValue()].manage eq true)}">
                      <li <%=reportTwo%> style="display: none;">
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Mgmt Reports &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(38).intValue()].view eq true || sessionBean.pmap[(38).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/pageUnderConstruction.jsp">Daily Transactions</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(15).intValue()].view eq true || sessionBean.pmap[(15).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=programsummary&title=Program Summary">Program Summary Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(39).intValue()].view eq true || sessionBean.pmap[(39).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=pharmacysummary&title=Weekly Pharmacy Summary">Weekly Pharmacy Summary Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(40).intValue()].view eq true || sessionBean.pmap[(40).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=reimbursementsummary&title=Reimbursement Quality Summary">Reimbursement Quality Summary Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(41).intValue()].view eq true || sessionBean.pmap[(41).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=reimbursementdetail&title=Reimbursement Quality Detail">Reimbursement Quality Detail Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(35).intValue()].view eq true || sessionBean.pmap[(35).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=utilization&title=Pharmacy Utilization">Pharmacy Utilization Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(51).intValue()].view eq true || sessionBean.pmap[(51).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=physicianutilization&title=Physician Utilization">Physician Utilization Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(42).intValue()].view eq true || sessionBean.pmap[(42).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=rx&title=Abandoned Rxs ">Abandoned Rxs Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(43).intValue()].view eq true || sessionBean.pmap[(43).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/reports.jsp?name=dormant&title=Dormant Patient / Rx ">Dormant Patient / Rx Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(44).intValue()].view eq true || sessionBean.pmap[(44).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/pageUnderConstruction.jsp">Compound Ingredients Report</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(45).intValue()].view eq true || sessionBean.pmap[(45).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/pageUnderConstruction.jsp">Patient CC Payments Received</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(46).intValue()].view eq true || sessionBean.pmap[(46).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/pageUnderConstruction.jsp">Pharmacy Administrative Fees</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(47).intValue()].view eq true || sessionBean.pmap[(47).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/pageUnderConstruction.jsp">Pharmacy Proceeds</a></li>
                                  </c:if>
                          </ul>
                      </li>
                </c:if>
                <c:if test="${(sessionBean.pmap[(48).intValue()].view eq true || sessionBean.pmap[(48).intValue()].manage eq true)
                              || (sessionBean.pmap[(49).intValue()].view eq true || sessionBean.pmap[(49).intValue()].manage eq true)
                              || (sessionBean.pmap[(50).intValue()].view eq true || sessionBean.pmap[(50).intValue()].manage eq true)}">
                      <li <%=reportThree%> style="display: none;">
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Program Reports &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(59).intValue()].view eq true || sessionBean.pmap[(59).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}/pages/apiprogramreports.jsp?name=flowdollar&title=Flow $ ">Flow $ Report</a></li> 
                                  </c:if>
                              <li id="apiProgramReports" class="dropdown-submenu">
                                  <a href="#">Most Active Practices Reports &nbsp;</a>
                                  <ul id="mostActiveReports" class="dropdown-menu">
                                      <c:if test="${(sessionBean.pmap[(48).intValue()].view eq true || sessionBean.pmap[(48).intValue()].manage eq true)}">
                                          <li class="link"><a href="${pageContext.request.contextPath}/pages/apiprogramreports.jsp?name=mostactive&title=Most Active Practices by No. of Messages Sent">By # of Msg Sent</a></li> 
                                          </c:if>
                                          <c:if test="${(sessionBean.pmap[(49).intValue()].view eq true || sessionBean.pmap[(49).intValue()].manage eq true)}">
                                          <li class="link"><a href="${pageContext.request.contextPath}/pages/apiprogramreports.jsp?name=mostactive&title=Most Active Practices by Patient's Satisfaction">By Patient's Satisfaction</a></li>
                                          </c:if>
                                          <c:if test="${(sessionBean.pmap[(50).intValue()].view eq true || sessionBean.pmap[(50).intValue()].manage eq true)}">
                                          <li class="link"><a href="${pageContext.request.contextPath}/pages/apiprogramreports.jsp?name=mostactive&title=Most Active Practices by Zip Code">By Practice Zip Code</a></li> 
                                          </c:if>
                                  </ul>
                              </li>
                          </ul>
                      </li>
                </c:if>
            </ul>
        </nav>
    </div>

</header>  
