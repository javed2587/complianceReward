<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@page contentType="text/html" pageEncoding="windows-1252"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


            <div class="modal fade drug_modals price_auditor_modal wid" id="Drug_ind_multi" role="dialog" style="display: none;">
                <div class="vertical-alignment-modal">
                    <div class="modal-dialog vertical-align-modal ui-draggable">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="crox" data-dismiss="modal" onclick="window.drugestimateprice.resetField('admin');window.drugestimateprice.resetEditFlagValues();resetMultiRxForm();">x</button>
                                <h4>Drug Price Auditor</h4>
                            </div>
                            <div id="modal-body" class="modal-body clearfix">

                                    <div id="drugMultiErrorMsg" class="col-lg-12 error-msg" style="padding-left: 15px;display:none"><span class="" style="display:none">error has been occurred</span></div>
                                    <div id="drugMultiSuccessMsg" class="col-lg-12 sucess_mesg-full" style="padding-left: 15px;display:none "><span class="" style="display:none">Record(s) updated successfully</span></div>
                                <div>
                                    <p class="search">Search by:</p>
                                    <div >
                                       
                                            <input type="radio"  name="a" id='multiRadioGCN' value="1" class="checkbox_style" checked 
                                                   onclick="window.drugestimateprice.checkMultiRadioButtonOption()"> GCN
                                            <input type="radio"  name="a" id='multiRadioDrugName' value="2" class="checkbox_style"
                                                   onclick="window.drugestimateprice.checkMultiRadioButtonOption()"> Drug Name
                                    </div>
                                   <div id="multiGcnDiv">
                                    
                                        <input class="form-control_custom" type="text" placeholder="GCN"  
                                               id="multiDrugGcnText" oninput="window.transferRequest.populateMultiDrugDetailByGCN(this.value)" onkeypress="return IsCommaSeparatedNumbers(event)"
                                             aria-label="Search">
                                             <button class="btn btn-search submit new_width_button" 
                                             onclick="window.drugestimateprice.resetEditFlagValues();window.drugestimateprice.estimateDrugPriceListUsingSearchParameter()">Search</button>
                                        <p class="block italic">
                                            (In Case of multiple GCN's, provide comma separated values)
                                        </p>
                                    </div>
                                    <div id="multiDrugDiv" style="display:none">
                                        <input class="form-control_custom" type="text" placeholder="Drug Name" aria-label="Search"
                                               id="multiDrugText" oninput="window.transferRequest.lookUpObjectMultiRx('multiDrugText','${pageContext.request.contextPath}/ConsumerPortal/populateDrugDetailByBrandOrGenericName');" 
                                               ><button class="btn btn-search submit new_search" onclick="window.drugestimateprice.resetEditFlagValues();window.drugestimateprice.estimateDrugPriceListUsingSearchParameter()">Search</button>
                                        <p class="block italic">
                                            
                                        </p>
                                    </div>
                                    <div id="table-wrap">
                                            <div id="table-scroll">
                                                <table id="multiDrugPriceUpdateTable" class="new_table">
                                                    <thead>
                                                        <tr>
                                                            <th style="width:33px !important;">
                                                                GCN
                                                            </th>
                                                            <th style="width:130px !important;">Brand</th>
                                                            <th style="width:140px !important;">Generic</th>
                                                            <th style="width:72px !important;">Strength</th>
                                                            <th style="width:51px !important;">Form</th>
                                                            <th style="text-align:center; width:30px !important;">Qty</th>
                                                            <th style="text-align:center; width:68px !important;">Pkg Cost</th>
                                                            <th style="text-align:center; width:60px !important;">Updated On</th>
                                                            <th style="text-align:center; width:69px !important;">Unit Cost</th>
                                                            <th style="text-align:center; width:62px !important;">Margin%</th>
                                                            <th style="text-align:center; width:85px !important;">Retail Price/Unit</th>
                                                            <th style="text-align:center; width:69px !important;">Unit Margin</th>
                                                            <th style="text-align:center; width:95px !important">Base Overhead/Rx</th>
                                                            <th style="text-align:center; width:85px !important;">Mkt Surcharge</th>
                                                           <!-- <th style="text-align:center;">Final Price</th>-->
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                </div>





                            </div>
                            <input type="hidden" id="unitCostChanged" value="0">
                            <input type="hidden" id="unitMarkupAmoutChanged" value="0">
                            <div class="modal-footer" style="text-align:right">
                               
                                <button class="btn btn-reset submit_rest new_width_button_bottom new_padding_reset" onclick="window.drugestimateprice.resetEditFlagValues();resetMultiRxForm();" style="background-color: #df1111;">RESET</button>
                                 <button class="btn btn-save submit new_width_button_bottom" 
                                        onclick="window.drugestimateprice.resetEditFlagValues();window.drugestimateprice.updateMultiRxPrices(
                                                    'multiDrugPriceUpdateTable')">Save</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>