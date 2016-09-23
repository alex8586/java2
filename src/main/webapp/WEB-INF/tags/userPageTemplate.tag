<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="rightBar" fragment="true" %>

<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <div class="collection card center blue-text">
                    <h5 class="collection-header">Menu</h5>
                    <a class="collection-item blue-text" href="profile"><h6>Profile
                        main</h6></a>
                    <a class="collection-item blue-text" href="profileUpdate"><h6>Update
                        profile</h6></a>
                    <a class="collection-item blue-text" href="<tags:linkTo controller="ProfileCartController"/>"><h6>Show
                        cart</h6></a>
                    <a class="collection-item blue-text" href="profileHistoryOrders"><h6>Show
                        history of orders</h6></a>
                    <a class="collection-item blue-text" href="shippingProfiles"><h6>Shipping
                        profiles</h6></a>
                </div>
            </div>
            <div class="col s8">
                <jsp:invoke fragment="content"/>
            </div>
            <div class="col s2">
                <jsp:invoke fragment="rightBar"/>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>