<%@attribute name="product" required="true" type="lv.javaguru.java2.domain.Product" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<div class="col l3 s12 m7">
    <div class="card horizontal">
        <div class="card-image">
            <img src="http://lorempixel.com/100/190/nature/10">
        </div>
        <div class="card-stacked">
            <div class="card-content">
                <span class="card-title">
                    ${product.displayName}
                </span>
                <p>${product.displayDescription}%</p>
            </div>
            <div class="card-action">
                <a href="<tags:linkTo controller="ProductController" resourceId="${product.id}" />">This is a link</a>
            </div>
        </div>
    </div>
</div>