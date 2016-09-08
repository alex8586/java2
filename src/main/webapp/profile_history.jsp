<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
        <div class="row">
            <c:if test="${empty requestScope.model.orderList}">
                <div class="center-align">
                    You have not made a purchase yet.
                </div>
            </c:if>
            <c:if test="${not empty requestScope.model.orderList}">
                <div class="row center-align">

                    List of orders :
                    <hr style="color: orange; width: 200px ">
                </div>
                <div class="row">
                    <div class="col s1 center-align">
                            Id :
                    </div>
                    <div class="col s2 center-align">
                            Person :
                    </div>
                    <div class="col s3 center-align">
                            Address :
                    </div>
                    <div class="col s2 center-align">
                            Order date :
                    </div>
                    <div class="col s2 center-align">
                            Total sum :
                    </div>
                    <div class="col s2 center-align">

                    </div>
                </div>
                <c:forEach items="${requestScope.model.orderList}" var="list">
                    <div class="row">
                        <div class="col s1 center-align">
                            ${list.id}
                        </div>
                        <div class="col s2 center-align">
                            ${list.person}
                        </div>
                        <div class="col s3 left-align">
                            ${list.address}
                        </div>
                        <div class="col s2 center-align">
                            ${list.orderDate}
                        </div>
                        <div class="col s2 center-align">
                            ${list.total} &#8364
                        </div>
                        <div class="col s2 center-align">
                            <a href="<tags:linkTo controller="ProfileOrderController"/>?id=${list.id}">Details</a>
                        </div>
                    </div>

                </c:forEach>
         </c:if>
        </div>
    </jsp:attribute>
    <jsp:attribute name="rightBar">
        some right bar content for a profile history
        <hr>
        <%@include file="/includes/product_banner.jsp" %>
    </jsp:attribute>
</tags:userPageTemplate>
