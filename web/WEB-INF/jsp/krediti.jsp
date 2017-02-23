<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/jsStyle.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/krediti.css" />" rel="stylesheet" type="text/css">
        <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/custom.js" />" type="text/javascript"></script>
        <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/popup.css" />" rel="stylesheet" type="text/css">
        <script src="<c:url value="/resources/js/admin.js" />" type="text/javascript"></script>
        <title>Banka</title>
    </head>
    <body>
        <%@include file="/resources/template/header.html" %>
        <%@include file="/resources/template/popup.html" %>
        <div id="wrapperUplatnice" onload="downShow()" class="animate-bottom"><!--wrapper uplatnice-->
            <div class="levisablon">
                <div class="hederTransfera">
                    <h3><spring:message code="creditCalc" /></h3>
                </div><!--krajhederuplatnice-->
                <div class="uplatnica">
                    <div class="form-inline">
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="loanType" /></label>
                            </div>
                            <div class="loanInput">
                                <select class="loanText" onclick="loanInfo()" id="izabraniKredit">
                                    <c:forEach items="${kreditiUPonudi}" var="k">
                                        <option>${k.vrstaKredita}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="nominalAnnualRate" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" readonly="" class="loanText" id="godisnjaStopa">
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="loanCurrency" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" readonly="" class="loanText" id="valutaKredita">
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="loanAmount" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" class="loanText" id="iznosKredita">
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="repaymentPeriod" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" class="loanText" id="periodOtplate">
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="share" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" class="loanText" id="ucesce">
                            </div>
                        </div>
                        <br>
                        <hr>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="monthly" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" readonly="" class="loanText" id="mesecniAnuitet">
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="repaymentAmount" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" readonly="" class="loanText" id="iznosOtplate">
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="interestAmount" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" readonly="" class="loanText" id="iznosKamate">
                            </div>
                        </div>
                        <br>
                        <div class="krediti">
                            <div class="loanLabel">
                                <label><spring:message code="interest" /></label>
                            </div>
                            <div class="loanInput">
                                <input type="text" readonly="" class="loanText" id="kamata">
                            </div>
                        </div>
                        <br>
                        <br>
                        <button class="button" onclick="izracunajKredit()"><spring:message code="calculate" /></button>
                    </div>
                </div><!--krajUplatnice-->
            </div><!--kraj levisablon-->
        </div><!--kraj wrapper uplatnice-->
    </body>
</html>
