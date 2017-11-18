package services;

import database.MockDatabase;
import entities.Categorie;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class ArticleServiceTest {

    MockDatabase mock = new MockDatabase();

    @Test
    public void getListArticleByCategorie() throws Exception {
        assertTrue(mock.selectArticleByCategorie(Categorie.ACCESSOIRE_T_C)!=mock.selectArticleByCategorie(Categorie.SAUCE_E_SA));
    }
}