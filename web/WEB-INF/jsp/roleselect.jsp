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
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/jsStyle.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <title>Banka</title>
    </head>
    <body onload="downShow()" class="animate" >
        <div id="wrapper">
            <div id="dobrodosli">
                <h2><spring:message code="welcome"/></h2>
            </div>
            <p>
                <em>
                    <spring:message code="umultiuser"/>
                </em>
            </p>
            <a style="width: 110px;" class="btn btn-default" href="${pageContext.request.contextPath}/pocetna"> korisnik</a>
            <br>
            <br>
            <p>
                <em>
                    <spring:message code="amultiuser"/>
                </em>
            </p>
            <a style="width: 110px;" class="btn btn-default" href="${pageContext.request.contextPath}/admin">administrator</a>
        </div><!--end of wrapper-->
        <img id="logo" src="<c:url value="/resources/images/logo.png" />" alt="logo">
    </body>
</html>
