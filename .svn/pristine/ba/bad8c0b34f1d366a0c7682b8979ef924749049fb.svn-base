/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.patientInfo = {
    patientInfoAllergyDialogBox: null,
    appUrl: null,
    PatientProfileUpdateVO: function () {

        /**
         * attributes
         */
        this.patientProfileId = null;
        this.allergies = null;
        this.status = null;
        this.statuscode = null;
        this.errorMessage = null;
        this.phoneNumber = null;
        this.message = null;
    },
    updateAllergies: function () {
        var PatientProfileUpdateVO_Clone = new window.patientInfo.PatientProfileUpdateVO();
        PatientProfileUpdateVO_Clone.patientProfileId = $("#patientId").val();
        PatientProfileUpdateVO_Clone.allergies = $("#allergyId").val();

        $.ajax({
            url: window.patientInfo.appUrl + "/patient/updatePatientAllergies",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(PatientProfileUpdateVO_Clone),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    window.location.href = window.patientInfo.appUrl + "/patient/detail/" + $("#patientId").val();
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });


    },
    InitializeDiaologBoxAllergies: function () {
        window.patientInfo.patientInfoAllergyDialogBox = $("#dialogAllergies").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "15%",
            cache: false,
            title: "Allergy",
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
    },
    ShowDialogBoxAllergy: function (allergyValue) {

        window.patientInfo.InitializeDiaologBoxAllergies();
        window.patientInfo.patientInfoAllergyDialogBox.html(window.patientInfo.ComposeHtmlAllergy(allergyValue));
        window.patientInfo.patientInfoAllergyDialogBox.dialog("open");

    },
    ComposeHtmlAllergy: function (allergyValue) {

        var html = "<input id='allergyId' type='text' value='" + allergyValue + "'/>";
        //save & cancel button

        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<div class='col-sm-6' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.patientInfo.CancelActionBtn();' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-6' style='text-align:left;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' onclick='window.patientInfo.updateAllergies();' type='button' style='color:#FFFFFF; padding-top: 1px;'>Save</button>";
        html += "</div>";
        html += "</div>";

        return html;

    },
    CancelActionBtn: function () {
        window.patientInfo.patientInfoAllergyDialogBox.html("");
        window.patientInfo.patientInfoAllergyDialogBox.dialog("close");
        window.patientInfo.patientInfoAllergyDialogBox = null;
    },
    ProgramActivityTable: function () {
        $('#programActivityTb').dataTable({
            "sPaginationType": "full_numbers",
            "bSort": false,
            "order": [[0, "desc"]],
            "bLengthChange":true,
            "bFilter": true
        });
    },
    PointsBalanceTable: function () {
        $('#pointsBalanceTb').dataTable({
            "sPaginationType": "full_numbers",
            "bSort": false,
            "order": [[0, "desc"]],
            "bLengthChange": true,
            "bFilter": true
        });
    },
    PatientTable: function () {

        $('#patientTb').dataTable({

            "sPaginationType": "full_numbers",
            "bSort": true,
            "order": [[0, "desc"]],
            "bLengthChange": false,
            "bFilter": true
        });
    },
    ActivityMedicationTable: function () {
        $('#activeMedicationTb').dataTable({
            "sPaginationType": "full_numbers",
            "bSort": true,
            "order": [[0, "desc"]],
            "bLengthChange": true,
            "bFilter": true
        });
    },
    SendPatientNotification: function (patientId, phoneNumber) {
        if ($("#messageText").val().length == 0) {
            $("#errorSendMessage").text("Required");
            $("#errorSendMessage").attr("style", "color:red;font-size:12px;");
            $("#errorSendMessage").attr("class", "errorMessage pull-right");
            return;
        }
        var PatientProfileUpdateVO_Clone = new window.patientInfo.PatientProfileUpdateVO();
        PatientProfileUpdateVO_Clone.patientProfileId = patientId;
        PatientProfileUpdateVO_Clone.phoneNumber = phoneNumber;
        PatientProfileUpdateVO_Clone.message = $("#messageText").val();
        $.ajax({
            url: window.patientInfo.appUrl + "/patient/patientNotifiction",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(PatientProfileUpdateVO_Clone),
            success: function (data) {
                if (data == true) {
                    $("#errorSendMessage").attr("style", "color:green;font-size:12px;");
                    $("#errorSendMessage").attr("class", "message pull-right");
                    $("#errorSendMessage").text("Msg sent sucessfully.");
                    window.patientInfo.resetMessageFn();
                } else {
                    $("#errorSendMessage").text("Msg sent fail.");
                    $("#errorSendMessage").attr("style", "color:red;font-size:12px;");
                    $("#errorSendMessage").attr("class", "errorMessage pull-right");
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    resetMessageFn: function () {
        $("#messageText").val("");
    }, populateAppUrl: function (appUrl) {
        window.patientInfo.appUrl = appUrl;
    }
}


