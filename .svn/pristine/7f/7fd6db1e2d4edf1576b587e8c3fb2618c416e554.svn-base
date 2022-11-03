<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.ssa.cms.util.PropertiesUtil"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <div id="content" class="c-login clearfix" style="width: 50%">
            <div class="header" style="border-bottom: 0px solid #B2B2B2;text-align: left;">

                <img src="${pageContext.request.contextPath}${PropertiesUtil.getProperty('APP_PATH_CODE')}resources/images/logo_desk.png" height="50">&nbsp;&nbsp;&nbsp;&nbsp;
                <!--<img class="hidden-xs hidden-sm" src="${pageContext.request.contextPath}/resources/images/bar.png"/>&nbsp;&nbsp;&nbsp;&nbsp;-->
                <!--<span class="innovative-text" style="color:#2071b6;font-family:arial;font-size: 25px;">Compliance Reward </span>-->
            </div> <!-- /header -->
            <div class="widget grid12">
                <div class="widget-content effect2">
                    <div class="row">
                        <h1 style="margin-left: 5%;clear:both;" class="loginpage-title">Admin Login</h1>  
                    </div>
                    <form:form id="userLoginForm" commandName="users" action="login" prependId="false">
                        <c:if test="${not empty message}"><div style="margin-left:5%;font-family:arial;" class="errorMessage" id="errormsg">${message}</div></c:if>
                      
                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12 login-image" style="padding:5px 8% 12px 5%;">
                            <form:input path="userName" placeholder="username" id="user" value="${cookie.user.value}" maxlength="28"/> 
                            <form:password id="psw" path="userPassword" placeholder="Password" value="${cookie.password.value}" maxlength="35"/>
                            <div class="form-group" style="margin-top: 3px;padding-left: 0px;">
                                <div class="pull-right" style="text-align:right;">
                                    <button class="btndrop" style="border: 0px;margin-bottom: 5px; background-color: #9dc92a; color: #FFFFFF;">Login</button>
                                    <button class="btndrop" type="reset" style="border: 0px;margin-bottom: 5px;  background-color: #9dc92a; color: #FFFFFF;" onclick="hideErrorMsg()">Reset</button>
                                </div>
                                <div class="custom-input pull-left" style="margin-top: -2px;">
                                    <input class="checkbox" type="checkbox" id="chkbx-1"   <c:if test="${cookie.user.value ne null && cookie.user.value ne '' && cookie.password.value ne null && cookie.password.value ne ''}"> checked="checked" </c:if>><label for="chkbx-1" style="color:#37343B;cursor: pointer; color: black;">Remember me.</label> 
                                    <form:hidden path="chkValue"></form:hidden>
                                    </div>
                                </div>
                                <!--                                <div class="login-image-background"></div>  -->

                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-4  hidden-xs  login-image-background">
                                &nbsp;
                            </div> 

                    </form:form>
                </div>
            </div>
        </div> <!-- /content -->
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            
            $("#user").focus();
            
            function hideErrorMsg() {

                var username = document.getElementById('user');
                var password = document.getElementById('psw');
                var chkbox = document.getElementById('chkbx-1');

                if (username.value != "" && username.value != 'Your id') {
                    username.value = "";
                    document.getElementById('user').value = "";
                }
                if (password.value != "" && password.value != 'Passcode') {
                    password.value = "";
                    document.getElementById('psw').value = "";
                  $("#chkbx-1").attr('checked', false);
                }
            }
            $(document).ready(function() {
                $("#chkbx-1").change(function() {
                    if ($(this).is(":checked"))
                        $("#chkValue").val("1");
                    else
                        $("#chkValue").val("");
                });
            });
        </script>
    </body>
</html>