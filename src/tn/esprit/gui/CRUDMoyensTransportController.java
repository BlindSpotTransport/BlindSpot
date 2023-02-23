/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.sun.xml.internal.fastinfoset.stax.events.Util;
import java.awt.Image;
import java.io.Console;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.management.Notification;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.services.MoyenstransportService;
import tn.esprit.tests.TestMyProject;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class CRUDMoyensTransportController implements Initializable {

    @FXML
    private TableColumn<Moyenstransport, Integer> IDColumn;
    @FXML
    private TableColumn<Moyenstransport, String> TypeColumn;
    @FXML
    private TableColumn<Moyenstransport, String> MatColumn;    
    @FXML
    private TableColumn<Moyenstransport, Integer> CapaColumn;
    @FXML
    private TableColumn<Moyenstransport, String> Numcolumn;    
    
    @FXML
    private TextField MatMDF;
    @FXML
    private TextField CapMDF;
    @FXML
    private TextField NumMDF;
    @FXML
    private ComboBox<String> TypeMDF;
    @FXML
    private TextField IDMDF;
    @FXML
    private TableView<Moyenstransport> tableMoyenne;

    String s;
    int index=-1 ;
   // int countt = tableMoyenne.getItems().size();
    MoyenstransportService ms = new MoyenstransportService();

    @FXML
    private Label OLDtype;
    @FXML
    private Label OldMat;
    @FXML
    private Label OldCap;
    @FXML
    private Label OldNum;
    @FXML
    private Label VideCombo;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Moyenstransport> MoyenTListe = ms.getAll();
        ObservableList<Moyenstransport> listeM = FXCollections.observableArrayList(MoyenTListe);
        IDColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,Integer>("idMoy"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("type"));
        MatColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("matricule"));
        CapaColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,Integer>("capacite"));
        Numcolumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("numMoy"));
        tableMoyenne.setItems(listeM);
        ObservableList<String> List = FXCollections.observableArrayList("Bus","Metro","Train");
        TypeMDF.setItems(List);
        //TypeMDF.getSelectionModel().select(0);
    }    

    @FXML
    private void ModifierAction(ActionEvent event) {
   // String x=TypeMDF.getSelectionModel().getSelectedItem().toString();
    
    if(tableMoyenne.getSelectionModel().getSelectedItem()==null){
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("veuillez sélectionner moyenne de transport dans le tableau !");
        alert.showAndWait();
        
    }else if (TypeMDF.getSelectionModel().getSelectedItem().toString().equals(OLDtype.getText()) && MatMDF.getText().equals(OldMat.getText()) 
            && CapMDF.getText().equals(OldCap.getText()) && NumMDF.getText().equals(OldNum.getText()) ){
        System.out.println("True");
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("warning");
        alert.setHeaderText(null);
        alert.setContentText("veuillez modifier aux moins un champ !");
        alert.showAndWait();
        return;
    }else{    
        Moyenstransport m = new Moyenstransport();
        int Id=Integer.parseInt(IDMDF.getText());
        m.setIdMoy(Id);
        String s=TypeMDF.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
        m.setType(s);
        m.setMatricule(MatMDF.getText());
        int cap=Integer.parseInt(CapMDF.getText());
        m.setCapacite(cap);
        m.setNumMoy(NumMDF.getText());
        ms.modifierTransport(m.getCapacite(), m.getType(), m.getMatricule(), m.getNumMoy(), m);
        //ms.modifierTransport(m.getCapacite(), m.getType(), m.getMatricule(), m.getNumMoy(), m)
        refreshTable();
        
        
        }
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
    if(tableMoyenne.getSelectionModel().getSelectedItem()==null){
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("veuillez sélectionner moyenne de transport dans le tableau !");
        alert.showAndWait();
    }else{
        Moyenstransport m = new Moyenstransport();
        int Id=Integer.parseInt(IDMDF.getText());
        m.setIdMoy(Id);
        ms.supprimer(m);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Moyenne de transport a ete supprimer avec succès !");
        alert.showAndWait();
        refreshTable();
    }
    }

    @FXML
    private void getSelected(MouseEvent event) {
    index = tableMoyenne.getSelectionModel().getSelectedIndex();
    if(index<=-1){
        return;
    }
    ObservableList<String> List = FXCollections.observableArrayList("Bus","Metro","Train");
    IDMDF.setText(IDColumn.getCellData(index).toString());
    TypeMDF.setValue(TypeColumn.getCellData(index).toString());
    MatMDF.setText(MatColumn.getCellData(index).toString());
    CapMDF.setText(CapaColumn.getCellData(index).toString());
    NumMDF.setText(Numcolumn.getCellData(index).toString());

    //for Old data
    OLDtype.setText(TypeColumn.getCellData(index).toString());
    OldMat.setText(MatColumn.getCellData(index).toString());
    OldCap.setText(CapaColumn.getCellData(index).toString());
    OldNum.setText(Numcolumn.getCellData(index).toString());
    }
    
    private void refreshTable(){
                List<Moyenstransport> MoyenTListe = ms.getAll();
        ObservableList<Moyenstransport> listeM = FXCollections.observableArrayList(MoyenTListe);
        IDColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,Integer>("idMoy"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("type"));
        MatColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("matricule"));
        CapaColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,Integer>("capacite"));
        Numcolumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("numMoy"));
        tableMoyenne.setItems(listeM);
    }

    @FXML
    private void AjouterBtn(ActionEvent event) {
        
        if ( TypeMDF.getSelectionModel().getSelectedItem()==null || MatMDF.getText().equals("") || CapMDF.getText().equals("") || NumMDF.getText().equals("")){
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Les champs sont obligatoire !");
        alert.showAndWait();
        return;
        }
        Moyenstransport m = new Moyenstransport();
        s=TypeMDF.getSelectionModel().getSelectedItem().toString();
        m.setType(s);
        m.setMatricule(MatMDF.getText());
        int cap = Integer.parseInt(CapMDF.getText());
        m.setCapacite(cap);
        m.setNumMoy(NumMDF.getText());
        ms.ajouter(m);
        //System.out.println(s);
        reset();
        refreshTable();
          
      
    
        }
    
     private void reset(){
        MatMDF.setText("");
        CapMDF.setText("");
        NumMDF.setText("");   
        }
    }
    

