/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Circuit;
//import tn.esprit.entity.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class ServiceCircuit implements InterfaceCircuit{
    
    Connection cnx;

    public ServiceCircuit() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterCircuit(Circuit c) {
          try {
            String sql = "insert into circuit(departC,arriveeC,nomC)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getDepartC());
            ste.setString(2, c.getArriveeC());
            ste.setString(3, c.getNomC());
            ste.executeUpdate();
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("Circuit added successfully");
           alert.showAndWait();
           
        } catch (SQLException ex) {
            Alert alert = new Alert (Alert.AlertType.ERROR);  
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Circuit already exist");
           alert.showAndWait();
        }
    }

    @Override
    public List getAll() {
        List<Circuit> circuits = new ArrayList<>();
        try {
            String sql = "select * from circuit";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Circuit c = new Circuit(rs.getInt(1),
                        rs.getString("nomC"), rs.getString("departC"), rs.getString("arriveeC"));
                circuits.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return circuits;
    }

    @Override
    public List findByNomC(String nomC) {
         List<Circuit> circuits = new ArrayList<>(); 
        
   try {
   String sql ="select * from `circuit` where nomC = ?";
   PreparedStatement ste = cnx.prepareStatement(sql);
   ste.setString(1, nomC);
   
   ResultSet rs = ste.executeQuery();
   while (rs.next())  {
       
	  Circuit c = new Circuit(rs.getInt(1),
                        rs.getString("departC"), rs.getString("arriveeC"), rs.getString("nomC"));
          circuits.add(c);
  }
 } catch (SQLException ex) {
System.out.println(ex.getMessage());
        }
        return circuits;
    }

    @Override
    public void supprimerCircuit(Circuit c) {
          String sql = "delete from circuit where idCircuit=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getIdCircuit());
            ste.executeUpdate();
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("Circuit deleted successfully");
           alert.showAndWait();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCircuit(String departC,String arriveeC, String nomC, Circuit c) {
         String sql = "update circuit set departC=?, arriveeC=?, nomC=? where idCircuit=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, departC);
            ste.setString(2, arriveeC);
            ste.setString(3, nomC);
            ste.setInt(4,c.getIdCircuit());
            ste.executeUpdate();
             Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("Circuit updated successfully");
           alert.showAndWait();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public List<String> getNomC() {
 List<String> list = new ArrayList<>();
        try {
            String req = "SELECT distinct nomC FROM `circuit`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String c = new String (rs.getString("nomC"));
                list.add(c);
               // System.out.println(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
    return list;
    }
}

   

