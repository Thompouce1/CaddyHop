package services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09/06/2016.
 */
public class RegisterServiceTest {

    RegisterService re = new RegisterService();
    @Test
    public void authentification() throws Exception {
        assertTrue(re.authentification("coucou","blabla")==-1);
    }
}