package fr.polytechnice.caddyhop.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shafiq on 02/06/16.
 */
public class ItemList implements Serializable{
    private long id;
    private List<Article> articleList;
    private String nom;

    public ItemList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "ItemList {" + " id: " + id
                + " nom: " + nom
                + " articleList: " + articleList
                + "}";
    }

    @Override
    public boolean equals(Object o) {

        if ( this == o ) return true;

        if ( !(o instanceof ItemList) ) return false;

        ItemList that = (ItemList) o;

        //now a proper field-by-field evaluation can be made
        return this.id == that.id;
    }
}
