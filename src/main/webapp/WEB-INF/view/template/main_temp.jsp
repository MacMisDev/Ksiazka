<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Wymiana książek, wirtualna biblioteczka!</title>
        <!-- Include meta tag to ensure proper rendering and touch zooming -->
        <meta name="viewport" content="width=device-width, initial-scale=2">
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.3.0/css/bootstrap.min.css" />'>
        <link href="<c:url value="/webjars/parsleyjs/2.0.5/parsley.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <link href="<c:url value="webjars/animate.css/3.2.0/animate.min.css" />" rel="stylesheet">
    </head>

    <body>
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="content" />
    </body>
    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js" />"></script>
    <script src="<c:url value="/webjars/parsleyjs/2.0.5/parsley.min.js" />"></script>
    <script src="<c:url value="/resources/js/UI.js" />"></script>
    <script src="<c:url value="/resources/js/test.js" />"></script>
    <script src="<c:url value="/resources/js/validators.js" />"></script>
    <script src="<c:url value="/webjars/bootstrap/3.3.0/js/bootstrap.min.js" />"></script>
</html>