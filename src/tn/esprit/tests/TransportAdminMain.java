/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class TransportAdminMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
   try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/PrincipalePage.fxml"));
            Scene scene = new Scene(root, 1300, 650);
            scene.getStylesheets().add("style.css");
            primaryStage.setTitle("Taktak");
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().setValue(false);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(TransportAdminMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          launch(args);   
    }
    
}
