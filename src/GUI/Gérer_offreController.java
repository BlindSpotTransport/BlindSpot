package GUI;

import Service.OffresService;
import entites.Offres;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Gérer_offreController implements Initializable {

    @FXML
    private Button btn_ajout_offre;

    @FXML
    private Button btn_modifier_offre;

    @FXML
    private Button btn_supprimer_offre;

    @FXML
    private DatePicker date_debut;

    @FXML
    private TableColumn<?, ?> date_debutCol;

    @FXML
    private DatePicker date_fin;

    @FXML
    private TableColumn<?, ?> date_finCol;

    @FXML
    private TableColumn<?, ?> decpcol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TextField idForm_offre;

    @FXML
    private TextField id_budget;

    @FXML
    private TextField id_desc;

    @FXML
    private TextField id_offre;

    @FXML
    private ComboBox proprietaire;

    @FXML
    private Label registrationerrormsg;

    @FXML
    private TableColumn<?, ?> statutcol;

     @FXML
    private ComboBox status;

    @FXML
    private TableView<?> tab_affiche_offre;

    @FXML
    private TextField titre_offre;

    @FXML
    private TableColumn<?, ?> titrecol;
    private String selectedStatut;
    
    OffresService offreService=new OffresService();
    
        Gérer_offreController(){
             this.status=new ComboBox();
             this.status.getItems().add("Offre_En_cours");
             this.status.getItems().add("Offre_Confirmé");
             this.status.getItems().add("Offre_Annulé");
             
             this.proprietaire=new ComboBox();
        }
        
    @FXML
    void selectedStatus(ActionEvent event) {
        String s = status.getSelectionModel().getSelectedItem().toString();
       this.selectedStatut=s;
    }   
    
    @FXML
    void TrainerEvent(ActionEvent event) {

    }

    @FXML
    void afifche_prop(ActionEvent event) {

    }

    @FXML
    void ajouteroffre(ActionEvent event) {
       this.verifierOffreForm();
    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void rowClicked(MouseEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {

    }
    //controle de saisie de formulaire 
    void verifierOffreForm(){
      //verification 
      if(this.id_budget.getText().length()>0 && this.titre_offre.getText().length()>0 && this.id_desc.getText().length()>0 ){
           java.sql.Date ddd=new java.sql.Date(this.date_debut.getValue().getYear());
           Offres offre = new Offres(1,3,Float.parseFloat(this.id_budget.getText())," ",this.selectedStatut,this.titre_offre.getText(),this.id_desc.getText(),ddd,ddd);
           System.out.println("date debut "+ddd);
           offreService.ajouter(offre);
      }else{
            System.out.println("champs vide !!!! ");
      }
      
      
    
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
