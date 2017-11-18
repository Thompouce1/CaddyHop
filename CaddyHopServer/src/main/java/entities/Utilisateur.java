package entities;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by user on 31/05/16.
 */

@XmlRootElement
@Entity
public class Utilisateur {

    private static long idGene = 0;

    private String login;
    private String mdp;
    private String email;
    private String nom;
    private String prenom;

    private long ide;
    private ArrayList<ItemList> itemListList = new ArrayList<ItemList>();
    private ArrayList<Fidelite> listCarteFidel = new ArrayList<Fidelite>();

    public Utilisateur() { ide = idGene++; }

    public Utilisateur(String login, String mdp, String email, String nom, String prenom){
        this.login=login;
        this.mdp=mdp;
        this.email=email;
        this.nom=nom;
        this.prenom=prenom;
        itemListList = new ArrayList<ItemList>();
        listCarteFidel = new ArrayList<Fidelite>();
        ide = idGene++;
    }

    public long creerListe(String nom){
        ItemList liste = new ItemList(nom);
        itemListList.add(liste);
        return liste.getId();
    }

    public void generateID(){
        this.setId(idGene-1);
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public long getId() {
        return ide;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(long ide) {
        this.ide = ide;
    }

    public ArrayList<ItemList> getItemListList() {
        return itemListList;
    }

    public void setItemListList(ArrayList<ItemList> itemListList) {
        this.itemListList = itemListList;
    }

    public ItemList getList(long idList) {
        for(ItemList il:itemListList) {
            if(il.getId() == idList) {
                return il;
            }
        }
        return null;
    }

    public ItemList setList(long idList, ItemList item){
        for(ItemList il:itemListList) {
            if(il.getId() == idList) {
                String no = il.getNom();
                itemListList.remove(il);
                itemListList.add(item);
                item.setNom(no);
            }
        }
        return null;
    }

    public void delList(long idList){
        for(ItemList il:itemListList) {
            if(il.getId() == idList) {
                itemListList.remove(il);
            }
        }
    }

    public ArrayList<Fidelite> getListCarteFidel() {
        return listCarteFidel;
    }

    public void setListCarteFidel(ArrayList<Fidelite> listCarteFidel) {
        this.listCarteFidel = listCarteFidel;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ide=" + ide +
                ", itemListList=" + itemListList +
                ", listCarteFidel=" + listCarteFidel +
                '}';
    }
}
