/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Circuit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import services.ServiceCircuit;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Circuit> cir = sc.getAll();
        ObservableList<Circuit> listCir = FXCollections.observableArrayList(cir);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdCircuit"));
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("departC"));
        ArriveeCol.setCellValueFactory(new PropertyValueFactory<>("arriveeC"));
        table.setItems(listCir);
    }    

    @FXML
    private void AjouterAction(ActionEvent event) {
  
      if (TextDepart.getText().equals("") && TextArrivee.getText().equals("")) {
            Alert alert = new Alert (Alert.AlertType.ERROR);  
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("fill all the case");
           alert.showAndWait();
        }else{
        Circuit c= new Circuit();
        //c.setIdCircuit((ifid.getText()));
        c.setDepartC(TextDepart.getText());
        c.setArriveeC(TextArrivee.getText());
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
           alert.setContentText("Please select a circuit from the table");
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
           alert.setContentText("Please select a circuit from the table");
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
        sc.modifierCircuit(Departt,Arriveee,c);
        reset();
        refresh();
    }
    }

    private void reset() {
      
      TextDepart.setText("");
      TextArrivee.setText("");
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
        //TestDep.setText(DepartCol.getCellData(index).toString());
        //testArr.setText(ArriveeCol.getCellData(index).toString());
    }

    @FXML
    private void RechercherAction(ActionEvent event) {
         if (TextRecherche.getText().equals(DepartCol)) {
       // if (TextRecherche== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Fill the case");
           alert.showAndWait();
        }else{
             String departC = TextRecherche.getText();
             String arriveeC = TextRecherche.getText();
         List<Circuit> cir = sc.findBy(departC, arriveeC);
        ObservableList<Circuit> listCir = FXCollections.observableArrayList(cir);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdCircuit"));
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("departC"));
        ArriveeCol.setCellValueFactory(new PropertyValueFactory<>("arriveeC"));
        table.setItems(listCir);
        
       
    }
    }
         
         private void refresh(){
         List<Circuit> cir = sc.getAll();
        ObservableList<Circuit> listCir = FXCollections.observableArrayList(cir);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdCircuit"));
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("departC"));
        ArriveeCol.setCellValueFactory(new PropertyValueFactory<>("arriveeC"));
        table.setItems(listCir);
         }
        
    }
    

