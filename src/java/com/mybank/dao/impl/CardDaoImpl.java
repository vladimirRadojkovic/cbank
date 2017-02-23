package com.mybank.dao.impl;

import com.mybank.dao.CardDao;
import com.mybank.domain.Kartica;
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
public class CardDaoImpl implements CardDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void insertNewCard(Kartica kartica) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //insert card logic
        session.save(kartica);
        transaction.commit();
    }

    @Override
    public Kartica findCardByChecking(String tekuciRacun) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Kartica kartica = (Kartica) session.getNamedQuery("Kartica.findByTekuciRacun")
                .setParameter("tekuciRacun", tekuciRacun)
                .uniqueResult();
        transaction.commit();
        return kartica;
    }

    @Override
    public void updateSelectedCard(Kartica kartica) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(kartica);
        transaction.commit();
    }

    @Override
    public boolean updateChecking(String tr, int amount) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Kartica kartica = (Kartica) session.getNamedQuery("Kartica.findByTekuciRacun")
                .setParameter("tekuciRacun", tr)
                .uniqueResult();

        if (kartica.getSaldo() < 0) {
            transaction.rollback();
            return false;
        }
        if ((kartica.getSaldo() - amount) < 0) {
            transaction.rollback();
            return false;
        }
        try {
            kartica.setSaldo(kartica.getSaldo() - amount);
            session.update(kartica);
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
        transaction.commit();
        return true;
    }

    @Override
    public void deleteCard(Kartica kartica) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(kartica);
        transaction.commit();
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
