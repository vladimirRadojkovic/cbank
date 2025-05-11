/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.controller;

import com.mybank.domain.Korisnik;
import com.mybank.domain.Krediti;
import com.mybank.model.KreditiModel;
import com.mybank.service.KreditiServis;
import com.mybank.service.UserService;
import java.util.List;
import jakarta.validation.Valid;
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
public class LoansController {

    @Autowired
    private KreditiServis kreditiServis;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/krediti", method = RequestMethod.GET)
    public String getLoans(Model model) {
        List<Krediti> vrsteKredita = getKreditiServis().getListOfLoans();
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Korisnik ulogovaniKorisnik = getUserService().getKorisnik(user);
        model.addAttribute("user", ulogovaniKorisnik);
        model.addAttribute("kreditiUPonudi", vrsteKredita);
        return "krediti";
    }

    @RequestMapping(value = "/kreditiUnos", method = RequestMethod.POST)
    public String unesiKredit(@Valid @ModelAttribute(value = "noviKredit") KreditiModel kreditiModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:admin";
        }
        getKreditiServis().unesiNoviKredit(kreditiModel);
        return "redirect:admin";
    }

    @RequestMapping(value = "/obrisiKredit", method = RequestMethod.POST)
    public String obrisiKredit(@RequestParam("krb") String kzb) {
        getKreditiServis().obrisiKredit(kzb);
        return "redirect:admin";
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
}
