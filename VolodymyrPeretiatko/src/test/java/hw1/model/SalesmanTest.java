package hw1.model;

import org.junit.*;

import java.util.List;

public class SalesmanTest {

    private Salesman s1,s2,s3,s4,s5,s6;

    @Before
    public void init(){
        s1 = new Salesman("Jhon Lohan",    "Jhon",  "qwerty");
        s2 = new Salesman("1.1", "Frank", "qwerty");
        s3 = new Salesman("1.2", "Frank", "qwerty");
        s4 = new Salesman("1.3", "Frank", "qwerty");
        s5 = new Salesman("1.2.1", "Frank", "qwerty");
        s6 = new Salesman("1.2.2", "Frank", "qwerty");
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

    @Test
    public void testGetAllSubSalesmen(){

        s1.addSubSalesman(s2);
        s1.addSubSalesman(s3);

        s3.addSubSalesman(s5);
        s3.addSubSalesman(s6);

        List<Salesman> actual = Salesman.getAllSubSalesmen(s1);

        System.out.println(actual);

    }

}
