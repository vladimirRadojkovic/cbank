/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service;

import com.mybank.domain.Placanja;
import com.mybank.model.UplatnicaModel;

/**
 *
 * @author Vladimir
 */
public interface PlacanjaService {

    public boolean sendSlipData(String nazivPlatioca, String adresaPlatioca, String mestoPlatioca, String datumIzvrsenja, UplatnicaModel uplatnicaModel);

    public Placanja getPlacanjeById(String redBr);

}
