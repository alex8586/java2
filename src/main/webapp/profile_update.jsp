<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
    <jsp:attribute name="content">
         <form action="<tags:linkTo controller="ProfileUpdateController" />" method="post">
             <tags:userProfileForm/>
             <button class="btn waves-effect waves-light" type="submit" name="update">Change
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

        <img src="<c:url value='includes/product_banner.jsp' />"/>

    </jsp:attribute>
</tags:userPageTemplate>
