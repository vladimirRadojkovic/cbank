<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/jsStyle.css" />" rel="stylesheet" type="text/css">
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
        <!-- end header -->
        <div id="wrapperUplatnice" onload="downShow()" class="animate-bottom"><!--wrapper uplatnice-->
            <%@include file="/resources/template/message.html" %>
            <div class="levisablon">
                <div class="hederTransfera">
                    <h3><spring:message code="transferOrder"/></h3>
                </div><!--krajhederuplatnice-->
                <div class="uplatnica">
                    <form:form name="uplata" class="form-inline" action="posaljiNalog" method="POST" commandName="nalogZaUplatu" onsubmit="return validateForm()">
                        <div class="form-group">
                            <form:label path="sifraPlacanja"><spring:message code="paymentCode"/></form:label>
                                <br>
                            <form:select id="sifraPlacanja" class="form-control" path="sifraPlacanja" >
                                <form:option value="289"/>
                                <form:option value="269"/>
                                <form:option value="279"/>
                                <form:option value="250"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <form:label class="desnaLab" path="iznos"><spring:message code="amount"/></form:label>
                                <br>
                            <form:input id="iznos" class="form-control" path="iznos" />
                        </div>
                        <br>
                        <div class="form-group">
                            <label><spring:message code="debtorName"/></label>
                            <br>
                            <input type="text" readonly="true" id="nazivPlatioca" class="form-control" name="nazivPlatioca" value="${user.getKorisnikPodaci().korisnickoIme} ${user.getKorisnikPodaci().getPrezime()}">
                        </div>
                        <div class="form-group">
                            <form:label class="desnaLab" path="nazivPrimaoca"><spring:message code="recipientName"/></form:label>
                                <br>
                            <form:input id="nazivPrimaoca" class="form-control" path="nazivPrimaoca"/>
                        </div>
                        <br>
                        <div class="form-group">
                            <label><spring:message code="debtorAdress"/></label>
                            <br>
                            <input type="text" readonly="true" id="adresaPlatioca" class="form-control" name="adresaPlatioca" value="${user.getKorisnikPodaci().getAdresa()}">
                        </div>
                        <div class="form-group">
                            <form:label class="desnaLab" path="adresaPrimaoca"><spring:message code="recipientAdress"/></form:label>
                                <br>
                            <form:input id="adresaPrimaoca" class="form-control" path="adresaPrimaoca" />
                        </div>
                        <br>
                        <div class="form-group">
                            <label><spring:message code="debtorCity"/></label>
                            <br>
                            <input type="text" readonly="true" id="mestoPlatioca" class="form-control" name="mestoPlatioca" value="${user.getKorisnikPodaci().getMesto()}">
                        </div>
                        <div class="form-group">
                            <form:label class="desnaLab" path="mestoPrimaoca"><spring:message code="recipientCity"/></form:label>
                                <br>
                            <form:input id="mestoPrimaoca" class="form-control" path="mestoPrimaoca" />
                        </div>
                        <br>
                        <div class="form-group">
                            <form:label path="racunUplatioca"><spring:message code="debtorAccounts"/></form:label>
                                <br>
                            <form:select id="racunPlatioca" class="form-control" path="racunUplatioca">
                                <c:forEach items="${user.getKartice()}" var="k">
                                    <form:option value="${k.tekuciRacun}" />
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <form:label class="desnaLab" path="racunPrimaoca"><spring:message code="creditorAccount"/></form:label>
                                <br>
                            <form:input id="racunPrimaoca" class="form-control" path="racunPrimaoca" />
                        </div>
                        <br>
                        <div class="form-group">
                            <form:label path="model"><spring:message code="model"/></form:label>
                                <br>
                            <form:input id="model" class="form-control" path="model" />
                        </div>
                        <div class="form-group">
                            <form:label class="desnaLabDeset" path="pozivNaBroj"><spring:message code="referenceNumber"/></form:label>
                                <br>
                            <form:input id="pozivnabroj" class="form-control" path="pozivNaBroj" />
                        </div>
                        <div class="form-group">
                            <label class="desnaLabDeset"><spring:message code="date"/></label>
                            <br>
                            <input type="text" readonly="" id="datumIzvrsenja" class="form-control" name="datumIzvrsenja" value="${datum}">
                        </div>
                        <div class="form-group">
                            <form:checkbox id="hitno" path="hitno" value="1"/>&nbsp;
                            <label><spring:message code="urgent"/></label>
                        </div>
                        <br>
                        <div class="form-group">
                            <form:label path="svrhaPlacanja"><spring:message code="purpose"/></form:label>
                                <br>
                            <form:input id="svrhaplacanja" class="form-control" path="svrhaPlacanja" />
                        </div>
                        <br>
                        <br>
                        <input id="izvrsiUplatu" type="submit" class="button" value="<spring:message code="sendOrder"/>"/>
                    </form:form>
                </div><!--krajUplatnice-->
            </div><!--kraj levisablon-->
            <div class="desniSablon">
                <div class="hederTransfera">
                    <h3><spring:message code="ordersBook"/></h3>
                </div><!--krajhederuplatnice-->
                <div class="listaUplata">
                    <table>
                        <tr>
                            <th><spring:message code="dat"/></th>
                            <th><spring:message code="amountStatus"/></th>
                            <th><spring:message code="details"/></th>
                        </tr>
                        <c:forEach items="${user.getKartice()}" var="k">
                            <c:forEach items="${k.getPlacanja()}" var="p">
                                <tr>
                                    <td>${p.getDatum()}</td>
                                    <td>${p.getIznos()}</td>
                                    <td><a href="detalji?reb=${p.getId()}"><i class="fa fa-info-circle" aria-hidden="true"></i></a></td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </table>
                </div><!--krajUplatnice-->
            </div><!--kraj desniSablon-->
        </div><!--kraj wrapper uplatnice-->
    </body>
</html>