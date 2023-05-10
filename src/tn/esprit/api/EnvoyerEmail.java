/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.api;

/**
 *
 * @author hp
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EnvoyerEmail {
 final String username = "salsabil.hamraoui@esprit.tn";
 final String password = "vwlbhiwggyoscwaj";
 private Session session;
 
 // methode 
public void envoyer(String subject,String reciever,String msg) {
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
props.put("mail.smtp.ssl.protocols","TLSv1.2");

this.session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
   try {
// Etape 2 : Création de l'objet Message

Message message = new MimeMessage(this.session);
message.setFrom(new InternetAddress(this.username));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(reciever));
message.setSubject(subject);
message.setText(msg);
// Etape 3 : Envoyer le message
Transport.send(message);
//System.out.println("Message_envoye");

} catch (MessagingException e) {
throw new RuntimeException(e);
} 
}

//Etape 4 : Tester la méthode
public static void main(String[] args) {
EnvoyerEmail test = new EnvoyerEmail();
test.envoyer("test subject ","wala.hammemi00@gmail.com","msg panne jjjjjjjjjjjj");
//test.envoyer("test subject ","salsabil.hamraoui@esprit.tn","msg panne jjjjjjjjjjjj");
//"salsabil.hamraoui@esprit.tn";
} 


}
 