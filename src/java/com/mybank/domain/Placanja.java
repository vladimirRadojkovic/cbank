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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vladimir
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Placanja.findAll", query = "SELECT p FROM Placanja p"),
    @NamedQuery(name = "Placanja.findById", query = "SELECT p FROM Placanja p WHERE p.id = :id"),
    @NamedQuery(name = "Placanja.findByAdresaPrimaoca", query = "SELECT p FROM Placanja p WHERE p.adresaPrimaoca = :adresaPrimaoca"),
    @NamedQuery(name = "Placanja.findByAdresaUplatioca", query = "SELECT p FROM Placanja p WHERE p.adresaUplatioca = :adresaUplatioca"),
    @NamedQuery(name = "Placanja.findByDatum", query = "SELECT p FROM Placanja p WHERE p.datum = :datum"),
    @NamedQuery(name = "Placanja.findByIznos", query = "SELECT p FROM Placanja p WHERE p.iznos = :iznos"),
    @NamedQuery(name = "Placanja.findByMestoPrimaoca", query = "SELECT p FROM Placanja p WHERE p.mestoPrimaoca = :mestoPrimaoca"),
    @NamedQuery(name = "Placanja.findByMestoUplatioca", query = "SELECT p FROM Placanja p WHERE p.mestoUplatioca = :mestoUplatioca"),
    @NamedQuery(name = "Placanja.findByModel", query = "SELECT p FROM Placanja p WHERE p.model = :model"),
    @NamedQuery(name = "Placanja.findByNazivPrimaoca", query = "SELECT p FROM Placanja p WHERE p.nazivPrimaoca = :nazivPrimaoca"),
    @NamedQuery(name = "Placanja.findByNazivUplatioca", query = "SELECT p FROM Placanja p WHERE p.nazivUplatioca = :nazivUplatioca"),
    @NamedQuery(name = "Placanja.findByPozivNaBroj", query = "SELECT p FROM Placanja p WHERE p.pozivNaBroj = :pozivNaBroj"),
    @NamedQuery(name = "Placanja.findByRacunPrimaoca", query = "SELECT p FROM Placanja p WHERE p.racunPrimaoca = :racunPrimaoca"),
    @NamedQuery(name = "Placanja.findByRacunUplatioca", query = "SELECT p FROM Placanja p WHERE p.racunUplatioca = :racunUplatioca"),
    @NamedQuery(name = "Placanja.findBySifraPlacanja", query = "SELECT p FROM Placanja p WHERE p.sifraPlacanja = :sifraPlacanja"),
    @NamedQuery(name = "Placanja.findBySvrhaPlacanja", query = "SELECT p FROM Placanja p WHERE p.svrhaPlacanja = :svrhaPlacanja")})
public class Placanja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String adresaPrimaoca;
    private String adresaUplatioca;
    private String datum;
    private int iznos;
    private String mestoPrimaoca;
    private String mestoUplatioca;
    private String model;
    private String pozivNaBroj;
    private String nazivPrimaoca;
    private String racunPrimaoca;
    private String racunUplatioca;
    private String sifraPlacanja;
    private String svrhaPlacanja;
    private String nazivUplatioca;

    @ManyToOne
    private Kartica idKartice;

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
     * @return the adresaPrimaoca
     */
    public String getAdresaPrimaoca() {
        return adresaPrimaoca;
    }

    /**
     * @param adresaPrimaoca the adresaPrimaoca to set
     */
    public void setAdresaPrimaoca(String adresaPrimaoca) {
        this.adresaPrimaoca = adresaPrimaoca;
    }

    /**
     * @return the adresaUplatioca
     */
    public String getAdresaUplatioca() {
        return adresaUplatioca;
    }

    /**
     * @param adresaUplatioca the adresaUplatioca to set
     */
    public void setAdresaUplatioca(String adresaUplatioca) {
        this.adresaUplatioca = adresaUplatioca;
    }

    /**
     * @return the datum
     */
    public String getDatum() {
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(String datum) {
        this.datum = datum;
    }

    /**
     * @return the iznos
     */
    public int getIznos() {
        return iznos;
    }

    /**
     * @param iznos the iznos to set
     */
    public void setIznos(int iznos) {
        this.iznos = iznos;
    }

    /**
     * @return the mestoPrimaoca
     */
    public String getMestoPrimaoca() {
        return mestoPrimaoca;
    }

    /**
     * @param mestoPrimaoca the mestoPrimaoca to set
     */
    public void setMestoPrimaoca(String mestoPrimaoca) {
        this.mestoPrimaoca = mestoPrimaoca;
    }

    /**
     * @return the mestoUplatioca
     */
    public String getMestoUplatioca() {
        return mestoUplatioca;
    }

    /**
     * @param mestoUplatioca the mestoUplatioca to set
     */
    public void setMestoUplatioca(String mestoUplatioca) {
        this.mestoUplatioca = mestoUplatioca;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the pozivNaBroj
     */
    public String getPozivNaBroj() {
        return pozivNaBroj;
    }

    /**
     * @param pozivNaBroj the pozivNaBroj to set
     */
    public void setPozivNaBroj(String pozivNaBroj) {
        this.pozivNaBroj = pozivNaBroj;
    }

    /**
     * @return the nazivPrimaoca
     */
    public String getNazivPrimaoca() {
        return nazivPrimaoca;
    }

    /**
     * @param nazivPrimaoca the nazivPrimaoca to set
     */
    public void setNazivPrimaoca(String nazivPrimaoca) {
        this.nazivPrimaoca = nazivPrimaoca;
    }

    /**
     * @return the racunPrimaoca
     */
    public String getRacunPrimaoca() {
        return racunPrimaoca;
    }

    /**
     * @param racunPrimaoca the racunPrimaoca to set
     */
    public void setRacunPrimaoca(String racunPrimaoca) {
        this.racunPrimaoca = racunPrimaoca;
    }

    /**
     * @return the racunUplatioca
     */
    public String getRacunUplatioca() {
        return racunUplatioca;
    }

    /**
     * @param racunUplatioca the racunUplatioca to set
     */
    public void setRacunUplatioca(String racunUplatioca) {
        this.racunUplatioca = racunUplatioca;
    }

    /**
     * @return the sifraPlacanja
     */
    public String getSifraPlacanja() {
        return sifraPlacanja;
    }

    /**
     * @param sifraPlacanja the sifraPlacanja to set
     */
    public void setSifraPlacanja(String sifraPlacanja) {
        this.sifraPlacanja = sifraPlacanja;
    }

    /**
     * @return the svrhaPlacanja
     */
    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    /**
     * @param svrhaPlacanja the svrhaPlacanja to set
     */
    public void setSvrhaPlacanja(String svrhaPlacanja) {
        this.svrhaPlacanja = svrhaPlacanja;
    }

    /**
     * @return the nazivUplatioca
     */
    public String getNazivUplatioca() {
        return nazivUplatioca;
    }

    /**
     * @param nazivUplatioca the nazivUplatioca to set
     */
    public void setNazivUplatioca(String nazivUplatioca) {
        this.nazivUplatioca = nazivUplatioca;
    }

    /**
     * @return the idKartice
     */
    public Kartica getIdKartice() {
        return idKartice;
    }

    /**
     * @param idKartice the idKartice to set
     */
    public void setIdKartice(Kartica idKartice) {
        this.idKartice = idKartice;
    }

}
