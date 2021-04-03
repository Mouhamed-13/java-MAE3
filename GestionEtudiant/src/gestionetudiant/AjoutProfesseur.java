/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Classe;
import models.Professeur;
import services.GestionEtudiant;
import services.Lib;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutProfesseur implements Initializable {
    
    private GestionEtudiant service=new GestionEtudiant();
    private ObservableList<String> obvMat;
    private ObservableList<String> obvMatEns;
    private static List<String> lMatEns=new ArrayList<>();
    private static Professeur prof=new Professeur();

    @FXML
    private TextField txt_nci;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_grade;
    @FXML
    private TableView<String> tblv_matiere;
    @FXML
    private TableColumn<String, String> tblc_matEns;
    
    @FXML
    private ComboBox<String> cbo_matiere;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_enregistrer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> lMat=service.getMatieres();
        
        //Convertir ArrayList en Observable
        obvMat=FXCollections.observableArrayList(lMat);
        obvMatEns=FXCollections.observableArrayList(this.lMatEns); 
        
        cbo_matiere.setItems(obvMat);
        tblv_matiere.setItems(obvMatEns);
        
        btn_add.setDisable(true);
        btn_enregistrer.setDisable(true);
        
    }    
    
   

    @FXML
    private void handleSearchNci(ActionEvent event) {
            String nci=txt_nci.getText().trim(); //trim() pour enlever les espaces
        if(nci.compareTo("")==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("NCI obligatoire");
            alert.showAndWait();
            
        }else{
            prof=service.getProfesseurByNci(Integer.parseInt(nci));
            //System.out.println(prof);
            if(prof != null){
                
                Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Ce professeur existe déjà");
            alert.showAndWait();
            String[] nomComplet;
            nomComplet=prof.getNomComplet().split(" ");
            txt_nom.setText(nomComplet[1]);
            txt_prenom.setText(nomComplet[0]);
            txt_grade.setText(prof.getGrade());
            //System.out.println(prof.getMatieres());
            this.lMatEns=prof.getMatieres();
            obvMatEns.addAll(lMatEns);
            tblv_matiere.getItems().setAll(this.lMatEns);
            tblc_matEns.setCellValueFactory(new PropertyValueFactory<>("Matieres Enseignes"));
            tblc_matEns.setCellValueFactory(param -> { 
             final String car = param.getValue(); 
             return new SimpleStringProperty(car); 
            });
            tblv_matiere.getColumns().setAll(tblc_matEns);
            txt_nom.setDisable(true);
            txt_prenom.setDisable(true);
            txt_grade.setDisable(true);
                
            }else{
                Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir les autres informations du professeur");
            alert.showAndWait();
            txt_nom.setText("");
            txt_prenom.setText("");
            txt_grade.setText("");
            txt_nom.setDisable(false);
            txt_prenom.setDisable(false);
            txt_grade.setDisable(false);
            this.lMatEns.clear();
            obvMatEns.addAll(lMatEns);
            
            tblv_matiere.getItems().setAll(this.lMatEns);
            }
            
        }
        btn_add.setDisable(false);
    }

    @FXML
    private void handleInsertProf(ActionEvent event) {
        String nci=txt_nci.getText().trim();
        String nom=txt_nom.getText().trim();
        String prenom=txt_prenom.getText().trim();
        String grade=txt_grade.getText().trim();
        String matiere=Lib.listToString(this.lMatEns);
        if(prof==null){
            //prof=new Professeur();
            if(nci.compareTo("")==0 || nom.compareTo("")==0 || prenom.compareTo("")==0 || grade.compareTo("")==0 || this.lMatEns.isEmpty() ){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez compléter tous les champs !");
            alert.showAndWait();
            
            }else{
                
                String nomC=prenom + " " +nom;
                prof=new Professeur(nomC,Integer.parseInt(nci),grade,"vide",this.lMatEns);
            
                if(service.addUser(prof)!=0){
                      Alert alert=new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Information");
                      alert.setContentText("Ajout effectuée avec succés");
                      alert.showAndWait();
               }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Erreur");
                      alert.setContentText("Erreur dans l'ajout");
                      alert.showAndWait();

                }
                
            }
        }else{
            prof.setMatieres(lMatEns);
            if(service.updateMatiere(prof)){
                      Alert alert=new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Information");
                      alert.setContentText("Matiere mise à jour avec succés");
                      alert.showAndWait();
               }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Erreur");
                      alert.setContentText("Erreur dans la mise à jour");
                      alert.showAndWait();

                }
            
        }
        
        
            
        
    }

    @FXML
    private void handleAddMatiere(ActionEvent event) {
        String matiere=cbo_matiere.getSelectionModel().getSelectedItem();
        //System.out.println(matiere);
        if(matiere==null){
             Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez choisir une matiére");
            alert.showAndWait();
            
        }else{
            if(this.lMatEns.contains(matiere)){    
                Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Matiére déja ajouté !!!");
            alert.showAndWait();              
                }else{
                    this.lMatEns.add(matiere);
                    obvMatEns.addAll(lMatEns);
            
            tblv_matiere.getItems().setAll(this.lMatEns);
            tblc_matEns.setCellValueFactory(new PropertyValueFactory<>("Matieres Enseignes"));
            tblc_matEns.setCellValueFactory(param -> { 
             final String car = param.getValue(); 
             return new SimpleStringProperty(car); 
            });
            tblv_matiere.getColumns().setAll(tblc_matEns);           
            //System.out.println(this.lMatEns);
            //tblv_matiere.setItems(obvMatEns);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Matiere ajouté avec succés");
            alert.showAndWait();
               
            }     
            btn_enregistrer.setDisable(false);
        }
        
    }
    
}
