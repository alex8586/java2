<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate>
    <jsp:body>
        <div class="container">
        <div class="row">

            <ul class="tabs">
                <li class="tab col s3"><a href="#products">PRODUCTS</a></li>
                <li class="tab col s3 disabled"><a href="#test3">Disabled Tab</a></li>
                <li class="tab col s3"><a href="#test4">Test 4</a></li>
            </ul>
            <div id="products" class="col s12 l12 m12">

                <h1>${requestScope.model.productView.huy}</h1>
                <jsp:include page="adminko/${requestScope.model.productView.jspFile}"/>

         </div>
        </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
