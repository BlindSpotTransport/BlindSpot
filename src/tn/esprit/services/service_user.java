/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

/**
 *
 * @author user
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entity.User;
import tn.esprit.entity.Adresse;
import javax.swing.JOptionPane;
import tn.esprit.tools.MaConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import javax.swing.JFrame;
import java.util.regex.*;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import tn.esprit.services.InterfaceService;
import tn.esprit.services.UsersSession;

public abstract class service_user implements InterfaceService<User> {

    Connection cnx;
    Statement ste;
    public static int cUserId;
    public static ResultSet cUserRow;
    public service_user() {
        cnx = MaConnection.getInstance().getCnx();
    } //assure la connectivité
      public static String encryptMdp(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


     public boolean validerEmail(String email) {
    String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    return email.matches(mail);
}
      public static boolean verifierEmail(String email)  {
        try {
            String requete = "SELECT idU from  utilisateur where emailU=?";
            PreparedStatement pst = MaConnection.getInstance().getCnx().prepareStatement(requete);
            int Login =0;
            ResultSet rs ;
            pst.setString(1, email);
            rs=pst.executeQuery();
            while(rs.next()){
                Login = rs.getInt("idU");
            }
            rs.close();
            pst.close();
            if (Login>0){
                System.out.println("Error user same email found !");
                
                //JOptionPane.showMessageDialog(null, "email existant");

                return false;
            }else {
                System.out.println("Email is valid");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
            
        }
        
     }
//      public boolean validerNumero(String numero) {
//    String regex = "^(\\+\\d{1,3}[- ]?)?\\d{8}$";
//    return numero.matches(regex);
//}
 
public static boolean validerCin(String input) {
    String pattern = "^\\d{8}$";
    return input.matches(pattern);
}
      

public boolean validateString(String inputString) {
    String regex = "^[a-zA-Z0-9 ]*$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(inputString);

    return matcher.matches();
}
    @Override
    public void ajouter(User u) {
        
    String encryptedPassword = encryptMdp(u.getMdpU());
     try {
            String sql = "INSERT INTO `utilisateur`(`idU`, `nomU`, `prenomU`, `idAdresse`, `telephoneU`, `emailU`, `roleU`, `createdAtU`, `cinU`, `imagePU`, `permisU`, `abonnéU`, `mdpU`) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, u.getIdU());
            ste.setString(2, u.getNomU());
            ste.setString(3, u.getPrenomU());
            ste.setInt(4, u.getIdAdresseU());
            ste.setInt(5, u.getTelephoneU());
            if (validerEmail(u.getEmailU())){
               
                 if(verifierEmail(u.getEmailU())){
                  ste.setString(6, u.getEmailU());
                 }
                
            else {
                System.out.println("email non valide");
            }
            ste.setString(7, u.getRoleU());
            ste.setString(8, u.getCreatedAtU());
            ste.setInt(9, u.getCinU());
            ste.setString(10, u.getImagePU());
            ste.setString(11, u.getPermisU());
            ste.setBoolean(12, u.isAbonnéU());
            ste.setString(13, encryptedPassword);
            ste.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Utilisateur ajoutée");
            System.out.println("Utilisateur ajoutée");
            }
     } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    @Override
    public void supprimer(User u) {
        String sql = "delete from utilisateur where IdU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, u.getIdU());
            ste.executeUpdate();
            System.out.println("utilisateur supprimé avec succés");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("account is deleted with success !");
            alert.showAndWait();
            return;
//JOptionPane.showMessageDialog(null, "utilisateur supprimé avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
   /*public void modifier(String nom,String prenom,String image,String mdp,String Email,Adresse  Adresse,int telephone,User u) {
    int idAdresse;
    idAdresse=Adresse.getIdAdresse();
    
    String encryptedPassword = encryptMdp(mdp);
       
       String sql = "UPDATE utilisateur SET nomU=?, prenomU=?, imagePU=?, mdpU=?, emailU=?, idAdresse=?, telephoneU=? WHERE idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
             ste.setString(2, prenom);
              ste.setString(3, image);
               ste.setString(4,encryptedPassword);
                ste.setString(5, Email);
                 ste.setInt(6,idAdresse);
                  ste.setInt(7, telephone);
            ste.setInt(8,u.getIdU());
            ste.executeUpdate();
            System.out.println("utilisateur a éte modifier avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }*/  
   
   @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        
        try {
            String sql = "select * from utilisateur where roleU='client' or roleU='chauffeur'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u = new User(s.getInt(1), 
                        s.getString("nomU"), s.getString("prenomU"),s.getString("imagePU"),s.getString("emailU"),s.getInt("idAdresse"),s.getInt("telephoneU"),s.getString("roleU"));
                users.add(u);
 

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
   public List<User> findById(int id ) {
        List<User> users = new ArrayList<>();
        try {
            String sql = "select * from utilisateur where idU='"+id+"'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u = new User(s.getInt(1), 
                        s.getString("nomU"), s.getString("prenomU"),s.getString("imagePU"),s.getString("emailU"),s.getInt("idAdresse"),s.getInt("telephoneU"));
                users.add(u);
 

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
        
     
    }
    
   public void modifier1(String nom,String prenom,String role,String Email,int tel,User u) {
    //int idAdresse;
    //idAdresse=Adresse.getIdAdresse();
    //String encryptedPassword = encryptMdp(mdp);
 String sql = "UPDATE utilisateur SET nomU=?, prenomU=?,roleU=?,emailU=?,telephoneU=? WHERE idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
          ste.setString(1,nom);
          ste.setString(2,prenom);
          ste.setString(3,role);
          ste.setString(4,Email);
          ste.setInt(5,u.getTelephoneU());
          ste.setInt(6,u.getIdU());
           ste.executeUpdate();
            System.out.println("utilisateur a éte modifier avec succées !");
            //JOptionPane.showMessageDialog(null, "utilisateur a éte modifier avec succées !");
                 
                   
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("account is modified with success !");
            alert.showAndWait();
            return;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   } 
   public void modifier2(String nom,String prenom,String role,String Email,User u) {
    //int idAdresse;
    //idAdresse=Adresse.getIdAdresse();
    //String encryptedPassword = encryptMdp(mdp);
 String sql = "UPDATE utilisateur SET nomU=?, prenomU=?,roleU=?,emailU=? WHERE idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
          ste.setString(1,nom);
          ste.setString(2,prenom);
          ste.setString(3,role);
          ste.setString(4,Email);
          ste.setInt(5,u.getIdU());
           ste.executeUpdate();
            System.out.println("utilisateur a éte modifier avec succées !");
            //JOptionPane.showMessageDialog(null, "utilisateur a éte modifier avec succées !");
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
           alert.setContentText("account is modified with success !");
            alert.showAndWait();
            return;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
   public void modifierImage(String image,int id) {
    //int idAdresse;
    //idAdresse=Adresse.getIdAdresse();
    //String encryptedPassword = encryptMdp(mdp);
 try {
    String sql = "UPDATE utilisateur SET imagePU=? WHERE idU=?";
       
            PreparedStatement ste = cnx.prepareStatement(sql);
          ste.setString(1,image);
          ste.setInt(2,id);
           ste.executeUpdate();
            System.out.println("updated profile pic with succés");
            //JOptionPane.showMessageDialog(null, "utilisateur a éte modifier avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
//   public void modifierMdp(String mdp,String email) {
//    //int idAdresse;
//    //idAdresse=Adresse.getIdAdresse();
//    String encryptedPassword = encryptMdp(mdp);
// String sql = "UPDATE utilisateur SET mdpU=? WHERE emailU=?";
//        try {
//            PreparedStatement ste = cnx.prepareStatement(sql);
//          ste.setString(1,encryptedPassword);
//          ste.setString(2,email);
//           ste.executeUpdate();
//            System.out.println("updated profile pic with succés");
//            //JOptionPane.showMessageDialog(null, "utilisateur a éte modifier avec succées !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//   }
   
   public  void  sms (String Mdp,String Num) {
    
        Twilio.init("AC8abd625402bce796bb5214e64d8cb791","bdbd19edd73c7dceddb5c11aaec52c9d");
        Message message = Message.creator(
                //new com.twilio.type.PhoneNumber("+21653821895"),
                new com.twilio.type.PhoneNumber(Num),
                new com.twilio.type.PhoneNumber("+13159034644"),
                 Mdp )
            .create();

        System.out.println(message.getSid());
    }
   public void sendEmail(String to , String sub , String body){
        
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ghribi.chaima@esprit.tn", "212JFT7533");
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress("ghribi.chaima@esprit.tn"));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub);
            m.setText(body);

            //send mail

            Transport.send(m);
            //sentBoolValue.setVisible(true);
            //JOptionPane.showMessageDialog(null, "message sent");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
           alert.setContentText("Email sent");
            alert.showAndWait();
            return;

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }
   
   public String generateRandomString(){
         
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
private static final String CHAR_LIST =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;
    public int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
     public static boolean LoginUser(String email, String password) throws Exception {
           boolean checkUser = true;
          ResultSet rs ;
         Connection cnx; 
         cnx = MaConnection.getInstance().getCnx();
       
        User u = new User();
           try {
           
  //String encrytedpwd = service_user.encryptMdp(password);
        String encrytedpwd = service_user.encryptMdp(password);
        String sql = "Select* from utilisateur where emailU=? and mdpU=?";
        PreparedStatement pst = cnx.prepareStatement(sql);
               pst.setString(1,email);
               pst.setString(2,encrytedpwd);
               rs = pst.executeQuery();
                cUserRow=rs;
               if(rs.next()) {
                   //JOptionPane.showMessageDialog(null, "Email And Password is Correct");
               
                
//                cUserId = rs.getInt("idU");
//                cUserRow = rs;
//                UsersSession.addUserLogin(cUserRow);
                   UsersSession.addUserLogin(cUserRow);
              
           System.out.println(UsersSession.getIdU());
               }
               else{
               
               System.out.println("user not found");
               checkUser = false;
               }
               //UsersSession.addUserLogin(cUserRow);
        
//            System.out.println(UsersSession.getRole());
         
//             System.out.println(UsersSession.getEmail());

                
            
        } catch (SQLException ex) {
            Logger.getLogger(service_user.class.getName()).log(Level.SEVERE, null, ex);
            checkUser = false;
        }
        
        return checkUser;}
    


     
     }
