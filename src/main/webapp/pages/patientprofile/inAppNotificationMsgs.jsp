<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Modal Medication For Refill too Soon-->
<div id="medicationModalRefill" class="medicationModal listModal healthModal formModal modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="window.pharmacyNotification.closeInfoDivRefill()">&times;</button>
                <h4 id="inAppInfoModal" class="modal-title"><label id="msg1Refill" style="color:red"></label> 
                    <label id="msg2Refill"></label></h4>
            </div>
            <div class="modal-body refill_medi">
                <input type="hidden" id="eventNameRefill" />   
                <input type="hidden" id="responseRefill" /> 
                <div id="inAppInfoRefill" style="display:none" align="left"></div>
                <div id="medicationInfoDiv">
                <div class="clearfix icmedicen">
                    <label class="Medication">
                        Medication:
                    </label>
                    <span id="medicationModalRefillDrug" class="drug">
                        ${order.drug}.
                    </span>
                </div>
                <div class="clearfix icmedicen" >
                    <label class="Medication">
                        Quantity:
                    </label>
                    <span class="drug" id="medicationModalRefillQty">
                        ${order.quantity}
                    </span>
                </div>
                
                <div class="refill_options">
                <p class="clearfix"><span>Next Refillable</span>
                    
                    <fmt:formatDate pattern="MM-dd-yyyy" value="11/7/2016" var="date"/>	
                    <input type="text" value="11/07/2016" class="fill_field" /> </p>
                
                <p class="clearfix"><span>MBR Cust SVC#</span><input type="text" value="1-800-521-2227" class="fill_field" /></p>
                <p class="clearfix"><span>Insurer</span><input type="text" value="BCBS GA" class="fill_field" /></p>
                </div>
                </div>
                <!-------------------------------------------------------------------->
                <div style="text-align: left; position: relative; ">
                   
                </div>
                <div id="customMessageDivRefill" >
                    <div id="textarea" 
                         contenteditable style="overflow:auto">
                             <p><span style="font-weight: bold;">MSG:<br></span>
                                 <span style="color: rgb(255, 0, 0);">(CRITICAL)</span>:Insurance did not Approve Payment of your Rx Order - "REFILL TOO SOON"<br></p><p><span style="font-weight: bold;">
                                     TRANSACTION DETAILS:</span></p><p>MEDICATION: <span style="font-weight: bold;">
                                             <span style="color: rgb(57, 132, 198);">${order.drugNameWithoutStrength} ${order.strength}  ${order.drugType}</span></span></p>
                                             <p>QTY REQUESTED: <span style="color: rgb(57, 132, 198);"><span style="font-weight: bold;">${order.quantity}</span></span></p>
                                             <p>NEXT REFILL DATE: 11/7/2016
                                                 <span style="color: rgb(57, 132, 198);"><span style="font-weight: bold;">
                                                <fmt:formatDate value='11/7/2016' pattern='mm/dd/yyyy'/></span>
                                                     
                                                 </span></p><p><span style="font-weight: bold;">ACTION REQUIRED</span> (Three Options Available)
                                                     <span style="font-weight: bold;">:<br></span>1) Wait until  11/7/2016, We will reschedule processing on the day Insurance allows refill.</p><p>2) Call you insurance provider. This denial may be resolved by you if you need a vacation supply.</p>
                    </div>
                </div>
                <!-------------------------------------------------------------------->
                <div class="col-lg-12">
                    <hr style="border: 1px solid;">
                </div>
                <div style="text-align: center; position: relative; bottom: 10px;">
                  <!--
                    <input id="cancel_btnRefill" type="submit" class="btn back_btn" value="Cancel
                          style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;">-->
                          <input id="saveMsgButtonRefill" type="submit" class="btn back_btn" value="SEND" 
                     onclick="window.pharmacyNotification.sendInAppRefillTooSoonNotification(
                                    '',0,${order.orderId},${order.patientId}, 'Rx Order', '',
                                    '', '', '','eventNameRefill','responseRefill','inAppInfoRefill','textarea')" 
                                    style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;">&nbsp;
                    
                </div>

            </div>
        </div>

    </div>
</div>
<!--Modal Medication For Refill too soon-->

<!-- Modal Medication -->
<div id="medicationModal" class="medicationModal listModal healthModal formModal modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="window.pharmacyNotification.closeInfoDiv()">&times;</button>
                <h4 id="inAppInfoModal" class="modal-title"><label id="msg1" style="color:red"></label> <label id="msg2"></label></h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="eventName" />   
                <input type="hidden" id="response" /> 
                <div id="inAppInfo" style="display:none" align="left"></div>
                <div id="medicationInfoDiv">
                <div class="clearfix">
                    <div class="col-lg-12 Medication">
                        <div class="col-sm-2 padd-zero">
                            Medication:
                        </div>
                        <div class="col-sm-8 padding-left" id="medicationModalDrug">
                            ${order.drug}
                        </div>
                        <div class="col-sm-1 padding-left Medication">
                            Quantity:
                        </div>
                        <div class="col-sm-1 padding-left drug" id="medicationModalQty">
                            ${order.quantity}
                        </div>
                         
                    </div>
<!--                    <div class="col-lg-12 drug" >
                        
                    </div>-->
                </div>
                <div class="clearfix">
<!--                    <div class="col-lg-12 ">
                        
                    </div>
                    <div class="col-lg-12 " >
                        
                    </div>-->
<!--
                    <div class="col-lg-12" style="color:red;display:none" id="warnEditDiv"> <strong>Note:</strong> Please don't edit the values within [] since these are placeholders
                     & will be replaced dynamically.</div>-->
                </div>
                </div>
                <!-------------------------------------------------------------------->
                <div style="text-align: left; position: relative; ">
                   
                </div>
                <div id="customMessageDiv" style="height:400px;width:100%;overflow-y:scroll;" contenteditable="true">
                    <textarea id="customMessageId" name="customMessageId" class="messageBox" 
                     placeholder="Send Message to Patient"></textarea>
                </div>
                <!-------------------------------------------------------------------->
                <div class="col-lg-12">
                    <hr style="border: 1px solid;">
                </div>
                <div style="text-align: center; position: relative; bottom: 15px;">
                    <span id="responseMsg2" style="padding-left: 140px;"></span>
                     <span id="pdfFileReq2" class="errorMessage"></span>
                    <input id="saveMsgButton" type="submit" class="btn back_btn" value="SEND" onclick="window.pharmacyNotification.sendInAppNotification(
                                    '',0,${order.orderId},${order.patientId}, 'Rx Order', '',
                                    '', '', '','eventName','response','inAppInfo','customMessageDiv','isCritical')" 
                                    style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;">&nbsp;
                     <input type="buton" class="btn back_btn" value="Edit" id="editMsgButton"
                            style="display:none;width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;"
                           onclick="window.pharmacyNotification.showEditDrugDiv('customMessageDiv');
                                    window.pharmacyNotification.hideEditDrugDiv('editMsgButton');
                                    window.pharmacyNotification.hideEditDrugDiv('medicationInfoDiv');
                                    window.pharmacyNotification.setTextAreaValue('customMessageId','${patientProfile.firstName}'+' '+'${patientProfile.lastName}',
                                         '${order.drug}','${order.drugType}','${order.quantity}')"/>
                    
                     <input id="drugPdffile2" type="file" style="display: none;" 
                            onchange="window.pharmacyNotification.displayFile('drugPdffile2', 'pdfFileReq2', 'pdfFileName2', 'pdf', this.value);"/>
                     <input id="pdfFileName2" type="hidden" />
                     <input type="button" class="btn back_btn" value="Upload" id="uploadButton2"
                           style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;display: none;" 
                           onclick="window.pharmacyNotification.uploadFile2('drugPdffile2');" />
                     <span id="lbfileName"></span>
<!--                     <p id="textColor"style="color: black"> <span id="lbfileName"></span></p>-->
                </div>

            </div>
        </div>

    </div>
</div>
<!-- Modal Medication -->
<!-- Modal Medication For Processing-->
<!------------------------------------------------------------------>
<div id="medicationModal2" class="medicationModal listModal healthModal formModal modal fade"  role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" 
                        onclick="window.pharmacyNotification.closeInfoDiv2('eventNameProcessing','responseProcessing',
                            'inAppInfoProcessing')">&times;</button>
                <h4 id="inAppInfoModal" class="modal-title"><label id="processMsg1" style="color:red"></label> <label id="processMsg2"></label></h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="eventNameProcessing" />   
                <input type="hidden" id="responseProcessing" /> 
                <div id="inAppInfoProcessing" style="display:none" align="left"></div>
                <div id="medicationInfoDiv2">
                <div class="clearfix">
                    <!---------------------------------------------------------->
                     <table width="100%">
                                    <tr>
                                        <th width="50%" align="left" class="Medication">Medication</th>
                                        <th width="25%" class="Medication">Type</th>
                                        <th width="25%" align="center" class="Medication">Quantity</th>
                                     
                                    </tr>
                                    <tr>
                                        <td class="drug" id="medicationModal2Drug">
                                             ${order.drug}
                                        </td>
                                        <td class="drug" id="medicationModal2Type">${order.drugType}</td>
                                        <td align="center" class="drug" id="medicationModal2Qty">${order.quantity}</td>                                        
                                       
                                    </tr>
                                   
                     </table>
                    <!---------------------------------------------------------->
                </div>
                </div>
                <!-------------------------------------------------------------------->
                
                <div id="customMessageDiv2" style="display:none">
                    <textarea id="customMessageId2" name="customMessageId" class="messageBox" 
                     placeholder="Send Message to Patient"></textarea>
                </div>
                <!-------------------------------------------------------------------->
                                        <!--
                <div class="clearfix">
                     <div class="comments_section">
                                    <div class="col-md-9 comment_area">
                                        <textarea id="defMsg"  class="form-control cust summernote textarea"      /> 
                                                  </textarea>
                                    </div>
                     </div>
                </div>-->
                <div class="col-lg-12">
                    <hr style="border: 1px solid;">
                </div>
                <div style="text-align: center; position: relative; bottom: 15px;">
                    <input type="submit" id="saveMsgButton2" class="btn back_btn" value="SEND" onclick="window.pharmacyNotification.sendInAppProcessingNotification(
                                    '',0,${order.orderId},${order.patientId}, 'Rx Order', '',
                                    '', '', '','eventNameProcessing','responseProcessing',
                                    'inAppInfoProcessing','customMessageId2')" 
                                    style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;">
                    <input type="buton" class="btn back_btn" value="Edit" id="editMsgButton2"
                           onclick="window.pharmacyNotification.showEditDrugDiv('customMessageDiv2')
                                    window.pharmacyNotification.hideEditDrugDiv('editMsgButton2');
                                    window.pharmacyNotification.hideEditDrugDiv('medicationInfoDiv2')
                                    window.pharmacyNotification.setTextAreaValue('customMessageId2','${patientProfile.firstName}'+' '+'${patientProfile.lastName}',
                                         '${order.drug}','${order.drugType}','${order.quantity}');"
                           style="width: 97px; vertical-align: middle; height: 30px; padding-top: 4px;"/>
                </div>
                
                
                                    
                
                

            </div>
        </div>

    </div>
</div>




<!----------------------------------------------------------------->

<div id="medicationModalGeneric" class="newmedimodel modal fade"  role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
            <h2>My Alerts</h2>
                <button type="button" class="close" data-dismiss="modal" onclick="window.pharmacyNotification.closeInfoDiv2('eventNameProcessing','responseProcessing',
                            'inAppInfoProcessing')">&times;</button>
             <h3>Awaiting Reply</h3>
            </div>
            <div class="modal-body">
            <div class="col-xs-3 blue_bg"><a href="#" class="prv_a">Previous</a></div>
            <div class="col-xs-6 new_msge">New Message 2 of 4</div>
            <div class="col-xs-3 blue_bg"><a href="#" class="next_a">Previous</a></div>
      <div class="body_contentt clearfix">
      <h4>Subject: <span>RECEIVED (4:16pm 01/13/2015)</span></h4>
      <p>YOUR Rx REQUEST IS AWAITING REPLY FROM<br />
DOCTOR OR PREVIOUS PHARMACY</p>

<h4>TRANSACTION DETAILS: </h4>
<h5>SERVICE REQUESTED: <span>TRANSFER RX via APP</span> </h5>
<h5>REQUEST MADE: <small>3:16pm 01/13/2016)</small></h5>
<h5>MEDICATION: <span>PROPRANOLOL 20MG TABS</span></h5>
<h5>PAYMENT CARD CHARGED: <span>$22.97</span></h5>
<h5>ADDITIONAL PAYMENT REQUIRED: <span>N0</span></h5>

<div class="pharmacy_commets">
<h6>PHARMACY COMMENTS: <span>(350 characters)</span></h6>
<p>WE ARE ATTEMPTING TO SECURE THE AUTHORIZATION TO REFILL
YOUR PRESCRIPTION.<br />
UP TO THIS MOMENT NO REPLY BACK TO US .<br />
IF YOU HAPPEN TO BE IN TOUCH WITH EITHER– YOU MAY
REQUEST THEY CALL ME DIRECTLY -- KEN AT 817-905-2855</p>


<ul>
<li>INSURANCE COPAY: <span>$ 17.50</span></li>
<li>INSURANCE COPAY: <span>$ 17.50</span></li>
<li>COMPLIANCE REWARD PTs APPLIED : 396<span> - $ 11.88</span></li>
<li>2ND DAY HANDING CHARGE: <span>$ 2.75</span></li>
<li>DELIVERY METHOD: <li>1-2 days</li></li>
<li><span>TOTAL $ 8.37</span></li>

</ul>
</div>
<a href="#" class="ctr_btn btn">AUTHORIZE PAYMENT</a>
<div class="action_comment"><h4>ACTION: </h4>
<p>You CAN CONTINUE TO WAIT FOR THEIR REPLY
or CANCEL YOUR TRANSFER REQUEST . </p>
<a href="#" class="ctr_btn btn">Cancel Transfer Request</a>

<h4>QUESTION FOR PHARMACY : <span>(225 CHARACTERS)</span></h4>
<textarea class="form-control"></textarea>
<input type="submit" name="send" value="SEND A QUESTION" class="btn qsent" />
</div>
<a href="#" class="btn markvbtn">MARK READ / VIEW NEXT MSG</a>

      </div>
            </div>
        </div>

    </div>
</div>
<input type="hidden" id="delOption" value="0"/>
<input type="hidden" id="autodelOption" value="0"/>
<!-- Modal Medication For Processing-->

<!--<script language="JavaScript">
     var fileName= $('#drugPdffile2').val().split('\\').pop();
               document.write(fileName);
</script>-->

    
<style>
    .modal-body{
        padding-bottom: 2px !important;
    }
    .medicationModal.modal .modal-dialog,.medicationModal.modal .modal-content{
        width:400px;
        
    }
    
    
    .medicationModal  ul{
        margin:0;
        padding:0;
    }
    .medicationModal li{
        display:block;
        width:100%;
        margin:0;
    }
    .medicationModal li:not(:first-child){
        margin-top:10px;
    }

    .drug{
        color:#2170b3;
    }
    .Medication{
        color:#666666;
        margin-top:2px;
    }
    
    #textarea {
    -moz-appearance: textfield-multiline;
    -webkit-appearance: textarea;
    border: 1px solid gray;
    font: medium -moz-fixed;
    font: -webkit-small-control;
    height: 28px;
    overflow: auto;
    padding: 2px;
    resize: both;
    width: 390px; 
    height: 300px;
  }
</style>