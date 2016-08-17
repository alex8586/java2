<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>
<%@include file="includes/category.jsp" %>

<div class="section">
    <a href="#" data-activates="slide-out" class="btn button-collapse">
        <i class="material-icons right">menu</i>
        CATEGORIES
    </a>
</div>

<div class="row">
    <c:forEach items="${requestScope.model.products}" var="product">
        <tags:productCard product="${product}"/>
    </c:forEach>
</div>

<%@include file="includes/footer.jsp"%>
