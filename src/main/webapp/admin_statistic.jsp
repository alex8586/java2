<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:adminTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <%@include file="includes/admin_menu.jsp"%>
            </div>
            <div class="col s10">
                <br>
                <table class="striped centered">
                    <thead>
                    <tr>
                        <th data-field="id">Pr. id</th>
                        <th data-field="name">Product name</th>
                        <th data-field="price">C. id</th>
                        <th data-field="price">Category name</th>
                        <th data-field="price">Comments</th>
                        <th data-field="price">Rates</th>
                        <th data-field="price">Views</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:if test="${not empty requestScope.productList}">
                    <c:forEach items="${requestScope.productList}" var="product">
                    <tr>
                        <%--<td>${product.id}</td>--%>
                        <%--<td>${product.name}</td>--%>
                        <%--<td>${}</td>--%>
                        <%--<td>${}</td>--%>
                        <%--<td>${}</td>--%>
                        <%--<td>${}</td>--%>
                        <%--<td>${}</td>--%>
                    </tr>
                    </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</tags:adminTemplate>
