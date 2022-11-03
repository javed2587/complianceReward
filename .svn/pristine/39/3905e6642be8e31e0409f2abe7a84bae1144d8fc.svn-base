<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" >

    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />

        <div class="container contentsContainer" style="background-color:#efefef;margin-top:10px;padding-bottom: 5px;">
            <div class="row">
                <h2 class="pageTitle">WELCOME, PHARMACISTS & STAFF!</h2>
                <hr style="width: 85px; height: 2px; align-items: center; background-color: #b8b8b8; margin: 0 auto 10px;" />
                <c:forEach var="pc" items="${pageContents}">
                    <c:set var="dcTitle" value="${pc.cMSPages.title}"/>
                    <c:if test="${fn:containsIgnoreCase(dcTitle, 'Pharmacy Home – Welcome Pharmacists & Staff')}">
                        <p class="discription" style="text-indent:30px;">${pc.content}</p>
                    </c:if>
                </c:forEach>
            </div>
            <div class="row sperator">
                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 left-devs">
                    <div id="patientrespone" class="background1" style="min-height: 270px;">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="acceptBtnDiv" style="padding-right: 0; padding-left: 0;">
                            <form:form action="${pageContext.request.contextPath}/PharmacyPortal/${path}" commandName="order" method="post">
                                <form:hidden path="orderStatusId" value="1"/>
                                <form:hidden path="type" value="Un-Assigned"/>
                                <div style="float: left;">
                                    <h4 class="innermessage" style="float: left;">Patient Responded Rx's In Queue</h4>&nbsp;<span style="font-size: 12px; color: #2071b6;float: left;padding-left: 2px;">(Recent Order)</span>
                                </div>
                                <div style="float: right; padding-bottom: 4px;">
                                    <button id="acceptBtn" class="btn btn-primary" style="padding: 1px 17px;" type="button" onclick="acceptSelected();" disabled="disabled">
                                        <span>Accept</span>
                                    </button>
                                </div>
                                <input type="hidden" name="orderToBeAccepted"/>
                            </form:form>
                        </div>    
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 phg" style="padding:1px 0px;">
                            <table id="${not empty recentOrderlist ?'recentOrderlist':'recentOrder'}" class="recentOrder col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top: 15px; padding-bottom: 15px; width: 100%;">
                                <thead style="border: none !important;">
                                    <tr>
                                        <th class=""><input type="checkbox" id="selectAll" ${not empty recentOrderlist ? '' : 'disabled'} style="cursor: pointer;"/></th>
                                        <th class="">Trans. #</th>
                                        <th class="">Rx #</th>
                                        <th class="">Order Date</th>
                                        <th class="">AWP</th>
                                        <th class="">Ingred.</th>
                                        <th class="" <c:if test="${empty recentOrderlist}">style="padding-right: 0px;"</c:if>>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:choose>
                                        <c:when test="${not empty recentOrderlist}">
                                            <c:forEach var="row" items="${recentOrderlist}">
                                                <tr>
                                                    <td class=""><input type="checkbox" name="orderSelectedChk" value="${row.id}"/></td>
                                                    <td class=""><a href="${pageContext.request.contextPath}/PharmacyPortal/${path}/${row.id}">${row.id}</a></td>
                                                    <td class="">${row.rxNo}</td>
                                                    <td class=""><fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${row.createOn}"/></td>
                                                    <td class="">$<fmt:formatNumber pattern="0.00" value="${row.GenRxAWP}"/></td>
                                                    <td class="">${row.itemsOrder}&nbsp;<i style="color:#1f4c76;font-size: 22px;font-weight:bold;cursor: pointer" class="fa fa-eye" onclick="showIngredientsPopup('${row.transactionNo}', 'INGREDIENTS');"></i></td>
                                                    <td class=""><span style="color:rgb(222,102,255);">${row.orderStatus.name}</span></td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="7">
                                                    <div style="text-align: center; color: red;">Nothing in queue.</div>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 right-dev">
                    <div id="processedrx" class="background1" style="min-height: 270px;">
                        <h5 class="innermessage" style="float: left; padding-bottom: 5px;">Processed Rx's In Queue</h5>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 phg" style="padding:1px 0px;">
                            <table id="${not empty trackingOrderlist?'trackingOrderlist':'trackingOrder'}" class="recentOrder" style="padding-top: 15px; padding-bottom: 15px;width: 100%;">
                                <thead style="border: none !important;">
                                    <tr>
                                        <th class="">Trans. #</th>
                                        <th class="">Rx #</th>
                                        <th class="">Order Date</th>
                                        <th class="">Comments</th>
                                        <th class="">RPh</th>
                                        <th class="" <c:if test="${empty recentOrderlist}">style="padding-right: 0px;"</c:if>>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:choose>
                                        <c:when test="${not empty trackingOrderlist}">
                                            <c:forEach var="row" items="${trackingOrderlist}">
                                                <tr>
                                                    <td class=""><a href="${pageContext.request.contextPath}/PharmacyPortal/${path}/${row.id}">${row.id}</a></td>
                                                    <td class="">${row.rxNo}</td>
                                                    <td class=""><fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${row.createOn}"/></td>
                                                    <td class="">
                                                        <c:choose>
                                                            <c:when test="${fn:length(row.comments) gt 10}">
                                                                ${fn:substring(row.comments, 0, 9)}&nbsp;<i style="color:#1f4c76;font-size: 18px;font-weight:bold;cursor: pointer" class="fa fa-eye" onclick="showIngredientsPopup('${row.id}', 'comments');"></i> 
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:choose>
                                                                    <c:when test="${empty row.comments}">
                                                                        ---
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${row.comments}
                                                                    </c:otherwise> 
                                                                </c:choose>        
                                                            </c:otherwise>    
                                                        </c:choose>
                                                    </td>
                                                    <td class="">${fn:toUpperCase(row.rph)}</td>
                                                    <td class="">
                                                        <c:if test="${row.orderStatus.id eq 2}">
                                                            <span style="color: #2071b6;text-align: center;">${row.orderStatus.name}</span>
                                                        </c:if>
                                                        <c:if test="${row.orderStatus.id eq 3}">
                                                            <span style="color:rgb(232,108,62);">${row.orderStatus.name}</span>
                                                        </c:if>
                                                        <c:if test="${row.orderStatus.id eq 4}">
                                                            <span style="color: #067512;">${row.orderStatus.name}</span>
                                                        </c:if>
                                                        <c:if test="${row.orderStatus.id eq 5}">
                                                            <span style="color: #E70000;">${row.orderStatus.name}</span>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="6">
                                                    <div style="text-align: center; color: red;">Nothing in queue.</div>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;"></div> 
                <div id="fullPageDialog" style="height: 200px; overflow: auto; background-color: white;display: none;"></div> 
            </div>
            <div class="row sperator">
                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 left-devs">
                    <div class="min-height background1 phqm" id="qm">
                        <h4 class="innermessage"><i class="fa fa-search imageicon"></i> Quality Materials at compitative pricing</h4>
                        <c:forEach var="qm" items="${pageContents}">
                            <c:set var="dcTitle" value="${qm.cMSPages.title}"/>
                            <c:if test="${fn:containsIgnoreCase(dcTitle, 'Pharmacy Home – Quality Materials')}">
                                <p class="messagecontent">${qm.content}</p>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 right-dev">
                    <div class="min-height background1" id="vp">
                        <h4 class="innermessage"><i class="fa fa-bar-chart-o imageicon"></i> Value Proposition</h4>
                        <p class="messagecontent">
                            <c:forEach var="qm" items="${pageContents}">
                                <c:set var="dcTitle" value="${qm.cMSPages.title}"/>
                                <c:if test="${fn:containsIgnoreCase(dcTitle, 'Pharmacy Home – Value Proposition')}">
                                    ${qm.content}
                                </c:if>
                            </c:forEach> 
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <h2 class="wellcomeheading" style="font-size: 14px !important;">Questions about the API-Direct Web Access?</h2>
                <c:forEach var="qa" items="${pageContents}">
                    <c:set var="dcTitle" value="${qa.cMSPages.title}"/>
                    <c:if test="${fn:containsIgnoreCase(dcTitle, 'Pharmacy Home – Question About the API-Direct')}">
                        <p class="discription" style="text-indent:30px;">${qa.content}</p>
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <jsp:include page="./inc/footer.jsp" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables1.10.7.min.js"></script>
        <script type="text/javascript">
                                                                    $(document).ready(function () {
                                                                        var materialcnt = document.getElementById('qm').clientHeight;
                                                                        var patientcnt = document.getElementById('vp').clientHeight;
                                                                        if (materialcnt > patientcnt) {
                                                                            document.getElementById('vp').style.height = materialcnt + 'px';
                                                                        } else if (patientcnt > materialcnt) {
                                                                            document.getElementById('qm').style.height = patientcnt + 'px';
                                                                        }
                                                                        if ($(window).width() === 1280 || $(window).width() === 1920 || $(window).width() === 1366) {
                                                                            if (document.getElementById('processedrx').clientHeight > document.getElementById('patientrespone').clientHeight) {
                                                                                document.getElementById('patientrespone').style.height = document.getElementById('processedrx').clientHeight + 'px';
                                                                                document.getElementById('processedrx').style.height = document.getElementById('processedrx').clientHeight + 'px';
                                                                            } else {
                                                                                document.getElementById('patientrespone').style.height = document.getElementById('patientrespone').clientHeight + 'px';
                                                                                document.getElementById('processedrx').style.height = document.getElementById('patientrespone').clientHeight + 'px';
                                                                            }
                                                                        }
                                                                        $('#trackingOrderlist,#recentOrderlist').dataTable({
                                                                            "scrollY": "220px",
                                                                            "scrollCollapse": true,
                                                                            "paging": false,
                                                                            "bSort": false,
                                                                            "aaSorting": [],
                                                                            "scrollX": true,
                                                                            "bAutoWidth": true,
                                                                            "bFilter": false,
                                                                            "language": {
                                                                                "emptyTable": "<div style='padding-top: 10px; padding-bottom: 10px; color: red;'>Nothing in queue.</div>"
                                                                            }
                                                                        });
                                                                    });
                                                                    function showIngredientsPopup(transactionNo, type) {
                                                                        var title, w;
                                                                        if (type == 'INGREDIENTS') {
                                                                            title = "ORDERED INGREDIENTS";
                                                                            if (window.screen.width > 360) {
                                                                                w = 360;
                                                                            } else {
                                                                                w = $(window).width() * 0.9;
                                                                            }
                                                                        } else {
                                                                            title = "ORDER COMMENTS";
                                                                            if (window.screen.width > 360) {
                                                                                w = 360;
                                                                            } else {
                                                                                w = $(window).width() * 0.9;
                                                                            }
                                                                        }
                                                                        var dialog = $("#dialog").dialog({
                                                                            closeOnEscape: false,
                                                                            autoOpen: false,
                                                                            modal: true,
                                                                            height: 200,
                                                                            width: w,
                                                                            title: title,
                                                                            open: function (event, ui) {
                                                                                //titlebar styling.
                                                                                $(".ui-dialog-titlebar").css("font-size", "14px");
                                                                                $(".ui-dialog-titlebar").css("font-weight", "normal");
                                                                            },
                                                                            close: function () {
                                                                                $(this).dialog('close');
                                                                                $(this).dialog('destroy');
                                                                                $(this).html("");
                                                                            }
                                                                        });
                                                                        if (type == 'INGREDIENTS') {
                                                                            var url = "${pageContext.request.contextPath}/PharmacyPortal/orderingredients/" + transactionNo;
                                                                            $.get(url, function (data) {
                                                                                var html = "<table style='background-color: white; table-layout:fixed; width: 100%;padding-top:5px;padding-bottom:5px;'><tbody>";
                                                                                html += "<tr style='font-size: 12px; border-bottom: 1px solid black; color: #1f4c76;'>";
                                                                                html += "<td style='width:5%;'></td>";
                                                                                html += "<td style='width:30%;'>NDC</td>";
                                                                                html += "<td style='width:65%;'>Product Name</td>";
                                                                                html += "</tr>";
                                                                                $.each(data, function (index, element) {
                                                                                    if (element.ndcMatch === true) {
                                                                                        html += "<tr style='font-size: 12px;'>";
                                                                                    } else {
                                                                                        html += "<tr style='font-size: 12px; color:red;'>";
                                                                                    }
                                                                                    html += "<td style='width:5%;'>";
                                                                                    html += index + 1;
                                                                                    html += ") </td>";
                                                                                    html += "<td style='width: 30%;'>";
                                                                                    if (element.ndc != null) {
                                                                                        html += element.ndc.substr(0, 5) + "-" + element.ndc.substr(5, 4) + "-" + element.ndc.substr(9, element.ndc.length - 9);
                                                                                    }
                                                                                    html += "</td>";
                                                                                    html += "<td style='width: 65%;'>";
                                                                                    html += element.name;
                                                                                    html += "</td>";
                                                                                    html += "</tr>";
                                                                                });
                                                                                html += "</tbody></table>";
                                                                                $(dialog).html(html);
                                                                            });
                                                                        } else {
                                                                            var url = "${pageContext.request.contextPath}/PharmacyPortal/comments/" + transactionNo;
                                                                            $.get(url, function (data) {

                                                                                var html = "<div class='col-lg-12 col-sm-12 col-md-12 col-xs-12' style='padding-left:0px;padding-top:5px;padding-bottom:5px;'>";
                                                                                $.each(data, function (index, element) {
                                                                                    html += "<div id='oc" + index + "'>";
                                                                                    html += "<div style='color: #1f4c76;margin-bottom:-2px;font-size:12px;width:100%;font-weight:bold;'>";
                                                                                    html += formatDate(new Date(element.createdOn));
                                                                                    html += "</div>";
                                                                                    html += "<div style='float:left;font-size:12px;font-weight:bold;'>Status: ";
                                                                                    html += "<div style='float:right;font-weight:normal'>";
                                                                                    html += element.status;
                                                                                    html += "</div>";
                                                                                    html += "</div><br>";
                                                                                    html += "<div style='float:left;font-size:12px;font-weight:bold;position: relative;bottom:10px;'>Comments: ";
                                                                                    html += "<div style='float:right;font-weight:normal;'>";
                                                                                    html += element.comments;
                                                                                    html += "</div>";
                                                                                    html += "</div><br>";
                                                                                    html += "</div>";
                                                                                });
                                                                                html += "</div>";
                                                                                $(dialog).html(html);
                                                                            });
                                                                        }
                                                                        $(dialog).dialog("open");
                                                                    }

                                                                    $("#selectAll").change(function () {
                                                                        $("input[name='orderSelectedChk']").prop('checked', $(this).prop("checked"));
                                                                    });
                                                                    function acceptSelected() {
                                                                        $("#fullPageDialog").css("width", "100%");
                                                                        var dialog = $("#fullPageDialog").dialog({
                                                                            closeOnEscape: false,
                                                                            autoOpen: false,
                                                                            modal: true,
                                                                            width: '100%',
                                                                            open: function (event, ui) {
                                                                                //hide titlebar.
                                                                                $(".ui-dialog-titlebar").hide();
                                                                            },
                                                                            close: function ()
                                                                            {
                                                                                $(this).dialog('close');
                                                                                $(this).dialog('destroy');
                                                                                $(this).html("");
                                                                            }
                                                                        });

                                                                        var confirmAcceptHtml = "<div style='text-align: center; margin-top: 20px;'>"
                                                                                + "<p style='margin-bottom: 20px;'>Are you sure, you want to accept selected order(s)?</p>"
                                                                                + "<div style='float: left; width: 49%; text-align: right; font-size: 14px;'>"
                                                                                + "<button id='confirmBtn' style='width: 120px;' class='btn btn-primary' type='button' onclick='acceptOrderConfirm();'>Continue</button>"
                                                                                + "</div>"
                                                                                + "<div style='float: right; width: 49%; text-align: left; margin-bottom: 20px; font-size: 14px;'>"
                                                                                + "<button style='width: 120px;' class='btn btn-primary' type='button' onclick='acceptOrderCancel();'>Cancel</button>"
                                                                                + "</div>"
                                                                                + "</div>";
                                                                        $(dialog).html(confirmAcceptHtml);
                                                                        $(dialog).dialog("open");
                                                                        $("#confirmBtn").blur();
                                                                    }

                                                                    function acceptOrderConfirm() {
                                                                        var data = "";
                                                                        $("input[name='orderSelectedChk']").each(function (index, element) {
                                                                            if (element.checked) {
                                                                                data += $(element).val();
                                                                                data += ",";
                                                                            }
                                                                        });
                                                                        data = data.substr(0, data.length - 1);
                                                                        $("input[name='orderToBeAccepted']").val(data);

                                                                        $("#order").prop("action", "${pageContext.request.contextPath}/PharmacyPortal/acceptall");
                                                                        $("#order").submit();
                                                                    }

                                                                    function acceptOrderCancel() {
                                                                        $("#fullPageDialog").dialog("close");
                                                                        $("input[type='checkbox']").prop('checked', '');
                                                                        $("#acceptBtn").prop("disabled", true);
                                                                    }
                                                                    $("input[type='checkbox']").on("change", function () {
                                                                        if ($("input:checkbox:checked").length > 0) {
                                                                            $("#acceptBtn").prop("disabled", false);
                                                                        } else {
                                                                            $("#acceptBtn").prop("disabled", true);
                                                                        }
                                                                    });

        </script>
    </body>
</html>
