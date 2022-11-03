<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">

    <body onload="startTimer()">

        <c:redirect url="${pageContext.request.contextPath}/PharmacyPortal/login"/>
        <div class="row banner"> <img src="${pageContext.request.contextPath}/resources/images/banner.jpg" alt="" width="100%" /> </div>
        <!--Home Widgets-->
        <div class="mini_wrapper home_content clearfix">
            <h2>Pay as little as $0 for your generic medications, delivered</h2>

            <!--Widget-->
            <div class="col-sm-6">
                <div class="home_widget"> <img src="${pageContext.request.contextPath}/resources/images/home_widget01.png" alt="" />
                    <h5>Pay as Little As $0*</h5>
                    <p>We purchase direct and pass the savings on to you!</p>
                </div>
            </div>
            <!--/Widget--> 

            <!--Widget-->
            <div class="col-sm-6">
                <div class="home_widget"> <img src="${pageContext.request.contextPath}/resources/images/home_widget01.png" alt="" />
                    <h5>Compliance Rewards&trade;</h5>
                    <p>Order Generic medications and earn reward points usable for future purchases form the Rx-DeirectTM  store!</p>
                </div>
            </div>
            <!--/Widget--> 

            <!--Widget-->
            <div class="col-sm-6">
                <div class="home_widget"> <img src="images/home_widget01.png" alt="" />
                    <h5>Convenient home delivery</h5>
                    <p>Why wait at the pharmacy when your medications can be delivered straight to your door - for less!</p>
                </div>
            </div>
            <!--/Widget--> 

            <!--Widget-->
            <div class="col-sm-6">
                <div class="home_widget"> <img src="images/home_widget01.png" alt="" />
                    <h5>Simple & Easy to Use</h5>
                    <p>Install the app. Create your free account, And start saving on your gereric medications. It’s that easy.</p>
                </div>
            </div>
            <!--/Widget--> 

        </div>


        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.min.js"></script>
        <script type="text/javascript">
        function isNumber(evt) {
            evt = (evt) ? evt : window.event;
            var charCode = (evt.which) ? evt.which : evt.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                return false;
            }
            return true;
        }
        $(document).ready(function () {
            $('.nav-tabs > li > a').click(function (event) {
                event.preventDefault(); //stop browser to take action for clicked anchor

                //get displaying tab content jQuery selector
                var active_tab_selector = $('.nav-tabs > li.active > a').attr('href');
                //find actived navigation and remove 'active' css
                var actived_nav = $('.nav-tabs > li.active');
                actived_nav.removeClass('active');
                //add 'active' css into clicked navigation
                $(this).parents('li').addClass('active');
                //hide displaying tab content
                $(active_tab_selector).removeClass('active');
                $(active_tab_selector).addClass('hide');
                //show target tab content
                var target_tab_selector = $(this).attr('href');
                if (target_tab_selector === '#emailContainer') {
                    $('#communicationMethod').val("email");

                    $('#phoneValidMsg').text("Please enter your valid email & phone number in the box above and click submit.**");

                    $('#emailValidMsg').attr('style', 'display:inline;font-size: 11px;padding-left: 0px;position: relative;bottom: 18px;');
                    $('#emailrate').attr('style', 'display:inline;padding-left: 0px;position: relative;bottom: 10px;');
                } else {
                    $('#communicationMethod').val("text");
                    $('#phoneValidMsg').text("Please enter your 10-digit mobile phone number in the box above and click submit.**");
                    $('#emailValidMsg').attr('style', 'display:none;');
                }
                $(target_tab_selector).removeClass('hide');
                $(target_tab_selector).addClass('active');

            });
            if (window.screen.width > 1366) {
                $("#message1").attr('class', 'indextopmessgae1920');
                $("#message2").attr('class', 'indextopmessage21920');
                $("#fieldContainer").attr('class', 'fieldContainer1920');
                $("#formContainer").attr('class', 'formContainer1920');
//                                                            $("#phoneContainer > div").attr('style', 'padding-top: 43px;');
                $("#email").attr('class', 'emailtab col-lg-12');
                $("#phoneId").attr('class', 'phonetab col-lg-12');
                $("#phoneValidMsg").attr('style', 'font-size: 18px;padding-left: 0px;top: 5px;');
                $("#msgrates").attr('style', 'padding-left: 0px;top:0.9vw;');
                $("#msgrates > label").attr('class', 'indexMessage1920');
                $("#errorMessage").attr('class', 'indexerrormsg1920');
            } else {
                $("#message1").attr('class', 'indextopmessgae');
                $("#message2").attr('class', 'indextopmessage2');
                $("#fieldContainer").attr('class', 'fieldContainer indextxtfield');
                $("#formContainer").attr('class', 'formContainer');
                $("#phoneContainer > div").attr('style', 'padding-top: 27px;');
                $("#email").attr('style', 'padding: 5px 10px;');
                $("#email").attr('class', 'col-lg-12');
//                                                        $("#phoneValidMsg").attr('style', 'font-size: 11px;padding-left: 0px;');
                $("#msgrates").attr('style', 'padding-left: 0px;');
                $("#msgrates > label").attr('class', 'indexMessage');
                $("#errorMessage").attr('class', 'indexerrormsg');
                $("#phoneNo").attr('class', 'form-control1 indexfontsize');
                $("#txtemail").attr('class', 'form-control1 indexfontsize');
                $("#memberId").attr('class', 'form-control1 indexfontsize');
            }
            $('#communicationMethod').val("text");
            var materialcnt = document.getElementById('material').clientHeight;
            var patientcnt = document.getElementById('patient').clientHeight;
            var pharmh = document.getElementById('pharmacies').clientHeight;
            var hcph = document.getElementById('hcp').clientHeight;
            if (materialcnt > patientcnt) {
                document.getElementById('patient').style.height = materialcnt + 'px';
            } else if (patientcnt > materialcnt) {
                document.getElementById('material').style.height = patientcnt + 'px';
            }
            if (pharmh > hcph) {
                document.getElementById('hcp').style.height = pharmh + 'px';
            } else if (hcph > pharmh) {
                document.getElementById('pharmh').style.height = hcph + 'px';
            }
            $("#phoneNo").mask("(999)999-9999");
            $("#memberId").mask("(999)999-9999");
        });
        function validateField() {
            if ($('#communicationMethod').val() === 'text') {
                if ($("#phoneNo").val() === "") {
                    $('#errorMessage').attr('style', 'display: inline;');
                    $("#errorMessage").text("Phone# required.");
                    return false;
                }
                return true;
            } else if ($('#communicationMethod').val() === 'email') {
                if ($("#txtemail").val() === "") {
                    $('#errorMessage').attr('style', 'display: inline;');
                    $("#errorMessage").text("e-mail required.");
                    return false;
                }

                if ($("#memberId").val() === "") {
                    $('#errorMessage').attr('style', 'display: inline;');
                    $("#errorMessage").text("Phone# required.");
                    return false;
                }
                if ($("#txtemail").val() !== "") {
                    var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
                    if (!pattern.test(document.getElementById("txtemail").value)) {
                        $('#errorMessage').attr('style', 'display: inline;');
                        $("#errorMessage").text("Invalid email address.");
                        return false;
                    }
                }
                return true;
            }
        }
        function startTimer() {
            window.setInterval("hideMessage()", 5000);
        }
        function hideMessage() {
            document.getElementById("errorMessage").style.display = "none";
        }
        function optIn() {
            var dataValue;
            if ($('#communicationMethod').val() === 'text') {
                dataValue = 'shortCode=' + $("#shortCode").val() + '&eventName=' + $("#eventName").val() + '&userId=' + $("#userId").val() + '&password=' + $("#password").val() + '&ctrigger=' + $("#ctrigger").val() + '&communicationMethod=' + $("#communicationMethod").val() + '&communicationId=' + $("#phoneNo").val();
            } else if ($('#communicationMethod').val() === 'email') {
                dataValue = 'shortCode=' + $("#shortCode").val() + '&eventName=' + $("#eventName").val() + '&userId=' + $("#userId").val() + '&password=' + $("#password").val() + '&ctrigger=' + $("#ctrigger").val() + '&communicationMethod=' + $("#communicationMethod").val() + '&communicationId=' + $("#txtemail").val() + '&memberId=' + $('#memberId').val();
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/ConsumerPortal/optIn", type: "POST",
                data: dataValue,
                success: function (data) {
                    if (data === "Success" && $('#communicationMethod').val() === 'text') {
                        $("#btnContainer").attr('style', 'display: none;');
                        $("#messageContainer").attr('style', 'display: block;');
                        $("#radioButtonContainer").attr('style', 'display: none;');
                        $("#fieldContainer").attr('style', 'display: none;');
                        $("#textMsg").attr('style', 'display: block;');
                        $("#message2").attr('class', 'optinindextopmessage2');
                    } else if (data === "Success" && $('#communicationMethod').val() === 'email') {
                        $("#btnContainer").attr('style', 'display: none;');
                        $("#messageContainer").attr('style', 'display: block;');
                        $("#radioButtonContainer").attr('style', 'display: none;');
                        $("#fieldContainer").attr('style', 'display: none;');
                        $("#emailMsg").attr('style', 'display: block;');
                        $("#message2").attr('class', 'optinindextopmessage2');
                    } else {
                        $('#errorMessage').attr('style', 'display: inline;');
                        $("#errorMessage").text("Invalid phone#.");
                    }
                },
                error: function (e) {
                    alert('Error while request...' + e);
                }
            });
        }
        </script>
    </body>
</html>

