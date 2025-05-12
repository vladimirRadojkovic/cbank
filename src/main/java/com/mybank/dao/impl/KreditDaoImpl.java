/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.dao.impl;

import com.mybank.dao.KreditDao;
import com.mybank.domain.Krediti;
import java.util.List;
import org.hibernate.Query;
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
public class KreditDaoImpl implements KreditDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Krediti> getAllLoans() {
        Session session = getCurrentSession();
        List<Krediti> krediti = session
                .createQuery("SELECT k FROM Krediti k")
                .list();
        return krediti;
    }

    @Override
    @Transactional(readOnly = true)
    public Krediti findCreditByName(String name) {
        Session session = getCurrentSession();
        Krediti krediti = (Krediti) session
                .getNamedQuery("Krediti.findByVrstaKredita")
                .setParameter("vrstaKredita", name)
                .uniqueResult();
        return krediti;
    }

    @Override
    public void insertNewKredit(Krediti krediti) {
        Session session = getCurrentSession();
        session.save(krediti);
    }

    @Override
    public void removeCreditByName(String kreditZaBrisanje) {
        Session session = getCurrentSession();
        Krediti krediti = (Krediti) session
                .getNamedQuery("Krediti.findByVrstaKredita")
                .setParameter("vrstaKredita", kreditZaBrisanje)
                .uniqueResult();
        session.delete(krediti);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Krediti> getKreditList() {
        Session session = getCurrentSession();
        List<Krediti> krediti = session.getNamedQuery("Krediti.findAll").list();
        return krediti;
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
