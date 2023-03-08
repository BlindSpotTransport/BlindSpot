/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.esprit.entity;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Circle;
/**
 *
 * @author pc
 */
public class Planning {
      private int idMoy ,idCir ,NbPlace	; 
      private Moyenstransport moy ;
      private Circuit cir; 
      private Time dateD , DateA ;
      private int prix;

      
      
      
      private Time OldDate;
    public void setOldDate(Time OldDate) {
        this.OldDate = OldDate;
    }

    public Time getOldDate() {
        return OldDate;
    }
      
    
      
      
              
              
      public Planning() {
    }
    public Planning(Time dateD,Time DateA) {
    this.dateD=dateD;
    this.DateA=DateA;
    }   
    
  /*  public Planning( , Circuit cir, int NbPlace, Time dateD, Time DateA) {
        this.moy = moy;
        this.cir = cir;
        this.NbPlace = NbPlace;
        this.dateD = dateD;
        this.DateA = DateA;
    }*/
    public Planning(Moyenstransport m, Circuit c, int NbPlace, Time dateD, Time DateA ) {
        this.moy = m ;
        this.cir = c;
        this.NbPlace = NbPlace;
        this.dateD = dateD;
        this.DateA = DateA;
    }

    public Planning(ResultSet s) {
        System.out.print(s.toString());
    }
    
    

    public Planning(String departCircuit, String arriveeCircuit, String typeMoyen, Time dateDepart, Time dateArriver, int capacitee, String NumeroM, int prix) {
     this.moy = new Moyenstransport(idMoy,typeMoyen,capacitee,NumeroM);
     this.cir = new Circuit(idCir,departCircuit, arriveeCircuit );
     this.dateD=dateDepart;
     this.DateA=dateArriver;
     this.prix=prix;
     this.NbPlace=capacitee;
    }

    public int getPrix() {
        return prix;
    }

    public void setMoy(Moyenstransport moy) {
        this.moy = moy;
    }

    public void setCir(Circuit cir) {
        this.cir = cir;
    }

    public Moyenstransport getMoy() {
        return moy;
    }

    public Circuit getCir() {
        return cir;
    }
    public int getCiruit(){
         return this.cir.getIdCircuit();
    }
    public void setCircuitID(int idc){
        this.getCir().setIdCircuit(idc);
    }
    public int getMoyenne(){
        return this.moy.getIdMoy();
    }
     
     //   Planning planning = new Planning(c1.getDepartC(),c1.getArriveeC(), m1.getType(), dateDepart, dateArriver,m1.getCapacite());        
  /*public Planning(String Dep , String Arr, String Type, Time dateD, Time DateA, int nbrPlace, String NumMoy) {
        this.idCir=set
        this.cir.setArriveeC(Arr);
        this.moy.setType(Type);
        this.dateD = dateD;
        this.DateA = DateA;
        this.moy.setCapacite(nbrPlace);
        this.moy.setNumMoy(NumMoy);
    }*/
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
    private static final Logger LOG = Logger.getLogger(Planning.class.getName());

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Planning{" + "idMoy=" + idMoy + ", idCir=" + idCir + ", NbPlace=" + NbPlace + ", moy=" + moy + ", cir=" + cir + ", dateD=" + dateD + ", DateA=" + DateA + ", prix=" + prix + '}';
    }

  
    public void setMoyenneID(int IdMoy) {
        this.getMoy().setIdMoy(IdMoy);
    }

   


}
