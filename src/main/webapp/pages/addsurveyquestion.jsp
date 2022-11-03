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
                <div class="breadcrumbs">
                    <c:if test="${surveyQuestion.id!=null && surveyQuestion.id!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Question Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit
                    </c:if>
                    <c:if test="${surveyQuestion.id==null || surveyQuestion.id==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Question Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add New
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${surveyQuestion.id!=null && surveyQuestion.id!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Question</h1>
                    </c:if>
                    <c:if test="${surveyQuestion.id==null || surveyQuestion.id==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Question</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/surveyQuestion/add" commandName="surveyQuestion" cssClass="frm">
                    <form:hidden path="id"/>
                    <form:hidden path="CreatedBy" />
                    <form:hidden path="CreatedOn" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/surveyQuestion/list'"><a href="${pageContext.request.contextPath}/surveyQuestion/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#surveyQuestion').submit();" ><a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>

                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom:17px;background:#f7f7f7;">
                            <!--                          <h3 class="demo">Add Industry</h3>  -->
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3 margin-bottom-zero" style="margin-bottom: 15px;">
                                        <label>Response Type:<span style="color:red">*</span></label>
                                        <form:select path="surveyResponseType.id" cssClass="form-control selectpicker show-tick" id="selectedFolder">
                                            <form:option value="0" label="Please Select" />
                                            <form:options items="${surveyResponseTypeList}" itemValue="id" itemLabel="title"/>
                                        </form:select> 
                                        <form:errors path="surveyResponseType.id" cssClass="errorMessage"/>
                                    </div> 
                                    <div class="col-sm-9"></div>
                                </div> 
                                    
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-12">
                                        <label>Question:<span style="color:red">*</span></label>
                                        <form:textarea path="title" cssClass="form-control" cols="3" maxlength="255"/>
                                        <form:errors path="title" cssClass="errorMessage"/>
                                    </div>  
                                </div>  
                            </div>
                        </div>
                    </div>
                </form:form>




            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
    </body>
</html>
