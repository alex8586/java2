<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="collection card center">
    <h5 class="collection-header">Categories</h5>
    <c:forEach items="${requestScope.model.categories}" var="category">
        <a href="<tags:linkTo controller="CategoryChooseController" resourceId="${category.id}"/>"
           class="collection-item">${category.name}</a>
    </c:forEach>
</div>
