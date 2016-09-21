<%@attribute name="productCard" required="true" type="lv.javaguru.java2.dto.ProductCard" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="col s3">
    <div class="card vertical-grabber">
        <div class="card-title center">
            ${productCard.productName}
        </div>
        <div class="card-image">
            <a href="${s:mvcUrl('PC#productModel').arg(0, productCard.productId).build()}"><img
                    src="${productCard.productImgUrl}"
                    height="130" width="130"></a>
        </div>
        <div class="card-content left-align">
            <p>${productCard.productDescription}</p>
            <hr>
            Price : ${productCard.productPrice} &#8364
            <hr>
            Expiration: ${productCard.stockExpireDate}
            <hr>
            Stock: ${productCard.stockQuantity}
        </div>
        <div class="card-action center">
            <a href="addToCart?productId=${productCard.productId}">Add to cart</a>
        </div>
    </div>
</div>