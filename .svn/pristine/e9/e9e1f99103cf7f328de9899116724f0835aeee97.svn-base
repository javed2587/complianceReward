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

                    <c:if test="${response.responseId!=null && response.responseId!=''}">

                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Response Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Response

                    </c:if>

                    <c:if test="${response.responseId==null || response.responseId==''}">

                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Response Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Response

                    </c:if> 

                </div>

                <div class="heading" >

                    <c:if test="${response.responseId!=null && response.responseId!=''}">

                        <c:set value="../add" var="action"/>

                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Response</h1>

                    </c:if>

                    <c:if test="${response.responseId==null || response.responseId==''}">

                        <c:set value="add" var="action"/>

                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Response</h1>

                    </c:if>

                </div> <!-- /header -->



                <form:form action="${pageContext.request.contextPath}/response/add" commandName="response">

                    <form:hidden path="responseId" />

                    <form:hidden path="isActive" />

                    <form:hidden path="isDelete" />

                    <form:hidden path="createdBy" />

                    <form:hidden path="createdOn" />  



                    <input type="hidden" id="type" value="1" />

                    <div class="page-message">

                        <c:if test="${not empty message}"><div class="message messageheading" id="message">${message}</div></c:if>

                        <div class="errorMessage messageheading" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>

                            <br clear="all">

                        </div>



                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">

                            <div style="float: right;">

                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/response/list'"><a href="${pageContext.request.contextPath}/response/list" class="btn_Color">Cancel</a></div>



                            <div class="btn-group">

                                <div class="btndrop btn-margin" onclick="$('#response').submit();">

                                    <a class="btn_Color">Save</a></div>

                            </div>  

                        </div>

                        <br><br>

                        <div class="row grid padding-top-10" style="height: auto;margin-bottom: 40px; padding-bottom:10px;background:#f7f7f7 !important;">



                            <div class="search-grid">

                                <div class="form-group fgPL-zero">



                                    <div class="col-sm-3">

                                        <label>Response Title:<span style="color:red">*</span></label>



                                        <form:input path="responseTitle" onkeypress="return IsAlphaNumeric(event);" cssClass="form-control2"/>

                                        <form:errors path="responseTitle" cssClass="errorMessageValid" />

                                        <span id="myspan" class="errorMessage1"><c:if test="${not empty message1}">${message1}</c:if></span>

                                        </div>    

                                    </div>

                                    <div class="form-group fgPL-zero">



                                        <div class="col-sm-3 col-xs-11">  

                                            <label> Valid Response(s):<span style="color:red">*</span></label>



                                        <c:forEach items="${response.validResponses}" varStatus="loop">

                                            <div id="validResponses${loop.index}" <c:if test="${response.validResponses[loop.index].remove eq 1}">class="hidden"</c:if><c:if test="${loop.index eq 0}">style="margin-bottom: 6px;"</c:if>>

                                                <c:set var="style" value="float:left;"></c:set>

                                                <c:if test="${loop.index eq 0}">

                                                    <c:set var="style" value=""></c:set>

                                                </c:if>

                                                <form:hidden path="validResponses[${loop.index}].vresponseId" id="validResponses[${loop.index}].vresponseId"/>

                                                <form:input path="validResponses[${loop.index}].validWord" cssClass="form-control2" cssStyle="${style}" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="255"/> 

                                                <form:hidden path="validResponses[${loop.index}].remove" value="${validResponses[loop.index].remove}" />

                                                <c:if test="${loop.index ne 0}">

                                                    <div class="form-inline">

<!--                                                        <button type="button" class="remove res-toplg" data-index="${loop.index}" onclick="deleteRow('validResponses[${loop.index}].vresponseId', 'validResponses' +${loop.index})"><i class="fa fa-minus-circle"></i></button>-->

                                                        <a class="remove res-toplg1 fa fa-minus-circle" href="#" data-index="${loop.index}" onclick="deleteRow('validResponses[${loop.index}].vresponseId', 'validResponses' +${loop.index})"></a>

                                                    </div> 

                                                        </c:if>



                                                <form:errors path="validResponses[${loop.index}].validWord" cssClass="errorMessageValid" id="resmsg"/>

                                            </div>

                                        </c:forEach>  



                                        <c:if test="${empty response.validResponses}" >

                                            <form:input path="validResponses[0].validWord" cssClass="form-control2" cssStyle="margin-bottom:6px;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="25" /> 

                                            <form:hidden path="validResponses[0].remove" value="${validResponses[0].remove}" />

                                            <form:errors path="validResponses[0].validWord" cssClass="errorMessageValid" />

                                        </c:if>

                                        <div class="form-inline">

                                            <!--                                            <button class="fa fa-plus-circle" style="position:absolute;top:17px;right:-14px;"  id="add" type="button" class="fa fa-plus-circle"></button>                                            -->

                                            <a id="add" class="fa fa-plus-circle" href="#" style="position:absolute;top:26px;right: -14px;"></a>

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

            $(function() {

                var isIE = /*@cc_on!@*/false || !!document.documentMode; // At least IE6

                var index = ${fn:length(response.validResponses)};

                if (index == 0) {

                    index = 1;

                }

                $("#add").off("click").on("click", function() {



                    $(this).before(function() {

                        for (var i = 0; i <= index; i++) {

                            if ($("#validResponses" + i + "\\.validWord").val() == '') {

                                document.getElementById("errorMessage").style.display = "block";

                                document.getElementById("errorMessage").innerHTML = "Valid Response(s) value required";

                                return;

                            }

                        }

                        var html = '<div id="validResponses' + index + '" class="">';

                        html += '<input class="form-control2" type="text" id="validResponses' + index + '.validWord" name="validResponses[' + index + '].validWord" style="float:left;margin-top:5px;" onkeypress="return IsAlphaNumeric(event);" title="This must be alphanumberic characters" maxlength="255" />';

                        html += '<input type="hidden" id="validResponses' + index + '.remove" name="validResponses[' + index + '].remove" value="0"/>';

                        html += '<a class="remove res-toplg fa fa-minus-circle" href="#" data-index="' + index + '"></a> ';

                        html += "</div>";

                        $("#validResponses" + index).show();

                        index++;

                        return html;

                    });



                    return false;

                });





                $('body').on('click', '.remove', function() {

                    var index2remove = $(this).data("index");

                    $("#validResponses" + index2remove + '\\.validWord').val("dfd1");

                    $("#validResponses" + index2remove + '\\.remove').val("1");

                    $("#validResponses" + index2remove).remove();

                    index--;

                    return false;

                });



            });

            function hideMessage() {

                document.getElementById("errorMessage").style.display = "none";

            }



            function startTimer() {

                window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds

            }

//            $('Input').bind("paste", function(e) {

//                e.preventDefault();

//            });

            function deleteRow(id, divId) {

                var validResponseId = document.getElementById(id).value;

                if (validResponseId > 0) {

                    $.ajax({

                        url: "${pageContext.request.contextPath}/response/deleteValidResponse/" + validResponseId,

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

                    $("#" + divId).remove();

                }

            }

        </script>

    </body>

</html>

