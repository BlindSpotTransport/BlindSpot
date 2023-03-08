/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.api;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sbs
 */
public class JavaMail {
    public static void sendMail(String recipient) { 
       
        String host="firas.saafi@esprit.tn";  //← my email address
        final String user="firas.saafi@esprit.tn";//← my email address
        final String password="Sajilfafiba1963";//change accordingly   //← my email password

        String to="firas.saafi@esprit.tn";//→ the EMAIL i want to send TO →
        Properties props = new Properties (); 
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        
       Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" Notification de reponse de Réclamation !!! ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h1 style =\"color:red\" >Veuillez Vérifier votre espace de reclamation !! </h1> <br/> <img width=\"50%\" height=\"50%\" src=https://portal.britishparking.co.uk/Images/photo_91708_20170527.jpg>",
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}

    }

}
