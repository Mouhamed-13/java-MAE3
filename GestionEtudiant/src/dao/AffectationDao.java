/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Affectation;
import models.Classe;
import models.Professeur;
import services.Lib;

/**
 *
 * @author ASUS
 */
public class AffectationDao implements IDao<Affectation> {
    
    private MySQL mysql=new MySQL();
    private ClasseDao daoClasse=new ClasseDao();
    
    private final String SQL_INSERT="INSERT INTO `affectation` (`matiere_ens`, `create_at`, `classe_id`, `professeur_id`, `annee`) VALUES (?, ?, ?, ?, ?);";
    private final String SQL_SELECT_ALL="select * from classe";
    private final String SQL_SELECT_AFFECT_PROF="select * from affectation where professeur_id=?";
    private final String SQL_SELECT_MODULES_CLASSE_PROF="select * from affectation where professeur_id=? and classe_id=?";
    
    @Override
    public int insert(Affectation obj) {
        int nbreLigne=0;
        
        //Connexion BD
        mysql.connexionBD();
        try {
            //Preparer la requête
            mysql.initPs(SQL_INSERT);
            mysql.getPs().setString(1, Lib.listToString(obj.getMatieresEns()));
            mysql.getPs().setString(2, obj.getCreateAt().toString());
            mysql.getPs().setInt(3, obj.getClasse().getId());
            mysql.getPs().setInt(4, obj.getProf().getId());
            mysql.getPs().setString(5, obj.getAnnee());
           
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
    public int update(Affectation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Affectation> selectAll() {
        List<Affectation> lAff=null;
        //Connexion BD
        mysql.connexionBD();
        
            
            //Executer la requete 
            ResultSet rs=mysql.executeSelect(SQL_SELECT_ALL);
        try {
            //Recuperation des resultats
            lAff=new ArrayList<>();
            while(rs.next()){
                Affectation aff=new Affectation();
                aff.setId(rs.getInt("id"));
                aff.setAnnee(rs.getString("annee"));
                aff.setCreateAt(LocalDate.parse(rs.getString("createAt")));
                lAff.add(aff);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return lAff;
    }
    
    public List<Classe> selectClasseUnProfesseur(Professeur prof){
     
        Classe cl=null;
        List<Classe> lClasse=new ArrayList<Classe>();
        //Connexion BD
        mysql.connexionBD();
        
        try {
            mysql.initPs(SQL_SELECT_AFFECT_PROF);
            mysql.getPs().setInt(1, prof.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            //Recuperation des resultats
            while(rs.next()){
                cl=new Classe();
                int idClasse=rs.getInt("classe_id");
                for(Classe elt:daoClasse.selectAll()){
                    if(elt.getId()==idClasse){
                        lClasse.add(elt);
                    }
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return lClasse;
    }
    
    public List<String> selectModuleParClasseUnProf(Professeur prof, Classe cl){
     
        List<String> lModules=null;
        //Connexion BD
        mysql.connexionBD();
        
        try {
            mysql.initPs(SQL_SELECT_MODULES_CLASSE_PROF);
            mysql.getPs().setInt(1, prof.getId());
            mysql.getPs().setInt(2, cl.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            //Recuperation des resultats
            if(rs.next()){
                if(rs.getString("matiere_ens")!=null){
                    lModules=Lib.stringToList(rs.getString("matiere_ens"));
                }else{
                    lModules=new ArrayList<>();
                }
                      
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return lModules;
    
    }
}
