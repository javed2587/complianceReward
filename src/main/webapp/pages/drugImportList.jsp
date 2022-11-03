<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
    <style>
        #loadingg {
            width: 100%;
            height: 100%;
            top: 0px;
            left: 0px;
            position: fixed;
            display: block;
            opacity: 0.7;
            background-color: #000;
            z-index: 1500;
            text-align: center;
        }

        #loading-image {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 500;
            margin: -32px 0 0 -32px;
        }
        .modalDialog {
            position: fixed;
            font-family: Arial, Helvetica, sans-serif;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            background: rgba(0,0,0,0.8);
            z-index: 99999;
            opacity:1;
            -webkit-transition: opacity 400ms ease-in;
            -moz-transition: opacity 400ms ease-in;
            transition: opacity 400ms ease-in;
            pointer-events: auto;
        }
        .modalDialog:target {
            opacity:1;
            pointer-events: auto;
        }

        .modalDialog > div {
            width: 400px;
            position: relative;
            margin: 10% auto;
            padding: 5px 20px 13px 20px;
            border-radius: 10px;
            background: #fff;
            background: -moz-linear-gradient(#fff, #999);
            background: -webkit-linear-gradient(#fff, #999);
            background: -o-linear-gradient(#fff, #999);
        }

        .close {
            background: #606061;
            color: #FFFFFF;
            line-height: 25px;
            position: absolute;
            right: -12px;
            text-align: center;
            top: -10px;
            width: 24px;
            text-decoration: none;
            font-weight: bold;
            -webkit-border-radius: 12px;
            -moz-border-radius: 12px;
            border-radius: 12px;
            -moz-box-shadow: 1px 1px 3px #000;
            -webkit-box-shadow: 1px 1px 3px #000;
            box-shadow: 1px 1px 3px #000;
        }

        .close:hover { background: #00d9ff; }
    </style>
    <div id="loadingg">
        <img id="loading-image" src="http://cdn.nirmaltv.com/images/generatorphp-thumb.gif" alt="Loading..." />
    </div>
    <!----------------------------------------------------------->

    <script>
        function downloadDocument(id, viewId)
        {
            document.getElementById("drugDetailIdDocument").value = id;
            document.getElementById("dpfdoc").value = viewId;
            document.getElementById("downloadForm").submit();
            //alert("VAL "+id);
        }

        function downloadImageDocument(id)
        {
            document.getElementById("drugDetailIdImage").value = id;

            document.getElementById("downloadImageForm").submit();
            //alert("VAL "+id);
        }

        function openModalDiv(div, idFld, id)
        {
            document.getElementById(div).style.display = "inline";
            document.getElementById(idFld).value = id;
            //alert("VAL "+document.getElementById("drugDetailId").value);
        }


        function closeModal(div)
        {
            document.getElementById(div).style.display = "none";
        }

        function openDrugListDiv()
        {
            document.getElementById('drugListDiv').style.display = "inline";
        }
        function validateField() {
            if (document.getElementById("fileUpload").files.length == 0) {
                $("#isFileEmpty").removeAttr("style");
                $("#isFileEmpty").text("Required");
                return false;
            }
            return true;
        }
    </script>
    <style>

        input[type=number]{
            width: 90px;
        } 
    </style>

    <!----------------------------------------------------------->
    <jsp:include page="./inc/header.jsp" />

    <div id="wrapper">
        <div id="content" class="clearfix">
            <jsp:include page="./inc/newMenu.jsp" />
            <div class="breadcrumbs">
                <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Setup

            </div>
            <div class="heading clearfix">
                <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Drug</h1>
                <div class="drug_search">
                    <form action="${pageContext.request.contextPath}/drugBrand/list/searchdrugdata">
                        <input autocomplete="off" type="text" placeholder="Type brand reference to search." id="drugName" name="drugName" class="form-control field_s" value="${searchDrug}" > 
                        <input type="submit" value="Search" class="btn durg_bt" />
                    </form>
                </div>
            </div> <!-- /header -->
            <!---------------------------------------------------------------------------->
            <div class="after_messages">

                <c:if test="${not empty message}">
                    <div class="drug_success">${message}</div>
                </c:if>
                <div id="drug_error" class="drug_error" style="display: none;">${not empty errorMessage?errorMessage:''}</div>
            </div>
            <c:if test="${(sessionBean.pmap[(6).intValue()].manage eq true)}">
                <div id="drugListDiv" class="drug_up" >
                    <form  method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/drugBrand/importlist" onsubmit="return validateImportField();">
                        <div >
                            <label >Select File (.xlsx only)</label>
                            <input name="fileData" id="fileData" type="file"/><br />
                            <input type="submit" value="Import" class="btn bt-drug" />
                        </div>
                    </form></div> 
                </c:if>
            <!----------------------------------------------------------------------------------->

            <div id="openModal" class="modalDialog drugmodel" style="display:none">
                <div>
                    <form id="uploadForm"  method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/drugBrand/uploaddrugdocument">
                        <a href="javascript:closeModal('openModal')" title="Close" class="close">X</a>
                        <h2>Upload Document</h2>
                        <div >
                            <label >Type:</label>
                            <select id="docTypeOpt" name="docTypeOpt">
                                <option value="1">Patient Guide</option>
                                <option value="2">Medication Guide</option>
                                <option value="3">Image</option>
                            </select>
                            <input name="fileUpload" id="fileUpload" type="file"/><br />
                            <input type="submit" value="Import" class="btn bt-drug" />
                        </div>
                        <input type="hidden" id="drugDetailId" name="drugDetailId" value=""/>
                    </form>

                </div>
            </div>
            <!-------------------------------------------------------------------------------------->
            <div id="drugDataModal" class="modalDialog editdrugmodel" style="display:none">
                <div class="main_wr">
                    <form id="drugDataForm"  method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/drugBrand/uploaddrugdocument">
                        <a href="javascript:closeModal('drugDataModal')" title="Close" class="close">x</a>
                        <h2>Edit Drug Detail</h2>
                        <!----------------------------------------------->
                        <div class="edit_drig_wrap clearfix">
                            <div class="col-sm-3 dedit_col">
                                <label>NDC</label>
                                <input type="text" maxlength="15" size="20" id="ndcFld" value="" class="form-control" disabled="true" />
                            </div>


                            <div class="col-sm-3 dedit_col">
                                <label>GCN</label>
                                <input type="text" maxlength="15" size="11" id="gcnFld" value="" class="form-control" onkeypress="return IsDigit(event)"/>
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>GPI</label>
                                <input type="text" maxlength="15" size="11" id="gpiFld" value="" class="form-control" onkeypress="return IsDigit(event)"/>
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>In Stock</label>
                                <input type="text" maxlength="1" size="1" id="inStockFld" value="" class="form-control" onkeypress="return validateYNIgnoreCase(event)"/>
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Generic</label>
                                <input type="text"  size="11" id="genericFld" value="" oninput="window.transferRequest.lookUpObject('genericFld','${pageContext.request.contextPath}/drugBrand/searchDrugGenericName');" class="form-control" />
                            </div>


                            <div class="col-sm-3 dedit_col">
                                <label>Therapeutic</label>
                                <input type="text" maxlength="15" size="20" id="therapeuticFld" value="" class="form-control"  />
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Brand</label>
                                <input type="text" maxlength="15" size="11" id="drugFld" value="" class="form-control" />
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Strength</label>
                                <input type="text" maxlength="15" size="11" id="strengthFld" value="" class="form-control" />
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Def. Qty</label>
                                <input type="text" maxlength="15" size="1" id="qtyFld" value="" class="form-control" onkeypress="return IsDigit(event)"/>
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Form</label>
                                <input type="text"  size="11" id="formFld" value="" class="form-control" oninput="window.transferRequest.lookUpObject('formFld','${pageContext.request.contextPath}/drugBrand/searchDrugForm');" onkeypress="return onlyAlphabets(event, this)"/>
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Qty Desc.</label>
                                <input type="text" maxlength="200" id="packageSizeValuesFld" value="" class="form-control" onkeypress="return isNumberAndComma(event)"/>
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Packing</label>
                                <input type="text"  size="11" id="packingFld" value=""  class="form-control" />
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Price</label>
                                <input type="text" maxlength="15" size="11" id="priceFld" value="" class="form-control isDecimalValue" />
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Margin(%)</label>
                                <input type="text" maxlength="4" id="marginFld" value="" class="form-control" onkeypress="return isFloatNumber(this, event)"/>
                            </div>

                            <div class="col-sm-3 dedit_col">
                                <label>Add. Fee</label>
                                <input type="text"  size="11" id="addFeeFld" value="" oninput="window.transferRequest.lookUpObject('genericFld','${pageContext.request.contextPath}/drugBrand/searchDrugGenericName');" class="form-control isDecimalValue" />
                            </div>
                            <div class="col-sm-12 dedit_col dg_bt_e"><input type="button" value="Save" onclick="window.transferRequest.saveDrugDetail(0)" class="btn bt-drug" />
                                <input type="hidden" id="editDrugDetailId" name="drugDetailId" value="" /></div>
                        </div>


                    </form>

                </div>
            </div>
            <!------------------------------------------------------------------------------------->

            <form id="drugdatasubmit" action="${pageContext.request.contextPath}/drugBrand/list/drugdata">

            </form>

            <!--------------------------------------------------------------------------->
            <div id="editDrugModal" class="modalDialog drugmodel" style="display:none">
                <div>
                    <form id="editDrugModal"  method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/drugBrand/list/drugdata" onsubmit="return validateField();">
                        <a href="javascript:closeModal()" title="Close" class="close">X</a>
                        <h2>Edit Drug</h2>
                        <div class="contents">
                            <label >Type:</label>
                            <select id="docTypeOpt" name="docTypeOpt">
                                <option value="Pdf">Patient Guide</option>
                                <option value="DrugDoc">Medication Guide</option>
                                <option value="image">Image</option>
                            </select>
                            <input name="fileUpload" id="fileUpload" type="file"/><br />
                            <div id="isFileEmpty" class="errorMessage uploadDocMsg"></div>
                            <input type="submit" value="Import" class="btn bt-drug" />
                        </div>
                        <input type="hidden" id="drugDetailId" name="drugDetailId" value=""/>
                    </form>

                </div>
            </div>


            <!---------------------------------------------------------------------------->
            <form id="downloadForm"  method="post"  action="${pageContext.request.contextPath}/drugBrand/downloaddocument">
                <input type="hidden" id="drugDetailIdDocument" name="drugDetailIdDocument" value=""/>
                <input type="hidden" id="dpfdoc" name="dpfdoc" value=""/>

            </form>
            <form id="downloadImageForm"  method="post"  action="${pageContext.request.contextPath}/drugBrand/downloadimage">
                <input type="hidden" id="drugDetailIdImage" name="drugDetailIdImage" value=""/>


            </form>
            <form action="${pageContext.request.contextPath}/drugBrand/list/searchdrugdata">
                <div class="page-message messageheading">
                    <%--<c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                    <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>--%>
                </div>


                <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                <div class="drug_t_cont clearfix">

                    <div class="table_dwra">
                        <table cellspacing="0" cellpadding="0" class="data_tables" id="example">
                            <thead>
                                <tr>  
                                    <th style="padding-right:19px;">Action</th>
                                    <th class="hidden-xs">Patient Document</th>
                                    <th class="hidden-xs">Drug Document</th>
                                    <th class="hidden-xs">Drug Image</th>

                                    <th class="hidden-xs">NDC</th>
                                    <th class="hidden-xs">GCN</th>
                                    <th class="hidden-xs">GPI</th>
                                    <th>In&nbsp;Stock</th>
                                    <th>Generic</th>
                                    <th class="hidden-xs">Therapeutic</th>
                                    <th class="hidden-xs">Brand Reference</th>
                                    <th class="hidden-sm">Strength</th>
                                    <th class="hidden-sm">Form</th>
                                    <th class="hidden-sm">Packing&nbsp;Desc</th>
                                    <th class="hidden-sm">Qty</th>
                                    <th class="hidden-sm">Qty&nbsp;Desc.</th>
                                    <th class="hidden-sm">Price</th>
                                    <th class="hidden-sm">(%)</th>
                                    <th class="hidden-sm">Add&nbsp;Fee</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="drugCategory" items="${drugCategoriesList}">

                                    <tr>
                                        <td>
                                            <a href="#" class="drug_edit_btn" onclick="window.transferRequest.loadDrugDertail(${drugCategory.drugNDC}, 'drugDataModal',
                                               ${drugCategory.drugGCN},${drugCategory.drugGPI}, '${drugCategory.inStock}', '${drugCategory.drugBasic.drugGeneric.genericName}',
                                    '${drugCategory.drugBasic.drugGeneric.therapeuticClass}', '${drugCategory.drugBasic.brandName}',
                                    '${drugCategory.strength}', '${drugCategory.drugForm.formDescr}', '${drugCategory.packingDesc}',${drugCategory.defQty},
                                    '${drugCategory.packageSizeValues}',
                                               ${drugCategory.basePrice},${drugCategory.marginPercent},${drugCategory.additionalFee})"><i class="fa fa-pencil-square-o" aria-hidden="true" title="Edit"></i></a>
                                            <a href="#" class="drug_delete_btn" onclick="window.transferRequest.deleteDrugDetail(${drugCategory.drugNDC});"><i class="fa fa-trash-o" aria-hidden="true" title="Delete"></i></a>
                                            <a href="#" onclick="openModalDiv('openModal', 'drugDetailId',${drugCategory.drugNDC});" class="drug_up_btn"><i class="fa fa-upload" aria-hidden="true" title="Upload"></i></a>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${drugCategory.pdf eq '1'}">
                                                    <a href="javascript:window.open('${drugCategory.pdfDocUrl}', '', 
                                                       'width=500,height=500');" target="_blank"> 
                                                        <img src="${pageContext.request.contextPath}/resources/images/drug_pdf.png"  alt="View Patient Document" title="View Patient Document" />
                                                    </a>
                                                </c:when>    
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                        <td >   

                                            <c:choose>
                                                <c:when test="${drugCategory.drugDoc eq '1'}">
                                                    <a href="javascript:window.open('${drugCategory.drugDocUrl}', '', 
                                                       'width=500,height=500');" target="_blank"> 
                                                        <img src="${pageContext.request.contextPath}/resources/images/drug_pdf.png"  alt="View Drug Document" title="View Drug Document" />
                                                    </a>
                                                </c:when>    
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                        <td > 

                                            <c:choose>
                                                <c:when test="${drugCategory.image eq '1'}">
                                                    <a href="javascript:window.open('${drugCategory.imgUrl}', '', 
                                                       'width=500,height=500');" target="_blank"> 
                                                        <img src="${pageContext.request.contextPath}/resources/images/drug_img.png" 
                                                             alt="View Drug Image" title="View Drug Image" />
                                                    </a>
                                                </c:when>    
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>

                                        </td>



                                        <td class="hidden-xs">${drugCategory.drugNDC}</td>
                                        <td class="hidden-xs">${drugCategory.drugGCN}</td>
                                        <td class="hidden-xs">${drugCategory.drugGPI}</td>
                                        <td class="hidden-xs">${drugCategory.inStock}</td>
                                        <td class="hidden-xs">${drugCategory.drugBasic.drugGeneric.genericName}</td>
                                        <td class="hidden-xs">${drugCategory.drugBasic.drugGeneric.therapeuticClass}</td>
                                        <td class="hidden-xs">${drugCategory.drugBasic.brandName}</td>
                                        <td class="hidden-xs">${drugCategory.strength}</td>
                                        <td class="hidden-xs">${drugCategory.drugForm.formDescr}</td>
                                        <td class="hidden-xs">${drugCategory.packingDesc}</td>
                                        <td class="hidden-xs">${drugCategory.defQty}</td>
                                        <td class="hidden-xs">${drugCategory.packageSizeValues}</td>
                                        <td class="hidden-xs">${drugCategory.basePrice}</td>
                                        <td class="hidden-xs">${drugCategory.marginPercent}</td>
                                        <td class="hidden-xs">${drugCategory.additionalFee}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="wrp clearfix" style="padding:0 15px 0;">



                    <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
            </form>
        </div>
    </div> <!-- /content -->
    <jsp:include page="./inc/footer.jsp" />
</div> <!-- /wrapper -->

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/transferRequest.js"></script>

<script type="text/javascript">
                                                $(window).load(function () {
                                                    // alert("load");
                                                    $("#loadingg").fadeOut();
                                                    window.transferRequest.populateAppUrl('${pageContext.request.contextPath}');
                                                });
                                                function validateImportField() {
                                                    if (document.getElementById('fileData').files.length == 0) {
                                                        $("#drug_error").text("Only .xlsx files are allowed.");
                                                        $("#drug_error").removeAttr("style");
                                                        return false;
                                                    }
                                                    return true;
                                                }
</script>
</body>
</html>

