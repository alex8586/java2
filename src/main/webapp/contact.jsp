<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:pageTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
                <%@include file="includes/category.jsp" %>
            </div>
            <div class="col s8">
                <div class="row">
                    <div class="col s6">
                        <br>
                        <div id="map" class="map center">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d4353.0958750312!2d24.1523365449768!3d56.93941302164916!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sru!2slv!4v1471890362365"
                                    width="400" height="300" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>
                    </div>
                    <div class="col s6">
                        <br>
                        <div>
                            <ul>
                                <li class="collection-item valign-wrapper">
                                    <i class="material-icons tiny left">home</i>
                                    <p>Adress: Lomonosova street 1, Riga, LV-1019, Latvia</p>
                                </li>
                                <li class="collection-item valign-wrapper">
                                    <i class="material-icons tiny left">phone</i>
                                    <p>Phone : +371 29876543</p>
                                </li>
                                <li class="collection-item valign-wrapper">
                                    <i class="material-icons tiny left">email</i>
                                    <p>Email: info@miska.lv</p>
                                </li>
                            </ul>
                        </div>
                        <div>
                            Working hours :
                            <p>Monday - Thirsday  09:00 - 17:00</p>
                            <p>Friday 09:00 - 15:00</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col s2">
                <hr>
                <%@include file="includes/cart.jsp" %>
                <hr>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
