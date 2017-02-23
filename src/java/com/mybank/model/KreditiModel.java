/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Vladimir
 */
public class KreditiModel {

    @Size(min = 5, max = 30)
    private String vrstaKredita;
    @NumberFormat
    @Size(min = 1, max = 10)
    private String nominalnaGodisnjaStopa;
    @NotNull
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
