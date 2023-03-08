/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author 21626
 */
public class Typeabn {


    private int idtypeA;
    private String dureeA;
    private int prixA;

    public Typeabn() {}

    public Typeabn(int idtypeA, String dureeA, int prixA) {
        this.idtypeA = idtypeA;
        this.dureeA = dureeA;
        this.prixA = prixA;
    }

    public int getIdtypeA() {
        return idtypeA;
    }

    public void setIdtypeA(int idtypeA) {
        this.idtypeA = idtypeA;
    }

    public String getDureeA() {
        return dureeA;
    }

    public void setDureeA(String dureeA) {
        this.dureeA = dureeA;
    }

    public int getPrixA() {
        return prixA;
    }

    public void setPrixA(int prixA) {
        this.prixA = prixA;
    }

    @Override
    public String toString() {
        return "Typeabn{" +
                "idtypeA=" + idtypeA +
                ", dureeA='" + dureeA + '\'' +
                ", prixA=" + prixA +
                '}';
    }
}

    

