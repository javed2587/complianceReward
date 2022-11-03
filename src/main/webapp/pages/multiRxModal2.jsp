<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Modal Dependent For Refill too Soon-->
<div id="multiRxModal" class="medicationModal confirmation_modal listModal healthModal formModal modal fade"  role="dialog">
    <div class="modal-dialog">
        <input type="hidden" id="multiPatientId" value="" />
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="">&times;</button>
                <c:choose>
                    <c:when test="${order.status eq 'DELIVERY' || 
                          order.status eq 'Pickup At Pharmacy'  ||
                          order.status eq 'Shipped'}">
                        <h4  class="modal-title"><label>SHIPPING QUEUE</label> 
                        </h4>
                    </c:when>
                    <c:otherwise>
                            <h4  class="modal-title" style="display:inline-block;"><label><small style="color:#fff;" id="multiRxCount">Multi-Rx (Y-0)</small></label>
                                
                        </h4>
                        <h4 id="multiPatientName" style="display:inline; font-weight:600;">Name</h4>
                        
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="modal-heading">
                
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
                            <table class="table-striped" id="multiRxTable">
                                <thead>
                                    <tr>
                                        <!-- <th>Request#</th>
                                        <th>Date<br>Of Svc</th>
                                        <th>Medication</th>
                                        <th>Qty</th>
                                        <th>Svc</th>
                                        <th>Request</th> -->
                                       
                                        <th>Request <br> Control #</th>
                                        <th>Status <br> Posted</th>
                                        <th>Service <br> Requested</th>
                                        <th>Product Requested</th>
                                        <th>Stage <br> Of Process</th>
                                        <th>Rx <br> Number</th>
                                        <th><span style="color:#f60000">New RX</span><sub>CR</sub> <br> Refill</th>
                                        <th>Delivery <br> Zip Code</th>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script>
       
        // Jquery draggable
        $('#multiRxModal').draggable({
            handle: ".modal-header"
        });
</script> 
