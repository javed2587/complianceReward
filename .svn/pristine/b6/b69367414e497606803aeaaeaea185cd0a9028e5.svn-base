/**
 * Author Haider Ali
 * Delivery Address of Patient
 * 
 */

window.ssaSoftDeliveryAddress = {
    dialogBox: null,
    appUrl: null,
    SSASOFT_DeliveryAddressRequestResponse: function () {

        /**
         * attributes
         */
        this.id = null;
        this.address = null;
        this.apartment = null;
        this.zip = null;
        this.city = null;
        this.stateId = null;
        this.description = null;
        this.defaultAddress = null;
        this.addressType = null;
    },
    InitializeDiaologBox: function () {

        window.ssaSoftDeliveryAddress.dialogBox = $("#dialogDeliveryAddress").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "30%",
            cache: false,
            title: "Patient Delivery Address",
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
    LookupDeliveryAddressById: function (deliveryId) {

        var SSASOFT_DeliveryAddressRequestResponse_Clone = new window.ssaSoftDeliveryAddress.SSASOFT_DeliveryAddressRequestResponse();
        SSASOFT_DeliveryAddressRequestResponse_Clone.id = deliveryId;

        $.ajax({
            url: window.ssaSoftDeliveryAddress.appUrl + "/patient/getPatientShippingAddress",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(SSASOFT_DeliveryAddressRequestResponse_Clone),
            success: function (data) {
                console.log(data);
                var deliveryAddressResponse = eval(data);
                window.ssaSoftDeliveryAddress.States(deliveryAddressResponse);
                $("#deliveryAddressId").val(deliveryAddressResponse.id);
                $("#addressLine1").val(deliveryAddressResponse.address);
                $("#apartment").val(deliveryAddressResponse.apartment);
                $("#city").val(deliveryAddressResponse.city);
                $("#zip").val(deliveryAddressResponse.zip);
                $("#addressDescription").val(deliveryAddressResponse.description);
                if (deliveryAddressResponse.addressType == 'Work' || deliveryAddressResponse.addressType == 'WORK' || deliveryAddressResponse.addressType == 'work') {
                    $("#rdWork").val(deliveryAddressResponse.addressType);
                    $("#rdWork").attr("checked", "checked");
                } else {
                    $("#rdHome").val(deliveryAddressResponse.addressType);
                    $("#rdHome").attr("checked", "checked");
                }
                if (deliveryAddressResponse.defaultAddress == 'Yes') {
                    $("#isDefault").val(deliveryAddressResponse.defaultAddress);
                    $("#isDefault").attr("checked", "checked");
                } else {
                    $("#isDefault").val(deliveryAddressResponse.defaultAddress);
                }
                $("#shippingModal").modal('show');
                // window.ssaSoftDeliveryAddress.ShowDialogBox(deliveryAddressResponse);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    ShowDialogBox: function (deliveryAddressResponse) {

        window.ssaSoftDeliveryAddress.InitializeDiaologBox();
        window.ssaSoftDeliveryAddress.dialogBox.html(window.ssaSoftDeliveryAddress.DeliveryAddressHtml(deliveryAddressResponse));
        window.ssaSoftDeliveryAddress.States(deliveryAddressResponse);
        window.ssaSoftDeliveryAddress.dialogBox.dialog("open");

    },
    DeliveryAddressHtml: function (deliveryAddressResponse) {

        var html, address = deliveryAddressResponse.address, city = deliveryAddressResponse.city, stateId = deliveryAddressResponse.stateId, zip = deliveryAddressResponse.zip, apartment = deliveryAddressResponse.apartment;

        html = "<input id='deliveryAddressId' type='hidden' value='" + deliveryAddressResponse.id + "'/>";
        //address div
        html += "<div class='col-sm-12'>";
        html += "<div class='col-sm-2' style='padding-left: 21px;'>";
        html += "<label>Address:</label>";
        html += "</div>";
        html += "<div class='col-sm-10'>";
        html += "<input id='address' type='text' class='form-control' value='" + address + "'/>";
        html += "</div>";

        //apartment & city
        html += "<div class='col-sm-12' style='padding-top:7px;'>";
        //apartment
        html += "<div class='col-sm-2'>";
        html += "<label>Apartment:</label>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<input id='apartment' type='text' class='form-control' value='" + apartment + "'/>";
        html += "</div>";
        //city
        html += "<div class='col-sm-2' style='padding-left: 30px;'>";
        html += "<label>City:</label>";
        html += "</div>";

        html += "<div class='col-sm-5'>";
        html += "<input id='city' type='text' class='form-control' value='" + city + "'/>";
        html += "</div>";

        html += "</div>"; //end apartment & city

        //zip & state
        html += "<div class='col-sm-12' style='padding-top:7px;'>";
        //apartment
        html += "<div class='col-sm-2' style='padding-left: 47px;'>";
        html += "<label>Zip:</label>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<input id='zip' type='text' class='form-control' value='" + zip + "'/>";
        html += "</div>";
        //state
        html += "<div class='col-sm-2' style='text-align: right;'>";
        html += "<label>State:</label>";
        html += "</div>";
        html += "<div class='col-sm-5'>";
        html += "<select id='state' class='form-control selectpicker show-tick' style='padding-top:3px;'>";
        html += "<option value='0'>Select One</option>";
        html += "</select>";
        html += "</div>";

        html += "</div>"; //end Zip & State

        //save & cancel button

        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<div class='col-sm-7' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.ssaSoftDeliveryAddress.CancelActionBtn();' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-5' style='text-align:left;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' onclick='window.ssaSoftDeliveryAddress.updateDeliveryAddress();' type='button' style='color:#FFFFFF; padding-top: 1px;'>Save</button>";
        html += "</div>";
        html += "</div>";
        return html;

    },
    States: function (deliveryAddressResponse) {
        $('#state')
                .find('option')
                .remove()
                .end()
                .append('<option value="0">Select One</option>')
                .val('Select One');
        $.each(deliveryAddressResponse.states, function (index, element) {
            $('#state').append($('<option>', {value: element.id, text: element.name}));
        });

        if (deliveryAddressResponse.stateId > 0) {
            $('#state').val(deliveryAddressResponse.stateId).attr("selected", "selected");
            $("#state").trigger("change");
        }
    },
    updateDeliveryAddress: function () {

        var SSASOFT_DeliveryAddressRequestResponseUpdate_Clone = new window.ssaSoftDeliveryAddress.SSASOFT_DeliveryAddressRequestResponse();
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.id = $('#deliveryAddressId').val();
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.address = $('#addressLine1').val();
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.apartment = $('#apartment').val();
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.zip = $('#zip').val();
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.city = $('#city').val();
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.stateId = $('#state').val();
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.description = $('#addressDescription').val();
        if ($("#rdHome").val() != "") {
            SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.addressType = $("#rdHome").val();
        } else {
            SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.addressType = $("#rdWork").val();
        }
        SSASOFT_DeliveryAddressRequestResponseUpdate_Clone.defaultAddress = $("#isDefault").val();

        $.ajax({
            url: window.ssaSoftDeliveryAddress.appUrl + "/patient/savePatientShippingAddress",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(SSASOFT_DeliveryAddressRequestResponseUpdate_Clone),
            success: function (data) {
//                window.ssaSoftDeliveryAddress.dialogBox.html("");
//                window.ssaSoftDeliveryAddress.dialogBox.dialog("close");
//                window.ssaSoftDeliveryAddress.dialogBox = null;
                window.location.href = window.ssaSoftDeliveryAddress.appUrl + "/patient/detail/" + $('#patientId').val();
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    CancelActionBtn: function () {
        window.ssaSoftDeliveryAddress.dialogBox.html("");
        window.ssaSoftDeliveryAddress.dialogBox.dialog("close");
        window.ssaSoftDeliveryAddress.dialogBox = null;
    }, populateAppUrl: function (appUrl) {
        window.ssaSoftDeliveryAddress.appUrl = appUrl;
    }
}

