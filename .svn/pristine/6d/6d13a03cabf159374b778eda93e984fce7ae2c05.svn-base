<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <c:if test="${brand.brandId!=null && brand.brandId!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Brand Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Brand
                    </c:if>
                    <c:if test="${brand.brandId==null || brand.brandId==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Brand Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Brand
                    </c:if>
                </div>
                <div class="heading">
                    <c:if test="${brand.brandId!=null && brand.brandId!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Brand</h1>
                    </c:if>
                    <c:if test="${brand.brandId==null || brand.brandId==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Brand</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/brand/add" commandName="brand">
                    <form:hidden path="brandId" />
                    <form:hidden path="isActive" />
                    <form:hidden path="isDelete" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createOn" />
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>


                            <br clear="all">
                        </div>


                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/brand/list'"><a href="${pageContext.request.contextPath}/brand/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#brand').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 50px;padding-bottom:20px;background:#f7f7f7;">
                            <div class="search-grid">



                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3">
                                        <label>    Brand Title:<span style="color:red">*</span></label>
                                        <form:input path="brandTitle" cssClass="form-control" onkeypress="return IsAlphaNumeric(event);" onchange="checkLength(this)"/>
                                        <span id="myspan" class="errorMessage1"><c:if test="${not empty message1}">${message1}</c:if></span>
                                        <form:errors path="brandTitle" cssClass="errorMessageValid" />
                                    </div>
                                </div>
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3">
                                        <label>Short Code(s):<span style="color:red">*</span></label>  
                                        <form:select path="shortCodeIds" cssClass="form-control selectpicker show-tick">
                                            <form:option value="" label="Please Select" />
                                            <form:options items="${shortCodeList}" itemValue="shortCode" itemLabel="shortCode"/>
                                        </form:select>
                                        <form:errors path="shortCodeIds" cssClass="errorMessageValid" />

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
            function startTimer()
            {
                window.setInterval("hideMessage()", 5000);
            }
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
            }
        </script>
    </body>
</html>
