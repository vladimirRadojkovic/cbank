/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service;

import com.mybank.domain.Kartica;
import com.mybank.model.BrisanjeKarticeModel;
import com.mybank.model.BrisanjeKorisnikaModel;
import com.mybank.model.KarticaModel;
import com.mybank.model.KarticaModel;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Vladimir
 */
public interface CardService {

    public void insertCard(KarticaModel karticaModel);

    public List<KarticaModel> getCardByUserName(String name);

    public KarticaModel getCardByTr(String tr);

    public void updateCard(KarticaModel karticaModel);

    public void removeCard(BrisanjeKarticeModel brisanjeKarticeModel);
}
