<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/patientDetailse.css" />
    <jsp:include page="./inc/header.jsp" />
    <div id="wrapper">
        <div id="content" class="clearfix" style="z-index:0">
            <jsp:include page="./inc/menu.jsp" />
            <div class="breadcrumbs">
                <div><i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Patient Details.</div>
            </div>
            <div class="heading">
                <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Patient Details.</h1><div style="text-align: right; padding-top: 8px; margin-right: 15px;"> <img id="previousUserProfile" src="${pageContext.request.contextPath}/resources/images/Left.png" width="25" style="cursor: pointer;" onclick="previousUser()"/><img id="nextUserProfile" src="${pageContext.request.contextPath}/resources/images/Right.png" width="25" style="cursor: pointer;" onclick="nextUser()" /></div>
            </div> <!-- /header -->
            <form:form action="${pageContext.request.contextPath}/patient/update" commandName="patientProfile" method="post" role="form" autocomplete="off" onsubmit="return validateField()">    
                <form:hidden id="patientId" path="id"/>
                <div class="page-message messageheading">
                    <div class="${not empty message?'message':'errorMessage'}" id="message">${not empty message?message:errorMessage}</div>
                    <br clear="all">
                </div>
                <fmt:formatDate var="nowyear" pattern="yyyy" value="<%=new java.util.Date()%>"/> 


                <div class="wrapper p_detail_wrp">
                    <div class="userBanner clearfix">
                        <div class="clearfix">
                            <ul>
                                <li>NICHOLAS K. (M)</li>
                                <li>11/16/1977 (29 Years)</li>
                                <li>Member Since 01/2016</li>
                                <li><a href="#" data-toggle="modal" data-target="#dependentsModal"> 2 Dependents</a></li>
                                <li>4 Program Rx&acute;s</li>
                            </ul>
                            <p>January 29, 2016 11:41 CST</p>
                        </div>
                    </div>
                    <div class="customTableRow patientTableRow clearfix">
                        <div class="col-md-3">
                            <div class="heightList clearfix">
                                <div class="primume_box">
                                    <p><span>5000</span>Compliance
                                        <br />Reward&trade; Points</p>
                                    <div class="primiume_icon">Premium</div>
                                </div>
                                <ul>
                                    <li><span>Height</span></li>
                                    <li><span>Weight</span></li>
                                    <li><span>BMI</span><a href="#" data-toggle="modal" data-target="#healthModal"><i class="fa fa-pencil"></i></a></li>
                                </ul>
                                <ul>
                                    <li>6.5 ft</li>
                                    <li>215 lbs</li>
                                    <li>50%</li>
                                </ul>

                            </div>
                            <div class="contactContainer">
                                <p><span class="blueText">Phone #.</span> 734-564-4266</p>
                                <p><span class="blueText">Backup Phone #.</span> 734-564-4266</p>
                                <p><span class="blueText">Email</span> sample@email.com</p>
                                <p><span class="blueText">Allergies <a href="#" data-toggle="modal" data-target="#allergyModal" ><i class="fa fa-pencil"></i></a> </span>
                                    <br /> Penicillin, Peanuts, Mango</p>
                            </div>
                            <div class="shippingContainer">
                                <ol type="1">
                                    <li>
                                        <span class="blueText">Shipping Address</span>
                                        <a href="#" data-toggle="modal" data-target="#shippingModal"><i class="fa fa-pencil"></i></a>
                                        <br />
                                        <strong>"Nick Home"</strong>
                                        <br />
                                        <span>16320 E Hardwick Place APT 7-B SOUTHLAKE TX 76092</span>
                                    </li>


                                </ol>
                            </div>
                            <div class="insuranceContainer">
                                <span class="blueText">Insurance Card</span>
                                <a href="#" data-toggle="modal" data-target="#insEditModal"><i class="fa fa-pencil"></i></a>
                                <div>
                                    <img src="${pageContext.request.contextPath}/resources/images/insuranceCard_15.jpg" alt="insurance card front" data-toggle="modal" data-target="#cardsModal" />
                                    <img src="${pageContext.request.contextPath}/resources/images/insuranceCard_17.jpg" alt="insurance card back" data-toggle="modal" data-target="#cardsModal" />
                                </div>
                            </div>
                            <form action="" class="messageForm">
                                <textarea name="message" class="messageBox">Send Message to Patient</textarea>
                                <button type="submit"><i class="fa fa-paper-plane greenText"></i></button>
                            </form>
                            <a href="#" class="greenText messageHistory" data-toggle="modal" data-target="#historyModal">View Message History</a>

                        </div>
                        <div class="col-md-9 rightPatientDiv">

                            <div class="tableContainer">

                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Active Medications (05)</a></li>
                                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Points Balance</a></li>
                                    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Program Activities</a></li>
                                </ul>
                                <div class="rightTableTop searchRecordsContainer">
                                    <form action="">
                                        <select name="numberOfRecords" id="">
                                            <option value="10">10</option>
                                            <option value="20">20</option>
                                            <option value="30">30</option>
                                        </select>
                                        <div class="searchBoxField">
                                            <input type="text">
                                            <button type="submit">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="home">
                                        <div class="tableTenRecord">
                                            <table class="table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Date</th>
                                                        <th>Medication</th>
                                                        <th>QTY</th>
                                                        <th>Ref Rem</th>
                                                        <th>Last Action</th>
                                                        <th>Ins Code</th>
                                                        <th>Orig Copy</th>
                                                        <th>Handling Fee</th>
                                                        <th>Redeemed</th>
                                                        <th>Total State</th>
                                                        <th>Amt Collected</th>
                                                        <th>Next Refill</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>1</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>3</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>4</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>5</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>6</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>7</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>8</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>9</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>10</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>11</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>12</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                                <li>
                                                    <a href="#" aria-label="Previous">
                                                        <span aria-hidden="true"><i class="fa fa-caret-left"></i></span>
                                                    </a>
                                                </li>
                                                <li class="active"><a href="#">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#">5</a></li>
                                                <li>
                                                    <a href="#" aria-label="Next">
                                                        <span aria-hidden="true"><i class="fa fa-caret-right"></i></span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>

                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="profile">

                                        <div class="tableTenRecord">
                                            <table class="table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Date</th>
                                                        <th>Medication</th>
                                                        <th>QTY</th>
                                                        <th>Ref Rem</th>
                                                        <th>Last Action</th>
                                                        <th>Ins Code</th>
                                                        <th>Orig Copy</th>
                                                        <th>Handling Fee</th>
                                                        <th>Redeemed</th>
                                                        <th>Total State</th>
                                                        <th>Amt Collected</th>
                                                        <th>Next Refill</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                                <li>
                                                    <a href="#" aria-label="Previous">
                                                        <span aria-hidden="true"><i class="fa fa-caret-left"></i></span>
                                                    </a>
                                                </li>
                                                <li class="active"><a href="#">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#">5</a></li>
                                                <li>
                                                    <a href="#" aria-label="Next">
                                                        <span aria-hidden="true"><i class="fa fa-caret-right"></i></span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="messages">

                                        <div class="tableTenRecord">
                                            <table class="table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Date</th>
                                                        <th>Medication</th>
                                                        <th>QTY</th>
                                                        <th>Ref Rem</th>
                                                        <th>Last Action</th>
                                                        <th>Ins Code</th>
                                                        <th>Orig Copy</th>
                                                        <th>Handling Fee</th>
                                                        <th>Redeemed</th>
                                                        <th>Total State</th>
                                                        <th>Amt Collected</th>
                                                        <th>Next Refill</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                    <tr>
                                                        <td>12/12/2015</td>
                                                        <td>ATORVASTATIN 20mg (Tab)</td>
                                                        <td>30</td>
                                                        <td>2</td>
                                                        <td>TRANSFER IN</td>
                                                        <td>Com</td>
                                                        <td>$8.50</td>
                                                        <td>$2.75</td>
                                                        <td>300</td>
                                                        <td>$12.35</td>
                                                        <td>$5.25</td>
                                                        <td>01-07-2016</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                                <li>
                                                    <a href="#" aria-label="Previous">
                                                        <span aria-hidden="true"><i class="fa fa-caret-left"></i></span>
                                                    </a>
                                                </li>
                                                <li class="active"><a href="#">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#">5</a></li>
                                                <li>
                                                    <a href="#" aria-label="Next">
                                                        <span aria-hidden="true"><i class="fa fa-caret-right"></i></span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>

                            </div>
                            <div class="row textBox">
                                <div class="col-sm-12 messageForm">
                                    <form action="" class="">
                                        <textarea name="pharmacyNote" class="messageBox">Pharmacy Notes</textarea>
                                        <button type="submit"><i class="fa fa-paper-plane greenText"></i></button>
                                    </form>
                                    <a href="#" class="greenText messageHistory">Update</a>
                                </div>

                            </div>

                        </div>
                    </div>

                </div>



            </div>
        </div>
        <div id="dialog" style="height: auto; overflow: auto; background-color: white;display: none;"></div>
        <div id="dialogDeliveryAddress" style="height: 300px; overflow: auto; background-color: white;display: none;padding-left: 0px;padding-top: 25px !important;"></div>
        <div id="dialogAllergies" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
        <div id="dialogInsurance" style="height: auto; overflow: auto; background-color: white;display: none;padding-top: 15px !important;"></div>
    </form:form>
</div> <!-- /content -->
<jsp:include page="./inc/footer.jsp" />
</div> <!-- /wrapper -->

<!-- Modal Insurance Cards-->
<div id="cardsModal" class="imgModal modal fade" role="dialog" tabindex="">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Insurance Card (Front)</h4>
            </div>
            <div class="modal-body">
                <img src="${pageContext.request.contextPath}/resources/images/insuranceCard_15.jpg" alt="card front" />
                <i class="fa fa-chevron-right"></i>
                <i class="fa fa-chevron-left"></i>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Insurance Cards -->
<!-- Modal Dependents -->
<div id="dependentsModal" class="tableModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Dependent(s)</h4>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>DOB</th>
                                <th>Allergies</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 (9 Years)</td>
                                <td>Penicillin, Peanuts, Mango</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 (9 Years)</td>
                                <td>Penicillin, Peanuts, Mango</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 (9 Years)</td>
                                <td>Penicillin, Peanuts, Mango</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 (9 Years)</td>
                                <td>Penicillin, Peanuts, Mango</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 (9 Years)</td>
                                <td>Penicillin, Peanuts, Mango</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 (9 Years)</td>
                                <td>Penicillin, Peanuts, Mango</td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Dependents -->

<!-- Modal History -->
<div id="historyModal" class="tableModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Notification History</h4>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Sent By</th>
                                <th>Sent On</th>
                                <th>Message</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 13:22</td>
                                <td>Message will be here</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 13:22</td>
                                <td>Message will be here</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 13:22</td>
                                <td>Message will be here</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 13:22</td>
                                <td>Message will be here</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 13:22</td>
                                <td>Message will be here</td>
                            </tr>
                            <tr>
                                <td>Scott Smith (M)</td>
                                <td>12/23/2007 13:22</td>
                                <td>Message will be here</td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>
<!--/ Modal History -->

<!-- Modal Health Details -->
<div id="healthModal" class="listModal healthModal formModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Health Details</h4>
            </div>
            <div class="modal-body">
                <form action="" class="healthForm">
                    <div class="form-group">
                        <label for="heightFeet">Height:</label>
                        <input class="form-control" type="text" name="heightFeet" placeholder="Feet" />
                    </div>
                    <div class="form-group">
                        <label for="heightInches"></label>
                        <input type="text" class="form-control" name="heightInches" placeholder="Inch" />
                    </div>
                    <div class="form-group">
                        <label for="weight">Weight (lbs):</label>
                        <input class="form-control" type="text" name="weight" placeholder="lbs" />
                    </div>
                    <div class="form-group">
                        <label for="bmi">BMI (%):</label>
                        <input class="form-control" type="text" name="bmi" placeholder="%" />
                    </div>
                    <input class="blueSubmitButton" type="submit" value="Save" />

                </form>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Health Details -->

<!-- Modal Allergy -->
<div id="allergyModal"  tabindex='-1' class="listModal allergyModal formModal modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Allergies</h4>
            </div>
            <div class="modal-body">
                <form action="">
                    <div class="form-group">
                        <label for="allergies">Allergies:</label>
                        <input class="form-control" type="text" name="allergies" placeholder="Comma Seperated (Penicillin, Peanuts, Mango)" />
                    </div>
                    <input class="blueSubmitButton" type="submit" value="Save" />

                </form>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Allergy -->

<!-- Modal Shipping Details -->
<div id="shippingModal" class="listModal shippingModal formModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Shipping Address</h4>
            </div>
            <div class="modal-body">
                <form action="" class="shippingForm">
                    <div class="form-group">
                        <label for="addressOne">Address Line 1:</label>
                        <input class="form-control" type="text" name="addressOne" />
                    </div>
                    <div class="form-group">
                        <label for="addresstwo">Address Line 2:</label>
                        <input type="text" class="form-control" name="addressTwo" />
                    </div>
                    <div class="form-group">
                        <label for="apt">APT:</label>
                        <input class="form-control" type="text" name="apt" />
                    </div>
                    <div class="form-group">
                        <label for="city">City:</label>
                        <input class="form-control" type="text" name="city" />
                    </div>
                    <div class="form-group">
                        <label for="state">State:</label>
                        <!--                                <input class="form-control" type="text" name="state" />-->
                        <select name="state" id="stateSelect">
                            <option value="New York">New York</option>
                            <option value="Washington">Washington</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="zip">Zip:</label>
                        <input class="form-control" type="text" name="zip" />
                    </div>
                    <div class="form-group">
                        <label for="addressDescription">Address Description:</label>
                        <input class="form-control" type="text" name="addressDescription" />
                    </div>
                    <div class="form-group">
                        <label for="addressType">Address Type:</label>
                        <div>
                            <input type="radio" name="addressType" /> <span>Home</span>

                            <input type="radio" name="addressType" /> <span>Work</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isDefault"></label>
                        <input type="checkbox" name="isDefault" /> <span>Is Default</span>
                    </div>
                    <input class="blueSubmitButton" type="submit" value="Save" />

                </form>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Shipping Details -->

<!-- Modal Insurance edit -->
<div id="insEditModal" class="listModal shippingModal formModal modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Insurance Details</h4>
            </div>
            <div class="modal-body">
                <form action="" class="insEditForm">
                    <div class="clearfix">
                        <div class="form-group">
                            <label for="memId">Member ID:</label>
                            <input class="form-control" type="text" name="memId" />
                        </div>
                        <div class="form-group">
                            <label for="groupNum">Group Number:</label>
                            <input type="text" class="form-control" name="groupNum" />
                        </div>
                        <div class="form-group">
                            <label for="planId">Plan ID: </label>
                            <input class="form-control" type="text" name="planId" />
                        </div>
                        <div class="form-group">
                            <label for="insProv">Insurance Provider:</label>
                            <input class="form-control" type="text" name="insProv" />
                        </div>
                        <div class="form-group">
                            <label for="provAddr">Provider Address:</label>
                            <input class="form-control" type="text" name="provAddr" />
                        </div>
                        <div class="form-group">
                            <label for="providerPhone">Provider Phone:</label>
                            <input class="form-control" type="text" name="providerPhone" />
                        </div>
                        <div class="form-group">
                            <label for="expiryDate">Expiry Date:</label>
                            <input class="form-control" type="date" name="expiryDate" />
                        </div>
                    </div>
                    <input class="blueSubmitButton" type="submit" value="Save" />

                </form>
            </div>
        </div>

    </div>
</div>
<!--/ Modal Insurance edit -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/ssasoftDeliveryAddress.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInfo.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ssasoft/js/patientInfoInsurance.js"></script>
<script type="text/javascript">
                    $('#datetimepicker').datetimepicker({timepicker: false, format: 'm/d/Y',
                        onChangeDateTime: function (dp, $input) {
                            jQuery('#datetimepicker').datetimepicker('hide');
                        }});
</script>
<script type="text/javascript">
    function viewLargeImg(url, title) {
        var dialog = $("#dialog").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            height: 260,
            width: '24%',
            cache: false,
            autoResize: true,
            title: title,
            open: function (event, ui) {
                //hide titlebar.
                $(".ui-dialog-titlebar").css("font-size", "14px");
                $(".ui-dialog-titlebar").css("font-weight", "normal");
            },
            close: function ()
            {
                $(this).dialog('close');
                $(this).dialog('destroy');
            }
        });
        var html = '<img src="' + url + '" width="340" height="200">';
        $(dialog).html(html);
        $("#dialog").dialog("open");
    }
    function getPatientHealthRecord(patientProfileId, heightFeet, heightInch, weight, bmi) {
        var w;
        if (window.screen.width > 360) {
            w = 360;
        } else {
            w = $(window).width() * 0.9;
        }
        var dialog = $("#dialog").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: w,
            cache: false,
            title: "Patient Health",
            open: function (event, ui) {
                //hide titlebar.
                $(".ui-dialog-titlebar").css("font-size", "14px");
                $(".ui-dialog-titlebar").css("font-weight", "normal");
            },
            close: function ()
            {
                $(this).dialog('close');
                $(this).dialog('destroy');
                $(this).html("");
            }
        });
        var html = "<div style='margin-left:40px;'>";
        html += "<div class='col-sm-2'>";
        html += "<label >Height(Ft/</label>";
        html += "<div class='input-group'>";
        html += "<input id='feet' type ='text' class='form-control' value='" + heightFeet + "' onkeypress='return IsDigit(event)'>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col-sm-2' style='margin-left:7px;'>";
        html += "<label>Inch)</label>";
        html += "<div class='input-group'>";
        html += "<input id='inch' type ='text' class='form-control' value='" + heightInch + "' onkeypress='return IsDigit(event)'>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col-sm-3' >";
        html += "<label>Weight(Lbs)</label>";
        html += "<div class='input-group'>";
        html += "<input id='weight' type ='text' class='form-control' value='" + weight + "' onkeypress='return IsDigit(event)'>";
        html += "</div>";
        html += "</div>";
        html += "<div class='col-sm-3' style='margin-left:7px;'>";
        html += "<label >BMI(%)</label>";
        html += "<div class='input-group'>";
        html += "<input id='bmi' type ='text' class='form-control' value='" + bmi + "' onkeypress='return isFloatNumber(this,event)' maxlength='4'>";
        html += "</div>";
        html += "</div>";
        html += "</div>";
        html += "<div style=padding-top:5px;>";
        html += "<div style='float: left; padding-top:10px;margin-left:87px;'>";
        html += "<button  class = 'btndrop btn-margin' type='button' class = 'btn_Color' style='color:#FFFFFF; onclick='hideModel();' >Cancel</button></a>";
        html += "</div >";
        html += "<div style='float:right;margin-top:10px;margin-right:88px;'>";
        html += "<button  class = 'btndrop btn-margin' type='button' style='color:#FFFFFF;' onclick='savePatientHealth(" + patientProfileId + ")' class = 'btn_Color'> Save</button></a>";
        html += "</div>";
        html += "</div>";
        $(dialog).html(html);
        $(dialog).dialog("open");
    }
    function uploadRecord(patientProfileId, msg) {
        if (patientProfileId > 0) {
            $.ajax({
                url: "${pageContext.request.contextPath}/patient/getPatientProfileHealth/" + patientProfileId,
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        $("#profileHealthsId").val(data.id);
                        if (msg == "Success") {
                            setPatientHealthRecord(data);
                        } else {
                            getPatientHealthRecord(patientProfileId, data.heightFeet, data.heightInch, data.weight, data.bmi);
                        }
                    } else {
                        getPatientHealthRecord(patientProfileId, 0, 0, 0, '0.00');
                    }
                }
            });
        }
    }
    function savePatientHealth(patientProfileId) {
        var id;
        if ($("#profileHealthsId").val() != "") {
            id = $("#profileHealthsId").val();
        } else {
            id = null;
        }

        //var json = {"patientProfileId": patientProfileId,"height": $("#feet").val(), "inch": $("#inch").val(), "weigth": $("#weight").val(), "BMI": $("#bmi").val()};
        var json = {"patientHealthId": id, "patientProfileId": patientProfileId, "height": $("#feet").val(), "inch": $("#inch").val(), "weigth": $("#weight").val(), "bmi": $("#bmi").val()};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/savePatientProfileHealth",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            success: function (data) {
                var response = eval(data);
                var statusCode = response.statuscode;
                if (statusCode == 200) {
                    window.location.href = "/patient/detail/" + $("#patientId").val();
                }

            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }
    function setPatientHealthRecord(data) {
        if (data.heightFeet != null) {
            $("#heightFeet").val(data.heightFeet);
        } else {
            $("#heightFeet").val(0);
        }
        if (data.heightInch != null) {
            $("#Inch").val(data.heightInch);
        } else {
            $("#Inch").val(0);
        }
        if (data.weight != null) {
            $("#Weight").val(data.weight);
        } else {
            $("#Weight").val(0);
        }
        if (data.bmi != null) {
            $("#BMI").val(data.bmi);
        } else {
            $("#BMI").val('0.00');
        }
    }
    function showPatientRecordDetail(patientId, type) {
        var json = {"id": patientId};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/getPatientShippingAddress",
            type: "POST",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (type == "saved") {
                    $("#patientAddressId").val(data.id);
                    var shipAddress = data.address + " " + data.apartment + " " + data.city + " " + data.zip;
                    //$("#shippingAddress").text(shipAddress);
                } else {
                    showDialog(data, type, patientId);
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }

    function showDialog(data, type, patientId) {
        var dialog = $("#dialog").dialog({
            closeOnEscape: false,
            autoOpen: false,
            modal: true,
            width: "30%",
            cache: false,
            title: type,
            open: function (event, ui) {
                //hide titlebar.
                $(".ui-dialog-titlebar").css("font-size", "14px");
                $(".ui-dialog-titlebar").css("font-weight", "normal");
            },
            close: function ()
            {
                $(this).dialog('close');
                $(this).dialog('destroy');
                $(this).html("");
            }
        });
        var html, address = '', city = '', stateId = 0, zip = '', apartment = '';
        if (data != null) {
            if (data.id != null) {
                $("#patientAddressId").val(data.id);
            }
            if (data.address != null) {
                address = data.address;
            }
            if (data.city != null) {
                city = data.city;
            }
            if (data.zip != null) {
                zip = data.zip;
            }
            if (data.stateId != null) {
                stateId = data.stateId;
            }
            if (data.apartment != null) {
                apartment = data.apartment;
            }
        }
        html = "<div class='col-sm-2'>";
        html += "<label>Address:</label>";
        html += "<input id='address' type='text' class='form-control' value='" + address + "'/>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<label>Apartment:</label>";
        html += "<input id='apartment' type='text' class='form-control' value='" + apartment + "'/>";
        html += "</div>";
        html += "<div class='col-sm-2'>";
        html += "<label>City:</label>";
        html += "<input id='city' type='text' class='form-control' value='" + city + "'/>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<label>State:</label>";
        html += "<select id='state' class='form-control selectpicker show-tick' style='padding-top:3px;'>";
        html += "<option value='0'>Select One</option>";
        html += "</select>";
        html += "</div>";
        html += "<div class='col-sm-2'>";
        html += "<label>Zip:</label>";
        html += "<input id='zip' type='text' class='form-control' value='" + zip + "'/>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='hideModel();' style='color:#2071b6; padding-top: 1px;'>Cancel</button>";
        html += "</div>";
        html += "<div class='col-sm-3'>";
        html += "<button  class = 'btndrop btn-margin btn_Color' type='button' onclick='updatePatientRecord(" + patientId + ");' style='color:#2071b6; padding-top: 1px;'>Save</button>";
        html += "</div>";
        getStatesWs(stateId);
        $(dialog).html(html);
        $(dialog).dialog("open");
    }
    function updatePatientRecord(patientId) {
        var addressId = null;
        if ($("#patientAddressId").val() != "") {
            addressId = $("#patientAddressId").val();
        }
        var json = {"id": addressId, "address": $("#address").val(), "city": $("#city").val(), "apartment": $("#apartment").val(), "stateId": $("#state").val(), "zip": $("#zip").val(), "profileId": patientId};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/savePatientShippingAddress",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            success: function (data) {
                if (data == true) {
                    showPatientRecordDetail(patientId, "saved");
                    hideModel();
                }
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }
    function getStatesWs(stateId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/getStatesWs",
            type: "POST",
            dataType: 'json',
            success: function (data) {
                $('#state')
                        .find('option')
                        .remove()
                        .end()
                        .append('<option value="0">Select One</option>')
                        .val('Select One');
                $.each(data.data, function (index, element) {
                    $('#state').append($('<option>', {value: element.id, text: element.name}));
                });

                if (stateId > 0) {
                    $('#state').val(stateId).attr("selected", "selected");
                    $("#state").trigger("change");
                }
//                $('#state').selectpicker('refresh');
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }
    $(document).ready(function () {
        window.patientInfo.ProgramActivityTable();
        window.patientInfo.PointsBalanceTable();
        
        window.patientInfo.populateAppUrl('${pageContext.request.contextPath}');
        window.patientInfoInsurance.populateAppUrl('${pageContext.request.contextPath}');
        window.ssaSoftDeliveryAddress.populateAppUrl('${pageContext.request.contextPath}');
        
        $("#pointsBalanceTable").hide();
        $("#programActivitiesTable").hide();
    });
    function displayTab(id) {
        if (id == 1) {
            $("#madicat").attr("class", "activetab");
            $("#pointsB").attr("class", "inactivetab");
            $("#pa").attr("class", "inactivetab");
            $("#medicationsTable").show();
            $("#pointsBalanceTable").hide();
            $("#programActivitiesTable").hide();
        } else if (id == 2) {
            $("#pointsB").attr("class", "activetab");
            $("#madicat").attr("class", "inactivetab");
            $("#pa").attr("class", "inactivetab");

            $("#pointsBalanceTable").show();
            $("#programActivitiesTable").hide();
            $("#medicationsTable").hide();

        } else if (id == 3) {
            $("#pa").attr("class", "activetab");
            $("#pointsB").attr("class", "inactivetab");
            $("#madicat").attr("class", "inactivetab");

            $("#programActivitiesTable").show();
            $("#pointsBalanceTable").hide();
            $("#medicationsTable").hide();
        }
    }



    $("#pharmacyNotes").keypress(function (e) {

        if (e.keyCode == 13 && !e.shiftKey)
        {
//            console.log("Taking the string away");
            var patientId = "${patientProfile.id}";
            var textval = $("#pharmacyNotes").val();
            var dispval = document.getElementById("displayNotes").innerHTML;
            document.getElementById("displayNotes").innerHTML = dispval + '<br>' + textval;

            if (textval == "") {
                return;
            }
            var json = {"ptProfileId": patientId, "notes": textval};

            e.preventDefault();
            //now call the code to submit your form
            $.ajax({
                url: "${pageContext.request.contextPath}/patient/savePatientNotes",
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(json),
                success: function (data) {
                    var response = eval(data);
                    $("#pharmacyNotes").val("");
                },
                error: function (e) {
                    alert('Error while request...' + e);
                }
            });
        }
    });

    function previousUser() {
        var newUser = ${patientProfile.id} - 1;
        var json = {"id": newUser};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/checkPatientIdExists",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data) {
                    window.location = window.location.href = "/patient/detail/" + newUser;
                }
                var response = eval(data);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }

    function nextUser() {
        var newUser = ${patientProfile.id} + 1;
        var json = {"id": newUser};
        $.ajax({
            url: "${pageContext.request.contextPath}/patient/checkPatientIdExists",
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: function (data) {
                if (data) {
                    window.location = window.location.href = "/patient/detail/" + newUser;
                }
                var response = eval(data);
            },
            error: function (e) {
                alert('Error while request...' + e);
            }
        });
    }

</script>
<script>
    window.onload = function () {
        var backImg = document.querySelector('.insuranceContainer > div > img:last-child');
        var frontImg = document.querySelector('.insuranceContainer > div > img:first-child');
        var backImgSrc = backImg.src;
        var frontImgSrc = frontImg.src;
        var modalImg = document.querySelector('.imgModal img');
        var modalTitle = document.querySelector('.imgModal .modal-title');
        var leftNav = document.querySelector('.imgModal .fa-chevron-left');
        var rightNav = document.querySelector('.imgModal .fa-chevron-right');

        function backSide() {
            modalImg.src = backImgSrc;
            modalTitle.textContent = "Insurance Card (Back)";
            leftNav.style.display = "block";
            rightNav.style.display = "none";
        }

        function frontSide() {
            modalImg.src = frontImgSrc;
            modalTitle.textContent = "Insurance Card (Front)";
            rightNav.style.display = "block";
            leftNav.style.display = "none";
        }
        backImg.onclick = backSide;
        rightNav.onclick = backSide;
        frontImg.onclick = frontSide;
        leftNav.onclick = frontSide;
    }
</script>
</body>
</html>
