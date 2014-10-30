<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">

    <tiles:putAttribute name="content">
        <h2>REGISTER</h2>
        <sf:form method="POST" modelAttribute="user" action="register">
            <fieldset>
                <table>
                    <tr>
                        <td><label>email: </label></td>
                        <td><sf:input path="email" id="email" /></td>
                        <td><sf:errors path="email" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>password: </label></td>
                        <td><sf:password path="password" id="password" /></td>
                        <td><sf:errors path="password" cssClas="error" /></td>
                    </tr>
                </table>
                <input name="submit" type="submit" value="Submit" class="btn btn-primary" />
            </fieldset>
        </sf:form>

    </tiles:putAttribute>

</tiles:insertDefinition>

