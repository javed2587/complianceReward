<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div class="container contentsContainer marginbottom">
            <h2 class="pageTitle">Forgot Password</h2>
            <hr class="titleBorder">    
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center" style="text-align: center !important;">
                <c:forEach var="pc" items="${pageContents}">
                    <c:set var="dcTitle" value="${pc.cMSPages.title}"/>
                    <c:if test="${fn:containsIgnoreCase(dcTitle, 'Forgot Password – Content')}">
                        ${pc.content}
                    </c:if>
                </c:forEach>
            </div>
            <form:form action="${pageContext.request.contextPath}/ConsumerPortal/forgotpassword" commandName="pharmacy" role="form" method="POST">
                <div class="form-group">
                    <div class="col-lg-6 col-sm-12 col-md-12 col-xs-12">
                        <c:if test="${not empty errorMessage}"><label class="error">${errorMessage}</label></c:if>
                        <c:if test="${not empty message}"><label class="message">${message}</label></c:if>
                        <form:input path="email" class="form-control" placeholder="Email address" autofocus="autofocus"/>
                    </div>
                </div>
                <span class="margin-top-18 pull-left col-lg-12 col-sm-12 col-md-12 col-xs-12">
                    <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
                        <!--type="submit"-->
                        <button type="submit" class="btn btn-primary">SUBMIT</button>
                        <button type="button" class="btn btn-primary" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/login'">CANCEL</button>
                    </div>
                </span>
            </form:form>
        </div>
        <jsp:include page="./inc/footer.jsp" />
    </body>
</html>

