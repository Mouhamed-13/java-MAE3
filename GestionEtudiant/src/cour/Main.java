/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cour;

import dao.MySQL;
import java.util.ArrayList;
import java.util.List;
import models.Etudiant;
import models.Professeur;
import models.User;

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        // User u=new Etudiant(); Erreur car User est abstract
        
        //Polymorphisme d'objet
        /*User u=new Etudiant();
        
        // System.out.print(u.getClass());
        // ((Etudiant)u).getTuteur();
        // u.toString();
        
        List<String> ls=new ArrayList();
        
        ls.add("Bonjour");
        ls.add("A ");
        ls.add("Tous");
        
        for(String elt:ls){
            System.out.println(elt);
        }
        
        List<User> lu=new ArrayList();
        lu.add(u);
        lu.add(new Professeur());
        
        
        for(User eltU:lu){
            System.out.println(eltU);
        } */
        
        MySQL mysql=new MySQL();
        mysql.connexionBD();
        
        
    }
    
}
