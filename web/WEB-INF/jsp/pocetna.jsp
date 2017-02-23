<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/popup.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/jsStyle.css" />" rel="stylesheet" type="text/css">
        <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/custom.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/admin.js" />" type="text/javascript"></script>
        <title>Banka</title>
    </head>
    <body>
        <%@include file="/resources/template/header.html" %>
        <%@include file="/resources/template/popup.html" %>
        <div id="innerTable" onload="downShow()" class="animate-bottom">
            <table>
                <tr>
                    <th><spring:message code="owner"/></th>
                    <th><spring:message code="description"/></th>
                    <th><spring:message code="balance"/></th>
                    <th><spring:message code="limit"/></th>
                    <th><spring:message code="valid"/></th>
                </tr>
                <c:forEach items="${user.getKartice()}" var="k">
                    <tr>
                        <td>${user.getKorisnikPodaci().getIme()} ${user.getKorisnikPodaci().getPrezime()}<br>${k.tekuciRacun}</td>
                        <td>${k.opis}</td>
                        <td>${k.saldo}</td>
                        <td>${k.ogranicenje}</td>
                        <td>${k.rokVazenja}</td>
                    </tr>
                </c:forEach>
            </table>
        </div><!--krajInnerTable-->
    </body>
</html>