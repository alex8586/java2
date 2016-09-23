<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s3">
            </div>
            <div class="col s7">
                <div class="col s6">
                    <form action="registration" method="post">
                        <tags:userProfileForm/>
                        <button class="btn waves-effect waves-light" type="submit" name="register">Register me
                            <i class="material-icons right">send</i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="col s2">
                <%@include file="includes/product_banner.jsp" %>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>


