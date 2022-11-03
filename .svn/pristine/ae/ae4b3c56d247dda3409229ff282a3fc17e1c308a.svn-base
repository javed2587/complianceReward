/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.pharmacyNotification = {
    pharmacyNotificationHistoryDialogBox: null,
    /////////////////////////////////////////////////////////
    fillHandler: function (div, status)
    {
//        alert("val "+$("#requestTypeFld").val())
        if (status == 8 || $("#requestTypeFld").val() == 'Refill')
        {
//            alert("1");
            $("#filledlnk").click();
        } else
        {
            if (this.validateOrderProcessFields())
            {
                $("#rxdetail3").modal('show');
            }
//            alert("2");
//            this.showEditDrugDiv(div);
        }
    },
    setTextAreaValue: function (txtArea, patient, medicine, type, qty)
    {
        //alert("a");
        $("#" + txtArea).val("Dear " + patient + "\n Following are your drug details:\nMedicine " + medicine + "(" + type + ")\tQty " + qty);
    },
    showEditDrugDiv: function (div)
    {
        $("#" + div).show();
        $("#warnEditDiv").show();
    },
    hideEditDrugDiv: function (div)
    {
        $("#" + div).hide();
    },
    closeDiv: function (divId)
    {
        $("#eventName").val("");
        $("#response").val("");
        $("#" + divId).hide();
        $("#inAppInfo").hide();
    },
    closeInfoDiv: function ()
    {
        //alert("a");
        $("#eventName").val("");
        $("#response").val("");

        $("#inAppInfo").hide();
        $("#customMessageId").val("");
//        $("#customMessageDiv").hide();
//        this.showEditDrugDiv('editMsgButton');
        this.showEditDrugDiv('medicationInfoDiv');
        $("#saveMsgButton").attr("disabled", false);
        $("#editMsgButton").attr("disabled", false);
    },
    closeInfoDivRefill: function ()
    {
        //alert("a");
        $("#eventNameRefill").val("");
        $("#responseRefill").val("");

        $("#inAppInfoRefill").hide();
        // $("#textarea").html("")
        //$("#customMessageDivRefill").hide();
        //this.showEditDrugDiv('editMsgButton');
        //this.showEditDrugDiv('medicationInfoDiv');
        $("#saveMsgButtonRefill").attr("disabled", false);
        // $("#editMsgButton").attr("disabled", false);
    },
    closeInfoDiv2: function (eventFld, responseFld, infoDiv)
    {
        //alert("a");
        $("#" + eventFld).val("");
        $("#" + responseFld).val("");

        $("#" + infoDiv).hide();
        $("#customMessageId2").val("");
        $("#customMessageDiv2").hide();
        this.showEditDrugDiv('editMsgButton2');
        this.showEditDrugDiv('medicationInfoDiv2');
        $("#saveMsgButton2").attr("disabled", false);
        $("#editMsgButton2").attr("disabled", false);
    },
    showEditableArea: function (div, nameFld)
    {
        $("#" + div).show();
        $("#" + nameFld).focus();
    },
    closeEditableArea: function (div)
    {
        $("#" + div).hide();
    },
    setLowerCost: function (v)
    {


        $("#lowerCost").val(v);

    },
    openDrugCanNitFillnDiv: function (medicationModal, msg1, msg2, eventName, resp, fileUpload, delOption, autodelOption)//,drug,qty)
    {
        if ($("#popUpException1") != null)
        {
            $("#popUpException1").hide();
        }
        //alert(" "+drug);
        // $("#medicationLabel").html(drug);//.append(drug);
        //$("#medicationQty").html(qty);
        //$("#"+divId).show();
        $("#" + medicationModal).modal('show');
        $("#msg1").html(msg1);
        $("#msg2").html(msg2);
        $("#eventName").val(eventName);
        ////////////////////////////////////////////////////
        var drugNameDbId = $("#referenceBrandDetail").val();
        var drugType = $("#drugTypeIdDetail").val();
        var drugStrength = $("#drugStrengthDetail").val();
        var rxNumberFld = $("#rxNumberFld").val();
        var drugQtyFld = $("#drugQtyFld").val();
        var daysSupplyFld = $("#daysSupplyFld").val();
        var patientNameFld = '';//$("#patientNameFld").val();
        var pharmacyRxNoFld = $("#pharmacyRxNoFld").val();
        var pharmacyNameFld = $("#pharmacyNameFld").val();
        var pharmacyPhoneFld = $("#pharmacyPhoneFld").val();
        var prescriberNameFld = $("#prescriberNameFld").val();
        var prescriberPhoneFld = $("#prescriberPhoneFld").val();
        var prescriberNPIFld = $("#prescriberNPIFld").val();
        var acqCostFld = $("#acqCostFld").val();
        var reimbFld = $("#reimbFld").val();
        var ptCopayFld = $("#ptCopayFld").val();
        var finalPaymentFld = $("#finalPaymentFeeFld").val();

        var profitMargin = $("#profitMarginCol").val();
        //alert("profit "+profitMargin);
        var profitSharePoint = $("#totalRedeemPointsId").val();//.html();
        //alert("profitSharePoint "+profitSharePoint);
        var actualProfitShare = $("#redeemPointsCostFld").val();//html();
        //alert("actualProfitShare "+actualProfitShare);
        var refillRemain = $("#refillUsedFld").val();
        var estPriceFld = $("#estPriceFld").val();
        var insuranceType = '';
        if ($("#radio-a").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Public";
        } else if ($("#radio-b").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Cash";
        } else if ($("#INS").is(":checked"))
        {
            insuranceType = "Commercial";
        }
        var handlingFee = $("#handlingFeeFld").val();
        var systemGeneratedRxNumber = $("#systemGeneratedRxNumberFld").val();
        var prescriberLastName = $("#prescriberLastNameFld").val();

        ///////////////////////////////////////////////////
        $("#response").val(resp);
        $("#medicationModalDrug").html($("#drugNameIdDetail").val() + ' ' + $("#drugStrengthDetail").val() + ' '
                + $("#drugTypeIdDetail").val());
        $("#medicationModalQty").html($("#drugQtyFld").val());
        if (fileUpload == 1)
        {
//            alert("AAA "+$("#uploadButton"));
            $("#uploadButton2").show();
        }
//        alert("first "+delOption)
        if (delOption != null && delOption == 1)
        {
//            alert("here "+delOption)
            $("#delOption").val("1");
//            alert("last "+$("#delOption").val("1"));
        }

        if (autodelOption != null && autodelOption == 1)
        {
//            alert("here is autodelOption is  "+delOption)
            $("#autodelOption").val("1");
//           
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        var json = {"eventName": eventName, "message": resp, "rxNumberFld": rxNumberFld,
            "name": drugNameDbId, "drugType": drugType,
            "drugStrength": drugStrength, "drugQty": drugQtyFld, "daysSupplyFld": daysSupplyFld, "patientNameFld": patientNameFld,
            "pharmacyRxNoFld": pharmacyRxNoFld, "pharmacyNameFld": pharmacyNameFld,
            "pharmacyPhoneFld": pharmacyPhoneFld, "prescriberNameFld": prescriberNameFld, "prescriberPhoneFld": prescriberPhoneFld,
            "prescriberNPIFld": prescriberNPIFld, "acqCostFld": acqCostFld, "reimbFld": reimbFld, "ptCopayFld": ptCopayFld,
            "finalPaymentFld": finalPaymentFld, "insuranceType": insuranceType,
            "profitMargin": profitMargin,
            "profitSharePoint": profitSharePoint, "actualProfitShare": actualProfitShare,
            "handlingFee": handlingFee, "systemGeneratedRxNumber": systemGeneratedRxNumber,
            "prescriberLastName": prescriberLastName, "estPriceFld": estPriceFld,
            "recievedDateFld": $("#recievedDateFld").val(), "orderId": $("#orderId").val(), "refillRemain": refillRemain,
            "refillAllowedFld": $("#refillAllowedFld").val()};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/retrievecampaign/",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
//                alert("Test Alert by Osman: " + response.auditFields.isCritical);
                var critical = $("#isCritical").is(":checked");
                var isCritical = 0;
                if (critical == true)
                {
                    var isCritical = 1;//.val();
                }
                $("#customMessageDiv").html(data.smstext);

            },
            error: function (e) {
                alert('Error while processing order...' + e.responseText);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
    },
    ////////////////////////////////////////////////////////////////////
    openMultiRxModel: function (mulitRxModel, id, name, pid)//,drug,qty)
    {
//        alert(" "+name);
        // $("#medicationLabel").html(drug);//.append(drug);
        //$("#medicationQty").html(qty);
        //$("#"+divId).show();
        $("#multiPatientName").html(name);
        $("#multiPatientId").val(pid);
//        alert(""+fName);
        //$("#multiPatientId").val(id);
        $("#multiRxTable tbody tr").remove();


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        var json = {"id": id};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/populateMultiRxOrdersDetail/" + id,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
//                alert("Data "+data.ordersDTOList.length)
//                alert("Test Alert by Osman: " + response.auditFields.isCritical);
                //alert("adult "+data.isAdult)

//                appendDataToOrderHistoryTable(data,'dependentHistoryTable');
                $("#multiRxCount").html("Multi-Rx (Y-" + data.length + ")");
                $("#" + mulitRxModel).modal('show');
                var rowCount = 1;
                for (var i = 0; i < data.length; i++)
                {
                    var row = data[i];
                    var drugName = row.drugName;
                    var qty = row.qty;
                    var strength = row.strength;
                    var drugType = row.drugType;

                    var yearsFromOrder = row.year;
                    var monthFromOrder = row.month;
                    var daysFromOrder = row.days;
                    var extraorderval = row.extraordervalue;

                    var daysFromOrdr;
                    if (row.days != null)
                    {
                        daysFromOrdr = 'style=color:red;';
                        var day = "<span " + daysFromOrdr + " >" + daysFromOrder + "</span>";
                    }
//                    alert(day);
                    if (drugName == null)
                    {
                        drugName = '';
                    }
                    if (qty == null)
                    {
                        qty = '-';
                    }
                    if (drugType == null)
                    {
                        drugType = '';
                    }
                    if (strength == null)
                    {
                        strength = '';
                    }
                    var prefStyle = '';
//                    alert(""+row.deliveryPreferencesName)
                    var deliveryPreferencesName = row.deliveryPreferencesName;
                    if (row.deliveryPreferencesName == 'Same Day')
                    {
                        prefStyle = 'style=color:red;';
                        deliveryPreferencesName = "<span class='bold_new'>!*Same<br/>DAY *!</span>";
                    } else if (row.deliveryPreferencesName == 'Next Day*')
                    {
                        deliveryPreferencesName = "<span class='redText'>! *</span>";
                        deliveryPreferencesName += "<span style='color:blue;'>NEXT<br/> DAY</span>";
                        deliveryPreferencesName += "<span class='redText'>*!</span>";
                    } else if (row.deliveryPreferencesName == 'Pick Up at Pharmacy') {
                        deliveryPreferencesName = 'Pick Up<br/>at Pharmacy';
                    }

                    var prefRequestType = 'style=color:red;';
                    var stageOfProcess = row.requestType;
                    if (row.requestType == 'X-FER LABEL SCAN')
                    {
                        //prefRequestType = 'style=color:blue;';
                        stageOfProcess = "NEW";
                    } else if (row.requestType == 'X-FER RX SCAN')
                    {

                        //prefRequestType = 'style=color:green;';
                        stageOfProcess = "NEW";
                    } else
                    {
                        //prefRequestType = 'style=color:red;';
                        prefRequestType = 'style=color:blue;';

                    }

//                     alert(yearsFromOrder+"-"+monthFromOrder+"-"+daysFromOrder+"-"+extraorderval);
                    var url = window.pharmacyNotification.appUrl + "/ConsumerPortal/rxDetail/" + row.id + "/" + $("#multiPatientId").val();
                    if (row.careGiver == 1)
                    {
                        url = window.pharmacyNotification.appUrl + "/PharmacyPortal/careGiverDetail/" + row.dependentId + "?pq=1";
                    }
                    var rxnumber = "--";
                    if (row.systemGeneratedRxNumber.length > 0) {
                        rxnumber = "<span class='bold_new'>RXD</span>" + row.systemGeneratedRxNumber;
                    }
                    var orderStatus = row.orderStatusName;
                    if (row.orderStatusName == 'Pending') {
                        orderStatus = "NEW MBR<br/>REQUEST";
                    } else if (row.orderStatusName == 'Pending Review') {
                        orderStatus = "INTERPRETED<br/>IMAGES";
                    } else if (row.orderStatusName == 'Waiting Pt Response') {
                        orderStatus = "AWAITING PT<br/>RESPONSE";
                    } else if (row.orderStatusName == 'Response Received') {
                        orderStatus = "INCOMMING PT<br/>RESPONSES";
                    } else if (row.orderStatusName == 'Filled') {
                        orderStatus = "PROCESSED PT<br/>REQUEST";
                    } else if (row.orderStatusName == 'Shipped') {
                        orderStatus = "SHIPPING<br/>QUEUE";
                    } else if (row.orderStatusName == 'DELIVERY') {
                        orderStatus = "DELIVERY<br/>QUEUE";
                    }
                    var newRowContent = "<tr><td><span class='redText bold_new' style='float:left;'>" + rowCount + "</span><a href=" + url + " style=\"color: blue;\">" + yearsFromOrder + "" + monthFromOrder + "<br>" + day + extraorderval + "</td>"
                            + "<td>" + row.statusPostedDate + "</td>"
                            + "<td " + prefStyle + ">" + deliveryPreferencesName + "</td>"
                            + "<td >" + drugName + ' ' + drugType + ' ' + strength + "</td>"
                            + "<td>" + orderStatus + "</td>"
                            + "<td>" + rxnumber + "</td>"
                            + "<td " + prefRequestType + ">" + stageOfProcess + "</td>"
                            + "<td>" + row.zipCode + "</td>"
                            + "</tr>";
                    $("#multiRxTable tbody").append(newRowContent);
                    rowCount++;
                }

            },
            error: function (e) {
                alert('Error while processing ...' + e.responseText);
            }
        });

    },
    ////////////////////////////////////////////////////////////////////
    openProgramRxModel: function (mulitRxModel, id, name, pid)//,drug,qty)
    {
//        alert(" ttt");
        // $("#medicationLabel").html(drug);//.append(drug);
        //$("#medicationQty").html(qty);
        //$("#"+divId).show();

        $("#programPatientName").html(name);
        $("#programPatientId").val(pid);
//        alert(""+fName);
        //$("#multiPatientId").val(id);
        $("#programRxTable tbody tr").remove();


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        var json = {"id": id};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));
//        $("#programRxTable").dataTable().fnDestroy();
//        this.initializeDataTable('programRxTable','100px');
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/populateProcessedOrdersDetail/" + id,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
//                alert("Data "+data.ordersDTOList.length)
//                alert("Test Alert by Osman: " + response.auditFields.isCritical);
                //alert("adult "+data.isAdult)

//                appendDataToOrderHistoryTable(data,'dependentHistoryTable');
                $("#programRxCount").html("ALL PROGRAM RX's (" + data.length + ")");
                for (var i = 0; i < data.length; i++)
                {
                    var row = data[i];
                    var drugName = row.drugName;
                    var qty = row.qty;
                    var strength = row.strength;
                    var drugType = row.drugType;
                    if (drugName == null)
                    {
                        drugName = '';
                    }
                    if (qty == null)
                    {
                        qty = '-';
                    }
                    if (drugType == null)
                    {
                        drugType = '';
                    }
                    if (strength == null)
                    {
                        strength = '';
                    }
                    var prefStyle = '';
//                    alert(""+row.deliveryPreferencesName)
                    var colour;
                    if (row.requestType == "X-FER LABEL SCAN")
                    {
                        colour = 'style=color:blue;';
                    } else if (row.requestType == "X-FER RX SCAN")
                    {
                        colour = 'style=color:green;';
                    } else {
                        colour = 'style=color:red;';
                    }
                    var deliveryPreferencesName = row.deliveryPreferencesName;
                    var chkBox = "";
                    var chkId = "rxNumber-" + row.id;
                    if (row.deliveryPreferencesName == 'Same Day')
                    {
                        deliveryPreferencesName = "<span style='color:red;'>!*Same<br/>DAY *!</span>";
                        //Todo
                        // alert(row.statusId);
//                        if (row.orderStatusName == 'Filled') {
//
//                            $("#divSameDayOrd").removeAttr("style");
//                            $("#chkAllSameDay").removeAttr("style");
//                            chkBox = "<input id=" + chkId + " type='checkbox' value=" + row.id + " class='left' name='allProgramRxChk' onchange='window.pharmacyNotification.populateAllProgramRxComboValue();'/>";
//                        }

                    } else if (row.deliveryPreferencesName == 'Next Day*')
                    {
                        deliveryPreferencesName = "<span class='redText'>! *</span>";
                        deliveryPreferencesName += "<span style='color:blue;'>NEXT<br/> DAY</span>";
                        deliveryPreferencesName += "<span class='redText'>*!</span>";
                    } else if (row.deliveryPreferencesName == 'Pick Up at Pharmacy') {
                        deliveryPreferencesName = 'Pick Up<br/>at Pharmacy';
                    }
                    var url = window.pharmacyNotification.appUrl + "/ConsumerPortal/rxDetail/" + row.id + "/" + $("#patientId").val();
                    if (row.careGiver == 1)
                    {
                        url = window.pharmacyNotification.appUrl + "/PharmacyPortal/careGiverDetail/" + row.dependentId + "?pq=1";
                    }
                    var paymentMode = row.paymentMode;
                    if (paymentMode.length > 0 && paymentMode == 'INSURANCE') {
                        paymentMode = "INS";
                    } else {
                        paymentMode = "<span class='redText'>N</span>";
                    }
                    var rxnumber = "--";
                    if (row.systemGeneratedRxNumber.length > 0) {
                        rxnumber = "<span class='bold_new'>RXD-<br/></span>" + row.systemGeneratedRxNumber;
                    }
                    var dateValue = row.orderDate;

                    var yearPart = "", monthpart = "", dayspart = "";

                    monthpart = dateValue.slice(0, 2);

                    dayspart = dateValue.slice(3, 5);

                    yearPart = dateValue.slice(6, 10);

                    var newRowContent = "<tr><td class='rxNumber'>" + chkBox + rxnumber + "</td>"
                            + "<td class='dateCompletedField'><a href=" + url + ">" + yearPart + "-" + monthpart + "-<span class='redText'>" + dayspart + "</span></a></td>"
//                          + "<td style=\"color:red;font-weight:bold\">" + row.requestType + "</td>"

                            + "<td >" + drugName + ' ' + drugType + ' ' + strength + "</td>"
                            + "<td>" + qty + "</td>"
                            + "<td>" + paymentMode + "</td>"
                            + "<td>" + row.finalPaymentStr + "</td>"
                            + "<td>" + deliveryPreferencesName + "</td>"
                            + "<td>" + row.zipCode + "</td>"
                            + "<td " + colour + " >" + row.requestType + "</td>"
                            + "</tr>";
                    $("#programRxTable tbody").append(newRowContent);
                }
                $("#" + mulitRxModel).modal('show');
            },
            error: function (e) {
                alert('Error while processing ...' + e.responseText);
            }
        });

    },

    /////////////////////////////////////////////////////////////////////////
    openSamePrefRxModel: function (mulitRxModel, id, name, pid)//,drug,qty)
    {
        //this.initializeDataTable('deliveryPrefTable');

//        alert(" "+name);
        // $("#medicationLabel").html(drug);//.append(drug);
        //$("#medicationQty").html(qty);
        //$("#"+divId).show();
        $("#" + mulitRxModel).modal('show');
        $("#samePrefPatientName").html(name);
        $("#samePrefPatientId").val(pid);
//        alert(""+fName);
        //$("#multiPatientId").val(id);
        $("#deliveryPrefTable tbody tr").remove();


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        var json = {"id": id};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));
//        $("#programRxTable").dataTable().fnDestroy();
//        this.initializeDataTable('programRxTable','100px');
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/populateOrdersWithSameDeliverPref/" + id,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
//                alert("Data "+data.ordersDTOList.length)
//                alert("Test Alert by Osman: " + response.auditFields.isCritical);
                //alert("adult "+data.isAdult)

//                appendDataToOrderHistoryTable(data,'dependentHistoryTable');
                for (var i = 0; i < data.length; i++)
                {
                    var row = data[i];
                    var drugName = row.drugName;
                    var qty = row.qty;
                    var strength = row.strength;
                    var drugType = row.drugType;
                    if (drugName == null)
                    {
                        drugName = '';
                    }
                    if (qty == null)
                    {
                        qty = '-';
                    }
                    if (drugType == null)
                    {
                        drugType = '';
                    }
                    if (strength == null)
                    {
                        strength = '';
                    }
                    var prefStyle = '';
//                    alert(""+row.deliveryPreferencesName)
                    if (row.deliveryPreferencesName == 'Same Day')
                    {
                        prefStyle = 'style=color:red;';
                    } else if (row.deliveryPreferencesName == 'Next Day*')
                    {
                        prefStyle = 'style=color:blue;';
                    }
                    var url = window.pharmacyNotification.appUrl + "/ConsumerPortal/rxDetail/" + row.id + "/" + $("#patientId").val();
                    if (row.careGiver == 1)
                    {
                        url = window.pharmacyNotification.appUrl + "/PharmacyPortal/careGiverDetail/" + row.dependentId + "?pq=1";
                    }
                    var newRowContent = "<tr><td><a href=" + url + " style=\"color: blue;\" >" + row.id + "</td>"
                            + "<td>" + row.orderDate + "</td>"
                            + "<td style=\"color:red;font-weight:bold\">" + row.requestType + "</td>"
                            + "<td>" + row.handlingFeeStr + "</td>"
                            + "<td >" + drugName + ' ' + drugType + ' ' + strength + "</td>"
                            + "<td>" + qty + "</td>"
                            + "<td " + prefStyle + ">" + row.deliveryPreferencesName + "</td>"
                            + "<td>" + row.requestType + "</td>"
                            + "</tr>";
                    $("#deliveryPrefTable tbody").append(newRowContent);
                }

            },
            error: function (e) {
                alert('Error while processing ...' + e.responseText);
            }
        });

    },
    ////////////////////////////////////////////////////////////////////////

    initializeDataTable: function (table, maxHeight, sortingCoumns, defSortColumn) {
        $('#' + table).dataTable({
            "sPaginationType": "full_numbers",
            "bLengthChange": false, //thought this line could hide the LengthMenu
            "searching": false,
            "bInfo": false,
            "bSort": false,
            "scrollY": "50px",
            "scrollX": true
        });
        $('#' + table).css({"width": "100%"});
    },

    ////////////////////////////////////////////////////////////////////
    openShippingModel: function (mulitRxModel, id, name, pid)//,drug,qty)
    {
        alert(" " + name);
        // $("#medicationLabel").html(drug);//.append(drug);
        //$("#medicationQty").html(qty);
        //$("#"+divId).show();
        $("#" + mulitRxModel).modal('show');
        $("#multiPatientName").html(name);
        $("#multiPatientId").val(pid);
//        alert(""+fName);
        //$("#multiPatientId").val(id);
        $("#multiRxTable tbody tr").remove();


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        var json = {"id": id};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/populateShippingQueue/" + id,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
//                alert("Data "+data.ordersDTOList.length)
//                alert("Test Alert by Osman: " + response.auditFields.isCritical);
                //alert("adult "+data.isAdult)

//                appendDataToOrderHistoryTable(data,'dependentHistoryTable');
                for (var i = 0; i < data.length; i++)
                {
                    var row = data[i];
                    var drugName = row.drugName;
                    var qty = row.qty;
                    var strength = row.strength;
                    var drugType = row.drugType;
                    if (drugName == null)
                    {
                        drugName = '';
                    }
                    if (qty == null)
                    {
                        qty = '-';
                    }
                    if (drugType == null)
                    {
                        drugType = '';
                    }
                    if (strength == null)
                    {
                        strength = '';
                    }
                    var prefStyle = '';
//                    alert(""+row.deliveryPreferencesName)
                    if (row.deliveryPreferencesName == 'Same Day')
                    {
                        prefStyle = 'style=color:red;';
                    } else if (row.deliveryPreferencesName == 'Next Day*')
                    {
                        prefStyle = 'style=color:blue;';
                    }
//                    debuger;
//                    alert('sss')
                    var prefRequestType;
                    if (row.requestType == 'X-FER LABEL SCAN')
                    {
                        prefRequestType = 'style=color:blue;';
                    } else if (row.requestType == 'X-FER RX SCAN')
                    {

                        prefRequestType = 'style=color:green;';
                    } else
                    {
                        prefRequestType = 'style=color:red;';
                    }

                    var url = window.pharmacyNotification.appUrl + "/ConsumerPortal/rxDetail/" + row.id + "/" + $("#multiPatientId").val();
                    if (row.careGiver == 1)
                    {
                        url = window.pharmacyNotification.appUrl + "/PharmacyPortal/careGiverDetail/" + row.dependentId + "?pq=1";
                    }
                    var newRowContent = "<tr><td><a href=" + url + " style=\"color: blue;\" >RXD-" + row.id + "</td>"
                            + "<td>" + row.orderDate + "</td>"
                            + "<td >" + drugName + ' ' + drugType + ' ' + strength + "</td>"
                            + "<td>" + qty + "</td>"
                            + "<td " + prefStyle + ">" + row.deliveryPreferencesName + "</td>"
                            + "<td " + prefRequestType + ">" + row.requestType + "</td></tr>";
                    $("#multiRxTable tbody").append(newRowContent);
                }

            },
            error: function (e) {
                alert('Error while processing ...' + e.responseText);
            }
        });

    },
    ////////////////////////////////////////////////////////////////////
    openDependentModel: function (dependentModal, id, fName, lName, dob, gender)//,drug,qty)
    {
        //alert(" "+drug);
        // $("#medicationLabel").html(drug);//.append(drug);
        //$("#medicationQty").html(qty);
        //$("#"+divId).show();
        $("#" + dependentModal).modal('show');
//        alert(""+fName);
        $("#dependentId").val(id);
        $("#dependentNameFld").html(fName + ' ' + lName);
        $("#dobFld").html(dob);
        $("#genderFld").html(gender);

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        var json = {"id": id};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/populateDependentDetail/" + id,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
//                alert("Data "+data.ordersDTOList.length)
//                alert("Test Alert by Osman: " + response.auditFields.isCritical);
                //alert("adult "+data.isAdult)
                $("#adultFld").html(data.isAdult == true ? 'Yes' : 'No');
                $("#approvalDateFld").html(data.approvalDateStr);
                $("#expiryDateFld").html(data.expiryDateStr);
                $("#ageFld").html(data.age);
                $("#approvedFld").html(data.approvalStr);
                $("#dependentAllergiesDiv").html(data.allergies);
//                appendDataToOrderHistoryTable(data,'dependentHistoryTable');
                for (var i = 0; i < data.ordersDTOList.length; i++)
                {
//            alert("Data id "+)
                    //var date=new Date(data.ordersDTOList[i].filledDate);
                    //alert("date "+date+" parts "+date.getYear()+"-"+date.getMonth()+"-"+date.getDay());
                    var row = data.ordersDTOList[i];
                    var url = window.pharmacyNotification.appUrl + "/ConsumerPortal/rxDetail/" + row.id + "/" + $("#patientId").val();
                    var newRowContent = "<tr><td><a href='" + url + "' style=\"color: blue;\" >RXD-" + row.systemGeneratedRxNumber + "</td>"
                            + "<td>" + row.filledDateStr + "</td>"
                            + "<td>" + row.drugName + ' ' + row.drugType + ' ' + row.strength + "</td>"
                            + "<td>" + row.qty + "</td>"
                            + "<td>" + row.daysSupply + "</td>"
                            + "<td>" + row.daysToRefill + "</td>"
                            + "<td>" + row.refillsRemaining + "</td>"
                            + "<td>" + row.requestType + "</td>"
                            + "<td>" + row.selfPayCheck + "</td>"
                            + "<td>" + row.publicInsCheck + "</td>"
                            + "<td>" + row.finalPaymentStr + "</td>"
                            + "<td>" + row.redeemPoints + "</td></tr>";
                    $("#dependentHistoryTable tbody").append(newRowContent);
                }

            },
            error: function (e) {
                alert('Error while processing ...' + e.responseText);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
    },
    ////////////////////////////////////////////////////////////////////
    appendDataToOrderHistoryTable: function (data, tableId)
    {
        for (var i = 0; i < data.length; i++)
        {
            var newRowContent = "<tr><td>" + data.systemGeneratedRxNumber + "</td></tr>";
            $("#" + tableId + " tbody").append(newRowContent);
        }
    },
    ////////////////////////////////////////////////////////////////////
    openDrugRefillTooSoonDiv: function (medicationModal, msg1, msg2, eventName, resp)//,drug,qty)
    {
        //alert(" "+drug);
        // $("#medicationLabel").html(drug);//.append(drug);
        //$("#medicationQty").html(qty);
        //$("#"+divId).show();
        $("#" + medicationModal).modal('show');
        $("#msg1Refill").html(msg1);
        $("#msg2Refill").html(msg2);
        $("#eventNameRefill").val(eventName);
        $("#responseRefill").val(resp);
        $("#medicationModalRefillDrug").html($("#drugNameIdDetail").val() + ' ' + $("#drugStrengthDetail").val() + ' ' + $("#drugTypeIdDetail").val());
        //$("#medicationModalRefillType").html($("#drugTypeId").val());
        $("#medicationModalRefillQty").html($("#drugQtyFld").val());
    },
    truncateFldValue: function (fld, numberOfCharactersToTruncate, front, stringToEliminate)
    {
        var str = $("#" + fld).val();
        if (front == 1)
        {
            if (str.startsWith(stringToEliminate))
            {
                str = str.substring(numberOfCharactersToTruncate);
                $("#" + fld).val(str);
            }

        }
        $("#" + fld).val(str.replace(/,/, ""));//to replace commas from numeric value
    },
    appendFldValue: function (fld, front, stringToAppend, decimalFlag)
    {
        var str = $("#" + fld).val();
        if (front == 1)
        {
            //alert("abc "+str+" front "+str.startsWith(stringToAppend) )
            if (!str.startsWith(stringToAppend))
            {
//                alert("a");
                str = stringToAppend + str;
            }
            //alert("Str111 "+str);
            str = this.formatPriceToTwoDecimalPlaces2(str);
            //alert("Str2 "+str);
            $("#" + fld).val(str);


        }
        this.addCommas(fld, decimalFlag);
    },
    //////////////////////////////////////////////////
    formatPriceToTwoDecimalPlaces2: function (val)
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
    /////////////////////////////////////////////////
    eliminateCurrencySignFromNumricFlds: function ()
    {
        this.truncateFldValue('acqCostFld', 1, 1, '$');
        this.truncateFldValue('ptCopayFld', 1, 1, '$');
        this.truncateFldValue('reimbFld', 1, 1, '$');
        this.truncateFldValue('sellingPriceFld', 1, 1, '$');
        this.truncateFldValue('redeemPointsCostFld', 1, 1, '$');
        this.truncateFldValue('totalRedeemPointsId', 1, 1, '$');
        this.truncateFldValue('finalPaymentFeeFld', 1, 1, '$');
//        this.truncateFldValue('handlingFeeFld', 1, 1, '$');
        //this.truncateFldValue('actualProfitShare',1,1,'$');
    },
    appendCurrencySignFromNumricFlds: function ()
    {
        this.appendFldValue('acqCostFld', 1, '$', 1);
        this.appendFldValue('ptCopayFld', 1, '$', 1);
        this.appendFldValue('reimbFld', 1, '$', 1);
        this.appendFldValue('sellingPriceFld', 1, '$', 1);
        this.appendFldValue('totalRedeemPointsId', 1, '', 0);
        this.appendFldValue('redeemPointsCostFld', 1, '$', 1);
        this.appendFldValue('finalPaymentFeeFld', 1, '$', 1);
//        this.appendFldValue('handlingFeeFld', 1, '$');
        //this.truncateFldValue('actualProfitShare',1,1,'$');
    },
    calculateProfit: function (reimbFld, acqFld)
    {
        this.eliminateCurrencySignFromNumricFlds();
        //alert("A");
        reimb = parseFloat($("#" + reimbFld).val());//parseFloat(document.getElementById(reimbFld).val());
        //alert("B "+reimb);
        acq = parseFloat($("#" + acqFld).val());
        // alert("c "+acq);
        if (acq > reimb)
        {
//            alert("Acq cost shouldn't be greater than selling peice.");
            $("#popUpException").text("Acq cost shouldn't be greater than selling peice.");
            $("#popUpException").attr("style", "color:red");
            $("#messagePopUpModal").modal('show');
//                   $("#ptCopayFld").focus();
            //$("#" + reimbFld).focus();
            return;
        }
        var subtract = reimb - acq;
        $("#profitMarginCol").val(Math.round(subtract * 100) / 100);
        this.appendCurrencySignFromNumricFlds();

    },
    calculateReimbursementAndProfit: function (sellingPriceFld, copayFld, ingCostFld)
    {
        this.eliminateCurrencySignFromNumricFlds();
        //alert("A");
        //selling = parseFloat($("#" + sellingPriceFld).val());//parseFloat(document.getElementById(reimbFld).val());
        //alert("B "+reimb);
        copay = parseFloat($("#" + copayFld).val());
        //alert("copay vale is "+copay);
        acq = parseFloat($("#" + ingCostFld).val());
        //alert("acq vale is "+acq);
        reimb = parseFloat($("#reimbFld").val());
        //alert("reimb vale is "+reimb);
        // alert("c "+acq);
//        if (copay > reimb)
//        {
//            alert("OOP/Copay shouldn't be greater than Selling Price Cost");
//            //$("#" + reimbFld).focus();
//            return;
//        }
//        var subtract = selling - copay;
        var selling = reimb + copay;
        //alert("selling  reimb + copay "+selling);
        $("#sellingPriceFld").val(Math.round(selling * 100) / 100);
        //alert("imb "+subtract);
//        $("#reimbFld").val(Math.round(subtract * 100) / 100);
//        if (acq > reimb)
//        {
//            alert("Acq cost shouldn't be greater than selling peice.");
//            //$("#" + reimbFld).focus();
//            return;
//        }
        var profit = selling - acq;
        if (profit < 0)
        {
            profit = 0;
        }
        //alert("profit "+profit)
        $("#profitMarginCol").val(Math.round(profit * 100) / 100);
        window.transferRequest.lookUpProfitCalculation();
        this.appendCurrencySignFromNumricFlds();
//        this.addCommas(ingCostFld);
//        this.addCommas(copayFld	); 
//        this.addCommas('reimbFld'); 
//        this.addCommas('sellingPriceFld');
//        this.addCommas('profitMarginCol');
//        this.addCommas('redeemPointsCostFld');
//        this.addCommas('finalPaymentFeeFld');
    },
    calculateFinalPay: function (ptCopayFld, redeemPointsCostCol, handlingFeeCol, finalPtFeeCol)
    {
        //alert("A");
        this.eliminateCurrencySignFromNumricFlds();
        ptCopay = parseFloat($("#" + ptCopayFld).val());
        //alert("B "+ptCopay);
        if ($("#" + redeemPointsCostCol).html() == '')
        {
            $("#" + redeemPointsCostCol).html("0");
        }
        redeemCost = parseFloat($("#" + redeemPointsCostCol).html());
        //alert("c "+redeemCost)
        if ($("#" + handlingFeeCol).html() == '')
        {
            $("#" + handlingFeeCol).html("0");
        }
//        handlingCost = parseFloat($("#" + handlingFeeCol).html());
        //alert("d "+handlingCost)
        var cal = ptCopay - redeemCost;// + handlingCost;
        $("#finalPaymentFeeFld").val(Math.round(cal * 100) / 100);

        //   $("#" + finalPtFeeCol).html(ptCopay - redeemCost + handlingCost);

    },
    changeStatusButtonStyles: function (pendingStyle, delayedStyle, filledStyle, courierStyle, shippedStyle, holdStyle)
    {
        $("#pendingLnk").attr("class", pendingStyle);
        $("#delayedLnk").attr("class", delayedStyle);
        $("#filledlnk").attr("class", filledStyle);
        $("#courierLnk").attr("class", courierStyle);
        $("#shippedLnk").attr("class", shippedStyle);
        $("#holdLnk").attr("class", holdStyle);
    },
    displayStatusErrMsg: function (msg)
    {
        $("#statusException").text(msg);
        $("#statusException").attr("style", "color:red; font-weight:bold;display:block;");
        $("#statusBit").val("0");
    },
    /////////////////////////////////////////////////////////////////
    checkCancelStatusBitHandler: function (statusBit, nextStatus)
    {
        var currStatus1 = $("#orderStatusSpan").html();
        this.hideMsg("popUpException");

        $("#cancelStatusConfirmBoxBtn").text('');
        $("#popUpException").text('');
        $("#confirmCancelStatusDiv").modal('show');
        $("#statusBit").val(statusBit);
    },
    ////////////////////////////////////////////////////////////////
    checkCancelStatusBit: function (divId, msg1, msg2, eventName, resp)// statusBit)
    {
        var lnk = $("#clear-button");
        //var flag = confirm("Do you want to update this order");
//        var flag = $("#popUpException").text("Do you want to update this order");

        $("#cancelStatusConfirmBoxBtn").click();
//        if (flag == false)
//            return;
        var statusBit = $("#statusBit").val();
        this.processOrder(1, statusBit);
    },
    //////////////////////////////////////////////////////////////////////////
    checkStatusBit: function (statusBit, nextStatus)
    {


        var statusBitt2 = $("#statusBit").val();
        if (statusBitt2 == 8)
        {
            this.checkOtherStatusBitHandler(statusBit, nextStatus);
        } else {
            this.checkOtherStatusBitHandler2(statusBit);

        }
    },
    checkOtherStatusBitHandler2: function (statusBit)
    {

        if (statusBit == 5 || statusBit == 6 || statusBit == 15)
        {
            if ($("#popUpException1") != null)
            {
                $("#popUpException1").hide();

            }
            if ($("#traclingno").val() == '')
            {

                $("#popUpException").text("Please enter Tracking number.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                //                 $("#messagePopUpModal").modal('show');
                $("#traclingno").focus();
                return false;
            }


            if ($("#clerk").val() == '')
            {

                $("#popUpException").text("Please enter Clerk.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                //                 $("#messagePopUpModal").modal('show');
                $("#clerk").focus();
                return false;
            }
            var re = /[a-zA-Z]/;
            if (!re.test($("#clerk").val()))
            {
                $("#popUpException").text("Clerk should contain at least one alphabet.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                $("#clerk").focus();
                return false;
            }
            if ($("#datetimepicker2").val() == '')
            {

                $("#popUpException").text("Please enter ship date.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                $("#datetimepicker2").focus();
                return false;
            }
            var fillDate = ($("#filledate").val());

            var shippDate = ($("#datetimepicker2").val());

            //alert(this.fn_DateCompare(shippDate,fillDate));

            if (this.fn_DateCompare(shippDate, fillDate) == -1)
            {
                //            alert('else exception');
                $("#popUpException").text("Shipped date should be grater or equal filled date" + "(" + fillDate + ")");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                //                 $("#messagePopUpModal").modal('show');
                $("#shipDate").focus();
                //               $("#popUpException").modal('hide');
                return false;
            }
        }
        $("#statusBit").val(statusBit);
        if (statusBit == 6 && $("#multiRxScreen").val() == 1) {
            this.processMultiRxDeliveryOrder(statusBit);
        } else {
            this.processOrder(1, statusBit);
        }

        return true;
    },
    /////////////////////////////////////////////////////////////////
    checkOtherStatusBitHandler: function (statusBit, nextStatus)
    {


        if (statusBit != 17 && this.validateOrderProcessFields() == false)
        {
            return;
        }
        if (statusBit == 8 && $("#systemGeneratedRxNumberFld").val().trim() == '')
        {
            $("#popUpException").text("Please specify System Generated Rx#.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            $("#systemGeneratedRxNumberFld").focus();
            return false;
        }
        var currStatus1 = $("#orderStatusSpan").html();
        this.hideMsg("popUpException");
        this.hideMsg("popUpException1");
        if (currStatus1.toLowerCase() == nextStatus.toLowerCase())
        {
            if (currStatus1 != 'Waiting Pt Response')
            {
                this.displayStatusErrMsg("Order status is already " + nextStatus);
                $("#cancelOtherStatusConfirmBoxBtn").click();
                return;
            }
        }
        $("#statusException").text('');
        $("#popUpException").text('');
        //$("#confirmOtherStatusDiv").modal('show');
        $("#statusBit").val(statusBit);
        this.checkOtherStatusBit('medicationModal2', '', 'Processing Order', 'RX Order',
                'Order Processed');
    },
    checkOtherStatusBit: function (divId, msg1, msg2, eventName, resp)// statusBit)
    {
        var lnk = $("#clear-button");
        //var flag = confirm("Do you want to update this order");
//        var flag = $("#popUpException").text("Do you want to update this order");

        $("#cancelOtherStatusConfirmBoxBtn").click();
//        if (flag == false)
//            return;
        var statusBit = $("#statusBit").val();
        if (statusBit == 11)
        {
            this.processOrder(1, 11);
            return;
        }
//        alert("Status Bit "+statusBit);
        if (statusBit != 17 && this.validateOrderProcessFields() == false)
            return;

        //$("#statusBit").val(statusBit);
//        var statusBit = $("#statusBit").val();
        //alert("status "+statusBit);
        var currStatus = $("#orderStatusSpan").html();
        //alert("val "+currStatus);
        if (statusBit == 3)
        {
            //alert("3");
            if (currStatus == 'Pending' || currStatus.toLowerCase() == 'shipped' || currStatus == 'DELIVERY')
            {
                this.displayStatusErrMsg("Order status is " + currStatus + " & can't be set to Pending Phmcy Reply.");
                return;
            }
            this.processOrder(0, "PENDING PHARMACY REPLY");
//            window.pharmacyNotification.changeStatusButtonStyles("btn btn_one", "btn btn_two", "btn btn_two", "btn btn_two", "btn btn_two");
        } else if (statusBit == 4)
        {
            //alert("4");
            this.processOrder(0, "DELAYED / RX ON ORDER");
            window.pharmacyNotification.changeStatusButtonStyles("btn btn_two", "btn btn_one", "btn btn_two", "btn btn_two", "btn btn_two");
        } else if (statusBit == 5)
        {
            this.hideMsg("statusException");
            //alert("5");
            this.processOrder(0, "Waiting Pt Response");

            this.disableAllFields();


        } else if (statusBit == 6)
        {
//            if (currStatus != 'Filled')
//            {
//                this.displayStatusErrMsg("Order status should be filled before shipping an order.");
//                return;
//            }
            if ($("#deliverycarrier").val() == "") {
//                alert("Please select a carrier.");
                this.displayStatusErrMsg("Please select a carrier.");
//                $("#popUpException").text("Please select a carrier.");
//                $("#popUpException").attr("style", "color:red");
//                 $("#messagePopUpModal").modal('show');
//            $("#drugQtyFld").focus();
                return false;
            }
            if ($("#clerk").val() == "") {
                this.displayStatusErrMsg("Please select a clerk.");
//                alert("Please select a clerk.");
//                $("#popUpException").text("Please select a clerk.");
//                $("#popUpException").attr("style", "color:red");
//                 $("#messagePopUpModal").modal('show');
                return false;
            }
            this.hideMsg("statusException");
            //alert("6");
            this.processOrder(0, "SHIPPED");
//            $("#processingLnk").attr("disabled", true);
//            $("#pendingLnk").attr("disabled", true);
//            $("#filledlnk").attr("disabled", true);
//            $("#courierLnk").attr("disabled", true);
//            $("#shippedLnk").attr("disabled", true);
//            $("#saveBtn").attr("disabled", true);
//            $("#paymentLnk").attr("disabled", true);
            this.disableAllFields();
//            $("#deliverycarrier").attr("disabled", true);//1
//            $("#traclingno").attr("disabled", true);//2
//            $("#clerk").attr("disabled", true);//3

            //window.pharmacyNotification.changeStatusButtonStyles("btn btn_two", "btn btn_two", "btn btn_two", "btn btn_one", "btn btn_two");
        } else if (statusBit == 15)
        {
//            if (currStatus != 'Filled')
//            {
//                this.displayStatusErrMsg("Order status should be filled before Pickup an order.");
//                return;
//            }
            if ($("#deliverycarrier").val() == "") {
//                alert("Please select a carrier.");
                this.displayStatusErrMsg("Please select a carrier.");
//                $("#popUpException").text("Please select a carrier.");
//                $("#popUpException").attr("style", "color:red");
//                 $("#messagePopUpModal").modal('show');
//            $("#drugQtyFld").focus();
                return false;
            }
            if ($("#clerk").val() == "") {
                this.displayStatusErrMsg("Please select a clerk.");
//                alert("Please select a clerk.");
//                $("#popUpException").text("Please select a clerk.");
//                $("#popUpException").attr("style", "color:red");
//                 $("#messagePopUpModal").modal('show');
                return false;
            }
            this.hideMsg("statusException");
            //alert("6");
            this.processOrder(0, "PICKUP FROM PHARMACY");
//            $("#processingLnk").attr("disabled", true);
//            $("#pendingLnk").attr("disabled", true);
//            $("#filledlnk").attr("disabled", true);
//            $("#courierLnk").attr("disabled", true);
//            $("#shippedLnk").attr("disabled", true);
//            $("#saveBtn").attr("disabled", true);
//            $("#paymentLnk").attr("disabled", true);
            this.disableAllFields();
//            $("#deliverycarrier").attr("disabled", true);//1
//            $("#traclingno").attr("disabled", true);//2
//            $("#clerk").attr("disabled", true);//3

            //window.pharmacyNotification.changeStatusButtonStyles("btn btn_two", "btn btn_two", "btn btn_two", "btn btn_one", "btn btn_two");
        } else if (statusBit == 7)
        {
            //alert("7");
            this.processOrder(0, "ON HOLD");
            window.pharmacyNotification.changeStatusButtonStyles("btn btn_two", "btn btn_two", "btn btn_two", "btn btn_two", "btn btn_one");
        } else if (statusBit == 8)
        {
            //alert("8");
            if (currStatus.toLowerCase() == 'shipped' || currStatus == 'DELIVERY' ||
                    currStatus == 'Ready to Fill' || currStatus == 'Filled' ||
                    currStatus == 'Cancelled' || currStatus == 'Waiting payment authorization' ||
                    currStatus == 'Fill Request Accepted')
            {
                this.displayStatusErrMsg("Order status is " + currStatus + " & can't be Dispensed.");
                return;
            }
            this.hideMsg("statusException");
            //////////////////////////////////////
            this.processOrder(0, "Filled");
//            $("#processingLnk").attr("disabled", true);
//            $("#pendingLnk").attr("disabled", true);
//            $("#filledlnk").attr("disabled", true);
//            $("#courierLnk").attr("disabled", false);
//            $("#shippedLnk").attr("disabled", false);
//            $("#saveBtn").attr("disabled", true);
//            $("#paymentLnk").attr("disabled", true);
            var sysRx = $("#systemGeneratedRxNumberFld").val();
//            alert("A "+sysRx)
            $("#sysRxNoLabel").html(sysRx);
            $("#rxNumberFld").attr("disabled", true);//1
            $("#drugNameId").attr("disabled", true);//2
            $("#drugNameId").attr("disabled", true);//3
            $("#drugTypeId").attr("disabled", true);//4
            $("#drugStrength").attr("disabled", true);//5
            $("#drugQtyFld").attr("disabled", true);//6
            $("#daysSupplyFld").attr("disabled", true);//7
            $("#refillAllowedFld").attr("disabled", true);//8
            $("#refillUsedFld").attr("disabled", true);//9
            $("#paymentFld").attr("disabled", true);//10
            $("#pharmacyNameFld").attr("disabled", true);//11
            $("#pharmacyPhoneFld").attr("disabled", true);//13
            $("#prescriberNameFld").attr("disabled", true);//14
            $("#prescriberPhoneFld").attr("disabled", true);//15
            $("#prescriberNPIFld").attr("disabled", true);//16
            $("#acqCostFld").attr("disabled", true);//17
            $("#reimbFld").attr("disabled", true);//18
            $("#ptCopayFld").attr("disabled", true);//19
            $("#datetimepicker").attr("disabled", true);
            $("#sellingPriceFld").attr("disabled", true);
            $("#profitMarginCol").attr("disabled", true);
            $("#saveBtn").attr("disabled", true);
            $("#radio-a").attr("disabled", true);
            $("#radio-b").attr("disabled", true);
            $("#deliverycarrier").attr("disabled", false);//1
            $("#traclingno").attr("disabled", false);//2
            $("#clerk").attr("disabled", false);//3


        } else if (statusBit == 9)
        {
            //alert("9");
            //alert("10");
//            if (currStatus != 'Processing')
//            {
//                this.displayStatusErrMsg("To Authorize Payment, order status should be Processing.");
//                return;
//            }
            this.hideMsg("statusException");
            this.processOrder(0, "Waiting Pt Response");

            if (lnk != null)
            {
                lnk.hide();
            }

        }

        ////////////////////////////////////////////////////////////////////////////////////////////
        else if (statusBit == 13)
        {//alert("here");
            // alert("curr status is "+currStatus.toLowerCase());
            if (currStatus == 'Pending' || currStatus.toLowerCase() == 'shipped' || currStatus == 'DELIVERY' ||
                    currStatus == 'Ready to Fill' || currStatus == 'Filled' ||
                    currStatus == 'Cancelled' || currStatus == 'Waiting payment authorization' ||
                    currStatus == 'Fill Request Accepted')
            {
                this.displayStatusErrMsg("Order status is " + currStatus + " & can't be set to Ready to fill.");
                return;
            }
            this.hideMsg("statusException");
            //alert("9");
            //alert("10");

            this.processOrder(0, "Filled");
            if (lnk != null)
            {
                lnk.hide();
            }
        } else if (statusBit == 17) {
            if (!(currStatus !== "Pending"))
            {
                this.displayStatusErrMsg("Order status is " + currStatus + " & can't be set to Interpreted Image.");
                return;
            }
            this.hideMsg("statusException");
            //arslan
            if ($("#referenceBrandDetail").val() == '')
            {
//            alert("Please specify proper drug.");
                $("#popUpException").text("Please specify proper drug.");
                $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
//                 $("#messagePopUpModal").modal('show');
                $("#referenceBrandDetail").focus();
                return false;
            }

            if ($("#drugStrengthDetail").val() == '')
            {
//            alert("Please select drug strength.");
                $("#popUpException").text("Please select drug strength.");
                $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
//                 $("#messagePopUpModal").modal('show');
                $("#drugStrengthDetail").focus();
                $("#drugStrengthDetail").css("border", "1px solid red");
                return false;
            }

            if ($("#drugTypeIdDetail").val() == '')
            {
//            alert("Please select drug type.");
                $("#popUpException").text("Please select dosage type.");
                $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
//                 $("#messagePopUpModal").modal('show');
                $("#drugTypeIdDetail").focus();
                $("#drugTypeIdDetail").css("border", "1px solid red");
                return false;
            }


            if ($("#drugQtyFld").val() == '' || parseFloat($("#drugQtyFld").val()) <= 0)
            {
//            alert("Please specify quantity.");
                $("#popUpException").text("Please specify quantity.");
                $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
//                 $("#messagePopUpModal").modal('show');
                $("#drugQtyFld").focus();
                return false;
            }
            this.hideMsg("popUpException");
//            alert("arslan");
            this.interpretedImageprocessOrder(1, "Pending Review");

        }
        ////////////////////////////////////////////////////////////////////////////////////////////


        populateArrays();

    },
    disableAllFields: function () {
        $('input').each(function () {

            $(this).attr({
                'disabled': 'disabled'
            });
        })
        $('button').each(function () {

            $(this).attr({
                'disabled': 'disabled'
            });
        })
        $('select').each(function () {

            $(this).attr({
                'disabled': 'disabled'
            });
        })
        $("#deliverycarrier").attr("disabled", true);//1
        $("#drugTypeId").attr("disabled", true);
        $("#drugStrength").attr("disabled", true);
        $("#cancel").attr("disabled", false);
        $("#Next").attr("disabled", false);
        $("#messageId").attr("disabled", false);
        $("#isCriticalPT").attr("disabled", false);
    },
    validateOrderProcessFields: function ()
    {
        //this.scrollToElement($("#popUpException"));
        if ($("#popUpException1") != null)
        {
            $("#popUpException1").hide();
        }
        if ($("#byPassImgVerification").val() == 0 && !this.isVerifiedImage()) {
            return false;
        }
        if (!$("#INS").is(":checked") && !$("#radio-a").is(":checked") && !$("#radio-b").is(":checked")) {
            $("#popUpException").text("Please select insurance type.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            return false;
        }
        var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
        var rxDate = $("#datetimepicker").val();
        if (rxDate == "") {

//            alert("Please enter original date.");
            $("#popUpException").text("Please enter original date.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#datetimepicker").focus();
            return false;
        }
        if (rxDate != '')
        {
            if (!(date_regex.test(rxDate)))
            {
//                alert("Please specify date in mm/dd/yyyy format.");
                $("#popUpException").text("Please specify date in mm/dd/yyyy format.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
                $("#datetimepicker").focus();
                return false;
            }

//            var date = new Date();
//            var month = date.getMonth()+1;
//            var day = date.getDay();
//            var year = date.getYear();
            var newDate = new Date().getTime();
            var prescDate = new Date(rxDate).getTime();
            if (prescDate > newDate)
            {
//                alert("Rx date can't be future date.");
                $("#popUpException").text("Rx date can't be future date.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
                $("#datetimepicker").focus();
                return false;
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        var nextRefillDate = $("#datetimepicker3").val();
        if (nextRefillDate != '')
        {
            if (!(date_regex.test(rxDate)))
            {
                $("#popUpException").text("Please specify date in mm/dd/yyyy format.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                $("#datetimepicker3").focus();
                return false;
            }

            var newDate = new Date().getTime();
            var nextRefilDate = new Date(nextRefillDate).getTime();

            if (nextRefilDate <= newDate)
            {

                $("#popUpException").text("Next Refill date must be a future date.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                $("#datetimepicker3").focus();
                return false;
            }
        }
        ///////////////////////////////////////
        if ($("#referenceBrandDetail").val() == '')
        {
//            alert("Please specify proper drug.");
            $("#popUpException").text("Please specify proper drug.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugNameId").focus();
            return false;
        }
        if ($("#drugTypeIdDetail").val() == '')
        {
//            alert("Please select drug type.");
            $("#popUpException").text("Please select dosage type.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugTypeIdDetail").css("border", "1px solid red");
            $("#drugTypeIdDetail").focus();
            return false;
        }

        if ($("#drugStrengthDetail").val() == '')
        {
//            alert("Please select drug strength.");
            $("#popUpException").text("Please select drug strength.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugStrengthDetail").focus();
            return false;
        }
        if ($("#drugQtyFld").val() == '' || parseFloat($("#drugQtyFld").val()) <= 0)
        {
//            alert("Please specify quantity.");
            $("#popUpException").text("Please specify quantity.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugQtyFld").focus();
            return false;
        }
        if (!$("#INS").is(":checked") && !$("#radio-a").is(":checked") && !$("#radio-b").is(":checked")) {
            $("#popUpException").text("Please select insurance type.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            return false;
        }
        if ($("#daysSupplyFld").val() == '')
        {
//            alert("Please enter days supply.");
            $("#popUpException").text("Please enter days supply.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#daysSupplyFld").focus();
            return false;
        }
        if ($("#daysSupplyFld").val() == 0) {
//            alert("Days supply must be greater than 0.");
            $("#popUpException").text("Days supply must be greater than 0.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#daysSupplyFld").focus();
            return false;
        }
        if ($("#refillAllowedFld").val() == '')
        {
//            alert("Please enter refill allow.");
            $("#popUpException").text("Please enter refill allow.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#refillAllowedFld").focus();
            return false;
        }
        if ($("#refillUsedFld").val() == '')
        {

            $("#popUpException").text("Please enter refill remain.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#refillUsedFld").focus();
            return false;
        }
        if ($("#labelScanFld").val() == '1')
        {

            if ($("#pharmacyNameFld").val() == '')
            {
                //            alert("Please enter pharmacy name.");
                $("#popUpException").text("Please enter pharmacy name.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");

                //                 $("#messagePopUpModal").modal('show');
                $("#pharmacyNameFld").focus();
                return false;
            }
            if ($("#pharmacyPhoneFld").val() == '')
            {
                //            alert("Please enter pharmacy phone.");
                $("#popUpException").text("Please enter pharmacy phone");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                //                 $("#messagePopUpModal").modal('show');
                $("#pharmacyPhoneFld").focus();
                return false;
            }
            if (!this.validatePhoneNumber($("#pharmacyPhoneFld").val(), 'pharmacyPhoneFld')) {
//                $("#popUpException").text("Please enter valid 10 digit pharmacy phone number.");
//                $("#popUpException").attr("style", "color:red; font-weight:bold");
//                $("#pharmacyPhoneFld").focus();
                return false;
            }
        }
        if (this.isEmpty($("#prescriberLastNameFld").val()) || this.isBlank($("#prescriberLastNameFld").val()))
        {
            //            alert("Please enter prescriber name.");
            $("#popUpException").text("Please enter prescriber last name.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            //                 $("#messagePopUpModal").modal('hide');
            $("#prescriberLastNameFld").focus();
            return false;
        }
        if ($("#rxScanFld").val() == '1')
        {
//            if ($("#prescriberLastNameFld").val() == '')
//            {
//                //            alert("Please enter prescriber name.");
//                $("#popUpException").text("Please enter prescriber name.");
//                $("#popUpException").attr("style", "color:red; font-weight:bold");
//                //                 $("#messagePopUpModal").modal('hide');
//                $("#prescriberLastNameFld").focus();
//                return false;
//            }
            if (!this.validatePhoneNumber($("#prescriberPhoneFld").val(), 'prescriberPhoneFld'))
            {
                return false;
            }
            if ($("#prescriberPhoneFld").val() == '') {
                $("#popUpException").text("Please enter prescriber phone number.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                $("#prescriberPhoneFld").focus();
                return false;
            }
        }
        refill = parseFloat($("#refillAllowedFld").val());

        remaining = parseFloat($("#refillUsedFld").val());
        //alert("refill: "+refill+" remaining: "+remaining);
        if (refill < remaining)
        {
//            alert("Refill remaning can not be grator than refill allowed.");
            $("#popUpException").text("Refill remaning can not be greater than refill allowed.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#refillUsedFld").focus();
            return false;
        }
//        if ($("#paymentType").val() == "INSURANCE") {
//            if (!$('#radio-a').is(':checked')) {
//                alert("Please select public ins checkbox.");
//                return false;
//            }
//        }
//        if (!$('#radio-a').is(':checked') && !$('#radio-b').is(':checked') ) 
//        {
////            alert("Please select either cash pay or public ins radio.");
//             $("#popUpException").text("Please select either cash pay or public ins radio.");
//              $("#popUpException").attr("style","color:red");
////                 $("#messagePopUpModal").modal('show');
//            return false;
//        }
        if ($("#acqCostFld").val() == '')
        {
//            alert("Please enter ingredient cost.");
            $("#popUpException").text("Please enter ingredient cost.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#acqCostFld").focus();
            return false;
        }
        if (parseFloat($("#ptCopayFld").val()) < 0)
        {
            $("#popUpException").text("Copay field value should be greater than 0.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#ptCopayFld").focus();
            return false;
        }
        if (parseFloat($("#reimbFld").val()) < 0)
        {
            $("#popUpException").text("Reimbursement field value should be greater than 0.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#reimbFld").focus();
            return false;
        }
        if ($("#ptCopayFld").val() == '')
        {
            $("#ptCopayFld").val(0);
//            alert("Please enter patinet copay.");
//            $("#popUpException").text("Please enter patinet copay.");
//            $("#popUpException").attr("style", "color:red; font-weight:bold");
////                 $("#messagePopUpModal").modal('show');
//            $("#ptCopayFld").focus();
//            return false;
        }
        if ($("#sellingPriceFld").val() == '')
        {
//            alert("Please enter selling price.");
            $("#popUpException").text("Please enter selling price.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#sellingPriceFld").focus();
            return false;
        }
        if (!$('#radio-b').is(':checked')) {
            reimb = parseFloat($("#reimbFld").val());//parseFloat(document.getElementById(reimbFld).val());
//            if (reimb <= 0)
//            {
//                alert("Reimb should be greater than 0.");
//                return false;
//            }
            //alert("B "+reimb);
            acq = parseFloat($("#acqCostFld").val());
            if (acq <= 0)
            {
//                alert("Acq should be greater than 0.");
                $("#popUpException").text("Acq should be greater than 0.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
                $("#acqCostFld").focus();

                return false;
            }

            // alert("c "+acq);
//            if (acq > reimb)
//            {
//                alert("Acq cost shouldn't be greater than Reimb Cost");
//                //$("#" + reimbFld).focus();
//                return false;
//            }

//            copy = parseFloat($("#ptCopayFld").val());
//            if (copy <= 0) {
////                alert("OOP/Copay  cost should be greater than 0");
//                $("#popUpException").text("OOP/Copay  cost should be greater than 0");
//                $("#popUpException").attr("style", "color:red; font-weight:bold");
////                 $("#messagePopUpModal").modal('show');
//                $("#ptCopayFld").focus();
//                return false;
//            }
        }

//         $("#popUpException").text("Order has been updated successfully..");
//          $("#popUpException").attr("style","color:green","font-weight:Bold");
//          $("#popUpException").modal('show');
        this.hideMsg("popUpException");
        return true;
    },
    checkProcessingStatusBitHandler: function ()
    {
        var currStatus2 = $("#orderStatusSpan").html();
        this.hideMsg("popUpException");
        if (currStatus2 == 'Processing')
        {
            this.displayStatusErrMsg("Order status is alreay processing.");
            return;
        }
        $("#statusException").text('');
        $("#popUpException").text('');
        $("#confirmDiv").modal('show');
    },
    checkProcessingStatusBit: function (divId, msg1, msg2, eventName, resp)
    {
        //alert("hello");
        $("#cancelConfirmBoxBtn").click();
        var currStatus = $("#orderStatusSpan").html();
        this.hideMsg("popUpException");
        if (currStatus == 'Filled' || currStatus.toLowerCase() == 'shipped' || currStatus == 'DELIVERY'
                || currStatus == 'Cancelled'
                || currStatus == 'Waiting payment authorization'
                || currStatus == 'Ready to Fill'
                || currStatus == 'Fill Request Accepted'
                || currStatus == 'Payment Authorized')
        {
            this.displayStatusErrMsg("Order status is " + currStatus + " & can't be set to Processing.");
            return;
        }

//        var flag = confirm("Do you want to update this order");
//
////         var flag = $("#popUpException").text("Do you want to update this order");
//        if (flag == false)
//            return;
        if (this.validateOrderProcessFields() == false)
            return;
        var processingBit = $("#processingID").val();
        if (processingBit == "0")
        {

//            $("#processingLnk").attr("disabled", true);
//            $("#paymentLnk").attr("disabled",false);
//            $("#readyFilledlnk").attr("disabled", false);
//            $("#courierLnk").attr("disabled", true);
//            $("#shippedLnk").attr("disabled", true);

            $("#processingLnk").attr("class", "btn btn_one redbtn");
            $("#processingID").val("1");
            $("#statusBit").val("2");
            this.processOrder(0, "Processing");
            //alert("A");
            populateArrays();

            //////////////////////////////////////////
            var json = {"eventName": eventName, "message": resp}
            $.ajax(
                    {
                        url: window.pharmacyNotification.appUrl + "/ConsumerPortal/getInAppMsg",
                        type: "POST",
                        dataType: 'text',
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        success: function (data)
                        {
                            //alert(""+data);
                            $("#defMsg").val(data);

                            //now json variable contains data in json format
                            //let's display a few items
//                        for (var i=0;i<json.length;++i)
//                        {
//                            alert('data is '+json[i].name);
//                        }
//                        
                            //alert("Message sent successfully");
                            //$( "#backLnk" ).click();
                        },
                        error: function (e)
                        {
                            alert("err " + e);
                        }
                    });

            /////////////////////////////////////////
        } else
        {
            $("#processingLnk").attr("class", "btn btn_two");
            $("#processingID").val("0");
            $("#statusBit").val("0");
        }
        //$("#processingLnk").focus();
    },
    sendInAppNotification: function (drug, qty, orderId, patientId, orderType,
            drugStrength, drugType, payment, preference, eventFld, responseFld, infoDiv, msgDiv, critical)
    {
        //alert("hi "+$("#msg1").html()+' '+$("#msg2").html());
        var orderDate = $("#orderDateFld").val();
//        var critical = $("#isCritical").is(":checked");
        var isCritical = 0;
        if (critical == true)
        {
            var isCritical = 1;//.val();
        }
//        
//        var lowerCostBtn = $("#lowerCostBtn");
//        alert("lowerCostBtn");
//        var lowerCostOpt = $("#lowerCost").val();
//        if (lowerCostOpt == 0 && lowerCostBtn == "LOWER COST OPTION" )
//        {
//            $("#lowerCost").val("1");
//        }
        //alert(isCritical+"b");
        //alert(""+patientId+" event/response/date is "+$("#eventName").val()+"/"+$("#response").val()+"/"+orderDate);
        //alert("td "+document.getElementById("dateColumn").innerHTML);//$("#dateColumn").html());
        if ($("#drugPdffile2")[0].files == null || $("#drugPdffile2")[0].files.length <= 0)
        {
            var insType = "";
            if ($("#radio-a").is(":checked"))
            {
                insType = "Public";
            } else if ($("#radio-b").is(":checked"))
            {
                insType = "Cash";
            } else if ($("#INS").is(":checked"))
            {
                insType = "Commercial";
            }


//            alert("del option "+$("#delOption").val())
            var json = {"eventName": $("#" + eventFld).val(), "message": $("#" + responseFld).val(), "drug": $("#drugNameIdDetail").val(),
                "qty": $("#drugQtyFld").val(), "DRUG_STRENGTH": $("#drugStrengthDetail").val(),
                "DRUG_TYPE": $("#drugTypeIdDetail").val(), "ORDER_TYPE": orderType, "isCritical": isCritical, "ORDER_DATE": orderDate, "PAYMENT": payment,
                "DELIVER_PREFERENCE": preference, "customMsg": $("#" + msgDiv).html(), //.val(),
                "customSubject": $("#msg1").html() + ' ' + $("#msg2").html(), "delOption": $("#delOption").val(), "autodelOrderOption": $("#autodelOption").val(), "lowerCostOption": $("#lowerCost").val(),
                "daysSupply": $("#daysSupplyFld").val(), "refillAllowedFld": $("#refillAllowedFld").val(), "insType": insType, "acqCostFld": $("#acqCostFld").val(), "ptCopayFld": $("#ptCopayFld").val()
                , "redeemPointsCostFld": $("#redeemPointsCostFld").val(), "finalPayment": $("#finalPaymentFeeFld").val()};
            //        var json = {"eventName": $("#" + eventFld).val(), "message": $("#" + responseFld).val(), "drug": drug, "qty": qty, "DRUG_STRENGTH": drugStrength,
            //            "DRUG_TYPE": drugType, "ORDER_TYPE": orderType, "ORDER_DATE": orderDate, "PAYMENT": payment,
            //            "DELIVER_PREFERENCE": preference, "customMsg": $("#" + msgDiv).val(),
            //            "customSubject": $("#msg1").html() + ' ' + $("#msg2").html()};
            //alert(""+JSON.stringify(json));

            $.ajax(
                    {
                        url: window.pharmacyNotification.appUrl + "/ConsumerPortal/sendinappnotification/" + orderId + "/" + patientId,
                        type: "POST",
                        dataType: 'json',
                        contentType: 'application/json',
                        enctype: 'multipart/form-data',
                        data: JSON.stringify(json),
                        success: function (data)
                        {//alert(data.baseDTO.olVersion);
                            $("#" + infoDiv).show();
                            $("#" + infoDiv).css("color", "green");
                            $("#" + infoDiv).html("<strong>Message sent successfully</strong>");
                            $("#saveMsgButton").attr("disabled", true);
                            $("#editMsgButton").attr("disabled", true);
                            window.pharmacyNotification.setLowerCost(0);
                            //$("#olversion").val(data.baseDTO.olVersion);
                            if ($("#nextOrder").val() == 1)
                                setTimeout(function () {
                                    $("#medicationModal").modal('hide');
                                    $("#Next").click();
                                }, $("#loadingTime").val());
                            else
                                setTimeout(function () {
                                    $("#medicationModal").modal('hide');
                                    $("#backLnk").click();
                                }, 1000);
                            //alert("A");
                            //$("[data-dismiss=modal]").trigger({ type: "click" });
                            //setTimeout($("#medicationModal").hide(),10000);
                            //alert("Message sent successfully");
                            //$( "#backLnk" ).click();
                        },
                        error: function (e)
                        {
                            $("#inAppInfo").show();
                            //alert("error "+e.responseText);
                            $("#inAppInfo").css("color", "red");
                            window.console.log(e);
                            //debugger;
                            $("#inAppInfo").html("Error while sending message or you did not have sufficient priviliges to perform this action.");
                            //                        alert('Error while processing order...' + e.responseText);
                        }
                    });
        } else
        {
            /*
             "eventName": $("#" + eventFld).val(), "message": $("#" + responseFld).val(), "drug": $("#drugNameIdDetail").val(),
             "qty": $("#drugQtyFld").val(), "DRUG_STRENGTH": $("#drugStrengthDetail").val(),
             "DRUG_TYPE": $("#drugTypeIdDetail").val(), "ORDER_TYPE": orderType, "isCritical": isCritical, "ORDER_DATE": orderDate, "PAYMENT": payment,
             "DELIVER_PREFERENCE": preference, "customMsg": $("#" + msgDiv).html(),//.val(),
             "customSubject": $("#msg1").html() + ' ' + $("#msg2").html()
             
             */

            ////////////////////////////////////////////////////////////////////////////
            var data = new FormData();
            data.append("eventName", $("#" + eventFld).val());
            data.append("message", $("#" + responseFld).val());
            data.append("drug", $("#drugNameIdDetail").val());
            data.append("qty", $("#drugQtyFld").val());
            data.append("DRUG_STRENGTH", $("#drugStrengthDetail").val());
            data.append("DRUG_TYPE", $("#drugTypeIdDetail").val());
            data.append("ORDER_TYPE", orderType);
            data.append("isCritical", isCritical);
            if (orderDate != null)
                data.append("ORDER_DATE", orderDate);
            data.append("PAYMENT", payment);
            data.append("DELIVER_PREFERENCE", preference);
            data.append("customMsg", $("#" + msgDiv).html());
            data.append("customSubject", $("#msg1").html() + ' ' + $("#msg2").html());
            data.append("pdfDocFile", $("#drugPdffile2")[0].files[0]);
//                    alert("data is "+data);
//                    var formKeys    = data.keys();
//                    var formEntries = data.entries();
//                    do {
//  console.log(formEntries.next().value);
//} while (!formKeys.next().done)
            $.ajax({
                url: window.pharmacyNotification.appUrl + "/ConsumerPortal/sendInAppMessageWithFile/" + orderId + "/" + patientId,
                type: "POST",
                contentType: false,
                enctype: 'multipart/form-data',
                processData: false,
                cache: false,
                data: data,
                success: function (response) {
                    //alert("CODE "+response.toString());
                    if (response.statuscode == 200) {
//                            var currentUserRole = $("#currentUserRole").val();
//                            window.drugestimateprice.resetField(currentUserRole);
                        $("#" + infoDiv).show();
                        $("#" + infoDiv).css("color", "green");
                        $("#" + infoDiv).html("<strong>Message sent successfully</strong>");
                        $("#saveMsgButton").attr("disabled", true);
                        $("#editMsgButton").attr("disabled", true);
                        if ($("#nextOrder").val() == 1)
                            setTimeout(function () {
                                $("#medicationModal").modal('hide');
                                $("#Next").click();
                            }, $("#loadingTime").val());
                        else
                            setTimeout(function () {
                                $("#medicationModal").modal('hide');
                                $("#backLnk").click();
                            }, 1000);
                    } else {
                        $("#" + infoDiv).show();
                        $("#" + infoDiv).css("color", "red");
                        $("#" + infoDiv).html("<strong>Some error occurred. Please try again.</strong>");

                    }

                    //                alert(response.errorMessage);
                }, error: function (e) {
                    $("#" + infoDiv).show();
                    $("#" + infoDiv).css("color", "red");
                    $("#" + infoDiv).html("<strong>Some error occurred. Please try again.</strong>");

                }
            });
            ///////////////////////////////////////////////////////////////////////////
        }
    },
    ///////////////////////////////////////////////////////////////////////
    sendInAppRefillTooSoonNotification: function (drug, qty, orderId, patientId, orderType,
            drugStrength, drugType, payment, preference, eventFld, responseFld, infoDiv, msgDiv)
    {
        //alert("hi "+$("#msg1").html()+' '+$("#msg2").html());
        var orderDate = $("#orderDateFld").val();
        //alert("b");
        //alert(""+patientId+" event/response/date is "+$("#eventName").val()+"/"+$("#response").val()+"/"+orderDate);
        //alert("td "+document.getElementById("dateColumn").innerHTML);//$("#dateColumn").html());
        var json = {"eventName": $("#" + eventFld).val(), "message": $("#" + responseFld).val(), "drug": drug, "qty": qty, "DRUG_STRENGTH": drugStrength,
            "DRUG_TYPE": drugType, "ORDER_TYPE": orderType, "ORDER_DATE": orderDate, "PAYMENT": payment,
            "DELIVER_PREFERENCE": preference, "customMsg": $("#" + msgDiv).html(), //.val(),
            "customSubject": $("#msg1Refill").html() + ' ' + $("#msg2Refill").html()}
        //alert(""+JSON.stringify(json));

        $.ajax(
                {
                    url: window.pharmacyNotification.appUrl + "/ConsumerPortal/sendinappnotification/" + orderId + "/" + patientId,
                    type: "POST",
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(json),
                    success: function (data)
                    {
                        //alert("Message sent successfully");
                        $("#" + infoDiv).show();
                        $("#" + infoDiv).css("color", "green");
                        $("#" + infoDiv).html("<strong>Message sent successfully</strong>");
                        $("#saveMsgButtonRefill").attr("disabled", true);
                        // $("#editMsgButton").attr("disabled", true);
                        //alert("Message sent successfully");
                        //$( "#backLnk" ).click();
                    },
                    error: function (e)
                    {
                        $("#" + infoDiv).show();
                        //alert("error "+e.responseText);
                        $("#" + infoDiv).css("color", "red");
                        $("#" + infoDiv).html("Error while sending message");
                        //alert('Error while processing order...' + e.responseText);
                    }
                });
    },
    /////////////////////////////////////////////////////////////
    sendInAppProcessingNotification: function (drug, qty, orderId, patientId, orderType,
            drugStrength, drugType, payment, preference, eventFld, responseFld, infoDiv, msgDiv)
    {
        //alert("hi "+$("#msg1").html()+' '+$("#msg2").html());
        var orderDate = $("#orderDateFld").val();
        //alert("b");
        //alert(""+patientId+" event/response/date is "+$("#eventName").val()+"/"+$("#response").val()+"/"+orderDate);
        //alert("td "+document.getElementById("dateColumn").innerHTML);//$("#dateColumn").html());
//        var json = {"eventName": $("#" + eventFld).val(), "message": $("#" + responseFld).val(), "drug": drug, "qty": qty, "DRUG_STRENGTH": drugStrength,
//            "DRUG_TYPE": drugType, "ORDER_TYPE": orderType, "ORDER_DATE": orderDate, "PAYMENT": payment,
//            "DELIVER_PREFERENCE": preference, "customMsg": $("#" + msgDiv).val(),
//            "customSubject": $("#processMsg1").html() + ' ' + $("#processMsg2").html()}
        var insType = "";
        if ($("#radio-a").is(":checked"))
        {
            insType = "Public";
        } else if ($("#radio-b").is(":checked"))
        {
            insType = "Cash";
        } else if ($("#INS").is(":checked"))
        {
            insType = "Commercial";
        }
        var json = {"eventName": $("#" + eventFld).val(), "message": $("#" + responseFld).val(), "drug": $("#drugNameIdDetail").val(),
            "qty": $("#drugQtyFld").val(), "DRUG_STRENGTH": $("#drugStrengthDetail").val(),
            "DRUG_TYPE": $("#drugTypeIdDetail").val(), "ORDER_TYPE": orderType, "ORDER_DATE": orderDate, "PAYMENT": payment,
            "DELIVER_PREFERENCE": preference, "customMsg": $("#" + msgDiv).val(),
            "customSubject": $("#processMsg1").html() + ' ' + $("#processMsg2").html(), "acqCostFld": $("#acqCostFld").val()
            , "ptCopayFld": $("#ptCopayFld").val(), "redeemPointsCostFld": $("#redeemPointsCostFld").val(), "insType": insType, "daysSupply": $("#daysSupplyFld").val(), "refillAllowedFld": $("#refillAllowedFld").val()};
        //alert(""+JSON.stringify(json));

        $.ajax(
                {
                    url: window.pharmacyNotification.appUrl + "/ConsumerPortal/sendinappnotification/" + orderId + "/" + patientId,
                    type: "POST",
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(json),
                    success: function (data)
                    {
                        //alert("Message sent successfully");
                        $("#" + infoDiv).show();
                        $("#" + infoDiv).css("color", "green");
                        $("#" + infoDiv).html("<strong>Message sent successfully</strong>");
                        $("#saveMsgButton2").attr("disabled", true);
                        $("#editMsgButton2").attr("disabled", true);
                        //alert("Message sent successfully");
                        //$( "#backLnk" ).click();
                    },
                    error: function (e)
                    {
                        $("#" + infoDiv).show();
                        //alert("error "+e.responseText);
                        $("#" + infoDiv).css("color", "red");
                        $("#" + infoDiv).html("Error while sending message");
                        //alert('Error while processing order...' + e.responseText);
                    }
                });
    },
    ////////////////////////////////////////////////////////////

    updatePatientAllergies: function ()
    {
        var allergies = $("#allergiesDiv").html();
        var patientId = $("#patientId").val();
        var json = {"allergies": allergies};
        ////////////////////////////////////////
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/updatePatientAllergies/" + patientId,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {

                {

                    $("#popUpException").text("Allergies has been updated successfully.");
                    $("#popUpException").attr("style", "color:green ; font-weight:bold");
                    $("#statusException").text("");

                }
            },
            error: function (e) {
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });
    },
    popUp: function ()
    {

        var patientId = $("#patientId").val();
        var json = {"statusId": 19};
        ////////////////////////////////////////
//        alert('js url');
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/popUpWind/" + patientId,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
//           alert('success Portion'+data);
//              alert(' if data have notification portion');

                if (data !== 0) {
                    window.pharmacyNotification.notification(data);
                }
            },
            error: function (e) {
//                  alert('error Portion')
            }
        });
    },
    setIntervalAndExecute: function (fn, t) {
//      alert('recursive portion');
        fn();
        return(setInterval(fn, t));
    },
    notification: function (data) {
//       alert('notification portion');

//                         alert("aaaaaaaaaaaaaaa");
        var url = window.pharmacyNotification.appUrl + "/PharmacyPortal/newMemberRequest";
        $.notifier({
            "type": "error",
            "title": '<p>You have ' + data + ' patient response(s) </p>',
            "text": "<a href='" + url + "'>see detail</a>",
            "positionX": "right",
            "delay": 25000,
            "number": 5,
            "animationIn": 'shake',
            "animationIn_duration": 'slow',
            "animationOut": 'drop',
            "animationOut_duration": 'slow'

        });

    },
//        notification2: function(data){
////alert('toster');
//        $.toaster({ 
//            priority : 'info', 
//            title : "<p> You have this ( "+data+" ) Counts </p>",
//            message : "<button><a href='/PharmacyPortal/newMemberRequest'>for detail click here</a></button>"});
//  $.toaster({
//      
//        'toaster':{
//                'id': 'toaster',
//                        'container' : 'body',
//                        'template'  : '<div></div>',
//                        'class'     : 'toaster',
//                        'css'       : {
//                        'position' : 'fixed',
//                                'top'      : '100px',
//                                'right'    : '10px',
//                                'width'    : '300px',
//                                'zIndex'   : 5000
//                        }
//                },
//                'toast':{
//                'template':
//                        '<div class="alert alert-%priority% alert-dismissible" role="alert">' +
//                        '<button type="button" class="close" data-dismiss="alert">' +
//                        '<span aria-hidden="true">&times;</span>' +
//                        '<span class="sr-only">Close</span>' +
//                        '</button>' +
//                        '<span class="title"></span>: <span class="message"></span>' +
//                        '</div>',
//                        'defaults' :{
//                        'title'    : 'Notice',
//                        'priority' : 'success'
//                        },
//                        'css'      : {},
//                        'cssm'     : {},
//                        'csst'     : { 'fontWeight' : 'bold' },
//                        'fade'     : 'slow',
//                        'display'    : function ($toast){
//                        return $toast.fadeIn(settings.toast.fade);
//                        },
//                        'remove'     : function ($toast, callback){
//                        return $toast.animate({
//                        opacity : '0',
//                                padding : '0px',
//                                margin  : '0px',
//                                height  : '0px'
//                        }, {
//                        duration : settings.toast.fade,
//                                complete : callback
//                        }
//                        );
//                        }
//                },
//                'debug'        : false,
//                'timeout'      : 95000,
//                'stylesheet'   : null,
//                'donotdismiss' : []
//      
//               
//  
//            });
//               
//
//        },



    updateDependentAllergies: function ()
    {
        var allergies = $("#dependentAllergiesDiv").html();
        var dependentId = $("#dependentId").val();
        var json = {"allergies": allergies};
        ////////////////////////////////////////
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/updateDependentAllergies/" + dependentId,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {

                {

                    $("#popUpdependent").text("Allergies has been updated successfully.");
                    $("#popUpdependent").attr("style", "color:green ; font-weight:bold");
//                    $("#statusException").text("");

                }
            },
            error: function (e) {
                $("#popUpdependent").text("Error while processing order " + e.responseText);
                $("#popUpdependent").attr("style", "color:red; font-weight:bold");
//                $("#statusBit").val("0");
            }
        });
    },
    interpretedImageprocessOrder: function (loadFlag, statusVal)
    {
        //var processingBit = $("#processingID").val();
        this.eliminateCurrencySignFromNumricFlds();
        var statusBit = $("#statusBit").val();
        var orderId = $("#orderId").val();
        var patientId = $("#patientId").val();
        var message = $("#processMessage").val();

        /////////////////////////////////////////////
        var drugNameDbId = $("#referenceBrandDetail").val();// $("#drugNameIdDetail").val();
        var drugType = $("#drugTypeIdDetail").val();
        var drugStrength = $("#drugStrengthDetail").val();
        var rxNumberFld = $("#rxNumberFld").val();
        var drugQtyFld = $("#drugQtyFld").val();
        var daysSupplyFld = $("#daysSupplyFld").val();
        var refillAllowedFld = $("#refillAllowedFld").val();
        var refillUsedFld = $("#refillUsedFld").val();
        var paymentFld = $("#paymentFld").val();
        var patientNameFld = '';//$("#patientNameFld").val();
        var pharmacyRxNoFld = $("#pharmacyRxNoFld").val();
        var pharmacyNameFld = $("#pharmacyNameFld").val();
        var pharmacyPhoneFld = $("#pharmacyPhoneFld").val();
        var prescriberNameFld = $("#prescriberNameFld").val();
        var prescriberPhoneFld = $("#prescriberPhoneFld").val();
        var prescriberNPIFld = $("#prescriberNPIFld").val();
        var acqCostFld = $("#acqCostFld").val();
        var reimbFld = $("#reimbFld").val();
        var ptCopayFld = $("#ptCopayFld").val();
        var deliverycarrier = $("#deliverycarrier").val();
        var traclingno = $("#traclingno").val();
        var clerk = $("#clerk").val();

        var finalPaymentFld = $("#finalPaymentFeeFld").val();

        var orderComments = $("#orderComments").val();
        var mfrCost = $("#mfrCostFld").val();

        var profitMargin = $("#profitMarginCol").val();
        //alert("profit "+profitMargin);
        var profitSharePoint = $("#totalRedeemPointsId").val();//.html();
        //alert("profitSharePoint "+profitSharePoint);
        var actualProfitShare = $("#redeemPointsCostFld").val();//.html();
        //alert("actualProfitShare "+actualProfitShare);
        var systemGeneratedRxNumber = $("#systemGeneratedRxNumberFld").val();
        var prescriberLastName = $("#prescriberLastNameFld").val();
        var ptOverridePrice = $("#ptOverridePriceFld").val();
        var insuranceType;
        if ($("#radio-a").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Public";
        } else if ($("#radio-b").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Cash";
        } else
        {
            insuranceType = "Commercial";
        }
        var priceIncludingMargins = $("#estimatedDrugPrice").val();
        var rxExpireDate = "";//$("#expiredDateFld").val();$("#rxExpiredDate").html();
        var rxDate = $("#datetimepicker").val();
        ////////////////////////////////////////////
        var changeStatus = "0";

        if (statusBit != "0")
        {
            changeStatus = "17";
        }

        var sellingPrice = $("#sellingPriceFld").val();
        var olversion = $("#olversion").val();

        var pharmacyExt = $("#pharmacyPhoneExt").val();
        var prescriberExt = $("#prescriberPhoneExt").val();
        var nextRefillDateStr =""; //$("#nextRefillDate").html();

        var json = {"changeStatus": changeStatus, "message": message, "statusBit": statusBit, "rxNumberFld": rxNumberFld,
            "name": drugNameDbId, "drugType": drugType,
            "drugStrength": drugStrength, "drugQty": drugQtyFld, "daysSupplyFld": daysSupplyFld, "refillAllowedFld": refillAllowedFld,
            "refillUsedFld": refillUsedFld, "paymentFld": paymentFld, "patientNameFld": patientNameFld, "pharmacyNameFld": pharmacyNameFld,
            "pharmacyPhoneFld": pharmacyPhoneFld, "prescriberNameFld": prescriberNameFld, "prescriberPhoneFld": prescriberPhoneFld,
            "prescriberNPIFld": prescriberNPIFld, "acqCostFld": acqCostFld, "reimbFld": reimbFld, "ptCopayFld": ptCopayFld,
            "finalPaymentFld": finalPaymentFld, "deliverycarrier": deliverycarrier, "traclingno": traclingno, "clerk": clerk,
            "orderComments": orderComments, "mfrCost": mfrCost, "insuranceType": insuranceType, "priceIncludingMargins": priceIncludingMargins,
            "rxExpireDate": rxExpireDate, "rxDate": rxDate, "profitMargin": profitMargin,
            "profitSharePoint": profitSharePoint, "actualProfitShare": actualProfitShare,
            "sellingPrice": sellingPrice, "olversion": olversion, "pharmacyRxNoFld": pharmacyRxNoFld,
            "systemGeneratedRxNumber": systemGeneratedRxNumber, "prescriberLastName": prescriberLastName,
            "ptOverridePrice": ptOverridePrice, "pharmacyExt": pharmacyExt, "prescriberExt": prescriberExt, "nextRefillDateStr": nextRefillDateStr};
        ////////////////////////////////////////
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/updateorderstatus/" + orderId + "/" + patientId,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data.status == 'Fail') {
                    $("#popUpException").text(data.errorMessage);
                    $("#popUpException").show();
                    $("#statusBit").val("0");
                    return;
                }
                if (loadFlag == "1")
                    $("#verifiedLnk").click();
                else
                {
                    $("#orderStatusSpan").html(statusVal);
//                    $("#popUpException").text("Order has been updated successfully.");
//                    $("#popUpException").attr("style", "color:green ; font-weight:bold");
                    $("#popUpSuccess").show();
                    $("#statusException").text("");
                    $("#interpretedImageLnk").attr("disabled", true);
                    $("#verifiedImg").val("0");
                    $("#saveBtn").removeAttr("disabled");
                    $("#filledlnk").removeAttr("disabled");
//                    alert(data.baseDTO.olVersion);
                    $("#olversion").val(data.baseDTO.olVersion);

                }
            },
            error: function (e) {
                alert(e.responseText);
                $("#popupException").text("Error while processing order " + e.responseText);
                $("#popupException").attr("style", "color:red; font-weight:bold");
                $("#popupException").show();
                $("#statusBit").val("0");
            }
        });
        this.appendCurrencySignFromNumricFlds();
    },
    /////////////////////////////////////////////////////////////
    deleteOrderHandler: function ()
    {
//      alert("A");
        $('#confirmDiv').modal('show');
    },
    deleteOrder: function (loadFlag)
    {
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/cancelOrder/" + $("#orderId").val(),
            type: "POST",
            contentType: 'application/json',
            success: function (data) {
//                alert("data "+data + "ol "+data.baseDTO.olVersion + "status "+data.baseDTO.status);
//                debugger;
                if (loadFlag == "1") {
//                    alert("if");
                    $("#delLnk").click();
                }


            },
            error: function (e) {
                //alert('Error while processing order...' + e.responseText);
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });

    },
    /////////////////////// Iterpreted Image ////////////////////
    processOrder: function (loadFlag, statusVal)
    {
        if ($("#popUpException1") != null)
        {
            $("#popUpException1").hide();
        }
        if (statusVal != 11)
        {
//            alert("stats "+statusVal);
            this.eliminateCurrencySignFromNumricFlds();
            this.truncateFldValue('estPriceFld', 1, 1, '$');
            if (this.validateOrderProcessFields() == false)
                return;
        } else
        {
            /////////////////////////////////////////////////////////
//          alert("here "+$("#orderId").val());
            $.ajax({
                url: window.pharmacyNotification.appUrl + "/ConsumerPortal/cancelOrder/" + $("#orderId").val(),
                type: "POST",
                contentType: 'application/json',
                data: JSON.stringify(json),
                success: function (data) {
//                alert("data "+data + "ol "+data.baseDTO.olVersion + "status "+data.baseDTO.status);
//                debugger;
                    if (loadFlag == "1") {
//                    alert("if");
                        $("#backLnk").click();
                    }


                },
                error: function (e) {
                    //alert('Error while processing order...' + e.responseText);
                    $("#statusException").text("Error while processing order " + e.responseText);
                    $("#statusException").attr("style", "color:red; font-weight:bold");
                    $("#statusBit").val("0");
                }
            });

            //////////////////////////////////////////////////////
            return;
        }
//        if (!$('#radio-b').is(':checked')) {
//            this.calculateProfit('reimbFld', 'acqCostFld');
//        }
//       alert("zzzz");
        var processingBit = $("#processingID").val();
//        alert("yyyy");
        var statusBit = $("#statusBit").val();

        var orderId = $("#orderId").val();
        //alert("order id "+orderId)
        var patientId = $("#patientId").val();
        var message = $("#processMessage").val();

        /////////////////////////////////////////////
        var drugNameDbId = $("#referenceBrandDetail").val();//$("#drugNameIdDetail").val();
        var drugType = $("#drugTypeIdDetail").val();
        var drugStrength = $("#drugStrengthDetail").val();
        var rxNumberFld = $("#rxNumberFld").val();
        var drugQtyFld = $("#drugQtyFld").val();
        var daysSupplyFld = $("#daysSupplyFld").val();
        var refillAllowedFld = $("#refillAllowedFld").val();
        var refillUsedFld = $("#refillUsedFld").val();
        var paymentFld = $("#paymentFld").val();
        var patientNameFld = '';//$("#patientNameFld").val();
        var pharmacyRxNoFld = $("#pharmacyRxNoFld").val();
        var pharmacyNameFld = $("#pharmacyNameFld").val();
        var pharmacyPhoneFld = $("#pharmacyPhoneFld").val();
        var prescriberNameFld = $("#prescriberNameFld").val();
        var prescriberPhoneFld = $("#prescriberPhoneFld").val();
        var prescriberNPIFld = $("#prescriberNPIFld").val();
        var acqCostFld = $("#acqCostFld").val();
        var reimbFld = $("#reimbFld").val();
        var ptCopayFld = $("#ptCopayFld").val();
        var deliverycarrier = $("#deliverycarrier").val();
        var traclingno = $("#traclingno").val();
        var clerk = $("#clerk").val();
        var shipDate = ($("#datetimepicker2").val());

        var finalPaymentFld = $("#finalPaymentFeeFld").val();

        var orderComments = $("#orderComments").val();
        var mfrCost = $("#mfrCostFld").val();

        var profitMargin = $("#profitMarginCol").val();
        //alert("profit "+profitMargin);
        var profitSharePoint = $("#totalRedeemPointsId").val();//.html();
        //alert("profitSharePoint "+profitSharePoint);
        var actualProfitShare = $("#redeemPointsCostFld").val();//html();
        //alert("actualProfitShare "+actualProfitShare);

        var estPriceFld = $("#estPriceFld").val();
        var paymentAuthorization = $("#paymentAuthorization").val();

        var insuranceType;
        if ($("#radio-a").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Public";
        } else if ($("#radio-b").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Cash";
        } else if ($("#INS").is(":checked"))
        {
            insuranceType = "Commercial";
        } else {
            $("#popUpException").text("Please select insurance type.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            return false;
        }
        var priceIncludingMargins = $("#estimatedDrugPrice").val();
        var rxExpireDate = $("#rxExpiredDate").html(); //$("#expiredDateFld").val();
        var rxDate = $("#datetimepicker").val();
        var nextRefillDateStr = $("#nextRefillDate").html();
//        if ($("#datetimepicker3") != null && $.trim($("#datetimepicker3").val()) != '')
//        {
//            nextRefillDateStr = $("#datetimepicker3").val();
////           alert('nextrefill'+nextRefillDateStr);
//        }

        ////////////////////////////////////////////

        var changeStatus = "0";

        //alert("comments "+message);
        if (statusBit != "0")//(processingBit=="1" || pendingBit=="1" || delayedBit=="1" || courierBit=="1" || shippedBit=="1")
        {
//            var flag = confirm("Do you want to update this order?");
//            if (flag == false)
//            {
//                return;
//            }
            changeStatus = "1";

        }

        var sellingPrice = $("#sellingPriceFld").val();
        var olversion = $("#olversion").val();
        var handlingFee = $("#handlingFeeFld").val();
        var systemGeneratedRxNumber = $("#systemGeneratedRxNumberFld").val();
        var prescriberLastName = $("#prescriberLastNameFld").val();
        var ptOverridePrice = $("#ptOverridePriceFld").val();

        var pharmacyExt = $("#pharmacyPhoneExt").val();
        var prescriberExt = $("#prescriberPhoneExt").val();
        var prefId = $("#deliveryPreferences").val();

        var multiRxIds = ""; //[];
        var multiRx = false;
//        if ($("#multiRxC").prop("checked") == true) {
//            multiRx = true;
//            $($('[name=multirxDCB]').each(function () {
//                if ($(this).is(":checked")) {
//                    multiRxIds.push($(this).val());
//                }
//            }));
//        }
        if ($("#hiddenRxNumbers").val().trim().length > 0) {
            multiRx = true;
            multiRxIds = $("#hiddenRxNumbers").val();
            //finalPaymentFld=$("#finalPaymentCol").html();
            //finalPaymentFld=finalPaymentFld.replace("$","");
        }


        var json = {"changeStatus": changeStatus, "message": message, "statusBit": statusBit, "rxNumberFld": rxNumberFld,
            "name": drugNameDbId, "drugType": drugType,
            "drugStrength": drugStrength, "drugQty": drugQtyFld, "daysSupplyFld": daysSupplyFld, "refillAllowedFld": refillAllowedFld,
            "refillUsedFld": refillUsedFld, "paymentFld": paymentFld, "patientNameFld": patientNameFld, "pharmacyRxNoFld": pharmacyRxNoFld, "pharmacyNameFld": pharmacyNameFld,
            "pharmacyPhoneFld": pharmacyPhoneFld, "prescriberNameFld": prescriberNameFld, "prescriberPhoneFld": prescriberPhoneFld,
            "prescriberNPIFld": prescriberNPIFld, "acqCostFld": acqCostFld, "reimbFld": reimbFld, "ptCopayFld": ptCopayFld,
            "finalPaymentFld": finalPaymentFld, "deliverycarrier": deliverycarrier, "shipDate": shipDate, "traclingno": traclingno, "clerk": clerk,
            "orderComments": orderComments, "mfrCost": mfrCost, "insuranceType": insuranceType, "priceIncludingMargins": priceIncludingMargins,
            "rxExpireDate": rxExpireDate, "rxDate": rxDate, "nextRefillDateStr": nextRefillDateStr, "profitMargin": profitMargin,
            "profitSharePoint": profitSharePoint, "actualProfitShare": actualProfitShare,
            "sellingPrice": sellingPrice, "olversion": olversion, "handlingFee": handlingFee,
            "multiRxIds": multiRxIds, "multiRx": multiRx, "systemGeneratedRxNumber": systemGeneratedRxNumber,
            "prescriberLastName": prescriberLastName, "ptOverridePrice": ptOverridePrice,
            "pharmacyExt": pharmacyExt, "prescriberExt": prescriberExt, "estPriceFld": estPriceFld,
            "paymentAuthorization": paymentAuthorization, "prefId": prefId};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));
        //alert("json");
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/updateorderstatus/" + orderId + "/" + patientId,
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
//                alert("data "+data + "ol "+data.baseDTO.olVersion + "status "+data.baseDTO.status);
//                debugger;
                if (data.status == 'Fail') {
                    $("#popUpException").text(data.errorMessage);
                    $("#popUpException").show();
                    $("#statusBit").val("0");
                    return;
                }
                if (loadFlag == "1") {
//                    alert("if "+statusBit);
                    $("#backLnk").click();
                } else
                {
//                    alert("else "+statusBit);
                    //alert("Order has been updated successfully.");
                    //alert("orderStatusSpan1111");
                    $("#orderStatusSpan").html(statusVal);
//                    $("#popUpException").text("Order has been updated successfully.");
//                    $("#popUpException").attr("style", "color:green ; font-weight:bold");
                    $("#popUpSuccess").show();
                    $("#statusException").text("");

                    $("olversion").val(data.baseDTO.olVersion);
                    //alert("bit  is "+statusBit)
                    if (statusBit == 8)
                    {
                        $("#backLnk").click();
                    }
                    if (statusBit == 5 || statusBit == 6 || statusBit == 15 || statusBit == 13)
                    {
                        location.reload();
//                        disableAllFields();
//                        $("#Next").attr("disabled",false);
//                        $("#cancelConfirmBoxBtn").attr("disabled",false);

                    }
                    //alert("orderStatusSpan2222");
//                    $("#olversion").val(data.baseDTO.olVersion);
                    if (data.baseDTO.status === "16") {
//                        alert("16 if");
                        location.reload();
                    }

//                    if(data.baseDTO.status === "8")
//                    {
//                         if($("#nextOrder").val()==1)
//                                setTimeout(function () {$("#medicationModal").modal('hide');$("#Next").click()},$("#loadingTime").val());
//                            else
//                                setTimeout(function () {$("#medicationModal").modal('hide');$("#backLnk").click()},1000);
//                    }
                }

            },
            error: function (e) {
                alert('Error while processing order...' + e.responseText);
//                $("#popupErrDiv").text("Error while processing order " + e.responseText);
                $("#popUpException").show();
                $("#popupErrDiv").show();
                $("#statusBit").val("0");
            }
        });


        //$("#processingLnk").focus();
    },
    savePharmacyNotificationFn: function () {



        var patientId = $("#patientId").val();
        var orderId = $("#orderId").val();
        var message = $("#messageId").val();
        var critical = $("#isCriticalPT").is(":checked");
        var isCriticalPT = 0;
        if (critical == true)
        {
            var isCriticalPT = 1;//.val();
        }

        if ($("#messageId").val().length == 0) {
            $("#errorSendMessage").text("Required");
            $("#errorSendMessage").attr("style", "color:red;font-size:12px;");
            $("#errorSendMessage").attr("class", "errorMessage pull-right");
            return;
        }
        var data = new FormData();
        data.append("file", $("#attachFile")[0].files[0]);
        data.append("patientId", patientId);
        data.append("orderId", orderId);
        data.append("message", message);
        data.append("isCriticalPT", isCriticalPT);

        var json = {"patientId": patientId, "orderId": orderId, "message": message, "isCriticalPT": isCriticalPT};

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/PharmacyPortal/pharmacyNotifiction",
            type: "POST",
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            cache: false,
            data: data,
            success: function (res) {
                var response = JSON.parse(res);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    console.log(response);
                    $("#errorSendMessage").attr("style", "color:green;font-size:12px;");
                    $("#errorSendMessage").attr("class", "message pull-right");
                    $("#errorSendMessage").text("Message sent sucessfully.");
                    window.pharmacyNotification.resetMessageFn();
                }
                if (statusCode != 200) {
                    console.log(response.errorMessage);
                    $("#errorSendMessage").text(response.errorMessage);
                    $("#errorSendMessage").attr("style", "color:red;font-size:12px;");
                    $("#errorSendMessage").attr("class", "errorMessage pull-right");
                    window.pharmacyNotification.resetMessageFn();
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    /////////////////////////////////////////////////////////////////////////////////
    savePharmacyNotificationForPaientFn: function () {



        var patientId = $("#patientId").val();
        var orderId = "0";
        var message = $("#messageId").val();
        if ($("#messageId").val().length == 0) {
            $("#errorSendMessage").text("Required");
            $("#errorSendMessage").attr("style", "color:red;font-size:12px;");
            $("#errorSendMessage").attr("class", "errorMessage pull-right");
            return;
        }
        var data = new FormData();
        data.append("file", $("#attachFile")[0].files[0]);
        data.append("patientId", patientId);
        data.append("orderId", orderId);
        data.append("message", message);

        var json = {"patientId": patientId, "orderId": orderId, "message": message};

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/PharmacyPortal/pharmacyNotifiction",
            type: "POST",
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            cache: false,
            data: data,
            success: function (res) {
                var response = JSON.parse(res);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    console.log(response);
                    $("#errorSendMessage").attr("style", "color:green;font-size:12px;");
                    $("#errorSendMessage").attr("class", "message pull-right");
                    $("#errorSendMessage").text("Message sent sucessfully.");
                    window.pharmacyNotification.resetMessageFn();
                }
                if (statusCode != 200) {
                    console.log(response.errorMessage);
                    $("#errorSendMessage").text(response.errorMessage);
                    $("#errorSendMessage").attr("style", "color:red;font-size:12px;");
                    $("#errorSendMessage").attr("class", "errorMessage pull-right");
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    ////////////////////////////////////////////////////////////////////////////////
    resetMessageFn: function () {
        $("#messageId").val("");
        $("#attachFile").val("");
    },
    InitializeDiaologBoxMessageHistory: function () {
        window.pharmacyNotification.pharmacyNotificationHistoryDialogBox = $("#dialogMessageHistory").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "30%",
            cache: false,
            title: "Message History",
            open: function (event, ui) {
                //hide titlebar.
                $(".ui-dialog-titlebar").css("font-size", "14px");
                $(".ui-dialog-titlebar").css("font-weight", "normal");
                //$(".ui-dialog").attr("class", "ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-draggable ui-resizable dialogInsurance");
            },
            close: function ()
            {
                $(this).dialog('close');
                $(this).dialog('destroy');
                $(this).html("");
            }
        });

    },
    ComposeHtmlMessageHistory: function () {
        var html = "";

        //member Id
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Message :</span>&nbsp;&nbsp;";
        html += "<span><input id='dialogMessageId' type='text'/></span>";
        html += "</div>";
        //Date
        html += "<div class='col-sm-12' style='padding-top:10px;'>";
        html += "<span>Date :</span>&nbsp;&nbsp;";
        html += "<span><input id='dialogDateId' type='text'/></span>";
        html += "</div>";

        //save & cancel button

        html += "<div class='col-sm-12' style='padding-top:10px;'>";

        html += "<div class='col-sm-6' style='text-align:right;'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='window.pharmacyNotification.CancelActionBtn();' style='color:#FFFFFF; padding-top: 1px;'>Cancel</button>";
        html += "</div>";

        html += "</div>";

        return html;
    },
    CancelActionBtn: function () {
        window.pharmacyNotification.pharmacyNotificationHistoryDialogBox.html("");
        window.pharmacyNotification.pharmacyNotificationHistoryDialogBox.dialog("close");
        window.pharmacyNotification.pharmacyNotificationHistoryDialogBox = null;
    },
    getPharmacyNotificationDetails: function () {

        var patientId = $("#patientId").val();
        var orderId = $("#orderId").val();
        //var message = $("#messageId").val();
        var json = {"patientId": patientId, "orderId": orderId};

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/getpharmacyNotifiction",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    console.log(response);
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
    ShowDialogBoxPharmacyNotification: function () {

        window.pharmacyNotification.InitializeDiaologBoxMessageHistory();
        window.pharmacyNotification.pharmacyNotificationHistoryDialogBox.html(window.pharmacyNotification.ComposeHtmlMessageHistory());
        //window.pharmacyNotification.getPharmacyNotificationDetails();
        //////////////////////////////////////////////
        var orderId = $("#orderId").val();
        var json = {"orderId": orderId};

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/pharmacyNotifictionHistory",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {

                // alert("data " + data);

            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
        ////////////////////////////////////////////

        window.pharmacyNotification.pharmacyNotificationHistoryDialogBox.dialog("open");
    },
    uploadFile: function () {
        $("#attachFile").click();
    },
    validateFileFormat: function () {
        var val = $("#attachFile").val().toLowerCase();

        var regex = new RegExp("(.*?)\.(zip|exe|eml|msg|msi)$");
        if (regex.test(val)) {
            $("#attachFile").val('');
            $("#errorSendMessage").text("Invalid file format");
            $("#errorSendMessage").attr("style", "color:red;font-size:12px;");
            $("#errorSendMessage").attr("class", "errorMessage pull-right");
            return false;
        }
        return true;
    },
    validatePhoneNumber: function (val, fId) {
        if (val.length > 0) {
            val = val.replace("-", "");
            val = val.replace("-", "");
            val = val.replace("(", "").replace(")", "");
            if (val.length == 10)
                return true;
            else
            {
                $("#popUpException").text("Please enter valid 10 digit phone number.");
                $("#popUpException").attr("style", "color:red; font-weight:bold");
                $("#" + fld).focus();
                return false;
            }
//            alert("VAL "+val+" len "+val.length)
//                var reg1 = /^\[0-9]{3}-[0-9]{3}-[0-9]{4}$/;
//                var reg2 = /^\([0-9]{3}\)[0-9]{3}-[0-9]{4}$/;
//
//                var reg4 = /^\d{10}$/;
//
//                if (!reg1.test(val)&& !reg2.test(val)&&!reg4.test(val)) {
//                    $("#popUpException").text("Provide valid Phone number either as(000)000-0000 or as 000-000-0000 or as 1234567890");
//                    $("#popUpException").attr("style", "color:red;display:block;");
//                    $("#" + fId).focus();
//                    return false;
//                }
//            return true;
        }
        return true;
        // var patt = /^\s*(?:\+?(\d{1,3}))?[-]*(\d{3})[-]*(\d{3})[-]*(\d{4})(?: *[x/]{1}(\d+))?\s*$/;
    }, scrollToElement: function (ele) {
        $(window).scrollTop(ele.offset().top).scrollLeft(ele.offset().left);
    }, validateDrugFields: function (msgType, checkVerifiedImage)
    {
        this.scrollToElement($("#popUpException"));
        if ($("#popUpException1") != null)
        {
            $("#popUpException1").hide();
        }
        if (!this.isVerifiedImage() && checkVerifiedImage) {
            return false;
        }
        if ($("#referenceBrandDetail").val() == '')
        {
//            alert("Please specify proper drug.");
            $("#popUpException").text("Please specify proper drug.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugNameIdDetail").focus();
            return false;
        }
        if ($("#drugTypeIdDetail").val() == '')
        {
//            alert("Please select drug type.");
            $("#popUpException").text("Please select dosage type.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugTypeIdDetail").focus();
            $("#drugTypeIdDetail").css("border", "1px solid red");
            return false;
        }

        if ($("#drugStrengthDetail").val() == '')
        {
//            alert("Please select drug strength.");
            $("#popUpException").text("Please select drug strength.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugStrengthDetail").focus();
            return false;
        }
        if ($("#drugQtyFld").val() == '' || parseFloat($("#drugQtyFld").val()) <= 0)
        {
//            alert("Please specify quantity.");
            $("#popUpException").text("Please specify quantity.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#drugQtyFld").focus();
            return false;
        }
        if ($("#daysSupplyFld").val() == '')
        {
//            alert("Please enter days supply.");
            $("#popUpException").text("Please enter days supply.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#daysSupplyFld").focus();
            return false;
        }
        if ($("#daysSupplyFld").val() == 0) {
//            alert("Days supply must be greater than 0.");
            $("#popUpException").text("Days supply must be greater than 0.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
//                 $("#messagePopUpModal").modal('show');
            $("#daysSupplyFld").focus();
            return false;
        }
        this.hideMsg("popUpException");
        return true;
    }, validateFinalPayment: function () {
        this.scrollToElement($("#popUpException"));
        this.truncateFldValue('finalPaymentFeeFld', 1, 1, '$');
        if ($("#finalPaymentFeeFld").val().length == 0) {
            $("#popUpException").text("Final payment is required.");
            $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
            $("#finalPaymentFeeFld").focus();
        }
        var finalpayment = parseFloat($("#finalPaymentFeeFld").val().replace("$", ""));
        if (finalpayment == 0) {
            $("#popUpException").text("Final payment must be greater than 0.");
            $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
            $("#finalPaymentFeeFld").focus();
            return false;
        }
        this.hideMsg("popUpException");
        return true;
    },
    ////////////////////////////////////////////////////////////////
    validateFinalPaymentForPaymentFailureMsg: function () {
//        $("#" + fld).val(str.replace(/,/, ""));
//        alert("EXCEPTION DIv "+$("#popUpException1"));
        if ($("#popUpException1") != null)
        {
            $("#popUpException1").hide();

        }
        var fldVal = $("#finalPaymentCol").html();
        if (fldVal.length == 0)
        {
            $("#popUpException").text("Final payment is required.");
            $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");

        }
        fldVal = fldVal.replace(/,/, "").replace('$', "");
        this.scrollToElement($("#popUpException"));
//        alert("fional payment "+fldVal);
        var finalpayment = parseFloat(fldVal);
        if (finalpayment == 0) {
            $("#popUpException").text("Final payment must be greater than 0.");
            $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
            $("#finalPaymentFeeFld").focus();
            return false;
        }
        this.hideMsg("popUpException");
        return true;
    },
    ///////////////////////////////////////////////////////////////
    hideMsg: function (divId) {
        if ($("#" + divId).text().length > 0) {
            $("#" + divId).text("");
            $("#" + divId).attr("style", "display:none;");
        }

        if ($("#statusException").text().length > 0) {
            $("#statusException").text("");
            $("#statusException").attr("style", "display:none;");
        }

    }, isVerifiedImage: function () {
        var drugIncluded = $("#verifiedImg").val();
        if (drugIncluded == 1) {
            $("#popUpException").text("Please verify image first.");
            $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
            return false;
        }
        this.hideMsg("popUpException");
        return true;
    },
    ////////////////////////////////////////////////////////////////
    waiveDeliveryFee: function (deliveryFeeStr, totalStr)
    {
        //alert("delivery fee "+deliveryFeeStr+" checkbox state "+$("#waiveDeliveryFeeChkbox").prop("checked"));
        deliveryFee = parseFloat(deliveryFeeStr);
        total = parseFloat(totalStr);
        if ($("#waiveDeliveryFeeChkbox").prop("checked") == true)
        {

            total = total - deliveryFee;
            $("#handlingFeeFld").val("0");

        } else
        {
            $("#handlingFeeFld").val(deliveryFeeStr);
        }
        $("#finalPaymentCol").html("$" + total.toString());
        $("#netAmountBtn").val("Amount to be Collected:$" + total.toString());
        this.multiRxOrder();
    },
    ///////////////////////////////////////////////////////////////
    multiRxOrder: function ()
    {
//alert($('#multiRxC').is(':checked'))
//        var statusBit = $("#statusBit").val();
        var orderId = $("#orderId").val();
        var patientId = $("#patientId").val();
        var waiveDeliveryFee = $("#waiveDeliveryFeeChkbox").prop("checked") == true ? "1" : "0";
//        alert("waive delivery fee "+waiveDeliveryFee+" url "+"/ConsumerPortal/multirxOrder/" + patientId + "/" + orderId+"/"+waiveDeliveryFee);
        if ($("#multiRxC").prop("checked") == true) {
            $("#shippingInfoTB").empty();
            $("#show_div").show();
//            alert("o" + orderId + " pi" + patientId);
            $.ajax({
                url: window.pharmacyNotification.appUrl + "/ConsumerPortal/multirxOrder/" + patientId + "/" + orderId + "/" + waiveDeliveryFee,
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
//                alert("data "+data + "ol "+data.baseDTO.olVersion + "status "+data.baseDTO.status);
                    data = JSON.parse(data);
                    var total = 0;
                    $.each(data.dependants, function (key, value) {
//                            alert(value.drugName + " "+value.id);
//                            alert(value.id);
                        total = total + value.finalPayment;
                        var html = "";
                        html = '<tr>';
                        html += '<td><input id="chk' + value.id + '" name="multirxDCB" type="checkbox" value="' + value.id + '" style="" checked onclick="window.pharmacyNotification.totalCollectedAmount(\'chk\',' + value.id + ');"></td>';
                        html += '<td>RXD-' + value.systemGeneratedRxNumber + '</td><td>' + value.drugName + " " + value.drugType + " " + value.strength + '</td>';
                        html += '<td>' + value.deliveryPreferencesName + '</td>';
                        html += '<td class=text_right>' + value.handlingFeeStr + '</td>';
                        html += '<td id="finalPayment' + value.id + '" class="text_right">' + value.finalPaymentStr + '</td>';
                        html += '</tr>';
                        $('#shippingInfoTB').append(html);
                        //$('#shippingInfoTB').append('<tr><td><input name="multirxDCB" type="checkbox" value="' + value.id + '" style="" checked></td><td>RXD-' + value.id + '</td><td>' + value.drugName + " " + value.drugType + " " + value.strength + '</td><td>' + value.deliveryPreferencesName + '</td><td class=text_right>' + value.handlingFeeStr + '</td><td class=text_right>' + value.finalPaymentStr + '</td></tr>');

                    });
                    //total = total + parseFloat($("#paymentExcludingDeliveryHidden").val());
//                    total=Math.round(total/100)*100;
//                    $("#netAmountBtn").val("Amount to be Collected:"+addCommasToValue(Math.round10(total,-2),'netAmountBtn',"$"));
//                    addCommas('netAmountBtn');
                    $("#totalPayment").val(total.toFixed(2));
                    $("#netAmountBtn").val(" Amount to be Collected:$" + total.toLocaleString());
                    //addCommasToValue(total.toFixed(2), 'netAmountBtn', "$");
//                    debugger;
//                    alert(data);


                },
                error: function (e) {
//                    alert('Error while processing order...' + e.responseText);
                    $("#statusException").text("Error while processing order " + e.responseText);
                    $("#statusException").attr("style", "color:red; font-weight:bold");
                    $("#statusBit").val("0");
                }
            });
        } else {
            $("#show_div").hide();
            $("#shippingInfoTB").empty();
        }
        return;
    },
    /////////////////////////////////////
    shippedOrder: function ()
    {
        var orderId = $("#orderId").val();
        var patientId = $("#patientId").val();
        var multiRxIds = [];
        if ($("#multiRxC").prop("checked") == true) {

            $($('[name=multirxDCB]').each(function () {
                if ($(this).is(":checked")) {
//                    alert("m r x c "+$(this).val());
                    multiRxIds.push($(this).val());
                }
            }));
            alert(multiRxIds);

            return;
        }
    },
    //////////////////////////
    validateImageVerifiedFields: function ()
    {
        var validate = true;
//        $("#interpretedImageLnk").attr("disabled", true);
        $("#popUpException").text("");
        $("#popUpException").hide();
        if ($("#drugNameIdDetail").val() == '')
        {
            $("#referenceBrandDetail").focus();
            validate = false;
            return validate;
        }

        if ($('#drugStrengthDetail > option').length <= 0)
        {
            $("#drugStrengthDetail").focus();
            validate = false;
            $("#drugStrengthDetail").css("border", "1px solid red");
            return validate;
        }

        if ($('#drugTypeIdDetail > option').length <= 0)
        {
            $("#drugTypeIdDetail").focus();
            validate = false;
            $("#drugTypeIdDetail").css("border", "1px solid red");
            return validate;
        }

//        if (validate) {
//            $("#interpretedImageLnk").removeAttr("disabled");
//        }
        return validate;
    },
    ///////////////////////////////////
    newOnlineOrder: function (loadFlag, statusVal)
    {
        this.eliminateCurrencySignFromNumricFlds();

        var processingBit = $("#processingID").val();
        var statusBit = $("#statusBit").val();
        var orderId = $("#orderId").val();
        var patientId = $("#patientId").val();
        var dependentId = $("#dependentId").val();
        var message = $("#processMessage").val();

        /////////////////////////////////////////////
        // alert($("#drugNameIdDetail").val());
        var drugNameDbId = $("#referenceBrandDetail").val();
        var drugType = $("#drugTypeIdDetail").val();
        var drugStrength = $("#drugStrengthDetail").val();
        var rxNumberFld = $("#rxNumberFld").val();
        var drugQtyFld = $("#drugQtyFld").val();
        var daysSupplyFld = $("#daysSupplyFld").val();
        var refillAllowedFld = $("#refillAllowedFld").val();
        var refillUsedFld = $("#refillUsedFld").val();
        var paymentFld = $("#paymentFld").val();
        var patientNameFld = '';//$("#patientNameFld").val();
        var pharmacyRxNoFld = $("#pharmacyRxNoFld").val();
        var pharmacyNameFld = $("#pharmacyNameFld").val();
        var pharmacyPhoneFld = $("#pharmacyPhoneFld").val();
        var prescriberNameFld = $("#prescriberNameFld").val();
        var prescriberPhoneFld = $("#prescriberPhoneFld").val();
        var prescriberNPIFld = $("#prescriberNPIFld").val();
        var acqCostFld = $("#acqCostFld").val();
        var reimbFld = $("#reimbFld").val();
        var ptCopayFld = $("#ptCopayFld").val();
        var deliverycarrier = $("#deliverycarrier").val();
        var traclingno = $("#traclingno").val();
        var clerk = $("#clerk").val();

        var finalPaymentFld = $("#finalPaymentFeeFld").val();

        var orderComments = $("#orderComments").val();
        var mfrCost = $("#mfrCostFld").val();

        var profitMargin = !isNaN($("#profitMarginCol").val()) ? $("#profitMarginCol").val() : 0;
        //alert("profit "+profitMargin);
        var profitSharePoint = $("#totalRedeemPointsId").val();//.html();
        //alert("profitSharePoint "+profitSharePoint);
        var actualProfitShare = $("#redeemPointsCostFld").val();//html();
        //alert("actualProfitShare "+actualProfitShare);


        var insuranceType;
        if ($("#radio-a").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Public";
        } else if ($("#radio-b").is(":checked"))
        {
//                alert("PUBLIC ");
            insuranceType = "Cash";
        } else if ($("#INS").is(":checked"))
        {
            insuranceType = "Commercial";
        } else {
            $("#popUpException").text("Please select insurance type.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            return false;
        }

        var pref = $("#pref").val();

        if (pref == "-1")
        {
            $("#popUpException").text("Please select delivery preferences.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            return false;
        }

        var priceIncludingMargins = $("#estimatedDrugPrice").val();
        var rxExpireDate = $("#rxExpiredDate").html(); //$("#expiredDateFld").val();
        var rxDate = $("#datetimepicker").val();
        ////////////////////////////////////////////

        var changeStatus = "0";

        if (statusBit != "0")
        {
            changeStatus = "1";
        }

        var sellingPrice = $("#sellingPriceFld").val();
        var olversion = $("#olversion").val();
        var handlingFee = $("#handlingFeeFld").val();

        var multiRxIds = [];
        var multiRx = false;
        if ($("#multiRxC").prop("checked") == true) {
            multiRx = true;
            $($('[name=multirxDCB]').each(function () {
                if ($(this).is(":checked")) {
                    multiRxIds.push($(this).val());
                }
            }));
        }

        var json = {"changeStatus": changeStatus, "message": message, "statusBit": statusBit, "rxNumberFld": rxNumberFld,
            "name": drugNameDbId, "drugType": drugType, "patientId": patientId, "dependentId": dependentId,
            "drugStrength": drugStrength, "drugQty": drugQtyFld, "daysSupplyFld": daysSupplyFld, "refillAllowedFld": refillAllowedFld,
            "refillUsedFld": refillUsedFld, "paymentFld": paymentFld, "patientNameFld": patientNameFld, "pharmacyRxNoFld": pharmacyRxNoFld, "pharmacyNameFld": pharmacyNameFld,
            "pharmacyPhoneFld": pharmacyPhoneFld, "prescriberNameFld": prescriberNameFld, "prescriberPhoneFld": prescriberPhoneFld,
            "prescriberNPIFld": prescriberNPIFld, "acqCostFld": acqCostFld, "reimbFld": reimbFld, "ptCopayFld": ptCopayFld,
            "finalPaymentFld": finalPaymentFld, "deliverycarrier": deliverycarrier, "traclingno": traclingno, "clerk": clerk,
            "orderComments": orderComments, "mfrCost": mfrCost, "insuranceType": insuranceType, "priceIncludingMargins": priceIncludingMargins,
            "rxExpireDate": rxExpireDate, "rxDate": rxDate, "profitMargin": profitMargin,
            "profitSharePoint": profitSharePoint, "actualProfitShare": actualProfitShare,
            "sellingPrice": sellingPrice, "olversion": olversion, "handlingFee": handlingFee,
            "multiRxIds": multiRxIds, "multiRx": multiRx, "pref": pref};
        ////////////////////////////////////////
        // alert("DATA "+JSON.stringify(json));
        //alert("json");
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/saveOnlineOrder/",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (loadFlag == "1") {
                    $("#backLnk").click();
                } else
                {
                    $("#orderStatusSpan").html(statusVal);
                    $("#popUpException").text("Order has been saved successfully.");
                    $("#popUpException").attr("style", "color:green ; font-weight:bold");
                    $("#statusException").text("");
                    $("olversion").val(data.baseDTO.olVersion);
                    location.href = window.pharmacyNotification.appUrl + '/ConsumerPortal/rxDetail/' + data.baseDTO.orderId + '/' + patientId;
                }
            },
            error: function (e) {
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });
    },
    ///////////////////////////
    showAddNewRx: function () {
        $("#addNewRxOrderDlg").modal('show');
    },
    /////////// get patient information //////////////
    getPatientInformationByPhoneNumber: function () {
        var mobileNumber = $("#addNewRxPatientMobileNo").val();
        if (mobileNumber !== '') {
            $.ajax({
                url: window.pharmacyNotification.appUrl + "/ConsumerPortal/patientDetailByMobileNumber/" + mobileNumber,
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
                    data = JSON.parse(data);
                    $("#addNewRxpatientInformation").empty();
                    if (data.auditFields !== null) {
                        var url = window.pharmacyNotification.appUrl + '/ConsumerPortal/newOnlineRxDetail/' + data.auditFields.id + '/0';
                        $("#addNewRxpatientInformation").append('<br/>Full Name: ' + data.auditFields.firstName + ' ' + data.auditFields.lastName + ' ' + ' <br />');
                        $("#addNewRxpatientInformation").append('Have Dependent: <input id="haveDependents" name="haveDependents" type="checkbox" onclick="window.pharmacyNotification.checkPatientDependents();" value="' + data.auditFields.id + '" > <br>');
                        $("#addNewRxpatientInformation").append('<a id="proceedLink" href="' + url + '">Proceed ?</a>');
                        $("#addNewRxpatientInformation").attr("style", "color:green ; font-weight:bold");
                    } else {
                        $("#addNewRxpatientInformation").append('No Record Found');
                        $("#addNewRxpatientInformation").attr("style", "color:red ; font-weight:bold");
                    }
                },
                error: function (e) {
                    $("#addNewRxpatientInformation").append('No Record Found');
                    $("#addNewRxpatientInformation").attr("style", "color:red ; font-weight:bold");
                }
            });
        }
        return;
    },
    checkPatientDependents: function () {
        var patientId = $("#haveDependents").val();
        if ($("#haveDependents").prop("checked") == true) {
            $("#proceedLink").remove();
            $("#dependentId").remove();
            $.ajax({
                url: window.pharmacyNotification.appUrl + "/ConsumerPortal/checkPatientDependents/" + patientId,
                type: "POST",
                contentType: 'application/json',
                success: function (data) {
//                alert("data "+data + "ol "+data.baseDTO.olVersion + "status "+data.baseDTO.status);
                    data = JSON.parse(data);
                    if (data.dependants.length > 0) {
                        var create = '<select id="dependentId" onchange="window.pharmacyNotification.chooseDependents();"><option value="0"></option>';
                        $.each(data.dependants, function () {
                            if (this.isAdult == true)
                                create += '<option value="' + this.id + '" style=\"color:red\">' + this.firstName + " " + this.lastName + '(Adult)</option>';
                            else
                                create += '<option value="' + this.id + '">' + this.firstName + " " + this.lastName + '</option>';
                        });
                        create += '</select>';
                        $("#addNewRxpatientInformation").append(create);
                        var href = window.pharmacyNotification.appUrl + "/ConsumerPortal/newOnlineRxDetail/" + patientId + "/0";
                        $("#addNewRxpatientInformation").append('<br><a id="proceedLink" href="' + href + '">Proceed ?</a>');
                    } else {
                        $("#addNewRxpatientInformation").text("This patient does't have any depended.");
                        $("#addNewRxpatientInformation").attr("style", "color:red; font-weight:bold");
                        var href = window.pharmacyNotification.appUrl + "/ConsumerPortal/newOnlineRxDetail/" + patientId + "/0";
                        $("#addNewRxpatientInformation").append('<br><a id="proceedLink" href="' + href + '">Proceed ?</a>');
                    }
                },
                error: function (e) {
//                    alert('Error while processing order...' + e.responseText);
                    $("#statusException").text("Error while processing order " + e.responseText);
                    $("#statusException").attr("style", "color:red; font-weight:bold");
                    $("#statusBit").val("0");
                }
            });
        } else {
            $("#proceedLink").remove();
            $("#dependentId").remove();
        }

        return;


    },
    chooseDependents: function () {

        var patientId = $("#haveDependents").val();
        var dp = $("#dependentId").val();
//        alert("dp"+dp);
//        alert("select d"+patientId);
        $("#proceedLink").attr("href", window.pharmacyNotification.appUrl + "/ConsumerPortal/newOnlineRxDetail/" + patientId + "/" + dp);//append('<br><a href="/ConsumerPortal/newOnlineRxDetail/' + patientId + '/'+dp+'">Proceed ?</a> <br />');
    },
    refillRemainingOverride: function ()
    {


        $("#refillUsedFld").removeAttr("disabled");
        $("#refillAllowedFld").removeAttr("disabled");
//        $("#saveBtn").removeAttr("disabled");
        $("#popUpException").hide();
        $("#button1").hide();

    },
    ptOverrideEvent: function ()
    {
        $("#ptOverridePriceFld").val(1);
        $("#acqCostFld").removeAttr("disabled");
        $("#ptCopayFld").removeAttr("disabled");
        $("#reimbFld").removeAttr("disabled");
        $("#totalRedeemPointsId").removeAttr("disabled");
        $("#finalPaymentFeeFld").removeAttr("disabled");
    },
    selfPayEvent: function () {
        //alert("A");
        if ($("#radio-b").is(':checked'))
        {
            $("#estPriceFld").attr("disabled", "disabled");
//            $("#reimbFld").attr("disabled", "true");
//            $("#reimbFld").val("$0.0");
        } else
        {
            //alert("B");
            $("#reimbFld").removeAttr("disabled");
        }

        if ($("#radio-a").is(':checked'))
        {
            $("#estPriceFld").removeAttr("disabled");
            $("#redeemPointsCostFld").attr("disabled", "true");
            $("#redeemPointsCostFld").val("$0.0");
            window.pharmacyNotification.calculateReimbursementAndProfit('sellingPriceFld', 'ptCopayFld', 'acqCostFld');
            window.pharmacyNotification.calculatePayment('sellingPriceFld', 'ptCopayFld', 'acqCostFld');


        } else
        {
            $("#redeemPointsCostFld").removeAttr("disabled");
        }
    },
    loadNextImage: function ()
    {
        $('.dvLoading').show();
//        alert("HI");
        var orderId = $("#orderId").val();
        var imageId = $("#imageId").val();
        //alert("Image Id= "+imageId+" order "+orderId);
        ////////////////////////////////////////
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/rxTransfer/loadNextImage/" + orderId + "/" + imageId,
            type: "GET",
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            success: function (data) {

                // var responses = eval(data);
//                alert(" "+data.transferImage+" prev "+data.prevImage+" next "+data.nextImage+" replace "+ data.transferImage.replace(/"/g,'') );
//                alert(data.replace(/"/g,''));
                $(".drugImg").attr('src', data.transferImage.replace(/"/g, ''));
                $(".prevImage").show();
                if (data.nextImage == false)
                {
                    $(".nextImage").hide();
                }
                $("#imageId").val(data.imageId);
            },
            error: function (e) {
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });

    },
    loadPrevImage: function ()
    {
        $('.dvLoading').show();
//        alert("HI");
        var orderId = $("#orderId").val();
        var imageId = $("#imageId").val();
        //alert("Image Id= "+imageId+" order "+orderId);
        ////////////////////////////////////////
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/rxTransfer/loadPrevImage/" + orderId + "/" + imageId,
            type: "GET",
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {

                // var responses = eval(data);
//                alert(" "+data.transferImage+" prev "+data.prevImage+" next "+data.nextImage+" replace "+ data.transferImage.replace(/"/g,'') );
//                alert(data.replace(/"/g,''));
                $(".drugImg").attr('src', data.transferImage.replace(/"/g, ''));
                $(".nextImage").show();
                if (data.prevImage == false)
                {
                    $(".prevImage").hide();
                }
                $("#imageId").val(data.imageId);

            },
            error: function (e) {
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });

    },
    displayValidationSuccessMsg: function ()
    {
        $("#popUpException").text("All required fields are specified.");
        $("#popUpException").attr("style", "color:green; font-weight:bold;display:block;");
    },
    verifyOrderFlds: function ()
    {
        var currStatus = $("#statusHiddenFld").val();
        this.hideMsg("statusException");
        if (currStatus == "Unverified Image") {

            if (this.verifyCommonFlds())
            {
                this.displayValidationSuccessMsg();
            }

        } else if (currStatus == "Pending" || currStatus == "Waiting payment authorization" ||
                currStatus == "Pending Review" || currStatus == "Processing" || currStatus == "Filled") {

            if (this.verifyCommonFlds())
            {
//                alert("A");
                if (this.verifyProcessingFlds(currStatus))
                {
                    this.displayValidationSuccessMsg();
                }

            }

        }
    },
    verifyCommonFlds: function ()
    {
        //alert("A");
        var err = "";
        var flag = true;
        if ($("#drugNameIdDetail").val() == '')
        {
//            alert("Please specify proper drug.");
            err += "Please specify proper drug;";
            flag = false;
        }

        if ($("#drugStrengthDetail").val() == '')
        {
            err += "Please select drug strength;";
            flag = false;

        }

        if ($("#drugTypeIdDetail").val() == '')
        {
//            alert("Please select drug type.");
            err += "Please select drug type;";
            flag = false;
        }


        if ($("#drugQtyFld").val() == '' || parseFloat($("#drugQtyFld").val()) <= 0)
        {
            err += "Please specify quantity;";
            flag = false;
        }
        if (!flag)
        {
            $("#popUpException").text(err);
            $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
        }

        return flag;

    },
    verifyProcessingFlds: function (status)
    {
//        alert("1");
        var err = "";
        var flag = true;

        if (!$("#INS").is(":checked") && !$("#radio-a").is(":checked") && !$("#radio-b").is(":checked")) {
            err += "Please select insurance type;";
            flag = false;
        }
        var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
        var rxDate = $("#datetimepicker").val();
        if (rxDate == "") {

//            alert("Please enter original date.");
            err += "Please enter original date;";
            flag = false;
        }
//        alert("2");
        if (rxDate != '')
        {
            if (!(date_regex.test(rxDate)))
            {
//                alert("Please specify date in mm/dd/yyyy format.");
                err += "Please specify date in mm/dd/yyyy format;";
                flag = false;
            }

            var newDate = new Date().getTime();
            var prescDate = new Date(rxDate).getTime();
            if (prescDate > newDate)
            {
//                alert("Rx date can't be future date.");
                err += "Rx date can't be future date;";
                flag = false;
            }
        }
//        alert("3");

        if (!$("#INS").is(":checked") && !$("#radio-a").is(":checked") && !$("#radio-b").is(":checked")) {
            err += "Please select insurance type;";
            flag = false;
        }
//        alert("4");
        if ($("#daysSupplyFld").val() == '')
        {
//            alert("Please enter days supply.");
            err += "Please enter days supply;";
            flag = false;
        }
//        alert("5");
        if ($("#daysSupplyFld").val() == 0) {
//            alert("Days supply must be greater than 0.");
            err += "Days supply must be greater than 0;";
            flag = false;
        }
        if ($("#refillAllowedFld").val() == '')
        {
//            alert("Please enter refill allow.");
            err += "Please enter refill allow;";
            flag = false;
        }
        if ($("#refillUsedFld").val() == '')
        {
//            alert("Please enter refill remain.");
            err += "Please enter refill remain;";
            flag = false;
        }
        if ($("#labelScanFld").val() == '1')
        {

            if ($("#pharmacyNameFld").val() == '')
            {
                //            alert("Please enter pharmacy name.");
                err += "Please enter pharmacy name;";
                flag = false;
            }
            if ($("#pharmacyPhoneFld").val() == '')
            {
                //            alert("Please enter pharmacy phone.");
                err += "Please enter pharmacy phone;";
                flag = false;
            }
            if (!this.validatePhoneNumber($("#pharmacyPhoneFld").val(), 'pharmacyPhoneFld')) {
                err += "Provide valid Pharmacy Phone number either as(000)000-0000 or as (000)000-0000-xxxx;";
                flag = false;
            }
        }
        if ($("#rxScanFld").val() == '1')
        {
            if ($("#prescriberLastNameFld").val() == '')
            {
                //            alert("Please enter prescriber name.");
                err += "Please enter prescriber name;";
                flag = false;
            }
            if (!this.validatePhoneNumber($("#prescriberPhoneFld").val(), 'prescriberPhoneFld')) {
                err += "Provide valid Prescriber Phone number either as(000)000-0000 or as (000)000-0000-xxxx;"
                flag = false;
            }
        }
        var refill = parseFloat($("#refillAllowedFld").val());

        var remaining = parseFloat($("#refillUsedFld").val());
        //alert("refill: "+refill+" remaining: "+remaining);
        if (refill < remaining)
        {
//            alert("Refill remaning can not be grator than refill allowed.");
            err += "Refill remaning can not be greater than refill allowed;";
            flag = false;
        }
        if ($("#acqCostFld").val() == '')
        {
//            alert("Please enter ingredient cost.");
            err += "Please enter ingredient cost;";
            flag = false;
        }
        if (parseFloat($("#ptCopayFld").val()) < 0)
        {
            err += "Copay field value should be greater than 0;";
            flag = false;
        }
        if (parseFloat($("#reimbFld").val()) < 0)
        {
            err += "Reimbursement field value should be greater than 0;";
            flag = false;
        }

        if ($("#sellingPriceFld").val() == '')
        {

            err += "Please enter selling price;";
            flag = false;
        }
        if (!$('#radio-b').is(':checked')) {
            var reimb = parseFloat($("#reimbFld").val());//parseFloat(document.getElementById(reimbFld).val());
            var acq = parseFloat($("#acqCostFld").val());
            if (acq <= 0)
            {
//                alert("Acq should be greater than 0.");
                err += "Acq should be greater than 0;";
                flag = false;
            }

        }
        if (status == "Filled")
        {
            if ($("#systemGeneratedRxNumberFld").val().trim() == '')
            {
                err += "Please specify System Generated Rx#;";
                flag = false;
            }
        }
        if (!flag)
        {
            $("#popUpException").text(err);
            $("#popUpException").attr("style", "color:red; font-weight:bold;display:block;");
        }
        return flag;
    },
    zoomin: function (image)
    {
        var currentZoom = 10.0;
        $('#' + image).animate({'zoom': currentZoom += .1}, 'slow');
    },
    addCommasToValue: function (val, fld, currencySymbol)
    {

//            alert("Val");
        var nStr = "" + val;
        nStr = nStr.replace(/,/, "");
        var x = nStr.split('.');
        var x1 = x[0];
        var x2 = '';
        if (x.length > 1)
        {
            if (x[1].length == 1)
            {
                x[1] = x[1] + '0';
            }
            x2 = '.' + x[1];
        } else
        {
            x2 = x.length > 1 ? '.' + x[1] : '';
        }
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        x1 = currencySymbol + x1;

        $("#" + fld).val(x1 + x2);
        //this.appendFldValue(fld,1,'$');


        //return  x1 + x2;
    },
    addCommas: function (fld, decimalFlag)
    {

        //////////////////////////////////////////
        //this.truncateFldValue(fld,1,1);
//        var n= $("#"+fld).val();
//        n=n.replace(',','');
//        var dp=69;
        //    var s = ''+(Math.floor(n)), i = s.length, r = '';
//  while ( (i -= 3) > 0 ) { r = ',' + s.substr(i, 3) + r; }
//  alert( "VAL "+s.substr(0, i + 3) + r );
//  return;
        ///////////////////////////////////////////////////

//            var nStr = $("#"+fld).val();
//            var x = nStr.split('.');
//            var n = x[0];
//            //alert("val "+n)
//            n=n.replace(',','');
//            var x2 = x.length > 1 ? '.' + x[1] : '';
//            var s = n;//''+(Math.floor(n)); 
//            var i = s.length;
//            var r = '';
//            while ( (i -= 3) > 0 ) 
//            {
//                r = ',' + s.substr(i, 3) + r; 
//            }
//            return $("#"+fld).val(s.substr(0, i + 3) + r ) + x2;     '
        var nStr = $("#" + fld).val();
        nStr = nStr.replace(/,/, "");
        var x = nStr.split('.');
        var x1 = x[0];
        var x2 = x.length > 1 ? '.' + x[1] : '.00';
//            alert("FLAG "+decimalFlag);
        if (decimalFlag == 0)
        {
            var x2 = x.length > 1 ? '.' + x[1] : '';
        }
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        if (fld != 'totalRedeemPointsId' && !x1.startsWith('$'))
        {
            x1 = '$' + x1;
        }
        $("#" + fld).val(x1 + x2);
        //this.appendFldValue(fld,1,'$');


        //return  x1 + x2;
    },
    uploadFile2: function (target)
    {
        $("#" + target).trigger("click");
    },
    displayFile: function (files, imgId, name, type, filevalue) {
//        alert("type "+type);
        if (type == "pdf") {
//            alert("for pdf file ");
            var res = this.validateFileType(type, filevalue, files);
//            alert("res "+res);
            if (!res) {
//                alert("A");
                $("#responseMsg2").text("Please upload only pdf file.");
                $("#responseMsg2").attr("class", "errorMessage");
                $("#responseMsg2").show();
                return;
            } else {
                var fileName = $('#drugPdffile2').val().split("\\").pop();
//               var text = "Attached File:";
//               $('#textColor').html(text);
//               $("#textColor").attr("style", "color:black; font-weight:bold;");
                $('#lbfileName').html("Attached File: " + fileName);
                $("#lbfileName").attr("style", "color:black; font-weight:bold;");

//               $("#lbfileName").attr("style", "color:blue; font-weight:bold;");
                $("#responseMsg2").hide();
                $("#" + name).val("customDoc" + "." + type);
            }
        }
        if (type == "png") {
            var res = this.validateFileType(type, filevalue, imgId);
            if (!res) {
                $("#responseMsg").text("Please upload only png,gpg images.");
                $("#responseMsg").attr("class", "errorMessage");
                $("#responseMsg").show();
                return;
            }
            $("#responseMsg").hide();
            // show image on screen
            if (files) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $("#" + imgId).attr('src', e.target.result);
                };
                if (type == "png") {
                    $("#" + name).val($("#gcnTxtId").val() + "." + type);
                } else {
                    $("#" + name).val($("#gcnTxtId").val() + "." + type);
                }
                reader.readAsDataURL(files);
            }
        }

    },
    validateFileType: function (type, file, fileId) {
        //alert("validating");
        var ext = file.split(".");
        ext = ext[ext.length - 1].toLowerCase();
        var arrayExtensions;
        if (type == "pdf") {
            arrayExtensions = ["pdf"];
        } else {
            arrayExtensions = ["jpg", "jpeg", "png", "bmp", "gif"];
        }
        //alert("last index of "+arrayExtensions.lastIndexOf(ext));
        if (arrayExtensions.lastIndexOf(ext) == -1) {
            $("#" + fileId).val("");
            return false;
        }
        return true;
    },
    checkingCheckBoxValueInTable: function (tbl, actionBtn)
    {

        var flag = true;
        var itemIds = '';
        $('#' + tbl + ' tr').each(function () {
            if ($(this).find("td:first").find(":input") != null && $(this).find("td:first").find(":input:checkbox").is(':checked'))
            {

                $("#" + actionBtn).attr("disabled", false);
                itemIds += $(this).find("td:first").find(":input").val() + ",";
                flag = false;

            }

        });
//         alert(" id "+itemIds);
        $("#itemIds").val(itemIds);
//         alert("item ids "+$("#itemIds").val());
        return flag;

    },
    controllingActionButtonForPage1: function (tbl, actionBtn)
    {

        var flag = this.checkingCheckBoxValueInTable(tbl, actionBtn);
        $("#" + actionBtn).attr("disabled", flag);
        $("#delBtn").attr("disabled", flag);

    },
    controllingActionButtonForRefill: function (tbl, actionBtn)
    {

        var flag = this.checkingCheckBoxValueInTable(tbl);
        $("#" + actionBtn).attr("disabled", flag);


    },
    refillSelectedOrders: function ()
    {
        var orderIds = $("#itemIds").val();
        var json = {"commaSeparatedOrderNode": orderIds, "actionType": 1};
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/refillOrders/",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                $("#refillOrder").val(data);
                $("#refillLnk").click();


            },
            error: function (e) {
//               alert("error");
                //alert('Error while processing order...' + e.responseText);
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });
    },
    deleteSelectedOrderHandler: function ()
    {
        $("#confirmDiv").modal('show');
    },
    deleteSelectedOrder: function ()
    {
        var orderIds = $("#itemIds").val();
        var ss = orderIds.split(",");
        for (var i in ss)
        {
            if (ss[i] != '')
            {
//                alert("A")
                $("#orderId").val(ss[i]);
//                alert("B "+$("#orderId").val());
                this.deleteOrder(0);
                $("#popUpException").show();
                setTimeout(function () {
                    $("#backLnk").click();
                }, $("#loadingTime").val());

            }
        }
    },
    openSelectedOrders: function ()
    {
        var orderIds = $("#itemIds").val();
        var ss = orderIds.split(",");
        for (var i in ss)
        {
            if (ss[i] != '')
            {
//                alert("A")
                $("#orderId").val(ss[i]);
                break;
//                alert("B "+$("#orderId").val());


            }
        }
        $("#fwdLnk").click();
    },
    loadOrderForNextPatient: function ()
    {
        $("#nextPatientLnk").click();
    },
    refillOverride: function ()
    {
        var order = $("#orderId").val();

        var refillOverridenVar = $("#refillOverriden").val();
        if (refillOverridenVar != null && refillOverridenVar == 0)
        {
//            alert(1);
            $("#refillOverriden").val("1");
            this.refillOrder(order);
        }
    },
    refillOrder: function (orderId, orderStatusId)
    {
        if ($("#popUpException1") != null)
        {
            $("#popUpException1").hide();
        }
        if (orderStatusId == 1 || orderStatusId == 17 || orderStatusId == 16 || orderStatusId == 19) {
            window.pharmacyNotification.checkOtherStatusBitHandler(8, 'Filled');
            return;
        }
        this.truncateFldValue('acqCostFld', 1, 1, '$');
        this.truncateFldValue('ptCopayFld', 1, 1, '$');
        this.truncateFldValue('reimbFld', 1, 1, '$');
        this.truncateFldValue('totalRedeemPointsId', 1, 1, '$');
        this.truncateFldValue('redeemPointsCostFld', 1, 1, '$');
        this.truncateFldValue('finalPaymentFeeFld', 1, 1, '$');
        var refillRemain = $("#refillUsedFld").val();
        if ((orderStatusId == 8) && (refillRemain <= 0))
        {

//                    $("#popUpException").text("Drug can't be Refilled since no refills are remaining");
            $("#popupErrDiv").text("Drug can't be Refilled since no refills are remaining");
            $("#popUpException").show();
            $("#overrideRemainingSpan").show();

//            $("#popUpException").click(function(){
//            $("#messagePopUpModal").text("Hello world!");

//                   $("#ptCopayFld").focus();
            //$("#" + reimbFld).focus();
            return;
        }


        var json = {"orderId": orderId, "orderStatusId": orderStatusId, "actionType": 8, "acqCostFld": $("#acqCostFld").val(), "ptCopayFld": $("#ptCopayFld").val(),
            "reimbFld": $("#reimbFld").val(), "totalRedeemPointsId": $("#totalRedeemPointsId").val(), "redeemPointsCostFld": $("#redeemPointsCostFld").val(), "finalPaymentFeeFld": $("#finalPaymentFeeFld").val(), "refillOverriden": $("#refillOverriden").val()};
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/refillOrders/",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data == 0) {

                    return;
                }
                if (data.status == 'Fail') {
                    $("#popUpException").text(data.errorMessage);
                    $("#popUpException").show();
                    $("#statusBit").val("0");
                    return;
                }
                $("#refillOrder").val(data);
                $("#refillLnk").click();
            },
            error: function (e) {
//                alert("error");
                //alert('Error while processing order...' + e.responseText);
                $("#popupErrDiv").text("Error while processing order " + e.responseText);
                $("#popUpException").show();
                $("#overrideSpan").show();

//                        $("#statusException").addClass("errorNotice");
//                        $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });
    },
    totalCollectedAmount: function (chk, fId) {
        var selectedAmt = $("#finalPayment" + fId).text();
        selectedAmt = selectedAmt.substring(1);
        selectedAmt = selectedAmt.replace(/,/, "");
        var totalAmount = $("#totalPayment").val();//$("#netAmountBtn").val();
        var total = 0;
        if ($("#" + chk + fId).is(":checked")) {
            total = parseFloat(totalAmount) + parseFloat(selectedAmt);
        } else {
            total = totalAmount - selectedAmt;
        }
        $("#totalPayment").val(total);
        if (total > 0) {
            total = total.toLocaleString('en-US', {maximumFractionDigits: 2});
        } else {
            total = "0.00";
        }
        $("#netAmountBtn").val(" Amount to be Collected:$" + total);
    },
    showAnswerRxQuestion: function (orderId, patientResponse, patientId, questionId) {
        var json = {"questionId": questionId};
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/PharmacyPortal/getQuestionAnswer",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data.statuscode == 200) {
                    $.each(data.dependants, function (key, value) {
                        $("#msgTitle").html(value.msgTitle);
                        $("#questionTime").html(value.questionTimeStr);
                        $("#reqFldAnsQuestionLabel").html(value.systemRxNumberLabel);
                        var link = window.pharmacyNotification.appUrl + "/ConsumerPortal/rxDetail/" + orderId + "/" + patientId;
                        $("#frmRxdetailWithQuestionId").attr("action", link);
                        $("#reqFldAnsQuestionValue").html('<a href="#" onclick=\"window.pharmacyNotification.submitForm(\'frmRxdetailWithQuestionId\');\">' + value.systemGeneratedRxNumber + '</a>');
                        $("#patientName").html('<strong>' + value.patientName + '</strong>');
                        $("#patientEmail").html(value.patientEmail);
                        $("#mobileNumber").html(value.patientPhoneNumber);
                        $("#osType").html(value.osType);

                        $("#questionSpan").html('Q:<strong>' + patientResponse + '</strong>');
                        $("#hiddenQuestionId").val(questionId);
                        $("#hiddenQuestionOrderId").val(orderId);
                        $("#giveAnswerOfQuestiona").modal('show');
                    });
                }

            },
            error: function (e) {
                alert('Error while processing order...' + e.responseText);
            }
        });

//        alert("question ID "+questionId);
    },
    //////////////////////////////////////////////////////////////
    saveAnswer: function ()
    {
        var json = {"orderId": $("#hiddenQuestionOrderId").val(), "questionId": $("#hiddenQuestionId").val(),
            "answer": $("#answerFld").val()};

        ////////////////////////////////////////
//   alert("saveAnswer");

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/postAnswer/",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
//                var response = eval(data);
//                alert("Test Alert by Osman: " + response.auditFields.isCritical);
                $("#postAnswerMessageDiv").show();
                $("#postAnswerMessageDiv").css("color", "green");
                $("#postAnswerMessageDiv").html("<strong>Answer has delivered successfully</strong>");
                $("#answerSendBtn").attr("disabled", true);
                if ($("#nextOrdered").val() == 1)
                    setTimeout(function () {
                        $("#giveAnswerOfQuestiona").modal('hide');
//                                    $("#Next").click();
                    }, $("#loadingTime").val());
                else
                    setTimeout(function () {
                        $("#giveAnswerOfQuestiona").modal('hide');
                        $("#loadPage").click();
                    }, 1500);

            },
            error: function (e) {
                $("#postAnswerMessageDiv").show();
                $("#postAnswerMessageDiv").html('' + e.responseText);
//                alert('Error while processing order...' + e.responseText);
            }
        });
    }, fn_DateCompare: function (dateA, dateB) {
//        alert("date "+dateA);
//        alert("date "+dateB);
        var a = new Date(dateA);
        var b = new Date(dateB);
        return a.getTime() >= b.getTime() ? 1 : -1;
//        alert("shipped "+a.getTime()+" filled "+b.getTime());
//        var msDateA = Date.UTC(a.getFullYear(), a.getMonth() + 1, a.getDate());
//        var msDateB = Date.UTC(b.getFullYear(), b.getMonth() + 1, b.getDate());
//
//        if (parseFloat(msDateA) < parseFloat(msDateB))
//            return -1;  // lt
//        else if (parseFloat(msDateA) == parseFloat(msDateB))
//            return 0;  // eq
//        else if (parseFloat(msDateA) > parseFloat(msDateB))
//            return 1;  // gt
//        else
//            return null;  // error
    },
    /////////////////////////////////////////////////////////////
    selectDeliverySvcsAction: function (cmb, cmbVal)
    {
        if ($("#deliveryPreferences").val() == 4)

        {
            this.selectComboValue(cmb, cmbVal);
        }
        var json = {"orderId": $("#orderId").val(), "zipCode": $("#zipCodeFId").text(), "deliveryPreferencesId": $("#deliveryPreferences").val(), "outOfPocket": $("#outOfPocketFId").text().replace("$", "")};
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/calculateShippingFee",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data.statuscode == 200) {
                    $("#handlingFeeFld").val("$" + data.dependants.handlingFeeStr);
                    $("#finalPaymentCol").text(data.dependants.finalPaymentStr);
                }
            },
            error: function (e) {
//               alert("error");
                //alert('Error while processing order...' + e.responseText);
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
                $("#statusBit").val("0");
            }
        });
    },
    selectDeliveryCarrierAction: function (cmb, cmbVal)
    {
        if ($("#deliverycarrier").val() == 'Pharmacy Pickup')

        {
            this.selectComboValue(cmb, cmbVal);
        }
    },
    ////////////////////////////////////////////////////////////
    selectComboValue: function (cmb, cmbVal)
    {
        $("#" + cmb).val(cmbVal);
    },
    populateAllProgramRxComboValue: function () {

        var assignedTo = $(':checkbox[name=allProgramRxChk]:checked').map(function () {
            return this.value;
        })
                .get();
        //Out for DEMO purposes only 
        //alert(JSON.stringify(assignedTo));
        $("#hiddenRxNumbers").val(assignedTo);
        if ($("#hiddenRxNumbers").val().length > 0) {
            $("#btnShippingMultiRx").removeAttr("disabled");
        } else {
            $("#divSameShippingOrders").attr("style", "display:none;");
            $("#btnShippingMultiRx").attr("disabled", true);
            $("#handlingFeeFld").removeAttr("disabled");
            $("#handlingFeeFld").val($("#currentOrdShippingFee").val());
            $("#outOfPocketFId").html($("#currentOrdOutOfPocket").val());
            $("#finalPaymentCol").html($("#currentOrdFinalPayment").val());
        }

        $(".allProgramRxChk").click(function () {
            if ($(this).is(":checked")) {
                var isAllChecked = 0;

                $(".allProgramRxChk").each(function () {
                    if (!this.checked)
                        isAllChecked = 1;
                });

                if (isAllChecked == 0) {
                    $("#chkAllSameDay1").prop("checked", true);
                }
            } else {
                $("#chkAllSameDay1").prop("checked", false);
            }
        });
    },
    processSameDayShippingRxOrders: function (modalId, vieworders) {
        //alert($("#hiddenRxNumbers").val());
        if ($("#hiddenRxNumbers").val().length == 0) {
            $("#message").text("Please select checkbox");
            $("#message").attr("style", "color:red; font-weight:bold;padding-left:0px;");
            return;
        }
        var json = {"orderIds": $("#hiddenRxNumbers").val(), "vieworders": vieworders};
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/Pharmacyqueue/processSameDayShippingRxOrders",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data.statuscode == 200) {
                    //$("#handlingFeeFld").val(data.dependants.handlingFeeStr);
                    $("#handlingFeeFld").val("$0.00");
                    $("#handlingFeeFld").attr("disabled", true);
                    $("#newShippingFee").val("$0.00");
                    $("#outOfPocketFId").text(data.dependants.paymentExcludingDeliveryStr);
                    $("#finalPaymentCol").text(data.dependants.finalPaymentStr);
                    $("#btnSameShippingOrders").attr("value", "SAME DAY Shipping ORDERS (" + data.dependants.totalRxShipped + ")");
                    $("#divSameShippingOrders").removeAttr("style");
                    $("#" + modalId).modal('hide');
                    //For Rxdetail3 page
                    $("#detail3Tabs > :nth-child(2)").tab('show');
                    $("#shippingInfoTab").attr("class", "active");
                    $('#shipping').attr("class", "tab-pane fade video_div_one in active");
                    $("#sameDayOrders").attr("class", "tab-pane fade video_div_two");
                    $("#multiRxTab").attr("class", "");
                }
            },
            error: function (e) {
//               alert("error");
                //alert('Error while processing order...' + e.responseText);
                $("#message").text("Error while processing order " + e.responseText);
                $("#message").attr("style", "color:red; font-weight:bold;padding-left:0px;");
            }
        });
    }, populateSameDayShippingRxOrderList: function (vieworders) {
        // alert($("#hiddenRxNumbers").val());
        var json = {"orderIds": $("#hiddenRxNumbers").val(), "vieworders": vieworders};
        $.ajax({
            url: window.pharmacyNotification.appUrl + "/Pharmacyqueue/processSameDayShippingRxOrders",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                $("#sameDayRxOrderTable tbody tr").remove();
                var count = 0;
                $.each(data.dependants, function (key, row) {

                    // var row = data[i];
                    var rxnumber = "--";
                    if (row.systemGeneratedRxNumber.length > 0) {
                        rxnumber = "<span class='bold_new'>RXD-<br/></span>" + row.systemGeneratedRxNumber;
                    }

                    var drugName = row.drugName;
                    var qty = row.qty;
                    var strength = row.strength;
                    var drugType = row.drugType;
                    if (drugName == null)
                    {
                        drugName = '';
                    }
                    if (qty == null)
                    {
                        qty = '-';
                    }
                    if (drugType == null)
                    {
                        drugType = '';
                    }
                    if (strength == null)
                    {
                        strength = '';
                    }
                    var deliveryPreferencesName = "";
                    if (row.deliveryPreferencesName == 'Same Day')
                    {
                        deliveryPreferencesName = "<span style='color:red;'>!*Same<br/>DAY *!</span>";
                    } else if (row.deliveryPreferencesName == 'Next Day*')
                    {
                        deliveryPreferencesName = "<span class='redText'>! *</span>";
                        deliveryPreferencesName += "<span style='color:blue;'>NEXT<br/> DAY</span>";
                        deliveryPreferencesName += "<span class='redText'>*!</span>";
                    } else if (row.deliveryPreferencesName == 'Pick Up at Pharmacy') {
                        deliveryPreferencesName = 'Pick Up<br/>at Pharmacy';
                    }
                    var url = window.pharmacyNotification.appUrl + "\"/ConsumerPortal/rxDetail/" + row.id + "/" + $("#patientId").val() + "\ ";
                    if (row.careGiver == 1)
                    {
                        url = window.pharmacyNotification.appUrl + "\"/PharmacyPortal/careGiverDetail/" + row.dependentId + "?pq=1\ ";
                    }
                    var dateValue = row.orderDate;
                    var yearPart = "", monthpart = "", dayspart = "";
                    monthpart = dateValue.slice(0, 2);
                    dayspart = dateValue.slice(3, 5);
                    yearPart = dateValue.slice(6, 10);
                    var newRowContent = "<tr><td class='rxNumber'>" + rxnumber + "</td>"
                            + "<td class='dateCompletedField'><a href=" + url + "\" >" + yearPart + "-" + monthpart + "-<span class='redText'>" + dayspart + "</span></a></td>"
//                          + "<td style=\"color:red;font-weight:bold\">" + row.requestType + "</td>"

                            + "<td >" + drugName + ' ' + drugType + ' ' + strength + "</td>"
                            + "<td>" + deliveryPreferencesName + "</td>"
                            + "<td class='txtAlign'>" + row.paymentExcludingDeliveryStr + "</td>"
                            + "<td class='txtAlign' style='display:none;'>" + row.handlingFeeStr + "</td>"
                            + "<td class='txtAlign'>" + row.finalPaymentStr + "</td>"
                            + "</tr>";
                    $("#sameDayRxOrderTable tbody").append(newRowContent);
                    count++;
                });
                $("#sameDayRxCount").text("SHIPPING SAME DAY RX's (" + count + ")");
                $("#totalMultiRxAmount").html($("#finalPaymentCol").html());
                $("#sameDayOrderRxModal").modal('show');
            },
            error: function (e) {
//               alert("error");
                //alert('Error while processing order...' + e.responseText);
                $("#statusException").text("Error while processing order " + e.responseText);
                $("#statusException").attr("style", "color:red; font-weight:bold");
            }
        });
    }, hideModal: function (mulitRxModel) {
        $("#" + mulitRxModel).modal('hide');
    }, setUpperCaseChar: function (fieldId, index) {
        var str = $("#" + fieldId).val();
        if (this.isBlank(str) || this.isEmpty(str)) {
            return;
        }
        if ($('#' + fieldId).val()[0] === " ") {
            str = str.trim();
        }

        if (str.length > 0) {
            var upperStr = str.charAt(index).toUpperCase();
            $("#" + fieldId).val(upperStr + str.slice(1));
        }
    }, allSameDayMainChkBox: function () {
        if ($('#chkAllSameDay1').is(':checked')) {
            $($('[name=allProgramRxChk]').each(function () {
                $('[name=allProgramRxChk]').prop("checked", true);
            }));
            $("#btnShippingMultiRx").removeAttr("disabled");
            this.populateAllProgramRxComboValue();
        } else {
            $('[name=allProgramRxChk]').prop("checked", false);
            $("#btnShippingMultiRx").attr("disabled", true);
            this.populateAllProgramRxComboValue();
        }
    }, isEmpty: function (str) {
        return (!str || 0 === str.length);
    },
    isBlank: function (str) {
        return (!str || /^\s*$/.test(str));
    }, replaceShippingFee: function (handlingfee) {
        if ($("#handlingFeeFld").val() == handlingfee) {
            return;
        }
        $("#oldShippingFee").val($("#handlingFeeFld").val());
        $("#handlingFeeFld").val(handlingfee);
        handlingfee = handlingfee.replace("$", "");
        if (!this.isBlank(handlingfee) && !this.isEmpty(handlingfee) && (handlingfee == ".00" || handlingfee == "0.00")) {
            $("#totalMultiRxAmount").html($("#outOfPocketFId").html());
            $("#finalPaymentCol").html($("#outOfPocketFId").html());
        } else {
            var totalMultiRxAmount = $("#totalMultiRxAmount").html();
            totalMultiRxAmount = totalMultiRxAmount.replace("$", "");
            totalMultiRxAmount = totalMultiRxAmount.replace(",", "");
            handlingfee = handlingfee.replace("$", "");
            totalMultiRxAmount = parseFloat(totalMultiRxAmount) - parseFloat($("#oldShippingFee").val().replace("$", ""));
            totalMultiRxAmount = parseFloat(totalMultiRxAmount) + parseFloat(handlingfee);
//                alert("a");
            totalMultiRxAmount = "$" + totalMultiRxAmount.toFixed(2);
            //alert("Str111 "+str);
            totalMultiRxAmount = this.formatPriceToTwoDecimalPlaces2(totalMultiRxAmount);
            $("#totalMultiRxAmount").html(totalMultiRxAmount);
            $("#finalPaymentCol").html(totalMultiRxAmount);
        }


    }, processMultiRxDeliveryOrder: function (statusVal) {
        if ($("#popUpException1") != null)
        {
            $("#popUpException1").html("");
            $("#popUpException1").hide();
        }
        //this.truncateFldValue('estPriceFld', 1, 1, '$');
        var orderId = $("#orderId").val();
        //alert("order id "+orderId)
        var patientId = $("#patientId").val();
        var deliverycarrier = $("#deliverycarrier").val();
        var traclingno = $("#traclingno").val();
        var clerk = $("#clerk").val();
        var shipDate = $("#datetimepicker2").val();

        var finalPaymentFld = $("#finalPaymentCol").html().replace("$", "");
        var outOfPocketFId = $("#outOfPocketFId").html().replace("$", "");
        var handlingFeeFld = $("#handlingFeeFld").html().replace("$", "");
        var numberOfRecord = $("#numberOfRecordFld").val();

        var json = {"orderId": orderId, "patientId": patientId, "deliverycarrier": deliverycarrier, "traclingno": traclingno, "statusVal": statusVal,
            "clerk": clerk, "shipDate": shipDate, "finalPaymentFld": finalPaymentFld, "outOfPocketFId": outOfPocketFId, "handlingFeeFld": handlingFeeFld,
            "numberOfRecord": numberOfRecord};

        $.ajax({
            url: window.pharmacyNotification.appUrl + "/ConsumerPortal/processMultiRxOrders",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
//                alert("data "+data + "ol "+data.baseDTO.olVersion + "status "+data.baseDTO.status);
//                debugger;
                if (data.status == 'Fail') {
                    $("#popUpException").text(data.errorMessage);
                    $("#popUpException").show();
                    $("#statusBit").val("0");
                    return;
                }

                $("#orderStatusSpan").html(statusVal);
                $("#popUpSuccess").show();
                $("#popUpException").text("");
                $('#btnOrderShip').attr('disabled', 'disabled');
                $('#notCarried').attr('disabled', 'disabled');
                $("olversion").val(data.baseDTO.olVersion);

                if (statusVal == 6)
                {
                    window.location.href = window.pharmacyNotification.appUrl + "/PharmacyPortal/newMemberRequest";
                }
            },
            error: function (e) {
                alert('Error while processing order...' + e.responseText);
//                $("#popupErrDiv").text("Error while processing order " + e.responseText);
                $("#popUpException").show();
                $("#popupErrDiv").show();
                $("#statusBit").val("0");
            }
        });
    }, submitForm: function (frmId) {
        $("#" + frmId).submit();
    }, isDisplayPtResponseSection: function (chk) {
        if (chk == 1) {
            $("#ptInCommingRes").attr("style", "display:none;padding-bottom: 5px;");
        }

        if (chk == 0 && $('#ptInCommingRes').css('display') == 'none') {
            $("#ptInCommingRes").attr("style", "display:block;padding-bottom: 5px;");
        }
    }, populateAppUrl: function (appUrl) {
        window.pharmacyNotification.appUrl = appUrl;
    }, resetField: function () {
        $("input[type=text], textarea").val("");
        $("#daysSupplyFld").val(0);
        $("#refillAllowedFld").val(0);
        $("#refillUsedFld").val(0);
        $("#acqCostFld").val("$0.00");
        $("#estPriceFld").val("$0.00");
    }, resetAddNewRxField: function () {
        $("#addNewRxpatientInformation").html("");
        $("input[type=text]").val("");
    }, calculateNextRefillDate: function (originDate) {
        if (this.isEmpty(originDate) || this.isBlank(originDate)) {
            console.log("Original Date is empty.");
            return;
        }
        var daysSupplyFld = $("#daysSupplyFld").val();
        if (this.isEmpty(daysSupplyFld) || this.isBlank(daysSupplyFld)) {
            console.log("Days Supply must be greater than zero.");
            $("#popUpException").text("Please enter days supply.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            $("#daysSupplyFld").focus();
            return;
        }
        if (daysSupplyFld == 0) {
            console.log("Days Supply must be greater than zero.");
            $("#popUpException").text("Days supply must be greater than 0.");
            $("#popUpException").attr("style", "color:red; font-weight:bold");
            $("#daysSupplyFld").focus();
            return;
        }
        var date = new Date(originDate);
        var newdate = new Date(date);
        var addday = ($("#daysSupplyFld").val() * 80) / 100;
        newdate.setDate(newdate.getDate() + parseInt(addday));

        var dd = newdate.getDate();
        var mm = newdate.getMonth() + 1;
        var y = newdate.getFullYear();
        if (dd <= 9) {
            dd = "0" + dd;
        }
        if (mm <= 9) {
            mm = "0" + mm;
        }
        var formattedDate = mm + '-' + dd + '-' + y;
        $("#nextRefillDate").html(formattedDate);
        this.calculateDrugExpireDate(originDate);
    }, calculateDrugExpireDate: function (originDate) {
        console.log("monthRxExpiration " + originDate);
        if (this.isEmpty(originDate) || this.isBlank(originDate)) {
            console.log("Original Date is empty.");
            return;
        }
        var monthRxExpiration = $("#drugRxExpire").val();
        console.log("monthRxExpiration " + monthRxExpiration);
        if (this.isBlank(monthRxExpiration) || this.isEmpty(monthRxExpiration)) {
            console.log("Drug RxExpire Date is empty.");
            return;
        }
        var date = new Date(originDate);
        var newdate = new Date(date);
        if (monthRxExpiration == "y" || monthRxExpiration == "Y") {
            newdate.setDate(newdate.getDate() + 182);
        } else {
            newdate.setDate(newdate.getDate() + 365);
        }
        var dd = newdate.getDate();
        var mm = newdate.getMonth() + 1;
        var y = newdate.getFullYear();
        if (dd <= 9) {
            dd = "0" + dd;
        }
        if (mm <= 9) {
            mm = "0" + mm;
        }
        var formattedDate = mm + '-' + dd + '-' + y;
        $("#rxExpiredDate").html(formattedDate);
    }

}