<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
         <form action="<tags:linkTo controller="ShippingProfileController" />" method="post">
             <c:if test="${not empty requestScope.model.shippingProfiles}">
                <div class="input-field col s12">
                    <select id="list">
                        <option value="empty">select profile to edit</option>
                        <c:forEach items="${requestScope.model.shippingProfiles}" var="shippingProfile">
                            <option value="${shippingProfile.id}">${shippingProfile.address} ${shippingProfile.person}</option>
                        </c:forEach>
                    </select>
                    <label>Enter new shipping profile , or add </label>
                </div>
                 <script>
                     $('#list').on('change', function (e) {
                         var activeId = $("#list").val();
                         switch (activeId) {
                             case "empty":
                                 $('#profileId').val('');
                                 $('#address').val('');
                                 $('#address_label').removeClass('active');
                                 $('#person').val('');
                                 $('#person_label').removeClass('active');
                                 $('#phone').val('');
                                 $('#phone_label').removeClass('active');
                                 $('#document').val('');
                                 $('#document_label').removeClass('active');
                                 $("#delete_button").attr("disabled", true);
                                 break;
                                 <c:forEach items="${requestScope.model.shippingProfiles}" var="shippingProfile">
                             case "${shippingProfile.id}":
                                 $('#profileId').val('${shippingProfile.id}');
                                 $('#address').val('${shippingProfile.address}');
                                 $('#address_label').addClass('active');
                                 $('#person').val('${shippingProfile.person}');
                                 $('#person_label').addClass('active');
                                 $('#phone').val('${shippingProfile.phone}');
                                 $('#phone_label').addClass('active');
                                 $('#document').val('${shippingProfile.document}');
                                 $('#document_label').addClass('active');
                                 $("#delete_button").attr("disabled", false);
                                 break;
                                 </c:forEach>
                         }
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
                 <div class="input-field col s6">
                     <button class="btn waves-effect waves-light" type="submit" name="save" id="save_button">Save
                         <i class="material-icons right">send</i>
                     </button>
                 </div>
                 <div class="input-field col s6">
                     <button class="btn waves-effect waves-light" type="submit" name="delete" id="delete_button">Delete
                         <i class="material-icons right">delete</i>
                     </button>
                 </div>
                 <script>
                     $("#delete_button").attr("disabled", true);
                     $("#delete_button").click(function () {
                         if ($('#profileId').val) {
                             $("form").attr("action", "/java2/profile/shippingProfile/delete");
                             $("form").submit();
                         }
                     });
                 </script>
             </div>
         </form>
    </jsp:attribute>
    <jsp:attribute name="rightBar">
        errors here
    </jsp:attribute>
</tags:userPageTemplate>


