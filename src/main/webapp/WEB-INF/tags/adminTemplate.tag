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

    <jsp:doBody/>

</main>
<%@include file="/includes/footer.jsp" %>

<c:url var="js" value="/miskaweb/js/materialize.min.js"/>
<script type="text/javascript" src="${js}">

    $(document).ready(function(){
        $('.collapsible').collapsible({
            accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
        });
    });

</script>

</body>
</html>