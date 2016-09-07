<%@attribute name="product" required="true" type="lv.javaguru.java2.domain.Product" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<div class="col s3">
    <div class="card vertical-grabber">
        <div class="card-title center">
            ${product.name}
        </div>
        <div class="card-image">
            <a href="product?=${product.id}"><img src="${product.imgUrl}" height="130" width="130"></a>
        </div>
        <div class="card-content left-align">
            <p>${product.description}</p>
            <hr>
            Price : ${product.price} &#8364
        </div>
        <div class="card-action center">

            <a href="<tags:linkTo controller="CartController" resourceId="${product.id}" />">Add to cart</a>
        </div>
    </div>
</div>