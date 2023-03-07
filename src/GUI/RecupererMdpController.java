/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.service_user;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RecupererMdpController implements Initializable {

    @FXML
    private TextField Random;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 String message;
 String Email;
    void setMessage(String message) {
        this.message=message;
        //Email=email;
    }
    
    @FXML
    private void SubmitOnAction(ActionEvent event) {
               service_user ps =new service_user() {
};
  try {
    String Random1=Random.getText();
      if(Random1.equals(message)){
                  
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
                       Parent root;
                       root = loader.load();
//                       ProfilController controller = loader.getController();
//                       controller.setUserEmail(Email);
                       Scene scene = new Scene(root);
                       Stage stage = new Stage();
                       stage.setScene(scene);
                       stage.show();
                   }} catch (IOException ex) {
                       Logger.getLogger(RecupererMdpController.class.getName()).log(Level.SEVERE, null, ex);
                   }
         
      
     
    
    }
      
    }
    

