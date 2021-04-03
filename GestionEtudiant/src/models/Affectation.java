/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class Affectation {
    
    private int id;
    private LocalDate createAt;
    private String annee;
    private List<String> matieresEns;
    
    //Propriete de Navigation
    private Professeur prof;
    private Classe classe;

    public Affectation(Professeur prof, Classe classe,String annee,List<String> matieresEns) {
        this.prof = prof;
        this.classe = classe;
        this.annee=annee;
        this.matieresEns=matieresEns;
        this.createAt=LocalDate.now();
    }

    public Affectation() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Professeur getProf() {
        return prof;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public List<String> getMatieresEns() {
        return matieresEns;
    }

    public void setMatieresEns(List<String> matieresEns) {
        this.matieresEns = matieresEns;
    }
    
    
    
    
    
}
