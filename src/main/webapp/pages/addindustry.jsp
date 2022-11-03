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
                    <c:if test="${industry.industryId!=null && industry.industryId!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Industry Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Industry
                    </c:if>
                    <c:if test="${industry.industryId==null || industry.industryId==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Industry Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Industry
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${industry.industryId!=null && industry.industryId!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Industry</h1>
                    </c:if>
                    <c:if test="${industry.industryId==null || industry.industryId==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Industry</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/industry/add" commandName="industry" cssClass="frm">
                    <form:hidden path="industryId" />
                    <form:hidden path="isActive" />
                    <form:hidden path="isDelete" />
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
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/industry/list'"><a href="${pageContext.request.contextPath}/industry/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#industry').submit();" ><a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>

                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom:17px;background:#f7f7f7;">
                            <!--                          <h3 class="demo">Add Industry</h3>  -->
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3">

                                        <label>Industry Title: <span style="color:red">*</span></label>

                                        <form:input path="industryTitle" cssClass="form-control" maxlength="100" onkeypress="return IsAlphaNumeric(event);" onchange="checkLength(this)"/>
                                        <span id="myspan" class="errorMessage1"><c:if test="${not empty message1}">${message1}</c:if></span>
                                        <form:errors path="industryTitle" cssClass="errorMessageValid" id="error" />
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
            function startTimer()
            {
                window.setInterval("hideMessage()", 5000);
            }
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
            }
            function checkLength(inputtxt) {
                if (inputtxt.value.trim() != "" && inputtxt.value.length < 4) {
                    document.getElementById("myspan").style.display = "block";
                    document.getElementById("myspan").innerHTML = "Minimum length 4 character";
                    return false;
                }
                else {
                    document.getElementById("myspan").style.display = "none";
                    return true;
                }
            }

            $(document).ready(function() {
                $(".frm :text").focus();
            });
        </script>
    </body>
</html>
