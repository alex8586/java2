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
<script type="text/javascript" src="${js}"></script>
<script>
    $(document).ready(function () { $('select').material_select();     });
    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
    });

    $(document).ready(function() {  $('select').material_select();     });

    $('.dropdown-button').dropdown({
                inDuration: 300,
                outDuration: 225,
                constrain_width: false, // Does not change width of dropdown to that of the activator
                hover: true, // Activate on hover
                gutter: 1, // Spacing from edge
                belowOrigin: true, // Displays dropdown below the button
                alignment: 'left' // Displays dropdown with edge aligned to the left of button
            }
    );
</script>

</body>
</html>
