<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <%@include file="includes/category.jsp" %>
            </div>
            <div class="col s8">

                <div class="navbar">
                    <nav>
                        <div class="nav-wrapper white">
                            <ul class="right hide-on-med-and-down">
                                <c:forEach items="${requestScope.productCardSortingStrategies}" var="sortingStrategy">
                                    <li>
                                        <a href="<c:url value="/index/sortBy/${sortingStrategy.key}" />"
                                           class="html-editor-align-justify blue-text">
                                                ${sortingStrategy.value}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </nav>
                </div>

                <c:forEach items="${requestScope.productCards}" var="productCard">
                    <tags:productCard productCard="${productCard}"/>
                </c:forEach>
            </div>
            <div class="col s2">
                <hr>
                <%@include file="includes/cart.jsp" %>
                <hr>
                <%@include file="includes/product_banner.jsp" %>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>