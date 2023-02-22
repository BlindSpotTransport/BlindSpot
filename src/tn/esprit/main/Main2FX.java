/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
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
 * @author sbs
 */
public class Main2FX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      // Create a background thread to load the GUI
    Thread thread = new Thread(() -> {
        try {
            // Load the GUI using a cached FXML loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CommentForm.fxml"));
            Parent root = loader.load();
            
            // Create a scene and set it on the primary stage
            Scene scene = new Scene(root);
            
            Platform.runLater(() -> {
                primaryStage.setScene(scene);
                primaryStage.show();
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
    
    // Start the background thread
    thread.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
