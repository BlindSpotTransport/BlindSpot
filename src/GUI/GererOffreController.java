/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Evenement;
import java.io.IOException;
import javafx.fxml.Initializable;
import tools.MaConnection;
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



/**
 * FXML Controller class
 *
 * @author hp
 */
public class GererOffreController implements Initializable {
    
    
    Connection cnx;
    String sql="";
    Statement ste;
    
    
    public GererOffreController() {
         cnx = MaConnection.getInstance().getCnx();
       
    }
   
     @FXML
     private TableView<Evenement> offretab;
    @FXML
    private TableColumn<Evenement, String> id_col_offre;
    @FXML
    private TableColumn<Evenement, String> titrecol_off;
    @FXML
    private TableColumn<Evenement, String> descpcol_off;
    @FXML
    private TableColumn<Evenement, String> datedebcol_off;
    @FXML
    private TableColumn<Evenement, String>datefin_coloff;
    @FXML
    private TableColumn<Evenement, String> editcol;
    
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Evenement offre = null ;
    
    ObservableList<Evenement>  offretList = FXCollections.observableArrayList();
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadDate();
        // TODO
    }    


    private void refreshtable() {
  
        
        try {
            offretList.clear();
            
           
            query = "SELECT * FROM `evenement`";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            
            
            while (resultSet.next()){
                offretList.add(new  Evenement(
                        resultSet.getInt("id_eve"),
                        resultSet.getString("titre_eve"),
                         resultSet.getString("desc_eve"),
                        resultSet.getDate("date_deb_eve"),
                        resultSet.getDate("date_fin_eve")));
                offretab.setItems(offretList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GererOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }

   
     
    private void loadDate() {
        
         refreshtable();
        
        id_col_offre.setCellValueFactory(new PropertyValueFactory<>("id_eve"));
        titrecol_off.setCellValueFactory(new PropertyValueFactory<>("titre_eve"));
        descpcol_off.setCellValueFactory(new PropertyValueFactory<>("desc_eve"));
        datedebcol_off.setCellValueFactory(new PropertyValueFactory<>("date_deb_eve"));
        datefin_coloff.setCellValueFactory(new PropertyValueFactory<>("date_fin_eve"));
        
        
         //add cell of button edit 
         Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = (TableColumn<Evenement, String> param) -> {
            // make cell containing buttons
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
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
                            
                            try {
                                Evenement e = offretab.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `evenement` WHERE id_eve  ="+e.getId_eve();
                                //connection = DbConnect.getConnect();
                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.execute();
                                refreshtable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(GererOffreController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Evenement e= offretab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/AjouterOffre.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GererOffreController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AjouterOffreController ajouteroffreController = loader.getController();
                            ajouteroffreController.setUpdate(true);
                            ajouteroffreController.setTextField(e.getId_eve(), e.getTitre_eve(), 
                                    e.getDesc_eve(),e.getDate_deb_eve().toLocalDate(),e.getDate_fin_eve().toLocalDate());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

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
         editcol.setCellFactory(cellFoctory);
         offretab.setItems(offretList);
         
        
        
    }
    
    @FXML
    void ajouter(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AjouterOffre.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GererOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }
    @FXML
    void print(MouseEvent event) {

    }
    @FXML
    void refresh(MouseEvent event) {
        

    }


  
    
}
