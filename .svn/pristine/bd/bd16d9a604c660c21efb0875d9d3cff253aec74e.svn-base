<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <jsp:include page="./inc/header.jsp" />
        <script>


        </script>
        <div id="wrapper">
            <div id="content" class="clearfix">
                <jsp:include page="./inc/newMenu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home <img src="${pageContext.request.contextPath}/resources/images/arrow.png" /> Manage Campaigns
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Manage Campaigns</h1>
                </div>

                <div class="page-message" style="padding-left: 15px; padding-top: 15px;">
                    <c:if test="${not empty message}"><div id="message" class="message">${message}</div></c:if>
                    <c:if test="${not empty errorMessage}"><div id="errorMessage" class="errorMessage">${errorMessage}</div></c:if>
                    </div>  

                    <div class="clearfix" style="padding-left: 15px;padding-right: 15px; padding-bottom: 10px;padding-top: 12px; margin-bottom: 50px;">
                        <div style="float: right;" class="btndrop" onclick="location.href = '${pageContext.request.contextPath}/campaign/add'">
                        <a class="btn_Color" href="${pageContext.request.contextPath}/campaign/add">Add New</a>&nbsp;&nbsp;<span class="plusCol"><i class="fa fa-plus"></i></span>
                    </div>
                    <br><br>
                    <div class="row grid" style="height: auto;border-top: 0px;margin-bottom: 50px;">
                        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
                        <div class="contents">                                   
                            <div class="table-box">
                                <table class="display table" id="example" class="display">                                         
                                    <thead style="border-top:1px solid #cdcdce;height:35px;">
                                        <tr class="row grid-header">																									
                                            <th class="col-lg-2 col-md-2 col-sm-2 col-xs-10 sorting btn_Color" style=" font-weight:normal;">Campaign Title</th>
                                            <th class="col-lg-3 col-md-3 col-sm-3 col-xs-3 hidden-xs sorting"   style="font-weight:normal;">Compound(s)</th>
                                            <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1  hidden-xs" style="font-weight:normal;">Trigger(s)</th>
                                            <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-xs" style="font-weight:normal;">Launch Date</th>
                                            <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs" style="font-weight:normal;">Termination Date</th>  
                                            <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden-xs " style="font-weight:normal;">Status</th> 
                                            <th class="col-lg-1 col-md-1 col-sm-1 col-xs-2 " style="font-weight:normal;">Action</th>
                                        </tr>       
                                    </thead>   
                                    <tbody>
                                        <c:forEach var="campaign" items="${list}">
                                            <tr class="row grid-row">												
                                                <td class="col-lg-2 col-md-2 col-sm-2 col-xs-10"  style="vertical-align: top;${campaign.isActive ne 'No' ?'color: #067512;':'color: #E70000;'}">${campaign.campaignName}</td>
                                                <td class="col-lg-3 col-md-3 col-sm-3 col-xs-3 hidden-xs" valign="top" style="${campaign.isActive ne 'No' ?'color: #067512;':'color: #E70000;'}">
                                                    <div class="accordion-group">
                                                        <span class="SeeMore2" style="cursor: pointer;text-decoration: underline;" data-toggle="collapse" href="#collapseOne">Show Compound List</span>
                                                        <div id="collapseOne" class="accordion-body collapse">
                                                            <div class="accordion-inner">
                                                                <p>
                                                                    <c:forEach var="drug" items="${campaign.drugBrands}" varStatus="validLoop">
                                                                        ${drug.genericName}<br>
                                                                    </c:forEach>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>

                                                <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1  hidden-xs"  style="vertical-align: top;${campaign.isActive ne 'No' ?'color: #067512;':'color: #E70000;'}">
                                                    <c:forEach var="trigger" items="${campaign.triggers}" varStatus="loop">
                                                        ${trigger.id.keyword}<c:if test="${!loop.last}"><br /></c:if>
                                                    </c:forEach>
                                                </td>

                                                <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-xs"  style="vertical-align: top;${campaign.isActive ne 'No' ?'color: #067512;':'color: #E70000;'}">
                                                    <fmt:formatDate pattern="MM/dd/yyyy" value="${campaign.lanchDateTime}" />
                                                </td>
                                                <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-sm hidden-xs"  style="vertical-align: top;${campaign.isActive ne 'No' ?'color: #067512;':'color: #E70000;'}">
                                                    <fmt:formatDate pattern="MM/dd/yyyy" value="${campaign.terminationDateTime}" />
                                                </td>
                                                <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-xs"  style="vertical-align: top; ${campaign.isActive ne 'No' ?'color: #067512;':'color: #E70000;'}">
                                                    ${campaign.isActive ne 'No' ?'Active':'InActive'}
                                                </td>
                                                <td class="col-lg-1 col-md-1 col-sm-1 col-xs-2 "  style="vertical-align: top">
                                                    <div class="btn-group">
                                                        <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#" style="padding-top: 4px;">
                                                            <i class="fa fa-eye">&nbsp;&nbsp;<span class="caret"></span></i>
                                                        </a>
                                                        <ul class="dropdown-menu" style="margin: 1px 0 0 -25px;">
                                                            <li><a href="${pageContext.request.contextPath}/campaign/edit/${campaign.campaignId}">Edit</a></li>
                                                            <li><a href="${pageContext.request.contextPath}/campaign/SMS/${campaign.campaignId}">SMS Setup</a></li>
                                                            <li><a href="${pageContext.request.contextPath}/campaign/Email/${campaign.campaignId}">Email Setup</a></li>
                                                            <li><a href="${pageContext.request.contextPath}/campaign/OIVR/${campaign.campaignId}">IVR Setup</a></li>
                                                        </ul>
                                                    </div>
                                                    <form id="move${campaign.campaignId}" method="get" action="${pageContext.request.contextPath}/campaign/move/${campaign.campaignId}">
                                                        <input type="hidden" name="campaignId" value="${campaign.campaignId}" />
                                                    </form>
                                                </td>                        
                                            </tr>

                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="clearfix"></div>
                            </div> 
                        </div>
                    </div></div>
            </div> <!-- /wrp -->
        </div> <!-- /content -->
    </div> <!-- /wrapper -->

    <jsp:include page="./inc/footer.jsp" />
    <script>
                            function move(id) {
                                if (confirm('Are you sure, you want to move this campaign?')) {
                                    id = '#' + id;
                                    $(id).submit();
                                }
                            }

                            $('.SeeMore2').click(function() {
                                var $this = $(this);
                                $this.toggleClass('SeeMore2');
                                if ($this.hasClass('SeeMore2')) {
                                    $this.text('Show Compound List');
                                } else {
                                    $this.text('Hide Compound List');
                                }
                            });
    </script>
</body>
</html>
