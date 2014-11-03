<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="content">
        <h2>book/list.jsp</h2>


        <div>
            Ilosc stron : <c:out value="${maxPages}" />
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

        <ul class="pagination">
            <c:choose>
                <c:when test="${currentPage==0}">
                    <li class="disabled"><a href="#">&laquo;</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/ksiazka/book/list?page=${currentPage-1}">&laquo;</a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="0" end="${maxPages}">
                <c:choose>
                    <c:when test="${i==currentPage}">
                        <li class="active"><a href="#">${i+1}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/ksiazka/book/list?page=${i}">${i+1}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage==maxPages}">
                    <li class="disabled"><a href="#">&raquo;</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/ksiazka/book/list?page=${currentPage+1}">&raquo;</a></li>
                </c:otherwise>
            </c:choose>
        </ul>


    </tiles:putAttribute>
</tiles:insertDefinition>

