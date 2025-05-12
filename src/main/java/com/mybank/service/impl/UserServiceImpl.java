/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service.impl;

import com.mybank.dao.UserDao;
import com.mybank.domain.Korisnik;
import com.mybank.domain.KorisnikPodaci;
import com.mybank.domain.KorisnikRoles;
import com.mybank.model.BrisanjeKorisnikaModel;
import com.mybank.model.UnosKorisnikaModel;
import com.mybank.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.*;

/**
 *
 * @author Vladimir
 */
@Controller
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Korisnik getKorisnik(User user) {
        Korisnik korisnik = getUserDao().findByUserName(user.getUsername());
        return korisnik;
    }

    @Override
    public List<Korisnik> getKorisnikList() {
        List<Korisnik> korisnici = getUserDao().getAllKorisnik();
        return korisnici;
    }

    @Override
    public void registerKorisnik(UnosKorisnikaModel korisnikModel) {
        Korisnik korisnik = new Korisnik();
        korisnik.setAktivan(false);
        korisnik.setKorisnickoIme(korisnikModel.getKorisnickoIme());
        KorisnikPodaci korisnikPodaci = repack(korisnikModel);
        Set<KorisnikRoles> role = new HashSet<>();
        String[] nizRola = roleArray(korisnikModel.getRola());

        KorisnikRoles korisnikRoles;
        for (String nizRola1 : nizRola) {
            korisnikRoles = new KorisnikRoles();
            korisnikRoles.setKorisnickoIme(korisnik);
            korisnikRoles.setRola(nizRola1);
            role.add(korisnikRoles);
        }

        korisnik.setKorisnikRole(role);
        korisnik.setKorisnikPodaci(korisnikPodaci);
        korisnikPodaci.setKorisnik(korisnik);

        getUserDao().registerUser(korisnik, korisnikPodaci, role);
    }

    @Override
    public void removeKorisnik(BrisanjeKorisnikaModel brisanjeKorisnikaModel) {
        Korisnik korisnik = getUserDao()
                .findByUserName(brisanjeKorisnikaModel.getIme());
        getUserDao().deleteKorisnik(korisnik);
    }

    @Override
    public UnosKorisnikaModel getKorisnikByUsername(String userName) {
        Korisnik korisnik = getUserDao().findByUserName(userName);
        StringBuilder stringBuilder = new StringBuilder();
        for (KorisnikRoles korisnikRole : korisnik.getKorisnikRole()) {
            stringBuilder
                    .append(korisnikRole.getRola())
                    .append(",");
        }
        KorisnikPodaci korisnikPodaci = getUserDao().getKorisnikPodaci(userName);

        UnosKorisnikaModel ukm = new UnosKorisnikaModel();
        ukm.setIme(korisnikPodaci.getIme());
        ukm.setPrezime(korisnikPodaci.getPrezime());
        ukm.setKorisnickoIme(korisnik.getKorisnickoIme());
        ukm.setAdresa(korisnikPodaci.getAdresa());
        ukm.setEmail(korisnikPodaci.getEmail());
        ukm.setJmbg(korisnikPodaci.getJmbg());
        ukm.setMesto(korisnikPodaci.getMesto());
        ukm.setBrTelefona(korisnikPodaci.getBrojTelefona());
        ukm.setOperater(korisnikPodaci.getOperater());
        ukm.setRola(stringBuilder.toString());

        return ukm;
    }

    @Override
    public void izmeniKorisnika(UnosKorisnikaModel unosKorisnikaModel) {
        Korisnik korisnik1 = getUserDao()
                .findByUserName(unosKorisnikaModel.getKorisnickoIme());

        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(unosKorisnikaModel.getKorisnickoIme());
        korisnik.setLozinka(korisnik1.getLozinka());
        korisnik.setAktivan(korisnik1.isAktivan());

        getUserDao().removeKorisnikRole(korisnik1);
        String[] nizRola = roleArray(unosKorisnikaModel.getRola());
        KorisnikRoles korisnikRoles = new KorisnikRoles();
        for (String nizRola1 : nizRola) {
            korisnikRoles.setKorisnickoIme(korisnik1);
            korisnikRoles.setRola(nizRola1);
            getUserDao().azurirajRole(korisnikRoles);
        }

        KorisnikPodaci korisnikPodaci = repack(unosKorisnikaModel);
        korisnikPodaci.setSlika(korisnik1.getKorisnikPodaci().getSlika());
        korisnikPodaci.setKorisnik(korisnik);

        getUserDao().azurirajKorisnika(korisnik, korisnikPodaci);
    }

    private String[] roleArray(String string) {
        String[] cleanRole = string.split(",");
        return cleanRole;
    }

    private KorisnikPodaci repack(UnosKorisnikaModel unosKorisnikaModel) {
        KorisnikPodaci korisnikPodaci = new KorisnikPodaci();

        korisnikPodaci.setIme(unosKorisnikaModel.getIme());
        korisnikPodaci.setPrezime(unosKorisnikaModel.getPrezime());
        korisnikPodaci.setKorisnickoIme(unosKorisnikaModel.getKorisnickoIme());
        korisnikPodaci.setAdresa(unosKorisnikaModel.getAdresa());
        korisnikPodaci.setBrojTelefona(unosKorisnikaModel.getBrTelefona());
        korisnikPodaci.setEmail(unosKorisnikaModel.getEmail());
        korisnikPodaci.setJmbg(unosKorisnikaModel.getJmbg());
        korisnikPodaci.setMesto(unosKorisnikaModel.getMesto());
        korisnikPodaci.setOperater(unosKorisnikaModel.getOperater());

        return korisnikPodaci;
    }

    @Override
    public Korisnik getKorisnikByLetter(String key) {
        Korisnik korisnik = getUserDao().findByUserName(key);
        return (korisnik != null) ? korisnik : null;
    }

    @Override
    public boolean aktivateUser(String aktivacijaUsername, String aktivirajPassword, String repeatPassword) {

        Korisnik k = getUserDao().findByUserName(aktivacijaUsername);

        if (aktivirajPassword.equals(repeatPassword) && k != null) {
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(aktivacijaUsername);
            korisnik.setLozinka(aktivirajPassword);
            korisnik.setAktivan(true);
            getUserDao().registerNewActiveUser(korisnik);
            return true;
        }
        return false;
    }

    @Override
    public void uploadUserPicture(String username, String photo) {
        getUserDao().azurirajKorisnikPodaci(username, photo);
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
