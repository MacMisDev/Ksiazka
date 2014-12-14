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
                                            <div class="homeEditBtn" id="editName"></div>
                                            <div class="editBtns" id="saveName" style="display: none">
                                                <div class="homeSaveBtn"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <p>Nazwa użytkownika</p>
                                            <div class="homeFormBG">
                                            <input type="text" name="username" id="username" value='<c:out value="${user.username}" />' disabled="true" />
                                            <div class="homeEditBtn" id="editUsername"></div>
                                            <div class="editBtns" id="saveUsername" style="display: none">
                                                <div class="homeSaveBtn"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <p>Adres email</p>
                                        <div class="homeFormBG">
                                            <input type="text" name="email" id="email" value='<c:out value="${user.email}" />' disabled="true" />
                                            <div class="homeEditBtn" id="editMail"></div>
                                            <div class="editBtns" id="saveMail" style="display: none">
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
                                    <a href=#> <div class="extBtn" id="extLoc" ></div></a>
                                </li>
                                <li>
                                    <a href=#> <div class="extBtn" id="extColl" ></div></a>
                                </li>
                                <li>
                                    <a href=#> <div class="extBtn" id="extRep"></div></a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/static/j_spring_security_logout"> <div name="logout" value="logout" class="extBtn" id="extLogout"></div></a>
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
        $('#editName').click(function () {
            $('#editName').toggleClass('editActive');
            $("#name").prop('disabled', ! $("#name").prop('disabled') );
            $('#saveName').toggle('slow', function () {
                //Buttons shown, yay!
            });
        });

        $('#editUsername').click(function () {
            $('#editUsername').toggleClass('editActive');
            $("#username").prop('disabled', ! $("#username").prop('disabled') );
            $('#saveUsername').toggle('slow', function () { });
        });

        $('#editMail').click(function () {
            $('#editMail').toggleClass('editActive');
            $("#email").prop('disabled', ! $("#email").prop('disabled') );
            $('#saveMail').toggle('slow', function () { });
        });
    });
</script>

