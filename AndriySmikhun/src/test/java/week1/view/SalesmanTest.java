package week1.view;

import org.junit.*;

import week1.model.Salesman;

import static org.junit.Assert.*;

public class SalesmanTest {

    private Salesman s1, s2, s3;

    @Before
    public void init(){

        s1 = new Salesman("Marusia", "maria", "pass", true);
        s2 = new Salesman("Oleg","oleg","pass",false);
        s3 = new Salesman("Marusia", "maria", "pass", true);

    }

    @Test
    public void testEquals(){
        assertEquals(s1,s3);
        assertNotEquals(s1,s2);

    }

    @Test
    public void testHashCode(){
        assertEquals(s1.hashCode(),s3.hashCode());
        assertNotEquals(s1.hashCode(),s2.hashCode());
    }

}