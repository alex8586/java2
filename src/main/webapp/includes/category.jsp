
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="slide-out" class="side-nav">
    <c:forEach items="${requestScope.model.categories}" var="category">
        <li><a href='#'>${category.name}</a></li>
    </c:forEach>
</ul>

