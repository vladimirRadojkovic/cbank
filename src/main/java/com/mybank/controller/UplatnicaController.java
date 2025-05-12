/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.controller;

import com.mybank.domain.Korisnik;
import com.mybank.domain.Placanja;
import com.mybank.model.UplatnicaModel;
import com.mybank.service.PlacanjaService;
import com.mybank.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Vladimir
 */
@Controller
public class UplatnicaController {

    @Autowired
    private UserService loginService;

    @Autowired
    private PlacanjaService placanjaService;

    @RequestMapping(value = "/uplatnica", method = RequestMethod.GET)
    public String uplatnica(
            Model model,
            @RequestParam(value = "msg", required = false) String mesage) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Korisnik ulogovaniKorisnik = getLoginService().getKorisnik(user);
        model.addAttribute("datum", new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        model.addAttribute("user", ulogovaniKorisnik);
        model.addAttribute("nalogZaUplatu", new UplatnicaModel());
        if (mesage != null) {
            if (mesage.equals("sucsess")) {
                model.addAttribute("uspeh", "uspesno izvrsena uplata");
            } else {
                model.addAttribute("neuspeh", "neuspesno izvrsena uplata");
            }
        }
        return "uplatnica";
    }

    @RequestMapping(value = "/posaljiNalog", method = {RequestMethod.POST})
    public String izvrsiUplatu(
            Model model,
            @RequestParam(value = "nazivPlatioca", required = true) String nazivPlatioca,
            @RequestParam(value = "adresaPlatioca", required = true) String adresaPlatioca,
            @RequestParam(value = "mestoPlatioca", required = true) String mestoPlatioca,
            @RequestParam(value = "datumIzvrsenja", required = true) String datumIzvrsenja,
            @Valid
            @ModelAttribute("nalogZaUplatu") UplatnicaModel uplatnicaModel,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:uplatnica";
        }
        boolean paid
                = getPlacanjaService().sendSlipData(nazivPlatioca, adresaPlatioca, mestoPlatioca, datumIzvrsenja, uplatnicaModel);
        if (paid) {
            return "redirect:uplatnica?msg=sucsess";
        } else {
            return "redirect:uplatnica?msg=faild";
        }
    }

    @RequestMapping(value = {"/detalji"}, method = RequestMethod.GET)
    public String getDetails(Model model, @RequestParam(value = "reb", required = true) String redBr) {
        Placanja placanje = getPlacanjaService().getPlacanjeById(redBr);
        model.addAttribute("placanje", placanje);
        return "detalji";
    }

    /**
     * @return the loginService
     */
    public UserService getLoginService() {
        return loginService;
    }

    /**
     * @param loginService the loginService to set
     */
    public void setLoginService(UserService loginService) {
        this.loginService = loginService;
    }

    /**
     * @return the placanjaService
     */
    public PlacanjaService getPlacanjaService() {
        return placanjaService;
    }

    /**
     * @param placanjaService the placanjaService to set
     */
    public void setPlacanjaService(PlacanjaService placanjaService) {
        this.placanjaService = placanjaService;
    }
}
