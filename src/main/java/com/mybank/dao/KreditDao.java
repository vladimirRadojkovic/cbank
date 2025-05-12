/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.dao;

import com.mybank.domain.Krediti;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public interface KreditDao {

    public List<Krediti> getAllLoans();

    public Krediti findCreditByName(String name);

    public void insertNewKredit(Krediti krediti);

    public void removeCreditByName(String kreditZaBrisanje);

    public List<Krediti> getKreditList();
}
