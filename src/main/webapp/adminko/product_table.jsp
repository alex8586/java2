<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maksims
  Date: 9/8/2016
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="input-field col s12 l12 m12">
    <select>
        <option value="" disabled selected>Choose your option</option>
        <option value="1">Option 1</option>
        <option value="2">Option 2</option>
        <option value="3">Option 3</option>
    </select>
    <label>Materialize Select</label>
</div>

<table class="highlight bordered  ">
    <form action="admin"  method="get">
        <input type="hidden" name="view" value="productEdit">
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


            <td style="border-right: solid 1px">  ${rows.getId()}</td>
            <td>  ${rows.name}</td>
            <td>  ${rows.description}</td>
            <td>  ${rows.remains}</td>
            <td>  ${rows.price}</td>

            <td>
                <button class="btn waves-effect waves-light" type="submit" name="productID" value="${rows.id}">EDIT</button>

                    <%--<input type="submit" name="productID" value="${rows.id}" style="display:none;" />--%>
                    <%--<a href="#prod" class="modal-trigger" onclick="$('#submit${rows.getId()}').click();   ">EDIT</a>--%>

            </td>
            <td>
                <span class='dropdown-button btn' style="width:10em" href='#' data-activates='dropdown1'>Drop Me!</span>
                <ul id='dropdown1' class='dropdown-content'>
                    <c:forEach items="${rows.stock}" var="st">
                        <li>${st.quantity}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        </c:forEach>

</table>

</form>
