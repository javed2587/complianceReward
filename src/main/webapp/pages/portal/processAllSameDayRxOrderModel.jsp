<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Modal Dependent For Refill too Soon-->
<div id="sameDayOrderRxModal" class="medicationModal confirmation_modal listModal healthModal formModal modal fade"  role="dialog">
    <div class="modal-dialog mti_rx_hostory_margtop">
        <input type="hidden" id="programPatientId" value="" />
        <input type="hidden" id="hiddenPharmacyAbbr" value="${sessionBeanPortal.pharmacyAbbr}-" />
        <input type="hidden" id="hiddenRxNumbers" value="0" />
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="">&times;</button>
                <h4  class="modal-title"><label id='sameDayRxCount'>SHIPPING SAME DAY RX's (0)</label>   <span id="programPatientName" style="padding-left: 20px;font-weight:bold;display: none;">Name</span>
                </h4>

            </div>
            <div class="modal-heading">

                <!--<span >Adult - Name</span>-->
            </div>

        </div>
        <div class="col-md-12 padd-zero custome_right " id="sameDay_rx">
            <div class="tableContainer">                
                <!--                    <div class="col-sm-3 padd_zero">
                                        <ul>
                                            <li>Patient</li>
                                            <li class="blueText" id="patientNameFld">---</li>
                                          
                                        </ul>
                
                
                                    </div>-->

                <div class="tab-content">

                    <div id="" class="tab-pane active">
                        <table id="sameDayRxOrderTable"  class="table-striped">
                            <thead>
                                <tr>
                                    <th>RX<br/>NUMBER #</th>
                                    <th>DATE<br/>COMPLETED</th>
                                    <th id="medication">MEDICATION</th>
                                    <th>SERVICE</th>
                                    <th class="txtAlign">ORGIN RX<br/> OUT OF<br/>PCKT</th>
                                    <th class="txtAlign" style="display: none;">SHIPPING<br/> FEE</th>
                                    <th class="txtAlign">TOTAL</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">SHIPPING FEE</td>
                                    <td class="txtAlign" style="color: #000000;">
                                        <input id="oldShippingFee" type="hidden"/>
                                        <input id="newShippingFee" class="txtAlign" type="text" value="$0.00" onkeypress="return  float_validation(event, this.value);" onblur="window.pharmacyNotification.addCommas('newShippingFee', 1);window.pharmacyNotification.replaceShippingFee(this.value);"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5">TOTAL AMOUNT</td>
                                    <td class="txtAlign" style="color: #000000;">
                                        <span id="totalMultiRxAmount">$0.00</span>
                                    </td>
                                </tr>
                            </tfoot>
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
