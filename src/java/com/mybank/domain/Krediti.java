/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vladimir
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Krediti.findAll", query = "SELECT k FROM Krediti k"),
    @NamedQuery(name = "Krediti.findByVrstaKredita", query = "SELECT k FROM Krediti k WHERE k.vrstaKredita = :vrstaKredita")})
public class Krediti implements Serializable {

    @Id
    private String vrstaKredita;
    private String nominalnaGodisnjaStopa;
    private String valuta;

    /**
     * @return the vrstaKredita
     */
    public String getVrstaKredita() {
        return vrstaKredita;
    }

    /**
     * @param vrstaKredita the vrstaKredita to set
     */
    public void setVrstaKredita(String vrstaKredita) {
        this.vrstaKredita = vrstaKredita;
    }

    /**
     * @return the nominalnaGodisnjaStopa
     */
    public String getNominalnaGodisnjaStopa() {
        return nominalnaGodisnjaStopa;
    }

    /**
     * @param nominalnaGodisnjaStopa the nominalnaGodisnjaStopa to set
     */
    public void setNominalnaGodisnjaStopa(String nominalnaGodisnjaStopa) {
        this.nominalnaGodisnjaStopa = nominalnaGodisnjaStopa;
    }

    /**
     * @return the valuta
     */
    public String getValuta() {
        return valuta;
    }

    /**
     * @param valuta the valuta to set
     */
    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

}
