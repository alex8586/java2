
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="miskaweb/css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>
<header>
    <nav>
        <div class="container">


            <div class="nav-wrapper">
                <a href="frontpageSkeleton.jsp" class="brand-logo"><i class="material-icons">all_inclusive</i> MISKA</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href=login.jsp>LOGIN</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<main>

    <div class="center-align" >
        <form class="col s8" action="registration" method="post">

            <div class="row">
                <div class="input-field col s4 center-align">
                    Enter first name and last name
                    <input id="first_last_name" type="text" class="validate" name="fullName">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s4">
                    Enter email
                    <input id="email" type="email" class="validate" name="email">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s4">
                    Enter password<input id="password" type="password" class="validate" name="password">
                </div>
            </div>

            <button class="btn waves-effect waves-light" type="submit" name="register">Register me
                <i class="material-icons right">send</i>
            </button>

        </form>
    </div>

</main>
<%@include file="footer.jsp"%>
</body>
</html>