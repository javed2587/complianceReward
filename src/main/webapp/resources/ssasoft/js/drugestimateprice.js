/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.drugestimateprice = {
    populateQty: function (fid) {
        $("#" + fid).val($("#qtyFId").val());
    },
    initializeDataTable: function (maxHeight) {
        $('#estimateDrugPriceTbl').dataTable({
            "sPaginationType": "full_numbers",
            "bLengthChange": false, //thought this line could hide the LengthMenu
            "searching": false,
            "bPaginate": false,
            "bInfo": false,
            "bSort": false,
            "scrollY": "50px",
            "scrollX": true
        });
        $("#estimateDrugPriceTbl").css({"width": "100%"});
    },
    resetField: function (currentUserRole) {
        $("#drugGCN").val("");
        $("#referenceBrand").val("");
        $("#drugTypeId").val("");
        $("#drugStrength").val("");
        $("#quantity").val("");
        $(".errorMessage").text("");
        $("#responseMsg").text("");
        $("#drugNameId").val("");
        $("#searchBy").text("");
        if (currentUserRole == "user") {
            $("#gpI").text("");
            $("#TherapyId").text("");
            $("#ptInfo").text("");
            $("#drugImg").attr("src", "#");
            $("#acqCost").text("");
            $("#drug_total_amout").text("");
            $("#genericNameId").text("Generic");
        } else {
            $('.disabled_write .form-control').attr('disabled', 'disabled');
            $('.disabled_write .form-control').val("");
            $('#btnSavechanges').attr('disabled', 'disabled');
            $('.btn_upload').attr('disabled', 'disabled');
            $("#drugImgUrl").attr("src", "#");
            $("#drugImgLink").attr("href", "#");
            $("#priceBase").val("");
            $('#priceBase').attr('disabled', 'disabled');
            $("#acqPrice").val("");
            $("#margin").val("");
            $('#margin').attr('disabled', 'disabled');
            $("#marginFId").val("");
            $("#baseOverhead").val("");
            $('#baseOverhead').attr('disabled', 'disabled');
            $("#mrktSurcharge").val("");
            $('#mrktSurcharge').attr('disabled', 'disabled');
            $("#final").val("");
            $("#final_total").text("");
            $("#BI").val("GENERIC");
            $("#inStock").val("Y");
            $("#rxExpires").val("Y");
            $("#reqHandDelivery").val("Y");
            $("#drugBrandModal").modal("hide");
            $("#lastUpdatedDate").text("");
            //$("#modal-body").removeAttr("style");
            $("#headingDrugPrice").text(" ");
            $("#headingDrugPrice").attr("class", "heading_drugprice");
        }

        $("#estimateDrugPriceTbl").dataTable().fnDestroy();
        window.drugestimateprice.initializeDataTable("0px");
    },
    populateDrugDosageAndStrength: function () {
        var drugBrand = $("#referenceBrand").val();
        var drugGCN = $("#drugGCN").val();
        $(".ui-menu-item").hide();
        if (drugBrand.length > 0) {
            $('#referenceBrand').autocomplete("destroy");
            if (drugBrand.indexOf("{") != -1) {
                var drugBrands = drugBrand.split('{');
                $("#drugNameId").val(drugBrands[0]);
                window.transferRequest.lookUpDrugFormByBrandName();
            } else {
                var drugBrands = drugBrand.split('*');
                $("#drugNameId").val(drugBrands[0]);
                window.transferRequest.lookUpDrugFormByBrandName();
            }
        } else if (drugGCN.length > 0) {
            $('#drugGCN').autocomplete("destroy");
            window.drugestimateprice.populateDrugQtyByGCN();
        }

    },
    ////////////////////////////////////////////
    populateDrugDosageAndStrengthDetail: function (fld, idFld, strengthFld, typeFld) {
        var drugBrand = $("#" + fld).val();
        var drugGCN = $("#drugGCN").val();
        $(".ui-menu-item").hide();
        if (drugBrand.length > 0) {
            $('#' + fld).autocomplete("destroy");
            $("#drugStrengthDetail").css("border", "1px solid #0066ff;");
            $("#drugStrengthDetail").css("color","black;");
            $("#drugTypeIdDetail").css("border", "1px solid #0066ff;");
            $("#drugTypeIdDetail").css("color","black;");
            window.transferRequest.lookUpDrugFormByBrandNameDetail(fld, strengthFld, typeFld);
//            if (drugBrand.indexOf("{") != -1) {
//                var drugBrands = drugBrand.split('{');
//                $("#" + idFld).val(drugBrands[0]);
//                window.transferRequest.lookUpDrugFormByBrandNameDetail(idFld, strengthFld, typeFld);
//            } else {
//                var drugBrands = drugBrand.split('*');
//                $("#" + idFld).val(drugBrands[0]);
//                window.transferRequest.lookUpDrugFormByBrandNameDetail(idFld, strengthFld, typeFld);
//            }
        } else if (drugGCN.length > 0) {
            $('#drugGCN').autocomplete("destroy");
            window.transferRequest.populateDrugQtyByGCN();
        }

    },
    ///////////////////////////////////////////
    populateDrugDetailCalculation: function (element, qty, currentUserRole) {
        if (currentUserRole == "user") {
            $("#drugGCN").val(element.drugGCN);
            $("#quantity").text(qty);
            $("#gpI").text(element.drugGPI);
            $("#TherapyId").text(element.therapy);
            $("#ptInfo").text(element.pdf);
            $("#drugImg").attr("src", element.image);
            window.pharmacyNotification.addCommasToValue(element.acqPrice, "acqPrice", "$");
            window.pharmacyNotification.addCommasToValue(element.estimatedCashPrice, "drug_total_amout", "$");
//            $("#drug_total_amout").text("$ " + element.estimatedCashPrice);
            $("#genericNameId").text(element.genericName);
        } else {
            $("#drugGCN").val(element.drugGCN);
            $("#gcnTxtId").val(element.drugGCN);
            $("#gpitxtId").val(element.drugGPI);
            $("#BI").val(element.brandIndicator);
            $("#inStock").val(element.inStock);
            $("#brand").val(element.brandName);
            $("#genericTxtId").val(element.genericName);
            $("#strengthTxt").val(element.strength);
            $("#gnnTxt").val(element.gnn);
            $("#dosageFormTxt").val(element.formDesc);
            $("#packagingDesc").val(element.packingDesc);
            $("#rxQty").val(element.defQty);
            $("#qtyList").val(element.packageSizeValues);
            $("#therapeuticCategory").val(element.therapy);
            $("#pdfFileName").val(element.pdf);
            var u = element.pdfDocUrl;
            $("#pdfImg").attr("href", element.pdfDocUrl);
            $("#drugImgFileTxt").val(element.drugImageName);
            $("#drugImgUrl").attr("src", element.image);
            $("#drugImgLink").attr("href", element.image);
            $("#drugDocFileName").val(element.drugDocName);
            $("#drugDocImg").attr("href", element.drugDocUrl);
            window.pharmacyNotification.addCommasToValue(element.basePrice, "priceBase", "$");
            window.pharmacyNotification.addCommasToValue(element.calculatedAcqPrice, "acqPrice", "$");
            window.pharmacyNotification.addCommasToValue(element.marginPercent, "margin", "");
            window.pharmacyNotification.addCommasToValue(element.markUpAmt, "marginFId", "$");
            window.pharmacyNotification.addCommasToValue(element.additionalFee, "baseOverhead", "$");
            window.pharmacyNotification.addCommasToValue(element.mktSurcharge, "mrktSurcharge", "$");
            window.pharmacyNotification.addCommasToValue(element.estimatedCashPrice, "final", "$");
            window.pharmacyNotification.addCommasToValue(element.estimatedCashPrice, "final_total", "$");
            $("#rxExpires").val(element.rxExpire);
            $("#reqHandDelivery").val(element.requiresHandDelivery);
            $("#lastUpdatedDate").text(element.lastUpdated);
        }

    },
    formatPriceToTwoDecimalPlaces: function (val)
    {
        var s = val.toString();
        var ss = s.split(".");
        if (ss.length == 2)
        {
            if (ss[1].length == 1)
            {
                ss[1] = ss[1] + "0";
                return ss[0] + "." + ss[1];
            }
        }
        return s;
    },
    removeDisableProp: function () {
        if ($('.disabled_write .form-control').val().length > 0) {
            $('.disabled_write .form-control').removeAttr('disabled');
            $('#btnSavechanges').removeAttr('disabled');
            $('#priceBase').removeAttr('disabled');
            $('#margin').removeAttr('disabled');
            $('#baseOverhead').removeAttr('disabled');
            $('#mrktSurcharge').removeAttr('disabled');
            $('.btn_upload').removeAttr('disabled');
        }

    },
    estimateDrugPriceList: function (currentUserRole) {
        $("#drugGCNErrorMsg").text("");
        if ($('.disabled_write .form-control').val().length > 0) {
            $('.disabled_write .form-control').val("");
            $("#responseMsg").text("");
            $(".errorMessage").text("");
            $("#BI").val("GENERIC");
            $("#inStock").val("Y");
            $("#rxExpires").val("Y");
            $("#reqHandDelivery").val("Y");
            $("#final_total").text("");
            $("#priceBase").val("");
            $("#acqPrice").val("");
            $("#margin").val("");
            $("#marginFId").val("");
            $("#baseOverhead").val("");
            $("#mrktSurcharge").val("");
            $("#final").val("");
            $("#lastUpdatedDate").text("");
            $("#drugImgLink").attr("href", "#");
        }
        var gcn = $("#drugGCN").val();
        var brandname = $("#drugNameId").val();
        var qty = $("#quantity").val();
        if (brandname.length === 0 && gcn.length === 0) {
            return false;
        }

        if (qty === "") {
            qty = 1;
        }
        var dosageForm = $("#drugTypeId").val();
        var drugStrength = $("#drugStrength").val();
        if ($("#searchBy").text() === "brand") {
            gcn = 0;
        }
        var json = {"drugGCN": gcn, "brandname": brandname, "dosageForm": dosageForm, "qty": qty, "drugStrength": drugStrength};
        //alert(json);
        $.ajax({
            url: window.drugestimateprice.appUrl + "/ConsumerPortal/estimateDrugPriceList",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (resp) {
                if (resp.length > 0) {
                    $('.disabled_write .form-control').attr('disabled', 'disabled');
                    if (resp.length >= 1) {
                        $("#estimateDrugPriceTbl").dataTable().fnDestroy();
                        window.drugestimateprice.initializeDataTable("100px");
                    }
                    $("#estimateDrugPriceTblRecord").val(resp.length);
                    $("#estimateDrugPriceTbl > tbody > tr").remove();
                    $.each(resp, function (index, element) {
                        var html = "<tr>";
                        if (element.isChk === true) {
                            html += "<td><input id='drugDetailId" + element.drugDetailId + "' type='radio' name='drugDetailId' onclick='window.drugestimateprice.estimateDrugPrice(" + element.drugDetailId + ")' checked='checked'/> " + element.brandName + "</td>";
                        } else {
                            html += "<td><input id='drugDetailId" + element.drugDetailId + "' type='radio' name='drugDetailId' onclick='window.drugestimateprice.estimateDrugPrice(" + element.drugDetailId + ")'/> " + element.brandName + "</td>";
                        }
                        html += "<td>" + element.genericName + "</td>";
                        html += "<td>" + element.strength + "</td>";
                        html += "<td>" + element.formDesc + "</td>";
                        html += "</tr>";
                        $("#estimateDrugPriceTbl").find("tbody").append(html);
                        if (element.isChk === true) {
                            window.drugestimateprice.populateDrugDetailCalculation(element, qty, currentUserRole);
                        }
                    });
                    if (currentUserRole == "admin") {
                        $("#modal-body").attr("style", "overflow: auto;height: 580px;");
                    }
                } else {
                    window.drugestimateprice.resetField(currentUserRole);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    estimateDrugPrice: function (drugDetailId) {

        var qty = $("#quantity").val();
        if (qty === "") {
            qty = 1;
        }

        if ($("#estimateDrugPriceTblRecord").val() > 0 && (!$("#drugDetailId" + drugDetailId).is(":checked"))) {
            return false;
        }
        $("#drugDetailId").val(drugDetailId);
        var role = $("#currentUserRole").val();
        var json = {"drugDetailId": drugDetailId, "qty": qty};
        //alert(json);
        $.ajax({
            url: window.drugestimateprice.appUrl + "/ConsumerPortal/calculateDrugPrice",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (resp) {
                $.each(resp, function (index, element) {
                    window.drugestimateprice.populateDrugDetailCalculation(element, qty, role);
                });
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    ////////////////////////////////////////////////////////////////////////////
    updateMultiRxPrices: function (id)
    {
        var jsonArr = [];
        $("#" + id + " tr:gt(0)").each(function ()
        {
            var this_row = $(this);
            var index = this_row.index();
//            console.log("Index "+this_row.index()+" id "+$("#id_"+index).val()
//                    +" base price "+$("#txtBasePrice_"+index).val());
            jsonArr.push({
                drugDetailId: $("#id_" + index).val(),
                basePriceStr: $("#txtBasePrice_" + index).val(),
                marginPercent: $("#txtMarginPercent_" + index).val(),
                defQty: $("#txtQty_" + index).val(),
                acqPpriceStr: $("#txtAcqPrice_" + index).val(),
                unitMarkupAmtStr: $("#txtMarkupAmt_" + index).val()
            });
        });
        //console.log("json arr length "+jsonArr.length);
        //////////////////////////////////////////////////////////////
        var json = {"jsonArr": jsonArr};
        //alert(json);
        $.ajax({
            url: window.drugestimateprice.appUrl + "/ConsumerPortal/udateMultiDrugPrices",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (resp) {
                window.drugestimateprice.estimateDrugPriceListUsingSearchParameter();
                $("#drugMultiSuccessMsg").text("Record(s) has been updated successfully");
                $("#drugMultiSuccessMsg").show();
                $("#drugMultiErrorMsg").hide();
            },
            error: function (e) {
//                   $("#drugMultiSuccessMsg").text("problem in saving record "+e);
                $("#drugMultiSuccessMsg").hide();
                $("#drugMultiErrorMsg").show();
                $("#drugMultiErrorMsg").text(e.responseText);
            }
        });
        //////////////////////////////////////////////////////////////


    },
    ////////////////////////////////////////////////////////////////////////////
    checkMultiRadioButtonOption: function ()
    {
//        alert("attr "+$("#multiRadioGCN").is(':checked'));
        $("#drugMultiErrorMsg").hide();
        $("#drugMultiSuccessMsg").hide();

        if ($("#multiRadioGCN").is(':checked'))
        {
            $("#multiGcnDiv").show();
            $("#multiDrugDiv").hide();
        } else
        {
            $("#multiGcnDiv").hide();
            $("#multiDrugDiv").show();
        }
    },
    ////////////////////////////////////////////////////////////////////////////
    estimateDrugPriceListUsingSearchParameter: function () {
//        $("#drugGCNErrorMsg").text("");
        $("#drugMultiErrorMsg").hide();
        $("#drugMultiSuccessMsg").hide();
        var url = '';
        var searchParam;
        if ($("#multiRadioGCN").is(":checked"))
        {
            url = window.drugestimateprice.appUrl + '/ConsumerPortal/estimateDrugPriceListUsingGCN';
            searchParam = $("#multiDrugGcnText").val();
        } else
        {
            url = window.drugestimateprice.appUrl + '/ConsumerPortal/estimateDrugPriceListUsingDrugName';
            searchParam = $("#multiDrugText").val();
        }


        if (searchParam.length === 0)
        {
//            alert("Please specify proper search value.");
            $("#drugMultiErrorMsg").text("Please specify proper search value.");
            $("#drugMultiErrorMsg").show();
            return;
        }


        var json = {"searchParam": searchParam};
        //alert(json);
        $.ajax({
            url: url,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (resp) {
                if (resp.length > 0) {

                    $("#multiDrugPriceUpdateTable").dataTable().fnDestroy();
//                        window.drugestimateprice.initializeDataTable("100px");

//                    $("#estimateDrugPriceTblRecord").val(resp.length);
                    $("#multiDrugPriceUpdateTable > tbody > tr").remove();
                    $.each(resp, function (index, element) {
                        var html = "<tr class=\"underline\">";
                        html += "<td id=\"gcnCol_index\">"
                                + "<input id=\"id_" + index + "\" type=\"hidden\" value=\"" + element.drugDetailId + "\" />"
                                + "<span id=\"gcnSpan_index\">" + element.drugGCN + "</span>"
                                + "</td>";

                        html += "<td>" + element.brandName + "</td>";
                        html += "<td>" + element.genericName + "</td>";
                        html += "<td>" + element.strength + "</td>";
                        html += "<td>" + element.formDesc + "</td>";
                        html += "<td align=\"\"><input id=\"txtQty_" + index + "\" type=\"text\" size=\"2\"  onkeypress=\"return IsDigit(event)\" style=\"text-align:center;\" maxlength=5  onblur=\"window.drugestimateprice.multiRxCalculationFieldHandler(this)\" value=\"" + element.stdRxQty + "\"></td>";
                        html += "<td align=\"center\"><input id=\"txtAcqPrice_" + index + "\" type=\"text\" size=\"5\" onkeypress=\"return IsNumbersWithDecimal(event)\" style=\"text-align:center;\" "
                                + " value=\"" + element.acqPpriceStr + "\" "
                                + " onblur=\"window.drugestimateprice.calculateUnitPriceFromPkgCost(this)\"></td>";
                        if (element.sameDateFlag == true) {
//                            console.log("red" + element.sameDateFlag);
                            html += "<td align=\"center\"><span id=\"dateUpdated_" + index + "\" style=\"color:red;\">" + element.lastUpdatedYMD + "</span></td>";
                        } else {
//                            console.log("green" + element.sameDateFlag);
                            html += "<td align=\"center\"><span id=\"dateUpdated_" + index + "\">" + element.lastUpdatedYMD + "</span></td>";
                        }
                        html += "<td align=\"center\"><input id=\"txtBasePrice_" + index + "\" size=\"5\" type=\"text\" onkeypress=\"return IsNumbersWithDecimal(event)\" style=\"text-align:center;\" "
                                + " value=\"" + element.basePriceStr + "\" "
                                + " onchange=\"window.drugestimateprice.markUnitCostEditFlag(this)\" "
                                + " onblur=\"window.drugestimateprice.checkUnitCostEditFlag(this)\"></td>";
                        html += "<td align=\"center\"><input size=\"2\" maxlength=\"5\" id=\"txtMarginPercent_" + index + "\" type=\"text\" onkeypress=\"return IsNumbersWithDecimal(event)\" style=\"text-align:center;\" "
                                + " value=\"" + element.marginPercent + "\" onblur=\"window.drugestimateprice.checkUnitMarkupAmtFlag(this)\" "
                                + " onchange=\"window.drugestimateprice.markUnitMarkupAmtEditFlag(this)\" >"
                        //////////////////////////////////////////////////////////////////////////////////
                        html += "<td align=\"center\"><span id=\"retailPrice_" + index + "\">" + element.unitRetailPriceStr + "</span></td>";
                        /////////////////////////////////////////////////////////////////////////////////
                        html += "<td align=\"center\"><input size=\"5\" maxlength=\"5\" id=\"txtMarkupAmt_" + index + "\" type=\"text\" onkeypress=\"return IsNumbersWithDecimal(event)\" style=\"text-align:center;\" "
                                + " value=\"" + element.unitMarkupAmtStr + "\" onblur=\"window.drugestimateprice.calculatePercentFromMarkUpCost(this)\">";
                        html += "<td align=\"center\"><span id=\"additionalFee_" + index + "\">" + element.unitAdditionalFeeStr + "</span></td>";
                        html += "<td align=\"center\"><span id=\"mktSurcharge_" + index + "\">" + element.mktSurchatgeStr + "</span></td>";
                        //html += "<td align=\"center\"><span id=\"estimatedPrice_"+index+"\">" + element.estimatedCashPriceStr + "</span></td>";
                        html += "</tr>";
                        $("#multiDrugPriceUpdateTable").find("tbody").append(html);
//                        if (element.isChk === true) {
//                            window.drugestimateprice.populateDrugDetailCalculation(element, qty, currentUserRole);
//                        }
                    });
//                    if (currentUserRole == "admin") {
//                        $("#modal-body").attr("style", "overflow: auto;height: 580px;");
//                    }
                }
//                else {
//                    window.drugestimateprice.resetField(currentUserRole);
//                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    ////////////////////////////////////////////////////////////////////////////
    markUnitMarkupAmtEditFlag: function (obj)
    {
        console.log("IN markUnitMarkupAmtEditFlag ");
        $("#unitMarkupAmoutChanged").val('1');

    },

    checkUnitMarkupAmtFlag: function (obj)
    {
        if ($("#unitMarkupAmoutChanged").val() == '1')
        {
            this.multiRxUnitMarkupReset(obj);
        }
        $("#unitMarkupAmoutChanged").val('0');

    },
    ////////////////////////////////////////////////////////////////////////////
    resetEditFlagValues: function ()
    {//alert("HI");
        $("#unitCostChanged").val('0');
        $("#unitMarkupAmoutChanged").val('0');
    },
    ///////////////////////////////////////////////////////////////////////////
    markUnitCostEditFlag: function (obj)
    {
        console.log("IN markUnitCostEditFlag ");
        $("#unitCostChanged").val('1');

    },

    checkUnitCostEditFlag: function (obj)
    {
        if ($("#unitCostChanged").val() == '1')
        {
            this.multiRxCalculationFieldHandler(obj);
        }
        $("#unitCostChanged").val('0');

    },

    ///////////////////////////////////////////////////////////////////////////
    multiRxCalculationFieldHandler: function (obj)
    {
        var id = $(obj).attr('id');
//         alert("ID "+id);
        var fldNames = id.split('_');
        var index = fldNames[1];
        $("#txtAcqPrice_" + index).val('0');
        $("#txtMarkupAmt_" + index).val('0');
        this.calculateDrugPricesMultiUpdate(obj);
    },

    multiRxUnitMarkupReset: function (obj)
    {
        var id = $(obj).attr('id');
//         alert("ID "+id);
        var fldNames = id.split('_');
        var index = fldNames[1];
        $("#txtMarkupAmt_" + index).val('0');
        this.calculateDrugPricesMultiUpdate(obj);
    },
    ////////////////////////////////////////////////////////////////////////////
    calculateUnitPriceFromPkgCost: function (obj)
    {
        $("#drugMultiErrorMsg").hide();
        $("#drugMultiSuccessMsg").hide();
        var id = $(obj).attr('id');
//         alert("ID "+id);
        var fldNames = id.split('_');
        var index = fldNames[1];
        var pkgCostStr = $("#txtAcqPrice_" + index).val().replace("$", "");
        var pkgCost = parseFloat(pkgCostStr.replace(/,/g, ""));
        if (isNaN(pkgCost) || pkgCost <= 0)
        {
            $("#drugMultiErrorMsg").text("Please specify proper package cost.");
            $("#drugMultiErrorMsg").show();
            $("#txtAcqPrice_" + index).focus();
            return;
        }
        var qtyStr = $("#txtQty_" + index).val();
        var qty = parseInt(qtyStr);
        if (isNaN(qty) || qty <= 0)
        {
            $("#drugMultiErrorMsg").text("Please specify proper quantity.");
            $("#drugMultiErrorMsg").show();
            $("#txtQty_" + index).focus();
            return;
        }
        console.log("PKG " + pkgCost + " qty " + qty + " UNIT " + pkgCost / qty);
        $("#txtBasePrice_" + index).val((pkgCost / qty).toFixed(2));
        $("#txtMarkupAmt_" + index).val('');
        this.calculateDrugPricesMultiUpdate(obj);
    },
    ////////////////////////////////////////////////////////////////////////////
    calculatePercentFromMarkUpCost: function (obj)
    {
        $("#drugMultiErrorMsg").hide();
        $("#drugMultiSuccessMsg").hide();
        var id = $(obj).attr('id');
//         alert("ID "+id);
        var fldNames = id.split('_');
        var index = fldNames[1];
        var markUpAmountStr = $("#txtMarkupAmt_" + index).val().replace("$", "");
        var markUpAmount = parseFloat(markUpAmountStr.replace(/,/g, ""));
        if (isNaN(markUpAmount) || markUpAmount <= 0)
        {
            $("#drugMultiErrorMsg").text("Please specify proper unit margin.");
            $("#drugMultiErrorMsg").show();
            $("#txtMarkupAmt_" + index).focus();
            return;
        }
        var unitCostStr = $("#txtBasePrice_" + index).val().replace("$", "").replace(/,/g, "");
        var unitCost = parseFloat(unitCostStr);
//         console.log("str "+unitCostStr+" Price "+unitCost)
        if (isNaN(unitCost) || unitCost <= 0)
        {
            $("#drugMultiErrorMsg").text("Please specify proper unit cost.");
            $("#drugMultiErrorMsg").show();
            $("#txtBasePrice_" + index).focus();
            return;
        }
//         console.log("PKG "+pkgCost+" qty "+qty+" UNIT "+pkgCost/qty);
        $("#txtMarginPercent_" + index).val((markUpAmount * 100 / unitCost).toFixed(2));
        this.calculateDrugPricesMultiUpdate(obj);
    },
    ////////////////////////////////////////////////////////////////////////////
    calculateDrugPricesMultiUpdate: function (obj)
    {
        $("#drugMultiErrorMsg").hide();
        $("#drugMultiSuccessMsg").hide();
        var id = $(obj).attr('id');
//         alert("ID "+id);
        var fldNames = id.split('_');
        var index = fldNames[1];
        var idParam = $("#id_" + index).val();
        var qtyParam = $("#txtQty_" + index).val();//.text();
        var qty = parseInt(qtyParam);
        if (isNaN(qty) || qty <= 0)
        {
            $("#drugMultiErrorMsg").text("Please specify proper quantity.");
            $("#drugMultiErrorMsg").show();
            $("#txtQty_" + index).focus();
            return;
        }
        //         console.log("FLD #txtQty_"+index+" OBJ "+$("#txtQty_"+index)+" QTY PARAM "+qtyParam);
        var marginPercentParam = $("#txtMarginPercent_" + index).val();
        var basePriceParam = $("#txtBasePrice_" + index).val();
        var unitMarginParam = $("#txtMarkupAmt_" + index).val();
        var pkgCostParam = $("#txtAcqPrice_" + index).val();
        console.log("UNIT MARGIN " + unitMarginParam);
        var url = window.drugestimateprice.appUrl + '/ConsumerPortal/estimateDrugPriceListUsingId';
        ////////////////////////////////////////////////////////////////////////
        var json = {"idParam": idParam, "qtyParam": qtyParam,
            "basePriceParam": basePriceParam, "marginPercentParam": marginPercentParam,
            "pkgCostParam": pkgCostParam, "unitMarginParam": unitMarginParam};
        //alert(json);
        $.ajax({
            url: url,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            data: JSON.stringify(json),
            success: function (resp) {

                $("#txtBasePrice_" + index).val(resp.basePriceStr);
                $("#txtMarginPercent_" + index).val(resp.marginPercent);
                $("#txtAcqPrice_" + index).val(resp.acqPpriceStr);
                $("#txtMarkupAmt_" + index).val(resp.unitMarkupAmtStr);
                $("#additionalFee_" + index).text(resp.unitAdditionalFeeStr);
                $("#mktSurcharge_" + index).text(resp.mktSurchatgeStr);
                $("#retailPrice_" + index).text(resp.unitRetailPriceStr);
//               $("#estimatedPrice_"+index).text(resp.estimatedCashPriceStr);
            },
            error: function (e) {
                $("#drugMultiErrorMsg").text("Invalid Gcn.");
                //$("#drugGCNErrorMsg").attr("class", "successMessage");
                $("#drugMultiErrorMsg").show();
            }
        });
        ////////////////////////////////////////////////////////////////////////
    },

    ////////////////////////////////////////////////////////////////////////////
    sendNotifyAdmin: function () {
        var notifyAdmin = $("#notifyAdminFId").val();
        if (notifyAdmin.length == 0) {
            $("#notifyAdminReq").text("Required");
            $("#notifyAdminReq").attr("style", "color:red;display:block;margin-top:-14px;");
            return;
        } else {
            $("#notifyAdminReq").text("");
            $("#notifyAdminReq").hide();
        }
        var json = {"notifyAdmin": notifyAdmin};
        $.ajax({
            url: window.drugestimateprice.appUrl + "/PharmacyPortal/sendNotifyAdmin",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (resp) {
                alert(resp.errorMessage);
                $("#notifyAdminFId").val("");
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    saveDrugDetail: function () {

        if (!this.validateField()) {
            return;
        }
        $("#responseMsg").hide();

        var data = this.populateFormData();

        $.ajax({
            url: window.drugestimateprice.appUrl + "/Pharmacyqueue/uploadDrugDetail",
            type: "POST",
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            cache: false,
            data: data,
            success: function (response) {
                if (response.statuscode == 200) {
                    $("#responseMsg").text(response.errorMessage);
                    $("#responseMsg").attr("class", "successMessage");
                    $("#responseMsg").show();
                } else {
                    $("#responseMsg").text(response.errorMessage);
                    $("#responseMsg").attr("class", "errorMessage");
                    $("#responseMsg").show();
                }
//                alert(response.errorMessage);
            }, error: function (e) {
                $("#responseMsg").text('Error while request...' + e);
                $("#responseMsg").attr("class", "errorMessage");
                $("#responseMsg").show();
            }
        });
    }, addNewDrugDetail: function () {
        if (!this.validateField()) {
            return;
        }
        $("#responseMsg").hide();
        var data = this.populateFormData();
        $.ajax({
            url: window.drugestimateprice.appUrl + "/Pharmacyqueue/addNewDrugDetail",
            type: "POST",
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            cache: false,
            data: data,
            success: function (response) {
                if (response.statuscode == 200) {
                    var currentUserRole = $("#currentUserRole").val();
                    window.drugestimateprice.resetField(currentUserRole);
                    $("#responseMsg").text(response.errorMessage);
                    $("#responseMsg").attr("class", "successMessage");
                    $("#responseMsg").show();
                } else {
                    $("#responseMsg").text(response.errorMessage);
                    $("#responseMsg").attr("class", "errorMessage");
                    $("#responseMsg").show();
                }
//                alert(response.errorMessage);
            }, error: function (e) {
                $("#responseMsg").text('Error while request...' + e);
                $("#responseMsg").attr("class", "errorMessage");
                $("#responseMsg").show();
            }
        });
    }, validateField: function () {
        if ($("#gcnTxtId").val().length == 0) {
            $("#responseMsg").text("Please enter gcn.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#gcnTxtId").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#gpitxtId").val().length == 0) {
            $("#responseMsg").text("Please enter gpi.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#gpitxtId").focus();
            $("#responseMsg").show();
            return false;
        }
        if (this.isEmpty($("#BI").val())) {
            $("#responseMsg").text("Please select brand indicator.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#BI").focus();
            $("#responseMsg").show();
            return false;
        }
        if (this.isEmpty($("#inStock").val())) {
            $("#responseMsg").text("Please select in stock.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#inStock").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#brand").val().length == 0) {
            $("#responseMsg").text("Please enter brand name.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#brand").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#genericTxtId").val().length == 0) {
            $("#responseMsg").text("Please enter generic name.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#genericTxtId").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#strengthTxt").val().length == 0) {
            $("#responseMsg").text("Please enter strength.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#strengthTxt").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#dosageFormTxt").val().length == 0) {
            $("#responseMsg").text("Please enter dosage form.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#dosageFormTxt").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#packagingDesc").val().length == 0) {
            $("#responseMsg").text("Please enter packaging desc.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#packagingDesc").focus();
            return false;
        }
        if (!this.validateRxQty()) {
            return false;
        }
        if ($("#qtyList").val().length == 0) {
            $("#responseMsg").text("Please enter Qty DropDown.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#qtyList").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#qtyList").val().length > 0) {
            var qty = parseInt($("#qtyList").val());
            if (isNaN(qty))
            {
                $("#responseMsg").text("Please enter valid qty dropdown.");
                $("#responseMsg").attr("class", "errorMessage");
                $("#qtyList").focus();
                $("#responseMsg").show();
                return false;
            }
        }
        if ($("#therapeuticCategory").val().length == 0) {
            $("#responseMsg").text("Please enter therapeutic category.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#therapeuticCategory").focus();
            $("#responseMsg").show();
            return false;
        }
        if (!this.validatePriceField()) {
            return false;
        }
        if (this.isEmpty($("#rxExpires").val())) {
            $("#responseMsg").text("Please select rx expires.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#rxExpires").focus();
            $("#responseMsg").show();
            return false;
        }
        if (this.isEmpty($("#reqHandDelivery").val())) {
            $("#responseMsg").text("Please select req same/next day delivery.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#reqHandDelivery").focus();
            $("#responseMsg").show();
            return false;
        }
        return true;
    }, populateFormData: function () {
        this.truncateFldValue();
        var data = new FormData();
        data.append("drugDetailId", $("#drugDetailId").val());
        data.append("drugGCN", $("#gcnTxtId").val());
        data.append("drugGPI", $("#gpitxtId").val());
        data.append("demandBrand", $("#BI").val());
        data.append("inStock", $("#inStock").val());
        data.append("brandName", $("#brand").val());
        data.append("genericName", $("#genericTxtId").val());
        data.append("strength", $("#strengthTxt").val());
        data.append("formDesc", $("#dosageFormTxt").val());
        data.append("packingDesc", $("#packagingDesc").val());
        data.append("defQty", $("#rxQty").val());
        data.append("packageSizeValues", $("#qtyList").val());
        data.append("therapeuticCategory", $("#therapeuticCategory").val());
        data.append("gnn", $("#gnnTxt").val());
        data.append("rxExpire", $("#rxExpires").val());
        data.append("requiresHandDelivery", $("#reqHandDelivery").val());
        data.append("marginPercent", $("#margin").val());
        data.append("basePrice", $("#priceBase").val());
        data.append("additionalFee", $("#baseOverhead").val());
        data.append("mktSurcharge", $("#mrktSurcharge").val());

        if ($("#drugPdffile")[0].files.length > 0) {
            data.append("pdfDocFile", $("#drugPdffile")[0].files[0]);
        }

        if ($("#drugDocfile")[0].files.length > 0) {
            data.append("drugDocFile", $("#drugDocfile")[0].files[0]);
        }
        if ($("#drugImagefile")[0].files.length > 0) {
            data.append("imgFile", $("#drugImagefile")[0].files[0]);
        }
        this.appendFldValue();
        return data;
    }, addBrandNewProduct: function (type) {
        $("#drugBrandModal").modal("hide");
        $(".disabled_write .form-control").removeAttr("disabled");
        $("#btnSavechanges").removeAttr("disabled");
        $('.btn_upload').removeAttr('disabled');
        $("#priceBase,#margin,#baseOverhead,#mrktSurcharge").removeAttr("disabled");
        if (type == 1) {
            $(".disabled_write .form-control").val("");
            $("#drugImgUrl").attr("src", "#");
            $("#priceBase,#margin,#baseOverhead,#mrktSurcharge").val("0");
            $("#marginFId,#acqPrice,#final").val("0");
            $("#headingDrugPrice").attr("class", "heading_drugprice add_new_drugbrand");
            $("#headingDrugPrice").text("ADDING - BRAND NEW PRODUCT / DRUG ");
            $("#modal-body").attr("style", "overflow: auto;height: 580px;");
        } else {
            $("#gcnTxtId,#BI,#brand,#genericTxtId").val("");
            $("#headingDrugPrice").attr("class", "heading_drugprice add_line_extension");
            $("#headingDrugPrice").text("ADDING - LINE EXTENSION");
        }
        $("#btnSavechanges").attr("onclick", "window.drugestimateprice.addNewDrugDetail()");
    }, isOpenAddNewDialog: function () {
        $("#drugBrandModal").modal("show");
        if ($('.disabled_write .form-control').val().length > 0) {
            $("#cloneLink").attr("onclick", "window.drugestimateprice.addBrandNewProduct('2')");
        }
    }, hideDialog: function (dialogId) {
        $("#" + dialogId).modal("hide");
    },
    qtyFldBlurHandler: function ()
    {
        try
        {
//            debugger;
            var qty = $("#rxQty").val();
            if (qty.trim().length > 0)
            {
                if (parseInt(qty) >= 0)
                {
                    $("#rxQty").val($("#quantity").val());
                    $("#rxQty").change();
                }
            }
        } catch (err)
        {

        }

    },

    calculateDrugDetailPrice: function () {
        this.truncateFldValue();
        var data = new FormData();
        if (!this.validateRxQty()) {
            return false;
        }
        if (!this.validatePriceField()) {
            return false;
        }
        $("#responseMsg").text("");
        $("#responseMsg").hide();
        data.append("defQty", $("#rxQty").val().length > 0 ? $("#rxQty").val() : 0);
        data.append("marginPercent", $("#margin").val().length > 0 ? $("#margin").val() : 0);
        data.append("basePrice", $("#priceBase").val().length > 0 ? $("#priceBase").val() : 0);
        data.append("additionalFee", $("#baseOverhead").val().length > 0 ? $("#baseOverhead").val() : 0);
        data.append("mktSurcharge", $("#mrktSurcharge").val().length > 0 ? $("#mrktSurcharge").val() : 0);
        $.ajax({
            url: window.drugestimateprice.appUrl + "/Pharmacyqueue/calculateDrugDetailPrice",
            type: "POST",
            contentType: false,
            processData: false,
            cache: false,
            data: data,
            success: function (response) {
                if (response == null && response.statuscode !== 200) {
                    $("#responseMsg").text(response.errorMessage);
                    $("#responseMsg").attr("class", "errorMessage");
                    $("#responseMsg").show();
                } else {
//                    alert("base price "+element.basePrice+" with commas "+window.pharmacyNotification.addCommasToValue(element.basePrice,"priceBase","$"));
                    window.pharmacyNotification.addCommasToValue(response.basePrice, "priceBase", "$");
                    window.pharmacyNotification.addCommasToValue(response.acqPrice, "acqPrice", "$");
                    window.pharmacyNotification.addCommasToValue(response.marginPercent, "margin", "");
                    window.pharmacyNotification.addCommasToValue(response.markUpAmt, "marginFId", "$");
                    window.pharmacyNotification.addCommasToValue(response.additionalFee, "baseOverhead", "$");
                    window.pharmacyNotification.addCommasToValue(response.mktSurcharge, "mrktSurcharge", "$");
                    window.pharmacyNotification.addCommasToValue(response.estimatedCashPrice, "final", "$");
                    window.pharmacyNotification.addCommasToValue(response.estimatedCashPrice, "final_total", "$");
//                    $("#priceBase").val(response.basePrice);
//                    $("#acqPrice").val(response.acqPrice);
//                    $("#margin").val(response.marginPercent);
//                    $("#marginFId").val(response.markUpAmt);
//                    $("#baseOverhead").val(response.additionalFee);
//                    $("#mrktSurcharge").val(response.mktSurcharge);
//                    $("#final").val("$" + response.estimatedCashPrice);
//                    $("#final_total").text("$" + response.estimatedCashPrice);
                    window.drugestimateprice.appendFldValue();
                }
//                alert(response.errorMessage);
            }, error: function (e) {
                $("#responseMsg").text('Error while request...' + e);
                $("#responseMsg").attr("class", "errorMessage");
                $("#responseMsg").show();
            }
        });
    }, isEmpty: function (value) {
        if (value == null || value.length == 0) {
            return true;
        }
        return false;
    }, truncateFldValue: function () {
        window.pharmacyNotification.truncateFldValue('priceBase', 1, 1, '$');
        window.pharmacyNotification.truncateFldValue('baseOverhead', 1, 1, '$');
        window.pharmacyNotification.truncateFldValue('mrktSurcharge', 1, 1, '$');
    }, appendFldValue: function () {
        window.pharmacyNotification.appendFldValue('priceBase', 1, '$');
        window.pharmacyNotification.appendFldValue('baseOverhead', 1, '$');
        window.pharmacyNotification.appendFldValue('mrktSurcharge', 1, '$');
    },
    /////////////////////////////////////////////////////////////////////////
    drugGCNSearchIndicator: function ()
    {
        var gcn = $("#drugGCN").val();
        if (gcn.trim().length > 0)
        {
//           console.log("called populate function.");
            $("#gcnSearch").val(1);
            this.populateDrugQtyByGCN();
        } else
        {
//           console.log("didn't call populate function.");
            $("#gcnSearch").val(0);
        }
    },
    /////////////////////////////////////////////////////////////////////////
    populateDrugQtyByGCN: function () {
        $(".ui-menu-item").hide();
        try
        {

            $('#drugGCN').autocomplete("destroy");
        } catch (ex) {
        }
        var gcnVal = $("#drugGCN").val();
        if (gcnVal.length === 0) {
            return;
        }
        var json = {"drugGCN": gcnVal};
        $.ajax({
            url: window.drugestimateprice.appUrl + "/rxTransfer/populateDrugQtyByGCN",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (resp) {
                $("#searchBy").text("gcn");
                $("#drugGCNErrorMsg").text("");
                $('#drugDetailId').val(resp.drugDetailId);
                $('#qtyFId').val(resp.defQty);
                $('#quantity').val(resp.defQty);
                $("#referenceBrand").val(resp.brandName);

                $("#drugTypeId").html('<option value="' + resp.dosageForm + '">' + resp.dosageForm + '</option>');
                $("#drugStrength").html('<option value="' + resp.strength + '">' + resp.strength + '</option>');
                $("#drugNameId").val(resp.genericName);
                if ($("#gcnSearch") == null || $("#gcnSearch").val() == 0)
                {
//                    console.log("going to call drug type blur");
                    $('#drugTypeId').trigger("blur");
                }

            },
            error: function (e) {
                //alert('No such drug exists');
                $("#drugGCNErrorMsg").text("Invalid Gcn.");
                //$("#drugGCNErrorMsg").attr("class", "successMessage");
                $("#drugGCNErrorMsg").show();
            }
        });
    }, populateUniqueObject: function (fid, drugGcnId, url, selectFid) {
        var gcnSearch = 0;
        if ($("#gcnSearch") != null)
        {
            gcnSearch = $("#gcnSearch").val();
        }
//        console.log("gcn search val in brand blur function "+gcnSearch);
        $("#searchBy").text("brand");
        $("#drugGCNErrorMsg").text("");
        var drugNameDbId = $("#" + fid).val();
        if (drugNameDbId.length < 2) {
            return;
        }
        var drugGcn = $("#" + drugGcnId).val();
        if (drugGcn.length === 0) {
            drugGcn = 0;
        }
        var json = {"name": drugNameDbId, "drugGcn": drugGcn};
        $.ajax({
            url: url,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                var html = '';
//                alert("here now "+responses.length);
                if (responses.length > 0) {
                    for (var i = 0; i < responses.length; i++) {
                        $('#drugDetailId').val(responses[i].drugDetailId);
                        $('#' + selectFid).val(responses[i].drugGCN);
//                        $('#drugTypeId').html('<option value="' + responses[i].formDesc + '">' + responses[i].formDesc + '</option>');
                        //$('#drugStrength').html('<option value="' + responses[i].strength + '">' + responses[i].strength + '</option>');
                        //$('#qtyFId').val(responses[i].defQty);
                        //$('#quantity').val(responses[i].defQty);
                        break;
                    }
                }
//                console.log("gcn search val in brand blur function 2 "+gcnSearch);
                if (gcnSearch == 1)
                {
                    $('#' + selectFid).trigger("blur");
//                    console.log("gcn search val in brand blur 3 "+$("#gcnSearch").val()+" select fid "+selectFid);
                } else
                {
                    window.drugestimateprice.populateDrugDosageAndStrength();
                    window.drugestimateprice.populateDrugQtyByGCN();
                }
//                $('#drugTypeId').trigger("blur");
            },
            error: function (e) {
                //alert('No such drug exists');
                $("#drugGCNErrorMsg").text("No such drug exists.");
                $("#drugGCNErrorMsg").show();
            }
        });
    }, validatePriceField: function () {
        if ($("#priceBase").val().length == 0) {
            $("#responseMsg").text("Please enter base price.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#priceBase").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#priceBase").val() == 0) {
            $("#responseMsg").text("Base price must be greater then 0.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#priceBase").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#margin").val().length == 0) {
            $("#responseMsg").text("Please enter margin percent.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#margin").focus();
            $("#responseMsg").show();
            return false;
        }

        if ($("#baseOverhead").val().length == 0) {
            $("#responseMsg").text("Please enter base overhead.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#baseOverhead").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#mrktSurcharge").val().length == 0) {
            $("#responseMsg").text("Please enter mrkt surcharge.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#mrktSurcharge").focus();
            $("#responseMsg").show();
            return false;
        }
        return true;
    }, validateRxQty: function () {
        if ($("#rxQty").val().length == 0) {
            $("#responseMsg").text("Please enter rx qty.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#rxQty").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#rxQty").val() == 0) {
            $("#responseMsg").text("Rx qty must be greater then 0.");
            $("#responseMsg").attr("class", "errorMessage");
            $("#rxQty").focus();
            $("#responseMsg").show();
            return false;
        }
        if ($("#rxQty").val().length > 0) {
            var qty = parseInt($("#rxQty").val());
            if (isNaN(qty))
            {
                $("#responseMsg").text("Please enter valid rx qty.");
                $("#responseMsg").attr("class", "errorMessage");
                $("#rxQty").focus();
                $("#responseMsg").show();
                return false;
            }
        }
        return true;
    },

    resetMultiRxForm: function ()
    {
        this.checkMultiRadioButtonOption();
        $("#multiDrugGcnText").val("");
        $("#multiDrugText").val("");
        $("#multiDrugPriceUpdateTable > tbody").html("");
    }, populateAppUrl: function (appUrl) {
        window.drugestimateprice.appUrl = appUrl;
    }, time_sleep_until: function (milliseconds) {
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds) {
                return true;
                break;
            }
        }
    }
}