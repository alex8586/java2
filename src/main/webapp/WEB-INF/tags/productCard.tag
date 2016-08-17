<%@attribute name="product" required="true" type="lv.javaguru.java2.domain.Product" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<div class="col s3">
    <div class="card vertical-grabber">
        <div class="card-title center">
            ${product.displayName}
        </div>
        <div class="card-image">
            <img src="miskaweb/img/product.jpg" height="130" width="130">
        </div>
        <div class="card-content left-align">
            <p>${product.displayDescription}</p>
            <hr>
            Price : ${product.price} &#8364
        </div>
        <div class="card-action center">
            <a href="<tags:linkTo controller="ProductController" resourceId="${product.id}" />">This is a link</a>
        </div>
    </div>
</div>