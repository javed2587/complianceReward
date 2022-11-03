/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.patientInfoDependant = {

    patientInfoDependantDialogBox: null,
    patientInfoDependantHtml: null,
    appUrl: null,
    ComposeHtmlDependant: function () {
        var html = "";
        //dependant
        //header div
//          html += "<div class='col-sm-8'>";
//            html += "<strong>Dependent Name</strong>";
//          html+="</div><br>";

        html += "<div id='dependantId'>";
        html += "<table id='deptable'>";
        html += "<thead><tr><th class='col-sm-4'>Name</th><th class='col-sm-2'>Gender</th><th class='col-sm-2'>DOB</th><th class='col-sm-4'>Allergies</th></tr></thead>";
        html += "<tbody id='deptbody'>";
        html += "</tbody>";
        html += "</table>";
        html += "</div>";
        return html;
    },
    InitializeDiaologBoxDependant: function () {
        window.patientInfoDependant.patientInfoDependantDialogBox = $("#dialogDependant").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "40%",
            cache: false,
            title: "Dependent(s) Details",
            open: function (event, ui) {
                //hide titlebar.
                $(".ui-dialog-titlebar").css("background-color", "blue");
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
        $(".ui-dialog").attr("class", "ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-draggable ui-resizable dialogDependant");
    },
    CancelActionBtn: function () {
        window.patientInfoDependant.InitializeDiaologBoxDependant.html("");
        window.patientInfoDependant.patientInfoDependantDialogBox.dialog("close");
        window.patientInfoDependant.patientInfoInsuranceDialogBox = null;
    },

    ShowDialogBoxDependant: function () {
        window.patientInfoDependant.InitializeDiaologBoxDependant();
        window.patientInfoDependant.patientInfoDependantDialogBox.html(window.patientInfoDependant.ComposeHtmlDependant());
        window.patientInfoDependant.getDependantDetails();
        window.patientInfoDependant.patientInfoDependantDialogBox.dialog("open");
    },
    getDependantDetails: function () {

        var patientProfileId = $("#patientId").val();
        var html = "";


        $.ajax({
            url: window.patientInfoDependant.appUrl+"/patient/getPatientDependant/" + patientProfileId,
            type: "GET",
            dataType: 'json',
            contentType: 'application/json',
            data: null,
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                var html = "";

                if (statusCode == 200) {
                    //console.log(response);
                    console.log(response.dependants);

                    for (var i = 0; i < response.dependants.length; i++) {

                        var firstName = response.dependants[i].firstName;
                        var secondName = response.dependants[i].lastName;
                        var fullName = firstName + " " + secondName;
                        console.log("full Name");
                        console.log(fullName);
                        //dependant
                        //html += "<div class='col-sm-3' style='padding-top:10px;'>";
                        html += "<tr>";

                        html += "<td>" + fullName + "</td>";
                        html += "<td>" + response.dependants[i].gender + "</td>";
                        html += "<td>" + response.dependants[i].dob + "</td>";
                        html += "<td>" + response.dependants[i].allergies + "</td>";
                        html += "</tr>";


                        //html += "<span>Name : <input type='text' value='"+fullName+"'/>&nbsp;Gender:"+response.dependants[i].gender+"</span>";
                        //html += "</div>";

                    }

                    window.patientInfoDependant.patientInfoDependantHtml = html;
                    $("#deptbody").html(html);
                }
                console.log("html");
                console.log(html);


            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }, populateAppUrl: function (appUrl) {
        window.patientInfoDependant.appUrl = appUrl;
    }
}
