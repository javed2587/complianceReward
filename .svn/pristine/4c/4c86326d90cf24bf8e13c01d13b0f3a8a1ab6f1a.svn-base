<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0"> 
                <jsp:include page="./inc/newMenu.jsp" />

                <div class="breadcrumbs">
                    <c:if test="${role.roleId == null || role.roleId == 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> 
                        Manage Roles <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Role
                    </c:if>
                    <c:if test="${role.roleId != null &&  role.roleId != 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> 
                        Manage Roles <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Role
                    </c:if>
                </div>
                <div class="heading">
                    <c:if test="${role.roleId == null || role.roleId == 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Role</h1>
                    </c:if>
                    <c:if test="${role.roleId != null &&  role.roleId != 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Role</h1>
                    </c:if>
                </div> <!-- /header -->


                <form:form action="${pageContext.request.contextPath}/role/add" method="post" commandName="role" prependId="false" onsubmit="return validateLength()">
                    <form:hidden path="roleId" />
                    <form:hidden path="createdOn" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px;">
                        <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage">${errorMessage}</div></c:if>

                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/role/list'"><a href="${pageContext.request.contextPath}/role/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group" onclick="$('#role').submit();">
                                <div class="btndrop btn-margin"><a class="btn_Color">Save</a></div>

                            </div>
                        </div>
                        <br><br>

                        <div class="row grid" style="height: auto;">
                            <div class="search-grid">


                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-top-10 padding-bottom-10" style="padding-bottom:20px;background:#f7f7f7;">
                                    <div class="form-group fgPL-zero" style="position:relative;top:15px;">
                                        <div class="col-sm-3">
                                            <label>Role Title:<span style="color:red">*</span></label>
                                            <form:input path="roleTitle" onkeypress="return IsAlphaNumeric(event);" cssClass="form-control" onchange="return validateLength()" id="roleTitle" /> 
                                            <form:errors path="roleTitle" cssClass="errorMessageValid" id="rolemsg"/>    
                                            <span id="myspan" class="errorMessage1" style="display: none;">Minimum length 4 character</span>

                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Status:</label><br>
                                            <form:radiobutton path="isActive" value="Yes"/>&nbsp;Active&nbsp;&nbsp;
                                            <form:radiobutton path="isActive" value="No"/>&nbsp;InActive
                                        </div>
                                    </div>  
                                    <div class="form-group hidden-xs">
                                        <div class="col-sm-6"></div>
                                    </div>
                                    <!-- <div class="form-group">
                                         <div class="col-sm-6" style="margin-top:15px;">
                                             <label> Description:</label>    
 
                                    <form:textarea path="roleDescription" cssClass="form-control" rows="4" cols="100" />
                                </div>
                            </div>-->     
                                    
                                </div>   
                            </div>
                        </div>
                    </div>
                </form:form>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script>
            function hideErrorMsg() {
                document.getElementById('rolemsg').style.display = 'none';
            }
            function validateLength(){
                document.getElementById("myspan").style.display = "none";
                if(document.getElementById("roleTitle").value != "" && document.getElementById("roleTitle").value.length < 4){
                    document.getElementById("myspan").style.display = "block";
                    return false;
                }
                else{
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
