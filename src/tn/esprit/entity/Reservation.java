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
public class Reservation {
    private int idNum, cin , prix;
    private Time dateR, heureDep, heureArr;
    private String type ,NUM ; 

    public Reservation() {
    }

    public Reservation(int idNum, int cin, int prix, Time dateR, Time heureDep, Time heureArr, String type) {
        this.idNum = idNum;
        this.cin = cin;
        this.prix = prix;
        this.dateR = dateR;
        this.heureDep = heureDep;
        this.heureArr = heureArr;
        this.type = type;
    }

    public Reservation( int cin, int prix, Time dateR, Time heureDep, Time heureArr, String type, String NUM) {
        this.cin = cin;
        this.prix = prix;
        this.dateR = dateR;
        this.heureDep = heureDep;
        this.heureArr = heureArr;
        this.type = type;
        this.NUM=NUM;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    public String getNUM() {
        return NUM;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Time getDateR() {
        return dateR;
    }

    public void setDateR(Time dateR) {
        this.dateR = dateR;
    }

    public Time getHeureDep() {
        return heureDep;
    }

    public void setHeureDep(Time heureDep) {
        this.heureDep = heureDep;
    }

    public Time getHeureArr() {
        return heureArr;
    }

    public void setHeureArr(Time heureArr) {
        this.heureArr = heureArr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idNum=" + idNum + ", cin=" + cin + ", prix=" + prix + ", dateR=" + dateR + ", heureDep=" + heureDep + ", heureArr=" + heureArr + ", type=" + type + '}';
    }
    
    
}
