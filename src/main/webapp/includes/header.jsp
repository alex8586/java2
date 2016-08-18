<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <div class="row">
                <div class="col s2">
                <a href="<tags:linkTo controller="FrontPageController" />" class="brand-logo">
                    <i class="material-icons">all_inclusive</i>
                    MISKA
                </a>
                </div>
                <div class="col s9">
                <ul class=" hide-on-med-and-down">
                    <li><a href="">NEWS</a></li>
                    <li><a href="">SALE</a></li>
                    <li><a href="">YOUR IDEA</a></li>
                    <li><a href="">YOUR IDEA</a></li>
                    <li><a href="">SHIPPING</a></li>
                    <li><a href="">CONTACT</a></li>
                    <li><a href="<tags:linkTo controller="ProfileController" />">PROFILE</a></li>
                    <li><a href="<tags:linkTo controller="LogoutController" />">LOGOUT</a></li>
                    <c:if test="${not empty sessionScope.user}">
                        Hi ${sessionScope.user.fullName}!
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <li><a href="">NEWS</a></li>
                        <li><a href="">SALE</a></li>
                        <li><a href="">YOUR IDEA</a></li>
                        <li><a href="">YOUR IDEA</a></li>
                        <li><a href="">SHIPPING</a></li>
                        <li><a href="">CONTACT</a></li>
                        <li><a href="<tags:linkTo controller="LoginController" />">LOGIN</a></li>
                        <li><a href="<tags:linkTo controller="RegistrationController" />">REGISTER</a></li>
                    </c:if>
                </ul>
                </div>
            </div>
    </nav>
</header>

