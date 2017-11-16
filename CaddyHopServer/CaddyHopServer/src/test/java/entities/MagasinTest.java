package entities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class MagasinTest {

    Magasin m1 = new Magasin("Casino",43.617721,7.075659);
    Article a1 = new Article(0.0,0.0,"coca cola 2L","bouteille de coca 2L","5000112616316",1.80,1, Categorie.SODA_B);

    @Test
    public void getNom() throws Exception {
        assertEquals("Casino",m1.getNom());
    }

    @Test
    public void getIde() throws Exception {
        assertTrue(m1.getIde()==m1.getIde());
    }

    @Test
    public void getLatitude() throws Exception {
        assertTrue(m1.getLatitude()==43.617721);
    }

    @Test
    public void getLongitude() throws Exception {
        assertTrue(m1.getLongitude()==7.075659);
    }

    @Test
    public void getListArticle() throws Exception {
        assertTrue(m1.getListArticle().size()==0);
    }

    @Test
    public void setNom() throws Exception {
        m1.setNom("Carrefour");
        assertEquals("Carrefour",m1.getNom());
    }

    @Test
    public void setIde() throws Exception {
        m1.setIde(5);
        assertTrue(m1.getIde()==5);
    }

    @Test
    public void setLatitude() throws Exception {
        m1.setLatitude(2.2);
        assertTrue(m1.getLatitude()==2.2);
    }

    @Test
    public void setLongitude() throws Exception {
        m1.setLongitude(1.1);
        assertTrue(m1.getLongitude()==1.1);
    }

    @Test
    public void addArticle() throws Exception {
        m1.addArticle(a1);
        assertTrue(m1.getListArticle().size()==1);
    }

    @Test
    public void setListArticle() throws Exception {
        ArrayList<Article> list = new ArrayList<Article>();
        list.add(a1);
        m1.setListArticle(list);
        assertEquals(list,m1.getListArticle());
    }

    @Test
    public void modifiedArticle() throws Exception {
        m1.modifiedArticle(new Article(0.0,0.0,"coca","bouteille de coca 2L","5000112616316",1.80,1, Categorie.SODA_B));
        assertTrue(m1.getListArticle().size()==0);
    }
}