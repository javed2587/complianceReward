<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page='./inc/head.jsp'/>

    <body onload="markCheckBoxes()">
        <!---------------------------------NEW DESIGN-------------------------------------------------->
        <style>
            .modal{
                top: 0 !important;
                position: fixed;
                z-index: 1050;
                left: unset;
                bottom: unset;
                right: unset;
            }
        </style>
        <div class="container-fluid fixed_wrap">	
            <jsp:include page='./inc/header.jsp'/>
            <form:form action="${pageContext.request.contextPath}/ConsumerPortal/addConsumer" id="target" commandName="pharmacy" role="form" method="Post" onsubmit="return ValidateForm()">
                <!--Registration Page-->
                <div class="row registrationContainer">
                    <div class="wrapper">

                        <form:hidden path="pharmacyLookup.id"/>
                        <form:hidden path="pharmacyLookup.title"/>
                        <form:hidden path="pharmacyLookup.address"/>
                        <form:hidden path="pharmacyLookup.city"/>
                        <form:hidden path="pharmacyLookup.state.id"/>
                        <form:hidden path="pharmacyLookup.state.abbr"/>
                        <form:hidden path="pharmacyLookup.zip"/>
                        <form:hidden path="pharmacyLookup.npi"/>
                        <form:hidden path="pharmacyLookup.passNumber"/>
                        <form:hidden path="pharmacyLookup.pharmacyType.title"/>
                        <form:hidden path="pharmacyLookup.salesRep"/>
                        <input id="IsValidHours" value="0" type="hidden"/>

                        <div class="col-lg-2 registration_menu clearfix">
                            <ul>
                                <li class="active"><a href="#">Pharmacy Portal</a></li>
                                <li><a href="#">Product Catalog</a></li>
                                <li><a href="#">Drug Information</a></li>
                                <li><a href="#">Pharmacy Locator</a></li>
                                <li><a href="#">Customer Service</a></li>
                                <li><a href="#">Forms</a></li>
                            </ul>
                        </div>
                        <div class="col-lg-10 registration_layout">
                            <div class="col-sm-12 r_p_head clearfix">
                                <div class="row">
                                    <p>Once prescriptions arrive to your work queue in the Rx-Direct Portal. You Will be notified if a prescription filling opportunity is <span>idle for longer than 20 minutes during work hours.</span></p>
                                </div>
                            </div>



                            <div class="row clearfix reg_colum">
                                <div class="col-lg-6">
                                    <div class="regi_col">
                                        <h4>Pharmacist in Charge at Facility</h4>

                                        <div class="row pharm_sec1 float-label"> 
                                            <div class="col-sm-6 control first_field">
                                                <!--                                        <input type="text" name="f-name" value="" placeholder="First Name" class="form-control field_1" />-->
                                                <form:input id="inchargeFirstName" path="pharmacyUserList[0].firstName" class="form-control field_1 fieldfloat" autofocus="autofocus" maxlength="50" placeholder="First Name" required="true" />
                                                <label for="inchargeFirstName">First Name</label>
                                                <form:errors path="pharmacyUserList[0].firstName" cssClass="errorMessageValid" />
                                            </div>
                                            <div class="col-sm-6 control secont_field">
                                                <!--                                        <input type="text" name="l-name" value="" placeholder="Last Name" class="form-control field_1" />-->
                                                <form:input id="inchargeLastName" path="pharmacyUserList[0].lastName" class="form-control field_1" placeholder="Last Name" maxlength="50" required="true"  />
                                                <label for="inchargeLastName">Last Name</label>
                                                <form:errors path="pharmacyUserList[0].lastName" cssClass="errorMessageValid" />

                                            </div>


                                            <div class="col-sm-6 control first_field">
                                                <!--  <label>Email Address</label> -->
                                                <!--                                        <input type="text" name="f-name" value="" placeholder="Email Address" class="form-control field_1" />-->
                                                <form:input id="inchargeEmail" path="pharmacyUserList[0].email" class="form-control field_1 email" placeholder="Email Address" maxlength="100" required="true"  />
                                                <label for="inchargeEmail">Email Address</label>
                                                <form:errors path="pharmacyUserList[0].email" cssClass="errorMessageValid" />
                                            </div>
                                            <div class="col-sm-6 control secont_field">
                                                <!--  <label>Notification Phone #</label> -->
                                                <!--                                        <input type="text" name="l-name" value="" placeholder="Mobile Phone" class="form-control field_1" />-->
                                                <form:input id="inchargePhone" path="pharmacyUserList[0].phone" class="form-control field_1" placeholder="Notification Phone" maxlength="10" onkeypress="return allowOnlyNumber(event)" required="true"  />
                                                <label for="inchargePhone">Notification Phone</label>
                                                <form:errors path="pharmacyUserList[0].phone" cssClass="errorMessageValid" />
                                                <form:hidden path="pharmacyUserList[0].role" value="admin"/>
                                            </div>
                                            <!--  <div class="col-sm-12 secont_field">
                                                                                 <i class="aye">Auto generated password will be sent at your given email.</i>
                                                                                  </div> -->

                                            <%--  <div class="col-sm-6 first_field">
                                                 <label>Choose Password </label>
         <!--                                        <input type="text" name="f-name" value="" placeholder="Enter Password" class="form-control field_1" />-->
                                                 <form:input id="choosePassword" path="pharmacyUserList[0].password" class="form-control field_1" placeholder="Enter Password"/>
                                                 <form:errors path="pharmacyUserList[0].password" cssClass="errorMessageValid" />
                                                 <i>(4-10 characters; Alpha Numeric)</i>
                                             </div> --%>
                                            <!--  <div class="col-sm-6 secont_field">
                                                 <label>Repeat Password</label>
                                                 <input type="text" name="l-name" value="" placeholder="Enter Password" class="form-control field_1" />
                                             </div> -->
                                        </div>


                                        <div class="row">
                                            <div class="col-sm-6 col-xs-6 first_field radio_style check_style">
                                                <label>Facility Operation </label>
                                                <ul>
                                                    <li>
                                                        <!--                                                <input type="checkbox" id="monday" name="monday" checked>-->
                                                        <form:checkbox id="monday" path="pharmacyFacilityOperationList[0].isSelected" value="Monday" onclick="isFacilityOperation('monday','mondayStartTimingId','mondayEndTimingId')" cssClass="days"/>
                                                        <form:hidden path="pharmacyFacilityOperationList[0].day" value="Monday"/>
                                                        <label for="monday">Monday</label>
                                                        <div class="check"></div>
                                                    </li>
                                                    <li>
                                                        <!--                                                <input type="checkbox" id="tuesday" name="tuesday" checked>-->
                                                        <form:checkbox id="tuesday" path="pharmacyFacilityOperationList[1].isSelected" value="Tuesday" onclick="isFacilityOperation('tuesday','tuesdayStartTimingId','tuesdayEndTimingId')" cssClass="days"/>
                                                        <form:hidden path="pharmacyFacilityOperationList[1].day" value="Tuesday"/>
                                                        <label for="tuesday">Tuesday</label>
                                                        <div class="check"></div>
                                                    </li>
                                                    <li>
                                                        <!--                                                <input type="checkbox" id="wednesday" name="wednesday">-->
                                                        <form:checkbox id="wednesday" path="pharmacyFacilityOperationList[2].isSelected" value="Wednesday" onclick="isFacilityOperation('wednesday','wednesdayStartTimingId','wednesdayEndTimingId')" cssClass="days"/>
                                                        <form:hidden path="pharmacyFacilityOperationList[2].day" value="Wednesday"/>
                                                        <label for="wednesday">Wednesday</label>
                                                        <div class="check"></div>
                                                    </li>
                                                    <li>
                                                        <!--                                                <input type="checkbox" id="thursday" name="thursday">-->
                                                        <form:checkbox id="thursday" path="pharmacyFacilityOperationList[3].isSelected" value="Thursday" onclick="isFacilityOperation('thursday','thursdayStartTimingId','thursdayEndTimingId')" cssClass="days"/>
                                                        <form:hidden path="pharmacyFacilityOperationList[3].day" value="Thursday"/>
                                                        <label for="thursday">Thursday</label>
                                                        <div class="check"></div>
                                                    </li>
                                                    <li>
                                                        <!--                                                <input type="checkbox" id="friday" name="friday">-->
                                                        <form:checkbox id="friday" path="pharmacyFacilityOperationList[4].isSelected" value="Friday" onclick="isFacilityOperation('friday','fridayStartTimingId','fridayEndTimingId')" cssClass="days"/>
                                                        <form:hidden path="pharmacyFacilityOperationList[4].day" value="Friday"/>
                                                        <label for="friday">Friday</label>
                                                        <div class="check"></div>
                                                    </li>
                                                    <li>
                                                        <!--                                                <input type="checkbox" id="saturday" name="saturday">-->
                                                        <form:checkbox id="saturday" path="pharmacyFacilityOperationList[5].isSelected" value="Saturday" onclick="isFacilityOperation('saturday','saturdayStartTimingId','saturdayEndTimingId')" cssClass="days"/>
                                                        <form:hidden path="pharmacyFacilityOperationList[5].day" value="Saturday"/>
                                                        <label for="saturday">Saturday</label>
                                                        <div class="check"></div>
                                                    </li>
                                                    <li>
                                                        <!--                                                <input type="checkbox" id="sunday" name="sunday">-->
                                                        <form:checkbox id="sunday" path="pharmacyFacilityOperationList[6].isSelected" value="Sunday" onclick="isFacilityOperation('sunday','sundayStartTimingId','sundayEndTimingId')" cssClass="days"/>
                                                        <form:hidden path="pharmacyFacilityOperationList[6].day" value="Sunday"/>
                                                        <label for="sunday">Sunday</label>
                                                        <div class="check"></div>
                                                    </li>
                                                    <!--                                                    
                                                                                                        <li>
                                                                                                            <input id="allSameTime" type="checkbox" onclick="sameTime('allSameTime')" disabled="disabled" style="float: left;"/>
                                                                                                            <label for="allSameTime" style="color: red; font-size: 11px;">Apply this schedule for all days of week.</label>
                                                                                                        </li>
                                                    -->
                                                </ul>
                                            </div>
                                            <div class="col-sm-6 col-xs-6 secont_field" style="width: 48% !important;">
                                                <label>Phone Hours</label> 
                                                <div class="schedule_list">
                                                    <!-- Monday -->
                                                    <div id="time1Id" class="timeBlock" >
                                                        <form:input id="mondayStartTimingId" path="pharmacyFacilityOperationList[0].phoneHoursFrom" cssClass="timing phoneHoursFrom"   placeholder="08:00AM" value="08:00AM" onchange="populateSameHours('mondayStartTimingId','mondayEndTimingId','phoneHoursTo');isEqualHours('mondayStartTimingId','mondayEndTimingId');"/> <!-- <label>AM</label> --> 
                                                    </div>
                                                    <div class="timeBlock">
                                                        <form:input id="mondayEndTimingId" path="pharmacyFacilityOperationList[0].phoneHoursTo" cssClass="timing phoneHoursTo"   placeholder="18:00PM" onchange="isEnableAllPhoneHours(this.value);populateSameHours('mondayStartTimingId','mondayEndTimingId','phoneHoursTo');isEqualHours('mondayStartTimingId','mondayEndTimingId');" value="18:00PM"/><!-- <label>PM</label> -->
                                                    </div>

                                                    <!-- Tuesday -->
                                                    <div class="timeBlock">
                                                        <form:input id="tuesdayStartTimingId" path="pharmacyFacilityOperationList[1].phoneHoursFrom" cssClass="timing phoneHoursFrom"  placeholder="08:00AM" value="08:00AM" onchange="isEqualHours('tuesdayStartTimingId','tuesdayEndTimingId');"/><!-- <label>AM</label> -->
                                                    </div>
                                                    <div class="timeBlock">
                                                        <form:input id="tuesdayEndTimingId" path="pharmacyFacilityOperationList[1].phoneHoursTo" cssClass="timing phoneHoursTo"  placeholder="18:00PM" value="18:00PM" onchange="isEnableAllPhoneHours(this.value);isEqualHours('tuesdayStartTimingId','tuesdayEndTimingId');"/><!-- <label>PM</label> -->

                                                    </div>

                                                    <!-- Wednesday -->
                                                    <div class="timeBlock">
                                                        <form:input id="wednesdayStartTimingId" path="pharmacyFacilityOperationList[2].phoneHoursFrom" cssClass="timing phoneHoursFrom"  placeholder="08:00AM" value="08:00AM" onchange="isEqualHours('wednesdayStartTimingId','wednesdayEndTimingId');"/><!-- <label>AM</label> -->
                                                    </div>
                                                    <div class="timeBlock">
                                                        <form:input id="wednesdayEndTimingId" path="pharmacyFacilityOperationList[2].phoneHoursTo" cssClass="timing phoneHoursTo"  placeholder="18:00PM" onchange="isEnableAllPhoneHours(this.value);isEqualHours('wednesdayStartTimingId','wednesdayEndTimingId');" value="18:00PM"/><!-- <label>PM</label> -->

                                                    </div>
                                                    <!-- Thursday -->
                                                    <div class="timeBlock">
                                                        <form:input id="thursdayStartTimingId" path="pharmacyFacilityOperationList[3].phoneHoursFrom" cssClass="timing phoneHoursFrom"  placeholder="08:00AM" value="08:00AM" onchange="isEqualHours('thursdayStartTimingId','thursdayEndTimingId');"/><!-- <label>AM</label> -->
                                                    </div>
                                                    <div class="timeBlock">
                                                        <form:input id="thursdayEndTimingId" path="pharmacyFacilityOperationList[3].phoneHoursTo" cssClass="timing phoneHoursTo"  placeholder="18:00PM" value="18:00PM" onchange="isEnableAllPhoneHours(this.value);isEqualHours('thursdayStartTimingId','thursdayEndTimingId');"/><!-- <label>PM</label> -->

                                                    </div>

                                                    <!-- Friday -->    
                                                    <div class="timeBlock">
                                                        <form:input id="fridayStartTimingId" path="pharmacyFacilityOperationList[4].phoneHoursFrom" cssClass="timing phoneHoursFrom"  placeholder="08:00AM" value="08:00AM" onchange="isEqualHours('fridayEndTimingId','fridayEndTimingId');"/><!-- <label>AM</label> -->
                                                    </div>
                                                    <div class="timeBlock">
                                                        <form:input id="fridayEndTimingId" path="pharmacyFacilityOperationList[4].phoneHoursTo" cssClass="timing phoneHoursTo"  placeholder="18:00PM" onchange="isEnableAllPhoneHours(this.value);isEqualHours('fridayEndTimingId','fridayEndTimingId');" value="18:00PM"/><!-- <label>PM</label> -->

                                                    </div>

                                                    <!-- Saturday -->    
                                                    <div class="timeBlock">
                                                        <form:input id="saturdayStartTimingId" path="pharmacyFacilityOperationList[5].phoneHoursFrom" cssClass="timing phoneHoursFrom"   placeholder="08:00AM" onchange="isEqualHours('saturdayStartTimingId','saturdayEndTimingId');"/><!-- <label>AM</label> -->
                                                    </div>
                                                    <div class="timeBlock">
                                                        <form:input id="saturdayEndTimingId" path="pharmacyFacilityOperationList[5].phoneHoursTo" cssClass="timing phoneHoursTo"   placeholder="18:00PM" onchange="isEnableAllPhoneHours(this.value);isEqualHours('saturdayStartTimingId','saturdayEndTimingId');"/><!-- <label>PM</label> -->

                                                    </div>

                                                    <!-- Sunday -->    
                                                    <div class="timeBlock">
                                                        <form:input id="sundayStartTimingId" path="pharmacyFacilityOperationList[6].phoneHoursFrom" cssClass="timing phoneHoursFrom"   placeholder="08:00AM" onchange="isEqualHours('sundayStartTimingId','sundayEndTimingId');"/><!-- <label>AM</label> -->
                                                    </div>
                                                    <div class="timeBlock">
                                                        <form:input id="sundayEndTimingId" path="pharmacyFacilityOperationList[6].phoneHoursTo" cssClass="timing phoneHoursTo"   placeholder="18:00PM" onchange="isEnableAllPhoneHours(this.value);isEqualHours('sundayStartTimingId','sundayEndTimingId');"/><!-- <label>PM</label> -->
                                                    </div>

                                                </div>

                                            </div>
                                            <div class="col-sm-12">
                                                <!--    <form action="">
                                                        <input id="allSameTime" type="checkbox" onclick="sameTime('allSameTime')" disabled="disabled" style="float: left;"/>
                                                        <label for="allSameTime" style="color: red; font-size: 11px;padding-left:31px;">Apply this schedule for all days of week.</label>
                                                    </form>-->
                                            </div>           
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 rightRegCol">
                                    <h5>Passwords will be automatically generated & sent to all Contacts</h5>
                                    <h4>Secondary Contacts</h4>
                                    <div class="regi_col">


                                        <div class="row">

                                            <div class="col-sm-6 first_field">
                                                <form:checkbox name="" id="secondaryContact" path="addSecondaryContacts" onclick="isContactsEnable('secondaryContact',2)"/>
                                                <label>Pharmacy Contact #2 </label>
                                                <div class="float-label">
                                                    <div class="control secondary_float"><form:input type="text" name="firstName" value="" path="pharmacyUserList[1].firstName" id="fName2" placeholder="First Name" class="form-control field_1"  maxlength="50" required="true" />
                                                        <label for="fName2">First Name</label>
                                                        <form:errors path="pharmacyUserList[1].firstName" cssClass="errorMessageValid" /></div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 secont_field">
                                                <label></label>
                                                <div class="float-label"><div class="control secondary_float"><form:input type="text" name="lastName" value="" path="pharmacyUserList[1].lastName"  id="lName2" placeholder="Last Name" class="form-control field_1" maxlength="50" required="true" /><label for="lName2">Last Name</label>
                                                        <form:errors path="pharmacyUserList[1].lastName" cssClass="errorMessageValid" /></div></div>
                                            </div>


                                            <div class="float-label"><div class="col-sm-6 control first_field">
                                                    <!--  <label>Pharmacy Contact #2 Email </label> -->
                                                    <form:input type="text" name="mail" value="" id="email2" path="pharmacyUserList[1].email" placeholder="Email Address" maxlength="100" class="form-control field_1" required="true" />
                                                    <label for="email2">Email Address</label>
                                                    <form:errors path="pharmacyUserList[1].email" cssClass="errorMessageValid" />
                                                </div>
                                                <div class="col-sm-6 control secont_field">
                                                    <!--  <label>Notification Phone #</label> -->
                                                    <form:input type="text" name="phone" value="" id="phone2" placeholder="Mobile Phone" maxlength="10" onkeypress="return allowOnlyNumber(event)" path="pharmacyUserList[1].phone" class="form-control field_1" required="true" />
                                                    <label for="phone2">Mobile Phone</label>
                                                    <form:errors path="pharmacyUserList[1].phone" cssClass="errorMessageValid" />
                                                </div>
                                                <form:hidden path="pharmacyUserList[1].role" value="user"/></div>
                                        </div>
                                    </div>
                                    <div class="regi_col">
                                        <div class="row">


                                            <div class="col-sm-6 first_field">
                                                <form:checkbox name=""  id="thirdContact" path="addThirdContacts" onclick="isContactsEnable('thirdContact',3)"/>
                                                <label>Pharmacy Contact #3  </label>
                                                <div class="float-label">
                                                    <div class="control secondary_float">
                                                        <form:input type="text" name="f-Name" value="" path="pharmacyUserList[2].firstName" placeholder="First Name" id="fName3" maxlength="50" class="form-control field_1" required="true"  />
                                                        <label for="fName3">First Name</label>
                                                        <form:errors path="pharmacyUserList[2].firstName" cssClass="errorMessageValid" /></div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 secont_field">
                                                <label></label>
                                                <div class="float-label">
                                                    <div class="control secondary_float"><form:input type="text" name="lastName" value="" path="pharmacyUserList[2].lastName" placeholder="Last Name" id="lName3" maxlength="50" class="form-control field_1" required="true"  />
                                                        <label for="lName3">Last Name</label>
                                                        <form:errors path="pharmacyUserList[2].lastName" cssClass="errorMessageValid" /></div>
                                                </div>
                                            </div>

                                            <div class="float-label">
                                                <div class="col-sm-6 control first_field">
                                                    <!--  <label>Pharmacy Contact #3 Email </label> -->
                                                    <form:input type="text" name="email" value="" path="pharmacyUserList[2].email" placeholder="Email Address" maxlength="100" id="email3" class="form-control field_1" required="true"  />
                                                    <label for="email3">Email Address</label>
                                                    <form:errors path="pharmacyUserList[2].email" cssClass="errorMessageValid" />
                                                </div>
                                                <div class="col-sm-6 control secont_field">
                                                    <!-- <label>Notification Phone #</label> -->
                                                    <form:input type="text" name="phone" value="" path="pharmacyUserList[2].phone" maxlength="10" onkeypress="return allowOnlyNumber(event)" placeholder="Mobile Phone" id="phone3" class="form-control field_1" required="true"  />
                                                    <label for="phone3">Mobile Phone</label>
                                                    <form:errors path="pharmacyUserList[2].phone" cssClass="errorMessageValid" />
                                                </div>

                                                <form:hidden path="pharmacyUserList[2].role" value="user"/></div> 
                                        </div>


                                    </div>


                                </div>
                                <div class="col-sm-12">
                                    <div class=" regi_cofi">
                                        <p>
                                            <form:checkbox name="" id="acceptBtn" value="1" path="acceptedTerms" />I accept the <a href="#">Terms of Use</a></p>
                                    </div>
                                    <div class=" regi_cofi">
                                        <p>
                                            <form:checkbox name="" id="privacyPolicy" value="1" path="acceptedPolicy" />I authorize real time advisories as prescription claims for the this pharmacy location linked to either my email or mobile number
                                            <a href="#">Privacy Policy</a>
                                        </p>
                                    </div>

                                    <div class="">
                                        <input type="submit" name="create" value="Create" id="save" class="btn regi_btn" />
                                        <input type="button" name="cancel" value="Cancel" class="btn regi_btn" id="cancel" formnovalidate onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/login';"  />
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
                <!--Registration Page-->
            </form:form>

            <!--Footer-->
            <jsp:include page='./inc/footer.jsp'/>
            <!--        <div class="row footer">
                        <div class="wrapper">
                            <div class="col-sm-12">
                                <div class="row">
                                    <div class="col-lg-4 col-md-6">
                                        <p>3201 FANNIN LN  l  Suite 200  l  SOUTHLAKE, TX 76092</p>
                                    </div>
                                    <div class="col-lg-4 col-md-6 footer_contact">
                                        <ul>
                                            <li><i class="fa fa-phone"></i>(888) 495-7271</li>
                                            <li><a href="mailto:info@Rx-Direct.us"><i class="fa fa-envelope-o"></i>info@Rx-Direct.us</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-lg-4 col-md-12 footer_privcy">
                                        <p>© 2016 <strong>Rx-Direct.</strong> All rights reserved.<a href="#">Privacy Policy</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->
            <!--/Footer-->

        </div>
        <!-- Modal Login -->
        <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="vertical-alignment-helper">
                <div class="modal-dialog vertical-align-center" role="document">
                    <div class="modal-content login_model clearfix">
                        <button type="button" class="close model_close" data-dismiss="modal" aria-label="Close"></button>
                        <div class="col-sm-12">
                            <div class="col-md-6 col-sm-12 login_col clearfix">
                                <h2>Pharmacy Login</h2>
                                <input type="email" name="email" value="" placeholder="Email" class="form-control field_1" />
                                <input type="password" name="password" value="" placeholder="Password" class="form-control field_1" />
                                <input type="submit" name="login" class="btn field_btn pull-left" value="Login" />
                                <a href="#" class="forget_pass pull-right">Forgot user name or password?</a> </div>
                            <div class="col-md-6 col-sm-12 register_col">
                                <h2>Register New Pharmacy?</h2>
                                <p>Your pharmacy not register yet?</p>
                                <input type="submit" name="register_btn" class="btn field_btn" data-dismiss="modal" aria-label="Close" data-toggle="modal" data-target="#register" value="Register Now" />
                            </div>
                            <div class="col-sm-12 login_footer">
                                <p>Questions about the Rx-Direct Web Access : Internet help desk: 888-xxx-xxxx 5am ? 8pm EST 7 days a week</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ Modal Login -->

        <!-- Modal Register -->
        <div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="vertical-alignment-helper">
                <div class="modal-dialog vertical-align-center registrationDiv" role="document">
                    <div class="modal-content regitsre_model login_model clearfix">
                        <div class="col-sm-12 regiter_title">
                            <h2>Register Pharmacy </h2>
                        </div>
                        <button type="button" class="close model_close" data-dismiss="modal" aria-label="Close"></button>
                        <div class="col-sm-12">
                            <div class="col-md-6 col-sm-12 licence_col clearfix">
                                <label>Pharmacy NPI <span>(as it appears on your license)</span></label>
                                <input type="email" name="email" value="" placeholder="Email" class="form-control field_1" />
                                <label>Site Pass Number</label>
                                <input type="password" name="password" value="" placeholder="Password" class="form-control field_1" />
                                <input type="submit" name="next" class="btn field_btn" value="Next" />
                            </div>
                            <div class="col-md-6 col-sm-12 type_col">
                                <h5>Pharmacy Type </h5>
                                <ul>
                                    <li>
                                        <input type="radio" id="a-option" name="selector">
                                        <label for="a-option">Retail</label>
                                        <div class="check"></div>
                                    </li>
                                    <li>
                                        <input type="radio" id="b-option" name="selector">
                                        <label for="b-option">Compounding Specialty</label>
                                        <div class="check">
                                            <div class="inside"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <input type="radio" id="c-option" name="selector">
                                        <label for="c-option">Public Institutional</label>
                                        <div class="check">
                                            <div class="inside"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <input type="radio" id="d-option" name="selector">
                                        <label for="d-option">Hospital</label>
                                        <div class="check">
                                            <div class="inside"></div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-sm-12 clearfix register_b">
                                <div class="select_div">
                                    <h5>Select</h5>
                                    <ul>
                                        <li>
                                            <input type="radio" id="script" name="script">
                                            <label for="script">SCRIPT N SAVE PHARMACY # 205<span>16986 S. BELLEFLOWER PL    CONSHOHOCKEN    PA     27080</span> </label>
                                            <div class="check"></div>
                                        </li>
                                    </ul>
                                </div>
                                <input type="submit" name="next" class="btn field_btn" value="Next" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ Modal Register -->
        <div id="confirmDiv2" class="medicationModal confirmation_modal listModal healthModal formModal modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" >&times;</button>
                        <h4  class="modal-title"><label>Confirm Dialog</label></h4> 

                    </div>
                    <div class="modal-body refill_medi">

                        <div>


                            <div class="refill_options">
                                <p class="clearfix"><span>You are going to use above 12 hours?</span>

                                </p>
                                <div>
                                    <input id="confirmBtn" type="button" class="btn back_btn" value="Yes" 
                                           onclick="submitValidHours(0)"> 

                                    <button id="cancelConfirmBoxBtn" type="button" class="btn back_btn" data-dismiss="modal" aria-hidden="true" style="width: 80px; vertical-align: middle;" onclick="restValue();">No</button>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
        <!-- jQuery -->
        <script>
            $(function () {
                var pull = $('#pull');
                menu = $('.Menu ul');
                menuHeight = menu.height();

                $(pull).on('click', function (e) {
                    e.preventDefault();
                    menu.slideToggle();
                });

                $(window).resize(function () {
                    var w = $(window).width();
                    if (w > 320 && menu.is(':hidden')) {
                        menu.removeAttr('style');
                    }
                });
            });


            $(window).load(function () {

                if (document.getElementById("mondayStartTimingId").value !== "" || document.getElementById("mondayEndTimingId").value !== "") {
                    $("#monday").attr("checked", "checked");
                    isEnableAllPhoneHours($("#monday").val());
                } else {
                    $("#monday").removeAttr("checked");
                    $("#target input#mondayStartTimingId").prop("disabled", true);
                    $('#mondayStartTimingId').addClass('input-disabled');
                    $("#target input#mondayEndTimingId").prop("disabled", true);
                    $('#mondayEndTimingId').addClass('input-disabled');
                }
                if (document.getElementById("tuesdayStartTimingId").value !== "" || document.getElementById("tuesdayEndTimingId").value !== "") {
                    $("#tuesday").attr("checked", "checked");
                    isEnableAllPhoneHours($("#tuesday").val());

                } else {
                    $("#target input#tuesdayStartTimingId").prop("disabled", true);
                    $('#tuesdayStartTimingId').addClass('input-disabled');
                    $("#target input#tuesdayEndTimingId").prop("disabled", true);
                    $('#tuesdayEndTimingId').addClass('input-disabled');
                    $("#tuesday").removeAttr("checked");
                }
                if (document.getElementById("wednesdayStartTimingId").value !== "" || document.getElementById("wednesdayEndTimingId").value !== "") {
                    $("#wednesday").attr("checked", "checked");
                    isEnableAllPhoneHours($("#wednesday").val());
                } else {
                    $("#target input#wednesdayStartTimingId").prop("disabled", true);
                    $('#wednesdayStartTimingId').addClass('input-disabled');
                    $("#target input#wednesdayEndTimingId").prop("disabled", true);
                    $('#wednesdayEndTimingId').addClass('input-disabled');
                    $("#wednesday").removeAttr("checked");
                }
                if (document.getElementById("thursdayStartTimingId").value !== "" || document.getElementById("thursdayEndTimingId").value !== "") {
                    $("#thursday").attr("checked", "checked");
                    isEnableAllPhoneHours($("#thursday").val());
                } else {
                    $("#target input#thursdayStartTimingId").prop("disabled", true);
                    $('#thursdayStartTimingId').addClass('input-disabled');
                    $("#target input#thursdayEndTimingId").prop("disabled", true);
                    $('#thursdayEndTimingId').addClass('input-disabled');
                    $("#thursday").removeAttr("checked");
                }
                if (document.getElementById("fridayStartTimingId").value !== "" || document.getElementById("fridayEndTimingId").value !== "") {
                    $("#friday").attr("checked", "checked");
                    isEnableAllPhoneHours($("#friday").val());
                } else {
                    $("#target input#fridayStartTimingId").prop("disabled", true);
                    $('#fridayStartTimingId').addClass('input-disabled');
                    $("#target input#fridayEndTimingId").prop("disabled", true);
                    $('#fridayEndTimingId').addClass('input-disabled');
                    $("#friday").removeAttr("checked");
                }
                if (document.getElementById("saturdayStartTimingId").value !== "" || document.getElementById("saturdayEndTimingId").value !== "") {
                    $("#saturday").attr("checked", "checked");
                    isEnableAllPhoneHours($("#saturday").val());
                } else {
                    $("#target input#saturdayStartTimingId").prop("disabled", true);
                    $('#saturdayStartTimingId').addClass('input-disabled');
                    $("#target input#saturdayEndTimingId").prop("disabled", true);
                    $('#saturdayEndTimingId').addClass('input-disabled');
                    $("#saturday").removeAttr("checked");
                }
                if (document.getElementById("saturdayStartTimingId").value !== "" || document.getElementById("saturdayEndTimingId").value !== "") {
                    $("#sunday").attr("checked", "checked");
                    isEnableAllPhoneHours($("#sunday").val());
                } else {
                    $("#target input#sundayStartTimingId").prop("disabled", true);
                    $('#sundayStartTimingId').addClass('input-disabled');
                    $("#target input#sundayEndTimingId").prop("disabled", true);
                    $('#sundayEndTimingId').addClass('input-disabled');
                    $("#sunday").removeAttr("checked");
                }

                if (document.getElementById("mondayStartTimingId").value !== "" && document.getElementById("mondayEndTimingId").value !== ""
                        && document.getElementById("tuesdayStartTimingId").value !== "" && document.getElementById("tuesdayEndTimingId").value !== ""
                        && document.getElementById("wednesdayStartTimingId").value !== "" && document.getElementById("wednesdayEndTimingId").value !== ""
                        && document.getElementById("thursdayStartTimingId").value !== "" && document.getElementById("thursdayEndTimingId").value !== ""
                        && document.getElementById("fridayStartTimingId").value !== "" && document.getElementById("fridayEndTimingId").value !== ""
                        && document.getElementById("saturdayStartTimingId").value !== "" && document.getElementById("saturdayEndTimingId").value !== ""
                        && document.getElementById("saturdayStartTimingId").value !== "" && document.getElementById("saturdayEndTimingId").value !== "") {
                    $("#allSameTime").attr("checked", "checked");
                }
            });

            function markCheckBoxes()
            {
                document.getElementById("monday").checked = true;

            }

            function compareTime(time1, time2) {
                /*         var re = /^([012]?\d):([0-6]?\d)\s*(a|p)m$/i;
                 time1 = time1.match(re);
                 time2 = time2.match(re);
                 
                 
                 if(time1[0] === time2[0]){
                 return 'true';
                 }
                 else if(time1 && time2){
                 var is_pm1 = /p/i.test(time1[3]) ? 12 : 0;
                 var hour1 = (time1[1]*1 + is_pm1) % 12;
                 var is_pm2 = /p/i.test(time2[3]) ? 12 : 0;
                 var hour2 = (time2[1]*1 + is_pm2) % 12;
                 if(hour1 != hour2) return hour1 > hour2;
                 
                 var minute1 = time1[2]*1;
                 var minute2 = time2[2]*1;
                 return minute1 > minute2;
                 }  */

                var time1length = time1.length;
                var time2length = time2.length;
                var startTime;
                var endTime;
                var am;
                var pm;
                if (time1length != null && time1length != undefined && time1length != '' && time1length == 7) {
                    startTime = time1.slice(0, 5);
                    am = time1.slice(5, 7);
                    if (am == 'am') {
                        am = 'AM';
                    } else if (am == 'pm') {
                        pm = 'PM';
                    }
                } else if (time1length != null && time1length != undefined && time1length != '' && time1length == 6) {
                    startTime = time1.slice(0, 4);
                    am = time1.slice(4, 6);
                    if (am == 'am') {
                        am = 'AM';
                    } else if (am == 'pm') {
                        pm = 'PM';
                    }
                }
                if (time2length != null && time2length != undefined && time2length != '' && time2length == 7) {
                    endTime = time2.slice(0, 5);
                    pm = time2.slice(5, 7);
                    if (pm == 'pm') {
                        pm = 'PM';
                    } else if (pm == 'am') {
                        pm = 'AM'
                    }
                } else if (time2length != null && time2length != undefined && time2length != '' && time2length == 6) {
                    endTime = time2.slice(0, 4);
                    pm = time2.slice(4, 6);
                    if (pm == 'pm') {
                        pm = 'PM';
                    } else if (pm == 'am') {
                        pm = 'AM'
                    }
                }

                console.log(startTime);
                console.log(endTime);


                var fTime = startTime + " " + am;


                var tTime = endTime + " " + pm;
                console.log(fTime);
                console.log(tTime);



                var hours = Number(fTime.match(/^(\d+)/)[1]);
                var minutes = Number(fTime.match(/:(\d+)/)[1]);
                var AMPM = fTime.match(/\s(.*)$/)[1];
                if (AMPM == "PM" && hours < 12)
                    hours = hours + 12;
                if (AMPM == "AM" && hours == 12)
                    hours = hours - 12;
                var sHours = hours.toString();
                var sMinutes = minutes.toString();
                if (hours < 10)
                    sHours = "0" + sHours;
                if (minutes < 10)
                    sMinutes = "0" + sMinutes;
                console.log(sHours + ":" + sMinutes);

                var fromDate = new Date();


                fromDate.setHours(sHours, sMinutes, 00);

                console.log('new fromdate' + fromDate.getTime());


                var hours2 = Number(tTime.match(/^(\d+)/)[1]);
                console.log(hours2);
                var minutes2 = Number(tTime.match(/:(\d+)/)[1]);
                console.log(minutes2);
                var AMPM2 = tTime.match(/\s(.*)$/)[1];

                console.log(AMPM2);
                if (AMPM2 == "PM" && hours2 < 12)
                    hours2 = hours2 + 12;
                if (AMPM2 == "AM" && hours2 == 12)
                    hours2 = hours2 - 12;
                var sHours2 = hours2.toString();
                console.log(sHours2);
                var sMinutes2 = minutes2.toString();
                if (hours2 < 10)
                    sHours2 = "0" + sHours2;
                if (minutes2 < 10)
                    sMinutes2 = "0" + sMinutes2;
                console.log(sHours2 + ":" + sMinutes2);

                var toDate = new Date();

                toDate.setHours(sHours2, sMinutes2, 00);
                //debugger;
                console.log('new toDate' + toDate.getTime());
                fromDate = $.datepicker.formatDate("m/dd/yy", fromDate) + " " + fTime;
                toDate = $.datepicker.formatDate("m/dd/yy", toDate) + " " + tTime;

                //if(fromDate > toDate ){
                if (fromDate.getTime() > toDate.getTime()) {
                    return true;
                }//else if(fromDate  === toDate ){
                else if (fromDate.getTime() == toDate.getTime())
                {
                    return true;
                }
                return false;
            }

            function timeFormatter(dateTime) {
                var date = new Date(dateTime);
                if (date.getHours() >= 12) {
                    var hour = parseInt(date.getHours()) - 12;
                    var amPm = "PM";
                } else {
                    var hour = date.getHours();
                    var amPm = "AM";
                }
                var time = hour + ":" + date.getMinutes() + " " + amPm;
                console.log(time);
                return time;
            }
            function isFacilityOperation(chkId, startTimeId, endTimeId) {

                if ($("#" + chkId).is(':checked')) {
                    $("#" + startTimeId).prop(
                            "disabled", false);
                    $('#' + startTimeId).removeClass(
                            'input-disabled');
                    $("#" + endTimeId).prop(
                            "disabled", false);
                    $('#' + endTimeId).removeClass(
                            'input-disabled');
                } else {
                    $("#target input#" + startTimeId)
                            .prop("disabled", true);
                    $('#' + startTimeId).addClass(
                            'input-disabled');
                    $("#target input#" + endTimeId).prop(
                            "disabled", true);
                    $('#' + endTimeId).addClass(
                            'input-disabled');
                    $('#' + startTimeId).val("");
                    $('#' + endTimeId).val("");
                    $("#allSameTime").attr("disabled", "disabled");
                    $("#allSameTime").attr("checked", false);


                }
            }
            $(document).ready(function () {
                var startMaxMinTime = {'timeFormat': 'H:i A', 'minTime': '0:00AM', 'step': '15', 'maxTime': '23:45pm'};
                var endMaxMinTime = {'timeFormat': 'H:i A', 'minTime': '0:00AM', 'step': '15', 'maxTime': '23:45pm'};
                $('#mondayStartTimingId').timepicker(startMaxMinTime);
                $('#mondayEndTimingId').timepicker(endMaxMinTime);
                $('#tuesdayStartTimingId').timepicker(startMaxMinTime);
                $('#tuesdayEndTimingId').timepicker(endMaxMinTime);
                $('#wednesdayStartTimingId').timepicker(startMaxMinTime);
                $('#wednesdayEndTimingId').timepicker(endMaxMinTime);
                $('#thursdayStartTimingId').timepicker(startMaxMinTime);
                $('#thursdayEndTimingId').timepicker(endMaxMinTime);
                $('#fridayStartTimingId').timepicker(startMaxMinTime);
                $('#fridayEndTimingId').timepicker(endMaxMinTime);
                $('#saturdayStartTimingId').timepicker(startMaxMinTime);
                $('#saturdayEndTimingId').timepicker(endMaxMinTime);
                $('#sundayStartTimingId').timepicker(startMaxMinTime);
                $('#sundayEndTimingId').timepicker(endMaxMinTime);
            });

            if (document.getElementById("fName2").value !== "" || $("#lName2").val() !== "" || $("#email2").val() !== "" || $("#phone2").val() !== "") {
                $("#secondaryContact").attr("checked", "checked");
            } else {
                $("#target input#fName2").prop("disabled", true);
                $('#fName2').addClass('input-disabled');
                $("#target input#lName2").prop("disabled", true);
                $('#lName2').addClass('input-disabled');
                $("#target input#email2").prop("disabled", true);
                $('#email2').addClass('input-disabled');
                $("#target input#phone2").prop("disabled", true);
                $('#phone2').addClass('input-disabled');
                $("#secondaryContact").removeAttr("checked");
            }

            if (($("#fName3").val().length > 0) && ($("#lName3").val().length > 0) && ($("#email3").val().length > 0) && ($("#phone3").val().length > 0)) {
                $("#thirdContact").attr("checked", "checked");
            } else {
                $("#target input#fName3").prop("disabled", true);
                $('#fName3').addClass('input-disabled');
                $("#target input#lName3").prop("disabled", true);
                $('#lName3').addClass('input-disabled');
                $("#target input#email3").prop("disabled", true);
                $('#email3').addClass('input-disabled');
                $("#target input#phone3").prop("disabled", true);
                $('#phone3').addClass('input-disabled');
                $("#thirdContact").removeAttr("checked");
            }

            function ValidateForm() {
                var errCount = 0;

                if ($('#inchargeFirstName').val().trim() == '') {
                    $('#inchargeFirstName').addClass("errorClass");
                    errCount++;
                    return false;
                } else {
                    $('#inchargeFirstName').removeClass("errorClass");

                }
                if ($('#inchargeLastName').val().trim() == '') {
                    $('#inchargeLastName').addClass("errorClass");
                    errCount++;
                    return false;
                } else {
                    $('#inchargeLastName').removeClass("errorClass");

                }

                if ($('#inchargePhone').val().trim() == '') {
                    $('#inchargePhone').addClass("errorClass");
                    errCount++;
                    return false;
                } else {
                    $('#inchargePhone').removeClass("errorClass");

                }

                if ($('#inchargeEmail').val().trim() == '') {
                    $('#inchargeEmail').addClass("errorClass");
                    errCount++;
                    return false;
                } else
                {

                    $('#inchargeEmail').removeClass("errorClass");

                    var emil = $('#inchargeEmail').val();
                    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                    if (!emailReg.test(emil)) {
                        customAlert('Please enter valid email');
                    } else {
                        $(this).removeClass('errorClass');

                    }
                }




                if ($('#monday').is(':checked')
                        || $('#tuesday').is(':checked')
                        || $('#wednesday').is(':checked')
                        || $('#thursday').is(':checked')
                        || $('#friday').is(':checked')
                        || $('#saturday').is(':checked')
                        || $('#sunday').is(':checked')) {

                } else {
                    customAlert('Atleast one facility checkbox should be checked. Please select the time.');

                    errCount++;
                }

                if ($('#monday').is(':checked')) {
                    if (($('#mondayStartTimingId').val() == null || $('#mondayStartTimingId').val() == undefined || $('#mondayStartTimingId').val() == '')) {
                        $('#mondayStartTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }
                    if (($('#mondayEndTimingId').val() == null || $('#mondayEndTimingId').val() == undefined || $('#mondayEndTimingId').val() == '')) {
                        $('#mondayEndTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }

                    if (($('#mondayStartTimingId').val() != null && $('#mondayStartTimingId').val() != undefined && $('#mondayStartTimingId').val() != '') &&
                            ($('#mondayEndTimingId').val() != null && $('#mondayEndTimingId').val() != undefined && $('#mondayEndTimingId').val() != '')) {
                        var starttime = '';
                        var endtime = '';
                        var time = '';
                        starttime = $("#mondayStartTimingId").val();
                        endtime = $("#mondayEndTimingId").val();

                        //time = compareTime(starttime, endtime);
                        if ($("#IsValidHours").val() == 0 && endtime <= starttime) {
                            $('#mondayStartTimingId').addClass("errorClass");
                            $('#mondayEndTimingId').addClass("errorClass");
                            changeEvent();
                            errCount++;
                            return false;
                        }

                    }

                }

                if ($('#tuesday').is(':checked')) {
                    if (($('#tuesdayStartTimingId').val() == null || $('#tuesdayStartTimingId').val() == undefined || $('#tuesdayStartTimingId').val() == '')) {
                        $('#tuesdayStartTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }
                    if (($('#tuesdayEndTimingId').val() == null || $('#tuesdayEndTimingId').val() == undefined || $('#tuesdayEndTimingId').val() == '')) {
                        $('#tuesdayEndTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }

                    if (($('#tuesdayStartTimingId').val() != null && $('#tuesdayStartTimingId').val() != undefined && $('#tuesdayStartTimingId').val() != '') &&
                            ($('#tuesdayEndTimingId').val() != null && $('#tuesdayEndTimingId').val() != undefined && $('#tuesdayEndTimingId').val() != '')) {
                        var starttime = '';
                        var endtime = '';
                        var time = '';
                        starttime = $("#tuesdayStartTimingId").val();
                        endtime = $("#tuesdayEndTimingId").val();
                        // time = compareTime(starttime, endtime);
                        if ($("#IsValidHours").val() == 0 && endtime <= starttime) {

                            $('#tuesdayStartTimingId').addClass("errorClass");
                            $('#tuesdayEndTimingId').addClass("errorClass");
                            changeEvent();
                            errCount++;
                        }

                    }

                }



                if ($('#wednesday').is(':checked')) {
                    if (($('#wednesdayStartTimingId').val() == null || $('#wednesdayStartTimingId').val() == undefined || $('#wednesdayStartTimingId').val() == '')) {
                        $('#wednesdayStartTimingId').addClass("errorClass");
                        errCount++
                        return false;
                    }
                    if (($('#wednesdayEndTimingId').val() == null || $('#wednesdayEndTimingId').val() == undefined || $('#wednesdayEndTimingId').val() == '')) {
                        $('#wednesdayEndTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }

                    if (($('#wednesdayStartTimingId').val() != null && $('#wednesdayStartTimingId').val() != undefined && $('#wednesdayStartTimingId').val() != '') &&
                            ($('#wednesdayEndTimingId').val() != null && $('#wednesdayEndTimingId').val() != undefined && $('#wednesdayEndTimingId').val() != '')) {
                        var starttime = '';
                        var endtime = '';
                        var time = '';
                        starttime = $("#wednesdayStartTimingId").val();
                        endtime = $("#wednesdayEndTimingId").val();

                        // time = compareTime(starttime, endtime);
                        if ($("#IsValidHours").val() == 0 && endtime <= starttime) {
                            $('#wednesdayStartTimingId').addClass("errorClass");
                            $('#wednesdayEndTimingId').addClass("errorClass");
                            changeEvent();
                            errCount++;
                        }

                    }

                }

                if ($('#thursday').is(':checked')) {
                    if (($('#thursdayStartTimingId').val() == null || $('#thursdayStartTimingId').val() == undefined || $('#thursdayStartTimingId').val() == '')) {
                        $('#thursdayStartTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }
                    if (($('#thursdayEndTimingId').val() == null || $('#thursdayEndTimingId').val() == undefined || $('#thursdayEndTimingId').val() == '')) {
                        $('#thursdayEndTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }

                    if (($('#thursdayStartTimingId').val() != null && $('#thursdayStartTimingId').val() != undefined && $('#thursdayStartTimingId').val() != '') &&
                            ($('#thursdayEndTimingId').val() != null && $('#thursdayEndTimingId').val() != undefined && $('#thursdayEndTimingId').val() != '')) {
                        var starttime = '';
                        var endtime = '';
                        var time = '';
                        starttime = $("#thursdayStartTimingId").val();
                        endtime = $("#thursdayEndTimingId").val();

                        //time = compareTime(starttime, endtime);
                        if ($("#IsValidHours").val() == 0 && endtime <= starttime) {
                            $('#thursdayStartTimingId').addClass("errorClass");
                            $('#thursdayEndTimingId').addClass("errorClass");
                            changeEvent();
                            errCount++;
                        }

                    }

                }


                if ($('#friday').is(':checked')) {
                    if (($('#fridayStartTimingId').val() == null || $('#fridayStartTimingId').val() == undefined || $('#fridayStartTimingId').val() == '')) {
                        $('#fridayStartTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }
                    if (($('#fridayEndTimingId').val() == null || $('#fridayEndTimingId').val() == undefined || $('#fridayEndTimingId').val() == '')) {
                        $('#fridayEndTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }

                    if (($('#fridayStartTimingId').val() != null && $('#fridayStartTimingId').val() != undefined && $('#fridayStartTimingId').val() != '') &&
                            ($('#fridayEndTimingId').val() != null && $('#fridayEndTimingId').val() != undefined && $('#fridayEndTimingId').val() != '')) {
                        var starttime = '';
                        var endtime = '';
                        var time = '';
                        starttime = $("#fridayStartTimingId").val();
                        endtime = $("#fridayEndTimingId").val();

                        //time = compareTime(starttime, endtime);
                        if ($("#IsValidHours").val() == 0 && endtime <= starttime) {
                            $('#fridayStartTimingId').addClass("errorClass");
                            $('#fridayEndTimingId').addClass("errorClass");
                            changeEvent();
                            errCount++;
                        }

                    }

                }
                if ($('#saturday').is(':checked')) {
                    if (($('#saturdayStartTimingId').val() == null || $('#saturdayStartTimingId').val() == undefined || $('#saturdayStartTimingId').val() == '')) {
                        $('#saturdayStartTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }
                    if (($('#saturdayEndTimingId').val() == null || $('#saturdayEndTimingId').val() == undefined || $('#saturdayEndTimingId').val() == '')) {
                        $('#saturdayEndTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }

                    if (($('#saturdayStartTimingId').val() != null && $('#saturdayStartTimingId').val() != undefined && $('#saturdayStartTimingId').val() != '') &&
                            ($('#saturdayEndTimingId').val() != null && $('#saturdayEndTimingId').val() != undefined && $('#saturdayEndTimingId').val() != '')) {
                        var starttime = '';
                        var endtime = '';
                        var time = '';
                        starttime = $("#saturdayStartTimingId").val();
                        endtime = $("#saturdayEndTimingId").val();

                        // time = compareTime(starttime, endtime);
                        if ($("#IsValidHours").val() == 0 && endtime <= starttime) {
                            $('#saturdayStartTimingId').addClass("errorClass");
                            $('#saturdayEndTimingId').addClass("errorClass");
                            changeEvent();
                            errCount++;
                        }

                    }

                }
                if ($('#sunday').is(':checked')) {
                    if (($('#sundayStartTimingId').val() == null || $('#sundayStartTimingId').val() == undefined || $('#sundayStartTimingId').val() == '')) {
                        $('#sundayStartTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }
                    if (($('#sundayEndTimingId').val() == null || $('#sundayEndTimingId').val() == undefined || $('#sundayEndTimingId').val() == '')) {
                        $('#sundayEndTimingId').addClass("errorClass");
                        errCount++;
                        return false;
                    }

                    if (($('#sundayStartTimingId').val() != null && $('#sundayStartTimingId').val() != undefined && $('#sundayStartTimingId').val() != '') &&
                            ($('#sundayEndTimingId').val() != null && $('#sundayEndTimingId').val() != undefined && $('#sundayEndTimingId').val() != '')) {
                        var starttime = '';
                        var endtime = '';
                        var time = '';
                        starttime = $("#sundayStartTimingId").val();
                        endtime = $("#sundayEndTimingId").val();

                        //time = compareTime(starttime, endtime);
                        if ($("#IsValidHours").val() == 0 && endtime <= starttime) {
                            $('#sundayStartTimingId').addClass("errorClass");
                            $('#sundayEndTimingId').addClass("errorClass");
                            changeEvent();
                            errCount++;
                        }

                    }

                }


                if ($('#secondaryContact').is(':checked')) {

                    if ($('#fName2').val().trim() == '') {
                        $('#fName2').addClass("errorClass");
                        errCount++;
                    } else {
                        $('#fName2').removeClass("errorClass");

                    }
                    if ($('#lName2').val().trim() == '') {
                        $('#lName2').addClass("errorClass");
                        errCount++;
                    } else {
                        $('#lName2').removeClass("errorClass");

                    }

                    if ($('#phone2').val().trim() == '') {
                        $('#phone2').addClass("errorClass");
                        errCount++;
                    } else {
                        $('#phone2').removeClass("errorClass");

                    }

                    if ($('#email2').val().trim() == '') {
                        $('#email2').addClass("errorClass");
                        errCount++;
                    } else {

                        $('#email2').removeClass("errorClass");

                        var emil = $('#email2').val();
                        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                        if (!emailReg.test(emil)) {
                            customAlert('Please enter valid email.');
                        } else {
                            $(this).removeClass('errorClass');

                        }
                    }

                }

                if ($('#thirdContact').is(':checked')) {

                    if ($('#fName3').val().trim() == '') {
                        $('#fName3').addClass("errorClass");
                        errCount++;
                    } else {
                        $('#fName3').removeClass("errorClass");

                    }
                    if ($('#lName3').val().trim() == '') {
                        $('#lName3').addClass("errorClass");
                        errCount++;
                    } else {
                        $('#lName3').removeClass("errorClass");

                    }

                    if ($('#phone3').val().trim() == '') {
                        $('#phone3').addClass("errorClass");
                        errCount++;
                    } else {
                        $('#phone3').removeClass("errorClass");

                    }

                    if ($('#email3').val().trim() == '') {
                        $('#email3').addClass("errorClass");
                        errCount++;
                    } else {

                        $('#email3').removeClass("errorClass");

                        var emil = $('#email3').val();
                        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                        if (!emailReg.test(emil)) {
                            customAlert('Please enter valid email.');
                        } else {
                            $(this).removeClass('errorClass');

                        }
                    }

                }



                if ($('#acceptBtn').is(':checked')) {
                } else {
                    customAlert('Please check the accept button.');
                    errCount++;
                }

                if ($('#privacyPolicy').is(':checked')) {
                } else {
                    customAlert('Please check the privacy policy button.');
                    errCount++;
                }
                if (errCount > 0) {
                    return false;
                } else {
                    return true;

                }
            }
            ;

            $('#inchargeFirstName').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#inchargeLastName').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#inchargePhone').focus(function () {
                $(this).removeClass('errorClass');
            });

            $('#inchargeEmail')
                    .focusout(
                            function () {
                                $('#inchargeEmail')
                                        .filter(
                                                function () {
                                                    var emil = $(
                                                            '#inchargeEmail')
                                                            .val();
                                                    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                                                    if (!emailReg
                                                            .test(emil)) {
                                                        customAlert('Please enter valid email.');
                                                        $(this)
                                                                .addClass(
                                                                        'errorClass');
                                                    } else {
                                                        $(this)
                                                                .removeClass(
                                                                        'errorClass');
                                                    }
                                                })
                            });
            $('#fName2').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#lName2').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#phone2').focus(function () {
                $(this).removeClass('errorClass');
            });

            $('#email2')
                    .focusout(
                            function () {
                                $('#email2')
                                        .filter(
                                                function () {
                                                    var emil = $(
                                                            '#email2')
                                                            .val();
                                                    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                                                    if (!emailReg
                                                            .test(emil)) {
                                                        customAlert('Please enter valid email.');
                                                        $(this)
                                                                .addClass(
                                                                        'errorClass');
                                                    } else {
                                                        $(this)
                                                                .removeClass(
                                                                        'errorClass');
                                                    }
                                                })
                            });
            $('#fName3').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#lName3').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#email3').focus(function () {
                $(this).removeClass('errorClass');
            });

            $('#email3').focusout(function () {
                $('#email3').filter(function () {
                    var emil = $('#email3').val();
                    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                    if (!emailReg.test(emil)) {
                        customAlert('Please enter valid email.');
                        $(this).addClass('errorClass');
                    } else {
                        $(this).removeClass('errorClass');
                    }
                });
            });


            $('#mondayStartTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#mondayEndTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#tuesdayStartTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#tuesdayEndTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#wednesdayStartTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#wednesdayEndTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#thursdayStartTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#thursdayEndTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#fridayStartTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#fridayEndTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#saturdayStartTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#saturdayEndTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#sundayStartTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            $('#sundayEndTimingId').focus(function () {
                $(this).removeClass('errorClass');
            });
            var ALERT_TITLE = "Message";
            var ALERT_BUTTON_TEXT = "Ok";



            function customAlert(txt) {
                d = document;

                if (d.getElementById("modalContainer"))
                    return;

                mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
                mObj.id = "modalContainer";
                mObj.style.height = d.documentElement.scrollHeight + "px";

                alertObj = mObj.appendChild(d.createElement("div"));
                alertObj.id = "alertBox";
                if (d.all && !window.opera)
                    alertObj.style.top = document.documentElement.scrollTop + "px";
                alertObj.style.visiblity = "visible";
                //alertObj.style.left = (d.documentElement.scrollWidth - alertObj.offsetWidth) / 2 + "px";
                if (window.screen.width == 320) {
                    alertObj.style.left = "1%";
                    alertObj.style.width = "98%";
                } else {
                    alertObj.style.left = "50%";
                    alertObj.style.width = "50%";
                    alertObj.style.transform = "translate(-40%)";
                }
                h1 = alertObj.appendChild(d.createElement("h1"));
                h1.appendChild(d.createTextNode(ALERT_TITLE));

                msg = alertObj.appendChild(d.createElement("p"));
                //msg.appendChild(d.createTextNode(txt));
                msg.innerHTML = txt;

                btn = alertObj.appendChild(d.createElement("a"));
                btn.id = "closeBtn";
                btn.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
                btn.href = "#";
                btn.focus();
                btn.onclick = function () {
                    removeCustomAlert();
                    return false;
                }

                alertObj.style.display = "block";

            }

            function removeCustomAlert() {
                document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
            }
            function sameTime(chkId) {
                if ($("#" + chkId).is(':checked')) {
                    for (var i = 0; i < $('.phoneHoursFrom').length; i++) {
                        var phoneHoursFrom = document.getElementsByName("pharmacyFacilityOperationList[" + i + "].phoneHoursFrom");
                        var phoneHoursTo = document.getElementsByName("pharmacyFacilityOperationList[" + i + "].phoneHoursTo");
                        if (phoneHoursFrom[0].value !== "" && phoneHoursTo[0].value !== "") {
                            $('.phoneHoursFrom').val(phoneHoursFrom[0].value);
                            $('.phoneHoursTo').val(phoneHoursTo[0].value);
                            $('.phoneHoursFrom').removeClass("input-disabled");
                            $('.phoneHoursTo').removeClass("input-disabled");
                            $(".days").attr("checked", true);
                            $('.phoneHoursTo').removeAttr("disabled");
                            $('.phoneHoursFrom').removeAttr("disabled");
                        }
                    }
                } else {
                    $('.phoneHoursFrom').addClass("input-disabled");
                    $('.phoneHoursTo').addClass("input-disabled");
                    $('.phoneHoursFrom').val("");
                    $('.phoneHoursTo').val("");
                    $(".days").attr("checked", false);
                    $("#allSameTime").attr("disabled", "disabled");
                }
            }
            function isEnableAllPhoneHours(txtValue) {
                if (txtValue !== "") {
                    $("#allSameTime").removeAttr("disabled");
                    $('.phoneHoursFrom').removeClass("input-disabled");
                    $('.phoneHoursTo').removeClass("input-disabled");
                    $('.phoneHoursTo').removeAttr("disabled");
                    $('.phoneHoursFrom').removeAttr("disabled");
                } else {
                    $("#allSameTime").attr("disabled", "disabled");
                    $('.phoneHoursFrom').addClass("input-disabled");
                    $('.phoneHoursTo').addClass("input-disabled");
                    $('.phoneHoursTo').attr("disabled", true);
                    $('.phoneHoursFrom').attr("disabled", true);
                }
            }
            function isContactsEnable(chkId, index) {
                if ($("#" + chkId).is(':checked')) {
                    $("#fName" + index).prop(
                            "disabled", false);
                    $('#fName' + index).removeClass(
                            'input-disabled');
                    $("#lName" + index).prop(
                            "disabled", false);
                    $('#lName' + index).removeClass(
                            'input-disabled');
                    $("#email" + index).prop(
                            "disabled", false);
                    $('#email' + index).removeClass(
                            'input-disabled');
                    $("#phone" + index).prop(
                            "disabled", false);
                    $('#phone' + index).removeClass(
                            'input-disabled');
                } else {
                    if (confirm("Are you sure you want to uncheck this?")) {
                        $("#fName" + index).val("");
                        $("#lName" + index).val("");
                        $("#email" + index).val("");
                        $("#phone" + index).val("");
                        $("#fName" + index).prop(
                                "disabled", true);
                        $('#fName' + index).addClass(
                                'input-disabled');
                        $("#lName" + index).prop(
                                "disabled", true);
                        $('#lName' + index).addClass(
                                'input-disabled');
                        $("#email" + index).prop(
                                "disabled", true);
                        $('#email' + index).addClass(
                                'input-disabled');
                        $("#phone" + index).prop(
                                "disabled", true);
                        $('#phone' + index).addClass(
                                'input-disabled');
                    } else {
                        return false;
                    }
                }
            }
            function populateSameHours(startHourId,endHourId, clsName) {
                if ($("#"+startHourId).val() === '07:00 AM' && $("#"+endHourId).val() === '21:00 PM') {
                    $("." + clsName).val($("#"+endHourId).val());
                    $(".phoneHoursFrom").val($("#"+startHourId).val());
                    $("#saturdayStartTimingId").val("");
                    $("#saturdayEndTimingId").val("");
                    $("#sundayStartTimingId").val("");
                    $("#sundayEndTimingId").val("");
                }

            }
            function isEqualHours(fromHourId, toHourId) {
                var frmHours = $("#" + fromHourId).val();
                var toHour=$("#" + toHourId).val();
                if(frmHours.length==0 || toHour.length==0){
                    return;
                }
                if ($("#IsValidHours").val() == 0 && toHour <= frmHours) {
                    $("#confirmDiv2").modal('show');
                }
            }
            function  submitValidHours(type) {
                if (type == 0) {
                    $("#IsValidHours").val('1');
                    $("#confirmDiv2").modal('hide');
                } else {
                    $("#IsValidHours").val('1');
                    $("#target").submit();
                }

            }
            function restValue() {
                $("#IsValidHours").val('0');
                $("#confirmDiv2").modal('hide');
            }
            function changeEvent() {
                $("#confirmDiv2").modal('show');
                $("#confirmBtn").attr("onclick", "submitValidHours(1)");
            }
        </script>


    </body>

</html>
