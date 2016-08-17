<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includes/header.jsp"%>

<div class="row">
    <div class="col s2">
        <%@include file="includes/category.jsp" %>
    </div>
    <div class="col s8">
        <c:forEach items="${requestScope.model.products}" var="product">
            <tags:productCard product="${product}"/>
        </c:forEach>
    </div>
    <div class="col s2">
        <hr>
        <%@include file="includes/cart.jsp" %>
        <hr>
    </div>
</div>
</main>
<%@include file="includes/footer.jsp"%>