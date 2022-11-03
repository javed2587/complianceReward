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

        <div id="wrapper">

            <div id="content" class="clearfix">

                <jsp:include page="./inc/newMenu.jsp" />

                <div class="breadcrumbs">

                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Users

                </div>

                <div class="heading" >

                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Users</h1>

                </div>

                <form:form action="add" commandName="user">

                    <div class="page-message" style="padding-left: 15px; padding-top: -5px;">

                        <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>

                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>

                            <br clear="all">

                        </div>



                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px;">

                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">

                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >

                            </div>

                        <c:if test="${sessionBean.pmap[(5).intValue()].manage eq true}">

                            <div style="float: right;" class="btndrop" onclick="location.href = '${pageContext.request.contextPath}/user/add'">

                                <a class="btn_Color" href="${pageContext.request.contextPath}/user/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>

                            </div> 

                        </c:if> 

                        <br>    

                    </div>



                    <div class="clearfix" style="padding-left: 15px;padding-right: 15px;">

                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:58px; padding-top: 5px;">



                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>

                            <div class="contents">                                   

                                <div class="table-box table-responsive">

                                    <table class="display table" id="example" class="display" style="width: 100%;">                                         

                                        <thead style="border-top:1px solid #cdcdce;height:35px;">

                                            <tr class="row grid-header">  																									

                                                <th class="sorting" >User Name</th>

                                                <th class="sorting">First Name&nbsp;</th>

                                                <th class="hidden-xs" >Last Name&nbsp;</th>

                                                <th class="hidden-sm hidden-xs" >Role&nbsp;</th>  

                                                <th class="hidden-sm hidden-xs">Start Date&nbsp;</th>

                                                <th class="hidden-xs">Status&nbsp;</th>

                                                <th style="text-align: right; position: relative; right: 34px;">Action</th>

                                            </tr>

                                        </thead>

                                        <tbody>

                                            <c:forEach var="user" items="${users}">

                                                <tr class="row grid-row">												

                                                    <td class="">${user.userName}</td>

                                                    <td class="">${user.firstName}</td>

                                                    <td class="hidden-xs">${user.lastName}</td>

                                                    <td class="hidden-sm hidden-xs">

                                                        ${user.roleTypes.roleTitle}

                                                    </td>

                                                    <td class="hidden-sm hidden-xs ">

                                                        <fmt:formatDate pattern="MM/dd/yyyy" value="${user.userStartDate}" />

                                                    </td>

                                                    <td class="hidden-xs" <c:if test="${user.isActive eq 'Yes'}">style="color: #067512;"</c:if><c:if test="${user.isActive eq 'No'}">style="color: #E70000;"</c:if>>

                                                        ${user.isActive ne 'No' ?'Active':'InActive'}

                                                    </td>

                                                    <td class="useraction">

                                                        <a id="editImag" class="${sessionBean.pmap[(5).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/user/edit/${user.userId}/${user.isAdmin}" ><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="18"/></a>&nbsp;

                                                        <a class="${sessionBean.pmap[(3).intValue()].manage eq true?'':'disabled'}" href="#" onclick="isDeleteRecord('user/delete/${user.userId}/${user.isAdmin}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>&nbsp;

                                                        <a class="${sessionBean.pmap[(5).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/user/changepassword/${user.userId}/${user.isAdmin}"><img src="${pageContext.request.contextPath}/resources/images/Change_Password.jpg" width="20"/></a>

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

