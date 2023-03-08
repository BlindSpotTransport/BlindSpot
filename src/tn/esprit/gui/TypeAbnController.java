/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tn.esprit.entity.Typeabn;
import tn.esprit.services.TypeService;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class TypeAbnController implements Initializable {
    @FXML
    private TableView<Typeabn> tblType;

    @FXML
    private TableColumn<Typeabn,Integer> idaColumn2;
    @FXML
    private TableColumn<Typeabn,Integer> prixColumn;
    @FXML
    private TableColumn<Typeabn,String> dureeColumn;
    TypeService ts=new TypeService();
    List<Typeabn> plans =ts.getAll();
    @FXML
    private Button BackBtn;
    static boolean BoooleanAbn = false; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idaColumn2.setCellValueFactory(new PropertyValueFactory<>("idtypeA"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixA"));
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("dureeA"));
        tblType.setItems(FXCollections.observableArrayList(plans));
 
    }    

    @FXML
    private void retour(ActionEvent event) {
      try {
        BoooleanAbn=true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/PrincipalePage.fxml"));
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
    

