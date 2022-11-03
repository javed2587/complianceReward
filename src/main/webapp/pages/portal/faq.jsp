<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="false" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <!--   <link rel="icon" type="image/png" href="images/e-transcription_favicon.png" />-->
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
        <title>FAQ</title>
    </head>

    <body>
        <!-- Navigation -->
        <jsp:include page="inc/loginheader.jsp"/>
        <section class="banner_back_faq">
            <div class="container">
                <h1>
                    FAQ
                </h1>
            </div>
        </section>

        <section class="faq">
            <div class="container">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading0">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse0" aria-expanded="true"
                                   aria-controls="collapse0" class="">
                                    What is the Rx Run Delivery™ program?<i class="accordion_icon fa fa-angle-down"></i>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading0"
                             aria-expanded="true" style="">
                            <div class="panel-body">
                                <p><span style="font-size:12pt"><span style="font-family: 'Roboto Condensed', sans-serif;">The
                                            Rx Run Delivery™ program is an application based, full service Pharmacy
                                            program designed to assist patients by offering the convenience of home
                                            delivery and access to copay assistance and personalized communication for
                                            adults and permitted dependents.</span></span></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading1">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="false"
                                   aria-controls="collapse1" class="collapsed">
                                    Where is the participating area for the program services? <i class="accordion_icon fa fa-angle-down"></i>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading1"
                             aria-expanded="false">
                            <div class="panel-body">
                                <p><span style="font-size:12pt"><span style="font-family: 'Roboto Condensed', sans-serif;">Patients
                                            must be in the Dallas Fort Worth area for same and next day deliveries—and a
                                            Texas resident to receive service facilitated by mail to participate in this
                                            program. </span></span></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading2">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false"
                                   aria-controls="collapse2" class="collapsed">
                                    If I am under 18 years of age, can I enroll in the Rx Run Delivery™ program? <i class="accordion_icon fa fa-angle-down"></i>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2"
                             aria-expanded="false">
                            <div class="panel-body">
                                <p><span style="font-size:12pt"><span style="font-family: 'Roboto Condensed', sans-serif;">A
                                            patient under the age of 18 can be entered in the Rx Run Delivery™ system;
                                            however, a parent or legal guardian must be entered as the patient’s caregiver
                                            in order to complete enrollment. </span></span></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading3">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" aria-expanded="false"
                                   aria-controls="collapse3" class="collapsed">
                                    Can I use the program benefits at any pharmacy? <i class="accordion_icon fa fa-angle-down"></i>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading3"
                             aria-expanded="false">
                            <div class="panel-body">
                                <p><span style="font-size:12pt"><span style="font-family: 'Roboto Condensed', sans-serif;">No,
                                            the program is reserved for those invited at this time—we will be expanding
                                            once we complete our expansion of the Rx Run Delivery™ program.</span></span></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading4">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse4" aria-expanded="false"
                                   aria-controls="collapse4" class="collapsed">
                                    How much does the program cost? <i class="accordion_icon fa fa-angle-down"></i>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse4" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading4"
                             aria-expanded="false">
                            <div class="panel-body">
                                <p><span style="font-size:12pt"><span style="font-family: 'Roboto Condensed', sans-serif;">There
                                            is no cost to the patient for use of the program, although, any message and
                                            data rates may apply.. We never charge you for the messages; however, your
                                            mobile service provider may charge you for mobile messages as a part of your
                                            contract or service. Please contact your mobile service provider for details.</span></span></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading5">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse5" aria-expanded="false"
                                   aria-controls="collapse5" class="collapsed">
                                    I don't have a mobile phone; can I still participate and receive treatment savings? <i
                                        class="accordion_icon fa fa-angle-down"></i>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading5"
                             aria-expanded="false">
                            <div class="panel-body">
                                <p><span style="font-size:12pt"><span style="font-family: 'Roboto Condensed', sans-serif;">No!
                                            Patients may only participate via the mobile application at this time—and only
                                            to those invited. </span></span></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading6">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse6" aria-expanded="false"
                                   aria-controls="collapse6" class="collapsed">
                                    If I experience a problem with the program, who should I contact? <i class="accordion_icon fa fa-angle-down"></i>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse6" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading6"
                             aria-expanded="false">
                            <div class="panel-body">
                                <p><span style="font-size:12pt">You can contact a customer service representative by
                                        clicking the help link within the application.</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="inc/loginPagefooter.jsp"/>
        <!-- Navigation -->
        <!-- jQuery first, then Bootstrap JS. -->
        <!-- jQuery first, then Bootstrap JS. -->
        <script type='text/javascript' src='${pageContext.request.contextPath}/resources/js/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/js/bootstrap.min.js'></script>
        <!-- login Page Modal-->
        <jsp:include page="inc/loginPageModals.jsp"/>
        <!-- login Page Modal-->



    </body>

</html>