<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tiles:insertDefinition name="template">

    <tiles:putAttribute name="content">
        <%--<div>
            <p>Please login!</p>
            <spring:url var="auth" value="/static/j_spring_security_check" />

            <c:if test="${error!=null}">
                <div class="alert alert-danger" role="alert"><c:out value="${error}" /></div>
            </c:if>
            <c:if test="${logout!=null}">
                <div class="alert alert-info" role="alert"><c:out value="${logout}" /></div>
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
                            <td><a href="<s:url value="/register" />"> Register </a></td>
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
        </div>--%>
        <spring:url var="auth" value="${pageContext.request.contextPath}/static/j_spring_security_check"/>
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
                <main class="contentManagerCenter">
                    <!--<div style="margin-top:100px;border:2px solid red;">dsdsd</div>-->
                    <div id="formWrapper">

                        <div class="formTemplate loginGroup">

                            <div id="loginBookLockedImage"></div>
                            <div id="ribbon"><p>Zaloguj się!</p></div>
                            <form method="post" name="f" action="${auth}">
                                <fieldset>
                                    <c:if test="${(not empty SPRING_SECURITY_LAST_EXCEPTION)}">
                                        <div class="formErrMsg">
                                            Zły email lub hasło!
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <p>Email</p>
                                        <input type="email" name="j_username" id="username"/>
                                    </div>
                                    <div class="form-group">
                                        <p>Hasło</p>
                                        <input type="password" name="j_password" id="password"/>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" name="commit" class="btn btn-default">Zaloguj</button>
                                        <a href="<s:url value="/register" />" class="btn btn-default">Rejestracja</a>
                                    </div>


                                    <input class="css-checkbox" type="checkbox" name="_spring_security_remember_me"
                                           id="remember_me">
                                    <label for="remember_me" class="css-label">Zapamiętaj mnie</label>

                                </fieldset>
                            </form>


                        </div>

                    </div>
                    <div class="triangle"></div>
                </main>
                <div class="spliterContent">

                    <p>This is just to show ten concept. Login Page do not have this white area</p>
                </div>
            </div>
        </div>

    </tiles:putAttribute>

</tiles:insertDefinition>

