<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
            <div id="dobrodosli">
                <p><spring:message code="welcome"/></p>
                <h2><spring:message code="pleaselogin"/></h2>
            </div>
            <%@include file="/resources/template/message.html" %>
            <c:url var="login_url"  value="/j_spring_security_check" />
            <form class="form-inline"
                  action="${login_url}"
                  method="POST"
                  name="login">
                <div class="form-group">
                    <label><spring:message code="username"/></label>
                    <br>
                    <input type="text" name="username" class="form-control" required="" />
                </div>
                <br>
                <div class="form-group">
                    <label><spring:message code="password"/></label>
                    <br>
                    <input type="password" name="password" class="form-control" required=""/>
                </div>
                <br>
                <br>
                <input type="submit" class="btn btn-default" value="<spring:message code="login"/>" />
                <br>
                <br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
            <p>
                <em>
                    <spring:message code="firsttime"/>
                </em>
            </p>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/aktivirajServis"><spring:message code="activationservis"/></a>
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
