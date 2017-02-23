/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service;

import com.mybank.domain.Transferi;
import com.mybank.model.TransferModel;

/**
 *
 * @author Vladimir
 */
public interface TransferService {

    public boolean startTransfer(TransferModel transferModel, String datum);

    public Transferi getTransferById(String redBr);
}
