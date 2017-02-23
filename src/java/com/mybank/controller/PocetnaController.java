package com.mybank.controller;

import com.mybank.domain.Korisnik;
import com.mybank.domain.Krediti;
import com.mybank.model.BrisanjeKarticeModel;
import com.mybank.model.BrisanjeKorisnikaModel;
import com.mybank.model.KarticaModel;
import com.mybank.model.KreditiModel;
import com.mybank.model.UnosKorisnikaModel;
import com.mybank.service.KreditiServis;
import com.mybank.service.UserService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Vladimir
 */
@Controller
public class PocetnaController {

    @Autowired
    private KreditiServis kreditiServis;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String loginPage(Model model, @RequestParam(value = "msg", required = false) String mesage) {
        if (mesage != null) {
            if (mesage.equals("sucsess")) {
                model.addAttribute("uspeh", "uspesna registracija");
            } else {
                model.addAttribute("neuspeh", "neuspesna registracija");
            }
        }
        return "index";
    }

    @RequestMapping(value = {"/pocetna"}, method = RequestMethod.GET)
    public String getPocetna(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Korisnik ulogovaniKorisnik = getUserService().getKorisnik(user);
        model.addAttribute("user", ulogovaniKorisnik);

        return "pocetna";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String getAdmin(
            Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        List<Krediti> sviKrediti = getKreditiServis().getAll();
        Korisnik ulogovaniKorisnik = getUserService().getKorisnik(user);
        model.addAttribute("sviKrediti", sviKrediti);
        model.addAttribute("adname", ulogovaniKorisnik.getKorisnikPodaci().getIme());
        model.addAttribute("noviKorisnik", new UnosKorisnikaModel());
        model.addAttribute("sviKorisnici", getUserService().getKorisnikList());
        model.addAttribute("novaKartica", new KarticaModel());
        model.addAttribute("brisanjeKorisnika", new BrisanjeKorisnikaModel());
        model.addAttribute("izmeniKorisnika", new UnosKorisnikaModel());
        model.addAttribute("izmeniKarticu", new KarticaModel());
        model.addAttribute("obrisiKarticu", new BrisanjeKarticeModel());
        model.addAttribute("noviKredit", new KreditiModel());

        return "administrator";
    }

    @RequestMapping("/redirect")
    public String redirection() {
        return "redirect";
    }

    @RequestMapping(value = "/pictureUpload", method = RequestMethod.POST)
    public String pictureUpload(
            @RequestParam("korisnik") String username,
            @RequestParam MultipartFile photo,
            HttpServletRequest request) throws FileNotFoundException, IOException {
        if (!photo.isEmpty()) {
            String filepath = request.getServletContext().getRealPath("resources/images");
                try (FileOutputStream fos = new FileOutputStream(filepath + "/" + photo.getOriginalFilename())) {
                    fos.write(photo.getBytes());
                }
            getUserService().uploadUserPicture(username, photo.getOriginalFilename());
        }
        return "redirect:pocetna";
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
