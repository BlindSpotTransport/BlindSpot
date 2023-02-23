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
import java.util.stream.Stream;
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
import javafx.scene.input.MouseEvent;
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
    private TableView<PlanningItem> TablePlanning;
    @FXML
    private TableColumn<PlanningItem, Time> DateDepColumn;
    @FXML
    private TableColumn<PlanningItem, Time> DateArrColumn;
    @FXML
    private TableColumn<PlanningItem, Integer> NbPlaceColumn; 
    @FXML
    private TableColumn<PlanningItem, String> CirDepColumn;
    @FXML
    private TableColumn<PlanningItem, String> CirArrColumn;
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
    private TableColumn<PlanningItem, String> TypeColumn;
      Connection cnx;
    @FXML
    private ComboBox<String> typeTran;
    
    int index=-1 ;
    @FXML
    private TableColumn<PlanningItem, String> NumMCol;
    @FXML
    private ComboBox<String> NumeroMComboBox;
    
    public CrudPlanningController() {
             cnx = MaConnection.getInstance().getCnx();

    }
      
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //pour faire jointure entre les table en besoin d'ajouter une classe 
         ObservableList<PlanningItem> Plan = FXCollections.observableArrayList();        
        try{
            String SqlJointurePlanning="select C.departC AS DepartCircuit , C.arriveeC AS ArriverCircuit ,"
                    + " M.type AS TypeMoyen, M.capacite AS capacitee, P.dateD AS dateDepart , P.DateA AS dateArriver,"
                    + " M.numMoy AS NumeroM  from circuit AS C"
                    + " , moyenstransport AS M , planning AS P where P.idMoy= M.idMoy AND P.idCir=C.idCircuit ;" ;
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
        String departCircuit = s.getString("DepartCircuit");
        String arriveeCircuit = s.getString("ArriverCircuit");
        String typeMoyen = s.getString("TypeMoyen");
        int capacitee = s.getInt("capacitee");
        Time dateDepart = s.getTime("dateDepart");
        Time dateArriver = s.getTime("dateArriver");
        String NumeroM = s.getString("NumeroM");
        PlanningItem planningItem = new PlanningItem(departCircuit, arriveeCircuit, typeMoyen, capacitee, dateDepart, dateArriver ,NumeroM);        
        Plan.add(planningItem);
            }
       
        CirDepColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("departCircuit"));
        CirArrColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("arriveeCircuit"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("typeMoyen"));
        NbPlaceColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,Integer>("capacitee"));
        DateDepColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,Time>("dateDepart"));
        DateArrColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,Time>("dateArriver"));
        NumMCol.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("NumeroM"));

        TablePlanning.setItems(Plan);        
     
        // Pour remplir comboBOX de Circuit  de depart
        //CirDepComboBox
        ObservableList<String> CircuitDep = FXCollections.observableArrayList();        
        String SqlGetCircuitDepart = "SELECT DISTINCT departC FROM circuit";
        ResultSet dep = ste.executeQuery(SqlGetCircuitDepart);
        while(dep.next()){
            String depart=dep.getString("departC");
            String p2 = new String(depart);
        CircuitDep.add(p2);
        }
        List<String> DEP = new ArrayList<>();
        for (String CDep : CircuitDep){
            //System.out.println(CDep.getDepartCircuit());
            DEP.add(CDep);
        }
        ObservableList<String> ListtD = FXCollections.observableArrayList(DEP);
        CirDepComboBox.setItems(ListtD);
          
        // Pour remplir comboBOX de Circuit  d'arriver
        //CirDepComboBox
        ObservableList<String> CircuitArr = FXCollections.observableArrayList();        
        String SqlGetCircuitArriver = "SELECT DISTINCT arriveeC FROM circuit";
        ResultSet arr = ste.executeQuery(SqlGetCircuitArriver);
        while(arr.next()){
            String arriver=arr.getString("arriveeC");
            String p2 = new String(arriver);
        CircuitArr.add(p2);
        }
        CirArrComboBox.setItems(CircuitArr);
        
        // pour remplir comboBOX de type de transport
        ObservableList<String> TypeCir = FXCollections.observableArrayList();        
        String SqlGetTransportType="SELECT DISTINCT type from moyenstransport";
        ResultSet trans = ste.executeQuery(SqlGetTransportType);
        while(trans.next()){
            
            String type =trans.getString("type");
            TypeCir.add(type);    
        }
        typeTran.setItems(TypeCir);
        
        // Pour remplier comboBOX de numero Moyenne de transport 
        ObservableList<String> NumeroMoy = FXCollections.observableArrayList();
        String SqlGetNumeorMoy="SELECT numMoy from moyenstransport";
        ResultSet NumM =ste.executeQuery(SqlGetNumeorMoy);
        while(NumM.next()){
            String Numeroo = NumM.getString("numMoy");
            NumeroMoy.add(Numeroo);
        }
        NumeroMComboBox.setItems(NumeroMoy);

        
        
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }       
    }    

    @FXML
    private void AjouterBtn(ActionEvent event) {
    Date sdf , sdf1;
        try{
        Planning PL = new Planning();
        String DEP =CirDepComboBox.getValue().toString();
        String ARR =CirArrComboBox.getValue().toString();
        String NUM =NumeroMComboBox.getValue().toString();
        String TYPEM=typeTran.getValue().toString();
        String DATE_ARR = DateArr.getText().toString();
        sdf = new SimpleDateFormat("hh:mm:ss").parse(DATE_ARR);
                   long ms = sdf.getTime();
        Time t1 = new Time(ms);
        pl.changerHourTo12A(t1);

        
        String DATE_DEP = DateDep.getText().toString();
        sdf1 = new SimpleDateFormat("hh:mm:ss").parse(DATE_DEP);
        long ms1 = sdf1.getTime();
        Time t2 = new Time(ms1);
        pl.changerHourTo12A(t2);

        String SQLgetIDCicruit="select idCircuit from circuit where departC='"+DEP+"' AND arriveeC='"+ARR+"' " ;
            Statement ste = cnx.createStatement();
            ResultSet c = ste.executeQuery(SQLgetIDCicruit);
            ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();    
            while(c.next()){
           int IDD_C = c.getInt("idCircuit");
           IDlisteC.add(IDD_C);
            }
           //id de circuit
           int FINAL_ID_C=IDlisteC.get(0);
           
        String SQLgetIDMoyenne="select idMoy from moyenstransport where type='"+TYPEM+"' AND numMoy='"+NUM+"' ";
        ResultSet m = ste.executeQuery(SQLgetIDMoyenne);
        ObservableList<Integer> IDlisteM = FXCollections.observableArrayList();    
        while(m.next()){
            int IDD_M = m.getInt("idMoy");
            IDlisteM.add(IDD_M);
        }
        int FINAL_ID_M=IDlisteM.get(0);
        PL.setIdMoy(FINAL_ID_M);
        PL.setIdCir(FINAL_ID_C);
        PL.setDateA(t1);
        PL.setDateD(t2);
        pl.ajouter(PL);
        refreshTable();
            }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
    
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
       Date sdf1 ; 
       try {
           Planning p = new Planning();
        String DEP =CirDepComboBox.getValue().toString();
        String ARR =CirArrComboBox.getValue().toString();
        String NUM =NumeroMComboBox.getValue().toString();
        String TYPEM=typeTran.getValue().toString();
        String DATE_DEP = DateDep.getText().toString();
        sdf1 = new SimpleDateFormat("hh:mm:ss").parse(DATE_DEP);
        long ms1 = sdf1.getTime();
        Time t2 = new Time(ms1);
        pl.changerHourTo12(t2);
        
        String SQLgetIDCicruit="select idCircuit from circuit where departC='"+DEP+"' AND arriveeC='"+ARR+"' " ;
            Statement ste = cnx.createStatement();
            ResultSet c = ste.executeQuery(SQLgetIDCicruit);
            ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();    
            while(c.next()){
           int IDD_C = c.getInt("idCircuit");
           IDlisteC.add(IDD_C);
            }
           //id de circuit
           int FINAL_ID_C=IDlisteC.get(0);
           
        String SQLgetIDMoyenne="select idMoy from moyenstransport where type='"+TYPEM+"' AND numMoy='"+NUM+"' ";
        ResultSet m = ste.executeQuery(SQLgetIDMoyenne);
        ObservableList<Integer> IDlisteM = FXCollections.observableArrayList();    
        while(m.next()){
            int IDD_M = m.getInt("idMoy");
            IDlisteM.add(IDD_M);
        }
        int FINAL_ID_M=IDlisteM.get(0);
        //System.out.println(FINAL_ID_C);
         p.setIdMoy(FINAL_ID_M);
         p.setIdCir(FINAL_ID_C);
         p.setDateD(t2);
        pl.supprimer(p);  
        System.out.print(p);
        refreshTable();
        } catch (Exception ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    


    @FXML
    private void GetSelected(MouseEvent event) {
     index = TablePlanning.getSelectionModel().getSelectedIndex();
    if(index<=-1){
        return;
    }
    //ObservableList<String> List = FXCollections.observableArrayList("Bus","Metro","Train");
    DateDep.setText(DateDepColumn.getCellData(index).toString());
    DateArr.setText(DateArrColumn.getCellData(index).toString());
    typeTran.setValue(TypeColumn.getCellData(index).toString());
    CirDepComboBox.setValue(CirDepColumn.getCellData(index).toString());
    CirArrComboBox.setValue(CirArrColumn.getCellData(index).toString());
    NumeroMComboBox.setValue(NumMCol.getCellData(index).toString());
    }

    @FXML
    private void SelectedCirDep(ActionEvent event) {
        //System.out.println("nawres");
   /* String DEPART = CirDepComboBox.getValue().toString();
       // System.out.println(DEPART);
    ObservableList<String> CircuitArr = FXCollections.observableArrayList();        
    String SQLgetCirArr="select arriveeC from circuit where departC='"+DEPART+"' " ;
  */
       try {
           /* Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SQLgetCirArr);
            
            while (s.next()) {
            String CirArr = s.getString("arriveeC");
            CircuitArr.add(CirArr);
            }
            CirArrComboBox.setItems(CircuitArr);
            
          */   
           // String DEP =CirDepComboBox.getValue().toString();
           // String ARR =CirArrComboBox.getValue().toString();
            String SQLgetIDCicruit="SELECT idCircuit from circuit where departC = 'Barcelone' AND arriveeC = 'Manouba'";
            Statement st = cnx.createStatement();
             ResultSet r = st.executeQuery(SQLgetIDCicruit);
             ObservableList<Integer> IDliste = FXCollections.observableArrayList();      
             
          //for give type for any variable : ((Object)r.getInt("idCircuit")).getClass().getSimpleName()
             while(r.next()){
                 int idd=r.getInt("idCircuit");
                 IDliste.add(idd);
             }
           //  System.out.print(IDliste.get(0));
          /*  Statement st = cnx.createStatement();
            ResultSet r = st.executeQuery(SQLgetIDCicruit);
            //String IDD= r.getInt("idCircuit");
            ObservableList<Integer> IDliste = FXCollections.observableArrayList();        
            while(r.next()){
            Integer IDD=r.getInt(1);
            IDliste.add(IDD);
            }
            System.out.println(IDliste);
          */
            
              } catch (Exception ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }

 

    @FXML
    private void SelectedCirArr(ActionEvent event) {
   String ARRIVER = CirArrComboBox.getValue().toString();
   ObservableList<String> CircuitDep = FXCollections.observableArrayList();        
   String SQLgetCirDep="select departC from circuit where arriveeC='"+ARRIVER+"' " ;
     try {
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SQLgetCirDep);
            
            while (s.next()) {
            String CirDep = s.getString("departC");
            CircuitDep.add(CirDep);
            }
            CirDepComboBox.setItems(CircuitDep);
              } catch (SQLException ex) {
            Logger.getLogger(CrudPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private void refreshTable(){
                        ObservableList<PlanningItem> Plan = FXCollections.observableArrayList();        
        try{
            String SqlJointurePlanning="select C.departC AS DepartCircuit , C.arriveeC AS ArriverCircuit ,"
                    + " M.type AS TypeMoyen, M.capacite AS capacitee, P.dateD AS dateDepart , P.DateA AS dateArriver,"
                    + " M.numMoy AS NumeroM  from circuit AS C"
                    + " , moyenstransport AS M , planning AS P where P.idMoy= M.idMoy AND P.idCir=C.idCircuit ;" ;
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
        String departCircuit = s.getString("DepartCircuit");
        String arriveeCircuit = s.getString("ArriverCircuit");
        String typeMoyen = s.getString("TypeMoyen");
        int capacitee = s.getInt("capacitee");
        Time dateDepart = s.getTime("dateDepart");
        Time dateArriver = s.getTime("dateArriver");
        String NumeroM = s.getString("NumeroM");
        PlanningItem planningItem = new PlanningItem(departCircuit, arriveeCircuit, typeMoyen, capacitee, dateDepart, dateArriver ,NumeroM);        
        Plan.add(planningItem);
            }
       
        CirDepColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("departCircuit"));
        CirArrColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("arriveeCircuit"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("typeMoyen"));
        NbPlaceColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,Integer>("capacitee"));
        DateDepColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,Time>("dateDepart"));
        DateArrColumn.setCellValueFactory(new PropertyValueFactory<PlanningItem,Time>("dateArriver"));
        NumMCol.setCellValueFactory(new PropertyValueFactory<PlanningItem,String>("NumeroM"));

        TablePlanning.setItems(Plan);
    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }       
}
    
}


