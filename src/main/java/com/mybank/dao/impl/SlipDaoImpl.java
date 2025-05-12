/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.dao.impl;

import com.mybank.dao.SlipDao;
import com.mybank.domain.Placanja;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vladimir
 */
@Repository
@Transactional
public class SlipDaoImpl implements SlipDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void executeAndSaveSlip(Placanja placanja) {
        Session session = getCurrentSession();
        session.save(placanja);
    }

    @Override
    @Transactional(readOnly = true)
    public Placanja getSlipById(int id) {
        Session session = getCurrentSession();
        Placanja placanja = (Placanja) session
                .getNamedQuery("Placanja.findById")
                .setParameter("id", id)
                .uniqueResult();
        return placanja;
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
