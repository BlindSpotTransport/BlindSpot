/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import API.EnvoyerEmail;
import entites.Alerte;
import entites.Evenement;
import entites.Utilisateur;
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
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import tools.MaConnection;

/**
 *
 * @author hp
 */
public class NotificationService   {
    Connection cnx;
    String sql="";
    Statement ste;
      List<Utilisateur> users = new ArrayList<>();
      List<Utilisateur> clients = new ArrayList<>();
      List<Evenement> evenements = new ArrayList<>();
      List<Alerte> alerts = new ArrayList<>();
    public NotificationService() {
       cnx = MaConnection.getInstance().getCnx();
    }
    
    //get all users 
    //SELECT * FROM `utilisateur` WHERE 1
    
    //get all events 
    public void getEvents() {
       
  
        try {
            String sql = "select * from evenement";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Evenement e = new Evenement(s.getInt("id_eve"),s.getString("titre_eve"),
                        s.getString("desc_eve"), s.getDate("date_deb_eve"),s.getDate("date_fin_eve"));
                this.evenements.add(e);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    
}
    public void getAlerts() {
       
  
        try {
            String sql = "select * from alerte";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
               
                Alerte e = new Alerte(s.getInt("id_alerte_eve"),s.getString("type_alerte_eve"),
                        s.getString("titre_eve"),s.getString("desc_eve"), s.getDate("date_deb_eve"),s.getDate("date_fin_eve"));
                this.alerts.add(e);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    
}

         // ay Object besh ywali user  
        //get users = {chauffeur , client }
    public void getTargetUsers() {
       
  
        try {
            String sql = "select * from utilisateur WHERE roleU = 'chauffeur' or roleU = 'client' ";   //	enum('admin', 'chauffeur', 'client', 'partenaire')	
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Utilisateur u = new Utilisateur(s.getInt("idU"),s.getString("nomU"),s.getString("prenomU"),s.getString("emailU"));
                this.users.add(u);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
}
    public void getOnlyClients() {
        try {
            String sql = "select * from utilisateur WHERE  roleU ='client' ";   //	enum('admin', 'chauffeur', 'client', 'partenaire')	
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Utilisateur u = new Utilisateur(s.getInt("idU"),s.getString("nomU"),s.getString("prenomU"),s.getString("emailU"));
                this.clients.add(u);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
}

    public void NotificationMail(){
        this.getTargetUsers();
        this.getEvents();
        EnvoyerEmail msg_envoyer = new EnvoyerEmail();
         for (Utilisateur u : users) {   
            for (Evenement e : evenements) {
                System.out.println("Titre "+ e.getTitre_eve()+"Descreption "+e.getDesc_eve()+" Date Début : "+e.getDate_deb_eve()); 
                msg_envoyer.envoyer(e.getTitre_eve(),u.getEmailU(),e.getDesc_eve());
            } }
        } 
    
    
    public void Notifyondashbord(){
        this.getTargetUsers();
        this.getEvents();
         for (Utilisateur u : users) {   
            for (Evenement e : evenements) {                //msg_envoyer.envoyer(e.getTitre_eve(),u.getEmailU(),e.getDesc_eve());
                Notifications notificationBuilder = Notifications.create()
                .title(e.getTitre_eve())
                .text(e.getDesc_eve())
                //.graphic(new ImageView(img))
                //.hideAfter(Duration.seconds(5))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
        System.out.println("clicked on notification");
        }

        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
            } 
         }
        } 

    
    public void NotificationClients(){
        this.getAlerts();
        this.getOnlyClients();
        EnvoyerEmail msg_envoyer = new EnvoyerEmail();
        for (Utilisateur u : clients) {   
                for (Alerte a : alerts) {
                msg_envoyer.envoyer(a.getTitre_eve(),u.getEmailU(),a.getDesc_eve());
            }
           } 
        } 

    
} 
    
    
   

