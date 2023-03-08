/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.time.LocalDate;

/**
 *
 * @author sbs
 */
public class RepReclamation {
  private int idrep ; 
  private String NomAg,reponse ;
    private LocalDate daterep ;
  private Reclamation reclamation ;

    public RepReclamation(int idrep, LocalDate daterep, String NomAg, String reponse, Reclamation reclamation) {
        this.idrep = idrep;
        this.daterep = daterep;
        this.NomAg = NomAg;
        this.reponse = reponse;
        this.reclamation = reclamation;
    }

    public RepReclamation(LocalDate daterep, String NomAg, String reponse, Reclamation reclamation) {
        this.daterep = daterep;
        this.NomAg = NomAg;
        this.reponse = reponse;
        this.reclamation = reclamation;
    }
  

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }
  

    public RepReclamation() {
    }

    public RepReclamation(LocalDate daterep, String NomAg, String reponse) {
        this.daterep = daterep;
        this.NomAg = NomAg;
        this.reponse = reponse;
    }

    public RepReclamation(int idrep, LocalDate daterep, String NomAg, String reponse) {
        this.idrep = idrep;
        this.daterep = daterep;
        this.NomAg = NomAg;
        this.reponse = reponse;
    }

    public RepReclamation(String NomAg, String reponse) {
        this.NomAg = NomAg;
        this.reponse = reponse;
    }

    public int getIdrep() {
        return idrep;
    }

    public void setIdrep(int idrep) {
        this.idrep = idrep;
    }

    public LocalDate getDaterep() {
        return daterep;
    }

    public void setDaterep(LocalDate daterep) {
        this.daterep = daterep;
    }

    public String getNomAg() {
        return NomAg;
    }

    public void setNomAg(String NomAg) {
        this.NomAg = NomAg;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "RepReclamation{" + "idrep=" + idrep + ", NomAg=" + NomAg + ", reponse=" + reponse + ", daterep=" + daterep + ", reclamation=" + reclamation + '}';
    }

   
    
  
    }
