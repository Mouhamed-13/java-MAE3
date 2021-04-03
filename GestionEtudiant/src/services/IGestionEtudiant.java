/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.Classe;
import models.Etudiant;
import models.Professeur;
import models.User;

/**
 *
 * @author ASUS
 */
public interface IGestionEtudiant {
    
    public List<Classe> listerClasseUnEnseignant(Professeur prof);
    
    public boolean inscrireEtudiant(Etudiant etu,Classe cl,String annee);
    
    public int addClasse(Classe cl);
    
    public int addUser(User u);
    
    public List<Classe> listerClasse();
    
    public List<String> listerModuleParClasseUnProf(Professeur prof,Classe cl);
    
    public boolean modifierClasse(Classe cl);
    
    public boolean affecterProfesseur(Professeur prof, Classe classe,String annee,List<String> matieresEns);
    
    public boolean noterEtudiant(String module, Classe classe, List<Etudiant> etu, Professeur prof);
    
    public User seConnecter(String login, String pwd);
    
    public boolean rechercherClasseByLibelle(String libelle);
    
    public boolean rechercherEtudiantParMatricule(String matricule);
    
    public boolean rechercherProfesseurParNci(String nci);
    
    public List<Etudiant> listerEtudiantParClasse(Classe cl,String annee);
    
    public List<String> getAnneeScolaire();
    
    public List<String> getMatieres();
    
    public Etudiant getEtudiantByMatricule(String matricule);
    
    public Professeur getProfesseurByNci(int nci);
    
    public boolean updateMatiere(User u);
    
    
    
}
