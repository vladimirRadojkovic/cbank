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
public class UplatnicaModel {

    private String sifraPlacanja;
    @Size(min = 2, max = 7)
    @NumberFormat
    private String iznos;
    @Size(min = 2, max = 20)
    private String nazivPrimaoca;
    @Size(min = 2, max = 50)
    private String adresaPrimaoca;
    @Size(min = 2, max = 20)
    private String mestoPrimaoca;
    @NotNull
    private String racunUplatioca;
    @NotNull
    @NumberFormat
    @Size(min = 6, max = 10)
    private String racunPrimaoca;
    @NotNull
    private String model;
    @NotNull
    private String pozivNaBroj;
    @NotNull
    private String svrhaPlacanja;
    @NotNull
    private boolean hitno;

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
     * @return the iznos
     */
    public String getIznos() {
        return iznos;
    }

    /**
     * @param iznos the iznos to set
     */
    public void setIznos(String iznos) {
        this.iznos = iznos;
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
     * @return the hitno
     */
    public boolean isHitno() {
        return hitno;
    }

    /**
     * @param hitno the hitno to set
     */
    public void setHitno(boolean hitno) {
        this.hitno = hitno;
    }
}
