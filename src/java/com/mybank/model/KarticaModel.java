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
public class KarticaModel {

    private String korisnik;
    @Size(min = 3, max = 20)
    private String nazivKartice;
    @NumberFormat
    @Size(min = 5, max = 10)
    private String ogranicenje;
    @Size(min = 1, max = 20)
    private String opis;
    @Size(min = 3, max = 20)
    private String rokVazenja;
    @NumberFormat
    @Size(min = 2, max = 7)
    private String saldo;
    @NumberFormat
    @Size(min = 6, max = 10)
    private String brRacuna;

    /**
     * @return the korisnik
     */
    public String getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * @return the nazivKartice
     */
    public String getNazivKartice() {
        return nazivKartice;
    }

    /**
     * @param nazivKartice the nazivKartice to set
     */
    public void setNazivKartice(String nazivKartice) {
        this.nazivKartice = nazivKartice;
    }

    /**
     * @return the ogranicenje
     */
    public String getOgranicenje() {
        return ogranicenje;
    }

    /**
     * @param ogranicenje the ogranicenje to set
     */
    public void setOgranicenje(String ogranicenje) {
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
    public String getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the brRacuna
     */
    public String getBrRacuna() {
        return brRacuna;
    }

    /**
     * @param brRacuna the brRacuna to set
     */
    public void setBrRacuna(String brRacuna) {
        this.brRacuna = brRacuna;
    }

}
