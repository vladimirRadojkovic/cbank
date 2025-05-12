/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.controller;

import com.google.gson.GsonBuilder;
import com.mybank.domain.Korisnik;
import com.mybank.model.KarticaModel;
import com.mybank.model.KreditiModel;
import com.mybank.model.UnosKorisnikaModel;
import com.mybank.service.CardService;
import com.mybank.service.KreditiServis;
import com.mybank.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Vladimir
 */
@Controller
public class AjaxController {

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Autowired
    private KreditiServis kreditiServis;

    @RequestMapping(
            value = "/podaci",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    UnosKorisnikaModel korisnik(@RequestParam("name") String name) {
        UnosKorisnikaModel izmenaKorisnikaModel = getUserService().getKorisnikByUsername(name);
        return izmenaKorisnikaModel;
    }

    @RequestMapping(
            value = "/racuni",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    ResponseEntity<String> getRacuni(@RequestParam("name") String name) {

        List<KarticaModel> kartice = getCardService().getCardByUserName(name);

        com.google.gson.Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonString = gson.toJson(kartice);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<>(jsonString, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/promeni",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    KarticaModel promeniKarticu(@RequestParam("tr") String tr) {
        KarticaModel karticaModel = getCardService().getCardByTr(tr);
        return karticaModel;
    }

    @RequestMapping(
            value = "/KorisnikProvera",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    boolean korisnikProvera(@RequestParam("name") String name) {
        Korisnik korisnik = null;
        if (name.length() > 3) {
            korisnik = getUserService().getKorisnikByLetter(name);
        }
        if (korisnik != null) {
            return true;
        }
        return false;
    }

    @RequestMapping(
            value = "/loans",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    KreditiModel kreditInfo(@RequestParam("name") String name) {
        KreditiModel kreditiModel = getKreditiServis().izabraniKredit(name);
        return kreditiModel;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return the cardService
     */
    public CardService getCardService() {
        return cardService;
    }

    /**
     * @param cardService the cardService to set
     */
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * @return the kreditiServis
     */
    public KreditiServis getKreditiServis() {
        return kreditiServis;
    }

    /**
     * @param kreditiServis the kreditiServis to set
     */
    public void setKreditiServis(KreditiServis kreditiServis) {
        this.kreditiServis = kreditiServis;
    }
}
