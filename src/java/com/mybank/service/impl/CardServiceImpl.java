/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service.impl;

import com.mybank.dao.CardDao;
import com.mybank.dao.UserDao;
import com.mybank.domain.Kartica;
import com.mybank.domain.Korisnik;
import com.mybank.model.BrisanjeKarticeModel;
import com.mybank.model.BrisanjeKorisnikaModel;
import com.mybank.model.KarticaModel;
import com.mybank.service.CardService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vladimir
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void insertCard(KarticaModel karticaModel) {
        Kartica kartica = new Kartica();
        kartica.setNaziv(karticaModel.getNazivKartice());
        kartica.setOgranicenje(Integer.parseInt(karticaModel.getOgranicenje()));
        kartica.setOpis(karticaModel.getOpis());
        kartica.setRokVazenja(karticaModel.getRokVazenja());
        kartica.setSaldo(Integer.parseInt(karticaModel.getSaldo()));
        kartica.setTekuciRacun(karticaModel.getBrRacuna());
        kartica.setKorisnickoIme(getUserDao().findByUserName(karticaModel.getKorisnik()));

        getCardDao().insertNewCard(kartica);
    }

    @Override
    public List<KarticaModel> getCardByUserName(String name) {
        Korisnik korisnik = getUserDao().findByUserName(name);
        List<KarticaModel> listaKartica = new ArrayList<>();
        for (Kartica kartica : korisnik.getKartice()) {
            KarticaModel karticaModel = new KarticaModel();
            karticaModel.setNazivKartice(kartica.getNaziv());
            karticaModel.setOgranicenje(String.valueOf(kartica.getOgranicenje()));
            karticaModel.setRokVazenja(kartica.getRokVazenja());
            karticaModel.setSaldo(String.valueOf(kartica.getSaldo()));
            karticaModel.setBrRacuna(kartica.getTekuciRacun());
            karticaModel.setKorisnik(kartica.getKorisnickoIme().getKorisnickoIme());
            listaKartica.add(karticaModel);
        }
        return listaKartica;
    }

    @Override
    public KarticaModel getCardByTr(String tr) {

        Kartica kartica = getCardDao().findCardByChecking(tr);

        KarticaModel karticaModel = new KarticaModel();
        karticaModel.setNazivKartice(kartica.getNaziv());
        karticaModel.setOgranicenje(String.valueOf(kartica.getOgranicenje()));
        karticaModel.setRokVazenja(kartica.getRokVazenja());
        karticaModel.setSaldo(String.valueOf(kartica.getSaldo()));
        karticaModel.setBrRacuna(kartica.getTekuciRacun());
        karticaModel.setKorisnik(kartica.getKorisnickoIme().getKorisnickoIme());
        karticaModel.setOpis(kartica.getOpis());

        return karticaModel;
    }

    @Override
    public void updateCard(KarticaModel karticaModel) {
        Kartica kartica = new Kartica();

        Kartica k = getCardDao().findCardByChecking(karticaModel.getBrRacuna());

        kartica.setId(k.getId());
        kartica.setNaziv(karticaModel.getNazivKartice());
        kartica.setOgranicenje(Integer.parseInt(karticaModel.getOgranicenje()));
        kartica.setOpis(karticaModel.getOpis());
        kartica.setRokVazenja(karticaModel.getRokVazenja());
        kartica.setSaldo(Integer.parseInt(karticaModel.getSaldo()));
        kartica.setTekuciRacun(karticaModel.getBrRacuna());
        kartica.setKorisnickoIme(getUserDao().findByUserName(karticaModel.getKorisnik()));

        getCardDao().updateSelectedCard(kartica);
    }

    @Override
    public void removeCard(BrisanjeKarticeModel brisanjeKarticeModel) {
        Kartica kartica = getCardDao().findCardByChecking(brisanjeKarticeModel.getKartica());
        getCardDao().deleteCard(kartica);
    }

    /**
     * @return the cardDao
     */
    public CardDao getCardDao() {
        return cardDao;
    }

    /**
     * @param cardDao the cardDao to set
     */
    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
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
