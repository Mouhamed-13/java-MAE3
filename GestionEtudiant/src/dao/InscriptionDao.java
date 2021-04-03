/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.Etudiant;
import models.Inscription;
import models.Professeur;

/**
 *
 * @author ASUS
 */
public class InscriptionDao implements IDao<Inscription>{
    private MySQL mysql=new MySQL();
    
    private final String SQL_INSERT="INSERT INTO `inscription` (`create_at`, `annee`, `classe_id`, `etudiant_id`) VALUES (?, ?, ?, ?);";

    @Override
    public int insert(Inscription obj) {
        int nbreLigne=0;
        
        //Connexion BD
        mysql.connexionBD();
        try {
            //Preparer la requête
            mysql.initPs(SQL_INSERT);
            mysql.getPs().setString(1, obj.getCreateAt().toString());
            mysql.getPs().setString(2, obj.getAnnee());
            mysql.getPs().setInt(3, obj.getClasse().getId());
            mysql.getPs().setInt(4, obj.getEtu().getId());
           
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
    public int update(Inscription obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inscription> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
