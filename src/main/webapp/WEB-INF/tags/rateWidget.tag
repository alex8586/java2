<%@attribute name="productCard" required="true" type="lv.javaguru.java2.dto.ProductCard" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value='/product/rate'/>" method="post">
    Rate
    <input type="submit" value="1" name="rate"
           style="background-color: #e3f2fd">
    <input type="submit" value="2" name="rate"
           style="background-color: #bbdefb">
    <input type="submit" value="3" name="rate"
           style="background-color: #90caf9">
    <input type="submit" value="4" name="rate"
           style="background-color: #64b5f6">
    <input type="submit" value="5" name="rate"
           style="background-color: #42a5f5">
    <input type="hidden" value="${productCard.productId}" name="productId">
</form>