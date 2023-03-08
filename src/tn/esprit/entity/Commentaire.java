/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author sbs
 */
public class Commentaire {
    private int idco ;  
    private String nom ;
    private String prenom;
     private LocalDate datecom ;
    private String contenu ; 
     
 ; 

    public Commentaire() {
    }

    public Commentaire(int idco, String nom, String prenom, LocalDate datecom, String contenu) {
        this.idco = idco;
        this.nom = nom;
        this.prenom = prenom;
        this.contenu = contenu;
        this.datecom = datecom;   }    

    public Commentaire(String nom, String prenom, LocalDate datecom,String contenu) {
        this.nom = nom;
        this.prenom = prenom;
        this.contenu = contenu;
        this.datecom = datecom;
    }

    public Commentaire(String nom, String prenom, String contenu) {
        this.nom = nom;
        this.prenom = prenom;
        this.contenu = contenu;
    }

    public int getIdco() {
        return idco;
    }

    public void setIdco(int idco) {
        this.idco = idco;
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDatecom() {
        return datecom;
    }

    public void setDatecom(LocalDate datecom) {
        this.datecom = datecom;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idco=" + idco + ", nom=" + nom + ", prenom=" + prenom + ", contenu=" + contenu + ", datecom=" + datecom + '}';
    }
    
}