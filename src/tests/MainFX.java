/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class MainFX extends Application{
    
    
     @Override
    public void start(Stage primaryStage) {
        
         {
            
         
             try {
              Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterCircuit.fxml"));
              Scene scene = new Scene(root, 600, 500);   
              primaryStage.setTitle("CIRCUIT");
              primaryStage.setScene(scene);
              primaryStage.show();
             } catch (IOException ex) {
                 Logger.getLogger(MainFX.class.getName()).log(Level.SEVERE, null, ex);
             }
    
    }
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
