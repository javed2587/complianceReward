<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <c:if test="${ingredients.id!=null && ingredients.id!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Ingredients Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Ingredients
                    </c:if>
                    <c:if test="${ingredients.id==null || ingredients.id==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Ingredients Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Ingredients
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${ingredients.id!=null && ingredients.id!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Ingredients</h1>
                    </c:if>
                    <c:if test="${ingredients.id==null || ingredients.id==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Ingredients</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/ingredient/add" commandName="ingredients" cssClass="frm">
                    <form:hidden path="id" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />   
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/ingredient/list'"><a href="${pageContext.request.contextPath}/ingredient/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#ingredients').submit();" ><a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>

                        <div class="row grid" style="height: auto;margin-bottom: 40px; padding-bottom:17px;background:#f7f7f7;">
                            <div class="search-grid">
                                <div class="form-group">
                                    <div class="col-sm-3">

                                        <label>Ingredient Title: <span style="color:red">*</span></label>

                                        <form:input path="name" cssClass="form-control" maxlength="100" onkeypress="return IsAlphaNumeric(event);"/>
                                        <form:errors path="name" cssClass="errorMessageValid" id="error" />
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

