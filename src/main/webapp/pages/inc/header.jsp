<!--<div id="loading">
    <div>
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.ssa.cms.util.PropertiesUtil"%>
<div id="top">  
    <div class="main-logo">  
        <a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}dashboard" onclick="return false;">
            <img src="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}resources/images/logo_desk.png" height="50">&nbsp;&nbsp;&nbsp;&nbsp;<img class="hidden-xs hidden-sm" src="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}resources/images/bar.png" />&nbsp;&nbsp;&nbsp;&nbsp;</a>
        <span class="iCSolution" style="color:#2071b6;font-family:arial;font-size: 22px;">Super Admin</span>
    </div>  
    <%--<div class="main-logo" style="display: ${survey.title ne null?'block':'none'};">
        <img src="${pageContext.request.contextPath}/resources/images/RxDirect_Logo.png">&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="iCSolution" style="color:#2071b6;font-family:arial;font-size: 18px;">${survey.title}</span>
    </div>--%>

    <!--    <div class="m-nav"><i class="fa fa-bars"></i></div>  -->   
    <div class="btn-group loginn hidden-xs" id="login">      
        <button type="button" class="btn btn-default dropdown-toggle" style="font-family: arial;padding:8px 7px;max-height:35px;font-size:12px;box-shadow: 0.1em 0.2em 0.1em 0.001em #cccccc inset;border:none;" data-toggle="dropdown">  
            <a><span  class="fa fa-bell shake" style="font-size:18px;align:center;color:#999999;border-right:1px solid gray;padding-right: 6px;"></span></a> 
            &nbsp;&nbsp;<b style="color: #9dc92a;position:relative;top:-2px;font-weight:bolder;">Welcome, </b><b  style="color: #444444;position:relative;font-weight:normal;top:-2px;">${sessionBean.userName}</b> <span class="fa fa-user" style="color: #969697; font-size: 17px; padding-left: 4px; position: relative; top: -2px; vertical-align: middle;"></span> 
            <span class="caret" style="position:relative;top:-2px;"></span>
        </button>
        <ul class="dropdown-menu" role="menu" <c:choose><c:when test="${sessionBean.userNameDB ne 'viewOrder'}">style="text-align: center;margin-left:108px;min-width:100px; z-index: 1001;"</c:when> <c:otherwise>style="text-align: center;margin-left:132px;min-width:100px; z-index: 1001;"</c:otherwise></c:choose> > 
            <li><a href="${pageContext.request.contextPath}${fn:trim(fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE')))}user/list">User Setting</a></li>
            <li><a href="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}logout">Logout</a></li>
        </ul>  
    </div>

</div>