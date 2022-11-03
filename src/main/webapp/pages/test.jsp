<!DOCTYPE html>
<html lang="en">
    <jsp:include page="./inc/head.jsp" />
    <body>

        <div id="wrapper">
            <jsp:include page="./inc/header.jsp" />
            <div id="content" class="clearfix" style="z-index:0">
                <jsp:include page="./inc/menu.jsp" />
                <div class="breadcrumbs">
                    <i class="fa fa-home"></i> Text Page
                </div>
                <div class="heading" >
                    <h1 class="page-title"><i class="fa fa-arrow-circle-o-right"></i>&nbsp;Text Page</h1>

                </div> <!-- /content -->
                <div class="page-body wrp clearfix" style="color:black;padding-left: 15px; padding-right: 0px; text-align: left;">
                    <br />        
                    <form method="Post" action="${pageContext.request.contextPath}/campaign/helloWorld">
                        <button id="submit">Submit</button>
                    </form>
                </div>
            </div> <!-- /wrapper -->
        </div>
        <jsp:include page="./inc/footer.jsp" />

    </body>
</html>
