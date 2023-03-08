package tn.esprit.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import tn.esprit.entity.User;
import tn.esprit.entity.Demande;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import tn.esprit.tools.MaConnection;


/**
 *
 * @author ASUS
 */
public class ServiceNotification {




/**
 *
 * @author hp
 */
    Connection cnx;
    String sql="";
    Statement ste;
      List<User> users = new ArrayList<>();
      List<User> chauffeurs = new ArrayList<>();
      List<Demande> demandes = new ArrayList<>();
      
    public ServiceNotification() {
       cnx = MaConnection.getInstance().getCnx();
    }
    
    //get all users 
    //SELECT * FROM utilisateur WHERE 1
    
    //get all events 
    public void getDemande() {
       
  
        try {
            String sql = "select * from demande";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Demande d = new Demande(s.getInt("id"),s.getString("nomC"),
                        s.getString("moyen"), s.getString("HD"),s.getString("HA"),s.getString("permis"),s.getString("EmailC"));
                this.demandes.add(d);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    
}
   

         // ay Object besh ywali user  
        //get users = {chauffeur , client }
    public void getTargetUsers() {
       
  
        try {
            String sql = "select * from utilisateur WHERE roleU = 'chauffeur'  ";   
//	enum('admin', 'chauffeur', 'client', 'partenaire')	
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                 User u = new User(s.getInt("idU"),s.getString("nomU"),s.getString("prenomU"), s.getString("emailU"), s.getInt("telephoneU"));
                this.users.add(u);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
}
    public void getOnlyChauffeurs() {
        try {
            String sql = "select * from utilisateur WHERE  roleU ='chauffeur' ";   //	enum('admin', 'chauffeur', 'client', 'partenaire')	
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u = new User(s.getInt("idU"),s.getString("nomU"),s.getString("prenomU"), s.getString("emailU"), s.getInt("telephoneU"));
                this.chauffeurs.add(u);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
}
//important
//    public void NotificationMail(){
//        this.getTargetUsers();
//        this.getDemande();
//        EnvoyerEmail msg_envoyer = new EnvoyerEmail();
//         for (Utilisateur u : users) {   
//            for (Demande d : demandes) {
//                System.out.println("Nom de circuit "+ d.getNomC()+"Moyen de transport  "+d.getMoyen()+" Heure Départ : "+d.getHD()+" Heure Arrivée : "+d.getHA()); 
//                msg_envoyer.envoyer(d.getNomC(),u.getEmailU(),d.getMoyen());
//            } }
//        } 
   
    // les notification al dashbord:
    
   

    
        public void NotifyondashbordDemande(){
        this.getOnlyChauffeurs();
        this.getDemande();
        
         for (User u : chauffeurs) {   
            for (Demande d : demandes) {    
                ImageView img=new ImageView("/Services/smallTick.png");
                Notifications notificationBuilder = Notifications.create()
                .title(d.getNomC())
                .text(d.getMoyen())
                .graphic(img)
                //.hideAfter(Duration.seconds(5))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
        System.out.println("clicked on notification");
        }

        });
        notificationBuilder.show();
        notificationBuilder.show();
            } 
         }
        } 

    
//    public void NotificationChauffeur(){
//        this.getDemande();
//        this.getOnlyChauffeurs();
//        EnvoyerEmail msg_envoyer = new EnvoyerEmail();
//        for (Utilisateur u : chauffeurs) {   
//                for (Demande  d : demandes) {
//                msg_envoyer.envoyer(d.getNomC(),u.getEmailU(),d.getMoyen());
//            }
//           } 
//        } 
//    
     
    

    
}
