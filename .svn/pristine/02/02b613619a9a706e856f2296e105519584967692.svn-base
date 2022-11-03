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

                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Campaigns <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage IVR
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage IVR</h1>
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
                                <div class="btndrop" onclick="location.reload();"><a href="#" onclick="location.reload();" class="plusCol">Cancel</a></div>
                            <div class="btn-group">
                                <div class="btndrop" onclick="$('#campaigns').submit();"><a class="plusCol">Save</a></div>
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

                                        </dt>  

                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 background" style="display: block;border-left: 1px solid #2071b6; border-right: 1px solid #2071b6;border-bottom: 1px solid #2071b6;margin-bottom: 20px;margin-top: -4px;padding-right: 8px;" id="panel${loop.index}">         

                                            <div style="padding:14px; padding-right: 0px;width:100%;padding-left: 0px;position:relative;left:-10px;" class="pull-left">  
                                                <div class="form-group">
                                                    <div class="col-lg-3 col-md-13 col-sm-12 col-xs-12 padd-left-zero-ipad" style="padding-left:0px; padding-right:0px;">         
                                                        <label class="btn_Color"> Event to initiate Communication Flow </label>     

                                                        <form:select multiple="true" path="folderHasCampaignses[${count}].selectedEvents" class="form-control selectpicker show-tick width-101" style="height:auto;">  

                                                            <form:options items="${eventList}" itemValue="eventId" itemLabel="eventTitle" />  
                                                        </form:select>  
                                                    </div> 
                                                </div>  

                                                <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7 hidden-sm hidden-md">&nbsp;</div>     
                                            </div>   

                                            <div class="full-width overflow-auto pull-left">  
                                                <form:hidden path="folderHasCampaignses[${count}].folder.folderName" value="${folder.folderName}" />
                                                <c:forEach var="message" items="${folder.messageTypes}" varStatus="childLoop">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin: 0px;padding:0px;">    
                                                        <form:hidden path="campaignMessageses[${childcounter}].messageType.folder.folderId" value="${folder.folderId}" />

                                                        <form:hidden path="campaignMessageses[${childcounter}].messageType.messageTypeTitle" value="${message.messageTypeTitle}" />

                                                        <div class="form-group padd-bottom-zero-ipad" style="padding:10px 14px 10px 14px; background: white;border:1px solid #2071b6;float: left;width:100%;border-radius:2px;">  
                                                            <div class="form-group btn_Color" style="padding-left: 0px;margin-bottom:10px;">
                                                                <form:checkbox path="campaignMessageses[${childcounter}].messageTypeId"  value="${message.id.messageTypeId}" /> ${message.messageTypeTitle} 
                                                                <img id="msg_move_down${childcounter}" src="${pageContext.request.contextPath}/resources/images/msg_move_down.jpg" style="padding-left:10px" onclick="location.href = '${pageContext.request.contextPath}/campaign/moveDown/${message.id.messageTypeId}/${communicationPath}/${campaigns.campaignId}'" title="Move Down"> <img id="msg_move_up${childcounter}" src="${pageContext.request.contextPath}/resources/images/msg_move_up.jpg" style="padding-left: 5px;" onclick="location.href = '${pageContext.request.contextPath}/campaign/moveUp/${message.id.messageTypeId}/${communicationPath}/${campaigns.campaignId}'" title="Move Up">
                                                            </div>  

                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="padding-left: 0px;">  
                                                                <label> Servo Id</label>
                                                                <form:input path="campaignMessageses[${childcounter}].outboundIvrtext" cssClass="form-control cust" placeholder="Enter servo Id" maxlength="50"/>
                                                            </div>



                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" id = "response${childcounter}" style="padding-left:0px;">  
                                                                <c:set var="responsecounter" value="0" scope="page"/>
                                                                <c:set var="responselength" value="${fn:length(campaigns.campaignMessageses[childcounter].campaignMessagesResponses)}" scope="page"/>
                                                                <c:if test="${responselength gt 0}">
                                                                    <c:set var="responselength" value="${responselength - 1}" scope="page"/>
                                                                </c:if>
                                                                <c:forEach begin="0" end="${responselength}" varStatus="loopdd">
                                                                    <div id="${childcounter}${responsecounter}"> 
                                                                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="padding-left:0px;">
                                                                            <c:if test="${loopdd.index eq 0}"><label> Response</label> </c:if>

                                                                            <form:select path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].response.responseId" class="form-control">
                                                                                <form:option value="0">Please select</form:option>
                                                                                <form:options items="${responseList}" itemValue="responseId" itemLabel="responseTitle" />
                                                                            </form:select>
                                                                        </div>

                                                                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">      
                                                                            <c:if test="${loopdd.index eq 0}"><label> Interval </label></c:if>

                                                                            <form:select path="campaignMessageses[${childcounter}].campaignMessagesResponses[${responsecounter}].intervals.intervalId" class="form-control">
                                                                                <form:option value="0">Please select</form:option>
                                                                                <c:forEach var="interval" items="${intervalList}">
                                                                                    <form:option value="${interval.intervalId}"><c:out value="${interval.intervalValue} ${interval.intervalsType.intervalsTypeTitle}"/></form:option>
                                                                                </c:forEach>
                                                                            </form:select>
                                                                        </div>
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
                    function addFolderValue(index) {
                        $("#showFolder").val(index);
                    }
                    function hideMessage() {
                        document.getElementById("errorMessage").style.display = "none";
                    }

                    function startTimer() {
                        window.setInterval("hideMessage()", 5000);  // 5000 milliseconds = 5 seconds
                    }
                </script>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
    </body>
</html>
