<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <c:if test="${not empty requestScope.model.error}">
                ${requestScope.model.error}
            </c:if>
            <c:if test="${empty requestScope.model.error}">
                ${requestScope.model.product} <br>
                <a href="<tags:linkTo controller="CartController" resourceId="${requestScope.model.product.id}"/>">Add
                    to Cart</a>
            </c:if>
        </div>
    </jsp:body>
</tags:pageTemplate>
