<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <c:if test="${drug.drugId!=null && drug.drugId!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Compound Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Compound
                    </c:if>
                    <c:if test="${drug.drugId==null || drug.drugId==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Compound Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Compound
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${drug.drugId!=null && drug.drugId!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Compound</h1>
                    </c:if>
                    <c:if test="${drug.drugId==null || drug.drugId==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Compound</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/drugIngredient/add" commandName="drug">
                    <form:hidden path="drugId" />

                    <input type="hidden" id="type" value="1" />
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <div class="errorMessage" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            <br clear="all">
                        </div>


                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/drugIngredient/list'"><a href="${pageContext.request.contextPath}/drugIngredient/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#drug').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid" style="height: auto;margin-bottom: 40px;padding-bottom:20px;background:#f7f7f7;">
                            <div class="search-grid">



                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label>    Generic Drug Title:<span style="color:red">*</span></label>
                                        <form:select path="drugBrand.drugBrandId" cssClass="form-control selectpicker show-tick" onchange="cascadingDropDown(this.value,'drugStrength')">
                                            <form:option value="0" label="Please Select" />
                                            <form:options items="${getGenericDrugList}" itemValue="drugBrandId" itemLabel="brandName" />
                                        </form:select>
                                        <form:errors path="drugBrand.drugBrandId" cssClass="errorMessage"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label>Drug Strength:<span style="color:red">*</span></label>  
                                        <form:select id="drugStrength" path="selectedDrugId" cssClass="form-control selectpicker show-tick">
                                            <form:option value="0" label="Please Select" />
                                            <form:options items="${drugList}" itemValue="drugId" itemLabel="ndcstrengthvalue" />
                                        </form:select>
                                        <form:errors path="selectedDrugId" cssClass="errorMessage"/>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <div class="row grid" style="height: auto;margin-bottom: 40px;padding-bottom:20px;background:#f7f7f7;margin-top: -35px;">
                            <div class="search-grid">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 for-320" id = "response" style="padding-top:5px;">  
                                    <div id="dynamicIngredientTableDiv" style="padding-left:0px;" >
                                        <table class="display" style="padding-top: 12px;" id="dynamicIngredientTable">
                                            <tbody>
                                            <thead style="border-top:none !important;border-bottom:none !important;">
                                                <tr>
                                                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                        Compound:<span style="color:red">*</span>
                                                    </th>
                                                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                        NDC Number:<span style="color:red">*</span>
                                                    </th>
                                                    <th class="col-lg-2 col-md-3 col-sm-2 col-xs-2 hidden-sm hidden-xs" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                        Percentage(%):<span style="color:red">*</span>
                                                    </th>
                                                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                        Cost($):<span style="color:red">*</span>
                                                    </th> 
                                                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                        Min Offer($):<span style="color:red">*</span>
                                                    </th> 
                                                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden-sm hidden-xs" style="font-weight:normal;color:#5B585F;font-size: 13px;">
                                                        Max Offer($):<span style="color:red">*</span>
                                                    </th> 
                                                </tr>   
                                            </thead>
                                            <c:set var="drugIngredientslength" value="${fn:length(drug.drugIngredients)}" scope="page"/>
                                            <c:if test="${drugIngredientslength gt 0}">
                                                <c:set var="drugIngredientlength" value="${drugIngredientslength - 1}" scope="page"/>
                                            </c:if>
                                            <c:forEach var="rowCounter" begin="0" step="1" end="${drugIngredientlength}"> 
                                                <tr id="drugIngredient-${rowCounter}">
                                                    <td valign="top" class="col-lg-2 col-md-2 col-xs-2">
                                                        <form:select id="drugIngredients[${rowCounter}].ingredients.id" path="drugIngredients[${rowCounter}].ingredients.id" cssClass="form-control selectpicker show-tick">
                                                            <form:option value="0" label="Please Select" />
                                                            <form:options items="${ingredientsList}" itemValue="id" itemLabel="name" />
                                                        </form:select>
                                                        <form:errors path="drugIngredients[${rowCounter}].ingredients.id" cssClass="errorMessage"/>
                                                    </td>
                                                    <td class="col-lg-2 col-md-2 col-xs-2" valign="top">
                                                        <form:input path="drugIngredients[${rowCounter}].ndcNo" cssClass="form-control" maxlength="11" onkeypress="return IsDigit(event)" title="This must be a number"/>
                                                        <form:errors path="drugIngredients[${rowCounter}].ndcNo" cssClass="errorMessage" />
                                                    </td>
                                                    <td class="col-lg-2 col-md-2 col-xs-2" valign="top">
                                                        <form:input path="drugIngredients[${rowCounter}].percentage" cssClass="form-control str" maxlength="3" onkeypress="return IsDigit(event)" title="This must be a number"/>
                                                        <form:errors path="drugIngredients[${rowCounter}].percentage" cssClass="errorMessage" />
                                                    </td>
                                                    <td class="col-lg-2 col-md-2 col-xs-2" valign="top">
                                                        <form:input path="drugIngredients[${rowCounter}].cost" cssClass="form-control" onkeypress="return isFloatNumber(this,event)"/> 
                                                        <form:errors path="drugIngredients[${rowCounter}].cost" cssClass="errorMessage" />
                                                    </td>
                                                    <td class="col-lg-2 col-md-2 col-xs-2" valign="top">
                                                        <form:input path="drugIngredients[${rowCounter}].minOffer" cssClass="form-control" onkeypress="return isFloatNumber(this,event)"/> 
                                                        <form:errors path="drugIngredients[${rowCounter}].minOffer" cssClass="errorMessage" />
                                                    </td>
                                                    <td class="col-lg-1 col-md-1 col-xs-1" valign="top">
                                                        <form:input path="drugIngredients[${rowCounter}].maxOffer" cssClass="form-control" onkeypress="return isFloatNumber(this,event)"/> 
                                                        <form:errors path="drugIngredients[${rowCounter}].maxOffer" cssClass="errorMessage" />
                                                    </td>
                                                    <td class="col-lg-1 col-md-1 col-xs-1" valign="top" style="padding-top: 10px;">
                                                        <c:if test="${rowCounter eq 0}">
                                                            <a id="addMore" href="#" class="fa fa-plus-circle" style="position: static;" onclick="addNewDetail();"></a>
                                                        </c:if>
                                                        <c:if test="${rowCounter ne 0}">
                                                            <a id="deleteButton" class="fa fa-minus-circle" href="#" style="position: static;" onclick="delRow(this)"></a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach> 

                                            </tbody>        
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </form:form>
                </div> <!-- /content -->
                <jsp:include page="./inc/footer.jsp" />
            </div> <!-- /wrapper -->
            <script type="text/javascript">
                function cascadingDropDown(parentVal, child) {
                    if (parentVal > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/drugIngredient/drug/" + parentVal,
                            dataType: "json",
                            success: function(data) {
                                $('[id="' + child + '"]')
                                        .find('option')
                                        .remove()
                                        .end()
                                        .append('<option value="0">Please Select</option>')
                                        .val('Select One');
                                $.each(data, function(index, element) {
                                    $('[id="' + child + '"]').append($('<option>', {value: element.drugId, text: element.ndcstrengthvalue}));
                                });
                                $('[id="' + child + '"]').selectpicker('refresh');
                            }
                        });
                    }
                    else {
                        $('[id="' + child + '"]')
                                .find('option')
                                .remove()
                                .end()
                                .append('<option value="0">Please Select</option>')
                                .val('Select One');
                        $('[id="' + child + '"]').selectpicker('refresh');

                    }
                }
                function addNewDetail()
                {
                    var table = document.getElementById("dynamicIngredientTable");
                    var select = document.getElementsByTagName('select');
                    var row = document.createElement("tr");
                    row.setAttribute("id", 'trId' + 1);
                    var index = table.rows.length - 1;
                    var isIE = /*@cc_on!@*/false || !!document.documentMode; // At least IE6
                    for (var i = 0; i <= index; i++) {
                        if ($("#drugIngredients" + i + "\\.ingredients\\.id").selectedIndex == '0' || $("#drugIngredients" + i + "\\.ndcNo").val() == ''
                                || $("#drugIngredients" + i + "\\.percentage").val() == '' || $("#drugIngredients" + i + "\\.cost").val() == '' || $("#drugIngredients" + i + "\\.minOffer").val() == '' || $("#drugIngredients" + i + "\\.maxOffer").val() == ''){
                            document.getElementById("errorMessage").style.display = "block";
                            document.getElementById("errorMessage").innerHTML = "All dynamic field(s) required";
                            return;
                        }
                    }
                    var innerHtml = "";
                    if (isIE) {
                        innerHtml += '<br clear="all" /><div style="width:603%; padding-left:0px;" class="col-lg-12"><table class="col-lg-12"><tr>';
                    }
                    innerHtml += '<td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs">';
                    innerHtml += '<select id="drugIngredients[' + index + '].ingredients.id" name="drugIngredients[' + index + '].ingredients.id" class="form-control selectpicker show-tick">';
                    innerHtml += select[2].innerHTML;
                    innerHtml += '</select>';
                    innerHtml += '</td>';
                    innerHtml += ' <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs">';
                    innerHtml += '<input class="form-control" id="drugIngredients' + index + '.ndcNo" name="drugIngredients[' + index + '].ndcNo" maxlength="11" onkeypress="return IsDigit(event)" title="This must be a number"/>';
                    innerHtml += '</td>';
                    innerHtml += ' <td class="col-lg-2 col-md-2 col-sm-2 col-xs-3 hidden-sm hidden-xs">';
                    innerHtml += '<input id="drugIngredients' + index + '.percentage" name="drugIngredients[' + index + '].percentage" class="form-control" maxlength="3" onkeypress="return IsDigit(event);" />';
                    innerHtml += '</td>';
                    innerHtml += ' <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs">';
                    innerHtml += '<input class="form-control" type="text" id="drugIngredients' + index + '.cost" name="drugIngredients[' + index + '].cost" onkeypress="return isFloatNumber(this,event)"/>';
                    innerHtml += '</td>';
                    innerHtml += ' <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs">';
                    innerHtml += '<input class="form-control" type="text" id="drugIngredients' + index + '.minOffer" name="drugIngredients[' + index + '].minOffer" onkeypress="return isFloatNumber(this,event)"/>';
                    innerHtml += '</td>';
                    innerHtml += ' <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden-sm hidden-xs">';
                    innerHtml += '<input class="form-control" type="text" id="drugIngredients' + index + '.maxOffer" name="drugIngredients[' + index + '].maxOffer" onkeypress="return isFloatNumber(this,event)"/>';
                    innerHtml += '</td>';
                    innerHtml += ' <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden-sm hidden-xs">';
                    innerHtml += '<a id="deleteButton" class="fa fa-minus-circle" href="#" style="position: static;"></a>';
                    innerHtml += '</td>';
                    if (isIE) {
                        innerHtml += '</tr></table></div>';
                    }
                    row.innerHTML = innerHtml;
                    table.appendChild(row);
                    var id = 'drugIngredients[' + index + '].ingredients.id';
                    document.getElementById(id).selectedIndex = "0";
                }

                $('table').on('click', '#deleteButton', function(e) {
                    $(this).closest('tr').remove();
                });
            </script>
    </body>
</html>
