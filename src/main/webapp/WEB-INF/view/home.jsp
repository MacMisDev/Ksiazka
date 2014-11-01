<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="content">
        <h2>home.jsp</h2>

        Hello!
        <a href="/ksiazka/static/j_spring_security_logout"> <input type="submit" name="logout" value="logout" /></a>
      
    </tiles:putAttribute>
</tiles:insertDefinition>

