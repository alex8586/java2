<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
         <form action="<c:url value='/profile/shippingProfiles/save'/>" method="post">
             <c:if test="${not empty requestScope.shippingProfiles}">
                <div class="input-field col s12">
                    <select id="list">
                        <option value="empty">select profile to edit</option>
                        <c:forEach items="${requestScope.shippingProfiles}" var="shippingProfile">
                            <option value="${shippingProfile.id}">${shippingProfile.address} ${shippingProfile.person}</option>
                        </c:forEach>
                    </select>
                    <label>Enter new shipping profile , or add </label>
                </div>
                 <script>

                     var spMap = {};
                     spMap['empty'] = {'profileId': '', 'address': '', 'person': '', 'phone': '', 'document': ''};
                     <c:forEach items="${requestScope.shippingProfiles}" var="shippingProfile">
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
                 <div class="input-field col s6">
                     <button class="btn waves-effect waves-light blue" type="submit" name="save" id="save_button">Save
                         <i class="material-icons right">send</i>
                     </button>
                 </div>
                 <div class="input-field col s6">
                     <button class="btn waves-effect waves-light blue" type="submit" name="delete" id="delete_button">Delete
                         <i class="material-icons right">delete</i>
                     </button>
                 </div>
                 <script>
                     $("#delete_button").attr("disabled", true);
                     $("#delete_button").click(function () {
                         if ($('#profileId').val) {
                             $("form").attr("action", "<c:url value='/profile/shippingProfiles/delete'/>");
                             $("form").submit();
                         }
                     });
                 </script>
             </div>
         </form>
    </jsp:attribute>
    <jsp:attribute name="rightBar">
        <%@include file="/includes/product_banner.jsp" %>
    </jsp:attribute>
</tags:userPageTemplate>


