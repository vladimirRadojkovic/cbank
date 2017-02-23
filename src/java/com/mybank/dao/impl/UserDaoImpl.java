package com.mybank.dao.impl;

import com.mybank.dao.UserDao;
import com.mybank.domain.Korisnik;
import com.mybank.domain.KorisnikPodaci;
import com.mybank.domain.KorisnikRoles;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public Korisnik findByUserName(String username) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Korisnik korisnik = (Korisnik) session
                .getNamedQuery("Korisnik.findByKorisnickoIme")
                .setParameter("korisnickoIme", username)
                .uniqueResult();
        transaction.commit();
        return (korisnik != null) ? korisnik : null;
    }

    @Override
    public void registerUser(Korisnik korisnik, KorisnikPodaci korisnikPodaci, Set<KorisnikRoles> korisnikRole) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //register logic
        session.save(korisnik);
        session.save(korisnikPodaci);
        for (KorisnikRoles korisnikRole1 : korisnikRole) {
            session.save(korisnikRole1);
        }
        //register logic
        transaction.commit();
    }

    @Override
    public List<Korisnik> getAllKorisnik() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Korisnik> listaKorisnika = session
                .getNamedQuery("Korisnik.findAll")
                .list();
        transaction.commit();
        return listaKorisnika;
    }

    @Override
    public void deleteKorisnik(Korisnik korisnik) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(korisnik);
        transaction.commit();
    }

    @Override
    public KorisnikPodaci getKorisnikPodaci(String username) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        KorisnikPodaci korisnikPodaci = (KorisnikPodaci) session
                .getNamedQuery("KorisnikPodaci.findByKorisnickoIme")
                .setParameter("korisnickoIme", username)
                .uniqueResult();
        transaction.commit();
        return korisnikPodaci;
    }

    @Override
    public void azurirajKorisnika(Korisnik korisnik, KorisnikPodaci korisnikPodaci) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(korisnik);
        session.update(korisnikPodaci);
        transaction.commit();
    }

    @Override
    public void azurirajRole(KorisnikRoles korisnikRoles) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(korisnikRoles);
        transaction.commit();
    }

    @Override
    public void removeKorisnikRole(Korisnik korisnik) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for (KorisnikRoles korisnikRoles : korisnik.getKorisnikRole()) {
            session.delete(korisnikRoles);
        }
        transaction.commit();
    }

    @Override
    public void registerNewActiveUser(Korisnik korisnik) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(korisnik);
        transaction.commit();
    }

    @Override
    public void azurirajKorisnikPodaci(String username, String photo) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session
                .createQuery("UPDATE KorisnikPodaci AS k SET k.slika = :photo WHERE k.korisnickoIme = :username")
                .setParameter("photo", photo)
                .setParameter("username", username);
        int result = query.executeUpdate();
        transaction.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
