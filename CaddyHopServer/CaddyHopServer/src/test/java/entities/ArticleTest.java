package entities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class ArticleTest {

    Article a1 = new Article(0.0,0.0,"coca cola 2L","bouteille de coca 2L","5000112616316",1.80,1, Categorie.SODA_B);

    @Test
    public void getCategorie() throws Exception {
        assertTrue(a1.getCategorie()==Categorie.SODA_B);
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals(a1.getDescription(),"bouteille de coca 2L");
    }

    @Test
    public void setDescription() throws Exception {
        a1.setDescription("bouteillessss de coca 2L");
        assertEquals(a1.getDescription(),"bouteillessss de coca 2L");
    }

    @Test
    public void setCategorie() throws Exception {
        a1.setCategorie(Categorie.ACCESSOIRE_T_C);
        assertTrue(a1.getCategorie()==Categorie.ACCESSOIRE_T_C);
    }

    @Test
    public void getNom() throws Exception {
        assertEquals(a1.getNom(),"coca cola 2L");
    }

    @Test
    public void setNom() throws Exception {
        a1.setNom("coca cola 2L ou 3L");
        assertEquals(a1.getNom(),"coca cola 2L ou 3L");
    }

    @Test
    public void getCodeBarre() throws Exception {
        assertEquals(a1.getCodeBarre(),"5000112616316");
    }

    @Test
    public void setCodeBarre() throws Exception {
        a1.setCodeBarre("1111");
        assertEquals(a1.getCodeBarre(),"1111");
    }

    @Test
    public void getPrix() throws Exception {
        assertTrue(a1.getPrix()==1.80);
    }

    @Test
    public void setPrix() throws Exception {
        a1.setPrix(1.82);
        assertTrue(a1.getPrix()==1.82);
    }

    @Test
    public void getQuantite() throws Exception {
        assertTrue(a1.getQuantite()==1);
    }

    @Test
    public void setQuantite() throws Exception {
        a1.setQuantite(2);
        assertTrue(a1.getQuantite()==2);
    }
}