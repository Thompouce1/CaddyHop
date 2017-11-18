package entities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class UtilisateurTest {

    Utilisateur u1 = new Utilisateur("user2","mdp2","email2","nom2","prenom2");

    @Test
    public void creerListe() throws Exception {
        u1.creerListe("Lundi");
        assertTrue(u1.getItemListList().size()==1);
    }

    @Test
    public void generateID() throws Exception {
        u1.generateID();
        assertTrue(u1.getId()==u1.getId());
    }

    @Test
    public void getLogin() throws Exception {
        assertEquals(u1.getLogin(),"user2");
    }

    @Test
    public void getMdp() throws Exception {
        assertEquals(u1.getMdp(),"mdp2");
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals(u1.getEmail(),"email2");
    }

    @Test
    public void getNom() throws Exception {
        assertEquals(u1.getNom(),"nom2");
    }

    @Test
    public void getPrenom() throws Exception {
        assertEquals(u1.getPrenom(),"prenom2");
    }

    @Test
    public void getId() throws Exception {
        assertTrue(u1.getId()==u1.getId());
    }

    @Test
    public void setLogin() throws Exception {
        u1.setLogin("log");
        assertEquals(u1.getLogin(),"log");
    }

    @Test
    public void setMdp() throws Exception {
        u1.setMdp("dd");
        assertEquals(u1.getMdp(),"dd");
    }

    @Test
    public void setEmail() throws Exception {
        u1.setEmail("mai");
        assertEquals(u1.getEmail(),"mai");
    }

    @Test
    public void setNom() throws Exception {
        u1.setNom("coucou");
        assertEquals(u1.getNom(),"coucou");
    }

    @Test
    public void setPrenom() throws Exception {
        u1.setPrenom("blabla");
        assertEquals(u1.getPrenom(),"blabla");
    }

    @Test
    public void setId() throws Exception {
        u1.setId(78);
        assertTrue(u1.getId()==78);
    }

    @Test
    public void getItemListList() throws Exception {
        assertEquals(u1.getItemListList(),u1.getItemListList());
    }

    @Test
    public void setItemListList() throws Exception {
        ArrayList<ItemList> list = new ArrayList<ItemList>();
        u1.setItemListList(list);
        assertEquals(u1.getItemListList(),list);
    }

    @Test
    public void delList() throws Exception {
        u1.delList(0);
        assertTrue(u1.getItemListList().size()==0);
    }

    @Test
    public void getListCarteFidel() throws Exception {
        assertTrue(u1.getListCarteFidel().size()==0);
    }

    @Test
    public void setListCarteFidel() throws Exception {
        ArrayList<Fidelite> list1 = new ArrayList<Fidelite>();
        u1.setListCarteFidel(list1);
        assertTrue(u1.getListCarteFidel().size()==0);
    }
}