<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
        <title>Banka</title>
    </head>
    <body>
        <div id="detalji">
            <div class="levisablon">
                <div class="hederTransfera">
                    <h3>
                        Detalji o uplati
                        <span id="right">
                            <a href="uplatnica"><i style="color: white;" class="fa fa-times-circle" aria-hidden="true"></i></a>
                        </span>
                    </h3>
                </div><!--krajhederuplatnice-->
                <div class="uplatnica">
                    <form class="form-inline" action="placanja" method="post">
                        <div class="form-group">
                            <label>SIFRA PLACANJA</label>
                            <br>
                            <input readonly="" id="sifraPlacanja" class="form-control" value="${placanje.getSifraPlacanja()}">
                        </div>
                        <div class="form-group">
                            <label class="desnaLab">IZNOS</label>
                            <br>
                            <input readonly="" type="text" id="iznos" class="form-control" value="${placanje.getIznos()}">
                        </div>
                        <br>
                        <div class="form-group">
                            <label>NAZIV PLATIOCA</label>
                            <br>
                            <input readonly="" type="text" id="nazivPlatioca" class="form-control" value="${placanje.getNazivUplatioca()}">
                        </div>
                        <div class="form-group">
                            <label class="desnaLab">NAZIV PRIMAOCA</label>
                            <br>
                            <input readonly="" type="text" id="nazivPrimaoca" class="form-control" value="${placanje.getNazivPrimaoca()}">
                        </div>
                        <br>
                        <div class="form-group">
                            <label>ADRESA PLATIOCA</label>
                            <br>
                            <input readonly="" type="text" id="adresaPlatioca" class="form-control" value="${placanje.getAdresaUplatioca()}">
                        </div>
                        <div class="form-group">
                            <label class="desnaLab">ADRESA PRIMAOCA</label>
                            <br>
                            <input readonly="" type="text" id="adresaPrimaoca" class="form-control" value="${placanje.getAdresaPrimaoca()}">
                        </div>
                        <br>
                        <div class="form-group">
                            <label>MESTO PLATIOCA</label>
                            <br>
                            <input readonly="" type="text" id="mestoPlatioca" class="form-control" value="${placanje.getMestoUplatioca()}">
                        </div>
                        <div class="form-group">
                            <label class="desnaLab">MESTO PRIMAOCA</label>
                            <br>
                            <input readonly="" type="text" id="mestoPrimaoca" class="form-control" value="${placanje.getMestoPrimaoca()}">
                        </div>
                        <br>
                        <div class="form-group">
                            <label>RACUN PLATIOCA</label>
                            <br>
                            <input readonly="" id="racunPlatioca" class="form-control" value="${placanje.getIdKartice().getNaziv()} ${placanje.getRacunUplatioca()}">
                        </div>
                        <div class="form-group">
                            <label class="desnaLab">RACUN PRIMAOCA</label>
                            <br>
                            <input type="text" id="racunPrimaoca" class="form-control" value="${placanje.getRacunPrimaoca()}">
                        </div>
                        <br>
                        <div class="form-group">
                            <label>MODEL</label>
                            <br>
                            <input readonly="" type="text" id="model" class="form-control" value="${placanje.getModel()}">
                        </div>
                        <div class="form-group">
                            <label class="desnaLabDeset">POZIV NA BROJ</label>
                            <br>
                            <input readonly="" type="text" id="pozivnabroj" class="form-control" value="${placanje.getPozivNaBroj()}">
                        </div>
                        <div class="form-group">
                            <label class="desnaLabDeset">DATUM IZVRSENJA</label>
                            <br>
                            <input readonly="" type="text" id="datumIzvrsenja" class="form-control" value="${placanje.getDatum()}">
                        </div>
                        <br>
                        <div class="form-group">
                            <label>SVRHA PLACANJA</label>
                            <br>
                            <input readonly="" type="text" id="svrhaplacanja" class="form-control" value="${placanje.getSvrhaPlacanja()}">
                        </div>
                    </form>
                </div><!--krajUplatnice-->
            </div><!--kraj levisablon-->
        </div><!--kraj detalji-->
    </body>
</html>
