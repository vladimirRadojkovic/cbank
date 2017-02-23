/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.service;

import com.mybank.domain.Krediti;
import com.mybank.model.KreditiModel;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Vladimir
 */
public interface KreditiServis {

    public List<Krediti> getListOfLoans();

    public KreditiModel izabraniKredit(String name);

    public void unesiNoviKredit(KreditiModel kreditiModel);

    public void obrisiKredit(String kreditZaBrisanje);

    List<Krediti> getAll();
}
