/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.entity.Planning;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author pc
 */
public class PlanningService implements NewInterface<Planning>{
        Connection cnx;

    public PlanningService() {
         cnx = MaConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(Planning p) {
       try{
        String sql = "INSERT INTO `planning`(`idMoy`,`idCir`, `dateD`, `DateA`, `NbPlace`) VALUES (?,?,?,?,?) ";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, p.getIdMoy());
        ste.setInt(2, p.getIdCir());
        ste.setTime(3, p.getDateD());
        ste.setTime(4, p.getDateA());
        ste.setInt(5, p.getNbPlace());
        System.out.println("planning ajoutée avec succées !");
        ste.executeUpdate();
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<Planning> getAll() {
        List<Planning> Plan = new ArrayList<>();
        try{
            String sql="select * from planning ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) 
            {
                Planning p = new Planning(s.getInt(1),s.getInt(2),s.getInt(5)
                        ,s.getTime(3),s.getTime(4));
                Plan.add(p);
            }
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        return Plan;
    }
    
     /*
     public List<Planning> getAllPlanning() {
        List<Planning> Plan = new ArrayList<>();        
        try{
            String sql="select C.departC AS DepartCircuit , C.arriveeC AS ArriverCircuit , M.type AS TypeMoyen, M.capacite AS capacitee, P.dateD AS dateDepart , P.DateA AS dateArriver  from circuit AS C "
                    + ", moyenstransport AS M , planning AS P where P.idMoy= M.idMoy AND P.idCir=C.idCircuit" ;
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) 
            {
                Planning pln = new Planning(s.getString("DepartCircuit"), s.getString("ArriverCircuit"), s.getString("TypeMoyen")
                        , s.getInt("capacitee"), s.getTime("dateDepart"), s.getTime("dateArriver"));
              /* String DepartCircuit = s.getString(1);
  /*              String ArriverCircuit = s.getString(2);
                String TypeMoyen=s.getString(3);
                int capacitee=s.getInt(4);
                Time dateDepart=s.getTime(5);
                Time dateArriver=s.getTime(6);
                Plan.add(DepartCircuit);
                Plan.add(ArriverCircuit);
                Plan.add(TypeMoyen);
                Plan.add(capacitee);
                Plan.add(dateDepart);
                Plan.add(dateArriver);
                */
              /*
                Plan.add(pln);
            
}
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        return Plan;
    }*/

    @Override
    public List<Planning> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Planning p) {
    String sql = "delete from planning where idMoy=? and idCir=? and dateD=? ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getIdMoy());
            ste.setInt(2, p.getIdCir());
            ste.setTime(3, p.getDateD());
            ste.executeUpdate();
            System.out.println("Planning a éte supprimer avec succées !!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // fiha khedma okhra khater modification khater fiha 3 cle etrangere 
     public void modifierPlannig(Time DateA, int NbPlace,Planning p) {
        String sql = "update planning set  DateA=? , NbPlace=? where idMoy=? and idCir=? and DateD=?" ;
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);     
              if (DateA != p.getDateA()){
            ste.setTime(1,DateA);
            } else{
                ste.setTime(1, p.getDateA());
            }
              if (NbPlace != p.getNbPlace()){
            ste.setInt(2,NbPlace);
            } else{
                ste.setInt(2, p.getNbPlace());
            }
           
            ste.setInt(3, p.getIdMoy());
            ste.setInt(4, p.getIdCir());
            ste.setTime(5, p.getDateD());
            ste.executeUpdate();
            System.out.println("moyenne de transport a éte Modifier avec succées !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
     public void changerHourTo12(Time t){
        if (t.getHours()== 00){
            t.setHours(12);
     }
        else if(t.getHours()==12)
        {
        t.setHours(00);
        }
     
     }
          public void changerHourTo12A(Time t){
        if (t.getHours()== 00){
            t.setHours(12);
     }
        
     
     }
    }


