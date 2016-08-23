<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
         Here we could see user cart.
    </jsp:attribute>
    <jsp:attribute name="rightBar">
        some right bar content for a cart
        <hr>
        <%@include file="includes/product_banner.jsp" %>
    </jsp:attribute>
</tags:userPageTemplate>


