<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="card-image">
    <hr>
    <a href="product?productId=${requestScope.saleOffer.id}">
        <a href="${s:mvcUrl('PC#productModel').arg(0, requestScope.saleOffer.id).build()}">
            <img src="<c:url value="${requestScope.saleOffer.imgUrl}" />" width="202" height="224">
            <img src="<c:url value='/miskaweb/img/sale.jpg' />" width="202" height="50">
        </a>
    <hr>
</div>
