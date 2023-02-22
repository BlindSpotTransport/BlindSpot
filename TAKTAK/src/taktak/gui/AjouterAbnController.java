/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.gui;

import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
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
import taktak.entity.TypeAbn;
import taktak.services.AbnService;
import taktak.services.TypeService;


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
    AbnService as=new AbnService();
    TypeService ts=new TypeService();
    @FXML
    private Label control;
    @FXML
    private Label control2;

    private Parent root;
    //Abonnememt ab=new Abonnement();
    private Scene scene;
    private Stage stage;
    @FXML
    private ImageView ImgFld1;
    @FXML
    private Button PlanMBtn;
    @FXML
    private Button PlanSBtn;
    @FXML
    private Button PlanABtn;
    @FXML
    private Label control3;
    private RadioButton rBtn1;
    @FXML
    private Button ReadBtn;
    private RadioButton rbtn1;
    private RadioButton rbtn2;
    private RadioButton rbtn3;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBx.getItems().addAll(moyTr);
        
		
	}

    @FXML
    private void ajouterAbn(ActionEvent event){
         Abonnement a= new Abonnement();
         TypeAbn t=new TypeAbn();
        if (ChoiceBx.getSelectionModel().isEmpty())
        {control.setText("Ce champ est obligatoire");}
        else{

          
            a.setIdU(1);
            a.setMoyTrA(ChoiceBx.getValue());
            verifEtudiant(a);
            verifPromo(a);
            t.setIdA(a.getIdA());
            t.setIdU(a.getIdU());
            ajouterPlan(a,t);
            as.add2(a);
            ts.add(t);
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

private void ajouterPlan(Abonnement a,TypeAbn t) {
    if (PlanMBtn.isPressed()) {
        //TypeAbn t = new TypeAbn();
        //t.setIdA(a.getIdA());
        //t.setIdU(a.getIdU());
        t.setPrixA(5);
        t.setDureeA("Mensuel");
        t.setDateExpA(addDurationToDate(a.getDateA(), Calendar.MONTH, 1));
        //ts.add(t);
    } else if (PlanSBtn.isPressed()) {
        //TypeAbn t = new TypeAbn();
        //t.setIdA(a.getIdA());
        //t.setIdU(a.getIdU());
        t.setPrixA(10);
        t.setDureeA("Semestriel");
        t.setDateExpA(addDurationToDate(a.getDateA(), Calendar.MONTH, 6));
        //ts.add(t);
    } else if (PlanABtn.isPressed()) {
        //TypeAbn t = new TypeAbn();
        //t.setIdA(a.getIdA());
       // t.setIdU(a.getIdU());
        t.setPrixA(15);
        t.setDureeA("Annuel");
        t.setDateExpA(addDurationToDate(a.getDateA(), Calendar.YEAR, 1));
        //ts.add(t);
    } else {
        control3.setText("Veillez Choisir un Plan");
    }
}

private Date addDurationToDate(Date date, int field, int amount) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(field, amount);
    return (Date) calendar.getTime();
}



    
 

    }
    

