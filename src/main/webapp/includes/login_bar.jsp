
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String name = (String) request.getAttribute("model");
if(name != null){%>
<div class="right">
Welcome , <%=name%>
    <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="index.jsp">LOGOUT</a></li>
        <li><a href="../usercabinet.jsp">CABINET</a></li>
    </ul>
</div>
<%}else{%>
<ul id="nav-mobile" class="right hide-on-med-and-down">
    <li><a href="login">LOGIN</a></li>
    <li><a href="register">REGISTER</a></li>
</ul>
<%}%>