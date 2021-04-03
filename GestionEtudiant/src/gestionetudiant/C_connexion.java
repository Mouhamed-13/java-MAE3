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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.GestionEtudiant;
import models.User;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class C_connexion implements Initializable {
    
    private GestionEtudiant service=new GestionEtudiant();
    private static User user;

    @FXML
    private TextField txt_login;
    @FXML
    private PasswordField txt_pwd;
    @FXML
    private Text txt_error;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        C_connexion.user = user;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //cacher le message d'erreur
        txt_error.setVisible(false);
    }    

    @FXML
    private void handleConnexion(ActionEvent event) {
        String login=txt_login.getText();
        String pwd=txt_pwd.getText();
        if(login.compareTo("")!=0 && pwd.compareTo("")!=0 ){
            user=service.seConnecter(login, pwd);
            if(user==null){
                txt_error.setText("Login ou mot de passe incorrects");
                txt_error.setVisible(true);
            }else{
                txt_error.getScene().getWindow().hide();
                Stage stage=new Stage();
                Parent layout;
                try {
                    layout = FXMLLoader.load(getClass().getResource("V_dashboard.fxml"));
                    Scene scene = new Scene(layout);
                     stage.setScene(scene);
                     stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(C_connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                 
            }
        }else{
            txt_error.setText("Login ou mot de passe obligatoire");
            txt_error.setVisible(true);
        }
        
    }
    
}
