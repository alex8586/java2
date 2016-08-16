<%@ page import="lv.javaguru.java2.domain.Category" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="slide-out" class="side-nav">
                <%
                    if(request.getAttribute("model") instanceof Map){
                        Map modelData = (Map)request.getAttribute("model");
                        if(modelData != null){
                            List<Category> categories = (List<Category>) modelData.get("categories");
                            for (Category category : categories) {%>
                                <li><a href='category/<%= category.getId()  %>'><%=category.getName()%></a></li>
                            <%}
                        }
                    }
                %>
                    </ul>

