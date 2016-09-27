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
                <c:if test="${not empty requestScope.productList}">
                <ul class="collapsible popout" data-collapsible="accordion">
                    <c:forEach items="${requestScope.productList}" var="product">
                    <li>
                        <div class="collapsible-header grey lighten-4">
                            <div class="row">
                                <div class="col s3">
                                    id ${product.id}. ${product.name}
                                </div>
                                <div class="col s3">
                                        Category id ${product.categoryId}
                                </div>
                                <div class="col s2">
                                    Price : ${product.price} &euro;
                                </div>
                                <div class="col s4">

                                </div>
                            </div>
                        </div>
                        <div class="collapsible-body">
                            <div class="row">
                                <form action="<c:url value='/product/edit'/>" method="post">
                                    <div class="col s8">
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Product name</b>
                                            </div>
                                            <div class="col s9">
                                                <input value="${product.name}" type="text" name="name">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Category</b>
                                            </div>
                                            <div class="col s9">
                                                <select class="browser-default" name="categoryId">
                                                    <option value="empty">category id = ${product.categoryId}</option>
                                                    <c:if test="${not empty requestScope.categoryList}">
                                                        <c:forEach items="${requestScope.categoryList}" var="category">
                                                            <c:if test="${category.id eq product.categoryId}">
                                                                <option selected="true"
                                                                        value="${category.id}">${category.id}. ${category.name}</option>
                                                            </c:if>
                                                            <c:if test="${category.id ne product.categoryId}">
                                                                <option value="${category.id}">${category.id}. ${category.name}</option>
                                                            </c:if>

                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Image path</b>
                                            </div>
                                            <div class="col s9">
                                                <input value="${product.imgUrl}" type="text" name="imgUrl">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Price</b>
                                            </div>
                                            <div class="col s3">
                                                <input value="${product.price}" type="text" name="pricex">
                                            </div>
                                            <div class="col s6">
                                                <br>&euro;
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Description</b>
                                            </div>
                                            <div class="col s9">
                                                <textarea class="materialize-textarea" name="description"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s4 center-align">
                                        <br>
                                        <button class="btn waves-effect waves-light blue" type="submit" name="edit">Edit
                                            <i class="material-icons right">description</i>
                                            <input type="hidden" value="${product.id}" name="id">
                                        </button><br><br>

                                </form>
                                <form action="<c:url value='/product/delete'/>" method="post">
                                    <button class="btn waves-effect waves-light blue" type="submit" name="update">Delete
                                        <i class="material-icons right">delete</i>
                                        <input type="hidden" value="${product.id}" name="productId">
                                    </button>
                                </form>
                            </div>
                        </div>
            </div>
            </li>
            </c:forEach>
            </ul>
            </c:if>
        </div>
        </div>
    </jsp:body>
</tags:adminTemplate>