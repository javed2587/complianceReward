<%-- 
    Document   : patientActivityDetails
    Created on : Aug 30, 2019, 1:22:22 PM
    Author     : ahsan.sharif
--%>

<div id="patientActivityDetails" class="medicationModal confirmation_modal listModal healthModal formModal modal fade" role="dialog">
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
