<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="content">

        <div>
            Ilosc stron : <c:out value="${maxBooks}" />
        </div>
        <c:forEach items="${lastBooks}" var="book">
            <table>
                <tr>
                    <td>ISBN : </td><td><c:out value="${book.ISBN}" /></td>
                </tr>
                <tr>
                    <td>Tytul : </td><td><c:out value="${book.title}" /></td>
                </tr>
                <tr>
                    <td>Autor : </td><td><c:out value="${book.author}" /></td>
                </tr>
            </table>
            <br />
        </c:forEach>


    </tiles:putAttribute>
</tiles:insertDefinition>

