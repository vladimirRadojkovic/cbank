/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service.impl;

import com.mybank.dao.CardDao;
import com.mybank.dao.SlipDao;
import com.mybank.domain.Kartica;
import com.mybank.domain.Placanja;
import com.mybank.model.UplatnicaModel;
import com.mybank.service.PlacanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vladimir
 */
@Service
public class PlacanjaServiceImpl implements PlacanjaService {

    @Autowired
    private SlipDao slipDao;

    @Autowired
    private CardDao cardDao;

    @Override
    public boolean sendSlipData(String nazivPlatioca, String adresaPlatioca, String mestoPlatioca, String datumIzvrsenja, UplatnicaModel uplatnicaModel) {

        boolean sucsess = getCardDao()
                .updateChecking(uplatnicaModel.getRacunUplatioca(), Integer.parseInt(uplatnicaModel.getIznos()));

        if (sucsess) {
            Kartica kartica = getCardDao().findCardByChecking(uplatnicaModel.getRacunUplatioca());

            Placanja placanja = new Placanja();
            placanja.setAdresaPrimaoca(uplatnicaModel.getAdresaPrimaoca());
            placanja.setAdresaUplatioca(adresaPlatioca);
            placanja.setDatum(datumIzvrsenja);
            placanja.setIznos(Integer.parseInt(uplatnicaModel.getIznos()));
            placanja.setMestoPrimaoca(uplatnicaModel.getMestoPrimaoca());
            placanja.setMestoUplatioca(mestoPlatioca);
            placanja.setModel(uplatnicaModel.getModel());
            placanja.setNazivPrimaoca(uplatnicaModel.getNazivPrimaoca());
            placanja.setNazivUplatioca(nazivPlatioca);
            placanja.setPozivNaBroj(uplatnicaModel.getPozivNaBroj());
            placanja.setRacunPrimaoca(uplatnicaModel.getRacunPrimaoca());
            placanja.setRacunUplatioca(uplatnicaModel.getRacunUplatioca());
            placanja.setSifraPlacanja(uplatnicaModel.getSifraPlacanja());
            placanja.setSvrhaPlacanja(uplatnicaModel.getSvrhaPlacanja());
            placanja.setIdKartice(kartica);

            getSlipDao().executeAndSaveSlip(placanja);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Placanja getPlacanjeById(String redBr) {
        Placanja placanja = getSlipDao().getSlipById(Integer.parseInt(redBr));
        return placanja;
    }

    /**
     * @return the slipDao
     */
    public SlipDao getSlipDao() {
        return slipDao;
    }

    /**
     * @param slipDao the slipDao to set
     */
    public void setSlipDao(SlipDao slipDao) {
        this.slipDao = slipDao;
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

}
