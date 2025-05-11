/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.model;

import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Vladimir
 */
public class TransferModel {

    @NumberFormat
    @Size(min = 2, max = 7)
    private String iznosTransfera;
    @NumberFormat
    @Size(min = 6, max = 10)
    private String racunPrimaoca;
    private String racunUplatioca;

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
     * @return the iznosTransfera
     */
    public String getIznosTransfera() {
        return iznosTransfera;
    }

    /**
     * @param iznosTransfera the iznosTransfera to set
     */
    public void setIznosTransfera(String iznosTransfera) {
        this.iznosTransfera = iznosTransfera;
    }
}
