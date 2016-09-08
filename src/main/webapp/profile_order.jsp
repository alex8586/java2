<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
         <div class="row">
             <c:if test="${empty requestScope.model.order}">
                 <div class="center-align" style="color: red">
                     <br>
                     <h5>Error ! No such order !</h5>
                 </div>
             </c:if>
             <c:if test="${not empty requestScope.model.order}">
                 <div class="center-align">
                     <br>
                     Order Nr. ${requestScope.model.order.id}
                     <hr style="color: orange; width: 200px ">
                 </div>
                 <div class="row ">
                     <div class="col s3">
                         Person :
                     </div>
                     <div class="col s9">
                         ${requestScope.model.order.person}
                     </div>
                     <div class="col s3">
                         Document :
                     </div>
                     <div class="col s9">
                         ${requestScope.model.order.document}
                     </div>
                     <div class="col s3">
                        Address :
                     </div>
                     <div class="col s9">
                        ${requestScope.model.order.address}
                     </div>
                     <div class="col s3">
                        Phone :
                     </div>
                     <div class="col s9">
                        ${requestScope.model.order.phone}
                     </div>
                     <div class="col s3">
                        Order date :
                     </div>
                     <div class="col s9">
                        ${requestScope.model.order.orderDate}
                     </div>
                     <div class="col s3">
                        Delivery date :
                     </div>
                     <div class="col s9">
                        ${requestScope.model.order.deliveryDate}
                     </div>
                     <div class="col s3">
                        Total sum :
                     </div>
                     <div class="col s9">
                             ${requestScope.model.order.total} &#8364
                     </div>
                 </div>
                 <div class="row">
                     <div class="col s9">

                     </div>
                     <div class="col s3">
                         <a href="<tags:linkTo controller="ProfileHistoryOrdersController"/>"><h5>Back</h5></a>
                     </div>
                 </div>
             </c:if>
         </div>
    </jsp:attribute>
    <jsp:attribute name="rightBar">
        some right bar content for a cart
        <hr>
        <%@include file="includes/product_banner.jsp" %>
    </jsp:attribute>
</tags:userPageTemplate>

