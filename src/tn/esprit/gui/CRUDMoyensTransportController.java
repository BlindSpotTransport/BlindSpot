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
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.management.Notification;
import javax.swing.JOptionPane;
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
    ObservableList<Moyenstransport> listeM ;
    
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
    @FXML
    private TextField recherche;
    @FXML
    private Button addID;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Moyenstransport> MoyenTListe = ms.getAll();
        listeM = FXCollections.observableArrayList(MoyenTListe);
        IDColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,Integer>("idMoy"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("type"));
        MatColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("matricule"));
        CapaColumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,Integer>("capacite"));
        Numcolumn.setCellValueFactory(new PropertyValueFactory<Moyenstransport,String>("numMoy"));
        tableMoyenne.setItems(listeM);
        
        
        
        
        ObservableList<String> List = FXCollections.observableArrayList("Bus","Metro","Train");
        TypeMDF.setItems(List);
        //TypeMDF.getSelectionModel().select(0);
                addID.setId("addId");
                
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
    }else  if ( TypeMDF.getSelectionModel().getSelectedItem()==null || MatMDF.getText().equals("") || CapMDF.getText().equals("") || NumMDF.getText().equals("")){
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Les champs sont obligatoire !");
        alert.showAndWait();
        return;
        }
    else{    
        Moyenstransport m = new Moyenstransport();
        
        int Id=Integer.parseInt(IDMDF.getText());
        m.setIdMoy(Id);
        if (MatMDF.getText().toString().length()<=15 ){
                    m.setMatricule(MatMDF.getText());
        }else{ 
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("le nembre de caractere de matricule doit etre inferieur ou égale a 15");
        alert.showAndWait();
        return;
        }
       // System.out.println(s);
        String s=TypeMDF.getSelectionModel().getSelectedItem().toString();
        m.setType(s);
        m.setMatricule(MatMDF.getText());
          if(CapMDF.getText().length()<11){
        int cap=Integer.parseInt(CapMDF.getText());
        m.setCapacite(cap);
          }else{
              Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("capacite est tres grande !");
        alert.showAndWait();
        return;
          }
        if (NumMDF.getText().toString().length()<=10 ){
        m.setNumMoy(NumMDF.getText());
        }else{
            Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("le nembre de caractere de numeor transport doit etre inferieur ou égale a 10");
        alert.showAndWait();
         return;
        }
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
        //System.out.println( ms.RechercherString(MatMDF.getText().toString(), "matricule"));
        if (MatMDF.getText().toString().length()<=15 ){
        if(ms.RechercherString(MatMDF.getText().toString(), MatColumn.getText().toString())){
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Ce matricule existe déjà dans la base de données");
        alert.showAndWait();
         return;
        }
        }else{
         Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("le nembre de caractere de matricule doit etre inferieur ou égale a 15");
        alert.showAndWait();
        return;
        }
        if(CapMDF.getText().length()<11){
        try{
        int cap = Integer.parseInt(CapMDF.getText());
        m.setCapacite(cap);
        }catch(NumberFormatException e){
           Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Les champs de capacité doit etre un nembre!");
        alert.showAndWait();
        return;
        }
        }else{
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("capacite est tres grande !");
        alert.showAndWait();
        return;
        }
         if (NumMDF.getText().toString().length()<=10 ){
        if(ms.RechercherString(NumMDF.getText().toString(), "numMoy")){
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Ce numéro de transport existe déjà dans la base de données !");
        alert.showAndWait();
         return;
        }
        }else{  
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("le nembre de caractere de numeor transport doit etre inferieur ou égale a 10");
        alert.showAndWait();
         return;
         }
        
        s=TypeMDF.getSelectionModel().getSelectedItem().toString();
        m.setType(s);
        m.setMatricule(MatMDF.getText());
        m.setNumMoy(NumMDF.getText());
        
        ms.ajouter(m);
        //System.out.println(s);
        reset();
        refreshTable();
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText(null);
        alert.setContentText("Moyenne de transport ajoutée avec succées !");
        alert.showAndWait();
        }
    
     private void reset(){
        MatMDF.setText("");
        CapMDF.setText("");
        NumMDF.setText("");   
        }

    @FXML
    private void RechercherTAB(KeyEvent event) {
    String nom1 = "";
    if (event.getText().length()>0)
        nom1 = recherche.getText()+ event.getText();
    else
        nom1 = recherche.getText().substring(0,recherche.getText().length()-1 );
    System.out.println(nom1);
    String nom = nom1.toLowerCase();
    ObservableList<Moyenstransport> filterMoy =  listeM.stream()
        .filter(r -> r.getMatricule().toLowerCase().contains(nom)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    tableMoyenne.setItems(filterMoy);
    
    }
    }
    
   