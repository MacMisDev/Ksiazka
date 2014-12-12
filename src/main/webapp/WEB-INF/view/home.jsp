<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="content">
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
                    <div id="formWrapper">

                        <div class="formTemplate homeGroup">
                            <div id="HomeUserAvatar"></div>
                            <div id="hello"><h2>Cześć <c:out value="${user.name}" />!</h2></div>

                            <a href="${pageContext.request.contextPath}/static/j_spring_security_logout"> <input type="submit" name="logout" value="logout" class="btn btn-primary" /></a>

                            <form method="post" name="home">
                                <fieldset>

                                    <div class="form-group">
                                        <p>Imię i nazwisko</p>
                                        <input type="text" name="name" id="name" value='<c:out value="${user.name}" />' disabled="true" />
                                    </div>
                                    <div class="form-group">
                                        <p>Nazwa użytkownika</p>
                                        <input type="text" name="username" id="username" value='<c:out value="${user.username}" />' disabled="true" />
                                    </div>
                                    <div class="form-group">
                                        <p>Adres email</p>
                                        <input type="text" name="email" id="email" value='<c:out value="${user.email}" />' disabled="true" />
                                    </div>

                                </fieldset>
                            </form>

                        </div>

                        <div class="formExtension">

                        </div>

                    </div>
                </main>
            </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>

