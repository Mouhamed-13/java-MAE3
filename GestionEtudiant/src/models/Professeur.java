/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Professeur extends User {
    
    private String grade;
    private List<String> matieres;
    private int nci;
    //private 

    public Professeur() {
        type="Professeur";
    }

    public Professeur(String nomComplet, int nci, String grade, String photo,List<String> matieres) {
       super(nomComplet, photo);
       this.grade = grade;
       this.nci = nci;
       this.matieres=matieres;
       type="Professeur";
       
    }

    
    

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<String> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<String> matieres) {
        this.matieres = matieres;
    }

    public int getNci() {
        return nci;
    }

    public void setNci(int nci) {
        this.nci = nci;
    }
    
    

    @Override
    public String toString() {
        return super.toString()+"Professeur{" + "grade=" + grade + ", matieres=" + matieres + '}';
    }
    
}
