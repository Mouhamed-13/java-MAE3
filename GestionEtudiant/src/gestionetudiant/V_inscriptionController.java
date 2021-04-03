/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Classe;
import models.Etudiant;
import models.Inscription;
import services.GestionEtudiant;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class V_inscriptionController implements Initializable {
    
    private GestionEtudiant service=new GestionEtudiant();
    private ObservableList<String> obvAnneeScolaire;
    private ObservableList<Classe> obvClasse;

    @FXML
    private ComboBox<String> cbo_AnneeScolaire;
    @FXML
    private TextField txt_matricule;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private ComboBox<Classe> cbo_Classe;
    @FXML
    private TextField txt_tuteur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> lAnneeScolaire=service.getAnneeScolaire();
        List<Classe> lClasse=service.listerClasse();
        obvAnneeScolaire=FXCollections.observableArrayList(lAnneeScolaire);
        obvClasse=FXCollections.observableArrayList(lClasse);
        cbo_AnneeScolaire.setItems(obvAnneeScolaire);
        cbo_Classe.setItems(obvClasse);
        
    }    

    @FXML
    private void handleInsertEtudiant(ActionEvent event) {
        String matricule=txt_matricule.getText().trim();
        String nom=txt_nom.getText().trim();
        String prenom=txt_prenom.getText().trim();
        String tuteur=txt_tuteur.getText().trim();
        String annee=cbo_AnneeScolaire.getSelectionModel().getSelectedItem();
        Classe cl=new Classe();
        cl=cbo_Classe.getSelectionModel().getSelectedItem();
        
        if(matricule.compareTo("")==0 || nom.compareTo("")==0 || prenom.compareTo("")==0 || tuteur.compareTo("")==0 || annee==null || cl==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            
        }else{
            String nomC=prenom + " " +nom;
            Etudiant etu=new Etudiant(tuteur,matricule,nomC,"vide");
            
            if(service.inscrireEtudiant(etu, cl, annee)){
                  Alert alert=new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Information");
                  alert.setContentText("Inscription effectuée avec succés");
                  alert.showAndWait();
           }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Erreur");
                  alert.setContentText("Erreur dans l'inscription");
                  alert.showAndWait();
            
            }
                    
                    
        
                
        }       
        
        
    }

    @FXML
    private void handleSearchEtudiant(ActionEvent event) {
        String matricule=txt_matricule.getText().trim();
        if(matricule.compareTo("")==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Matricule obligatoire");
            alert.showAndWait();
            
        }else{
            Etudiant etu=service.getEtudiantByMatricule(matricule);
            if(etu != null){
                Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Cet étudiant existe déjà");
            alert.showAndWait();
            String[] nomComplet;
            nomComplet=etu.getNomComplet().split(" ");
            //System.out.println(nomComplet[0]);
            txt_nom.setText(nomComplet[1]);
            txt_prenom.setText(nomComplet[0]);
            txt_tuteur.setText(etu.getTuteur());
            txt_nom.setDisable(true);
            txt_prenom.setDisable(true);
            txt_tuteur.setDisable(true);
                
            }else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Info");
            alert.setContentText("Etudiant non inscrit! Veuillez remplir ces informations");
            alert.showAndWait();
            
            }
             
            
        }
        
    }
    
}
