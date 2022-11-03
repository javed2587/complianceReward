<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/menu.jsp" />
                <form:form action="${pageContext.request.contextPath}/order/add/${order.id}" commandName="order" method="post" role="form">    
                    <div class="wrp clearfix" style="padding-left: 15px; padding-right: 15px;padding-top: 15px;">
                        <div class="search-grid">   
                            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">         
                                <img src="${pageContext.request.contextPath}/resources/images/newHeader.png" class="bg" style="left: 0;right: 0;">
                            </div>
                            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <p style="color: #2071b6; font-weight: bold; font-size: 20px;">Receive Your Savings TODAY!</p>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="color: #2071b6; font-size: 14px;">
                                        How would you like to receive your savings?
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <form:radiobutton id="textMsg" path="firstName" onclick="dynamicallyIdChange('textMsg')"/> TEXT MESSAGES
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <form:radiobutton id="email" path="firstName" onclick="dynamicallyIdChange('email')"/> E-MAIL
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <form:radiobutton id="phoneCall" path="firstName" onclick="dynamicallyIdChange('phoneCall')"/> PHONE CALL
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        invalid Phone
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <form:input path="logo" cssClass="txt form-control" placeHolder="(_ _ _)_ _ _ - _ _ _ _"/>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="color: #2071b6;font-size: 12px;">
                                        Please enter your 10-digit mobile phone number in the box above and click submit.*
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-7 col-xs-12" style="top: 10px;">
                                        * Message and data rates may apply.
                                    </div>
                                    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12" align="right">
                                        <button class="btndrop" style="background:#2071b6; color: white;"> SIGNUP <i class="fa fa-play" style="padding-left: 9px;"></i></button>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="search-grid">     
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background1" style="display: block;border-left: 1px solid #c6c6c6; border-right: 1px solid #c6c6c6;border-bottom: 1px solid #c6c6c6;margin-bottom: 10px;margin-right: 10px;float: left; margin-top: 15px; height: 470px;" id="panel${0}">         

                                <div style="padding:5px;width:100%;padding-left: 0px;position:relative;font-size: 20px; color: #2071b6;top:8px;" align="center">  
                                    Welcome to API Direct 
                                </div>
                                <div align="center">  
                                    <hr/>
                                </div>
                                <div align="center">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing<br>
                                    Sed bibendum, arcu ac rhoncus adipiscing, nulla lectus dictum mauris, non volutpat massa ipsum in nibh.
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" style="background: white;top:10px;left:20px;height: 170px;">
                                    <br/><span><i class="fa fa-search" style="background:#2071b6; color: white;font-size: 30px;"></i>
                                        <span style="color: #2071b6; font-size: 20px;">Quality Materials at Competitive Pricing </span></span><br><br>
                                    <span style="top:10px;">Fertility, Topical Pain Care, Scar & Sound, Migraine, Allergy, HCG & Testosterone, Anti-Aging Serums, Sun Damage Repair, Libido</span><br><br/>
                                </div>
                                <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12" style="background: white;top:10px;right:20px;float: right;width: 45%;">
                                    <br/><i class="fa fa-male" style="background:#2071b6; color: white;font-size: 30px;"></i>
                                    <span style="color: #2071b6; font-size: 20px;">Patients </span><br><br>
                                    By participating in the API Direct savings program, you may pay as little as $8* for your compounded medication on covered insurance plans.
                                    Enrollments is quick and easy and can be done in the widget to the right. Simply select how you'd like to receive your savings (text message, e-mail, or phone call)
                                    and click submit.<br><br/>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" style="background: white;top:30px;left:20px;">
                                    <br/><i class="fa fa-medkit" style="background:#2071b6; color: white;font-size: 30px;"></i>
                                    <span style="color: #2071b6; font-size: 20px;">Pharmacies </span><br><br>
                                    <span style="top:10px;">To Track your patients' compounded prescription orders, lookup ingredient information, and generate finacial reports, you will need to <a href="#"><u style="color: #2071b6;">create an account</u></a>. if you already have an account,you may <a href="#"><u style="color: #2071b6;">login now</u></a>.</span><br><br/>
                                </div>
                                <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12" style="background: white;top:30px;right:20px;float: right;width: 45%;height: 150px;">
                                    <br/><i class="fa fa-user-md" style="background:#2071b6; color: white;font-size: 30px;"></i>
                                    <span style="color: #2071b6; font-size: 20px;">Healthcare Provider </span><br><br>
                                    To learn about how API Direct may be benefit your patients, please visit our <a href="#"><u style="color: #2071b6;">Provider Portal</u></a> 
                                    <br><br/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>

            <script type="text/javascript">
                function dynamicallyIdChange(id) {
                    if (id === "textMsg") {
                        $('.txt').attr("id", "txtMsgs");
                        $('.txt').attr("placeHolder", "(_ _ _)_ _ _ - _ _ _ _");
                        $('.txt').val('');
                    } else if (id === "email") {
                        $('.txt').attr("id", "e-mail");
                        $('.txt').attr("placeHolder", "Enter E-mail");
                        $('.txt').val('');
                    } else if (id === "phoneCall") {
                        $('.txt').attr("id", "phoneNo");
                        $('.txt').attr("placeHolder", "(_ _ _)_ _ _ - _ _ _ _");
                        $('.txt').val('');
                    }
                }
                $(document).ready(function() {
                    $('#textMsg').attr('checked', true);
                });
            </script>
        </div> <!-- /content -->
        <jsp:include page="./inc/footer.jsp" />
    </div> <!-- /wrapper -->
</body>
</html>
