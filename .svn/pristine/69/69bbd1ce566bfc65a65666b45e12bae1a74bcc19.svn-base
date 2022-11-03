<div class="row footer stickyFooter">
    <div class="wrapper">
        <div class="col-sm-12">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <p>3201 FANNIN LN  l  Suite 200  l  SOUTHLAKE, TX  76092</p>
                </div>
                <div class="col-lg-4 col-md-6 footer_contact">
                    <ul>
                        <li><i class="fa fa-phone"></i>313 633 0165</li>
                        <li><a href="mailto:info@ComplianceReward.us"><i class="fa fa-envelope-o"></i>info@ComplianceReward.us</a></li>
                    </ul>
                </div>
                <div class="col-lg-4 col-md-12 footer_privcy">
                    <p>© 2019 <strong>Compliance Reward.</strong> All rights reserved.<a href="#">Privacy Policy</a></p>
                </div>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">


    function timeOut() {
// setTimeout() function will be fired after page is loaded
// it will wait for 5 sec. and then will fire
// $("#successMessage").hide() function
        setTimeout(function () {
            $('#errorMessage').fadeOut('slow');
            $('#message').fadeOut('slow');
            $('.errorMessageValid').fadeOut('slow');
            $('.errorMessageValid1').fadeOut('slow');
            $('.errorMessage').fadeOut('slow');
            $('.error').fadeOut('slow');
            $('#errorSendMessage').fadeOut('slow');
        }, 5000);
    }
    function IsDigit(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

        return ret;
    }
    function IsCommaSeparatedNumbers(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 44);

        return ret;
    }
     function IsNumbersWithDecimal(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 46);

        return ret;
    }
    function resetMultiRxForm() {
//       alert("hi");
       document.getElementById("multiRadioGCN").checked = true;
       window.drugestimateprice.resetMultiRxForm();
       
    }
    $(document).ready(function () {
        timeOut();
        if (window.screen.width === 1366) {
            $('#buttonrigisteration').attr('style', 'text-align: center;right:6px !important;');
            $('#btnlogin').attr('style', 'margin-top: -3% !important;');
        }
        if (window.screen.width === 1280) {
            $('#loginBtn').attr('class', 'btnlogin1280 btn btn-primary');
        } else {
            $('#loginBtn').attr('class', 'btn btn-primary btnlogin');
        }
    });
    function IsDigit(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

        return ret;
    }
    function float_validation(event, value) {
    
        if (event.which == 8 || event.which == 0) {
            return true;
            event.preventDefault();
        }
        if (event.which < 45 || event.which > 58 || event.which == 47) {
            return false;
            event.preventDefault();
        } // prevent if not number/dot

        if (event.which == 46 && value.indexOf('.') != -1) {
            return false;
            event.preventDefault();
        } // prevent if already dot

//        if (event.which == 45 && value.indexOf('-') != -1) {
//            return false;
//            event.preventDefault();
//        } // prevent if already dash

        if (event.which == 45) {
            event.preventDefault();
            return false;
        } // prevent if already -

        return true;

    }
    ;
    function allowOnlyNumber(evt)
    {
        var charCode = (evt.which) ? evt.which : evt.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    }
    function formatDate(date) {
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var ampm = hours >= 12 ? 'PM' : 'AM';
        hours = hours % 12;
        hours = hours ? hours : 12; // the hour '0' should be '12'
        minutes = minutes < 10 ? '0' + minutes : minutes;
        var strTime = hours + ':' + minutes + ampm;
        return date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear() + " " + strTime;
    }
    var specialKeys = new Array();
    //specialKeys.push(8); //Backspace
    //specialKeys.push(9); //Tab
    specialKeys.push(46); //Delete
    specialKeys.push(36); //Home
    specialKeys.push(35); //End
    specialKeys.push(37); //Left
    specialKeys.push(39); //Right
    function IsAlphaNumeric(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 32 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

        return ret;
    }
    function IsAlphaNumericWithHyphen(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 32 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16 || keyCode == 45);

        return ret;
    }
    function onlyAlphabets(e, t) {
        try {
            if (window.event) {
                var charCode = window.event.keyCode;
            } else if (e) {
                var charCode = e.which;
            } else {
                return true;
            }
            
            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode === 32 || charCode === 8 || charCode === 45 || charCode===0)
                return true;
            else
                return false;
        } catch (err) {
            alert(err.Description);
        }
    }
    function IsDigitWithHyphen(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16 || keyCode == 45
                || keyCode == 40 || keyCode == 41);

        return ret;
    }
    //////////////////////////////////////////////////
    var zoomLevelOneVar = 100;
    var maxZoomLevelOneVar = 108;
    var minZoomLevelOneVar = 95;

    function zoomImage(zm,imgId) {
                          var img=document.getElementById(imgId);
                          if(zm > 1)
                          {
                                            if(zoomLevelOneVar < maxZoomLevelOneVar){
                                                zoomLevelOneVar++;
                                            }else{
                                                return;
                                            }
                                        }else if(zm < 1){
                                            if(zoomLevelOneVar > minZoomLevelOneVar){
                                                zoomLevelOneVar--;
                                            }else{
                                                return;
                                            }
                                        }
                                        wid = img.width;
                                        ht = img.height;
                                        img.style.width = (wid*zm)+"px";
                                        img.style.height = (ht*zm)+"px";
                                    //    img.style.marginLeft = -(img.width/2) + "px";
                                    //    img.style.marginTop = -(img.height/2) + "px";
                                    }
                                    
                                    

    /////////////////////////////////////////////////
</script>


