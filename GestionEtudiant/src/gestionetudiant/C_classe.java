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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.Classe;
import models.Etudiant;
import models.User;
import services.GestionEtudiant;

/**
 *
 * @author ASUS
 */
public class C_classe implements Initializable {
    
    
    private GestionEtudiant service=new GestionEtudiant();
    private ObservableList<String> obvAnneeScolaire;
    private ObservableList<Classe> obvClasse;
    private ObservableList<Etudiant> obvEtudiant;
    private static User userConnect=C_connexion.getUser();
    
    @FXML
    private ComboBox<String> cbo_AnneeScolaire;
    @FXML
    private TableView<Classe> tblv_Classe;
    @FXML
    private TableColumn<Classe, String> tblc_Id;
    @FXML
    private TableColumn<Classe, String> tblc_Libelle;
    @FXML
    private TextField txt_Libelle;
    @FXML
    private TableView<Etudiant> tblv_etudiant;
    @FXML
    private TableColumn<Etudiant, String> tblc_matricule;
    @FXML
    private TableColumn<Etudiant, String> tblc_nomPrenom;
    @FXML
    private Pane pane_addClasse;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> lAnneeScolaire=service.getAnneeScolaire();
        //Convertir ArrayList en Observable
        obvAnneeScolaire=FXCollections.observableArrayList(lAnneeScolaire);
        obvClasse=FXCollections.observableArrayList(service.listerClasse());
        //Construction des cellules de la table
        tblc_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblc_Libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        //Souscription
        cbo_AnneeScolaire.setItems(obvAnneeScolaire);
        tblv_Classe.setItems(obvClasse);
        //Selectionner le premier element
        cbo_AnneeScolaire.getSelectionModel().selectFirst();
        
    }    

    @FXML
    private void handleInsertClasse(ActionEvent event) {
        
        String libelle=txt_Libelle.getText().trim(); //trim() pour enlever les espaces
        if(libelle.compareTo("")==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Libelle obligatoire");
            alert.showAndWait();
            
        }else{
            if(service.rechercherClasseByLibelle(libelle)){
                Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Cette classe existe déjà");
            alert.showAndWait();
                
            }
            else{
                //Enregistrement de la classe tout est ok
                Classe cl=new Classe();
                cl.setLibelle(libelle);
                int id=service.addClasse(cl);
                if(id!=0){
                    cl.setId(id);
                    obvClasse.add(cl);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Classe enregistrée avec succés");
            alert.showAndWait();
                }
            }
        }
        
        
    }

    @FXML
    private void handleListeEtudiantParClasse(MouseEvent event) {
        String annee=cbo_AnneeScolaire.getSelectionModel().getSelectedItem();
        Classe cl=tblv_Classe.getSelectionModel().getSelectedItem() ;
        List<Etudiant> lEtudiant=service.listerEtudiantParClasse(cl, annee);
        obvEtudiant=FXCollections.observableArrayList(lEtudiant);
        tblc_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tblc_nomPrenom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        
        tblv_etudiant.setItems(obvEtudiant);
    
    }
    
}
