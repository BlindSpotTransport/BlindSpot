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
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.Evaluation;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author sbs
 */
public class ServiceEvaluation implements IServiceEvaluation<Evaluation> {
    


  
    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void ajouter_evaluation(Evaluation e) {
        try {
            String requete = "INSERT INTO Evaluation (nbev) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,e.getNbev());
           if (e.getNbev()>=0 && e.getNbev()<=10)
           { pst.executeUpdate();
            System.out.println("L évaluation a été ajouter");
           }
           else  System.out.println(" évaluation doit etre entre 0 et 10");
              
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   @Override
    public List<Evaluation> afficher_evaluation() {
        List<Evaluation> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM evaluation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Evaluation(rs.getInt(1),rs.getInt(2)));  
                System.out.println(list.toString());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

@Override
    public void modifier_evaluation(Evaluation e) {
     
        try {
            PreparedStatement pst = cnx.prepareStatement("Update Evaluation set nbev=? where ideva =? ");

            pst.setInt(1,e.getNbev());
            pst.setInt(2,e.getIdeva());
          
          if (e.getNbev()>=0 && e.getNbev()<=10)
           { pst.executeUpdate();
            System.out.println("L évaluation a été modifiée");
           } 
          else System.out.println("Veuillez Saisir une valeur entre 0 et 10 ");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
 
    }


   
@Override
    public void supprimer_evaluation(Evaluation e) {
        try {
            String requete = "DELETE FROM evaluation WHERE ideva=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,e.getIdeva());
            pst.executeUpdate();
            System.out.println("Evaluation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
@Override
    public int compter_eva() {

        int i = 0;
        String requete = "SELECT COUNT(*) as n FROM Evaluation";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                i = rs.getInt("n");
                System.out.println("le nombre de Evaluations est " + i);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return i;
    }

}

    