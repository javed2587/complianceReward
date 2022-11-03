<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String url = request.getRequestURL().toString();
    String homeLink = "class='nav-item'", faqlink = "class='nav-item'", contactUsLink = "class='nav-item'";

    if (url.contains("login") || url.contains("pharmacylogin2.jsp")) {
        homeLink = "class='nav-item active'";
    }
    if (url.contains("faq")) {
        faqlink = "class='nav-item active'";
    }
    if (url.contains("contacts")) {
        contactUsLink = "class='nav-item active'";
    }
%>

<header id="header">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-faded">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/pharmacyqueue/login"><img height="50" src="${pageContext.request.contextPath}/resources/images/compliance-reward-logo.png"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="navbarNavDropdown" class="navbar-collapse collapse">
                <ul class="navbar-nav mr-auto">
                    <li id="homeLink" <%=homeLink%>>
                        <a class="nav-link" href="${pageContext.request.contextPath}/pharmacyqueue/login">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li id="hiw" class="nav-item">
                        <a id="hiwhref" class="nav-link" href="${pageContext.request.contextPath}/pharmacyqueue/login#howItWorkDiv" onclick="window.pharmacyRegisteration.hiwLinkActive();">How it works </a>
                    </li>
                    <li id="faqlink" <%=faqlink%>>
                        <a class="nav-link" href="${pageContext.request.contextPath}/ConsumerPortal/faq">FAQ</a>
                    </li>
                    <li id="contactUsLink" <%=contactUsLink%>>
                        <a class="nav-link" href="${pageContext.request.contextPath}/ConsumerPortal/contactUs">Contact Us </a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link join_now" href="#" data-toggle="modal" onclick="window.pharmacyRegisteration.openPopUp('register')">Join Now</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link phram_btn" href="#" class=" btn-pharmacy" data-toggle="modal" onclick="window.pharmacyRegisteration.openPopUp('login')">PHARMACY PORTAL</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>