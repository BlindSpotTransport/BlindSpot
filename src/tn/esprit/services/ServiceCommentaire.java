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
import tn.esprit.entity.Commentaire;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author sbs
 */
public class ServiceCommentaire implements IServiceCommentaire<Commentaire>{
  
    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void ajouter_commentaire(Commentaire c) {
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            Date currentDate = new Date(now.getTime());
            String requete = "INSERT INTO commentaire (nom,prenom,datecom,contenu) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
 LocalDate localDate = c.getDatecom();
 java.sql.Date LocalToDate = java.sql.Date.valueOf(localDate);
            pst.setDate(3, currentDate);
            pst.setString(4, c.getContenu());
       
            pst.executeUpdate();
            System.out.println("Le Commentair a été ajouter");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   @Override
    public List<Commentaire> afficher_commentaire() {
  List<Commentaire> CommentaireList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commentaire c ";
            Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Commentaire c = new Commentaire();
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));;;
                c.setDatecom(rs.getDate("datecom").toLocalDate()); 
                c.setContenu(rs.getString("contenu"));   
                
                CommentaireList.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CommentaireList;
    }

@Override
    public void modifier_commentaire(Commentaire c) {
     
        try {
            PreparedStatement pst = cnx.prepareStatement("Update commentaire set nom=?,prenom=?,contenu=? where idco = ? ");
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getContenu());
            pst.setInt(4, c.getIdco());
            pst.executeUpdate();
            System.out.println("Commentaire modifiée!");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
 
    }


   
@Override
    public void supprimer_commentaire(Commentaire c) {
        try {
            String requete = "DELETE FROM commentaire WHERE idco=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getIdco());
            pst.executeUpdate();
            System.out.println("Commentaire supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
@Override
    public int compter() {

        int i = 0;
        String requete = "SELECT COUNT(*) as n FROM Commentaire";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                i = rs.getInt("n");
                System.out.println("le nombre de Commentaires est " + i);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return i;
    }

}

    