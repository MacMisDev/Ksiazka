<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">

    <tiles:putAttribute name="content">
        <div>
            <p>Please login!</p>
            <spring:url var="auth" value="/static/j_spring_security_check" />

            <c:if test="${error!=null}">
                <div class="alert alert-danger" role="alert"><c:out value="${error}" /></div>
            </c:if>

            <form method="post" name="f" action="${auth}">
                <fieldset>
                    <table>
                        <tr>
                            <td>Username:</td>
                            <td><input type="text" name="j_username" id="username" /></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" name="j_password" id="password" /></td>
                        </tr>
                        <tr>
                            <td>Remember me?</td>
                            <td><input type="checkbox" name="_spring_security_remember_me" id="remember_me"></td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="commit" value="Submit"></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
            <script type="text/javascript">
                document.getElementsByName('username').focus();
            </script>
        </div>

    </tiles:putAttribute>

</tiles:insertDefinition>

