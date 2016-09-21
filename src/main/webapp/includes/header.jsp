<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<header>
    <nav>
            <div class="row">
                <div class="col s3">
                    <a href="index" class="brand-logo">

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
                    <li><a href="${s:mvcUrl('CC#model').build()}">CONTACT</a></li>
                    <c:if test="${not empty sessionScope.user}">
                        <li><a href="${s:mvcUrl('PC#profile').build()}">PROFILE</a></li>
                        <li><a href="logout">LOGOUT</a></li>
                        Hi ${sessionScope.user.fullName}!
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <li><a href="login">LOGIN</a></li>
                        <li><a href="${s:mvcUrl('RC#registration').build()}">REGISTER</a></li>
                    </c:if>
                </ul>
                </div>
            </div>
    </nav>
</header>

