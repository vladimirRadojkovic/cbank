/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.Column;

/**
 *
 * @author Vladimir
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "KorisnikPodaci.findAll", query = "SELECT k FROM KorisnikPodaci k"),
    @NamedQuery(name = "KorisnikPodaci.findByKorisnickoIme", query = "SELECT k FROM KorisnikPodaci k WHERE k.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "KorisnikPodaci.findByAdresa", query = "SELECT k FROM KorisnikPodaci k WHERE k.adresa = :adresa"),
    @NamedQuery(name = "KorisnikPodaci.findByBrojTelefona", query = "SELECT k FROM KorisnikPodaci k WHERE k.brojTelefona = :brojTelefona"),
    @NamedQuery(name = "KorisnikPodaci.findByEmail", query = "SELECT k FROM KorisnikPodaci k WHERE k.email = :email"),
    @NamedQuery(name = "KorisnikPodaci.findByIme", query = "SELECT k FROM KorisnikPodaci k WHERE k.ime = :ime"),
    @NamedQuery(name = "KorisnikPodaci.findByJmbg", query = "SELECT k FROM KorisnikPodaci k WHERE k.jmbg = :jmbg"),
    @NamedQuery(name = "KorisnikPodaci.findByMesto", query = "SELECT k FROM KorisnikPodaci k WHERE k.mesto = :mesto"),
    @NamedQuery(name = "KorisnikPodaci.findByOperater", query = "SELECT k FROM KorisnikPodaci k WHERE k.operater = :operater"),
    @NamedQuery(name = "KorisnikPodaci.findByPrezime", query = "SELECT k FROM KorisnikPodaci k WHERE k.prezime = :prezime")})
public class KorisnikPodaci implements Serializable {

    @Id
    @GenericGenerator(
            name = "custom",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "korisnik"))
    @GeneratedValue(generator = "custom")
    @Column(name = "korisnik")
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String adresa;
    private String brojTelefona;
    private String email;
    private String jmbg;
    private String mesto;
    private String operater;
    private String slika;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Korisnik korisnik;

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
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * @return the adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * @param adresa the adresa to set
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * @return the brojTelefona
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * @param brojTelefona the brojTelefona to set
     */
    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the jmbg
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * @param jmbg the jmbg to set
     */
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    /**
     * @return the mesto
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * @param mesto the mesto to set
     */
    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    /**
     * @return the operater
     */
    public String getOperater() {
        return operater;
    }

    /**
     * @param operater the operater to set
     */
    public void setOperater(String operater) {
        this.operater = operater;
    }

    /**
     * @return the korisnik
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * @return the slika
     */
    public String getSlika() {
        return slika;
    }

    /**
     * @param slika the slika to set
     */
    public void setSlika(String slika) {
        this.slika = slika;
    }

}
