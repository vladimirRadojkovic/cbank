/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.dao;

import com.mybank.domain.Transferi;
import com.mybank.model.TransferModel;

/**
 *
 * @author Vladimir
 */
public interface TransferDao {

    public void saveTransfer(Transferi transferi);

    public Transferi findById(String redBr);
}
