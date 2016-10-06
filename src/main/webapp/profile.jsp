<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
         <h4>Profile main page</h4>
         Here we could put a lot of cool stuff , like widgets for fastforwarding to your faivorite
         set of items , asking a question , but we didn't ...
    </jsp:attribute>
    <jsp:attribute name="rightBar">

        <%@include file="includes/product_banner.jsp" %>
    </jsp:attribute>
</tags:userPageTemplate>