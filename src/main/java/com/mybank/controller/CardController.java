/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.controller;

import com.mybank.model.BrisanjeKarticeModel;
import com.mybank.model.KarticaModel;
import com.mybank.service.CardService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vladimir
 */
@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping(value = {"/cardEntry"}, method = RequestMethod.POST)
    public String KarticaUnos(@Valid @ModelAttribute("novaKartica") KarticaModel karticaModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {   
            return "redirect:admin";
        }
        getCardService().insertCard(karticaModel);
        return "redirect:admin";
    }

    @RequestMapping(value = {"/cardChange"}, method = RequestMethod.POST)
    public String cardChange(@Valid @ModelAttribute("izmeniKarticu") KarticaModel karticaModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:admin";
        }
        getCardService().updateCard(karticaModel);
        return "redirect:admin";
    }

    @RequestMapping(value = {"/obrisiKarticu"}, method = RequestMethod.POST)
    public String cardRemove(@ModelAttribute("obrisiKarticu") BrisanjeKarticeModel brisanjeKarticeModel) {
        getCardService().removeCard(brisanjeKarticeModel);
        return "redirect:admin";
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
}
