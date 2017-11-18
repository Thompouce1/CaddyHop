package fr.polytechnice.caddyhop.models;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by shafiq on 14/06/16.
 */
public class UserTest {
    @Test
    public void nullTest() {
        User user = new User();
        assertNotNull(user);

        user = new User("login", "mdp", "email", "nom", "prenom");

        assertEquals("login", user.getLogin());
        assertEquals("mdp", user.getMdp());
        assertEquals("email", user.getEmail());
        assertEquals("nom", user.getNom());
        assertEquals("prenom", user.getPrenom());
    }

    @Test
    public void setterTest() {
        User user = new User();
        user.setNom("nom");
        assertEquals("nom", user.getNom());
        user.setId(0);
        assertEquals(0, user.getId());
        user.setPrenom("prenom");
        assertEquals("prenom", user.getPrenom());
        user.setEmail("email");
        assertEquals("email", user.getEmail());
        user.setMdp("mdp");
        assertEquals("mdp", user.getMdp());
        user.setLogin("login");
        assertEquals("login", user.getLogin());

        List<ItemList> itemLists = new ArrayList<>();
        user.setItemListList(itemLists);
        assertEquals(itemLists, user.getItemListList());

        assertNotNull(user.toString());
        itemLists.add(new ItemList());

        user.setActiveItemList(itemLists.get(0));
        assertNotNull(user.getActiveItemList());

        ArrayList<Fidelite> fid = new ArrayList<>();
        user.setListCarteFidel(fid);
        assertNotNull(user.getListCarteFidel());
    }
}
