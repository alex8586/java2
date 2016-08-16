<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
        <div class="container">
            <div class="nav-wrapper">
                <a href="<tags:linkTo controller="FrontPageController" />" class="brand-logo">
                    <i class="material-icons">all_inclusive</i>
                    MISKA
                </a>

                <ul id="nav-mobile" class="right hide-on-med-and-down">

                    <%
                        Map<String, Object> model = (Map<String, Object>) request.getAttribute("model");
                        String userName = null;
                        if (model != null) {
                            User user = (User) model.get("user");
                            if (user != null)
                                userName = user.getFullName();
                        }
                        if (userName != null) {%>
                    Hi, <%= userName %>!
                    <%} else {%>
                    <li><a href="<tags:linkTo controller="LoginController" />">LOGIN</a></li>
                    <li><a href="<tags:linkTo controller="RegistrationController" />">REGISTER</a></li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </nav>
</header>


