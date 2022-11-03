<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload = "startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0"> 
                <jsp:include page="./inc/menu.jsp" />

                <div class="breadcrumbs">
                    <c:if test="${widgetUser.widgetUserId == null || widgetUser.widgetUserId == 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> 
                        Widget Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Widget
                    </c:if>
                    <c:if test="${widgetUser.widgetUserId != null &&  widgetUser.widgetUserId != 0}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> 
                        Widget Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Widget
                    </c:if>
                </div>
                <div class="heading">
                    <c:if test="${widgetUser.widgetUserId == null || widgetUser.widgetUserId == 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Widget</h1>
                    </c:if>
                    <c:if test="${widgetUser.widgetUserId != null &&  widgetUser.widgetUserId != 0}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Widget</h1>
                    </c:if>
                </div> <!-- /header -->


                <form:form action="${pageContext.request.contextPath}/widgetUser/add" commandName="widgetUser" onsubmit="return validatePassword()">
                    <form:hidden path="widgetUserId" />
                    <form:hidden path="createdOn" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="isActive" />
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <div class="errorMessage" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>

                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/widgetUser/list'"><a href="${pageContext.request.contextPath}/widgetUser/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group" onclick="$('#widgetUser').submit();">
                                <div class="btndrop btn-margin"><a class="btn_Color">Save</a></div>
                            </div> 
                        </div>
                        <br><br>

                        <div class="row grid" style="height: auto;margin-bottom:70px;">
                            <div class="search-grid">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-top-10 padding-bottom-10" style="background:#f7f7f7;">
                                    <div class="form-group fgPL-zero" style="position:relative;top:15px;">
                                        <div class="col-sm-3">
                                            <label>User Name:<span style="color:red">*</span></label>
                                            <form:input path="userName" cssClass="form-control" maxlength="20" id="txtUserName" title="This must be a alphabets characters" onchange="return validatePassword()" /> 
                                            <span id="myspan1" class="errorMessage1" style="display: none;">Minimum length 5 characters</span>
                                            <form:errors path="userName" cssClass="errorMessageValid" />
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Password:<span style="color:red">*</span></label><br>
                                            <form:input id="password" path="password" cssClass="form-control" maxlength="20" onchange="return validatePassword()"/> 
                                            <form:errors path="password" cssClass="errorMessageValid" />
                                            <span id="myspan" class="errorMessage1" style="display: none;">Password cannot less than 8 characters</span>
                                        </div>
                                    </div>

                                    <div class="form-group fgPL-zero">

                                        <div class="col-sm-3">
                                            <label>Campaign Title:<span style="color:red">*</span></label>
                                            <form:select path="campaignId" cssClass="form-control selectpicker show-tick">
                                                <form:option value="" label="Please Select" />
                                                <form:options items="${campaignlist}" itemValue="campaignId" itemLabel="campaignName"/>
                                            </form:select>
                                            <form:errors path="campaignId" cssClass="errorMessageValid" />
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Validate IP:</label><br>  
                                            <form:checkbox path="validateIp" value="Yes"/>
                                        </div>
                                    </div>
                                    <div class="form-group fgPL-zero" style="clear:both;">
                                        <div class="col-sm-3" style="margin-top:15px;"> 
                                            <label>IP Address(s):</label><br>  
                                            <c:forEach items="${widgetUser.widgetUserIpaddresseses}" varStatus="loop">
                                                <div id="widgetUserIpaddresseses${loop.index}" <c:if test="${widgetUser.widgetUserIpaddresseses[loop.index].remove eq 1}">class="hidden"</c:if> <c:if test="${loop.index eq 0}">style="margin-bottom:6px;"</c:if>>
                                                    <c:set var="style" value="float:left;margin-top:5px;"></c:set>
                                                    <c:set var="frmstyle" value="form-control3"></c:set>
                                                    <c:if test="${loop.index eq 0}">
                                                        <c:set var="style" value=""></c:set>
                                                        <c:set var="frmstyle" value="form-control3"></c:set>
                                                    </c:if>

                                                    <form:input path="widgetUserIpaddresseses[${loop.index}].ipAddress" cssClass="${frmstyle}" cssStyle="${style}" maxlength="15" onblur="isIP(this)" onclick="makeEmpty(this)" onkeypress="return IsAlphaNumeric(event)"/> 
                                                    <form:hidden path="widgetUserIpaddresseses[${loop.index}].remove" value="${widgetUserIpaddresseses[loop.index].remove}" />
                                                    <c:if test="${loop.index ne 0}">
                                                          
                                                        <a href="#" class="remove btn-margin-ipad btn-margin-ipad-remove remove-button fa fa-minus-circle btn-minus-1" data-index="${loop.index}"></a>
                                                        </c:if>

                                                </div>
                                            </c:forEach> 
                                            <c:if test="${empty widgetUser.widgetUserIpaddresseses}" >                                                
                                                <form:input path="widgetUserIpaddresseses[0].ipAddress" cssClass="form-control3 margin-bottom-zero float-left" onblur="isIP(this)" maxlength="15" onclick="makeEmpty(this)" cssStyle="margin-bottom:6px;" onkeypress="return IsAlphaNumeric(event)"/>                                                   
                                                <form:hidden path="widgetUserIpaddresseses[0].remove" value="${validResponses[0].remove}" />
                                            </c:if>
                                            
                                            <a id="add" class="fa fa-plus-circle" href="#" style="position:absolute;top:26px;right: 0px;"></a>
                                        </div>
                                    </div>  
                                    <div class="form-group hidden-xs" style="clear:both;">
                                        <div class="col-sm-4"></div>
                                    </div>  

                                    <div class="form-group hidden-xs">
                                        <div class="col-sm-4"></div>
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
            $(function() {
                var index = ${fn:length(widgetUser.widgetUserIpaddresseses)};
                if (index == 0) {
                    index = 1;
                }
                $("#add").off("click").on("click", function() {
                    $(this).before(function() {
                        for (var i = 0; i <= index; i++) {
                            if ($("#widgetUserIpaddresseses" + i + "\\.ipAddress").val() == '') {
                                document.getElementById("errorMessage").style.display = "block";
                                document.getElementById("errorMessage").innerHTML = "IP Address value required";
                                return;
                            }
                        }
                        var html = '<div id="widgetUserIpaddresseses' + index + '" class="">';
                        html += '<input class="form-control3" type="text" id="widgetUserIpaddresseses' + index + '.ipAddress" name="widgetUserIpaddresseses[' + index + '].ipAddress" style="float:left;margin-top:6px;" maxlength="15" onclick="makeEmpty(this)" onblur="isIP(this)" onkeypress="return IsAlphaNumeric(event)" />';
                        html += '<input type="hidden" id="widgetUserIpaddresseses' + index + '.remove" name="widgetUserIpaddresseses[' + index + '].remove" value="0"/>';
                        html += '<a href="#" class="remove btn-margin-ipad btn-margin-ipad-remove remove-button fa fa-minus-circle btn-minus-1" data-index="' + index + '"></a> ';
                        html += "</div>";
                        $("#widgetUserIpaddresseses" + index).show();
                        index++;
                        return html;
                    });

                    return false;
                });
                $('body').on('click', '.remove', function() {
                    var index2remove = $(this).data("index");
                    $("#widgetUserIpaddresseses" + index2remove + '\\.ipAddress').val("192.168.1.1");
                    $("#widgetUserIpaddresseses" + index2remove + '\\.remove').val("1");
                    $("#widgetUserIpaddresseses" + index2remove).hide();
                    return false;
                });
            });
            function isIP(obj) {
                var ary = obj.value.split(".");
                var ip = true;
                for (var i in ary) {
                    ip = (!ary[i].match(/^\d{1,3}$/) || (Number(ary[i]) > 255)) ? false : ip;
                }
                ip = (ary.length != 4) ? false : ip;
                if (obj.value != "") {

                    if (!ip) {    // the value is NOT a valid IP address
                        obj.value = "Invalid IP address";
                        //                                obj.select();
                    } else {
                        obj.style.background = "";
                    } // the value IS a valid IP address
                }
            }
            function makeEmpty(obj) {
                if (obj.value == "Invalid IP address") {
                    obj.value = "";
                }
            }
            $(function() {
                $('#txtUserName').keydown(function(e) {
                    if (e.altKey) {
                        e.preventDefault();
                    } else {
                        var key = e.keyCode;
                        if (!((key == 8) || (key == 32) || (key == 9) || (key == 13) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90) || (key >= 48 && key <= 57))) {
                            e.preventDefault();
                        }
                    }
                });
            });
            function validatePassword()
            {
                var res = true;
                document.getElementById("myspan1").style.display = "none";
                document.getElementById("myspan").style.display = "none";
                var passw1 = /^(?=.*[0-9])(?=.*[!@#$%^._&*])[a-zA-Z0-9!@#$%^&._*]{8,25}$/;
                if (document.getElementById("password").value != "" && document.getElementById("password").value.length < 8)
                {
                    document.getElementById("myspan").style.display = "block";
                    if (document.getElementById("myspan").innerHTML = "Password should contain at least one digit and one special character") {
                        document.getElementById("myspan").innerHTML = "Password cannot less than 8 characters";
                    }
                    res = false;
                }
                if (document.getElementById("txtUserName").value.trim() != "" && document.getElementById("txtUserName").value.trim().length < 5) {
                    document.getElementById("myspan1").style.display = "block";
                    res = false;
                }
                if (document.getElementById("password").value != null && document.getElementById("password").value != "" && document.getElementById("password").value.length >= 8) {
                    var result2 = document.getElementById("password").value.match(passw1);
                    if (result2 == null) {
                        document.getElementById("myspan").style.display = "block";
                        document.getElementById("myspan").innerHTML = "Password should contain at least one digit and one special character";
                        res = false;
                    }
                }
                return res;
            }

            function hideMessage() {
                document.getElementById("myspan").style.display = "none";
                document.getElementById("myspan1").style.display = "none";
                document.getElementById("errorMessage").style.display = "none";
            }

            function startTimer() {
                window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
            }
            pecialKeys = new Array();
            //specialKeys.push(8); //Backspace
            //specialKeys.push(9); //Tab
            specialKeys.push(46); //Delete
            specialKeys.push(36); //Home
            specialKeys.push(35); //End
            specialKeys.push(37); //Left
            specialKeys.push(39); //Right
            function IsAlphaNumeric(e) {
                var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode) || keyCode == 8 || keyCode == 46);
                return ret;
            }
        </script>
    </body>
</html>
