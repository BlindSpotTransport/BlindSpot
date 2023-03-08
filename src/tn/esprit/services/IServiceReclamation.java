/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;

/**
 *
 * @author sbs
 */
public interface IServiceReclamation <T> {
     public void ajouter_reclamation (T t);
    public  List<T> afficher_reclamation (); 
    public void modifier_reclamation(T t);
    public void supprimer_reclamation(T t);
    public int compter_rec();
    
}
