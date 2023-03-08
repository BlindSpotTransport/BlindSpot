/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author pc
 */
public class Divers {
 
    public static void imprimerJtable( String titre){
        MessageFormat entete = new MessageFormat(titre);
        MessageFormat pied = new MessageFormat("Page{0, number, integer}");
    
//    try{
//    jt.print(JTable.PrintMode.FIT_WIDTH, entete ,pied);
//    }catch(Exception e){
//            JOptionPane.showConfirmDialog(null,"erreur :\n"+e,"Impression",JOptionPane.ERROR_MESSAGE);
//}
}
}
