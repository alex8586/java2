<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<header>
    <nav>
        <div class="row orange">
            <div class="col s3">
                <a href="<c:url value='/index/all'/>" class="brand-logo">
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
                    <li><a href="<c:url value='/contact'/>">CONTACT</a></li>
                    <c:if test="${not empty sessionScope.user}">
                        <li><a href="<c:url value='/profile'/>">PROFILE</a></li>
                        <li><a href="<c:url value='/logout'/>">LOGOUT</a></li>
                        Hi ${sessionScope.user.fullName}!
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <li><a href="<c:url value='/login'/>">LOGIN</a></li>
                        <li><a href="<c:url value='/registration'/>">REGISTER</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>

