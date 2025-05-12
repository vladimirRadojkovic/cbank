/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service.impl;

import com.mybank.dao.CardDao;
import com.mybank.dao.TransferDao;
import com.mybank.domain.Kartica;
import com.mybank.domain.Transferi;
import com.mybank.model.TransferModel;
import com.mybank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vladimir
 */
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferDao transferDao;

    @Autowired
    private CardDao cardDao;

    @Override
    public boolean startTransfer(TransferModel transferModel, String datum) {

        boolean uspesanTransfer
                = getCardDao().updateChecking(transferModel.getRacunUplatioca(), Integer.parseInt(transferModel.getIznosTransfera()));

        if (uspesanTransfer) {
            Kartica kartica = getCardDao().findCardByChecking(transferModel.getRacunUplatioca());

            Transferi transferi = new Transferi();
            transferi.setDatum(datum);
            transferi.setIznos(Integer.parseInt(transferModel.getIznosTransfera()));
            transferi.setRacunPrimaoca(transferModel.getRacunPrimaoca());
            transferi.setRacunUplatioca(transferModel.getRacunUplatioca());
            transferi.setIdKartice(kartica);

            getTransferDao().saveTransfer(transferi);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Transferi getTransferById(String redBr) {
        Transferi transferi = getTransferDao().findById(redBr);
        return transferi;
    }

    /**
     * @return the transferDao
     */
    public TransferDao getTransferDao() {
        return transferDao;
    }

    /**
     * @param transferDao the transferDao to set
     */
    public void setTransferDao(TransferDao transferDao) {
        this.transferDao = transferDao;
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
