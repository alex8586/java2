
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
                <a href="index" class="brand-logo"><i class="material-icons">all_inclusive</i> MISKA</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="login">LOGIN</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<main>

        <% String error = (String) request.getAttribute("model");
            if(error != null)%>
            <div class="col s8 center red-text">
                <br>
                <h4><%=error%></h4>
            </div>

        <form class="col s8" action="tryregister" method="post">

            <div class="row">
                <div class="input-field col s4 offset-s4">
                    Enter first name and last name
                    <input id="first_last_name" type="text" class="validate" name="fullName">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s4 offset-s4">
                    Enter email
                    <input id="email" type="email" class="validate" name="email">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s4 offset-s4">
                    Enter password<input id="password" type="password" class="validate" name="password">
                </div>
            </div>
            <div align="center">
            <button class="btn waves-effect waves-light" type="submit" name="register">Register me
                <i class="material-icons right">send</i>
            </button>
            </div>
        </form>


</main>
<%@include file="includes/footer.jsp"%>
</body>
</html>