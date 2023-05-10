 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.entity.Planning;
import tn.esprit.services.MoyenstransportService;
import tn.esprit.services.ReservationService;
import tn.esprit.entity.Reservation;
import tn.esprit.services.PlanningService;
import tn.esprit.services.service_user;

/**
 *
 * @author pc
 */
public class TestMyProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //**************Ajouter un Moyenne de transport***************//
        MoyenstransportService ms1 = new MoyenstransportService();
        Moyenstransport m1 = new Moyenstransport(1,50, "Bus", "2598TUN", "b35");
        Moyenstransport m2 = new Moyenstransport(2,50, "metro", "9651TUN", "b54");
        Moyenstransport m3 = new Moyenstransport(5,50, "train", "7453TUN", "A30");
        Moyenstransport m4 = new Moyenstransport(9,50, "Bus", "2567TUN", "A52");
        //ms1.ajouter(m1);
        //ms1.ajouter(m2);
        //ms1.ajouter(m3);
        //ms1.ajouter(m4);
        //**************Afficher Liste de Moyenne de transport***************//
        //System.out.println(ms1.getAll());
        //****************Supprimer un Moyenne de transport******************//
        //ms1.supprimer(m1);
        //****************Modifier un Moyenne de transport******************//
        //ms1.modifierTransport("metro",m4);  
        //ms1.modifierTransport(50,"Bus","2567TUN","A80", m4);
   
        //***************Recherche un moyenne de transport selon id ******************//
        //System.out.println(ms1.findById(9));
        
        
        //-------------------------------------------------------------------------------------------------------------------------------------------------//
        

        //**************Ajouter une Reservation***************//
        ReservationService rs1 = new ReservationService();
        
        Date sdf; Date sdf1; Date sdf2; Date sdf3;
        try {
            sdf = new SimpleDateFormat("hh:mm:ss").parse("10:30:10");
                   long ms = sdf.getTime();
            Time t1 = new Time(ms);
            
            sdf1 = new SimpleDateFormat("hh:mm:ss").parse("12:00:00");
            //long ms2 = sdf1.getTime()+((12 * 60) + 59) * 1000;
            long ms2 = sdf1.getTime();
            Time t2 = new Time(ms2);
            rs1.changerHourTo12(t2);
            sdf2 = new SimpleDateFormat("hh:mm:ss").parse("14:30:10");
            long ms3 = sdf2.getTime();
            Time t3 = new Time(ms3);
            Reservation r1 = new Reservation(1,13025486, 550, t1, t2 , t3 , "metro");
            Reservation r2 = new Reservation(2,13025496, 550, t1, t2 , t3 , "metro");
            Reservation r3 = new Reservation(3,13025400, 550, t1, t2 , t3 , "metro");
           
            //rs1.ajouter(r1);
            //rs1.ajouter(r2);
            //rs1.ajouter(r3);

        //*************Afficher Liste de reservation************//
        //System.out.println(rs1.getAll());
        //****************Supprimer une reservation****************//
        //rs1.supprimer(r1);
                //****************Modifier une reservation******************//
        //rs1.modifierReservation("Bus", r1);

        //***************Recherche un moyenne de transport selon cin ******************//
        //System.out.println(rs1.findById(13025486));

        
       String password,encryption;
       password="CuVn9Bo6LO";
//            System.out.println(encryption=service_user.encryptMdp(password));
   //-------------------------------------------------------------------------------------------------------------------------------------------------//

        PlanningService ps1 = new PlanningService();
        
        //Planning p1 = new Planning(1, 1, 30, t2, t3) ;
        //**************Ajouter une Planning***************//
        //ps1.ajouter(p1);
        //*************Afficher Liste de Planning************//
        //System.out.println(ps1.getAll());
                
        //****************Supprimer une Planning****************//
        //ps1.supprimer(p1);
        //**************** Modifier Planning********************//
        sdf3 = new SimpleDateFormat("hh:mm:ss").parse("15:20:14");
            long ms4 = sdf3.getTime();
            Time t4 = new Time(ms4); 
        //ps1.modifierPlannig(t4,80, p1);
        
        //Afficher jointure Planning
        Planning pla = new Planning();
        //ps1.getAllPlanning();
        //System.out.println(ps1.getAll());
                
                } catch (ParseException ex) {
            Logger.getLogger(TestMyProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
       
        
   


