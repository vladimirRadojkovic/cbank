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
                        Detalji o transferu
                        <span id="right">
                            <a href="transferi"><i style="color: white;" class="fa fa-times-circle" aria-hidden="true"></i></a>
                        </span>
                    </h3>
                </div><!--krajhederuplatnice-->
                <div class="uplatnica">
                    <div class="form-inline" >
                        <div class="form-group">
                            <label>RACUN NA TERET:</label>
                            <br>
                            <input readonly="" class="form-control" style="width: 360px;" value="${transferi.racunUplatioca}">
                        </div>
                        <div class="form-group" style="margin-left: 15px;">
                            <label>RACUN U KORIST:</label>
                            <br>
                            <input readonly="" class="form-control" style="width: 360px;margin-left:0;" value="${transferi.racunPrimaoca}"/>
                        </div>
                        <br>
                        <div class="form-group">
                            <label>IZNOS:</label>
                            <br>
                            <input class="form-control" 
                                   style="
                                   padding-right:10px;
                                   width: 738px;
                                   display: block;
                                   margin-left: 0;
                                   text-align:right;" value="${transferi.iznos}" readonly=""/>
                        </div>
                        <br>
                        <div class="form-group">
                            <label>DATUM:</label>
                            <br>
                            <input type="text" readonly="" class="form-control" value="${transferi.datum}" name="date">
                        </div>
                    </div><!--krajUplatnice-->
                </div><!--kraj levisablon-->
            </div><!--kraj detalji-->
        </div>
    </body>
</html>

