package entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by user on 31/05/16.
 */
@XmlRootElement
public class Article {

    private String nom;
    private String description;
    private String codeBarre;
    private double prix;
    private double coordX;
    private double coordY;
    private int quantite;
    private Categorie categorie;

    public Article() {}

    public Article(double coordX,double coordY,String nom, String description, String codeBarre, double prix, int quantite, Categorie categorie ){
        this.nom=nom;
        this.description=description;
        this.codeBarre=codeBarre;
        this.prix=prix;
        this.quantite=quantite;
        this.categorie=categorie;
        this.coordX=coordX;
        this.coordY=coordY;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Article{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", codeBarre='" + codeBarre + '\'' +
                ", prix=" + prix +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
                ", quantite=" + quantite +
                ", categorie=" + categorie +
                '}';
    }
}