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
import tn.esprit.entities.Commentaire;
import tn.esprit.tools.Connexion;

/**
 *
 * @author sbs
 */
public class ServiceCommentaire implements IServiceCommentaire<Commentaire>{
  
    Connection cnx = Connexion.getInstance().getCnx();

    @Override
    public void ajouter_commentaire(Commentaire c) {
        try {
            String requete = "INSERT INTO Commentaire (parentid,contenu,datecom,reaction) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getParentid());
            pst.setString(2, c.getContenu());
            pst.setDate(3, c.getDatecom());
            pst.setBoolean(4, c.isReaction());
            pst.executeUpdate();
            System.out.println("Le commmentaire a été ajouter");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   @Override
    public List<Commentaire> afficher_commentaire() {
        List<Commentaire> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM commentaire";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Commentaire(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getBoolean(5)));  
                System.out.println(list.toString());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

@Override
    public void modifier_commentaire(Commentaire c) {
     
        try {
            PreparedStatement pst = cnx.prepareStatement("Update Commentaire set contenu=? where idC = ? ");

            pst.setInt(1, c.getParentid());
            pst.setString(2, c.getContenu());
            pst.setDate(3, c.getDatecom());
            pst.setBoolean(4, c.isReaction());
            pst.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
 
    }


   
@Override
    public void supprimer_commentaire(Commentaire c) {
        try {
            String requete = "DELETE FROM commentaire WHERE IdC=?";
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

    