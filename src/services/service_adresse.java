/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.User;
import entities.adresse;
import tools.MaConnection;

/**
 *
 * @author user
 */
public abstract class service_adresse implements InterfaceService<adresse> {
     Connection cnx;
    Statement st;

   
    public service_adresse() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(adresse a) {
        try {
            String sql = "insert into adresse(idAdresseU,region,cité,rue,numPoste)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, a.getIdAdresse());
            ste.setString(2, a.getRegion());
            ste.setString(3, a.getCité());
            ste.setString(4, a.getRue());
             ste.setInt(5, a.getNumPoste());
            ste.executeUpdate();
            System.out.println("adresse utilisateur ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    public void modifierA(int numposte,String region,String cité,String rue,adresse a) {
        String sql = "update adresse set NumPoste=?,region=?,cité=?,rue=? where idAdresseU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, numposte);
            ste.setString(2,region);
            ste.setString(3,cité);
            ste.setString(4,rue);
            ste.setInt(5,a.getIdAdresse());
           ste.executeUpdate();
           System.out.println("adresse modifié avec succés"); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public void supprimer(adresse a) {
        String sql = "delete from adresse where idAdresseU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, a.getIdAdresse());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public List<adresse> getAll() {
        List<adresse> adresses = new ArrayList<>();
        try {
            String sql = "select * from adresse";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                adresse a = new adresse(s.getInt("idAdressU"),s.getInt("numPoste"),
                       s.getString("region"), s.getString("cité"),s.getString("rue"));
                adresses.add(a);
 

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adresses;
    }
  

   
    public List<adresse> findById(int idAdresseU) {
        List<adresse> adresses = new ArrayList<>();
        try {
            String sql = "select * from adresse where idAdresseU='"+idAdresseU+"'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                adresse a = new adresse(s.getInt("idAdresseU"),s.getInt("numPoste"),
                       s.getString("region"), s.getString("cité"),s.getString("rue"));
                adresses.add(a);
 
          
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adresses;
     
    }
    
    
}
