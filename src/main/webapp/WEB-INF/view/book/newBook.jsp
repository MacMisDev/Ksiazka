<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="content">

        <h2>book/newBook.jsp</h2>

        <div id="bookResponse">

        </div>
        <div>
            Add a book to system:
        </div>
        <div>
            <sf:form id="addBook" action="${pageContext.request.contextPath}/book/new.json" modelAttribute="book">
                <table>
                    <tr>
                        <td><label>isbn: </label></td>
                        <td><sf:input path="ISBN" id="ISBN" /></td>
                        <td><sf:errors path="ISBN" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>Autor: </label></td>
                        <td><sf:input path="author" id="author" /></td>
                        <td><sf:errors path="author" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>Tytul: </label></td>
                        <td><sf:input path="title" id="title" /></td>
                        <td><sf:errors path="title" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>Opis: </label></td>
                        <td><sf:textarea path="description" id="description" /></td>
                        <td><sf:errors path="description" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>Ilosc stron: </label></td>
                        <td><sf:input path="pages" id="pages" /></td>
                        <td><sf:errors path="pages" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>Rok wydawania: </label></td>
                        <td><sf:input path="publicationYear" id="publicationYear" /></td>
                        <td><sf:errors path="publicationYear" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>Wydawca: </label></td>
                        <td><sf:textarea path="publisher" id="publisher" /></td>
                        <td><sf:errors path="publisher" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><input name="submit" type="submit" value="Dodaj" class="btn btn-primary" /></td>
                    </tr>
                </table>
            </sf:form>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>

