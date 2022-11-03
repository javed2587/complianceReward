<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body onload="setFocus()">
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
               <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <c:if test="${folder.folderId!=null && folder.folderId!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" />Folder Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Folder
                    </c:if>
                    <c:if test="${folder.folderId==null || folder.folderId==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Folder Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Folder
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${folder.folderId!=null && folder.folderId!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Folder</h1>
                    </c:if>
                    <c:if test="${folder.folderId==null || folder.folderId==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Folder</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/folder/add" commandName="folder" cssClass="frm">
                    <form:hidden path="folderId" />
                    <form:hidden path="isActive" />
                    <form:hidden path="isDelete" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />   
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">

                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/folder/list'"><a href="${pageContext.request.contextPath}/folder/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group" onclick="$('#folder').submit();">
                                <div class="btndrop btn-margin"><a class="btn_Color">Save</a></div>

                            </div>


                        </div>
                        <br><br>
                        <div class="row grid" style="height: auto;margin-bottom: 40px; padding-bottom: 20px;background:#f7f7f7;"> 
                            <div class="search-grid">
                                <div class="form-group addFormBackground">
                                    <div class="col-sm-3">
                                        <label>  Folder Title:<span style="color:red">*</span></label> 
                                        <form:input path="folderName" cssClass="form-control" maxlength="50" onkeypress="return IsAlphaNumeric(event);" onchange="checkLength(this);"/>
                                        <span id="myspan" class="errorMessage1"><c:if test="${not empty message1}">${message1}</c:if></span>
                                        <form:errors path="folderName" cssClass="errorMessageValid" />
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
            function setFocus()
            {

                $(".frm :text").each(function() {
                    if ($(this).val() == "")
                    {
                        $(this).focus();
                        return false;
                    }
                    else {
                        $(this).focus();
                        return false;
                    }
                });
                window.setInterval("hideMessage()", 6000); 
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
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
            }
        </script>
    </body>
</html>
