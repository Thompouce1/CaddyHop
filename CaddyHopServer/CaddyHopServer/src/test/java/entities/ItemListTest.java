package entities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class ItemListTest {

    ItemList i1 = new ItemList("Lundi");
    Article a1 = new Article(0.0,0.0,"coca cola 2L","bouteille de coca 2L","5000112616316",1.80,1, Categorie.SODA_B);

    @Test
    public void getNom() throws Exception {
        assertEquals("Lundi",i1.getNom());
    }

    @Test
    public void setNom() throws Exception {
        i1.setNom("Mardi");
        assertEquals("Mardi",i1.getNom());
    }

    @Test
    public void getId() throws Exception {
        assertFalse(i1.getId()==5);
    }

    @Test
    public void setId() throws Exception {
        i1.setId(5);
        assertTrue(i1.getId()==5);
    }

    @Test
    public void getArticleList() throws Exception {
        assertTrue(i1.getArticleList().size()==0);
    }

    @Test
    public void setArticleList() throws Exception {
        ArrayList<Article> list = new ArrayList<Article>();
        list.add(a1);
        i1.setArticleList(list);
        assertEquals(list,i1.getArticleList());
    }

    @Test
    public void addArticle() throws Exception {
        i1.addArticle(a1);
        assertTrue(i1.getArticleList().size()==1);
    }

    @Test
    public void delArticle() throws Exception {
        i1.delArticle(a1);
        assertTrue(i1.getArticleList().size()==0);
    }

    @Test
    public void modifiedArticle() throws Exception {
        i1.addArticle(a1);
        i1.modifiedArticle(a1);
        assertTrue(i1.getArticleList().size()==1);
    }
}