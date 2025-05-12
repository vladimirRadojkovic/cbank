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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    public Korisnik findByUserName(String username) {
        System.out.println("DEBUG: UserDaoImpl.findByUserName called for username: " + username);
        try {
            Session session = getCurrentSession();
            Korisnik korisnik = (Korisnik) session
                    .getNamedQuery("Korisnik.findByKorisnickoIme")
                    .setParameter("korisnickoIme", username)
                    .uniqueResult();
            System.out.println("DEBUG: UserDaoImpl.findByUserName result: " + (korisnik != null ? korisnik.getKorisnickoIme() : "null"));
            return korisnik;
        } catch (Exception e) {
            System.out.println("ERROR: Exception in findByUserName: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void registerUser(Korisnik korisnik, KorisnikPodaci korisnikPodaci, Set<KorisnikRoles> korisnikRole) {
        Session session = getCurrentSession();
        //register logic
        session.save(korisnik);
        session.save(korisnikPodaci);
        for (KorisnikRoles korisnikRole1 : korisnikRole) {
            session.save(korisnikRole1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Korisnik> getAllKorisnik() {
        Session session = getCurrentSession();
        List<Korisnik> listaKorisnika = session
                .getNamedQuery("Korisnik.findAll")
                .list();
        return listaKorisnika;
    }

    @Override
    public void deleteKorisnik(Korisnik korisnik) {
        Session session = getCurrentSession();
        session.delete(korisnik);
    }

    @Override
    @Transactional(readOnly = true)
    public KorisnikPodaci getKorisnikPodaci(String username) {
        Session session = getCurrentSession();
        KorisnikPodaci korisnikPodaci = (KorisnikPodaci) session
                .getNamedQuery("KorisnikPodaci.findByKorisnickoIme")
                .setParameter("korisnickoIme", username)
                .uniqueResult();
        return korisnikPodaci;
    }

    @Override
    public void azurirajKorisnika(Korisnik korisnik, KorisnikPodaci korisnikPodaci) {
        Session session = getCurrentSession();
        session.update(korisnik);
        session.update(korisnikPodaci);
    }

    @Override
    public void azurirajRole(KorisnikRoles korisnikRoles) {
        Session session = getCurrentSession();
        session.save(korisnikRoles);
    }

    @Override
    public void removeKorisnikRole(Korisnik korisnik) {
        Session session = getCurrentSession();
        for (KorisnikRoles korisnikRoles : korisnik.getKorisnikRole()) {
            session.delete(korisnikRoles);
        }
    }

    @Override
    public void registerNewActiveUser(Korisnik korisnik) {
        Session session = getCurrentSession();
        session.update(korisnik);
    }

    @Override
    public void azurirajKorisnikPodaci(String username, String photo) {
        Session session = getCurrentSession();
        Query query = session
                .createQuery("UPDATE KorisnikPodaci AS k SET k.slika = :photo WHERE k.korisnickoIme = :username")
                .setParameter("photo", photo)
                .setParameter("username", username);
        int result = query.executeUpdate();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
