<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Wymiana książek, wirtualna biblioteczka!</title>
        <!-- Include meta tag to ensure proper rendering and touch zooming -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.3.0/css/bootstrap.min.css" />'>
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    </head>

    <body>
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="content" />
        <tiles:insertAttribute name="footer" />
    </body>
    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/UI.js" />"></script>
    <script src="<c:url value="/resources/js/test.js" />"></script>
    <script src="<c:url value="/webjars/bootstrap/3.3.0/js/bootstrap.min.js" />"></script>
</html>