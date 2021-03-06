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
            <div class="col s7">
                <ul class=" hide-on-med-and-down">
                    <li><a href="">NEWS</a></li>
                    <li><a href="">SALE</a></li>
                    <li><a href="">SHIPPING</a></li>
                    <li><a href="<c:url value='/contact'/>">CONTACT</a></li>
                    <c:if test="${not empty sessionScope.user}">
                        <li><a href="<c:url value='/profile'/>">PROFILE</a></li>
                        <li><a href="<c:url value='/logout'/>">LOGOUT</a></li>
                        Hi ${sessionScope.user.fullName}!
                    </c:if>
                    <c:if test="${not empty requestScope.admin}">
                        <li><a href="<c:url value='/profile'/>">PROFILE</a></li>
                        <li><a href="<c:url value='/logout'/>">LOGOUT</a></li>
                        Hi ${requestScope.admin.fullName}!
                    </c:if>
                    <c:if test="${empty requestScope.user and empty requestScope.admin and empty sessionScope.user}">
                        <li><a href="<c:url value='/login'/>">LOGIN</a></li>
                        <li><a href="<c:url value='/registration'/>">REGISTER</a></li>
                    </c:if>
                </ul>
            </div>
            <div class="col s2" >
                <c:if test="${not empty requestScope.admin}">
                <ul class="hide-on-med-and-down">
                    <li class="blue lighten-1">
                        <a href="<c:url value='/orders'/>">
                            ADMIN
                            <c:if test="${requestScope.newOrders > 0}">
                                <span class="new badge red">${requestScope.newOrders}</span>
                            </c:if>
                        </a>
                    </li>
                </ul>
                </c:if>
            </div>
        </div>
    </nav>
</header>

