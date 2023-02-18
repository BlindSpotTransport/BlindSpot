/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author sbs
 */
public class Commentaire {
    private int idco ; 
    private int parentid ; 
    private String contenu ; 
    private Date datecom ;   
    private boolean reaction ; 

    public Commentaire() {
    }

    public Commentaire(int idco, int parentid, String contenu, Date datecom, boolean reaction) {
        this.idco = idco;
        this.parentid = parentid;
        this.contenu = contenu;
        this.datecom = datecom;
        this.reaction = reaction;
    }

    public Commentaire(int idco) {
        this.idco = idco;
    }

    public Commentaire(int parentid, String contenu, Date datecom, boolean reaction) {
        this.parentid = parentid;
        this.contenu = contenu;
        this.datecom = datecom;
        this.reaction = reaction;
    }

    public void setIdco(int idco) {
        this.idco = idco;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDatecom(Date datecom) {
        this.datecom = datecom;
    }

    public void setReaction(boolean reaction) {
        this.reaction = reaction;
    }

    public int getIdco() {
        return idco;
    }

    public int getParentid() {
        return parentid;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDatecom() {
        return datecom;
    }

    public boolean isReaction() {
        return reaction;
        
        
    }

    @Override
    public String toString() {
        return "Commentaire{"  + "parentid=" + parentid + ", contenu=" + contenu + ", datecom=" + datecom + ", reaction=" + reaction + '}';
    }

   

    
    
  
    
}
