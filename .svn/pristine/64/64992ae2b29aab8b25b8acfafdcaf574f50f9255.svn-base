<%-- 
    Document   : deleterecord
    Created on : Jan 14, 2020, 12:49:11 PM
    Author     : Jandal
--%>

<!--%@page contentType="text/html" pageEncoding="windows-1252"%-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="content" class="clearfix">
            <jsp:include page="./inc/newMenu.jsp" />
            <div class="breadcrumbs">
                <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Delete Record
            </div>
            <div class="heading" >
                <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Delete Record</h1>
            </div> 

            <div id="content" style="background-color: lightgrey">

                <!--h1 style="color: black;text-align: center" style="background-color: lightgrey;margin-top: 20px;margin-bottom: 10px"><b>Delete Record</b></h1-->

                <div class="tab-pane col-sm-12" id="tab" style="margin-top: 10px; margin-left: 150px; padding-top: 15px">

                    <form:form  method="post" modelAttribute="deleteRecord" action="${pageContext.request.contextPath}/patient/searchPatient" onsubmit="return validateRemoveField();">

                        <!--<div class="form-group"  style="padding-top: 10px; margin-top: 0px;">-->
                        <div class="col-sm-2" style="padding-left: 80px;">
                            <label class="control-label" style="margin-top: 5px">Enter Phone/Email<form:label style="color:red;size:15px" path="">*</form:label></label>
                            </div>   
                                <div class="col-sm-3">
                                <form:input id="value" type="text" path="value" placeholder="Enter Phone/Email" cssClass="form-control" value="${deleteInfo.value}"/>
                                <div class="control-label" id="error" style="width: 900px;color: red;">${error}</div>
                                <c:if test="${not empty message}">
                                    <c:choose>
                                        <c:when test="${message eq 'Record sucessfully deleted'}" >                                                         
                                            <h4 class= "control-label" id="errorMessage" style="width: 900px;color:green;">${message}</h4>
                                        </c:when>
                                        <c:otherwise>
                                            <h4 class="control-label" id="errorMessage" style="width: 900px;color:red;">${message}</h4>
                                        </c:otherwise>
                                    </c:choose>                     
                                </c:if>
                            </div>
                        <!--<div style="margin-left: 90px;padding-left: 110px"></div>-->
                        <div class="col-sm-2">
                            <input id="serchbtn" class="btn pull-left" style="background: #2071b6;color: #FFF;padding-top: 1px; margin-top: 0px;" type="submit" value="Search"/>
                        </div>
                        <form:errors path="*" cssClass="error message" cssStyle="width: 900px"/>
                        <div class="col-sm-5"></div>
                     </div>
                    <div class="col-sm-12">
                        <div class="col-sm-3"></div>
                        <div class="col-sm-9">
                            <div style="padding-top: 5px;padding-left: 40px">
                              <label><form:radiobutton  id="patient" name="radio" path="Type" value="patient" class="radioList[]" checked="${deleteInfo.type == 'patient' ? 'checked' : '' }"  /> Patient Record </label></br>
                              <label><form:radiobutton  id="practice" name="radio" path="Type" onclick="handleRadioBox(this.id);" value="practice" class="radioList[]" checked="${deleteInfo.type == 'practice' ? 'checked' : '' }"/>  Practice Record by Phone</label>                      
                          </div>
                            </div>
                      
                    </div>
                </form:form>
           




        <!--</div>-->
        <c:if test="${not empty patient}">
            ${patient}
            <form:form id="deletePatient" action="${pageContext.request.contextPath}/patient/deleteRecord" method="post" modelAttribute="deleteRecord">
                <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px;padding-top: 4px;">  
                    <div class="contents">                 
                        <form:input type="hidden" path="value" value="${deleteInfo.value}"/>
                        <form:input type="hidden" path="type" value="${deleteInfo.type}"/>
                        <div class="table-box table-responsive" style="padding-top: 25px;">
                            <table class="display" id="example" class="display table">
                                <thead>
                                    <tr class="row grid-header">
                                        <th class="" style="padding-left: 10px;">
                                            <span class="tbl_f">Patient Name</span>
                                        </th>
                                        <th class="hidden-xs" style="padding-left: 10px;">
                                            <span class="tbl_f">DOB</span>
                                        </th>
                                        <th class="hidden-xs">
                                            <span class="tbl_f">Gender</span>
                                        </th>
                                        <th class="hidden-xs">
                                            <span class="tbl_f">Email</span>
                                            </th-->
                                        <th class="hidden-xs">
                                            <span class="tbl_f">Mobile Phone</span>
                                        </th>
                                        <th class="hidden-xs">
                                            <span class="tbl_f">Zip</span>
                                        </th>
                                        <th style="padding-left: 20px;">
                                            <span class="tbl_f">City</span>
                                        </th>
                                        <th class="text-center text-left-ipad">
                                            <span class="">&nbsp;&nbsp;Action</span> 
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="row grid-row">
                                        <td class="">
                                            <span id="tbl_textalign">${patient.firstName} ${patient.lastName}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${patient.patientDOB}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${patient.gender eq 'M'?'Male':'Female'}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${patient.emailAddress}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${patient.mobileNumber}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${patient.zip }</span>
                                        </td>
                                        <td>
                                            <span id="tbl_textalign">${patient.address}</span>
                                        </td>
                                        <td class="" align="center">

                                                    <!--img class="${sessionBean.pmap[(57).intValue()].manage eq true?'':'disabled'}" src="${pageContext.request.contextPath}/resources/images/edit.jpg"  style="cursor:pointer;" width="20" onclick="location.href = '${pageContext.request.contextPath}/patient/view/${list.id}'"/-->
                                            <a class="${sessionBean.pmap[(3).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeletePatient();"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>


                        </div>
                    </div>
                </div>
            </form:form>                                        
        </c:if>

        <c:if test="${not empty practice}">
            <form:form id="deletePractice" action="${pageContext.request.contextPath}/patient/deleteRecord" method="post" modelAttribute="deleteRecord">
                <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px;padding-top: 4px;">  
                    <div class="contents">
                        <form:input type="hidden" path="value" value="${deleteInfo.value}"/>
                        <form:input type="hidden" path="type" value="${deleteInfo.type}"/>
                        <div class="table-box table-responsive" style="padding-top: 25px;">
                            <table class="display" id="example" class="display table">
                                <thead>
                                    <tr class="row grid-header">
                                        <th class="" style="padding-left: 10px;">
                                            <span class="tbl_f">Practice Name</span>
                                        </th>
                                        <th class="hidden-xs" style="padding-left: 10px;">
                                            <span class="tbl_f">Type</span>
                                        </th>
                                        <th class="hidden-xs">
                                            <span class="tbl_f">Address</span>
                                        </th>
                                        <th class="hidden-xs">
                                            <span class="tbl_f">City</span>
                                            </th-->
                                        <th class="hidden-xs">
                                            <span class="tbl_f">Phone Number</span>
                                        </th>
                                        <th class="hidden-xs">
                                            <span class="tbl_f">Zip</span>
                                        </th>
                                        <th style="padding-left: 20px;">
                                            <span class="tbl_f">License Number</span>
                                        </th>
                                        <th class="text-center text-left-ipad">
                                            <span class="">&nbsp;&nbsp;Action</span> 
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="row grid-row">
                                        <td class="">
                                            <span id="tbl_textalign">${practice.practicename}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${practice.practicetype}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${practice.practiceaddress}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${practice.practicecity}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${practice.practicephonenumber}</span>
                                        </td>
                                        <td class="hidden-xs">
                                            <span id="tbl_textalign">${practice.practicezip}</span>
                                        </td>
                                        <td>
                                            <span id="tbl_textalign">${practice.practicelicensenumber}</span>
                                        </td>
                                        <td class="" align="center">

                                                    <!--img class="${sessionBean.pmap[(57).intValue()].manage eq true?'':'disabled'}" src="${pageContext.request.contextPath}/resources/images/edit.jpg"  style="cursor:pointer;" width="20" onclick="location.href = '${pageContext.request.contextPath}/patient/view/${list.id}'"/-->
                                            <a class="${sessionBean.pmap[(3).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeletePractice();"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>


                        </div>
                    </div>
                </div>
            </form:form>                                   
        </c:if>
        <div id="Patientdialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
        <div id="Practicedialog" style="height: 1500px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
         <div id="dvLoading1" class="dvLoading"></div>
        <script type="text/javascript">

            function replacePhoneNo()
            {
                $("#value").val($("#value").val().replace(/(\d{3})\-?(\d{3})\-?(\d{4})/, "($1) $2-$3"));
            }
            function validateRemoveField() {
                $("#error").hide();
                var value = $("#value").val();
                if (value.length === 0) {
                    $("#error").text("Input field is required.");
                    $("#error").show();
                    
                    return false;
                }
                if (document.getElementById('patient').checked === true)
                {
                    if (this.inputType() === false) {
                        return false;
                    }
                }

                if (document.getElementById('practice').checked === true)
                {
                    if (this.practiceContact(document.getElementById('practice').value) === false) {
                        return false;
                    }
                }
                var rdoTblName = document.getElementsByClassName('radioList[]');
                var tblName_value = [];
                for (var i = 0; i < rdoTblName.length; i++) {
                    if (rdoTblName[i].checked) {
                        tblName_value.push(rdoTblName[i].value);
                    }
                }
                if (tblName_value.length === 0) {
                    $("#error").text("Please select atleast one option.");
                    $("#error").show();
                    return  false;
                }
                
                $("#serchbtn").attr("disabled", true);
                return true;
            }

            function practiceContact(id) {
                if (id === "practice")
                {
                    var value = $("#value").val();
                    if (value.length === 0) {
                        $("#error").text("Input Field is Empty");
                        $("#error").show();
                        return false;
                    }
                    value = value.replace(/\D/g, '');
                    if (value.length < 10) {
                        $("#error").text("Provide valid 10 digits Phone number");
                        $("#error").show();
                        return false;
                    } else {
                        replacePhoneNo();
                    }
                }
            }
            function validateEmail(email) {
                var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }
            function inputType()
            {

//validate phone
                var inputvalue = $('#value').val();
                if ((!inputvalue.match(/[A-Za-z]/i)))
                {
                    inputvalue = inputvalue.replace(/\D/g, '');
                    if (inputvalue.length < 10) {
                        $("#error").text("Provide valid 10 digits Phone number");
                        $("#error").show();
                        return false;
                    }
                    document.getElementById('value').value = inputvalue;
                    replacePhoneNo();
                    return true;
                } else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(inputvalue)))
                {
                    $("#error").text("Invalid Email Syntex");
                    $("#error").show();
                    return false;
                }
                return true;
            }

            function isDeletePatient() {
                var dialog = $("#Patientdialog").dialog({
                    closeOnEscape: false,
                    autoOpen: false,
                    modal: true,
                    height: 170,
                    width: '22%',
                    cache: false,
                    autoResize: true,
                    open: function (event, ui) {
                        //hide titlebar.
                        $(".ui-dialog-titlebar").css("font-size", "14px");
                        $(".ui-dialog-titlebar").css("font-weight", "normal");
                        $(".ui-dialog-titlebar").hide();
                    },
                    close: function ()
                    {
                        $(this).dialog('close');
                        $(this).dialog('destroy');
                    }
                });
                var logoutHtml = '<div  style="text-align: center; ">'
                        + '<p style="margin-bottom: 20px; color:#E70000;">You want to delete this patient?</p>'
                        + '<div style="float: left; width: 49%; text-align: right; font-size: 14px;">'
                        + '<button id="PatientbtnYes"  class="btn btnisdel"  type="submit" onclick="delRecord(this.id);" style="background: #2071b6;color: #FFF;">Yes</button>'
                        + "</div>"
                        + '<div style="float: right; width: 49%; text-align: left;font-size: 14px;  ">'
                        + '<button id="PatientbtnNo" class="btn btnisdel" type="button" onclick="hidePatientModel();">No</button>'
                        + '</div>'
                        + '</div>';
                $(dialog).html(logoutHtml);
                $("#Patientdialog").dialog("open");
            }


            function isDeletePractice() {
                var dialog = $("#Practicedialog").dialog({
                    closeOnEscape: false,
                    autoOpen: false,
                    modal: true,
                    height: 260,
                    width: '25%',
                    cache: false,
                    autoResize: true,
                    open: function (event, ui) {
                        //hide titlebar.
                        $(".ui-dialog-titlebar").css("font-size", "14px");
                        $(".ui-dialog-titlebar").css("font-weight", "normal");
                        $(".ui-dialog-titlebar").hide();
                    },
                    close: function ()
                    {
                        $(this).dialog('close');
                        $(this).dialog('destroy');
                    }
                });
                var logoutHtml = '<div  style="text-align: center;">'
                        + '<p style="margin-bottom: 20px; color:#E70000;">This action will delete all the associated data like(Users,Patients,BankingDetails) with Practice.</p><p style="align:center">Do you want to continue this action?</p>'
                        + '<div style="float: left; width: 49%; text-align: right; font-size: 14px;">'
                        + '<button id="PracticebtnYes"  class="btn btnisdel"  type="submit" onclick="delRecord(this.id);" style="background: #2071b6;color: #FFF;">Yes</button>'
                        + "</div>"
                        + '<div style="float: right; width: 49%; text-align: left;font-size: 14px;  ">'
                        + '<button id="PracticebtnNo" class="btn btnisdel" type="button" onclick="hidePracticeModel();">No</button>'
                        + '</div>'
                        + '</div>';
                $(dialog).html(logoutHtml);
                $("#Practicedialog").dialog("open");
            }

            function delRecord(id) {
                if (id == "PatientbtnYes")
                {
                    $("#PatientbtnYes").attr("disabled", true);
                     $("#PatientbtnNo").attr("disabled", true);
                    $("#deletePatient").submit();
                }
                if (id == "PracticebtnYes")
                {
                     $("#PracticebtnYes").attr("disabled", true);
                     $("#PracticebtnNo").attr("disabled", true);
                    $("#deletePractice").submit();
                }
            }

            function hidePatientModel() {
                $("#Patientdialog").dialog("close");
                $("#Patientdialog").dialog('destroy');
                $("#Patientdialog").html("");
            }
            $(document).keyup(function (e) {
                if (e.keyCode == 27) {
                    hideModel();
                }   // esc
            });

            function hidePracticeModel() {
                $("#Practicedialog").dialog("close");
                $("#Practicedialog").dialog('destroy');
                $("#Practicedialog").html("");
            }
            $(document).keyup(function (e) {
                if (e.keyCode == 27) {
                    hideModel();
                }   // esc
            });

        </script>
        
<!--         <script>
                    // show loading image
                    $('#dvLoading').show();
                    $("#dvLoading1").show();

                    // main image loaded ?
                    $('#drugImg,#drugImg1').on('load', function () {
                        // hide/remove the loading image
                        $('#dvLoading').hide();
                        $("#dvLoading1").hide();
                    });

                    $(window).bind("load", function () {
                        $('#dvLoading').fadeOut(2000);
                        $("#dvLoading1").fadeOut(2000);
                    });
                </script>

                <style>
                    #dvLoading,#dvLoading1 {
                        background:url(http://loadinggif.com/images/image-selection/36.gif) no-repeat center center;
                        height: 200px;
                        width: 200px;
                        position: fixed;
                        left: 50%;
                        top: 50%;
                        margin: -25px 0 0 -25px;
                        z-index: 1000;
                    }
                </style>-->
    </div>
    <jsp:include page="./inc/footer.jsp" />

</body>
</html>
