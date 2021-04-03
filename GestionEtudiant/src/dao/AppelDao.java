/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.Appel;
import services.Lib;

/**
 *
 * @author ASUS
 */
public class AppelDao implements IDao<Appel> {
    
    private MySQL mysql=new MySQL();
    
    private final String SQL_INSERT="INSERT INTO `appel` (`module`, `dateAbs`, `classe_id`, `professeur_id`, `etudiants`) VALUES (?, ?, ?, ?, ?);";

    @Override
    public int insert(Appel obj) {
        
        int nbreLigne=0;
        
        //Connexion BD
        mysql.connexionBD();
        try {
            //Preparer la requête
            mysql.initPs(SQL_INSERT);
            mysql.getPs().setString(1, obj.getModule() );
            mysql.getPs().setString(2, obj.getDateAbs().toString());
            mysql.getPs().setInt(3, obj.getClasse().getId());
            mysql.getPs().setInt(4, obj.getProf().getId());
            mysql.getPs().setString(5, obj.getEtu().toString());
           
            //Executer la requete //Recuperation des resultats
            mysql.executeMaj();
            ResultSet rs=mysql.getPs().getGeneratedKeys();
            
            rs.next();
            nbreLigne=rs.getInt(1);
            
            //Fermer la connexion
            mysql.fermerBD();
        } catch (SQLException ex) {
            System.out.print("");
        }
        
        return nbreLigne;
        
    }

    @Override
    public int update(Appel obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appel> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
