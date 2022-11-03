/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.patientInfoInsurance = {
    patientInfoInsuranceDialogBox: null,
    appUrl:null,
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
            url: window.patientInfoInsurance.appUrl+"/patient/savePatientInsurance",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(PatientProfileInsuranceVO_Clone),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    window.location.href = "/patient/detail/" + $("#patientId").val();
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
            url: window.patientInfoInsurance.appUrl+"/patient/getPatientInsurance/" + insuranceDetailId,
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
                $(".ui-dialog-titlebar").css("background-color", "blue");
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
        html += "<div class='col-sm-12 field_rwp'>";
        html += "<label>Memeber Id :</label>";
        html += "<input id='memberIDId' type='text' disabled='disabled'/>";
        html += "</div>";
        //insurance provider
        html += "<div class='col-sm-12 field_rwp'>";
        html += "<label>Insurance Provider :</label>";
        html += "<input id='insuranceProviderId' type='text' disabled='disabled'/>";
        html += "</div>";
        //GroupNumber
        html += "<div class='col-sm-12 field_rwp'>";
        html += "<label>Group Number :</label>";
        html += "<input id='groupNumberId' type='text' disabled='disabled'/>";
        html += "</div>";
        //PlanId
        html += "<div class='col-sm-12 field_rwp'>";
        html += "<label>Plan Id :</label>";
        html += "<input id='planIdId' type='text' disabled='disabled'/>";
        html += "</div>";

        //ProviderPhone
        html += "<div class='col-sm-12 field_rwp'>";
        html += "<label>Provider Phone :</label>";
        html += "<input id='providerPhoneId' type='text' disabled='disabled' maxlength='11'/>";
        html += "</div>";

        //ProviderAddress
        html += "<div class='col-sm-12 field_rwp'>";
        html += "<label>Provider Address :</label>";
        html += "<input id='providerAddressId' type='text' disabled='disabled'/>";
        html += "</div>";

        //ProviderAddress
        html += "<div class='col-sm-12 field_rwp'>";
        html += "<label>Expiry Date :</label>";
        //html += "<span><input id='expiryDateId' type='text'/></span>";
        //<form:input path="userStartDate" id="datetimepicker" cssClass="form-control" placeholder="mm/dd/yyyy" onkeyup="addSlashes(this)" maxlength="10" onkeypress="return IsDigit(event)"/>
        html += "<input id='datetimepicker' type='text' disabled='disabled'/>";
        html += "</div>";

        //save & cancel button



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

    },populateAppUrl: function (appUrl) {
        window.patientInfoInsurance.appUrl = appUrl;
    }
}
