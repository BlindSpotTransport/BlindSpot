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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entity.User;
import tn.esprit.entity.Adresse;
import tn.esprit.tools.MaConnection;


/**
 *
 * @author user
 */
public abstract class service_adresse implements InterfaceService<Adresse> {
     Connection cnx;
    Statement st;

   
    public service_adresse() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Adresse a) {
        try {
            String sql = "insert into adresse(id,region,cite,rue,numposte)"
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
   
    public void modifierA(int numposte,String region,String cité,String rue,Adresse a) {
        String sql = "update adresse set numposte=?,region=?,cite=?,rue=? where id=?";
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
    public void supprimer(Adresse a) {
        String sql = "delete from adresse where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, a.getIdAdresse());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public List<Adresse> getAll() {
        List<Adresse> adresses = new ArrayList<>();
        try {
            String sql = "select * from adresse";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Adresse a = new Adresse(s.getInt("id"),s.getInt("numposte"),
                       s.getString("region"), s.getString("cite"),s.getString("rue"));
                adresses.add(a);
 

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adresses;
    }
  

   
    public List<Adresse> findById(int idAdresseU) {
        List<Adresse> adresses = new ArrayList<>();
        try {
            String sql = "select * from adresse where id='"+idAdresseU+"'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Adresse a = new Adresse(s.getInt("id"),s.getInt("numposte"),
                       s.getString("region"), s.getString("cite"),s.getString("rue"));
                adresses.add(a);
 
          
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adresses;
     
    }
    
    
}
