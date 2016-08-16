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
        <div class="col s6 left-align">
            <form action="profile" method="post">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="fullName" type="text" class="validate" name="fullName">
                        <label for="email">Name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" class="validate" name="email">
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate" name="password">
                        <label for="password">Password</label>
                    </div>
                </div>
            <button class="btn waves-effect waves-light" type="submit" name="register">Change
                <i class="material-icons right">send</i>
            </button>
            </form>
        </div>
        <div class="col s2">
            <%
                Map<String,Object> map = (Map)request.getAttribute("model");
                String error = (String) map.get("profileError");
                if(error != null){%>
            <div class="red-text">
                <h4><%=error%></h4>
            </div>
                <%}
                User user = (User) map.get("user");
                if(user != null){%>
            Your name : <%=user.getFullName()%><br>
            Your email : <%=user.getEmail()%><br>
            Your password : <%=user.getPassword()%><br>
            <% }
            %>
        </div>
    </div>
</main>
<%@include file="includes/footer.jsp" %>
</body>
</html>