/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DashbordAdminEventsController implements Initializable {

    @FXML
    private ImageView event;
    @FXML
    private ImageView logo;
    @FXML
    private Button offradmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


           
    }    

  
    @FXML
    void gereroffre(MouseEvent event) {
   try {
            Parent parent = FXMLLoader.load(getClass().getResource("GererOffre.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GererOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    void gereralerte(MouseEvent event) {
 try {
            Parent parent = FXMLLoader.load(getClass().getResource("GererAlerte.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GererAlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
       
    
    
}
