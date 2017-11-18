package fr.polytechnice.caddyhop.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shafiq on 01/06/16.
 */
public class User implements Serializable {
    private String email;
    private String login;
    private String nom;
    private String prenom;
    private String mdp;

    private List<ItemList> itemListList;
    private ArrayList<Fidelite> listCarteFidel;

    transient private ItemList activeItemList;

    private long id;

    public User() {}

    public User(String login, String mdp, String email, String nom, String prenom) {
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ItemList> getItemListList() {
        return itemListList;
    }

    public void setItemListList(List<ItemList> itemListList) {
        this.itemListList = itemListList;
    }

    @Override
    public String toString() {
        String result = "";

        result += "User { "
                + "id: " + id
                + ", email: " + email
                + ", login: " + login
                + ", nom: " + nom
                + ", prenom: " + prenom
                + ", itemListList: " + itemListList
                + ", listCarteFidel: " + listCarteFidel
                + ", mdp: " + mdp
                + "}";
        /*
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(this);*/
        return result;
    }

    public ItemList getActiveItemList() {
        return activeItemList;
    }

    public void setActiveItemList(ItemList activeItemList) {
        this.activeItemList = activeItemList;
    }

    public ArrayList<Fidelite> getListCarteFidel() {
        return listCarteFidel;
    }

    public void setListCarteFidel(ArrayList<Fidelite> listCarteFidel) {
        this.listCarteFidel = listCarteFidel;
    }
}
