<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./portal/inc/head2.jsp" />

    <body>
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


 <div class="container-fluid paddBot">
  <c:choose>
        <c:when test="${sessionScope.sessionBeanPortal!=null && sessionScope.sessionBeanPortal.userNameDB eq 'PharmacyPortal'}">
        <div class="row">
            <div class="head_top">
                <div class="wrapper clearfix">
                    <div class="col-sm-5 logo">
                        <h1><a href="#"><img src="${pageContext.request.contextPath}/resources/images/rx_logo.png" alt="" /></a>
                        <span>Rx-Direct - Home Delivery</span>
                        </h1>
                    </div>
                    <div class="col-sm-7 rightHead">
                        <h2>Transfer RX List</h2>
                        <div class="loginDiv">
                            <a href="#" class="buttonss"><i class="fa fa-user"></i>
                                Welcome to <span>${sessionBeanPortal.currentUserName}! <i class="fa fa-chevron-down"></i></span></a>
                            <div class="logDrop">
                                <ul>
                                    <li><a href="#" onclick="logout();">Logout</a></li>
                                </ul>
                            </div>
                        </div>

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
         </c:otherwise>
    </c:choose>
      
 
    <!--Registration Page-->
        <!--Registration Page-->
            <div class="row">
                <div class="wrapper ">
                    <div class="userBanner clearfix">
                        <div class="clearfix">
                           <ul>
                            <li >NICHOLAS K. (M)</li>
                            <li >11/16/1977 (29 Years)</li>
                            <li >Member Since 01/2016</li>
                            <li ><a href="#"> 2 Dependents</a></li>
                            <li >4 Program Rx’s</li>
                            </ul>
                            <p>Tuesday, January 29,  2016 11:41 CST</p>
                        </div>
                    </div>
                    <div class="customTableRow">
                        <div class="col-md-3">
                            <div class="heightList clearfix">
                                <div>
                                    <h4>Height (ft/inch)</h4>
                                    <p>6 ft</p>
                                    <p>5 in</p>
                                </div>
                                <div>
                                <h4>Weight (lbs)</h4>
                                    <p>215</p>
                                </div>
                                <div>
                                    <h4>BMI (%)</h4>
                                    <p>20.2</p>
                                </div>
                                <a href="#"><i class="fa fa-pencil"></i></a>
                            </div>
                            <div class="contactContainer">
                                <p><span class="blueText">Phone #.</span> 734-564-4266</p>
                                <p><span class="blueText">Backup Phone #.</span> 734-564-4266</p>
                                <p><span class="blueText">Email</span> sample@email.com</p>
                                <p><span class="blueText">Allergies <a href="#"><i class="fa fa-pencil"></i></a></span><br />  Penicillin, Peanuts, Mango</p>
                            </div>
                            <div class="shippingContainer">
                                <ol type="1">
                                    <li>
                                        <span class="blueText">Shipping Address</span>
                                        <a href="#"><i class="fa fa-pencil"></i></a>
                                        <br />
                                        <strong>"Nick Home"</strong>
                                        <br />
                                        <span>16320 E Hardwick Place APT 7-B SOUTHLAKE TX 76092</span>
                                    </li>
                                    <li>
                                        <span class="blueText">Shipping Address</span>
                                        <a href="#"><i class="fa fa-pencil"></i></a>
                                        <br />
                                        <strong>"Nick Home"</strong>
                                        <br />
                                        <span>16320 E Hardwick Place APT 7-B SOUTHLAKE TX 76092</span>
                                    </li>
                                    <li>
                                        <span class="blueText">Shipping Address</span>
                                        <a href="#"><i class="fa fa-pencil"></i></a>
                                        <br />
                                        <strong>"Nick Home"</strong>
                                        <br />
                                        <span>16320 E Hardwick Place APT 7-B SOUTHLAKE TX 76092</span>
                                    </li>
                                </ol>
                            </div>
                               <div class="insuranceContainer">
                                <span class="blueText">Insurance Card</span>
                                <span><a href="#" class="greenText">View Detail</a></span>
                                <div>
                                    <img src="images/insuranceCard_15.jpg" alt="insurance card front" />
                                    <img src="images/insuranceCard_17.jpg" alt="insurance card back" />
                                </div>
                            </div>
                            <form action="" class="messageForm">
                                <textarea name="message" class="messageBox" >Send Message to Patient</textarea>
                                <button type="submit"><i class="fa fa-paper-plane greenText" ></i></button>
                            </form>
                            <a href="#" class="greenText messageHistory">View Message History</a>
                        </div>
                        <div class="col-md-9">

                            <div class="tableContainer rxListTable">

                                <table class="table-striped">
                                    <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Medication</th>
                                            <th>Qty</th>
                                            <th>Pharmacy Name</th>
                                            <th>Pharmacy Phone</th>
                                            <th>Rx Number</th>
                                            <th>Quoted</th>
                                            <th>PT Video</th>
                                            <th>Status </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><a href="#">01/29/2016 @ 03:17 PM</a></td>
                                            <td>PROPRANOLOL 20MG</td>
                                            <td>90</td>
                                            <td>SAMS Pharmacy</td>
                                            <td>806-653-6502</td>
                                            <td>H5689655</td>
                                            <td>$ 9.44</td>
                                            <td><a href="#"><i class="fa fa-play"></i></a></td>
                                            <td class="orangeText">Pending</td>
                                        </tr>
                                        <tr>
                                        <td><a href="#">01/29/2016 @ 03:17 PM</a></td>
                                        <td>PROPRANOLOL 20MG</td>
                                        <td>90</td>
                                        <td>SAMS Pharmacy</td>
                                        <td>806-653-6502</td>
                                        <td>H5689655</td>
                                        <td>$ 9.44</td>
                                            <td><a href="#"><i class="fa fa-play"></i></a></td>
                                        <td class="greenText">Transfered</td>
                                        </tr>

                                    </tbody>
                                </table>
                                <nav aria-label="Page navigation">
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
                                </nav>

                            </div>

                        </div>
                    </div>







                </div>


            </div>
            <!--Registration Page-->
        <!--Registration Page-->
           
               
     
  
        <jsp:include page="./portal/inc/footer.jsp" />
     </div>
    <!-- Modal Status -->
    <div id="statusModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">STATUses</h4>
                </div>
                <div class="modal-body">
                    <h6>OPEN BY OTHER USER</h6>
                    <p>Prescription is currently open by another user and cannot be viewed.</p>
                    <h6>Pending</h6>
                    <p>Prescription has not been viewed by any user.</p>
                </div>
            </div>

        </div>
    </div>
    <!--/ Modal Status -->

    <!-- Modal Register -->
    <!--/ Modal Register -->
      <!-- jQuery -->
    
    
   <script>

   $(document).ready(function( ) {
	   $('#example').dataTable({"sPaginationType": "full_numbers"});
   }); 
           $(function () {
            var pull = $('#pull');
            menu = $('.Menu ul');
            menuHeight = menu.height();

            $(pull).on('click', function (e) {
                e.preventDefault();
                menu.slideToggle();
            });

            $(window).resize(function () {
                var w = $(window).width();
                if (w > 320 && menu.is(':hidden')) {
                    menu.removeAttr('style');
                }
            });
        });


        $(document).ready(function(e) {
            $(".buttonss").click(function(){

                $(".logDrop").slideToggle();
            })
        }); 
          var currdate = new Date();
        var time= (currdate.getMonth()+ 1) + '/' + currdate.getDate() + '/' + currdate.getFullYear()+'  '+(currdate.getHours() + ":"+ currdate.getMinutes() + ": " + currdate.getSeconds());
       // var formatted = $.datepicker.formatDate("DD M d, yy", new Date());
		    document.getElementById("dt").innerHTML=time; 
		   
    </script>
</body>
</html>

