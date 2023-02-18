/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.PersonneService;
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entites.Personne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjoutPersonneController implements Initializable {
    PersonneService ps = new PersonneService();

    @FXML
    private TextField nom_personne;
    @FXML
    private TextField nom_prenom;
    @FXML
    private TextField id_age;
    @FXML
    private Button id_ajouter;
    @FXML
    private Button id_afficher;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        Personne p = new Personne();
        p.setAge(Integer.parseInt(id_age.getText()));
        p.setNom(nom_personne.getText());
        p.setPrenom(nom_prenom.getText());
    
        ps.ajouter(p);
        reset();
             }
                
    

    @FXML
    private void Afficher(ActionEvent event) {
      
        //navigation
        Parent loader;
        try {
            loader = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                nom_personne.getScene().setRoot(loader);
        } catch (IOException ex) {
            Logger.getLogger(AjoutPersonneController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
          
                
        
    }
    private void reset() {
    nom_personne.setText("");
    id_age.setText("");
    nom_personne.setText("");
 
}

}
    
    
    

    

    

