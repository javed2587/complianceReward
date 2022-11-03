<!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div class="container" style="background-color:#efefef;margin-top:10px;padding-bottom:15px;margin-bottom: 50px;">
            <div>
                <h2 class="pageTitle">PRIVACY POLICY</h2>
                <hr class="titleBorder"/>
            </div>
            <div style="text-align: justify;">
                <div class="col-lg-12 col-sm-12 col-xs-12 item">
                    <c:forEach var="accountContent" items="${pageContents}">
                        <c:set var="dcTitle" value="${accountContent.cMSPages.title}"/>
                        <c:if test="${fn:containsIgnoreCase(dcTitle, 'Privacy Policy – Content')}">
                            ${accountContent.content}
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp" />
    </body>
</html>

