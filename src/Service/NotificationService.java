/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import API.EnvoyerEmail;
import entites.Evenement;
import entites.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
      List<Evenement> evenements = new ArrayList<>();
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
    
    public void Notification(){
        this.getTargetUsers();
        this.getEvents();
        EnvoyerEmail msg_envoyer = new EnvoyerEmail();

         for (Utilisateur u : users) {   
           //System.out.println("Bonjour : "+ u.getNomU()+" "+u.getPrenomU()+"\n"); 
           //System.out.println("************** Nos Evenements *****************"); 
            for (Evenement e : evenements) {
                System.out.println("Titre "+ e.getTitre_eve()+"Descreption "+e.getDesc_eve()+" Date Début : "+e.getDate_deb_eve()); 
              msg_envoyer.envoyer(e.getTitre_eve(),u.getEmailU(),e.getDesc_eve());
            } 
            
            // System.out.println("************** Good Bye ,Vous avez recu une notif sur le mail . *****************"); 
        } 
    } 
    
    
   
}
