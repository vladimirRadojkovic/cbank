/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.dao;

import com.mybank.domain.Kartica;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public interface CardDao {

    public void insertNewCard(Kartica kartica);

    public Kartica findCardByChecking(String tekuciRacun);

    public void updateSelectedCard(Kartica kartica);

    public boolean updateChecking(String tr, int amount);

    public void deleteCard(Kartica kartica);
}
