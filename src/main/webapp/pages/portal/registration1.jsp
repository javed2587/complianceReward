<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <jsp:include page="./inc/head.jsp"/>
    <body>
        <jsp:include page="./inc/header.jsp"/>
        <form:form action="${pageContext.request.contextPath}/ConsumerPortal/lookup" commandName="pharmacyLookup" role="form" method="Post" class="form-inline pharmacyRegistration" style="padding-bottom: 10px;padding-top: 0px;">
            <div class="container contentsContainer">
                <h2 class="pageTitle">PHARMACY REGISTRATION</h2>
                <hr class="titleBorder"> 
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                    <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 aboutusinfo" style='text-align: center;'>
                        <c:forEach var="pc" items="${pageContents}">
                            <c:set var="dcTitle" value="${pc.cMSPages.title}"/>
                            <c:if test="${fn:containsIgnoreCase(dcTitle, 'Pharmacy Registration – Search')}">
                                <p style="text-align: center;">${pc.content}</p>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center removebootstrappadding" style="padding-top: 10px;">



                    <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12" style="padding-bottom: 10px;">
                        <label class="sr-only" for="NPI"></label>
                        <form:input class="form-control" id="NPI" path="npi" placeholder="Pharmacy NPI (as on your license)" autofocus="true" maxlength="10"/>
                    </div>


                    <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12" style="padding-bottom: 10px;">
                        <label class="sr-only" for="sitePass"></label>
                        <form:input class="form-control" id="sitePass" path="passNumber" placeholder="Site Pass ID" maxlength="10" onkeypress="return IsAlphaNumeric(event)"/>
                    </div>
                    <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12" style="padding-bottom: 10px;">
                        <label class="sr-only" for="selectPharmacy"></label>
                        <form:select class="form-control" id="selectPharmacy" path="pharmacyType.id">
                            <option value="0">Pharmacy Type</option>
                            <form:options items="${pharmacyTypeList}" itemLabel="title" itemValue="id"/>
                        </form:select>
                    </div>
                    <div class="padd-bottom-10 col-lg-1 col-md-12 col-sm-12 col-xs-12" style="text-align: center;right:10px;" id="buttonrigisteration">
                        <button type="submit" class="btn btn-primary" style="">Search</button>
                    </div>



                    <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                        <div class="serchResult">
                            <div class="col-lg-11 col-md-11 col-sm-11 col-sm-12 col-xs-10 removebootstrappadding">
                                <input type="hidden" id="title" value="${pharmacyLookup.title}"/>
                                <c:if test="${empty message && empty pharmacyLookup.title && not pharmacyLookup.pharmacyExist}">
                                    <h3 class="blueHeading" style="font-weight: normal;text-align: center;padding-top: 15px;">Please use the above form to search for your pharmacy.</h3> 
                                </c:if>
                                <c:if test="${not empty message}">
                                    <h3 class="blueHeading" style="font-weight: normal; color: red !important;text-align: center;padding-top: 15px;">${message}</h3> 
                                </c:if>
                                <c:if test="${not empty pharmacyLookup.title}"> 
                                    <c:choose>
                                        <c:when test="${not pharmacyLookup.pharmacyExist}">
                                            <h3 class="blueHeading">${pharmacyLookup.title}</h3> 
                                            <p class="descriptionText">${pharmacyLookup.address}, ${pharmacyLookup.city}, ${pharmacyLookup.state.abbr} ${pharmacyLookup.zip}</p>
                                        </c:when>
                                        <c:otherwise>
                                            <h3 class="blueHeading" style="font-weight: normal;text-align: center;padding-top: 15px;">Account is already created for this pharmacy. Please login with provided credentials.</h3> 
                                        </c:otherwise>
                                    </c:choose>

                                    <form:hidden path="id"/>
                                    <form:hidden path="title"/>
                                    <form:hidden path="address"/>
                                    <form:hidden path="city"/>
                                    <form:hidden path="state.id"/>
                                    <form:hidden path="state.abbr"/>
                                    <form:hidden path="zip"/>
                                    <form:hidden path="pharmacyType.title"/>
                                    <form:hidden path="pharmacyType.id"/>
                                    <form:hidden path="salesRep"/>
                                </c:if>
                            </div>
                        </div>
                        <!--<div>
                            <label class="radio-inline">
                                <input type="radio" value="" />
                                <span class="blueHeading">SCRIPT N SAVE PHARMACY # 25</span> 
                                <span class="descriptionText">16986 S Belleflower Place, Conshohocken, PA 19428</span>
                            </label>
                        </div>-->
                    </div>
                    <span class="pull-left col-lg-12 col-sm-12 col-md-12 col-xs-12 margin-top-10" >
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 " style="text-align: center;">
                            <c:choose>
                                <c:when test="${not pharmacyLookup.pharmacyExist}">
                                    <button type="button" onclick="continueRegistration();" class="btn btn-primary" ${empty pharmacyLookup.title ? 'disabled' : ''}>CONTINUE</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" onclick="window.location = '${pageContext.request.contextPath}/PharmacyPortal/login'" class="btn btn-primary">LOGIN</button>
                                </c:otherwise>
                            </c:choose>

                            <button type="button" class="btn btn-primary" type="button" onclick="window.location = '${pageContext.request.contextPath}/PharmacyPortal/login'">NOT NOW</button>
                        </div>
                    </span>
                </div>



            </div>
        </form:form> 

        <jsp:include page="./inc/footer.jsp"/>
        <script>
            function continueRegistration() {
                $("#pharmacyLookup").prop('action', "${pageContext.request.contextPath}/ConsumerPortal/continueRegistration");
                $("#pharmacyLookup").submit();
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
        </script>
    </body>
</html>
