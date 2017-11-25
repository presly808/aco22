package hw1.model;

import org.junit.*;

public class TestSalesman {

    private Salesman s1,s2;

    @Before
    public void init(){
        s1 = new Salesman("Jhon Lohan",    "Jhon",  "qwerty");
        s2 = new Salesman("Frank Sinatra", "Frank", "qwerty");
    }

    @Test
    public void testEquals(){
        Assert.assertFalse(s1.equals(s2));
        Salesman s3 = new Salesman("Jhon Lohan",    "Jhon",  "qwerty");
        Assert.assertTrue(s1.equals(s3));
    }

    @Test
    public void testHashCode(){
        Assert.assertFalse(s1.hashCode() == s2.hashCode());
        Salesman s3 = new Salesman("Jhon Lohan",    "Jhon",  "qwerty");
        Assert.assertTrue(s1.hashCode() == s3.hashCode());
    }

}
