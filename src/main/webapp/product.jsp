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
                <c:if test="${empty requestScope.model.error}">
                    <div class="row">
                        <div class="row">
                            <div class="col s12 center-align" >
                                <br>
                                <h5>${requestScope.model.productCard.productName}</h5>
                                <b style="background-color: ${requestScope.model.rateColor}">&ensp;  Rating &ensp; ${requestScope.model.averageRate}&ensp;</b>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s3 center-align" >
                                <div class="row">
                                    <i>Views</i> ${requestScope.model.views}
                                </div>
                                <div class="row">
                                    <img src="${requestScope.model.productCard.productImgUrl}" width="219" height="219">
                                </div>
                                <c:if test="${not empty sessionScope.user}">
                                    <c:if test="${empty requestScope.model.cantRate}">
                                        <div class="row center">
                                            <form action="<tags:linkTo controller="RateController"/>" method="post">
                                                Rate
                                                <input type="submit" value="1" name="rate"  style="background-color: #e3f2fd">
                                                <input type="submit" value="2" name="rate"  style="background-color: #bbdefb">
                                                <input type="submit" value="3" name="rate"  style="background-color: #90caf9">
                                                <input type="submit" value="4" name="rate"  style="background-color: #64b5f6">
                                                <input type="submit" value="5" name="rate"  style="background-color: #42a5f5">
                                                <input type="hidden" value="${requestScope.model.productCard.productId}" name="productId">
                                            </form>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty requestScope.model.cantRate}">
                                        <div class="row">
                                            ${requestScope.model.cantRate}
                                        </div>
                                    </c:if>
                                </c:if>
                            </div>
                            <div class="col s9">
                                <div class="row">
                                    <p style="margin-left: 40px;">
                                            ${requestScope.model.productCard.productDescription}
                                        and more description and more description and more description and more description
                                        and more description and more description and more description and more description
                                        and more description and more description and more description and more description
                                        and more description and more description and more description and more description
                                    </p>
                                    <p style="margin-left: 40px;">
                                        <br>
                                        price - <b style="color: red">${requestScope.model.productCard.productPrice} &#8364</b>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s6" style="display: inline-block;">

                            </div>
                            <div class="col s2 center-align" style="display: inline-block;">
                                Stock - ${requestScope.model.productCard.stockQuantity}
                            </div>
                            <form action="<tags:linkTo controller="ProductController"/>" method="post">
                                <div class="col s1" style="display: inline-block;">
                                    <input type="text" placeholder="0" name="quantity">
                                    <input type="hidden" value="${requestScope.model.productCard.productId}" name="productId">
                                </div>
                                <div class="col s3 right-align" style="display: inline-block;">
                                    <button class="btn waves-effect waves-light" type="submit" name="addToCart">Add to cart
                                        <i class="material-icons right">add_shopping_cart</i>
                                    </button>
                                </div>
                            </form>
                        </div>
                        <c:if test="${not empty sessionScope.user}">
                            <div class="row">
                                <div class="col s12">
                                    Add comment :
                                </div>
                                <form action="<tags:linkTo controller="CommentController"/>" method="post">
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">mode_edit</i>
                                        <textarea id="icon_prefix2" class="materialize-textarea" name="comment"></textarea>
                                        <label for="icon_prefix2">Comment here</label>
                                        <input type="hidden" value="${requestScope.model.productCard.productId}" name="productId">
                                    </div>
                                    <div class="col s12 right-align">
                                        <button class="btn waves-effect waves-light" type="submit" name="comment">Comment !
                                            <i class="material-icons right">list</i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${not empty requestScope.model.reviews}">
                            <div class="row">
                                <ul class="collection with-header">
                                    <li class="collection-header"><h5>Comments :</h5></li>
                                    <c:forEach items="${requestScope.model.reviews}" var="reviews">
                                        <li class="collection-item" style="background-color: lightgray">${reviews.userName}, ${reviews.date}</li>
                                        <li class="collection-item">${reviews.comment}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </c:if>
                </div>
            <div class="col s2">
                <%@include file="includes/cart.jsp" %>
                <%@include file="includes/product_banner.jsp" %>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>