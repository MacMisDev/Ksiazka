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

                        <div class="formTemplate">
                            <h2>Cześć <c:out value="${user.name}" />!</h2>
                        </div>

                        <a href="${pageContext.request.contextPath}/static/j_spring_security_logout"> <input type="submit" name="logout" value="logout" class="btn btn-primary" /></a>

                    </div>
                </main>
            </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>

