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
public class Appel {
    
    private int id;
    private String module;
    private LocalDate dateAbs;
    private Classe classe;
    private List<Etudiant> etu;
    private Professeur prof;

    public Appel(String module, Classe classe, List<Etudiant> etu, Professeur prof) {
        this.module = module;
        this.classe = classe;
        this.etu = etu;
        this.prof = prof;
        this.dateAbs=LocalDate.now();
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public LocalDate getDateAbs() {
        return dateAbs;
    }

    public void setDateAbs(LocalDate dateAbs) {
        this.dateAbs = dateAbs;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Etudiant> getEtu() {
        return etu;
    }

    public void setEtu(List<Etudiant> etu) {
        this.etu = etu;
    }

    public Professeur getProf() {
        return prof;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }
    
    
    
    
    
    
    
    
}
