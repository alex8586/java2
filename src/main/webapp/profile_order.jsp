<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
         <div class="row">
             <c:if test="${empty requestScope.order}">
                 <div class="center-align" style="color: red">
                     <br>
                     <h5>Error ! No such order !</h5>
                 </div>
             </c:if>
             <c:if test="${not empty requestScope.order}">
                 <div class="center-align">
                     <br>
                     Order Nr. ${requestScope.order.id}
                     <hr style="color: orange; width: 200px ">
                 </div>
                 <div class="row ">
                     <div class="col s3">
                         Person :
                     </div>
                     <div class="col s9">
                             ${requestScope.order.person}
                     </div>
                     <div class="col s3">
                         Document :
                     </div>
                     <div class="col s9">
                             ${requestScope.order.document}
                     </div>
                     <div class="col s3">
                         Address :
                     </div>
                     <div class="col s9">
                             ${requestScope.order.address}
                     </div>
                     <div class="col s3">
                         Phone :
                     </div>
                     <div class="col s9">
                             ${requestScope.order.phone}
                     </div>
                     <div class="col s3">
                         Order date :
                     </div>
                     <div class="col s9">
                             ${requestScope.order.orderDate}
                     </div>
                     <div class="col s3">
                         Delivery date :
                     </div>
                     <div class="col s9">
                             ${requestScope.order.deliveryDate}
                     </div>
                 </div>
                 <div class="row">
                     <div class="col s12">
                         Your order contains :
                     </div>
                 </div>
                    <div class="row">
                        <div class="col s2 center-align">
                            Product name :
                        </div>
                        <div class="col s4 center-align">
                            Description :
                        </div>
                        <div class="col s2 center-align">
                            ExpireDate :
                        </div>
                        <div class="col s2 center-align">
                            Quantity :
                        </div>
                        <div class="col s2 center-align">
                            Price :
                        </div>
                    </div>
                 <c:if test="${not empty requestScope.order}">
                     <c:forEach items="${requestScope.order.orderLines}" var="product">
                      <div class="row">
                          <div class="col s2">
                                ${product.name}
                          </div>
                          <div class="col s4">
                                ${product.description}
                          </div>
                          <div class="col s2 center-align">
                                ${product.expireDate}
                          </div>
                          <div class="col s2 center-align">
                                ${product.quantity}
                          </div>
                          <div class="col s2 center-align">
                                ${product.price}
                          </div>
                      </div>
                     </c:forEach>
                     <div class="row">
                         <div class="col s8">

                         </div>
                         <div class="col s2 center-align">
                             <b>Total sum :</b>
                         </div>
                         <div class="col s2 center-align">
                                 <b>${requestScope.order.total} &#8364</b>
                         </div>
                     </div>
                 </c:if>

                 <div class="row">
                     <div class="col s8">

                     </div>
                     <div class="col s4">
                         <a href="<c:url value='/profile/orders'/>"><h5>Back to history</h5>
                         </a>
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

