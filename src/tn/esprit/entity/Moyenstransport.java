/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author pc
 */
public class Moyenstransport {
    public int idMoy ,capacite; 
    public String type , matricule, numMoy ;
   
    public Moyenstransport() {
    }
    public Moyenstransport(int idMoy,String type,int capacite, String Num) {
        this.idMoy = idMoy;
        this.type=type;
        this.capacite=capacite;
        this.numMoy=Num;
    }
    public Moyenstransport(int idMoy, int capacite, String type, String matricule, String numMoy) {
        this.idMoy = idMoy;
        this.capacite = capacite;
        this.type = type;
        this.matricule = matricule;
        this.numMoy = numMoy;
    }
 
    public Moyenstransport(int capacite, String type, String matricule, String numMoy) {
        this.capacite = capacite;
        this.type = type;
        this.matricule = matricule;
        this.numMoy = numMoy;
    }

    public int getIdMoy() {
        return idMoy;
    }

    public void setIdMoy(int idMoy) {
        this.idMoy = idMoy;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNumMoy() {
        return numMoy;
    }

    public void setNumMoy(String numMoy) {
        this.numMoy = numMoy;
    }

    @Override
    public String toString() {
        return "Moyenstransport{" + "idMoy=" + idMoy + ", capacite=" + capacite + ", type=" + type + ", matricule=" + matricule + ", numMoy=" + numMoy + '}';
    }

  
    
    
    
    
    
}
