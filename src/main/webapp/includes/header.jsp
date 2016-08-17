<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav>
        <div class="container">
            <div class="nav-wrapper">
                <a href="<tags:linkTo controller="FrontPageController" />" class="brand-logo">
                    <i class="material-icons">all_inclusive</i>
                    MISKA
                </a>

                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="">NEWS</a></li>
                    <li><a href="">SALE</a></li>
                    <li><a href="">YOUR IDEA</a></li>
                    <li><a href="">YOUR IDEA</a></li>
                    <li><a href="">SHIPPING</a></li>
                    <li><a href="">CONTACT</a></li>
                    <c:if test="${not empty sessionScope.user}">
                        Hi ${sessionScope.user.fullName}!
                        <div class="right hide-on-med-and-down">
                            <li><a href="<tags:linkTo controller="ProfileController" />">PROFILE</a></li>
                            <li><a href="<tags:linkTo controller="LogoutController" />">LOGOUT</a></li>
                        </div>
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

