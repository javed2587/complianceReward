<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="${pageContext.request.contextPath}/resources/css/portal/logincss/style.css" rel="stylesheet" type="text/css" property="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/portal/logincss/stylelogin.css" rel="stylesheet" type="text/css" property="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/portal/logincss/medialogin.css" rel="stylesheet" type="text/css" property="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/portal/logincss/timepicki.css" rel="stylesheet" type="text/css" property="stylesheet" />
<script src="${pageContext.request.contextPath}/resources/ssasoft/js/pharmacyRegisteration.js" type="text/javascript"></script>

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
