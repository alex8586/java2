<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <%@include file="includes/category.jsp" %>
            </div>

            <tr>
                <c:forEach items="${requestScope.model.tabrows}" var="rows">
                    ${rows}
                </c:forEach>
            </tr>
            <div class="col s8">


                <c:forEach items="${requestScope.model.products}" var="product">
                    <tags:productCard product="${product}"/>
                </c:forEach>
            </div>
            <div class="col s2">
                <hr>
                <%@include file="includes/cart.jsp" %>
                <hr>
                <%@include file="includes/product_banner.jsp" %>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
