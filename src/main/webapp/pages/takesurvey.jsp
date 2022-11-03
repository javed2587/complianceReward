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
                    <form:hidden path="id"/>
                    <form:hidden path="communicationId" value="${communicationId}"/>
                    <form:hidden id="userResponse" path="userResponse"/>
                    <div class="page-message messageheading takesurveymsgs">
                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="" class="">${errorMessage}</div></c:if>
                        </div> <br/>
                        <div class="wrp clearfix takesurveymsg" style="padding-left: 15px;padding-right: 15px;">
                            <div class="table-box">
                            <c:forEach var="list" items="${surveyQuestionList}" varStatus="questionCounter">
                                <div class="row surveygrid-header col-lg-12 col-md-12 col-sm-12 col-xs-12 takesurveyh" style="color: #2071b6;">  
                                    <form:hidden path="surveyQuestionList[${questionCounter.index}].surveyQuestion.id"/>
                                    ${list.surveyQuestion.title}
                                </div>
                                <c:forEach var="response" items="${list.surveyQuestion.surveyResponseType.surveyResponseDetails}" varStatus="responseCounter">
                                    <div class="row grid-row col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background:#f7f7f7;">&nbsp;&nbsp;
                                        <form:radiobutton name="responseId${questionCounter.index}" path="surveyQuestionList[${questionCounter.index}].surveyQuestion.selectedResponse" value="${response.id}" onclick="insertValue()"/>
                                        <span id="">${response.title}</span><br/>
                                    </div>
                                </c:forEach>
                            </c:forEach>
                        </div>

                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 17px; padding-bottom:17px;" align="center">
                            <div></div>
                            <div class="btndrop" align="center">
                                <a class="btn_Color" onclick="$('#survey').submit()">Submit</a>
                            </div>
                            <div></div>
                        </div>
                    </form:form>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript">
            function insertValue()
            {
                var hidden_field = document.getElementById('userResponse');
                hidden_field.value = 'response';

            }
        </script>
    </body>
</html>

