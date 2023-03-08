/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import tn.esprit.entity.Circuit;

/**
 *
 * @author ASUS
 */
public class Station {
    private int idStation;
    private String nomS , adresse;
    private  Circuit cir ;
    private String ciir;

    
     public Station() {
    }

    public Station(int idStation, String nomS, String adresse, Circuit cir) {
        this.idStation = idStation;
        this.nomS = nomS;
        this.adresse = adresse;
        this.cir = cir;
    }

    public Station(int idStation, String nomS, String adresse, String c) {
        this.idStation = idStation;
        this.nomS = nomS;
        this.adresse = adresse;
        this.ciir=c;
    }

    public Station(int idStation, String nomS, String adresse) {
        this.idStation = idStation;
        this.nomS = nomS;
        this.adresse = adresse;
    }
    
    

    

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public String getNomS() {
        return nomS;
    }

    public void setNomS(String nomS) {
        this.nomS = nomS;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Circuit getCir() {
        return cir;
    }

    public void setCir(String cir) {
        this.cir.setNomC(cir);
    }
    
    public Circuit getCir1() {
        return cir;
    }
    
    public void setCir1(int cir) {
        this.cir.setIdCircuit(cir);
    }

    

    @Override
    public String toString() {
        return "Station{" + "idStation=" + idStation + ", nomS=" + nomS + ", adresse=" + adresse + ", Circuit=" + cir + '}';
    }

    public void setCircuit(Circuit c) {
    this.cir = c;
    }
     
    
    

   


   
    
    
}
