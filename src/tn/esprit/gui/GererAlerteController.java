/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.services.AlerteService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import tn.esprit.entity.Alerte;
import tn.esprit.entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class GererAlerteController implements Initializable {
    
    Connection cnx;
    String sql="";
    Statement ste;
   
    
    public GererAlerteController() {
         cnx = MaConnection.getInstance().getCnx();
       
    }
    
    @FXML
    private TableColumn<Alerte, String> tiypecol_alerte;

    @FXML
    private TableView<Alerte> tabalerte;
    @FXML
    private TableColumn<Alerte, String> idcol_alerte;
    @FXML
    private TableColumn<Alerte, String> titrecol_alerte;
    @FXML
    private TableColumn<Alerte,String> desceiptioncol_alerte;
    @FXML
    private TableColumn<Alerte, String> datedebut_alertecol;
    @FXML
    private TableColumn<Alerte,String> datefin_alertecol;
    @FXML
    private TableColumn<Alerte, String> mod_supalerte;
    
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Alerte alerte = null ;
    
    ObservableList<Alerte>  alerteList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        loadDate();
        
        
        // TODO
    }   
    
    //recuperer les donnes 
    private void refreshtable() {
        // affichage
        
//        AlerteService as = new AlerteService();
//        as.afficher();
//        tabalerte.setItems(alerteList);
        
        
        try {
            alerteList.clear();
            
           
            query = "SELECT * FROM `alerte`";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
           
            
            
            while (resultSet.next()){
                alerteList.add(new  Alerte(
                        resultSet.getInt("id_alerte_eve"),
                        resultSet.getString("type_alerte_eve"),
                        resultSet.getString("titre_eve"),
                        resultSet.getString("desc_eve"),
                        resultSet.getDate("date_deb_eve"),
                        resultSet.getDate("date_fin_eve")));
                tabalerte.setItems(alerteList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GererAlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ajouter(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterAlerte.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GererAlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(MouseEvent event) {
        refreshtable();
        
    }
    
    
   

    @FXML
    private void print(MouseEvent event) {
    }
     // afficher
    // takhou colonne w thotna valeur mtaaha bi id 
    private void loadDate() {
        refreshtable();
      
        //idcol_alerte.setCellValueFactory(new PropertyValueFactory<>("id_alerte_eve"));
        tiypecol_alerte.setCellValueFactory(new PropertyValueFactory<>("type_alerte_eve"));
        titrecol_alerte.setCellValueFactory(new PropertyValueFactory<>("titre_eve"));
        desceiptioncol_alerte.setCellValueFactory(new PropertyValueFactory<>("desc_eve"));
        datedebut_alertecol.setCellValueFactory(new PropertyValueFactory<>("date_deb_eve"));
        datefin_alertecol.setCellValueFactory(new PropertyValueFactory<>("date_fin_eve"));
        
        
        // ken fergha colone lekhra test null ki tabda masna3t chaay sinn bch nasnou les icon delete,modif
         Callback<TableColumn<Alerte, String>, TableCell<Alerte, String>> cellFoctory = (TableColumn<Alerte, String> param) -> {
           
            final TableCell<Alerte, String> cell = new TableCell<Alerte, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment Supprimer cet Alerte ?", "Confirmation de Suppression", JOptionPane.YES_NO_OPTION);

                                if (confirmation == JOptionPane.YES_OPTION) {
                                     try {
                                //récuperation de donnes pour faire modification et supp 
                                Alerte a = tabalerte.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `alerte` WHERE id_alerte_eve  ="+a.getId_alerte_eve();
                                //connection = DbConnect.getConnect();
                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.execute();
                                refreshtable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(GererAlerteController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                   
                                } else {
                                   // Annuler la modification
                                }

                        });
                        
                        // modifier
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment Modifier cette Alerte ?", "Confirmation de Modification", JOptionPane.YES_NO_OPTION);

                                if (confirmation == JOptionPane.YES_OPTION){  
                            Alerte a= tabalerte.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AjouterAlerte.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GererAlerteController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            // true pour faire update 
                            AjouterAlerteController ajouteralerteController = loader.getController();
                            ajouteralerteController.setUpdate(true);
                            ajouteralerteController.setTextField(a.getId_alerte_eve(), a.getType_alerte_eve(), a.getTitre_eve(),
                                    a.getDesc_eve(),a.getDate_deb_eve().toLocalDate(),a.getDate_fin_eve().toLocalDate());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           
                                }
                        });
                                
                                
                            
                            
                            

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         //akher colonne edit ,supp 
         mod_supalerte.setCellFactory(cellFoctory);
         tabalerte.setItems(alerteList);
         
        
        
        
    }
    
}



