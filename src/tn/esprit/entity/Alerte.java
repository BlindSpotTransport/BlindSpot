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
public class Alerte  extends Evenement {
    
    private int id_alerte_eve;
    private String type_alerte_eve;

//    public Alerte(int id_alerte_eve,String type_alerte_eve,String titre_eve,String desc_eve, Date date_deb_eve, Date date_fin_eve) {
//        
//        this.id_alerte_eve=id_alerte_eve;
//        this.type_alerte_eve = type_alerte_eve;
//        this.titre_eve=titre_eve;
//        this.desc_eve=desc_eve;
//        this.date_deb_eve=date_deb_eve;
//        this.date_fin_eve=date_fin_eve;  
//    }

   public Alerte(){}
    
    
    public Alerte(int id_alerte_eve, String type_alerte_eve, String titre_eve, String desc_eve, Date date_deb_eve, Date date_fin_eve) {
        super(titre_eve, desc_eve, date_deb_eve, date_fin_eve);
        this.id_alerte_eve=id_alerte_eve;
        this.type_alerte_eve = type_alerte_eve;
    }

  

    public Alerte(int id_alerte_eve, String type_alerte_eve, int id_eve, String titre_eve, String desc_eve, Date date_deb_eve, Date date_fin_eve) {
        super(id_eve, titre_eve, desc_eve, date_deb_eve, date_fin_eve);
        this.id_alerte_eve = id_alerte_eve;
        this.type_alerte_eve = type_alerte_eve;
    }


    public int getId_alerte_eve() {
        return id_alerte_eve;
    }

    public void setId_alerte_eve(int id_alerte_eve) {
        this.id_alerte_eve = id_alerte_eve;
    }

    public String getType_alerte_eve() {
        return type_alerte_eve;
    }

    public void setType_alerte_eve(String type_alerte_eve) {
        this.type_alerte_eve = type_alerte_eve;
    }

    @Override
    public String toString() {
        
        return "Alerte{" +super.toString()+ "id_alerte_eve=" + id_alerte_eve + ", type_alerte_eve=" + type_alerte_eve + '}';
    }

   

   
    
    
    
    
    
    
    
    
}
