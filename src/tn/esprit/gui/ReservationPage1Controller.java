/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.entity.User;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Window;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import sun.java2d.pipe.SpanShapeRenderer;
import tn.esprit.entity.Adresse;
import tn.esprit.entity.Circuit;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.entity.Planning;
import tn.esprit.entity.Reservation;
import tn.esprit.services.PlanningService;
import tn.esprit.services.ReservationService;
import tn.esprit.services.SendMail;
import tn.esprit.services.SendSms;
import tn.esprit.services.UsersSession;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ReservationPage1Controller implements Initializable {

    @FXML
    private ComboBox<String> ComboDepart;
    @FXML
    private ComboBox<String> ComboArr;
    @FXML
    private ComboBox<String> ComboType;

    Connection cnx;
    @FXML
    private ListView<Planning> listViewReference = new ListView<Planning>();
    @FXML
    private TextField idMoy;
    @FXML
    private TextField idCir;
    @FXML
    private TextField DateD;
    @FXML
    private Button buttonReser;
    String HeureArr;
    String Type;
    String prix;
    String NUM;
    @FXML
    private Button OFFRE;
    public ReservationPage1Controller() {
        cnx = MaConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ObservableList<String> CircuitDep = FXCollections.observableArrayList();
            String SqlGetCircuitDepart = "SELECT DISTINCT departC FROM circuit";
            Statement ste = cnx.createStatement();
            ResultSet dep = ste.executeQuery(SqlGetCircuitDepart);
            while (dep.next()) {
                String depart = dep.getString("departC");
                String p2 = new String(depart);
                CircuitDep.add(p2);
            }
            List<String> DEP = new ArrayList<>();
            for (String CDep : CircuitDep) {
                //System.out.println(CDep.getDepartCircuit());
                DEP.add(CDep);
            }
            ObservableList<String> ListtD = FXCollections.observableArrayList(DEP);
            ComboDepart.setItems(ListtD);

            // Pour remplir comboBOX de Circuit  d'arriver
            //CirDepComboBox
            ObservableList<String> CircuitArr = FXCollections.observableArrayList();
            String SqlGetCircuitArriver = "SELECT DISTINCT arriveeC FROM circuit";
            ResultSet arr = ste.executeQuery(SqlGetCircuitArriver);
            while (arr.next()) {
                String arriver = arr.getString("arriveeC");
                String p2 = new String(arriver);
                CircuitArr.add(p2);
            }
            ComboArr.setItems(CircuitArr);

            // pour remplir comboBOX de type de transport
            ObservableList<String> TypeCir = FXCollections.observableArrayList();
            String SqlGetTransportType = "SELECT DISTINCT type from moyenstransport";
            ResultSet trans = ste.executeQuery(SqlGetTransportType);
            while (trans.next()) {
                String type = trans.getString("type");
                TypeCir.add(type);
            }
            ComboType.setItems(TypeCir);

        } catch (SQLException ex) {
            Logger.getLogger(ReservationPage1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RechercherBtn(ActionEvent event) throws FileNotFoundException {
        PlanningService pl = new PlanningService();

        String CircuitDepart = ComboDepart.getValue().toString();
        String CircuitArr = ComboArr.getValue().toString();
        String TypeT = ComboType.getValue().toString();

        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat formatteur = new SimpleDateFormat("HH:mm:ss");
            String Timenow = formatteur.format(date);

            Date parsedDate = formatteur.parse(Timenow);

            long dt = parsedDate.getTime();

            Time TimeNow = new Time(dt);

            List<Planning> Plan = new ArrayList<>();

            String SqlJointurePlanning = "select  P.idMoy as Moy, P.idCir as Cir, C.departC AS DepartCircuit , C.arriveeC AS ArriverCircuit ,"
                    + " M.type AS TypeMoyen, M.capacite AS capacitee, P.dateD AS dateDepart , P.DateA AS dateArriver,"
                    + " M.numMoy AS NumeroM, P.prix as prix  from circuit AS C"
                    + " , moyenstransport AS M , planning AS P where P.idMoy= M.idMoy AND P.idCir=C.idCircuit AND P.dateD > '" + TimeNow + "' "
                    + " AND C.departC = '" + CircuitDepart + "' AND C.arriveeC = '" + CircuitArr + "' AND  M.type= '" + TypeT + "' ;";
            Statement ste;
            ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SqlJointurePlanning);
            while (s.next()) {

                int IdMoy = s.getInt("Moy");
                int IdCir = s.getInt("Cir");
                String departCircuit = s.getString("DepartCircuit");
                String arriveeCircuit = s.getString("ArriverCircuit");

                String typeMoyen = s.getString("TypeMoyen");
                int capacitee = s.getInt("capacitee");

                Time dateDepart = s.getTime("dateDepart");
                Time dateArriver = s.getTime("dateArriver");
                String NumeroM = s.getString("NumeroM");
                int PRIX = s.getInt("prix");

                Planning planning = new Planning(departCircuit, arriveeCircuit, typeMoyen, dateDepart, dateArriver, capacitee, NumeroM, PRIX);

                planning.setIdMoy(IdMoy);
                planning.setIdCir(IdCir);
                planning.setCircuitID(IdCir);
                planning.setMoyenneID(IdMoy);

                Plan.add(planning);

            }
            FileInputStream inputstream = new FileInputStream("C:\\Users\\pc\\Downloads\\Final\\TakTak_Final\\src\\tn\\esprit\\gui\\images\\train.jpg");
            Image trainImage = new Image(inputstream);
            FileInputStream inputstream1 = new FileInputStream("C:\\Users\\pc\\Downloads\\Final\\TakTak_Final\\src\\tn\\esprit\\gui\\images\\bus.png");
            Image busImage = new Image(inputstream1);
            FileInputStream inputstream2 = new FileInputStream("C:\\Users\\pc\\Downloads\\Final\\TakTak_Final\\src\\tn\\esprit\\gui\\images\\metro.jpg");
            Image metroImage = new Image(inputstream2);

            Image[] imagesArray = {trainImage, busImage, metroImage};

            // ObservableList<Planning> listeGET = FXCollections.observableArrayList();
            ObservableList<Planning> listeP = FXCollections.observableArrayList(Plan);

            listViewReference.setItems(listeP);
            listViewReference.setCellFactory(param -> new ListCell<Planning>() {
                /*view the image class to display the image*/
                private ImageView displayImage = new ImageView();

                @Override
                public void updateItem(Planning name, boolean empty) {
                    super.updateItem(name, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        if (name.getMoy().getType().equals("Train")) {
                            displayImage.setImage(imagesArray[0]);
                            /*setting array image to First Image*/
                        } else if (name.getMoy().getType().equals("Metro")) {
                            displayImage.setImage(imagesArray[2]);/*setting array image to Second Image*/
                        } else if (name.getMoy().getType().equals("Bus")) {
                            displayImage.setImage(imagesArray[1]);/*setting array image to Third Image*/
                        }
                        
                        if(UsersSession.getAbonneUser() == 1 ){
                                
                        setText("depart : " + name.getCir().getDepartC() + " à " + name.getCir().getArriveeC() + " Numéro " + name.getMoy().getNumMoy() + " Date de départ : " + name.getDateD() + " Date d'arriver : " + name.getDateA());
                        }
                        else{
                        setText("depart : " + name.getCir().getDepartC() + " à " + name.getCir().getArriveeC() + " Numéro " + name.getMoy().getNumMoy() + " Date de départ : " + name.getDateD() + " Date d'arriver : " + name.getDateA() + " prix : " + name.getPrix());

                        }
                        displayImage.setFitHeight(100);
                        displayImage.setFitWidth(100);

                        setGraphic(displayImage);
  
                    }
                }
            });
if(listeP.isEmpty()){
      Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Aucune planning trouvée !");
                    alert.showAndWait();
                    listViewReference.setVisible(false);
                    buttonReser.setVisible(true);
                    return;
}
            listViewReference.setVisible(true);
            buttonReser.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(ReservationPage1Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReservationPage1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ReserverBtn(ActionEvent event) {
        try {

            PlanningService pl = new PlanningService();
            ReservationService rs = new ReservationService();

            
            
            //Recuperer les ids pour je peux recuperer nembre de place 
            int idmoyy = Integer.parseInt(idMoy.getText().toString());
            int idCirr = Integer.parseInt(idCir.getText().toString());
            
            //Conversion String to time dateDepart recuperer t1
            Date sdf = new SimpleDateFormat("hh:mm:ss").parse(DateD.getText().toString());
            long ms = sdf.getTime();
            Time t1 = new Time(ms);
            pl.changerHourTo12A(t1);
            
            //Recuperer nombre de place
            int nbplace = pl.GetNombreDePlace(idCirr, idmoyy, t1);
            System.out.println(nbplace);
            
            
            //Recuperer Time Now in variable TimeNow 
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat formatteur = new SimpleDateFormat("HH:mm:ss");
            String Timenow = formatteur.format(date);
            Date parsedDate = formatteur.parse(Timenow);
            long dt = parsedDate.getTime();
            Time TimeNow = new Time(dt);

            

            //Conversion String to time dateDepart recuperer t1
            Date sdf1 = new SimpleDateFormat("hh:mm:ss").parse(HeureArr);
            long ms1 = sdf.getTime();
            Time t2 = new Time(ms);
            pl.changerHourTo12A(t2);

            //Recuperer Type C deja String Type = selected.get(0).getMoy().getType() + "";
            
            //recuperer Numero de transport  NUM = selected.get(0).getMoy().numMoy;
            
            //Cin de utilisateur de session En fait exemple jusqu'a en trouve une solution 
            Adresse a = new Adresse();
            a.setIdAdresse(75);
//            User u1 = new User(1, "montasser", "benouirane", "url", "montamonta", "montabwi@gmail.com", "user", "nonPermis",a, 26923145, 13025486, "02/03/2023", false);
//            int cin = u1.getCinU();


            //recuperer prix in prixx 
            int prixx = Integer.parseInt(prix);

            Reservation R = new Reservation(UsersSession.getCin(), prixx, TimeNow, t1, t2, Type, NUM);
            rs.ajouter(R);

            pl.SuppNbPlace(idCirr, idmoyy, t1);
            //recuperer ID de ticket ajouter
            int Ticket = rs.GetEndTicket();

            SendMail sm = new SendMail();
            sm.sendEmail(UsersSession.getEmail(), Ticket);

            //envoyer sms 
            SendSms sms = new SendSms();
            //sms.sendSms(Ticket,"+216"+u1.getTelephoneU());

        } catch (ParseException ex) {
            Logger.getLogger(ReservationPage1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    private void getData(MouseEvent event) {
        ObservableList<Planning> selected = listViewReference.getSelectionModel().getSelectedItems();
        buttonReser.setDisable(false);
        idMoy.setText(selected.get(0).getIdMoy() + "");
        idCir.setText(selected.get(0).getIdCir() + "");
        DateD.setText(selected.get(0).getDateD() + "");
        HeureArr = selected.get(0).getDateA() + "";
        Type = selected.get(0).getMoy().getType() + "";
        prix = selected.get(0).getPrix() + "";
        NUM = selected.get(0).getMoy().numMoy;
       
    }

    @FXML
    private void MAP_PAGE(ActionEvent event) {
      try {
            //AnchorPane view = FXMLLoader.load(getClass().getResource("AjouterMoyens.fxml"));
            
              Parent loader =FXMLLoader.load(getClass().getResource("MapUSE.fxml"));   
              //TransportMain m1 = new TransportMain();
              loader.prefHeight(100);
              loader.prefWidth(100);
              DateD.getScene().setRoot(loader);
              
            
        } catch (IOException ex) {
            Logger.getLogger(ReservationPage1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }

    @FXML
    private void OFFRE_ACTION(ActionEvent event) {
    }
}
