/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.entities.Reclamation;
import tn.esprit.services.ServiceReclamation;
import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;




/**
 * FXML Controller class
 *
 * @author sbs
 */
public class ReclamationFormController implements Initializable {

    private TextArea nomag;
    private TextArea Reponse;
Connection mc;
    PreparedStatement ste;
    private TextArea prenomRec;
    private TextArea nomRec;
    private TextArea descRec;
       private static final String BAD_WORDS_FILE = "badwords.txt";
    @FXML
    private AnchorPane anchorPaneMsg;
    @FXML
    private TextField rep2;
    @FXML
    private TextField reponseText;
    @FXML
    private Button reppsend;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void retourRec(ActionEvent event) {
         ProgressIndicator progressIndicator = new ProgressIndicator();
    progressIndicator.setMaxSize(100, 100);
    StackPane stackPane = new StackPane(progressIndicator);

    // Create a scene for the progress indicator and set it on the primary stage
    Scene progressScene = new Scene(stackPane);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Platform.runLater(() -> {
        stage.setScene(progressScene);
        stage.show();
    });

    // Load the GUI on a background thread
    Task<Parent> loadTask = new Task<Parent>() {
        @Override
        protected Parent call() throws Exception {
            // Load the GUI using the FXML loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminRecInterface.fxml"));
            return loader.load();
        }
    };

    loadTask.setOnSucceeded(e -> {
        // Get the loaded GUI and set it on the primary stage
        Parent root = loadTask.getValue();
        Scene scene = new Scene(root);
        Platform.runLater(() -> {
            stage.setScene(scene);
        });
    });

    loadTask.setOnFailed(e -> {
        System.out.println(loadTask.getException().getMessage());
    });

    // Start the task on a background thread
    Thread thread = new Thread(loadTask);
    thread.start();
    }
public static String getFullPath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }
    private void AddRec(ActionEvent event) throws SQLException {
        
         String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String Description = descRec.getText();
          try (
                  BufferedReader br = new BufferedReader(new FileReader(BAD_WORDS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Description = Description.replaceAll(line, "****");
            }
        } catch (IOException e) {
            System.err.println("Error reading bad words file: " + e.getMessage());
        }
        descRec.setText(Description);
        
     
         if (nom.isEmpty() || prenom.isEmpty() || Description.isEmpty()  ){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{
            
             Reclamation rc=new Reclamation();
             ServiceReclamation sr = new ServiceReclamation();
             ResultSet rs=ste.executeQuery();
             sr.ajouter_reclamation(rc);
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Réponse de reclamation ajoutée avec succes!");
                alert.showAndWait();
                
           
 
           nomRec.setText(null);
          prenomRec.setText(null);
          descRec.setText(null);
         }
   
    
    }

    private void reset(ActionEvent event) {
        
          nomRec.setText(null);
          prenomRec.setText(null);
          descRec.setText(null);
    }

    @FXML
    private void reponseSend(ActionEvent event) {
    }


    
}
