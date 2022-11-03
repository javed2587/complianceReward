<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Modal Dependent For Refill too Soon-->
<div id="dependentModal" class="medicationModal listModal dependentmodal healthModal formModal modal fade" role="dialog">
    <div class="modal-dialog">
        <input type="hidden" id="dependentId" value="" />
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="">&times;</button>
                <h4  class="modal-title"><label>Dependent Profile</label> 
                    <span>Fill History</span>  </h4>
            </div>
            <div class="modal-body refill_medi">
                <div class="col-sm-12 padd_zero">                
                    <div class="col-sm-3 padd_zero">
                        <ul>
                            <li>Name</li>
                            <li class="blueText" id="dependentNameFld">---</li>
                            <li>DOB</li>
                            <li class="blueText" id="dobFld">---</li>
                            <li>Adult</li>
                            <li class="blueText" id="adultFld">Yes/NO</li>
                            <li>POA Approval Date</li>
                            <li class="blueText" id="approvalDateFld">----</li>
                        </ul>


                    </div>
                    <div class="col-sm-3 padd_zero">
                        <ul>
                            <li>Gender</li>
                            <li class="blueText" id="genderFld">Yes/No</li>
                            <li>Age</li>
                            <li class="blueText" id="ageFld">---</li>
                            <li>Approved</li>
                            <li class="blueText" id="approvedFld">Yes/NO</li>
                            <li>POA Expiry Date</li>
                            <li class="blueText" id="expiryDateFld">----</li>
                        </ul>


                    </div> 

                    <div class="col-sm-6 padd_zero">
                        <div id="" class="tab-pane fade video_div_two active in">
                            <table class="table table-three" id="dependentHistoryTable">
                                <thead>
                                    <tr>
                                        <th>Rx No.</th>
                                        <th>Date<br>Of Svc</th>
                                        <th>Medication</th>
                                        <th>Qty</th>
                                        <th>Days<br>Supply</th>
                                        <th>Days<br>Until<br>Refill</th>
                                        <th>Fills<br>Remain</th>
                                        <th>Origin</th>
                                        <th>Self<br>Pay</th>
                                        <th>Public<br>Ins</th>
                                        <th>Final<br>Payment</th>
                                        <th>Compliance<br>Rewards</th>

                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-sm-1 rx_labe rx_lab padd_zero" >
                    <h5>Allergies</h5>
                </div>
                <div id="dependentAllergiesDiv" class="col-sm-4 rx_allergies padd_zero scroll" contenteditable="">

                </div>
                <div id="popUpdependent" ></div>
                <!-- <input type="button" class="btn-save"  onclick="window.pharmacyNotification.updateDependentAllergies()" value="Save"> -->
            </div>
        </div>

    </div>
</div>

<!-- Modal Medication For Processing-->




<style>
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

</style>