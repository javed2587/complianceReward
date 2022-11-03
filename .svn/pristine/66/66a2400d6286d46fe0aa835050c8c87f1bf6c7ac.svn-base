<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String url = request.getRequestURL().toString();
    String index = "";

    if (url.contains("patient")) {
        index = "class='menu_selected'";
    }

%>
<div class="header">
    <div id="top" class="container">
        <div class="row">
            <div id="logo"class="col-lg-8 col-md-6 col-sm-6 col-xs-3">
                <img src="${pageContext.request.contextPath}/resources/images/Logo.jpg" class="logo" />
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6 col-xs-9" style="margin-top: 13px; text-align: right;">
                <a id="loginBtn" href="#" style="margin-right: 5px;" <%=index%>>Home</a>
                <a id="loginBtn" href="#">Contact Us</a>
            </div>
        </div>
    </div>
    <div style="background: #1f4c76;">
        <div class="nav-header-patient">
            INSURED PATIENTS PAY $0*
        </div>
    </div>
</div>

