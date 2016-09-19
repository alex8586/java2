<%@ tag import="lv.javaguru.java2.servlet.mvc.PathResolver" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="controller" required="true" %>
<%@attribute name="resourceId" required="false" %>

<%
    PathResolver pathResolver = (PathResolver) application.getAttribute("reverseRouter");
%>







