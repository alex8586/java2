<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:adminTemplate>
    <jsp:body>
        <div class="row">
            <div class="col s2">
            <%@include file="includes/admin_menu.jsp"%>
            </div>
            <div class="col s5">
                <c:if test="${not empty requestScope.categories}">
                <ul class="collapsible popout" data-collapsible="accordion">
                    <c:forEach items="${requestScope.categories}" var="category">
                    <li>
                        <div class="collapsible-header grey lighten-4">
                            <div class="row">
                                <div class="col s3">
                                    id ${category.id}
                                </div>
                                <div class="col s3">
                                    ${category.name}
                                </div>
                                 <div class="col s6">

                                 </div>
                            </div>
                        </div>
                        <div class="collapsible-body">
                            <div class="row" style="padding-bottom: 10px;margin-bottom: auto;border-bottom: 1px solid rgba(160, 160, 160, 0.2)">
                                <form action="<c:url value='category/edit'/> " method="post">
                                <div class="col s7">
                                    <br>
                                    Change category name :<br><br>
                                    <input value="${category.name}" type="text" name="name">
                                </div>
                                <div class="col s5 center-align">
                                    <br>
                                    <button class="btn waves-effect waves-light blue" type="submit">Edit
                                        <i class="material-icons right">description</i>
                                        <input type="hidden" value="${category.id}" name="id">
                                        <input type="hidden" value="${category.fatherId}" name="father_id">
                                    </button>
                                </div>
                                </form>
                            </div>
                            <div class="row" style="margin-bottom:5px">
                                <div class="col s7">
                                    <br>Delete category :
                                </div>
                                <div class="col s5 center-align">
                                    <br>
                                    <form action="<c:url value='category/delete'/> " method="post">
                                        <button class="btn waves-effect waves-light blue" type="submit" name="delete">Delete
                                            <i class="material-icons right">delete</i>
                                            <input type="hidden" value="${category.id}" name="deleteId">
                                        </button>
                                    </form>
                                    <br>
                                    <form action="<c:url value='/category/addChild'/> " method="post">
                                        <button class="btn waves-effect waves-light blue" type="submit" name="addChild">
                                            Add Child
                                            <i class="material-icons right">playlist_add</i>
                                            <input type="hidden" value="${category.id}" name="fatherId">
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
                </c:if>
            </div>
            <div class="col s5">

            </div>
        </div>
    </jsp:body>
</tags:adminTemplate>