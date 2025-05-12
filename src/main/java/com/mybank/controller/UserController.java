/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.controller;

import com.mybank.model.BrisanjeKorisnikaModel;
import com.mybank.model.UnosKorisnikaModel;
import com.mybank.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerKorisnik(@Valid @ModelAttribute("noviKorisnik") UnosKorisnikaModel korisnikModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:admin";
        }
        getUserService().registerKorisnik(korisnikModel);
        return "redirect:admin";
    }

    @RequestMapping(value = {"/brisanjeKorisnika"}, method = RequestMethod.POST)
    public String brisanjeKorisnika(@Valid @ModelAttribute("brisanjeKorisnika") BrisanjeKorisnikaModel brisanjeKorisnikaModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:admin";
        }
        getUserService().removeKorisnik(brisanjeKorisnikaModel);
        return "redirect:admin";
    }

    @RequestMapping(value = {"/izmeniKorisnika"}, method = RequestMethod.POST)
    public String izmenaKorisnika(@Valid @ModelAttribute("izmeniKorisnika") UnosKorisnikaModel korisnikModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:admin";
        }
        getUserService().izmeniKorisnika(korisnikModel);
        return "redirect:admin";
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
