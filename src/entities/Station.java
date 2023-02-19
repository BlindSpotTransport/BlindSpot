/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class Station {
    private int idStation;
    private String nomS , adresse;

    
     public Station() {
    }
     
    public Station(int idStation, String nomS, String adresse) {
        this.idStation = idStation;
        this.nomS = nomS;
        this.adresse = adresse;
    }

    public int getIdStation() {
        return idStation;
    }

    public String getNomS() {
        return nomS;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public void setNomS(String nomS) {
        this.nomS = nomS;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Station{" + "idStation=" + idStation + ", nomS=" + nomS + ", adresse=" + adresse + '}';
    }

   
    
    
}
