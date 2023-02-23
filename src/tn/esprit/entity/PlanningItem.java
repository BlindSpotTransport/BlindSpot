/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.sql.Time;

/**
 *
 * @author pc
 */
public class PlanningItem {
    
    private String departCircuit;
    private String arriveeCircuit;
    private String typeMoyen;
    private int capacitee;
    private Time dateDepart;
    private Time dateArriver;
    private String NumeroM ;

    public PlanningItem(String departCircuit){
        this.departCircuit = departCircuit;
    }

    public String getNumeroM() {
        return NumeroM;
    }
    public PlanningItem(String departCircuit, String arriveeCircuit, String typeMoyen, int capacitee, Time dateDepart, Time dateArriver, String NumeroM) {
        this.departCircuit = departCircuit;
        this.arriveeCircuit = arriveeCircuit;
        this.typeMoyen = typeMoyen;
        this.capacitee = capacitee;
        this.dateDepart = dateDepart;
        this.dateArriver = dateArriver;
        this.NumeroM=NumeroM;
    }

    public String getDepartCircuit() {
        return departCircuit;
    }

    public void setDepartCircuit(String departCircuit) {
        this.departCircuit = departCircuit;
    }

    public void setArriveeCircuit(String arriveeCircuit) {
        this.arriveeCircuit = arriveeCircuit;
    }

    public void setTypeMoyen(String typeMoyen) {
        this.typeMoyen = typeMoyen;
    }

    public void setCapacitee(int capacitee) {
        this.capacitee = capacitee;
    }

    public void setDateDepart(Time dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateArriver(Time dateArriver) {
        this.dateArriver = dateArriver;
    }

    public void setNumeroM(String NumeroM) {
        this.NumeroM = NumeroM;
    }

    public String getArriveeCircuit() {
        return arriveeCircuit;
    }

    public String getTypeMoyen() {
        return typeMoyen;
    }

    public int getCapacitee() {
        return capacitee;
    }

    public Time getDateDepart() {
        return dateDepart;
    }

    public Time getDateArriver() {
        return dateArriver;
    }

    @Override
    public String toString() {
        return "PlanningItem{" + "departCircuit=" + departCircuit + ", arriveeCircuit=" + arriveeCircuit + ", typeMoyen=" + typeMoyen + ", capacitee=" + capacitee + ", dateDepart=" + dateDepart + ", dateArriver=" + dateArriver + ", NumeroM=" + NumeroM + '}';
    }


}

