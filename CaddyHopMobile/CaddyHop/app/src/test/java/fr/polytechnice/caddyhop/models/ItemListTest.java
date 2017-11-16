package fr.polytechnice.caddyhop.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by shafiq on 14/06/16.
 */

public class ItemListTest {

    @Test
    public void itemListTest() {
        ItemList itemList = new ItemList();
        assertNotNull(itemList);

        itemList.setId(0);
        assertEquals(0, itemList.getId());

        List<Article> articleList = new ArrayList<Article>();
        itemList.setArticleList(articleList);
        assertNotNull(itemList.getArticleList());
        assertEquals(0, itemList.getArticleList().size());

        Article article = new Article();
        articleList.add(article);
        itemList.setArticleList(articleList);
        assertEquals(1, itemList.getArticleList().size());

        itemList.setNom("itemList");
        assertEquals("itemList", itemList.getNom());
    }

    @Test
    public void equalityTest() {
        ItemList itemList1 = new ItemList();
        ItemList itemList2 = new ItemList();

        itemList1.setId(1);
        itemList2.setId(1);

        assertTrue(itemList1.equals(itemList1));

        Article a = new Article();
        assertFalse(itemList1.equals(a));

        assertTrue(itemList1.equals(itemList2));

        itemList2.setId(2);
        assertFalse(itemList1.equals(itemList2));

        assertNotNull(itemList1.toString());
    }
}
