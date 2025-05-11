/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.domain;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author Vladimir
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Transferi.findAll", query = "SELECT t FROM Transferi t"),
    @NamedQuery(name = "Transferi.findById", query = "SELECT t FROM Transferi t WHERE t.id = :id")})
public class Transferi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String datum;
    private int iznos;
    private String racunPrimaoca;
    private String racunUplatioca;

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
