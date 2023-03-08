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
import javafx.scene.control.Alert;
import tn.esprit.entity.Circuit;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.entity.Planning;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author pc
 */
public class PlanningService implements NewInterface<Planning> {

    Connection cnx;

    public PlanningService() {
        cnx = MaConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(Planning p) {
        try {
            String sql = "INSERT INTO `planning`(`idMoy`,`idCir`, `dateD`, `DateA`, `NbPlace` , `prix` ) VALUES (?,?,?,?,?,?) ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getIdMoy());
            ste.setInt(2, p.getIdCir());
            ste.setTime(3, p.getDateD());
            ste.setTime(4, p.getDateA());
            ste.setInt(5, p.getNbPlace());
            ste.setInt(6, p.getPrix());

            ste.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("votre planning a éte ajouté avec succès !");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert (Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("ce planning existe deja  !!");
            alert.showAndWait();
        }
    }

    @Override
    public List<Planning> getAll() {
        return null;

//        List<Planning> Plan = new ArrayList<>();
//        try{
//            String sql="select * from planning ";
//            Statement ste = cnx.createStatement();
//            ResultSet s = ste.executeQuery(sql);
//            while (s.next()) 
//            {
//                Planning p = new Planning();
//                Moyenstransport moyy = new Moyenstransport();
//                Circuit cirr = new Circuit();
//                moyy.setIdMoy(s.getInt("idMoy"));
//                moyy.setCapacite(p.getMoy().getCapacite());
//                cirr.setIdCircuit(s.getInt("idCir"));
//                p.setMoy(moyy);
//                p.setCir(cirr);
//                p.setIdMoy(moyy.getIdMoy());
//                p.setIdCir(cirr.getIdCircuit());
//                p.setDateA(s.getTime("dateD"));
//                p.setDateA(s.getTime("DateA"));
//                p.setNbPlace(moyy.getCapacite());
//                Plan.add(p);
//            }
//            
//            } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//    }
//        return Plan; 
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(" planning a éte supprimer avec succès !");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // fiha khedma okhra khater modification khater fiha 3 cle etrangere 
    public void modifierPlannig(int OLDidM, int OLDidC, Planning p) {
        String sql = "update planning set  idMoy=? , idCir=? , dateD=? , DateA=? , prix=? where idMoy=? and idCir=? and DateD=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setInt(1, p.getMoy().getIdMoy());
            ste.setInt(2, p.getCir().getIdCircuit());
            ste.setTime(3, p.getDateD());
            ste.setTime(4, p.getDateA());
            ste.setInt(5, p.getPrix());
            ste.setInt(6, OLDidM);
            ste.setInt(7, OLDidC);
            ste.setTime(8, p.getOldDate());

            ste.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("planning a éte Modifier avec succées !!");
            alert.showAndWait();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void changerHourTo12(Time t) {
        if (t.getHours() == 00) {
            t.setHours(12);
        } else if (t.getHours() == 12) {
            t.setHours(00);
        }

    }

    public void changerHourTo12A(Time t) {
        if (t.getHours() == 00) {
            t.setHours(12);
        }
    }

    public int GetNombreDePlace(int idCir, int idMoy, Time DateDep) {
        int NbPlace = 0;
        try {
            String GetNombreplace = "select NbPlace from planning where idMoy='" + idMoy + "' and idCir='" + idCir + "' and dateD ='" + DateDep + "' ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(GetNombreplace);

            while (s.next()) {
                NbPlace = s.getInt("NbPlace");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return NbPlace;
    }
    public void SuppNbPlace(int idCir, int idMoy, Time DateDep){
        try {
            String UpdateNombreplace = "update planning set  NbPlace = NbPlace -1 where idMoy= '"+idMoy+"' and idCir= '"+idCir+"' and DateD= '"+DateDep+"' ";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(UpdateNombreplace);
            System.out.println("Nembre de place a ete modifier avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ResetNbPlace(int idCir, int idMoy, Time DateDep, String TYPEM,String NUM ){
        try {
            String SQLgetCapacite = "select capacite from moyenstransport where type='" + TYPEM + "' AND numMoy='" + NUM + "' ";
            Statement ste = cnx.createStatement();

            int CAP = 0;
            ResultSet k = ste.executeQuery(SQLgetCapacite);
            while (k.next()) {
                CAP = k.getInt("capacite");
            }
            int capacitee = CAP;
            //On applique modification (reset)
            String UpdateNombreplace = "update planning set  NbPlace = '"+capacitee+"' where idMoy= '"+idMoy+"' and idCir= '"+idCir+"' and DateD= '"+DateDep+"' ";
            ste.executeUpdate(UpdateNombreplace);
            System.out.println("Reset Nombre de place effectué avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public List<String> getHeurD() {
 List<String> list = new ArrayList<>();
        try {
            String req = "SELECT distinct DateD FROM `planning`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String c = new String (rs.getString("DateD"));
                list.add(c);
               // System.out.println(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
    return list;
    }
     public List<String> getHeurA() {
 List<String> list = new ArrayList<>();
        try {
            String req = "SELECT distinct DateA FROM `planning`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String c = new String (rs.getString("DateA"));
                list.add(c);
               // System.out.println(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
    return list;
    }
    
}
