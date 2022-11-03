<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <script>
            /**
             * DHTML textbox character counter script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
             */

            maxL = 160;
            var bName = navigator.appName;
            function taLimit(taObj) {

                if (taObj.value.length == maxL)
                    return;

                return true;
            }

            function checkIt(evt) {
                evt = (evt) ? evt : window.event
                var charCode = (evt.which) ? evt.which : evt.keyCode
                if (charCode != 46 && charCode != 45 && charCode > 31
                        && (charCode < 48 || charCode > 57))
                    return false;
                return true
            }


            function submitForm(msgObj, emailObj, id_, subObject, textid) {

                var emailId = emailObj.value;
                var cell;
                var email = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
                if (emailId === '') {
                    $('#' + id_).show();
                    $('#' + id_).html('<span style="color:#E70000;">Required</span>');
                    $('#' + id_).delay(3000).fadeOut('slow');
                    return false;
                }
                if (!email.test(emailId))
                {
                    $('#' + id_).show();
                    $('#' + id_).html('<span style="color:#E70000;">Enter valid email.</span>');
                    $('#' + id_).delay(3000).fadeOut('slow');
                    return false;
                }
                var subj = subObject.value;
                if (subj === '') {
                    $('#errorMessage').show();
                    $('#errorMessage').html('<span style="color:#E70000;">Subject required</span>');
                    $('#errorMessage').delay(3000).fadeOut('slow');
                    return false;
                }
                var msg = msgObj.value;
                if (msg.trim() == "" || msg.trim().length < 2) {
                    $('#errorMessage').show();
                    $('#errorMessage').html('<span style="color:#E70000;">Email test required</span>');
                    $('#errorMessage').delay(3000).fadeOut('slow');
                    return false;
                }
                appendValue('emailbody_', textid);
                msg = $('#emailbody_').val();
                if (msg.trim() == "" || msg.trim().length < 2) {
                    $('#errorMessage').show();
                    $('#errorMessage').html('<span style="color:#E70000;">Email test required</span>');
                    $('#errorMessage').delay(3000).fadeOut('slow');
                    return false;
                }
                $('#email_').val(emailId);
                //$('#emailbody_').val(msg);
                $('#subject_').val(subj);
                madeAjaxCall(id_);

                return true;
            }

            function madeAjaxCall(id_) {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/campaign/sendEmail",
                    cache: false,
                    data: 'email_=' + $("#email_").val() + "&subject_=" + escape($("#subject_").val()) + "&emailbody_=" + escape($("#emailbody_").val()) + "&campaignId=" + $("#campaignId").val(),
                    success: function(response) {
                        if (response == "Enter valid email.") {
                            $('#' + id_).show();
                            $('#' + id_).html('<span style="color:#E70000;">' + response + '</span>');
                            $('#' + id_).delay(3000).fadeOut('slow');
                        } else if (response == "Email sending failed.") {
                            $('#' + id_).show();
                            $('#' + id_).html('<span style="color:#E70000;">' + response + '</span>');
                            $('#' + id_).delay(3000).fadeOut('slow');
                        } else {
                            $('#' + id_).show();
                            $('#' + id_).html(response);
                            $('#' + id_).delay(3000).fadeOut('slow');
                        }
                    },
                    error: function() {
                        alert('Error while request..');
                    }
                });
            }
            var count = 1;
            function showGraph(id) {

                var counter = count++;

                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/campaign/graph/",
                    cache: false,
                    data: "campaignId=" + $("#campaignId").val() + "&folderId=" + id + "&communicationPath=" + $("#communicationPath").val() + "&imgId=" + counter,
                    success: function(response) {
                        $('.lb-image').attr("src", "${pageContext.request.contextPath}/resources/images/graphImages/graphimage" + counter + ".png");
                    },
                    error: function() {

                        $('.lb-image').attr("src", "${pageContext.request.contextPath}/resources/images/graphImages/graphimage" + counter + ".png");


                    }
                });
            }

//            function taCount(taObj, Cnt) {
//                objCnt = createObject(Cnt);
//                objVal = taObj.value;
//                if (objVal.length > maxL)
//                    objVal = objVal.substring(0, maxL);
//                if (objCnt) {
//                    if (bName == "Netscape") {
//                        objCnt.textContent = maxL - objVal.length;
//                    }
//                    else {
//                        objCnt.innerText = maxL - objVal.length;
//                    }
//                }
//                return true;
//            }
            function createObject(objId) {
                if (document.getElementById)
                    return document.getElementById(objId);
                else if (document.layers)
                    return eval("document." + objId);
                else if (document.all)
                    return eval("document.all." + objId);
                else
                    return eval("document." + objId);
            }

        </script>
        <jsp:include page="./inc/header.jsp" />

        <form id="sendEmail" action="${pageContext.request.contextPath}/campaign/sendEmail" method="post">
            <input type="hidden" name="emailbody_" id="emailbody_"/>
            <input type="hidden" name="email_" id="email_" />
            <input type="hidden" name="subject_" id="subject_" />
            <input type="hidden" name="campaignId" id="campaignId" value="${campaigns.campaignId}" />
        </form>
        <div id="wrapper">
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/newMenu.jsp" />

                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Campaigns <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Email
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Email</h1>
                </div> <!-- /header -->
                <form:form action="${pageContext.request.contextPath}/campaign/${communicationPath}" commandName="campaigns" method="post" role="form">    
                    <form:hidden path="campaignId" />
                    <form:hidden path="campaignType" />
                    <div class="page-message" style="padding-left: 15px; padding-top: 15px; float: left;">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                            <br clear="all">
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px; padding-right: 15px;">
                            <div style="float: right;">
                                <div class="btndrop" onclick="location.reload();"><a href="#" onclick="location.reload();" class="btn_Color">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop" onclick="$('#campaigns').submit();"><a class="btn_Color">Save</a></div>
                            </div>
                        </div>

                        <br><br>  
                        <div class="row grid" style="height: auto;border: 0px;margin-bottom:33px;">
                            <div class="search-grid demo-show2 accordionnn">     
                                <form:hidden path="showFolder"/>
                                <c:set var="count" value="0" scope="page" />
                                <c:set var="childcounter" value="0" scope="page"/>
                                <c:forEach var="folder" items="${folderList}" varStatus="loop">
                                    <c:if test="${not empty folder.messageTypes}" >
                                        <dt>       
                                            <input type="hidden" name="folderId" id="folderId" value="${folder.folderId}"/>
                                            <input type="hidden" name="communicationPath" id="communicationPath" value="${communicationPath}"/> 
                                            <form:checkbox path="folderHasCampaignses[${count}].folder.folderId" value="${folder.folderId}" /> ${folder.folderName}
                                            <i style="font-size: 20px;float:right;cursor: pointer;padding-left: 12px;padding-right: 15px;" class="fa fa-arrow-circle-down" id="acc${loop}" onclick="addFolderValue('${loop.index}')"></i> 

                                            <span class="glyphicon glyphicon-signal" style="float:right; color: #2071b6;"><a href="" onclick="showGraph(${folder.folderId});"  data-lightbox="graph" class="plusCol">&nbsp;Graph</a></span>   

                                        </dt>  

                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background" style="display: block;border-left: 1px solid #c6c6c6; border-right: 1px solid #c6c6c6;border-bottom: 1px solid #c6c6c6;margin-bottom: 20px;margin-top: -4px;padding-right: 8px;" id="panel${loop.index}">         

                                            <div style="padding:14px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left padd-right-zero-ipad">  
                                                <div class="form-group">
                                                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12  " style="padding-left:0px; padding-right:0px;">         
                                                        <label> Event to initiate Communication Flow </label>     

                                                        <form:select multiple="true" path="folderHasCampaignses[${count}].selectedEvents" class="form-control selectpicker show-tick width-101" style="height:auto;">  

                                                            <form:options items="${eventList}" itemValue="eventId" itemLabel="eventTitle" />  
                                                        </form:select>  
                                                    </div> 
                                                </div>  

                                                <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7 hidden-sm hidden-xs hidden-md">&nbsp;</div>     
                                            </div>   

                                            <div class="full-width overflow-auto pull-left">  
                                                <form:hidden path="folderHasCampaignses[${count}].folder.folderName" value="${folder.folderName}" />
                                                <c:forEach var="message" items="${folder.messageTypes}" varStatus="childLoop">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                                        <form:hidden path="campaignMessageses[${childcounter}].messageType.folder.folderId" value="${folder.folderId}" />

                                                        <form:hidden path="campaignMessageses[${childcounter}].messageType.messageTypeTitle" value="${message.messageTypeTitle}" />

                                                        <div class="form-group" style="padding:10px 7px 10px 7px; background: white;border:1px solid #c6c6c6;float: left;width:100%;border-radius:2px;">  
                                                            <div class="form-group btn_Color" style="padding-left: 0px;margin-bottom:10px;">
                                                                <form:checkbox path="campaignMessageses[${childcounter}].messageTypeId"  value="${message.id.messageTypeId}" /> ${message.messageTypeTitle} 
                                                                <img id="msg_move_down${childcounter}" src="${pageContext.request.contextPath}/resources/images/msg_move_down.jpg" style="padding-left:10px" onclick="location.href = '${pageContext.request.contextPath}/campaign/moveDown/${message.id.messageTypeId}/${communicationPath}/${campaigns.campaignId}'" title="Move Down"> <img id="msg_move_up${childcounter}" src="${pageContext.request.contextPath}/resources/images/msg_move_up.jpg" style="padding-left: 5px;" onclick="location.href = '${pageContext.request.contextPath}/campaign/moveUp/${message.id.messageTypeId}/${communicationPath}/${campaigns.campaignId}'" title="Move Up">
                                                            </div>  
                                                            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-12" style="padding-left: 0px;padding-right: 0px;">  
                                                                <form:input path="campaignMessageses[${childcounter}].emailSubject" cssClass="form-control" placeholder="Subject"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12 pull-left" style="padding-left:27px;padding-right: 0px;">    

<!--                                                                    <div class="pull-left hidden-sm hidden-xs " style="padding:10px 5px 10px 5px;"><form:checkbox path="campaignMessageses[${childcounter}].isLastEmail" value="Yes"/> </div>-->
                                                                    <div class="pull-right col-xs-12 test-buttn margin-right-zero margin-top-eipad mailSetup" style="margin-right: -10px">       
                                                                        <input type="text" class="form-control right-zero" name="email" id="email${childcounter}" style="width:80%;float:left;position: relative;right:-9px;" placeholder="Enter Your Email"/>
                                                                        <button type="button" class="btndrop pull-right position-absolute" style="margin-top:1px; background-color: #145994; color: #FFFFFF;" onclick="submitForm(document.getElementById('textid' +${childcounter}), document.getElementById('email' +${childcounter}), 'email${childcounter}_', document.getElementById('campaignMessageses' +${childcounter} + '.emailSubject'), 'textid${childcounter}')">Test</button> 
                                                                        <div class="message" id="email${childcounter}_"  style="text-align: left;font-family: arial,sans-serif;font-size:12px;float:left;width:100%;padding-left:9px;"></div>
                                                                    </div>  
                                                                </div> 
                                                            </div> 
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 margin-top-20" style="padding-left: 0px;padding-right: 0px; margin-top: 10px;">  
                                                                <form:textarea id="textid${childcounter}" path="campaignMessageses[${childcounter}].emailBody" class="form-control cust textarea summernote" cssStyle="height:70px;" onchange="copyValueInEmailBody('textid${childcounter}')" />

                                                            </div>



                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 for-320" id = "response${childcounter}" style="padding-left:0px;">  
                                                                <c:set var="responsecounter" value="0" scope="page"/>
                                                                <c:set var="responselength" value="${fn:length(campaigns.campaignMessageses[childcounter].campaignMessagesResponses)}" scope="page"/>
                                                                <c:if test="${responselength gt 0}">
                                                                    <c:set var="responselength" value="${responselength - 1}" scope="page"/>
                                                                </c:if>
                                                                <c:forEach begin="0" end="${responselength}" varStatus="loopdd">
                                                                    <div id="${childcounter}${responsecounter}" class="padd-left-10 sms-input-width-ipad">
                                                                        <div class=" fgPL-10 form-group padd-left-10">    
                                                                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width padd-right-zero" style="padding-left:0px;">
                                                                                <c:if test="${loopdd.index eq 0}"><label> Response</label> </c:if>

                                                                                <form:select path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].response.responseId" class="form-control">
                                                                                    <form:option value="0">Please select</form:option>
                                                                                    <form:options items="${responseList}" itemValue="responseId" itemLabel="responseTitle" />
                                                                                </form:select>
                                                                            </div>
                                                                        </div>
                                                                        <div class="fgPL-10 form-group padd-left-10">   
                                                                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width padd-right-zero">      
                                                                                <c:if test="${loopdd.index eq 0}"><label> Interval </label></c:if>

                                                                                <form:select path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].intervals.intervalId" class="form-control">
                                                                                    <form:option value="0">Please select</form:option>
                                                                                    <c:forEach var="interval" items="${intervalList}">
                                                                                        <form:option value="${interval.intervalId}"><c:out value="${interval.intervalValue} ${interval.intervalsType.intervalsTypeTitle}"/></form:option>
                                                                                    </c:forEach>
                                                                                </form:select>
                                                                            </div>
                                                                        </div>
                                                                                <div class="fgPL-10 form-group padd-left-10" style="padding-left:10px;">
                                                                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width padd-right-zero">
                                                                                <c:if test="${loopdd.index eq 0}"><label>Next Email </label></c:if>

                                                                                <form:select path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].nextMessage" class="form-control">
                                                                                    <form:option value="0">Please select</form:option>
                                                                                    <form:options items="${folder.messageTypes}" itemValue="id.messageTypeId" itemLabel="messageTypeTitle" />
                                                                                </form:select>
                                                                            </div>
                                                                        </div>
                                                                                <div class="fgPL-10 form-group padd-left-10" style="padding-left:10px;">
                                                                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width padd-right-zero" >
                                                                                <c:if test="${loopdd.index eq 0}"><label>Repeat Interval</label></c:if>

                                                                                <form:select path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].repeatIntervalId" class="form-control">
                                                                                    <form:option value="0">Please select</form:option>
                                                                                    <c:forEach var="interval" items="${intervalList}">
                                                                                        <form:option value="${interval.intervalId}"><c:out value="${interval.intervalValue} ${interval.intervalsType.intervalsTypeTitle}"/></form:option>
                                                                                    </c:forEach>
                                                                                </form:select>
                                                                            </div>
                                                                        </div>
                                                                        <div class="fgPL-10 form-group padd-left-10" style="padding-left:10px;">
                                                                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width padd-right-zero">     
                                                                                <c:if test="${loopdd.index eq 0}"><label> Repeat Email</label></c:if>



                                                                                <form:select path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].repeatMessageId" class="form-control">
                                                                                    <form:option value="0">Please select</form:option>
                                                                                    <form:options items="${folder.messageTypes}" itemValue="id.messageTypeId" itemLabel="messageTypeTitle" />
                                                                                </form:select>
                                                                            </div>
                                                                        </div>

                                                                        <div class="paired smschkmargin" style="padding-right:3px;padding-left:2px;font-size:12px;float:left;margin-top:4px;"><c:if test="${loopdd.index eq 0}"> <label>Paired</label><br></c:if> <form:checkbox path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].paired" value="Yes" /> </div>

                                                                        <c:if test="${responsecounter ne 0}">
<!--                                                                            <button style="margin-top:4px; position: relative; right: -28px;" type="button" onclick="removeResponse('${childcounter}${responsecounter}', '${childcounter}')"><i class="fa fa-minus-circle"></i></button>-->
                                                                            <a href="#" class="fa fa-minus-circle" style="margin-top:4px; position: relative; right: -28px;" onclick="removeResponse('${childcounter}${responsecounter}', '${childcounter}')"></a>
                                                                            </c:if>
                                                                            <c:if test="${responsecounter eq 0}">
<!--                                                                            <button style="margin-top:23px;position:relative;right:-4px;" type="button" onclick="addResponse('response${childcounter}', '${childcounter}')"><i class="fa fa-plus-circle"></i></button>-->
                                                                            <a href="#" class="fa fa-plus-circle" style="margin-top:27px;position:relative;right:-4px;" onclick="addResponse('response${childcounter}', '${childcounter}')"></a>
                                                                            </c:if>
                                                                        <br clear="all" />
                                                                    </div>
                                                                    <c:set var="responsecounter" value="${responsecounter + 1}" scope="page"/>
                                                                </c:forEach>
                                                                <script>
                                                                    var index${childcounter} = ${responsecounter};</script>  

                                                            </div>   


                                                        </div>

                                                    </div> 

                                                    <c:set var="childcounter" value="${childcounter + 1}" scope="page"/>
                                                </c:forEach>
                                            </div>
                                            <!--<div class="full-width pull-right" style="text-align: right;padding:18px 16px 0px 0px;border-top:1px solid #8b8b8b; ">
                                                  <button type="submit" class="btn btn-green">Save</button>&nbsp;&nbsp;  <button class="btn btn-black">Cancel</button>
                                            </div> -->    
                                        </div>

                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:if>
                                </c:forEach>
                            </div>


                        </div></div>
                    </form:form> 
                <script type="text/javascript">

//                    $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false, format: 'd/m/Y'});
                    //adding trigger dynamically
                    $(function() {
                        var index = ${fn:length(campaigns.triggers)};
                        if (index == 0) {
                            index = 1;
                        }
                        $("#addtrigger").off("click").on("click", function() {
                            $(this).before(function() {
                                var html = '<div id="triggers' + index + '" class="">';
                                html += '<input maxlength="10" class="texbox" style="margin-top:4px;" type="text" id="triggers' + index + '.id.keyword" name="triggers[' + index + '].id.keyword" />';
                                html += '&nbsp;<a href="#" class="removetrigger" data-index="' + index + '"><i class="fa fa-minus-square"></i></a>';
                                html += "</div>";
                                return html;
                            });
                            $("#triggers" + index).show();
                            index++;
                            return false;
                        });
                        $('body').on('click', '.removetrigger', function() {
                            var index2remove = $(this).data("index");
                            $("#triggers" + index2remove).hide();
                            $("#triggers" + index2remove + "\\.remove").val("1");
                            return false;
                        });
                    });

                    function addResponse(id, parent) {
                        var name = 'index' + parent;
                        var child = eval(name);
                        var divid = id + parent;
                        var select = document.getElementsByTagName('select');
                        for (var j = 0; j <= child; j++) {
                            if ($("#campaignMessageses" + parent + "\\.campaignMessagesResponses" + j + "\\.intervals\\.intervalId").val() == 0) {
                                document.getElementById("errorMessage").style.display = "block";
                                document.getElementById("errorMessage").innerHTML = "Message Response value required.";
                                return;
                            }
                        }
                        var innerHtml = '<div id="' + divid + '" style="width:100%;" class="sms-input-width-ipad">';
                        innerHtml += '<div class="fgPL-10 col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width  padd-right-zero" style="padding-left:0px;">';
                                                innerHtml += '<label class="hidden-lg hidden-md hidden-sm"> Response</label> ';
                        innerHtml += '<select name="campaignMessageses[' + parent + '].campaignMessagesResponses[' + child + '].response.responseId" id="campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.response.responseId" class="form-control">';
                        innerHtml += select[1].innerHTML;
                        innerHtml += '</select>';
                        innerHtml += '</div>';
                        innerHtml += '<div class="fgPL-10 col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width  padd-right-zero">   ';
                                                innerHtml += '<label class="hidden-lg hidden-md hidden-sm"> Interval </label>';  
                        innerHtml += '<select name="campaignMessageses[' + parent + '].campaignMessagesResponses[' + child + '].intervals.intervalId" id="campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.intervals.intervalId" class="form-control">';
                        innerHtml += select[2].innerHTML;
                        innerHtml += '</select>';
                        innerHtml += '</div>';
                        innerHtml += '<div class="fgPL-10 col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width  padd-right-zero">';
                                                innerHtml += '<label class="hidden-lg hidden-md hidden-sm">Next SMS </label>';
                        innerHtml += '<select name="campaignMessageses[' + parent + '].campaignMessagesResponses[' + child + '].nextMessage" id="campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.nextMessage" class="form-control">';
                        innerHtml += select[3].innerHTML;
                        innerHtml += '</select>';
                        innerHtml += '</div>';
                        innerHtml += '<div class="fgPL-10 col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width  padd-right-zero">';
                                                innerHtml += '<label class="hidden-lg hidden-md hidden-sm">Repeat Interval</label>';
                        innerHtml += '<select name="campaignMessageses[' + parent + '].campaignMessagesResponses[' + child + '].repeatIntervalId" id="campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.repeatIntervalId" class="form-control">';
                        innerHtml += select[4].innerHTML;
                        innerHtml += '</select>';
                        innerHtml += '</div>';
                        innerHtml += '<div class="fgPL-10 col-lg-2 col-md-2 col-sm-2 col-xs-12 max-width  padd-right-zero"> ';
                                                innerHtml += '<label class="hidden-lg hidden-md hidden-sm"> Repeat SMS</label>';
                        innerHtml += '<select name="campaignMessageses[' + parent + '].campaignMessagesResponses[' + child + '].repeatMessageId" id="campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.repeatMessageId" class="form-control">';
                        innerHtml += select[5].innerHTML;
                        innerHtml += '</select>';
                        innerHtml += '</div> ';

                        innerHtml += '   <div class="paired smschkmargin" style="padding-right:27px;padding-left:2px;font-size:12px;float:left;margin-top:4px;"><input type="checkbox" path="campaignMessageses[' + parent + '].campaignMessagesResponses[' + child + '].paired" value="campaignMessageses[' + child + '].campaignMessagesResponses[${responsecounter}].paired" /></div>'

                        innerHtml += '<a class="fa fa-minus-circle" href="#" style="margin-top:8px;position:relative;right:-4px;" onclick="removeResponse(\'' + divid + '\',' + parent + ')"></a><br clear="all" /></div>';
                        $("#" + id).append(innerHtml);

                        var respId = 'campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.response.responseId';
                        var intervalId = 'campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.intervals.intervalId';
                        var nextmsg = 'campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.nextMessage';
                        var rptinterval = 'campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.repeatIntervalId';
                        var rptmsg = 'campaignMessageses' + parent + '.campaignMessagesResponses' + child + '.repeatMessageId';
                        document.getElementById(respId).selectedIndex = "0";
                        document.getElementById(intervalId).selectedIndex = "0";
                        document.getElementById(nextmsg).selectedIndex = "0";
                        document.getElementById(rptinterval).selectedIndex = "0";
                        document.getElementById(rptmsg).selectedIndex = "0";

                        this[name]++;
                    }

                    function removeResponse(id, parent) {
                        var name = 'index' + parent;
                        $("#" + id).remove();
                        this[name]--;
                    }

                    $(document).on('input keyup', 'textarea[maxlength]', function(e) {
                        // maxlength attribute does not in IE prior to IE10
                        var $this = $(this);
                        var maxlength = $this.attr('maxlength');
                        if (!!maxlength) {
                            var text = $this.val();

                            if (text.length > maxlength) {
                                // truncate excess text (in the case of a paste)
                                $this.val(text.substring(0, maxlength));
                                e.preventDefault();
                            }

                        }

                    });
                    function addPhoneMask(id) {
                        var ind = 'index' + id;
                        $("#" + id).mask("999-999-9999");
                        ind++;
                    }
                    function addFolderValue(index) {
                        $("#showFolder").val(index);
                    }
                    function hideMessage() {
                        document.getElementById("errorMessage").style.display = "none";
                    }

                    function startTimer() {
                        window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
                    }
//                                                                    $('.textarea').wysihtml5({
//                                                                    });

                    $(window).load(function(){
                        $('.summernote').summernote();
                    });

                    function copyValueInEmailBody(id) {
                        $('#' + id).each(function() {
                            var $textArea = $(this);

                            $textArea.summernote({
                                onkeyup: function(e) {
                                    $textArea.val($(this).code());
                                    $textArea.change(); //To update any action binded on the control
                                }
                            });
                        });
                    }
                    function appendValue(id, textid) {
                        $('#' + textid).each(function() {
                            $('#' + id).val($(this).code());
                        });
                    }
                </script>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
    </body>
</html>

