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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
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
import javafx.scene.control.Button;
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
import javax.swing.JOptionPane;
import tn.esprit.entity.Commentaire;
import tn.esprit.services.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class CommentFormController implements Initializable {

    @FXML
    private TextField nomcom;
    @FXML
    private TextField prenomcom;
    @FXML
    private TextArea contenucom;
    @FXML
    private TableView<Commentaire> tableRec;
    @FXML
    private TableColumn<Commentaire, Integer> idc;
    @FXML
    private TableColumn<Commentaire, String> nomc;
    @FXML
    private TableColumn<Commentaire, String> prenomc;
    @FXML
    private TableColumn<Commentaire, String> datec;
    @FXML
    private TableColumn<Commentaire, String> contenuc;
      ObservableList<Commentaire>comList;    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private TextField recherche;
     private static int id_tst = 0;
    @FXML
    private DatePicker datecom;
    ServiceCommentaire rc = new ServiceCommentaire();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       afficherCommentaire();
    }    
private void afficherCommentaire() {
        mc=tn.esprit.tools.MaConnection.getInstance().getCnx();
        comList = FXCollections.observableArrayList();
       
        
        String sql="select * from commentaire";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
            while(rs.next()){
                Commentaire c = new Commentaire();
                //tekhou mel base w tseti fel instance mb3ed el instance bsh thotha fi lista w tajoputiha or taffich or update
                c.setIdco(rs.getInt("idco"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setContenu(rs.getString("contenu"));
                c.setDatecom(rs.getDate("datecom").toLocalDate());
                comList.add(c); 
            }
            
         // nom.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdr());
        nomc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenomc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        datec.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDatecom().toString()));
        contenuc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getContenu()));
       
        tableRec.setItems(comList);
      //  search();   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
        
    }
 @FXML
    private void getSelected(MouseEvent event) {
        Commentaire clicked = tableRec.getSelectionModel().getSelectedItem();
       
        id_tst = clicked.getIdco();
         nomcom.setText(String.valueOf(clicked.getNom()));
        prenomcom.setText(String.valueOf(clicked.getPrenom()));
       datecom.setValue(clicked.getDatecom());                  
        contenucom.setText(String.valueOf(clicked.getContenu()));
    }
    @FXML
    private void AddCom(ActionEvent event) throws SQLException {
         String nom = nomcom.getText();
        String prenom = prenomcom.getText(); // bch te5ou text mawjoud f label w thotou f variable
        LocalDate datec = datecom.getValue();
        String contenu = contenucom.getText();
        
     
         if (nom.isEmpty() || prenom.isEmpty() ||  contenu.isEmpty() ){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{
            
             Commentaire c=new Commentaire(nom,prenom,datec,contenu);
             ServiceCommentaire rc = new ServiceCommentaire();
             ResultSet rs=ste.executeQuery();
             rc.ajouter_commentaire(c);
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Commentaire Ajoutée avec succes!");
                alert.showAndWait();
                
           
 
           nomcom.setText(null);
          prenomcom.setText(null);
          datecom.setValue(null);
          contenucom.setText(null);
         }
         refresh();
    
   
    }
     public void refresh(){
       comList.clear();
          mc=tn.esprit.tools.MaConnection.getInstance().getCnx();

        comList = FXCollections.observableArrayList();
        
        String sql="select * from commentaire";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Commentaire e = new Commentaire();
                e.setIdco(rs.getInt("idco")); 
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setDatecom(rs.getDate("datecom").toLocalDate());
                e.setContenu(rs.getString("contenu"));
                comList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableRec.setItems(comList); 
     }

    @FXML
    private void reset(ActionEvent event) {
   String nom = nomcom.getText();
        String prenom = prenomcom.getText(); // bch te5ou text mawjoud f label w thotou f variable
        LocalDate dater = datecom.getValue();
        String contenu = contenucom.getText();
        
           nomcom.setText(null);
          prenomcom.setText(null);
          datecom.setValue(null);
          contenucom.setText(null);
            refresh();
    }

    @FXML
    private void retourCom(ActionEvent event) {
        // Create a progress indicator to show the user that the GUI is loading
    ProgressIndicator progressIndicator = new ProgressIndicator();
    progressIndicator.setMaxSize(100, 100);
    StackPane stackPane = new StackPane(progressIndicator);

    // Create a scene for the progress indicator and set it on the primary stage
    Scene progressScene1 = new Scene(stackPane);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Platform.runLater(() -> {
        stage.setScene(progressScene1);
        stage.show();
    });

    // Load the GUI on a background thread
    Task<Parent> loadTask = new Task<Parent>() {
        @Override
        protected Parent call() throws Exception {
            // Load the GUI using the FXML loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            return loader.load();
        }
    };

    loadTask.setOnSucceeded(e -> {
        // Get the loaded GUI and set it on the primary stage
        Parent root1 = loadTask.getValue();
        Scene scene=  new Scene(root1);
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

    @FXML
    private void deleteCom(ActionEvent event) {
        Commentaire clicked = tableRec.getSelectionModel().getSelectedItem();
        System.out.println(clicked);
     
       
        
        if (clicked==null) { Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(null);
            alert1.setContentText("Cliquez sur une commentaire table!");
            alert1.showAndWait();                 
        }
             Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, " Supprimer Cette Commentaire ? :" +clicked.getIdco()+" ?");
                alert2.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert2.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                rc.supprimer_commentaire(clicked);
                //updating user data after closing popup
                comList = FXCollections.observableList(rc.afficher_commentaire());
                tableRec.setItems(comList);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText("Waiting...");
            alert.setContentText("done!");
            }
        
            refresh();
    }

    @FXML
    private void updateCom(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Waiting...");
            alert.setContentText("Confirmation..!");
       
          
             String nom = nomcom.getText();
             String prenom = prenomcom.getText();
             LocalDate date =  datecom.getValue();
             String contenu = contenucom.getText();
             
             Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        
            try{
        
             ServiceCommentaire rc = new ServiceCommentaire();
             Commentaire c= new Commentaire(id_tst,nom,prenom,date,contenu);
             rc.modifier_commentaire(c);
            JOptionPane.showMessageDialog(null, "commentaire modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
    }}

    @FXML
    private void Recherche(KeyEvent event) {
    String nom1 = "";
    if (event.getText().length()>0)
        nom1 = recherche.getText()+ event.getText();
    else
        nom1 = recherche.getText().substring(0,recherche.getText().length()-1 );
    System.out.println(nom1);
    String nom = nom1.toLowerCase();
    ObservableList<Commentaire> filterReclist =  comList.stream()
        .filter(r -> r.getNom().toLowerCase().contains(nom)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    tableRec.setItems(filterReclist);
    }
    
}
