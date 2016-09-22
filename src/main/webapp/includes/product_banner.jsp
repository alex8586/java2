<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card-image">
    <hr>
    <a href="product?productId=${requestScope.saleOffer.id}">
        <img src="<c:url value="${requestScope.saleOffer.imgUrl}" />" width="202" height="224">
        <img src="miskaweb/img/sale.jpg" width="202" height="50">
    </a>
    <hr>
</div>
