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
public interface IServiceRepReclam <T> {
     public void ajouter_repreclamation (T t);
    public  List<T> afficher_repreclamation (); 
    public void supprimer_repreclamation(T t);
    public int compter_repreclam();
    public void modifier_repreclamation(T t);
}
