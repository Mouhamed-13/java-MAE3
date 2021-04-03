/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IDao<T> {
    
    public int insert(T obj);
    public int update(T obj);
    public int insert(int id);
    public List<T> selectAll();
    
    
}
