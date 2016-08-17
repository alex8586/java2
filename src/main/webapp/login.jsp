<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@include file="includes/header.jsp"%>

<main>
        <%
            Map<String,Object> map = (Map)request.getAttribute("model");
            String error = (String) map.get("loginError");
            if(error != null){%>
                 <div class="col s8 center red-text"><br>
                    <h4><%=error%></h4>
                </div>
            <%}
        %>

    <div class="row">
        <div class="col s4 m2 center">

        </div>
        <div class="col s6 left-align">
            <form action="login" method="post">
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

        </div>

    </div>

</main>
<%@include file="includes/footer.jsp"%>
</body>
</html>