/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.services;

import java.util.List;

/**
 *
 * @author 21626
 */
public interface NewInterface<T> {
    public void add(T t);
    public List<T> getAll();
    //public T findById(int id);
 
    public void delete (T t);
  //  public void update (T t);
    
}
