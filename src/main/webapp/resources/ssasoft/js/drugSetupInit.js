/* 
 * Haider Ali
 */

window.DrugSetup = {
    
    initDrugSetup : function(){
        // Initialize Drug Category auto compelte 
        var availableTagsDrugCatSearch = [];
        $("#drugCatId").autocomplete({
            source: availableTagsDrugCatSearch,
            minLength: 0,
            dataType: 'json'
        });
        
        // Initialize Therapy Class auto compelte 
        var availableTagsTherapyClassSearch = [];
        $("#lookupTherapyClassId").autocomplete({
            source: availableTagsTherapyClassSearch,
            minLength: 0,
            dataType: 'json'
        });
        
        // Initialize Generic Name auto compelte 
        var availableTagsGenericNameSearch = [];
        $("#lookupgenericNameId").autocomplete({
            source: availableTagsGenericNameSearch,
            minLength: 0,
            dataType: 'json'
        });
        
        // Initialize Brand Name auto compelte 
        var availableTagsBrandNameSearch = [];
        $("#lookupBrandNameId").autocomplete({
            source: availableTagsBrandNameSearch,
            minLength: 0,
            dataType: 'json'
        });
    }
}



