<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home  <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Notification Template
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Notification Template</h1>
                </div>
                
                <div class="container-fluid">
                    <div class="row">
                        <div class="wrapper">
                            <div class="newmedimodel refil"> 
                                <!-- Modal content-->
                                <div class="modal-header">
                                    <h3 class="blue_bg">READY TO DELIVER</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="body_contentt clearfix">
                                        <h4>Subject: <span>RECEIVED ([date_time])</span></h4>
                                        <p><strong>We have completed</strong> processing of <strong>Orders</strong></p>
                                        <div class="clearfix"></div>
                                        <h4><i>Details</i><span class="pull-right blue"><strong>RX: [request_no]</strong></span></h4>
                                        <div class="pharmacy_commets rct_container">
                                            <h5>MEDICATION: <span class="blue">[DRUG_NAME]</span></h5>
                                            <h5 style="float:left;">Qty: <span>[DRUG_QTY]</span></h5>&nbsp;<h5 style="float:left;padding-left: 20px;"> DaysSupply: <span>[DYS_SUPPLY]</span></h5>
                                            <h5>SELF PAY PRICE: <span class="blue">[DRUG_COST]</span></h5>
                                            <h5>INS PRICE APPLIED: <span class="red">[ins_price]</span></h5>
                                            <h5>SALES TAX (TX): <span class="red">N/A</span></h5>
                                            <h5>DELIVERY METHOD: <span class="blue">[delivery_method]</span></h5>
                                            <h5>FEE: <span class="blue">[handling_fee]</span></h5>
                                            <h5>COMPLIANCE REWARD <span class="red">CREDIT:  [points_cost]</span></h5>
                                            <h5>TOTAL CHARGES TO   VISA [card_number]:  <span class="blue">[final_payment]</span></h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <a href="inapp://homeBtn" class="ctr_btn btn backhome">Home</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <jsp:include page="./inc/footer.jsp" />
    </body>
</html>
