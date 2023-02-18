/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author hp
 */
public interface NewInterface1<T> {
    
      public void ajouter(T t);
      public void supprimer(T t);
      public List<T> afficher();
      
      
    
}
