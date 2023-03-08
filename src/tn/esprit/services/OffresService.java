/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Evenement;
import tn.esprit.entity.Offres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author hp
 */
public class OffresService implements NewInterface_salsa<Offres>{
    Connection cnx;
    String sql="";
    Statement ste;
    
    public OffresService() {
       cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Offres t) {
        try {
            String sql = "insert into offre (idU,titre_eve, desc_eve, date_deb_eve, date_fin_eve,duree_offre,budget_offre,img_src_offre,statut_offre)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getIdU());// cle étranger
            ste.setString(2, t.getTitre_eve());
            ste.setString(3, t.getDesc_eve());
            ste.setDate(4,t.getDate_deb_eve());
            ste.setDate(5,t.getDate_fin_eve());
            ste.setInt(6,t.getDuree_offre());
            ste.setFloat(7,t.getBudget_offre());
            ste.setString(8,t.getImg_src_offre());
            ste.setString(9,t.getStatut_offre());
            
            
            if(ste.executeUpdate()==1 ){
                 System.out.println("offre ajoutée");
            }else{
                System.out.println("probleme d'insertion offre avec la base");
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    

    }

    @Override
    public void supprimer(Offres t) {
          sql = "delete from offre where id_offre_eve =?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_offre_eve());
            if(ste.executeUpdate()==1 ){
                 System.out.println("offre supprimé");
            }else{
                System.out.println("probleme de suppression offre avec la base");
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Offres> afficher() {
         List<Offres> offres = new ArrayList<>();
        try {
            String sql = "select * from offre";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

//                Offres o = new Offres(s.getInt("id_offre_eve"),s.getInt("id_offre_eve"),s.getInt("idU"),s.getInt("duree_offre"),s.getFloat("budget_offre")
//                ,s.getString("img_src_offre"),s.getString("statut_offre"), s.getString("titre_eve"),
//                 s.getString("desc_eve"), s.getDate("date_deb_eve"),s.getDate("date_fin_eve"));
                Offres o1 = new Offres(s.getInt("id_offre_eve"),s.getString("titre_eve"),s.getString("desc_eve"),s.getDate("date_deb_eve"),s.getDate("date_fin_eve"));
                
                 offres.add(o1);
                 
                
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }

    

    @Override
    public void modifier(Offres o) {
        String sql = "update offre SET duree_offre=?,budget_offre=?,img_src_offre=?,statut_offre=?,titre_eve=?,desc_eve=?,date_deb_eve=?,date_fin_eve=?,idU=? WHERE id_offre_eve=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, o.getDuree_offre());
            ste.setFloat(2,o.getBudget_offre());
            ste.setString(3,o.getImg_src_offre() );
            ste.setString(4,o.getStatut_offre());
            ste.setString(5,o.getTitre_eve());
            ste.setString(6, o.getDesc_eve());
            ste.setDate(7, o.getDate_deb_eve());
            ste.setDate(8, o.getDate_fin_eve());
            ste.setInt(9, o.getIdU());
            ste.setInt(10,o.getId_offre_eve());
            
            if(ste.executeUpdate()==1 ){
                 System.out.println("offre modifié avec succés");
            }else{
                System.out.println("probleme de modification offre avesc la base");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
