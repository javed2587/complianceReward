<%@page import="com.ssa.cms.model.Event"%>
<%@page import="com.ssa.cms.model.EventDetail"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body onload="startTimer()">
        <jsp:include page="./inc/header.jsp" />
        <script>
            function onSelectChange(obj, index) {
                document.getElementById("NOSELCION" + index).style.display = "none";
                document.getElementById("IRF" + index).style.display = "none";
                document.getElementById("DRF" + index).style.display = "none";
                if (obj == null) {
                    document.getElementById("NOSELCION" + index).style.display = "block";
                } else {
                    if (obj.value == "InstantRedemption") {
                        document.getElementById("IRF" + index).style.display = "block";

                    } else if (obj.value == "DailyRedemption") {
                        document.getElementById("DRF" + index).style.display = "block";
                    } else {
                        document.getElementById("NOSELCION" + index).style.display = "block";
                    }
                }
            }
        </script>
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <c:if test="${event.eventId!=null && event.eventId!=''}">
                        <c:set value="../add" var="action"/>
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Event Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Edit Event
                    </c:if>
                    <c:if test="${event.eventId==null || event.eventId==''}">
                        <c:set value="../add" var="action"/>
                        <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Event Setup <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Add Event
                    </c:if>
                </div>
                <div class="heading" >
                    <c:if test="${event.eventId!=null && event.eventId!=''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Edit Event</h1>
                    </c:if>
                    <c:if test="${event.eventId==null || event.eventId==''}">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Add Event</h1>
                    </c:if>
                </div> <!-- /header -->

                <form:form action="${pageContext.request.contextPath}/event/add" commandName="event">
                    <form:hidden path="eventId"/>
                    <input type="hidden" name="deletedIds" value="" id="deletedIds"/>
                    <input type="hidden" name="addeddetails" value="" id="addeddetails"/>
                    <input type="hidden" value="0" id="remove">
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <div class="errorMessage" id="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/event/list'"><a href="${pageContext.request.contextPath}/event/list" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop btn-margin" onclick="$('#event').submit();">
                                    <a class="btn_Color">Save</a></div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row grid padd-bottom-15-ipad" style="background:#f7f7f7;margin-bottom: 40px;">
                            <div class="search-grid">

                                <div class="form-group">
                                    <div class="col-lg-3 col-sm-3 col-md-3 padd-bottom-zero padd-left-zero" style="padding-bottom:15px;">
                                        <label>Event Title:<span style="color:red">*</span></label> 
                                        <form:input path="eventTitle" onkeypress="return IsAlphaNumeric(event);" cssClass="form-control" onchange="checkLength(this)"/>
                                        <span id="myspan" class="errorMessage1"><c:if test="${not empty lengthMsg}">${lengthMsg}</c:if></span>
                                        <form:errors path="eventTitle" cssClass="errorMessageValid" />
                                    </div>
                                </div>
                                    <div class="form-group"> 
                                    <div class="col-lg-2 col-sm-6 col-md-6 padd-bottom-zero padd-left-zero" style="padding-bottom:15px;">
                                        <label> Criteria:<span style="color:red">*</span></label><br>   
                                        <form:radiobutton path="eventCriteria" value="Static" onclick="checkCriteria();"/>&nbsp;Static&nbsp;&nbsp;
                                        <form:radiobutton path="eventCriteria" value="Dynamic"  onclick="checkCriteria();" onchange="hideValidate()"/>&nbsp;Dynamic
                                    </div>  
                                </div>
                                <div class="form-group" id="staticValueTextBox1"  style="display:${event.eventCriteria != 'Dynamic' ? 'block': 'none'}">  
                                    <div class="col-sm-12 col-md-12 col-lg-4 clr-768" style="padding-left:0px;" >  
                                        <label> Value:<span style="color:red">*</span></label>    
                                        <form:input path="staticValue" onkeypress="return IsAlphaNumeric(event);" id="staticValueTextBox" class="form-control" maxlength="50"/>
                                        <c:if test="${not empty message1}"><div class="errorMessageValid1">${message1}</div></c:if>
                                        </div>   
                                    </div>

                                    <div class="form-group" id="test" style="display:${event.eventCriteria == 'Dynamic' ? 'block': 'none'}">  
                                    <div class="col-sm-12 col-md-12 col-lg-3" style="padding-left:0px;">
                                        <label> Value:<span style="color:red">*</span></label>    
                                        <form:select path="dynamicValue" cssClass="form-control selectpicker show-tick" multiple="false">
                                            <form:option value="">Select One</form:option>
                                            <form:option value="Redemption">Redemption</form:option>
                                            <form:option value="Refill">Refill</form:option>
                                        </form:select>
                                        <c:if test="${not empty message2}"><div class="errorMessageValid1" id="errorMsg">${message2}</div></c:if>
                                        </div>   
                                    </div>
                                    <div id="dynamicCriteriaTableDiv" style="float:left;width:100%;padding-left:11px;display ${event.eventCriteria == 'Dynamic' ? 'block': 'none'};" >
                                    <c:set var="responselength" value="${fn:length(event.eventDetails)}" scope="page"/>
                                    <c:if test="${responselength gt 0}">
                                        <c:set var="responselength" value="${responselength - 1}" scope="page"/>
                                    </c:if>
                                    <c:forEach var="rowCounter" begin="0" step="1" end="${responselength}"> 
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 for-320" style="padding-left: 0px; margin-bottom: 10px;">
                                            <div id="dynamicEventDetail-${rowCounter}">
                                                <div class="form-group padd-left-zero">
                                                    <div class="col-lg-2 col-md-12 col-xs-12">
                                                        <c:if test="${rowCounter eq 0}"><label>Data Set:<span style="color:red">*</span></label></c:if>
                                                        <form:select onchange="onSelectChange(this, '${rowCounter}')" path="eventDetails[${rowCounter}].dataSet" cssClass="form-control">
                                                            <form:option value="">Select One</form:option>
                                                            <form:option value="InstantRedemption">IRF</form:option>
                                                            <form:option value="DailyRedemption">DRF</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="form-group padd-left-zero">
                                                    <div class="col-lg-2 col-md-12 col-xs-12">
                                                        <c:if test="${rowCounter eq 0}"><label>Field Selection:<span style="color:red">*</span></label></c:if>
                                                        <div id="NOSELCION${rowCounter}" style="display: none;">
                                                            <form:select path="eventDetails[${rowCounter}].tempValue" cssClass="form-control">
                                                                <form:option value="">Select One</form:option>
                                                            </form:select>
                                                        </div>

                                                        <div id="IRF${rowCounter}" style="display: none;">
                                                            <form:select path="eventDetails[${rowCounter}].fieldSelection" cssClass="form-control">
                                                                <form:option value="">Select One</form:option>
                                                                <form:options items="${irfList}"/>
                                                            </form:select>
                                                        </div>
                                                        <div id="DRF${rowCounter}" style="display: none;">
                                                            <form:select path="eventDetails[${rowCounter}].drfValue" cssClass="form-control">
                                                                <form:option value="">Select One</form:option>
                                                                <form:options items="${drfList}"/>
                                                            </form:select>
                                                        </div>
                                                        <script>onSelectChange(document.getElementById('eventDetails${rowCounter}.dataSet'), ${rowCounter})</script>
                                                    </div>
                                                </div>
                                                <div class="form-group padd-left-zero">
                                                    <div class="col-lg-2 col-md-12 col-xs-12">
                                                        <c:if test="${rowCounter eq 0}"><label>Operation:<span style="color:red">*</span></label></c:if>
                                                        <form:select path="eventDetails[${rowCounter}].operation" cssClass="form-control">
                                                            <form:option value="">Select One</form:option>
                                                            <form:option value="=">=</form:option>>
                                                            <form:option value="<=">&lt;=</form:option>
                                                            <form:option value=">=">&gt;=</form:option>
                                                            <form:option value="!=">!=</form:option>
                                                            <form:option value=">">&gt;</form:option>
                                                            <form:option value="<">&lt;</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="form-group padd-left-zero">
                                                    <div class="col-lg-2 col-md-12 col-xs-12">
                                                        <c:if test="${rowCounter eq 0}"><label>Specific Value:<span style="color:red">*</span></label></c:if>
                                                        <form:input path="eventDetails[${rowCounter}].specificValue" maxlength="10" onkeypress="IsAlphaNumeric(event);" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="form-group padd-left-zero">
                                                    <div class="col-lg-2 col-md-12 col-xs-12">
                                                        <c:if test="${rowCounter eq 0}"><label>Condition:<span style="color:red">*</span></label></c:if>
                                                        <form:select path="eventDetails[${rowCounter}].condition" cssClass="form-control3">
                                                            <form:option value="">Select One</form:option>
                                                            <form:option value="AND">AND</form:option>
                                                            <form:option value="OR">OR</form:option>
                                                            <form:option value="END">END</form:option>
                                                        </form:select>
                                                        <c:if test="${rowCounter eq 0}">
                                                            <a id="addMore" href="#" class="fa fa-plus-circle" style="float: right; margin-top: -22px;" onclick="addNewDetail('dynamicEventDetail-' +${rowCounter});"></a>
                                                        </c:if>
                                                        <c:if test="${rowCounter ne 0}">
                                                            <a id="deleteButton" class="fa fa-minus-circle" href="#" style="float: right; margin-top: -21px;" onclick="deleteRow('dynamicEventDetail-' +${rowCounter})"></a>
                                                        </c:if>  
                                                    </div>
                                                </div>


                                            </div>
                                        </div>
                                    </c:forEach> 

                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->

        <script type="text/javascript">
            function addNewDetail(indx)
            {
                var i = $("#remove").val();
                var select = document.getElementsByTagName('select');
                var index = ${fn:length(event.eventDetails)-1};
                if (index == 0 && i == 0) {
                    index = 1;
                } else {
                    index = parseInt(i) + 1;
                }
                var isIE = /*@cc_on!@*/false || !!document.documentMode; // At least IE6
                for (var i = 0; i <= index; i++) {
                    if ($("#eventDetails" + i + "\\.dataSet").val() == '' || $("#eventDetails" + i + "\\.operation").val() == ''
                            || $("#eventDetails" + i + "\\.specificValue").val() == '' || $("#eventDetails" + i + "\\.condition").val() == '') {
                        document.getElementById("errorMessage").style.display = "block";
                        document.getElementById("errorMessage").innerHTML = "All dynamic field(s) required";
                        return;
                    }
                }
                var innerHtml = "";
                if (isIE) {

                }
                innerHtml += '<div id="dynamicEventDetail-' + index + '" class="col-lg-12" style="padding-left:0px;margin-top:15px;width:101%;float:left;"> <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12">';
                innerHtml += '<select onchange="onSelectChange(this,' + index + ')" id="eventDetails' + index + '.dataSet" name="eventDetails[' + index + '].dataSet" class="form-control selectpicker show-tick">';
                innerHtml += select[2].innerHTML;
                innerHtml += '</select>';
                innerHtml += '</div>';
                innerHtml += ' <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12 eventfs">';
                innerHtml += '<div id="NOSELCION' + index + '"><select  id="eventDetails' + index + '.fieldSelection" name="eventDetails[' + index + '].fieldSelection" class="form-control selectpicker show-tick fieldSelection">';
                innerHtml += select[3].innerHTML;
                innerHtml += '</select></div>';
                innerHtml += '<div id="IRF' + index + '" style="display: none;"><select id="eventDetails' + index + '.fieldSelection" name="eventDetails[' + index + '].fieldSelection" class="form-control selectpicker show-tick" style="margin-left:1px;">';
                innerHtml += select[4].innerHTML;
                innerHtml += '</select></div>';
                innerHtml += '<div id="DRF' + index + '" style="display: none;"><select id="eventDetails' + index + '.drfValue" name="eventDetails[' + index + '].drfValue" class="form-control selectpicker show-tick" style="margin-left:1px;">';
                innerHtml += select[5].innerHTML;
                innerHtml += '</select></div>';
                innerHtml += '</div>';
                innerHtml += ' <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12 eventop">';
                innerHtml += '<select id="eventDetails' + index + '.operation" name="eventDetails[' + index + '].operation" class="form-control selectpicker show-tick operation" style="">';
                innerHtml += select[6].innerHTML;
                innerHtml += '</select>';
                innerHtml += '</div>';
                innerHtml += ' <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12 eventsv">';
                innerHtml += '<input id="eventDetails' + index + '.specificValue" name="eventDetails[' + index + '].specificValue" class="form-control specificValue" maxlength="10" onkeypress="return IsAlphaNumeric(event);" style=""/>';
                innerHtml += '</div>';
                innerHtml += ' <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12 eventcd">';
                innerHtml += '<select id="eventDetails' + index + '.condition" name="eventDetails[' + index + '].condition" class="form-control3 selectpicker show-tick econdition eventcddrop" style="float:left;">';
                innerHtml += select[7].innerHTML;
                innerHtml += '</select>';
                innerHtml += '<a id="deleteButton" class="fa fa-minus-circle" href="#" style="float:right;margin-right:-4px;margin-top:7px;" onclick=deleteRow("dynamicEventDetail-' + index + '")></a>';
                innerHtml += '</div></div>';


                $("#" + indx).append(innerHtml);
                $("#remove").val(index);
                var ds = 'eventDetails' + index + '.dataSet';
                var op = 'eventDetails' + index + '.operation';
                var cond = 'eventDetails' + index + '.condition';
                var irfValue = 'eventDetails' + index + '.fieldSelection';
                var drfValue = 'eventDetails' + index + '.drfValue';

                document.getElementById(ds).selectedIndex = "0";
                document.getElementById(op).selectedIndex = "0";
                document.getElementById(cond).selectedIndex = "0";
                document.getElementById(irfValue).selectedIndex = "0";
                document.getElementById(drfValue).selectedIndex = "0";
            }

            function deleteRow(id) {
                $("#" + id).remove();
            }
            function checkCriteria() {
                if (document.getElementById("eventCriteria1").checked) {
                    document.getElementById("staticValueTextBox1").style.display = 'block';
                    document.getElementById("test").style.display = 'none';
                    document.getElementById("dynamicCriteriaTableDiv").style.display = "none";
                } else if (document.getElementById("eventCriteria2").checked) {
                    document.getElementById("staticValueTextBox1").style.display = 'none';
                    document.getElementById("test").style.display = 'block';
                    document.getElementById("dynamicCriteriaTableDiv").style.display = "block";
                }
            }
            checkCriteria();
            function hideValidate() {
                document.getElementById("errorMsg").style.display = 'none';
            }

            function checkLength(inputtxt) {
                if (inputtxt.value.trim() != "" && inputtxt.value.length < 2) {
                    document.getElementById("myspan").style.display = "block";
                    document.getElementById("myspan").innerHTML = "Minimum length 2 character";
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
                //e.preventDefault();
            });
        </script>
    </body>
</html>
