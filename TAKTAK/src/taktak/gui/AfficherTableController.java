/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
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
import taktak.entity.Abonnement;
import taktak.services.AbnService;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class AfficherTableController implements Initializable {

    @FXML
    private TableColumn<Abonnement,Integer> idaColumn;
    @FXML
    private TableColumn<Abonnement,Integer> iduColumn;
    @FXML
    private TableColumn<Abonnement, String> moyenColumn;
    @FXML
    private TableColumn<Abonnement,Date> dateColumn;
    @FXML
    private TableColumn<Abonnement,Date> dateExpColumn;
    @FXML
    private TableColumn<Abonnement,String> dureeColumn;
    @FXML
    private TableColumn<Abonnement,Integer> prixColumn;
    @FXML
    private TableColumn<Abonnement,Boolean> etudColumn;
    @FXML
    private TableColumn<Abonnement,Integer> redColumn;
    @FXML
    private TableColumn<Abonnement,Integer> promoColumn;
    AbnService as=new AbnService();
    @FXML
    private TableView<Abonnement> tblAbn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<Abonnement,String> imgColumn;
    private Abonnement abn;
    @FXML
    private TextField searchBar;
    @FXML
    private Button srchBtn;
    List<Abonnement> abonnements =as.getAll();  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       // List<Abonnement> abonnements =as.getAll();       
        //ObservableList<Abonnement> listAbn = FXCollections.observableArrayList(abonnements);
        idaColumn.setCellValueFactory(new PropertyValueFactory<>("idA"));
        iduColumn.setCellValueFactory(new PropertyValueFactory<>("idU"));
        moyenColumn.setCellValueFactory(new PropertyValueFactory<>("moyTrA"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateA" ));
        imgColumn.setCellValueFactory(new PropertyValueFactory<>("imageA" ));
        etudColumn.setCellValueFactory(new PropertyValueFactory<>("etudiantA"));
        redColumn.setCellValueFactory(new PropertyValueFactory<>("redEtA"));
        promoColumn.setCellValueFactory(new PropertyValueFactory<>("redEvA"));
        tblAbn.setItems(FXCollections.observableArrayList(abonnements));

    }

   @FXML
    private void supprimerAbn(ActionEvent event) {
 
        Abonnement a=new Abonnement();
        if(tblAbn.getSelectionModel().getSelectedItems()!=null)
        {   int x=Integer.parseInt(idaColumn.getText());
            as.findById(x);
            as.delete(a); }}

      

   /*@FXML
    private void rechercherAbn(ActionEvent event) {
       abonnements.getItems().clear();
       abonnements.getItems().addAll(searchList(searchBar.getText(),abonnements));
    }
        private List<String> searchList( String searchWords,List<Abonnement> listOfAbn) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfAbn.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }*/

 
    
}
