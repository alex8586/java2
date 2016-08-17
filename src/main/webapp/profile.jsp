<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <%@include file="includes/header.jsp" %>

<main>
    <div class="row">
        <div class="col s3">
            <div class="collection card center">
                <h5 class="collection-header">Menu</h5>
                <a class="collection-item" href="profile"><h6>Profile main</h6></a>
                <a class="collection-item" href="profile_update"><h6>Update profile</h6></a>
                <a class="collection-item" href="profile_cart"><h6>Show cart</h6></a>
                <a class="collection-item" href="profile_history"><h6>Show history</h6></a>
            </div>
        </div>
        <div class="col s7">
            Here is the main profile.
        </div>
        <div class="col s2">

            <div class="red-text">
                <hr>
                <h4>Some information here</h4>
                <hr>
            </div>

        </div>
    </div>
</main>
<%@include file="includes/footer.jsp" %>
</body>
</html>