<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <c:if var="isSent" test="${survey.sentOn ne null}"></c:if>
                    <div class="breadcrumbs">
                    <c:if test="${survey.id!=null && survey.id!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Survey Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit
                    </c:if>
                    <c:if test="${survey.id==null || survey.id==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Survey Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add New
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${survey.id!=null && survey.id!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Survey</h1>
                    </c:if>
                    <c:if test="${survey.id==null || survey.id==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Survey</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/survey/add" commandName="survey" cssClass="frm" enctype="multipart/form-data" method="post" onsubmit="return validateField()">
                    <form:hidden path="id"/>
                    <form:hidden path="createdBy"/>
                    <form:hidden path="createdOn"/>
                    <form:hidden path="associated"/>
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/survey/list'">
                                <a href="${pageContext.request.contextPath}/survey/list" class="btn_Color">Cancel</a>
                            </div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#survey').submit();" ><a class="btn_Color">Save</a></div>
                            </div>

                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 10px; background:#f7f7f7;padding-top: 10px;">
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-5 padd-left-1-ipad" style="padding-left:4px;" >
                                        <label>Survey Title:<span style="color:red" >*</span></label>
                                        <form:input path="title" cssClass="form-control" maxlength="100" onkeypress="return IsAlphaNumeric(event)"/>
                                        <div id="titleReq" class="errorMessage"></div>
                                    </div>
                                    <div class="col-sm-3 padding-top-10">
                                        <label>Status:</label><br>
                                        <form:radiobutton path="status" value="Active"/> <label>Active</label> <form:radiobutton path="status" value="InActive"/> <label>InActive</label>
                                    </div>
                                    <div class="col-sm-10 " style="padding-top:15px; margin-left: -4px;">
                                        <label>Question:<span style="color:red;" >*</span></label>                             
                                    </div>
                                    <div class="table-box" style="float: left; width: 100%; padding-top:7px;">
                                        <table   class="display addsurv" id="example1" class="table-responsive" class="display table" style=" border-color: #c2c8d1;margin: 4px;  width: 98.5%; padding-right: 1px; " border="1px" >
                                            <thead>
                                                <tr class="row grid-header"  >
                                                    <th class="col-sm-1" >
                                                        <span class="tbl_f">Associate</span>
                                                    </th>
                                                    <th class="col-sm-9">                                                    
                                                        <span class="tbl_f">Question</span>
                                                    </th>
                                                    <th class="col-sm-2 " style=" padding-right: 3px;">
                                                        <span class="tbl_f">Response Type</span>
                                                    </th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="list" items="${surveyQuestionList}" varStatus="loop">
                                                    <tr class="row grid-row">
                                                        <td>
                                                            <span id="tbl_textalign">
                                                                <c:set var="associationFlag" value = "0"/>
                                                                <c:forEach var="surveyList" items="${survey.surveyQuestionList}">
                                                                    <c:choose>
                                                                        <c:when test="${surveyList.surveyQuestion.id eq list.id}">
                                                                            <c:set var="associationFlag" value = "1"/>    
                                                                        </c:when>
                                                                    </c:choose>
                                                                </c:forEach>

                                                            </span>
                                                            <form:hidden id="surveyQuestionId${loop.index}" path="surveyQuestionList[${loop.index}].surveyQuestion.id" value="${list.id}"/> 
                                                            <form:checkbox id="chkQuestion${loop.index}" path="surveyQuestionList[${loop.index}].questionAssociate" onclick="handleCheckBox(${loop.index})" cssClass="chkQuestion" checked="${associationFlag eq 1?'checked':''}" value="${associationFlag eq 1?1:0}"/>
                                                        </td>
                                                        <td>
                                                            <span id="tbl_textalign">${list.title}</span>
                                                        </td>
                                                        <td>
                                                            <span id="tbl_textalign">${list.surveyResponseType.title}</span>   
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div id="questionReq" class="errorMessage"></div>
                                    </div>   <%--tablebox--%>
                                </div><%--form-group--%>
                            </div>  <%--searchgrid--%>
                        </div><%--rowgrid--%>
                    </div><%--wrap--%>
                </form:form>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript">
            function handleCheckBox(index) {
                if ($("#chkQuestion" + index).is(':checked')) {
                    $("#chkQuestion" + index).val(1);
                } else {
                    $("#chkQuestion" + index).val(0);
                }
            }
            function validateField() {
                var questionId = document.getElementsByClassName("chkQuestion");
                var empty = true;
                if ($("#title").val().trim() === "") {
                    $("#titleReq").text("Required");
                    empty = false;
                }
                for (var j = 0; j < questionId.length; j++) {
                    var tagname = "surveyQuestionList" + questionId[j] + ".questionAssociate";
                    if ($('input[class^=chkQuestion]:checked').length <= 0) {
                        $("#questionReq").text("Required");
                        empty = false;
                    }
                }

                return empty;
            }
        </script>
    </body>
</html>