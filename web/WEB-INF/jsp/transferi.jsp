<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/jsStyle.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/custom.js" />" type="text/javascript"></script>
        <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/popup.css" />" rel="stylesheet" type="text/css">
        <script src="<c:url value="/resources/js/admin.js" />" type="text/javascript"></script>
        <title>Banka</title>
    </head>
    <body>
        <%@include file="/resources/template/header.html" %>
        <%@include file="/resources/template/popup.html" %>
        <!-- end header -->
        <div id="wrapperTransfera" onload="downShow()" class="animate-bottom">
            <%@include file="/resources/template/message.html" %>
            <div class="levisablon">
                <div class="hederTransfera">
                    <h3><spring:message code="transferInit" /></h3>
                </div><!--krajhederuplatnice-->
                <div class="uplatnica">
                    <form:form onsubmit="return validateTForm()" name="transferForm" class="form-inline" method="POST" commandName="noviTransfer" action="inicirajTransfer">
                        <div class="form-group">
                            <form:label path="racunUplatioca"><spring:message code="debitAccount" /></form:label>
                                <br>
                            <form:select class="form-control" style="width: 360px;" path="racunUplatioca">
                                <c:forEach items="${user.getKartice()}" var="k">
                                    <form:option value="${k.tekuciRacun}" />
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="form-group" style="margin-left: 15px;">
                            <form:label path="racunPrimaoca"><spring:message code="creditAccount" /></form:label>
                                <br>
                            <form:input id="racunPrimaocaTransfer" path="racunPrimaoca" class="form-control" />
                        </div>
                        <br>
                        <div class="form-group">
                            <form:label path="iznosTransfera"><spring:message code="amount" /></form:label>
                                <br>
                            <form:input id="iznosTransfera" path="iznosTransfera" class="form-control"/>
                        </div>
                        <br>
                        <div class="form-group">
                            <label><spring:message code="dat" />:</label>
                            <br>
                            <input type="text" readonly="" class="form-control" value="${datum}" name="date">
                        </div>
                        <br>
                        <br>
                        <input type="submit" class="button" value="<spring:message code="startTransfer" />"/>
                    </form:form>
                </div>
            </div>
            <div class="desniSablon">
                <div class="hederTransfera">
                    <h3><spring:message code="transferArchive" /></h3>
                </div>
                <div class="listaUplata">
                    <table>
                        <tr>
                            <th><spring:message code="dat" /></th>
                            <th><spring:message code="amountStatus" /></th>
                            <th><spring:message code="details" /></th>
                        </tr>
                        <c:forEach items="${user.getKartice()}" var="k">
                            <c:forEach items="${k.getTransferi()}" var="t">
                                <tr>
                                    <td>${t.getDatum()}</td>
                                    <td>${t.getIznos()}</td>
                                    <td><a href="tdetalji?reb=${t.getId()}"><i class="fa fa-info-circle" aria-hidden="true"></i></a></td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </table>
                </div><!--krajUplatnice-->
            </div><!--kraj desniSablon-->
        </div><!--kraj wrapper uplatnice-->
    </body>
</html>