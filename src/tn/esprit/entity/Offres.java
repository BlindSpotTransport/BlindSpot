/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.sql.Date;
import tn.esprit.entity.User;

/**
 *
 * @author hp
 */
public class Offres  extends Evenement {
    private int id_offre_eve, duree_offre;
    private float budget_offre;
    private String img_src_offre ;
    private String statut_offre;
    private User user ; 

     public Offres( int duree_offre, float budget_offre, String img_src_offre, String statut_offre, String titre_eve, String desc_eve, Date date_deb_eve, Date date_fin_eve) {
        super(titre_eve, desc_eve, date_deb_eve, date_fin_eve);
        
        
        this.duree_offre = duree_offre;
        this.budget_offre = budget_offre;
        this.img_src_offre = img_src_offre;
        this.statut_offre = statut_offre;
    }

    public Offres(int id_eve,int id_offre_eve, User user, int duree_offre, float budget_offre, String img_src_offre, String statut_offre, String titre_eve, String desc_eve, Date date_deb_eve, Date date_fin_eve) {
        super(id_eve,titre_eve, desc_eve, date_deb_eve, date_fin_eve);
        this.id_offre_eve = id_offre_eve;
        this.user=user;
        this.duree_offre = duree_offre;
        this.budget_offre = budget_offre;
        this.img_src_offre = img_src_offre;
        this.statut_offre = statut_offre;
    }

    public Offres(int id_offre_eve) {
        this.id_offre_eve = id_offre_eve;
    }

    public Offres(int id_offre_eve, String titre_eve, String desc_eve, Date date_deb_eve, Date date_fin_eve) {
        super(titre_eve, desc_eve, date_deb_eve, date_fin_eve);
        this.id_offre_eve = id_offre_eve;
        
    }

    

    public int getId_offre_eve() {
        return id_offre_eve;
    }

    public void setId_offre_eve(int id_offre_eve) {
        this.id_offre_eve = id_offre_eve;
    }

    public int getIdU() {
        return user.getIdU();
    }

    public User setUser() {
        return user;
    }

    public int getDuree_offre() {
        return duree_offre;
    }

    public void setDuree_offre(int duree_offre) {
        this.duree_offre = duree_offre;
    }

    public float getBudget_offre() {
        return budget_offre;
    }

    public void setBudget_offre(float budget_offre) {
        this.budget_offre = budget_offre;
    }

    public String getImg_src_offre() {
        return img_src_offre;
    }

    public void setImg_src_offre(String img_src_offre) {
        this.img_src_offre = img_src_offre;
    }

    public String getStatut_offre() {
        return statut_offre;
    }

    public void setStatut_offre(String statut_offre) {
        this.statut_offre = statut_offre;
    }

    @Override
    public String toString() {
        return "Offres{" + "id_offre_eve=" + id_offre_eve + ", duree_offre=" + duree_offre + ", budget_offre=" + budget_offre + ", img_src_offre=" + img_src_offre + ", statut_offre=" + statut_offre + ", user=" + user + '}';
    }


    }
    
    
    

  

    

   
    
    
            
            
    
    
    

