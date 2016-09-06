<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                right column here right column here right column here right column here
            </div>
            <div class="col s8">
                        <div class="row center">
                            <br>
                            In your cart:
                            <hr style="color: orange; width: 200px ">
                        </div>
                <c:if test="${not empty requestScope.checkoutCart}">
                    <c:forEach var="cart" items="${requestScope.checkoutCart.all}">
                        <div class="row">
                            <div class="col s2">
                                <img src="${cart.key.imgUrl}" height="50" width="50">
                            </div>
                            <div class="col s6 left-align">
                                ${cart.key.name}
                            </div>
                            <div class="col s2 center-align">
                                    ${cart.value} items
                            </div>
                            <div class="col s2 center-align">
                                    ${cart.key.price} &#8364
                            </div>
                        </div>
                        </c:forEach>
                        </c:if>
                        <div class="row">
                            <div class="col s8"></div>
                            <div class="col s2 center-align">
                                <b>Total :</b>
                            </div>
                            <div class="col s2 center-align">
                                <b>${requestScope.checkoutCart.totalPrice}&#8364</b>
                            </div>
                        </div>
                        <hr style="color: whitesmoke">
                        <div class="row">
                            <form action="order" method="post">
                                <c:if test="${not empty requestScope.model.shippingProfiles}">
                                    <div class="input-field col s12">
                                        <select id="list">
                                            <option value="empty">select profile</option>
                                            <c:forEach items="${requestScope.model.shippingProfiles}" var="shippingProfile">
                                                <option value="${shippingProfile.id}">${shippingProfile.address} ${shippingProfile.person}</option>
                                            </c:forEach>
                                        </select>
                                        <label>choose from existing shipping profiles </label>
                                    </div>
                                    <script>

                                        var spMap = {};
                                        spMap['empty'] = {'profileId': '', 'address': '', 'person': '', 'phone': '', 'document': ''};
                                        <c:forEach items="${requestScope.model.shippingProfiles}" var="shippingProfile">
                                        spMap['${shippingProfile.id}'] = {
                                            'profileId': '${shippingProfile.id}',
                                            'address': '${shippingProfile.address}',
                                            'person': '${shippingProfile.person}',
                                            'phone': '${shippingProfile.phone}',
                                            'document': '${shippingProfile.document}'
                                        };
                                        </c:forEach>

                                        $('#list').on('change', function (e) {
                                            var id = $("#list").val();
                                            var record = spMap[id];
                                            for (var key in record) {
                                                var value = record[key];
                                                $('#' + key).val(value);
                                                if (value.length > 0) {
                                                    $('#' + key + '_label').addClass('active');
                                                }
                                                else {
                                                    $('#' + key + '_label').removeClass('active');
                                                }
                                            }
                                            $("#delete_button").attr("disabled", id == 'empty');
                                        });

                                    </script>
                                </c:if>
                                <div class="row">
                                    <input id="profileId" type="hidden" class="validate" name="profileId">
                                    <div class="input-field col s12">
                                        <input id="address" type="text" class="validate" name="address">
                                        <label id="address_label" for="address">Ship to</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input id="person" type="text" class="validate" name="person">
                                        <label id="person_label" for="person">Person</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input id="phone" type="text" class="validate" name="phone">
                                        <label id="phone_label" for="phone">Contact phone</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input id="document" type="text" class="validate" name="document">
                                        <label id="document_label" for="document">Document</label>
                                    </div>
                                    <div class="input-field col s12 center-align">
                                        <button class="btn waves-effect waves-light" type="submit" name="save" id="save_button">Buy
                                            <i class="material-icons right">payment</i>
                                        </button>
                                    </div>

                                </div>
                            </form>
                        </div>
            </div>
            <div class="col s2">
                <%@include file="includes/product_banner.jsp" %>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
