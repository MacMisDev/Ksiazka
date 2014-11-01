<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <title>Wymiana książek, wirtualna biblioteczka!</title>
        <!-- Include meta tag to ensure proper rendering and touch zooming -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel='stylesheet' href='webjars/bootstrap/3.3.0/css/bootstrap.min.css'>
        <!-- Latest compiled and minified JavaScript -->
        <script src="webjars/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    </head>

    <body>
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="content" />
        <tiles:insertAttribute name="footer" />
    </body>
</html>