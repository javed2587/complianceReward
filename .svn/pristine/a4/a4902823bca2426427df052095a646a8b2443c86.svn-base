<div class="row footer">
    <div class="wrapper">
      <div class="col-sm-12">
        <div class="row">
          <div class="col-lg-4 col-md-6">
            <p>3201 FANNIN LN  l  Suite 200  l  SOUTHLAKE, TX  76092</p>
          </div>
          <div class="col-lg-4 col-md-6 footer_contact">
            <ul>
              <li><i class="fa fa-phone"></i>(888) 495-7271</li>
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

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/PlaceHolderIe.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/media/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.timepicker.js">
	</script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.timepicker.min.js">
	</script>

<script type="text/javascript">


    function timeOut() {
// setTimeout() function will be fired after page is loaded
// it will wait for 5 sec. and then will fire
// $("#successMessage").hide() function
        setTimeout(function() {
            $('#errorMessage').fadeOut('slow');
            $('#message').fadeOut('slow');
            $('.errorMessageValid').fadeOut('slow');
            $('.errorMessageValid1').fadeOut('slow');
            $('.errorMessage').fadeOut('slow');
            $('.error').fadeOut('slow');
        }, 5000);
    }
    function IsDigit(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

        return ret;
    }
    $(document).ready(function() {
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
    $('.isDecimalValue').keypress(function (event) {
        var $this = $(this);
        if ((event.which != 46 || $this.val().indexOf('.') != -1) &&
                ((event.which < 48 || event.which > 57) &&
                        (event.which != 0 && event.which != 8))) {
            event.preventDefault();
        }

        var text = $(this).val();
        if ((event.which == 46) && (text.indexOf('.') == -1)) {
            setTimeout(function () {
                if ($this.val().substring($this.val().indexOf('.')).length > 3) {
                    $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
                }
            }, 1);
        }

        if ((text.indexOf('.') != -1) &&
                (text.substring(text.indexOf('.')).length > 3) &&
                (event.which != 0 && event.which != 8) &&
                ($(this)[0].selectionStart >= text.length - 2)) {
            event.preventDefault();
        }
    });
</script>

 
