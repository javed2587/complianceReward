<%-- 
    Document   : uploadcreditcard
    Created on : Sep 29, 2015, 5:32:17 PM
    Author     : msheraz
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div class="container containerConents">
            <form:form action="${pageContext.request.contextPath}/patient/save" commandName="patientProfile" method="post" enctype="multipart/form-data" onsubmit="return validateField();">
                <form:hidden path="communicationID"/>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <p class="font-class" style="color: red; font-weight: bold;">
                            Almost done- just a few last questions...<br style="margin-bottom: 10px;"/>
                            <b class="heading-font-class" style="color: rgba(51, 104, 158, 1);">Upload Prescription Drug Insurance Card here</b> 
                        </p>
                    </div>
                </div>
                <div id="frontLargeDiv" class="row hidden-sm hidden-xs">
                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                        <div class="row" style="margin-left: 0; margin-right: 0;">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0;">
                                <b class="heading-font-class" style="font-weight: normal; float: left; padding-right: 10px;">Front Side:</b>
                                <div id="frontErrorReq" class="errorMessage" style="float: none; margin-bottom: 0; padding-top: 6px;"></div>
                            </div>
                        </div>    
                        <div class="row" style="margin-left: 0; margin-right: 0;">
                            <div id="frontDropZone" class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="border: rgba(102, 102, 102, 1) 1px solid; margin-bottom: 10px; text-align: center; height: 250px;">
                                <span style="display: inline-block; height: 100%; vertical-align: middle;"></span><img id="forntFileImgLg" src="${not empty insuranceCardFront ? insuranceCardFront : ''}" name="forntFileImgLg" alt="Drag image here"/>
                                <form:input id="frontFileLg" path="insuranceCardFront" onchange="uploadFile(this.files[0], 'front');" type="file" style="display: none;" accept="image/*"/>
                            </div>
                        </div>
                        <div class="row" style="margin-left: 0; margin-right: 0;">    
                            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5" style="padding-left: 0;">
                                <p class="heading-font-class" style="white-space: nowrap;">OR Select a file to upload: </p>
                            </div>
                            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                                <div style="float: left; margin-right: 5px;">
                                    <button type="button" id="chooseFrontBtnLg" class="btn btn-primary" onclick="chooseFile('front')">
                                        <i class="fa fa-search"></i> Choose File
                                    </button>
                                </div>
                            </div>
                        </div>    
                    </div>
                    <div id="backLargeDiv" class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                        <div class="row" style="margin-left: 0; margin-right: 0;">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0;">
                                <b class="heading-font-class" style="font-weight: normal; float: left; padding-right: 10px;">Back Side:</b>
                                <div id="backErrorReq" class="errorMessage" style="float: none; margin-bottom: 0; padding-top: 6px;"></div>
                            </div>
                        </div>    
                        <div class="row" style="margin-left: 0; margin-right: 0;">
                            <div id="backDropZone" class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="border: rgba(102, 102, 102, 1) 1px solid; margin-bottom: 10px; text-align: center; height: 250px;">
                                <span style="display: inline-block; height: 100%; vertical-align: middle;"></span><img id="backFileImgLg" src="" alt="Drag image here"/>
                                <form:input id="backFileLg" path="insuranceCardBack" onchange="uploadFile(this.files[0], 'back');" type="file" style="display: none;" accept="image/*"/>
                            </div>
                        </div>
                        <div class="row" style="margin-left: 0; margin-right: 0;">    
                            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5" style="padding-left: 0;">
                                <p class="heading-font-class" style="white-space: nowrap;">OR Select a file to upload: </p>
                            </div>
                            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                                <div style="float: left; margin-right: 5px;">
                                    <button type="button" id="chooseBackBtnLg" class="btn btn-primary" onclick="chooseFile('back')">
                                        <i class="fa fa-search"></i> Choose File
                                    </button>
                                </div>
                            </div>
                        </div>    
                    </div>  
                </div>
                <div id="smallFrontDiv" class="row hidden-lg hidden-md">
                    <div class="col-md-3 col-sm-3 col-xs-3" style=" padding-right: 0;">
                        <p class="font-class">Front Side:</p>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-6" style=" padding: 0; text-align: center;">
                        <p id="frontDateTime" class="font-class" style="white-space: nowrap; font-weight: normal;">No file</p>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-3" style=" padding: 0;">
                        <form:input id="captureFront" path="insuranceCardFront" type="file" accept="image/*" style="display: none;"/>
                        <a class="font-class" onclick="takePhoto('front');" href="#" style="white-space: nowrap">Take Photo</a>
                    </div>
                    <div id="smallFrontErrorReq" class="col-md-12 col-sm-12 col-xs-12 errorMessage" style="clear: both; margin-bottom: 10px; line-height: 0; display: none;">

                    </div>        
                </div>
                <div id="smallBackDiv" class="row hidden-lg hidden-md">
                    <div class="col-md-3 col-sm-3 col-xs-3" style=" padding-right: 0;">
                        <p class="font-class">Back Side:</p>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-6" style=" padding: 0;">
                        <p id="backDateTime" class="font-class" style="white-space: nowrap; text-align: center;">No file</p>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-3" style=" padding: 0;">
                        <form:input id="captureBack" path="insuranceCardBack" type="file" accept="image/*" style="display: none;"/>
                        <a class="font-class" onclick="takePhoto('back');" href="#" style="white-space: nowrap;">Take Photo</a>
                    </div>
                    <div id="smallBackErrorReq" class="col-md-12 col-sm-12 col-xs-12 errorMessage" style="clear: both; margin-bottom: 10px; line-height: 0; display: none;">

                    </div>    
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h4 class="heading-font-class" style="color: rgba(51, 104, 158, 1);">Patient Relationship to Cardholder</h4>
                    </div>
                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                        <div class="row">
                            <div id="cardHolderDiv" class="col-lg-3 col-md-2 col-sm-12 col-xs-12">
                                <form:radiobutton id="cardHolderRadio" path="patientCardHolderRelation" name="relationShip" value="Cardholder"/>
                                <label class="font-class" for="cardHolderRadio">Cardholder</label>
                            </div>
                            <div id="spouseDiv" class="col-lg-2 col-md-1 col-sm-12 col-xs-12">
                                <form:radiobutton id="spouseRadio" path="patientCardHolderRelation" name="relationShip" value="Spouse"/>
                                <label class="font-class" for="spouseRadio">Spouse</label>
                            </div>
                            <div id="childDiv" class="col-lg-2 col-md-1 col-sm-12 col-xs-12">
                                <form:radiobutton id="childRadio" path="patientCardHolderRelation" name="relationShip" value="Child"/>
                                <label class="font-class" for="childRadio">Child</label>
                            </div>
                            <div id="otherDiv" class="col-lg-3 col-md-2 col-sm-12 col-xs-12">
                                <form:radiobutton id="otherRadio" path="patientCardHolderRelation" name="relationShip" value="Other Dependant"/>
                                <label class="font-class" for="otherRadio">Other Dependant</label>
                            </div>
                        </div>    
                    </div>    
                </div> 
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h4 class="heading-font-class" style="color: rgba(51, 104, 158, 1);">Patient Known Allergies</h4>
                    </div>
                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                        <div class="row">
                            <div class="col-lg-3 col-md-2 col-sm-12 col-xs-12">
                                <form:radiobutton id="noAllergyRadio" path="allergyStatus" name="patientAllergies" value="None"/>
                                <label class="font-class" for="noAllergyRadio">None Known</label>
                            </div>
                            <div class="col-lg-9 col-md-10 col-sm-12 col-xs-12">
                                <form:radiobutton id="allergyRadio" path="allergyStatus" name="patientAllergies" value="Yes"/>
                                <label class="font-class" for="allergyRadio">Yes (List below separated by commas)</label>
                            </div>
                        </div>    
                        <div class="row">    
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <form:input id="knowAllergiesTxt" class="font-class form-control" path="patientAllergies" placeholder="Enter Known Allergies" maxlength="255"/>
                                <div id="knowAllergiesReq" class="errorMessage"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <h4 class="heading-font-class" style="color: rgba(51, 104, 158, 1);">Dermatologist Prescribing</h4>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <form:input class="font-class form-control" path="dermatologist" placeholder="Enter Dermatologist" maxlength="100"/>
                                <div id="dermatologistReq" class="errorMessage"></div>
                            </div>
                        </div>
                    </div> 
                </div>
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="margin-top: 10px;">
                        <b class="font-class" style="font-weight: normal; color: red;">
                            Page 3 of 3
                        </b>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="text-align: right; margin-top: 10px;">
                        <a class="font-class" style="text-decoration: underline; color: red;" href="${pageContext.request.contextPath}/patient/creditcardinfo"><i class="fa fa-angle-double-left" style="margin-right: 2px;"></i>BACK</a>
                        <button id="doneBtn" class="btn btn-primary">DONE</button>
                    </div>
                </div>            
            </form:form>
        </div> 
        <jsp:include page="./inc/footer.jsp" />
        <script>
            $(window).on("orientationchange", function () {
                if ($("#frontLargeDiv").is(":visible") && $("#backLargeDiv").is(":visible")) {
                    $("#frontFileLg").attr("name", "insuranceCardFront");
                    $("#backFileLg").attr("name", "insuranceCardBack");
                    $("#captureFront").attr("name", "");
                    $("#captureBack").attr("name", "");
                } else {
                    $("#frontFileLg").attr("name", "");
                    $("#backFileLg").attr("name", "");
                    $("#captureFront").attr("name", "insuranceCardFront");
                    $("#captureBack").attr("name", "insuranceCardBack");
                }
            });

            $(window).on("load", function () {
                if ($("#frontLargeDiv").is(":visible") && $("#backLargeDiv").is(":visible")) {
                    $("#frontFileLg").attr("name", "insuranceCardFront");
                    $("#backFileLg").attr("name", "insuranceCardBack");
                    $("#captureFront").attr("name", "");
                    $("#captureBack").attr("name", "");
                } else {
                    $("#frontFileLg").attr("name", "");
                    $("#backFileLg").attr("name", "");
                    $("#captureFront").attr("name", "insuranceCardFront");
                    $("#captureBack").attr("name", "insuranceCardBack");
                }
            });

            function takePhoto(target) {
                if (target == 'front') {
                    $("#captureFront").click();
                } else {
                    $("#captureBack").click();
                }
            }
            $("#captureFront").on("change", function () {
                var now = new Date();
                $("#frontDateTime").text("Uploaded " + formatDate(now));
            });
            $("#captureBack").on("change", function () {
                var now = new Date();
                $("#backDateTime").text("Uploaded " + formatDate(now));
            });
            function formatDate(date) {
                var months = (date.getMonth() + 1) >= 10 ? (date.getMonth() + 1) : '0' + (date.getMonth() + 1);
                var day = date.getDate() >= 10 ? date.getDate() : '0' + date.getDate();
                var hours = date.getHours();
                var minutes = date.getMinutes();
                var ampm = hours >= 12 ? 'pm' : 'am';
                hours = hours % 12;
                hours = hours ? hours : 12; // the hour '0' should be '12'
                minutes = minutes < 10 ? '0' + minutes : minutes;
                var strTime = hours + ':' + minutes + ampm;
                return months + "-" + day + "-" + date.getFullYear() + "  " + strTime;
            }
            $(window).load(function () {
                $("#knowAllergiesTxt").prop("readonly", true);
            });
            $("#allergyRadio").on("click", function () {
                if (this.checked) {
                    $("#knowAllergiesTxt").prop("readonly", false);
                }
            });
            $("#noAllergyRadio").on("click", function () {
                if (this.checked) {
                    $("#knowAllergiesTxt").prop("readonly", true);
                    $("#knowAllergiesTxt").val("");
                }
            });

            ////////////////////////////////////////////////////////////
            ///////////////// File upload handling /////////////////////
            ///////////////////////////////////////////////////////////
            function chooseFile(target) {
                if (target == "front") {
                    $("#frontFileLg").click();
                } else if (target == "back") {
                    $("#backFileLg").click();
                }
            }

            function uploadFile(files, target) {
                // show image on screen
                if (files) {
                    var reader = new FileReader();
                    var imageObj = $('#forntFileImgLg');
                    if (target == "back") {
                        imageObj = $('#backFileImgLg');
                    } else if (target == "front") {
                        imageObj = $('#forntFileImgLg');
                    }
                    reader.onload = function (e) {
                        imageObj.attr('src', e.target.result).width(300).height(200);
                    };
                    reader.readAsDataURL(files);
                }
            }

            function sendFileToServer(data, target, file)
            {
                var imageObj;
                if(target === "front"){
                    imageObj = $('#forntFileImgLg');
                } else if(target === "back"){
                    imageObj = $('#backFileImgLg');
                }
                imageObj.attr("alt", "loading image...");
                imageObj.attr("src", "");
                $("#doneBtn").prop('disabled', true);
                var uploadURL = "${pageContext.request.contextPath}/patient/uploadimage/" + target;
                $.ajax({
                    url: uploadURL,
                    type: "POST",
                    contentType: false,
                    processData: false,
                    cache: false,
                    data: data,
                    success: function (data) {
                        imageObj.attr("alt", "Done");
                        $("#doneBtn").prop('disabled', false);
                        uploadFile(file, target);
                    }
                });

            }

            //drag and drop handling
            $("#frontDropZone").add("#backDropZone").on('dragenter', function (e) {
                e.stopPropagation();
                e.preventDefault();
            });

            $("#frontDropZone").add("#backDropZone").on('dragover', function (e) {
                e.stopPropagation();
                e.preventDefault();
            });

            $("#frontDropZone").on('drop', function (ev) {
                ev.preventDefault();
                var files = ev.originalEvent.dataTransfer.files;
                //uploadFile(files[0], "front");
                
                var data = new FormData();
                $.each(files, function(key, value){
                    data.append("file-"+key, value);
                });
                sendFileToServer(data, 'front', files[0]);
                
            });

            $("#backDropZone").on('drop', function (e) {
                e.preventDefault();
                var files = e.originalEvent.dataTransfer.files;
                //uploadFile(files[0], "back");
                
                var data = new FormData();
                $.each(files, function(key, value){
                    data.append("file-"+key, value);
                });
                sendFileToServer(data, 'back', files[0]);
            });
            /////////////////////////////////////////////////////////
            //////////////////// Form Validation ///////////////////
            ////////////////////////////////////////////////////////
            function validateField() {
                var empty = true;
                if ($("#allergyRadio").is(':checked')) {
                    if ($("#knowAllergiesTxt").val().trim() === "") {
                        $("#knowAllergiesReq").text("Required");
                        $("#knowAllergiesReq").show();
                        empty = false;
                    }
                }
                if ($("#dermatologist").val().trim() === "") {
                    $("#dermatologistReq").text("Required");
                    $("#dermatologistReq").show();
                    empty = false;
                }

                if ($("#smallFrontDiv").is(":visible") && $("#smallBackDiv").is(":visible")) {
                    if ($("#captureFront").val().trim() === "") {
                        $("#smallFrontErrorReq").text("Required");
                        $("#smallFrontErrorReq").show();
                        empty = false;
                    }
                    if ($("#captureBack").val().trim() === "") {
                        $("#smallBackErrorReq").text("Required");
                        $("#smallBackErrorReq").show();
                        empty = false;
                    }
                } else {
                    if ($("#frontFileLg").val().trim() === "" && $("#forntFileImgLg").attr("src") === "") {
                        $("#frontErrorReq").text("Required");
                        $("#frontErrorReq").show();
                        empty = false;
                    }
                    if ($("#backFileLg").val().trim() === "" && $("#backFileImgLg").attr("src") === "") {
                        $("#backErrorReq").text("Required");
                        $("#backErrorReq").show();
                        empty = false;
                    }
                }

                if (!empty) {
                    timeOut();
                }

                return empty;
            }
        </script>
    </body>
</html>
