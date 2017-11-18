package fr.polytechnice.caddyhop.models;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by shafiq on 14/06/16.
 */

public class ArticleTest {
    @Test
    public void creationTest() {
        Article article = new Article();
        assertNotNull(article);
        article.setNom("article");
        assertEquals("article", article.getNom());

        Article article1 = new Article("article1");
        assertNotNull(article1);
        assertEquals("article1", article1.getNom());

        article.setCodeBarre("0000");
        assertEquals("0000", article.getCodeBarre());

        float prix = (float)10.20;
        article.setPrix(prix);
        assertEquals(prix, article.getPrix());

        article.setQuantite(1);
        assertEquals(1, article.getQuantite());
    }

    @Test
    public void equalityTest() {
        Article a1 = new Article();
        a1.setCodeBarre("0000");
        Article a2 = new Article();
        a2.setCodeBarre("1111");
        assertFalse(a1.equals(a2));
        a2.setCodeBarre("0000");
        assertTrue(a1.equals(a2));

        assertNotNull(a1.toString());

        ItemList itemList = new ItemList();
        assertFalse(a1.equals(itemList));
    }
}
