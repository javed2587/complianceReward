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
                    <c:if test="${intervalsType.intervalsTypeId!=null && intervalsType.intervalsTypeId!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Interval Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Interval
                    </c:if>
                    <c:if test="${intervalsType.intervalsTypeId==null || intervalsType.intervalsTypeId==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Interval Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Interval
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${intervalsType.intervalsTypeId!=null && intervalsType.intervalsTypeId!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Interval</h1>
                    </c:if>
                    <c:if test="${intervalsType.intervalsTypeId==null || intervalsType.intervalsTypeId==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Interval</h1>
                    </c:if>
                </div> <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/intervalsType/add" commandName="intervalsType">
                    <form:hidden path="intervalsTypeId" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <div class="errorMessage" id="errorMessage"> <c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            <br clear="all">
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/intervalsType/list'"><a href="${pageContext.request.contextPath}/intervalsType/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#intervalsType').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>        
                        </div>
                        <br><br>  
                        <div class="row grid padding-top-10" style="height: auto;padding-bottom:10px;margin-bottom:40px;background:#f7f7f7;">

                            <div class="search-grid">
                                <div class="form-group fgPL-zero">

                                    <div class="col-sm-3">
                                        <label>Interval Title: <span style="color:red">*</span></label>
                                        <form:input path="intervalsTypeTitle" onkeypress="return IsAlphaNumeric(event);" cssClass="form-control2" onchange="checkLength(this)" maxlength="20"/>
                                        <span id="myspan" class="errorMessage1"><c:if test="${not empty message1}">${message1}</c:if></span>
                                        <form:errors path="intervalsTypeTitle" cssClass="errorMessageValid" />   
                                    </div>
                                </div>
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3">
                                        <label>Interval In Seconds(s):<span style="color:red">*</span></label>
                                        <form:input path="unitInSecond"  cssClass="form-control2" title="This must be a +iv number" onkeypress="return isNumber(event);" maxlength="10"/>
                                        <form:errors path="unitInSecond" cssClass="errorMessageValid" />
                                    </div>
                                </div>   

                                <div class="form-group fgPL-zero"> 
                                    <div class="col-sm-3 col-xs-11" id="textboxgroup" >
                                        <label>Interval Value(s): <span style="color:red">*</span></label>

                                        <c:forEach items="${intervalsType.intervals}" varStatus="loop">
                                            <div  id="intervals${loop.index}" <c:if test="${intervalsType.intervals[loop.index].remove eq 1}">class="hidden"</c:if> <c:if test="${loop.index eq 0}">style="margin-bottom:6px;"</c:if>>
                                                <c:set var="style" value="float:left;margin-top:5px;"></c:set>
                                                <c:if test="${loop.index eq 0}">
                                                    <c:set var="style" value=""></c:set>
                                                </c:if>
                                                <form:hidden id="intervals[${loop.index}].intervalId" path="intervals[${loop.index}].intervalId"/>
                                                <form:input path="intervals[${loop.index}].intervalValue" cssClass="form-control2" maxlength="5"  cssStyle="${style}" title="This must be a +iv number" onkeypress="return isNumber(event);"/> 
                                                <form:hidden path="intervals[${loop.index}].remove" value="${intervals[loop.index].remove}" />
                                                <c:if test="${loop.index ne 0}">
                                                    
                                                    <a class="res-toplginterval fa fa-minus-circle" href="#" data-index="${loop.index}" onclick="deleteRow('intervals[${loop.index}].intervalId', 'intervals' +${loop.index})"></a>
                                                    </c:if>
                                                    <form:errors path="intervals[${loop.index}].intervalValue" cssClass="errorMessageValid" />
                                            </div>
                                        </c:forEach>
                                        <c:if test="${empty intervalsType.intervals}">
                                            <form:input path="intervals[0].intervalValue" cssClass="form-control2" maxlength="5" title="This must be a +iv number" onkeypress="return isNumber(event);" cssStyle="margin-bottom:6px;" />
                                            <form:hidden path="intervals[0].remove" value="${intervals[0].remove}" />
                                            <form:errors path="intervals[0].intervalValue" cssClass="errorMessageValid" />
                                        </c:if>


                                        <c:set var="right" value="9px"/>
                                        <c:if test="${fn:contains(header['User-Agent'],'MSIE')}"> <c:set var="right" value="13px"/></c:if>
<!--                                        <button id="add"  class="fa fa-plus-circle" style="float: right; position: absolute; right:${right}; top: 18px;right:-14px;" type="button"></button>  -->
                                        <a id="add" class="fa fa-plus-circle" href="#" style="float: right; position: absolute; right:${right}; top: 26px;right:-14px;"></a>
                                    </div>   
                                </div>
                                <div class="form-group fgPL-zero">
                                    <div class="col-sm-3" id="textboxgroup">
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
                    var index = ${fn:length(intervalsType.intervals)};

                    $("#add").off("click").on("click", function() {
                        $(this).before(function() {
                            for (var i = 0; i <= index; i++) {
                                if ($("#intervals" + i + "\\.intervalValue").val() == '') {
                                    document.getElementById("errorMessage").style.display = "block";
                                    document.getElementById("errorMessage").innerHTML = "Interval Value(s) required";
                                    return;
                                }
                            }
                            var html = '<div id="intervals' + index + '" class="">';
                            html += '<input class="form-control2" type="text" id="intervals' + index + '.intervalValue" name="intervals[' + index + '].intervalValue" style="float:left;margin-top:5px;" title="This must be a +iv number" onkeypress="return isNumber(event);" maxlength="5" />';
                            html += '<input type="hidden" id="intervals' + index + '.remove" name="intervals[' + index + '].remove" value="0"/>';
                            html += '<a href="#" data-index="' + index + '" class="remove res-toplg fa fa-minus-circle"></a>  ';
                            html += "</div>";
                            $("#intervals" + index).show();
                            index++;
                            return html;
                        });
                        return false;
                    });

                    $('body').on('click', '.remove', function() {
                        var index2remove = $(this).data("index");
                        $("#intervals" + index2remove + '\\.intervalValue').val("1");
                        $("#intervals" + index2remove + '\\.remove').val("1");
                        $("#intervals" + index2remove).hide();
                        return false;
                    });

                });


                function isNumber(evt) {
                    evt = (evt) ? evt : window.event;
                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                        return false;
                    }
                    return true;
                }
                $(function() {
                    setTimeout(function() {
                        $("#errorMessage").hide('blind', {}, 500)
                        $("#message").hide('blind', {}, 500)
                    }, 5000);
                });
                function checkLength(inputtxt) {
                    if (inputtxt.value.trim() != "" && inputtxt.value.length < 3) {
                        document.getElementById("myspan").style.display = "block";
                        document.getElementById("myspan").innerHTML = "Minimum length 3 character";
                        return false;
                    }
                    else {
                        document.getElementById("myspan").style.display = "none";
                        return true;
                    }
                }
                function hideMessage() {
                    document.getElementById("myspan").style.display = "none";
                    document.getElementById("errorMessage").style.display = "none";
                }

                function startTimer() {
                    window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
                }
                $('Input').bind("paste", function(e) {
                    e.preventDefault();
                });

                function deleteRow(id, divId) {
                    var intervalId = document.getElementById(id).value;
                    if (intervalId > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/intervalsType/deleteInterval/" + intervalId,
                            type: "POST",
                            async: false,
                            success: function(data) {
                                if (data == true) {
                                    $("#" + divId).remove();
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
                        $("#" + divId + '\\.intervalValue').val("1");
                        $("#" + divId + '\\.remove').val("1");
                        $("#" + divId).hide();
                    }
                }
            </script>
    </body>
</html>
