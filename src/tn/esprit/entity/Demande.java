/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.io.File;

/**
 *
 * @author ASUS
 */
public class Demande {
    private int id;
    private String nomC;
    private String moyen;
    private String HD;
    private String HA;
    private String permis;
    private String EmailC;

    public Demande() {
    }

    public Demande(int id, String nomC, String moyen, String HD, String HA, String permis, String EmailC) {
        this.id = id;
        this.nomC = nomC;
        this.moyen = moyen;
        this.HD = HD;
        this.HA = HA;
        this.permis = permis;
        this.EmailC = EmailC;
    }
    
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getMoyen() {
        return moyen;
    }

    public void setMoyen(String moyen) {
        this.moyen = moyen;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getHA() {
        return HA;
    }

    public void setHA(String HA) {
        this.HA = HA;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public String getEmailC() {
        return EmailC;
    }

    public void setEmailC(String EmailC) {
        this.EmailC = EmailC;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", nomC=" + nomC + ", moyen=" + moyen + ", HD=" + HD + ", HA=" + HA + ", permis=" + permis + ", EmailC=" + EmailC + '}';
    }
    

    
    
    
    
    
}
