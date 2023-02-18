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
            String requete = "INSERT INTO Reclamation (dater,descrec,statusr) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, r.getDater());
            pst.setString(2, r.getDescrec());
            pst.setString(3, r.getStatusr());
            pst.executeUpdate();
            System.out.println("La reclamation a été ajouter");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   @Override
    public List<Reclamation> afficher_reclamation() {
        List<Reclamation> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM reclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1),rs.getDate(2), rs.getString(3), rs.getString(4)));  
                System.out.println(list.toString());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

@Override
    public void modifier_reclamation(Reclamation r) {
     
        try {
            PreparedStatement pst = cnx.prepareStatement("Update Reclamation set descrec=?,statusr=? where idr = ? ");

            pst.setString(1, r.getDescrec());
            pst.setString(2, r.getStatusr());
            pst.setInt(3, r.getIdr());
            pst.executeUpdate();
            System.out.println("Réclmation modifiée!");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
 
    }
    @Override
     public void modifier_reclamation1(Reclamation r) {
     
         try {
            String requete = "UPDATE reclamation SET descrec='" + r.getDescrec() + "',statusr='" + r.getStatusr() + "' WHERE idr=" + r.getIdr();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("reclamation modifiée ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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

