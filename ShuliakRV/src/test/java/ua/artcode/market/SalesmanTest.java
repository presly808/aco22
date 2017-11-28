package ua.artcode.market;

import org.junit.Test;
import ua.artcode.market.models.Salesman;

import static org.junit.Assert.*;

public class SalesmanTest {

    private Salesman s = new Salesman("Andry1", "Andry", "1234567");

    @Test
    public void getFullname() throws Exception {
        assertEquals("Andry1", s.getFullname());
    }

    @Test
    public void getLogin() throws Exception {
        assertEquals("Andry", s.getLogin());
    }

    @Test
    public void getPassword() throws Exception {
        assertEquals("1234567", s.getPassword());
    }

    @Test
    public void isLogged() throws Exception {
        assertEquals(false, s.isLogged());
    }

}