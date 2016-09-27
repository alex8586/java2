<%@attribute name="categoryTreeNode" required="true" type="lv.javaguru.java2.helpers.TreeNode" %>
<%@attribute name="depth" required="true" type="java.lang.Integer" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


<a href="<c:url value='/index/category/${categoryTreeNode.instance.id}'/>" class="collection-item blue-text">
    <span style="padding-left: ${depth * 9}%">
        - ${categoryTreeNode.instance.name}
    </span>
</a>


<c:forEach items="${categoryTreeNode.childrenNodes}" var="subcategory">
    <tags:categoryTree categoryTreeNode="${subcategory}" depth="${depth + 1}"/>
</c:forEach>



