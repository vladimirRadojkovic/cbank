/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.controller;

import com.mybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Vladimir
 */
@Controller
public class AktivirajServisController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/aktivirajServis"}, method = {RequestMethod.GET})
    public String aktivirajServis() {
        return "aktivirajServis";
    }

    @RequestMapping(value = {"/aktivirajServis"}, method = RequestMethod.POST)
    public String zapamtiIzmene(
            @RequestParam(value = "aktivirajUsername", required = true) String aktivacijaUsername,
            @RequestParam(value = "aktivirajPassword", required = true) String aktivirajPassword,
            @RequestParam(value = "repeatPassword", required = true) String repeatPassword) {
        boolean isActive
                = getUserService().aktivateUser(aktivacijaUsername, aktivirajPassword, repeatPassword);
        return (isActive) ? "redirect:login?msg=sucsess" : "redirect:login?msg=faild";
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
