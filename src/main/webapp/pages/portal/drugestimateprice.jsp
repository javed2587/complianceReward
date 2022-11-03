<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input id="currentUserRole" value="${sessionBeanPortal.role}" type="hidden"/>
<c:choose>
    <c:when test="${(sessionBeanPortal.pmap[(82).intValue()].view eq true && sessionBeanPortal.pmap[(82).intValue()].manage eq false)}">
        <div class="modal fade drug_modals" id="Drug_ind" role="dialog" data-keyboard="false" data-backdrop="static">
            <div class="vertical-alignment-modal">
                <div class="modal-dialog vertical-align-modal"> 

                    <!-- Modal content-->
                    <div class="modal-content">
                        <jsp:include page="drugestimatepricesearchfield.jsp"/>
                        <div class="col-sm-12 drug_modal_col">
                            <div class="drug_list_tab">
                                <input id="drugDetailId" type="hidden" value="0"/>
                                <input id="drugNameId" type="hidden" value=""/>
                                <input id="estimateDrugPriceTblRecord" type="hidden" value="0"/>
                                <div>
                                    <table id="estimateDrugPriceTbl" class="table" cellpadding="0" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Brand Name</th>
                                                <th>Generic Name</th>
                                                <th>Strength</th>
                                                <th>Type</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 drug_modal_col clearfix">
                            <div class="drug_p_det clearfix">
                                <div class="col-sm-5 drug_det_col">
                                    <p><span class="pull-left">GPI: </span> <span id="gpI" style="color: black;"></span></p>
                                    <p><span>Therapeutic Category: </span> <span id="TherapyId" style="color: black;"></span></p>
                                    <a href="#" class="btn btn-primary" id="genericNameId">Generic</a> </div>
                                <div class="col-sm-5 drug_det_col">

                                    <p>
                                        <label>Req�d pt Info:</label>
                                        <span id="ptInfo" style="color: black;"></span>
                                    </p>
                                    <p>
                                        <label>Req'd Medication Guide:</label>
                                        None</p>
                                    <p>
                                        <label>Drug Image:</label>
                                        <a href="#" target="_blank">
                                            <img id="drugImg"  alt="" height="30" width="30"/>
                                        </a>
                                    </p>
                                </div>
                                <div class="col-sm-2 drug_det_col drug_total_amout">
                                    <p><span id="drug_total_amout">$ </span>Ing COST $ <span id="acqCost"></span></p>
                                </div>
                            </div>
                        </div>


                        <div class="col-sm-10 drug_modal_col">
                            <textarea id="notifyAdminFId" class="form-control" placeholder="Notify Admin"></textarea>
                            <span id="notifyAdminReq" class="errorMessageValid"></span>
                        </div>
                        <div class="col-sm-2 drug_modal_col">
                            <input type="submit" name="send" value="SEND" class="btn btn-primary send_btn" onclick="window.drugestimateprice.sendNotifyAdmin()" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <!--Drug Modal Two-->
        <div class="modal fade drug_modals price_auditor_modal" id="Drug_ind" role="dialog">
            <div class="vertical-alignment-modal">
                <div class="modal-dialog vertical-align-modal"> 

                    <!-- Modal content-->
                    <div class="modal-content">
                        <jsp:include page="drugestimatepricesearchfield.jsp"/>


                        <div class="col-sm-12 drug_modal_col">
                            <div class="drug_list_tab">
                                <input id="drugDetailId" type="hidden" value="0"/>
                                <input id="drugNameId" type="hidden" value=""/>
                                <input id="estimateDrugPriceTblRecord" type="hidden" value="0"/>
                                <div>
                                    <table id="estimateDrugPriceTbl" class="table" cellpadding="0" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Brand Name</th>
                                                <th>Generic Name</th>
                                                <th>Strength</th>
                                                <th>Dosage Form</th> 
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>


                        <div id="administrator_edit" class="administrator_edit clearfix disabled_write">
                            <div class="col-sm-12 drug_modal_col admin_header clearfix">
                                <h2>Supervisor / Administrator Edit</h2>

                                <span id="responseMsg" style="padding-left: 140px;"></span>
                                <a href="#" class="btn btn-edit wid_new" onclick="window.drugestimateprice.removeDisableProp();"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</a>
                                <a href="#" class="btn btn-reset wid_new" onclick="window.drugestimateprice.resetField('${sessionBeanPortal.role}')"><i class="fa fa-caret-left" aria-hidden="true"></i> Reset</a>
                                <a href="#" class="btn btn-reset wid_new" style="color: red;padding-right: 10px;" onclick="window.drugestimateprice.isOpenAddNewDialog();">ADD NEW</a>
                            </div>

                            <div class="audit_form_wrap clearfix">
                                <div class="col-sm-9 drug_modal_col">
                                    <div class="admin_col clearfix">
                                        <div class="col-sm-1 drug_modal_col">
                                            <label>GCN</label>
                                            <input id="gcnTxtId" type="text" name="gcn" maxlength="5"  value="" placeholder="" class="form-control margin_control" onkeypress="return IsDigit(event)"/>
                                            <span id="gcnReq" class="errorMessage"></span>
                                        </div>
                                        <div class="col-sm-2 drug_modal_col">
                                            <label>GPI</label>
                                            <input id="gpitxtId" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="20" onkeypress="return IsDigit(event)"/>
                                        </div>

                                        <div class="col-sm-3 drug_modal_col">
                                            <label>Brand Indicator</label>

                                            <select id="BI" class="form-control">
                                                <option value="GENERIC">GENERIC</option>
                                                <option value="BRAND">BRAND</option>
                                            </select>
                                        </div>

                                        <div class="col-sm-1 drug_modal_col">
                                            <label>Stock</label>

                                            <select id="inStock" class="form-control" name="inStock">
                                                <option value="Y">Y</option>
                                                <option value="N">N</option>
                                            </select>
                                        </div>

                                        <div class="col-sm-5 drug_modal_col">
                                            <label>Reference Brand</label>
                                            <input id="brand" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="100" />
                                        </div>


                                        <div class="col-sm-5 drug_modal_col">
                                            <label>Generic Chooser</label>
                                            <input id="genericTxtId" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="100" />
                                        </div>

                                        <div class="col-sm-4 drug_modal_col">
                                            <label>GNN</label>
                                            <input id="gnnTxt" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="100" />
                                        </div>

                                        <div class="col-sm-3 drug_modal_col">
                                            <label>Strength</label>
                                            <input id="strengthTxt" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="50" />
                                        </div>

                                        <div class="col-sm-3 drug_modal_col">
                                            <label>Dosage Form</label>
                                            <input id="dosageFormTxt" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="200" />
                                        </div>
                                        <div class="col-sm-3 drug_modal_col">
                                            <label>Packaging Desc</label>
                                            <input id="packagingDesc" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="100" />
                                        </div>


                                        <div class="col-sm-2 drug_modal_col">
                                            <label>STD RX QTY</label>
                                            <input id="rxQty" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="4" onkeypress="return IsDigit(event)" maxlength="20" onchange="window.drugestimateprice.calculateDrugDetailPrice();"/>
                                        </div>

                                        <div class="col-sm-4 drug_modal_col">
                                            <label>QTY Dropdown</label>
                                            <input id="qtyList" type="text" name="gcn" value="" placeholder="" class="form-control" onkeypress="return IsCommaSeparatedNumbers(event)" maxlength="100" />
                                        </div>




                                        <div class="col-sm-2 drug_modal_col">
                                            <label>RX Expires</label>
                                            <select id="rxExpires" class="form-control" name="rxExpires">
                                                <option value="Y">YES</option>
                                                <option value="N">NO</option>
                                                <option value="NO REFILLS">NO REFILLS</option>
                                            </select>
                                        </div>

                                        <div class="col-sm-3 drug_modal_col">
                                            <label>Req Same delivery</label>

                                            <select id="reqHandDelivery" class="form-control" name="reqHandDelivery">
                                                <option value="Y">YES</option>
                                                <option value="N">NO</option>
                                            </select>
                                        </div>

                                        <div class="col-sm-7 drug_modal_col">
                                            <label>Therapeutic Category</label>
                                            <input id="therapeuticCategory" type="text" name="gcn" value="" placeholder="" class="form-control" maxlength="150" />
                                        </div>
                                        <!--  <div class="col-sm-2 drug_modal_col">
                                             &nbsp;
                                         </div> -->

                                        <div class="col-sm-4 drug_modal_col">
                                            <label>Req'd PT Info</label>
                                            <div class="input-group">
                                                <input id="pdfFileName" type="text" class="form-control" value="" placeholder="" readonly="true">
                                                <span id="pdfFileReq" class="errorMessage"></span>
                                                <span class="input-group-btn">
                                                    <a id="pdfImg" href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/pdf_icon.png" alt=""/></a>
                                                    <input id="drugPdffile" type="file" style="display: none;" onchange="displayFile('drugPdffile', 'pdfFileReq', 'pdfFileName', 'pdf', this.value)"/>
                                                    <button class="btn btn_upload" type="button" onclick="uploadFile('drugPdffile')" disabled="disabled"><i class="fa fa-upload" aria-hidden="true"></i></button>
                                                </span>
                                            </div>

                                        </div>

                                        <div class="col-sm-4 drug_modal_col">
                                            <label>Req'D Medication Guide</label>
                                            <div class="input-group">
                                                <input id="drugDocFileName" type="text" class="form-control" value="" placeholder="" readonly="true">
                                                <span id="drugDocReq" class="errorMessage"></span>
                                                <span class="input-group-btn">
                                                    <a id="drugDocImg" href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/pdf_icon.png" alt=""/></a>
                                                    <input id="drugDocfile" type="file" style="display: none;" onchange="displayFile('drugDocfile', 'drugDocReq', 'drugDocFileName', 'pdf', this.value)"/>
                                                    <button id="btnDrugDocfile" class="btn btn_upload" type="button" onclick="uploadFile('drugDocfile')" disabled="disabled"><i class="fa fa-upload" aria-hidden="true"></i></button>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="col-sm-4 drug_modal_col">
                                            <label>Drug Image file</label>
                                            <div class="input-group">
                                                <input id="drugImgFileTxt" type="text" class="form-control" value="" placeholder="" readonly="true">
                                                <span id="drugImageFileReq" class="errorMessage"></span>
                                                <span class="input-group-btn">
                                                    <a id="drugImgLink" href="#" target="_blank"><img id="drugImgUrl"  alt="" height="30" width="30"/></a>
                                                    <input id="drugImagefile" type="file" style="display: none;" onchange="displayFile(this.files[0], 'drugImagefile', 'drugImgFileTxt', 'png', this.value)" accept="image/*"/>
                                                    <button id="btnUploadImgFile" class="btn btn_upload" type="button" onclick="uploadFile('drugImagefile')" disabled="disabled"><i class="fa fa-upload" aria-hidden="true"></i></button>
                                                </span>
                                            </div>
                                        </div>


                                        <div class="col-sm-12 drug_modal_col">
                                            <input id="btnSavechanges" type="submit" name="savechanges" value="Save Changes" class="btn btn-primary simple_btn" style="background: #7eb514" onclick="window.drugestimateprice.saveDrugDetail()" disabled="disabled" />
                                        </div>

                                    </div>
                                </div>
                                <div class="col-sm-3 drug_modal_col drug_bg">
                                    <div class="admin_col audit_total clearfix">
                                        <span id="final_total" class="final_total"></span>
                                        <ul>
                                            <li>Unit Price base<input id="priceBase" class="input_control"  disabled="true" onkeypress="return float_validation(event, this.value)" onchange="window.drugestimateprice.calculateDrugDetailPrice();"/></li>
                                            <li>Acq Price<input id="acqPrice" class="input_control"  disabled="true" onkeypress="return float_validation(event, this.value)"/></li>
                                            <li class="red">Margin %<input id="margin" class="input_control" disabled="true" onkeypress="return float_validation(event, this.value)" onchange="window.drugestimateprice.calculateDrugDetailPrice();"/></li>
                                            <li>Margin<input id="marginFId" class="input_control"  disabled="true" onkeypress="return float_validation(event, this.value)"/></li>
                                            <li>Base Overhead<input id="baseOverhead" class="input_control"  disabled="true" onkeypress="return float_validation(event, this.value)" onchange="window.drugestimateprice.calculateDrugDetailPrice();"/></li>
                                            <li style="display:none">Mrkt Surcharge<input id="mrktSurcharge" class="input_control"  disabled="true" onkeypress="return float_validation(event, this.value)" onchange="window.drugestimateprice.calculateDrugDetailPrice();"/></li>
                                            <li>FINAL<input id="final" class="input_control"  disabled="true" onkeypress="return float_validation(event, this.value)"/></li>
                                            <li><span style="float: left;font-size: 12px;padding-left: 0px;">Last Updated: </span><span id="lastUpdatedDate" style="float: right;font-size: 12px;"></span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/Drug Modal Two-->
</c:otherwise>
</c:choose>
<!--/ Modal Insurance Detail -->
<div id="drugBrandModal" class="modal fade" role="dialog" style="margin-top: 190px;display: none;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content" style="border-radius: 19px; height: 140px; width: 60%;border: 2px solid #2171b6;">
            <div>
                <button type="button" class="close" data-dismiss="modal" style="color: #666666;background: whitesmoke;opacity: 1;" onclick="window.drugestimateprice.hideDialog('drugBrandModal')">&times;</button>
            </div>
            <div class="modal-body">
                <h4 style="color: #666666;">ADD DATABASE NEW ENTRY</h4>
                <h5 style="float:left;width: 40%;text-align: center;cursor: pointer;" onclick="window.drugestimateprice.addBrandNewProduct('1')">ADD BRAND NEW PRODUCT</h5>
                <h5 id="cloneLink" style="float:right;color: red;width: 53%;cursor: pointer;">LINE EXTENSION CLONE ATTRIBUTES</h5>

            </div>
        </div>

    </div>
</div>
<style>
    ul.ui-autocomplete {
        z-index: 9999;
       /** display: block !important;**/
    }
    .ui-helper-hidden-accessible{
        display: none;
    }
    .dataTables_wrapper.no-footer .dataTables_scrollBody {
        border-bottom: 0px !important;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        window.drugestimateprice.initializeDataTable("0px");
        $('.disabled_write .form-control').attr('disabled', 'disabled');
    });

    function displayFile(files, imgId, name, type, filevalue) {
        if (type == "pdf") {
            var res = validate(type, filevalue, files);
            if (!res) {
                $("#responseMsg").text("Please upload only pdf file.");
                $("#responseMsg").attr("class", "errorMessage");
                $("#responseMsg").show();
                return;
            } else {
                $("#responseMsg").hide();
                $("#" + name).val($("#gcnTxtId").val() + "." + type);
            }
        }
        if (type == "png") {
            var res = validate(type, filevalue, imgId);
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

    }
    function uploadFile(target) {
        $("#" + target).trigger("click");
    }
    function validate(type, file, fileId) {
        var ext = file.split(".");
        ext = ext[ext.length - 1].toLowerCase();
        var arrayExtensions;
        if (type == "pdf") {
            arrayExtensions = ["pdf"];
        } else {
            arrayExtensions = ["jpg", "jpeg", "png", "bmp", "gif"];
        }
        if (arrayExtensions.lastIndexOf(ext) == -1) {
            $("#" + fileId).val("");
            return false;
        }
        return true;
    }
    $(document).keydown(function (e) {
        // ESCAPE key pressed
        if (e.which == 27) {
            window.drugestimateprice.resetField();
        }
    });
    $("#drugGCN").keydown(function (e) {
        if (e.which == 13) {
            window.drugestimateprice.drugGCNSearchIndicator();
        }
    });
    
    $("#referenceBrand").keydown(function (e) {
        if (e.which == 13) {
            window.drugestimateprice.populateUniqueObject('referenceBrand','drugGCN', '/rxTransfer/populateDrugDetail', 'drugGCN');
            window.drugestimateprice.populateDrugDosageAndStrength();
        }
    });
</script>