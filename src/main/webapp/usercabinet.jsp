
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
        <%@include file="header.jsp"%>

    </nav>
</header>
<main>


    <div class="row">
            <div class="col s4 center">
                <div class="text-accent-4">
                    <h4>Change information</h4>
                </div>

                <div class="input-field">
                    Enter first name and last name
                    <input id="first_last_name" type="text" class="validate" name="fullName">
                </div>
                <div class="input-field">
                    Enter email
                    <input id="email" type="email" class="validate" name="email">
                </div>
                <div class="input-field">
                    Enter password<input id="password" type="password" class="validate" name="password">
                </div>
                <div align="center">
                    <button class="btn waves-effect waves-light" type="submit" name="register">Change
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </div>


    </div>





</main>
<%@include file="footer.jsp"%>
</body>
</html>