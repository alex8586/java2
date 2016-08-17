<%@ page import="lv.javaguru.java2.domain.Category" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<main>
    <div class="row">
        <div class="col s3">
            <div class="collection card center">
                <h5 class="collection-header">Categories</h5>
                <%
                    if (request.getAttribute("model") instanceof Map) {
                        Map modelData = (Map) request.getAttribute("model");
                        if (modelData != null) {
                            List<Category> categories = (List<Category>) modelData.get("categories");

                            for (Category category : categories) {%>
                <a class="collection-item" href='category/<%= category.getId() %>'><h6><%=category.getName()%>
                </h6></a>
                <%}%>
            </div>
        </div>
        <div class="col s7">
            <%
                List<Product> products = (List<Product>) modelData.get("products");
                for (Product product : products) {%>
            <div class="col s3">
                <div class="card vertical-grabber">
                    <div class="card-title center">
                        <%=product.getDisplayName()%>
                    </div>
                    <div class="card-image">
                        <img src="miskaweb/img/product.jpg" height="130" width="130">
                    </div>
                    <div class="card-content left-align">
                        <%=product.getDisplayDescription()%>
                        <hr>
                        Price : <%=product.getPrice()%> &#8364
                    </div>
                    <div class="card-action center">
                        <a href="#">Add to cart</a>
                    </div>
                </div>
            </div>
            <%
                        }
                    }
                }
            %>
        </div>
        <div class="col s2">
            <hr>
            <%@include file="cart.jsp" %>
            <hr>
        </div>
    </div>
</main>