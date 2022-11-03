<footer class="footer">
    <div class="container">
        <div class="row">
            <div id="fo-left" class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <b class="pull-left" style="color:#fff; font-weight: normal;">&copy; 2015 All rights reserved</b>
            </div>
            <div id="fo-right" class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <div class="pull-right fpull-right">
                    <a href="#" style="color:#fff;text-decoration:none;"> Terms & Conditions |</a>
                    <a href="#" style="color:#fff;text-decoration:none;">Privacy Policy |</a>
                    <a href="#" style="color:#fff;text-decoration:none;">Return Policy</a>
                </div>
            </div>
        </div>
    </div>
</footer>
<script src="${pageContext.request.contextPath}/resources/media/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrapp.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/PlaceHolderIe.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/media/js/jquery-ui.min.js"></script>
<script type="text/javascript">
    function IsDigit(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode)
                || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 16);

        return ret;
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
        }, 5000);
    }
</script>