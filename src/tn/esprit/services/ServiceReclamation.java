/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Reclamation;
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
import tn.esprit.entity.User;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author sbs
 */
public class ServiceReclamation implements IServiceReclamation<Reclamation>{
  Connection cnx = MaConnection.getInstance().getCnx();
      @Override
    public void ajouter_reclamation(Reclamation r) {
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            Date currentDate = new Date(now.getTime());
            String requete = "INSERT INTO Reclamation (idU,nom,prenom,dater,descrec) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setInt(1, r.getUser().getIdU());

            
            pst.setString(2, r.getNom());
            pst.setString(3, r.getPrenom());
            pst.setDate(4, currentDate);
            pst.setString(5, r.getDescrec());
             
       
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
                User u= new User();
                u.setIdU(rs.getInt("idu"));
                r.setUser(u);
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
            PreparedStatement pst = cnx.prepareStatement("Update Reclamation set nom=?,prenom=?,descrec=? where idr = ? ");
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

    public List<Reclamation> getMessages(int idU) {
        List<Reclamation> ReclamationsList = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM reclamation r where idu ="+idU +" order by dater desc";
          //   String requete2 = "SELECT * FROM utilisateur u where idu ="+idU +" order by dater";
            Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(requete);
         //  ResultSet rs2 = st.executeQuery(requete2);
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setIdr(rs.getInt("idr"));
                
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

    public List<RepReclamation> getReponses(int id) {
        List<RepReclamation> reps = new ArrayList<>();
        try {
            String requete = "SELECT * FROM RepReclamation r where idr ="+id +" order by daterep desc";
            Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                RepReclamation r = new RepReclamation();
                 r.setIdrep(rs.getInt("idrep"));
                r.setNomAg(rs.getString("nomAg"));
                r.setReponse(rs.getString("reponse"));
                r.setDaterep(rs.getDate("daterep").toLocalDate());   
                
                reps.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reps;
    }

}  

