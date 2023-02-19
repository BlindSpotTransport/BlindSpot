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
import tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class ServiceStation implements InterfaceStation {
    
    Connection cnx;

    public ServiceStation() {
        cnx = MaConnection.getInstance().getCnx();
    }

     @Override
    public void ajouterStation(Station s) {
        try {
            String sql = "insert into station(nomS,adresse)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, s.getNomS());
            ste.setString(2, s.getAdresse());
            ste.executeUpdate();
            System.out.println("Station ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List getAll() {
         List<Station> stations = new ArrayList<>();
        try {
            String sql = "select * from station";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Station s = new Station(rs.getInt(1),
                        rs.getString("nomS"), rs.getString("adresse"));
                stations.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stations;
    }

    @Override
    public List findById(int idStation) {
         List<Station> stations = new ArrayList<>(); 
        
   try {
   String sql ="select * from `station` where idStation = ?";
   PreparedStatement ste = cnx.prepareStatement(sql);
   ste.setInt(1, idStation);
   ResultSet rs = ste.executeQuery();
   while (rs.next())  {
       
	  Station s = new Station(rs.getInt(1),
                        rs.getString("nomS"), rs.getString("adresse"));
          stations.add(s);
  }
 } catch (SQLException ex) {
System.out.println(ex.getMessage());
        }
        return stations;
    }
    

    @Override
    public void supprimerStation(Station s) {
        
         String sql = "delete from station where idStation=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, s.getIdStation());
            ste.executeUpdate();
            System.out.println("Station supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierStation(String nomS, Station s) {
       String sql = "update station set nomS=? where idStation=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nomS);
            ste.setInt(2,s.getIdStation());
            ste.executeUpdate();
            System.out.println("Station modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
