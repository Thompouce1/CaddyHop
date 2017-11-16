package fr.polytechnice.caddyhop.models;

import java.io.Serializable;

/**
 * Created by shafiq on 30/05/16.
 */
public class Article implements Serializable {

    private String nom;
    private float prix;
    private String codeBarre;
    private int quantite;

    public Article() {
    }

    public Article(String nom) {
        this.nom = nom;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    @Override
    public String toString() {
        return "Article { "
                + "nom: " + nom
                + ", prix: " + prix
                + ", quantite: " + quantite
                + ", codeBarre: " + codeBarre
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article))
            return false;
        else {
            Article a = (Article)o;
            return this.codeBarre.equals(a.getCodeBarre());
        }

    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
