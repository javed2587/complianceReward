<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page session="false" %>

<!DOCTYPE html>

<html lang="en">

    <head>



        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <meta http-equiv="x-ua-compatible" content="ie=edge">

        <title>COMPLIANCE REWARDS</title>



        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/e-transcription_favicon.png" />

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>

        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">

        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:400,400i,700" rel="stylesheet">



        <!--    <link rel="shortcut icon" href="images/" type="image/x-icon" /> -->

        <!-- Bootstrap CSS -->

        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/css/bootstrap.min.css'>

        <link href="${pageContext.request.contextPath}/resources/fontawesome4/fontawesome4/css/fontawesome.css" rel="stylesheet" type="text/css" property="stylesheet"

              />



        <link href="${pageContext.request.contextPath}/resources/css/portal/logincss/style.css" rel="stylesheet" type="text/css" property="stylesheet" />

        <link href="${pageContext.request.contextPath}/resources/css/portal/logincss/stylelogin.css" rel="stylesheet" type="text/css" property="stylesheet" />

        <link href="${pageContext.request.contextPath}/resources/css/portal/logincss/medialogin.css" rel="stylesheet" type="text/css" property="stylesheet" />

        <link href="${pageContext.request.contextPath}/resources/css/portal/logincss/timepicki.css" rel="stylesheet" type="text/css" property="stylesheet" />

        <script src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyRegisteration.js" type="text/javascript"></script>

        <!-- jQuery -->

        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/resources/js/slick.min.js" type="text/javascript"></script>



        <script type="text/javascript">

            $(window).scroll(function () {

                var scroll = $(window).scrollTop();



                if (scroll >= 100) {

                    $("body").addClass("smallhead_body");

                    $(".head_top").addClass("small_header");

                } else {

                    $("body").removeClass("smallhead_body");

                    $(".head_top").removeClass("small_header");

                }

            });

        </script>

        <script type="text/javascript">

            $(document).ready(function () {
                window.pharmacyRegisteration.populateAppUrl('${pageContext.request.contextPath}');

                $('.top_slid').slick({

                    autoplay: true,

                    autoplaySpeed: 5000

                });



                $('#startedBtn').click(function () {

                    //          alert("Hi");

                    var str = $("#txtZip").val();

                    if (str.trim() == '')

                    {

                        //              alert("A "+ $("#txtZip").val());

                        $("#errZipDiv").show();

                        $("#successZipDiv").hide();

                    } else

                    {

                        //              alert("B");

                        var flag = checkStateZip(str.trim(), -1);

                        //              alert("flag "+flag);

                    }

                });
                
                

                $('#imgIPhone').click(function () {

                    //          alert("Hi");

                    var str = $("#txtZip").val();

                    if (str.trim() == '')

                    {

                        //              alert("A "+ $("#txtZip").val());

                        $("#errZipDiv").show();

                        $("#successZipDiv").hide();

                    } else

                    {

                        //              alert("B");

                        var flag = checkStateZip(str.trim(), 1);

                        //              alert("flag "+flag);

                    }

                });



                $('#imgAndroid').click(function () {

                    //          alert("Hi");

                    var str = $("#txtZip").val();

                    if (str.trim() == '')

                    {

                        //              alert("A "+ $("#txtZip").val());

                        $("#errZipDiv").show();

                        $("#successZipDiv").hide();

                    } else

                    {

                        //              alert("B");

                        var flag = checkStateZip(str.trim(), 0);

                        //              alert("flag "+flag);

                    }

                });



                function checkStateZip(zip, iPhone)

                {

                    //////////////////////////////////////////////////////////////

                    var json = {"zip": zip};

                    $.ajax({

                        url: "${pageContext.request.contextPath}/ConsumerPortal/validateZip/" + zip + "/Texas",

                        type: "POST",

                        dataType: 'json',

                        contentType: 'application/json',

                        async: false,

                        data: JSON.stringify(json),

                        success: function (data) {

                            var response = eval(data);



                            //                alert("data length "+data.length);

                            if (data.length == 0)

                            {

                                $("#errZipDiv").show();

                                //$("#errZipDiv").html("Only Texas zip codes are allowed.");

                                $("#errZipDiv").html("Invalid Zip code, only available for Texas area");

                                $("#successZipDiv").hide();

                                return false;

                            } else

                            {

                                $("#errZipDiv").hide();

                                $("#successZipDiv").show();

                                if (iPhone == 1)

                                {

                                    window.open("https://itunes.apple.com/us/app/rx-direct/id1195583119?mt=8");

                                } else if (iPhone == 0)

                                {

                                    window.open("https://play.google.com/store/apps/details?id=com.rxdirect&amp;hl=en");

                                }

                                //                    $("#successZipDiv").html("Valid zip code.");

                                return true;

                            }

                            //                alert("B");   

                            //                return true;



                        },

                        error: function (e) {

                            alert('Error while processing ...' + e.responseText);

                        }

                    });



                    /////////////////////////////////////////////////////////////

                }



                $('.multiple-slider').slick({

                    infinite: true,

                    slidesToShow: 4,

                    slidesToScroll: 1,

                    autoplay: true,

                    autoplaySpeed: 3000,

                    prevArrow: null,

                    nextArrow: null,

                    responsive: [

                        {

                            breakpoint: 1024,

                            settings: {

                                slidesToShow: 2,

                                slidesToScroll: 1,

                                infinite: true,

                                dots: true

                            }

                        },

                        {

                            breakpoint: 600,

                            settings: {

                                slidesToShow: 2,

                                slidesToScroll: 2

                            }

                        },

                        {

                            breakpoint: 480,

                            settings: {

                                slidesToShow: 1,

                                slidesToScroll: 1,

                                centerMode: true

                            }

                        }

                        // You can unslick at a given breakpoint now by adding:

                        // settings: "unslick"

                        // instead of a settings object

                    ]

                });



            });

        </script>

        <script>

            //Hide the Previous button.



            $('.top_slid').slick({

                prevArrow: null,

                nextArrow: null,

            });

        </script>

        <script type="text/javascript">

            (function ($) {

                "use strict"



                // Accordion Toggle Items

                var iconOpen = 'fa fa-minus',
                        iconClose = 'fa fa-plus';



                $(document).on('show.bs.collapse hide.bs.collapse', '.accordion', function (e) {

                    var $target = $(e.target)

                    $target.siblings('.accordion-heading')

                            .find('em').toggleClass(iconOpen + ' ' + iconClose);

                    if (e.type == 'show')
                        $target.prev('.accordion-heading').find('.accordion-toggle').addClass('active');

                    if (e.type == 'hide')
                        $(this).find('.accordion-toggle').not($target).removeClass('active');

                });



            })(jQuery);

        </script>

    </head>



    <!-- Navigation -->

    <jsp:include page="inc/loginheader.jsp"/>



    <section class="banner_back">

        <img src="${pageContext.request.contextPath}/resources/images/banner_02_edited.png" class="img-fluid" style="width: 100%;">

        <div class="container">
            <div class="set_pos">
                <h1>
                    Cross the Pharmacy off your to-do list<sup>&trade;</sup>
                </h1>
                <h2>
                    Rx Run Delivery  <span> - free 2<sup>nd</sup> day</span>
                </h2>
                <h3>
                    prescription delivery to residents of the dalas <br>
                    fort worth metro area! 
                </h3>
                <h4>
                    <i>(same day delivery available at a  modest fee) </i>
                </h4>

                <div class="form-sect">
                    <div class="form-group">
                        <input type="text" class="form-control" id="txtZip" aria-describedby="emailHelp" placeholder="Enter your zip code" onkeypress="return IsDigit(event)">
                        <span id="errZipDiv" style="color:red;display:none">Please enter zip code</span>
                        <span id="successZipDiv" style="color:black;display:none">Coming soon in your area</span>
                        <button class="trial_button" id="startedBtn">Get Started</button>
                    </div>
                </div>
            </div>
            <!-- <div class="set_pos">

                <h1>

                    Cross the Pharmacy off your to-do list™

                </h1>

                <h2>

                    RxMetro Delivery - offering free 2nd day 

                </h2>

                <h3>

                    prescription delivery <small>(same day modest upcharge)</small> 

                </h3>

                <h4>

                    for  Dallas Fort Worth Metro area! 

                </h4>





                <div class="form-sect">

                    <div class="form-group">

                        <input type="text" class="form-control" id="txtZip" aria-describedby="emailHelp" placeholder="Enter your zip code" onkeypress="return IsDigit(event)">

                        <span id="errZipDiv" style="color:red;display:none">Please enter zip code</span>

                        <span id="successZipDiv" style="color:black;display:none">Coming soon in your area</span>

                        <button class="trial_button" id="startedBtn">Get Started</button>

                    </div>

                </div>



                <img id="imgIPhone" src="/resources/images/appstore.png" class="img-fluid app-btn1" style="display: none;">



                &nbsp;        





                <img id="imgAndroid" src="/resources/images/goggle.png" class="img-fluid app-btn1" style="display: none;">

                <br>   



                <img src="${pageContext.request.contextPath}/resources/images/inge_logo.png" class="img-fluid hide_img">

            </div> -->

        </div>

    </section>



    <section class="mobile_button_show">

        <div class="container">

            <a href="https://itunes.apple.com/us/app/rx-direct/id1195583119?mt=8" target="_blank" tabindex="-1" style="display: none;">

                <img src="${pageContext.request.contextPath}/resources/images/appstore.png" class="img-fluid app-btn2">

            </a>

            &nbsp;        



            <a href="https://play.google.com/store/apps/details?id=com.rxdirect&amp;hl=en" target="_blank" tabindex="-1" style="display: none;"><img src="/resources/images/goggle.png" class="img-fluid app-btn2">

            </a>

            <div class="form-sect2">

                <div class="form-group">

                    <input type="text" class="form-control" id="txtZipCode" aria-describedby="emailHelp" placeholder="Enter your zip code" onkeypress="return IsDigit(event)">
                    <span id="errZipDiv1" style="color:red;display:none;">Please enter zip code</span>
                    <span id="successZipDiv1" style="color:black;display:none">Coming soon in your area</span>
                    <button class="trial_button" onclick="window.pharmacyRegisteration.checkStateZip('txtZipCode',1);">Get Started</button>

                </div>

            </div>

    </section>



    <section class="pay_as_little">

        <div class="container">

            <h2>

                <strong>PAY AS LITTLE AS $0 PER MONTH</strong> FOR YOUR MEDICATIONS,<br>

                DELIVERED WITH COMPLIANCE REWARDS<sup>&trade;</sup>                

            </h2>

            <div class="row">

                <div class="col-sm-6 col-md-6 col-lg-3">

                    <div class="border_back">

                        <img src="${pageContext.request.contextPath}/resources/images/phone-2.png" class="img-fluid">

                        <h3>

                            AT YOUR CONVENIENCE

                        </h3>

                        <p>

                            WE MAKE IT CONVENIENT AND EASY TO TRANSFER YOUR PRESCRIPTIONS FROM ANY PHARMACY

                        </p>

                    </div>    

                </div>

                <div class="col-sm-6 col-md-6 col-lg-3">

                    <div class="border_back">

                        <img src="${pageContext.request.contextPath}/resources/images/accepts.png" class="img-fluid">

                        <h3>

                            ACCEPTS  MOST RX INSURANCE

                        </h3>

                        <p>

                            UPLOAD YOUR RX INSURANCE CARD USING JUST YOUR SMARTPHONE

                        </p>

                    </div> 

                </div>

                <div class="col-sm-6 col-md-6 col-lg-3">

                    <div class="border_back">

                        <img src="${pageContext.request.contextPath}/resources/images/medicine.png" class="img-fluid">

                        <h3>

                            Flexible Scheduling

                        </h3>

                        <p class="padd_bottom">

                            CHOOSE FROM ASAP, 4 HOUR OR NEXT MORNING DELIVERY

                        </p>

                    </div> 

                </div>

                <div class="col-sm-6 col-md-6 col-lg-3">

                    <div class="border_back">

                        <img src="${pageContext.request.contextPath}/resources/images/rewardlogin.png" class="img-fluid">

                        <h3>

                            COMPLIANCE REWARDS™

                        </h3>

                        <p>

                            EARN VALUABLE POINTS WHICH CAN BE USED TOWARD YOUR PURCHASES AT RX-DIRECT PHARMACIES *<br>
                            *(<small style="font-size:78%;"><i>CASH PAYING FOR COMMERCIALLY INSURANED PATIENTS</i></small>).

                        </p>

                    </div> 

                </div>
                <div class="col-md-12 work_petient">
                    <h4>How It works?</h4>
                    <p>Frictionless, patient application designed to empower and reward patients for taking charge of their medication
                    </p>
                </div>
            </div>

        </div>

    </section>



    <section class="car_banner" id="howItWorkDiv">

        <div class="container">

            <!-- <h2>

                How It works?

            </h2> -->

            <div class="car_image_align">

                <img src="${pageContext.request.contextPath}/resources/images/car_image_mobile.png" class="img-fluid">

            </div>

        </div>

    </section>



    <!-- <section class="hippa_calender">

        <div class="container">

            <div class="row">

                <div class="col-sm-12 col-md-6 col-lg-6">

                    <div class="inner_hippa">

                        <img src="${pageContext.request.contextPath}/resources/images/hipaa.png" class="img-fluid">

                        <h3>

                            HiPAA-compliant Driver

                        </h3>

                        <h4>

                            Security & Reliability

                        </h4>

                        <p>

                            Our climate controlled vehicles stay cool in drivers carry insulated bags and the drivers are background check and uniformed

                        </p>

                    </div>

                </div>

                <div class="col-sm-12 col-md-6 col-lg-6">

                    <div class="inner_hippa">

                        <div class="fixed_celender">

                            <input type="text" name="timepicker" class="time_element" placeholder="Available delivery times" style="min-width:250px; text-align: right; 

                                   background-color: #f9f9f9 ; border: 1px solid #999999; margin-bottom: 5px; padding:  10px 25px 10px 0; border-radius: 8px;" />

                            <i class="fa fa-calendar"></i>

                        </div>



                        <h3>

                            Flexible Scheduling

                        </h3>

                        <h4>

                            Technology That Works for Your

                        </h4>

                        <p>

                            No endless waiting – You determine when you’d like it delivered! You'll receive confirmations with signature verification

                            when she orders have been

                        </p>

                    </div>

                </div>

            </div>

        </div>

    </section> -->

    <section class="hippa_calender">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-4 col-lg-4">
                    <div class="inner_hippa">
                        <img src="${pageContext.request.contextPath}/resources/images/ISO-20001.png" class="img-fluid">


                    </div>
                    <div class="proffdeliver">
                        <p>ENCRYPTED & SECURE
                        </p>
                    </div>
                </div>
                <div class="col-sm-12 col-md-4 col-lg-4">
                    <div class="inner_hippa">
                        <img src="${pageContext.request.contextPath}/resources/images/hippa.png" class="img-fluid">



                    </div>
                    <div class="proffdeliver">
                        <p>VERIFIED CHAIN OF CUSTODY

                        </p>
                    </div>
                </div>
                <div class="col-sm-12 col-md-4 col-lg-4">
                    <div class="inner_hippa">
                        <img src="${pageContext.request.contextPath}/resources/images/gippa_b.png" class="img-fluid">




                    </div>
                    <div class="proffdeliver">
                        <p>PROOF OF DELIVERY
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>



    <section class="mobile_section">

        <div class="container">

            <div class="row">

                <div class="col-lg-5 ">

                    <img src="${pageContext.request.contextPath}/resources/images/mobiles.png" class="img-fluid">

                </div>

                <div class="col-lg-7 mobile_text">

                    <h2>

                        The Benefits?

                    </h2>

                    <h3>

                        Just a few of our benefits:

                    </h3>

                    <div>

                        <p><i class="fa fa-check"></i>No more wasting time in pharmacy lines</p><br>

                        <p><i class="fa fa-check"></i>PAY AS LITTLE AS $0 -- EARNING VALUABLE COMPLIANCE REWARDS™</p><br>

                        <p><i class="fa fa-check"></i>Existing refills can be easily transferred</p><br>

                        <p><i class="fa fa-check"></i>Relax while we bring prescriptions to your door</p><br>

                        <p><i class="fa fa-check"></i>Minimize exposure to germs in crowded pharmacies</p>

                    </div>

                </div>

            </div>

        </div>

    </section>





    <!-- Footer page-->

    <jsp:include page="inc/loginPagefooter.jsp"/>

    <!-- Footer page-->



    <!-- Modal Login -->



    <div class="modal" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">

        <div class="vertical-alignment-helper">

            <div class="modal-dialog vertical-align-center" role="document">

                <div class="modal-content login_model clearfix">

                    <button type="button" class="close model_close" data-dismiss="modal" aria-label="Close" onclick="window.pharmacyRegisteration.loginModelCloseAction();"></button>

                    <div class="row">

                        <div  class="col-md-6  login_col clearfix">



                            <h2>Pharmacy Login</h2>

                            <div class="message" id="message" style="color: green;" >${message}</div>

                            <form:form id="pharmacyLogin" commandName="pharmacy" method="post">

                                <div class="errorMessage" id="errorMessageLogin"></div>

                                <form:input id="loginEmailId" path="email" cssClass="form-control field_1" placeholder="Email Address" />

                                <form:password id="loginPasswordId" path="password" cssClass="form-control field_1" placeholder="Password"/>

                                <button type="button" class="btn field_btn pull-left" onclick="window.pharmacyRegisteration.loginPharmacyFn();">  Login</button>

                                <div style=" margin-left: 100px; line-height: 32px;width: 200px;"><small> Forgot</small>

                                    <a href="#" class="alignsignupmessage" style="font-size: 11.9px; color: #2071b6;" data-dismiss="modal" aria-label="Close" data-toggle="modal" data-target="#forgotUserPassword">Username/Password </a> <!--<small>or</small>-->

                                    <!--                                        <a href="#" class="alignsignupmessage" style="font-size: 14px; color: #2071b6;" data-dismiss="modal" aria-label="Close" data-toggle="modal" data-target="#forgotUserPassword">Password?</a>-->

                                </div>

                            </form:form>

                        </div>

                        <div class="col-md-6 register_col">



                            <%--       <c:if test="${(sessionBean.pmap[(93).intValue()].view eq true || sessionBean.pmap[(93).intValue()].manage eq true)}">--%>

                            <h2>Register New Pharmacy?</h2>

                            <p>Your pharmacy not register yet?</p>



                            <input type="submit" name="register_btn" class="btn field_btn" data-dismiss="modal" aria-label="Close" data-toggle="modal" data-target="#register" value="Register Now"  />

                            <%-- </c:if>--%>



                        </div>



                    </div>

                    <div class="col-sm-12 login_footer">

                        <p>Questions about the Compliance Reward  Web Access : Internet help desk: 888-xxx-xxxx  5am - 8pm  EST 7  days a week</p>

                    </div>

                </div>

            </div>

        </div>

    </div>



    <!--/ Modal Login -->

    <!---------------Register Modal ---------------->

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

                        <div class="row">

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

        <%--<jsp:include page="./inc/footer.jsp" />--%>

    </div>

    <!--/ Modal Register -->

    <!--  Forget user/password -->



    <div class="modal forgot_h" id="forgotUserPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">

        <div class="vertical-alignment-helper">

            <div class="modal-dialog vertical-align-center" role="document">

                <div class="modal-content regitsre_model login_model clearfix">

                    <button type="button" class="close model_close" data-dismiss="modal" aria-label="Close" onclick="window.pharmacyRegisteration.forgotUserNamePasswordModelCloseAction();"></button>

                    <div class="col-sm-12">

                        <div class="col-sm-12 regiter_title">

                            <h2>Forgot Username/Password</h2>

                            <div style="text-align: justify;">If you've forgotten your username/password, please enter the e-mail address associated with your account. Your username/password will be sent to that address.</div>

                            <form:form id="forgotFormId" commandName="pharmacy" role="form" method="POST">

                                <form:input id="forgotFormEmail" path="email" cssClass="form-control field_1" placeholder="Email Address" />

                                <div class="errorMessage" id="errorMessageForgotPassword"></div>

                                <button type="button" class="btn btn-primary" onclick="window.pharmacyRegisteration.forgotUserNamePasswordFn();"> SUBMIT </button>&nbsp;&nbsp;

                                <button type="button" class="btn btn-primary" data-dismiss="modal" aria-label="Close" onclick="window.pharmacyRegisteration.forgotUserNamePasswordModelCloseAction();">CANCEL</button>

                                <br/>

                                <br/>

                            </form:form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>



    <!--/Forget userpassword-->

    <script>

        var specialKeys = new Array();

        //specialKeys.push(8); //Backspace

        //specialKeys.push(9); //Tab

        specialKeys.push(46); //Delete

        specialKeys.push(36); //Home

        specialKeys.push(35); //End

        specialKeys.push(37); //Left

        specialKeys.push(39); //Right

        function IsDigit(e) {

            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;

            //alert(keyCode);

            var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)

                    || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);



            return ret;

        }



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

        function hideMessage() {



            document.getElementById("errorMessageLogin").style.display = "none";

            document.getElementById("errorMessageForgotPassword").style.display = "none";

            document.getElementById("errorMessagePharReg").style.display = "none";

            //document.getElementById("errorMsgLable").style.display = "none";

        }

        function startTimer() {

            //window.setInterval("hideMessage()", 15000);  // 5000 milliseconds = 5 seconds

        }

        function validateForm() {

            //            var valid = true;

            //            if ($("#email").val() === "") {

            //                alert("email required");

            //                valid = false;

            //                return;

            //            }

            //            if ($("#password").val() === "") {

            //                alert("password required");

            //                valid = false;

            //                return;

            //            }

            //

            //            $.ajax({

            //                    url: "${pageContext.request.contextPath}/PharmacyPortal/login/",

            //                    type: "POST",

            //                    data: $("#pharmacyLogin").serialize(),

            //                    async: false,

            //                    success: function (data) {

            //                        var json_obj = $.parseJSON(data);//parse JSON

            //                        console.log(json_obj.status);

            //                        if(json_obj.status == 'Fail'){

            //                            $('#errorMessage').html('<span><b>'+json_obj.errorMessage+'</b></span>');

            //                            valid = false;

            //                        }

            //                    }

            //                    , error: function (e) {

            //                        alert('Error while request...' + e);

            //                    }

            //                });

            //

            //                if(valid){

            //                    window.location.href = "${pageContext.request.contextPath}/PharmacyPortal/successLogin";

            //                }



            //            if (!valid) {

            //

            //                $.ajax({

            //                    url: "${pageContext.request.contextPath}/validateLoginField/" + $("#email").val() + "/" + $("#password").val(),

            //                    type: "POST",

            //                    async: false,

            //                    success: function (data) {

            //

            //                        valid = false;

            //                    }

            //

            //                    , error: function (e) {

            //                        alert('Error while request...' + e);

            //                    }

            //                });

            //            }

            //return valid;

        }





    </script>

    <%

        String message = (String) request.getAttribute("message");

        if (message != null && !message.trim().equals("")) {

    %>

    <script>

        $(window).load(function () {

            $('#login').modal('show');

        });

    </script>

    <%        }

    %>



</body>

</html>

