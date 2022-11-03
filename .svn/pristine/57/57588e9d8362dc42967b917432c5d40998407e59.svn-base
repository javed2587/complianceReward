<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:formatDate var="currentyear" pattern="yyyy" value="<%=new java.util.Date()%>"/>
<style>

    #cardImageDiv {
        width: 820px;
        max-height: 600px;
        overflow: hidden;
    }
    #cardImageDiv.rotate90,
    #cardImageDiv.rotate270 {
        width: 600px;
        height: 400px
    }
    #cardImage {
        transform-origin: top left;
        /* IE 10+, Firefox, etc. */
        -webkit-transform-origin: top left;
        /* Chrome */
        -ms-transform-origin: top left;
        /* IE 9 */
    }
    #cardImageDiv.rotate90 #cardImage {
        transform: rotate(90deg) translateY(-100%);
        -webkit-transform: rotate(90deg) translateY(-100%);
        -ms-transform: rotate(90deg) translateY(-100%);
    }
    #cardImageDiv.rotate180 #cardImage {
        transform: rotate(180deg) translate(-100%, -100%);
        -webkit-transform: rotate(180deg) translate(-100%, -100%);
        -ms-transform: rotate(180deg) translateX(-100%, -100%);
    }
    #cardImageDiv.rotate270 #cardImage {
        transform: rotate(270deg) translateX(-100%);
        -webkit-transform: rotate(270deg) translateX(-100%);
        -ms-transform: rotate(270deg) translateX(-100%);
    }

</style>
<!-- Modal Dependents -->
<div id="dependentsModal" class="tableModal modal fade" role="dialog">

</div>
<!--/ Modal Dependents -->

<!-- Modal History -->
<div id="historyModal" class="tableModal modal fade" role="dialog">

</div>
<!--/ Modal History -->

<!-- Modal Insurance Cards-->
<div id="cardsModal" class="imgModal modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="reset()">&times;</button>
                <h4 class="modal-title">Insurance Card (Front)</h4>
            </div>
            <div class="modal-body" id="cardImageDiv">
                <img  alt="card front" title="Click here for full screen" id="cardImage"/>

            </div>
            <div class="image-rotate">
                <img class="rotate-img" src="${pageContext.request.contextPath}/resources/images/plus.png" onclick="zoomImage(1.1, 'cardImage')" />
                <img id="rotateImg" class="rotate-img"  
                     alt="card front" src="${pageContext.request.contextPath}/resources/images/rotate.png" />
                <img class="rotate-img" src="${pageContext.request.contextPath}/resources/images/minus.png" onclick="zoomImage(0.9, 'cardImage')"/>
            </div>




        </div>

    </div>
</div>
<!--/ Modal Insurance Cards -->

<!-- Modal Insurance Detail -->
<div id="insuranceDetailModal" class="listModal modal fade" role="dialog" style="padding-top: 135px;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Insurance Details</h4>
            </div>
            <div class="modal-body">
                <ul class="clearfix">
                    <li><dl><dt>Member ID:</dt><dd>${patientInsuranceDetails.memberID}</dd></dl></li>
                    <li><dl><dt>Group Number:</dt><dd>${patientInsuranceDetails.groupNumber}</dd></dl></li>
                    <li><dl><dt>Plan ID:</dt><dd>${patientInsuranceDetails.planId}</dd></dl></li>
                    <li><dl><dt>Insurance Provider:</dt><dd>${patientInsuranceDetails.insuranceProvider}</dd></dl></li>
                    <li><dl><dt>Provider Address:</dt><dd>${patientInsuranceDetails.providerAddress}</dd></dl></li>
                    <li><dl><dt>Provider Phone:</dt><dd>${patientInsuranceDetails.providerPhone}</dd></dl></li>
                    <li><dl><dt>Expiry Date:</dt><dd><fmt:formatDate pattern="yyyy/dd/MM" value="${patientInsuranceDetails.expiryDate}"/></dd></dl></li>
                </ul>
                <div class="clearfix">
                    <div class="pull-left InsuranceCardFrontModel">
                        <div class="insurancecardfront">Insurance Card Front</div>
                        <img  src="${patientProfile.insuranceFrontCardPath}" alt="insurance card front" class="frontsidecardimg1 insurancecardfront" width="200"/>
                    </div>
                    <div class="InsuranceBackCardModel">
                        <div class="insurancecardback">Insurance Card Back</div>
                        <img src="${patientProfile.insuranceBackCardPath}" alt="insurance card back" class="frontsidecardimg1 insurancecardback" width="200"/>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div> 
<!--/ Modal Insurance Detail -->
<!-- Modal History -->
<div id="programRxModal" class="tableModal modal fade" role="dialog">

</div>
<!--/ Modal History -->
<link href="${pageContext.request.contextPath}/resources/css/portal/dependentshistoryModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

    /////////////////////////////////////////////////////
    var angle = 0;
    var img = document.getElementById('cardImageDiv');
    document.getElementById('rotateImg').onclick = function () {
        angle = (angle + 90) % 360;
        img.className = "rotate" + angle;
    }
    /////////////////////////////////////////////////////    
    function displayModal(id, modalId, type) {
        $.get("${pageContext.request.contextPath}/" + type + "/" + id, function (data) {
            $("#" + modalId).html(data);
        });
        $(document).keypress(function (e) {
            if (e.keyCode == 27) {
                $(".modal").modal('hide');
            }
        });
    }
    window.onload = function () {
        var backImg = $('#backImage').attr('src');
        var frontImg = $('#frontImage').attr('src');
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


    }
    function frontSide(src, title) {
        var modalImg = document.querySelector('.imgModal img');
        modalImg.src = src;
        var modalTitle = document.querySelector('.imgModal .modal-title');
        modalTitle.textContent = title;
        $("#cardsModal").modal('show');
    }
    function fullScreen() {
        var modalImg = document.querySelector('.imgModal img');

        modalImg.style.width = "100%";
        modalImg.style.height = "98%";
        //$("#cardsModal").attr("style","width:100%;");
        $(".modal-dialog").attr("style", "width:100%;height:98%;margin:0;padding:0;");
        $(".modal-content").attr("style", "height:auto;min-height:100%;width:97%;");
        $("#cardsModal").modal('show');
    }
    function reset() {
        $("#cardsModal").modal('hide');
        var modalImg = document.querySelector('.imgModal img');

        modalImg.style.width = "";
        modalImg.style.height = "";
        $(".modal-dialog").attr("style", "");
        $(".modal-content").attr("style", "");

    }
</script>
