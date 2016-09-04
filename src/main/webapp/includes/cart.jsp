<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="center-align">
    <h5 style="color: orange">In your cart :</h5>
</div>
    <c:if test="${not empty requestScope.model.cart}">
        <c:forEach var="cart" items="${requestScope.model.cart.all}">
    <div class="row">

        <div>
            <div class="col s8 left">
                    ${cart.key.name}
            </div>
            <div class="col s4 right-align">
                    ${cart.key.price}&#8364
            </div>
        </div>
   </div>

        <div class="row">
            <div class="col s6 left">
                <form action="cart" method="post">
                    <div style="display: inline-block">
                        <input type="hidden" name="productId" value="${cart.key.id}">
                        <input type="submit" name="delete" value="-">
                    </div>
                    <div style="display: inline-block">
                        <input type="hidden" name="productId" value="${cart.key.id}">
                        <input type="submit" name="add" value="+">
                    </div>
                </form>
            </div>
            <div class="col s6 right-align">
                    ${cart.value}
                <small>items</small>
            </div>
        </div>
        </c:forEach>
</c:if>
<div class="row">
    <div class="col s6 left-align">
        Total :
    </div>
    <div class="col s6 right-align">
        ${requestScope.model.cartPrice}&#8364
    </div>
</div>

<hr>
<button class="btn waves-effect waves-light" type="submit" name="buy">Buy
    <i class="material-icons right">send</i>
</button>

