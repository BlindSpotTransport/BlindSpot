/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Circuit;
import entities.Station;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceCircuit;
import services.ServiceStation;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterStationController implements Initializable {

    @FXML
    private TextField TextNom;
    @FXML
    private TextField TextAdresse;
    @FXML
    private TableView<Station> table;
    @FXML
    private TableColumn<Station, Integer> IDCol;
    @FXML
    private TableColumn<Station, String> nomCol;
    @FXML
    private TableColumn<Station, String> adresseCol;
    @FXML
    private TextField ifid;
    ServiceStation ss = new ServiceStation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Station> stat = ss.getAll();
        ObservableList<Station> listStat = FXCollections.observableArrayList(stat);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdStation"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomS"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        table.setItems(listStat);
    }    

    @FXML
    private void AjouterAction(ActionEvent event) {
        if (TextNom.getText() == null | TextAdresse.getText() == null){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("fill in all fields");
           alert.showAndWait();
        }else{
        Station s= new Station();
        //c.setIdCircuit((ifid.getText()));
        s.setNomS(TextNom.getText());
        s.setAdresse(TextAdresse.getText());
        ss.ajouterStation(s);
        reset();
        
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
        Station s= new Station();
        //c.setDepartC(TextDepart.getText());
        //System.out.println(c.getDepartC());
        //c.setArriveeC(TextArrivee.getText()); 
       
       // c.getIdCircuit(ifid.getText());
        int x=Integer.parseInt(ifid.getText());
        s.setIdStation(x);
        //System.out.println(x);
        s.setNomS(TextNom.getText());
        //System.out.println(c.getNomS());
        String nom=s.getNomS();
        ss.modifierStation(nom,s);
        reset();
    }
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a station from the table");
           alert.showAndWait();
        }else{
        
        System.out.println( table.getSelectionModel().getSelectedItem()) ;
         Station s= new Station();
//        c.setDepartC(TextDepart.getText());
//        System.out.println( TextDepart.getText()) ;
//        c.setArriveeC(TextArrivee.getText());
//        System.out.println( TextArrivee.getText()) ;
int x=Integer.parseInt(ifid.getText());
        s.setIdStation(x);
        System.out.println(x);

        ss.supprimerStation(s);
    }
    }
    private void reset() {
      
      TextNom.setText("");
      TextAdresse.setText("");
    }
     @FXML
    public void getSelected(MouseEvent event){
        int index = -1;
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        ifid.setText(IDCol.getCellData(index).toString());
        TextNom.setText(nomCol.getCellData(index).toString());
        TextAdresse.setText(adresseCol.getCellData(index).toString());
        
    }
}
