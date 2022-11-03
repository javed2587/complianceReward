<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">

                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Shipping Preferences
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Shipping Preferences</h1>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/deliveryDsitances/add" commandName="deliveryDsitances" onsubmit="return validateField()">
                    <input type="hidden" value="${fn:length(deliveryDsitances.deliveryDsitanceses) gt 0?fn:length(deliveryDsitances.deliveryDsitanceses)-1:0}" id="remove">
                    <input type="hidden" id="type" value="1" />
                    <div class="page-message">
                        <div class="${not empty message?'message':'errorMessage'} messageheading" id="message">${not empty message?message:errorMessage}</div>
                        <br clear="all">
                    </div>

                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                        <div style="float: right;">
                            <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/deliveryDsitances/add'"><a href="${pageContext.request.contextPath}/deliveryDsitances/add" class="btn_Color">Cancel</a></div>


                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#deliveryDsitances').submit();">
                                    <a class="btn_Color">Save</a></div>

                            </div>  
                        </div>
                        <br><br>
                    </div>
                    <div class="row grid1 padd-bottom-zero" style="height: auto;margin-bottom: 40px;padding-bottom:20px;background:#f7f7f7;margin-left: 15px;margin-right: 15px;">
                        <div class="search-grid">

                            <c:set var="drugslength" value="${fn:length(deliveryDsitances.deliveryDsitanceses)}" scope="page"/>
                            <c:choose>
                                <c:when test="${drugslength gt 0}">
                                    <c:set var="druglength" value="${drugslength - 1}" scope="page"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="defualtDistanceFrom" value="0" scope="page"/>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="rowCounter" begin="0" step="1" end="${druglength}">
                                <div class="drugListWidth col-lg-12 col-md-12 col-sm-12 col-xs-12 for-320" id="drugList${rowCounter}" style="padding-top:5px;width:100%;float:left;">
                                    <div id="deliveryDsitanceses-${rowCounter}" style="padding-left:0px;" >
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                <c:if test="${rowCounter eq 0}"><label>Distance From:<span style="color:red">*</span></label></c:if>
                                                <form:hidden id="deliveryDsitanceses[${rowCounter}].id" path="deliveryDsitanceses[${rowCounter}].id"/>
                                                <form:hidden id="deliveryDsitanceses[${rowCounter}].createdBy" path="deliveryDsitanceses[${rowCounter}].createdBy"/>
                                                <form:hidden id="deliveryDsitanceses[${rowCounter}].createdOn" path="deliveryDsitanceses[${rowCounter}].createdOn"/>
                                                <form:input path="deliveryDsitanceses[${rowCounter}].distanceFrom" cssClass="form-control" maxlength="10" onkeypress="return IsDigit(event)" value="${defualtDistanceFrom}" readonly="true"/>

                                                <div id="distanceFromReq${rowCounter}" class="errorMessage"></div>
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                &nbsp;
                                            </div>
                                        </div>

                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-2 col-md-12 col-sm-12 col-xs-10" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                <c:if test="${rowCounter eq 0}"><label>Distance To:<span style="color:red">*</span></label></c:if>
                                                <form:input path="deliveryDsitanceses[${rowCounter}].distanceTo" cssClass="form-control" maxlength="10" onkeypress="return IsDigit(event)"/>
                                                <div id="distanceToReq${rowCounter}" class="errorMessage"></div>
                                                <c:if test="${rowCounter eq 0}">
                                                    <a id="addMore" href="#" class="fa fa-plus-circle" style="position:relative;top: -21px; left: 15px;float:right;" onclick="addNewDetail('deliveryDsitanceses-' +${fn:length(deliveryDsitances.deliveryDsitanceses)-1});"></a>

                                                </c:if>
                                                <c:if test="${rowCounter ne 0}">
                                                    <a id="deleteButton1" class="fa fa-minus-circle" href="#" style="position:relative;top: -21px; left: 15px;float:right;" onclick="deleteRow(this, 'deliveryDsitanceses[${rowCounter}].id',${rowCounter})"></a>

                                                </c:if>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </div>
            </form:form>




        </div> <!-- /content -->
        <jsp:include page="./inc/footer.jsp" />
    </div> <!-- /wrapper -->
    <script type="text/javascript">
        function isNumber(evt) {
            evt = (evt) ? evt : window.event;
            var charCode = (evt.which) ? evt.which : evt.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                return false;
            }
            return true;
        }

        function hideMessage() {
            document.getElementById("errorMessage").style.display = "none";
            var totalLength = $("#remove").val();
            for (var row = 0; row <= parseInt(totalLength); row++) {
                $("#distanceToReq" + row).text("Required");
                $("#distanceToReq" + row).attr("style", "display:none;float:left;");
            }
        }

        function startTimer() {
            window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
        }
        function addNewDetail(id)
        {
            var i = $("#remove").val();
            var index = ${fn:length(drugBrand.deliveryDsitanceses)-1};
            if (index == 0 && i == 0) {
                index = 1;
            } else {
                index = parseInt(i) + 1;
            }
            var isIE = /*@cc_on!@*/false || !!document.documentMode; // At least IE6
            for (var i = 0; i <= index; i++) {
                if ($("#deliveryDsitanceses" + i + "\\.distanceFrom").val() == '' || $("#deliveryDsitanceses" + i + "\\.distanceTo").val() == '') {
                    document.getElementById("errorMessage").style.display = "block";
                    document.getElementById("errorMessage").innerHTML = "All dynamic field(s) required";
                    return;
                }
            }
            var html = "";
            html += '<div id="deliveryDsitanceses-' + index + '" style="margin-top:12px;width:100%; float:left;"><div class="col-lg-2 col-md-12 col-sm-12 col-xs-12">';
            html += '<input class="form-control" id="deliveryDsitanceses' + index + '.distanceFrom" name="deliveryDsitanceses[' + index + '].distanceFrom" maxlength="10" onkeypress="return IsDigit(event)" readonly="true"/>';
            html += '<div id="distanceFromReq' + index + '" class="errorMessage"></div>';
            html += '</div>';
            html += ' <div class="col-lg-2 col-md-12 col-sm-2 col-xs-12">';
            html += '&nbsp';
            html += '</div>';

            html += ' <div class="col-lg-2 col-md-12 col-sm-12 col-xs-10"> ';
            html += '<input class="form-control" id="deliveryDsitanceses' + index + '.distanceTo" name="deliveryDsitanceses[' + index + '].distanceTo" maxlength="10" onkeypress="return IsDigit(event)"/>';
            html += '<div id="distanceToReq' + index + '" class="errorMessage"></div>';
            html += '<a id="deleteButton" class="fa fa-minus-circle" href="#" style="position:relative;top: -21px; left: 15px;float:right;" onclick=deleteDiv("' + index + '")></a>';
            html += '</div> </div>';
            $("#deliveryDsitanceses-" + index).show();
            $("#" + id).append(html);
            $("#remove").val(index);

            var distanCeTo = $("#deliveryDsitanceses" + parseInt(index - 1) + "\\.distanceTo").val();
            $("#deliveryDsitanceses" + index + "\\.distanceFrom").val(parseInt(distanCeTo) + 1);
            index++;

        }
        function deleteRow(btn, id, divId) {
            var drugId = document.getElementById(id).value;
            if (drugId > 0) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/deliveryDsitances/delete/" + drugId,
                    type: "POST",
                    async: false,
                    success: function (data) {
                        if (data == true) {
                            deleteDiv(divId);
                        } else {
                            $("#message").attr("class", "errorMessage");
                            document.getElementById("message").style.display = "block";
                            document.getElementById("message").innerHTML = "Unable to delete, record is associated with further records.";
                        }
                    }, error: function (e) {
                        alert('Error while request...' + e);
                    }
                });
            } else {
                deleteDiv(divId);
            }
        }
        function deleteDiv(id) {
            $("#remove").val(parseInt(id) - 1);
            $("#deliveryDsitanceses-" + id).remove();
            var distanCeTo = $("#deliveryDsitanceses" + parseInt(id - 1) + "\\.distanceTo").val();
            var newid = parseInt(id) + 1;
            $("#deliveryDsitanceses" + newid + "\\.distanceFrom").val(distanCeTo);
        }
        //$('input[type=file]').bootstrapFileInput();
        $('.file-inputs').bootstrapFileInput();
        $('Input').bind("paste", function (e) {
            e.preventDefault();
        });
        function validateField() {
            var totalLength = $("#remove").val();
            for (var row = 0; row <= parseInt(totalLength); row++) {
                if ($("#deliveryDsitanceses" + row + "\\.distanceTo").val() === "") {
                    $("#distanceToReq" + row).text("Required");
                    $("#distanceToReq" + row).attr("style", "display:block;float:left;");
                    return false;
                }
            }
            return true;
        }
    </script>
</body>
</html>

