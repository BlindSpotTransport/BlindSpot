/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.entity.Circuit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.services.ServiceCircuit;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterCircuitController implements Initializable {

    @FXML
    private TextField TextDepart;
    @FXML
    private TextField TextArrivee;
    @FXML
    private Button AjouterID;
    @FXML
    private Button SupprimerID;
    @FXML
    private Button ModifierID;
    @FXML
    private TableView<Circuit> table;
    @FXML
    private TableColumn<Circuit, String> DepartCol;
    @FXML
    private TableColumn<Circuit, String> ArriveeCol;
    ServiceCircuit sc = new ServiceCircuit();
    @FXML
    private TextField ifid;
    @FXML
    private TableColumn<Circuit, Integer> IDCol;

    @FXML
    private TextField TextRecherche;
    @FXML
    private Button rechercheID;
    @FXML
    private TableColumn<Circuit, String> NomCol;
    @FXML
    private TextField TextNom;
    @FXML
    private ImageView circuit;
     ObservableList<Circuit>listCir;    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Circuit> cir = sc.getAll();
        listCir = FXCollections.observableArrayList(cir);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdCircuit"));
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("departC"));
        ArriveeCol.setCellValueFactory(new PropertyValueFactory<>("arriveeC"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nomC"));
        table.setItems(listCir);
    }    

    @FXML
    private void AjouterAction(ActionEvent event) {
  
      if (TextDepart.getText().equals("") && TextArrivee.getText().equals("") && TextNom.getText().equals("")) {
            Alert alert = new Alert (Alert.AlertType.ERROR);  
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("les champs sont obligatoire !");
           alert.showAndWait();
        }else{
        Circuit c= new Circuit();
        //c.setIdCircuit((ifid.getText()));
        c.setDepartC(TextDepart.getText());
        c.setArriveeC(TextArrivee.getText());
        c.setNomC(TextNom.getText());
        sc.ajouterCircuit(c);
        reset(); 
        refresh();
      
      }
    }

              
          
    
    

    @FXML
    private void SupprimerAction(ActionEvent event) {
        
    if (table.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("s'il vous plait selectionnez un circuit !");
           alert.showAndWait();
        }else{
        
        System.out.println( table.getSelectionModel().getSelectedItem()) ;
         Circuit c= new Circuit();
//        c.setDepartC(TextDepart.getText());
//        System.out.println( TextDepart.getText()) ;
//        c.setArriveeC(TextArrivee.getText());
//        System.out.println( TextArrivee.getText()) ;
        int x=Integer.parseInt(ifid.getText());
        c.setIdCircuit(x);
        System.out.println(x);

        sc.supprimerCircuit(c);
        refresh();
    }
        
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("s'il vous plait selectionnez un circuit !");
           alert.showAndWait();
        }else{
        Circuit c= new Circuit();
        //c.setDepartC(TextDepart.getText());
        //System.out.println(c.getDepartC());
        //c.setArriveeC(TextArrivee.getText()); 
       
       // c.getIdCircuit(ifid.getText());
        int x=Integer.parseInt(ifid.getText());
        c.setIdCircuit(x);
        //System.out.println(x);
        c.setDepartC(TextDepart.getText());
        //System.out.println(c.getDepartC());
        String Departt=c.getDepartC();
        c.setArriveeC(TextArrivee.getText()); 
        String Arriveee=c.getArriveeC();
        c.setNomC(TextNom.getText()); 
        String nom=c.getNomC();
        sc.modifierCircuit(Departt,Arriveee, nom ,c);
        reset();
        refresh();
    }
    }

    private void reset() {
      
      TextDepart.setText("");
      TextArrivee.setText("");
      TextNom.setText("");
    }
    
    @FXML
    public void getSelected(MouseEvent event){
        int index = -1;
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        ifid.setText(IDCol.getCellData(index).toString());
        TextDepart.setText(DepartCol.getCellData(index).toString());
        TextArrivee.setText(ArriveeCol.getCellData(index).toString());
        TextNom.setText(NomCol.getCellData(index).toString());
        //TestDep.setText(DepartCol.getCellData(index).toString());
        //testArr.setText(ArriveeCol.getCellData(index).toString());
    }

    
         
         private void refresh(){
         List<Circuit> cir = sc.getAll();
        ObservableList<Circuit> listCir = FXCollections.observableArrayList(cir);
       // IDCol.setCellValueFactory(new PropertyValueFactory<>("IdCircuit"));
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("departC"));
        ArriveeCol.setCellValueFactory(new PropertyValueFactory<>("arriveeC"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nomC"));
        table.setItems(listCir);
         }

    @FXML
    private void Rechercher(KeyEvent event) {
         String nom1 = "";
    if (event.getText().length()>0)
        nom1 = TextRecherche.getText()+ event.getText();
    else
        nom1 = TextRecherche.getText().substring(0,TextRecherche.getText().length()-1 );
    System.out.println(nom1);
    String nom = nom1.toLowerCase();
    ObservableList<Circuit> list =  listCir.stream()
        .filter(r -> r.getNomC().toLowerCase().contains(nom)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    table.setItems(list);
        
    }

    @FXML
    private void fresh(MouseEvent event) {
//         List<Circuit> cir = sc.getAll();
//        ObservableList<Circuit> listCir = FXCollections.observableArrayList(cir);
//        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdCircuit"));
//        DepartCol.setCellValueFactory(new PropertyValueFactory<>("departC"));
//        ArriveeCol.setCellValueFactory(new PropertyValueFactory<>("arriveeC"));
//        NomCol.setCellValueFactory(new PropertyValueFactory<>("nomC"));
//        table.setItems(listCir);

    }
        
    }
    

