/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

//import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author 21626
 */
import java.sql.*;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;



public class Abonnement {

    private int idA;
    private int idU;
    private String moyTrA;
    private Date dateA;
    private LocalDate dateExpA;
    private int idtypeA;
    private boolean etudiantA;
    private int redEtA;
    private int redEvA;

    public Abonnement() {}

    public Abonnement(int idA, int idU, String moyTrA, Date dateA, LocalDate dateExpA, int idtypeA, boolean etudiantA, int redEtA, int redEvA) {
        this.idA = idA;
        this.idU = idU;
        this.moyTrA = moyTrA;
        this.dateA = dateA;
        this.dateExpA = dateExpA;
        this.idtypeA = idtypeA;
        this.etudiantA = etudiantA;
        this.redEtA = redEtA;
        this.redEvA = redEvA;
    }
        public Abonnement(String moyTrA, Date dateA, int idtypeA, boolean etudiantA, int redEtA, int redEvA) {

        this.moyTrA = moyTrA;
        this.dateA = dateA;
     
        this.idtypeA = idtypeA;
        this.etudiantA = etudiantA;
        this.redEtA = redEtA;
        this.redEvA = redEvA;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getMoyTrA() {
        return moyTrA;
    }

    public void setMoyTrA(String moyTrA) {
        this.moyTrA = moyTrA;
    }

    public Date getDateA() {
        return dateA;
    }

    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    public LocalDate getDateExpA() {
        return dateExpA;
    }

    public void setDateExpA(LocalDate dateExpA) {
        this.dateExpA = dateExpA;
    }

    public int getIdtypeA() {
        return idtypeA;
    }

    public void setIdtypeA(int idtypeA) {
        this.idtypeA = idtypeA;
    }

    public boolean isEtudiantA() {
        return etudiantA;
    }

    public void setEtudiantA(boolean etudiantA) {
        this.etudiantA = etudiantA;
    }

    public int getRedEtA() {
        return redEtA;
    }

    public void setRedEtA(int redEtA) {
        this.redEtA = redEtA;
    }

    public int getRedEvA() {
        return redEvA;
    }

    public void setRedEvA(int redEvA) {
        this.redEvA = redEvA;
    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "idA=" + idA +
                ", idU=" + idU +
                ", moyTrA='" + moyTrA + '\'' +
                ", dateA=" + dateA +
                ", dateExpA=" + dateExpA +
                ", idtypeA=" + idtypeA +
                ", etudiantA=" + etudiantA +
                ", redEtA=" + redEtA +
                ", redEvA=" + redEvA +
                '}';
    }
    

    
}

   