/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.User;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class C_dashboard implements Initializable {

    private static User userConnect;
            
    @FXML
    private Button btn_deconnexion;
    @FXML
    private AnchorPane anchor_container;
    @FXML
    private Text txt_nom;
    @FXML
    private Text txt_profil;
    @FXML
    private TitledPane menu_classe;
    @FXML
    private TitledPane menu_inscription;
    @FXML
    private TitledPane menu_professeur;
    @FXML
    private Button btn_listeClasse;
    @FXML
    private Button btn_noteAbs;
    @FXML
    private Button btn_modifClasse;
    @FXML
    private Button btn_insEtu;
    @FXML
    private Button btn_ajoutProf;
    @FXML
    private Button btn_affectProf;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userConnect=C_connexion.getUser();
        txt_nom.setText(userConnect.getNomComplet());
        //System.out.println(userConnect.getType());
        txt_profil.setText(userConnect.getType());
        btn_noteAbs.setDisable(true);
        
        //menu_professeur.setCollapsible(true);
        if(userConnect.getType().compareTo("AC")==0){
            menu_inscription.setDisable(true);
            menu_professeur.setDisable(true);
            
        }else{
            if(userConnect.getType().compareTo("Professeur")==0){
            menu_inscription.setDisable(true);
            menu_professeur.setDisable(true);
            btn_noteAbs.setDisable(false);
             btn_listeClasse.setDisable(true);
              btn_modifClasse.setDisable(true);
            
        }
        }
    }    

    @FXML
    private void handleDeconnexion(ActionEvent event) {
        btn_deconnexion.getScene().getWindow().hide();
                Stage stage=new Stage();
                Parent layout;
                try {
                    layout = FXMLLoader.load(getClass().getResource("V_connexion.fxml"));
                    Scene scene = new Scene(layout);
                     stage.setScene(scene);
                     stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(C_connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void handleLoadViewClasse(ActionEvent event) {
                Parent layout;
                try {
                    layout = FXMLLoader.load(getClass().getResource("v_classe.fxml"));
                    //On vide le anchor pour ne pas avoir de superposition de page
                    anchor_container.getChildren().clear();
                    anchor_container.getChildren().add(layout);
                    
                } catch (IOException ex) {
                    Logger.getLogger(C_connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void handleLoadViewAppel(ActionEvent event) {
        Parent layout;
                try {
                    layout = FXMLLoader.load(getClass().getResource("V_appelEtudiant.fxml"));
                    //On vide le anchor pour ne pas avoir de superposition de page
                    anchor_container.getChildren().clear();
                    anchor_container.getChildren().add(layout);
                    
                } catch (IOException ex) {
                    Logger.getLogger(C_connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void handleLoadViewInscription(ActionEvent event) {
        Parent layout;
                try {
                    layout = FXMLLoader.load(getClass().getResource("V_inscription.fxml"));
                    //On vide le anchor pour ne pas avoir de superposition de page
                    anchor_container.getChildren().clear();
                    anchor_container.getChildren().add(layout);
                    
                } catch (IOException ex) {
                    Logger.getLogger(C_connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void handleLoadViewAjoutProf(ActionEvent event) {
        Parent layout;
                try {
                    layout = FXMLLoader.load(getClass().getResource("AjoutProfesseur.fxml"));
                    //On vide le anchor pour ne pas avoir de superposition de page
                    anchor_container.getChildren().clear();
                    anchor_container.getChildren().add(layout);
                    
                } catch (IOException ex) {
                    Logger.getLogger(C_connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void handleLoadViewAffectProf(ActionEvent event) {
        Parent layout;
                try {
                    layout = FXMLLoader.load(getClass().getResource("Affectation.fxml"));
                    //On vide le anchor pour ne pas avoir de superposition de page
                    anchor_container.getChildren().clear();
                    anchor_container.getChildren().add(layout);
                    
                } catch (IOException ex) {
                    Logger.getLogger(C_connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
