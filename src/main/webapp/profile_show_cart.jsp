<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="miskaweb/css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>
<header>
    <nav>
        <%@include file="includes/header.jsp" %>
    </nav>
</header>
<main>
    <div class="row">
        <div class="col s4 m2 center">
            <div class="collection card">
                <h5 class="collection-header">Menu</h5>
                <a class="collection-item" href="index"><h6>Main page</h6></a>
                <a class="collection-item" href=""><h6>Update profile</h6></a>
                <a class="collection-item" href=""><h6>Show cart</h6></a>
                <a class="collection-item" href=""><h6>Show history</h6></a>
            </div>
        </div>
        <div class="col s6 ">
           Here we could see user cart.
        </div>
        <div class="col s2">

            <div class="red-text">
                <h4>Next div</h4>
            </div>

        </div>
    </div>
</main>
<%@include file="includes/footer.jsp" %>
</body>
</html>