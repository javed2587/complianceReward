<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Survey <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Survey Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Associate Questions
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Associate Question(s)</h1>
                </div> <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/survey/addQuestions" commandName="survey" cssClass="frm">
                    <form:hidden path="id"/>
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/survey/list'"><a href="${pageContext.request.contextPath}/survey/edit/${survey.id}" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="saveOrUpdate();" ><a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 55px;"> 
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
<!--                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                                                    <span class="tbl_f"><input type="checkbox" id="allQuestionsChk" onchange="checkAll(this)"/></span>
                                                </th>-->
                                                <th class="col-lg-9 col-md-9 col-sm-9 col-xs-7">
                                                    <span class="tbl_f">Question Title</span>
                                                </th>
                                                <th class="col-lg-3 col-md-3 col-sm-3 col-xs-5" style="float: right;">
                                                    <span class="tbl_f">Response Type</span>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${surveyQuestionList}" varStatus="loop">
                                                <tr class="row grid-row">
                                                    <td class="col-lg-9 col-md-9 col-sm-9 col-xs-7">
                                                        <span id="tbl_textalign">
                                                            <c:set var="associationFlag" value = "0"/>
                                                            <c:forEach var="surveyList" items="${survey.surveyQuestionList}">
                                                                <c:choose>
                                                                    <c:when test="${surveyList.surveyQuestion.id eq list.id}">
                                                                       <c:set var="associationFlag" value = "1"/>    
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:forEach>
                                                            
                                                            <form:hidden path="surveyQuestionList[${loop.index}].surveyQuestion.id" value="${list.id}"/> 
                                                            <input type="checkbox" onchange="handleCheckBox()" name="questionId" <c:if test="${associationFlag eq 1}">checked="checked"</c:if>/>
                                                        </span>
                                                        <span id="tbl_textalign">${list.title}</span>
                                                    </td>
                                                    <td class="col-lg-3 col-md-3 col-sm-3 col-xs-5" align="right">
                                                        <span id="tbl_textalign">${list.surveyResponseType.title}</span>   
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript">
            function startTimer()
            {
                window.setInterval("hideMessage()", 5000);
            }
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
            }
            function saveOrUpdate(){
                var counter = 0;
                $("input[name='questionId']").each(function() {
                    if(!$(this).is(":checked")){
                        var selectedQuestion = document.getElementById("surveyQuestionList"+counter+".surveyQuestion.id");
                        
                        $(selectedQuestion).closest("tr").remove();
                    }
                    counter++;
                });
                $("#survey").submit();
            }
            
            function handleCheckBox(){
                if($(this).is(':checked')){
                    $(this).prop("checked", false);
                } else {
                    $(this).prop("checked", true);
                } 
            }
            
            function checkAll(source){
                if($(source).is(':checked')){
                    $("input[name='questionId']").each(function(index, element) {
                        $(element).prop("checked", true);
                    });
                } else {
                    $("input[name='questionId']").each(function(index, element) {
                        $(element).prop("checked", false);
                    });
                }
            }
        </script>
    </body>
</html>
