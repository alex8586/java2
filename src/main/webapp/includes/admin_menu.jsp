<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<div class="collection card center">

    <h5 class="collection-header">Menu</h5>
    <a href="<c:url value='/admin'/>" class="collection-item blue-text"><h6>Admin main</h6></a>
    <a href="<c:url value='/statistic'/>" class="collection-item blue-text"><h6>Statistic</h6></a>
    <a href="<c:url value='/productEditor'/>" class="collection-item blue-text"><h6>Product Editor</h6></a>
    <a href="<c:url value='/categoryEditor'/>" class="collection-item blue-text"><h6>Category Editor</h6></a>
    <a href="<c:url value='/stockEditor'/>" class="collection-item blue-text"><h6>Stock Editor</h6></a>
    <a href="<c:url value='/orders'/>" class="collection-item blue-text"><h6>Orders</h6></a>

</div>
