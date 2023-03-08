/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author hp
 */
public class EvenementService implements NewInterface_salsa<Evenement>{
     Connection cnx;
    String sql="";
    Statement ste;
    
    public EvenementService() {
       cnx = MaConnection.getInstance().getCnx();
    }
    //Ajout d'un évenement 

    @Override
    public void ajouter(Evenement t) {
       try {
            String sql = "insert into evenement (titre_eve, desc_eve, date_deb_eve, date_fin_eve)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           
            ste.setString(1, t.getTitre_eve());
            ste.setString(2, t.getDesc_eve());
            ste.setDate(3,t.getDate_deb_eve());
            ste.setDate(4,t.getDate_fin_eve());
            
            if(ste.executeUpdate()==1 ){
                 System.out.println("evenement ajoutée");
            }else{
                System.out.println("probleme d'insertion evenemnt avec la base");
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public void supprimer(Evenement t) {
        sql = "delete from evenement where id_eve=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_eve());
            if(ste.executeUpdate()==1 ){
                 System.out.println("evenement supprimé");
            }else{
                System.out.println("probleme de suppression evenemnt avec la base");
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Evenement> afficher() {
       
        List<Evenement> evenements = new ArrayList<>();
        try {
            String sql = "select * from evenement";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Evenement e = new Evenement(s.getInt("id_eve"),s.getString("titre_eve"),
                        s.getString("desc_eve"), s.getDate("date_deb_eve"),s.getDate("date_fin_eve"));
                evenements.add(e);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    
}

   

    @Override
    public void modifier(Evenement e) {
        String sql = "update evenement set titre_eve=? , desc_eve=?,date_deb_eve=?,date_fin_eve=? where id_eve=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, e.getTitre_eve());
            ste.setString(2, e.getDesc_eve());
            ste.setDate(3, e.getDate_deb_eve());
            ste.setDate(4, e.getDate_fin_eve());
          
            ste.setInt(5,e.getId_eve());
            if(ste.executeUpdate()==1 ){
                 System.out.println("enenement modifié avec succés");
            }else{
                System.out.println("probleme de modification evenemnt avec la base");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
