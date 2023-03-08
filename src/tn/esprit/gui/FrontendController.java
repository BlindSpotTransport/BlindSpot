/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import tn.esprit.entity.Abonnement;
import tn.esprit.entity.Typeabn;
import tn.esprit.services.AbnService;
import tn.esprit.services.TypeService;
import tn.esprit.services.UsersSession;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class FrontendController implements Initializable {

    @FXML
    private ChoiceBox<String> ChoiceBx;
    @FXML
    private RadioButton RadioBtn;
    @FXML
    private TextField PromoFld;
    @FXML
    private ChoiceBox<String> ChoiceBx2;
    @FXML
    private Label control;
    @FXML
    private Label control2;
    @FXML
    private Button ReadBtn1;
    private String[] moyTr = {"Bus", "Métro", "Train"};
    private String[] type = {"Mensuel", "Semestriel", "Annuel"};
    AbnService as = new AbnService();
    TypeService ts = new TypeService();
    LocalDate currentDate = LocalDate.now();
    List<Typeabn> typesabn = ts.getAll();
    Abonnement a = new Abonnement();
    Connection cnx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Settings ChoiceBox
        ChoiceBx.getItems().addAll(moyTr);
        ChoiceBx2.getItems().addAll(type);

    }
    public FrontendController(){
                cnx = MaConnection.getInstance().getCnx();

    }

    //  A helper method to get the Typeabn by id
    private Typeabn getTypeabnById(int id) {
        for (Typeabn typeabn : typesabn) {
            if (typeabn.getIdtypeA() == id) {
                return typeabn;
            }
        }
        return null;
    }

    @FXML
    private void ajouterAbn(ActionEvent event) throws SQLException {
//           Abonnement a=new Abonnement();
//           Typeabn as=new Typeabn();

        if (ChoiceBx.getSelectionModel().isEmpty() || ChoiceBx2.getSelectionModel().isEmpty()) {
            control.setText("les champs moyen transport et plan sont obligatoires");
            return;
        } else {

            //  a.setIdU(1);
            a.setMoyTrA(ChoiceBx.getValue());
            verifEtudiant(a);
            verifPromo(a);

        }
if(!control2.getText().equals("Code invalide")){
        if (ChoiceBx2.getValue().equals("Mensuel")) {
            a.setIdtypeA(1);

            a.setDateExpA(currentDate.plusMonths(1));

        } else if (ChoiceBx2.getValue().equals("Semestriel")) {
            a.setIdtypeA(2);
            a.setDateExpA(currentDate.plusMonths(6));
        } else if (ChoiceBx2.getValue().equals("Annuel")) {
            a.setIdtypeA(3);
            a.setDateExpA(currentDate.plusYears(1));
        }

        as.add(a);
        
        try {
            String UpdateN = "update utilisateur set abonnéU = 1 where idU = '"+UsersSession.getIdU()+"' ";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(UpdateN);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        

        reset();
        // create and show the notification
        Notifications.create()
                .title("Abonnement ajouté")
                .text("L'abonnement a été ajouté avec succès.")
                .showInformation();
        //  JOptionPane.showMessageDialog(null, "Abonnement ajouté avec succès!"); 
}else{
    return;
}
    }

    private void reset() {
        PromoFld.setText("");
        RadioBtn.setSelected(false);
        ChoiceBx.getSelectionModel().clearSelection();
        ChoiceBx2.getSelectionModel().clearSelection();

    }

    private void verifEtudiant(Abonnement a) {
        if (RadioBtn.isSelected()) {
            a.setEtudiantA(true);
            a.setRedEtA(20);
        } else {
            a.setEtudiantA(false);
            a.setRedEtA(0);
        }
    }

    private void verifPromo(Abonnement a) {
        if (!PromoFld.getText().isEmpty()) {
                        control2.setText("Code invalide");
           
        } /*   else if(PromoFld.getText()=="ouverture")
        a.setRedEvA(50);}*/ else { a.setRedEvA(0);

        }
       
    }

}
