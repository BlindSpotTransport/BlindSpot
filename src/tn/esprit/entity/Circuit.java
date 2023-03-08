/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author ASUS
 */
public class Circuit {
    private int idCircuit;
    private String nomC;
    private String departC, arriveeC;

    public Circuit() {
    }

    public Circuit(int idCircuit, String nomC, String departC, String arriveeC) {
        this.idCircuit = idCircuit;
        this.nomC = nomC;
        this.departC = departC;
        this.arriveeC = arriveeC;
    }
    public Circuit(int idCircuit, String departC, String arriveeC) {
        this.idCircuit = idCircuit;
        this.departC = departC;
        this.arriveeC = arriveeC;
    }
    public Circuit(int idCircuit, String nomC) {
        this.idCircuit = idCircuit;
        this.nomC = nomC;
    }

    

    public Circuit(String departC) {
        this.departC = departC;
    }
    

    public int getIdCircuit() {
        return idCircuit;
    }

    public String getDepartC() {
        return departC;
    }

    public String getArriveeC() {
        return arriveeC;
    }

    public void setIdCircuit(int idCircuit) {
        this.idCircuit = idCircuit;
    }

    public void setDepartC(String departC) {
        this.departC = departC;
    }

    public void setArriveeC(String arriveeC) {
        this.arriveeC = arriveeC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    @Override
    public String toString() {
        return "Circuit{" + "idCircuit=" + idCircuit + ", nomC=" + nomC + ", departC=" + departC + ", arriveeC=" + arriveeC + '}';
    }
    
    

    

 
    
    
    
}
