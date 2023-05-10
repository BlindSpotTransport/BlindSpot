/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.util.Date;
import java.time.LocalDate;
import tn.esprit.entity.User;
import tn.esprit.entity.User;

/**
 *
 * @author sbs
 */
public class Reclamation {
    
    private int idr ; 
    private String nom ; 
    private String prenom ;
    private LocalDate dater ; 
    private String descrec ; 
    private User user = new User() ; 
   
    

    public Reclamation() {
    }
 public Reclamation(int idr, String nom, String prenom, LocalDate dater, String descrec) {
        this.idr = idr;
        this.nom = nom;
        this.prenom = prenom;
        this.dater = dater;
        this.descrec = descrec;
    }

    public Reclamation(int idr, String nom, String prenom, LocalDate dater, String descrec, User user) {
        this.idr = idr;
        this.nom = nom;
        this.prenom = prenom;
        this.dater = dater;
        this.descrec = descrec;
        this.user = user;
    }

    public Reclamation(String nom, String prenom, LocalDate dater, String descrec, User user) {
        this.nom = nom;
        this.prenom = prenom;
        this.dater = dater;
        this.descrec = descrec;
        this.user = user;
    }

    public Reclamation(String nom, String prenom, LocalDate dater, String descrec) {
        this.nom = nom;
        this.prenom = prenom;
        this.dater = dater;
        this.descrec = descrec;
    }

    public Reclamation(String nom, String prenom, String descrec) {
        this.nom = nom;
        this.prenom = prenom;
        this.descrec = descrec;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public int getIdU() {
        return this.getUser().getIdU();
    }
    public void setIdU(int idU) {
       this.getUser().setIdU(idU);
}
    public String getEmailU(){
    return this.getEmailU(); 
    }
      public String getImagePU() {
        return this.getImagePU();
    }
    
    public String getNom() {
        return nom;
    }
 
  
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

   

 

    public Reclamation(int idr) {
        this.idr = idr;
    }

    public int getIdr() {
        return idr;
    }

    public LocalDate getDater() {
        return dater;
    }

   

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public void setDater(LocalDate dater) {
        this.dater = dater;
    }

    

 

    public String getDescrec() {
        return descrec;
    }

 


    public void setDescrec(String descrec) {
        this.descrec = descrec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idr=" + idr + ", nom=" + nom + ", prenom=" + prenom + ", dater=" + dater + ", descrec=" + descrec + ", user=" + user + '}';
    }

   

 

   
    
}
