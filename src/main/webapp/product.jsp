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
                <c:set var="productCard" value="${requestScope.productCard}"/>

                <div class="row">
                    <div class="row">
                        <div class="col s12 center-align">
                            <br>
                            <h5>${productCard.productName}</h5>
                            <c:if test="${productCard.averageRate > 0}">
                                <b style="background-color: ${productCard.rateColorCode}">&ensp;
                                    Rating &ensp; ${productCard.averageRate}&ensp;
                                </b>
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s3 center-align">
                            <div class="row">
                                <i>Views</i> ${productCard.viewCount}
                            </div>
                            <div class="row">
                                <img src="<c:url value='${productCard.productImgUrl}'/>" width="219" height="219">
                            </div>

                            <c:if test="${not empty requestScope.user}">
                                <c:if test="${empty requestScope.cantRate}">
                                    <div class="row center">
                                        <tags:rateWidget productCard="${productCard}"/>
                                    </div>
                                </c:if>
                                <c:if test="${not empty requestScope.cantRate}">
                                    <div class="row">
                                            ${requestScope.cantRate}
                                    </div>
                                </c:if>
                            </c:if>
                        </div>
                        <div class="col s9">
                            <div class="row">
                                <p style="margin-left: 40px;">
                                        ${productCard.productDescription}
                                    and more description and more description and more description and more description
                                    and more description and more description and more description and more description
                                    and more description and more description and more description and more description
                                    and more description and more description and more description and more description
                                </p>
                                <p style="margin-left: 40px;">
                                    <br>
                                    price : <b style="color: red">${productCard.productPrice} &#8364</b>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s6" style="display: inline-block;">
                        </div>
                        <div class="col s2 center-align" style="display: inline-block;">
                            At warehouse : ${productCard.available}
                        </div>
                        <form action="<c:url value="/cart/addQuantity" /> " method="post">
                            <div class="col s1" style="display: inline-block;">
                                <input type="text" placeholder="0" name="quantity">
                                <input type="hidden" value="${productCard.productId}" name="productId">
                            </div>
                            <div class="col s3 right-align" style="display: inline-block;">
                                <button class="btn waves-effect waves-light blue" type="submit" name="addToCart">Add to
                                    cart
                                    <i class="material-icons right">add_shopping_cart</i>
                                </button>
                            </div>
                        </form>
                    </div>

                    <c:if test="${not empty requestScope.user}">
                        <div class="row">
                            <div class="col s12">
                                Add comment :
                            </div>
                            <form action="<c:url value='/product/comment'/>" method="post">
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">mode_edit</i>
                                    <textarea id="commentText" class="materialize-textarea"
                                              name="commentText"></textarea>
                                    <label for="commentText">Comment here</label>
                                    <input type="hidden" value="${productCard.productId}" name="productId">
                                </div>
                                <div class="col s12 right-align">
                                    <button class="btn waves-effect waves-light blue" type="submit" name="comment">
                                        Comment !
                                        <i class="material-icons right">list</i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </c:if>

                    <c:if test="${not empty requestScope.reviews}">
                        <div class="row">
                            <ul class="collection with-header">
                                <li class="collection-header"><h5>Comments :</h5></li>
                                <c:forEach items="${requestScope.reviews}" var="review">
                                    <li class="collection-item"
                                        style="background-color: lightgray">${review.userName}, ${review.date}</li>
                                    <li class="collection-item">${review.review}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                </div>


            </div>
            <div class="col s2">
                <%@include file="includes/cart.jsp" %>
                <%@include file="includes/product_banner.jsp" %>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>