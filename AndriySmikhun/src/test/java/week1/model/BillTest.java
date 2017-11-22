package week1.model;


import org.junit.*;
import static org.junit.Assert.*;


public class BillTest {
    private Product product1, product2;
    private Bill bill1;


    @Before
    public void initData() {

        product1 = new Product(1,"Grecha", 40.00);
        product2 = new Product(2,"juse", 20.00);







    }


    @Test
    public void getSalesman() {
        Assert.assertEquals(bill1.getSalesman(),bill1.getSalesman());
    }

    @Test
    public void getAmountPrice() {
        Assert.assertEquals(bill1.getAmountPrice(),bill1.getAmountPrice(),1);
    }

    @Test
    public void getDataTime() {
        Assert.assertEquals(bill1.getDataTime(),bill1.getDataTime());
    }

    @Test
    public void addProduct1(){
        Assert.assertTrue(bill1.addProduct(product1));
    }

    @Test
    public void calculateAmountPrice() {
        bill1.addProduct(product1);
        bill1.addProduct(product2);
        double sum = bill1.calculateAmountPrice();
        Assert.assertEquals(60.0,sum,1);
    }

    @Test
    public void printBill()  {
        assertTrue(bill1.setCloseBill());
    }

    @Test
    public void compareTo()  {
        Assert.assertEquals(product1.compareTo(product1),0);

    }

    @Test
    public void setCloseBill() {
        assertTrue(bill1.setCloseBill());
    }

}