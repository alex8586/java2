<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav>
            <div class="row">
                <div class="col s3">
                <a href="<tags:linkTo controller="FrontPageController" />" class="brand-logo">
                    <i class="material-icons">all_inclusive</i>
                    MISKA
                </a>
                </div>
                <div class="col s9">
                <ul class=" hide-on-med-and-down">
                    <li><a href="">NEWS</a></li>
                    <li><a href="">SALE</a></li>
                    <li><a href="">YOUR IDEA</a></li>
                    <li><a href="">YOUR IDEA</a></li>
                    <li><a href="">SHIPPING</a></li>
                    <li><a href="">CONTACT</a></li>
                    <c:if test="${not empty sessionScope.user}">
                        <li><a href="<tags:linkTo controller="ProfileController" />">PROFILE</a></li>
                        <li><a href="<tags:linkTo controller="LogoutController" />">LOGOUT</a></li>
                        Hi ${sessionScope.user.fullName}!
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <li><a href="<tags:linkTo controller="LoginController" />">LOGIN</a></li>
                        <li><a href="<tags:linkTo controller="RegistrationController" />">REGISTER</a></li>
                    </c:if>
                </ul>
                </div>
            </div>
    </nav>
</header>

