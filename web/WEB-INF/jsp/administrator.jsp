<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/admin.js" />" type="text/javascript"></script>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/jsStyle.css" />" rel="stylesheet">
        <title>Banka</title>
    </head>
    <body onload="downShow()" class="animate-bottom">
        <div class="korisnikreg">
            <div id="adminTop">
                <h2><spring:message code="admin"/></h2>
            </div>
            <div id="welcome">
                <h4><spring:message code="welcomeAdd"/> ${adname}</h4>
            </div>
            <div id="lastlogin">
                <h4><spring:message code="lastLogin"/></h4>
            </div>
            <div id="exit">
                <c:url value="/j_spring_security_logout" var="logoutUrl" />
                <form action="${logoutUrl}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="submit" value="<spring:message code="logout"/>" id="adminLogout" />
                </form>
            </div>
            <hr id="plavalinija">
            <label><spring:message code="chooseAction"/></label>
            <select name="opcija" id="izaberiAkciju" onchange="showonlyone(this.value);">
                <option value="unosKorisnika"><spring:message code="enteranewuser"/></option>
                <option value="izmeniKorisnika"><spring:message code="editprofile"/></option>
                <option value="brisanjeKorisnika"><spring:message code="removeUser"/></option>
                <option value="unosKartice"><spring:message code="enternewcard"/></option>
                <option value="izmeniKarticu"><spring:message code="editcard"/></option>
                <option value="brisanjeKartice"><spring:message code="removecard"/></option>
                <option value="unosKredita"><spring:message code="enternewloan"/></option>
                <option value="brisanjeKredita"><spring:message code="removeloand"/></option>
            </select>
            <div id="unosKorisnika" class="izaberiAkciju" style="display: block;">
                <form:form class="form-inline" action="register" method="POST" 
                           commandName="noviKorisnik" onsubmit="return validateUserEnter()" name="enterNewUser">
                    <br>
                    <br>
                    <div class="form-group">
                        <form:label path="ime"><spring:message code="newName"/></form:label>
                            <br>
                        <form:input class="form-control" path="ime" id="vikunos"/>
                    </div>
                    <div class="form-group">
                        <form:label path="prezime"><spring:message code="newLastname"/></form:label>
                            <br>
                        <form:input class="form-control" path="prezime" />
                    </div>
                    <div class="form-group">
                        <form:label path="korisnickoIme"><spring:message code="newUsername"/></form:label>
                            <br>
                        <form:input onkeyup="checkUser()" class="form-control" path="korisnickoIme" id="proveraKorisnika"/>
                    </div>
                    <div class="form-group">
                        <form:label path="email"><spring:message code="newEmail"/></form:label>
                            <br>
                        <form:input class="form-control" path="email" />
                    </div>
                    <div class="form-group">
                        <form:label path="jmbg"><spring:message code="newjmbg"/></form:label>
                            <br>
                        <form:input class="form-control" path="jmbg" />
                    </div>
                    <br />
                    <div class="form-group">
                        <form:label path="brTelefona"><spring:message code="newNumber"/></form:label>
                            <br>
                        <form:input class="form-control" path="brTelefona" />
                    </div>
                    <div class="form-group">
                        <form:label path="adresa"><spring:message code="newAdress"/></form:label>
                            <br>
                        <form:input class="form-control" path="adresa"/>
                    </div>
                    <div class="form-group">
                        <form:label path="mesto"><spring:message code="newCity"/></form:label>
                            <br>
                        <form:input class="form-control" path="mesto" />
                    </div>
                    <div class="form-group">
                        <form:label path="operater"><spring:message code="newOperater"/></form:label>
                            <br>
                        <form:input class="form-control" path="operater" />
                    </div>
                    <div class="form-group">
                        <form:label path="rola"><spring:message code="newRola"/></form:label>
                            <br>
                        <form:select class="form-control" path="rola" style="width:196px;" >
                            <form:option value="ROLE_USER"/>
                            <form:option value="ROLE_ADMIN"/>
                            <form:option value="ROLE_USER,ROLE_ADMIN"/>
                        </form:select>
                    </div>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-default" value="<spring:message code="enteranewuser"/>" id="unesiKorisnika"/>
                </form:form>
            </div>
            <!--**************************************KRAJ UNOS NOVOG KORISNIKA***********************************************************
            *******************************************************************************************************************************-->
            <div id="unosKartice" class="izaberiAkciju" style="display: none;">
                <form:form class="form-inline" action="cardEntry" method="POST" 
                           commandName="novaKartica" onsubmit="return validateCardEnter()" name="enterNewCard">
                    <br>
                    <br>
                    <div class="form-group">
                        <form:label path="korisnik"><spring:message code="newUsername"/></form:label>
                            <br>
                        <form:select class="form-control" path="korisnik" style="width:196px;">
                            <c:forEach items="${sviKorisnici}" var="k">
                                <form:option value="${k.korisnickoIme}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <br />
                    <div class="form-group">
                        <form:label path="nazivKartice"><spring:message code="cardName"/></form:label>
                            <br>
                        <form:input class="form-control" path="nazivKartice" />
                    </div>
                    <div class="form-group">
                        <form:label path="ogranicenje"><spring:message code="cardLimit"/></form:label>
                            <br>
                        <form:input class="form-control" path="ogranicenje"/>
                    </div>
                    <div class="form-group">
                        <form:label path="opis"><spring:message code="carddescription"/></form:label>
                            <br>
                        <form:input class="form-control" path="opis" />
                    </div>
                    <br>
                    <div class="form-group">
                        <form:label path="rokVazenja"><spring:message code="cardexpired"/></form:label>
                            <br>
                        <form:input class="form-control" path="rokVazenja" />
                    </div>
                    <div class="form-group">
                        <form:label path="saldo"><spring:message code="amount"/>:</form:label>
                            <br>
                        <form:input class="form-control" path="saldo" />
                    </div>
                    <div class="form-group">
                        <form:label path="brRacuna"><spring:message code="acountNumber"/></form:label>
                            <br>
                        <form:input class="form-control" path="brRacuna" />
                    </div>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-default" value="<spring:message code="enternewcard"/>"/>
                </form:form>
            </div>
            <!--**************************************KRAJ UNOSA NOVE KARTICE***********************************************************
            *****************************************************************************************************************************-->
            <div id="brisanjeKorisnika" class="izaberiAkciju" style="display: none;">
                <form:form class="form-inline" action="brisanjeKorisnika" method="POST" commandName="brisanjeKorisnika">
                    <br>
                    <br>
                    <div class="form-group">
                        <form:label path="ime"><spring:message code="newUsername"/></form:label>
                            <br>
                        <form:select class="form-control" path="ime" style="width:196px;">
                            <c:forEach items="${sviKorisnici}" var="k">
                                <form:option value="${k.korisnickoIme}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-default" value="<spring:message code="removeUser"/>"/>
                </form:form>
            </div>
            <!--**************************************KRAJ BRISANJA KORISNIKA***********************************************************
            ***************************************************************************************************************************-->            
            <div id="izmeniKorisnika" class="izaberiAkciju" style="display: none;">
                <form:form class="form-inline" action="izmeniKorisnika" method="POST" 
                           commandName="izmeniKorisnika" onsubmit="return validateEditUser()" name="editUserValid">
                    <br>
                    <br>
                    <div class="form-group">
                        <form:label path="korisnickoIme"><spring:message code="newUsername"/></form:label>
                            <br>
                        <form:select onclick="getData()" id="user" class="form-control" style="width:196px;" path="korisnickoIme">
                            <c:forEach items="${sviKorisnici}" var="k">
                                <form:option value="${k.korisnickoIme}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <form:label path="ime"><spring:message code="newName"/></form:label>
                            <br>
                        <form:input class="form-control" id="ifirstName" path="ime" />
                    </div>
                    <div class="form-group">
                        <form:label path="prezime"><spring:message code="newLastname"/></form:label>
                            <br>
                        <form:input class="form-control" id="ilastName" path="prezime" />
                    </div>
                    <div class="form-group">
                        <form:label path="email"><spring:message code="newEmail"/></form:label>
                            <br>
                        <form:input class="form-control" id="iemail" path="email" />
                    </div>
                    <div class="form-group">
                        <form:label path="jmbg"><spring:message code="newjmbg"/></form:label>
                            <br>
                        <form:input class="form-control" id="ijmbg" readonly="true"  path="jmbg" />
                    </div>
                    <br />
                    <div class="form-group">
                        <form:label path="brTelefona"><spring:message code="newNumber"/></form:label>
                            <br>
                        <form:input class="form-control" id="iphoneNo" path="brTelefona" />
                    </div>
                    <div class="form-group">
                        <form:label path="adresa"><spring:message code="newAdress"/></form:label>
                            <br>
                        <form:input class="form-control" id="iadresa" path="adresa" />
                    </div>
                    <div class="form-group">
                        <form:label path="mesto"><spring:message code="newCity"/></form:label>
                            <br>
                        <form:input class="form-control" id="imesto" path="mesto" />
                    </div>
                    <div class="form-group">
                        <form:label path="operater"><spring:message code="newOperater"/></form:label>
                            <br>
                        <form:input class="form-control" id="ioperater" path="operater" />
                    </div>
                    <div class="form-group">
                        <form:label path="rola"><spring:message code="newRola"/></form:label>
                            <br>
                        <form:select class="form-control" path="rola" style="width:196px;" > <!--id="irola">-->
                            <form:option value="ROLE_USER"/>
                            <form:option value="ROLE_ADMIN"/>
                            <form:option value="ROLE_USER,ROLE_ADMIN"/>
                        </form:select>
                    </div>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-default" value="<spring:message code="editprofile"/>" />
                </form:form>
            </div>
            <!--**************************************KRAJ IZMENE KORISNIKA***********************************************************
            ***************************************************************************************************************************-->
            <div id="izmeniKarticu" class="izaberiAkciju" style="display: none;">
                <form:form class="form-inline" action="cardChange" method="POST" 
                           commandName="izmeniKarticu" onsubmit="return validateCardEdit()" name="editCardValid">
                    <br>
                    <br>
                    <div class="form-group">
                        <form:label path="korisnik"><spring:message code="newUsername"/></form:label>
                            <br>
                        <form:select onclick="clearAll()" onchange="cardData()" id="selected" class="form-control" style="width:196px;" path="korisnik">
                            <%--<form:option disabled="" value="IZABERI KORISNIKA" />--%>
                            <c:forEach items="${sviKorisnici}" var="k">
                                <form:option value="${k.korisnickoIme}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <br />
                    <div class="form-group">
                        <form:label path="brRacuna"><spring:message code="acountNumber"/></form:label>
                            <br>
                        <form:select onclick="changeCard()" id="sviRacuni" class="form-control" style="width:196px;" path="brRacuna"></form:select>
                        </div>
                        <div class="form-group">
                        <form:label path="saldo"><spring:message code="amount"/>:</form:label>
                            <br>
                        <form:input class="form-control" id="izmeniStanje" path="saldo"/>
                    </div>
                    <div class="form-group">
                        <form:label path="opis"><spring:message code="carddescription"/></form:label>
                            <br>
                        <form:input readonly="true" class="form-control" id="opisRacuna" path="opis"/>
                    </div>
                    <br>
                    <div class="form-group">
                        <form:label path="ogranicenje"><spring:message code="cardLimit"/></form:label>
                            <br>
                        <form:input class="form-control" id="izmeniOgranicenje" path="ogranicenje"/>
                    </div>
                    <div class="form-group">
                        <form:label path="rokVazenja"><spring:message code="cardexpired"/></form:label>
                            <br>
                        <form:input class="form-control" id="rokovi" path="rokVazenja"/>
                    </div>
                    <div class="form-group">
                        <form:label path="nazivKartice"><spring:message code="cardName"/></form:label>
                            <br>
                        <form:input readonly="true" class="form-control" id="nazivK" path="nazivKartice"/>
                    </div>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-default" value="<spring:message code="editcard"/>" />
                </form:form>
            </div>
            <!--**************************************KRAJ IZMENE KARTICE***********************************************************
            ***************************************************************************************************************************-->
            <div id="brisanjeKartice" class="izaberiAkciju" style="display: none;">
                <form:form class="form-inline" 
                           action="obrisiKarticu" 
                           method="POST" 
                           commandName="obrisiKarticu">
                    <br>
                    <br>
                    <div class="form-group">
                        <div class="form-group">
                            <form:label path="korisnik"><spring:message code="newUsername"/></form:label>
                                <br>
                            <form:select onclick="" onchange="deleteCard()" id="bKorisnik" class="form-control" style="width:196px;" path="korisnik">
                                <form:option disabled="" value="IZABERI KORISNIKA" />
                                <c:forEach items="${sviKorisnici}" var="k">
                                    <form:option value="${k.korisnickoIme}" />
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="kartica"><spring:message code="acountNumber"/></form:label>
                            <br>
                        <form:select id="bKartica" class="form-control" style="width:196px;" path="kartica"></form:select>
                        </div>
                        <br>
                        <br>
                        <input type="submit" class="btn btn-default" value="<spring:message code="removecard"/>"/>
                </form:form>
            </div>
            <!--**************************************KRAJ BRISANJA KARTICE***********************************************************
            ***************************************************************************************************************************-->
            <div id="unosKredita" class="izaberiAkciju" style="display: none;">
                <form:form class="form-inline" action="kreditiUnos" method="POST" 
                           commandName="noviKredit" onsubmit="return validateLoanEnter()" name="enterLoanValid">
                    <br>
                    <br>
                    <div class="form-group">
                        <form:label path="vrstaKredita"><spring:message code="credittype"/></form:label>
                            <br>
                        <form:input class="form-control" path="vrstaKredita"/>
                    </div>
                    <div class="form-group">
                        <form:label path="nominalnaGodisnjaStopa"><spring:message code="nomRate"/></form:label>
                            <br>
                        <form:input class="form-control" path="nominalnaGodisnjaStopa" />
                    </div>
                    <div class="form-group">
                        <form:label path="valuta"><spring:message code="creditCurrency"/></form:label>
                            <br>
                        <form:input class="form-control" path="valuta"/>
                    </div>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-default" value="<spring:message code="enternewloan"/>"/>
                </form:form>
            </div>
            <!--**************************************KRAJ UNOS KREDITA***********************************************************
            ***************************************************************************************************************************-->
            <div id="brisanjeKredita" class="izaberiAkciju" style="display: none;">
                <form class="form-inline"  action="obrisiKredit"  method="POST" >
                    <br>
                    <br>
                    <div class="form-group">
                        <div class="form-group">
                            <label><spring:message code="removeloan"/></label>
                            <br>
                            <select id="bKorisnik" class="form-control" style="width:196px;" name="krb" >
                                <c:forEach items="${sviKrediti}" var="k">
                                    <option>${k.vrstaKredita}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <br>
                    <br>
                    <input type="submit" class="btn btn-default" value="<spring:message code="removeloand"/>"/>
                </form>
            </div>
            <!--**************************************KRAJ BRISANJAKREDITA DIV***********************************************************
            ***************************************************************************************************************************-->
        </div>
        <!--**************************************KRAJ KORISNIKREG DIV***********************************************************
        ***************************************************************************************************************************-->
    </body>
</html>
