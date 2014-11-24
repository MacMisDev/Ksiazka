<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">

    <tiles:putAttribute name="content">
        <%-- OLD CONTENT FOR REFERENCE ONLY
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
                        <td><label>name: </label></td>
                        <td><sf:input path="name" id="name" /></td>
                        <td><sf:errors path="name" cssClas="error" /></td>
                    </tr>
                    <tr>
                        <td><label>username: </label></td>
                        <td><sf:input path="username" id="username" /></td>
                        <td><sf:errors path="username" cssClas="error" /></td>
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
        --%>

        <div id="wrapper">

            <nav>
                <ul>
                    <li>
                        <button id='hideShowButton' class="menuButtonShowHide">Hide</button>
                    </li>
                    <li>
                        <a href="" class="menuButtonHome">Home</a>
                    </li>

                </ul>
            </nav>

            <div id="pageContentWrapper">
                <main class="contentManager">
                    <div id="loginWrapper">

                        <div class="registerGroup loginGroup">

                            <div id="loginBookLockedImage"></div>
                            <div id="ribbon"><p>Zarejestruj się!</p></div>
                            <form method="post" name="f" action="${auth}">
                                <fieldset>
                                    <div class="form-group">
                                        <p>Adres email</p>
                                        <input type="text" name="email" id="email"/>
                                    </div>
                                    <div class="form-group">
                                        <p>Imię</p>
                                        <input type="text" name="name" id="name"/>
                                    </div>
                                    <div class="form-group">
                                        <p>Nazwa użytkownika</p>
                                        <input type="text" name="username" id="username"/>
                                    </div>
                                    <div class="form-group">
                                        <p>Hasło</p>
                                        <input type="password" name="password" id="password"/>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" name="commit" class="btn btn-default">Zarejestruj</button>
                                        <a href="<s:url value="/login" />" class="btn btn-default">Logowanie</a>
                                    </div>

                                </fieldset>
                            </form>


                        </div>

                    </div>
                </main>
            </div>
        </div>

    </tiles:putAttribute>

</tiles:insertDefinition>

