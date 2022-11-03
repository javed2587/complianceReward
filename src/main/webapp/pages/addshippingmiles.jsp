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
                    <c:if test="${pharmacyZipCodes.id!=null && pharmacyZipCodes.id!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Shipping Pref Setup 
                    </c:if>
                    <c:if test="${pharmacyZipCodes.id==null || pharmacyZipCodes.id==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Shipping Pref Setup 
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${pharmacyZipCodes.id!=null && pharmacyZipCodes.id!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Shipping Pref Setup</h1>
                    </c:if>
                    <c:if test="${pharmacyZipCodes.id==null || pharmacyZipCodes.id==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp; Add Shipping Pref Setup</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/shippingMiles/add" commandName="pharmacyZipCodes">
                    <form:hidden path="pharmacyName"/>
                    <form:hidden path="pharmacyZip"/>

                    <div class="page-message">
                        <c:if test="${not empty message}"><div class="errorMessage messageheading" id="message">${message}</div></c:if>
                        <div class="errorMessage messageheading" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            <br clear="all">
                        </div>


                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/shippingMiles/list'"><a href="${pageContext.request.contextPath}/shippingMiles/list" class="btn_Color">Cancel</a></div>


                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#pharmacyZipCodes').submit();">
                                    <a class="btn_Color">Save</a>
                                </div>
                            </div>  
                        </div>
                        <br><br>

                        <div class="row grid padd-top-10 padding-bottom-10" style="height: auto;margin-bottom: 10px;padding-bottom: 10px;background:#f7f7f7;">
                            <div class="search-grid">
                                <div class="form-group">  
                                    <div class="col-sm-2 padd-left-zero" style="width:22%;">  
                                        <label>Pharmacy:<span style="color:red">*</span></label> 
                                        <form:select id="pharmacyId" path="id" cssClass="form-control selectpicker show-tick" onchange="uploadRecord(this.value)" >
                                            <form:option value="0">Select one</form:option>
                                            <c:forEach var="row" items="${list}">
                                                <form:option value="${row.id}">${row.pharmacyName} (${row.pharmacyZip})</form:option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors path="id" cssClass="errorMessage"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:set var="count" value="0" scope="page"></c:set>
                    <c:set var="childCounter" value="0" scope="page"></c:set>
                    <c:forEach var="row" items="${deliveryPreferenceslist}" varStatus="counter">
                        <div class="row grid1 padd-bottom-zero" style="height: auto;margin-bottom: 40px;padding-bottom:20px;background:#f7f7f7;margin-left: 15px;margin-right: 15px;">
                            <div class="search-grid">

                                <div class="drugListWidth col-lg-12 col-md-12 col-sm-12 col-xs-12 for-320" id="drugList" style="padding-top:5px;width:100%;float:left;">
                                    <div id="drugs" style="padding-left:0px;" >
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                ${row.name}
                                                <form:hidden id="deliveryDistanceFeesList[${childCounter}].deliveryPreferenceses.id" path="deliveryDistanceFeesList[${childCounter}].deliveryPreferenceses.id" value="${row.id}"/>
                                                <form:hidden id="deliveryDistanceFeesList[${childCounter}].id" path="deliveryDistanceFeesList[${childCounter}].id"/>
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                <label>Description<span style="color:red">*</span></label>
                                                <form:input path="deliveryDistanceFeesList[${childCounter}].description" cssClass="form-control" maxlength="50" value="${row.description}"/>
                                                <form:errors path="deliveryDistanceFeesList[${childCounter}].description" cssClass="errorMessage"/>
                                            </div>
                                        </div>

                                        <div style="clear: both;"></div>
                                        <c:forEach var="innerRow" items="${deliveryDistanceslist}" varStatus="ct">
                                            <div class="padd-left-zero">
                                                <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;padding-top: 15px;">
                                                    <label>Miles ${innerRow.distanceFrom} - ${innerRow.distanceTo}<span style="color:red">*</span></label>
                                                    <input id="deliveryPreferenceses${childCounter}" type="hidden" value="${row.id}"/>
                                                    <form:hidden id="deliveryDistancesId${childCounter}" path="deliveryDistanceFeesList[${childCounter}].deliveryDistances.id" value="${innerRow.id}" cssClass="deliveryDistancesIds"/>
                                                    <form:input id="deliveryFee${innerRow.id}_${row.id}" path="deliveryDistanceFeesList[${childCounter}].fee" cssClass="form-control" maxlength="50"/>
                                                    <form:errors path="deliveryDistanceFeesList[${childCounter}].fee" cssClass="errorMessage"/>
                                                </div>
                                            </div>
                                            <c:set var="childCounter" value="${childCounter+1}" scope="page"></c:set>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var="count" value="${count+1}" scope="page"></c:set>
                    </c:forEach>
                </form:form>
            </div>
            <jsp:include page="./inc/footer.jsp" />
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                if ("${pharmacyZipCodes.id}" !== "") {
                    var count = 0;
                    var list = ${fn:length(pharmacyZipCodes.deliveryDistanceFeesList)-1};
                    for (var i = 0; i <= list; i++) {
                        var value = $("#deliveryDistancesId" + i).val();
                        var delPrefId = $("#deliveryPreferenceses" + i).val();

                        $.ajax({
                            url: "${pageContext.request.contextPath}/shippingMiles/getDeliveryDistances/" + value + "/" + $("#deliveryPreferenceses" + i).val(),
                            dataType: "json",
                            success: function (data) {
                                if (Object.keys(data).length > 0) {
                                    $.each(data, function (index, element) {
                                        $("#deliveryFee" + element.id + "_" + element.dprefId).val(element.fee);
                                    });
                                } else {
                                    $("#deliveryFee" + value + "_" + delPrefId).val("");
                                }
                            }
                        });
                        count++;
                    }
                }
            });
            function hideMessage() {
                document.getElementById("errorMessage").style.display = "none";
            }

            function startTimer() {
                window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
            }
            //$('input[type=file]').bootstrapFileInput();
            $('.file-inputs').bootstrapFileInput();
            $('Input').bind("paste", function (e) {
                e.preventDefault();
            });
        </script>
    </body>
</html>
