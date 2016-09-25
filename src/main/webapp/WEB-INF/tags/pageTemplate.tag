<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <c:url var="css" value="/miskaweb/css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet" href="${css}" media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
</head>
<body>
<%@include file="/includes/header.jsp" %>
<main>
    <c:if test="${not empty requestScope.error}">
        <div class="red-text center"><br>
            <h5>Error:${requestScope.error}</h5>
            <hr>
        </div>
    </c:if>
    <c:if test="${not empty requestScope.message}">
        <div class="blue-text center"><br>
            <h5>${requestScope.message}</h5>
            <hr>
        </div>
    </c:if>
    <jsp:doBody/>
</main>
<%@include file="/includes/footer.jsp" %>
<!--Import jQuery before materialize.js-->
<c:url var="js" value="/miskaweb/js/materialize.min.js"/>
<script type="text/javascript" src="${js}"></script>
<script>
    $(document).ready(function () {
        $('select').material_select();
    });

    $('.datepicker').pickadate({
        selectMonths: true, selectYears: 2, autoclose: true,
    });


</script>

</body>
</html>
