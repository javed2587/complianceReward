/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.drugSetup = {

    drugCategoryDialogBox: null,
    therapyClassDialogBox: null,
    genericNameDialogBox: null,
    brandNameDialogBox: null,
    appUrl: null,
    saveDrugCategory: function () {

        var drugCategoryId = $("#dialogTagDrugCategoryId").val();
        var json = {"name": drugCategoryId};

        $.ajax({
            url: window.drugSetup.appUrl + "/drugBrand/saveDrugCategory",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    //alert('Drug Category saved succcessfully !!');
                    $("#messageJavaScript").attr("style", "display:block;");
                    successMessageHtml = "<span>Drug Category saved succcessfully</span>"
                    $("#messageJavaScript").html(successMessageHtml);
                    $('#searchDrugCatId').val(response.auditFields.id);
                    window.drugSetup.CancelActionBtn(window.drugSetup.drugCategoryDialogBox);
                }
                if (statusCode != 200) {
                    alert(response.errorMessage);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    saveTherapyClass: function () {

        var catId = $("#searchDrugCatId").val();
        var therapyClassId = $("#dialogTagTherapyClassId").val();
        var json = {"catId": catId, "name": therapyClassId};

        $.ajax({
            url: window.drugSetup.appUrl + "/drugBrand/saveTherapyClass",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    //alert('Therapy Class saved succcessfully !!');
                    $("#messageJavaScript").attr("style", "display:block;");
                    successMessageHtml = "<span>Therapy Class saved succcessfully</span>"
                    $("#messageJavaScript").html(successMessageHtml);
                    $('#searchTherapyClassId').val(response.auditFields.id);
                    window.drugSetup.CancelActionBtn(window.drugSetup.therapyClassDialogBox);
                }
                if (statusCode != 200) {
                    alert(response.errorMessage);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    saveGenericName: function () {


        var therapyClassId = $("#searchTherapyClassId").val();
        var genericName = $("#dialogTagGenericNameId").val();
        var json = {"catId": therapyClassId, "name": genericName};

        $.ajax({
            url: window.drugSetup.appUrl + "/drugBrand/saveGenericName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    //alert('Generic Name saved succcessfully !!');
                    $("#messageJavaScript").attr("style", "display:block;");
                    successMessageHtml = "<span>Generic Name saved succcessfully</span>"
                    $("#messageJavaScript").html(successMessageHtml);
                    $('#searchgenericNameId').val(response.auditFields.id);
                    window.drugSetup.CancelActionBtn(window.drugSetup.genericNameDialogBox);
                }
                if (statusCode != 200) {
                    alert(response.errorMessage);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    saveBrandName: function () {

        var genericNameId = $("#searchgenericNameId").val();
        var brandName = $("#dialogTagBrandNameId").val();
        var json = {"catId": genericNameId, "name": brandName};

        $.ajax({
            url: window.drugSetup.appUrl + "/drugBrand/saveBrandName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    //alert('Brand Name saved succcessfully !!');
                    $("#messageJavaScript").attr("style", "display:block;");
                    successMessageHtml = "<span>Brand Name saved succcessfully</span>"
                    $("#messageJavaScript").html(successMessageHtml);
                    $('#searchbrandNameId').val(response.auditFields.id);
                    window.drugSetup.CancelActionBtn(window.drugSetup.brandNameDialogBox);
                }
                if (statusCode != 200) {
                    alert(response.errorMessage);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    InitializeDiaologBoxDrugCategory: function () {

        var w;
        if (window.screen.width > 360) {
            w = 360;
        } else {
            w = $(window).width() * 0.9;
        }
        window.drugSetup.drugCategoryDialogBox = $("#dialogDrugCategory").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "18%",
            cache: false,
            title: "ADD NEW DRUG CATEGORY",
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
    InitializeDiaologBoxTherapyClass: function () {

        var w;
        if (window.screen.width > 360) {
            w = 360;
        } else {
            w = $(window).width() * 0.9;
        }
        window.drugSetup.therapyClassDialogBox = $("#dialogTherapyClass").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "18%",
            cache: false,
            title: "ADD NEW THERAPY CLASS",
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
    InitializeDiaologBoxGenericName: function () {

        var w;
        if (window.screen.width > 360) {
            w = 360;
        } else {
            w = $(window).width() * 0.9;
        }
        window.drugSetup.genericNameDialogBox = $("#dialogGenericName").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "18%",
            cache: false,
            title: "ADD NEW Generic Name",
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
    InitializeDiaologBoxBrandName: function () {

        var w;
        if (window.screen.width > 360) {
            w = 360;
        } else {
            w = $(window).width() * 0.9;
        }
        window.drugSetup.brandNameDialogBox = $("#dialogBrandName").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "18%",
            cache: false,
            title: "ADD NEW Brand Name",
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
    ShowDialogBoxDrugCategory: function () {

        window.drugSetup.InitializeDiaologBoxDrugCategory();
        window.drugSetup.drugCategoryDialogBox.html(window.drugSetup.ComposeHtmlDrugCategory());
        window.drugSetup.drugCategoryDialogBox.dialog("open");

    },
    ShowDialogBoxTherapyClass: function () {

        window.drugSetup.InitializeDiaologBoxTherapyClass();
        window.drugSetup.therapyClassDialogBox.html(window.drugSetup.ComposeHtmlTherapyClass());
        window.drugSetup.therapyClassDialogBox.dialog("open");

    },
    ShowDialogBoxGenericName: function () {

        window.drugSetup.InitializeDiaologBoxGenericName();
        window.drugSetup.genericNameDialogBox.html(window.drugSetup.ComposeHtmlGenericName());
        window.drugSetup.genericNameDialogBox.dialog("open");

    },
    ShowDialogBoxBrandName: function () {

        window.drugSetup.InitializeDiaologBoxBrandName();
        window.drugSetup.brandNameDialogBox.html(window.drugSetup.ComposeHtmlBrandName());
        window.drugSetup.brandNameDialogBox.dialog("open");

    },
    ComposeHtmlDrugCategory: function () {

        var html = "<div class='col-sm-12'>";
        html += "<div class='col-sm-1' style='text-align:right;'>";
        html += "</div>";
        html += "<div class='col-sm-11' style='text-align:right;'>";
        html += "<input id='dialogTagDrugCategoryId' type='text'/>";
        html += "</div>";
        html += "</div>";

        //save & cancel button

        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<div class='col-sm-6' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.drugSetup.CancelActionBtn(window.drugSetup.drugCategoryDialogBox);' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-6' style='text-align:left;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' onclick='window.drugSetup.saveDrugCategory();' type='button' style='color:#FFFFFF; padding-top: 1px;'>Save</button>";
        html += "</div>";
        html += "</div>";

        return html;
    },
    ComposeHtmlTherapyClass: function () {

        var html = "<div class='col-sm-12'>";
        html += "<div class='col-sm-1' style='text-align:right;'>";
        html += "</div>";
        html += "<div class='col-sm-11' style='text-align:right;'>";
        html += "<input id='dialogTagTherapyClassId' type='text'/>";
        html += "</div>";
        html += "</div>";

        //save & cancel button
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<div class='col-sm-6' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.drugSetup.CancelActionBtn(window.drugSetup.therapyClassDialogBox);' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-6' style='text-align:left;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' onclick='window.drugSetup.saveTherapyClass();' type='button' style='color:#FFFFFF; padding-top: 1px;'>Save</button>";
        html += "</div>";
        html += "</div>";

        return html;
    },
    ComposeHtmlGenericName: function () {

        var html = "<div class='col-sm-12'>";
        html += "<div class='col-sm-1' style='text-align:right;'>";
        html += "</div>";
        html += "<div class='col-sm-11' style='text-align:right;'>";
        html += "<input id='dialogTagGenericNameId' type='text'/>";
        html += "</div>";
        html += "</div>";

        //save & cancel button
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<div class='col-sm-6' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.drugSetup.CancelActionBtn(window.drugSetup.genericNameDialogBox);' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-6' style='text-align:left;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' onclick='window.drugSetup.saveGenericName();' type='button' style='color:#FFFFFF; padding-top: 1px;'>Save</button>";
        html += "</div>";
        html += "</div>";

        return html;
    },
    ComposeHtmlBrandName: function () {

        var html = "<div class='col-sm-12'>";
        html += "<div class='col-sm-1' style='text-align:right;'>";
        html += "</div>";
        html += "<div class='col-sm-11' style='text-align:right;'>";
        html += "<input id='dialogTagBrandNameId' type='text'/>";
        html += "</div>";
        html += "</div>";

        //save & cancel button
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<div class='col-sm-6' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.drugSetup.CancelActionBtn(window.drugSetup.brandNameDialogBox);' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-6' style='text-align:left;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' onclick='window.drugSetup.saveBrandName();' type='button' style='color:#FFFFFF; padding-top: 1px;'>Save</button>";
        html += "</div>";
        html += "</div>";

        return html;
    },
    CancelActionBtn: function (dialogBox) {
        dialogBox.html("");
        dialogBox.dialog("close");
        dialogBox = null;
    },
    DrugHtml: function () {
        var counterString = $("#counter").val();
        var counter = parseInt(counterString);

        var nextIndex = counter + 1;
        var select = document.getElementsByTagName('select');
        var html = "";

        html += '<div id="drugs-' + nextIndex + '" class="col-sm-12">';
        html += '<input type="hidden" id="drugSetupDrugBean[' + nextIndex + '].drugId" name="drugSetupDrugBean[' + nextIndex + '].drugId"/>';
        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 115px;padding-top:13px;">';
        html += '<input type="text" name="drugSetupDrugBean[' + nextIndex + '].strength" class="form-control" maxlength="5" onkeypress="return IsDigit(event)"/>';
        html += '</div>';
        html += '</div>';
        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 150px;">';

        html += '<select id="drugs' + nextIndex + '.drugUnits.id" name="drugSetupDrugBean[' + nextIndex + '].mou" class="form-control selectpicker show-tick">';
        html += select[0].innerHTML;
        html += '</select>';

        html += '</div>';
        html += '</div>';

        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 145px;">';
        html += '<select id="drugs' + nextIndex + '.drugType" name="drugSetupDrugBean[' + nextIndex + '].type" class="form-control selectpicker show-tick">';
        html += select[1].innerHTML;
        html += '</select>';
        html += '</div>';
        html += '</div>';

        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 125px;">';
        html += '<input type="text" name="drugSetupDrugBean[' + nextIndex + '].gcn" class="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/>';
        html += '</div>';
        html += '</div>';
        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 120px;">';
        html += '<input type="text" name="drugSetupDrugBean[' + nextIndex + '].gpi" class="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/>';
        html += '</div>';
        html += '</div>';
        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 225px;">';
        html += '<input type="text" name="drugSetupDrugBean[' + nextIndex + '].route" class="form-control3"/>';
        html += '</div>';
        html += '</div>';
        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">';
        html += '<input type="text" name="drugSetupDrugBean[' + nextIndex + '].mac" class="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/>';
        html += '</div>';
        html += '</div>';
        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">';
        html += '<input type="text" name="drugSetupDrugBean[' + nextIndex + '].awp" class="form-control" maxlength="7" onkeypress="return isFloatNumber(this,event)"/>';
        html += '</div>';
        html += '</div>';

        html += '<div class="form-group padd-left-zero">';
        html += '<div class="col-lg-2 col-md-12 col-sm-12 col-xs-10" style="font-weight:normal;color:#5B585F;font-size: 13px;width: 175px;">';

        html += '<input type="text" name="drugSetupDrugBean[' + nextIndex + '].deaSchedule" class="form-control3" maxlength="5" onkeypress="return isFloatNumber(this,event)"/>';
        html += '<a id="addMore" href="#" class="fa fa-minus-circle" style="position:relative;top:-21px;float:right;left:20px;" onclick="deleteDiv(' + nextIndex + ');"></a>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $("#drugRecordDiv").append(html);
        divId = 'drugs' + nextIndex + '.drugUnits.id';
        divIdDrugType = 'drugs' + nextIndex + '.drugType';
        $('[id="' + divId + '"]').selectpicker('refresh');
        $('[id="' + divIdDrugType + '"]').selectpicker('refresh');

        $("#counter").val(nextIndex);
    },
    RemoveDrug: function (divId) {
        $("#" + divId).remove();
    }, populateAppUrl: function (appUrl) {
        window.drugSetup.appUrl = appUrl;
    }
}

