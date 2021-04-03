/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.net.URL;
import java.util.ArrayList;
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
public class AffectationController implements Initializable {
    
    private GestionEtudiant service=new GestionEtudiant();
    private ObservableList<String> obvMat;
    private ObservableList<String> obvAnneeScolaire;
    private ObservableList<Classe> obvClasse;
    private ObservableList<String> obvMatEns;
    private static List<String> lMatEns=new ArrayList<>();
    private static Professeur prof=new Professeur();

    @FXML
    private ComboBox<String> cbo_annee;
    @FXML
    private TextField txt_nci;
    @FXML
    private TextField txt_nom;
    @FXML
    private ComboBox<Classe> cbo_classe;
    @FXML
    private ComboBox<String> cbo_matEns;
    @FXML
    private TableView<String> tblv_mat;
    @FXML
    private TableColumn<String, String> tblc_mat;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_affect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> lAnneeScolaire=service.getAnneeScolaire();
        //Convertir ArrayList en Observable
        obvAnneeScolaire=FXCollections.observableArrayList(lAnneeScolaire);
        obvClasse=FXCollections.observableArrayList(service.listerClasse());
        obvMat=FXCollections.observableArrayList(this.lMatEns);
        //Souscription
        cbo_annee.setItems(obvAnneeScolaire);
        cbo_classe.setItems(obvClasse);
        tblv_mat.setItems(obvMat);
        
        btn_add.setDisable(true);
        btn_affect.setDisable(true);
        
    }    

    @FXML
    private void handleSearchProf(ActionEvent event) {
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
                obvMatEns=FXCollections.observableArrayList(prof.getMatieres());
                cbo_matEns.setItems(obvMatEns);
                Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Ce professeur existe déjà");
            alert.showAndWait();
            String[] nomComplet;
            nomComplet=prof.getNomComplet().split(" ");
            txt_nom.setText(nomComplet[1]);
            //System.out.println(prof.getMatieres());
            //this.lMatEns=prof.getMatieres();
            //obvMatEns.addAll(lMatEns);
            //cbo_matEns.getItems().setAll(this.lMatEns);
            txt_nom.setDisable(true);
                
            }else{
                Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Ce professeur n'existe pas !");
            alert.showAndWait();
            txt_nom.setText("");
            txt_nom.setDisable(false);
            this.lMatEns.clear();
            obvMatEns.addAll(lMatEns);
            
            cbo_matEns.getItems().setAll(this.lMatEns);
            }
            
        }
        btn_add.setDisable(false);
    }

    @FXML
    private void handleAddMat(ActionEvent event) {
        String matiere=cbo_matEns.getSelectionModel().getSelectedItem();
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
                    //System.out.println(lMatEns);
                    obvMat.addAll(lMatEns);
                    tblv_mat.getItems().setAll(this.lMatEns);
                    tblc_mat.setCellValueFactory(new PropertyValueFactory<>("MATIERES"));
                    tblc_mat.setCellValueFactory(param -> { 
                     final String car = param.getValue(); 
                     return new SimpleStringProperty(car); 
                    });
                    tblv_mat.getColumns().setAll(tblc_mat);           
                    //System.out.println(this.lMatEns);
                    //tblv_matiere.setItems(obvMatEns);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setContentText("Matiere ajouté avec succés");
                    alert.showAndWait();
               
            }     
            btn_affect.setDisable(false);
        }
    }

    @FXML
    private void handleAffectProf(ActionEvent event) {
        String nci=txt_nci.getText().trim();
        String nom=txt_nom.getText().trim();
        String annee=cbo_annee.getSelectionModel().getSelectedItem();
        Classe classe=cbo_classe.getSelectionModel().getSelectedItem();
        String matiere=Lib.listToString(this.lMatEns);
        if(prof!=null){
            //prof=new Professeur();
            if(nci.compareTo("")==0 || nom.compareTo("")==0 || annee==null || classe==null || this.lMatEns.isEmpty() ){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez compléter tous les champs !");
            alert.showAndWait();
            
            }else{
               
                if(service.affecterProfesseur(prof, classe, annee, lMatEns)){
                      Alert alert=new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Information");
                      alert.setContentText("Affectation effectuée avec succés");
                      alert.showAndWait();
               }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Erreur");
                      alert.setContentText("Erreur dans l'affectation");
                      alert.showAndWait();

                }
                
            }
        }
    }
    
}
