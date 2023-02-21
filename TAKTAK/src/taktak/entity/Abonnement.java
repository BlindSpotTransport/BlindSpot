/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entity;

//import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author 21626
 */
public class Abonnement {
    private int idA,idU,redEtA,redEvA;
    private Date dateA;
    private boolean etudiantA;
  //  private byte[] imageA;
  //  private enum moyTrA {Bus,Métro,Train};
    private String imageA,moyTrA ;

    public Abonnement(){}

    public Abonnement(int idA,int idU,String moyTrA,Date dateA,String imageA,boolean etudiantA , int redEtA, int redEvA) {
        this.idA = idA;
        this.idU = idU;
        this.moyTrA = moyTrA;
        this.dateA = dateA;
        this.imageA = imageA;
        this.etudiantA = etudiantA;
        this.redEtA = redEtA;
        this.redEvA = redEvA; 
        
    }
    public Abonnement(int idU,String moyTrA,boolean etudiantA , int redEtA, int redEvA) {
        
        this.idU = idU;
        this.moyTrA = moyTrA;
        
        this.etudiantA = etudiantA;
        this.redEtA = redEtA;
        this.redEvA = redEvA; 
        
    }




 // idA auto increment?
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

    public Date getDateA() {
        return dateA;
    }

    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    public boolean isEtudiantA() {
        return etudiantA;
    }

    public void setEtudiantA(boolean etudiantA) {
        this.etudiantA = etudiantA;
    }

 /*   public byte[] getImageA() {
        return imageA;
    }

    public void setImageA(byte[] imageA) {
        this.imageA = imageA;
    }
*/
    
    public String getImageA() {
        return imageA;
    }

    public void setImageA(String imageA) {
        this.imageA = imageA;
    }

    public String getMoyTrA() {
        return moyTrA;
    }

    public void setMoyTrA(String moyTrA) {
        this.moyTrA = moyTrA;
    }

    
    @Override
    public String toString() {
        return "Abonnement{" + "idA=" + idA + ", idU=" + idU + ", redEtA=" + redEtA + ", redEvA=" + redEvA + ", dateA=" + dateA + ", etudiantA=" + etudiantA + ", imageA=" + imageA + '}';
    }
    
}
