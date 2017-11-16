package hw1.model;


import org.junit.*;

public class TestProduct {

    private Product product;
    private static Product testData1;
    private static Product testData2;

    @BeforeClass
    public static void initTestData(){

        testData1 = new Product(1, "Laptop HP 750B", 500.0);
        testData2 = new Product(2, "Laptop HP 1020Z", 650.0);
    }

    @AfterClass
    public static void clearTestData(){
        testData1 = null;
        testData2 = null;
    }

    @Before
    public void init(){

        product = new Product(1, "Laptop HP 750B", 500.00);

    }

    @Test
    public void testProductEquels(){
        Assert.assertTrue(product.equals(testData1));
        Assert.assertFalse(product.equals(testData2));
    }

    @Test
    public void testProductHash(){
        Assert.assertEquals(testData1.hashCode(), product.hashCode());
        Assert.assertNotEquals(testData2.hashCode(), product.hashCode());
    }

    @Test
    public void testGetPrice(){
        Assert.assertEquals(500.0, product.getPrice(), 0.1);
    }

}
