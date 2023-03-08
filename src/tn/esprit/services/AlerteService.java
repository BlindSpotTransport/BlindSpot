/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Alerte;
import tn.esprit.entity.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author hp
 */
public class AlerteService implements NewInterface_salsa<Alerte>{
Connection cnx;
    String sql="";
    Statement ste;
    
    public AlerteService() {
       cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Alerte t) {
        try {
           
            String sql = "insert into alerte (type_alerte_eve, titre_eve, desc_eve,date_deb_eve, date_fin_eve)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            
            ste.setString(1,t.getType_alerte_eve());
            ste.setString(2, t.getTitre_eve());
            ste.setString(3, t.getDesc_eve());
            ste.setDate(4,t.getDate_deb_eve());
            ste.setDate(5,t.getDate_fin_eve());
            
            if(ste.executeUpdate()==1 ){
                 System.out.println("alerte ajoutée");
            }else{
                System.out.println("probleme d'insertion alerte avec la base");
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    
    }

    @Override
    public void supprimer(Alerte t) {
        sql = "delete from alerte where id_alerte_eve=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_alerte_eve());
            if(ste.executeUpdate()==1 ){
                 System.out.println("alerte supprimé");
            }else{
                System.out.println("probleme de suppression alerte avec la base");
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Alerte> afficher() {
        List<Alerte> alertes= new ArrayList<>();
        try {
            String sql = "select * from alerte";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
             
               Alerte a = new Alerte(s.getInt("id_alerte_eve"),s.getString("type_alerte_eve"),s.getInt("id_alerte_eve"),s.getString("titre_eve"),s.getString("desc_eve"),s.getDate("date_deb_eve"),s.getDate("date_fin_eve"));
               a.toString();
               alertes.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return alertes;
    }

    

    @Override
    public void modifier(Alerte a) {
        String sql = "update alerte set type_alerte_eve=? ,titre_eve=? , desc_eve=?,date_deb_eve=?,date_fin_eve=? where id_alerte_eve=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setString(1, a.getType_alerte_eve());
            ste.setString(2, a.getTitre_eve());
            ste.setString(3, a.getDesc_eve());
            ste.setDate(4, a.getDate_deb_eve());
            ste.setDate(5, a.getDate_fin_eve());
          
            ste.setInt(6,a.getId_alerte_eve());
            if(ste.executeUpdate()==1 ){
                 System.out.println("alerte modifié avec succés");
            }else{
                System.out.println("probleme de modification alerte avec la base");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
