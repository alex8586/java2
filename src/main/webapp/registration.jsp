<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="includes/header.jsp"%>
<main>

        <%
            Map<String,Object> map = (Map)request.getAttribute("model");
            String error = (String) map.get("registrationError");
            if(error != null){%>
                <div class="col s8 center red-text"><br>
                    <h4><%=error%></h4>
                </div>
            <%}
        %>

        <form class="col s8" action="register" method="post">
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