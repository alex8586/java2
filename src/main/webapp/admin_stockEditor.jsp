<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:adminTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <%@include file="includes/admin_menu.jsp" %>
            </div>
            <div class="col s10">
                <div class="row card-panel">
                    <form action="<c:url value='/stockEditor/edit'/>" method="post">
                    <div class="row">
                        <div class="col s7">
                            Choose stock product : <br><br>
                            <select class="browser-default" name="id">
                                <c:forEach items="${requestScope.stockProducts}" var="stockLine">
                                    <option selected value="${stockLine.id}">
                                            ${stockLine.productName},&nbsp;
                                        expire date ${stockLine.expireDate},&nbsp;
                                        current stock ${stockLine.quantity}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col s5">

                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s2">
                            <input placeholder="" type="text" class="validate" id="stock" name="quantity">
                            <label class="active" for="stock">Enter stock</label>
                        </div>
                        <div class="col s2">

                        </div>
                        <div class="input-field col s2">
                            <input type="date" class="datepicker" id="expireDate" name="expireDate">
                            <label class="active" for="expireDate">Exprire date</label>
                        </div>
                        <div class="col s6 center-align">
                            <br>
                            <button class="btn waves-effect waves-light blue" type="submit" name="edit">Edit
                                <i class="material-icons right">description</i>
                            </button><br><br>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:adminTemplate>