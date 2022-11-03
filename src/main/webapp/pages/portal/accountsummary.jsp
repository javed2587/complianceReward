<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div class="container contentsContainer accountsummary" style="min-height: 0 !important;">
            <h2 class="pageTitle">ACCOUNT SUMMARY</h2>
            <hr class="titleBorder"> 
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
                    <c:forEach var="accountContent" items="${pageContents}">
                        <c:set var="dcTitle" value="${accountContent.cMSPages.title}"/>
                        <c:if test="${fn:containsIgnoreCase(dcTitle, 'Account Summary – Content')}">
                            <p style="text-align: center;">${accountContent.content}</p>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="row sperator">
                <div id="accountSummarySearchBarDiv" class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="background: #d9d9d9 none repeat scroll 0 0; padding: 10px; min-height: 55px;">
                    
                        <form:form cssClass="form-inline" method="get" action="${pageContext.request.contextPath}/PharmacyPortal/accountsummary" commandName="order">
                            <div class="col-lg-3 col-sm-12 col-md-12 col-xs-12" style="padding-left: 0; padding-right: 0;">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        Search by
                                    </div>
                                    <form:input path="orderNo" cssClass="form-control" placeholder="Enter Rx #" onkeypress="return IsAlphaNumeric(event)" maxlength="15"/>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-12 col-md-12 col-xs-12" style="padding-bottom: 23px;" id="dateRangeLinkContainer">
                                <div class="col-lg-6 col-sm-6 col-md-6 col-xs-6" style="font-size: 12px;">
                                    <a id="past7DaysLink" href="#" cssClass="form-control" onclick="searchOrder('7')">Past 7 Days</a>
                                    <form:hidden path="daysBack"/>
                                </div>
                                <div class="col-lg-6 col-sm-6 col-md-6 col-xs-6" style="font-size: 12px;">
                                    <a id="past30DaysLink" href="#" cssClass="form-control" onclick="searchOrder('30')">Past 30 Days</a><br/>
                                </div>
                            </div>
                            <div class="col-lg-2 col-sm-12 col-md-12 col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        From
                                    </div>
                                    <form:input id="datetimepicker" path="fromDate" cssClass="form-control" placeholder="Order From Date" onkeyup="addSlashes(this)" onkeypress="return IsDigit(event)"/>
                                </div>
                            </div> 
                            <div class="col-lg-2 col-sm-12 col-md-12 col-xs-12" style="padding-right: 0px; padding-left: 0;">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        To
                                    </div>
                                    <form:input id="datetimepicker1" path="toDate" cssClass="form-control" placeholder="Order To Date" onkeyup="addSlashes(this)" onkeypress="return IsDigit(event)"/>
                                </div>
                            </div>
                            <div class="col-lg-2 col-sm-12 col-md-12 col-xs-12" id="accountSummaryBtnContainer">
                                <button type="button" class="btn btn-primary" onclick="searchOrder();"><i class="fa fa-search"></i> Search</button>
                                <button type="submit" class="btn btn-primary"><i class="fa fa-eraser"></i> Clear</button>
                            </div>
                        </form:form>
                    
                </div>
            </div>
            <div class="row sperator">
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 mobileOverflow" style="padding: 0;">
                    <table id="example" class="recentOrder" style="min-width: 1200px;" cellpadding="10" cellspacing="10">
                        <thead>
                            <tr>
                                <th style="width: 8%;">Order Date</th>
                                <th style="width: 7%;">Trans. #</th>
                                <th style="width: 6%;">Rx #</th>
                                <th style="width: 8%;">Patient Name</th>
                                <th style="width: 5%;">Ingredients</th>
                                <th style="width: 8%;">Amt Assistance</th>
                                <th style="width: 6%;">AWP Cost</th>
                                <th style="width: 9%;">Ins. Reimbursed</th>
                                <th style="width: 6%;">Ins. Quality</th>
                                <th style="width: 9%;">Pt. Remittance</th>
                                <th style="width: 9%;">Date Collected</th>
                                <th style="width: 6%;">Comments</th>
                                <th style="width: 5%;">RPh</th>
                                <th style="width: 8%;">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${orderList}">
                                <c:if test="${list.orderStatus.name ne 'Un-Assigned'}">
                                <tr>
                                    <td style="width: 8%;">
                                        <c:forEach var="history" items="${list.orderHistory}" varStatus="loop">
                                            <c:if test="${loop.last && history.orderStatus.id eq list.orderStatus.id}">
                                                <fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${history.createdOn}"/>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td style="width: 7%;">${list.id}</td>
                                    <td style="width: 6%;">${list.rxNo}</td>
                                    <td style="width: 8%;">${fn:toUpperCase(fn:substring(list.firstName, 0, 1))} <c:set var="lastname" value="${list.lastName}" />${lastname}</td>
                                    <td style="width: 5%;">${list.itemsOrder}&nbsp;<i style="color:#1f4c76;font-size: 18px;font-weight:bold;cursor: pointer" class="fa fa-eye" onclick="showIngredientsPopup('${list.transactionNo}', 'INGREDIENTS');"></i></td>
                                    <td style="width: 8%;">$<fmt:formatNumber pattern="0.00" value="${list.copay}"/></td>
                                    <td style="width: 6%;">$<fmt:formatNumber pattern="0.00" value="${list.GenRxAWP}"/></td>
                                    <td style="width: 9%;">$<fmt:formatNumber pattern="0.00" value="${list.dailyRedemption.planPharmacyAmount}"/></td>
                                    <td style="width: 6%;">${list.quality}</td>
                                    <td style="width: 9%;">$<fmt:formatNumber pattern="0.00" value="${list.payment}"/></td>
                                    <td style="width: 9%;">
                                        <c:forEach var="history" items="${list.orderHistory}" varStatus="loop">
                                            <c:if test="${loop.last && history.orderStatus.id eq list.orderStatus.id}">
                                                <fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${history.createdOn}"/>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td style="width: 6%;"><i style="color:#1f4c76;font-size: 18px;font-weight:bold;cursor: pointer;" class="fa fa-eye" onclick="showIngredientsPopup('${list.id}', 'comments');"></i></td>
                                    <td style="width: 5%;">${fn:toUpperCase(list.rph)}</td>
                                    <td style="width: 8%;">
                                        <c:if test="${list.orderStatus.id eq 2}">
                                            <span style="color: #2071b6;">${list.orderStatus.name}</span>
                                        </c:if>
                                        <c:if test="${list.orderStatus.id eq 3}">
                                            <span style="color:rgb(232,108,62);">${list.orderStatus.name}</span>
                                        </c:if>
                                        <c:if test="${list.orderStatus.id eq 4}">
                                            <span style="color: #067512;">${list.orderStatus.name}</span>
                                        </c:if>
                                        <c:if test="${list.orderStatus.id eq 5}">
                                            <span style="color: #E70000;">${list.orderStatus.name}</span>
                                        </c:if>
                                    </td>
                                </tr>
                                </c:if>
                            </c:forEach>

                        </tbody>
                    </table>
                </div> 
            </div>
            <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;"></div>
            <div id="fullPageDialog" style="overflow: auto; background-color: white;display: none;"></div>
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12" style="text-align: center;">
                <c:forEach var="accounthelp" items="${pageContents}">
                    <c:set var="dcTitle" value="${accounthelp.cMSPages.title}"/>
                    <c:if test="${fn:containsIgnoreCase(dcTitle, 'Account Summary – Help')}">
                        ${accounthelp.content}
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables1.10.7.min.js"></script>
        <script type="text/javascript">
                                            $(window).load(function () {
                                                $('#example').dataTable({
                                                    "scrollY": "301px",
                                                    "scrollCollapse": true,
                                                    "paging": false,
                                                    "bSort": false,
                                                    "aaSorting": [],
                                                    "scrollX": true,
                                                    "autoWidth": true,
                                                    "language": {
                                                        "emptyTable": "<div style='padding-top: 10px; padding-bottom: 10px; color: red;'>Nothing in queue.</div>"
                                                    }
                                                });
                                                $('#example_length').attr('style', 'display: none;');
                                                $('#example_filter').attr('style', 'display: none;');
                                                if(window.screen.width===1280 || window.screen.width===1920 || window.screen.width===1366){
                                                    $('#dateRangeLinkContainer').attr('style','padding-top: 7px;');
                                                }else{
                                                     $('#dateRangeLinkContainer').attr('style','padding-top: 7px; padding-bottom: 23px;');
                                                }
                                            });

                                            $('#datetimepicker, #datetimepicker1').datetimepicker({timepicker: false,
                                                format: 'm/d/Y',
                                                onChangeDateTime: function (dp, $input) {
                                                    jQuery('#datetimepicker').datetimepicker('hide');
                                                    jQuery('#datetimepicker1').datetimepicker('hide');
                                                }

                                            });
                                            function addSlashes(input) {
                                                var v = input.value;
                                                if (v.match(/^\d{2}$/) !== null) {
                                                    input.value = v + '/';
                                                } else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
                                                    input.value = v + '/';
                                                }
                                            }
                                            function hideAccountOverView(id, cls) {
                                                if ($('.' + cls).is(':visible')) {
                                                    $("." + id + ">i").prop('class', 'fa fa-plus-circle');
                                                    $("." + cls).css({display: "none"});
                                                } else {
                                                    $("." + id + ">i").prop('class', 'fa fa-minus-circle');
                                                    $("." + cls).css({display: "block"});
                                                }
                                            }

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
                                                        $(".ui-dialog-titlebar").show();
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
                                                if (type == 'INGREDIENTS') {
                                                    var url = "${pageContext.request.contextPath}/PharmacyPortal/orderingredients/" + transactionNo;
                                                    $.get(url, function (data) {
                                                        var html = "<table style='background-color: white; table-layout:fixed; width: 100%;padding-top:5px;padding-bottm:5px;'><tbody>";
                                                        html += "<tr style='font-size: 12px; border-bottom: 1px solid black; color: #1f4c76;'>";
                                                        html += "<td style='width:5%;'></td>";
                                                        html += "<td style='width:30%;'>NDC</td>";
                                                        html += "<td style='width:65%;'>Product Name</td>";
                                                        html += "</tr>";
                                                        $.each(data, function (index, element) {
                                                            if(element.ndcMatch === true){
                                                                html += "<tr style='font-size: 12px;'>";
                                                            } else {
                                                                html += "<tr style='font-size: 12px; color: red;'>";
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

                                                        var html = "<div class='col-lg-12 col-sm-12 col-md-12 col-xs-12' style='padding-left:0px;padding-top:5px;padding-bottm:5px;'>";
                                                        $.each(data, function (index, element) {

                                                            html += "<div id='oc" + index + "'>";
                                                            html += "<div style='color: #1f4c76;margin-bottom:-2px;font-size:12px;width:100%;font-weight:bold;'>";
                                                            html += formatDate(new Date(element.createdOn));
                                                            html += "</div>";
                                                            html += "<div style='float:left;font-size:12px;font-weight:bold;'>Status: ";
                                                            html += "<div style='float:right;font-size:12px;font-weight:normal;'>";
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

                                            function searchOrder(daysBack) {
                                                var fromDate = $("#datetimepicker").val();
                                                var toDate = $("#datetimepicker1").val();

                                                if (fromDate != '') {
                                                    if (toDate == '') {
                                                        invalidDateRange("Please select 'To Date' before proceeding.");
                                                        return;
                                                    } else {
                                                        if (new Date(fromDate) > new Date(toDate)) {
                                                            invalidDateRange("'From Date' should be less than 'To Date'.");
                                                            return;
                                                        }
                                                    }
                                                } else {
                                                    $("#datetimepicker1").val("");
                                                }

                                                if (daysBack != '') {
                                                    $("#daysBack").val(daysBack);
                                                }

                                                $("#order").attr("method", "post");
                                                $("#order").attr("action", "${pageContext.request.contextPath}/PharmacyPortal/searchOrder");
                                                $("#order").submit();
                                            }

                                            function invalidDateRange(message) {
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

                                                var alertHtml = "<div style='text-align: center; margin-top: 20px;'>"
                                                        + "<p style='margin-bottom: 20px;'>" + message + "</p>"
                                                        + "<div style='width: 100%; font-size: 14px;'>"
                                                        + "<button id='closeBtn' style='width: 120px;' class='btn btn-primary' type='button' onclick='closeDialog();'>Close</button>"
                                                        + "</div>"
                                                        + "</div>";
                                                $(dialog).html(alertHtml);
                                                $(dialog).dialog("open");
                                            }

                                            function closeDialog() {
                                                $("#fullPageDialog").dialog("close");
                                            }
        </script>
    </body>
</html>

