/* 
 * Haider Ali
 * Drug Lookup java script file
 * and open the template in the editor.
 * 
 * test use case
 * cat : ANTIBIOTICS
 * therapyClass : PENICILLIN DERIVED
 * generic Name : AMOX TR-POTASSIUM CLAVULANATE
 * brand name : AUGMENTIN
 */

window.DrugLookup = {
    appUrl: null,
    lookDrugCategory: function () {

        var drugCategoryId = $("#drugCatId").val();
        var json = {"name": drugCategoryId};
        var autoCompleteAllValuesCus = new Array();
        $.ajax({
            url: window.DrugLookup.appUrl + "/drugBrand/searchDrugCategory",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length == 0) {
                    $('#drugCatId').val("");
                    $('#lookupTherapyClassId').val("");
                    $('#lookupgenericNameId').val("");
                    $('#lookupBrandNameId').val("");

                    alert('No Drug Category Found');
                } else {

                    for (var i = 0; i < responses.length; i++) {
                        autoCompleteAllValuesCus[i] = {
                            'value': responses[i].categoryId,
                            'label': responses[i].categoryName
                        };
                    }
                    $('#drugCatId').autocomplete('option', 'source', autoCompleteAllValuesCus);
                    $("#drugCatId").autocomplete("search", "");

                    $("#drugCatId").autocomplete({
                        select: function (event, ui) {
                            event.preventDefault();
                            $('#drugCatId').val(ui.item.label);
                            $('#searchDrugCatId').val(ui.item.value);
                        }
                    });
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    lookupTherapyClass: function () {

        var drugCategoryId = $("#searchDrugCatId").val();
        var therapyClassNamePrefix = $("#lookupTherapyClassId").val();

        var json = {"catId": drugCategoryId, "name": therapyClassNamePrefix};
        var autoCompleteAllValuesCus = new Array();
        $.ajax({
            url: window.DrugLookup.appUrl + "/drugBrand/searchTherapyClass",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length == 0) {
                    $('#lookupTherapyClassId').val("");
                    $('#lookupgenericNameId').val("");
                    $('#lookupBrandNameId').val("");
                    alert('No Therapy Class Found');
                } else {

                    for (var i = 0; i < responses.length; i++) {
                        autoCompleteAllValuesCus[i] = {
                            'value': responses[i].therapyClassId,
                            'label': responses[i].therapyClassName
                        };
                    }
                    $('#lookupTherapyClassId').autocomplete('option', 'source', autoCompleteAllValuesCus);
                    $("#lookupTherapyClassId").autocomplete("search", "");

                    $("#lookupTherapyClassId").autocomplete({
                        select: function (event, ui) {
                            event.preventDefault();
                            $('#lookupTherapyClassId').val(ui.item.label);
                            $('#searchTherapyClassId').val(ui.item.value);
                        }
                    });
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    lookupGenericName: function () {

        var therapyClassId = $("#searchTherapyClassId").val();
        var genericNamePrefix = $("#lookupgenericNameId").val();

        var json = {"catId": therapyClassId, "name": genericNamePrefix};
        var autoCompleteAllValuesCus = new Array();
        $.ajax({
            url: window.DrugLookup.appUrl + "/drugBrand/searchGenericName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length == 0) {
                    $('#lookupgenericNameId').val("");
                    $('#lookupBrandNameId').val("");
                    alert('No Generic Name Found');
                } else {

                    for (var i = 0; i < responses.length; i++) {
                        autoCompleteAllValuesCus[i] = {
                            'value': responses[i].genericNameId,
                            'label': responses[i].genericName
                        };
                    }
                    $('#lookupgenericNameId').autocomplete('option', 'source', autoCompleteAllValuesCus);
                    $("#lookupgenericNameId").autocomplete("search", "");

                    $("#lookupgenericNameId").autocomplete({
                        select: function (event, ui) {
                            event.preventDefault();
                            $('#lookupgenericNameId').val(ui.item.label);
                            $('#searchgenericNameId').val(ui.item.value);
                        }
                    });
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    },
    lookupBrandName: function () {

        var genericNameId = $("#searchgenericNameId").val();
        var brandNamePrefix = $("#lookupBrandNameId").val();

        var json = {"catId": genericNameId, "name": brandNamePrefix};
        var autoCompleteAllValuesCus = new Array();
        $.ajax({
            url: window.DrugLookup.appUrl + "/drugBrand/searchBrandName",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                var responses = eval(data);
                if (responses.length == 0) {
                    $('#lookupBrandNameId').val("");
                    alert('No Brand Name Found');
                } else {

                    for (var i = 0; i < responses.length; i++) {
                        autoCompleteAllValuesCus[i] = {
                            'value': responses[i].brandNameId,
                            'label': responses[i].brandName
                        };
                    }
                    $('#lookupBrandNameId').autocomplete('option', 'source', autoCompleteAllValuesCus);
                    $("#lookupBrandNameId").autocomplete("search", "");

                    $("#lookupBrandNameId").autocomplete({
                        select: function (event, ui) {
                            event.preventDefault();
                            $('#lookupBrandNameId').val(ui.item.label);
                            $('#searchbrandNameId').val(ui.item.value);
                        }
                    });
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }, populateAppUrl: function (appUrl) {
        window.DrugLookup.appUrl = appUrl;
    }
}

