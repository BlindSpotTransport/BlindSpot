/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
import tn.esprit.entity.Reclamation;
import tn.esprit.services.ServiceReclamation;
import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import static tn.esprit.api.JavaMail.sendMail;
import tn.esprit.entity.RepReclamation;
import tn.esprit.services.ServiceRepReclam;
import tn.esprit.services.UsersSession;
import tn.esprit.tools.MaConnection;
import tn.esprit.tools.Variables;


     
       


/**
 * FXML Controller class
 *
 * @author sbs
 */
public class ReclamationFormController implements Initializable {

    private TextArea nomAd;
    private TextArea rep;
    @FXML
    private TextField rep2;
    @FXML
    private AnchorPane anchorPaneMsg;
    @FXML
    private TextField reponseText;
    @FXML
    private Button reppsend;
    @FXML
    private ScrollPane scrollpanemsg;
    //private static int
    /**
     * Initializes the controller class.
     */
       Connection cnx ;
     
             public ReclamationFormController(){
      cnx = MaConnection.getInstance().getCnx();
       }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMessages();
      scrollpanemsg.setVvalue(1);
      
    }    
    
    private void showMessages(){
       
        ServiceReclamation sr = new ServiceReclamation();
        System.out.println(UsersSession.getIdU());
        List<Reclamation> msgUser = sr.getMessages(UsersSession.getIdU());
        int index1= 0;
        int index3= 0;
        for (Reclamation r : msgUser){
            int index2= 0;
                    System.out.println("a : "+index1+"  "+index2+"  "+index3);
                    System.out.println(r.getIdr());

            
            
            List<RepReclamation> reps = sr.getReponses(r.getIdr());
             for (RepReclamation rp : reps){
                                     System.out.println("b : "+index1+"  "+index2+"  "+index3);

                 
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("paneMsg3.fxml"));

                    try {
                    Pane p = (Pane) loader2.load();
    try  {
                String nameAg = rp.getNomAg() ;
//                System.out.println("hedha houa nom: "+ rp.getNomAg());
                             String pic = ""; 
        String sqlpic= "SELECT imagepu FROM `utilisateur` WHERE  nomU='"+nameAg+"'" ;
        
            Statement ste = cnx.createStatement();
            ResultSet rspic = ste.executeQuery(sqlpic);
            while (rspic.next())
            {
                pic = rspic.getString("imagepu");
                System.out.println("hedha houa pic wost l while: "+ pic);

            }
            String resultpic = pic ; 
//              System.out.println("hedha houa resultpic lbara  mell while: "+ pic);
                String imagePath1 = resultpic.substring("file:/".length());
           Image image1 = new Image(new File(imagePath1).toURI().toString());
                        ((ImageView) p.getChildren().get(1)).setImage(image1);
            
        } catch (SQLException ex) {
            Logger.getLogger(RepReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    ((Text)p.getChildren().get(0)).setText(rp.getNomAg());
                  
                    ((Text)p.getChildren().get(3)).setText(rp.getDaterep().toString());
                    ((Text)p.getChildren().get(4)).setText(rp.getReponse());
                    ((Text)p.getChildren().get(5)).setText(String.valueOf(reps.size()- index2));
//                     ((Button)p.getChildren().get(6)).setOnMouseClicked((event -> {
//                                remove_reply(rp.getIdrep());
//                      }));





                    
                    p.setPrefWidth(350);
                    p.setPrefHeight(100);
                    p.setLayoutX(130);
                    p.setLayoutY(1830-110*index3);
                         anchorPaneMsg.getChildren().add(p);
                         } catch (IOException ex) {
                        Logger.getLogger(RepReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    index2++;
                    index3++;
                    
             }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaneMsg.fxml"));

            try {
            Pane p = (Pane) loader.load();
            ((Text)p.getChildren().get(0)).setText(UsersSession.getName()+" "+UsersSession.getLastname());
          String imagePath = UsersSession.getProfilepicture().substring("file:/".length());
           Image image = new Image(new File(imagePath).toURI().toString());
        ((ImageView) p.getChildren().get(1)).setImage(image);
            ((Text)p.getChildren().get(3)).setText(r.getDater().toString());
            ((Text)p.getChildren().get(4)).setText(r.getDescrec());
            ((Text)p.getChildren().get(5)).setText(String.valueOf(msgUser.size()- index1));
            ImageView imageView = (ImageView) p.getChildren().get(6); 
imageView.setPickOnBounds(true); imageView.setCursor(Cursor.HAND); 
imageView.setOnMouseClicked(event -> { remove_reclam(r.getIdr()); });
           
           //(p.getChildren().get(7)).setOnMouseClicked(repondre(ActionEvent event));
//             final int index = msgUser.size()- index1;
//                    ((Button)p.getChildren().get(6)).setOnMouseClicked((event -> {
//                                setInput(index);  
//                            }));



//                      ((Button)p.getChildren().get(8)).setOnMouseClicked((event -> {
//                                setInput(index);
//                            }));
            p.setPrefWidth(350);
            p.setPrefHeight(100);
            p.setLayoutX(30);
            p.setLayoutY(1830-110*index3);
                 anchorPaneMsg.getChildren().add(p);
                 } catch (IOException ex) {
                Logger.getLogger(RepReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            index3++;
            index1++;
            
        }
    }
    
    
    
    
    private void Afficher_Reclamation()
    {
    Reclamation rec =  Variables.getRecClicked();
// Text name = new Text("Le nom: ");
//name.getStyleClass().add("label-prefix");
//Text nameValue = new Text(rec.getNom());
//nameValue.getStyleClass().add("label-value");
//
//Text lastName = new Text("Le Prenom: ");
//lastName.getStyleClass().add("label-prefix");
//Text lastNameValue = new Text(rec.getPrenom());
//lastNameValue.getStyleClass().add("label-value");
//
//Text date = new Text("La Date : ");
//date.getStyleClass().add("label-prefix");
//Text dateValue = new Text(rec.getDater().toString());
//dateValue.getStyleClass().add("label-value");
//
//Text description = new Text("La Description: ");
//description.getStyleClass().add("label-prefix");
//Text descriptionValue = new Text(rec.getDescrec());
//descriptionValue.getStyleClass().add("label-value");
//
//HBox nameBox = new HBox(name, nameValue);
//HBox lastNameBox = new HBox(lastName, lastNameValue);
//HBox dateBox = new HBox(date, dateValue);
//HBox descriptionBox = new HBox(description, descriptionValue);
//    Label Name = new Label("Le nom: "+rec.getNom());
//    Label LastName = new Label("Le Prenom: "+rec.getPrenom());
//    Label Date = new Label("La Date : "+rec.getDater());
//    Label Description = new Label("La Description : "+rec.getDescrec());
//    Name.getStyleClass().add("label");
//    LastName.getStyleClass().add("label");
//    Date.getStyleClass().add("label");
//   display.getStyleClass().add("tile-pane");
//     display.getChildren().addAll(nameBox, lastNameBox, dateBox, descriptionBox);
//  Parent parent = display.getParent(); 
//  parent.getStylesheets().add(getClass().getResource("../css/Display.css").toExternalForm());
  

    
    
    
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalePageClient.fxml"));
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

//    private void repRec(ActionEvent event) {
//        
//        String nom = nomAd.getText();
//         String repp = rep.getText();
//        Reclamation rec =  Variables.getRecClicked();
//        RepReclamation repRecc = new RepReclamation();
//        repRecc.setReclamation(rec);
//        repRecc.setNomAg(nom);
//        Calendar calendar = Calendar.getInstance();
//            java.util.Date now = calendar.getTime();
//            java.sql.Date currentDate = new java.sql.Date(now.getTime());
//            
//        repRecc.setDaterep(currentDate.toLocalDate());
//        repRecc.setReponse(repp);
//        ServiceRepReclam srp = new ServiceRepReclam();
//          srp.ajouter_repreclamation(repRecc);
//          sendMail("firas.saafi@esprit.tn");
//     ProgressIndicator progressIndicator = new ProgressIndicator();
//    progressIndicator.setMaxSize(100, 100);
//    StackPane stackPane = new StackPane(progressIndicator);
//
//    // Create a scene for the progress indicator and set it on the primary stage
//    Scene progressScene = new Scene(stackPane);
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    Platform.runLater(() -> {
//        stage.setScene(progressScene);
//        stage.show();
//    });
//
//    // Load the GUI on a background thread
//    Task<Parent> loadTask = new Task<Parent>() {
//        @Override
//        protected Parent call() throws Exception {
//            // Load the GUI using the FXML loader
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalePageClient.fxml"));
//            return loader.load();
//        }
//    };
//
//    loadTask.setOnSucceeded(e -> {
//        // Get the loaded GUI and set it on the primary stage
//        Parent root = loadTask.getValue();
//        Scene scene = new Scene(root);
//        Platform.runLater(() -> {
//            stage.setScene(scene);
//        });
//    });
//
//    loadTask.setOnFailed(e -> {
//        System.out.println(loadTask.getException().getMessage());
//    });
//
//    // Start the task on a background thread
//    Thread thread = new Thread(loadTask);
//    thread.start();
//    }
    private void Repondre(){
         
    }

 




        private void remove_reclam(int id) {
       
        ServiceReclamation sr = new ServiceReclamation();
       Reclamation r = new Reclamation();
        r.setIdr(id);
        sr.supprimer_reclamation(r);
        double i = scrollpanemsg.getVvalue();
        anchorPaneMsg.getChildren().clear();
        showMessages();
         scrollpanemsg.setVvalue(i);
         
    }

    @FXML
    private void ReclamSend(ActionEvent event) {
       
        ServiceReclamation sr = new ServiceReclamation();
        ServiceRepReclam srp = new ServiceRepReclam();

    
//        List<Reclamation> msgUser = sr.getMessages(UsersSession.getUser().getIdU());
        Reclamation r = new Reclamation();
        LocalDate currentDate = LocalDate.now();
        r.setIdU(UsersSession.getIdU());
        r.setDater(currentDate);
        r.setNom(UsersSession.getName());
        r.setPrenom(UsersSession.getLastname());
        if(rep2.getText().isEmpty()) {
        // Display a warning message
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une Réclamation! ");
        alert.showAndWait();
        return;
    }
        r.setDescrec(rep2.getText());     
        sr.ajouter_reclamation(r);
        anchorPaneMsg.getChildren().clear();
        showMessages();
    }
    }