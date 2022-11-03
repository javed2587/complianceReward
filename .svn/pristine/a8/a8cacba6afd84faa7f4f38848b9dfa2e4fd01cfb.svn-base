<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <style type="text/css">.hidden {display: none;}</style>
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <c:if test="${surveyResponseType.id!=null && surveyResponseType.id!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Response Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit
                    </c:if>
                    <c:if test="${surveyResponseType.id==null || surveyResponseType.id==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Response Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add New
                    </c:if> 
                </div>
                <div class="heading">
                    <c:if test="${surveyResponseType.id!=null && surveyResponseType.id!=''}">
                        <c:set value="../add" var="action"/>
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Response</h1>
                    </c:if>
                    <c:if test="${surveyResponseType.id==null || surveyResponseType.id==''}">
                        <c:set value="add" var="action"/>
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Response</h1>
                    </c:if>
                </div> <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/surveyResponse/add" commandName="surveyResponseType">
                    <form:hidden path="id"/>
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message">
                        <c:if test="${not empty message}"><div class="message messageheading" id="message">${message}</div></c:if>
                        <div class="errorMessage messageheading" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/surveyResponse/list'"><a href="${pageContext.request.contextPath}/surveyResponse/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#surveyResponseType').submit();">
                                    <a class="btn_Color">Save</a>
                                </div>
                            </div>  
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom:20px;background:#f7f7f7 !important;">
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3">
                                        <label>Response Type:<span style="color:red">*</span></label>
                                        <form:select path="selectedId" cssClass="form-control selectpicker show-tick" onchange="cascadeResponses(this.value);" id="surveyResponseTypeId" disabled="${surveyResponseType.id gt 0 ? true : false}" >
                                            <form:option value="0" label="Please Select" />
                                            <form:options items="${surveyResponseTypeList}" itemValue="id" itemLabel="title"/>
                                        </form:select> 
                                        <form:errors path="selectedId" cssClass="errorMessageValid"/>
                                        <c:if test="${surveyResponseType.id gt 0}">
                                            <form:hidden path="selectedId"/>
                                        </c:if>
                                    </div>
                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3 col-xs-11" id="surveyResponseBlock">  
                                            <label>Response Title:<span style="color:red">*</span></label>
                                            <c:forEach items="${surveyResponseType.surveyResponseDetails}" varStatus="loop">
                                                <div id="surveyResponseDetails${loop.index}" ${surveyResponseType.surveyResponseDetails[loop.index].remove eq 1 ? 'class=hidden' : 'style="margin-bottom: 6px;"'}>
                                                    <form:hidden path="surveyResponseDetails[${loop.index}].id" />
                                                    <c:set var="style" value="float:left;"></c:set>
                                                    <c:if test="${loop.index eq 0}">
                                                        <c:set var="style" value=""></c:set>
                                                    </c:if>
                                                    <form:input path="surveyResponseDetails[${loop.index}].title" cssClass="${loop.index eq 0?'form-control2':'form-control2 mar-top'}" style="float: left;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="100" onkeydown="return noCopyKey(event)"/> 
                                                    <form:hidden id="${loop.index}" path="surveyResponseDetails[${loop.index}].remove" value="${surveyResponseDetails[loop.index].remove}" />
                                                    <c:if test="${loop.index eq 0}">
                                                        <div class="form-inline">
                                                            <button style="position:absolute;right:-14px;margin-top:4px;" class="fa fa-plus-circle" id="add" type="button" class="fa fa-plus-circle"></button>                                            
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${loop.index ne 0}">
                                                        <button type="button" class="remove widget-btnremove-ipad" data-index="${loop.index}"><i class="fa fa-minus-circle"></i></button>  
                                                        </c:if> 
                                                        <form:errors path="surveyResponseDetails[${loop.index}].title" cssClass="errorMessageValid" id="resmsg"/>
                                                </div>
                                            </c:forEach>  
                                            <c:if test="${empty surveyResponseType.surveyResponseDetails}" >
                                                <form:input path="surveyResponseDetails[0].title" cssClass="form-control2" cssStyle="margin-bottom:6px; float: left;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="100" onkeydown="return noCopyKey(event)"/> 
                                                <form:hidden id="0" path="surveyResponseDetails[0].remove" value="${surveyResponseDetails[0].remove}" />
                                                <form:errors path="surveyResponseDetails[0].title" cssClass="errorMessageValid" />
                                                <div class="form-inline">
                                                    <button style="position:absolute;right:-14px;margin-top:4px;" class="fa fa-plus-circle" id="add" type="button" class="fa fa-plus-circle"></button>                                            
                                                </div>
                                            </c:if>
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

                $('body').on('click', '#add', function() {
                    $(this).before(function() {
                        var index = $("#surveyResponseBlock input[type='text']").length;
                        if (index == 0) {
                            index = 1;
                        }
                        for (var i = 0; i < index; i++) {
                            var id = "#surveyResponseDetails" + i;
                            if ($(id + "\\.title").val() == '' && !($(id).is(":hidden"))) {
                                document.getElementById("errorMessage").style.display = "block";
                                document.getElementById("errorMessage").innerHTML = "Response Title is required";
                                return;
                            }
                        }
                        var html = '<div id="surveyResponseDetails' + index + '" class="">';
                        html += '<input class="form-control2 mar-top" type="text" id="surveyResponseDetails' + index + '.title" name="surveyResponseDetails[' + index + '].title" style="float:left;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="100" onkeydown="return noCopyKey(event);" onkeyup="validateSpace(' + index + ')"/>';
                        html += '<input type="hidden" id="' + index + '" name="surveyResponseDetails[' + index + '].remove" value="0"/>';
                        html += '<button type="button" class="remove widget-btn-ipad widget-btnremove-ipad" data-index="' + index + '"><i class="fa fa-minus-circle"></i></button> ';
                        html += "</div>";
                        $("#surveyResponseDetails" + index).show();
                        $("#surveyResponseBlock").append(html);
                    });
                    return false;
                });


                $('body').on('click', '.remove', function() {
                    var index2remove = $(this).data("index");
                    var id = $("#surveyResponseDetails" + index2remove + "\\.id").val();
                    if (id !== null && id > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/surveyResponse/isdelete/" + id,
                            type: "POST",
                            async: false,
                            success: function(data) {
                                if (data == true) {
                                    $("#" + index2remove).val("1");
                                    $("#surveyResponseDetails" + index2remove).hide();
                                    return false;
                                }
                                else {
                                    document.getElementById("errorMessage").style.display = "block";
                                    document.getElementById("errorMessage").innerHTML = "Unable to delete, record is associated with further records.";
                                }
                            }, error: function(e) {
                                alert('Error while request...' + e);
                            }
                        });
                    } else {
                        $("#" + index2remove).val("1");
                        $("#surveyResponseDetails" + index2remove).hide();
                        return false;
                    }
                });

                function hideMessage() {
                    document.getElementById("errorMessage").style.display = "none";
                }

                function startTimer() {
                    window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
                }

                function cascadeResponses(index) {
                    if (index > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/surveyResponse/associatedResponses/" + index,
                            dataType: "json",
                            success: function(data) {
                                if (data.length > 0) {
                                    $("#surveyResponseBlock").empty();
                                    $("#surveyResponseBlock").append('<label>Response Title:<span style="color:red">*</span></label>');
                                    $.each(data, function(index, element) {
                                        var html = '<div id="surveyResponseDetails' + index + '" class="">';
                                        html += '<input class="form-control2" type="text" value="' + element.title + '" id="surveyResponseDetails' + index + '.title" name="surveyResponseDetails[' + index + '].title" style="float:left;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="100" onkeydown="return noCopyKey(event);" onkeyup="validateSpace(' + index + ')"/>';
                                        html += '<input type="hidden" id="' + index + '" name="surveyResponseDetails[' + index + '].remove" value="0"/>';
                                        html += '<input type="hidden" value="' + element.id + '" id="surveyResponseDetails' + index + '.id" name="surveyResponseDetails[' + index + '].id"/>';

                                        if (index == 0) {
                                            html += '<div class="form-inline"><button style="position:absolute;right:-14px;margin-top:4px;" class="fa fa-plus-circle" id="add" type="button" class="fa fa-plus-circle"></button></div">';
                                        } else {
                                            html += '<div class="form-inline"><button type="button" class="remove widget-btnremove-ipad" data-index="' + index + '"><i class="fa fa-minus-circle"></i></button></div>';
                                        }
                                        html += "</div>";
                                        $("#surveyResponseBlock").append(html);
                                    });
                                } else {
                                    $("#surveyResponseBlock").empty();
                                    $("#surveyResponseBlock").append('<label>Response Title:<span style="color:red">*</span></label>');
                                    var html = '<div id="surveyResponseDetails0" class="">';
                                    html += '<input class="form-control2" type="text" id="surveyResponseDetails0.title" name="surveyResponseDetails[0].title" style="float:left;margin-top:7px;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="100" onkeydown="return noCopyKey(event);" onkeyup="validateSpace(0)"/>';
                                    html += '<input type="hidden" id="0" name="surveyResponseDetails[0].remove" value="0"/>';
                                    html += '<div class="form-inline"><button style="position:absolute;right:-14px;margin-top:4px;" id="add" class="fa fa-plus-circle" type="button"></button></div>';
                                    html += "</div>";
                                    $("#surveyResponseBlock").append(html);
                                }
                            }
                        });
                    } else {
                        $("#surveyResponseBlock").empty();
                        $("#surveyResponseBlock").append('<label>Response Title:<span style="color:red">*</span></label>');
                        var html = '<div id="surveyResponseDetails0" class="">';
                        html += '<input class="form-control2" type="text" id="surveyResponseDetails0.title" name="surveyResponseDetails[0].title" style="float:left;margin-top:7px;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="100" onkeydown="return noCopyKey(event);" onkeyup="validateSpace(0)"/>';
                        html += '<input type="hidden" id="0" name="surveyResponseDetails[0].remove" value="0"/>';
                        html += '<div class="form-inline"><button style="position:absolute;right:-14px;margin-top:4px;" id="add" class="fa fa-plus-circle" type="button"></button></div>';
                        html += "</div>";
                        $("#surveyResponseBlock").append(html);
                    }
                }

                function noCopyKey(e) {
                    if (e.ctrlKey && (e.keyCode == 88 || e.keyCode == 67 || e.keyCode == 86 || e.button == 2)) {
                        return false;
                    }
                    var forbiddenKeys = new Array('c', 'x', 'v');
                    var keyCode = (e.keyCode) ? e.keyCode : e.which;
                    var isCtrl;

                    if (window.event)
                        isCtrl = e.ctrlKey
                    else
                        isCtrl = (window.Event) ? ((e.modifiers & Event.CTRL_MASK) == Event.CTRL_MASK) : false;

                    if (isCtrl) {
                        for (i = 0; i < forbiddenKeys.length; i++) {
                            if (forbiddenKeys[i] == String.fromCharCode(keyCode).toLowerCase()) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                //disable right click
                function clickIE() {
                    if (document.all) {

                        return false;
                    }
                }
                function clickNS(e) {
                    if (document.layers || (document.getElementById && !document.all)) {
                        if (e.which == 2 || e.which == 3) {

                            return false;
                        }
                    }
                }

                if (document.layers) {
                    document.captureEvents(Event.MOUSEDOWN);
                    document.onmousedown = clickNS;
                } else {
                    document.onmouseup = clickNS;
                    document.oncontextmenu = clickIE;
                }
                document.oncontextmenu = new Function("return false")
                function validateSpace(index) {
                    var id = "surveyResponseDetails" + index + ".title";
                    if (document.getElementById(id).value.charAt(0) == " " && document.getElementById(id).value.match(/\s/g)) {
                        document.getElementById(id).value = document.getElementById(id).value.replace(/\s/g, '');
                    }
                }
            </script>
    </body>
</html>
