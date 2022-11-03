<%@page import="com.ssa.cms.bean.SessionBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div class="wrapper">
            <div class="container changepwcontainer">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="pageTitle">CHANGE PASSWORD</h1>
                        <hr class="titleBorder" />
                        <p class="discription" style="text-indent:30px;">Lorem ipsum dolor site amet,consecteture adipicng sed bibendum arcu ac rhoncus adpiscing,nulla lexts dictum manruis,non volupat massa ipsum in nibh.</p>
                    </div>
                </div>
                <div>
                    <form:form action="${pageContext.request.contextPath}/PharmacyPortal/changepassword" commandName="pharmacy" role="form" method="POST" autocomplete="nofill" onsubmit="return validatePassword()">
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="form-group" style="position: relative;bottom: 8px;">
                                <div class="${not empty message?'message':'errorMessage'} messageleft">${not empty message?message:errorMessage}</div>
                   
                            </div>
                            <div class="form-group" style="padding-top:2px;">
                                <form:password path="password" class="form-control textboxchangepasswod" placeholder="Current password" autocomplete="nofill" />
                                <form:errors path="password" cssClass="errorMessageValid messageleft"/>
                            </div>
                            <div class="form-group" style="padding-top: 10px;">
                                <form:password path="newPassword" class="form-control textboxchangepasswod" placeholder="New password" />
                                <form:errors path="newPassword" cssClass="errorMessageValid messageleft"/>
                                <div id="newpsw" class="errorMessage messageleft"></div>
                            </div>
                            <div class="form-group" style="padding-top: 10px;">
                                <form:password path="repeatNewPassword" class="form-control textboxchangepasswod" placeholder="Repeat New password" />
                                <form:errors path="repeatNewPassword" cssClass="errorMessageValid messageleft"/>
                                <div id="rptpsw" class="errorMessage messageleft"></div>
                            </div>
                            <div class="form-group" style="padding-top: 10px;">
                                <button class="btn btn-primary" type="submit">UPDATE</button>
                                <button class="btn btn-primary" type="button" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/profile'">CANCEL</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            $(document).ready(function() {
                var pw = $("#password").val();
                if (pw !== "") {
                    $("#password").val('');
                }
            });
            function validatePassword() {
                var re, valid = true;
                if ($("#newPassword").val() !== "") {
                    re = /[A-Z]/;
                    if (!re.test($("#newPassword").val())) {
                        $("#newpsw").attr('style', 'display:inline;');
                        $("#newpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                    re = /[0-9]/;
                    if (!re.test($("#newPassword").val())) {
                        $("#newpsw").attr('style', 'display:inline;');
                        $("#newpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                    re = /[!@#$%^&*_=+-/]/;
                    if (!re.test($("#newPassword").val())) {
                        $("#newpsw").attr('style', 'display:inline;');
                        $("#newpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                    if ($("#newPassword").val().length < 10) {
                        $("#newpsw").attr('style', 'display:inline;');
                        $("#newpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                }
                if ($("#repeatNewPassword").val() !== "") {
                    re = /[A-Z]/;
                    if (!re.test($("#repeatNewPassword").val())) {
                        $("#rptpsw").attr('style', 'display:inline;');
                        $("#rptpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                    re = /[0-9]/;
                    if (!re.test($("#repeatNewPassword").val())) {
                        $("#rptpsw").attr('style', 'display:inline;');
                        $("#rptpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                    re = /[!@#$%^&*_=+-/]/;
                    if (!re.test($("#repeatNewPassword").val())) {
                        $("#rptpsw").attr('style', 'display:inline;');
                        $("#rptpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                    if ($("#repeatNewPassword").val().length < 10) {
                        $("#rptpsw").attr('style', 'display:inline;');
                        $("#rptpsw").text("Password must contains 10 characters (at least 1 digit, 1 capital letter & 1 speical character)");
                        valid = false;
                    }
                }
                return valid;
            }
            function hideMessage() {
                document.getElementById("rptpsw").style.display = "none";
                document.getElementById("newpsw").style.display = "none";
            }

            function startTimer() {
                window.setInterval("hideMessage()", 10000);  // 10000 milliseconds = 10 seconds
            }
        </script>
    </body>
</html>

