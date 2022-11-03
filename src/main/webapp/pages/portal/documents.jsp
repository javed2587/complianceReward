<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div class="container contentsContainer">
            <h2 class="pageTitle">Documents & Forms</h2>
            <hr class="titleBorder"> 
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 aboutusinfo" style="text-align: center;">
                    <c:forEach var="pc" items="${pageContents}">
                        <c:set var="dcTitle" value="${pc.cMSPages.title}"/>
                        <c:if test="${fn:containsIgnoreCase(dcTitle, 'Documents & Forms')}">
                            <p style="text-align: center;">${pc.content}</p>
                        </c:if>
                    </c:forEach> 
                </div>
            </div>
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center" style="margin-top: 15px; margin-bottom: 15px;">
                <form:form action="${pageContext.request.contextPath}/ConsumerPortal/search" commandName="documents" method="Post">
                    <div class="form-group">
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center" style="text-align: center;">
                            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center" >
                                <div class="input-group col-lg-6 col-sm-6 col-md-12 col-xs-12" style="margin:auto;float:none;">
                                    <form:input path="title" cssClass="form-control" placeholder="Search" style="font-size: 16px;border-right:0px;" onmouseout="onchab(this.id);" onfocus="changeCls(this.id)"/>
                                    <div class="input-group-btn">
                                        <button id="btnSearch" type="submit" class="btn btn-default" style="font-size: 14px;border-left:0px;"><i class="glyphicon glyphicon-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>  
                    </div> 
                </form:form>
            </div>
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="padding-left:0px;padding-right:0px;">

                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 margin-top-18">
                    <c:choose>
                        <c:when test="${fn:length(documentlist) > 0}">
                            <c:forEach var="loop" items="${documentlist}">
                                <div class="document">
                                    <c:if test="${loop.dType eq 'pdf'}">
                                        <img src="${pageContext.request.contextPath}/resources/images/pdf_1.png" style="float: left;"/>&nbsp;&nbsp;&nbsp; <span style="padding-left: 5px;"><a href="${pageContext.request.contextPath}/ConsumerPortal/download/${loop.id}">${loop.title}</a></span>
                                        <span class="filesize pull-right">${loop.fileSize}</span>
                                    </c:if>
                                    <c:if test="${loop.dType eq 'excel'}">
                                        <img src="${pageContext.request.contextPath}/resources/images/excel_1.png" style="float: left;">&nbsp;&nbsp;&nbsp; <span style="padding-left: 5px;"><a href="${pageContext.request.contextPath}/ConsumerPortal/download/${loop.id}">${loop.title}</a></span>
                                        <span class="filesize pull-right">${loop.fileSize}</span>
                                    </c:if>
                                    <c:if test="${loop.dType eq 'word'}">
                                        <img src="${pageContext.request.contextPath}/resources/images/word.png" style="float: left;"> &nbsp;&nbsp;&nbsp; <span style="padding-left: 5px;"><a href="${pageContext.request.contextPath}/ConsumerPortal/download/${loop.id}">${loop.title}</a></span>
                                        <span class="filesize pull-right">${loop.fileSize}</span>
                                    </c:if>
                                    <c:if test="${loop.dType eq 'ppt'}">
                                        <img src="${pageContext.request.contextPath}/resources/images/ppt.png" style="float: left;"> &nbsp;&nbsp;&nbsp; <span style="padding-left: 5px;"><a href="${pageContext.request.contextPath}/ConsumerPortal/download/${loop.id}">${loop.title}</a></span>
                                        <span class="filesize pull-right">${loop.fileSize}</span>
                                    </c:if>
                                    <c:if test="${loop.dType eq 'csv'}">
                                        <img src="${pageContext.request.contextPath}/resources/images/csv_1.png" style="float: left;"> &nbsp;&nbsp;&nbsp; <span style="padding-left: 5px;"><a href="${pageContext.request.contextPath}/ConsumerPortal/download/${loop.id}">${loop.title}</a></span>
                                        <span class="filesize pull-right">${loop.fileSize}</span>
                                    </c:if>
                                </div>
                            </c:forEach>
                            <span class="col-sm-12 pull-left" style="text-align: center;color:#2984b8;padding-top:5px;font-size: 12px;"><div id="viewMore" <c:if test="${fn:length(list) > fn:length(documentlist)}">onclick="location.href = '${pageContext.request.contextPath}/ConsumerPortal/documents/${fn:length(documentlist)}'"</c:if> style="color: #2984b8;cursor: pointer;display: ${fn:length(list) > fn:length(documentlist)?'block':'none'}">view more</div></span>
                        </c:when>
                        <c:otherwise>
                            <div class="document" style="color: red;">
                                <div style="text-align: center;">No document found.</div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>


        </div>
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            function changeCls(id) {
                if ($("#" + id).is(":focus")) {
                    $("#btnSearch").attr("class", "btnfocus btn btn-default");
                } else {
                    $("#btnSearch").attr("class", "btn btn-default");
                }
            }
            function onchab(id) {
                $("#btnSearch").attr("class", "btn btn-default");
                $('#' + id).blur();
            }

        </script>
    </body>
</html>

