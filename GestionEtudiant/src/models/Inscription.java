/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class Inscription {
    
    private int id;
    private LocalDate createAt;
    private String annee;
    
    //Propriete de Navigation
    private Etudiant etu;
    private Classe classe;
    
    //Dés qu'on a un polymorphisme d'objet( Ex: User u=new Etudiant() ) on peut faire qu'appelle au méthode redéfinis ou méthodes polyphormes 
    // UpCasting User u=new Etudiant()
    // DownCasting => Convertir un objet de classe mére en classe fille et permet d'accéder temporellement
    // aux méthodes de la classe fille => ( (Etudiant)u ) 

    public Inscription() {
    }

    public Inscription(String annee, Etudiant etu, Classe classe) {
        this.annee = annee;
        this.etu = etu;
        this.classe = classe;
        this.createAt=LocalDate.now();
    }
    
    

    public Inscription(LocalDate createAt, String annee) {
        this.createAt = createAt;
        this.annee = annee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Etudiant getEtu() {
        return etu;
    }

    public void setEtu(Etudiant etu) {
        this.etu = etu;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
    
    
    
    
}
