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
import models.AC;
import models.Classe;
import models.Etudiant;
import models.Professeur;
import models.RP;
import models.User;
import services.Lib;

/**
 *
 * @author ASUS
 */
public class UserDao implements IDao<User>{
    
    private MySQL mysql=new MySQL();
    
    private final String SQL_SELECT_USER_LOGIN_PWD="select * from user where login=? and pwd=?";
    private final String SQL_SELECT_ETUDIANT_MATRICULE="select * from user where matricule=?";
    private final String SQL_UPDATE_MATRICULE="UPDATE `user` SET `matricule` = ? WHERE `user`.`id` = ?;";
    private final String SQL_UPDATE_MATIERES="UPDATE `user` SET `matieres` = ? WHERE `user`.`id` = ?;";
    private final String SQL_SELECT_ETUDIANT="select * from user where type='Etudiant'";
    private final String SQL_SELECT_PROFESSEUR="select * from user where type='Professeur'";
    private final String SQL_SELECT_PROFESSEUR_NCI="select * from user where nci=?";
    private final String SQL_SELECT_MATIERE_PROFESSEUR="select matieres from user where nci=?";
    private final String SQL_INSERT="INSERT INTO `user` (`nomComplet`, `login`, `pwd`, `grade`, `matieres`, `tuteur`, `matricule`,`nci`, `type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_SELECT_ETUDIANT_CLASSE="select u.id,u.matricule,u.nomComplet,u.tuteur from user u,inscription i "
                                                +"where u.id=i.etudiant_id"
                                                +" and i.classe_id=? and i.annee=?"
                                                +" and u.type='Etudiant'";
    
    @Override
    public int insert(User obj) {
        int id=0;
        //Connexion BD
        mysql.connexionBD();
        try {
            //Preparer la requête
            mysql.initPs(SQL_INSERT);
            String type=obj.getType();
            mysql.getPs().setString(1, obj.getNomComplet());
            mysql.getPs().setString(9, obj.getType());
                if (type.compareTo("Etudiant")==0){
                    
                    mysql.getPs().setString(2, null);
                    mysql.getPs().setString(3, null);
                    mysql.getPs().setString(4, null);
                    mysql.getPs().setString(5, null);
                    mysql.getPs().setString(6, ((Etudiant)obj).getTuteur());
                    mysql.getPs().setString(7, ((Etudiant)obj).getMatricule());
                    mysql.getPs().setInt(8, 0);
                    
                    
                }else{
                    
                    
                    if (type.compareTo("Professeur")==0){
                    mysql.getPs().setString(2, null);
                    mysql.getPs().setString(3, null);
                    mysql.getPs().setString(4, ((Professeur)obj).getGrade());
                    mysql.getPs().setString(5, Lib.listToString(((Professeur)obj).getMatieres()));
                    //mysql.getPs().setString(5, null);
                    mysql.getPs().setString(6,null);
                    mysql.getPs().setString(7, null);
                    mysql.getPs().setInt(8, ((Professeur)obj).getNci());
                    
                }else{
                        //RP ou AC
                    mysql.getPs().setString(2, obj.getLogin());
                    mysql.getPs().setString(3, obj.getPwd());
                    mysql.getPs().setString(4, null);
                    mysql.getPs().setString(5, null);
                    mysql.getPs().setString(6, null);
                    mysql.getPs().setString(7, null);
                    mysql.getPs().setString(8, null);
                    
                    }
                
                
                }
                
                
            //System.out.println(mysql);
           
            //Executer la requete //Recuperation des resultats
            mysql.executeMaj();
            ResultSet rs=mysql.getPs().getGeneratedKeys();
            
            rs.next();
            id=rs.getInt(1);
            
            //Fermer la connexion
            mysql.fermerBD();
        } catch (SQLException ex) {
            System.out.print("");
        }
        return id;
    }

    @Override
    public int update(User obj) {
        int id=0;
        //Connexion BD
        mysql.connexionBD();
        try {
            //Preparer la requête
            mysql.initPs(SQL_UPDATE_MATRICULE);
            mysql.getPs().setString(1, ((Etudiant)obj).getMatricule());
            mysql.getPs().setInt(2, obj.getId());
                
            //System.out.print(mysql.getPs());
            //Executer la requete //Recuperation des resultats
            mysql.executeMaj();
            ResultSet rs=mysql.getPs().getGeneratedKeys();
            
            rs.next();
            id=rs.getInt(1);
            
            //Fermer la connexion
            mysql.fermerBD();
        } catch (SQLException ex) {
            System.out.print("");
        }
        return id;
    }
    
     public int updateMat(User obj) {
        int id=0;
        //Connexion BD
        mysql.connexionBD();
        try {
            //Preparer la requête
            mysql.initPs(SQL_UPDATE_MATIERES);
            mysql.getPs().setString(1, Lib.listToString(((Professeur)obj).getMatieres()));
            mysql.getPs().setInt(2, obj.getId());
                
            //System.out.print(mysql.getPs());
            //Executer la requete //Recuperation des resultats
            mysql.executeMaj();
            ResultSet rs=mysql.getPs().getGeneratedKeys();
            
            rs.next();
            //System.out.print(rs);
            id=rs.getInt(1);
            
            //Fermer la connexion
            mysql.fermerBD();
        } catch (SQLException ex) {
            System.out.print("");
        }
        return id;
    }

    @Override
    public int insert(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User selectUserByLoginAndPwd(String login, String pwd) {
         User userConnect=null;
        //Connexion BD
        mysql.connexionBD();
        
        try {
            mysql.initPs(SQL_SELECT_USER_LOGIN_PWD);
            mysql.getPs().setString(1, login);
            mysql.getPs().setString(2, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            //Recuperation des resultats
            if(rs.next()){
                String type=rs.getString("type");
                if (type.compareTo("AC")==0){
                userConnect=new AC();
                }else{
                        if (type.compareTo("Professeur")==0){
                    userConnect=new Professeur();
                    ((Professeur)userConnect).setNci(rs.getInt("nci"));
                    ((Professeur)userConnect).setMatieres(Lib.stringToList(rs.getString("matieres")));
                    ((Professeur)userConnect).setGrade(rs.getString("grade"));
                    }
                        
                        else{
                            userConnect=new RP();
                        }
                }
                
                userConnect.setId(rs.getInt("id"));
                userConnect.setNomComplet(rs.getString("nomComplet"));
                userConnect.setLogin(rs.getString("login"));
                userConnect.setPwd(rs.getString("pwd"));
                
            }
        } catch (SQLException ex) {
            System.out.print("Erreur !!!!");
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return userConnect;
    }
    
    public Etudiant selectEtudiantParMatricule(String matricule){
     
        Etudiant etu=null;
        //Connexion BD
        mysql.connexionBD();
        
        try {
            mysql.initPs(SQL_SELECT_ETUDIANT_MATRICULE);
            mysql.getPs().setString(1, matricule);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            //Recuperation des resultats
            if(rs.next()){
                etu=new Etudiant();
                etu.setId(rs.getInt("id"));
                etu.setMatricule(rs.getString("matricule")); 
                etu.setNomComplet(rs.getString("nomComplet"));
                etu.setTuteur(rs.getString("tuteur"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return etu;
    
    }
    
     public List<Etudiant> selectEtudiant(){
     
        List<Etudiant> lEtudiant=null;
        //Connexion BD
        mysql.connexionBD();
        
        mysql.initPs(SQL_SELECT_ETUDIANT);
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            lEtudiant=new ArrayList<>();
            //Recuperation des resultats
            while(rs.next()){
                Etudiant etu=new Etudiant();
                etu.setId(rs.getInt("id"));
                etu.setMatricule(rs.getString("matricule")); 
                etu.setNomComplet(rs.getString("nomComplet"));
                etu.setTuteur(rs.getString("tuteur"));
                lEtudiant.add(etu);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return lEtudiant;
    }
     
    public List<Professeur> selectProfesseur(){
     
        List<Professeur> lProfesseur=null;
        //Connexion BD
        mysql.connexionBD();
        
        mysql.initPs(SQL_SELECT_PROFESSEUR);
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            lProfesseur=new ArrayList<>();
            //Recuperation des resultats
            while(rs.next()){
                Professeur prof=new Professeur();
                prof.setId(rs.getInt("id"));
                prof.setNci(rs.getInt("nci")); 
                prof.setNomComplet(rs.getString("nomComplet"));
                prof.setGrade(rs.getString("grade"));
                prof.setMatieres(Lib.stringToList(rs.getString("matieres")));
                lProfesseur.add(prof);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        //System.out.println(lProfesseur);
        return lProfesseur;
    }
    
    
    
    public List<Etudiant> selectEtudiantByClasseByAnnee(Classe cl,String annee){
    
        List<Etudiant> lEtudiant=null;
        //Connexion BD
        mysql.connexionBD();
        
        try {
            mysql.initPs(SQL_SELECT_ETUDIANT_CLASSE);
            mysql.getPs().setInt(1, cl.getId());
             mysql.getPs().setString(2, annee);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
        try {
            lEtudiant=new ArrayList<>();
            //Recuperation des resultats
            while(rs.next()){
                Etudiant etu=new Etudiant();
                etu=new Etudiant();
                etu.setId(rs.getInt("id"));
                etu.setMatricule(rs.getString("matricule")); 
                etu.setNomComplet(rs.getString("nomComplet"));
                etu.setTuteur(rs.getString("tuteur"));
                lEtudiant.add(etu);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return lEtudiant;
    
    }

    public Professeur selectProfesseurParNci(String nci) {
        Professeur prof=null;
        //Connexion BD
        mysql.connexionBD();
        
        try {
            mysql.initPs(SQL_SELECT_PROFESSEUR_NCI);
            mysql.getPs().setString(1, nci);
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Executer la requete 
            ResultSet rs=mysql.executeSelect();
            
        try {
            //Recuperation des resultats
            if(rs.next()){
                prof=new Professeur();
                prof.setId(rs.getInt("id"));
                prof.setNci(rs.getInt("nci")); 
                prof.setNomComplet(rs.getString("nomComplet"));
                prof.setGrade(rs.getString("grade"));
                prof.setMatieres(Lib.stringToList(rs.getString("grade")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Fermer la connexion
            mysql.fermerBD();
        
        return prof;
    }
            
    
}
