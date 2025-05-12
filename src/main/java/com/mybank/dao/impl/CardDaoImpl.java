package com.mybank.dao.impl;

import com.mybank.dao.CardDao;
import com.mybank.domain.Kartica;
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
public class CardDaoImpl implements CardDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void insertNewCard(Kartica kartica) {
        Session session = getCurrentSession();
        session.save(kartica);
    }

    @Override
    @Transactional(readOnly = true)
    public Kartica findCardByChecking(String tekuciRacun) {
        Session session = getCurrentSession();
        Kartica kartica = (Kartica) session.getNamedQuery("Kartica.findByTekuciRacun")
                .setParameter("tekuciRacun", tekuciRacun)
                .uniqueResult();
        return kartica;
    }

    @Override
    public void updateSelectedCard(Kartica kartica) {
        Session session = getCurrentSession();
        session.update(kartica);
    }

    @Override
    public boolean updateChecking(String tr, int amount) {
        Session session = getCurrentSession();
        Kartica kartica = (Kartica) session.getNamedQuery("Kartica.findByTekuciRacun")
                .setParameter("tekuciRacun", tr)
                .uniqueResult();

        if (kartica.getSaldo() < 0) {
            return false;
        }
        if ((kartica.getSaldo() - amount) < 0) {
            return false;
        }
        try {
            kartica.setSaldo(kartica.getSaldo() - amount);
            session.update(kartica);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    @Override
    public void deleteCard(Kartica kartica) {
        Session session = getCurrentSession();
        session.delete(kartica);
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
