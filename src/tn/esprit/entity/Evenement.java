/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class Evenement {
     int id_eve;
     String titre_eve,desc_eve;
     Date date_deb_eve ,date_fin_eve;

    public Evenement() {
        
    }
public Evenement(String titre_eve, String desc_eve, Date date_deb_eve, Date date_fin_eve) {
       
        this.titre_eve = titre_eve;
        this.desc_eve = desc_eve;
        this.date_deb_eve = date_deb_eve;
        this.date_fin_eve = date_fin_eve;
    }
    
    public Evenement(int id_eve, String titre_eve, String desc_eve, Date date_deb_eve, Date date_fin_eve) {
        this.id_eve = id_eve;
        this.titre_eve = titre_eve;
        this.desc_eve = desc_eve;
        this.date_deb_eve = date_deb_eve;
        this.date_fin_eve = date_fin_eve;
    }

    public int getId_eve() {
        return id_eve;
    }

    public void setId_eve(int id_eve) {
        this.id_eve = id_eve;
    }

    public String getTitre_eve() {
        return titre_eve;
    }

    public void setTitre_eve(String titre_eve) {
        this.titre_eve = titre_eve;
    }

    public String getDesc_eve() {
        return desc_eve;
    }

    public void setDesc_eve(String desc_eve) {
        this.desc_eve = desc_eve;
    }

    public Date getDate_deb_eve() {
        return date_deb_eve;
    }

    public void setDate_deb_eve(Date date_deb_eve) {
        this.date_deb_eve = date_deb_eve;
    }

    public Date getDate_fin_eve() {
        return date_fin_eve;
    }

    public void setDate_fin_eve(Date date_fin_eve) {
        this.date_fin_eve = date_fin_eve;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_eve=" + id_eve + ", titre_eve=" + titre_eve + ", desc_eve=" + desc_eve + ", date_deb_eve=" + date_deb_eve + ", date_fin_eve=" + date_fin_eve + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
