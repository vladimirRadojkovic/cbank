/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Vladimir
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k"),
    @NamedQuery(name = "Korisnik.findByKorisnickoIme", query = "SELECT k FROM Korisnik k WHERE k.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Korisnik.findByAktivan", query = "SELECT k FROM Korisnik k WHERE k.aktivan = :aktivan"),
    @NamedQuery(name = "Korisnik.findByLozinka", query = "SELECT k FROM Korisnik k WHERE k.lozinka = :lozinka")})
public class Korisnik implements Serializable {

    @Id
    private String korisnickoIme;

    private boolean aktivan;
    private String lozinka;

    @OneToMany(mappedBy = "korisnickoIme", cascade = CascadeType.REMOVE)
    private Set<Kartica> kartice;

    @OneToMany(mappedBy = "korisnickoIme", cascade = CascadeType.REMOVE)
    private Set<KorisnikRoles> korisnikRole;

    @OneToOne(mappedBy = "korisnik", cascade = CascadeType.REMOVE)
    private KorisnikPodaci korisnikPodaci;

    /**
     * @return the korisnickoIme
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * @param korisnickoIme the korisnickoIme to set
     */
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * @return the lozinka
     */
    public String getLozinka() {
        return lozinka;
    }

    /**
     * @param lozinka the lozinka to set
     */
    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    /**
     * @return the kartice
     */
    @JsonIgnore
    public Set<Kartica> getKartice() {
        return kartice;
    }

    /**
     * @param kartice the kartice to set
     */
    public void setKartice(Set<Kartica> kartice) {
        this.kartice = kartice;
    }

    /**
     * @return the korisnikRole
     */
    @JsonIgnore
    public Set<KorisnikRoles> getKorisnikRole() {
        return korisnikRole;
    }

    /**
     * @param korisnikRole the korisnikRole to set
     */
    public void setKorisnikRole(Set<KorisnikRoles> korisnikRole) {
        this.korisnikRole = korisnikRole;
    }

    /**
     * @return the korisnikPodaci
     */
    public KorisnikPodaci getKorisnikPodaci() {
        return korisnikPodaci;
    }

    /**
     * @param korisnikPodaci the korisnikPodaci to set
     */
    public void setKorisnikPodaci(KorisnikPodaci korisnikPodaci) {
        this.korisnikPodaci = korisnikPodaci;
    }

    /**
     * @return the aktivan
     */
    public boolean isAktivan() {
        return aktivan;
    }

    /**
     * @param aktivan the aktivan to set
     */
    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

}
