/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.esprit.entity;
import java.sql.Time;
import javafx.scene.shape.Circle;
/**
 *
 * @author pc
 */
public class Planning {
      private int idMoy ,idCir ,NbPlace	; 
           
      private Time dateD , DateA ;
    
      
      public Planning() {
    }
    public Planning(Time dateD,Time DateA) {
    this.dateD=dateD;
    this.DateA=DateA;
    }
    public Planning(int idMoy, int idCir, int NbPlace, Time dateD, Time DateA) {
        this.idMoy = idMoy;
        this.idCir = idCir;
        this.NbPlace = NbPlace;
        this.dateD = dateD;
        this.DateA = DateA;
    }
      

    public int getIdMoy() {
        return idMoy;
    }

    public int getIdCir() {
        return idCir;
    }

    public int getNbPlace() {
        return NbPlace;
    }

    public Time getDateD() {
        return dateD;
    }

    public Time getDateA() {
        return DateA;
    }

    public void setIdMoy(int idMoy) {
        this.idMoy = idMoy;
    }

    public void setIdCir(int idCir) {
        this.idCir = idCir;
    }

    public void setNbPlace(int NbPlace) {
        this.NbPlace = NbPlace;
    }

    public void setDateD(Time dateD) {
        this.dateD = dateD;
    }

    public void setDateA(Time DateA) {
        this.DateA = DateA;
    }

    @Override
    public String toString() {
        return "Planning{" + "idMoy=" + idMoy + ", idCir=" + idCir + ", NbPlace=" + NbPlace + ", dateD=" + dateD + ", DateA=" + DateA + '}';
    }


}
