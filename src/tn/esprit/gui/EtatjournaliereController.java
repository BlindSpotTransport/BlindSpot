/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import tn.esprit.entity.Planning;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class EtatjournaliereController implements Initializable {

    @FXML
    private TableColumn<Object[], String> TypeTCol;
    @FXML
    private TableColumn<Object[], String> NumCol;
    @FXML
    private TableColumn<Object[], String> TotalCol;
    @FXML
    private TableView<Object[]> TableJournaliere;
    ObservableList<Object[]> listeP;
String type;
String NumeroT;
Integer Sumprix;
    Connection cnx;

    /**
     * Initializes the controller class.
     */
    public EtatjournaliereController() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Object[]> dataList = new ArrayList<>();

        try {
            String SqlGetJournal = "select type , NumeroT, SUM(prix) as sum from reservation group BY type";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(SqlGetJournal);

            while (s.next()) {
                type = s.getString("type");
                NumeroT = s.getString("NumeroT");
                Sumprix = s.getInt("sum");
                Object[] rowData = {type, NumeroT, Sumprix};
                dataList.add(rowData);
            }
            listeP = FXCollections.observableArrayList(dataList);

            TypeTCol.setCellValueFactory(c -> new SimpleStringProperty((String) c.getValue()[0]));
            NumCol.setCellValueFactory(c -> new SimpleStringProperty((String) c.getValue()[1]));
            TotalCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue()[2].toString()));

            TableJournaliere.setItems(listeP);

        } catch (SQLException ex) {
            Logger.getLogger(EtatjournaliereController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @FXML
    private void GetSelectedTable(MouseEvent event) {
    
    
    
    }

    @FXML
    private void PDFButton(ActionEvent event) throws DocumentException, IOException {
        Object[] com= TableJournaliere.getSelectionModel().getSelectedItem();
        
        if (com==null) { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur un commentaire !");
            alert.showAndWait();                 
        } else {
        Object c = new Object();
        c= TableJournaliere.getSelectionModel().getSelectedItem();
            Document document = new Document();
        if (TypeTCol.getText().isEmpty()||NumCol.getText().isEmpty() ||TotalCol.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Impression non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
          
            PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(document, new FileOutputStream("Commentaire.pdf"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EtatjournaliereController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(EtatjournaliereController.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
            com.itextpdf.text.Font font = new com.itextpdf.text.Font();
            font.setSize(20);
            font.setColor(BaseColor.RED);
            font.isBold();
            font.isItalic();

            com.itextpdf.text.Font fontSmall = new com.itextpdf.text.Font();
            fontSmall.setSize(8);
            document.add(new LineSeparator());

            document.add(new Paragraph("Etat journliere", font));
            document.add(new Paragraph(" "));

            document.add(new LineSeparator());

            document.add(new Paragraph("Moyens de transport : " + type));
            document.add(new Paragraph("numero de transport : " + NumeroT));
            document.add(new Paragraph("Somme de prix : " + Sumprix));
            
           
            document.add(new Paragraph(" "));

            document.newPage();
            document.close();

            writer.close();

            Desktop.getDesktop().open(new File("Commentaire.pdf"));
        
    }
    }   
    }
    
}
