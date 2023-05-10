/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import tn.esprit.entity.Circuit;
import tn.esprit.entity.Station;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.services.ServiceCircuit;
import tn.esprit.services.ServiceStation;
import tn.esprit.tools.MaConnection;

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
    @FXML
    private TextField TextRechercher;
    @FXML
    private TableColumn<Station, String> circuitCol;
    private TextField TextCircuit;
    @FXML
    private ComboBox<String> circuitCobbox;
    ServiceCircuit sc=new ServiceCircuit();
    ObservableList<Station> statList;  
    
    Connection cnx;
    @FXML
    private ImageView station;
    @FXML
    private AnchorPane Anch;

    public AjouterStationController() {
             cnx = MaConnection.getInstance().getCnx();
    }
    
   
            
            
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        ImageView imageView = new ImageView(new Image("C:\\Users\\pc\\Downloads\\Final\\TakTak_Final\\src\\tn\\esprit\\gui\\images\\background.jpg"));
//        imageView.setPreserveRatio(false);
//        
//        imageView.fitHeightProperty().bind(Anch.widthProperty());
//        imageView.fitHeightProperty().bind(Anch.heightProperty());
//        Anch.getChildren().add(imageView);
FileInputStream inputstream;
        try {
            inputstream = new FileInputStream("C:\\\\Users\\\\pc\\\\Downloads\\\\Final\\\\TakTak_Final\\\\src\\\\tn\\\\esprit\\\\gui\\\\images\\\\background.jpg");  
            Image trainImage = new Image(inputstream); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterStationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 


        List<Station> stat = ss.getAll();
         statList = FXCollections.observableArrayList(stat);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("idStation"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomS"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       //circuitCol.setCellValueFactory(new PropertyValueFactory<>("Cir"));
        table.setItems(statList);
        
         circuitCol.setCellValueFactory(c -> 
                 new SimpleStringProperty(c.getValue().getCir().getNomC()));
        //Circuit c = new Circuit();
        List<String> cir = sc.getNomC();
        ObservableList<String> ListCir = FXCollections.observableArrayList(cir);
        circuitCobbox.setItems(ListCir);
    }    

    @FXML
    private void AjouterAction(ActionEvent event) {
        if (TextNom.getText().equals("") && TextAdresse.getText().equals("") 
                && TextAdresse.getText().equals("")){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("les champs sont obligatoire !");
           alert.showAndWait();
        }else{
      try{
        Station s= new Station();
        //c.setIdCircuit((ifid.getText()));
        s.setNomS(TextNom.getText());
        s.setAdresse(TextAdresse.getText());

       String cs = circuitCobbox.getValue().toString();
           // System.out.println(cs);
       ObservableList<Integer> ID = FXCollections.observableArrayList();
       String sql = "select idCircuit from circuit where '"+cs+"' = nomC";
             
           Statement st = cnx.createStatement();
            ResultSet r = st.executeQuery(sql);
                        //  System.out.println(rs);

           while (r.next()) {           
                int id = r.getInt("idCircuit");
               // Circuit c = new Circuit();
               Integer ids = new Integer(id);
       ID.add(ids);
        }
                System.out.println(ID);
            int idFINAL=ID.get(0);
            
     
//System.out.println(this.circuitCobbox.getValue().toString());
                   Circuit c = new Circuit();
                   //int idd=ss.getID();
       c.setIdCircuit(idFINAL);
       s.setCircuit(c);
       ss.ajouterStation(s);  
        reset();
        refresh();
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
           
           
        
        }
    }
 
    @FXML
    private void ModifierAction(ActionEvent event) {
         int idcc = 0;
        if (table.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("s'il vous plait selectionnez une station !");
           alert.showAndWait();
        }else{
        Station s= new Station();
        //c.setDepartC(TextDepart.getText());
        //System.out.println(c.getDepartC());
        //c.setArriveeC(TextArrivee.getText()); 
       
       // c.getIdCircuit(ifid.getText());
        
                //System.out.println(x);
                
        s.setNomS(TextNom.getText());
       // System.out.println(TextNom.getText());
        // String nom=s.getNomS();
        s.setAdresse(TextAdresse.getText());
       // System.out.println(TextAdresse.getText());
       // String adresse=s.getAdresse();  
       String nomC=circuitCobbox.getValue().toString();
           // System.out.println(nomC);
           
         try {
            String req = "select idCircuit from circuit where nomC= '"+nomC+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) { 
              idcc= rs.getInt("idCircuit");
                
            }
             
          
             
         
         } catch (SQLException ex) {
                Logger.getLogger(AjouterStationController.class.getName()).log(Level.SEVERE, null, ex);
            }
         Circuit c = new Circuit();
         c.setIdCircuit(idcc);
        s.setCircuit(c);
      int x=Integer.parseInt(ifid.getText());
            System.out.println(x);
       s.setIdStation(x);
        
       ss.modifierStation(s,x);
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
           alert.setContentText("s'il vous plait selectionnez une station !");
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
        refresh();
    }
    }
    private void reset() {
      
      TextNom.setText("");
      TextAdresse.setText("");
      circuitCobbox.getValue();
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
        circuitCobbox.setValue(circuitCol.getCellData(index).toString());
        
    }
    private void refresh(){
         List<Station> stat = ss.getAll();
        ObservableList<Station> listStat = FXCollections.observableArrayList(stat);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdStation"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomS"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      //  circuitCol.setCellValueFactory(new PropertyValueFactory<>("circuit"));
      circuitCol.setCellValueFactory(c -> 
                 new SimpleStringProperty(c.getValue().getCir().getNomC().toString()));
        table.setItems(listStat);
         }

    @FXML
     private void RechercherAction(ActionEvent event) {
         if (TextRechercher.getText().equals("")) {
       // if (TextRecherche== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("les champs sont obligatoire !");
           alert.showAndWait();
        }else{
             String nomS = TextRechercher.getText();
             
//         Station stat = ss.findByNomS(nomS);
          List<Station> stat = ss.findByNomS(nomS);
        ObservableList<Station> listStat = FXCollections.observableArrayList(stat);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("IdStation"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomS"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        circuitCol.setCellValueFactory(new PropertyValueFactory<>("circuit"));
//         circuitCol.setCellValueFactory(c -> 
//                 new SimpleStringProperty(c.getValue().getCir().getNomC()));
        table.setItems(listStat);
        
       
    }
    }
//      public void getCombo() {
//         System.out.print(this.circuitCobbox.getValue().toString());
//    }
//    public AjouterStationController(String c){
//    c=this.circuitCobbox.getValue().toString();
//}

    @FXML
    private void Rechercher(KeyEvent event) {
           String nom1 = "";
    if (event.getText().length()>0)
        nom1 = TextRechercher.getText()+ event.getText();
    else
        nom1 = TextRechercher.getText().substring(0,TextRechercher.getText().length()-1 );
    System.out.println(nom1);
    String nom = nom1.toLowerCase();
    ObservableList<Station> list =  statList.stream()
        .filter(r -> r.getNomS().toLowerCase().contains(nom)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    table.setItems(list);
    }
   
}
