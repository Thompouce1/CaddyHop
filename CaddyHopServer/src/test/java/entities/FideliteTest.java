package entities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class FideliteTest {

    Fidelite f1 = new Fidelite("Casino","1111");

    @Test
    public void getNomMag() throws Exception {
        assertEquals(f1.getNomMag(),"Casino");
    }

    @Test
    public void getCodeFidel() throws Exception {
        assertEquals(f1.getCodeFidel(),"1111");
    }

    @Test
    public void setNomMag() throws Exception {
        f1.setNomMag("Carrefour");
        assertEquals(f1.getNomMag(),"Carrefour");
    }

    @Test
    public void setCodeFidel() throws Exception {
        f1.setCodeFidel("2222");
        assertEquals(f1.getCodeFidel(),"2222");
    }
}