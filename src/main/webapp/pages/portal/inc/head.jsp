<head>
    <title>Consumer Portal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta charset="utf-8" />
    <meta name = "format-detection" content = "telephone=no">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/dist/css/bootstrap_1.css" rel="stylesheet" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome-4.0.3/css/font-awesome.min.css">
<!--    <link href="${pageContext.request.contextPath}/resources/css/PortalStyle.css" rel="stylesheet" type="text/css" />-->

    <link href="${pageContext.request.contextPath}/resources/css/stylepharmacy.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/navigation.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.min.css" />
    <style type="text/css" title="currentStyle">
        @import "${pageContext.request.contextPath}/resources/media/css/media.css";
    </style>
    <style type="text/css" title="currentStyle">
        @import "${pageContext.request.contextPath}/resources/media/css/demo_table.min.css";
    </style>
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/resources/images/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <link href="${pageContext.request.contextPath}/resources/media/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />

    <link href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css" rel="stylesheet" type="text/css"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/portal/bootstrap.css" rel="stylesheet" property="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/portal/navigation.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/font-awesome-4.5.0/css/font-awesome.css" rel="stylesheet" type="text/css" property="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/portal/style.css" rel="stylesheet" type="text/css" property="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/portal/media.css" rel="stylesheet" type="text/css" property="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/media/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrapp.min.js"></script>
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
</head>
