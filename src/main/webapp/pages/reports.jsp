<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
    String serverName = request.getScheme() + "://" + request.getServerName();
    String reportController = request.getParameter("name");
    String title = request.getParameter("title");
    
%>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>
        <div id="wrapper">
            <jsp:include page="./inc/header.jsp" />
            <div id="content" class="clearfix">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Home > <%=title%> Report
                </div>
                <div class="heading">
                    <div style="float: left;">
                        <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;<%=title%> Report</h1>
                    </div>
                </div> <!-- /content -->
                <div class="wrp clearfix" style="text-align: center; min-height: 300px;margin-bottom: 0.3em;">
                    <iframe src="<%=serverName%>/API-Reports/<%=reportController%>" width="100%" height="500px;" frameborder="0" scrolling="no"></iframe>
                </div>
            </div> <!-- /wrapper -->
        </div>
        <jsp:include page="./inc/footer.jsp" />
    </body>
</html>
