<%--
  Created by IntelliJ IDEA.
  User: Maksims
  Date: 9/8/2016
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>product edit</h1>

<form action="adminaa?aspect=single" method="get">

    <label>Name</label>
<input type="text" name="name" value="${requestScope.model.productname}"/>

    <button type="submit" >submit</button>
</form>
