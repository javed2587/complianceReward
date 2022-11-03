<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Modal Dependent For Refill too Soon-->
<div id="programRxModal" class="medicationModal confirmation_modal listModal healthModal formModal modal fade"  role="dialog">
    <div class="modal-dialog mti_rx_hostory_margtop">
        <input type="hidden" id="programPatientId" value="" />
        <input type="hidden" id="hiddenPharmacyAbbr" value="${sessionBeanPortal.pharmacyAbbr}-" />
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="">&times;</button>
                    <h4  class="modal-title"><label id='programRxCount'>ALL PROGRAM RX's (0)</label>   <span id="programPatientName" style="padding-left: 20px;font-weight:bold;">Name</span>
                    </h4>
               
            </div>
            <div class="modal-heading">
               
                <!--<span >Adult - Name</span>-->
            </div>
                   
                </div>
            <div class="col-md-12 padd-zero custome_right background_white_color" id="patient_rx">
                <div class="tableContainer">                
<!--                    <div class="col-sm-3 padd_zero">
                        <ul>
                            <li>Patient</li>
                            <li class="blueText" id="patientNameFld">---</li>
                          
                        </ul>


                    </div>-->
               
                    <div class="tab-content">
                       
                        <div id="" class="tab-pane active">
                            <div id="divSameDayOrd" class="col-sm-12 back_white" style="display: none;">
                                <div id="message" class="col-sm-6">
                                  
                                </div>
                                <div class="col-sm-6 right_flo">
                                    <button class="btn btn-primary right_flo" onclick="window.pharmacyNotification.processSameDayShippingRxOrders('programRxModal',0);" title="CLICK HERE FOR SHIPPED SAME DAY ORDERS">SHIPPING SAME DAY ORDERS</button>
                                </div>
                            </div>
                            <table id="programRxTable"  class="table-striped" >
                                <thead>
                                    <tr>
                                        <th><input id="chkAllSameDay" type="checkbox" name="chkAllSameDay" style="display: none;" class="left" title="SELECT ALL SAME DAY ORDERS" onclick="window.pharmacyNotification.allSameDayMainChkBox();">RX<br/>NUMBER #</th>
                                        <th>DATE<br/>COMPLETED</th>
                                        <th id="medication">Medication</th>
                                        <th>QTY</th>
                                        <th>INS</th>
                                        <th>PT OUT OF<br/>PCKT</th>
                                        <th>Service</th>
                                        <th>Delivery<br/>Zip Code</th>
                                        <th>Rx Origin</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
               
               
                <div id="popUpdependent" ></div>
               
                 
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
.test{}
</style>
<script>
       
        // Jquery draggable
        $('#programRxModal').draggable({
            handle: ".modal-header"
        });
</script> 
