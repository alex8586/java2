<%@attribute name="productCard" required="true" type="lv.javaguru.java2.dto.ProductCard" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col m6 l3" style=" display: inline-block;">
    <div class="card vertical-grabber">
        <div class="row center" style="height: 60px;margin: 10px;  font-size: 20px">
            ${productCard.productName}

        </div>
        <div class="card-image">
            <a href="<c:url value="/product/${productCard.productId}"/>">
                <img src="<c:url value='${productCard.productImgUrl}'/>" height="130" width="130">
            </a>
            <div class="row center-align" style="background-color: ${productCard.rateColorCode}; margin-bottom: auto;
                    border-bottom: 1px solid rgba(160, 160, 160, 0.2)">
                Rating &ensp; ${productCard.averageRate}&ensp;
            </div>
        </div>
        <div class="card-content left-align" style="padding: 10px">
            Price : ${productCard.productPrice} &euro;
            <br>
            Stock: ${productCard.stockQuantity}
        </div>

        <div class="card-action center" style="padding: 10px">
            <a href="<c:url value='/cart/add/${productCard.productId}'/>">Add to cart</a>
        </div>
    </div>
</div>