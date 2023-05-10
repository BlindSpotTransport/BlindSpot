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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import tn.esprit.entity.Reclamation;
import tn.esprit.entity.User;
import tn.esprit.services.ServiceReclamation;
import tn.esprit.services.service_user;
import tn.esprit.tools.Variables;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class AdminReclaInterfaceController implements Initializable {

    private TextArea prenomRec;
    private TextArea nomRec;
    @FXML
    private TableColumn<Reclamation, String>nom;
    @FXML
    private TableColumn<Reclamation, String> prenom; 
    @FXML
    private TableColumn<Reclamation, String>date;
    @FXML
    private TableColumn<Reclamation, String> desc;
    @FXML
     private TableView<Reclamation> tableRec;
      ObservableList<Reclamation>recList;    
     Connection mc;
    PreparedStatement ste;
@FXML
    private DatePicker dateRec;
   @FXML
     private TextArea descRec;
   private static int id_tst = 0;
      ServiceReclamation rc = new ServiceReclamation();
int ID;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<Reclamation, Integer> idr;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          afficherReclamation();
        // TODO
    }    

    
    private void afficherReclamation() {
        mc=tn.esprit.tools.MaConnection.getInstance().getCnx();
        recList = FXCollections.observableArrayList();
       
        service_user su = new service_user() {};
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
            while(rs.next()){
                Reclamation r = new Reclamation();
                //tekhou mel base w tseti fel instance mb3ed el instance bsh thotha fi lista w tajoputiha or taffich or update
                r.setIdr(rs.getInt("idr"));
                r.setNom(rs.getString("nom"));
                //User u= new User();
                //u.setIdU(rs.getInt("idu"));
                r.setUser(su.findById(rs.getInt("idu")).get(0));
                r.setPrenom(rs.getString("prenom"));
                r.setDater(rs.getDate("dater").toLocalDate());
                r.setDescrec(rs.getString("descrec"));
                recList.add(r); 
            }
            
         // nom.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdr());
        nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        date.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDater().toString()));
        desc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescrec()));
       
        tableRec.setItems(recList);
      //  search();   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
        
    }
    @FXML
    private void getSelected(MouseEvent event) {
   //Reclamation clicked = tableRec.getSelectionModel().getSelectedItem();
      Variables.setRecClicked(tableRec.getSelectionModel().getSelectedItem()); 
      /*  id_tst = clicked.getIdr();
         nomRec.setText(String.valueOf(clicked.getNom()));
        prenomRec.setText(String.valueOf(clicked.getPrenom()));
       dateRec.setValue(clicked.getDater());                  
        descRec.setText(String.valueOf(clicked.getDescrec()));*/
    }

    @FXML
    private void deleteRec(ActionEvent event) {
    Reclamation clicked = tableRec.getSelectionModel().getSelectedItem();
        System.out.println(clicked);
     
       
        
        if (clicked==null) { Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(null);
            alert1.setContentText("Cliquez sur une reclamation table!");
            alert1.showAndWait();                 
        }
             Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, " Supprimer Cette Reclamation ? :" +clicked.getIdr()+" ?");
                alert2.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert2.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                rc.supprimer_reclamation(clicked);
                //updating user data after closing popup
                recList = FXCollections.observableList(rc.afficher_reclamation());
                tableRec.setItems(recList);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText("Waiting...");
            alert.setContentText("done!");
            }
        
            refresh();
             }
       
    


    

//    private void updateRec(ActionEvent event)  {
//    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("Waiting...");
//            alert.setContentText("Confirmation..!");
//       
//          
//             String nom = nomRec.getText();
//             String prenom = prenomRec.getText();
//             LocalDate date =  dateRec.getValue();
//             String description = descRec.getText();
//             
//             Optional<ButtonType>result =  alert.showAndWait(); 
//        if(result.get() == ButtonType.OK){ 
//        
//            try{
//        
//             ServiceReclamation rc = new ServiceReclamation( );
//             Reclamation r= new Reclamation(id_tst,nom,prenom,date,description);
//             rc.modifier_reclamation(r);
//            JOptionPane.showMessageDialog(null, "reclamation modifié");
//        }catch(Exception e){
//               JOptionPane.showMessageDialog(null,e);
//
//        }
//        refresh();
//               }
////        else{
////
////              nomRec.setText(null);
////              prenomRec.setText(null);
////              dateRec.setValue(null);
////              descRec.setText(null);
////              
////            ;
////
////        }
//      
//    }

    @FXML
    private void retourRec(ActionEvent event) {
             // Create a progress indicator to show the user that the GUI is loading
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalePage.fxml"));
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

//    private void AddRec(ActionEvent event) throws SQLException {
//           
//        String nom = nomRec.getText();
//        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
//        LocalDate dater = dateRec.getValue();
//        String description = descRec.getText();
//        
//     
//         if (nom.isEmpty() || prenom.isEmpty() ||  description.isEmpty() ){
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//             alert.setContentText("Donnees non disponible!!"); // controle de saisie
//             alert.showAndWait();          
//         }
//         else{
//            
//             Reclamation r=new Reclamation(nom,prenom,dater,description);
//             ServiceReclamation rc = new ServiceReclamation();
//             ResultSet rs=ste.executeQuery();
//             rc.ajouter_reclamation(r);
//             
//             Alert alert = new Alert(Alert.AlertType.INFORMATION);
//             
//             alert.setContentText("Reclamation Ajoutée avec succes!");
//                alert.showAndWait();
//                
//           
// 
//           nomRec.setText(null);
//          prenomRec.setText(null);
//          dateRec.setValue(null);
//          descRec.setText(null);
//         }
//         refresh();
    
   
    //}
 
    //for optimization   
        public void refresh(){
        
         recList.clear();
          mc=tn.esprit.tools.MaConnection.getInstance().getCnx();

        recList = FXCollections.observableArrayList();
        
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Reclamation e = new Reclamation();
                e.setIdr(rs.getInt("idr")); 
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setDater(rs.getDate("dater").toLocalDate());
                e.setDescrec(rs.getString("descrec"));
                recList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableRec.setItems(recList);   
    }
    @FXML
        public void reset ()
        {String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        LocalDate dater = dateRec.getValue();
        String description = descRec.getText();
        
           nomRec.setText(null);
          prenomRec.setText(null);
          dateRec.setValue(null);
          descRec.setText(null);
            refresh();
}

    @FXML
    private void Recherche(KeyEvent event) {  
        //PauseTransition delay = new PauseTransition(Duration.seconds(1));
//   
//        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
//    // Filter the table based on the search text
//    ObservableList<Reclamation> filteredList = FXCollections.observableArrayList();
//   
//    
//    for (Reclamation reclamation : recList) {
//        if (reclamation.getNom().toLowerCase().contains(newValue.toLowerCase())) {
//            filteredList.add(reclamation);
//        }
//    }
//    tableRec.setItems(filteredList);
// 
//
//
//    String Search = recherche.getText().toLowerCase();
//
//  
//});
String nom1 = "";
    if (event.getText().length()>0)
        nom1 = recherche.getText()+ event.getText();
    else
        nom1 = recherche.getText().substring(0,recherche.getText().length()-1 );
    System.out.println(nom1);
    String nom = nom1.toLowerCase();
    ObservableList<Reclamation> filterReclist =  recList.stream()
        .filter(r -> r.getNom().toLowerCase().contains(nom)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    tableRec.setItems(filterReclist);
    
    }

    @FXML
    private void repRec(ActionEvent event) {
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
            System.out.println("firasss");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RepReclamation.fxml"));
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
}
  


      

    


 

