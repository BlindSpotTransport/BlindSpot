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
public interface IServiceEvaluation <T> {
    public void ajouter_evaluation (T t);
    public  List<T> afficher_evaluation (); 
    public void modifier_evaluation(T t);
    public void supprimer_evaluation(T t);
    public int compter_eva(); 
}
