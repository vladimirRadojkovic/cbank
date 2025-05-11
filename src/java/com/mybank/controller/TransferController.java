/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.controller;

import com.mybank.domain.Korisnik;
import com.mybank.domain.Transferi;
import com.mybank.model.TransferModel;
import com.mybank.service.TransferService;
import com.mybank.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserService loginService;

    @RequestMapping(value = "/transferi")
    public String getTransfer(Model model, @RequestParam(value = "msg", required = false) String message) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Korisnik ulogovaniKorisnik = getLoginService().getKorisnik(user);
        model.addAttribute("user", ulogovaniKorisnik);
        model.addAttribute("datum", new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        model.addAttribute("noviTransfer", new TransferModel());
        if (message != null) {
            if (message.equals("sucsess")) {
                model.addAttribute("uspeh", "uspesno izvrsen transfer");
            } else {
                model.addAttribute("neuspeh", "neuspesno izvrsen transfer");
            }
        }
        return "transferi";
    }

    @RequestMapping(value = "/inicirajTransfer", method = RequestMethod.POST)
    public String zapocniTransfer(@Valid @RequestParam(value = "date", required = true) String datum, @ModelAttribute("noviTransfer") TransferModel transferModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:transferi";
        }
        boolean transferFinished
                = getTransferService().startTransfer(transferModel, datum);
        if (transferFinished) {
            return "redirect:transferi?msg=sucsess";
        } else {
            return "redirect:transferi?msg=faild";
        }
    }

    @RequestMapping(value = {"/tdetalji"}, method = RequestMethod.GET)
    public String getDetails(Model model, @RequestParam(value = "reb", required = true) String redBr) {
        Transferi transferi = getTransferService().getTransferById(redBr);
        model.addAttribute("transferi", transferi);
        return "tdetalji";
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
     * @return the transferService
     */
    public TransferService getTransferService() {
        return transferService;
    }

    /**
     * @param transferService the transferService to set
     */
    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }
}
