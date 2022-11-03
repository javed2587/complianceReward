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
                    <c:if test="${surveyEmailTemplate.id!=null && surveyEmailTemplate.id!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Email Template <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit
                    </c:if>
                    <c:if test="${surveyEmailTemplate.id==null || surveyEmailTemplate.id==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Email Template <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add New
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${surveyEmailTemplate.id!=null && surveyEmailTemplate.id!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Template</h1>
                    </c:if>
                    <c:if test="${surveyEmailTemplate.id==null || surveyEmailTemplate.id==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Template</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/surveyEmailTemplate/add" cssClass="frm" commandName="surveyEmailTemplate">
                    <form:hidden path="id"/>
                    <form:hidden path="createdBy"/>
                    <form:hidden path="createdOn"/>
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/surveyEmailTemplate/list'"><a href="${pageContext.request.contextPath}/surveyEmailTemplate/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#surveyEmailTemplate').submit();" ><a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>

                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom:17px;background:#f7f7f7;">
                            <!--                          <h3 class="demo">Add Industry</h3>  -->
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-lg-6 col-sm-12 padd-left-1-ipad">
                                        <label class="col-lg-6 col-sm-12" style="padding-left: 0px;">Template Title:<span style="color:red">*</span></label>
                                        <form:input path="title" cssClass="form-control" maxlength="100" onkeypress="return IsAlphaNumeric(event);"/>
                                        <form:errors path="title" cssClass="errorMessageValid"/>
                                    </div> 
                                    <!--                                    <div class="col-lg-6 col-sm-6">
                                                                            <label class="col-lg-6 col-sm-6">&nbsp;&nbsp;&nbsp;</label>
                                                                        </div> -->
                                </div> 


                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-12 col-lg-12 padd-left-1-ipad" style="padding-right: 10px;">
                                        <label style="margin-top: 12px;">Template:</label>
                                        <form:textarea path="detail" cssClass="form-control cust textarea summernote" cssStyle="height:20%; background-color:white;" onchange="copyValueInEmailBody('detail')"/>
                                        <form:errors path="detail" cssClass="errorMessageValid"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>




            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript">
            $.getScript('//cdnjs.cloudflare.com/ajax/libs/summernote/0.5.1/summernote.min.js', function() {
                $('.summernote').summernote();
                document.getElementsByClassName("note-editable")[0].style.backgroundColor = "white";
            });
            function copyValueInEmailBody(id) {
                $('#' + id).each(function() {
                    var $textArea = $(this);

                    $textArea.summernote({
                        onkeyup: function(e) {
                            $textArea.val($(this).code());
                            $textArea.change(); //To update any action binded on the control
                        }
                    });
                });
            }
        </script>
    </body>
</html>
