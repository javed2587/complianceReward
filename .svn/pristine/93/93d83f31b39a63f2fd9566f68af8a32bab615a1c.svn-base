/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.support = {
    appUrl: null,
    updateFillDate: function () {
        var orderId = $("#orderIdhiden").val();
        var nextRefill = $("#nextRefill").val();
//         alert(orderId);
//         alert(nextRefill);

        if ($("#nextRefill").val().length == 0) {
            $("#errorMessage").text("Enter fill date.");
            $("#errorMessage").attr("class", "errorMessage");
            $("#errorMessage").attr("style", "display:block;");
            return;
        }
        var json = {"orderId": orderId, "fillDate": nextRefill};
        $.ajax({
            url: window.support.appUrl + "/updateOrderFillDate",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                if (response.statuscode == 200) {
                    $("#oldRefillDate" + orderId).text($("#nextRefillDateFId_" + orderId).val());
                    $("#errorMessage").text(data.errorMessage);
                    $("#errorMessage").attr("class", "message");
                    $("#errorMessage").attr("style", "display:block;");
                    window.support.hideDetailFn();
                } else {
                    $("#errorMessage").text(data.errorMessage);
                    $("#errorMessage").attr("class", "errorMessage");
                    $("#errorMessage").attr("style", "display:block;");
                }

            },
            error: function (e) {
                $("#errorMessage").text("Internal server error.");
                $("#errorMessage").attr("class", "errorMessage");
                $("#errorMessage").attr("style", "display:block;");
            }
        });
    }, cancelFn: function (orderId) {
        $("#nextRefillDateFId_" + orderId).val($("#oldRefillDate" + orderId).text());
        $("#oldRefillDate" + orderId).removeAttr("style");
        $("#nextRefillDate" + orderId).attr("style", "display:none;");
        $("#btnUpdate" + orderId).attr("style", "display:none;position: relative;top:20px;");
        $("#btnEdit" + orderId).removeAttr("style");
    }, displayBtnFn: function (orderId, fmtDate) {
        $("#nextRefill").val(fmtDate);
        $("#refillreminderhidden").val(orderId);
        $("#orderIdhiden").val(orderId);
//        $("#nextRefillDate" + orderId).removeAttr("style");
//        $("#oldRefillDate" + orderId).attr("style", "display:none;");
//        $("#btnUpdate" + orderId).removeAttr("style");
//        $("#btnUpdate" + orderId).attr("style", "position: relative;top:20px;");
//        $("#btnEdit" + orderId).attr("style", "display:none;");
        $("#refillmodel").modal('show');
    }, validateOrderDetailsField: function () {
        if ($("#mobileNoFid").val().length == 0) {
            $("#errorMessage").text("Enter patient phone number.");
            $("#errorMessage").attr("class", "errorMessage");
            $("#errorMessage").attr("style", "display:block;");
            return  false;
        }
        if ($("#mobileNoFid").val().length < 10) {
            $("#errorMessage").text("Enter 10 digit phone number.");
            $("#errorMessage").attr("class", "errorMessage");
            $("#errorMessage").attr("style", "display:block;");
            return  false;
        }
        return true;
    }, hideDetailFn: function () {
        $("#refillmodel").modal('hide');
//       $("#oldRefillDate" + orderId).removeAttr("style");
//        $("#nextRefillDate" + orderId).attr("style", "display:none;");
//        $("#btnUpdate" + orderId).attr("style", "display:none;position: relative;top:20px;");
//        $("#btnEdit" + orderId).removeAttr("style");
    }, sendRefillMsgs: function () {
        var orderId = $("#refillreminderhidden").val();
        var json = {"orderId": orderId};
        $.ajax({
            url: window.support.appUrl + "/sendRefillReminderMsg",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                if (response.statuscode == 200) {
                    $("#errorMessage").text(data.errorMessage);
                    $("#errorMessage").attr("class", "message");
                    $("#errorMessage").attr("style", "display:block;");
                    window.support.hideDetailFn();
                } else {
                    $("#errorMessage").text(data.errorMessage);
                    $("#errorMessage").attr("class", "errorMessage");
                    $("#errorMessage").attr("style", "display:block;");
                }

            },
            error: function (e) {
                $("#errorMessage").text("Internal server error.");
                $("#errorMessage").attr("class", "errorMessage");
                $("#errorMessage").attr("style", "display:block;");
            }
        });
    }, populateAppUrl: function (appUrl) {
        window.support.appUrl = appUrl;
    }
}
