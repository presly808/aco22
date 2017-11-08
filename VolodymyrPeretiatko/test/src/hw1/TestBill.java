package hw1;

import org.junit.*;


public class TestBill {

    private Bill bill;
    private static Bill testDataBill;
    private static Salesman testDataSalesman;
    private static Product testDataProduct;


    @BeforeClass
    public static void initTestData(){
        testDataSalesman = new Salesman("Jhon Lohan", "Jhon", "qwerty");
        testDataProduct = new Product(1, "Laptop HP 750B", 500.0);
        testDataBill = new Bill(1, testDataSalesman);
        testDataBill.addProduct(testDataProduct);
    }

    @Before
    public void init(){
        bill = new Bill(1, testDataSalesman);
    }



}
