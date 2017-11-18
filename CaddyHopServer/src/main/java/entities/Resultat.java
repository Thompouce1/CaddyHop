package entities;

import java.util.ArrayList;

/**
 * Created by user on 08/06/16.
 */
public class Resultat {

    private String nomMag;
    private double prixTotal;
    private int nbrArtTotal;
    private int nbrArtDispo;
    private ArrayList<Article> articleNonDispo;

    public Resultat(){}

    public Resultat(String nomMag, double prixTotal, int nbrArtDispo, int nbrArtTotal, ArrayList<Article> articleNonDispo){
        this.nomMag=nomMag;
        this.prixTotal=prixTotal;
        this.nbrArtDispo=nbrArtDispo;
        this.nbrArtTotal=nbrArtTotal;
        this.articleNonDispo=articleNonDispo;
    }

    public String getNomMag() {
        return nomMag;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public int getNbrArtTotal() {
        return nbrArtTotal;
    }

    public int getNbrArtDispo() {
        return nbrArtDispo;
    }

    public ArrayList<Article> getArticleNonDispo() {
        return articleNonDispo;
    }

    public void setNomMag(String nomMag) {
        this.nomMag = nomMag;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setNbrArtTotal(int nbrArtTotal) {
        this.nbrArtTotal = nbrArtTotal;
    }

    public void setNbrArtDispo(int nbrArtDispo) {
        this.nbrArtDispo = nbrArtDispo;
    }

    public void setArticleNonDispo(ArrayList<Article> articleNonDispo) {
        this.articleNonDispo = articleNonDispo;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "nomMag='" + nomMag + '\'' +
                ", prixTotal=" + prixTotal +
                ", nbrArtTotal=" + nbrArtTotal +
                ", nbrArtDispo=" + nbrArtDispo +
                ", articleNonDispo=" + articleNonDispo +
                '}';
    }
}
