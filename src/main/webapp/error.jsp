<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header.jsp"%>
<% String error = (String) request.getAttribute("model");%>
    <div class="container ">
        <div class="offset-s4 center">
            <br><br><br>
            <h3>404  Error</h3><br><br>
            <%=error%>
        </div>
    </div>
<%@include file="includes/footer.jsp"%>

