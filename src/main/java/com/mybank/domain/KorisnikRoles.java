/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vladimir
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "KorisnikRoles.findAll", query = "SELECT k FROM KorisnikRoles k"),
    @NamedQuery(name = "KorisnikRoles.findById", query = "SELECT k FROM KorisnikRoles k WHERE k.id = :id"),
    @NamedQuery(name = "KorisnikRoles.findByRola", query = "SELECT k FROM KorisnikRoles k WHERE k.rola = :rola"),
    @NamedQuery(name = "KorisnikRoles.findByKorisnickoIme", query = "SELECT k FROM KorisnikRoles k WHERE k.korisnickoIme = :korisnickoIme")})
public class KorisnikRoles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rola;
    @ManyToOne
    @JoinColumn(name = "korisnickoIme")
    private Korisnik korisnickoIme;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the rola
     */
    public String getRola() {
        return rola;
    }

    /**
     * @param rola the rola to set
     */
    public void setRola(String rola) {
        this.rola = rola;
    }

    /**
     * @return the korisnickoIme
     */
    public Korisnik getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * @param korisnickoIme the korisnickoIme to set
     */
    public void setKorisnickoIme(Korisnik korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

}
