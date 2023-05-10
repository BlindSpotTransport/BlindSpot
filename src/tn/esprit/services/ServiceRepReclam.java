/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import tn.esprit.entity.RepReclamation;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author sbs
 */
public class ServiceRepReclam implements IServiceRepReclam<RepReclamation>{
     Connection cnx = MaConnection.getInstance().getCnx();
    @Override
    public void ajouter_repreclamation(RepReclamation r) {
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            Date currentDate = new Date(now.getTime());
            String requete = "INSERT INTO RepReclamation (nomag,daterep,reponse,idr) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, r.getNomAg());
 LocalDate localDate = r.getDaterep();
 java.sql.Date LocalToDate = java.sql.Date.valueOf(localDate);
            pst.setDate(2, LocalToDate);
            pst.setString(3, r.getReponse());
       pst.setInt(4, r.getReclamation().getIdr());
            pst.executeUpdate();
            System.out.println("La Réponse a été ajouter");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public List<RepReclamation> afficher_repreclamation() {
         List<RepReclamation> RepReclamationList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM repreclamation r ";
            Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                RepReclamation r = new RepReclamation();
                r.setNomAg(rs.getString("NomAg"));
                r.setDaterep(rs.getDate("daterep").toLocalDate()); 
                r.setReponse(rs.getString("reponse"));   
                
                RepReclamationList.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return RepReclamationList;
    }



    @Override
    public void supprimer_repreclamation(RepReclamation r) {
            try {
            String requete = "DELETE FROM repreclamation WHERE idrep=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getIdrep());
            pst.executeUpdate();
            System.out.println("Réponse supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public int compter_repreclam() {
        int i = 0;
        String requete = "SELECT COUNT(*) as n FROM RepReclamation";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                i = rs.getInt("n");
                System.out.println("le nombre de Réponse est " + i);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return i;
    }
    @Override
    public void modifier_repreclamation(RepReclamation rp) {
     
        try {
            PreparedStatement pst = cnx.prepareStatement("Update RepReclamation set reponse=? where idrep = ? ");
            pst.setString(1, rp.getReponse());
            pst.setInt(2, rp.getIdrep());
            pst.executeUpdate();
            System.out.println("Répréclmation modifiée!");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
 
    }  
    }
   
 
    


