package database;

import entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 31/05/16.
 */

public class MockDatabase {
    public static MockDatabase data = new MockDatabase();

    private ArrayList<Utilisateur> usersList;
    private ArrayList<Magasin> magasinList;
    private ArrayList<Article> tousLesArticlesListe;

    public MockDatabase(){
        usersList = new ArrayList<Utilisateur>();
        magasinList = new ArrayList<Magasin>();
        tousLesArticlesListe = new ArrayList<Article>();
    }

    public void addUser(Utilisateur user) {
        user.generateID();
        usersList.add(user);
    }

    public void addArticleInMag(Article article, long id) {
        boolean b = false;
        for (Magasin mag: magasinList){
            if(mag.getIde()==id){
                mag.addArticle(article);
            }
        }
        for(Article art: tousLesArticlesListe){
            if(art.getCodeBarre().equals(article.getCodeBarre())){
                b= true;
            }
        }
        if(b==false){
            tousLesArticlesListe.add(article);
        }
    }

    public void addMagasin(Magasin magasin) {
        magasinList.add(magasin);
    }


    public void modifiedArticleInMag(Article article, long id){
        for (Magasin mag: magasinList){
            if(mag.getIde()==id){
                for(Article art: mag.getListArticle()){
                    if(art.getCodeBarre().equals(article.getCodeBarre())){
                        mag.getListArticle().remove(art);
                        mag.getListArticle().add(article);
                    }
                }
            }
        }
    }

    public void modifiedUser(Utilisateur utilisateur,long id){
        for(Utilisateur uti: usersList){
            if(uti.getId()==id){
                usersList.remove(uti);
                usersList.add(utilisateur);
                utilisateur.setId(id);
            }
        }
    }

    public void deleteArticleInMag(Article article, long id){
        boolean b = false;
        for (Magasin mag: magasinList){
            if(mag.getIde()==id){
                for(Article art: mag.getListArticle()){
                    if(art.getCodeBarre().equals(article.getCodeBarre())){
                        mag.getListArticle().remove(art);
                    }
                }
            }
        }
        for (Magasin mag: magasinList){
            for(Article art: mag.getListArticle()){
                if(art.getCodeBarre().equals(article.getCodeBarre())){
                    b = true;
                }
            }
        }
        if(b==true){
            tousLesArticlesListe.remove(article);
        }
    }

    public void deleteMagasin(Magasin magasin){
        for(Magasin mag: magasinList){
            if(mag.getIde() == magasin.getIde()){
                magasinList.remove(mag);
            }
        }
    }

    public void deleteUser(Utilisateur user){
        for(Utilisateur uti: usersList){
            if(uti.getId()==user.getId()){
                usersList.remove(uti);
            }
        }
    }

    public ArrayList<Utilisateur> getUsersList() {
        return usersList;
    }

    public Utilisateur getUser(long id) {
        for(Utilisateur u:usersList) {
            if(u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Magasin> getMagasinList() {
        return magasinList;
    }

    public ArrayList<Article> getArticlesListInMag(long id){
        for (Magasin mag : magasinList){
            if(mag.getIde() == id){
                return mag.getListArticle();
            }
        }
        return null;
    }

    public ArrayList<ItemList> getItemListList(Utilisateur user) {
        if(user != null) {
            return user.getItemListList();
        }
        return null;
    }

    public ArrayList<Article> selectArticleByCategorie(Categorie cat){
        ArrayList<Article> listByCat = new ArrayList<Article>();
        for(Article art: tousLesArticlesListe){
            if(art.getCategorie()==cat){
                listByCat.add(art);
            }
        }
        return listByCat;
    }

    public ArrayList<Resultat> resultat(long id, long listId, double latitude, double longitude, int distMax){
        ArrayList<Resultat> listResultat = new ArrayList<Resultat>();
        int nbr = 0;
        for(int i = 0; i <getMagasinList().size(); i++){
            if(distMax>=distance(getMagasinList().get(i).getLatitude(),latitude,getMagasinList().get(i).getLongitude(),longitude)){
                listResultat.add(new Resultat());
                listResultat.get(nbr).setNomMag(getMagasinList().get(i).getNom());
                ArrayList<Article> articleNonDispo = new ArrayList<Article>();
                double prix = 0.0;
                int k=0;
                for( Article art: getUser(id).getList(listId).getArticleList()) {
                    boolean nonTrouve = false;
                    for (Article article : getArticlesListInMag(i)) {
                        if (art.getCodeBarre().equals(article.getCodeBarre())) {
                            k++;
                            prix = prix + article.getPrix();
                            nonTrouve = true;
                        }
                    }
                    if (nonTrouve == false) {
                        articleNonDispo.add(art);
                    }
                }
                listResultat.get(nbr).setPrixTotal(prix);
                listResultat.get(nbr).setNbrArtDispo(k);
                int nbrArtTotal = getUser(id).getList(listId).getArticleList().size();
                listResultat.get(nbr).setNbrArtTotal(nbrArtTotal);
                listResultat.get(nbr).setArticleNonDispo(articleNonDispo);
                nbr++;
            }
        }
        return listResultat;
    }

    public double distance(double lat1, double lat2, double lon1, double lon2) {

        int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance)/1000;
    }

    public ArrayList<Article> getTousLesArticlesListe() {
        return tousLesArticlesListe;
    }
}