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
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Interval Setup

                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Intervals</h1>

                </div> <!-- /header -->


                <form:form action="add" commandName="intervalsType">
                    <div class="page-message messageheading" style="padding-top: 3px;">
                        <c:if test="${not empty message}"><div id="message"  class="message">${message}</div></c:if>
                        <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                        </div>    

                        <div class="wrp clearfix" style="padding-left: 15px;padding-right: 15px; padding-top: 20px; margin-bottom:40px;">
                            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" style="padding-left: 0px;">
                                <input autocomplete="off" type="text" class="form-control" id="searchTitle_1" placeholder="Search" name="searchTitle_1" >
                            </div>
                        <c:if test="${sessionBean.pmap[(13).intValue()].manage eq true}">
                            <div class="btndrop" style="float: right;margin-top: 1px;" onclick="location.href = '${pageContext.request.contextPath}/intervalsType/add'">
                                <a class="btn_Color" href="${pageContext.request.contextPath}/intervalsType/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                            </div> 
                        </c:if>
                        <br><br>
                        <div class="row grid" style="height: auto;border-top: 0px;margin-bottom:45px; padding-top: 5px;">  
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                            <div class="contents">                                   
                                <div class="table-box table-responsive">
                                    <table class="display" id="example" class="display table">
                                        <thead>
                                            <tr class="row grid-header">
                                                <th class="">
                                                    <span class="tbl_f">Interval Title</span>
                                                </th>
                                                <th class="">
                                                    <span class="tbl_f">Interval In Seconds&nbsp;</span>
                                                </th>
                                                <th class="">
                                                    <span class="tbl_f">Interval Value(s)</span>   
                                                </th>
                                                <th class="text-center text-left-ipad" style="padding-right:20px;">
                                                    <span class="tbl_f">&nbsp;&nbsp;Action</span> 
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="list" items="${intervaltypelist}">
                                                <tr class="row grid-row">
                                                    <td class="">
                                                        <span id="tbl_textalign">${list.intervalsTypeTitle}</span>
                                                    </td>
                                                    <td class=" hidden-sm hidden-xs">
                                                        <span id="tbl_textalign">${list.unitInSecond}</span>
                                                    </td>

                                                    <td class="">     

                                                        <c:forEach var="valid" items="${list.intervals}" varStatus="validLoop">
                                                            <span id="tbl_textalign">${valid.intervalValue}<c:if test="${!validLoop.last}">,</c:if> </span>
                                                        </c:forEach>
                                                    </td>


                                                    <td class="text-right text-left-ipad padd-left-16-ipad">
                                                        <span id="tbl_textalign">
                                                            <a class="${sessionBean.pmap[(13).intValue()].manage eq true?'':'disabled'}" href="${pageContext.request.contextPath}/intervalsType/edit/${list.intervalsTypeId}"><img src="${pageContext.request.contextPath}/resources/images/edit.jpg" width="20"/></a>&nbsp;
                                                            <a  class="${sessionBean.pmap[(13).intValue()].manage eq true?'':'disabled'} " href="#" onclick="isDeleteRecord('intervalsType/delete/${list.intervalsTypeId}')"><img src="${pageContext.request.contextPath}/resources/images/delete.jpg" width="20"/></a>

                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                             <div id="dialog" style="height: 200px; overflow: auto; background-color: white;display: none;padding-top: 25px !important;"></div>
                    </form:form>
                </div>
            </div> <!-- /content -->
            <jsp:include page="./inc/footer.jsp" />
        </div> <!-- /wrapper -->
        <script type="text/javascript">

        </script>
    </body>
</html>
