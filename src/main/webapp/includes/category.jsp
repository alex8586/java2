<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div class="collection card center">
    <h5 class="collection-header">Categories</h5>
    <c:forEach items="${requestScope.categories}" var="category">
        <a href="${s:mvcUrl('FPC#chooseCategory').arg(0,category.id).build()}"
           class="collection-item blue-text">${category.name}</a>
    </c:forEach>
</div>

<div class="card vertical-grabber collection" style="font-size: 16px">
    <ul style="padding-left: 10px ; margin: 0">
        <tags:categoryTree categoryTreeNode="${requestScope.rootCategoryNode}"/>
    </ul>
</div>
