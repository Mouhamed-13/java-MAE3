/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.AffectationDao;
import dao.ClasseDao;
import dao.InscriptionDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import models.Affectation;
import models.Classe;
import models.Etudiant;
import models.Inscription;
import models.Professeur;
import models.User;

/**
 *
 * @author ASUS
 */
public class GestionEtudiant implements IGestionEtudiant {

    private ClasseDao daoClasse=new ClasseDao();
    private UserDao daoUser=new UserDao();
    private InscriptionDao daoInscription=new InscriptionDao();
    private AffectationDao daoAffectation=new AffectationDao();

    @Override
    public List<Classe> listerClasseUnEnseignant(Professeur prof) {
        //select * from Affectation where professeur_id=prof.id
        
        return daoAffectation.selectClasseUnProfesseur(prof);
    }
    
    @Override
    public int addUser(User u) {
        return daoUser.insert(u);
    }

    @Override
    public boolean inscrireEtudiant(Etudiant etu, Classe cl,String annee) {
        
        if(etu.getId()==0){
           etu.setMatricule(this.generateMatricule(annee,etu.getId()));
           
           int id=daoUser.insert(etu);
           etu.setMatricule(this.generateMatricule(annee,id));
           
           //System.out.print(etu.getMatricule());
           etu.setId(id);
           daoUser.update(etu);
               
           
           
        }
        
        Inscription ins=new Inscription(annee,etu,cl);
        
        return daoInscription.insert(ins) != 0;
        
    }
    
    private String generateMatricule(String annees,int id){
     String[] annee;
     annee=annees.split("-");
    
    String matricule="MAT"+annee[1]+String.valueOf(id);
    return matricule;
    }

    @Override
    public int addClasse(Classe cl) {
        return daoClasse.insert(cl);
    }

    @Override
    public List<Classe> listerClasse() {
        return daoClasse.selectAll();
    }

    @Override
    public List<String> listerModuleParClasseUnProf(Professeur prof, Classe cl) {
       return daoAffectation.selectModuleParClasseUnProf(prof, cl);
    }

    @Override
    public boolean modifierClasse(Classe cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean affecterProfesseur(Professeur prof, Classe classe,String annee,List<String> matieresEns) {
        
        Affectation aff=new Affectation(prof,classe,annee,matieresEns);
        
        return daoAffectation.insert(aff) != 0;
    }

    @Override
    public User seConnecter(String login, String pwd) {
        return daoUser.selectUserByLoginAndPwd(login,pwd);
    }

    @Override
    public boolean rechercherClasseByLibelle(String libelle) {
        return daoClasse.selectClasseByLibelle(libelle) != null;
        
    }

    @Override
    public boolean rechercherEtudiantParMatricule(String matricule) {
        
        return daoUser.selectEtudiantParMatricule(matricule) != null;
        
    }

    @Override
    public List<Etudiant> listerEtudiantParClasse(Classe cl, String annee) {
        
        return daoUser.selectEtudiantByClasseByAnnee(cl, annee);
        
    }

    @Override
    public List<String> getAnneeScolaire() {
        List<String> lAnneeScolaire=new ArrayList<>();
        lAnneeScolaire.add("2020-2021");
        lAnneeScolaire.add("2019-2020");
        lAnneeScolaire.add("2018-2019");
        lAnneeScolaire.add("2017-2018");
        
        return lAnneeScolaire;
    }
    
    @Override
    public List<String> getMatieres() {
        List<String> lMat=new ArrayList<>();
        lMat.add("PhP");
        lMat.add("C");
        lMat.add("Python");
        lMat.add("Java");
        lMat.add("Flutter");
        lMat.add("React native");
        lMat.add("Angular js");
        lMat.add("Mathématiques");
        
        return lMat;
    }
    
    @Override
    public Etudiant getEtudiantByMatricule(String matricule) {
        List<Etudiant> etudiants = daoUser.selectEtudiant();

        for (Etudiant etu : etudiants) {
            if (etu.getMatricule().compareTo(matricule) == 0) {
                return etu;
            }
        }
        return null;
    }

    @Override
    public boolean rechercherProfesseurParNci(String nci) {
        return daoUser.selectProfesseurParNci(nci) != null;
    }

    @Override
    public Professeur getProfesseurByNci(int nci) {
        List<Professeur> professeurs = daoUser.selectProfesseur();

        for (Professeur prof : professeurs) {
            if (prof.getNci() == nci) {
                return prof;
            }
        }
        return null;
    }

    @Override
    public boolean updateMatiere(User u) {
        //System.out.println(daoUser.updateMat(u));
        return daoUser.updateMat(u) == 0;
    }

    @Override
    public boolean noterEtudiant(String module, Classe classe, List<Etudiant> etu, Professeur prof) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
}
