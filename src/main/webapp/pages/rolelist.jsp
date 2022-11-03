<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<html lang="en">


    <jsp:include page="./inc/head.jsp" />
    <style type="text/css">
    @media (max-width: 1200px){
.table-box .table {
    width: 100% !important;
}
}
</style>

    <body>

        <jsp:include page="./inc/header.jsp" />

        <script>



            function showhide()

            {

                var div = document.getElementById('roleSearch');

                var img1 = document.getElementById('imgMini');

                var imgM = document.getElementById('max');

                div.style.display = (div.style.display == "none") ? "block" : "none";

                if (div.style.display == "none") {

                    img1.style.display = "none";

                    imgM.style.display = "block";

                } else {

                    img1.style.display = "block";

                    imgM.style.display = "none";

                }

            }

        </script>

        <div id="wrapper">

            <div id="content" class="clearfix">

                <jsp:include page="./inc/newMenu.jsp" />

                <div class="breadcrumbs">

                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Roles

                </div>

                <div class="heading" >

                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Roles</h1>

                </div>

                <form:form action="add" commandName="role">

                    <div class="page-message" style="padding-left: 15px; padding-top: -5px;">

                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>

                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>

                            <br clear="all">

                        </div>



                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">

                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">

                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >

                            </div>

                        <c:if test="${sessionBean.pmap[(3).intValue()].manage eq true}">

                            <div style="float: right;" class="btndrop btn-margin" onclick="location.href = '${pageContext.request.contextPath}/role/add'">

                                <a class="btn_Color" href="${pageContext.request.contextPath}/role/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>

                            </div>  

                        </c:if>

                        <br><br>    

                        <div class="clearfix" style="">

                            <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 58px; padding-top: 7px;">

                                <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>

                                <div class="contents">                                   

                                    <div class="table-box table-responsive">

                                        <table class="display table" id="example" class="display" style="width: 100%;">                                         

                                            <thead>

                                                <tr class="row grid-header">

                                                    <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">

                                                        Role Title

                                                    </th>

                                                    <th class="col-lg-4 col-md-4 col-sm-4 col-xs-4 hidden-sm hidden-xs">

                                                        Created On

                                                    </th>

                                                    <th class="col-lg-2 col-md-1 col-sm-1 hidden-sm hidden-xs " style=" font-weight:normal;">Status</th>

                                                    <th class="col-lg-1 col-md-2 col-sm-2 col-xs-2 " style=" font-weight:normal;text-align: right;padding-right: 44px;">Action</th>

                                                </tr>  

                                            </thead>

                                            <tbody>

                                                <c:forEach var="role" items="${roles}">

                                                    <tr class="row grid-row">												

                                                        <td>${role.roleTitle}</td>

                                                        <td class="hidden-sm hidden-xs">  

                                                            <fmt:formatDate pattern="MM/dd/yyyy" value="${role.createdOn}" />

                                                        </td>

                                                        <td class="hidden-sm hidden-xs" <c:if test="${role.isActive eq 'Yes'}">style="color: #067512;"</c:if><c:if test="${role.isActive eq 'No'}">style="color: #E70000;"</c:if>> 

                                                            ${role.isActive ne 'No' ?'Active':'InActive'}

                                                        </td>

                                                        <td style="text-align: right;padding-right: 34px;">

                                                            <a class="${sessionBean.pmap[(3).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/role/edit/${role.roleId}" style="padding-right: 0px; "><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>

                                                            <a class="${sessionBean.pmap[(3).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeleteRecord('role/delete/${role.roleId}')" style=" padding-left: 7px; "><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>





                                                        </td>                        

                                                    </tr>

                                                </c:forEach>

                                            </tbody>

                                        </table>

                                        <div class="clearfix"></div>

                                    </div>

                                </div>

                            </div>

                        </div> <!-- /wrp -->

                        <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>

                </form:form>

            </div> <!-- /content -->

        </div> <!-- /wrapper -->

        <jsp:include page="./inc/footer.jsp" />

    </body>

</html>

