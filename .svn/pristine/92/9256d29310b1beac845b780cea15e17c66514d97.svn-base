<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <style type="text/css">.hidden {display: none;}</style>

    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Documents Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> ${cMSDocuments.id!=null && cMSDocuments.id!=''?'Edit Document':'Add Document'} 
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;${cMSDocuments.id!=null && cMSDocuments.id!=''?'Edit Document':'Add Document'}</h1>
                </div> <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/cMSDocuments/add" commandName="cMSDocuments" enctype="multipart/form-data" method="post">
                    <form:hidden path="id" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <div class="errorMessage" id="errorMessage"> <c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            <br clear="all">
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/cMSDocuments/list'"><a href="${pageContext.request.contextPath}/cMSDocuments/list" class="btn_Color">Cancel</a></div>
                            <c:if test="${sessionBean.pmap[(52).intValue()].manage eq true}">
                                <div class="btn-group">
                                    <div class="btndrop btn-margin" onclick="$('#cMSDocuments').submit();">
                                        <a class="btn_Color">Save</a></div>
                                </div> 
                            </c:if>       
                        </div>
                        <br><br>  
                        <div class="row grid padding-top-10" style="height: auto;padding-bottom:10px;margin-bottom:40px;background:#f7f7f7;">

                            <div class="search-grid">
                                <div class="form-group fgPL-zero">

                                    <div class="col-sm-3">
                                        <label>Title:<span style="color:red">*</span></label>
                                        <form:input path="title" onkeypress="return IsAlphaNumeric(event);" cssClass="form-control2"/>
                                        <div class="errorMessage">${message1}</div> 
                                    </div>
                                </div>
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3">
                                        <label>Type:<span style="color:red">*</span></label>
                                        <form:select cssClass="form-control selectpicker show-tick" path="dType">
                                            <form:option value="">Please Select</form:option> 
                                            <form:option value="word">Word</form:option> 
                                            <form:option value="excel">Excel &nbsp;<img src="${pageContext.request.contextPath}/resources/images/excel.png" /></form:option> 
                                            <form:option value="pdf">PDF &nbsp;<i class="fa fa-file-pdf-o"></i></form:option> 
                                            <form:option value="ppt">PPT &nbsp;<i class="fa fa-file-powerpoint-o"></i></form:option> 
                                            <form:option value="csv">CSV &nbsp;<i class="fa fa-file-image-o"></i></form:option>  
                                        </form:select>  
                                        <div class="errorMessage">${message2}</div>
                                    </div>
                                </div>   

                                <div class="form-group fgPL-zero"> 
                                    <div class="col-sm-3 col-xs-11" id="textboxgroup">
                                        <label>Upload:<span style="color:red">*</span></label><br>
                                        <form:input path="attachment" type="file"/>
                                        <div class="errorMessage">${message3}</div>
                                    </div>   
                                </div>
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3" id="textboxgroup">
                                    </div>      
                                </div>    
                            </div>
                        </div>
                    </form:form>




                </div> <!-- /content -->
                <jsp:include page="./inc/footer.jsp" />
            </div> <!-- /wrapper -->
            <script type="text/javascript">
                function isNumber(evt) {
                    evt = (evt) ? evt : window.event;
                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                        return false;
                    }
                    return true;
                }
                $(function() {
                    setTimeout(function() {
                        $("#errorMessage").hide('blind', {}, 500)
                        $("#message").hide('blind', {}, 500)
                    }, 5000);
                });
                $('Input').bind("paste", function(e) {
                    e.preventDefault();
                });
            </script>
    </body>
</html>

