<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <!-- <link rel="icon" type="image/png" href="images/e-transcription_favicon.png" />-->
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
              rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:400,400i,700" rel="stylesheet">
        <!--    <link rel="shortcut icon" href="images/" type="image/x-icon" /> -->
        <!-- Bootstrap CSS -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/css/bootstrap.min.css'>
        <link href="${pageContext.request.contextPath}/resources/fontawesome4/fontawesome4/css/fontawesome.css" rel="stylesheet" type="text/css" property="stylesheet"
              />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/resources/css/portal/logincss/styleabout.css" rel="stylesheet" type="text/css" property="stylesheet" />
        <link href="${pageContext.request.contextPath}/resources/css/portal/logincss/mediaabout.css" rel="stylesheet" type="text/css" property="stylesheet" />
        <!-- <link rel="stylesheet" href="css/header.css">-->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/slick.min.js" type="text/javascript"></script>
        <title>Contact Us</title>
    </head>

    <body>
        <!-- Navigation -->
        <jsp:include page="inc/loginheader.jsp"/>
        <section class="banner_back_contact">
            <div class="container">
                <h1>
                    Contact Us
                </h1>
            </div>
        </section>

        <section class="contact_us_form">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-6 col-lg-6 padd">
                        <i class="fa fa-envelope"></i><h2>Contact</h2>
                        <form id="frmContact">
                            <div>
                                <input id="contactName" type="text" placeholder="Name" name="name" oninput="window.pharmacyRegisteration.hideErrorMsg('contactName');"><br id="contactNameBrline">
                                <span id="contactNameReq" style="font-size: 11.9px; color: red;">&nbsp;</span>
                            </div>
                            <div>
                                <input id="contactEmail" type="text" placeholder="Email" name="email" oninput="window.pharmacyRegisteration.hideErrorMsg('contactEmail');"><br id="contactEmailBrline">
                                <span id="contactEmailReq" style="font-size: 11.9px; color: red;">&nbsp;</span>
                            </div>
                            <div>
                                <input id="contactPhone" type="text" placeholder="Phone" name="phoneNumber" maxlength="12" oninput="window.pharmacyRegisteration.hideErrorMsg('contactPhone');" onkeypress="return window.pharmacyRegisteration.isDigit(event)"><br id="phoneBrLine">
                                <span id="contactPhoneReq" style="font-size: 11.9px; color: red;">&nbsp;</span>
                            </div>
                            <div id="msgBodyDiv">
                                <textarea id="msgBody" placeholder="How can we help you?" name="message" oninput="window.pharmacyRegisteration.hideErrorMsg('msgBody');"></textarea><br>
                                <span id="msgBodyReq" style="font-size: 11.9px; color: red;">&nbsp;</span>
                            </div>
                        </form>
                        <div id="errorMessage" style="font-size: 11.9px; color: #2071b6;">&nbsp;</div>
                        <button class="sub_bu" onclick="window.pharmacyRegisteration.saveContactInfo()">Submit</button>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-6 padd">
                        <i class="fa fa-info-circle"></i>
                        <h2>
                            Contact Us Via
                        </h2>
                        <div class="cont_inner">
                            <div class="i_cs">
                                <i class="fa fa-phone"></i>
                                <div class="disp_inlin">
                                    <h3>
                                        Tel:
                                    </h3>
                                    <p>
                                        313 633 0165
                                    </p>
                                </div>
                            </div>
                            <div class="i_cs">
                                <i class="fa fa-envelope"></i>
                                <div class="disp_inlin">
                                    <h3>
                                        Email:
                                    </h3>
                                    <p>
                                        info@RxRun.com
                                    </p>
                                </div>
                            </div>
                            <div class="i_cs">
                                <i class="fa fa-map-marker"></i>
                                <div class="disp_inlin pad_left">
                                    <h3>
                                        Address:
                                        </h2>
                                        <p>
                                            3201 FANNIN LN  l  Suite 200  l  SOUTHLAKE, TX 76092
                                        </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3347.2167409607305!2d-97.124540785232!3d32.971683080913635!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x864dd3647fa48ce7%3A0x6e7e7e6093435611!2s3201+Fannin+Ln+l++Suite%2C+Grapevine%2C+TX+76092!5e0!3m2!1sen!2s!4v1537900867620" width="100%" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="inc/loginPagefooter.jsp"/>



        <!-- Navigation -->
        <!-- jQuery first, then Bootstrap JS. -->
        <!-- jQuery first, then Bootstrap JS. -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js'></script>
        <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.min.js"></script>
        <!-- login Page Modal-->
        <jsp:include page="inc/loginPageModals.jsp"/>
        <!-- login Page Modal-->
        <script type="text/javascript">
                            $(function () {
                                window.pharmacyRegisteration.populateAppUrl('${pageContext.request.contextPath}');
                                
                                $('#contactPhone').keydown(function (e) {
                                    var key = e.charCode || e.keyCode || 0;
                                    $text = $(this);
                                    if (key !== 8 && key !== 9) {
                                        if ($text.val().length === 3) {
                                            $text.val($text.val() + '-');
                                        }
                                        if ($text.val().length === 7) {
                                            $text.val($text.val() + '-');
                                        }

                                    }

                                    return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
                                });
                            });

        </script>
    </body>

</html>