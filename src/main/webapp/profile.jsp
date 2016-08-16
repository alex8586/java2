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
    <%
        Map<String, Object> map = (Map) request.getAttribute("model");
        String error = (String) map.get("profileError");
        if (error != null) {%>
    <div class="col s8 center red-text"><br>
        <h4><%=error%>
        </h4>
    </div>
    <%
        }
        User user = (User) map.get("user");
        if (user != null) {
    %>
    Your name : <%=user.getFullName()%><br>
    Your email : <%=user.getEmail()%><br>
    Your password : <%=user.getPassword()%><br>
    <% }
    %>

    <div class="row">
        <div class="col s4 center">
            <div class="text-accent-4">
                <h4>Change information</h4>
            </div>
            <form class="col s8" action="profile" method="post">
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
            </form>
        </div>


    </div>


</main>
<%@include file="includes/footer.jsp" %>
</body>
</html>