<%@ tag import="lv.javaguru.java2.servlet.ReverseRouter" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="controller" required="true" %>
<%@attribute name="resourceId" required="false" %>
<%
    ReverseRouter reverseRouter = (ReverseRouter) application.getAttribute("reverseRouter");
%>
<c:if test="${not empty resourceId}">
    <c:url value="<%= reverseRouter.linkTo(controller, resourceId)%>"/>
</c:if>
<c:if test="${empty resourceId}">
    <c:url value="<%= reverseRouter.linkTo(controller)%>"/>
</c:if>






