<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/newMenu.jsp" />

                <c:set var="mode" value="Add" ></c:set>
                <c:if test="${campaigns.campaignId!=null && campaigns.campaignId!=''}">
                    <c:set var="mode" value="Edit" ></c:set>
                </c:if>

                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Campaigns <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> ${mode} Campaign
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;${mode} Campaign</h1>
                </div> <!-- /header -->

                <style>
                    .hidde {
                        display: none;
                    }
                </style>
                <form:form action="${pageContext.request.contextPath}/campaign/add" commandName="campaigns" method="post" prependId="false" onsubmit="return validateLength()">
                    <form:hidden path="campaignId" />
                    <form:hidden path="campaignType" />
                    <form:hidden path="isDelete" />
                    <form:hidden path="createdBy" />
                    <form:hidden path="createdOn" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <div class="errorMessage" id="errorMessage"><c:if test="${not empty message}">${message}</c:if></div> <br clear="all">

                        </div>  

                        <div class="wrp clearfix" style="padding-left: 15px; padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/campaign/list/${type}'"><a href="${pageContext.request.contextPath}/campaign/list/${type}" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#campaigns').submit();"><a class="btn_Color">Save</a></div>
                            </div>  
                        </div>
                        <br><br>
                        <div class="row grid " style="height: auto;margin-bottom:50px;">
                            <div class="search-grid">

                                <!--                                <h3 class="demo">Basic Setup</h3>-->
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-bottom-10 padding-top-10" style="background:#f7f7f7;padding-right:10px;">

                                    <div class="form-group fgPL-zero" style="position:relative;top:15px;">
                                        <div class="col-sm-3">
                                            <label>Campaign Title:<span style="color: red;">*</span></label>  

                                            <form:input path="campaignName" maxlength="255" cssClass="form-control" onkeypress="return IsAlphaNumeric(event);" id="campaignTitle" onchange="return validateLength()" />
                                            <form:errors path="campaignName" cssClass="errorMessageValid" />  
                                            <c:if test="${not empty message1}"><div class="errorMessage">${message1}</div></c:if>
                                                <span id="myspan" class="errorMessage1" style="display: none;">Minimum length 4 character</span>
                                            </div>  
                                        </div>


                                        <div class="form-group fgPL-zero">
                                            <div class="col-sm-3">
                                                <label>Industry:<span style="color: red;">*</span></label>

                                            <form:select path="industry.industryId" class="form-control selectpicker show-tick" >
                                                <form:option value="0">Please select</form:option>
                                                <form:options items="${industryList}" itemValue="industryId" itemLabel="industryTitle" />
                                            </form:select>
                                            <c:if test="${not empty message5}"><div class="errorMessage">${message5}</div></c:if>

                                            </div>
                                        </div>

                                        <div class="form-group fgPL-zero">
                                            <div class="col-sm-3 margin-bottom-zero" style="margin-bottom:-12px;">
                                                <label>Compound(s):<span style="color:red">*</span></label>

                                            <form:select path="selectedDrugBrands"  multiple="true" class="form-control selectpicker show-tick" style="height:40px;">

                                                <c:forEach var="brnd" items="${brands}">
                                                    <form:option value="${brnd.drugBrandId}"><c:out value="${brnd.genericName} (${brnd.brandName})"/> </form:option>
                                                </c:forEach>
                                            </form:select> 
                                            <c:if test="${not empty message2}"><div class="errorMessage">${message2}</div></c:if>
                                            </div>
                                        </div>

                                        <div class="form-group fgPL-zero">
                                            <div class="col-sm-3" style="margin-bottom:12px;">
                                                <label>Short Code:<span style="color: red;">*</span></label>

                                            <form:select path="shortCodes.shortCode" class="form-control selectpicker show-tick">
                                                <form:option value="0">Please select</form:option>
                                                <form:options items="${shortCodeList}" itemValue="shortCode" itemLabel="shortCode" />
                                            </form:select>
                                            <c:if test="${not empty message4}"><div class="errorMessage">${message4}</div></c:if>
                                            </div>
                                        </div>  

                                        <div class="form-group fgPL-zero">
                                            <div class="col-sm-3">
                                                <label>Launch Date:<span style="color:red">*</span></label>

                                            <form:input path="lanchDateTime" id="datetimepicker" cssClass="form-control" placeholder="mm/dd/yyyy" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" />
                                            <form:errors path="lanchDateTime" cssClass="errorMessageValid" />
                                        </div>  
                                    </div>

                                    <div class="form-group fgPL-zero">  
                                        <div class="col-sm-3" style="margin-bottom:12px;">
                                            <label>Termination Date:<span style="color:red">*</span></label>

                                            <form:input path="terminationDateTime" id="datetimepicker1" cssClass="form-control" placeholder="mm/dd/yyyy" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" />
                                            <form:errors path="terminationDateTime" cssClass="errorMessageValid" />
                                        </div>
                                    </div>

                                    <div class="form-group fgPL-zero">
                                        <div class="col-sm-3">
                                            <label>Refill Process Timing:</label>

                                            <form:select path="refillProcessTiming" class="form-control selectpicker show-tick" >
                                                <form:option value="0">Please select</form:option>
                                                <c:forEach var="interval" items="${intervalList}">
                                                    <form:option value="${interval.intervalId}"><c:out value="${interval.intervalValue} ${interval.intervalsType.intervalsTypeTitle}"/></form:option>
                                                </c:forEach>
                                            </form:select>
                                            <c:if test="${not empty message8}"><div class="errorMessage">${message8}</div></c:if>
                                            </div>
                                        </div>


                                        <div class="form-group fgPL-zero">
                                            <div class="col-sm-3">
                                                <label>SMTP:<span style="color:red">*</span></label>

                                            <form:select path="smtpServerInfo.smtpId" class="form-control selectpicker show-tick" >
                                                <form:option value="0">Please select</form:option>
                                                <form:options items="${smtpList}" itemValue="smtpId" itemLabel="outGoingSmtp" />
                                            </form:select>
                                            <c:if test="${not empty message6}"><div class="errorMessage">${message6}</div></c:if>
                                            </div>
                                        </div>
                                        <div class="form-group fgPL-zero">
                                            <div class="col-sm-3" style="clear:both; margin-bottom: 18px;">
                                                <label style="clear:right;">Advance Option(s):</label><br>
                                            <form:select id="advanceOptions" path="selectedOptions" name="advanceOptions" multiple="true" class="form-control selectpicker show-tick">
                                                <form:option  value="clinical_messages">Clinical Messages</form:option>
                                                <form:option  value="refill">Refill</form:option>
                                                <form:option  value="max_benefit">Max Benefit</form:option>
                                            </form:select>



                                            <c:set var="hidden" value="block" />
                                            ${isMaxBenefit}
                                            <c:if test="${campaigns.isMaxBenefit ne 'Yes'}" >
                                                <c:set var="hidden" value="none" />
                                            </c:if>
                                            <div id="maxbenifits"  style="display:${hidden};clear:both;margin: 8px -6px 10px -7px;">
                                                <div class="col-sm-6"> <form:input path="maxBenefitAmount" class="form-control" placeholder="Max Benefit Amount" onkeypress="return isFloatNumber(this,event)"/></div>
                                                <div class="col-sm-6"> <form:input path="redemptionLimit" class="form-control" placeholder="Redemption Limit" onkeypress="return IsDigit(event)"/>  </div>  
                                                <c:if test="${not empty message7}"><br><div class="errorMessage" style="padding-left: 9px;">${message7}</div></c:if>   
                                                </div>
                                                    <div class="hidden-xs" <c:if test="${empty message7}">style="padding-top: 29px;"</c:if>></div>
                                            <c:set var="hidden" value="block" />
                                            ${isClinicalMsgs}
                                            <c:if test="${campaigns.isClinicalMsgs ne 'Yes'}" >
                                                <c:set var="hidden" value="none" />
                                            </c:if>

                                            <div id="clinicalmsgs" style="display:${hidden}; clear: both; margin: 8px -6px 10px -7px;">
                                                <div class="col-sm-6"> <form:input path="clinicalMsgsCount" class="form-control" placeholder="Monthly Messages Limit" maxlength="1" onkeypress="return isNumber(event)" title="This must be a number"/></div> 
                                                <div class="col-sm-6"> <form:input path="clinicalMsgsTime" class="form-control" placeholder="Interval (in mint.)" onkeypress="return isNumber(event)" title="This must be a number" maxlength="5"/>  </div> 
                                                <c:if test="${not empty clinicalMessage}"><br><div class="errorMessage" style="padding-left: 9px;">${clinicalMessage}</div></c:if>   
                                                </div>
                                            </div>

                                        </div>          

                                        <div class="form-group fgPL-zero">
                                            <div class="col-sm-3">
                                                <label>Trigger(s):<span style="color:red">*</span></label> 

                                            <c:forEach items="${campaigns.triggers}" varStatus="loop">
                                                <c:if test="${campaigns.triggers[loop.index].remove ne 1}">

                                                    <div id="triggers${loop.index}" class="float-left" style="margin-bottom:8px;clear:left;width:100%;">
                                                        <form:input maxlength="10" path="triggers[${loop.index}].id.keyword" cssClass="form-control float-left margin-bottom-mobile55" cssStyle="width:96%;" onkeypress="return IsAlphaNumeric(event);" /> 
                                                        <form:hidden path="triggers[${loop.index}].remove" value="${triggers[loop.index].remove}" />

                                                        <c:if test="${loop.index ne 0}">
                                                            <a class="removetrigger btn-trigger Campaign-removetrigger" data-index="${loop.index}"><i class="fa fa-minus-circle"></i></a>
                                                            </c:if>    
                                                            <form:errors path="triggers[${loop.index}].id.keyword" cssClass="errorMessageValid" />

                                                    </div>

                                                </c:if>
                                            </c:forEach>


                                            <c:if test="${empty campaigns.triggers}">

                                                <form:input  path="triggers[0].id.keyword" cssClass="form-control" cssStyle="margin-bottom:8px; width:96%;" maxlength="10" onkeypress="return IsAlphaNumeric(event);" /> 
                                            </c:if>
                                            <a id="addtrigger" class="fa fa-plus-circle addtrigger"></a>

                                            <c:if test="${not empty message3}"><div class="errorMessage">${message3}</div></c:if>
                                            </div>
                                        </div>
                                        <div class="form-group fgPL-zero float-left full-width">
                                            <div class="col-sm-3"> 
                                                <label>Survey:<span style="color:red">*</span></label>
                                            <form:select path="survey.id" class="form-control selectpicker show-tick" >
                                                <form:option value="0">Please select</form:option>
                                                <form:options items="${surveyList}" itemValue="id" itemLabel="title" />
                                            </form:select>
                                            <c:if test="${not empty surveyMessage}"><div class="errorMessage">${surveyMessage}</div></c:if>
                                            </div>
                                        </div>
                                        <div class="form-group fgPL-zero float-left full-width">
                                            <div class="col-sm-3"> 
                                                <label>Status: </label><br>
                                                <div class="radio-inline">  

                                                    <label>
                                                    <form:radiobutton path="isActive" value="Yes"/>&nbsp;Active
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <form:radiobutton path="isActive" value="No"/>&nbsp;InActive
                                                </label>
                                            </div>  
                                        </div>
                                    </div> 


                                </div>

                            </div><!--search grid-->
                        </div><!--row grid-->   
                    </div>  
                </form:form>

                <script type="text/javascript">
                    $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                        format: 'm/d/Y',
                        onChangeDateTime: function(dp, $input) {
                            jQuery('#datetimepicker').datetimepicker('hide');
                            jQuery('#datetimepicker1').datetimepicker('hide');
                        }

                    });


                    $("#campaignTitle").focus();

                    //adding trigger dynamically
                    $(function() {
                        var index = ${fn:length(campaigns.triggers)};
                        if (index == 0) {
                            index = 1;
                        }
                        $("#addtrigger").off("click").on("click", function() {
                            $(this).before(function() {
                                for (var i = 0; i <= index; i++) {
                                    if ($("#triggers" + i + "\\.id\\.keyword").val() == '') {
                                        document.getElementById("errorMessage").style.display = "block";
                                        document.getElementById("errorMessage").innerHTML = "Trigger(s) value required";
                                        return;
                                    }
                                }
                                var html = '<div id="triggers' + index + '" class="clear-both-320" style="margin-bottom:-11px;">';

                                html += '<input maxlength="10" class="form-control margin-bottom-mobile" style="width:96%;margin-left:0px;" type="text" id="triggers' + index + '.id.keyword" name="triggers[' + index + '].id.keyword" onkeypress="return IsAlphaNumeric(event);" />';
                                html += '<input type="hidden" id="triggers' + index + '.remove" name="triggers[' + index + '].remove" value="0"/>';

                                html += '&nbsp;<a href="#" class="removetrigger btn-trigger" data-index="' + index + '"><i class="fa fa-minus-circle"></i></a>';
                                html += "</div>";
                                $("#triggers" + index).show();
                                index++;
                                return html;
                            });
                            return false;
                        });

                        $('body').on('click', '.removetrigger', function() {
                            var index2remove = $(this).data("index");
                            $("#triggers" + index2remove + '\\.id\\.keyword').val("1");
                            $("#triggers" + index2remove + '\\.remove').val("1");
                            $("#triggers" + index2remove).remove();
                            index--;
                            return false;
                        });
                    });

                    $(document).ready(function() {
                        $("select").change(function() {
                            $("#maxbenifits").hide();
                            $("#clinicalmsgs").hide();
                            $("select option:selected").each(function() {
                                if ($(this).attr("value") == "max_benefit") {
                                    $("#maxbenifits").show();
                                }
                                if ($(this).attr("value") == "clinical_messages") {
                                    $("#clinicalmsgs").show();
                                }
                            });
                        }).change();
                    });
                    function isNumber(evt) {
                        evt = (evt) ? evt : window.event;
                        var charCode = (evt.which) ? evt.which : evt.keyCode;
                        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                            return false;
                        }
                        return true;
                    }


                    function validateLength() {
                        document.getElementById("myspan").style.display = "none";
                        if (document.getElementById("campaignTitle").value != "" && document.getElementById("campaignTitle").value.length < 4) {
                            document.getElementById("myspan").style.display = "block";
                            return false;
                        }
                        else {
                            document.getElementById("myspan").style.display = "none";
                            return true;
                        }
                    }
//                    jQuery(document).ready(function($) {
//                        if ($.browser.msie) {
//                            //var text = this.attr('placeholder');
//                            $("#datetimepicker").mask("99/99/9999");
//                            $("#datetimepicker1").mask("99/99/9999");
//                        }
//                        else {
//                            $("#datetimepicker").mask("99/99/9999");
//                            $("#datetimepicker1").mask("99/99/9999");
//                        }
//                    });
                    function hideMessage() {
                        document.getElementById("myspan").style.display = "none";
                        document.getElementById("errorMessage").style.display = "none";
                    }

                    function startTimer() {
                        window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
                    }
                    function addSlashes(input) {
                        var v = input.value;
                        if (v.match(/^\d{2}$/) !== null) {
                            input.value = v + '/';
                        } else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
                            input.value = v + '/';
                        }
                    }
                    $('Input').bind("paste", function(e) {
                        e.preventDefault();
                    });

                </script>

            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
    </body>
</html>
