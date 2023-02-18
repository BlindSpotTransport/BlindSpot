/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.services.MoyenstransportService;

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
    
    int index=-1 ;
    MoyenstransportService ms = new MoyenstransportService();
    
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
   


    }    

    @FXML
    private void ModifierAction(ActionEvent event) {
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    index = tableMoyenne.getSelectionModel().getSelectedIndex();
    if(index<=-1){
        return;
    }
    IDMDF.setText(IDColumn.getCellData(index).toString());
    TypeMDF.setText(TypeColumn.getCellData(index).toString());
    MatMDF.setText(MatColumn.getCellData(index).toString());
    CapMDF.setText(CapaColumn.getCellData(index).toString());
    NumMDF.setText(Numcolumn.getCellData(index).toString());

    
    }
    
    }
    
}
