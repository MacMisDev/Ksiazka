<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                        <div id="menuBooks" class="menuButtonBooks">Książki</div>
                    </li>

                </ul>
            </nav>


            <div id="pageContentWrapper">
                <main class="contentManagerCenter">
                    <div id="formWrapper">

                        <div class="formTemplate homeGroup">
                            <div class="avatarBorder"></div>
                            <img class="HomeUserAvatar" src="/ksiazka/resources/images/avatarPlaceholder.jpg">

                            <div id="hello"><h2>Cześć <c:out value="${user.name}"/>!</h2></div>

                            <form method="post" name="home">
                                <fieldset>

                                    <div class="form-group">
                                        <p>Imię</p>

                                        <div class="homeFormBG">
                                            <input type="text" name="name" id="name" value="${user.name}"
                                                   disabled="true"/>

                                            <div class="homeEditBtn" id="editName"></div>
                                            <div class="editBtns" id="saveName" style="display: none">
                                                <div class="homeSaveBtn"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <p>Nazwa użytkownika</p>

                                        <div class="homeFormBG">
                                            <input type="text" name="username" id="username" value="${user.username}"
                                                   disabled="true"/>

                                            <div class="homeEditBtn" id="editUsername"></div>
                                            <div class="editBtns" id="saveUsername" style="display: none">
                                                <div class="homeSaveBtn"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <p>Adres email</p>

                                        <div class="homeFormBG">
                                            <input type="text" name="email" id="email" value="${user.email}"
                                                   disabled="true"/>

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
                                    <a href=#>
                                        <div class="extBtn" id="extLoc"></div>
                                    </a>
                                </li>
                                <li>
                                    <a href=#>
                                        <div class="extBtn" id="extColl"></div>
                                    </a>
                                </li>
                                <li>
                                    <a href=#>
                                        <div class="extBtn" id="extRep"></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/static/j_spring_security_logout">
                                        <div name="logout" value="logout" class="extBtn" id="extLogout"></div>
                                    </a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <div class="triangle"></div>
                </main>
                <div class="spliterContent">

                        <%-- <table data-url="http://wenzhixin.net.cn/p/bootstrap-table/docs/data1.json" data-height="299" data-sort-name="name" data-sort-order="desc">
                             <thead>
                             <tr>
                                 <th data-field="id" data-align="right" data-sortable="true">Item ID</th>
                                 <th data-field="name" data-align="center" data-sortable="true">Item Name</th>
                                 <th data-field="price" data-sortable="true">Item Price</th>
                             </tr>
                             </thead>
                         </table>--%>

                </div>
            </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>
