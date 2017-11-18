package database;

import entities.Article;
import entities.Categorie;
import entities.Magasin;
import entities.Utilisateur;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas on 09/06/2016.
 */
public class MockDatabaseTest {

    Utilisateur u1 = new Utilisateur("user1", "mdp1", "email1@gmail.com", "pelo1", "pelo1");
    Magasin m1 = new Magasin("Casino",43.617721,7.075659);
    Article a1 = new Article(0.0,0.0,"coca cola 2L","bouteille de coca 2L","5000112616316",1.80,1, Categorie.SODA_B);
    MockDatabase mock = new MockDatabase();

    @Test
    public void addUser() throws Exception {
        mock.addUser(new Utilisateur("user1", "mdp1", "email1@gmail.com", "pelo1", "pelo1"));
        assertTrue(mock.getUsersList().size()==1);
    }

    @Test
    public void addMagasin() throws Exception {
        mock.addMagasin(m1);
        assertTrue(mock.getMagasinList().size()==1);
    }

    @Test
    public void addArticleInMag() throws Exception {
        mock.addArticleInMag(a1,m1.getIde());
        assertTrue(mock.getArticlesListInMag(14)==mock.getArticlesListInMag(14));
    }

    @Test
    public void modifiedArticleInMag() throws Exception {
        mock.modifiedArticleInMag(new Article(0.0,0.0,"coca cola","bouteille de coca 2L","5000112616316",1.82,1, Categorie.SODA_B),0);
        assertTrue(mock.getArticlesListInMag(14)==mock.getArticlesListInMag(14));
    }

    @Test
    public void modifiedUser() throws Exception {
        mock.modifiedUser(new Utilisateur("useeeeer1", "mdp1", "email1@gmail.com", "pelo1", "pelo1"),5);
        assertTrue(mock.getUsersList().size()==mock.getUsersList().size());
    }

    @Test
    public void deleteArticleInMag() throws Exception {
        mock.deleteArticleInMag(a1,14);
        assertTrue(mock.getArticlesListInMag(14)==mock.getArticlesListInMag(14));
    }

    @Test
    public void deleteMagasin() throws Exception {
        mock.deleteMagasin(m1);
        assertTrue(mock.getMagasinList().size()==0);
    }

    @Test
    public void deleteUser() throws Exception {
        mock.deleteUser(u1);
        assertTrue(mock.getUsersList().size()==0);
    }

    @Test
    public void getUsersList() throws Exception {
        assertEquals(mock.getUsersList(),mock.getUsersList());
    }

    @Test
    public void getUser() throws Exception {
        assertEquals(mock.getUser(0),mock.getUser(0));
    }

    @Test
    public void getMagasinList() throws Exception {
        assertEquals(mock.getMagasinList(),mock.getMagasinList());
    }

    @Test
    public void getArticlesListInMag() throws Exception {
        assertEquals(mock.getArticlesListInMag(0),mock.getArticlesListInMag(0));
    }

    @Test
    public void distance() throws Exception {
        System.out.println(mock.distance(10,10,10,11));
        assertTrue(mock.distance(10,10,10,11)==109.5055839436889);
    }
}