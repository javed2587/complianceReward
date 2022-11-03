<div class="modal-header">
    <button type="button" class="crox" data-dismiss="modal" onclick="window.drugestimateprice.resetField('${sessionBeanPortal.role}')">&times;</button>
    <h4>Drug Price Auditor</h4>
</div>
<div id="modal-body" class="modal-body clearfix">
    <div class="col-lg-12" style="padding-left: 2px;"><span id="drugGCNErrorMsg" class="errorMessage"></span></div>
    <div class="col-sm-1 drug_modal_col">
        <input id='searchBy' type="hidden"/>
        <input id='gcnSearch' type="hidden" value="0"/>
        <label>GCN</label>
        <input id="drugGCN" type="search" name="" value="" placeholder="" class="form-control" autofocus onblur="window.drugestimateprice.drugGCNSearchIndicator();" oninput="window.transferRequest.populateDrugDetailByGCN(this.value)" onkeypress="return IsDigit(event)"/>
    </div>
    <div class="col-sm-4 drug_modal_col">
        <label>Reference Brand/Generic Name</label>
        <input id="referenceBrand" type="text" value="" placeholder="" class="form-control" oninput="window.transferRequest.drugLookupHandler('referenceBrand','${pageContext.request.contextPath}/ConsumerPortal/populateDrugDetailByBrandOrGenericName');" name=""
               onblur="needToConfirm = false; window.drugestimateprice.populateUniqueObject('referenceBrand','drugGCN', '${pageContext.request.contextPath}/rxTransfer/populateDrugDetail', 'drugGCN')" />
        <span id="referenceBrandReq" class="errorMessage"></span>
    </div>
    <div class="col-sm-2 drug_modal_col">
        <label>Dosage Form</label>
        <select id="drugTypeId" class="form-control" onblur="window.transferRequest.drugTypeBlurHandler();">

        </select>
    </div>
    <div class="col-sm-2 drug_modal_col">
        <label>Strength</label>
        <select id="drugStrength" class="form-control" onchange="window.transferRequest.populateQtyByStrengthAndDrugName('quantity');">

        </select>
    </div>
    <div class="col-sm-1 drug_modal_col">
        <label>Quantity</label>
        <input id="qtyFId" type="hidden" value="0"/>
        <input id="quantity" type="text" class="form-control pull-left"  value="" onkeypress="return IsDigit(event)" onblur="window.drugestimateprice.qtyFldBlurHandler();"/>
    </div>
    <div class="col-sm-2 drug_modal_col marg_top">
        <input type="submit" class="btn btn-primary" value="Self Pay" onclick="window.drugestimateprice.estimateDrugPriceList('${sessionBeanPortal.role}')"/>
    </div>