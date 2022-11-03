<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Modal Dependent For Refill too Soon-->
<div id="deliveryPrefModal" class="medicationModal confirmation_modal listModal healthModal formModal modal fade"  role="dialog">
    <div class="modal-dialog mti_rx_hostory_margtop">
        <input type="hidden" id="samePrefPatientId" value="" />
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="">&times;</button>
                  <h4  class="modal-title"><label>Order with Same Preference</label> 
                        </h4>
                   
            </div>
            <div class="modal-heading">
                <h4 id="samePrefPatientName">Name</h4>
                <!--<span >Adult - Name</span>-->
            </div>
                   
                </div>
            <div class="col-md-12 padd-zero custome_right rx_newtable">
                <div class="tableContainer">                
<!--                    <div class="col-sm-3 padd_zero">
                        <ul>
                            <li>Patient</li>
                            <li class="blueText" id="patientNameFld">---</li>
                          
                        </ul>


                    </div>-->
               
                    <div class="tab-content">
                       
                        <div id="" class="tab-pane active">
                            <table class="table-striped" id="deliveryPrefTable">
                                <thead>
                                    <tr>
                                        <th>Req#</th>
                                        <th>Date</th>
                                        <th>Rx Origin</th>
                                        <th>Svc. Fee</th>
                                        <th>Medication</th>
                                        <th>Qty</th>
                                        <th>Svc</th>
                                        <th>Request</th>
                                       

                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
               
               
              
               
                 
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
//        $('#programRxModal').draggable({
//            handle: ".modal-header"
//        });
</script> 
