<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.ssa.cms.util.PropertiesUtil"%>
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
    if (url.contains("drugBrand") || url.contains("druglist") || url.contains("adddrug") || url.contains("widget") || url.contains("feesetup.jsp") || url.contains("deliverypreferences.jsp")
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
    if (url.contains("patientNoOrderReport") || url.contains("surveyReports") ||
            url.contains("inAppReport") || url.contains("report") || url.contains("patientBasicReport.jsp") || url.contains("patientEnhancedReport.jsp")) {
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
               margin-right: 12px;" class="hidden-lg hidden-md hidden-sm pull-right" href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}logout"><i class="fa fa-power-off"></i> Logout</a>
        </div>
        <nav role="navigation" class="collapse navbar-collapse bs-navbar-collapse">
            <ul class="nav navbar-nav">
                <li <%=home%>style="display: none;">
                    <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}dashboard">Dashboard</a>
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
                      <li <%=setup%> style="display: none;">
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Program Setup &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <li>
                                  <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}feeSettings/load">Fee Setup &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}rewardPoints/load">Points Setup &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}deliveryDsitances/add">Shipping Distance &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}shippingMiles/list">Shipping Miles &nbsp;</a>
                              </li>
                              <li>
                                  <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}drugAdditionalMargin/list">Drug Margin Setup &nbsp;</a>
                              </li>
                              <c:if test="${(sessionBean.pmap[(8).intValue()].view eq true || sessionBean.pmap[(8).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li style="display: none;"><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}drugBrand/list">Compound Setup</a></li>

                              </c:if>

                              <c:if test="${(sessionBean.pmap[(14).intValue()].view eq true || sessionBean.pmap[(14).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}smtp/list">SMTP Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(52).intValue()].view eq true || sessionBean.pmap[(52).intValue()].manage eq true)}">
                                  <li>
                                      <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}CmsPageContent/add">Contents Setup &nbsp;</a>
                                  </li>
                                  <li>
                                      <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}email/add">Emails Setup &nbsp;</a>
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
                              || (sessionBean.pmap[(70).intValue()].view eq true || sessionBean.pmap[(70).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(14).intValue()].view eq true || sessionBean.pmap[(14).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(27).intValue()].view eq true || sessionBean.pmap[(27).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(52).intValue()].view eq true || sessionBean.pmap[(52).intValue()].manage eq true)}">
                      <li <%=setup%> style="display: none;">
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Drug Setup &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <li>
                                  <!--
                                  <a href="${pageContext.request.contextPath}/drugBrand/list/category">Drug Setup &nbsp;</a>-->
                                  <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}drugBrand/list/drugdata">Drug Setup &nbsp;</a>
                              </li>

                          </ul>
                      </li>
                </c:if>

                <c:if test="${(sessionBean.pmap[(71).intValue()].view eq true || sessionBean.pmap[(71).intValue()].manage eq true) || (sessionBean.pmap[(72).intValue()].view eq true || sessionBean.pmap[(72).intValue()].manage eq true) || (sessionBean.pmap[(73).intValue()].view eq true
                              || sessionBean.pmap[(73).intValue()].manage eq true) || (sessionBean.pmap[(74).intValue()].view eq true || sessionBean.pmap[(74).intValue()].manage eq true)
                              || (sessionBean.pmap[(75).intValue()].manage eq true) || (sessionBean.pmap[(75).intValue()].view eq true) || (sessionBean.pmap[(76).intValue()].manage eq true) || (sessionBean.pmap[(76).intValue()].view eq true)}">
                      <li <%=democampaign%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Msging Setup &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(71).intValue()].view eq true || sessionBean.pmap[(71).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}response/list">Response Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(72).intValue()].view eq true || sessionBean.pmap[(72).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}intervalsType/list">Interval Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(73).intValue()].view eq true || sessionBean.pmap[(73).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}event/list">Event Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(74).intValue()].view eq true || sessionBean.pmap[(74).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}folder/list">Folder Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(75).intValue()].view eq true || sessionBean.pmap[(75).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}messageType/list">Msg Type Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(76).intValue()].view eq true || sessionBean.pmap[(76).intValue()].manage eq true)}">
                                  <li class="link"><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}campaign/SMS/13">Msgs Setup</a></li>
                                  </c:if>

                          </ul>
                      </li>
                </c:if>
                <li <%=patient%> style="display: none;">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">Patients &nbsp;<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${(sessionBean.pmap[(57).intValue()].view eq true || sessionBean.pmap[(57).intValue()].manage eq true)}">
                            <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}patient/detail">Patient Profile</a></li>
                            </c:if>
                      <!--  <li><a href="${pageContext.request.contextPath}/rxTransfer/list">Rx Transfer Requests</a></li>-->

                    </ul>
                </li>
                <c:if test="${(sessionBean.pmap[(29).intValue()].view eq true || sessionBean.pmap[(29).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(30).intValue()].view eq true || sessionBean.pmap[(30).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(31).intValue()].view eq true || sessionBean.pmap[(31).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(32).intValue()].view eq true || sessionBean.pmap[(32).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(64).intValue()].view eq true || sessionBean.pmap[(64).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')
                              || (sessionBean.pmap[(63).intValue()].view eq true || sessionBean.pmap[(63).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">

                      <li <%=survey%> style="display: none;">
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Survey &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(29).intValue()].view eq true || sessionBean.pmap[(29).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}surveyResponseType/list">Response Type Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(30).intValue()].view eq true || sessionBean.pmap[(30).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}surveyResponse/list">Response Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(31).intValue()].view eq true || sessionBean.pmap[(31).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}surveyQuestion/list">Question Setup</a></li>
                                  </c:if>
                                  <c:if test="${(sessionBean.pmap[(33).intValue()].view eq true || sessionBean.pmap[(33).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}survey/list">Survey Setup</a></li>
                                  </c:if>
                          </ul>
                      </li>

                      <li <%=patientNoOrderReport%> style="display: none;">
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">Reports &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(63).intValue()].view eq true || sessionBean.pmap[(63).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li>
                                      <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}campaign/surveyReports">Survey Report</a>
                                  </li>
                              </c:if>
                              <c:if test="${(sessionBean.pmap[(64).intValue()].view eq true || sessionBean.pmap[(64).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}inAppReport/view">In App Notification Report</a></li>
                                  </c:if>
                              <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}campaign/patientNoOrderReport" id="rptLnkInactivePatients">Enrolled Patient No Order Report</a></li>
                              <li><a href="#" id="rptLnkFinancial">Financial Report</a></li>
                              <li><a href="#" id="rptLnkTransactional">Pharmacy Transaction Report</a></li>
                              <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}report/view">Basic Statistics Report</a></li>
                              <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}patient/patientBasicReport">Patient Basic Report</a></li>
                              <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}patient/patientEnhancedReport">Patient Enhanced Report</a></li>
                          </ul>
                      </li>



                </c:if>
                <c:if test="${(sessionBean.pmap[(61).intValue()].view eq true || sessionBean.pmap[(61).intValue()].manage eq true )
                              || (sessionBean.pmap[(68).intValue()].view eq true || sessionBean.pmap[(68).intValue()].manage eq true )
                              || (sessionBean.pmap[(69).intValue()].view eq true || sessionBean.pmap[(69).intValue()].manage eq true )}">
                      <li <%=role%>>
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle">User Administration &nbsp;<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                              <c:if test="${(sessionBean.pmap[(68).intValue()].view eq true || sessionBean.pmap[(68).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li class="link"> <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}role/list">Manage Roles</a> </li>
                                  </c:if>

                              <c:if test="${(sessionBean.pmap[(69).intValue()].view eq true || sessionBean.pmap[(69).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li class="" ><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}permission/load">Manage Role Permissions</a></li>
                                  </c:if>

                              <c:if test="${(sessionBean.pmap[(61).intValue()].view eq true || sessionBean.pmap[(61).intValue()].manage eq true || sessionBean.userNameDB eq 'admin')}">
                                  <li class="link"> <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}user/list">Manage Users</a>  </li>
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
                          <a href="#" data-toggle="dropdown" class="dropdown-toggle"> Compliance Reward Reports&nbsp;<b class="caret"></b></a>
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
                      <c:if test="${(sessionBean.pmap[(93).intValue()].view eq true || sessionBean.pmap[(93).intValue()].manage eq true)}">
                          <li class="link"><a href="${pageContext.request.contextPath}/pharmacyqueue/login">Pharmacy Enrollment</a></li>  
                          </c:if>
<!--                          <c:if test="${(sessionBean.pmap[(97).intValue()].view eq true || sessionBean.pmap[(97).intValue()].manage eq true)}">
                          <li class="link"><a href="${pageContext.request.contextPath}/patient/deleterecord">Delete Record</a></li>  -->

                          <li <%=patient%> style="margin-left:5px;">
                              <a href="#" data-toggle="dropdown" class="dropdown-toggle">Delete Patient &nbsp;<b class="caret"></b></a>
                              <ul class="dropdown-menu">
                                  <c:if test="${(sessionBean.pmap[(97).intValue()].view eq true || sessionBean.pmap[(97).intValue()].manage eq true)}">
                                      <li style="width:126px; text-align: center"><a href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}patient/deletePatientrecorByPhone">By Phone</a></li>
                                      <li style="width:126px;text-align: center"><a href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}patient/deletePatientByEmail">By Email</a></li>
                                      </c:if>
                              </ul>
                          </li>
                                      <li <%=patient%> >
                              <a href="#" data-toggle="dropdown" class="dropdown-toggle">Delete Practice &nbsp;<b class="caret"></b></a>
                              <ul class="dropdown-menu">
                                  <c:if test="${(sessionBean.pmap[(97).intValue()].view eq true || sessionBean.pmap[(97).intValue()].manage eq true)}">
                                      <li style="width:126px; text-align: center"><a href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}patient/deletePracticerecorByPhone">By Phone</a></li>
                                      <li style="width:126px; text-align: center"><a href="${PropertiesUtil.getProperty('APP_PATH_CODE')}patient/deletePracticeRecordByLicenseNo">By License</a></li>
                                  </c:if>
                                  
                              </ul>
                          </li>
                      </c:if>


            </ul>
        </nav>
    </div>

    <!-- Modal Register -->
    <div class="modal" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="vertical-alignment-helper">
            <div class="modal-dialog vertical-align-center" role="document">
                <div class="modal-content regitsre_model login_model clearfix" style="width: 670px;">
                    <div class="col-sm-12 regiter_title">
                        <h2>Register Pharmacy </h2>
                        <div style="text-align: justify">To track your patient's compounded prescription orders, lookup ingredient information, and generate financial reports, you will need to create an account. If you already have an account, you may <a href="#" onclick="window.pharmacyRegisteration.registerPharmacyLoginNowAction();" data-dismiss="modal" aria-label="Close">LOGIN NOW</a>.</div>
                        <!--<div class="errorMessage" id="errorMessagePharReg"></div><br/>-->
                        <br/>

                    </div>
                    <button type="button" class="close model_close" data-dismiss="modal" aria-label="Close" onclick="window.pharmacyRegisteration.registerPharmacyCloseAction();"></button>

                    <form:form id="pharmacyLookupFormId" commandName="pharmacyLookup" role="form" method="POST">
                        <div class="col-sm-12">
                            <div class="col-md-7 col-sm-12 licence_col clearfix">
                                <label>Pharmacy NPI <span>(as it appears on your license)</span></label>
                                <form:input class="form-control field_1" id="NPI" path="npi" placeholder="Pharmacy NPI (as on your license)" autofocus="true" maxlength="10"/>
                                <label>Site Pass Number</label>
                                <form:input class="form-control field_1" id="sitePass" path="passNumber" placeholder="Site Pass ID" maxlength="10" onkeypress="return IsAlphaNumeric(event)"/>
                                <div class="errorMessage" id="errorMessagePharReg" style="padding-bottom: 5px;"></div>
                                <button type="button" class="btn field_btn" onclick="window.pharmacyRegisteration.lookupPharmacyFn();">Next</button>
                            </div>
                            <div class="col-md-5 col-sm-12 type_col" style="padding-right: 10px;">
                                <h5>Pharmacy Type </h5>
                                <ul>
                                    <c:forEach items="${pharmacyTypeList}" var="item">
                                        <li>
                                            <input type="radio" id="${item.id}" name="pharmacyType.id" value="${item.id}">
                                            <label for="${item.id}">${item.title}</label>
                                            <div class="check"></div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>


                            <!--<div class="col-md-6 col-sm-12 type_col">
                                                                                            <h5>Pharmacy Type </h5>
                                                                                            <ul>
                                                                                                <li>
                                                                                                    <input type="radio" id="a-option" name="selector">
                                                                                                    <label for="a-option">Retail</label>
                                                                                                    <div class="check"></div>
                                                                                                </li>
                                                                                                <li>
                                                                                                    <input type="radio" id="b-option" name="selector">
                                                                                                    <label for="b-option">Compounding Specialty</label>
                                                                                                    <div class="check">
                                                                                                        <div class="inside"></div>
                                                                                                    </div>
                                                                                                </li>
                                                                                                <li>
                                                                                                    <input type="radio" id="c-option" name="selector">
                                                                                                    <label for="c-option">Public Institutional</label>
                                                                                                    <div class="check">
                                                                                                        <div class="inside"></div>
                                                                                                    </div>
                                                                                                </li>
                                                                                                <li>
                                                                                                    <input type="radio" id="d-option" name="selector">
                                                                                                    <label for="d-option">Hospital</label>
                                                                                                    <div class="check">
                                                                                                        <div class="inside"></div>
                                                                                                    </div>
                                                                                                </li>
                                                                                            </ul>
                                                                                        </div>-->


                            <div class="col-sm-12 clearfix register_b" id="pharmacyAddressDivId" style="display: none;">
                                <div  class="select_div regiter_title">
                                    <!--<label id="pharmacyAddressId" class="regiter_title" style="text-align: left;">&nbsp;</label>-->
                                    <div id="pharmacyAddressId" style="text-align: left;color: #2368b2">&nbsp;</div>
                                    <div id="pharmacyRegisteredMessage" style="color: darkgreen;text-align: left;"></div>
                                </div>
                                <input id="pharmacyLoginBtnId" type="button" name="pharmacyLogin" class="btn field_btn" value="Login" data-dismiss="modal" aria-label="Close" onclick="window.pharmacyRegisteration.registerPharmacyLoginAction();"/>
                                <input id="pharmacyRegisterBtnId" type="button" name="pharmacyRegister" class="btn field_btn" value="Register Now" data-dismiss="modal" aria-label="Close" onclick="window.pharmacyRegisteration.registerationPageShow();"/> &nbsp;&nbsp;
                                <a id="notThisId" href="#" onclick="window.pharmacyRegisteration.registerPharmacyResetAction();">NOT THIS</a>


                            </div>
                            <div class="col-sm-12" id="endSpaceDivId" >
                                &nbsp;
                            </div>
                        </div>
                    </form:form>


                </div>
            </div>
        </div>
        <jsp:include page="../portal/inc/footer.jsp" />
    </div>
    <!--/ Modal Register -->
</header>  
