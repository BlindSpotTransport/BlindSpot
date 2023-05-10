/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.services.UsersSession;
import tn.esprit.services.service_user;
//import static tn.esprit.services.service_user.encryptMdp;
//import static services.service_user.cUserRow;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RecupererMdpController implements Initializable {

    @FXML
    private TextField Random;
    @FXML
    private Button Bttn_email;
    @FXML
    private Label labelNotif;
    @FXML
    private TextField txt_email_up;
     service_user  ps = new service_user() {} ;
     String message=ps.generateRandomString();
     String mdp=ps.generateRandomString();
     public static ResultSet UserRow;
       
    /**
     * Initializes the controller class.
     */
      Connection cnx;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public RecupererMdpController(){
    cnx = MaConnection.getInstance().getCnx();
}
    
    @FXML
    private void SubmitOnAction(ActionEvent event) {
        service_user ps =new service_user() {};
        String Random1=Random.getText();
        
       try { 
      if(Random1.equals(message)){
                 String encryptedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt());

        //String encryptedPassword = encryptMdp(mdp);
 String sql = "UPDATE utilisateur SET password=? WHERE email=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
          ste.setString(1,encryptedPassword);
          ste.setString(2,UsersSession.getEmail());
           ste.executeUpdate();
            System.out.println(mdp);
            //JOptionPane.showMessageDialog(null, "utilisateur a éte modifier avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String Objet="Recuperation de mot de passe";
        String Body="Bonjour Voici votre nouveau mot de passe"+" "+mdp;
        ps.sendEmail(UsersSession.getEmail(), Objet,Body);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceLogin.fxml"));
         Parent root;
          
              root = loader.load();
              Scene scene = new Scene(root);
    Stage stage = (Stage) Random.getScene().getWindow(); // get the current stage
    stage.setScene(scene);
    stage.show();    
         
          
          
          
          
      } } catch (IOException ex) {
              Logger.getLogger(RecupererMdpController.class.getName()).log(Level.SEVERE, null, ex);
          }
         
      
     
    
      }

    @FXML
    private void EmailOnAction(ActionEvent event) {
          
//   Connection cnx;
//  
//    cnx = MaConnection.getInstance().getCnx();
    String Num="+21653821895";
  String str="+216";
   String str2="";
int g=0;

  String email=txt_email_up.getText(); 
  try {
  if(service_user.verifierEmail(email)==false){
      
              
                  labelNotif.setText("Email valid,a verification email is sent");
                 String sql = "select * from utilisateur where email=?";
                PreparedStatement ste = cnx.prepareStatement(sql);
                  ste.setString(1,email);
                  ResultSet s = ste.executeQuery();
                 while(s.next()){
                     UserRow=s;
                     UsersSession.addUserLogin(UserRow);
                 System.out.println(UsersSession.getIdU());
                  g=s.getInt("telephoneu"); 
                   str2=String.valueOf(g);
                   Num=str.concat(str2); 
                 System.out.println(Num);
                 System.out.println(message);
                 //ps.sms(message,Num);
                 String message1="code de verification d'adresse email de taktak :";
                 ps.sendEmail(UsersSession.getEmail(), message1, message);
                 //ps.modifierMdp(mdp, email);
                 }

              }
   else{
  labelNotif.setText("Email not found");
  
  }} catch (SQLException ex) {
                  Logger.getLogger(RecupererMdpController.class.getName()).log(Level.SEVERE, null, ex);
              }

 
    }
      
    }
    

