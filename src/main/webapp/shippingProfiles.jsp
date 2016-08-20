<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:userPageTemplate>
     <jsp:attribute name="content">
         <form action="/profile/shippingProfiles" method="post">
             <c:if test="${not empty requestScope.model.shippingProfiles}">
                <div class="input-field col s12">
                    <select>
                        <option value="empty">select profile to edit</option>
                        <c:forEach items="${requestScope.model.shippingProfiles}" var="shippingProfile">
                            <option value="${shippingProfile.id}">${shippingProfile.address} ${shippingProfile.person}</option>
                        </c:forEach>
                    </select>
                    <label>Choose one of yours saved profiles to edit</label>
                </div>
             </c:if>
             <div class="row">
                 <input id="profileId" type="hidden" class="validate" name="address">
                 <div class="input-field col s12">
                     <input id="address" type="text" class="validate" name="address">
                     <label for="address">Shit to</label>
                 </div>
                 <div class="input-field col s12">
                     <input id="person" type="text" class="validate" name="person">
                     <label for="address">Person</label>
                 </div>
                 <div class="input-field col s12">
                     <input id="phone" type="text" class="validate" name="phone">
                     <label for="phone">Contact phone</label>
                 </div>
                 <div class="input-field col s12">
                     <input id="document" type="text" class="validate" name="document">
                     <label for="document">Document</label>
                 </div>
             </div>
             <button class="btn waves-effect waves-light" type="submit" name="register">Save
                 <i class="material-icons right">send</i>
             </button>
         </form>
    </jsp:attribute>
    <jsp:attribute name="rightBar">
        errors here
    </jsp:attribute>
</tags:userPageTemplate>


