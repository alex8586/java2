<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:adminTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <%@include file="includes/admin_menu.jsp"%>
            </div>
            <div class="col s10">
                <div class="row">
                    <br>
                    <table class="striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th data-field="name">Person</th>
                                <th data-field="price">Document</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Order date</th>
                                <th>Delivery date</th>
                                <th>Total</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty requestScope.orderList}">
                                <c:forEach items="${requestScope.orderList}" var="order">
                                    <tr>
                                        <td>${order.id}</td>
                                        <td>${order.person}</td>
                                        <td>${order.document}</td>
                                        <td>${order.address}</td>
                                        <td>${order.phone}</td>
                                        <td>${order.orderDate}</td>
                                        <td>${order.deliveryDate}</td>
                                        <td>${order.total} &euro;</td>
                                        <td>
                                            <c:if test="${order.status eq 'Done'}">
                                                ${order.status}
                                            </c:if>
                                            <c:if test="${order.status eq 'In progress'}">
                                                <form action="<c:url value='/orders/accept'/>" method="post">
                                                    <button class="btn waves-effect waves-light blue" type="submit">Accept
                                                        <input type="hidden" value="${order.id}" name="orderId">
                                                    </button>
                                                </form>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:adminTemplate>
