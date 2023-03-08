/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author sbs
 */
public class Evaluation {
    
    private int ideva ;  
    private int nbev ; 

    public Evaluation() {
    }

    public Evaluation(int ideva, int nbev) {
        this.ideva = ideva;
        this.nbev = nbev;
    }

    public Evaluation(int nbev) {
        this.nbev = nbev;
    }
    

    public int getIdeva() {
        return ideva;
    }

    public int getNbev() {
        return nbev;
    }

    public void setIdeva(int ideva) {
        this.ideva = ideva;
    }

    public void setNbev(int nbev) {
        this.nbev = nbev;
    }

    @Override
    public String toString() {
        return "evaluation{" + "ideva=" + ideva + ", nbev=" + nbev + '}';
    }

}
