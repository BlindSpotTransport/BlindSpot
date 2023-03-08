/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;
import tn.esprit.entity.Commentaire;
/**
 * @author sbs
 */
public interface IServiceCommentaire <T> {
    public void ajouter_commentaire (T t);
    public  List<T> afficher_commentaire (); 
    public void modifier_commentaire(T t);
    public void supprimer_commentaire(T t);
    public int compter();
}
