/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import tn.esprit.entity.Reclamation;
import tn.esprit.entity.RepReclamation;

/**
 *
 * @author sbs
 */
public class Variables {
          private static Reclamation recClicked = new Reclamation();
           private static RepReclamation repClicked = new RepReclamation();

    public static RepReclamation getRepClicked() {
        return repClicked;
    }

    public static void setRepClicked(RepReclamation repClicked) {
        Variables.repClicked = repClicked;
    }

    public static Reclamation getRecClicked() {
        return recClicked;
    }

    public static void setRecClicked(Reclamation recClicked) {
        Variables.recClicked = recClicked;
    }

}
