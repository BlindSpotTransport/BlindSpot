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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;
import tn.esprit.entity.Circuit;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.entity.Planning;
import tn.esprit.entity.PlanningItem;
import tn.esprit.services.PlanningService;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class CrudPlanningController implements Initializable {

    @FXML
    private TableView<Planning> TablePlanning;
    @FXML
    private TableColumn<Planning, Time> DateDepColumn;
    @FXML
    private TableColumn<Planning, Time> DateArrColumn;
    @FXML
    private TableColumn<Planning, String> NbPlaceColumn;
    @FXML
    private TableColumn<Planning, String> CirDepColumn;
    @FXML
    private TableColumn<Planning, String> CirArrColumn;
    @FXML
    private TextField DateDep;
    @FXML
    private TextField DateArr;
    @FXML
    private ComboBox<String> CirDepComboBox;
    @FXML
    private ComboBox<String> CirArrComboBox;

    PlanningService pl = new PlanningService();
    @FXML
    private TableColumn<Planning, String> TypeColumn;
    Connection cnx;
    @FXML
    private ComboBox<String> typeTran;

    Boolean isSelected = false;
    @FXML
    private TableColumn<Planning, String> NumMCol;
    @FXML
    private ComboBox<String> NumeroMComboBox;
    @FXML
    private TableColumn<Planning, String> PrixCol;
    ObservableList<Planning> listeP;
    @FXML
    private TextField Prix;
    String IDM;
    String IDC;

    /**
     * Initialisation de données pour les test *
     */
    @FXML
    private Text DateDMdf;
    @FXML
    private Text DateAMdf;
    @FXML
    private Text TypeTMdf;
    @FXML
    private Text CirDepMdf;
    @FXML
    private Text CirArrMdf;
    @FXML
    private Text NumeroMdf;
    @FXML
    private Text prixMdf;
    @FXML
    private TableColumn<Planning, String> IDcir;
    @FXML
    private TableColumn<Planning, String> IDmoy;
    @FXML
    private TextField Rechercher;
    @FXML
    private TextField NBplaceMDF;

    public CrudPlanningController() {
        cnx = MaConnection.getInstance().getCnx();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //pour faire jointure entre les table en besoin d'ajouter une classe 
        List<Planning> Plan = new ArrayList<>();
        Circuit cc = new Circuit();
        Moyenstransport M = new Moyenstransport();
        try {
            String SqlJointurePlanning = "select  P.idMoy as Moy, P.idCir as Cir, C.departC AS DepartCircuit , C.arriveeC AS ArriverCircuit ,"
                    + " M.type AS TypeMoyen, P.NbPlace AS capacitee, P.dateD AS dateDepart , P.DateA AS dateArriver,"
                    + " M.numMoy AS NumeroM, P.prix as prix  from circuit AS C"
                    + " , moyenstransport AS M , planning AS P where P.idMoy= M.idMoy AND P.idCir=C.idCircuit ;";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SqlJointurePlanning);

            while (s.next()) {
                /*    
             Circuit c = new Circuit(s.getString("DepartCircuit"), s.getString("ArriverCircuit"));
             Moyenstransport m = new Moyenstransport(s.getString("TypeMoyen"),s.getInt("capacitee"));
             Planning p = new Planning(s.getTime("dateDepart"),s.getTime("dateArriver"));
            
             List<Object> myList = new ArrayList<>(Arrays.asList(c,m,p));        
             Plan.add(myList);

             }            
            System.out.println(Plan);
            
                 */
                int IdMoy = s.getInt("Moy");
                int IdCir = s.getInt("Cir");
                String departCircuit = s.getString("DepartCircuit");
                String arriveeCircuit = s.getString("ArriverCircuit");
                // pour recuperer l'object circuit
                /*Circuit c1 = new Circuit();
        c1.setDepartC(departCircuit);
        c1.setArriveeC(arriveeCircuit);
        String DEP=c1.getDepartC();
        String ARR=c1.getArriveeC();
                 */
                String typeMoyen = s.getString("TypeMoyen");
                int capacitee = s.getInt("capacitee");
                /*Moyenstransport m1 = new Moyenstransport();
        m1.setType(typeMoyen);
        m1.setCapacite(capacitee);
        String TYPE=m1.getType();
        int CAPACITE=m1.getCapacite();
                 */
                Time dateDepart = s.getTime("dateDepart");
                Time dateArriver = s.getTime("dateArriver");
                String NumeroM = s.getString("NumeroM");
                int PRIX = s.getInt("prix");
                // wlh mokhi tahreg xD
                //System.out.println(departCircuit + " "+ arriveeCircuit +" "+ typeMoyen +" "+ dateDepart +" "+ dateArriver + " "+capacitee +" "+ NumeroM);
                Planning planning = new Planning(departCircuit, arriveeCircuit, typeMoyen, dateDepart, dateArriver, capacitee, NumeroM, PRIX);

                planning.setIdMoy(IdMoy);
                planning.setIdCir(IdCir);
                planning.setCircuitID(IdCir);
                planning.setMoyenneID(IdMoy);

                Plan.add(planning);
                // c = Plan.get(0).getCir();
                // M=Plan.get(0).getMoy();
            }

            //  System.out.print(Plan);
            //Planning p = Plan.get(0);
            listeP = FXCollections.observableArrayList(Plan);
            
            //date now pour tester nombre de place
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat formatteur = new SimpleDateFormat("HH:mm:ss");
            String Timenow = formatteur.format(date);
            Date parsedDate = formatteur.parse(Timenow);
            long dt = parsedDate.getTime();
            Time TimeNow = new Time(dt);
            System.out.println(TimeNow);
            
            for (int i = 0; i < listeP.size(); i++) {
                Planning planning = listeP.get(i);
                System.out.println(planning);
                if (TimeNow.getTime() > planning.getDateA().getTime()){
                  pl.ResetNbPlace(planning.getIdCir(),planning.getIdMoy(),planning.getDateD(),planning.getMoy().getType(),planning.getMoy().getNumMoy());  
                }
                
                CirDepColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCir().getDepartC().toString()));

                CirArrColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCir().getArriveeC().toString()));

                TypeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMoy().getType()));

//                NbPlaceColumn.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getMoy().getCapacite())));
                NbPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("NbPlace"));

                DateDepColumn.setCellValueFactory(new PropertyValueFactory<>("dateD"));

                DateArrColumn.setCellValueFactory(new PropertyValueFactory<>("dateA"));

                NumMCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMoy().getNumMoy()));

                PrixCol.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getPrix())));

                IDcir.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getIdCir())));

                IDmoy.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getIdMoy())));

                TablePlanning.getItems().add(planning);
            }

            // Pour remplir comboBOX de Circuit  de depart
            //CirDepComboBox
            InitialBOXDEP();
            InitialBOXArr();
            InitialBOXTYPE();
            InitialBOXNUM();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InitialBOXDEP() {
        try {
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
            CirDepComboBox.setItems(ListtD);
        } catch (SQLException ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InitialBOXArr() {
        try {
            // Pour remplir comboBOX de Circuit  d'arriver
            //CirDepComboBox
            ObservableList<String> CircuitArr = FXCollections.observableArrayList();
            String SqlGetCircuitArriver = "SELECT DISTINCT arriveeC FROM circuit";
            Statement ste = cnx.createStatement();
            ResultSet arr = ste.executeQuery(SqlGetCircuitArriver);
            while (arr.next()) {
                String arriver = arr.getString("arriveeC");
                String p2 = new String(arriver);
                CircuitArr.add(p2);
            }
            CirArrComboBox.setItems(CircuitArr);
        } catch (SQLException ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InitialBOXTYPE() {
        try {
            // pour remplir comboBOX de type de transport
            ObservableList<String> TypeCir = FXCollections.observableArrayList();
            String SqlGetTransportType = "SELECT DISTINCT type from moyenstransport";
            Statement ste = cnx.createStatement();
            ResultSet trans = ste.executeQuery(SqlGetTransportType);
            while (trans.next()) {

                String type = trans.getString("type");
                TypeCir.add(type);
            }
            typeTran.setItems(TypeCir);
        } catch (SQLException ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InitialBOXNUM() {
        try {
            // Pour remplier comboBOX de numero Moyenne de transport 
            ObservableList<String> NumeroMoy = FXCollections.observableArrayList();
            String SqlGetNumeorMoy = "SELECT numMoy from moyenstransport";
            Statement ste = cnx.createStatement();
            ResultSet NumM = ste.executeQuery(SqlGetNumeorMoy);
            while (NumM.next()) {
                String Numeroo = NumM.getString("numMoy");
                NumeroMoy.add(Numeroo);
            }
            NumeroMComboBox.setItems(NumeroMoy);
        } catch (SQLException ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterBtn(ActionEvent event) {

        Date sdf, sdf1;
        if (CirArrComboBox.getSelectionModel().getSelectedItem() == null
                || CirDepComboBox.getSelectionModel().getSelectedItem() == null
                || typeTran.getSelectionModel().getSelectedItem() == null
                || NumeroMComboBox.getSelectionModel().getSelectedItem() == null
                || DateDep.getText().toString().equals("")
                || DateArr.getText().toString().equals("")
                || Prix.getText().toString().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Les champs sont obligatoire !");
            alert.showAndWait();
            return;
        }
        try {
            Time t2;
            Time t1;
            Planning PL = new Planning();
            String DEP = CirDepComboBox.getValue().toString();
            //System.out.print(DEP);
            String ARR = CirArrComboBox.getValue().toString();
            String NUM = NumeroMComboBox.getValue().toString();
            String TYPEM = typeTran.getValue().toString();

            try {
                String DATE_ARR = DateArr.getText().toString();
                sdf = new SimpleDateFormat("hh:mm:ss").parse(DATE_ARR);
                long ms = sdf.getTime();
                t1 = new Time(ms);
                pl.changerHourTo12A(t1);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Respecter la format de point d'arrvier !");
                alert.showAndWait();
                return;
            }
            try {
                String DATE_DEP = DateDep.getText().toString();
                sdf1 = new SimpleDateFormat("hh:mm:ss").parse(DATE_DEP);
                long ms1 = sdf1.getTime();
                t2 = new Time(ms1);
                pl.changerHourTo12A(t2);

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Respecter la format de point de depart !");
                alert.showAndWait();
                return;
            }

            String SQLgetIDCicruit = "select idCircuit from circuit where departC='" + DEP + "' AND arriveeC='" + ARR + "' ";
            Statement ste = cnx.createStatement();
            ResultSet c = ste.executeQuery(SQLgetIDCicruit);
            ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();
            while (c.next()) {
                int IDD_C = c.getInt("idCircuit");
                IDlisteC.add(IDD_C);
            }

            //id de circuit
            int FINAL_ID_C = IDlisteC.get(0);

            String SQLgetIDMoyenne = "select idMoy from moyenstransport where type='" + TYPEM + "' AND numMoy='" + NUM + "' ";
            ResultSet m = ste.executeQuery(SQLgetIDMoyenne);
            ObservableList<Integer> IDlisteM = FXCollections.observableArrayList();
            while (m.next()) {
                int IDD_M = m.getInt("idMoy");
                IDlisteM.add(IDD_M);
            }
            int FINAL_ID_M = IDlisteM.get(0);
            if (Prix.getText().toString().length() <= 5) {
                try {
                    int prix = Integer.parseInt(Prix.getText().toString());
                    PL.setPrix(prix);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Les champs de prix doit etre un nombre!");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("prix est trés grande !");
                alert.showAndWait();
                return;
            }

            if (t2.getHours() > t1.getHours()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Attention point de depart doit etre inferieur a point d'arriver !");
                alert.showAndWait();
                return;
            } else if (t2.getHours() == t1.getHours()) {
                if (t2.getMinutes() > t1.getMinutes()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention point de depart doit etre inferieur a point d'arriver !");
                    alert.showAndWait();
                    return;
                }
            }

            //recuperer capacite de Moyenne selonn IdMoy et numero 
            String SQLgetCapacite = "select capacite from moyenstransport where type='" + TYPEM + "' AND numMoy='" + NUM + "' ";

            int CAP = 0;
            ResultSet k = ste.executeQuery(SQLgetCapacite);
            while (k.next()) {
                CAP = k.getInt("capacite");
            }
            int capacitee = CAP;
            PL.setNbPlace(capacitee);

            PL.setIdMoy(FINAL_ID_M);
            PL.setIdCir(FINAL_ID_C);
            PL.setDateA(t1);
            PL.setDateD(t2);
//            PL.setNbPlace();

            pl.ajouter(PL);
            refreshTable();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        System.out.println(prixMdf.getText());
         if(TablePlanning.getSelectionModel().getSelectedItem()==null){
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("veuillez sélectionner une ligne de planning dans le tableau !");
        alert.showAndWait();
        }else if ( CirDepComboBox.getValue().toString().equals(CirDepMdf.getText().toString()) 
                && CirArrComboBox.getValue().toString().equals(CirArrMdf.getText().toString())              
                && typeTran.getValue().toString().equals(TypeTMdf.getText().toString())
                && NumeroMComboBox.getValue().toString().equals(NumeroMdf.getText().toString())
                && DateDep.getText().toString().equals(DateDMdf.getText().toString())
                && DateArr.getText().toString().equals(DateAMdf.getText().toString())
                && Prix.getText().toString().equals(prixMdf.getText().toString())
                )
        {
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("warning");
        alert.setHeaderText(null);
        alert.setContentText("veuillez modifier aux moins un champ !");
        alert.showAndWait();
        return;
        }
        else if ( DateDep.getText().toString().equals("") ||  DateArr.getText().toString().equals("")  || Prix.getText().toString().equals(""))
        {
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Les champs sont obligatoire !");
        alert.showAndWait(); 
        return;
        }
        else 
        {    
             Date sdf1, sdf, sdf2;
//              System.out.println(CirDepComboBox.getValue().toString() 
//                      +" "+CirArrComboBox.getValue().toString()
//              +" "+NumeroMComboBox.getValue().toString()
//              +" "+typeTran.getValue().toString()
//              +" "+DateDep.getText().toString()
//                      +" "+DateArr.getText().toString()
//                      +" "+Prix.getText().toString()
//                      +" "+IDC
//                      +" "+IDM
//                                            +" "+DateDMdf
//
//                      
//              );
             try
             {
        Planning p = new Planning();
        Time t1,t2;
            String DEP = CirDepComboBox.getValue().toString();
            String ARR = CirArrComboBox.getValue().toString();
            String NUM = NumeroMComboBox.getValue().toString();
            String TYPEM = typeTran.getValue().toString();
            
            
             try {
                String DATE_DEP = DateDep.getText().toString();
            sdf1 = new SimpleDateFormat("hh:mm:ss").parse(DATE_DEP);
            long ms1 = sdf1.getTime();
            t2 = new Time(ms1);
            pl.changerHourTo12(t2);
            p.setDateD(t2);

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Respecter la format de point de depart !");
                alert.showAndWait();
                return;
            }
            
                   try {
            String DATE_ARR = DateArr.getText().toString();
            sdf = new SimpleDateFormat("hh:mm:ss").parse(DATE_ARR);
            long ms = sdf.getTime();
            t1 = new Time(ms);
            pl.changerHourTo12A(t1);
            p.setDateA(t1);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Respecter la format de point d'arriver !");
                alert.showAndWait();
                return;
            }
        
            String SQLgetIDCicruit = "select idCircuit from circuit where departC='" + DEP + "' AND arriveeC='" + ARR + "' ";
            Statement ste = cnx.createStatement();
            ResultSet c = ste.executeQuery(SQLgetIDCicruit);
            ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();
            while (c.next()) {
                int IDD_C = c.getInt("idCircuit");
                IDlisteC.add(IDD_C);
            }
            //id de circuit
            int FINAL_ID_C = IDlisteC.get(0);
                       // System.out.println(FINAL_ID_C);

            
                        String SQLgetIDMoyenne = "select idMoy from moyenstransport where type='" + TYPEM + "' AND numMoy='" + NUM + "' ";
            ResultSet m = ste.executeQuery(SQLgetIDMoyenne);
            ObservableList<Integer> IDlisteM = FXCollections.observableArrayList();
            while (m.next()) {
                int IDD_M = m.getInt("idMoy");
                IDlisteM.add(IDD_M);
            }
            int FINAL_ID_M = IDlisteM.get(0);
            //System.out.println(FINAL_ID_M);
            
            sdf2 = new SimpleDateFormat("hh:mm:ss").parse(DateDMdf.getText().toString());
            long mm = sdf2.getTime();
            Time t3 = new Time(mm);
            pl.changerHourTo12A(t3);
            
            Moyenstransport Moyy = new Moyenstransport();
            Moyy.setIdMoy(FINAL_ID_M);
            
            Circuit cir = new Circuit();
            cir.setIdCircuit(FINAL_ID_C);
            
            if (Prix.getText().toString().length() <= 5) {
                try {
                    int prix = Integer.parseInt(Prix.getText().toString());
                    p.setPrix(prix);

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Les champs de prix doit etre un nombre!");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("prix est trés grande !");
                alert.showAndWait();
                return;
            }
            
            
            int idcc=Integer.parseInt(IDC);
            int idmm=Integer.parseInt(IDM);
            
            if (t2.getHours() > t1.getHours()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Attention point de depart doit etre inferieur a point d'arriver !");
                alert.showAndWait();
                return;
            } else if (t2.getHours() == t1.getHours()) {
                if (t2.getMinutes() > t1.getMinutes()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention point de depart doit etre inferieur a point d'arriver !");
                    alert.showAndWait();
                    return;
                }
            }
            
            
            
            
            p.setIdMoy(FINAL_ID_M);
            p.setIdCir(FINAL_ID_C);
            p.setOldDate(t3);
            p.setMoy(Moyy);
            p.setCir(cir);
            
            pl.modifierPlannig(idmm,idcc,p);
            System.out.println(p);
            refreshTable();
            } catch (ParseException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
        Date sdf1;
        try {
            Planning p = new Planning();
            String DEP = CirDepComboBox.getValue().toString();
            String ARR = CirArrComboBox.getValue().toString();
            String NUM = NumeroMComboBox.getValue().toString();
            String TYPEM = typeTran.getValue().toString();
            String DATE_DEP = DateDep.getText().toString();
            sdf1 = new SimpleDateFormat("hh:mm:ss").parse(DATE_DEP);
            long ms1 = sdf1.getTime();
            Time t2 = new Time(ms1);
            pl.changerHourTo12(t2);

            String SQLgetIDCicruit = "select idCircuit from circuit where departC='" + DEP + "' AND arriveeC='" + ARR + "' ";
            Statement ste = cnx.createStatement();
            ResultSet c = ste.executeQuery(SQLgetIDCicruit);
            ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();
            while (c.next()) {
                int IDD_C = c.getInt("idCircuit");
                IDlisteC.add(IDD_C);
            }
            //id de circuit
            int FINAL_ID_C = IDlisteC.get(0);

            String SQLgetIDMoyenne = "select idMoy from moyenstransport where type='" + TYPEM + "' AND numMoy='" + NUM + "' ";
            ResultSet m = ste.executeQuery(SQLgetIDMoyenne);
            ObservableList<Integer> IDlisteM = FXCollections.observableArrayList();
            while (m.next()) {
                int IDD_M = m.getInt("idMoy");
                IDlisteM.add(IDD_M);
            }
            int FINAL_ID_M = IDlisteM.get(0);
            //System.out.println(FINAL_ID_C);
            p.setIdMoy(FINAL_ID_M);
            p.setIdCir(FINAL_ID_C);
            p.setDateD(t2);
            pl.supprimer(p);

            refreshTable();
        } catch (Exception ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GetSelected(MouseEvent event) {
        isSelected = true;
        try {
            int index = TablePlanning.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                return;
            } else {
                DateDep.setText(DateDepColumn.getCellData(index).toString());
                DateArr.setText(DateArrColumn.getCellData(index).toString());
                typeTran.setValue(TypeColumn.getCellData(index).toString());
                CirDepComboBox.setValue(CirDepColumn.getCellData(index));
                CirArrComboBox.setValue(CirArrColumn.getCellData(index));
                NumeroMComboBox.setValue(NumMCol.getCellData(index).toString());
                Prix.setText(PrixCol.getCellData(index).toString());
                IDC = IDcir.getCellData(index).toString();
                IDM = IDmoy.getCellData(index).toString();
                NBplaceMDF.setText(NbPlaceColumn.getCellData(index).toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void SelectedCirDep(ActionEvent event) {
        try {
            String DEPART = CirDepComboBox.getValue().toString();
            // System.out.println(DEPART);
            ObservableList<String> CircuitArr = FXCollections.observableArrayList();
            String SQLgetCirArr = "select arriveeC from circuit where departC='" + DEPART + "' ";

            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SQLgetCirArr);

            while (s.next()) {
                String CirArr = s.getString("arriveeC");
                CircuitArr.add(CirArr);
            }
            CirArrComboBox.setItems(CircuitArr);
            if (isSelected == true) {

                InitialBOXArr();

            }
            return;
        } catch (Exception ex) {
            System.out.println("La liste de circuit d'arriver a éte modifier avec succées");
        }

    }

    @FXML
    private void SelectedCirArr(ActionEvent event) {
        try {
            String ARRIVER = CirArrComboBox.getValue().toString();
            ObservableList<String> CircuitDep = FXCollections.observableArrayList();
            String SQLgetCirDep = "select departC from circuit where arriveeC='" + ARRIVER + "' ";

            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SQLgetCirDep);

            while (s.next()) {
                String CirDep = s.getString("departC");
                CircuitDep.add(CirDep);
            }
            CirDepComboBox.setItems(CircuitDep);
            if (isSelected == true) {

                InitialBOXDEP();

            }
            return;
        } catch (Exception ex) {
            System.out.println("La liste de circuit de depart a éte modifier avec succées");
        }
    }

    private void refreshTable() {
        List<Planning> Plan = new ArrayList<>();
        Circuit cc = new Circuit();
        Moyenstransport M = new Moyenstransport();
        try {
            String SqlJointurePlanning = "select  P.idMoy as Moy, P.idCir as Cir, C.departC AS DepartCircuit , C.arriveeC AS ArriverCircuit ,"
                    + " M.type AS TypeMoyen, P.NbPlace AS capacitee, P.dateD AS dateDepart , P.DateA AS dateArriver,"
                    + " M.numMoy AS NumeroM, P.prix as prix  from circuit AS C"
                    + " , moyenstransport AS M , planning AS P where P.idMoy= M.idMoy AND P.idCir=C.idCircuit ;";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SqlJointurePlanning);

            while (s.next()) {
                /*    
             Circuit c = new Circuit(s.getString("DepartCircuit"), s.getString("ArriverCircuit"));
             Moyenstransport m = new Moyenstransport(s.getString("TypeMoyen"),s.getInt("capacitee"));
             Planning p = new Planning(s.getTime("dateDepart"),s.getTime("dateArriver"));
            
             List<Object> myList = new ArrayList<>(Arrays.asList(c,m,p));        
             Plan.add(myList);

             }            
            System.out.println(Plan);
            
                 */
                int IdMoy = s.getInt("Moy");
                int IdCir = s.getInt("Cir");
                String departCircuit = s.getString("DepartCircuit");
                String arriveeCircuit = s.getString("ArriverCircuit");
                // pour recuperer l'object circuit
                /*Circuit c1 = new Circuit();
        c1.setDepartC(departCircuit);
        c1.setArriveeC(arriveeCircuit);
        String DEP=c1.getDepartC();
        String ARR=c1.getArriveeC();
                 */
                String typeMoyen = s.getString("TypeMoyen");
                int capacitee = s.getInt("capacitee");
                /*Moyenstransport m1 = new Moyenstransport();
        m1.setType(typeMoyen);
        m1.setCapacite(capacitee);
        String TYPE=m1.getType();
        int CAPACITE=m1.getCapacite();
                 */
                Time dateDepart = s.getTime("dateDepart");
                Time dateArriver = s.getTime("dateArriver");
                String NumeroM = s.getString("NumeroM");
                int prix = s.getInt("prix");
                // wlh mokhi tahreg xD
                //System.out.println(departCircuit + " "+ arriveeCircuit +" "+ typeMoyen +" "+ dateDepart +" "+ dateArriver + " "+capacitee +" "+ NumeroM);
                Planning planning = new Planning(departCircuit, arriveeCircuit, typeMoyen, dateDepart, dateArriver, capacitee, NumeroM, prix);

                planning.setIdMoy(IdMoy);
                planning.setIdCir(IdCir);
                planning.setCircuitID(IdCir);
                planning.setMoyenneID(IdMoy);
                Plan.add(planning);
                // c = Plan.get(0).getCir();
                // M=Plan.get(0).getMoy();
            }

            //  System.out.print(Plan);
            //Planning p = Plan.get(0);
            ObservableList<Planning> listeP = FXCollections.observableArrayList(Plan);
            for (int i = 0; i < listeP.size(); i++) {
                Planning planning = listeP.get(i);

                CirDepColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCir().getDepartC().toString()));

                CirArrColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCir().getArriveeC().toString()));

                TypeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMoy().getType()));

                NbPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("NbPlace"));

                DateDepColumn.setCellValueFactory(new PropertyValueFactory<>("dateD"));

                DateArrColumn.setCellValueFactory(new PropertyValueFactory<>("dateA"));

                NumMCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMoy().getNumMoy()));

                PrixCol.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getPrix())));

                TablePlanning.setItems(listeP);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void SelectedNumero(ActionEvent event) {
        try {
            String Type = typeTran.getValue().toString();
            ObservableList<String> Numero = FXCollections.observableArrayList();
            String SQLgetCirDep = "select numMoy from moyenstransport where type='" + Type + "' ";

            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SQLgetCirDep);

            while (s.next()) {
                String num = s.getString("numMoy");
                Numero.add(num);
            }
            NumeroMComboBox.setItems(Numero);
            if (isSelected == true) {

                InitialBOXNUM();

            }
            return;
        } catch (Exception ex) {
            System.out.println("La liste de numero de moyenne de transport a éte modifier avec succées");
        }

    }

    @FXML
    private void SelectedType(ActionEvent event) {
        try {
            String Numero = NumeroMComboBox.getValue().toString();
            ObservableList<String> Numm = FXCollections.observableArrayList();
            String SQLgetCirDep = "select type from moyenstransport where numMoy='" + Numero + "' ";

            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SQLgetCirDep);

            while (s.next()) {
                String type = s.getString("type");
                Numm.add(type);
            }
            typeTran.setItems(Numm);
            if (isSelected == true) {

                InitialBOXTYPE();

            }
            return;
        } catch (Exception ex) {
            System.out.println("La liste de type de moyenne de transport a éte modifier avec succées");
        }

    }

    @FXML
    private void RechercherTAB(KeyEvent event) {
        String nom1 = "";
        if (event.getText().length() > 0) {
            nom1 = Rechercher.getText() + event.getText();
        } else {
            nom1 = Rechercher.getText().substring(0, Rechercher.getText().length() - 1);
        }
        System.out.println(nom1);
        String nom = nom1.toLowerCase();
        ObservableList<Planning> filtePla = listeP.stream()
                .filter(r -> r.getCir().getDepartC().toLowerCase().contains(nom)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        TablePlanning.setItems(filtePla);

    }

    @FXML
    private void NewPassager(ActionEvent event) {
        if (TablePlanning.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("veuillez sélectionner une ligne de planning dans le tableau !");
            alert.showAndWait();
        }
        String DEP = CirDepComboBox.getValue().toString();
        //System.out.print(DEP);
        String ARR = CirArrComboBox.getValue().toString();
        String NUM = NumeroMComboBox.getValue().toString();
        String TYPEM = typeTran.getValue().toString();

        String SQLgetIDCicruit = "select idCircuit from circuit where departC='" + DEP + "' AND arriveeC='" + ARR + "' ";
        Statement ste;
        try {
            ste = cnx.createStatement();

            ResultSet c = ste.executeQuery(SQLgetIDCicruit);
            ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();
            while (c.next()) {
                int IDD_C = c.getInt("idCircuit");
                IDlisteC.add(IDD_C);
            }

            //id de circuit
            int FINAL_ID_C = IDlisteC.get(0);

            String SQLgetIDMoyenne = "select idMoy from moyenstransport where type='" + TYPEM + "' AND numMoy='" + NUM + "' ";
            ResultSet m = ste.executeQuery(SQLgetIDMoyenne);
            ObservableList<Integer> IDlisteM = FXCollections.observableArrayList();
            while (m.next()) {
                int IDD_M = m.getInt("idMoy");
                IDlisteM.add(IDD_M);
            }
            int FINAL_ID_M = IDlisteM.get(0);

            String DATE_DEP = DateDep.getText().toString();
            Date sdf1 = new SimpleDateFormat("hh:mm:ss").parse(DATE_DEP);
            long ms1 = sdf1.getTime();
            Time t2 = new Time(ms1);
            pl.changerHourTo12A(t2);

            pl.SuppNbPlace(FINAL_ID_C, FINAL_ID_M, t2);
            refreshTable();

        } catch (ParseException | SQLException ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SelectedGet(MouseEvent event) {
    
     try {
            int index = TablePlanning.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                return;
            }
                prixMdf.setText(PrixCol.getCellData(index).toString());
                DateDMdf.setText(DateDepColumn.getCellData(index).toString());
                DateAMdf.setText(DateArrColumn.getCellData(index).toString());
                TypeTMdf.setText(TypeColumn.getCellData(index).toString());
                CirDepMdf.setText(CirDepColumn.getCellData(index));
                CirArrMdf.setText(CirArrColumn.getCellData(index));
                NumeroMdf.setText(NumMCol.getCellData(index).toString());
            

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
