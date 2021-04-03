/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author ASUS
 */
public class Lib {
     
    public static  String listToString(List<String> matieres){
        String stringModules="";
        for(String elt:matieres){
            stringModules+=elt+"-";
        }
        return stringModules;
    }
    public static  List<String> stringToList(String modules){
        List<String> lModules=new ArrayList<>();
        //chaine en tableau
        String tab[]=modules.split("-");
        for(String elt :tab){
            lModules.add(elt);
        }
        return lModules;
    }
    
}
