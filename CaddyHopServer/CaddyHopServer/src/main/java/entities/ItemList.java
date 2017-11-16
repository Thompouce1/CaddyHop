package entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


/**
 * Created by user on 31/05/16.
 */
@XmlRootElement
public class ItemList {

    private static long idGen = 0;

    private long id;
    private ArrayList<Article> articleList = new ArrayList<Article>();
    private String nom;

    public ItemList(){
        id = idGen++;
    }
    public ItemList(String nom) {
        id = idGen++;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<Article> articleList) {
        this.articleList = articleList;
    }

    public void addArticle(Article article) { articleList.add(article);}

    public void delArticle(Article article){
        for(Article art: articleList){
            if(art.getCodeBarre().equals(article.getCodeBarre())){
                articleList.remove(art);
            }
        }
    }

    public void modifiedArticle(Article article){
        for(Article art: articleList){
            if(art.getCodeBarre().equals(article.getCodeBarre())){
                articleList.remove(art);
                articleList.add(article);
            }
        }
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "id=" + id +
                ", articleList=" + articleList +
                ", nom='" + nom + '\'' +
                '}';
    }
}
