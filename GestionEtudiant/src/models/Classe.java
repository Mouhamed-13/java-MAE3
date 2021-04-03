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
public class Classe {
    
    private int id;
    private String libelle;
    private static int nbreEtu;

    public Classe() {
    }

    public Classe(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public static int getNbreEtu() {
        return nbreEtu;
    }

    public static void setNbreEtu(int nbreEtu) {
        Classe.nbreEtu = nbreEtu;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
    
    
    
}
