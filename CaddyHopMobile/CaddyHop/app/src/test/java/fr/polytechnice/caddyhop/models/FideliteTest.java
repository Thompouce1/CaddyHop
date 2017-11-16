package fr.polytechnice.caddyhop.models;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by shafiq on 14/06/16.
 */

public class FideliteTest {

    @Test
    public void creationTest() {
        Fidelite fid1 = new Fidelite();
        fid1.setCodeFidel("000000");
        assertEquals("000000", fid1.getCodeFidel());
        fid1.setNomMag("Casino");
        assertEquals("Casino", fid1.getNomMag());

        Fidelite fid2 = new Fidelite("Carrefour", "1111");
        assertNotNull(fid2);
        assertEquals("Carrefour", fid2.getNomMag());
        assertEquals("1111", fid2.getCodeFidel());
        assertNotNull(fid2.toString());
    }
}
