/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MySQL implements IDatabase{
    
    private Connection  cnx;
    private PreparedStatement ps=null;

    public PreparedStatement getPs() {
        return ps;
    }

    
    
    
    @Override
    public void connexionBD() {
        try {
            // 1-Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // 2-Ouvrir Connexion
            cnx=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gestion_etudiant?serverVersion=5.7","root","");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erreur de chargement du Driver");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
        } 
    }

    @Override
     public void fermerBD() {
        if(cnx!=null){
        cnx=null;
        }
    }

    @Override
    public ResultSet executeSelect(String sql) {
        ResultSet rs=null;
        
        initPs(sql);
        //System.out.print(sql);
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
        
    }

    @Override
    public int executeMaj(String sql) {
        int nbreLigne=0;
        
        initPs(sql);
        try {
            nbreLigne=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nbreLigne;
        
    }

    @Override
    public void initPs(String sql) {
        
        try {
            if(sql.toLowerCase().startsWith("insert")){
                ps=cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            }else{
                ps=cnx.prepareStatement(sql);
            }
            
        } catch (SQLException ex) {
            System.out.print("Erreur !!!");
        }
        
    }

    @Override
    public ResultSet executeSelect() {
        ResultSet rs=null;
        

        //System.out.print(sql);
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }

    @Override
    public int executeMaj() {
        int nbreLigne=0;
      
        try {
            
            nbreLigne=ps.executeUpdate();
            //System.out.print(nbreLigne);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nbreLigne;
    }
    
}
