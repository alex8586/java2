<%@attribute name="product" required="true" type="lv.javaguru.java2.domain.Product" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div class="card-image">
    <hr>
    <img src=${requestScope.model.imgPath} width="202" height="224">
    <img src="miskaweb/img/sale.jpg" width="202" height="50">
    <hr>
</div>