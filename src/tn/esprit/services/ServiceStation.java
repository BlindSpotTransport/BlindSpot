/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Circuit;
import tn.esprit.entity.Station;
import tn.esprit.gui.AjouterStationController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class ServiceStation implements InterfaceStation {
    
    Connection cnx;
String cs;
    public ServiceStation() {
        cnx = MaConnection.getInstance().getCnx();
    }

     @Override
    public void ajouterStation(Station s) {
               
           

                try {

                    String req = "insert into station(nomS,adresse,idCircuit)"
                    + "values (?,?,?)";
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setString(1, s.getNomS());
                    ps.setString(2, s.getAdresse());
                    


////    Circuit c = new Circuit();

                    ps.setInt(3, s.getCir().getIdCircuit());
                    
                    ps.executeUpdate();
                    Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("Station ajoutée avec succée !");
           alert.showAndWait();
                   
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
             Alert alert = new Alert (Alert.AlertType.ERROR);  
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Circuit deja existe !");
           alert.showAndWait();
                }
            }
        

    

    

    @Override
    public List getAll() {
         List<Station> stations = new ArrayList<>();
        try {
            String sql = "select idStation , nomS , adresse , c.nomC as nom "
                    + "from circuit as c , station as s where c.idCircuit = s.idCircuit;";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
//                Circuit c = new Circuit(rs.getInt(1),
//                rs.getString("departC"), rs.getString("arriveeC"));
//                circuits.add(c);
                int id = rs.getInt("idStation");
                String nomS = rs.getString("nomS");
                String adresse = rs.getString("adresse");
                String nom = rs.getString("nom");
                Circuit c = new Circuit();
                c.setNomC(nom);
                
                /*Station s = new Station(rs.getInt("idStation"), rs.getString("nomS"), 
                        rs.getString("adresse"), rs.getString("circuit"));*/
                Station s = new Station(id, nomS, adresse, c);
               stations.add(s);
                
                
                
        /*   String req1 = "select departC from circuit where departC = ?";
           
                Statement st1 = cnx.createStatement();
                ResultSet rs1 = st1.executeQuery(req1);
                //rs.getInt(1)
                 while (rs1.next()) {
                     Circuit c = new Circuit(rs1.getString(2));
                Station s = new Station(rs.getInt(1),
                        rs.getString("nomS"), rs.getString("adresse"), c);
                stations.add(s);
                 }*/
               
            }
           System.out.println(stations);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return stations;
    }

    @Override
    public Station findByNom(String nomS) {
       Station s= null;
        try {
            String req = "select * from station";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

               
                
                  String req1 = "select nomC from circuit where nomC = ?";
                Statement st1 = cnx.createStatement();
                
                ResultSet rs1 = st.executeQuery(req);
                
                while(rs1.next()){
                    
                    Circuit c = new Circuit(rs1.getString("nomC"));

                 s = new Station(rs.getInt(1),
                        rs.getString("nomS"), rs.getString("adresse"), c);
               // System.out.println(s);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return s;
    }
    

    @Override
    public void supprimerStation(Station s) {
        
         String sql = "delete from station where idStation=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, s.getIdStation());
            ste.executeUpdate();
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("Station a ete supprimer avec succée !");
           alert.showAndWait();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierStation(Station s, int id) {
       String sql = "update station set nomS=?, adresse=?, idCircuit=? where idStation=?";
        try {
            Circuit c1 = new Circuit();
          //  Station s1 = new Station();
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, s.getNomS());
            ste.setString(2, s.getAdresse());
            ste.setInt(3, s.getCir().getIdCircuit());
            ste.setInt(4,id);
            
          
            ste.executeUpdate();
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("Station a ete modifiée avec succes !");
           alert.showAndWait();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public int getID(){
        ObservableList<Integer> ID = FXCollections.observableArrayList();
        
      //  AjouterStationController A1 = new AjouterStationController(cs);
       System.out.println(cs);
       /*String sql = "select idCircuit from circuit where '"+cs+"' = departC";
        try { 
 
           Statement st = cnx.createStatement();
            ResultSet r = st.executeQuery(sql);
                        //  System.out.println(rs);

           while (r.next()) {           
                int id = r.getInt("idCircuit");
               // Circuit c = new Circuit();
               Integer ids = new Integer(id);
       ID.add(ids);
       
           }

        }catch (SQLException ex) {
                Logger.getLogger(AjouterStationController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
     */
       return ID.get(0);
    }

//    @Override
//    public Station findBy(String nomS) {
//        List<Station> stations = new ArrayList<>(); 
//        
//   try {
//   String sql ="select * from `station` where nomS = ? ";
//   PreparedStatement ste = cnx.prepareStatement(sql);
//   ste.setString(1, nomS);
//   
//   ResultSet rs = ste.executeQuery();
//   while (rs.next())  {
//       Circuit c = new Circuit(rs.getString("nomC"));
//	  Station s = new Station(rs.getInt(1),
//                        rs.getString("nomS"), rs.getString("adresse")));
//          stations.add(s);
//  }
// } catch (SQLException ex) {
//System.out.println(ex.getMessage());
//        }
//        return circuits;
//    }
//    

    @Override
    public List<Station> findByNomS(String nomS) {
        List<Station> stationss = new ArrayList<>(); 
        
   try {
   String sql ="select * from `station` where nomS = ?";
   PreparedStatement ste = cnx.prepareStatement(sql);
   ste.setString(1, nomS);
   
   ResultSet rs = ste.executeQuery();
   while (rs.next())  {
        Circuit c = new Circuit();
             String req1 = "select nomC from `circuit` where nomC = ?";
                PreparedStatement st1 = cnx.prepareStatement(req1);
                 st1.setString(1, c.getNomC());
                ResultSet rs1 = st1.executeQuery(req1);
                
                while(rs1.next()){
                    
                    Circuit c1 = new Circuit(rs.getString("nomC"));
        
                    
	  Station s = new Station(rs.getInt(1),
                        rs.getString("nomS"), rs.getString("adresse"), c1);
          stationss.add(s);
  }
   }
 } catch (SQLException ex) {
System.out.println(ex.getMessage());
        }
        return stationss;
    }
     
    }

    


