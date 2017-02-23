/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.dao.impl;

import com.mybank.dao.TransferDao;
import com.mybank.domain.Placanja;
import com.mybank.domain.Transferi;
import com.mybank.model.TransferModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir
 */
@Repository
public class TransferDaoImpl implements TransferDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void saveTransfer(Transferi transferi) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //insert logic
        session.save(transferi);
        transaction.commit();
    }

    @Override
    public Transferi findById(String redBr) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Transferi transferi = (Transferi) session
                .getNamedQuery("Transferi.findById")
                .setParameter("id", Integer.parseInt(redBr))
                .uniqueResult();
        transaction.commit();
        return transferi;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
