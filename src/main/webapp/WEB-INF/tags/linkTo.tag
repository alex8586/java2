<%@ tag import="lv.javaguru.java2.servlet.ReverseRouter" %>
<%@attribute name="controller" required="true" %>
<%@attribute name="resourceId" required="false" %>
<%
    ReverseRouter reverseRouter = (ReverseRouter) application.getAttribute("reverseRouter");
    if (resourceId == null) {
        out.println(reverseRouter.linkTo(controller));
    } else {
        out.println(reverseRouter.linkTo(controller, resourceId));
    }
%>




