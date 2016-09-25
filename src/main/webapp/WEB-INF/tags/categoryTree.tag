<%@attribute name="categoryTreeNode" required="true" type="lv.javaguru.java2.helpers.TreeNode" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<li style="padding: 2% 2% 2% 9%;margin:2% ;">
    <ul style="padding: 0 ; margin: 0">
        <div>
            <a href="<c:url value="/index/category/${categoryTreeNode.instance.id}" />">
                ${categoryTreeNode.instance.name}
            </a>
        </div>

        <ul>
            <c:forEach items="${categoryTreeNode.childrenNodes}" var="subcategory">
                <tags:categoryTree categoryTreeNode="${subcategory}"/>
            </c:forEach>
        </ul>
    </ul>
</li>


