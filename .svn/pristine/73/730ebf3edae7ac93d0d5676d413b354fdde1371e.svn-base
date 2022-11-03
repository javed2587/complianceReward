<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />

    <body>
        <jsp:include page="./inc/header.jsp" />

        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Drug Compound Setup

                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Drug Compound</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="drug">
                    <div class="page-message messageheading">
                        <c:if test="${not empty message}"><div class="message" id="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div class="errorMessage" id="errorMessage">${errorMessage}</div></c:if>
                        </div>

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px;">
                            <div style="margin-top: 1px;" class="btn addbtn" onclick="location.href = '${pageContext.request.contextPath}/drugIngredient/add'">
                            <a class="btn_Color" href="${pageContext.request.contextPath}/drugIngredient/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                        </div>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:55px;">
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
                                                <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                    Drug Title
                                                </th>
                                                <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs">
                                                    Compound Item
                                                </th>
                                                <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                    NDC Number
                                                </th>
                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden-sm hidden-xs">
                                                    Cost($)
                                                </th>
                                                <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                    Min. Offer($)
                                                </th>
                                                <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                    Max. Offer($)
                                                </th>
                                                <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-right">
                                                    <span style="margin-right: 25px;">Action</span>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${drugIngredientList}">
                                                <tr class="row grid-row">
                                                    <td valign="top">
                                                        <span id="tbl_textalign">${list.drugBrand.brandName} (${list.strength})</span>
                                                    </td>
                                                    <td valign="middle">
                                                        <c:forEach var="drgIngredient" items="${list.drugIngredients}" varStatus="loop">
                                                            <span id="tbl_textalign">${drgIngredient.ingredients.name} (${drgIngredient.percentage}%)</span><br>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="hidden-sm hidden-xs" valign="middle">
                                                        <span id="tbl_textalign">
                                                            <c:forEach var="drgNdcNo" items="${list.drugIngredients}" varStatus="loopNdc">
                                                                <span id="tbl_textalign">${drgNdcNo.ndcNo}</span><br>
                                                            </c:forEach>
                                                        </span>
                                                    </td>
                                                    <td class="hidden-sm hidden-xs" valign="middle">
                                                        <c:forEach var="drgCost" items="${list.drugIngredients}" varStatus="loopCost">
                                                            <span id="tbl_textalign">${drgCost.cost}</span><br>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="hidden-sm hidden-xs" valign="middle">
                                                        <c:forEach var="drgMinOffer" items="${list.drugIngredients}" varStatus="loopMinOffer">
                                                            <span id="tbl_textalign">${drgMinOffer.minOffer}</span><br>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="hidden-sm hidden-xs" valign="middle">    
                                                        <c:forEach var="drgMaxOffer" items="${list.drugIngredients}" varStatus="loopMaxOffer">
                                                            ${drgMaxOffer.maxOffer}<br>
                                                        </c:forEach>

                                                    </td>

                                                    <td class="text-right">
                                                        <div class="btn-group">
                                                            <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-eye">&nbsp;&nbsp;<span class="caret"></span></i></a>
                                                            <ul class="dropdown-menu">
                                                                <li><a href="${pageContext.request.contextPath}/drugIngredient/edit/${list.drugId}">Edit</a></li> 
                                                            </ul>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>


                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript">
                                $(function() {
                                    // setTimeout() function will be fired after page is loaded
                                    // it will wait for 5 sec. and then will fire
                                    // $("#successMessage").hide() function
                                    setTimeout(function() {
                                        $("#errorMessage").hide('blind', {}, 500)
                                        $("#message").hide('blind', {}, 500)
                                    }, 5000);
                                });
        </script>
    </body>
</html>



