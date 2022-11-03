<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <div class="container contentsContainer">
            <h2 class="pageTitle">Ingredients Center</h2>
            <hr class="titleBorder"> 
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
                    <c:forEach var="Ic" items="${pageContents}">
                        <c:set var="dcTitle" value="${Ic.cMSPages.title}"/>
                        <c:if test="${fn:containsIgnoreCase(dcTitle, 'Ingredients Center – Content')}">
                            <p style="text-align: center;">${Ic.content}</p>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center margin-top-18">
                <form:form action="${pageContext.request.contextPath}/PharmacyPortal/ingredientSearch" commandName="drugBrand" method="Post">
                    <div class="form-group">
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center" style="text-align: center;">
                            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center" >
                                <div class="input-group col-lg-6 col-sm-6 col-md-12 col-xs-12" style="margin:auto;float:none;">
                                    <form:input path="search" cssClass="form-control" style="font-size: 16px;border-right:0px;" onmouseout="onchab(this.id);" onfocus="changeCls(this.id)"/> 
                                    <div class="input-group-btn">
                                        <button id="btnSearch" type="submit" class="btn btn-default" style="font-size:14px;border-left:0px;"><i class="glyphicon glyphicon-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>  
                    </div> 
                </form:form>
            </div>
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 margin-top-18 table-responsive">
                    <c:choose>
                        <c:when test="${fn:length(redemptionIngredient) > 0}">
                            <table cellspacing="0" cellpadding="0" width="100%" class="ingredientsTable">
                                <thead>
                                    <tr>
                                        <th class="col-md-6 col-lg-6 col-sm-6 col-xs-6">Ingredient Title</th>
                                        <th class="">Packing Size</th>
                                        <th class="">NDC Number</th>
                                        <th class="">WAC Price($)</th>
                                        <th class="">AWP Price($)</th>
                                        <th class="">Max Offer($)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="loop" items="${redemptionIngredient}">
                                        <tr>
                                            <td>${loop.genericName}</td>
                                            <td>
                                                <c:forEach var="drug" items="${loop.drugs}">
                                                    ${drug.strength} ${drug.drugUnits.name}<br>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach var="ndc" items="${loop.drugs}">
                                                    ${ndc.ndcnumber}<br>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach var="wac" items="${loop.drugs}">
                                                    <fmt:formatNumber pattern="0.00" value="${wac.wacPkgPrice}"/><br>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach var="awp" items="${loop.drugs}">
                                                    <fmt:formatNumber pattern="0.00" value="${awp.awpCurrentPkgPrice}"/><br>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach var="mo" items="${loop.drugs}">
                                                    <fmt:formatNumber pattern="0.00" value="${mo.maxOffer}"/><br>
                                                </c:forEach>
                                            </td>
                                        </tr> 
                                    </c:forEach> 
                                </tbody>
                            </table>   

                            <span class="col-sm-12 pull-left" style="text-align: center;color:#2984b8;font-size: 15px;"><div onclick="location.href = '${pageContext.request.contextPath}/PharmacyPortal/ingredients/${fn:length(redemptionIngredient)}'" style="color: #2984b8;cursor: pointer;display: ${fn:length(totalRecords) > fn:length(redemptionIngredient)?'block':'none'}">view more</div></span>
                        </c:when>
                        <c:otherwise>
                            <div style="color: red;">
                                <div style="text-align: center;">${emptyList}</div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>


        </div>
        <jsp:include page="./inc/footer.jsp" />
        <script type="text/javascript">
            function changeCls(id) {
                if ($("#" + id).is(":focus")) {
                    $("#btnSearch").attr("class", "btnfocus btn btn-default");
                } else {
                    $("#btnSearch").attr("class", "btn btn-default");
                }
            }
            function onchab(id) {
                $("#btnSearch").attr("class", "btn btn-default");
                 $('#'+id).blur();
            }

        </script>
    </body>
</html>


