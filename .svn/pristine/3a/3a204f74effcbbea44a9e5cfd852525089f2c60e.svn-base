<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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


<div class="container-fluid headerthree">
    <c:choose>
        <c:when test="${sessionScope.sessionBeanPortal!=null && sessionScope.sessionBeanPortal.userNameDB eq 'PharmacyPortal'}">
            <div class="row">
                <div class="head_top">
                    <div class="wrapper clearfix">
                        <div class="col-sm-2 logo">
                            <h1><a href="${pageContext.request.contextPath}/PharmacyPortal/newMemberRequest"><img src="${pageContext.request.contextPath}/resources/images/compliance-reward-logo.png" alt="" height="42" /></a>
                                <span>Compliance Reward - Home Delivery</span>
                            </h1>
                        </div>
                           <div class="col-sm-4 center-section padd-zero">
                               <div class="inner-dtail">
                                   <a href="javascript:window.pharmacyNotification.openSamePrefRxModel('deliveryPrefModal',${order.orderId},'${fn:toUpperCase(order.patientName)}',${patientId})">
                                   <span style="font-weight: 700;">DELIVERY SVC: </span>
                                    <c:choose>
                                         <c:when test="${order.deliveryService eq 'Same Day'}">
                                            <h5 class="redText blink_me">${order.deliveryService} Delivery</h5>
                                         </c:when>
                                         <c:when test="${order.deliveryService eq 'Next Day*'}">
                                            <h5 style="color:blue">${order.deliveryService}</h5>
                                         </c:when>
                                         <c:otherwise>
                                             <h5 >${order.deliveryService}</h5> 
                                         </c:otherwise>
                                    </c:choose>
                                   </a>
                               </div>
                                <div class="inner-dtail">
                                   <span>Multi Rx </span>
                                   <c:choose>
                                       <c:when test="${order.multiRx eq true}">
                                        <c:choose>
                                        <c:when test="${order.status eq 'DELIVERY' || 
                                              order.status eq 'Pickup At Pharmacy'  ||
                                              order.status eq 'Shipped'}">
                                                <a href=
                                                      "javascript:window.pharmacyNotification.openShippingModel(
                                                      'multiRxModal',${order.orderId},'${fn:toUpperCase(order.patientName)}',${patientId})">   <h5 class="redText" >${order.multiRxLabel}</h5></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href=
                                                      "javascript:window.pharmacyNotification.openMultiRxModel(
                                                      'multiRxModal',${order.orderId},'${fn:toUpperCase(order.patientName)}',${patientId})">   <h5 class="redText" >${order.multiRxLabel}</h5></a>
                                        </c:otherwise>
                                        </c:choose>
                                        </c:when>
                                       <c:otherwise>
                                        <!---------------------------------------->
                                         <c:choose>
                                        <c:when test="${order.status eq 'DELIVERY' || 
                                              order.status eq 'Pickup At Pharmacy'  ||
                                              order.status eq 'Shipped'}">
                                                <a href=
                                                      "javascript:window.pharmacyNotification.openShippingModel(
                                                      'multiRxModal',${order.orderId},'${fn:toUpperCase(order.patientName)}',${patientId})">   <h5 class="redText" >${order.multiRxLabel}</h5></a>
                                        </c:when>
                                        <c:otherwise>
                                        <!---------------------------------------->
                                        <h5 >${order.multiRxLabel}</h5>   
                                        </c:otherwise>
                                        </c:choose>
                                       </c:otherwise>
                                   </c:choose>
                               </div>
                               </div>
<!--                               <div class="inner-dtail">
                                   <span>Rx Number:</span>
                                   <h5>RXD-${order.orderId}</h5>
                               </div>
                                <div class="inner-dtail">
                                   <span>Date of Service:</span>
                                   <fmt:formatDate var="receivedDateLabel" value='${order.receivedDate}' pattern='yyyy-MM-dd'/>
                                   <h5>${receivedDateLabel}</h5>
                               </div>
                                <div class="inner-dtail">
                                   <span>Rx Status:</span>
                                   <h5>${order.status}</h5>
                               </div>
                                <div class="inner-dtail">
                                   <span>Rx EXP Date:</span>
                                    <fmt:formatDate var="expDate" value='${order.rxExpiredDate}' pattern='yyyy-MM-dd'/>
                                    <h5>${expDate}</h5>
                               </div>
                                <div class="inner-dtail">
                                   <span>Next Refill:</span>
                                   <fmt:formatDate var="nextRefillDate" value='${order.nextRefillDate}' pattern='yyyy-MM-dd'/>
                                   <h5>${nextRefillDate}</h5>
                               </div>-->
                                   <div class="col-sm-4 padd-zero button_div">
                                        <button data-toggle="modal" data-target="#Drug_ind_multi" class="btn-multiprice" >
                                                <i class="fa fa-usd" aria-hidden="true"></i> Multi Rx Price
                                                 </button>
                                    <button data-toggle="modal" data-target="#Drug_ind" class="btn-estimate" >
                            <i class="fa fa-usd" aria-hidden="true"></i> Estimate Price
                             </button>
                             <button onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/newOnlineRxDetail/${patientId}/${order.dependentId}'"  class="btn-save" >
                            <i class="fa fa-plus-circle" aria-hidden="true"></i> Add New RX
                             </button>                                
                              </div>
                            
                            <div class="dropdown col-sm-2 rightHead loginDiv">
                                <button class="btn dropdown-toggle" type="button" data-toggle="dropdown"><i class="fa fa-user"></i> Welcome to<span> ${sessionBeanPortal.currentUserName}! <i class="fa fa-chevron-down"></i></span>
                                </button>
                                <ul class="dropdown-menu logDrop">
                                    <li><a href="#!" onclick="logout();">Logout</a></li>
                                </ul>
                            </div>
                            <!--<div class="loginDiv">
                                 <a href="#" class="buttonss"><i class="fa fa-user"></i>
                                     Welcome to <span>${sessionBeanPortal.currentUserName}! <i class="fa fa-chevron-down"></i></span></a>
                                 <div class="logDrop">
                                     <ul>
                                         <li><a href="#!" onclick="logout();">Logout</a></li>
                                     </ul>
                                 </div>
                             </div>--> 

                        </div>
                    </div>
                </div>
            </div>
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
                window.setInterval(CheckIdleTime, 3600000);

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
        </c:otherwise>
    </c:choose>

    <%-- <div class="hearder">
        
        

  
    <c:choose>
        <c:when test="${sessionScope.sessionBeanPortal!=null && sessionScope.sessionBeanPortal.userNameDB eq 'PharmacyPortal'}">
            <div id="top" class="container" style=" padding-bottom: 0;">
                <div class="row">
                    <div class="wrapper clearfix">
        <div class="col-sm-5 logo">
          <h1><a href="#"><img src="${pageContext.request.contextPath}/resources/images/rx_logo.png" alt="" /></a></h1>
        </div>
        <div class="col-sm-7 main_nav">
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
  <div class="container-fluid">
  <div class="row">
    <div class="head_top">
      <div class="wrapper clearfix">
        <div class="col-sm-5 logo">
          <h1><a href="#"><img src="${pageContext.request.contextPath}/resources/images/rx_logo.png" alt="" /></a></h1>
        </div>
        <div class="col-sm-7 main_nav">
          <div class="Menu"> <a href="#" id="pull">Menu</a>
            <ul>
              <li><a href="#">PATIENTS</a></li>
              <li><a href="#">PROVIDERS</a></li>
             <li><a href="#" data-toggle="modal" data-target="#login">PHARMACIES</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
        </c:otherwise>
    </c:choose>
</div>
    --%>