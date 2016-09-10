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
            <div id="products" class="col s12">
                <jsp:include page="/adminko/${requestScope.productScene}"/>
param is - ${requestScope.productScene}
<c:if test="${requestScope.model.productAspect =='table'}">
<table class="highlight bordered responsive-table ">
    <form action=""  method="get">

    <thead>
    <tr>
        <th data-field="id">ID</th>
        <th data-field="name">Product name</th>
        <th data-field="price">Description</th>
        <th data-field="remains">Available</th>
        <th data-field="amount">Price</th>
        <th data-field="amount">Action</th>
        <th data-field="amount">Button</th>
    </tr>
    </thead>

                <c:forEach items="${requestScope.model.tabrows}" var="rows">
            <tr>

                <td style="border-right: solid 1px">  ${rows.get("id")}</td>
                <td>  ${rows.get("Product name")}</td>
                <td>  ${rows.get("Description")}</td>
                <td>  ${rows.get("remains")}</td>
                <td>  ${rows.get("price")}</td>

               <%--<td><input type="text" name="prid" value="${rows.get("id")}"></td>--%>
                <%--<td><a href="#prod" class="btn modal-trigger">test</a> </td>--%>

              <td>
                    <input type="submit" name="productAspect" value="${rows.get("Description")}" id="submit${rows.get("id")}" style="display:none;" />
              <a href="#prod" class="modal-trigger" onclick="$('#submit${rows.get("id")}').click();   ">Sample Submit Trick</a>

</td>
                <td>
<input type="submit" class="btn" name="productAspect" value="single&productID=${rows.get("id")}"/>
                </td>
            </tr>
                </c:forEach>

</table>

</form>
</c:if>

                <div id="prod" class="modal"><h1>kuku - ${param.prd} </h1></div>
         </div>
        </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
