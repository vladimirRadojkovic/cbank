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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir
 */
@Repository
public class KreditDaoImpl implements KreditDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public List<Krediti> getAllLoans() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //insert card logic
        List<Krediti> krediti = session
                .createQuery("SELECT k FROM Krediti k")
                .list();
        transaction.commit();
        return krediti;
    }

    @Override
    public Krediti findCreditByName(String name) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Krediti krediti = (Krediti) session
                .getNamedQuery("Krediti.findByVrstaKredita")
                .setParameter("vrstaKredita", name)
                .uniqueResult();
        transaction.commit();

        return krediti;
    }

    @Override
    public void insertNewKredit(Krediti krediti) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(krediti);
        transaction.commit();
    }

    @Override
    public void removeCreditByName(String kreditZaBrisanje) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Krediti krediti = (Krediti) session
                .getNamedQuery("Krediti.findByVrstaKredita")
                .setParameter("vrstaKredita", kreditZaBrisanje)
                .uniqueResult();
        session.delete(krediti);
        transaction.commit();
    }

    @Override
    public List<Krediti> getKreditList() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Krediti> krediti = session.getNamedQuery("Krediti.findAll").list();
        transaction.commit();
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
