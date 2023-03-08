/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.esprit.entity.Abonnement;
import tn.esprit.entity.Typeabn;
import org.controlsfx.control.Notifications;
//import javax.swing.JOptionPane;

import tn.esprit.services.AbnService;
import tn.esprit.services.TypeService;



/**
 *
 * @author 21626
 */
public class AjouterAbnController implements Initializable {

    @FXML
    private TextField PromoFld;
    @FXML
    private RadioButton RadioBtn;
    PreparedStatement ste;
    @FXML
    private ChoiceBox<String> ChoiceBx;
    private String[] moyTr = {"Bus","Métro","Train"};
    private String[] type = {"Mensuel","Semestriel","Annuel"};
    AbnService as=new AbnService();
    TypeService ts=new TypeService();
    @FXML
    private Label control;
    @FXML
    private Label control2;

    private Parent root;
    //Abonnememt ab=new Abonnement();
    private Scene scene;
    private Stage stage;
    private RadioButton rBtn1;
    private RadioButton rbtn1;
    private RadioButton rbtn2;
    private RadioButton rbtn3;
    @FXML
    private Button ReadBtn1;
     Abonnement a= new Abonnement();
   // Typeabn t=new Typeabn();
    @FXML
    private Button ReadBtn2;
    private Label disDuree;
    @FXML
    private ChoiceBox<String> ChoiceBx2;
    @FXML
    private TableView<Abonnement> tblAbn;
    @FXML
    private TableColumn<Abonnement,Integer> idaColumn;
    @FXML
    private TableColumn<Abonnement,Integer> iduColumn;
    @FXML
    private TableColumn<Abonnement,String> moyenColumn;
    @FXML
    private TableColumn<Abonnement,Date> dateColumn;
    @FXML
    private TableColumn<Abonnement,LocalDate> dateExColumn;
    @FXML
    private TableColumn<Abonnement,Boolean> etudColumn;
    @FXML
    private TableColumn<Abonnement,Integer> redColumn;
    @FXML
    private TableColumn<Abonnement,Integer> promoColumn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField searchBar;
     List<Abonnement> abonnements =as.getAll();
     List<Typeabn>typesabn=ts.getAll();
   
    LocalDate currentDate = LocalDate.now();
    @FXML
    private Button RefBtn;
    @FXML
    private Button ExpBtn;
    @FXML
    private Button NextBtn;
    @FXML
    private TableColumn<Abonnement,Integer> idplanColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Settings ChoiceBox
        ChoiceBx.getItems().addAll(moyTr);
        ChoiceBx2.getItems().addAll(type);
        //Affichage
        idaColumn.setCellValueFactory(new PropertyValueFactory<>("idA"));
        iduColumn.setCellValueFactory(new PropertyValueFactory<>("idU"));
        moyenColumn.setCellValueFactory(new PropertyValueFactory<>("moyTrA"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateA" ));
        dateExColumn.setCellValueFactory(new PropertyValueFactory<>("dateExpA" ));
        idplanColumn.setCellValueFactory(new PropertyValueFactory<>("idtypeA" ));
        etudColumn.setCellValueFactory(new PropertyValueFactory<>("etudiantA"));
        redColumn.setCellValueFactory(new PropertyValueFactory<>("redEtA"));
        promoColumn.setCellValueFactory(new PropertyValueFactory<>("redEvA"));
        tblAbn.setItems(FXCollections.observableArrayList(abonnements));
        
     // Create a new column to display the dureeA of the Typeabn
    TableColumn<Abonnement, String> dureeColumn = new TableColumn<>("Plan");
    dureeColumn.setCellValueFactory(cellData -> {
        Abonnement abonnement = cellData.getValue();
        Typeabn typeabn = getTypeabnById(abonnement.getIdtypeA());
        if (typeabn != null) {
            return new SimpleStringProperty(typeabn.getDureeA());
        } else {
            return new SimpleStringProperty("");
        }
    });
    tblAbn.getColumns().add(dureeColumn);

    tblAbn.setItems(FXCollections.observableArrayList(abonnements));

		
	}


// A helper method to get the Typeabn by id
private Typeabn getTypeabnById(int id) {
    for (Typeabn typeabn : typesabn) {
        if (typeabn.getIdtypeA() == id) {
            return typeabn;
        }
    }
    return null;
}
    @FXML
    private void ajouterAbn(ActionEvent event) throws SQLException{

        if (ChoiceBx.getSelectionModel().isEmpty()||ChoiceBx2.getSelectionModel().isEmpty())
        {control.setText("les champs moyen transport et plan sont obligatoires");}
        else{

          
          //  a.setIdU(1);
            a.setMoyTrA(ChoiceBx.getValue());
            verifEtudiant(a);
            verifPromo(a);
            


    }

    if (ChoiceBx2.getValue().equals("Mensuel")) {
        a.setIdtypeA(1);
        
        a.setDateExpA(currentDate.plusMonths(1));
   
    } else if (ChoiceBx2.getValue().equals("Semestriel")) {
        a.setIdtypeA(2);
        a.setDateExpA(currentDate.plusMonths(6));
    } else if (ChoiceBx2.getValue().equals("Annuel")) {
        a.setIdtypeA(3);
        a.setDateExpA(currentDate.plusYears(1));
    }
  

    as.add(a);
   
    reset();
    // create and show the notification
    Notifications.create()
    .title("Abonnement ajouté")
    .text("L'abonnement a été ajouté avec succès.")
    .showInformation();
 //  JOptionPane.showMessageDialog(null, "Abonnement ajouté avec succès!"); 
    }

    private void reset() {
        PromoFld.setText("");
        RadioBtn.setSelected(false);
        ChoiceBx.getSelectionModel().clearSelection();
        ChoiceBx2.getSelectionModel().clearSelection();
        
    }	
    
    
    private void verifEtudiant(Abonnement a) {
        if(RadioBtn.isSelected()){
            a.setEtudiantA(true);
            a.setRedEtA(20);
        }else{
            a.setEtudiantA(false);
            a.setRedEtA(0);
        }
    }
    
    private void verifPromo(Abonnement a) {
        if(PromoFld.getText().isEmpty()){a.setRedEvA(0);}
     /*   else if(PromoFld.getText()=="ouverture")
        {a.setRedEvA(50);}*/
        else{control2.setText("Code invalide"); }
    }

 

    

  @FXML
private void modifierAbn(ActionEvent event) {
    if (tblAbn.getSelectionModel().isEmpty()) {
                  Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Veillez sélectionner un abonnement à modifier");
           alert.showAndWait();
    } else {
        Abonnement abonnement = tblAbn.getSelectionModel().getSelectedItem();
        // int x=Integer.parseInt(idaColumn.getText());
       //  abonnement.setIdA(x);
        abonnement.setMoyTrA(ChoiceBx.getValue());
        verifEtudiant(abonnement);
        verifPromo(abonnement);

        if (ChoiceBx2.getValue().equals("Mensuel")) {
            abonnement.setIdtypeA(1);
            abonnement.setDateExpA(currentDate.plusMonths(1));
        } else if (ChoiceBx2.getValue().equals("Semestriel")) {
            abonnement.setIdtypeA(2);
            abonnement.setDateExpA(currentDate.plusMonths(6));
        } else if (ChoiceBx2.getValue().equals("Annuel")) {
            abonnement.setIdtypeA(3);
            abonnement.setDateExpA(currentDate.plusYears(1));
        }

        as.update(abonnement);
        reset();
            // create and show the notification
    Notifications.create()
    .title("Abonnement modifier")
    .text("L'abonnement a été modifié avec succès.")
    .showInformation();
    }
}



    

    

    @FXML
    private void supprimerAbn(ActionEvent event) {
           List<Abonnement> list;
        Abonnement clicked=new Abonnement();
  /*      if(tblAbn.getSelectionModel().getSelectedItems()!=null)
        {   int x=Integer.parseInt(idaColumn.getText());
            a.setIdA(x);
            as.delete(a); }}*/
     clicked = tblAbn.getSelectionModel().getSelectedItem();
    //System.out.println("clicked");
    as.delete(clicked);
                //updating user data after closing popup
                list = FXCollections.observableList(as.getAll());
                tblAbn.setItems((ObservableList<Abonnement>) list);
    }

    @FXML
    private void rechercherAbn(KeyEvent event) {
     String searchText = searchBar.getText() + event.getText();
    ObservableList<Abonnement> filteredAbonnements = FXCollections.observableArrayList();
    for (Abonnement abonnement : abonnements) {
        if (String.valueOf(abonnement.getIdA()).contains(searchText) || String.valueOf(abonnement.getIdU()).contains(searchText)) {
            filteredAbonnements.add(abonnement);
        }
    }
    tblAbn.setItems(filteredAbonnements);
    }


    @FXML
    private void refreshTable(ActionEvent event) {

    List<Abonnement> abonnements = as.getAll();
    
    tblAbn.setItems(FXCollections.observableArrayList(abonnements));
    
                }
    private void controleAbn(Abonnement a){
        
      //   if (!as.isUnique(a) && a.getDateExpA().isBefore(currentDate)){
      if (!as.isUnique(a)){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Vous avez déjà un abonnement actid");
           alert.showAndWait();
    }
        
    }

    @FXML
    private void abnExpiree(ActionEvent event) {
        as.deleteExpiredAbonnements();

    }

    @FXML
    private void suivant(ActionEvent event) {
      try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/TypeAbn.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Fiche patient");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                 Stage stage = (Stage) node.getScene().getWindow();
                                 stage.close();
                                 //rechercher(ab);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
    
    

