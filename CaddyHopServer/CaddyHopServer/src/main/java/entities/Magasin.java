package entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by user on 06/06/16.
 */

@XmlRootElement
public class Magasin {

    private static long idGene = 0;
    private String nom;
    private long ide;
    private ArrayList<Article> listArticle;
    private double latitude;
    private double longitude;

    public Magasin(){
        ide = idGene++;
    }

    public Magasin(String nom, double latitude, double longitude){
        ide = idGene++;
        this.nom = nom;
        this.latitude=latitude;
        this.longitude=longitude;
        listArticle = new ArrayList<Article>();
    }

    public String getNom() {
        return nom;
    }

    public long getIde() {
        return ide;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<Article> getListArticle() {
        return listArticle;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIde(long ide) {
        this.ide = ide;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setListArticle(ArrayList<Article> listArticle) {
        this.listArticle = listArticle;
    }

    public void addArticle(Article article){
        listArticle.add(article);
    }

    public void modifiedArticle(Article article){
        for(Article art: listArticle){
            if(art.getCodeBarre().equals(article.getCodeBarre())){
                listArticle.remove(art);
                listArticle.add(article);
            }
        }
    }

    @Override
    public String toString() {
        return "Magasin{" +
                "nom='" + nom + '\'' +
                ", ide=" + ide +
                ", listArticle=" + listArticle +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}