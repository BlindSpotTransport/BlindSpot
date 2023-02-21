/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.gui;

import java.net.URL;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import taktak.entity.TypeAbn;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import taktak.entity.Abonnement;
import taktak.services.TypeService;

/**
 *
 * @author 21626
 */
public class AjouterTAController implements Initializable{

    @FXML
    private Button PlanMBtn;
    @FXML
    private Button PlanSBtn;
    @FXML
    private Button PlanABtn;
    @FXML
    private Label control3;
    TypeService ts=new TypeService();
    //TypeAbn t=new TypeAbn();
    //Abonnement ab=new Abonnement();
    @FXML
    private Button PrvBtn1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 /*   public int AjoutObjAbn(Abonnement ab){
        int a = ab.getIdA();
        int b = ab.getIdU();
        return (a,b);
    }
    */
    @FXML
    private void ajouterPlanM(ActionEvent event) {
        Abonnement ab=new Abonnement();
        
        ts.plan1(ab);
        
    }

 /*  @FXML
    private void ajouterPlanS(ActionEvent event) {
    plan2(ab);

    }
*/
    @FXML
    private void ajouterPlanA(ActionEvent event) {
        Abonnement ab=new Abonnement();
    
        ts.plan3(ab);
    }



}
