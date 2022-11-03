<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Users <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Change Password
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Change Password</h1>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/user/savepassword" autocomplete="off" commandName="users" onsubmit="return validatePassword()">
                    <form:hidden path="userId" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />
                    <form:hidden path="emailAddress" />
                    <form:hidden path="isAdmin" />

                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">

                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/user/list'"><a href="${pageContext.request.contextPath}/user/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group" onclick="$('#users').submit();">
                                <div class="btndrop btn-margin"><a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom: 17px;background:#f7f7f7;">

                            <div class="search-grid">

                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3">
                                        <label>  New Password:<span style="color:red">*</span></label> 

                                        <form:password path="userPassword" cssClass="form-control" autocomplete="off" id="newpassword" maxlength="25" onchange="return validatePassword()"/> 
                                        <span id="myspan" class="errorMessage1" style="display: none;">Password cannot less than 10 character</span>
                                        <c:if test="${not empty message1}"><div class="errorMessageValid1">${message1}</div></c:if>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Confirm Password:<span style="color:red">*</span></label> 
                                        <form:password path="confirmPassword" cssClass="form-control" showPassword="true" autocomplete="false" id="confirmPassword" maxlength="25" onchange="return validatePassword()"/>
                                        <span id="myspan1" class="errorMessage1" style="display: none;">Password cannot less than 10 character</span>
                                        <c:if test="${not empty message2}"><div class="errorMessageValid1">${message2}</div></c:if>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero margin-bottom-zero">
                                        <div class="col-sm-3">

                                            <label>User Name:</label> <br>
                                        ${users.userName}

                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">

                                    </div>
                                </div>
                            </div>  
                        </div>
                    </div>
                </form:form>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript">
            function validatePassword()
            {
                var res = true;
                document.getElementById("myspan1").style.display = "none";
                document.getElementById("myspan").style.display = "none";
                var re;

                if (document.getElementById("confirmPassword").value != "" && document.getElementById("confirmPassword").value.length < 10)
                {
                    document.getElementById("myspan1").style.display = "block";
                    if (document.getElementById("myspan1").innerHTML = "Password should be contains at least one digit,one capital letter and one special character") {
                        document.getElementById("myspan1").innerHTML = "Password cannot less than 10 characters";
                    }
                    res = false;
                }
                if (document.getElementById("newpassword").value != "" && document.getElementById("newpassword").value.length < 10)
                {
                    document.getElementById("myspan").style.display = "block";
                    if (document.getElementById("myspan").innerHTML = "Password should be contains at least one digit,one capital letter and one special character") {
                        document.getElementById("myspan").innerHTML = "Password cannot less than 10 characters";
                    }
                    res = false;
                }
                if (document.getElementById("newpassword").value != null && document.getElementById("newpassword").value != "" && document.getElementById("newpassword").value.length >= 10) {
                    re = /[A-Z]/;
                    if (!re.test($("#newpassword").val())) {
                        document.getElementById("myspan").style.display = "block";
                        document.getElementById("myspan").innerHTML = "Password should be contains at least one digit,one capital letter and one special character";
                        res = false;
                    }
                    re = /[0-9]/;
                    if (!re.test($("#newpassword").val())) {
                        document.getElementById("myspan").style.display = "block";
                        document.getElementById("myspan").innerHTML = "Password should be contains at least one digit,one capital letter and one special character";
                        res = false;
                    }
                    re = /[!@#$%^&*_=+-/]/;
                    if (!re.test($("#newpassword").val())) {
                        document.getElementById("myspan").style.display = "block";
                        document.getElementById("myspan").innerHTML = "Password should be contains at least one digit,one capital letter and one special character";
                        res = false;
                    }
                }
                if (document.getElementById("confirmPassword").value != null && document.getElementById("confirmPassword").value != "" && document.getElementById("confirmPassword").value.length >= 10) {
                    re = /[A-Z]/;
                    if (!re.test($("#confirmPassword").val())) {
                        document.getElementById("myspan1").style.display = "block";
                        document.getElementById("myspan1").innerHTML = "Password should be contains at least one digit,one capital letter and one special character";
                        res = false;
                    }
                    re = /[0-9]/;
                    if (!re.test($("#confirmPassword").val())) {
                        document.getElementById("myspan1").style.display = "block";
                        document.getElementById("myspan1").innerHTML = "Password should be contains at least one digit,one capital letter and one special character";
                        res = false;
                    }
                    re = /[!@#$%^&*_=+-/]/;
                    if (!re.test($("#confirmPassword").val())) {
                        document.getElementById("myspan1").style.display = "block";
                        document.getElementById("myspan1").innerHTML = "Password should be contains at least one digit,one capital letter and one special character";
                        res = false;
                    }
                }
                return res;
            }
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
                document.getElementById("myspan1").style.display = "none";
            }

            function startTimer() {
                window.setInterval("hideMessage()", 10000);  // 10000 milliseconds = 10 seconds
            }
        </script>
</html>

