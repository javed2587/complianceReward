<head>
    <title>COMPLIANCE REWARDS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="utf-8" />
    <meta name = "format-detection" content = "telephone=no">
       <link href="${pageContext.request.contextPath}/resources/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" property="stylesheet" />
       <link href="${pageContext.request.contextPath}/resources/css/portal/theme.css" rel="stylesheet" property="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/js/portal/jquery.js" type="text/javascript"></script>  
    <script src="${pageContext.request.contextPath}/resources/js/portal/jquery-1.12.3.js"  type="text/javascript" ></script>  
    <!--
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>  
    -->
    <script src="${pageContext.request.contextPath}/resources/js/portal/bootstrap.min.js" type="text/javascript"></script>

    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/portal/bootstrap.css" rel="stylesheet" property="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/portal/navigation.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/js/portal/jquery.dataTables1.10.7.css" rel="stylesheet" type="text/css" />
    <!--    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.7/css/jquery.dataTables.css"> -->
    <link href="${pageContext.request.contextPath}/resources/font-awesome-4.5.0/css/font-awesome.css" rel="stylesheet" type="text/css" property="stylesheet" />
    
    <link href="${pageContext.request.contextPath}/resources/css/portal/media.css" rel="stylesheet" type="text/css" property="stylesheet" />
     <link href="${pageContext.request.contextPath}/resources/css/portal/notifier.css" rel="stylesheet" type="text/css" property="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/portal/style.css" rel="stylesheet" type="text/css" property="stylesheet" />
     <link href="${pageContext.request.contextPath}/resources/css/newmedia.css" rel="stylesheet" type="text/css" property="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/portal/jquery.dataTables1.10.7.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/portal/style1.css" rel="stylesheet" type="text/css" property="stylesheet" />
    <!--    <script type="text/javascript" src="http://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>-->
    <script>
    (function (global) {

            if (typeof (global) === "undefined") {
                throw new Error("window is undefined");
            }

            var _hash = "!";
            var noBackPlease = function () {
                global.location.href += "#";

                // making sure we have the fruit available for juice (^__^)
                global.setTimeout(function () {
                    global.location.href += "!";
                }, 50);
            };

            global.onhashchange = function () {
                if (global.location.hash !== _hash) {
                    global.location.hash = _hash;
                }
            };

            global.onload = function () {
                noBackPlease();

                // disables backspace on page except on input fields and textarea..
                document.body.onkeydown = function (e) {
                    var elm = e.target.nodeName.toLowerCase();
                    if (e.which === 8 && (elm !== 'input' && elm !== 'textarea')) {
                        e.preventDefault();
                    }
                    // stopping event bubbling up the DOM tree..
                    e.stopPropagation();
                };
            }

        })(window);
    </script> 

</head>

