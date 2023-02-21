/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.gui;

import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import taktak.entity.Abonnement;
import taktak.services.AbnService;


/**
 *
 * @author 21626
 */
public class AjouterAbnController implements Initializable {

    @FXML
    private TextField PromoFld;
    @FXML
    private RadioButton RadioBtn;
    @FXML
    private ChoiceBox<String> ChoiceBx;
    private String[] moyTr = {"Bus","Métro","Train"};
    @FXML
    private ImageView ImgFld;
    @FXML
    private Button AddBtn;
    AbnService as=new AbnService();
    @FXML
    private Label control;
    @FXML
    private Label control2;

    private Parent root;
    @FXML
    private Button NextBtn;
    //Abonnememt ab=new Abonnement();
    private Scene scene;
    private Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBx.getItems().addAll(moyTr);
        
		
	}

    @FXML
    private void ajouterAbn(ActionEvent event){
        if (ChoiceBx.getSelectionModel().isEmpty())
        {control.setText("Ce champ est obligatoire");}
        else{
            Abonnement a= new Abonnement();
          
            a.setIdU(1);
            a.setMoyTrA(ChoiceBx.getValue());
            verifEtudiant(a);
            verifPromo(a);
            as.add2(a);
            reset();

    }

    }

    private void reset() {
        PromoFld.setText("");
        RadioBtn.setSelected(false);
        ChoiceBx.getSelectionModel().clearSelection();
        
    }	
    
    
    private void verifEtudiant(Abonnement a) {
        if(RadioBtn.isSelected()){
            a.setEtudiantA(true);
            a.setRedEtA(20);
        }else{
            a.setEtudiantA(false);
            a.setRedEtA(0);
        }
    }
    
    private void verifPromo(Abonnement a) {
        if(PromoFld.getText().isEmpty()){a.setRedEvA(0);}
     /*   else if(PromoFld.getText()=="ouverture")
        {a.setRedEvA(50);}*/
        else{control2.setText("Code invalide"); }
    }



    @FXML
    private void pageSuiv(ActionEvent event) throws IOException {
        
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTA.fxml"));	
            root = loader.load();	
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    }
    

