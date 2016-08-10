<%@ page import="lv.javaguru.java2.domain.Category" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">

    <div class="col l6 offset-l3 m12 s12 center-align">

        <div class="collection card">
            <h5 class="collection-header">Categories</h5>
                <%
                    Map modelData = (Map)request.getAttribute("model");
                    List<Category> categories = (List<Category>) modelData.get("categories");
                    for (Category category : categories) {%>
                        <a href='category/<%= category.getId()  %>' class='collection-item '><h6><%=category.getName()%></h6></a>
                    <%}
                %>
        </div>
    </div>
</div>
