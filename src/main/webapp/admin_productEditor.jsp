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
                                            ${product.categoryId}. CategoryName
                                    </div>
                                    <div class="col s2">
                                            Price : ${product.price}
                                    </div>
                                    <div class="col s4">

                                    </div>
                                </div>
                            </div>
                            <div class="collapsible-body">
                                <div class="row" style="height: auto">
                                    <div class="col s8" style="height: 100%; display: table">
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Product name</b>
                                            </div>
                                            <div class="col s9">
                                                <input value="${product.name}" id="name" type="text" class="validate">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Category</b>
                                            </div>
                                            <div class="col s9">
                                                <select class="browser-default">
                                                    <option disabled>${product.categoryId}</option>
                                                    <c:forEach items="${requestScope.productList}" var="category">
                                                        <option>${category.categoryId}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Image path</b>
                                            </div>
                                            <div class="col s9">
                                                <input value="${product.imgUrl}" id="ur" type="text" >
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s3">
                                                <br><b>Price</b>
                                            </div>
                                            <div class="col s3">
                                                <input value="${product.price}" id="pric" type="text" class="validate">
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
                                                <textarea id="commentText" class="materialize-textarea"
                                                          name="commentText"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s4 center-align">
                                        <br>
                                        <button class="btn waves-effect waves-light blue" type="submit" name="edit">Edit
                                            <i class="material-icons right">description</i>
                                        </button><br><br>
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
