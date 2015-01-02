<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                        <a href="<s:url value="/" />" class="menuButtonHome">Home</a>
                    </li>

                </ul>
            </nav>

            <div id="pageContentWrapper">
                <main class="contentManagerCenter">
                    <div id="formWrapper">

                        <div class="formTemplate registerGroup ">

                            <div id="loginBookLockedImage"></div>
                            <div id="ribbon"><p>Zarejestruj się!</p></div>
                            <sf:form method="POST" id="registerForm" modelAttribute="user" action="register"
                                     data-parsley-validate="true"
                                    >
                                <fieldset>
                                    <div class="form-group">
                                        <p>Adres email</p>
                                        <sf:input type="email" path="email" name="email" id="email"
                                                  data-parsley-trigger="change"
                                                  data-parsley-required-message="To pole jest wymagane"
                                                  data-parsley-type-message="Wprowadź prawdłowy adres email"
                                                  required="true"/>
                                        <sf:errors path="email" class="formErrMsg"/>
                                    </div>
                                    <div class="form-group">
                                        <p>Imię</p>
                                        <sf:input path="name" type="text" name="name"
                                                  id="name"
                                                  data-parsley-trigger="change"
                                                  data-parsley-minlength="3"
                                                  data-parsley-minlength-message="Minimalnie 3 znaków"
                                                  required="true"/>
                                        <sf:errors path="name" class="formErrMsg"/>
                                    </div>
                                    <div class="form-group">
                                        <p>Nazwa użytkownika</p>
                                        <sf:input path="username" type="text" name="username" id="username"
                                                  data-parsley-trigger="change"
                                                  data-parsley-minlength="3"
                                                  data-parsley-minlength-message="Minimalnie 3 znaków"
                                                  required="true"/>
                                        <sf:errors path="username" class="formErrMsg"/>
                                    </div>
                                    <div class="form-group">
                                        <p>Hasło</p>
                                        <sf:input path="password" type="password" required="true" name="password"
                                                  id="password1"
                                                  data-parsley-trigger="keypress"
                                                  data-parsley-validation-threshold="0"
                                                  data-parsley-minlength-message="Minimalnie 6 znaków"
                                                  data-parsley-minlength="6"/>

                                        <p>Powtórz Hasło</p>
                                        <input data-parsley-equalto="#password1" type="password"
                                               required="true"
                                               data-parsley-trigger="keypress"
                                               data-parsley-validation-threshold="0"
                                               name="password2" id="password2"/>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" name="submit" class="btn btn-default">Zarejestruj</button>
                                        <a href="<s:url value="/login" />" class="btn btn-default">Logowanie</a>
                                    </div>

                                </fieldset>
                            </sf:form>


                        </div>

                    </div>
                </main>
            </div>
        </div>

    </tiles:putAttribute>

</tiles:insertDefinition>

