<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div class="card collection" style="font-size: 16px">
    <h5 class="center collection-item">Categories</h5>
    <tags:categoryTree categoryTreeNode="${requestScope.rootCategoryNode}" depth="1"/>
</div>


