/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.transferRequest = {
    appUrl: null,
    saveDrugDetail: function (drugId) {
        //alert("drug "+drugId);
//      var  ndc=$("#ndc"+drugId).val();
//      var  inStock=$("#inStock"+drugId).val();
//      var  generic=$("#generic"+drugId).val();
//      var  brand=$("#brand"+drugId).val();
//      var  strength=$("#strength"+drugId).val();
//      var  formDesc=$("#formDesc"+drugId).val();
//      var  packingDesc=$("#packingDesc"+drugId).val();
//      var  defQty=$("#defQty"+drugId).val();
//      var  packageSizeValues=$("#packageSizeValues"+drugId).val();
//      var  basePrice=$("#basePrice"+drugId).val();
//      var  marginPercent=$("#marginPercent"+drugId).val();
//      var  additionalFee=$("#additionalFee"+drugId).val();

        var ndc = $("#ndcFld").val();
        var gcn = $("#gcnFld").val();
        var gpi = $("#gpiFld").val();
        var thereupatic = $("#therapeuticFld").val();
        var inStock = $("#inStockFld").val();
        var generic = $("#genericFld").val();
        var brand = $("#drugFld").val();
        var strength = $("#strengthFld").val();
        var formDesc = $("#formFld").val();
        var packingDesc = $("#packingFld").val();
        var defQty = $("#qtyFld").val();
        var packageSizeValues = $("#packageSizeValuesFld").val();
        var basePrice = $("#priceFld").val();
        var marginPercent = $("#marginFld").val();
        var additionalFee = $("#addFeeFld").val();

        var json = {"ndc": ndc, "gcn": gcn, "gpi": gpi, "thereupatic": thereupatic, "inStock": inStock, "generic": generic, "brand": brand, "strength": strength, "formDesc": formDesc, "packingDesc": packingDesc,
            "defQty": defQty, "packageSizeValues": packageSizeValues, "basePrice": basePrice, "marginPercent": marginPercent,
            "additionalFee": additionalFee};
        $.ajax(
                {
                    url: window.transferRequest.appUrl + "/drugBrand/updatedrugdetail/" + ndc,
                    type: "POST",
                    dataType: 'text',
                    contentType: 'application/json',
                    data: JSON.stringify(json),
                    success: function (data)
                    {
                        //alert(""+data);
                        alert("Drug detail has been saved successfully.")
                        $("#drugDataForm").submit();

                    },
                    error: function (xhr, status, error)
                    {
                        alert(xhr.responseText);
                    }
                });
    },
    loadDrugDertail: function (drugId, div,
            drugGCN, drugGPI, inStock, genericName,
            therapy, brandName, strength, formDesc, packingDesc,
            defQty, packageSizeValues, basePrice, marginPercent, additionalFee)
    {
        $("#" + div).show();
        $("#ndcFld").val(drugId);
        $("#gpiFld").val(drugGPI);
        $("#gcnFld").val(drugGCN);
        $("#inStockFld").val(inStock);
        $("#genericFld").val(genericName);
        $("#therapeuticFld").val(therapy);
        $("#drugFld").val(brandName);
        $("#strengthFld").val(strength);
        $("#qtyFld").val(defQty);
        $("#formFld").val(formDesc);
        $("#packingFld").val(packingDesc);
        $("#packageSizeValuesFld").val(packageSizeValues);
        $("#priceFld").val(basePrice);
        $("#marginFld").val(marginPercent);
        $("#addFeeFld").val(additionalFee);
        //alert("A"+drugCategory.drugNDC);
        //alert("ID "+drugId);
//        var json={"ndc":drugId};
//         $.ajax(
//                  {
//                      url: "/drugBrand/retrieveDrug/"+drugId,
//                      type: "POST",
//                      dataType: 'text',
//                      contentType: 'application/json',
//                      data: JSON.stringify(json),
//                      success: function (data)
//
//                      {
//                          $("#"+div).show();
//                          alert("Success");
//                          alert("VAL "+data.drugNDC);
//                          $("#ndcFld").val(data.drugNDC);
//                          //alert("Drug detail has been deleted successfully.")
//                          
//                          //$("#drugdatasubmit").submit();
//
//                      },
//                      error: function(xhr, status, error) 
//                       {
//                          alert(xhr.responseText);
//                       }
//                  });

    },
    deleteDrugDetail: function (drugId) {
        //alert("drug "+drugId);
        if (confirm("Want to delete?"))
        {
            var ndc = drugId;//$("#ndc"+drugId).val();
            var json = {"ndc": ndc};
            $.ajax(
                    {
                        url: window.transferRequest.appUrl + "/drugBrand/deletedrugdetail/" + drugId,
                        type: "POST",
                        dataType: 'text',
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        success: function (data)

                        {
                            //alert(""+data);
                            alert("Drug detail has been deleted successfully.")
                            $("#drugdatasubmit").submit();

                        },
                        error: function (xhr, status, error)
                        {
                            alert(xhr.responseText);
                        }
                    });
        }
    },
    ///////////////////////////////////////////////////////////////////////////////////
    uploadDocumentHandler: function (drugId) {
        //alert("drug "+drugId);
        $("#drugDetailId").val(drugId);
    },
    //////////////////////////////////////////////////////////////////////////////////

    initTransferRequest: function () {
        // Initialize Drug Category auto compelte 
        var availableTagsTransferRequestSearch = [];
        $("#drugNameId").autocomplete({
            source: availableTagsTransferRequestSearch,
            minLength: 0,
            dataType: 'json'
        });
    },
    lookUpDrugName: function () {

        var drugNameId = $("#drugNameId").val();
        var json = {"name": drugNameId};
        var autoCompleteAllValuesCus = new Array();
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugBrandByName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length == 0) {
                    $('#drugNameId').val("");
                    alert('No Drug Category Found');
                } else {

                    for (var i = 0; i < responses.length; i++) {
                        autoCompleteAllValuesCus[i] = {
                            'value': responses[i].brandNameId,
                            'label': responses[i].brandName
                        };
                    }
                    $('#drugNameId').autocomplete('option', 'source', autoCompleteAllValuesCus);
                    $("#drugNameId").autocomplete("search", "");

                    $("#drugNameId").autocomplete({
                        select: function (event, ui) {
                            event.preventDefault();
                            $('#searchDrugNameId').val(ui.item.label);
                            $('#drugNameId').val(ui.item.label);
                            $('#drugNameDbId').val(ui.item.value);
                        }
                    });
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    //////////////////////////////////////////////////////////////////////////////
    lookUpDrugName2: function () {

        var drugNameId = $("#drugNameId").val();
        var length = $("#drugNameId").val().length;
        if (length == 1 || length == 2 || length == 3 || length == 4 || length == 5 || length == 10)
        {
            var json = {"name": drugNameId};
            var autoCompleteAllValuesCus = new Array();
            $.ajax({
                url: window.transferRequest.appUrl + "/rxTransfer/populateDrugByNameSearch",
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(json),
                success: function (data) {
                    var responses = eval(data);
                    if (responses.length == 0) {
                        $('#drugNameId').val("");
                        alert('No Drug Category Found');
                    } else {

                        for (var i = 0; i < responses.length; i++)
                        {
                            autoCompleteAllValuesCus[i] = responses[i]

                        }
                        $("#drugNameId").autocomplete({
                            source: autoCompleteAllValuesCus
                        });

                    }
                },
                error: function (e) {
                    alert('Error while request...' + e);
                }
            });
        }
    },
    /////////////////////////////////////////////////////////////////////
    drugLookupHandler: function (fld, url) {

        //alert("A");
        var drugNameId = $("#" + fld).val();
        var length = $("#" + fld).val().length;
        if (length > 0)
        {
            if ($("#gcnSearch") != null)
            {
                $("#gcnSearch").val(0);
            }
            this.lookUpObject(fld, url);
        } else
        {
            if ($("#gcnSearch") != null)
            {
                $("#gcnSearch").val(1);
            }
        }
    },
    ////////////////////////////////////////////////////////////////////
    lookUpObject: function (fld, url) {

        //alert("A");
        var drugNameId = $("#" + fld).val();
        var length = $("#" + fld).val().length;
        if (length > 0)
        {
            if (length == 1)
            {
                $("#drugNotFoundSpan").hide();
                $("#drugGCN").val("");
                $("#drugTypeId").val("");
                $("#drugStrength").val("");
                $("#quantity").val("");
                $("#popUpException").text("");
                $("#popUpException").hide();
                $("#responseMsg").text("");
                $("#drugNameId").val("");
                $("#estimateDrugPriceTbl").dataTable().fnDestroy();
//                return;
            }
            var json = {"name": drugNameId};
            var autoCompleteAllValuesCus = new Array();
            $.ajax({
                url: url,
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                data: JSON.stringify(json),
                success: function (data) {
                    var responses = eval(data);
                    if (responses.length == 0) {
//                        $("#" + fld).val("");
                        $("#drugNotFoundSpan").html("Drug not found.");
                        $("#drugNotFoundSpan").show();
                        $("#" + fld).focus();
//                        alert('No Record Found');

                    } else if (responses.length == 1 && responses[0] == 'No drug found.')
                    {
//                        $("#" + fld).val("");
                        $("#drugNotFoundSpan").html("Drug not found.");
                        $("#drugNotFoundSpan").show();
                        $("#" + fld).focus();
                    } else {
                        $("#drugNotFoundSpan").hide();
                        for (var i = 0; i < responses.length; i++)
                        {
                            autoCompleteAllValuesCus[i] = responses[i];

                        }
                        $("#" + fld).autocomplete({
                            source: autoCompleteAllValuesCus
                        }).keydown(function (e) {
                            if (e.which === 13) {
                                $(".ui-menu-item").hide();

                            }
                        });

                    }
                },
                error: function (e) {
                    alert('Error while request...' + e);
                }
            });
        }
    },
    ////////////////////////////////////////////////////////////////////
    lookUpObjectMultiRx: function (fld, url) {

        //alert("A");
        var drugNameId = $("#" + fld).val();
        var length = $("#" + fld).val().length;
        if (length > 0)
        {
            if (length == 1)
            {
                $("#drugMultiErrorMsg").hide();
                $("#drugMultiSuccessMsg").hide();
                $("#multiDrugGcnText").val("");
                $("#multiDrugPriceUpdateTable").dataTable().fnDestroy();
//                return;
            }
            var json = {"name": drugNameId};
            var autoCompleteAllValuesCus = new Array();
            $.ajax({
                url: url,
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                data: JSON.stringify(json),
                success: function (data) {
                    var responses = eval(data);
                    if (responses.length == 0) {
//                        $("#" + fld).val("");
                        $("#drugMultiErrorMsg").html("Drug not found.");
                        $("#drugMultiErrorMsg").show();
                        $("#" + fld).focus();
//                        alert('No Record Found');

                    } else if (responses.length == 1 && responses[0] == 'No drug found.')
                    {
//                        $("#" + fld).val("");
                        $("#drugMultiErrorMsg").html("Drug not found.");
                        $("#drugMultiErrorMsg").show();
                        $("#" + fld).focus();
                    } else {
                        $("#drugMultiErrorMsg").hide();
                        $("#drugMultiSuccessMsg").hide();
                        for (var i = 0; i < responses.length; i++)
                        {
                            autoCompleteAllValuesCus[i] = responses[i];

                        }
                        $("#" + fld).autocomplete({
                            source: autoCompleteAllValuesCus
                        }).keydown(function (e) {
                            if (e.which === 13) {
                                $(".ui-menu-item").hide();

                            }
                        });

                    }
                },
                error: function (e) {
                    $("#drugMultiErrorMsg").html('Error while request...' + e);
                }
            });
        }
    },
    ////////////////////////////////////////////////////////////////////

    lookUpDrugTypeByBrandName: function () {

        var drugNameDbId = $("#drugNameId").val();
        var json = {"name": drugNameDbId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugByDrugBrandName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Type</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].type + '">' + responses[i].type + '</option>';
                    }
                    $('#drugTypeId').html(html);
                    $("#drugTypeId").trigger("change");
                }
            },
            error: function (e) {
                alert('No such drug exists');
            }
        });
    },
    ////////////////////////////////////////////////////////////
    drugTypeBlurHandler: function ()
    {
        var gcnSearchVal = 0;
        if ($("#gcnSearch") != null)
        {
            gcnSearchVal = $("#gcnSearch").val();
        }
//       console.log("gcn search in type blur function "+gcnSearchVal);
        if (gcnSearchVal == 0)
        {
//           console.log("calling drug type blur function now");
            this.lookUpDrugStrengthByBrandNameAndType();
        } else
        {
//           console.log("NOT Called drug type blur function");
        }
    },
    ///////////////////////////////////////////////////////////
    lookUpDrugStrengthByBrandNameAndType: function () {

        var drugNameDbId = $("#referenceBrand").val();
        var drugType = $("#drugTypeId").val();
        var json = {"name": drugNameDbId, "drugType": drugType};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugByDrugTypeAndName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Strength</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].strength + '">' + responses[i].strength + '</option>';
                    }

                    $('#drugStrength').html(html);
                    $("#drugStrength").trigger("blur");
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    ///////////////////////////////////////////////////////////////////
    lookUpDrugStrengthByBrandNameAndTypeDetail: function (idFld, strengthFld, typeFld) {

        var drugNameDbId = $("#" + idFld).val();
        var drugType = $("#" + typeFld).val();
        var json = {"name": drugNameDbId, "drugType": drugType};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugByDrugTypeAndName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Strength</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].strength + '">' + responses[i].strength + '</option>';
                    }

                    $('#' + strengthFld).html(html);
                    $("#" + strengthFld).trigger("blur");
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    ///////////////////////////////////////////////////////////////////
    lookUpDrugDetailByBrandNameAndType: function () {

        var drugNameDbId = $("#drugNameId").val();
        var drugType = $("#drugTypeId").val();
        var drugStrength = $("#drugStrength").val();
        var qty = $("#drugQtyFld").val();
        var patientProfileId = $("#patientId").val();
        var json = {"name": drugNameDbId, "drugType": drugType, "drugStrength": drugStrength, "qty": qty, "patientProfileId": patientProfileId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugDetailByDrugTypeAndName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                $('#acqCostFld').val(data.drugCost.toFixed(2));
                $("#estimatedDrugPrice").val(data.totalPrice);
                $("#estimatedDrugPriceH").text("Estimated Drug Price $ " + data.totalPrice);
                $("#sellingPriceFld").val(data.totalPrice);

                var calculatedProfitFld = $("#profitMarginCol").val();
                var profitShareFld = $("#profitShareFld").val();
                var mfrCost = $("#mfrCostFld").val();

                var handlingFee = $("#handlingFeeFld").val().split('$');
                var handlingF = handlingFee[1];
                if (parseFloat(profitShareFld) > parseFloat(calculatedProfitFld)) {
                    alert("Profit share must be less than calculated profit");
                    return;
                }
                if (mfrCost !== "" && mfrCost >= 0 && data.totalPrice >= 0 && handlingF >= 0) {
                    var totalCost = data.totalPrice - parseFloat(mfrCost) - parseFloat(profitShareFld);
                    if (totalCost < 0) {
                        totalCost = 0;
                    }
                    var totalDrugCost = (parseFloat(totalCost) + parseFloat(handlingF));

                    $("#finalPaymentFeeFld").val(totalDrugCost);
                } else {
                    $("#finalPaymentFeeFld").val(data.totalPrice);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    //////////////////////////////////////////////////////////////////
    lookUpDrugCost: function () {
        window.pharmacyNotification.eliminateCurrencySignFromNumricFlds();
        var drugNameDbId = $("#drugNameId").val();
        var drugType = $("#drugTypeId").val();
        var drugStrength = $("#drugStrength").val();
        var qty = $("#drugQtyFld").val();
        var patientProfileId = $("#patientId").val();
        var orderId = $("#orderId").val();
        var json = {"name": drugNameDbId, "drugType": drugType, "drugStrength": drugStrength, "qty": qty, "patientProfileId": patientProfileId, "orderId": orderId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugDetailByDrugTypeAndName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                //alert("points "+data.pointsFromShare+" cost "+data.costFromPoints);
                if (data.drugCost != null) {
                    $('#acqCostFld').val(data.drugCost.toFixed(2));
                } else {
                    $('#acqCostFld').val("0.0");
                }

                $("#estimatedDrugPrice").val(0);//data.totalPrice);
                //$("#estimatedDrugPriceH").text("Estimated Drug Price $ " + data.totalPrice);
                $("#sellingPriceFld").val(0);//data.totalPrice);
                $("#profitMarginCol").val(data.profitValue);
                $("#profitSharePoints").val(data.pointsFromShare);
                $("#totalRedeemPointsId").val(data.pointsFromShare);
                //alert(" "+$("#radio-a").is(':checked'))
                if ($("#radio-a").is(':checked') == false)
                {
                    $("#redeemPointsCostFld").val(data.costFromPoints);
                }
                window.transferRequest.calculatePayment();
                //window.pharmacyNotification.appendCurrencySignFromNumricFlds();
                //alert("Points from share "+data.pointsFromShare);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    //////////////////////////////////////////////////////////////////
    lookUpDrugCostDetail: function (idFld, brandFld, strengthFld, typeFld) {
        window.pharmacyNotification.eliminateCurrencySignFromNumricFlds();
        var drugNameDbId = $("#" + brandFld).val();//+ idFld).val();
        var drugType = $("#" + typeFld).val();
        var drugStrength = $("#" + strengthFld).val();
        var qty = $("#drugQtyFld").val();
        var patientProfileId = $("#patientId").val();
        var orderId = $("#orderId").val();
        var json = {"name": drugNameDbId, "drugType": drugType, "drugStrength": drugStrength, "qty": qty, "patientProfileId": patientProfileId, "orderId": orderId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugDetailByDrugTypeAndName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                //alert("points "+data.pointsFromShare+" cost "+data.costFromPoints);
                if (data.drugCost != null) {
                    $('#acqCostFld').val(data.drugCost.toFixed(2));
                } else {
                    $('#acqCostFld').val("0.0");
                }

                $("#estimatedDrugPrice").val(0);//data.totalPrice);
                //$("#estimatedDrugPriceH").text("Estimated Drug Price $ " + data.totalPrice);
                $("#sellingPriceFld").val(0);//data.totalPrice);
                $("#profitMarginCol").val(data.profitValue);
                $("#profitSharePoints").val(data.pointsFromShare);
                $("#totalRedeemPointsId").val(data.pointsFromShare);
                //alert(" "+$("#radio-a").is(':checked'))
                if ($("#radio-a").is(':checked') == false)
                {
                    $("#redeemPointsCostFld").val(data.costFromPoints);
                }
                window.transferRequest.calculatePayment();
                //window.pharmacyNotification.appendCurrencySignFromNumricFlds();
                //alert("Points from share "+data.pointsFromShare);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    //////////////////////////////////////////////////////////////////
    lookUpProfitCalculation: function () {

        var profit = $("#profitMarginCol").val();

        var json = {"profit": profit};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/calculatePointsFromProfit",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                //alert("points "+data.pointsFromShare+" cost "+data.costFromPoints);

                $("#profitSharePoints").val(data.pointsFromShare);
                $("#totalRedeemPointsId").val(data.pointsFromShare);
                if ($("#radio-a").is(':checked'))
                {
                    $("#redeemPointsCostFld").val("$0.0");
                } else
                {
                    $("#redeemPointsCostFld").val(data.costFromPoints);
                }
                //alert("Here");
                window.transferRequest.calculatePayment();
                //alert("Here2");
                //alert("Points from share "+data.pointsFromShare);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    //////////////////////////////////////////////////////////////////

    calculatePayment: function ()
    {
//        alert("Aaaaaaa");
        window.pharmacyNotification.eliminateCurrencySignFromNumricFlds();
        var oop = parseFloat($("#ptCopayFld").val());
        console.log("ptCopayFld# " + oop);
        //alert("OOP "+oop);
        var redeemedCost = parseFloat($("#redeemPointsCostFld").val());
        console.log("redeemedCost# " + redeemedCost);
        if ($("#radio-a").is(':checked'))
        {
            redeemedCost = 0;
        }
        if (redeemedCost > oop)
        {
            oop = redeemedCost;
        }
        //alert("Redeemed "+redeemedCost);
//        var deliveryFee = parseFloat($("#handlingFeeFld").val());
        //alert("Delivery fee "+deliveryFee);
        var finalPayment = oop - redeemedCost;// + deliveryFee;
        console.log("finalPayment=(oop - redeemedCost)# " + finalPayment);
        //alert("Final payment "+finalPayment);
        $("#finalPaymentFeeFld").val(finalPayment.toFixed(2));
        window.pharmacyNotification.appendCurrencySignFromNumricFlds();

    },
    //////////////////////////////////////////////////////////////////
    updateDrugInfoInOrder: function (orderId) {
        var flag = confirm("Do you want to update drug for this order?");
        if (flag == false)
        {
            return;
        }
        var drugNameDbId = $("#drugNameId").val();
        var drugType = $("#drugTypeId").val();
        var drugStrength = $("#drugStrength").val();
        var drugQty = $("#drugQtyFld").val();
        var json = {"name": drugNameDbId, "drugType": drugType, "drugStrength": drugStrength, "drugQty": drugQty, "oid": orderId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/updateDrugInfoInOrder",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                //alert(" "+data);
                $("#drugUpdateLnk").click();
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    /////////////////////////////////////////////////////////////////////////////
    lookUpDrugType: function () {

        var drugNameDbId = $("#drugNameDbId").val();
        var json = {"id": drugNameDbId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugByDrugBrandId",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Type</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].type + '">' + responses[i].type + '</option>';
                    }
                    $('#drugTypeId').html(html);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    lookUpDrugStrength: function () {

        var drugNameDbId = $("#drugNameDbId").val();
        var drugType = $("#drugTypeId").val();
        var json = {"id": drugNameDbId, "name": drugType};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugByDrugType",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Strength</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].strength + '">' + responses[i].strength + '</option>';
                    }
                    $("#drugStrength").html(html);
                    $("#drugStrength").trigger("change");
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    defaultQtyByStrength: function (strength) {
        $("#defaultQty").val(0).attr("selected", "selected");
        $("#defaultQty").trigger("change");
        var drugNameDbId = $("#drugNameDbId").val();
        var json = {"drugBasicId": drugNameDbId, "strength": strength};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugDefaultQtyByStrength",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length > 0) {
                    var html = "";
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].defaultQty + '">' + responses[i].defaultQty + '</option>';
                        $("#ndc").val(responses[i].ndc);
                    }
                    $("#defaultQty").html(html);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    calculateRXAcqCost: function (qty) {
        var drugNameDbId = $("#drugNameId").val();
        var drugType = $("#drugTypeId").val();
        var drugStrength = $("#drugStrength").val();
        var json = {"name": drugNameDbId, "drugType": drugType, "strength": drugStrength};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugBasicPrice",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Strength</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        $("#acqCostFld").val(responses[i].basePrice * parseInt(qty));
                    }
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }, populateDrugByDrugGenericName: function () {
        var drugNameId = $("#genericName").val();
        var length = $("#genericName").val().length;
        if (length == 1 || length == 2 || length == 3 || length == 4 || length == 5 || length == 10)
        {
            var json = {"name": drugNameId};
            var autoCompleteAllValuesCus = new Array();
            $.ajax({
                url: window.transferRequest.appUrl + "/rxTransfer/populateDrugByDrugGenericName",
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(json),
                success: function (data) {
                    var responses = eval(data);
                    if (responses.length == 0) {
                        $('#genericName').val("");
                        alert('No Drug GenericName Found');
                    } else {

                        for (var i = 0; i < responses.length; i++)
                        {
                            autoCompleteAllValuesCus[i] = responses[i];

                        }
                        $("#genericName").autocomplete({
                            source: autoCompleteAllValuesCus
                        });

                    }
                },
                error: function (e) {
                    alert('Error while request...' + e);
                }
            });
        }

    },
    populateUniqueObject: function (fid, url, selectFid) {

        var drugNameDbId = $("#" + fid).val();
        if (drugNameDbId.length < 2) {
            return;
        }
        var json = {"name": drugNameDbId};
        $.ajax({
            url: url,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                if (responses.length > 0) {
                    for (var i = 0; i < responses.length; i++) {
                        $('#drugDetailId').val(responses[i].drugDetailId);
                        $('#' + selectFid).val(responses[i].drugGCN);
                        //$('#drugStrength').html('<option value="' + responses[i].strength + '">' + responses[i].strength + '</option>');
                        //$('#qtyFId').val(responses[i].defQty);
                        //$('#quantity').val(responses[i].defQty);
                        break;
                    }
                }
                $('#' + selectFid).trigger("blur");
            },
            error: function (e) {
                alert('No such drug exists');
            }
        });
    },
    populateQtyByStrengthAndDrugName: function (selectFid) {

        var drugStrengthId = $("#drugStrength").val();
        var referenceBrand = $("#referenceBrand").val();
        var drugType = $("#drugTypeId").val();
        if (drugStrengthId.length == 0 && referenceBrand.length == 0) {
            return;
        }
        var json = {"drugStrength": drugStrengthId, "drugName": referenceBrand, "drugType": drugType};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/populateQtyByStrengthAndDrugName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length > 0) {
                    for (var i = 0; i < responses.length; i++) {
                        $('#' + selectFid).val(responses[i].defQty);
                        $("#drugGCN").val(responses[i].drugGCN);
                        break;
                    }
                    if (referenceBrand.length > 0 && drugStrengthId.length > 0 && $("#drugTypeId").val().length > 0) {
                        //$('#drugGCN').val("");
                    }
                    $('#' + selectFid).trigger("blur");
                }
            },
            error: function (e) {
                alert('No such drug exists');
            }
        });
    },
    ////////////////////////////////////////////////////////////////
    populateQtyByStrengthAndDrugNameDetail: function (selectFid, brandFld, strengthFld, typeFld) {

        var drugStrengthId = $("#" + strengthFld).val();
        var referenceBrand = $("#" + brandFld).val();
        var drugType = $("#" + typeFld).val();
        if (drugStrengthId.length == 0 && referenceBrand.length == 0) {
            return;
        }
        var json = {"drugStrength": drugStrengthId, "drugName": referenceBrand, "drugType": drugType};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/populateQtyByStrengthAndDrugName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length > 0) {
                    for (var i = 0; i < responses.length; i++) {
                        $('#' + selectFid).val(responses[i].defQty);
                        $("#drugRxExpire").val(responses[i].monthRxExpiration);
                        break;
                    }
                    if (referenceBrand.length > 0 && drugStrengthId.length > 0 && $("#" + typeFld).val().length > 0
                            && $('#drugGCN') != null) {
//                        alert("b");
                        $('#drugGCN').val("");
                    }
//                    alert("c");
                    $('#' + selectFid).trigger("blur");
                }
            },
            error: function (e) {
                alert('No such drug exists');
            }
        });
    },
    //////////////////////////////////////////////////////////////
    populateDrugDetailByGCN: function (gcnVal) {
        if (gcnVal.length === 0) {
            return;
        }
        var json = {"drugGCN": gcnVal};
        var autoCompleteAllDrugGCN = new Array();
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/populateDrugDetailByGCN",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length == 0) {
                    $('#drugGCN').val("");
                    alert('No Drug GCN Found');
                } else {

                    for (var i = 0; i < responses.length; i++)
                    {
                        autoCompleteAllDrugGCN[i] = responses[i];

                    }

                    $("#drugGCN").autocomplete({
                        source: autoCompleteAllDrugGCN
                    }).keydown(function (e) {
                        if (e.which === 13) {
                            $(".ui-menu-item").hide();

                        }
                    });

                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    /////////////////////////////////////////////////////////////////////////
    populateMultiDrugDetailByGCN: function (gcnVal) {
        if (gcnVal.length === 0) {
            return;
        }
        var json = {"drugGCN": gcnVal};
        var autoCompleteAllDrugGCN = new Array();
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/populateDrugDetailByMultiGCN",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length == 0) {
//                    $('#multiDrugGcnText').val("");
                    $("#drugMultiErrorMsg").text("No record found");
                    $("#drugMultiSuccessMsg").hide();
                    $("#drugMultiErrorMsg").show();
                } else {
                    $("#drugMultiErrorMsg").text("");
                    $("#drugMultiSuccessMsg").hide();
                    $("#drugMultiErrorMsg").hide();
                    for (var i = 0; i < responses.length; i++)
                    {
                        autoCompleteAllDrugGCN[i] = responses[i];

                    }

                    $("#multiDrugGcnText").autocomplete({
                        source: autoCompleteAllDrugGCN
                    }).keydown(function (e) {
                        if (e.which === 13) {
                            $(".ui-menu-item").hide();

                        }
                    });

                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    ////////////////////////////////////////////////////////////////////////
    populateDrugQtyByGCN: function () {
        var gcnVal = $("#drugGCN").val();
        if (gcnVal.length === 0) {
            return;
        }
        var json = {"drugGCN": gcnVal};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/populateDrugQtyByGCN",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (resp) {
                $('#drugDetailId').val(resp.drugDetailId);
                $('#qtyFId').val(resp.defQty);
                $('#quantity').val(resp.defQty);
            },
            error: function (e) {
                alert('No such drug exists');
            }
        });
    }, lookUpDrugFormByBrandName: function () {

        var drugNameDbId = $("#drugNameId").val();
        var json = {"name": drugNameDbId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugFormByDrugBrandName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Type</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].type + '">' + responses[i].type + '</option>';
                    }
                    $('#drugTypeId').html(html);
                    $("#drugTypeId").trigger("blur");
                }
                $("#drugStrength").trigger("blur");
            },
            error: function (e) {
                alert('No such drug exists');
            }
        });
    },
    lookUpDrugFormByBrandNameDetail: function (idFld, strengthFld, typeFld) {
       // console.log(window.transferRequest.appUrl + "/rxTransfer/searchDrugFormByDrugBrandName");
        var drugNameDbId = $("#" + idFld).val();
        var json = {"name": drugNameDbId};
        $.ajax({
            url: window.transferRequest.appUrl + "/rxTransfer/searchDrugFormByDrugBrandName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
                html += '<option value="">Select Drug Type</option>';
                if (responses.length > 0) {
                    var html = '';
                    for (var i = 0; i < responses.length; i++) {
                        html += '<option value="' + responses[i].type + '">' + responses[i].type + '</option>';
                    }
                    $('#' + typeFld).html(html);
                    $("#" + typeFld).trigger("blur");
                }
                $("#" + strengthFld).trigger("blur");
            },
            error: function (e) {
                alert('No such drug exists');
            }
        });
    }, postOrderToIOMNI: function () {
        var total = $("#totalPayment").val();
        if (total > 0) {
            total = total.toLocaleString('en-US', {maximumFractionDigits: 2});
        } else {
            total = "0.00";
        }
        var json = {"orderId": $("#orderId").val(), "totalPayment": total};
        $.ajax({
            url: window.transferRequest.appUrl + "/PharmacyPortal/postOrderToIOMNI",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                $("#postAnswerMessageDiv").show();

            },
            error: function (e) {
                $("#errorMessagePharReg").show();
                $("#errorMessagePharReg").html('' + e.responseText);
            }
        });
    }, populateAppUrl: function (appUrl) {
        window.transferRequest.appUrl = appUrl;
    }
}
