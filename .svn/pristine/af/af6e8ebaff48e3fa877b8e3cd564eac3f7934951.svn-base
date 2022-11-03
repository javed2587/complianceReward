<!-------------------------------------------------------------------->
<div id="giveAnswerOfQuestiona" class="medicationModal patient_response confirmation_modal listModal healthModal formModal modal fade" role="dialog">
    <input id="loadPage" type="hidden" <a href="#presponse" aria-controls="presponse" role="tab" data-toggle="tab" onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/responseReceived'" /></a>


<input   type="hidden" id="hiddenQuestionOrderId" value="" />

<div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <!-- <button type="button" class="close" data-dismiss="modal" >&times;</button> -->
            <h4  class="modal-title disp_in_mod"><label>Incoming pt response</label></h4>
            <h5 class="modal-title left" style="float: right;"><label><strong>Likely Topic</strong> Lower cost option</label></h5>
        </div>
        <div class="modal-body refill_medi">
            <div id="postAnswerMessageDiv">
            </div>
            <div>
                <div class="col-sm-5 time_inner_modal">
                    <h6 id="questionTime">
                        13:04:<small>33</small><a href="#">John Deo</a>
                    </h6>
                    <h6 >
                        <div class="col-sm-2">
                            <span id="reqFldAnsQuestionLabel">  Rx#:</span>
                        </div>
                        <div class="col-sm-9">
                            <span>
                                <form id="frmRxdetailWithQuestionId" action="">
                                    <input type="hidden" id="hiddenQuestionId" value="" name="questionId" />
                                    <a href="#" id="reqFldAnsQuestionValue">
                                    </a>
                                </form>
                            </span>
                        </div>
                    </h6>
                </div>
                <div class="col-sm-7 line_inner_modal">
                    <h5>
                        direct link: <span id="patientEmail">Email</span>
                    </h5>
                    <h4 id="osType">
                        iOS app
                    </h4>
                    <h3>
                        Direct link: <span id="mobileNumber">mobile phone</span>
                    </h3>
                </div>
                <div class="refill_options no_disp">
                    <p class="clearfix"><span id="questionSpan">Q:</span>

                    </p>
                    <div class="">
                        <div class="col-sm-1 padd-zero" style="max-width: 25px;"> <span class="answer">A:</span>
                        </div>
                        <div class="col-sm-11 padd-zero ">
                            <input type="text" class="form-control"  id="answerFld" /> </div> 
                    </div>
                    <br />
                    <div class="align-center">
                        <button id="cancelAnswerBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 110px; vertical-align: middle; background-color: #f60000; font-size:12px; line-height:19px; font-family: 'Roboto Condensed', sans-serif; color: #ffd600; font-weight:bold;">Cancel</button>
                        <input id="answerSendBtn" type="button" class="btn back_btn" value="Responded" 
                               onclick="window.pharmacyNotification.saveAnswer();" 
                               style="width: 110px;vertical-align: middle; font-size:12px; line-height:19px; font-family: 'Roboto Condensed', sans-serif; color: #ffd600; font-weight:bold;">
                    </div>
                </div>
            </div> 
        </div>
    </div>
</div>
</div>
<!----------------------------------------------------------------------------------------------------------------->
<div id="addNewRxOrderDlg" class="medicationModal confirmation_modal listModal healthModal formModal modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="window.pharmacyNotification.resetAddNewRxField();">&times;</button>
                <h4  class="modal-title"><label>Patient Information Dialog</label></h4> 

            </div>
            <div class="modal-body refill_medi">

                <div >


                    <div class="refill_options">
                        <p class="clearfix"><span>Do you want to place new order?</span>

                        </p>
                        <div> Mobile # <input type="text" id="addNewRxPatientMobileNo" /> </div>

                        <div id="addNewRxpatientInformation"> 

                        </div>
                        <br />
                        <div style="text-align: right;">


                            <button id="cancelOtherStatusConfirmBoxBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 80px; vertical-align: middle; background-color: #d43f3a;" onclick="window.pharmacyNotification.resetAddNewRxField();">Cancel</button>

                            <input id="confirmOtherStatusBtn" type="button" class="btn back_btn" value="Look Up Patient" 
                                   onclick="window.pharmacyNotification.getPatientInformationByPhoneNumber();" 
                                   style="width: 128px; vertical-align: middle;color:white; ">  
                        </div>

                    </div>
                </div>

                <!---------------------------------------------------------------------------------->
