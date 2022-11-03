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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" />  Contents Setup
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Contents</h1>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/CmsPageContent/add" cssClass="frm" commandName="cMSPageContent">
                    <form:hidden id="id" path="id"/>
                    <form:hidden id="createdBy" path="createdBy"/>
                    <form:hidden id="createdOn" path="createdOn"/>
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/CmsPageContent/add'"><a href="${pageContext.request.contextPath}/CmsPageContent/add" class="btn_Color">Cancel</a></div>
                            <c:if test="${sessionBean.pmap[(52).intValue()].manage eq true}">
                                <div class="btn-group">
                                    <div class="btndrop btn-margin" onclick="$('#cMSPageContent').submit();" ><a class="btn_Color">Save</a></div>
                                </div>
                            </c:if>
                        </div>
                        <br><br>

                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom:17px;background:#f7f7f7;">
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-lg-6 col-sm-12 padd-left-1-ipad">
                                        <label class="col-lg-6 col-sm-12" style="padding-left: 0px;">Page Title:<span style="color:red">*</span></label>
                                        <form:select path="cMSPages.id" cssClass="form-control selectpicker show-tick" onchange="getRecord(this.value)">
                                            <form:option value="0">Select One</form:option>
                                            <form:options items="${cmsPagelist}" itemValue="id" itemLabel="title"/>
                                        </form:select>
                                        <div class="errorMessage">${message1}</div>
                                    </div> 
                                </div> 


                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-12 col-lg-12 padd-left-1-ipad" style="padding-right: 10px;">
                                        <label style="margin-top: 12px;">Page Content:</label>
                                        <form:textarea id="content" path="content" cssClass="form-control cust textarea summernote" cssStyle="height:20%; background-color:white;" onchange="copyValueInEmailBody('content')"/>
                                        <form:errors path="content" cssClass="errorMessageValid"/>
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
            $.getScript('//cdnjs.cloudflare.com/ajax/libs/summernote/0.5.1/summernote.min.js', function () {
                $('.summernote').summernote();
                document.getElementsByClassName("note-editable")[0].style.backgroundColor = "white";
            });
            function copyValueInEmailBody(id) {
                $('#' + id).each(function () {
                    var $textArea = $(this);

                    $textArea.summernote({
                        onkeyup: function (e) {
                            $textArea.val($(this).code());
                            $textArea.change(); //To update any action binded on the control
                        }
                    });
                });
            }
            function getRecord(id) {
                if (id > 0) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/CmsPageContent/getRecord/" + id,
                        dataType: "json",
                        success: function (data) {
                            $("#id").val(data.id);
                            $('.summernote').code(data.content);
                        }, error: function (e) {
                            alert('Error while request...' + e);
                        }
                    });
                }
            }
        </script>
    </body>
</html>
