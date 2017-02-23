package com.mybank.model;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.NumberFormat;

public class UnosKorisnikaModel {

    @Size(min = 3, max = 20)
    private String ime;
    @Size(min = 3, max = 20)
    private String prezime;
    @Size(min = 3, max = 20)
    private String korisnickoIme;
    @Email
    private String email;
    @NumberFormat
    private String jmbg;
    @NumberFormat
    @Size(min = 3, max = 20)
    private String brTelefona;
    @Size(min = 3, max = 50)
    private String adresa;
    @Size(min = 3, max = 20)
    private String mesto;
    @Size(min = 3, max = 20)
    private String operater;
    private String rola;

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
     * @return the korisniickoIme
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * @param korisniickoIme the korisniickoIme to set
     */
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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
     * @return the brTelefona
     */
    public String getBrTelefona() {
        return brTelefona;
    }

    /**
     * @param brTelefona the brTelefona to set
     */
    public void setBrTelefona(String brTelefona) {
        this.brTelefona = brTelefona;
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

}
