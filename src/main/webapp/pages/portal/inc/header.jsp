<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String url = request.getRequestURL().toString();
    String index = "", aboutUs = "", document = "", contact = "", login = "", createAccount = "", prescription = "", ingredient = "", accountSummary = "", profile = "";
    if (url.contains("index") || url.contains("logout") || url.contains("home")) {
        index = "class='menu_selected'";
    }
    if (url.contains("aboutUs")) {
        aboutUs = "class='menu_selected'";
    }
    if (url.contains("documents")) {
        document = "class='menu_selected'";
    }
    if (url.contains("contact")) {
        contact = "class='menu_selected'";
    }
    if (url.contains("login")) {
        login = "class='menu_selected btn btn-primary dropdown-toggle'";
    }
    if (url.contains("registration1") || url.contains("registration2")) {
        login = "class='menu_selected btn btn-primary dropdown-toggle'";
    }
    if (url.contains("prescription")) {
        prescription = "class='menu_selected'";
    }
    if (url.contains("ingredients")) {
        ingredient = "class='menu_selected'";
    }
    if (url.contains("accountsummary")) {
        accountSummary = "class='menu_selected'";
    }
    if (url.contains("profile") || url.contains("changepassword")) {
        profile = "class='menu_selected'";
    }
%>





<c:choose>
    <c:when test="${sessionScope.sessionBeanPortal!=null && sessionScope.sessionBeanPortal.userNameDB eq 'PharmacyPortal'}">
        <div id="top" class="container" style=" padding-bottom: 0;">
            <div class="row">
                <div class="wrapper clearfix">
                    <div class="col-sm-5 logo">
                        <h1 ><a href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest"><img src="${pageContext.request.contextPath}/resources/images/compliance-reward-logo.png" alt="" /></a></h1>
                    </div>
                    <div class="col-sm-7 main_nav" >
                        <div class="Menu"> <a href="#" id="pull">Menu</a>
                            <ul>
                                <li><a href="#">PATIENTS</a></li>
                                <li><a href="#">PROVIDERS</a></li>
                                <li><a href="#" data-toggle="modal" data-target="#login">PHARMACIES</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 headertitle" style="line-height: 5px;">
                    <!--                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="white-space: nowrap;">
                                                <h3 class="pharmacyName">${sessionBeanPortal.pharmacy.title} <b style="font-size: 14px;">(NPI: ${sessionBeanPortal.pharmacy.npi})</b></h3>
                                                <div class="pharmacyAddress">${sessionBeanPortal.pharmacy.address1}, ${sessionBeanPortal.pharmacy.city}, ${sessionBeanPortal.pharmacy.state.abbr} ${sessionBeanPortal.pharmacy.zip}, Ph: ${sessionBeanPortal.pharmacy.phone}</div>
                                            </div>-->
                    <div class="hidden-lg col-md-12 col-sm-12 col-xs-12" style="text-align: center; padding-top: 5px;">

                        Welcome, <b style="color: #1f4c76;">${sessionBeanPortal.currentUserName}!</b>
                        <br/>
                        <i style="font-size: 11px;">Last Logged on ${sessionBeanPortal.lastLoggedOn} ETD</i>

                    </div>
                </div>
                <div id="login" class="col-lg-4 col-md-3 col-sm-3 hidden-xs lastlogintext" style="padding-right: 0; text-align: right;float: right;padding-top: 30px;">
                    <table style="float: right;">
                        <tbody>
                            <tr>
                                <td class="hidden-md hidden-sm hidden-xs">

                                    Welcome, <b style="color: #1f4c76;">${sessionBeanPortal.currentUserName}!</b>
                                    <br/>
                                    <i style="font-size: 11px;" class="logininfo">Last Logged on ${sessionBeanPortal.lastLoggedOn} ETD</i>

                                </td>
                                <td>
                                    &nbsp;&nbsp;
                                    <a class="btn btn-primary hidden-xs1" onclick="logout();" href="#">
                                        <i class="fa fa-power-off" style="padding-right: 5px;font-size:20px;"></i>Logout
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div id="logoutDialog"></div>            
        <input type="hidden" id="timeout" value="<%= session.getMaxInactiveInterval()%>"/>  
        <script>
            function logout() {
                logoutConfirm();
//                    var dialog = $("#logoutDialog").dialog({
//                        closeOnEscape: false,
//                        autoOpen: false,
//                        modal: true,
//                        width: '100%',
//                        open: function (event, ui) {
//                            //hide title bar.
//                            $(".ui-dialog-titlebar").hide();
//                        }
//                    });
//
//                    var logoutHtml = "<div style='text-align: center; margin-top: 20px;'>"
//                            + "<p style='margin-bottom: 20px;'>Are you sure you want to logout?</p>"
//                            + "<div style='float: left; width: 49%; text-align: right; font-size: 14px;'>"
//                            + "<button id='confirmBtn' style='width: 120px;' class='btn btn-primary' type='button' onclick='logoutConfirm();'>YES</button>"
//                            + "</div>"
//                            + "<div style='float: right; width: 49%; text-align: left; margin-bottom: 20px; font-size: 14px;'>"
//                            + "<button style='width: 120px;' class='btn btn-primary' type='button' onclick='logoutCancel();'>NO</button>"
//                            + "</div>"
//                            + "</div>";
//                    $(dialog).html(logoutHtml);
//                    $(dialog).dialog("open");
//                    $("#confirmBtn").blur();
            }

            function logoutConfirm() {
                window.location = "${pageContext.request.contextPath}/PharmacyPortal/logout";
            }

            function logoutCancel() {
                $("#logoutDialog").dialog("close");
            }

            var IDLE_TIMEOUT = document.getElementById("timeout").value; //seconds
            var _idleSecondsCounter = 0;
            document.onclick = function () {
                _idleSecondsCounter = 0;
            };
            document.onmousemove = function () {
                _idleSecondsCounter = 0;
            };
            document.onkeypress = function () {
                _idleSecondsCounter = 0;
            };
            window.setInterval(CheckIdleTime, 1000);

            function CheckIdleTime() {
                _idleSecondsCounter++;
                if (_idleSecondsCounter >= IDLE_TIMEOUT) {
                    var dialog = $("#logoutDialog").dialog({
                        closeOnEscape: false,
                        autoOpen: false,
                        modal: true,
                        width: '100%',
                        title: "SESSION EXPIRED",
                        open: function (event, ui) {
                            //hide title bar.
                            $(".ui-dialog-titlebar").hide();
                        }
                    });

                    var logoutHtml = "<div style='text-align: center; margin-top: 20px;'>"
                            + "<p style='margin-bottom: 20px;'>You have been logged out automatically due to inactivity.</p>"
                            + "<div style='width: 100%; font-size: 14px;'>"
                            + "<button style='width: 120px;' class='btn btn-primary' type='button' onclick='logoutConfirm();'>Continue</button>"
                            + "</div>"
                            + "</div>";
                    $(dialog).html(logoutHtml);
                    $(dialog).dialog("open");
                }
            }
        </script>               
    </c:when>
    <c:otherwise>

        <div class="row">
            <div class="head_top">
                <div class="wrapper clearfix">
        <div class="col-sm-3 logo">
                        <h1 style="border-bottom: none;padding-left: 2px;"><a href="#"><img src="${pageContext.request.contextPath}/resources/images/compliance-reward-logo.png" alt="" /></a></h1>
                    </div>
                    <div class="col-sm-7 registration_head1">
                        <h4>${pharmacy.pharmacyLookup.title}</h4>
                        <p>${pharmacy.pharmacyLookup.npi},${pharmacy.pharmacyLookup.passNumber},${pharmacy.pharmacyLookup.pharmacyType.title}
                            ${pharmacy.pharmacyLookup.address}, ${pharmacy.pharmacyLookup.city}, ${pharmacy.pharmacyLookup.state.abbr} ${pharmacy.pharmacyLookup.zip}
                            <span>Pharmacy Phone: ${pharmacy.phone}</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>













    </c:otherwise>
</c:choose>

