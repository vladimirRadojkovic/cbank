/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.dao;

import com.mybank.domain.Placanja;

/**
 *
 * @author Vladimir
 */
public interface SlipDao {

    public void executeAndSaveSlip(Placanja placanja);

    public Placanja getSlipById(int id);
}
