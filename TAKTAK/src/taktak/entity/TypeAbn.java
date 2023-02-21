/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entity;

import java.util.Date;

/**
 *
 * @author 21626
 */
public class TypeAbn {
    private int idA,idU,prixA;
    private Date dateExpA;
  //  private enum dureeA {Mensuel,Semestriel,Annuel};
    private String dureeA;

    public TypeAbn(){}
    public TypeAbn(Abonnement a,String dureeA, int prixA, Date dateExpA) {
        this.idA = a.getIdA();
        this.idU = a.getIdU();
        this.dureeA = dureeA;
        this.prixA = prixA;
        this.dateExpA = dateExpA;
        
    }
    
    public TypeAbn(int idA,int idU ,String dureeA, int prixA, Date dateExpA) {
        this.idA =idA;
        this.idU =idU;
        this.dureeA = dureeA;
        this.prixA = prixA;
        this.dateExpA = dateExpA;
        
    }
    
    
    public TypeAbn(String dureeA, int prixA, Date dateExpA) {

        this.dureeA = dureeA;
        this.prixA = prixA;
        this.dateExpA = dateExpA;
        
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

    public int getPrixA() {
        return prixA;
    }

    public void setPrixA(int prixA) {
        this.prixA = prixA;
    }

    public Date getDateExpA() {
        return dateExpA;
    }

    public void setDateExpA(Date dateExpA) {
        this.dateExpA = dateExpA;
    }

    public String getDureeA() {
        return dureeA;
    }

    public void setDureeA(String dureeA) {
        this.dureeA = dureeA;
    }

    @Override
    public String toString() {
        return "TypeAbn{" + "idA=" + idA + ", idU=" + idU + ", prixA=" + prixA + ", dateExpA=" + dateExpA + '}';
    }
    
}
