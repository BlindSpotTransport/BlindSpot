/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.main;
import java.sql.Date;
import tn.esprit.entities.Commentaire;
import tn.esprit.entities.Evaluation;
import tn.esprit.entities.Reclamation;
import tn.esprit.services.ServiceCommentaire;
import tn.esprit.services.ServiceEvaluation;
import tn.esprit.services.ServiceReclamation;
/**
 *
 * @author sbs
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   
 ServiceCommentaire sc = new ServiceCommentaire();
  Date CreationDate = new Date (123,02,15);
  Commentaire co1 = new Commentaire(1,"I would like to file a complaint",CreationDate,true);
  Commentaire co2 = new Commentaire(3, "salem cv", CreationDate, false);
//  sc.ajouter_commentaire(co1);  
////  sc.supprimer_commentaire(co1);
//  /*sc.modifier_commentaire(co1); */
//  
//  sc.afficher_commentaire(); 
ServiceReclamation sr = new ServiceReclamation();
Date createdat = new Date (123,02,14);
Date createddate = new Date (123,05,22);
Reclamation re1 = new Reclamation (createdat,"J ai un probleme avec la reservation ","Hold");
Reclamation re2 = new Reclamation (5,createddate,"J ai un probleme avec l application","traitée");
//sr.ajouter_reclamation(re1) ;
// sr.afficher_reclamation();
 sr.modifier_reclamation1(re2);
//  sr.supprimer_reclamation(re2);
ServiceEvaluation se = new ServiceEvaluation();
Evaluation eva1 = new Evaluation(5); 
Evaluation eva2 = new Evaluation(7);
//se.ajouter_evaluation(eva2);
//se.afficher_evaluation();
//se.modifier_evaluation(eva2);
    }  
}
