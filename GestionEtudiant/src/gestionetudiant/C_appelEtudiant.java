/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Classe;
import models.Etudiant;
import models.Professeur;
import models.User;
import services.GestionEtudiant;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class C_appelEtudiant implements Initializable {
    
    private GestionEtudiant service=new GestionEtudiant();
    private ObservableList<String> obvAnnee;
    private ObservableList<String> obvMat;
    private ObservableList<Classe> obvClasse;
    private ObservableList<Etudiant> obvEtudiant;
    private static User userConnect=C_connexion.getUser();

    @FXML
    private AnchorPane anachronpane;
    @FXML
    private Pane cont;
    @FXML
    private ImageView content1;
    @FXML
    private TableView<Etudiant> tblv_etudiant;
    @FXML
    private TableColumn<Etudiant, String> tblc_matricule;
    @FXML
    private TableColumn<Etudiant, String> tblc_nom;
    @FXML
    private TableColumn<Etudiant, Boolean> tblc_absence= new TableColumn<Etudiant, Boolean>("absence");;
    
              
    @FXML
    private ComboBox<String> cbo_annee;
    @FXML
    private TableView<Classe> tblv_classe;
    @FXML
    private TableColumn<Classe, String> tblc_id;
    @FXML
    private TableColumn<Classe, String> tblc_libelle;
    @FXML
    private ComboBox<String> cbo_module;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<String> lAnneeScolaire=service.getAnneeScolaire();
        //Convertir ArrayList en Observable
        obvAnnee=FXCollections.observableArrayList(lAnneeScolaire);
        obvClasse=FXCollections.observableArrayList(service.listerClasseUnEnseignant((Professeur) userConnect));
        //Construction des cellules de la table
        tblc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblc_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        //Souscription
        cbo_annee.setItems(obvAnnee);
        tblv_classe.setItems(obvClasse);
        //Selectionner le premier element
        cbo_annee.getSelectionModel().selectFirst();
        
    }    

    @FXML
    private void handleAjoutAbsence(ActionEvent event) {
        
           System.out.println(obvEtudiant);
         
         
    }

    @FXML
    private void handleListeEtudiantParClasse(MouseEvent event) {
        String annee=cbo_annee.getSelectionModel().getSelectedItem();
        Classe cl=tblv_classe.getSelectionModel().getSelectedItem() ;
        List<Etudiant> lEtudiant=service.listerEtudiantParClasse(cl, annee);
        List<String> lModules=service.listerModuleParClasseUnProf((Professeur) userConnect, cl);
        obvEtudiant=FXCollections.observableArrayList(lEtudiant);
        obvMat=FXCollections.observableArrayList(lModules);
        tblc_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tblc_nom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblc_absence.setCellValueFactory(new PropertyValueFactory<>("absence"));
        //tblc_absence.setCellFactory(CheckBoxTableCell.forTableColumn(tblc_absence));
        
        cbo_module.setItems(obvMat);
        tblv_etudiant.setItems(obvEtudiant);
    }


    @FXML
    private void handleVerification(MouseEvent event) {
        /*
         Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
      alert.setContentText("C:/MyFile.txt");
 
      // option != null.
      Optional<ButtonType> option = alert.showAndWait();
 
      if (option.get() == null) {
         System.out.println("No selection");
      } else if (option.get() == ButtonType.OK) {
          //tblc_absence.set
         System.out.println("Ok");
      } else if (option.get() == ButtonType.CANCEL) {
         System.out.println("Cancel");
      } else {
         System.out.println("-");
      }
        */

    }

        
    
}
