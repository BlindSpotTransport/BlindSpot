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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Reclamation;
import tn.esprit.tools.Connexion;

/**
 *
 * @author sbs
 */
public class ServiceReclamation implements IServiceReclamation<Reclamation>{
  Connection cnx = Connexion.getInstance().getCnx();
      @Override
    public void ajouter_reclamation(Reclamation r) {
        try {
            String requete = "INSERT INTO Reclamation (nom,prenom,dater,descrec) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, r.getNom());
            pst.setString(2, r.getPrenom());
 LocalDate localDate = r.getDater();
 java.sql.Date LocalToDate = java.sql.Date.valueOf(localDate);
            pst.setDate(3, LocalToDate);
            pst.setString(4, r.getDescrec());
       
            pst.executeUpdate();
            System.out.println("La reclamation a été ajouter");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   @Override
    public List<Reclamation> afficher_reclamation() {
       List<Reclamation> ReclamationsList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation r ";
            Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setDater(rs.getDate("dater").toLocalDate()); 
                r.setDescrec(rs.getString("descrec"));   
                
                ReclamationsList.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReclamationsList;
         
    }

@Override
    public void modifier_reclamation(Reclamation r) {
     
        try {
            PreparedStatement pst = cnx.prepareStatement("Update Reclamation nom=?,prenom=?,descrec=? where idr = ? ");
            pst.setString(1, r.getNom());
            pst.setString(2, r.getPrenom());
            pst.setString(3, r.getDescrec());
            pst.setInt(4, r.getIdr());
            pst.executeUpdate();
            System.out.println("Réclmation modifiée!");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
 
    }   
@Override
    public void supprimer_reclamation(Reclamation r) {
        try {
            String requete = "DELETE FROM reclamation WHERE idr=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getIdr());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
@Override
    public int compter_rec() {

        int i = 0;
        String requete = "SELECT COUNT(*) as n FROM Reclamation";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                i = rs.getInt("n");
                System.out.println("le nombre de Reclamations est " + i);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return i;
    }

}  

