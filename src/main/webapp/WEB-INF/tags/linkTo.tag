<%@ tag import="lv.javaguru.java2.servlet.PathResolver" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="controller" required="true" %>
<%@attribute name="resourceId" required="false" %>
<%
    PathResolver pathResolver = (PathResolver) application.getAttribute("reverseRouter");
%>
<c:if test="${not empty resourceId}">
    <c:url value="<%= pathResolver.linkTo(controller, resourceId)%>"/>
</c:if>
<c:if test="${empty resourceId}">
    <c:url value="<%= pathResolver.linkTo(controller)%>"/>
</c:if>






