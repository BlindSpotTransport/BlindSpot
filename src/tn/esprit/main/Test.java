/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.main;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.FormatStyle;
import javafx.util.converter.LocalDateStringConverter;
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
//  Commentaire co1 = new Commentaire(1,"I would like to file a complaint",CreationDate,true);
//  Commentaire co2 = new Commentaire(3, "salem cv", CreationDate, false);
//  sc.ajouter_commentaire(co1);  
////  sc.supprimer_commentaire(co1);
//  /*sc.modifier_commentaire(co1); */
//  
//  sc.afficher_commentaire(); 
ServiceReclamation sr = new ServiceReclamation();
LocalDate createdat = LocalDate.of(2023, 2, 20);
LocalDate createdat1 = LocalDate.of(2023, 3, 10);
Reclamation re3 = new Reclamation ("Semi","saafi",createdat,"J ai un probleme avec la reservation ");
Reclamation re4 = new Reclamation ("Amir","Razi",createdat1,"Il y a un bug ");

//sr.ajouter_reclamation(re3) ;
//sr.ajouter_reclamation(re4) ;
// sr.afficher_reclamation();
// sr.modifier_reclamation(re2);
//  sr.supprimer_reclamation(re2);
ServiceEvaluation se = new ServiceEvaluation();
Evaluation eva1 = new Evaluation(5); 
Evaluation eva2 = new Evaluation(7);
//se.ajouter_evaluation(eva2);
//se.afficher_evaluation();
//se.modifier_evaluation(eva2);
    }  
}
