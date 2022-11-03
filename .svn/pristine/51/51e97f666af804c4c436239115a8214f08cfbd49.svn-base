/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.patientInfoInsurance = {

    patientInfoInsuranceDialogBox: null,
    appUrl: null,
    PatientProfileInsuranceVO: function () {

        /**
         * attributes
         */
        this.id = null;
        this.patientProfileId = null;
        this.memberID = null;
        this.insuranceProvider = null;
        this.groupNumber = null;
        this.planId = null;
        this.providerPhone = null;
        this.providerAddress = null;
        this.expiryDate = null;
    },
    saveUpdateInsurnaceDetails: function () {

        var PatientProfileInsuranceVO_Clone = new window.patientInfoInsurance.PatientProfileInsuranceVO();
        PatientProfileInsuranceVO_Clone.patientProfileId = $("#patInsId").val();
        PatientProfileInsuranceVO_Clone.patientProfileId = $("#patientId").val();
        PatientProfileInsuranceVO_Clone.memberID = $("#memberIDId").val();
        PatientProfileInsuranceVO_Clone.insuranceProvider = $("#insuranceProviderId").val();
        PatientProfileInsuranceVO_Clone.groupNumber = $("#groupNumberId").val();
        PatientProfileInsuranceVO_Clone.planId = $("#planIdId").val();
        PatientProfileInsuranceVO_Clone.providerPhone = $("#providerPhoneId").val();
        PatientProfileInsuranceVO_Clone.providerAddress = $("#providerAddressId").val();
        PatientProfileInsuranceVO_Clone.expiryDate = $("#datetimepicker").val();

        $.ajax({
            url: window.patientInfoInsurance.appUrl + "/patient/savePatientInsurance",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(PatientProfileInsuranceVO_Clone),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    window.location.href = window.patientInfoInsurance.appUrl + "/patient/detail/" + $("#patientId").val();
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });


    },
    getInsurnaceDetails: function () {

        //var patientProfileId = $("#patientId").val();
        var insuranceDetailId=$("#insuranceDetailId").val();

        $.ajax({
            url: window.patientInfoInsurance.appUrl + "/patient/getPatientInsurance/" + insuranceDetailId,
            type: "GET",
            dataType: 'json',
            contentType: 'application/json',
            data: null,
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    console.log(data);
                    console.log(data.baseDTO.memberID);
                    $("#patInsId").val(data.baseDTO.id);
                    $("#memberIDId").val(data.baseDTO.memberID);
                    $("#insuranceProviderId").val(data.baseDTO.insuranceProvider);
                    $("#groupNumberId").val(data.baseDTO.groupNumber);
                    $("#planIdId").val(data.baseDTO.planId);
                    $("#providerPhoneId").val(data.baseDTO.providerPhone);
                    $("#providerAddressId").val(data.baseDTO.providerAddress);
                    $("#datetimepicker").val(data.baseDTO.expiryDate);
                    $("#insEditModal").modal('show');
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });


    },
    InitializeDiaologBoxInsurance: function () {
        window.patientInfoInsurance.patientInfoInsuranceDialogBox = $("#dialogInsurance").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "30%",
            cache: false,
            title: "Insurance Details",
            open: function (event, ui) {
                //hide titlebar.
                $(".ui-dialog-titlebar").css("font-size", "14px");
                $(".ui-dialog-titlebar").css("font-weight", "normal");
                $(".ui-dialog").attr("class", "ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-draggable ui-resizable dialogInsurance");
            },
            close: function ()
            {
                $(this).dialog('close');
                $(this).dialog('destroy');
                $(this).html("");
            }
        });

    },
    ShowDialogBoxInsurance: function () {

        window.patientInfoInsurance.InitializeDiaologBoxInsurance();
        window.patientInfoInsurance.patientInfoInsuranceDialogBox.html(window.patientInfoInsurance.ComposeHtmlInsurance());
        window.patientInfoInsurance.getInsurnaceDetails();
        window.patientInfoInsurance.patientInfoInsuranceDialogBox.dialog("open");

    },
    ComposeHtmlInsurance: function () {
        var html = "";
        html += "<input id='patInsId' type='hidden'>";
        //member Id
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Memeber Id :</span>&nbsp;&nbsp;";
        html += "<span><input id='memberIDId' type='text'/></span>";
        html += "</div>";
        //insurance provider
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>InsuranceProvider :</span>&nbsp;&nbsp;";
        html += "<span><input id='insuranceProviderId' type='text'/></span>";
        html += "</div>";
        //GroupNumber
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Group Number :</span>&nbsp;&nbsp;";
        html += "<span><input id='groupNumberId' type='text'/></span>";
        html += "</div>";
        //PlanId
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Plan Id :</span>&nbsp;&nbsp;";
        html += "<span><input id='planIdId' type='text'/></span>";
        html += "</div>";

        //ProviderPhone
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Provider Phone :</span>&nbsp;&nbsp;";
        html += "<span><input id='providerPhoneId' type='text'/></span>";
        html += "</div>";

        //ProviderAddress
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>&nbsp;&nbsp;&nbsp;Provider Address :</span>&nbsp;&nbsp;";
        html += "<span><input id='providerAddressId' type='text'/></span>";
        html += "</div>";

        //ProviderAddress
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Expiry Date :&nbsp;&nbsp;</span>";
        //html += "<span><input id='expiryDateId' type='text'/></span>";
        //<form:input path="userStartDate" id="datetimepicker" cssClass="form-control" placeholder="mm/dd/yyyy" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"/>
        html += "<span><input id='datetimepicker' type='text' placeholder='mm/dd/yyyy' onkeyup='addSlashes(this)' maxlength='10' onkeypress='return IsDigit(event)' onclick='window.patientInfoInsurance.showDatePicker();'/></span>";
        html += "</div>";

        //save & cancel button

        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<div class='col-sm-6' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.patientInfoInsurance.CancelActionBtn();' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-6' style='text-align:left;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' onclick='window.patientInfoInsurance.saveUpdateInsurnaceDetails();' type='button' style='color:#FFFFFF; padding-top: 1px;'>Save</button>";
        html += "</div>";
        html += "</div>";

        window.patientInfoInsurance.showDatePicker();
        return html;

    },
    CancelActionBtn: function () {
        window.patientInfoInsurance.patientInfoInsuranceDialogBox.html("");
        window.patientInfoInsurance.patientInfoInsuranceDialogBox.dialog("close");
        window.patientInfoInsurance.patientInfoInsuranceDialogBox = null;
    },
    showDatePicker: function () {
        $('#datetimepicker').datetimepicker({timepicker: false, format: 'm/d/Y',
            onChangeDateTime: function (dp, $input) {
                jQuery('#datetimepicker').datetimepicker('hide');
            }});

    }, populateAppUrl: function (appUrl) {
        window.patientInfoInsurance.appUrl = appUrl;
    }
}
