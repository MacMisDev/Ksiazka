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
                            <div class="avatarBorder"></div>
                            <img class="HomeUserAvatar" src="resources/images/avatarPlaceholder.jpg">
                            <div id="hello"><h2>Cześć <c:out value="${user.name}" />!</h2></div>

                            <form method="post" name="home">
                                <fieldset>

                                    <div class="form-group">
                                        <p>Imię i nazwisko</p>
                                        <div class="homeFormBG">
                                            <input type="text" name="name" id="name" value='<c:out value="${user.name}" />' disabled="true" />
                                            <div class="homeEditBtn"></div>
                                            <div class="editBtns" style="display: none">
                                                <div class="homeSaveBtn"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <p>Nazwa użytkownika</p>
                                            <div class="homeFormBG">
                                            <input type="text" name="username" id="username" value='<c:out value="${user.username}" />' disabled="true" />
                                            <div class="homeEditBtn"></div>
                                            <div class="editBtns" style="display: none">
                                                <div class="homeSaveBtn"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <p>Adres email</p>
                                        <div class="homeFormBG">
                                            <input type="text" name="email" id="email" value='<c:out value="${user.email}" />' disabled="true" />
                                            <div class="homeEditBtn"></div>
                                            <div class="editBtns" style="display: none">
                                                <div class="homeSaveBtn"></div>
                                            </div>
                                        </div>
                                    </div>

                                </fieldset>
                            </form>

                        </div>

                        <div class="formExtension">
                            <ul class="extensionBtns">
                                <li>
                                    <a href=#> <input type="image" src="resources/images/icon-location.png"class="extBtn" /></a>
                                </li>
                                <li>
                                    <a href=#> <input type="image" src="resources/images/icon-collection.png"class="extBtn" /></a>
                                </li>
                                <li>
                                    <a href=#> <input type="image" src="resources/images/icon-reputation.png"class="extBtn" /></a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/static/j_spring_security_logout"> <input type="image" src="resources/images/icon-logout.png" name="logout" value="logout" class="extBtn" /></a>
                                </li>
                            </ul>
                        </div>

                    </div>
                </main>
            </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        $('.homeEditBtn').click(function () {
            $('.homeEditBtn').toggleClass('editActive');
            $('.editBtns').toggle('slow', function () {
                //Buttons shown, yay!
            });
        });
    });
</script>

