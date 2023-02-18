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
public class Reclamation {
    private int idr ; 
    private Date dater  ; 
    private String descrec ;  
    private String statusr  ; 

    public Reclamation() {
    }

    public Reclamation(int idr, Date dater , String descrec, String statusr) {
        this.idr = idr;
        this.dater  = dater ;
        this.descrec = descrec;
        this.statusr = statusr;
    }

    public Reclamation(Date dater , String descrec, String statusr) {
        this.dater  = dater ;
        this.descrec = descrec;
        this.statusr = statusr;
    }

    public Reclamation(int idr) {
        this.idr = idr;
    }

    public int getIdr() {
        return idr;
    }

    public Date getDater() {
        return dater;
    }

   

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public void setDater(Date dater) {
        this.dater = dater;
    }

    

 

    public String getDescrec() {
        return descrec;
    }

    public String getStatusr() {
        return statusr;
    }



    public void setDescrec(String descrec) {
        this.descrec = descrec;
    }

    public void setStatusr(String statusr) {
        this.statusr = statusr;
    }

    @Override
    public String toString() {
        return "reclamation{" + "idr=" + idr + ", dater =" + dater  + ", descrec=" + descrec + ", statusr=" + statusr + '}';
    }
    
}
