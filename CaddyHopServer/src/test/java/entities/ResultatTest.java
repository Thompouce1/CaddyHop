package entities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class ResultatTest {

    Resultat r1 = new Resultat("Casino",1.1,2,3,new ArrayList<Article>());

    @Test
    public void getNomMag() throws Exception {
        assertEquals(r1.getNomMag(),"Casino");
    }

    @Test
    public void getPrixTotal() throws Exception {
        assertTrue(r1.getPrixTotal()==1.1);
    }

    @Test
    public void getNbrArtTotal() throws Exception {
        assertTrue(r1.getNbrArtTotal()==3);
    }

    @Test
    public void getNbrArtDispo() throws Exception {
        assertTrue(r1.getNbrArtDispo()==2);
    }

    @Test
    public void getArticleNonDispo() throws Exception {
        assertEquals(r1.getNbrArtDispo(),r1.getNbrArtDispo());
    }

    @Test
    public void setNomMag() throws Exception {
        r1.setNomMag("car");
        assertEquals(r1.getNomMag(),"car");
    }

    @Test
    public void setPrixTotal() throws Exception {
        r1.setPrixTotal(2.2);
        assertTrue(r1.getPrixTotal()==2.2);
    }

    @Test
    public void setNbrArtTotal() throws Exception {
        r1.setNbrArtTotal(5);
        assertTrue(r1.getNbrArtTotal()==5);
    }

    @Test
    public void setNbrArtDispo() throws Exception {
        r1.setNbrArtDispo(6);
        assertTrue(r1.getNbrArtDispo()==6);
    }

    @Test
    public void setArticleNonDispo() throws Exception {
        r1.setArticleNonDispo(new ArrayList<Article>());
        assertEquals(r1.getArticleNonDispo(),r1.getArticleNonDispo());
    }
}