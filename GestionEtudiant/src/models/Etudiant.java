/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class Etudiant  extends User{
    
    private String tuteur;
    private String matricule;
    private boolean absence;

    public Etudiant() {
        type="Etudiant";
    }

    public Etudiant(String tuteur, String matricule, String nomComplet,String photo) {
        super(nomComplet, photo);
        this.tuteur = tuteur;
        this.matricule = matricule;
        type="Etudiant";
    }
    
    
    
    

    public String getTuteur() {
        return tuteur;
    }

    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public boolean getAbsence() {
        return absence;
    }

    public void setAbsence(Boolean absence) {
        this.absence = absence;
    }
    
    
    

    @Override
    public String toString() {
        return super.toString()+"Etudiant{" + "tuteur=" + tuteur + '}';
    }

    
    
    

    
    
    
    
}
