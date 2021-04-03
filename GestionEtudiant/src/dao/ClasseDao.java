/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Classe;

/**
 *
 * @author ASUS
 */
public class ClasseDao implements IDao<Classe>{
    
    private final String SQL_SELECT_ALL="select * from classe";
    private final String SQL_SELECT_CLASSE_BY_LIBELLE="select * from classe where libelle=?"; // ? veut dire que c'est une variable
    private final String SQL_INSERT="INSERT INTO `classe` (`libelle`) VALUES (?);";
    
    private MySQL mysql=new MySQL();

    @Override
    public int insert(Classe obj) {
        int id=0;
        //Connexion BD
        mysql.connexionBD();
        try {
            mysql.initPs(SQL_INSERT);
            //Preparer la requête
            mysql.getPs().setString(1, obj.getLibelle());
            //Executer la requete //Recuperation des resultats
            mysql.executeMaj();
            
            ResultSet rs=mysql.getPs().getGeneratedKeys();
            
            rs.next();
            id=rs.getInt(1);
            
            //Fermer la connexion
            mysql.fermerBD();
        } catch (SQLException ex) {
            System.out.print("Erreur !!!");
        }
        return id;
    }

    @Override
    public int update(Classe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Classe> selectAll() {
        
        List<Classe> lClasses=null;
        //Connexion BD
        mysql.connexionBD();
        
            
            //Executer la requete 
            ResultSet rs=mysql.executeSelect(SQL_SELECT_ALL);
        try {
            //Recuperation des resultats
            lClasses=new ArrayList<>();
            while(rs.next()){
                Classe cl=new Classe();
                cl.setId(rs.getInt("id"));
                cl.setLibelle(rs.getString("libelle"));
                lClasses.add(cl);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return lClasses;
    }
    
    public Classe selectClasseByLibelle(String libelle){
        Classe cl=null;
        //Connexion BD
        mysql.connexionBD();
        
        try {
            mysql.initPs(SQL_SELECT_CLASSE_BY_LIBELLE);
            mysql.getPs().setString(1, libelle);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            //Recuperation des resultats
            if(rs.next()){
                cl=new Classe();
                cl.setId(rs.getInt("id"));
                cl.setLibelle(rs.getString("libelle"));   
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return cl;
    }
    
    
}
