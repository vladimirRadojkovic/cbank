/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service;

import com.mybank.domain.Korisnik;
import com.mybank.model.BrisanjeKorisnikaModel;
import com.mybank.model.UnosKorisnikaModel;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Vladimir
 */
public interface UserService {

    public UnosKorisnikaModel getKorisnikByUsername(String userName);

    public Korisnik getKorisnik(User user);

    public List<Korisnik> getKorisnikList();

    public void registerKorisnik(UnosKorisnikaModel korisnikModel);

    public void removeKorisnik(BrisanjeKorisnikaModel brisanjeKorisnikaModel);

    public void izmeniKorisnika(UnosKorisnikaModel unosKorisnikaModel);

    public Korisnik getKorisnikByLetter(String key);

    public boolean aktivateUser(String aktivacijaUsername, String aktivirajPassword, String repeatPassword);

    public void uploadUserPicture(String username, String photo);
}
