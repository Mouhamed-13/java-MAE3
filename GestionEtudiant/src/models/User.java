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
public abstract class User {
    
    //User herite de Object
    // methode equals() compare 2 objets
    // methode toString() afiche les attributs
    protected int id;
    protected String nomComplet;
    protected String login;
    protected String pwd;
    protected String photo;
    protected String type;

    public User() {
    }

    public User(String nomComplet, String login, String pwd, String photo) {
        this.nomComplet = nomComplet;
        this.login = login;
        this.pwd = pwd;
        this.photo = photo;
    }

    public User(String nomComplet, String photo) {
        this.nomComplet = nomComplet;
        this.photo = photo;
    }

    
    
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return nomComplet;
    }
    
    
}
