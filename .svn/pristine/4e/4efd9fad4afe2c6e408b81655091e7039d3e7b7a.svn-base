/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.pharmacyRegisteration = {
    pharmacyLookupObject: null,
    appUrl:null,
    loginPharmacyFn: function () {
        var appUrl = window.pharmacyRegisteration.appUrl;
        var valid = true;
        if ($("#loginEmailId").val() === "") {
            $('#errorMessageLogin').html('<span>Email Address is required.</span>');
            $("#errorMessageLogin").show();
            valid = false;
            return;
        }
        if ($("#loginPasswordId").val() === "") {
            $('#errorMessageLogin').html('<span>Password is required.</span>');
            $("#errorMessageLogin").show();
            valid = false;
            return;
        }
        $.ajax({
            url: appUrl + "/PharmacyPortal/login/",
            type: "POST",
            data: $("#pharmacyLogin").serialize(),
            async: false,
            success: function (data) {
                var json_obj = $.parseJSON(data); //parse JSON
                console.log(json_obj.status);
                if (json_obj.status == 'Fail') {
                    $('#errorMessageLogin').html('<span>' + json_obj.errorMessage + '</span>');
                    $("#errorMessageLogin").show();
                    valid = false;
                }
            }
            , error: function (e) {
                alert('Error while request...' + e);
            }
        });
        if (valid) {
            window.location.href = appUrl + "/PharmacyPortal/successSignin";
            window.location.href = appUrl + "/PharmacyPortal/newMemberRequest";
        }

    },
    forgotUserNamePasswordFn: function () {
        var appUrl = window.pharmacyRegisteration.appUrl;
        var valid = true;
        if ($("#forgotFormEmail").val() === "") {
            //alert("email required");
            $('#errorMessageForgotPassword').html('<span>Email Address is Required</span><br><br>');
            $("#errorMessageForgotPassword").show();
            valid = false;
            return;
        }
        $.ajax({
            url: appUrl + "/ConsumerPortal/forgotUserNamePassword/",
            type: "POST",
            data: $("#forgotFormId").serialize(),
            async: false,
            success: function (data) {
                var json_obj = JSON.parse(data); //parse JSON
                console.log(json_obj.status);
                if (json_obj.status == 'Fail') {
                    $('#errorMessageForgotPassword').html('<span>' + json_obj.errorMessage + '</span>');
                    $("#errorMessageForgotPassword").show();
                    valid = false;
                }
            }
            , error: function (e) {
                alert('Error while request...' + e);
            }
        });
        if (valid) {
            $("#forgotFormEmail").val("");
            $('#message').attr("style", "color: green;");
            $('#message').html('<span>Username/Password sent at provided email address.</span>');
            $('#forgotUserPassword').modal('hide');
            $('#login').modal('show');
        }
        return valid;
    },
    lookupPharmacyFn: function () {

        if ($("#NPI").val() === "") {
            //alert("Pharmacy NPI is required");
            $('#errorMessagePharReg').html('<span>Pharmacy NPI is required.</span><br>');
            $('#errorMessagePharReg').show();
            return;
        } else if ($("#sitePass").val() === "") {
            //alert("Site Pass number is required");
            $('#errorMessagePharReg').html('<span>Site Pass number is required.</span><br>');
            $('#errorMessagePharReg').show();
            return;
        }

        var selectedVal = "";
        var selected = $("input[type='radio'][name='pharmacyType.id']:checked");
        if (selected.length > 0) {
            selectedVal = selected.val();
        }

        if (selectedVal === "") {
            //alert("Pharmacy NPI is required");
            $('#errorMessagePharReg').html('<span>Pharmacy Type is required.</span>');
            $('#errorMessagePharReg').show();
            return;
        }
        $.ajax({
            url: window.pharmacyRegisteration.appUrl+"/ConsumerPortal/lookup/",
            type: "POST",
            data: $("#pharmacyLookupFormId").serialize(),
            async: false,
            success: function (data) {
                console.log(data);
                var json_obj = $.parseJSON(data); //parse JSON
                console.log(json_obj.status);
                var baseDTOVar = json_obj.baseDTO;
                if (baseDTOVar != null) {
                    var pharmacyAddressHtml = '<span>';
                    pharmacyAddressHtml = pharmacyAddressHtml + baseDTOVar.title + ',&nbsp;';
                    pharmacyAddressHtml = pharmacyAddressHtml + baseDTOVar.address + ',&nbsp;';
                    pharmacyAddressHtml = pharmacyAddressHtml + baseDTOVar.city + ',&nbsp;' + baseDTOVar.state + ',&nbsp;' + baseDTOVar.zip;
                    pharmacyAddressHtml = pharmacyAddressHtml + '</span>';
                }
                if (json_obj.status == 'Fail') {
                    if (baseDTOVar != null) {
                        $('#errorMessagePharReg').html('<span>&nbsp;</span>');
                        $('#errorMessagePharReg').hide();
                        $('#endSpaceDivId').hide();
                        $('#pharmacyAddressId').html('<b>' + pharmacyAddressHtml + '</b>');
                        $('#pharmacyRegisteredMessage').html('<span>' + json_obj.errorMessage + '</span>');
                        $('#pharmacyAddressDivId').show();
                        $('#notThisId').hide();
                        $('#pharmacyRegisterBtnId').hide();
                        $('#pharmacyLoginBtnId').show();
                    } else {
                        $('#pharmacyAddressId').html("'<spna></span>'");
                        $('#pharmacyAddressDivId').hide();
                        $('#endSpaceDivId').show();
                        $('#errorMessagePharReg').html('<span>' + json_obj.errorMessage + '</span><br>');
                        $('#errorMessagePharReg').show();
                    }
                }
                if (json_obj.status == 'Success') {

                    window.pharmacyRegisteration.pharmacyLookupObject = json_obj.baseDTO;
                    $('#errorMessagePharReg').html('<span>&nbsp;</span>');
                    var messageRegIns = 'This pharmacy is not registered. Please click below <b>"Register Now"</b> button to complete your registration.';
                    $('#pharmacyRegisteredMessage').html('<span>' + messageRegIns + '</span>');
                    $('#errorMessagePharReg').hide();
                    $('#endSpaceDivId').hide();
                    $('#pharmacyAddressId').html('<b>' + pharmacyAddressHtml + '</b>');
                    $('#pharmacyAddressDivId').show();
                    $('#notThisId').show();
                    $('#pharmacyLoginBtnId').hide();
                    $('#pharmacyRegisterBtnId').show();
                }
            }
            , error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    registerationPageShow: function () {
        var npi = window.pharmacyRegisteration.pharmacyLookupObject.npi;
        var sitePass = window.pharmacyRegisteration.pharmacyLookupObject.sitePass;
        var typeId = window.pharmacyRegisteration.pharmacyLookupObject.typeId;
        window.location.href = window.pharmacyRegisteration.appUrl+"/ConsumerPortal/pharmacyRegistration?npi=" + npi + "&sitePass=" + sitePass + "&typeId=" + typeId;
    },
    forgotUserNamePasswordCancelFn: function () {
        $('#login').modal('show');
    },
    loginModelCloseAction: function () {
        window.pharmacyRegisteration.resetLoginModel();
    },
    forgotUserNamePasswordModelCloseAction: function () {
//        $("#forgotFormEmail").val("");
//        $('#errorMessageForgotPassword').html('<span>&nbsp;</span>');
        window.pharmacyRegisteration.resetLoginModel();
        $('#login').modal('show');
    },
    registerPharmacyCloseAction: function () {
        window.pharmacyRegisteration.resetLoginModel();
        $('#login').modal('show');
    },
    registerPharmacyLoginNowAction: function () {
        window.pharmacyRegisteration.resetLoginModel();
        $('#login').modal('show');
    },
    registerPharmacyLoginAction: function () {
        window.pharmacyRegisteration.resetLoginModel();
        $('#pharmacyAddressDivId').hide();
        $('#login').modal('show');
    },
    registerPharmacyResetAction: function () {
        $("#NPI").val("");
        $("#sitePass").val("");
        $("#pharmacyAddressDivId").hide();
        $("#errorMessagePharReg").hide();
        //uncheck all radio button
        $('#endSpaceDivId').show();
        $(':radio').attr('checked', false);
    },
    resetLoginModel: function () {

        $("#loginEmailId").val("");
        $("#loginPasswordId").val("");
        $("#errorMessageLogin").hide();
        $("#forgotFormEmail").val("");
        $("#errorMessageForgotPassword").hide();
        $("#NPI").val("");
        $("#sitePass").val("");
        $("#pharmacyAddressDivId").hide();
        $("#errorMessagePharReg").hide();
        $('#forgotUserPassword').modal('hide');
        $('#register').modal('hide');
        //uncheck all radio button
        $('#endSpaceDivId').show();
        $(':radio').attr('checked', false);
    },
    saveContactInfo: function () {
        var name = $("#contactName").val();
        var email = $("#contactEmail").val();
        var phone = $("#contactPhone").val();
        var msgBody = $("#msgBody").val();

        if (!this.validateContactInfo(name, email, phone, msgBody)) {
            return false;
        }
        this.hideErrorMsg();
        $.ajax({
            url: window.pharmacyRegisteration.appUrl+"/ConsumerPortal/contactUs/",
            type: "POST",
            data: $("#frmContact").serialize(),
            async: false,
            success: function (data) {
                var json_obj = $.parseJSON(data); //parse JSON
                console.log(json_obj.status);
                if (json_obj.status == 'Fail') {
                    $('#errorMessage').html(json_obj.errorMessage);
                    $('#errorMessage').attr("style", "font-size: 14px; color:red;");
                    $("#errorMessage").show();
                    valid = false;
                } else {
                    window.pharmacyRegisteration.resetContactInfo();
                    $('#errorMessage').html(json_obj.errorMessage);
                    $('#errorMessage').attr("style", "font-size: 14px; color: #2071b6;");
                    $("#errorMessage").show();
                }
            }
            , error: function (e) {
                $('#errorMessage').html('Error while request...' + e);
                $('#errorMessage').attr("style", "font-size: 14px; color: red;");
                $("#errorMessage").show();
                // alert('Error while request...' + e);
            }
        });
    },
    isEmpty: function (str) {
        return (!str || 0 === str.length);
    },
    isBlank: function (str) {
        return (!str || /^\s*$/.test(str));
    },
    formatPhoneNo: function (fId, e) {
        var phone = $("#" + fId).val();
        if (!this.isEmpty(phone) || !this.isBlank(phone)) {

            phone = phone.replace(/(\d{3})(\d{3})(\d{4})/, "$1-$2-$3");

            $("#contactPhone").val(phone);

        }
    },
    validateEmail: function (email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    },
    validateContactInfo: function (name, email, phone, msgBody) {
        $("#errorMessage").text("");
        $("#errorMessage").append('<div id="errorMessage" style="font-size: 11.9px; color: #2071b6;">&nbsp;</div>');
        var valid = true;
        if (this.isEmpty(name) || this.isBlank(name)) {
            $("#contactNameReq").text("Required");
            valid = false;
        }

        if (this.isEmpty(email) || this.isBlank(email)) {
            $("#contactEmailReq").text("Required");
            valid = false;
        } else if (!this.validateEmail(email)) {
            $("#contactEmailReq").text("Invalid email");
            valid = false;
        }
        phone = phone.split("-").join("");
        if (this.isEmpty(phone) || this.isBlank(phone)) {
            $("#contactPhoneReq").text("Required");
            valid = false;
        } else if (phone.length < 10) {
            $("#contactPhoneReq").text("Please enter valid 10 digit phone number.");
            valid = false;
        }

        if (this.isEmpty(msgBody) || this.isBlank(msgBody)) {
            $("#msgBodyReq").text("Required");
            valid = false;
        }
        return valid;
    },
    resetContactInfo: function () {
        $("#contactName").val("");
        $("#contactEmail").val("");
        $("#contactPhone").val("");
        $("#msgBody").val("");
    },
    hideErrorMsg: function (type) {
        if (type == "All") {
            $("#contactNameReq").text("");
            $('#contactNameReq').append('<span id="contactNameReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');

            $("#contactEmailReq").text("");
            $('#contactEmailReq').append('<span id="contactEmailReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');

            $("#contactPhoneReq").text("");
            $('#contactPhoneReq').append('<span id="contactPhoneReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');

            $("#msgBodyReq").text("");
            $('#msgBodyReq').append('<span id="msgBodyReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');
        }
        if (type == "contactName") {
            $("#contactNameReq").text("");
            $('#contactNameReq').append('<span id="contactNameReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');
        }
        if (type == "contactEmail") {
            $("#contactEmailReq").text("");
            $('#contactEmailReq').append('<span id="contactEmailReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');
        }
        if (type == "contactPhone") {
            $("#contactPhoneReq").text("");
            $('#contactPhoneReq').append('<span id="contactPhoneReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');
        }
        if (type == "msgBody") {
            $("#msgBodyReq").text("");
            $('#msgBodyReq').append('<span id="msgBodyReq" style="font-size: 11.9px; color: red;">&nbsp;</span>');
        }
    }, isDigit: function (e) {
        var specialKeys = new Array();
        //specialKeys.push(8); //Backspace
        //specialKeys.push(9); //Tab
        specialKeys.push(46); //Delete
        specialKeys.push(36); //Home
        specialKeys.push(35); //End
        specialKeys.push(37); //Left
        specialKeys.push(39); //Right
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        //alert(keyCode);
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

        return ret;
    }, hiwLinkActive: function () {

        $("#homeLink").attr("class", "nav-item");
        $(".nav-item").attr("class", "nav-item");
        $("#hiw").attr("class", "nav-item active");

    }, openPopUp: function (type) {
        if (type == "register") {
            $('#' + type).modal('show');
            $('#login').modal('hide');
        } else {
            $('#' + type).modal('show');
            $('#register').modal('hide');
        }
    }, checkStateZip: function (txtId, index) {
        var zipCode = $("#" + txtId).val();
        if (this.isEmpty(zipCode) || this.isBlank(zipCode)) {
            $("#errZipDiv" + index).attr("style", "color:red;");
            return false;
        }
        $("#errZipDiv" + index).attr("style", "color:red;display:none;");

        var json = {"zip": zipCode};
        $.ajax({

            url: window.pharmacyRegisteration.appUrl + "/ConsumerPortal/validateZip/" + zipCode + "/Texas",

            type: "POST",

            dataType: 'json',

            contentType: 'application/json',

            async: false,

            data: JSON.stringify(json),

            success: function (data) {

                var response = eval(data);



                //                alert("data length "+data.length);

                if (data.length == 0)

                {

                    $("#errZipDiv" + index).show();

                    //$("#errZipDiv").html("Only Texas zip codes are allowed.");

                    $("#errZipDiv" + index).html("Invalid Zip code, only available for Texas area");

                    $("#successZipDiv" + index).hide();

                    return false;

                } else

                {

                    $("#errZipDiv" + index).hide();

                    $("#successZipDiv" + index).show();
                    return true;

//                    if (iPhone == 1)
//
//                    {
//
//                        window.open("https://itunes.apple.com/us/app/rx-direct/id1195583119?mt=8");
//
//                    } else if (iPhone == 0)
//
//                    {
//
//                        window.open("https://play.google.com/store/apps/details?id=com.rxdirect&amp;hl=en");
//
//                    }

                }

            },

            error: function (e) {

                alert('Error while processing ...' + e.responseText);

            }

        });
    }, populateAppUrl: function (appUrl) {
        console.log("AppUrl is# "+appUrl);
        window.pharmacyRegisteration.appUrl = appUrl;
    }
}
