<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="<c:url value="/resources/js/admin.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/jsStyle.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
        <title>Banka</title>
    </head>
    <body onload="slowShow()" class="animate" >
        <div id="wrapper">
            <div id="dobrodosliAktivirajServis">
                <p><spring:message code="welcome"/></p>
                <h2><spring:message code="activationservis"/></h2>
            </div>
            <form class="form-inline" action="aktivirajServis" method="POST" name="">
                <div class="form-group">
                    <label><spring:message code="username"/></label>
                    <br>
                    <input type="text" name="aktivirajUsername" class="form-control" required=""/>
                </div>
                <br>
                <div class="form-group">
                    <label><spring:message code="password"/></label>
                    <br>
                    <input type="password" name="aktivirajPassword" class="form-control" id="activatePassword" required=""/>
                </div>
                <br>
                <div class="form-group">
                    <label><spring:message code="repeatpassword"/></label>
                    <br>
                    <input type="password" name="repeatPassword" class="form-control" onkeyup="checkPassword()" id="repeatActivatePassword" required=""/>
                </div>
                <br>
                <br>
                <input type="submit" class="btn btn-default" value="<spring:message code="activationservis"/>" id="serviceActivation" />
                <br>
                <br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
            <p>
                <em>
                    <spring:message code="alreadyhave"/>
                </em>
            </p>
            <a class="btn btn-default" href="login"><spring:message code="login"/></a>
        </div><!--end of wrapper-->
        <div id="lokalizacija">
            <h5>
                <spring:message code="chooselanguage"/>&nbsp;&nbsp;
                <a href="?lang=sr">SRP</a> | 
                <a href="?lang=en">ENG</a>
            </h5>
        </div><!--end of lokalizacija-->
        <img id="logo" src="<c:url value="/resources/images/logo.png" />" alt="logo">
    </body>
</html>
