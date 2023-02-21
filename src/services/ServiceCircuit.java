/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Circuit;
import entities.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import tools.MaConnection;

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
            String sql = "insert into circuit(departC,arriveeC)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getDepartC());
            ste.setString(2, c.getArriveeC());
            ste.executeUpdate();
            System.out.println("Circuit added successfully");
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
                        rs.getString("departC"), rs.getString("arriveeC"));
                circuits.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return circuits;
    }

    @Override
    public List findBy(String departC, String arriveeC) {
         List<Circuit> circuits = new ArrayList<>(); 
        
   try {
   String sql ="select * from `circuit` where departC = ? or arriveeC = ?";
   PreparedStatement ste = cnx.prepareStatement(sql);
   ste.setString(1, departC);
   ste.setString(2, arriveeC);
   ResultSet rs = ste.executeQuery();
   while (rs.next())  {
       
	  Circuit c = new Circuit(rs.getInt(1),
                        rs.getString("departC"), rs.getString("arriveeC"));
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
            System.out.println("Circuit deleted successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCircuit(String departC,String arriveeC, Circuit c) {
         String sql = "update circuit set departC=?, arriveeC=? where idCircuit=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, departC);
            ste.setString(2, arriveeC);
            ste.setInt(3,c.getIdCircuit());
            ste.executeUpdate();
            System.out.println("Circuit updated successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    }

   

