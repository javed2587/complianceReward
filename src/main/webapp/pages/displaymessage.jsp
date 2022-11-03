<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <div class="c-login clearfix" style="width: 57%;padding-left: 35px;">
            <div class="header" style="border-bottom: 0px solid #B2B2B2;text-align: left;" id="header">
                <img src="${pageContext.request.contextPath}/resources/images/API_Logo.jpg">&nbsp;&nbsp;&nbsp;&nbsp;<img class="hidden-xs hidden-sm" src="${pageContext.request.contextPath}/resources/images/bar.png"/>&nbsp;&nbsp;&nbsp;&nbsp;<span class="innovative-text" style="color:#2071b6;font-family:arial;font-size: 22px;">Rx Saving & Support Program</span>
            </div> <!-- /header -->
            <div class="widget-message grid12" id="messageDiv" style="text-align: center;">
                <div class="widget-content effect2">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 76px;padding-left: 0px;color:#2071b6;font-family:arial;font-size: 20px;font-weight: bold;">
                        ${message}
                        <!--                        <div class="form-group" style="margin-top: 76px;padding-left: 0px;color:#2071b6;font-family:arial;font-size: 22px;">
                                                   
                                                </div>-->
                    </div>
                </div>
            </div>
        </div>
        <script>
            $('#messageDiv').css('min-width', $('#header').outerWidth(true));
        </script>                
    </body>
</html>
