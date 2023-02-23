/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entity.Reservation;
import tn.esprit.services.ReservationService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ConsulterReservationController implements Initializable {

    @FXML
    private TableColumn<Reservation, Integer> IDcol;
    @FXML
    private TableColumn<Reservation, Time> dateRcol;
    @FXML
    private TableColumn<Reservation, Time> heureDepCol;
    @FXML
    private TableColumn<Reservation, Time> heureArrCol;
    @FXML
    private TableColumn<Reservation, String> typeCol;
    @FXML
    private TableColumn<Reservation, Integer> CinCol;
    @FXML
    private TableColumn<Reservation, Integer> prixCol;

    ReservationService rs= new ReservationService();
    @FXML
    private TableView<Reservation> tableR;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Reservation> ReservationL = rs.getAll();
        ObservableList<Reservation> listeR = FXCollections.observableArrayList(ReservationL);
        IDcol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idNum"));
        dateRcol.setCellValueFactory(new PropertyValueFactory<Reservation,Time>("dateR"));
        heureDepCol.setCellValueFactory(new PropertyValueFactory<Reservation,Time>("heureDep"));
        heureArrCol.setCellValueFactory(new PropertyValueFactory<Reservation,Time>("heureArr"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("type"));
        CinCol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("cin"));
        prixCol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("prix"));
        tableR.setItems(listeR);
    }    
    
}
