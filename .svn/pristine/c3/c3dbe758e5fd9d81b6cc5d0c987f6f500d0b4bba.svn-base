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

                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Additional Margin Setup 
                </div>
                <div class="heading">
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Drug Additional Margin Setup </h1>
                </div> <!-- /header -->
                <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/drugAdditionalMargin/add" cssClass="frm" commandName="drugAdditionalMargin" onsubmit="return validateField()" >
                    <form:hidden id="id" path="id"/>

                    <input type="hidden" id="type" value="1" />
                    <div class="page-message">
                        <div class="${not empty message?'message':'errorMessage'} messageheading" id="message">${not empty message?message:errorMessage}</div>
                        <br clear="all">
                    </div>
                    <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                        <div style="float: right;">
                            <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/drugAdditionalMargin/list'"><a href="${pageContext.request.contextPath}/drugAdditionalMargin/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#drugAdditionalMargin').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>
                        <div class="row grid padding-top-10 padding-bottom-10" style="height: auto;margin-bottom: 40px; padding-bottom:25px;background:#f7f7f7;">
                            <div class="search-grid">
                                <div class="form-group fgPL-zero">
                                    <div class="col-lg-6 col-sm-12 padd-left-1-ipad">
                                        <label style="padding-left: 0px; margin-left:0px;">Drug Category:<span style="color:red">*</span> </label>
                                        <form:select id="drugBrandId" path="drugCategory.id" cssClass="form-control selectpicker show-tick" onchange="uploadRecord(this.value)">
                                            <form:option value="0">
                                                Select one
                                            </form:option>
                                            <form:options  items="${drugCategorylist}" itemLabel="drugCategoryName" itemValue="id"/>
                                        </form:select>
                                        <div id="drugBrandIdReq" class="errorMessage"></div>
                                    </div>
                                </div> 
                                <div class="form-group fgPL-zero">
                                    <div class="col-lg-6 col-sm-12 padd-left-1-ipad">
                                        &nbsp;
                                    </div> 
                                </div>
                                <div style="clear: both;"></div>
                                <div class="form-group margin-ive fgPL-zero" style="margin-top:10px; " >
                                    <div class="col-sm-2" style="width: 262px;" >
                                        <label>Qty 1-30:<span style="color:red">*</span></label>
                                        <form:hidden id="drugAdditionalMarginPricesId0" path="drugAdditionalMarginPricesList[0].id"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[0].drugQtyFrom" value="1"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[0].drugQtyTo" value="30"/>
                                        <form:input id="drugMarginValue0" path="drugAdditionalMarginPricesList[0].drugMarginValue" cssClass="form-control"  maxlength="6" onkeypress="return isFloatNumber(this,event)" readonly="${drugAdditionalMargin.drugCategory.id ne null && drugAdditionalMargin.drugCategory.id >0?false:true}" />
                                        <div id="drugMarginValue0Req" class="errorMessage"></div>
                                    </div> 
                                </div>
                                <div class="form-group margin-ive fgPL-zero"  >
                                    <div class="col-sm-2" style="width: 262px;" >
                                        <label>Qty 31-60:<span style="color:red">*</span></label>
                                        <form:hidden id="drugAdditionalMarginPricesId1" path="drugAdditionalMarginPricesList[1].id"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[1].drugQtyFrom" value="31"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[1].drugQtyTo" value="60"/>
                                        <form:input id="drugMarginValue1" path="drugAdditionalMarginPricesList[1].drugMarginValue" cssClass="form-control" onkeypress="return isFloatNumber(this,event)" maxlength="6" readonly="${drugAdditionalMargin.drugCategory.id ne null && drugAdditionalMargin.drugCategory.id >0?false:true}"/>
                                        <div id="drugMarginValue1Req" class="errorMessage"></div>
                                    </div> 
                                </div>
                                <div class="form-group margin-ive fgPL-zero">
                                    <div class="col-sm-2" style="width: 262px;" >
                                        <label>Qty 61-100:<span style="color:red">*</span></label>
                                        <form:hidden id="drugAdditionalMarginPricesId2" path="drugAdditionalMarginPricesList[2].id"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[2].drugQtyFrom" value="61"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[2].drugQtyTo" value="100"/>
                                        <form:input id="drugMarginValue2" path="drugAdditionalMarginPricesList[2].drugMarginValue" cssClass="form-control" onkeypress="return isFloatNumber(this,event)" maxlength="6" readonly="${drugAdditionalMargin.drugCategory.id ne null && drugAdditionalMargin.drugCategory.id >0?false:true}"/>
                                        <div id="drugMarginValue2Req" class="errorMessage"></div>
                                    </div> 
                                </div>
                                <div class="form-group margin-ive fgPL-zero">
                                    <div class="col-sm-2" style="width: 262px;"  >
                                        <label>Qty 101-180:<span style="color:red">*</span></label>
                                        <form:hidden id="drugAdditionalMarginPricesId3" path="drugAdditionalMarginPricesList[3].id"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[3].drugQtyFrom" value="101"/>
                                        <form:hidden path="drugAdditionalMarginPricesList[3].drugQtyTo" value="180"/>
                                        <form:input id="drugMarginValue3" path="drugAdditionalMarginPricesList[3].drugMarginValue" cssClass="form-control" onkeypress="return isFloatNumber(this,event)" maxlength="6" readonly="${drugAdditionalMargin.drugCategory.id ne null && drugAdditionalMargin.drugCategory.id >0?false:true}"/>
                                        <div id="drugMarginValue3Req" class="errorMessage"></div>
                                    </div> 
                                </div>
                            </div>
                            <div class="form-group margin-ive fgPL-zero">
                                <div class="col-sm-2" style="width: 262px;">
                                    <label>Qty 181+:<span style="color:red">*</span></label>
                                    <form:hidden id="drugAdditionalMarginPricesId4" path="drugAdditionalMarginPricesList[4].id"/>
                                    <form:hidden path="drugAdditionalMarginPricesList[4].drugQtyFrom" value="181"/>
                                    <form:hidden path="drugAdditionalMarginPricesList[4].drugQtyTo" value="1500"/>
                                    <form:input id="drugMarginValue4" path="drugAdditionalMarginPricesList[4].drugMarginValue" cssClass="form-control" onkeypress="return isFloatNumber(this,event)" maxlength="6" readonly="${drugAdditionalMargin.drugCategory.id ne null && drugAdditionalMargin.drugCategory.id >0?false:true}"/>
                                    <div id="drugMarginValue4Req" class="errorMessage"></div>
                                </div> 
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
    <!-- /content -->
    <jsp:include page="./inc/footer.jsp" />
    <script type="text/javascript">
        function startTimer()
        {
            window.setInterval("hideMessage()", 5000);
        }
        function hideMessage() {
            $("#drugBrandIdReq").hide();
            $("#drugMarginValue0Req").hide();
            $("#drugMarginValue1Req").hide();
            $("#message").hide();
            $("#drugMarginValue2Req").hide();
            $("#drugMarginValue3Req").hide();
            $("#drugMarginValue4Req").hide();
        }
        function validateField() {
            var valid = true;
            var e = document.getElementById("drugBrandId");
            if (e.options[e.selectedIndex].value == 0) {
                $("#drugBrandIdReq").text("Required");
                $("#drugBrandIdReq").show();
                valid = false;
            }
            if ($("#drugMarginValue0").val() === "") {
                $("#drugMarginValue0Req").text("Required");
                $("#drugMarginValue0Req").show();
                valid = false;
            }
            if ($("#drugMarginValue1").val() === "") {
                $("#drugMarginValue1Req").text("Required");
                $("#drugMarginValue1Req").show();
                valid = false;
            }
            if ($("#drugMarginValue2").val() === "") {
                $("#drugMarginValue2Req").text("Required");
                $("#drugMarginValue2Req").show();
                valid = false;
            }
            if ($("#drugMarginValue3").val() === "") {
                $("#drugMarginValue3Req").text("Required");
                $("#drugMarginValue3Req").show();
                valid = false;
            }
            if ($("#drugMarginValue4").val() === "") {
                $("#drugMarginValue4Req").text("Required");
                $("#drugMarginValue4Req").show();
                valid = false;
            }
            return valid;
        }
        function uploadRecord(drugBrandId) {
            if (drugBrandId > 0) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/drugAdditionalMargin/load/" + drugBrandId,
                    dataType: "json",
                    success: function (data) {
                        $.each(data, function (index, element) {
//                                    alert(element.id + " Margin pric " + element.drugMarginValue + " Margin id " + element.drugAdditionalMargin.id);
                            $("#id").val(element.drugAdditionalMargin.id);
                            if (element.drugQtyFrom === 1 && element.drugQtyTo === 30) {
                                $("#drugAdditionalMarginPricesId0").val(element.id);
                                $("#drugMarginValue0").val(element.drugMarginValue);
                                $("#drugAdditionalMarginPricesList0\\.drugQtyFrom").val(element.drugQtyFrom);
                                $("#drugAdditionalMarginPricesList0\\.drugQtyTo").val(element.drugQtyTo);
                                $('#drugMarginValue0').prop('readonly', false);

                            }
                            if (element.drugQtyFrom === 31 && element.drugQtyTo === 60) {
                                $("#drugAdditionalMarginPricesId1").val(element.id);
                                $("#drugMarginValue1").val(element.drugMarginValue);
                                $("#drugAdditionalMarginPricesList1\\.drugQtyFrom").val(element.drugQtyFrom);
                                $("#drugAdditionalMarginPricesList1\\.drugQtyTo").val(element.drugQtyTo);
                                $('#drugMarginValue1').prop('readonly', false);
                            }
                            if (element.drugQtyFrom === 61 && element.drugQtyTo === 100) {
                                $("#drugAdditionalMarginPricesId2").val(element.id);
                                $("#drugMarginValue2").val(element.drugMarginValue);
                                $("#drugAdditionalMarginPricesList2\\.drugQtyFrom").val(element.drugQtyFrom);
                                $("#drugAdditionalMarginPricesList2\\.drugQtyTo").val(element.drugQtyTo);
                                $('#drugMarginValue2').prop('readonly', false);
                            }
                            if (element.drugQtyFrom === 101 && element.drugQtyTo === 180) {
                                $("#drugAdditionalMarginPricesId3").val(element.id);
                                $("#drugMarginValue3").val(element.drugMarginValue);
                                $("#drugAdditionalMarginPricesList3\\.drugQtyFrom").val(element.drugQtyFrom);
                                $("#drugAdditionalMarginPricesList3\\.drugQtyTo").val(element.drugQtyTo);
                                $('#drugMarginValue3').prop('readonly', false);
                            }
                            if (element.drugQtyFrom > 180) {
                                $("#drugAdditionalMarginPricesId4").val(element.id);
                                $("#drugMarginValue4").val(element.drugMarginValue);
                                $("#drugAdditionalMarginPricesList4\\.drugQtyFrom").val(element.drugQtyFrom);
                                $("#drugAdditionalMarginPricesList4\\.drugQtyTo").val(element.drugQtyTo);
                                $('#drugMarginValue4').prop('readonly', false);
                            }

                        });
                    }
                });
            } else {
                $("input").val("");
                $("#drugMarginValue0").attr('readonly', true);
                $("#drugMarginValue1").attr('readonly', true);
                $("#drugMarginValue2").attr('readonly', true);
                $("#drugMarginValue3").attr('readonly', true);
                $("#drugMarginValue4").attr('readonly', true);
            }
        }
    </script>
</body>
</html>