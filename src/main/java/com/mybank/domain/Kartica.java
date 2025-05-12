/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

/**
 *
 * @author Vladimir
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Kartica.findAll", query = "SELECT k FROM Kartica k"),
    @NamedQuery(name = "Kartica.findById", query = "SELECT k FROM Kartica k WHERE k.id = :id"),
    @NamedQuery(name = "Kartica.findByNaziv", query = "SELECT k FROM Kartica k WHERE k.naziv = :naziv"),
    @NamedQuery(name = "Kartica.findByOgranicenje", query = "SELECT k FROM Kartica k WHERE k.ogranicenje = :ogranicenje"),
    @NamedQuery(name = "Kartica.findByOpis", query = "SELECT k FROM Kartica k WHERE k.opis = :opis"),
    @NamedQuery(name = "Kartica.findByRokVazenja", query = "SELECT k FROM Kartica k WHERE k.rokVazenja = :rokVazenja"),
    @NamedQuery(name = "Kartica.findBySaldo", query = "SELECT k FROM Kartica k WHERE k.saldo = :saldo"),
    @NamedQuery(name = "Kartica.findByTekuciRacun", query = "SELECT k FROM Kartica k WHERE k.tekuciRacun = :tekuciRacun")})
public class Kartica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String naziv;
    private int ogranicenje;
    private String opis;
    private String rokVazenja;
    private int saldo;
    private String tekuciRacun;

    @OneToMany(mappedBy = "idKartice", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Transferi> transferi;

    @OneToMany(mappedBy = "idKartice", cascade = CascadeType.REMOVE)
    private Set<Placanja> placanja;

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
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * @param naziv the naziv to set
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * @return the ogranicenje
     */
    public int getOgranicenje() {
        return ogranicenje;
    }

    /**
     * @param ogranicenje the ogranicenje to set
     */
    public void setOgranicenje(int ogranicenje) {
        this.ogranicenje = ogranicenje;
    }

    /**
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * @param opis the opis to set
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * @return the rokVazenja
     */
    public String getRokVazenja() {
        return rokVazenja;
    }

    /**
     * @param rokVazenja the rokVazenja to set
     */
    public void setRokVazenja(String rokVazenja) {
        this.rokVazenja = rokVazenja;
    }

    /**
     * @return the saldo
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the tekuciRacun
     */
    public String getTekuciRacun() {
        return tekuciRacun;
    }

    /**
     * @param tekuciRacun the tekuciRacun to set
     */
    public void setTekuciRacun(String tekuciRacun) {
        this.tekuciRacun = tekuciRacun;
    }

    /**
     * @return the transferi
     */
    public Set<Transferi> getTransferi() {
        return transferi;
    }

    /**
     * @param transferi the transferi to set
     */
    public void setTransferi(Set<Transferi> transferi) {
        this.transferi = transferi;
    }

    /**
     * @return the placanja
     */
    public Set<Placanja> getPlacanja() {
        return placanja;
    }

    /**
     * @param placanja the placanja to set
     */
    public void setPlacanja(Set<Placanja> placanja) {
        this.placanja = placanja;
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
