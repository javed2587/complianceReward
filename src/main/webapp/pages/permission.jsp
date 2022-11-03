<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> 
                    Manage Role Permissions
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Role Permissions</h1>
                </div> <!-- /header -->

                <form:form action="load" method="post" commandName="objectPermissions" prependId="false">
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
                        <div id="errorMessage" class="errorMessage"><c:if test="${not empty errorMessage}">${errorMessage}</c:if></div>
                            <br clear="all">
                        </div>
                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;margin-top:0px">
                            <div style="float: right;">
                            <c:if test="${not empty(objectPermissions.resources)}" >
                                <div style="float: left;">
                                    <div class="btndrop  btn-margin"><a href="${pageContext.request.contextPath}/permission/load" class="btn_Color">Cancel</a></div>
                                    <c:if test="${sessionBean.pmap[(4).intValue()].manage eq true}">
                                        <div class="btndrop  btn-margin"><a class="btn_Color" onclick="if (validate()) {
                                                    $('#objectPermissions').attr('action', 'save');
                                                    $('#objectPermissions').submit();
                                                }
                                                                            ">Apply Permission</a></div>
                                        </c:if>
                                </div>
                            </c:if>
                        </div>
                        <br><br>
                        <div class="row grid" style="height: auto;padding: 7px;border-bottom: 0px !important;background:#f7f7f7;">
                            <div class="search-grid">  
                                <div id="searchCmp" style="display:inline">
                                    <div class="col-lg-12 col-md-12 col-sm-12 padd-left-zero">     
                                        <div class="col-lg-2 col-sm-2"></div>  <div class="col-lg-1 col-sm-1 text-left" style="text-align: right;padding-right: 0px;"><label style="padding-top: 5px;">Roles:</label></div>  
                                        <div class="col-lg-3 col-sm-5 padd-right-zero" style="padding-top:4px;"> 
                                            <form:select path="id.roleId" onchange="$('#objectPermissions').submit();" Class="form-control selectpicker show-tick">  
                                                <option value="0">Select One</option>
                                                <form:options items="${roles}" itemValue="roleId" itemLabel="roleTitle" />
                                            </form:select>
                                        </div>
                                        <div class="col-lg-4 col-sm-2">

                                        </div>
                                        <div class="col-lg-2 col-sm-2 col-sm-offset-2 text-left" style="text-align: right;">
                                            <label style="padding-top: 5px;">Assign All:</label>
                                            <input onclick="checkAll()" type="checkbox" value="false" id="chkAll" style="position: relative; top: 3px;">
                                        </div>     
                                    </div>        
                                </div>
                            </div>
                        </div>
                        <div class="row grid" style="height: auto;margin-bottom:55px;padding-bottom:3px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="table-responsive">
                                <table  class="display table" style="margin-bottom: 0px;">
                                    <thead style="background: #E6E6E6;height:35px;">
                                    <th class="hidden-sm hidden-xs col-sm-1">#</th>  
                                    <th class="col-sm-9 col-xs-5 module-width">Module(s)</th>
                                    <th class="hidden-xs"></th>
                                    <th class="hidden-xs"></th>
                                    <th class="col-sm-1 col-xs-1" style="${not empty objectPermissions.resources?'padding-left: 0px;width:90px;':''}">View</th>
                                    <th class="col-sm-1 col-xs-1" style="${not empty objectPermissions.resources?'padding-left: 0px;width:90px;':''}">Manage</th>     
                                    </thead>
                                    <tbody style="font-family:arial;font-size:13px;">      
                                        <c:forEach var="resource" items="${objectPermissions.resources}" varStatus="loop">

                                            <tr style="height:35px;">
                                                <td class="hidden-sm hidden-xs"> ${loop.index+1}</td>
                                                <td class="col-sm-9 col-xs-5 module-width">

                                                    ${resource.resourceTitle}

                                                </td>
                                                <form:hidden cssClass="chk" path="resources[${loop.index}].resourceId" />
                                                <td class="hidden-xs"></td>
                                                <td class="hidden-xs"></td>
                                                <td class="col-sm-1 col-xs-1" style="width:90px;">

                                                    <form:checkbox id="view${loop.index}" cssClass="chk" path="resources[${loop.index}].viewAllow" onclick="validateCheck()" />

                                                </td>
                                                <td class="col-sm-1 col-xs-1" style="padding-left: 16px;width:90px;">

                                                    <form:checkbox id="manage${loop.index}" cssClass="chk" path="resources[${loop.index}].manageAllow" onclick="validateCheck()" />

                                                </td>  
                                            </tr>     
                                        </c:forEach>   
                                    </tbody>     
                                </table>
                            </div>
                        </div>
                        <div class="clearfix"></div>

                    </div>
                </form:form>
                <script>
                                                function checkAll() {

                                                    if ($("#chkAll").is(':checked')) {
                                                        $('.chk').attr('checked', true);
                                                    }
                                                    else {
                                                        unCheckAll();
                                                    }
                                                }
                                                function unCheckAll() {
                                                    $('.chk').attr('checked', false);
                                                }
                                                function validate() {
                                                    var index = ${fn:length(objectPermissions.resources)};
                                                    for (var i = 0; i < index; i++) {
                                                        if ($('#view' + i).is(':checked') || $('#manage' + i).is(':checked')) {
                                                            return true;
                                                        }

                                                    }
                                                    document.getElementById("errorMessage").style.display = "block";
                                                    document.getElementById("errorMessage").innerHTML = "Please select atleast one permission.";
                                                    return false;
                                                }
                                                function hideMessage() {
                                                    document.getElementById("errorMessage").style.display = "none";
                                                }

                                                function startTimer() {
                                                    window.setInterval("hideMessage()", 7000);  // 5000 milliseconds = 5 seconds
                                                }

                                                jQuery(document).ready(function() {
                                                    var index = ${fn:length(objectPermissions.resources)};
                                                    for (var i = 0; i < index; i++) {
                                                        if ($('#view' + i).is(':checked') && $('#manage' + i).is(':checked')) {
                                                            $("#chkAll").attr('checked', true);
                                                        }
                                                        else {
                                                            $("#chkAll").attr('checked', false);
                                                        }
                                                    }
                                                });

                                                function validateCheck() {
                                                    var index = ${fn:length(objectPermissions.resources)};
                                                    for (var i = 0; i < index; i++) {
                                                        if ($('#view' + i).is(':checked') && $('#manage' + i).is(':checked')) {
                                                            $("#chkAll").attr('checked', true);
                                                        }
                                                        else {
                                                            $("#chkAll").attr('checked', false);
                                                            break;
                                                        }
                                                    }
                                                }
                </script>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
    </body>
</html>
