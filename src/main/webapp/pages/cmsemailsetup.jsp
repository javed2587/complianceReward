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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Emails Setup
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Program Emails</h1>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/email/add" cssClass="frm" commandName="cMSEmailContent">
                    <form:hidden id="id" path="id"/>
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/email/add'"><a href="${pageContext.request.contextPath}/email/add" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#cMSEmailContent').submit();" ><a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>

                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom:25px;background:#f7f7f7;">
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-lg-6 col-sm-12 padd-left-1-ipad">
                                        <label class="col-lg-6 col-sm-12" style="padding-left: 0px; margin-left: -4px;">Email Type:<span style="color:red">*</span></label>
                                        <form:select path="cMSEmailType.id" cssClass="form-control selectpicker show-tick" onchange="getRecord(this.value)">
                                            <form:option value="0">Select One</form:option>
                                            <form:options items="${cmseTypelist}" itemValue="id" itemLabel="title"/>
                                        </form:select>
                                        <div class="errorMessage">${message1}</div>
                                    </div> 
                                </div> 
                                <div class="form-group fgPL-zero">
                                    <div class="col-lg-12 col-sm-12 padd-left-1-ipad">
                                        <label class="col-lg-6 col-sm-12"style="padding-right: 10px; margin-left: -5px; padding-top: 15px;">Subject:<span style="color:red">*</span></label>
                                        <form:input path="subject" cssClass="form-control"/>
                                        <div class="errorMessage">${message2}</div>
                                    </div> 
                                </div>

                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-12 col-lg-12 padd-left-1-ipad" style="padding-right: 10px; padding-top: 10px;">
                                        <label style="margin-top: 10px;">Content:</label>
                                        <form:textarea id="emailBody" path="emailBody" cssClass="form-control cust textarea summernote" cssStyle="height:20%; background-color:white;" onchange="copyValueInEmailBody('emailBody')"/>
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
            function getRecord(id) {
                if (id > 0) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/email/getRecord/" + id,
                        dataType: "json",
                        success: function(data) {
                            $("#id").val(data.id);
                            $("#subject").val(data.subject);
                            $('.summernote').code(data.emailBody);
                        }, error: function(e) {
                            alert('Error while request...' + e);
                        }
                    });
                }
            }
        </script>
    </body>
</html>

