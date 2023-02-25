/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Service.AlerteService;
import Service.EvenementService;
import Service.NotificationService;
import Service.OffresService;
import entites.Alerte;
import entites.Evenement;
import entites.Offres;
import java.sql.Date;

/**
 *
 * @author hp
 */
public class Taktak {
   public static void main(String[] args) {
        
//        Alerte a = new Alerte("panne","bus_panne","ben_arous",new Date(2014, 02, 13),new Date(2014, 02, 18));
//        Alerte a1 = new Alerte("greve","metro","tunis",new Date(2015, 02, 13),new Date(2018, 02, 18));
//        Alerte a3 = new Alerte("traffic","tunis","metro_panne",new Date(2023, 02, 13),new Date(2023, 02, 18));
//        AlerteService as = new AlerteService();
//        //as.ajouter(a);
//        //as.ajouter(a3);
//        
//        
//       //System.out.println(as.afficher());
////       a.setId_alerte_eve(5);
////       a.setType_alerte_eve("accident");
////       as.modifier(a);
//       a1.setId_alerte_eve(5);
//       as.supprimer(a1);
       

//       Offres o = new Offres(1,20,(float) 30.5,"offre.jpg","Offre_En_cours","promotion","20%",new Date(2015, 02, 13),new Date(2016, 02, 18)); // cv
//       Offres o1 = new Offres(1,20,(float) 250.5,"offre.jpg","Offre_Annulé","promotion","10%",new Date(2023, 02, 13),new Date(2023, 02, 18));
//       Offres o3 = new Offres(1,20,(float) 250.5,"publicité.jpg","Offre_En_cours","publicité tunisie telecom","20%",new Date(2023, 02, 13),new Date(2023, 02, 18));

//         OffresService os = new OffresService();
         //os.ajouter(o3);
         //os.ajouter(o1);
         //o1.setId_offre_eve(5);
         //os.supprimer(o1);
//         o.setId_offre_eve(6);
//         o.setBudget_offre((float)800);
//         os.modifier(o);
        //System.out.println(os.afficher());
         
        
         
         //******** les notifications pour les clients et les chauffeureus 
         
//       NotificationService notif = new NotificationService();
//       notif.Notification();
         
         
         
        
        
        // evenement ***********************************************:
//        Evenement e = new Evenement("Annivarsaire_TakTak","soyez le bienvenus",new Date(2023, 02, 14),new Date(2023, 02, 19));
//         EvenementService es = new EvenementService();
         //es.ajouter(e);
        // System.out.println(es.afficher());
         
         //e.setId_eve(4);
         //e.setTitre_eve("panne");
         //es.modifier(e);
         //e.setId_eve(4);
         //es.supprimer(e);
         OffresService os = new OffresService();
        System.out.println(os.afficher());
     
        
    }
    
    
    
    
}
