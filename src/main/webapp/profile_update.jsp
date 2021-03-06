<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<tags:userPageTemplate>
    <jsp:attribute name="content">
         <form action="${s:mvcUrl('ProfileUpdateController#update').build()}" method="post">
             <tags:userProfileForm/>
             <button class="btn waves-effect waves-light blue" type="submit" name="update">Change
                 <i class="material-icons right">send</i>
             </button>
         </form>
    </jsp:attribute>
    <jsp:attribute name="rightBar">
        <hr>
        <c:if test="${not empty sessionScope.user}">
            Your name : ${sessionScope.user.fullName}<br>
            Your email : ${sessionScope.user.email}<br>
            Your password : ${sessionScope.user.password}<br>
        </c:if>
        <hr>

        <%@include file="includes/product_banner.jsp" %>

    </jsp:attribute>
</tags:userPageTemplate>
