/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service.impl;

import com.mybank.dao.KreditDao;
import com.mybank.domain.Krediti;
import com.mybank.model.KreditiModel;
import com.mybank.service.KreditiServis;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vladimir
 */
@Service
public class KreditiServisImpl implements KreditiServis {

    @Autowired
    private KreditDao kreditDao;

    @Override
    public List<Krediti> getListOfLoans() {
        return getKreditDao().getAllLoans();
    }

    @Override
    public KreditiModel izabraniKredit(String name) {
        Krediti krediti = getKreditDao().findCreditByName(name);
        KreditiModel kreditiModel = new KreditiModel();
        kreditiModel.setVrstaKredita(krediti.getVrstaKredita());
        kreditiModel.setNominalnaGodisnjaStopa(krediti.getNominalnaGodisnjaStopa());
        kreditiModel.setValuta(krediti.getValuta());
        return kreditiModel;
    }

    @Override
    public void unesiNoviKredit(KreditiModel kreditiModel) {
        Krediti krediti = new Krediti();
        krediti.setNominalnaGodisnjaStopa(kreditiModel.getNominalnaGodisnjaStopa());
        krediti.setValuta(kreditiModel.getValuta());
        krediti.setVrstaKredita(kreditiModel.getVrstaKredita());
        getKreditDao().insertNewKredit(krediti);
    }

    @Override
    public void obrisiKredit(String kreditZaBrisanje) {
        getKreditDao().removeCreditByName(kreditZaBrisanje);
    }

    @Override
    public List<Krediti> getAll() {
        return getKreditDao().getKreditList();
    }

    /**
     * @return the kreditDao
     */
    public KreditDao getKreditDao() {
        return kreditDao;
    }

    /**
     * @param kreditDao the kreditDao to set
     */
    public void setKreditDao(KreditDao kreditDao) {
        this.kreditDao = kreditDao;
    }

}
