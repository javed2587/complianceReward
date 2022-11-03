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
                    <c:if test="${messageType.id.messageTypeId!=null && messageType.id.messageTypeId!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Message Type Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Message Type
                    </c:if>
                    <c:if test="${messageType.id.messageTypeId==null || messageType.id.messageTypeId==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Message Type Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Message Type
                    </c:if>
                </div>
                <div class="heading">
                    <c:if test="${messageType.id.messageTypeId!=null && messageType.id.messageTypeId!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Message Type</h1>
                    </c:if>
                    <c:if test="${messageType.id.messageTypeId==null || messageType.id.messageTypeId==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Message Type</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/messageType/add" commandName="messageType">
                    <form:hidden path="id.messageTypeId" />
                    <form:hidden path="isActive" />
                    <form:hidden path="isDelete" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" /> 
                    <c:if test="${messageType.id.messageTypeId!=null && messageType.campaignMessageses !=null && not empty  messageType.campaignMessageses}">
                        <form:hidden path="type" /> 
                        <form:hidden path="id.folderId" /> 
                        <form:hidden path="associatedType" />
                    </c:if>
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin"  onclick="location.href = '${pageContext.request.contextPath}/messageType/list'"><a href="${pageContext.request.contextPath}/messageType/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#messageType').submit();">
                                    <a class="btn_Color">Save</a></div>

                            </div>
                        </div>
                        <br><br>
                        <div class="row grid addFormBackground" style="height: auto;margin-bottom: 40px;background:#f7f7f7;">  
                            <div class="search-grid">

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-bottom: 20px;padding-left:0px;">




                                    <div class="form-group" style="position: relative; top:15px;">
                                        <div class="col-sm-3 padd-left-zero-ipad">
                                            <label>  Message Title:<span style="color:red">*</span></label>
                                            <form:input path="messageTypeTitle" cssClass="form-control" onkeypress="return IsAlphaNumeric(event);" onchange="checkLength(this)"/>
                                            <span id="myspan" class="errorMessage1"><c:if test="${not empty message2}">${message2}</c:if></span>
                                            <form:errors path="messageTypeTitle" cssClass="errorMessageValid" />
                                        </div>  
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label>Message Type:<span style="color:red">*</span></label>
                                            <form:select path="type" cssClass="form-control selectpicker show-tick" id="selectedFolder" disabled="${messageType.associatedType}">
                                                <form:option value="" label="Please Select" />
                                                <form:option value="SMS" label="SMS" />
                                            </form:select>
                                            <form:errors path="type" cssClass="errorMessageValid" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">   
                                            <label> Communication Folder:<span style="color:red">*</span></label>
                                            <form:select path="id.folderId" cssClass="form-control selectpicker show-tick" id="selectedFolder" disabled="${messageType.associatedType}">
                                                <form:option value="0" label="Please Select" />
                                                <form:options items="${folderList}" itemValue="folderId" itemLabel="folderName"/>
                                            </form:select>  
                                            <c:if test="${not empty message1}"><div class="errorMessageValid1">${message1}</div></c:if>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label>Sort Order<span style="color:red">*</span></label>
                                            <form:input path="sortOrder" cssClass="form-control" onkeypress="return IsDigit(event);" maxlength="2"/>
                                            <form:errors path="sortOrder" cssClass="errorMessageValid" />  
                                        </div>
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
                } else {
                    document.getElementById("myspan").style.display = "none";
                    return true;
                }
            }
            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
            }

            function startTimer() {
                window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
            }
        </script>
    </body>
</html>
