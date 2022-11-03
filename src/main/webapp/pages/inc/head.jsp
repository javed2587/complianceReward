<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ssa.cms.util.PropertiesUtil"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
    <script language="javascript">
        window.history.forward(-1);
    </script>
    <title>Compliance Rewards</title>

    <!-- bootstrap -->     

    <link href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/bootstrap/dist/css/bootstrapp.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/bootstrap/dist/css/fileinput.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/css/font-awesome-4.0.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/css/jquery-ui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/js/jquery.datetimepicker.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/css/selectBox.min.css" />  

    <link rel="stylesheet" href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/css/lightbox.min.css" /> 

    <link href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/css/style.css" rel="stylesheet" type="text/css" />
    <style type="text/css" title="currentStyle">
        @import "${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/media/css/demo_table.min.css";
    </style>
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/media/js/jquery.min.js"></script>
    <!--
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script> 
    -->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/media/js/jquery.dataTable.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/js/jquery.datetimepicker.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/js/jquery.maskedinput.min.js"></script>  
    <!---------------------------------------------->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/js/bootstrap-select.min.js"></script> 
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/bootstrap/dist/js/bootstrap.file-input.min.js"></script> 
    <%--Text Editor--%>
    <link href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/bootstrap/texteditor/bootstrap-wysihtml5.min.css" rel="stylesheet" />
    <!-- Bootstrap file upload -->
    <link href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/bootstrap/dist/css/jasny-bootstrap.min.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/bootstrap/dist/js/jasny-bootstrap.min.js"></script> 
         <style>
		
        .lb-image {
            display: block !important;
        }
    </style>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/summernote/summernote.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/summernote/summernote.min.css"/>  
</head>