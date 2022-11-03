<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <form:form action="${pageContext.request.contextPath}/survey/submitSurvey" commandName="survey">
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>   
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div class="row grid" style="height: auto;margin-bottom: 10px; padding-bottom:17px;background:#f7f7f7;">
                                <div style="margin-left: 10px;">
                                    <h3>Thank you for taking the time to complete this Survey!</h3>
                                    <span>Your response is very valuable to us. We'll keep this information to ourselves.</span>
                                </div>
                            </div>
                        </div> <!-- /content -->
                </form:form>
            </div>
        </div> <!-- /wrapper -->
        <jsp:include page="./inc/footer.jsp" />
        <script language="javascript" type="text/javascript">
            function closeWindow() {
                var win = window.open(window.location, "_self");
                win.close();
            }
        </script> 
    </body>
</html>

