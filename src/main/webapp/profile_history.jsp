<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s3 center">
                <div class="collection card">
                    <h5 class="collection-header">Menu</h5>
                    <a class="collection-item" href="profile"><h6>Main page</h6></a>
                    <a class="collection-item" href="profile_update"><h6>Update profile</h6></a>
                    <a class="collection-item" href="profile_cart"><h6>Show cart</h6></a>
                    <a class="collection-item" href="profile_history"><h6>Show history</h6></a>
                </div>
            </div>
            <div class="col s7">
                Here we could see user history.
            </div>
            <div class="col s2">
                <hr>
                <div class="red-text">
                    <h4>Some information here</h4>
                </div>
                <hr>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
