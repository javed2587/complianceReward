<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body onload="startTimer();">
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <c:if test="${drugBrand.id!=null && drugBrand.id!=''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Setup 
                    </c:if>
                    <c:if test="${drugBrand.id==null || drugBrand.id==''}">
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Setup 
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${drugBrand.id!=null && drugBrand.id!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Compound</h1>
                    </c:if>
                    <c:if test="${drugBrand.id==null || drugBrand.id==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp; Drug Setup</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form id="drugBrandFormId" action="${pageContext.request.contextPath}/drugBrand/add" commandName="drugSetupAddBean">
                    
                    <div class="page-message">
                        <c:if test="${not empty message}"><div class="errorMessage messageheading" id="message">${message}</div></c:if>
                        <div class="errorMessage messageheading" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                        <div class="message messageheading" id="messageJavaScript"></div>
                        
                        <br clear="all">
                    </div>


                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/drugBrand/list/category'"><a href="${pageContext.request.contextPath}/drugBrand/list/category" class="btn_Color">Cancel</a></div>


                            <div class="btn-group">
                                <c:if test="${drugSetupAddBean.saveAction eq true}">
                                    <div class="btndrop btn-margin" onclick="$('#drugBrandFormId').submit();">
                                        <a class="btn_Color">Save</a>
                                    </div>
                                </c:if>
                                <c:if test="${drugSetupAddBean.updateAction eq true}">
                                    <div class="btndrop btn-margin" onclick="$('#drugBrandFormId').submit();">
                                        <a class="btn_Color">Update</a>
                                    </div>
                                </c:if>
                                <c:if test="${drugSetupAddBean.deleteAction eq true}">
                                    <div class="btndrop btn-margin" onclick="deleteDrugs();">
                                        <a class="btn_Color">Delete</a>
                                    </div>
                                </c:if>
                            </div>  
                        </div>
                        <br><br>
                        
                        <div class="row grid padd-top-10 padding-bottom-10" style="height: auto;margin-bottom: 10px;padding-bottom: 10px;background:#f7f7f7;">

                           
                            
                            <div class="search-grid">
                                <div class="form-group">  
                                    <div class="col-sm-2 padd-left-zero" style="width:22%;">  
                                        <label>Drug Category:<span style="color:red">*</span></label>
                                        <form:hidden id="searchDrugCatId" path="drugCategoryId" autocomplete="off" value=""/>
                                        <c:if test="${(drugSetupAddBean.updateAction eq true) || (drugSetupAddBean.deleteAction eq true)}">
                                            <form:input path="drugCategoryName" cssClass="form-control" readonly="true"/>
                                        </c:if>
                                        <c:if test="${drugSetupAddBean.saveAction eq true}">
                                            <input id="drugCatId" type="text" class="form-control selectpicker show-tick" oninput="window.DrugLookup.lookDrugCategory();"/>
                                            <div>
                                                <a id="addMore" href="#" class="fa fa-plus-circle" style="position:relative;top: -21px;float:right;left: 20px;" onclick="window.drugSetup.ShowDialogBoxDrugCategory();"></a>
                                            </div>
                                        </c:if>
                                        
                                    </div>

                                    <div class="col-sm-2 padd-left-zero" style="width:22%;margin-left: 40px;">  
                                        
                                        <label>Therapy Class:<span style="color:red">*</span></label>
                                        <form:hidden id="searchTherapyClassId" path="therapyClassId" autocomplete="off" value=""/>
                                        <c:if test="${(drugSetupAddBean.updateAction eq true ) || (drugSetupAddBean.deleteAction eq true)}">
                                            <form:input path="therapyClassName" cssClass="form-control" readonly="true"/>
                                        </c:if>
                                        <c:if test="${drugSetupAddBean.saveAction eq true}">
                                            <input id="lookupTherapyClassId" type="text" class="form-control selectpicker show-tick" oninput="window.DrugLookup.lookupTherapyClass();"/>
                                        
                                            <div>
                                                <a id="addMore" href="#" class="fa fa-plus-circle" style="position:relative;top: -21px;float:right;left: 20px;" onclick="window.drugSetup.ShowDialogBoxTherapyClass();"></a>
                                            </div>
                                      </c:if>  
                                    </div>
                                    <div class="col-sm-2 padd-left-zero" style="width:22%;margin-left: 40px;">  
                                        <label>HICL/Generic Name:<span style="color:red">*</span></label>
                                        <form:hidden id="searchgenericNameId" path="genericNameId" autocomplete="off" value=""/>
                                         <c:if test="${(drugSetupAddBean.updateAction eq true ) || (drugSetupAddBean.deleteAction eq true)}">
                                            <form:input path="genericNameName" cssClass="form-control" readonly="true"/>
                                        </c:if>
                                        <c:if test="${drugSetupAddBean.saveAction eq true}">
                                            <input id="lookupgenericNameId" type="text" class="form-control selectpicker show-tick" oninput="window.DrugLookup.lookupGenericName();"/>
                                            <div>
                                                <a id="addMore" href="#" class="fa fa-plus-circle" style="position:relative;top: -21px;float:right;left: 20px;" onclick="window.drugSetup.ShowDialogBoxGenericName();"></a>
                                            </div>
                                        </c:if>  
                                    </div>
                                    <div class="col-sm-2 padd-left-zero" style="width:22%;margin-left: 40px;">  
                                        <label>Trade/Brand Name:<span style="color:red">*</span></label>
                                         <form:hidden id="searchbrandNameId" path="brandNameId" autocomplete="off" value=""/>
                                        <c:if test="${(drugSetupAddBean.updateAction eq true ) || (drugSetupAddBean.deleteAction eq true)}">
                                            <form:input path="brandNameName" cssClass="form-control" readonly="true"/>
                                        </c:if>
                                        <c:if test="${drugSetupAddBean.saveAction eq true}">
                                            <input id="lookupBrandNameId" type="text" class="form-control selectpicker show-tick" oninput="window.DrugLookup.lookupBrandName();"/>
                                            <div>
                                                <a id="addMore" href="#" class="fa fa-plus-circle" style="position:relative;top: -21px;float:right;left: 20px;" onclick="window.drugSetup.ShowDialogBoxBrandName();"></a>
                                            </div>
                                       </c:if> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row grid1 padd-bottom-zero" style="height: auto;margin-bottom: 40px;padding-bottom:20px;background:#f7f7f7;margin-left: 15px;margin-right: 15px;">
                        <div class="search-grid">
                            
                                <div class="drugListWidth col-lg-12 col-md-12 col-sm-12 col-xs-12 for-320" id="drugList" style="padding-top:5px;width:100%;float:left;">
                                    <div id="drugs-0" style="padding-left:0px;" >
                                        <!-- Parent  Label Div -->
                                        <div class="col-sm-12">
                                            
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 115px;">
                                                <label>Strength<span style="color:red">*</span></label>
                                            </div>
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 150px;">
                                                <label>MOU<span style="color:red">*</span></label>
                                            </div>
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 145px;">
                                                 <label>Type<span style="color:red">*</span></label>
                                            </div>
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 125px;">
                                                  <label>GCN#<span style="color:red">*</span></label>
                                            </div>
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 120px;">
                                                 <label>GPI#<span style="color:red">*</span></label>
                                            </div>
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 225px;">
                                                <label>Route Description</label>
                                            </div>
                                            
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">
                                                <label>MAC/Unit Price ($)<span style="color:red">*</span></label>
                                            </div>
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">
                                                <label>AWP/Unit Price ($)<span style="color:red">*</span></label>
                                            </div>
                                            <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">
                                                <label>DEA Schedule</label>
                                            </div>
                                            <c:if test="${drugSetupAddBean.deleteAction eq true}">
                                                <div class="col-sm-2" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 100px;text-align: left;">
                                                    <input id="selectAllId" name="selectedAllIds" type="checkbox" value="" class="transViewClass"/>
                                                </div>
                                            </c:if>
                                        </div>
                                        
                                        <!--  Parent Record Div start -->
                                    <c:set var="drugslength" value="${fn:length(drugSetupAddBean.drugSetupDrugBean)}" scope="page"/>
                                    <c:if test="${drugslength gt 0}">
                                        <c:set var="drugslength" value="${drugslength - 1}" scope="page"/>
                                    </c:if>
                                   <c:forEach var="rowCounter" begin="0" step="1" end="${drugslength}">     
                                     <div id="drugRecordDiv">
                                        
                                     <input id="counter" type="hidden" value="0"/>
                                     <div id="0" class="col-sm-12">
                                         <form:hidden id="drugSetupDrugBean[${rowCounter}].drugId" path="drugSetupDrugBean[${rowCounter}].drugId"/>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 115px;padding-top:13px;">
                                                <form:input path="drugSetupDrugBean[${rowCounter}].strength" cssClass="form-control" maxlength="5" onkeypress="return IsDigit(event)"/>
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].strength" cssClass="errorMessage" />
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 150px;">
                                                <form:select path="drugSetupDrugBean[${rowCounter}].mou" cssClass="form-control selectpicker show-tick">
                                                    <form:option value="0" label="Select One" />
                                                    <form:options items="${drugUnitList}" itemValue="id" itemLabel="name" />
                                                    
                                                </form:select>
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].mou" cssClass="errorMessage" />
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 145px;">
                                                <form:select path="drugSetupDrugBean[${rowCounter}].type" cssClass="form-control selectpicker show-tick">
                                                    <form:option value="0" label="Select One" />
                                                    <form:option value="TABLET" label="TABLET"/>
                                                    <form:option value="CAPSULE" label="CAPSULE"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 125px;">
                                               
                                                <form:input path="drugSetupDrugBean[${rowCounter}].gcn" cssClass="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/> 
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].gcn" cssClass="errorMessage" />
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 120px;">
                                                
                                                <form:input path="drugSetupDrugBean[${rowCounter}].gpi" cssClass="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/> 
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].gpi" cssClass="errorMessage" />
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 225px;">
                                                <form:input path="drugSetupDrugBean[${rowCounter}].route" cssClass="form-control3"/> 
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].route" cssClass="errorMessage" />
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">
                                               
                                                <form:input path="drugSetupDrugBean[${rowCounter}].mac" cssClass="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/> 
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].mac" cssClass="errorMessage" />
                                            </div>
                                        </div>
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">
                                                
                                                <form:input path="drugSetupDrugBean[${rowCounter}].awp" cssClass="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/> 
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].awp" cssClass="errorMessage" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group padd-left-zero">
                                            <div class="col-lg-2 col-md-12 col-sm-12 col-xs-10" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">
                                                
                                                <form:input path="drugSetupDrugBean[${rowCounter}].deaSchedule" cssClass="form-control3" maxlength="5" onkeypress="return isFloatNumber(this,event)"/> 
                                                <form:errors path="drugSetupDrugBean[${rowCounter}].deaSchedule" cssClass="errorMessage" />
                                                <c:if test="${drugSetupAddBean.saveAction eq true}">
                                                    <a id="addMore" href="#" class="fa fa-plus-circle" style="position:relative;top: -21px;float:right;left: 20px;" onclick="window.drugSetup.DrugHtml();"></a>
                                                </c:if>
                                                <c:if test="${drugSetupAddBean.deleteAction eq true}">
                                                    <form:checkbox path="drugSetupDrugBean[${rowCounter}].deleteRecord" style="position:relative;top: -21px;float:right;left: 20px;" cssClass="checkBox1"/>
                                                </c:if>    
                                            </div>
                                        </div>
                                     </div>
                                  </div><!-- end record div -->
                                </c:forEach>  
                             </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="dialog" style="height: 300px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
            <div id="dialogDrugCategory" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
            <div id="dialogTherapyClass" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
            <div id="dialogGenericName" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
            <div id="dialogBrandName" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
        </form:form>

    </div> <!-- /content -->
    <jsp:include page="./inc/footer.jsp" />
    
</div> <!-- /wrapper -->

<!--  Page java script files   -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugSetupInit.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugLookup.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/drugSetup.js"></script>
<script type="text/javascript">
    
    /**
     * 
     * Document ready function
     * 
     */
    $(document).ready(function() {
        window.DrugSetup.initDrugSetup();
        window.DrugLookup.populateAppUrl('${pageContext.request.contextPath}');
        window.drugSetup.populateAppUrl('${pageContext.request.contextPath}');
        //************************ select All check box Drugs ********************
    $("#selectAllId").on("click",function(){
        $("input:checkbox").prop('checked', $(this).prop("checked"));
    });
    }); //************************  Document ready end *******************************
    
    
    function isNumber(evt) {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }

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
        document.getElementById("errorMessage").style.display = "none";
        document.getElementById("messageJavaScript").style.display = "none";
    }

    function startTimer() {
        window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
    }
    function addNewDetail(id)
    {
        var i = $("#remove").val();
        var index = ${fn:length(drugBrand.drugs)-1};
        var select = document.getElementsByTagName('select');
        if (index == 0 && i == 0) {
            index = 1;
        } else {
            index = parseInt(i) + 1;
        }
        var isIE = /*@cc_on!@*/false || !!document.documentMode; // At least IE6
        for (var i = 0; i <= index; i++) {
            if ($("#drugs" + i + "\\.strength").val() == '' || $("#drugs" + i + "\\.drugGcn").val() == '' || $("#drugs" + i + "\\.drugMacPrice").val() == '' || $("#drugs" + i + "\\.drugAwpPrice").val() == ''
                    || $("#drugs" + i + "\\.daeSchedule").val() == '') {
                document.getElementById("errorMessage").style.display = "block";
                document.getElementById("errorMessage").innerHTML = "All dynamic field(s) required";
                return;
            }
        }
        var html = "";

        html += '<div id="drugs-' + index + '" style="margin-top:12px;width:100%; float:left;"><div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="width:105px;">';
        html += '<input class="form-control" id="drugs' + index + '.strength" name="drugs[' + index + '].strength" maxlength="5" onkeypress="return IsDigit(event)"/>';
        html += '</div>';
        html += ' <div class="col-lg-1 col-md-12 col-sm-2 col-xs-12" style="width:135px;">';
        html += '<select id="drugs' + index + '.drugUnits.id" name="drugs[' + index + '].drugUnits.id" class="form-control selectpicker show-tick" style="">';
        html += select[4].innerHTML;
        html += '</select>';
        html += '</div>';
         html += ' <div class="col-lg-1 col-md-12 col-sm-2 col-xs-12" style="width:135px;">';
        html += '<select id="drugs' + index + '.drugType" name="drugs[' + index + '].drugType" class="form-control selectpicker show-tick" style="">';
        html += select[5].innerHTML;
        html += '</select>';
        html += '</div>';
        html += ' <div class="col-lg-1 col-md-12 col-sm-2 col-xs-12" style="width:105px;">';
        html += '<input id="drugs' + index + '.drugGcn" name="drugs[' + index + '].drugGcn" class="form-control" maxlength="11" onkeypress="return IsDigit(event);" title="This must be a number" />';
        html += '</div>';
        html += ' <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="width:105px;">';
        html += '<input class="form-control" type="text" id="drugs' + index + '.drugGpi" name="drugs[' + index + '].drugGpi" onkeypress="return isFloatNumber(this,event)" maxlength="7"/>';
        html += '</div>';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="width:125px;"> ';
        html += '<input class="form-control" type="text" id="drugs' + index + '.routeDescription" name="drugs[' + index + '].routeDescription" onkeypress="return isFloatNumber(this,event)" maxlength="7"/>';
        html += '</div>';
        html += ' <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="width:125px;"> ';
        html += '<input class="form-control" type="text" id="drugs' + index + '.drugMacPrice" name="drugs[' + index + '].drugMacPrice" onkeypress="return isFloatNumber(this,event)" maxlength="7"/>';
        html += '</div>';
        html += ' <div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="width:130px;"> ';
        html += '<input class="form-control" type="text" id="drugs' + index + '.drugAwpPrice" name="drugs[' + index + '].drugAwpPrice" onkeypress="return isFloatNumber(this,event)" maxlength="7"/>';
        html += '</div>';
        html += ' <div class="col-lg-2 col-md-12 col-sm-12 col-xs-10" style="width:125px;"> ';
        html += '<input class="form-control3" type="text" id="drugs' + index + '.daeSchedule" name="drugs[' + index + '].daeSchedule" onkeypress="return isFloatNumber(this,event)" maxlength="5"/>';
        html += '<a id="deleteButton" class="fa fa-minus-circle" href="#" style="position:relative;top: -21px; left: 5px;float:right;" onclick=deleteDiv("drugs-' + index + '")></a>';
        html += '</div> </div>';
        $("#drugs-" + index).show();
        $("#" + id).append(html);
        $("#remove").val(index);
        var drugUnitId = 'drugs' + index + '.drugUnits.id';
        var drugTypeId='drugs' + index + '.drugType';
        document.getElementById(drugUnitId).selectedIndex = "0";
        $('[id="' + drugUnitId + '"]').selectpicker('refresh');
        document.getElementById(drugTypeId).selectedIndex = "0";
        $('[id="' + drugTypeId + '"]').selectpicker('refresh');
        index++;
    }
    function deleteRow(btn, id, divId) {
        var drugId = document.getElementById(id).value;
        if (drugId > 0) {
            $.ajax({
                url: "${pageContext.request.contextPath}/drugBrand/deleteDrug/" + drugId,
                type: "POST",
                async: false,
                success: function (data) {
                    if (data == true) {
                        $("#" + divId).remove();
                    } else {
                        document.getElementById("errorMessage").style.display = "block";
                        document.getElementById("errorMessage").innerHTML = "Unable to delete, record is associated with further records.";
                    }
                }, error: function (e) {
                    alert('Error while request...' + e);
                }
            });
        } else {
            $("#" + divId).remove();
        }
    }
    function deleteDiv(id) {
         $("#drugs-" + id).remove();
    }
    function fileCheck(obj) {
        var fileExtension = ['jpeg', 'jpg', 'png', 'JPEG', 'PNG', 'JPG'];
        if ($.inArray($(obj).val().split('.').pop().toLowerCase(), fileExtension) == -1)
            document.getElementById("errorMessage").style.display = "block";
        document.getElementById("errorMessage").innerHTML = "Invalid image format,Please enter only jpg or png images.";

    }
    function load_image(id, ext)
    {
        if (validateExtension(ext) == false)
        {
            document.getElementById("errorMessage").style.display = "block";
            document.getElementById("errorMessage").innerHTML = "Invalid image format,Please enter only jpg or png images.";
            document.getElementById(id).value = "";
            return;
        }
    }

    function validateExtension(v)
    {
        var allowedExtensions = new Array("jpg", "JPG", "jpeg", "JPEG", "PNG", "png");
        for (var ct = 0; ct < allowedExtensions.length; ct++)
        {
            sample = v.lastIndexOf(allowedExtensions[ct]);
            if (sample != -1) {
                return true;
            }
        }
        return false;
    }
    //$('input[type=file]').bootstrapFileInput();
    $('.file-inputs').bootstrapFileInput();
    $('Input').bind("paste", function (e) {
        e.preventDefault();
    });
    function calculateAWP(index) {
        var wacp = $("#drugs" + index + "\\.wacPkgPrice").val();
        var speard = $("#drugs" + index + "\\.spread").val();
        if (wacp != '' || speard != '') {
            var result = (Math.round(wacp * speard) / 100).toFixed(2);
            var res = parseFloat(wacp) + parseFloat(result);
            $("#drugs" + index + "\\.awpCurrentPkgPrice").val(res);
        } else {
            document.getElementById("errorMessage").style.display = "block";
            document.getElementById("errorMessage").innerHTML = "All dynamic field(s) required";
        }
    }
    function showDialog(type, title) {
        alert;
        var w;
        if (window.screen.width > 360) {
            w = 360;
        } else {
            w = $(window).width() * 0.9;
        }
        var dialog = $("#dialog").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: w,
            cache: false,
            title: title,
            open: function (event, ui) {
                //hide titlebar.
                $(".ui-dialog-titlebar").css("font-size", "14px");
                $(".ui-dialog-titlebar").css("font-weight", "normal");
            },
            close: function ()
            {
                $(this).dialog('close');
                $(this).dialog('destroy');
                $(this).html("");
            }
        });
        var html = "<div class='col-sm-4'>"
        html += "<div class='input-group'>";
        html += "<input type ='text' style='width:360%;' class='form-control'>";
        html += "</div>";
        html += "</div>";
        html += "<div style=padding-top:5px;>";
        html += "<div style='float: left; padding-top:35px;margin-left:30px;'>";
        html += "<button  class = 'btndrop btn-margin' type='button' class = 'btn_Color' onclick='hideModel();' >Add</button></a>";
        html += "</div >";
        $(dialog).html(html);
        $(dialog).dialog("open");
    }
    
    function deleteDrugs(){
        
	var r = confirm("Do you really want to delete Drugs(s)?");
	if (r == true) {
            $('#drugBrandFormId').submit();
	} else {
            return;
	}
    }
</script>
</body>
</html>

