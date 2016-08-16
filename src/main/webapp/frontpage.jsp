<%@ page import="lv.javaguru.java2.domain.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! Map<String,Object> model; %>
<%! List<Product> products; %>
<%@include file="includes/header.jsp"%>



    <%@include file="includes/category.jsp"%>
<div class="section"><a href="#" data-activates="slide-out" class="btn button-collapse"><i class="material-icons right">menu</i>CATEGORIES</a></div>
<div class="row">
<%  model = (Map<String, Object>) request.getAttribute("model");
products = (List<Product>) model.get("products");
    for (Product product:products
         ) {%>


    <div class="col l3 s12 m7">

        <div class="card horizontal">
            <div class="card-image">
                <img src="http://lorempixel.com/100/190/nature/10">
            </div>
            <div class="card-stacked">

                <div class="card-content">
                    <span class="card-title"><%=product.getDisplayName()%></span>
                    <p><%=product.getDisplayDescription()%></p>
                </div>

                <div class="card-action">
                    <a href="#">This is a link</a>
                </div>
            </div>
        </div>
    </div>

   <%
    }

%>
</div>

<%@include file="includes/footer.jsp"%>
