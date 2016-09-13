<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <%@include file="includes/category.jsp" %>
            </div>
            <div class="col s8">
                <c:if test="${not empty requestScope.model.error}">
                    <div class="row center-align">
                        <h5 style="color: red">${requestScope.model.error}</h5>
                    </div>
                </c:if>
                <c:if test="${empty requestScope.model.error}">
                    <div class="row">
                        <div class="row">
                            <div class="col s12 center-align" >
                                <br>
                                <h5>${requestScope.model.product.name}</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s3 center-align" >
                                <div class="row">
                                    <i>Rating</i> 567
                                </div>
                                <div class="row">
                                    <img src="${requestScope.model.product.imgUrl}" width="219" height="219">
                                </div>
                            </div>
                            <div class="col s9">
                                <div class="row">
                                <p style="margin-left: 40px;">
                                        ${requestScope.model.product.description}
                                    and more description and more description and more description and more description
                                    and more description and more description and more description and more description
                                    and more description and more description and more description and more description
                                    and more description and more description and more description and more description

                                </p>
                                <p style="margin-left: 40px;">
                                    <br>
                                    price - <b style="color: red">${requestScope.model.product.price} &#8364</b>
                                </p>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <form action="" method="post">
                                <div class="col s6" style="display: inline-block;">

                                </div>
                                <div class="col s2 center-align" style="display: inline-block;">
                                    Stock - 34
                                </div>
                                <div class="col s1" style="display: inline-block;">
                                    <input type="text" placeholder="0" name="quantity">
                                </div>
                                <div class="col s3" style="display: inline-block;">
                                    <button class="btn waves-effect waves-light" type="submit" name="addToCart">Add to cart
                                        <i class="material-icons">add_shopping_cart</i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="col s2">
                <%@include file="includes/cart.jsp" %>
                <%@include file="includes/product_banner.jsp" %>
            </div>
        </div>
        <div class="row">

        </div>
    </jsp:body>
</tags:pageTemplate>