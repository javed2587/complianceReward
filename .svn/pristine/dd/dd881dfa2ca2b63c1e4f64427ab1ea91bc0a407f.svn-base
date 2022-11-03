<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.ssa.cms.util.PropertiesUtil"%>
<jsp:useBean id="date" class="java.util.Date" />
<!--<footer class="footer">
    &COPY;<fmt:formatDate value="${date}" pattern="yyyy" />, <a  href="http://rx-direct.us"  target="_blank"  style="color: #2071b6;">Rx-Direct</a>, All rights reserved
</footer>-->
<!-------------------------------Pharmacy Transaction Report Data---------------------------------------->
<div class="modal fade financial_modal" id="pharmacymodal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4>Pharmacy Transaction Report</h4>
            </div>
            <div class="modal-body clearfix">

                <div class="col-sm-6"><label>From</label>
                    <input id="frmDateTransactional" name="frmDateTransactional" class="form-control" placeholder="mm/dd/yyyy" value='${frmDate}'
                           onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" /> </div>

                <div class="col-sm-6"><label>To</label>
                    <input   id="toDateTransactional"  name="toDateTransactional" placeholder="mm/dd/yyyy" value='${toDate}'
                             class="form-control" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"/> </div>
                <!----------------------------------------------------------------------------------------------------------------->
                <!--------------------------------------------------------->
                <div class="col-sm-6"><label>Pharmacy</label>
                    <select style="color: black" id="pharmacyTransId" name="pharmacyTransId"  >  
                        <option value="0">Select Pharmacy</option>

                    </select> </div>

                <!--------------------------------------------------------->
                <div class="col-sm-12"><label>Format</label> 
                    <input type="radio" name="formatTransactionalBtn" id="radio-aTransactional" value="pdfView" checked>Pdf &nbsp;
                    <input type="radio" name="formatTransactionalBtn" id="radio-bTransactional" value="excelView" >Excel
                </div>

                <div>
                    <span id="errorDivTransactional" style="color:red; font-weight:bold">

                    </span>      
                </div>
                <!----------------------------------------------------------------------------------------------------------------->
                <div class="col-sm-12 gobtn">
                    <input type="button" value="Submit" name="goTransactional" class="btn btn-primary" onclick="exportTransactionalReportData()"/>
                </div>
            </div>

        </div>

    </div>
</div>

<!-------------------------------Pharmacy Transaction Report Data---------------------------------------->

<!--------------------------------------------------------FINancial report section----------------------------------------->
<!--Financial Report-->
<div class="modal fade financial_modal" id="finanmodal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4>Financial Report</h4>
            </div>
            <div class="modal-body clearfix">
                <div class="col-sm-6"><label>From</label>
                    <input id="frmDateFinancial" name="frmDateFinancial" class="form-control" placeholder="mm/dd/yyyy" value='${frmDate}'
                           onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"
                           disabled="true"   /> </div>

                <div class="col-sm-6"><label>To</label>
                    <input   id="toDateFinancial" name="toDateFinancial" placeholder="mm/dd/yyyy" value='${toDate}'
                             class="form-control" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" disabled="true"/> </div>
                <!----------------------------------------------------------------------------------------------------------------->
                <div class="col-sm-6"><label>Cycle#</label>
                    <input id="cycleNoFinancial" name="currCycleFinancial" class="form-control" placeholder="Cycle#" value='${currCycle}'
                           maxlength="2" onkeypress="return IsDigit(event)"
                           /> </div>

                <div class="col-sm-6"><label>Year</label>
                    <input   id="yearFinancial" name="yearFinancial" placeholder="Year" value='${currYear}'
                             class="form-control"  maxlength="4" onkeypress="return IsDigit(event)"/> </div>
                <!--------------------------------------------------------->
                <div class="col-sm-12"><label>Pharmacy</label>
                    <select style="color: black" id="cmbPharmacyFinancialId" name="pharmacyTransId"  >  
                        <option value="0">Select Pharmacy</option>

                    </select>
                </div>
                <!--------------------------------------------------------->
                <div class="col-sm-12"><label>Format</label>
                    <input type="radio" name="formatBtn" id="radio-a" value="pdfView" checked>Pdf &nbsp;
                    <input type="radio" name="formatBtn" id="radio-b" value="excelView" >Excel
                </div>

                <div>
                    <span id="errorDivFinancial" style="color:red; font-weight:bold">

                    </span>      
                </div>
                <!----------------------------------------------------------------------------------------------------------------->
                <div class="col-sm-12 gobtn">
                    <input type="button" value="Submit" name="go" class="btn btn-primary" onclick="exportFinancialReportData()"/>
                </div>
            </div>

        </div>

    </div>
</div>
<!--/Financial Report-->
<!--------------------------------------------------------Basic Statistics report section----------------------------------------->
<!--Basic Statistics Report-->
<div class="modal fade financial_modal" id="rptBasicStatisticsModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4>Basic Statistics Report</h4>
            </div>
            <div class="modal-body clearfix">
                <div class="col-sm-6">
                    <label>From</label>
                    <input id="basicStatisticsFrmDate" name="basicStatisticsFrmDate" class="form-control" placeholder="mm/dd/yyyy"
                           onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"
                           readonly="true"   />
                    <div id="frmDateErrorDiv" style="color:red; font-weight:bold"></div>
                </div>

                <div class="col-sm-6">
                    <label>To</label>
                    <input   id="basicStatisticsToDate" name="basicStatisticsToDate" placeholder="mm/dd/yyyy"
                             class="form-control" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)" readonly="true"/>
                    <div id="toDateErrorDiv" style="color:red; font-weight:bold"></div>
                </div>
                <!----------------------------------------------------------------------------------------------------------------->


                <div class="col-sm-12"><label>Format</label>
                    <input type="radio" name="formatBtn" id="radioPdfView" value="pdfView" checked>Pdf &nbsp;
                    <input type="radio" name="formatBtn" id="radioExcelView" value="excelView" >Excel
                </div>
                <!----------------------------------------------------------------------------------------------------------------->
                <div class="col-sm-12 gobtn">
                    <input type="button" value="Submit" name="Submit" class="btn btn-primary" onclick="exportBasicStatisticsReportData()"/>
                </div>
            </div>

        </div>

    </div>
</div>
<!--/Basic Statistics-->
<!--------------------------------------------------------------------------------------------------------------------->
<div class="row footer">
    <div class="wrapper">
        <div class="col-sm-12">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <p>3201 FANNIN LN  l  Suite 200  l  SOUTHLAKE, TX  76092</p>
                </div>
                <div class="col-lg-4 col-md-6 footer_contact">
                    <ul>
                        <li><i class="fa fa-phone"></i>(888) 495-7271</li>
                        <li><a href="mailto:info@ComplianceReward.us"><i class="fa fa-envelope-o"></i>info@ComplianceReward.us</a></li>
                    </ul>
                </div>
                <div class="col-lg-4 col-md-12 footer_privcy">
                    <p>© 2020 <strong>Compliance Reward.</strong> All rights reserved.<a href="http://compliancerewards.ssasoft.com/comprewards/public/privacy-policy">Privacy Policy</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}resources/js/jquery-ui.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}resources/js/bootstrapp.min.js"></script>    
<script type="text/javascript" src="${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}resources/js/lightbox.min.js"></script>

<script type="text/javascript">
                        $(function () {
//        alert('ffffffffffff');
                            $("#toDateTransactional").datepicker({
                                dateFormat: "mm/dd/yy"
                            }).datepicker("setDate", "0");
                        });

                        $(function () {
//        alert('ffffffffffff');
                            $("#frmDateTransactional").datepicker({
                                dateFormat: "mm/dd/yy"
                            }).datepicker("setDate", "-14");
                        });





//    function myFunction() {
//
//$(document).ready(function(){
//    alert('f1');
//  $('#toDateTransactional').attr("placeholder", Date());
//
//});
//
//}
//function myFunction() {
//    alert('f2');
//document.getElementById('toDateTransactional').value= Date();
//}
//    
//  function myFunction() {
//                     document.getElementById('demo').value= Date();
//                 }




                        $('#frmDateTransactional,#toDateTransactional').datetimepicker({timepicker: false, format: 'm/d/Y',
                            onChangeDateTime: function (dp, $input) {
                                jQuery('#basicStatisticsFrmDate,#basicStatisticsToDate').datetimepicker('hide');
                            }});
                        $('#frmDateTransactional,#toDateTransactional').datetimepicker({timepicker: false, format: 'm/d/Y',
                            onChangeDateTime: function (dp, $input) {
                                jQuery('#frmDateTransactional,#toDateTransactional').datetimepicker('hide');
                            }});
                        function exportFinancialReportData() {

                            $("#errorDivFinancial").text("");

                            if ($("#cycleNoFinancial").val() == '')
                            {
                                $("#errorDivFinancial").text("Please specify Cycle#.");
                                $("#cycleNoFinancial").focus();
                                return false;
                            }

                            if ($("#yearFinancial").val() == '')
                            {
                                $("#errorDivFinancial").text("Please specify year.");
                                $("#yearFinancial").focus();
                                return false;
                            }

                            if ($("#cmbPharmacyFinancialId").val() <= 0)
                            {
                                $("#errorDivFinancial").text("Please select pharmacy.");
                                $("#cmbPharmacyFinancialId").focus();
                                return false;
                            }
                            var format = 'pdfView';
                            if ($('#radio-b').is(':checked'))
                            {
                                format = 'excelView';
                            }
                            $("#errorDivFinancial").html('');
                            window.open('${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}rxTransfer/exportPdfFinancial?fromDate=' + $("#frmDateFinancial").val() + '&toDate=' + $("#toDateFinancial").val() + '&cycle=' + $("#cycleNoFinancial").val() + '&format=' + format + "&pharmacy=" + $("#cmbPharmacyFinancialId option:selected").text() + "&pharmacyId=" + $("#cmbPharmacyFinancialId").val(), "_blank");
                            $("#finanmodal").modal('hide');
                        }

                        /////////////////////////////////////////////////////////////////////////////
                        function exportTransactionalReportData() {

//                            alert("Val "+$("#pharmacyTransId").val())
                            if ($("#pharmacyTransId").val() <= 0)
                            {
                                $("#errorDivTransactional").html("Please select pharmacy.");
                                $("#pharmacyTransId").focus();
                                return false;
                            }
                            if ($("#frmDateTransactional").val() == '')
                            {
                                $("#errorDivTransactional").html("Please specify from date.");
                                $("#frmDateTransactional").focus();
                                return false;
                            }
                            if ($("#toDateTransactional").val() == '')
                            {
                                $("#errorDivTransactional").html("Please specify to date.");
                                $("#toDateTransactional").focus();
                                return false;
                            }
                            var format = 'pdfView';
                            if ($('#radio-bTransactional').is(':checked'))
                            {
                                format = 'excelView';
                            }
                            $("#errorDivTransactional").html('');
                            window.open('${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}rxTransfer/exportPdfTransactional?fromDate=' + $("#frmDateTransactional").val() + '&toDate=' + $("#toDateTransactional").val() + '&pharmacyId=' + $("#pharmacyTransId").val() + '&format=' + format + '&pharmacy=' + $("#pharmacyTransId option:selected").text(), "_blank");
                            $("#pharmacymodal").modal('hide');
                        }

                        /////////////////////////////////////////////////////////////////////////////

                        function addSlashes(input)
                        {
                            var v = input.value;
                            if (v.match(/^\d{2}$/) !== null)
                            {
                                input.value = v + '/';
                            } else if (v.match(/^\d{2}\/\d{2}$/) !== null)
                            {
                                input.value = v + '/';
                            }
                        }

                        function IsDigit(e) {
                            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                            var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                                    || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

                            return ret;
                        }
                        ////////////////////////////////////////////////////////////

                        $(document).ready(function () {
                            displayDataTable();
                            if (window.screen.width === 480 || window.screen.width === 320) {
                                $('.caret').attr('style', 'top: 0px !important');
                                $('.dashboardrd').attr('style', 'top: 3px !important;position: relative;color: rgba(48,116,191,1);');

                            }
                            if (window.screen.width >= 1300 && window.screen.width <= 1500) {
                                $("#apiProgramReports").attr('class', 'dropdown-submenu pull-left');
                                $("#mostActiveReports").attr('class', 'dropdown-menu submenuright');
                            } else {
                                $("#apiProgramReports").attr('class', 'dropdown-submenu');
                                $("#mostActiveReports").attr('class', 'dropdown-menu');
                            }

                            //////////////////////////////////////////////
                            $("#cycleNoFinancial,#yearFinancial").bind('blur', function ()
                            {
                                //alert("A");
                                var cycle = $("#cycleNoFinancial").val();
                                var year = $("#yearFinancial").val();

                                var json = {"cycle": cycle, "year": year};
                                /////////////////////////////////////////
                                $.ajax(
                                        {
                                            url: "${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}rxTransfer/loadFinancialCycleInfo",
                                            type: "POST",
                                            dataType: 'text',
                                            contentType: 'application/json',
                                            data: JSON.stringify(json),
                                            success: function (data)
                                            {
                                                var response = $.parseJSON(data);
                                                //alert("data "+response);
                                                $("#frmDateFinancial").val(response.frmDate);
                                                $("#toDateFinancial").val(response.toDate);
                                                //alert(""+data);
                                                //alert("Drug detail has been saved successfully.")
                                                //$("#drugDataForm").submit();

                                            },
                                            error: function (xhr, status, error)
                                            {
                                                alert(xhr.responseText);
                                            }
                                        });
                                ////////////////////////////////////////
                            });
                            ///////////////////////////////////////////
                            $("#rptLnkFinancial").click(function ()
                            {
                                //alert("A");
                                //////////////////////////////////////////////////////////
                                var json = {"cycle": "0", "year": "0"};
                                $.ajax(
                                        {
                                            url: "${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}rxTransfer/loadFinancialReportParams",
                                            type: "POST",
                                            dataType: 'json',
                                            contentType: 'application/json',
                                            data: JSON.stringify(json),
                                            success: function (data)
                                            {
                                                //alert("BB "+data);
                                                // alert(""+$.parseJSON(data));
                                                var response = data;//$.parseJSON(data);
                                                $("#finanmodal").modal('show');
                                                $("#frmDateFinancial").val(response.frmDate);
                                                $("#toDateFinancial").val(response.toDate);
                                                $("#yearFinancial").val(response.year);
                                                $("#cycleNoFinancial").val(response.cycleNo);
                                                ////////////////////////////////////////////////
                                                $.each(response.lstItems, function (i, item) {
                                                    $('#cmbPharmacyFinancialId').append($('<option>', {
                                                        value: item.value,
                                                        text: item.name
                                                    }));
                                                });
                                                ////////////////////////////////////////////////

                                            },
                                            error: function (xhr, status, error)
                                            {
                                                alert(xhr.responseText);
                                            }
                                        });
                                //////////////////////////////////////////////////////////
                            });
                            //////////////////////////////////////////

                        });
                        /////////////////Transactional Report//////////////////////////////

                        ///////////////////////////////////////////
                        $("#rptLnkTransactional").click(function ()
                        {
                            ///////////////////////////////////////////////////////////
                            $.ajax(
                                    {
                                        url: "${appPath}/user/loadPharmacies",
                                        type: "GET",
                                        dataType: 'text',
                                        contentType: 'application/json',

                                        success: function (data)
                                        {
                                            var response = $.parseJSON(data);
                                            $("#pharmacymodal").modal('show');
                                            $.each(response, function (i, item) {
                                                $('#pharmacyTransId').append($('<option>', {
                                                    value: item.value,
                                                    text: item.name
                                                }));
                                            });


                                        },
                                        error: function (xhr, status, error)
                                        {
                                            alert("error " + xhr.responseText);
                                        }
                                    });
                            //////////////////////////////////////////////////////////


                        });
                        ///////////////////////////////////////////////////////////////////
                        function displayDataTable() {
                            $('#example').dataTable({
                                "sPaginationType": "full_numbers",
                                "bSort": false,
                                "order": [[0, "desc"]]
                            });
                        }
                        $(document).ready(function () {
                            var dataTable = $('#example').dataTable();
                            //var oSettings = dataTable.fnSettings();
                            $("#searchTitle_1").keyup(function () {
                                var v = dataTable.fnFilter(this.value);
                                if (typeof v === 'undefined' && this.value !== "") {
                                    $(".dataTables_empty").attr("colspan", 13);
                                }
                            });
//        if (oSettings.aoData.length === 0) {
//            $(".dataTables_empty").attr("colspan", 13);
//        }
                            $('#industryTitle,#title,#npi,#accountNo,#address1,#address2,\n\
#city,#zip,#messageTypeTitle,#brandTitle,#folderName,#fName,#smtpPort,#smtpUserName,#txtUserName,#roleTitle,#lname,#uname').bind("paste", function (e) {
                                // e.preventDefault();
                            });
                        });
                        $(window).load(function () {
                            //$('#loading').fadeOut(1000);
                            // $(document).ready(function(){

                            $('.collapsible > a').click(function () {
                                $(this).parent().toggleClass('open');
                                if ($(this).parent().siblings().hasClass('open')) {
                                    $(this).parent().siblings().removeClass('open');
                                }
                                return false;
                            }); // Collapsible

                            // Mobile Nav
                            $('.m-nav').click(function () {
                                $('.main-nav').toggleClass('open');
                            });
                            $('#imgMini').click(function () {
                                //alert("OK");
                                if ($('#searchCmp').is(':visible')) {
                                    $('#searchCmp').fadeOut('fast');
                                    $("#minus").hide();
                                    $("#max").show();
                                } else {
                                    $('#searchCmp').fadeIn('slow');
                                }

                                //$('#toggle11').slideToggle('medium'); 
                                return false;
                            });
                            $('#imgMaxi').click(function () {
                                //alert("OK");

                                $('#searchCmp').fadeIn('slow');
                                $("#minus").show();
                                $("#max").hide();
                                //$('#toggle11').slideToggle('medium'); 
                                return false;
                            });
                            $('.selectpicker').selectpicker();

                            /////////////////////////////accordion new
                            (function ($) {
                                for (var i = 0; i <= 20; i++) {
                                    $('.accordionnn > #panel' + i).hide();
                                }
                                $('.accordionnn > dt > i').removeClass('fa-arrow-circle-up').addClass('fa-arrow-circle-down');
                                $('.accordionnn > dt > i').click(function () {
                                    for (var i = 0; i <= 20; i++) {
                                        $('.accordionnn > #panel' + i).hide('slow');
                                    }
                                    $(this).parent().next().show();
                                    if ($(this).attr('class') == 'fa fa-arrow-circle-up') {
                                        $(this).removeClass('fa-arrow-circle-up').addClass('fa-arrow-circle-down');
                                    } else {
                                        $(this).addClass('fa-arrow-circle-up').removeClass('fa-arrow-circle-down');
                                    }


                                    return true;
                                });

                            })(jQuery);

                            if ($("#showFolder") != null) {
                                var val = $("#showFolder").val();
                                $("#panel" + val).show();
                            }
                            /////////////////////accordion end

                        }); // Ready.

                        $(function () {
                            //Run this script only for IE for placeholder
                            if (navigator.appName === "Microsoft Internet Explorer") {
                                $("input[type=text]").each(function () {
                                    var p;
                                    // Run this script only for input field with placeholder attribute
                                    if (p = $(this).attr('placeholder')) {
                                        // Input field's value attribute gets the placeholder value.
                                        if ($(this).val() === '') {
                                            $(this).val(p);
                                        }
                                        // On selecting the field, if value is the same as placeholder, it should become blank
                                        $(this).focus(function () {
                                            if (p === $(this).val()) {
                                                return $(this).val('');
                                            }
                                        });
                                        // On exiting field, if value is blank, it should be assigned the value of placeholder
                                        $(this).blur(function () {
                                            if ($(this).val() === '') {
                                                return $(this).val(p);
                                            }
                                        });
                                    }
                                });
                                $("input[type=password]").each(function () {
                                    var e_id, p;
                                    if (p = $(this).attr('placeholder')) {
                                        e_id = $(this).attr('id');
                                        // change input type so that the text is displayed
                                        if ($(this).val() === '') {
                                            document.getElementById(e_id).type = 'text';
                                            $(this).val(p);
                                        }
                                        $(this).focus(function () {
                                            // change input type so that password is not displayed
                                            document.getElementById(e_id).type = 'password';
                                            if (p === $(this).val()) {
                                                return $(this).val('');
                                            }
                                        });
                                        $(this).blur(function () {
                                            if ($(this).val() === '') {
                                                document.getElementById(e_id).type = 'text';
                                                $(this).val(p);
                                            }
                                        });
                                    }
                                });
                                $('form').submit(function () {
                                    //Interrupt submission to blank out input fields with placeholder values
                                    $("input[type=text]").each(function () {
                                        if ($(this).val() === $(this).attr('placeholder')) {
                                            $(this).val('');
                                        }
                                    });
                                    $("input[type=password]").each(function () {
                                        if ($(this).val() === $(this).attr('placeholder')) {
                                            $(this).val('');
                                        }
                                    });
                                });
                            }
                        });
                        var specialKeys = new Array();
                        //specialKeys.push(8); //Backspace
                        //specialKeys.push(9); //Tab
                        specialKeys.push(46); //Delete
                        specialKeys.push(36); //Home
                        specialKeys.push(35); //End
                        specialKeys.push(37); //Left
                        specialKeys.push(39); //Right
                        function IsAlphaNumeric(e) {
                            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                            var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                                    || keyCode == 32 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

                            return ret;
                        }
                        function IsAlphaNumeric2(e) {
                            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                            var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                                    || keyCode == 32 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16 || keyCode == 45);

                            return ret;
                        }
                        function IsDigit(e) {
                            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                            var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                                    || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

                            return ret;
                        }
                        $(function () {
                            // setTimeout() function will be fired after page is loaded
                            // it will wait for 5 sec. and then will fire
                            // $("#successMessage").hide() function
                            setTimeout(function () {
                                $('#errorMessage').fadeOut('slow');
                                $('#message').fadeOut('slow');
                                $('.errorMessageValid').fadeOut('slow');
                                $('.errorMessageValid1').fadeOut('slow');
                                $('.errorMessage').fadeOut('slow');
                            }, 5000);
                        });
                        var specialKeys = new Array();
                        specialKeys.push(36); //Home
                        specialKeys.push(35); //End
                        specialKeys.push(37); //Left
                        specialKeys.push(39); //Right
                        function IsAlphaNumericDots(e) {
                            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
                            var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                                    || keyCode == 32 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16 || keyCode == 46);

                            return ret;
                        }
                        function isFloatNumber(item, evt) {
                            evt = (evt) ? evt : window.event;
                            var charCode = (evt.which) ? evt.which : evt.keyCode;
                            if (charCode == 46)
                            {
                                var regex = new RegExp(/\./g);
                                var count = $(item).val().match(regex).length;
                                if (count > 1)
                                {
                                    return false;
                                }
                            }
                            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                                return false;
                            }
                            return true;
                        }
                        function onlyAlphabets(e, t) {
                            try {
                                if (window.event) {
                                    var charCode = window.event.keyCode;
                                } else if (e) {
                                    var charCode = e.which;
                                } else {
                                    return true;
                                }
                                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode === 32 || charCode === 8)
                                    return true;
                                else
                                    return false;
                            } catch (err) {
                                alert(err.Description);
                            }
                        }

// Empty title issue. Enter atleast one character
                        $('input').on('keydown', function (e) {
                            if (e.which === 32 && e.target.selectionStart === 0) {
                                return false;
                            }
                        });


                        function isDeleteRecord(path) {
                            var dialog = $("#dialog").dialog({
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
                                    + '<p style="margin-bottom: 20px; color:#E70000;">You want to delete this record?</p>'
                                    + '<div style="float: left; width: 49%; text-align: right; font-size: 14px;">'
                                    + '<a id="btnL"  href=\"${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}' + path + '\" style="cursor:pointer;"  ><button id="btnYes"  class="btn btnisdel"  type="submit">Yes</button></a>'
                                    + "</div>"
                                    + '<div style="float: right; width: 49%; text-align: left;font-size: 14px;  ">'
                                    + '<button  class="btn btnisdel" type="button" onclick="hideModel();">No</button>'
                                    + '</div>'
                                    + '</div>';
                            $(dialog).html(logoutHtml);
                            $("#dialog").dialog("open");
                        }
                        function hideModel() {
                            $("#dialog").dialog("close");
                            $("#dialog").dialog('destroy');
                            $("#dialog").html("");
                        }
                        $(document).keyup(function (e) {
                            if (e.keyCode == 27) {
                                hideModel();
                            }   // esc
                        });

                        $('.isDecimalValue').keypress(function (event) {
                            var $this = $(this);
                            if ((event.which != 46 || $this.val().indexOf('.') != -1) &&
                                    ((event.which < 48 || event.which > 57) &&
                                            (event.which != 0 && event.which != 8))) {
                                event.preventDefault();
                            }

                            var text = $(this).val();
                            if ((event.which == 46) && (text.indexOf('.') == -1)) {
                                setTimeout(function () {
                                    if ($this.val().substring($this.val().indexOf('.')).length > 3) {
                                        $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
                                    }
                                }, 1);
                            }

                            if ((text.indexOf('.') != -1) &&
                                    (text.substring(text.indexOf('.')).length > 3) &&
                                    (event.which != 0 && event.which != 8) &&
                                    ($(this)[0].selectionStart >= text.length - 2)) {
                                event.preventDefault();
                            }
                        });
                        function validateYN(evt)
                        {
                            var theEvent = evt || window.event;
                            var k = theEvent.keyCode || theEvent.which;
                            if (k == 89 || k == 78 || k == 8 || k == 46)
                                return true;
                            else {
                                //alert("Your Message");
                                evt.preventDefault();
                                return false;
                            }
                        }
                        function validateYNIgnoreCase(evt)
                        {
                            var theEvent = evt || window.event;
                            var k = theEvent.keyCode || theEvent.which;
                            if (k == 89 || k == 78 || k == 110 || k == 121)
                                return true;
                            else {
                                //alert("Your Message");
                                evt.preventDefault();
                                return false;
                            }
                        }
                        function isNumberAndComma(evt) {
                            evt = (evt) ? evt : window.event;
                            var charCode = (evt.which) ? evt.which : evt.keyCode;
                            //allow comma and arrow keys
                            if (charCode === 44 || charCode === 37 || charCode === 38 || charCode === 39 || charCode === 40) {
                                return true;
                            }
                            if (charCode > 31 && (charCode < 46 || charCode > 57)) {
                                return false;
                            }
                            return true;
                        }
                        $("#rptLnkBasicStatistics").click(function ()
                        {
                            $("#rptBasicStatisticsModal").modal('show');
                        });
                        function exportBasicStatisticsReportData() {
                            if ($("#basicStatisticsFrmDate").val() == '')
                            {
                                $("#frmDateErrorDiv").html("Required");
                                $("#frmDate").focus();
                                return false;
                            }
                            if ($("#basicStatisticsToDate").val() == '')
                            {
                                $("#toDateErrorDiv").html("Required");
                                $("#toDate").focus();
                                return false;
                            }
                            var format = 'pdfView';
                            if ($('#radioExcelView').is(':checked'))
                            {
                                format = 'excelView';
                            }
                            $("#frmDateErrorDiv").html('');
                            $("#toDateErrorDiv").html('');
                            window.open('${pageContext.request.contextPath}${fn:trim(PropertiesUtil.getProperty('APP_PATH_CODE'))}report/exportExcel?fromDate=' + $("#basicStatisticsFrmDate").val() + '&toDate=' + $("#basicStatisticsToDate").val() + '&format=' + format, "_blank");
                            $("#rptBasicStatisticsModal").modal('hide');
                        }
</script>     